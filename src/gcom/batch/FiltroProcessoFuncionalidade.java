package gcom.batch;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de um
 * processofuncionalidade
 * 
 * @author Rodrigo Silveira
 * @created 22/07/2006
 */

public class FiltroProcessoFuncionalidade extends Filtro implements
		Serializable {
	
	private static final long serialVersionUID = 1L;
	public FiltroProcessoFuncionalidade() {
	}

	public FiltroProcessoFuncionalidade(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID_PROCESSO = "processo.id";
	
	public final static String PROCESSO_PROCESSO_TIPO_ID = "processo.processoTipo.id";	

	public final static String SEQUENCIAL_EXECUCAO = "sequencialExecucao";

	public static final String INDICADOR_USO = "indicadorUso";

}
