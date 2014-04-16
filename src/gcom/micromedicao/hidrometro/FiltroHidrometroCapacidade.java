package gcom.micromedicao.hidrometro;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroHidrometroCapacidade extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe FiltroHidrometroCapacidade
	 */
	public FiltroHidrometroCapacidade() {
	}

	/**
	 * Construtor da classe FiltroHidrometroCapacidade
	 * 
	 * @param campoOrderBy
	 *            Descrição do parâmetro
	 */
	public FiltroHidrometroCapacidade(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
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
	public final static String DESCRICAO = "descricao";

	/**
	 * Description of the Field
	 */
	public final static String NUMERO_ORDEM = "numeroOrdem";

	/**
	 * Description of the Field
	 */
	public final static String CODIGO_HIDROMETRO_CAPACIDADE = "codigoHidrometroCapacidade";

}
