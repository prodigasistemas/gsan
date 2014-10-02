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
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RetornarArquivoZipLeituraAction extends GcomAction {

	/**
	 * Este caso de uso permite Retornar um Arquivo Txt Leitura
	 * 
	 * [UC0629] Retornar Arquivo Txt Leitura
	 * 
	 * 
	 * @author R�mulo Aur�lio
	 * @date 13/10/2008
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

		String[] idsRegistrosRemocao = form.getIdsRegistros();

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoArquivoTextoRoteiroEmpresa = fachada
				.pesquisarArquivosTextoRoteiroEmpresaCompletoParaArquivoZip(idsRegistrosRemocao);

		Iterator colecaoArquivoTextoRoteiroEmpresaIterator = (Iterator) colecaoArquivoTextoRoteiroEmpresa
				.iterator();

		ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa = null;

		ZipOutputStream zos = null;

		// filtro para pesquisar os arquivos dividido caso a rota seja dividida.
		FiltroArquivoTextoRoteiroEmpresaDivisao filtroArquivoTextoRoteiroEmpresaDivisao = null;

		if (colecaoArquivoTextoRoteiroEmpresa != null
				&& !colecaoArquivoTextoRoteiroEmpresa.isEmpty()) {

			if (colecaoArquivoTextoRoteiroEmpresaIterator.hasNext()) {
				arquivoTextoRoteiroEmpresa = (ArquivoTextoRoteiroEmpresa) colecaoArquivoTextoRoteiroEmpresaIterator
						.next();
			}

			// Informa��es da rota
			// ----------------------------------------------------------
			String localidade = arquivoTextoRoteiroEmpresa.getLocalidade()
					.getId() + "";
			String setorComercial = arquivoTextoRoteiroEmpresa
					.getCodigoSetorComercial1() + "";
			String codigoRota = arquivoTextoRoteiroEmpresa.getRota()
					.getCodigo() + "";
			// -----------------------------------------------------------------------------/

			String nomeArquivo = "Rol_Grupo";

			String grupo = "";

			// Verifica se o grupo de faturamento est� selecionado e
			// seta o grupo atrav�s do primeiro arquivo pesquisado.
			if (!form.getGrupoFaturamentoID().equals("-1")) {
				grupo = "_"
						+ arquivoTextoRoteiroEmpresa.getFaturamentoGrupo()
								.getId();
			}

			String nomeArquivoZip = nomeArquivo + grupo + ".zip";

			File compactadoTipo = new File(nomeArquivoZip);

			try {
				zos = new ZipOutputStream(new FileOutputStream(compactadoTipo));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

			colecaoArquivoTextoRoteiroEmpresaIterator = (Iterator) colecaoArquivoTextoRoteiroEmpresa
					.iterator();

			try {
				while (colecaoArquivoTextoRoteiroEmpresaIterator.hasNext()) {

					arquivoTextoRoteiroEmpresa = (ArquivoTextoRoteiroEmpresa) colecaoArquivoTextoRoteiroEmpresaIterator
							.next();

					if (arquivoTextoRoteiroEmpresa
							.getSituacaoTransmissaoLeitura().getId()
							.equals(SituacaoTransmissaoLeitura.LIBERADO)
							|| arquivoTextoRoteiroEmpresa
									.getSituacaoTransmissaoLeitura()
									.getId()
									.equals(SituacaoTransmissaoLeitura.EM_CAMPO)) {

						// Id da rota do arquivo principal
						Integer idRota = arquivoTextoRoteiroEmpresa.getRota()
								.getId();
						// Ano e m�s de faturamento da rota
						Integer anoMesFaturamento = arquivoTextoRoteiroEmpresa
								.getAnoMesReferencia();

						// Array contendo situa��o para atualizar o arquivo
						// principal
						// quando a rota for dividida
						Integer[] idsSituacaoTransmissao = new Integer[1];
						idsSituacaoTransmissao[0] = SituacaoTransmissaoLeitura.EM_CAMPO;

						/*
						 *
						 * 
						 * Verifica se a rota � dividida para adicionar ao zip
						 * os arquivos divididos.
						 * 
						 * @author Felipe Santos
						 * 
						 * @date 19/07/2011
						 */
						if (fachada.isRotaDividida(idRota, anoMesFaturamento)) {
							filtroArquivoTextoRoteiroEmpresaDivisao = new FiltroArquivoTextoRoteiroEmpresaDivisao();

							filtroArquivoTextoRoteiroEmpresaDivisao
									.adicionarParametro(new ParametroSimples(
											FiltroArquivoTextoRoteiroEmpresaDivisao.ARQUIVO_TEXTO_ROTEIRO_EMPRESA_ID,
											arquivoTextoRoteiroEmpresa.getId()));

							// Array para ordenar a cole��o por Numero de
							// Sequencia
							// do arquivo e Situacao de Transmissao
							String[] orderby = new String[] {
									FiltroArquivoTextoRoteiroEmpresaDivisao.NUMERO_SEQUENCIA_LEITURA,
									FiltroArquivoTextoRoteiroEmpresaDivisao.SITUACAO_TRANS_LEITURA };

							filtroArquivoTextoRoteiroEmpresaDivisao
									.adicionarCaminhoParaCarregamentoEntidade(FiltroArquivoTextoRoteiroEmpresaDivisao.ARQUIVO_TEXTO_ROTEIRO_EMPRESA);
							filtroArquivoTextoRoteiroEmpresaDivisao
									.adicionarCaminhoParaCarregamentoEntidade(FiltroArquivoTextoRoteiroEmpresa.SITUACAO_TRANS_LEITURA);

							// Seta no filtro o crit�rio para ordena��o da
							// cole��o
							filtroArquivoTextoRoteiroEmpresaDivisao
									.setCampoOrderBy(orderby);

							// Cole��o contendo os arquivos divididos da rota
							Collection colecaoArquivoTextoRoteiroEmpresaDivisao = fachada
									.pesquisar(
											filtroArquivoTextoRoteiroEmpresaDivisao,
											ArquivoTextoRoteiroEmpresaDivisao.class
													.getName());

							Iterator colecaoArquivoTextoRoteiroEmpresaDivisaoIterator = (Iterator) colecaoArquivoTextoRoteiroEmpresaDivisao
									.iterator();

							/*
							 * Para cada arquivo dividido, descomprime o arquivo
							 * e adiciona o n�mnero de sequ�ncia do arquivo em
							 * seu nome. Em seguida, comprime o arquivo
							 * novamente e adiciona-o ao zip.
							 */
							while (colecaoArquivoTextoRoteiroEmpresaDivisaoIterator
									.hasNext()) {
								ArquivoTextoRoteiroEmpresaDivisao arquivoTextoRoteiroEmpresaDivisao = (ArquivoTextoRoteiroEmpresaDivisao) colecaoArquivoTextoRoteiroEmpresaDivisaoIterator
										.next();

								// Arquivo dividido original
								File arquivoOriginal = new File(
										arquivoTextoRoteiroEmpresaDivisao
												.getNomeArquivo());

								FileOutputStream out = new FileOutputStream(
										arquivoOriginal.getAbsolutePath());

								out.write(arquivoTextoRoteiroEmpresaDivisao
										.getArquivoTexto());

								out.close();

								// Descomprime o arquivo dividido original
								File arquivoDescomprimido = ZipUtil
										.descomprimirGzip(arquivoOriginal);

								// Renomeia o arquivo descomprimido com sufixo
								// sendo o n�mero de sequ�ncia do arquivo
								// dividido
								File arquivoDescomprimidoRenomeado = new File(
										arquivoDescomprimido.getAbsolutePath()
												+ "-"
												+ arquivoTextoRoteiroEmpresaDivisao
														.getNumeroSequenciaArquivo());

								arquivoDescomprimido
										.renameTo(arquivoDescomprimidoRenomeado);

								// Comprime o arquivo renomeado
								File arquivoComprimido = ZipUtil
										.comprimirGzip(arquivoDescomprimidoRenomeado);

								// Adiciona no arquivo zip final
								ZipUtil.adicionarArquivo(zos, arquivoComprimido);

								arquivoDescomprimidoRenomeado.delete();
								arquivoComprimido.delete();

								zos.closeEntry();

								// Atualiza situa��o do arquivo dividido para EM
								// CAMPO
								fachada.atualizarArquivoTextoDivisaoEnviado(
										arquivoTextoRoteiroEmpresaDivisao
												.getId(),
										SituacaoTransmissaoLeitura.EM_CAMPO);
							}

							fachada.atualizarArquivoTextoEnviado(
									arquivoTextoRoteiroEmpresa.getId(),
									SituacaoTransmissaoLeitura.EM_CAMPO);

						} else {
							// Arquivo original
							File arquivoOriginal = new File(
									arquivoTextoRoteiroEmpresa.getNomeArquivo());

							FileOutputStream out = new FileOutputStream(
									arquivoOriginal.getAbsolutePath());

							/*
							 * Par�metro do m�todo out.write()
							 * modificado. Adiciona diretamente o array de bytes
							 * -> arquivoTextoRoteiroEmpresa.getArquivoTexto()
							 */
							out.write(arquivoTextoRoteiroEmpresa
									.getArquivoTexto());

							out.close();

							// Adiciona no arquivo zip final
							ZipUtil.adicionarArquivo(zos, arquivoOriginal);

							arquivoOriginal.delete();

							zos.closeEntry();

							// Atualiza situa��o do arquivo para EM CAMPO
							fachada.atualizarArquivoTextoEnviado(
									arquivoTextoRoteiroEmpresa.getId(),
									SituacaoTransmissaoLeitura.EM_CAMPO);
						}
					} else {
						throw new ActionServletException(
								"atencao.arquivo_nao_pode_baixar_rota_invalida",
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

				// Finaliza o arquivo zip final
				sos.write(IoUtil.getBytesFromFile(arquivoZip));

				sos.flush();

				sos.close();

				arquivoZip.delete();

			} catch (IOException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao gerar o arquivo zip", e);
			} catch (ActionServletException ex2) {
				throw ex2;
			}
		}

		try {
			zos.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao fechar o zip do relatorio", e);
		}

		return retorno;
	}

}
