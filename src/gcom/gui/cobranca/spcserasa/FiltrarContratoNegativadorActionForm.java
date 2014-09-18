package gcom.gui.cobranca.spcserasa;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Yara Taciane de Souza
 * @created 19/12/2007
 */

public class FiltrarContratoNegativadorActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String indicadorAtualizar;
	
	private String idNegativador;

	private Collection colecaoNegativador;

	private String numeroContrato;

	/**
	 * @return Retorna o campo colecaoNegativador.
	 */
	public Collection getColecaoNegativador() {
		return colecaoNegativador;
	}

	/**
	 * @param colecaoNegativador O colecaoNegativador a ser setado.
	 */
	public void setColecaoNegativador(Collection colecaoNegativador) {
		this.colecaoNegativador = colecaoNegativador;
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
	 * @return Retorna o campo indicadorAtualizar.
	 */
	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	/**
	 * @param indicadorAtualizar O indicadorAtualizar a ser setado.
	 */
	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}

	/**
	 * @return Retorna o campo numeroContrato.
	 */
	public String getNumeroContrato() {
		return numeroContrato;
	}

	/**
	 * @param numeroContrato O numeroContrato a ser setado.
	 */
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}   		
 
	
	
	public void reset(ActionMapping arg0, ServletRequest arg1) {
		
		super.reset(arg0, arg1);
		
		this.idNegativador="";

		this.colecaoNegativador= new ArrayList();

		this.numeroContrato="";
	
	}
	
	
	
}

