package gcom.gui.portal;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.cliente.ClienteLogin;
import gcom.cadastro.cliente.FiltroClienteLogin;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

public class ValidacaoEmailAction extends GcomAction {

	public static final String ATRIBUTO_ERRO = "err-confirmacao-email";

	private HttpServletRequest request;
	private ActionErrors errors;

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.errors = new ActionErrors();

		try {
			Integer id = Integer.parseInt(request.getParameter("id"));

			ClienteLogin cadastro = pesquisarCadastro(id);

			if (cadastro != null) {
				cadastro.setIndicadorEmailConfirmado(ConstantesSistema.SIM);
				cadastro.setUltimaAlteracao(new Date());
				getFachada().atualizar(cadastro);
			} else {
				salvarErro();
			}

		} catch (Exception e) {
			e.printStackTrace();
			salvarErro();
		}

		return mapping.findForward("validacao-email");
	}

	@SuppressWarnings("unchecked")
	private ClienteLogin pesquisarCadastro(Integer id) {
		FiltroClienteLogin filtro = new FiltroClienteLogin();
		filtro.adicionarParametro(new ParametroSimples(FiltroClienteLogin.ID, id));

		return (ClienteLogin) Util
				.retonarObjetoDeColecao(getFachada().pesquisar(filtro, ClienteLogin.class.getName()));
	}

	private void salvarErro() {
		errors.add(ATRIBUTO_ERRO, new ActionError("errors.portal.erro_inesperado"));
		saveErrors(request, errors);
		request.setAttribute(ATRIBUTO_ERRO, errors);
	}
}