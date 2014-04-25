package gcom.cadastro.sistemaparametro;

import gcom.util.filtro.Filtro;

/**
 * Tabela Nacional Feriado
 * 
 * @author Rafael Santos
 * @since 20/02/2006
 */
public class FiltroNacionalFeriado extends Filtro {
	
	private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe FiltroNacionalFeriado
     */
    public FiltroNacionalFeriado() {
    }

    /**
     * Construtor da classe FiltroNacionalFeriado
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroNacionalFeriado(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }


    /**
     * Código do Feriado
     */
    public final static String ID = "id";

    /**
     * Data do Feriado
     */
    public final static String DATA = "data";

    /**
     * Descrição do Feriado
     */
    public final static String NOME = "descricao";

    
}
