package gcom.seguranca.acesso;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de uma municipio
 * 
 * @author Thiago Tenório
 * @created 27 de Março de 2005
 */

public class FiltroUnidadeEmpresa extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor for the FiltroBairro object
     */
    public FiltroUnidadeEmpresa() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroUnidadeEmpresa(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Id do Unidade
     */
    public final static String ID = "id";

    /**
     * Descrição da Unidade
     */
    public final static String DESCRICAO = "descricaoUnidade";

    /**
     * Id Unidade Nivel
     */
    public final static String UNIDADE_NIVEL_ID = "unidadeNivel.id";

    /**
     * Sigla da Unidade
     */
    public final static String SIGLA = "siglaUnidade.id";

    /**
     * Nome do Municipio
     */
    public final static String UNIDADE_SUPERIOR_ID = "unidadeEmpresa.id";

}
