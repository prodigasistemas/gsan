package gcom.seguranca.transacao;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Transacao implements Serializable {

	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private String nome;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private String nomeAplicacao;

    /** nullable persistent field */
    private Date ltimaAlteracao;

    /** persistent field */
    private gcom.seguranca.transacao.Transacao transacao;

    /** full constructor */
    public Transacao(String nome, String descricao, String nomeAplicacao, Date ltimaAlteracao, gcom.seguranca.transacao.Transacao transacao) {
        this.nome = nome;
        this.descricao = descricao;
        this.nomeAplicacao = nomeAplicacao;
        this.ltimaAlteracao = ltimaAlteracao;
        this.transacao = transacao;
    }

    /** default constructor */
    public Transacao() {
    }

    /** minimal constructor */
    public Transacao(String nome, gcom.seguranca.transacao.Transacao transacao) {
        this.nome = nome;
        this.transacao = transacao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeAplicacao() {
        return this.nomeAplicacao;
    }

    public void setNomeAplicacao(String nomeAplicacao) {
        this.nomeAplicacao = nomeAplicacao;
    }

    public Date getLtimaAlteracao() {
        return this.ltimaAlteracao;
    }

    public void setLtimaAlteracao(Date ltimaAlteracao) {
        this.ltimaAlteracao = ltimaAlteracao;
    }

    public gcom.seguranca.transacao.Transacao getTransacao() {
        return this.transacao;
    }

    public void setTransacao(gcom.seguranca.transacao.Transacao transacao) {
        this.transacao = transacao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
