package gcom.cobranca.contratoparcelamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroPrestacaoItemContratoParcelamento extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Constructor
	 */
	public FiltroPrestacaoItemContratoParcelamento() {
	}

	/**
	 * Constructor
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroPrestacaoItemContratoParcelamento(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Código
	 */
	public final static String ID = "id";

	public final static String PRESTACAO = "prestacao";
	
	public final static String PRESTACAO_NUMERO = "prestacao.numero";
	
	public final static String CONTRATO = "prestacao.contratoParcelamento";
	
	public final static String CONTRATO_PRESTACAO_ID = "prestacao.contratoParcelamento.id";
	
	public final static String VALOR_PRESTACAO = "prestacao.valor";
	
	public final static String PRESTACAO_ID = "prestacao.id";
	
	public final static String ITEM = "item";
	
	public final static String ITEM_ID = "item.id"; 
	
	public final static String DOCUMENTO_TIPO_ID = "item.documentoTipo.id";
	
	public final static String CONTA_ID = "item.contaGeral.id";
	
	public final static String GUIA_PAGAMENTO_ID = "item.guiaPagamentoGeral.id";
	
	public final static String CREDITO_REALIZAR_ID = "item.creditoARealizarGeral.id";
	
	public final static String DEBITO_COBRAR_ID = "item.debitoACobrarGeral.id";
	

}
