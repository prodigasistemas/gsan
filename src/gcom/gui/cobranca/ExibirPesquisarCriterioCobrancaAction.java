package gcom.gui.cobranca;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Consultar Pagamento - Exibir
 * 
 * @author TIAGO MORENO - 31/01/2006
 */
public class ExibirPesquisarCriterioCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirPesquisarCriterioCobrancaAction");

		// Instacia a fachada
		//Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		
        //envia uma flag que será verificado no criterio_cobranca_pesquisa.jsp
        //para saber se irá usar o enviar dados ou o enviar dados parametros
		
		PesquisarCriterioCobrancaActionForm pesquisarCriterioCobrancaActionForm = (PesquisarCriterioCobrancaActionForm) actionForm;
		
		
		String limpaForm = httpServletRequest.getParameter("limpaForm");
		String menu = httpServletRequest.getParameter("menu");
		
		if (limpaForm != null || menu != null){
			pesquisarCriterioCobrancaActionForm.setDataFim("");
			pesquisarCriterioCobrancaActionForm.setDataInicio("");
			pesquisarCriterioCobrancaActionForm.setDescricaoCriterio("");
			pesquisarCriterioCobrancaActionForm.setNumeroAnos("");
			pesquisarCriterioCobrancaActionForm.setOpcaoContaRevisao("3");
			pesquisarCriterioCobrancaActionForm.setOpcaoImovelDebito("3");
			pesquisarCriterioCobrancaActionForm.setOpcaoImovelSitCobranca("3");
			pesquisarCriterioCobrancaActionForm.setOpcaoImovelSitEspecial("3");
			pesquisarCriterioCobrancaActionForm.setOpcaoInqDebitoConta("3");
			pesquisarCriterioCobrancaActionForm.setOpcaoInqDebitoContaAntiga("3");
		}

		
		
		if (httpServletRequest.getParameter("popup") != null){
			sessao.setAttribute("popup", httpServletRequest.getParameter("popup"));
		}
		
        if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaCriterioCobranca") != null) {
        	  sessao.setAttribute("caminhoRetornoTelaPesquisaCriterioCobranca",
        	          httpServletRequest
        	                  .getParameter("caminhoRetornoTelaPesquisaCriterioCobranca"));

        }	  
        
        //recupera no ExibirAdicionarCriterioCobrancaRotaAction
		if (httpServletRequest.getParameter("idCobrancaAcao") != null){
			sessao.setAttribute("idCobrancaAcao", httpServletRequest.getParameter("idCobrancaAcao"));	
		}
		
		return retorno;

	}
}
