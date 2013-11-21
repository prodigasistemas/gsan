package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroServicoTipoSeletiva extends Filtro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroUnidadeOrganizacional object
	 */
	public FiltroServicoTipoSeletiva() {
	}

	/**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroServicoTipoSeletiva(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID_SERVICO_TIPO = "servicoTipo.id";

	public final static String CONSTANTE = "codigoConstante";
	
	public final static String SERVICO_TIPO = "servicoTipo";

}
