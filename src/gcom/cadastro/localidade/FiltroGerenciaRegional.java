package gcom.cadastro.localidade;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */

public class FiltroGerenciaRegional extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe FiltroGerenciaRegional
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroGerenciaRegional(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroGerenciaRegional
     */
    public FiltroGerenciaRegional() {
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
    public final static String NOME = "nome";

    /**
     * Description of the Field
     */
    public final static String NOME_ABREVIADO = "nomeAbreviado";
    
    public final static String CEP = "cep.codigo";
    
    /**
     * Description of the Field
     */    
    public final static String CLIENTE = "cliente";
    
    
    /**
     * Description of the Field
     */
    public final static String CNPJ_GERENCIA_REGIONAL = "cnpjGerenciaRegional";
    
    
    
}
