package gcom.gui.atendimentopublico;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC00738] Gerar Certidao Negativa
 * 
 * @author Bruno Barros
 *
 * @date 22/01/2008
 */

public class GerarCertidaoNegativaActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idImovel;
	private String matriculaImovel;
	private String enderecoImovel;
	private String cpfCnpj;
	private String nomeClienteUsuario;

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	
	public String getCpfCnpj() {
	
		return cpfCnpj;
	}

	
	public void setCpfCnpj(String cpfCnpj) {
	
		this.cpfCnpj = cpfCnpj;
	}

	
	public String getEnderecoImovel() {
	
		return enderecoImovel;
	}

	
	public void setEnderecoImovel(String enderecoImovel) {
	
		this.enderecoImovel = enderecoImovel;
	}

	public String getNomeClienteUsuario() {
	
		return nomeClienteUsuario;
	}

	
	public void setNomeClienteUsuario(String nomeClienteUsuario) {
	
		this.nomeClienteUsuario = nomeClienteUsuario;
	}
}
