package gcom.cobranca.parcelamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 * @created 11 de Agosto de 2005
 */
public class FiltroParcelamentoMotivoDesfazer extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroImovelEconomia object
     */
    public FiltroParcelamentoMotivoDesfazer() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroParcelamentoMotivoDesfazer(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String PARCELAMENTO = "parcelamento.id";
    
    
    public final static String INDICADOR_USO = "indicadorUso";
    
    public final static String DESCRICAO = "descricaoParcelamentoMotivoDesfazer";
    
    
}
