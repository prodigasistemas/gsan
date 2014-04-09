package gcom.arrecadacao.pagamento;

import gcom.util.filtro.Filtro;


/**
 * O filtro é responsável por armazenar os parâmetros de pesquisa de pagamentos 
 *
 * @author Pedro Alexandre, Roberta Costa
 * @date 21/03/2006, 10/05/2006
 */
public class FiltroPagamento extends Filtro {
	
	private static final long serialVersionUID = 1L;

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    public final static String IMOVEL = "imovel";
    
    public final static String IMOVEL_ID = "imovel.id";
    
    public final static String CLIENTE = "cliente";
    
    public final static String CLIENTE_ID = "cliente.id";
    
    public final static String CONTA_ID = "contaGeral.conta.id";
    
    public final static String CONTA = "contaGeral.conta";

    public final static String AVISO_BANCARIO_ID = "avisoBancario.id";
    
    public final static String AVISO_BANCARIO = "avisoBancario";
    
    public final static String ANO_MES_REFERENCIA_ARRECADACAO = "anoMesReferenciaArrecadacao";
    
    public final static String ANO_MES_REFERENCIA_PAGAMENTO = "anoMesReferenciaPagamento";
    
    public final static String DATA_PAGAMENTO = "dataPagamento";
    
    public final static String GUIA_PAGAMENTO_DOCUMENTO_TIPO_ID = "guiaPagamento.documentoTipo.id";
    
    public final static String DOCUMENTO_TIPO = "documentoTipo";
    
    public final static String DOCUMENTO_TIPO_ID = "documentoTipo.id";
    
    public final static String PAGAMENTO_ARRECADACAO_FORMA = "arrecadacaoForma.id";
    
    public final static String PAGAMENTO_SITUACAO_ATUAL_ID = "pagamentoSituacaoAtual.id";
    
    public final static String PAGAMENTO_GUIA_PAGAMENTO_CONTA_CLIENTE_CONTAS_CLIENTE_RELACAO_TIPO_ID = "guiaPagamento.conta.clienteContas.clienteRelacaoTipo.id";
    
    public final static String PAGAMENTO_GUIA_PAGAMENTO_CONTA_CLIENTE_CONTAS_CLIENTE_ID = "guiaPagamento.conta.clienteContas.cliente.id";
    
    public final static String PAGAMENTO_IMOVEL_CLIENTE_IMOVEIS_CLIENTE_RELACAO_TIPO_ID = "imovel.clienteImoveis.clienteRelacaoTipo.id";
    
    public final static String PAGAMENTO_IMOVEL_CLIENTE_IMOVEIS_CLIENTE_ID = "imovel.clienteImoveis.cliente.id";
    
    //public final static String DEVOLUCAO_GUIA_DEVOLUCAO_GUIA_PAGAMENTO_CLIENTE_GUIA_PAGAMENTO_CLIENTE_RELACAO_TIPO_ID = "guiaPagamento.guiaPagamento.clientesGuiaPagamento.clienteRelacaoTipo.id";
    
    public final static String ARRECADACAO_FORMA = "arrecadacaoForma";
    
    
   
    
	public final static String PAGAMENTO_GUIA_PAGAMENTO_CLIENTE_GUIA_PAGAMENTO_CLIENTE_RELACAO_TIPO_ID = "guiaPagamento.clientesGuiaPagamento.clienteRelacaoTipo.id";

	/**
	 * Description of the Field
	 */	
	public final static String PAGAMENTO_GUIA_PAGAMENTO_CLIENTE_GUIA_PAGAMENTO_CLIENTE_ID = "guiaPagamento.clientesGuiaPagamento.cliente.id";
	
	/**
	 * Description of the Field
	 */	
	public final static String PAGAMENTO_GUIA_PAGAMENTO_CLIENTE_GUIA_PAGAMENTO_GUIA_PAGAMENTO_ID = "guiaPagamento.clientesGuiaPagamento.guiaPagamento.id";
    
   
    
    public final static String AVISO_BANCARIO_ARRECADADOR = "avisoBancario.arrecadador";
	
	/**
	 * Description of the Field
	 */
	public final static String DEBITO_TIPO = "debitoTipo.id";
	
	public final static String DEBITO_TIPO_ = "debitoTipo";
	
	/**
	 * Description of the Field
	 */
	public final static String DEBITO_A_COBRAR = "debitoACobrarGeral.debitoACobrar.id";
	
	public final static String DEBITO_A_COBRAR_ = "debitoACobrarGeral.debitoACobrar";
	

	/**
	 * Description of the Field
	 */
	public final static String PAGAMENTO_SITUACAO_ATUAL_DESCRICAO = "pagamentoSituacaoAtual.descricaoPagamentoSituacao";
	
	/**
	 * Description of the Field
	 */
	public final static String PAGAMENTO_SITUACAO_ANTERIOR_DESCRICAO = "pagamentoSituacaoAnterior.descricaoPagamentoSituacao";
	
	/**
	 * Description of the Field
	 */
	public final static String GUIA_PAGAMENTO_ID = "guiaPagamento.id";
	
	public final static String GUIA_PAGAMENTO = "guiaPagamento";
	
	/**
	 * Description of the Field
	 */
	public final static String ARRECADADOR_MOVIMENTO_ITEM_ID = "arrecadadorMovimentoItem.id";
	
	public final static String LOCALIDADE = "localidade";
	
	public final static String LOCALIDADE_ID = "localidade.id";
	
	public final static String CONTA_HISTORICO_ID = "contaGeral.contaHistorico.id";
    
    public final static String CONTA_HISTORICO = "contaGeral.contaHistorico";
    
    public final static String PAGAMENTO_SITUACAO_ATUAL = "pagamentoSituacaoAtual";
    
    public final static String PAGAMENTO_SITUACAO_ANTERIOR = "pagamentoSituacaoAnterior";

    
    /**
     * Construtor de FiltroPagamento 
     * 
     */
    public FiltroPagamento() {
    }

    /**
     * Construtor da classe FiltroPagamento
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroPagamento(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
