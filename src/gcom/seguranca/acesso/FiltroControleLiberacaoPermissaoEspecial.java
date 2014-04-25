package gcom.seguranca.acesso;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Descrição da classe 
 *
 * @author Daniel Alves
 * @date 23/07/2010
 */
public class FiltroControleLiberacaoPermissaoEspecial extends Filtro implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String FUNCIONALIDADE = "funcionalidade";

    /**
     * Description of the Field
     */
    public final static String PERMISSAO_ESPECIAL = "permissaoEspecial";
    /**
     * Description of the Field
     */
    public final static String FUNCIONALIDADE_ID = "funcionalidade.id";

    /**
     * Description of the Field
     */
    public final static String PERMISSAO_ESPECIAL_ID = "permissaoEspecial.id";
    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";
    
    
    public final static String ULTIMA_ALTERACAO= "ultimaAlteracao";
    	
	/**
     * 
     * 
     * Construtor de FiltroControleLiberacaoPermissaoEspecial 
     *
     */
		
	public FiltroControleLiberacaoPermissaoEspecial(){
		
	}
	/**
     * Construtor da classe FiltroFuncionalidade
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroControleLiberacaoPermissaoEspecial(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
	
	

}
