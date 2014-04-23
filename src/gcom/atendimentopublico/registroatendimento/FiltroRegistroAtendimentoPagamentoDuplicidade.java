package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtro para pesquisar o Registro de Atendimento Pagamento Duplicidade.
 * @author Hugo Leonardo
 * @since 14/03/2011
 * 
 */
public class FiltroRegistroAtendimentoPagamentoDuplicidade  extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the FiltroRegistroAtendimentoPagamentoDuplicidade object
	 */
	public FiltroRegistroAtendimentoPagamentoDuplicidade() {
	}

	/**
	 * Constructor for the FiltroRegistroAtendimentoPagamentoDuplicidade object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroRegistroAtendimentoPagamentoDuplicidade(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String REGISTRO_ATENDIMENTO = "registroAtendimento";
	
    public final static String CONTA = "contaGeral";
    
    public final static String PAGAMENTO = "pagamento";
    
    public final static String INDICADOR_PAGAMENTO_DEVOLVIDO = "indicadorPagamentoDevolvido";
    
    public final static String IMOVEL = "imovel";
    
    public final static String PAGAMENTO_SITUACAO_ATUAL = "pagamento.pagamentoSituacaoAtual";
    
	public final static String REGISTRO_ATENDIMENTO_ID = "registroAtendimento.id";
	
    public final static String CONTA_ID = "contaGeral.id";
    
    public final static String PAGAMENTO_ID = "pagamento.id";
    
    public final static String REFERENCIA_CONTA = "referenciaConta";
	
}
