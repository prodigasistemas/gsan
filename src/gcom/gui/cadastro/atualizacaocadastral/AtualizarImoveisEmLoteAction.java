package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.FachadaException;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarImoveisEmLoteAction extends GcomAction{
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
    	ActionForward retorno =	actionMapping.findForward("telaSucesso");
        
    	FiltrarAlteracaoAtualizacaoCadastralActionForm form = (FiltrarAlteracaoAtualizacaoCadastralActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
        HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> listaImoveis = (Collection<ConsultarMovimentoAtualizacaoCadastralHelper>) sessao.getAttribute("colecaoConsultarMovimentoAtualizacaoCadastralHelper");
		
		try {
			fachada.aprovarImoveisEmLote(usuario, listaImoveis);
        } catch (FachadaException e) {
        	throw new ActionServletException("erro.aprovacao.lote", "exibirFiltrarAlteracaoAtualizacaoCadastralAction.do", null, new String[] {});
        }
		
		montarPaginaSucesso(httpServletRequest, "Imóveis atualizados em lote com sucesso.",
				"Consultar Movimento",
				"exibirFiltrarAlteracaoAtualizacaoCadastralAction.do?menu=sim");        
        		return retorno;

		
	}

}
