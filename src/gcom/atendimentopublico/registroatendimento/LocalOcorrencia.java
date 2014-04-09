package gcom.atendimentopublico.registroatendimento;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class LocalOcorrencia implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private String descricaoAbreviada;

    /** persistent field */
    private short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;
    
    /** persistent field */
    private short indicadorRua;
    
    /** persistent field */
    private short indicadorCalcada;

    /** full constructor */
    public LocalOcorrencia(String descricao, String descricaoAbreviada, short indicadorUso, Date ultimaAlteracao, short indicadorRua, short indicadorCalcada) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorRua = indicadorRua;
        this.indicadorCalcada = indicadorCalcada; 
    }

    /** default constructor */
    public LocalOcorrencia() {
    }

    /** minimal constructor */
    public LocalOcorrencia(short indicadorUso, Date ultimaAlteracao) {
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }
    
    

    public short getIndicadorCalcada() {
		return indicadorCalcada;
	}

	public void setIndicadorCalcada(short indicadorCalcada) {
		this.indicadorCalcada = indicadorCalcada;
	}

	public short getIndicadorRua() {
		return indicadorRua;
	}

	public void setIndicadorRua(short indicadorRua) {
		this.indicadorRua = indicadorRua;
	}

	public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoAbreviada() {
        return this.descricaoAbreviada;
    }

    public void setDescricaoAbreviada(String descricaoAbreviada) {
        this.descricaoAbreviada = descricaoAbreviada;
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
