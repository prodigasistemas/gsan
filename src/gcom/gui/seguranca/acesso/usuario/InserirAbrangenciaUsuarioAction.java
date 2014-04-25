package gcom.gui.seguranca.acesso.usuario;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.util.ConstantesSistema;

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
public class InserirAbrangenciaUsuarioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		InserirAbrangenciaUsuarioActionForm inserirAbrangenciaUsuarioActionForm = (InserirAbrangenciaUsuarioActionForm) actionForm;

		UsuarioAbrangencia usuarioAbrangencia = new UsuarioAbrangencia();

		usuarioAbrangencia.setDescricao(inserirAbrangenciaUsuarioActionForm
				.getDescricaoUsuarioSituacao());
		usuarioAbrangencia
				.setDescricaoAbreviada(inserirAbrangenciaUsuarioActionForm
						.getDescricaoAbreviada());
		// Se Abrangeência Superior for nulo vai atribuir o valor igual a -1

		if (inserirAbrangenciaUsuarioActionForm.getAbrangenciaSuperior()
				.equalsIgnoreCase("-1")) {
			inserirAbrangenciaUsuarioActionForm.setAbrangenciaSuperior(null);
		}
		// Se Abrangência Superior for nulo Abrangência Superior vai ser ID do próprio Usuario 

		if (inserirAbrangenciaUsuarioActionForm.getAbrangenciaSuperior() != null
				&& !inserirAbrangenciaUsuarioActionForm
						.getAbrangenciaSuperior().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			UsuarioAbrangencia usuarioAbrangenciaSuperior = new UsuarioAbrangencia();
			usuarioAbrangenciaSuperior.setId(Integer
					.parseInt(inserirAbrangenciaUsuarioActionForm
							.getAbrangenciaSuperior()));
			usuarioAbrangencia
					.setUsuarioAbrangenciaSuperior(usuarioAbrangenciaSuperior);

		}

		usuarioAbrangencia.setIndicadorUso(new Short((short) 1));

		Integer id = (Integer) fachada
				.inserirAbrangenciaUsuario(usuarioAbrangencia);

		montarPaginaSucesso(httpServletRequest, "Abrangência Usuario" + id
				+ " inserida com sucesso.",
				"Inserir outra Abrangência do Usuário",
				"exibirInserirAbrangenciaUsuarioAction.do?menu=sim",
				"exibirAtualizarAbrangenciaUsuarioAction.do?idRegistroAtualizacao="
				+ id, "Atualizar Abrangencia do Usuário inserida");



		return retorno;
	}
}
