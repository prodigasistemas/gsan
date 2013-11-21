package gcom.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FaixaDocumentosAReceber implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private BigDecimal valorFaixaInicial;
	
	private BigDecimal valorFaixaFinal;
	
	private Date ultimaAlteracao;

	
	public FaixaDocumentosAReceber() {
		super();
	}

	public FaixaDocumentosAReceber(Integer id, BigDecimal valorFaixaInicial,
			BigDecimal valorFaixaFinal, Date ultimaAlteracao) {
		super();
		this.id = id;
		this.valorFaixaInicial = valorFaixaInicial;
		this.valorFaixaFinal = valorFaixaFinal;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorFaixaFinal() {
		return valorFaixaFinal;
	}

	public void setValorFaixaFinal(BigDecimal valorFaixaFinal) {
		this.valorFaixaFinal = valorFaixaFinal;
	}

	public BigDecimal getValorFaixaInicial() {
		return valorFaixaInicial;
	}

	public void setValorFaixaInicial(BigDecimal valorFaixaInicial) {
		this.valorFaixaInicial = valorFaixaInicial;
	}

}
