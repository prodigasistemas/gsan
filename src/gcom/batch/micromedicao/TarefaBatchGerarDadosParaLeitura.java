package gcom.batch.micromedicao;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.micromedicao.Rota;
import gcom.micromedicao.leitura.LeituraTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * Tarefa que manda para batch Consistir Leituras e Consumos
 * 
 * @author Rodrigo Silveira
 * @created 20/07/2006
 */
public class TarefaBatchGerarDadosParaLeitura extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarDadosParaLeitura(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarDadosParaLeitura() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Integer anoMesFaturamentoGrupo = (Integer) getParametro("anoMesFaturamentoGrupo");
		Integer idFaturamentoGrupo = (Integer) getParametro("idFaturamentoGrupo");
		SistemaParametro sistemaParametro = (SistemaParametro) getParametro("sistemaParametro");
		Collection colecaoRotasParaExecucao = 
			(Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		

		Iterator iterator = colecaoRotasParaExecucao.iterator();
		Collection<Rota> colecaoRotasColetorCompesa = new ArrayList();
		
		if (sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(SistemaParametro.EMPRESA_COMPESA)) {
			
			while (iterator.hasNext()) {

				Object[] array = (Object[]) iterator.next();

				Rota rota = (Rota) array[1];

				if (rota.getLeituraTipo() != null && 
					!rota.getLeituraTipo().getId().equals(LeituraTipo.MICROCOLETOR)) {

					enviarMensagemControladorBatch(
						ConstantesJNDI.BATCH_GERAR_DADOS_PARA_LEITURA_MDB,
						new Object[] { 
							Collections.singletonList(rota),
							anoMesFaturamentoGrupo, 
							idFaturamentoGrupo, 
							sistemaParametro,
							this.getIdFuncionalidadeIniciada() });
				} else {
					colecaoRotasColetorCompesa.add(rota);
				}
			}

			enviarMensagemControladorBatch(
				ConstantesJNDI.BATCH_GERAR_DADOS_PARA_LEITURA_MDB,
				new Object[] { 
					colecaoRotasColetorCompesa,
					anoMesFaturamentoGrupo, 
					idFaturamentoGrupo, 
					sistemaParametro,
					this.getIdFuncionalidadeIniciada() });

		} else {

			while (iterator.hasNext()) {

				Object[] array = (Object[]) iterator.next();

				Rota rota = (Rota) array[1];

				enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_GERAR_DADOS_PARA_LEITURA_MDB,
					new Object[] { 
						Collections.singletonList(rota),
						anoMesFaturamentoGrupo, 
						idFaturamentoGrupo, 
						sistemaParametro,
						this.getIdFuncionalidadeIniciada() });
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
		AgendadorTarefas.agendarTarefa("GerarDadosParaLeituraBatch", this);
	}

}
