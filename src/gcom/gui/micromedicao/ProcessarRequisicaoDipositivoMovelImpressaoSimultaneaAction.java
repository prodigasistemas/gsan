package gcom.gui.micromedicao;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.RetornoAtualizarFaturamentoMovimentoCelularHelper;
import gcom.gui.GcomAction;
import gcom.micromedicao.ArquivoTextoRetornoIS;
import gcom.micromedicao.ServicoTipoCelular;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.util.Util;
import gcom.util.ZipUtil;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jboss.logging.Logger;

public class ProcessarRequisicaoDipositivoMovelImpressaoSimultaneaAction extends GcomAction {

	private static final char RESPOSTA_ERRO = '#';
	private static final byte RESPOSTA_OK = '*';

	private static final int CONFIRMAR_ARQUIVO_RECEBIDO = 3;
	private static final int PACOTE_BAIXAR_ARQUIVO = 0;
	private static final int ATUALIZAR_MOVIMENTO = 1;
	public static final int FINALIZAR_LEITURA = 2;
	public static final int FINALIZAR_LEITURA_INCOMPLETA = 6;
	private static final int BAIXAR_NOVA_VERSAO_JAD = 4;
	private static final int BAIXAR_NOVA_VERSAO_JAR = 5;
	public static final int FINALIZAR_LEITURA_ARQUIVO_IMOVEIS_FALTANDO = 7;

	private Logger logger = Logger.getLogger(ProcessarRequisicaoDipositivoMovelImpressaoSimultaneaAction.class);
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

		InputStream in = null;
		OutputStream out = null;

		try {
			out = response.getOutputStream();
		} catch (IOException e) {
			logger.error("Erro: " + e.getMessage());
		}

		try {
			in = request.getInputStream();
			
			ZipInputStream zin = new ZipInputStream(request.getInputStream());
        	ZipEntry ze = null;
        	ZipUtil.criarZip(request.getInputStream(), obterNomeArquivoRetorno(request.getInputStream()), obterCaminhoArquivo());

			while ((ze = zin.getNextEntry()) != null) {
        		if (isArquivoRetorno(ze.getName())) {
        			
        			DataInputStream din = new DataInputStream(zin);
        			int pacote = din.readByte();
        			
        			logger.info("Solicitacao Mobile : " + pacote);
        			
        			switch (pacote) {
        			case PACOTE_BAIXAR_ARQUIVO:
        				this.baixarArquivo(din, response, out);
        				break;
        				
        			case ATUALIZAR_MOVIMENTO:
        				this.atualizarMovimentacao(din, response, out);
        				break;
        			case FINALIZAR_LEITURA:
        				this.finalizarMovimentacao(din, response, out, FINALIZAR_LEITURA);
        				break;
        				
        			case FINALIZAR_LEITURA_INCOMPLETA:
        				this.finalizarMovimentacao(din, response, out, FINALIZAR_LEITURA_INCOMPLETA);
        				break;
        				
        			case CONFIRMAR_ARQUIVO_RECEBIDO:
        				this.confirmacaoArquivoRecebido(din, response, out);
        				break;
        				
        			case BAIXAR_NOVA_VERSAO_JAD:
        				this.baixarNovaVersaoJad(din, response, out);
        				break;
        				
        			case BAIXAR_NOVA_VERSAO_JAR:
        				this.baixarNovaVersaoJar(din, response, out);
        				break;
        				
        			case FINALIZAR_LEITURA_ARQUIVO_IMOVEIS_FALTANDO:
        				this.finalizarMovimentacao(din, response, out,FINALIZAR_LEITURA_ARQUIVO_IMOVEIS_FALTANDO);
        				break;
        			}
        		}
			}

		} catch (Exception e) {
			e.printStackTrace();
			
			logger.error("Erro: " + e.getMessage());
			response.setContentLength(1);
			try {
				out.write(RESPOSTA_ERRO);
				out.flush();
			} catch (IOException e1) {
				logger.error("Erro: " + e.getMessage());
			}
			
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return null;

	}

