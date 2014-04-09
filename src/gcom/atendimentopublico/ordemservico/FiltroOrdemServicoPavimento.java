package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtar da Ordem de Serviço Pavimento
 * @author Arthur Carvalho
 * @since 13/11/2009
 *
 */
public class FiltroOrdemServicoPavimento extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructor for the FiltroOrdemServico object
	 */
	public FiltroOrdemServicoPavimento() {
	}

	/**
	 * 
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroOrdemServicoPavimento(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	/**
	 * Description of the Field
	 */
	public final static String ORDEM_SERVICO_ID = "ordemServico";
	
	public final static String PAVIMENTO_RUA = "pavimentoRua";
	public final static String PAVIMENTO_CALCADA = "pavimentoCalcada";
	
	public final static String PAVIMENTO_RUA_RETORNO = "pavimentoRuaRetorno";
	public final static String PAVIMENTO_CALCADA_RETORNO = "pavimentoCalcadaRetorno";
	
	public final static String UNIDADE_REPAVIMENTADORA = "unidadeRepavimentadora";
	
	public final static String MOTIVO_REJEICAO = "motivoRejeicao";
	
	public final static String USUARIO_ACEITE = "usuarioAceite";
	
	public final static String PAVIMENTO_RUA_ID = "pavimentoRua.id";
	public final static String PAVIMENTO_CALCADA_ID = "pavimentoCalcada.id";
	
}

