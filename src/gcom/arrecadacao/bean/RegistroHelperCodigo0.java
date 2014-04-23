package gcom.arrecadacao.bean;

import java.io.Serializable;

/**
 * [UC0721] - Distribuir dados do Registro de Movimento do Arrecadador da Ficha de Compensação
 * Autor: Vivianne Sousa
 * Data: 23/11/2007
 */
public class RegistroHelperCodigo0 implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public RegistroHelperCodigo0() {
	}

    private String codigoBancoCompensacao;
    
    private String loteServico;
    
	private String codigoRegistro;
    
    private String usoExclusivo04;
    
    private String tipoInscricaoEmpresa;
    
    private String numeroInscricaoEmpresa;
    
    private String codigoConvenioBanco;
    
    private String agenciaMantedoraConta;
    
    private String digitoVerificadorAgencia;
    
    private String numeroContaCorrente;
    
    private String digitoVerificadorConta;
    
    private String digitoVerificadorAgConta;
    
    private String nomeEmpresa;
    
    private String nomeBanco;
    
    private String usoExclusivo15;
    
    private String codigoRemessaRetorno;
    
    private String dataGeracaoArquivo;
    
    private String horaGeracaoArquivo;
    
    private String numeroSequencialArquivo;
    
    private String numeroVersaoLayoutArquivo;

	private String densidadeGravacaoArquivo;

    private String reservadoBanco;
    
    private String reservadoEmpresa;
    
    private String usoExclusivo24;

	private String cobrancaSemPapel;

	private String usoExclusivoVans;
    
    private String tipoServico;
    
    private String codigoOcorrencias;

    public String getAgenciaMantedoraConta() {
        return agenciaMantedoraConta;
    }

    public void setAgenciaMantedoraConta(String agenciaMantedoraConta) {
        this.agenciaMantedoraConta = agenciaMantedoraConta;
    }

    public String getCobrancaSemPapel() {
        return cobrancaSemPapel;
    }

    public void setCobrancaSemPapel(String cobrancaSemPapel) {
        this.cobrancaSemPapel = cobrancaSemPapel;
    }

    public String getCodigoBancoCompensacao() {
        return codigoBancoCompensacao;
    }

    public void setCodigoBancoCompensacao(String codigoBancoCompensacao) {
        this.codigoBancoCompensacao = codigoBancoCompensacao;
    }

    public String getCodigoConvenioBanco() {
        return codigoConvenioBanco;
    }

    public void setCodigoConvenioBanco(String codigoConvenioBanco) {
        this.codigoConvenioBanco = codigoConvenioBanco;
    }

    public String getCodigoOcorrencias() {
        return codigoOcorrencias;
    }

    public void setCodigoOcorrencias(String codigoOcorrencias) {
        this.codigoOcorrencias = codigoOcorrencias;
    }

    public String getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    public String getCodigoRemessaRetorno() {
        return codigoRemessaRetorno;
    }

    public void setCodigoRemessaRetorno(String codigoRemessaRetorno) {
        this.codigoRemessaRetorno = codigoRemessaRetorno;
    }

    public String getDataGeracaoArquivo() {
        return dataGeracaoArquivo;
    }

    public void setDataGeracaoArquivo(String dataGeracaoArquivo) {
        this.dataGeracaoArquivo = dataGeracaoArquivo;
    }

    public String getDensidadeGravacaoArquivo() {
        return densidadeGravacaoArquivo;
    }

    public void setDensidadeGravacaoArquivo(String densidadeGravacaoArquivo) {
        this.densidadeGravacaoArquivo = densidadeGravacaoArquivo;
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

    public String getHoraGeracaoArquivo() {
        return horaGeracaoArquivo;
    }

    public void setHoraGeracaoArquivo(String horaGeracaoArquivo) {
        this.horaGeracaoArquivo = horaGeracaoArquivo;
    }

    public String getLoteServico() {
        return loteServico;
    }

    public void setLoteServico(String loteServico) {
        this.loteServico = loteServico;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getNumeroContaCorrente() {
        return numeroContaCorrente;
    }

    public void setNumeroContaCorrente(String numeroContaCorrente) {
        this.numeroContaCorrente = numeroContaCorrente;
    }

    public String getNumeroInscricaoEmpresa() {
        return numeroInscricaoEmpresa;
    }

    public void setNumeroInscricaoEmpresa(String numeroInscricaoEmpresa) {
        this.numeroInscricaoEmpresa = numeroInscricaoEmpresa;
    }

    public String getNumeroSequencialArquivo() {
        return numeroSequencialArquivo;
    }

    public void setNumeroSequencialArquivo(String numeroSequencialArquivo) {
        this.numeroSequencialArquivo = numeroSequencialArquivo;
    }

    public String getNumeroVersaoLayoutArquivo() {
        return numeroVersaoLayoutArquivo;
    }

    public void setNumeroVersaoLayoutArquivo(String numeroVersaoLayoutArquivo) {
        this.numeroVersaoLayoutArquivo = numeroVersaoLayoutArquivo;
    }

    public String getReservadoBanco() {
        return reservadoBanco;
    }

    public void setReservadoBanco(String reservadoBanco) {
        this.reservadoBanco = reservadoBanco;
    }

    public String getReservadoEmpresa() {
        return reservadoEmpresa;
    }

    public void setReservadoEmpresa(String reservadoEmpresa) {
        this.reservadoEmpresa = reservadoEmpresa;
    }

    public String getTipoInscricaoEmpresa() {
        return tipoInscricaoEmpresa;
    }

    public void setTipoInscricaoEmpresa(String tipoInscricaoEmpresa) {
        this.tipoInscricaoEmpresa = tipoInscricaoEmpresa;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
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

    public String getUsoExclusivo24() {
        return usoExclusivo24;
    }

    public void setUsoExclusivo24(String usoExclusivo24) {
        this.usoExclusivo24 = usoExclusivo24;
    }

    public String getUsoExclusivoVans() {
        return usoExclusivoVans;
    }

    public void setUsoExclusivoVans(String usoExclusivoVans) {
        this.usoExclusivoVans = usoExclusivoVans;
    }
    
    

	

	
}
