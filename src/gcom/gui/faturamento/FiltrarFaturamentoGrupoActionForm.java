package gcom.gui.faturamento;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0771]FILTRAR GRUPO DE FATURAMENTO
 * 
 * @author Vinicius Medeiros
 * @date 23/04/2008
 */

public class FiltrarFaturamentoGrupoActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String id;
	private String descricao;
	private String descricaoAbreviada;
	private String indicadorUso;
	private String anoMesReferencia;
	private String diaVencimento;
	private String indicadorVencimentoMesFatura;
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

	public String getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(String anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public String getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(String diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIndicadorVencimentoMesFatura() {
		return indicadorVencimentoMesFatura;
	}

	public void setIndicadorVencimentoMesFatura(String indicadorVencimentoMesFatura) {
		this.indicadorVencimentoMesFatura = indicadorVencimentoMesFatura;
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
