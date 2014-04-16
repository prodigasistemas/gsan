package gcom.gui.faturamento.debito;

import org.apache.struts.validator.ValidatorActionForm;

public class InserirDebitoTipoVigenciaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String tipoDebito;
	private String nomeTipoDebito;
	private String valorDebito;
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
	public String getNomeTipoDebito() {
		return nomeTipoDebito;
	}
	public void setNomeTipoDebito(String nomeTipoDebito) {
		this.nomeTipoDebito = nomeTipoDebito;
	}
	public String getTipoDebito() {
		return tipoDebito;
	}
	public void setTipoDebito(String tipoDebito) {
		this.tipoDebito = tipoDebito;
	}
	public String getValorDebito() {
		return valorDebito;
	}
	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}
	
	
	
}
