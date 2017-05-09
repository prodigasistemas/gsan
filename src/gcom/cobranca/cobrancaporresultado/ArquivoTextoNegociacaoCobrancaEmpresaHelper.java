package gcom.cobranca.cobrancaporresultado;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.util.ConstantesSistema;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ArquivoTextoNegociacaoCobrancaEmpresaHelper implements Serializable {

	private static final long serialVersionUID = -5644538483971160431L;

	private int tipoNegociacao;
	private Integer idNegociacao;
	private Date dataNegociacao;
	private Date dataVencimentoNegociacao;
	private BigDecimal valorDivida;
	private BigDecimal valorDescontos;
	private BigDecimal valorEntrada;
	private BigDecimal valorParcela;
	private Integer quantidadePrestacoes;
	
	private List<Integer> idsContas;

	public ArquivoTextoNegociacaoCobrancaEmpresaHelper() {
	}
	
	public ArquivoTextoNegociacaoCobrancaEmpresaHelper(int tipoNegociacao, Integer idNegociacao, Date dataNegociacao, Date dataVencimentoNegociacao, BigDecimal valorDivida,
			BigDecimal valorDescontos, BigDecimal valorEntrada, BigDecimal valorParcela, Integer quantidadePrestacoes) {
		this.tipoNegociacao = tipoNegociacao;
		this.idNegociacao = idNegociacao;
		this.dataNegociacao = dataNegociacao;
		this.dataVencimentoNegociacao = dataVencimentoNegociacao;
		this.valorDivida = valorDivida;
		this.valorDescontos = valorDescontos;
		this.valorEntrada = valorEntrada;
		this.valorParcela = valorParcela;
		this.quantidadePrestacoes = quantidadePrestacoes;
	}
	
	public int getTipoNegociacao() {
		return tipoNegociacao;
	}

	public void setTipoNegociacao(int tipoNegociacao) {
		this.tipoNegociacao = tipoNegociacao;
	}

	public Integer getIdNegociacao() {
		return idNegociacao;
	}

	public void setIdNegociacao(Integer idNegociacao) {
		this.idNegociacao = idNegociacao;
	}

	public Date getDataNegociacao() {
		return dataNegociacao;
	}

	public void setDataNegociacao(Date dataNegociacao) {
		this.dataNegociacao = dataNegociacao;
	}

	public Date getDataVencimentoNegociacao() {
		return dataVencimentoNegociacao;
	}

	public void setDataVencimentoNegociacao(Date dataVencimentoNegociacao) {
		this.dataVencimentoNegociacao = dataVencimentoNegociacao;
	}

	public BigDecimal getValorDivida() {
		return valorDivida;
	}

	public void setValorDivida(BigDecimal valorDivida) {
		this.valorDivida = valorDivida;
	}

	public BigDecimal getValorDescontos() {
		return valorDescontos;
	}

	public void setValorDescontos(BigDecimal valorDescontos) {
		this.valorDescontos = valorDescontos;
	}

	public BigDecimal getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(BigDecimal valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	public Integer getQuantidadePrestacoes() {
		return quantidadePrestacoes;
	}

	public void setQuantidadePrestacoes(Integer quantidadePrestacoes) {
		this.quantidadePrestacoes = quantidadePrestacoes;
	}

	public List<Integer> getIdsContas() {
		return idsContas;
	}

	public void setIdsContas(List<Integer> idsContas) {
		this.idsContas = idsContas;
	}
	
}
