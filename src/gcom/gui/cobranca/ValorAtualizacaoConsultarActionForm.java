package gcom.gui.cobranca;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Fernanda Paiva
 * @created 31 de Março de 2006
 */
public class ValorAtualizacaoConsultarActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	
	private String multa;

    private String juros;

    private String atualizacao;

	public String getAtualizacao() {
		return atualizacao;
	}

	public void setAtualizacao(String atualizacao) {
		this.atualizacao = atualizacao;
	}

	public String getJuros() {
		return juros;
	}

	public void setJuros(String juros) {
		this.juros = juros;
	}

	public String getMulta() {
		return multa;
	}

	public void setMulta(String multa) {
		this.multa = multa;
	}

}
