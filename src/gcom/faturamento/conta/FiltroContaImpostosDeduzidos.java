package gcom.faturamento.conta;

import gcom.util.filtro.Filtro;

/**
 * filtro de impostos deduzidos de conta
 * 
 * @author  pedro alexandre
 * @created 06 de Janeiro de 2006
 */
public class FiltroContaImpostosDeduzidos extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Description of the Field
     */
    public final static String CODIGO = "id";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricao";

    /**
     * Description of the Field
     */
    public final static String CONTA_ID = "conta.id";
    
    /**
     * Description of the Field
     */
    public final static String IMPOSTO_TIPO = "impostoTipo.id";
    

    /**
     * Construtor da classe FiltroImpostosDeduzidos
     */
    public FiltroContaImpostosDeduzidos() {
    }

    /**
     * Construtor da classe FiltroImpostosDeduzidos
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroContaImpostosDeduzidos(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
