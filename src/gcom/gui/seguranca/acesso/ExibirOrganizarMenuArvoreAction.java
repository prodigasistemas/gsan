package gcom.gui.seguranca.acesso;

import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.FiltroFuncionalidadeCategoria;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.FuncionalidadeCategoria;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirOrganizarMenuArvoreAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirOrganizarMenuArvore");
		
		OrganizarMenuActionForm form = 
			(OrganizarMenuActionForm) actionForm;

		String ehParaSalvar = httpServletRequest.getParameter("ehParaSalvar");
		
		if(ehParaSalvar != null && !ehParaSalvar.equals("")){

			if(httpServletRequest.getParameter("idFuncionalidade") != null && 
				!httpServletRequest.getParameter("idFuncionalidade").equals("")){

				String idFuncionalidade = 
					httpServletRequest.getParameter("idFuncionalidade");

				Funcionalidade funcionalidade = 
					this.pesquisarFuncionalidade(idFuncionalidade);
				
				funcionalidade.setNumeroOrdemMenu(new Long(form.getNumeroOrdemFuncionalidade()));

				this.getFachada().atualizar(funcionalidade);
				
			}else{

				String idFuncionalidade = 
					httpServletRequest.getParameter("idFuncionalidadeCategoria");

				FuncionalidadeCategoria funcionalidadeCategoria = 
					this.pesquisarFuncionalidadeCategoria(idFuncionalidade);
				
				funcionalidadeCategoria.setNumeroOrdemMenu(new Long(form.getNumeroOrdemPasta()));

				this.getFachada().atualizar(funcionalidadeCategoria);
				
			}
		} else {
			
			String idFuncionalidade = null;
			String descricao = null;
			boolean ordemFuncionalidade = false;
			
			if(httpServletRequest.getParameter("idFuncionalidade") != null && 
				!httpServletRequest.getParameter("idFuncionalidade").equals("")){

				idFuncionalidade = 
					httpServletRequest.getParameter("idFuncionalidade");
				
				Funcionalidade funcionalidade = 
					this.pesquisarFuncionalidade(idFuncionalidade);
				
				if(funcionalidade.getNumeroOrdemMenu() != null){
					form.setNumeroOrdemFuncionalidade(funcionalidade.getNumeroOrdemMenu().toString());	
				}
				
				descricao = funcionalidade.getDescricao();
				ordemFuncionalidade = true;
				form.setTipoOrdem("F");
				
				httpServletRequest.setAttribute("ordemFuncionalidade",ordemFuncionalidade);
				
			} else if(httpServletRequest.getParameter("idFuncionalidadeCategoria") != null && 
					!httpServletRequest.getParameter("idFuncionalidadeCategoria").equals("")){
				
				idFuncionalidade = 
					httpServletRequest.getParameter("idFuncionalidadeCategoria");

				FuncionalidadeCategoria funcionalidade = 
					this.pesquisarFuncionalidadeCategoria(idFuncionalidade);
				
				if(funcionalidade.getNumeroOrdemMenu() != null){
					form.setNumeroOrdemPasta(funcionalidade.getNumeroOrdemMenu().toString());	
				}
				
				descricao = funcionalidade.getNome();
				form.setTipoOrdem("P");
				
				httpServletRequest.setAttribute("ordemFuncionalidade",ordemFuncionalidade);
			}
			
			
			httpServletRequest.setAttribute("descricaoFuncionalidade",descricao);
			httpServletRequest.setAttribute("idFuncionalidade",idFuncionalidade);
			
		}
		
		FuncionalidadeCategoria arvoreFuncionalidades = 
			this.getFachada().pesquisarArvoreFuncionalidades(new Integer(form.getModulo()));
		
		MenuGCOM menu = new MenuGCOM();
		String menuGerado = menu.gerarMenuOrganizarMenu(arvoreFuncionalidades);
		
		
		httpServletRequest.setAttribute("arvoreFuncionalidades",menuGerado);
		
		return retorno;
	}
	
	private Funcionalidade pesquisarFuncionalidade(String idFuncionalidade){
		Funcionalidade funcionalidade = null;
		
		FiltroFuncionalidade filtro = new FiltroFuncionalidade();
		filtro.adicionarParametro(
			new ParametroSimples(
				FiltroFuncionalidade.ID,idFuncionalidade));

		Collection colecaoFuncionalidade =
			this.getFachada().pesquisar(filtro,Funcionalidade.class.getName());
		
		funcionalidade = (Funcionalidade) Util.retonarObjetoDeColecao(colecaoFuncionalidade);
		
		return funcionalidade;
	}
	
	private FuncionalidadeCategoria pesquisarFuncionalidadeCategoria(String idFuncionalidade){
		FuncionalidadeCategoria funcionalidade = null;
		
		FiltroFuncionalidadeCategoria filtro = new FiltroFuncionalidadeCategoria();
		filtro.adicionarParametro(
			new ParametroSimples(
				FiltroFuncionalidadeCategoria.ID,idFuncionalidade));

		Collection colecaoFuncionalidade =
			this.getFachada().pesquisar(filtro,FuncionalidadeCategoria.class.getName());
		
		funcionalidade = (FuncionalidadeCategoria) Util.retonarObjetoDeColecao(colecaoFuncionalidade);
		
		return funcionalidade;
	}
	
	
}
