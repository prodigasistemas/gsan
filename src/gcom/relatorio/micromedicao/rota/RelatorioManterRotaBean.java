package gcom.relatorio.micromedicao.rota;

import gcom.relatorio.RelatorioBean;

/**
 * Title: GCOM
 * 
 * @author Rafael Corrêa
 * @date 17/07/2006
 */

public class RelatorioManterRotaBean implements RelatorioBean {

	private String codigo;

	private String localidade;

	private String setorComercial;

	private String grupoFaturamento;

	private String grupoCobranca;

	private String tipoLeitura;

	private String empresaLeitura;

	private String indicadorUso;
	
	private String empresaCobranca;
	
	private String empresaEntregaContas;



	public RelatorioManterRotaBean(String codigo, String localidade,
			String setorComercial, String grupoFaturamento,
			String grupoCobranca, String tipoLeitura, String empresaLeitura,
			String empresaCobranca, String empresaEntregaContas,
			String indicadorUso) {
		this.codigo = codigo;
		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.grupoFaturamento = grupoFaturamento;
		this.grupoCobranca = grupoCobranca;
		this.tipoLeitura = tipoLeitura;
		this.empresaLeitura = empresaLeitura;
		this.empresaCobranca = empresaCobranca;
		this.empresaEntregaContas = empresaEntregaContas;
		this.indicadorUso = indicadorUso;
	}

	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEmpresaLeitura() {
		return empresaLeitura;
	}

	public void setEmpresaLeitura(String empresaLeitura) {
		this.empresaLeitura = empresaLeitura;
	}

	public String getGrupoCobranca() {
		return grupoCobranca;
	}

	public void setGrupoCobranca(String grupoCobranca) {
		this.grupoCobranca = grupoCobranca;
	}

	public String getGrupoFaturamento() {
		return grupoFaturamento;
	}

	public void setGrupoFaturamento(String grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	public String getTipoLeitura() {
		return tipoLeitura;
	}

	public void setTipoLeitura(String tipoLeitura) {
		this.tipoLeitura = tipoLeitura;
	}

	public String getEmpresaCobranca() {
		return empresaCobranca;
	}

	public void setEmpresaCobranca(String empresaCobranca) {
		this.empresaCobranca = empresaCobranca;
	}

	public String getEmpresaEntregaContas() {
		return empresaEntregaContas;
	}

	public void setEmpresaEntregaContas(String empresaEntregaContas) {
		this.empresaEntregaContas = empresaEntregaContas;
	}

}
