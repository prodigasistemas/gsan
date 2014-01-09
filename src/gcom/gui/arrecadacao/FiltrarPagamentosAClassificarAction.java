package gcom.gui.arrecadacao;

import gcom.arrecadacao.FiltroClassificarPagamentos;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.batch.FiltroProcessoIniciado;
import gcom.batch.ProcessoIniciado;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis2.databinding.types.soapencoding.Array;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarPagamentosAClassificarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("filtrarPagamentosAClassificar");

		Fachada fachada = Fachada.getInstancia();
		
		PagamentosAClassificarActionForm pagamentosAClassificarActionForm = (PagamentosAClassificarActionForm) actionForm;

		Integer situacaoPagamento = new Integer(pagamentosAClassificarActionForm.getIdSituacaoPagamento());
		Integer referenciaArrecadacao = Util.formatarMesAnoComBarraParaAnoMes(pagamentosAClassificarActionForm.getReferenciaArrecadacao());
		

		FiltroClassificarPagamentos filtroClassificarPagamentos = 
			new FiltroClassificarPagamentos(FiltroClassificarPagamentos.ORDER_BY);

		if ( situacaoPagamento != null && referenciaArrecadacao != null ) {
			
			filtroClassificarPagamentos.adicionarParametro(new ParametroSimples(
					FiltroClassificarPagamentos.ID_SITUACAO_PAGAMENTO, situacaoPagamento));
			
			filtroClassificarPagamentos.adicionarParametro(new ParametroSimples(
					FiltroClassificarPagamentos.REFERENCIA_ARRECADACAO, referenciaArrecadacao));
		}

		
		Map resultado = controlarPaginacao(httpServletRequest, retorno, filtroClassificarPagamentos, Pagamento.class.getName());
		
		@SuppressWarnings("unchecked")
		Collection<Pagamento> colecaoPagamentos = (Collection<Pagamento>) resultado.get("colecaoRetorno");
		
		Collection<Pagamento> colecaoPagamentosAClassificar = fachada.obterPagamentos(getIdPagamentos(colecaoPagamentos));
				
		pagamentosAClassificarActionForm.setColecaoPagamentosAClassificar(colecaoPagamentosAClassificar);
		
		if ( colecaoPagamentosAClassificar != null && colecaoPagamentosAClassificar.isEmpty() ) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		
		}else{
			httpServletRequest.setAttribute("colecaoPagamentosAClassificar",colecaoPagamentosAClassificar);
			httpServletRequest.setAttribute("totalRegistros",colecaoPagamentosAClassificar.size());

			return retorno;
		}
		
	}
	
	private Collection<Integer> getIdPagamentos(Collection<Pagamento> pagamentos) {
		Collection<Integer> ids = new ArrayList<Integer>();
		
		for (Pagamento pagamento : pagamentos) {
			ids.add(pagamento.getId());
		}
		
		return ids;
	}
}
