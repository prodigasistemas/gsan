package gcom.faturamento;

import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.MetodosBatch;
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

public class ControladorBatchMicromedicao implements MessageDrivenBean,
		MessageListener {
	private static final long serialVersionUID = 1L;
	public ControladorBatchMicromedicao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setMessageDrivenContext(MessageDrivenContext ctx)
			throws EJBException {
		// TODO Auto-generated method stub

	}

	public void ejbRemove() throws EJBException {
		// TODO Auto-generated method stub

	}

	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {

			ObjectMessage objectMessage = (ObjectMessage) message;
			try {
				switch (objectMessage.getIntProperty("nomeMetodo")) {
				case (MetodosBatch.REGISTRAR_LEITURAS_ANORMALIDADES): {
					this.getControladorMicromedicao()
							.registrarLeiturasAnormalidades(
									(Collection) ((Object[]) objectMessage
											.getObject())[0],
									(Integer) ((Object[]) objectMessage
											.getObject())[1],
									(Integer) ((Object[]) objectMessage
											.getObject())[2],
									(Usuario) ((Object[]) objectMessage
											.getObject())[3],
											(String)((Object[]) objectMessage
													.getObject())[4]);
					
					break;

				}
				
				case (MetodosBatch.ATUALIZAR_LEITURA_ANORMALIDADE_CELULAR_CASO_SISTEMA): {
//					this.getControladorMicromedicao()
//							.atualizarLeituraAnormalidadeCelularCasoSistema(
//									(Vector<DadosMovimentacao>) ((Object[]) objectMessage
//											.getObject())[0]);
						
					break;
				}

					
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

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 */
	public void ejbCreate() {
		// TODO Auto-generated method stub
	}
}
