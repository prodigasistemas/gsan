package gcom.faturamento.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DeclaracaoQuitacaoAnualDebitosItemHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idConta;
	private Date dataSituacao;
	private Integer anoMesReferencia;
	private String descricaoSituacao;
	private BigDecimal valorTotalConta;
	
	//Dados para Calculo
    private BigDecimal valorAgua;
    private BigDecimal valorEsgoto;
    private BigDecimal valorDebitos;
    private BigDecimal valorCreditos;
    private BigDecimal valorImposto;
   
	public DeclaracaoQuitacaoAnualDebitosItemHelper(
			Integer idConta, Integer anoMesReferenciaConta,
			BigDecimal valorAgua, BigDecimal valorEsgoto,
			BigDecimal valorDebitos, BigDecimal valorCreditos,
			BigDecimal valorImposto,
			Date dataSituacao,
			String descricaoSituacao) {
		this.idConta = idConta;
		this.dataSituacao = dataSituacao;
		this.anoMesReferencia = anoMesReferenciaConta;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
		this.valorDebitos = valorDebitos;
		this.valorCreditos = valorCreditos;
		this.valorImposto = valorImposto;
		this.descricaoSituacao = descricaoSituacao;
	}
	public Integer getIdConta() {
		return idConta;
	}
	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}
	public Date getDataSituacao() {
		return dataSituacao;
	}
	public void setDataSituacao(Date dataSituacao) {
		this.dataSituacao = dataSituacao;
	}
	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}
	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}
	
	public BigDecimal getValorAgua() {
		return valorAgua;
	}
	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}
	public BigDecimal getValorEsgoto() {
		return valorEsgoto;
	}
	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}
	public BigDecimal getValorDebitos() {
		return valorDebitos;
	}
	public void setValorDebitos(BigDecimal valorDebitos) {
		this.valorDebitos = valorDebitos;
	}
	public BigDecimal getValorCreditos() {
		return valorCreditos;
	}
	public void setValorCreditos(BigDecimal valorCreditos) {
		this.valorCreditos = valorCreditos;
	}
	public BigDecimal getValorImposto() {
		return valorImposto;
	}
	public void setValorImposto(BigDecimal valorImposto) {
		this.valorImposto = valorImposto;
	}
	
	public BigDecimal getValorTotal() {

		BigDecimal valorTotalConta = new BigDecimal("0.00");

		// Valor de água
		if (this.getValorAgua() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorAgua());
		}

		// Valor de esgoto
		if (this.getValorEsgoto() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorEsgoto());
		}

		// Valor dos débitos
		if (this.getValorDebitos() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorDebitos());
		}

		// Valor dos créditos
		if (this.getValorCreditos() != null) {
			valorTotalConta = valorTotalConta.subtract(this.getValorCreditos());
		}
		
		//Valor dos Impostos
		if (this.getValorImposto() != null) {
			valorTotalConta = valorTotalConta.subtract(this.getValorImposto());
		}

		
		valorTotalConta = valorTotalConta.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return valorTotalConta;	
	}
	public String getDescricaoSituacao() {
		return descricaoSituacao;
	}
	public void setDescricaoSituacao(String descricaoSituacao) {
		this.descricaoSituacao = descricaoSituacao;
	}
	public BigDecimal getValorTotalConta() {
		return valorTotalConta;
	}
	public void setValorTotalConta(BigDecimal valorTotalConta) {
		this.valorTotalConta = valorTotalConta;
	}
	
	public boolean equalsAnoMesConta(Object obj) {
		
		boolean retorno = false;
		
		if (obj instanceof DeclaracaoQuitacaoAnualDebitosItemHelper) {
			DeclaracaoQuitacaoAnualDebitosItemHelper parametro = (DeclaracaoQuitacaoAnualDebitosItemHelper) obj;
			
			retorno = parametro.getAnoMesReferencia().compareTo(this.anoMesReferencia)==0;
		}
		
		return retorno;
	}
	@Override
	public boolean equals(Object obj) {
		boolean retorno = false;
		
		if (obj instanceof DeclaracaoQuitacaoAnualDebitosItemHelper) {
			DeclaracaoQuitacaoAnualDebitosItemHelper parametro = (DeclaracaoQuitacaoAnualDebitosItemHelper) obj;
			
			retorno = parametro.getIdConta().compareTo(this.idConta)==0;
		}
		
		return retorno;
	}

}
