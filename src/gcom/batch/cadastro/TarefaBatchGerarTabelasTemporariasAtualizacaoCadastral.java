package gcom.batch.cadastro;

import gcom.cadastro.imovel.bean.ImovelGeracaoTabelasTemporariasCadastroHelper;
import gcom.fachada.Fachada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * @author: Ana Maria
 * @date: 22/06/2009
 */
public class TarefaBatchGerarTabelasTemporariasAtualizacaoCadastral extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarTabelasTemporariasAtualizacaoCadastral(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarTabelasTemporariasAtualizacaoCadastral() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Map parametros = (Map) getParametro(ConstantesSistema.PARAMETROS_BATCH);
		ImovelGeracaoTabelasTemporariasCadastroHelper helper = (ImovelGeracaoTabelasTemporariasCadastroHelper)
				parametros.get("imovelGeracaoTabelasTemporariasCadastroHelper");
		
		Collection<Integer> colecaoIdRotas = Fachada.getInstancia().pesquisarRotasAtualizacaoCadastral(helper);
		
		for (Integer idRota : colecaoIdRotas) {
			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_GERAR_TABELAS_TEMPORARIAS_ATUALIZACAO_CADASTRAL_MDB,
					new Object[]{ idRota, this.getIdFuncionalidadeIniciada(), helper });
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
		AgendadorTarefas.agendarTarefa("GerarTabelasTemporariasAtualizacaoCadastralBatch", this);
	}

}
