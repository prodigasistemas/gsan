package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * @author Administrador
 *
 */
public class FiltroDocumentosReceberFaixaDiasVencidos extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Construtor da classe FiltroTabelaAuxiliar
     */
    public FiltroDocumentosReceberFaixaDiasVencidos() {
    }

    /**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroDocumentosReceberFaixaDiasVencidos(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
    
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
    
    /**
     * Description of the Field
     */
    public final static String VALOR_MENOR_FAIXA = "valorInicialFaixa";

    /**
     * Description of the Field
     */
    public final static String VALOR_MAIOR_FAIXA = "valorFinalFaixa";
    
    /**
     * Description of the Field
     */
    public final static String DESCRICAO_FAIXA = "descricaoFaixa";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";

    

}
