package gcom.gui.arrecadacao;

import gcom.arrecadacao.FiltroClassificarPagamentos;
import gcom.arrecadacao.pagamento.FiltroPagamentoSituacao;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarPagamentosAClassificarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("filtrarPagamentosRecuperacaoDeCreditoAction");

		Fachada fachada = Fachada.getInstancia();
		
		PagamentosAClassificarActionForm form = (PagamentosAClassificarActionForm) actionForm;

		Integer situacaoPagamento = new Integer(form.getIdSituacaoPagamento());
		Integer referenciaArrecadacao = Util.formatarMesAnoComBarraParaAnoMes(form.getReferenciaArrecadacao());
		String matriculaImovel = new String (form.getMatriculaImovel());
		String dataPagamento = new String (form.getDataPagamento());

		FiltroClassificarPagamentos filtroClassificarPagamentos = new FiltroClassificarPagamentos(FiltroClassificarPagamentos.ORDER_BY);

		
		if ( situacaoPagamento != null && referenciaArrecadacao != null ) {
			
			filtroClassificarPagamentos.adicionarParametro(new ParametroSimples(FiltroClassificarPagamentos.ID_SITUACAO_PAGAMENTO, situacaoPagamento));
			filtroClassificarPagamentos.adicionarParametro(new ParametroSimples(FiltroClassificarPagamentos.REFERENCIA_ARRECADACAO, referenciaArrecadacao));
			filtroClassificarPagamentos.adicionarParametro(new ParametroNaoNulo(FiltroClassificarPagamentos.ID_CONTA));
			
			if (isMatriculaImovelPreenchida(matriculaImovel)) {
				filtroClassificarPagamentos.adicionarParametro(new ParametroSimples(FiltroClassificarPagamentos.ID_IMOVEL, matriculaImovel));
			}
			
			if (isDataPagementoPreenchida(dataPagamento)) {
				filtroClassificarPagamentos.adicionarParametro(new ParametroSimples(FiltroClassificarPagamentos.DATA_PAGAMENTO, Util.converteStringParaDate(dataPagamento)));
			}
			
		}

		@SuppressWarnings("unchecked")
		Collection<Pagamento> colecaoPagamentos = (Collection<Pagamento>) getFachada().pesquisar(filtroClassificarPagamentos, Pagamento.class.getName());;
		
		if (colecaoPagamentos == null || colecaoPagamentos.isEmpty() ) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			
		}else{
			Collection<Pagamento> colecaoPagamentosAClassificar = fachada.obterPagamentos(getIdPagamentos(colecaoPagamentos));
					
			form.setColecaoPagamentosAClassificar(colecaoPagamentosAClassificar);
			form.setSituacaoPagamento(getDescricaoSituacaoPagamento(situacaoPagamento));
				
			httpServletRequest.setAttribute("colecaoPagamentosAClassificar",colecaoPagamentosAClassificar);
			httpServletRequest.setAttribute("totalRegistros",colecaoPagamentosAClassificar.size());
	
			httpServletRequest.setAttribute("situacaoPesquisada",colecaoPagamentosAClassificar.size());
			httpServletRequest.setAttribute("qtdPagamentos",colecaoPagamentosAClassificar.size());
			
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
	
	private String getDescricaoSituacaoPagamento(Integer situacaoPagamento) {
		FiltroPagamentoSituacao filtroSituacao = new FiltroPagamentoSituacao();
		filtroSituacao.adicionarParametro(new ParametroSimples(FiltroPagamentoSituacao.CODIGO, situacaoPagamento));
		
		PagamentoSituacao pagamentoSituacao = (PagamentoSituacao) (getFachada().pesquisar(filtroSituacao, PagamentoSituacao.class.getName()).iterator().next());
		return pagamentoSituacao.getDescricao();
	}
	
	private boolean isMatriculaImovelPreenchida(String matriculaImovel) {
		return matriculaImovel != null && !matriculaImovel.equals("");
	}
	
	private boolean isDataPagementoPreenchida(String dataPagamento) {
		return dataPagamento != null && !dataPagamento.equals("");
	}
	
}
