package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ServicoTipoSubgrupo implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private String descricaoAbreviada;

    /** persistent field */
    private Date dataCadastramento;

    /** persistent field */
    private short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.ServicoTipoGrupo servicoTipoGrupo;

    /** full constructor */
    public ServicoTipoSubgrupo(String descricao, String descricaoAbreviada, Date dataCadastramento, short indicadorUso, Date ultimaAlteracao, gcom.atendimentopublico.ordemservico.ServicoTipoGrupo servicoTipoGrupo) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.dataCadastramento = dataCadastramento;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.servicoTipoGrupo = servicoTipoGrupo;
    }

    /** default constructor */
    public ServicoTipoSubgrupo() {
    }

    /** minimal constructor */
    public ServicoTipoSubgrupo(Date dataCadastramento, short indicadorUso, Date ultimaAlteracao, gcom.atendimentopublico.ordemservico.ServicoTipoGrupo servicoTipoGrupo) {
        this.dataCadastramento = dataCadastramento;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.servicoTipoGrupo = servicoTipoGrupo;
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

    public Date getDataCadastramento() {
        return this.dataCadastramento;
    }

    public void setDataCadastramento(Date dataCadastramento) {
        this.dataCadastramento = dataCadastramento;
    }

    public short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.atendimentopublico.ordemservico.ServicoTipoGrupo getServicoTipoGrupo() {
        return this.servicoTipoGrupo;
    }

    public void setServicoTipoGrupo(gcom.atendimentopublico.ordemservico.ServicoTipoGrupo servicoTipoGrupo) {
        this.servicoTipoGrupo = servicoTipoGrupo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
