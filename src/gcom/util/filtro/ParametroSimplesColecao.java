package gcom.util.filtro;

/**
 * Representa um parâmetro simples de busca de um filtro
 * 
 * @author rodrigo
 */
public class ParametroSimplesColecao extends ParametroSimples {
	private static final long serialVersionUID = 1L;
	public ParametroSimplesColecao(String nomeAtributo, Object valor,
			String conector) {
		super(nomeAtributo, valor, conector);

	}

	public ParametroSimplesColecao(String nomeAtributo, Object valor) {
		super(nomeAtributo, valor);

	}

	public ParametroSimplesColecao(String nomeAtributo, Object valor,
			String conector, int numeroParametrosIsoladosConector) {
		super(nomeAtributo, valor, conector, numeroParametrosIsoladosConector);
	}

}
