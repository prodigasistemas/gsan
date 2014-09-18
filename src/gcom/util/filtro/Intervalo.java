package gcom.util.filtro;


/**
 * Representa um par�metro de intervalo para um filtro
 * 
 * @author rodrigo
 */
public class Intervalo extends FiltroParametro {
	private static final long serialVersionUID = 1L;
	private Object IntervaloInicial;

    private Object IntervaloFinal;

    /**
     * Construtor da classe Intervalo
     * 
     * @param nomeAtributoIntervalo
     *            Nome do atributo de que ser� feita a filtragem
     * @param IntervaloInicial
     *            Data inicial do intervalo
     * @param dataIntervaloFinal
     *            Data final do intervalo
     */
    public Intervalo(String nomeAtributoIntervalo, Object IntervaloInicial,
            Object dataIntervaloFinal) {
        super(nomeAtributoIntervalo);
        this.IntervaloInicial = IntervaloInicial;
        this.IntervaloFinal = dataIntervaloFinal;

    }

    /**
     * Construtor da classe Intervalo
     * 
     * @param nomeAtributoIntervalo
     *            Descri��o do par�metro
     * @param IntervaloInicial
     *            Descri��o do par�metro
     * @param IntervaloFinal
     *            Descri��o do par�metro
     * @param conector
     *            Descri��o do par�metro
     */
    public Intervalo(String nomeAtributoIntervalo, Object IntervaloInicial,
            Object IntervaloFinal, String conector) {
        super(nomeAtributoIntervalo, conector);
        this.IntervaloInicial = IntervaloInicial;
        this.IntervaloFinal = IntervaloFinal;

    }

    /**
     * Construtor da classe Intervalo
     * 
     * @param nomeAtributoIntervalo
     *            Descri��o do par�metro
     * @param IntervaloInicial
     *            Descri��o do par�metro
     * @param IntervaloFinal
     *            Descri��o do par�metro
     * @param conector
     *            Descri��o do par�metro
     * @param numeroParametrosIsoladosConector
     *            Descri��o do par�metro
     */
    public Intervalo(String nomeAtributoIntervalo, Object IntervaloInicial,
            Object IntervaloFinal, String conector,
            int numeroParametrosIsoladosConector) {
        super(nomeAtributoIntervalo, conector, numeroParametrosIsoladosConector);
        this.IntervaloInicial = IntervaloInicial;
        this.IntervaloFinal = IntervaloFinal;

    }

    /**
     * Retorna o valor de IntervaloFinal
     * 
     * @return O valor de IntervaloFinal
     */
    public Object getIntervaloFinal() {
        return IntervaloFinal;
    }

    /**
     * Retorna o valor de IntervaloInicial
     * 
     * @return O valor de IntervaloInicial
     */
    public Object getIntervaloInicial() {
        return IntervaloInicial;
    }

    /**
     * Seta o valor de IntervaloFinal
     * 
     * @param IntervaloFinal
     *            O novo valor de IntervaloFinal
     */
    public void setIntervaloFinal(Object IntervaloFinal) {
        this.IntervaloFinal = IntervaloFinal;
    }

    /**
     * Seta o valor de IntervaloInicial
     * 
     * @param IntervaloInicial
     *            O novo valor de IntervaloInicial
     */
    public void setIntervaloInicial(Object IntervaloInicial) {
        this.IntervaloInicial = IntervaloInicial;
    }

	@Override
	public Object getValor() {
		
		return null;
	}

}
