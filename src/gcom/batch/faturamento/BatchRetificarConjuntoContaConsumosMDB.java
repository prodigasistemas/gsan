package gcom.batch.faturamento;

import gcom.faturamento.controladores.ControladorRetificarContaLocal;
import gcom.faturamento.controladores.ControladorRetificarContaLocalHome;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;

import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class BatchRetificarConjuntoContaConsumosMDB implements MessageDrivenBean,
		MessageListener {
	
	private static final long serialVersionUID = 1L;

	public BatchRetificarConjuntoContaConsumosMDB() {
		super();
	}

	public void setMessageDrivenContext(MessageDrivenContext ctx)
			throws EJBException {

	}

	public void ejbRemove() throws EJBException {

	}

    @SuppressWarnings("rawtypes")
	public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {

            ObjectMessage objectMessage = (ObjectMessage) message;
            try {
            	this.getControladorRetificarConta().retificarConjuntoContaConsumos(
            			(Integer) ((Object[]) objectMessage.getObject())[0],
            			(Map) ((Object[]) objectMessage.getObject())[1]);

            } catch (JMSException e) {
                System.out.println("Erro no MDB");
                e.printStackTrace();
            } catch (ControladorException e) {
                System.out.println("Erro no MDB");
                e.printStackTrace();
            }
        }

    }

    
    private ControladorRetificarContaLocal getControladorRetificarConta() {

		ControladorRetificarContaLocalHome localHome = null;
		ControladorRetificarContaLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorRetificarContaLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_RETIFICAR_CONTA);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

    public void ejbCreate() {
    }
}
