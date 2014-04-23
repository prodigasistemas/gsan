package gcom.faturamento;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroFaturamentoAtivCronRota extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroFaturamentoAtivCronRota(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroAreaConstruidaFaxia
     */
    public FiltroFaturamentoAtivCronRota() {
    }

    /**
     * Description of the Field
     */
    public final static String COMP_ID_ROTA_ID = "rota.id";

    /**
     * Description of the Field
     */
    public final static String COMP_ID_FATURAMENTO_ATIVIDADE_CRONOGRAMA_ID = "faturamentoAtividadeCronograma.id";
    
    /**
     * Description of the Field
     */
    public final static String COMP_ID_FATURAMENTO_ATIVIDADE_CRONOGRAMA_DATA_REALIZACAO = "faturamentoAtividadeCronograma.dataRealizacao";

    /**
     * Description of the Field
     */
    public final static String COMP_ID_FATURAMENTO_ATIVIDADE_CRONOGRAMA_FATURAMENTO_ATIVIDADE_ID = "faturamentoAtividadeCronograma.faturamentoAtividade.id";

    
    /**
     * Description of the Field
     */
    public final static String COMP_ID_FATURAMENTO_ATIVIDADE_CRONOGRAMA_FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_ANO_MES_REFERENCIA = "faturamentoAtividadeCronograma.faturamentoGrupoCronogramaMensal.anoMesReferencia";
    
    /**
     * Description of the Field
     */
    public final static String FATURAMENTO_GRUPO_ID ="faturamentoAtividadeCronograma.faturamentoGrupoCronogramaMensal.faturamentoGrupo.id";
    
    /**
     * Description of the Field
     */
    public final static String GERENCIA_REGIONAL_NOME_ABREVIADO = "rota.setorComercial.localidade.gerenciaRegional.nomeAbreviado";
    
    /**
     * Description of the Field
     */
    public final static String LOCALIDADE_DESCRICAO = "rota.setorComercial.localidade.descricao";

    /**
     * Description of the Field
     */
    public final static String UNIDADE_NEGOCIO = "rota.setorComercial.localidade.unidadeNegocio";
    
    
    /**
     * Description of the Field
     */
    public final static String SETOR_COMERCIAL_CODIGO = "rota.setorComercial.codigo";
}
