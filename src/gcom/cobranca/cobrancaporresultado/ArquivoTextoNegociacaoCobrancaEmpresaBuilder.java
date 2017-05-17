package gcom.cobranca.cobrancaporresultado;

import gcom.util.ConstantesSistema;

import java.util.ArrayList;
import java.util.List;

public class ArquivoTextoNegociacaoCobrancaEmpresaBuilder {
	
	protected NegociacaoCobrancaEmpresa negociacao = null;
	protected ArquivoTextoNegociacaoCobrancaEmpresaHelper helper = null;
	
	public ArquivoTextoNegociacaoCobrancaEmpresaBuilder() {
		helper = new ArquivoTextoNegociacaoCobrancaEmpresaHelper();
	}
	
	public ArquivoTextoNegociacaoCobrancaEmpresaBuilder(NegociacaoCobrancaEmpresa negociacao) {
		helper = new ArquivoTextoNegociacaoCobrancaEmpresaHelper();
		this.negociacao = negociacao;
	}

	public ArquivoTextoNegociacaoCobrancaEmpresaHelper buildHelper() {
		
		if (negociacao.getParcelamento() != null) {
			buildParcelamentoHelper();
		} else if (negociacao.getCobrancaDocumento() != null) {
			buildExtratoHelper();
		} else if (negociacao.getGuiaPagamentoGeral() != null) {
			buildGuiaHelper();
		}
		
		buildContas();
		
		return helper;
	}
	
	protected void buildParcelamentoHelper() {
		helper.setTipoNegociacao(ConstantesSistema.TIPO_NEGOCIACAO_PARCELAMENTO);
		helper.setIdNegociacao(negociacao.getParcelamento().getId());
		helper.setDataNegociacao(negociacao.getParcelamento().getParcelamento());
		helper.setDataVencimentoNegociacao(null);
		helper.setValorDivida(negociacao.getParcelamento().getValorNegociado());
		helper.setValorDescontos(negociacao.getParcelamento().getValorDescontoAcrescimos());
		helper.setValorEntrada(negociacao.getParcelamento().getValorEntrada());
		helper.setValorParcela(negociacao.getParcelamento().getValorPrestacao());
		helper.setQuantidadePrestacoes(negociacao.getParcelamento().getNumeroPrestacoes().intValue());
	}

	protected void buildExtratoHelper() {
		helper.setTipoNegociacao(ConstantesSistema.TIPO_NEGOCIACAO_EXTRATO);
		helper.setIdNegociacao(negociacao.getCobrancaDocumento().getId());
		helper.setDataNegociacao(negociacao.getCobrancaDocumento().getEmissao());
		helper.setDataVencimentoNegociacao(null);
		helper.setValorDivida(negociacao.getCobrancaDocumento().getValorDocumento());
		helper.setValorDescontos(negociacao.getCobrancaDocumento().getValorDesconto());
		helper.setValorEntrada(null);
		helper.setValorParcela(negociacao.getCobrancaDocumento().getValorDocumento());
		helper.setQuantidadePrestacoes(new Integer(1));
	}
	
	protected void buildGuiaHelper() {
		
		if (negociacao.getGuiaPagamentoGeral().getGuiaPagamento() != null) {
			buildGuiaPagamentoHelper();
		} else {
			buildGuiaPagamentoHistoricoHelper();
		}
		
	}
	
	protected void buildGuiaPagamentoHelper() {
		helper.setTipoNegociacao(ConstantesSistema.TIPO_NEGOCIACAO_GUIA);
		helper.setIdNegociacao(negociacao.getGuiaPagamentoGeral().getId());
		helper.setDataNegociacao(negociacao.getGuiaPagamentoGeral().getGuiaPagamento().getDataEmissao());
		helper.setDataVencimentoNegociacao(negociacao.getGuiaPagamentoGeral().getGuiaPagamento().getDataVencimento());
		helper.setValorDivida(negociacao.getGuiaPagamentoGeral().getGuiaPagamento().getValorDebito());
		helper.setValorDescontos(null);
		helper.setValorEntrada(null);
		helper.setValorParcela(null);
		helper.setQuantidadePrestacoes(new Integer(1));
	}
	
	protected void buildGuiaPagamentoHistoricoHelper() {
		helper.setTipoNegociacao(ConstantesSistema.TIPO_NEGOCIACAO_GUIA);
		helper.setIdNegociacao(negociacao.getGuiaPagamentoGeral().getId());
		helper.setDataNegociacao(negociacao.getGuiaPagamentoGeral().getGuiaPagamentoHistorico().getDataEmissao());
		helper.setDataVencimentoNegociacao(negociacao.getGuiaPagamentoGeral().getGuiaPagamentoHistorico().getDataVencimento());
		helper.setValorDivida(negociacao.getGuiaPagamentoGeral().getGuiaPagamentoHistorico().getValorDebito());
		helper.setValorDescontos(null);
		helper.setValorEntrada(null);
		helper.setValorParcela(null);
		helper.setQuantidadePrestacoes(new Integer(1));
	}
	
	protected void buildContas() {
		helper.setIdsContas(obterIdsContas());
	}
	
	private List<Integer> obterIdsContas() {
		List<Integer> ids = new ArrayList<Integer>();
		
		for (NegociacaoContaCobrancaEmpresa contaNegociada : negociacao.getContasNegociadas()) {
			ids.add(contaNegociada.getContaGeral().getId());
		}
		
		return ids;
	}
}

