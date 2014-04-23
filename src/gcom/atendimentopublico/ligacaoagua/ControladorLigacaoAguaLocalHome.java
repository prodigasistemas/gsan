package gcom.atendimentopublico.ligacaoagua;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;


/**
 * Interface do ControladorLigacaoAgua
 * 
 * @author Leonardo Regis
 * @date 09/09/2006
 */
public interface ControladorLigacaoAguaLocalHome extends EJBLocalHome {
    public ControladorLigacaoAguaLocal create() throws CreateException;
}
