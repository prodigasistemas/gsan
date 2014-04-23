package gcom.gui.cobranca;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

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
 * 
 * Exclui Comando de Atividade do Cronograma de Ação de Cobrança
 *
 * [SB0001] - Excluir Comando de Atividade de Ação de Cobrança
 *
 * @author Rafael Santos
 * @since 24/03/2006
 */
public class ExcluirComandoAtividadeCronogramaAcaoCobrancaAction  extends GcomAction{
	
	
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
                .findForward("telaSucesso");
        
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
        
		ManterComandoAcaoCobrancaActionForm manterComandoAcaoCobrancaActionForm =(ManterComandoAcaoCobrancaActionForm)actionForm;
		
		String[] idsCobrancaAcaoAtividadeCronograma = manterComandoAcaoCobrancaActionForm.getIdCobrancaAcaoAtividadeCronograma();
		
		fachada.excluirComandoAtividadeCronogramaAcaoCobranca(idsCobrancaAcaoAtividadeCronograma);
		
		montarPaginaSucesso(httpServletRequest,idsCobrancaAcaoAtividadeCronograma.length + " Comando(s) de Ação de Cobrança removidos com sucesso",
                "Manter outro Comando de Ação de Cobrança",
                "exibirManterComandoAcaoCobrancaAction.do?menu=sim");

   		if(sessao.getAttribute("colecaoAtividadeCronogramaAcaoCobrancaComandadas") != null ){
   			sessao.removeAttribute("colecaoAtividadeCronogramaAcaoCobrancaComandadas");
   		}
		if(sessao.getAttribute("colecaoAtividadesEventuaisAcaoCobrancaComandadas") != null ){
			sessao.removeAttribute("colecaoAtividadesEventuaisAcaoCobrancaComandadas");
		}
		
		
        return retorno;
    }

}
