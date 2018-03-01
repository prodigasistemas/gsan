package gcom.gui.portal;

import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.gui.GcomAction;
import gcom.util.ControladorException;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class EmitirSegundaViaContaAction extends GcomAction {

	private EmitirSegundaViaContaActionForm form;
	private HttpServletRequest request;
	private ActionErrors errors;

	private Collection<ContaValoresHelper> contas;
	private int quantidadeContas;
	private BigDecimal valorTotalContas;

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = mapping.findForward("segunda-via-conta");
		this.form = (EmitirSegundaViaContaActionForm) actionForm;
		this.request = request;
		
		if (isResetar()) {
			resetar();
		} else {
			validar();
			
			if (errors.isEmpty()) {
				try {
					ObterDebitoImovelOuClienteHelper helper = (ObterDebitoImovelOuClienteHelper) getFachada().obterDebitoImovelOuCliente(1, form.getMatricula(), null, null, "000101", "999912",
							Util.converteStringParaDate("01/01/0001"), Util.converteStringParaDate("31/12/9999"), 1, 1, 1, 1, 1, 1, 1, null);
					
					totalizarContas(helper);
					setForm();
					request.removeAttribute("erro-segunda-via");
				} catch (Exception e) {
					e.printStackTrace();
					errors.add("erro-segunda-via", new ActionError("errors.portal.erro_inesperado"));
					salvarErros();
					return retorno;
				}
			} else {
				resetar();
			}
		}

		return retorno;
	}

	private boolean isResetar() {
		String action = (String) request.getParameter("action");
		return action != null && action.equals("resetar");
	}

	private void salvarErros() {
		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("erro-segunda-via", true);
		} else {
			request.removeAttribute("erro-segunda-via");
		}
	}

	private void validar() {
		errors = form.validate();
		validarMatricula();
		saveErrors(request, errors);
		request.setAttribute("erro-segunda-via", true);
	}

	private boolean naoContemErro(String campo) {
		return !errors.get(campo).hasNext();
	}

	private void validarMatricula() {
		if (naoContemErro("erro-segunda-via") && getFachada().verificarExistenciaImovel(Integer.valueOf(form.getMatricula())) != 1) {
			errors.add("erro-segunda-via", new ActionError("errors.portal.invalida", "Matrícula do Imóvel"));
		}
	}

	private void resetar() {
		form.setMatricula(null);
		form.setNomeUsuario(null);
		form.setEndereco(null);
		form.setQuantidadeContas(null);
		form.setValorTotalContas(null);
		request.removeAttribute("contas");
	}

	private void totalizarContas(ObterDebitoImovelOuClienteHelper helper) {
		contas = helper.getColecaoContasValores();
		quantidadeContas = 0;
		valorTotalContas = new BigDecimal("0.00");
		for (ContaValoresHelper conta : contas) {
			valorTotalContas = valorTotalContas.add(conta.getValorTotalConta());
			quantidadeContas++;
		}
		
		request.setAttribute("contas", contas);
	}

	private void setForm() throws NumberFormatException, ControladorException {
		form.setNomeUsuario(getFachada().consultarClienteUsuarioImovel(Integer.valueOf(form.getMatricula())));
		form.setEndereco(getFachada().pesquisarEnderecoFormatado(Integer.valueOf(form.getMatricula())));
		form.setQuantidadeContas(String.valueOf(quantidadeContas));
		form.setValorTotalContas(Util.formatarMoedaReal(valorTotalContas));
	}
}