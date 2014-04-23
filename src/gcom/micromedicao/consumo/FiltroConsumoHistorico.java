package gcom.micromedicao.consumo;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroConsumoHistorico extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroConsumoHistorico(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroAreaConstruidaFaxia
     */
    public FiltroConsumoHistorico() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String ANO_MES_FATURAMENTO = "referenciaFaturamento";

    /**
     * Description of the Field
     */
    public final static String IMOVEL_ID = "imovel.id";
    
    public final static String IMOVEL_INDICADOR_EXCLUSAO = "imovel.indicadorExclusao";

    public final static String LIGACAO_TIPO_ID = "ligacaoTipo.id";
    
    public final static String ANORMALIDADE_CONSUMO = "consumoAnormalidade.id";
    
    public final static String CONSUMO_FATURADO_MEDIO= "numeroConsumoFaturadoMes";
    
    public final static String CONSUMO_MEDIDO = "consumoMedio"; 
    
    public final static String LOCALIDADE_IMOVEL = "imovel.localidade.id";
    
    public final static String LIGACAO_AGUA_SITUACAO_IMOVEL = "imovel.ligacaoAguaSituacao.id";
    
    public final static String LIGACAO_ESGOTO_SITUACAO_IMOVEL = "imovel.ligacaoEsgotoSituacao.id";
    
    public final static String SETOR_COMERCIAL_IMOVEL = "imovel.setorComercial.id";
    
    public final static String QUADRA_IMOVEL = "imovel.quadra.id";
    
    public final static String INDICADOR_IMOVEL_CONDOMINIO = "imovel.indicadorImovelCondominio";
    
    public final static String PERFIL_IMOVEL = "imovel.imovelPerfil.id";
    
    public final static String IMOVEL_CONDOMINIO_ID = "imovel.imovelCondominio.id";
    
    public final static String CONSUMO_EMPRESA = "imovel.quadra.rota.id";
    
    public final static String CONSUMO_IMOVEL_CONDOMINIO = "consumoImovelCondominio";
    
    public final static String CONSUMO_TIPO = "consumoTipo";
    
    public final static String SOLICITACAO_TIPO_ESPECIFICACAO = "solicitacaoTipoEspecificacao.id";
    
    public final static String CODIGO_SITUACAO = "codigoSituacao";
    
}
