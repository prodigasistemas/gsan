package gcom.relatorio.cadastro.micromedicao;

import java.io.Serializable;

/**
 * [UC1180] Classe que irá auxiliar no formato de entrada da pesquisa do
 * relatorio de imoveis com leituristas
 * 
 * @author Magno Gouveia
 * @date 03/06/2011
 */
public class FiltrarRelatorioImoveisComLeiturasHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	private String mesAnoReferencia;

	private Integer grupoFaturamento;

	private Integer empresa;

	private Integer leiturista;

	private Integer localidadeInicial;

	private Integer setorComercialInicial;

	private Integer rotaInicial;

	private Integer localidadeFinal;

	private Integer setorComercialFinal;

	private Integer rotaFinal;

	public FiltrarRelatorioImoveisComLeiturasHelper() {
	}

	public FiltrarRelatorioImoveisComLeiturasHelper(String mesAnoReferencia,
			Integer grupoFaturamento, Integer empresa, Integer leiturista,
			Integer localidadeInicial, Integer setorComercialInicial,
			Integer rotaInicial, Integer localidadeFinal,
			Integer setorComercialFinal, Integer rotaFinal) {
		this.mesAnoReferencia = mesAnoReferencia;
		this.grupoFaturamento = grupoFaturamento;
		this.empresa = empresa;
		this.leiturista = leiturista;
		this.localidadeInicial = localidadeInicial;
		this.setorComercialInicial = setorComercialInicial;
		this.rotaInicial = rotaInicial;
		this.localidadeFinal = localidadeFinal;
		this.setorComercialFinal = setorComercialFinal;
		this.rotaFinal = rotaFinal;
	}

	public Integer getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Integer empresa) {
		this.empresa = empresa;
	}

	public Integer getGrupoFaturamento() {
		return grupoFaturamento;
	}

	public void setGrupoFaturamento(Integer grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}

	public Integer getLeiturista() {
		return leiturista;
	}

	public void setLeiturista(Integer leiturista) {
		this.leiturista = leiturista;
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

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	public Integer getRotaFinal() {
		return rotaFinal;
	}

	public void setRotaFinal(Integer rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	public Integer getRotaInicial() {
		return rotaInicial;
	}

	public void setRotaInicial(Integer rotaInicial) {
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
}
