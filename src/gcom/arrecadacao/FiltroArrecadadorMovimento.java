package gcom.arrecadacao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de um Movimento Arrecadador
 * 
 * @author Tiago Moreno
 * @created 07 de Fev de 2006
 */

public class FiltroArrecadadorMovimento extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe FiltroClienteEndereco
	 */
	public FiltroArrecadadorMovimento() {
	}

	/**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroArrecadadorMovimento(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	/**
	 * Description of the Field
	 */
	public final static String BANCO = "codigoBanco";

	/**
	 * Description of the Field
	 */
	public final static String REMESSA = "codigoRemessa";

	/**
	 * Description of the Field
	 */
	public final static String IDENTIFICACAO_SERVICO = "descricaoIdentificacaoServico";

	/**
	 * Description of the Field
	 */
	public final static String SEQUENCIA_ARQUIVO = "numeroSequencialArquivo";
	
	/**
	 * Description of the Field
	 */
	public final static String DATA_GERACAO = "dataGeracao";
	
	/**
	 * Description of the Field
	 */
	public final static String DATA_ULTIMA_ALTERACAO = "ultimaAlteracao";
	
	/**
	 * Description of the Field
	 */
	public final static String ALIAS_ARRECADADOR_MOVIMENTO_ITEM = "ami";
	
	/**
	 * Description of the Field
	 */
	public final static String ARRECADADOR_MOVIMENTO_ITEM_DESCRICAO_OCORRENCIA = ALIAS_ARRECADADOR_MOVIMENTO_ITEM + ".descricaoOcorrencia";
	
	/**
	 * Description of the Field
	 */
	public final static String ARRECADADOR_MOVIMENTO_ITEM_INDICADOR_ACEITACAO = ALIAS_ARRECADADOR_MOVIMENTO_ITEM + ".indicadorAceitacao";

}
