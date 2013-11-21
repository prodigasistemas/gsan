package gcom.batch.atendimentopublico;

import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocal;
import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocalHome;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;

import java.util.Date;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * [UC1184] ? Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
 *
 * @author Bruno Barros
 * 
 * @date 03/08/2011
 */
public class BatchGerarDadosArquivoAcompanhamentoServicoMDB implements MessageDrivenBean,
		MessageListener {
	
	private static final long serialVersionUID = 1L;

	public BatchGerarDadosArquivoAcompanhamentoServicoMDB() {
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
            	
            	this.getControladorOrdemServico().gerarArquivoAcompanharServicoRoteiroProgramado(
            			(Integer) ((Object[]) objectMessage.getObject())[0],
            			(Integer) ((Object[]) objectMessage.getObject())[1],
            			(Date) ((Object[]) objectMessage.getObject())[2]);

            } catch (JMSException e) {
                System.out.println("Erro no MDB");
                e.printStackTrace();
            } catch (ControladorException e) {
                System.out.println("Erro no MDB");
                e.printStackTrace();
            }
        }

    }

    
    private ControladorOrdemServicoLocal getControladorOrdemServico() {
		ControladorOrdemServicoLocalHome localHome = null;
		ControladorOrdemServicoLocal local = null;

		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorOrdemServicoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ORDEM_SERVICO_SEJB);

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
