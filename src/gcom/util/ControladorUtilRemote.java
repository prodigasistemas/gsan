package gcom.util;

import java.rmi.RemoteException;

public interface ControladorUtilRemote extends javax.ejb.EJBObject {
	public int registroMaximo(Class classe) throws RemoteException;
}
