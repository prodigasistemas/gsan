package gcom.gerencial.cadastro.localidade;

import gcom.gerencial.micromedicao.GRota;
import gcom.gerencial.operacional.GDistritoOperacional;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GQuadra implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private int numeroQuadra;

    /** nullable persistent field */
    private Short indicadorRedeAgua;

    /** nullable persistent field */
    private Short indicadorRedeEsgoto;

    /** nullable persistent field */
    private Short numeroRotaSequencia;

    /** nullable persistent field */
    private Date dataImplantacao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.gerencial.cadastro.localidade.GSetorComercial gerSetorComercial;

    /** persistent field */
    private GRota gerRota;

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
    private Set unResumoIndicadorDesempenhoMicromedicaos;
    private GDistritoOperacional gerDistritoOperacional;

    public GDistritoOperacional getGerDistritoOperacional() {
		return gerDistritoOperacional;
	}



	public void setGerDistritoOperacional(
			GDistritoOperacional gerDistritoOperacional) {
		this.gerDistritoOperacional = gerDistritoOperacional;
	}



	/** full constructor */
    public GQuadra(int numeroQuadra, Short indicadorRedeAgua, Short indicadorRedeEsgoto, Short numeroRotaSequencia, Date dataImplantacao, Short indicadorUso, Date ultimaAlteracao, gcom.gerencial.cadastro.localidade.GSetorComercial gSetorComercial, GRota gRota, Set unResumoColetaEsgotos, Set unResumoConsumoAguas, Set unResumoFaturamentos, Set unResumoArrecadacao, Set unResumoLigacaoEconomias) {
        this.numeroQuadra = numeroQuadra;
        this.indicadorRedeAgua = indicadorRedeAgua;
        this.indicadorRedeEsgoto = indicadorRedeEsgoto;
        this.numeroRotaSequencia = numeroRotaSequencia;
        this.dataImplantacao = dataImplantacao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerSetorComercial = gSetorComercial;
        this.gerRota = gRota;
        this.unResumoColetaEsgotos = unResumoColetaEsgotos;
        this.unResumoConsumoAguas = unResumoConsumoAguas;
        this.unResumoFaturamentos = unResumoFaturamentos;
        this.unResumoArrecadacao = unResumoArrecadacao;
        this.unResumoLigacaoEconomias = unResumoLigacaoEconomias;
    }

    /** default constructor */
    public GQuadra() {
    }

    /** minimal constructor */
    public GQuadra(int numeroQuadra, gcom.gerencial.cadastro.localidade.GSetorComercial gSetorComercial, GRota gRota, Set unResumoColetaEsgotos, Set unResumoConsumoAguas, Set unResumoFaturamentos, Set unResumoArrecadacao, Set unResumoLigacaoEconomias) {
        this.numeroQuadra = numeroQuadra;
        this.gerSetorComercial = gSetorComercial;
        this.gerRota = gRota;
        this.unResumoColetaEsgotos = unResumoColetaEsgotos;
        this.unResumoConsumoAguas = unResumoConsumoAguas;
        this.unResumoFaturamentos = unResumoFaturamentos;
        this.unResumoArrecadacao = unResumoArrecadacao;
        this.unResumoLigacaoEconomias = unResumoLigacaoEconomias;
    }

  
    public Date getDataImplantacao() {
		return dataImplantacao;
	}

	public void setDataImplantacao(Date dataImplantacao) {
		this.dataImplantacao = dataImplantacao;
	}

	public GRota getGerRota() {
		return gerRota;
	}

	public void setGerRota(GRota gerRota) {
		this.gerRota = gerRota;
	}

	public gcom.gerencial.cadastro.localidade.GSetorComercial getGerSetorComercial() {
		return gerSetorComercial;
	}

	public void setGerSetorComercial(
			gcom.gerencial.cadastro.localidade.GSetorComercial gerSetorComercial) {
		this.gerSetorComercial = gerSetorComercial;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorRedeAgua() {
		return indicadorRedeAgua;
	}

	public void setIndicadorRedeAgua(Short indicadorRedeAgua) {
		this.indicadorRedeAgua = indicadorRedeAgua;
	}

	public Short getIndicadorRedeEsgoto() {
		return indicadorRedeEsgoto;
	}

	public void setIndicadorRedeEsgoto(Short indicadorRedeEsgoto) {
		this.indicadorRedeEsgoto = indicadorRedeEsgoto;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public int getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(int numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Short getNumeroRotaSequencia() {
		return numeroRotaSequencia;
	}

	public void setNumeroRotaSequencia(Short numeroRotaSequencia) {
		this.numeroRotaSequencia = numeroRotaSequencia;
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
