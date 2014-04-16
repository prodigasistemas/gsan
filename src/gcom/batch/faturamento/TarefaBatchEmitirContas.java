package gcom.batch.faturamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * Tarefa que manda para batch Emitir Contas
 * 
 * @author Rodrigo Silveira
 * @created 05/12/2006
 */
public class TarefaBatchEmitirContas extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchEmitirContas(Usuario usuario, int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchEmitirContas() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) getParametro("faturamentoGrupo");
		Integer anoMesFaturamentoGrupo = (Integer) getParametro("anoMesFaturamentoGrupo");
		Collection colecaoIdsEmpresas = 
			(Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();

		// Manda rodar cada tipo de conta numa thread diferente
		if (!sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase("CAERN") && 
			!sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase("CAER") && 
			!sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase("CAEMA") &&
			!sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase("COSANPA")) {

			if (faturamentoGrupo != null) {

				for (int tipoConta = 0; tipoConta < 6; tipoConta++) {

					if (tipoConta == 3 || tipoConta == 4) {
						
						enviarMensagemControladorBatch(
								ConstantesJNDI.BATCH_EMITIR_CONTAS_MDB,
								new Object[] { anoMesFaturamentoGrupo,
										faturamentoGrupo,
										this.getIdFuncionalidadeIniciada(),
										tipoConta, 
										null, 
										ConstantesSistema.NAO }
								);

					} else {
						
						Iterator iteratorEmpresas = colecaoIdsEmpresas.iterator();
						
						while (iteratorEmpresas.hasNext()) {
							
							Integer idEmpresa = (Integer) iteratorEmpresas.next();

							enviarMensagemControladorBatch(
									ConstantesJNDI.BATCH_EMITIR_CONTAS_MDB,
									new Object[] { anoMesFaturamentoGrupo,
											faturamentoGrupo,
											this.getIdFuncionalidadeIniciada(),
											tipoConta, 
											idEmpresa,
											ConstantesSistema.NAO 
											}
									);
						}

					}
				}

			} else {
				for (int tipoConta = 3; tipoConta < 5; tipoConta++) {

					enviarMensagemControladorBatch(
							ConstantesJNDI.BATCH_EMITIR_CONTAS_MDB,
							new Object[] { anoMesFaturamentoGrupo,
									faturamentoGrupo,
									this.getIdFuncionalidadeIniciada(),
									tipoConta, 
									null, 
									ConstantesSistema.SIM });
				}
			}
		} else if ((sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase("CAERN")) ||
				(sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase("CAEMA")) ||
				(sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase("COSANPA"))){
			
			//QUANDO A EMPRESA FOR CAERN
			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_EMITIR_CONTAS_MDB, new Object[] {
							anoMesFaturamentoGrupo, faturamentoGrupo,
							this.getIdFuncionalidadeIniciada(), 
							5, 
							1, 
							ConstantesSistema.SIM });
		//CAER
		}else{
			
			if (faturamentoGrupo != null) {

				for (int tipoConta = 0; tipoConta < 2; tipoConta++) {

					if (tipoConta == 2) {
						
						enviarMensagemControladorBatch(
								ConstantesJNDI.BATCH_EMITIR_CONTAS_MDB,
								new Object[] { anoMesFaturamentoGrupo,
										faturamentoGrupo,
										this.getIdFuncionalidadeIniciada(),
										tipoConta, 
										null, 
										ConstantesSistema.NAO }
								);

					} else {
						
						enviarMensagemControladorBatch(
							ConstantesJNDI.BATCH_EMITIR_CONTAS_MDB,
								new Object[] { anoMesFaturamentoGrupo,
									faturamentoGrupo,
									this.getIdFuncionalidadeIniciada(),
									tipoConta, 
									1,
									ConstantesSistema.NAO }
						);
					}
				}

			} else {

				enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_EMITIR_CONTAS_MDB,
						new Object[] { anoMesFaturamentoGrupo,
							faturamentoGrupo,
							this.getIdFuncionalidadeIniciada(),
							2, 
							null, 
							ConstantesSistema.SIM });
			}			
			
		}

		return null;
	}

	@Override
	public Collection pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@Override
	public Collection pesquisarTodasUnidadeProcessamentoReinicioBatch() {

		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("EmitirContasBatch", this);
	}

}
