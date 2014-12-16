package gcom.faturamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroReceitasAFaturarResumo extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;

	public FiltroReceitasAFaturarResumo() {
	}

	public FiltroReceitasAFaturarResumo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";

	public final static String GRUPO_ID = "idGrupo";
	
	public final static String ANO_MES_REFERENCIA = "anoMesReferencia";
}
