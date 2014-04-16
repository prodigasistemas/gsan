package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * FiltroSolicitacaoTipoEspecificacao 
 *
 * @author Raphael Rossiter
 * @date 25/07/2006
 */
public class FiltroSolicitacaoTipoEspecificacao extends Filtro implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor for the FiltroSolicitacaoTipoEspecificacao object
     */
    public FiltroSolicitacaoTipoEspecificacao() {
    }

    /**
     * Constructor for the FiltroSolicitacaoTipoEspecificacao object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroSolicitacaoTipoEspecificacao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";

    public final static String DESCRICAO = "descricao";
    
    public final static String SOLICITACAO_TIPO_ID = "solicitacaoTipo.id";
    
    public final static String SERVICO_TIPO_ID = "servicoTipo.id";
    
    public final static String SOLICITACAO_TIPO_DESCRICAO = "solicitacaoTipo.descricao";
    
    public final static String INDICADOR_USO = "indicadorUso";
    
    public final static String ESPECIFICACAO_IMOVEL_SITUACAO_ID = "especificacaoImovelSituacao.id";
    
    public final static String SOLICITACAO_TIPO = "solicitacaoTipo";
    
    public final static String INDICADOR_SOLICITANTE = "indicadorSolicitante";
    
    public final static String SERVICO_TIPO = "servicoTipo";
    
    public final static String INDICADOR_DOCUMENTO_OBRIGATORIEDADE = "indicadorDocumentoObrigatorio";
    
    public final static String INDICADOR_ENCERRAMENTO_AUTOMATICO = "indicadorEncerramentoAutomatico";
    
    public final static String UNIDADE_ORGANIZACIONAL = "unidadeOrganizacional";
    
    public final static String ESPECIFICACAO_IMOVEL_SITUACAO = "especificacaoImovelSituacao";
    
    public final static String DEBITO_TIPO = "debitoTipo";
    
    public final static String SOLICITACAO_TIPO_ESPECIFICACAO_NOVO_RA ="solicitacaoTipoEspecificacaoNovoRA";

    public final static String INDICADOR_INFORMAR_CONTA_RA = "indicadorInformarContaRA";
    
    public final static String CODIGO_CONSTANTE = "codigoConstante";
    
    public final static String INDICADOR_LOJA_VIRTUAL = "indicadorLojaVirtual";
}
