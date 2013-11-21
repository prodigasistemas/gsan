package gcom.batch.faturamento;

import gcom.cadastro.empresa.Empresa;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
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
 * [UC1008] Gerar TXT declaração de quitação anual de débitos
 * 
 * 	Este caso de uso permite a geração do TXT da declaração de quitação de débitos.
 * 
 * @author Hugo Amorim
 * @date 23/03/2010
 */
public class BatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitosMDB implements
		MessageDrivenBean, MessageListener {

	private static final long serialVersionUID = 1L;

	public BatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitosMDB() {
		super();
	}

	public void onMessage(Message message) {

		if (message instanceof ObjectMessage) {

			ObjectMessage objectMessage = (ObjectMessage) message;

			try {			


					this.getControladorFaturamento()
							.gerarArquivoTextoDeclaracaoQuitacaoAnualDebitos(
									(Integer) ((Object[]) objectMessage.getObject())[0],
									(Integer) ((Object[]) objectMessage.getObject())[1],
									(Empresa) ((Object[]) objectMessage.getObject())[2]);

				
			} catch (JMSException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			} catch (ControladorException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			}
		}
	}

	public void ejbRemove() throws EJBException {
	}

	public void setMessageDrivenContext(MessageDrivenContext arg0)
			throws EJBException {

	}

	/**
	 * Retorna o valor de controladorFaturamento
	 * 
	 * @return O valor de controladorFaturamento
	 */
	protected ControladorFaturamentoLocal getControladorFaturamento() {
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

	public void ejbCreate() {

	}

}
