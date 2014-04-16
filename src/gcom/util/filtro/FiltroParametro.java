package gcom.util.filtro;

import java.io.Serializable;

/**
 * Representa um parâmetro genérico do filtro
 * 
 * @author rodrigo
 */
public abstract class FiltroParametro implements Serializable {

	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Description of the Field
     */
    public final static String CONECTOR_AND = " AND ";

    /**
     * Description of the Field
     */
    public final static String CONECTOR_OR = "  OR ";

    /**
     * Description of the Field
     */
    protected String conector = CONECTOR_AND;

    /**
     * Description of the Field
     */
    protected String nomeAtributo;

    /**
     * Description of the Field
     */
    protected int numeroArgumentosIsoladosPeloConector = 0;

    /**
     * Construtor da classe FiltroParametro
     * 
     * @param nomeAtributo
     *            nome do atributo de que será feita a filtragem
     */
    public FiltroParametro(String nomeAtributo) {
        this.nomeAtributo = nomeAtributo;

    }

    /**
     * Construtor da classe FiltroParametro
     * 
     * @param nomeAtributo
     *            nome do atributo de que será feita a filtragem
     * @param conector
     *            conector lógico para a query condicional
     */
    public FiltroParametro(String nomeAtributo, String conector) {
        this.nomeAtributo = nomeAtributo;
        this.conector = conector;

    }

    /**
     * Construtor da classe FiltroParametro
     * 
     * @param nomeAtributo
     *            nome do atributo de que será feita a filtragem
     * @param conector
     *            conector lógico para a query condicional
     * @param numeroArgumentosIsoladosPeloConector
     *            Número de argumentos que serão isolados pelo conector por
     *            '(arg1 OR arg2 OR arg3)'
     */
    public FiltroParametro(String nomeAtributo, String conector,
            int numeroArgumentosIsoladosPeloConector) {
        this.nomeAtributo = nomeAtributo;
        this.conector = conector;
        this.numeroArgumentosIsoladosPeloConector = numeroArgumentosIsoladosPeloConector;
    }
    
    /**
     * Construtor da classe FiltroParametro
     * 
     * @param nomeAtributo
     *            nome do atributo de que será feita a filtragem
     * @param numeroArgumentosIsoladosPeloConector
     *            Número de argumentos que serão isolados pelo conector por
     *            '(arg1 OR arg2 OR arg3)'
     */
    public FiltroParametro(String nomeAtributo, int numeroArgumentosIsoladosPeloConector) {
        this.nomeAtributo = nomeAtributo;
        this.numeroArgumentosIsoladosPeloConector = numeroArgumentosIsoladosPeloConector;
    }

    /**
     * Retorna o valor de nomeAtributo
     * 
     * @return O valor de nomeAtributo
     */
    public String getNomeAtributo() {
        return nomeAtributo;
    }

    /**
     * Seta o valor de nomeAtributo
     * 
     * @param nomeAtributo
     *            O novo valor de nomeAtributo
     */
    public void setNomeAtributo(String nomeAtributo) {
        this.nomeAtributo = nomeAtributo;
    }

    /**
     * Retorna o valor de conector
     * 
     * @return O valor de conector
     */
    public String getConector() {
        return conector;
    }

    /**
     * Seta o valor de conector
     * 
     * @param conector
     *            O novo valor de conector
     */
    public void setConector(String conector) {
        this.conector = conector;
    }

    /**
     * Retorna o valor de numeroArgumentosIsoladosPeloConector
     * 
     * @return O valor de numeroArgumentosIsoladosPeloConector
     */
    public int getNumeroArgumentosIsoladosPeloConector() {
        return numeroArgumentosIsoladosPeloConector;
    }
    
    public abstract Object getValor();

}
