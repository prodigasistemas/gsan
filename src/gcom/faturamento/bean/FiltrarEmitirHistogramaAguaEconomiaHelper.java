package gcom.faturamento.bean;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.CategoriaTipo;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.faturamento.ConsumoFaixaCategoria;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;


/**
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * de emitir histograma de agua por economia
 *
 * @author Rafael Pinto
 * 
 * @date 16/06/2007
 */
public class FiltrarEmitirHistogramaAguaEconomiaHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int opcaoTotalizacao;
	private Integer mesAnoFaturamento;
	
	private GerenciaRegional gerenciaRegional = null;
	private UnidadeNegocio unidadeNegocio = null;
	private Localidade eloPolo = null;
	private Localidade localidade = null;
	private SetorComercial setorComercial = null;
	private Quadra quadra = null;
	private Integer tarifa = null;
	private Integer indicadorTarifaCategoria = null;
	
	private CategoriaTipo tipoCategoria = null;
	
	private Collection<Integer> colecaoCategoria = null;
	private Collection<Integer> colecaoSubcategoria = null;
	private Collection<Integer> colecaoTarifa = null;
	private Collection<Integer> colecaoPerfilImovel = null;
	private Collection<Integer> colecaoEsferaPoder = null;
	private Collection<Integer> colecaoSituacaoLigacaoAgua = null;

	private Short consumo = null;
	private Short poco = null;
	private Short volumoFixoAgua = null;
	
	private LinkedHashMap linkedHashMapConsumoFaixaCategoria = new LinkedHashMap();
	
	//Valores variados depende da totalizacao
	private Short medicao = null;
	private String tipoGroupBy = null;
	private String tipoOrderBy = null;
	private Categoria categoria = null;
	private Subcategoria subcategoria = null;
	private ConsumoFaixaCategoria consumoFaixaCategoria = null;

	public FiltrarEmitirHistogramaAguaEconomiaHelper() { }
	
	public FiltrarEmitirHistogramaAguaEconomiaHelper(FiltrarEmitirHistogramaAguaEconomiaHelper filtro) { 
		
		this.opcaoTotalizacao = filtro.getOpcaoTotalizacao();
		this.mesAnoFaturamento = filtro.getMesAnoFaturamento();
		
		this.gerenciaRegional = filtro.getGerenciaRegional();
		this.unidadeNegocio = filtro.getUnidadeNegocio();
		this.eloPolo = filtro.getEloPolo();
		this.localidade = filtro.getLocalidade();
		this.setorComercial = filtro.getSetorComercial();
		this.quadra = filtro.getQuadra();
		this.tarifa = filtro.getTarifa();
		
		this.tipoCategoria = filtro.getTipoCategoria();
		
		this.colecaoCategoria = filtro.getColecaoCategoria();
		this.colecaoSubcategoria = filtro.getColecaoSubcategoria();
		this.indicadorTarifaCategoria = filtro.getIndicadorTarifaCategoria();		
		this.colecaoTarifa = filtro.getColecaoTarifa();
		this.colecaoPerfilImovel = filtro.getColecaoPerfilImovel();
		this.colecaoEsferaPoder = filtro.getColecaoEsferaPoder();
		this.colecaoSituacaoLigacaoAgua = filtro.getColecaoSituacaoLigacaoAgua();

		this.consumo = filtro.getConsumo();
		this.poco = filtro.getPoco();
		this.volumoFixoAgua = filtro.getVolumoFixoAgua();
		
		this.linkedHashMapConsumoFaixaCategoria = filtro.getLinkedHashMapConsumoFaixaCategoria();
		
		this.medicao = filtro.getMedicao();
		this.tipoGroupBy = filtro.getTipoGroupBy();
		this.tipoOrderBy = filtro.getTipoOrderBy();
		this.categoria = filtro.getCategoria();
		this.consumoFaixaCategoria = filtro.getConsumoFaixaCategoria();
	}
	
	
	public String getTipoOrderBy() {
		return tipoOrderBy;
	}

	public void setTipoOrderBy(String tipoOrderBy) {
		this.tipoOrderBy = tipoOrderBy;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public ConsumoFaixaCategoria getConsumoFaixaCategoria() {
		return consumoFaixaCategoria;
	}

	public void setConsumoFaixaCategoria(ConsumoFaixaCategoria consumoFaixaCategoria) {
		this.consumoFaixaCategoria = consumoFaixaCategoria;
	}

	public LinkedHashMap getLinkedHashMapConsumoFaixaCategoria() {
		return linkedHashMapConsumoFaixaCategoria;
	}

	public void setLinkedHashMapConsumoFaixaCategoria(
			LinkedHashMap linkedHashMapConsumoFaixaCategoria) {
		this.linkedHashMapConsumoFaixaCategoria = linkedHashMapConsumoFaixaCategoria;
	}

	public String getTipoGroupBy() {
		return tipoGroupBy;
	}

	public void setTipoGroupBy(String tipoGroupBy) {
		this.tipoGroupBy = tipoGroupBy;
	}

	public CategoriaTipo getTipoCategoria() {
		return tipoCategoria;
	}

	public void setTipoCategoria(CategoriaTipo tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}

	public Collection<Integer> getColecaoCategoria() {
		return colecaoCategoria;
	}

	public void setColecaoCategoria(Collection<Integer> colecaoCategoria) {
		this.colecaoCategoria = colecaoCategoria;
	}

	public Collection<Integer> getColecaoEsferaPoder() {
		return colecaoEsferaPoder;
	}

	public void setColecaoEsferaPoder(Collection<Integer> colecaoEsferaPoder) {
		this.colecaoEsferaPoder = colecaoEsferaPoder;
	}

	public Collection<Integer> getColecaoPerfilImovel() {
		return colecaoPerfilImovel;
	}

	public void setColecaoPerfilImovel(Collection<Integer> colecaoPerfilImovel) {
		this.colecaoPerfilImovel = colecaoPerfilImovel;
	}

	public Collection<Integer> getColecaoSituacaoLigacaoAgua() {
		return colecaoSituacaoLigacaoAgua;
	}

	public void setColecaoSituacaoLigacaoAgua(
			Collection<Integer> colecaoSituacaoLigacaoAgua) {
		this.colecaoSituacaoLigacaoAgua = colecaoSituacaoLigacaoAgua;
	}

	public Collection<Integer> getColecaoTarifa() {
		return colecaoTarifa;
	}

	public void setColecaoTarifa(Collection<Integer> colecaoTarifa) {
		this.colecaoTarifa = colecaoTarifa;
	}

	public Short getConsumo() {
		return consumo;
	}

	public void setConsumo(Short consumo) {
		this.consumo = consumo;
	}

	public Localidade getEloPolo() {
		return eloPolo;
	}

	public void setEloPolo(Localidade eloPolo) {
		this.eloPolo = eloPolo;
	}

	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Short getMedicao() {
		return medicao;
	}

	public void setMedicao(Short medicao) {
		this.medicao = medicao;
	}

	public Integer getMesAnoFaturamento() {
		return mesAnoFaturamento;
	}

	public void setMesAnoFaturamento(Integer mesAnoFaturamento) {
		this.mesAnoFaturamento = mesAnoFaturamento;
	}

	public int getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}

	public void setOpcaoTotalizacao(int opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	public Short getPoco() {
		return poco;
	}

	public void setPoco(Short poco) {
		this.poco = poco;
	}

	public Quadra getQuadra() {
		return quadra;
	}

	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

	public SetorComercial getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}


	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Short getVolumoFixoAgua() {
		return volumoFixoAgua;
	}

	public void setVolumoFixoAgua(Short volumoFixoAgua) {
		this.volumoFixoAgua = volumoFixoAgua;
	}

	public Integer getTarifa() {
		return tarifa;
	}

	public void setTarifa(Integer tarifa) {
		this.tarifa = tarifa;
	}

	public Collection<Integer> getColecaoSubcategoria() {
		return colecaoSubcategoria;
	}

	public void setColecaoSubcategoria(Collection<Integer> colecaoSubcategoria) {
		this.colecaoSubcategoria = colecaoSubcategoria;
	}

	public Integer getIndicadorTarifaCategoria() {
		return indicadorTarifaCategoria;
	}

	public void setIndicadorTarifaCategoria(Integer indicadorTarifaCategoria) {
		this.indicadorTarifaCategoria = indicadorTarifaCategoria;
	}

	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}
	
	

}
