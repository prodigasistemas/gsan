package gcom.cadastro.localidade;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public interface ControladorLocalidadeHome extends javax.ejb.EJBHome {
    public ControladorLocalidadeRemote create() throws CreateException,
            RemoteException;
}
