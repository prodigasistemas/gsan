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
 * 
 * @author not attributable
 * @version 1.0
 */

public class FiltroServicoTipoCelular extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
		
	public static final String ID = "id";
	
	public static final String DESCRICAO = "descricao";	

    public static final String INDICADOR_USO = "indicadorUso";

    public FiltroServicoTipoCelular() {
    }

    public FiltroServicoTipoCelular(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}
