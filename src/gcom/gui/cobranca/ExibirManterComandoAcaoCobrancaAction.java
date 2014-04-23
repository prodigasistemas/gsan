package gcom.gui.cobranca;

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
 * Permite excluir um comando de atividade de cobrança do crongrama 
 * ou alterar/excluir um comando deatividade de cobrança eventual
 * [UC0244] Manter Comando de Ação de Conbrança
 * @author Rafael Santos
 * @since 23/03/2006
 */
public class ExibirManterComandoAcaoCobrancaAction  extends GcomAction{
	
	
	/**
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

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirManterComandoAcaoCobranca");
        
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
        
		Collection colecaoAtividadeCronogramaAcaoCobrancaComandadas = fachada.obterListaAtividadeCronogramaAcaoCobrancaComandadas();
		
		Collection colecaoAtividadesEventuaisAcaoCobrancaComandadas = fachada.obterListaAtividadesEventuaisAcaoCobrancaComandadas();
		
		if( (colecaoAtividadesEventuaisAcaoCobrancaComandadas == null
				|| colecaoAtividadesEventuaisAcaoCobrancaComandadas.isEmpty()) 
				&&
		 (colecaoAtividadeCronogramaAcaoCobrancaComandadas == null
				|| colecaoAtividadeCronogramaAcaoCobrancaComandadas.isEmpty()) ){
			throw new ActionServletException(
					"atencao.nao.atividade.cobranca.comandadas");
		}
		
		sessao.setAttribute("colecaoAtividadeCronogramaAcaoCobrancaComandadas",colecaoAtividadeCronogramaAcaoCobrancaComandadas);
		
		sessao.setAttribute("colecaoAtividadesEventuaisAcaoCobrancaComandadas",colecaoAtividadesEventuaisAcaoCobrancaComandadas);
        return retorno;
    }

}
