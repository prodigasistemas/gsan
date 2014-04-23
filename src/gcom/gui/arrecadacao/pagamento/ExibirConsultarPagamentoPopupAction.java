package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.pagamento.FiltroPagamento;
import gcom.arrecadacao.pagamento.FiltroPagamentoHistorico;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Vivianne Sousa
 * @created 10/07/2007
 */
public class ExibirConsultarPagamentoPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirConsultarPagamentoPopup");

		Fachada fachada = Fachada.getInstancia();
		
		ConsultarPagamentoPopupActionForm consultarPagamentoPopupActionForm = 
			(ConsultarPagamentoPopupActionForm) actionForm;
		

		String idPagamento = httpServletRequest.getParameter("idPagamento");
		String idPagamentoHistorico = httpServletRequest.getParameter("idPagamentoHistorico");
		
		if (idPagamento != null){
			
			FiltroPagamento filtroPagamento = new FiltroPagamento();
			filtroPagamento.adicionarParametro(new ParametroSimples(FiltroPagamento.ID, new Integer(idPagamento)));
			filtroPagamento.adicionarCaminhoParaCarregamentoEntidade("avisoBancario.arrecadador.cliente");

			// Pesquisa o pagamento no sistema com os parâmetros informados no
			// filtro
			Collection colecaoPagamentos = fachada.pesquisar(filtroPagamento,Pagamento.class.getName());
			
			// Caso a pesquisa tenha retornado o pagamento
			if (colecaoPagamentos != null && !colecaoPagamentos.isEmpty()) {
				// Recupera da coleção o pagamento que vai ser atualizado
				Pagamento pagamento = (Pagamento) Util.retonarObjetoDeColecao(colecaoPagamentos);
				
				consultarPagamentoPopupActionForm.setCodigoAgenteArrecadador("" + 
						pagamento.getAvisoBancario().getArrecadador().getCodigoAgente());
				
				if (pagamento.getAvisoBancario().getArrecadador().getCliente() != null){
					
					consultarPagamentoPopupActionForm.setNomeClienteArrecadador(
							pagamento.getAvisoBancario().getArrecadador().getCliente().getNome());
				}
				consultarPagamentoPopupActionForm.setUltimaAlteracaoPagamento(Util.formatarData(pagamento.getUltimaAlteracao()));
				
			}
			
		}else if(idPagamentoHistorico != null){
			
			FiltroPagamentoHistorico filtroPagamentoHistorico = new FiltroPagamentoHistorico();
			filtroPagamentoHistorico.adicionarParametro(new ParametroSimples(FiltroPagamentoHistorico.ID, new Integer(idPagamentoHistorico)));
			filtroPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("avisoBancario.arrecadador.cliente");

			// Pesquisa o pagamento no sistema com os parâmetros informados no
			// filtro
			Collection colecaoPagamentos = fachada.pesquisar(filtroPagamentoHistorico,PagamentoHistorico.class.getName());
			
			// Caso a pesquisa tenha retornado o pagamento
			if (colecaoPagamentos != null && !colecaoPagamentos.isEmpty()) {
				// Recupera da coleção o pagamento que vai ser atualizado
				PagamentoHistorico pagamentoHistorico = (PagamentoHistorico) Util.retonarObjetoDeColecao(colecaoPagamentos);
				
				consultarPagamentoPopupActionForm.setCodigoAgenteArrecadador("" + 
						pagamentoHistorico.getAvisoBancario().getArrecadador().getCodigoAgente());
				
				if (pagamentoHistorico.getAvisoBancario().getArrecadador().getCliente() != null){
					
					consultarPagamentoPopupActionForm.setNomeClienteArrecadador(
							pagamentoHistorico.getAvisoBancario().getArrecadador().getCliente().getNome());
				}
				consultarPagamentoPopupActionForm.setUltimaAlteracaoPagamento(Util.formatarData(pagamentoHistorico.getUltimaAlteracao()));
				
			}
			
			
		}
		
				
		return retorno;

	}

}
