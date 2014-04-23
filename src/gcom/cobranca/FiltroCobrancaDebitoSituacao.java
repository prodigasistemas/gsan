package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * FiltroCobrancaDebitoSituacao
 *
 * @author Yara Taciane
 * @date 03/12/2008
 */
public class FiltroCobrancaDebitoSituacao extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor for the FiltroCobrancaDebitoSituacao object
     */
    public FiltroCobrancaDebitoSituacao() {
    }

    /**
     * Constructor for the FiltroCobrancaDebitoSituacao object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroCobrancaDebitoSituacao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

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
