package gcom.faturamento.consumotarifa;

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
 * @author Rossiter
 * @version 1.0
 */

public class FiltroConsumoTarifaCategoria extends Filtro implements
        Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroConsumoTarifaCategoria(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroConsumoTarifaCategoria() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String CATEGORIA_ID = "categoria.id";
    
    public final static String CATEGORIA_Descricao = "categoria.descricao";
    
    public final static String SUBCATEGORIA_ID = "subCategoria.id";
    
    public final static String CONSUMO_VIGENCIA_ID = "consumoTarifaVigencia.id";
    
    public final static String CONSUMO_VIGENCIA_CONSUMO_TARIFA_ID = "consumoTarifaVigencia.consumoTarifa.id";
    
    public final static String CONSUMO_VIGENCIA_DATA_VIGENCIA = "consumoTarifaVigencia.dataVigencia";
    
}
