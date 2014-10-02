package gcom.util.filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class Menor extends FiltroParametro {
	private static final long serialVersionUID = 1L;
    private String nomeSegundoAtributo;
    private Object numero;

    /**
     * Construtor da classe MenorQue
     * 
     * @param nomeAtributoIntervalo
     *            Descrição do parâmetro
     * @param numero
     *            Descrição do parâmetro
     */
    public Menor(String nomeAtributoIntervalo, Object numero) {
        super(nomeAtributoIntervalo);
        this.numero = numero;
    }

    /**
     * Construtor da classe ParametroNulo
     * 
     * @param nomeAtributoIntervalo
     *            Descrição do parâmetro
     * @param numero
     *            Descrição do parâmetro
     * @param conector
     *            Conector da query
     */
    public Menor(String nomePrimeiroAtributo, Object numero, String conector) {
        super(nomePrimeiroAtributo, conector);
        this.numero = numero;
    }

    /**
     * Construtor da classe MenorQue
     * 
     * @param nomeAtributo
     *            Descrição do parâmetro
     * @param numero
     *            Descrição do parâmetro
     * @param conector
     *            Descrição do parâmetro
     * @param numeroParametrosIsoladosConector
     *            Descrição do parâmetro
     */
    public Menor(String nomeAtributo, Object numero, String conector,
            int numeroParametrosIsoladosConector) {
        super(nomeAtributo, conector, numeroParametrosIsoladosConector);
        this.numero = numero;
    }
    
    /**
     * Construtor da classe MenorQue
     * 
     * @param nomeAtributo
     *            Descrição do parâmetro
     * @param numero
     *            Descrição do parâmetro
     * @param numeroParametrosIsoladosConector
     *            Descrição do parâmetro
     */
    public Menor(String nomeAtributo, Object numero, int numeroParametrosIsoladosConector) {
        super(nomeAtributo, numeroParametrosIsoladosConector);
        this.numero = numero;
    }

    /**
     * Retorna o valor de numero
     * 
     * @return O valor de numero
     */
    public String getNomeSegundoAtributo() {
        return nomeSegundoAtributo;
    }

    /**
     * Seta o valor de numero
     * 
     * @param numero
     *            O novo valor de numero
     */
    public void setNumero(String nomeSegundoAtributo) {
        this.nomeSegundoAtributo = nomeSegundoAtributo;
    }

	public Object getNumero() {
		return numero;
	}

	public void setNumero(Object numero) {
		this.numero = numero;
	}

	@Override
	public Object getValor() {
		
		return null;
	}

}
