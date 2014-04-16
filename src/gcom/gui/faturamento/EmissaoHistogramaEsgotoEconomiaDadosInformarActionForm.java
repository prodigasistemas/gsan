package gcom.gui.faturamento;


import gcom.faturamento.ConsumoFaixaCategoria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC00605] Informar Dados para Emissão do Histograma de Esgoto por Economia
 * 
 * @author Rafael Pinto
 *
 * @date 07/11/2007
 */
			 
public class EmissaoHistogramaEsgotoEconomiaDadosInformarActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String indicadorTarifaCategoria;
	private String opcaoTotalizacao;
	private String mesAnoFaturamento;
	private String eloPolo;
	private String nomeEloPolo;
	private String localidade;
	private String nomeLocalidade;
	private String setorComercial;
	private String nomeSetorComercial;
	private String quadra;
	private String nomeQuadra;
	private String consumo;
	private String poco;
	private String volumoFixoEsgoto;
	
	private String gerenciaRegional;
	private String unidadeNegocio;
	private String tipoCategoria;
	
	private String[] categoria;
	private String[] subcategoria;
	private String[] tarifa;
	private String[] perfilImovel;
	private String[] esferaPoder;
	private String[] situacaoLigacaoEsgoto;
	private String[] perfilLigacaoEsgoto;
	
	//Parte do Popup
	private String subTituloFaixaConsumo;
	private String limiteInferiorFaixa;
	private String limiteSuperiorFaixa;
	private String tipoConsumoCategoria;
	
	private LinkedHashMap<String,Collection<ConsumoFaixaCategoria>> linkedHashMapConsumoFaixaCategoria = 
		new LinkedHashMap<String,Collection<ConsumoFaixaCategoria>>();
	
	private Collection<ConsumoFaixaCategoria> colecaoConsumoFaixaCategoria = new ArrayList();
	
	private String tamanhoColecao = "0";
	
	
	
	public Collection<ConsumoFaixaCategoria> getColecaoConsumoFaixaCategoria() {
		return colecaoConsumoFaixaCategoria;
	}

	public void setColecaoConsumoFaixaCategoria(
			Collection<ConsumoFaixaCategoria> colecaoConsumoFaixaCategoria) {
		this.colecaoConsumoFaixaCategoria = colecaoConsumoFaixaCategoria;
	}

	public void setLinkedHashMapConsumoFaixaCategoria(
			LinkedHashMap<String, Collection<ConsumoFaixaCategoria>> linkedHashMapConsumoFaixaCategoria) {
		this.linkedHashMapConsumoFaixaCategoria = linkedHashMapConsumoFaixaCategoria;
	}

	public LinkedHashMap getLinkedHashMapConsumoFaixaCategoria() {
		return linkedHashMapConsumoFaixaCategoria;
	}

	public String getTipoConsumoCategoria() {
		return tipoConsumoCategoria;
	}

	public void setTipoConsumoCategoria(String tipoConsumoCategoria) {
		this.tipoConsumoCategoria = tipoConsumoCategoria;
	}

	public String getTamanhoColecao() {
		return tamanhoColecao;
	}
	
	public void setTamanhoColecao(String tamanhoColecao) {
		this.tamanhoColecao = tamanhoColecao;
	}
	
	public String getLimiteInferiorFaixa() {
		return limiteInferiorFaixa;
	}
	public void setLimiteInferiorFaixa(String limiteInferiorFaixa) {
		this.limiteInferiorFaixa = limiteInferiorFaixa;
	}
	public String getLimiteSuperiorFaixa() {
		return limiteSuperiorFaixa;
	}
	public void setLimiteSuperiorFaixa(String limiteSuperiorFaixa) {
		this.limiteSuperiorFaixa = limiteSuperiorFaixa;
	}
	public String getSubTituloFaixaConsumo() {
		return subTituloFaixaConsumo;
	}
	public void setSubTituloFaixaConsumo(String subTituloFaixaConsumo) {
		this.subTituloFaixaConsumo = subTituloFaixaConsumo;
	}

	public String[] getCategoria() {
		return categoria;
	}
	public void setCategoria(String[] categoria) {
		this.categoria = categoria;
	}
	public String[] getEsferaPoder() {
		return esferaPoder;
	}
	public void setEsferaPoder(String[] esferaPoder) {
		this.esferaPoder = esferaPoder;
	}
	public String[] getPerfilImovel() {
		return perfilImovel;
	}
	public void setPerfilImovel(String[] perfilImovel) {
		this.perfilImovel = perfilImovel;
	}
	public String[] getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}
	public void setSituacaoLigacaoEsgoto(String[] situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}
	public String[] getTarifa() {
		return tarifa;
	}
	public void setTarifa(String[] tarifa) {
		this.tarifa = tarifa;
	}
	public String getTipoCategoria() {
		return tipoCategoria;
	}
	public void setTipoCategoria(String tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}
	public String getConsumo() {
		return consumo;
	}
	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}
	public String getEloPolo() {
		return eloPolo;
	}
	public void setEloPolo(String eloPolo) {
		this.eloPolo = eloPolo;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getMesAnoFaturamento() {
		return mesAnoFaturamento;
	}
	
	public void setMesAnoFaturamento(String mesAnoFaturamento) {
		this.mesAnoFaturamento = mesAnoFaturamento;
	}
	public String getNomeEloPolo() {
		return nomeEloPolo;
	}
	public void setNomeEloPolo(String nomeEloPolo) {
		this.nomeEloPolo = nomeEloPolo;
	}
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	public String getNomeQuadra() {
		return nomeQuadra;
	}
	public void setNomeQuadra(String nomeQuadra) {
		this.nomeQuadra = nomeQuadra;
	}
	public String getNomeSetorComercial() {
		return nomeSetorComercial;
	}
	public void setNomeSetorComercial(String nomeSetorComercial) {
		this.nomeSetorComercial = nomeSetorComercial;
	}
	public String getPoco() {
		return poco;
	}
	public void setPoco(String poco) {
		this.poco = poco;
	}
	public String getQuadra() {
		return quadra;
	}
	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}
	public String getSetorComercial() {
		return setorComercial;
	}
	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}
	public String getVolumoFixoEsgoto() {
		return volumoFixoEsgoto;
	}
	public void setVolumoFixoEsgoto(String volumoFixoEsgoto) {
		this.volumoFixoEsgoto = volumoFixoEsgoto;
	}
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}
	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}
	
	public String[] getPerfilLigacaoEsgoto() {
		return perfilLigacaoEsgoto;
	}

	public void setPerfilLigacaoEsgoto(String[] perfilLigacaoEsgoto) {
		this.perfilLigacaoEsgoto = perfilLigacaoEsgoto;
	}

	public String getIndicadorTarifaCategoria() {
		return indicadorTarifaCategoria;
	}

	public void setIndicadorTarifaCategoria(String indicadorTarifaCategoria) {
		this.indicadorTarifaCategoria = indicadorTarifaCategoria;
	}

	public String[] getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(String[] subcategoria) {
		this.subcategoria = subcategoria;
	}

}
