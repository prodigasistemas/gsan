package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;


/** @author Hibernate CodeGenerator */
public class FiltroNegativacaoCriterioImovelPerfil extends Filtro implements Serializable {
	
	public FiltroNegativacaoCriterioImovelPerfil(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
	
	private static final long serialVersionUID = 1L;

    
    /** identifier field */
    public static final String COMP_ID_NEGATIVACAO_CRITERIO_ID = "comp_id.negativacaoCriterio.id";

    /** identifier field */
    public static final String COMP_ID_IMOVEL_PERFIL_ID = "comp_id.imovelPerfil.id";

    /** persistent field */
    public static final String ULTIMA_ALTERACAO = "ultimaAlteracao";

    /** default constructor */
    public FiltroNegativacaoCriterioImovelPerfil() {
    }


}
