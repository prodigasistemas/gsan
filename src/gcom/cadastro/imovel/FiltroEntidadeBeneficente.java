package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

import java.io.Serializable;
/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 * @created 19 de Maio de 2005
 */
public class FiltroEntidadeBeneficente extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroEntidadeBeneficente(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroEntidadeBeneficente() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";

    /**
     * Description of the Field
     */
    public final static String ID_CLIENTE = "cliente.id";
    
    public final static String CLIENTE = "cliente";

    /**
     * Description of the Field
     */
    public final static String ID_DEBITO_TIPO = "debitoTipo.id";
    
    /**
     * Description of the Field
     */
    public final static String ID_EMPRESA = "empresa.id";
    
    
    /**
     * Description of the Field
     */
    public final static String CONTRATO_INICIAL = "anoMesContratoInicial";
    
    /**
     * Description of the Field
     */
    public final static String CONTRATO_FINAL = "anoMesContratoFinal";

}
