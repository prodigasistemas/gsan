package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;


/** @author Hibernate CodeGenerator */
public class FiltroNegativacaoCriterioSubcategoria extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
	public static final String COMP_ID_NEGATIVACAO_CRITERIO_ID = "comp_id.negativacaoCriterio.id";

	/** identifier field */
	public static final String COMP_ID_SUBCATEGORIA_ID = "comp_id.subcategoria.id";
	
    /** persistent field */
    public static final String ULTIMA_ALTERACAO = "ultimaAlteracao";

    /** nullable persistent field */
    public static final String ID_NEGATIVACAO_CRITERIO = "negativacaoCriterio.id";

    /** nullable persistent field */
    public static final String SUBCATEGORIA = "subcategoria";

    /** default constructor */
    public FiltroNegativacaoCriterioSubcategoria() {
    }
    
    public FiltroNegativacaoCriterioSubcategoria(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}
