package gcom.gui.operacional;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * 
 * @author Fernando Fontelles Filho
 */

public class FiltrarSistemaAbastecimentoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String codigo;

	private String descricao;
	private String descricaoAbreviada;

	private String idFonteCaptacao;
	private String descricaoFonteCaptacao;
	
	private String tipoCaptacao;
	private String descricaoTipoCaptacao;

	private String indicadorUso;
	
	private String tipoPesquisa;
	
	private Boolean tipoPesquisaFonte;
	
	public Boolean getTipoPesquisaFonte() {
		return tipoPesquisaFonte;
	}

	public void setTipoPesquisaFonte(Boolean tipoPesquisaFonte) {
		this.tipoPesquisaFonte = tipoPesquisaFonte;
	}

	public String getMesAno(Integer anoMes){
		String anoMesStr = anoMes + "";
		String mesAno = "";
		
		mesAno = anoMesStr.substring(0,4) + "/" + anoMesStr.substring(4,6);
		
		return mesAno;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public String getDescricaoFonteCaptacao() {
		return descricaoFonteCaptacao;
	}

	public void setDescricaoFonteCaptacao(String descricaoFonteCaptacao) {
		this.descricaoFonteCaptacao = descricaoFonteCaptacao;
	}

	public String getIdFonteCaptacao() {
		return idFonteCaptacao;
	}

	public void setIdFonteCaptacao(String idFonteCaptacao) {
		this.idFonteCaptacao = idFonteCaptacao;
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

	public String getDescricaoTipoCaptacao() {
		return descricaoTipoCaptacao;
	}

	public void setDescricaoTipoCaptacao(String descricaoTipoCaptacao) {
		this.descricaoTipoCaptacao = descricaoTipoCaptacao;
	}

	public String getTipoCaptacao() {
		return tipoCaptacao;
	}

	public void setTipoCaptacao(String tipoCaptacao) {
		this.tipoCaptacao = tipoCaptacao;
	}
	
}
