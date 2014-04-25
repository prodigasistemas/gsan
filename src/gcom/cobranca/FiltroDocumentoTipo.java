package gcom.cobranca;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author TiagoMoreno
 */
public class FiltroDocumentoTipo extends Filtro {
	private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe FiltroOcupacaoTipo
     */
    public FiltroDocumentoTipo() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricaoDocumentoTipo";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_PAGAVEL = "indicadorPagavel";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";
    
        public FiltroDocumentoTipo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}

