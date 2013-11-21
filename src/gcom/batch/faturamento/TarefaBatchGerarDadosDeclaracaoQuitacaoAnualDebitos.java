package gcom.batch.faturamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.micromedicao.Rota;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * [UC1001] Emitir declaração de quitação anual de débitos
 * 
 * 	Este caso de uso permite a geração de declaração de quitação de débitos.
 * 
 * @author Hugo Amorim
 * @date 17/03/2010
 */
public class TarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos extends
		TarefaBatch {

	private static final long serialVersionUID = 1L;
	
	public TarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos(Usuario usuario,
			int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos() {
		super(null, 0);
	}


	@Override
	public Object executar() throws TarefaException {
		
		
		SistemaParametro sistemaParametro = (SistemaParametro) getParametro("SistemaParametros");
		
		if(sistemaParametro.getNumeroMesesAnterioresParaDeclaracaoQuitacao() == null
				|| sistemaParametro.getNumeroAnoQuitacao() == null){
			throw new TarefaException("atencao.sistema_nao_parametrizado");
		}
		
		
		//Quantidade de anos para gerar a declaração 
		//(realizar a diferença entre o ano de SISTEMA_PARAMETROS.PARM_AMREFERENCIAARRECADACAO 
		//e SISTEMA_PARAMETROS.PARM_NNANOQUITACAO, o valor obtido deverá ser utilizado 
		//para considerar os anos a serem geradas as declarações até o ano de  
		//SISTEMA_PARAMETROS.PARM_AMREFERENCIAARRECADACAO -1);
		Integer numeroAnoQuitacao = sistemaParametro.getNumeroAnoQuitacao();
		Integer anoMesArrecadacao = sistemaParametro.getAnoMesArrecadacao();
		
		Integer anoArrecadacao = Util.obterAno(anoMesArrecadacao);
		Integer anoArrecadacaoMenosUm = Util.obterAno(anoMesArrecadacao)-1;
		
		Integer anoInicialParaGerarDeclaracao = anoArrecadacao - numeroAnoQuitacao;
		
		Collection<Integer> anosParaGeracaoDeclaracao = new ArrayList<Integer>();
		
		while(anoInicialParaGerarDeclaracao<=anoArrecadacaoMenosUm){
			anosParaGeracaoDeclaracao.add(anoInicialParaGerarDeclaracao);
			anoInicialParaGerarDeclaracao++;
		}
		
		//	Verificar o indicador de conta parcelada (SISTEMA_PARAMETROS.PARM_ICCONTAPARCELADA) 
		//	Caso este esteja como 1, as contas parceladas deverão ser verificadas;
		//	Caso contrário, não deverão ser consideradas na geração da declaração.
		//	Verificar o indicador de conta em cobrança judicial (SISTEMA_PARAMETROS.PARM_ICCOBRANCAJUDICIAL) 
		//	Caso este esteja como 1, as contas em cobrança judicial deverão ser verificadas; 
		//	Caso contrário, não deverão ser consideradas na geração da declaração.
		Short indicadorContaParcelada = ConstantesSistema.NAO;			
		if(sistemaParametro.getIndicadorContaParcelada()!=null
				&& sistemaParametro.getIndicadorContaParcelada()
					.compareTo(ConstantesSistema.SIM)==0){
			indicadorContaParcelada = ConstantesSistema.SIM;
		}
		
		Short indicadorCobrancaJudical = ConstantesSistema.NAO;
		if(sistemaParametro.getIndicadorCobrancaJudical()!=null
				&& sistemaParametro.getIndicadorCobrancaJudical()
					.compareTo(ConstantesSistema.SIM)==0){
			indicadorCobrancaJudical = ConstantesSistema.SIM;
		}
		
		Integer numeroMesesAnterioresParaDeclaracaoQuitacao = sistemaParametro.getNumeroMesesAnterioresParaDeclaracaoQuitacao();
		
		Integer anoMesSubtraido = Util.subtrairMesDoAnoMes(anoMesArrecadacao, numeroMesesAnterioresParaDeclaracaoQuitacao);
		
		Date dataVerificacaoPagamentos = Util.gerarDataApartirAnoMesRefencia(anoMesSubtraido);
		
						
			Collection colecaoRotasParaExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

			Iterator iterator = colecaoRotasParaExecucao.iterator();

			while (iterator.hasNext()) {
				
				Rota rota = (Rota) iterator.next();

				System.out.println("ROTA GERAR DADOS QUITAÇÃO ANUAL DE DÉBITOS "
								+ rota.getId()
								+ "*********************************************************");
				

					enviarMensagemControladorBatch(
			                ConstantesJNDI.BATCH_GERAR_DECLARACAO_QUITACAO_ANUAL_DEBITOS,
			                new Object[] { 
			                		this.getIdFuncionalidadeIniciada(),
			                		anosParaGeracaoDeclaracao,
			                		rota,
			                		indicadorContaParcelada,
			                		indicadorCobrancaJudical,
			                		dataVerificacaoPagamentos,
			                		});			
			}	
		
		return null;
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa(
				"BatchGerarDadosDeclaracaoQuitacaoAnualDebitos", this);

	}

}
