package gcom.batch.cobranca;

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

public class BatchGerarArquivoTextoPagamentosContasCobrancaEmpresaMDB implements
		MessageListener, MessageDrivenBean {

	private static final long serialVersionUID = 1L;

	public BatchGerarArquivoTextoPagamentosContasCobrancaEmpresaMDB() {
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
				
				Integer idEmpresa = (Integer) ((Object[]) objectMessage.getObject()) [0];
				
				Integer referenciaInicial = (Integer)((Object[]) objectMessage.getObject()) [1];
				
				Integer referenciaFinal = (Integer)((Object[]) objectMessage.getObject()) [2];
				
				int idFuncionalidadeIniciada = (Integer)((Object[]) objectMessage.getObject()) [3];
				
				Integer idUnidadeNegocio = (Integer)((Object[]) objectMessage.getObject()) [4];
													
					this.getControladorCobranca()
						.gerarArquivoTextoPagamentosContasEmCobrancaEmpresa(
								idEmpresa,
								referenciaInicial, 
								referenciaFinal, 
								idFuncionalidadeIniciada, 
								idUnidadeNegocio);
									

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
	 * Retorna o valor de ControladorCobrancaLocal
	 * 
	 * @return O valor de ControladorCobrancaLocal
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
