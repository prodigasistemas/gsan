package gcom.cobranca.contratoparcelamento;

import gcom.util.filtro.Filtro;

public class FiltroTipoRelacao extends Filtro {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FiltroTipoRelacao() {
	}

	public FiltroTipoRelacao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}


	public final static String ID = "id";
	public final static String INDICADOR_USO = "indicadorUso";
}
