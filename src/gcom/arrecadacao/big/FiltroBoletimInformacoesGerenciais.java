package gcom.arrecadacao.big;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

public class FiltroBoletimInformacoesGerenciais extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;

	public FiltroBoletimInformacoesGerenciais() {
	}

	public FiltroBoletimInformacoesGerenciais(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";

	public final static String LOCALIDADE_ID = "localidade.id";
	
	public final static String LOCALIDADE = "localidade";
}
