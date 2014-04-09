package gcom.gui.faturamento.debito;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author Josenildo Neves
 * @date 22/02/2010
 */
public class AtualizarDebitoTipoVigenciaActionForm extends
		ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String debitoTipo;

	private String nomeDebitoTipo;

	private String valorDebito;
	
	private String idDebitoTipoVigencia;
	
	private String dataVigenciaInicial;
	
	private String dataVigenciaFinal;
	
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
	
	public String getDebitoTipo() {
		return debitoTipo;
	}
	
	public void setDebitoTipo(String debitoTipo) {
		this.debitoTipo = debitoTipo;
	}
	
	public String getIdDebitoTipoVigencia() {
		return idDebitoTipoVigencia;
	}
	
	public void setIdDebitoTipoVigencia(String idDebitoTipoVigencia) {
		this.idDebitoTipoVigencia = idDebitoTipoVigencia;
	}
	
	public String getNomeDebitoTipo() {
		return nomeDebitoTipo;
	}
	
	public void setNomeDebitoTipo(String nomeDebitoTipo) {
		this.nomeDebitoTipo = nomeDebitoTipo;
	}
	
	public String getValorDebito() {
		return valorDebito;
	}
	
	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}
		
}
