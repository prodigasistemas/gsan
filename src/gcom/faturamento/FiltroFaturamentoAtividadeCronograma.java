package gcom.faturamento;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroFaturamentoAtividadeCronograma extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroFaturamentoAtividadeCronograma(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroAreaConstruidaFaxia
     */
    public FiltroFaturamentoAtividadeCronograma() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_FATURAMENTO_GRUPO_ID = "faturamentoGrupoCronogramaMensal.faturamentoGrupo.id";

    /**
     * Description of the Field
     */

    public final static String FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_ANO_MES_REFERENCIA = "faturamentoGrupoCronogramaMensal.anoMesReferencia";

    /**
     * Description of the Field
     */

    public final static String FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_ID = "faturamentoGrupoCronogramaMensal.id";

    /**
     * Description of the Field
     */
    public final static String DATA_PREVISTA = "dataPrevista";

    /**
     * Description of the Field
     */
    public final static String FATURAMENTO_ATIVIDADE_ID = "faturamentoAtividade.id";
    
    /**
     * Description of the Field
     */
    public final static String FATURAMENTO_ATIVIDADE_ORDEM_REALIZACAO = "faturamentoAtividade.ordemRealizacao";

    /**
     * Description of the Field
     */
    public final static String DATA_REALIZADA = "dataRealizacao";

    /**
     * Description of the Field
     */
    public final static String COMANDO = "comando";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";

}
