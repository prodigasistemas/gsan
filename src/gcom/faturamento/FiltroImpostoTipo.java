package gcom.faturamento;

import gcom.util.filtro.Filtro;

/**
 * filtro de impostos deduzidos de conta
 * 
 * @author  fernanda paiva
 * @created 22 de Setembro de 2006
 */
public class FiltroImpostoTipo extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricaoImposto";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";

    
    /**
     * Construtor da classe FiltroImpostosDeduzidos
     */
    public FiltroImpostoTipo() {
    }

    /**
     * Construtor da classe FiltroImpostosDeduzidos
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroImpostoTipo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
