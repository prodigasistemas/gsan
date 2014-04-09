package gcom.faturamento.bean;

import java.util.Collection;

/**
 * Retorna as coleções de contaCategoria e contaCategoriaConsumoFaixa
 *
 * @author Raphael Rossiter
 * 
 * @date 01/04/2008
 */
public class GerarContaCategoriaHelper {

	private Collection colecaoContaCategoria;
	
	private Collection colecaoContaCategoriaConsumoFaixa;
	
	public GerarContaCategoriaHelper(){}

	public Collection getColecaoContaCategoria() {
		return colecaoContaCategoria;
	}

	public void setColecaoContaCategoria(Collection colecaoContaCategoria) {
		this.colecaoContaCategoria = colecaoContaCategoria;
	}

	public Collection getColecaoContaCategoriaConsumoFaixa() {
		return colecaoContaCategoriaConsumoFaixa;
	}

	public void setColecaoContaCategoriaConsumoFaixa(
			Collection colecaoContaCategoriaConsumoFaixa) {
		this.colecaoContaCategoriaConsumoFaixa = colecaoContaCategoriaConsumoFaixa;
	}
	
}
