package gcom.gui.faturamento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * 
 * @author Flávio Leonardo
 */

public class FiltrarQualidadeAguaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String referencia;

	private String idLocalidade;

	private String nomeLocalidade;

	private String codigoSetor;

	private String nomeSetor;

	private String fonteCaptacao;
	
	private String descricaoFonteCaptacao;
	
	private String tipoPesquisa;
	
	private String idQualidadeAgua;
	
	private String sistemaAbastecimento;

	public String getSistemaAbastecimento() {
		return sistemaAbastecimento;
	}

	public void setSistemaAbastecimento(String sistemaAbastecimento) {
		this.sistemaAbastecimento = sistemaAbastecimento;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getCodigoSetor() {
		return codigoSetor;
	}

	public void setCodigoSetor(String codigoSetor) {
		this.codigoSetor = codigoSetor;
	}

	public String getFonteCaptacao() {
		return fonteCaptacao;
	}

	public void setFonteCaptacao(String fonteCaptacao) {
		this.fonteCaptacao = fonteCaptacao;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getNomeSetor() {
		return nomeSetor;
	}

	public void setNomeSetor(String nomeSetor) {
		this.nomeSetor = nomeSetor;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getIdQualidadeAgua() {
		return idQualidadeAgua;
	}

	public void setIdQualidadeAgua(String idQualidadeAgua) {
		this.idQualidadeAgua = idQualidadeAgua;
	}

	
	public String getMesAno(Integer anoMes){
		String anoMesStr = anoMes + "";
		String mesAno = "";
		
		mesAno = anoMesStr.substring(0,4) + "/" + anoMesStr.substring(4,6);
		
		return mesAno;
	}

	public String getDescricaoFonteCaptacao() {
		return descricaoFonteCaptacao;
	}

	public void setDescricaoFonteCaptacao(String descricaoFonteCaptacao) {
		this.descricaoFonteCaptacao = descricaoFonteCaptacao;
	}
	
}
