package gcom.cobranca.contratoparcelamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroContratoParcelamentoItem extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Constructor
	 */
	public FiltroContratoParcelamentoItem() {
	}

	/**
	 * Constructor
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroContratoParcelamentoItem(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Código
	 */
	public final static String ID = "id";

	public final static String CONTRATO = "contrato";

	public final static String CONTRATO_ID = "contrato.id";
	
	public final static String INDICADOR_ITEM_CANCELADO = "indicadorItemCancelado";
	
	public final static String DOCUMENTO_TIPO = "documentoTipo";
	
	public final static String DOCUMENTO_TIPO_ID = "documentoTipo.id";
	
	public final static String CONTA_GERAL = "contaGeral";
	
	public final static String CONTA_GERAL_CONTA = "contaGeral.conta";
	
	public final static String CONTA_GERAL_CONTA_DEBITO_CREDITO_SITUACAO_ATUAL  = "contaGeral.conta.debitoCreditoSituacaoAtual";
	
	public final static String DEBITO_A_COBRAR_GERAL = "debitoACobrarGeral";
	
	public final static String DEBITO_A_COBRAR_GERAL_DEBITO = "debitoACobrarGeral.debitoACobrar";
	
	public final static String DEBITO_A_COBRAR_GERAL_DEBITO_IMOVEL = "debitoACobrarGeral.debitoACobrar.imovel";
	
	public final static String DEBITO_A_COBRAR_GERAL_DEBITO_TIPO = "debitoACobrarGeral.debitoACobrar.debitoTipo";
	
	public final static String GUIA_PAGAMENTO_GERAL = "guiaPagamentoGeral";
	
	public final static String GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO = "guiaPagamentoGeral.guiaPagamento";
	
	public final static String CREDITO_A_REALIZAR_GERAL = "creditoARealizarGeral";

	public final static String GUIA_PAGAMENTO_GERAL_ID = "guiaPagamentoGeral.id";

	public final static String GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO_DEBITO_TIPO_ID = "guiaPagamentoGeral.guiaPagamento.debitoTipo.id";
	
	public final static String GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO_DEBITO_TIPO_COD_CONST = "guiaPagamentoGeral.guiaPagamento.debitoTipo.codigoConstante";
	
	public final static String GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO_CLIENTE = "guiaPagamentoGeral.guiaPagamento.cliente";
	
	public final static String GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO_IMOVEL = "guiaPagamentoGeral.guiaPagamento.imovel";
	
	public final static String GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO_IMOVEL_LOCALIDADE = "guiaPagamentoGeral.guiaPagamento.imovel.localidade";
	

}
