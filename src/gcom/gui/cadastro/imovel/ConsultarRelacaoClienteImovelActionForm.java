package gcom.gui.cadastro.imovel;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Action ConsultarRelacaoClienteImovelActionForm
 *
 * @author thiago
 *  
 * @date 10/03/2006
 */
public class ConsultarRelacaoClienteImovelActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String idImovel = ""; 
	private String idCliente = "";
	private String idClienteRelacaoTipo = "";
	private String idClienteImovelEconomia = "";
	private String idImovelEconomia = "";
	private String periodoInicialDataInicioRelacao = "";
	private String periodoFinalDataInicioRelacao = "";
	private String periodoInicialDataFimRelacao = "";
	private String periodoFinalDataFimRelacao = "";
	private String idClienteImovelFimRelacaoMotivo = "";
	private String situacaoRelacao = "";	
	
	/**
	 * Método que limpa os atributos 
	 *
	 * @author Administrador
	 * @date 10/03/2006
	 *
	 * @param arg0 mapeamento
	 * @param arg1 request
	 */
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		
		super.reset(arg0, arg1);

		this.idImovel = ""; 
		this.idCliente = "";
		this.idClienteRelacaoTipo = "";
		this.periodoInicialDataInicioRelacao = "";
		this.periodoFinalDataInicioRelacao = "";
		this.periodoInicialDataFimRelacao = "";
		this.periodoFinalDataFimRelacao = "";
		this.idClienteImovelFimRelacaoMotivo = "";
		this.idClienteImovelEconomia = "";
		this.idImovelEconomia = "";
		this.situacaoRelacao = "";
	}


	/**
	 * @return Retorna o campo idCliente.
	 */
	public String getIdCliente() {
		return idCliente;
	}


	/**
	 * @param idCliente O idCliente a ser setado.
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}


	/**
	 * @return Retorna o campo idClienteImovelEconomia.
	 */
	public String getIdClienteImovelEconomia() {
		return idClienteImovelEconomia;
	}


	/**
	 * @param idClienteImovelEconomia O idClienteImovelEconomia a ser setado.
	 */
	public void setIdClienteImovelEconomia(String idClienteImovelEconomia) {
		this.idClienteImovelEconomia = idClienteImovelEconomia;
	}


	/**
	 * @return Retorna o campo idClienteImovelFimRelacaoMotivo.
	 */
	public String getIdClienteImovelFimRelacaoMotivo() {
		return idClienteImovelFimRelacaoMotivo;
	}


	/**
	 * @param idClienteImovelFimRelacaoMotivo O idClienteImovelFimRelacaoMotivo a ser setado.
	 */
	public void setIdClienteImovelFimRelacaoMotivo(
			String idClienteImovelFimRelacaoMotivo) {
		this.idClienteImovelFimRelacaoMotivo = idClienteImovelFimRelacaoMotivo;
	}


	/**
	 * @return Retorna o campo idClienteRelacaoTipo.
	 */
	public String getIdClienteRelacaoTipo() {
		return idClienteRelacaoTipo;
	}


	/**
	 * @param idClienteRelacaoTipo O idClienteRelacaoTipo a ser setado.
	 */
	public void setIdClienteRelacaoTipo(String idClienteRelacaoTipo) {
		this.idClienteRelacaoTipo = idClienteRelacaoTipo;
	}


	/**
	 * @return Retorna o campo idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}


	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}


	/**
	 * @return Retorna o campo idImovelEconomia.
	 */
	public String getIdImovelEconomia() {
		return idImovelEconomia;
	}


	/**
	 * @param idImovelEconomia O idImovelEconomia a ser setado.
	 */
	public void setIdImovelEconomia(String idImovelEconomia) {
		this.idImovelEconomia = idImovelEconomia;
	}


	/**
	 * @return Retorna o campo periodoFinalDataFimRelacao.
	 */
	public String getPeriodoFinalDataFimRelacao() {
		return periodoFinalDataFimRelacao;
	}


	/**
	 * @param periodoFinalDataFimRelacao O periodoFinalDataFimRelacao a ser setado.
	 */
	public void setPeriodoFinalDataFimRelacao(String periodoFinalDataFimRelacao) {
		this.periodoFinalDataFimRelacao = periodoFinalDataFimRelacao;
	}


	/**
	 * @return Retorna o campo periodoFinalDataInicioRelacao.
	 */
	public String getPeriodoFinalDataInicioRelacao() {
		return periodoFinalDataInicioRelacao;
	}


	/**
	 * @param periodoFinalDataInicioRelacao O periodoFinalDataInicioRelacao a ser setado.
	 */
	public void setPeriodoFinalDataInicioRelacao(
			String periodoFinalDataInicioRelacao) {
		this.periodoFinalDataInicioRelacao = periodoFinalDataInicioRelacao;
	}


	/**
	 * @return Retorna o campo periodoInicialDataFimRelacao.
	 */
	public String getPeriodoInicialDataFimRelacao() {
		return periodoInicialDataFimRelacao;
	}


	/**
	 * @param periodoInicialDataFimRelacao O periodoInicialDataFimRelacao a ser setado.
	 */
	public void setPeriodoInicialDataFimRelacao(String periodoInicialDataFimRelacao) {
		this.periodoInicialDataFimRelacao = periodoInicialDataFimRelacao;
	}


	/**
	 * @return Retorna o campo periodoInicialDataInicioRelacao.
	 */
	public String getPeriodoInicialDataInicioRelacao() {
		return periodoInicialDataInicioRelacao;
	}


	/**
	 * @param periodoInicialDataInicioRelacao O periodoInicialDataInicioRelacao a ser setado.
	 */
	public void setPeriodoInicialDataInicioRelacao(
			String periodoInicialDataInicioRelacao) {
		this.periodoInicialDataInicioRelacao = periodoInicialDataInicioRelacao;
	}


	public String getSituacaoRelacao() {
		return situacaoRelacao;
	}


	public void setSituacaoRelacao(String situacaoRelacao) {
		this.situacaoRelacao = situacaoRelacao;
	}
	
}
