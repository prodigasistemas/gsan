package gcom.gui.cobranca.spcserasa;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AtualizarNegativadorActionForm extends ActionForm {
	
	private String codigoAgente;
	
	private String codigoCliente;
	
	private String codigoImovel;
	
	private String inscricaoEstadual;
	
	private String nomeCliente;
	
	private String inscricaoImovel;
	
	private String mensagemAgente;

	private String okAgente;
	
	private String okCliente;
	
	private String okImovel;
	
	private String ativo;
	
	private String inativo;
	
	private String idNegativador;
	
	private String time;
	
	private String indicadorUso;
	
	
	private static final long serialVersionUID = 1L;

    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    	
    	this.codigoAgente = "";
    	this.inscricaoEstadual = "";
    	this.codigoImovel = "";
    	this.codigoCliente = "";
    	this.nomeCliente = "";
    	this.inscricaoImovel = "";
    	this.mensagemAgente = "";
    	this.okAgente = "";
    	this.okCliente = "";
    	this.okImovel = "";
    	this.ativo = "";
    	this.inativo = "";
    	this.idNegativador = "";
    	this.time = "";
    	this.indicadorUso="";
    	
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
	 * @return Retorna o campo ativo.
	 */
	public String getAtivo() {
		return ativo;
	}

	/**
	 * @param ativo O ativo a ser setado.
	 */
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	/**
	 * @return Retorna o campo inativo.
	 */
	public String getInativo() {
		return inativo;
	}

	/**
	 * @param inativo O inativo a ser setado.
	 */
	public void setInativo(String inativo) {
		this.inativo = inativo;
	}

	/**
	 * @return Retorna o campo idNegativador.
	 */
	public String getIdNegativador() {
		return idNegativador;
	}

	/**
	 * @param idNegativador O idNegativador a ser setado.
	 */
	public void setIdNegativador(String idNegativador) {
		this.idNegativador = idNegativador;
	}

	/**
	 * @return Retorna o campo time.
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time O time a ser setado.
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return Retorna o campo indicadorUso.
	 */
	public String getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @param indicadorUso O indicadorUso a ser setado.
	 */
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	
	

}
