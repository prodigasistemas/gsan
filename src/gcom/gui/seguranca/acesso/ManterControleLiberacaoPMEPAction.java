package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.ControleLiberacaoPermissaoEspecial;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.FiltroPermissaoEspecial;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Daniel Alves
 * @date 23/07/2010
 */
public class ManterControleLiberacaoPMEPAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma novo Controle Liberação Permissão Especial
	 * 
	 * [UC0280] Manter Controle Liberação Permissão Especial
	 * 
	 * 
	 * @author Daniel Alves
	 * @date 23/07/2010
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

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		ExibirManterControleLiberacaoPMEPActionForm manterControleLiberacaoPMEPActionForm = (ExibirManterControleLiberacaoPMEPActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtendo dados do form e setando no Objeto ControleLiberacaoPermissaoEspecial
		
		ControleLiberacaoPermissaoEspecial controleLiberacaoPermissaoEspecial = new ControleLiberacaoPermissaoEspecial();


		if (manterControleLiberacaoPMEPActionForm.getIdFuncionalidade() != null
				&& !manterControleLiberacaoPMEPActionForm.getIdFuncionalidade().trim()
						.equalsIgnoreCase("") ) {

			FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
			filtroFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroFuncionalidade.ID,
							manterControleLiberacaoPMEPActionForm
									.getIdFuncionalidade()));

			Collection colecaoFuncionalidade = fachada.pesquisar(
					filtroFuncionalidade,
					Funcionalidade.class.getName());

			if (colecaoFuncionalidade != null
					&& !colecaoFuncionalidade.isEmpty()) {
				Funcionalidade funcionalidade = (Funcionalidade) Util
						.retonarObjetoDeColecao(colecaoFuncionalidade);
				
				controleLiberacaoPermissaoEspecial.setFuncionalidade(funcionalidade);
			} else {

				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Funcionalidade");
			}

		}
		
		if (manterControleLiberacaoPMEPActionForm.getIdPermissaoEspecial() != null
				&& !manterControleLiberacaoPMEPActionForm.getIdPermissaoEspecial()
						.trim().equalsIgnoreCase("") ) {

			FiltroPermissaoEspecial filtroPermissaoEspecial = new FiltroPermissaoEspecial();
			filtroPermissaoEspecial
					.adicionarParametro(new ParametroSimples(
							FiltroPermissaoEspecial.ID,
							manterControleLiberacaoPMEPActionForm
									.getIdPermissaoEspecial()));

			Collection colecaoPermissaoEspecial = fachada.pesquisar(
					filtroPermissaoEspecial,
					PermissaoEspecial.class.getName());

			if (colecaoPermissaoEspecial != null
					&& !colecaoPermissaoEspecial.isEmpty()) {
				
				PermissaoEspecial permissaoEspecial = (PermissaoEspecial) Util
						.retonarObjetoDeColecao(colecaoPermissaoEspecial);
				
				controleLiberacaoPermissaoEspecial.setPermissaoEspecial(permissaoEspecial);
				
				//REGISTRAR TRANSAÇÂO
			} else {

				//throw new ActionServletException("atencao.pesquisa_inexistente", null,"Permissão Especial");
			}

		}
		
		controleLiberacaoPermissaoEspecial.setIndicadorUso(Short.valueOf(manterControleLiberacaoPMEPActionForm.getIndicadorUso()));
		controleLiberacaoPermissaoEspecial.setUltimaAlteracao(new Date());		
		
		Usuario usuarioLogado =(Usuario) (httpServletRequest.getSession()).getAttribute("usuarioLogado");
		
		 fachada.manterControleLiberacaoPermissaoEspecial(controleLiberacaoPermissaoEspecial, usuarioLogado);

		sessao.removeAttribute("colecaoFuncionalidadeTela");

		montarPaginaSucesso(httpServletRequest, "Novo Controle de Liberação de Permissão " +
				" Especial inserido com sucesso.",
				"Atualizar outro Controle de Liberação de Permissão Especial.",
				"exibirFiltrarControleLiberacaoPMEPAction.do");

		return retorno;
	}
}
