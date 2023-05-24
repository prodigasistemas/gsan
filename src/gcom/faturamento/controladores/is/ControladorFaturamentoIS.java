package gcom.faturamento.controladores.is;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;

import org.jboss.logging.Logger;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelContaEnvio;
import gcom.cadastro.imovel.RepositorioImovelHBM;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.IRepositorioCobranca;
import gcom.cobranca.RepositorioCobrancaHBM;
import gcom.fachada.Fachada;
import gcom.faturamento.ControladorFaturamentoFINAL;
import gcom.faturamento.ExtratoQuitacao;
import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FaturamentoImediatoAjuste;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoImediatoAjuste;
import gcom.faturamento.FiltroImpostoTipo;
import gcom.faturamento.FiltroMovimentoContaCategoriaConsumoFaixa;
import gcom.faturamento.FiltroMovimentoContaImpostoDeduzido;
import gcom.faturamento.FiltroMovimentoContaPrefaturada;
import gcom.faturamento.FiltroMovimentoContaPrefaturadaCategoria;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.ImpostoTipo;
import gcom.faturamento.MobileComunicationException;
import gcom.faturamento.MovimentoContaCategoriaConsumoFaixa;
import gcom.faturamento.MovimentoContaImpostoDeduzido;
import gcom.faturamento.MovimentoContaPrefaturada;
import gcom.faturamento.MovimentoContaPrefaturadaCategoria;
import gcom.faturamento.MovimentoContaPrefaturadaCategoriaPK;
import gcom.faturamento.RepositorioFaturamentoHBM;
import gcom.faturamento.bean.ApagarDadosFaturamentoHelper;
import gcom.faturamento.bean.AtualizarContaPreFaturadaHelper;
import gcom.faturamento.bean.CalcularValoresAguaEsgotoHelper;
import gcom.faturamento.bean.DeterminarValoresFaturamentoAguaEsgotoHelper;
import gcom.faturamento.bean.RetornoAtualizarFaturamentoMovimentoCelularHelper;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaCategoria;
import gcom.faturamento.conta.ContaCategoriaConsumoFaixa;
import gcom.faturamento.conta.ContaCategoriaPK;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.conta.ContaImpostosDeduzidos;
import gcom.faturamento.conta.ContaImpressao;
import gcom.faturamento.conta.ContaTipo;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.conta.FiltroContaCategoria;
import gcom.faturamento.conta.FiltroContaCategoriaConsumoFaixa;
import gcom.faturamento.conta.FiltroContaImpostosDeduzidos;
import gcom.faturamento.conta.FiltroContaImpressao;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoARealizarCategoria;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.credito.CreditoRealizadoCategoria;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.credito.FiltroCreditoARealizar;
import gcom.faturamento.credito.FiltroCreditoRealizado;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.gui.micromedicao.DadosMovimentacao;
import gcom.gui.micromedicao.ProcessarRequisicaoDipositivoMovelImpressaoSimultaneaAction;
import gcom.micromedicao.ArquivoTextoRetornoIS;
import gcom.micromedicao.FiltroMovimentoRoteiroEmpresa;
import gcom.micromedicao.FiltroReleituraMobile;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.IRepositorioMicromedicao;
import gcom.micromedicao.MovimentoArquivoTextoRetornoIS;
import gcom.micromedicao.MovimentoRoteiroEmpresa;
import gcom.micromedicao.ReleituraMobile;
import gcom.micromedicao.RepositorioMicromedicaoHBM;
import gcom.micromedicao.Rota;
import gcom.micromedicao.RotaAtualizacaoSeq;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.ConsumoTipo;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidade;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraSituacao;
import gcom.micromedicao.medicao.FiltroMedicaoHistorico;
import gcom.micromedicao.medicao.FiltroMedicaoTipo;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.faturamento.RelatorioErrosMovimentosContaPreFaturadas;
import gcom.relatorio.faturamento.RelatorioErrosMovimentosContaPreFaturadasBean;
import gcom.relatorio.faturamento.RelatorioResumoLeiturasAnormalidadesImpressaoSimultanea;
import gcom.relatorio.faturamento.RelatorioResumoLeiturasAnormalidadesImpressaoSimultaneaBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesAplicacao;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorComum;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.IRepositorioUtil;
import gcom.util.RepositorioUtilHBM;
import gcom.util.Util;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

public class ControladorFaturamentoIS extends ControladorComum {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(ControladorFaturamentoFINAL.class);

	SistemaParametro sistemaParametro = null;
	
	protected IRepositorioFaturamento repositorioFaturamento;
	protected IRepositorioCobranca repositorioCobranca;
	protected IRepositorioUtil repositorioUtil;
	protected IRepositorioMicromedicao repositorioMicromedicao;
	
	public void ejbCreate() throws CreateException {
		repositorioFaturamento = RepositorioFaturamentoHBM.getInstancia();
		repositorioCobranca = RepositorioCobrancaHBM.getInstancia();
		repositorioUtil = RepositorioUtilHBM.getInstancia();
		repositorioMicromedicao = RepositorioMicromedicaoHBM.getInstancia();
	}
	
