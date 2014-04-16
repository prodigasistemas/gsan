package gcom.gerencial.cadastro.geografico;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GMunicipio implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String nome;

    /** nullable persistent field */
    private Integer cepInicio;

    /** nullable persistent field */
    private Integer cepFim;

    /** nullable persistent field */
    private Short ddd;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Date dataConcessaoInicio;

    /** nullable persistent field */
    private Date dataConcessaoFim;

    /** persistent field */
    private GMicrorregiao gerMicrorregiao;

    /** persistent field */
    private Set gerBairros;

    /** persistent field */
    private Set rgResumoLigacaoEconomias;

    /** full constructor */
    public GMunicipio(Integer id, String nome, Integer cepInicio, Integer cepFim, Short ddd, Short indicadorUso, Date ultimaAlteracao, Date dataConcessaoInicio, Date dataConcessaoFim, GMicrorregiao gMicrorregiao, Set gBairros, Set rgResumoLigacaoEconomias) {
        this.id = id;
        this.nome = nome;
        this.cepInicio = cepInicio;
        this.cepFim = cepFim;
        this.ddd = ddd;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.dataConcessaoInicio = dataConcessaoInicio;
        this.dataConcessaoFim = dataConcessaoFim;
        this.gerMicrorregiao = gMicrorregiao;
        this.gerBairros = gBairros;
        this.rgResumoLigacaoEconomias = rgResumoLigacaoEconomias;
    }

    /** default constructor */
    public GMunicipio() {
    }

    /** minimal constructor */
    public GMunicipio(Integer id, GMicrorregiao gMicrorregiao, Set gBairros, Set rgResumoLigacaoEconomias) {
        this.id = id;
        this.gerMicrorregiao = gMicrorregiao;
        this.gerBairros = gBairros;
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

    public Integer getCepInicio() {
        return this.cepInicio;
    }

    public void setCepInicio(Integer cepInicio) {
        this.cepInicio = cepInicio;
    }

    public Integer getCepFim() {
        return this.cepFim;
    }

    public void setCepFim(Integer cepFim) {
        this.cepFim = cepFim;
    }

    public Short getDdd() {
        return this.ddd;
    }

    public void setDdd(Short ddd) {
        this.ddd = ddd;
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

    public Date getDataConcessaoInicio() {
        return this.dataConcessaoInicio;
    }

    public void setDataConcessaoInicio(Date dataConcessaoInicio) {
        this.dataConcessaoInicio = dataConcessaoInicio;
    }

    public Date getDataConcessaoFim() {
        return this.dataConcessaoFim;
    }

    public void setDataConcessaoFim(Date dataConcessaoFim) {
        this.dataConcessaoFim = dataConcessaoFim;
    }

 

    public Set getGerBairros() {
		return gerBairros;
	}

	public void setGerBairros(Set gerBairros) {
		this.gerBairros = gerBairros;
	}

	public GMicrorregiao getGerMicrorregiao() {
		return gerMicrorregiao;
	}

	public void setGerMicrorregiao(
			GMicrorregiao gerMicrorregiao) {
		this.gerMicrorregiao = gerMicrorregiao;
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
