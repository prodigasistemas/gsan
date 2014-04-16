package gcom.util.filtro;

/**
 * Representa um parâmetro simples de busca de um filtro
 * 
 * @author rossiter
 */
public class ParametroSimplesColecaoDiferenteDe extends ParametroSimples {
	private static final long serialVersionUID = 1L;
	public ParametroSimplesColecaoDiferenteDe(String nomeAtributo, Object valor,
			String conector) {
		super(nomeAtributo, valor, conector);

	}

	public ParametroSimplesColecaoDiferenteDe(String nomeAtributo, Object valor) {
		super(nomeAtributo, valor);

	}

}
