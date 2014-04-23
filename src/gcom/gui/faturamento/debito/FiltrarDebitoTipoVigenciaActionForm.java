package gcom.gui.faturamento.debito;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 27/10/2006
 */

public class FiltrarDebitoTipoVigenciaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String debitoTipo;

	private String nomeDebitoTipo;

	private String valorDebitoInicial;

	private String valorDebitoFinal;

	private String atualizar;

	public String getAtualizar() {
		return atualizar;
	}

	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
	}

	public String getDebitoTipo() {
		return debitoTipo;
	}

	public void setDebitoTipo(String debitoTipo) {
		this.debitoTipo = debitoTipo;
	}

	public String getNomeDebitoTipo() {
		return nomeDebitoTipo;
	}

	public void setNomeDebitoTipo(String nomeDebitoTipo) {
		this.nomeDebitoTipo = nomeDebitoTipo;
	}

	public String getValorDebitoFinal() {
		return valorDebitoFinal;
	}

	public void setValorDebitoFinal(String valorDebitoFinal) {
		this.valorDebitoFinal = valorDebitoFinal;
	}

	public String getValorDebitoInicial() {
		return valorDebitoInicial;
	}

	public void setValorDebitoInicial(String valorDebitoInicial) {
		this.valorDebitoInicial = valorDebitoInicial;
	}

	
}
