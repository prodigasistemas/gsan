package gcom.seguranca.acesso;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Descrição da classe 
 *
 * @author Rômulo Aurélio
 * @date 28/04/2006
 */
public class FiltroFuncionalidade extends Filtro implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricao";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";

    
    /**
     * Description of the Field
     */
    public final static String CAMINHO_MENU = "caminhoMenu";
    
    
    /**
     * Description of the Field
     */
    public final static String CAMINHO_URL = "caminhoUrl";
    
    /**
     * Description of the Field
     */
    
    public final static String INDICADOR_PONTO_ENTRADA= "indicadorPontoEntrada";
       
    
    /**
	 * Description of the Field
	 */
	public final static String MODULO_ID = "modulo.id";
	
	public final static String MODULO = "modulo";
	
	public final static String MODULO_NUMERO_ORDEM_MENU = "modulo.numeroOrdemMenu";
    
    /**
	 * Description of the Field
	 */
	public final static String FUNCIONALIDADE_DEPENDENCIAS = "funcionalidadeDependencias";

	public final static String FUNCIONALIDADE_DEPENDENCIAS_BY_ID_INDEPENDENCIA = "funcionalidadeDependenciasByFncdIddependencia";
	
	public final static String OPERACOES = "operacoes";
	
	public final static String OPERACOES_INDICADOR_REGISTRA_TRANSACAO = "operacoes.indicadorRegistraTransacao";

    public final static String NUMERO_ORDEM_MENU = "numeroOrdemMenu";
    /**
     * 
     * 
     * Construtor de FiltroFuncionalidade 
     *
     */
		
	public FiltroFuncionalidade(){
		
	}
	/**
     * Construtor da classe FiltroFuncionalidade
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroFuncionalidade(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
	
	

}
