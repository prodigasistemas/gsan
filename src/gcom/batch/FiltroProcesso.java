package gcom.batch;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de um processo
 * 
 * @author Rodrigo Silveira
 * @created 22/07/2006
 */

public class FiltroProcesso extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	public FiltroProcesso() {
	}

	public FiltroProcesso(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";

	public final static String PROCESSO_TIPO = "processoTipo";
	
	public final static String DESCRICAO_PROCESSO = "descricaoProcesso";
	
	public final static String PROCESSO_TIPO_ID = "processoTipo.id";
	
	public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";
}
