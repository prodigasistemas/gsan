package gcom.arrecadacao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroClassificarPagamentos extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FiltroClassificarPagamentos() {

	}
	
	public FiltroClassificarPagamentos(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String ID_SITUACAO_PAGAMENTO = "pagamentoSituacaoAtual.id";

	public final static String REFERENCIA_ARRECADACAO = "anoMesReferenciaArrecadacao";

	public final static String ID = "id";
	
	public final static String VALOR_PAGAMENTO = "valorPagamento";
	
	public final static String ID_IMOVEL = "imovel.id";
	
	public final static String REFERENCIA_PAGAMENTO = "pagamento.getAnoMesReferenciaPagamento";
	
	public final static String MOTIVO_CANCELAMENTO = "contaGeral.conta.contaMotivoCancelamento.descricaoMotivoCancelamentoConta";
	
	public final static String ORDER_BY = "dataPagamento, objeto.imovel.id, objeto.valorPagamento";
}
