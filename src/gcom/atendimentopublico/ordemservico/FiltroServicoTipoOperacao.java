package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;


/**
 * FiltroUnidadeOrganizacional 
 *
 * @author Sávio Luiz
 * @date 27/07/2006
 */
public class FiltroServicoTipoOperacao extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor for the FiltroUnidadeOrganizacional object
     */
    public FiltroServicoTipoOperacao() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroServicoTipoOperacao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Id da Unidade
     */
    public final static String ID_SERVICO_TIPO = "comp_id.idServicoTipo";
    
    /**
     * Id da Operacao
     */
    public final static String ID_OPERACAO = "comp_id.idOperacao";
    
   
   
}
