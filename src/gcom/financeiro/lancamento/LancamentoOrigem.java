package gcom.financeiro.lancamento;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class LancamentoOrigem implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private String descricaoAbreviada;
    
    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Short numeroCartao;
    
    /** nullable persistent field */
    private Short codigoTipo;
    
    /** nullable persistent field */
    private Short numeroFolha;
    
    /** nullable persistent field */
    private String codigoReferencia;
    
    /** nullable persistent field */
    private Short numeroVersao;
    
    /** nullable persistent field */
    private Short numeroCartao2;
    
    /** nullable persistent field */
    private Integer numeroHistoricoCredito;

    /** nullable persistent field */
    private Integer numeroHistoricoDebito;
    

    
    
    //Constantes
    public final static Integer FATURAMENTO = new Integer("1");
    
    public final static Integer ARRECADACAO = new Integer("2");
    
    public final static Integer DEVEDORES_DUVIDOSOS = new Integer("3");
    
    public final static Integer AVISO_BANCARIO = new Integer("4");

    /** full constructor */
    public LancamentoOrigem(String descricao, String descricaoAbreviada, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public LancamentoOrigem() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoAbreviada() {
        return this.descricaoAbreviada;
    }

    public void setDescricaoAbreviada(String descricaoAbreviada) {
        this.descricaoAbreviada = descricaoAbreviada;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public String getCodigoReferencia() {
		return codigoReferencia;
	}

	public void setCodigoReferencia(String codigoReferencia) {
		this.codigoReferencia = codigoReferencia;
	}

	public Short getCodigoTipo() {
		return codigoTipo;
	}

	public void setCodigoTipo(Short codigoTipo) {
		this.codigoTipo = codigoTipo;
	}

	public Short getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(Short numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public Short getNumeroCartao2() {
		return numeroCartao2;
	}

	public void setNumeroCartao2(Short numeroCartao2) {
		this.numeroCartao2 = numeroCartao2;
	}

	public Short getNumeroFolha() {
		return numeroFolha;
	}

	public void setNumeroFolha(Short numeroFolha) {
		this.numeroFolha = numeroFolha;
	}

	public Short getNumeroVersao() {
		return numeroVersao;
	}

	public void setNumeroVersao(Short numeroVersao) {
		this.numeroVersao = numeroVersao;
	}
	
	public Integer getNumeroHistoricoCredito() {
		return numeroHistoricoCredito;
	}

	public void setNumeroHistoricoCredito(Integer numeroHistoricoCredito) {
		this.numeroHistoricoCredito = numeroHistoricoCredito;
	}

	public Integer getNumeroHistoricoDebito() {
		return numeroHistoricoDebito;
	}

	public void setNumeroHistoricoDebito(Integer numeroHistoricoDebito) {
		this.numeroHistoricoDebito = numeroHistoricoDebito;
	}
	

}
