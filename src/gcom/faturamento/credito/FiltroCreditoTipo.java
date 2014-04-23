package gcom.faturamento.credito;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroCreditoTipo extends Filtro {
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe FiltroOcupacaoTipo
	 */
	public FiltroCreditoTipo() {
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String DESCRICAO = "descricao";

	/**
	 * Description of the Field
	 */
	public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_USO = "indicadorUso";

	/**
	 * Description of the Field
	 */
	public final static String LANCAMENTO_ITEM_CONTABIL = "lancamentoItemContabil";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_GERACAO_AUTOMATICO = "indicadorGeracaoAutomatica";

	/**
	 * Description of the Field
	 */
	public final static String VALOR_LIMITE_CREDITO = "valorLimite";

	/**
	 * Construtor da classe FiltroDebitoTipo
	 * 
	 * @param campoOrderBy
	 *            Descrição do parâmetro
	 */
	public FiltroCreditoTipo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

}
