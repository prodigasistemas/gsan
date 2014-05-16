package gcom.gui.micromedicao;

import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.bean.RetornoAtualizarFaturamentoMovimentoCelularHelper;
import gcom.gui.GcomAction;
import gcom.micromedicao.ArquivoTextoRetornoIS;
import gcom.micromedicao.ServicoTipoCelular;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.util.Util;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ProcessarRequisicaoDipositivoMovelImpressaoSimultaneaAction extends
		GcomAction {

	/**
	 * Constantes de Classe.
	 */
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

	/**
	 * Método Execute do Action
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		InputStream in = null;
		OutputStream out = null;

		try {
			out = response.getOutputStream();
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}

		try {
			in = request.getInputStream();
			DataInputStream din = new DataInputStream(in);

			int pacote = din.readByte();

			System.out.println("Solicitacao Mobile : " + pacote);
			switch (pacote) {
			case PACOTE_BAIXAR_ARQUIVO:
				// Baixar Arquivo
				this.baixarArquivo(din, response, out);
				break;
			case ATUALIZAR_MOVIMENTO:
				// Atualizar Movimento
				this.atualizarMovimentacao(din, response, out);
				break;
			case FINALIZAR_LEITURA:
				// Finalizar Movimento
				this.finalizarMovimentacao(din, response, out,
						FINALIZAR_LEITURA);
				break;
			case FINALIZAR_LEITURA_INCOMPLETA:
				// Finalizar Movimento
				this.finalizarMovimentacao(din, response, out,
						FINALIZAR_LEITURA_INCOMPLETA);
				break;
			case CONFIRMAR_ARQUIVO_RECEBIDO:
				// Finalizar Movimento
				this.confirmacaoArquivoRecebido(din, response, out);
				break;
			case BAIXAR_NOVA_VERSAO_JAD:
				// Carrega o arquivo jad do banco
				this.baixarNovaVersaoJad(din, response, out);
				break;
			case BAIXAR_NOVA_VERSAO_JAR:
				// Carrega o arquivo jar do banco
				this.baixarNovaVersaoJar(din, response, out);
				break;
			case FINALIZAR_LEITURA_ARQUIVO_IMOVEIS_FALTANDO:
				this.finalizarMovimentacao(din, response, out,
						FINALIZAR_LEITURA_ARQUIVO_IMOVEIS_FALTANDO);
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro: " + e.getMessage());
			response.setContentLength(1);
			try {
				out.write(RESPOSTA_ERRO);
				out.flush();
			} catch (IOException e1) {
				System.out.println("Erro: " + e.getMessage());
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
	 * 
	 * Método que baixa o arquivo de entrada do servidor.
	 * 
	 * @author Bruno Barros
	 * @date 13/09/2009
	 * 
	 * @param data
	 * @param response
	 * @throws IOException
	 */
	public void baixarArquivo(DataInputStream data,
			HttpServletResponse response, OutputStream out) throws IOException {
		Fachada fachada = Fachada.getInstancia();
		long imei = data.readLong();
		System.out.println("imei: " + imei);

		// ******************************************************************
		// 08/01/2009
		// COMENTADO PARA ENTRAR SÓ NA PROX. VERSÃO
		// String versao = data.readUTF();
		// System.out.println("versao: " + versao);
		// if(versao != null && versao.equals("v0.1")){
		// *************************************************************

		try {
			Object[] retorno = fachada
					.baixarArquivoTextoParaLeituristaImpressaoSimulanea(imei,
							ServicoTipoCelular.IMPRESSAO_SIMULTANEA);
			byte[] arq = (byte[]) retorno[0];
			String nomeArquivo = (String) retorno[1];

			if (arq != null) {
				System.out.println("Inicio : Baixando arquivo Mobile");

				String tipoArquivo = "";

				if (nomeArquivo.endsWith(".txt")) {
					tipoArquivo = "tipoArquivo=T&";
				} else {
					tipoArquivo = "tipoArquivo=G&";
				}

				// Parametro que identifica que o tipo de arquivo da rota está
				// sendo enviado
				String parametroArquivoBaixarRota = "arquivoRoteiro=";

				// 1 do tipo de resposta ok + tipo do arquivo + parametro
				// Arquivo baixar rota + tamanho do arquivo da rota
				response.setContentLength(1 + tipoArquivo.getBytes().length
						+ parametroArquivoBaixarRota.getBytes().length
						+ arq.length);

				out.write(RESPOSTA_OK);
				out.write(tipoArquivo.getBytes());
				out.write(parametroArquivoBaixarRota.getBytes());
				out.write(arq);

				out.flush();

				System.out.println("Fim: Baixando arquivo Mobile");
			} else {
				System.out.println("Erro ao Baixar arquivo Mobile");
				response.setContentLength(1);
				// OutputStream out = response.getOutputStream();
				out.write(RESPOSTA_ERRO);
				out.flush();
				// out.close();
			}

		} catch (Exception e) {

			System.out.println("Erro ao Baixar arquivo Mobile");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();

		}

		// ******************************************************************
		// else {
		// System.out.println("Erro versão Mobile");
		// response.setContentLength(RESPOSTA_ERRO_VERSAO);
		// OutputStream out = response.getOutputStream();
		// out.write(RESPOSTA_ERRO_VERSAO);
		// out.flush();
		// out.close();
		// }
		// ******************************************************************
	}

	/**
	 * [UC0811] Processar Requisições do Dispositivo Móvel.
	 * 
	 * Método que atualiza as movimentações dos leituristas.
	 * 
	 * @author Bruno Barros
	 * @date 10/11/2009
	 * 
	 * @throws IOException
	 * 
	 */
	public void atualizarMovimentacao(DataInputStream data,
			HttpServletResponse response, OutputStream out) throws IOException {
		InputStreamReader reader = new InputStreamReader(data);
		BufferedReader buffer = new BufferedReader(reader);

		try {
			RetornoAtualizarFaturamentoMovimentoCelularHelper retorno = Fachada
					.getInstancia().atualizarFaturamentoMovimentoCelular(
							buffer, false, false, null, null, buffer);
			// caso tenha gerado relatório de inconsistência,
			// então retorna que o imóvel não foi processado
			if (retorno.getRelatorioConsistenciaProcessamento() != null) {
				System.out
						.println("Erro ao atualizar faturamento movimento celular");
				response.setContentLength(1);
				out.write(RESPOSTA_ERRO);
				out.flush();
			} else if (retorno.getMensagemComunicacaoServidorCelular() != null) {
				System.out
						.println("Validação encontrada. Retornando para o celular.");

				response.setContentLength(1 + retorno
						.getMensagemComunicacaoServidorCelular().length());

				if (retorno.getIndicadorSucessoAtualizacao()) {
					out.write(RESPOSTA_OK);
				} else {
					out.write(RESPOSTA_ERRO);
				}

				out.write(retorno.getMensagemComunicacaoServidorCelular()
						.getBytes());
				out.flush();
			} else {
				response.setContentLength(1);
				out.write(RESPOSTA_OK);
				out.flush();
				System.out
						.println("Fim: atualizar faturamento movimento celular");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("Erro ao atualizar faturamento movimento celular");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}

	@SuppressWarnings("unchecked")
	public void finalizarMovimentacao(DataInputStream data, HttpServletResponse response, OutputStream out, int tipoFinalizacao) throws IOException {
		
		Fachada fachada = Fachada.getInstancia();
		long imei = data.readLong();
		Integer idRota = 0;

		InputStreamReader reader = new InputStreamReader(data);
		BufferedReader buffer = new BufferedReader(reader);
		BufferedReader bufferOriginal = new BufferedReader(reader);

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

			/**
			 * TODO - COSANPA Adicionando informacoes da rota para ser mostradas
			 * no log.
			 */
			System.out.println("Finalizando arquivo Mobile [Imei=" + imei
					+ ", Localidade: " + localidade + ", Setor: "
					+ setorComercial + ", Rota: " + codRota + "]");
			// Caso o tipo de finalização seja de arquivo com imóveis faltando,
			// pesquisamos quais ja chegaram
			if (indcFinalizacao == FINALIZAR_LEITURA_ARQUIVO_IMOVEIS_FALTANDO) {
				buffer = fachada
						.removerImoveisJaProcessadosBufferImpressaoSimultanea(
								idRota, buffer);
			}

			RetornoAtualizarFaturamentoMovimentoCelularHelper retorno = null;

			if (buffer != null) {
				retorno = fachada.atualizarFaturamentoMovimentoCelular(buffer,
						false, true, idRota, arquivoRetorno, bufferOriginal);
			}

			// caso não tenha gerado relatório de inconsistência,
			// então retorna que o imóvel não foi processado
			if (retorno != null
					&& (retorno.getRelatorioConsistenciaProcessamento() != null || retorno
							.getMensagemComunicacaoServidorCelular() != null)) {
				/**
				 * TODO - COSANPA Adicionando informacoes da rota para ser
				 * mostradas no log.
				 */
				System.out.println("Erro ao finalizar rota [Imei=" + imei
						+ ", Localidade: " + localidade + ", Setor: "
						+ setorComercial + ", Rota: " + codRota + "]");
				response.setContentLength(1);
				out.write(RESPOSTA_ERRO);
				out.flush();
			} else {

				// cria uma coleção de situações de transmissões para verificar
				// se é para finalizar o arquivo princial
				// (Caso todos arquivos divididos estejam com a situação de
				// Finalizado ou Finalizado Incompleto então finaliza
				// o arquivo principal para finalizado ou finalizado
				// imcompleto).
				Integer[] idsSituacaoTransmissao = new Integer[1];
				idsSituacaoTransmissao[0] = SituacaoTransmissaoLeitura.TRANSMITIDO;

				// Atualiza a situação do arquivo texto de em campo para
				// finalizado
				// Alteração feita por Sávio Luiz
				// Data:05/04/2010
				/**
				 * TODO - COSANPA Alteracao para finalizar a rota dividida por
				 * sequencial da rota, e não pelo IMEI
				 */
				if (indcFinalizacao == FINALIZAR_LEITURA
						|| indcFinalizacao == FINALIZAR_LEITURA_ARQUIVO_IMOVEIS_FALTANDO) {

					Integer diferenca = fachada
							.pesquisarDiferencaQuantidadeMovimentoContaPrefaturadaArquivoTextoRoteiroEmpresa(
									idRota, anoMesFaturamento);

					if (!fachada.isRotaDividida(idRota, anoMesFaturamento)) {
						if (diferenca != 0) {
							/**
							 * TODO - COSANPA Adicionando informacoes da rota
							 * para ser mostradas no log.
							 */
							String msg = "Validação encontrada. Retornando para o celular";
							msg += "[Diferença de imoveis: " + diferenca;
							msg += ", Imei=" + imei + ", Localidade: "
									+ localidade + ", Setor: " + setorComercial
									+ ", Rota: " + codRota + "]";

							System.out.println(msg);

							String mensagem = "mensagem=A quantidade de imóveis enviados não corresponde ao esperado";
							response.setContentLength(1 + mensagem.getBytes().length);
							out.write(RESPOSTA_ERRO);
							out.write(mensagem.getBytes());
							out.flush();
						} else {
							fachada.atualizarArquivoTextoEnviadoPorRota(idRota,
									SituacaoTransmissaoLeitura.EM_CAMPO,
									SituacaoTransmissaoLeitura.TRANSMITIDO);
						}
					} else {
						// caso exista arquivo dividido, então atualiza o
						// arquivo dividido pelo imei
						// fachada.atualizarArquivoTextoEnviado(imei,
						// SituacaoTransmissaoLeitura.EM_CAMPO,
						// SituacaoTransmissaoLeitura.TRANSMITIDO);
						// ALTERAR O METODO PARA FILTRAR POR ROTA/NUMERO ARQUIVO
						// E N PELO IMEI
						fachada.atualizarArquivoTextoDividido(idRota,
								anoMesFaturamento, numeroSequenciaArquivo,
								SituacaoTransmissaoLeitura.EM_CAMPO,
								SituacaoTransmissaoLeitura.TRANSMITIDO);

						if (diferenca != 0) {
							/**
							 * TODO - COSANPA Adicionando informacoes da rota
							 * para ser mostradas no log.
							 */
							String msg = "Validação encontrada. Retornando para o celular";
							msg += "[Diferença de imoveis: " + diferenca;
							msg += ", Imei=" + imei + ", Localidade: "
									+ localidade + ", Setor: " + setorComercial
									+ ", Rota: " + codRota + "]";

							System.out.println(msg);

							String mensagem = "mensagem=A quantidade de imóveis enviados não corresponde ao esperado";
							response.setContentLength(1 + mensagem.getBytes().length);
							out.write(RESPOSTA_ERRO);
							out.write(mensagem.getBytes());
							out.flush();
						} else {
							// caso exista arquivo dividido, então atualiza o
							// arquivo dividido pelo imei
							fachada.atualizarArquivoTextoEnviado(imei,
									SituacaoTransmissaoLeitura.EM_CAMPO,
									SituacaoTransmissaoLeitura.TRANSMITIDO);
							// verifica se todas as rotas divididas estão com a
							// situação de FINALIZADO
							if (!fachada
									.verificarExistenciaArquivosDivididosSituacaoDiferente(
											idRota, anoMesFaturamento,
											idsSituacaoTransmissao)) {
								// atualiza o arquivo principal para a situação
								// de finalizado
								fachada.atualizarArquivoTextoEnviadoPorRota(
										idRota,
										SituacaoTransmissaoLeitura.EM_CAMPO,
										SituacaoTransmissaoLeitura.TRANSMITIDO);
							} else {
								// verifica se todas as rotas divididas estão
								// com a situação de FINALIZADO ou finalizado
								// imcompleto.
								idsSituacaoTransmissao = new Integer[2];
								idsSituacaoTransmissao[0] = SituacaoTransmissaoLeitura.TRANSMITIDO;
								idsSituacaoTransmissao[1] = SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO;
								if (!fachada
										.verificarExistenciaArquivosDivididosSituacaoDiferente(
												idRota, anoMesFaturamento,
												idsSituacaoTransmissao)) {
									// atualiza o arquivo principal para a
									// situação de finalizado
									fachada.atualizarArquivoTextoEnviadoPorRota(
											idRota,
											SituacaoTransmissaoLeitura.EM_CAMPO,
											SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO);
								}
							}
						}

					}

					response.setContentLength(1);
					out.write(RESPOSTA_OK);
					out.flush();

					/**
					 * TODO - COSANPA Adicionando informacoes da rota para ser
					 * mostradas no log.
					 */
					System.out.println("Fim: finalizar rota [Localidade: "
							+ localidade + ", Setor: " + setorComercial
							+ ", Rota: " + codRota + "]");

				} else if (indcFinalizacao == FINALIZAR_LEITURA_INCOMPLETA) {

					if (!fachada.isRotaDividida(idRota, anoMesFaturamento)) {
						fachada.atualizarArquivoTextoEnviadoPorRota(
								idRota,
								SituacaoTransmissaoLeitura.EM_CAMPO,
								SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO);
					} else {
						// caso exista arquivo dividido, então atualiza o
						// arquivo dividido pelo imei
						fachada.atualizarArquivoTextoEnviado(
								imei,
								SituacaoTransmissaoLeitura.EM_CAMPO,
								SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO);
						// verifica se todas as rotas divididas estão com a
						// situação de FINALIZADO
						if (!fachada
								.verificarExistenciaArquivosDivididosSituacaoDiferente(
										idRota, anoMesFaturamento,
										idsSituacaoTransmissao)) {
							// atualiza o arquivo principal para a situação de
							// finalizado
							fachada.atualizarArquivoTextoEnviadoPorRota(idRota,
									SituacaoTransmissaoLeitura.EM_CAMPO,
									SituacaoTransmissaoLeitura.TRANSMITIDO);
						} else {
							// verifica se todas as rotas divididas estão com a
							// situação de FINALIZADO ou finalizado imcompleto.
							idsSituacaoTransmissao = new Integer[2];
							idsSituacaoTransmissao[0] = SituacaoTransmissaoLeitura.TRANSMITIDO;
							idsSituacaoTransmissao[1] = SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO;
							if (!fachada
									.verificarExistenciaArquivosDivididosSituacaoDiferente(
											idRota, anoMesFaturamento,
											idsSituacaoTransmissao)) {
								// atualiza o arquivo principal para a situação
								// de finalizado
								fachada.atualizarArquivoTextoEnviadoPorRota(
										idRota,
										SituacaoTransmissaoLeitura.EM_CAMPO,
										SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO);
							}
						}
					}
					response.setContentLength(1);
					out.write(RESPOSTA_OK);
					out.flush();

					/**
					 * TODO - COSANPA Adicionando informacoes da rota para ser
					 * mostradas no log.
					 */
					System.out.println("Fim: finalizar rota [Localidade: "
							+ localidade + ", Setor: " + setorComercial
							+ ", Rota: " + codRota);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();

			/**
			 * TODO - COSANPA Adicionando informacoes da rota para ser mostradas
			 * no log.
			 */
			System.out
					.println("Erro ao atualizar faturamento movimento celular [Localidade: "
							+ idLocalidade
							+ ", Setor: "
							+ setorComercial
							+ ", Rota: " + codRota);

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
	public void confirmacaoArquivoRecebido(DataInputStream data,
			HttpServletResponse response, OutputStream out) throws IOException {
		Fachada fachada = Fachada.getInstancia();
		long imei = data.readLong();

		System.out.println("Confirmando Recebimento do arquivo Mobile imei = "
				+ imei);

		try {

			// Atualiza a situação do arquivo texto de "em campo" para
			// "finalizado e não transmitido"
			// Alteração feita por Sávio Luiz
			// Data:05/04/2010
			fachada.atualizarArquivoTextoEnviado(imei,
					SituacaoTransmissaoLeitura.EM_CAMPO,
					SituacaoTransmissaoLeitura.FINALIZADO_NAO_TRANSMITIDO);

			// Atualiza a situação do arquivo texto de "liberado" para
			// "em campo"
			// Alteração feita por Sávio Luiz
			// Data:05/04/2010
			fachada.atualizarArquivoTextoEnviado(imei,
					SituacaoTransmissaoLeitura.LIBERADO,
					SituacaoTransmissaoLeitura.EM_CAMPO);

			// Atualiza a situação do arquivo texto de "disponível" para
			// "liberado"
			// Alteração feita por Sávio Luiz
			// Data:05/04/2010
			if (fachada.liberaProximoArquivoImpressaoSimultaneaOnLine()) {
				fachada.atualizarArquivoTextoMenorSequencialLeitura(imei,
						SituacaoTransmissaoLeitura.DISPONIVEL,
						SituacaoTransmissaoLeitura.LIBERADO,
						ServicoTipoCelular.IMPRESSAO_SIMULTANEA);
			}

			response.setContentLength(1);
			out.write(RESPOSTA_OK);
			out.flush();
			System.out.println("Fim: atualizar faturamento movimento celular");
		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("Erro ao atualizar faturamento movimento celular");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}

	/**
	 * [UC0811] Processar Requisições do Dispositivo Móvel Impressao Simultanea.
	 * 
	 * Método que baixa a nova versão do mobile para o celular
	 * 
	 * @author Bruno Barros
	 * @date 08/06/2010
	 * 
	 * @param
	 * @param response
	 * @throws IOException
	 */
	public void baixarNovaVersaoJad(DataInputStream data,
			HttpServletResponse response, OutputStream out) throws IOException {
		Fachada fachada = Fachada.getInstancia();

		try {
			byte[] arq = fachada.baixarNovaVersaoJad();

			if (arq != null) {
				String parametroTipoArquivo = "jad=";

				System.out
						.println("Inicio : Baixando NOVA VERSÃO, ARQUIVO JAD Mobile");
				response.setContentLength(arq.length
						+ parametroTipoArquivo.getBytes().length + 1);

				out.write(RESPOSTA_OK);
				out.write(parametroTipoArquivo.getBytes());

				out.write(arq);
				out.flush();

				System.out
						.println("Fim: Baixando NOVA VERSÃO, ARQUIVO JAD Mobile");
			} else {
				System.out.println("Erro ao Baixar arquivo Mobile");
				response.setContentLength(1);

				out.write(RESPOSTA_ERRO);
				out.flush();
			}

		} catch (Exception e) {

			System.out.println("Erro ao Baixar arquivo Mobile");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();

		}
	}

	/**
	 * [UC0811] Processar Requisições do Dispositivo Móvel Impressao Simultanea.
	 * 
	 * Método que baixa a nova versão do mobile para o celular
	 * 
	 * @author Bruno Barros
	 * @date 08/06/2010
	 * 
	 * @param
	 * @param response
	 * @throws IOException
	 */
	public void baixarNovaVersaoJar(DataInputStream data,
			HttpServletResponse response, OutputStream out) throws IOException {
		Fachada fachada = Fachada.getInstancia();

		try {
			byte[] arq = fachada.baixarNovaVersaoJar();

			if (arq != null) {

				String parametroTipoArquivo = "jar=";

				System.out
						.println("Inicio : Baixando NOVA VERSÃO, ARQUIVO JAR Mobile");
				response.setContentLength(arq.length
						+ parametroTipoArquivo.getBytes().length + 1);

				out.write(RESPOSTA_OK);
				out.write(parametroTipoArquivo.getBytes());

				out.write(arq);
				out.flush();

				System.out
						.println("Fim: Baixando NOVA VERSÃO, ARQUIVO JAR Mobile");
			} else {
				System.out.println("Erro ao Baixar arquivo Mobile");
				response.setContentLength(1);

				out.write(RESPOSTA_ERRO);
				out.flush();
			}

		} catch (Exception e) {

			System.out.println("Erro ao Baixar arquivo Mobile");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();

		}
	}

}
