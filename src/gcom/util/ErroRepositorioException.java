package gcom.util;

/**
 * Representa uma falha de repositorio
 * 
 * @author Administrador
 */
public class ErroRepositorioException extends Exception {
	private static final long serialVersionUID = 1L;
	private long timestamp;

	/**
	 * Construtor da classe SistemaException
	 */
	public ErroRepositorioException() {
	}

	/**
	 * Construtor da classe SistemaException
	 * 
	 * @param mensagem
	 *            Mensagem da exceção
	 */

	public ErroRepositorioException(String mensagem) {
	}

	/**
	 * Construtor da classe SistemaException
	 * 
	 * @param excecaoCausa
	 *            Exceção inicial a ser encapsulada
	 */
	public ErroRepositorioException(Exception excecaoCausa) {
		super(excecaoCausa);

	}

	/**
	 * Construtor da classe SistemaException
	 * 
	 * @param excecaoCausa
	 *            Exceção inicial a ser encapsulada
	 * @param mensagem
	 *            Mensagem da exceção
	 */
	public ErroRepositorioException(Exception excecaoCausa, String mensagem) {
		super(mensagem, excecaoCausa);
	}

	/**
	 * Retorna o timestamp do erro
	 * 
	 * @return O timestamp do erro
	 */
	public long getTimestamp() {
		return timestamp;
	}

}
