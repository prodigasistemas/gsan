package gcom.operacional;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroFonteCaptacao extends Filtro {
	
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroFonteCaptacao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroQuadra
     */
    public FiltroFonteCaptacao() {
    }


    public final static String ID = "id";
    public final static String DESCRICAO = "descricao";
    public final static String INDICADOR_USO = "indicadorUso";
    public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";
    public final static String ID_TIPO_CAPTACAO = "tipoCaptacao";

}
