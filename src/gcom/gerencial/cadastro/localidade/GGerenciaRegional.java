package gcom.gerencial.cadastro.localidade;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GGerenciaRegional implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private String nome;

    /** persistent field */
    private String nomeAbreviado;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Set unResumoColetaEsgotos;

    /** persistent field */
    private Set gerUnidadeNegocios;

    /** persistent field */
    private Set unResumoConsumoAguas;

    /** persistent field */
    private Set unResumoFaturamentos;

    /** persistent field */
    private Set unResumoArrecadacao;

    /** persistent field */
    private Set unResumoLigacaoEconomias;

    /** persistent field */
    private Set gerLocalidades;
    
    /** persistent field */
    private Set unResumoIndicadorDesempenhoMicromedicaos;

    /** full constructor */
    public GGerenciaRegional(String nome, String nomeAbreviado, Date ultimaAlteracao, Set unResumoColetaEsgotos, Set gUnidadeNegocios, Set unResumoConsumoAguas, Set unResumoFaturamentos, Set unResumoArrecadacao, Set unResumoLigacaoEconomias, Set gLocalidades) {
        this.nome = nome;
        this.nomeAbreviado = nomeAbreviado;
        this.ultimaAlteracao = ultimaAlteracao;
        this.unResumoColetaEsgotos = unResumoColetaEsgotos;
        this.gerUnidadeNegocios = gUnidadeNegocios;
        this.unResumoConsumoAguas = unResumoConsumoAguas;
        this.unResumoFaturamentos = unResumoFaturamentos;
        this.unResumoArrecadacao = unResumoArrecadacao;
        this.unResumoLigacaoEconomias = unResumoLigacaoEconomias;
        this.gerLocalidades = gLocalidades;
    }

    /** default constructor */
    public GGerenciaRegional() {
    }

 
    public Set getGerLocalidades() {
		return gerLocalidades;
	}

	public void setGerLocalidades(Set gerLocalidades) {
		this.gerLocalidades = gerLocalidades;
	}

	public Set getGerUnidadeNegocios() {
		return gerUnidadeNegocios;
	}

	public void setGerUnidadeNegocios(Set gerUnidadeNegocios) {
		this.gerUnidadeNegocios = gerUnidadeNegocios;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeAbreviado() {
		return nomeAbreviado;
	}

	public void setNomeAbreviado(String nomeAbreviado) {
		this.nomeAbreviado = nomeAbreviado;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	
	
	public Set getUnResumoArrecadacao() {
		return unResumoArrecadacao;
	}

	public void setUnResumoArrecadacao(Set unResumoArrecadacao) {
		this.unResumoArrecadacao = unResumoArrecadacao;
	}

	public Set getUnResumoColetaEsgotos() {
		return unResumoColetaEsgotos;
	}

	public void setUnResumoColetaEsgotos(Set unResumoColetaEsgotos) {
		this.unResumoColetaEsgotos = unResumoColetaEsgotos;
	}

	public Set getUnResumoConsumoAguas() {
		return unResumoConsumoAguas;
	}

	public void setUnResumoConsumoAguas(Set unResumoConsumoAguas) {
		this.unResumoConsumoAguas = unResumoConsumoAguas;
	}

	public Set getUnResumoFaturamentos() {
		return unResumoFaturamentos;
	}

	public void setUnResumoFaturamentos(Set unResumoFaturamentos) {
		this.unResumoFaturamentos = unResumoFaturamentos;
	}

	public Set getUnResumoLigacaoEconomias() {
		return unResumoLigacaoEconomias;
	}

	public void setUnResumoLigacaoEconomias(Set unResumoLigacaoEconomias) {
		this.unResumoLigacaoEconomias = unResumoLigacaoEconomias;
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
