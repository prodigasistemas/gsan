package gcom.faturamento.controladores;

import javax.ejb.CreateException;

public interface ControladorDebitoACobrarLocalHome extends javax.ejb.EJBLocalHome {
    public ControladorDebitoACobrarLocal create() throws CreateException;
}