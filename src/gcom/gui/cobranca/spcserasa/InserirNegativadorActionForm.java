package gcom.gui.cobranca.spcserasa;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InserirNegativadorActionForm extends ActionForm {
	
	private String codigoAgente;
	
	private String cliente;
	
	private String codigoCliente;
	
	private String imovel;
	
	private String codigoImovel;
	
	private String inscricaoEstadual;
	
	private String nomeCliente;
	
	private String inscricaoImovel;
	
	private String mensagemAgente;

	private String okAgente;
	
	private String okCliente;
	
	private String okImovel;
	
	private String teste;
	
	private static final long serialVersionUID = 1L;

    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    	
    	this.codigoAgente = "";
    	this.cliente = "";
    	this.imovel = "";
    	this.inscricaoEstadual = "";
    	this.codigoImovel = "";
    	this.codigoCliente = "";
    	this.nomeCliente = "";
    	this.inscricaoImovel = "";
    	this.mensagemAgente = "";
    	this.okAgente = "";
    	this.okCliente = "";
    	this.okImovel = "";
    	this.teste = "";
    }

	/**
	 * @return Retorna o campo cliente.
	 */
	public String getCliente() {
		return cliente;
	}

	/**
	 * @param cliente O cliente a ser setado.
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return Retorna o campo codigoAgente.
	 */
	public String getCodigoAgente() {
		return codigoAgente;
	}

	/**
	 * @param codigoAgente O codigoAgente a ser setado.
	 */
	public void setCodigoAgente(String codigoAgente) {
		this.codigoAgente = codigoAgente;
	}

	/**
	 * @return Retorna o campo imovel.
	 */
	public String getImovel() {
		return imovel;
	}

	/**
	 * @param imovel O imovel a ser setado.
	 */
	public void setImovel(String imovel) {
		this.imovel = imovel;
	}

	/**
	 * @return Retorna o campo inscricaoEstadual.
	 */
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	/**
	 * @param inscricaoEstadual O inscricaoEstadual a ser setado.
	 */
	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	/**
	 * @return Retorna o campo codigoImovel.
	 */
	public String getCodigoImovel() {
		return codigoImovel;
	}

	/**
	 * @param codigoImovel O codigoImovel a ser setado.
	 */
	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	/**
	 * @return Retorna o campo codigoCliente.
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente O codigoCliente a ser setado.
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return Retorna o campo inscricaoImovel.
	 */
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	/**
	 * @param inscricaoImovel O inscricaoImovel a ser setado.
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	/**
	 * @return Retorna o campo nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente O nomeCliente a ser setado.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * @return Retorna o campo mensagemAgente.
	 */
	public String getMensagemAgente() {
		return mensagemAgente;
	}

	/**
	 * @param mensagemAgente O mensagemAgente a ser setado.
	 */
	public void setMensagemAgente(String mensagemAgente) {
		this.mensagemAgente = mensagemAgente;
	}

	/**
	 * @return Retorna o campo okAgente.
	 */
	public String getOkAgente() {
		return okAgente;
	}

	/**
	 * @param okAgente O okAgente a ser setado.
	 */
	public void setOkAgente(String okAgente) {
		this.okAgente = okAgente;
	}

	/**
	 * @return Retorna o campo okCliente.
	 */
	public String getOkCliente() {
		return okCliente;
	}

	/**
	 * @param okCliente O okCliente a ser setado.
	 */
	public void setOkCliente(String okCliente) {
		this.okCliente = okCliente;
	}

	/**
	 * @return Retorna o campo okImovel.
	 */
	public String getOkImovel() {
		return okImovel;
	}

	/**
	 * @param okImovel O okImovel a ser setado.
	 */
	public void setOkImovel(String okImovel) {
		this.okImovel = okImovel;
	}

	/**
	 * @return Retorna o campo teste.
	 */
	public String getTeste() {
		return teste;
	}

	/**
	 * @param teste O teste a ser setado.
	 */
	public void setTeste(String teste) {
		this.teste = teste;
	}

}
