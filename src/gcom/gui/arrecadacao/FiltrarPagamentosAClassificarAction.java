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
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarPagamentosAClassificarAction extends GcomAction {

	Fachada fachada;

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("filtrarPagamentosRecuperacaoDeCreditoAction");

		fachada = Fachada.getInstancia();

		PagamentosAClassificarActionForm form = (PagamentosAClassificarActionForm) actionForm;
		Integer situacaoPagamento = new Integer(form.getIdSituacaoPagamento());

		Collection<Pagamento> colecaoPagamentosAClassificar = obterPagamentosParaClassificar(form);

		form.setColecaoPagamentosAClassificar(colecaoPagamentosAClassificar);
		form.setSituacaoPagamento(getDescricaoSituacaoPagamento(situacaoPagamento));

		httpServletRequest.setAttribute("colecaoPagamentosAClassificar", colecaoPagamentosAClassificar);
		httpServletRequest.setAttribute("totalRegistros", colecaoPagamentosAClassificar.size());

		httpServletRequest.setAttribute("situacaoPesquisada", getDescricaoSituacaoPagamento(situacaoPagamento));
		httpServletRequest.setAttribute("qtdPagamentos", colecaoPagamentosAClassificar.size());

		return retorno;

	}

	private Collection<Pagamento> obterPagamentosParaClassificar(PagamentosAClassificarActionForm form) {
		Integer situacaoPagamento = new Integer(form.getIdSituacaoPagamento());

		FiltroClassificarPagamentos filtro = obterFiltro(form);

		@SuppressWarnings("unchecked")
		Collection<Pagamento> colecaoPagamentos = (Collection<Pagamento>) getFachada().pesquisar(filtro,
				Pagamento.class.getName());
		;

		if (colecaoPagamentos == null || colecaoPagamentos.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");

		} else {
			Collection<Pagamento> colecaoPagamentosAClassificar = filtrarEObterPagamentos(colecaoPagamentos);

			if (situacaoPagamento.equals(PagamentoSituacao.DOCUMENTO_INEXISTENTE_CONTA_PARCELADA))
				return fachada.validarRecuperacaoCreditoContaParcelada(colecaoPagamentosAClassificar);
			else
				return colecaoPagamentosAClassificar;
		}
	}

	private Collection<Pagamento> filtrarEObterPagamentos(Collection<Pagamento> colecaoPagamentos) {
		Collection<Pagamento> pagamentos = new ArrayList<Pagamento>();
		Collection<Integer> ids = null;
		Pagamento pagamento = new Pagamento();
		int tamanhoColecao = colecaoPagamentos.size();
		int paginacao = 0;
		
		if (tamanhoColecao > 500 ) {
			paginacao = 500;
		} else {
			paginacao = tamanhoColecao;
		}
		
		for (int i = 0; i < tamanhoColecao;) {
			ids = new ArrayList<Integer>();
			for (int j = 0; j < paginacao; j++) {
				pagamento = (Pagamento) colecaoPagamentos.toArray()[i];
				ids.add(pagamento.getId());
				i++;
			}
			pagamentos.addAll(fachada.obterPagamentos(ids));
			
			if ((tamanhoColecao-i < paginacao)) {
				paginacao = tamanhoColecao-i;
			}
		}
		return pagamentos;
	}

	private FiltroClassificarPagamentos obterFiltro(PagamentosAClassificarActionForm form) {
		Integer situacaoPagamento = new Integer(form.getIdSituacaoPagamento());
		Integer referenciaArrecadacao = Util.formatarMesAnoComBarraParaAnoMes(form.getReferenciaArrecadacao());
		String matriculaImovel = new String(form.getMatriculaImovel());
		String dataPagamento = new String(form.getDataPagamento());

		FiltroClassificarPagamentos filtro = new FiltroClassificarPagamentos(FiltroClassificarPagamentos.ORDER_BY);

		if (situacaoPagamento != null && referenciaArrecadacao != null) {

			filtro.adicionarParametro(
					new ParametroSimples(FiltroClassificarPagamentos.ID_SITUACAO_PAGAMENTO, situacaoPagamento));
			filtro.adicionarParametro(
					new ParametroSimples(FiltroClassificarPagamentos.REFERENCIA_ARRECADACAO, referenciaArrecadacao));
			filtro.adicionarParametro(new ParametroNaoNulo(FiltroClassificarPagamentos.ID_CONTA));

			if (isMatriculaImovelPreenchida(matriculaImovel)) {
				filtro.adicionarParametro(new ParametroSimples(FiltroClassificarPagamentos.ID_IMOVEL, matriculaImovel));
			}

			if (isDataPagementoPreenchida(dataPagamento)) {
				filtro.adicionarParametro(new ParametroSimples(FiltroClassificarPagamentos.DATA_PAGAMENTO,
						Util.converteStringParaDate(dataPagamento)));
			}
		}

		return filtro;
	}

	private String getDescricaoSituacaoPagamento(Integer situacaoPagamento) {
		FiltroPagamentoSituacao filtroSituacao = new FiltroPagamentoSituacao();
		filtroSituacao.adicionarParametro(new ParametroSimples(FiltroPagamentoSituacao.CODIGO, situacaoPagamento));

		PagamentoSituacao pagamentoSituacao = (PagamentoSituacao) (getFachada()
				.pesquisar(filtroSituacao, PagamentoSituacao.class.getName()).iterator().next());
		return pagamentoSituacao.getDescricao();
	}

	private boolean isMatriculaImovelPreenchida(String matriculaImovel) {
		return matriculaImovel != null && !matriculaImovel.equals("");
	}

	private boolean isDataPagementoPreenchida(String dataPagamento) {
		return dataPagamento != null && !dataPagamento.equals("");
	}

}
