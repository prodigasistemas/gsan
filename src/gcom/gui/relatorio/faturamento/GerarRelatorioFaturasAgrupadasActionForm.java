package gcom.gui.relatorio.faturamento;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0217] Inserir Resolução de Diretoria
 * @author Rafael Corrêa
 * @since 30/03/2006
 */
public class GerarRelatorioFaturasAgrupadasActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String mesAno;
	private String idClienteSuperior;
	private String nomeClienteSuperior;
	private String idEsferaPoder;
	private String idCliente;
	private String nomeCliente;
	private String[] idsClientesAssociados;
	private String indicadorTotalizador;
	
	
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	/**
	 * @return Retorna o campo mesAno.
	 */
	public String getMesAno() {
		return mesAno;
	}

	/**
	 * @param mesAno O mesAno a ser setado.
	 */
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
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
	 * @return Retorna o campo idClienteSuperior.
	 */
	public String getIdClienteSuperior() {
		return idClienteSuperior;
	}

	/**
	 * @param idClienteSuperior O idClienteSuperior a ser setado.
	 */
	public void setIdClienteSuperior(String idClienteSuperior) {
		this.idClienteSuperior = idClienteSuperior;
	}

	/**
	 * @return Retorna o campo idEsferaPoder.
	 */
	public String getIdEsferaPoder() {
		return idEsferaPoder;
	}

	/**
	 * @param idEsferaPoder O idEsferaPoder a ser setado.
	 */
	public void setIdEsferaPoder(String idEsferaPoder) {
		this.idEsferaPoder = idEsferaPoder;
	}

	/**
	 * @return Retorna o campo nomeClienteSuperior.
	 */
	public String getNomeClienteSuperior() {
		return nomeClienteSuperior;
	}

	/**
	 * @param nomeClienteSuperior O nomeClienteSuperior a ser setado.
	 */
	public void setNomeClienteSuperior(String nomeClienteSuperior) {
		this.nomeClienteSuperior = nomeClienteSuperior;
	}

	/**
	 * @return Retorna o campo idsClientesAssociados.
	 */
	public String[] getIdsClientesAssociados() {
		return idsClientesAssociados;
	}

	/**
	 * @param idsClientesAssociados O idsClientesAssociados a ser setado.
	 */
	public void setIdsClientesAssociados(String[] idsClientesAssociados) {
		this.idsClientesAssociados = idsClientesAssociados;
	}

	/**
	 * @return Retorna o campo indicadorTotalizador.
	 */
	public String getIndicadorTotalizador() {
		return indicadorTotalizador;
	}

	/**
	 * @param indicadorTotalizador O indicadorTotalizador a ser setado.
	 */
	public void setIndicadorTotalizador(String indicadorTotalizador) {
		this.indicadorTotalizador = indicadorTotalizador;
	}
	
}
