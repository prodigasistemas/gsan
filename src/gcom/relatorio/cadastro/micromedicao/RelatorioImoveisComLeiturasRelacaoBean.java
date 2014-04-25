package gcom.relatorio.cadastro.micromedicao;

import gcom.relatorio.RelatorioBean;

/**
 * <b>[UC1180] Relatório Imóveis com Leituristas</b>:
 *
 * <ul>
 * 		<li> 
 * 			<b>[SB0001] Gerar Relatório do Tipo 1</b>: Quantitativo de imóveis com leituras através da WEB
 * 		</li>
 * 		<li> 
 * 			<b>[SB0002] Gerar Relatório do Tipo 2</b>: Quantitativo de imóveis sem leituras através da ISC e WEB
 * 		</li>
 * 		<li> 
 * 			<b>[SB0003] Gerar Relatório do Tipo 3</b>: Quantitativo de imóveis que estão na rota mas não foram recebidos através da ISC</p>
 * 		</li>
 * 		<li> 
 * 			<b>[SB0004] Gerar Relatório do Tipo 4</b>: Relação de imóveis com leituras não recebidas através da ISC</b>
 * 		</li>
 * 		<li> 
 * 			<b>[SB0005] Gerar Relatório do Tipo 5</b>: Relação de imóveis não medidos que não estão na rota de ISC</b>
 * 		</li>
 * 		<li> 
 * 			<b>[SB0006] Gerar Relatório do Tipo 6</b>: Relação de imóveis medidos que não estão na rota de ISC</b>
 * 		</li>
 * </ul>
 * 
 * @author Magno Gouveia
 * @date 03/06/2011
 */
public class RelatorioImoveisComLeiturasRelacaoBean implements RelatorioBean {

	private static final long serialVersionUID = 1L;

	private String mesAnoReferencia;

	private Integer grupoFaturamento;

	private String empresa;

	private String localidade;

	private String setorComercial;

	private Integer rota;

	private String leiturista;

	private String imovel;

	public RelatorioImoveisComLeiturasRelacaoBean(String mesAnoReferencia,
			Integer grupoFaturamento, String empresa, String localidade,
			String setorComercial, Integer rota, String leiturista,
			String imovel) {
		this.mesAnoReferencia = mesAnoReferencia;
		this.grupoFaturamento = grupoFaturamento;
		this.empresa = empresa;
		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.rota = rota;
		this.leiturista = leiturista;
		this.imovel = imovel;
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

	public String getImovel() {
		return imovel;
	}

	public void setImovel(String imovel) {
		this.imovel = imovel;
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
