package gcom.util.tabelaauxiliar.abreviadatipo;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * <Esse componente serve para SetorAbastecimento e ZonaAbastecimento, sendo o tipo SistemaAbastecimento>
 * 
 * @author Administrador
 */
public class FiltroTabelaAuxiliarAbreviadaTipo extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroTabelaAuxiliarTipo
     */
    public FiltroTabelaAuxiliarAbreviadaTipo() {
    }

    /**
     * Construtor da classe FiltroTabelaAuxiliarTipo
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroTabelaAuxiliarAbreviadaTipo(String campoOrderBy) {
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

    /**
     * Description of the Field
     */
    public final static String SISTEMAABASTECIMENTO = "sistemaAbastecimento.id";
    
}