	/**
	 * Atualiza um conjunto de leituras e anormalidades bem como seu consumo e suas
	 * contas prefaturadas
	 */
	public RetornoAtualizarFaturamentoMovimentoCelularHelper atualizarFaturamentoMovimentoCelular(BufferedReader buffer,
			String nomeArquivo, boolean offline, boolean finalizarArquivo, Integer idRota,
			ArquivoTextoRetornoIS arquivoTextoRetornoIS, BufferedReader bufferOriginal) throws ControladorException {

		RetornoAtualizarFaturamentoMovimentoCelularHelper retorno = new RetornoAtualizarFaturamentoMovimentoCelularHelper();

		Object[] retornoIncluirMovimento = null;
		Collection<AtualizarContaPreFaturadaHelper> colecaoAtualizarContaPreFaturadaHelper = null;
		byte[] relatorio = null;

		if (buffer == null) {
			throw new ControladorException("atencao.arquivo_sem_dados", null, nomeArquivo);
		}

		try {
			// Verificamos se algum erro de comunieeo previsto aconteceu. Caso positivo,
			// incluimos a mensagem retornada no retorno da funeeo.
			retornoIncluirMovimento = this.incluirMovimentoContaPreFaturada(buffer, idRota, arquivoTextoRetornoIS,
					bufferOriginal);
		} catch (MobileComunicationException mce) {
			if (offline) {
				throw new ControladorException(mce.getMessage(), mce);
			} else {
				// Setamos e retornamos
				mce.printStackTrace();
				retorno.setMensagemComunicacaoServidorCelular("mensagem=" + ConstantesAplicacao.get(mce.getMessage()));
				return retorno;
			}
		}

		try {
			relatorio = (byte[]) retornoIncluirMovimento[0];
			colecaoAtualizarContaPreFaturadaHelper = (Collection) retornoIncluirMovimento[1];
			Collection<MovimentoContaPrefaturada> colContaPreFaturada = new ArrayList();
			Collection<Integer> colIdsImoveisAtualizar = new ArrayList();

			if (relatorio != null) {
				retorno.setRelatorioConsistenciaProcessamento(relatorio);
				retorno.setIndicadorSucessoAtualizacao(false);
				return retorno;
			}

			// Pesquisamos a rota que utilizaremos na consistencia dos dados
			Rota rota = null;
			if (colecaoAtualizarContaPreFaturadaHelper != null && !colecaoAtualizarContaPreFaturadaHelper.equals("")) {

				for (AtualizarContaPreFaturadaHelper helperCabecalho : colecaoAtualizarContaPreFaturadaHelper) {
					if (rota == null || rota.equals("")) {
						rota = pesquisarRotaImpressaoSimultanea(helperCabecalho);
					}

					Collection<MovimentoContaPrefaturada> colMovimentoContaPreFaturada = verificarExistenciaListaMovimentoContaPrefaturada(
							helperCabecalho);
					if (colMovimentoContaPreFaturada != null && !colMovimentoContaPreFaturada.isEmpty()) {
						colContaPreFaturada.addAll(colMovimentoContaPreFaturada);

						for (MovimentoContaPrefaturada prefaturada : colMovimentoContaPreFaturada) {
							colIdsImoveisAtualizar.add(prefaturada.getImovel().getId());
						}
					}
				}
			}

			this.processarMovimentoContaPrefaturada(rota, colContaPreFaturada, true);
			this.atualizarInformacoesImpressaoExtratoQuitacao(colContaPreFaturada);

			if (offline) {
				relatorio = this.geraResumoLeiturasAnormalidadesImpressaoSimultanea(colContaPreFaturada);
				retorno.setRelatorioConsistenciaProcessamento(relatorio);
			}

			// Verificamos se je foi enviado algum tipo de mensagem nessa requisieeo
			if (rota != null) {
				// Caso neo seja finalizar a rota, enteo verifica se tem releitura para a
				// rota
				if (!finalizarArquivo) {
					if (retorno.getMensagemComunicacaoServidorCelular() == null) {
						String releituraImoveis = this
								.verificarSolicitacaoReleituraImovelImpressaoSimultanea(rota.getId());

						if (releituraImoveis != null) {
							retorno.setMensagemComunicacaoServidorCelular(releituraImoveis);
						}
					}
				}

				Integer anoMesFaturamentoGrupoRota = getControladorFaturamento().retornaAnoMesFaturamentoGrupoDaRota(rota.getId());

				FiltroReleituraMobile filtroReleituraMobile = new FiltroReleituraMobile();
				filtroReleituraMobile.adicionarParametro(
						new ParametroSimples(FiltroReleituraMobile.ANO_MES_FATURAMENTO, anoMesFaturamentoGrupoRota));
				filtroReleituraMobile.adicionarParametro(
						new ParametroSimplesIn(FiltroReleituraMobile.ID_IMOVEL, colIdsImoveisAtualizar));
				filtroReleituraMobile.adicionarParametro(
						new ParametroSimples(FiltroReleituraMobile.INDICADOR_RELEITURA, ConstantesSistema.NAO));

				Collection<ReleituraMobile> colReleituraMobile = this.getControladorUtil()
						.pesquisar(filtroReleituraMobile, ReleituraMobile.class.getName());

				if (colReleituraMobile != null && colReleituraMobile.size() > 0) {
					for (ReleituraMobile mobile : colReleituraMobile) {

						FiltroMovimentoContaPrefaturada filtroMovimentoContaPrefaturada = new FiltroMovimentoContaPrefaturada();
						filtroMovimentoContaPrefaturada.adicionarParametro(new ParametroSimples(
								FiltroMovimentoContaPrefaturada.MATRICULA, mobile.getImovel().getId()));
						filtroMovimentoContaPrefaturada.adicionarParametro(
								new ParametroSimples(FiltroMovimentoContaPrefaturada.ANO_MES_REFERENCIA_PRE_FATURAMENTO,
										anoMesFaturamentoGrupoRota));

						Collection<MovimentoContaPrefaturada> colMovimentoContaPrefaturada = getControladorUtil()
								.pesquisar(filtroMovimentoContaPrefaturada, MovimentoContaPrefaturada.class.getName());

						Integer leituraAgua = null;
						Integer leituraPoco = null;

						LeituraAnormalidade leituraAnormalidadeAgua = null;
						LeituraAnormalidade leituraAnormalidadePoco = null;

						for (MovimentoContaPrefaturada prefaturada : colMovimentoContaPrefaturada) {
							if (prefaturada.getMedicaoTipo().getId().intValue() == MedicaoTipo.LIGACAO_AGUA) {
								leituraAgua = prefaturada.getLeituraHidrometro();
								leituraAnormalidadeAgua = prefaturada.getLeituraAnormalidadeLeitura();
							} else {
								leituraPoco = prefaturada.getLeituraHidrometro();
								leituraAnormalidadePoco = prefaturada.getLeituraAnormalidadeLeitura();
							}
						}

						mobile.setLeituraAnteriorAgua(mobile.getLeituraAtualAgua());
						mobile.setLeituraAnteriorPoco(mobile.getLeituraAtualPoco());
						mobile.setLeituraAnormalidadeAnteriorAgua(mobile.getLeituraAnormalidadeAtualAgua());
						mobile.setLeituraAnormalidadeAnteriorPoco(mobile.getLeituraAnormalidadeAtualPoco());
						mobile.setLeituraAtualAgua(leituraAgua);
						mobile.setLeituraAtualPoco(leituraPoco);
						mobile.setLeituraAnormalidadeAtualAgua(leituraAnormalidadeAgua);
						mobile.setLeituraAnormalidadeAtualPoco(leituraAnormalidadePoco);
						mobile.setIndicadorReleitura(new Integer(ConstantesSistema.SIM));
						mobile.setUltimaAlteracao(new Date());
					}

					this.getControladorBatch().atualizarColecaoObjetoParaBatch(colReleituraMobile);
				}
			}

			retorno.setIndicadorSucessoAtualizacao(true);
		} catch (Exception e) {
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}
	
	/**
	 * Este caso de uso permite enviar email para cliente informando que sua conta
	 * je foi gerada. Retorno do celular
	 * 
	 * @author Raphael Rossiter
	 * @date 03/03/2010
	 * 
	 * @param rota
	 * @param colContaPreFaturada
	 * @param efetuarRateio
	 * @param atualizaSituacaoAtualConta - Caso seja chamado via a funcionalidade de
	 *                                   ISC, neo atualiza a situaeeo atual da
	 *                                   conta que neo foi impressa. Caso seja
	 *                                   chamado via a funcionalidade de consistir,
	 *                                   atualiza a situaeeo atual da conta.
	 * @throws ControladorException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void processarMovimentoContaPrefaturada(Rota rota, Collection<MovimentoContaPrefaturada> colContaPreFaturada,
			boolean efetuarRateio) throws ControladorException {

		try {
			if (colContaPreFaturada != null && !colContaPreFaturada.isEmpty()) {

				Collection<Integer> colIdImoveisCondominio = new ArrayList();
				Collection<Imovel> colImoveis = new ArrayList();

				Collection<DadosMovimentacao> colecaoDadosMovimentacao = new ArrayList();

				Long imei = null;
				if (rota != null && rota.getLeiturista() != null && !rota.getLeiturista().equals("")) {
					imei = rota.getLeiturista().getNumeroImei();
				}

				Collection<Integer> colIdImoveis = new ArrayList();

				for (MovimentoContaPrefaturada movimentoContaPreFaturada : colContaPreFaturada) {

					if (!colIdImoveis.contains(movimentoContaPreFaturada.getImovel().getId())) {
						colIdImoveis.add(movimentoContaPreFaturada.getImovel().getId());
						colImoveis.add(movimentoContaPreFaturada.getImovel());
					}

					FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
					filtroLigacaoAgua.adicionarParametro(
							new ParametroSimples(FiltroLigacaoAgua.ID, movimentoContaPreFaturada.getImovel().getId()));
					filtroLigacaoAgua.adicionarParametro(
							new ParametroNaoNulo(FiltroLigacaoAgua.HIDROMETRO_INSTALACAO_HISTORICO));

					Collection<LigacaoAgua> colLigacaoAgua = this.repositorioUtil.pesquisar(filtroLigacaoAgua,
							LigacaoAgua.class.getName());

					if ((colLigacaoAgua != null && colLigacaoAgua.size() > 0)
							|| movimentoContaPreFaturada.getImovel().getHidrometroInstalacaoHistorico() != null) {

						if ((movimentoContaPreFaturada.getMedicaoTipo().getId() == MedicaoTipo.LIGACAO_AGUA.intValue()
								&& colLigacaoAgua != null && colLigacaoAgua.size() > 0)
								|| (movimentoContaPreFaturada.getMedicaoTipo().getId() == MedicaoTipo.POCO.intValue()
										&& movimentoContaPreFaturada.getImovel()
												.getHidrometroInstalacaoHistorico() != null)) {
							incluirMedicaoHistorico(movimentoContaPreFaturada);
						}
					} else {

						if (movimentoContaPreFaturada.getLeituraAnormalidadeLeitura() != null
								&& movimentoContaPreFaturada.getLeituraAnormalidadeLeitura().getId() != null
								&& !movimentoContaPreFaturada.getLeituraAnormalidadeLeitura().getId().equals("")) {

							Imovel imovel = movimentoContaPreFaturada.getImovel();
							imovel.setLeituraAnormalidade(movimentoContaPreFaturada.getLeituraAnormalidadeLeitura());
							imovel.setUltimaAlteracao(new Date());

							RepositorioImovelHBM.getInstancia()
									.atualizarImovelLeituraAnormalidadeProcessoMOBILE(imovel);
						}
					}

					if (movimentoContaPreFaturada.getImovel().isImovelMacroCondominio()
							&& !colIdImoveisCondominio.contains(movimentoContaPreFaturada.getImovel().getId())) {
						colIdImoveisCondominio.add(movimentoContaPreFaturada.getImovel().getId());	
					}

					Integer idAnormalidade = null;
					if (movimentoContaPreFaturada.getLeituraAnormalidadeLeitura() != null) {
						idAnormalidade = movimentoContaPreFaturada.getLeituraAnormalidadeLeitura().getId();
					}

					Byte indicadorConfirmacao = new Byte("0");
					if (movimentoContaPreFaturada.getIndicadorSituacaoLeitura() != null
							&& !movimentoContaPreFaturada.getIndicadorSituacaoLeitura().equals("")) {
						indicadorConfirmacao = new Byte("" + movimentoContaPreFaturada.getIndicadorSituacaoLeitura());
					}

					Integer idMedicaoTipo = null;
					if ((movimentoContaPreFaturada.getLeituraHidrometro() != null
							&& !movimentoContaPreFaturada.getLeituraHidrometro().equals(""))
							|| (movimentoContaPreFaturada.getLeituraAnormalidadeLeitura() != null
									&& movimentoContaPreFaturada.getLeituraAnormalidadeLeitura().getId() != null
									&& !movimentoContaPreFaturada.getLeituraAnormalidadeLeitura().getId().equals(""))) {
						idMedicaoTipo = movimentoContaPreFaturada.getMedicaoTipo().getId();
					}

					DadosMovimentacao dadosMovimentacao = new DadosMovimentacao(
							movimentoContaPreFaturada.getImovel().getId(),
							movimentoContaPreFaturada.getLeituraHidrometro(), idAnormalidade,
							movimentoContaPreFaturada.getDataHoraLeitura(), imei, indicadorConfirmacao, idMedicaoTipo);

					colecaoDadosMovimentacao.add(dadosMovimentacao);

				}

				// ataliza movimento roteiro empresa
				if (colecaoDadosMovimentacao != null && !colecaoDadosMovimentacao.isEmpty()) {
					getControladorMicromedicao().atualizarRoteiro(colecaoDadosMovimentacao, true);
				}

				if (colIdImoveis != null && !colIdImoveis.isEmpty()) {
					this.getControladorMicromedicao()
							.consistirLeiturasCalcularConsumosImoveis(rota.getFaturamentoGrupo(), colIdImoveis);
					getControladorFaturamento().atualizarFaturamentoImoveisCortados(colImoveis,
							rota.getFaturamentoGrupo().getAnoMesReferencia().intValue());
					colImoveis = null;
				}

				// Verificamos se devemos efetuar o rateio
				if (colIdImoveisCondominio != null && colIdImoveisCondominio.size() > 0 && efetuarRateio) {
					for (Integer idImovelCondominio : colIdImoveisCondominio) {
						this.getControladorMicromedicao().efetuarRateioDeConsumo(idImovelCondominio,
								rota.getFaturamentoGrupo().getAnoMesReferencia());
					}
				}

				for (MovimentoContaPrefaturada movimentoContaPreFaturada : colContaPreFaturada) {
					Date dataLeituraAtual = movimentoContaPreFaturada.getDataHoraLeitura();

					if (dataLeituraAtual == null || dataLeituraAtual.equals("")) {
						dataLeituraAtual = new Date();
					}
					getControladorMicromedicao().atualizarDataRealizacaoGronogramaPreFaturamento(
							rota.getFaturamentoGrupo().getId(), rota.getFaturamentoGrupo().getAnoMesReferencia(),
							dataLeituraAtual);
					break;
				}

				this.atualizarMovimentoCelular(colContaPreFaturada, efetuarRateio);

				// neo atualizar o indicador de atualizacao de faturamento caso indicador de
				// emissao de conta seja igual a 2 e o imevel neo
				// esteja vinculado com nenhuma outra matrecula (Imevel micro) ou venha pela
				// funcionalidade de consistir
				for (MovimentoContaPrefaturada movimentoContaPreFaturada : colContaPreFaturada) {

					if (movimentoContaPreFaturada.getMovimentoContaPrefaturadaCategorias() != null
							&& movimentoContaPreFaturada.getMovimentoContaPrefaturadaCategorias().size() > 0) {

						if ((movimentoContaPreFaturada.getIndicadorEmissaoConta().shortValue() == ConstantesSistema.SIM
								.shortValue())
								|| (movimentoContaPreFaturada.getIndicadorEmissaoConta()
										.shortValue() == ConstantesSistema.NAO.shortValue()
										&& movimentoContaPreFaturada.getImovel().getImovelCondominio() != null)) {

							movimentoContaPreFaturada.setUtlimaAlteracao(new Date());
							movimentoContaPreFaturada.setIndicadorAtualizacaoFaturamento(Short.parseShort("1"));

							repositorioFaturamento
									.atualizarMovimentoContaPrefaturadaProcessoMOBILE(movimentoContaPreFaturada);
						}
					}
				}
			}

		} catch (Exception e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	private Collection<CalcularValoresAguaEsgotoHelper> obterValoresAguaEsgotoAtualizarMovimentoCelular(MovimentoContaPrefaturada movimento, Conta conta) throws ControladorException {
		
		try {
			ConsumoHistorico consumoHistoricoAgua = getControladorMicromedicao().
					obterConsumoHistorico(movimento.getImovel(), new LigacaoTipo(LigacaoTipo.LIGACAO_AGUA), 
							movimento.getFaturamentoGrupo().getAnoMesReferencia());
		
			ConsumoHistorico consumoHistoricoEsgoto = getControladorMicromedicao().
					obterConsumoHistorico(movimento.getImovel(), new LigacaoTipo(LigacaoTipo.LIGACAO_ESGOTO), 
							movimento.getFaturamentoGrupo().getAnoMesReferencia());
			
			@SuppressWarnings("rawtypes")
			Collection colecaoCategoriaOUSubcategoria = getControladorImovel()
					.obterColecaoCategoriaOuSubcategoriaDoImovel(movimento.getImovel());
	
			colecaoCategoriaOUSubcategoria = getControladorFaturamento().obterColecaoCategoriaConta(
					sistemaParametro, conta);
	
			boolean isImpressaoSimultanea = true;
			
			DeterminarValoresFaturamentoAguaEsgotoHelper helperValoresAguaEsgoto = getControladorFaturamento()
					.determinarValoresFaturamento(movimento.getImovel(), movimento.getFaturamentoGrupo().getAnoMesReferencia(),
							colecaoCategoriaOUSubcategoria, movimento.getImovel().getQuadra().getRota().getFaturamentoGrupo(),
							consumoHistoricoAgua, consumoHistoricoEsgoto, isImpressaoSimultanea);
	
			@SuppressWarnings("unchecked")
			Collection<CalcularValoresAguaEsgotoHelper> colecaoCalcularValoresAguaEsgotoHelper = helperValoresAguaEsgoto
					.getColecaoCalcularValoresAguaEsgotoHelper();
	
			return colecaoCalcularValoresAguaEsgotoHelper;
		
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new ControladorException("Erro obterValoresAguaEsgotoAtualizarMovimentoCelular", e);
		}

	}
	
	@SuppressWarnings("unchecked")
	private void preencherValoresTotaisMovimentoContaPreFaturada(MovimentoContaPrefaturada movimento) {
		Collection<MovimentoContaPrefaturadaCategoria> colMovimentoContaPrefaturadaCategoria = movimento
				.getMovimentoContaPrefaturadaCategorias();

		BigDecimal valorAgua = new BigDecimal(0);
		BigDecimal valorEsgoto = new BigDecimal(0);

		Integer consumoAgua = 0;
		Integer consumoEsgoto = 0;

		for (MovimentoContaPrefaturadaCategoria helperCategoria : colMovimentoContaPrefaturadaCategoria) {
			valorAgua = valorAgua.add(helperCategoria.getValorFaturadoAgua());
			valorEsgoto = valorEsgoto.add(helperCategoria.getValorFaturadoEsgoto());
			consumoAgua += helperCategoria.getConsumoFaturadoAgua();
			consumoEsgoto += helperCategoria.getConsumoFaturadoEsgoto();
		}
		
		movimento.setValorTotalCategoriasAgua(valorAgua);
		movimento.setValorTotalCategoriasEsgoto(valorEsgoto);
		
		movimento.setValorTotalConsumoAgua(consumoAgua);
		movimento.setValorTotalConsumoEsgoto(consumoEsgoto);

	}
	@SuppressWarnings("unchecked")
	private void executarFaturamentoAjusteMovimentoCelular(MovimentoContaPrefaturada movimento, boolean efetuarRateio,
			BigDecimal valorTotalAguaCalculado, BigDecimal valorTotalEsgotoCalculado,
			Integer consumoAguaCalculado, Integer consumoEsgotoCalculado, Conta conta) throws ControladorException {
		Collection<MovimentoContaPrefaturadaCategoria> categoriasMovimento = movimento
				.getMovimentoContaPrefaturadaCategorias();
		

		BigDecimal diferencaValorAgua = movimento.getValorTotalCategoriasAgua().subtract(valorTotalAguaCalculado);
		BigDecimal diferencaValorEsgoto = movimento.getValorTotalCategoriasEsgoto().subtract(valorTotalEsgotoCalculado);

		if (efetuarRateio && ((diferencaValorAgua.doubleValue() > .01d
				|| diferencaValorAgua.doubleValue() < -.01d)
				|| (diferencaValorEsgoto.doubleValue() > .01d
						|| diferencaValorEsgoto.doubleValue() < -.01d)
				|| consumoAguaCalculado.intValue() != movimento.getValorTotalConsumoAgua().intValue()
				|| consumoEsgotoCalculado.intValue() != movimento.getValorTotalConsumoEsgoto().intValue())) {

			FaturamentoImediatoAjuste faturamentoImediatoAjuste = new FaturamentoImediatoAjuste();
			faturamentoImediatoAjuste.setConta(conta);
			faturamentoImediatoAjuste.setNumeroConsumoAgua(movimento.getValorTotalConsumoAgua() - consumoAguaCalculado);
			faturamentoImediatoAjuste.setNumeroConsumoEsgoto(movimento.getValorTotalConsumoEsgoto() - consumoEsgotoCalculado);
			faturamentoImediatoAjuste.setValorCobradoAgua(diferencaValorAgua);
			faturamentoImediatoAjuste.setValorCobradoEsgoto(diferencaValorEsgoto);
			faturamentoImediatoAjuste.setUltimaAlteracao(new Date());

			this.getControladorBatch().inserirObjetoParaBatch(faturamentoImediatoAjuste);
			this.atualizarConsumoMovimentoCelular(conta, movimento.getValorTotalConsumoAgua(), consumoAguaCalculado,
					movimento.getValorTotalConsumoEsgoto(), consumoEsgotoCalculado);
		}

	}
	
	private ContaCategoria obterContaCategoriaAtualizarMovimento(Conta conta, MovimentoContaPrefaturadaCategoria movimentoCategoria) throws ControladorException {
		FiltroContaCategoria filtroContaCategoria = new FiltroContaCategoria();

		filtroContaCategoria
				.adicionarParametro(new ParametroSimples(FiltroContaCategoria.CATEGORIA_ID,
						movimentoCategoria.getComp_id().getCategoria().getId()));
		filtroContaCategoria.adicionarParametro(
				new ParametroSimples(FiltroContaCategoria.CONTA_ID, conta.getId()));

		Integer idSubcategoria = getIdSubcategoria(movimentoCategoria);
		filtroContaCategoria.adicionarParametro(
				new ParametroSimples(FiltroContaCategoria.SUBCATEGORIA_ID, idSubcategoria));
		filtroContaCategoria
				.adicionarCaminhoParaCarregamentoEntidade(FiltroContaCategoria.CONTA);
		filtroContaCategoria
				.adicionarCaminhoParaCarregamentoEntidade(FiltroContaCategoria.IMOVEL);
		filtroContaCategoria
				.adicionarCaminhoParaCarregamentoEntidade(FiltroContaCategoria.CATEGORIA);

		@SuppressWarnings("unchecked")
		Collection<ContaCategoria> colContaCategoria = this.getControladorUtil()
				.pesquisar(filtroContaCategoria, ContaCategoria.class.getName());

		ContaCategoria contaCategoria = (ContaCategoria) Util
				.retonarObjetoDeColecao(colContaCategoria);
		
		return contaCategoria;

	}
	
	private Integer getIdSubcategoria(MovimentoContaPrefaturadaCategoria movimentoCategoria) {
		if (sistemaParametro.getIndicadorTarifaCategoria()
				.equals(SistemaParametro.INDICADOR_TARIFA_CATEGORIA)) {
			return 0;
		} else {
			return movimentoCategoria.getComp_id().getSubcategoria().getId();
		}
	}
	
	/**
	 * 
	 * [UC0840] - Atualizar Faturamento do Movimento Celular
	 * 
	 * @author bruno
	 * @date 15/06/2009
	 * 
	 * @param colHelper
	 * @param atualizaSituacaoAtualConta - Caso seja chamado via a funcionalidade de
	 *    ISC, neo atualiza a situaeeo atual da conta que neo foi impressa. Caso seja
	 *    chamado via a funcionalidade de consistir, atualiza a situaeeo atual da conta.
	 */
	private void atualizarMovimentoCelular(Collection<MovimentoContaPrefaturada> colMovimentoContaPrefaturada,
			boolean efetuarRateio) throws ControladorException {

		sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
		
		String matriculaImovel = "";
		try {
			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();

			for (MovimentoContaPrefaturada helper : colMovimentoContaPrefaturada) {

				if (helper.existeCategoria()) {

					//Quando a conta nao tiver sido emitida pelo IS, nao altera a conta, continua PF
					if (helper.isContaEmitidaPeloIS()) {
						
						// Caso o imovel seja o imovel condominio, pulamos
						if (helper.getImovel().isImovelMicroCondominio()) {
							continue;
						}

						Conta contaAtualizacao = helper.getConta();

						contaAtualizacao = getControladorFaturamento().pesquisarContaPreFaturada(
								helper.getImovel().getId(), helper.getAnoMesReferenciaPreFaturamento(),
								DebitoCreditoSituacao.PRE_FATURADA);

						if (contaAtualizacao == null || contaAtualizacao.getId() == null) {
							continue;
						}
						
						matriculaImovel = helper.getImovel().getId().toString();
						Imovel imo = getControladorImovel().pesquisarImovel(helper.getImovel().getId());
						helper.setImovel(imo);
						
						Collection<CalcularValoresAguaEsgotoHelper> colecaoCalcularValoresAguaEsgotoHelper = 
								obterValoresAguaEsgotoAtualizarMovimentoCelular(helper, contaAtualizacao);
							
						BigDecimal valorTotalAguaCalculado = getControladorFaturamento().calcularValorTotalAguaOuEsgotoPorCategoria(
								colecaoCalcularValoresAguaEsgotoHelper, ConstantesSistema.CALCULAR_AGUA);
						
						BigDecimal valorTotalEsgotoCalculado = getControladorFaturamento().calcularValorTotalAguaOuEsgotoPorCategoria(
								colecaoCalcularValoresAguaEsgotoHelper, ConstantesSistema.CALCULAR_ESGOTO);

						Integer consumoAguaCalculado = null;
						consumoAguaCalculado = getControladorFaturamento().calcularConsumoTotalAguaOuEsgotoPorCategoria(
								colecaoCalcularValoresAguaEsgotoHelper, ConstantesSistema.CALCULAR_AGUA);

						Integer consumoEsgotoCalculado = null;
						consumoEsgotoCalculado = getControladorFaturamento().calcularConsumoTotalAguaOuEsgotoPorCategoria(
								colecaoCalcularValoresAguaEsgotoHelper, ConstantesSistema.CALCULAR_ESGOTO);

						@SuppressWarnings("unchecked")
						Collection<MovimentoContaPrefaturadaCategoria> colMovimentoContaPrefaturadaCategoria = helper
								.getMovimentoContaPrefaturadaCategorias();

						this.preencherValoresTotaisMovimentoContaPreFaturada(helper);

						this.executarFaturamentoAjusteMovimentoCelular(helper, efetuarRateio, 
								valorTotalAguaCalculado, valorTotalEsgotoCalculado, consumoAguaCalculado, 
								consumoEsgotoCalculado, contaAtualizacao);
						
						helper.setConta(contaAtualizacao);

						for (MovimentoContaPrefaturadaCategoria helperCategoria : colMovimentoContaPrefaturadaCategoria) {
							ContaCategoria contaCategoria = obterContaCategoriaAtualizarMovimento(contaAtualizacao, helperCategoria);

							Integer idSubcategoria = getIdSubcategoria(helperCategoria);
							
							if (contaCategoria != null && !contaCategoria.equals("")) {

								atualizarValoresContaCategoria(contaAtualizacao, helperCategoria, contaCategoria);	

								atualizarValoresContaCategoriaFaixa(helper, helperCategoria, contaCategoria,
										idSubcategoria);

							}
						}

						/*
						 * 
						 * Para cada registro do tipo 4, alterar na tabela CONTA_IMPOSTOS_DEDUZIDOS o
						 * seu correspondente (CNTA_ID = Conta do movimento em processamento e IMTP_ID =
						 * cedigo do imposto do movimento) , com os seguintes dados
						 */
						FiltroMovimentoContaImpostoDeduzido filtroMovimentoContaImpostoDeduzido = new FiltroMovimentoContaImpostoDeduzido();

						filtroMovimentoContaImpostoDeduzido.adicionarParametro(new ParametroSimples(
								FiltroMovimentoContaImpostoDeduzido.MOVIMENTO_CONTA_PREFATURADA_ID,
								helper.getId()));

						Collection<MovimentoContaImpostoDeduzido> colMovimentoContaImpostoDeduzido = this
								.getControladorUtil().pesquisar(filtroMovimentoContaImpostoDeduzido,
										MovimentoContaImpostoDeduzido.class.getName());

						BigDecimal valorTotalMenosImposto = new BigDecimal(helper.getValorTotalCategoriasAgua().doubleValue()
								+ helper.getValorTotalCategoriasEsgoto().doubleValue() + contaAtualizacao.getDebitos().doubleValue()
								- contaAtualizacao.getValorCreditos().doubleValue());

						BigDecimal valorImposto = new BigDecimal(0);
						for (MovimentoContaImpostoDeduzido helperMovimentoContaImpostoDeduzido : colMovimentoContaImpostoDeduzido) {

							FiltroContaImpostosDeduzidos filtroContaImpostosDeduzidos = new FiltroContaImpostosDeduzidos();
							filtroContaImpostosDeduzidos.adicionarParametro(new ParametroSimples(
									FiltroContaImpostosDeduzidos.CONTA_ID, helper.getConta().getId()));
							filtroContaImpostosDeduzidos.adicionarParametro(
									new ParametroSimples(FiltroContaImpostosDeduzidos.IMPOSTO_TIPO,
											helperMovimentoContaImpostoDeduzido.getImpostoTipo().getId()));

							Collection<ContaImpostosDeduzidos> colContaImpostosDeduzidos = this.getControladorUtil()
									.pesquisar(filtroContaImpostosDeduzidos,
											ContaImpostosDeduzidos.class.getName());

							ContaImpostosDeduzidos contaImpostosDeduzidos = (ContaImpostosDeduzidos) Util
									.retonarObjetoDeColecao(colContaImpostosDeduzidos);

							if (contaImpostosDeduzidos != null && !contaImpostosDeduzidos.equals("")) {
								contaImpostosDeduzidos
										.setValorImposto(helperMovimentoContaImpostoDeduzido.getValorImposto());

								valorImposto = valorImposto
										.add(helperMovimentoContaImpostoDeduzido.getValorImposto());

								contaImpostosDeduzidos.setPercentualAliquota(
										helperMovimentoContaImpostoDeduzido.getPercentualAliquota());
								contaImpostosDeduzidos.setValorBaseCalculo(valorTotalMenosImposto);
								contaImpostosDeduzidos.setUltimaAlteracao(new Date());

								try {
									repositorioFaturamento
											.atualizarContaImpostosDeduzidosProcessoMOBILE(contaImpostosDeduzidos);
								} catch (ErroRepositorioException e) {
									throw new ControladorException("erro.sistema", e);
								}
							}
						}

						contaAtualizacao.setConsumoAgua(helper.getValorTotalConsumoAgua());
						contaAtualizacao.setConsumoEsgoto(helper.getValorTotalConsumoEsgoto());
						contaAtualizacao.setConsumoRateioAgua(helper.getConsumoRateioAgua());
						contaAtualizacao.setConsumoRateioEsgoto(helper.getConsumoRateioEsgoto());
						contaAtualizacao.setValorAgua(helper.getValorTotalCategoriasAgua());
						contaAtualizacao.setValorEsgoto(helper.getValorTotalCategoriasEsgoto());
						contaAtualizacao.setValorImposto(valorImposto);
						contaAtualizacao.setDataEmissao(helper.getDataHoraLeitura());
						contaAtualizacao.setUltimaAlteracao(new Date());
						contaAtualizacao.setNumeroLeituraAtual(helper.getLeituraFaturamento());
						contaAtualizacao.setNumeroLeituraAnterior(helper.getLeituraHidrometroAnterior());
						contaAtualizacao.setValorRateioAgua(helper.getValorRateioAgua());
						contaAtualizacao.setValorRateioEsgoto(helper.getValorRateioEsgoto());

						DebitoCreditoSituacao debitoCreditoSituacao = new DebitoCreditoSituacao();
						debitoCreditoSituacao.setId(DebitoCreditoSituacao.NORMAL);
						contaAtualizacao.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);

						// Se ire atualizar o nitrato na conta caso o mesmo ainda nao tenha sido
						// atualizado
						FiltroCreditoARealizar filtroCreditoARealizar = new FiltroCreditoARealizar();
						filtroCreditoARealizar.adicionarParametro(
								new ParametroSimples(FiltroCreditoARealizar.IMOVEL_ID, helper.getImovel().getId()));
						filtroCreditoARealizar.adicionarParametro(
								new ParametroSimples(FiltroCreditoARealizar.ANO_MES_REFERENCIA_CREDITO,
										helper.getAnoMesReferenciaPreFaturamento()));
						filtroCreditoARealizar.adicionarParametro(new ParametroSimples(
								FiltroCreditoARealizar.CREDITO_TIPO, CreditoTipo.CREDITO_NITRATO));

						Collection<CreditoARealizar> colCreditoARealizar = this.getControladorUtil()
								.pesquisar(filtroCreditoARealizar, CreditoARealizar.class.getName());

						if (colCreditoARealizar != null && colCreditoARealizar.size() > 0) {
							CreditoARealizar credito = (CreditoARealizar) Util
									.retonarObjetoDeColecao(colCreditoARealizar);

							if (credito.getValorCredito() == null || credito.getValorCredito().floatValue() == 0) {
								BigDecimal valorCreditoNitrato = getControladorFaturamento().atualizarCreditoARealizarNitrato(
										helper.getImovel(), helper.getAnoMesReferenciaPreFaturamento(), helper.getValorTotalCategoriasAgua(),
										helper.getConta());

								if (valorCreditoNitrato != null) {
									BigDecimal valorCreditos = contaAtualizacao.getValorCreditos();
									valorCreditos = valorCreditos.add(valorCreditoNitrato);
									contaAtualizacao.setValorCreditos(valorCreditos);
								}
							}
						}

						// verifica se o valor credito e maior que o valor da conta caso seja chamar
						// atualizar os creditos a realizar e os creditos realizados
						BigDecimal valorTotalContaSemCreditos = helper.getValorTotalCategoriasAgua().add(helper.getValorTotalCategoriasEsgoto());
						valorTotalContaSemCreditos = valorTotalContaSemCreditos.add(contaAtualizacao.getDebitos());
						valorTotalContaSemCreditos = valorTotalContaSemCreditos.subtract(valorImposto);

						BigDecimal valorCreditos = contaAtualizacao.getValorCreditos();

//							if (valorBolsaAguaConcedido != null && valorBolsaAguaConcedido.doubleValue() > 0) {
//								/**
//								 * Credito Bolsa egua sere validado antes de ser adicionado aos Creditos da
//								 * Conta
//								 * 
//								 * @author: Kurt Matheus Sampaio de Matos
//								 * @date: 03/06/2022
//								 */
//								if (!validarCreditoConcedido(valorCreditos, valorBolsaAguaConcedido)) {
//									valorCreditos = valorCreditos.add(valorBolsaAguaConcedido);
//									contaAtualizacao.setValorCreditos(valorCreditos);
//								}
//								atualizarValorCreditoBolsaAgua(helper.getAnoMesReferenciaPreFaturamento(),
//										helper.getImovel(), valorBolsaAguaConcedido, contaAtualizacao);
//							}

						logger.info(" 1 - Credito a Realizar: Imovel: "
								+ (contaAtualizacao.getImovel() != null ? contaAtualizacao.getImovel().getId()
										: "NULL")
								+ " | Valor creditos: " + contaAtualizacao.getValorCreditos());

						Collection indicadorRetransmissaoColecao = repositorioFaturamento
								.pesquisaIndicadorRetransmissaoMovimentoContaPF(
										contaAtualizacao.getImovel().getId(),
										helper.getFaturamentoGrupo().getAnoMesReferencia());
						Integer indicadorRetransmissao = null;
						if (!indicadorRetransmissaoColecao.isEmpty()) {
							Iterator indicadorIterator = indicadorRetransmissaoColecao.iterator();
							indicadorRetransmissao = (Integer) indicadorIterator.next();
						}
						System.out.println("ANTER AJUSTE");
						getControladorFaturamento().ajustarCobrancaContasValoresAbaixoMinimoEmissao(contaAtualizacao, sistemaParametro.getValorMinimoEmissaoConta());
						System.out.println("DEPOIS AJUSTE");
						if (valorCreditos.compareTo(valorTotalContaSemCreditos) == 1) {
							Imovel imovel = contaAtualizacao.getImovel();
							BigDecimal valorTotalCreditos = this.atualizarCreditoResidual(imovel,
									contaAtualizacao.getId(), helper.getFaturamentoGrupo().getAnoMesReferencia(),
									valorTotalContaSemCreditos, contaAtualizacao);
							contaAtualizacao.setValorCreditos(valorTotalCreditos);
							logger.info(" 2 - Credito a Realizar: Imovel: "
									+ (contaAtualizacao.getImovel() != null ? contaAtualizacao.getImovel().getId()
											: "NULL")
									+ " | Valor creditos: " + contaAtualizacao.getValorCreditos()
									+ " | Valor total creditos: " + valorTotalCreditos);

							/**
							 * Autor: Wellington Rocha Data: 30/08/2011
							 * 
							 * Caso a conta seja retransmitida o valor do credito residual neo sere
							 * atualizado novamente.
							 */
						} else if ((indicadorRetransmissao != null && indicadorRetransmissao.equals(2))
								&& (valorCreditos.compareTo(valorTotalContaSemCreditos) == 0
										|| valorCreditos.compareTo(valorTotalContaSemCreditos) == -1)) {

							logger.info(" 3 - Credito a Realizar: Imovel: "
									+ (contaAtualizacao.getImovel() != null ? contaAtualizacao.getImovel().getId()
											: "NULL")
									+ " | Valor creditos: " + contaAtualizacao.getValorCreditos());

							/**
							 * Autor: Adriana Muniz Data: 09/08/2011
							 * 
							 * Como os valores residuais dos creditos a realizar neo esteo mais sendo
							 * zerados no momento de geraeeo da rota, e necesserio zerar, esse valor
							 * caso seja diferente de zero, no retorno do IS
							 */
							// consulta todos os creditos com valor residual diferente de zero
							Collection colecaoCreditosARealizar = repositorioFaturamento
									.buscarCreditoARealizarPorImovelValorResidualDiferenteZero(
											contaAtualizacao.getImovel().getId());

							if (!colecaoCreditosARealizar.isEmpty() && colecaoCreditosARealizar != null) {

								logger.info(" 4 - Credito a Realizar: Imovel: "
										+ (contaAtualizacao.getImovel() != null
												? contaAtualizacao.getImovel().getId()
												: "NULL")
										+ " | Qtde de creditos com valor residual diferente de zero: "
										+ colecaoCreditosARealizar.size());

								Iterator creditoIterator = colecaoCreditosARealizar.iterator();

								while (creditoIterator.hasNext()) {
									CreditoARealizar credito = (CreditoARealizar) creditoIterator.next();

									credito.setValorResidualMesAnterior(ConstantesSistema.VALOR_ZERO);

									logger.info(" 5 - Credito a Realizar: Imovel: "
											+ (credito.getImovel() != null ? credito.getImovel().getId() : "NULL")
											+ " | Creditos: "
											+ (credito.getValorCredito() != null ? credito.getValorCredito()
													: "NULL")
											+ " | Residual Concedido no Mes: "
											+ (credito.getValorResidualConcedidoMes() != null
													? credito.getValorResidualConcedidoMes()
													: "NULL")
											+ " | Residual do Mes Anterior: "
											+ (credito.getValorResidualMesAnterior() != null
													? credito.getValorResidualMesAnterior()
													: "NULL"));

									repositorioFaturamento.atualizarCreditoARealizar(credito);
								}
							}
						}

						try {
							repositorioFaturamento.atualizarContaProcessoMOBILE(contaAtualizacao);
						} catch (ErroRepositorioException e) {
							throw new ControladorException("erro.sistema", e);
						}

						// Verfificar se o imevel e para ser faturado ou neo,
						// caso neo seja enteo deletar a conta.
						boolean faturar = true;
						if (contaAtualizacao.getLigacaoAguaSituacao() != null
								&& contaAtualizacao.getLigacaoAguaSituacao().getIndicadorFaturamentoSituacao()
										.equals(ConstantesSistema.NAO)
								&& contaAtualizacao.getLigacaoEsgotoSituacao() != null
								&& contaAtualizacao.getLigacaoEsgotoSituacao().getIndicadorFaturamentoSituacao()
										.equals(ConstantesSistema.NAO)
								&& contaAtualizacao.getDebitos().compareTo(BigDecimal.ZERO) == 0) {
							faturar = false;
						}

						BigDecimal valorMinimoEmissao = sistemaParametro.getValorMinimoEmissaoConta();
						// Caso o valor da conta seja menor que o valor menimo
						// permitido para a deleeeo da conta,
						// enteo deleta os dados da conta
						if (contaAtualizacao.getValorTotal().compareTo(valorMinimoEmissao) < 0 || !faturar) {
							if (contaAtualizacao.getValorCreditos().compareTo(BigDecimal.ZERO) == 0) {
								// Objeto que armazenare as informaeees para
								// deleeeo das contas
								ApagarDadosFaturamentoHelper helperApagarDadosFaturamento = new ApagarDadosFaturamentoHelper();
								helperApagarDadosFaturamento
										.setIdDebitoCreditoSituacaoAtual(DebitoCreditoSituacao.NORMAL);
								helperApagarDadosFaturamento.setIdImovel(helper.getImovel().getId());
								helperApagarDadosFaturamento
										.setAnoMesFaturamento(helper.getAnoMesReferenciaPreFaturamento());

								getControladorFaturamento().apagarDadosGeradosFaturarGrupoFaturamento(helperApagarDadosFaturamento,
										FaturamentoAtividade.FATURAR_GRUPO.intValue());

								// pula de imevel
								continue;
							}
						} else {
							Imovel imovel = contaAtualizacao.getImovel();
							if (imovel.getCodigoConvenio() != null) {
								getControladorFaturamento().registrarFichaCompensacao(contaAtualizacao.getId());
							}
						}

						boolean contaNaoImpressa = false;
						// Caso o valor da conta seja zero e o imevel neo tenha
						// credito,
						// enteo neo coloca em conta_impresseo
						if (contaAtualizacao.getValorTotal().compareTo(BigDecimal.ZERO) == 0) {
							if (contaAtualizacao.getValorCreditos().compareTo(BigDecimal.ZERO) == 0) {
								contaNaoImpressa = true;
							}
						}

						// Caso o indicador de emisseo de conta seja igual e neo
						// emitida
						if (helper.getIndicadorEmissaoConta().shortValue() == ConstantesSistema.NAO.shortValue()
								&& !contaNaoImpressa) {

							ContaImpressao contaImpressao = new ContaImpressao();
							contaImpressao.setId(helper.getConta().getId());
							ContaGeral contaGeral = new ContaGeral();
							contaGeral.setConta(contaAtualizacao);
							contaImpressao.setContaGeral(contaGeral);
							contaImpressao.setReferenciaConta(helper.getFaturamentoGrupo().getAnoMesReferencia());
							contaImpressao.setFaturamentoGrupo(helper.getFaturamentoGrupo());
							contaImpressao.setIndicadorImpressao(ConstantesSistema.NAO);
							contaImpressao.setUltimaAlteracao(new Date());

							/*
							 * 
							 * Caso esteja indicado no imevel que a conta deve ser entregue ao
							 * responsevel (ICTE_ID da tabela IMOVEL seja igual a 1 ou 3), e o imevel
							 * neo seja debito autometico( IMOV_ICDEBITOCONTA da tabela IMOVEL seja
							 * igual a 2), atribuir CLIE_ID da tabela CLIENTE_IMOVEL para IMOV_ID=Id da
							 * matrecula do imevel e CLIM_DTRELACAOFIM com o valor correspondente a nulo
							 * e CRTP_ID com o valor correspondente a responsevel da tabela
							 * CLIENTE_RELACAO_TIPO, caso contrerio atribuir o valor nulo.
							 */
							// CAERN se vai imprimir quando for enviar para o
							// cliente responsavel no final do grupo
							// helper.getImovel().getImovelContaEnvio().getId()
							// == ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL ||
							// helper.getImovel().getImovelContaEnvio().getId()
							// ==
							// ImovelContaEnvio.NAO_PAGAVEL_IMOVEL_PAGAVEL_RESPONSAVEL
							// ||

							boolean clienteResponsavel = false;
							if (sistemaParametro.getCodigoEmpresaFebraban()
									.equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAERN)) {
								if (helper.getImovel().getImovelContaEnvio() != null
										&& (helper.getImovel().getImovelContaEnvio().getId()
												.equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL_FINAL_GRUPO))) {
									clienteResponsavel = true;
								}
							} else {
								if (helper.getImovel().getImovelContaEnvio() != null
										&& (helper.getImovel().getImovelContaEnvio().getId()
												.equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL)
												|| helper.getImovel().getImovelContaEnvio().getId().equals(
														ImovelContaEnvio.NAO_PAGAVEL_IMOVEL_PAGAVEL_RESPONSAVEL)
												|| helper.getImovel().getImovelContaEnvio().getId().equals(
														ImovelContaEnvio.ENVIAR_CONTA_BRAILLE_RESPONSAVEL))) {
									clienteResponsavel = true;
								}
							}

							if (clienteResponsavel) {
								try {

									Integer idClienteResponsavel = (Integer) repositorioFaturamento
											.pesquisarClienteResponsavel(helper.getImovel().getId());

									if (idClienteResponsavel != null && !idClienteResponsavel.equals("")) {
										Cliente cliente = new Cliente();
										cliente.setId(idClienteResponsavel);
										contaImpressao.setClienteResponsavel(cliente);
									}
								} catch (ErroRepositorioException e) {
									throw new ControladorException("erro.sistema", e);
								}

							}

							/*
							 * 1.Caso id do cliente responsevel esteja preenchido (CLIE_IDRESPONSAVEL)
							 * atribuir o valor correspondente a conta de cliente responsevel da tabela
							 * CONTA_TIPO;
							 * 
							 * 2.Caso imevel seja debito autometico (IMOV_ICDEBITOCONTA da tabela
							 * IMOVEL seja igual a 1), atribuir o valor correspondente a conta debito
							 * autometico da tabela CONTA_TIPO;
							 * 
							 * Caso nenhuma das condieees acima tenha sido verdadeira atribuir o valor
							 * correspondente a conta normal da tabela CONTA_TIPO;
							 */

							ContaTipo contaTipo = new ContaTipo();

							if (contaImpressao.getClienteResponsavel() != null) {
								if (helper.getImovel().getIndicadorDebitoConta().equals(ConstantesSistema.SIM)) {
									contaTipo.setId(ContaTipo.CONTA_DEBITO_AUTO_COM_CLIENTE_RESP);
								} else {
									contaTipo.setId(ContaTipo.CONTA_CLIENTE_RESPONSAVEL);
								}

							} else if (helper.getImovel().getIndicadorDebitoConta().equals(ConstantesSistema.SIM)) {
								contaTipo.setId(ContaTipo.CONTA_DEBITO_AUTOMATICO);
							} else {
								contaTipo.setId(ContaTipo.CONTA_NORMAL);
							}

							contaImpressao.setContaTipo(contaTipo);

							/*
							 * Caso a empresa seja a COMPESA e Caso id do cliente responsevel esteja
							 * preenchido (CLIE_IDRESPONSAVEL) atribuir o valor nulo, caso contrerio
							 * atribuir e empresa associada e rota (EMPR_ID da tabela ROTA);
							 */
							if (sistemaParametro.getCodigoEmpresaFebraban()
									.equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_COMPESA)) {
								if (contaImpressao.getClienteResponsavel() == null) {
									contaImpressao
											.setEmpresa(helper.getImovel().getQuadra().getRota().getEmpresa());
								}
							} else {
								/*
								 * Caso contrerio, atribuir e empresa associada e rota (EMPR_ID da tabela
								 * ROTA);
								 */
								if (!sistemaParametro.getCodigoEmpresaFebraban()
										.equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAER)) {
									contaImpressao
											.setEmpresa(helper.getImovel().getQuadra().getRota().getEmpresa());
								}
							}
							/*
							 * 
							 * Valor total da conta (Soma do valor da egua + Soma do valor de esgoto +
							 * Valor de debitos da conta ? Valor de creditos da conta ? Soma do valor
							 * dos impostos)
							 */
							BigDecimal valorTotalConta = BigDecimal.ZERO;
							if (valorTotalMenosImposto != null) {
								valorTotalConta = valorTotalMenosImposto.subtract(valorImposto);
							}
							contaImpressao.setValorConta(valorTotalConta);

							this.getControladorBatch().inserirObjetoParaBatch(contaImpressao);

						}

