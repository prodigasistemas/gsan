package gcom.gui.cobranca;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela 
 * 
 * @author  pedro alexandre
 * @created 31 de Janeiro de 2006
 */
public class ExecutarAtividadeAcaoCobrancaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {
    	
    	//cria a variável de retorno e seta o mapeamento para a tela de sucesso
        ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        //recupera o form
        ExecutarAtividadeAcaoCobrancaActionForm executarAtividadeAcaoCobrancaActionForm = (ExecutarAtividadeAcaoCobrancaActionForm) actionForm;
        
        //cria uma instância da fachada
        Fachada fachada = Fachada.getInstancia();
                
        //recupera o array de ids de atividades de cobrança do cronograma do form
        String[] idsAtividadesCobrancaCronograma = executarAtividadeAcaoCobrancaActionForm.getIdsAtividadesCobrancaCronograma();
        
        //recupera o array de ids de atividades de cobrança eventuais
        String[] idsAtividadesCobrancaEventuais = executarAtividadeAcaoCobrancaActionForm.getIdsAtividadesCobrancaEventuais();

        //verifica se o usuário selecionou alguma atividade de ação de cobrança na página para executar
        if(idsAtividadesCobrancaCronograma == null || idsAtividadesCobrancaCronograma.length == 0){  
        	if(idsAtividadesCobrancaEventuais == null || idsAtividadesCobrancaEventuais.length == 0){
        		throw new ActionServletException("Nenhuma atividade de cobrança selecionada");       
        	}                                                                                        
        }   
        
        //chama o metódo de executar ação de atividade e cobrança da fachada
        fachada.executarAcaoAtividadeCobranca(idsAtividadesCobrancaCronograma,idsAtividadesCobrancaEventuais);
        
        //monta a página de sucesso
        montarPaginaSucesso(httpServletRequest,
				"Atividade(s) de ação de cobrança executada(s) com sucesso.",
		        "Executar outra(s) atividade(s) de ação de cobrança",
		        "exibirExecutarAtividadeAcaoCobrancaAction.do");
		
        //retorna o mapeamento contido na variável retorno
        return retorno;
    }
}


