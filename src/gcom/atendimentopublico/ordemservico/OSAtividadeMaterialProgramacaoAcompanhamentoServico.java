package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OSAtividadeMaterialProgramacaoAcompanhamentoServico implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private BigDecimal quantidadeMaterial;

    /** persistent field */
    private Date dataUltimaAlteracao;

    /** persistent field */
    private OSAtividadeProgramacaoAcompanhamentoServico osAtividadeProgramacaoAcompanhamentoServico;

    /** persistent field */
    private Material material;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer oamsId) {
        this.id = oamsId;
    }

    public BigDecimal getQuantidadeMaterial() {
        return this.quantidadeMaterial;
    }

    public void setQuantidadeMaterial(BigDecimal oatsQtmaterial) {
        this.quantidadeMaterial = oatsQtmaterial;
    }

    public Date getDataUltimaAlteracao() {
        return this.dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(Date oamsTmultimaalteracao) {
        this.dataUltimaAlteracao = oamsTmultimaalteracao;
    }

    public Material getMaterial() {
        return this.material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
    
    

    public OSAtividadeProgramacaoAcompanhamentoServico getOsAtividadeProgramacaoAcompanhamentoServico() {
		return osAtividadeProgramacaoAcompanhamentoServico;
	}

	public void setOsAtividadeProgramacaoAcompanhamentoServico(
			OSAtividadeProgramacaoAcompanhamentoServico osAtividadeProgramacaoAcompanhamentoServico) {
		this.osAtividadeProgramacaoAcompanhamentoServico = osAtividadeProgramacaoAcompanhamentoServico;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("oamsId", getId())
            .toString();
    }

}
