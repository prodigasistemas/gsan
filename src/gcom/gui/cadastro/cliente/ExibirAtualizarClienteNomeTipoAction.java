package gcom.gui.cadastro.cliente;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuarioPemissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

/**
 * Action que inicializa a primeira página do processo de atualizar cliente
 */
public class ExibirAtualizarClienteNomeTipoAction extends GcomAction {

	private HttpServletRequest request;
	private HttpSession session;
	private Usuario usuario;
	private DynaValidatorForm form;

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.session = request.getSession(false);
		this.usuario = (Usuario) session.getAttribute("usuarioLogado");

		setarFormulario(request);

		verificarPermissaoAlterarNomeCliente();
		verificarCpfCnpj();
		verificarPermissaoAlterarAcrescimos();
		verificarLabelsDescricao();
		pesquisarColecaoTipoPessoa();
		verificarPermissaoVisualizarVencimentoConta();
		verificarPermissaoEspecial();
		verificarPopup();

		return mapping.findForward("atualizarClienteNomeTipo");
	}

	private void verificarPermissaoAlterarNomeCliente() {
		request.setAttribute("temPermissaoAlterarNomeCliente", getFachada().verificarPermissaoAlterarNomeCliente(usuario));
	}

	private void verificarCpfCnpj() {
		session.removeAttribute("temCpfCnpj");
		
		String cpf = (String) form.get("cpf");
		String cnpj = (String) form.get("cnpj");

		if ((cpf != null && !cpf.equals("")) || (cnpj != null && !cnpj.equals("")))
			session.setAttribute("temCpfCnpj", "true");
	}

	private void verificarPermissaoAlterarAcrescimos() {
		request.setAttribute("temPermissaoAlterarAcrescimos", getFachada().verificarPermissaoValAcrescimosImpontualidade(usuario));
	}

	private void verificarLabelsDescricao() {
		SistemaParametro sistemaParametro = getFachada().pesquisarParametrosDoSistema();

		String descricao = "";
		String descricaoAbreviada = "";

		if (sistemaParametro.getIndicadorUsoNMCliReceitaFantasia().intValue() == ConstantesSistema.INDICADOR_USO_ATIVO.intValue()) {
			session.setAttribute("indicadorNomeFantasia", true);
			descricao = "Nome na Receita Federal:";
			descricaoAbreviada = "Nome de Fantasia:";

		} else {
			descricao = "Nome: ";
			descricaoAbreviada = "Nome Abreviado: ";
			session.removeAttribute("indicadorNomeFantasia");
		}

		session.setAttribute("descricao", descricao);
		session.setAttribute("descricaoAbreviada", descricaoAbreviada);
	}

	private void setarFormulario(HttpServletRequest request) {
		if (request.getAttribute("ClienteActionForm") != null) {
			session.setAttribute("ClienteActionForm", request.getAttribute("ClienteActionForm"));
		}

		this.form = (DynaValidatorForm) session.getAttribute("ClienteActionForm");
	}

	private void pesquisarColecaoTipoPessoa() {
		String indicadorPessoaFisicaJuridica = (String) form.get("indicadorPessoaFisicaJuridica");

		FiltroClienteTipo filtro = new FiltroClienteTipo();
		filtro.adicionarParametro(new ParametroSimples(FiltroClienteTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtro.adicionarParametro(new ParametroSimples(FiltroClienteTipo.INDICADOR_PESSOA_FISICA_JURIDICA, indicadorPessoaFisicaJuridica));
		filtro.setCampoOrderBy(FiltroClienteTipo.DESCRICAO);

		request.setAttribute("colecaoTipoPessoa", Fachada.getInstancia().pesquisar(filtro, ClienteTipo.class.getName()));
	}

	private void verificarPermissaoVisualizarVencimentoConta() {
		request.setAttribute("temPermissaoVisualizarDiaVencimentoContaCliente", getFachada().verificarPermissaoVisualizarDiaVencimentoContaCliente(usuario));
	}

	@SuppressWarnings("rawtypes")
	private void verificarPermissaoEspecial() {
		FiltroUsuarioPemissaoEspecial filtro = new FiltroUsuarioPemissaoEspecial();
		filtro.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
		filtro.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.INSERIR_MANTER_CLIENTE_SEM_NEGATIVACAO));

		Collection colecao = getFachada().pesquisar(filtro, UsuarioPermissaoEspecial.class.getName());

		session.removeAttribute("permissaoEspecial");

		if (colecao != null && !colecao.isEmpty()) {
			session.setAttribute("permissaoEspecial", "true");
		}
	}

	private void verificarPopup() {
		if (request.getParameter("POPUP") != null) {
			if (request.getParameter("POPUP").equals("true")) {
				session.setAttribute("POPUP", "true");
			} else {
				session.setAttribute("POPUP", "false");
			}
		} else if (session.getAttribute("POPUP") == null) {
			session.setAttribute("POPUP", "false");
		}
	}
}
