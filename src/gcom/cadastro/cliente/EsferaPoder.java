package gcom.cadastro.cliente;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class EsferaPoder implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private Short indicadorUso;
    
    private Short indicadorPermiteCertidaoNegativaDebitosParaImovel;
    
    private Short indicadorPermiteCertidaoNegativaDebitosParaCliente;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /**
     * Description of the Field
     */
    public final static Short MUNICIPAL = new Short("1");
    public final static Short ESTADUAL = new Short("2");
    public final static Short FEDERAL = new Short("3");
    public final static Short PARTICULAR = new Short("4");
    
    /** full constructor */
    public EsferaPoder(String descricao, Short indicadorUso,
			Short indicadorPermiteCertidaoNegativaDebitosParaImovel,
			Short indicadorPermiteCertidaoNegativaDebitosParaCliente,
			Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.indicadorPermiteCertidaoNegativaDebitosParaImovel = indicadorPermiteCertidaoNegativaDebitosParaImovel;
        this.indicadorPermiteCertidaoNegativaDebitosParaCliente = indicadorPermiteCertidaoNegativaDebitosParaCliente;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public EsferaPoder() {
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

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

	/**
	 * @return Retorna o campo indicadorPermiteCertidaoNegativaDebitosParaCliente.
	 */
	public Short getIndicadorPermiteCertidaoNegativaDebitosParaCliente() {
		return indicadorPermiteCertidaoNegativaDebitosParaCliente;
	}

	/**
	 * @param indicadorPermiteCertidaoNegativaDebitosParaCliente O indicadorPermiteCertidaoNegativaDebitosParaCliente a ser setado.
	 */
	public void setIndicadorPermiteCertidaoNegativaDebitosParaCliente(
			Short indicadorPermiteCertidaoNegativaDebitosParaCliente) {
		this.indicadorPermiteCertidaoNegativaDebitosParaCliente = indicadorPermiteCertidaoNegativaDebitosParaCliente;
	}

	/**
	 * @return Retorna o campo indicadorPermiteCertidaoNegativaDebitosParaImovel.
	 */
	public Short getIndicadorPermiteCertidaoNegativaDebitosParaImovel() {
		return indicadorPermiteCertidaoNegativaDebitosParaImovel;
	}

	/**
	 * @param indicadorPermiteCertidaoNegativaDebitosParaImovel O indicadorPermiteCertidaoNegativaDebitosParaImovel a ser setado.
	 */
	public void setIndicadorPermiteCertidaoNegativaDebitosParaImovel(
			Short indicadorPermiteCertidaoNegativaDebitosParaImovel) {
		this.indicadorPermiteCertidaoNegativaDebitosParaImovel = indicadorPermiteCertidaoNegativaDebitosParaImovel;
	}

}
