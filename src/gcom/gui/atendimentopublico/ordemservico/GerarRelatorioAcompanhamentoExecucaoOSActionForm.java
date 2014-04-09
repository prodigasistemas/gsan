package gcom.gui.atendimentopublico.ordemservico;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0450] Pesquisar Ordem Serviço
 * 
 * @author Rafael Pinto
 *
 * @date 15/06/2006
 */
public class GerarRelatorioAcompanhamentoExecucaoOSActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String origemServico;
	private String situacaoOrdemServico;
	
	private String[] tipoServico;
	private String[] tipoServicoSelecionados;
	
	private String idUnidadeAtendimento;
	private String descricaoUnidadeAtendimento;
	
	private String idUnidadeAtual;
	private String descricaoUnidadeAtual;
	
	private String idUnidadeEncerramento;
	private String descricaoUnidadeEncerramento;
	
	private String periodoAtendimentoInicial;
	private String periodoAtendimentoFinal;
	private String periodoEncerramentoInicial;
	private String periodoEncerramentoFinal;
	
	private String idEquipeProgramacao;
	private String descricaoEquipeProgramacao;
	private String idEquipeExecucao;
	private String descricaoEquipeExecucao;
	private String tipoOrdenacao;

	public void resetOS() {
		
		origemServico= null;
		situacaoOrdemServico= null;
		
		idUnidadeAtendimento= null;
		descricaoUnidadeAtendimento= null;
		
		idUnidadeAtual= null;
		descricaoUnidadeAtual= null;
		
		idUnidadeEncerramento= null;
		descricaoUnidadeEncerramento= null;
		
		periodoAtendimentoInicial = null;
		periodoAtendimentoFinal = null;
		periodoEncerramentoInicial = null;
		periodoEncerramentoFinal = null;
		
		idEquipeProgramacao = null;
		descricaoEquipeProgramacao = null;
		idEquipeExecucao = null;
		descricaoEquipeExecucao = null;
		tipoOrdenacao = null;

	}

	/**
	 * @return Retorna o campo descricaoEquipeExecucao.
	 */
	public String getDescricaoEquipeExecucao() {
		return descricaoEquipeExecucao;
	}

	/**
	 * @param descricaoEquipeExecucao O descricaoEquipeExecucao a ser setado.
	 */
	public void setDescricaoEquipeExecucao(String descricaoEquipeExecucao) {
		this.descricaoEquipeExecucao = descricaoEquipeExecucao;
	}

	/**
	 * @return Retorna o campo descricaoEquipeProgramacao.
	 */
	public String getDescricaoEquipeProgramacao() {
		return descricaoEquipeProgramacao;
	}

	/**
	 * @param descricaoEquipeProgramacao O descricaoEquipeProgramacao a ser setado.
	 */
	public void setDescricaoEquipeProgramacao(String descricaoEquipeProgramacao) {
		this.descricaoEquipeProgramacao = descricaoEquipeProgramacao;
	}

	/**
	 * @return Retorna o campo descricaoUnidadeAtendimento.
	 */
	public String getDescricaoUnidadeAtendimento() {
		return descricaoUnidadeAtendimento;
	}

	/**
	 * @param descricaoUnidadeAtendimento O descricaoUnidadeAtendimento a ser setado.
	 */
	public void setDescricaoUnidadeAtendimento(String descricaoUnidadeAtendimento) {
		this.descricaoUnidadeAtendimento = descricaoUnidadeAtendimento;
	}

	/**
	 * @return Retorna o campo descricaoUnidadeAtual.
	 */
	public String getDescricaoUnidadeAtual() {
		return descricaoUnidadeAtual;
	}

	/**
	 * @param descricaoUnidadeAtual O descricaoUnidadeAtual a ser setado.
	 */
	public void setDescricaoUnidadeAtual(String descricaoUnidadeAtual) {
		this.descricaoUnidadeAtual = descricaoUnidadeAtual;
	}

	/**
	 * @return Retorna o campo descricaoUnidadeEncerramento.
	 */
	public String getDescricaoUnidadeEncerramento() {
		return descricaoUnidadeEncerramento;
	}

	/**
	 * @param descricaoUnidadeEncerramento O descricaoUnidadeEncerramento a ser setado.
	 */
	public void setDescricaoUnidadeEncerramento(String descricaoUnidadeEncerramento) {
		this.descricaoUnidadeEncerramento = descricaoUnidadeEncerramento;
	}

	/**
	 * @return Retorna o campo idEquipeExecucao.
	 */
	public String getIdEquipeExecucao() {
		return idEquipeExecucao;
	}

	/**
	 * @param idEquipeExecucao O idEquipeExecucao a ser setado.
	 */
	public void setIdEquipeExecucao(String idEquipeExecucao) {
		this.idEquipeExecucao = idEquipeExecucao;
	}

	/**
	 * @return Retorna o campo idEquipeProgramacao.
	 */
	public String getIdEquipeProgramacao() {
		return idEquipeProgramacao;
	}

	/**
	 * @param idEquipeProgramacao O idEquipeProgramacao a ser setado.
	 */
	public void setIdEquipeProgramacao(String idEquipeProgramacao) {
		this.idEquipeProgramacao = idEquipeProgramacao;
	}

	/**
	 * @return Retorna o campo idUnidadeAtendimento.
	 */
	public String getIdUnidadeAtendimento() {
		return idUnidadeAtendimento;
	}

	/**
	 * @param idUnidadeAtendimento O idUnidadeAtendimento a ser setado.
	 */
	public void setIdUnidadeAtendimento(String idUnidadeAtendimento) {
		this.idUnidadeAtendimento = idUnidadeAtendimento;
	}

	/**
	 * @return Retorna o campo idUnidadeAtual.
	 */
	public String getIdUnidadeAtual() {
		return idUnidadeAtual;
	}

	/**
	 * @param idUnidadeAtual O idUnidadeAtual a ser setado.
	 */
	public void setIdUnidadeAtual(String idUnidadeAtual) {
		this.idUnidadeAtual = idUnidadeAtual;
	}

	/**
	 * @return Retorna o campo idUnidadeEncerramento.
	 */
	public String getIdUnidadeEncerramento() {
		return idUnidadeEncerramento;
	}

	/**
	 * @param idUnidadeEncerramento O idUnidadeEncerramento a ser setado.
	 */
	public void setIdUnidadeEncerramento(String idUnidadeEncerramento) {
		this.idUnidadeEncerramento = idUnidadeEncerramento;
	}

	/**
	 * @return Retorna o campo origemServico.
	 */
	public String getOrigemServico() {
		return origemServico;
	}

	/**
	 * @param origemServico O origemServico a ser setado.
	 */
	public void setOrigemServico(String origemServico) {
		this.origemServico = origemServico;
	}

	/**
	 * @return Retorna o campo periodoAtendimentoFinal.
	 */
	public String getPeriodoAtendimentoFinal() {
		return periodoAtendimentoFinal;
	}

	/**
	 * @param periodoAtendimentoFinal O periodoAtendimentoFinal a ser setado.
	 */
	public void setPeriodoAtendimentoFinal(String periodoAtendimentoFinal) {
		this.periodoAtendimentoFinal = periodoAtendimentoFinal;
	}

	/**
	 * @return Retorna o campo periodoAtendimentoInicial.
	 */
	public String getPeriodoAtendimentoInicial() {
		return periodoAtendimentoInicial;
	}

	/**
	 * @param periodoAtendimentoInicial O periodoAtendimentoInicial a ser setado.
	 */
	public void setPeriodoAtendimentoInicial(String periodoAtendimentoInicial) {
		this.periodoAtendimentoInicial = periodoAtendimentoInicial;
	}

	/**
	 * @return Retorna o campo periodoEncerramentoFinal.
	 */
	public String getPeriodoEncerramentoFinal() {
		return periodoEncerramentoFinal;
	}

	/**
	 * @param periodoEncerramentoFinal O periodoEncerramentoFinal a ser setado.
	 */
	public void setPeriodoEncerramentoFinal(String periodoEncerramentoFinal) {
		this.periodoEncerramentoFinal = periodoEncerramentoFinal;
	}

	/**
	 * @return Retorna o campo periodoEncerramentoInicial.
	 */
	public String getPeriodoEncerramentoInicial() {
		return periodoEncerramentoInicial;
	}

	/**
	 * @param periodoEncerramentoInicial O periodoEncerramentoInicial a ser setado.
	 */
	public void setPeriodoEncerramentoInicial(String periodoEncerramentoInicial) {
		this.periodoEncerramentoInicial = periodoEncerramentoInicial;
	}

	/**
	 * @return Retorna o campo situacaoOrdemServico.
	 */
	public String getSituacaoOrdemServico() {
		return situacaoOrdemServico;
	}

	/**
	 * @param situacaoOrdemServico O situacaoOrdemServico a ser setado.
	 */
	public void setSituacaoOrdemServico(String situacaoOrdemServico) {
		this.situacaoOrdemServico = situacaoOrdemServico;
	}

	/**
	 * @return Retorna o campo tipoOrdenacao.
	 */
	public String getTipoOrdenacao() {
		return tipoOrdenacao;
	}

	/**
	 * @param tipoOrdenacao O tipoOrdenacao a ser setado.
	 */
	public void setTipoOrdenacao(String tipoOrdenacao) {
		this.tipoOrdenacao = tipoOrdenacao;
	}

	/**
	 * @return Retorna o campo tipoServico.
	 */
	public String[] getTipoServico() {
		return tipoServico;
	}

	/**
	 * @param tipoServico O tipoServico a ser setado.
	 */
	public void setTipoServico(String[] tipoServico) {
		this.tipoServico = tipoServico;
	}

	/**
	 * @return Retorna o campo tipoServicoSelecionados.
	 */
	public String[] getTipoServicoSelecionados() {
		return tipoServicoSelecionados;
	}

	/**
	 * @param tipoServicoSelecionados O tipoServicoSelecionados a ser setado.
	 */
	public void setTipoServicoSelecionados(String[] tipoServicoSelecionados) {
		this.tipoServicoSelecionados = tipoServicoSelecionados;
	}



}
