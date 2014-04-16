package gcom.gui.seguranca.acesso.usuario;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Thiago Tenório
 * @date 06/05/2006
 */
public class InserirSituacaoUsuarioAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		InserirSituacaoUsuarioActionForm inserirSituacaoUsuarioActionForm = (InserirSituacaoUsuarioActionForm) actionForm;

		UsuarioSituacao usuarioSituacao = new UsuarioSituacao();

		usuarioSituacao
				.setDescricaoUsuarioSituacao(inserirSituacaoUsuarioActionForm
						.getDescricaoUsuarioSituacao());
		usuarioSituacao.setDescricaoAbreviada(inserirSituacaoUsuarioActionForm
				.getDescricaoAbreviada());
		if (inserirSituacaoUsuarioActionForm.getIndicadorUsoSistema() != null) {
			usuarioSituacao.setIndicadorUsoSistema(new Short(
					inserirSituacaoUsuarioActionForm.getIndicadorUsoSistema()));
		}

		usuarioSituacao.setIndicadorUso(new Short("1"));
		
		Integer id = (Integer) fachada.inserirSituacaoUsuario(usuarioSituacao);

		montarPaginaSucesso(httpServletRequest, "Situacao Usuario "
				+ id + " inserida com sucesso!", "Inserir outra Situacao do Usuário",
				"exibirInserirSituacaoUsuarioAction.do?menu=sim",
				"exibirAtualizarSituacaoUsuarioAction.do?idRegistroAtualizacao="
						+ id, "Atualizar Situação do Usuário Inserida");

		return retorno;

	}
}
