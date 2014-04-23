package gcom.arrecadacao;

import gcom.util.filtro.Filtro;

/**
 * < <Classe Filtro de Contrato Tarifa>>
 * 
 * @author Arthur Carvalho
 */
public class FiltroArrecadadorContratoTarifa extends Filtro {
	
	private static final long serialVersionUID = 1L;

    /**
     * Description of the Field
     */
    public final static String CODIGO = "id";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricao";
    
    /**
     * Description of the Field
     */
    public final static String ARRECADACAO_FORMA = "arrecadacaoForma";
    
    /**
     * Description of the Field
     */
    public final static String ARRECADADOR_CONTRATO_ID = "comp_id.arrecadadorContratoId";
    /**
     * Description of the Field
     */
    public FiltroArrecadadorContratoTarifa() {
    }

    public FiltroArrecadadorContratoTarifa(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
