package gcom.relatorio;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtro do Relatorio Gerado
 * 
 * @author Rodrigo Silveira *
 * @date 26/10/2006
 */
public class FiltroRelatorioGerado extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	public FiltroRelatorioGerado(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public FiltroRelatorioGerado() {
	}

	public final static String ID = "id";

	public final static String FUNCIONALIDADE_INICIADA_ID = "funcionalidadeIniciada.id";

}
