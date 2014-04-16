package gcom.seguranca.acesso;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca da operação
 * 
 * @author Thiago Toscano
 * @date 23/03/2006
 */
public class FiltroOperacao extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor
	 */
	public FiltroOperacao() {
	}

	/**
	 * Constructor
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroOperacao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * CODIGO
	 */
	public final static String ID = "id";

	/** TABELA COLUNA */
	public final static String TABELA_COLUNA = "tabelaColuna";

	/** ID DA TABELA COLUNA */
	public final static String TABELA_COLUNA_ID = "tabelaColuna.id";

	/** FUNCIONALIDADE */
	public final static String FUNCIONALIDADE = "funcionalidade";

	/** FUNCIONALIDADE */
	public final static String FUNCIONALIDADE_ID = "funcionalidade.id";

	/** OPERACAO TIPO */
	public final static String OPERACAO_TIPO = "operacaoTipo";

	/** OPERACAO DE PESQUISA */
	public final static String OPERACAO_PESQUISA = "idOperacaoPesquisa";

	/** OPERACAO DE PESQUISA */
	public final static String OPERACAO_PESQUISA_TABELA_COLUNA = "idOperacaoPesquisa.tabelaColuna";
	
	public final static String ARGUMENTO_PESQUISA = "argumentoPesquisa";
	
	public final static String ARGUMENTO_PESQUISA_TABELA = "argumentoPesquisa.tabela";

	/** DESCRICAO */
	public final static String DESCRICAO = "descricao";

	/** CAMINHO URL */
	public final static String CAMINHO_URL = "caminhoUrl";

	/** OPERACAO TIPO */
	public final static String OPERACAO_TIPO_ID = "operacaoTipo.id";


}
