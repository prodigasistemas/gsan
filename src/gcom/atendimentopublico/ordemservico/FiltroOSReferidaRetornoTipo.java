package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

/**
 * O filtro serve para armazenar os critérios de busca de um cliente
 * 
 * @author Rodrigo
 */
public class FiltroOSReferidaRetornoTipo extends Filtro {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe FiltroOrgaoExpedidorRg
	 */
	public FiltroOSReferidaRetornoTipo() {
	}

	/**
	 * Construtor da classe FiltroOrgaoExpedidorRg
	 * 
	 * @param campoOrderBy
	 *            Descrição do parâmetro
	 */
	public FiltroOSReferidaRetornoTipo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Código da unidade de medição
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String DESCRICAO = "descricao";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_USO = "indicadorUso";
	
	/**
	 * Description of the Field
	 */
	public final static String ID_SERVICO_TIPO_REFERENCIA = "servicoTipoReferencia.id";
	
	/**
	 * Description of the Field
	 */
	public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";
	
	/**
	 * Description of the Field
	 */
	
	public final static String INDICADOR_DEFERIMENTO = "indicadorDeferimento";
	
	/**
	 * Description of the Field
	 */
	
	public final static String INDICADOR_TROCA_SERVICO = "indicadorTrocaServico";
	
	/**
	 * Description of the Field
	 */
	
	public final static String SITUACAO_OS_REFERENCIA = "situacaoOsReferencia";
	
	/**
	 * Description of the Field
	 */
	
	public final static String ATENDIMENTO_MOTIVO_ENCERRAMENTO_ID = "atendimentoMotivoEncerramento.id";
	
	

}
