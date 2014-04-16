package gcom.micromedicao.leitura;

import gcom.interceptor.ObjetoGcom;
import gcom.micromedicao.medicao.MedicaoHistorico;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class LeituraFaixaFalsa extends ObjetoGcom {
	private static final long serialVersionUID = 1L;
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer numeroFalsaInferior;

    /** nullable persistent field */
    private Integer numeroFalsaSuperior;

    /** nullable persistent field */
    private Integer numeroCorretaInferior;

    /** nullable persistent field */
    private Integer numeroCorretaSuperior;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private MedicaoHistorico medicaoHistorico;

    /** full constructor */
    public LeituraFaixaFalsa(Integer numeroFalsaInferior,
            Integer numeroFalsaSuperior, Integer numeroCorretaInferior,
            Integer numeroCorretaSuperior, Date ultimaAlteracao,
            MedicaoHistorico medicaoHistorico) {
        this.numeroFalsaInferior = numeroFalsaInferior;
        this.numeroFalsaSuperior = numeroFalsaSuperior;
        this.numeroCorretaInferior = numeroCorretaInferior;
        this.numeroCorretaSuperior = numeroCorretaSuperior;
        this.ultimaAlteracao = ultimaAlteracao;
        this.medicaoHistorico = medicaoHistorico;
    }

    /** default constructor */
    public LeituraFaixaFalsa() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroFalsaInferior() {
        return this.numeroFalsaInferior;
    }

    public void setNumeroFalsaInferior(Integer numeroFalsaInferior) {
        this.numeroFalsaInferior = numeroFalsaInferior;
    }

    public Integer getNumeroFalsaSuperior() {
        return this.numeroFalsaSuperior;
    }

    public void setNumeroFalsaSuperior(Integer numeroFalsaSuperior) {
        this.numeroFalsaSuperior = numeroFalsaSuperior;
    }

    public Integer getNumeroCorretaInferior() {
        return this.numeroCorretaInferior;
    }

    public void setNumeroCorretaInferior(Integer numeroCorretaInferior) {
        this.numeroCorretaInferior = numeroCorretaInferior;
    }

    public Integer getNumeroCorretaSuperior() {
        return this.numeroCorretaSuperior;
    }

    public void setNumeroCorretaSuperior(Integer numeroCorretaSuperior) {
        this.numeroCorretaSuperior = numeroCorretaSuperior;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public MedicaoHistorico getMedicaoHistorico() {
        return this.medicaoHistorico;
    }

    public void setMedicaoHistorico(MedicaoHistorico medicaoHistorico) {
        this.medicaoHistorico = medicaoHistorico;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

}
