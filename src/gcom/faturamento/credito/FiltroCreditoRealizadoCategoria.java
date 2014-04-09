package gcom.faturamento.credito;

import gcom.util.filtro.Filtro;

/**
 * filtro de credito realizado categoria
 * 
 * @author  Raphael Rossiter
 * @created 13/06/2007
 */
public class FiltroCreditoRealizadoCategoria extends Filtro {
	
	private static final long serialVersionUID = 1L;
	
	public FiltroCreditoRealizadoCategoria() {
    }

    public FiltroCreditoRealizadoCategoria(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String CREDITO_REALIZADO_ID = "creditoRealizado.id";
}
