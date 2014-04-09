package gcom.gerencial.cadastro.geografico;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GBairro implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private int codigoBairro;

    /** nullable persistent field */
    private String nomeBairro;

    /** nullable persistent field */
    private Integer codigoBairroPrefeitura;

    /** nullable persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private GMunicipio gerMunicipio;

    /** persistent field */
    private Set unResumoLigacaoEconomiasRegiao;

    /** full constructor */
    public GBairro(int codigoBairro, String nomeBairro, Integer codigoBairroPrefeitura, Short indicadorUso, Date ultimaAlteracao, GMunicipio gMunicipio, Set unResumoLigacaoEconomiasRegiao) {
        this.codigoBairro = codigoBairro;
        this.nomeBairro = nomeBairro;
        this.codigoBairroPrefeitura = codigoBairroPrefeitura;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerMunicipio = gMunicipio;
        this.unResumoLigacaoEconomiasRegiao = unResumoLigacaoEconomiasRegiao;
    }

    /** default constructor */
    public GBairro() {
    }

    /** minimal constructor */
    public GBairro(int codigoBairro, Date ultimaAlteracao, GMunicipio gMunicipio, Set unResumoLigacaoEconomiasRegiao) {
        this.codigoBairro = codigoBairro;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerMunicipio = gMunicipio;
        this.unResumoLigacaoEconomiasRegiao = unResumoLigacaoEconomiasRegiao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCodigoBairro() {
        return this.codigoBairro;
    }

    public void setCodigoBairro(int codigoBairro) {
        this.codigoBairro = codigoBairro;
    }

    public String getNomeBairro() {
        return this.nomeBairro;
    }

    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    public Integer getCodigoBairroPrefeitura() {
        return this.codigoBairroPrefeitura;
    }

    public void setCodigoBairroPrefeitura(Integer codigoBairroPrefeitura) {
        this.codigoBairroPrefeitura = codigoBairroPrefeitura;
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

 

    public GMunicipio getGerMunicipio() {
		return gerMunicipio;
	}

	public void setGerMunicipio(GMunicipio gerMunicipio) {
		this.gerMunicipio = gerMunicipio;
	}

	public Set getUnResumoLigacaoEconomiasRegiao() {
        return this.unResumoLigacaoEconomiasRegiao;
    }

    public void setUnResumoLigacaoEconomiasRegiao(Set unResumoLigacaoEconomiasRegiao) {
        this.unResumoLigacaoEconomiasRegiao = unResumoLigacaoEconomiasRegiao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
