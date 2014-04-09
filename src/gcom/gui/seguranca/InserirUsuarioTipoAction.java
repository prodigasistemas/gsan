package gcom.gui.seguranca;


import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class InserirUsuarioTipoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um tipo de usuario
	 * 
	 * [UC0854] Inserir Tipo de Usuario
	 * 
	 * @author Arthur Carvalho
	 * @date 26/08/2008
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

		InserirUsuarioTipoActionForm inserirUsuarioTipoActionForm = (InserirUsuarioTipoActionForm) actionForm;

		
		String descricao = inserirUsuarioTipoActionForm.getDescricao();
		Short indicadorFuncionario = inserirUsuarioTipoActionForm.getIndicadorFuncionario();
		
		Collection colecaoPesquisa = null;
		
		// Descrição
		if (descricao == null || "".equals(descricao)) {
			throw new ActionServletException("atencao.required", null,"Descrição");
		}
		
		FiltroUsuarioTipo filtroUsuarioTipo = new FiltroUsuarioTipo();
		filtroUsuarioTipo.adicionarParametro(
				new ParametroSimples(FiltroUsuarioTipo.DESCRICAO,descricao));
		
		colecaoPesquisa = (Collection)
		this.getFachada().pesquisar(filtroUsuarioTipo, UsuarioTipo.class.getName());
				
		if( colecaoPesquisa !=null && !colecaoPesquisa.isEmpty()){
			throw new ActionServletException("atencao.descricao_existente", null, descricao);
		}
		

			UsuarioTipo usuarioTipo= new UsuarioTipo();
			usuarioTipo.setIndicadorFuncionario(indicadorFuncionario);
			usuarioTipo.setDescricao(descricao);
			usuarioTipo.setUltimaAlteracao(new Date());
			usuarioTipo.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

			Integer idUsuarioTipo = (Integer) this.getFachada().inserir(usuarioTipo);

			montarPaginaSucesso(httpServletRequest,
					"Tipo de Usuário " + descricao
							+ " inserido com sucesso.",
					"Inserir outra Tipo de Usuário",
					"exibirInserirUsuarioTipoAction.do?menu=sim",
					"exibirAtualizarUsuarioTipoAction.do?idRegistroAtualizacao="
							+ idUsuarioTipo,
					"Atualizar Tipo de Usuário Inserida");

			this.getSessao(httpServletRequest).removeAttribute("InserirUsuarioTipoActionForm");

			return retorno;
		
		
	}
}		
