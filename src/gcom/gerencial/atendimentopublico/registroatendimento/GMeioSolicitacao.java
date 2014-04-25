package gcom.gerencial.atendimentopublico.registroatendimento;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GMeioSolicitacao implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private String descricao;

    /** nullable persistent field */
    private String descricaoAbreviado;

    /** persistent field */
    private short indicador;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Set unResumoRegistroAtendimentos;

    /** full constructor */
    public GMeioSolicitacao(Integer id, String descricao, String descricaoAbreviado, short indicador, Date ultimaAlteracao, Set unResumoRegistroAtendimentos) {
        this.id = id;
        this.descricao = descricao;
        this.descricaoAbreviado = descricaoAbreviado;
        this.indicador = indicador;
        this.ultimaAlteracao = ultimaAlteracao;
        this.unResumoRegistroAtendimentos = unResumoRegistroAtendimentos;
    }

    /** default constructor */
    public GMeioSolicitacao() {
    }

    /** minimal constructor */
    public GMeioSolicitacao(Integer id, String descricao, short indicador, Date ultimaAlteracao, Set unResumoRegistroAtendimentos) {
        this.id = id;
        this.descricao = descricao;
        this.indicador = indicador;
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

    public String getDescricaoAbreviado() {
        return this.descricaoAbreviado;
    }

    public void setDescricaoAbreviado(String descricaoAbreviado) {
        this.descricaoAbreviado = descricaoAbreviado;
    }

    public short getIndicador() {
        return this.indicador;
    }

    public void setIndicador(short indicador) {
        this.indicador = indicador;
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
