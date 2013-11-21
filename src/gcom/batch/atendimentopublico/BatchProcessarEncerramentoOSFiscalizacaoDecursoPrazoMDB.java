package gcom.batch.atendimentopublico;

import gcom.atendimentopublico.ControladorAtendimentoPublicoLocal;
import gcom.atendimentopublico.ControladorAtendimentoPublicoLocalHome;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * [UC1197] Encerrar Comandos de OS Seletiva de Inspeção de Anormalidade
 *
 * @author Vivianne Sousa
 * @created 21/07/2011
 */
public class BatchProcessarEncerramentoOSFiscalizacaoDecursoPrazoMDB implements MessageDrivenBean,
		MessageListener {
	
	private static final long serialVersionUID = 1L;

	public BatchProcessarEncerramentoOSFiscalizacaoDecursoPrazoMDB() {
		super();
	}

	public void setMessageDrivenContext(MessageDrivenContext ctx)
			throws EJBException {

	}

	public void ejbRemove() throws EJBException {

	}

    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {

            ObjectMessage objectMessage = (ObjectMessage) message;
            try {
            	
            	this.getControladorAtendimentoPublico().ProcessarEncerramentoOSFiscalizacaoDecursoPrazo(
            			 (Integer) ((Object[]) objectMessage.getObject())[0]
            	);

            
            } catch (ControladorException e) {
                System.out.println("Erro no MDB");
                e.printStackTrace();
            } catch (JMSException e) {
                System.out.println("Erro no MDB");
                e.printStackTrace();
			}
        }

    }

    
    private ControladorAtendimentoPublicoLocal getControladorAtendimentoPublico() {
		ControladorAtendimentoPublicoLocalHome localHome = null;
		ControladorAtendimentoPublicoLocal local = null;

		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorAtendimentoPublicoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ATENDIMENTO_PUBLICO_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
    

    /**
     * Default create method
     * 
     * @throws CreateException
     */
    public void ejbCreate() {

    }

}
