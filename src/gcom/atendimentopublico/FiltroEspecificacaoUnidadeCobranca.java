package gcom.atendimentopublico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroEspecificacaoUnidadeCobranca extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroEspecificacaoUnidadeCobranca(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroEspecificacaoUnidadeCobranca() {
    }


    public final static String COMP_ID_SOLICITACAO_TIPO_ESPECIFICACAO_ID = "comp_id.solicitacaoTipoEspecificacaoId";
    
    public final static String SOLICITACAO_TIPO_ESPECIFICACAO = "solicitacaoTipoEspecificacao";
    
    public final static String ID_SOLICITACAO_TIPO_ESPECIFICACAO = "solicitacaoTipoEspecificacao.id";

    public final static String COMP_ID_COBRANCA_SITUACAO_ID = "comp_id.cobrancaSituacaoId";
    
    public final static String COBRANCA_SITUACAO = "cobrancaSituacao";

    public final static String ID_COBRANCA_SITUACAO = "cobrancaSituacao.id";
    
    public final static String UNIDADE_ORGANIZACIONAL = "unidadeOrganizacional";
    
    public final static String ID_UNIDADE_ORGANIZACIONAL = "unidadeOrganizacional.id";
    
}