						// Atualiza a forma do documento de cobranea se a conta foi
						// impressa
						if (helper.getIndicadorEmissaoConta().equals(ConstantesSistema.SIM)
								&& helper.getCobrancaDocumento() != null
								&& helper.getCobrancaDocumento().getId() != null) {

							repositorioCobranca
									.atualizarFormaEmissaoCobrancaDocumento(helper.getCobrancaDocumento().getId());
						}
					}
				}
			}

		} catch (Exception e) {
			System.out.println("MATRICULA IMOVEL QUE DEU ERRO:" + matriculaImovel);
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

	}

	private void atualizarValoresContaCategoriaFaixa(MovimentoContaPrefaturada helper,
			MovimentoContaPrefaturadaCategoria helperCategoria, ContaCategoria contaCategoria, Integer idSubcategoria)
			throws ControladorException {
		/*
		 * 3. Para cada registro do tipo 3, alterar na tabela
		 * CONTA_CATEGORIA_CONSUMO_FAIXA o seu correspondente (CNTA_ID = Conta do
		 * movimento em processamento e CATG_ID = cedigo da categoria do movimento e
		 * SCAT_ID = cedigo da subcategoria do movimento)
		 */
		FiltroMovimentoContaCategoriaConsumoFaixa filtroMovimentoContaCategoriaConsumoFaixa = new FiltroMovimentoContaCategoriaConsumoFaixa();

		filtroMovimentoContaCategoriaConsumoFaixa.adicionarParametro(new ParametroSimples(
				FiltroMovimentoContaCategoriaConsumoFaixa.MOVIMENTO_CONTA_PREFATURADA_ID,
				helper.getId()));

		filtroMovimentoContaCategoriaConsumoFaixa.adicionarParametro(
				new ParametroSimples(FiltroMovimentoContaCategoriaConsumoFaixa.CATEGORIA_ID,
						helperCategoria.getComp_id().getCategoria().getId()));

		filtroMovimentoContaCategoriaConsumoFaixa.adicionarParametro(new ParametroSimples(
				FiltroMovimentoContaCategoriaConsumoFaixa.SUBCATEGORIA_ID, idSubcategoria));

		Collection<MovimentoContaCategoriaConsumoFaixa> colMovimentoContaCategoriaConsumoFaixa = this
				.getControladorUtil().pesquisar(filtroMovimentoContaCategoriaConsumoFaixa,
						MovimentoContaCategoriaConsumoFaixa.class.getName());

		for (MovimentoContaCategoriaConsumoFaixa helperMovimentoContaCategoriaConsumoFaixa : colMovimentoContaCategoriaConsumoFaixa) {

			ContaCategoriaConsumoFaixa contaCategoriaConsumoFaixa = new ContaCategoriaConsumoFaixa();
			contaCategoriaConsumoFaixa
					.setCategoria(helperMovimentoContaCategoriaConsumoFaixa
							.getMovimentoContaPrefaturadaCategoria().getComp_id()
							.getCategoria());

			contaCategoriaConsumoFaixa
					.setSubcategoria(helperMovimentoContaCategoriaConsumoFaixa
							.getMovimentoContaPrefaturadaCategoria().getComp_id()
							.getSubcategoria());

			contaCategoriaConsumoFaixa.setContaCategoria(contaCategoria);

			contaCategoriaConsumoFaixa
					.setValorAgua(helperMovimentoContaCategoriaConsumoFaixa
							.getValorFaturadoAguaNaFaixa());
			contaCategoriaConsumoFaixa
					.setConsumoAgua(helperMovimentoContaCategoriaConsumoFaixa
							.getConsumoFaturadoAguaNaFaixa());
			contaCategoriaConsumoFaixa
					.setValorEsgoto(helperMovimentoContaCategoriaConsumoFaixa
							.getValorFaturadoEsgotoNaFaixa());
			contaCategoriaConsumoFaixa
					.setConsumoEsgoto(helperMovimentoContaCategoriaConsumoFaixa
							.getConsumoFaturadoEsgotoNaFaixa());
			contaCategoriaConsumoFaixa
					.setConsumoFaixaInicio(helperMovimentoContaCategoriaConsumoFaixa
							.getLimiteInicialConsumoNaFaixa());
			contaCategoriaConsumoFaixa
					.setConsumoFaixaFim(helperMovimentoContaCategoriaConsumoFaixa
							.getLimiteFinalConsumoNaFaixa());
			contaCategoriaConsumoFaixa.setValorTarifaFaixa(
					helperMovimentoContaCategoriaConsumoFaixa.getValorTarifaNaFaixa());
			contaCategoriaConsumoFaixa.setUltimaAlteracao(new Date());

			this.getControladorBatch().inserirObjetoParaBatch(contaCategoriaConsumoFaixa);
		}
	}

	private void atualizarValoresContaCategoria(Conta contaAtualizacao,
			MovimentoContaPrefaturadaCategoria helperCategoria, ContaCategoria contaCategoria)
			throws ControladorException {
		ContaCategoriaPK contaCategoriaPK = null;
		contaCategoriaPK = new ContaCategoriaPK();
		contaCategoriaPK.setConta(contaAtualizacao);
		contaCategoriaPK.setCategoria(helperCategoria.getComp_id().getCategoria());
		contaCategoriaPK.setSubcategoria(helperCategoria.getComp_id().getSubcategoria());
		contaCategoria.setComp_id(contaCategoriaPK);
		contaCategoria.setValorAgua(helperCategoria.getValorFaturadoAgua());
		contaCategoria.setConsumoAgua(helperCategoria.getConsumoFaturadoAgua());
		contaCategoria.setValorEsgoto(helperCategoria.getValorFaturadoEsgoto());
		contaCategoria.setConsumoEsgoto(helperCategoria.getConsumoFaturadoEsgoto());
		contaCategoria.setValorTarifaMinimaAgua(helperCategoria.getValorTarifaMinimaAgua());
		contaCategoria.setConsumoMinimoAgua(helperCategoria.getConsumoMinimoAgua());
		contaCategoria
				.setValorTarifaMinimaEsgoto(helperCategoria.getValorTarifaMinimaEsgoto());
		contaCategoria.setConsumoMinimoEsgoto(helperCategoria.getConsumoMinimoEsgoto());
		contaCategoria.setUltimaAlteracao(new Date());							
 
		this.atualizarValorContaCategoriaBolsaAguaMovimentoCelular(contaCategoria, contaAtualizacao);
		try {
			repositorioFaturamento.atualizarContaCategoriaProcessoMOBILE(contaCategoria);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	public void atualizarValorContaCategoriaBolsaAguaMovimentoCelular(ContaCategoria contaCategoria, Conta conta)
			throws ControladorException {
		if (getControladorImovel().isContaBolsaAgua(conta.getId())) {
			if (contaCategoria.getCategoria().isResidencial()) {
				BigDecimal valorCredito = new BigDecimal("0");
				BigDecimal valorCreditoAtualizado = new BigDecimal("0");
				CreditoARealizar creditoARealizar = new CreditoARealizar();
				CreditoRealizado creditoRealizado = new CreditoRealizado();
				String nomePacoteObjeto = CreditoRealizado.class.getName();
				FiltroCreditoRealizado filtroCreditoRealizado = new FiltroCreditoRealizado();
				filtroCreditoRealizado
						.adicionarParametro(new ParametroSimples(FiltroCreditoRealizado.CONTA_ID, conta.getId()));
				Collection<CreditoRealizado> creditosRealizados = getControladorUtil().pesquisar(filtroCreditoRealizado,
						nomePacoteObjeto);
				
				for (CreditoRealizado realizado : creditosRealizados) {
					valorCredito = new BigDecimal("0");
					creditoARealizar = getControladorFaturamento()
							.pesquisarCreditoARealizar(realizado.getCreditoARealizarGeral().getId());
					if (creditoARealizar.isCreditoBolsaAgua()) {
						try {

							System.out.println("ATUALIZANDO CONTA CATEGORIA - BOLSA AGUA ["
									+ contaCategoria.getConta().getImovel().getId() + "]");					
							valorCredito = getControladorFaturamento().calcularValorCreditoBolsaAguaAtualizado(contaCategoria, creditoARealizar,
									realizado, true);
							System.out
									.println("FIM ATUALIZANDO CREDITO A REALIZAR E CREDITO REALIZADO - BOLSA AGUA ["
											+ contaCategoria.getConta().getImovel().getId() + "]");

						} catch (ControladorException e) {
							throw new ControladorException(
									"Erro ao atualizar valor da conta para imoveis bolsa agua", e);
						}
					} else {
						valorCredito = realizado.getValorCredito();
					}
					valorCreditoAtualizado = valorCreditoAtualizado.add(valorCredito);
				}

				conta.setValorCreditos(valorCreditoAtualizado);
				getControladorUtil().atualizar(conta);
				System.out.println("FIM ATUALIZANDO CONTA - BOLSA AGUA [" + conta.getImovel().getId() + "]");
			}
		}
	}

	/**
	 * 
	 * [UC0840] - Atualizar Faturamento do Movimento Celular
	 * 
	 * @author Raphael Rossiter
	 * @date 20/07/2011
	 * 
	 * @param conta
	 * @param consumoAguaMovimentoCelular
	 * @param consumoAguaGSAN
	 * @param consumoEsgotoMovimentoCelular
	 * @param consumoEsgotoGSAN
	 * @throws ControladorException
	 */
	public void atualizarConsumoMovimentoCelular(Conta conta, Integer consumoAguaMovimentoCelular,
			Integer consumoAguaGSAN, Integer consumoEsgotoMovimentoCelular, Integer consumoEsgotoGSAN)
			throws ControladorException {

		if (conta != null && conta.getId() != null) {

			// Verifica se o imevel este associado a um imevel condomenio
			Integer idImovelCondominio = this.getControladorImovel()
					.pesquisarImovelCondominio(conta.getImovel().getId());

			// eGUA
			if (consumoAguaMovimentoCelular.intValue() != consumoAguaGSAN.intValue()) {

				MovimentoContaPrefaturada movimentoContaPrefaturadaAgua = null;

				try {
					movimentoContaPrefaturadaAgua = repositorioFaturamento
							.pesquisarMovimentoContaPrefaturadaPorIdConta(conta.getId(), MedicaoTipo.LIGACAO_AGUA);
				} catch (ErroRepositorioException ex) {
					ex.printStackTrace();
					throw new ControladorException("erro.sistema", ex);
				}

				if (movimentoContaPrefaturadaAgua != null) {

					try {

						Integer idConsumoHistoricoAguaMacro = null;
						Integer consumoImovelVinculadosCondominioAgua = null;

						if (movimentoContaPrefaturadaAgua.getConsumoRateioAgua() != null) {

							if (idImovelCondominio != null) {

								Object[] dadosConsumoHistoricoAguaCondominio = this.getControladorMicromedicao()
										.obterConsumoLigacaoAguaOuEsgotoDoImovel(idImovelCondominio,
												conta.getReferencia(), LigacaoTipo.LIGACAO_AGUA);

								if (dadosConsumoHistoricoAguaCondominio != null) {

									// id do consumo historico do imevel macro
									idConsumoHistoricoAguaMacro = (Integer) dadosConsumoHistoricoAguaCondominio[0];

									consumoImovelVinculadosCondominioAgua = movimentoContaPrefaturadaAgua
											.getConsumoMedido();

									if (consumoImovelVinculadosCondominioAgua == null
											&& movimentoContaPrefaturadaAgua.getConsumoCobrado() != null) {

										consumoImovelVinculadosCondominioAgua = movimentoContaPrefaturadaAgua
												.getConsumoCobrado()
												- movimentoContaPrefaturadaAgua.getConsumoRateioAgua();
									}
								}
							}
						}

						repositorioFaturamento.atualizarMedicaoHistoricoMovimentoCelular(movimentoContaPrefaturadaAgua);

						repositorioFaturamento.atualizarConsumoHistoricoMovimentoCelular(movimentoContaPrefaturadaAgua,
								consumoAguaMovimentoCelular, idConsumoHistoricoAguaMacro,
								consumoImovelVinculadosCondominioAgua);

					} catch (ErroRepositorioException ex) {
						ex.printStackTrace();
						throw new ControladorException("erro.sistema", ex);
					}
				}
			}

			// ESGOTO
			if (consumoEsgotoMovimentoCelular.intValue() != consumoEsgotoGSAN.intValue()) {

				MovimentoContaPrefaturada movimentoContaPrefaturadaEsgoto = null;

				try {

					movimentoContaPrefaturadaEsgoto = repositorioFaturamento
							.pesquisarMovimentoContaPrefaturadaPorIdConta(conta.getId(), MedicaoTipo.POCO);

				} catch (ErroRepositorioException ex) {
					ex.printStackTrace();
					throw new ControladorException("erro.sistema", ex);
				}

				if (movimentoContaPrefaturadaEsgoto != null) {

					try {

						Integer idConsumoHistoricoEsgotoMacro = null;
						Integer consumoImovelVinculadosCondominioEsgoto = null;

						if (movimentoContaPrefaturadaEsgoto.getConsumoRateioEsgoto() != null) {

							if (idImovelCondominio != null) {

								Object[] dadosConsumoHistoricoEsgotoCondominio = this.getControladorMicromedicao()
										.obterConsumoLigacaoAguaOuEsgotoDoImovel(idImovelCondominio,
												conta.getReferencia(), LigacaoTipo.LIGACAO_ESGOTO);

								if (dadosConsumoHistoricoEsgotoCondominio != null) {

									// id do consumo historico do imevel macro
									idConsumoHistoricoEsgotoMacro = (Integer) dadosConsumoHistoricoEsgotoCondominio[0];

									consumoImovelVinculadosCondominioEsgoto = movimentoContaPrefaturadaEsgoto
											.getConsumoMedido();

									if (consumoImovelVinculadosCondominioEsgoto == null
											&& movimentoContaPrefaturadaEsgoto.getConsumoCobrado() != null) {

										consumoImovelVinculadosCondominioEsgoto = movimentoContaPrefaturadaEsgoto
												.getConsumoCobrado()
												- movimentoContaPrefaturadaEsgoto.getConsumoRateioEsgoto();
									}
								}
							}
						}

						repositorioFaturamento
								.atualizarMedicaoHistoricoMovimentoCelular(movimentoContaPrefaturadaEsgoto);

						repositorioFaturamento.atualizarConsumoHistoricoMovimentoCelular(
								movimentoContaPrefaturadaEsgoto, consumoEsgotoMovimentoCelular,
								idConsumoHistoricoEsgotoMacro, consumoImovelVinculadosCondominioEsgoto);

					} catch (ErroRepositorioException ex) {
						ex.printStackTrace();
						throw new ControladorException("erro.sistema", ex);
					}
				}
			}
		}
	}
	
	/**
	 * [UC0113] Faturar Grupo de Faturamento
	 * 
	 * [SB0005] - Gerar os Creditos Realizados
	 * 
	 * @author Sevio Luiz
	 * @date 27/10/2009
	 * 
	 * @param imovel
	 * @param faturamentoGrupo
	 * @param helperValoresAguaEsgoto
	 * @param valorTotalDebitos
	 * @param gerarAtividadeGrupoFaturamento
	 * @return GerarCreditoRealizadoHelper
	 * @throws ControladorException
	 */
	private BigDecimal atualizarCreditoResidual(Imovel imovel, Integer idConta, Integer anoMesFaturamento,
			BigDecimal valorTotalContaSemCredito, Conta contaAtualizacao) throws ControladorException {
		BigDecimal valorTotalCreditos = BigDecimal.ZERO;

		try {
			Collection colecaoCreditoRealizado = repositorioFaturamento.pesquisarCreditosRealizados(idConta);

			/**
			 * Autor: Adriana Muniz Data: 20/07/2011
			 * 
			 * Alteraeeo para atender creditos com uma prestaeeo
			 */
			List<Integer> idCreditosARealizarVerificados = new ArrayList<Integer>();

			if (colecaoCreditoRealizado != null && !colecaoCreditoRealizado.isEmpty()) {
				Iterator iteratorColecaoCreditosRealizados = colecaoCreditoRealizado.iterator();
				boolean deletaCreditoRealizado = false;
				CreditoRealizado creditoRealizado = null;
				BigDecimal valorTotalACobrar = valorTotalContaSemCredito;
				boolean verificouBolsaAgua = false;

				while (iteratorColecaoCreditosRealizados.hasNext()) {
					creditoRealizado = (CreditoRealizado) iteratorColecaoCreditosRealizados.next();

					Collection colecaoCreditosARealizar = getControladorFaturamento().obterCreditoARealizarDadosCreditoRealizadoAntigo(
							imovel.getId(), DebitoCreditoSituacao.NORMAL, anoMesFaturamento, creditoRealizado);
					CreditoRealizado creditoBolsaAgua = getControladorFaturamento().obterCreditoBolsaAgua(colecaoCreditoRealizado);

					BigDecimal valorCorrespondenteParcelaMes = ConstantesSistema.VALOR_ZERO;
					BigDecimal valorCredito = ConstantesSistema.VALOR_ZERO;
					BigDecimal valorConta = ConstantesSistema.VALOR_ZERO;

					if (creditoBolsaAgua != null && !verificouBolsaAgua) {

						valorTotalACobrar = valorTotalACobrar.subtract(creditoBolsaAgua.getValorCredito());

						CreditoARealizar creditoARealizar = creditoBolsaAgua.getCreditoARealizarGeral().getCreditoARealizar();
						logger.info(
								" 0 - Credito a Realizar BOLSA eGUA: Imovel (atualizarCreditoResidual): "
										+ (creditoARealizar
												.getImovel() != null ? creditoARealizar.getImovel().getId() : "NULL")
										+ " | Creditos: "
										+ (creditoARealizar
												.getValorCredito() != null ? creditoARealizar.getValorCredito() : "NULL")
										+ " | Residual Concedido no Mes: "
										+ (creditoARealizar
												.getValorResidualConcedidoMes() != null ? creditoARealizar
														.getValorResidualConcedidoMes() : "NULL")
										+ " | Residual Concedido no Mes Anterior: "
										+ (creditoARealizar
												.getValorResidualMesAnterior() != null
														? creditoARealizar.getValorResidualMesAnterior()
														: "NULL"));

						creditoBolsaAgua.setUltimaAlteracao(new Date());
						getControladorUtil().atualizar(creditoRealizado);

						// atualiza o credito realizado categoria
						this.atualizarCreditoRealizadoCategoria(
								creditoARealizar, creditoBolsaAgua);
						
						creditoARealizar.setAnoMesReferenciaPrestacao(anoMesFaturamento);

						getControladorUtil()
								.atualizar(creditoARealizar);

						valorTotalCreditos = valorTotalCreditos.add(creditoBolsaAgua.getValorCredito());

						verificouBolsaAgua = true;

						if (valorTotalACobrar.compareTo(ConstantesSistema.VALOR_ZERO) == 0) {
							deletaCreditoRealizado = true;
						}

					}

					if (colecaoCreditosARealizar != null && !colecaoCreditosARealizar.isEmpty()
							&& valorTotalACobrar.compareTo(BigDecimal.ZERO) == 1) {

						Iterator iteratorColecaoCreditosARealizar = colecaoCreditosARealizar.iterator();

						CreditoARealizar creditoARealizar = null;

						while (iteratorColecaoCreditosARealizar.hasNext()) {
							creditoARealizar = (CreditoARealizar) iteratorColecaoCreditosARealizar.next();

							if (creditoARealizar.isCreditoBolsaAgua()) {
								continue;
							}

							if (!idCreditosARealizarVerificados.contains(creditoARealizar.getId())) {
								idCreditosARealizarVerificados.add(creditoARealizar.getId());

								if (!deletaCreditoRealizado
										|| idCreditosARealizarVerificados.contains(creditoARealizar.getId())) {
									valorCorrespondenteParcelaMes = ConstantesSistema.VALOR_ZERO;
									valorCredito = ConstantesSistema.VALOR_ZERO;
									valorConta = ConstantesSistema.VALOR_ZERO;

									/**
									 * 
									 * Data: 01/07/2011 autor: Adriana Muniz
									 * 
									 * Alteraeeo para atender casos de creditos com apenas uma prestaeeo e
									 * que seo consumidos conforme o valor da conta a ate ser concedido totalmente
									 */
									if (creditoARealizar.getNumeroPrestacaoCredito() == 1) {
										BigDecimal valorResidual = ConstantesSistema.VALOR_ZERO;

										if (valorTotalCreditos.compareTo(valorTotalContaSemCredito) == -1) {

											if (creditoARealizar.getValorResidualMesAnterior()
													.compareTo(ConstantesSistema.VALOR_ZERO) == 0) {
												valorConta = valorTotalACobrar;
												valorTotalACobrar = valorTotalACobrar
														.subtract(creditoARealizar.getValorCredito());
												valorResidual = creditoARealizar.getValorCredito();
											} else {
												valorConta = valorTotalACobrar;
												valorTotalACobrar = valorTotalACobrar
														.subtract(creditoARealizar.getValorResidualMesAnterior());
												valorResidual = creditoARealizar.getValorResidualMesAnterior();
											}
											BigDecimal valorCreditoConcedido = ConstantesSistema.VALOR_ZERO;
											// valorCredito = creditoARealizar.getValorCredito();

											if (valorTotalACobrar.compareTo(ConstantesSistema.VALOR_ZERO) == -1) {

												creditoARealizar.setValorResidualMesAnterior(
														valorTotalACobrar.multiply(new BigDecimal("-1")));

												valorCreditoConcedido = valorResidual
														.subtract(creditoARealizar.getValorResidualMesAnterior());

												/**
												 * @author Adriana Muniz e Wellington Rocha
												 * @date 30/08/2012 Atualizaeeo da data da ultima alteraeeo do
												 *       credito realizado e atualizaeeo do credito realizado
												 *       categoria
												 */
												// atualiza o credito realizado
												creditoRealizado.setValorCredito(valorCreditoConcedido);
												creditoRealizado.setUltimaAlteracao(new Date());
												getControladorUtil().atualizar(creditoRealizado);

												// atualiza o credito realizado categoria
												this.atualizarCreditoRealizadoCategoria(creditoARealizar,
														creditoRealizado);

												// atualiza o credito a realizar
												repositorioFaturamento.atualizarCreditoARealizar(creditoARealizar);

												// Acumula o valor do credito
												valorTotalCreditos = valorTotalCreditos.add(valorCreditoConcedido);

												if (valorTotalCreditos.compareTo(valorTotalContaSemCredito) == 0) {
													valorTotalACobrar = ConstantesSistema.VALOR_ZERO;

												}
											} else {
												BigDecimal valorConcedido = valorConta.subtract(valorTotalACobrar);

												if (valorConcedido.compareTo(ConstantesSistema.VALOR_ZERO) == -1)
													valorConcedido = valorConcedido.multiply(new BigDecimal("-1"));

												if (valorConcedido.compareTo(valorConta) == -1)
													creditoARealizar
															.setValorResidualMesAnterior(ConstantesSistema.VALOR_ZERO);
												else {
													if (valorResidual.compareTo(ConstantesSistema.VALOR_ZERO) == 0)
														creditoARealizar.setValorResidualMesAnterior(creditoARealizar
																.getValorCredito().subtract(valorConcedido));
													else
														creditoARealizar.setValorResidualMesAnterior(
																valorResidual.subtract(valorConcedido));

												}

												logger.info(
														" 1 - Credito a Realizar: Imovel (atualizarCreditoResidual): "
																+ (creditoARealizar.getImovel() != null
																		? creditoARealizar.getImovel().getId()
																		: "NULL")
																+ " | Creditos: "
																+ (creditoARealizar.getValorCredito() != null
																		? creditoARealizar.getValorCredito()
																		: "NULL")
																+ " | Residual Concedido no Mes: "
																+ (creditoARealizar
																		.getValorResidualConcedidoMes() != null
																				? creditoARealizar
																						.getValorResidualConcedidoMes()
																				: "NULL")
																+ " | Residual Concedido no Mes Anterior: "
																+ (creditoARealizar
																		.getValorResidualMesAnterior() != null
																				? creditoARealizar
																						.getValorResidualMesAnterior()
																				: "NULL"));

												repositorioFaturamento.atualizarCreditoARealizar(creditoARealizar);
												// Acumula o valor do credito
												valorTotalCreditos = valorTotalCreditos.add(valorConcedido);

												/**
												 * @author Adriana Muniz e Wellington Rocha
												 * @date 30/08/2012 Atualizaeeo da data da ultima alteraeeo do
												 *       credito realizado e atualizaeeo do credito realizado
												 *       categoria
												 */
												// atualiza o credito realizado
												creditoRealizado.setValorCredito(valorConcedido);
												creditoRealizado.setUltimaAlteracao(new Date());
												getControladorUtil().atualizar(creditoRealizado);

												// atualiza o credito realizado categoria
												this.atualizarCreditoRealizadoCategoria(creditoARealizar,
														creditoRealizado);

												// Acrescentado no dia 26/08/2011
												if (valorTotalCreditos.compareTo(valorTotalContaSemCredito) == 0) {
													valorTotalACobrar = ConstantesSistema.VALOR_ZERO;

												}
											}

										} else {
											creditoARealizar
													.setValorResidualMesAnterior(creditoARealizar.getValorCredito());
											logger.info(" 2 - Credito a Realizar: Imovel (atualizarCreditoResidual): "
													+ (creditoARealizar.getImovel() != null
															? creditoARealizar.getImovel().getId()
															: "NULL")
													+ " | Creditos: "
													+ (creditoARealizar.getValorCredito() != null
															? creditoARealizar.getValorCredito()
															: "NULL")
													+ " | Residual Concedido no Mes: "
													+ (creditoARealizar.getValorResidualConcedidoMes() != null
															? creditoARealizar.getValorResidualConcedidoMes()
															: "NULL")
													+ " | Residual Concedido no Mes Anterior: "
													+ (creditoARealizar.getValorResidualMesAnterior() != null
															? creditoARealizar.getValorResidualMesAnterior()
															: "NULL"));

											// atualiza o credito a realizar
											repositorioFaturamento.atualizarCreditoARealizar(creditoARealizar);
										}
									} else {

										int numeroPrestacoesRealizadas = creditoARealizar.getNumeroPrestacaoRealizada()
												.intValue() - 1;

										Short numeroParcelaBonus = 0;
										if (creditoARealizar.getNumeroParcelaBonus() != null) {
											numeroParcelaBonus = creditoARealizar.getNumeroParcelaBonus();
										}

										if (numeroPrestacoesRealizadas < (creditoARealizar.getNumeroPrestacaoCredito()
												.intValue() - numeroParcelaBonus.intValue())) {

											valorCorrespondenteParcelaMes = creditoARealizar.getValorCredito().divide(
													new BigDecimal(creditoARealizar.getNumeroPrestacaoCredito()), 2,
													BigDecimal.ROUND_HALF_UP);

											if (numeroPrestacoesRealizadas == ((creditoARealizar
													.getNumeroPrestacaoCredito().intValue()
													- numeroParcelaBonus.intValue()) - 1)) {

												BigDecimal valorMesVezesPrestacaoCredito = valorCorrespondenteParcelaMes
														.multiply(new BigDecimal(
																creditoARealizar.getNumeroPrestacaoCredito()))
														.setScale(2);

												BigDecimal parte11 = valorCorrespondenteParcelaMes
														.add(creditoARealizar.getValorCredito());

												BigDecimal parte22 = parte11.subtract(valorMesVezesPrestacaoCredito);

												valorCorrespondenteParcelaMes = parte22;
											}

										}

										valorCredito = valorCorrespondenteParcelaMes
												.add(creditoARealizar.getValorResidualMesAnterior());

										valorTotalACobrar = valorTotalACobrar.subtract(valorCredito);

										if (valorTotalACobrar.compareTo(ConstantesSistema.VALOR_ZERO) == -1) {

											creditoARealizar.setValorResidualMesAnterior(
													valorTotalACobrar.multiply(new BigDecimal("-1")));

											valorCredito = valorCredito
													.subtract(creditoARealizar.getValorResidualMesAnterior());

											valorTotalACobrar = ConstantesSistema.VALOR_ZERO;

											logger.info(
													" 3 - Credito a Realizar: Imovel (atualizarCreditoResidual): "
															+ (creditoARealizar.getImovel() != null ? creditoARealizar
																	.getImovel().getId() : "SEM IMeVEL...")
															+ " | Creditos: "
															+ (creditoARealizar.getValorCredito() != null
																	? creditoARealizar.getValorCredito()
																	: "NULL")
															+ " | Residual Concedido no Mes: "
															+ (creditoARealizar.getValorResidualConcedidoMes() != null
																	? creditoARealizar.getValorResidualConcedidoMes()
																	: "NULL")
															+ " | Residual Concedido no Mes Anterior: "
															+ (creditoARealizar.getValorResidualMesAnterior() != null
																	? creditoARealizar.getValorResidualMesAnterior()
																	: "NULL"));

											// atualiza o credito a realizar
											repositorioFaturamento.atualizarCreditoARealizar(creditoARealizar);

											/**
											 * @author Adriana Muniz e Wellington Rocha
											 * @date 30/08/2012 Atualizaeeo da data da ultima alteraeeo do
											 *       credito realizado e atualizaeeo do credito realizado categoria
											 */
											// atualiza o credito realizado
											creditoRealizado.setValorCredito(valorCredito);
											creditoRealizado.setUltimaAlteracao(new Date());
											getControladorUtil().atualizar(creditoRealizado);

											// atualiza o credito realizado categoria
											this.atualizarCreditoRealizadoCategoria(creditoARealizar, creditoRealizado);

										} else {
											creditoARealizar.setValorResidualMesAnterior(ConstantesSistema.VALOR_ZERO);

											logger.info(" 4 - Credito a Realizar: Imovel (atualizarCreditoResidual): "
													+ (creditoARealizar.getImovel() != null
															? creditoARealizar.getImovel().getId()
															: "NULL")
													+ " | Creditos: "
													+ (creditoARealizar.getValorCredito() != null
															? creditoARealizar.getValorCredito()
															: "NULL")
													+ " | Residual Concedido no Mes: "
													+ (creditoARealizar.getValorResidualConcedidoMes() != null
															? creditoARealizar.getValorResidualConcedidoMes()
															: "NULL")
													+ " | Residual Concedido no Mes Anterior: "
													+ (creditoARealizar.getValorResidualMesAnterior() != null
															? creditoARealizar.getValorResidualMesAnterior()
															: "NULL"));

											repositorioFaturamento.atualizarCreditoARealizar(creditoARealizar);
										}
										valorTotalCreditos = valorTotalCreditos.add(valorCredito);
									}

								} else {
									// atualiza o credito a realizar

									/**
									 * 
									 * Para neo subtrair a prestaeeo do credito, caso o credito seja apenas
									 * de uma parcela
									 */
									if (creditoARealizar.getNumeroPrestacaoCredito() != 1) {
										// Atualiza o ne de prestaeees realizadas
										creditoARealizar.setNumeroPrestacaoRealizada(new Short(
												(creditoARealizar.getNumeroPrestacaoRealizada().intValue() - 1) + ""));
									}
									// anoMes da prestaeeo sere o anaMes de
									// referencia da conta
									creditoARealizar.setAnoMesReferenciaPrestacao(null);

									logger.info(" 5 - Credito a Realizar: Imovel (atualizarCreditoResidual): "
											+ (creditoARealizar.getImovel() != null
													? creditoARealizar.getImovel().getId()
													: "NULL")
											+ " | Creditos: "
											+ (creditoARealizar.getValorCredito() != null
													? creditoARealizar.getValorCredito()
													: "NULL")
											+ " | Residual Concedido no Mes: "
											+ (creditoARealizar.getValorResidualConcedidoMes() != null
													? creditoARealizar.getValorResidualConcedidoMes()
													: "NULL")
											+ " | Residual Concedido no Mes Anterior: "
											+ (creditoARealizar.getValorResidualMesAnterior() != null
													? creditoARealizar.getValorResidualMesAnterior()
													: "NULL"));

									repositorioFaturamento.atualizarCreditoARealizar(creditoARealizar);
								}

							} /*
								 * fim laeo que verifica se o credito a realizar je foi analisado
								 * 
								 * if (creditoARealizar.getCreditoTipo().getId().equals(CreditoTipo.
								 * CREDITO_BOLSA_AGUA)) { Filtro filtro = new FiltroCreditoARealizar();
								 * filtro.adicionarParametro(new ParametroSimples(FiltroCreditoARealizar.ID,
								 * creditoARealizar.getId()));
								 * 
								 * CreditoARealizar creditoParaAtualizar = (CreditoARealizar)
								 * Util.retonarObjetoDeColecao(getControladorUtil().pesquisar(filtro,
								 * CreditoARealizar.class.getName()));
								 * 
								 * BigDecimal novoValorCredito = creditoARealizar.getValorCredito()
								 * .subtract(creditoARealizar.getValorResidualMesAnterior());
								 * 
								 * if (contaAtualizacao.getValorDebitos().compareTo(BigDecimal.ZERO) == 1) {
								 * novoValorCredito =
								 * novoValorCredito.subtract(contaAtualizacao.getValorDebitos());
								 * contaAtualizacao.setValorCreditos(novoValorCredito); }
								 * 
								 * creditoParaAtualizar.setValorCredito(novoValorCredito);
								 * creditoParaAtualizar.setValorResidualMesAnterior(BigDecimal.ZERO);
								 * getControladorUtil().atualizar(creditoParaAtualizar);
								 * 
								 * logger.info(" 6 - Credito a Realizar: Imovel (atualizarCreditoResidual): " +
								 * (creditoARealizar.getImovel() != null ? creditoARealizar.getImovel().getId()
								 * : "NULL") + " | Creditos: " + (creditoARealizar.getValorCredito() != null ?
								 * creditoARealizar.getValorCredito() : "NULL" ) +
								 * " | Residual Concedido no Mes: " +
								 * (creditoARealizar.getValorResidualConcedidoMes() != null ?
								 * creditoARealizar.getValorResidualConcedidoMes() : "NULL") +
								 * " | Residual Concedido no Mes Anterior: " +
								 * (creditoARealizar.getValorResidualMesAnterior() != null ?
								 * creditoARealizar.getValorResidualMesAnterior() : "NULL")); }
								 */
						} // fim laeo de credito a realizar
					}

					if (deletaCreditoRealizado && !creditoRealizado.isCreditoBolsaAgua()) {
						// deleta o credito realizado categoria
						repositorioFaturamento.deletarCreditoRealizadoCategoria(creditoRealizado.getId());

						// deleta o credito realizado
						getControladorBatch().removerObjetoParaBatchSemTransacao(creditoRealizado);
					}

					if (valorTotalACobrar.compareTo(ConstantesSistema.VALOR_ZERO) == 0) {
						deletaCreditoRealizado = true;
					}
				}
			}

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		return valorTotalCreditos;
	}
	
	/**
	 * @author Adriana Muniz e Wellington Rocha
	 * @date 30/08/2012
	 * 
	 * @param creditoARealizar
	 * @param creditoRealizado
	 */
	public void atualizarCreditoRealizadoCategoria(CreditoARealizar creditoARealizar,
			CreditoRealizado creditoRealizado) {
		try {
			// Pesquisa os creditos a realizar categoria
			Collection colecaoCreditoARealizarCategoria = getControladorFaturamento().obterCreditoRealizarCategoria(creditoARealizar.getId());

			Iterator colecaoCreditoARealizarCategoriaIterator = colecaoCreditoARealizarCategoria.iterator();

			Collection colecaoCategorias = new ArrayList();

			// Laeo para recuperar as categorias do credito a realizar
			while (colecaoCreditoARealizarCategoriaIterator.hasNext()) {
				CreditoARealizarCategoria creditoARealizarCategoria = (CreditoARealizarCategoria) colecaoCreditoARealizarCategoriaIterator
						.next();
				Categoria categoria = new Categoria();
				categoria.setId(creditoARealizarCategoria.getCategoria().getId());
				categoria.setQuantidadeEconomiasCategoria(creditoARealizarCategoria.getQuantidadeEconomia());
				colecaoCategorias.add(categoria);
			}

			// obter o valor do credito pra cada categoria
			Collection colecaoCategoriasEValores = getControladorFaturamento().obterValorPorCategoria(colecaoCategorias,
					creditoRealizado.getValorCredito());

			Collection creditosRealizadosCategoria = repositorioFaturamento
					.pesquisarCreditoRealizadoCategoria(creditoRealizado.getId());

			Iterator creditosRealizadosCategoriaIterator = creditosRealizadosCategoria.iterator();

			while (creditosRealizadosCategoriaIterator.hasNext()) {

				CreditoRealizadoCategoria creditoRealizadoCategoria = (CreditoRealizadoCategoria) creditosRealizadosCategoriaIterator
						.next();

				Iterator colecaoCategoriasEValoresIterator = colecaoCategoriasEValores.iterator();
				while (colecaoCategoriasEValoresIterator.hasNext()) {
					Object[] categoriaEValor = (Object[]) colecaoCategoriasEValoresIterator.next();

					if (categoriaEValor[0].equals(creditoRealizadoCategoria.getCategoria().getId())) {
						creditoRealizadoCategoria.setValorCategoria((BigDecimal) categoriaEValor[1]);
						creditoRealizadoCategoria.setUltimaAlteracao(new Date());
					}
				}
				// atualiza o credito realizado categoria
				getControladorUtil().atualizar(creditoRealizadoCategoria);
			}

		} catch (ControladorException e) {
			e.printStackTrace();
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * [UC0820] Atualizar Faturamento do Movimento Celular [SB002] Incluir Medicao
	 * 
	 * @param movimentoContaPreFaturada
	 * @param rota
	 * @throws ErroRepositorioException
	 */
	private void incluirMedicaoHistorico(MovimentoContaPrefaturada movimentoContaPreFaturada)
			throws ErroRepositorioException, ControladorException {

		MedicaoHistorico medicaoHistorico = this.verificarExistenciaHistoricoMedicao(movimentoContaPreFaturada);

		if (medicaoHistorico == null) {

			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();

			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua.hidrometroInstalacaoHistorico");
			filtroImovel.adicionarParametro(
					new ParametroSimples(FiltroImovel.ID, movimentoContaPreFaturada.getImovel().getId()));

			Imovel imovel = (Imovel) Util
					.retonarObjetoDeColecao(this.getControladorUtil().pesquisar(filtroImovel, Imovel.class.getName()));

			if (movimentoContaPreFaturada.getFaturamentoGrupo() != null) {
				sistemaParametro
						.setAnoMesFaturamento(movimentoContaPreFaturada.getFaturamentoGrupo().getAnoMesReferencia());
			}

			medicaoHistorico = this.getControladorMicromedicao().gerarHistoricoMedicao(
					movimentoContaPreFaturada.getMedicaoTipo(), imovel, movimentoContaPreFaturada.getFaturamentoGrupo(),
					sistemaParametro);

			medicaoHistorico
					.setImovel((movimentoContaPreFaturada.getMedicaoTipo().getId().intValue() == MedicaoTipo.POCO)
							? movimentoContaPreFaturada.getImovel()
							: null);

			if (movimentoContaPreFaturada.getMedicaoTipo().getId().intValue() == MedicaoTipo.LIGACAO_AGUA) {
				LigacaoAgua ligacaoAgua = new LigacaoAgua();
				ligacaoAgua.setId(movimentoContaPreFaturada.getImovel().getId());
				medicaoHistorico.setLigacaoAgua(ligacaoAgua);
			} else {
				medicaoHistorico.setLigacaoAgua(null);
			}

			medicaoHistorico.setMedicaoTipo(movimentoContaPreFaturada.getMedicaoTipo());
			medicaoHistorico.setAnoMesReferencia(movimentoContaPreFaturada.getFaturamentoGrupo().getAnoMesReferencia());
			medicaoHistorico.setNumeroVezesConsecutivasOcorrenciaAnormalidade(null);
			medicaoHistorico.setDataLeituraAtualInformada(movimentoContaPreFaturada.getDataHoraLeitura());
			medicaoHistorico.setDataLeituraCampo(movimentoContaPreFaturada.getDataHoraLeitura());
			medicaoHistorico.setDataLeituraAtualFaturamento(movimentoContaPreFaturada.getDataHoraLeitura());

			if (movimentoContaPreFaturada.getLeituraFaturamento() != null) {
				medicaoHistorico.setLeituraAtualFaturamento(movimentoContaPreFaturada.getLeituraFaturamento());
			} else {
				medicaoHistorico.setLeituraAtualFaturamento(0);
			}

			if (movimentoContaPreFaturada.getLeituraHidrometro() != null) {
				medicaoHistorico.setLeituraAtualInformada(movimentoContaPreFaturada.getLeituraHidrometro());
				medicaoHistorico.setLeituraCampo(movimentoContaPreFaturada.getLeituraHidrometro());
			} else {
				medicaoHistorico.setLeituraAtualInformada(null);
				medicaoHistorico.setLeituraCampo(null);
			}

			if (movimentoContaPreFaturada.getConsumoMedido() != null
					&& movimentoContaPreFaturada.getConsumoMedido() > ConstantesSistema.ZERO) {
				medicaoHistorico.setNumeroConsumoMes(movimentoContaPreFaturada.getConsumoMedido());
			} else {
				medicaoHistorico.setNumeroConsumoMes(null);
			}

			FiltroRota filtroRota = new FiltroRota();
			filtroRota.adicionarParametro(
					new ParametroSimples(FiltroRota.ID_ROTA, movimentoContaPreFaturada.getRota().getId()));

			Collection<Rota> colRota = this.getControladorUtil().pesquisar(filtroRota, Rota.class.getName());
			Rota rota = (Rota) colRota.iterator().next();
			medicaoHistorico.setLeiturista(rota.getLeiturista());

			medicaoHistorico.setLeituraProcessamentoMovimento(new Date());
			medicaoHistorico.setFuncionario(null);

			if (movimentoContaPreFaturada.getLeituraAnormalidadeLeitura() != null
					&& !movimentoContaPreFaturada.getLeituraAnormalidadeLeitura().equals("")) {
				medicaoHistorico
						.setLeituraAnormalidadeInformada(movimentoContaPreFaturada.getLeituraAnormalidadeLeitura());
			} else {
				medicaoHistorico.setLeituraAnormalidadeInformada(null);

			}

			if (movimentoContaPreFaturada.getLeituraAnormalidadeLeitura() != null
					&& !movimentoContaPreFaturada.getLeituraAnormalidadeLeitura().equals("")) {
				medicaoHistorico.setLeituraAnormalidadeFaturamento(
						movimentoContaPreFaturada.getLeituraAnormalidadeFaturamento());
			} else {
				medicaoHistorico.setLeituraAnormalidadeFaturamento(null);

			}

			LeituraSituacao leituraSituacao = new LeituraSituacao();

			if (movimentoContaPreFaturada.getLeituraHidrometro() == null
					|| movimentoContaPreFaturada.getLeituraHidrometro() == 0) {
				leituraSituacao.setId(LeituraSituacao.NAO_REALIZADA);

			} else if (movimentoContaPreFaturada.getIndicadorSituacaoLeitura() != null && movimentoContaPreFaturada
					.getIndicadorSituacaoLeitura().equals(ConstantesSistema.INDICADOR_USO_ATIVO)) {

				leituraSituacao.setId(LeituraSituacao.CONFIRMADA);
			} else {
				leituraSituacao.setId(LeituraSituacao.REALIZADA);
			}

			medicaoHistorico.setLeituraSituacaoAtual(leituraSituacao);

			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = new HidrometroInstalacaoHistorico();

			if (movimentoContaPreFaturada.getMedicaoTipo().getId().intValue() == MedicaoTipo.LIGACAO_AGUA) {
				hidrometroInstalacaoHistorico.setId(movimentoContaPreFaturada.getImovel().getLigacaoAgua()
						.getHidrometroInstalacaoHistorico().getId());
				medicaoHistorico.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);

			} else {
				hidrometroInstalacaoHistorico
						.setId(movimentoContaPreFaturada.getImovel().getHidrometroInstalacaoHistorico().getId());
				medicaoHistorico.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
			}

			medicaoHistorico.setConsumoMedioHidrometro(null);
			medicaoHistorico.setIndicadorAnalisado(ConstantesSistema.NAO);
			medicaoHistorico.setUltimaAlteracao(new Date());

			if (medicaoHistorico.getId() != null) {
				RepositorioMicromedicaoHBM.getInstancia().atualizarMedicaoHistoricoProcessoMOBILE(medicaoHistorico);
			} else {
				getControladorBatch().inserirObjetoParaBatchSemTransacao(medicaoHistorico);
			}
		}
	}
	
	/**
	 * [UC0820] Atualizar Faturamento do Movimento Celular
	 * 
	 * FS0005 - Verificar Existencia do histerico de Medieeo
	 * 
	 * 
	 * @param matricula matricula do imovel selecionado
	 * @param anoMes    ano mes do historico a ser consultado
	 */
	private MedicaoHistorico verificarExistenciaHistoricoMedicao(MovimentoContaPrefaturada movimentoContaPreFaturada)
			throws ErroRepositorioException, ControladorException {
		FiltroMedicaoHistorico filtroMedicaoHistorico = new FiltroMedicaoHistorico();

		// Caso o tipo de medieeo seja egua
		if (movimentoContaPreFaturada.getMedicaoTipo().getId().equals(MedicaoTipo.LIGACAO_AGUA)) {
			filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(FiltroMedicaoHistorico.LIGACAO_AGUA_ID,
					movimentoContaPreFaturada.getImovel().getId()));
		} else {
			filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(FiltroMedicaoHistorico.IMOVEL_ID,
					movimentoContaPreFaturada.getImovel().getId()));
		}

		filtroMedicaoHistorico
				.adicionarParametro(new ParametroSimples(FiltroMedicaoHistorico.ANO_MES_REFERENCIA_FATURAMENTO,
						movimentoContaPreFaturada.getFaturamentoGrupo().getAnoMesReferencia()));
		filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(FiltroMedicaoHistorico.MEDICAO_TIPO_ID,
				movimentoContaPreFaturada.getMedicaoTipo().getId()));
		Collection<MedicaoHistorico> colMedicaoHistorico = this.repositorioUtil.pesquisar(filtroMedicaoHistorico,
				MedicaoHistorico.class.getName());

		MedicaoHistorico medicaoHistorico = (MedicaoHistorico) Util.retonarObjetoDeColecao(colMedicaoHistorico);

		if (medicaoHistorico != null) {

			medicaoHistorico.setFuncionario(null);

			if (movimentoContaPreFaturada.getLeituraAnormalidadeLeitura() != null
					&& !movimentoContaPreFaturada.getLeituraAnormalidadeLeitura().equals("")) {
				medicaoHistorico
						.setLeituraAnormalidadeInformada(movimentoContaPreFaturada.getLeituraAnormalidadeLeitura());
			} else {
				medicaoHistorico.setLeituraAnormalidadeInformada(null);

			}

			if (movimentoContaPreFaturada.getLeituraAnormalidadeLeitura() != null
					&& !movimentoContaPreFaturada.getLeituraAnormalidadeLeitura().equals("")) {
				medicaoHistorico.setLeituraAnormalidadeFaturamento(
						movimentoContaPreFaturada.getLeituraAnormalidadeFaturamento());
			} else {
				medicaoHistorico.setLeituraAnormalidadeFaturamento(null);

			}

			if (movimentoContaPreFaturada.getDataHoraLeitura() != null
					&& !movimentoContaPreFaturada.getDataHoraLeitura().equals("")) {
				medicaoHistorico.setDataLeituraAtualInformada(movimentoContaPreFaturada.getDataHoraLeitura());
				medicaoHistorico.setDataLeituraAtualFaturamento(movimentoContaPreFaturada.getDataHoraLeitura());
				medicaoHistorico.setDataLeituraCampo(movimentoContaPreFaturada.getDataHoraLeitura());
			}

			// 1.10, 1.12
			if (movimentoContaPreFaturada.getLeituraFaturamento() != null) {
				medicaoHistorico.setLeituraAtualFaturamento(movimentoContaPreFaturada.getLeituraFaturamento());
			} else {
				medicaoHistorico.setLeituraAtualFaturamento(0);
			}

			if (movimentoContaPreFaturada.getLeituraHidrometro() != null) {
				medicaoHistorico.setLeituraAtualInformada(movimentoContaPreFaturada.getLeituraHidrometro());
				medicaoHistorico.setLeituraCampo(movimentoContaPreFaturada.getLeituraHidrometro());
			} else {
				medicaoHistorico.setLeituraAtualInformada(null);
				medicaoHistorico.setLeituraCampo(null);
			}

			LeituraSituacao leituraSituacao = new LeituraSituacao();

			if (movimentoContaPreFaturada.getLeituraFaturamento() == null
					|| movimentoContaPreFaturada.getLeituraFaturamento() == 0) {
				leituraSituacao.setId(LeituraSituacao.NAO_REALIZADA);
			} else {
				if (movimentoContaPreFaturada.getIndicadorSituacaoLeitura() == ConstantesSistema.INDICADOR_USO_ATIVO) {
					leituraSituacao.setId(LeituraSituacao.CONFIRMADA);
				} else {
					leituraSituacao.setId(LeituraSituacao.REALIZADA);
				}
			}

			medicaoHistorico.setLeituraSituacaoAtual(leituraSituacao);

			// 1.13
			if (movimentoContaPreFaturada.getConsumoMedido() != null
					&& movimentoContaPreFaturada.getConsumoMedido() > ConstantesSistema.ZERO) {
				medicaoHistorico.setNumeroConsumoMes(movimentoContaPreFaturada.getConsumoMedido());
			} else {
				medicaoHistorico.setNumeroConsumoMes(null);
			}

			// Adicionamos o leiturista da rota na medicao historico
			FiltroRota filtroRota = new FiltroRota();
			filtroRota.adicionarParametro(
					new ParametroSimples(FiltroRota.ID_ROTA, movimentoContaPreFaturada.getRota().getId()));
			filtroRota.adicionarCaminhoParaCarregamentoEntidade("leiturista");
			Collection<Rota> colRota = this.getControladorUtil().pesquisar(filtroRota, Rota.class.getName());
			Rota rota = (Rota) colRota.iterator().next();
			medicaoHistorico.setLeiturista(rota.getLeiturista());

			repositorioUtil.atualizar(medicaoHistorico);
		}

		return medicaoHistorico;
	}
	
	private Rota pesquisarRotaImpressaoSimultanea(AtualizarContaPreFaturadaHelper helperCabecalho)
			throws ControladorException {
		Rota rota = null;

		try {
			if (helperCabecalho.getMatriculaImovel() != null && helperCabecalho.getAnoMesFaturamento() != null) {

				FiltroMovimentoRoteiroEmpresa filtroMovimentoRoteiroEmpresa = new FiltroMovimentoRoteiroEmpresa();

				filtroMovimentoRoteiroEmpresa.adicionarCaminhoParaCarregamentoEntidade("rota.faturamentoGrupo");
				filtroMovimentoRoteiroEmpresa.adicionarCaminhoParaCarregamentoEntidade("rota.leiturista");

				filtroMovimentoRoteiroEmpresa.adicionarCaminhoParaCarregamentoEntidade("localidade");

				filtroMovimentoRoteiroEmpresa.adicionarParametro(new ParametroSimples(
						FiltroMovimentoRoteiroEmpresa.IMOVEL_ID, helperCabecalho.getMatriculaImovel()));

				filtroMovimentoRoteiroEmpresa.adicionarParametro(new ParametroSimples(
						FiltroMovimentoRoteiroEmpresa.ANO_MES_MOVIMENTO, helperCabecalho.getAnoMesFaturamento()));

				Collection<Rota> colMovimmentoRoteiroEmpresa = (Collection<Rota>) repositorioUtil
						.pesquisar(filtroMovimentoRoteiroEmpresa, MovimentoRoteiroEmpresa.class.getName());
				MovimentoRoteiroEmpresa movimentoRoteiroEmpresa = (MovimentoRoteiroEmpresa) Util
						.retonarObjetoDeColecao(colMovimmentoRoteiroEmpresa);
				if (movimentoRoteiroEmpresa != null && !movimentoRoteiroEmpresa.equals("")) {
					rota = movimentoRoteiroEmpresa.getRota();
				}
			}
			// caso neo tenha a rota em movimento_roteiro_empresa, pesquisar
			// pelo imevel
			if (rota == null || rota.equals("")) {

				/*
				 * 
				 * Alteracao para, quando for rota alternativa, bucar a rota pela rota
				 * alternativa do imevel, e neo pelo setor comercial
				 */
				Imovel imovel = this.getControladorImovel().pesquisarImovel(helperCabecalho.getMatriculaImovel());

				if (imovel.getRotaAlternativa() != null && imovel.getRotaAlternativa().getId() != null) {
					rota = this.getControladorMicromedicao().pesquisarRota(imovel.getRotaAlternativa().getId());
				} else {
					FiltroRota filtroRota = new FiltroRota();
					filtroRota.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
					filtroRota.adicionarCaminhoParaCarregamentoEntidade("leiturista");
					filtroRota.adicionarParametro(
							new ParametroSimples(FiltroRota.CODIGO_ROTA, helperCabecalho.getCodigoRota()));

					filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_CODIGO,
							helperCabecalho.getCodigoSetorComercial()));

					filtroRota.adicionarParametro(
							new ParametroSimples(FiltroRota.LOCALIDADE_ID, helperCabecalho.getLocalidade()));

					Collection<Rota> colRota = (Collection<Rota>) repositorioUtil.pesquisar(filtroRota,
							Rota.class.getName());
					rota = (Rota) Util.retonarObjetoDeColecao(colRota);
				}
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return rota;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Object[] incluirMovimentoContaPreFaturada(BufferedReader buffer, Integer idRota,
			ArquivoTextoRetornoIS arquivoTextoRetornoIS, BufferedReader bufferOriginal)
			throws ControladorException, MobileComunicationException {

		byte[] relatorioRetorno = null;

		Object[] retorno = new Object[3];

		try {
			AtualizarContaPreFaturadaHelper helper = new AtualizarContaPreFaturadaHelper();
			Collection<AtualizarContaPreFaturadaHelper> colecaoAtualizarContaPreFaturadaHelper = new ArrayList();
			AtualizarContaPreFaturadaHelper helperDadosCabecalho = null;

			Collection<AtualizarContaPreFaturadaHelper> colHelper = helper.parseHelper(buffer);

			Collection<String> errors = new ArrayList();

			errors.addAll(verificarSequenciaTiposRegistros(colHelper));

			Integer matriculaImovel = null;
			for (AtualizarContaPreFaturadaHelper helperLaco : colHelper) {

				if (helperLaco.getTipoRegistro() == 1
						&& (matriculaImovel == null || !matriculaImovel.equals(helperLaco.getMatriculaImovel()))) {
					matriculaImovel = helperLaco.getMatriculaImovel();
					colecaoAtualizarContaPreFaturadaHelper.add(helperLaco);
					helperDadosCabecalho = helperLaco;
				}

				errors.addAll(verificarValorTipoRegistro(helperLaco));
				errors.addAll(verificarExistenciaMatriculaImovel(helperLaco));
				errors.addAll(verificarTipoMedicao(helperLaco));
				errors.addAll(verificarExistenciaFaturamentoGrupo(helperLaco));
				errors.addAll(verificarExistenciaCodigoAnormalidadeLeitura(helperLaco));
				errors.addAll(verificarDataHoraLeitura(helperLaco));
				errors.addAll(validarIndicadorConfirmacaoLeitura(helperLaco));
				errors.addAll(verificarExistenciaCodigoAnormalidadeConsumo(helperLaco));
				errors.addAll(verificarExistenciaCategoria(helperLaco));
				errors.addAll(verificarExistenciaImpostoTipo(helperLaco));
			}

			if (errors != null && errors.size() > 0 && helperDadosCabecalho != null) {
				relatorioRetorno = geraResumoInconsistenciasLeiturasAnormalidades(errors, helperDadosCabecalho);
			} else {
				try {
					incluiDadosMovimentosContaPreFaturada(colHelper, idRota);
					incluiDadosArquivoRetorno(arquivoTextoRetornoIS, bufferOriginal, colHelper, idRota);
				} catch (ErroRepositorioException e) {
					logger.error("Erro ao incluir movimento conta pre faturada", e);
				}
			}

			retorno[0] = relatorioRetorno;
			retorno[1] = colecaoAtualizarContaPreFaturadaHelper;
		} catch (IOException e) {
			e.printStackTrace();
			throw new ControladorException("erro de io", e);
		}
		return retorno;
	}
	
	/**
	 * 
	 * Este caso de uso permite a insereeo de dados na tabela movimento conta
	 * pre-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pre-Faturada
	 * 
	 * Caso o tipo de registro possua valor <> 1, 2, 3 ou 4, gerar no log de
	 * consistencia a mensagem "Imevel: <<nemero do imevel>> com Movimento
	 * de pre-faturamento com tipo de registro invelido".
	 * 
	 * [FS0009] - Verificar valor do tipo de registro
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param helperLaco
	 */
	private Collection<String> verificarValorTipoRegistro(AtualizarContaPreFaturadaHelper helperLaco) {

		Collection<String> errors = new ArrayList();

		if (!helperLaco.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)
				&& !helperLaco.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_2)
				&& !helperLaco.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_3)
				&& !helperLaco.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_4)
				&& !helperLaco.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_5)) {
			errors.add(ConstantesAplicacao.get("atencao.imovel_tipo_registros_invalidados",
					helperLaco.getMatriculaImovel() + ""));
			System.out.println(
					"[IMOVEL: " + helperLaco.getMatriculaImovel() + "] atencao.imovel_tipo_registros_invalidados");
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a insereeo de dados na tabela movimento conta
	 * pre-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pre-Faturada
	 * 
	 * Caso a matrecula do imevel neo exista na tabela IMOVEL, gerar no log de
	 * consistencia a mensagem "Matrecula do imevel inexistente: <<nemero do
	 * imevel>>"
	 * 
	 * [FS0002] - Verificar existencia da matrecula do imevel
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param helperLaco
	 * @throws ControladorException
	 */
	private Collection<String> verificarExistenciaMatriculaImovel(AtualizarContaPreFaturadaHelper helperLaco)
			throws ControladorException {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		if (helperLaco.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {
			// [FS0002] - Verificar existencia da matrecula do imevel
			if (this.getControladorImovel().verificarExistenciaImovel(helperLaco.getMatriculaImovel()) == 0) {
				errors.add(ConstantesAplicacao.get("atencao.imovel_matricula_inexistente",
						helperLaco.getMatriculaImovel() + ""));
				System.out.println(
						"[IMOVEL: " + helperLaco.getMatriculaImovel() + "] atencao.imovel_matricula_inexistente");
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a insereeo de dados na tabela movimento conta
	 * pre-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pre-Faturada
	 * 
	 * Caso o tipo de medieeo seja diferente de zero e neo exista na tabela
	 * MEDICAO_TIPO, gerar no log de consistencia a mensagem "Imevel: <<nemero
	 * do imevel>> com Tipo de Medieeo inexistente <<tipo de medieeo>>".
	 * 
	 * Caso o tipo de medieeo corresponda e ligaeeo de egua e neo
	 * exista hidremetro instalado para a ligaeeo (LAGU_ID=matrecula do
	 * imevel, HIDI_ID neo preenchido na tabela LIGAeeO_AGUA), gerar no log
	 * de consistencia a mensagem "Imevel: <<nemero do imevel>> com
	 * Movimento para ligaeeo de egua sem hidremetro".
	 * 
	 * Caso o tipo de medieeo corresponda a poeo e neo exista hidremetro
	 * instalado para o poeo (IMOV_NNMATRICULA=matrecula do imevel, HIDI_ID
	 * neo preenchido na tabela IMOVEL), gerar no log de consistencia a mensagem
	 * "Imevel: <<nemero do imevel>> com Movimento para poeo sem
	 * hidremetro".
	 * 
	 * Caso o tipo de medieeo seja zero e a leitura seja informada e neo
	 * exista hidremetro instalado para o imevel (LAGU_ID=matrecula do
	 * imevel, HIDI_ID neo preenchido na tabela LIGAeeO_AGUA e
	 * IMOV_ID=matrecula do imevel, HIDI_ID neo preenchido na tabela IMOVEL),
	 * gerar no log de consistencia a mensagem "Imevel: <<nemero do imevel>>
	 * com Movimento para ligaeeo sem hidremetro" e retornar para o passo 3 do
	 * fluxo principal. Caso o tipo de medieeo seja zero e a anormalidade
	 * informada neo seja compatevel com ligaeeo sem hidremetro
	 * (LTAN_ICIMOVELSEMHIDROMETRO=2) e neo exista hidremetro instalado para o
	 * imevel (LAGU_ID=matrecula do imevel, HIDI_ID neo preenchido na tabela
	 * LIGAeeO_AGUA e IMOV_ID=matrecula do imevel, HIDI_ID neo preenchido
	 * na tabela IMOVEL), gerar no log de consistencia a mensagem "Imevel:
	 * <<nemero do imevel>> com Anormalidade neo permitida para ligaeeo
	 * sem hidremetro".
	 * 
	 * [FS0003] - Verificar tipo de medieeo
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param helperLaco
	 * @throws ControladorException
	 */
	private Collection<String> verificarTipoMedicao(AtualizarContaPreFaturadaHelper helperLaco)
			throws ControladorException {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		// Apenas testamos o tipo de medieeo para registros tipo 1
		if (helperLaco.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {

			// Coletamos as informaeees necesserias para as validaeees
			FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
			filtroLigacaoAgua
					.adicionarParametro(new ParametroSimples(FiltroLigacaoAgua.ID, helperLaco.getMatriculaImovel()));
			filtroLigacaoAgua
					.adicionarParametro(new ParametroNulo(FiltroLigacaoAgua.ID_HIDROMETRO_INSTALACAO_HISTORICO));
			Collection<LigacaoAgua> colLigacaoAgua = this.getControladorUtil().pesquisar(filtroLigacaoAgua,
					LigacaoAgua.class.getName());

			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, helperLaco.getMatriculaImovel()));
			filtroImovel.adicionarParametro(new ParametroNulo(FiltroImovel.HIDROMETRO_INSTALACAO_HISTORICO_ID));
			Collection<Imovel> colImovel = this.getControladorUtil().pesquisar(filtroImovel, Imovel.class.getName());

			/*
			 * Caso o tipo de medieeo seja diferente de zero e neo exista na tabela
			 * MEDICAO_TIPO, gerar no log de consistencia a mensagem "Imevel: <<nemero
			 * do imevel>> com Tipo de Medieeo inexistente <<tipo de medieeo>>".
			 */
			if (!helperLaco.getTipoMedicao().equals(ConstantesSistema.ZERO)) {

				FiltroMedicaoTipo filtroMedicaoTipo = new FiltroMedicaoTipo();
				filtroMedicaoTipo
						.adicionarParametro(new ParametroSimples(FiltroMedicaoTipo.ID, helperLaco.getTipoMedicao()));
				Collection<MedicaoTipo> colMedicaoTipo = this.getControladorUtil().pesquisar(filtroMedicaoTipo,
						MedicaoTipo.class.getName());

				if (colMedicaoTipo.size() == 0) {
					errors.add(ConstantesAplicacao.get("atencao.imovel_tipo_medicao_inexistente",
							helperLaco.getMatriculaImovel() + "", helperLaco.getTipoMedicao() + ""));
					System.out.println("[IMOVEL: " + helperLaco.getMatriculaImovel()
							+ "] atencao.imovel_tipo_medicao_inexistente");
				} else {
					MedicaoTipo medicaoTipo = (MedicaoTipo) Util.retonarObjetoDeColecao(colMedicaoTipo);

					/*
					 * Caso o tipo de medieeo corresponda e ligaeeo de egua e neo
					 * exista hidremetro instalado para a ligaeeo (LAGU_ID=matrecula do
					 * imevel, HIDI_ID neo preenchido na tabela LIGAeeO_AGUA), gerar no log
					 * de consistencia a mensagem "Imevel: <<nemero do imevel>> com
					 * Movimento para ligaeeo de egua sem hidremetro".
					 */
					if (medicaoTipo.getId() == MedicaoTipo.LIGACAO_AGUA) {
						if (colLigacaoAgua == null || colLigacaoAgua.size() == 0) {
							errors.add(ConstantesAplicacao.get("atencao.imovel_movimento_ligacao_agua_sem_hidrometro",
									helperLaco.getMatriculaImovel() + ""));
							System.out.println("[IMOVEL: " + helperLaco.getMatriculaImovel()
									+ "] atencao.imovel_movimento_ligacao_agua_sem_hidrometro");
						}
						/*
						 * Caso o tipo de medieeo corresponda a poeo e neo exista hidremetro
						 * instalado para o poeo (IMOV_NNMATRICULA=matrecula do imevel, HIDI_ID
						 * neo preenchido na tabela IMOVEL), gerar no log de consistencia a mensagem
						 * "Imevel: <<nemero do imevel>> com Movimento para poeo sem
						 * hidremetro".
						 */
					} else if (medicaoTipo.getId() == MedicaoTipo.POCO) {
						if (colImovel == null || colImovel.size() == 0) {
							errors.add(ConstantesAplicacao.get("atencao.imovel_movimento_poco_sem_hidrometro",
									helperLaco.getMatriculaImovel() + ""));
							System.out.println("[IMOVEL: " + helperLaco.getMatriculaImovel()
									+ "] atencao.imovel_movimento_ligacao_agua_sem_hidrometro");
						}
					}
				}
			} else {
				/*
				 * Caso o tipo de medieeo seja zero e a leitura seja informada e neo
				 * exista hidremetro instalado para o imevel (LAGU_ID=matrecula do
				 * imevel, HIDI_ID neo preenchido na tabela LIGAeeO_AGUA e
				 * IMOV_ID=matrecula do imevel, HIDI_ID neo preenchido na tabela IMOVEL),
				 * gerar no log de consistencia a mensagem "Imevel: <<nemero do imevel>>
				 * com Movimento para ligaeeo sem hidremetro".
				 */
				if (helperLaco.getLeituraHidrometro() != null) {
					if ((colLigacaoAgua == null || colLigacaoAgua.size() == 0)
							&& (colImovel == null || colImovel.size() == 0)) {
						errors.add(ConstantesAplicacao.get("atencao.movimento_ligacao_sem_hidrometro",
								helperLaco.getMatriculaImovel() + ""));
						System.out.println("[IMOVEL: " + helperLaco.getMatriculaImovel()
								+ "] atencao.movimento_ligacao_sem_hidrometro");
					}
				}

				/*
				 * Caso o tipo de medieeo seja zero e a anormalidade informada neo seja
				 * compatevel com ligaeeo sem hidremetro (LTAN_ICIMOVELSEMHIDROMETRO=2)
				 * e neo exista hidremetro instalado para o imevel (LAGU_ID=matrecula do
				 * imevel, HIDI_ID neo preenchido na tabela LIGAeeO_AGUA e
				 * IMOV_ID=matrecula do imevel, HIDI_ID neo preenchido na tabela IMOVEL),
				 * gerar no log de consistencia a mensagem "Imevel: <<nemero do imevel>>
				 * com Anormalidade neo permitida para ligaeeo sem hidremetro".
				 */
				FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
				filtroLeituraAnormalidade.adicionarParametro(
						new ParametroSimples(FiltroLeituraAnormalidade.ID, helperLaco.getAnormalidadeLeitura()));
				Collection<LeituraAnormalidade> colAnormalidade = this.getControladorUtil()
						.pesquisar(filtroLeituraAnormalidade, LeituraAnormalidade.class.getName());

				if (colAnormalidade != null && colAnormalidade.size() > 0) {

					LeituraAnormalidade leituraAnormalidade = (LeituraAnormalidade) Util
							.retonarObjetoDeColecao(colAnormalidade);

					if (leituraAnormalidade.getIndicadorImovelSemHidrometro() == ConstantesSistema.NAO
							&& (colLigacaoAgua == null || colLigacaoAgua.size() == 0)
							&& (colImovel == null || colImovel.size() == 0)) {
						errors.add(ConstantesAplicacao.get(
								"atencao.imovel_anormalidade_nao_permitida_ligacao_sem_hidrometro",
								helperLaco.getMatriculaImovel() + ""));
						System.out.println("[IMOVEL: " + helperLaco.getMatriculaImovel()
								+ "] atencao.imovel_anormalidade_nao_permitida_ligacao_sem_hidrometro");
					}
				}
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a insereeo de dados na tabela movimento conta
	 * pre-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pre-Faturada
	 * 
	 * Caso o grupo de faturamento neo exista na tabela FATURAMENTO_GRUPO, exibir
	 * a mensagem "Grupo de faturamento inexistente" e cancelar a operaeeo.
	 * Lembrar que se vire um grupo por arquivo.
	 * 
	 * [FS0001] - Verificar existencia do grupo de faturamento
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param helperLaco
	 */
	private Collection<String> verificarExistenciaFaturamentoGrupo(AtualizarContaPreFaturadaHelper helperLaco)
			throws ControladorException {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		/*
		 * Caso o grupo de faturamento neo exista na tabela FATURAMENTO_GRUPO, exibir
		 * a mensagem "Grupo de faturamento inexistente" e cancelar a operaeeo.
		 * Lembrar que se vire um grupo por arquivo.
		 */
		if (helperLaco.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {

			FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
			filtroFaturamentoGrupo.adicionarParametro(
					new ParametroSimples(FiltroFaturamentoGrupo.ID, helperLaco.getCodigoGrupoFaturamento()));
			Collection<FaturamentoGrupo> colGrupoFaturamento = this.getControladorUtil()
					.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

			if (colGrupoFaturamento == null || colGrupoFaturamento.size() == 0) {
				errors.add(ConstantesAplicacao.get("atencao.grupo_faturamento_inexistente"));
				System.out.println("atencao.grupo_faturamento_inexistente");
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a insereeo de dados na tabela movimento conta
	 * pre-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pre-Faturada
	 * 
	 * Caso o cedigo da anormalidade seja informado (diferente de zero e de
	 * espaeos em branco) e neo exista na tabela LEITURA_ANORMALIDADE, gerar no
	 * log de consistencia a mensagem "Imevel: <<nemero do imevel>> com
	 * Cedigo da Anormalidade de Leitura inexistente <<cedigo da
	 * anormalidade>>".
	 * 
	 * FS0008 - Verificar existencia do cedigo da anormalidade de leitura]
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param colHelper
	 */
	private Collection<String> verificarExistenciaCodigoAnormalidadeLeitura(AtualizarContaPreFaturadaHelper helperLaco)
			throws ControladorException {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		/*
		 * Caso o cedigo da anormalidade seja informado (diferente de zero e de
		 * espaeos em branco) e neo exista na tabela LEITURA_ANORMALIDADE, gerar no
		 * log de consistencia a mensagem "Imevel: <<nemero do imevel>> com
		 * Cedigo da Anormalidade de Leitura inexistente <<cedigo da
		 * anormalidade>>".
		 */
		if (helperLaco.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {

			/*
			 *
			 * Alteraeeo para verificar se o codigo de anormalida e igual a 0 (zero), ou
			 * seja, se neo existe anormalidade, pois caso seja 0, a consulta de
			 * anormalidades neo vai achar a anormalidade e vai indicar erro de
			 * anormalidade inexistente, se que e o caso de neo ter anormalidade no
			 * imevel, e neo de ser um cedigo inexistente.
			 */
			if (helperLaco.getAnormalidadeLeitura() != null
					&& !helperLaco.getAnormalidadeLeitura().equals(new Integer(0))) {
				FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
				filtroLeituraAnormalidade.adicionarParametro(
						new ParametroSimples(FiltroLeituraAnormalidade.ID, helperLaco.getAnormalidadeLeitura()));
				Collection<LeituraAnormalidade> colAnormalidade = this.getControladorUtil()
						.pesquisar(filtroLeituraAnormalidade, LeituraAnormalidade.class.getName());

				if (colAnormalidade == null || colAnormalidade.size() == 0) {
					errors.add(ConstantesAplicacao.get("atencao.imovel_codigo_anormalidade_inexistente",
							helperLaco.getMatriculaImovel() + "", helperLaco.getAnormalidadeLeitura() + ""));
					System.out.println("[IMOVEL: " + helperLaco.getMatriculaImovel()
							+ "] atencao.imovel_codigo_anormalidade_inexistente");
				}
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a insereeo de dados na tabela movimento conta
	 * pre-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pre-Faturada
	 * 
	 * Caso a data e hora de leitura seja invelida ou maior que a data corrente,
	 * gerar no log de consistencia a mensagem "Imevel: <<nemero do imevel>>
	 * com Data e hora de leitura invelida <<data da leitura>>".
	 * 
	 * Caso o ano/mes da data de leitura neo seja igual ao ano/mes de
	 * referencia do faturamento do grupo (FTGR_AMREFERENCIA) e neo seja igual
	 * ao ano/mes de referencia do faturamento do grupo menos um mes e neo
	 * seja igual ao ano/mes de referencia do faturamento do grupo mais um
	 * mes, gerar no log de consistencia a mensagem "Data de leitura
	 * incompatevel com o mes/ano de faturamento".
	 * 
	 * [FS0004 - Verificar data e hora da leitura]
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param colHelper
	 */
	private Collection<String> verificarDataHoraLeitura(AtualizarContaPreFaturadaHelper helperLaco)
			throws ControladorException {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		/*
		 * Caso o cedigo da anormalidade seja informado (diferente de zero e de
		 * espaeos em branco) e neo exista na tabela LEITURA_ANORMALIDADE, gerar no
		 * log de consistencia a mensagem "Imevel: <<nemero do imevel>> com
		 * Cedigo da Anormalidade de Leitura inexistente <<cedigo da
		 * anormalidade>>".
		 */
		if (helperLaco.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {

			if (helperLaco.getDataHoraLeituraHidrometro() != null) {
				Date dataLeituraHidrometro = helperLaco.getDataHoraLeituraHidrometro();

				dataLeituraHidrometro = Util.subtrairNumeroDiasDeUmaData(dataLeituraHidrometro, 1);

				if (Util.compararData(dataLeituraHidrometro, new Date()) == 1) {
					errors.add(ConstantesAplicacao.get("atencao.data_leitura_incompativel_mes_ano_faturamento"));
					System.out.println("atencao.data_leitura_incompativel_mes_ano_faturamento");
				}

				/*
				 * Caso o ano/mes da data de leitura neo seja igual ao ano/mes de
				 * referencia do faturamento do grupo (FTGR_AMREFERENCIA) e neo seja igual
				 * ao ano/mes de referencia do faturamento do grupo menos um mes e neo
				 * seja igual ao ano/mes de referencia do faturamento do grupo mais um
				 * mes, gerar no log de consistencia a mensagem "Data de leitura
				 * incompatevel com o mes/ano de faturamento" e retornar para o passo 3 do
				 * fluxo principal.
				 */

				// Pesquisamos o grupo de faturamento
				FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
				filtroFaturamentoGrupo.adicionarParametro(
						new ParametroSimples(FiltroFaturamentoGrupo.ID, helperLaco.getCodigoGrupoFaturamento()));
				Collection<FaturamentoGrupo> colGrupoFaturamento = this.getControladorUtil()
						.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

				FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) Util.retonarObjetoDeColecao(colGrupoFaturamento);

				Integer anoMesFaturamentoGrupoMaisUmMes = Util
						.somaMesAnoMesReferencia(faturamentoGrupo.getAnoMesReferencia(), 1);
				Integer anoMesFaturamentoGrupoMenosUmMes = Util
						.subtrairMesDoAnoMes(faturamentoGrupo.getAnoMesReferencia(), 1);

				Integer anoMesDataLeituraHidrometro = Util.recuperaAnoMesDaData(dataLeituraHidrometro);

				if (faturamentoGrupo.getAnoMesReferencia().intValue() != anoMesDataLeituraHidrometro.intValue()
						&& anoMesFaturamentoGrupoMaisUmMes.intValue() != anoMesDataLeituraHidrometro.intValue()
						&& anoMesFaturamentoGrupoMenosUmMes.intValue() != anoMesDataLeituraHidrometro.intValue()) {

					errors.add(ConstantesAplicacao.get("atencao.data_leitura_incompativel_mes_ano_faturamento"));
				}
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a insereeo de dados na tabela movimento conta
	 * pre-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pre-Faturada
	 * 
	 * Caso o Indicador de confirmaeeo de leitura neo seja igual a 0 ou 1,
	 * gerar no log de consistencia a mensagem "Imevel: <<nemero do imevel>>
	 * com Indicador de Confirmaeeo de Leitura invelido <<indicador de
	 * confirmaeeo de leitura>>".
	 * 
	 * [FS0006] - Validar indicador de confirmaeeo de leitura
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param helperLaco
	 */
	private Collection<String> validarIndicadorConfirmacaoLeitura(AtualizarContaPreFaturadaHelper helperLaco) {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		/*
		 * Caso o Indicador de confirmaeeo de leitura neo seja igual a 0 ou 1,
		 * gerar no log de consistencia a mensagem "Imevel: <<nemero do imevel>>
		 * com Indicador de Confirmaeeo de Leitura invelido <<indicador de
		 * confirmaeeo de leitura>>" e retornar para o passo 3 do fluxo principal.
		 */
		if (helperLaco.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {
			if (helperLaco.getIndicadorConfirmacaoLeitura() != null) {
				if (helperLaco.getIndicadorConfirmacaoLeitura() != 0
						&& helperLaco.getIndicadorConfirmacaoLeitura() != 1) {
					errors.add(ConstantesAplicacao.get("atencao.imovel_indicador_confirmacao_leitura_invalido",
							helperLaco.getMatriculaImovel() + "", helperLaco.getIndicadorConfirmacaoLeitura() + ""));
					System.out.println("[IMOVEL: " + helperLaco.getMatriculaImovel()
							+ "] atencao.imovel_indicador_confirmacao_leitura_invalido");
				}
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a insereeo de dados na tabela movimento conta
	 * pre-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pre-Faturada
	 * 
	 * Caso o cedigo da anormalidade seja informado (diferente de zero e de
	 * espaeos em branco) e neo exista na tabela CONSUMO_ANORMALIDADE, gerar no
	 * log de consistencia a mensagem "Imevel: <<nemero do imevel>> com
	 * Cedigo da Anormalidade de consumo inexistente <<cedigo da
	 * anormalidade>>".
	 * 
	 * [FS0012] - Verificar existencia do cedigo da anormalidade de consumo
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param helperLaco
	 */
	private Collection<String> verificarExistenciaCodigoAnormalidadeConsumo(AtualizarContaPreFaturadaHelper helperLaco)
			throws ControladorException {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		/*
		 * Caso o Indicador de confirmaeeo de leitura neo seja igual a 0 ou 1,
		 * gerar no log de consistencia a mensagem "Imevel: <<nemero do imevel>>
		 * com Indicador de Confirmaeeo de Leitura invelido <<indicador de
		 * confirmaeeo de leitura>>" e retornar para o passo 3 do fluxo principal.
		 */
		if (helperLaco.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {

			if (helperLaco.getAnormalidadeConsumo() != null) {
				FiltroConsumoAnormalidade filtroConsumoAnormalidade = new FiltroConsumoAnormalidade();
				filtroConsumoAnormalidade.adicionarParametro(
						new ParametroSimples(FiltroConsumoAnormalidade.ID, helperLaco.getAnormalidadeConsumo()));
				Collection<ConsumoAnormalidade> colConsumoAnormalidade = this.getControladorUtil()
						.pesquisar(filtroConsumoAnormalidade, ConsumoAnormalidade.class.getName());

				if (colConsumoAnormalidade != null && colConsumoAnormalidade.size() == 0) {
					errors.add(ConstantesAplicacao.get("atencao.imovel_codigo_anormalidade_consumo_inexistente",
							helperLaco.getMatriculaImovel() + "", helperLaco.getIndicadorConfirmacaoLeitura() + ""));
					System.out.println("[IMOVEL: " + helperLaco.getMatriculaImovel()
							+ "] atencao.imovel_codigo_anormalidade_consumo_inexistente");
				}
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a insereeo de dados na tabela movimento conta
	 * pre-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pre-Faturada
	 * 
	 * Caso o cedigo da categoria neo exista na tabela CATEGORIA, gerar no log
	 * de consistencia a mensagem "Imevel: <<nemero do imevel>> com
	 * Categoria inexistente <<cedigo da categoria>>".
	 * 
	 * [FS0012] - Verificar existencia do cedigo da anormalidade de consumo
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param helperLaco
	 */
	private Collection<String> verificarExistenciaCategoria(AtualizarContaPreFaturadaHelper helperLaco)
			throws ControladorException {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		/*
		 * Caso o cedigo da categoria neo exista na tabela CATEGORIA, gerar no log
		 * de consistencia a mensagem "Imevel: <<nemero do imevel>> com
		 * Categoria inexistente <<cedigo da categoria>>".
		 */

		if (helperLaco.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_2)) {

			if (helperLaco.getCodigoCategoria() != 0) {
				FiltroCategoria filtroCategoria = new FiltroCategoria();
				filtroCategoria.adicionarParametro(
						new ParametroSimples(FiltroCategoria.CODIGO, helperLaco.getCodigoCategoria()));
				Collection<Categoria> colCategoria = this.getControladorUtil().pesquisar(filtroCategoria,
						ConsumoAnormalidade.class.getName());

				if (colCategoria != null && colCategoria.size() == 0) {
					errors.add(ConstantesAplicacao.get("atencao.imovel_categoria_inexistente"));
					System.out.println("atencao.imovel_categoria_inexistente");
				}
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a insereeo de dados na tabela movimento conta
	 * pre-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pre-Faturada
	 * 
	 * Caso o tipo do imposto neo exista na tabela IMPOSTO_TIPO, gerar no log de
	 * consistencia a mensagem "Imevel: <<nemero do imevel>> com Tipo do
	 * Imposto inexistente <<tipo do imposto>>".
	 * 
	 * [FS0010] - Verificar existencia do tipo do imposto
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param helperLaco
	 */
	private Collection<String> verificarExistenciaImpostoTipo(AtualizarContaPreFaturadaHelper helperLaco)
			throws ControladorException {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		/*
		 * Caso o tipo do imposto neo exista na tabela IMPOSTO_TIPO, gerar no log de
		 * consistencia a mensagem "Imevel: <<nemero do imevel>> com Tipo do
		 * Imposto inexistente <<tipo do imposto>>".
		 */
		if (helperLaco.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_4)) {

			if (helperLaco.getTipoImposto() != 0) {
				FiltroImpostoTipo filtroImpostoTipo = new FiltroImpostoTipo();
				filtroImpostoTipo
						.adicionarParametro(new ParametroSimples(FiltroImpostoTipo.ID, helperLaco.getTipoImposto()));
				Collection<ImpostoTipo> colImpostoTipo = this.getControladorUtil().pesquisar(filtroImpostoTipo,
						ImpostoTipo.class.getName());

				if (colImpostoTipo != null && colImpostoTipo.size() == 0) {
					errors.add(ConstantesAplicacao.get("atencao.imovel_tipo_imposto_inexistente"));
					System.out.println("atencao.imovel_tipo_imposto_inexistente");
				}
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a insereeo de dados na tabela movimento conta
	 * pre-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pre-Faturada
	 * 
	 * 
	 * Caso seja chamado por uma tela, o sistema gera uma tela de acordo com o
	 * movimento, Caso contrerio, o sistema gera um relaterio e envia, por
	 * e-mail para o operador, registrado com os seguintes campos:
	 * 
	 * No cabeealho imprimir o grupo de faturamento informado (FTGR_ID), o
	 * cedigo e descrieeo da empresa (EMPR_ID e EMPR_NMEMPRESA da tabela
	 * EMPRESA com EMPR_ID da tabela ROTA com ROTA_ID da tabela QUADRA com QDRA_ID
	 * da tabela IMOVEL com IMOV_ID=matrecula do imevel do primeiro registro do
	 * arquivo que exista na tabela IMOVEL), o cedigo da localidade e o tetulo
	 * fixo "MOVIMENTO CELULAR - IMPRESSeO SIMULTeNEA" quando processado o
	 * arquivo de movimento;
	 * 
	 * Imprimir o erro correspondente encontrado no processamento do imevel;
	 * 
	 * Caso seja chamado por uma tela, imprimir um texto "Arquivo processado com
	 * problema e enviado para operaeeo para processar o movimento. Localidade
	 * <<Cedigo da Localidade>>";
	 * 
	 * [SB0001] - Gera Tela Resumo das leituras e anormalidades da impresseo
	 * simultenea com Problemas
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param colErrors
	 */
	private byte[] geraResumoInconsistenciasLeiturasAnormalidades(Collection<String> colErrors,
			AtualizarContaPreFaturadaHelper helper) throws ControladorException {

		RelatorioErrosMovimentosContaPreFaturadas relatorioErrosMovimentosContaPreFaturadas = new RelatorioErrosMovimentosContaPreFaturadas(
				new Usuario());

		// Adicionamos os parametros
		Map parametros = new HashMap();

		parametros.put("imagem", this.getControladorUtil().pesquisarParametrosDoSistema().getImagemRelatorio());

		// Grupo de faturamento
		parametros.put("grupoFaturamento", helper.getCodigoGrupoFaturamento() + "");

		// Id da localidade
		FiltroRota filtro = new FiltroRota();
		filtro.adicionarCaminhoParaCarregamentoEntidade("empresa");
		filtro.adicionarParametro(new ParametroSimples(FiltroRota.CODIGO_ROTA, helper.getCodigoRota()));
		filtro.adicionarParametro(new ParametroSimples(FiltroRota.LOCALIDADE_ID, helper.getLocalidade()));
		filtro.adicionarParametro(
				new ParametroSimples(FiltroRota.SETOR_COMERCIAL_CODIGO, helper.getCodigoSetorComercial()));
		Collection<Rota> colRota = Fachada.getInstancia().pesquisar(filtro, Rota.class.getName());
		Rota rota = (Rota) Util.retonarObjetoDeColecao(colRota);
		parametros.put("idLocalidade", helper.getLocalidade() + "");

		// Cedigo do setor Comercial
		parametros.put("codigoSetorComercial", helper.getCodigoSetorComercial() + "");

		// Id e descrieeo de empresa
		String descricaoRota = "";
		if (rota != null && !rota.equals("")) {
			descricaoRota = rota.getEmpresa().getId() + " - " + rota.getEmpresa().getDescricao();
		}
		parametros.put("idDescricaoEmpresa", descricaoRota);

		// Cedigo da localidade
		parametros.put("codigoLocalidade", helper.getLocalidade() + "");

		// Criamos agora os beans do relatorio
		List relatorioBeans = new ArrayList();

		for (String erro : colErrors) {

			RelatorioErrosMovimentosContaPreFaturadasBean bean = new RelatorioErrosMovimentosContaPreFaturadasBean();

			bean.setError(erro);

			relatorioBeans.add(bean);
		}

		// Criamos o source
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		return relatorioErrosMovimentosContaPreFaturadas.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_ERROS_MOVIMENTOS_CONTA_PRE_FATURADAS, parametros, ds,
				TarefaRelatorio.TIPO_PDF);
	}
	
	/**
	 * 
	 * Selecionamos os movimento para serem processados
	 * 
	 * @param helper Helper para pesquisa
	 * @return Coleeeo com os dados solicitados
	 * 
	 * @throws ControladorException
	 */
	private Collection<MovimentoContaPrefaturada> verificarExistenciaListaMovimentoContaPrefaturada(
			AtualizarContaPreFaturadaHelper helperCabecalho) throws ControladorException {
		FiltroMovimentoContaPrefaturada filtroMovimentoContaPrefaturada = new FiltroMovimentoContaPrefaturada();
		filtroMovimentoContaPrefaturada
				.adicionarCaminhoParaCarregamentoEntidade("imovel.hidrometroInstalacaoHistorico");
		filtroMovimentoContaPrefaturada.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
		filtroMovimentoContaPrefaturada.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoAgua");
		filtroMovimentoContaPrefaturada.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeFaturamento");
		filtroMovimentoContaPrefaturada.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra.rota");
		filtroMovimentoContaPrefaturada.adicionarCaminhoParaCarregamentoEntidade("conta");
		filtroMovimentoContaPrefaturada.adicionarCaminhoParaCarregamentoEntidade("movimentoContaPrefaturadaCategorias");
		filtroMovimentoContaPrefaturada.adicionarCaminhoParaCarregamentoEntidade("conta.ligacaoAguaSituacao");
		filtroMovimentoContaPrefaturada.adicionarCaminhoParaCarregamentoEntidade("conta.ligacaoEsgotoSituacao");
		filtroMovimentoContaPrefaturada.adicionarCaminhoParaCarregamentoEntidade("imovel.imovelCondominio");
		filtroMovimentoContaPrefaturada.setInitializeLazy(true);

		filtroMovimentoContaPrefaturada.adicionarParametro(new ParametroSimples(
				FiltroMovimentoContaPrefaturada.INDICADOR_ATUALIZAR_FATURAMENTO, ConstantesSistema.NAO));

		filtroMovimentoContaPrefaturada.adicionarParametro(
				new ParametroSimples(FiltroMovimentoContaPrefaturada.MATRICULA, helperCabecalho.getMatriculaImovel()));

		filtroMovimentoContaPrefaturada.adicionarParametro(
				new ParametroSimples(FiltroMovimentoContaPrefaturada.ANO_MES_REFERENCIA_PRE_FATURAMENTO,
						helperCabecalho.getAnoMesFaturamento()));

		Collection<MovimentoContaPrefaturada> colMovimentoContaPrefaturada = null;

		try {
			colMovimentoContaPrefaturada = (Collection<MovimentoContaPrefaturada>) repositorioUtil
					.pesquisar(filtroMovimentoContaPrefaturada, MovimentoContaPrefaturada.class.getName());
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return colMovimentoContaPrefaturada;
	}
	
	/**
	 * 
	 * @author Pamela Gatinho
	 * @date 15/03/2013
	 * 
	 *       Metodo que atualiza se o Extrato de Quitaeeo gerado para o ano
	 *       anterior foi impresso pelo Impresseo Simultenea.
	 * 
	 * @param colMovimentoContaPreFaturada
	 * @throws ControladorException
	 */
	private void atualizarInformacoesImpressaoExtratoQuitacao(
			Collection<MovimentoContaPrefaturada> colMovimentoContaPreFaturada) throws ControladorException {
		System.out.println("Atualizando informaeees de extrato de quitaeeo...");
		if (colMovimentoContaPreFaturada != null && !colMovimentoContaPreFaturada.isEmpty()) {
			for (MovimentoContaPrefaturada movimento : colMovimentoContaPreFaturada) {

				Integer anoMesAnterior = Util.subtrairAnoAnoMesReferencia(movimento.getAnoMesReferenciaPreFaturamento(),
						1);

				Integer anoAnterior = Util.obterAno(anoMesAnterior);

				ExtratoQuitacao extrato = getControladorFaturamento().obterExtratoQuitacaoImovel(movimento.getImovel().getId(), anoAnterior);

				if (extrato != null && !extrato.getIndicadorImpressaoNaConta()
						.equals(new Integer(movimento.getIndicadorEmissaoConta()))) {
					extrato.setIndicadorImpressaoNaConta(new Integer(movimento.getIndicadorEmissaoConta()));

					this.getControladorUtil().atualizar(extrato);
				}
			}
		}
		System.out.println("Fim da atualizaeeo das informaeees de extrato de quitaeeo...");
	}
	
	/**
	 * 
	 * Este caso de uso permite a insereeo de dados na tabela movimento conta
	 * pre-faturada.
	 * 
	 * [SB0001] - Gera Tela Resumo das leituras e anormalidades da impresseo
	 * simultenea registradas
	 * 
	 * @author bruno
	 * @date 21/09/2009
	 */
	private byte[] geraResumoLeiturasAnormalidadesImpressaoSimultanea(
			Collection<MovimentoContaPrefaturada> colRelatorio) throws ControladorException {

		Integer qtdRegistrosRecebidos = 0;
		Integer qtdRegistrosLeitura = 0;
		Integer qtdRegistrosAnormalidade = 0;
		Integer qtdRegistrosLeituraAnormalidade = 0;
		Integer qtdRegistrosInvalidos = 0;
		Integer qtdRegistrosContaNaoEmitida = 0;

		MovimentoContaPrefaturada helperCabecalho = null;

		Collection<Integer> imoveisJaLidos = new ArrayList();

		// Criamos agora os beans do relatorio
		List relatorioBeans = new ArrayList();

		for (MovimentoContaPrefaturada helper : colRelatorio) {

			if (!imoveisJaLidos.contains(helper.getImovel().getId())) {
				imoveisJaLidos.add(helper.getImovel().getId());

				qtdRegistrosRecebidos++;

				if (helperCabecalho == null) {
					helperCabecalho = helper;
				}

				if (helper.getLeituraFaturamento() != null && helper.getLeituraFaturamento() != 0) {
					qtdRegistrosLeitura++;
				} else if (helper.getLeituraAnormalidadeFaturamento() != null) {
					qtdRegistrosAnormalidade++;
				} else if (helper.getLeituraFaturamento() != null && helper.getLeituraFaturamento() != 0
						&& helper.getLeituraAnormalidadeFaturamento() != null) {
					qtdRegistrosLeituraAnormalidade++;
				} else {
					qtdRegistrosInvalidos++;
				}

				if (helper.getIndicadorEmissaoConta() == ConstantesSistema.NAO) {
					qtdRegistrosContaNaoEmitida++;
				}

				RelatorioResumoLeiturasAnormalidadesImpressaoSimultaneaBean bean = new RelatorioResumoLeiturasAnormalidadesImpressaoSimultaneaBean();

				if (helper.getLeituraAnormalidadeFaturamento() != null) {
					bean.setCodigoAnormalidade(helper.getLeituraAnormalidadeFaturamento().getId() + "");

					if (!relatorioBeans.contains(bean)) {

						if (helper.getLeituraAnormalidadeFaturamento() != null) {
							bean.setCodigoAnormalidade(helper.getLeituraAnormalidadeFaturamento().getId() + "");
							bean.setDescricaoAnormalidade(helper.getLeituraAnormalidadeFaturamento().getDescricao());
						}

						bean.setQtdAnormalidade(1);

						relatorioBeans.add(bean);
					} else {
						int index = relatorioBeans.indexOf(bean);
						bean = (RelatorioResumoLeiturasAnormalidadesImpressaoSimultaneaBean) relatorioBeans.get(index);
						bean.setQtdAnormalidade(bean.getQtdAnormalidade() + 1);
					}
				}
			}
		}

		if (relatorioBeans.size() == 0) {
			relatorioBeans.add(new RelatorioResumoLeiturasAnormalidadesImpressaoSimultaneaBean());
		}

		RelatorioResumoLeiturasAnormalidadesImpressaoSimultanea relatorioResumoLeiturasAnormalidadesImpressaoSimultanea = new RelatorioResumoLeiturasAnormalidadesImpressaoSimultanea(
				new Usuario());

		// Adicionamos os parametros
		Map parametros = new HashMap();

		parametros.put("imagem", this.getControladorUtil().pesquisarParametrosDoSistema().getImagemRelatorio());

		// Id da localidade
		FiltroImovel filtro = new FiltroImovel();
		filtro.adicionarCaminhoParaCarregamentoEntidade("quadra.rota.empresa");
		filtro.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtro.adicionarParametro(new ParametroSimples(FiltroImovel.ID, helperCabecalho.getImovel().getId()));
		Collection<Imovel> colImo = Fachada.getInstancia().pesquisar(filtro, Imovel.class.getName());
		Imovel imo = (Imovel) Util.retonarObjetoDeColecao(colImo);
		parametros.put("grupo", helperCabecalho.getFaturamentoGrupo().getId() + "");
		parametros.put("localidade", imo.getLocalidade().getId() + "");
		parametros.put("codigoEmpresa", imo.getQuadra().getRota().getEmpresa().getId());
		parametros.put("empresa", imo.getQuadra().getRota().getEmpresa().getDescricao());

		parametros.put("qtdRegistrosRecebidos", qtdRegistrosRecebidos);
		parametros.put("qtdRegistrosLeitura", qtdRegistrosLeitura);
		parametros.put("qtdRegistrosAnormalidade", qtdRegistrosAnormalidade);
		parametros.put("qtdRegistrosInvalidos", qtdRegistrosInvalidos);
		parametros.put("qtdRegistrosLeituraAnormalidade", qtdRegistrosLeituraAnormalidade);
		parametros.put("qtdRegistrosSemContaEmitida", qtdRegistrosContaNaoEmitida);

		// Criamos o source
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		return relatorioResumoLeiturasAnormalidadesImpressaoSimultanea.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_RESUMO_LEITURAS_ANORMALIDADE_IMPRESSAO_SIMULTANEA, parametros, ds,
				TarefaRelatorio.TIPO_PDF);
	}
	
	/**
	 * 
	 * Verifica se algum imevel teve uma solicitaeeo de releitura para uma
	 * rota e anomes
	 * 
	 * @author Bruno Barros
	 * @date 01/09/2010
	 * 
	 * @param idRota - Id da rota a ser pesquisada
	 * 
	 * @return String com a mensagem formatada para o celular - Formato da mensagem:
	 *         XXXXXXX...&YYYYY=123456,654321,567890&ZZZZZ=123123,123123 ,123123...
	 * 
	 *         Sendo XXXX -> Mensagem a ser apresentada no celular. & -> Separador
	 *         que indica que a mensagem acabou. YYYYY - > Nome do parametro = ->
	 *         Indicador que o nome do parametro acabou 123456 -> Valor retornado ,
	 *         -> Separador de valor
	 * 
	 *         Caso neo haja imeveis para releitura, retorna nulo;
	 * 
	 * @throws ErroRepositorioException
	 */
	private String verificarSolicitacaoReleituraImovelImpressaoSimultanea(Integer idRota) throws ControladorException {

		// Verificamos se alguma solicitaeeo de releitura foi feita para essa
		// rota
		Collection<ReleituraMobile> colReleituraMobile;
		StringBuffer retorno = new StringBuffer();

		try {
			Integer anoMesFaturamentoGrupoRota = getControladorFaturamento().retornaAnoMesFaturamentoGrupoDaRota(idRota);

			colReleituraMobile = this.repositorioMicromedicao.pesquisarImoveisReleituraMobileSolicitada(idRota,
					anoMesFaturamentoGrupoRota);

			if (colReleituraMobile != null && colReleituraMobile.size() > 0) {

				StringBuffer matriculas = new StringBuffer();
				StringBuffer matriculasFormatadas = new StringBuffer();

				int i = 1;

				for (ReleituraMobile releituraMobile : colReleituraMobile) {

					if (i == colReleituraMobile.size()) {
						matriculas.append(releituraMobile.getImovel().getId());
						matriculasFormatadas.append(releituraMobile.getImovel().getMatriculaFormatada());
					} else {
						matriculas.append(releituraMobile.getImovel().getId() + ",");
						matriculasFormatadas.append(releituraMobile.getImovel().getMatriculaFormatada() + ", ");
					}

					releituraMobile.setIndicadorMensagemRecebida(new Integer(ConstantesSistema.SIM));
					releituraMobile.setUltimaAlteracao(new Date());

					++i;
				}

				retorno.append("imoveis=" + matriculas);

				if (colReleituraMobile.size() == 1) {
					// retorno.append( "mensagem=Refazer leitura para o imovel "
					// + matriculasFormatadas.toString() + " . Ir para o imovel
					// ?&imoveis=" + matriculas );
				} else {
					// retorno.append( "mensagem=Refazer leitura para os imoveis
					// " + matriculasFormatadas.toString() + " . Ir para o
					// primeiro imovel ?&imoveis=" + matriculas );
				}

				// Colocamos como enviados
				this.getControladorBatch().atualizarColecaoObjetoParaBatch(colReleituraMobile);
				return retorno.toString();
			}
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			new ControladorException("erro.sistema", ex);
		}

		return null;
	}
	
	/**
	 * 
	 * Este caso de uso permite a insereeo de dados na tabela movimento conta
	 * pre-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pre-Faturada
	 * 
	 * Verificar seqeencia dos tipos de registros
	 * 
	 * Neo podere vir um registro do tipo 1 depois de outro tipo 1 para o mesmo
	 * imevel, devere retornar uma mensagem "Imevel: <<nemero do imevel>>,
	 * do arquivo, com seqeencial 1 depois de outro seqeencial 1";
	 * 
	 * Neo podere vir um registro do tipo 2 sem que tenha um do tipo 1 para o
	 * mesmo imevel, devere retornar uma mensagem "Imevel: <<nemero do
	 * imevel>>, do arquivo, com seqeencial 2 sem seqeencial 1.";
	 * 
	 * Neo podere vir um registro do tipo 3 sem que tenha um do tipo 2 para o
	 * mesmo imevel, devere retornar uma mensagem "Imevel: <<nemero do
	 * imevel>> , do arquivo, com seqeencial 3 sem seqeencial 2.";
	 * 
	 * Neo podere vir um registro do tipo 4 sem que tenha um do tipo 1 para o
	 * mesmo imevel devere retornar uma mensagem "Imevel: <<nemero do
	 * imevel>>, do arquivo, com seqeencial 4 sem seqeencial 1.";
	 * 
	 * [FS0008] - Verificar seqeencia dos tipos de registros
	 * 
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param colHelper
	 * @throws ControladorException
	 */
	private Collection<String> verificarSequenciaTiposRegistros(Collection<AtualizarContaPreFaturadaHelper> colHelper) {

		Integer registroAnterior = null;
		Integer matriculaImovelRegistroTipo1Selecionado = null;
		Integer matriculaImovelRegistroTipo2Selecionado = null;

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		for (AtualizarContaPreFaturadaHelper helperLaco : colHelper) {

			if (helperLaco.getTipoRegistro() == AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1) {
				// Neo podere vir um registro do tipo 1 depois de outro tipo 1
				if (registroAnterior != null && registroAnterior.equals(helperLaco.getTipoRegistro())) {
					/*
					 * errors.add( ConstantesAplicacao.get(
					 * "atencao.imovel_movimento_dados_faturamento_registro_tipo_1" ,
					 * helperLaco.getMatriculaImovel()+"" ) );
					 */
				}

				// Guardamos as informaeees necessarias ao registro tipo 1
				matriculaImovelRegistroTipo1Selecionado = helperLaco.getMatriculaImovel();
			} else if (helperLaco.getTipoRegistro() == AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_2) {
				// Neo podere vir um registro do tipo 2 sem que tenha um do
				// tipo 1 para o mesmo imevel
				if (matriculaImovelRegistroTipo1Selecionado == null
						|| !matriculaImovelRegistroTipo1Selecionado.equals(helperLaco.getMatriculaImovel())) {

					System.out.println("[IMOVEL: " + helperLaco.getMatriculaImovel()
							+ "] atencao.imovel_movimento_dados_faturamento_registro_tipo_2");
					errors.add(ConstantesAplicacao.get("atencao.imovel_movimento_dados_faturamento_registro_tipo_2",
							helperLaco.getMatriculaImovel() + ""));
				}

				// Guardamos as informaeees necessarias ao registro tipo 2
				matriculaImovelRegistroTipo2Selecionado = helperLaco.getMatriculaImovel();
			} else if (helperLaco.getTipoRegistro() == AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_3) {
				// Neo podere vir um registro do tipo 3 sem que tenha um do
				// tipo 2 para o mesmo imevel
				if (matriculaImovelRegistroTipo2Selecionado == null
						|| !matriculaImovelRegistroTipo2Selecionado.equals(helperLaco.getMatriculaImovel())) {
					System.out.println("[IMOVEL: " + helperLaco.getMatriculaImovel()
							+ "] atencao.imovel_movimento_dados_faturamento_registro_tipo_3");
					errors.add(ConstantesAplicacao.get("atencao.imovel_movimento_dados_faturamento_registro_tipo_3",
							helperLaco.getMatriculaImovel() + ""));
				}
			} else if (helperLaco.getTipoRegistro() == AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_4) {
				// Neo podere vir um registro do tipo 4 sem que tenha um do
				// tipo 1 para o mesmo imevel
				if (matriculaImovelRegistroTipo1Selecionado == null
						|| !matriculaImovelRegistroTipo1Selecionado.equals(helperLaco.getMatriculaImovel())) {
					System.out.println("[IMOVEL: " + helperLaco.getMatriculaImovel()
							+ "] atencao.imovel_movimento_dados_faturamento_registro_tipo_4");
					errors.add(ConstantesAplicacao.get("atencao.imovel_movimento_dados_faturamento_registro_tipo_4",
							helperLaco.getMatriculaImovel() + ""));
				}
			}

			registroAnterior = helperLaco.getTipoRegistro();
		}

		return errors;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes", "rawtypes", "unused" })
	private void incluiDadosMovimentosContaPreFaturada(Collection<AtualizarContaPreFaturadaHelper> colHelper,
			Integer idRota) throws ControladorException, MobileComunicationException {

		String matriculaImovel = "";
		Integer anoMesFaturamentoGrupoFaturamento = null;
		Rota rota = null;

		Collection<RotaAtualizacaoSeq> colAtuSeq = new ArrayList();
		Collection moviContaPF = new ArrayList();

		try {
			MovimentoContaPrefaturada movimentoContaPreFaturadaIncluido = null;
			MovimentoContaPrefaturadaCategoria movimentoContaPrefaturadaCategoriaIncluido = null;

			SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

			boolean jaSelecionouRegistroTipo1 = false;
			Object[] dadosArquivoTextoRoteiroEmpresa = null;

			for (AtualizarContaPreFaturadaHelper helper : colHelper) {

				if (helper.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {

					if (moviContaPF.isEmpty()) {
						Imovel imovelMovimentoContaPF = this.getControladorImovel()
								.pesquisarImovel(helper.getMatriculaImovel());
						Integer amReferenciaGrupo = repositorioFaturamento
								.retornaAnoMesFaturamentoGrupo(imovelMovimentoContaPF.getId());

						moviContaPF = repositorioFaturamento.pesquisaMovimentoContaPF(imovelMovimentoContaPF.getId(),
								amReferenciaGrupo);
					}

					if ((rota == null || rota.equals(""))) {
						if (idRota != null) {
							rota = this.getControladorMicromedicao().pesquisarRota(idRota);
						} else {
							rota = pesquisarRotaImpressaoSimultanea(helper);
						}

						if (rota != null && !rota.equals("")) {
							dadosArquivoTextoRoteiroEmpresa = repositorioFaturamento
									.pesquisarArquivoTextoRoteiroEmpresa(rota.getId(), helper.getAnoMesFaturamento());

							if (dadosArquivoTextoRoteiroEmpresa != null) {
								Integer idSituacaoTransmissaoLeitura = (Integer) dadosArquivoTextoRoteiroEmpresa[1];

								if (!idSituacaoTransmissaoLeitura.equals(SituacaoTransmissaoLeitura.DISPONIVEL)
										&& !idSituacaoTransmissaoLeitura.equals(SituacaoTransmissaoLeitura.LIBERADO)
										&& !idSituacaoTransmissaoLeitura.equals(SituacaoTransmissaoLeitura.EM_CAMPO)
										&& !idSituacaoTransmissaoLeitura
												.equals(SituacaoTransmissaoLeitura.FINALIZADO_NAO_TRANSMITIDO)) {

									matriculaImovel = "" + helper.getMatriculaImovel();
									throw new MobileComunicationException("atencao.arquivo_ja_finalizado", null);
								}
							}

							if (rota.getFaturamentoGrupo() != null && !rota.getFaturamentoGrupo().equals("")) {
								Integer anoMesGrupo = rota.getFaturamentoGrupo().getAnoMesReferencia();

								if (helper.getAnoMesFaturamento() != null && helper.getAnoMesFaturamento() != 0) {

									if (!anoMesGrupo.equals(helper.getAnoMesFaturamento())) {
										matriculaImovel = "" + helper.getMatriculaImovel();
										throw new MobileComunicationException("atencao.grupo_ja_faturado", null);
									}
								}
							}
						}
					}

					this.removerDadosMovimentosContaPreFaturada(helper);

				}
			}

			BigDecimal valorRateioAgua = new BigDecimal(0);
			BigDecimal valorRateioEsgoto = new BigDecimal(0);

			for (AtualizarContaPreFaturadaHelper helper : colHelper) {
				Imovel imovel = this.getControladorImovel().pesquisarImovel(helper.getMatriculaImovel());

				if ((rota.getIndicadorRotaAlternativa().equals(ConstantesSistema.NAO)
						&& imovel.getRotaAlternativa() == null)
						|| (rota.getIndicadorRotaAlternativa().equals(ConstantesSistema.SIM)
								&& imovel.getRotaAlternativa() != null)) {

					if (helper.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {

						valorRateioAgua = helper.getValorRateioAgua();
						valorRateioEsgoto = helper.getValorRateioEsgoto();

						MovimentoContaPrefaturada movimentoContaPrefaturada = new MovimentoContaPrefaturada();
						movimentoContaPrefaturada.setAnoMesReferenciaPreFaturamento(helper.getAnoMesFaturamento());
						movimentoContaPrefaturada.setImovel(imovel);

						MedicaoTipo medicaoTipo = new MedicaoTipo(helper.getTipoMedicao());
						movimentoContaPrefaturada.setMedicaoTipo(medicaoTipo);

						if (helper.getNumeroConta() != 0) {
							Conta conta = new Conta(helper.getNumeroConta());
							movimentoContaPrefaturada.setConta(conta);
						}

						FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo(helper.getCodigoGrupoFaturamento());
						movimentoContaPrefaturada.setFaturamentoGrupo(faturamentoGrupo);

						if (rota == null || rota.equals("")) {
							rota = pesquisarRotaImpressaoSimultanea(helper);
						}
						movimentoContaPrefaturada.setRota(rota);

						movimentoContaPrefaturada.setLeituraHidrometro(helper.getLeituraHidrometro());

						if (helper.getAnormalidadeLeitura() != null && !helper.getAnormalidadeLeitura().equals(0)) {
							LeituraAnormalidade anormalidadeLeitura = new LeituraAnormalidade(
									helper.getAnormalidadeLeitura());
							movimentoContaPrefaturada.setLeituraAnormalidadeLeitura(anormalidadeLeitura);
						}

						movimentoContaPrefaturada.setDataHoraLeitura(helper.getDataHoraLeituraHidrometro());
						movimentoContaPrefaturada.setIndicadorSituacaoLeitura(helper.getIndicadorConfirmacaoLeitura());
						movimentoContaPrefaturada.setLeituraFaturamento(helper.getLeituraFaturamento());
						movimentoContaPrefaturada.setConsumoMedido(helper.getConsumoMedido());
						movimentoContaPrefaturada.setConsumoCobrado(helper.getConsumoASerCobradoMes());

						if (helper.getTipoConsumo() != null) {
							ConsumoTipo consumoTipo = new ConsumoTipo(helper.getTipoConsumo());
							movimentoContaPrefaturada.setConsumoTipo(consumoTipo);
						}

						if (helper.getAnormalidadeConsumo() != null) {
							ConsumoAnormalidade anormalidadeConsumo = new ConsumoAnormalidade(
									helper.getAnormalidadeConsumo());
							movimentoContaPrefaturada.setConsumoAnormalidade(anormalidadeConsumo);
						}

						movimentoContaPrefaturada.setDataHoraGeracaoMovimento(new Date());
						movimentoContaPrefaturada.setIndicadorAtualizacaoFaturamento(Short.parseShort("2"));
						movimentoContaPrefaturada.setUtlimaAlteracao(new Date());
						movimentoContaPrefaturada.setIndicadorEmissaoConta(helper.getIndicacaoEmissaoConta());
						movimentoContaPrefaturada.setConsumoRateioAgua(helper.getConsumoRateioAgua());
						movimentoContaPrefaturada.setConsumoRateioEsgoto(helper.getConsumoRateioEsgoto());
						movimentoContaPrefaturada.setIndicadorGeracaoConta(helper.getIndicadorGeracaoConta());
						movimentoContaPrefaturada.setValorRateioAgua(valorRateioAgua);
						movimentoContaPrefaturada.setValorRateioEsgoto(valorRateioEsgoto);

						if (helper.getAnormalidadeFaturamento() != null
								&& !helper.getAnormalidadeFaturamento().equals(0)) {
							LeituraAnormalidade anormalidadeLeitura = new LeituraAnormalidade(
									helper.getAnormalidadeFaturamento());
							movimentoContaPrefaturada.setLeituraAnormalidadeFaturamento(anormalidadeLeitura);
						}

						if (helper.getLeituraHidrometroAnterior() != null
								&& !helper.getLeituraHidrometroAnterior().equals("")) {
							movimentoContaPrefaturada
									.setLeituraHidrometroAnterior(helper.getLeituraHidrometroAnterior());
						}

						movimentoContaPrefaturada.setIndicadorAlteracao(ConstantesSistema.NAO);

						matriculaImovel = movimentoContaPrefaturada.getImovel().getId() + "";

						if (moviContaPF.isEmpty()) {
							movimentoContaPrefaturada.setIndicadorRetransmissao(ConstantesSistema.NAO);
						} else {
							movimentoContaPrefaturada.setIndicadorRetransmissao(ConstantesSistema.SIM);
						}

						movimentoContaPrefaturada.setLatitude(helper.getLatitude());
						movimentoContaPrefaturada.setLongitude(helper.getLongitude());
						movimentoContaPrefaturada.setId(
								(Integer) this.getControladorBatch().inserirObjetoParaBatch(movimentoContaPrefaturada));

						if (!jaSelecionouRegistroTipo1) {
							movimentoContaPreFaturadaIncluido = movimentoContaPrefaturada;
							jaSelecionouRegistroTipo1 = true;
						} else {
							jaSelecionouRegistroTipo1 = false;
						}

					} else if (helper.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_2)) {

						jaSelecionouRegistroTipo1 = false;

						Collection<MovimentoContaPrefaturadaCategoria> colMovimentoContaPrefaturadaCategoria = null;

						if (sistemaParametro.getIndicadorTarifaCategoria()
								.equals(SistemaParametro.INDICADOR_TARIFA_CATEGORIA)) {
							FiltroMovimentoContaPrefaturadaCategoria filtroMovimentoContaPrefaturadaCategoria = new FiltroMovimentoContaPrefaturadaCategoria();
							filtroMovimentoContaPrefaturadaCategoria.adicionarParametro(new ParametroSimples(
									FiltroMovimentoContaPrefaturadaCategoria.MOVIMENTO_CONTA_PREFATURADA_ID,
									movimentoContaPreFaturadaIncluido.getId()));

							filtroMovimentoContaPrefaturadaCategoria.adicionarParametro(
									new ParametroSimples(FiltroMovimentoContaPrefaturadaCategoria.ID_CATEGORIA,
											helper.getCodigoCategoria()));

							colMovimentoContaPrefaturadaCategoria = this.getControladorUtil().pesquisar(
									filtroMovimentoContaPrefaturadaCategoria,
									MovimentoContaPrefaturadaCategoria.class.getName());
						}

						MovimentoContaPrefaturadaCategoria movimentoContaPrefaturadaCategoria = null;

						if (colMovimentoContaPrefaturadaCategoria != null
								&& !colMovimentoContaPrefaturadaCategoria.isEmpty()) {

							for (MovimentoContaPrefaturadaCategoria movimentoContaPrefaturadaCategoriaAtualizar : colMovimentoContaPrefaturadaCategoria) {
								BigDecimal valorFaturadoAgua = movimentoContaPrefaturadaCategoriaAtualizar
										.getValorFaturadoAgua().add(helper.getValorFaturadoAgua());
								Integer consumoFaturadoAgua = movimentoContaPrefaturadaCategoriaAtualizar
										.getConsumoFaturadoAgua() + helper.getConsumoFaturadoAgua();

								BigDecimal valorTarifaMinimaAgua = movimentoContaPrefaturadaCategoriaAtualizar
										.getValorTarifaMinimaAgua().add(helper.getValorTarifaMinimaAgua());
								Integer consumoMinimoAgua = movimentoContaPrefaturadaCategoriaAtualizar
										.getConsumoMinimoAgua() + helper.getConsumoMinimoAgua();

								movimentoContaPrefaturadaCategoriaAtualizar.setValorFaturadoAgua(valorFaturadoAgua);
								movimentoContaPrefaturadaCategoriaAtualizar.setConsumoFaturadoAgua(consumoFaturadoAgua);
								movimentoContaPrefaturadaCategoriaAtualizar
										.setValorTarifaMinimaAgua(valorTarifaMinimaAgua);
								movimentoContaPrefaturadaCategoriaAtualizar.setConsumoMinimoAgua(consumoMinimoAgua);

								BigDecimal valorFaturadoEsgoto = movimentoContaPrefaturadaCategoriaAtualizar
										.getValorFaturadoEsgoto().add(helper.getValorFaturadoEsgoto());
								Integer consumoFaturadoEsgoto = movimentoContaPrefaturadaCategoriaAtualizar
										.getConsumoFaturadoEsgoto() + helper.getConsumoFaturadoEsgoto();

								BigDecimal valorTarifaMinimaEsgoto = movimentoContaPrefaturadaCategoriaAtualizar
										.getValorTarifaMinimaEsgoto().add(helper.getValorTarifaMinimaEsgoto());
								Integer consumoMinimoEsgoto = movimentoContaPrefaturadaCategoriaAtualizar
										.getConsumoMinimoEsgoto() + helper.getConsumoMinimoEsgoto();

								movimentoContaPrefaturadaCategoriaAtualizar.setValorFaturadoEsgoto(valorFaturadoEsgoto);
								movimentoContaPrefaturadaCategoriaAtualizar
										.setConsumoFaturadoEsgoto(consumoFaturadoEsgoto);
								movimentoContaPrefaturadaCategoriaAtualizar
										.setValorTarifaMinimaEsgoto(valorTarifaMinimaEsgoto);
								movimentoContaPrefaturadaCategoriaAtualizar.setConsumoMinimoEsgoto(consumoMinimoEsgoto);

								movimentoContaPrefaturadaCategoriaAtualizar.setUltimaAlteracao(new Date());

								this.getControladorUtil().atualizar(movimentoContaPrefaturadaCategoriaAtualizar);

								movimentoContaPrefaturadaCategoria = movimentoContaPrefaturadaCategoriaAtualizar;
							}

							/**
							 * 
							 * Pamela gatinho - 28/05/2012 Alteracao para salvar o valor do rateio junto com
							 * o valor faturado de agua.
							 */
							BigDecimal valorFaturadoAgua = new BigDecimal(0);

							System.out.println(helper.getMatriculaImovel());
							if (valorRateioAgua != null && !valorRateioAgua.equals(new BigDecimal(0)) && helper
									.getIndicadorGeracaoConta().shortValue() == ConstantesSistema.SIM.shortValue()) {
								valorFaturadoAgua = movimentoContaPrefaturadaCategoria.getValorFaturadoAgua()
										.add(helper.getValorRateioAgua());
							} else {
								valorFaturadoAgua = movimentoContaPrefaturadaCategoria.getValorFaturadoAgua();
							}
							movimentoContaPrefaturadaCategoria.setValorFaturadoAgua(valorFaturadoAgua);
							// Atualizar
							this.getControladorUtil().atualizar(movimentoContaPrefaturadaCategoria);

						} else {
							movimentoContaPrefaturadaCategoria = new MovimentoContaPrefaturadaCategoria();

							// Informamos o movimento atual
							MovimentoContaPrefaturadaCategoriaPK pk = new MovimentoContaPrefaturadaCategoriaPK();
							pk.setMovimentoContaPrefaturada(movimentoContaPreFaturadaIncluido);

							Categoria categoria = new Categoria(helper.getCodigoCategoria());
							pk.setCategoria(categoria);

							Subcategoria subcategoria = new Subcategoria(helper.getCodigoSubCategoria());
							pk.setSubcategoria(subcategoria);

							movimentoContaPrefaturadaCategoria.setComp_id(pk);

							BigDecimal valorFaturadoAgua = new BigDecimal(0);
							if (valorRateioAgua != null) {
								valorFaturadoAgua = helper.getValorFaturadoAgua().add(valorRateioAgua);
							} else {
								valorFaturadoAgua = helper.getValorFaturadoAgua();
							}

							movimentoContaPrefaturadaCategoria.setValorFaturadoAgua(valorFaturadoAgua);
							movimentoContaPrefaturadaCategoria.setConsumoFaturadoAgua(helper.getConsumoFaturadoAgua());
							movimentoContaPrefaturadaCategoria
									.setValorTarifaMinimaAgua(helper.getValorTarifaMinimaAgua());
							movimentoContaPrefaturadaCategoria.setConsumoMinimoAgua(helper.getConsumoMinimoAgua());

							BigDecimal valorFaturadoEsgoto = new BigDecimal(0);
							if (valorFaturadoEsgoto != null) {
								valorFaturadoEsgoto = helper.getValorFaturadoEsgoto().add(valorRateioEsgoto);
							} else {
								valorFaturadoEsgoto = movimentoContaPrefaturadaCategoria.getValorFaturadoEsgoto();
							}

							movimentoContaPrefaturadaCategoria.setValorFaturadoEsgoto(valorFaturadoEsgoto);
							movimentoContaPrefaturadaCategoria
									.setConsumoFaturadoEsgoto(helper.getConsumoFaturadoEsgoto());
							movimentoContaPrefaturadaCategoria
									.setValorTarifaMinimaEsgoto(helper.getValorTarifaMinimaEsgoto());
							movimentoContaPrefaturadaCategoria.setConsumoMinimoEsgoto(helper.getConsumoMinimoEsgoto());
							movimentoContaPrefaturadaCategoria.setUltimaAlteracao(new Date());

							this.getControladorBatch().inserirObjetoParaBatch(movimentoContaPrefaturadaCategoria);
						}

						movimentoContaPrefaturadaCategoriaIncluido = movimentoContaPrefaturadaCategoria;

					} else if (helper.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_3)) {

						Collection<MovimentoContaCategoriaConsumoFaixa> colMovimentoContaCategoriaConsumoFaixa = null;

						if (sistemaParametro.getIndicadorTarifaCategoria()
								.equals(SistemaParametro.INDICADOR_TARIFA_CATEGORIA)) {

							FiltroMovimentoContaCategoriaConsumoFaixa filtroMovimentoContaCategoriaConsumoFaixa = new FiltroMovimentoContaCategoriaConsumoFaixa();
							filtroMovimentoContaCategoriaConsumoFaixa.adicionarParametro(new ParametroSimples(
									FiltroMovimentoContaCategoriaConsumoFaixa.MOVIMENTO_CONTA_PREFATURADA_ID,
									movimentoContaPreFaturadaIncluido.getId()));

							filtroMovimentoContaCategoriaConsumoFaixa.adicionarParametro(
									new ParametroSimples(FiltroMovimentoContaCategoriaConsumoFaixa.CATEGORIA_ID,
											helper.getCodigoCategoria()));

							filtroMovimentoContaCategoriaConsumoFaixa.adicionarParametro(new ParametroSimples(
									FiltroMovimentoContaCategoriaConsumoFaixa.LIMITE_INICIAL_CONSUMO_FAIXA,
									helper.getLimiteInicialConsumoFaixa()));

							filtroMovimentoContaCategoriaConsumoFaixa.adicionarParametro(new ParametroSimples(
									FiltroMovimentoContaCategoriaConsumoFaixa.LIMITE_FINAL_CONSUMO_FAIXA,
									helper.getLimiteFinalConsumoFaixa()));

							colMovimentoContaCategoriaConsumoFaixa = this.getControladorUtil().pesquisar(
									filtroMovimentoContaCategoriaConsumoFaixa,
									MovimentoContaCategoriaConsumoFaixa.class.getName());

						}

						if (colMovimentoContaCategoriaConsumoFaixa != null
								&& !colMovimentoContaCategoriaConsumoFaixa.isEmpty()) {

							for (MovimentoContaCategoriaConsumoFaixa movimentoContaCategoriaConsumoFaixa : colMovimentoContaCategoriaConsumoFaixa) {

								BigDecimal valorFaturadoAguaFaixa = movimentoContaCategoriaConsumoFaixa
										.getValorFaturadoAguaNaFaixa().add(helper.getValorFaturadoAguaFaixa());

								Integer consumoFaturadoAguaFaixa = movimentoContaCategoriaConsumoFaixa
										.getConsumoFaturadoAguaNaFaixa() + helper.getConsumoFaturadoAguaFaixa();

								movimentoContaCategoriaConsumoFaixa
										.setConsumoFaturadoAguaNaFaixa(consumoFaturadoAguaFaixa);
								movimentoContaCategoriaConsumoFaixa.setValorFaturadoAguaNaFaixa(valorFaturadoAguaFaixa);

								BigDecimal valorFaturadoEsgotoFaixa = movimentoContaCategoriaConsumoFaixa
										.getValorFaturadoEsgotoNaFaixa().add(helper.getValorFaturadoEsgotoFaixa());
								Integer consumoFaturadoEsgotoFaixa = movimentoContaCategoriaConsumoFaixa
										.getConsumoFaturadoEsgotoNaFaixa() + helper.getConsumoFaturadoEsgotoFaixa();

								BigDecimal valorTarifaFaixa = new BigDecimal("0.00");
								if (helper.getValorTarifaAguaFaixa() != null) {
									valorTarifaFaixa = valorTarifaFaixa.add(helper.getValorTarifaAguaFaixa());
								} else {
									valorTarifaFaixa = valorTarifaFaixa.add(helper.getValorTarifaEsgotoFaixa());
								}

								movimentoContaCategoriaConsumoFaixa
										.setConsumoFaturadoEsgotoNaFaixa(consumoFaturadoEsgotoFaixa);
								movimentoContaCategoriaConsumoFaixa
										.setValorFaturadoEsgotoNaFaixa(valorFaturadoEsgotoFaixa);
								movimentoContaCategoriaConsumoFaixa.setValorTarifaNaFaixa(valorTarifaFaixa);
								movimentoContaCategoriaConsumoFaixa.setUltimaAlteracao(new Date());

								this.getControladorUtil().atualizar(movimentoContaCategoriaConsumoFaixa);

							}
						} else {

							MovimentoContaCategoriaConsumoFaixa movimentoContaCategoriaConsumoFaixa = new MovimentoContaCategoriaConsumoFaixa();

							movimentoContaCategoriaConsumoFaixa
									.setMovimentoContaPrefaturadaCategoria(movimentoContaPrefaturadaCategoriaIncluido);
							movimentoContaCategoriaConsumoFaixa
									.setConsumoFaturadoAguaNaFaixa(helper.getConsumoFaturadoAguaFaixa());
							movimentoContaCategoriaConsumoFaixa
									.setValorFaturadoAguaNaFaixa(helper.getValorFaturadoAguaFaixa());
							movimentoContaCategoriaConsumoFaixa
									.setConsumoFaturadoEsgotoNaFaixa(helper.getConsumoFaturadoEsgotoFaixa());
							movimentoContaCategoriaConsumoFaixa
									.setValorFaturadoEsgotoNaFaixa(helper.getValorFaturadoEsgotoFaixa());
							movimentoContaCategoriaConsumoFaixa
									.setLimiteInicialConsumoNaFaixa(helper.getLimiteInicialConsumoFaixa());
							movimentoContaCategoriaConsumoFaixa
									.setLimiteFinalConsumoNaFaixa(helper.getLimiteFinalConsumoFaixa());

							BigDecimal valorTarifaFaixa = new BigDecimal("0.00");
							if (helper.getValorTarifaAguaFaixa() != null) {
								valorTarifaFaixa = valorTarifaFaixa.add(helper.getValorTarifaAguaFaixa());
							} else {
								valorTarifaFaixa = valorTarifaFaixa.add(helper.getValorTarifaEsgotoFaixa());
							}

							movimentoContaCategoriaConsumoFaixa.setUltimaAlteracao(new Date());
							movimentoContaCategoriaConsumoFaixa.setValorTarifaNaFaixa(valorTarifaFaixa);

							this.getControladorBatch().inserirObjetoParaBatch(movimentoContaCategoriaConsumoFaixa);
						}

					} else if (helper.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_4)) {
						MovimentoContaImpostoDeduzido movimentoContaImpostoDeduzido = new MovimentoContaImpostoDeduzido();

						movimentoContaImpostoDeduzido.setMovimentoContaPrefaturada(movimentoContaPreFaturadaIncluido);

						ImpostoTipo impostoTipo = new ImpostoTipo(helper.getTipoImposto());
						movimentoContaImpostoDeduzido.setImpostoTipo(impostoTipo);

						movimentoContaImpostoDeduzido.setDescricaoImposto(helper.getDescricaoImposto());
						movimentoContaImpostoDeduzido.setPercentualAliquota(helper.getPercentualAliquota());
						movimentoContaImpostoDeduzido.setValorImposto(helper.getValorImposto());
						movimentoContaImpostoDeduzido.setUltimaAlteracao(new Date());

						this.getControladorBatch().inserirObjetoParaBatch(movimentoContaImpostoDeduzido);

					} else if (helper.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_5)) {
						RotaAtualizacaoSeq atuSeq = new RotaAtualizacaoSeq();
						Imovel imo = new Imovel(helper.getMatriculaImovel());

						FiltroImovel filtro = new FiltroImovel();
						filtro.adicionarParametro(new ParametroSimples(FiltroImovel.ID, helper.getMatriculaImovel()));
						filtro.adicionarCaminhoParaCarregamentoEntidade("quadra.rota");

						Collection<Imovel> colImovel = repositorioUtil.pesquisar(filtro, Imovel.class.getName());
						Iterator itImovel = colImovel.iterator();

						imo = (Imovel) itImovel.next();

						colImovel = null;
						itImovel = null;
						filtro = null;

						rota = imo.getQuadra().getRota();

						anoMesFaturamentoGrupoFaturamento = repositorioFaturamento
								.retornaAnoMesFaturamentoGrupo(imo.getId());

						atuSeq.setImovel(imo);
						atuSeq.setRota(rota);
						atuSeq.setAmFaturamento(anoMesFaturamentoGrupoFaturamento);
						atuSeq.setSequencialRota(Integer.parseInt(helper.getSequencialRotaMarcacao()));
						atuSeq.setDtUltimaAlteracao(new Date());

						colAtuSeq.add(atuSeq);
					}
				}
			}

			if (colAtuSeq.size() > 0) {
				repositorioMicromedicao.deletarRotaAtualizacaoSequencial(anoMesFaturamentoGrupoFaturamento,
						rota.getId());

				this.getControladorBatch().inserirColecaoObjetoParaBatchSemTransacao(colAtuSeq);
			}

		} catch (MobileComunicationException mce) {
			throw mce;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControladorException("atencao.erro_inserindo_imovel", e, matriculaImovel);
		}
	}
	
	/*
	 * 
	 * 02/05/2011 - Pamela Gatinho Adicionando o ID da rota como informacao para
	 * finalizar o arquivo de rota.
	 */
	private void incluiDadosArquivoRetorno(ArquivoTextoRetornoIS arquivoRetornoIS, BufferedReader bufferOriginal,
			Collection<AtualizarContaPreFaturadaHelper> colHelper, Integer idRota)
			throws ControladorException, ErroRepositorioException, IOException {

		if (!colHelper.isEmpty()) {

			AtualizarContaPreFaturadaHelper helper = colHelper.iterator().next();
			Rota rota;
			if (idRota != null) {
				rota = this.getControladorMicromedicao().pesquisarRota(idRota);
			} else {
				rota = pesquisarRotaImpressaoSimultanea(helper);
			}

			StringBuilder arquivoRetorno = this.converterArquivoRetorno(bufferOriginal);

			Localidade localidade = new Localidade();
			localidade.setId(helper.getLocalidade());

			if (arquivoRetornoIS != null) {
				arquivoRetornoIS.setFaturamentoGrupo(rota.getFaturamentoGrupo());
				arquivoRetornoIS.setLocalidade(localidade);
				arquivoRetornoIS.setArquivoTexto(arquivoRetorno.toString());

				logger.info("Salvando arquivo retorno " + arquivoRetornoIS.getNomeArquivo() + ", conteudo vazio? "
						+ arquivoRetorno.equals(null));

				Integer idArquivoTextoRetornoIS = (Integer) repositorioUtil.inserir(arquivoRetornoIS);
				arquivoRetornoIS.setId(idArquivoTextoRetornoIS);

			} else {

				logger.info("Arquivo de retorno NULO...");
				arquivoRetornoIS = new ArquivoTextoRetornoIS();

				arquivoRetornoIS.setLocalidade(localidade);
				arquivoRetornoIS.setCodigoSetorComercial(helper.getCodigoSetorComercial());
				arquivoRetornoIS.setCodigoRota(helper.getCodigoRota());
				arquivoRetornoIS.setAnoMesReferencia(helper.getAnoMesFaturamento());
			}

			this.inserirMovimentoArquivoRetornoIS(colHelper, arquivoRetornoIS, rota);
		}
	}
	
	private void removerDadosMovimentosContaPreFaturada(AtualizarContaPreFaturadaHelper helper)
			throws ControladorException {
		try {

			Integer idConta = helper.getNumeroConta();

			FiltroMovimentoContaPrefaturada filtroMovContaPF = new FiltroMovimentoContaPrefaturada();
			filtroMovContaPF.adicionarParametro(
					new ParametroSimples(FiltroMovimentoContaPrefaturada.MATRICULA, helper.getMatriculaImovel()));
			filtroMovContaPF.adicionarParametro(new ParametroSimples(
					FiltroMovimentoContaPrefaturada.ANO_MES_REFERENCIA_PRE_FATURAMENTO, helper.getAnoMesFaturamento()));

			Collection<MovimentoContaPrefaturada> colMovimento = getControladorUtil().pesquisar(filtroMovContaPF,
					MovimentoContaPrefaturada.class.getName());

			for (MovimentoContaPrefaturada movimento : colMovimento) {

				FiltroMovimentoContaPrefaturadaCategoria filtroMovContaPFCategoria = new FiltroMovimentoContaPrefaturadaCategoria();
				filtroMovContaPFCategoria.adicionarParametro(new ParametroSimples(
						FiltroMovimentoContaPrefaturadaCategoria.MOVIMENTO_CONTA_PREFATURADA_ID, movimento.getId()));

				Collection<MovimentoContaPrefaturadaCategoria> colMovimentoContaPFCategoria = getControladorUtil()
						.pesquisar(filtroMovContaPFCategoria, MovimentoContaPrefaturadaCategoria.class.getName());

				for (MovimentoContaPrefaturadaCategoria movimentoCategoria : colMovimentoContaPFCategoria) {

					FiltroMovimentoContaCategoriaConsumoFaixa filtroMovContaCategoriaConsumoFaixa = new FiltroMovimentoContaCategoriaConsumoFaixa();
					filtroMovContaCategoriaConsumoFaixa.adicionarParametro(new ParametroSimples(
							FiltroMovimentoContaCategoriaConsumoFaixa.MOVIMENTO_CONTA_PREFATURADA_ID,
							movimento.getId()));

					Collection<MovimentoContaCategoriaConsumoFaixa> colMovimentoContaCategoriaConsumoFaixa = getControladorUtil()
							.pesquisar(filtroMovContaCategoriaConsumoFaixa,
									MovimentoContaCategoriaConsumoFaixa.class.getName());

					getControladorBatch()
							.removerColecaoObjetoParaBatchSemTransacao(colMovimentoContaCategoriaConsumoFaixa);
					getControladorBatch().removerObjetoParaBatchSemTransacao(movimentoCategoria);
				}

				FiltroMovimentoContaImpostoDeduzido filtroMovContaImpostoDeduzido = new FiltroMovimentoContaImpostoDeduzido();
				filtroMovContaImpostoDeduzido.adicionarParametro(new ParametroSimples(
						FiltroMovimentoContaImpostoDeduzido.MOVIMENTO_CONTA_PREFATURADA_ID, movimento.getId()));

				Collection<MovimentoContaImpostoDeduzido> colMovimentoContaImpostoDeduzido = getControladorUtil()
						.pesquisar(filtroMovContaImpostoDeduzido, MovimentoContaImpostoDeduzido.class.getName());

				if (colMovimentoContaImpostoDeduzido != null && !colMovimentoContaImpostoDeduzido.isEmpty()) {
					getControladorBatch().removerColecaoObjetoParaBatchSemTransacao(colMovimentoContaImpostoDeduzido);
				}

				getControladorBatch().removerObjetoParaBatchSemTransacao(movimento);
			}

			FiltroConta filtroConta = new FiltroConta();
			filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.ID, idConta));

			Collection<Conta> colConta = getControladorUtil().pesquisar(filtroConta, Conta.class.getName());

			if (colConta == null || colConta.isEmpty()) {

				Conta contaAtualizacao = repositorioFaturamento.pesquisarContaPreFaturada(helper.getMatriculaImovel(),
						helper.getAnoMesFaturamento(), DebitoCreditoSituacao.NORMAL);

				colConta = new ArrayList();

				if (contaAtualizacao != null) {

					idConta = contaAtualizacao.getId();
					colConta.add(contaAtualizacao);

				} else {
					contaAtualizacao = repositorioFaturamento.pesquisarContaPreFaturada(helper.getMatriculaImovel(),
							helper.getAnoMesFaturamento(), DebitoCreditoSituacao.PRE_FATURADA);
					if (contaAtualizacao != null) {
						idConta = contaAtualizacao.getId();
						colConta.add(contaAtualizacao);
					}
				}

			}

			BigDecimal valorCreditoNitrato = null;
			CreditoRealizado creditoRealizado = null;

			Conta contaCreditos = new Conta(idConta);

			Collection<CreditoRealizado> colCreditos = Fachada.getInstancia()
					.obterCreditosRealizadosConta(contaCreditos);

			for (CreditoRealizado objeto : (Collection<CreditoRealizado>) colCreditos) {
				if (objeto.getCreditoTipo().getId().equals(CreditoTipo.CREDITO_NITRATO)) {
					creditoRealizado = objeto;
				}
			}

			Object[] dadosCreditoARealizarNitrato = null;

			if (creditoRealizado != null) {
				dadosCreditoARealizarNitrato = repositorioFaturamento.pesquisarCreditoARealizar(
						creditoRealizado.getCreditoARealizarGeral().getId(), helper.getAnoMesFaturamento());
			}

			if (dadosCreditoARealizarNitrato != null && !dadosCreditoARealizarNitrato.equals("")) {
				BigDecimal valorCredito = new BigDecimal("0.00");
				valorCreditoNitrato = new BigDecimal("0.00");
				Integer idCreditoARealizarNitrato = (Integer) dadosCreditoARealizarNitrato[0];
				valorCreditoNitrato = (BigDecimal) dadosCreditoARealizarNitrato[1];
				repositorioFaturamento.atualizarValorCreditoARealizar(idCreditoARealizarNitrato, valorCredito,
						DebitoCreditoSituacao.PRE_FATURADA);
			}

			FiltroContaCategoriaConsumoFaixa filtroContaCategoriaConsumoFaixa = new FiltroContaCategoriaConsumoFaixa();
			filtroContaCategoriaConsumoFaixa
					.adicionarParametro(new ParametroSimples(FiltroContaCategoriaConsumoFaixa.CONTA_ID, idConta));
			Collection<Object> colContaCategoriaConsumoFaixa = getControladorUtil()
					.pesquisar(filtroContaCategoriaConsumoFaixa, ContaCategoriaConsumoFaixa.class.getName());
			getControladorBatch().removerColecaoObjetoParaBatchSemTransacao(colContaCategoriaConsumoFaixa);

			FiltroFaturamentoImediatoAjuste filtroFaturamentoImediatoAjuste = new FiltroFaturamentoImediatoAjuste();
			filtroFaturamentoImediatoAjuste
					.adicionarParametro(new ParametroSimples(FiltroFaturamentoImediatoAjuste.ID_CONTA, idConta));

			Collection<Object> colFaturamentoImediatoAjuste = getControladorUtil()
					.pesquisar(filtroFaturamentoImediatoAjuste, FaturamentoImediatoAjuste.class.getName());
			getControladorBatch().removerColecaoObjetoParaBatchSemTransacao(colFaturamentoImediatoAjuste);

			FiltroContaImpressao filtroContaImpressao = new FiltroContaImpressao();
			filtroContaImpressao.adicionarParametro(new ParametroSimples(FiltroContaImpressao.ID, idConta));

			Collection<Object> colContaImpressao = getControladorUtil().pesquisar(filtroContaImpressao,
					ContaImpressao.class.getName());
			getControladorBatch().removerColecaoObjetoParaBatchSemTransacao(colContaImpressao);

			for (Conta conta : colConta) {

				if (conta.getDebitoCreditoSituacaoAtual().getId() != DebitoCreditoSituacao.PRE_FATURADA) {

					DebitoCreditoSituacao debitoCreditoSituacao = new DebitoCreditoSituacao(
							DebitoCreditoSituacao.PRE_FATURADA);

					conta.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);
					conta.setValorAgua(BigDecimal.ZERO);
					conta.setValorEsgoto(BigDecimal.ZERO);
					conta.setValorImposto(BigDecimal.ZERO);

					if (valorCreditoNitrato != null) {
						BigDecimal valorCreditos = conta.getValorCreditos();
						valorCreditos = valorCreditos.subtract(valorCreditoNitrato);
						conta.setValorCreditos(valorCreditos);
					}

					try {
						repositorioFaturamento
								.zerarValoresContaPassarDebitoCreditoSituacaoAtualPreFaturadaMOBILE(conta);
					} catch (ErroRepositorioException e) {
						throw new ControladorException("erro.sistema", e);
					}
				}
			}
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	private StringBuilder converterArquivoRetorno(BufferedReader buffer) {
		String linha = "";
		StringBuilder arquivoCompleto = new StringBuilder();
		do {

			try {
				linha = buffer.readLine();

				arquivoCompleto.append(linha);
				arquivoCompleto.append(System.getProperty("line.separator"));

			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (linha != null && linha.length() > 0);
		return arquivoCompleto;
	}
	
	private Collection<MovimentoArquivoTextoRetornoIS> inserirMovimentoArquivoRetornoIS(
			Collection<AtualizarContaPreFaturadaHelper> colHelper, ArquivoTextoRetornoIS arquivoTextoRetornoIS,
			Rota rota) throws IOException, ErroRepositorioException {

		Collection<MovimentoArquivoTextoRetornoIS> colecaoMovimentos = null;

		for (AtualizarContaPreFaturadaHelper helper : colHelper) {

			if (helper.getTipoRegistro().equals(new Integer(1))) {

				MovimentoArquivoTextoRetornoIS movimento = new MovimentoArquivoTextoRetornoIS();
				movimento.setAnoMesMovimento(helper.getAnoMesFaturamento());
				movimento.setCodigoRota(helper.getCodigoRota());
				movimento.setCodigoSetorComercial(helper.getCodigoSetorComercial());
				movimento.setFaturamentoGrupo(rota.getFaturamentoGrupo());
				movimento.setImovel(new Imovel(helper.getMatriculaImovel()));
				movimento.setLocalidade(new Localidade(helper.getLocalidade()));
				movimento.setMedicaoTipo(new MedicaoTipo(helper.getTipoMedicao()));
				movimento.setTempoRetornoArquivo(new Date());
				movimento.setUltimaAlteracao(new Date());

				if (arquivoTextoRetornoIS != null && arquivoTextoRetornoIS.getId() != null) {
					movimento.setArquivoTextoRetornoIS(arquivoTextoRetornoIS);
				}

				if (helper.getArquivoImovel().toString() != null) {
					movimento.setNomeArquivo(this.obterNomeArquivoRetorno(arquivoTextoRetornoIS).toString());
					movimento.setArquivoTexto(helper.getArquivoImovel().toString());
				}

				if (helper.getAnormalidadeConsumo() != null) {
					movimento.setConsumoAnormalidade(new ConsumoAnormalidade(helper.getAnormalidadeConsumo()));
				}

				if (helper.getAnormalidadeLeitura() != null) {
					movimento.setLeituraAnormalidade(new LeituraAnormalidade(helper.getAnormalidadeLeitura()));
				}

				if (helper.getLeituraHidrometro() != null) {
					movimento.setLeituraHidrometro(helper.getLeituraHidrometro());
				}

				repositorioUtil.inserir(movimento);
			}
		}

		return colecaoMovimentos;

	}

	public StringBuilder obterNomeArquivoRetorno(ArquivoTextoRetornoIS arquivoRetorno) {
		StringBuilder nomeArquivo = new StringBuilder();

		if (arquivoRetorno.getTipoFinalizacao() != null && arquivoRetorno.getTipoFinalizacao()
				.intValue() == ProcessarRequisicaoDipositivoMovelImpressaoSimultaneaAction.FINALIZAR_LEITURA_ARQUIVO_IMOVEIS_FALTANDO) {
			nomeArquivo.append("GCOMPLETO");
		} else {
			nomeArquivo.append("G");
		}

		nomeArquivo.append(Util.completaStringComZeroAEsquerda(arquivoRetorno.getLocalidade().getId().toString(), 3));
		nomeArquivo.append(Util.completaStringComZeroAEsquerda(arquivoRetorno.getCodigoSetorComercial().toString(), 3));
		nomeArquivo.append(Util.completaStringComZeroAEsquerda(arquivoRetorno.getCodigoRota().toString(), 3));
		nomeArquivo.append(Util.completaString(arquivoRetorno.getAnoMesReferencia().toString(), 6));
		nomeArquivo.append(".txt");

		return nomeArquivo;

	}

}
