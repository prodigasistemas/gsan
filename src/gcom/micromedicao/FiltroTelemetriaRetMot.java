package gcom.micromedicao;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

/**
 * @author Rossiter
 * @date 27/09/2010
 */
public class FiltroTelemetriaRetMot extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public final static String INDICADOR_MOV_ACEITO = "indicadorMovAceito";
	public final static String ID = "id";
	
	public FiltroTelemetriaRetMot() {

	}

	public FiltroTelemetriaRetMot(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
}
