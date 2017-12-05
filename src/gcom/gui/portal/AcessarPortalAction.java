package gcom.gui.portal;

import gcom.gui.GcomAction;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AcessarPortalAction extends GcomAction {

	private AcessarPortalActionForm form;
	private HttpSession sessao;
	private ActionErrors errors;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		form = (AcessarPortalActionForm) actionForm;
		sessao = request.getSession(false);
		boolean validarCpfCnpj = (Boolean) sessao.getAttribute("validarCpfCnpj");

		validar(mapping, validarCpfCnpj);
		if (errors.isEmpty()) {
			sessao.setAttribute("nomeUsuario", getFachada().consultarClienteUsuarioImovel(Integer.valueOf(form.getMatricula())));
			sessao.setAttribute("matricula", form.getMatricula());

			if (isCpfInformado())
				sessao.setAttribute("cpfCnpj", form.getCpfCnpj());

			return mapping.findForward((String) sessao.getAttribute("action"));
		} else {
			saveErrors(request, errors);
			reset();
			return mapping.findForward("acessar-portal");
		}
	}

	private boolean isCpfInformado() {
		return form.getCpfCnpj() != null && !form.getCpfCnpj().trim().equals("");
	}

	private void reset() {
		sessao.removeAttribute("matricula");
		sessao.removeAttribute("cpfCnpj");
	}

	private void validar(ActionMapping mapping, boolean validarCpfCnpj) {
		errors = form.validate(sessao, validarCpfCnpj);
		validarMatricula();
		if (validarCpfCnpj)
			validarCpfCnpj();
	}

	private boolean naoContemErro(String campo) {
		return !errors.get(campo).hasNext();
	}

	private void validarMatricula() {
		if (naoContemErro("matricula") && getFachada().verificarExistenciaImovel(Integer.valueOf(form.getMatricula())) != 1) {
			errors.add("matricula", new ActionError("errors.portal.invalida", "Matrícula"));
		}
	}

	private void validarCpfCnpj() {
		boolean valido = false;
		if (form.getCpfCnpj().length() == 11) {
			valido = Util.validacaoCPF(form.getCpfCnpj());
		} else {
			valido = Util.validacaoCNPJ(form.getCpfCnpj());
		}

		if (naoContemErro("cpfCnpj") && !valido) {
			errors.add("cpfCnpj", new ActionError("errors.portal.invalido", "CPF/CNPJ"));
		}

		boolean cpfCnpjCadastrado = getFachada().isCpfCnpjCadastrado(form.getMatricula(), form.getCpfCnpj());
		if (naoContemErro("cpfCnpj") && !cpfCnpjCadastrado)
			errors.add("cpfCnpj", new ActionError("errors.portal.nao_cadastrado", "CPF/CNPJ"));
	}
}
