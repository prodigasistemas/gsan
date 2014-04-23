package gcom.gerencial.bean;

import gcom.util.Util;

import java.io.Serializable;
import java.util.Collection;


/**
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * do relatorio para orçamentoe SINP 
 *
 * @author Rafael Pinto
 * @date 20/11/2007
 */
public class FiltrarRelatorioOrcamentoSINPHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer opcaoTotalizacao;
	
	private String opcaoAgrupamento;
	
	private Integer anoMesReferencia;
	private Integer localidade;
	private Integer unidadeNegocio;
	private Integer gerenciaRegional;
	private Integer municipio;
	
	private Collection<Integer> chavesLocalidade;
	private Collection<Integer> chavesUnidade;
	private Collection<Integer> chavesGerencia;
	private Collection<Integer> chavesLocalidadesMunicipio;
	
	private String groupBy = "";
	
	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public Integer getAnoMesReferenciaAnterior() {
		return Util.subtrairMesDoAnoMes(this.anoMesReferencia,1);
	}

	public Integer getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(Integer gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Integer getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Integer localidade) {
		this.localidade = localidade;
	}

	public Integer getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(Integer unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public Integer getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}

	public void setOpcaoTotalizacao(Integer opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	public Collection<Integer> getChavesGerencia() {
		return chavesGerencia;
	}

	public void setChavesGerencia(Collection<Integer> chavesGerencia) {
		this.chavesGerencia = chavesGerencia;
	}

	public Collection<Integer> getChavesLocalidade() {
		return chavesLocalidade;
	}

	public void setChavesLocalidade(Collection<Integer> chavesLocalidade) {
		this.chavesLocalidade = chavesLocalidade;
	}

	public Collection<Integer> getChavesUnidade() {
		return chavesUnidade;
	}

	public void setChavesUnidade(Collection<Integer> chavesUnidade) {
		this.chavesUnidade = chavesUnidade;
	}

	public String getOpcaoAgrupamento() {
		return opcaoAgrupamento;
	}

	public void setOpcaoAgrupamento(String opcaoAgrupamento) {
		this.opcaoAgrupamento = opcaoAgrupamento;
	}

	public Integer getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Integer municipio) {
		this.municipio = municipio;
	}

	public Collection<Integer> getChavesLocalidadesMunicipio() {
		return chavesLocalidadesMunicipio;
	}

	public void setChavesLocalidadesMunicipio(
			Collection<Integer> chavesLocalidadesMunicipio) {
		this.chavesLocalidadesMunicipio = chavesLocalidadesMunicipio;
	}	
}
