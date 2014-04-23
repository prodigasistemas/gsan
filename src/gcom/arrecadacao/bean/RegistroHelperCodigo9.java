package gcom.arrecadacao.bean;

import java.io.Serializable;

/**
 * [UC0721] - Distribuir dados do Registro de Movimento do Arrecadador da Ficha de Compensação
 * Autor: Vivianne Sousa
 * Data: 23/11/2007
 */
public class RegistroHelperCodigo9 implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public RegistroHelperCodigo9() {
	}

	private String codigoBancoCompensacao;
    
    private String loteServico;
    
    private String codigoRegistro;
    
    private String usoExclusivo04;
    
    private String qtdeLoteArquivo;
    
    private String qtdeRegistrosArquivo;
    
    private String qtdeContas;
    
    private String usoExclusivo08;

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

    public String getQtdeContas() {
        return qtdeContas;
    }

    public void setQtdeContas(String qtdeContas) {
        this.qtdeContas = qtdeContas;
    }

    public String getQtdeLoteArquivo() {
        return qtdeLoteArquivo;
    }

    public void setQtdeLoteArquivo(String qtdeLoteArquivo) {
        this.qtdeLoteArquivo = qtdeLoteArquivo;
    }

    public String getQtdeRegistrosArquivo() {
        return qtdeRegistrosArquivo;
    }

    public void setQtdeRegistrosArquivo(String qtdeRegistrosArquivo) {
        this.qtdeRegistrosArquivo = qtdeRegistrosArquivo;
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

    public String getUsoExclusivo08() {
        return usoExclusivo08;
    }

    public void setUsoExclusivo08(String usoExclusivo08) {
        this.usoExclusivo08 = usoExclusivo08;
    }

  
    
}
