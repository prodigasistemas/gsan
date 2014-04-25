package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Atividade implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public final static Integer INDICADOR_ATIVIDADE_UNICA_SIM = 1;

	public final static Integer INDICADOR_ATIVIDADE_UNICA_NAO = 2;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private String descricaoAbreviada;

    /** persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Short indicadorAtividadeUnica;

    /** full constructor */
    public Atividade(String descricao, String descricaoAbreviada, Short indicadorUso, Date ultimaAlteracao, Short indicadorAtividadeUnica) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorAtividadeUnica = indicadorAtividadeUnica;
    }

    /** default constructor */
    public Atividade() {
    }

    /** minimal constructor */
    public Atividade(short indicadorUso, Date ultimaAlteracao) {
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
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

    public Short getIndicadorAtividadeUnica() {
        return this.indicadorAtividadeUnica;
    }

    public void setIndicadorAtividadeUnica(Short indicadorAtividadeUnica) {
        this.indicadorAtividadeUnica = indicadorAtividadeUnica;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
	/**
	 * < <Descrição do método>>
	 * 
	 * @param other
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof Atividade)) {
			return false;
		}
		Atividade castOther = (Atividade) other;

		return (this.getId().equals(castOther.getId()));
	}

}
