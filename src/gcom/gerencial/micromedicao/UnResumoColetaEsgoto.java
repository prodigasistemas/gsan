package gcom.gerencial.micromedicao;

import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao;
import gcom.gerencial.cadastro.cliente.GClienteTipo;
import gcom.gerencial.cadastro.cliente.GEsferaPoder;
import gcom.gerencial.cadastro.imovel.GCategoria;
import gcom.gerencial.cadastro.imovel.GImovelPerfil;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao;
import gcom.gerencial.cadastro.imovel.GSubcategoria;
import gcom.gerencial.cadastro.localidade.GLocalidade;
import gcom.gerencial.cadastro.localidade.GQuadra;
import gcom.gerencial.cadastro.localidade.GSetorComercial;
import gcom.gerencial.cadastro.localidade.GUnidadeNegocio;
import gcom.gerencial.cadastro.localidade.GGerenciaRegional;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoColetaEsgoto implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer receId;

    /** persistent field */
    private int referencia;

    /** persistent field */
    private int quantidadeEconomias;

    /** persistent field */
    private int volumeEsgoto;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private int quantidadeLigacoes;

    /** persistent field */
    private int volumeExcedente;

    /** persistent field */
    private int codigoSetorComercial;

    /** persistent field */
    private int numeroQuadra;
    
    /** persistent field */
    private int volumeFaturado;
    
    /** persistent field */
    private Short indicadorMedidoAgua;
    
    /** persistent field */
    private Short indicadorPoco;
    
    /** persistent field */
    private Short indicadorMedidoFonteAlternativa;
    
    /** persistent field */
    private Short indicadorVolumeExcedente;
    
    /** persistent field */
    private Short indicadorVolumeMinimoEsgoto;
    
    /** persistent field */
    private BigDecimal percentualColeta;

    /** persistent field */
    private BigDecimal percentualEsgoto;

    
    /** persistent field */
    private GSubcategoria gerSubcategoria;

    /** persistent field */
    private GClienteTipo gerClienteTipo;

    /** persistent field */
    private GLigacaoAguaSituacao gerLigacaoAguaSituacao;

    /** persistent field */
    private GUnidadeNegocio gerUnidadeNegocio;

    /** persistent field */
    private GLocalidade gerLocalidade;

    /** persistent field */
    private GLocalidade gerLocalidadeElo;

    /** persistent field */
    private GQuadra gerQuadra;

    /** persistent field */
    private GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao;

    /** persistent field */
    private GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil;

    /** persistent field */
    private GGerenciaRegional gerGerenciaRegional;

    /** persistent field */
    private GSetorComercial gerSetorComercial;

    /** persistent field */
    private GLigacaoAguaPerfil gerLigacaoAguaPerfil;

    /** persistent field */
    private GEsferaPoder gerEsferaPoder;

    /** persistent field */
    private GCategoria gerCategoria;

    /** persistent field */
    private GImovelPerfil gerImovelPerfil;

    /** persistent field */
    private gcom.gerencial.micromedicao.GRota gerRota;
    
    private Short codigoRota;

    /** full constructor */
    public UnResumoColetaEsgoto(int referencia, int quantidadeEconomias, int volumeEsgoto, Date ultimaAlteracao, int quantidadeLigacoes, int volumeExcedente, int codigoSetorComercial, int numeroQuadra, GSubcategoria gSubcategoria, GClienteTipo gClienteTipo, GLigacaoAguaSituacao gLigacaoAguaSituacao,GUnidadeNegocio gUnidadeNegocio, GLocalidade gLocalidade, GLocalidade gLocalidadeElo, GQuadra gQuadra, GLigacaoEsgotoSituacao gLigacaoEsgotoSituacao, GLigacaoEsgotoPerfil gLigacaoEsgotoPerfil, GGerenciaRegional gGerenciaRegional, GSetorComercial gSetorComercial, GLigacaoAguaPerfil gLigacaoAguaPerfil, GEsferaPoder gEsferaPoder, GCategoria gCategoria, GImovelPerfil gImovelPerfil, gcom.gerencial.micromedicao.GRota gRota) {
        this.referencia = referencia;
        this.quantidadeEconomias = quantidadeEconomias;
        this.volumeEsgoto = volumeEsgoto;
        this.ultimaAlteracao = ultimaAlteracao;
        this.quantidadeLigacoes = quantidadeLigacoes;
        this.volumeExcedente = volumeExcedente;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.gerSubcategoria = gSubcategoria;
        this.gerClienteTipo = gClienteTipo;
        this.gerLigacaoAguaSituacao = gLigacaoAguaSituacao;
        this.gerUnidadeNegocio = gUnidadeNegocio;
        this.gerLocalidade = gLocalidade;
        this.gerLocalidadeElo = gLocalidadeElo;
        this.gerQuadra = gQuadra;
        this.gerLigacaoEsgotoSituacao = gLigacaoEsgotoSituacao;
        this.gerLigacaoEsgotoPerfil = gLigacaoEsgotoPerfil;
        this.gerGerenciaRegional = gGerenciaRegional;
        this.gerSetorComercial = gSetorComercial;
        this.gerLigacaoAguaPerfil = gLigacaoAguaPerfil;
        this.gerEsferaPoder = gEsferaPoder;
        this.gerCategoria = gCategoria;
        this.gerImovelPerfil = gImovelPerfil;
        this.gerRota = gRota;
    }

    /** default constructor */
    public UnResumoColetaEsgoto() {
    }

    
    
    public Short getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}

	public int getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(int codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public GCategoria getGerCategoria() {
		return gerCategoria;
	}

	public void setGerCategoria(GCategoria gerCategoria) {
		this.gerCategoria = gerCategoria;
	}

	public GClienteTipo getGerClienteTipo() {
		return gerClienteTipo;
	}

	public void setGerClienteTipo(GClienteTipo gerClienteTipo) {
		this.gerClienteTipo = gerClienteTipo;
	}

	public GEsferaPoder getGerEsferaPoder() {
		return gerEsferaPoder;
	}

	public void setGerEsferaPoder(GEsferaPoder gerEsferaPoder) {
		this.gerEsferaPoder = gerEsferaPoder;
	}

	public GGerenciaRegional getGerGerenciaRegional() {
		return gerGerenciaRegional;
	}

	public void setGerGerenciaRegional(GGerenciaRegional gerGerenciaRegional) {
		this.gerGerenciaRegional = gerGerenciaRegional;
	}

	public GImovelPerfil getGerImovelPerfil() {
		return gerImovelPerfil;
	}

	public void setGerImovelPerfil(GImovelPerfil gerImovelPerfil) {
		this.gerImovelPerfil = gerImovelPerfil;
	}

	public GLigacaoAguaPerfil getGerLigacaoAguaPerfil() {
		return gerLigacaoAguaPerfil;
	}

	public void setGerLigacaoAguaPerfil(GLigacaoAguaPerfil gerLigacaoAguaPerfil) {
		this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
	}

	public GLigacaoAguaSituacao getGerLigacaoAguaSituacao() {
		return gerLigacaoAguaSituacao;
	}

	public void setGerLigacaoAguaSituacao(
			GLigacaoAguaSituacao gerLigacaoAguaSituacao) {
		this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
	}

	public GLigacaoEsgotoPerfil getGerLigacaoEsgotoPerfil() {
		return gerLigacaoEsgotoPerfil;
	}

	public void setGerLigacaoEsgotoPerfil(
			GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
		this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
	}

	public GLigacaoEsgotoSituacao getGerLigacaoEsgotoSituacao() {
		return gerLigacaoEsgotoSituacao;
	}

	public void setGerLigacaoEsgotoSituacao(
			GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao) {
		this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
	}

	public GLocalidade getGerLocalidade() {
		return gerLocalidade;
	}

	public void setGerLocalidade(GLocalidade gerLocalidade) {
		this.gerLocalidade = gerLocalidade;
	}

	public GLocalidade getGerLocalidadeElo() {
		return gerLocalidadeElo;
	}

	public void setGerLocalidadeElo(GLocalidade gerLocalidadeElo) {
		this.gerLocalidadeElo = gerLocalidadeElo;
	}

	public GQuadra getGerQuadra() {
		return gerQuadra;
	}

	public void setGerQuadra(GQuadra gerQuadra) {
		this.gerQuadra = gerQuadra;
	}

	public gcom.gerencial.micromedicao.GRota getGerRota() {
		return gerRota;
	}

	public void setGerRota(gcom.gerencial.micromedicao.GRota gerRota) {
		this.gerRota = gerRota;
	}

	public GSetorComercial getGerSetorComercial() {
		return gerSetorComercial;
	}

	public void setGerSetorComercial(GSetorComercial gerSetorComercial) {
		this.gerSetorComercial = gerSetorComercial;
	}

	public GSubcategoria getGerSubcategoria() {
		return gerSubcategoria;
	}

	public void setGerSubcategoria(GSubcategoria gerSubcategoria) {
		this.gerSubcategoria = gerSubcategoria;
	}

	public GUnidadeNegocio getGerUnidadeNegocio() {
		return gerUnidadeNegocio;
	}

	public void setGerUnidadeNegocio(GUnidadeNegocio gerUnidadeNegocio) {
		this.gerUnidadeNegocio = gerUnidadeNegocio;
	}

	public int getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(int numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public int getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	public void setQuantidadeEconomias(int quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	public int getQuantidadeLigacoes() {
		return quantidadeLigacoes;
	}

	public void setQuantidadeLigacoes(int quantidadeLigacoes) {
		this.quantidadeLigacoes = quantidadeLigacoes;
	}

	public Integer getReceId() {
		return receId;
	}

	public void setReceId(Integer receId) {
		this.receId = receId;
	}

	public int getReferencia() {
		return referencia;
	}

	public void setReferencia(int referencia) {
		this.referencia = referencia;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public int getVolumeEsgoto() {
		return volumeEsgoto;
	}

	public void setVolumeEsgoto(int volumeEsgoto) {
		this.volumeEsgoto = volumeEsgoto;
	}

	public int getVolumeExcedente() {
		return volumeExcedente;
	}

	public void setVolumeExcedente(int volumeExcedente) {
		this.volumeExcedente = volumeExcedente;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("receId", getReceId())
            .toString();
    }

	public Short getIndicadorMedidoAgua() {
		return indicadorMedidoAgua;
	}

	public void setIndicadorMedidoAgua(Short indicadorMedidoAgua) {
		this.indicadorMedidoAgua = indicadorMedidoAgua;
	}

	public Short getIndicadorMedidoFonteAlternativa() {
		return indicadorMedidoFonteAlternativa;
	}

	public void setIndicadorMedidoFonteAlternativa(
			Short indicadorMedidoFonteAlternativa) {
		this.indicadorMedidoFonteAlternativa = indicadorMedidoFonteAlternativa;
	}

	public Short getIndicadorPoco() {
		return indicadorPoco;
	}

	public void setIndicadorPoco(Short indicadorPoco) {
		this.indicadorPoco = indicadorPoco;
	}

	public Short getIndicadorVolumeExcedente() {
		return indicadorVolumeExcedente;
	}

	public void setIndicadorVolumeExcedente(Short indicadorVolumeExcedente) {
		this.indicadorVolumeExcedente = indicadorVolumeExcedente;
	}

	public Short getIndicadorVolumeMinimoEsgoto() {
		return indicadorVolumeMinimoEsgoto;
	}

	public void setIndicadorVolumeMinimoEsgoto(Short indicadorVolumeMinimoEsgoto) {
		this.indicadorVolumeMinimoEsgoto = indicadorVolumeMinimoEsgoto;
	}

	public BigDecimal getPercentualColeta() {
		return percentualColeta;
	}

	public void setPercentualColeta(BigDecimal percentualColeta) {
		this.percentualColeta = percentualColeta;
	}

	public BigDecimal getPercentualEsgoto() {
		return percentualEsgoto;
	}

	public void setPercentualEsgoto(BigDecimal percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}

	public int getVolumeFaturado() {
		return volumeFaturado;
	}

	public void setVolumeFaturado(int volumeFaturado) {
		this.volumeFaturado = volumeFaturado;
	}

}
