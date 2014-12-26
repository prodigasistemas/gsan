package gcom.batch;

import gcom.arrecadacao.ControladorArrecadacaoLocal;
import gcom.arrecadacao.ControladorArrecadacaoLocalHome;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.ComandoEmpresaCobrancaContaExtensao;
import gcom.cobranca.NegativacaoComando;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorContrato;
import gcom.cobranca.NegativadorMovimento;
import gcom.cobranca.NegativadorMovimentoReg;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FaturamentoGrupo;
import gcom.financeiro.ControladorFinanceiroLocal;
import gcom.financeiro.ControladorFinanceiroLocalHome;
import gcom.financeiro.ParametrosDevedoresDuvidosos;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.spcserasa.ControladorSpcSerasaLocal;
import gcom.spcserasa.ControladorSpcSerasaLocalHome;
import gcom.spcserasa.FiltroNegativadorMovimento;
import gcom.tarefa.TarefaBatch;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.IoUtil;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.ejb.CreateException;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.jboss.logging.Logger;

public class FuncionalidadeIniciada implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Logger logger = Logger.getLogger(FuncionalidadeIniciada.class);

	private Integer id;

	private Date dataHoraInicio;

	private Date dataHoraTermino;

	private FuncionalidadeSituacao funcionalidadeSituacao;

	private ProcessoIniciado processoIniciado;

	private ProcessoFuncionalidade processoFuncionalidade;

	private Set unidadesIniciadas;

	private Set relatoriosGerados;

	private byte[] tarefaBatch;

	private String descricaoExcecao;

	public String getDescricaoExcecao() {
		return descricaoExcecao;
	}

	public void setDescricaoExcecao(String descricaoExcecao) {
		this.descricaoExcecao = descricaoExcecao;
	}

	/** full constructor */
	public FuncionalidadeIniciada(Date dataHoraInicio, Date dataHoraTermino, gcom.batch.FuncionalidadeSituacao funcionalidadeSituacao,
			gcom.batch.ProcessoIniciado processoIniciado, gcom.batch.ProcessoFuncionalidade processoFuncionalidade, Set unidadesIniciadas, Set relatoriosGerados) {
		this.dataHoraInicio = dataHoraInicio;
		this.dataHoraTermino = dataHoraTermino;
		this.funcionalidadeSituacao = funcionalidadeSituacao;
		this.processoIniciado = processoIniciado;
		this.processoFuncionalidade = processoFuncionalidade;
		this.unidadesIniciadas = unidadesIniciadas;
		this.relatoriosGerados = relatoriosGerados;
	}

	/** default constructor */
	public FuncionalidadeIniciada() {
	}

	/** minimal constructor */
	public FuncionalidadeIniciada(gcom.batch.FuncionalidadeSituacao funcionalidadeSituacao, gcom.batch.ProcessoIniciado processoIniciado,
			gcom.batch.ProcessoFuncionalidade processoFuncionalidade, Set unidadesIniciadas, Set relatoriosGerados) {
		this.funcionalidadeSituacao = funcionalidadeSituacao;
		this.processoIniciado = processoIniciado;
		this.processoFuncionalidade = processoFuncionalidade;
		this.unidadesIniciadas = unidadesIniciadas;
		this.relatoriosGerados = relatoriosGerados;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataHoraInicio() {
		return this.dataHoraInicio;
	}

	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public Date getDataHoraTermino() {
		return this.dataHoraTermino;
	}

	public void setDataHoraTermino(Date dataHoraTermino) {
		this.dataHoraTermino = dataHoraTermino;
	}

	public gcom.batch.FuncionalidadeSituacao getFuncionalidadeSituacao() {
		return this.funcionalidadeSituacao;
	}

	public void setFuncionalidadeSituacao(gcom.batch.FuncionalidadeSituacao funcionalidadeSituacao) {
		this.funcionalidadeSituacao = funcionalidadeSituacao;
	}

	public gcom.batch.ProcessoIniciado getProcessoIniciado() {
		return this.processoIniciado;
	}

	public void setProcessoIniciado(gcom.batch.ProcessoIniciado processoIniciado) {
		this.processoIniciado = processoIniciado;
	}

	public gcom.batch.ProcessoFuncionalidade getProcessoFuncionalidade() {
		return this.processoFuncionalidade;
	}

	public void setProcessoFuncionalidade(gcom.batch.ProcessoFuncionalidade processoFuncionalidade) {
		this.processoFuncionalidade = processoFuncionalidade;
	}

	public Set getUnidadesIniciadas() {
		return this.unidadesIniciadas;
	}

	public void setUnidadesIniciadas(Set unidadesIniciadas) {
		this.unidadesIniciadas = unidadesIniciadas;
	}

	public Set getRelatoriosGerados() {
		return this.relatoriosGerados;
	}

	public void setRelatoriosGerados(Set relatoriosGerados) {
		this.relatoriosGerados = relatoriosGerados;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public byte[] getTarefaBatch() {
		return tarefaBatch;
	}

	public void setTarefaBatch(byte[] tarefaBatch) {
		this.tarefaBatch = tarefaBatch;
	}

	public String getDiferencaInicioTermino() {
		String retorno = "";
		if (this.dataHoraTermino != null) {
			long diferencaEmMilisegundos = this.dataHoraTermino.getTime() - this.dataHoraInicio.getTime();

			long diferencaEmHoras = diferencaEmMilisegundos / (60 * 60 * 1000);

			long diferencaEmMinutos = (diferencaEmMilisegundos / (60 * 1000)) % 60;

			long diferencaEmSegundos = (diferencaEmMilisegundos / (1000)) % 60;

			if (diferencaEmHoras < 10) {
				retorno = "0" + diferencaEmHoras;
			} else {
				retorno = "" + diferencaEmHoras;
			}

			if (diferencaEmMinutos < 10) {
				retorno = retorno + ":0" + diferencaEmMinutos;
			} else {
				retorno = retorno + ":" + diferencaEmMinutos;
			}

			if (diferencaEmSegundos < 10) {
				retorno = retorno + ":0" + diferencaEmSegundos;
			} else {
				retorno = retorno + ":" + diferencaEmSegundos;
			}

		}
		return retorno;
	}

	public void finalizador() {
		logger.info("Entrou no finalizador do processo");

		try {
			SistemaParametro sistemaParametros = getControladorUtil().pesquisarParametrosDoSistema();

			TarefaBatch tarefaBatch = (TarefaBatch) IoUtil.transformarBytesParaObjeto(this.tarefaBatch);

			System.out.println("Entrou na Funcionalidade: " + this.processoFuncionalidade.getFuncionalidade().getId() + "-"
					+ this.processoFuncionalidade.getFuncionalidade().getDescricao());

			switch (this.processoFuncionalidade.getFuncionalidade().getId()) {

			case Funcionalidade.GERAR_DADOS_PARA_LEITURA:

				Integer anoMesFaturamentoGrupoLeitura = (Integer) tarefaBatch.getParametro("anoMesFaturamentoGrupo");

				Integer idFaturamentoGrupo = (Integer) tarefaBatch.getParametro("idFaturamentoGrupo");

				// atualiza a data e a hora da realização da atividade com a
				// data e
				// a hora correntes

				getControladorFaturamento().atualizarDataHoraRealizacaoAtividade(FaturamentoAtividade.GERAR_ARQUIVO_LEITURA, anoMesFaturamentoGrupoLeitura,
						idFaturamentoGrupo);

				break;

			case Funcionalidade.FATURAR_GRUPO_FATURAMENTO:

				FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) tarefaBatch.getParametro("faturamentoGrupo");
				Integer atividade = (Integer) tarefaBatch.getParametro("atividade");

				System.out.println("**********************ENTROU PARA ATUALIZAR ANOMES:  " + faturamentoGrupo.getAnoMesReferencia() + "  ********************");

				/*
				 * Caso a atividade que esteja sendo executada, corresponda a
				 * faturar grupo, atualiza o ano/mês de referência do
				 * faturamento para o mês seguinte.
				 */
				if (atividade == FaturamentoAtividade.FATURAR_GRUPO.intValue()) {
					getControladorFaturamento().atualizarAnoMesReferenciaFaturamentoGrupo(faturamentoGrupo, faturamentoGrupo.getAnoMesReferencia(), atividade);
				}

				break;

			case Funcionalidade.CONSISTIR_LEITURAS_E_CALCULAR_CONSUMOS:

				FaturamentoGrupo faturamentoGrupoConsistirLeituras = (FaturamentoGrupo) tarefaBatch.getParametro("faturamentoGrupo");

				System.out.println("**********************ENTROU PARA ATUALIZAR DATAHORA REALIZAÇÃO DA ATIVIDADE:  "
						+ faturamentoGrupoConsistirLeituras.getId().toString() + "********************");

				getControladorFaturamento().atualizarDataHoraRealizacaoAtividade(FaturamentoAtividade.CONSISTIR_LEITURAS_E_CALCULAR_CONSUMOS,
						faturamentoGrupoConsistirLeituras.getAnoMesReferencia(), faturamentoGrupoConsistirLeituras.getId());

				break;

			case Funcionalidade.GERAR_HISTORICO_PARA_ENCERRAR_FATURAMENTO_MES:

				/**
				 * Item 12 do batch de encerrar faturamento do mês.
				 */
				// recupera o ano/mês de referência do faturamento
				int anoMesFaturamentoSistemaParametro = sistemaParametros.getAnoMesFaturamento();

				getControladorFaturamento().atualizarAnoMesFaturamento(anoMesFaturamentoSistemaParametro);

				break;

			case Funcionalidade.GERAR_HISTORICO_PARA_ENCERRAR_ARRECADACAO_MES:

				/**
				 * Item 11 do batch de encerrar arrecadação do mês.
				 */
				// recupera o ano/mês de referência da arrecadação
				int anoMesArrecadacaoSistemaParametro = sistemaParametros.getAnoMesArrecadacao();

				getControladorArrecadacao().atualizarAnoMesArrecadacao(anoMesArrecadacaoSistemaParametro);

				break;

			case Funcionalidade.GERAR_RESUMO_DEVEDORES_DUVIDOSOS:

				Integer anoMesReferenciaContabil = (Integer) tarefaBatch.getParametro("anoMesReferenciaContabil");
				ParametrosDevedoresDuvidosos parametrosDevedoresDuvidosos = getControladorFinanceiro().pesquisarParametrosDevedoresDuvidosos(
						anoMesReferenciaContabil);
				parametrosDevedoresDuvidosos.setDataProcessamento(new Date());
				this.getControladorUtil().atualizar(parametrosDevedoresDuvidosos);

				System.out.println("ENTROU PARA ATUALIZAR ANOMES:  " + anoMesReferenciaContabil);

				break;

			case Funcionalidade.GERAR_DADOS_DIARIOS_ARRECADACAO:

				sistemaParametros.setDataHoraDadosDiariosArrecadacao(new Date());

				System.out.println("**********************ENTROU PARA ATUALIZAR DATAHORA DA GERAÇÃO DOS DADOS********************");

				getControladorUtil().atualizar(sistemaParametros);

				break;

			case Funcionalidade.INSERIR_RESUMO_ACOES_COBRANCA_CRONOGRAMA:

				sistemaParametros.setDataHoraResumoAcoesCobranca(new Date());

				System.out.println("**********************ENTROU PARA ATUALIZAR DATAHORA DO RESUMO DE AÇÕES DE COBRANÇA ********************");

				getControladorUtil().atualizar(sistemaParametros);

				break;

			case Funcionalidade.GERAR_RESUMO_DIARIO_NEGATIVACAO:

				// sistemaParametro =
				// this.getControladorUtil().pesquisarParametrosDoSistema();
				// Integer numeroExecucao =
				// sistemaParametro.getNumeroExecucaoResumoNegativacao() + 1;
				//
				// // [UC0688] Gerar Resumo Diário da Negativação.
				// //-------------------------------------------------------------------------------------------
				// // Alterado por : Yara Taciane - data : 08/07/2008
				// // Analista : Fátima Sampaio
				// //-------------------------------------------------------------------------------------------
				//
				// //O sistema atualiza o número da execução do resumo da
				// negativação na tabela SISTEMA_PARAMETROS mais um).
				// sistemaParametro.setNumeroExecucaoResumoNegativacao(numeroExecucao);
				// sistemaParametro.setUltimaAlteracao(new Date());
				// this.getControladorUtil().atualizarSistemaParametro(sistemaParametro);
				// System.out.println(" Fim --- > altualizado Sistema Parametro = "
				// + sistemaParametro.getNumeroExecucaoResumoNegativacao());
				// //-------------------------------------------------------------------------------------------
				//

				// System.out.println("**********************ENTROU  GERAR MOV. DE EXCLUSÃO NEGATIVADORES********************");
				// Collection coll =
				// getControladorSpcSerasa().consultarNegativadoresParaExclusaoMovimento();
				// Integer[] idNegat = new Integer[coll.size()] ;
				// Iterator it = coll.iterator();
				// int i = 0;
				// while(it.hasNext()){
				// Negativador negativador = (Negativador)it.next();
				// idNegat[i]= negativador.getId();
				// i++;
				// }
				// Collection collRetorno =
				// getControladorSpcSerasa().gerarMovimentoExclusaoNegativacao(idNegat);
				// if(collRetorno != null){
				// System.out.println("---> retornou da Exclusão <----");
				// }

				System.out.println(" Fim do Resumo Diário! ");

				break;

			case Funcionalidade.EXECUTAR_COMANDO_DE_NEGATIVACAO:
				NegativacaoComando comando = (NegativacaoComando) tarefaBatch.getParametro("nComando");
				Negativador negativador = (Negativador) tarefaBatch.getParametro("neg");

				Object[] dadosInclusaoNegativacao = null;
				Integer quantidadeInclusaoItemNegativacao = null;

				if (comando.getIndicadorSimulacao() == NegativacaoComando.NAO_SIMULACAO) {
					// [SB0012] - Gerar Arquivo TXT para envio ao negativador
					dadosInclusaoNegativacao = getControladorSpcSerasa().pesquisarQuantidadeInclusaoNegativacao(comando.getId());
					quantidadeInclusaoItemNegativacao = getControladorSpcSerasa().pesquisarQuantidadeInclusaoItemNegativacao(comando.getId());

					Integer qtdRegistro = (Integer) dadosInclusaoNegativacao[2] + 1;

					FiltroNegativadorMovimento filtroNegativadorMovimento = new FiltroNegativadorMovimento();
					filtroNegativadorMovimento.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimento.ID, (Integer) dadosInclusaoNegativacao[3]));
					filtroNegativadorMovimento.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimento.NEGATIVADOR);
					filtroNegativadorMovimento.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimento.NEGATIVACAO_COMANDO);

					Collection colecaoNegativadorMovimento = getControladorUtil().pesquisar(filtroNegativadorMovimento, NegativadorMovimento.class.getName());

					NegativadorMovimento negativadorMovimento = null;

					if (colecaoNegativadorMovimento != null && !colecaoNegativadorMovimento.isEmpty()) {
						negativadorMovimento = (NegativadorMovimento) colecaoNegativadorMovimento.iterator().next();
					}

					this.getControladorSpcSerasa().gerarArquivoNegativacao(negativadorMovimento, true);

					NegativadorContrato contrato = getControladorSpcSerasa().consultarNegativadorContratoVigente(comando.getNegativador().getId());
					contrato.setNumeroSequencialEnvio(negativadorMovimento.getNumeroSequencialEnvio());

					if (contrato.getNumeroInclusoesEnviadas() != null) {
						contrato.setNumeroInclusoesEnviadas(contrato.getNumeroInclusoesEnviadas() + (Integer) dadosInclusaoNegativacao[0]);
					} else {
						contrato.setNumeroInclusoesEnviadas((Integer) dadosInclusaoNegativacao[0]);
					}

					getControladorUtil().atualizar(contrato);

					negativadorMovimento.setNumeroRegistrosEnvio(qtdRegistro);
					negativadorMovimento.setValorTotalEnvio((BigDecimal) dadosInclusaoNegativacao[1]);

					getControladorUtil().atualizar(negativadorMovimento);

				} else {
					dadosInclusaoNegativacao = getControladorSpcSerasa().pesquisarQuantidadeInclusaoNegativacaoSimulacao(comando.getId());
					quantidadeInclusaoItemNegativacao = (Integer) dadosInclusaoNegativacao[2];
				}

				comando.setDataHoraRealizacao(new Date());
				comando.setQuantidadeInclusoes((Integer) dadosInclusaoNegativacao[0]);
				comando.setValorDebito((BigDecimal) dadosInclusaoNegativacao[1]);
				comando.setQuantidadeItensIncluidos(quantidadeInclusaoItemNegativacao);
				comando.setUltimaAlteracao(new Date());

				getControladorUtil().atualizar(comando);

				NegativacaoComando.resetQuantidadeImoveisJaIncluidos();
				NegativadorMovimentoReg.resetNumeroProximoRegistro();

				logger.info("******************** FIM EXECUTAR COMANDO NEGATIVAÇÃO ********************");

				break;

			case Funcionalidade.GERAR_MOVIMENTO_EXTENSAO_CONTAS_COBRANCA_POR_EMPRESA:

				Collection colecaoComandoEmpresaCobrancaContaExtensao = (Collection) tarefaBatch.getParametro("colecaoComandoEmpresaCobrancaContaExtensao");

				if (colecaoComandoEmpresaCobrancaContaExtensao != null && !colecaoComandoEmpresaCobrancaContaExtensao.isEmpty()) {

					Iterator it = colecaoComandoEmpresaCobrancaContaExtensao.iterator();

					while (it.hasNext()) {

						ComandoEmpresaCobrancaContaExtensao comandoEmpresaCobrancaContaExtensao = (ComandoEmpresaCobrancaContaExtensao) it.next();

						comandoEmpresaCobrancaContaExtensao.setDataExecucao(new Date());

						comandoEmpresaCobrancaContaExtensao.setUltimaAlteracao(new Date());

						getControladorUtil().atualizar(comandoEmpresaCobrancaContaExtensao);
					}
				}
				break;
			}

			System.out.println("Saiu do finalizador ");

			// Caso ocorra erro com o finalizador seta a funcionalidadeIniciada para CONCLUIDA_COM_ERRO
		} catch (ControladorException ex) {
			this.funcionalidadeSituacao.setId(FuncionalidadeSituacao.CONCLUIDA_COM_ERRO);
		} catch (IOException ex) {
			this.funcionalidadeSituacao.setId(FuncionalidadeSituacao.CONCLUIDA_COM_ERRO);
		} catch (ClassNotFoundException ex) {
			this.funcionalidadeSituacao.setId(FuncionalidadeSituacao.CONCLUIDA_COM_ERRO);
		}
	}

	private ControladorArrecadacaoLocal getControladorArrecadacao() {
		ControladorArrecadacaoLocalHome localHome = null;
		ControladorArrecadacaoLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorArrecadacaoLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_ARRECADACAO_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	private ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorFaturamentoLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
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

	private ControladorFinanceiroLocal getControladorFinanceiro() {
		ControladorFinanceiroLocalHome localHome = null;
		ControladorFinanceiroLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorFinanceiroLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FINANCEIRO_SEJB);
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

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FuncionalidadeIniciada other = (FuncionalidadeIniciada) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
