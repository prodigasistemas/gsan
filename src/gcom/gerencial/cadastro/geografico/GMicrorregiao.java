package gcom.gerencial.cadastro.geografico;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GMicrorregiao implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String nome;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private GRegiao gerRegiao;

    /** persistent field */
    private Set gerMunicipios;

    /** persistent field */
    private Set rgResumoLigacaoEconomias;

    /** full constructor */
    public GMicrorregiao(String nome, Short indicadorUso, Date ultimaAlteracao, GRegiao gRegiao, Set gMunicipios, Set rgResumoLigacaoEconomias) {
        this.nome = nome;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerRegiao = gRegiao;
        this.gerMunicipios = gMunicipios;
        this.rgResumoLigacaoEconomias = rgResumoLigacaoEconomias;
    }

    /** default constructor */
    public GMicrorregiao() {
    }

    /** minimal constructor */
    public GMicrorregiao(GRegiao gRegiao, Set gMunicipios, Set rgResumoLigacaoEconomias) {
        this.gerRegiao = gRegiao;
        this.gerMunicipios = gMunicipios;
        this.rgResumoLigacaoEconomias = rgResumoLigacaoEconomias;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

 

    public Set getGerMunicipios() {
		return gerMunicipios;
	}

	public void setGerMunicipios(Set gerMunicipios) {
		this.gerMunicipios = gerMunicipios;
	}

	public GRegiao getGerRegiao() {
		return gerRegiao;
	}

	public void setGerRegiao(GRegiao gerRegiao) {
		this.gerRegiao = gerRegiao;
	}

	public Set getRgResumoLigacaoEconomias() {
        return this.rgResumoLigacaoEconomias;
    }

    public void setRgResumoLigacaoEconomias(Set rgResumoLigacaoEconomias) {
        this.rgResumoLigacaoEconomias = rgResumoLigacaoEconomias;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
