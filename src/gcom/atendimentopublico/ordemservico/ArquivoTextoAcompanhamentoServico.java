package gcom.atendimentopublico.ordemservico;

import gcom.micromedicao.SituacaoTransmissaoLeitura;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ArquivoTextoAcompanhamentoServico implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private Date dataProgramacao;

    /** persistent field */
    private SituacaoTransmissaoLeitura situacaoTransmissaoLeitura;

    /** nullable persistent field */
    private BigDecimal imei;

    /** persistent field */
    private Date dataUltimaAlteracao;

    /** persistent field */
    private Equipe equipe;

    /** persistent field */
    private Set osPrgAcompServicos;

    /** default constructor */
    public ArquivoTextoAcompanhamentoServico() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer txacId) {
        this.id = txacId;
    }

    public Date getDataProgramacao() {
        return this.dataProgramacao;
    }

    public void setDataProgramacao(Date txacDtprogramacao) {
        this.dataProgramacao = txacDtprogramacao;
    }

    public SituacaoTransmissaoLeitura getSituacaoTransmissaoLeitura() {
        return this.situacaoTransmissaoLeitura;
    }

    public void setSituacaoTransmissaoLeitura(SituacaoTransmissaoLeitura situacaoTransmissaoLeitura) {
        this.situacaoTransmissaoLeitura = situacaoTransmissaoLeitura;
    }

    public BigDecimal getImei() {
        return this.imei;
    }

    public void setImei(BigDecimal txacNnimei) {
        this.imei = txacNnimei;
    }

    public Date getDataUltimaAlteracao() {
        return this.dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(Date txacTmultimaalteracao) {
        this.dataUltimaAlteracao = txacTmultimaalteracao;
    }

    public Equipe getEquipe() {
        return this.equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public Set getOsPrgAcompServicos() {
        return this.osPrgAcompServicos;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("txacId", getId())
            .toString();
    }

}
