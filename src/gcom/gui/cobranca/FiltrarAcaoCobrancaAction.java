package gcom.gui.cobranca;

import gcom.cobranca.FiltroCobrancaAcao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processamento para filtrar a ação da cobrança
 * 
 * @author Sávio Luiz
 * @date 10/10/2007
 */
public class FiltrarAcaoCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("manterCobrancaAcao");

		AcaoCobrancaFiltrarActionForm acaoCobrancaFiltrarActionForm = (AcaoCobrancaFiltrarActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		String indicadorAtualizar = httpServletRequest
				.getParameter("indicadorAtualizar");

		if (indicadorAtualizar == null) {
			acaoCobrancaFiltrarActionForm.setIndicadorAtualizar("2");
		} else {
			acaoCobrancaFiltrarActionForm
					.setIndicadorAtualizar(indicadorAtualizar);
		}

		Fachada fachada = Fachada.getInstancia();

		FiltroCobrancaAcao filtroCobrancaAcao = fachada.filtrarAcaoCobranca(
				acaoCobrancaFiltrarActionForm.getDescricaoAcao(),
				acaoCobrancaFiltrarActionForm.getNumeroDiasValidade(),
				acaoCobrancaFiltrarActionForm.getIdAcaoPredecessora(),
				acaoCobrancaFiltrarActionForm.getNumeroDiasEntreAcoes(),
				acaoCobrancaFiltrarActionForm.getIdTipoDocumentoGerado(),
				acaoCobrancaFiltrarActionForm.getIdSituacaoLigacaoAgua(),
				acaoCobrancaFiltrarActionForm.getIdSituacaoLigacaoEsgoto(),
				acaoCobrancaFiltrarActionForm.getIdCobrancaCriterio(),
				acaoCobrancaFiltrarActionForm.getDescricaoCobrancaCriterio(),
				acaoCobrancaFiltrarActionForm.getIdServicoTipo(),
				acaoCobrancaFiltrarActionForm.getDescricaoServicoTipo(),
				acaoCobrancaFiltrarActionForm.getOrdemCronograma(),
				acaoCobrancaFiltrarActionForm.getIcCompoeCronograma(),
				acaoCobrancaFiltrarActionForm.getIcAcaoObrigatoria(),
				acaoCobrancaFiltrarActionForm.getIcRepetidaCiclo(),
				acaoCobrancaFiltrarActionForm.getIcSuspensaoAbastecimento(),
				acaoCobrancaFiltrarActionForm.getIcDebitosACobrar(),
				acaoCobrancaFiltrarActionForm.getIcAcrescimosImpontualidade(),
				acaoCobrancaFiltrarActionForm.getIcGeraTaxa(),
				acaoCobrancaFiltrarActionForm.getIcEmitirBoletimCadastro(),
				acaoCobrancaFiltrarActionForm.getIcImoveisSemDebitos(),
				acaoCobrancaFiltrarActionForm.getIcMetasCronograma(),
				acaoCobrancaFiltrarActionForm.getIcOrdenamentoCronograma(),
				acaoCobrancaFiltrarActionForm.getIcOrdenamentoEventual(),
				acaoCobrancaFiltrarActionForm.getIcDebitoInterfereAcao(),
				acaoCobrancaFiltrarActionForm.getNumeroDiasRemuneracaoTerceiro(),
				acaoCobrancaFiltrarActionForm.getIcUso(),
				acaoCobrancaFiltrarActionForm.getIcCreditosARealizar(),
				acaoCobrancaFiltrarActionForm.getIcNotasPromissoria());

		// Manda o filtro pelo request para o ExibirManterClienteAction
		sessao.setAttribute("filtroCobrancaCriterio", filtroCobrancaAcao);
		sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);

		return retorno;
	}

}
