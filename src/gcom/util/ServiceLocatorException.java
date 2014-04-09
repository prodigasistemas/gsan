package gcom.util;

/**
 * Representa uma exceção relativa a localização / instanciamento de um serviço
 * 
 * @author Administrador
 */
public class ServiceLocatorException extends Exception {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe ServiceLocatorException
     */
    public ServiceLocatorException() {
    }

    /**
     * Construtor da classe ServiceLocatorException
     * 
     * @param msg
     *            Menssagem da exceção
     */
    public ServiceLocatorException(String msg) {
        super(msg);
    }

    /**
     * Construtor da classe ServiceLocatorException
     * 
     * @param e
     *            Exceção que será encapsulada
     */
    public ServiceLocatorException(Exception e) {
        super(e);
    }

    /**
     * Construtor da classe ServiceLocatorException
     * 
     * @param e
     *            Exceção que será encapsulada
     * @param msg
     *            Menssagem da exceção
     */
    public ServiceLocatorException(Exception e, String msg) {
        super(msg, e);
    }
}
