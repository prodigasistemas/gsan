package gcom.cadastro.localidade;

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

public class FiltroLocalidadePorte extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    public FiltroLocalidadePorte() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */

    public FiltroLocalidadePorte(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";
    
    public final static String DESCRICAO = "descricao";

}
