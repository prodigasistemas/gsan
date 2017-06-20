package gcom.batch.cobranca;

import gcom.cobranca.controladores.ControladorParcelamentoLocal;
import gcom.cobranca.controladores.ControladorParcelamentoLocalHome;
import gcom.seguranca.acesso.usuario.Usuario;
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

public class BatchCancelarParcelamentosMDB implements MessageDrivenBean, MessageListener {

	private static final long serialVersionUID = 1L;

	public BatchCancelarParcelamentosMDB() {
		super();
	}

	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {
			ObjectMessage objectMessage = (ObjectMessage) message;

			try {
				getControladorParcelamento().cancelarParcelamentos((Usuario) ((Object[]) objectMessage.getObject())[0], (Integer) ((Object[]) objectMessage.getObject())[1]);
			} catch (JMSException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			} catch (ControladorException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			}
		}
	}

	public void ejbCreate() {}

	public void ejbRemove() throws EJBException {}

	public void setMessageDrivenContext(MessageDrivenContext ctx) throws EJBException {}

	private ControladorParcelamentoLocal getControladorParcelamento() {
		try {
			ControladorParcelamentoLocalHome localHome = (ControladorParcelamentoLocalHome) ServiceLocator.getInstancia().getLocalHome(ConstantesJNDI.CONTROLADOR_PARCELAMENTO);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
}
