package gcom.faturamento.debito;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Ivan Sergio
 */
public class FiltroDebitoTipoVigencia extends Filtro {
	private static final long serialVersionUID = 1L;

	public FiltroDebitoTipoVigencia() {
    }

	/**
	 * Constructor for the FiltroServicoCobrancaValor
	 * 
	 * @param campoOrderBy
	 */
	public FiltroDebitoTipoVigencia(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String VALOR = "valorDebito";

	/**
	 * Description of the Field
	 */
	public final static String ULTIMAALTERACAO = "ultimaAlteracao";

	/**
	 * Description of the Field
	 */
	public final static String DEBITO_TIPO_ID = "debitoTipo.id";
	
	/**
	 * Description of the Field
	 */
	public final static String DEBITO_TIPO = "debitoTipo";

    public final static String DATA_VIGENCIA_INICIAL = "dataVigenciaInicial";
    
    public final static String DATA_VIGENCIA_FINAL = "dataVigenciaFinal";

}
