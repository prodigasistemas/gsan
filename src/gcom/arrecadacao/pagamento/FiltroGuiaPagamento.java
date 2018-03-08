package gcom.arrecadacao.pagamento;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

public class FiltroGuiaPagamento extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public FiltroGuiaPagamento() {
	}

	public FiltroGuiaPagamento(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String ID = "id";
	public final static String ANO_MES_REFERENCIA_CONTABIL = "anoMesReferenciaContabil";
	public final static String IMOVEL_ID = "imovel.id";
	public final static String CLIENTE_ID = "cliente.id";
	public final static String DEBITO_CREDITO_SITUACAO_ATUAL = "debitoCreditoSituacaoAtual";
	public final static String DEBITO_CREDITO_SITUACAO_ATUAL_ID = "debitoCreditoSituacaoAtual.id";
	public final static String DEBITO_TIPO_ID = "debitoTipo.id";
	public final static String DEBITO_TIPO = "debitoTipo";
	public final static String EMISSAO_GUIA_PAGAMENTO = "dataEmissao";
	public final static String DATA_VENCIMENTO = "dataVencimento";
	public final static String PARCELAMENTO_ID = "parcelamento.id";
	public final static String REGISTRO_ATENDIMENTO_ID = "registroAtendimento.id";
	public final static String REGISTRO_ATENDIMENTO = "registroAtendimento";
    public final static String NUMERO_PRESTACAO_DEBITO = "numeroPrestacaoDebito";
    public final static String ORDEM_SERVICO = "ordemServico";
    public final static String FINANCIAMENTO_TIPO_ID = "financiamentoTipo.id";
    public final static String LOCALIDADE = "localidade";
    public final static String CLIENTE = "cliente";
    public final static String GUIA_PAGAMENTO_GERAL = "guiaPagamentoGeral";
    
}
