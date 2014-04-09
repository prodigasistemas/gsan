package gcom.atendimentopublico.ligacaoesgoto;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;


/**
 * Local Home do ControladorLigacaoEsgoto
 * 
 * @author Leonardo Regis
 * @date 08/09/2006
 */
public interface ControladorLigacaoEsgotoLocalHome extends EJBLocalHome {
    public ControladorLigacaoEsgotoLocal create() throws CreateException;
}
