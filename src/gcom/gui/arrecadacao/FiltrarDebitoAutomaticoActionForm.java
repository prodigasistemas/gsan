package gcom.gui.arrecadacao;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC00802] Filtrar Debito Automatico
 * 
 * @author Bruno Barros
 *
 * @date 23/05/2008
 */
public class FiltrarDebitoAutomaticoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
//	private String idDebitoAutomatico;
	private String matricula;
	private String bancoID;
	private String agenciaCodigo;
	private String bancoDescricao;
	private String agenciaDescricao;
	private String indicadorAtualizar;
	
	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}
	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}
	public String getAgenciaCodigo() {
		return agenciaCodigo;
	}
	public void setAgenciaCodigo(String agenciaCodigo) {
		this.agenciaCodigo = agenciaCodigo;
	}
	public String getBancoID() {
		return bancoID;
	}
	public void setBancoID(String bancoID) {
		this.bancoID = bancoID;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getAgenciaDescricao() {
		return agenciaDescricao;
	}
	public void setAgenciaDescricao(String agenciaDescricao) {
		this.agenciaDescricao = agenciaDescricao;
	}
	public String getBancoDescricao() {
		return bancoDescricao;
	}
	public void setBancoDescricao(String bancoDescricao) {
		this.bancoDescricao = bancoDescricao;
	}
	
//	public String getIdDebitoAutomatico() {
//		return idDebitoAutomatico;
//	}	
//	public void setIdDebitoAutomatico(String idDebitoAutomatico) {
//		this.idDebitoAutomatico = idDebitoAutomatico;
//	}
}
