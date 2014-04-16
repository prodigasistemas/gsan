package gcom.batch;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de uma UnidadeIniciada
 * 
 * @author Rodrigo Silveira
 * @created 01/08/2006
 */

public class FiltroUnidadeIniciada extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	public FiltroUnidadeIniciada() {
	}

	public FiltroUnidadeIniciada(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";

	public final static String ID_FUNCIONALIDADE_INICIADA = "funcionalidadeIniciada.id";

	public final static String DATA_HORA_TERMINO = "dataHoraTermino";
	
	public final static String ID_UNIDADE_INICIADA_SITUACAO = "unidadeSituacao.id";

}
