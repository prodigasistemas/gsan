package gcom.gui.operacional;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Vinicius Medeiros
 * @date 09/06/2008
 */
public class InserirProducaoAguaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	String id;
	
	String anoMesReferencia;

	String localidadeID;

	String localidadeDescricao;

	String volumeProduzido;
	
	String IndicadorUso;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIndicadorUso() {
		return IndicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		IndicadorUso = indicadorUso;
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
