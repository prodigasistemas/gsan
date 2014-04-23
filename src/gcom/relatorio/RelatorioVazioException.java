package gcom.relatorio;

import gcom.tarefa.TarefaException;

/**
 * Exceção gerada quando o relatório não possui dados para exibição
 * 
 * @author rodrigo
 */
public class RelatorioVazioException extends TarefaException {
	private static final long serialVersionUID = 1L;
    private Throwable excecaoCausa;

    private long timestamp;

    /**
     * Construtor da classe SistemaException
     */
    public RelatorioVazioException() {
    	super(null);
    }

    /**
     * Construtor da classe SistemaException
     * 
     * @param mensagem
     *            Mensagem da exceção
     */

    public RelatorioVazioException(String mensagem) {
    	super(mensagem);
    }

    /**
     * Construtor da classe SistemaException
     * 
     * @param excecaoCausa
     *            Exceção inicial a ser encapsulada
     */
    public RelatorioVazioException(Exception excecaoCausa) {
    	super(null, excecaoCausa);
        this.excecaoCausa = excecaoCausa;
    }

    /**
     * Construtor da classe SistemaException
     * 
     * @param excecaoCausa
     *            Exceção inicial a ser encapsulada
     * @param mensagem
     *            Mensagem da exceção
     */
    public RelatorioVazioException(Exception excecaoCausa, String mensagem) {
    	super(mensagem, excecaoCausa);
        this.excecaoCausa = excecaoCausa;

    }

    /**
     * Retorna o timestamp do erro
     * 
     * @return O timestamp do erro
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Retorna o valor de excecaoCausa
     * 
     * @return O valor de excecaoCausa
     */
    public Throwable getExcecaoCausa() {
        return this.excecaoCausa;
    }

}
