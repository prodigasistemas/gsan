package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

/**
 * Filtrar Serviço Tipo Prioridade
 * 
 * @author lms
 * @since 01/08/2006
 */
public class FiltroServicoTipoPrioridade extends Filtro {
	
	private static final long serialVersionUID = 1L;
	
	public FiltroServicoTipoPrioridade() {
	}
	
	public FiltroServicoTipoPrioridade(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";
	public final static String DESCRICAO = "descricao";
	public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";
	public final static String INDICADOR_USO = "indicadorUso";
	public final static String PRAZO_EXECUCAO_INICIO = "prazoExecucaoInicio";
	public final static String PRAZO_EXECUCAO_FIM = "prazoExecucaoFim";

}
