package gcom.batch.cobranca.cobrancaporresultado;

import gcom.cadastro.empresa.Empresa;
import gcom.cobranca.controladores.ControladorCobrancaPorResultadoLocal;
import gcom.cobranca.controladores.ControladorCobrancaPorResultadoLocalHome;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class BatchEncerrarComandosDeCobrancaResultadoPorEmpresaMDB implements MessageDrivenBean,
		MessageListener {
	
	private static final long serialVersionUID = 1L;

	public BatchEncerrarComandosDeCobrancaResultadoPorEmpresaMDB() {
		super();
	}

	public void setMessageDrivenContext(MessageDrivenContext ctx)
			throws EJBException {

	}

	public void ejbRemove() throws EJBException {

	}

    @SuppressWarnings("unchecked")
	public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {

            ObjectMessage objectMessage = (ObjectMessage) message;
            try {
            	
            	this.getControladorCobrancaPorResultado().encerrarComandosCobrancaResultadoPorEmpresa(
            			(Integer) ((Object[]) objectMessage.getObject())[0],
            			(Usuario) ((Object[]) objectMessage.getObject())[1],
            			(Collection<Empresa>) ((Object[]) objectMessage.getObject())[2],
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
