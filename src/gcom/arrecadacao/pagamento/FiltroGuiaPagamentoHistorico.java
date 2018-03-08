package gcom.arrecadacao.pagamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de uma guia de pagamento no histórico
 * @author Raphael Rossiter
 * @created 15 de Abril de 2008
 */
public class FiltroGuiaPagamentoHistorico extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FiltroGuiaPagamentoHistorico() {
	}

	public FiltroGuiaPagamentoHistorico(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String ID = "id";
	public final static String IMOVEL_ID = "imovel.id";
	public final static String CLIENTE_ID = "cliente.id";
	public final static String DEBITO_TIPO_ID = "debitoTipo.id";
	public final static String DEBITO_CREDITO_SITUACAO_ATUAL_ID = "debitoCreditoSituacaoByDcstIdatual.id";
	public final static String EMISSAO_GUIA_PAGAMENTO = "dataEmissao";
	public final static String GUIA_PAGAMENTO_GERAL = "guiaPagamentoGeral";
	
	public final static String LOCALIDADE = "localidade";
	
	public final static String PARCELAMENTO_ID = "parcelamento.id";
}
