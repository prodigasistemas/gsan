package gcom.cadastro.imovel;

import javax.ejb.CreateException;

public interface ControladorImovelLocalHome extends javax.ejb.EJBLocalHome {

    public ControladorImovelLocal create() throws CreateException;

}
