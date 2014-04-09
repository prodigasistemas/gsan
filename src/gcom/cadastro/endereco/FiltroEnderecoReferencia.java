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
 * @version 1.0
 */

public class FiltroEnderecoReferencia extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricao";

    /**
     * Description of the Field
     */
    public final static String DESCRICAOABREVIADA = "descricaoAbreviada";

    /**
     * Description of the Field
     */
    public final static String INDICADORUSO = "indicadorUso";

    /**
     * Construtor da classe FiltroEnderecoReferencia
     */
    public FiltroEnderecoReferencia() {
    }

    /**
     * Construtor da classe FiltroEnderecoReferencia
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroEnderecoReferencia(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}
