package gcom.spcserasa;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * FiltroNegativador
 *
 * @author Thiago Toscano 
 * @date 26/12/2007
 */
public class FiltroNegativadorMovimentoRegItem extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor for the FiltroNegativador object
     */
    public FiltroNegativadorMovimentoRegItem() {
    }

    /**
     * Constructor for the FiltroNegativador object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroNegativadorMovimentoRegItem(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";
    
    public final static String NEGATIVADOR_MOVIMENTO_REG_ID = "negativadorMovimentoReg.id";
    
    public final static String INDICADOR_SITUACAO_DEFINITIVA = "indicadorSituacaoDefinitiva";

    public final static String DOCUMENTO_TIPO = "documentoTipo";
    
    public final static String DOCUMENTO_TIPO_ID = "documentoTipo.id";

    public final static String CONTA_GERAL = "contaGeral";

    public final static String CONTA_GERAL_CONTA = "contaGeral.conta";
    
    public final static String CONTA_GERAL_CONTA_HISTORICO = "contaGeral.contaHistorico";

    public final static String CONTA_GERAL_ID = "contaGeral.id";

    public final static String GUIA_PAGAMENTO_GERAL = "guiaPagamentoGeral";

    public final static String GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO = "guiaPagamentoGeral.guiaPagamento";

    public final static String GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO_HISTORICO = "guiaPagamentoGeral.guiaPagamentoHistorico";

    public final static String GUIA_PAGAMENTO_GERAL_ID = "guiaPagamentoGeral.id";

    public final static String COBRANCA_DEBITO_SITUACAO_ID = "cobrancaDebitoSituacao.id";
    
    public final static String COBRANCA_DEBITO_SITUACAO = "cobrancaDebitoSituacao";
    
    public final static String COBRANCA_DEBITO_SITUACAO_APOS_EXCLUSAO = "cobrancaDebitoSituacaoAposExclusao";

}
