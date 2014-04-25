package gcom.batch.gerencial.micromedicao;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Descrição da classe 
 *
 * @author Fernando Fontelles
 * @date 20/05/2010
 */
public class BatchGerarResumoIndicadoresMicromedicaoPorAnoMDB
		implements
			MessageDrivenBean,
			MessageListener {
	
	private static final long serialVersionUID = 1L;

	public BatchGerarResumoIndicadoresMicromedicaoPorAnoMDB() {
		super();
	}

	public void setMessageDrivenContext(MessageDrivenContext ctx)
			throws EJBException {

	}

	public void ejbRemove() throws EJBException {

	}

	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {

//			ObjectMessage objectMessage = (ObjectMessage) message;
//			try {
//				this.getControladorGerencialMicromedicao().gerarResumoIndicadoresMicromedicaoPorAno((Integer) ((Object[]) objectMessage.getObject())[0]);
//			} catch (JMSException e) {
//				System.out.println("Erro no MDB");
//				e.printStackTrace();
//			} catch (ControladorException e) {
//				System.out.println("Erro no MDB");
//				e.printStackTrace();
//			}
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
