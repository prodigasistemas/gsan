package gcom.gui.operacional;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0596] Atualizar Sistema de Abastecimento
 * 
 * @author Fernando Fontelles Filho
 * @date 30/10/2009
 */

public class AtualizarSistemaAbastecimentoActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String id;

	private String descricao;

	private String descricaoAbreviada;

	private String fonteCaptacaoId;
	
	private String fonteCaptacaoDescricao;
	
	private Short indicadorUso;
	
	private String tipoCaptacao;
	
	private String descricaoTipoCaptacao;

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

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
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

	public String getFonteCaptacaoDescricao() {
		return fonteCaptacaoDescricao;
	}

	public void setFonteCaptacaoDescricao(String fonteCaptacaoDescricao) {
		this.fonteCaptacaoDescricao = fonteCaptacaoDescricao;
	}

	public String getFonteCaptacaoId() {
		return fonteCaptacaoId;
	}

	public void setFonteCaptacaoId(String fonteCaptacaoId) {
		this.fonteCaptacaoId = fonteCaptacaoId;
	}

	public String getId() {
		return id;
	}

	public void setId(String sistemaAbastecimentoId) {
		this.id = sistemaAbastecimentoId;
	}

	public String getMesAno(Integer anoMes) {
		String anoMesStr = anoMes + "";
		String mesAno = "";

		mesAno = anoMesStr.substring(0, 4) + "/" + anoMesStr.substring(4, 6);

		return mesAno;
	}
	
}
