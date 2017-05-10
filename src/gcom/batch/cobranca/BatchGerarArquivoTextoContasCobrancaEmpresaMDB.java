package gcom.batch.cobranca;

import gcom.cobranca.controladores.ControladorCobrancaPorResultadoLocal;
import gcom.cobranca.controladores.ControladorCobrancaPorResultadoLocalHome;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class BatchGerarArquivoTextoContasCobrancaEmpresaMDB implements MessageDrivenBean, MessageListener {

	private static final long serialVersionUID = 1L;

	public BatchGerarArquivoTextoContasCobrancaEmpresaMDB() {
		super();
	}

	public void setMessageDrivenContext(MessageDrivenContext ctx) throws EJBException {

	}

	public void ejbRemove() throws EJBException {

	}

	@SuppressWarnings({ "unchecked" })
	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {

			ObjectMessage objectMessage = (ObjectMessage) message;
			try {

				Collection<Integer> comandos = (Collection<Integer>) ((Object[]) objectMessage.getObject())[0];

				if (comandos != null) {
					this.getControladorCobrancaPorResultado().gerarArquivoContas(comandos, 
							(Integer) ((Object[]) objectMessage.getObject())[1], 
							(Integer) ((Object[]) objectMessage.getObject())[2]);
				}
			} catch (JMSException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			} catch (ControladorException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			}
		}
	}

	private ControladorCobrancaPorResultadoLocal getControladorCobrancaPorResultado() {
		try {
			ServiceLocator locator = ServiceLocator.getInstancia();
			ControladorCobrancaPorResultadoLocalHome localHome = (ControladorCobrancaPorResultadoLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_COBRANCA_POR_RESULTADO_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	public void ejbCreate() {}
}
