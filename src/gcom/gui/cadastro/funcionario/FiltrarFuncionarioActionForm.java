package gcom.gui.cadastro.funcionario;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * @author Rômulo Aurélio
 * @date 13/04/2007
 */
public class FiltrarFuncionarioActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	private String atualizar;
	
	private String matricula;
	
	private String funcionarioCargo;
	
	private String nome;
	
	private String numeroCpf;
	
	private String dataNascimento;
	
	private String empresa;
	
	private String idUnidade;
	
	private String nomeUnidade;

	/**
	 * @return Retorna o campo funcionarioCargo.
	 */
	public String getFuncionarioCargo() {
		return funcionarioCargo;
	}

	/**
	 * @param funcionarioCargo O funcionarioCargo a ser setado.
	 */
	public void setFuncionarioCargo(String funcionarioCargo) {
		this.funcionarioCargo = funcionarioCargo;
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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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

	public String getAtualizar() {
		return atualizar;
	}

	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
	}

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

}
