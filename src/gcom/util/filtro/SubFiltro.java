package gcom.util.filtro;

/**
 * Representa um filtro que serve como parâmetro simples de busca para outro um
 * filtro
 * 
 * @author rodrigo
 */
public class SubFiltro extends FiltroParametro {
	private static final long serialVersionUID = 1L;
    private Filtro filtro;

    /**
     * Construtor da classe SubFiltro
     * 
     * @param filtro
     *            Filtro para ser usado como parâmetro
     * @param nomeAtributo
     *            Nome do atributo do objeto que terá um subFiltro
     */
    public SubFiltro(Filtro filtro, String nomeAtributo) {
        super(nomeAtributo);
        this.filtro = filtro;
    }

    /**
     * Retorna o valor de filtro
     * 
     * @return O valor de filtro
     */
    public Filtro getFiltro() {
        return filtro;
    }

	@Override
	public Object getValor() {
		return filtro;
	}

}
