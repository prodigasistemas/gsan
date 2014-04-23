package gcom.gerencial.cadastro.localidade;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GUnidadeNegocio implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private String nome;

    /** persistent field */
    private String nomeAbreviado;

    /** persistent field */
    private short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.gerencial.cadastro.localidade.GGerenciaRegional gerGerenciaRegional;

    /** persistent field */
    private Set unResumoColetaEsgotos;

    /** persistent field */
    private Set unResumoConsumoAguas;

    /** persistent field */
    private Set unResumoFaturamentos;

    /** persistent field */
    private Set unResumoArrecadacao;

    /** persistent field */
    private Set unResumoLigacaoEconomias;

    /** persistent field */
    private Set gerLocalidade;
    
    /** persistent field */
    private Set unResumoIndicadorDesempenhoMicromedicaos;

    /** full constructor */
    public GUnidadeNegocio(String nome, String nomeAbreviado, short indicadorUso, Date ultimaAlteracao, gcom.gerencial.cadastro.localidade.GGerenciaRegional gGerenciaRegional, Set unResumoColetaEsgotos, Set unResumoConsumoAguas, Set unResumoFaturamentos, Set unResumoArrecadacao, Set unResumoLigacaoEconomias, Set gLocalidade) {
        this.nome = nome;
        this.nomeAbreviado = nomeAbreviado;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerGerenciaRegional = gGerenciaRegional;
        this.unResumoColetaEsgotos = unResumoColetaEsgotos;
        this.unResumoConsumoAguas = unResumoConsumoAguas;
        this.unResumoFaturamentos = unResumoFaturamentos;
        this.unResumoArrecadacao = unResumoArrecadacao;
        this.unResumoLigacaoEconomias = unResumoLigacaoEconomias;
        this.gerLocalidade = gLocalidade;
    }

    /** default constructor */
    public GUnidadeNegocio() {
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

    public String getNomeAbreviado() {
        return this.nomeAbreviado;
    }

    public void setNomeAbreviado(String nomeAbreviado) {
        this.nomeAbreviado = nomeAbreviado;
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

   
    public gcom.gerencial.cadastro.localidade.GGerenciaRegional getGerGerenciaRegional() {
		return gerGerenciaRegional;
	}

	public void setGerGerenciaRegional(
			gcom.gerencial.cadastro.localidade.GGerenciaRegional gerGerenciaRegional) {
		this.gerGerenciaRegional = gerGerenciaRegional;
	}

	public Set getGerLocalidade() {
		return gerLocalidade;
	}

	public void setGerLocalidade(Set gerLocalidade) {
		this.gerLocalidade = gerLocalidade;
	}

	public Set getUnResumoColetaEsgotos() {
        return this.unResumoColetaEsgotos;
    }

    public void setUnResumoColetaEsgotos(Set unResumoColetaEsgotos) {
        this.unResumoColetaEsgotos = unResumoColetaEsgotos;
    }

    public Set getUnResumoConsumoAguas() {
        return this.unResumoConsumoAguas;
    }

    public void setUnResumoConsumoAguas(Set unResumoConsumoAguas) {
        this.unResumoConsumoAguas = unResumoConsumoAguas;
    }

    public Set getUnResumoFaturamentos() {
        return this.unResumoFaturamentos;
    }

    public void setUnResumoFaturamentos(Set unResumoFaturamentos) {
        this.unResumoFaturamentos = unResumoFaturamentos;
    }

   

    public Set getUnResumoArrecadacao() {
		return unResumoArrecadacao;
	}

	public void setUnResumoArrecadacao(Set unResumoArrecadacao) {
		this.unResumoArrecadacao = unResumoArrecadacao;
	}

	public Set getUnResumoLigacaoEconomias() {
        return this.unResumoLigacaoEconomias;
    }

    public void setUnResumoLigacaoEconomias(Set unResumoLigacaoEconomias) {
        this.unResumoLigacaoEconomias = unResumoLigacaoEconomias;
    }

    public Set getGLocalidade() {
        return this.gerLocalidade;
    }

    public void setGLocalidade(Set gLocalidade) {
        this.gerLocalidade = gLocalidade;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Set getUnResumoIndicadorDesempenhoMicromedicaos() {
		return unResumoIndicadorDesempenhoMicromedicaos;
	}

	public void setUnResumoIndicadorDesempenhoMicromedicaos(
			Set unResumoIndicadorDesempenhoMicromedicaos) {
		this.unResumoIndicadorDesempenhoMicromedicaos = unResumoIndicadorDesempenhoMicromedicaos;
	}

}
