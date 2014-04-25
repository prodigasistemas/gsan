package gcom.util.tabelaauxiliar;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroTabelaAuxiliar extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Construtor da classe FiltroTabelaAuxiliar
     */
    public FiltroTabelaAuxiliar() {
    }

    /**
     * Construtor da classe FiltroTabelaAuxiliar
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroTabelaAuxiliar(String campoOrderBy) {
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

    public final static String INDICADORUSO = "indicadorUso";

}
