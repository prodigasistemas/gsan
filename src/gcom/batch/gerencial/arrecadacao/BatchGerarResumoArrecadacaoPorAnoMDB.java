package gcom.batch.gerencial.arrecadacao;

import gcom.gerencial.arrecadacao.ControladorGerencialArrecadacaoLocal;
import gcom.gerencial.arrecadacao.ControladorGerencialArrecadacaoLocalHome;
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

public class BatchGerarResumoArrecadacaoPorAnoMDB
		implements
			MessageDrivenBean,
			MessageListener {
	
	private static final long serialVersionUID = 1L;

	public BatchGerarResumoArrecadacaoPorAnoMDB() {
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

				this
						.getControladorGerencialArrecadacao()
						.gerarResumoArrecadacaoPorAno(
								( (Integer) ((Object[]) objectMessage.getObject())[0]).intValue(),
								( (Integer) ((Object[]) objectMessage.getObject())[1]).intValue(),
								( (Integer) ((Object[]) objectMessage.getObject())[2]).intValue());

			} catch (JMSException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			} catch (ControladorException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			}
		}

	}

	private ControladorGerencialArrecadacaoLocal getControladorGerencialArrecadacao() {

		ControladorGerencialArrecadacaoLocalHome localHome = null;
		ControladorGerencialArrecadacaoLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorGerencialArrecadacaoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_GERENCIAL_ARRECADACAO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
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
