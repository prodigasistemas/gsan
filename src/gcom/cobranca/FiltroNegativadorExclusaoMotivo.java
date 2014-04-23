package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;


/** @author Hibernate CodeGenerator */
public class FiltroNegativadorExclusaoMotivo extends Filtro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroCliente object
	 */
	public FiltroNegativadorExclusaoMotivo() {
	}

	/**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroNegativadorExclusaoMotivo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	
	public final static String NEGATIVADOR_ID = "negativador.id";
	
	public final static String DESCRICAO_EXCLUSAO_MOTIVO = "descricaoExclusaoMotivo";
	
	public final static String INDICADOR_USO = "indicadorUso";
	/**
	 * Description of the Field
	 */
	public final static String CODIGO_EXCLUSAO_MOTIVO = "codigoExclusaoMotivo";
	
	/**
	 * Description of the Field
	 */
	public final static String COBRANCA_DEBITO_SITUACAO_ID = "cobrancaDebitoSituacao.id";
	
	public final static String COBRANCA_DEBITO_SITUACAO = "cobrancaDebitoSituacao";
}

