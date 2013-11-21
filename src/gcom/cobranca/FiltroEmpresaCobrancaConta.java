package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroEmpresaCobrancaConta extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroEmpresaCobrancaConta(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroEmpresaCobrancaConta() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String EMPRESA = "empresa";
    
    /**
     * Description of the Field
     */
    public final static String EMPRESA_ID = "empresa.id";


    /**
     * Description of the Field
     */
    public final static String CONTA = "contaGeral";
    
    public final static String CONTA_NAO_HISTORICO = "contaGeral.conta";
    
    public final static String CONTA_NAO_HISTORICO_DC_SITUACAO_ATUAL = "contaGeral.conta.debitoCreditoSituacaoAtual";
    
    public final static String CONTA_HISTORICO_DC_SITUACAO_ATUAL = "contaGeral.contaHistorico.debitoCreditoSituacaoAtual";
    
    public final static String CONTA_HISTORICO = "contaGeral.contaHistorico";
    
    public final static String ORDEM_SERVICO = "ordemServico";
    
    public final static String ORDEM_SERVICO_IMOVEL = "ordemServico.imovel";
    
    public final static String ORDEM_SERVICO_SERVICO_TIPO = "ordemServico.servicoTipo";
    
    public final static String ORDEM_SERVICO_SERVICO_TIPO_ID = "ordemServico.servicoTipo.id";
    
    public final static String ORDEM_SERVICO_MOTIVO_ENCERRAMENTO= "ordemServico.atendimentoMotivoEncerramento";
    
    public final static String ORDEM_SERVICO_IMOVEL_ID = "ordemServico.imovel.id";
    
    /**
     * Description of the Field
     */
    public final static String CONTA_ID = "contaGeral.id";

    /**
     * Description of the Field
     */
    public final static String IMOVEL = "imovel";
    
    /**
     * Description of the Field
     */
    public final static String IMOVEL_ID = "imovel.id";
    
    /**
     * Description of the Field
     */
    public final static String COMANDO_EMPRESA_COBRANCA_CONTA_ID = "comandoEmpresaCobrancaConta.id";

}