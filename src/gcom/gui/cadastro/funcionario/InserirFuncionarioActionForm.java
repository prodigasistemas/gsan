package gcom.gui.cadastro.funcionario;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * @author gcom
 *
 */
public class InserirFuncionarioActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String matricula;
	private String descricaoCargo;
	private String funcionarioCargo;
	private String nome;
	private String empresa;
	private String idUnidade;
	private String nomeUnidade;
	private String numeroCpf;
	private String dataNascimento;
	
	
	
	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNumeroCpf() {
		return numeroCpf;
	}

	public void setNumeroCpf(String numeroCpf) {
		this.numeroCpf = numeroCpf;
	}

	public String getDescricaoCargo() {
		return descricaoCargo;
	}

	public void setDescricaoCargo(String descricaoCargo) {
		this.descricaoCargo = descricaoCargo;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(String idUnidade) {
		this.idUnidade = idUnidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeUnidade() {
		return nomeUnidade;
	}

	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return Retorna o campo cargo.
	 */
	public String getFuncionarioCargo() {
		return funcionarioCargo;
	}

	/**
	 * @param cargo O cargo a ser setado.
	 */
	public void setFuncionarioCargo(String funcionarioCargo) {
		this.funcionarioCargo = funcionarioCargo;
	}

}
