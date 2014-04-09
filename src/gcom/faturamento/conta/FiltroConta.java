package gcom.faturamento.conta;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroConta extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroOcupacaoTipo
     */
    public FiltroConta() {
    }
    
    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String IMOVEL_ID = "imovel.id";

    /**
     * Description of the Field
     */
    public final static String REFERENCIA = "referencia";
    
    /**
     * Description of the Field
     */
    public final static String DEBITO_CREDITO_SITUACAO_ATUAL_ID = "debitoCreditoSituacaoAtual.id";
    
    /**
     * Description of the Field
     */
    public final static String ANO_MES_REFERENCIA_CONTABIL = "referenciaContabil";

    public final static String DATA_VENCIMENTO = "dataVencimentoConta";
    
    public final static String DATA_EMISSAO = "dataEmissao";
    
    public final static String DATA_REVISAO = "dataRevisao";
    
    public final static String CONTA_MOTIVO_REVISAO_ID = "contaMotivoRevisao.id";
    
    public final static String CONTA_MOTIVO_CANCELAMENTO = "contaMotivoCancelamento";
    
    public final static String CONTA_MOTIVO_INCLUSAO = "contaMotivoInclusao";
    
    public final static String ID_CONTA_MOTIVO_INCLUSAO = "contaMotivoInclusao.id";
    
    public final static String DOCUMENTO_TIPO = "documentoTipo";
    
    public final static String CONTA_BANCARIA = "contaBancaria";
    
    public final static String FATURAMENTO_TIPO = "faturamentoTipo";
    
    public final static String NOME_CONTA = "nomeConta";
    
    public final static String REGISTRO_ATENDIMENTO = "registroAtendimento";
    
    public final static String LIGACAO_AGUA_SITUACAO = "ligacaoAguaSituacao";
    
    public final static String LIGACAO_ESGOTO_SITUACAO = "ligacaoEsgotoSituacao";
    
    public final static String IMOVEL = "imovel";
    
    public final static String CONSUMO_TARIFA = "consumoTarifa";
    
    public final static String IMOVEL_PERFIL = "imovelPerfil";
    
    public final static String LOCALIDADE = "localidade";
    
    public final static String MOTIVO_NAO_ENTREGA_DOCUMENTO = "motivoNaoEntregaDocumento";
    
    public final static String CONTA_MOTIVO_REVISAO = "contaMotivoRevisao";
    
    public final static String CONTA_MOTIVO_RETIFICACAO = "contaMotivoRetificacao";
    
    public final static String DEBITO_CREDITO_SITUACAO_ATUAL = "debitoCreditoSituacaoAtual";
    
    public final static String DEBITO_CREDITO_SITUACAO_ANTERIOR = "debitoCreditoSituacaoAnterior";
    
    public final static String CONTA_GERAL = "contaGeral";
    
    public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";
    
    public final static String PARCELAMENTO_ID = "parcelamento.id";
    
    public final static String PARCELAMENTO = "parcelamento";
    
    public final static String CONTA_CATEGORIA = "contaCategorias";
       
    public final static String CONTA_CATEGORIA_CATEGORIA = "contaCategorias.comp_id.categoria";
    
    public final static String CONTA_CATEGORIA_SUBCATEGORIA = "contaCategorias.comp_id.subcategoria";
    
    public final static String DEBITOS_COBRADOS = "debitoCobrados";

    public final static String CREDITOS_REALIZADOS = "creditoRealizados";
    
    public final static String USUARIO = "usuario";
    
    public final static String DATA_ENVIO_EMAIL_CONTA = "dataEnvioEmailConta";
    /**
     * Construtor da classe FiltroCategoria
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroConta(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}

