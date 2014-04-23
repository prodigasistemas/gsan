package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;



/** @author Hibernate CodeGenerator */
public class FiltroNegativacaoCriterioCpfTipo extends Filtro implements Serializable  {
	
	public FiltroNegativacaoCriterioCpfTipo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    public static final String ID = "id";

    /** persistent field */
    public static final String NUMERO_ORDEM_SELECAO = "numeroOrdemSelecao";

    /** persistent field */
    public static final String INDICADOR_COINCIDENTE = "indicadorCoincidente";

    /** persistent field */
    public static final String ULTIMA_ALTERACAO = "ultimaAlteracao";

    /** persistent field */
    public static final String ID_NEGATIVACAO_CRITERIO = "negativacaoCriterio.id";

    /** persistent field */
    public static final String CPF_TIPO = "cpfTipo.id";

    /** default constructor */
    public FiltroNegativacaoCriterioCpfTipo() {
    }


}
