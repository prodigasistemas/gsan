package gcom.cadastro.localidade;

import gcom.util.ControladorException;

import java.rmi.RemoteException;

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

public interface ControladorLocalidadeRemote extends javax.ejb.EJBObject {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param setorComercial
	 *            Descrição do parâmetro
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public void atualizarSetorComercial(SetorComercial setorComercial)
			throws RemoteException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param localidade
	 *            Descrição do parâmetro
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public void atualizarLocalidade(Localidade localidade)
			throws RemoteException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param quadra
	 *            Descrição do parâmetro
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public void atualizarQuadra(Quadra quadra) throws RemoteException;

	// public Collection pesquisarQuadraRelatorio(Quadra quadraParametros)
	// throws RemoteException;
	
	
	/**
	 * Inseri um objeto do tipo setor comercial no BD
	 * @param setorComercial
	 * @return ID gerado
	 * @throws ControladorException
	 */
	public Integer inserirSetorComercial(SetorComercial setorComercial) throws RemoteException;
	
//	/**
//	 * < <Descrição do método>>
//	 * 
//	 * @param gerencia Regional
//	 *            Descrição do parâmetro
//	 * @exception RemoteException
//	 *                Descrição da exceção
//	 */
//	public void atualizarGerenciaRegional(GerenciaRegional gerenciaRegional)
//			throws RemoteException;
//
}
