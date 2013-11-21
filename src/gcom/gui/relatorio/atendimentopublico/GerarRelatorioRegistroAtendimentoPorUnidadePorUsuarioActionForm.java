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
public class GerarRelatorioRegistroAtendimentoPorUnidadePorUsuarioActionForm extends ValidatorForm {
	
	private static final long serialVersionUID = 1L;
	
	private String dataAtendimentoInicial;
	private String dataAtendimentoFinal;
	private String situacaoRA;
	
	private String idUnidadeAtendimento;
	private String nomeUnidadeAtendimento;
	

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
	 * @return Retorna o campo idUnidade.
	 */
	public String getIdUnidadeAtendimento() {
		return idUnidadeAtendimento;
	}

	/**
	 * @param idUnidade O idUnidade a ser setado.
	 */
	public void setIdUnidadeAtendimento(String idUnidadeAtendimento) {
		this.idUnidadeAtendimento = idUnidadeAtendimento;
	}


	/**
	 * @return Retorna o campo nomeUnidade.
	 */
	public String getNomeUnidadeAtendimento() {
		return nomeUnidadeAtendimento;
	}

	/**
	 * @param nomeUnidade O nomeUnidade a ser setado.
	 */
	public void setNomeUnidadeAtendimento(String nomeUnidadeAtendimento) {
		this.nomeUnidadeAtendimento = nomeUnidadeAtendimento;
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

}
