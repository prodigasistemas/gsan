package gcom.gui.arrecadacao;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0758]FILTRAR FORMA DE ARRECADACAO
 * 
 * @author Vinicius Medeiros
 * @date 09/04/2008
 */

public class FiltrarArrecadacaoFormaActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String idArrecadacaoForma;

	private String descricao;

	private String codigoArrecadacaoForma;
	
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

	
	public String getCodigoArrecadacaoForma() {
		return codigoArrecadacaoForma;
	}

	public void setCodigoArrecadacaoForma(String codigoArrecadacaoForma) {
		this.codigoArrecadacaoForma = codigoArrecadacaoForma;
	}

	public String getIdArrecadacaoForma() {
		return idArrecadacaoForma;
	}

	public void setIdArrecadacaoForma(String idArrecadacaoForma) {
		this.idArrecadacaoForma = idArrecadacaoForma;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

}
