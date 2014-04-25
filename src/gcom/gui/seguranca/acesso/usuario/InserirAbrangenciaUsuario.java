package gcom.gui.seguranca.acesso.usuario;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;



/**
 * Descrição da classe 
 *
 * @author Thiago Tenório
 * @date 04/05/2006
 */
public class InserirAbrangenciaUsuario implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoUsuarioAbrangencia;

    /** nullable persistent field */
    private String descricaoAbreviada;

   
    /** nullable persistent field */
    private String abrangenciaSuperior;

    /** nullable persistent field */
    private Date ultimaAlteracao;


	/** full constructor */
    public InserirAbrangenciaUsuario(String descricaoUsuarioAbrangencia, String abrangenciaSuperior, String descricaoAbreviada, Date ultimaAlteracao) {
        this.descricaoUsuarioAbrangencia = descricaoUsuarioAbrangencia;
        this.descricaoAbreviada = descricaoAbreviada;
        this.ultimaAlteracao = ultimaAlteracao;

        this.abrangenciaSuperior = abrangenciaSuperior;
    }

    /** default constructor */
    public InserirAbrangenciaUsuario() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoUsuarioSituacao() {
        return this.descricaoUsuarioAbrangencia;
    }

    public void setDescricaoUsuarioAbrangencia(String descricaoUsuarioAbrangencia) {
        this.descricaoUsuarioAbrangencia = descricaoUsuarioAbrangencia;
    }

    public String getDescricaoAbreviada() {
        return this.descricaoAbreviada;
    }

    public void setDescricaoAbreviada(String descricaoAbreviada) {
        this.descricaoAbreviada = descricaoAbreviada;
    }

    public String getAbrangenciaSuperior() {
        return this.abrangenciaSuperior;
    }

    public void setAbrangenciaSuperior(String abrangenciaSuperior) {
        this.abrangenciaSuperior = abrangenciaSuperior;
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

