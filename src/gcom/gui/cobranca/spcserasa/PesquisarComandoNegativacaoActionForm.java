package gcom.gui.cobranca.spcserasa;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC 0653] Pesquisar Comando Negativação
 * 
 * @author Kássia Albuquerque
 * @date 22/10/2007
 */


public class PesquisarComandoNegativacaoActionForm extends ValidatorActionForm {
	
	
	private static final long serialVersionUID = 1L;
	private String tituloComando;
	private String tipoPesquisa;
	private String indicadorComandoSimulado;
	private String periodoGeracaoComandoInicial;
	private String periodoGeracaoComandoFinal;
	private String periodoExecucaoComandoInicial;
	private String periodoExecucaoComandoFinal;
	private String usuarioResponsavelId;
	private String usuarioResponsavelNome;
	private String negativadorId;
	private String popup;
	
	/**
	 * @return Retorna o campo negativadorId.
	 */
	public String getNegativadorId() {
		return negativadorId;
	}
	/**
	 * @param negativadorId O negativadorId a ser setado.
	 */
	public void setNegativadorId(String negativadorId) {
		this.negativadorId = negativadorId;
	}
	/**
	 * @return Retorna o campo usuarioResponsavelId.
	 */
	public String getUsuarioResponsavelId() {
		return usuarioResponsavelId;
	}
	/**
	 * @param usuarioResponsavelId O usuarioResponsavelId a ser setado.
	 */
	public void setUsuarioResponsavelId(String usuarioResponsavelId) {
		this.usuarioResponsavelId = usuarioResponsavelId;
	}
	/**
	 * @return Retorna o campo usuarioResponsavelNome.
	 */
	public String getUsuarioResponsavelNome() {
		return usuarioResponsavelNome;
	}
	/**
	 * @param usuarioResponsavelNome O usuarioResponsavelNome a ser setado.
	 */
	public void setUsuarioResponsavelNome(String usuarioResponsavelNome) {
		this.usuarioResponsavelNome = usuarioResponsavelNome;
	}
	/**
	 * @return Retorna o campo indicadorComandoSimulado.
	 */
	public String getIndicadorComandoSimulado() {
		return indicadorComandoSimulado;
	}
	/**
	 * @param indicadorComandoSimulado O indicadorComandoSimulado a ser setado.
	 */
	public void setIndicadorComandoSimulado(String indicadorComandoSimulado) {
		this.indicadorComandoSimulado = indicadorComandoSimulado;
	}
	/**
	 * @return Retorna o campo periodoExecucaoComandoFinal.
	 */
	public String getPeriodoExecucaoComandoFinal() {
		return periodoExecucaoComandoFinal;
	}
	/**
	 * @param periodoExecucaoComandoFinal O periodoExecucaoComandoFinal a ser setado.
	 */
	public void setPeriodoExecucaoComandoFinal(String periodoExecucaoComandoFinal) {
		this.periodoExecucaoComandoFinal = periodoExecucaoComandoFinal;
	}
	/**
	 * @return Retorna o campo periodoExecucaoComandoInicial.
	 */
	public String getPeriodoExecucaoComandoInicial() {
		return periodoExecucaoComandoInicial;
	}
	/**
	 * @param periodoExecucaoComandoInicial O periodoExecucaoComandoInicial a ser setado.
	 */
	public void setPeriodoExecucaoComandoInicial(
			String periodoExecucaoComandoInicial) {
		this.periodoExecucaoComandoInicial = periodoExecucaoComandoInicial;
	}
	/**
	 * @return Retorna o campo periodoGeracaoComandoFinal.
	 */
	public String getPeriodoGeracaoComandoFinal() {
		return periodoGeracaoComandoFinal;
	}
	/**
	 * @param periodoGeracaoComandoFinal O periodoGeracaoComandoFinal a ser setado.
	 */
	public void setPeriodoGeracaoComandoFinal(String periodoGeracaoComandoFinal) {
		this.periodoGeracaoComandoFinal = periodoGeracaoComandoFinal;
	}
	/**
	 * @return Retorna o campo periodoGeracaoComandoInicial.
	 */
	public String getPeriodoGeracaoComandoInicial() {
		return periodoGeracaoComandoInicial;
	}
	/**
	 * @param periodoGeracaoComandoInicial O periodoGeracaoComandoInicial a ser setado.
	 */
	public void setPeriodoGeracaoComandoInicial(String periodoGeracaoComandoInicial) {
		this.periodoGeracaoComandoInicial = periodoGeracaoComandoInicial;
	}
	/**
	 * @return Retorna o campo tituloComando.
	 */
	public String getTituloComando() {
		return tituloComando;
	}
	/**
	 * @param tituloComando O tituloComando a ser setado.
	 */
	public void setTituloComando(String tituloComando) {
		this.tituloComando = tituloComando;
	}
	/**
	 * @return Retorna o campo tipoPesquisa.
	 */
	public String getTipoPesquisa() {
		return tipoPesquisa;
	}
	/**
	 * @param tipoPesquisa O tipoPesquisa a ser setado.
	 */
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
	/**
	 * @return Returns the popup.
	 */
	public String getPopup() {
		return popup;
	}
	/**
	 * @param popup The popup to set.
	 */
	public void setPopup(String popup) {
		this.popup = popup;
	}
	

	

}
