package gcom.faturamento;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.CategoriaTipo;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.faturamento.consumotarifa.ConsumoTarifa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class HistogramaAguaLigacaoSemQuadra implements Serializable {

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
    
    private Subcategoria subcategoria;

	public HistogramaAguaLigacaoSemQuadra() {
		super();
	}

	public HistogramaAguaLigacaoSemQuadra(Integer id, int anoMesReferencia, int codigoSetorComercial, short indicadorLigacaoMista, short indicadorConsumoReal, short indicadorHidrometro, short indicadorPoco, short indicadorVolFixadoagua, int quantidadeConsumo, int quantidadeLigacao, int quantidadeEconomiaLigacao, BigDecimal valorFaturadoLigacao, int volumeFaturadoLigacao, Date ultimaAlteracao, GerenciaRegional gerenciaRegional, Localidade localidade, Localidade localidadeEelo, ConsumoTarifa consumoTarifa, ImovelPerfil imovelPerfil, LigacaoAguaSituacao ligacaoAguaSituacao, UnidadeNegocio unidadeNegocio, EsferaPoder esferaPoder, Categoria categoria, Subcategoria subcategoria, CategoriaTipo categoriaTipo, SetorComercial setorComercial) {
		super();

		this.id = id;
		this.anoMesReferencia = anoMesReferencia;
		this.codigoSetorComercial = codigoSetorComercial;
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
		this.consumoTarifa = consumoTarifa;
		this.imovelPerfil = imovelPerfil;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.unidadeNegocio = unidadeNegocio;
		this.esferaPoder = esferaPoder;
		this.categoria = categoria;
		this.subcategoria = subcategoria;
		this.categoriaTipo = categoriaTipo;
		this.setorComercial = setorComercial;
	}

	public int getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(int anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public CategoriaTipo getCategoriaTipo() {
		return categoriaTipo;
	}

	public void setCategoriaTipo(CategoriaTipo categoriaTipo) {
		this.categoriaTipo = categoriaTipo;
	}

	public int getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(int codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public ConsumoTarifa getConsumoTarifa() {
		return consumoTarifa;
	}

	public void setConsumoTarifa(ConsumoTarifa consumoTarifa) {
		this.consumoTarifa = consumoTarifa;
	}

	public EsferaPoder getEsferaPoder() {
		return esferaPoder;
	}

	public void setEsferaPoder(EsferaPoder esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ImovelPerfil getImovelPerfil() {
		return imovelPerfil;
	}

	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public short getIndicadorConsumoReal() {
		return indicadorConsumoReal;
	}

	public void setIndicadorConsumoReal(short indicadorConsumoReal) {
		this.indicadorConsumoReal = indicadorConsumoReal;
	}

	public short getIndicadorHidrometro() {
		return indicadorHidrometro;
	}

	public void setIndicadorHidrometro(short indicadorHidrometro) {
		this.indicadorHidrometro = indicadorHidrometro;
	}

	public short getIndicadorLigacaoMista() {
		return indicadorLigacaoMista;
	}

	public void setIndicadorLigacaoMista(short indicadorLigacaoMista) {
		this.indicadorLigacaoMista = indicadorLigacaoMista;
	}

	public short getIndicadorPoco() {
		return indicadorPoco;
	}

	public void setIndicadorPoco(short indicadorPoco) {
		this.indicadorPoco = indicadorPoco;
	}

	public short getIndicadorVolFixadoagua() {
		return indicadorVolFixadoagua;
	}

	public void setIndicadorVolFixadoagua(short indicadorVolFixadoagua) {
		this.indicadorVolFixadoagua = indicadorVolFixadoagua;
	}

	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Localidade getLocalidadeEelo() {
		return localidadeEelo;
	}

	public void setLocalidadeEelo(Localidade localidadeEelo) {
		this.localidadeEelo = localidadeEelo;
	}

	public int getQuantidadeConsumo() {
		return quantidadeConsumo;
	}

	public void setQuantidadeConsumo(int quantidadeConsumo) {
		this.quantidadeConsumo = quantidadeConsumo;
	}

	public int getQuantidadeEconomiaLigacao() {
		return quantidadeEconomiaLigacao;
	}

	public void setQuantidadeEconomiaLigacao(int quantidadeEconomiaLigacao) {
		this.quantidadeEconomiaLigacao = quantidadeEconomiaLigacao;
	}

	public int getQuantidadeLigacao() {
		return quantidadeLigacao;
	}

	public void setQuantidadeLigacao(int quantidadeLigacao) {
		this.quantidadeLigacao = quantidadeLigacao;
	}

	public SetorComercial getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public BigDecimal getValorFaturadoLigacao() {
		return valorFaturadoLigacao;
	}

	public void setValorFaturadoLigacao(BigDecimal valorFaturadoLigacao) {
		this.valorFaturadoLigacao = valorFaturadoLigacao;
	}

	public int getVolumeFaturadoLigacao() {
		return volumeFaturadoLigacao;
	}

	public void setVolumeFaturadoLigacao(int volumeFaturadoLigacao) {
		this.volumeFaturadoLigacao = volumeFaturadoLigacao;
	}

	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

}
