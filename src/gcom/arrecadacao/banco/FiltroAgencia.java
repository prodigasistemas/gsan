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

public class FiltroAgencia extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    public FiltroAgencia() {
    }
    
    public FiltroAgencia(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String BANCO_ID = "banco.id";
    
    public final static String BANCO = "banco";
    
    public final static String NOME_AGENCIA = "nomeAgencia";
    
    public final static String CODIGO_AGENCIA = "codigoAgencia";
    
    public final static String ID = "id"; 
    
}
