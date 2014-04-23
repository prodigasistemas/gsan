package gcom.batch.micromedicao;

import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
import gcom.micromedicao.MovimentoHidrometroHelper;
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

public class BatchGerarMovimentoHidrometroMDB implements
		MessageDrivenBean, MessageListener {

	private static final long serialVersionUID = 1L;

	public BatchGerarMovimentoHidrometroMDB() {
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

				MovimentoHidrometroHelper helper =  (MovimentoHidrometroHelper) ((Object[]) objectMessage
						.getObject())[0];

				
				if (helper != null) {

					this
							.getControladorMicromedicao()
								.inserirAtualizarMovimentacaoHidrometroIdsBatch(
										
										helper.getColecaoHidrometroSelecionado(),
										helper.getDataMovimentacao(),
										helper.getHoraMovimentacao(),
										helper.getIdLocalArmazenagemDestino(),
										helper.getIdMotivoMovimentacao(),
										helper.getParecer(),
										helper.getUsuario(),
										(Integer) ((Object[]) objectMessage.getObject())[1]);

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
	 * Retorna o valor de ControladorMicromedicaoLocal
	 * 
	 * @return O valor de ControladorMicromedicaoLocal
	 */
	private ControladorMicromedicaoLocal getControladorMicromedicao() {
		ControladorMicromedicaoLocalHome localHome = null;
		ControladorMicromedicaoLocal local = null;

//		 pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
		locator = ServiceLocator.getInstancia();

		localHome = (ControladorMicromedicaoLocalHome) locator
		.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_MICROMEDICAO_SEJB);
//		 guarda a referencia de um objeto capaz de fazer chamadas
//		 objetos remotamente
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
