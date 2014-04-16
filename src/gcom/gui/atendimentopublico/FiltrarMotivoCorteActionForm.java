package gcom.gui.atendimentopublico;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0755]FILTRAR MOTIVO DE CORTE
 * 
 * @author Vinicius Medeiros
 * @date 02/04/2008
 */

public class FiltrarMotivoCorteActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String idMotivoCorte;

	private String descricao;

	private String indicadorUso;
	
	private String tipoPesquisa;
	
	private String indicadorAtualizar;

	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	public String getIdMotivoCorte() {
		return idMotivoCorte;
	}

	public void setIdMotivoCorte(String idMotivoCorte) {
		this.idMotivoCorte = idMotivoCorte;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

}
