package gcom.gui.seguranca.acesso.usuario;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarAbrangenciaUsuarioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarAbrangenciaUsuarioActionForm atualizarAbrangenciaUsuarioActionForm = (AtualizarAbrangenciaUsuarioActionForm) actionForm;

		UsuarioAbrangencia usuarioAbrangencia = (UsuarioAbrangencia) sessao
				.getAttribute("usuarioAbrangenciaAtualizar");

		usuarioAbrangencia.setDescricao(atualizarAbrangenciaUsuarioActionForm
				.getDescricao());
		usuarioAbrangencia
				.setDescricaoAbreviada(atualizarAbrangenciaUsuarioActionForm
						.getDescricaoAbreviada());
		usuarioAbrangencia.setId(new Integer(
				atualizarAbrangenciaUsuarioActionForm.getId()));
		usuarioAbrangencia.setIndicadorUso(new Short(
				atualizarAbrangenciaUsuarioActionForm.getIndicadorUso()));

		if (atualizarAbrangenciaUsuarioActionForm
				.getUsuarioAbrangenciaSuperior() != null) {

			Integer idUsuarioAbrangenciaSuperior = new Integer(
					atualizarAbrangenciaUsuarioActionForm
							.getUsuarioAbrangenciaSuperior());

			UsuarioAbrangencia usuarioAbrangenciaSuperior = new UsuarioAbrangencia();

			if (idUsuarioAbrangenciaSuperior
					.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				idUsuarioAbrangenciaSuperior = Integer
						.parseInt(atualizarAbrangenciaUsuarioActionForm.getId());
			}

			usuarioAbrangenciaSuperior.setId(idUsuarioAbrangenciaSuperior);

			usuarioAbrangencia
					.setUsuarioAbrangenciaSuperior(usuarioAbrangenciaSuperior);
		}

		fachada.atualizarAbrangenciaUsuario(usuarioAbrangencia);

		montarPaginaSucesso(httpServletRequest, "Usuario Abrangência "
				+ usuarioAbrangencia.getId().toString()
				+ " atualizado com sucesso.",
				"Realizar outra Manutenção de Abrangência Usuario",
				"exibirManterAbrangenciaUsuarioAction.do");

		return retorno;

	}

}
