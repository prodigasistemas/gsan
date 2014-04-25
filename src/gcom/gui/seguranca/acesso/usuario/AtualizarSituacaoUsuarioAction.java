package gcom.gui.seguranca.acesso.usuario;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarSituacaoUsuarioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarSituacaoUsuarioActionForm atualizarSituacaoUsuarioActionForm = (AtualizarSituacaoUsuarioActionForm) actionForm;

		UsuarioSituacao usuarioSituacao = new UsuarioSituacao();

		usuarioSituacao = (UsuarioSituacao) sessao
				.getAttribute("usuarioSituacaoAtualizar");

		usuarioSituacao
				.setDescricaoUsuarioSituacao(atualizarSituacaoUsuarioActionForm
						.getDescricaoUsuarioSituacao());
		usuarioSituacao
				.setDescricaoAbreviada(atualizarSituacaoUsuarioActionForm
						.getDescricaoAbreviada());
		usuarioSituacao.setId(new Integer(atualizarSituacaoUsuarioActionForm
				.getId()));
		usuarioSituacao.setIndicadorUso(new Short(
				atualizarSituacaoUsuarioActionForm.getIndicadorUso()));
		usuarioSituacao.setIndicadorUsoSistema(new Short(
				atualizarSituacaoUsuarioActionForm.getIndicadorUsoSistema()));

		Collection<UsuarioSituacao> colecaoUsuarioSituacao = null;

		if (sessao.getAttribute("colecaoUsuarioSituacao") != null) {
			colecaoUsuarioSituacao = (Collection) sessao
					.getAttribute("colecaoUsuarioSituacao");
		}

		fachada.atualizarSituacaoUsuario(usuarioSituacao,
				colecaoUsuarioSituacao);

		montarPaginaSucesso(httpServletRequest, "Situação Usuario "
				+ usuarioSituacao.getId()
				+ " atualizado com sucesso.",
				"Realizar outra Manutenção de Situação Usuario",
				"exibirManterSituacaoUsuarioAction.do");

		return retorno;

	}

}
