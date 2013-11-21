package gcom.gui.atendimentopublico.ordemservico;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1192] Movimentar OS Seletiva de Inspeção de Anormalidade
 * 
 * Action responsável por exibir a página de Emitir OS do processo 
 * de movimentar ordem de serviço.
 * 
 * @author Vivianne Sousa
 * @date 14/07/2011
 */
public class ExibirMovimentarOSSeletivaInspecaoAnormalidadeEmitirOSAction extends GcomAction {
    
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

    	//Seta o mapeamento de retorno para a tela de Emitir OS 
        ActionForward retorno = actionMapping.findForward("movimentarOSSeletivaInspecaoAnormalidadeEmitirOS");

        MovimentarOSSeletivaInspecaoAnormalidadeActionForm form = (MovimentarOSSeletivaInspecaoAnormalidadeActionForm) actionForm;
		
		if (httpServletRequest.getParameter("confirmado") != null
				&& httpServletRequest.getParameter("confirmado").equalsIgnoreCase("ok")) {
			
			httpServletRequest.setAttribute("confirmacao", "true");

			retorno = actionMapping.findForward("emitirOSAction");
			
			return retorno;
			
		}
		
		if (httpServletRequest.getParameter("limparTotalizacao") != null
				&& httpServletRequest.getParameter("limparTotalizacao").equalsIgnoreCase("SIM")) {

			form.setNumeroOSInicialEmitir("");
			form.setNumeroOSFinalEmitir("");
			form.setNumerosOSEmitir(new String[10]);
			
			this.getSessao(httpServletRequest).removeAttribute("colecaoOSEmitir");
		}
		
	
		if (httpServletRequest.getParameter("pesquisarOSRA") != null
			&& httpServletRequest.getParameter("pesquisarOSRA").equalsIgnoreCase("SIM")) {
			
			Integer numeroOSInicial = null;
			Integer numeroOSFinal = null;
			
			if(form.getNumeroOSInicialEmitir() != null && !form.getNumeroOSInicialEmitir().equals("")){
				numeroOSInicial = new Integer(form.getNumeroOSInicialEmitir());
				numeroOSFinal = numeroOSInicial;
			}
			if(form.getNumeroOSFinalEmitir() != null && !form.getNumeroOSFinalEmitir().equals("")){
				numeroOSFinal = new Integer(form.getNumeroOSFinalEmitir());
			}

			//[FS0002] – Verificar OS final menor que OS inicial
			if(numeroOSInicial != null && numeroOSFinal != null && 
					numeroOSInicial.compareTo(numeroOSFinal) == 1){
				throw new ActionServletException("atencao.numero_ordem_servico.final.maior.inicial");
			}
			
			Collection colecaoOSEmitir = Fachada.getInstancia().pesquisarDadosOSEmitir(
			new Integer(form.getIdComando()),numeroOSInicial,numeroOSFinal);

			if (colecaoOSEmitir != null && !colecaoOSEmitir.isEmpty()) {
				this.getSessao(httpServletRequest).setAttribute("colecaoOSEmitir", colecaoOSEmitir);
			} else {
				this.getSessao(httpServletRequest).removeAttribute("colecaoOSEmitir");
			}
		}

		if (this.getSessao(httpServletRequest).getAttribute("statusWizard") != null) {
	        //Monta o Status do Wizard
	        StatusWizard statusWizard = (StatusWizard) this.getSessao(httpServletRequest).getAttribute("statusWizard");
	        
	        statusWizard.setNomeBotaoConcluir("Emitir OS");
	        
	        //manda o statusWizard para a sessão
	        this.getSessao(httpServletRequest).setAttribute("statusWizard", statusWizard);
		}
        
        //Retorna o mapemaneto na variável "retorno"
        return retorno;
    }
    
}
