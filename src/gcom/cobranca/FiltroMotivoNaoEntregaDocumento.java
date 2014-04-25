package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroMotivoNaoEntregaDocumento extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroMotivoNaoEntregaDocumento(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroFonteAbastecimento
     */
    public FiltroMotivoNaoEntregaDocumento() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    public final static String INDICADOR_USO = "indicadorUso";

    /**
     * Description of the Field
     */
    public final static String MOTIVO = "motivoNaoeEntregaDocumento";

}
