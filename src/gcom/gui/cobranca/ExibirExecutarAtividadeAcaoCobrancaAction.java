package gcom.gui.cobranca;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição da página de executar atividade de ação de cobrança
 * 
 * @author  pedro alexandre
 * @created 31 de Janeiro de 2006
 */
public class ExibirExecutarAtividadeAcaoCobrancaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {
    	
    	//cria a variável de retorno e seta o mapeamento para a tela de executar atividade de ação de cobrança 
        ActionForward retorno = actionMapping.findForward("exibirExecutarAtividadeAcaoCobrancaAction");

        //cria uma instância da fachada
        Fachada fachada = Fachada.getInstancia();
                
        //pesquisa os parâmetros do sistema
		SistemaParametro sistemaParametros = fachada.pesquisarParametrosDoSistema();
		
		//recupera o Ano/Mês de referência do ciclo de cobrança
		Integer anoMesCicloCobranca = sistemaParametros.getAnoMesFaturamento();
        
		//manda o Ano/Mês de referência do ciclo de cobrança no request
		httpServletRequest.setAttribute("anoMesCicloCobranca",anoMesCicloCobranca);
		
        /*Pesquisar as atividades de cobrança do cronograma que foram previamente comandas
        =============================================================================== */
        //realizando a pesquisa das atividades de cobrança de cronograma
        Collection colecaoAtividadesCobrancaCronograma = fachada.pesquisarCobrancaAcaoAtividadeCronograma();	
        
        
        /*Pesquisar as atividades de cobrança eventuais que foram previamente comandas
        =========================================================================== */
        //realizando a pesquisa das atividades de cobrança eventuais	
        Collection colecaoAtividadesCobrancaEventuais = fachada.pesquisarCobrancaAcaoAtividadeComando();
        
        
        //[FS0002] - Verificar a existência de atividade do cronograma comandada
        if (colecaoAtividadesCobrancaCronograma == null || colecaoAtividadesCobrancaCronograma.isEmpty()){
        	//[FS0003] - Verificar existência de atividade eventual comandada
        	if (colecaoAtividadesCobrancaEventuais == null || colecaoAtividadesCobrancaEventuais.isEmpty()){
        		throw new ActionServletException("atencao.naocadastrado", null, "Atividade(s) de ação de cobrança");
        	}
        }

        //manda a coleção de atividades de cobrança de cronograma no request para a página
        httpServletRequest.setAttribute("colecaoAtividadesCobrancaCronograma",colecaoAtividadesCobrancaCronograma);
        
        //manda a coleção de atividades de cobrança eventuais no request para a página
        httpServletRequest.setAttribute("colecaoAtividadesCobrancaEventuais",colecaoAtividadesCobrancaEventuais);
        
        //retorna o mapeamento contido na variável retorno
        return retorno;
    }
}


