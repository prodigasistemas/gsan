package gcom.gui.cobranca;

import gcom.gui.WizardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class EfetuarParcelamentoDebitosWizardAction extends WizardAction {

	public ActionForward exibirProcesso1Action(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		return new ExibirEfetuarParcelamentoDebitosProcesso1Action()
			 	.execute(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	}

	public ActionForward exibirEfetuarParcelamentoDebitosProcesso1Action(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		return new ExibirEfetuarParcelamentoDebitosProcesso1Action()
			 	.execute(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	}

	public ActionForward exibirProcesso2Action(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		return new ExibirEfetuarParcelamentoDebitosProcesso2Action()
		 		.execute(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	}

	public ActionForward exibirEfetuarParcelamentoDebitosProcesso2Action(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		return new ExibirEfetuarParcelamentoDebitosProcesso2Action()
		 		.execute(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	}

	public ActionForward exibirProcesso3Action(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		 return new ExibirEfetuarParcelamentoDebitosProcesso3Action()
		 		.execute(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	}

	public ActionForward exibirEfetuarParcelamentoDebitosProcesso3Action(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		 return new ExibirEfetuarParcelamentoDebitosProcesso3Action()
		 		.execute(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	}

	public ActionForward exibirProcesso4Action(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		 return new ExibirEfetuarParcelamentoDebitosProcesso4Action()
		 		.execute(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	}
	
	public ActionForward exibirEfetuarParcelamentoDebitosProcesso4Action(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		 return new ExibirEfetuarParcelamentoDebitosProcesso4Action()
		 		.execute(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	}

	public ActionForward processarProcesso1Action(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		 new ProcessarEfetuarParcelamentoDebitosProcesso1Action()
		 		.execute(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		 return this.redirecionadorWizard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	}
	
	public ActionForward processarEfetuarParcelamentoDebitosProcesso1Action(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		 new ProcessarEfetuarParcelamentoDebitosProcesso1Action()
		 		.execute(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		 return this.redirecionadorWizard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	}

	public ActionForward processarProcesso2Action(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		 new ProcessarEfetuarParcelamentoDebitosProcesso2Action()
		 		.execute(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		 return this.redirecionadorWizard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	}
	
	public ActionForward processarEfetuarParcelamentoDebitosProcesso2Action(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		 new ProcessarEfetuarParcelamentoDebitosProcesso2Action()
		 		.execute(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		 return this.redirecionadorWizard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	}

	public ActionForward processarProcesso3Action(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		 new ProcessarEfetuarParcelamentoDebitosProcesso3Action()
		 		.execute(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		 return this.redirecionadorWizard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	}

	public ActionForward processarEfetuarParcelamentoDebitosProcesso3Action(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		 new ProcessarEfetuarParcelamentoDebitosProcesso3Action()
		 		.execute(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		 return this.redirecionadorWizard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	}
	
	public ActionForward processarProcesso4Action(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		 new ProcessarEfetuarParcelamentoDebitosProcesso4Action()
		 		.execute(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		 return this.redirecionadorWizard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	}
	
	public ActionForward processarEfetuarParcelamentoDebitosProcesso4Action(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		 new ProcessarEfetuarParcelamentoDebitosProcesso4Action()
		 		.execute(actionMapping, actionForm, httpServletRequest, httpServletResponse);
		 return this.redirecionadorWizard(actionMapping, actionForm, httpServletRequest, httpServletResponse);
	}

	public ActionForward concluirProcessoAction(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		 return new ConcluirEfetuarParcelamentoDebitosAction().execute(actionMapping,
		 		actionForm, httpServletRequest, httpServletResponse);
	}
}
