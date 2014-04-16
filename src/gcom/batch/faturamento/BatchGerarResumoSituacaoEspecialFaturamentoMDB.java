package gcom.batch.faturamento;

import gcom.gerencial.faturamento.ControladorGerencialFaturamentoLocal;
import gcom.gerencial.faturamento.ControladorGerencialFaturamentoLocalHome;
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

public class BatchGerarResumoSituacaoEspecialFaturamentoMDB
		implements
			MessageDrivenBean,
			MessageListener {
	
	private static final long serialVersionUID = 1L;

	public BatchGerarResumoSituacaoEspecialFaturamentoMDB() {
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
						.getControladorGerencialFaturamento()
						.gerarResumoSituacaoEspecialFaturamento(
								(Integer) ((Object[]) objectMessage.getObject())[0],
								(Integer) ((Object[]) objectMessage.getObject())[1]);

			} catch (JMSException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			} catch (ControladorException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			}
		}

	}

	private ControladorGerencialFaturamentoLocal getControladorGerencialFaturamento() {
		ControladorGerencialFaturamentoLocalHome localHome = null;
		ControladorGerencialFaturamentoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorGerencialFaturamentoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_GERENCIAL_FATURAMENTO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
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
