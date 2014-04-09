package gcom.gui.seguranca.acesso.usuario;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
/**
 * Descrição da classe 
 *
 * @author Administrador
 * @date 04/05/2006
 */
public class InserirSituacaoUsuario implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoUsuarioSituacao;

    /** nullable persistent field */
    private String descricaoAbreviada;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Short indicadorUsoSistema;

	/** full constructor */
    public InserirSituacaoUsuario(String descricaoUsuarioSituacao, String descricaoAbreviada, Short indicadorUso, Date ultimaAlteracao, Short indicadorUsoSistema) {
        this.descricaoUsuarioSituacao = descricaoUsuarioSituacao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorUsoSistema = indicadorUsoSistema; 
    }

    /** default constructor */
    public InserirSituacaoUsuario() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoUsuarioSituacao() {
        return this.descricaoUsuarioSituacao;
    }

    public void setDescricaoUsuarioSituacao(String descricaoUsuarioSituacao) {
        this.descricaoUsuarioSituacao = descricaoUsuarioSituacao;
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public Short getIndicadorUsoSistema() {
		return indicadorUsoSistema;
	}

	public void setIndicadorUsoSistema(Short indicadorUsoSistema) {
		this.indicadorUsoSistema = indicadorUsoSistema;
	}
}
