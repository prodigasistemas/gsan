package gcom.gerencial.cadastro.localidade;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroGLocalidade extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	public FiltroGLocalidade(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public FiltroGLocalidade() {
	}

	public final static String ID = "id";

	public final static String NOME_LOCALIDADE = "nomelocalidade";

}
