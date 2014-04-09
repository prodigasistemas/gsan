package gcom.gui.arrecadacao;

import org.apache.struts.validator.ValidatorActionForm;


/**
 *  
 * @author 	Vinícius Medeiros 
 * @date  	09/04/2008
 */

public class AtualizarArrecadacaoFormaActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	private String idArrecadacaoForma;
	
	private String descricao;
		
	private String codigoArrecadacaoForma;
	
	private String dataUltimaAlteracao;

	public String getCodigoArrecadacaoForma() {
		return codigoArrecadacaoForma;
	}

	public void setCodigoArrecadacaoForma(String codigoArrecadacaoForma) {
		this.codigoArrecadacaoForma = codigoArrecadacaoForma;
	}

	public String getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(String dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIdArrecadacaoForma() {
		return idArrecadacaoForma;
	}

	public void setIdArrecadacaoForma(String idArrecadacaoForma) {
		this.idArrecadacaoForma = idArrecadacaoForma;
	}
	
	
}
