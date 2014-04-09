package gcom.gui.operacional;

import org.apache.struts.validator.ValidatorActionForm;

/**
 *  
 * @author 	Vinícius Medeiros 
 * @date  	11/06/2008
 */

public class AtualizarProducaoAguaActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	private String id;
	private String anoMesReferencia;
	private String localidadeID;
	private String localidadeDescricao;
	private String volumeProduzido;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(String anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public String getLocalidadeDescricao() {
		return localidadeDescricao;
	}

	public void setLocalidadeDescricao(String localidadeDescricao) {
		this.localidadeDescricao = localidadeDescricao;
	}

	public String getLocalidadeID() {
		return localidadeID;
	}

	public void setLocalidadeID(String localidadeID) {
		this.localidadeID = localidadeID;
	}

	public String getVolumeProduzido() {
		return volumeProduzido;
	}

	public void setVolumeProduzido(String volumeProduzido) {
		this.volumeProduzido = volumeProduzido;
	}

	

}
