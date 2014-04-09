package gcom.batch.micromedicao;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class BatchGerarRAOSAnormalidadeConsumo implements MessageDrivenBean, MessageListener {
	
	private static final long serialVersionUID = 1L;

	public BatchGerarRAOSAnormalidadeConsumo() {
		super();
	}

	public void setMessageDrivenContext(MessageDrivenContext ctx)
			throws EJBException {
	}

	public void ejbRemove() throws EJBException {

	}

	public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {

//            ObjectMessage objectMessage = (ObjectMessage) message;
//            try {
//                this.getControladorMicromedicao().gerarRAOSAnormalidadeConsumo(
//						(Collection) ((Object[]) objectMessage.getObject())[0],
//						(FaturamentoGrupo) ((Object[]) objectMessage.getObject())[1],						
//						(Integer) ((Object[]) objectMessage.getObject())[2]);            
//            } catch (JMSException e) {
//                System.out.println("Erro no MDB");
//                e.printStackTrace();
//            } catch (ControladorException e) {
//                System.out.println("Erro no MDB");
//                e.printStackTrace();
//            }
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
