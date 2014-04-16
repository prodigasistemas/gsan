package gcom.gui.operacional;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0813]Filtrar Producao de agua
 * 
 * @author Vinicius Medeiros
 * @date 10/06/2008
 */

public class FiltrarProducaoAguaActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String id;
	private String anoMesReferencia;
	private String localidadeID;
	private String localidadeDescricao;
	private String volumeProduzido;
	private String indicadorAtualizar;

	
	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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


	public String getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(String anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public String getVolumeProduzido() {
		return volumeProduzido;
	}

	public void setVolumeProduzido(String volumeProduzido) {
		this.volumeProduzido = volumeProduzido;
	}

}
