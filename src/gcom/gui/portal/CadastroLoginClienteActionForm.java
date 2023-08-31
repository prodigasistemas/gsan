package gcom.gui.portal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;

import gcom.util.FormatoData;
import gcom.util.Util;

public class CadastroLoginClienteActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private static final String ERR_NOME = "err-nome";
	private static final String ERR_CPF_CNPJ = "err-cpf-cnpj";
	private static final String ERR_DATA_NASCIMENTO = "err-data-nascimento";
	private static final String ERR_CELULAR = "err-celular";
	private static final String ERR_EMAIL = "err-email";
	private static final String ERR_SENHA = "err-senha";
	
	private String matriculaInformada;
	
	private String matriculaSelecionada;

	private String enderecoImovel;

	private String nome;

	private String dataNascimento;

	private String cpfOuCnpj;

	private String celular;

	private String email;

	private String senha;
	
	private String confirmarSenha;
	
	private ActionErrors errors;

	public String getMatriculaInformada() {
		return matriculaInformada;
	}

	public void setMatriculaInformada(String matriculaInformada) {
		this.matriculaInformada = matriculaInformada;
	}

	public String getMatriculaSelecionada() {
		return matriculaSelecionada;
	}

	public void setMatriculaSelecionada(String matriculaSelecionada) {
		this.matriculaSelecionada = matriculaSelecionada;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public void reset(HttpServletRequest request) {
		this.matriculaInformada = null;
		this.matriculaSelecionada = null;
		this.enderecoImovel = null;
		this.nome = null;
		this.dataNascimento = null;
		this.cpfOuCnpj = null;
		this.celular = null;
		this.email = null;
		this.senha = null;
		this.confirmarSenha = null;

		request.removeAttribute(CadastroLoginClienteAction.ATRIBUTO_ERRO);
		request.removeAttribute(CadastroLoginClienteAction.ATRIBUTO_ERRO_FORM);
	}
	
	public ActionErrors validate() {
		errors = new ActionErrors();
		
		validarNome();
		validarCpfOuCnpj();
		if(cpfOuCnpj.length() < 18){
		validarDataNascimento();
		}
		validarCelular();
		validarEmail();
		validarSenha();

		return errors;
	}
	
	
	public boolean matriculaInformadaInvalida() {
		return campoInvalido(matriculaInformada);
	}
	
	private boolean campoInvalido(String campo) {
		return campo == null || campo.trim().equals("");
	}

	private void validarNome() {
		if (campoInvalido(nome)) {
			errors.add(ERR_NOME, new ActionError("errors.portal.campo_obrigatorio"));
		}
	}

	private void validarCpfOuCnpj() {
		if (campoInvalido(cpfOuCnpj)) {
			errors.add(ERR_CPF_CNPJ, new ActionError("errors.portal.campo_obrigatorio"));
		} else if (Util.cpfCnpjInvalido(Util.removerSimbolosPontuacao(cpfOuCnpj))) {
			errors.add(ERR_CPF_CNPJ, new ActionError("errors.portal.invalido", "CPF ou CNPJ"));
		}
	}

	private void validarDataNascimento() {		
		if (campoInvalido(dataNascimento)) {
			errors.add(ERR_DATA_NASCIMENTO, new ActionError("errors.portal.campo_obrigatorio"));
		} else if (dataNascimentoInvalida()) {
			errors.add(ERR_DATA_NASCIMENTO, new ActionError("errors.portal.invalida", "Data de Nascimento"));
		}	  
	}

	private void validarCelular() {
		if (campoInvalido(celular)) {
			errors.add(ERR_CELULAR, new ActionError("errors.portal.campo_obrigatorio"));
		} else if (celular.length() < 15) {
			errors.add(ERR_CELULAR, new ActionError("errors.portal.invalido", "Número de Celular"));
		}
	}

	private void validarEmail() {
		if (campoInvalido(email)) {
			errors.add(ERR_EMAIL, new ActionError("errors.portal.campo_obrigatorio"));
		} else if (Util.isEmailInvalido(email)) {
			errors.add(ERR_EMAIL, new ActionError("errors.portal.invalido", "Email"));
		}
	}

	private void validarSenha() {
		if (campoInvalido(senha)) {
			errors.add(ERR_SENHA, new ActionError("errors.portal.campo_obrigatorio"));
		} else if (senha.length() < 8) {
			errors.add(ERR_SENHA, new ActionError("errors.portal.senha_menor_tamanho_maximo"));
		} else if (!senha.equals(confirmarSenha)) {
			errors.add(ERR_SENHA, new ActionError("errors.portal.senha_nao_confere"));
		}
	}
	
	private boolean dataNascimentoInvalida() {
		Date convertida;
		try {
			convertida = new SimpleDateFormat("dd/MM/yyyy").parse(dataNascimento);
		} catch (ParseException e) {
			return true;
		}

		String formatada = Util.formatarData(convertida, FormatoData.AMERICANO_COM_TRACO);

		return Util.dataInvalida(formatada, FormatoData.AMERICANO_COM_TRACO.getFormato());
	}
}