package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * [UC0459] Informar Dados da Agencia Reguladora
 *
 * @author Kássia Albuquerque
 * @date 10/04/2007
 */

public class FiltroRaDadosAgenciaReguladoraFone extends Filtro implements Serializable {

    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FiltroRaDadosAgenciaReguladoraFone(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    
    public FiltroRaDadosAgenciaReguladoraFone() {
    }

    
    public final static String ID = "id";
    public final static String DDD = "ddd";
    public final static String AGENCIA_REGULADORA_ID = "raDadosAgenciaReguladora.id";
    public final static String FONE = "fone";
    
    
    
}
