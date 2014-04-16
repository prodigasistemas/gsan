package gcom.faturamento.conta;

import gcom.cadastro.cliente.Cliente;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Fatura implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;
    
    /** nullable persistent field */
    private Date vencimento;

    /** nullable persistent field */
    private Date emissao;

    /** nullable persistent field */
    private Date validade;

    /** nullable persistent field */
    //private Integer indicadorUso;

    /** nullable persistent field */
    private BigDecimal debito;

    /** nullable persistent field */
    private BigDecimal taxa;

    /** nullable persistent field */
    private Integer anoMesReferencia;

    /** nullable persistent field */
    private Integer sequencial;

    /** nullable persistent field */
    private Short flag;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Cliente cliente;
    
    /** persistent field */
    private Integer codigoQualifica;
    
    private String numeroFatura;

    /** full constructor */
    public Fatura(Date vencimento, Date emissao, Date validade, BigDecimal debito, BigDecimal taxa, Date ultimaAlteracao, Integer anoMesReferencia, Integer sequencial, Short flag, Cliente cliente) {
    	this.vencimento = vencimento;
        this.emissao = emissao;
        this.validade = validade;
        //this.indicadorUso = indicadorUso;
        this.debito = debito;
        this.taxa = taxa;
        this.ultimaAlteracao = ultimaAlteracao;
        this.anoMesReferencia = anoMesReferencia;
        this.sequencial = sequencial;
        this.flag = flag;
        this.cliente = cliente;
    }

    /**
	 * @return Retorna o campo vencimento.
	 */
	public Date getVencimento() {
		return vencimento;
	}

	/**
	 * @param vencimento O vencimento a ser setado.
	 */
	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	/** default constructor */
    public Fatura() {
    }

    /** minimal constructor */
    public Fatura(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getEmissao() {
        return this.emissao;
    }

    public void setEmissao(Date emissao) {
        this.emissao = emissao;
    }

    public Date getValidade() {
        return this.validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

//    public Integer getIndicadorUso() {
//        return this.indicadorUso;
//    }
//
//    public void setIndicadorUso(Integer indicadorUso) {
//        this.indicadorUso = indicadorUso;
//    }

    public BigDecimal getDebito() {
        return this.debito;
    }

    public void setDebito(BigDecimal debito) {
        this.debito = debito;
    }

    public BigDecimal getTaxa() {
        return this.taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public Integer getSequencial() {
        return this.sequencial;
    }

    public void setSequencial(Integer sequencial) {
        this.sequencial = sequencial;
    }

    public Short getFlag() {
        return this.flag;
    }

    public void setFlag(Short flag) {
        this.flag = flag;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Integer getCodigoQualifica() {
		return codigoQualifica;
	}

	public void setCodigoQualifica(Integer codigoQualifica) {
		this.codigoQualifica = codigoQualifica;
	}

	public String getNumeroFatura() {
		return numeroFatura;
	}

	public void setNumeroFatura(String numeroFatura) {
		this.numeroFatura = numeroFatura;
	}
}
