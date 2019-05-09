package gcom.gui.arrecadacao;

import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.fachada.Fachada;
import gcom.faturamento.ControladorFaturamento;
import gcom.faturamento.credito.CreditoOrigem;
import gcom.faturamento.credito.CreditoTipo;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jboss.logging.Logger;

public class ClassificarPagamentosAction extends GcomAction {

	private CreditoTipo creditoTipo;
	private CreditoOrigem creditoOrigem;
	private static Logger logger = Logger.getLogger(ClassificarPagamentosAction.class);
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		PagamentosAClassificarActionForm form = (PagamentosAClassificarActionForm) actionForm;
		Usuario usuarioLogado = this.getUsuarioLogado(httpServletRequest);
		String parametroDevolver = (String) httpServletRequest.getParameter("devolver");
		
		this.setParametros(new Integer(form.getIdSituacaoPagamento()));
		
		String[] registrosClassificacao = form.getIdRegistrosClassificacao();
		logger.info("RECUPERACAO CREDITO 2.1");
		Collection<Pagamento> colecaoPagamentos = obterPagamentosSelecionados(form, registrosClassificacao);
		Integer idSituacaoPagamento = new Integer(form.getIdSituacaoPagamento());
		
		try {
			logger.info("			RECUPERACAO CREDITO 2.4: " + colecaoPagamentos.size() + " pagamentos");
			fachada.classificarPagamentosResolvidos(colecaoPagamentos, usuarioLogado, this.creditoTipo, 
					this.creditoOrigem, isDevolucao(sessao, parametroDevolver), idSituacaoPagamento);
			
		} catch (ControladorException e) {
			e.printStackTrace();
		}
		
		
		montarPaginaSucesso(httpServletRequest,
				"Pagamentos selecionados já classificados",
				"Voltar",
				"exibirFiltrarPagamentosAClassificarAction.do?menu=sim");
		
		sessao.removeAttribute("contas");
		return retorno;
	}

	private boolean isDevolucao(HttpSession sessao, String parametroDevolver) {
		boolean devolver = true;
		
		if(parametroDevolver == null){
			parametroDevolver = (String) sessao.getAttribute("devolver");
		}
		
		if (parametroDevolver.equals("0")) {
			devolver = false;
		}
		return devolver;
	}

	private Collection<Pagamento> obterPagamentosSelecionados(PagamentosAClassificarActionForm form, String[] registrosClassificacao) {
		Collection<Pagamento> colecaoPagamentos = new ArrayList<Pagamento>();
		if(!form.getColecaoPagamentosAClassificar().isEmpty()){
			Collection<Pagamento> pagamentos =	(Collection<Pagamento>) form.getColecaoPagamentosAClassificar();
			
			Iterator<Pagamento> iteratorPagamentos = pagamentos.iterator();
			logger.info("	RECUPERACAO CREDITO 2.2");
			while (iteratorPagamentos.hasNext()) {
				Pagamento pagamento = (Pagamento) iteratorPagamentos.next();
				for (int i = 0; i < registrosClassificacao.length; i++) {
					String idPagamento = registrosClassificacao[i];
					if(idPagamento.equals(pagamento.getId().toString())){
						logger.info("		RECUPERACAO CREDITO 2.3 (adicionado) " + pagamento.getId());
						colecaoPagamentos.add(pagamento);
					}
				}
			}
		}
		
		return colecaoPagamentos;
	}
	
	private void setParametros(Integer idPagamentoOriginal) {
		if (idPagamentoOriginal.equals(PagamentoSituacao.PAGAMENTO_EM_DUPLICIDADE)) {
			
			this.creditoTipo = new CreditoTipo(CreditoTipo.DEVOLUCAO_PAGAMENTOS_DUPLICIDADE);
			this.creditoOrigem = new CreditoOrigem(CreditoOrigem.CONTAS_PAGAS_EM_DUPLICIDADE_EXCESSO);

		} else if (idPagamentoOriginal.equals(PagamentoSituacao.DOCUMENTO_INEXISTENTE_CONTA_CANCELADA)
				|| idPagamentoOriginal.equals(PagamentoSituacao.DOCUMENTO_INEXISTENTE_ERRO_PROCESSAMENTO)) {
			
			this.creditoTipo = new CreditoTipo(CreditoTipo.DEVOLUCAO_OUTROS_VALORES);
			this.creditoOrigem = new CreditoOrigem(CreditoOrigem.RECUPERACAO_CREDITO_CONTA_CANCELADA);

		} else if (idPagamentoOriginal.equals(PagamentoSituacao.DOCUMENTO_INEXISTENTE_CONTA_PARCELADA)) {

			this.creditoTipo = new CreditoTipo(CreditoTipo.DEVOLUCAO_OUTROS_VALORES);
			this.creditoOrigem = new CreditoOrigem(CreditoOrigem.RECUPERACAO_CREDITO_CONTA_PARCELADA);
			
		}
	}

	public CreditoTipo getCreditoTipo() {
		return creditoTipo;
	}

	public void setCreditoTipo(CreditoTipo creditoTipo) {
		this.creditoTipo = creditoTipo;
	}

	public CreditoOrigem getCreditoOrigem() {
		return creditoOrigem;
	}

	public void setCreditoOrigem(CreditoOrigem creditoOrigem) {
		this.creditoOrigem = creditoOrigem;
	}

}
