package gcom.gui.atendimentopublico;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0861]FILTRAR Perfil da ligacao de esgoto
 * 
 * @author Arthur Carvalho
 * @date 17/10/2008
 */

public class FiltrarPerfilLigacaoEsgotoActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String id;

	private String descricao;

	private String percentualEsgotoConsumidaColetada;
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getPercentualEsgotoConsumidaColetada() {
		return percentualEsgotoConsumidaColetada;
	}

	public void setPercentualEsgotoConsumidaColetada(
			String percentualEsgotoConsumidaColetada) {
		this.percentualEsgotoConsumidaColetada = percentualEsgotoConsumidaColetada;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}



}
