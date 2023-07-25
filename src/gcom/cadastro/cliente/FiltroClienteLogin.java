package gcom.cadastro.cliente;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

public class FiltroClienteLogin extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;

	public FiltroClienteLogin() {
	}

	public FiltroClienteLogin(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";
	public final static String CPF_CNPJ = "cpfOuCnpj";
	public final static String EMAIL = "email";
	public final static String SENHA = "senha";
}