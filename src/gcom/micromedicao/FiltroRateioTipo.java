package gcom.micromedicao;

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
 * Filtro Rateio Tipo
 * @author Rafael Santos
 * @since 12/01/2006
 */

public class FiltroRateioTipo extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
    public static final String ID = "id";
    
    public static final String DESCRICAO = "descricao";
    
    public static final String INDICADOR_USO = "indicadorUso";

    public FiltroRateioTipo() {
    }

    public FiltroRateioTipo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}
