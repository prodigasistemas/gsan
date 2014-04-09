package gcom.arrecadacao.bean;

import java.io.Serializable;

/**
 * [UC0721] - Distribuir dados do Registro de Movimento do Arrecadador da Ficha de Compensação
 * Autor: Vivianne Sousa
 * Data: 28/01/2008
 */
public class RegistroHelperCodigoW implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public RegistroHelperCodigoW() {
	}

	private String codigoBancoCompensacao;
    
    private String loteServico;
    
    private String codigoRegistro;
    
    private String numeroSequencialRegLote;
    
    private String codigoMovimento;
    
    private String agenciaMantedoraConta;
    
    private String digitoVerificadorAgencia;
    
    private String numeroContaCorrente;
    
    private String digitoVerificadorConta;
    
    private String digitoVerificadorAgConta;
    
    private String nossoNumero;
    
    private String numeroDocCobranca;
    
    private String dataVencimentoTitulo;
    
    private String valorNominalTitulo;
    
    private String valorPagoSacado;
    
    private String dataOcorrencia;
    
    private String dataEfetivacaoCredito;

    public String getAgenciaMantedoraConta() {
        return agenciaMantedoraConta;
    }

    public void setAgenciaMantedoraConta(String agenciaMantedoraConta) {
        this.agenciaMantedoraConta = agenciaMantedoraConta;
    }

    public String getCodigoBancoCompensacao() {
        return codigoBancoCompensacao;
    }

    public void setCodigoBancoCompensacao(String codigoBancoCompensacao) {
        this.codigoBancoCompensacao = codigoBancoCompensacao;
    }

    public String getCodigoMovimento() {
        return codigoMovimento;
    }

    public void setCodigoMovimento(String codigoMovimento) {
        this.codigoMovimento = codigoMovimento;
    }

    public String getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    public String getDataEfetivacaoCredito() {
        return dataEfetivacaoCredito;
    }

    public void setDataEfetivacaoCredito(String dataEfetivacaoCredito) {
        this.dataEfetivacaoCredito = dataEfetivacaoCredito;
    }

    public String getDataOcorrencia() {
        return dataOcorrencia;
    }

    public void setDataOcorrencia(String dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }

    public String getDataVencimentoTitulo() {
        return dataVencimentoTitulo;
    }

    public void setDataVencimentoTitulo(String dataVencimentoTitulo) {
        this.dataVencimentoTitulo = dataVencimentoTitulo;
    }

    public String getDigitoVerificadorAgConta() {
        return digitoVerificadorAgConta;
    }

    public void setDigitoVerificadorAgConta(String digitoVerificadorAgConta) {
        this.digitoVerificadorAgConta = digitoVerificadorAgConta;
    }

    public String getDigitoVerificadorAgencia() {
        return digitoVerificadorAgencia;
    }

    public void setDigitoVerificadorAgencia(String digitoVerificadorAgencia) {
        this.digitoVerificadorAgencia = digitoVerificadorAgencia;
    }

    public String getDigitoVerificadorConta() {
        return digitoVerificadorConta;
    }

    public void setDigitoVerificadorConta(String digitoVerificadorConta) {
        this.digitoVerificadorConta = digitoVerificadorConta;
    }

    public String getLoteServico() {
        return loteServico;
    }

    public void setLoteServico(String loteServico) {
        this.loteServico = loteServico;
    }

    public String getNossoNumero() {
        return nossoNumero;
    }

    public void setNossoNumero(String nossoNumero) {
        this.nossoNumero = nossoNumero;
    }

    public String getNumeroContaCorrente() {
        return numeroContaCorrente;
    }

    public void setNumeroContaCorrente(String numeroContaCorrente) {
        this.numeroContaCorrente = numeroContaCorrente;
    }

    public String getNumeroDocCobranca() {
        return numeroDocCobranca;
    }

    public void setNumeroDocCobranca(String numeroDocCobranca) {
        this.numeroDocCobranca = numeroDocCobranca;
    }

    public String getNumeroSequencialRegLote() {
        return numeroSequencialRegLote;
    }

    public void setNumeroSequencialRegLote(String numeroSequencialRegLote) {
        this.numeroSequencialRegLote = numeroSequencialRegLote;
    }

    public String getValorNominalTitulo() {
        return valorNominalTitulo;
    }

    public void setValorNominalTitulo(String valorNominalTitulo) {
        this.valorNominalTitulo = valorNominalTitulo;
    }

    public String getValorPagoSacado() {
        return valorPagoSacado;
    }

    public void setValorPagoSacado(String valorPagoSacado) {
        this.valorPagoSacado = valorPagoSacado;
    }
    
    public String getIdentificacaoAgenciaContaDigitoCreditada(){
        String identificacaoAgenciaContaDigitoCreditada = "";
        
        identificacaoAgenciaContaDigitoCreditada = this.agenciaMantedoraConta ; 
        identificacaoAgenciaContaDigitoCreditada = 
            identificacaoAgenciaContaDigitoCreditada + "-" +this.digitoVerificadorAgencia; 
        identificacaoAgenciaContaDigitoCreditada = 
            identificacaoAgenciaContaDigitoCreditada + "/" + this.numeroContaCorrente; 
        identificacaoAgenciaContaDigitoCreditada = 
            identificacaoAgenciaContaDigitoCreditada + "-" + this.digitoVerificadorConta; 
        identificacaoAgenciaContaDigitoCreditada = 
            identificacaoAgenciaContaDigitoCreditada + "/" + this.digitoVerificadorAgConta; 

        return identificacaoAgenciaContaDigitoCreditada;
    }

	

	
}
