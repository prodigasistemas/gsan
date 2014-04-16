package gcom.seguranca.transacao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Classe que representa o filtro do Tipo de Alteracao
 * 
 * @author Vinicius Medeiros
 * @data 14/05/2008
 * 
 */
public class FiltroAlteracaoTipo extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroTabelaAuxiliar
     */
    public FiltroAlteracaoTipo() {
    }

    /**
     * Construtor da classe FiltroTabelaAuxiliar
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroAlteracaoTipo(String campoOrderBy) {
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

    

}
