package gcom.arrecadacao;

import gcom.util.filtro.Filtro;

/**
 * O filtro é responsável por armazenar os parâmetros da pesquisa de movimentos de cartão rejeitados 
 *
 * @author Raphael Rossiter
 * @date 07/06/2010
 */
public class FiltroMovimentoCartaoRejeita extends Filtro {
	
	private static final long serialVersionUID = 1L;
	
	public final static String ARRECADADOR_MOVIMENTO_ITEM_ID = "arrecadadorMovimentoItem.id";
	
	/**
     * Construtor de FiltroPagamento 
     * 
     */
    public FiltroMovimentoCartaoRejeita() {
    }

    /**
     * Construtor da classe FiltroPagamento
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroMovimentoCartaoRejeita(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
