package gcom.arrecadacao.debitoautomatico;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DebitoAutomaticoRetornoCodigo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Mantis 500
	 * Inclusão de constantes a serem consideradas na inclusão dos pagamentos para contas
	 * com débito automático.
	 * 
	 * @author Wellington Rocha
	 * @date 25/01/2012*/
	public static final Integer DEBITADO = new Integer(0);
	public static final Integer DEBITO_EFETUADO_DATA_DIFERENTE_DA_DATA_INFORMADA = new Integer(31);
	

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoDebitoAutomaticoRetornoCodigo;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
    public DebitoAutomaticoRetornoCodigo(String descricaoDebitoAutomaticoRetornoCodigo, Short indicadorUso, Date ultimaAlteracao) {
        this.descricaoDebitoAutomaticoRetornoCodigo = descricaoDebitoAutomaticoRetornoCodigo;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public DebitoAutomaticoRetornoCodigo() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    /**
	 * @return Returns the descricaoDebitoAutomaticoRetornoCodigo.
	 */
	public String getDescricaoDebitoAutomaticoRetornoCodigo() {
		return descricaoDebitoAutomaticoRetornoCodigo;
	}

	/**
	 * @param descricaoDebitoAutomaticoRetornoCodigo The descricaoDebitoAutomaticoRetornoCodigo to set.
	 */
	public void setDescricaoDebitoAutomaticoRetornoCodigo(
			String descricaoDebitoAutomaticoRetornoCodigo) {
		this.descricaoDebitoAutomaticoRetornoCodigo = descricaoDebitoAutomaticoRetornoCodigo;
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
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
