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
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.util.ConstantesSistema;
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
 * @author Sávio Luiz
 * @date 02/05/2006
 */
public class AtualizarUsuarioAcessosUsuarioAction extends GcomAction {

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

		AtualizarUsuarioDadosGeraisActionForm form = (AtualizarUsuarioDadosGeraisActionForm) actionForm;

		ActionForward retorno = actionMapping
				.findForward("gerenciadorProcesso");

		HttpSession sessao = httpServletRequest.getSession(false);

		// Usuario que vai ser cadastrado no sistema, usado só nessa
		// funcionalidade
		Usuario usuarioParaAtualizar = (Usuario) sessao
				.getAttribute("usuarioParaAtualizar");
		if (usuarioParaAtualizar == null)
			usuarioParaAtualizar = new Usuario();

		if (!"".equals(form.getAbrangencia())) {
			if (!(usuarioParaAtualizar.getUsuarioAbrangencia() != null
					&& usuarioParaAtualizar.getUsuarioAbrangencia().getId() != null && usuarioParaAtualizar
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
					usuarioParaAtualizar
							.setUsuarioAbrangencia((UsuarioAbrangencia) coll
									.iterator().next());
				}
			}
		} else {
			usuarioParaAtualizar.setUsuarioAbrangencia(null);
		}

		if (!"".equals(form.getGerenciaRegional())) {
			if (!(usuarioParaAtualizar.getGerenciaRegional() != null
					&& usuarioParaAtualizar.getGerenciaRegional().getId() != null && usuarioParaAtualizar
					.getGerenciaRegional().getId().toString().equals(
							form.getGerenciaRegional()))) {

				FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
				filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
						FiltroGerenciaRegional.ID, form.getGerenciaRegional()));
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroGerenciaRegional,
						GerenciaRegional.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					usuarioParaAtualizar
							.setGerenciaRegional((GerenciaRegional) coll
									.iterator().next());
				}
			}
		} else {
			usuarioParaAtualizar.setGerenciaRegional(null);
		}

		if (!"".equals(form.getUnidadeNegocio())) {
			if (!(usuarioParaAtualizar.getUnidadeNegocio() != null
					&& usuarioParaAtualizar.getUnidadeNegocio().getId() != null && usuarioParaAtualizar
					.getUnidadeNegocio().getId().toString().equals(
							form.getUnidadeNegocio()))) {

				FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
				filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
						FiltroUnidadeNegocio.ID, form.getUnidadeNegocio()));
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroUnidadeNegocio,
						UnidadeNegocio.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					usuarioParaAtualizar.setUnidadeNegocio((UnidadeNegocio) coll
							.iterator().next());
				}
			}
		} else {
			usuarioParaAtualizar.setUnidadeNegocio(null);
		}

		if (!"".equals(form.getIdElo())) {
			if (!(usuarioParaAtualizar.getLocalidadeElo() != null
					&& usuarioParaAtualizar.getLocalidadeElo().getId() != null && usuarioParaAtualizar
					.getLocalidadeElo().getId().toString().equals(
							form.getIdElo()))) {

				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, form.getIdElo()));
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroLocalidade, Localidade.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					usuarioParaAtualizar.setLocalidadeElo((Localidade) coll
							.iterator().next());
				}
			}
		} else {
			usuarioParaAtualizar.setLocalidadeElo(null);
		}

		if (!"".equals(form.getIdLocalidade())) {
			if (!(usuarioParaAtualizar.getLocalidade() != null
					&& usuarioParaAtualizar.getLocalidade().getId() != null && usuarioParaAtualizar
					.getLocalidade().getId().toString().equals(
							form.getIdLocalidade()))) {

				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, form.getIdLocalidade()));
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroLocalidade, Localidade.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					usuarioParaAtualizar.setLocalidade((Localidade) coll
							.iterator().next());
				}
			}
		} else {
			usuarioParaAtualizar.setLocalidade(null);
		}
		
		if (form.getSituacao() != null && !form.getSituacao().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			UsuarioSituacao usuarioSituacao = new UsuarioSituacao();
			usuarioSituacao.setId(new Integer(form.getSituacao()));
			
			usuarioParaAtualizar.setUsuarioSituacao(usuarioSituacao);
		} else {
			usuarioParaAtualizar.setUsuarioSituacao(null);
		}

		String[] grupo = form.getGrupo();

		sessao.setAttribute("grupo", grupo);
		sessao.setAttribute("usuarioParaAtualizar", usuarioParaAtualizar);

		return retorno;
	}
}
