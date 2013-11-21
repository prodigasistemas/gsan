package gcom.gerencial.cobranca;

import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao;
import gcom.gerencial.cadastro.cliente.GClienteTipo;
import gcom.gerencial.cadastro.cliente.GEsferaPoder;
import gcom.gerencial.cadastro.imovel.GCategoria;
import gcom.gerencial.cadastro.imovel.GImovelPerfil;
import gcom.gerencial.cadastro.imovel.GSubcategoria;
import gcom.gerencial.cadastro.localidade.GGerenciaRegional;
import gcom.gerencial.cadastro.localidade.GLocalidade;
import gcom.gerencial.cadastro.localidade.GSetorComercial;
import gcom.gerencial.cadastro.localidade.GUnidadeNegocio;
import gcom.gerencial.faturamento.GConsumoTarifa;
import gcom.gerencial.financeiro.GFinanciamentoTipo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoPendenciaSemQuadra implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer anoMesReferencia;

    /** nullable persistent field */
    private Integer codigoSetorComercial;

    /** nullable persistent field */
    private Integer indicadorVolumeFixadoAgua;

    /** nullable persistent field */
    private Integer indicadorVolumeFixadoEsgoto;

    /** nullable persistent field */
    private Integer anoMesReferenciaDocumento;

    /** nullable persistent field */
    private Short indicadorHidrometro;

    /** nullable persistent field */
    private Short indicadorVencido;

    /** nullable persistent field */
    private Integer quantidadeLigacoes;

    /** nullable persistent field */
    private Integer quantidadeDocumentos;

    /** nullable persistent field */
    private BigDecimal valorPendenteAgua;

    /** nullable persistent field */
    private BigDecimal valorPendenteEsgoto;

    /** nullable persistent field */
    private BigDecimal valorPendenteDebitos;

    /** nullable persistent field */
    private BigDecimal valorPendenteCreditos;

    /** nullable persistent field */
    private BigDecimal valorPendenteImpostos;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private GConsumoTarifa gerConsumoTarifa;

    /** persistent field */
    private GGerenciaRegional gerGerenciaRegional;

    /** persistent field */
    private GSetorComercial gerSetorComercial;

    /** persistent field */
    private GSubcategoria gerSubcategoria;

    /** persistent field */
    private gcom.gerencial.cobranca.GDocumentoTipo gerDocumentoTipo;

    /** persistent field */
    private GLigacaoAguaPerfil gerLigacaoAguaPerfil;

    /** persistent field */
    private GEsferaPoder gerEsferaPoder;

    /** persistent field */
    private GClienteTipo gerClienteTipo;

    /** persistent field */
    private GUnidadeNegocio gerUnidadeNegocio;

    /** persistent field */
    private GLigacaoAguaSituacao gerLigacaoAguaSituacao;

    /** persistent field */
    private GFinanciamentoTipo gerFinanciamentoTipo;

    /** persistent field */
    private GCategoria gerCategoria;

    /** persistent field */
    private GLocalidade gerLocalidade;

    /** persistent field */
    private GLocalidade gerLocalidadeElo;

    /** persistent field */
    private GImovelPerfil gerImovelPerfil;

    /** persistent field */
    private GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao;

    /** persistent field */
    private GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil;

    /** full constructor */
    public UnResumoPendenciaSemQuadra(Integer id, Integer anoMesReferencia, Integer codigoSetorComercial, Integer indicadorVolumeFixadoAgua, Integer indicadorVolumeFixadoEsgoto, Integer anoMesReferenciaDocumento, Short indicadorHidrometro, Short indicadorVencido, Integer quantidadeLigacoes, Integer quantidadeDocumentos, BigDecimal valorPendenteAgua, BigDecimal valorPendenteEsgoto, BigDecimal valorPendenteDebitos, BigDecimal valorPendenteCreditos, BigDecimal valorPendenteImpostos, Date ultimaAlteracao, GConsumoTarifa gerConsumoTarifa, GGerenciaRegional gerGerenciaRegional, GSetorComercial gerSetorComercial, GSubcategoria gerSubcategoria, gcom.gerencial.cobranca.GDocumentoTipo gerDocumentoTipo, GLigacaoAguaPerfil gerLigacaoAguaPerfil, GEsferaPoder gerEsferaPoder, GClienteTipo gerClienteTipo, GUnidadeNegocio gerUnidadeNegocio, GLigacaoAguaSituacao gerLigacaoAguaSituacao, GFinanciamentoTipo gerFinanciamentoTipo, GCategoria gerCategoria, GLocalidade gerLocalidade, GLocalidade gerLocalidadeElo, GImovelPerfil gerImovelPerfil, GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao, GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
        this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.codigoSetorComercial = codigoSetorComercial;
        this.indicadorVolumeFixadoAgua = indicadorVolumeFixadoAgua;
        this.indicadorVolumeFixadoEsgoto = indicadorVolumeFixadoEsgoto;
        this.anoMesReferenciaDocumento = anoMesReferenciaDocumento;
        this.indicadorHidrometro = indicadorHidrometro;
        this.indicadorVencido = indicadorVencido;
        this.quantidadeLigacoes = quantidadeLigacoes;
        this.quantidadeDocumentos = quantidadeDocumentos;
        this.valorPendenteAgua = valorPendenteAgua;
        this.valorPendenteEsgoto = valorPendenteEsgoto;
        this.valorPendenteDebitos = valorPendenteDebitos;
        this.valorPendenteCreditos = valorPendenteCreditos;
        this.valorPendenteImpostos = valorPendenteImpostos;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerConsumoTarifa = gerConsumoTarifa;
        this.gerGerenciaRegional = gerGerenciaRegional;
        this.gerSetorComercial = gerSetorComercial;
        this.gerSubcategoria = gerSubcategoria;
        this.gerDocumentoTipo = gerDocumentoTipo;
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
        this.gerEsferaPoder = gerEsferaPoder;
        this.gerClienteTipo = gerClienteTipo;
        this.gerUnidadeNegocio = gerUnidadeNegocio;
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
        this.gerFinanciamentoTipo = gerFinanciamentoTipo;
        this.gerCategoria = gerCategoria;
        this.gerLocalidade = gerLocalidade;
        this.gerLocalidadeElo = gerLocalidadeElo;
        this.gerImovelPerfil = gerImovelPerfil;
        this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
    }

    /** default constructor */
    public UnResumoPendenciaSemQuadra() {
    }

    /** minimal constructor */
    public UnResumoPendenciaSemQuadra(Integer id, GConsumoTarifa gerConsumoTarifa, GGerenciaRegional gerGerenciaRegional, GSetorComercial gerSetorComercial, GSubcategoria gerSubcategoria, gcom.gerencial.cobranca.GDocumentoTipo gerDocumentoTipo, GLigacaoAguaPerfil gerLigacaoAguaPerfil, GEsferaPoder gerEsferaPoder, GClienteTipo gerClienteTipo, GUnidadeNegocio gerUnidadeNegocio, GLigacaoAguaSituacao gerLigacaoAguaSituacao, GFinanciamentoTipo gerFinanciamentoTipo, GCategoria gerCategoria, GLocalidade gerLocalidade, GLocalidade gerLocalidadeElo, GImovelPerfil gerImovelPerfil, GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao, GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
        this.id = id;
        this.gerConsumoTarifa = gerConsumoTarifa;
        this.gerGerenciaRegional = gerGerenciaRegional;
        this.gerSetorComercial = gerSetorComercial;
        this.gerSubcategoria = gerSubcategoria;
        this.gerDocumentoTipo = gerDocumentoTipo;
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
        this.gerEsferaPoder = gerEsferaPoder;
        this.gerClienteTipo = gerClienteTipo;
        this.gerUnidadeNegocio = gerUnidadeNegocio;
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
        this.gerFinanciamentoTipo = gerFinanciamentoTipo;
        this.gerCategoria = gerCategoria;
        this.gerLocalidade = gerLocalidade;
        this.gerLocalidadeElo = gerLocalidadeElo;
        this.gerImovelPerfil = gerImovelPerfil;
        this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(Integer anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public Integer getCodigoSetorComercial() {
        return this.codigoSetorComercial;
    }

    public void setCodigoSetorComercial(Integer codigoSetorComercial) {
        this.codigoSetorComercial = codigoSetorComercial;
    }

    public Integer getIndicadorVolumeFixadoAgua() {
        return this.indicadorVolumeFixadoAgua;
    }

    public void setIndicadorVolumeFixadoAgua(Integer indicadorVolumeFixadoAgua) {
        this.indicadorVolumeFixadoAgua = indicadorVolumeFixadoAgua;
    }

    public Integer getIndicadorVolumeFixadoEsgoto() {
        return this.indicadorVolumeFixadoEsgoto;
    }

    public void setIndicadorVolumeFixadoEsgoto(Integer indicadorVolumeFixadoEsgoto) {
        this.indicadorVolumeFixadoEsgoto = indicadorVolumeFixadoEsgoto;
    }

    public Integer getAnoMesReferenciaDocumento() {
        return this.anoMesReferenciaDocumento;
    }

    public void setAnoMesReferenciaDocumento(Integer anoMesReferenciaDocumento) {
        this.anoMesReferenciaDocumento = anoMesReferenciaDocumento;
    }

    public Short getIndicadorHidrometro() {
        return this.indicadorHidrometro;
    }

    public void setIndicadorHidrometro(Short indicadorHidrometro) {
        this.indicadorHidrometro = indicadorHidrometro;
    }

    public Short getIndicadorVencido() {
        return this.indicadorVencido;
    }

    public void setIndicadorVencido(Short indicadorVencido) {
        this.indicadorVencido = indicadorVencido;
    }

    public Integer getQuantidadeLigacoes() {
        return this.quantidadeLigacoes;
    }

    public void setQuantidadeLigacoes(Integer quantidadeLigacoes) {
        this.quantidadeLigacoes = quantidadeLigacoes;
    }

    public Integer getQuantidadeDocumentos() {
        return this.quantidadeDocumentos;
    }

    public void setQuantidadeDocumentos(Integer quantidadeDocumentos) {
        this.quantidadeDocumentos = quantidadeDocumentos;
    }

    public BigDecimal getValorPendenteAgua() {
        return this.valorPendenteAgua;
    }

    public void setValorPendenteAgua(BigDecimal valorPendenteAgua) {
        this.valorPendenteAgua = valorPendenteAgua;
    }

    public BigDecimal getValorPendenteEsgoto() {
        return this.valorPendenteEsgoto;
    }

    public void setValorPendenteEsgoto(BigDecimal valorPendenteEsgoto) {
        this.valorPendenteEsgoto = valorPendenteEsgoto;
    }

    public BigDecimal getValorPendenteDebitos() {
        return this.valorPendenteDebitos;
    }

    public void setValorPendenteDebitos(BigDecimal valorPendenteDebitos) {
        this.valorPendenteDebitos = valorPendenteDebitos;
    }

    public BigDecimal getValorPendenteCreditos() {
        return this.valorPendenteCreditos;
    }

    public void setValorPendenteCreditos(BigDecimal valorPendenteCreditos) {
        this.valorPendenteCreditos = valorPendenteCreditos;
    }

    public BigDecimal getValorPendenteImpostos() {
        return this.valorPendenteImpostos;
    }

    public void setValorPendenteImpostos(BigDecimal valorPendenteImpostos) {
        this.valorPendenteImpostos = valorPendenteImpostos;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public GConsumoTarifa getGerConsumoTarifa() {
        return this.gerConsumoTarifa;
    }

    public void setGerConsumoTarifa(GConsumoTarifa gerConsumoTarifa) {
        this.gerConsumoTarifa = gerConsumoTarifa;
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

    public gcom.gerencial.cobranca.GDocumentoTipo getGerDocumentoTipo() {
        return this.gerDocumentoTipo;
    }

    public void setGerDocumentoTipo(gcom.gerencial.cobranca.GDocumentoTipo gerDocumentoTipo) {
        this.gerDocumentoTipo = gerDocumentoTipo;
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

    public GUnidadeNegocio getGerUnidadeNegocio() {
        return this.gerUnidadeNegocio;
    }

    public void setGerUnidadeNegocio(GUnidadeNegocio gerUnidadeNegocio) {
        this.gerUnidadeNegocio = gerUnidadeNegocio;
    }

    public GLigacaoAguaSituacao getGerLigacaoAguaSituacao() {
        return this.gerLigacaoAguaSituacao;
    }

    public void setGerLigacaoAguaSituacao(GLigacaoAguaSituacao gerLigacaoAguaSituacao) {
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
    }

    public GFinanciamentoTipo getGerFinanciamentoTipo() {
        return this.gerFinanciamentoTipo;
    }

    public void setGerFinanciamentoTipo(GFinanciamentoTipo gerFinanciamentoTipo) {
        this.gerFinanciamentoTipo = gerFinanciamentoTipo;
    }

    public GCategoria getGerCategoria() {
        return this.gerCategoria;
    }

    public void setGerCategoria(GCategoria gerCategoria) {
        this.gerCategoria = gerCategoria;
    }

    public GLocalidade getGerLocalidade() {
        return this.gerLocalidade;
    }

    public void setGerLocalidade(GLocalidade gerLocalidade) {
        this.gerLocalidade = gerLocalidade;
    }

    public GLocalidade getGerLocalidadeElo() {
        return this.gerLocalidadeElo;
    }

    public void setGerLocalidadeElo(GLocalidade gerLocalidadeElo) {
        this.gerLocalidadeElo = gerLocalidadeElo;
    }

    public GImovelPerfil getGerImovelPerfil() {
        return this.gerImovelPerfil;
    }

    public void setGerImovelPerfil(GImovelPerfil gerImovelPerfil) {
        this.gerImovelPerfil = gerImovelPerfil;
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

}
