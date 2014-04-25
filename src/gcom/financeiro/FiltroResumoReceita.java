package gcom.financeiro;

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

public class FiltroResumoReceita extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    public FiltroResumoReceita() {
    }
    
    public FiltroResumoReceita(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String BANCO_ID = "banco.id";
    
    public final static String BANCO = "banco";
    
    public final static String ID = "id";
    
    public final static String ANO_MES_REFERENCIA = "anoMesReferencia";
    
    public final static String LOCALIDADE = "localidade";
    
    public final static String GERENCIA_REGIONAL = "gerenciaRegional.id";
    
    public final static String CATEGORIA_ID = "categoria.id";
    
}
