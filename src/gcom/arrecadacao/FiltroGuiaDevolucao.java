package gcom.arrecadacao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de um cliente endereco
 * 
 * @author Rafael Corrêa
 * @created 22 de Abril de 2005
 */

public class FiltroGuiaDevolucao extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe FiltroClienteEndereco
	 */
	public FiltroGuiaDevolucao() {
	}

	/**
	 * Constructor for the FiltroDevolucao object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroGuiaDevolucao(String campoOrderBy) {
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
	
	/**
	 * Description of the Field
	 */
	public final static String CLIENTE_ID = "cliente.id";
	
	/**
	 * Description of the Field
	 */
	public final static String LOCALIDADE_ID = "imovel.localidade";

	/**
	 * Description of the Field
	 */
	public final static String SETOR_COMERCIAL_ID = "imovel.setorComercial.id";

	/**
	 * Description of the Field
	 */
	public final static String QUADRA_ID = "imovel.quadra.id";
	
	/**
	 * Description of the Field
	 */
	public final static String LOTE = "imovel.lote";

	/**
	 * Description of the Field
	 */
	public final static String SUBLOTE = "imovel.subLote";
	
	/**
	 * Description of the Field
	 */
	public final static String DEBITO_TIPO_ID = "debitoTipo.id";
	
	/**
	 * Description of the Field
	 */
	public final static String DATA_EMISSAO = "dataEmissao";
	
	/**
	 * Description of the Field
	 */
	public final static String DATA_VALIDADE = "dataValidade";
	
	/**
	 * Description of the Field
	 */
	public final static String CREDITO_TIPO_ID = "creditoTipo.id";
	
	/**
	 * Description of the Field
	 */
	public final static String DOCUMENTO_TIPO_ID = "documentoTipo.id";
	
	/**
	 * Description of the Field
	 */
	public final static String DEBITO_CREDITO_SITUACAO_ATUAL_ID = "debitoCreditoSituacaoAtual.id";
	
	/**
	 * Description of the Field
	 */
	public final static String DEBITO_CREDITO_SITUACAO_ANTERIOR_ID = "debitoCreditoSituacaoAnterior.id";
	
	/**
	 * Description of the Field
	 */
	public final static String ANO_MES_REFERENCIA_ARRECADACAO = "anoMesReferenciaContabil";
	
	/**
	 * Description of the Field
	 */
	public final static String ANO_MES_REFERENCIA_GUIA_DEVOLUCAO = "anoMesReferenciaGuiaDevolucao";
	
	/**
	 * Description of the Field
	 */	
	public final static String GUIA_DEVOLUCAO_CONTA_DEBITO_CREDITO_SITUACAO_ATUAL_ID = "conta.debitoCreditoSituacaoAtual.id";
	
	/**
	 * Description of the Field
	 */	
	public final static String GUIA_DEVOLUCAO_GUIA_PAGAMENTO_CLIENTE_ID = "guiaPagamento.cliente.id";
	
	/**
	 * Description of the Field
	 */	
	public final static String GUIA_DEVOLUCAO_GUIA_PAGAMENTO_DEBITO_CREDITO_SITUACAO_ATUAL_ID = "guiaPagamento.debitoCreditoSituacaoAtual.id";
	
	/**
	 * Description of the Field
	 */	
	public final static String GUIA_DEVOLUCAO_DEBITO_A_COBRAR_DEBITO_CREDITO_SITUACAO_ATUAL_ID = "debitoACobrarGeral.debitoACobrar.debitoCreditoSituacaoAtual.id";
	
	/**
	 * Description of the Field
	 */	
	public final static String GUIA_DEVOLUCAO_CONTA_CLIENTE_CONTAS_CLIENTE_RELACAO_TIPO_ID = "clienteContas.clienteRelacaoTipo.id";
	
	/**
	 * Description of the Field
	 */	
	public final static String GUIA_DEVOLUCAO_CONTA_CLIENTE_CONTAS_CLIENTE_ID = "clienteContas.cliente.id";

	/**
	 * Description of the Field
	 */	
	public final static String GUIA_DEVOLUCAO_DEBITO_A_COBRAR_IMOVEL_CLIENTE_IMOVEIS_CLIENTE_RELACAO_TIPO_ID = "clienteImoveis.clienteRelacaoTipo.id";

	/**
	 * Description of the Field
	 */	
	public final static String GUIA_DEVOLUCAO_DEBITO_A_COBRAR_IMOVEL_CLIENTE_IMOVEIS_CLIENTE_ID = "clienteImoveis.cliente.id";
	
	/**
	 * Description of the Field
	 */	
	public final static String GUIA_DEVOLUCAO_GUIA_PAGAMENTO_CLIENTE_GUIA_PAGAMENTO_CLIENTE_RELACAO_TIPO_ID = "clientesGuiaPagamento.clienteRelacaoTipo.id";

	/**
	 * Description of the Field
	 */	
	public final static String GUIA_DEVOLUCAO_GUIA_PAGAMENTO_CLIENTE_GUIA_PAGAMENTO_CLIENTE_ID = "clientesGuiaPagamento.cliente.id";
	
	public final static String CONTA_ID = "conta.id";
	
	public final static String GUIA_PAGAMENTO_ID = "guiaPagamento.id";
	
	public final static String DEBITO_A_COBRAR_ID = "debitoACobrarGeral.debitoACobrar.id";
	
	
}
