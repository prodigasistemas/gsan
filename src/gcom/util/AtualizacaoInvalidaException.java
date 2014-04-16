package gcom.util;

/**
 * Representa uma falha de atualização de registro
 * 
 * @author Rodrigo
 */
public class AtualizacaoInvalidaException extends ErroRepositorioException {

    private Throwable excecaoCausa;

    private long timestamp;

	private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe AtualizacaoInvalidaException
     */
    public AtualizacaoInvalidaException() {
    }

    /**
     * Construtor da classe AtualizacaoInvalidaException
     * 
     * @param mensagem
     *            Descrição do parâmetro
     */
    public AtualizacaoInvalidaException(String mensagem) {
        super(mensagem);
    }

    /**
     * Construtor da classe AtualizacaoInvalidaException
     * 
     * @param excecaoCausa
     *            Descrição do parâmetro
     */
    public AtualizacaoInvalidaException(Exception excecaoCausa) {
        this.excecaoCausa = excecaoCausa;

    }

    /**
     * Construtor da classe AtualizacaoInvalidaException
     * 
     * @param excecaoCausa
     *            Exceção que originou este erro
     * @param mensagem
     *            Mensagem da exceção
     */
    public AtualizacaoInvalidaException(Exception excecaoCausa, String mensagem) {
        super(mensagem);
        this.excecaoCausa = excecaoCausa;

    }

    /**
     * Retorna o valor de timestamp
     * 
     * @return O valor de timestamp
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
