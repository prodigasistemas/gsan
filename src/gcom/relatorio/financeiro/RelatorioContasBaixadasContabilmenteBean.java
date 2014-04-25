package gcom.relatorio.financeiro;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

/**
 * [UC0726] Gerar Relatório das Contas Baixadas Contabilmente
 * 
 * @author Vivianne Sousa
 * @data 08/04/2008
 */
public class RelatorioContasBaixadasContabilmenteBean implements RelatorioBean {

    private String idGerencia;
    private String descGerencia;
    private String idLocalidade;
    private String descLocalidade;
    private String faixaValor;
    private String anoMesReferenciaBaixaContabil;
    private String mensagemFaixaValor;

    private String matricula;
    private String inscricao;
    private String usuario;
    private String endereco;
    private String situacaoAgua;
    private String situacaoEsgoto;
    private String referenciaFatura;
    private BigDecimal valorConta;
    
    
    //Sintetico
    private BigDecimal valorTotalFaixaUm;
    private BigDecimal valorTotalFaixaDois;
    private BigDecimal valorTotalFaixaTres;
    private BigDecimal valorTotal;
    
    
    
    
    public RelatorioContasBaixadasContabilmenteBean(BigDecimal valorTotalFaixaUm, BigDecimal valorTotalFaixaDois, BigDecimal valorTotalFaixaTres, BigDecimal valorTotal) {
		super();
		// TODO Auto-generated constructor stub
		this.valorTotalFaixaUm = valorTotalFaixaUm;
		this.valorTotalFaixaDois = valorTotalFaixaDois;
		this.valorTotalFaixaTres = valorTotalFaixaTres;
		this.valorTotal = valorTotal;
	}

	public RelatorioContasBaixadasContabilmenteBean() {
        
    }
    
    public String getAnoMesReferenciaBaixaContabil() {
        return anoMesReferenciaBaixaContabil;
    }

    public void setAnoMesReferenciaBaixaContabil(String anoMesReferenciaBaixaContabil) {
        this.anoMesReferenciaBaixaContabil = anoMesReferenciaBaixaContabil;
    }

    public String getDescGerencia() {
        return descGerencia;
    }

    public void setDescGerencia(String descGerencia) {
        this.descGerencia = descGerencia;
    }

    public String getDescLocalidade() {
        return descLocalidade;
    }

    public void setDescLocalidade(String descLocalidade) {
        this.descLocalidade = descLocalidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getFaixaValor() {
        return faixaValor;
    }

    public void setFaixaValor(String faixaValor) {
        this.faixaValor = faixaValor;
    }

    public String getIdGerencia() {
        return idGerencia;
    }

    public void setIdGerencia(String idGerencia) {
        this.idGerencia = idGerencia;
    }

    public String getIdLocalidade() {
        return idLocalidade;
    }

    public void setIdLocalidade(String idLocalidade) {
        this.idLocalidade = idLocalidade;
    }

    public String getInscricao() {
        return inscricao;
    }

    public void setInscricao(String inscricao) {
        this.inscricao = inscricao;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getReferenciaFatura() {
        return referenciaFatura;
    }

    public void setReferenciaFatura(String referenciaFatura) {
        this.referenciaFatura = referenciaFatura;
    }

    public String getSituacaoAgua() {
        return situacaoAgua;
    }

    public void setSituacaoAgua(String situacaoAgua) {
        this.situacaoAgua = situacaoAgua;
    }

    public String getSituacaoEsgoto() {
        return situacaoEsgoto;
    }

    public void setSituacaoEsgoto(String situacaoEsgoto) {
        this.situacaoEsgoto = situacaoEsgoto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getValorConta() {
        return valorConta;
    }

    public void setValorConta(BigDecimal valorConta) {
        this.valorConta = valorConta;
    }

    public String getMensagemFaixaValor() {
        return mensagemFaixaValor;
    }

    public void setMensagemFaixaValor(String mensagemFaixaValor) {
        this.mensagemFaixaValor = mensagemFaixaValor;
    }


	public BigDecimal getValorTotalFaixaDois() {
		return valorTotalFaixaDois;
	}

	public void setValorTotalFaixaDois(BigDecimal valorTotalFaixaDois) {
		this.valorTotalFaixaDois = valorTotalFaixaDois;
	}

	public BigDecimal getValorTotalFaixaTres() {
		return valorTotalFaixaTres;
	}

	public void setValorTotalFaixaTres(BigDecimal valorTotalFaixaTres) {
		this.valorTotalFaixaTres = valorTotalFaixaTres;
	}

	public BigDecimal getValorTotalFaixaUm() {
		return valorTotalFaixaUm;
	}

	public void setValorTotalFaixaUm(BigDecimal valorTotalFaixaUm) {
		this.valorTotalFaixaUm = valorTotalFaixaUm;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

    
    
}
