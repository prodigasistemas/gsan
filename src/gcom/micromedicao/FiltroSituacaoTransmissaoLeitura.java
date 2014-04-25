package gcom.micromedicao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Descrição da classe
 * 
 * @author COMPESA
 */
public class FiltroSituacaoTransmissaoLeitura extends Filtro implements
		Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe FiltroHidrometroLocalArmazenagem
	 * 
	 * @param campoOrderBy
	 *            Descrição do parâmetro
	 */
	public FiltroSituacaoTransmissaoLeitura(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Construtor da classe FiltroHidrometroLocalArmazenagem
	 */
	public FiltroSituacaoTransmissaoLeitura() {
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_USO = "indicadorUso";

	/**
	 * Description of the Field
	 */
	public final static String DESCRICAO = "descricaoSituacao";


}
