package gcom.gerencial.faturamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroResumoFaturamentoSituacaoEspecial extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroResumoFaturamentoSituacaoEspecial(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroResumoFaturamentoSituacaoEspecial() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String FATURAMENTO_SITUCACAO_TIPO_ID = "faturamentoSituacaoTipo.id";

    /**
     * Description of the Field
     */
    public final static String FATURAMENTO_SITUCACAO_MOTIVO_ID = "faturamentoSituacaoMotivo.id";
    
    public final static String GERENCIA_REGIONAL_ID = "gerenciaRegional.id";
 
}
