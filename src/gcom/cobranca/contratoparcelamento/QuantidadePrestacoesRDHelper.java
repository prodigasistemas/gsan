package gcom.cobranca.contratoparcelamento;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * [UC1136] Inserir Contrato de Parcelamento por Cliente
 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
 * 
 * Helper utilizado para exibir uma lisa dos valores correspondentes às parcelas utilizadas a partir de uma RD. 
 * 
 * @author Mariana Victor
 * @data 31/08/2011
 */
public class QuantidadePrestacoesRDHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer quantidadeParcelas;
	
	private BigDecimal taxaJuros;

	private BigDecimal valorDaParcela;
	
	private Integer idQuantidadePrestacoes;

	public Integer getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(Integer quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public BigDecimal getTaxaJuros() {
		return taxaJuros;
	}

	public void setTaxaJuros(BigDecimal taxaJuros) {
		this.taxaJuros = taxaJuros;
	}

	public BigDecimal getValorDaParcela() {
		return valorDaParcela;
	}

	public void setValorDaParcela(BigDecimal valorDaParcela) {
		this.valorDaParcela = valorDaParcela;
	}

	public Integer getIdQuantidadePrestacoes() {
		return idQuantidadePrestacoes;
	}

	public void setIdQuantidadePrestacoes(Integer idQuantidadePrestacoes) {
		this.idQuantidadePrestacoes = idQuantidadePrestacoes;
	}
	

}
