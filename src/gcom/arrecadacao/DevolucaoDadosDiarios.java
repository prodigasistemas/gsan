package gcom.arrecadacao;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.DocumentoTipo;
import gcom.micromedicao.Rota;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DevolucaoDadosDiarios implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferencia;

    /** nullable persistent field */
    private Integer codigoSetorComercial;

    /** nullable persistent field */
    private Integer numeroQuadra;

    /** persistent field */
    private short indicadorHidrometro;

    /** persistent field */
    private Date dataDevolucao;

    /** persistent field */
    private int quantidadeDevolucoes;

    /** persistent field */
    private BigDecimal valorDevolucoes;

    /** persistent field */
    private Integer quantidadeDocumentos;

    /** nullable persistent field */
    private String devolucaoTipo;

    /** persistent field */
    private Date dataUltimaAlteracao;

    /** persistent field */
    private GerenciaRegional gerenciaRegional;

    /** persistent field */
    private Localidade localidade;

    /** persistent field */
    private Quadra quadra;

    /** persistent field */
    private DocumentoTipo documentoTipoAgregador;

    /** persistent field */
    private DocumentoTipo documentoTipo;

    /** persistent field */
    private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;

    /** persistent field */
    private ImovelPerfil imovelPerfil;

    /** persistent field */
    private Rota rota;

    /** persistent field */
    private Arrecadador arrecadador;

    /** persistent field */
    private ArrecadacaoForma arrecadacaoForma;

    /** persistent field */
    private SetorComercial setorComercial;

    /** persistent field */
    private LigacaoAguaSituacao ligacaoAguaSituacao;

    /** persistent field */
    private UnidadeNegocio unidadeNegocio;

    /** persistent field */
    private EsferaPoder esferaPoder;

    /** persistent field */
    private CobrancaDocumento cobrancaDocumento;

    /** persistent field */
    private Categoria categoria;

    /** full constructor */
    public DevolucaoDadosDiarios(Integer id, int anoMesReferencia, Integer codigoSetorComercial, Integer numeroQuadra, short indicadorHidrometro, Date dataDevolucao, int quantidadeDevolucoes, BigDecimal valorDevolucoes, Integer quantidadeDocumentos, String devolucaoTipo, Date dataUltimaAlteracao, GerenciaRegional gerenciaRegional, Localidade localidade, Quadra quadra, DocumentoTipo documentoTipoAgregador, DocumentoTipo documentoTipo, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, ImovelPerfil imovelPerfil, Rota rota, Arrecadador arrecadador, ArrecadacaoForma arrecadacaoForma, SetorComercial setorComercial, LigacaoAguaSituacao ligacaoAguaSituacao, UnidadeNegocio unidadeNegocio, EsferaPoder esferaPoder, CobrancaDocumento cobrancaDocumento, Categoria categoria) {
        this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.indicadorHidrometro = indicadorHidrometro;
        this.dataDevolucao = dataDevolucao;
        this.quantidadeDevolucoes = quantidadeDevolucoes;
        this.valorDevolucoes = valorDevolucoes;
        this.quantidadeDocumentos = quantidadeDocumentos;
        this.devolucaoTipo = devolucaoTipo;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
        this.gerenciaRegional = gerenciaRegional;
        this.localidade = localidade;
        this.quadra = quadra;
        this.documentoTipoAgregador = documentoTipoAgregador;
        this.documentoTipo = documentoTipo;
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
        this.imovelPerfil = imovelPerfil;
        this.rota = rota;
        this.arrecadador = arrecadador;
        this.arrecadacaoForma = arrecadacaoForma;
        this.setorComercial = setorComercial;
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
        this.unidadeNegocio = unidadeNegocio;
        this.esferaPoder = esferaPoder;
        this.cobrancaDocumento = cobrancaDocumento;
        this.categoria = categoria;
    }

    /** default constructor */
    public DevolucaoDadosDiarios() {
    }

    /** minimal constructor */
    public DevolucaoDadosDiarios(Integer id, int anoMesReferencia, short indicadorHidrometro, Date dataDevolucao, int quantidadeDevolucoes, BigDecimal valorDevolucoes, Integer quantidadeDocumentos, Date dataUltimaAlteracao, GerenciaRegional gerenciaRegional, Localidade localidade, Quadra quadra, DocumentoTipo documentoTipoAgregador, DocumentoTipo documentoTipo, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, ImovelPerfil imovelPerfil, Rota rota, Arrecadador arrecadador, ArrecadacaoForma arrecadacaoForma, SetorComercial setorComercial, LigacaoAguaSituacao ligacaoAguaSituacao, UnidadeNegocio unidadeNegocio, EsferaPoder esferaPoder, CobrancaDocumento cobrancaDocumento, Categoria categoria) {
        this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.indicadorHidrometro = indicadorHidrometro;
        this.dataDevolucao = dataDevolucao;
        this.quantidadeDevolucoes = quantidadeDevolucoes;
        this.valorDevolucoes = valorDevolucoes;
        this.quantidadeDocumentos = quantidadeDocumentos;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
        this.gerenciaRegional = gerenciaRegional;
        this.localidade = localidade;
        this.quadra = quadra;
        this.documentoTipoAgregador = documentoTipoAgregador;
        this.documentoTipo = documentoTipo;
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
        this.imovelPerfil = imovelPerfil;
        this.rota = rota;
        this.arrecadador = arrecadador;
        this.arrecadacaoForma = arrecadacaoForma;
        this.setorComercial = setorComercial;
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
        this.unidadeNegocio = unidadeNegocio;
        this.esferaPoder = esferaPoder;
        this.cobrancaDocumento = cobrancaDocumento;
        this.categoria = categoria;
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

    public Integer getCodigoSetorComercial() {
        return this.codigoSetorComercial;
    }

    public void setCodigoSetorComercial(Integer codigoSetorComercial) {
        this.codigoSetorComercial = codigoSetorComercial;
    }

    public Integer getNumeroQuadra() {
        return this.numeroQuadra;
    }

    public void setNumeroQuadra(Integer numeroQuadra) {
        this.numeroQuadra = numeroQuadra;
    }

    public short getIndicadorHidrometro() {
        return this.indicadorHidrometro;
    }

    public void setIndicadorHidrometro(short indicadorHidrometro) {
        this.indicadorHidrometro = indicadorHidrometro;
    }

    public Date getDataDevolucao() {
        return this.dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public int getQuantidadeDevolucoes() {
        return this.quantidadeDevolucoes;
    }

    public void setQuantidadeDevolucoes(int quantidadeDevolucoes) {
        this.quantidadeDevolucoes = quantidadeDevolucoes;
    }

    public BigDecimal getValorDevolucoes() {
        return this.valorDevolucoes;
    }

    public void setValorDevolucoes(BigDecimal valorDevolucoes) {
        this.valorDevolucoes = valorDevolucoes;
    }

    public Integer getQuantidadeDocumentos() {
        return this.quantidadeDocumentos;
    }

    public void setQuantidadeDocumentos(Integer quantidadeDocumentos) {
        this.quantidadeDocumentos = quantidadeDocumentos;
    }

    public String getDevolucaoTipo() {
        return this.devolucaoTipo;
    }

    public void setDevolucaoTipo(String devolucaoTipo) {
        this.devolucaoTipo = devolucaoTipo;
    }

    public Date getDataUltimaAlteracao() {
        return this.dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    public GerenciaRegional getGerenciaRegional() {
        return this.gerenciaRegional;
    }

    public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
        this.gerenciaRegional = gerenciaRegional;
    }

    public Localidade getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public Quadra getQuadra() {
        return this.quadra;
    }

    public void setQuadra(Quadra quadra) {
        this.quadra = quadra;
    }

    public DocumentoTipo getDocumentoTipoAgregador() {
        return this.documentoTipoAgregador;
    }

    public void setDocumentoTipoAgregador(DocumentoTipo documentoTipoAgregador) {
        this.documentoTipoAgregador = documentoTipoAgregador;
    }

    public DocumentoTipo getDocumentoTipo() {
        return this.documentoTipo;
    }

    public void setDocumentoTipo(DocumentoTipo documentoTipo) {
        this.documentoTipo = documentoTipo;
    }

    public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
        return this.ligacaoEsgotoSituacao;
    }

    public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
    }

    public ImovelPerfil getImovelPerfil() {
        return this.imovelPerfil;
    }

    public void setImovelPerfil(ImovelPerfil imovelPerfil) {
        this.imovelPerfil = imovelPerfil;
    }

    public Rota getRota() {
        return this.rota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }

    public Arrecadador getArrecadador() {
        return this.arrecadador;
    }

    public void setArrecadador(Arrecadador arrecadador) {
        this.arrecadador = arrecadador;
    }

    public ArrecadacaoForma getArrecadacaoForma() {
        return this.arrecadacaoForma;
    }

    public void setArrecadacaoForma(ArrecadacaoForma arrecadacaoForma) {
        this.arrecadacaoForma = arrecadacaoForma;
    }

    public SetorComercial getSetorComercial() {
        return this.setorComercial;
    }

    public void setSetorComercial(SetorComercial setorComercial) {
        this.setorComercial = setorComercial;
    }

    public LigacaoAguaSituacao getLigacaoAguaSituacao() {
        return this.ligacaoAguaSituacao;
    }

    public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
    }

    public UnidadeNegocio getUnidadeNegocio() {
        return this.unidadeNegocio;
    }

    public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
        this.unidadeNegocio = unidadeNegocio;
    }

    public EsferaPoder getEsferaPoder() {
        return this.esferaPoder;
    }

    public void setEsferaPoder(EsferaPoder esferaPoder) {
        this.esferaPoder = esferaPoder;
    }

    public CobrancaDocumento getCobrancaDocumento() {
        return this.cobrancaDocumento;
    }

    public void setCobrancaDocumento(CobrancaDocumento cobrancaDocumento) {
        this.cobrancaDocumento = cobrancaDocumento;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
