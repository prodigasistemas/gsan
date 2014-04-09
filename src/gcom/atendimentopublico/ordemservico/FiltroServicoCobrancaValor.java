package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * FiltroServicoCobrancaValor
 * 
 * @author Leonardo Regis
 * @date 29/09/2006
 */
public class FiltroServicoCobrancaValor extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroServicoCobrancaValor
	 */
	public FiltroServicoCobrancaValor() {
	}

	/**
	 * Constructor for the FiltroServicoCobrancaValor
	 * 
	 * @param campoOrderBy
	 */
	public FiltroServicoCobrancaValor(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String VALOR = "valor";

	/**
	 * Description of the Field
	 */
	public final static String ULTIMAALTERACAO = "ultimaAlteracao";

	/**
	 * Description of the Field
	 */
	public final static String SERVICOTIPO = "servicoTipo.id";

	/**
	 * Description of the Field
	 */
	public final static String IMOVELPERFIL = "imovelPerfil.id";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETROCAPACIDADE = "hidrometroCapacidade.id";

	/**
	 * Description of the Field
	 */
	public final static String INDICADORMEDIDO = "indicadorMedido";
	
	public final static String INDICATIVOSERVICOECONOMIA = "indicadorConsideraEconomias";
	/**
	 * Description of the Field
	 */
	public final static String SUBCATEGORIA = "subCategoria.id";
	
	public final static String CATEGORIA = "categoria.id";
	
	public final static String QUANTIDADE_ECONOMIA_INICIAL= "quantidadeEconomiasInicial";
	
	public final static String QUANTIDADE_ECONOMIA_FINAL= "quantidadeEconomiasFinal";
	
	public final static String DATA_INICIO_RELACAO = "dataVigenciaInicial";
	
	public final static String DATA_FIM_RELACAO = "dataVigenciaFinal";
	
	public final static String SUBCATEGORIA_ENTIDADE = "subCategoria";
	
	public final static String CATEGORIA_ENTIDADE = "subCategoria.categoria";
	
	public final static String INDICADOR_GERACAO_DEBITO = "indicadorGeracaoDebito";
}
