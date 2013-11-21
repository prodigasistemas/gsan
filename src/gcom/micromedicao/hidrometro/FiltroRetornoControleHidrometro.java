package gcom.micromedicao.hidrometro;

import gcom.util.filtro.Filtro;

/**
 * O filtro serve para armazenar os critérios de busca de um retorno Controle de Hidrometro 
 * 
 * @author Wallace Thierre
 */
public class FiltroRetornoControleHidrometro extends Filtro {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da Classe FiltroControleHidrometro
	 * 
	 */	
	public FiltroRetornoControleHidrometro(){
		
	}
	
	/**
	 * Construntor do Filtro controle Hidrometro
	 * 
	 *   @param campoOrderBy
     *            Descrição do parâmetro
	 * 
	 */
	
	public FiltroRetornoControleHidrometro(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}
	
	 /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricao";
        
    /**
     * Description of the Field
     */    
    public final static String INDICADOR_GERACAO = "indicadorGeracao"; 

		 
}
