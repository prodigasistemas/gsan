package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.bean.ComandoNegativacaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * [UC 0653] Pesquisar Comando Negativação
 * 
 * @author Kássia Albuquerque
 * @date 22/10/2007
 */

public class ExibirResultadoPesquisaComandoNegativacaoAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
			ActionForward retorno = actionMapping.findForward("resultadoPesquisaComandoNegativacao");
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			Fachada fachada = Fachada.getInstancia();
	
			ComandoNegativacaoHelper comandoNegativacaoHelper = (ComandoNegativacaoHelper) sessao.getAttribute("comandoNegativacaoHelper");
			
			
	        if (comandoNegativacaoHelper != null) {
	        	Integer totalRegistrosPrimeiraPaginacao = 0;
	        	if(sessao.getAttribute("totalRegistrosPrimeiraPaginacao") == null ){
	        		
	        		totalRegistrosPrimeiraPaginacao = fachada.pesquisarComandoNegativacao(comandoNegativacaoHelper);
	        	
	        		// 1º Passo - Pegar o total de registros através de um count da consulta que aparecerá na tela
	        		
	        		
	        	}else{
	        		totalRegistrosPrimeiraPaginacao = (Integer)sessao.getAttribute("totalRegistrosPrimeiraPaginacao");
	        	}
				// 2º Passo - Chamar a função de Paginação passando o total de registros
				retorno = this.controlarPaginacao(httpServletRequest, retorno,totalRegistrosPrimeiraPaginacao,true);

				// 3º Passo - Obter a coleção da consulta que aparecerá na tela passando o numero de paginas
				// da pesquisa que está no request
				Collection collectionComandoNegativacao = fachada.pesquisarComandoNegativacaoParaPaginacao(comandoNegativacaoHelper,
						(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisaPrimeiraPaginacao"));

				
				//[FS0006 NENHUM REGISTRO ENCONTRADO]
				if (collectionComandoNegativacao == null || collectionComandoNegativacao.isEmpty()) {
					throw new ActionServletException("atencao.pesquisa.nenhumresultado");
				}
				String popup = (String) sessao.getAttribute("popup");
				if (popup != null && popup.equals("2")) {
					sessao.setAttribute("popup", popup);
				} else {
					sessao.removeAttribute("popup");
				}
				
				sessao.setAttribute("collectionComandoNegativacao", collectionComandoNegativacao);
				sessao.setAttribute("totalRegistrosPrimeiraPaginacao", totalRegistrosPrimeiraPaginacao);
				sessao.setAttribute("numeroPaginasPesquisaPrimeiraPaginacao",httpServletRequest.getAttribute("numeroPaginasPesquisaPrimeiraPaginacao"));
				
			}
			
			return retorno;
		
		
	} 
	
}
