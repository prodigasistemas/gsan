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

public class FiltroRoteiroEmpresa extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String CODIGO_ROTEIRO = "codigo";
	
	public static final String ID_ROTEIRO = "id";
	
	public static final String EMPRESA = "empresa.id";	

    public static final String INDICADOR_USO = "indicadorUso";

    public static final String LOCALIDADE_ID = "setorComercial.localidade.id";
    
    public final static String LOCALIDADE_DESCRICAO = "setorComercial.localidade.descricao";

    public static final String SETOR_COMERCIAL_ID = "setorComercial.id";

    public static final String SETOR_COMERCIAL_CODIGO = "setorComercial.codigo";
    
    public static final String LEITURISTA_ID = "leiturista.id";

    public FiltroRoteiroEmpresa() {
    }

    public FiltroRoteiroEmpresa(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}
