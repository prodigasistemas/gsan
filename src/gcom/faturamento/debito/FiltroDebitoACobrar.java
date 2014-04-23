package gcom.faturamento.debito;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

/**
 * O filtro serve para armazenar os critérios de busca de um debito a cobrar
 * @author Rafael Santos
 * @since 30/12/2005
 *
 */
public class FiltroDebitoACobrar extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the FiltroDebitoACobrar object
	 */
	public FiltroDebitoACobrar() {
	}

	/**
	 * Constructor for the FiltroDebitoACobrar object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroDebitoACobrar(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	/**
	 * Description of the Field
	 */
	public final static String IMOVEL_ID = "imovel.id";
	
	public final static String IMOVEL = "imovel";
	
	
	/**
	 * Description of the Field
	 */
	public final static String FINANCIAMENTO_TIPO = "financiamentoTipo";
	
	public final static String FINANCIAMENTO_TIPO_ID = "financiamentoTipo.id";
	
	public final static String FINANCIAMENTO_TIPO_ICINCLUSAO = "financiamentoTipo.indicadorInclusao";
	
	/**
	 * Description of the Field
	 */
	public final static String DEBITO_TIPO = "debitoTipo";
	
	/**
	 * 
	 */
	public final static String DEBITO_CREDITO_SITUACAO =  "debitoCreditoSituacaoAtual";

	
	public final static String REFERENCIA_DEBITO = "anoMesReferenciaDebito";
	
	public final static String GERACAO_DEBITO = "geracaoDebito";
	
	public final static String DEBITO_TIPO_ID = "debitoTipo.id";
	
	public final static String DEBITO_CREDITO_SITUACAO_ATUAL_ID =  "debitoCreditoSituacaoAtual.id";
	
	public final static String PARCELAMENTO_ID = "parcelamento.id";
	
	public final static String ID_REGISTRO_ATENDIMENTO = "registroAtendimento.id";
	
	public final static String NUMERO_PRESTACOES_COBRADAS = "numeroPrestacaoCobradas";
	
	public final static String NUMERO_PRESTACOES_DEBITO = "numeroPrestacaoDebito";
	
	public final static String ID_CREDITO_TIPO = "creditoTipo.id";
	
	public final static String LANCAMENTO_ITEM_CONTABIL = "lancamentoItemContabil";
	
	public final static String ORDEM_SERVICO = "ordemServico";
	
	public final static String REGISTRO_ATENDIMENTO = "registroAtendimento";
	
	public final static String PARCELAMENTO = "parcelamento";
	
	public final static String COBRANCA_FORMA = "cobrancaForma";

}
