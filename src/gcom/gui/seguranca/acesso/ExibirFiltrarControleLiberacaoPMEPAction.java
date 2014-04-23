package gcom.gui.seguranca.acesso;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.ControleLiberacaoPermissaoEspecial;
import gcom.seguranca.acesso.FiltroControleLiberacaoPermissaoEspecial;
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

public class ExibirFiltrarControleLiberacaoPMEPAction extends GcomAction {

	/**
	 * Este caso de uso efetua pesquisa de Controle de Liberação de Permissão Especial
	 * 
	 * Filtrar Controle de Liberação de Permissão Especial
	 * 
	 * 
	 * @author Daniel Alves
	 * @date 12/08/2010
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

		ActionForward retorno = actionMapping
				.findForward("controleLiberacaoPMEPFiltrar");

		FiltrarControleLiberacaoPMEPActionForm filtrarControleLiberacaoPMEPActionForm = (FiltrarControleLiberacaoPMEPActionForm) actionForm;

		FiltroControleLiberacaoPermissaoEspecial filtroControleLiberacaoPermissaoEspecial = new FiltroControleLiberacaoPermissaoEspecial();

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		Collection<ControleLiberacaoPermissaoEspecial> colecaoControleLiberacaoPMEP = this.getFachada().pesquisar(filtroControleLiberacaoPermissaoEspecial,
				ControleLiberacaoPermissaoEspecial.class.getName());

		if (colecaoControleLiberacaoPMEP == null || colecaoControleLiberacaoPMEP.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Controle de Liberação de Permissão Especial.");
		}

		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

		// Pesquisar com enter
		if (objetoConsulta != null && !objetoConsulta.trim().equals("")) {
			
			if(objetoConsulta.trim().equals("1")){
				// Faz a consulta de Funcionalidade
				this.pesquisarFuncionalidade(filtrarControleLiberacaoPMEPActionForm);				
			}else if(objetoConsulta.trim().equals("2")){
				// Faz a consulta de PermissaoEspecial
				this.pesquisarPermissaoEspecial(filtrarControleLiberacaoPMEPActionForm);				
			} 
			
		}
		
		
		//Seta os request´s encontrados
		this.setaRequest(httpServletRequest,filtrarControleLiberacaoPMEPActionForm);
		
		if(filtrarControleLiberacaoPMEPActionForm.getIndicadorUso()==null){
			filtrarControleLiberacaoPMEPActionForm.setIndicadorUso("1");
		}
		if(filtrarControleLiberacaoPMEPActionForm.getAtualizar()==null){
			filtrarControleLiberacaoPMEPActionForm.setAtualizar("1");
		}
		
		return retorno;
		
	}
					
	
	
	/**
	 * Seta os request com os id encontrados 
	 *
	 * @author Rafael Pinto
	 * @date 23/11/2007
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			FiltrarControleLiberacaoPMEPActionForm form){
		
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
	private void pesquisarFuncionalidade(FiltrarControleLiberacaoPMEPActionForm form) {

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
	private void pesquisarPermissaoEspecial(FiltrarControleLiberacaoPMEPActionForm form) {

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
