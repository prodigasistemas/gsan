package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

public class FiltroOsAtividadePeriodoExecucao extends Filtro {
	
	private static final long serialVersionUID = 1L;
	
	public FiltroOsAtividadePeriodoExecucao() {}
	
	
	public FiltroOsAtividadePeriodoExecucao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	
	public final static String ID = "id";
	
	public final static String DATA_INICIO = "dataInicio";
	
	public final static String DATA_FIM = "dataFim";
	
	public final static String ID_ORDEM_SERVICO_ATIVIDADE = "ordemServicoAtividade.id";

}