	/**
	 * [UC0811] Processar Requisições do Dispositivo Móvel Impressao Simultanea.
	 */
	public void baixarArquivo(DataInputStream data, HttpServletResponse response, OutputStream out) throws IOException {
		
		Fachada fachada = Fachada.getInstancia();
		long imei = data.readLong();

		logger.info("imei: " + imei);

		try {
			Object[] retorno = fachada.baixarArquivoTextoParaLeituristaImpressaoSimulanea(imei, ServicoTipoCelular.IMPRESSAO_SIMULTANEA);
			byte[] arq = (byte[]) retorno[0];
			String nomeArquivo = (String) retorno[1];

			if (arq != null) {
				logger.info("Inicio : Baixando arquivo Mobile");

				String tipoArquivo = "";

				if (nomeArquivo.endsWith(".txt")) {
					tipoArquivo = "tipoArquivo=T&";
				} else {
					tipoArquivo = "tipoArquivo=G&";
				}

				String parametroArquivoBaixarRota = "arquivoRoteiro=";

				response.setContentLength(1 + tipoArquivo.getBytes().length + parametroArquivoBaixarRota.getBytes().length + arq.length);

				out.write(RESPOSTA_OK);
				out.write(tipoArquivo.getBytes());
				out.write(parametroArquivoBaixarRota.getBytes());
				out.write(arq);

				out.flush();

				logger.info("Fim: Baixando arquivo Mobile");
			} else {
				logger.info("Erro ao Baixar arquivo Mobile");
				response.setContentLength(1);
				out.write(RESPOSTA_ERRO);
				out.flush();
			}

		} catch (Exception e) {
			logger.error("Erro ao baixar arquivo mobile");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();

		}
	}

