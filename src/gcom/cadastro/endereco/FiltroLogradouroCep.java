package gcom.cadastro.endereco;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de da tabela de associação entre LOGRADOURO e CEP
 * 
 * @author Raphael Rossiter
 * @created 04/05/2006
 */
public class FiltroLogradouroCep extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
     * Description of the Field
     */
    public final static String ID = "id";
	
	/**
     * Description of the Field
     */
    public final static String ID_LOGRADOURO = "logradouro.id";

    /**
     * Description of the Field
     */
    public final static String ID_CEP = "cep.id";

    
    /**
     * Description of the Field
     */
    public final static String CODIGO_CEP = "cep.codigo";
    
    /**
     * Description of the Field
     */
    public final static String ID_CEP_TIPO_CEP = "cep.cepTipo.id";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";


    /**
     * Constructor for the FiltroLogradouroBairro object
     */
    
    public static final String LOGRADOURO = "logradouro";
    
    public static final String CEP = "cep";
    
    public static final String NOME_LOGRADOURO = "logradouro.nome";
    
    public static final String NOME_POPULAR_LOGRADOURO = "logradouro.nomePopular";

    public static final String ID_MUNICIPIO_LOGRADOURO = "logradouro.municipio.id";

    public static final String NOME_MUNICIPIO_LOGRADOURO = "logradouro.municipio.nome";

    public static final String ID_LOGRADOUROTIPO_LOGRADOURO = "logradouro.logradouroTipo.id";

    public static final String ID_LOGRADOUROTITULO_LOGRADOURO = "logradouro.logradouroTitulo.id";

    public static final String INDICADORUSO_LOGRADOURO = "logradouro.indicadorUso";
    
    public FiltroLogradouroCep() {
    }

    /**
     * Constructor for the FiltroLogradouroBairro object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroLogradouroCep(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}
