package gcom.cadastro.tarifasocial;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroTarifaSocialCartaoTipo extends Filtro implements
        Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe FiltroTarifaSocialCartaoTipo
     */
    public FiltroTarifaSocialCartaoTipo() {
    }

    /**
     * Construtor da classe FiltroTabelaAuxiliar
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroTarifaSocialCartaoTipo(String campoOrderBy) {
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
    
    
    public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";
    

    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";

}
