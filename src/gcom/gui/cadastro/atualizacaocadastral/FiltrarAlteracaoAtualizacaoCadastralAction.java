package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarAlteracaoAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		Fachada fachada = Fachada.getInstancia();

		ActionForward retorno = actionMapping.findForward("filtrarAlteracaoAtualizacaoCadastral");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarAlteracaoAtualizacaoCadastralActionForm form = (FiltrarAlteracaoAtualizacaoCadastralActionForm) actionForm;
        
		if (!form.existeParametroInformado()) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro = new FiltrarAlteracaoAtualizacaoCadastralActionHelper(form);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> helper = fachada.pesquisarMovimentoAtualizacaoCadastral(filtro);
        
        if( helper.isEmpty()){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", "exibirFiltrarAlteracaoAtualizacaoCadastralAction.do", null, new String[] {});
        }
        sessao.setAttribute("colecaoConsultarMovimentoAtualizacaoCadastralHelper",helper);
        sessao.setAttribute("aprovacaoEmLote", filtro.isAprovacaoEmLote());

		return retorno;
	}
}
