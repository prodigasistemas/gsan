package gcom.cadastro.geografico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de área de bairro
 * 
 * @author Raphael Rossiter
 * @data 02/08/2006
 */
public class FiltroBairroArea extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public FiltroBairroArea() {
    }

    public FiltroBairroArea(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";

    public final static String ID_BAIRRO = "bairro.id";

}
