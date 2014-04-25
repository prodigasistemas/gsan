package gcom.util.tabelaauxiliar.abreviada;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroTabelaAuxiliarAbreviada extends Filtro implements
        Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroTabelaAuxiliar
     */
    public FiltroTabelaAuxiliarAbreviada() {
    }

    /**
     * Construtor da classe FiltroTabelaAuxiliar
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroTabelaAuxiliarAbreviada(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricao";

    /**
     * Description of the Field
     */
    public final static String DESCRICAOABREVIADA = "descricaoAbreviada";

    /**
     * Description of the Field
     */
    public final static String INDICADORUSO = "indicadorUso";

}
