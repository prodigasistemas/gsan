package gcom.gui.cobranca;

import gcom.cobranca.bean.CobrancaAcaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;

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
public class InserirAcaoCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AcaoCobrancaActionForm acaoCobrancaActionForm = (AcaoCobrancaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);

		Fachada fachada = Fachada.getInstancia();
		Integer idCobrancaAcao = null;
		
   
		
		

		
		CobrancaAcaoHelper cobrancaAcaoHelper = new CobrancaAcaoHelper(acaoCobrancaActionForm.getDescricaoAcao(),
				acaoCobrancaActionForm.getIcAcaoObrigatoria(),
				acaoCobrancaActionForm.getIcRepetidaCiclo(),
				acaoCobrancaActionForm.getIcSuspensaoAbastecimento(),
				acaoCobrancaActionForm.getNumeroDiasValidade(),
				acaoCobrancaActionForm.getNumeroDiasEntreAcoes(),
				""+ConstantesSistema.INDICADOR_USO_ATIVO,
				acaoCobrancaActionForm.getIcDebitosACobrar(),
				acaoCobrancaActionForm.getIcCreditosARealizar(),
				acaoCobrancaActionForm.getIcNotasPromissoria(),
				acaoCobrancaActionForm.getIcGeraTaxa(),
				acaoCobrancaActionForm.getOrdemCronograma(),
				acaoCobrancaActionForm.getIcAcrescimosImpontualidade(),
				acaoCobrancaActionForm.getIdAcaoPredecessora(),
				acaoCobrancaActionForm.getIdSituacaoLigacaoEsgoto(),
				acaoCobrancaActionForm.getIdTipoDocumentoGerado(),
				acaoCobrancaActionForm.getIdSituacaoLigacaoAgua(),
				acaoCobrancaActionForm.getIdServicoTipo(),
				acaoCobrancaActionForm.getIdCobrancaCriterio(),
				acaoCobrancaActionForm.getIcCompoeCronograma(),
				acaoCobrancaActionForm.getIcEmitirBoletimCadastro(),
				acaoCobrancaActionForm.getIcImoveisSemDebitos(),
				acaoCobrancaActionForm.getNumeroDiasVencimento(),
				acaoCobrancaActionForm.getDescricaoCobrancaCriterio(),
				acaoCobrancaActionForm.getDescricaoServicoTipo(),
				acaoCobrancaActionForm.getIcMetasCronograma(),
				acaoCobrancaActionForm.getIcOrdenamentoCronograma(),
				acaoCobrancaActionForm.getIcOrdenamentoEventual(),
				acaoCobrancaActionForm.getIcDebitoInterfereAcao(),
				acaoCobrancaActionForm.getNumeroDiasRemuneracaoTerceiro(),
				acaoCobrancaActionForm.getIcOrdenarMaiorValor(),
				acaoCobrancaActionForm.getIcValidarItem(),
				usuarioLogado); 
		
		idCobrancaAcao = fachada.inserirAcaoCobranca(cobrancaAcaoHelper);

		

		montarPaginaSucesso(httpServletRequest, "Ação de Cobrança "
				+ acaoCobrancaActionForm.getDescricaoAcao() + " inserida com sucesso.",
				"Inserir outra Ação de Cobrança",
				"exibirInserirAcaoCobrancaAction.do?menu=sim",
				"exibirAtualizarAcaoCobrancaAction.do?idRegistroAtualizar="
						+ idCobrancaAcao + "&retornoFiltrar=1",
				"Atualizar Ação de Cobrança inserida");
		return retorno;
	}

}
