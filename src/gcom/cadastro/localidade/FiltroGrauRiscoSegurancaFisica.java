package gcom.cadastro.localidade;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * FiltroGrauRiscoSegurancaFisica
 * 
 * @author Hugo Leonardo
 * @date 27/04/2010
 */
public class FiltroGrauRiscoSegurancaFisica extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroGrauRiscoSegurancaFisica
	 */
	public FiltroGrauRiscoSegurancaFisica() {
	}

	/**
	 * Constructor for the FiltroGrauRiscoSegurancaFisica
	 * 
	 * @param campoOrderBy
	 */
	public FiltroGrauRiscoSegurancaFisica(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
    public final static String INDICADOR_GRAU_RISCO_SEGURANCA_FISICA = "indicativoGrauRiscoSegurancaFisica";
	
	/**
	 * Description of the Field
	 */
	public final static String DESCRICAO = "descricao";

	/**
	 * Description of the Field
	 */
	public final static String ULTIMAALTERACAO = "ultimaAlteracao";

}
