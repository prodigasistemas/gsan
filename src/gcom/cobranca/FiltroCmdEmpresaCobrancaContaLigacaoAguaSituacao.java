package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroCmdEmpresaCobrancaContaLigacaoAguaSituacao extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroCmdEmpresaCobrancaContaLigacaoAguaSituacao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroCmdEmpresaCobrancaContaLigacaoAguaSituacao() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    public final static String COMANDO_EMPRESA_COBRANCA_CONTA = "comandoEmpresaCobrancaConta";
    
    public final static String ID_COMANDO_EMPRESA_COBRANCA_CONTA = "comandoEmpresaCobrancaConta.id";
    
    public final static String ID_LIGACAO_AGUA_SITUACAO = "ligacaoAguaSituacao.id";
    
    public final static String LIGACAO_AGUA_SITUACAO = "ligacaoAguaSituacao";

    public final static String CELS_ID_COMANDO_EMPRESA_COBRANCA_CONTA_ID = "cels_id.comandoEmpresaCobrancaContaId";
    
    public final static String CELS_ID_LIGACAO_AGUA_SITUACAO_ID = "cels_id.ligacaoAguaSituacaoId";
}
