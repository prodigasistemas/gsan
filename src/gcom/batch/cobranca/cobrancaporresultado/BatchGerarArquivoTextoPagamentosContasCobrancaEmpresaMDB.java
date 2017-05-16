package gcom.batch.cobranca.cobrancaporresultado;

import gcom.cobranca.controladores.ControladorCobrancaPorResultadoLocal;
import gcom.cobranca.controladores.ControladorCobrancaPorResultadoLocalHome;
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

public class BatchGerarArquivoTextoPagamentosContasCobrancaEmpresaMDB implements MessageListener, MessageDrivenBean {

	private static final long serialVersionUID = 1L;

	public BatchGerarArquivoTextoPagamentosContasCobrancaEmpresaMDB() {
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

				int idFuncionalidadeIniciada = (Integer)((Object[]) objectMessage.getObject())[0];
				Integer idEmpresa = (Integer) ((Object[]) objectMessage.getObject())[1];

				this.getControladorCobrancaPorResultado().gerarArquivoTextoPagamentosCobrancaEmpresa(idFuncionalidadeIniciada, idEmpresa);

			} catch (JMSException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			} catch (ControladorException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			}
		}
	}

	private ControladorCobrancaPorResultadoLocal getControladorCobrancaPorResultado() {
		ControladorCobrancaPorResultadoLocalHome localHome = null;
		ControladorCobrancaPorResultadoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorCobrancaPorResultadoLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_COBRANCA_POR_RESULTADO_SEJB);
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
