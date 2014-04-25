package gcom.faturamento;

import gcom.util.filtro.Filtro;


/**
 * 
 * Filtro de Faturamento Imediato Ajuste 
 *
 * @author bruno
 * @date 07/07/2009
 */
public class FiltroFaturamentoImediatoAjuste extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroFaturamentoImediatoAjuste(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroAreaConstruidaFaxia
     */
    public FiltroFaturamentoImediatoAjuste() {
    }

    /**
     * Description of the Field
     */
    public final static String ID_CONTA = "conta.id";
}
