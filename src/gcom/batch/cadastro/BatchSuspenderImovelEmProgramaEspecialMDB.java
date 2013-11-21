package gcom.batch.cadastro;

import java.util.Collection;
import java.util.Iterator;

import gcom.cadastro.ControladorCadastroLocal;
import gcom.cadastro.ControladorCadastroLocalHome;
import gcom.micromedicao.Rota;
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

public class BatchSuspenderImovelEmProgramaEspecialMDB implements
		MessageListener, MessageDrivenBean {

	private static final long serialVersionUID = 1L;
	
	public BatchSuspenderImovelEmProgramaEspecialMDB() {
		super();
	}	
	
	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {

			ObjectMessage objectMessage = (ObjectMessage) message;
			
			try {
				
				Collection colecaoRotas = (Collection) ((Object[]) objectMessage.getObject()) [2];
				Iterator iterator = colecaoRotas.iterator();
				
				while(iterator.hasNext()) {
					
					Object[] array = (Object[]) iterator.next();
					Rota rota = (Rota) array[1];
					
					this.getControladorCadastro().suspenderImovelEmProgramaEspecialBatch(
							(Integer) ((Object[]) objectMessage.getObject())[0],
							(Usuario) ((Object[]) objectMessage.getObject())[1],
							rota);
					
				}

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
	 * Retorna a interface remota de ControladorCadastro
	 * 
	 * @return A interface remota do controlador de parâmetro
	 */
	protected ControladorCadastroLocal getControladorCadastro() {
		ControladorCadastroLocalHome localHome = null;
		ControladorCadastroLocal local = null;

		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorCadastroLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_CADASTRO_SEJB);

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
