package gcom.gerencial.micromedicao;

import gcom.gerencial.micromedicao.hidrometro.GHidrometroCapacidade;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroClasseMetrologica;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroDiametro;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroLocalArmazenagem;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroMarca;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroMotivoBaixa;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroSituacao;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroTipo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoHidrometro implements Serializable {

	private static final long serialVersionUID = 1L;

	
    /** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferencia;

    /** persistent field */
    private short numeroAnoFabricacao;

    /** nullable persistent field */
    private Short indicadorMacro;

    /** persistent field */
    private int quantidadeHidrometro;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private GHidrometroMarca gerHidrometroMarca;

    /** persistent field */
    private GHidrometroSituacao gerHidrometroSituacao;

    /** persistent field */
    private GHidrometroLocalArmazenagem gerHidrometroLocalArmazenagem;

    /** persistent field */
    private GHidrometroTipo gerHidrometroTipo;

    /** persistent field */
    private GHidrometroDiametro gerHidrometroDiametro;

    /** persistent field */
    private GHidrometroCapacidade gerHidrometroCapacidade;
    
    /** persistent field */
    private GHidrometroMotivoBaixa gerHidrometroMotivoBaixa;
    
    /** persistent field */
    private GHidrometroClasseMetrologica gerHidrometroClasseMetrologica;

    /** full constructor */
    public UnResumoHidrometro(Integer id, int anoMesReferencia, short numeroAnoFabricacao, Short indicadorMacro, int quantidadeHidrometro, Date ultimaAlteracao, GHidrometroMarca gerHidrometroMarca, GHidrometroSituacao gerHidrometroSituacao, GHidrometroLocalArmazenagem gerHidrometroLocalArmazenagem, GHidrometroTipo gerHidrometroTipo, GHidrometroDiametro gerHidrometroDiametro, GHidrometroCapacidade gerHidrometroCapacidade) {
        this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.numeroAnoFabricacao = numeroAnoFabricacao;
        this.indicadorMacro = indicadorMacro;
        this.quantidadeHidrometro = quantidadeHidrometro;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerHidrometroMarca = gerHidrometroMarca;
        this.gerHidrometroSituacao = gerHidrometroSituacao;
        this.gerHidrometroLocalArmazenagem = gerHidrometroLocalArmazenagem;
        this.gerHidrometroTipo = gerHidrometroTipo;
        this.gerHidrometroDiametro = gerHidrometroDiametro;
        this.gerHidrometroCapacidade = gerHidrometroCapacidade;
    }

    /** default constructor */
    public UnResumoHidrometro() {
    }

    /** minimal constructor */
    public UnResumoHidrometro(Integer id, int anoMesReferencia, short numeroAnoFabricacao, int quantidadeHidrometro, Date ultimaAlteracao, GHidrometroMarca gerHidrometroMarca, GHidrometroSituacao gerHidrometroSituacao, GHidrometroLocalArmazenagem gerHidrometroLocalArmazenagem, GHidrometroTipo gerHidrometroTipo, GHidrometroDiametro gerHidrometroDiametro, GHidrometroCapacidade gerHidrometroCapacidade) {
        this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.numeroAnoFabricacao = numeroAnoFabricacao;
        this.quantidadeHidrometro = quantidadeHidrometro;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerHidrometroMarca = gerHidrometroMarca;
        this.gerHidrometroSituacao = gerHidrometroSituacao;
        this.gerHidrometroLocalArmazenagem = gerHidrometroLocalArmazenagem;
        this.gerHidrometroTipo = gerHidrometroTipo;
        this.gerHidrometroDiametro = gerHidrometroDiametro;
        this.gerHidrometroCapacidade = gerHidrometroCapacidade;
    }
    
    /** full constructor */
    public UnResumoHidrometro(int anoMesReferencia, short numeroAnoFabricacao, Short indicadorMacro, Date ultimaAlteracao, GHidrometroMarca gerHidrometroMarca, GHidrometroSituacao gerHidrometroSituacao, GHidrometroLocalArmazenagem gerHidrometroLocalArmazenagem, GHidrometroTipo gerHidrometroTipo, GHidrometroDiametro gerHidrometroDiametro, GHidrometroCapacidade gerHidrometroCapacidade, int quantidadeHidrometro) {

        this.anoMesReferencia = anoMesReferencia;
        this.numeroAnoFabricacao = numeroAnoFabricacao;
        this.indicadorMacro = indicadorMacro;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerHidrometroMarca = gerHidrometroMarca;
        this.gerHidrometroSituacao = gerHidrometroSituacao;
        this.gerHidrometroLocalArmazenagem = gerHidrometroLocalArmazenagem;
        this.gerHidrometroTipo = gerHidrometroTipo;
        this.gerHidrometroDiametro = gerHidrometroDiametro;
        this.gerHidrometroCapacidade = gerHidrometroCapacidade;
        this.quantidadeHidrometro = quantidadeHidrometro;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(int anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public short getNumeroAnoFabricacao() {
        return this.numeroAnoFabricacao;
    }

    public void setNumeroAnoFabricacao(short numeroAnoFabricacao) {
        this.numeroAnoFabricacao = numeroAnoFabricacao;
    }

    public Short getIndicadorMacro() {
        return this.indicadorMacro;
    }

    public void setIndicadorMacro(Short indicadorMacro) {
        this.indicadorMacro = indicadorMacro;
    }

    public int getQuantidadeHidrometro() {
        return this.quantidadeHidrometro;
    }

    public void setQuantidadeHidrometro(int quantidadeHidrometro) {
        this.quantidadeHidrometro = quantidadeHidrometro;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public GHidrometroMarca getGerHidrometroMarca() {
        return this.gerHidrometroMarca;
    }

    public void setGerHidrometroMarca(GHidrometroMarca gerHidrometroMarca) {
        this.gerHidrometroMarca = gerHidrometroMarca;
    }

    public GHidrometroSituacao getGerHidrometroSituacao() {
        return this.gerHidrometroSituacao;
    }

    public void setGerHidrometroSituacao(GHidrometroSituacao gerHidrometroSituacao) {
        this.gerHidrometroSituacao = gerHidrometroSituacao;
    }

    public GHidrometroLocalArmazenagem getGerHidrometroLocalArmazenagem() {
        return this.gerHidrometroLocalArmazenagem;
    }

    public void setGerHidrometroLocalArmazenagem(GHidrometroLocalArmazenagem gerHidrometroLocalArmazenagem) {
        this.gerHidrometroLocalArmazenagem = gerHidrometroLocalArmazenagem;
    }

    public GHidrometroTipo getGerHidrometroTipo() {
        return this.gerHidrometroTipo;
    }

    public void setGerHidrometroTipo(GHidrometroTipo gerHidrometroTipo) {
        this.gerHidrometroTipo = gerHidrometroTipo;
    }

    public GHidrometroDiametro getGerHidrometroDiametro() {
        return this.gerHidrometroDiametro;
    }

    public void setGerHidrometroDiametro(GHidrometroDiametro gerHidrometroDiametro) {
        this.gerHidrometroDiametro = gerHidrometroDiametro;
    }

    public GHidrometroCapacidade getGerHidrometroCapacidade() {
        return this.gerHidrometroCapacidade;
    }

    public void setGerHidrometroCapacidade(GHidrometroCapacidade gerHidrometroCapacidade) {
        this.gerHidrometroCapacidade = gerHidrometroCapacidade;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public GHidrometroClasseMetrologica getGerHidrometroClasseMetrologica() {
		return gerHidrometroClasseMetrologica;
	}

	public void setGerHidrometroClasseMetrologica(
			GHidrometroClasseMetrologica gerHidrometroClasseMetrologica) {
		this.gerHidrometroClasseMetrologica = gerHidrometroClasseMetrologica;
	}

	public GHidrometroMotivoBaixa getGerHidrometroMotivoBaixa() {
		return gerHidrometroMotivoBaixa;
	}

	public void setGerHidrometroMotivoBaixa(
			GHidrometroMotivoBaixa gerHidrometroMotivoBaixa) {
		this.gerHidrometroMotivoBaixa = gerHidrometroMotivoBaixa;
	}

}
