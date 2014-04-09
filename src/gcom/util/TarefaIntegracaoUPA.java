package gcom.util;

import gcom.integracao.ControladorIntegracaoLocal;
import gcom.integracao.ControladorIntegracaoLocalHome;

import javax.ejb.CreateException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Esta classe representa o componente que faz a integração com o UPA
 * 
 * @author Rodrigo Silveira
 * @date 27/02/2008
 */
public class TarefaIntegracaoUPA implements Job {

	public static void main(String[] args) {

	}

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
			System.out.print("Integracao UPA");
			getControladorIntegracao().receberMovimentoExportacaoFirma();
	}

	private ControladorIntegracaoLocal getControladorIntegracao() {
		ControladorIntegracaoLocalHome localHome = null;
		ControladorIntegracaoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorIntegracaoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_INTEGRACAO_SEJB);
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

}
