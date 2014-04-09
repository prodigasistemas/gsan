package gcom.arrecadacao.bean;

import java.io.Serializable;

/**
 * [UC0721] - Distribuir dados do Registro de Movimento do Arrecadador da Ficha de Compensação
 * Autor: Vivianne Sousa
 * Data: 23/11/2007
 */
public class RegistroHelperCodigo5 implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public RegistroHelperCodigo5() {
	}

	private String codigoBancoCompensacao;
    
    private String loteServico;
    
    private String codigoRegistro;
    
    private String usoExclusivo04;
    
    private String qtdeRegistrosLote;
    
    private String qtdeTITCobranca06;
    
    private String valorTITCarteira07;
    
    private String qtdeTITCobranca08;
    
    private String valorTITCarteira09;
    
    private String qtdeTITCobranca10;
    
    private String valorTITCarteira11;
    
    private String qtdeTITCobranca12;
    
    private String valorTITCarteira13;
    
    private String numeroAvisoLancamento;
    
    private String usoExclusivo15;

    public String getCodigoBancoCompensacao() {
        return codigoBancoCompensacao;
    }

    public void setCodigoBancoCompensacao(String codigoBancoCompensacao) {
        this.codigoBancoCompensacao = codigoBancoCompensacao;
    }

    public String getLoteServico() {
        return loteServico;
    }

    public void setLoteServico(String loteServico) {
        this.loteServico = loteServico;
    }

    public String getNumeroAvisoLancamento() {
        return numeroAvisoLancamento;
    }

    public void setNumeroAvisoLancamento(String numeroAvisoLancamento) {
        this.numeroAvisoLancamento = numeroAvisoLancamento;
    }

    public String getQtdeRegistrosLote() {
        return qtdeRegistrosLote;
    }

    public void setQtdeRegistrosLote(String qtdeRegistrosLote) {
        this.qtdeRegistrosLote = qtdeRegistrosLote;
    }

    public String getQtdeTITCobranca06() {
        return qtdeTITCobranca06;
    }

    public void setQtdeTITCobranca06(String qtdeTITCobranca06) {
        this.qtdeTITCobranca06 = qtdeTITCobranca06;
    }

    public String getQtdeTITCobranca08() {
        return qtdeTITCobranca08;
    }

    public void setQtdeTITCobranca08(String qtdeTITCobranca08) {
        this.qtdeTITCobranca08 = qtdeTITCobranca08;
    }

    public String getQtdeTITCobranca10() {
        return qtdeTITCobranca10;
    }

    public void setQtdeTITCobranca10(String qtdeTITCobranca10) {
        this.qtdeTITCobranca10 = qtdeTITCobranca10;
    }

    public String getQtdeTITCobranca12() {
        return qtdeTITCobranca12;
    }

    public void setQtdeTITCobranca12(String qtdeTITCobranca12) {
        this.qtdeTITCobranca12 = qtdeTITCobranca12;
    }

    public String getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    public String getUsoExclusivo04() {
        return usoExclusivo04;
    }

    public void setUsoExclusivo04(String usoExclusivo04) {
        this.usoExclusivo04 = usoExclusivo04;
    }

    public String getUsoExclusivo15() {
        return usoExclusivo15;
    }

    public void setUsoExclusivo15(String usoExclusivo15) {
        this.usoExclusivo15 = usoExclusivo15;
    }

    public String getValorTITCarteira07() {
        return valorTITCarteira07;
    }

    public void setValorTITCarteira07(String valorTITCarteira07) {
        this.valorTITCarteira07 = valorTITCarteira07;
    }

    public String getValorTITCarteira09() {
        return valorTITCarteira09;
    }

    public void setValorTITCarteira09(String valorTITCarteira09) {
        this.valorTITCarteira09 = valorTITCarteira09;
    }

    public String getValorTITCarteira11() {
        return valorTITCarteira11;
    }

    public void setValorTITCarteira11(String valorTITCarteira11) {
        this.valorTITCarteira11 = valorTITCarteira11;
    }

    public String getValorTITCarteira13() {
        return valorTITCarteira13;
    }

    public void setValorTITCarteira13(String valorTITCarteira13) {
        this.valorTITCarteira13 = valorTITCarteira13;
    }

    
}
