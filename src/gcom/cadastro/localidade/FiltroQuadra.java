package gcom.cadastro.localidade;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroQuadra extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroQuadra(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroQuadra
     */
    public FiltroQuadra() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String NUMERO_QUADRA = "numeroQuadra";

    /**
     * Description of the Field
     */
    public final static String ID_SETORCOMERCIAL = "setorComercial.id";

    /**
     * Description of the Field
     */
    public final static String CODIGO_SETORCOMERCIAL = "setorComercial.codigo";

    /**
     * Description of the Field
     */
    public final static String INDICADORUSO = "indicadorUso";
    
    public final static String INDICADOR_ROTA_ALTERNATIVA = "indicadorRotaAlternativa";

    /**
     * Description of the Field
     */
    public final static String ID_LOCALIDADE = "setorComercial.localidade.id";
    
    public final static String ROTA_ID = "rota.id";
    
    public final static String DESCRICAO_LOCALIDADE = "setorComercial.localidade.descricao";
    
    public final static String BAIRRO = "bairro";

}
