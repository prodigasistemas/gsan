package gcom.gui.seguranca.acesso.usuario;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.Usuario;
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
 * Action que exibe o menu
 * 
 * @author Administrador
 * @date 02/05/2006
 */
public class InserirUsuarioAcessosUsuarioAction extends GcomAction {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		InserirUsuarioDadosGeraisActionForm form = (InserirUsuarioDadosGeraisActionForm) actionForm;

		ActionForward retorno = actionMapping
				.findForward("gerenciadorProcesso");

		HttpSession sessao = httpServletRequest.getSession(false);

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// Usuario que vai ser cadastrado no sistema, usado só nessa
		// funcionalidade
		Usuario usuarioCadastrar = (Usuario) sessao
				.getAttribute("usuarioCadastrar");
		if (usuarioCadastrar == null)
			usuarioCadastrar = new Usuario();

		if (!"".equals(form.getAbrangencia())) {
			if (!(usuarioCadastrar.getUsuarioAbrangencia() != null
					&& usuarioCadastrar.getUsuarioAbrangencia().getId() != null && usuarioCadastrar
					.getUsuarioAbrangencia().getId().toString().equals(
							form.getAbrangencia()))) {

				FiltroUsuarioAbrangencia filtroUsuarioAbrangencia = new FiltroUsuarioAbrangencia();
				filtroUsuarioAbrangencia
						.adicionarParametro(new ParametroSimples(
								FiltroUsuarioAbrangencia.ID, form
										.getAbrangencia()));
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroUsuarioAbrangencia,
						UsuarioAbrangencia.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					usuarioCadastrar
							.setUsuarioAbrangencia((UsuarioAbrangencia) coll
									.iterator().next());
				}
			}
		} else {
			usuarioCadastrar.setUsuarioAbrangencia(null);
		}

		if (!"".equals(form.getGerenciaRegional())) {
			if (!(usuarioCadastrar.getGerenciaRegional() != null
					&& usuarioCadastrar.getGerenciaRegional().getId() != null && usuarioCadastrar
					.getGerenciaRegional().getId().toString().equals(
							form.getGerenciaRegional()))) {

				FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
				filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
						FiltroGerenciaRegional.ID, form.getGerenciaRegional()));
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroGerenciaRegional,
						GerenciaRegional.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					usuarioCadastrar
							.setGerenciaRegional((GerenciaRegional) coll
									.iterator().next());
				}
			}
		} else {
			usuarioCadastrar.setGerenciaRegional(null);
		}

		if (!"".equals(form.getUnidadeNegocio())) {
			if (!(usuarioCadastrar.getUnidadeNegocio() != null
					&& usuarioCadastrar.getUnidadeNegocio().getId() != null && usuarioCadastrar
					.getUnidadeNegocio().getId().toString().equals(
							form.getUnidadeNegocio()))) {

				FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
				filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
						FiltroUnidadeNegocio.ID, form.getUnidadeNegocio()));
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroUnidadeNegocio,
						UnidadeNegocio.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					usuarioCadastrar.setUnidadeNegocio((UnidadeNegocio) coll
							.iterator().next());
				}
			}
		} else {
			usuarioCadastrar.setUnidadeNegocio(null);
		}

		if (!"".equals(form.getIdElo())) {
			if (!(usuarioCadastrar.getLocalidadeElo() != null
					&& usuarioCadastrar.getLocalidadeElo().getId() != null && usuarioCadastrar
					.getLocalidadeElo().getId().toString().equals(
							form.getIdElo()))) {

				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, form.getIdElo()));
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroLocalidade, Localidade.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					usuarioCadastrar.setLocalidadeElo((Localidade) coll
							.iterator().next());
				}
			}
		} else {
			usuarioCadastrar.setLocalidadeElo(null);
		}

		if (!"".equals(form.getIdLocalidade())) {
			if (!(usuarioCadastrar.getLocalidade() != null
					&& usuarioCadastrar.getLocalidade().getId() != null && usuarioCadastrar
					.getLocalidade().getId().toString().equals(
							form.getIdLocalidade()))) {

				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, form.getIdLocalidade()));
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroLocalidade, Localidade.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					usuarioCadastrar.setLocalidade((Localidade) coll.iterator()
							.next());
				}
			}
		} else {
			usuarioCadastrar.setLocalidade(null);
		}

		String[] grupo = form.getGrupo();

		sessao.setAttribute("grupo", grupo);
		sessao.setAttribute("usuario", usuario);
		sessao.setAttribute("usuarioCadastrar", usuarioCadastrar);

		return retorno;
	}
}
