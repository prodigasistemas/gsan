package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

/**
 * Filtrar Atendimento Motivo Encerramento
 * 
 * @author lms
 * @since 27/07/2006
 */
public class FiltroAtendimentoMotivoEncerramento extends Filtro {
	
	private static final long serialVersionUID = 1L;
	
	public FiltroAtendimentoMotivoEncerramento() {
	}
	
	public FiltroAtendimentoMotivoEncerramento(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";
	public final static String DESCRICAO = "descricao";
	public final static String INDICADOR_USO = "indicadorUso";	
	public final static String INDICADOR_DUPLICIDADE = "indicadorDuplicidade";
	public final static String INDICADOR_EXECUCAO = "indicadorExecucao";
}
