package gcom.cobranca.parcelamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtro do Perfil do Parcelamento
 * @author Roberta Costa
 * @since 06/03/2006
 */
public class FiltroParcelamentoPerfil extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroImovelEconomia object
     */
    public FiltroParcelamentoPerfil() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroParcelamentoPerfil(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    public final static String PERCENTUAL_DESCONTO_ACRESCIMO = "percentualDescontoAcrescimo";

    public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";

    public final static String SUBCATEGORIA_ID = "subcategoria.id";

    public final static String IMOVEL_SITUACAO_TIPO_ID = "imovelSituacaoTipo.id";
    
    public final static String IMOVEL_PERFIL_ID = "imovelPerfil.id";
    
    public final static String RESOLUCAO_DIRETORIA_ID = "resolucaoDiretoria.id";

    public final static String RESOLUCAO_DIRETORIA_NUMERO = "resolucaoDiretoria.numeroResolucaoDiretoria";
    
    public final static String CATEGORIA_ID = "categoria.id";
}
