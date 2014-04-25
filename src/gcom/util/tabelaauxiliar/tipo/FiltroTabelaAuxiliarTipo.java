package gcom.util.tabelaauxiliar.tipo;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroTabelaAuxiliarTipo extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroTabelaAuxiliarTipo
     */
    public FiltroTabelaAuxiliarTipo() {
    }

    /**
     * Construtor da classe FiltroTabelaAuxiliarTipo
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroTabelaAuxiliarTipo(String campoOrderBy) {
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
    public static String TIPO = "tipo";

    public static void setTipo(String tipo) {
        TIPO = tipo;
    }

    public static String getTipo() {
        return TIPO;
    }

}
