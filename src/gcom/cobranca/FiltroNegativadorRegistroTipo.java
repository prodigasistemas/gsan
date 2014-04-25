package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/** @author Hibernate CodeGenerator */
public class FiltroNegativadorRegistroTipo extends Filtro implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroCliente object
	 */
	public FiltroNegativadorRegistroTipo() {
	}

	/**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroNegativadorRegistroTipo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String NEGATIVADOR_ID = "negativador.id";

	public final static String NEGATIVADOR = "negativador";
	
	public final static String ID = "id";

	public final static String CODIGO_REGISTRO = "codigoRegistro";
	
	public final static String DESCRICAO_REGISTRO_TIPO = "descricaoRegistroTipo";

	
}
