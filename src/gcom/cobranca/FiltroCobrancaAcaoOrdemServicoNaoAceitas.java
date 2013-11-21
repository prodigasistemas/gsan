package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroCobrancaAcaoOrdemServicoNaoAceitas extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
     * Constructor for the FiltroCobrancaAcaoOrdemServicoNaoAceitas object
     */
    public FiltroCobrancaAcaoOrdemServicoNaoAceitas() {
    }

    /**
     * Constructor for the FiltroCobrancaAcaoOrdemServicoNaoAceitas object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroCobrancaAcaoOrdemServicoNaoAceitas(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
    
    public final static String ORDEM_SERVICO = "ordemServico";
    
    public final static String COBRANCA_ACAO = "cobrancaAcao";
    
    public final static String ORDEM_SERVICO_ID = "ordemServico.id";
    
    public final static String COBRANCA_ACAO_ID = "cobrancaAcao.id";
    
    public final static String COMP_ID_ORDEM_SERVICO_ID = "comp_id.ordemServico.id";
    
    public final static String COMP_ID_COBRANCA_ACAO_ID = "comp_id.cobrancaAcao.id";
    
    public final static String MOTIVO_NAO_ACEITACAO = "motivoNaoAceitacao";
    
}
