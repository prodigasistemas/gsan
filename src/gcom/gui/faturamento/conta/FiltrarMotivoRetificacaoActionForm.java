package gcom.gui.faturamento.conta;

import org.apache.struts.validator.ValidatorActionForm;

public class FiltrarMotivoRetificacaoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String descricao;
	
	private String numeroOcorrenciasNoAno;
	
	private String indicadorCompetenciaConsumo;
	
	private String indicadorUso;
	
	private String indicadorAtualizar;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIndicadorCompetenciaConsumo() {
		return indicadorCompetenciaConsumo;
	}

	public void setIndicadorCompetenciaConsumo(String indicadorCompetenciaConsumo) {
		this.indicadorCompetenciaConsumo = indicadorCompetenciaConsumo;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getNumeroOcorrenciasNoAno() {
		return numeroOcorrenciasNoAno;
	}

	public void setNumeroOcorrenciasNoAno(String numeroOcorrenciasNoAno) {
		this.numeroOcorrenciasNoAno = numeroOcorrenciasNoAno;
	}

	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}
	
	

}
