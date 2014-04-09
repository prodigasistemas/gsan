package gcom.faturamento;

import gcom.util.ControladorException;

public class MobileComunicationException extends ControladorException {

    private static final long serialVersionUID = 4347956694867712595L;

    public MobileComunicationException(String mensagem) {
        super(mensagem);
    }
    
    public MobileComunicationException(String mensagem, Throwable e) {
        super(mensagem, e);
    }
    

}
