package gcom.cobranca.controladores;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.batch.Processo;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.EnvioEmail;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.endereco.FiltroLogradouroTipo;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelCobrancaSituacao;
import gcom.cadastro.imovel.FiltroImovelSubCategoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.CobrancaSituacaoHistorico;
import gcom.cobranca.CobrancaSituacaoTipo;
import gcom.cobranca.ComandoEmpresaCobrancaConta;
import gcom.cobranca.ComandoEmpresaCobrancaContaHelper;
import gcom.cobranca.EmpresaCobrancaConta;
import gcom.cobranca.EmpresaCobrancaContaPagamentos;
import gcom.cobranca.FiltroCobrancaSituacaoHistorico;
import gcom.cobranca.GerarArquivoTextoContasCobrancaEmpresaHelper;
import gcom.cobranca.IRepositorioCobranca;
import gcom.cobranca.NegativacaoImoveis;
import gcom.cobranca.RelatorioPagamentosContasCobrancaEmpresaHelper;
import gcom.cobranca.RepositorioCobrancaHBM;
import gcom.cobranca.UC0870GerarMovimentoContasEmCobrancaPorEmpresa;
import gcom.cobranca.cobrancaporresultado.ArquivoTextoNegociacaoCobrancaEmpresaBuilder;
import gcom.cobranca.cobrancaporresultado.ArquivoTextoNegociacaoCobrancaEmpresaHelper;
import gcom.cobranca.cobrancaporresultado.ArquivoTextoPagamentoContasCobrancaEmpresaHelper;
import gcom.cobranca.cobrancaporresultado.ArquivoTextoParagentosCobancaEmpresaBuilder;
import gcom.cobranca.cobrancaporresultado.NegociacaoCobrancaEmpresa;
import gcom.cobranca.cobrancaporresultado.NegociacaoContaCobrancaEmpresa;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoItem;
import gcom.cobranca.repositorios.IRepositorioCobrancaPorResultadoHBM;
import gcom.cobranca.repositorios.RepositorioCobrancaPorResultadoHBM;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.micromedicao.IRepositorioMicromedicao;
import gcom.micromedicao.RepositorioMicromedicaoHBM;
import gcom.relatorio.cobranca.RelatorioPagamentosContasCobrancaEmpresaBean;
import gcom.spcserasa.FiltroNegativacaoImoveis;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorComum;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.email.ServicosEmail;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.apache.log4j.Logger;


public class ControladorCobrancaPorResultado extends ControladorComum {
	private static final long serialVersionUID = 4498794060506412760L;
	
	private static Logger logger = Logger.getLogger(ControladorCobrancaPorResultado.class);

	protected IRepositorioCobrancaPorResultadoHBM repositorio;
	protected IRepositorioCobranca repositorioCobranca;
	protected IRepositorioMicromedicao repositorioMicromedicao;

	public void ejbCreate() throws CreateException {
		repositorio = RepositorioCobrancaPorResultadoHBM.getInstancia();
		repositorioCobranca = RepositorioCobrancaHBM.getInstancia();
		repositorioMicromedicao = RepositorioMicromedicaoHBM.getInstancia();
	}

	public List<Object[]> pesquisarQuantidadeContas(ComandoEmpresaCobrancaContaHelper helper, boolean agrupadoPorImovel, boolean percentualInformado) throws ControladorException {
		try {
			SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
			
			return repositorio.pesquisarQuantidadeContas(helper, agrupadoPorImovel, percentualInformado, sistemaParametro.getAnoMesFaturamento());
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	public void gerarMovimentoContas(ComandoEmpresaCobrancaConta comando, int idFuncionalidadeIniciada) throws ControladorException {
		int idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, UnidadeProcessamento.COMANDO_EMPRESA_COBRANCA_CONTA, comando.getId());

		try {
			logger.info("Cobrança por Resultado - Iniciou Comando: " + comando.getId());

			UC0870GerarMovimentoContasEmCobrancaPorEmpresa movimento = UC0870GerarMovimentoContasEmCobrancaPorEmpresa.getInstancia(repositorioCobranca);
			movimento.gerarMovimentoContasEmCobranca(comando);

			comando.setDataExecucao(new Date());
			comando.setUltimaAlteracao(new Date());

			getControladorUtil().atualizar(comando);

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);

			logger.info("Cobrança por Resultado - Finalizou Comando: " + comando.getId());
		} catch (Exception e) {
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
			throw new EJBException(e);
		}
	}
	
