package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

public class FiltroImovelEnderecoAnterior extends Filtro {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String ENDERECOANTERIOR = "enderecoAnterior";

    /**
     * Construtor da classe FiltroCategoria
     */
    public FiltroImovelEnderecoAnterior() {
    }

    /**
     * Construtor da classe FiltroCategoria
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroImovelEnderecoAnterior(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}
