package gcom.gui.arrecadacao;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.ArrecadadorContrato;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.arrecadacao.FiltroArrecadadorContrato;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pre- processamento para registrar leituras e anormalidades
 * 
 * @author S�vio Luiz
 */
public class ExibirRegistrarMovimentoArredadadoresAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("registrarMovimentoArrecacadores");

		// RegistrarMovimentoArredadadoresActionForm form =
		// (RegistrarMovimentoArredadadoresActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		sessao.setAttribute("colecaoArrecadadorContrato", new ArrayList());
		// Parte que trata do c�digo quando o usu�rio tecla enter
		// caso seja o id do arrecadador

		String codigoAgenteDigitadoEnterArrecadador = null;

		String idTipoMovimento = null;

		if (httpServletRequest.getParameter("objetoConsulta") != null) {

			try {

				DiskFileUpload upload = new DiskFileUpload();

				// Parse the request
				List items = upload.parseRequest(httpServletRequest);

				if (items != null) {
					FileItem item = null;

					// pega uma lista de itens do form
					Iterator iter = items.iterator();
					while (iter.hasNext()) {

						item = (FileItem) iter.next();

						if (item.getFieldName().equals("codigoAgente")) {
							codigoAgenteDigitadoEnterArrecadador = item
									.getString();
						}

						if (item.getFieldName().equals("idTipoMovimento")) {
							idTipoMovimento = item.getString();
						}

					}
				}
			} catch (FileUploadException e) {
				throw new ActionServletException("erro.sistema", e);
			}

			httpServletRequest.getParameter("caminho");

			// Verifica se o c�digo foi digitado
			if (codigoAgenteDigitadoEnterArrecadador != null
					&& !codigoAgenteDigitadoEnterArrecadador.trim().equals("")
					&& Integer.parseInt(codigoAgenteDigitadoEnterArrecadador) > 0) {

				FiltroArrecadador filtroArrecadador = new FiltroArrecadador();

				filtroArrecadador.adicionarParametro(new ParametroSimples(
						FiltroArrecadador.CODIGO_AGENTE,
						codigoAgenteDigitadoEnterArrecadador));

				filtroArrecadador
						.adicionarCaminhoParaCarregamentoEntidade("cliente");

				Collection arrecadadorEncontrado = this.getFachada().pesquisar(
						filtroArrecadador, Arrecadador.class.getName());

				if (arrecadadorEncontrado != null
						&& !arrecadadorEncontrado.isEmpty()) {

					// O arrecadador foi encontrado
					httpServletRequest
							.setAttribute(
									"parametroidArrecadador",
									""
											+ ((Arrecadador) ((List) arrecadadorEncontrado)
													.get(0)).getCodigoAgente());

					httpServletRequest
							.setAttribute(
									"parametroNomeArrecadador",
									""
											+ ((Arrecadador) ((List) arrecadadorEncontrado)
													.get(0)).getCliente()
													.getNome());

					httpServletRequest.setAttribute(
							"idArrecadadorNaoEncontrado", "true");
					httpServletRequest.setAttribute("nomeCampo",
							"idTipoMovimento");

					if (idTipoMovimento != null && !idTipoMovimento.equals("")
							&& !idTipoMovimento.equals("-1")) {

						// Pesquisa Arrecadador Contrato

						FiltroArrecadadorContrato filtroArrecadadorContrato = new FiltroArrecadadorContrato();
						int i;
						for (i = 0; i < arrecadadorEncontrado.size(); i++) {

							if (arrecadadorEncontrado.size() == 1) {

								filtroArrecadadorContrato
										.adicionarParametro(new ParametroSimples(
												FiltroArrecadadorContrato.ARRECADADOR_ID,
												""
														+ ((Arrecadador) ((List) arrecadadorEncontrado)
																.get(i))
																.getId()));

							} else if (i == 0) {

								filtroArrecadadorContrato
										.adicionarParametro(new ParametroSimples(
												FiltroArrecadadorContrato.ARRECADADOR_ID,
												""
														+ ((Arrecadador) ((List) arrecadadorEncontrado)
																.get(i))
																.getId(),
												ParametroSimples.CONECTOR_OR,
												arrecadadorEncontrado.size()));

							} else if (i == (arrecadadorEncontrado.size() - 1)) {

								filtroArrecadadorContrato
										.adicionarParametro(new ParametroSimples(
												FiltroArrecadadorContrato.ARRECADADOR_ID,
												""
														+ ((Arrecadador) ((List) arrecadadorEncontrado)
																.get(i))
																.getId()));

							} else {
								filtroArrecadadorContrato
										.adicionarParametro(new ParametroSimples(
												FiltroArrecadadorContrato.ARRECADADOR_ID,
												""
														+ ((Arrecadador) ((List) arrecadadorEncontrado)
																.get(i))
																.getId(),
												ParametroSimples.CONECTOR_OR));
							}

						}
						
						filtroArrecadadorContrato.adicionarParametro(new ParametroNulo(
								FiltroArrecadadorContrato.DATA_CONTRATO_ENCERRAMENTO));
						filtroArrecadadorContrato
								.adicionarCaminhoParaCarregamentoEntidade("arrecadador");

						Collection colecaoArrecadadorContrato = this
								.getFachada().pesquisar(
										filtroArrecadadorContrato,
										ArrecadadorContrato.class.getName());

						Collection removidos = new ArrayList();

						boolean codigoBarras = false;
						boolean fichaCompensacao = false;
						boolean debitoAutomatico = false;

						if (colecaoArrecadadorContrato != null
								&& !colecaoArrecadadorContrato.isEmpty()) {

							if (colecaoArrecadadorContrato.size() > 1) {

								Iterator colecaoArrecadadorContratoIterator = colecaoArrecadadorContrato
										.iterator();

								while (colecaoArrecadadorContratoIterator
										.hasNext()) {

									ArrecadadorContrato arrecadadorContrato = (ArrecadadorContrato) colecaoArrecadadorContratoIterator
											.next();

									if (idTipoMovimento
											.equalsIgnoreCase("CODIGO DE BARRAS")) {

										if (arrecadadorContrato
												.getNumeroSequecialArquivoRetornoCodigoBarras() == null
												|| arrecadadorContrato
														.getNumeroSequecialArquivoRetornoCodigoBarras().toString()
														.equals("0")) {

											removidos.add(arrecadadorContrato);
										}
										codigoBarras = true;
										httpServletRequest.setAttribute(
												"tipoMovimento",
												"CODIGO DE BARRAS");

									} else if (idTipoMovimento
											.equalsIgnoreCase("FICHA DE COMPENSACAO")) {

										if (arrecadadorContrato
												.getNumeroSequencialArquivoRetornoFichaCompensacao() == null
												|| arrecadadorContrato
														.getNumeroSequencialArquivoRetornoFichaCompensacao().toString()
														.equals("0")) {

											removidos.add(arrecadadorContrato);

										}

										fichaCompensacao = true;
										httpServletRequest.setAttribute(
												"tipoMovimento",
												"FICHA DE COMPENSACAO");

									} else {

										if (arrecadadorContrato
												.getNumeroSequencialArquivoEnvioDebitoAutomatico() == null
												|| arrecadadorContrato
														.getNumeroSequencialArquivoEnvioDebitoAutomatico().toString()
														.equals("0")) {

											removidos.add(arrecadadorContrato);

										}
										debitoAutomatico = true;
										httpServletRequest.setAttribute(
												"tipoMovimento",
												"DEBITO AUTOMATICO");

									}

								}
							}else{
								httpServletRequest.setAttribute(
										"tipoMovimento",
										idTipoMovimento);
							}

							if ( !Util.isVazioOrNulo(removidos)) {

								colecaoArrecadadorContrato.removeAll(removidos);

							}

							if (colecaoArrecadadorContrato == null
									|| colecaoArrecadadorContrato.isEmpty()) {

								if (codigoBarras) {

									throw new ActionServletException(
											"atencao.arrecadador_contrato_sem_numero_sequecial_arquivo_retorno",
											null, "C�DIGO DE BARRAS");

								} else if (fichaCompensacao) {

									throw new ActionServletException(
											"atencao.arrecadador_contrato_sem_numero_sequecial_arquivo_retorno",
											null, "FICHA DE COMPENSA��O");

								} else if (debitoAutomatico) {

									throw new ActionServletException(
											"atencao.arrecadador_contrato_sem_numero_sequecial_arquivo_envio_debito_automatico");
								}

							} else {

								sessao.setAttribute(
										"colecaoArrecadadorContrato",
										colecaoArrecadadorContrato);
							}
						}

						if (colecaoArrecadadorContrato.size() < 2) {
							sessao.setAttribute("um", true);
						} else {
							sessao.setAttribute("convenio", true);
						}

					} else {
						sessao.setAttribute("colecaoArrecadadorContrato",
								new ArrayList());
					}

				} else {

					httpServletRequest.setAttribute("parametroNomeArrecadador",
							"");
					httpServletRequest.setAttribute("parametroNomeArrecadador",
							"ARRECADADOR INEXISTENTE");

					httpServletRequest.setAttribute(
							"idArrecadadorNaoEncontrado", "exception");

					sessao.removeAttribute("convenio");

				}

			}

		}

		// Cria��o das cole��es
		Collection tiposMovimentos = new ArrayList();

		tiposMovimentos.add("DEBITO AUTOMATICO");
		tiposMovimentos.add("CODIGO DE BARRAS");
		tiposMovimentos.add("FICHA DE COMPENSACAO");

		sessao.setAttribute("tiposMovimentos", tiposMovimentos);

		return retorno;
	}
}
