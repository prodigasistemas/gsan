package gcom.atendimentopublico.ordemservico;

import gcom.integracao.ServicoTerceiroAcompanhamentoServico;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OSAtividadeProgramacaoAcompanhamentoServico implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private short indicadorAtualizacaoOS;

    /** persistent field */
    private short indicadorTransmissaoOS;

    /** persistent field */
    private Date dataUltimaAlteracao;
    
    /** persistent field */
    private BigDecimal qtdMaterialExedente;

    /** persistent field */
    private OrdemServicoSituacao ordemServicoSituacao;

    private Atividade atividade;
    
    private EquipamentosEspeciais equipamentoFaltante;
    
    private OSProgramacaoAcompanhamentoServico osProgramacaoAcompanhamentoServico;
    
    private ServicoTerceiroAcompanhamentoServico servicoTerceiroAcompanhamentoServico;
    
	/** persistent field */
	private short indicadorExcluido;

    public OSProgramacaoAcompanhamentoServico getOsProgramacaoAcompanhamentoServico() {
		return osProgramacaoAcompanhamentoServico;
	}

	public void setOsProgramacaoAcompanhamentoServico(
			OSProgramacaoAcompanhamentoServico osProgramacaoAcompanhamentoServico) {
		this.osProgramacaoAcompanhamentoServico = osProgramacaoAcompanhamentoServico;
	}

	public Integer getId() {
        return this.id;
    }

    public void setId(Integer oatsId) {
        this.id = oatsId;
    }

    public short getIndicadorAtualizacaoOS() {
        return this.indicadorAtualizacaoOS;
    }

    public void setIndicadorAtualizacaoOS(short oatsIcatualizaos) {
        this.indicadorAtualizacaoOS = oatsIcatualizaos;
    }

    public short getIndicadorTransmissaoOS() {
        return this.indicadorTransmissaoOS;
    }

    public void setIndicadorTransmissaoOS(short oatsIctransmissao) {
        this.indicadorTransmissaoOS = oatsIctransmissao;
    }

    public Date getDataUltimaAlteracao() {
        return this.dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(Date oatsTmultimaalteracao) {
        this.dataUltimaAlteracao = oatsTmultimaalteracao;
    }

    public OrdemServicoSituacao getOrdemServicoSituacao() {
        return this.ordemServicoSituacao;
    }

    public void setOrdemServicoSituacao(OrdemServicoSituacao ordemServicoSituacao) {
        this.ordemServicoSituacao = ordemServicoSituacao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("oatsId", getId())
            .toString();
    }

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public EquipamentosEspeciais getEquipamentoFaltante() {
		return equipamentoFaltante;
	}

	public void setEquipamentoFaltante(EquipamentosEspeciais equipamentoFaltante) {
		this.equipamentoFaltante = equipamentoFaltante;
	}

	public short getIndicadorExcluido() {
		return indicadorExcluido;
	}

	public void setIndicadorExcluido(short indicadorExcluido) {
		this.indicadorExcluido = indicadorExcluido;
	}

	public ServicoTerceiroAcompanhamentoServico getServicoTerceiroAcompanhamentoServico() {
		return servicoTerceiroAcompanhamentoServico;
	}

	public void setServicoTerceiroAcompanhamentoServico(
			ServicoTerceiroAcompanhamentoServico servicoTerceiroAcompanhamentoServico) {
		this.servicoTerceiroAcompanhamentoServico = servicoTerceiroAcompanhamentoServico;
	}

	public BigDecimal getQtdMaterialExedente() {
		return qtdMaterialExedente;
	}

	public void setQtdMaterialExedente(BigDecimal qtdMaterialExedente) {
		this.qtdMaterialExedente = qtdMaterialExedente;
	}
}
