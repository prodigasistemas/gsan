package gcom.batch;

import java.util.Collection;
import java.util.Iterator;

import gcom.fachada.Fachada;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;

import javax.ejb.CreateException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Esta classe representa o componente que verifica no sistema a presenca de
 * ProcessosIniciados nao agendados para iniciar a execucao
 * 
 * @author Rodrigo Silveira
 * @date 21/08/2006
 */
public class VerificadorProcessosIniciados implements Job {

	public static void main(String[] args) {

	}
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		try {
			
			Collection colecao = Fachada.getInstancia().retornaProcessoFuncionalidadeEmExecucao();
			
			String descricaoProcessoFuncionalidade = "";
			
			if ( colecao != null && !colecao.isEmpty() ) {
				Iterator iteratorColecao = colecao.iterator();
				while ( iteratorColecao.hasNext() ) {
					
					Object[] object = (Object[]) iteratorColecao.next();
					
					if ( object[0] != null ) {
						descricaoProcessoFuncionalidade = "Processo: " + (String) object[0] + ";";
					
						if ( object[1] != null ) {
							descricaoProcessoFuncionalidade = descricaoProcessoFuncionalidade +" Funcionalidade: "+ (String) object[1] +";";
							System.out.print(descricaoProcessoFuncionalidade + 
									" Percentual de memória usada: " + 
									Util.retornaPercentualUsadoDeMemoriaJVM()+"%");
						} else {
							System.out.print(descricaoProcessoFuncionalidade + 
									" Percentual de memória usada: " + 
									Util.retornaPercentualUsadoDeMemoriaJVM()+"%");
						}
					}
				
				}
			}
			
			System.out.print("Verificador");
			getControladorBatch().verificadorProcessosSistema();
			
			//Estah aqui soh para testes -- colocar como uma vez por dia
			getControladorBatch().deletarRelatoriosBatchDataExpiracao();
			
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
