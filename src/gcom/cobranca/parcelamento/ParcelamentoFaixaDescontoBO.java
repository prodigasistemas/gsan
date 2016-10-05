package gcom.cobranca.parcelamento;

import gcom.cobranca.IRepositorioCobranca;
import gcom.faturamento.conta.Conta;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;

import java.math.BigDecimal;

public class ParcelamentoFaixaDescontoBO {

	protected IRepositorioCobranca repositorioCobranca = null;

	public ParcelamentoFaixaDescontoBO(IRepositorioCobranca repositorioCobranca) {
		super();

		this.repositorioCobranca = repositorioCobranca;
	}

	public BigDecimal calcularValorDescontoConta(Conta conta) throws ErroRepositorioException {
		BigDecimal percentualDesconto = repositorioCobranca.getPercentualDescontoPorFaixa(conta.getReferencia());
		percentualDesconto = Util.dividirArredondando(percentualDesconto.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO), ConstantesSistema.CEM);
		return conta.getValorTotal().multiply(percentualDesconto).setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
	}
}
