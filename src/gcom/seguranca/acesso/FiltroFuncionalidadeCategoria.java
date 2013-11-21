package gcom.seguranca.acesso;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroFuncionalidadeCategoria extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String DESCRICAO_MENU_ITEM = "nome";
	
	public static final String ID = "id";
	
	public static final String MENU_ITEM_SUPERIOR = "funcionalidadeCategoriaSuperior";
	
	public static final String NUMERO_ORDEM_MENU = "numeroOrdemMenu";
	
	public static final String MODULO_ID = "modulo.id";
	
	public static final String INDICADOR_USO = "indicadorUso";

	
    /**
     * Construtor de FiltroFuncionalidadeCategoria 
     */
		
	public FiltroFuncionalidadeCategoria(){
		
	}
	/**
     * Construtor da classe FiltroFuncionalidadeCategoria
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroFuncionalidadeCategoria(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}
