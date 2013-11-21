package gcom.cobranca.contratoparcelamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroContratoParcelamentoRD extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Constructor
	 */
	public FiltroContratoParcelamentoRD() {
	}

	/**
	 * Constructor
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroContratoParcelamentoRD(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Código
	 */
	public final static String CONTRATO_PARCELAMENTO_RD = "contratoParcelamentoRD";

	public final static String CONTRATO_PARCELAMENTO_RD_ID = "id";

	public final static String NUMERO = "numero";
	
	public final static String ASSUNTO = "assunto";

	public final static String DATA_VIGENCIA_INICIO = "dataVigenciaInicio";

	public final static String DATA_VIGENCIA_FIM = "dataVigenciaFinal";


}
