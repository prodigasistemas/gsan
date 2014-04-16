package gcom.batch.cobranca;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.ControladorCobrancaLocal;
import gcom.cobranca.ControladorCobrancaLocalHome;
import gcom.micromedicao.Rota;
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

public class BatchGerarAtividadeAcaoCobrancaMDB
		implements
			MessageDrivenBean,
			MessageListener {
	
	private static final long serialVersionUID = 1L;

	public BatchGerarAtividadeAcaoCobrancaMDB() {
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

				/*
				 * gerarAtividadeAcaoCobranca( CobrancaGrupo grupoCobranca, int
				 * anoMesReferenciaCicloCobranca, Integer
				 * idCronogramaAtividadeAcaoCobranca, Integer
				 * idComandoAtividadeAcaoCobranca, Collection<Rota> rotas,
				 * CobrancaAcao acaoCobranca, CobrancaAtividade
				 * atividadeCobranca, Integer indicadorCriterio,
				 * CobrancaCriterio criterioCobranca, Cliente cliente,
				 * ClienteRelacaoTipo relacaoClienteImovel, String
				 * anoMesReferenciaInicial, String anoMesReferenciaFinal, Date
				 * dataVencimentoInicial, Date dataVencimentoFinal)
				 */

//				this.getControladorCobranca().gerarAtividadeAcaoCobranca(
//								(CobrancaGrupo) ((Object[]) objectMessage.getObject())[0],
//								(Integer) ((Object[]) objectMessage.getObject())[1],
//								(CobrancaAcaoAtividadeCronograma) ((Object[]) objectMessage.getObject())[2],
//								(CobrancaAcaoAtividadeComando) ((Object[]) objectMessage.getObject())[3],
//								(Collection) ((Object[]) objectMessage.getObject())[4],
//								(CobrancaAcao) ((Object[]) objectMessage.getObject())[5],
//								(CobrancaAtividade) ((Object[]) objectMessage.getObject())[6],
//								(Integer) ((Object[]) objectMessage.getObject())[7],
//								(CobrancaCriterio) ((Object[]) objectMessage.getObject())[8],
//								(Cliente) ((Object[]) objectMessage.getObject())[9],
//								(ClienteRelacaoTipo) ((Object[]) objectMessage.getObject())[10],
//								(String) ((Object[]) objectMessage.getObject())[11],
//								(String) ((Object[]) objectMessage.getObject())[12],
//								(Date) ((Object[]) objectMessage.getObject())[13],
//								(Date) ((Object[]) objectMessage.getObject())[14],
//								(Date) ((Object[]) objectMessage.getObject())[15],
//								(Integer) ((Object[]) objectMessage.getObject())[16],
//								(Cliente) ((Object[]) objectMessage.getObject())[17]
//
//						);

				
				this.getControladorCobranca().gerarAtividadeAcaoCobranca(
						(CobrancaAcaoAtividadeCronograma) ((Object[]) objectMessage.getObject())[0],
						(CobrancaAcaoAtividadeComando) ((Object[]) objectMessage.getObject())[1],
						(Rota) ((Object[]) objectMessage.getObject())[2],
						(CobrancaAcao) ((Object[]) objectMessage.getObject())[3],
						(CobrancaAtividade) ((Object[]) objectMessage.getObject())[4],
						(Integer) ((Object[]) objectMessage.getObject())[5],
						(CobrancaCriterio) ((Object[]) objectMessage.getObject())[6],
						(Cliente) ((Object[]) objectMessage.getObject())[7],
						(ClienteRelacaoTipo) ((Object[]) objectMessage.getObject())[8],
						(String) ((Object[]) objectMessage.getObject())[9],
						(String) ((Object[]) objectMessage.getObject())[10],
						(Date) ((Object[]) objectMessage.getObject())[11],
						(Date) ((Object[]) objectMessage.getObject())[12],
						(Date) ((Object[]) objectMessage.getObject())[13],
						(Integer) ((Object[]) objectMessage.getObject())[14],
						(Cliente) ((Object[]) objectMessage.getObject())[15],
						(Integer) ((Object[]) objectMessage.getObject())[16]

				);
				
				

				
				
				
				
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
	 * Author: Rafael Santos Data: 04/01/2006
	 * 
	 * Retorna o valor do Controlador de Cobranca
	 * 
	 * @return O valor de controladorCobrancaLocal
	 */
	private ControladorCobrancaLocal getControladorCobranca() {

		ControladorCobrancaLocalHome localHome = null;
		ControladorCobrancaLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorCobrancaLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);
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

	// this.enviarMensagemControladorBatch(
	// MetodosBatch.ENDERECO_INSERIR_CEP_IMPORTADOS,
	// ConstantesJNDI.QUEUE_CONTROLADOR_FATURAMENTO_MDB,
	// new Object[] { cepsImportados });

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 */
	public void ejbCreate() {

	}
}
