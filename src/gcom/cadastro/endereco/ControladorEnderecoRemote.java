package gcom.cadastro.endereco;

import gcom.cadastro.geografico.Bairro;
import gcom.util.ControladorException;

import java.rmi.RemoteException;
import java.util.Collection;

/**
 * Declaração pública de serviços do Session Bean de ControladorEndereço
 * 
 * @author Sávio Luiz
 * @created 23 de Agosto de 2005
 */
public interface ControladorEnderecoRemote extends javax.ejb.EJBObject {

	public Integer inserirLogradouro(Logradouro logradouro, Bairro bairro) throws RemoteException;

	@SuppressWarnings("rawtypes")
	public void inserirCepImportados(Collection cepsImportados) throws RemoteException;

}
