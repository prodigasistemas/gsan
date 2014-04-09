package gcom.arrecadacao;

import gcom.util.filtro.Filtro;


/**
 * O filtro é responsável por armazenar os parâmetros de pesquisa de pagamentos 
 *
 * @author Fernanda Paiva
 * @date 16/05/2006
 */
public class FiltroArrecadacaoDadosDiarios extends Filtro {
	private static final long serialVersionUID = 1L;

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    public final static String ANO_MES_REFERENCIA_ARRECADACAO = "anoMesReferenciaArrecadacao";
    
    public final static String DATA_PAGAMENTO = "dataPagamento";
    
    public final static String GERENCIA_REGIONAL = "gerenciaRegional.id";
    
    public final static String DOCUMENTO_TIPO_ID = "documentoTipo.id";
    
    public final static String LOCALIDADE_ID = "localidade.id";
    
    public final static String LOCALIDADE_ELO = "localidade.localidade";
    
    public final static String ARRECADADOR_ID = "arrecadador.id";
    
    public final static String IMOVEL_PERFIL_ID = "imovelPerfil.id";
    
    public final static String LIGACAO_AGUA_SITUACAO_ID = "ligacaoAguaSituacao.id";
    
    public final static String LIGACAO_ESGOTO_SITUACAO_ID = "ligacaoEsgotoSituacao.id";
    
    public final static String CATEGORIA_ID = "categoria.id";
    
    public final static String CATEGORIA_DESCRICAO = "categoria.descricao";
    
    public final static String ESFERA_PODER_ID = "esferaPoder.id";
    
    public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";
    
    
    
    /**
     * Construtor de FiltroPagamento 
     * 
     */
    public FiltroArrecadacaoDadosDiarios() {
    }

    /**
     * Construtor da classe FiltroPagamento
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroArrecadacaoDadosDiarios(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
