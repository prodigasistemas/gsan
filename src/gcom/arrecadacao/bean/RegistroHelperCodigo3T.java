package gcom.arrecadacao.bean;

import java.io.Serializable;

/**
 * [UC0721] - Distribuir dados do Registro de Movimento do Arrecadador da Ficha de Compensação
 * Autor: Vivianne Sousa
 * Data: 23/11/2007
 */
public class RegistroHelperCodigo3T implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public RegistroHelperCodigo3T() {
	}

	private String codigoBancoCompensacao;
    
    private String loteServico;
    
    private String codigoRegistro;
    
    private String numeroSequencialRegLote;
    
    private String codSegmentoRegDetalhe;
    
    private String usoExclusivo06;
    
    private String codigoMovimento;
    
    private String agenciaMantedoraConta;
    
    private String digitoVerificadorAgencia;
    
    private String numeroContaCorrente;
    
    private String digitoVerificadorConta;
    
    private String digitoVerificadorAgConta;
    
    private String nossoNumero;
    
    private String codigoCarteira;
    
    private String numeroDocCobranca;
    
    private String dataVencimentoTitulo;
    
    private String valorNominalTitulo;
    
    private String numeroBanco;
    
    private String agenciaCobradoraRecebedora;
    
    private String digitoVerificadorAgenciaT;
    
    private String identificadorTituloEmpresa;
    
    private String codigoMoeda;
    
    private String tipoInscricao;
    
    private String numeroInscricao;
    
    private String nome;
    
    private String contratoOperacaoCred;
    
    private String valorTarifaCustas;
    
    private String identificacao;
    
    private String usoExclusivo29;

    public String getAgenciaCobradoraRecebedora() {
        return agenciaCobradoraRecebedora;
    }

    public void setAgenciaCobradoraRecebedora(String agenciaCobradoraRecebedora) {
        this.agenciaCobradoraRecebedora = agenciaCobradoraRecebedora;
    }

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

    public String getCodigoCarteira() {
        return codigoCarteira;
    }

    public void setCodigoCarteira(String codigoCarteira) {
        this.codigoCarteira = codigoCarteira;
    }

    public String getCodigoMoeda() {
        return codigoMoeda;
    }

    public void setCodigoMoeda(String codigoMoeda) {
        this.codigoMoeda = codigoMoeda;
    }

    public String getCodigoMovimento() {
        return codigoMovimento;
    }

    public void setCodigoMovimento(String codigoMovimento) {
        this.codigoMovimento = codigoMovimento;
    }

    public String getCodSegmentoRegDetalhe() {
        return codSegmentoRegDetalhe;
    }

    public void setCodSegmentoRegDetalhe(String codSegmentoRegDetalhe) {
        this.codSegmentoRegDetalhe = codSegmentoRegDetalhe;
    }

    public String getContratoOperacaoCred() {
        return contratoOperacaoCred;
    }

    public void setContratoOperacaoCred(String contratoOperacaoCred) {
        this.contratoOperacaoCred = contratoOperacaoCred;
    }

    public String getDataVencimentoTitulo() {
        return dataVencimentoTitulo;
    }

    public void setDataVencimentoTitulo(String dataVencimentoTitulo) {
        this.dataVencimentoTitulo = dataVencimentoTitulo;
    }

    public String getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
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

    public String getDigitoVerificadorAgenciaT() {
        return digitoVerificadorAgenciaT;
    }

    public void setDigitoVerificadorAgenciaT(String digitoVerificadorAgenciaT) {
        this.digitoVerificadorAgenciaT = digitoVerificadorAgenciaT;
    }

    public String getDigitoVerificadorConta() {
        return digitoVerificadorConta;
    }

    public void setDigitoVerificadorConta(String digitoVerificadorConta) {
        this.digitoVerificadorConta = digitoVerificadorConta;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getIdentificadorTituloEmpresa() {
        return identificadorTituloEmpresa;
    }

    public void setIdentificadorTituloEmpresa(String identificadorTituloEmpresa) {
        this.identificadorTituloEmpresa = identificadorTituloEmpresa;
    }

    public String getLoteServico() {
        return loteServico;
    }

    public void setLoteServico(String loteServico) {
        this.loteServico = loteServico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNossoNumero() {
        return nossoNumero;
    }

    public void setNossoNumero(String nossoNumero) {
        this.nossoNumero = nossoNumero;
    }

    public String getNumeroBanco() {
        return numeroBanco;
    }

    public void setNumeroBanco(String numeroBanco) {
        this.numeroBanco = numeroBanco;
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

    public String getNumeroInscricao() {
        return numeroInscricao;
    }

    public void setNumeroInscricao(String numeroInscricao) {
        this.numeroInscricao = numeroInscricao;
    }

    public String getNumeroSequencialRegLote() {
        return numeroSequencialRegLote;
    }

    public void setNumeroSequencialRegLote(String numeroSequencialRegLote) {
        this.numeroSequencialRegLote = numeroSequencialRegLote;
    }

    public String getTipoInscricao() {
        return tipoInscricao;
    }

    public void setTipoInscricao(String tipoInscricao) {
        this.tipoInscricao = tipoInscricao;
    }
    
    public String getUsoExclusivo06() {
        return usoExclusivo06;
    }

    public void setUsoExclusivo06(String usoExclusivo06) {
        this.usoExclusivo06 = usoExclusivo06;
    }

    public String getUsoExclusivo29() {
        return usoExclusivo29;
    }

    public void setUsoExclusivo29(String usoExclusivo29) {
        this.usoExclusivo29 = usoExclusivo29;
    }

    public String getValorNominalTitulo() {
        return valorNominalTitulo;
    }

    public void setValorNominalTitulo(String valorNominalTitulo) {
        this.valorNominalTitulo = valorNominalTitulo;
    }

    public String getValorTarifaCustas() {
        return valorTarifaCustas;
    }

    public void setValorTarifaCustas(String valorTarifaCustas) {
        this.valorTarifaCustas = valorTarifaCustas;
    }
    
}
