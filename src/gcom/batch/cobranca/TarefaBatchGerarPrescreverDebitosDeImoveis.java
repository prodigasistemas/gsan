package gcom.batch.cobranca;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;


/**
 * Descrição da classe 
 *
 * @author Hugo Leonardo
 * @date 07/07/2010
 */
public class TarefaBatchGerarPrescreverDebitosDeImoveis extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarPrescreverDebitosDeImoveis(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarPrescreverDebitosDeImoveis() {
		super(null, 0);
	}

    public Object executar() throws TarefaException {
    	
    	Collection<Integer> colecaoCobrancaSituacao = (Collection<Integer>) 
			getParametro("colecaoCobrancaSituacao"); 
	
    	Iterator iterator = colecaoCobrancaSituacao.iterator();
	
    	String idsCobrancaSituacao = "";
	
    	Integer idCobranca = null;
	
		while(iterator.hasNext()){
			idCobranca = (Integer) iterator.next();
			idsCobrancaSituacao = idsCobrancaSituacao + idCobranca + ",";
		}
		
		idsCobrancaSituacao = Util.removerUltimosCaracteres(idsCobrancaSituacao, 1);
		
		System.out.println("********Iniciando Processo de Prescrição de Débitos***********");
		
	    Integer anoMesFaturamento = (Integer) getParametro("anoMesFaturamento");
	    
	    /**
	     * Alterações para atender ao Mantis 490
	     * Considerar a data de vencimento da conta ao invés de considerar o anoMes referencia
	     * da mesma
	     * @author Wellington Rocha
	     * @date 25/01/2012*/
	    Date dataPrescricao = new Date();
	    
	    int year = dataPrescricao.getYear();
	    
	    year = year - 10;
	    
	    dataPrescricao.setYear(year);
	    
	    //Integer anoMesPrescricao = Util.subtrairAnoAnoMesReferencia(anoMesFaturamento, 10);
	    
	    Integer usuarioLogado = Usuario.USUARIO_BATCH.getId();
    	
    	enviarMensagemControladorBatch(
	        		ConstantesJNDI.BATCH_GERAR_PRESCREVER_DEBITOS_DE_IMOVEIS_MDB,
	        		new Object[] { this.getIdFuncionalidadeIniciada(), 
	        				anoMesFaturamento, dataPrescricao, usuarioLogado, idsCobrancaSituacao});
        
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
		AgendadorTarefas.agendarTarefa("GerarPrescreverDebitosDeImoveisBatch",
				this);
	}

}
