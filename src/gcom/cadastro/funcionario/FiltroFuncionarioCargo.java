package gcom.cadastro.funcionario;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Arthur Carvalho
 * @data 11/08/08
 */
public class FiltroFuncionarioCargo extends Filtro implements
        Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroFuncionarioCargo
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroFuncionarioCargo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroFuncionarioCargo
     */
    public FiltroFuncionarioCargo() {
    }

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
    public final static String INDICADOR_USO = "indicadorUso";
    /**
     * Description of the Field
     */
    public final static String CODIGO = "codigo";
    

}
