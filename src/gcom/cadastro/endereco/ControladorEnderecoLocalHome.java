package gcom.cadastro.endereco;

import javax.ejb.CreateException;

public interface ControladorEnderecoLocalHome extends javax.ejb.EJBLocalHome {
    public ControladorEnderecoLocal create() throws CreateException;
}
