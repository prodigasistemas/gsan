package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * 
 * @author Pedro Alexandre
 * @created 08 de Fevereiro de 2006
 */

public class FiltroCobrancaDocumento extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor for the FiltroCobrancaDocumento object
     */
    public FiltroCobrancaDocumento() {
    }
    public FiltroCobrancaDocumento(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
    public final static String ID = "id";
    public final static String IMOVEL_ID = "imovel.id";
    public final static String DATA_EMISSAO = "emissao";
    public final static String DOCUMENTO_EMISSAO_FORMA = "documentoEmissaoForma";
    public final static String ATIVIDADE_CRONOGRAMA_ID = "cobrancaAcaoAtividadeCronograma.id";
    public final static String ATIVIDADE_COMANDO_ID = "cobrancaAcaoAtividadeComando.id";
    public final static String VALOR_DOCUMENTO = "valorDocumento";
    public final static String  MOTIVO_NAO_ENTREGA_ID = "motivoNaoEntregaDocumento.id";
    public final static String PERFIL_IMOVEL_ID = "imovelPerfil.id";
    public final static String GERENCIA_REGIONAL = "localidade.gerenciaRegional.id";
    public final static String DOCUMENTO_TIPO_ID = "documentoTipo.id";
    public final static String LOCALIDADE_ID = "localidade.id";
    public final static String SETOR_COMERCIAL = "codigoSetorComercial";
    public final static String QUADRA_NM = "quadra.numeroQuadra";
    public final static String ID_COBRANCA_CRITERIO = "cobrancaCriterio.id";
    public final static String ID_UNIDADE_NEGOCIO = "localidade.unidadeNegocio.id";
    
    public final static String ID_COBRANCA_ACAO = "cobrancaAcao.id";
    
    public final static String ID_CATEGORIA = "categoria.id";
    
    public final static String ID_EMPRESA = "empresa.id";
    
    public final static String EMPRESA = "empresa";
    
    public final static String ID_COBRANCA_ACAO_SITUACAO = "cobrancaAcaoSituacao.id";
    
    public final static String ID_COBRANCA_DEBITO_SITUACAO = "cobrancaDebitoSituacao.id";

    public final static String PERFIL_IMOVEL = "imovelPerfil";
    
}

