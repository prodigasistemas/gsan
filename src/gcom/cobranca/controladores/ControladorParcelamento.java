package gcom.cobranca.controladores;

import gcom.cobranca.repositorios.IRepositorioParcelamentoHBM;
import gcom.cobranca.repositorios.RepositorioParcelamentoHBM;
import gcom.cobranca.repositorios.dto.CancelarParcelamentoDTO;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.FiltroCreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.FiltroDebitoACobrar;
import gcom.util.ControladorComum;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;

public class ControladorParcelamento extends ControladorComum {

	private static final long serialVersionUID = -2063305788601928963L;

	protected IRepositorioParcelamentoHBM repositorioParcelamento;

	public void ejbCreate() throws CreateException {
		repositorioParcelamento = RepositorioParcelamentoHBM.getInstancia();
	}

	public void cancelarParcelamentos() throws ControladorException {
		try {
			List<CancelarParcelamentoDTO> parcelamentos = repositorioParcelamento.pesquisarParcelamentosParaCancelar();

			for (CancelarParcelamentoDTO dto : parcelamentos) {
				cancelarParcelamento(dto.getIdParcelamento());
				
				BigDecimal acrescimos = calcularAcrescimosPorImpontualidade(dto.getSaldoDevedor());
				gerarContaIncluida();
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public void cancelarParcelamento(Integer idParcelamento) {
		cancelarDebitoACobrar(idParcelamento);
		cancelarCreditoARealizar(idParcelamento);
		
		// CANCELAR PARCELAMENTO
	}

	@SuppressWarnings("unchecked")
	private void cancelarDebitoACobrar(Integer idParcelamento) {
		try {
			Filtro filtro = new FiltroDebitoACobrar();
			filtro.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.PARCELAMENTO_ID, idParcelamento));
			Collection<DebitoACobrar> colecao = super.getControladorUtil().pesquisar(filtro, DebitoACobrar.class.toString());

			for (DebitoACobrar debito : colecao) {
				debito.setDebitoCreditoSituacaoAnterior(debito.getDebitoCreditoSituacaoAtual());
				debito.setDebitoCreditoSituacaoAtual(getSituacaoCancelada());
				debito.setUltimaAlteracao(new Date());

				super.getControladorUtil().atualizar(debito);
			}
		} catch (ControladorException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void cancelarCreditoARealizar(Integer idParcelamento) {
		try {
			Filtro filtro = new FiltroCreditoARealizar();
			filtro.adicionarParametro(new ParametroSimples(FiltroCreditoARealizar.PARCELAMENTO_ID, idParcelamento));
			Collection<CreditoARealizar> colecao = super.getControladorUtil().pesquisar(filtro, CreditoARealizar.class.toString());

			for (CreditoARealizar credito : colecao) {
				credito.setDebitoCreditoSituacaoAnterior(credito.getDebitoCreditoSituacaoAtual());
				credito.setDebitoCreditoSituacaoAtual(getSituacaoCancelada());
				credito.setUltimaAlteracao(new Date());

				super.getControladorUtil().atualizar(credito);
			}
		} catch (ControladorException e) {
			e.printStackTrace();
		}
	}

	private BigDecimal calcularAcrescimosPorImpontualidade(BigDecimal saldoDevedor) {
		return null;
	}

	private void gerarContaIncluida() {

	}

	private DebitoCreditoSituacao getSituacaoCancelada() {
		return new DebitoCreditoSituacao(DebitoCreditoSituacao.CANCELADA);
	}
}