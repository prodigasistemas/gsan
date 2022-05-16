package gcom.batch.faturamento;

import gcom.cadastro.localidade.Localidade;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.FaturamentoGrupo;
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

/**
 * 
 * [UC0994] - Envio de Email da Conta para o Cliente
 * 
 * @author Fernando Fontelles Filho
 * 
 * @date 01/03/2010
 *
 */

public class BatchEnvioEmailContaParaClienteMDB
		implements
			MessageDrivenBean,
			MessageListener {
	private static final long serialVersionUID = 1L;

	public BatchEnvioEmailContaParaClienteMDB() {
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
				this.getControladorFaturamento().envioEmailContaParaCliente(
						(Integer) ((Object[]) objectMessage.getObject())[0],
						(Integer) ((Object[]) objectMessage.getObject())[1],
						(Integer) ((Object[]) objectMessage.getObject())[3]);

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
	 * Retorna o valor de controladorMicromedicao
	 * 
	 * @return O valor de controladorMicromedicao
	 */
	private ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
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
