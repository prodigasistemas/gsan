package gcom.faturamento;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.CategoriaTipo;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.faturamento.consumotarifa.ConsumoTarifa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class HistogramaAguaLigacao implements Serializable {

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
    private short indicadorLigacaoMista;

    /** persistent field */
    private short indicadorConsumoReal;

    /** persistent field */
    private short indicadorHidrometro;

    /** persistent field */
    private short indicadorPoco;

    /** persistent field */
    private short indicadorVolFixadoagua;

    /** persistent field */
    private int quantidadeConsumo;

    /** persistent field */
    private int quantidadeLigacao;

    /** persistent field */
    private int quantidadeEconomiaLigacao;

    /** persistent field */
    private BigDecimal valorFaturadoLigacao;

    /** persistent field */
    private int volumeFaturadoLigacao;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private GerenciaRegional gerenciaRegional;

    /** persistent field */
    private Localidade localidade;

    /** persistent field */
    private Localidade localidadeEelo;

    /** persistent field */
    private Quadra quadra;

    /** persistent field */
    private ConsumoTarifa consumoTarifa;

    /** persistent field */
    private ImovelPerfil imovelPerfil;

    /** persistent field */
    private LigacaoAguaSituacao ligacaoAguaSituacao;

    /** persistent field */
    private UnidadeNegocio unidadeNegocio;

    /** persistent field */
    private EsferaPoder esferaPoder;

    /** persistent field */
    private Categoria categoria;

    /** persistent field */
    private CategoriaTipo categoriaTipo;

    /** persistent field */
    private SetorComercial setorComercial;

    /** full constructor */
    public HistogramaAguaLigacao(Integer id, int anoMesReferencia, int codigoSetorComercial, int numeroQuadra, short indicadorLigacaoMista, short indicadorConsumoReal, short indicadorHidrometro, short indicadorPoco, short indicadorVolFixadoagua, int quantidadeConsumo, int quantidadeLigacao, int quantidadeEconomiaLigacao, BigDecimal valorFaturadoLigacao, int volumeFaturadoLigacao, Date ultimaAlteracao, GerenciaRegional gerenciaRegional, Localidade localidade, Localidade localidadeEelo, Quadra quadra, ConsumoTarifa consumoTarifa, ImovelPerfil imovelPerfil, LigacaoAguaSituacao ligacaoAguaSituacao, UnidadeNegocio unidadeNegocio, EsferaPoder esferaPoder, Categoria categoria, CategoriaTipo categoriaTipo, SetorComercial setorComercial) {
        this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.indicadorLigacaoMista = indicadorLigacaoMista;
        this.indicadorConsumoReal = indicadorConsumoReal;
        this.indicadorHidrometro = indicadorHidrometro;
        this.indicadorPoco = indicadorPoco;
        this.indicadorVolFixadoagua = indicadorVolFixadoagua;
        this.quantidadeConsumo = quantidadeConsumo;
        this.quantidadeLigacao = quantidadeLigacao;
        this.quantidadeEconomiaLigacao = quantidadeEconomiaLigacao;
        this.valorFaturadoLigacao = valorFaturadoLigacao;
        this.volumeFaturadoLigacao = volumeFaturadoLigacao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerenciaRegional = gerenciaRegional;
        this.localidade = localidade;
        this.localidadeEelo = localidadeEelo;
        this.quadra = quadra;
        this.consumoTarifa = consumoTarifa;
        this.imovelPerfil = imovelPerfil;
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
        this.unidadeNegocio = unidadeNegocio;
        this.esferaPoder = esferaPoder;
        this.categoria = categoria;
        this.categoriaTipo = categoriaTipo;
        this.setorComercial = setorComercial;
    }

    /** default constructor */
    public HistogramaAguaLigacao() {
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

    public short getIndicadorLigacaoMista() {
        return this.indicadorLigacaoMista;
    }

    public void setIndicadorLigacaoMista(short indicadorLigacaoMista) {
        this.indicadorLigacaoMista = indicadorLigacaoMista;
    }

    public short getIndicadorConsumoReal() {
        return this.indicadorConsumoReal;
    }

    public void setIndicadorConsumoReal(short indicadorConsumoReal) {
        this.indicadorConsumoReal = indicadorConsumoReal;
    }

    public short getIndicadorHidrometro() {
        return this.indicadorHidrometro;
    }

    public void setIndicadorHidrometro(short indicadorHidrometro) {
        this.indicadorHidrometro = indicadorHidrometro;
    }

    public short getIndicadorPoco() {
        return this.indicadorPoco;
    }

    public void setIndicadorPoco(short indicadorPoco) {
        this.indicadorPoco = indicadorPoco;
    }

    public short getIndicadorVolFixadoagua() {
        return this.indicadorVolFixadoagua;
    }

    public void setIndicadorVolFixadoagua(short indicadorVolFixadoagua) {
        this.indicadorVolFixadoagua = indicadorVolFixadoagua;
    }

    public int getQuantidadeConsumo() {
        return this.quantidadeConsumo;
    }

    public void setQuantidadeConsumo(int quantidadeConsumo) {
        this.quantidadeConsumo = quantidadeConsumo;
    }

    public int getQuantidadeLigacao() {
        return this.quantidadeLigacao;
    }

    public void setQuantidadeLigacao(int quantidadeLigacao) {
        this.quantidadeLigacao = quantidadeLigacao;
    }

    public int getQuantidadeEconomiaLigacao() {
        return this.quantidadeEconomiaLigacao;
    }

    public void setQuantidadeEconomiaLigacao(int quantidadeEconomiaLigacao) {
        this.quantidadeEconomiaLigacao = quantidadeEconomiaLigacao;
    }

    public BigDecimal getValorFaturadoLigacao() {
        return this.valorFaturadoLigacao;
    }

    public void setValorFaturadoLigacao(BigDecimal valorFaturadoLigacao) {
        this.valorFaturadoLigacao = valorFaturadoLigacao;
    }

    public int getVolumeFaturadoLigacao() {
        return this.volumeFaturadoLigacao;
    }

    public void setVolumeFaturadoLigacao(int volumeFaturadoLigacao) {
        this.volumeFaturadoLigacao = volumeFaturadoLigacao;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
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

    public Localidade getLocalidadeEelo() {
        return this.localidadeEelo;
    }

    public void setLocalidadeEelo(Localidade localidadeEelo) {
        this.localidadeEelo = localidadeEelo;
    }

    public Quadra getQuadra() {
        return this.quadra;
    }

    public void setQuadra(Quadra quadra) {
        this.quadra = quadra;
    }

    public ConsumoTarifa getConsumoTarifa() {
        return this.consumoTarifa;
    }

    public void setConsumoTarifa(ConsumoTarifa consumoTarifa) {
        this.consumoTarifa = consumoTarifa;
    }

    public ImovelPerfil getImovelPerfil() {
        return this.imovelPerfil;
    }

    public void setImovelPerfil(ImovelPerfil imovelPerfil) {
        this.imovelPerfil = imovelPerfil;
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

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public CategoriaTipo getCategoriaTipo() {
        return this.categoriaTipo;
    }

    public void setCategoriaTipo(CategoriaTipo categoriaTipo) {
        this.categoriaTipo = categoriaTipo;
    }

    public SetorComercial getSetorComercial() {
        return this.setorComercial;
    }

    public void setSetorComercial(SetorComercial setorComercial) {
        this.setorComercial = setorComercial;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
