package gcom.batch.cobranca.spcserasa;

import gcom.cobranca.NegativacaoComando;
import gcom.cobranca.NegativacaoCriterio;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorContrato;
import gcom.cobranca.NegativadorMovimento;
import gcom.spcserasa.ControladorSpcSerasaLocal;
import gcom.spcserasa.ControladorSpcSerasaLocalHome;
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

public class BatchExecutarComandoNegativacaoMDB implements MessageDrivenBean, MessageListener {
	
	private static final long serialVersionUID = 1L;

	public BatchExecutarComandoNegativacaoMDB() {
		super();
	}

	public void setMessageDrivenContext(MessageDrivenContext ctx) throws EJBException {}

	public void ejbRemove() throws EJBException {}

	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {
			ObjectMessage objectMessage = (ObjectMessage) message;
			
			try {
				this.getControladorSpcSerasa().gerarMovimentodeInclusaoNegativacaoPorCriterio(
						(Integer) ((Object[]) objectMessage.getObject())[0],
						(NegativacaoCriterio) ((Object[]) objectMessage.getObject())[1],
						(Negativador) ((Object[]) objectMessage.getObject())[2],
						(NegativacaoComando) ((Object[]) objectMessage.getObject())[3],
						(NegativadorContrato) ((Object[]) objectMessage.getObject())[4],
						(NegativadorMovimento) ((Object[]) objectMessage.getObject())[5],
						(Integer) ((Object[]) objectMessage.getObject())[6]);

			} catch (JMSException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			} catch (ControladorException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			}
		}
	}

	private ControladorSpcSerasaLocal getControladorSpcSerasa() {
		ControladorSpcSerasaLocalHome localHome = null;
		ControladorSpcSerasaLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorSpcSerasaLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_SPC_SERASA_SEJB);
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
