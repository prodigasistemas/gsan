package gcom.atendimentopublico.registroatendimento;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

/**
 * Filtro Agencia Reguladora Motivo Retorno 
 *
 * @author Kassia Regina
 * @date 03/05/2007
 */
public class FiltroAgenciaReguladoraMotRetorno extends Filtro implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FiltroAgenciaReguladoraMotRetorno() {
    }

    public FiltroAgenciaReguladoraMotRetorno(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";

    public final static String DESCRICAO = "descricao";
    
    public final static String INDICADOR_USO = "indicadorUso";

}
