package gcom.micromedicao.leitura;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

public class FiltroLeituraAnormalidadeLeitura extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroLeituraAnormalidade object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */

    public FiltroLeituraAnormalidadeLeitura(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }	
    
    public FiltroLeituraAnormalidadeLeitura(){}
	
    public final static String INDICADOR_USO = "indicadorUso";	
		
	// Colocado o ID por Felipe e por "" ,por enquanto, só para o
	// exibirDadosFaturamentoAction
	public final static String ID = "id";
}
