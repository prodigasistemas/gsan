package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.NegativadorMovimento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.bean.NegativadorMovimentoHelper;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de exibir o resultado do filtro do Negativador Movimento
 * @author Yara Taciane de Souza
 * @created 21/01/2008
 */

public class ExibirConsultarNegativadorMovimentoAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

  
        ActionForward retorno = actionMapping.findForward("consultarNegativadorMovimento");
		HttpSession sessao = httpServletRequest.getSession(false);

		NegativadorMovimentoHelper negativadorMovimentoHelper = null;
		
//		 Verifica se o filtro foi informado pela página de filtragem do Negativador
		if (sessao.getAttribute("negativadorMovimentoHelper") != null) {
			negativadorMovimentoHelper = (NegativadorMovimentoHelper) sessao.getAttribute("negativadorMovimentoHelper");
		}
		
		// 1º Passo - Pegar o total de registros através de um count da consulta que aparecerá na tela
		Integer totalRegistros = Fachada.getInstancia().pesquisarNegativadorMovimentoCount(negativadorMovimentoHelper);
   	
		//[FS0004] Nenhum registro encontrado				
		if (totalRegistros == null || totalRegistros.equals(new Integer(0))) {
			// Nenhuma Contrato Negativador cadastrado
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
//		Integer totalRegistros = new Integer(collectionNegativadorMovimento.size()); 

		// 2º Passo - Chamar a função de Paginação passando o total de registros
		retorno = this.controlarPaginacao(httpServletRequest, retorno,	totalRegistros);

		// 3º Passo - Obter a coleção da consulta que aparecerá na tela passando o numero de paginas
		// da pesquisa que está no request
		
		Collection collectionNegativadorMovimento = Fachada.getInstancia().pesquisarNegativadorMovimento(negativadorMovimentoHelper,(Integer) httpServletRequest
				.getAttribute("numeroPaginasPesquisa"));
		
		for (Iterator iter = collectionNegativadorMovimento.iterator(); iter.hasNext();) {
			NegativadorMovimento nv = (NegativadorMovimento) iter.next();
			
			if ( nv.getValorTotalEnvio() != null ) {
				nv.setValorTotalEnvio(nv.getValorTotalEnvio().setScale(2));
			}
		}
		
		sessao.setAttribute("collectionNegativadorMovimento", collectionNegativadorMovimento);
		
	     sessao.setAttribute("negativadorMovimentoHelper", negativadorMovimentoHelper);
		
		
        return retorno;
        
    }

}
