package gcom.cadastro;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descri��o da Classe>>
 * 
 * @author paulo almeida 3.05.2023
 */
public class FiltroDmc extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public FiltroDmc() {
    }

    public FiltroDmc(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";
    
    public final static String INDICADORUSO = "indicadorUso";

    public final static String DESCRICAO = "descricao";

    public final static String SETORCOMERCIAL_ID = "setorComercial.id";

    public final static String LOCALIDADE_ID = "localidade.id";
     

}
