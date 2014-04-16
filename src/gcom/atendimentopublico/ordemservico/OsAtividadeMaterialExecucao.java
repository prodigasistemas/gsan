package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OsAtividadeMaterialExecucao implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private BigDecimal quantidadeMaterial;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.OrdemServicoAtividade ordemServicoAtividade;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.Material material;

    /** full constructor */
    public OsAtividadeMaterialExecucao(BigDecimal quantidadeMaterial, Date ultimaAlteracao, gcom.atendimentopublico.ordemservico.OrdemServicoAtividade ordemServicoAtividade, gcom.atendimentopublico.ordemservico.Material material) {
        this.quantidadeMaterial = quantidadeMaterial;
        this.ultimaAlteracao = ultimaAlteracao;
        this.ordemServicoAtividade = ordemServicoAtividade;
        this.material = material;
    }

    /** default constructor */
    public OsAtividadeMaterialExecucao() {
    }

    /** minimal constructor */
    public OsAtividadeMaterialExecucao(gcom.atendimentopublico.ordemservico.OrdemServicoAtividade ordemServicoAtividade, gcom.atendimentopublico.ordemservico.Material material) {
        this.ordemServicoAtividade = ordemServicoAtividade;
        this.material = material;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getQuantidadeMaterial() {
        return this.quantidadeMaterial;
    }

    public void setQuantidadeMaterial(BigDecimal quantidadeMaterial) {
        this.quantidadeMaterial = quantidadeMaterial;
    }
    
    public Date getUltimaAlteracao() {
		return ultimaAlteracao;
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

    public gcom.atendimentopublico.ordemservico.Material getMaterial() {
        return this.material;
    }

    public void setMaterial(gcom.atendimentopublico.ordemservico.Material material) {
        this.material = material;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
