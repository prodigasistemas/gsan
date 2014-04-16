package gcom.gui.atendimentopublico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author Administrador
 * @date 17/01/2007
 */
public class EmitirSegundaViaContaInternetActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;


	private String matricula;

	private String nomeCliente;
	
	private String dataDebito;
	
	private String elo;
	
	private String debitoACobrar;
	
	private String valorDebito;
	
	private String cpf;
	
	private String cnpj;
	
	private String valorEncargosACobrar;
	private String valorOutrosServicosACobrar;
	
	public String getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}

	public String getDataDebito() {
		return dataDebito;
	}

	public void setDataDebito(String dataDebito) {
		this.dataDebito = dataDebito;
	}

	public String getDebitoACobrar() {
		return debitoACobrar;
	}

	public void setDebitoACobrar(String debitoACobrar) {
		this.debitoACobrar = debitoACobrar;
	}

	

	public String getElo() {
		return elo;
	}

	public void setElo(String elo) {
		this.elo = elo;
	}

	/**
	 * @return Retorna o campo matricula.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * @param matricula O matricula a ser setado.
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return Retorna o campo nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente O nomeCliente a ser setado.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getValorEncargosACobrar() {
		return valorEncargosACobrar;
	}

	public void setValorEncargosACobrar(String valorEncargosACobrar) {
		this.valorEncargosACobrar = valorEncargosACobrar;
	}

	public String getValorOutrosServicosACobrar() {
		return valorOutrosServicosACobrar;
	}

	public void setValorOutrosServicosACobrar(String valorOutrosServicosACobrar) {
		this.valorOutrosServicosACobrar = valorOutrosServicosACobrar;
	}

}
