package gcom.util.filtro;

/**
 * Representa um filtro de busca textual
 * 
 * @author rodrigo
 */
public class ComparacaoTextoCompleto extends FiltroParametro {
	private static final long serialVersionUID = 1L;
	private String valor;

	/**
	 * Construtor da classe ComparacaoTexto
	 * 
	 * @param nomeAtributo
	 *            Descrição do parâmetro
	 * @param valor
	 *            Descrição do parâmetro
	 */
	public ComparacaoTextoCompleto(String nomeAtributo, String valor) {
		super(nomeAtributo);
		this.valor = "%" + valor + "%";
	}

	/**
	 * Construtor da classe ComparacaoTexto
	 * 
	 * @param nomeAtributo
	 *            Descrição do parâmetro
	 * @param valor
	 *            Descrição do parâmetro
	 * @param conector
	 *            Descrição do parâmetro
	 */
	public ComparacaoTextoCompleto(String nomeAtributo, String valor,
			String conector) {
		super(nomeAtributo, conector);
		this.valor = "%" + valor + "%";
	}

	/**
	 * Construtor da classe ComparacaoTexto
	 * 
	 * @param nomeAtributo
	 *            Descrição do parâmetro
	 * @param valor
	 *            Descrição do parâmetro
	 * @param conector
	 *            Descrição do parâmetro
	 * @param numeroParametrosIsoladosConector
	 *            Descrição do parâmetro
	 */
	public ComparacaoTextoCompleto(String nomeAtributo, Object valor,
			String conector, int numeroParametrosIsoladosConector) {
		super(nomeAtributo, conector, numeroParametrosIsoladosConector);
		this.valor = "%" + valor + "%";
	}

	/**
	 * Retorna o valor de valor
	 * 
	 * @return O valor de valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Seta o valor de valor
	 * 
	 * @param valor
	 *            O novo valor de valor
	 */
	public void setValor(String valor) {
		this.valor = "%" + valor + "%";
	}

}
