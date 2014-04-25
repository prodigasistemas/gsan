package gcom.faturamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroQualidadeAgua extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */

    /**
     * Construtor da classe FiltroQuadra
     */
    public FiltroQualidadeAgua() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String LOCALIDADE_ID = "localidade.id";
    
    public final static String SETOR_COMERCIAL_ID = "setorComercial.id";
    
    public final static String SETOR_COMERCIAL_CODIGO = "setorComercial.codigo";
    
    public final static String ANO_MES_REFERENCIA = "anoMesReferencia";
    
    public final static String ID_FONTE_CAPTACAO = "fonteCaptacao";
    
    public final static String SISTEMA_ABASTECIMENTO = "sistemaAbastecimento.id";
    

}
