package gcom.gui.atendimentopublico.ordemservico;

import java.util.Collection;
import java.util.Iterator;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
 * 
 * Action responsável por exibir a página de Encerrar OS do processo 
 * de movimentar ordem de serviço
 * 
 * @author Vivianne Sousa
 * @since 12/07/2011
 */
public class ExibirMovimentarOSSeletivaInspecaoAnormalidadeEncerrarOSAction extends GcomAction {
    
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

    	//Seta o mapeamento de retorno para a tela de Encerrar OS 
        ActionForward retorno = actionMapping.findForward("movimentarOSSeletivaInspecaoAnormalidadeEncerrarOS");

        MovimentarOSSeletivaInspecaoAnormalidadeActionForm form = (MovimentarOSSeletivaInspecaoAnormalidadeActionForm) actionForm;
        

		if (httpServletRequest.getParameter("liberaNumeroOS") != null
				&& httpServletRequest.getParameter("liberaNumeroOS").equalsIgnoreCase("sim")) {
			
			if (this.getSessao(httpServletRequest).getAttribute("colecaoAtendimentoMotivoEncerramento") != null
					&& !this.getSessao(httpServletRequest).getAttribute("colecaoAtendimentoMotivoEncerramento").equals("")) {
				
				Collection<AtendimentoMotivoEncerramento> colecaoAtendimentoMotivoEncerramento = (Collection<AtendimentoMotivoEncerramento>)
					this.getSessao(httpServletRequest).getAttribute("colecaoAtendimentoMotivoEncerramento");
				
				Iterator iterator = colecaoAtendimentoMotivoEncerramento.iterator();
				boolean motivoEncontrado = false;
				
				while(iterator.hasNext()) {
					AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = (AtendimentoMotivoEncerramento) iterator.next();
					
					if (atendimentoMotivoEncerramento.getId().compareTo(new Integer(form.getIdMotivoEncerramento())) == 0) {
						
						if (atendimentoMotivoEncerramento.getIndicadorExecucao() != 0
								&& (new Short(atendimentoMotivoEncerramento.getIndicadorExecucao())).compareTo(ConstantesSistema.NAO) == 0) {
							this.getSessao(httpServletRequest).setAttribute("habilitaNumerosOS", true);
						} else {
							this.getSessao(httpServletRequest).removeAttribute("habilitaNumerosOS");
						}
						
						this.getSessao(httpServletRequest).setAttribute("motivoInformado", true);
						
						motivoEncontrado = true;
						
						break;
					}
				}
				
				if (!motivoEncontrado){

					this.getSessao(httpServletRequest).removeAttribute("motivoInformado");
					this.getSessao(httpServletRequest).removeAttribute("habilitaNumerosOS");
				}
			}
			
		} else {
			
	        this.limparForm(form);
	    	this.limparSessao(this.getSessao(httpServletRequest));
		}
		
		if (httpServletRequest.getParameter("pesquisarOSRA") != null
				&& httpServletRequest.getParameter("pesquisarOSRA").equalsIgnoreCase("SIM")) {
				
			Integer numeroOSInicial = null;
			Integer numeroOSFinal = null;
			
			if(form.getNumeroOSInicial() != null && !form.getNumeroOSInicial().equals("")){
				numeroOSInicial = new Integer(form.getNumeroOSInicial());
				numeroOSFinal = numeroOSInicial;
			}
			if(form.getNumeroOSFinal() != null && !form.getNumeroOSFinal().equals("")){
				numeroOSFinal = new Integer(form.getNumeroOSFinal());
			}

			//[FS0002] – Verificar OS final menor que OS inicial
			if(numeroOSInicial != null && numeroOSFinal != null && 
					numeroOSInicial.compareTo(numeroOSFinal) == 1){
				throw new ActionServletException("atencao.numero_ordem_servico.final.maior.inicial");
			}
			
			Collection colecaoOSEncerrar = Fachada.getInstancia().pesquisarDadosOSEmitir(
			new Integer(form.getIdComando()),numeroOSInicial,numeroOSFinal);

			if (colecaoOSEncerrar != null && !colecaoOSEncerrar.isEmpty()) {
				this.getSessao(httpServletRequest).setAttribute("colecaoOSEncerrar", colecaoOSEncerrar);
			} else {
				this.getSessao(httpServletRequest).removeAttribute("colecaoOSEncerrar");
			}
		}

		
		if (this.getSessao(httpServletRequest).getAttribute("statusWizard") != null) {
	        //Monta o Status do Wizard
	        StatusWizard statusWizard = (StatusWizard) this.getSessao(httpServletRequest).getAttribute("statusWizard");
	        
	        statusWizard.setNomeBotaoConcluir("Encerrar OS");
	        
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
