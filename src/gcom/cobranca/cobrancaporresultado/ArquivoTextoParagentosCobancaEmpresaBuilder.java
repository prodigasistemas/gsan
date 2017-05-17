package gcom.cobranca.cobrancaporresultado;

import java.math.BigDecimal;
import java.util.Date;

import gcom.util.Util;

public class ArquivoTextoParagentosCobancaEmpresaBuilder {

	protected ArquivoTextoPagamentoContasCobrancaEmpresaHelper helper;
	protected Object[] dados;
	
	public ArquivoTextoParagentosCobancaEmpresaBuilder() {
		helper = new ArquivoTextoPagamentoContasCobrancaEmpresaHelper();
	}
	
	public ArquivoTextoParagentosCobancaEmpresaBuilder(Object[] dados) {
		helper = new ArquivoTextoPagamentoContasCobrancaEmpresaHelper();
		this.dados = dados;
	}
	
	public ArquivoTextoPagamentoContasCobrancaEmpresaHelper buildHelper() {
		
		helper.setMatricula(dados[0] != null ? (Integer)dados[0] : null);
		helper.setNomeClienteConta((String) (dados[1]));
		helper.setReferenciaConta((Integer) (dados[2]));
		helper.setValorConta((BigDecimal) (dados[3]));
		helper.setReferenciaPagamento((Integer) (dados[4]));
		helper.setTipoPagamento((Integer) (dados[5]));
		helper.setNumeroParcelas((Integer) (dados[6]));
		helper.setNumeroParcelasTotal((Integer) (dados[7]));
		helper.setValorPagamentoPrincipal((BigDecimal) (dados[8]));
		helper.setValorEncargos((BigDecimal) (dados[9]));
		helper.setPercentualEmpresa((BigDecimal) (dados[10]));
		helper.setIdUnidadeNegocio((Integer) (dados[11]));
		helper.setNomeUnidadeNegocio((String) (dados[12]));
		helper.setIdLocalidade((Integer) (dados[13]));
		helper.setNomeLocalidade((String) (dados[14]));
		helper.setNumeroQuadra((Integer) (dados[15]));
		helper.setLote((Integer) (dados[16]));
		helper.setSubLote((Integer) (dados[17]));
		helper.setCodigoRota((Integer) (dados[18]));
		helper.setSequencialRota((Integer) (dados[19]));
		helper.setCodigoSetor((Integer) (dados[20]));
		helper.setDataPagamento((Date) (dados[21]));
		helper.setIdEmpresaCobrancaContaPagamento((Integer)dados[22]);
		helper.setValorPagamentoTotal(Util.somaBigDecimal(helper.getValorPagamentoPrincipal(), helper.getValorEncargos()));
		helper.setValorPagamentoEmpresa(calcularValorPagamentoEmpresa());

		return helper;
	}
	
	private BigDecimal calcularValorPagamentoEmpresa() {
		BigDecimal result = helper.getValorPagamentoTotal().multiply(helper.getPercentualEmpresa());
		result = result.setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal valorPagamentoEmpresa = result.divide(new BigDecimal("100"));
		valorPagamentoEmpresa = valorPagamentoEmpresa.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return valorPagamentoEmpresa;
	}
}
