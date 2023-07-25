package gcom.gui.portal;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.cliente.ClienteLogin;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroClienteLogin;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

public class AcessarPortalAction extends GcomAction {

	private static final String PARAMETRO_ACAO = "acao";
	private static final String PARAMETRO_TELA = "portalTela";
	
	private static final String ATRIBUTO_MATRICULA = "portalMatricula";
	private static final String ATRIBUTO_NOME_CLIENTE = "portalNomeCliente";
	private static final String ATRIBUTO_CPF_OU_CNPJ = "portalCpfOuCnpjCliente";
	
	private static final String TELA_PORTAL = "portal";
	
	private static final String ACAO_LOGIN = "login";
	
	private static final String ATRIBUTO_ERRO = "portal-err-login";
	private static final String ATRIBUTO_ERRO_FORM = "portal-err-login-form";
	
	private HttpServletRequest request;
	private HttpSession sessao;
	private AcessarPortalActionForm form;
	private String acao;
	private ActionErrors errors;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.sessao = request.getSession(false);
		this.form = (AcessarPortalActionForm) actionForm;
		this.acao = request.getParameter(PARAMETRO_ACAO);

		if (acao == null) {
			form.reset();
		}
		
		if (verificarAcao(ACAO_LOGIN)) {
			errors = form.validate(sessao);
			
			if (errors.isEmpty()) {

				ClienteLogin cadastro = pesquisarCadastro();
				Cliente cliente = pesquisarCliente();

				if (cadastro != null && cliente != null) {
					sessao.setAttribute(ATRIBUTO_MATRICULA, cadastro.getImovel().getId());
					sessao.setAttribute(ATRIBUTO_NOME_CLIENTE, cliente.getNome());
					sessao.setAttribute(ATRIBUTO_CPF_OU_CNPJ, form.getCpfOuCnpj());
					
					return redirecionar(mapping);
				} else {
					errors.add(ATRIBUTO_ERRO, new ActionError("errors.portal.invalido", "Dados de Login"));
					request.setAttribute(ATRIBUTO_ERRO, errors);
				}
			} else {
				request.setAttribute(ATRIBUTO_ERRO_FORM, errors);
			}

			salvarErros();
		}
		
		return mapping.findForward("acessar-portal");
	}

	private ActionForward redirecionar(ActionMapping mapping) {
		String tela = request.getParameter(PARAMETRO_TELA);
		
		if (tela != null && !tela.trim().equals("")) {
			return mapping.findForward(tela);
		}
		
		return mapping.findForward(TELA_PORTAL);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ClienteLogin pesquisarCadastro() {
		Filtro filtro = new FiltroClienteLogin();
		filtro.adicionarParametro(new ParametroSimples(FiltroClienteLogin.CPF_CNPJ, form.getCpfOuCnpj()));
		filtro.adicionarParametro(new ParametroSimples(FiltroClienteLogin.SENHA, form.getCpfOuCnpj()));

		Collection colecao = getFachada().pesquisar(filtro, ClienteLogin.class.getName());

		return (ClienteLogin) Util.retonarObjetoDeColecao(colecao);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Cliente pesquisarCliente() {
		Filtro filtro = new FiltroCliente();
		filtro.adicionarParametro(new ParametroSimples(FiltroCliente.CPF, form.getCpfOuCnpj(), ParametroSimples.CONECTOR_OR));
		filtro.adicionarParametro(new ParametroSimples(FiltroCliente.CNPJ, form.getCpfOuCnpj()));

		Collection colecao = getFachada().pesquisar(filtro, FiltroCliente.class.getName());

		return (Cliente) Util.retonarObjetoDeColecao(colecao);
	}
	
	private boolean verificarAcao(String acao) {
		return this.acao != null && this.acao.trim().equals(acao);
	}
	
	private void salvarErros() {
		form.reset();
		saveErrors(request, errors);
	}
}
