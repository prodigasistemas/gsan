package gcom.faturamento;

import gcom.util.filtro.Filtro;


/**
 * 
 * Filtro de Movimento Conta Pre Faturada 
 *
 * @author bruno
 * @date 07/07/2009
 */
public class FiltroMovimentoContaPrefaturada extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroMovimentoContaPrefaturada(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroAreaConstruidaFaxia
     */
    public FiltroMovimentoContaPrefaturada() {
    }

    /**
     * Description of the Field
     */
    public final static String MATRICULA = "imovel.id";
    public final static String ANO_MES_REFERENCIA_PRE_FATURAMENTO = "anoMesReferenciaPreFaturamento";
    public final static String INDICADOR_ATUALIZAR_FATURAMENTO = "indicadorAtualizacaoFaturamento";
    public final static String CODIGO_ROTA = "rota.codigo";
    public final static String ID_ROTA = "rota.id";
    public final static String ID_SETOR_COMERCIAL = "setorComercial.id";
    public final static String ID_LOCALIDADE = "localidade.id";
    public final static String CODIGO_SETOR_COMERCIAL = "setorComercial.codigo";
    public final static String ID_CONTA = "conta.id";
    
    public final static String ID_MEDICAO_TIPO = "medicaoTipo.id";
    public final static String CONTA = "conta";
    public final static String ID_CONTA_DEBITO_CREDITO_SITUACAO_ATUAL = "conta.debitoCreditoSituacaoAtual.id";
}
