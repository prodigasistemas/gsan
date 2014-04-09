package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;


/**
 * FiltroServicoNaoCobranca
 *
 * @author Leonardo Regis
 * @date 16/09/2006
 */
public class FiltroServicoNaoCobrancaMotivo extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor for the FiltroServicoNaoCobranca object
     */
    public FiltroServicoNaoCobrancaMotivo() {
    }

    /**
     * Constructor for the FiltroServicoNaoCobranca object
     * 
     * @param campoOrderBy
     */
    public FiltroServicoNaoCobrancaMotivo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";
    public final static String DESCRICAO = "descricao";
    public final static String ULTIMAALTERACAO = "ultimaAlteracao";
}
