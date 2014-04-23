package gcom.atendimentopublico.registroatendimento;

import gcom.cadastro.cliente.FoneTipo;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class SolicitanteFone implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Short ddd;

    /** nullable persistent field */
    private String fone;

    /** nullable persistent field */
    private String ramal;

    /** nullable persistent field */
    private Short indicadorPadrao;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private FoneTipo foneTipo;

    /** persistent field */
    private gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante registroAtendimentoSolicitante;
    
    public static final Short INDICADOR_FONE_PADRAO = new Short("1");

    /** full constructor */
    public SolicitanteFone(Short ddd, String fone, String ramal, Short indicadorPadrao, Date ultimaAlteracao, FoneTipo foneTipo, gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante registroAtendimentoSolicitante) {
        this.ddd = ddd;
        this.fone = fone;
        this.ramal = ramal;
        this.indicadorPadrao = indicadorPadrao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.foneTipo = foneTipo;
        this.registroAtendimentoSolicitante = registroAtendimentoSolicitante;
    }

    /** default constructor */
    public SolicitanteFone() {
    }

    /** minimal constructor */
    public SolicitanteFone(FoneTipo foneTipo, gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante registroAtendimentoSolicitante) {
        this.foneTipo = foneTipo;
        this.registroAtendimentoSolicitante = registroAtendimentoSolicitante;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getDdd() {
        return this.ddd;
    }

    public void setDdd(Short ddd) {
        this.ddd = ddd;
    }

    public String getFone() {
        return this.fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getRamal() {
        return this.ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public Short getIndicadorPadrao() {
        return this.indicadorPadrao;
    }

    public void setIndicadorPadrao(Short indicadorPadrao) {
        this.indicadorPadrao = indicadorPadrao;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public FoneTipo getFoneTipo() {
        return this.foneTipo;
    }

    public void setFoneTipo(FoneTipo foneTipo) {
        this.foneTipo = foneTipo;
    }

    public gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante getRegistroAtendimentoSolicitante() {
        return this.registroAtendimentoSolicitante;
    }

    public void setRegistroAtendimentoSolicitante(gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante registroAtendimentoSolicitante) {
        this.registroAtendimentoSolicitante = registroAtendimentoSolicitante;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
