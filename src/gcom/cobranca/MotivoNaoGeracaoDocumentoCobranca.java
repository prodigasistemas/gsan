package gcom.cobranca;

import gcom.util.tabelaauxiliar.abreviada.TabelaAuxiliarAbreviada;


public class MotivoNaoGeracaoDocumentoCobranca extends TabelaAuxiliarAbreviada{


	//public static int IMOVEL_MARIO = 1;
	public static int IMOVEL_EXCLUIDO = 2;
	public static int NAO_EXISTE_DOCUMENTO_PRECEDENTE_VALIDO = 3;
	public static int NAO_EXISTE_ORDEM_SERVICO_ACAO_PRECEDENTE_EXECUTADA_PRAZO = 4;
	public static int EXISTE_DOCUMENTO_VALIDO_ACAO_SUCESSORA = 5;
	public static int EXISTE_DOCUMENTO_VALIDO_MESMO_TIPO = 6;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String[] retornaCamposChavePrimaria() {
		String []ids = {"id"}; 
		return ids;
	}


}
