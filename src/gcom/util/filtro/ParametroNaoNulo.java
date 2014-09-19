package gcom.util.filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Cesar
 */
public class ParametroNaoNulo extends FiltroParametro {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe ParametroNulo
     * 
     * @param nomeAtributo
     *            Descrição do parâmetro
     */
    public ParametroNaoNulo(String nomeAtributo) {
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
    public ParametroNaoNulo(String nomeAtributo, String conector) {
        super(nomeAtributo, conector);
    }

    /**
     * Construtor da classe ParametroNaoNulo
     * 
     * @param nomeAtributo
     *            Descrição do parâmetro
     * @param conector
     *            Descrição do parâmetro
     * @param numeroParametrosIsoladosConector
     *            Descrição do parâmetro
     */
    public ParametroNaoNulo(String nomeAtributo, String conector,
            int numeroParametrosIsoladosConector) {
        super(nomeAtributo, conector, numeroParametrosIsoladosConector);
    }
    
    /**
     * Construtor da classe ParametroNaoNulo
     * 
     * @param nomeAtributo
     *            Descrição do parâmetro
     * @param numeroParametrosIsoladosConector
     *            Descrição do parâmetro
     */
    public ParametroNaoNulo(String nomeAtributo, int numeroParametrosIsoladosConector) {
        super(nomeAtributo, numeroParametrosIsoladosConector);
    }

	@Override
	public Object getValor() {
		
		return null;
	}

}
