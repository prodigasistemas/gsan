package gcom.util.tabelaauxiliar.faixa;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * @author Administrador
 *
 */
public class FiltroTabelaAuxiliarFaixaReal extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Construtor da classe FiltroTabelaAuxiliar
     */
    public FiltroTabelaAuxiliarFaixaReal() {
    }

    /**
     * Construtor da classe FiltroTabelaAuxiliar
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroTabelaAuxiliarFaixaReal(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String VOLUME_MENOR_FAIXA = "volumeMenorFaixa";

    /**
     * Description of the Field
     */
    public final static String VOLUME_MAIOR_FAIXA = "volumeMaiorFaixa";
    
    /**
     * Description of the Field
     */
   // public final static String FAIXACOMPLETA= "faixaCompleta";
    

}
