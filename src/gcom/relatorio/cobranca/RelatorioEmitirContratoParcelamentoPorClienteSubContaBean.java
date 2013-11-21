package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

/**
 * [UC1141] Emitir Contrato de Parcelamento por Cliente
 * 
 * Classe responsável por emitir o relatório com os dados de um Contrato de Parcelamento por Cliente
 * 
 * @author Mariana Victor
 *
 * @date 28/04/2011 
 */
public class RelatorioEmitirContratoParcelamentoPorClienteSubContaBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;

	private String idConta;
	
	private BigDecimal acrescImpont;
	
	private String anoMesConta;
	
	private String desvinculadaContrato;
	
	private String matricula;
	
	private String situacaoConta;
	
	private String vencimentoConta;
	
	private BigDecimal valorAgua;
	
	private BigDecimal valorEsgoto;
	
	private BigDecimal valorDebitos;
	
	private BigDecimal valorCreditos;
	
	private BigDecimal valorImpostos;
	
	private BigDecimal valorConta;
	
	public BigDecimal getAcrescImpont() {
		return acrescImpont;
	}

	public void setAcrescImpont(BigDecimal acrescImpont) {
		this.acrescImpont = acrescImpont;
	}

	public String getAnoMesConta() {
		return anoMesConta;
	}

	public void setAnoMesConta(String anoMesConta) {
		this.anoMesConta = anoMesConta;
	}

	public String getDesvinculadaContrato() {
		return desvinculadaContrato;
	}

	public void setDesvinculadaContrato(String desvinculadaContrato) {
		this.desvinculadaContrato = desvinculadaContrato;
	}

	public String getIdConta() {
		return idConta;
	}

	public void setIdConta(String idConta) {
		this.idConta = idConta;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSituacaoConta() {
		return situacaoConta;
	}

	public void setSituacaoConta(String situacaoConta) {
		this.situacaoConta = situacaoConta;
	}

	public BigDecimal getValorAgua() {
		return valorAgua;
	}

	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}

	public BigDecimal getValorConta() {
		return valorConta;
	}

	public void setValorConta(BigDecimal valorConta) {
		this.valorConta = valorConta;
	}

	public BigDecimal getValorCreditos() {
		return valorCreditos;
	}

	public void setValorCreditos(BigDecimal valorCreditos) {
		this.valorCreditos = valorCreditos;
	}

	public BigDecimal getValorDebitos() {
		return valorDebitos;
	}

	public void setValorDebitos(BigDecimal valorDebitos) {
		this.valorDebitos = valorDebitos;
	}

	public BigDecimal getValorEsgoto() {
		return valorEsgoto;
	}

	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}

	public BigDecimal getValorImpostos() {
		return valorImpostos;
	}

	public void setValorImpostos(BigDecimal valorImpostos) {
		this.valorImpostos = valorImpostos;
	}

	public String getVencimentoConta() {
		return vencimentoConta;
	}

	public void setVencimentoConta(String vencimentoConta) {
		this.vencimentoConta = vencimentoConta;
	}
	
}
