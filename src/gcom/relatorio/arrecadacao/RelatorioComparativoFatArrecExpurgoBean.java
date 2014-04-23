package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

/**
 * [UC00744] Gerar Comparativo do Faturamento, Arrecadação e Expurgo
 * 
 * @author Sávio Luiz
 *
 * @date 09/12/2008
 */
public class RelatorioComparativoFatArrecExpurgoBean implements RelatorioBean {

	private static final long serialVersionUID = 1L;

	private String unidadeNegocio;

	private String gerenciaRegional;

	private String codigoCentroCusto;

	private String nomeLocalidade;

	private String valorItemFaturado;
	
	private String valorItemFaturadoLiquido;

	private String valorItemArrecadacao;
	
	private String valorItemFaturadoBruto;
	
	private String valorItemCancelados;

	private String valorPagamentoExpurgado;

	private String valorArrecadacaoPagamento;
	
	private String percentualArrecadadoFaturadoLiquido;
	
	private String percentualArrecadadoFaturamentoEmConta;
	
	private String qtdeItemFaturado;
	
	private String qtdeItemArrecadacao;
	
	private String qtdeItemFaturadoLiquido;
	
	

	public RelatorioComparativoFatArrecExpurgoBean(String unidadeNegocio,
			String gerenciaRegional, String codigoCentroCusto,
			String nomeLocalidade, String valorItemFaturado,String valorItemFaturadoLiquido,
			String valorItemArrecadacao,String valorItemFaturadoBruto,String valorItemCancelados,
			String valorPagamentoExpurgado,
			String valorArrecadacaoPagamento, String qtdeItemFaturado, 
			String qtdeItemArrecadacao, String qtdeItemFaturadoLiquido) {

		
		this.unidadeNegocio = unidadeNegocio;
		this.gerenciaRegional = gerenciaRegional;
		this.codigoCentroCusto = codigoCentroCusto;
		this.nomeLocalidade = nomeLocalidade;
		this.valorItemFaturado = valorItemFaturado;
		this.valorItemFaturadoLiquido = valorItemFaturadoLiquido;
		this.valorItemArrecadacao = valorItemArrecadacao;
		this.valorItemFaturadoBruto = valorItemFaturadoBruto;
		this.valorItemCancelados = valorItemCancelados;
		this.valorPagamentoExpurgado = valorPagamentoExpurgado;
		this.valorArrecadacaoPagamento = valorArrecadacaoPagamento;
		this.qtdeItemFaturado = qtdeItemFaturado;
		this.qtdeItemArrecadacao = qtdeItemArrecadacao;
		this.qtdeItemFaturadoLiquido = qtdeItemFaturadoLiquido;
	}
	

	public String getPercentualArrecadadoFaturamentoEmConta() {
		return percentualArrecadadoFaturamentoEmConta;
	}
	
	public void setPercentualArrecadadoFaturamentoEmConta(
			String percentualArrecadadoFaturamentoEmConta) {
		this.percentualArrecadadoFaturamentoEmConta = percentualArrecadadoFaturamentoEmConta;
	}

	public String getCodigoCentroCusto() {
		return codigoCentroCusto;
	}

	public void setCodigoCentroCusto(String codigoCentroCusto) {
		this.codigoCentroCusto = codigoCentroCusto;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getValorItemArrecadacao() {
		return valorItemArrecadacao;
	}

	public void setValorItemArrecadacao(String valorItemArrecadacao) {
		this.valorItemArrecadacao = valorItemArrecadacao;
	}

	public String getValorItemFaturado() {
		return valorItemFaturado;
	}

	public void setValorItemFaturado(String valorItemFaturado) {
		this.valorItemFaturado = valorItemFaturado;
	}

	public String getValorArrecadacaoPagamento() {
		return valorArrecadacaoPagamento;
	}

	public void setValorArrecadacaoPagamento(String valorArrecadacaoPagamento) {
		this.valorArrecadacaoPagamento = valorArrecadacaoPagamento;
	}

	public String getValorPagamentoExpurgado() {
		return valorPagamentoExpurgado;
	}

	public void setValorPagamentoExpurgado(String valorPagamentoExpurgado) {
		this.valorPagamentoExpurgado = valorPagamentoExpurgado;
	}

	public String getValorItemFaturadoLiquido() {
		return valorItemFaturadoLiquido;
	}

	public void setValorItemFaturadoLiquido(String valorItemFaturadoLiquido) {
		this.valorItemFaturadoLiquido = valorItemFaturadoLiquido;
	}

	public String getQtdeItemArrecadacao() {
		return qtdeItemArrecadacao;
	}

	public void setQtdeItemArrecadacao(String qtdeItemArrecadacao) {
		this.qtdeItemArrecadacao = qtdeItemArrecadacao;
	}

	public String getQtdeItemFaturado() {
		return qtdeItemFaturado;
	}

	public void setQtdeItemFaturado(String qtdeItemFaturado) {
		this.qtdeItemFaturado = qtdeItemFaturado;
	}

	public String getQtdeItemFaturadoLiquido() {
		return qtdeItemFaturadoLiquido;
	}

	public void setQtdeItemFaturadoLiquido(String qtdeItemFaturadoLiquido) {
		this.qtdeItemFaturadoLiquido = qtdeItemFaturadoLiquido;
	}


	public String getValorItemFaturadoBruto() {
		return valorItemFaturadoBruto;
	}


	public void setValorItemFaturadoBruto(String valorItemFaturadoBruto) {
		this.valorItemFaturadoBruto = valorItemFaturadoBruto;
	}


	public String getValorItemCancelados() {
		return valorItemCancelados;
	}


	public void setValorItemCancelados(String valorItemCancelados) {
		this.valorItemCancelados = valorItemCancelados;
	}


	public String getPercentualArrecadadoFaturadoLiquido() {
		return percentualArrecadadoFaturadoLiquido;
	}


	public void setPercentualArrecadadoFaturadoLiquido(
			String percentualArrecadadoFaturadoLiquido) {
		this.percentualArrecadadoFaturadoLiquido = percentualArrecadadoFaturadoLiquido;
	}
	
	





	

}
