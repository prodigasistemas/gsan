package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OrdemServicoAtividade implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.OrdemServico ordemServico;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.Atividade atividade;

    /** full constructor */
    public OrdemServicoAtividade(Date ultimaAlteracao, gcom.atendimentopublico.ordemservico.OrdemServico ordemServico, gcom.atendimentopublico.ordemservico.Atividade atividade) {
        this.ultimaAlteracao = ultimaAlteracao;
        this.ordemServico = ordemServico;
        this.atividade = atividade;
    }

    /** default constructor */
    public OrdemServicoAtividade() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.atendimentopublico.ordemservico.OrdemServico getOrdemServico() {
        return this.ordemServico;
    }

    public void setOrdemServico(gcom.atendimentopublico.ordemservico.OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public gcom.atendimentopublico.ordemservico.Atividade getAtividade() {
        return this.atividade;
    }

    public void setAtividade(gcom.atendimentopublico.ordemservico.Atividade atividade) {
        this.atividade = atividade;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
