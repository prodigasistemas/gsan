package gcom.gui.atendimentopublico;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0846]FILTRAR LIGACAO DE ESGOTO ESGOTAMENTO
 * 
 * @author Arthur Carvalho
 * @date 25/08/2008
 */

public class FiltrarLigacaoEsgotoEsgotamentoActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String id;
	private String descricao;
	private String quantidadeMesesSituacaoEspecial;
	private String faturamentoSituacaoTipo;
	private String faturamentoSituacaoMotivo;
	private String indicadorUso;
	private String indicadorAtualizar;
	private String tipoPesquisa;

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getQuantidadeMesesSituacaoEspecial() {
		return quantidadeMesesSituacaoEspecial;
	}
	public void setQuantidadeMesesSituacaoEspecial(
			String quantidadeMesesSituacaoEspecial) {
		this.quantidadeMesesSituacaoEspecial = quantidadeMesesSituacaoEspecial;
	}
	public String getFaturamentoSituacaoMotivo() {
		return faturamentoSituacaoMotivo;
	}
	public void setFaturamentoSituacaoMotivo(String faturamentoSituacaoMotivo) {
		this.faturamentoSituacaoMotivo = faturamentoSituacaoMotivo;
	}
	public String getFaturamentoSituacaoTipo() {
		return faturamentoSituacaoTipo;
	}
	public void setFaturamentoSituacaoTipo(String faturamentoSituacaoTipo) {
		this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
	}
	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}
	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}
	public String getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}


}	
