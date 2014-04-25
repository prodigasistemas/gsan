package gcom.batch.atendimentopublico;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class TarefaBatchGerarDadosArquivoAcompanhamentoServico extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarDadosArquivoAcompanhamentoServico(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarDadosArquivoAcompanhamentoServico() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Collection colecaoUnidadesNegocio = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		Date dataRoteiro = (Date) getParametro("dataAtual");		

		Iterator iterator = colecaoUnidadesNegocio.iterator();
        
        while (iterator.hasNext()) {
        	Integer idUnidadeNegocio = (Integer) iterator.next();
            enviarMensagemControladorBatch(
                    ConstantesJNDI.BATCH_GERAR_DADOS_ARQUIVO_ACOMPANHAMENTO_SERVICO_MDB,
                    new Object[] { this.getIdFuncionalidadeIniciada(),idUnidadeNegocio, dataRoteiro });
            	
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
		AgendadorTarefas.agendarTarefa("BatchGerarDadosArquivoAcompanhamentoServico",
				this);
	}

}
