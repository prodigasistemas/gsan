package gcom.batch.micromedicao;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.FaturamentoGrupo;
import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
import gcom.micromedicao.Rota;
import gcom.micromedicao.leitura.LeituraTipo;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

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
 * @author Pedro Alexandre
 * @date 13/09/2007
 */
public class BatchGerarArquivoTextoParaLeituristaMDB implements MessageDrivenBean, MessageListener {
	
	private static final long serialVersionUID = 1L;

	public BatchGerarArquivoTextoParaLeituristaMDB() {
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
				
				Collection colecaoRoteirosEmpresa = (Collection) ((Object[]) objectMessage.getObject())[0];
				Collection colecaoRotas = (Collection) ((Object[]) objectMessage.getObject())[1];
				
				FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) ((Object[]) objectMessage.getObject())[3];
				Integer anoMesFaturamento = faturamentoGrupo.getAnoMesReferencia();
				SistemaParametro sistemaParametro = (SistemaParametro) ((Object[]) objectMessage.getObject())[4];
				Date dataComando = (Date) ((Object[]) objectMessage.getObject())[5];
				Integer idFuncionalidadeIniciada = (Integer) ((Object[]) objectMessage.getObject())[6];
				
				Iterator iterator = colecaoRotas.iterator();
				
				Collection<Rota> colecaoLeiturista = new ArrayList();
				Collection<Rota> colecaoConvencional = new ArrayList();
				Collection<Rota> colecaoMicroColetor = new ArrayList();
				
				while(iterator.hasNext()) {
					
					Rota rota = (Rota)iterator.next();
					
					
					if(rota.getLeituraTipo().getId().intValue() == LeituraTipo.CONVENCIONAL.intValue()){
						
						colecaoConvencional.add(rota);
					} 
					else if (rota.getLeituraTipo().getId().intValue() == LeituraTipo.CELULAR_MOBILE.intValue()){
						
						colecaoLeiturista.add(rota);
					} 
					else if (rota.getLeituraTipo().getId().intValue() == LeituraTipo.LEITURA_E_ENTRADA_SIMULTANEA.intValue()){
						
						this.getControladorFaturamento().gerarArquivoTextoParaFaturamento(
							rota, anoMesFaturamento, faturamentoGrupo,dataComando,
							idFuncionalidadeIniciada);
					} 
					else {
						
						colecaoMicroColetor.add(rota);					
					}
					rota = null;
				}
				
				//CELULAR MOBILE - LAYOUT ÚNICO PARA TODAS AS EMPRESAS
				if (!colecaoLeiturista.isEmpty()){
					this.getControladorMicromedicao().gerarArquivoTextoParaLeiturista(
						colecaoRoteirosEmpresa,
						colecaoLeiturista, 
						anoMesFaturamento,
						faturamentoGrupo,
						idFuncionalidadeIniciada.intValue());
				}
				
				//CONVENCIONAL - LAYOUT POR EMPRESA
				if (!colecaoConvencional.isEmpty()){
					this.getControladorMicromedicao().gerarDadosPorLeituraConvencional(
						colecaoConvencional,
						anoMesFaturamento,
						faturamentoGrupo.getId(),
						sistemaParametro,
						idFuncionalidadeIniciada);
				}
				
				//MICROCOLETOR - LAYOUT POR EMPRESA
				if(!colecaoMicroColetor.isEmpty()){
					this.getControladorMicromedicao().gerarDadosPorLeituraMicroColetor(
						colecaoMicroColetor,
						anoMesFaturamento,
						faturamentoGrupo.getId(),
						sistemaParametro,
						idFuncionalidadeIniciada);
				}
				
			
			} catch (JMSException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			} catch (ControladorException e) {
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
