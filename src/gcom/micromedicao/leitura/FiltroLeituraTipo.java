package gcom.micromedicao.leitura;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

/**
 * @author Vivianne Sousa
 * @created 21/03/2006
 */

public class FiltroLeituraTipo extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
    public FiltroLeituraTipo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }	
    
    public FiltroLeituraTipo(){}
    
		
	public final static String ID = "id";
	
	public final static String DESCRICAO = "descricao";
	
	public final static String INDICADOR_USO = "indicadorUso";
	
	
}
