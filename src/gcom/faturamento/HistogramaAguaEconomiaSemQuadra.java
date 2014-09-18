package gcom.faturamento;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.CategoriaTipo;
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
public class HistogramaAguaEconomiaSemQuadra implements Serializable {

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
    private int indicadorConsumoReal;

    /** persistent field */
    private int indicadorHidrometro;

    /** persistent field */
    private int indicadorPoco;

    /** persistent field */
    private int indicadorVolFixadoAgua;

    /** persistent field */
    private int quantidadeConsumo;

    /** persistent field */
    private int quantidadeEconomia;

    /** persistent field */
    private BigDecimal valorFaturadoEconomia;

    /** persistent field */
    private int volumeFaturadoEconomia;
    
    /** persistent field */
    private int quantidadeLigacao;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    Categoria categoria;

    /** persistent field */
    LigacaoAguaSituacao ligacaoAguaSituacao;

    /** persistent field */
    CategoriaTipo categoriaTipo;

    /** persistent field */
    Subcategoria subcategoria;

    /** persistent field */
    Localidade localidadeElo;

    /** persistent field */
    Localidade localidade;

    /** persistent field */
    EsferaPoder esferaPoder;

    /** persistent field */
    UnidadeNegocio unidadeNegocio;

    /** persistent field */
    SetorComercial setorComercial;

    /** persistent field */
    ConsumoTarifa consumoTarifa;

    /** persistent field */
    GerenciaRegional gerenciaRegional;

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

	public int getIndicadorConsumoReal() {
		return indicadorConsumoReal;
	}

	public void setIndicadorConsumoReal(int indicadorConsumoReal) {
		this.indicadorConsumoReal = indicadorConsumoReal;
	}

	public int getIndicadorHidrometro() {
		return indicadorHidrometro;
	}

	public void setIndicadorHidrometro(int indicadorHidrometro) {
		this.indicadorHidrometro = indicadorHidrometro;
	}

	public int getIndicadorPoco() {
		return indicadorPoco;
	}

	public void setIndicadorPoco(int indicadorPoco) {
		this.indicadorPoco = indicadorPoco;
	}

	public int getIndicadorVolFixadoAgua() {
		return indicadorVolFixadoAgua;
	}

	public void setIndicadorVolFixadoAgua(int indicadorVolFixadoAgua) {
		this.indicadorVolFixadoAgua = indicadorVolFixadoAgua;
	}

	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public Localidade getLocalidadeElo() {
		return localidadeElo;
	}

	public void setLocalidadeElo(Localidade localidadeElo) {
		this.localidadeElo = localidadeElo;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public int getQuantidadeConsumo() {
		return quantidadeConsumo;
	}

	public void setQuantidadeConsumo(int quantidadeConsumo) {
		this.quantidadeConsumo = quantidadeConsumo;
	}

	public int getQuantidadeEconomia() {
		return quantidadeEconomia;
	}

	public void setQuantidadeEconomia(int quantidadeEconomia) {
		this.quantidadeEconomia = quantidadeEconomia;
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

	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
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

	public BigDecimal getValorFaturadoEconomia() {
		return valorFaturadoEconomia;
	}

	public void setValorFaturadoEconomia(BigDecimal valorFaturadoEconomia) {
		this.valorFaturadoEconomia = valorFaturadoEconomia;
	}

	public int getVolumeFaturadoEconomia() {
		return volumeFaturadoEconomia;
	}

	public void setVolumeFaturadoEconomia(int volumeFaturadoEconomia) {
		this.volumeFaturadoEconomia = volumeFaturadoEconomia;
	}

	public HistogramaAguaEconomiaSemQuadra(Integer id, int anoMesReferencia, int codigoSetorComercial, int indicadorConsumoReal, int indicadorHidrometro, int indicadorPoco, int indicadorVolFixadoAgua, int quantidadeConsumo, int quantidadeEconomia, BigDecimal valorFaturadoEconomia, int volumeFaturadoEconomia, int quantidadeLigacao, Date ultimaAlteracao, Categoria categoria, LigacaoAguaSituacao ligacaoAguaSituacao, CategoriaTipo categoriaTipo, Subcategoria subcategoria, Localidade localidadeElo, Localidade localidade, EsferaPoder esferaPoder, UnidadeNegocio unidadeNegocio, SetorComercial setorComercial, ConsumoTarifa consumoTarifa, GerenciaRegional gerenciaRegional) {
		super();
		
		this.id = id;
		this.anoMesReferencia = anoMesReferencia;
		this.codigoSetorComercial = codigoSetorComercial;
		this.indicadorConsumoReal = indicadorConsumoReal;
		this.indicadorHidrometro = indicadorHidrometro;
		this.indicadorPoco = indicadorPoco;
		this.indicadorVolFixadoAgua = indicadorVolFixadoAgua;
		this.quantidadeConsumo = quantidadeConsumo;
		this.quantidadeEconomia = quantidadeEconomia;
		this.valorFaturadoEconomia = valorFaturadoEconomia;
		this.volumeFaturadoEconomia = volumeFaturadoEconomia;
		this.quantidadeLigacao = quantidadeLigacao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.categoria = categoria;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.categoriaTipo = categoriaTipo;
		this.subcategoria = subcategoria;
		this.localidadeElo = localidadeElo;
		this.localidade = localidade;
		this.esferaPoder = esferaPoder;
		this.unidadeNegocio = unidadeNegocio;
		this.setorComercial = setorComercial;
		this.consumoTarifa = consumoTarifa;
		this.gerenciaRegional = gerenciaRegional;
	}

	public HistogramaAguaEconomiaSemQuadra() {
		super();
	}

}
