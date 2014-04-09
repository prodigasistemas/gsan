package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * 
 * @author Pedro Alexandre 
 * @created 01 de Fevereiro de 2006
 */

public class FiltroCobrancaAcaoAtividadeCronograma extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
     * Constructor for the FiltroCobrancaAcaoAtividadeCronograma object
     */
    public FiltroCobrancaAcaoAtividadeCronograma() {
    }

    /**
     * Constructor for the FiltroCobrancaAcaoAtividadeCronograma object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroCobrancaAcaoAtividadeCronograma(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String COMANDO = "comando";
    
    /**
     * Description of the Field
     */
    public final static String REALIZACAO = "realizacao";
    
    public final static String COBRANCA_GRUPO_CRONOGRAMA_MES_MES_ANO = "cobrancaAcaoCronograma.cobrancaGrupoCronogramaMes.anoMesReferencia";
    
    public final static String COBRANCA_GRUPO_CRONOGRAMA_MES_COBRANCA_GRUPO = "cobrancaAcaoCronograma.cobrancaGrupoCronogramaMes.cobrancaGrupo";
    
    public final static String COBRANCA_GRUPO_CRONOGRAMA_MES_COBRANCA_GRUPO_ID = "cobrancaAcaoCronograma.cobrancaGrupoCronogramaMes.cobrancaGrupo.id";

    public final static String COBRANCA_GRUPO_CRONOGRAMA_MES_COBRANCA_GRUPO_DESCRICAO_ABREVIADA 
    	= "cobrancaAcaoCronograma.cobrancaGrupoCronogramaMes.cobrancaGrupo.descricaoAbreviada";
    
    /**
     * Description of the Field
     */
    public final static String ID_COBRANCA_ATIVIDADE = "cobrancaAtividade.id";

    /**
     * Description of the Field
     */
    public final static String COBRANCA_ATIVIDADE = "cobrancaAtividade";
    
    /**
     * Description of the Field
     */
    public final static String COBRANCA_ATIVIDADE_COBRANCA_ACAO = "cobrancaAtividade.cobrancaAcao";

    /**
     * Description of the Field
     */ 
    public final static String ID_COBRANCA_ACAO_CRONOGRAMA = "cobrancaAcaoCronograma.id";

    
    /**
     * Description of the Field
     */ 
    public final static String COBRANCA_ACAO_CRONOGRAMA = "cobrancaAcaoCronograma";

    /**
     * Description of the Field
     */ 
    public final static String COBRANCA_ACAO_CRONOGRAMA_COBRANCA_ACAO = "cobrancaAcaoCronograma.cobrancaAcao";
    
    /**
     * Description of the Field
     */ 
    public final static String COBRANCA_ACAO_CRONOGRAMA_COBRANCA_ACAO_ID = "cobrancaAcaoCronograma.cobrancaAcao.id";    
    
    /**
     * Description of the Field
     */
    public final static String COBRANCA_GRUPO_CRONOGRAMA_MES = "cobrancaAcaoCronograma.cobrancaGrupoCronogramaMes";
    
    /**
     * Description of the Field
     */
    public final static String COBRANCA_GRUPO_CRONOGRAMA_MES_ID = "cobrancaAcaoCronograma.cobrancaGrupoCronogramaMes.id";
    
    /**
     * Description of the Field
     */
    public final static String COBRANCA_ACAO = "cobrancaAcaoCronograma.cobrancaAcao";

    /**
     * Description of the Field
     */
    public final static String COBRANCA_GRUPO = "cobrancaAcaoCronograma.cobrancaGrupoCronogramaMes.cobrancaGrupo";
    
    public final static String COBRANCA_ATIVIDADE_ORDEM_REALIZACAO = "cobrancaAtividade.ordemRealizacao";
    
    public final static String COBRANCA_ACAO_CRONOGRAMA_COBRANCA_ACAO_ORDEM_REALIZACAO = "cobrancaAcaoCronograma.cobrancaAcao.ordemRealizacao";
    
    public final static String DATA_PREVISTA = "dataPrevista";
    
    public final static String VALOR_DOCUMENTOS = "valorDocumentos";
    
    public final static String QUANTIDADE_DOCUMENTOS = "quantidadeDocumentos";

    public final static String QUANTIDADE_ITENS_COBRADOS = "quantidadeItensCobrados";    
    
    public final static String COBRANCA_GRUPO_MES_ANO = "cobrancaAcaoCronograma.cobrancaGrupoCronogramaMes.cobrancaGrupo.anoMesReferencia";
    
    public final static String COBRANCA_ATIVIDADE_INDICADOR_CRONOGRAMA = "cobrancaAtividade.indicadorCronograma";
    
}
