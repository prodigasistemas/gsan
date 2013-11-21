package gcom.gui.atendimentopublico.ordemservico;

import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1192] Movimentar OS Seletiva de Inspeção de Anormalidade
 * 
 * Action responsável por exibir a página de Gerar OS do processo 
 * de movimentar ordem de serviço.
 * 
 * @author Vivianne Sousa
 * @date 14/07/2011
 */
public class ExibirMovimentarOSSeletivaInspecaoAnormalidadeGerarOSAction extends GcomAction {
    
    /**
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            					ActionForm actionForm, 
            					HttpServletRequest httpServletRequest,
            					HttpServletResponse httpServletResponse) {

    	//Seta o mapeamento de retorno para a tela de Gerar OS 
        ActionForward retorno = actionMapping.findForward("movimentarOSSeletivaInspecaoAnormalidadeGerarOS");

        MovimentarOSSeletivaInspecaoAnormalidadeActionForm form = (MovimentarOSSeletivaInspecaoAnormalidadeActionForm) actionForm;

		if (httpServletRequest.getParameter("confirmado") != null
				&& httpServletRequest.getParameter("confirmado").equalsIgnoreCase("ok")) {
			
			httpServletRequest.setAttribute("confirmacao", "true");
			
			retorno = actionMapping.findForward("gerarOSSeletivaInspecaoAnormalidadeAction");
			
			return retorno;
			
		}
		
    	this.limparForm(form);
    	this.limparSessao(this.getSessao(httpServletRequest));
		
		if (this.getSessao(httpServletRequest).getAttribute("statusWizard") != null) {
	        //Monta o Status do Wizard
	        StatusWizard statusWizard = (StatusWizard) this.getSessao(httpServletRequest).getAttribute("statusWizard");
	        
	        statusWizard.setNomeBotaoConcluir("Gerar OS");
	        
	        //manda o statusWizard para a sessão
	        this.getSessao(httpServletRequest).setAttribute("statusWizard", statusWizard);
		}
		
        //Retorna o mapemaneto na variável "retorno"
        return retorno;
    }

    private void limparForm(MovimentarOSSeletivaInspecaoAnormalidadeActionForm form){

    	form.setNumeroOSInicialEmitir("");
    	form.setNumeroOSFinalEmitir("");
    	form.setNumerosOSEmitir(new String[10]);
    	
    	form.setNumeroOSInicial("");
    	form.setNumeroOSFinal("");
    	
    	form.setTipoPesquisa("");

    	
    	form.setIdTipoServico("");
    	form.setMatriculasImoveis(new String[10]);
    	
    	form.setNumerosOS(new String[10]);
    	form.setIdMotivoEncerramento("");
    	form.setDataEncerramento("");
    	form.setHoraEncerramento("");
    	form.setObservacaoEncerramento("");
    }
    
    private void limparSessao(HttpSession sessao){

    	sessao.removeAttribute("colecaoOSEmitir");
    	sessao.removeAttribute("motivoInformado");
    	sessao.removeAttribute("habilitaNumerosOS");
    	sessao.removeAttribute("colecaoOSEncerrar");
    	
    }
}
