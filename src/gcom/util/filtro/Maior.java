package gcom.util.filtro;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Hugo Leonardo
 * @date 22/12/2010
 */
public class Maior extends FiltroParametro {
	private static final long serialVersionUID = 1L;
    private Object numero;

    /**
     * Construtor da classe MenorQue
     * 
     * @param nomeAtributoIntervalo
     *            Descri��o do par�metro
     * @param numero
     *            Descri��o do par�metro
     */
    public Maior(String nomeAtributoIntervalo, Object numero) {
        super(nomeAtributoIntervalo);
        this.numero = numero;
    }

    /**
     * Construtor da classe ParametroNulo
     * 
     * @param nomeAtributoIntervalo
     *            Descri��o do par�metro
     * @param numero
     *            Descri��o do par�metro
     * @param conector
     *            Conector da query
     */
    public Maior(String nomePrimeiroAtributo, Object numero, String conector) {
        super(nomePrimeiroAtributo, conector);
        this.numero = numero;
    }

    /**
     * Construtor da classe MenorQue
     * 
     * @param nomeAtributo
     *            Descri��o do par�metro
     * @param numero
     *            Descri��o do par�metro
     * @param conector
     *            Descri��o do par�metro
     * @param numeroParametrosIsoladosConector
     *            Descri��o do par�metro
     */
    public Maior(String nomeAtributo, Object numero, String conector,
            int numeroParametrosIsoladosConector) {
        super(nomeAtributo, conector, numeroParametrosIsoladosConector);
        this.numero = numero;
    }
    
    /**
     * Construtor da classe MenorQue
     * 
     * @param nomeAtributo
     *            Descri��o do par�metro
     * @param numero
     *            Descri��o do par�metro
     * @param numeroParametrosIsoladosConector
     *            Descri��o do par�metro
     */
    public Maior(String nomeAtributo, Object numero, int numeroParametrosIsoladosConector) {
        super(nomeAtributo, numeroParametrosIsoladosConector);
        this.numero = numero;
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