	/**
	 * [UC0811] Processar Requisições do Dispositivo Móvel.
	 */
	public void atualizarMovimentacao(DataInputStream data, HttpServletResponse response, OutputStream out) throws IOException {
		InputStreamReader reader = new InputStreamReader(data);
		BufferedReader buffer = new BufferedReader(reader);

		try {
			RetornoAtualizarFaturamentoMovimentoCelularHelper retorno = Fachada
					.getInstancia().atualizarFaturamentoMovimentoCelular(buffer, false, false, null, null, buffer);
			
			if (retorno.getRelatorioConsistenciaProcessamento() != null) {
			
				logger.info("Erro ao atualizar faturamento movimento celular");
				response.setContentLength(1);
				out.write(RESPOSTA_ERRO);
				out.flush();

			} else if (retorno.getMensagemComunicacaoServidorCelular() != null) {
				logger.info("Validação encontrada. Retornando para o celular.");
				response.setContentLength(1 + retorno.getMensagemComunicacaoServidorCelular().length());

				if (retorno.getIndicadorSucessoAtualizacao()) {
					out.write(RESPOSTA_OK);
				} else {
					out.write(RESPOSTA_ERRO);
				}

				out.write(retorno.getMensagemComunicacaoServidorCelular().getBytes());
				out.flush();
			} else {
				response.setContentLength(1);
				out.write(RESPOSTA_OK);
				out.flush();
				logger.info("Fim: atualizar faturamento movimento celular");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao atualizar faturamento movimento celular");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}

	@SuppressWarnings({ "unchecked", "resource" })
	public void finalizarMovimentacao(DataInputStream data, HttpServletResponse response, OutputStream out, int tipoFinalizacao) throws IOException {
		
		Fachada fachada = Fachada.getInstancia();
		long imei = data.readLong();
		Integer idRota = 0;

		InputStreamReader reader = new InputStreamReader(data);
		InputStreamReader readerOriginal = new InputStreamReader(data);
		BufferedReader buffer = new BufferedReader(reader);
		BufferedReader bufferOriginal = new BufferedReader(readerOriginal);

		Integer codRota = null;
		Integer setorComercial = null;
		Integer idLocalidade = null;
		Integer numeroSequenciaArquivo = null;

		try {
			String registro0 = buffer.readLine();

			int indcFinalizacao = Integer.parseInt(registro0.substring(1, 2));
			idLocalidade = Integer.parseInt(registro0.substring(2, 5));
			setorComercial = Integer.parseInt(registro0.substring(5, 8));
			codRota = Integer.parseInt(registro0.substring(8,15));

			if (registro0.length() == 17) {
				numeroSequenciaArquivo = Integer.parseInt(registro0.substring(15, 17));
				idRota = fachada.obterIdRotaPorSetorComercialELocalidade(codRota, setorComercial, idLocalidade);
			} else {
				idRota = Integer.parseInt(registro0.substring(15, 19));
				numeroSequenciaArquivo = Integer.parseInt(registro0.substring(19, 21));
			}

			if (idRota == null) {
				String primeiroRegistro = buffer.readLine();
				Integer matricula = Integer.parseInt(primeiroRegistro.substring(1, 10));

				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("rotaAlternativa.setorComercial");
				filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, matricula));
				
				Collection<Imovel> colImovel = Fachada.getInstancia().pesquisar(filtroImovel, Imovel.class.getName());
				Imovel imo = (Imovel) Util.retonarObjetoDeColecao(colImovel);

				idLocalidade = imo.getLocalidade().getId();
				setorComercial = imo.getRotaAlternativa().getSetorComercial().getCodigo();
				codRota = imo.getRotaAlternativa().getCodigo().intValue();

				idRota = fachada.obterIdRotaPorSetorComercialELocalidade(codRota, setorComercial, idLocalidade);

				String linha;
				StringBuffer arquivo = new StringBuffer();
				arquivo.append(primeiroRegistro + "\n");

				while ((linha = buffer.readLine()) != null) {
					arquivo.append(linha + "\n");
				}

				InputStream is = new ByteArrayInputStream(arquivo.toString().getBytes());
				InputStreamReader readerRetorno = new InputStreamReader(is);
				buffer = new BufferedReader(readerRetorno);
			}

			Integer anoMesFaturamento = fachada.retornaAnoMesFaturamentoGrupoDaRota(idRota);

			Localidade localidade = new Localidade(idLocalidade);

			ArquivoTextoRetornoIS arquivoRetorno = new ArquivoTextoRetornoIS();
			arquivoRetorno.setAnoMesReferencia(anoMesFaturamento);
			arquivoRetorno.setCodigoRota(codRota);
			arquivoRetorno.setCodigoSetorComercial(setorComercial);
			arquivoRetorno.setLocalidade(localidade);
			arquivoRetorno.setNomeArquivo(fachada.obterNomeArquivoRetorno(arquivoRetorno).toString());
			arquivoRetorno.setTempoRetornoArquivo(new Date());
			arquivoRetorno.setTipoFinalizacao(new Short(tipoFinalizacao + ""));
			arquivoRetorno.setUltimaAlteracao(new Date());

			logger.info("Finalizando arquivo Mobile [Imei=" + imei + ", Localidade: " + localidade.getId() + ", Setor: " + setorComercial + ", Rota: " + codRota + "]");

			if (indcFinalizacao == FINALIZAR_LEITURA_ARQUIVO_IMOVEIS_FALTANDO) {
				buffer = fachada.removerImoveisJaProcessadosBufferImpressaoSimultanea(idRota, buffer);
			}

			RetornoAtualizarFaturamentoMovimentoCelularHelper retorno = null;

			if (buffer != null) {
				retorno = fachada.atualizarFaturamentoMovimentoCelular(buffer, false, true, idRota, arquivoRetorno, bufferOriginal);
			}

			if (retorno != null
					&& (retorno.getRelatorioConsistenciaProcessamento() != null || retorno.getMensagemComunicacaoServidorCelular() != null)) {

				logger.info("Erro ao finalizar rota [Imei=" + imei + ", Localidade: " + localidade.getId() + ", Setor: " + setorComercial + ", Rota: " + codRota + "]");
				
				response.setContentLength(1);
				out.write(RESPOSTA_ERRO);
				out.flush();
			} else {

				Integer[] idsSituacaoTransmissao = new Integer[1];
				idsSituacaoTransmissao[0] = SituacaoTransmissaoLeitura.TRANSMITIDO;

				if (indcFinalizacao == FINALIZAR_LEITURA || indcFinalizacao == FINALIZAR_LEITURA_ARQUIVO_IMOVEIS_FALTANDO) {

					Integer diferenca = fachada.pesquisarDiferencaQuantidadeMovimentoContaPrefaturadaArquivoTextoRoteiroEmpresa(idRota, anoMesFaturamento);

					if (!fachada.isRotaDividida(idRota, anoMesFaturamento)) {
						if (diferenca != 0) {
							String msg = "Validação encontrada. Retornando para o celular";
							msg += "[Diferença de imoveis: " + diferenca;
							msg += ", Imei=" + imei + ", Localidade: " + localidade.getId() + ", Setor: " + setorComercial + ", Rota: " + codRota + "]";

							logger.info(msg);
							
							String mensagem = "mensagem=A quantidade de imóveis enviados não corresponde ao esperado";
							response.setContentLength(1 + mensagem.getBytes().length);
							out.write(RESPOSTA_ERRO);
							out.write(mensagem.getBytes());
							out.flush();
						} else {
							fachada.atualizarArquivoTextoEnviadoPorRota(idRota, SituacaoTransmissaoLeitura.EM_CAMPO, SituacaoTransmissaoLeitura.TRANSMITIDO);
						}
					} else {
						fachada.atualizarArquivoTextoDividido(idRota, anoMesFaturamento, numeroSequenciaArquivo, SituacaoTransmissaoLeitura.EM_CAMPO, SituacaoTransmissaoLeitura.TRANSMITIDO);

						if (diferenca != 0) {
							String msg = "Validação encontrada. Retornando para o celular";
							msg += "[Diferença de imoveis: " + diferenca;
							msg += ", Imei=" + imei + ", Localidade: "
									+ localidade + ", Setor: " + setorComercial
									+ ", Rota: " + codRota + "]";

							logger.info(msg);

							String mensagem = "mensagem=A quantidade de imóveis enviados não corresponde ao esperado";
							response.setContentLength(1 + mensagem.getBytes().length);
							out.write(RESPOSTA_ERRO);
							out.write(mensagem.getBytes());
							out.flush();
						} else {
							fachada.atualizarArquivoTextoEnviado(imei, SituacaoTransmissaoLeitura.EM_CAMPO, SituacaoTransmissaoLeitura.TRANSMITIDO);

							if (!fachada.verificarExistenciaArquivosDivididosSituacaoDiferente(idRota, anoMesFaturamento, idsSituacaoTransmissao)) {
								fachada.atualizarArquivoTextoEnviadoPorRota(idRota, SituacaoTransmissaoLeitura.EM_CAMPO, SituacaoTransmissaoLeitura.TRANSMITIDO);
							} else {
								idsSituacaoTransmissao = new Integer[2];
								idsSituacaoTransmissao[0] = SituacaoTransmissaoLeitura.TRANSMITIDO;
								idsSituacaoTransmissao[1] = SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO;
								
								if (!fachada.verificarExistenciaArquivosDivididosSituacaoDiferente(idRota, anoMesFaturamento, idsSituacaoTransmissao)) {
									fachada.atualizarArquivoTextoEnviadoPorRota(idRota, SituacaoTransmissaoLeitura.EM_CAMPO, SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO);
								}
							}
						}

					}

					response.setContentLength(1);
					out.write(RESPOSTA_OK);
					out.flush();

					logger.info("Fim: finalizar rota [Localidade: " + localidade.getId() + ", Setor: " + setorComercial + ", Rota: " + codRota + "]");

				} else if (indcFinalizacao == FINALIZAR_LEITURA_INCOMPLETA) {

					if (!fachada.isRotaDividida(idRota, anoMesFaturamento)) {
						fachada.atualizarArquivoTextoEnviadoPorRota(idRota, SituacaoTransmissaoLeitura.EM_CAMPO, SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO);
					
					} else {
						fachada.atualizarArquivoTextoEnviado(imei, SituacaoTransmissaoLeitura.EM_CAMPO, SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO);
						
						if (!fachada.verificarExistenciaArquivosDivididosSituacaoDiferente(idRota, anoMesFaturamento, idsSituacaoTransmissao)) {
							fachada.atualizarArquivoTextoEnviadoPorRota(idRota, SituacaoTransmissaoLeitura.EM_CAMPO, SituacaoTransmissaoLeitura.TRANSMITIDO);
						
						} else {
							idsSituacaoTransmissao = new Integer[2];
							idsSituacaoTransmissao[0] = SituacaoTransmissaoLeitura.TRANSMITIDO;
							idsSituacaoTransmissao[1] = SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO;
							
							if (!fachada.verificarExistenciaArquivosDivididosSituacaoDiferente(idRota, anoMesFaturamento,idsSituacaoTransmissao)) {

								fachada.atualizarArquivoTextoEnviadoPorRota(idRota, SituacaoTransmissaoLeitura.EM_CAMPO, SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO);
							}
						}
					}
					response.setContentLength(1);
					out.write(RESPOSTA_OK);
					out.flush();

					logger.info("Fim: finalizar rota [Localidade: " + localidade.getId() + ", Setor: " + setorComercial + ", Rota: " + codRota);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao atualizar faturamento movimento celular [Localidade: " + idLocalidade + ", Setor: " + setorComercial + ", Rota: " + codRota);
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}

	/**
	 * 
	 * Método de Comfirmação do Recebimento do Arquivo
	 * 
	 * @since 23/05/08
	 * @param data
	 * @throws IOException
	 */
	public void confirmacaoArquivoRecebido(DataInputStream data, HttpServletResponse response, OutputStream out) throws IOException {
		
		Fachada fachada = Fachada.getInstancia();
		long imei = data.readLong();

		logger.info("Confirmando Recebimento do arquivo Mobile imei = " + imei);

		try {

			fachada.atualizarArquivoTextoEnviado(imei, SituacaoTransmissaoLeitura.EM_CAMPO, SituacaoTransmissaoLeitura.FINALIZADO_NAO_TRANSMITIDO);
			fachada.atualizarArquivoTextoEnviado(imei,SituacaoTransmissaoLeitura.LIBERADO, SituacaoTransmissaoLeitura.EM_CAMPO);

			if (fachada.liberaProximoArquivoImpressaoSimultaneaOnLine()) {
				fachada.atualizarArquivoTextoMenorSequencialLeitura(imei, SituacaoTransmissaoLeitura.DISPONIVEL,
						SituacaoTransmissaoLeitura.LIBERADO, ServicoTipoCelular.IMPRESSAO_SIMULTANEA);
			}

			response.setContentLength(1);
			out.write(RESPOSTA_OK);
			out.flush();
			logger.info("Fim: atualizar faturamento movimento celular");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao atualizar faturamento movimento celular");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}

	/**
	 * [UC0811] Processar Requisições do Dispositivo Móvel Impressao Simultanea.
	 */
	public void baixarNovaVersaoJad(DataInputStream data, HttpServletResponse response, OutputStream out) throws IOException {
	
		Fachada fachada = Fachada.getInstancia();

		try {
			byte[] arq = fachada.baixarNovaVersaoJad();

			if (arq != null) {
				String parametroTipoArquivo = "jad=";

				logger.info("Inicio : Baixando NOVA VERSÃO, ARQUIVO JAD Mobile");
				
				response.setContentLength(arq.length + parametroTipoArquivo.getBytes().length + 1);

				out.write(RESPOSTA_OK);
				out.write(parametroTipoArquivo.getBytes());
				out.write(arq);
				out.flush();

				logger.info("Fim: Baixando NOVA VERSÃO, ARQUIVO JAD Mobile");
			} else {
				logger.info("Erro ao Baixar arquivo Mobile");
				response.setContentLength(1);

				out.write(RESPOSTA_ERRO);
				out.flush();
			}

		} catch (Exception e) {

			logger.error("Erro ao Baixar arquivo Mobile");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();

		}
	}

	/**
	 * [UC0811] Processar Requisições do Dispositivo Móvel Impressao Simultanea.
	 */
	public void baixarNovaVersaoJar(DataInputStream data, HttpServletResponse response, OutputStream out) throws IOException {
		
		Fachada fachada = Fachada.getInstancia();

		try {
			byte[] arq = fachada.baixarNovaVersaoJar();

			if (arq != null) {

				String parametroTipoArquivo = "jar=";

				logger.info("Inicio : Baixando NOVA VERSÃO, ARQUIVO JAR Mobile");
				
				response.setContentLength(arq.length+ parametroTipoArquivo.getBytes().length + 1);

				out.write(RESPOSTA_OK);
				out.write(parametroTipoArquivo.getBytes());

				out.write(arq);
				out.flush();

				logger.info("Fim: Baixando NOVA VERSÃO, ARQUIVO JAR Mobile");
			} else {
				logger.info("Erro ao Baixar arquivo Mobile");
				response.setContentLength(1);

				out.write(RESPOSTA_ERRO);
				out.flush();
			}

		} catch (Exception e) {

			logger.info("Erro ao Baixar arquivo Mobile");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();

		}
	}
	
	private String obterCaminhoArquivo() {
		return null;
	}
	
	private String obterNomeArquivoRetorno(InputStream data) {
		return "Retorno.zip";
	}
	
	private boolean isArquivoRetorno(String nomeArquivo) {
		return nomeArquivo.startsWith("G");
	}
}
