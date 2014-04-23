package gcom.gui.cadastro.imovel;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.bean.ObterDescricaoSituacaoOSHelper;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.bean.CobrancaDocumentoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 8° Aba - Dopcumento de Cobrança e ordens de Serviçoi de Cobrança Emitidos
 * para o Imóvel
 * 
 * @author Rafael Santos
 * @since 05/09/2006
 */
public class ExibirConsultarImovelDocumentosCobrancaAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("consultarImovelDocumentosCobranca");

		// Obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarImovelActionForm consultarImovelActionForm = (ConsultarImovelActionForm) actionForm;

		// id do imovel da aba documento de cobranca
		String idImovelDocumentosCobranca = consultarImovelActionForm
				.getIdImovelDocumentosCobranca();
		String limparForm = httpServletRequest.getParameter("limparForm");
		String indicadorNovo = httpServletRequest.getParameter("indicadorNovo");
		String idImovelPrincipalAba = null;
		if (sessao.getAttribute("idImovelPrincipalAba") != null) {
			idImovelPrincipalAba = (String) sessao
					.getAttribute("idImovelPrincipalAba");
		}

		if (limparForm != null && !limparForm.equals("")) {
			// limpar os dados
			httpServletRequest.setAttribute(
					"idImovelDocumentosCobrancaNaoEncontrado", null);
			
			sessao.removeAttribute("imovelClientes");

			consultarImovelActionForm.setIdImovelDadosComplementares(null);
			consultarImovelActionForm.setIdImovelDadosCadastrais(null);
			consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(null);
			consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
			consultarImovelActionForm.setIdImovelDebitos(null);
			consultarImovelActionForm.setIdImovelPagamentos(null);
			consultarImovelActionForm.setIdImovelDevolucoesImovel(null);
			consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
			consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
			consultarImovelActionForm.setIdImovelRegistroAtendimento(null);
			consultarImovelActionForm.setImovIdAnt(null);

			sessao.removeAttribute("imovelDocumentosCobranca");
			sessao.removeAttribute("colecaoDocumentoCobranca");
			sessao.removeAttribute("idImovelPrincipalAba");
			consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
			consultarImovelActionForm
					.setMatriculaImovelDocumentosCobranca(null);
			consultarImovelActionForm.setSituacaoAguaDocumentosCobranca(null);
			consultarImovelActionForm.setSituacaoEsgotoDocumentosCobranca(null);
			

			// }else if(idImovelDocumentosCobranca != null &&
			// !idImovelDocumentosCobranca.equalsIgnoreCase("")){
		} else if ((idImovelDocumentosCobranca != null && !idImovelDocumentosCobranca
				.equalsIgnoreCase(""))
				|| (idImovelPrincipalAba != null && !idImovelPrincipalAba
						.equalsIgnoreCase(""))) {

			if (idImovelDocumentosCobranca != null
					&& !idImovelDocumentosCobranca.equalsIgnoreCase("")) {
				
			
				if (idImovelPrincipalAba != null
						&& !idImovelPrincipalAba.equalsIgnoreCase("")) {

					if (indicadorNovo != null && !indicadorNovo.equals("")) {
						consultarImovelActionForm
								.setIdImovelDocumentosCobranca(idImovelDocumentosCobranca);
						
					} else if (!(idImovelDocumentosCobranca
							.equals(idImovelPrincipalAba))) {
						consultarImovelActionForm
								.setIdImovelDocumentosCobranca(idImovelPrincipalAba);
						idImovelDocumentosCobranca = idImovelPrincipalAba;
					}

				}
			} else if (idImovelPrincipalAba != null
					&& !idImovelPrincipalAba.equalsIgnoreCase("")) {
				consultarImovelActionForm
						.setIdImovelRegistroAtendimento(idImovelPrincipalAba);
				idImovelDocumentosCobranca = idImovelPrincipalAba;
			}

			// Obtém a instância da Fachada
			Fachada fachada = Fachada.getInstancia();
			Imovel imovel = null;
			// verifica se o objeto imovel é o mesmo ja pesquisado, para não
			// precisar pesquisar mas.
			boolean imovelNovoPesquisado = false;
			if (sessao.getAttribute("imovelDocumentosCobranca") != null) {
				imovel = (Imovel) sessao
						.getAttribute("imovelDocumentosCobranca");
				if (!(imovel.getId().toString()
						.equals(idImovelDocumentosCobranca.trim()))) {
					imovel = fachada
							.consultarImovelHistoricoFaturamento(new Integer(
									idImovelDocumentosCobranca.trim()));
					imovelNovoPesquisado = true;
				}
			} else {
				imovel = fachada
						.consultarImovelHistoricoFaturamento(new Integer(
								idImovelDocumentosCobranca.trim()));
				imovelNovoPesquisado = true;
			}

			if (imovel != null) {
				sessao.setAttribute("imovelDocumentosCobranca", imovel);
				sessao.setAttribute("idImovelPrincipalAba", imovel.getId()
						.toString());
				consultarImovelActionForm.setIdImovelDocumentosCobranca(imovel
						.getId().toString());
				
				if (imovel.getIndicadorExclusao().equals(ConstantesSistema.SIM)) {
					httpServletRequest.setAttribute("imovelExcluido", true);
				}

				// caso o imovel pesquisado seja diferente do pesquisado
				// anterior ou seja a primeira vez que se esteja pesquisando
				if (imovelNovoPesquisado) {
					// seta na tela a inscrição do imovel
					httpServletRequest.setAttribute(
							"idImovelDocumentosCobrancaNaoEncontrado", null);

					consultarImovelActionForm
							.setMatriculaImovelDocumentosCobranca(fachada
									.pesquisarInscricaoImovelExcluidoOuNao(new Integer(
											idImovelDocumentosCobranca.trim())));

					// seta a situação de agua
					if (imovel.getLigacaoAguaSituacao() != null) {
						consultarImovelActionForm
								.setSituacaoAguaDocumentosCobranca(imovel
										.getLigacaoAguaSituacao()
										.getDescricao());
					}
					// seta a situação de esgoto
					if (imovel.getLigacaoEsgotoSituacao() != null) {
						consultarImovelActionForm
								.setSituacaoEsgotoDocumentosCobranca(imovel
										.getLigacaoEsgotoSituacao()
										.getDescricao());
					}

					// 1º Passo - Pegar o total de registros através de um count
					// da consulta que aparecerá na tela
					// int totalRegistros = fachada
					// .consultarQuantidadeImovelDocumentosCobranca(new
					// Integer(idImovelDocumentosCobranca.trim()));

					// 2º Passo - Chamar a função de Paginação passando o total
					// de registros
					// retorno = this.controlarPaginacao(httpServletRequest,
					// retorno,
					// totalRegistros);

					// 3º Passo - Obter a coleção da consulta que aparecerá na
					// tela passando o numero de paginas
					// da pesquisa que está no request
					Collection colecaoDocumentoCobranca = fachada
							.consultarImovelDocumentosCobranca(new Integer(
									idImovelDocumentosCobranca.trim()), 0);

					/*
					 * if (colecaoDocumentoCobranca == null ||
					 * colecaoDocumentoCobranca.isEmpty()) {
					 * httpServletRequest.setAttribute(
					 * "idImovelDocumentosCobrancaNaoEncontrado", null);
					 * 
					 * sessao.removeAttribute("imovelDocumentosCobranca");
					 * sessao.removeAttribute("colecaoDocumentoCobranca");
					 * sessao.removeAttribute("idImovelPrincipalAba");
					 * consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
					 * consultarImovelActionForm.setMatriculaImovelDocumentosCobranca(null);
					 * consultarImovelActionForm.setSituacaoAguaDocumentosCobranca(null);
					 * consultarImovelActionForm.setSituacaoEsgotoDocumentosCobranca(null); //
					 * [FS0010] Nenhum registro encontrado throw new
					 * ActionServletException(
					 * "atencao.pesquisa.nenhumresultado", null, ""); }
					 */

					if (colecaoDocumentoCobranca != null
							&& !colecaoDocumentoCobranca.isEmpty()) {

						Iterator colecaoDocumentoCobrancaIterator = colecaoDocumentoCobranca
								.iterator();
						CobrancaDocumentoHelper cobrancaDocumentoHelper = null;
						CobrancaDocumento cobrancaDocumento = null;
						Collection colecaoCobrancaDocumentoHelper = new ArrayList();

						while (colecaoDocumentoCobrancaIterator.hasNext()) {
							cobrancaDocumento = (CobrancaDocumento) colecaoDocumentoCobrancaIterator
									.next();
							cobrancaDocumentoHelper = new CobrancaDocumentoHelper();
							cobrancaDocumentoHelper
									.setCobrancaDocumento(cobrancaDocumento);

							Object[] dadosOrdemServico = fachada
									.pesquisarDadosOrdemServicoDocumentoCobranca(cobrancaDocumento
											.getId());
							if (dadosOrdemServico != null) {
								if (dadosOrdemServico[0] != null) {
									cobrancaDocumentoHelper
											.setIdOrdemServico((Integer) dadosOrdemServico[0]);
								}
								if (dadosOrdemServico[1] != null) {
									Short situacaoOS = (Short) dadosOrdemServico[1];
									if (situacaoOS
											.equals(OrdemServico.SITUACAO_PENDENTE)) {
										cobrancaDocumentoHelper
												.setSituacaoOrdemServico(OrdemServico.SITUACAO_DESCRICAO_PENDENTE);
									}
									if (situacaoOS
											.equals(OrdemServico.SITUACAO_ENCERRADO)) {
										ObterDescricaoSituacaoOSHelper obterDescricaoSituacaoOSHelper = fachada.obterDescricaoSituacaoOS((Integer) dadosOrdemServico[0]);
										cobrancaDocumentoHelper
												.setSituacaoOrdemServico(obterDescricaoSituacaoOSHelper.getDescricaoSituacao());
									}
									if (situacaoOS
											.equals(OrdemServico.SITUACAO_EXECUCAO_EM_ANDAMENTO)) {
										cobrancaDocumentoHelper
												.setSituacaoOrdemServico(OrdemServico.SITUACAO_DESC_ABREV_EXECUCAO_EM_ANDAMENTO);
									}
									if (situacaoOS
											.equals(OrdemServico.SITUACAO_AGUARDANDO_LIBERACAO)) {
										cobrancaDocumentoHelper
												.setSituacaoOrdemServico(OrdemServico.SITUACAO_DESC_ABREV_AGUARDANDO_LIBERACAO);
									}
								}
							}

							Integer quantidade = fachada
									.consultarQuantidadeImovelDocumentosItemCobranca(cobrancaDocumento
											.getId());

							cobrancaDocumentoHelper
									.setQuantidadeItensCobrancaDocumento(quantidade);

							colecaoCobrancaDocumentoHelper
									.add(cobrancaDocumentoHelper);
						}
						
						//Track No. 619 : Ordenar por data de emissão
						Collections.sort((List) colecaoCobrancaDocumentoHelper, new Comparator() {
							public int compare(Object a, Object b) {
								String data1 = ((CobrancaDocumentoHelper) a).getCobrancaDocumento().getEmissao().toString();
								String data2 = ((CobrancaDocumentoHelper) b).getCobrancaDocumento().getEmissao().toString();
								
								data1 = data1.substring(0, 4) + data1.substring(5, 7) + data1.substring(8, 10);
								data2 = data2.substring(0, 4) + data2.substring(5, 7) + data2.substring(8, 10);
								
								Integer dtEmissao1 = Integer.decode(data1);
								Integer dtEmissao2 = Integer.decode(data2);
								
								//String dtEmissao1 = ((CobrancaDocumentoHelper) a).getCobrancaDocumento().getEmissao().toString();
								//String dtEmissao2 = ((CobrancaDocumentoHelper) b).getCobrancaDocumento().getEmissao().toString();

								return dtEmissao2.compareTo(dtEmissao1);
							}
						});
						
						sessao.setAttribute("colecaoDocumentoCobranca",
								colecaoCobrancaDocumentoHelper);

					}else{
						sessao.removeAttribute("colecaoDocumentoCobranca");
					}
				}
			} else {
				httpServletRequest.setAttribute(
						"idImovelDocumentosCobrancaNaoEncontrado", "true");
				consultarImovelActionForm
						.setMatriculaImovelDocumentosCobranca("IMÓVEL INEXISTENTE");

				// limpar os dados pesquisados
				sessao.removeAttribute("imovelDocumentosCobranca");
				sessao.removeAttribute("colecaoDocumentoCobranca");
				sessao.removeAttribute("idImovelPrincipalAba");
				consultarImovelActionForm.setIdImovelDadosComplementares(null);
				consultarImovelActionForm.setIdImovelDadosCadastrais(null);
				consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(null);
				consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
				consultarImovelActionForm.setIdImovelDebitos(null);
				consultarImovelActionForm.setIdImovelPagamentos(null);
				consultarImovelActionForm.setIdImovelDevolucoesImovel(null);
				consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
				consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
				consultarImovelActionForm.setIdImovelRegistroAtendimento(null);
				consultarImovelActionForm.setImovIdAnt(null);
				consultarImovelActionForm.setSituacaoAguaDocumentosCobranca(null);
				consultarImovelActionForm.setSituacaoEsgotoDocumentosCobranca(null);

			}
		} else {
			consultarImovelActionForm
					.setIdImovelDocumentosCobranca(idImovelDocumentosCobranca);

			httpServletRequest.setAttribute(
					"idImovelDocumentosCobrancaNaoEncontrado", null);

			sessao.removeAttribute("imovelDocumentosCobranca");
			sessao.removeAttribute("colecaoDocumentoCobranca");
			sessao.removeAttribute("idImovelPrincipalAba");
			consultarImovelActionForm
					.setMatriculaImovelDocumentosCobranca(null);
			consultarImovelActionForm.setSituacaoAguaDocumentosCobranca(null);
			consultarImovelActionForm.setSituacaoEsgotoDocumentosCobranca(null);

		}

		return retorno;
	}

}
