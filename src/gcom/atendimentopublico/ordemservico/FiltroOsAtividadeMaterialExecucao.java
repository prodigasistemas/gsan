package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

public class FiltroOsAtividadeMaterialExecucao extends Filtro {
	
	private static final long serialVersionUID = 1L;
	
	public FiltroOsAtividadeMaterialExecucao() {}
	
	
	public FiltroOsAtividadeMaterialExecucao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	
	public final static String ID = "id";
	
	public final static String ID_ORDEM_SERVICO_ATIVIDADE = "ordemServicoAtividade.id";
	
	public final static String ID_MATERIAL = "material.id";

}
