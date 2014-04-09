package gcom.arrecadacao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de um Item do Movimento Arrecadador  
 *
 * @author Raphael Rossiter
 * @date 21/03/2006
 */
public class FiltroArrecadadorMovimentoItem extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public FiltroArrecadadorMovimentoItem() {}

	
	public FiltroArrecadadorMovimentoItem(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	
	public final static String ID = "id";
	
}

