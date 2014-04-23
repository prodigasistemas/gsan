package gcom.faturamento;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroFaturamentoSituacaoTipo extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroFaturamentoSituacaoTipo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroAreaConstruidaFaxia
     */
    public FiltroFaturamentoSituacaoTipo() {
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

    /**
     * Description of the Field 
     */
    public final static String INDICADOR_PARALISACAO_FATURAMENTO = "indicadorParalisacaoFaturamento";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_PARALISACAO_LEITURA = "indicadorParalisacaoLeitura";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_VALIDO_AGUA = "indicadorValidoAgua";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_VALIDO_ESGOTO = "indicadorValidoEsgoto";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_INFORMAR_CONSUMO = "indicadorInformarConsumo";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_INFORMAR_VOLUME = "indicadorInformarVolume";
    
    /**
     * Description of the Field
     */
    public final static String LEITURA_ANORMALIDADE_COM_LEITURA = "leituraAnormalidadeLeituraComLeitura";
    
    /**
     * Description of the Field
     */
    public final static String LEITURA_ANORMALIDADE_SEM_LEITURA = "leituraAnormalidadeLeituraSemLeitura";
    
    /**
     * Description of the Field
     */
    public final static String LEITURA_ANORMALIDADE_CONSUMO_COM_LEITURA = "leituraAnormalidadeConsumoComLeitura";
    
    /**
     * Description of the Field
     */
    public final static String LEITURA_ANORMALIDADE_CONSUMO_SEM_LEITURA = "leituraAnormalidadeConsumoSemLeitura";
    
}
