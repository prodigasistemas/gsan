package gcom.cobranca.parcelamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 * @created 11 de Agosto de 2005
 */
public class FiltroParcelamentoDescontoInatividade extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    public final static String QUANTIDADE_MAXIMA_MESES_INATIVIDADE = "quantidadeMaximaMesesInatividade";
    
    public final static String PERCENTUAL_DESCONTO_SEM_RESTABELECIMENTO = "percentualDescontoSemRestabelecimento";
    
    public final static String PERCENTUAL_DESCONTO_COM_RESTABELECIMENTO = "percentualDescontoComRestabelecimento";
    
    public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";

    public final static String PARCELAMENTO_PERFIL = "parcelamentoPerfil.id";

    /**
     * Constructor for the FiltroImovelEconomia object
     */
    public FiltroParcelamentoDescontoInatividade() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroParcelamentoDescontoInatividade(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
