package gcom.gui.cobranca.contratoparcelamento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * 
 * Form que Filtra ResolucaoDiretoriaContratoParcelamentoCliente
 * 
 * @author Paulo Otávio
 * @date 17/03/2011
 */
public class FiltrarResolucaoDiretoriaContratoParcelamentoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String numero = "";
	
	private String assunto = "";
	
	private String dataVigenciaInicial = "";
	
	private String dataVigenciaFinal = "";
	
	private Short indicadorAtualizar = null;
	
	public FiltrarResolucaoDiretoriaContratoParcelamentoActionForm(){}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getNumero() {
		return numero;
	}

	public Short getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	public void setIndicadorAtualizar(Short indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDataVigenciaFinal() {
		return dataVigenciaFinal;
	}

	public void setDataVigenciaFinal(String dataVigenciaFinal) {
		this.dataVigenciaFinal = dataVigenciaFinal;
	}

	public String getDataVigenciaInicial() {
		return dataVigenciaInicial;
	}

	public void setDataVigenciaInicial(String dataVigenciaInicial) {
		this.dataVigenciaInicial = dataVigenciaInicial;
	}

}
