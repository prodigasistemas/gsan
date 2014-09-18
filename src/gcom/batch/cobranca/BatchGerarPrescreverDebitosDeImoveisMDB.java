package gcom.batch.cobranca;

import java.util.Date;

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

public class BatchGerarPrescreverDebitosDeImoveisMDB
		implements
			MessageDrivenBean,
			MessageListener {

	/**
	 * @Autor Hugo Leonardo
	 * @since 07/07/2010
	 */
	private static final long serialVersionUID = 1L;

	public BatchGerarPrescreverDebitosDeImoveisMDB() {
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

				/**
				 * Prescrever o débito com base na data de vencimento da conta
				 * e não mais do anoMes referencia da conta
				 * 
				 * @author Wellington Rocha
				 * @date 25/01/2012*/
				this.getControladorCobranca().gerarPrescreverDebitosDeImoveis(
						(Integer) ((Object[]) objectMessage.getObject())[0],
						(Integer) ((Object[]) objectMessage.getObject())[1],
						(Date) ((Object[]) objectMessage.getObject())[2],
						(Integer) ((Object[]) objectMessage.getObject())[3],
						(String) ((Object[]) objectMessage.getObject())[4]);
				
			} catch (JMSException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			} catch (ControladorException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			}
		}

	}

	/**
	 * Author: Rafael Santos Data: 04/01/2006
	 * 
	 * Retorna o valor do Controlador de Cobranca
	 * 
	 * @return O valor de controladorCobrancaLocal
	 */
	private ControladorCobrancaLocal getControladorCobranca() {

		ControladorCobrancaLocalHome localHome = null;
		ControladorCobrancaLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorCobrancaLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);
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
