package gcom.batch;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de um processo tipo
 * 
 * @author Rodrigo Silveira
 * @created 09/08/2006
 */

public class FiltroProcessoTipo extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	public FiltroProcessoTipo() {
		
		
	}

	public FiltroProcessoTipo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";

}
