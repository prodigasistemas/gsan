package gcom.batch;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de um processoIniciado
 * 
 * @author Rodrigo Silveira
 * @created 26/07/2006
 */

public class FiltroProcessoIniciado extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public FiltroProcessoIniciado() {
		

	}

	public FiltroProcessoIniciado(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";

	public final static String ID_PROCESSO = "processo.id";
	
	public final static String PROCESSO = "processo";
	
	public final static String NOME_ARQUIVO_PROCESSO = "processo.nomeArquivoBatch";

	public final static String PROCESSO_SITUACAO_ID = "processoSituacao.id";
	
	public final static String PROCESSO_SITUACAO = "processoSituacao";

	public final static String DATA_HORA_AGENDAMENTO = "dataHoraAgendamento";
	
	public final static String DATA_HORA_AGENDAMENTO_DESC = "dataHoraAgendamento DESC";

	public final static String DATA_HORA_INICIO = "dataHoraInicio";

	public final static String DATA_HORA_TERMINO = "dataHoraTermino";

	public final static String DATA_HORA_COMANDO = "dataHoraComando";
	
	public final static String USUARIO_ID = "usuario.id";
	
	public final static String USUARIO = "usuario";
	
	public final static String CODIGO_GRUPO = "codigoGrupoProcesso";

}
