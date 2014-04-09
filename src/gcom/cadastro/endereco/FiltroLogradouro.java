package gcom.cadastro.endereco;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class FiltroLogradouro extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    public static final String ID = "id";

    public static final String NOME = "nome";
    
    public static final String NOME_POPULAR = "nomePopular";

    public static final String ID_MUNICIPIO = "municipio.id";
    
    public static final String NOME_MUNICIPIO = "municipio.nome";

    public static final String DESCRICAOABREVIADA_LOGRADOUROTIPO = "logradouroTipo.descricaoAbreviada";
    
    public static final String ID_LOGRADOUROTIPO = "logradouroTipo.id";    

    public static final String ID_LOGRADOUROTITULO = "logradouroTitulo.id";
    
    public static final String DESCRICAOABREVIADA_LOGRADOUROTITULO = "logradouroTitulo.descricaoAbreviada";

    public static final String INDICADORUSO = "indicadorUso";
    
    public static final String LOGRADOUROTIPO = "logradouroTipo";

    public static final String LOGRADOUROTITULO = "logradouroTitulo";
    
    public static final String ID_OS_PROGRAMA_CALIBRAGEM = "programaCalibragem.id";
    
    public static final String OS_PROGRAMA_CALIBRAGEM = "programaCalibragem";
    
    public FiltroLogradouro() {
    }

    public FiltroLogradouro(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}
