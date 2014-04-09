package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OsAtividadePeriodoExecucao implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private Date dataInicio;

    /** persistent field */
    private Date dataFim;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.OrdemServicoAtividade ordemServicoAtividade;

    /** full constructor */
    public OsAtividadePeriodoExecucao(Date dataInicio, Date dataFim, Date ultimaAlteracao, gcom.atendimentopublico.ordemservico.OrdemServicoAtividade ordemServicoAtividade) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.ultimaAlteracao = ultimaAlteracao;
        this.ordemServicoAtividade = ordemServicoAtividade;
    }

    /** default constructor */
    public OsAtividadePeriodoExecucao() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return this.dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.atendimentopublico.ordemservico.OrdemServicoAtividade getOrdemServicoAtividade() {
        return this.ordemServicoAtividade;
    }

    public void setOrdemServicoAtividade(gcom.atendimentopublico.ordemservico.OrdemServicoAtividade ordemServicoAtividade) {
        this.ordemServicoAtividade = ordemServicoAtividade;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
