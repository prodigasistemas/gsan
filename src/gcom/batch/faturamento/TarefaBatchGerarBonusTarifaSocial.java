package gcom.batch.faturamento;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;


/**
 * UC0926 - Gerar Bônus de Tarifa Social
 * 
 * @author Hugo Amorim
 * @created 25/08/2009
 */
public class TarefaBatchGerarBonusTarifaSocial extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;
	
	public TarefaBatchGerarBonusTarifaSocial
		(Usuario usuario,int idFuncionalidadeIniciada) {
		
		super(usuario, idFuncionalidadeIniciada);
		
	}
	
	@Deprecated
	public TarefaBatchGerarBonusTarifaSocial() {
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {


		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) getParametro("faturamentoGrupo");
		SistemaParametro sistemaParametro = (SistemaParametro) getParametro("sistemaParametros");
		Collection colecaoRotasParaExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); // (Collection<Rota>)

		Iterator iterator = colecaoRotasParaExecucao.iterator();

		while (iterator.hasNext()) {

			Object[] array = (Object[]) iterator.next();

			System.out
					.println("Rota Gerar Bonus Tarifa Social "
							+ ((Rota) array[1]).getId()
							+ " *********************************************************");


			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_GERAR_BONUS_TARIFA_SOCIAL,
					new Object[]{faturamentoGrupo, sistemaParametro,
							Collections.singletonList((Rota) array[1]),
							this.getIdFuncionalidadeIniciada()});

		}

		return null;
	}
	

	
	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoBatch() {
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) getParametro("faturamentoGrupo");
		FiltroRota filtroRota = new FiltroRota();
		filtroRota.adicionarParametro(new ParametroSimples(
				FiltroRota.FATURAMENTO_GRUPO_ID, faturamentoGrupo.getId()));

		return Fachada.getInstancia().pesquisar(filtroRota,
				Rota.class.getName());
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa(
				"GerarBonusTarifaSocialBatch", this);

	}

}
