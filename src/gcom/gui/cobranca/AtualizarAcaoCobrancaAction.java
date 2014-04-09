package gcom.gui.cobranca;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.bean.CobrancaAcaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processamento para inserir o critério da cobrança e as linhas do criterio da
 * cobrança
 * 
 * @author Sávio Luiz
 * @date 18/09/2007
 */
public class AtualizarAcaoCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AcaoCobrancaAtualizarActionForm acaoCobrancaAtualizarActionForm = (AcaoCobrancaAtualizarActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);

		Fachada fachada = Fachada.getInstancia();
		
		CobrancaAcao cobrancaAcao = (CobrancaAcao)sessao.getAttribute("cobrancaAcao");
		
		String idAcaoPredecessora = "";
		String numeroDiasMinimoAcoes = "";
		if ( acaoCobrancaAtualizarActionForm.getIdAcaoPredecessora() != null &&
				!acaoCobrancaAtualizarActionForm.getIdAcaoPredecessora().equals("")){
			idAcaoPredecessora = acaoCobrancaAtualizarActionForm.getIdAcaoPredecessora();
			numeroDiasMinimoAcoes = acaoCobrancaAtualizarActionForm.getNumeroDiasEntreAcoes();
		}
		
		CobrancaAcaoHelper cobrancaAcaoHelper = new CobrancaAcaoHelper(acaoCobrancaAtualizarActionForm.getDescricaoAcao(),
				acaoCobrancaAtualizarActionForm.getIcAcaoObrigatoria(),
				acaoCobrancaAtualizarActionForm.getIcRepetidaCiclo(),
				acaoCobrancaAtualizarActionForm.getIcSuspensaoAbastecimento(),
				acaoCobrancaAtualizarActionForm.getNumeroDiasValidade(),
				numeroDiasMinimoAcoes,
				acaoCobrancaAtualizarActionForm.getIcUso(),
				acaoCobrancaAtualizarActionForm.getIcDebitosACobrar(),
				acaoCobrancaAtualizarActionForm.getIcCreditosARealizar(),
				acaoCobrancaAtualizarActionForm.getIcNotasPromissoria(),
				acaoCobrancaAtualizarActionForm.getIcGeraTaxa(),
				acaoCobrancaAtualizarActionForm.getOrdemCronograma(),
				acaoCobrancaAtualizarActionForm.getIcAcrescimosImpontualidade(),
				idAcaoPredecessora,
				acaoCobrancaAtualizarActionForm.getIdSituacaoLigacaoEsgoto(),
				acaoCobrancaAtualizarActionForm.getIdTipoDocumentoGerado(),
				acaoCobrancaAtualizarActionForm.getIdSituacaoLigacaoAgua(),
				acaoCobrancaAtualizarActionForm.getIdServicoTipo(),
				acaoCobrancaAtualizarActionForm.getIdCobrancaCriterio(),
				acaoCobrancaAtualizarActionForm.getIcCompoeCronograma(),
				acaoCobrancaAtualizarActionForm.getIcEmitirBoletimCadastro(),
				acaoCobrancaAtualizarActionForm.getIcImoveisSemDebitos(),
				acaoCobrancaAtualizarActionForm.getNumeroDiasVencimento(),
				acaoCobrancaAtualizarActionForm.getDescricaoCobrancaCriterio(),
				acaoCobrancaAtualizarActionForm.getDescricaoServicoTipo(),
				acaoCobrancaAtualizarActionForm.getIcMetasCronograma(),
				acaoCobrancaAtualizarActionForm.getIcOrdenamentoCronograma(),
				acaoCobrancaAtualizarActionForm.getIcOrdenamentoEventual(),
				acaoCobrancaAtualizarActionForm.getIcDebitoInterfereAcao(),
				acaoCobrancaAtualizarActionForm.getNumeroDiasRemuneracaoTerceiro(),
				acaoCobrancaAtualizarActionForm.getIcOrdenarMaiorValor(),
				acaoCobrancaAtualizarActionForm.getIcValidarItem(),
				usuarioLogado); 
		
		
		fachada.atualizarAcaoCobranca(cobrancaAcao,cobrancaAcaoHelper);

		sessao.removeAttribute("voltar");
		sessao.removeAttribute("cobrancaAcao");
		sessao.removeAttribute("colecaoLigacaoEsgotoSituacao");
		sessao.removeAttribute("colecaoLigacaoAguaSituacao");
		sessao.removeAttribute("colecaoDocumentoTipo");
		sessao.removeAttribute("colecaoAcaoPredecessora");

		montarPaginaSucesso(httpServletRequest, "Ação de Cobrança "
				+ acaoCobrancaAtualizarActionForm.getDescricaoAcao() + " atualizada com sucesso.",
				"Realizar outra Manutenção de Ação de Cobrança",
		        "exibirFiltrarAcaoCobrancaAction.do?menu=sim");
		
		
		return retorno;
	}

}
