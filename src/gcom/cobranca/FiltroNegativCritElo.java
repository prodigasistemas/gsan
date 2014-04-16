package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;


/** @author Hibernate CodeGenerator */
public class FiltroNegativCritElo extends Filtro implements Serializable {
	
	public FiltroNegativCritElo(String campoOrderBy) {
	        this.campoOrderBy = campoOrderBy;
	    }
	
	private static final long serialVersionUID = 1L;

	 /** persistent field */
    public static final String ULTIMA_ATUALIZACAO = "ultimaAlteracao";
    
    /** nullable persistent field */
    public static final String COMP_ID_NEGATIVACAO_CRITERIO_ID = "comp_id.negativacaoCriterio.id";

    /** nullable persistent field */
    public static final String COMP_ID_LOCALIDADE_ID = "comp_id.localidade.id";

    /** default constructor */
    public FiltroNegativCritElo() {
    }

}
