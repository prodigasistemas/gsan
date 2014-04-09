package gcom.arrecadacao.pagamento;

import gcom.util.filtro.Filtro;


/**
 * O filtro é responsável por armazenar os parâmetros de pesquisa de pagamentos historico
 *
 * @author Vivianne Sousa
 * @date 11/07/2007
 */
public class FiltroPagamentoHistorico extends Filtro {
	
	private static final long serialVersionUID = 1L;

	
	
    public final static String ID = "id";
    
    public final static String CONTA_ID = "contaGeral.contaHistorico.id";
    
    public final static String DEBITO_A_COBRAR_ID = "debitoACobrarGeral.debitoACobrar.id";
    
    public final static String GUIA_PAGAMENTO_ID = "guiaPagamento.id";
    
    public final static String IMOVEL_ID = "imovel.id";
    
    public final static String ANO_MES_REFERENCIA_PAGAMENTO = "anoMesReferenciaPagamento";
  
    
    /**
     * Construtor de FiltroPagamento 
     * 
     */
    public FiltroPagamentoHistorico() {
    }

    /**
     * Construtor da classe FiltroPagamento
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroPagamentoHistorico(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
