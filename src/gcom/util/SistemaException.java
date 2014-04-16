package gcom.util;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Representa uma falha de sistema (comunicação, instanciamento, etc)
 * 
 * @author Administrador
 */
public class SistemaException extends RuntimeException {
	private static final long serialVersionUID = 1L;
    private Throwable excecaoCausa;

    private long timestamp;

    /**
     * Construtor da classe SistemaException
     */
    public SistemaException() {
    }

    /**
     * Construtor da classe SistemaException
     * 
     * @param mensagem
     *            Mensagem da exceção
     */

    public SistemaException(String mensagem) {
    }

    /**
     * Construtor da classe SistemaException
     * 
     * @param excecaoCausa
     *            Exceção inicial a ser encapsulada
     */
    public SistemaException(Exception excecaoCausa) {
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
    public SistemaException(Exception excecaoCausa, String mensagem) {
        //        super(Mensagens.getMensagem(mensagem));
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

    /**
     */
    public void printStackTrace() {
        super.printStackTrace();

        System.err.println("------------------> Root Exception --> ");

        if (excecaoCausa != null) {
        	excecaoCausa.printStackTrace();
        }
    }

    /**
     * @param s
     */
    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s);

        s.println("------------------> Root Exception --> ");
        
        if (excecaoCausa != null) {
        	excecaoCausa.printStackTrace(s);
        }
    }

    /**
     * @param s
     */
    public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);

        s.println("------------------> Root Exception --> ");

        if (excecaoCausa != null) 
        	excecaoCausa.printStackTrace(s);
    }

}
