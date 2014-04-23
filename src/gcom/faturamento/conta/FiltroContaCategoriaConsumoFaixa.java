package gcom.faturamento.conta;

import gcom.util.filtro.Filtro;

/**
 * filtro de categorias consumo faixa de conta
 * 
 * @author  Raphael Rossiter
 * @created 13/06/2007
 */
public class FiltroContaCategoriaConsumoFaixa extends Filtro {
	
	private static final long serialVersionUID = 1L;
	
	public FiltroContaCategoriaConsumoFaixa(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
	
	public FiltroContaCategoriaConsumoFaixa() {
    }
	
	public final static String CONTA_ID = "contaCategoria.comp_id.conta.id";
	
	public final static String CATEGORIA_ID = "contaCategoria.comp_id.categoria.id";
	
	public final static String SUBCATEGORIA_ID = "contaCategoria.comp_id.subcategoria.id";

}
