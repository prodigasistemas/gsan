package gcom.gerencial.cadastro.geografico;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GRegiao implements Serializable {
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
    private Set gerMicrorregiaos;

    /** persistent field */
    private Set rgResumoLigacaoEconomias;

    /** full constructor */
    public GRegiao(String nome, Short indicadorUso, Date ultimaAlteracao, Set gMicrorregiaos, Set rgResumoLigacaoEconomias) {
        this.nome = nome;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerMicrorregiaos = gMicrorregiaos;
        this.rgResumoLigacaoEconomias = rgResumoLigacaoEconomias;
    }

    /** default constructor */
    public GRegiao() {
    }

    /** minimal constructor */
    public GRegiao(Set gMicrorregiaos, Set rgResumoLigacaoEconomias) {
        this.gerMicrorregiaos = gMicrorregiaos;
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



    public Set getGerMicrorregiaos() {
		return gerMicrorregiaos;
	}

	public void setGerMicrorregiaos(Set gerMicrorregiaos) {
		this.gerMicrorregiaos = gerMicrorregiaos;
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
