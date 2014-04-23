package gcom.gui.atendimentopublico.registroatendimento;

import gcom.gui.WizardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Esta classe tem por finalidade gerar as abas que serão responsáveis pelo processo de inserção de um
 * registro de atendimento
 *
 * @author Raphael Rossiter
 * @date 24/07/2006
 */
public class InserirRegistroAtendimentoWizardAction extends WizardAction {
	
	
	/*
	 * ABA Nº 01 - DADOS GERAIS
	 */
	public ActionForward exibirInserirRegistroAtendimentoDadosGeraisAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		return new ExibirInserirRegistroAtendimentoDadosGeraisAction().execute(
				actionMapping, actionForm, httpServletRequest,
				httpServletResponse);
	}
	
	public ActionForward inserirRegistroAtendimentoDadosGeraisAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		new InserirRegistroAtendimentoDadosGeraisAction().execute(actionMapping,
				actionForm, httpServletRequest, httpServletResponse);
		
		return this.redirecionadorWizard(actionMapping, actionForm,
				httpServletRequest, httpServletResponse);
	}
	
	
	
	/*
	 * ABA Nº 02 - LOCAL OCORRÊNCIA
	 */
	public ActionForward exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		return new ExibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction().execute(
				actionMapping, actionForm, httpServletRequest,
				httpServletResponse);
	}
	
	public ActionForward inserirRegistroAtendimentoDadosLocalOcorrenciaAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = null;
		
		retorno = new InserirRegistroAtendimentoDadosLocalOcorrenciaAction().execute(actionMapping,
				actionForm, httpServletRequest, httpServletResponse);
		
		if (httpServletRequest.getAttribute("telaOpcaoConsultar") != null){
			return retorno;
		}
		else{
			return this.redirecionadorWizard(actionMapping, actionForm,
					httpServletRequest, httpServletResponse);
		}
		
	}
	
	
	
	/*
	 * ABA Nº 03 - SOLICITANTE
	 */
	public ActionForward exibirInserirRegistroAtendimentoDadosSolicitanteAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		return new ExibirInserirRegistroAtendimentoDadosSolicitanteAction().execute(
				actionMapping, actionForm, httpServletRequest,
				httpServletResponse);
	}
	
	public ActionForward inserirRegistroAtendimentoDadosSolicitanteAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		new InserirRegistroAtendimentoDadosSolicitanteAction().execute(actionMapping,
				actionForm, httpServletRequest, httpServletResponse);
		
		return this.redirecionadorWizard(actionMapping, actionForm,
				httpServletRequest, httpServletResponse);
	}
	
	
	/*
	 * ABA Nº 04 - ANEXOS
	 */
	public ActionForward exibirInserirRegistroAtendimentoAnexosAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		return new ExibirInserirRegistroAtendimentoAnexosAction().execute(
				actionMapping, actionForm, httpServletRequest,
				httpServletResponse);
	}
	
	public ActionForward inserirRegistroAtendimentoAnexosAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		new InserirRegistroAtendimentoAnexosAction().execute(actionMapping,
				actionForm, httpServletRequest, httpServletResponse);
		
		return this.redirecionadorWizard(actionMapping, actionForm,
				httpServletRequest, httpServletResponse);
	}
	
	/*
	 * CONCLUIR
	 */
	public ActionForward concluirInserirRegistroAtendimentoAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		return new ConcluirInserirRegistroAtendimentoAction().execute(actionMapping,
				actionForm, httpServletRequest, httpServletResponse);
	}
	
	
	
	/*
	 * CANCELAR
	 */
	public ActionForward cancelarInserirRegistroAtendimentoAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		return new CancelarInserirRegistroAtendimentoAction().execute(actionMapping,
				actionForm, httpServletRequest, httpServletResponse);
	}

}
