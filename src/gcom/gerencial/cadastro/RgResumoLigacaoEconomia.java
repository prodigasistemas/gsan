package gcom.gerencial.cadastro;

import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao;
import gcom.gerencial.cadastro.cliente.GClienteTipo;
import gcom.gerencial.cadastro.cliente.GEsferaPoder;
import gcom.gerencial.cadastro.geografico.GBairro;
import gcom.gerencial.cadastro.geografico.GMicrorregiao;
import gcom.gerencial.cadastro.geografico.GMunicipio;
import gcom.gerencial.cadastro.geografico.GRegiao;
import gcom.gerencial.cadastro.imovel.GCategoria;
import gcom.gerencial.cadastro.imovel.GImovelPerfil;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao;
import gcom.gerencial.cadastro.imovel.GSubcategoria;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class RgResumoLigacaoEconomia implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private int referencia;

    /** persistent field */
    private short indicadorHidrometro;

    /** persistent field */
    private short volumeFixadoAgua;

    /** persistent field */
    private short indicadorVolumeFixadoEsgoto;

    /** persistent field */
    private short indicadorPoco;

    /** persistent field */
    private int quantidadeLigacoes;

    /** persistent field */
    private int quantidadeEconomias;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private GMunicipio gerMunicipio;

    /** persistent field */
    private GSubcategoria gerSubcategoria;

    /** persistent field */
    private GClienteTipo gerClienteTipo;

    /** persistent field */
    private GLigacaoAguaSituacao gerLigacaoAguaSituacao;

    /** persistent field */
    private GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao;

    /** persistent field */
    private GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil;

    /** persistent field */
    private GRegiao gerRegiao;

    /** persistent field */
    private GBairro gerBairro;

    /** persistent field */
    private GLigacaoAguaPerfil gerLigacaoAguaPerfil;

    /** persistent field */
    private GEsferaPoder gerEsferaPoder;

    /** persistent field */
    private GMicrorregiao gerMicrorregiao;

    /** persistent field */
    private GCategoria gerCategoria;

    /** persistent field */
    private GImovelPerfil gerImovelPerfil;

    /** full constructor */
    public RgResumoLigacaoEconomia(int referencia, short indicadorHidrometro, short volumeFixadoAgua, short indicadorVolumeFixadoEsgoto, short indicadorPoco, int quantidadeLigacoes, int quantidadeEconomias, Date ultimaAlteracao, GMunicipio gMunicipio, GSubcategoria gSubcategoria, GClienteTipo gClienteTipo, GLigacaoAguaSituacao gLigacaoAguaSituacao, GLigacaoEsgotoSituacao gLigacaoEsgotoSituacao, GLigacaoEsgotoPerfil gLigacaoEsgotoPerfil, GRegiao gRegiao, GBairro gBairro, GLigacaoAguaPerfil gLigacaoAguaPerfil, GEsferaPoder gEsferaPoder, GMicrorregiao gMicrorregiao, GCategoria gCategoria, GImovelPerfil gImovelPerfil) {
        this.referencia = referencia;
        this.indicadorHidrometro = indicadorHidrometro;
        this.volumeFixadoAgua = volumeFixadoAgua;
        this.indicadorVolumeFixadoEsgoto = indicadorVolumeFixadoEsgoto;
        this.indicadorPoco = indicadorPoco;
        this.quantidadeLigacoes = quantidadeLigacoes;
        this.quantidadeEconomias = quantidadeEconomias;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerMunicipio = gMunicipio;
        this.gerSubcategoria = gSubcategoria;
        this.gerClienteTipo = gClienteTipo;
        this.gerLigacaoAguaSituacao = gLigacaoAguaSituacao;
        this.gerLigacaoEsgotoSituacao = gLigacaoEsgotoSituacao;
        this.gerLigacaoEsgotoPerfil = gLigacaoEsgotoPerfil;
        this.gerRegiao = gRegiao;
        this.gerBairro = gBairro;
        this.gerLigacaoAguaPerfil = gLigacaoAguaPerfil;
        this.gerEsferaPoder = gEsferaPoder;
        this.gerMicrorregiao = gMicrorregiao;
        this.gerCategoria = gCategoria;
        this.gerImovelPerfil = gImovelPerfil;
    }

    /** default constructor */
    public RgResumoLigacaoEconomia() {
    }

    
  
    public GBairro getGerBairro() {
		return gerBairro;
	}

	public void setGerBairro(GBairro gerBairro) {
		this.gerBairro = gerBairro;
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

	public GMicrorregiao getGerMicrorregiao() {
		return gerMicrorregiao;
	}

	public void setGerMicrorregiao(GMicrorregiao gerMicrorregiao) {
		this.gerMicrorregiao = gerMicrorregiao;
	}

	public GMunicipio getGerMunicipio() {
		return gerMunicipio;
	}

	public void setGerMunicipio(GMunicipio gerMunicipio) {
		this.gerMunicipio = gerMunicipio;
	}

	public GRegiao getGerRegiao() {
		return gerRegiao;
	}

	public void setGerRegiao(GRegiao gerRegiao) {
		this.gerRegiao = gerRegiao;
	}

	public GSubcategoria getGerSubcategoria() {
		return gerSubcategoria;
	}

	public void setGerSubcategoria(GSubcategoria gerSubcategoria) {
		this.gerSubcategoria = gerSubcategoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public short getIndicadorHidrometro() {
		return indicadorHidrometro;
	}

	public void setIndicadorHidrometro(short indicadorHidrometro) {
		this.indicadorHidrometro = indicadorHidrometro;
	}

	public short getIndicadorPoco() {
		return indicadorPoco;
	}

	public void setIndicadorPoco(short indicadorPoco) {
		this.indicadorPoco = indicadorPoco;
	}

	public short getIndicadorVolumeFixadoEsgoto() {
		return indicadorVolumeFixadoEsgoto;
	}

	public void setIndicadorVolumeFixadoEsgoto(short indicadorVolumeFixadoEsgoto) {
		this.indicadorVolumeFixadoEsgoto = indicadorVolumeFixadoEsgoto;
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

	public short getVolumeFixadoAgua() {
		return volumeFixadoAgua;
	}

	public void setVolumeFixadoAgua(short volumeFixadoAgua) {
		this.volumeFixadoAgua = volumeFixadoAgua;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
