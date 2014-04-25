package gcom.faturamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroFaturamentoGrupoCronogramaMensal extends Filtro implements
        Serializable {
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroFaturamentoGrupoCronogramaMensal(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroAreaConstruidaFaxia
     */
    public FiltroFaturamentoGrupoCronogramaMensal() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String ID_FATURAMENTO_GRUPO = "faturamentoGrupo.id";

    /**
     * Description of the Field
     */
    public final static String REFERENCIA = "anoMesReferencia";

    /**
     * Description of the Field
     */
    public final static String ANO_MES_REFERENCIA_GRUPO = "faturamentoGrupo.dataPrevista";

    /**
     * Description of the Field
     */
    public final static String ANO_MES_REFERENCIA = "anoMesReferencia";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";
    
    /**
     * Description of the Field
     */
    public final static String FATURAMENTO_ATIVIDADE_CRONOGRAMAS = "faturamentoAtividadeCronogramas";

    /**
     * Description of the Field
     */
    public final static String FATURAMENTO_GRUPO = "faturamentoGrupo";

    /**
     * Description of the Field
     */
    public final static String FATURAMENTO_GRUPO_ANO_MES_REFERENCIA = "faturamentoGrupo.anoMesReferencia";
}
