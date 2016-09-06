package gcom.cadastro.controladores;

import javax.ejb.CreateException;

public interface ControladorAtualizacaoCadastroLocalHome extends javax.ejb.EJBLocalHome {
    public ControladorAtualizacaoCadastroLocal create() throws CreateException;
}