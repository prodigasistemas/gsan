package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Descrição da classe
 * 
 * @author Paulo Diniz
 * @date 22/07/2011
 */
public class FiltroServicoTipoMotivoEncerramento extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	public final static String SERVICO_TIPO = "servicoTipo";

	public final static String SERVICO_TIPO_ID = "servicoTipo.id";

	public final static String ATENDIMENTO_MOTIVO_ENCERRAMENTO = "motivoEncerramento";

	public final static String ATENDIMENTO_MOTIVO_ENCERRAMENTO_ID = "motivoEncerramento.id";
	
	public final static String ATENDIMENTO_MOTIVO_ENCERRAMENTO_INDICADOR_USO = "motivoEncerramento.indicadorUso";	
	public final static String ATENDIMENTO_MOTIVO_ENCERRAMENTO_INDICADOR_DUPLICIDADE = "motivoEncerramento.indicadorDuplicidade";
	/**
	 * 
	 * 
	 * Construtor de FiltroMaterial<ate
	 * 
	 */

	public FiltroServicoTipoMotivoEncerramento() {

	}

	/**
	 * Construtor da classe FiltroMaterial
	 * 
	 * @param campoOrderBy
	 *            Descrição do parâmetro
	 */
	public FiltroServicoTipoMotivoEncerramento(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

}
