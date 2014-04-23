package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

public class FiltroOsExecucaoEquipe extends Filtro {
	
	private static final long serialVersionUID = 1L;
	
	public FiltroOsExecucaoEquipe() {}
	
	
	public FiltroOsExecucaoEquipe(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	
	public final static String ID_EQUIPE = "equipe.id";
	
	public final static String ID_OS_ATIVIDADE_PERIODO_EXECUCAO = "osAtividadePeriodoExecucao.id";

}
