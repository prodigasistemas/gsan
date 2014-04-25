package gcom.cadastro.geografico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de uma municipio
 * 
 * @author Sávio Luiz
 * @created 22 de Abril de 2005
 */

public class FiltroBairro extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroBairro object
     */
    public FiltroBairro() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroBairro(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Id do bairro
     */
    public final static String ID = "id";

    /**
     * Código do bairro
     */
    public final static String CODIGO = "codigo";

    /**
     * Nome do bairro
     */
    public final static String NOME = "nome";

    /**
     * Nome do Municipio
     */
    public final static String MUNICIPIO = "municipio.nome";

    /**
     * Nome do Municipio
     */
    public final static String MUNICIPIO_ID = "municipio.id";

    /**
     * Indicador uso
     */
    public final static String INDICADOR_USO = "indicadorUso";
}
