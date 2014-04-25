package gcom.util;

import gcom.batch.ControladorBatchLocal;
import gcom.batch.ControladorBatchLocalHome;

import javax.ejb.CreateException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Esta classe representa o componente que registra as queries demoradas no sistema
 * 
 * @author Rodrigo Silveira
 * @date 12/02/2008
 */
public class MonitorQueriesDemoradasSistema implements Job {

	public static void main(String[] args) {

	}

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			System.out.print("MonitorQueriesDemoradas");
			getControladorBatch().verificadorQueriesDemoradasSistema();
			
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new JobExecutionException();
		}

	}

	private ControladorBatchLocal getControladorBatch() {
		ControladorBatchLocalHome localHome = null;
		ControladorBatchLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorBatchLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_BATCH_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

	
}
