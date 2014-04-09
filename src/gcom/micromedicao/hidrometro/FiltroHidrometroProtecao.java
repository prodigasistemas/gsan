package gcom.micromedicao.hidrometro;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Descrição da classe
 * 
 * @author COMPESA
 * @date 29/06/2006
 */
public class FiltroHidrometroProtecao extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe FiltroHidrometroLocalArmazenagem
	 * 
	 * @param campoOrderBy
	 *            Descrição do parâmetro
	 */
	public FiltroHidrometroProtecao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Construtor da classe FiltroHidrometroLocalArmazenagem
	 */
	public FiltroHidrometroProtecao() {
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
	public final static String DESCRICAOABREVIADA = "descricaoAbreviada";

}
