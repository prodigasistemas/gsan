package gcom.cadastro.cliente;

import gcom.util.filtro.Filtro;

/*
 *
 *  @author     Rafael Corrêa
 *  @created    16 de Janeiro de 2006
 */
/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 * @created 14 de Outubro de 2005
 */
public class FiltroClienteGuiaPagamento extends Filtro {
	
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroClienteFone object
     */
    public FiltroClienteGuiaPagamento() {
    }

    /**
     * Constructor for the FiltroClienteFone object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroClienteGuiaPagamento(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Código da unidade de medição
     */
    public final static String ID = "id";

    /**
     * Código da unidade de medição
     */
    public final static String GUIA_PAGAMENTO_ID = "guiaPagamento.id";

    /**
     * Description of the Field
     */
    public final static String CLIENTE_ID = "cliente.id";

}
