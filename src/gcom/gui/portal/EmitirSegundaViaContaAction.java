package gcom.gui.portal;

import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class EmitirSegundaViaContaAction extends GcomAction {

	private EmitirSegundaViaContaActionForm form;

	private String matricula;

	private Collection<ContaValoresHelper> contas;
	private BigDecimal totalContas;
	private BigDecimal totalDebitos;
	private BigDecimal totalAcrescimos;

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = mapping.findForward("segunda-via-conta");

		HttpSession sessao = request.getSession(true);
		form = (EmitirSegundaViaContaActionForm) actionForm;
		resetar(request);

		if (isMatriculaValida(sessao)) {
			try {
				ObterDebitoImovelOuClienteHelper helper = (ObterDebitoImovelOuClienteHelper) getFachada().obterDebitoImovelOuCliente(1, matricula, null, null, "000101", "999912",
						Util.converteStringParaDate("01/01/0001"), Util.converteStringParaDate("31/12/9999"), 1, 1, 1, 1, 1, 1, 1, null);

				totalizarContas(helper, request);
				totalizarDebitos(helper);
			} catch (Exception e) {
				request.setAttribute("erroSistema", true);
				e.printStackTrace();
			}

			setForm();
		} else {
			retorno = mapping.findForward("acessar-portal");
			sessao.setAttribute("action", "segunda-via-conta");
			sessao.setAttribute("validarCpfCnpj", false);
		}

		return retorno;
	}

	private void resetar(HttpServletRequest request) {
		form.setMatricula(null);
		form.setData(null);
		form.setValorDebito(null);
		form.setValorDebitoCobrado(null);
		
		request.removeAttribute("totalContas");
		request.removeAttribute("contas");
		request.removeAttribute("erroSistema");
		
	}

	private void totalizarDebitos(ObterDebitoImovelOuClienteHelper helper) {
		Collection<DebitoACobrar> debitos = helper.getColecaoDebitoACobrar();
		totalDebitos = new BigDecimal("0.00");
		for (DebitoACobrar debito : debitos) {
			totalDebitos = totalDebitos.add(debito.getValorTotalComBonus());
		}
	}

	private void totalizarContas(ObterDebitoImovelOuClienteHelper helper, HttpServletRequest request) {
		contas = helper.getColecaoContasValores();
		totalContas = new BigDecimal("0.00");
		totalAcrescimos = new BigDecimal("0.00");
		for (ContaValoresHelper conta : contas) {
			totalContas = totalContas.add(conta.getValorTotalConta());
			totalAcrescimos = totalAcrescimos.add(conta.getValorTotalContaValores());
		}

		request.setAttribute("totalContas", totalContas);
		request.setAttribute("contas", contas);
	}

	private boolean isMatriculaValida(HttpSession sessao) {
		matricula = (String) sessao.getAttribute("matricula");
		return matricula != null && !matricula.equals("");
	}

	private void setForm() {
		form.setMatricula(matricula);
		form.setData(Util.formatarData(new Date()));
		form.setValorDebito(Util.formatarMoedaReal(totalContas));
		form.setValorDebitoCobrado(Util.formatarMoedaReal(totalDebitos.add(totalAcrescimos)));
	}
}