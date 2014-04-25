package gcom.batch.micromedicao;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
import gcom.micromedicao.Rota;
import gcom.micromedicao.leitura.LeituraTipo;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class BatchGerarDadosParaLeituraMDB implements MessageDrivenBean,
		MessageListener {
	
	private static final long serialVersionUID = 1L;

	public BatchGerarDadosParaLeituraMDB() {
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
				
				
				Collection colecaoRotas = (Collection) ((Object[]) objectMessage.getObject()) [0];
				
				Integer anoMesCorrente = (Integer) ((Object[]) objectMessage.getObject())[1];
				Integer idGrupoFaturamento = (Integer) ((Object[]) objectMessage.getObject())[2];
				SistemaParametro sistemaParametro = (SistemaParametro) ((Object[]) objectMessage.getObject())[3];
				Integer idFuncionalidadeIniciada = (Integer) ((Object[]) objectMessage.getObject())[4];
				
				Iterator iterator = colecaoRotas.iterator();
				
				//Collection colecaoMicroColetor = new ArrayList();
				//Collection colecaoPreFaturamento = new ArrayList();
				
				while(iterator.hasNext()) {
				
					Rota rota = (Rota)iterator.next();
					
					if(rota.getLeituraTipo().getId().intValue() == LeituraTipo.CONVENCIONAL.intValue()){
						
						this.getControladorMicromedicao().gerarDadosPorLeituraParaInserir(
							rota ,
							anoMesCorrente, 
							idGrupoFaturamento, 
							sistemaParametro, 
							idFuncionalidadeIniciada);
						
					}else if (rota.getLeituraTipo().getId().intValue() == LeituraTipo.CELULAR_MOBILE.intValue()){
						
						this.getControladorMicromedicao().gerarDadosPorLeituraParaInserir(
							rota ,
							anoMesCorrente, 
							idGrupoFaturamento, 
							sistemaParametro, 
							idFuncionalidadeIniciada);
					
					}else if (rota.getLeituraTipo().getId().intValue() == LeituraTipo.LEITURA_E_ENTRADA_SIMULTANEA.intValue()){
						this.getControladorFaturamento().preFaturarGrupoFaturamento(rota,
								anoMesCorrente,
								idGrupoFaturamento,idFuncionalidadeIniciada);
					
					}/*else if ((sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(SistemaParametro.EMPRESA_COMPESA))
							&& (rota.getLeituraTipo().getId().intValue() == LeituraTipo.MICROCOLETOR.intValue())){
						
						colecaoMicroColetor.add(rota);
						
					}*/else if((!sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(SistemaParametro.EMPRESA_COMPESA))
							&& rota.getLeituraTipo().getId().intValue() == LeituraTipo.MICROCOLETOR.intValue()){
						
						this.getControladorMicromedicao().gerarDadosPorLeituraParaInserir(rota
						,anoMesCorrente, idGrupoFaturamento, sistemaParametro, idFuncionalidadeIniciada);
					}
					
					rota = null;
					
				}
				
				/*if (!colecaoMicroColetor.isEmpty()){
					this.getControladorMicromedicao().gerarDadosPorLeituraMicroColetor(colecaoRotas,anoMesCorrente,
							idGrupoFaturamento,sistemaParametro,idFuncionalidadeIniciada);
				}*/



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
	 * Retorna o valor de controladorLocalidade
	 * 
	 * @return O valor de controladorLocalidade
	 */
	private ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
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
