package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0492] - Gerar Relatório Acompanhamento de Execução de Ordem de Serviço
 * 
 * @author Rafael Corrêa
 * @date 07/11/2006
 */
public class RelatorioAcompanhamentoExecucaoOSBean implements RelatorioBean {

	private String idOS;

	private String unidadeAtual;

	private String situacao;

	private String tipoServico;

	private String idRA;

	private String endereco;

	private String origem;

	private String dataSolicitacao;

	private String dataProgramacao;

	private String dataEncerramento;

	private String equipe;

	private String diasAtraso;

	private String total;

	private String totalGeral;

	public RelatorioAcompanhamentoExecucaoOSBean(String idOS,
			String unidadeAtual, String situacao, String tipoServico,
			String idRA, String endereco, String origem,
			String dataSolicitacao, String dataProgramacao,
			String dataEncerramento, String equipe, String diasAtraso,
			String total, String totalGeral) {

		this.idOS = idOS;
		this.unidadeAtual = unidadeAtual;
		this.situacao = situacao;
		this.tipoServico = tipoServico;
		this.idRA = idRA;
		this.endereco = endereco;
		this.origem = origem;
		this.dataSolicitacao = dataSolicitacao;
		this.dataProgramacao = dataProgramacao;
		this.dataEncerramento = dataEncerramento;
		this.equipe = equipe;
		this.diasAtraso = diasAtraso;
		this.total = total;
		this.totalGeral = totalGeral;

	}

	/**
	 * @return Retorna o campo totalGeral.
	 */
	public String getTotalGeral() {
		return totalGeral;
	}

	/**
	 * @param totalGeral
	 *            O totalGeral a ser setado.
	 */
	public void setTotalGeral(String totalGeral) {
		this.totalGeral = totalGeral;
	}

	/**
	 * @return Retorna o campo dataEncerramento.
	 */
	public String getDataEncerramento() {
		return dataEncerramento;
	}

	/**
	 * @param dataEncerramento
	 *            O dataEncerramento a ser setado.
	 */
	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	/**
	 * @return Retorna o campo dataProgramacao.
	 */
	public String getDataProgramacao() {
		return dataProgramacao;
	}

	/**
	 * @param dataProgramacao
	 *            O dataProgramacao a ser setado.
	 */
	public void setDataProgramacao(String dataProgramacao) {
		this.dataProgramacao = dataProgramacao;
	}

	/**
	 * @return Retorna o campo dataSolicitacao.
	 */
	public String getDataSolicitacao() {
		return dataSolicitacao;
	}

	/**
	 * @param dataSolicitacao
	 *            O dataSolicitacao a ser setado.
	 */
	public void setDataSolicitacao(String dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	/**
	 * @return Retorna o campo diasAtraso.
	 */
	public String getDiasAtraso() {
		return diasAtraso;
	}

	/**
	 * @param diasAtraso
	 *            O diasAtraso a ser setado.
	 */
	public void setDiasAtraso(String diasAtraso) {
		this.diasAtraso = diasAtraso;
	}

	/**
	 * @return Retorna o campo endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco
	 *            O endereco a ser setado.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return Retorna o campo equipe.
	 */
	public String getEquipe() {
		return equipe;
	}

	/**
	 * @param equipe
	 *            O equipe a ser setado.
	 */
	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}

	/**
	 * @return Retorna o campo idOS.
	 */
	public String getIdOS() {
		return idOS;
	}

	/**
	 * @param idOS
	 *            O idOS a ser setado.
	 */
	public void setIdOS(String idOS) {
		this.idOS = idOS;
	}

	/**
	 * @return Retorna o campo idRA.
	 */
	public String getIdRA() {
		return idRA;
	}

	/**
	 * @param idRA
	 *            O idRA a ser setado.
	 */
	public void setIdRA(String idRA) {
		this.idRA = idRA;
	}

	/**
	 * @return Retorna o campo origem.
	 */
	public String getOrigem() {
		return origem;
	}

	/**
	 * @param origem
	 *            O origem a ser setado.
	 */
	public void setOrigem(String origem) {
		this.origem = origem;
	}

	/**
	 * @return Retorna o campo situacao.
	 */
	public String getSituacao() {
		return situacao;
	}

	/**
	 * @param situacao
	 *            O situacao a ser setado.
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	/**
	 * @return Retorna o campo tipoServico.
	 */
	public String getTipoServico() {
		return tipoServico;
	}

	/**
	 * @param tipoServico
	 *            O tipoServico a ser setado.
	 */
	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	/**
	 * @return Retorna o campo unidadeAtual.
	 */
	public String getUnidadeAtual() {
		return unidadeAtual;
	}

	/**
	 * @param unidadeAtual
	 *            O unidadeAtual a ser setado.
	 */
	public void setUnidadeAtual(String unidadeAtual) {
		this.unidadeAtual = unidadeAtual;
	}

	/**
	 * @return Retorna o campo total.
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            O total a ser setado.
	 */
	public void setTotal(String total) {
		this.total = total;
	}

}
