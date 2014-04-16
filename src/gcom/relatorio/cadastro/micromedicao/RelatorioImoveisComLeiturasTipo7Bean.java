package gcom.relatorio.cadastro.micromedicao;

import gcom.relatorio.RelatorioBean;

/**
 * [UC1180] Gerar Relatório de Imóveis Com Leituras [SB0001] Gerar relatório
 * tipo 1
 * 
 * @author Magno Gouveia
 * @date 03/06/2011
 */
public class RelatorioImoveisComLeiturasTipo7Bean implements RelatorioBean {

	private static final long serialVersionUID = 1L;

	private String mesAnoReferencia;

	private Integer grupoFaturamento;

	private String empresa;

	private Integer rota;

	private String leiturista;

	private Integer qtdEnviados;

	private Integer qtdRecebidos;

	private Integer diferencaRecebidosEnviados;

	private Integer qtdMedidosNaoImpressos;

	private Integer qtdNaoMedidosNaoImpressos;

	public RelatorioImoveisComLeiturasTipo7Bean(String mesAnoReferencia,
			Integer grupoFaturamento, String empresa, Integer rota,
			String leiturista, Integer qtdEnviados, Integer qtdRecebidos,
			Integer diferencaRecebidosEnviados, Integer qtdMedidosNaoImpressos,
			Integer qtdNaoMedidosNaoImpressos) {
		this.mesAnoReferencia = mesAnoReferencia;
		this.grupoFaturamento = grupoFaturamento;
		this.empresa = empresa;
		this.rota = rota;
		this.leiturista = leiturista;
		this.qtdEnviados = qtdEnviados;
		this.qtdRecebidos = qtdRecebidos;
		this.diferencaRecebidosEnviados = diferencaRecebidosEnviados;
		this.qtdMedidosNaoImpressos = qtdMedidosNaoImpressos;
		this.qtdNaoMedidosNaoImpressos = qtdNaoMedidosNaoImpressos;
	}

	public Integer getDiferencaRecebidosEnviados() {
		return diferencaRecebidosEnviados;
	}

	public void setDiferencaRecebidosEnviados(Integer diferencaRecebidosEnviados) {
		this.diferencaRecebidosEnviados = diferencaRecebidosEnviados;
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

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	public Integer getQtdEnviados() {
		return qtdEnviados;
	}

	public void setQtdEnviados(Integer qtdEnviados) {
		this.qtdEnviados = qtdEnviados;
	}

	public Integer getQtdMedidosNaoImpressos() {
		return qtdMedidosNaoImpressos;
	}

	public void setQtdMedidosNaoImpressos(Integer qtdMedidosNaoImpressos) {
		this.qtdMedidosNaoImpressos = qtdMedidosNaoImpressos;
	}

	public Integer getQtdNaoMedidosNaoImpressos() {
		return qtdNaoMedidosNaoImpressos;
	}

	public void setQtdNaoMedidosNaoImpressos(Integer qtdNaoMedidosNaoImpressos) {
		this.qtdNaoMedidosNaoImpressos = qtdNaoMedidosNaoImpressos;
	}

	public Integer getQtdRecebidos() {
		return qtdRecebidos;
	}

	public void setQtdRecebidos(Integer qtdRecebidos) {
		this.qtdRecebidos = qtdRecebidos;
	}

	public Integer getRota() {
		return rota;
	}

	public void setRota(Integer rota) {
		this.rota = rota;
	}

}
