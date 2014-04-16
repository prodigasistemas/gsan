package gcom.faturamento.conta;

import gcom.util.filtro.Filtro;

/**
 * filtro de Motivo Retificacao de conta
 * 
 * @author  Francisco do Nascimento
 * @created 14 de setembro de 2007
 */
public class FiltroContaMotivoRetificacaoColuna extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Description of the Field
     */
    public final static String CONTA_MOTIVO_RETIFICACAO = "contaMotivoRetificacao";
    /**
     * Description of the Field
     */
    public final static String CONTA_MOTIVO_RETIFICACAO_ID = "contaMotivoRetificacao.id";
    /**
     * Description of the Field
     */
    public final static String TABELA_COLUNA = "tabelaColuna";
    /**
     * Description of the Field
     */
    public final static String TABELA_COLUNA_ID = "tabelaColuna.id";
    
    /**
     * Construtor da classe FiltroContaMotivoRetificacao
     */
    public FiltroContaMotivoRetificacaoColuna() {
    }

    /**
     * Construtor da classe FiltroContaMotivoRetificacao
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroContaMotivoRetificacaoColuna(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
