package gcom.seguranca;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * 
 * @author Rodrigo Cabral
 */

public class FiltrarConsultaCadastroCdlActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	

	private String cpfCliente; 
	
	private String codigoCliente;
	
	private String cnpjCliente;
	
	private String nomeCliente;
	
	private String idMatriculaFuncionario;
	
	private String desMatriculaFuncionario;
	
	private String nomeUsuario;
	
	private String cpfUsuario;
	
	private String loginUsuario;
	
	private String periodoAcessoInicial;
	
	private String periodoAcessoFinal;
	
	private String acaoOperador;
	
	private String tipoPesquisa;

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	
	public String getIdMatriculaFuncionario() {
		return idMatriculaFuncionario;
	}

	public void setIdMatriculaFuncionario(String idMatriculaFuncionario) {
		this.idMatriculaFuncionario = idMatriculaFuncionario;
	}

	public String getDesMatriculaFuncionario() {
		return desMatriculaFuncionario;
	}

	public void setDesMatriculaFuncionario(String desMatriculaFuncionario) {
		this.desMatriculaFuncionario = desMatriculaFuncionario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getPeriodoAcessoInicial() {
		return periodoAcessoInicial;
	}

	public void setPeriodoAcessoInicial(String periodoAcessoInicial) {
		this.periodoAcessoInicial = periodoAcessoInicial;
	}

	public String getPeriodoAcessoFinal() {
		return periodoAcessoFinal;
	}

	public void setPeriodoAcessoFinal(String periodoAcessoFinal) {
		this.periodoAcessoFinal = periodoAcessoFinal;
	}

	public String getAcaoOperador() {
		return acaoOperador;
	}

	public void setAcaoOperador(String acaoOperador) {
		this.acaoOperador = acaoOperador;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCnpjCliente() {
		return cnpjCliente;
	}

	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}

	
	
}
