package gcom.util.filtro;

/**
 * Esta classe representa um par�metro de compara��o para o filtro, onde �
 * informado dois campos do BD para fazer a compara��o menorque
 * 
 * @author Rodrigo Silveira
 * @date 27/03/2006
 */
public class MenorQueComparacaoColuna extends FiltroParametro {
	private static final long serialVersionUID = 1L;
	private String nomeAtributoIntervaloComparacao;

	public MenorQueComparacaoColuna(String nomeAtributoIntervalo,
			String nomeAtributoIntervaloComparacao) {
		super(nomeAtributoIntervalo);
		this.nomeAtributoIntervaloComparacao = nomeAtributoIntervaloComparacao;
	}

	public MenorQueComparacaoColuna(String nomeAtributoIntervalo,
			String nomeAtributoIntervaloComparacao, String conector) {
		super(nomeAtributoIntervalo, conector);
		this.nomeAtributoIntervaloComparacao = nomeAtributoIntervaloComparacao;
	}

	public MenorQueComparacaoColuna(String nomeAtributo,
			String nomeAtributoIntervaloComparacao, String conector,
			int numeroParametrosIsoladosConector) {
		super(nomeAtributo, conector, numeroParametrosIsoladosConector);
		this.nomeAtributoIntervaloComparacao = nomeAtributoIntervaloComparacao;
	}

	public String getNomeAtributoIntervaloComparacao() {
		return nomeAtributoIntervaloComparacao;
	}

	public void setNomeAtributoIntervaloComparacao(
			String nomeAtributoIntervaloComparacao) {
		this.nomeAtributoIntervaloComparacao = nomeAtributoIntervaloComparacao;
	}

	@Override
	public Object getValor() {
		
		return null;
	}

}
