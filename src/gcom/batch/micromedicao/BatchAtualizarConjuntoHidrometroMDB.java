package gcom.batch.micromedicao;

import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
import gcom.micromedicao.hidrometro.Hidrometro;
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

public class BatchAtualizarConjuntoHidrometroMDB implements MessageDrivenBean, MessageListener {
	private static final long serialVersionUID = 1L;

	public BatchAtualizarConjuntoHidrometroMDB() {
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
				String fixo =  (String) ((Object[]) objectMessage.getObject())[0];
				
				String fixoInicial =  (String) ((Object[]) objectMessage.getObject())[1];
				
				String fixoFinal =  (String) ((Object[]) objectMessage.getObject())[2];
				
				Hidrometro hidrometroAtualizado = (Hidrometro) ((Object[]) objectMessage.getObject())[3];

				Usuario usuarioLogado = (Usuario) ((Object[]) objectMessage.getObject())[4];
				
				int i = (Integer) ((Object[]) objectMessage.getObject())[5];
				
				int idFuncionalidadeIniciada = (Integer) ((Object[]) objectMessage.getObject())[6];

				this.getControladorMicromedicao().atualizarConjuntoHidrometroBatch(fixo,fixoInicial,fixoFinal,hidrometroAtualizado,usuarioLogado,idFuncionalidadeIniciada,i);
						
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
	 * Retorna o valor de controladorMicromedicao
	 * 
	 * @return O valor de controladorMicromedicao
	 */
	private ControladorMicromedicaoLocal getControladorMicromedicao() {
		ControladorMicromedicaoLocalHome localHome = null;
		ControladorMicromedicaoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorMicromedicaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_MICROMEDICAO_SEJB);
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
