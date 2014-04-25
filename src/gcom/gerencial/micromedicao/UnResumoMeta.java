package gcom.gerencial.micromedicao;

import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao;
import gcom.gerencial.cadastro.cliente.GClienteTipo;
import gcom.gerencial.cadastro.cliente.GEsferaPoder;
import gcom.gerencial.cadastro.imovel.GCategoria;
import gcom.gerencial.cadastro.imovel.GImovelPerfil;
import gcom.gerencial.cadastro.localidade.GQuadra;
import gcom.gerencial.cadastro.imovel.GSubcategoria;
import gcom.gerencial.cadastro.localidade.GGerenciaRegional;
import gcom.gerencial.cadastro.localidade.GLocalidade;
import gcom.gerencial.cadastro.localidade.GSetorComercial;
import gcom.gerencial.cadastro.localidade.GUnidadeNegocio;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoMeta implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferencia;

    /** persistent field */
    private int codigoSetorComercial;

    /** persistent field */
    private int numeroQuadra;

    /** persistent field */
    private int quantidadeLigacoesCadastradas;

    /** persistent field */
    private int quantidadeLigacoesCortadas;

    /** persistent field */
    private int quantidadeLigacoesSuprimidas;

    /** persistent field */
    private int quantidadeLigacoesAtivas;

    /** persistent field */
    private int quantidadeLigacoesAtivasDebito3m;

    /** persistent field */
    private int quantidadeLigacoesConsumoMedido;

    /** persistent field */
    private int quantidadeLigacoesConsumoNaooMedido;

    /** persistent field */
    private int quantidadeLigacoesConsumoAte5m3;

    /** persistent field */
    private int quantidadeLigacoesConsumoMedia;

    /** persistent field */
    private int quantidadeEconomias;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private GGerenciaRegional gerGerenciaRegional;

    /** persistent field */
    private GSetorComercial gerSetorComercial;

    /** persistent field */
    private GSubcategoria gerSubcategoria;

    /** persistent field */
    private GLigacaoAguaPerfil gerLigacaoAguaPerfil;

    /** persistent field */
    private GEsferaPoder gerEsferaPoder;

    /** persistent field */
    private GClienteTipo gerClienteTipo;

    /** persistent field */
    private GLigacaoAguaSituacao gerLigacaoAguaSituacao;

    /** persistent field */
    private GUnidadeNegocio gerUnidadeNegocio;

    /** persistent field */
    private GLocalidade gerLocalidade;

    /** persistent field */
    private GLocalidade GLocalidadeElo;

    /** persistent field */
    private GCategoria gerCategoria;

    /** persistent field */
    private GImovelPerfil gerImovelPerfil;

    /** persistent field */
    private gcom.gerencial.micromedicao.GRota gerRota;

    /** persistent field */
    private GQuadra gerQuadra;

    /** persistent field */
    private GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao;

    /** persistent field */
    private GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil;
    
    /** persistent field */
    private Integer codigoGrupoSubcategoria;

    /** full constructor */
    public UnResumoMeta(Integer id, int anoMesReferencia, int codigoSetorComercial, int numeroQuadra, int quantidadeLigacoesCadastradas, int quantidadeLigacoesCortadas, int quantidadeLigacoesSuprimidas, int quantidadeLigacoesAtivas, int quantidadeLigacoesAtivasDebito3m, int quantidadeLigacoesConsumoMedido, int quantidadeLigacoesConsumoNaooMedido, int quantidadeLigacoesConsumoAte5m3, int quantidadeLigacoesConsumoMedia, int quantidadeEconomias, Date ultimaAlteracao, GGerenciaRegional gerGerenciaRegional, GSetorComercial gerSetorComercial, GSubcategoria gerSubcategoria, GLigacaoAguaPerfil gerLigacaoAguaPerfil, GEsferaPoder gerEsferaPoder, GClienteTipo gerClienteTipo, GLigacaoAguaSituacao gerLigacaoAguaSituacao, GUnidadeNegocio gerUnidadeNegocio, GLocalidade gerLocalidade, GLocalidade GLocalidadeElo, GCategoria gerCategoria, GImovelPerfil gerImovelPerfil, gcom.gerencial.micromedicao.GRota gerRota, GQuadra gerQuadra, GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao, GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
        this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.quantidadeLigacoesCadastradas = quantidadeLigacoesCadastradas;
        this.quantidadeLigacoesCortadas = quantidadeLigacoesCortadas;
        this.quantidadeLigacoesSuprimidas = quantidadeLigacoesSuprimidas;
        this.quantidadeLigacoesAtivas = quantidadeLigacoesAtivas;
        this.quantidadeLigacoesAtivasDebito3m = quantidadeLigacoesAtivasDebito3m;
        this.quantidadeLigacoesConsumoMedido = quantidadeLigacoesConsumoMedido;
        this.quantidadeLigacoesConsumoNaooMedido = quantidadeLigacoesConsumoNaooMedido;
        this.quantidadeLigacoesConsumoAte5m3 = quantidadeLigacoesConsumoAte5m3;
        this.quantidadeLigacoesConsumoMedia = quantidadeLigacoesConsumoMedia;
        this.quantidadeEconomias = quantidadeEconomias;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerGerenciaRegional = gerGerenciaRegional;
        this.gerSetorComercial = gerSetorComercial;
        this.gerSubcategoria = gerSubcategoria;
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
        this.gerEsferaPoder = gerEsferaPoder;
        this.gerClienteTipo = gerClienteTipo;
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
        this.gerUnidadeNegocio = gerUnidadeNegocio;
        this.gerLocalidade = gerLocalidade;
        this.GLocalidadeElo = GLocalidadeElo;
        this.gerCategoria = gerCategoria;
        this.gerImovelPerfil = gerImovelPerfil;
        this.gerRota = gerRota;
        this.gerQuadra = gerQuadra;
        this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
    }

    /** default constructor */
    public UnResumoMeta() {
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

    public int getCodigoSetorComercial() {
        return this.codigoSetorComercial;
    }

    public void setCodigoSetorComercial(int codigoSetorComercial) {
        this.codigoSetorComercial = codigoSetorComercial;
    }

    public int getNumeroQuadra() {
        return this.numeroQuadra;
    }

    public void setNumeroQuadra(int numeroQuadra) {
        this.numeroQuadra = numeroQuadra;
    }

    public int getQuantidadeLigacoesCadastradas() {
        return this.quantidadeLigacoesCadastradas;
    }

    public void setQuantidadeLigacoesCadastradas(int quantidadeLigacoesCadastradas) {
        this.quantidadeLigacoesCadastradas = quantidadeLigacoesCadastradas;
    }

    public int getQuantidadeLigacoesCortadas() {
        return this.quantidadeLigacoesCortadas;
    }

    public void setQuantidadeLigacoesCortadas(int quantidadeLigacoesCortadas) {
        this.quantidadeLigacoesCortadas = quantidadeLigacoesCortadas;
    }

    public int getQuantidadeLigacoesSuprimidas() {
        return this.quantidadeLigacoesSuprimidas;
    }

    public void setQuantidadeLigacoesSuprimidas(int quantidadeLigacoesSuprimidas) {
        this.quantidadeLigacoesSuprimidas = quantidadeLigacoesSuprimidas;
    }

    public int getQuantidadeLigacoesAtivas() {
        return this.quantidadeLigacoesAtivas;
    }

    public void setQuantidadeLigacoesAtivas(int quantidadeLigacoesAtivas) {
        this.quantidadeLigacoesAtivas = quantidadeLigacoesAtivas;
    }

    public int getQuantidadeLigacoesAtivasDebito3m() {
        return this.quantidadeLigacoesAtivasDebito3m;
    }

    public void setQuantidadeLigacoesAtivasDebito3m(int quantidadeLigacoesAtivasDebito3m) {
        this.quantidadeLigacoesAtivasDebito3m = quantidadeLigacoesAtivasDebito3m;
    }

    public int getQuantidadeLigacoesConsumoMedido() {
        return this.quantidadeLigacoesConsumoMedido;
    }

    public void setQuantidadeLigacoesConsumoMedido(int quantidadeLigacoesConsumoMedido) {
        this.quantidadeLigacoesConsumoMedido = quantidadeLigacoesConsumoMedido;
    }

    public int getQuantidadeLigacoesConsumoNaooMedido() {
        return this.quantidadeLigacoesConsumoNaooMedido;
    }

    public void setQuantidadeLigacoesConsumoNaooMedido(int quantidadeLigacoesConsumoNaooMedido) {
        this.quantidadeLigacoesConsumoNaooMedido = quantidadeLigacoesConsumoNaooMedido;
    }

    public int getQuantidadeLigacoesConsumoAte5m3() {
        return this.quantidadeLigacoesConsumoAte5m3;
    }

    public void setQuantidadeLigacoesConsumoAte5m3(int quantidadeLigacoesConsumoAte5m3) {
        this.quantidadeLigacoesConsumoAte5m3 = quantidadeLigacoesConsumoAte5m3;
    }

    public int getQuantidadeLigacoesConsumoMedia() {
        return this.quantidadeLigacoesConsumoMedia;
    }

    public void setQuantidadeLigacoesConsumoMedia(int quantidadeLigacoesConsumoMedia) {
        this.quantidadeLigacoesConsumoMedia = quantidadeLigacoesConsumoMedia;
    }

    public int getQuantidadeEconomias() {
        return this.quantidadeEconomias;
    }

    public void setQuantidadeEconomias(int quantidadeEconomias) {
        this.quantidadeEconomias = quantidadeEconomias;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public GGerenciaRegional getGerGerenciaRegional() {
        return this.gerGerenciaRegional;
    }

    public void setGerGerenciaRegional(GGerenciaRegional gerGerenciaRegional) {
        this.gerGerenciaRegional = gerGerenciaRegional;
    }

    public GSetorComercial getGerSetorComercial() {
        return this.gerSetorComercial;
    }

    public void setGerSetorComercial(GSetorComercial gerSetorComercial) {
        this.gerSetorComercial = gerSetorComercial;
    }

    public GSubcategoria getGerSubcategoria() {
        return this.gerSubcategoria;
    }

    public void setGerSubcategoria(GSubcategoria gerSubcategoria) {
        this.gerSubcategoria = gerSubcategoria;
    }

    public GLigacaoAguaPerfil getGerLigacaoAguaPerfil() {
        return this.gerLigacaoAguaPerfil;
    }

    public void setGerLigacaoAguaPerfil(GLigacaoAguaPerfil gerLigacaoAguaPerfil) {
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
    }

    public GEsferaPoder getGerEsferaPoder() {
        return this.gerEsferaPoder;
    }

    public void setGerEsferaPoder(GEsferaPoder gerEsferaPoder) {
        this.gerEsferaPoder = gerEsferaPoder;
    }

    public GClienteTipo getGerClienteTipo() {
        return this.gerClienteTipo;
    }

    public void setGerClienteTipo(GClienteTipo gerClienteTipo) {
        this.gerClienteTipo = gerClienteTipo;
    }

    public GLigacaoAguaSituacao getGerLigacaoAguaSituacao() {
        return this.gerLigacaoAguaSituacao;
    }

    public void setGerLigacaoAguaSituacao(GLigacaoAguaSituacao gerLigacaoAguaSituacao) {
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
    }

    public GUnidadeNegocio getGerUnidadeNegocio() {
        return this.gerUnidadeNegocio;
    }

    public void setGerUnidadeNegocio(GUnidadeNegocio gerUnidadeNegocio) {
        this.gerUnidadeNegocio = gerUnidadeNegocio;
    }

    public GLocalidade getGerLocalidade() {
        return this.gerLocalidade;
    }

    public void setGerLocalidade(GLocalidade gerLocalidade) {
        this.gerLocalidade = gerLocalidade;
    }

    public GLocalidade getGLocalidadeElo() {
        return this.GLocalidadeElo;
    }

    public void setGLocalidadeElo(GLocalidade GLocalidadeElo) {
        this.GLocalidadeElo = GLocalidadeElo;
    }

    public GCategoria getGerCategoria() {
        return this.gerCategoria;
    }

    public void setGerCategoria(GCategoria gerCategoria) {
        this.gerCategoria = gerCategoria;
    }

    public GImovelPerfil getGerImovelPerfil() {
        return this.gerImovelPerfil;
    }

    public void setGerImovelPerfil(GImovelPerfil gerImovelPerfil) {
        this.gerImovelPerfil = gerImovelPerfil;
    }

    public gcom.gerencial.micromedicao.GRota getGerRota() {
        return this.gerRota;
    }

    public void setGerRota(gcom.gerencial.micromedicao.GRota gerRota) {
        this.gerRota = gerRota;
    }

    public GQuadra getGerQuadra() {
        return this.gerQuadra;
    }

    public void setGerQuadra(GQuadra gerQuadra) {
        this.gerQuadra = gerQuadra;
    }

    public GLigacaoEsgotoSituacao getGerLigacaoEsgotoSituacao() {
        return this.gerLigacaoEsgotoSituacao;
    }

    public void setGerLigacaoEsgotoSituacao(GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao) {
        this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
    }

    public GLigacaoEsgotoPerfil getGerLigacaoEsgotoPerfil() {
        return this.gerLigacaoEsgotoPerfil;
    }

    public void setGerLigacaoEsgotoPerfil(GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Integer getCodigoGrupoSubcategoria() {
		return codigoGrupoSubcategoria;
	}

	public void setCodigoGrupoSubcategoria(Integer codigoGrupoSubcategoria) {
		this.codigoGrupoSubcategoria = codigoGrupoSubcategoria;
	}

}
