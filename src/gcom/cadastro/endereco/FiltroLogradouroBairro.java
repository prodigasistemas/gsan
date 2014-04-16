package gcom.cadastro.endereco;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author not attributable
 * @created 4 de Agosto de 2005
 * @version 1.0
 */

public class FiltroLogradouroBairro extends Filtro implements Serializable {
	
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
    public final static String INDICADORUSO_BAIRRO = "bairro.indicadorUso";

    /**
     * Description of the Field
     */
    public final static String ID_BAIRRO = "bairro.id";
    
    public final static String CD_BAIRRO = "bairro.codigo";

    /**
     * Description of the Field
     */
    public final static String NOME_BAIRRO = "bairro.nome";

    /**
     * Description of the Field
     */
    public final static String NOME_LOGRADOURO = "logradouro.nome";
    
    /**
     * Description of the Field
     */
    public final static String NOME_POPULAR_LOGRADOURO = "logradouro.nomePopular";
    
    /**
     * Description of the Field
     */
    public final static String ID_MUNICIPIO = "bairro.municipio.id";

    /**
     * Description of the Field
     */
    public final static String NOME_MUNICIPIO = "bairro.municipio.nome";

    /**
     * Description of the Field
     */
    public final static String ID_LOGRADOUROTIPO = "logradouro.logradouroTipo.id";

    /**
     * Description of the Field
     */
    public final static String ID_LOGRADOUROTITULO = "logradouro.logradouroTitulo.id";
    
    public static final String LOGRADOURO = "logradouro";
    
    public static final String BAIRRO = "bairro";
    
    
    public static final String LOGRADOURO_TIPO = "logradouro.logradouroTipo";
    
    public static final String LOGRADOURO_TITULO = "logradouro.logradouroTitulo";    
    
    public static final String LOGRADOUROTIPO_DESCRICAO = "logradouro.logradouroTipo.descricao";
    
    public static final String LOGRADOUROTITULO_DESCRICAO = "logradouro.logradouroTitulo.descricao";

    /**
     * Constructor for the FiltroLogradouroBairro object
     */
    public FiltroLogradouroBairro() {
    }

    /**
     * Constructor for the FiltroLogradouroBairro object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroLogradouroBairro(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}
