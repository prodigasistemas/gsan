package gcom.atualizacaocadastral;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

import gcom.util.ConstantesJNDI;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;

public class BatchAtualizacaoCadastralMDB implements MessageDrivenBean, MessageListener {

	private static final long serialVersionUID = -4276287730534911539L;

	private Logger logger;

	public void ejbRemove() throws EJBException {}

	public void setMessageDrivenContext(MessageDrivenContext arg0) throws EJBException {}

	public BatchAtualizacaoCadastralMDB() {
		super();
		logger = Logger.getLogger(BatchAtualizacaoCadastralMDB.class);
	}

	public void onMessage(Message message) {

		if (message instanceof ObjectMessage) {

			ObjectMessage objectMessage = (ObjectMessage) message;

			try {

				this.getControladorAtualizacaoCadastral().atualizarImoveisAprovados(
						(Integer) ((Object[]) objectMessage.getObject())[0],
						(Integer) ((Object[]) objectMessage.getObject())[1]);
				
			} catch (Exception e) {
				logger.error("Erro ao atualizar imoveis atualizados");
			}
		}
	}

	private ControladorAtualizacaoCadastralLocal getControladorAtualizacaoCadastral() {

		ControladorAtualizacaoCadastralLocalHome localHome = null;
		ControladorAtualizacaoCadastralLocal local = null;

		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorAtualizacaoCadastralLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_ATUALIZACAO_CADASTRAL);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

	public void ejbCreate() {}
}
