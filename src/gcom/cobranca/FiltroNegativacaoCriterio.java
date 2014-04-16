package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;


/** @author Hibernate CodeGenerator */
public class FiltroNegativacaoCriterio  extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
	public final static String ID = "id";

    /** persistent field */
	public final static String DESCRICAO_TITULO = "descricaoTitulo";

    /** persistent field */
	public final static String DESCRICAO_SOLICITACAO = "descricaoSolicitacao";

    /** nullable persistent field */
	public final static String CODIGO_SETOR_COMERCIAL_INICIAL = "codigoSetorComercialInicial";

    /** nullable persistent field */
	public final static String CODIGO_SETOR_COMERCIAL_FINAL = "codigoSetorComercialFinal";

    /** nullable persistent field */
	public final static String ANO_MES_REFERENCIA_CONTA_INICIAL = "anoMesReferenciaContaInicial";

    /** nullable persistent field */
	public final static String ANO_MES_REFERENCIA_CONTA_FINAL = "anoMesReferenciaContaFinal";

    /** nullable persistent field */
	public final static String DATA_VENCIMENTO_DEBITO_INICIAL = "dataVencimentoDebitoInicial";

    /** nullable persistent field */
	public final static String DATA_VENCIMENTO_DEBITO_FINAL = "dataVencimentoDebitoFinal";

    /** nullable persistent field */
	public final static String QUANTIDADE_MAXIMA_INCLUSOES = "quantidadeMaximaInclusoes";

    /** persistent field */
	public final static String INDICADOR_NEGATIVACAO_IMOVEL_PARALISACAO = "indicadorNegativacaoImovelParalisacao";

    /** persistent field */
	public final static String INDICADOR_NEGATIVACAO_IMOVEL_SITUACAO_COBRANCA = "indicadorNegativacaoImovelSituacaoCobranca";

    /** persistent field */
	public final static String INDICADOR_NEGATIVACAO_CONTA_REVISAO = "indicadorNegativacaoContaRevisao";

    /** persistent field */
	public final static String INDICADOR_NEGATIVACAO_GUIA_PAGAMENTO = "indicadorNegativacaoGuiaPagamento";

    /** persistent field */
	public final static String INDICADOR_NEGATIVACAO_INQUILINO_DEBITO_CONTA_MES = "indicadorNegativacaoInquilinoDebitoContaMes";

    /** persistent field */
	public final static String INDICADOR_NEGATIVACAO_RECEBIMENTO_CARTA_PARCELAMENTO = "indicadorNegativacaoRecebimentoCartaParcelamento";

    /** nullable persistent field */
	public final static String NUMERO_DIAS_ATRASO_RECEBIMENTO_CARTA_PARCELAMENTO = "numeroDiasAtrasoRecebimentoCartaParcelamento";

    /** persistent field */
	public final static String INDICADOR_USO = "indicadorUso";

    /** persistent field */
	public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";

    /** persistent field */
	public final static String VALOR_MINIMO_DECIMAR = "valorMinimoDebito";

    /** persistent field */
	public final static String QUANTIDADE_MINIMA_CONTAS = "quantidadeMinimaContas";

    /** persistent field */
	public final static String VALOR_MAXIMO_DEBITO = "valorMaximoDebito";

    /** persistent field */
	public final static String QUANTIDADE_MAXIMA_CONTAS = "quantidadeMaximaContas";

    /** persistent field */
	public final static String INDICADOR_PARCELAMENTO_ATRASO = "indicadorParcelamentoAtraso";

    /** nullable persistent field */
	public final static String NUMERO_DIAS_PARCELAMENTO_ATRASO = "numeroDiasParcelamentoAtraso";

    /** persistent field */
	public final static String ID_LOCALIDADE_FINAL = "localidadeFinal.id";

    /** persistent field */
	public final static String ID_LOCALIDADE_INICIAL = "localidadeInicial.id";

    /** persistent field */
	public final static String ID_NEGATIVACAO_COMANDO = "negativacaoComando.id";

    /** persistent field */
	public final static String ID_CLIENTE = "cliente.id";

    /** persistent field */
	public final static String ID_CLIENTE_RELACAO_TIPO = "clienteRelacaoTipo.id";

    
    /** default constructor */
    public FiltroNegativacaoCriterio() {
    }

    /**
     * Constructor for the FiltroCobrancaAtividade object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroNegativacaoCriterio(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
