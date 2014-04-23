package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.ArquivoTextoRoteiroEmpresa;
import gcom.micromedicao.ArquivoTextoRoteiroEmpresaDivisao;
import gcom.micromedicao.FiltroArquivoTextoRoteiroEmpresa;
import gcom.micromedicao.FiltroArquivoTextoRoteiroEmpresaDivisao;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.tarefa.TarefaException;
import gcom.util.IoUtil;
import gcom.util.ZipUtil;
import gcom.util.filtro.ParametroSimples;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RetornarArquivoZipLeituraImoveisPFAction extends GcomAction {

	/**
	 * Este caso de uso permite Retornar um Arquivo Txt Leitura contendo apenas
	 * Imóveis com Conta em Situação PF
	 * 
	 * [UC0629] Retornar Arquivo Txt Leitura
	 * 
	 * 
	 * @author Felipe Santos (Adaptação da Classe
	 *         RetornarArquivoZipLeituraAction feita por Rômulo Aurélio)
	 * @date 20/05/2011
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		ConsultarArquivoTextoLeituraActionForm form = (ConsultarArquivoTextoLeituraActionForm) actionForm;

		String[] idsRegistros = form.getIdsRegistros();

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoArquivoTextoRoteiroEmpresa = fachada.pesquisarArquivosTextoRoteiroEmpresaCompletoParaArquivoZip(idsRegistros);
		
		Iterator colecaoArquivoTextoRoteiroEmpresaIterator = (Iterator) colecaoArquivoTextoRoteiroEmpresa.iterator();
		
		ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa = null;
		
		ZipOutputStream zos = null;
		
		// filtro para pesquisar os arquivos dividido caso a rota seja dividida.
		FiltroArquivoTextoRoteiroEmpresaDivisao filtroArquivoTextoRoteiroEmpresaDivisao = null;
		
		if (colecaoArquivoTextoRoteiroEmpresa != null
				&& !colecaoArquivoTextoRoteiroEmpresa.isEmpty()) {
			
			if (colecaoArquivoTextoRoteiroEmpresaIterator.hasNext()) {
				arquivoTextoRoteiroEmpresa = (ArquivoTextoRoteiroEmpresa) colecaoArquivoTextoRoteiroEmpresaIterator.next();
			}
			
			// Informações da rota ----------------------------------------------------------
			String localidade = arquivoTextoRoteiroEmpresa.getLocalidade().getId()+"";
			String setorComercial = arquivoTextoRoteiroEmpresa.getCodigoSetorComercial1()+"";
			String codigoRota = arquivoTextoRoteiroEmpresa.getRota().getCodigo()+"";
			// -----------------------------------------------------------------------------/
			
			String nomeArquivo = "Rol_Grupo";
			
			String grupo = "";
			
			// Verifica se o grupo de faturamento está selecionado e 
			// seta o grupo através do primeiro arquivo pesquisado.
			if (!form.getGrupoFaturamentoID().equals("-1")) {				
				grupo = "_"+arquivoTextoRoteiroEmpresa.getFaturamentoGrupo().getId();
			} 
			
			String nomeArquivoZip = nomeArquivo + grupo + "_Imoveis_PF" + ".zip";		

			File compactadoTipo = new File(nomeArquivoZip);		

			try {
				zos = new ZipOutputStream(new FileOutputStream(compactadoTipo));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

			colecaoArquivoTextoRoteiroEmpresaIterator = (Iterator) colecaoArquivoTextoRoteiroEmpresa.iterator();

			try {
				while (colecaoArquivoTextoRoteiroEmpresaIterator.hasNext()) {

					arquivoTextoRoteiroEmpresa = (ArquivoTextoRoteiroEmpresa) colecaoArquivoTextoRoteiroEmpresaIterator.next();
					
					if (arquivoTextoRoteiroEmpresa.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.LIBERADO) || 
							arquivoTextoRoteiroEmpresa.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.EM_CAMPO)) {
						
						// id da rota do arquivo principal
						Integer idRota = arquivoTextoRoteiroEmpresa.getRota().getId();
						
						// ano e mês de faturamento da rota
						Integer anoMesFaturamento = arquivoTextoRoteiroEmpresa.getAnoMesReferencia();
						
						// quantidade de imóveis com contas PF
						Integer diferenca = null;
						
						try {
							diferenca = fachada.pesquisarDiferencaQuantidadeMovimentoContaPrefaturadaArquivoTextoRoteiroEmpresa(idRota, 
									anoMesFaturamento);
							
							if (diferenca == null) {
								diferenca = 0;
							} else if (diferenca < 0) {
								diferenca *= -1;
							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						
						int quantidadeTotalImoveis = arquivoTextoRoteiroEmpresa.getQuantidadeImovel().intValue();
						
						int leiturasRealizadas = quantidadeTotalImoveis - diferenca.intValue();

						// A rota apenas será baixada caso a quantidade de leituras realizadas
						// seja maior que zero e menor que a quantidade total de imóveis da rota.
						if (leiturasRealizadas > 0 && leiturasRealizadas < arquivoTextoRoteiroEmpresa.getQuantidadeImovel()) {
							
							// Quantidades de imóveis transmitidos da rota
							List<Integer> listaImoveisFaltandoTransmitir = fachada.obterImoveisNaoEnviadosMovimentoContaPreFaturada(idRota, anoMesFaturamento);
							
							/*
							 * TODO - COSANPA
							 * 
							 * Verifica se a rota é dividida para adicionar ao zip os
							 * arquivos divididos. Caso contrário, adiciona o arquivo
							 * normalmente.
							 * 
							 * @author Felipe Santos
							 * @date 20/07/2011
							 */
							if (fachada.isRotaDividida(idRota, anoMesFaturamento)) {
								filtroArquivoTextoRoteiroEmpresaDivisao = new FiltroArquivoTextoRoteiroEmpresaDivisao();
		
								filtroArquivoTextoRoteiroEmpresaDivisao.adicionarParametro(new ParametroSimples(
										FiltroArquivoTextoRoteiroEmpresaDivisao.ARQUIVO_TEXTO_ROTEIRO_EMPRESA_ID,
										arquivoTextoRoteiroEmpresa.getId()));
		
								// Array para ordenar a coleção por Numero de Sequencia
								// do arquivo e Situacao de Transmissao
								String[] orderby = new String[] {
										FiltroArquivoTextoRoteiroEmpresaDivisao.NUMERO_SEQUENCIA_LEITURA,
										FiltroArquivoTextoRoteiroEmpresaDivisao.SITUACAO_TRANS_LEITURA };
		
								filtroArquivoTextoRoteiroEmpresaDivisao.adicionarCaminhoParaCarregamentoEntidade(
										FiltroArquivoTextoRoteiroEmpresaDivisao.ARQUIVO_TEXTO_ROTEIRO_EMPRESA);
								filtroArquivoTextoRoteiroEmpresaDivisao.adicionarCaminhoParaCarregamentoEntidade(
										FiltroArquivoTextoRoteiroEmpresa.SITUACAO_TRANS_LEITURA);
		
								// Seta no filtro o critério para ordenação da coleção
								filtroArquivoTextoRoteiroEmpresaDivisao.setCampoOrderBy(orderby);
		
								// Coleção contendo os arquivos divididos da rota
								Collection colecaoArquivoTextoRoteiroEmpresaDivisao = fachada.pesquisar(filtroArquivoTextoRoteiroEmpresaDivisao,
										ArquivoTextoRoteiroEmpresaDivisao.class.getName());
		
								Iterator colecaoArquivoTextoRoteiroEmpresaDivisaoIterator = (Iterator) colecaoArquivoTextoRoteiroEmpresaDivisao.iterator();
		
								/*
								 * Para cada arquivo dividido, descomprime o arquivo e
								 * adiciona o númnero de sequência do arquivo em seu
								 * nome. Em seguida, comprime o arquivo novamente e
								 * adiciona-o ao zip.
								 */
								while (colecaoArquivoTextoRoteiroEmpresaDivisaoIterator.hasNext()) {
									ArquivoTextoRoteiroEmpresaDivisao arquivoTextoRoteiroEmpresaDivisao = (ArquivoTextoRoteiroEmpresaDivisao) 
											colecaoArquivoTextoRoteiroEmpresaDivisaoIterator.next();
		
									// Arquivo dividido original
									File arquivoOriginal = new File(arquivoTextoRoteiroEmpresaDivisao.getNomeArquivo());
		
									FileOutputStream out = new FileOutputStream(arquivoOriginal.getAbsolutePath());
		
									out.write(arquivoTextoRoteiroEmpresaDivisao.getArquivoTexto());
		
									out.close();
		
									// Descomprime o arquivo dividido original
									File arquivoDescomprimido = ZipUtil.descomprimirGzip(arquivoOriginal);
		
									// Renomeia o arquivo descomprimido com sufixo
									// sendo o número de sequência do arquivo dividido
									File arquivoDescomprimidoRenomeado = new File(arquivoDescomprimido.getAbsolutePath() + "-"
											+ arquivoTextoRoteiroEmpresaDivisao.getNumeroSequenciaArquivo());
		
									arquivoDescomprimido.renameTo(arquivoDescomprimidoRenomeado);
		
									// Gera novo arquivo apenas com imóveis com conta PF
									IoUtil.gerarArquivoTxtImoveisFaltandoTransmitir(arquivoDescomprimidoRenomeado,
											listaImoveisFaltandoTransmitir);
									
									// Quantidade de Imóveis com situação PF
									int quantidadeImoveisPF = IoUtil.verificaQuantidadeImoveis(arquivoDescomprimidoRenomeado);
		
									// Comprime o arquivo renomeado
									File arquivoComprimido = ZipUtil.comprimirGzip(arquivoDescomprimidoRenomeado);
										
									if (quantidadeImoveisPF > 0) {
										// Adiciona no arquivo zip final
										ZipUtil.adicionarArquivo(zos, arquivoComprimido);
									}
		
									arquivoDescomprimido.delete();
									arquivoDescomprimidoRenomeado.delete();
									arquivoComprimido.delete();
		
									zos.closeEntry();
		
									if (arquivoTextoRoteiroEmpresaDivisao.getSituacaoTransmissaoLeitura().getId().equals(
											SituacaoTransmissaoLeitura.DISPONIVEL) || 
											arquivoTextoRoteiroEmpresaDivisao.getSituacaoTransmissaoLeitura().getId().equals(
													SituacaoTransmissaoLeitura.LIBERADO)) {
										// Atualiza situação do arquivo dividido para EM CAMPO
										fachada.atualizarArquivoTextoDivisaoEnviado(arquivoTextoRoteiroEmpresaDivisao.getId(),
												SituacaoTransmissaoLeitura.EM_CAMPO);
									}
								}
		
								fachada.atualizarArquivoTextoEnviado(arquivoTextoRoteiroEmpresa.getId(),
										SituacaoTransmissaoLeitura.EM_CAMPO);
		
							} else {
								// Arquivo original
								File arquivoOriginal = new File(arquivoTextoRoteiroEmpresa.getNomeArquivo());
		
								FileOutputStream out = new FileOutputStream(arquivoOriginal.getAbsolutePath());
		
								out.write(arquivoTextoRoteiroEmpresa.getArquivoTexto());
		
								out.close();
		
								// Descomprime arquivo original
								File arquivoDescomprimido = ZipUtil.descomprimirGzip(arquivoOriginal);
		
								// Gera novo arquivo apenas com imóveis com conta PF
								IoUtil.gerarArquivoTxtImoveisFaltandoTransmitir(arquivoDescomprimido, listaImoveisFaltandoTransmitir);
		
								// Comprime o novo arquivo
								File arquivoComprimido = ZipUtil.comprimirGzip(arquivoDescomprimido);
								
								// Adiciona no arquivo zip final
								ZipUtil.adicionarArquivo(zos, arquivoComprimido);
		
								arquivoDescomprimido.delete();
								arquivoComprimido.delete();
		
								zos.closeEntry();
		
								// Atualiza situação para EM CAMPO
								fachada.atualizarArquivoTextoEnviado(arquivoTextoRoteiroEmpresa.getId(),
										SituacaoTransmissaoLeitura.EM_CAMPO);
							}						
						} else {
							throw new ActionServletException("atencao.arquivo_nao_pode_baixar_rota_invalida",
									localidade, setorComercial, codigoRota);
						}
					} else {
						throw new ActionServletException("atencao.arquivo_nao_pode_baixar_rota_invalida",
								localidade, setorComercial, codigoRota);
					}
				}

				zos.flush();

				zos.close();

				httpServletResponse.setContentType("application/zip");
				httpServletResponse.addHeader("Content-Disposition",
						"attachment; filename=" + nomeArquivoZip);

				File arquivoZip = new File(nomeArquivoZip);

				ServletOutputStream sos = httpServletResponse.getOutputStream();

				sos.write(IoUtil.getBytesFromFile(arquivoZip));

				sos.flush();

				sos.close();

				arquivoZip.delete();

			} catch (IOException ex) {
				ex.printStackTrace();
				throw new TarefaException("Erro ao gerar o arquivo zip", ex);
			} catch (ActionServletException ex2) {
				throw ex2;
			}
		}

		try {
			zos.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao fechar o zip do relatório", e);
		}

		return retorno;
	}
}
