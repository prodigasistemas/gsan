package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.pagamento.Pagamento;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action responsável por exibir a página de manter pagamentos
 *
 * @author Pedro Alexandre 
 * @date 21/03/2006
 */
public class ExibirManterPagamentoAction extends GcomAction {

	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * <Breve descrição sobre o subfluxo>
	 *
	 * <Identificador e nome do subfluxo>	
	 *
	 * <Breve descrição sobre o fluxo secundário>
	 *
	 * <Identificador e nome do fluxo secundário> 
	 *
	 * @author Pedro Alexandre
	 * @date 21/03/2006
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

		
		ActionForward retorno = null;//actionMapping.findForward("manterPagamento");

		//Fachada fachada = Fachada.getInstancia();

		Collection colecaoPagamentos = null;

		HttpSession sessao = httpServletRequest.getSession(false);

		if (sessao.getAttribute("colecaoImoveisPagamentos") != null) {
			colecaoPagamentos = (Collection) sessao
					.getAttribute("colecaoImoveisPagamentos");
			
		} else if (sessao.getAttribute("colecaoClientesPagamentos") != null) {
			colecaoPagamentos = (Collection) sessao
					.getAttribute("colecaoClientesPagamentos");
			
		} else if (sessao.getAttribute("colecaoPagamentosAvisoBancario") != null) {
			colecaoPagamentos = (Collection) sessao
					.getAttribute("colecaoPagamentosAvisoBancario");
			
			/*}else if (sessao.getAttribute("colecaoPagamentos") != null){
			colecaoPagamentos = (Collection) sessao.getAttribute("colecaoPagamentos");*/
			
		}else if (sessao.getAttribute("colecaoPagamentosLocalidade") != null){
			colecaoPagamentos = (Collection) sessao.getAttribute("colecaoPagamentosLocalidade");
			
		}else if (sessao.getAttribute("colecaoPagamentosMovimentoArrecadador") != null){
			colecaoPagamentos = (Collection) sessao.getAttribute("colecaoPagamentosMovimentoArrecadador");
		} else if (sessao.getAttribute("colecaoPagamentos") != null){
			colecaoPagamentos = (Collection) sessao.getAttribute("colecaoPagamentos");
		} 
			
		

		String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
		
		if (colecaoPagamentos != null && colecaoPagamentos.size() == 1 && identificadorAtualizar != null ){
			retorno = actionMapping.findForward("atualizarPagamentoFiltrar");
			
			Pagamento pagamento = (Pagamento)Util.retonarObjetoDeColecao(colecaoPagamentos);
			
			 sessao.setAttribute("idRegistroAtualizacao",pagamento.getId().toString());
		}else{
			retorno = actionMapping.findForward("manterPagamento");
		}
		

		if (colecaoPagamentos == null || colecaoPagamentos.isEmpty()) {
			// Nenhum pagamento cadastrado
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		sessao.setAttribute("colecaoPagamentos", colecaoPagamentos);
		
		sessao.removeAttribute("colecaoImoveisPagamentos");
		sessao.removeAttribute("colecaoClientesPagamentos");
		sessao.removeAttribute("colecaoAvisosBancariosPagamentos"); 

		return retorno;
	}

}
