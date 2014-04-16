package gcom.gui.arrecadacao;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * 
 * @author Vinicius Medeiros
 * @date 08/04/2007
 */
public class InserirArrecadacaoFormaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	String codigoArrecadacaoForma;
	
	String descricao;

	String IndicadorUso;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIndicadorUso() {
		return IndicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		IndicadorUso = indicadorUso;
	}

	public String getCodigoArrecadacaoForma() {
		return codigoArrecadacaoForma;
	}

	public void setCodigoArrecadacaoForma(String codigoArrecadacaoForma) {
		this.codigoArrecadacaoForma = codigoArrecadacaoForma;
	}

	
}
