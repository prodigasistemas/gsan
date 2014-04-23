package gcom.atendimentopublico.ordemservico;


import javax.ejb.CreateException;

public interface ControladorOrdemServicoLocalHome extends javax.ejb.EJBLocalHome {
    
	public ControladorOrdemServicoLocal create() throws CreateException;
}