	public List<Integer> pesquisarImoveis(ComandoEmpresaCobrancaContaHelper helper, boolean agrupadoPorImovel, boolean percentualInformado, Integer anoMesFaturamento) throws ControladorException {
		try {
			return repositorio.pesquisarImoveis(helper, agrupadoPorImovel, percentualInformado, anoMesFaturamento);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	public void gerarArquivoContas(Collection<Integer> comandos, Integer idEmpresa, int idFuncionalidadeIniciada) throws ControladorException {
		int idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, UnidadeProcessamento.FUNCIONALIDADE, 0);

		try {
			SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
			Integer idPerfilProgramaEspecial = sistemaParametro.getPerfilProgramaEspecial() != null ? sistemaParametro.getPerfilProgramaEspecial().getId() : null;

			Collection<Object[]> colecao = repositorioCobranca.pesquisarDadosArquivoTextoContasCobrancaEmpresa(comandos, idPerfilProgramaEspecial);

			StringBuilder registros = new StringBuilder();
			for (Iterator<Object[]> iterator = colecao.iterator(); iterator.hasNext();) {
				Object[] dados = (Object[]) iterator.next();

				if (dados[30] == null || ((Short) dados[30]).equals(ConstantesSistema.SIM)) {
					registros = gerarArquivoLayoutPadrao(comandos, dados, registros);
				} else {
					registros = gerarArquivoLayoutTipo02(comandos, dados, registros);
				}
			}
			
			enviarEmailArquivoContas(idEmpresa, comandos, registros);
			repositorioCobranca.atualizarIndicadorGeracaoTxt(comandos);

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
		} catch (Exception e) {
			e.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
		}
	}
	
	private void enviarEmailArquivoContas(Integer idEmpresa, Collection<Integer> comandos, StringBuilder registros) throws ControladorException {
		String dataHora = Util.formatarDataAAAAMMDD(new Date()) + "-" + Util.formatarDataHHMM(new Date());
		String nome = "contas_cobranca_empresa-" + idEmpresa + "_" + dataHora;
		
		EnvioEmail envioEmail = getControladorCadastro().pesquisarEnvioEmail(EnvioEmail.COBRANCA_EMPRESA);
		
		String corpo = envioEmail.getCorpoMensagem() + " - PROCESSO: " + Processo.GERAR_ARQUIVO_TEXTO_CONTAS_COBRANCA_EMPRESA 
				+ "\nEmpresa: " + idEmpresa 
				+ "\nComando(s): " + comandos.toString().replace("[", "").replace("]", ".");
		
		ServicosEmail.enviarArquivoCompactado(nome, registros, envioEmail.getEmailReceptor(), envioEmail.getEmailRemetente(), envioEmail.getTituloMensagem(), corpo);
	}
	
	private StringBuilder gerarArquivoLayoutPadrao(Collection<Integer> comandos, Object[] dados, StringBuilder registros) {
		GerarArquivoTextoContasCobrancaEmpresaHelper helper = buildDadosArquivoContasLayoutPadrao(dados, comandos);
		
		return helper.buildArquivoContasLayoutPadrao(registros);
	}
	
	private GerarArquivoTextoContasCobrancaEmpresaHelper buildDadosArquivoContasLayoutPadrao(Object[] dados, Collection<Integer> comandos) {
		GerarArquivoTextoContasCobrancaEmpresaHelper helper = new GerarArquivoTextoContasCobrancaEmpresaHelper();
		
		helper.setIdCobrancaConta((Integer) dados[0]);
		helper.setIdUnidadeNegocio((Integer) dados[1]);
		helper.setNomeUnidadeNegocio((String) dados[2]);
		helper.setIdFaturamentoGrupo((Integer) dados[3]);
		helper.setIdLocalidade((Integer) dados[4]);
		helper.setNomeLocalidade((String) dados[5]);
		helper.setCodigoRota((Short) dados[6]);
		helper.setNumeroSequencialRota((Integer) dados[7]);
		helper.setIdImovel((Integer) dados[8]);
		helper.setNomeClienteConta((String) dados[9]);
		helper.setNomeAbreviadoCliente((String) dados[17]);
		helper.setIdClienteTipo((Integer) dados[10]);
		helper.setCpf((String) dados[11]);
		helper.setCnpj((String) dados[12]);
		helper.setRg((String) dados[13]);
		helper.setNumeroQuadra((Integer) dados[14]);
		helper.setCodigoSetorComercial((Integer) dados[24]);
		helper.setNumeroLote((Short) dados[25]);
		helper.setNumeroSublote((Short) dados[26]);
		helper.setIdCliente((Integer) dados[27]);
		helper.setIdGerenciaRegional((Integer) dados[28]);
		helper.setNomeGerenciaRegional((String) dados[29]);
		helper.setCodigoLayoutTxt((Short) dados[30]);
		helper.setDataInicioCiclo((Date) dados[33]);
		helper.setDataFimCiclo((Date) dados[34]);
		
		if (dados[15] != null) {
			Conta conta = new Conta();
			conta.setId((Integer) dados[15]);
			conta.setReferencia((Integer) dados[18]);
			conta.setDataVencimentoConta((Date) dados[19]);
			conta.setValorAgua((BigDecimal) dados[20]);
			conta.setValorEsgoto((BigDecimal) dados[21]);
			conta.setDebitos((BigDecimal) dados[22]);
			conta.setValorCreditos((BigDecimal) dados[23]);
			helper.setConta(conta);
		}

		if (dados[32] != null && dados[16] != null) {
			String ddd = (String) dados[32];
			String telefone = (String) dados[16];
			helper.setTelefone(ddd + telefone);
		}

		try {
			helper = buildDadosEnderecoArquivoContasLayoutPadrao(helper);
			helper = buildDadosQuantidadeArquivoContas(helper, comandos);
			helper = buildDadosImovelArquivoContas(helper);
			helper = buildDadosSubcategoriasArquivoContas(helper);
			helper = buildDadosClientesArquivoContas(helper);
			helper = buildDadosNegativacaoArquivoContas(helper);
		} catch (ControladorException e) {
			e.printStackTrace();
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		
		return helper;
	}
	
	@SuppressWarnings("unchecked")
	private GerarArquivoTextoContasCobrancaEmpresaHelper buildDadosNegativacaoArquivoContas(GerarArquivoTextoContasCobrancaEmpresaHelper helper) throws ControladorException {
		FiltroNegativacaoImoveis filtro = new FiltroNegativacaoImoveis();
		filtro.adicionarParametro(new ParametroSimples(FiltroNegativacaoImoveis.IMOVEL_ID, helper.getIdImovel()));
		filtro.adicionarParametro(new ParametroSimples(FiltroNegativacaoImoveis.INDICADOR_EXCLUIDO, ConstantesSistema.NAO));
		filtro.adicionarParametro(new ParametroNulo(FiltroNegativacaoImoveis.DATA_EXCLUSAO));
		filtro.adicionarParametro(new ParametroNaoNulo(FiltroNegativacaoImoveis.DATA_CONFIRMACAO));
		
		Collection<NegativacaoImoveis> colecao = getControladorUtil().pesquisar(filtro, NegativacaoImoveis.class.getName());
		
		if (colecao != null && !colecao.isEmpty()) {
			NegativacaoImoveis negativacao = (NegativacaoImoveis) Util.retonarObjetoDeColecao(colecao);
			
			helper.setIndicadorImovelNegativado(ConstantesSistema.SIM);
			helper.setDataNegativacao(negativacao.getDataConfirmacao());
		} else {
			helper.setIndicadorImovelNegativado(ConstantesSistema.NAO);
		}
		
		return helper;
	}

	@SuppressWarnings("unchecked")
	private GerarArquivoTextoContasCobrancaEmpresaHelper buildDadosClientesArquivoContas(GerarArquivoTextoContasCobrancaEmpresaHelper helper) throws ControladorException {
		FiltroClienteImovel filtro = new FiltroClienteImovel();
		filtro.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, helper.getIdImovel()));
		filtro.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE_FONE);
		
