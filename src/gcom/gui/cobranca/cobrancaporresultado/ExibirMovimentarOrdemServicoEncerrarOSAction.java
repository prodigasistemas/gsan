package gcom.gui.cobranca.cobrancaporresultado;

import java.util.Collection;
import java.util.Iterator;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
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
 * [UC1169] Movimentar Ordens de Serviço de Cobrança por Resultado
 * 
 * Action responsável por exibir a página de Encerrar OS do processo 
 * de movimentar ordem de serviço de cobrança por resultado.
 * 
 * @author Mariana Victor
 * @date 10/05/2011
 */
public class ExibirMovimentarOrdemServicoEncerrarOSAction extends GcomAction {
    
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
        ActionForward retorno = actionMapping.findForward("movimentarOrdemServicoEncerrarOS");

        MovimentarOrdemServicoActionForm form = (MovimentarOrdemServicoActionForm) actionForm;
        

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

    private void limparForm(MovimentarOrdemServicoActionForm form){

    	form.setNumeroOSInicial("");
    	form.setNumeroOSFinal("");
    	
    	form.setTipoDivEscolhida("");
    	form.setTipoPesquisa("");
    	
    	form.setIdsCategoria(null);
    	form.setIdsImovelPerfil(null);
    	form.setIdsGerenciaRegional(null);
    	form.setIdsUnidadeNegocio(null);
    	
    	form.setIdLocalidadeOrigem("");
    	form.setIdLocalidadeDestino("");
    	form.setNomeLocalidadeOrigem("");
    	form.setNomeLocalidadeDestino("");
    	
    	form.setIdSetorComercialDestino("");
    	form.setIdSetorComercialOrigem("");
    	form.setCodigoSetorComercialDestino("");
    	form.setCodigoSetorComercialOrigem("");
    	form.setDescricaoSetorComercialDestino("");
    	form.setDescricaoSetorComercialOrigem("");
    	
    	form.setCodigoQuadraInicial("");
    	form.setDescricaoQuadraInicial("");
    	form.setCodigoQuadraFinal("");
    	form.setDescricaoQuadraFinal("");
    	
    	form.setValorMinimo("");
    	form.setValorMaximo("");
    	form.setQtdContas("");
    	form.setQtdClientes("");
    	form.setValorTotalDivida("");
    	form.setQtdeTotalClientes("");
    	
    	form.setColecaoInformada("");
    	form.setTotalSelecionado("");
    	
    	form.setNumerosOS(new String[10]);
    	form.setIdMotivoEncerramento("");
    	form.setDataEncerramento("");
    	form.setHoraEncerramento("");
    	form.setObservacaoEncerramento("");
    	
    }
    
    private void limparSessao(HttpSession sessao){

    	//1ª aba
    	sessao.removeAttribute("colecaoQuantidadeContas");
    	sessao.removeAttribute("colecaoFaixa");
    	sessao.removeAttribute("colecaoQtdeContas");
    	sessao.removeAttribute("colecaoQtdeClientes");
    	sessao.removeAttribute("colecaoValorTotalDivida");
    	sessao.removeAttribute("colecaoOSEmpresaCobranca");
    	sessao.removeAttribute("colecaoOSRegistroAtendimento");
    	sessao.removeAttribute("quadraInicialInexistente");
    	sessao.removeAttribute("setorComercialOrigemInexistente");
    	sessao.removeAttribute("localidadeOrigemInexistente");
    	sessao.removeAttribute("quadraFinalInexistente");
    	sessao.removeAttribute("setorComercialDestinoInexistente");
    	sessao.removeAttribute("localidadeDestinoInexistente");

    	//3ª aba
    	sessao.removeAttribute("motivoInformado");
    	sessao.removeAttribute("habilitaNumerosOS");
    	
    }

}
