package gcom.gui.cobranca;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0253] Pesquisar Comando de Ação de Cobrança
 * @author Rafael Santos
 * @since 24/01/2006
 */
public class PesquisarComandoAcaoCobrancaActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String cobrancaAtividade;
	
	private String cobrancaAcao;
	
	private String periodoExecucaoComandoInicial;
	
	private String periodoExecucaoComandoFinal;
	
	private String periodoGeracaoComandoInicial;
	
	private String periodoGeracaoComandoFinal;
	
	private String usuario;
	
	private String idUsuario;
	
	private String tituloComando;
	
	private String tipoPesquisa;
	
	
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {

		cobrancaAtividade = null;
		
		cobrancaAcao = null;
		
		periodoExecucaoComandoInicial = null;
		
		periodoExecucaoComandoFinal = null;
		
		periodoGeracaoComandoInicial = null;
		
		periodoGeracaoComandoFinal = null;
		
		usuario = null;
		
		idUsuario = null;
		
		tituloComando = null;
		
		tipoPesquisa = null;

	
	}

	/**
	 * @return Retorna o campo cobrancaAcao.
	 */
	public String getCobrancaAcao() {
		return cobrancaAcao;
	}

	/**
	 * @param cobrancaAcao O cobrancaAcao a ser setado.
	 */
	public void setCobrancaAcao(String cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}

	/**
	 * @return Retorna o campo cobrancaAtividade.
	 */
	public String getCobrancaAtividade() {
		return cobrancaAtividade;
	}

	/**
	 * @param cobrancaAtividade O cobrancaAtividade a ser setado.
	 */
	public void setCobrancaAtividade(String cobrancaAtividade) {
		this.cobrancaAtividade = cobrancaAtividade;
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
	 * @return Retorna o campo usuario.
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario O usuario a ser setado.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

	public String getTituloComando() {
		return tituloComando;
	}

	public void setTituloComando(String tituloComando) {
		this.tituloComando = tituloComando;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

}
