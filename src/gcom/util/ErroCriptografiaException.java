package gcom.util;

/**
 * Exceção lançada quando ocorre um erro de criptografia
 * 
 * @author Rodrigo Silveira
 */
public class ErroCriptografiaException extends Exception {
	private static final long serialVersionUID = 1L;
    private Exception excecaoInterna;

    private int codigoErro;

    /**
     * Construtor da classe ErroCriptografiaException
     * 
     * @param pMensagem
     *            Descrição do parâmetro
     * @param pExcecaoInterna
     *            Descrição do parâmetro
     * @param pCodigoErro
     *            Descrição do parâmetro
     */
    public ErroCriptografiaException(String pMensagem,
            Exception pExcecaoInterna, int pCodigoErro) {
        super(pMensagem);
        excecaoInterna = pExcecaoInterna;
        codigoErro = pCodigoErro;
    }

    /**
     * Construtor da classe ErroCriptografiaException
     * 
     * @param pMensagem
     *            Descrição do parâmetro
     */
    public ErroCriptografiaException(String pMensagem) {
        super(pMensagem);
    }

    /**
     * Retorna o valor de codigoErro
     * 
     * @return O valor de codigoErro
     */
    public int getCodigoErro() {
        return codigoErro;
    }

    /**
     * Retorna o valor de excecaoInterna
     * 
     * @return O valor de excecaoInterna
     */
    public Exception getExcecaoInterna() {
        return excecaoInterna;
    }

}
