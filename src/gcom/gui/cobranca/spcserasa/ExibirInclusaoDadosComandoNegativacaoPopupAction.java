package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.bean.DadosInclusoesComandoNegativacaoHelper;
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
 * @date 31/10/2007
 */


public class ExibirInclusaoDadosComandoNegativacaoPopupAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("inclusaoDadosComandoNegativacaoPopup");
			
			InclusaoDadosComandoNegativacaoPopupActionForm form = (InclusaoDadosComandoNegativacaoPopupActionForm)actionForm;
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			Fachada fachada = Fachada.getInstancia();
			
			Integer idComandoNegativacao = null;
			
			
			if (httpServletRequest.getParameter("idComandoNegativacao") != null) {
				
				idComandoNegativacao = new Integer(httpServletRequest.getParameter("idComandoNegativacao"));
				sessao.setAttribute("idComandoNegativacao",idComandoNegativacao);
			} else if(sessao.getAttribute("idComandoNegativacao") != null){
				idComandoNegativacao = (Integer)sessao.getAttribute("idComandoNegativacao");
			}
			
			
			// PAGINACAO DA TABELA
	       
        	Integer totalRegistrosSegundaPaginacao = 0;
//        	if(sessao.getAttribute("totalRegistrosSegundaPaginacao") == null ){
        		
        		totalRegistrosSegundaPaginacao = (Integer)fachada.pesquisarDadosInclusoesComandoNegativacao(idComandoNegativacao);

        		// 1º Passo - Pegar o total de registros através de um count da consulta que aparecerá na tela
        		 
        		
//        	}else{
//        		totalRegistrosSegundaPaginacao = (Integer)sessao.getAttribute("totalRegistrosSegundaPaginacao");
//        	}
        	
			// 2º Passo - Chamar a função de Paginação passando o total de registros
			retorno = this.controlarPaginacao(httpServletRequest, retorno,totalRegistrosSegundaPaginacao, false);
			
			// 3º Passo - Obter a coleção da consulta que aparecerá na tela passando o numero de paginas
			// da pesquisa que está no request
			Collection collectionDadosInclusoesComandoNegativacao = fachada.pesquisarDadosInclusoesComandoNegativacaoParaPaginacao(idComandoNegativacao,
					(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisaSegundaPaginacao"));
			
			
			//[FS0006 NENHUM REGISTRO ENCONTRADO]
			if (collectionDadosInclusoesComandoNegativacao == null || collectionDadosInclusoesComandoNegativacao.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}
			
			// SETANDO OS DADOS GERAIS DA TELA
			
			DadosInclusoesComandoNegativacaoHelper dadosInclusoesComandoNegativacaoHelper = (DadosInclusoesComandoNegativacaoHelper)
			collectionDadosInclusoesComandoNegativacao.iterator().next();
			
			if (dadosInclusoesComandoNegativacaoHelper.getNomeCliente() != null && 
					!dadosInclusoesComandoNegativacaoHelper.getNomeCliente().equals("")){
				
				form.setNegativador(dadosInclusoesComandoNegativacaoHelper.getNomeCliente());
			}
			if (dadosInclusoesComandoNegativacaoHelper.getQuantidadeInclusoes()!= null &&
					!dadosInclusoesComandoNegativacaoHelper.getQuantidadeInclusoes().equals("")){
				
				form.setQtdInclusoes(dadosInclusoesComandoNegativacaoHelper.getQuantidadeInclusoes().toString());
			}
			if (dadosInclusoesComandoNegativacaoHelper.getValorTotalDebito()!= null && 
					!dadosInclusoesComandoNegativacaoHelper.getValorTotalDebito().equals("")){
				
				form.setValorTotalDebito(dadosInclusoesComandoNegativacaoHelper.getValorTotalDebito().toString());
			}
			if (dadosInclusoesComandoNegativacaoHelper.getQuantidadeItensIncluidos()!= null && 
					!dadosInclusoesComandoNegativacaoHelper.getQuantidadeItensIncluidos().equals("")){
				
				form.setQtdItensIncluidos(dadosInclusoesComandoNegativacaoHelper.getQuantidadeItensIncluidos().toString());
			}
			
			
			sessao.setAttribute("collectionDadosInclusoesComandoNegativacao", collectionDadosInclusoesComandoNegativacao);
			sessao.setAttribute("totalRegistrosSegundaPaginacao", totalRegistrosSegundaPaginacao);
			sessao.setAttribute("numeroPaginasPesquisaSegundaPaginacao",httpServletRequest.getAttribute("numeroPaginasPesquisaSegundaPaginacao"));
			
			
			
			return retorno;
		
		
	
	}
}
