package gcom.gui.arrecadacao;

import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarPagamentosAClassificarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirFiltrarPagamentosAClassificar");

		HttpSession sessao = httpServletRequest.getSession(false);

		PagamentosAClassificarActionForm form = (PagamentosAClassificarActionForm) actionForm;

		//this.limparFormulario(form);
		
		Collection<PagamentoSituacao> listaSituacoesPagamento = obterSituacaoPagamento();

		httpServletRequest.setAttribute("listaSituacoesPagamento", listaSituacoesPagamento);
		
		sessao.setAttribute("pagamentosAClassificarActionForm",form);

		return retorno;
	}
		
	private Collection<PagamentoSituacao> obterSituacaoPagamento () {
		Collection<PagamentoSituacao> listaSituacoesPagamento = new ArrayList<PagamentoSituacao>();
			
		PagamentoSituacao pagamentoSituacao = new PagamentoSituacao();
		pagamentoSituacao.setId(PagamentoSituacao.DOCUMENTO_INEXISTENTE_CONTA_PARCELADA);
		pagamentoSituacao.setDescricao("CANCELADO POR PARCELAMENTO");
			
		listaSituacoesPagamento.add(pagamentoSituacao);
		
		
		pagamentoSituacao = new PagamentoSituacao();
		
		pagamentoSituacao.setId(PagamentoSituacao.DOCUMENTO_INEXISTENTE_CONTA_CANCELADA);
		pagamentoSituacao.setDescricao("CONTA CANCELADA");
		listaSituacoesPagamento.add(pagamentoSituacao);
		
		pagamentoSituacao = new PagamentoSituacao();
			
		pagamentoSituacao.setId(PagamentoSituacao.PAGAMENTO_EM_DUPLICIDADE);
		pagamentoSituacao.setDescricao("PAGAMENTO EM DUPLICIDADE");
		listaSituacoesPagamento.add(pagamentoSituacao);
		
		return listaSituacoesPagamento;
	}
	
	private void limparFormulario(PagamentosAClassificarActionForm formulario) {
		formulario.setIdRegistrosClassificacao(null);
		formulario.setColecaoPagamentosAClassificar(null);
	}
}
