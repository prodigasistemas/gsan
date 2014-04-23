package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;



/** @author Hibernate CodeGenerator */
public class FiltroNegativacaoCriterioClienteTipo extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    public static final String COMP_ID_NEGATIVACAO_CRITERIO_ID = "comp_id.negativacaoCriterio.id";

    /** persistent field */
    public static final String ULTIMA_ALTERACAO = "ultimaAlteracao";

    /** nullable persistent field */
    public static final String COMP_ID_CLIENTE_TIPO_ID = "comp_id.clienteTipo.id";


    /** default constructor */
    public FiltroNegativacaoCriterioClienteTipo() {
    }
    
    public FiltroNegativacaoCriterioClienteTipo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}
