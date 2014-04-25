package gcom.cadastro.funcionario;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca do funcionario
 * 
 * @author Vivianne Sousa
 */
public class FiltroFuncionario extends Filtro implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
    /**
     * Constructor 
     */
    public FiltroFuncionario() {
    }

    /**
     * Constructor 
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroFuncionario (String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Código do funcionário
     */
    public final static String ID = "id";
    
    /**
     * Nome do funcionário
     */
    public final static String NOME = "nome";
    
    public final static String FUNCIONARIO_CARGO_ID = "funcionarioCargo.id";
    
    /**
     * Dados do funcionário
     */
    public final static String NUMERO_CPF = "numeroCpf";
    
    public final static String DATA_NASCIMENTO = "dataNascimento";
    
    /**
     * Código da empresa 
     */
    public final static String UNIDADE_EMPRESA_ID = "empresa.id";
    
    public final static String UNIDADE_EMPRESA = "empresa";
    
    /**
     * Descrição da empresa
     */
    public final static String UNIDADE_EMPRESA_DESCRICAO = "empresa.descricao";
    
    public final static String UNIDADE_ORGANIZACIONAL_ID = "unidadeOrganizacional.id";
    
    public final static String UNIDADE_ORGANIZACIONAL = "unidadeOrganizacional";
}
