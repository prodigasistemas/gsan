package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * FiltroUnidadeOrganizacional 
 *
 * @author Raphael Rossiter
 * @date 09/08/2006
 */
public class FiltroLocalOcorrencia extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public FiltroLocalOcorrencia(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public FiltroLocalOcorrencia() {
    }

    public final static String ID = "id";
    
    public final static String DESCRICAO = "descricao";
    
    public final static String INDICADOR_USO = "indicadorUso";
}
