package gcom.batch.cadastro;

import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocalHome;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesJNDI;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

public class BatchEmitirTermoAlteracaoCadastral implements MessageDrivenBean, MessageListener {

	private static final long serialVersionUID = 1L;
	
	private Logger logger;
	
	public BatchEmitirTermoAlteracaoCadastral() {
		super();
		logger = Logger.getLogger(BatchEmitirTermoAlteracaoCadastral.class);
	}

	public void onMessage(Message message) {
		
		if (message instanceof ObjectMessage) {
			
			ObjectMessage objectMessage = (ObjectMessage) message;
			
			try {
				
				this.getControladorAtualizacaoCadastral().emitirTermoAlteracaoCadastral(
						(Integer) ((Object[])objectMessage.getObject())[0],
						(Usuario)((Object[])objectMessage.getObject())[1]);
			} catch (Exception ce) {
				ce.printStackTrace();
				logger.error("Erro ao emitir comunicado alteracao cadastral");
			}
			
		}
		
	}

	public void ejbRemove() throws EJBException {
	}

	public void setMessageDrivenContext(MessageDrivenContext arg0)
			throws EJBException {

	}

	protected ControladorAtualizacaoCadastralLocal getControladorAtualizacaoCadastral() {
		ControladorAtualizacaoCadastralLocalHome localHome = null;
		ControladorAtualizacaoCadastralLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorAtualizacaoCadastralLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ATUALIZACAO_CADASTRAL);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	public void ejbCreate() {

	}

}
