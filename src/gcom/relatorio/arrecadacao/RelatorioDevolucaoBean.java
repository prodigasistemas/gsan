package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Sávio Luiz
 * @created 8 de Julho de 2005
 */
public class RelatorioDevolucaoBean implements RelatorioBean {

	private String gerenciaRegional;

	private String localidade;

	private String matricula;

	private String inscricao;

	private String arrecadador;

	private String dataDevolucao;

	private String mesAno;

	private String tipoDebito;

	private String valorGuia;

	private String valorDevolucao;

	private String situacaoAtual;

	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAguaBean
	 * 
	 * @param codigo
	 *            Description of the Parameter
	 * @param nome
	 *            Description of the Parameter
	 * @param municipio
	 *            Description of the Parameter
	 * @param codPref
	 *            Description of the Parameter
	 * @param indicadorUso
	 *            Description of the Parameter
	 */
	public RelatorioDevolucaoBean(String gerenciaRegional, String localidade,
			String matricula, String inscricao, String arrecadador,
			String dataDevolucao, String mesAno, String tipoDebito,
			String valorGuia, String valorDevolucao, String situacaoAtual) {
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.matricula = matricula;
		this.inscricao = inscricao;
		this.arrecadador = arrecadador;
		this.dataDevolucao = dataDevolucao;
		this.mesAno = mesAno;
		this.tipoDebito = tipoDebito;
		this.valorGuia = valorGuia;
		this.valorDevolucao = valorDevolucao;
		this.situacaoAtual = situacaoAtual;
	}

	public String getArrecadador() {
		return arrecadador;
	}

	public void setArrecadador(String arrecadador) {
		this.arrecadador = arrecadador;
	}

	public String getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getSituacaoAtual() {
		return situacaoAtual;
	}

	public void setSituacaoAtual(String situacaoAtual) {
		this.situacaoAtual = situacaoAtual;
	}

	public String getTipoDebito() {
		return tipoDebito;
	}

	public void setTipoDebito(String tipoDebito) {
		this.tipoDebito = tipoDebito;
	}

	public String getValorDevolucao() {
		return valorDevolucao;
	}

	public void setValorDevolucao(String valorDevolucao) {
		this.valorDevolucao = valorDevolucao;
	}

	public String getValorGuia() {
		return valorGuia;
	}

	public void setValorGuia(String valorGuia) {
		this.valorGuia = valorGuia;
	}

}
