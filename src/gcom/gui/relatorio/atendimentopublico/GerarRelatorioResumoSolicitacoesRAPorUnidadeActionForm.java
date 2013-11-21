package gcom.gui.relatorio.atendimentopublico;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0497] Gerar Relatorio Resumo de Solicitacoes de RA por Unidade
 * 
 * @see gcom.gui.relatorio.atendimentopublico.GerarRelatorioResumoSolicitacoesRAPorUnidadeAction
 * @see gcom.gui.relatorio.atendimentopublico.ExibirGerarRelatorioResumoSolicitacoesRAPorUnidadeAction
 * @see gcom.relatorio.atendimentopublico.RelatorioResumoSolicitacoesRAPorUnidade
 * 
 * @author Victor Cisneiros
 * @date 20/06/2008
 */
public class GerarRelatorioResumoSolicitacoesRAPorUnidadeActionForm extends ValidatorForm {
	
	private static final long serialVersionUID = 1L;
	
	private String dataAtendimentoInicial;
	private String dataAtendimentoFinal;
	private String situacaoRA;
	
	private String[] solicitacaoTipo;
	private String[] especificacao;
	
	private String idUnidade;
	private String nomeUnidade;
	
	private String idUnidadeSuperior;
	private String nomeUnidadeSuperior;
	
	private String idMunicipio;
	private String nomeMunicipio;
	
	private String idBairro;
	private String nomeBairro;
	
	private String selectedSolicitacaoTipoSize = "0";

	/**
	 * @return Retorna o campo dataAtendimentoFinal.
	 */
	public String getDataAtendimentoFinal() {
		return dataAtendimentoFinal;
	}

	/**
	 * @param dataAtendimentoFinal O dataAtendimentoFinal a ser setado.
	 */
	public void setDataAtendimentoFinal(String dataAtendimentoFinal) {
		this.dataAtendimentoFinal = dataAtendimentoFinal;
	}

	/**
	 * @return Retorna o campo dataAtendimentoInicial.
	 */
	public String getDataAtendimentoInicial() {
		return dataAtendimentoInicial;
	}

	/**
	 * @param dataAtendimentoInicial O dataAtendimentoInicial a ser setado.
	 */
	public void setDataAtendimentoInicial(String dataAtendimentoInicial) {
		this.dataAtendimentoInicial = dataAtendimentoInicial;
	}

	/**
	 * @return Retorna o campo especificacao.
	 */
	public String[] getEspecificacao() {
		return especificacao;
	}

	/**
	 * @param especificacao O especificacao a ser setado.
	 */
	public void setEspecificacao(String[] especificacao) {
		this.especificacao = especificacao;
	}

	/**
	 * @return Retorna o campo idBairro.
	 */
	public String getIdBairro() {
		return idBairro;
	}

	/**
	 * @param idBairro O idBairro a ser setado.
	 */
	public void setIdBairro(String idBairro) {
		this.idBairro = idBairro;
	}

	/**
	 * @return Retorna o campo idMunicipio.
	 */
	public String getIdMunicipio() {
		return idMunicipio;
	}

	/**
	 * @param idMunicipio O idMunicipio a ser setado.
	 */
	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	/**
	 * @return Retorna o campo idUnidade.
	 */
	public String getIdUnidade() {
		return idUnidade;
	}

	/**
	 * @param idUnidade O idUnidade a ser setado.
	 */
	public void setIdUnidade(String idUnidade) {
		this.idUnidade = idUnidade;
	}

	/**
	 * @return Retorna o campo idUnidadeSuperior.
	 */
	public String getIdUnidadeSuperior() {
		return idUnidadeSuperior;
	}

	/**
	 * @param idUnidadeSuperior O idUnidadeSuperior a ser setado.
	 */
	public void setIdUnidadeSuperior(String idUnidadeSuperior) {
		this.idUnidadeSuperior = idUnidadeSuperior;
	}

	/**
	 * @return Retorna o campo nomeBairro.
	 */
	public String getNomeBairro() {
		return nomeBairro;
	}

	/**
	 * @param nomeBairro O nomeBairro a ser setado.
	 */
	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	/**
	 * @return Retorna o campo nomeMunicipio.
	 */
	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	/**
	 * @param nomeMunicipio O nomeMunicipio a ser setado.
	 */
	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	/**
	 * @return Retorna o campo nomeUnidade.
	 */
	public String getNomeUnidade() {
		return nomeUnidade;
	}

	/**
	 * @param nomeUnidade O nomeUnidade a ser setado.
	 */
	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}

	/**
	 * @return Retorna o campo nomeUnidadeSuperior.
	 */
	public String getNomeUnidadeSuperior() {
		return nomeUnidadeSuperior;
	}

	/**
	 * @param nomeUnidadeSuperior O nomeUnidadeSuperior a ser setado.
	 */
	public void setNomeUnidadeSuperior(String nomeUnidadeSuperior) {
		this.nomeUnidadeSuperior = nomeUnidadeSuperior;
	}

	/**
	 * @return Retorna o campo situacaoRA.
	 */
	public String getSituacaoRA() {
		return situacaoRA;
	}

	/**
	 * @param situacaoRA O situacaoRA a ser setado.
	 */
	public void setSituacaoRA(String situacaoRA) {
		this.situacaoRA = situacaoRA;
	}

	/**
	 * @return Retorna o campo solicitacaoTipo.
	 */
	public String[] getSolicitacaoTipo() {
		return solicitacaoTipo;
	}

	/**
	 * @param solicitacaoTipo O solicitacaoTipo a ser setado.
	 */
	public void setSolicitacaoTipo(String[] solicitacaoTipo) {
		this.solicitacaoTipo = solicitacaoTipo;
	}

	/**
	 * @return Retorna o campo selectedSolicitacaoTipoSize.
	 */
	public String getSelectedSolicitacaoTipoSize() {
		return selectedSolicitacaoTipoSize;
	}

	/**
	 * @param selectedSolicitacaoTipoSize O selectedSolicitacaoTipoSize a ser setado.
	 */
	public void setSelectedSolicitacaoTipoSize(String selectedSolicitacaoTipoSize) {
		this.selectedSolicitacaoTipoSize = selectedSolicitacaoTipoSize;
	}

}
