package gcom.seguranca.acesso;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Descrição da classe 
 *
 * @author Thiago Toscano
 * @date 05/05/2006
 */
public class FiltroGrupoFuncionalidadeOperacao extends Filtro implements Serializable{
	private static final long serialVersionUID = 1L;
	public final static String GRUPO_ID = "grupo.id";
	
	public final static String GRUPO = "grupo";

	public final static String FUNCIONALIDADE_ID = "funcionalidade.id";
	
	public final static String FUNCIONALIDADE = "funcionalidade";
	
	public final static String OPERACAO_ID = "operacao.id";
	
	public final static String OPERACAO = "operacao";
	
	public final static String FUNCIONALIDADE_INDICADOR_PONTO_ENTRADA= "funcionalidade.indicadorPontoEntrada";
    
    public final static String FUNCIONALIDADE_NUMERO_ORDEM_MENU= "funcionalidade.numeroOrdemMenu";

//	/**
//     * Description of the Field
//     */
//    public final static String ID = "id";
//
//    /**
//     * Description of the Field
//     */
//    public final static String DESCRICAO = "descricao";
//
//    /**
//     * Description of the Field
//     */
//    public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";
//
//    
//    /**
//     * Description of the Field
//     */
//    public final static String CAMINHO_MENU = "caminhoMenu";
//    
//    
//    /**
//     * Description of the Field
//     */
//    public final static String CAMINHO_URL = "caminhoUrl";
//    
//    /**
//     * Description of the Field
//     */
//    
//    public final static String INDICADOR_PONTO_ENTRADA= "indicadorPontoEntrada";
//       
//    
//    /**
//	 * Description of the Field
//	 */
//	public final static String MODULO_ID = "modulo.id";
    

        
    
    /**
     * 
     * 
     * Construtor de FiltroFuncionalidade 
     *
     */
		
	public FiltroGrupoFuncionalidadeOperacao(){
		
	}
	/**
     * Construtor da classe FiltroFuncionalidade
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroGrupoFuncionalidadeOperacao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
	
	

}
