package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * 
 * @author Pedro Alexandre 
 * @created 01 de Fevereiro de 2006
 */

public class FiltroCobrancaAcaoAtividadeComando extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor for the FiltroCobrancaAcaoAtividadeComando object
     */
    public FiltroCobrancaAcaoAtividadeComando() {
    }

    /**
     * Constructor for the FiltroCobrancaAcaoAtividadeComando object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroCobrancaAcaoAtividadeComando(String campoOrderBy) {
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
    
    /**
     * Description of the Field
     */
    public final static String ID_COBRANCA_ACAO = "cobrancaAcao.id";
    /**
     * Description of the Field
     */
    public final static String ID_COBRANCA_ATIVIDADE = "cobrancaAtividade.id";
    
    /**
     * Description of the Field
     */
    public final static String ID_COBRANCA_GRUPO = "cobrancaGrupo.id";
    /**
     * Description of the Field
     */
    public final static String ID_GERENCIA_REGIONAL = "gerenciaRegional.id";
    
    /**
     * Description of the Field
     */
    public final static String ID_LOCALIDADE_INICIAL = "localidadeInicial.id";
    
    /**
     * Description of the Field
     */
    public final static String ID_LOCALIDADE_FINAL = "localidadeFinal.id";

    /**
     * Description of the Field
     */
    public final static String CODIGO_SETOR_COMERCIAL_INICIAL = "codigoSetorComercialInicial";

    /**
     * Description of the Field
     */
    public final static String CODIGO_SETOR_COMERCIAL_FINAL = "codigoSetorComercialFinal";
    
    /**
     * Description of the Field
     */
    public final static String NUMERO_QUADRA_INICIAL = "numeroQuadraInicial";

    /**
     * Description of the Field
     */
    public final static String NUMERO_QUADRA_FINAL = "numeroQuadraFinal";
    
    
   
    /**
     * Description of the Field
     */
    public final static String ID_CLIENTE = "cliente.id";    
    
    /**
     * Description of the Field
     */
    public final static String ID_CLIENTE_SUPERIOR = "superior.id"; 
    
    /**
     * Description of the Field
     */
    public final static String ID_CLIENTE_RELACAO_TIPO = "clienteRelacaoTipo.id";
    
    /**
     * Description of the Field
     */
    public final static String ANO_MES_REFERENCIA_CONTA_INICIAL = "anoMesReferenciaContaInicial";
    
    /**
     * Description of the Field
     */
    public final static String ANO_MES_REFERENCIA_CONTA_FINAL = "anoMesReferenciaContaFinal";
    
    
    /**
     * Description of the Field
     */
    public final static String DATA_VENCIMENTO_CONTA_INICIAL = "dataVencimentoContaInicial";

    /**
     * Description of the Field
     */
    public final static String DATA_VENCIMENTO_CONTA_FINAL = "dataVencimentoContaFinal";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_CRITERIO  = "indicadorCriterio";

    /**
     * Description of the Field
     */
    public final static String ID_COBRANCA_CRITERIO = "cobrancaCriterio.id";

    /**
     * Description of the Field
     */
    public final static String ID_USUARIO = "usuario.id";

    /**
     * Description of the Field
     */
    public final static String COBRANCA_ACAO = "cobrancaAcao";

    /**
     * Description of the Field
     */
    public final static String COBRANCA_ATIVIDADE = "cobrancaAtividade";

    /**
     * Description of the Field
     */
    public final static String USUARIO = "usuario";
    
    /**
     * Description of the Field
     */
    public final static String COBRANCA_GRUPO = "cobrancaGrupo";
    
    /**
     * Description of the Field
     */
    public final static String GERENCIAL_REGIONAL = "gerenciaRegional";

    /**
     * Description of the Field
     */
    public final static String LOCALIDADE_INICIAL = "localidadeInicial";

    /**
     * Description of the Field
     */
    public final static String LOCALIDADE_FINAL = "localidadeFinal";    
    
    /**
     * Description of the Field
     */
    public final static String SETOR_COMERCIAL = "codigoSetorComercialInicial";
    
    /**
     * Description of the Field
     */
    public final static String ROTA_INICIAL = "rotaInicial";
    
    /**
     * Description of the Field
     */
    public final static String ROTA_FINAL = "rotaFinal";
    /**
     * Description of the Field
     */
    public final static String CODIGO_ROTA_INICIAL = "rotaInicial.codigo";
    
    /**
     * Description of the Field
     */
    public final static String CODIGO_ROTA_FINAL = "rotaFinal.codigo";
    
    /**
     * Description of the Field
     */
    public final static String CLIENTE = "cliente";
    
    /**
     * Description of the Field
     */
    public final static String CLIENTE_SUPERIOR = "superior";
    
    /**
     * Description of the Field
     */
    public final static String CLIENTE_RELACAO_TIPO = "clienteRelacaoTipo";
    

    /**
     * Description of the Field
     */
    public final static String COBRANCA_CRITERIO = "cobrancaCriterio";


    /**
     * Description of the Field
     */
    public final static String EMPRESA = "empresa";

    /**
     * Description of the Field
     */
    public final static String ID_ROTA_INICIAL = "rotaInicial.id";
    
    /**
     * Description of the Field
     */
    public final static String ID_ROTA_FINAL = "rotaFinal.id";
    
    
    public final static String VALOR_DOCUMENTOS = "valorDocumentos";
    
    public final static String QUANTIDADE_DOCUMENTOS = "quantidadeDocumentos";

    public final static String QUANTIDADE_ITENS_COBRADOS = "quantidadeItensCobrados";
    
    /**
     * Description of the Field
     */
    public final static String ID_UNIDADE_NEGOCIO = "unidadeNegocio.id";
    
    /**
     * Description of the Field
     */
    public final static String NEGOCIO_UNIDADE = "unidadeNegocio";
    
    /**
     * Description of the Field
     */
    public final static String DESCRICAO_TITULO = "descricaoTitulo";
    
    /**
     * Description of the Field
     */
    public final static String DATA_ENCERRAMENTO_REALIZADA = "dataEncerramentoRealizada";
    
    /**
     * Description of the Field
     */
    public final static String VALOR_LIMITE_OBRIGATORIA = "valorLimiteObrigatoria";
    /**
     * Description of the Field
     */
    public final static String CONSUMO_MEDIO_INICIAL = "consumoMedioInicial";
    /**
     * Description of the Field
     */
    public final static String CONSUMO_MEDIO_FINAL = "consumoMedioFinal";
    /**
     * Description of the Field
     */
    public final static String TIPO_CONSUMO = "tipoConsumo";
    /**
     * Description of the Field
     */
    public final static String PERIODO_FINAL_FISCALIZACAO = "periodoFinalFiscalizacao";
    /**
     * Description of the Field
     */
    public final static String PERIODO_INICIAL_FISCALIZACAO = "periodoInicialFiscalizacao";

    
}
