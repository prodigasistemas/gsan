package gcom.gerencial.cadastro.localidade;

import gcom.gerencial.cadastro.geografico.GMunicipio;
import gcom.gerencial.cadastro.localidade.GUnidadeNegocio;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GLocalidade implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private String nomelocalidade;

    /** persistent field */
    private Date ultimaalteracao;

    /** persistent field */
    private GUnidadeNegocio gerUnidadeNegocio;
    private GMunicipio gerMunicipio;
    /** persistent field */
    private gcom.gerencial.cadastro.localidade.GLocalidade gerLocalidade;

    /** persistent field */
    private gcom.gerencial.cadastro.localidade.GGerenciaRegional gerGerenciaRegional;

    /** persistent field */
    private Set unResumoColetaEsgotosByLocaId;

    /** persistent field */
    private Set unResumoColetaEsgotosElo;

    /** persistent field */
    private Set unResumoConsumoAguasElo;

    /** persistent field */
    private Set unResumoConsumoAguaslocalidade;

    /** persistent field */
    private Set unResumoFaturamentosLocalidade;

    /** persistent field */
    private Set unResumoFaturamentosElo;

    /** persistent field */
    private Set unResumoArrecadacaoByLocaId;

    /** persistent field */
    private Set unResumoArrecadacaoByLocaCdelo;

    /** persistent field */
    private Set unResumoLigacaoEconomiasByLocaId;

    /** persistent field */
    private Set unResumoLigacaoEconomiasByLocaCdelo;

    /** persistent field */
    private Set gerSetorComercials;

    /** persistent field */
    private Set gerLocalidadeElo;
    
    private String codigoCentroCusto;
    
    /** persistent field */
    private Set unResumoIndicadorDesempenhoMicromedicaosByLocaId;
	/** nullable persistent field */
	private Short indicadorUso;
	
    public String getCodigoCentroCusto() {		return codigoCentroCusto;	}

	public void setCodigoCentroCusto(String codigoCentroCusto) {
		this.codigoCentroCusto = codigoCentroCusto;
	}

	/** full constructor */
    public GLocalidade(Integer id, String nomelocalidade, Date ultimaalteracao, GUnidadeNegocio gUnidadeNegocio, gcom.gerencial.cadastro.localidade.GLocalidade gLocalidade, gcom.gerencial.cadastro.localidade.GGerenciaRegional gGerenciaRegional, Set unResumoColetaEsgotosByLocaId, Set unResumoColetaEsgotosElo, Set unResumoConsumoAguasElo, Set unResumoConsumoAguaslocalidade, Set unResumoFaturamentosLocalidade, Set unResumoFaturamentosElo, Set unResumoArrecadacaoByLocaId, Set unResumoArrecadacaoByLocaCdelo, Set unResumoLigacaoEconomiasByLocaId, Set unResumoLigacaoEconomiasByLocaCdelo, Set gSetorComercials, Set gLocalidadeElo) {
        this.id = id;
        this.nomelocalidade = nomelocalidade;
        this.ultimaalteracao = ultimaalteracao;
        this.gerUnidadeNegocio = gUnidadeNegocio;
        this.gerLocalidade = gLocalidade;
        this.gerGerenciaRegional = gGerenciaRegional;
        this.unResumoColetaEsgotosByLocaId = unResumoColetaEsgotosByLocaId;
        this.unResumoColetaEsgotosElo = unResumoColetaEsgotosElo;
        this.unResumoConsumoAguasElo = unResumoConsumoAguasElo;
        this.unResumoConsumoAguaslocalidade = unResumoConsumoAguaslocalidade;
        this.unResumoFaturamentosLocalidade = unResumoFaturamentosLocalidade;
        this.unResumoFaturamentosElo = unResumoFaturamentosElo;
        this.unResumoArrecadacaoByLocaId = unResumoArrecadacaoByLocaId;
        this.unResumoArrecadacaoByLocaCdelo = unResumoArrecadacaoByLocaCdelo;
        this.unResumoLigacaoEconomiasByLocaId = unResumoLigacaoEconomiasByLocaId;
        this.unResumoLigacaoEconomiasByLocaCdelo = unResumoLigacaoEconomiasByLocaCdelo;
        this.gerSetorComercials = gSetorComercials;
        this.gerLocalidadeElo = gLocalidadeElo;
    }

    /** default constructor */
    public GLocalidade() {
    }

 
    public gcom.gerencial.cadastro.localidade.GGerenciaRegional getGerGerenciaRegional() {
		return gerGerenciaRegional;
	}

	public void setGerGerenciaRegional(
			gcom.gerencial.cadastro.localidade.GGerenciaRegional gerGerenciaRegional) {
		this.gerGerenciaRegional = gerGerenciaRegional;
	}

	public gcom.gerencial.cadastro.localidade.GLocalidade getGerLocalidade() {
		return gerLocalidade;
	}

	public void setGerLocalidade(gcom.gerencial.cadastro.localidade.GLocalidade gerLocalidade) {
		this.gerLocalidade = gerLocalidade;
	}

	public Set getGerLocalidadeElo() {
		return gerLocalidadeElo;
	}

	public void setGerLocalidadeElo(Set gerLocalidadeElo) {
		this.gerLocalidadeElo = gerLocalidadeElo;
	}

	public Set getGerSetorComercials() {
		return gerSetorComercials;
	}

	public void setGerSetorComercials(Set gerSetorComercials) {
		this.gerSetorComercials = gerSetorComercials;
	}

	public GUnidadeNegocio getGerUnidadeNegocio() {
		return gerUnidadeNegocio;
	}

	public void setGerUnidadeNegocio(GUnidadeNegocio gerUnidadeNegocio) {
		this.gerUnidadeNegocio = gerUnidadeNegocio;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomelocalidade() {
		return nomelocalidade;
	}

	public void setNomelocalidade(String nomelocalidade) {
		this.nomelocalidade = nomelocalidade;
	}

	public Date getUltimaalteracao() {
		return ultimaalteracao;
	}

	public void setUltimaalteracao(Date ultimaalteracao) {
		this.ultimaalteracao = ultimaalteracao;
	}

	

	public Set getUnResumoArrecadacaoByLocaCdelo() {
		return unResumoArrecadacaoByLocaCdelo;
	}

	public void setUnResumoArrecadacaoByLocaCdelo(Set unResumoArrecadacaoByLocaCdelo) {
		this.unResumoArrecadacaoByLocaCdelo = unResumoArrecadacaoByLocaCdelo;
	}

	public Set getUnResumoArrecadacaoByLocaId() {
		return unResumoArrecadacaoByLocaId;
	}

	public void setUnResumoArrecadacaoByLocaId(Set unResumoArrecadacaoByLocaId) {
		this.unResumoArrecadacaoByLocaId = unResumoArrecadacaoByLocaId;
	}

	public Set getUnResumoColetaEsgotosByLocaId() {
		return unResumoColetaEsgotosByLocaId;
	}

	public void setUnResumoColetaEsgotosByLocaId(Set unResumoColetaEsgotosByLocaId) {
		this.unResumoColetaEsgotosByLocaId = unResumoColetaEsgotosByLocaId;
	}

	public Set getUnResumoColetaEsgotosElo() {
		return unResumoColetaEsgotosElo;
	}

	public void setUnResumoColetaEsgotosElo(Set unResumoColetaEsgotosElo) {
		this.unResumoColetaEsgotosElo = unResumoColetaEsgotosElo;
	}

	public Set getUnResumoConsumoAguasElo() {
		return unResumoConsumoAguasElo;
	}

	public void setUnResumoConsumoAguasElo(Set unResumoConsumoAguasElo) {
		this.unResumoConsumoAguasElo = unResumoConsumoAguasElo;
	}

	public Set getUnResumoConsumoAguaslocalidade() {
		return unResumoConsumoAguaslocalidade;
	}

	public void setUnResumoConsumoAguaslocalidade(Set unResumoConsumoAguaslocalidade) {
		this.unResumoConsumoAguaslocalidade = unResumoConsumoAguaslocalidade;
	}

	public Set getUnResumoFaturamentosElo() {
		return unResumoFaturamentosElo;
	}

	public void setUnResumoFaturamentosElo(
			Set unResumoFaturamentosElo) {
		this.unResumoFaturamentosElo = unResumoFaturamentosElo;
	}

	public Set getUnResumoFaturamentosLocalidade() {
		return unResumoFaturamentosLocalidade;
	}

	public void setUnResumoFaturamentosLocalidade(
			Set unResumoFaturamentosLocalidade) {
		this.unResumoFaturamentosLocalidade = unResumoFaturamentosLocalidade;
	}

	public Set getUnResumoLigacaoEconomiasByLocaCdelo() {
		return unResumoLigacaoEconomiasByLocaCdelo;
	}

	public void setUnResumoLigacaoEconomiasByLocaCdelo(
			Set unResumoLigacaoEconomiasByLocaCdelo) {
		this.unResumoLigacaoEconomiasByLocaCdelo = unResumoLigacaoEconomiasByLocaCdelo;
	}

	public Set getUnResumoLigacaoEconomiasByLocaId() {
		return unResumoLigacaoEconomiasByLocaId;
	}

	public GMunicipio getGerMunicipio() {
		return gerMunicipio;
	}



	public void setGerMunicipio(GMunicipio gerMunicipio) {
		this.gerMunicipio = gerMunicipio;
	}



	public void setUnResumoLigacaoEconomiasByLocaId(
			Set unResumoLigacaoEconomiasByLocaId) {
		this.unResumoLigacaoEconomiasByLocaId = unResumoLigacaoEconomiasByLocaId;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Set getUnResumoIndicadorDesempenhoMicromedicaosByLocaId() {
		return unResumoIndicadorDesempenhoMicromedicaosByLocaId;
	}

	public void setUnResumoIndicadorDesempenhoMicromedicaosByLocaId(
			Set unResumoIndicadorDesempenhoMicromedicaosByLocaId) {
		this.unResumoIndicadorDesempenhoMicromedicaosByLocaId = unResumoIndicadorDesempenhoMicromedicaosByLocaId;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}}
