package gcom.batch.cobranca;

import gcom.cobranca.ComandoEmpresaCobrancaConta;
import gcom.cobranca.ControladorCobrancaLocal;
import gcom.cobranca.ControladorCobrancaLocalHome;
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

public class BatchGerarMovimentoContasCobrancaPorEmpresaMDB implements MessageDrivenBean, MessageListener {

	private static final long serialVersionUID = 1L;

	public BatchGerarMovimentoContasCobrancaPorEmpresaMDB() {
		super();
	}

	public void setMessageDrivenContext(MessageDrivenContext ctx) throws EJBException {
	}

	public void ejbRemove() throws EJBException {
	}

	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {
			ObjectMessage objectMessage = (ObjectMessage) message;

			try {
				this.getControladorCobranca().gerarMovimentoContasEmCobranca(
						(ComandoEmpresaCobrancaConta) ((Object[]) objectMessage.getObject())[0],
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

	private ControladorCobrancaLocal getControladorCobranca() {
		try {
			ServiceLocator locator = ServiceLocator.getInstancia();
			ControladorCobrancaLocalHome localHome = (ControladorCobrancaLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);

			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

	public void ejbCreate() {

	}
}
