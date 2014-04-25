package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * 
 * @author Anderson Italo
 * @created 25 de Novembro de 2009
 */

public class FiltroImovelNaoGerado extends Filtro implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor for the FiltroCobrancaSituacao object
     */
    public FiltroImovelNaoGerado() {
    }
    
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroImovelNaoGerado(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    /**
     * Description of the Field
     */
    public final static String ID_IMOVEL = "imovel.id";
    
    public final static String IMOVEL = "imovel";
    
    public final static String LOCALIDADE = "imovel.localidade";
    
    public final static String LOCALIDADE_ID = "imovel.localidade.id";
    
    public final static String GERENCIA_REGIONAL = "imovel.localidade.gerenciaRegional";
    
    public final static String GERENCIA_REGIONAL_ID = "imovel.localidade.gerenciaRegional.id";
    
    public final static String UNIDADE_NEGOCIO = "imovel.localidade.unidadeNegocio";
    
    public final static String UNIDADE_NEGOCIO_ID = "imovel.localidade.unidadeNegocio.id";
    
    public final static String SETOR_COMERCIAL = "imovel.setorComercial";
    
    public final static String SETOR_COMERCIAL_ID = "imovel.setorComercial.id";
    
    public final static String SETOR_COMERCIAL_CODIGO = "imovel.setorComercial.codigo";
    
    public final static String QUADRA = "imovel.quadra";
    
    public final static String QUADRA_ID = "imovel.quadra.id";
    
    public final static String QUADRA_NUMERO = "imovel.quadra.numeroQuadra";
    
    
    
    /**
     * Description of the Field
     */
    public final static String ID_COBRANCA_ACAO_ATIVIDADE_CRONOGRAMA = "cobrancaAcaoAtividadeCronograma.id";
    
    /**
     * Description of the Field
     */
    public final static String MOTIVO_NAO_GERACAO_DOCUMENTO_COBRANCA = "motivoNaoGeracaoDocCobranca";
    
    /**
     * Description of the Field
     */
    public final static String ID_COBRANCA_ACAO_ATIVIDADE_COMANDO = "cobrancaAcaoAtividadeComando.id";
    
    public final static String ID_MOTIVO_NAO_GERACAO_DOCUMENTO_COBRANCA = "motivoNaoGeracaoDocCobranca.id";
    

}
