package gcom.arrecadacao.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class RegistroHelperFichaCompensacao implements Serializable {

	private static final long serialVersionUID = 1L;

	public RegistroHelperFichaCompensacao() {
	}
	
	private String codigoBanco;
	
	private String codigoMoeda;
	
	private String naoUtilizadoCampo1;
	
	private String digitoVerificadorModulo10Campo1;
	
	private String naoUtilizadoCampo2;
	
	private String convenio;
	
	private String idDocumentoTipo;
	
	private String digitoVerificadorModulo10Campo2;
	
	private String idCobrancaDocumento;
	
	private String carteira;
	
	private String digitoVerificadorModulo10Campo3;
	
	private String digitoVerificadorModulo11;
	
	private String fatorVencimento;
	
	private BigDecimal valorDocumento;

	public String getCarteira() {
		return carteira;
	}

	public void setCarteira(String carteira) {
		this.carteira = carteira;
	}

	public String getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public String getCodigoMoeda() {
		return codigoMoeda;
	}

	public void setCodigoMoeda(String codigoMoeda) {
		this.codigoMoeda = codigoMoeda;
	}

	public String getConvenio() {
		return convenio;
	}

	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}

	public String getDigitoVerificadorModulo10Campo1() {
		return digitoVerificadorModulo10Campo1;
	}

	public void setDigitoVerificadorModulo10Campo1(
			String digitoVerificadorModulo10Campo1) {
		this.digitoVerificadorModulo10Campo1 = digitoVerificadorModulo10Campo1;
	}

	public String getDigitoVerificadorModulo10Campo2() {
		return digitoVerificadorModulo10Campo2;
	}

	public void setDigitoVerificadorModulo10Campo2(
			String digitoVerificadorModulo10Campo2) {
		this.digitoVerificadorModulo10Campo2 = digitoVerificadorModulo10Campo2;
	}

	public String getDigitoVerificadorModulo10Campo3() {
		return digitoVerificadorModulo10Campo3;
	}

	public void setDigitoVerificadorModulo10Campo3(
			String digitoVerificadorModulo10Campo3) {
		this.digitoVerificadorModulo10Campo3 = digitoVerificadorModulo10Campo3;
	}

	public String getDigitoVerificadorModulo11() {
		return digitoVerificadorModulo11;
	}

	public void setDigitoVerificadorModulo11(String digitoVerificadorModulo11) {
		this.digitoVerificadorModulo11 = digitoVerificadorModulo11;
	}

	public String getFatorVencimento() {
		return fatorVencimento;
	}

	public void setFatorVencimento(String fatorVencimento) {
		this.fatorVencimento = fatorVencimento;
	}

	public String getIdCobrancaDocumento() {
		return idCobrancaDocumento;
	}

	public void setIdCobrancaDocumento(String idCobrancaDocumento) {
		this.idCobrancaDocumento = idCobrancaDocumento;
	}

	public String getIdDocumentoTipo() {
		return idDocumentoTipo;
	}

	public void setIdDocumentoTipo(String idDocumentoTipo) {
		this.idDocumentoTipo = idDocumentoTipo;
	}

	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}

	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}

	public String getNaoUtilizadoCampo1() {
		return naoUtilizadoCampo1;
	}

	public void setNaoUtilizadoCampo1(String naoUtilizadoCampo1) {
		this.naoUtilizadoCampo1 = naoUtilizadoCampo1;
	}

	public String getNaoUtilizadoCampo2() {
		return naoUtilizadoCampo2;
	}

	public void setNaoUtilizadoCampo2(String naoUtilizadoCampo2) {
		this.naoUtilizadoCampo2 = naoUtilizadoCampo2;
	}
	
	public String getCampo1SemDigito(){
		return this.getCodigoBanco() + this.getCodigoMoeda() + this.getNaoUtilizadoCampo1();
	}
	
	public String getCampo2SemDigito(){
		return this.getNaoUtilizadoCampo2() + this.getConvenio() + this.getIdDocumentoTipo();
	}
	
	public String getCampo3SemDigito(){
		return this.getIdCobrancaDocumento() + this.getCarteira();
	}
}
