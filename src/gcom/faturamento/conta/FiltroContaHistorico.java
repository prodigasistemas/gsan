package gcom.faturamento.conta;

import gcom.util.filtro.Filtro;

/**
 * Descrição da classe 
 *
 * @author Pedro Alexandre
 * @date 29/03/2006
 */
public class FiltroContaHistorico extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroContaHistorico
     */
    public FiltroContaHistorico() {
    }
    
    
    /**
     * @since 29/03/2006
     */
    public final static String ID = "id";

    
    /**
     * @since 29/03/2006
     */
    public final static String IMOVEL_ID = "imovel.id";

    /**
     * @since 29/03/2006
     */
    public final static String ANO_MES_REFERENCIA = "anoMesReferenciaConta";

    public final static String IMOVEL = "imovel";
    
    public final static String IMOVEL_PERFIL = "imovelPerfil";

    public final static String DATA_RETIFICACAO = "dataRetificacao";
    
    public final static String DEBITO_CREDITO_SITUACAO_ATUAL = "debitoCreditoSituacaoAtual";
    
    public final static String DEBITO_CREDITO_SITUACAO_ATUAL_ID = "debitoCreditoSituacaoAtual.id";
    
    /**
     * Construtor da classe FiltroContaHistorico
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroContaHistorico(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}

