package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * @author Bruno Barros
 * @since 08/2011
 * 
 */
public class FiltroOSProgramacaoAcompanhamentoServico extends Filtro implements
		Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final String ID_ARQUIVO = "arquivoTextoAcompanhamentoServico.id";
	public static final String ORDEM_SERVICO = "ordemServico.id";
	public static final String IC_EXCLUIDO = "indicadorExcluido";
	public static final String DATA_PROGRAMACAO = "dataProgramacao";
}
