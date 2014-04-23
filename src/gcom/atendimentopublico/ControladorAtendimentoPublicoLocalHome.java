package gcom.atendimentopublico;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;


/**
 * Description of the Interface
 * 
 * @author Administrador
 * @created 25 de Abril de 2005
 */
public interface ControladorAtendimentoPublicoLocalHome extends EJBLocalHome {
    public ControladorAtendimentoPublicoLocal create() throws CreateException;
   
}
