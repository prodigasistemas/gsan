package gcom.faturamento.conta;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ContaTipo implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private String descricao;

    /** persistent field */
    private short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;
    
    public static final Integer CONTA_RETIDA_POR_EC = new Integer("1");
    public static final Integer CONTA_RETIDA_POR_BC = new Integer("2");
    public static final Integer CONTA_CLIENTE_RESPONSAVEL = new Integer("3");
    public static final Integer CONTA_DEBITO_AUTOMATICO = new Integer("4");
    public static final Integer CONTA_NORMAL = new Integer("5");
    public static final Integer CONTA_DEBITO_AUTO_COM_CLIENTE_RESP = new Integer("6");


    /** full constructor */
    public ContaTipo(Integer id, String descricao, short indicadorUso, Date ultimaAlteracao) {
        this.id = id;
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public ContaTipo() {
    }

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

    
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
