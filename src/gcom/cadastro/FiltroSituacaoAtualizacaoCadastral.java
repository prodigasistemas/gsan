package gcom.cadastro;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroSituacaoAtualizacaoCadastral extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String ID = "id";

	public static final String DESCRICAO = "descricao";

	public FiltroSituacaoAtualizacaoCadastral() {
	}

	public FiltroSituacaoAtualizacaoCadastral(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
}
