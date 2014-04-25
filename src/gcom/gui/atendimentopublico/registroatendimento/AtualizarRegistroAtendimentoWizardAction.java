package gcom.gui.atendimentopublico.registroatendimento;

import gcom.gui.WizardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade gerar as abas que serão responsáveis pelo
 * processo de atualização de um registro de atendimento
 * 
 * @author Sávio Luiz
 * @date 24/07/2006
 */
public class AtualizarRegistroAtendimentoWizardAction extends WizardAction {

	/*
	 * ABA Nº 01 - DADOS GERAIS
	 */
	public ActionForward exibirAtualizarRegistroAtendimentoDadosGeraisAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		return new ExibirAtualizarRegistroAtendimentoDadosGeraisAction()
				.execute(actionMapping, actionForm, httpServletRequest,
						httpServletResponse);
	}

	public ActionForward atualizarRegistroAtendimentoDadosGeraisAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		new AtualizarRegistroAtendimentoDadosGeraisAction().execute(
				actionMapping, actionForm, httpServletRequest,
				httpServletResponse);

		return this.redirecionadorWizard(actionMapping, actionForm,
				httpServletRequest, httpServletResponse);
	}

	/*
	 * ABA Nº 02 - LOCAL OCORRÊNCIA
	 */
	public ActionForward exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		return new ExibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction()
				.execute(actionMapping, actionForm, httpServletRequest,
						httpServletResponse);
	}

	public ActionForward atualizarRegistroAtendimentoDadosLocalOcorrenciaAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		retorno = new AtualizarRegistroAtendimentoDadosLocalOcorrenciaAction()
				.execute(actionMapping, actionForm, httpServletRequest,
						httpServletResponse);
		if (httpServletRequest.getAttribute("telaOpcaoConsultar") != null) {
			return retorno;
		}
		return this.redirecionadorWizard(actionMapping, actionForm,
				httpServletRequest, httpServletResponse);

	}

	/*
	 * ABA Nº 03 - SOLICITANTE
	 */
	public ActionForward exibirAtualizarRegistroAtendimentoDadosSolicitanteAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		return new ExibirAtualizarRegistroAtendimentoDadosSolicitanteAction()
				.execute(actionMapping, actionForm, httpServletRequest,
						httpServletResponse);
	}

	public ActionForward atualizarRegistroAtendimentoDadosSolicitanteAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		new AtualizarRegistroAtendimentoDadosSolicitanteAction().execute(
				actionMapping, actionForm, httpServletRequest,
				httpServletResponse);

		return this.redirecionadorWizard(actionMapping, actionForm,
				httpServletRequest, httpServletResponse);
	}
	
	/*
	 * ABA Nº 04 - ANEXO
	 */
	public ActionForward exibirAtualizarRegistroAtendimentoAnexosAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		return new ExibirAtualizarRegistroAtendimentoAnexosAction()
				.execute(actionMapping, actionForm, httpServletRequest,
						httpServletResponse);
	}

	public ActionForward atualizarRegistroAtendimentoAnexosAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		new AtualizarRegistroAtendimentoAnexosAction().execute(
				actionMapping, actionForm, httpServletRequest,
				httpServletResponse);

		return this.redirecionadorWizard(actionMapping, actionForm,
				httpServletRequest, httpServletResponse);
	}

	/*
	 * CONCLUIR
	 */
	public ActionForward concluirAtualizarRegistroAtendimentoAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		return new ConcluirAtualizarRegistroAtendimentoAction().execute(
				actionMapping, actionForm, httpServletRequest,
				httpServletResponse);
	}

	/*
	 * CANCELAR
	 */
	public ActionForward cancelarAtualizarRegistroAtendimentoAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		return new CancelarAtualizarRegistroAtendimentoAction().execute(
				actionMapping, actionForm, httpServletRequest,
				httpServletResponse);
	}

}
