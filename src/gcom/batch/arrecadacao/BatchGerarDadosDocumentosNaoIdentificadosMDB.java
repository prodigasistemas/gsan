package gcom.batch.arrecadacao;

import gcom.arrecadacao.ControladorArrecadacaoLocal;
import gcom.arrecadacao.ControladorArrecadacaoLocalHome;
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

public class BatchGerarDadosDocumentosNaoIdentificadosMDB implements MessageDrivenBean, MessageListener{

	private static final long serialVersionUID = 6776254621003404089L;

	public BatchGerarDadosDocumentosNaoIdentificadosMDB() {
		super();
	}
	
	public void setMessageDrivenContext(MessageDrivenContext ctx) throws EJBException {
	}
	
	@Override
	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {

            ObjectMessage objectMessage = (ObjectMessage) message;
            try {
            	System.out.println("BatchGerarDadosDocumentosNaoIdentificadosMDB - iniciando");
                this.getControladorArrecadacao()
                        .gerarDadosDocumentosNaoIdentificados(
                        		(Integer) ((Object[]) objectMessage.getObject())[0],
                                (Integer) ((Object[]) objectMessage.getObject())[1]);

            } catch (JMSException e) {
                System.out.println("Erro no MDB");
                e.printStackTrace();
            } catch (ControladorException e) {
                System.out.println("Erro no MDB");
                e.printStackTrace();
            }
        }
	}
	
	private ControladorArrecadacaoLocal getControladorArrecadacao() {
        ControladorArrecadacaoLocalHome localHome = null;
        ControladorArrecadacaoLocal local = null;

        ServiceLocator locator = null;

        try {
            locator = ServiceLocator.getInstancia();

            localHome = (ControladorArrecadacaoLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_ARRECADACAO_SEJB);
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

	public void ejbRemove() throws EJBException {
	}

}
