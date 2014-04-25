package gcom.util.filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Cesar
 */
public class ParametroNulo extends FiltroParametro {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe ParametroNulo
     * 
     * @param nomeAtributo
     *            Descrição do parâmetro
     */
    public ParametroNulo(String nomeAtributo) {
        super(nomeAtributo);
    }

    /**
     * Construtor da classe ParametroNulo
     * 
     * @param nomeAtributo
     *            Nome do campo sendo filtrado
     * @param conector
     *            Conector da query
     */
    public ParametroNulo(String nomeAtributo, String conector) {
        super(nomeAtributo, conector);
    }

    public ParametroNulo(String nomeAtributo, String conector,
            int numeroParametrosIsoladosConector) {
        super(nomeAtributo, conector, numeroParametrosIsoladosConector);
    }

	@Override
	public Object getValor() {
		return null;
	}

}
