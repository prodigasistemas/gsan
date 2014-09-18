package gcom.util.filtro;

/**
 * Esta classe representa uma express�o do filtro. A express�o � o 'or' l�gico.
 * 
 * @author rodrigo
 */
public class ConectorOr extends FiltroParametro {
	private static final long serialVersionUID = 1L;
    private FiltroParametro filtro1;

    private FiltroParametro filtro2;

    /**
     * Construtor da classe ConectorOr
     * 
     * @param filtro1
     *            Filtro que ser� avaliado pela express�o
     * @param filtro2
     *            Filtro que ser� avaliado pela express�o
     */
    public ConectorOr(FiltroParametro filtro1, FiltroParametro filtro2) {
        super(null);
        this.filtro1 = filtro1;
        this.filtro2 = filtro2;
    }

    /**
     * Retorna o valor de filtro1
     * 
     * @return O valor de filtro1
     */
    public FiltroParametro getFiltro1() {
        return filtro1;
    }

    /**
     * Retorna o valor de filtro2
     * 
     * @return O valor de filtro2
     */
    public FiltroParametro getFiltro2() {
        return filtro2;
    }

	@Override
	public Object getValor() {
		
		return null;
	}

}
