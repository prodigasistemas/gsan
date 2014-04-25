package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

public class FiltroOSAtividadeProgramacaoAcompanhamentoServico extends Filtro {
	
	private static final long serialVersionUID = 1L;
	
	public FiltroOSAtividadeProgramacaoAcompanhamentoServico() {}
	
	
	public FiltroOSAtividadeProgramacaoAcompanhamentoServico(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	
	public final static String ID = "id";
	
	public final static String ID_OS_ACOMP_SERVICO = "osProgramacaoAcompanhamentoServico.id";
	
	public final static String ID_ATIVIDADE = "atividade.id";

}
