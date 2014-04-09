package gcom.arrecadacao.bean;

/**
 * Classe que irá auxiliar no formato do retorno da pesquisa dos itens de um
 * determinado movimento do arrecadador
 *
 * @author Raphael Rossiter
 * @date 22/03/2006
 */
public class DadosConteudoCodigoBarrasHelper {
	
	private String identificacaoProduto;
	private String identificacaoSegmento;
	private String identificacaoValorRealOUReferencia;
	private String digitoVerificadorGeral;
	private String valorPagamento;
	private String identificacaoEmpresa;
	private String tipoPagamento;
	private String codigoLocalidade;
	private String matriculaImovel;
	private String mesAnoReferenciaConta;
	private String digitoVerificadorContaModulo10;
	private String codigoTipoDebito;
	private String anoEmissaoGuiaPagamento;
	private String sequencialDocumentoCobranca;
	private String codigoTipoDocumento;
	private String codigoCliente;
	private String sequencialFaturaClienteResponsavel;
	
	private String codigoOrigemConta;
	private String numeroDocumento;
	private String qualificacao;
	private String mesAno;
    private String identificacaoTituloNoBanco;
    
    private String identificacaoDocumento;
    
    //codigo de barras da Ficha de Compensação
    private String codigoBanco;
    private String codigoMoeda;
    private String fatorVencimento;
    private String valorCodigoBarras;
    private String nossoNumero;
    private String tipoCarteira;
    
	
	public String getMesAno() {
		return mesAno;
	}
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	public String getQualificacao() {
		return qualificacao;
	}
	public void setQualificacao(String qualificacao) {
		this.qualificacao = qualificacao;
	}
	public String getCodigoOrigemConta() {
		return codigoOrigemConta;
	}
	public void setCodigoOrigemConta(String codigoOrigemConta) {
		this.codigoOrigemConta = codigoOrigemConta;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getAnoEmissaoGuiaPagamento() {
		return anoEmissaoGuiaPagamento;
	}
	public void setAnoEmissaoGuiaPagamento(String anoEmissaoGuiaPagamento) {
		this.anoEmissaoGuiaPagamento = anoEmissaoGuiaPagamento;
	}
	public String getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getCodigoLocalidade() {
		return codigoLocalidade;
	}
	public void setCodigoLocalidade(String codigoLocalidade) {
		this.codigoLocalidade = codigoLocalidade;
	}
	public String getCodigoTipoDebito() {
		return codigoTipoDebito;
	}
	public void setCodigoTipoDebito(String codigoTipoDebito) {
		this.codigoTipoDebito = codigoTipoDebito;
	}
	public String getCodigoTipoDocumento() {
		return codigoTipoDocumento;
	}
	public void setCodigoTipoDocumento(String codigoTipoDocumento) {
		this.codigoTipoDocumento = codigoTipoDocumento;
	}
	public String getDigitoVerificadorContaModulo10() {
		return digitoVerificadorContaModulo10;
	}
	public void setDigitoVerificadorContaModulo10(
			String digitoVerificadorContaModulo10) {
		this.digitoVerificadorContaModulo10 = digitoVerificadorContaModulo10;
	}
	public String getDigitoVerificadorGeral() {
		return digitoVerificadorGeral;
	}
	public void setDigitoVerificadorGeral(String digitoVerificadorGeral) {
		this.digitoVerificadorGeral = digitoVerificadorGeral;
	}
	public String getIdentificacaoEmpresa() {
		return identificacaoEmpresa;
	}
	public void setIdentificacaoEmpresa(String identificacaoEmpresa) {
		this.identificacaoEmpresa = identificacaoEmpresa;
	}
	public String getIdentificacaoProduto() {
		return identificacaoProduto;
	}
	public void setIdentificacaoProduto(String identificacaoProduto) {
		this.identificacaoProduto = identificacaoProduto;
	}
	public String getIdentificacaoValorRealOUReferencia() {
		return identificacaoValorRealOUReferencia;
	}
	public void setIdentificacaoValorRealOUReferencia(
			String identificacaoValorRealOUReferencia) {
		this.identificacaoValorRealOUReferencia = identificacaoValorRealOUReferencia;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getMesAnoReferenciaConta() {
		return mesAnoReferenciaConta;
	}
	public void setMesAnoReferenciaConta(String mesAnoReferenciaConta) {
		this.mesAnoReferenciaConta = mesAnoReferenciaConta;
	}
	public String getSequencialDocumentoCobranca() {
		return sequencialDocumentoCobranca;
	}
	public void setSequencialDocumentoCobranca(String sequencialDocumentoCobranca) {
		this.sequencialDocumentoCobranca = sequencialDocumentoCobranca;
	}
	public String getSequencialFaturaClienteResponsavel() {
		return sequencialFaturaClienteResponsavel;
	}
	public void setSequencialFaturaClienteResponsavel(
			String sequencialFaturaClienteResponsavel) {
		this.sequencialFaturaClienteResponsavel = sequencialFaturaClienteResponsavel;
	}
	public String getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	public String getValorPagamento() {
		return valorPagamento;
	}
	public void setValorPagamento(String valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
	public String getIdentificacaoSegmento() {
		return identificacaoSegmento;
	}
	public void setIdentificacaoSegmento(String identificacaoSegmento) {
		this.identificacaoSegmento = identificacaoSegmento;
	}
    public String getIdentificacaoTituloNoBanco() {
        return identificacaoTituloNoBanco;
    }
    public void setIdentificacaoTituloNoBanco(String identificacaoTituloNoBanco) {
        this.identificacaoTituloNoBanco = identificacaoTituloNoBanco;
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
    public String getFatorVencimento() {
        return fatorVencimento;
    }
    public void setFatorVencimento(String fatorVencimento) {
        this.fatorVencimento = fatorVencimento;
    }
    public String getNossoNumero() {
        return nossoNumero;
    }
    public void setNossoNumero(String nossoNumero) {
        this.nossoNumero = nossoNumero;
    }
    public String getTipoCarteira() {
        return tipoCarteira;
    }
    public void setTipoCarteira(String tipoCarteira) {
        this.tipoCarteira = tipoCarteira;
    }
    public String getValorCodigoBarras() {
        return valorCodigoBarras;
    }
    public void setValorCodigoBarras(String valorCodigoBarras) {
        this.valorCodigoBarras = valorCodigoBarras;
    }
	public String getIdentificacaoDocumento() {
		return identificacaoDocumento;
	}
	public void setIdentificacaoDocumento(String identificacaoDocumento) {
		this.identificacaoDocumento = identificacaoDocumento;
	}
}
