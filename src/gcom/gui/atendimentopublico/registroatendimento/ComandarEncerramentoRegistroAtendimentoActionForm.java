package gcom.gui.atendimentopublico.registroatendimento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * 
 * Este caso de uso gera relatorio de anormalidade de consumo
 * 
 * [UC0638]Gerar Relatorio Anormalidade Consumo
 * 
 * @author Kassia Albuquerque
 * @date 24/09/2006
 * 
 */


public class ComandarEncerramentoRegistroAtendimentoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	private String dataAtendimentoInicial;
	private String dataAtendimentoFinal;
	private String motivoEncerramento;
	private String idUsuario;
	private String nomeUsuario;
	private String idUnidadeAtendimento;
	private String nomeUnidadeAtendimento;
	private String idUnidadeAtual;
	private String nomeUnidadeAtual;
	private String idUnidadeSuperior;
	private String nomeUnidadeSuperior;
	private String[] idsSolicitacaoTipoEspecificcacoes;
	
	/**
	 * @return Retorna o campo idsSolicitacaoTipoEspecificcacoes.
	 */
	public String[] getIdsSolicitacaoTipoEspecificcacoes() {
		return idsSolicitacaoTipoEspecificcacoes;
	}
	/**
	 * @param idsSolicitacaoTipoEspecificcacoes O idsSolicitacaoTipoEspecificcacoes a ser setado.
	 */
	public void setIdsSolicitacaoTipoEspecificcacoes(
			String[] idsSolicitacaoTipoEspecificcacoes) {
		this.idsSolicitacaoTipoEspecificcacoes = idsSolicitacaoTipoEspecificcacoes;
	}
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
	 * @return Retorna o campo idUsuario.
	 */
	public String getIdUsuario() {
		return idUsuario;
	}
	/**
	 * @param idUsuario O idUsuario a ser setado.
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	/**
	 * @return Retorna o campo motivoEncerramento.
	 */
	public String getMotivoEncerramento() {
		return motivoEncerramento;
	}
	/**
	 * @param motivoEncerramento O motivoEncerramento a ser setado.
	 */
	public void setMotivoEncerramento(String motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}
	/**
	 * @return Retorna o campo nomeUnidadeAtendimento.
	 */
	public String getNomeUnidadeAtendimento() {
		return nomeUnidadeAtendimento;
	}
	/**
	 * @param nomeUnidadeAtendimento O nomeUnidadeAtendimento a ser setado.
	 */
	public void setNomeUnidadeAtendimento(String nomeUnidadeAtendimento) {
		this.nomeUnidadeAtendimento = nomeUnidadeAtendimento;
	}
	/**
	 * @return Retorna o campo nomeUnidadeAtual.
	 */
	public String getNomeUnidadeAtual() {
		return nomeUnidadeAtual;
	}
	/**
	 * @param nomeUnidadeAtual O nomeUnidadeAtual a ser setado.
	 */
	public void setNomeUnidadeAtual(String nomeUnidadeAtual) {
		this.nomeUnidadeAtual = nomeUnidadeAtual;
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
	 * @return Retorna o campo nomeUsuario.
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	/**
	 * @param nomeUsuario O nomeUsuario a ser setado.
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

}