		Collection<ClienteImovel> colecao = getControladorUtil().pesquisar(filtro, ClienteImovel.class.getName());
		
		for (Iterator<ClienteImovel> iterator = colecao.iterator(); iterator.hasNext();) {
			ClienteImovel clienteImovel = (ClienteImovel) iterator.next();
			
			if (clienteImovel.getClienteRelacaoTipo().getId().shortValue() == ClienteRelacaoTipo.USUARIO.shortValue()) {
				helper.montarDadosClienteUsuario(clienteImovel);
			} else if (clienteImovel.getClienteRelacaoTipo().getId().shortValue() == ClienteRelacaoTipo.RESPONSAVEL.shortValue()) {
				helper.montarDadosClienteResponsavel(clienteImovel);
			} else {
				helper.montarDadosClienteProprietario(clienteImovel);
			}
		}
		
		return helper;
	}

	private GerarArquivoTextoContasCobrancaEmpresaHelper buildDadosQuantidadeArquivoContas(
			GerarArquivoTextoContasCobrancaEmpresaHelper helper, Collection<Integer> comandos) throws ErroRepositorioException {
		
		Integer quantidadeContas = repositorioCobranca.pesquisarQuantidadeContasArquivoTextoContasCobrancaEmpresa(comandos, helper.getIdImovel());
		if (quantidadeContas != null) {
			helper.setQuantidadeContas(quantidadeContas);
		}
		
		return helper;
	}

	private GerarArquivoTextoContasCobrancaEmpresaHelper buildDadosEnderecoArquivoContasLayoutPadrao(GerarArquivoTextoContasCobrancaEmpresaHelper helper) throws ControladorException {
		Collection<Object[]> enderecos = getControladorEndereco().pesquisarDadosClienteEnderecoArquivoTextoContasCobrancaEmpresa(helper.getIdCliente());

		return buildDadosEnderecoArquivoContas(enderecos, helper);
	}
	
	@SuppressWarnings("unchecked")
	private GerarArquivoTextoContasCobrancaEmpresaHelper buildDadosEnderecoArquivoContas(Collection<Object[]> enderecos, 
			GerarArquivoTextoContasCobrancaEmpresaHelper helper) throws ControladorException {
		
		for (Iterator<Object[]> iterator = enderecos.iterator(); iterator.hasNext();) {
			Object[] endereco = (Object[]) iterator.next();

			helper.setNomeLogradouro((String) endereco[0]);
			helper.setComplementoEndereco((String) endereco[1]);
			helper.setCodigoCep((Integer) endereco[2]);
			helper.setNomeBairro((String) endereco[3]);
			helper.setNumeroImovel((String) endereco[4]);

			if (endereco[5] != null) {
				FiltroLogradouroTipo filtro = new FiltroLogradouroTipo();
				filtro.adicionarParametro(new ParametroSimples(FiltroLogradouroTipo.ID, (Integer) endereco[5]));
				Collection<LogradouroTipo> logradouroTipos = getControladorUtil().pesquisar(filtro, LogradouroTipo.class.getName());
				
				LogradouroTipo logradouroTipo = (LogradouroTipo) Util.retonarObjetoDeColecao(logradouroTipos);
				helper.setTipoLogradouro(logradouroTipo.getDescricao());
			}
		}
		
		return helper;
	}

	@SuppressWarnings("unchecked")
	private GerarArquivoTextoContasCobrancaEmpresaHelper buildDadosImovelArquivoContas(GerarArquivoTextoContasCobrancaEmpresaHelper helper) throws ControladorException, ErroRepositorioException {
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, helper.getIdImovel()));
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_SITUACAO);
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_ESGOTO_SITUACAO);
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.FONTE_ABASTECIMENTO);
		
		Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(getControladorUtil().pesquisar(filtroImovel, Imovel.class.getName()));

		helper.setLigacaoAguaSituacao(imovel.getLigacaoAguaSituacao().getDescricao());
		helper.setLigacaoEsgotoSituacao(imovel.getLigacaoEsgotoSituacao().getDescricao());
		helper.setFonteAbastecimento(imovel.getFonteAbastecimento() != null ? imovel.getFonteAbastecimento().getDescricao() : null);
		helper.setImovelHidrometrado(repositorioMicromedicao.verificaExistenciaHidrometro(helper.getIdImovel()));
		helper.setQuantidadeParcelamentos(imovel.getNumeroParcelamento());
		helper.setQuantidadeReparcelamentos(imovel.getNumeroReparcelamento());
		helper.setQuantidadeReparcelamentosConsecutivos(imovel.getNumeroReparcelamentoConsecutivos());
		
		return helper;
	}

	@SuppressWarnings("unchecked")
	private GerarArquivoTextoContasCobrancaEmpresaHelper buildDadosSubcategoriasArquivoContas(GerarArquivoTextoContasCobrancaEmpresaHelper helper) throws ControladorException {
		FiltroImovelSubCategoria filtro = new FiltroImovelSubCategoria(FiltroImovelSubCategoria.SUBCATEGORIA);
		filtro.adicionarParametro(new ParametroSimples(FiltroImovelSubCategoria.IMOVEL_ID, helper.getIdImovel()));
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelSubCategoria.SUBCATEGORIA);
		
		Collection<ImovelSubcategoria> colecao = getControladorUtil().pesquisar(filtro, ImovelSubcategoria.class.getName());
		
		for (Iterator<ImovelSubcategoria> iterator = colecao.iterator(); iterator.hasNext();) {
			ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) iterator.next();
			
			switch (imovelSubcategoria.getSubcategoria().getId().intValue()) {
			case Subcategoria.RESIDENCIAL_R1:
				helper.setQtdEconomiasR1(imovelSubcategoria.getQuantidadeEconomias());
				break;
			case Subcategoria.RESIDENCIAL_R2:
				helper.setQtdEconomiasR2(imovelSubcategoria.getQuantidadeEconomias());
				break;
			case Subcategoria.RESIDENCIAL_R3:
				helper.setQtdEconomiasR3(imovelSubcategoria.getQuantidadeEconomias());
				break;
			case Subcategoria.RESIDENCIAL_R4:
				helper.setQtdEconomiasR4(imovelSubcategoria.getQuantidadeEconomias());
				break;
			case Subcategoria.COMERCIAL_C1:
				helper.setQtdEconomiasC1(imovelSubcategoria.getQuantidadeEconomias());
				break;
			case Subcategoria.COMERCIAL_C2:
				helper.setQtdEconomiasC2(imovelSubcategoria.getQuantidadeEconomias());
				break;
			case Subcategoria.COMERCIAL_C3:
				helper.setQtdEconomiasC3(imovelSubcategoria.getQuantidadeEconomias());
				break;
			case Subcategoria.COMERCIAL_C4:
				helper.setQtdEconomiasC4(imovelSubcategoria.getQuantidadeEconomias());
				break;
			case Subcategoria.INDUSTRIAL_I1:
				helper.setQtdEconomiasI1(imovelSubcategoria.getQuantidadeEconomias());
				break;
			case Subcategoria.INDUSTRIAL_I2:
				helper.setQtdEconomiasI2(imovelSubcategoria.getQuantidadeEconomias());
				break;
			case Subcategoria.INDUSTRIAL_I3:
				helper.setQtdEconomiasI3(imovelSubcategoria.getQuantidadeEconomias());
				break;
			case Subcategoria.INDUSTRIAL_I4:
				helper.setQtdEconomiasI4(imovelSubcategoria.getQuantidadeEconomias());
				break;
			case Subcategoria.PUBLICA_P1:
				helper.setQtdEconomiasP1(imovelSubcategoria.getQuantidadeEconomias());
				break;
			case Subcategoria.PUBLICA_P2:
				helper.setQtdEconomiasP2(imovelSubcategoria.getQuantidadeEconomias());
				break;
			case Subcategoria.PUBLICA_P3:
				helper.setQtdEconomiasP3(imovelSubcategoria.getQuantidadeEconomias());
				break;
			case Subcategoria.PUBLICA_P4:
				helper.setQtdEconomiasP4(imovelSubcategoria.getQuantidadeEconomias());
				break;
			default:
				break;
			}
		}
		
		return helper;
	}
	
	private StringBuilder gerarArquivoLayoutTipo02(Collection<Integer> comandos, Object[] dados, StringBuilder registros) {
		GerarArquivoTextoContasCobrancaEmpresaHelper helper = new GerarArquivoTextoContasCobrancaEmpresaHelper();
		Map<Integer, GerarArquivoTextoContasCobrancaEmpresaHelper> map = new HashMap<Integer, GerarArquivoTextoContasCobrancaEmpresaHelper>();
		
		buildDadosArquivoLayout02(dados, map, helper, comandos);
		
		if (map != null && !map.isEmpty() && !map.values().isEmpty()) {
			Collection<GerarArquivoTextoContasCobrancaEmpresaHelper> colecao = map.values();
			Iterator<GerarArquivoTextoContasCobrancaEmpresaHelper> iterator = colecao.iterator();

			while (iterator.hasNext()) {
				GerarArquivoTextoContasCobrancaEmpresaHelper novoHelper = (GerarArquivoTextoContasCobrancaEmpresaHelper) iterator.next();
				registros = novoHelper.buildArquivoContasLayoutTipo02(registros);
			}
		}
		
		return registros;
	}
	
	private void buildDadosArquivoLayout02(Object[] dados, Map<Integer, GerarArquivoTextoContasCobrancaEmpresaHelper> mapHelper, 
			GerarArquivoTextoContasCobrancaEmpresaHelper helper, Collection<Integer> comandos) {

		List<Conta> colecaoConta = new ArrayList<Conta>();

		if ((Integer) (dados[8]) != null && mapHelper.containsKey((Integer) (dados[8]))) {
			GerarArquivoTextoContasCobrancaEmpresaHelper helperImovel = mapHelper.get((Integer) (dados[8]));
			colecaoConta = helperImovel.getColecaoConta();

			helperImovel.setNomeClienteConta((String) (dados[9]));
			helperImovel.setIdClienteTipo((Integer) (dados[10]));
			helperImovel.setCpf((String) (dados[11]));
			helperImovel.setCnpj((String) (dados[12]));
			helperImovel.setRg((String) (dados[13]));

			Conta conta = new Conta();
			conta.setId((Integer) (dados[15]));
			conta.setReferencia(((Integer) (dados[18])).intValue());
			conta.setDataVencimentoConta((Date) (dados[19]));
			conta.setValorAgua((BigDecimal) (dados[20]));
			conta.setValorEsgoto((BigDecimal) (dados[21]));
			conta.setDebitos((BigDecimal) (dados[22]));
			conta.setValorCreditos((BigDecimal) (dados[23]));

			colecaoConta.add(conta);
			helperImovel.setColecaoConta(colecaoConta);

			mapHelper.remove((Integer) (dados[8]));
			mapHelper.put((Integer) (dados[8]), helperImovel);
		} else {
			helper.setIdCobrancaConta((Integer) (dados[0]));
//			helper.setIdUnidadeNegocio((Integer) (dados[1]));
//			helper.setNomeUnidadeNegocio((String) (dados[2]));
			helper.setIdFaturamentoGrupo((Integer) (dados[3]));
			helper.setIdLocalidade((Integer) (dados[4]));
			helper.setNomeLocalidade((String) (dados[5]));
			helper.setCodigoRota((Short) (dados[6]));
			helper.setNumeroSequencialRota((Integer) (dados[7]));
			helper.setIdImovel((Integer) (dados[8]));
			helper.setNomeClienteConta((String) (dados[9]));
			helper.setIdClienteTipo((Integer) (dados[10]));
			helper.setCpf((String) (dados[11]));
			helper.setCnpj((String) (dados[12]));
			helper.setRg((String) (dados[13]));
			helper.setTelefone((String) (dados[16]));
			helper.setNomeAbreviadoCliente((String) (dados[17]));
			helper.setCodigoSetorComercial(((Integer) (dados[24])));
			helper.setNumeroLote((Short) (dados[25]));
			helper.setNumeroSublote((Short) (dados[26]));
			helper.setNumeroQuadra((((Integer) (dados[14])).intValue()));
			helper.setIdCliente((Integer) (dados[27]));
			helper.setIdGerenciaRegional((Integer) (dados[28]));
			helper.setNomeGerenciaRegional((String) (dados[29]));
			helper.setCodigoLayoutTxt((Short) (dados[30]));
			helper.setIdOrdemServico((Integer) (dados[31]));

			Conta conta = new Conta();
			conta.setId((Integer) (dados[15]));
			conta.setReferencia(((Integer) (dados[18])).intValue());
			conta.setDataVencimentoConta((Date) (dados[19]));
			conta.setValorAgua((BigDecimal) (dados[20]));
			conta.setValorEsgoto((BigDecimal) (dados[21]));
			conta.setDebitos((BigDecimal) (dados[22]));
			conta.setValorCreditos((BigDecimal) (dados[23]));

			colecaoConta.add(conta);
			helper.setColecaoConta(colecaoConta);

			try {
				buildDadosEnderecoArquivoLayoutTipo02(helper);
				buildDadosQuantidadeArquivoContas(helper, comandos);
				
				mapHelper.put(helper.getIdImovel(), helper);
			} catch (ControladorException e) {
				e.printStackTrace();
			} catch (ErroRepositorioException e) {
				e.printStackTrace();
			}
		}
	}

	private void buildDadosEnderecoArquivoLayoutTipo02(GerarArquivoTextoContasCobrancaEmpresaHelper helper) throws ControladorException {
		Collection<Object[]> enderecos = getControladorEndereco().pesquisarDadosClienteEnderecoArquivoTextoContasCobrancaEmpresaLayout02(helper.getIdCliente());
		buildDadosEnderecoArquivoContas(enderecos, helper);
	}
	
	public void gerarNegociacoesCobrancaEmpresa(int idFuncionalidadeIniciada, Integer idEmpresa) throws ControladorException {
		int idUnidadeIniciada = 0;

		try {
			List<Integer> negociacoes = new ArrayList<Integer>();

			idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, UnidadeProcessamento.EMPRESA, idEmpresa);

			gerarNegociacoesParcelamentos(idEmpresa, negociacoes);
			gerarNegociacoesExtrato(idEmpresa, negociacoes);
			gerarNegociacoesGuias(idEmpresa, negociacoes);

			gerarArquivoTextoNegociacoesCobrancaEmpresa(idEmpresa, negociacoes);

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);

		} catch (Exception e) {
			e.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
		}
	}

	private void gerarNegociacoesParcelamentos(Integer idEmpresa, List<Integer> negociacoes) throws ErroRepositorioException, ControladorException {
		List<Parcelamento> parcelamentos = repositorioCobranca.obterParcelamentosCobrancaEmpresa(idEmpresa);

		for (Parcelamento parcelamento : parcelamentos) {
			NegociacaoCobrancaEmpresa negociacao = new NegociacaoCobrancaEmpresa(parcelamento, new Empresa(idEmpresa), new Date());

			Date dataVencimento = getControladorCobranca().obterDataVencimentoEntradaParcelamento(parcelamento.getId());
			negociacao.setDataVencimento(dataVencimento);
			
			Integer idNegociacao = (Integer) getControladorUtil().inserir(negociacao);
			negociacao.setId(idNegociacao);
			negociacoes.add(idNegociacao);

			List<ContaGeral> contas = repositorioCobranca.obterContasParcelamentosCobrancaEmpresa(parcelamento.getId(), idEmpresa);
			gerarNegociacoesContas(contas, negociacao);
		}
	}

	private void gerarNegociacoesExtrato(Integer idEmpresa, List<Integer> negociacoes) throws ErroRepositorioException, ControladorException {
		List<CobrancaDocumento> documentos = repositorioCobranca.obterExtratosCobrancaEmpresa(idEmpresa);

		for (CobrancaDocumento documento : documentos) {
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

		for (GuiaPagamentoGeral guia : guias) {
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
					arquivoTxt.append(helper.getArquivoTextoNegociacoes());
				}

				enviarEmailArquivoNegociacoes(idEmpresa, arquivoTxt);
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
	
	
	public void atualizarPagamentosContasCobranca(int idFuncionalidadeIniciada, Integer idLocalidade, Integer anoMesArrecadacao) throws ControladorException {
		int idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, UnidadeProcessamento.LOCALIDADE, idLocalidade);
		
		try {
			Collection<EmpresaCobrancaContaPagamentos> pagamentos = obterPagamentosEmpresa(idLocalidade, anoMesArrecadacao);

			for (EmpresaCobrancaContaPagamentos pagamento : pagamentos) {
				getControladorUtil().inserir(pagamento);
				atualizarSituacaoCobranca(pagamento.getIdImovel(), pagamento.getEmpresaCobrancaConta().getComandoEmpresaCobrancaConta().getId());
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
		}
	}

	private void atualizarSituacaoCobranca(Integer idImovel, Integer idComando) throws ErroRepositorioException {
		if (repositorio.isContasPagas(idImovel, idComando)) {
			try {
				atualizarImovelCobrancaSituacao(idImovel);
				atualizarCobrancaSituacaoHistorico(idImovel);
			} catch (ControladorException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void atualizarImovelCobrancaSituacao(Integer idImovel) throws ControladorException {
		Filtro filtro = new FiltroImovelCobrancaSituacao();
		filtro.adicionarParametro(new ParametroSimples(FiltroImovelCobrancaSituacao.IMOVEL_ID, idImovel));
		filtro.adicionarParametro(new ParametroSimples(FiltroImovelCobrancaSituacao.ID_COBRANCA_SITUACAO, CobrancaSituacao.COBRANCA_EMPRESA_TERCEIRIZADA));
		filtro.adicionarParametro(new ParametroNulo(FiltroImovelCobrancaSituacao.DATA_RETIRADA_COBRANCA));
		
		Collection<ImovelCobrancaSituacao> colecao = getControladorUtil().pesquisar(filtro, ImovelCobrancaSituacao.class.getName());
		if (colecao != null && !colecao.isEmpty()) {
			ImovelCobrancaSituacao situacao = (ImovelCobrancaSituacao) Util.retonarObjetoDeColecao(colecao);
			situacao.setDataRetiradaCobranca(new Date());
			situacao.setUltimaAlteracao(new Date());
			getControladorUtil().atualizar(situacao);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void atualizarCobrancaSituacaoHistorico(Integer idImovel) throws ControladorException {
		Filtro filtro = new FiltroCobrancaSituacaoHistorico();
		filtro.adicionarParametro(new ParametroSimples(FiltroCobrancaSituacaoHistorico.IMOVEL_ID, idImovel));
		filtro.adicionarParametro(new ParametroSimples(FiltroCobrancaSituacaoHistorico.COBRANCA_TIPO_ID, CobrancaSituacaoTipo.COBRANCA_EMPRESA_TERCEIRIZADA));
		filtro.adicionarParametro(new ParametroNulo(FiltroCobrancaSituacaoHistorico.ANO_MES_COBRANCA_RETIRADA));
		
		Collection<CobrancaSituacaoHistorico> colecao = getControladorUtil().pesquisar(filtro, CobrancaSituacaoHistorico.class.getName());
		if (colecao != null && !colecao.isEmpty()) {
			SistemaParametro parametros = getControladorUtil().pesquisarParametrosDoSistema();
			
			CobrancaSituacaoHistorico situacao = (CobrancaSituacaoHistorico) Util.retonarObjetoDeColecao(colecao);
			situacao.setAnoMesCobrancaRetirada(parametros.getAnoMesFaturamento());
			situacao.setObservacaoRetira("TODAS AS CONTAS FORAM PAGAS");
			situacao.setUltimaAlteracao(new Date());
			getControladorUtil().atualizar(situacao);
		}
	}
	
	private Collection<EmpresaCobrancaContaPagamentos> obterPagamentosEmpresa(Integer idLocalidade, Integer anoMesArrecadacao) throws ErroRepositorioException {
		Collection<EmpresaCobrancaContaPagamentos> pagamentosEmpresa = new ArrayList<EmpresaCobrancaContaPagamentos>();

		boolean flagTerminou = false;
		final int quantidadeRegistros = 500;
		int numeroPaginas = 0;
		try {
			while (!flagTerminou) {
				Collection<Pagamento> pagamentos = getControladorArrecadacao().obterPagamentosClassificadosNaoRegistradosCobrancaPorEmpresa(idLocalidade, anoMesArrecadacao, numeroPaginas, quantidadeRegistros);

				if (pagamentos != null && !pagamentos.isEmpty()) {
					for (Pagamento pagamento : pagamentos) {
						if (categoriaPermiteGerarPagamento(pagamento)) {
							pagamentosEmpresa.addAll(gerarPagamentoCobrancaDeContas(pagamento));
							pagamentosEmpresa.addAll(gerarPagamentosCobrancaDeGuias(pagamento));
							pagamentosEmpresa.addAll(gerarPagamentosCobrandaDeDebitos(pagamento));
						}
					}
				}

				if (pagamentos == null || pagamentos.size() < quantidadeRegistros) {

					flagTerminou = true;
				}

				if (pagamentos != null) {
					pagamentos.clear();
					pagamentos = null;
				}

				numeroPaginas += quantidadeRegistros;
			}
		} catch (ControladorException e) {
			e.printStackTrace();
		}
		return pagamentosEmpresa;
	}
	
	private boolean categoriaPermiteGerarPagamento(Pagamento pagamento) throws ControladorException {
		boolean permite = false;
		
		if (pagamento.getImovel() != null) {
			Categoria categoria = getControladorImovel().obterPrincipalCategoriaImovel(pagamento.getImovel().getId());
			
			if (!categoria.getId().equals(Categoria.PUBLICO)) {
				permite =  true;
			}
			
		} else {
			permite =  true;
		}
		return permite;
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
					Parcelamento parcelamento =  null;
							
					if (debitoCobrado.getDebitoACobrarGeral() != null
							&& debitoCobrado.getDebitoACobrarGeral().getDebitoACobrar() != null
							&& debitoCobrado.getDebitoACobrarGeral().getDebitoACobrar().getParcelamento() != null ) {
						parcelamento = debitoCobrado.getDebitoACobrarGeral().getDebitoACobrar().getParcelamento();
					}
					
					pagamentosEmpresa.addAll(verificarItensParcelamentos(parcelamento, null, null, pagamento, debitoCobrado, pagamento.getAnoMesReferenciaArrecadacao()));
				}
			}
		}
		return pagamentosEmpresa;
	}

	private Boolean isContaEmCobranca(Pagamento pagamento) throws ErroRepositorioException {
		EmpresaCobrancaConta empresaCobrancaConta = repositorio.pesquisarEmpresaCobrancaConta(pagamento.getContaGeral().getId());
		
		if (empresaCobrancaConta != null) return true;
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

	private List<DebitoCobrado> obterDebitosDePagamentoDeParcelamento(Pagamento pagamento) throws ControladorException {
		
		Collection<Integer> financiamentos = obterFinanciamentoTipoParcelamento();

		Collection<Object[]> colecaoDadosDebitoCobrado = getControladorFaturamento().pesquisaridDebitoTipoDoDebitoCobradoDeParcelamento(
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

			EmpresaCobrancaConta empresaCobrancaConta = repositorio.pesquisarEmpresaCobrancaConta(idConta);

			if (empresaCobrancaConta != null) {
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
				pagamentoEmpresa.setDebitoTipo(debitoTipo);
				pagamentoEmpresa.setEmpresaCobrancaConta(empresaCobrancaConta);
				pagamentoEmpresa.setAnoMesPagamentoArrecadacao(pagamento.getAnoMesReferenciaArrecadacao());
				pagamentoEmpresa.setValorPagamentoMes(valorPagamentoMes);
				pagamentoEmpresa.setIndicadorTipoPagamento(indicadorTipoPagamento);
				pagamentoEmpresa.setNumeroParcelaAtual(debitoCobrado != null ? new Integer(debitoCobrado.getNumeroPrestacaoDebito()) : new Integer("0"));
				pagamentoEmpresa.setNumeroTotalParcelas(debitoCobrado != null ? new Integer(debitoCobrado.getNumeroPrestacao()) : new Integer("0"));
				pagamentoEmpresa.setUltimaAlteracao(new Date());
				pagamentoEmpresa.setIndicadorGeracaoArquivo(ConstantesSistema.NAO);
				pagamentoEmpresa.setIdPagamento(pagamento.getId());
				
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
				
				if (pagamento.getValorDesconto() != null) {
					pagamentoEmpresa.setValorDesconto(pagamento.getValorDesconto());
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
		
		int idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, UnidadeProcessamento.EMPRESA, idEmpresa);

		try {

			StringBuilder arquivoCompleto = new StringBuilder();
			
			Collection colecaoDadosTxt = repositorioCobranca.pesquisarDadosArquivoTextoPagamentosContasCobrancaEmpresa(idEmpresa);
			
			if (colecaoDadosTxt != null && !colecaoDadosTxt.isEmpty()) {

				Iterator colecaoDadosTxtIterator = colecaoDadosTxt.iterator();

				List<Integer> idsCobrancaPagamentos = new ArrayList<Integer>();
				
				while (colecaoDadosTxtIterator.hasNext()) {

					Object[] dados = (Object[]) colecaoDadosTxtIterator.next();

					ArquivoTextoPagamentoContasCobrancaEmpresaHelper helper = new ArquivoTextoParagentosCobancaEmpresaBuilder(dados).buildHelper(); 
					
					arquivoCompleto.append(helper.getArquivoTexto());
					
					idsCobrancaPagamentos.add(helper.getIdEmpresaCobrancaContaPagamento());
				}
				enviarEmailArquivoPagamentos(idEmpresa, arquivoCompleto);
				
				atualizarPagamentosGerados(idsCobrancaPagamentos);
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
		} catch (Exception ex) {
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			ex.printStackTrace();

			throw new EJBException(ex);
		}
	}
	
	private void atualizarPagamentosGerados(List<Integer> idsPagamentos) {
		try {
			repositorioCobranca.atualizarPagamentosCobrancaPorEmpresaGerados(idsPagamentos);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
	}
	
	private void enviarEmailArquivoPagamentos(Integer idEmpresa, StringBuilder arquivo) throws ControladorException {
		String dataHora = Util.formatarDataAAAAMMDD(new Date()) + "-" + Util.formatarDataHHMM(new Date());

		String nomeArquivo = "pagamentos_contas_cobranca_empresa_" + idEmpresa + "_" + dataHora;
		
		EnvioEmail envioEmail = getControladorCadastro().pesquisarEnvioEmail(EnvioEmail.COBRANCA_EMPRESA);
		
		String titulo = "Pagamentos contas da Empresa de Cobrança - " + idEmpresa ;
		String corpo = "Pagamentos contas da Empresa de Cobrança : " + idEmpresa ;
		
		ServicosEmail.enviarArquivoCompactado(nomeArquivo, arquivo, envioEmail.getEmailReceptor(), envioEmail.getEmailRemetente(), titulo, corpo);
	}
	
	private void enviarEmailArquivoNegociacoes(Integer idEmpresa, StringBuilder arquivoTxt) throws ControladorException {
		String dataHora = Util.formatarDataAAAAMMDD(new Date()) + "-" + Util.formatarDataHHMM(new Date());

		String nomeArquivo = "movimento_contas_cobranca_empresa_" + idEmpresa + "_" + dataHora;

		EnvioEmail envioEmail = getControladorCadastro().pesquisarEnvioEmail(EnvioEmail.COBRANCA_EMPRESA);

		String titulo = "Negociações da Empresa de Cobrança - " + idEmpresa;
		String corpo = "Negociações da Empresa de Cobrança : " + idEmpresa;

		ServicosEmail.enviarArquivoCompactado(nomeArquivo, arquivoTxt, envioEmail.getEmailReceptor(), envioEmail.getEmailRemetente(), titulo, corpo);
	}

	@SuppressWarnings("rawtypes")
	public Integer pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresaCount(Integer idEmpresa, String dataPagamentoInicial, String dataPagamentoFinal) throws ControladorException {
		Integer retorno = 0;

		try {
			Collection colecao = repositorio.pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresaCount(idEmpresa, dataPagamentoInicial, dataPagamentoFinal);

			if (colecao != null && !colecao.isEmpty()) {
				Iterator iterator = colecao.iterator();
				while (iterator.hasNext()) {
					retorno += (Integer) iterator.next();
				}
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		return retorno;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Collection pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresa(RelatorioPagamentosContasCobrancaEmpresaHelper helper) throws ControladorException {
		Collection<RelatorioPagamentosContasCobrancaEmpresaBean> retorno = new ArrayList<RelatorioPagamentosContasCobrancaEmpresaBean>();
		Collection<Object[]> colecaoDados = null;

		try {
			colecaoDados = repositorio.pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresaOpcaoTotalizacao(helper);

			if (colecaoDados != null && !colecaoDados.isEmpty()) {
				for (Object[] dados : colecaoDados) {
					RelatorioPagamentosContasCobrancaEmpresaBean bean = new RelatorioPagamentosContasCobrancaEmpresaBean();

					// idImovel
					if (dados[0] != null) {
						Integer idImovel = (Integer) dados[0];
						bean.setMatricula(Util.retornaMatriculaImovelFormatada(idImovel));
					}
					// nomeCliente
					if (dados[1] != null) {
						String nomeCliente = (String) dados[1];
						bean.setNomeCliente(nomeCliente);
					}
					// anoMesConta
					if (dados[2] != null) {
						Integer anoMesConta = (Integer) dados[2];
						bean.setAnoMesConta(Util.formatarAnoMesParaMesAno(anoMesConta.intValue()));
					}

					// valorConta
					if (dados[3] != null) {
						BigDecimal valorConta = (BigDecimal) dados[3];
						bean.setValorConta(Util.formatarMoedaReal(valorConta));
					}

					// anoMesReferenciaPagamento
					if (dados[4] != null) {
						Integer anoMesReferenciaPagamento = (Integer) dados[4];
						bean.setAnoMesReferenciaPagamento(Util.formatarAnoMesParaMesAno(anoMesReferenciaPagamento));
					}

					// valorPrincipal
					BigDecimal valorPrincipal = new BigDecimal(0.0);
					if (dados[5] != null) {
						valorPrincipal = (BigDecimal) dados[5];
					}
					
					bean.setValorPrincipal(Util.formatarMoedaReal(valorPrincipal));

					BigDecimal valorEncargos = new BigDecimal(0.0);
					// valorEncargos
					if (dados[6] != null) {
						valorEncargos = (BigDecimal) dados[6];
					}
					
					bean.setValorEncargos(Util.formatarMoedaReal(valorEncargos));

					BigDecimal percentualEmpresa = new BigDecimal(0.0);
					// percentualEmpresa
					if (dados[7] != null) {
						percentualEmpresa = (BigDecimal) dados[7];

					}
					bean.setPercentualEmpresa(percentualEmpresa.toString().replace(".", ","));

					// Id da Localidade
					if (dados[8] != null) {
						Integer idLocalidade = (Integer) dados[8];
						bean.setIdLocalidade(idLocalidade.toString());
					}

					// nome da Localidade
					if (dados[9] != null) {
						bean.setNomeLocalidade((String) dados[9]);
					}

					// Id Gerencia Regional
					if (dados[10] != null) {
						Integer idGerenciaRegional = (Integer) dados[10];
						bean.setIdGerenciaRegional(idGerenciaRegional.toString());
					}

					// Nome Gerencia Regional
					if (dados[11] != null) {
						bean.setNomeGerenciaRegional((String) dados[11]);
					}

					// Id Unidade Negocio
					if (dados[12] != null) {
						Integer idUnidadeNegocio = (Integer) dados[12];
						bean.setIdUnidadeNegocio(idUnidadeNegocio.toString());
					}

					// Nome Unidade Negocio
					if (dados[13] != null) {
						bean.setNomeUnidadeNegocio((String) dados[13]);
					}

					// Id Rota
					if (dados[14] != null) {
						Integer idRota = (Integer) dados[14];
						bean.setIdRota(idRota.toString());
					}
					// Indicador do Tipo de Pagamento
					if (dados[15] != null) {
						Short indicadorTipoPagamento = (Short) dados[15];
						bean.setIndicadorTipoPagamento(indicadorTipoPagamento.toString());
						if (indicadorTipoPagamento.intValue() == ConstantesSistema.INDICADOR_PAGAMENTO_A_VISTA.intValue()) {
							bean.setTipoPagamento("À Vista");
						} else {
							bean.setTipoPagamento("Parcelado");
						}
					}

					// Numero Parcela Atual
					if (dados[16] != null) {
						Integer numeroParcelaAtual = (Integer) dados[16];
						bean.setNumeroParcelaAtual(numeroParcelaAtual.toString());
					}
					// Numero Total Parcelas
					if (dados[17] != null) {
						Integer numeroTotalParcelas = (Integer) dados[17];
						bean.setNumeroTotalParcelas(numeroTotalParcelas.toString());
					}

					BigDecimal valorTotal = new BigDecimal(0.0);

					valorTotal = valorTotal.add(valorPrincipal);
					valorTotal = valorTotal.add(valorEncargos);

					// Valor total
					bean.setValorTotalPagamentos(Util.formatarMoedaReal4Casas(valorTotal));

					// Valor Empresa
					BigDecimal valorEmpresa = new BigDecimal(0.0);

					BigDecimal aux = new BigDecimal(100.0);

					valorEmpresa = valorEmpresa.add(valorTotal.multiply(percentualEmpresa));
					valorEmpresa = valorEmpresa.setScale(4, BigDecimal.ROUND_HALF_UP);
					valorEmpresa = valorEmpresa.divide(aux);
					valorEmpresa = valorEmpresa.setScale(4, BigDecimal.ROUND_HALF_UP);

					bean.setValorEmpresa(Util.formatarMoedaReal4Casas(valorEmpresa));
					bean.setCodigoQuebra2("");
					bean.setDescricaoQuebra2("");

					if (helper.getOpcaoTotalizacao().equalsIgnoreCase("estadoGerencia")) {
						bean.setCodigoQuebra(bean.getIdGerenciaRegional());
						bean.setDescricaoQuebra(bean.getNomeGerenciaRegional());
					} else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("estadoLocalidade")) {
						bean.setCodigoQuebra(bean.getIdLocalidade());
						bean.setDescricaoQuebra(bean.getNomeLocalidade());
					} else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("gerenciaRegional")) {
						bean.setCodigoQuebra(bean.getIdGerenciaRegional());
						bean.setDescricaoQuebra(bean.getNomeGerenciaRegional());
					} else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("gerenciaRegionalLocalidade")) {
						bean.setCodigoQuebra(bean.getIdGerenciaRegional());
						bean.setDescricaoQuebra(bean.getNomeGerenciaRegional());
						bean.setCodigoQuebra2(bean.getIdLocalidade());
						bean.setDescricaoQuebra2(bean.getNomeLocalidade());
					} else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("localidade")) {
						bean.setCodigoQuebra(bean.getIdLocalidade());
						bean.setDescricaoQuebra(bean.getNomeLocalidade());
					} else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("estadoUnidadeNegocio")) {
						bean.setCodigoQuebra(bean.getIdUnidadeNegocio());
						bean.setDescricaoQuebra(bean.getNomeUnidadeNegocio());
					} else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("unidadeNegocio")) {
						bean.setCodigoQuebra(bean.getIdUnidadeNegocio());
						bean.setDescricaoQuebra(bean.getNomeUnidadeNegocio());
					} else {
						bean.setCodigoQuebra("");
						SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
						bean.setDescricaoQuebra(sistemaParametro.getNomeEstado());
					}
					retorno.add(bean);
				}
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		return retorno;
	}
}
