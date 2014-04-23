package gcom.util.email;

/**
 * Exceção lançada quando ocorre um erro de e-mail Company: COMPESA - Companhia
 * Pernambucana de Saneamento GPD - Gerência de Processamento de Dados DDM -
 * Divisão de Desenvolvimento e Manutenção
 * 
 * @author Rodrigo Silveira
 * @version 1.0
 */
public class ErroEmailException extends Exception {
	private static final long serialVersionUID = 1L;
    private Exception excecaoInterna;

    private int codigoErro;

    /**
     * Construtor da classe ErroEmailException
     * 
     * @param pMensagem
     *            Descrição do parâmetro
     * @param pExcecaoInterna
     *            Descrição do parâmetro
     * @param pCodigoErro
     *            Descrição do parâmetro
     */
    public ErroEmailException(String pMensagem, Exception pExcecaoInterna,
            int pCodigoErro) {
        super(pMensagem);
        excecaoInterna = pExcecaoInterna;
        codigoErro = pCodigoErro;
    }

    /**
     * Construtor da classe ErroEmailException
     * 
     * @param pMensagem
     *            Descrição do parâmetro
     */
    public ErroEmailException(String pMensagem) {
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
