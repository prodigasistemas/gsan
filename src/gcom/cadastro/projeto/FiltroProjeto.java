package gcom.cadastro.projeto;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroProjeto extends Filtro implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor Default
	 */
	public FiltroProjeto() {
	}
	/**
	 * Construtor passando order by
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroProjeto(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	
	public final static String ID = "id";

	public final static String NOME = "nome";

	public final static String NOME_ABREVIADO = "nomeAbreviado";
	
	public final static String ID_ORGAO_FINACIADOR = "orgaoFinanciador.id";
	
	public final static String DATA_FIM = "dataFim";
}
