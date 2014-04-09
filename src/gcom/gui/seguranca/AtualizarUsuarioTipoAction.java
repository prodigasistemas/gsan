package gcom.gui.seguranca;





import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarUsuarioTipoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarUsuarioTipoActionForm atualizarUsuarioTipoActionForm = (AtualizarUsuarioTipoActionForm) actionForm;

		UsuarioTipo usuarioTipo= (UsuarioTipo) sessao.getAttribute("atualizarUsuarioTipo");
		
		Collection colecaoPesquisa = null;
		
		if(atualizarUsuarioTipoActionForm.getCodigo()!= null 
				&& !atualizarUsuarioTipoActionForm.getCodigo().equals("")){
			usuarioTipo.setId(new Integer(atualizarUsuarioTipoActionForm.getCodigo()));
		}else{
			usuarioTipo.setId(null);
		}
		usuarioTipo.setDescricao(atualizarUsuarioTipoActionForm.getDescricao());
		usuarioTipo.setIndicadorUso(new Short (atualizarUsuarioTipoActionForm.getIndicadorUso()));
		usuarioTipo.setIndicadorFuncionario(new Short (atualizarUsuarioTipoActionForm.getIndicadorFuncionario()));
		
		Short indicadorFuncionario = atualizarUsuarioTipoActionForm.getIndicadorFuncionario();
		String codigo = atualizarUsuarioTipoActionForm.getCodigo();		
        String descricao = atualizarUsuarioTipoActionForm.getDescricao();
        Short indicadorUso = atualizarUsuarioTipoActionForm.getIndicadorUso();
        
		
        usuarioTipo.setDescricao(descricao);
       
        if(codigo != null && !codigo.equals("")){
        	usuarioTipo.setId(new Integer(codigo));
        }else{
        	usuarioTipo.setId(null);
        }
        
        usuarioTipo.setIndicadorFuncionario(new Short(indicadorFuncionario));
        usuarioTipo.setIndicadorUso(new Short(indicadorUso));

        usuarioTipo.setUltimaAlteracao(new Date());
        

        FiltroUsuarioTipo filtroTipoUsuario= new FiltroUsuarioTipo();
        	filtroTipoUsuario.adicionarParametro(
				new ParametroSimples(FiltroUsuarioTipo.DESCRICAO, descricao));
        	filtroTipoUsuario.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroUsuarioTipo.ID, usuarioTipo.getId()));
		
		colecaoPesquisa = (Collection)
		this.getFachada().pesquisar(filtroTipoUsuario, UsuarioTipo.class.getName());
				
		if( colecaoPesquisa !=null && !colecaoPesquisa.isEmpty()){
			throw new ActionServletException("atencao.descricao_existente", null, descricao);
		}
		
		
		FiltroUsuarioTipo filtroUsuarioTipo = new FiltroUsuarioTipo();
			filtroUsuarioTipo.adicionarParametro(
					new ParametroSimples(FiltroUsuarioTipo.ID, codigo));
			filtroUsuarioTipo.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroUsuarioTipo.ID, usuarioTipo.getId()));
			
		colecaoPesquisa = (Collection) 
			this.getFachada().pesquisar(filtroUsuarioTipo, UsuarioTipo.class.getName());
		
	
		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			throw new ActionServletException("atencao.codigo_existente", null, codigo+"");
		}
		
		fachada.atualizar(usuarioTipo);

		
		montarPaginaSucesso(httpServletRequest, "Tipo de Usuário"
				+ descricao + " atualizado com sucesso.",
				"Realizar outra Manutenção do Tipo de Usuário",
				"exibirFiltrarUsuarioTipoAction.do?menu=sim");        
        
		return retorno;
	}
}
