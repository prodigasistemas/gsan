package gcom.faturamento;

import java.math.BigDecimal;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class ContaRevisaoFaixaValor {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private BigDecimal valorFaixaInicio;
	
	private BigDecimal valorFaixaFim;
	
	private Date ultimaAlteracao;

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

	public BigDecimal getValorFaixaFim() {
		return valorFaixaFim;
	}

	public void setValorFaixaFim(BigDecimal valorFaixaFim) {
		this.valorFaixaFim = valorFaixaFim;
	}

	public BigDecimal getValorFaixaInicio() {
		return valorFaixaInicio;
	}

	public void setValorFaixaInicio(BigDecimal valorFaixaInicio) {
		this.valorFaixaInicio = valorFaixaInicio;
	}

	public ContaRevisaoFaixaValor(BigDecimal valorFaixaInicio, BigDecimal valorFaixaFim, Date ultimaAlteracao) {
		super();
		
		this.valorFaixaInicio = valorFaixaInicio;
		this.valorFaixaFim = valorFaixaFim;
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public ContaRevisaoFaixaValor(){}
	
}
