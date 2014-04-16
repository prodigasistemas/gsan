package gcom.batch.gerencial.cadastro;

import gcom.gerencial.cadastro.ControladorGerencialCadastroLocal;
import gcom.gerencial.cadastro.ControladorGerencialCadastroLocalHome;
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
 * Descrição da classe 
 *
 * @author Rafael Corrêa
 * @date 07/03/2008
 */
public class BatchGerarResumoIndicadoresComercializacaoMDB
		implements
			MessageDrivenBean,
			MessageListener {
	
	private static final long serialVersionUID = 1L;

	public BatchGerarResumoIndicadoresComercializacaoMDB() {
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
				this.getControladorGerencialCadastro().gerarResumoIndicadoresComercializacao((Integer) ((Object[]) objectMessage.getObject())[0]);
				
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
	 * Retorna o valor de controladorCadastro
	 * 
	 * @return O valor de controladorCadastro
	 */
	private ControladorGerencialCadastroLocal getControladorGerencialCadastro() {

		ControladorGerencialCadastroLocalHome localHome = null;
		ControladorGerencialCadastroLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorGerencialCadastroLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_GERENCIAL_CADASTRO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
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
