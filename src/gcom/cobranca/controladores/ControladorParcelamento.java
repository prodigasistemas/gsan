package gcom.cobranca.controladores;

import gcom.cobranca.repositorios.IRepositorioParcelamentoHBM;
import gcom.cobranca.repositorios.RepositorioParcelamentoHBM;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.FiltroDebitoACobrar;
import gcom.faturamento.debito.FiltroDebitoCreditoSituacao;
import gcom.util.ControladorComum;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;

public class ControladorParcelamento extends ControladorComum {

	private static final long serialVersionUID = -2063305788601928963L;

	protected IRepositorioParcelamentoHBM repositorioParcelamento;
	
	private DebitoCreditoSituacao situacaoCancelada;

	public void ejbCreate() throws CreateException {
		repositorioParcelamento = RepositorioParcelamentoHBM.getInstancia();
	}

	public void cancelarParcelamentos() throws ControladorException {
		try {
			List<Object[]> parcelamentos = repositorioParcelamento.pesquisarParcelamentosParaCancelar();
			FiltroDebitoCreditoSituacao filtroDebitoCreditoSituacao = new  FiltroDebitoCreditoSituacao();
			filtroDebitoCreditoSituacao.adicionarParametro(new ParametroSimples(FiltroDebitoCreditoSituacao.ID, DebitoCreditoSituacao.CANCELADA));
			situacaoCancelada = (DebitoCreditoSituacao) super.getControladorUtil().pesquisar(filtroDebitoCreditoSituacao, DebitoCreditoSituacao.class.toString()).iterator().next();
			
			for (Object[] parcelamento : parcelamentos) {
				Integer idParcelamento = (Integer) parcelamento[0];
				BigDecimal saldoDevedor = (BigDecimal) parcelamento[1];
				
				cancelarParcelamento(idParcelamento);
				BigDecimal acrescimos = calcularAcrescimosPorImpontualidade(saldoDevedor);
				gerarContaIncluida();
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	public void cancelarParcelamento(Integer idParcelamento) {
		cancelarDebitoACobrar(idParcelamento);
		cancelarCreditoARealizar(idParcelamento);
	}

	private void cancelarDebitoACobrar(Integer idParcelamento) {
		try {
			FiltroDebitoACobrar filtroDebitoACobrar = new FiltroDebitoACobrar();
			filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.PARCELAMENTO_ID, idParcelamento));
			Collection<DebitoACobrar> colecaoDebitoACobrar = super.getControladorUtil().pesquisar(filtroDebitoACobrar, DebitoACobrar.class.toString());
			
			if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
				for (DebitoACobrar debitoACobrar : colecaoDebitoACobrar) {
					debitoACobrar.setDebitoCreditoSituacaoAnterior(debitoACobrar.getDebitoCreditoSituacaoAtual());
					debitoACobrar.setDebitoCreditoSituacaoAtual(situacaoCancelada);
					debitoACobrar.setUltimaAlteracao(new Date());
					
					super.getControladorUtil().atualizar(debitoACobrar);
				}
			}
			
		} catch (ControladorException e) {
			e.printStackTrace();
		}
	}

	private void cancelarCreditoARealizar(Integer idParcelamento) {

	}

	private BigDecimal calcularAcrescimosPorImpontualidade(BigDecimal saldoDevedor) {
		return null;
	}

	private void gerarContaIncluida() {

	}
}