package gcom.arrecadacao;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroArrecadacaoForma extends Filtro {
	
	private static final long serialVersionUID = 1L;

    /**
     * Description of the Field
     */
    public final static String CODIGO = "id";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricao";
    
    
    public final static String CODIGO_ARRECADACAO_FORMA = "codigoArrecadacaoForma";

    /**
     * Description of the Field
     */
    
    public FiltroArrecadacaoForma() {
    }

    public FiltroArrecadacaoForma(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
