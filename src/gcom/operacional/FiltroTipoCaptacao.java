package gcom.operacional;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroTipoCaptacao extends Filtro {
	
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroFonteCaptacao object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroTipoCaptacao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroTipoCaptacao
     */
    public FiltroTipoCaptacao() {
    }


    public final static String ID = "id";
    public final static String DESCRICAO = "descricao";
    public final static String INDICADOR_USO = "indicadorUso";

}
