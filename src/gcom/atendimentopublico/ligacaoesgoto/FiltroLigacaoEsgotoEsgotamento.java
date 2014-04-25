package gcom.atendimentopublico.ligacaoesgoto;


import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroLigacaoEsgotoEsgotamento extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroLigacaoEsgotoEsgotamento(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroLigacaoEsgotoEsgotamento() {
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
	public final static String INDICADOR_USO = "indicadorUso";
	
	public final static String ID_FATURAMENTO_SITUACAO_MOTIVO = "faturamentoSituacaoMotivo.id";
	/**
	 * Description of the Field
	 */
	public final static String QUANTIDADE_MESES_SITUACAO_ESPECIAL = "quantidadeMesesSituacaoEspecial";
	
	public final static String FATURAMENTO_SITUACAO_TIPO_ID = "faturamentoSituacaoTipo.id";
	
	public final static String FATURAMENTO_SITUACAO_MOTIVO_ID = "faturamentoSituacaoMotivo.id";
	
}
