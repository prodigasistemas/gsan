package gcom.cadastro.cliente;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class EsferaPoder implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricao;
    private Short indicadorUso;
    private Short indicadorPermiteCertidaoNegativaDebitosParaImovel;
    private Short indicadorPermiteCertidaoNegativaDebitosParaCliente;
    private Date ultimaAlteracao;

    public final static Short MUNICIPAL = new Short("1");
    public final static Short ESTADUAL = new Short("2");
    public final static Short FEDERAL = new Short("3");
    public final static Short PARTICULAR = new Short("4");
    
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

    public EsferaPoder() {
    }
    
    public EsferaPoder(Integer id) {
    	this.id = id;
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

	public Short getIndicadorPermiteCertidaoNegativaDebitosParaCliente() {
		return indicadorPermiteCertidaoNegativaDebitosParaCliente;
	}

	public void setIndicadorPermiteCertidaoNegativaDebitosParaCliente(
			Short indicadorPermiteCertidaoNegativaDebitosParaCliente) {
		this.indicadorPermiteCertidaoNegativaDebitosParaCliente = indicadorPermiteCertidaoNegativaDebitosParaCliente;
	}

	public Short getIndicadorPermiteCertidaoNegativaDebitosParaImovel() {
		return indicadorPermiteCertidaoNegativaDebitosParaImovel;
	}

	public void setIndicadorPermiteCertidaoNegativaDebitosParaImovel(
			Short indicadorPermiteCertidaoNegativaDebitosParaImovel) {
		this.indicadorPermiteCertidaoNegativaDebitosParaImovel = indicadorPermiteCertidaoNegativaDebitosParaImovel;
	}
	
	public static Integer[] obterIdsEsferaPoderPublico() {
		Integer[] ids = new Integer[3];
		
		ids[0] = EsferaPoder.ESTADUAL.intValue();
		ids[1] = EsferaPoder.FEDERAL.intValue();
		ids[2] = EsferaPoder.MUNICIPAL.intValue();
		
		return ids;
	}

}
