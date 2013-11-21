package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

public class FiltroImovelSuprimido extends Filtro {
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroImovelSuprimido(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public FiltroImovelSuprimido() {
    }

    public final static String ID = "id";

    public final static String ORDEM_SERVICO = "ordemServico";
    
    public final static String ORDEM_SERVICO_SITUACAO = "ordemServico.situacao";
      
    public final static String ORDEM_SERVICO_ID = "ordemServico.id";
    
    public final static String SETOR_COMERCIAL_ID = "idSetorComercial";
    
    public final static String DATA_EXECUCAO = "dataExecucao";
    
    public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";
}
