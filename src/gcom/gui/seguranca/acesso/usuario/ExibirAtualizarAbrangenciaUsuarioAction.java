package gcom.gui.seguranca.acesso.usuario;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroAbrangenciaUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Thiago Tenório
 * @date 15/05/2006
 */
public class ExibirAtualizarAbrangenciaUsuarioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarAbrangenciaUsuario");

		AtualizarAbrangenciaUsuarioActionForm atualizarAbrangenciaUsuarioActionForm = (AtualizarAbrangenciaUsuarioActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroUsuarioAbrangencia filtroUsuarioAbrangencia = new FiltroUsuarioAbrangencia();

		Collection<UsuarioAbrangencia> colecaoAbrangenciaSuperior = fachada.pesquisar(
				filtroUsuarioAbrangencia, UsuarioAbrangencia.class.getName());

		if (colecaoAbrangenciaSuperior != null
				&& !colecaoAbrangenciaSuperior.isEmpty()) {
			httpServletRequest.setAttribute("colecaoAbrangenciaSuperior",
					colecaoAbrangenciaSuperior);
		} else {
			throw new ActionServletException("atencao.atualizacao.timestamp");
		}

		if (sessao.getAttribute("usuarioAbrangencia") != null) {

			UsuarioAbrangencia usuarioAbrangencia = (UsuarioAbrangencia) sessao
					.getAttribute("usuarioAbragencia");
			atualizarAbrangenciaUsuarioActionForm
					.setDescricao(usuarioAbrangencia.getDescricao());
			atualizarAbrangenciaUsuarioActionForm
					.setDescricaoAbreviada(usuarioAbrangencia
							.getDescricaoAbreviada());
			atualizarAbrangenciaUsuarioActionForm.setIndicadorUso(""
					+ usuarioAbrangencia.getIndicadorUso());

			atualizarAbrangenciaUsuarioActionForm.setId(""
					+ usuarioAbrangencia.getId());

		} else {

			String usuarioAbrangenciaID = httpServletRequest
					.getParameter("idRegistroAtualizacao");

			FiltroAbrangenciaUsuario filtroAbrangenciaUsuario = new FiltroAbrangenciaUsuario();

			filtroAbrangenciaUsuario.adicionarParametro(new ParametroSimples(
					FiltroAbrangenciaUsuario.ID, usuarioAbrangenciaID));

			Collection<UsuarioAbrangencia> colecaoUsuarioAbrangencia = fachada
					.pesquisar(filtroAbrangenciaUsuario,
							UsuarioAbrangencia.class.getName());

			if (colecaoUsuarioAbrangencia == null
					|| colecaoUsuarioAbrangencia.isEmpty()) {
				throw new ActionServletException(
						"atencao.atualizacao.timestamp");
			}

			UsuarioAbrangencia usuarioAbrangencia = colecaoUsuarioAbrangencia.iterator().next();

			atualizarAbrangenciaUsuarioActionForm
					.setDescricao(usuarioAbrangencia.getDescricao());
			atualizarAbrangenciaUsuarioActionForm
					.setDescricaoAbreviada(usuarioAbrangencia
							.getDescricaoAbreviada());
			atualizarAbrangenciaUsuarioActionForm.setIndicadorUso(""
					+ usuarioAbrangencia.getIndicadorUso());

			if (usuarioAbrangencia.getUsuarioAbrangenciaSuperior() != null) {
				atualizarAbrangenciaUsuarioActionForm
						.setUsuarioAbrangenciaSuperior(usuarioAbrangencia
								.getUsuarioAbrangenciaSuperior().getId());
			}

			atualizarAbrangenciaUsuarioActionForm.setId(""
					+ usuarioAbrangencia.getId());

			sessao.setAttribute("usuarioAbrangenciaAtualizar",
					usuarioAbrangencia);
		}

		return retorno;
	}
}
