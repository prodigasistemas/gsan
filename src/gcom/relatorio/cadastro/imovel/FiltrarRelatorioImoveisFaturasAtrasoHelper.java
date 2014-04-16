package gcom.relatorio.cadastro.imovel;

import java.io.Serializable;
import java.util.Collection;


/**
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * do relatorio de imoveis com faturas em atraso 
 *
 * @author Bruno Barros
 * @date 30/11/2007
 */
public class FiltrarRelatorioImoveisFaturasAtrasoHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer unidadeNegocio;
	private Integer gerenciaRegional;
	
	private Integer localidadeInicial;
	private Integer setorComercialInicial;
	private Short rotaInicial;
	private Integer sequencialRotalInicial;

	private Integer localidadeFinal;
	private Integer setorComercialFinal;
	private Short rotaFinal;
	private Integer sequencialRotalFinal;

	private Collection<Integer> situacaoLigacaoAgua;
	private Collection<Integer> categorias;
	private Collection<Integer> perfisImovel;
	
	private Integer quantidadeFaturasAtrasoInicial;
	private Integer quantidadeFaturasAtrasoFinal;	
	
	private Integer referenciaFaturasAtrasoInicial;
	private Integer referenciaFaturasAtrasoFinal;
	
	private Float valorFaturasAtrasoInicial;
	private Float valorFaturasAtrasoFinal;	
	
	private Integer situacaoCobranca;
	private Integer clienteSuperior;
	private Integer cliente;
	private Integer responsavel;
	private Integer tipoRelacao;
	
	private Integer criterioFiltro;

	private String hidrometro;
	
	private Collection<Integer> esferaPoder;
	
	public String getHidrometro() {
		return hidrometro;
	}

	public void setHidrometro(String hidrometro) {
		this.hidrometro = hidrometro;
	}

	public Collection<Integer> getEsferaPoder() {
		return esferaPoder;
	}

	public void setEsferaPoder(Collection<Integer> esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	public Integer getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(Integer gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Integer getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(Integer unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Integer getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(Integer localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public Integer getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(Integer localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public Short getRotaFinal() {
		return rotaFinal;
	}

	public void setRotaFinal(Short rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	public Short getRotaInicial() {
		return rotaInicial;
	}

	public void setRotaInicial(Short rotaInicial) {
		this.rotaInicial = rotaInicial;
	}

	public Integer getSetorComercialFinal() {
		return setorComercialFinal;
	}

	public void setSetorComercialFinal(Integer setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}

	public Integer getSetorComercialInicial() {
		return setorComercialInicial;
	}

	public void setSetorComercialInicial(Integer setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}

	public Collection<Integer> getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	public void setSituacaoLigacaoAgua(Collection<Integer> situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	public Integer getSequencialRotalFinal() {
		return sequencialRotalFinal;
	}

	public void setSequencialRotalFinal(Integer sequencialRotalFinal) {
		this.sequencialRotalFinal = sequencialRotalFinal;
	}

	public Integer getSequencialRotalInicial() {
		return sequencialRotalInicial;
	}

	public void setSequencialRotalInicial(Integer sequencialRotalInicial) {
		this.sequencialRotalInicial = sequencialRotalInicial;
	}

	
	public Collection<Integer> getCategorias() {
	
		return categorias;
	}

	
	public void setCategorias(Collection<Integer> categorias) {
	
		this.categorias = categorias;
	}

	
	public Integer getQuantidadeFaturasAtrasoInicial() {
	
		return quantidadeFaturasAtrasoInicial;
	}

	
	public void setQuantidadeFaturasAtrasoInicial(
			Integer quantidadeFaturasAtrasoInicial) {
	
		this.quantidadeFaturasAtrasoInicial = quantidadeFaturasAtrasoInicial;
	}

	
	public Integer getQuantidadeFaturasAtrasoFinal() {
	
		return quantidadeFaturasAtrasoFinal;
	}

	
	public void setQuantidadeFaturasAtrasoFinal(Integer quantidadeFaturasAtrasoFinal) {
	
		this.quantidadeFaturasAtrasoFinal = quantidadeFaturasAtrasoFinal;
	}

	
	public Integer getReferenciaFaturasAtrasoFinal() {
	
		return referenciaFaturasAtrasoFinal;
	}

	
	public void setReferenciaFaturasAtrasoFinal(Integer referenciaFaturasAtrasoFinal) {
	
		this.referenciaFaturasAtrasoFinal = referenciaFaturasAtrasoFinal;
	}

	
	public Integer getReferenciaFaturasAtrasoInicial() {
	
		return referenciaFaturasAtrasoInicial;
	}

	
	public void setReferenciaFaturasAtrasoInicial(
			Integer referenciaFaturasAtrasoInicial) {
	
		this.referenciaFaturasAtrasoInicial = referenciaFaturasAtrasoInicial;
	}

	
	public Float getValorFaturasAtrasoFinal() {
	
		return valorFaturasAtrasoFinal;
	}

	
	public void setValorFaturasAtrasoFinal(Float valorFaturasAtrasoFinal) {
	
		this.valorFaturasAtrasoFinal = valorFaturasAtrasoFinal;
	}

	
	public Float getValorFaturasAtrasoInicial() {
	
		return valorFaturasAtrasoInicial;
	}

	
	public void setValorFaturasAtrasoInicial(Float valorFaturasAtrasoInicial) {
	
		this.valorFaturasAtrasoInicial = valorFaturasAtrasoInicial;
	}

	public Integer getSituacaoCobranca() {
		return situacaoCobranca;
	}

	public void setSituacaoCobranca(Integer situacaoCobranca) {
		this.situacaoCobranca = situacaoCobranca;
	}

	public Integer getClienteSuperior() {
		return clienteSuperior;
	}

	public void setClienteSuperior(Integer clienteSuperior) {
		this.clienteSuperior = clienteSuperior;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public Integer getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Integer responsavel) {
		this.responsavel = responsavel;
	}

	public Integer getTipoRelacao() {
		return tipoRelacao;
	}

	public void setTipoRelacao(Integer tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}

	public Integer getCriterioFiltro() {
		return criterioFiltro;
	}

	public void setCriterioFiltro(Integer criterioFiltro) {
		this.criterioFiltro = criterioFiltro;
	}

	public Collection<Integer> getPerfisImovel() {
		return perfisImovel;
	}

	public void setPerfisImovel(Collection<Integer> perfisImovel) {
		this.perfisImovel = perfisImovel;
	}
}
