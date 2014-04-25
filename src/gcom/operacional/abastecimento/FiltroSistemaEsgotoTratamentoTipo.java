package gcom.operacional.abastecimento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * @author Kássia Albquerque 
 * @created 09/03/2007
 */

public class FiltroSistemaEsgotoTratamentoTipo extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor for the FiltroMunicipio object
     */
    public FiltroSistemaEsgotoTratamentoTipo() {
    }

   
    public FiltroSistemaEsgotoTratamentoTipo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Código do Tipo de Tratamento do Sistema de Esgoto
     */
    public final static String ID = "id";

    /**
     * Descrição do Tipo de Tratamento do Sistema de Esgoto
     */
    public final static String NOME = "descricao";

    /**
     * Indicador de Uso
     */
    public final static String INDICADOR_USO = "indicadorUso";

}
