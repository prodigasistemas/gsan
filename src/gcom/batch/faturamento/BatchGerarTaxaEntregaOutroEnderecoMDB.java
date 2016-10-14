package gcom.batch.faturamento;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.jboss.logging.Logger;

import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesJNDI;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;

public class BatchGerarTaxaEntregaOutroEnderecoMDB implements MessageDrivenBean, MessageListener {
	private static final long serialVersionUID = -6273559462969163613L;

	private static Logger logger = Logger.getLogger(BatchGerarTaxaEntregaOutroEnderecoMDB.class);

	public BatchGerarTaxaEntregaOutroEnderecoMDB() {
		super();
	}

	public void setMessageDrivenContext(MessageDrivenContext ctx) throws EJBException {

	}

	public void ejbRemove() throws EJBException {

	}

	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {
			ObjectMessage objectMessage = (ObjectMessage) message;
			
			try {
				this.getControladorFaturamento().gerarTaxaEntregaDeContaEmOutroEndereco(
						(Collection<Rota>) ((Object[]) objectMessage.getObject())[0],
						(Integer) ((Object[]) objectMessage.getObject())[1],
						(Integer) ((Object[]) objectMessage.getObject())[2]);
			} catch (Exception e) {
				logger.error("Erro ao gerar taxa de entrega", e);
			}
		}

	}

	private ControladorFaturamentoLocal getControladorFaturamento() {
		try {
			ServiceLocator locator = ServiceLocator.getInstancia();

			ControladorFaturamentoLocalHome localHome = (ControladorFaturamentoLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);

			ControladorFaturamentoLocal local = localHome.create();

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
