package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

public class FiltroOrdemServicoAtividade extends Filtro {
	
	private static final long serialVersionUID = 1L;
	
	public FiltroOrdemServicoAtividade() {}
	
	
	public FiltroOrdemServicoAtividade(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	
	public final static String ID = "id";
	
	public final static String ID_ORDEM_SERVICO = "ordemServico.id";
	
	public final static String ID_ATIVIDADE = "atividade.id";

}
