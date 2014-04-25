package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OsProgramacaoRoteiro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private short sequencialProgramacao;

    /** persistent field */
    private short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.ProgramacaoRoteiro programacaoRoteiro;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao ordemServicoProgramacao;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.OsProgramNaoEncerMotivo osProgramNaoEncerMotivo;

    /** full constructor */
    public OsProgramacaoRoteiro(short sequencialProgramacao, short indicadorUso, Date ultimaAlteracao, gcom.atendimentopublico.ordemservico.ProgramacaoRoteiro programacaoRoteiro, gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao ordemServicoProgramacao, gcom.atendimentopublico.ordemservico.OsProgramNaoEncerMotivo osProgramNaoEncerMotivo) {
        this.sequencialProgramacao = sequencialProgramacao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.programacaoRoteiro = programacaoRoteiro;
        this.ordemServicoProgramacao = ordemServicoProgramacao;
        this.osProgramNaoEncerMotivo = osProgramNaoEncerMotivo;
    }

    /** default constructor */
    public OsProgramacaoRoteiro() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getSequencialProgramacao() {
        return this.sequencialProgramacao;
    }

    public void setSequencialProgramacao(short sequencialProgramacao) {
        this.sequencialProgramacao = sequencialProgramacao;
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

    public gcom.atendimentopublico.ordemservico.ProgramacaoRoteiro getProgramacaoRoteiro() {
        return this.programacaoRoteiro;
    }

    public void setProgramacaoRoteiro(gcom.atendimentopublico.ordemservico.ProgramacaoRoteiro programacaoRoteiro) {
        this.programacaoRoteiro = programacaoRoteiro;
    }

    public gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao getOrdemServicoProgramacao() {
        return this.ordemServicoProgramacao;
    }

    public void setOrdemServicoProgramacao(gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao ordemServicoProgramacao) {
        this.ordemServicoProgramacao = ordemServicoProgramacao;
    }

    public gcom.atendimentopublico.ordemservico.OsProgramNaoEncerMotivo getOsProgramNaoEncerMotivo() {
        return this.osProgramNaoEncerMotivo;
    }

    public void setOsProgramNaoEncerMotivo(gcom.atendimentopublico.ordemservico.OsProgramNaoEncerMotivo osProgramNaoEncerMotivo) {
        this.osProgramNaoEncerMotivo = osProgramNaoEncerMotivo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
