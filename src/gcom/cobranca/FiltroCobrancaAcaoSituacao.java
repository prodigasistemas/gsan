package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * 
 * @author Anderson Italo
 * @created 14/08/2009
 */
public class FiltroCobrancaAcaoSituacao extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor for the FiltroCobrancaAcaoSituacao object
     */
    public FiltroCobrancaAcaoSituacao() {
    }

    /**
     * Constructor for the FiltroCobrancaAcaoSituacao object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroCobrancaAcaoSituacao(String campoOrderBy) {
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
