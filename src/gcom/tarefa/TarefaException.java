package gcom.tarefa;


/**
 * Representa um problema no nível do Action
 * 
 * @author thiago
 * @date 24/01/2006
 */
public class TarefaException extends RuntimeException {
	private static final long serialVersionUID = 1L;
    private String parametroMensagem;

    /**
     * Construtor da classe TarefaException
     * 
     * @param mensagem
     *            Chave do erro que aconteceu no controlador(mensagem obtida num
     *            arquivo de properties)
     * @param excecaoCausa
     *            Exceção que originou o problema
     */
    public TarefaException(String mensagem, Exception excecaoCausa) {
        super(mensagem, excecaoCausa);

    }

    /**
     * Construtor da classe TarefaException
     * 
     * @param mensagem
     *            Descrição do parâmetro
     */
    public TarefaException(String mensagem) {
        super(mensagem, null);
    }

    /**
     * Construtor da classe TarefaException
     * 
     * @param mensagem
     *            Descrição do parâmetro
     * @param excecaoCausa
     *            Descrição do parâmetro
     * @param parametroMensagem
     *            Descrição do parâmetro
     */
    public TarefaException(String mensagem, Exception excecaoCausa,
            String parametroMensagem) {
        super(mensagem, excecaoCausa);
        this.parametroMensagem = parametroMensagem;

    }

	public TarefaException(String mensagem, String parametroMensagem) {
		super(mensagem);
		this.parametroMensagem = parametroMensagem;

	}

    /**
     * Retorna o valor de parametroMensagem
     * 
     * @return O valor de parametroMensagem
     */
    public String getParametroMensagem() {
        return parametroMensagem;
    }

}
