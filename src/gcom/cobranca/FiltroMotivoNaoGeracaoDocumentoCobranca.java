package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Anderson Italo
 * @date 26/11/2009
 */
public class FiltroMotivoNaoGeracaoDocumentoCobranca extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public FiltroMotivoNaoGeracaoDocumentoCobranca() {}
	
	public FiltroMotivoNaoGeracaoDocumentoCobranca(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
	
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
    public final static String INDICADOR_USO = "indicadorUso";
   

}
