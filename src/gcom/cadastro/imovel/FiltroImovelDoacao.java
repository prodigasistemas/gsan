package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;
import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 * @created 19 de Maio de 2005
 */
public class FiltroImovelDoacao extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroImovelDoacao object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroImovelDoacao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroImovelDoacao
     */
    public FiltroImovelDoacao() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String DATA_CANCELAMENTO = "dataCancelamento";

    /**
     * Description of the Field
     */
    public final static String ID_USUARIO_ADESAO = "usuarioAdesao";

    /**
     * Description of the Field
     */
    public final static String ID_USUARIO_CANCELAMENTO = "usuarioCancelamento";
    
    /**
     * Description of the Field
     */
    public final static String REFERENCIA_INICIAL = "referenciaInicial";
    
    /**
     * Description of the Field
     */
    public final static String REFERENCIA_FINAL = "referenciaFinal";
    
    /**
     * Description of the Field
     */
    public final static String ID_IMOVEL = "imovel.id";
    
    /**
     * Description of the Field
     */
    public final static String ID_ENTIDADE_BENEFICENTE = "entidadeBeneficente.id";
    
    /**
     * Description of the Field
     */
    public final static String DATA_ADESAO = "dataAdesao";   
  
   
}
