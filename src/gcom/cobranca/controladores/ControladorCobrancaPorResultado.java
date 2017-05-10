package gcom.cobranca.controladores;

import gcom.arrecadacao.IRepositorioArrecadacao;
import gcom.arrecadacao.RepositorioArrecadacaoHBM;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.EnvioEmail;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.cadastro.imovel.RepositorioImovelHBM;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.EmpresaCobrancaConta;
import gcom.cobranca.EmpresaCobrancaContaPagamentos;
import gcom.cobranca.IRepositorioCobranca;
import gcom.cobranca.RepositorioCobrancaHBM;
import gcom.cobranca.cobrancaporresultado.ArquivoTextoNegociacaoCobrancaEmpresaBuilder;
import gcom.cobranca.cobrancaporresultado.ArquivoTextoNegociacaoCobrancaEmpresaHelper;
import gcom.cobranca.cobrancaporresultado.ArquivoTextoPagamentoContasCobrancaEmpresaHelper;
import gcom.cobranca.cobrancaporresultado.ArquivoTextoParagentosCobancaEmpresaBuilder;
import gcom.cobranca.cobrancaporresultado.NegociacaoCobrancaEmpresa;
import gcom.cobranca.cobrancaporresultado.NegociacaoContaCobrancaEmpresa;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoItem;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.RepositorioFaturamentoHBM;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.micromedicao.IRepositorioMicromedicao;
import gcom.micromedicao.RepositorioMicromedicaoHBM;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorComum;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.email.ServicosEmail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;


public class ControladorCobrancaPorResultado extends ControladorComum {
	private static final long serialVersionUID = 4498794060506412760L;

	//private static Logger logger = Logger.getLogger(ControladorCobrancaPorResultado.class);

	protected IRepositorioFaturamento repositorioFaturamento;
	protected IRepositorioMicromedicao repositorioMicromedicao;
	protected IRepositorioArrecadacao repositorioArrecadacao;
	protected IRepositorioImovel repositorioImovel;
	protected IRepositorioCobranca repositorioCobranca;
	
	public void ejbCreate() throws CreateException {
		repositorioFaturamento = RepositorioFaturamentoHBM.getInstancia();
		repositorioMicromedicao = RepositorioMicromedicaoHBM.getInstancia();
		repositorioArrecadacao = RepositorioArrecadacaoHBM.getInstancia();
		repositorioImovel = RepositorioImovelHBM.getInstancia();
		repositorioCobranca = RepositorioCobrancaHBM.getInstancia();
	}

