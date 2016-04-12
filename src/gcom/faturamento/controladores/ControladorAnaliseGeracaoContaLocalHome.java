package gcom.faturamento.controladores;

import javax.ejb.CreateException;

public interface ControladorAnaliseGeracaoContaLocalHome extends javax.ejb.EJBLocalHome {
    public ControladorAnaliseGeracaoContaLocal create() throws CreateException;
}