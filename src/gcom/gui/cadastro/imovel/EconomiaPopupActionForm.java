package gcom.gui.cadastro.imovel;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 2 de Junho de 2004
 */
public class EconomiaPopupActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String[] idRegistrosRemocao;

	private String areaConstruida;

	private String idCliente;

	private String complementoEndereco;

	private String nomeCliente;

	private String numeroCelpe;

	private String numeroIptu;

	private String numeroMorador;

	private String numeroPontosUtilizacao;

	private String clienteRelacaoTipo;

	private String idClienteImovelUsuario;

	private String idClienteImovelResponsavel;

	private String idAreaConstruidaFaixa;

	private String textoSelecionadoEconomia;

	private String idClienteImovelUsuarioEconomias;

	private String botaoAdicionar;

	private String dataInicioClienteImovelRelacao;
	
	private String colecaoCliente;

	/**
	 * Gets the areaConstruida attribute of the EconomiaPopupActionForm object
	 * 
	 * @return The areaConstruida value
	 */
	public String getAreaConstruida() {
		return areaConstruida;
	}

	/**
	 * Sets the areaConstruida attribute of the EconomiaPopupActionForm object
	 * 
	 * @param areaConstruida
	 *            The new areaConstruida value
	 */
	public void setAreaConstruida(String areaConstruida) {
		this.areaConstruida = areaConstruida;
	}

	/**
	 * Gets the codigoCliente attribute of the EconomiaPopupActionForm object
	 * 
	 * @return The codigoCliente value
	 */
	public String getIdCliente() {
		return idCliente;
	}

	/**
	 * Sets the codigoCliente attribute of the EconomiaPopupActionForm object
	 * 
	 * @param idCliente
	 *            The new idCliente value
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * Gets the complementoEndereco attribute of the EconomiaPopupActionForm
	 * object
	 * 
	 * @return The complementoEndereco value
	 */
	public String getComplementoEndereco() {
		return complementoEndereco;
	}

	/**
	 * Sets the complementoEndereco attribute of the EconomiaPopupActionForm
	 * object
	 * 
	 * @param complementoEndereco
	 *            The new complementoEndereco value
	 */
	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	/**
	 * Gets the idClienteImovelResponsavel attribute of the
	 * EconomiaPopupActionForm object
	 * 
	 * @return The idClienteImovelResponsavel value
	 */
	public String getIdClienteImovelResponsavel() {
		return idClienteImovelResponsavel;
	}

	/**
	 * Sets the idClienteImovelResponsavel attribute of the
	 * EconomiaPopupActionForm object
	 * 
	 * @param idClienteImovelResponsavel
	 *            The new idClienteImovelResponsavel value
	 */
	public void setIdClienteImovelResponsavel(String idClienteImovelResponsavel) {
		this.idClienteImovelResponsavel = idClienteImovelResponsavel;
	}

	/**
	 * Gets the idClienteImovelUsuario attribute of the EconomiaPopupActionForm
	 * object
	 * 
	 * @return The idClienteImovelUsuario value
	 */
	public String getIdClienteImovelUsuario() {
		return idClienteImovelUsuario;
	}

	/**
	 * Sets the idClienteImovelUsuario attribute of the EconomiaPopupActionForm
	 * object
	 * 
	 * @param idClienteImovelUsuario
	 *            The new idClienteImovelUsuario value
	 */
	public void setIdClienteImovelUsuario(String idClienteImovelUsuario) {
		this.idClienteImovelUsuario = idClienteImovelUsuario;
	}

	/**
	 * Gets the idRegistrosRemocao attribute of the EconomiaPopupActionForm
	 * object
	 * 
	 * @return The idRegistrosRemocao value
	 */
	public String[] getIdRegistrosRemocao() {
		return idRegistrosRemocao;
	}

	/**
	 * Sets the idRegistrosRemocao attribute of the EconomiaPopupActionForm
	 * object
	 * 
	 * @param idRegistrosRemocao
	 *            The new idRegistrosRemocao value
	 */
	public void setIdRegistrosRemocao(String[] idRegistrosRemocao) {
		this.idRegistrosRemocao = idRegistrosRemocao;
	}

	/**
	 * Gets the nomeCliente attribute of the EconomiaPopupActionForm object
	 * 
	 * @return The nomeCliente value
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * Sets the nomeCliente attribute of the EconomiaPopupActionForm object
	 * 
	 * @param nomeCliente
	 *            The new nomeCliente value
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * Gets the numeroContratoEnergia attribute of the EconomiaPopupActionForm
	 * object
	 * 
	 * @return The numeroContratoEnergia value
	 */
	public String getNumeroCelpe() {
		return numeroCelpe;
	}

	/**
	 * Sets the numeroContratoEnergia attribute of the EconomiaPopupActionForm
	 * object
	 * 
	 * @param numeroCelpe
	 *            The new numeroCelpe value
	 */
	public void setNumeroCelpe(String numeroCelpe) {
		this.numeroCelpe = numeroCelpe;
	}

	/**
	 * Gets the numeroIptu attribute of the EconomiaPopupActionForm object
	 * 
	 * @return The numeroIptu value
	 */
	public String getNumeroIptu() {
		return numeroIptu;
	}

	/**
	 * Sets the numeroIptu attribute of the EconomiaPopupActionForm object
	 * 
	 * @param numeroIptu
	 *            The new numeroIptu value
	 */
	public void setNumeroIptu(String numeroIptu) {
		this.numeroIptu = numeroIptu;
	}

	/**
	 * Gets the numeroMorador attribute of the EconomiaPopupActionForm object
	 * 
	 * @return The numeroMorador value
	 */
	public String getNumeroMorador() {
		return numeroMorador;
	}

	/**
	 * Sets the numeroMorador attribute of the EconomiaPopupActionForm object
	 * 
	 * @param numeroMorador
	 *            The new numeroMorador value
	 */
	public void setNumeroMorador(String numeroMorador) {
		this.numeroMorador = numeroMorador;
	}

	/**
	 * Gets the numeroPontoUtilizacao attribute of the EconomiaPopupActionForm
	 * object
	 * 
	 * @return The numeroPontoUtilizacao value
	 */
	public String getNumeroPontosUtilizacao() {
		return numeroPontosUtilizacao;
	}

	/**
	 * Sets the numeroPontoUtilizacao attribute of the EconomiaPopupActionForm
	 * object
	 * 
	 * @param numeroPontosUtilizacao
	 *            The new numeroPontosUtilizacao value
	 */
	public void setNumeroPontosUtilizacao(String numeroPontosUtilizacao) {
		this.numeroPontosUtilizacao = numeroPontosUtilizacao;
	}

	/**
	 * Gets the idAreaConstruidaFaixa attribute of the EconomiaPopupActionForm
	 * object
	 * 
	 * @return The idAreaConstruidaFaixa value
	 */
	public String getIdAreaConstruidaFaixa() {
		return idAreaConstruidaFaixa;
	}

	/**
	 * Sets the idAreaConstruidaFaixa attribute of the EconomiaPopupActionForm
	 * object
	 * 
	 * @param idAreaConstruidaFaixa
	 *            The new idAreaConstruidaFaixa value
	 */
	public void setIdAreaConstruidaFaixa(String idAreaConstruidaFaixa) {
		this.idAreaConstruidaFaixa = idAreaConstruidaFaixa;
	}

	/**
	 * Gets the textoSelecionadoEconomia attribute of the
	 * EconomiaPopupActionForm object
	 * 
	 * @return The textoSelecionadoEconomia value
	 */
	public String getTextoSelecionadoEconomia() {
		return textoSelecionadoEconomia;
	}

	/**
	 * Sets the textoSelecionadoEconomia attribute of the
	 * EconomiaPopupActionForm object
	 * 
	 * @param textoSelecionadoEconomia
	 *            The new textoSelecionadoEconomia value
	 */
	public void setTextoSelecionadoEconomia(String textoSelecionadoEconomia) {
		this.textoSelecionadoEconomia = textoSelecionadoEconomia;
	}

	/**
	 * Gets the idClienteImovelUsuarioEconomias attribute of the
	 * EconomiaPopupActionForm object
	 * 
	 * @return The idClienteImovelUsuarioEconomias value
	 */
	public String getIdClienteImovelUsuarioEconomias() {
		return idClienteImovelUsuarioEconomias;
	}

	/**
	 * Sets the idClienteImovelUsuarioEconomias attribute of the
	 * EconomiaPopupActionForm object
	 * 
	 * @param idClienteImovelUsuarioEconomias
	 *            The new idClienteImovelUsuarioEconomias value
	 */
	public void setIdClienteImovelUsuarioEconomias(
			String idClienteImovelUsuarioEconomias) {
		this.idClienteImovelUsuarioEconomias = idClienteImovelUsuarioEconomias;
	}

	/**
	 * Gets the botaoAdicionar attribute of the EconomiaPopupActionForm object
	 * 
	 * @return The botaoAdicionar value
	 */
	public String getBotaoAdicionar() {
		return botaoAdicionar;
	}

	/**
	 * Sets the botaoAdicionar attribute of the EconomiaPopupActionForm object
	 * 
	 * @param botaoAdicionar
	 *            The new botaoAdicionar value
	 */
	public void setBotaoAdicionar(String botaoAdicionar) {
		this.botaoAdicionar = botaoAdicionar;
	}

	/**
	 * Gets the clienteRelacaoTipo attribute of the EconomiaPopupActionForm
	 * object
	 * 
	 * @return The clienteRelacaoTipo value
	 */
	public String getClienteRelacaoTipo() {
		return clienteRelacaoTipo;
	}

	/**
	 * Sets the clienteRelacaoTipo attribute of the EconomiaPopupActionForm
	 * object
	 * 
	 * @param clienteRelacaoTipo
	 *            The new clienteRelacaoTipo value
	 */
	public void setClienteRelacaoTipo(String clienteRelacaoTipo) {
		this.clienteRelacaoTipo = clienteRelacaoTipo;
	}

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 */
	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {

	}

	public String getDataInicioClienteImovelRelacao() {
		return dataInicioClienteImovelRelacao;
	}

	public void setDataInicioClienteImovelRelacao(
			String dataInicioClienteImovelRelacao) {
		this.dataInicioClienteImovelRelacao = dataInicioClienteImovelRelacao;
	}

	public String getColecaoCliente() {
		return colecaoCliente;
	}

	public void setColecaoCliente(String colecaoCliente) {
		this.colecaoCliente = colecaoCliente;
	}

}
