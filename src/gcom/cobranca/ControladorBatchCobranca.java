package gcom.cobranca;

import gcom.cobranca.controladores.ControladorCobrancaPorResultadoLocal;
import gcom.cobranca.controladores.ControladorCobrancaPorResultadoLocalHome;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.MetodosBatch;
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

public class ControladorBatchCobranca implements MessageDrivenBean, MessageListener {

	private static final long serialVersionUID = 8700399863315415818L;

	public ControladorBatchCobranca() {
		super();
	}

	public void ejbCreate() {}
	
	public void setMessageDrivenContext(MessageDrivenContext ctx) throws EJBException {}

	public void ejbRemove() throws EJBException {}

	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {

			ObjectMessage objectMessage = (ObjectMessage) message;
			try {
				switch (objectMessage.getIntProperty("nomeMetodo")) {
					case (MetodosBatch.INFORMAR_CONTAS_COBRANCA_EMPRESA): {
						getControladorCobrancaPorResultado().gerarComando((ComandoEmpresaCobrancaContaHelper) ((Object[]) objectMessage.getObject())[0]);
						break;
	
					}
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
			ControladorCobrancaPorResultadoLocalHome localHome = (ControladorCobrancaPorResultadoLocalHome) ServiceLocator.getInstancia().getLocalHome(ConstantesJNDI.CONTROLADOR_COBRANCA_POR_RESULTADO_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
}
