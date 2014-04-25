package gcom.gui.seguranca.acesso.usuario;

import java.util.Collection;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioGrupo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioGrupo;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processa a remoção do critério cobrança
 * 
 * @author Sávio Luiz
 * @created03/04/2006
 */
public class RemoverUsuarioAction extends GcomAction {
	/**
	 * @author Vivianne Sousa
	 * @date 03/04/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ExcluirUsuarioActionForm excluirUsuarioActionForm = (ExcluirUsuarioActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltroUsuarioGrupo filtroUsuarioGrupo = new FiltroUsuarioGrupo();
		//Verifica se o filtro foi informado pela página de filtragem de
		// cobranca critério
		if (sessao.getAttribute("filtroUsuarioGrupo") != null) {
			filtroUsuarioGrupo = (FiltroUsuarioGrupo) sessao
					.getAttribute("filtroUsuarioGrupo");
		}
		
		Collection colecaoUsuarioGrupo = fachada.pesquisar( filtroUsuarioGrupo, UsuarioGrupo.class.getName() );
		UsuarioGrupo usuarioGrupo =  (UsuarioGrupo) Util.retonarObjetoDeColecao(colecaoUsuarioGrupo);
		
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro( new ParametroSimples(FiltroUsuario.ID, usuarioGrupo.getUsuario().getId()));
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional.unidadeTipo");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("funcionario.unidadeOrganizacional");
		
		Collection colecaoUsuario =  fachada.pesquisar(filtroUsuario, Usuario.class.getName());
		Usuario usuario =  (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
		
		String[] ids = excluirUsuarioActionForm.getIds();
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// mensagem de erro quando o usuário tenta excluir sem ter selecionado
		// nenhum registro
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}
		
		//Passa o usuário logado para registrar operação.
    	Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
    	fachada.removerUsuario(ids, usuario, usuarioLogado);

		sessao.removeAttribute("indicadorAtualizar");
		sessao.removeAttribute("voltar");

		// Monta a página de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(httpServletRequest,
					"Usuário(s) removido(s) com sucesso",
					"Realizar outra manutenção de Usuário",
					"exibirFiltrarUsuarioAction.do?menu=sim");
		}

		return retorno;
	}

}
