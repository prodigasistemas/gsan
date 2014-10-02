package gcom.util.filtro;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class MenorQue extends FiltroParametro {
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
    public MenorQue(String nomeAtributoIntervalo, Object numero) {
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
    public MenorQue(String nomeAtributoIntervalo, Object numero, String conector) {
        super(nomeAtributoIntervalo, conector);
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
    public MenorQue(String nomeAtributo, Object numero, String conector,
            int numeroParametrosIsoladosConector) {
        super(nomeAtributo, conector, numeroParametrosIsoladosConector);
        this.numero = numero;
    }

    /**
     * Retorna o valor de numero
     * 
     * @return O valor de numero
     */
    public Object getNumero() {
        return numero;
    }

    /**
     * Seta o valor de numero
     * 
     * @param numero
     *            O novo valor de numero
     */
    public void setNumero(Object numero) {
        this.numero = numero;
    }

	@Override
	public Object getValor() {
		
		return null;
	}

}
