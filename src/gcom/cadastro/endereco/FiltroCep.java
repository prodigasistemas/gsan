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

public class FiltroCep extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    public static final String CEPID = "cepId";

    public static final String CODIGO = "codigo";
    
    public static final String LOGRADOURO = "logradouro";
    
    public static final String LOGRADOURO_TIPO = "descricaoTipoLogradouro";
    
    public static final String MUNICIPIO = "municipio";
    
    public static final String BAIRRO = "bairro";

    public static final String INDICADORUSO = "indicadorUso";
    
    public static final String CEP_TIPO_ID = "cepTipo.id";

    public FiltroCep() {
    }

    public FiltroCep(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}
