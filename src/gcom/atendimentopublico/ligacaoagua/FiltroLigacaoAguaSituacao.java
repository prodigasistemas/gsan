package gcom.atendimentopublico.ligacaoagua;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroLigacaoAguaSituacao extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroLigacaoAguaSituacao(
			String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Construtor da classe FiltroLigacaoAguaSituacao
	 */
	public FiltroLigacaoAguaSituacao() {
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
	public final static String DESCRICAO_ABREVIADA = "descricaoAbreviado";

	/**
	 * Description of the Field
	 */
	public final static String CONSUMO_MINIMO = "consumoMinimoFaturamento";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_FATURAMENTO = "indicadorFaturamentoSituacao";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_EXISTENCIA_REDE = "indicadorExistenciaRede";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_EXISTENCIA_LIGACAO = "indicadorExistenciaLigacao";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_AGUA_ATIVA = "indicadorAguaAtiva";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_AGUA_DESLIGADA = "indicadorAguaDesligada";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_AGUA_CADASTRADA = "indicadorAguaCadastrada";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_ANALISE_AGUA = "indicadorAnalizeAgua";

	public final static String INDICADOR_CONSUMO_REAL = "indicadorConsumoReal";

	public final static String NUMERO_DIAS_CORTE = "numeroDiasCorte";
}
