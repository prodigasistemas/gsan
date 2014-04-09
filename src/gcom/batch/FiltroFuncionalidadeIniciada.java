package gcom.batch;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de uma
 * FuncionalidadeIniciada
 * 
 * @author Rodrigo Silveira
 * @created 31/07/2006
 */

public class FiltroFuncionalidadeIniciada extends Filtro implements
		Serializable {
	
	private static final long serialVersionUID = 1L;
	public FiltroFuncionalidadeIniciada() {
	}

	public FiltroFuncionalidadeIniciada(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";

	public final static String PROCESSO_INICIADO_ID = "processoIniciado.id";

	public final static String PROCESSO_PROCESSO_SITUACAO_ID = "processoIniciado.processoSituacao.id";

	public final static String PROCESSO_PROCESSO_PRECEDENTE_PROCESSO_SITUACAO_ID = "processoIniciado.processoIniciadoPrecedente.processoSituacao.id";
	
	public final static String FUNCIONALIDADE_SITUACAO = "funcionalidadeSituacao.id";

	public final static String SEQUENCIAL_EXECUCAO = "processoFuncionalidade.sequencialExecucao";
	
	public final static String FUNCIONALIDADE_ID = "processoFuncionalidade.funcionalidade.id";

}
