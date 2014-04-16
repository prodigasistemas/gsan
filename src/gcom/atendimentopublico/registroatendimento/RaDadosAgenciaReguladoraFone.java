package gcom.atendimentopublico.registroatendimento;

import gcom.cadastro.cliente.FoneTipo;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class RaDadosAgenciaReguladoraFone extends ObjetoTransacao {
	
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
    private Short indicadoFonePadrao;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private FoneTipo foneTipo;

    /** persistent field */
    private gcom.atendimentopublico.registroatendimento.RaDadosAgenciaReguladora raDadosAgenciaReguladora;

    /** full constructor */
    public RaDadosAgenciaReguladoraFone(Short ddd, String fone, String ramal, Short indicadoFonePadrao, Date ultimaAlteracao, FoneTipo foneTipo, gcom.atendimentopublico.registroatendimento.RaDadosAgenciaReguladora raDadosAgenciaReguladora) {
        this.ddd = ddd;
        this.fone = fone;
        this.ramal = ramal;
        this.indicadoFonePadrao = indicadoFonePadrao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.foneTipo = foneTipo;
        this.raDadosAgenciaReguladora = raDadosAgenciaReguladora;
    }

    /** default constructor */
    public RaDadosAgenciaReguladoraFone() {
    }

    /** minimal constructor */
    public RaDadosAgenciaReguladoraFone(FoneTipo foneTipo, gcom.atendimentopublico.registroatendimento.RaDadosAgenciaReguladora raDadosAgenciaReguladora) {
        this.foneTipo = foneTipo;
        this.raDadosAgenciaReguladora = raDadosAgenciaReguladora;
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

    public Short getIndicadoFonePadrao() {
        return this.indicadoFonePadrao;
    }

    public void setIndicadoFonePadrao(Short indicadoFonePadrao) {
        this.indicadoFonePadrao = indicadoFonePadrao;
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

    public gcom.atendimentopublico.registroatendimento.RaDadosAgenciaReguladora getRaDadosAgenciaReguladora() {
        return this.raDadosAgenciaReguladora;
    }

    public void setRaDadosAgenciaReguladora(gcom.atendimentopublico.registroatendimento.RaDadosAgenciaReguladora raDadosAgenciaReguladora) {
        this.raDadosAgenciaReguladora = raDadosAgenciaReguladora;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	    
    public FiltroRaDadosAgenciaReguladoraFone  retornaFiltro() {
		FiltroRaDadosAgenciaReguladoraFone filtroRaDadosAgenciaReguladoraFone = new FiltroRaDadosAgenciaReguladoraFone();

		filtroRaDadosAgenciaReguladoraFone.adicionarParametro(new ParametroSimples(FiltroRaDadosAgenciaReguladoraFone.ID,this.getId()));
		filtroRaDadosAgenciaReguladoraFone.adicionarCaminhoParaCarregamentoEntidade("raDadosAgenciaReguladora");
		filtroRaDadosAgenciaReguladoraFone.adicionarCaminhoParaCarregamentoEntidade("foneTipo");
		
		
		return filtroRaDadosAgenciaReguladoraFone;
	}

}
