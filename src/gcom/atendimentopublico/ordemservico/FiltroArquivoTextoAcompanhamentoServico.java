package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * 
 * @author Bruno Barros
 *
 */
public class FiltroArquivoTextoAcompanhamentoServico extends Filtro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String ID = "id";
	public static final String EQUIPE = "equipe.id";
	public static final String DT_PROGRAMACAO = "dataProgramacao";
	public static final String IMEI = "imei";
}
