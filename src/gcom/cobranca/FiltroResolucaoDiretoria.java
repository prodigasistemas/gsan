package gcom.cobranca;

import gcom.util.filtro.Filtro;

/**
 * [UC0214] - Efetuar Parcelamento de Débitos
 * Filtro para Resolução de Diretoria
 * @author  Roberta Costa
 * @created 17/02/2006 
 */
public class FiltroResolucaoDiretoria extends Filtro {
	private static final long serialVersionUID = 1L;

    /**
     * Description of the Field
     */
    public final static String CODIGO = "id";

    /**
     * Description of the Field
     */
    public final static String NUMERO = "numeroResolucaoDiretoria";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricaoAssunto";

    /**
     * Description of the Field
     */
    public final static String DATA_VIGENCIA_INICIO = "dataVigenciaInicio";

    /**
     * Description of the Field
     */
    public final static String DATA_VIGENCIA_FIM = "dataVigenciaFim";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_PARCELAMENTO_UNICO = "indicadorParcelamentoUnico";    
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_UTILIZACAO_LIVRE = "indicadorUtilizacaoLivre";    
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_DESCONTOS_SANCOES = "indicadorDescontoSancoes";
    
    public final static String INDICADOR_PARCELAS_EM_ATRASO = "indicadorParcelasEmAtraso";
    public final static String ID_RD_PARCELAS_EM_ATRASO = "idParcelasEmAtraso";
    public final static String INDICADOR_PARCELAMENTO_EM_ANDAMENTO = "indicadorParcelamentoEmAndamento";
    public final static String ID_RD_PARCELAMENTO_EM_ANDAMENTO = "idParcelamentoEmAndamento";
    
    public final static String INDICADOR_NEGOCIACAO_SO_A_VISTA = "indicadorNegociacaoSoAVista";
    public final static String INDICADOR_DESCONTO_SO_EM_CONTA_A_VISTA = "indicadorDescontoSoEmContaAVista";
    public final static String INDICADOR_PARCELAMENTO_LOJA_VIRTUAL = "indicadorParcelamentoLojaVirtual";
    
    /**
     * Construtor da classe FiltroCategoria
     */
    public FiltroResolucaoDiretoria() {
    }

    /**
     * Construtor da classe FiltroCategoria
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroResolucaoDiretoria(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