	public void gerarNegociacoesCobrancaEmpresa(int idFuncionalidadeIniciada, Integer idEmpresa) throws ControladorException {
		int idUnidadeIniciada = 0;
		
		try {
			List<Integer> negociacoes = new ArrayList<Integer>();
			
			idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
					UnidadeProcessamento.EMPRESA, idEmpresa);
			
			gerarNegociacoesParcelamentos(idEmpresa, negociacoes);
			gerarNegociacoesExtrato(idEmpresa, negociacoes);
			gerarNegociacoesGuias(idEmpresa, negociacoes);
			
			gerarArquivoTextoNegociacoesCobrancaEmpresa(idEmpresa,negociacoes);
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
			
		} catch (Exception e) {
			e.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
		}
		
	}

	private void gerarNegociacoesParcelamentos(Integer idEmpresa, List<Integer> negociacoes) throws ErroRepositorioException, ControladorException {
		List<Parcelamento> parcelamentos = repositorioCobranca.obterParcelamentosCobrancaEmpresa(idEmpresa);
		
		for (Parcelamento parcelamento : parcelamentos ) {
			NegociacaoCobrancaEmpresa negociacao = new NegociacaoCobrancaEmpresa(parcelamento, new Empresa(idEmpresa), new Date());
			
			Integer idNegociacao = (Integer) getControladorUtil().inserir(negociacao);
			negociacao.setId(idNegociacao);
			negociacoes.add(idNegociacao);
			
			List<ContaGeral> contas = repositorioCobranca.obterContasParcelamentosCobrancaEmpresa(parcelamento.getId(), idEmpresa);
			gerarNegociacoesContas(contas, negociacao);
		}
	}
	
	private void gerarNegociacoesExtrato(Integer idEmpresa, List<Integer> negociacoes) throws ErroRepositorioException, ControladorException {
		List<CobrancaDocumento> documentos = repositorioCobranca.obterExtratosCobrancaEmpresa(idEmpresa);
		
		for (CobrancaDocumento documento : documentos ) {
			NegociacaoCobrancaEmpresa negociacao = new NegociacaoCobrancaEmpresa(documento, new Empresa(idEmpresa), new Date());
			
			Integer idNegociacao = (Integer) getControladorUtil().inserir(negociacao);
			negociacao.setId(idNegociacao);
			negociacoes.add(idNegociacao);
			
			List<ContaGeral> contas = repositorioCobranca.obterContasExtratosCobrancaEmpresa(documento.getId(), idEmpresa);
			gerarNegociacoesContas(contas, negociacao);
		}
	}
	
	private void gerarNegociacoesGuias(Integer idEmpresa, List<Integer> negociacoes) throws ErroRepositorioException, ControladorException {
		List<GuiaPagamentoGeral> guias = repositorioCobranca.obterGuiasCobrancaEmpresa(idEmpresa);
		
		for (GuiaPagamentoGeral guia : guias ) {
			NegociacaoCobrancaEmpresa negociacao = new NegociacaoCobrancaEmpresa(guia, new Empresa(idEmpresa), new Date());
			
			Integer idNegociacao = (Integer) getControladorUtil().inserir(negociacao);
			negociacao.setId(idNegociacao);
			negociacoes.add(idNegociacao);
			
			List<ContaGeral> contas = repositorioCobranca.obterContasGuiaCobrancaEmpresa(guia.getId(), idEmpresa);
			gerarNegociacoesContas(contas, negociacao);
		}
	}
	
	private void gerarNegociacoesContas(List<ContaGeral> contas, NegociacaoCobrancaEmpresa negociacao) {
		try {
			for (ContaGeral contaGeral : contas) {
				NegociacaoContaCobrancaEmpresa contaCobranca = new NegociacaoContaCobrancaEmpresa(negociacao, contaGeral, new Date());
					getControladorUtil().inserir(contaCobranca);
			}
		} catch (ControladorException e) {
			e.printStackTrace();
		}
	}
	
	public void gerarArquivoTextoNegociacoesCobrancaEmpresa(Integer idEmpresa, List<Integer> idNegociacoes) throws ControladorException {

		if (!idNegociacoes.isEmpty()) {
			
			try {
				List<NegociacaoCobrancaEmpresa> negociacoes = obterNegociacoesEmpresa(idNegociacoes);
				
				StringBuilder arquivoTxt = new StringBuilder();
				
				for (NegociacaoCobrancaEmpresa negociacao : negociacoes) {
					ArquivoTextoNegociacaoCobrancaEmpresaHelper helper = new ArquivoTextoNegociacaoCobrancaEmpresaBuilder(negociacao).buildHelper();
					arquivoTxt = helper.getArquivoTextoNegociacoes();
				}
				
				enviarEmailArquivoContasCobrancaEmpresa(idEmpresa, arquivoTxt);
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	private List<NegociacaoCobrancaEmpresa> obterNegociacoesEmpresa(List<Integer> idNegociacoes) throws ControladorException {
		List<NegociacaoCobrancaEmpresa> retorno = new ArrayList<NegociacaoCobrancaEmpresa>();
		
		try {
			retorno = repositorioCobranca.obterNegociacoesEmpresa(idNegociacoes);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	private void enviarEmailArquivoContasCobrancaEmpresa(Integer idEmpresa, StringBuilder registros) throws ControladorException {
		String dataHora = Util.formatarDataAAAAMMDD(new Date()) + "-" + Util.formatarDataHHMM(new Date());

		String nomeArquivo = "movimento_contas_cobranca_empresa_" + idEmpresa + "_" + dataHora;
		
		EnvioEmail envioEmail = getControladorCadastro().pesquisarEnvioEmail(EnvioEmail.COBRANCA_EMPRESA);
		
		String titulo = "Negociações da Empresa de Cobrança - " + idEmpresa ;
		String corpo = "Negociações da Empresa de Cobrança : " + idEmpresa ;
		
		ServicosEmail.enviarArquivoCompactado(nomeArquivo, registros, envioEmail.getEmailReceptor(), envioEmail.getEmailRemetente(), titulo, corpo);
	}
	
	public void atualizarPagamentosContasCobranca(int idFuncionalidadeIniciada, Integer idLocalidade)
			throws ControladorException {
		int idUnidadeIniciada = 0;

		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, UnidadeProcessamento.LOCALIDADE, idLocalidade);
		try {

			//repositorioCobranca.removerEmpresaCobrancaContaPagamentos(anoMesArrecadacao, idLocalidade);

			Collection<EmpresaCobrancaContaPagamentos> pagamentosEmpresa = obterPagamentosEmpresa(idLocalidade);
			//gerarArquivoTextoPagamentosCobrancaEmpresa(idEmpresa, idNegociacoes);
			if (!pagamentosEmpresa.isEmpty()) {
				getControladorBatch().inserirColecaoObjetoParaBatch(pagamentosEmpresa);
			}
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);

		} catch (Exception ex) {
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			ex.printStackTrace();

			throw new EJBException(ex);
		}
	}

	private Collection<EmpresaCobrancaContaPagamentos> obterPagamentosEmpresa(Integer idLocalidade) throws ControladorException,
			ErroRepositorioException {
		Collection<Pagamento> collPagamentos = getControladorArrecadacao().pesquisarPagamentosClassificados(idLocalidade);

		Collection<EmpresaCobrancaContaPagamentos> pagamentosEmpresa = new ArrayList<EmpresaCobrancaContaPagamentos>();
		if (collPagamentos != null && !collPagamentos.isEmpty()) {
			
			for (Pagamento pagamento : collPagamentos) {
				
				Categoria categoria = getControladorImovel().obterPrincipalCategoriaImovel(pagamento.getImovel().getId());

				if (!categoria.getId().equals(Categoria.PUBLICO)) {
					pagamentosEmpresa.addAll(gerarPagamentoCobrancaDeContas(pagamento));
					pagamentosEmpresa.addAll(gerarPagamentosCobrancaDeGuias(pagamento));
					pagamentosEmpresa.addAll(gerarPagamentosCobrandaDeDebitos(pagamento));
				}
			}
		}
		return pagamentosEmpresa;
	}

	private Collection<EmpresaCobrancaContaPagamentos> gerarPagamentoCobrancaDeContas(Pagamento pagamento)
			throws ErroRepositorioException, ControladorException {
		
		Collection<EmpresaCobrancaContaPagamentos> pagamentosEmpresa = new ArrayList<EmpresaCobrancaContaPagamentos>();
		
		if (pagamento.getContaGeral() != null) {

			if (isContaEmCobranca(pagamento)) {
			
				pagamentosEmpresa.addAll(criaColecaoEmpresaContaCobrancaPagamento(pagamento.getContaGeral().getId(), pagamento.getValorPagamento(),
						pagamento, null, null, false, null, ConstantesSistema.INDICADOR_PAGAMENTO_A_VISTA, null));
			
			} else {
				List<DebitoCobrado> debitosCobrados = obterDebitosDePagamentoDeParcelamento(pagamento);

				for (DebitoCobrado debitoCobrado : debitosCobrados) {
					Parcelamento parcelamento = debitoCobrado.getDebitoACobrarGeral().getDebitoACobrar().getParcelamento();
					pagamentosEmpresa.addAll(verificarItensParcelamentos(parcelamento, null, null, pagamento, debitoCobrado, pagamento.getAnoMesReferenciaArrecadacao()));
				}
			}
		}
		return pagamentosEmpresa;
	}

	private Boolean isContaEmCobranca(Pagamento pagamento) throws ErroRepositorioException {
		Integer idEmpresaCobrancaConta = repositorioCobranca.pesquisarEmpresaCobrancaConta(pagamento.getContaGeral().getId());
		
		if (idEmpresaCobrancaConta != null) return true;
		else return false;
	}

	private Collection<EmpresaCobrancaContaPagamentos> gerarPagamentosCobrandaDeDebitos(Pagamento pagamento) throws ControladorException {
		Collection<EmpresaCobrancaContaPagamentos> pagamentosEmpresa = new ArrayList<EmpresaCobrancaContaPagamentos>();

		if (pagamento.getDebitoACobrarGeral() != null
				&& (pagamento.getDebitoACobrarGeral().getDebitoACobrar().getParcelamento() != null)) {

			pagamentosEmpresa.addAll(verificarItensParcelamentos(pagamento.getDebitoACobrarGeral().getDebitoACobrar().getParcelamento(), null,
					pagamento.getDebitoACobrarGeral().getDebitoACobrar(), pagamento, null, pagamento.getAnoMesReferenciaArrecadacao()));
		}
		
		return pagamentosEmpresa;
	}

	private Collection<EmpresaCobrancaContaPagamentos> gerarPagamentosCobrancaDeGuias(Pagamento pagamento) throws ControladorException {
		Collection<EmpresaCobrancaContaPagamentos> pagamentosEmpresa = new ArrayList<EmpresaCobrancaContaPagamentos>();

		if (pagamento.getGuiaPagamento() != null && pagamento.getGuiaPagamento().getParcelamento() != null) {
			
			pagamentosEmpresa.addAll(verificarItensParcelamentos(pagamento.getGuiaPagamento().getParcelamento(), pagamento.getGuiaPagamento(), null, 
					pagamento, null, pagamento.getAnoMesReferenciaArrecadacao()));
		}
		return pagamentosEmpresa;
	}

	private List<DebitoCobrado> obterDebitosDePagamentoDeParcelamento(Pagamento pagamento) throws ErroRepositorioException {
		
		Collection<Integer> financiamentos = obterFinanciamentoTipoParcelamento();
		Collection<Object[]> colecaoDadosDebitoCobrado = repositorioFaturamento.pesquisaridDebitoTipoDoDebitoCobradoDeParcelamento(
															pagamento.getContaGeral().getId(), financiamentos);
		
		List<DebitoCobrado> debitos = new ArrayList<DebitoCobrado>();

		if (colecaoDadosDebitoCobrado != null && !colecaoDadosDebitoCobrado.isEmpty()) {

			for (Object[] dadosDebitoCobrado : colecaoDadosDebitoCobrado) {
				if (dadosDebitoCobrado != null) {
					DebitoTipo debitoTipo = null;
					Parcelamento parcelamento = null;
					DebitoCobrado debitoCobrado = null;

					if (dadosDebitoCobrado[3] != null) {
						debitoCobrado = new DebitoCobrado();
						debitoCobrado.setValorPrestacao((BigDecimal) dadosDebitoCobrado[3]);
						debitoCobrado.setNumeroPrestacaoDebito((Short) dadosDebitoCobrado[4]);
						debitoCobrado.setNumeroPrestacao((Short) dadosDebitoCobrado[5]);
						if (dadosDebitoCobrado[0] != null) {
							debitoTipo = new DebitoTipo();
							debitoTipo.setId((Integer) dadosDebitoCobrado[0]);
							debitoCobrado.setDebitoTipo(debitoTipo);
						}

						if (dadosDebitoCobrado[1] != null) {
							parcelamento = new Parcelamento();
							parcelamento.setId((Integer) dadosDebitoCobrado[1]);
							if (dadosDebitoCobrado[2] != null) {
								parcelamento.setValorDebitoAtualizado((BigDecimal) dadosDebitoCobrado[2]);
							}
							if (dadosDebitoCobrado[6] != null) {
								parcelamento.setValorConta((BigDecimal) dadosDebitoCobrado[6]);
							}
							DebitoACobrar debitoACobrar = new DebitoACobrar();
							debitoACobrar.setParcelamento(parcelamento);
							
							debitoCobrado.setDebitoACobrarGeral(new DebitoACobrarGeral(debitoACobrar));
						}
					}
					
					debitos.add(debitoCobrado);
				}
			}
		}
		
		return debitos;
	}

	private Collection<Integer> obterFinanciamentoTipoParcelamento() {
		Collection<Integer> collIdsFincanciamentoTipo = new ArrayList<Integer>();
		
		collIdsFincanciamentoTipo.add(FinanciamentoTipo.PARCELAMENTO_AGUA);
		collIdsFincanciamentoTipo.add(FinanciamentoTipo.PARCELAMENTO_ESGOTO);
		collIdsFincanciamentoTipo.add(FinanciamentoTipo.PARCELAMENTO_SERVICO);
		collIdsFincanciamentoTipo.add(FinanciamentoTipo.JUROS_PARCELAMENTO);
		collIdsFincanciamentoTipo.add(FinanciamentoTipo.ENTRADA_PARCELAMENTO);
		
		return collIdsFincanciamentoTipo;
	}
	
	private Collection<EmpresaCobrancaContaPagamentos> criaColecaoEmpresaContaCobrancaPagamento(Integer idConta, BigDecimal valorConta, Pagamento pagamento,
			DebitoTipo debitoTipo, Parcelamento parcelamento, boolean nivel2, BigDecimal valorPagamentoSemPercentual,
			Short indicadorTipoPagamento, DebitoCobrado debitoCobrado) throws ControladorException {

		Collection<EmpresaCobrancaContaPagamentos> pagamentosEmpresa = new ArrayList<EmpresaCobrancaContaPagamentos>();
		try {

			Integer idEmpresa = repositorioCobranca.pesquisarEmpresaCobrancaConta(idConta);

			if (idEmpresa != null) {
				// caso seja um re-parcelamento
				BigDecimal percentualContaParcelada = null;
				BigDecimal valorPagamentoMes = null;

				if (nivel2) {
					percentualContaParcelada = Util.dividirArredondando(valorConta, parcelamento.getValorConta());
					valorPagamentoMes = (valorPagamentoSemPercentual.multiply(percentualContaParcelada)).setScale(2,
							BigDecimal.ROUND_HALF_DOWN);
				} else {
					// caso exista parcelamento, calcular o percentual da conta paga
					if (parcelamento != null) {
						percentualContaParcelada = Util.dividirArredondando(valorConta, parcelamento.getValorConta());
						valorPagamentoMes = (valorPagamentoSemPercentual.multiply(percentualContaParcelada)).setScale(2,
								BigDecimal.ROUND_HALF_DOWN);
					} else {
						// caso não tenha parcelamento, o pagamento refere-se a uma conta e esteja em cobrança por alguma empresa.
						valorPagamentoMes = valorConta;
					}
				}

				EmpresaCobrancaContaPagamentos pagamentoEmpresa = new EmpresaCobrancaContaPagamentos();
				
				EmpresaCobrancaConta empresaCobrancaConta = new EmpresaCobrancaConta();
				empresaCobrancaConta.setId(idEmpresa);

				pagamentoEmpresa.setDebitoTipo(debitoTipo);
				pagamentoEmpresa.setEmpresaCobrancaConta(empresaCobrancaConta);
				pagamentoEmpresa.setAnoMesPagamentoArrecadacao(pagamento.getAnoMesReferenciaArrecadacao());
				pagamentoEmpresa.setValorPagamentoMes(valorPagamentoMes);
				pagamentoEmpresa.setIndicadorTipoPagamento(indicadorTipoPagamento);
				pagamentoEmpresa.setNumeroParcelaAtual(debitoCobrado != null ? new Integer(debitoCobrado.getNumeroPrestacaoDebito()) : new Integer("0"));
				pagamentoEmpresa.setNumeroTotalParcelas(debitoCobrado != null ? new Integer(debitoCobrado.getNumeroPrestacao()) : new Integer("0"));
				pagamentoEmpresa.setUltimaAlteracao(new Date());
				
				if (pagamento.getAnoMesReferenciaPagamento() != null) {
					pagamentoEmpresa.setAnoMesReferenciaPagamento(pagamento.getAnoMesReferenciaPagamento());
				}
				
				pagamentoEmpresa.setDataPagamento(pagamento.getDataPagamento());
				
				if (pagamento.getImovel() != null) {
					pagamentoEmpresa.setIdImovel(pagamento.getImovel().getId());
				}
				
				if (pagamento.getAvisoBancario() != null) {
					pagamentoEmpresa.setIdArrecadador(pagamento.getAvisoBancario().getArrecadador().getId());
				}
				pagamentosEmpresa.add(pagamentoEmpresa);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new EJBException(ex);
		}
		return pagamentosEmpresa;
	}
	
	private Collection<EmpresaCobrancaContaPagamentos> verificarItensParcelamentos(Parcelamento parcelamento, GuiaPagamento guiaPagamento, DebitoACobrar debitoACobrar,
			Pagamento pagamento, DebitoCobrado debitoCobrado, Integer anoMesArrecadacao)
			throws ControladorException {
		
		Collection<EmpresaCobrancaContaPagamentos> pagamentosEmpresa = new ArrayList<EmpresaCobrancaContaPagamentos>();
		try {

			if (parcelamento != null) {

				List<ParcelamentoItem> itens = repositorioCobranca.pesquisarItensParcelamentos(parcelamento.getId());
				
				if (itens != null && !itens.isEmpty()) {
					
					for (ParcelamentoItem item : itens) {

						if (item.getContaGeral() != null) {
							
							BigDecimal valorConta = item.getContaGeral().obterValorConta();

							// caso não seja guia de pagamento nem debito a cobrar
							if (guiaPagamento == null && debitoACobrar == null) {
								
								// [SB0003] - Atualizar pagamento de conta parcelada a partir do debito cobrado
								pagamentosEmpresa.addAll(criaColecaoEmpresaContaCobrancaPagamento(item.getContaGeral().getId(), valorConta, pagamento,
										debitoCobrado.getDebitoTipo(), parcelamento, false, debitoCobrado.getValorPrestacao(),
										ConstantesSistema.INDICADOR_PAGAMENTO_PARCELADO,debitoCobrado));
							} else {
								if (guiaPagamento != null) {
									// [SB0007] - Atualizar pagamento de conta parcelada a partir da guia de pagamento
									pagamentosEmpresa.addAll(criaColecaoEmpresaContaCobrancaPagamento(item.getContaGeral().getId(), valorConta, pagamento,
											guiaPagamento.getDebitoTipo(), parcelamento, false, guiaPagamento.getValorDebito(),
											ConstantesSistema.INDICADOR_PAGAMENTO_A_VISTA, null));
								} else {
									// [SB0011] - Atualizar pagamento de conta parcelada a partir do debito a cobrar
									pagamentosEmpresa.addAll(criaColecaoEmpresaContaCobrancaPagamento(item.getContaGeral().getId(), valorConta, pagamento,
											debitoACobrar.getDebitoTipo(), parcelamento, false, debitoACobrar.getValorTotalComBonus(),
											ConstantesSistema.INDICADOR_PAGAMENTO_PARCELADO, null));
								}
							}
						}
						
						// verifica se existe o id do debito a cobrar geral, refeere-se a um re-parcelamento
						if (item.getDebitoACobrarGeral() != null) {
							pagamentosEmpresa.addAll(verificarItensParcelamentosNivel2(parcelamento, guiaPagamento, debitoACobrar, pagamento, debitoCobrado,
									anoMesArrecadacao, item.getDebitoACobrarGeral().getId()));
						}

					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new EJBException(ex);
		}
		
		return pagamentosEmpresa;
	}
	
	@SuppressWarnings("unused")
	private Collection<EmpresaCobrancaContaPagamentos> verificarItensParcelamentosNivel2(Parcelamento parcelamento, GuiaPagamento guiaPagamento, DebitoACobrar debitoACobrar,
			Pagamento pagamento, DebitoCobrado debitoCobrado, Integer anoMesArrecadacao, Integer idDebitoACobrarNivel2) throws ControladorException {
		
		Collection<EmpresaCobrancaContaPagamentos> pagamentosEmpresa = new ArrayList<EmpresaCobrancaContaPagamentos>();
		
		try {

			Integer idParcelamento = null;

			if (idParcelamento != null) {

				Collection<Object[]> collItensParcelamentosNivel2 = repositorioCobranca.pesquisarItensParcelamentosNivel2(idParcelamento);

				Integer idContaGeralNivel2 = null;
				BigDecimal valorContaNivel2 = null;

				if (collItensParcelamentosNivel2 != null && !collItensParcelamentosNivel2.isEmpty()) {
					for (Object[] dadosItensParcelamento : collItensParcelamentosNivel2) {

						if (dadosItensParcelamento != null) {

							if (dadosItensParcelamento[2] != null) {
								valorContaNivel2 = (BigDecimal) dadosItensParcelamento[2];
							}

							if (dadosItensParcelamento[0] != null) {
								idContaGeralNivel2 = (Integer) dadosItensParcelamento[0];

								if (guiaPagamento == null && debitoACobrar == null) {
									// [SB0005] - Atualizar pagamento de conta parcelada a partir do debito cobrado - nivel 2
									
									pagamentosEmpresa.addAll(criaColecaoEmpresaContaCobrancaPagamento(idContaGeralNivel2, valorContaNivel2, pagamento,
											debitoCobrado.getDebitoTipo(), parcelamento, true, debitoCobrado.getValorPrestacao(),
											ConstantesSistema.INDICADOR_PAGAMENTO_PARCELADO, debitoCobrado));
								} else {
									if (guiaPagamento != null) {
										// [SB0008] - Atualizar pagamento de conta parcelada a partir da guia de - pagamento nivel 2
										pagamentosEmpresa.addAll(criaColecaoEmpresaContaCobrancaPagamento(idContaGeralNivel2, valorContaNivel2, pagamento,
												guiaPagamento.getDebitoTipo(), parcelamento, true, guiaPagamento.getValorDebito(),
												ConstantesSistema.INDICADOR_PAGAMENTO_A_VISTA, null));
									} else {
										// [SB0013] - Atualizar pagamento de conta parcelada a partir do debito a cobrar nivel 2
										pagamentosEmpresa.addAll(criaColecaoEmpresaContaCobrancaPagamento(idContaGeralNivel2, valorContaNivel2, pagamento,
												debitoACobrar.getDebitoTipo(), parcelamento, true, debitoACobrar.getValorTotalComBonus(),
												ConstantesSistema.INDICADOR_PAGAMENTO_PARCELADO, null));
									}
								}
							}
						}
						idContaGeralNivel2 = null;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new EJBException(ex);
		}
		return pagamentosEmpresa;
	}

	
	@SuppressWarnings("rawtypes")
	public void gerarArquivoTextoPagamentosCobrancaEmpresa(Integer idFuncionalidadeIniciada, Integer idEmpresa) throws ControladorException {
		int idUnidadeIniciada = 0;

		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, UnidadeProcessamento.EMPRESA, idEmpresa);
		try {

			Collection colecaoDadosTxt = repositorioCobranca.pesquisarDadosArquivoTextoPagamentosContasCobrancaEmpresa(idEmpresa);
			
			if (colecaoDadosTxt != null && !colecaoDadosTxt.isEmpty()) {

				Iterator colecaoDadosTxtIterator = colecaoDadosTxt.iterator();

				while (colecaoDadosTxtIterator.hasNext()) {

					Object[] dados = (Object[]) colecaoDadosTxtIterator.next();

					ArquivoTextoPagamentoContasCobrancaEmpresaHelper helper = new ArquivoTextoParagentosCobancaEmpresaBuilder(dados).buildHelper(); 

					enviarEmailArquivoPagamentos(idEmpresa, helper.getArquivoTexto());
				}
				
				getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
			}
		} catch (Exception ex) {
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			ex.printStackTrace();

			throw new EJBException(ex);
		}
	}
	
	private void enviarEmailArquivoPagamentos(Integer idEmpresa, StringBuilder arquivo) throws ControladorException {
		String dataHora = Util.formatarDataAAAAMMDD(new Date()) + "-" + Util.formatarDataHHMM(new Date());

		String nomeArquivo = "pagamentos_contas_cobranca_empresa_" + idEmpresa + "_" + dataHora;
		
		EnvioEmail envioEmail = getControladorCadastro().pesquisarEnvioEmail(EnvioEmail.COBRANCA_EMPRESA);
		
		String titulo = "Negociações da Empresa de Cobrança - " + idEmpresa ;
		String corpo = "Negociações da Empresa de Cobrança : " + idEmpresa ;
		
		ServicosEmail.enviarArquivoCompactado(nomeArquivo, arquivo, envioEmail.getEmailReceptor(), envioEmail.getEmailRemetente(), titulo, corpo);
		
	}

}
