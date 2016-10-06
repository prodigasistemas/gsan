package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.parcelamento.FiltroParcelamento;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultarImovelParcelamentosDebitosAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

		ActionForward retorno = actionMapping.findForward("consultarImovelParcelamentosDebitos");

		HttpSession sessao = request.getSession(false);

		ConsultarImovelActionForm form = (ConsultarImovelActionForm) actionForm;

		String idImovelParcelamentosDebitos = form.getIdImovelParcelamentosDebitos();
		String limparForm = request.getParameter("limparForm");
		String indicadorNovo = request.getParameter("indicadorNovo");
		
		String idImovelPrincipalAba = null;
		if (sessao.getAttribute("idImovelPrincipalAba") != null) {
			idImovelPrincipalAba = (String) sessao.getAttribute("idImovelPrincipalAba");
		}

		if (limparForm != null && !limparForm.equals("")) {
			request.setAttribute("idImovelParcelamentosDebitosNaoEncontrado", null);

			sessao.removeAttribute("imovelParcelamentosDebitos");
			sessao.removeAttribute("colecaoParcelamento");
			sessao.removeAttribute("idImovelPrincipalAba");
			sessao.removeAttribute("imovelClientes");

			form.setIdImovelDadosComplementares(null);
			form.setIdImovelDadosCadastrais(null);
			form.setIdImovelAnaliseMedicaoConsumo(null);
			form.setIdImovelHistoricoFaturamento(null);
			form.setIdImovelDebitos(null);
			form.setIdImovelPagamentos(null);
			form.setIdImovelDevolucoesImovel(null);
			form.setIdImovelDocumentosCobranca(null);
			form.setIdImovelParcelamentosDebitos(null);
			form.setIdImovelRegistroAtendimento(null);
			form.setImovIdAnt(null);

			form.setIdImovelParcelamentosDebitos(null);
			form.setMatriculaImovelParcelamentosDebitos(null);
			form.setSituacaoAguaParcelamentosDebitos(null);
			form.setSituacaoEsgotoParcelamentosDebitos(null);
			form.setParcelamento(null);
			form.setReparcelamento(null);
			form.setReparcelamentoConsecutivo(null);

		} else if ((idImovelParcelamentosDebitos != null && !idImovelParcelamentosDebitos.equalsIgnoreCase("")) || (idImovelPrincipalAba != null && !idImovelPrincipalAba.equalsIgnoreCase(""))) {

			if (idImovelParcelamentosDebitos != null && !idImovelParcelamentosDebitos.equalsIgnoreCase("")) {

				if (idImovelPrincipalAba != null && !idImovelPrincipalAba.equalsIgnoreCase("")) {

					if (indicadorNovo != null && !indicadorNovo.equals("")) {
						form.setIdImovelParcelamentosDebitos(idImovelParcelamentosDebitos);

					} else if (!(idImovelParcelamentosDebitos.equals(idImovelPrincipalAba))) {
						form.setIdImovelParcelamentosDebitos(idImovelPrincipalAba);
						idImovelParcelamentosDebitos = idImovelPrincipalAba;
					}

				}
			} else if (idImovelPrincipalAba != null && !idImovelPrincipalAba.equalsIgnoreCase("")) {
				form.setIdImovelRegistroAtendimento(idImovelPrincipalAba);
				idImovelParcelamentosDebitos = idImovelPrincipalAba;
			}

			Imovel imovel = null;
			boolean imovelNovoPesquisado = false;
			if (sessao.getAttribute("imovelParcelamentosDebitos") != null) {
				imovel = (Imovel) sessao.getAttribute("imovelParcelamentosDebitos");
				if (!(imovel.getId().toString().equals(idImovelParcelamentosDebitos.trim()))) {
					imovel = getFachada().consultarParcelamentosDebitosImovel(new Integer(idImovelParcelamentosDebitos.trim()));
					imovelNovoPesquisado = true;
				}
			} else {
				imovel = getFachada().consultarParcelamentosDebitosImovel(new Integer(idImovelParcelamentosDebitos.trim()));
				imovelNovoPesquisado = true;
			}

			if (imovel != null) {
				sessao.setAttribute("imovelParcelamentosDebitos", imovel);
				sessao.setAttribute("idImovelPrincipalAba", imovel.getId().toString());
				form.setIdImovelParcelamentosDebitos(imovel.getId().toString());

				if (imovel.getIndicadorExclusao().equals(ConstantesSistema.SIM)) {
					request.setAttribute("imovelExcluido", true);
				}

				if (imovelNovoPesquisado) {
					request.setAttribute("idImovelParcelamentosDebitosNaoEncontrado", null);

					form.setMatriculaImovelParcelamentosDebitos(getFachada().pesquisarInscricaoImovelExcluidoOuNao(new Integer(idImovelParcelamentosDebitos.trim())));

					if (imovel.getLigacaoAguaSituacao() != null) {
						form.setSituacaoAguaParcelamentosDebitos(imovel.getLigacaoAguaSituacao().getDescricao());
					}

					if (imovel.getLigacaoEsgotoSituacao() != null) {
						form.setSituacaoEsgotoParcelamentosDebitos(imovel.getLigacaoEsgotoSituacao().getDescricao());
					}

					if (imovel.getNumeroParcelamento() != null) {
						form.setParcelamento("" + imovel.getNumeroParcelamento());
					} else {
						form.setParcelamento(null);
					}

					if (imovel.getNumeroReparcelamento() != null) {
						form.setReparcelamento("" + imovel.getNumeroReparcelamento());
					} else {
						form.setReparcelamento(null);
					}

					if (imovel.getNumeroReparcelamentoConsecutivos() != null) {
						form.setReparcelamentoConsecutivo("" + imovel.getNumeroReparcelamentoConsecutivos());
					} else {
						form.setReparcelamentoConsecutivo(null);
					}

					FiltroParcelamento filtroParcelamento = new FiltroParcelamento();
					filtroParcelamento.adicionarParametro(new ParametroSimples(FiltroParcelamento.IMOVEL_ID, idImovelParcelamentosDebitos.trim()));
					filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("parcelamentoSituacao");

					Collection<Parcelamento> colecaoParcelamento = getFachada().pesquisar(filtroParcelamento, Parcelamento.class.getName());

					if (colecaoParcelamento != null && !colecaoParcelamento.isEmpty()) {
						sessao.setAttribute("colecaoParcelamento", colecaoParcelamento);
					} else {
						sessao.removeAttribute("colecaoParcelamento");
					}

				}
			} else {
				request.setAttribute("idImovelParcelamentosDebitosNaoEncontrado", "true");
				form.setMatriculaImovelParcelamentosDebitos("IMÓVEL INEXISTENTE");

				sessao.removeAttribute("imovelParcelamentosDebitos");
				sessao.removeAttribute("colecaoParcelamento");
				sessao.removeAttribute("idImovelPrincipalAba");
				form.setIdImovelDadosComplementares(null);
				form.setIdImovelDadosCadastrais(null);
				form.setIdImovelAnaliseMedicaoConsumo(null);
				form.setIdImovelHistoricoFaturamento(null);
				form.setIdImovelDebitos(null);
				form.setIdImovelPagamentos(null);
				form.setIdImovelDevolucoesImovel(null);
				form.setIdImovelDocumentosCobranca(null);
				form.setIdImovelParcelamentosDebitos(null);
				form.setIdImovelRegistroAtendimento(null);
				form.setImovIdAnt(null);
				form.setSituacaoAguaParcelamentosDebitos(null);
				form.setSituacaoEsgotoParcelamentosDebitos(null);
				form.setParcelamento(null);
				form.setReparcelamento(null);
				form.setReparcelamentoConsecutivo(null);

			}
		} else {
			form.setIdImovelParcelamentosDebitos(idImovelParcelamentosDebitos);
			request.setAttribute("idImovelParcelamentosDebitosNaoEncontrado", null);

			sessao.removeAttribute("imovelParcelamentosDebitos");
			sessao.removeAttribute("colecaoParcelamento");
			sessao.removeAttribute("idImovelPrincipalAba");

			form.setMatriculaImovelParcelamentosDebitos(null);
			form.setSituacaoAguaParcelamentosDebitos(null);
			form.setSituacaoEsgotoParcelamentosDebitos(null);
			form.setParcelamento(null);
			form.setReparcelamento(null);
			form.setReparcelamentoConsecutivo(null);
		}

		return retorno;
	}
}
