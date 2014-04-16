package gcom.gerencial.atendimentopublico.registroatendimento;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GSolicitacaoTipo implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Set unResumoRegistroAtendimentos;

    /** full constructor */
    public GSolicitacaoTipo(Integer id, String descricao, Date ultimaAlteracao, Set unResumoRegistroAtendimentos) {
        this.id = id;
        this.descricao = descricao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.unResumoRegistroAtendimentos = unResumoRegistroAtendimentos;
    }

    /** default constructor */
    public GSolicitacaoTipo() {
    }

    /** minimal constructor */
    public GSolicitacaoTipo(Integer id, Date ultimaAlteracao, Set unResumoRegistroAtendimentos) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.unResumoRegistroAtendimentos = unResumoRegistroAtendimentos;
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

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Set getUnResumoRegistroAtendimentos() {
        return this.unResumoRegistroAtendimentos;
    }

    public void setUnResumoRegistroAtendimentos(Set unResumoRegistroAtendimentos) {
        this.unResumoRegistroAtendimentos = unResumoRegistroAtendimentos;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
