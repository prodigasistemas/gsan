package gcom.cadastro.cliente;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

/**
 * O filtro serve para armazenar os critérios de busca de um cadastro_unico
 */
public class FiltroCadastroUnico extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;

	public FiltroCadastroUnico() {
	}

	public FiltroCadastroUnico(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";

	public final static String NUMERO_NIS = "numeroNIS";

	public final static String CPF = "cpf";
}