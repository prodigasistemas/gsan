package gcom.util.tabelaauxiliar.faixa;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * @author Rômulo Aurélio
 *
 */
public class FiltroTabelaAuxiliarFaixaInteiro extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Construtor da classe FiltroTabelaAuxiliar
     */
    public FiltroTabelaAuxiliarFaixaInteiro() {
    }

    /**
     * Construtor da classe FiltroTabelaAuxiliar
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroTabelaAuxiliarFaixaInteiro(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String MENOR_FAIXA = "menorFaixa";

    /**
     * Description of the Field
     */
    public final static String MAIOR_FAIXA = "maiorFaixa";
    
  
    

}
