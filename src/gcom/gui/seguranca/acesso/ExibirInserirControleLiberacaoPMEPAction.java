package gcom.gui.seguranca.acesso;

import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.FiltroPermissaoEspecial;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInserirControleLiberacaoPMEPAction extends ExibidorProcessamentoTarefaRelatorio{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirInserirControleLiberacaoPMEP");
		
		// Form
		ExibirInserirControleLiberacaoPMEPActionForm form = 
			(ExibirInserirControleLiberacaoPMEPActionForm) actionForm;
		
		
		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

		// Pesquisar com enter
		if (objetoConsulta != null && !objetoConsulta.trim().equals("")) {
			
			if(objetoConsulta.trim().equals("1")){
				// Faz a consulta de Funcionalidade
				this.pesquisarFuncionalidade(form);				
			}else if(objetoConsulta.trim().equals("2")){
				// Faz a consulta de PermissaoEspecial
				this.pesquisarPermissaoEspecial(form);				
			} 
			
		}
		
		
		//Seta os request´s encontrados
		this.setaRequest(httpServletRequest,form);
		
		return retorno;
		
	}
					
	
	
	/**
	 * Seta os request com os id encontrados 
	 *
	 * @author Rafael Pinto
	 * @date 23/11/2007
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			ExibirInserirControleLiberacaoPMEPActionForm form){
		
		//Funcionalidade
		if(form.getIdFuncionalidade() != null && 
			!form.getIdFuncionalidade().equals("") && 
			form.getFuncionalidade() != null && 
			!form.getFuncionalidade().equals("")){
					
			httpServletRequest.setAttribute("funcionalidadeEncontrada","true");
		}
		
		//Permissao Especial
		if(form.getIdPermissaoEspecial() != null && 
			!form.getIdPermissaoEspecial().equals("") && 
			form.getPermissaoEspecial() != null && 
			!form.getPermissaoEspecial().equals("")){
					
			httpServletRequest.setAttribute("permissaoEspecialEncontrada","true");
		}
	}
	
	/**
	 * Pesquisa Funcionalidade
	 *
	 * @author Daniel Alves
	 * @date 21/07/2010
	 */
	private void pesquisarFuncionalidade(ExibirInserirControleLiberacaoPMEPActionForm form) {

		FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
		filtroFuncionalidade.adicionarParametro(
				new ParametroSimples(FiltroFuncionalidade.ID, 
				form.getIdFuncionalidade()));
		
		// Recupera Funcionalidade
		Collection colecaoFuncionalidade = 
			this.getFachada().pesquisar(filtroFuncionalidade, Funcionalidade.class.getName());
	
		if (colecaoFuncionalidade != null && !colecaoFuncionalidade.isEmpty()) {

			Funcionalidade funcionalidade = 
				(Funcionalidade) Util.retonarObjetoDeColecao(colecaoFuncionalidade);
			
			form.setIdFuncionalidade(funcionalidade.getId().toString());
			form.setFuncionalidade(funcionalidade.getDescricao());
			
		} else {
			form.setIdFuncionalidade(null);
			form.setFuncionalidade("Funcionalidade inexistente");
		}
	}
	
	
	/**
	 * Pesquisa Permissao Especial
	 *
	 * @author Daniel Alves
	 * @date 21/07/2010
	 */
	private void pesquisarPermissaoEspecial(ExibirInserirControleLiberacaoPMEPActionForm form) {

		FiltroPermissaoEspecial filtroPermissaoEspecial = new FiltroPermissaoEspecial();
		filtroPermissaoEspecial.adicionarParametro(
				new ParametroSimples(FiltroPermissaoEspecial.ID, 
				form.getIdPermissaoEspecial()));
		
		// Recupera Permissao Especial
		Collection colecaoPermissaoEspecial = 
			this.getFachada().pesquisar(filtroPermissaoEspecial, PermissaoEspecial.class.getName());
	
		if (colecaoPermissaoEspecial != null && !colecaoPermissaoEspecial.isEmpty()) {

			PermissaoEspecial permissaoEspecial = 
				(PermissaoEspecial) Util.retonarObjetoDeColecao(colecaoPermissaoEspecial);
			
			form.setIdPermissaoEspecial(permissaoEspecial.getId().toString());
			form.setPermissaoEspecial(permissaoEspecial.getDescricao());
			
		} else {
			form.setIdPermissaoEspecial(null);
			form.setPermissaoEspecial("Permissão Especial inexistente");
		}
	}
	
	
}
