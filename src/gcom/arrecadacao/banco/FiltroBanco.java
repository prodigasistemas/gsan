package gcom.arrecadacao.banco;

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

public class FiltroBanco extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    public FiltroBanco() {
    }
    
    public FiltroBanco(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }


    /**
     * Id do Banco
     */
    public final static String ID = "id";
    
    public final static String NOME_BANCO = "descricao";
    
    public final static String INDICADOR_USO = "indicadorUso";
}
