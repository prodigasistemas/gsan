package gcom.batch.atendimentopublico;

import gcom.atendimentopublico.ControladorAtendimentoPublicoLocal;
import gcom.atendimentopublico.ControladorAtendimentoPublicoLocalHome;
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

public class BatchGerarArquivoOrdemDeFiscalizacaoMDB implements
		MessageListener, MessageDrivenBean {

	private static final long serialVersionUID = 1L;

	public BatchGerarArquivoOrdemDeFiscalizacaoMDB() {
		super();
	}

	public void ejbRemove() throws EJBException {

	}

	public void setMessageDrivenContext(MessageDrivenContext arg0)
			throws EJBException {

	}


	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {

			ObjectMessage objectMessage = (ObjectMessage) message;
			
			try {

					this.getControladorAtendimentoPublico()
							.gerarArquivoOrdemDeFiscalizacao(
									(Integer) ((Object[]) objectMessage.getObject())[0],
									(Usuario) ((Object[]) objectMessage.getObject())[1]
									);

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
	 * Default create method
	 * 
	 * @throws CreateException
	 */
	public void ejbCreate() {

	}
	
	/**
	 * Retorna o valor de controladorAtendimentoPublico
	 * 
	 * @return O valor de controladorAtendimentoPublico
	 */
	protected ControladorAtendimentoPublicoLocal getControladorAtendimentoPublico() {

		ControladorAtendimentoPublicoLocalHome localHome = null;
		ControladorAtendimentoPublicoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorAtendimentoPublicoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ATENDIMENTO_PUBLICO_SEJB);
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

}
