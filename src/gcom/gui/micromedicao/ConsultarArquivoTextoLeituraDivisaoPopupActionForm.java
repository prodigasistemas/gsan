package gcom.gui.micromedicao;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * UC1027 - Exibir Consultar Arquivo Texto Leitura Divisão.
 * 
 * @author Hugo Leonardo
 * @date 04/06/2010
 */
public class ConsultarArquivoTextoLeituraDivisaoPopupActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	//private String codigo;
	
	private String localidadeID;
	
	private String localidadeNome;
	
	private String codigoSetorComercial;
	
	private String codigoRota;
	
	private String[] idsRegistros;
	
	
	/**
	 * @return Returns the idsRegistros.
	 */
	public String[] getIdsRegistros() {
		return idsRegistros;
	}
	
	/**
	 * @param idsRegistros The idsRegistros to set.
	 */
	public void setIdsRegistros(String[] idsRegistros) {
		this.idsRegistros = idsRegistros;
	}
	
	public String getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getLocalidadeID() {
		return localidadeID;
	}

	public void setLocalidadeID(String localidadeID) {
		this.localidadeID = localidadeID;
	}

	public String getLocalidadeNome() {
		return localidadeNome;
	}

	public void setLocalidadeNome(String localidadeNome) {
		this.localidadeNome = localidadeNome;
	}

	@Override
	public void reset(ActionMapping arg0, ServletRequest arg1) {
		super.reset(arg0, arg1);
		
		this.localidadeID="";
		this.localidadeNome="";
		this.codigoSetorComercial="";
		this.codigoRota="";
		this.idsRegistros=new String[0];
	}

}
