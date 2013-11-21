package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

public class GerarArquivoTextoDeclaracaoQuitacaoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
	    HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;
        
        //Variaveis
        String idFaturamentoGrupo = (String) pesquisarActionForm.get("idGrupoFaturamento");
        
        Fachada fachada = Fachada.getInstancia();
        
        fachada.
        	inserirProcessoGerarArquivoDeclaracaoQuitacaoDebitos(
        			idFaturamentoGrupo,usuario);
        
		
		montarPaginaSucesso(httpServletRequest,
				"Arquivo Texto da Declaração de Quitação enviado para processamento", 
				"Voltar",
				"exibirGerarArquivoTextoDeclaracaoQuitacaoAction.do");
		
		
		return retorno;
	
	}

}
