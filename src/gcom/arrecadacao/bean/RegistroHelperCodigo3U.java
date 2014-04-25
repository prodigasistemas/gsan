package gcom.arrecadacao.bean;

import java.io.Serializable;

/**
 * [UC0721] - Distribuir dados do Registro de Movimento do Arrecadador da Ficha de Compensação
 * Autor: Vivianne Sousa
 * Data: 23/11/2007
 */
public class RegistroHelperCodigo3U implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public RegistroHelperCodigo3U() {
	}

	private String codigoBancoCompensacao;
    
    private String loteServico;
    
    private String codigoRegistro;
    
    private String numeroSequencialRegLote;
    
    private String codSegmentoRegDetalhe;
    
    private String usoExclusivo06;
    
    private String codigoMovimento;
    
    private String jurosMultaEncargos;
    
    private String valorDescontoConcedido;
    
    private String valorAbatConcedidoCancelado;
    
    private String valorIOFRecolhido;
    
    private String valorPagoSacado;
    
    private String valorLiquidoASerCreditado;
    
    private String valorOutrasDespesas;
    
    private String valorOutrosCreditos;
    
    private String dataOcorrencia;
    
    private String dataEfetivacaoCredito;
    
    private String codigoOcorrenciaSacado;
    
    private String dataOcorrenciaSacado;
    
    private String valorOcorrenciaSacado;
    
    private String complOcorrenciaSacado;
    
    private String codigoBanco;
    
    private String nossoNumero;
    
    private String usoExclusivo24;

    public String getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(String codigoBanco) {
        this.codigoBanco = codigoBanco;
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

    public String getCodigoOcorrenciaSacado() {
        return codigoOcorrenciaSacado;
    }

    public void setCodigoOcorrenciaSacado(String codigoOcorrenciaSacado) {
        this.codigoOcorrenciaSacado = codigoOcorrenciaSacado;
    }

    public String getCodSegmentoRegDetalhe() {
        return codSegmentoRegDetalhe;
    }

    public void setCodSegmentoRegDetalhe(String codSegmentoRegDetalhe) {
        this.codSegmentoRegDetalhe = codSegmentoRegDetalhe;
    }

    public String getComplOcorrenciaSacado() {
        return complOcorrenciaSacado;
    }

    public void setComplOcorrenciaSacado(String complOcorrenciaSacado) {
        this.complOcorrenciaSacado = complOcorrenciaSacado;
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

    public String getDataOcorrenciaSacado() {
        return dataOcorrenciaSacado;
    }

    public void setDataOcorrenciaSacado(String dataOcorrenciaSacado) {
        this.dataOcorrenciaSacado = dataOcorrenciaSacado;
    }

    public String getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    public String getJurosMultaEncargos() {
        return jurosMultaEncargos;
    }

    public void setJurosMultaEncargos(String jurosMultaEncargos) {
        this.jurosMultaEncargos = jurosMultaEncargos;
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

    public String getNumeroSequencialRegLote() {
        return numeroSequencialRegLote;
    }

    public void setNumeroSequencialRegLote(String numeroSequencialRegLote) {
        this.numeroSequencialRegLote = numeroSequencialRegLote;
    }

    public String getUsoExclusivo06() {
        return usoExclusivo06;
    }

    public void setUsoExclusivo06(String usoExclusivo06) {
        this.usoExclusivo06 = usoExclusivo06;
    }

    public String getUsoExclusivo24() {
        return usoExclusivo24;
    }

    public void setUsoExclusivo24(String usoExclusivo24) {
        this.usoExclusivo24 = usoExclusivo24;
    }

    public String getValorAbatConcedidoCancelado() {
        return valorAbatConcedidoCancelado;
    }

    public void setValorAbatConcedidoCancelado(String valorAbatConcedidoCancelado) {
        this.valorAbatConcedidoCancelado = valorAbatConcedidoCancelado;
    }

    public String getValorDescontoConcedido() {
        return valorDescontoConcedido;
    }

    public void setValorDescontoConcedido(String valorDescontoConcedido) {
        this.valorDescontoConcedido = valorDescontoConcedido;
    }

    public String getValorIOFRecolhido() {
        return valorIOFRecolhido;
    }

    public void setValorIOFRecolhido(String valorIOFRecolhido) {
        this.valorIOFRecolhido = valorIOFRecolhido;
    }

    public String getValorLiquidoASerCreditado() {
        return valorLiquidoASerCreditado;
    }

    public void setValorLiquidoASerCreditado(String valorLiquidoASerCreditado) {
        this.valorLiquidoASerCreditado = valorLiquidoASerCreditado;
    }

    public String getValorOcorrenciaSacado() {
        return valorOcorrenciaSacado;
    }

    public void setValorOcorrenciaSacado(String valorOcorrenciaSacado) {
        this.valorOcorrenciaSacado = valorOcorrenciaSacado;
    }

    public String getValorOutrasDespesas() {
        return valorOutrasDespesas;
    }

    public void setValorOutrasDespesas(String valorOutrasDespesas) {
        this.valorOutrasDespesas = valorOutrasDespesas;
    }

    public String getValorOutrosCreditos() {
        return valorOutrosCreditos;
    }

    public void setValorOutrosCreditos(String valorOutrosCreditos) {
        this.valorOutrosCreditos = valorOutrosCreditos;
    }

    public String getValorPagoSacado() {
        return valorPagoSacado;
    }

    public void setValorPagoSacado(String valorPagoSacado) {
        this.valorPagoSacado = valorPagoSacado;
    }
    
    

     
    
}
