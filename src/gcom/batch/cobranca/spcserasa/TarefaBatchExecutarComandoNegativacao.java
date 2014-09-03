package gcom.batch.cobranca.spcserasa;

import gcom.cobranca.NegativacaoComando;
import gcom.cobranca.NegativacaoCriterio;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorContrato;
import gcom.cobranca.NegativadorMovimento;
import gcom.cobranca.NegativadorMovimentoReg;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.ControladorSpcSerasaLocal;
import gcom.spcserasa.ControladorSpcSerasaLocalHome;
import gcom.spcserasa.FiltroNegativadorMovimento;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.RepositorioUtilHBM;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.CreateException;

/**
 * Tarefa que manda para Executar Comando Negativacao
 */
public class TarefaBatchExecutarComandoNegativacao extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	public TarefaBatchExecutarComandoNegativacao(Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchExecutarComandoNegativacao() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {
		Collection rotas = null;
		if (getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH) != null) {
			rotas = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		}

		NegativacaoCriterio criterio = (NegativacaoCriterio) getParametro("nCriterio");
		Negativador negativador = (Negativador) getParametro("neg");
		NegativacaoComando comando = (NegativacaoComando) getParametro("nComando");
		NegativadorContrato negativadorContrato = (NegativadorContrato) getParametro("nContrato");
		NegativadorMovimento negativadorMovimento = null;
		Integer idNegativacaoMovimento = null;
		NegativadorMovimentoReg.resetNumeroProximoRegistro();
		NegativacaoComando.resetQuantidadeImoveisJaIncluidos();

		try {
			if (comando.getIndicadorSimulacao() == 2) {
				// Inserindo o HEADER
				FiltroNegativadorMovimento fnm = new FiltroNegativadorMovimento();
				fnm.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimento.NEGATIVACAO_COMANDO, comando.getId()));
				Collection colecaoNegativadorMovimento = RepositorioUtilHBM.getInstancia().pesquisar(fnm, NegativadorMovimento.class.getName());

				if (colecaoNegativadorMovimento != null && !colecaoNegativadorMovimento.isEmpty()) {
					/*
					 * [UC0671] Gerar Movimento de Inclusão de Negativação
					 * [SB0014] Desfazer Atualização da Execução Descontinuada
					 */
					getControladorSpcSerasa().desfazerAtualizacaoExecucaoDescontinuada(this.getIdFuncionalidadeIniciada(), comando);
				}

				int numeroSequencialEnvio = negativadorContrato.getNumeroSequencialEnvio() + 1;
				Date dataAtual = new Date();

				negativadorMovimento = new NegativadorMovimento();
				negativadorMovimento.setNegativador(negativador);
				negativadorMovimento.setNegativacaoComando(comando);
				negativadorMovimento.setNumeroSequencialEnvio(numeroSequencialEnvio);
				negativadorMovimento.setCodigoMovimento(NegativadorMovimento.CODIGO_MOVIMENTO_INCLUSAO);
				negativadorMovimento.setDataEnvio(dataAtual);
				negativadorMovimento.setDataProcessamentoEnvio(dataAtual);
				negativadorMovimento.setUltimaAlteracao(dataAtual);

				idNegativacaoMovimento = (Integer) getControladorUtil().inserir(negativadorMovimento);
				negativadorMovimento.setId(idNegativacaoMovimento);

				getControladorSpcSerasa().gerarRegistroDeInclusaoTipoHeader(ConstantesSistema.TIPO_COMANDO_POR_CRITERIO,
						NegativadorMovimentoReg.getNumeroProximoRegistro(), negativador, negativadorContrato, comando,
						criterio, idNegativacaoMovimento);
			} else {
				negativadorMovimento = new NegativadorMovimento();
			}

			if (rotas != null) {
				Iterator iterator = rotas.iterator();
				while (iterator.hasNext()) {
					Integer id = (Integer) iterator.next();

					enviarMensagemControladorBatch(ConstantesJNDI.BATCH_EXECUTAR_COMANDO_NEGATIVACAO_MDB, 
							new Object[] { id, criterio, negativador, comando, negativadorContrato,
							negativadorMovimento, this.getIdFuncionalidadeIniciada() });
				}
			} else {
				enviarMensagemControladorBatch(ConstantesJNDI.BATCH_EXECUTAR_COMANDO_NEGATIVACAO_MDB,
						new Object[] { 0, criterio, negativador, comando, negativadorContrato,
						negativadorMovimento, this.getIdFuncionalidadeIniciada() });
			}
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new TarefaException(e.getMessage());
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException(e.getMessage());
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
		AgendadorTarefas.agendarTarefa("BatchExecutarComandoNegativacao", this);
	}

	private ControladorUtilLocal getControladorUtil() {
		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorUtilLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			local = localHome.create();
			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	private ControladorSpcSerasaLocal getControladorSpcSerasa() {
		ControladorSpcSerasaLocalHome localHome = null;
		ControladorSpcSerasaLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorSpcSerasaLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_SPC_SERASA_SEJB);
			local = localHome.create();
			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
}
