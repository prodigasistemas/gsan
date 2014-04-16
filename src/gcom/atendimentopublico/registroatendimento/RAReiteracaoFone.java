package gcom.atendimentopublico.registroatendimento;

import gcom.cadastro.cliente.FoneTipo;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class RAReiteracaoFone implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String ddd;

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
    private gcom.atendimentopublico.registroatendimento.RAReiteracao raReiteracao;
    
    public static final Short INDICADOR_FONE_PADRAO = new Short("1");

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public FoneTipo getFoneTipo() {
		return foneTipo;
	}

	public void setFoneTipo(FoneTipo foneTipo) {
		this.foneTipo = foneTipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorPadrao() {
		return indicadorPadrao;
	}

	public void setIndicadorPadrao(Short indicadorPadrao) {
		this.indicadorPadrao = indicadorPadrao;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public gcom.atendimentopublico.registroatendimento.RAReiteracao getRaReiteracao() {
		return raReiteracao;
	}

	public void setRaReiteracao(
			gcom.atendimentopublico.registroatendimento.RAReiteracao raReiteracao) {
		this.raReiteracao = raReiteracao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getTelefoneFormatado() {
		
		String foneFormatado = this.fone;
		
		if (this.ddd != null){
			foneFormatado =  "(" + this.ddd + ")" + foneFormatado;
		}
		if(this.ramal != null){
			foneFormatado = foneFormatado + "-" + this.ramal;
		}
		
		return foneFormatado;
	}
}
