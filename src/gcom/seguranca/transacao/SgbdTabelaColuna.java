package gcom.seguranca.transacao;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class SgbdTabelaColuna implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String nome;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.seguranca.transacao.SgbdTabela sgbdTabela;

    /** full constructor */
    public SgbdTabelaColuna(String nome, String descricao, Date ultimaAlteracao, gcom.seguranca.transacao.SgbdTabela sgbdTabela) {
        this.nome = nome;
        this.descricao = descricao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.sgbdTabela = sgbdTabela;
    }

    /** default constructor */
    public SgbdTabelaColuna() {
    }

    /** minimal constructor */
    public SgbdTabelaColuna(gcom.seguranca.transacao.SgbdTabela sgbdTabela) {
        this.sgbdTabela = sgbdTabela;
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

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.seguranca.transacao.SgbdTabela getSgbdTabela() {
        return this.sgbdTabela;
    }

    public void setSgbdTabela(gcom.seguranca.transacao.SgbdTabela sgbdTabela) {
        this.sgbdTabela = sgbdTabela;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
