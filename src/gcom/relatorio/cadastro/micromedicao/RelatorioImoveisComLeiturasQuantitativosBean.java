package gcom.relatorio.cadastro.micromedicao;

import gcom.relatorio.RelatorioBean;

/**
 * [UC1180] Gerar Relatório de Imóveis Com Leituras
 * [SB0001] Gerar relatório tipo 1
 * 
 * @author Magno Gouveia
 * @date 03/06/2011
 */
public class RelatorioImoveisComLeiturasQuantitativosBean implements RelatorioBean {

	private static final long serialVersionUID = 1L;

	private String mesAnoReferencia;

	private Integer grupoFaturamento;

	private String empresa;

	private String localidade;

	private String setorComercial;

	private Integer rota;

	private String leiturista;

	private Integer qtdImoveis;

	public RelatorioImoveisComLeiturasQuantitativosBean() {
	}

	public RelatorioImoveisComLeiturasQuantitativosBean(String mesAnoReferencia,
			Integer grupoFaturamento, String empresa, String localidade,
			String setorComercial, Integer rota, String leiturista,
			Integer qtdImoveis) {
		this.mesAnoReferencia = mesAnoReferencia;
		this.grupoFaturamento = grupoFaturamento;
		this.empresa = empresa;
		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.rota = rota;
		this.leiturista = leiturista;
		this.qtdImoveis = qtdImoveis;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Integer getGrupoFaturamento() {
		return grupoFaturamento;
	}

	public void setGrupoFaturamento(Integer grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}

	public String getLeiturista() {
		return leiturista;
	}

	public void setLeiturista(String leiturista) {
		this.leiturista = leiturista;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	public Integer getQtdImoveis() {
		return qtdImoveis;
	}

	public void setQtdImoveis(Integer qtdImoveis) {
		this.qtdImoveis = qtdImoveis;
	}

	public Integer getRota() {
		return rota;
	}

	public void setRota(Integer rota) {
		this.rota = rota;
	}

	public String getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}
}
