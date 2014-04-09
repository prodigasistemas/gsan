package gcom.micromedicao.hidrometro;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 5 de Setembro de 2005
 */
public class FiltroHidrometro extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroHidrometro object
	 */
	public FiltroHidrometro() {
	}

	/**
	 * Construtor da classe FiltroHidrometroCapacidade
	 * 
	 * @param campoOrderBy
	 *            Descrição do parâmetro
	 */
	public FiltroHidrometro(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String NUMERO_HIDROMETRO = "numero";

	/**
	 * Description of the Field
	 */
	public final static String DATA_AQUISICAO = "dataAquisicao";

	/**
	 * Description of the Field
	 */
	public final static String ANO_FABRICACAO = "anoFabricacao";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_MACROMEDIDOR = "indicadorMacromedidor";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_MOVIMENTACAO_ID = "hidrometroMovimentacao.id";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_CLASSE_METROLOGICA_ID = "hidrometroClasseMetrologica.id";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_MARCA_ID = "hidrometroMarca.id";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_DIAMETRO_ID = "hidrometroDiametro.id";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_CAPACIDADE_ID = "hidrometroCapacidade.id";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_TIPO_ID = "hidrometroTipo.id";
	
	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_RELOJOARIA_ID = "hidrometroRelojoaria.id";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_LOCAL_ARMAZENAGEM_ID = "hidrometroLocalArmazenagem.id";

	public final static String HIDROMETRO_SITUACAO_ID = "hidrometroSituacao.id";

	public final static String HIDROMETRO_HIDROMETRO_MOVIMENTADO_HIDROMETRO_MOVIMENTACAO_DATA = "hidrometroMovimentado.hidrometroMovimentacao.data";

	public final static String HIDROMETRO_HIDROMETRO_MOVIMENTADO_HIDROMETRO_MOVIMENTACAO_HORA = "hidrometroMovimentado.hidrometroMovimentacao.hora";

	public final static String HIDROMETRO_HIDROMETRO_MOVIMENTADO_HIDROMETRO_MOVIMENTACAO_HIDROMETRO_LOCAL_ARMAZENAGEM_DESTINO_DESCRICAO = "hidrometroMovimentado.hidrometroMovimentacao.hidrometroLocalArmazenagemDestino.descricao";

	public final static String HIDROMETRO_HIDROMETRO_MOVIMENTADO_HIDROMETRO_MOVIMENTACAO_HIDROMETRO_LOCAL_ARMAZENAGEM_ORIGEM_DESCRICAO = "hidrometroMovimentado.hidrometroMovimentacao.hidrometroLocalArmazenagemOrigem.descricao";
	
	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_TIPO = "hidrometroTipo";
	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_SITUACAO = "hidrometroSituacao";
	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_MARCA = "hidrometroMarca";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_DIAMETRO = "hidrometroDiametro";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_CAPACIDADE = "hidrometroCapacidade";
	
	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_MOTIVO_BAIXA = "hidrometroMotivoBaixa";
	
	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_LOCAL_ARMAZENAGEM = "hidrometroLocalArmazenagem";
	
	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_CLASSE_METROLOGICA = "hidrometroClasseMetrologica";
	
	/**
	 * Description of the Field
	 */
	public final static String VAZAO_TRANSICAO = "vazaoTransicao";
	
	/**
	 * Description of the Field
	 */
	public final static String VAZAO_NOMINAL = "vazaoNominal";
	
	/**
	 * Description of the Field
	 */
	public final static String VAZAO_MINIMA = "vazaoMinima";
	
	/**
	 * Description of the Field
	 */
	public final static String NOTA_FISCAL = "notaFiscal";
	
	/**
	 * Description of the Field
	 */
	public final static String TEMPO_GARANTIA_ANOS = "tempoGarantiaAnos";

}
