package gcom.cadastro;

import gcom.arrecadacao.FiltroArrecadador;
import gcom.arrecadacao.pagamento.FiltroPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.atendimentopublico.bean.DadosLigacoesBoletimCadastroHelper;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.RamalLocalInstalacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.RABuilder;
import gcom.atendimentopublico.registroatendimento.RADadosGeraisHelper;
import gcom.atendimentopublico.registroatendimento.RALocalOcorrenciaHelper;
import gcom.atendimentopublico.registroatendimento.RASolicitanteHelper;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper;
import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.arquivo.GeradorRegistroAcessoHidrometro;
import gcom.cadastro.arquivo.GeradorRegistroClasseSocial;
import gcom.cadastro.arquivo.GeradorRegistroHidromedro;
import gcom.cadastro.arquivo.GeradorRegistroLocalizacao;
import gcom.cadastro.arquivo.GeradorRegistroServicos;
import gcom.cadastro.arquivo.GeradorRegistroTipoLogradouro;
import gcom.cadastro.arquivo.GeradorRegistroTipoUsoImovel;
import gcom.cadastro.atualizacaocadastral.FiltroArquivoTextoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.FiltroImovelAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.command.AbstractAtualizacaoCadastralCommand;
import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.cadastro.atualizacaocadastral.command.EfetuarValidacoesAtualizacaoCadastralCommand;
import gcom.cadastro.atualizacaocadastral.command.MontarObjetosAtualizacaoCadastralCommand;
import gcom.cadastro.atualizacaocadastral.command.MontarObjetosRetornoCommand;
import gcom.cadastro.atualizacaocadastral.command.ParseAnormalidadeCommand;
import gcom.cadastro.atualizacaocadastral.command.ParseClienteCommand;
import gcom.cadastro.atualizacaocadastral.command.ParseHeaderCommand;
import gcom.cadastro.atualizacaocadastral.command.ParseImovelCommand;
import gcom.cadastro.atualizacaocadastral.command.ParseMedidorCommand;
import gcom.cadastro.atualizacaocadastral.command.ParseRamoAtividadeCommand;
import gcom.cadastro.atualizacaocadastral.command.ParseServicosCommand;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificado;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoBinario;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoCritica;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoLinha;
import gcom.cadastro.atualizacaocadastralsimplificado.FiltroAtualizacaoCadastralSimplificadoCritica;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteConta;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteFoneAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteConta;
import gcom.cadastro.cliente.FiltroClienteEndereco;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.cliente.FiltroOrgaoExpedidorRg;
import gcom.cadastro.cliente.FoneTipo;
import gcom.cadastro.cliente.IClienteAtualizacaoCadastral;
import gcom.cadastro.cliente.IRepositorioClienteImovel;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.cadastro.cliente.RepositorioClienteImovelHBM;
import gcom.cadastro.cliente.bean.ClienteEmitirBoletimCadastroHelper;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.cadastro.empresa.EmpresaContratoCobranca;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.empresa.FiltroEmpresaContratoCobranca;
import gcom.cadastro.empresa.IRepositorioEmpresa;
import gcom.cadastro.empresa.RepositorioEmpresaHBM;
import gcom.cadastro.endereco.FiltroLogradouroTipo;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.to.LogradouroTO;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipioFeriado;
import gcom.cadastro.geografico.FiltroUnidadeFederacao;
import gcom.cadastro.geografico.MunicipioFeriado;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.EntidadeBeneficente;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroEntidadeBeneficente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelProgramaEspecial;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.FonteAbastecimento;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelInscricaoAlterada;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelProgramaEspecial;
import gcom.cadastro.imovel.ImovelRamoAtividade;
import gcom.cadastro.imovel.ImovelRamoAtividadeAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSubcategoriaPK;
import gcom.cadastro.imovel.ImovelTipoOcupante;
import gcom.cadastro.imovel.ImovelTipoOcupanteQuantidadeAtualizacaoCadastral;
import gcom.cadastro.imovel.RepositorioImovelHBM;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.imovel.bean.ImovelGeracaoTabelasTemporariasCadastroHelper;
import gcom.cadastro.imovel.enums.AcessoHidrometro;
import gcom.cadastro.imovel.enums.ClasseSocial;
import gcom.cadastro.imovel.enums.TipoUsoImovel;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.IRepositorioSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.QuadraFace;
import gcom.cadastro.localidade.RepositorioSetorComercialHBM;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.FiltroNacionalFeriado;
import gcom.cadastro.sistemaparametro.FiltroSistemaAlteracaoHistorico;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaAlteracaoHistorico;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.sistemaparametro.bean.DadosEnvioEmailHelper;
import gcom.cadastro.sistemaparametro.bean.FeriadoHelper;
import gcom.cadastro.tarifasocial.TarifaSocialCarta;
import gcom.cadastro.tarifasocial.TarifaSocialCartaDebito;
import gcom.cadastro.tarifasocial.TarifaSocialCartaDebitoPK;
import gcom.cadastro.tarifasocial.TarifaSocialCartaPK;
import gcom.cadastro.tarifasocial.TarifaSocialComandoCarta;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialMotivoCarta;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.CobrancaSituacaoHistorico;
import gcom.cobranca.CobrancaSituacaoMotivo;
import gcom.cobranca.CobrancaSituacaoTipo;
import gcom.cobranca.IRepositorioCobranca;
import gcom.cobranca.RepositorioCobrancaHBM;
import gcom.cobranca.bean.CalcularAcrescimoPorImpontualidadeHelper;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.EmitirDocumentoCobrancaBoletimCadastroHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.RepositorioFaturamentoHBM;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaMensagem;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.conta.FiltroContaMensagem;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.relatorio.cadastro.FiltrarRelatorioAcessoSPCHelper;
import gcom.gui.relatorio.cadastro.GerarRelatorioAlteracoesCpfCnpjHelper;
import gcom.gui.relatorio.cadastro.micromedicao.FiltrarRelatorioColetaMedidorEnergiaHelper;
import gcom.gui.relatorio.seguranca.GerarRelatorioAlteracoesSistemaColunaHelper;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.ArquivoTextoLigacoesHidrometroHelper;
import gcom.micromedicao.IRepositorioMicromedicao;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.RepositorioMicromedicaoHBM;
import gcom.micromedicao.Rota;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.FiltroHidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.FiltroHidrometroMarca;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.leitura.FiltroLeiturista;
import gcom.relatorio.cadastro.GerarRelatorioAtualizacaoCadastralViaInternetHelper;
import gcom.relatorio.cadastro.RelatorioAcessoSPCBean;
import gcom.relatorio.cadastro.RelatorioAtualizacaoCadastralHelper;
import gcom.relatorio.cadastro.RelatorioBoletimCadastroIndividualBean;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisAtivosNaoMedidosHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisConsumoMedioHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisFaturasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisProgramasEspeciaisHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisTipoConsumoHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisUltimosConsumosAguaHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisAtivosNaoMedidosHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisConsumoMedioHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisFaturasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisProgramasEspeciaisHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisSituacaoLigacaoAguaHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisTipoConsumoHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisUltimosConsumosAguaHelper;
import gcom.relatorio.cadastro.micromedicao.RelatorioColetaMedidorEnergiaHelper;
import gcom.seguranca.Atributo;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorComum;
import gcom.util.ControladorException;
import gcom.util.Criptografia;
import gcom.util.ErroCriptografiaException;
import gcom.util.ErroRepositorioException;
import gcom.util.FachadaException;
import gcom.util.IRepositorioUtil;
import gcom.util.IoUtil;
import gcom.util.ParserUtil;
import gcom.util.RepositorioUtilHBM;
import gcom.util.Util;
import gcom.util.ZipUtil;
import gcom.util.email.ErroEmailException;
import gcom.util.email.ServicosEmail;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipOutputStream;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.jboss.logging.Logger;

import br.com.danhil.BarCode.Interleaved2of5;

public class ControladorCadastro extends ControladorComum {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(ControladorCadastro.class);

	private IRepositorioEmpresa repositorioEmpresa = null;
	protected IRepositorioCadastro repositorioCadastro = null;
	private IRepositorioSetorComercial repositorioSetorComercial = null;
	private IRepositorioCobranca repositorioCobranca = null;
	private IRepositorioClienteImovel repositorioClienteImovel = null;
	private IRepositorioImovel repositorioImovel = null;
	private IRepositorioFaturamento repositorioFaturamento = null;
	private IRepositorioUtil repositorioUtil = null;
	private IRepositorioMicromedicao repositorioMicromedicao = null;

	public void ejbCreate() throws CreateException {
		repositorioEmpresa = RepositorioEmpresaHBM.getInstancia();
		repositorioCadastro = RepositorioCadastroHBM.getInstancia();
		repositorioSetorComercial = RepositorioSetorComercialHBM.getInstancia();
		repositorioCobranca = RepositorioCobrancaHBM.getInstancia();
		repositorioClienteImovel = RepositorioClienteImovelHBM.getInstancia();
		repositorioImovel = RepositorioImovelHBM.getInstancia();
		repositorioFaturamento = RepositorioFaturamentoHBM.getInstancia();
		repositorioUtil = RepositorioUtilHBM.getInstancia();
		repositorioMicromedicao = RepositorioMicromedicaoHBM.getInstancia();
	}

	@SuppressWarnings("rawtypes")
	public Integer inserirHistoricoAlteracaoSistema(
			SistemaAlteracaoHistorico sistemaAlteracaoHistorico)
			throws ControladorException {

		FiltroSistemaAlteracaoHistorico filtroSistemaAlteracaoHistorico = new FiltroSistemaAlteracaoHistorico();
		filtroSistemaAlteracaoHistorico
				.adicionarParametro(new ParametroSimples(
						FiltroSistemaAlteracaoHistorico.NOME,
						sistemaAlteracaoHistorico.getNome()));

		Collection colecaoSistemaAlteracaoHistorico = getControladorUtil()
				.pesquisar(filtroSistemaAlteracaoHistorico,
						SistemaAlteracaoHistorico.class.getName());

		if (colecaoSistemaAlteracaoHistorico != null
				&& !colecaoSistemaAlteracaoHistorico.isEmpty()) {
			throw new ControladorException(
					"atencao.numero_resolucao_ja_existente");
		}

		sistemaAlteracaoHistorico.setUltimaAlteracao(new Date());

		Integer id = (Integer) getControladorUtil().inserir(
				sistemaAlteracaoHistorico);

		return id;

	}

	/**
	 * Permite inserir uma Gerencia Regional
	 *
	 * [UC0217] Inserir Gerencia Regional
	 *
	 * @author Thiago Tenório
	 * @date 30/03/2006
	 *
	 */
	public Integer inserirGerenciaRegional(GerenciaRegional gerenciaRegional)
			throws ControladorException {

		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
				FiltroGerenciaRegional.NOME, gerenciaRegional.getNome()));

		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
				FiltroGerenciaRegional.NOME_ABREVIADO, gerenciaRegional
						.getNomeAbreviado()));

		// Collection colecaoEnderecos = getControladorUtil().pesquisar(
		// filtroGerenciaRegional, GerenciaRegional.class.getName());

		// if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {
		// throw new ControladorException(
		// "atencao.endereco_localidade_nao_informado");
		// }

		Integer id = (Integer) getControladorUtil().inserir(gerenciaRegional);

		return id;

	}

	/**
	 * [UC0298] Manter Gerência Regional [] Atualizar Gerencia Regional Metodo
	 * que atualiza a Gerencia Regional
	 *
	 *
	 * @author Thiago Tenório
	 * @date 25/05/2006
	 *
	 *
	 * @throws ControladorException
	 */

	public void atualizarGerenciaRegional(GerenciaRegional gerenciaRegional)
			throws ControladorException {

		// Verifica se todos os campos obrigatorios foram preenchidos

		if ((gerenciaRegional.getId() == null || gerenciaRegional.getId()
				.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (gerenciaRegional.getNome() == null || gerenciaRegional
						.getNome().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (gerenciaRegional.getNomeAbreviado() == null || gerenciaRegional
						.getNomeAbreviado().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (gerenciaRegional.getFone() == null || gerenciaRegional
						.getFone().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			throw new ControladorException(
					"atencao.filtro.nenhum_parametro_informado");

		}

		// Verifica se o campo Nome foi preenchido

		if (gerenciaRegional.getNome() == null
				|| gerenciaRegional.getNome().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					" Nome");
		}

		// Verifica se o campo Nome Abreviado foi preenchido
		if (gerenciaRegional.getNomeAbreviado() == null
				|| gerenciaRegional.getNomeAbreviado().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					" Nome Abreviado");
		}

		// Verifica se o campo Telefone foi preenchido
		if (gerenciaRegional.getFone() == null
				|| gerenciaRegional.getFone().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					" Telefone");
		}

		// [FS0003] - Atualização realizada por outro usuário
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
				FiltroGerenciaRegional.ID, gerenciaRegional.getId()));

		Collection colecaoGerenciaRegionalBase = getControladorUtil()
				.pesquisar(filtroGerenciaRegional,
						GerenciaRegional.class.getName());

		if (colecaoGerenciaRegionalBase == null
				|| colecaoGerenciaRegionalBase.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		GerenciaRegional gerenciaRegionalBase = (GerenciaRegional) colecaoGerenciaRegionalBase
				.iterator().next();

		if (gerenciaRegionalBase.getUltimaAlteracao().after(
				gerenciaRegional.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		gerenciaRegional.setUltimaAlteracao(new Date());

		getControladorUtil().atualizar(gerenciaRegional);

	}

	/**
	 * Pesquisa as empresas que serão processadas no emitir contas
	 *
	 * @author Sávio Luiz
	 * @date 09/01/2007
	 *
	 */

	public Collection pesquisarIdsEmpresa() throws ControladorException {
		try {
			return repositorioEmpresa.pesquisarIdsEmpresa();
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public void informarParametrosSistema(SistemaParametro sistemaParametro, Usuario usuarioLogado) throws ControladorException {

		if (sistemaParametro.getNomeEstado().equals("")) {
			throw new ControladorException("atencao.informe_campo", null, "Nome do Estado");
		}

		if (sistemaParametro.getNomeEmpresa().equals("")) {
			throw new ControladorException("atencao.informe_campo", null, "Nome da Empresa");
		}

		if (sistemaParametro.getNomeAbreviadoEmpresa().equals("")) {
			throw new ControladorException("atencao.informe_campo", null, "Abreviatura da Empresa");
		}

		if (sistemaParametro.getCnpjEmpresa().equals("")) {
			throw new ControladorException("atencao.informe_campo", null, "CNPJ");
		}

		if (sistemaParametro.getLogradouro().equals("")) {
			throw new ControladorException("atencao.informe_campo", null, "Logradouro");
		}

		if (sistemaParametro.getAnoMesFaturamento() == null || sistemaParametro.getAnoMesFaturamento().equals("")) {
			throw new ControladorException("atencao.informe_campo", null, "Mês e Ano de Referência");
		}

		if (sistemaParametro.getAnoMesArrecadacao() == null || sistemaParametro.getAnoMesArrecadacao().equals("")) {
			throw new ControladorException("atencao.informe_campo", null, "Mês e Ano de Referência");
		}

		if (sistemaParametro.getMenorConsumoGrandeUsuario() == null || sistemaParametro.getMenorConsumoGrandeUsuario().equals("")) {
			throw new ControladorException("atencao.informe_campo", null, "Menor Consumo para ser Grande Usuário");
		}

		if (sistemaParametro.getValorMinimoEmissaoConta() == null || sistemaParametro.getValorMinimoEmissaoConta().equals("")) {
			throw new ControladorException("atencao.informe_campo", null, "Menor Valor para Emissão de Contas");
		}

		if (sistemaParametro.getMenorEconomiasGrandeUsuario() == null || sistemaParametro.getMenorEconomiasGrandeUsuario().equals("")) {
			throw new ControladorException("atencao.informe_campo", null, "Qtde de Economias para ser Grande Usuário");
		}

		if (sistemaParametro.getMesesMediaConsumo() == null || sistemaParametro.getMesesMediaConsumo().equals("")) {
			throw new ControladorException("atencao.informe_campo", null, "Meses para Cálculo de Média de Consumo");
		}

		if (sistemaParametro.getNumeroMinimoDiasEmissaoVencimento() == null || sistemaParametro.getNumeroMinimoDiasEmissaoVencimento().equals("")) {
			throw new ControladorException("atencao.informe_campo", null, "Número de Dias entre o Vencimento e o Início da Cobrança");
		}

		if (sistemaParametro.getIncrementoMaximoConsumoRateio() == null || sistemaParametro.getIncrementoMaximoConsumoRateio().equals("")) {
			throw new ControladorException("atencao.informe_campo", null, "Incremento Máximo de Consumo por economia em Rateio");
		}

		if (sistemaParametro.getDecrementoMaximoConsumoRateio() == null || sistemaParametro.getDecrementoMaximoConsumoRateio().equals("")) {
			throw new ControladorException("atencao.informe_campo", null, "Decremento Máximo de Consumo por economia em Rateio");
		}

		if (sistemaParametro.getDiasMaximoAlterarOS() == null || sistemaParametro.getDiasMaximoAlterarOS().equals("")) {
			throw new ControladorException("atencao.informe_campo", null, "Dias Máximo para Alterar Dados da OS");
		}

		if (sistemaParametro.getUltimoRAManual() == null || sistemaParametro.getUltimoRAManual().equals("")) {
			throw new ControladorException("atencao.informe_campo", null, "Último ID Utilizado para Geração do RA Manual");
		}

		if (sistemaParametro.getTituloPagina() == null || sistemaParametro.getTituloPagina().equals("")) {
			throw new ControladorException("atencao.informe_campo", null, "Títulos de Relatório");
		}

		sistemaParametro.setUltimaAlteracao(new Date());

		getControladorUtil().atualizar(sistemaParametro);
	}

	/**
	 * [UC0534] Inserir Feriado
	 *
	 * @author Kassia Albuquerque
	 * @date 17/01/2007
	 *
	 */
	public Integer inserirFeriado(NacionalFeriado nacionalFeriado,
			MunicipioFeriado municipioFeriado, Usuario usuarioLogado)
			throws ControladorException {

		if (nacionalFeriado != null) {

			// [FS0003] - Verificando a existência do Feriado Nacional pela
			// descrição

			FiltroNacionalFeriado filtroNacionalFeriado = new FiltroNacionalFeriado();

			/*
			 * filtroNacionalFeriado .adicionarParametro(new ParametroSimples(
			 * FiltroNacionalFeriado.NOME, nacionalFeriado .getDescricao()));
			 * filtroNacionalFeriado.adicionarParametro(new
			 * ParametroSimples(FiltroNacionalFeriado.DATA,
			 * nacionalFeriado.getData()));
			 *
			 * Collection colecaoNacionalFeriado =
			 * getControladorUtil().pesquisar( filtroNacionalFeriado,
			 * NacionalFeriado.class.getName());
			 *
			 * if (colecaoNacionalFeriado != null &&
			 * !colecaoNacionalFeriado.isEmpty()) { throw new
			 * ControladorException(
			 * "atencao.nacional_feriado.decricao.existente"); }
			 */

			// Verificando existência de mais de um Feriado Nacional numa mesma
			// data
			filtroNacionalFeriado.limparListaParametros();

			filtroNacionalFeriado.adicionarParametro(new ParametroSimples(
					FiltroNacionalFeriado.DATA, nacionalFeriado.getData()));

			Collection colecaoNacionalFeriado = getControladorUtil().pesquisar(
					filtroNacionalFeriado, NacionalFeriado.class.getName());

			if (colecaoNacionalFeriado != null
					&& !colecaoNacionalFeriado.isEmpty()) {
				throw new ControladorException(
						"atencao.nacional_feriado_com_data_existente");
			} else {
				filtroNacionalFeriado.limparListaParametros();

				filtroNacionalFeriado.adicionarParametro(new ParametroSimples(
						FiltroNacionalFeriado.DATA, nacionalFeriado.getData()));

				filtroNacionalFeriado.adicionarParametro(new ParametroSimples(
						FiltroNacionalFeriado.NOME, nacionalFeriado
								.getDescricao()));

				Collection colecaoNacionalFeriado2 = getControladorUtil()
						.pesquisar(filtroNacionalFeriado,
								NacionalFeriado.class.getName());

				if (!colecaoNacionalFeriado2.isEmpty()) {

					Iterator iterator2 = colecaoNacionalFeriado2.iterator();

					while (iterator2.hasNext()) {
						NacionalFeriado nacionalMunicipal = (NacionalFeriado) iterator2
								.next();

						Date data = nacionalMunicipal.getData();

						String data2String = Util.formatarData(data);

						String data2Tela = Util.formatarData(municipioFeriado
								.getDataFeriado());

						String ano2 = data2String.substring(6, 10);

						String ano2Tela = data2Tela.substring(6, 10);

						if (ano2.equals(ano2Tela)) {
							throw new ControladorException(
									"atencao.nacional_feriado_mesmo_nome_mesmo_ano");
						}
					}
				}
			}

			nacionalFeriado.setUltimaAlteracao(new Date());

			// ------------ REGISTRAR TRANSAÇÃO----------------------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_FERIADO_INSERIR,
					new UsuarioAcaoUsuarioHelper(usuarioLogado,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

			Operacao operacao = new Operacao();
			operacao.setId(Operacao.OPERACAO_FERIADO_INSERIR);

			OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
			operacaoEfetuada.setOperacao(operacao);

			nacionalFeriado.setOperacaoEfetuada(operacaoEfetuada);
			nacionalFeriado.adicionarUsuario(usuarioLogado,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(nacionalFeriado);
			// ------------ REGISTRAR TRANSAÇÃO----------------------------

			Integer idFeriado = (Integer) getControladorUtil().inserir(
					nacionalFeriado);

			return idFeriado;

		} else {

			// Verificando existencia de data Feriado Municipal numa mesma data
			// de Feriado Nacional

			FiltroNacionalFeriado filtroNacionalFeriado = new FiltroNacionalFeriado();

			filtroNacionalFeriado.adicionarParametro(new ParametroSimples(
					FiltroNacionalFeriado.DATA, municipioFeriado
							.getDataFeriado()));

			Collection colecaoNacionalFeriado = getControladorUtil().pesquisar(
					filtroNacionalFeriado, NacionalFeriado.class.getName());

			if (colecaoNacionalFeriado != null
					&& !colecaoNacionalFeriado.isEmpty()) {

				throw new ControladorException(
						"atencao.nacional_feriado_com_data_existente");
			}

			// Verificando se existe Feriado Municipal com mesma data do que
			// esta sendo atualizado
			FiltroMunicipioFeriado filtroMunicipioFeriado2 = new FiltroMunicipioFeriado();

			filtroMunicipioFeriado2.adicionarParametro(new ParametroSimples(
					FiltroMunicipioFeriado.DATA, municipioFeriado
							.getDataFeriado()));

			filtroMunicipioFeriado2.adicionarParametro(new ParametroSimples(
					FiltroMunicipioFeriado.ID_MUNICIPIO, municipioFeriado
							.getMunicipio()));

			Collection colecaoMunicipioFeriado1 = getControladorUtil()
					.pesquisar(filtroMunicipioFeriado2,
							MunicipioFeriado.class.getName());

			if (colecaoMunicipioFeriado1 != null
					&& !colecaoMunicipioFeriado1.isEmpty()) {
				throw new ControladorException(
						"atencao.municipio_feriado_com_data_existente");
			}

			filtroMunicipioFeriado2.limparListaParametros();

			filtroMunicipioFeriado2.adicionarParametro(new ParametroSimples(
					FiltroMunicipioFeriado.NOME, municipioFeriado
							.getDescricaoFeriado()));

			filtroMunicipioFeriado2.adicionarParametro(new ParametroSimples(
					FiltroMunicipioFeriado.DATA, municipioFeriado
							.getDataFeriado()));

			filtroMunicipioFeriado2.adicionarParametro(new ParametroSimples(
					FiltroMunicipioFeriado.ID_MUNICIPIO, municipioFeriado
							.getMunicipio()));

			Collection colecaoMunicipioFeriado2 = getControladorUtil()
					.pesquisar(filtroMunicipioFeriado2,
							MunicipioFeriado.class.getName());

			if (colecaoMunicipioFeriado2 != null
					&& !colecaoMunicipioFeriado2.isEmpty()) {
				throw new ControladorException(
						"atencao.municipio_feriado_com_data_existente");
			} else {
				// [FS0006] Verificar Existencia do feriado para outra data
				// informada
				filtroMunicipioFeriado2.limparListaParametros();

				filtroMunicipioFeriado2
						.adicionarParametro(new ParametroSimples(
								FiltroMunicipioFeriado.NOME, municipioFeriado
										.getDescricaoFeriado()));

				filtroMunicipioFeriado2
						.adicionarParametro(new ParametroSimples(
								FiltroMunicipioFeriado.ID_MUNICIPIO,
								municipioFeriado.getMunicipio()));

				Collection colecaoMunicipioFeriado = getControladorUtil()
						.pesquisar(filtroMunicipioFeriado2,
								MunicipioFeriado.class.getName());

				if (colecaoMunicipioFeriado != null
						&& !colecaoMunicipioFeriado.isEmpty()) {
					Iterator iterator = colecaoMunicipioFeriado.iterator();

					while (iterator.hasNext()) {
						MunicipioFeriado feriadoMunicipal = (MunicipioFeriado) iterator
								.next();

						Date data = feriadoMunicipal.getDataFeriado();

						String dataString = Util.formatarData(data);

						String dataTela = Util.formatarData(municipioFeriado
								.getDataFeriado());

						String ano = dataString.substring(6, 10);

						String anoTela = dataTela.substring(6, 10);

						if (ano.equals(anoTela)) {
							throw new ControladorException(
									"atencao.municipio_feriado_mesmo_nome_mesmo_ano");
						}
					}
				}
			}

		}
		municipioFeriado.setUltimaAlteracao(new Date());

		// ------------ REGISTRAR TRANSAÇÃO----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_FERIADO_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_FERIADO_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		municipioFeriado.setOperacaoEfetuada(operacaoEfetuada);
		municipioFeriado.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(municipioFeriado);
		// ------------ REGISTRAR TRANSAÇÃO----------------------------

		Integer idFeriado = (Integer) getControladorUtil().inserir(
				municipioFeriado);
		return idFeriado;
	}

	/**
	 * Pesquisa os feriados(nacionais e municipais)
	 *
	 * @author Kássia Albuquerque
	 * @date 22/01/2007
	 *
	 */
	public Collection pesquisarFeriado(Short tipoFeriado, String descricao,
			Date dataFeriadoInicio, Date dataFeriadoFim, Integer idMunicipio,
			Integer numeroPagina) throws ControladorException {

		Collection colecaoObject = new ArrayList();

		Collection colecaoFeriado = new ArrayList();

		try {
			colecaoObject = repositorioCadastro.pesquisarFeriado(tipoFeriado,
					descricao, dataFeriadoInicio, dataFeriadoFim, idMunicipio,
					numeroPagina);
			Iterator iteratorObject = colecaoObject.iterator();
			while (iteratorObject.hasNext()) {
				Object[] arrayObject = (Object[]) iteratorObject.next();
				if (arrayObject != null) {
					// instancia um FeriadoHelper que é um helper
					FeriadoHelper feriadoHelper = new FeriadoHelper();
					// tipo do feriado
					if (arrayObject[0] != null) {
						feriadoHelper.setTipoFeriado((Short) arrayObject[0]);
					}
					// código do feriado
					if (arrayObject[1] != null) {
						feriadoHelper.setId((Integer) arrayObject[1]);
					}
					// descrição do feriado
					if (arrayObject[2] != null) {
						feriadoHelper.setDescricao((String) arrayObject[2]);
					}
					// descrição do município
					if (arrayObject[3] != null) {
						feriadoHelper
								.setDescricaoMunicipio((String) arrayObject[3]);
					}
					// data do feriado
					if (arrayObject[4] != null) {
						feriadoHelper.setData((Date) arrayObject[4]);
					}

					colecaoFeriado.add(feriadoHelper);
				}
			}

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return colecaoFeriado;
	}

	/**
	 * Pesquisar quantidade de registro dos feriados(nacionais e municipais)
	 *
	 * @author Kássia Albuquerque
	 * @date 22/01/2007
	 *
	 */
	public Integer pesquisarFeriadoCount(Short tipoFeriado, String descricao,
			Date dataFeriadoInicio, Date dataFeriadoFim, Integer idMunicipio)
			throws ControladorException {
		try {
			return repositorioCadastro.pesquisarFeriadoCount(tipoFeriado,
					descricao, dataFeriadoInicio, dataFeriadoFim, idMunicipio);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0535] Manter Feriado [SB0001] Atualizar Feriado
	 *
	 * @author Kassia Albuquerque
	 * @date 27/01/2006
	 *
	 * @pparam feriado
	 * @throws ControladorException
	 */

	public void atualizarFeriado(NacionalFeriado nacionalFeriado,
			MunicipioFeriado municipioFeriado, Usuario usuarioLogado)
			throws ControladorException {

		if (nacionalFeriado != null) {

			// [FS0003] - Verificando a existência do Feriado Nacional pela
			// descrição

			FiltroNacionalFeriado filtroNacionalFeriado = new FiltroNacionalFeriado();
			/*
			 * filtroNacionalFeriado .adicionarParametro(new ParametroSimples(
			 * FiltroNacionalFeriado.NOME, nacionalFeriado .getDescricao()));
			 *
			 * filtroNacionalFeriado .adicionarParametro(new
			 * ParametroSimplesDiferenteDe( FiltroNacionalFeriado.ID,
			 * nacionalFeriado.getId()));
			 *
			 * Collection colecaoNacionalFeriado =
			 * getControladorUtil().pesquisar( filtroNacionalFeriado,
			 * NacionalFeriado.class.getName());
			 *
			 * if (colecaoNacionalFeriado != null &&
			 * !colecaoNacionalFeriado.isEmpty()) { throw new
			 * ControladorException(
			 * "atencao.nacional_feriado.decricao.existente"); }
			 */

			// Verificando existência de mais de um Feriado Nacional numa mesma
			// data
			filtroNacionalFeriado.limparListaParametros();

			filtroNacionalFeriado.adicionarParametro(new ParametroSimples(
					FiltroNacionalFeriado.DATA, nacionalFeriado.getData()));

			filtroNacionalFeriado
					.adicionarParametro(new ParametroSimplesDiferenteDe(
							FiltroNacionalFeriado.ID, nacionalFeriado.getId()));

			Collection colecaoNacionalFeriado = new ArrayList();

			colecaoNacionalFeriado = getControladorUtil().pesquisar(
					filtroNacionalFeriado, NacionalFeriado.class.getName());

			if (!colecaoNacionalFeriado.isEmpty()) {
				throw new ControladorException(
						"atencao.nacional_feriado_com_data_existente");
			} else {
				filtroNacionalFeriado.limparListaParametros();

				filtroNacionalFeriado.adicionarParametro(new ParametroSimples(
						FiltroNacionalFeriado.DATA, nacionalFeriado.getData()));

				filtroNacionalFeriado.adicionarParametro(new ParametroSimples(
						FiltroNacionalFeriado.NOME, nacionalFeriado
								.getDescricao()));

				Collection colecaoNacionalFeriado2 = getControladorUtil()
						.pesquisar(filtroNacionalFeriado,
								NacionalFeriado.class.getName());

				if (!colecaoNacionalFeriado2.isEmpty()) {

					Iterator iterator2 = colecaoNacionalFeriado2.iterator();

					while (iterator2.hasNext()) {
						NacionalFeriado nacionalMunicipal = (NacionalFeriado) iterator2
								.next();

						Date data = nacionalMunicipal.getData();

						String data2String = Util.formatarData(data);

						String data2Tela = Util.formatarData(nacionalFeriado
								.getData());

						String ano2 = data2String.substring(6, 10);

						String ano2Tela = data2Tela.substring(6, 10);

						if (ano2.equals(ano2Tela)
								&& !data.equals(nacionalFeriado.getData())) {
							throw new ControladorException(
									"atencao.nacional_feriado_mesmo_nome_mesmo_ano");
						}
					}
				}
			}

			nacionalFeriado.setUltimaAlteracao(new Date());

			// ------------ REGISTRAR TRANSAÇÃO----------------------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_FERIADO_ATUALIZAR,
					new UsuarioAcaoUsuarioHelper(usuarioLogado,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

			Operacao operacao = new Operacao();
			operacao.setId(Operacao.OPERACAO_FERIADO_ATUALIZAR);

			OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
			operacaoEfetuada.setOperacao(operacao);

			nacionalFeriado.setOperacaoEfetuada(operacaoEfetuada);
			nacionalFeriado.adicionarUsuario(usuarioLogado,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(nacionalFeriado);
			// ------------ REGISTRAR TRANSAÇÃO----------------------------

			// [FS0004] - Atualização realizada por outro usuário

			FiltroNacionalFeriado filtroNacionalFeriadoBase = new FiltroNacionalFeriado();

			// Seta o filtro para buscar o FERIADO na base
			filtroNacionalFeriadoBase.adicionarParametro(new ParametroSimples(
					FiltroNacionalFeriado.ID, nacionalFeriado.getId()));

			// Procura servicoPerfilTipo na base
			Collection feriadoAtualizados = getControladorUtil().pesquisar(
					filtroNacionalFeriadoBase, NacionalFeriado.class.getName());

			NacionalFeriado nacionalFeriadoNaBase = (NacionalFeriado) Util
					.retonarObjetoDeColecao(feriadoAtualizados);

			if (nacionalFeriadoNaBase == null) {

				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.registro_remocao_nao_existente");
			}

			// Verificar se o feriado já foi atualizado por outro usuário
			// durante esta atualização

			if (nacionalFeriadoNaBase.getUltimaAlteracao().after(
					nacionalFeriado.getUltimaAlteracao())) {

				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}

			nacionalFeriado.setUltimaAlteracao(new Date());

			// Atualiza o objeto na base
			getControladorUtil().atualizar(nacionalFeriado);

		} else {

			// Verificando existencia de data Feriado Municipal numa mesma data
			// de Feriado Nacional
			FiltroNacionalFeriado filtroNacionalFeriado = new FiltroNacionalFeriado();

			filtroNacionalFeriado.adicionarParametro(new ParametroSimples(
					FiltroNacionalFeriado.DATA, municipioFeriado
							.getDataFeriado()));

			Collection colecaoNacionalFeriado = getControladorUtil().pesquisar(
					filtroNacionalFeriado, NacionalFeriado.class.getName());

			if (colecaoNacionalFeriado != null
					&& !colecaoNacionalFeriado.isEmpty()) {

				throw new ControladorException(
						"atencao.nacional_feriado_com_data_existente");
			}

			// [FS0003] - Verificando a existência do Feriado Municipal pela
			// descrição

			// Verificando se existe Feriado Municipal com mesma data do que
			// esta sendo atualizado
			FiltroMunicipioFeriado filtroMunicipioFeriado2 = new FiltroMunicipioFeriado();

			filtroMunicipioFeriado2.adicionarParametro(new ParametroSimples(
					FiltroMunicipioFeriado.DATA, municipioFeriado
							.getDataFeriado()));

			filtroMunicipioFeriado2.adicionarParametro(new ParametroSimples(
					FiltroMunicipioFeriado.ID_MUNICIPIO, municipioFeriado
							.getMunicipio()));

			filtroMunicipioFeriado2
					.adicionarParametro(new ParametroSimplesDiferenteDe(
							FiltroMunicipioFeriado.ID, municipioFeriado.getId()));

			Collection colecaoMunicipioFeriado2 = getControladorUtil()
					.pesquisar(filtroMunicipioFeriado2,
							MunicipioFeriado.class.getName());

			if (colecaoMunicipioFeriado2 != null
					&& !colecaoMunicipioFeriado2.isEmpty()) {
				throw new ControladorException(
						"atencao.municipio_feriado_com_data_existente");
			} else {
				// [FS0006] Verificar Existencia do feriado para outra data
				// informada
				filtroMunicipioFeriado2.limparListaParametros();

				filtroMunicipioFeriado2
						.adicionarParametro(new ParametroSimples(
								FiltroMunicipioFeriado.NOME, municipioFeriado
										.getDescricaoFeriado()));

				filtroMunicipioFeriado2
						.adicionarParametro(new ParametroSimples(
								FiltroMunicipioFeriado.ID_MUNICIPIO,
								municipioFeriado.getMunicipio()));

				Collection colecaoMunicipioFeriado = getControladorUtil()
						.pesquisar(filtroMunicipioFeriado2,
								MunicipioFeriado.class.getName());

				if (colecaoMunicipioFeriado != null
						&& !colecaoMunicipioFeriado.isEmpty()) {
					Iterator iterator = colecaoMunicipioFeriado.iterator();

					while (iterator.hasNext()) {
						MunicipioFeriado feriadoMunicipal = (MunicipioFeriado) iterator
								.next();

						Date data = feriadoMunicipal.getDataFeriado();

						String dataString = Util.formatarData(data);

						String dataTela = Util.formatarData(municipioFeriado
								.getDataFeriado());

						String ano = dataString.substring(6, 10);

						String anoTela = dataTela.substring(6, 10);

						if (ano.equals(anoTela)
								&& !data.equals(municipioFeriado
										.getDataFeriado())) {
							throw new ControladorException(
									"atencao.municipio_feriado_mesmo_nome_mesmo_ano");
						}
					}
				}
			}

			municipioFeriado.setUltimaAlteracao(new Date());

			// ------------ REGISTRAR TRANSAÇÃO----------------------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_FERIADO_ATUALIZAR,
					new UsuarioAcaoUsuarioHelper(usuarioLogado,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

			Operacao operacao = new Operacao();
			operacao.setId(Operacao.OPERACAO_FERIADO_ATUALIZAR);

			OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
			operacaoEfetuada.setOperacao(operacao);

			municipioFeriado.setOperacaoEfetuada(operacaoEfetuada);
			municipioFeriado.adicionarUsuario(usuarioLogado,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(municipioFeriado);
			// ------------ REGISTRAR TRANSAÇÃO----------------------------

			// [FS0004] - Atualização realizada por outro usuário

			FiltroMunicipioFeriado filtroMunicipioFeriadoBase = new FiltroMunicipioFeriado();
			// Seta o filtro para buscar o FERIADO na base
			filtroMunicipioFeriadoBase.adicionarParametro(new ParametroSimples(
					FiltroMunicipioFeriado.ID, municipioFeriado.getId()));

			// Procura feriado na base
			Collection feriadoAtualizados = getControladorUtil().pesquisar(
					filtroMunicipioFeriadoBase,
					MunicipioFeriado.class.getName());

			MunicipioFeriado municipioFeriadoNaBase = (MunicipioFeriado) Util
					.retonarObjetoDeColecao(feriadoAtualizados);

			if (municipioFeriadoNaBase == null) {

				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.registro_remocao_nao_existente");
			}

			// Verificar se o feriado já foi atualizado por outro usuário
			// durante esta atualização

			if (municipioFeriadoNaBase.getUltimaAlteracao().after(
					municipioFeriado.getUltimaAlteracao())) {

				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}

			municipioFeriado.setUltimaAlteracao(new Date());

			// Atualiza o objeto na base
			getControladorUtil().atualizar(municipioFeriado);

		}
	}

	/**
	 * [UC0535] Manter Feriado
	 *
	 * Remover Feriado
	 *
	 * @author Kassia Albuquerque
	 * @date 29/01/2007
	 *
	 * @pparam feriado
	 * @throws ControladorException
	 */
	public void removerFeriado(String[] ids, Usuario usuarioLogado)
			throws ControladorException {

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_FERIADO_REMOVER);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(
				usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
		colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		if (ids != null && ids.length != 0) {
			for (int i = 0; i < ids.length; i++) {
				String[] idsColecao = ids[i].split(";");
				if (idsColecao[1].equals("2")) {
					this.getControladorUtil().removerUm(
							new Integer(idsColecao[0]),
							MunicipioFeriado.class.getName(), operacaoEfetuada,
							colecaoUsuarios);
				} else {
					this.getControladorUtil().removerUm(
							new Integer(idsColecao[0]),
							NacionalFeriado.class.getName(), operacaoEfetuada,
							colecaoUsuarios);
				}

			}
		}
	}

	/**
	 * Pesquisar os ids do Setor comercial pela localidade
	 *
	 * @author Ana Maria
	 * @date 07/02/2007
	 *
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsSetorComercial(Integer idLocalidade)
			throws ControladorException {
		try {
			return repositorioSetorComercial
					.pesquisarIdsSetorComercial(idLocalidade);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Informar Mensagem do Sistema
	 *
	 * @author Kássia Albuquerque
	 * @date 02/03/2007
	 *
	 */
	public void atualizarMensagemSistema(SistemaParametro sistemaParametro,
			Usuario usuarioLogado) throws ControladorException {

		// [FS0003] - Atualização realizada por outro usuário
		if (sistemaParametro == null) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.registro_remocao_nao_existente");
		}

		// Verificar se já foi atualizado por outro usuário durante esta
		// atualização
		SistemaParametro sistemaParametroBase = getControladorUtil()
				.pesquisarParametrosDoSistema();

		if (sistemaParametroBase.getUltimaAlteracao().after(
				sistemaParametro.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}
		try {
			// Atualiza o objeto na base
			repositorioCadastro.atualizarMensagemSistema(sistemaParametro
					.getMensagemSistema());
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Pesquisa os dados do email do batch para ser enviado
	 *
	 * @author Sávio Luiz
	 * @date 13/03/2007
	 *
	 */
	public EnvioEmail pesquisarEnvioEmail(Integer idEnvioEmail)
			throws ControladorException {
		try {
			return repositorioCadastro.pesquisarEnvioEmail(idEnvioEmail);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	public DadosEnvioEmailHelper pesquisarDadosEmailSistemaParametros()
			throws ControladorException {

		try {
			return repositorioCadastro.pesquisarDadosEmailSistemaParametros();
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0842] Inserir Funcionário
	 *
	 * @author Rômulo Aurélio, Raphael Rossiter
	 * @date 12/04/2007, 17/06/2009
	 *
	 * @param funcionario,
	 * @param usuarioLogado
	 * @throws ControladorException
	 */
	public void inserirFuncionario(Funcionario funcionario,
			Usuario usuarioLogado) throws ControladorException {

		// VALIDANDO OS DADOS DO FUNCIONÁRIO
		this.validarFuncionario(funcionario, true);

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_FUNCIONARIO_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_FUNCIONARIO_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		funcionario.setOperacaoEfetuada(operacaoEfetuada);
		funcionario.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(funcionario);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		getControladorUtil().inserir(funcionario);

	}

	/**
	 * [UC????] Atualizar Funcionario
	 *
	 * @author Rômulo Aurélio
	 * @date 17/04/2007
	 *
	 * @param funcionario,
	 *            usuarioLogado, idFuncionario
	 *
	 */
	public void atualizarFuncionario(Funcionario funcionario,
			Usuario usuarioLogado) throws ControladorException {

		// VALIDANDO OS DADOS DO FUNCIONÁRIO
		this.validarFuncionario(funcionario, false);

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_FUNCIONARIO_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_FUNCIONARIO_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		funcionario.setOperacaoEfetuada(operacaoEfetuada);
		funcionario.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(funcionario);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		getControladorUtil().atualizar(funcionario);

	}

	/**
	 * Pesquisar todos ids dos setores comerciais.
	 *
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 *
	 * @author Pedro Alexandre
	 * @date 25/04/2007
	 *
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarTodosIdsSetorComercial()
			throws ControladorException {
		try {
			return repositorioCadastro.pesquisarTodosIdsSetorComercial();
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Este caso de uso permite a emissão de boletins de cadastro
	 *
	 * [UC0582] Emitir Boletim de Cadastro
	 *
	 * @author Rafael Corrêa, Ivan Sergio
	 * @data 15/05/2007, 26/01/2009
	 * @alteracao 26/01/2009 - CRC1076 - Alterado o nome do arquivo gerado.
	 *
	 * @param
	 * @return void
	 */
	public void emitirBoletimCadastro(
			CobrancaAcaoAtividadeCronograma cronogramaAtividadeAcaoCobranca,
			CobrancaAcaoAtividadeComando comandoAtividadeAcaoCobranca,
			Date dataAtualPesquisa, CobrancaAcao cobrancaAcao,
			int idFuncionalidadeIniciada) throws ControladorException {

		int idUnidadeIniciada = 0;

		if (comandoAtividadeAcaoCobranca != null) {
			idUnidadeIniciada = getControladorBatch()
					.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
							UnidadeProcessamento.COB_ACAO_ATIV_COMAND,
							comandoAtividadeAcaoCobranca.getId());
		} else {
			idUnidadeIniciada = getControladorBatch()
					.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
							UnidadeProcessamento.COB_ACAO_ATIV_CRONOG,
							cronogramaAtividadeAcaoCobranca.getId());
		}

		Integer idCronogramaAtividadeAcaoCobranca = null;
		Integer idComandoAtividadeAcaoCobranca = null;

		if (cronogramaAtividadeAcaoCobranca != null
				&& cronogramaAtividadeAcaoCobranca.getId() != null) {
			idCronogramaAtividadeAcaoCobranca = cronogramaAtividadeAcaoCobranca
					.getId();
		}
		if (comandoAtividadeAcaoCobranca != null
				&& comandoAtividadeAcaoCobranca.getId() != null) {
			idComandoAtividadeAcaoCobranca = comandoAtividadeAcaoCobranca
					.getId();
		}

		// Caso seja cobrança ação atividade cronograma e seja Fiscalização
		// cortado ou suprimido então gera boletin de cadastro
		// Caso seja cobrança ação atividade comando e o indicador de emissão de
		// boletim seja "SIM"(1) então gera boletin de cadastro
		if ((idCronogramaAtividadeAcaoCobranca != null && (cobrancaAcao.getId()
				.equals(CobrancaAcao.FISCALIZACAO_SUPRIMIDO) || cobrancaAcao
				.getId().equals(CobrancaAcao.FISCALIZACAO_CORTADO)))
				|| (idComandoAtividadeAcaoCobranca != null
						&& comandoAtividadeAcaoCobranca.getIndicadorBoletim() != null && comandoAtividadeAcaoCobranca
						.getIndicadorBoletim()
						.equals(
								CobrancaAcaoAtividadeComando.INDICADOR_BOLETIM_SIM))) {

			System.out.println("********************");
			System.out.println("INICIO BOLETIM CADASTRO");
			System.out.println("********************");

			try {

				boolean flagFimPesquisa = false;
				final int quantidadeCobrancaDocumento = 500;
				int quantidadeCobrancaDocumentoInicio = 0;
				StringBuilder boletimCadastroTxt = new StringBuilder();
				int sequencialImpressao = 0;
				int pagina = 0;

				while (!flagFimPesquisa) {

					pagina++;

					Collection colecaoEmitirBoletimCadastro = null;
					try {

						colecaoEmitirBoletimCadastro = repositorioCobranca
								.pesquisarCobrancaDocumentoBoletimCadastro(
										idCronogramaAtividadeAcaoCobranca,
										idComandoAtividadeAcaoCobranca,
										dataAtualPesquisa,
										cobrancaAcao.getId(),
										quantidadeCobrancaDocumentoInicio);

					} catch (ErroRepositorioException ex) {
						ex.printStackTrace();
						throw new ControladorException("erro.sistema", ex);
					}

					if (colecaoEmitirBoletimCadastro != null
							&& !colecaoEmitirBoletimCadastro.isEmpty()) {

						if (colecaoEmitirBoletimCadastro.size() < quantidadeCobrancaDocumento) {
							flagFimPesquisa = true;
						} else {
							quantidadeCobrancaDocumentoInicio = quantidadeCobrancaDocumentoInicio + 500;
						}

						Iterator colecaoEmitirBoletimCadastroIterator = colecaoEmitirBoletimCadastro
								.iterator();
						int count = 0;

						EmitirDocumentoCobrancaBoletimCadastroHelper emitirDocumentoCobrancaBoletimCadastroHelper = null;

						while (colecaoEmitirBoletimCadastroIterator.hasNext()) {

							emitirDocumentoCobrancaBoletimCadastroHelper = (EmitirDocumentoCobrancaBoletimCadastroHelper) colecaoEmitirBoletimCadastroIterator
									.next();

							count++;

							System.out
									.println("VEZ QUE ENTRA:"
											+ pagina
											+ " / "
											+ count
											+ " / IMÓVEL:"
											+ emitirDocumentoCobrancaBoletimCadastroHelper
													.getIdImovel().toString());

							sequencialImpressao++;

							if (emitirDocumentoCobrancaBoletimCadastroHelper != null) {
								criarDadosTxtBoletimCadastro(
										boletimCadastroTxt,
										emitirDocumentoCobrancaBoletimCadastroHelper);
							}

							emitirDocumentoCobrancaBoletimCadastroHelper = null;

							boletimCadastroTxt.append(System
									.getProperty("line.separator"));
						}
					} else {
						flagFimPesquisa = true;
					}
				}

				System.out.println("********************");
				System.out.println("FIM BOLETIM CADASTRO");
				System.out.println("********************");

				Date dataAtual = new Date();

				String nomeZip = null;
				String tituloComandoEventual = null;
				String grupo = null;

				if (comandoAtividadeAcaoCobranca != null
						&& comandoAtividadeAcaoCobranca.getId() != null) {

					tituloComandoEventual = comandoAtividadeAcaoCobranca
							.getDescricaoTitulo();
					nomeZip = "BOLETIM_CADASTRAL_" + tituloComandoEventual
							+ " " + Util.formatarDataComHora(dataAtual);
				} else {
					grupo = cronogramaAtividadeAcaoCobranca
							.getCobrancaAcaoCronograma()
							.getCobrancaGrupoCronogramaMes().getCobrancaGrupo()
							.getId().toString();

					nomeZip = "BOLETIM_CAD_GRUPO_" + grupo + "_"
							+ Util.formatarDataComHora(dataAtual);
				}

				nomeZip = nomeZip.replace("/", "_");
				nomeZip = nomeZip.replace(" ", "_");
				nomeZip = nomeZip.replace(":", "_");
				nomeZip = nomeZip.replace("/", "_");

				try {
					if (boletimCadastroTxt != null
							&& boletimCadastroTxt.length() != 0) {

						boletimCadastroTxt.append("\u0004");

						// criar o arquivo zip
						File compactado = new File(nomeZip + ".zip"); // nomeZip
						ZipOutputStream zos = new ZipOutputStream(
								new FileOutputStream(compactado));

						File leitura = new File(nomeZip + ".txt");

						BufferedWriter out = new BufferedWriter(
								new OutputStreamWriter(new FileOutputStream(
										leitura.getAbsolutePath())));

						out.write(boletimCadastroTxt.toString());
						out.flush();
						out.close();
						ZipUtil.adicionarArquivo(zos, leitura);

						// close the stream
						zos.close();
						leitura.delete();
					}

					System.out.println("********************");
					System.out.println("FIM GERAÇÃO ARQUIVO");
					System.out.println("********************");

				} catch (IOException e) {
					e.printStackTrace();
					throw new ControladorException("erro.sistema", e);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ControladorException("erro.sistema", e);
				}

				getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
						idUnidadeIniciada, false);

			} catch (Exception e) {

				// Este catch serve para interceptar qualquer exceção que o
				// processo
				// batch venha a lançar e garantir que a unidade de
				// processamento do
				// batch será atualizada com o erro ocorrido
				e.printStackTrace();
				getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
						idUnidadeIniciada, true);
				throw new EJBException(e);
			}
		} else {
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
		}
	}

	private void criarDadosTxtBoletimCadastro(
			StringBuilder boletimCadastroTxt,
			EmitirDocumentoCobrancaBoletimCadastroHelper emitirDocumentoCobrancaBoletimCadastroHelper)
			throws ControladorException {

		ClienteEmitirBoletimCadastroHelper clienteEmitirBoletimCadastroHelperProprietario = null;
		ClienteEmitirBoletimCadastroHelper clienteEmitirBoletimCadastroHelperUsuario = null;

		clienteEmitirBoletimCadastroHelperProprietario = getControladorCliente()
				.pesquisarClienteEmitirBoletimCadastro(
						emitirDocumentoCobrancaBoletimCadastroHelper
								.getIdImovel(), ClienteRelacaoTipo.PROPRIETARIO);

		clienteEmitirBoletimCadastroHelperUsuario = getControladorCliente()
				.pesquisarClienteEmitirBoletimCadastro(
						emitirDocumentoCobrancaBoletimCadastroHelper
								.getIdImovel(), ClienteRelacaoTipo.USUARIO);

		// Início do processo de geração do arquivo txt

		// Número Documento/Referência
		boletimCadastroTxt.append(Util.completaString("", 8));

		// Dados do Cliente Proprietário
		if (clienteEmitirBoletimCadastroHelperProprietario != null) {

			adicionarDadosClienteEmitirBoletimCadastroTxt(boletimCadastroTxt,
					clienteEmitirBoletimCadastroHelperProprietario);
		} else {
			boletimCadastroTxt.append(Util.completaString("", 279));
		}

		// Dados do Cliente Usuário
		if (clienteEmitirBoletimCadastroHelperUsuario != null) {

			adicionarDadosClienteEmitirBoletimCadastroTxt(boletimCadastroTxt,
					clienteEmitirBoletimCadastroHelperUsuario);

		} else {
			boletimCadastroTxt.append(Util.completaString("", 279));
		}

		// Dados do Imóvel
		boletimCadastroTxt.append(Util.completaString("", 1));

		// Inscrição
		String inscricaoImovel =

		// Localidade
		Util.adicionarZerosEsquedaNumero(3,
				emitirDocumentoCobrancaBoletimCadastroHelper.getIdLocalidade()
						.toString())

				// Setor Comercial
				+ Util.adicionarZerosEsquedaNumero(3,
						emitirDocumentoCobrancaBoletimCadastroHelper
								.getCodigoSetorComercial().toString())

				// Quadra
				+ Util.adicionarZerosEsquedaNumero(3, ""
						+ emitirDocumentoCobrancaBoletimCadastroHelper
								.getNumeroQuadra())

				// Lote
				+ Util.adicionarZerosEsquedaNumero(4, ""
						+ emitirDocumentoCobrancaBoletimCadastroHelper
								.getLote())

				// Sublote
				+ Util.adicionarZerosEsquedaNumero(3, ""
						+ emitirDocumentoCobrancaBoletimCadastroHelper
								.getSubLote());

		boletimCadastroTxt.append(Util.completaString(inscricaoImovel, 16));

		// Matrícula do imóvel
		String matriculaImovelFormatada = Util
				.adicionarZerosEsquedaNumero(9, ""
						+ emitirDocumentoCobrancaBoletimCadastroHelper
								.getIdImovel());

		boletimCadastroTxt.append(Util.completaString(matriculaImovelFormatada,
				9));

		// Código do Cliente Proprietário
		String idClienteProprietario = "";

		if (clienteEmitirBoletimCadastroHelperProprietario != null) {

			idClienteProprietario = Util.adicionarZerosEsquedaNumero(12,
					clienteEmitirBoletimCadastroHelperProprietario.getCliente()
							.getId().toString());
		}

		boletimCadastroTxt.append(Util
				.completaString(idClienteProprietario, 12));

		// Inscrição Atual
		boletimCadastroTxt.append(Util.completaString("", 16));

		// Número de Moradores
		String numeroMoradores = "";

		if (emitirDocumentoCobrancaBoletimCadastroHelper.getNumeroMorador() != null) {

			numeroMoradores = Util.adicionarZerosEsquedaNumero(4,
					emitirDocumentoCobrancaBoletimCadastroHelper
							.getNumeroMorador().toString());
		}

		boletimCadastroTxt.append(Util.completaString(numeroMoradores, 4));

		// Nome na Conta
		String nomeConta = "";
		Integer idRelacaoTipo = null;

		try {

			idRelacaoTipo = repositorioClienteImovel
					.retornaTipoRelacaoClienteImovelNomeConta(emitirDocumentoCobrancaBoletimCadastroHelper
							.getIdImovel());

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (idRelacaoTipo != null) {

			if (idRelacaoTipo.toString().equals(
					ClienteRelacaoTipo.PROPRIETARIO.toString())) {
				nomeConta = "P";
			} else if (idRelacaoTipo.toString().equals(
					ClienteRelacaoTipo.USUARIO.toString())) {
				nomeConta = "U";
			} else {
				nomeConta = "R";
			}
		}

		boletimCadastroTxt.append(Util.completaString(nomeConta, 1));

		// Código do Cliente Usuário
		String idClienteUsuario = "";

		if (clienteEmitirBoletimCadastroHelperUsuario != null) {
			idClienteUsuario = Util.adicionarZerosEsquedaNumero(12,
					clienteEmitirBoletimCadastroHelperUsuario.getCliente()
							.getId().toString());
		}

		boletimCadastroTxt.append(Util.completaString(idClienteUsuario, 12));

		// Logradouro
		String idLogradouro = "";

		if (emitirDocumentoCobrancaBoletimCadastroHelper.getNumeroMorador() != null) {

			idLogradouro = Util.adicionarZerosEsquedaNumero(9,
					emitirDocumentoCobrancaBoletimCadastroHelper
							.getIdLogradouro().toString());
		}

		boletimCadastroTxt.append(Util.completaString(idLogradouro, 9));

		// Endereço Abreviado
		String enderecoImovel = getControladorEndereco()
				.pesquisarEnderecoFormatado(
						emitirDocumentoCobrancaBoletimCadastroHelper
								.getIdImovel());

		if (enderecoImovel == null) {
			enderecoImovel = "";
		}

		boletimCadastroTxt.append(Util.completaString(enderecoImovel, 60));

		// CEP
		String cep = "";

		if (emitirDocumentoCobrancaBoletimCadastroHelper.getCodigoCep() != null) {

			cep = Util.adicionarZerosEsquedaNumero(8,
					emitirDocumentoCobrancaBoletimCadastroHelper.getCodigoCep()
							.toString());
		}

		boletimCadastroTxt.append(Util.completaString(cep, 8));

		// Bairro
		String bairro = "";

		if (emitirDocumentoCobrancaBoletimCadastroHelper.getCodigoBairro() != null) {

			bairro = Util.adicionarZerosEsquedaNumero(3,
					emitirDocumentoCobrancaBoletimCadastroHelper
							.getCodigoBairro().toString());
		}

		boletimCadastroTxt.append(Util.completaString(bairro, 3));

		// Referência
		String referencia = "";

		if (emitirDocumentoCobrancaBoletimCadastroHelper.getReferencia() != null) {

			referencia = emitirDocumentoCobrancaBoletimCadastroHelper
					.getReferencia().toString();
		}

		boletimCadastroTxt.append(Util.completaString(referencia, 1));

		// Número do Imóvel
		String numeroImovel = "";

		if (emitirDocumentoCobrancaBoletimCadastroHelper.getNumeroImovel() != null) {

			numeroImovel = Util.adicionarZerosEsquedaNumero(5,
					emitirDocumentoCobrancaBoletimCadastroHelper
							.getNumeroImovel().toString());
		}

		boletimCadastroTxt.append(Util.completaString(numeroImovel, 5));

		// Complemento
		String complemento = "";

		if (emitirDocumentoCobrancaBoletimCadastroHelper.getComplemento() != null) {
			complemento = emitirDocumentoCobrancaBoletimCadastroHelper
					.getComplemento();
		}

		boletimCadastroTxt.append(Util.completaString(complemento, 19));

		// Dados das Subcategorias
		Collection colecaoSubcategorias = getControladorImovel()
				.obterQuantidadeEconomiasSubCategoria(
						emitirDocumentoCobrancaBoletimCadastroHelper
								.getIdImovel());

		String subcategorias = "";

		if (colecaoSubcategorias != null && !colecaoSubcategorias.isEmpty()) {

			Iterator colecaoSubcategoriasIterator = colecaoSubcategorias
					.iterator();

			for (int i = 0; i < 6; i++) {

				if (colecaoSubcategoriasIterator.hasNext()) {

					Subcategoria subcategoria = (Subcategoria) colecaoSubcategoriasIterator
							.next();

					subcategorias = subcategorias
							+ Util.adicionarZerosEsquedaNumero(2, subcategoria
									.getId()
									+ "")
							+ Util.adicionarZerosEsquedaNumero(4, subcategoria
									.getQuantidadeEconomias().toString());
				} else {
					break;
				}
			}
		}

		boletimCadastroTxt.append(Util.completaString(subcategorias, 36));

		// Qtde Apartamentos (Hotel)
		boletimCadastroTxt.append(Util.completaString("", 6));

		// Área Construída
		String areaConstruida = "";

		if (emitirDocumentoCobrancaBoletimCadastroHelper.getAreaConstruida() != null) {

			areaConstruida = ""
					+ emitirDocumentoCobrancaBoletimCadastroHelper
							.getAreaConstruida().intValue();
		}

		boletimCadastroTxt.append(Util.completaString(areaConstruida, 6));

		// Situação de Água
		String situacaoAgua = "";

		if (emitirDocumentoCobrancaBoletimCadastroHelper
				.getIdLigacaoAguaSituacao() != null) {

			situacaoAgua = emitirDocumentoCobrancaBoletimCadastroHelper
					.getIdLigacaoAguaSituacao().toString();
		}

		boletimCadastroTxt.append(Util.completaString(situacaoAgua, 1));

		// Obtém os dados das ligações de água e esgoto
		DadosLigacoesBoletimCadastroHelper dadosLigacoesBoletimCadastroHelper = getControladorAtendimentoPublico()
				.obterDadosLigacaoAguaEsgoto(
						emitirDocumentoCobrancaBoletimCadastroHelper
								.getIdImovel());

		// Diâmetro Ligação Água
		String diametroLigAgua = "";

		if (dadosLigacoesBoletimCadastroHelper.getLigacaoAgua() != null
				&& dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
						.getLigacaoAguaDiametro() != null) {

			diametroLigAgua = dadosLigacoesBoletimCadastroHelper
					.getLigacaoAgua().getLigacaoAguaDiametro().getId()
					.toString();

		}

		boletimCadastroTxt.append(Util.completaString(diametroLigAgua, 1));

		// Material Ligação Água
		String materialLigAgua = "";

		if (dadosLigacoesBoletimCadastroHelper.getLigacaoAgua() != null
				&& dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
						.getLigacaoAguaMaterial() != null) {

			materialLigAgua = dadosLigacoesBoletimCadastroHelper
					.getLigacaoAgua().getLigacaoAguaMaterial().getId()
					.toString();

		}

		boletimCadastroTxt.append(Util.completaString(materialLigAgua, 1));

		// Volume Reservatório Inferior
		String volumeReservatorioInferior = "";

		if (emitirDocumentoCobrancaBoletimCadastroHelper
				.getVolumeReservatorioInferior() != null) {

			volumeReservatorioInferior = emitirDocumentoCobrancaBoletimCadastroHelper
					.getVolumeReservatorioInferior().toString();
		}

		boletimCadastroTxt.append(Util.completaString(
				volumeReservatorioInferior, 1));

		// Volume Reservatório Superior
		String volumeReservatorioSuperior = "";

		if (emitirDocumentoCobrancaBoletimCadastroHelper
				.getVolumeReservatorioSuperior() != null) {

			volumeReservatorioSuperior = emitirDocumentoCobrancaBoletimCadastroHelper
					.getVolumeReservatorioSuperior().toString();
		}

		boletimCadastroTxt.append(Util.completaString(
				volumeReservatorioSuperior, 1));

		// Volume Piscina
		String volumePiscina = "";

		if (emitirDocumentoCobrancaBoletimCadastroHelper.getVolumePiscina() != null) {
			volumePiscina = emitirDocumentoCobrancaBoletimCadastroHelper
					.getVolumePiscina().toString();
		}

		boletimCadastroTxt.append(Util.completaString(volumePiscina, 1));

		// Jardim
		String jardim = "";

		if (emitirDocumentoCobrancaBoletimCadastroHelper.getJardim() != null) {
			jardim = emitirDocumentoCobrancaBoletimCadastroHelper.getJardim()
					.toString();
		}

		boletimCadastroTxt.append(Util.completaString(jardim, 1));

		// Pavimento Calçada
		String pavimentoCalcada = "";

		if (emitirDocumentoCobrancaBoletimCadastroHelper
				.getIdPavimentoCalcada() != null) {

			pavimentoCalcada = Util.adicionarZerosEsquedaNumero(2,
					emitirDocumentoCobrancaBoletimCadastroHelper
							.getIdPavimentoCalcada().toString());
		}

		boletimCadastroTxt.append(Util.completaString(pavimentoCalcada, 2));

		// Pavimento Rua
		String pavimentoRua = "";

		if (emitirDocumentoCobrancaBoletimCadastroHelper.getIdPavimentoRua() != null) {

			pavimentoRua = Util.adicionarZerosEsquedaNumero(2,
					emitirDocumentoCobrancaBoletimCadastroHelper
							.getIdPavimentoRua().toString());
		}

		boletimCadastroTxt.append(Util.completaString(pavimentoRua, 2));

		// Fonte de Abastecimento
		String fonteAbastecimento = "";

		if (emitirDocumentoCobrancaBoletimCadastroHelper
				.getIdFonteAbastecimento() != null) {
			fonteAbastecimento = emitirDocumentoCobrancaBoletimCadastroHelper
					.getIdFonteAbastecimento().toString();
		}

		boletimCadastroTxt.append(Util.completaString(fonteAbastecimento, 1));

		// Tipo de Poço
		String pocoTipo = "";

		if (emitirDocumentoCobrancaBoletimCadastroHelper.getIdPoco() != null) {
			pocoTipo = emitirDocumentoCobrancaBoletimCadastroHelper.getIdPoco()
					.toString();
		}

		boletimCadastroTxt.append(Util.completaString(pocoTipo, 1));

		// Número de Pontos
		String numeroPontos = "";

		if (emitirDocumentoCobrancaBoletimCadastroHelper
				.getNumeroPontosUtilizacao() != null) {

			numeroPontos = Util.adicionarZerosEsquedaNumero(4,
					emitirDocumentoCobrancaBoletimCadastroHelper
							.getNumeroPontosUtilizacao().toString());
		}

		boletimCadastroTxt.append(Util.completaString(numeroPontos, 4));

		// Situação de Esgoto
		String situacaoEsgoto = "";

		if (emitirDocumentoCobrancaBoletimCadastroHelper
				.getIdLigacaoEsgotoSituacao() != null) {

			situacaoEsgoto = emitirDocumentoCobrancaBoletimCadastroHelper
					.getIdLigacaoEsgotoSituacao().toString();
		}

		boletimCadastroTxt.append(Util.completaString(situacaoEsgoto, 1));

		// Diâmetro Ligação Esgoto
		String diametroLigEsgoto = "";

		if (dadosLigacoesBoletimCadastroHelper.getLigacaoEsgoto() != null
				&& dadosLigacoesBoletimCadastroHelper.getLigacaoEsgoto()
						.getLigacaoEsgotoDiametro() != null) {

			diametroLigEsgoto = dadosLigacoesBoletimCadastroHelper
					.getLigacaoEsgoto().getLigacaoEsgotoDiametro().getId()
					.toString();

		}

		boletimCadastroTxt.append(Util.completaString(diametroLigEsgoto, 1));

		// Material Ligação Esgoto
		String materialLigEsgoto = "";

		if (dadosLigacoesBoletimCadastroHelper.getLigacaoEsgoto() != null
				&& dadosLigacoesBoletimCadastroHelper.getLigacaoEsgoto()
						.getLigacaoEsgotoMaterial() != null) {

			materialLigEsgoto = dadosLigacoesBoletimCadastroHelper
					.getLigacaoEsgoto().getLigacaoEsgotoMaterial().getId()
					.toString();

		}

		boletimCadastroTxt.append(Util.completaString(materialLigEsgoto, 1));

		// Perfil do Imóvel
		String perfilImovel = "";

		if (emitirDocumentoCobrancaBoletimCadastroHelper.getIdImovelPerfil() != null) {
			perfilImovel = emitirDocumentoCobrancaBoletimCadastroHelper
					.getIdImovelPerfil().toString();
		}

		boletimCadastroTxt.append(Util.completaString(perfilImovel, 1));

		// Despejo
		String despejo = "";

		if (emitirDocumentoCobrancaBoletimCadastroHelper.getIdDespejo() != null) {
			despejo = emitirDocumentoCobrancaBoletimCadastroHelper
					.getIdDespejo().toString();
		}

		boletimCadastroTxt.append(Util.completaString(despejo, 1));

		// Dados do Hidrômetro na Ligação de Água
		// Leitura Inicial
		String leituraInicial = "";

		if (dadosLigacoesBoletimCadastroHelper.getLigacaoAgua() != null
				&& dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
						.getHidrometroInstalacaoHistorico() != null) {

			leituraInicial = Util.adicionarZerosEsquedaNumero(6,
					dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
							.getHidrometroInstalacaoHistorico()
							.getNumeroLeituraInstalacao().toString());

		}

		// Numero do Hidrometro
		String numeroHidrometro = "";
		if (dadosLigacoesBoletimCadastroHelper.getLigacaoAgua() != null
				&& dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
						.getHidrometroInstalacaoHistorico() != null) {

			numeroHidrometro = dadosLigacoesBoletimCadastroHelper
					.getLigacaoAgua().getHidrometroInstalacaoHistorico()
					.getHidrometro().getNumero();
		}

		boletimCadastroTxt.append(Util.completaString(leituraInicial, 6));

		// Capacidade
		String capacidadeHidrometro = "";

		if (dadosLigacoesBoletimCadastroHelper.getLigacaoAgua() != null
				&& dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
						.getHidrometroInstalacaoHistorico() != null
				&& dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
						.getHidrometroInstalacaoHistorico().getHidrometro()
						.getHidrometroCapacidade() != null) {

			capacidadeHidrometro = Util.adicionarZerosEsquedaNumero(2,
					dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
							.getHidrometroInstalacaoHistorico().getHidrometro()
							.getHidrometroCapacidade().getId().toString());

		}

		boletimCadastroTxt.append(Util.completaString(capacidadeHidrometro, 2));

		// Marca
		String marcaHidrometro = "";

		if (dadosLigacoesBoletimCadastroHelper.getLigacaoAgua() != null
				&& dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
						.getHidrometroInstalacaoHistorico() != null
				&& dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
						.getHidrometroInstalacaoHistorico().getHidrometro()
						.getHidrometroMarca() != null) {

			marcaHidrometro = Util.adicionarZerosEsquedaNumero(2,
					dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
							.getHidrometroInstalacaoHistorico().getHidrometro()
							.getHidrometroMarca().getId().toString());

		}

		boletimCadastroTxt.append(Util.completaString(marcaHidrometro, 2));

		// Local de Instalação do Hidrômetro
		String localInstalacaoHidrometro = "";

		if (dadosLigacoesBoletimCadastroHelper.getLigacaoAgua() != null
				&& dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
						.getHidrometroInstalacaoHistorico() != null
				&& dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
						.getHidrometroInstalacaoHistorico()
						.getHidrometroLocalInstalacao() != null) {

			localInstalacaoHidrometro = Util.adicionarZerosEsquedaNumero(2,
					dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
							.getHidrometroInstalacaoHistorico()
							.getHidrometroLocalInstalacao().getId().toString());

		}

		boletimCadastroTxt.append(Util.completaString(
				localInstalacaoHidrometro, 2));

		// Proteção
		String protecaoHidrometro = "";

		if (dadosLigacoesBoletimCadastroHelper.getLigacaoAgua() != null
				&& dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
						.getHidrometroInstalacaoHistorico() != null
				&& dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
						.getHidrometroInstalacaoHistorico()
						.getHidrometroProtecao() != null) {

			protecaoHidrometro = dadosLigacoesBoletimCadastroHelper
					.getLigacaoAgua().getHidrometroInstalacaoHistorico()
					.getHidrometroProtecao().getId().toString();

		}

		boletimCadastroTxt.append(Util.completaString(protecaoHidrometro, 1));

		// Indicador Cavalete
		String indicadorCavalete = "";

		if (dadosLigacoesBoletimCadastroHelper.getLigacaoAgua() != null
				&& dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
						.getHidrometroInstalacaoHistorico() != null
				&& dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
						.getHidrometroInstalacaoHistorico()
						.getIndicadorExistenciaCavalete() != null) {

			protecaoHidrometro = dadosLigacoesBoletimCadastroHelper
					.getLigacaoAgua().getHidrometroInstalacaoHistorico()
					.getIndicadorExistenciaCavalete().toString();

		}

		boletimCadastroTxt.append(Util.completaString(indicadorCavalete, 1));

		// Número IPTU
		String numeroIptu = "";

		if (emitirDocumentoCobrancaBoletimCadastroHelper.getNumeroIptu() != null) {

			numeroIptu = Util.adicionarZerosEsquedaNumero(26, ""
					+ emitirDocumentoCobrancaBoletimCadastroHelper
							.getNumeroIptu().intValue());
		}

		boletimCadastroTxt.append(Util.completaString(numeroIptu, 26));

		// Número Contrato CELPE
		String numeroCelpe = "";
		if (emitirDocumentoCobrancaBoletimCadastroHelper.getNumeroCelpe() != null) {
			numeroCelpe = Util.adicionarZerosEsquedaNumero(10,
					emitirDocumentoCobrancaBoletimCadastroHelper
							.getNumeroCelpe().toString());
		}
		boletimCadastroTxt.append(Util.completaString(numeroCelpe, 10));

		// Codigo Rota
		String codigoRota = "";
		if (emitirDocumentoCobrancaBoletimCadastroHelper.getCodigoRota() != null) {
			codigoRota = Util.adicionarZerosEsquedaNumero(7,
					emitirDocumentoCobrancaBoletimCadastroHelper
							.getCodigoRota().toString());
		}
		boletimCadastroTxt.append(Util.completaString(codigoRota, 7));

		// Sequencial da Rota
		String sequencialRota = "";
		if (emitirDocumentoCobrancaBoletimCadastroHelper
				.getNumeroSequencialRota() != null) {
			sequencialRota = Util.adicionarZerosEsquedaNumero(8,
					emitirDocumentoCobrancaBoletimCadastroHelper
							.getNumeroSequencialRota().toString());
		}
		boletimCadastroTxt.append(Util.completaString(sequencialRota, 8));

		// Valor Debitos
		SistemaParametro sistemaParametro = this.getControladorUtil()
				.pesquisarParametrosDoSistema();

		Integer anoMesReferenciaFinal = sistemaParametro.getAnoMesFaturamento();

		int anoMesSubtraido = Util
				.subtrairMesDoAnoMes(anoMesReferenciaFinal, 1);

		Integer dataVencimentoFinalInteger = sistemaParametro
				.getAnoMesArrecadacao();
		String anoMesSubtraidoString = ""
				+ Util.subtrairMesDoAnoMes(dataVencimentoFinalInteger, 1);

		int ano = Integer.parseInt(anoMesSubtraidoString.substring(0, 4));
		int mes = Integer.parseInt(anoMesSubtraidoString.substring(4, 6));

		// recupera o ultimo dia do anomes e passa a data como parametro
		Calendar dataVencimentoFinal = GregorianCalendar.getInstance();
		dataVencimentoFinal.set(Calendar.YEAR, ano);
		dataVencimentoFinal.set(Calendar.MONTH, (mes - 1));
		dataVencimentoFinal.set(Calendar.DAY_OF_MONTH, dataVencimentoFinal
				.getActualMaximum(Calendar.DAY_OF_MONTH));

		Date dataFinalDate = dataVencimentoFinal.getTime();

		// converte String em data
		Date dataVencimento = Util.converteStringParaDate("01/01/1900");

		ObterDebitoImovelOuClienteHelper debitoImovelClienteHelper = this
				.getControladorCobranca().obterDebitoImovelOuCliente(
						1,
						""
								+ emitirDocumentoCobrancaBoletimCadastroHelper
										.getIdImovel(), null, null, "190001",
						"" + anoMesSubtraido, dataVencimento, dataFinalDate, 1,
						1, 1, 1, 1, 1, 1, null);

		BigDecimal valorTotal = this
				.calcularValorTotalDebitoBoletimCadastro(debitoImovelClienteHelper);

		boletimCadastroTxt.append(Util.completaString(Util.formataBigDecimal(
				valorTotal, 2, true), 11));

		// Descrição Abreviada da Principal Categoria do imovel
		Categoria categoria = this.getControladorImovel()
				.obterPrincipalCategoriaImovel(
						emitirDocumentoCobrancaBoletimCadastroHelper
								.getIdImovel());

		String descricaoAbreviadaPrincipalCategoria = "";
		if (categoria != null) {
			descricaoAbreviadaPrincipalCategoria = categoria
					.getDescricaoAbreviada();
		}
		boletimCadastroTxt.append(Util.completaString(
				descricaoAbreviadaPrincipalCategoria, 3));

		// **********************************************************************
		// Alterado por: Ivan Sergio
		// data: 12/05/2009
		// CRC1818
		// Alteracao: Adicionar os campos Nome do Bairro e Municipio do
		// Proprietario e Imovel.
		// **********************************************************************
		Integer idMunicipio = null;
		Integer codigoBairro = null;

		String nomeBairroProprietario = "";
		String nomeMunicipioProprietario = "";

		if (clienteEmitirBoletimCadastroHelperProprietario != null) {
			if (clienteEmitirBoletimCadastroHelperProprietario
					.getClienteEndereco().getLogradouroBairro() != null
					&& clienteEmitirBoletimCadastroHelperProprietario
							.getClienteEndereco().getLogradouroBairro()
							.getBairro() != null
					&& clienteEmitirBoletimCadastroHelperProprietario
							.getClienteEndereco().getLogradouroBairro()
							.getBairro().getMunicipio() != null) {

				codigoBairro = clienteEmitirBoletimCadastroHelperProprietario
						.getClienteEndereco().getLogradouroBairro().getBairro()
						.getCodigo();

				idMunicipio = clienteEmitirBoletimCadastroHelperProprietario
						.getClienteEndereco().getLogradouroBairro().getBairro()
						.getMunicipio().getId();

				FiltroBairro filtroBairro = new FiltroBairro();
				filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.MUNICIPIO_ID, idMunicipio));
				filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.CODIGO, codigoBairro));
				filtroBairro
						.adicionarCaminhoParaCarregamentoEntidade("municipio");

				Collection colecaoBairro = getControladorUtil().pesquisar(
						filtroBairro, Bairro.class.getName());

				if (colecaoBairro != null && !colecaoBairro.isEmpty()) {
					Bairro dadosBairro = (Bairro) Util
							.retonarObjetoDeColecao(colecaoBairro);

					nomeBairroProprietario = dadosBairro.getNome();
					nomeMunicipioProprietario = dadosBairro.getMunicipio()
							.getNome();
				}
			}
		}

		boletimCadastroTxt.append(Util.completaString(nomeBairroProprietario,
				30));
		boletimCadastroTxt.append(Util.completaString(
				nomeMunicipioProprietario, 30));

		Imovel imovel = getControladorEndereco().pesquisarImovelParaEndereco(
				emitirDocumentoCobrancaBoletimCadastroHelper.getIdImovel());

		// Nome do Bairro do Imovel
		String nomeBairroImovel = "";

		if (imovel.getLogradouroBairro() != null
				&& imovel.getLogradouroBairro().getBairro() != null) {

			nomeBairroImovel = imovel.getLogradouroBairro().getBairro()
					.getNome();
		}
		boletimCadastroTxt.append(Util.completaString(nomeBairroImovel, 30));

		// Nome do Municipio do Imovel
		String nomeMunicipioImovel = "";

		if (imovel.getLogradouroBairro().getBairro() != null
				&& imovel.getLogradouroBairro().getBairro().getMunicipio() != null) {

			nomeMunicipioImovel = imovel.getLogradouroBairro().getBairro()
					.getMunicipio().getNome();
		}
		boletimCadastroTxt.append(Util.completaString(nomeMunicipioImovel, 30));

		// Numero do Hidrometro
		boletimCadastroTxt.append(Util.completaString(numeroHidrometro, 10));
		// **********************************************************************

		String codigosSubcategorias = "";

		if (colecaoSubcategorias != null && !colecaoSubcategorias.isEmpty()) {

			Iterator colecaoSubcategoriasIterator = colecaoSubcategorias
					.iterator();

			for (int i = 0; i < 6; i++) {

				if (colecaoSubcategoriasIterator.hasNext()) {

					Subcategoria subcategoria = (Subcategoria) colecaoSubcategoriasIterator
							.next();

					codigosSubcategorias = codigosSubcategorias
							+ Util.adicionarZerosEsquedaNumero(3, subcategoria
									.getCodigo()
									+ "");
				} else {
					break;
				}
			}
		}

		boletimCadastroTxt
				.append(Util.completaString(codigosSubcategorias, 18));

		if (clienteEmitirBoletimCadastroHelperProprietario != null
				&& clienteEmitirBoletimCadastroHelperProprietario.getTarifaSocialDadoEconomia() != null) {
			// Tipo de Renda
			String tipoRenda = "";

			if (clienteEmitirBoletimCadastroHelperProprietario.getTarifaSocialDadoEconomia().getRendaTipo() != null) {
				tipoRenda = clienteEmitirBoletimCadastroHelperProprietario.getTarifaSocialDadoEconomia()
						.getRendaTipo().getDescricao();
			}

			boletimCadastroTxt.append(Util.completaString(tipoRenda, 20));

			// Valor Renda
			String valorRenda = "";

			if (clienteEmitirBoletimCadastroHelperProprietario.getTarifaSocialDadoEconomia().getValorRendaFamiliar() != null) {
				valorRenda = Util.formataBigDecimal(
						clienteEmitirBoletimCadastroHelperProprietario.getTarifaSocialDadoEconomia().getValorRendaFamiliar(),
						2, true);
			}

			boletimCadastroTxt.append(Util.completaString(valorRenda, 10));

			// Tipo de Cartão
			String tipoCartao = "";

			if (clienteEmitirBoletimCadastroHelperProprietario.getTarifaSocialDadoEconomia().getTarifaSocialCartaoTipo() != null) {
				tipoCartao = clienteEmitirBoletimCadastroHelperProprietario.getTarifaSocialDadoEconomia()
						.getTarifaSocialCartaoTipo().getDescricao();
			}

			boletimCadastroTxt.append(Util.completaString(tipoCartao, 20));

			// Número Cartão
			String nomeroCartao = "";

			if (clienteEmitirBoletimCadastroHelperProprietario.getTarifaSocialDadoEconomia().getNumeroCartaoProgramaSocial() != null) {
				nomeroCartao = clienteEmitirBoletimCadastroHelperProprietario.getTarifaSocialDadoEconomia()
						.getNumeroCartaoProgramaSocial().toString();
			}

			boletimCadastroTxt.append(Util.completaString(nomeroCartao, 11));
		} else {
			boletimCadastroTxt.append(Util.completaString("", 58));
		}

	}

	/**
	 * Este caso de uso permite a emissão de boletins de cadastro
	 *
	 * [UC0582] Emitir Boletim de Cadastro
	 *
	 * @author Rafael Pinto
	 * @data 15/01/2008
	 *
	 * @param ObterDebitoImovelOuClienteHelper
	 * @return BigDecimal valorTotalDebito
	 */
	private BigDecimal calcularValorTotalDebitoBoletimCadastro(
			ObterDebitoImovelOuClienteHelper debitoImovelClienteHelper) {

		BigDecimal valorTotal = BigDecimal.ZERO;

		if (debitoImovelClienteHelper != null) {

			BigDecimal valorConta = BigDecimal.ZERO;
			BigDecimal valorDebitoACobrar = BigDecimal.ZERO;
			BigDecimal valorGuiaPagamento = BigDecimal.ZERO;
			BigDecimal valorCreditoARealizar = BigDecimal.ZERO;

			ContaValoresHelper dadosConta = null;
			DebitoACobrar dadosDebito = null;
			CreditoARealizar dadosCredito = null;
			GuiaPagamentoValoresHelper dadosGuiaPagamentoValoresHelper = null;

			Collection<ContaValoresHelper> colecaoContaValores = debitoImovelClienteHelper
					.getColecaoContasValores();
			if (colecaoContaValores != null && !colecaoContaValores.isEmpty()) {

				Iterator<ContaValoresHelper> colecaoContaValoresIterator = colecaoContaValores
						.iterator();

				// percorre a colecao de conta somando o valor para obter um
				// valor total
				while (colecaoContaValoresIterator.hasNext()) {

					dadosConta = (ContaValoresHelper) colecaoContaValoresIterator
							.next();
					valorConta = valorConta.add(dadosConta.getConta()
							.getValorTotal());
				}
			}

			Collection<DebitoACobrar> colecaoDebitoACobrar = debitoImovelClienteHelper
					.getColecaoDebitoACobrar();

			if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
				Iterator<DebitoACobrar> colecaoDebitoACobrarIterator = colecaoDebitoACobrar
						.iterator();

				// percorre a colecao de debito a cobrar somando o valor para
				// obter um valor total
				while (colecaoDebitoACobrarIterator.hasNext()) {

					dadosDebito = (DebitoACobrar) colecaoDebitoACobrarIterator
							.next();
					// alterado por Vivianne Sousa data:11/04/2008
					// analista :Adriano
					valorDebitoACobrar = valorDebitoACobrar.add(dadosDebito
							.getValorTotalComBonus());
				}
			}

			Collection<CreditoARealizar> colecaoCreditoARealizar = debitoImovelClienteHelper
					.getColecaoCreditoARealizar();

			if (colecaoCreditoARealizar != null
					&& !colecaoCreditoARealizar.isEmpty()) {

				Iterator<CreditoARealizar> colecaoCreditoARealizarIterator = colecaoCreditoARealizar
						.iterator();

				// percorre a colecao de credito a realizar somando o valor para
				// obter um valor total
				while (colecaoCreditoARealizarIterator.hasNext()) {

					dadosCredito = (CreditoARealizar) colecaoCreditoARealizarIterator
							.next();
					// alterado por Vivianne Sousa data:11/04/2008
					// analista :Adriano
					valorCreditoARealizar = valorCreditoARealizar
							.add(dadosCredito.getValorTotalComBonus());

				}
			}

			Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores = debitoImovelClienteHelper
					.getColecaoGuiasPagamentoValores();

			if (colecaoGuiaPagamentoValores != null
					&& !colecaoGuiaPagamentoValores.isEmpty()) {
				Iterator<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValoresHelperIterator = colecaoGuiaPagamentoValores
						.iterator();

				// percorre a colecao de guia de pagamento somando o valor para
				// obter um valor total
				while (colecaoGuiaPagamentoValoresHelperIterator.hasNext()) {

					dadosGuiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) colecaoGuiaPagamentoValoresHelperIterator
							.next();

					valorGuiaPagamento = valorGuiaPagamento
							.add(dadosGuiaPagamentoValoresHelper
									.getGuiaPagamento().getValorDebito());
				}
			}

			valorTotal = valorConta.add(valorDebitoACobrar);
			valorTotal = valorTotal.add(valorGuiaPagamento);
			valorTotal = valorTotal.subtract(valorCreditoARealizar);

		}// fim do if debitoImovelClienteHelper != null

		return valorTotal;
	}

	public void adicionarDadosClienteEmitirBoletimCadastroTxt(
			StringBuilder boletimCadastroTxt,
			ClienteEmitirBoletimCadastroHelper clienteEmitirBoletimCadastroHelper) {

		// Dados do Cliente

		// Id do Cliente
		String idClienteFormatado = Util.adicionarZerosEsquedaNumero(9,
				clienteEmitirBoletimCadastroHelper.getCliente().getId()
						.toString());

		boletimCadastroTxt.append(idClienteFormatado);

		// Nome do Cliente
		String nomeCliente = "";

		if (clienteEmitirBoletimCadastroHelper.getCliente().getNome() != null) {
			nomeCliente = clienteEmitirBoletimCadastroHelper.getCliente()
					.getNome();
		}

		boletimCadastroTxt.append(Util.completaString(nomeCliente, 23));

		// Tipo do Cliente
		String tipoCliente = "";

		if (clienteEmitirBoletimCadastroHelper.getCliente().getClienteTipo() != null) {
			tipoCliente = Util.adicionarZerosEsquedaNumero(2,
					clienteEmitirBoletimCadastroHelper.getCliente()
							.getClienteTipo().getId().toString());
		}

		boletimCadastroTxt.append(Util.completaString(tipoCliente, 2));

		// CPF/CNPJ
		String cpfCnpj = "";

		if (clienteEmitirBoletimCadastroHelper.getCliente().getCpf() != null) {
			cpfCnpj = Util.adicionarZerosEsquedaNumero(14,
					clienteEmitirBoletimCadastroHelper.getCliente().getCpf());
		}

		if (clienteEmitirBoletimCadastroHelper.getCliente().getCnpj() != null) {
			cpfCnpj = Util.adicionarZerosEsquedaNumero(14,
					clienteEmitirBoletimCadastroHelper.getCliente().getCnpj());
		}

		boletimCadastroTxt.append(Util.completaString(cpfCnpj, 14));

		// RG
		String rg = "";

		if (clienteEmitirBoletimCadastroHelper.getCliente().getRg() != null) {
			rg = Util.adicionarZerosEsquedaNumero(13,
					clienteEmitirBoletimCadastroHelper.getCliente().getRg());
		}

		boletimCadastroTxt.append(Util.completaString(rg, 13));

		// Data de Emissão RG
		String dataEmissaoRG = "";

		if (clienteEmitirBoletimCadastroHelper.getCliente().getDataEmissaoRg() != null) {
			dataEmissaoRG = Util.formatarData(
					clienteEmitirBoletimCadastroHelper.getCliente()
							.getDataEmissaoRg()).replace("/", "");
		}

		boletimCadastroTxt.append(Util.completaString(dataEmissaoRG, 8));

		// Órgão Expedidor RG
		String orgaoExpedidorRG = "";

		if (clienteEmitirBoletimCadastroHelper.getCliente()
				.getOrgaoExpedidorRg() != null) {
			orgaoExpedidorRG = clienteEmitirBoletimCadastroHelper.getCliente()
					.getOrgaoExpedidorRg().getDescricaoAbreviada();
		}

		boletimCadastroTxt.append(Util.completaString(orgaoExpedidorRG, 4));

		// Unidade Federação
		String unidadeFederacao = "";

		if (clienteEmitirBoletimCadastroHelper.getCliente()
				.getUnidadeFederacao() != null) {

			unidadeFederacao = clienteEmitirBoletimCadastroHelper.getCliente()
					.getUnidadeFederacao().getSigla();
		}

		boletimCadastroTxt.append(Util.completaString(unidadeFederacao, 2));

		// Data de Nascimento
		String dataNascimento = "";

		if (clienteEmitirBoletimCadastroHelper.getCliente().getDataNascimento() != null) {
			dataNascimento = Util.formatarData(
					clienteEmitirBoletimCadastroHelper.getCliente()
							.getDataNascimento()).replace("/", "");
		}

		boletimCadastroTxt.append(Util.completaString(dataNascimento, 8));

		// Profissão
		String profissao = "";

		if (clienteEmitirBoletimCadastroHelper.getCliente().getProfissao() != null) {
			profissao = clienteEmitirBoletimCadastroHelper.getCliente()
					.getProfissao().getDescricao();
		}

		boletimCadastroTxt.append(Util.completaString(profissao, 18));

		// Pessoa Sexo
		String sexo = "";

		if (clienteEmitirBoletimCadastroHelper.getCliente().getPessoaSexo() != null) {
			sexo = clienteEmitirBoletimCadastroHelper.getCliente()
					.getPessoaSexo().getId().toString();
		}

		boletimCadastroTxt.append(Util.completaString(sexo, 1));

		// Nome da Mãe
		String nomeMae = "";

		if (clienteEmitirBoletimCadastroHelper.getCliente().getNomeMae() != null) {
			nomeMae = clienteEmitirBoletimCadastroHelper.getCliente()
					.getNomeMae();
		}

		boletimCadastroTxt.append(Util.completaString(nomeMae, 32));

		// Indicador de Uso
		String indicadorUso = "";

		if (clienteEmitirBoletimCadastroHelper.getCliente().getIndicadorUso() != null) {
			indicadorUso = clienteEmitirBoletimCadastroHelper.getCliente()
					.getIndicadorUso().toString();
		}

		boletimCadastroTxt.append(Util.completaString(indicadorUso, 1));

		// Dados do Endereço do Cliente

		// Tipo de Endereço
		String tipoEndereco = "";

		if (clienteEmitirBoletimCadastroHelper.getClienteEndereco()
				.getEnderecoTipo() != null) {
			tipoEndereco = clienteEmitirBoletimCadastroHelper
					.getClienteEndereco().getEnderecoTipo().getId().toString();
		}

		boletimCadastroTxt.append(Util.completaString(tipoEndereco, 1));

		// Logradouro
		String logradouro = "";

		if (clienteEmitirBoletimCadastroHelper.getClienteEndereco()
				.getLogradouroCep() != null
				&& clienteEmitirBoletimCadastroHelper.getClienteEndereco()
						.getLogradouroCep().getLogradouro() != null) {

			logradouro = Util.adicionarZerosEsquedaNumero(9,
					clienteEmitirBoletimCadastroHelper.getClienteEndereco()
							.getLogradouroCep().getLogradouro().getId()
							.toString());
		}

		boletimCadastroTxt.append(Util.completaString(logradouro, 9));

		// Endereço Abreviado
		String endereco = "";

		if (clienteEmitirBoletimCadastroHelper.getEnderecoFormatado() != null) {
			endereco = clienteEmitirBoletimCadastroHelper
					.getEnderecoFormatado();
		}

		boletimCadastroTxt.append(Util.completaString(endereco, 60));

		// CEP
		String cep = "";

		if (clienteEmitirBoletimCadastroHelper.getClienteEndereco()
				.getLogradouroCep() != null
				&& clienteEmitirBoletimCadastroHelper.getClienteEndereco()
						.getLogradouroCep().getCep() != null) {

			cep = Util
					.adicionarZerosEsquedaNumero(8,
							clienteEmitirBoletimCadastroHelper
									.getClienteEndereco().getLogradouroCep()
									.getCep().getCodigo().toString());
		}

		boletimCadastroTxt.append(Util.completaString(cep, 8));

		// Bairro
		String bairro = "";

		if (clienteEmitirBoletimCadastroHelper.getClienteEndereco()
				.getLogradouroBairro() != null
				&& clienteEmitirBoletimCadastroHelper.getClienteEndereco()
						.getLogradouroBairro().getBairro() != null) {

			bairro = Util.adicionarZerosEsquedaNumero(3, ""
					+ clienteEmitirBoletimCadastroHelper.getClienteEndereco()
							.getLogradouroBairro().getBairro().getCodigo());
		}

		boletimCadastroTxt.append(Util.completaString(bairro, 3));

		// Referência
		String referencia = "";

		if (clienteEmitirBoletimCadastroHelper.getClienteEndereco()
				.getEnderecoReferencia() != null) {
			referencia = clienteEmitirBoletimCadastroHelper
					.getClienteEndereco().getEnderecoReferencia().getId()
					.toString();
		}

		boletimCadastroTxt.append(Util.completaString(referencia, 1));

		// Número do Imóvel
		String numeroImovel = "";

		if (clienteEmitirBoletimCadastroHelper.getClienteEndereco().getNumero() != null) {
			numeroImovel = Util.adicionarZerosEsquedaNumero(5,
					clienteEmitirBoletimCadastroHelper.getClienteEndereco()
							.getNumero().toString());
		}

		boletimCadastroTxt.append(Util.completaString(numeroImovel, 5));

		// Complemento
		String complemento = "";

		if (clienteEmitirBoletimCadastroHelper.getClienteEndereco()
				.getComplemento() != null) {
			complemento = clienteEmitirBoletimCadastroHelper
					.getClienteEndereco().getComplemento();
		}

		boletimCadastroTxt.append(Util.completaString(complemento, 19));

		// Dados do Telefone do Cliente
		// Tipo do Telefone
		Collection clientesFone = clienteEmitirBoletimCadastroHelper
				.getClientesFone();

		if (clientesFone != null && !clientesFone.isEmpty()) {

			Iterator clientesFoneIterator = clientesFone.iterator();

			int tamanho = clientesFone.size();

			while (clientesFoneIterator.hasNext()) {

				ClienteFone clienteFone = (ClienteFone) clientesFoneIterator
						.next();

				String tipoTelefone = "";

				if (clienteFone.getFoneTipo() != null) {
					tipoTelefone = clienteFone.getFoneTipo().getId().toString();
				}

				boletimCadastroTxt.append(Util.completaString(tipoTelefone, 1));

				// DDD
				String ddd = "";

				if (clienteFone.getDdd() != null) {
					ddd = Util.adicionarZerosEsquedaNumero(2, clienteFone
							.getDdd());
				}

				boletimCadastroTxt.append(Util.completaString(ddd, 2));

				// Número do Telefone
				String numeroTelefone = "";

				if (clienteFone.getTelefone() != null) {
					numeroTelefone = clienteFone.getTelefone();
				}

				boletimCadastroTxt.append(Util
						.completaString(numeroTelefone, 8));

				// Ramal
				String ramal = "";

				if (clienteFone.getRamal() != null) {
					ramal = clienteFone.getRamal();
				}

				boletimCadastroTxt.append(Util.completaString(ramal, 4));

			}

			if (tamanho == 1) {
				boletimCadastroTxt.append(Util.completaString("", 15));
			}

		} else {
			boletimCadastroTxt.append(Util.completaString("", 30));
		}

		boletimCadastroTxt.append(Util.completaString("", 8));
	}

	/**
	 * Permite inserir uma Anormalidade de Leitura
	 *
	 * [UC0217] Inserir Anormalidade Leitura
	 *
	 * @author Thiago Tenório
	 * @date 30/03/2006
	 *
	 */
	public Integer inserirClienteTipo(ClienteTipo clienteTipo,
			Usuario usuarioLogado) throws ControladorException {

		// ------------ REGISTRAR TRANSAÇÃO----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_CLIENTE_TIPO_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_CLIENTE_TIPO_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		clienteTipo.setOperacaoEfetuada(operacaoEfetuada);
		clienteTipo.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(clienteTipo);
		// ------------ REGISTRAR TRANSAÇÃO----------------------------

		Integer id = (Integer) getControladorUtil().inserir(clienteTipo);

		return id;

	}

	/**
	 * [UC0298] Manter Agência bancária [] Atualizar Agência Bancária Metodo que
	 * atualiza a Agência Bancária
	 *
	 *
	 * @author Thiago Tenório
	 * @date 25/05/2006
	 *
	 *
	 * @throws ControladorException
	 */

	public void atualizarClienteTipo(ClienteTipo clienteTipo)
			throws ControladorException {

		// Verifica se todos os campos obrigatorios foram preenchidos

		if ((clienteTipo.getId() == null || clienteTipo.getId().equals(
				"" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (clienteTipo.getDescricao() == null || clienteTipo
						.getDescricao().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (clienteTipo.getEsferaPoder() == null || clienteTipo
						.getEsferaPoder().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (clienteTipo.getIndicadorPessoaFisicaJuridica() == null || clienteTipo
						.getIndicadorPessoaFisicaJuridica().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			throw new ControladorException(
					"atencao.filtro.nenhum_parametro_informado");

		}

		// Verifica se o campo Descrição foi preenchido

		if (clienteTipo.getDescricao() == null
				|| clienteTipo.getDescricao().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					" Descrição");
		}

		// Verifica se o campo Esfera Poder foi preenchido
		if (clienteTipo.getEsferaPoder() == null
				|| clienteTipo.getEsferaPoder().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					" Esfera Poder");
		}

		// Verifica se o campo Referência do Tipo de Serviço foi preenchido
		if (clienteTipo.getIndicadorPessoaFisicaJuridica() == null
				|| clienteTipo.getIndicadorPessoaFisicaJuridica().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null,
					" Tipo de Pessoa");
		}

		// [FS0003] - Atualização realizada por outro usuário
		FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();
		filtroClienteTipo.adicionarParametro(new ParametroSimples(
				FiltroClienteTipo.ID, clienteTipo.getId()));

		Collection colecaoClienteTipoBase = getControladorUtil().pesquisar(
				filtroClienteTipo, ClienteTipo.class.getName());

		if (colecaoClienteTipoBase == null || colecaoClienteTipoBase.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		ClienteTipo clienteTipoBase = (ClienteTipo) colecaoClienteTipoBase
				.iterator().next();

		if (clienteTipoBase.getUltimaAlteracao().after(
				clienteTipo.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		clienteTipo.setUltimaAlteracao(new Date());

		getControladorUtil().atualizar(clienteTipo);

	}

	/**
	 * Este caso de uso permite a emissão de boletins de cadastro
	 *
	 * [UC0582] Emitir Boletim de Cadastro pelo Filtro Imóvel por Outros
	 * Critérios
	 *
	 * Alterado por: Ivan Sergio Data: 26/01/2009
	 *
	 * @alteracao 26/01/2009 - CRC1076 - Alterado o nome do arquivo gerado.
	 *
	 * @param
	 * @return void
	 */
	public byte[] emitirBoletimCadastro(String idImovelCondominio,
			String idImovelPrincipal, String idFaturasAtraso,
			String consumoMinimoInicialAgua, String consumoMinimoFinalAgua,
			String idSituacaoLigacaoEsgoto, String consumoMinimoInicialEsgoto,
			String consumoMinimoFinalEsgoto,
			String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,
			String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil,
			String idPocoTipo, String idFaturamentoSituacaoTipo,
			String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,
			String idEloAnormalidade, String areaConstruidaInicial,
			String areaConstruidaFinal, String idCadastroOcorrencia,
			String idConsumoTarifa, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String setorComercialInicial, String setorComercialFinal,
			String quadraInicial, String quadraFinal, String loteOrigem,
			String loteDestno, String cep, String logradouro, String bairro,
			String municipio, String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo,
			String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial,
			String numeroMoradoresFinal, String idAreaConstruidaFaixa,
			String idUnidadeNegocio, String indicadorCpfCnpj, String cpfCnpj) throws ControladorException {

		System.out.println("********************");
		System.out.println("INICIO BOLETIM CADASTRO");
		System.out.println("********************");

		boolean flagFimPesquisa = false;
		final int quantidadeCobrancaDocumento = 500;
		int quantidadeCobrancaDocumentoInicio = 0;
		StringBuilder boletimCadastroTxt = new StringBuilder();
		int sequencialImpressao = 0;
		int pagina = 0;

		while (!flagFimPesquisa) {

			pagina++;

			Collection colecaoEmitirBoletimCadastro = null;
			try {

				colecaoEmitirBoletimCadastro = repositorioImovel
						.pesquisarBoletimCadastro(idImovelCondominio,
								idImovelPrincipal, idFaturasAtraso,
								consumoMinimoInicialAgua,
								consumoMinimoFinalAgua,
								idSituacaoLigacaoEsgoto,
								consumoMinimoInicialEsgoto,
								consumoMinimoFinalEsgoto,
								intervaloValorPercentualEsgotoInicial,
								intervaloValorPercentualEsgotoFinal,
								intervaloMediaMinimaImovelInicial,
								intervaloMediaMinimaImovelFinal,
								intervaloMediaMinimaHidrometroInicial,
								intervaloMediaMinimaHidrometroFinal,
								idImovelPerfil, idPocoTipo,
								idFaturamentoSituacaoTipo,
								idCobrancaSituacaoTipo,
								idSituacaoEspecialCobranca, idEloAnormalidade,
								areaConstruidaInicial, areaConstruidaFinal,
								idCadastroOcorrencia, idConsumoTarifa,
								idGerenciaRegional, idLocalidadeInicial,
								idLocalidadeFinal, setorComercialInicial,
								setorComercialFinal, quadraInicial,
								quadraFinal, loteOrigem, loteDestno, cep,
								logradouro, bairro, municipio, idTipoMedicao,
								indicadorMedicao, idSubCategoria, idCategoria,
								quantidadeEconomiasInicial,
								quantidadeEconomiasFinal, diaVencimento,
								idCliente, idClienteTipo, idClienteRelacaoTipo,
								numeroPontosInicial, numeroPontosFinal,
								numeroMoradoresInicial, numeroMoradoresFinal,
								idAreaConstruidaFaixa, idUnidadeNegocio,
								quantidadeCobrancaDocumentoInicio, indicadorCpfCnpj, cpfCnpj);

			} catch (ErroRepositorioException ex) {
				ex.printStackTrace();
				throw new ControladorException("erro.sistema", ex);
			}

			if (colecaoEmitirBoletimCadastro != null
					&& !colecaoEmitirBoletimCadastro.isEmpty()) {

				if (colecaoEmitirBoletimCadastro.size() < quantidadeCobrancaDocumento) {
					flagFimPesquisa = true;
				} else {
					quantidadeCobrancaDocumentoInicio = quantidadeCobrancaDocumentoInicio + 500;
				}

				Iterator colecaoEmitirBoletimCadastroIterator = colecaoEmitirBoletimCadastro
						.iterator();
				int count = 0;

				EmitirDocumentoCobrancaBoletimCadastroHelper emitirDocumentoCobrancaBoletimCadastroHelper = null;
				while (colecaoEmitirBoletimCadastroIterator.hasNext()) {

					emitirDocumentoCobrancaBoletimCadastroHelper = (EmitirDocumentoCobrancaBoletimCadastroHelper) colecaoEmitirBoletimCadastroIterator
							.next();

					count++;

					System.out.println("VEZ QUE ENTRA:"
							+ pagina
							+ " / "
							+ count
							+ " / IMÓVEL:"
							+ emitirDocumentoCobrancaBoletimCadastroHelper
									.getIdImovel().toString());

					sequencialImpressao++;

					if (emitirDocumentoCobrancaBoletimCadastroHelper != null) {
						criarDadosTxtBoletimCadastro(boletimCadastroTxt,
								emitirDocumentoCobrancaBoletimCadastroHelper);
					}

					emitirDocumentoCobrancaBoletimCadastroHelper = null;

					boletimCadastroTxt.append(System
							.getProperty("line.separator"));

				}
			} else {
				flagFimPesquisa = true;
			}
		}

		System.out.println("********************");
		System.out.println("FIM BOLETIM CADASTRO");
		System.out.println("********************");

		Date dataAtual = new Date();

		String nomeZip = null;
		/*
		 * String tituloComandoEventual = null; if (comandoAtividadeAcaoCobranca !=
		 * null && comandoAtividadeAcaoCobranca.getId() != null) {
		 * tituloComandoEventual = comandoAtividadeAcaoCobranca
		 * .getDescricaoTitulo(); nomeZip = "BOL_CAD " + tituloComandoEventual + " " +
		 * Util.formatarDataComHora(dataAtual); }else {
		 */
		nomeZip = "BOLETIM_CADASTRAL " + Util.formatarDataComHora(dataAtual);
		// }

		nomeZip = nomeZip.replace("/", "_");
		nomeZip = nomeZip.replace(" ", "_");
		nomeZip = nomeZip.replace(":", "_");

		nomeZip = nomeZip.replace("/", "_");

		byte[] retornoArray = null;

		try {
			if (boletimCadastroTxt != null) {

				boletimCadastroTxt.append("\u0004");
				// criar o arquivo zip
				File compactado = File.createTempFile("zipHtml" + nomeZip,
						".zip");
				ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
						compactado));

				File leitura = new File(nomeZip + ".txt");
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(leitura.getAbsolutePath())));
				out.write(boletimCadastroTxt.toString());
				out.flush();
				out.close();
				ZipUtil.adicionarArquivo(zos, leitura);

				// close the stream
				zos.close();

				ByteArrayOutputStream retorno = new ByteArrayOutputStream();

				FileInputStream inputStream = new FileInputStream(compactado);

				int INPUT_BUFFER_SIZE = 1024;
				byte[] temp = new byte[INPUT_BUFFER_SIZE];
				int numBytesRead = 0;

				while ((numBytesRead = inputStream.read(temp, 0,
						INPUT_BUFFER_SIZE)) != -1) {
					retorno.write(temp, 0, numBytesRead);
				}

				inputStream.close();

				leitura.delete();

				// retorno.flush();
				// retorno.close();

				retornoArray = retorno.toByteArray();

			}

			System.out.println("********************");
			System.out.println("FIM GERAÇÃO ARQUIVO");
			System.out.println("********************");

		} catch (IOException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

		return retornoArray;

	}

	/**
	 *
	 * Pesquisa os imóveis do cliente de acordo com o tipo de relação
	 *
	 *
	 *
	 * [UC0251] Gerar Atividade de Ação de Cobrança [SB0001] Gerar Atividade de
	 *
	 * Ação de Cobrança para os Imóveis do Cliente
	 *
	 *
	 *
	 * @author Sávio Luiz
	 *
	 * @created 23/11/2007
	 *
	 *
	 *
	 * @param cliente
	 *
	 * @param relacaoClienteImovel
	 *
	 * @return
	 *
	 * @throws ErroRepositorioException
	 *
	 */

	public Collection pesquisarClientesSubordinados(Integer idCliente)

	throws ControladorException {
		try {
			// chama o metódo de pesquisar do repositório
			return repositorioCadastro.pesquisarClientesSubordinados(idCliente);

			// erro no hibernate
		} catch (ErroRepositorioException ex) {

			// levanta a exceção para a próxima camada
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 *
	 * [UC0624] Gerar Relatório para Atualização Cadastral
	 *
	 * @author Flávio Cordeiro
	 */
	public Collection pesquisarDadosRelatorioAtualizacaoCadastral(
			int anoMesFaturamento, Integer idFaturamentoGrupo,
			int indicadorLocalidadeInformatizada, Collection idLocalidades,
			Collection idSetores, Collection idQuadras, String rotaInicial,
			String rotaFinal, String sequencialRotaInicial,
			String sequencialRotaFinal, String tipoRelatorio,
			Usuario usuarioLogado) throws ControladorException {

		Collection retorno = new ArrayList();
		try {
			Collection colecaoObjeto = repositorioCadastro
					.pesquisarRelatorioAtualizacaoCadastral(idLocalidades,
							idSetores, idQuadras, rotaInicial, rotaFinal,
							sequencialRotaInicial, sequencialRotaFinal);
			if (colecaoObjeto != null && !colecaoObjeto.isEmpty()) {
				Iterator iterator = colecaoObjeto.iterator();

				while (iterator.hasNext()) {
					RelatorioAtualizacaoCadastralHelper relatorioAtualizacaoCadastralHelper = new RelatorioAtualizacaoCadastralHelper();
					Object[] objeto = (Object[]) iterator.next();
					// idImovel
					if (objeto[0] != null) {
						relatorioAtualizacaoCadastralHelper
								.setIdImovel((Integer) objeto[0]);
						relatorioAtualizacaoCadastralHelper
								.setInscricao(getControladorImovel()
										.pesquisarInscricaoImovel(
												relatorioAtualizacaoCadastralHelper
														.getIdImovel()));
					}
					// matricula Imovel
					if (objeto[1] != null) {
						relatorioAtualizacaoCadastralHelper
								.setIdImovel((Integer) objeto[1]);
					}
					// nome cliente
					if (objeto[2] != null) {
						relatorioAtualizacaoCadastralHelper
								.setNomeCliente((String) objeto[2]);
					}
					// localidade id
					if (objeto[3] != null) {
						relatorioAtualizacaoCadastralHelper
								.setIdLocalidade((Integer) objeto[3]);
					}
					// localidade descricao
					if (objeto[4] != null) {
						relatorioAtualizacaoCadastralHelper
								.setLocalidadeDescricao((String) objeto[4]);
					}
					// setor comercial codigo
					if (objeto[5] != null) {
						relatorioAtualizacaoCadastralHelper
								.setCodigoSetorComercial((Integer) objeto[5]);
					}
					// setor comercial descricao
					if (objeto[6] != null) {
						relatorioAtualizacaoCadastralHelper
								.setSetorComercialDescricao((String) objeto[6]);
					}
					// unidade negocio nome
					if (objeto[7] != null) {
						relatorioAtualizacaoCadastralHelper
								.setUnidadeNegocioDescricao((String) objeto[7]);
					}
					// rota codigo
					if (objeto[8] != null) {
						String rota = (String) objeto[8];
						// imovel numero sequencial rota
						if (objeto[9] != null) {
							rota = rota + "." + (String) objeto[9];
						}
						relatorioAtualizacaoCadastralHelper
								.setRotaSequencialRota(rota);
					}

					// imovel indicador exclusao
					if (objeto[10] != null) {
						relatorioAtualizacaoCadastralHelper
								.setIndicadorExclusao((String) objeto[10]);
					}

					// Unidade de negocio id
					if (objeto[11] != null) {
						relatorioAtualizacaoCadastralHelper
								.setIdUnidadeNegocio((Integer) objeto[11]);
					}

					String endereco = getControladorEndereco()
							.obterEnderecoAbreviadoImovel(
									relatorioAtualizacaoCadastralHelper
											.getIdImovel());
					if (endereco != null && !endereco.trim().equals("")) {
						relatorioAtualizacaoCadastralHelper
								.setEndereco(endereco);
					}

					Collection existeLigacaoAgua = getControladorLigacaoAgua()
							.verificaExistenciaLigacaoAgua(
									relatorioAtualizacaoCadastralHelper
											.getIdImovel());
					if (existeLigacaoAgua != null) {

					}

					retorno.add(relatorioAtualizacaoCadastralHelper);

				}

				// parte nova para o relatório ter o processamento em batch
				// cria uma instância da classe do relatório

			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		return retorno;
	}

	/**
	 * [UC0725] Gerar Relatório de Imóveis por Situação da Ligação de Agua
	 *
	 * @author Rafael Pinto
	 * @date 03/12/2007
	 *
	 * @param FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper
	 *
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisSituacaoLigacaoAguaHelper> pesquisarRelatorioImoveisSituacaoLigacaoAgua(
			FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper filtro)
			throws ControladorException {

		Collection<RelatorioImoveisSituacaoLigacaoAguaHelper> retorno = new ArrayList<RelatorioImoveisSituacaoLigacaoAguaHelper>();

		Collection<Object[]> colecaoPesquisa = null;

		try {
			colecaoPesquisa = this.repositorioCadastro
					.pesquisarRelatorioImoveisSituacaoLigacaoAgua(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {

			Iterator itera = colecaoPesquisa.iterator();

			while (itera.hasNext()) {
				Object[] objeto = (Object[]) itera.next();

				RelatorioImoveisSituacaoLigacaoAguaHelper helper = new RelatorioImoveisSituacaoLigacaoAguaHelper();

				Integer idImovel = (Integer) objeto[0];
				Integer localidade = (Integer) objeto[5];
				Integer codigoSetorComercial = (Integer) objeto[7];
				Integer numeroQuadra = (Integer) objeto[9];

				Short lote = (Short) objeto[15];
				Short subLote = (Short) objeto[16];

				helper.setMatriculaImovel(Util
						.retornaMatriculaImovelFormatada(idImovel));
				helper.setGerenciaRegional((Integer) objeto[1]);
				helper.setNomeGerenciaRegional((String) objeto[2]);
				helper.setUnidadeNegocio((Integer) objeto[3]);
				helper.setNomeUnidadeNegocio((String) objeto[4]);
				helper.setLocalidade(localidade);
				helper.setDescricaoLocalidade((String) objeto[6]);
				helper.setSetorComercial(codigoSetorComercial);
				helper.setDescricaoSetorComercial((String) objeto[8]);
				helper.setNumeroQuadra(numeroQuadra);
				helper.setNomeCliente((String) objeto[10]);
				helper.setSituacaoLigacaoAgua((String) objeto[11]);
				helper.setSituacaoLigacaoEsgoto((String) objeto[12]);

				helper.setRota((Short) objeto[13]);
				helper.setSequencialRota((Integer) objeto[14]);

				Imovel imovel = new Imovel();
				imovel.setId(idImovel);

				Localidade local = new Localidade();
				local.setId(localidade);
				imovel.setLocalidade(local);

				SetorComercial setorComercial = new SetorComercial();
				setorComercial.setCodigo(codigoSetorComercial);
				imovel.setSetorComercial(setorComercial);

				Quadra quadra = new Quadra();
				quadra.setNumeroQuadra(numeroQuadra);
				imovel.setQuadra(quadra);

				imovel.setLote(lote);
				imovel.setSubLote(subLote);

				helper.setInscricaoImovel(imovel.getInscricaoFormatada());

				String endereco = this.getControladorEndereco()
						.obterEnderecoAbreviadoImovel(idImovel);
				helper.setEndereco(endereco);

				retorno.add(helper);
			}
		}

		return retorno;
	}

	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 *
	 * @author Bruno Barros
	 * @date 06/12/2007
	 *
	 * @param FiltrarRelatorioImoveisFaturasAtrasoHelper
	 *
	 * @return Collection<RelatorioImoveisSituacaoLigacaoAguaHelper>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisFaturasAtrasoHelper> pesquisarRelatorioImoveisFaturasAtrasoAgrupadasLocalizacao(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ControladorException {

		Collection<RelatorioImoveisFaturasAtrasoHelper> retorno = new ArrayList<RelatorioImoveisFaturasAtrasoHelper>();

		Collection<Object[]> colecaoPesquisa = null;

		try {
			colecaoPesquisa = this.repositorioCadastro
					.pesquisarRelatorioImoveisFaturasAtrasoAgrupadasLocalizacao(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {

			Iterator itera = colecaoPesquisa.iterator();

			while (itera.hasNext()) {
				Object[] objeto = (Object[]) itera.next();

				RelatorioImoveisFaturasAtrasoHelper helper = new RelatorioImoveisFaturasAtrasoHelper();

				Integer idImovel = (Integer) objeto[12];
				Integer localidade = (Integer) objeto[6];
				Integer codigoSetorComercial = (Integer) objeto[4];
				Integer numeroQuadra = (Integer) objeto[15];
				String cpf = (String) objeto[22];
				String cnpj = (String) objeto[23];

				helper.setMatriculaImovel(Util
						.retornaMatriculaImovelFormatada(idImovel));
				helper.setGerenciaRegional((Integer) objeto[0]);
				helper.setNomeGerenciaRegional((String) objeto[1]);
				helper.setUnidadeNegocio((Integer) objeto[2]);
				helper.setNomeUnidadeNegocio((String) objeto[3]);
				helper.setLocalidade(localidade);
				helper.setDescricaoLocalidade((String) objeto[7]);
				helper.setSetorComercial(codigoSetorComercial);
				helper.setDescricaoSetorComercial((String) objeto[5]);
				helper.setNomeClienteUsuario((String) objeto[8]);
				helper.setSituacaoLigacaoAgua((String) objeto[9]);
				helper.setSituacaoLigacaoEsgoto((String) objeto[14]);
				helper.setRota((Short) objeto[10]);
				helper.setSequencialRota((Integer) objeto[11]);
				helper.setQuantidadeFaturasAtraso(((Integer) objeto[17]));
				helper
						.setValorFaturasAtrasoSemEncargos((BigDecimal) objeto[18]);
				helper.setReferenciaFaturasAtrasoInicial((Integer) objeto[16]);
				helper.setReferenciaFaturasAtrasoFinal((Integer) objeto[19]);

				if (cpf != null && !cpf.equals("")) {
					helper.setCpfOuCnpjClienteUsuario(Util.formatarCpf(cpf));
				}
				if (cnpj != null && !cnpj.equals("")) {
					helper.setCpfOuCnpjClienteUsuario(Util.formatarCnpj(cnpj));
				}
				Imovel imovel = new Imovel();
				imovel.setId(idImovel);

				Localidade local = new Localidade();
				local.setId(localidade);
				imovel.setLocalidade(local);

				SetorComercial setorComercial = new SetorComercial();
				setorComercial.setCodigo(codigoSetorComercial);
				imovel.setSetorComercial(setorComercial);

				Quadra quadra = new Quadra();
				quadra.setNumeroQuadra(numeroQuadra);
				imovel.setQuadra(quadra);

				imovel.setLote((Short) objeto[20]);
				imovel.setSubLote((Short) objeto[21]);

				helper.setInscricaoImovel(imovel.getInscricaoFormatada());

				String endereco = this.getControladorEndereco()
						.pesquisarEnderecoFormatado(idImovel);
				helper.setEndereco(endereco);

				/*
				 * if((String) objeto[22] != null){
				 * helper.setCpfCnpj(Util.formatarCpf((String) objeto[22]));
				 * }else{ helper.setCpfCnpj(Util.formatarCnpj((String)
				 * objeto[23])); }
				 */

				retorno.add(helper);
			}
		}

		return retorno;
	}

	/**
	 * [UC0726] - Gerar Relatório de Imóveis com Faturas em Atraso
	 *
	 * @since 31/08/2009
	 * @author Marlon Patrick
	 */
	public Collection<RelatorioImoveisFaturasAtrasoHelper> pesquisarRelatorioImoveisFaturasAtrasoAgrupadasCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ControladorException {

		Collection<RelatorioImoveisFaturasAtrasoHelper> retorno = new ArrayList<RelatorioImoveisFaturasAtrasoHelper>();

		Collection<Object[]> colecaoPesquisa = null;

		try {
			colecaoPesquisa = this.repositorioCadastro
					.pesquisarRelatorioImoveisFaturasAtrasoAgrupadasCliente(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (!Util.isVazioOrNulo(colecaoPesquisa)) {

			for (Object[] dadosRelatorio : colecaoPesquisa) {

				RelatorioImoveisFaturasAtrasoHelper helper = new RelatorioImoveisFaturasAtrasoHelper();

				helper.setIdCliente((Integer) dadosRelatorio[0]);
				helper.setNomeCliente((String) dadosRelatorio[1]);
				helper.setGerenciaRegional((Integer) dadosRelatorio[2]);
				helper.setLocalidade((Integer) dadosRelatorio[3]);
				helper.setSetorComercial((Integer) dadosRelatorio[4]);
				helper.setNumeroQuadra((Integer) dadosRelatorio[5]);
				helper.setSituacaoLigacaoAgua((String) dadosRelatorio[6]);
				helper
						.setReferenciaFaturasAtrasoInicial((Integer) dadosRelatorio[7]);
				helper
						.setQuantidadeFaturasAtraso(((Integer) dadosRelatorio[8]));
				helper
						.setValorFaturasAtrasoSemEncargos((BigDecimal) dadosRelatorio[9]);
				helper.setRota((Short) dadosRelatorio[10]);
				helper.setSequencialRota((Integer) dadosRelatorio[11]);
				helper.setIdImovel((Integer) dadosRelatorio[12]);
				helper.setMatriculaImovel(Util
						.retornaMatriculaImovelFormatada(helper.getIdImovel()));
				helper.setSituacaoLigacaoEsgoto((String) dadosRelatorio[13]);
				helper
						.setReferenciaFaturasAtrasoFinal((Integer) dadosRelatorio[14]);

				String endereco = this.getControladorEndereco()
						.pesquisarEnderecoFormatado(helper.getIdImovel());
				helper.setEndereco(endereco);

				configurarInscricaoImovelFormatada(helper);

				Cliente clienteUsuario = getControladorImovel()
						.consultarClienteUsuarioImovel(
								new Imovel(helper.getIdImovel()));
				helper.setNomeClienteUsuario(clienteUsuario.getNome());

				// getControladorCobranca().calcularAcrescimoPorImpontualidade(
				// anoMesReferenciaDebito, dataVencimento, dataPagamento,
				// valorDebito,
				// valorMultasCobradas, indicadorMulta, anoMesArrecadacao,
				// idConta,
				// indicadorArrecadacao);

				retorno.add(helper);
			}
		}

		return retorno;
	}

	/**
	 * Méto auxiliar usado para obter a inscrição formatada de um imóvel e setar
	 * a mesma no Helper
	 *
	 * @since 01/09/2009
	 * @author Marlon Patrick
	 */
	private void configurarInscricaoImovelFormatada(
			RelatorioImoveisFaturasAtrasoHelper helper) {

		SetorComercial setorComercial = new SetorComercial();
		setorComercial.setCodigo(helper.getSetorComercial());

		Quadra quadra = new Quadra();
		quadra.setNumeroQuadra(helper.getNumeroQuadra());

		Imovel imovel = new Imovel(helper.getIdImovel());
		imovel.setQuadra(quadra);
		imovel.setSetorComercial(setorComercial);
		imovel.setLocalidade(new Localidade(helper.getLocalidade()));

		helper.setInscricaoImovel(imovel.getInscricaoFormatada());
	}

	/**
	 * [UC0725] Gerar Relatório de Imóveis por Situação da Ligação de Agua
	 *
	 * Pesquisa o Total Registro
	 *
	 * @author Rafael Pinto
	 * @date 04/12/2007
	 *
	 * @param FiltrarRelatorioImoveisFaturasAtrasoHelper
	 *
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisSituacaoLigacaoAgua(
			FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper filtro)
			throws ControladorException {

		try {
			return this.repositorioCadastro
					.pesquisarTotalRegistroRelatorioImoveisSituacaoLigacaoAgua(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 *
	 * Pesquisa o Total Registro
	 *
	 * @author Bruno Barros
	 * @date 06/12/2007
	 *
	 * @param FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper
	 *
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoLocalizacao(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ControladorException {

		Integer retorno = null;

		try {
			retorno = this.repositorioCadastro
					.pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoLocalizacao(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ControladorException {

		Integer retorno = null;

		try {
			retorno = this.repositorioCadastro
					.pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoCliente(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 * Pesquisa os imoveis para o relatorio de imoveis por consumo medio
	 *
	 * @author Bruno Barros
	 * @data 17/12/2007
	 *
	 * @param filtro
	 * @return Collection<RelatorioImoveisConsumoMedioHelper>
	 * @throws FachadaException
	 */
	public Collection<RelatorioImoveisConsumoMedioHelper> pesquisarRelatorioImoveisConsumoMedio(
			FiltrarRelatorioImoveisConsumoMedioHelper filtro)
			throws ControladorException {
		Collection<RelatorioImoveisConsumoMedioHelper> retorno = new ArrayList<RelatorioImoveisConsumoMedioHelper>();

		Collection<RelatorioImoveisConsumoMedioHelper> colecaoPesquisa = null;

		SistemaParametro sistemaParametro = this.getControladorUtil()
				.pesquisarParametrosDoSistema();

		try {
			colecaoPesquisa = this.repositorioCadastro
					.pesquisarRelatorioImoveisConsumoMedio(filtro,
							sistemaParametro.getAnoMesFaturamento());
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {

			Iterator itera = colecaoPesquisa.iterator();

			while (itera.hasNext()) {
				Object[] objeto = (Object[]) itera.next();

				RelatorioImoveisConsumoMedioHelper helper = new RelatorioImoveisConsumoMedioHelper();

				Integer idImovel = (Integer) objeto[13];
				Integer localidade = (Integer) objeto[4];
				Integer codigoSetorComercial = (Integer) objeto[6];
				Integer numeroQuadra = (Integer) objeto[18];

				helper.setMatriculaImovel(Util
						.retornaMatriculaImovelFormatada(idImovel));
				helper.setGerenciaRegional((Integer) objeto[0]);
				helper.setNomeGerenciaRegional((String) objeto[1]);
				helper.setUnidadeNegocio((Integer) objeto[2]);
				helper.setNomeUnidadeNegocio((String) objeto[3]);
				helper.setLocalidade(localidade);
				helper.setDescricaoLocalidade((String) objeto[5]);
				helper.setSetorComercial(codigoSetorComercial);
				helper.setDescricaoSetorComercial((String) objeto[7]);
				helper.setNomeCliente((String) objeto[8]);
				helper.setSituacaoLigacaoAgua((String) objeto[9]);

				helper.setConsumoMedioAgua((Integer) objeto[10]);
				helper.setCodigoRota((Short) objeto[11]);

				if (objeto[12] != null){
					helper.setSequencialRota((Integer) objeto[12]);
				}

				helper.setSituacaoLigacaoEsgoto((String) objeto[14]);
				helper.setConsumoMedioEsgoto((Integer) objeto[15]);

				// Montamos um objeto imovel para poder pesquisar sua inscrição
				Imovel imovel = new Imovel();
				imovel.setId(idImovel);

				Localidade local = new Localidade();
				local.setId(localidade);
				imovel.setLocalidade(local);

				SetorComercial setorComercial = new SetorComercial();
				setorComercial.setCodigo(codigoSetorComercial);
				imovel.setSetorComercial(setorComercial);

				Quadra quadra = new Quadra();
				quadra.setNumeroQuadra(numeroQuadra);
				imovel.setQuadra(quadra);

				imovel.setLote((Short) objeto[16]);
				imovel.setSubLote((Short) objeto[17]);

				helper.setInscricaoImovel(imovel.getInscricaoFormatada());
				// ------------------------------------------------------------

				// Selecionamos o endereço
				String endereco = this.getControladorEndereco()
						.obterEnderecoAbreviadoImovel(idImovel);
				helper.setEndereco(endereco);

				retorno.add(helper);
			}
		}

		return retorno;
	}

	/**
	 * [UC0727] Gerar Relatório de Imóveis por Consumo Medio Pesquisa a
	 * quantidade de imoveis para o relatorio de imoveis por consumo medio
	 *
	 * @author Bruno Barros
	 * @data 17/12/2007
	 *
	 * @param filtro
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisConsumoMedio(
			FiltrarRelatorioImoveisConsumoMedioHelper filtro)
			throws ControladorException {


		SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();

		try {
			return this.repositorioCadastro
					.pesquisarTotalRegistroRelatorioImoveisConsumoMedio(filtro,
							sistemaParametro.getAnoMesFaturamento());

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0731] Gerar Relatório de Imóveis com os Ultimos Consumos de Agua
	 *
	 * @author Rafael Pinto
	 * @date 19/12/2007
	 *
	 * @param FiltrarRelatorioImoveisUltimosConsumosAguaHelper
	 *
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisUltimosConsumosAgua(
			FiltrarRelatorioImoveisUltimosConsumosAguaHelper filtro)
			throws ControladorException {

		try {
			return this.repositorioCadastro
					.pesquisarTotalRegistroRelatorioImoveisUltimosConsumosAgua(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0731] Gerar Relatório de Imóveis com os Ultimos Consumos de Agua
	 *
	 * @author Rafael Pinto
	 * @date 18/12/2007
	 *
	 * @param FiltrarRelatorioImoveisUltimosConsumosAguaHelper
	 *
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisUltimosConsumosAguaHelper> pesquisarRelatorioImoveisUltimosConsumosAgua(
			FiltrarRelatorioImoveisUltimosConsumosAguaHelper filtro)
			throws ControladorException {

		Collection<RelatorioImoveisUltimosConsumosAguaHelper> retorno = new ArrayList<RelatorioImoveisUltimosConsumosAguaHelper>();

		Collection<Object[]> colecaoPesquisa = null;

		try {
			colecaoPesquisa = this.repositorioCadastro
					.pesquisarRelatorioImoveisUltimosConsumosAgua(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {

			Iterator itera = colecaoPesquisa.iterator();

			while (itera.hasNext()) {
				Object[] objeto = (Object[]) itera.next();

				RelatorioImoveisUltimosConsumosAguaHelper helper = new RelatorioImoveisUltimosConsumosAguaHelper();

				Integer idImovel = (Integer) objeto[0];
				Integer localidade = (Integer) objeto[5];
				Integer codigoSetorComercial = (Integer) objeto[7];
				Integer numeroQuadra = (Integer) objeto[9];

				Short lote = (Short) objeto[15];
				Short subLote = (Short) objeto[16];

				helper.setMatriculaImovel(Util
						.retornaMatriculaImovelFormatada(idImovel));
				helper.setGerenciaRegional((Integer) objeto[1]);
				helper.setNomeGerenciaRegional((String) objeto[2]);
				helper.setUnidadeNegocio((Integer) objeto[3]);
				helper.setNomeUnidadeNegocio((String) objeto[4]);
				helper.setLocalidade(localidade);
				helper.setDescricaoLocalidade((String) objeto[6]);
				helper.setSetorComercial(codigoSetorComercial);
				helper.setDescricaoSetorComercial((String) objeto[8]);

				helper.setNomeCliente((String) objeto[10]);
				helper.setSituacaoLigacaoAgua((String) objeto[11]);
				helper.setSituacaoLigacaoEsgoto((String) objeto[12]);

				helper.setRota((Short) objeto[13]);
				helper.setSequencialRota((Integer) objeto[14]);

				Imovel imovel = new Imovel();
				imovel.setId(idImovel);

				Localidade local = new Localidade();
				local.setId(localidade);
				imovel.setLocalidade(local);

				SetorComercial setorComercial = new SetorComercial();
				setorComercial.setCodigo(codigoSetorComercial);
				imovel.setSetorComercial(setorComercial);

				Quadra quadra = new Quadra();
				quadra.setNumeroQuadra(numeroQuadra);
				imovel.setQuadra(quadra);

				imovel.setLote(lote);
				imovel.setSubLote(subLote);

				helper.setInscricaoImovel(imovel.getInscricaoFormatada());

				String endereco = this.getControladorEndereco()
						.obterEnderecoAbreviadoImovel(idImovel);
				helper.setEndereco(endereco);

				Categoria categoria = this.getControladorImovel()
						.obterPrincipalCategoriaImovel(idImovel);

				ImovelSubcategoria imovelSubCategoria = this
						.getControladorImovel().obterPrincipalSubcategoria(
								categoria.getId(), idImovel);

				helper.setSubCategoria(imovelSubCategoria.getComp_id()
						.getSubcategoria().getId());
				helper.setEconomias((Short) objeto[19]);

				int anoMes = this.getControladorUtil()
						.pesquisarParametrosDoSistema().getAnoMesFaturamento();

				String consumoAgua = "";
				String descricaoConsumo = "";

				// 1-Consumo Agua
				String[] retornoConsumo = montarConsumoHistorico(idImovel,
						anoMes, 1);

				descricaoConsumo = retornoConsumo[0];
				consumoAgua = retornoConsumo[1];

				helper.setDescricaoConsumo1(descricaoConsumo);
				helper.setConsumoAgua1(consumoAgua);

				// 2-Consumo Agua
				retornoConsumo = montarConsumoHistorico(idImovel, anoMes, 2);

				descricaoConsumo = retornoConsumo[0];
				consumoAgua = retornoConsumo[1];

				helper.setDescricaoConsumo2(descricaoConsumo);
				helper.setConsumoAgua2(consumoAgua);

				// 3-Consumo Agua
				retornoConsumo = montarConsumoHistorico(idImovel, anoMes, 3);

				descricaoConsumo = retornoConsumo[0];
				consumoAgua = retornoConsumo[1];

				helper.setDescricaoConsumo3(descricaoConsumo);
				helper.setConsumoAgua3(consumoAgua);

				// 4-Consumo Agua
				retornoConsumo = montarConsumoHistorico(idImovel, anoMes, 4);

				descricaoConsumo = retornoConsumo[0];
				consumoAgua = retornoConsumo[1];

				helper.setDescricaoConsumo4(descricaoConsumo);
				helper.setConsumoAgua4(consumoAgua);

				// 5-Consumo Agua
				retornoConsumo = montarConsumoHistorico(idImovel, anoMes, 5);

				descricaoConsumo = retornoConsumo[0];
				consumoAgua = retornoConsumo[1];

				helper.setDescricaoConsumo5(descricaoConsumo);
				helper.setConsumoAgua5(consumoAgua);

				// 6-Consumo Agua
				retornoConsumo = montarConsumoHistorico(idImovel, anoMes, 6);

				descricaoConsumo = retornoConsumo[0];
				consumoAgua = retornoConsumo[1];

				helper.setDescricaoConsumo6(descricaoConsumo);
				helper.setConsumoAgua6(consumoAgua);

				// 7-Consumo Agua
				retornoConsumo = montarConsumoHistorico(idImovel, anoMes, 7);

				descricaoConsumo = retornoConsumo[0];
				consumoAgua = retornoConsumo[1];

				helper.setDescricaoConsumo7(descricaoConsumo);
				helper.setConsumoAgua7(consumoAgua);

				// 8-Consumo Agua
				retornoConsumo = montarConsumoHistorico(idImovel, anoMes, 8);

				descricaoConsumo = retornoConsumo[0];
				consumoAgua = retornoConsumo[1];

				helper.setDescricaoConsumo8(descricaoConsumo);
				helper.setConsumoAgua8(consumoAgua);

				// 9-Consumo Agua
				retornoConsumo = montarConsumoHistorico(idImovel, anoMes, 9);

				descricaoConsumo = retornoConsumo[0];
				consumoAgua = retornoConsumo[1];

				helper.setDescricaoConsumo9(descricaoConsumo);
				helper.setConsumoAgua9(consumoAgua);

				// 10-Consumo Agua
				retornoConsumo = montarConsumoHistorico(idImovel, anoMes, 10);

				descricaoConsumo = retornoConsumo[0];
				consumoAgua = retornoConsumo[1];

				helper.setDescricaoConsumo10(descricaoConsumo);
				helper.setConsumoAgua10(consumoAgua);

				// 11-Consumo Agua
				retornoConsumo = montarConsumoHistorico(idImovel, anoMes, 11);

				descricaoConsumo = retornoConsumo[0];
				consumoAgua = retornoConsumo[1];

				helper.setDescricaoConsumo11(descricaoConsumo);
				helper.setConsumoAgua11(consumoAgua);

				// 12-Consumo Agua
				retornoConsumo = montarConsumoHistorico(idImovel, anoMes, 12);

				descricaoConsumo = retornoConsumo[0];
				consumoAgua = retornoConsumo[1];

				helper.setDescricaoConsumo12(descricaoConsumo);
				helper.setConsumoAgua12(consumoAgua);

				retorno.add(helper);
			}
		}

		return retorno;
	}

	/**
	 * [UC0731] Gerar Relatório de Imóveis com os Ultimos Consumos de Agua
	 *
	 * Monta os consumos anteriores do imovel
	 *
	 * @author Rafael Pinto
	 * @date 19/12/2007
	 *
	 * @param idImovel
	 * @param anoMes
	 *
	 * @return String[3]
	 * @throws ErroRepositorioException
	 */
	private String[] montarConsumoHistorico(int idImovel, int anoMes,
			int qtdMeses) throws ControladorException {

		String[] retorno = new String[3];

		String consumoAgua = "";

		int anoMesSubtraido = Util.subtrairMesDoAnoMes(anoMes, qtdMeses);
		String descricaoConsumo = Util.retornaDescricaoAnoMes(""
				+ anoMesSubtraido);

		Object[] consumoHistorico = this.getControladorMicromedicao()
				.obterConsumoAnteriorAnormalidadeDoImovel(idImovel,
						anoMesSubtraido, LigacaoTipo.LIGACAO_AGUA);

		if (consumoHistorico != null) {
			if (consumoHistorico[0] != null) {
				consumoAgua = "" + (Integer) consumoHistorico[0];
			}
		}

		retorno[0] = descricaoConsumo;
		retorno[1] = consumoAgua;

		return retorno;
	}

	/**
	 * [UC00728] Gerar Relatório de Imóveis Ativos e Não Medidos
	 *
	 * @author Rafael Pinto
	 * @date 03/01/2008
	 *
	 * @param FiltrarRelatorioImoveisAtivosNaoMedidosHelper
	 *
	 * @return Collection<RelatorioImoveisAtivosNaoMedidosHelper>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisAtivosNaoMedidosHelper> pesquisarRelatorioImoveisAtivosNaoMedidos(
			FiltrarRelatorioImoveisAtivosNaoMedidosHelper filtro)
			throws ControladorException {

		Collection<RelatorioImoveisAtivosNaoMedidosHelper> retorno = new ArrayList<RelatorioImoveisAtivosNaoMedidosHelper>();

		Collection<Object[]> colecaoPesquisa = null;

		try {
			colecaoPesquisa = this.repositorioCadastro
					.pesquisarRelatorioImoveisAtivosNaoMedidos(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {

			Iterator itera = colecaoPesquisa.iterator();

			while (itera.hasNext()) {
				Object[] objeto = (Object[]) itera.next();

				RelatorioImoveisAtivosNaoMedidosHelper helper = new RelatorioImoveisAtivosNaoMedidosHelper();

				Integer idImovel = (Integer) objeto[0];
				Integer localidade = (Integer) objeto[5];
				Integer codigoSetorComercial = (Integer) objeto[7];
				Integer numeroQuadra = (Integer) objeto[9];

				Short lote = (Short) objeto[15];
				Short subLote = (Short) objeto[16];

				helper.setMatriculaImovel(Util
						.retornaMatriculaImovelFormatada(idImovel));

				helper.setGerenciaRegional((Integer) objeto[1]);
				helper.setNomeGerenciaRegional((String) objeto[2]);
				helper.setUnidadeNegocio((Integer) objeto[3]);
				helper.setNomeUnidadeNegocio((String) objeto[4]);
				helper.setLocalidade(localidade);
				helper.setDescricaoLocalidade((String) objeto[6]);
				helper.setSetorComercial(codigoSetorComercial);
				helper.setDescricaoSetorComercial((String) objeto[8]);
				helper.setNumeroQuadra(numeroQuadra);
				helper.setNomeCliente((String) objeto[10]);
				helper.setSituacaoLigacaoAgua((String) objeto[11]);
				helper.setSituacaoLigacaoEsgoto((String) objeto[12]);

				helper.setRota((Short) objeto[13]);
				helper.setSequencialRota((Integer) objeto[14]);

				Imovel imovel = new Imovel();
				imovel.setId(idImovel);

				Localidade local = new Localidade();
				local.setId(localidade);
				imovel.setLocalidade(local);

				SetorComercial setorComercial = new SetorComercial();
				setorComercial.setCodigo(codigoSetorComercial);
				imovel.setSetorComercial(setorComercial);

				Quadra quadra = new Quadra();
				quadra.setNumeroQuadra(numeroQuadra);
				imovel.setQuadra(quadra);

				imovel.setLote(lote);
				imovel.setSubLote(subLote);

				helper.setInscricaoImovel(imovel.getInscricaoFormatada());

				String endereco = this.getControladorEndereco()
						.obterEnderecoAbreviadoImovel(idImovel);
				helper.setEndereco(endereco);

				retorno.add(helper);
			}
		}

		return retorno;
	}

	/**
	 * [UC00728] Gerar Relatório de Imóveis Ativos e Não Medidos
	 *
	 * @author Rafael Pinto
	 * @date 03/01/2008
	 *
	 * @param FiltrarRelatorioImoveisAtivosNaoMedidosHelper
	 *
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisAtivosNaoMedidos(
			FiltrarRelatorioImoveisAtivosNaoMedidosHelper filtro)
			throws ControladorException {

		try {
			return this.repositorioCadastro
					.pesquisarTotalRegistroRelatorioImoveisAtivosNaoMedidos(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0729] Gerar Relatório de Imóveis por Tipo de Consumo
	 *
	 * @author Bruno Barros
	 * @date 10/01/2008
	 *
	 * @param RelatorioImoveisTipoConsumoHelper
	 *
	 * @return Collection<RelatorioImoveisTipoConsumoHelper>
	 * @throws FachadaException
	 */
	public Collection<RelatorioImoveisTipoConsumoHelper> pesquisarRelatorioImoveisTipoConsumo(
			FiltrarRelatorioImoveisTipoConsumoHelper filtro)
			throws ControladorException {
		Collection<RelatorioImoveisTipoConsumoHelper> retorno = new ArrayList<RelatorioImoveisTipoConsumoHelper>();

		Collection<Object[]> colecaoPesquisa = null;

		try {
			colecaoPesquisa = this.repositorioCadastro
					.pesquisarRelatorioImoveisTipoConsumo(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {

			Iterator itera = colecaoPesquisa.iterator();

			while (itera.hasNext()) {
				Object[] objeto = (Object[]) itera.next();

				RelatorioImoveisTipoConsumoHelper helper = new RelatorioImoveisTipoConsumoHelper();

				Integer idImovel = (Integer) objeto[8];
				Integer localidade = (Integer) objeto[4];
				Integer codigoSetorComercial = (Integer) objeto[6];
				Integer numeroQuadra = (Integer) objeto[15];

				helper.setMatriculaImovel(Util
						.retornaMatriculaImovelFormatada(idImovel));
				helper.setGerenciaRegional((Integer) objeto[0]);
				helper.setNomeGerenciaRegional((String) objeto[1]);
				helper.setUnidadeNegocio((Integer) objeto[2]);
				helper.setNomeUnidadeNegocio((String) objeto[3]);
				helper.setLocalidade(localidade);
				helper.setDescricaoLocalidade((String) objeto[5]);
				helper.setSetorComercial(codigoSetorComercial);
				helper.setDescricaoSetorComercial((String) objeto[7]);
				helper.setNomeCliente((String) objeto[9]);
				helper.setSituacaoLigacaoAgua((String) objeto[10]);

				helper.setTipoConsumo((String) objeto[11]);
				helper.setCodigoRota((Short) objeto[12]);
				helper.setSequencialRota((Integer) objeto[13]);
				helper.setSituacaoLigacaoEsgoto((String) objeto[14]);
				String mesAno = Util.formatarAnoMesParaMesAno(objeto[18]
						.toString());
				helper.setReferencia(mesAno);

				// Montamos um objeto imovel para poder pesquisar sua inscrição
				Imovel imovel = new Imovel();
				imovel.setId(idImovel);

				Localidade local = new Localidade();
				local.setId(localidade);
				imovel.setLocalidade(local);

				SetorComercial setorComercial = new SetorComercial();
				setorComercial.setCodigo(codigoSetorComercial);
				imovel.setSetorComercial(setorComercial);

				Quadra quadra = new Quadra();
				quadra.setNumeroQuadra(numeroQuadra);
				imovel.setQuadra(quadra);

				imovel.setLote((Short) objeto[16]);
				imovel.setSubLote((Short) objeto[17]);

				helper.setInscricaoImovel(imovel.getInscricaoFormatada());
				// ------------------------------------------------------------

				// Selecionamos o endereço
				String endereco = this.getControladorEndereco()
						.obterEnderecoAbreviadoImovel(idImovel);
				helper.setEndereco(endereco);

				retorno.add(helper);
			}
		}

		return retorno;
	}

	/**
	 * [UC0729] Gerar Relatório de Imóveis por Tipo de Consumo
	 *
	 * @author Bruno Barros
	 * @date 10/01/2008
	 *
	 * @param RelatorioImoveisTipoConsumoHelper
	 *
	 * @return Collection<RelatorioImoveisTipoConsumoHelper>
	 * @throws FachadaException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisTipoConsumo(
			FiltrarRelatorioImoveisTipoConsumoHelper filtro)
			throws ControladorException {

		try {
			return this.repositorioCadastro
					.pesquisarTotalRegistroRelatorioImoveisTipoConsumo(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC00730] Gerar Relatório de Imóveis com Faturas Recentes em Dia e
	 * Faturas Antigas em Atraso
	 *
	 * @author Rafael Pinto
	 * @date 08/01/2008
	 *
	 * @param
	 * FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper
	 *
	 * @return Collection<RelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper> pesquisarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(
			FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper filtro)
			throws ControladorException {

		Collection<RelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper> retorno = new ArrayList<RelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper>();

		Collection<Object[]> colecaoPesquisa = null;

		try {
			colecaoPesquisa = this.repositorioCadastro
					.pesquisarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {

			Iterator itera = colecaoPesquisa.iterator();

			while (itera.hasNext()) {
				Object[] objeto = (Object[]) itera.next();

				RelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper helper = new RelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper();

				Integer idImovel = (Integer) objeto[0];
				Integer localidade = (Integer) objeto[5];
				Integer codigoSetorComercial = (Integer) objeto[7];
				Integer numeroQuadra = (Integer) objeto[9];

				Short lote = (Short) objeto[15];
				Short subLote = (Short) objeto[16];

				helper.setMatriculaImovel(Util
						.retornaMatriculaImovelFormatada(idImovel));
				helper.setGerenciaRegional((Integer) objeto[1]);
				helper.setNomeGerenciaRegional((String) objeto[2]);
				helper.setUnidadeNegocio((Integer) objeto[3]);
				helper.setNomeUnidadeNegocio((String) objeto[4]);
				helper.setLocalidade(localidade);
				helper.setDescricaoLocalidade((String) objeto[6]);
				helper.setSetorComercial(codigoSetorComercial);
				helper.setDescricaoSetorComercial((String) objeto[8]);

				helper.setNomeCliente((String) objeto[10]);
				helper.setSituacaoLigacaoAgua((String) objeto[11]);
				helper.setSituacaoLigacaoEsgoto((String) objeto[12]);

				helper.setRota((Short) objeto[13]);
				helper.setSequencialRota((Integer) objeto[14]);

				Imovel imovel = new Imovel();
				imovel.setId(idImovel);

				Localidade local = new Localidade();
				local.setId(localidade);
				imovel.setLocalidade(local);

				SetorComercial setorComercial = new SetorComercial();
				setorComercial.setCodigo(codigoSetorComercial);
				imovel.setSetorComercial(setorComercial);

				Quadra quadra = new Quadra();
				quadra.setNumeroQuadra(numeroQuadra);
				imovel.setQuadra(quadra);

				imovel.setLote(lote);
				imovel.setSubLote(subLote);

				helper.setInscricaoImovel(imovel.getInscricaoFormatada());

				String endereco = this.getControladorEndereco()
						.obterEnderecoAbreviadoImovel(idImovel);
				helper.setEndereco(endereco);

				Categoria categoria = this.getControladorImovel()
						.obterPrincipalCategoriaImovel(idImovel);

				ImovelSubcategoria imovelSubCategoria = this
						.getControladorImovel().obterPrincipalSubcategoria(
								categoria.getId(), idImovel);

				helper.setSubCategoria(imovelSubCategoria.getComp_id()
						.getSubcategoria().getId());
				helper.setEconomias((Short) objeto[19]);

				try {
					Object[] dadosConta = this.repositorioFaturamento
							.pesquisarQuantidadeFaturasValorFaturas(idImovel);

					helper.setQuantidadeFaturasAtraso((Integer) dadosConta[0]);
					helper.setValorFaturasAtras((BigDecimal) dadosConta[1]);

					Integer dadosRefenciaAntigaConta = this.repositorioFaturamento
							.pesquisarReferenciaAntigaContaSemPagamento(idImovel);

					helper.setReferenciaInicial(dadosRefenciaAntigaConta);

					Integer dadosRefenciaAtualConta = this.repositorioFaturamento
							.pesquisarReferenciaAtualContaSemPagamento(idImovel);

					helper.setReferenciaFinal(dadosRefenciaAtualConta);

				} catch (ErroRepositorioException e) {
					throw new ControladorException("erro.sistema", e);
				}

				retorno.add(helper);
			}
		}

		return retorno;
	}

	/**
	 * [UC00730] Gerar Relatório de Imóveis com Faturas Recentes em Dia e
	 * Faturas Antigas em Atraso
	 *
	 * @author Rafael Pinto
	 * @date 08/01/2008
	 *
	 * @param
	 * FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper
	 *
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(
			FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper filtro)
			throws ControladorException {

		try {
			return this.repositorioCadastro
					.pesquisarTotalRegistroRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0762] Gerar Arquivo Texto com Dados Cadastrais - CAERN
	 *
	 * @author Tiago Moreno
	 * @date 08/04/2008
	 *
	 * @param ArquivoTextoDadosCadastraisHelper
	 *
	 * @return
	 * @throws ControladorException
	 */
	public void gerarArquivoTextoDadosCadastrais(
			ArquivoTextoDadosCadastraisHelper arquivoTextoDadosCadastraisHelper)
			throws ControladorException {

		// Recupera os imoveis a serem submetidos ao processo
		Collection<Imovel> imoveis = (Collection) this
				.recuperaImoveisArquivoTextoDadosCadastrais(arquivoTextoDadosCadastraisHelper);

		// verifica se há imoveis para os parametros informados
		if (imoveis == null || imoveis.isEmpty()) {
			throw new ControladorException(
					"atencao.sem_registros_arquivo_texto");
		}

		// joga numa colecao de helper todas as informacoes necessarias
		Collection<StringArquivoTextoDadosCadastraisHelper> collectionHelper = this
				.formatarStringArquivoTextoDadosCadastrais(imoveis);

		// Arquivo texto final
		StringBuilder arquivoTxFinal = new StringBuilder();
		BufferedWriter out = null;
		ZipOutputStream zos = null;

		try {

			int i = 1;
			int j = collectionHelper.size();

			// itera a colecao do helper montando as linhas
			for (Iterator iter = collectionHelper.iterator(); iter.hasNext();) {
				StringArquivoTextoDadosCadastraisHelper helper = (StringArquivoTextoDadosCadastraisHelper) iter
						.next();

				// variavel que cria linha por linha
				StringBuilder arquivoTx = new StringBuilder();

				// Inscricao Imovel - 16
				if (helper.getInscricaoImovel() != null
						&& !helper.getInscricaoImovel().equalsIgnoreCase("")) {
					arquivoTx.append(Util.completaString(helper
							.getInscricaoImovel(), 16));
					arquivoTx.append(";");
				} else {
					arquivoTx.append(Util.completaString(" ", 16));
					arquivoTx.append(";");
				}

				// Matricula Imovel Imovel - 8
				if (helper.getMatriculaImovel() != null
						&& !helper.getMatriculaImovel().equalsIgnoreCase("")) {
					arquivoTx.append(Util.completaString(helper
							.getMatriculaImovel(), 8));
					arquivoTx.append(";");
				} else {
					arquivoTx.append(Util.completaString(" ", 8));
					arquivoTx.append(";");
				}

				// Nome Cliente usuario- 28
				if (helper.getNomeCliente() != null
						&& !helper.getNomeCliente().equalsIgnoreCase("")) {
					arquivoTx.append(Util.completaString(helper
							.getNomeCliente(), 28));
					arquivoTx.append(";");
				} else {
					arquivoTx.append(Util.completaString(" ", 28));
					arquivoTx.append(";");
				}

				// Numero Imovel Imovel - 5
				if (helper.getNumeroImovel() != null
						&& !helper.getNumeroImovel().equalsIgnoreCase("")) {
					arquivoTx.append(Util.completaString(helper
							.getNumeroImovel(), 5));
					arquivoTx.append(";");
				} else {
					arquivoTx.append(Util.completaString(" ", 5));
					arquivoTx.append(";");
				}

				// Complemento do Endereco do Imovel - 10
				if (helper.getComplementoEndereco() != null
						&& !helper.getComplementoEndereco()
								.equalsIgnoreCase("")) {
					arquivoTx.append(Util.completaString(helper
							.getComplementoEndereco(), 10));
					arquivoTx.append(";");
				} else {
					arquivoTx.append(Util.completaString(" ", 10));
					arquivoTx.append(";");
				}

				// Situacao de Agua do Imovel - 1
				if (helper.getSituacaoAgua() != null
						&& !helper.getSituacaoAgua().equalsIgnoreCase("")) {
					arquivoTx.append(Util.completaString(helper
							.getSituacaoAgua(), 1));
					arquivoTx.append(";");
				} else {
					arquivoTx.append(Util.completaString(" ", 1));
					arquivoTx.append(";");
				}

				// Situacao do Imovel - 1
				if (helper.getSituacaoImovel() != null
						&& !helper.getSituacaoImovel().equalsIgnoreCase("")) {
					arquivoTx.append(Util.completaString(helper
							.getSituacaoImovel(), 1));
					arquivoTx.append(";");
				} else {
					arquivoTx.append(Util.completaString(" ", 1));
					arquivoTx.append(";");
				}

				// Situacao de Esgoto do Imovel - 1
				if (helper.getSituacaoEsgoto() != null
						&& !helper.getSituacaoEsgoto().equalsIgnoreCase("")) {
					arquivoTx.append(Util.completaString(helper
							.getSituacaoEsgoto(), 1));
					arquivoTx.append(";");
				} else {
					arquivoTx.append(Util.completaString(" ", 1));
					arquivoTx.append(";");
				}

				// Codigo da Subcategoria 01 - 3
				if (helper.getCodigoSubcategoria01() != null
						&& !helper.getCodigoSubcategoria01().equalsIgnoreCase(
								"")) {
					arquivoTx.append(Util.completaString(Util
							.adicionarZerosEsquedaNumero(3, helper
									.getCodigoSubcategoria01()), 3));
					arquivoTx.append(";");
				} else {
					arquivoTx.append(Util.completaString("000", 3));
					arquivoTx.append(";");
				}

				// Quantidade da Subcategoria 01 - 3
				if (helper.getQuantidadeSubcategoria01() != null
						&& !helper.getQuantidadeSubcategoria01()
								.equalsIgnoreCase("")) {
					arquivoTx.append(Util.completaString(Util
							.adicionarZerosEsquedaNumero(3, helper
									.getQuantidadeSubcategoria01()), 3));
					arquivoTx.append(";");
				} else {
					arquivoTx.append(Util.completaString("000", 3));
					arquivoTx.append(";");
				}

				// Codigo da Subcategoria 02 - 3
				if (helper.getCodigoSubcategoria02() != null
						&& !helper.getCodigoSubcategoria02().equalsIgnoreCase(
								"")) {
					arquivoTx.append(Util.completaString(Util
							.adicionarZerosEsquedaNumero(3, helper
									.getCodigoSubcategoria02()), 3));
					arquivoTx.append(";");
				} else {
					arquivoTx.append(Util.completaString("000", 3));
					arquivoTx.append(";");
				}

				// Quantidade da Subcategoria 02 - 3
				if (helper.getQuantidadeSubcategoria02() != null
						&& !helper.getQuantidadeSubcategoria02()
								.equalsIgnoreCase("")) {
					arquivoTx.append(Util.completaString(Util
							.adicionarZerosEsquedaNumero(3, helper
									.getQuantidadeSubcategoria02()), 3));
					arquivoTx.append(";");
				} else {
					arquivoTx.append(Util.completaString("000", 3));
					arquivoTx.append(";");
				}

				// Codigo da Subcategoria 03 - 3
				if (helper.getCodigoSubcategoria03() != null
						&& !helper.getCodigoSubcategoria03().equalsIgnoreCase(
								"")) {
					arquivoTx.append(Util.completaString(Util
							.adicionarZerosEsquedaNumero(3, helper
									.getCodigoSubcategoria03()), 3));
					arquivoTx.append(";");
				} else {
					arquivoTx.append(Util.completaString("000", 3));
					arquivoTx.append(";");
				}

				// Quantidade da Subcategoria 03 - 3
				if (helper.getQuantidadeSubcategoria03() != null
						&& !helper.getQuantidadeSubcategoria03()
								.equalsIgnoreCase("")) {
					arquivoTx.append(Util.completaString(Util
							.adicionarZerosEsquedaNumero(3, helper
									.getQuantidadeSubcategoria03()), 3));
					arquivoTx.append(";");
				} else {
					arquivoTx.append(Util.completaString("000", 3));
					arquivoTx.append(";");
				}

				// Codigo da Subcategoria 04 - 3
				if (helper.getCodigoSubcategoria04() != null
						&& !helper.getCodigoSubcategoria04().equalsIgnoreCase(
								"")) {
					arquivoTx.append(Util.completaString(Util
							.adicionarZerosEsquedaNumero(3, helper
									.getCodigoSubcategoria04()), 3));
					arquivoTx.append(";");
				} else {
					arquivoTx.append(Util.completaString("000", 3));
					arquivoTx.append(";");
				}

				// Quantidade da Subcategoria 04 - 3
				if (helper.getQuantidadeSubcategoria04() != null
						&& !helper.getQuantidadeSubcategoria04()
								.equalsIgnoreCase("")) {
					arquivoTx.append(Util.completaString(Util
							.adicionarZerosEsquedaNumero(3, helper
									.getQuantidadeSubcategoria04()), 3));
					arquivoTx.append(";");
				} else {
					arquivoTx.append(Util.completaString("000", 3));
					arquivoTx.append(";");
				}

				// Codigo da Subcategoria 05 - 3
				if (helper.getCodigoSubcategoria05() != null
						&& !helper.getCodigoSubcategoria05().equalsIgnoreCase(
								"")) {
					arquivoTx.append(Util.completaString(Util
							.adicionarZerosEsquedaNumero(3, helper
									.getCodigoSubcategoria05()), 3));
					arquivoTx.append(";");
				} else {
					arquivoTx.append(Util.completaString("000", 3));
					arquivoTx.append(";");
				}

				// Quantidade da Subcategoria 05 - 3
				if (helper.getQuantidadeSubcategoria05() != null
						&& !helper.getQuantidadeSubcategoria05()
								.equalsIgnoreCase("")) {
					arquivoTx.append(Util.completaString(Util
							.adicionarZerosEsquedaNumero(3, helper
									.getQuantidadeSubcategoria05()), 3));
					arquivoTx.append(";");
				} else {
					arquivoTx.append(Util.completaString("000", 3));
					arquivoTx.append(";");
				}

				// Tipo do Logradouro do Imovel - 5
				if (helper.getTipoLogradouro() != null
						&& !helper.getTipoLogradouro().equalsIgnoreCase("")) {
					arquivoTx.append(Util.completaString(helper
							.getTipoLogradouro(), 5));
					arquivoTx.append(";");
				} else {
					arquivoTx.append(Util.completaString(" ", 5));
					arquivoTx.append(";");
				}

				// Título do Logradouro do Imovel - 6
				if (helper.getTituloLogradouro() != null
						&& !helper.getTituloLogradouro().equalsIgnoreCase("")) {
					arquivoTx.append(Util.completaString(helper
							.getTituloLogradouro(), 6));
					arquivoTx.append(";");
				} else {
					arquivoTx.append(Util.completaString(" ", 6));
					arquivoTx.append(";");
				}

				// Nome do Logradouro do Imovel - 19
				if (helper.getNomeLogradouro() != null
						&& !helper.getNomeLogradouro().equalsIgnoreCase("")) {
					arquivoTx.append(Util.completaString(helper
							.getNomeLogradouro(), 19));
					arquivoTx.append(";");
				} else {
					arquivoTx.append(Util.completaString(" ", 19));
					arquivoTx.append(";");
				}

				// Nome do Bairro do Imovel - 20
				if (helper.getNomeBairro() != null
						&& !helper.getNomeBairro().equalsIgnoreCase("")) {
					arquivoTx.append(Util.completaString(
							helper.getNomeBairro(), 20));
					arquivoTx.append(";");
				} else {
					arquivoTx.append(Util.completaString(" ", 20));
					arquivoTx.append(";");
				}

				// adicionando a linha no TXT
				arquivoTxFinal.append(arquivoTx.toString());

				// zerando a variavel da linha
				arquivoTx = null;

				// adicionando quebra da linha
				arquivoTxFinal.append(System.getProperty("line.separator"));

				System.out.println("Gerada Linha: " + i + "/" + j);
				i++;

			}

			System.out.println("CRIANDO O ARQUIVO TEXTO...");

			// criando nome do arquivo
			String nomeZip = null;

			String data = Util.formatarDataSemBarraDDMMAAAA(new Date());

			String hora = Util.formatarHoraSemDataSemDoisPontos(new Date());

			nomeZip = "ARQUIVO_DADOS_CADASTRAIS_" + data + "_" + hora;

			File compactadoTipo = new File(nomeZip + ".zip");
			File leituraTipo = new File(nomeZip + ".txt");

			if (arquivoTxFinal != null && arquivoTxFinal.length() != 0) {
				zos = new ZipOutputStream(new FileOutputStream(compactadoTipo));
				out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(leituraTipo.getAbsolutePath())));
				out.write(arquivoTxFinal.toString());
				out.flush();
				ZipUtil.adicionarArquivo(zos, leituraTipo);
				zos.close();
				leituraTipo.delete();
				out.close();
			}

			// limpa todos os campos
			nomeZip = null;
			out = null;
			zos = null;
			compactadoTipo = null;
			leituraTipo = null;
			arquivoTxFinal = null;

			System.out.println("ARQUIVO TEXTO CRIADO COM SUCESSO!");

		} catch (IOException e) {
			String mensagem = e.getMessage();
			String[] inicioMensagem = mensagem.split("\\.");
			if (inicioMensagem != null
					&& (inicioMensagem[0].equals("erro") || inicioMensagem[0]
							.equals("atencao"))) {
				throw new ControladorException(mensagem);
			} else {
				throw new ControladorException("erro.sistema", e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			String mensagem = e.getMessage();
			if (mensagem != null) {
				String[] inicioMensagem = mensagem.split("\\.");
				if (inicioMensagem != null
						&& (inicioMensagem[0].equals("erro") || inicioMensagem[0]
								.equals("atencao"))) {
					throw new ControladorException(mensagem);
				} else {
					throw new ControladorException("erro.sistema", e);
				}
			} else {
				throw new ControladorException("erro.sistema", e);
			}
		}

	}

	/**
	 * [UC0762] Gerar Arquivo Texto com Dados Cadastrais - CAERN
	 *
	 * Retornar a string formatada para o Arquivo Texto dos dados cadastrais
	 *
	 * @author Tiago Moreno
	 * @date 08/04/2008
	 *
	 * @param ArquivoTextoDadosCadastraisHelper
	 *
	 * @return
	 * @throws ControladorException
	 */
	public Collection<StringArquivoTextoDadosCadastraisHelper> formatarStringArquivoTextoDadosCadastrais(
			Collection<Imovel> imoveis) throws ControladorException {

		Collection<StringArquivoTextoDadosCadastraisHelper> retorno = new ArrayList();

		int i = 1;
		int j = imoveis.size();

		for (Iterator iter = imoveis.iterator(); iter.hasNext();) {

			Imovel imovel = (Imovel) iter.next();

			StringArquivoTextoDadosCadastraisHelper objeto = new StringArquivoTextoDadosCadastraisHelper();

			// Inscricao do Imovel - 16
			objeto.setInscricaoImovel(getControladorImovel()
					.pesquisarInscricaoImovelSemPonto(imovel.getId()));

			// Matricula do Imovel - 8
			objeto.setMatriculaImovel(Util.adicionarZerosEsquedaNumero(8,
					imovel.getId().toString()));

			// Nome do Cliente - 28
			FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.IMOVEL_ID, imovel.getId().toString()));
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID,
					ClienteRelacaoTipo.USUARIO));
			filtroClienteImovel.adicionarParametro(new ParametroNulo(
					FiltroClienteImovel.DATA_FIM_RELACAO));

			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("imovel");
			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");

			ClienteImovel clienteImovel = (ClienteImovel) getControladorUtil()
					.pesquisar(filtroClienteImovel,
							ClienteImovel.class.getName()).iterator().next();
			objeto.setNomeCliente(Util.completaString(clienteImovel
					.getCliente().getNome(), 28));

			// Numero do Imovel - 5
			objeto.setNumeroImovel(Util.completaString(
					imovel.getNumeroImovel(), 5));

			// Complemento Endereco - 10
			objeto.setComplementoEndereco(Util.completaString(imovel
					.getComplementoEndereco(), 10));

			// Situacao de Ligacao de Agua - 1
			String ligacaoAgua = " ";

			if (imovel.getLigacaoAguaSituacao().getId().equals(
					LigacaoAguaSituacao.POTENCIAL)
					|| imovel.getLigacaoAguaSituacao().getId().equals(
							LigacaoAguaSituacao.FACTIVEL)) {
				ligacaoAgua = "0";
			} else {
				ligacaoAgua = "1";
			}
			objeto.setSituacaoAgua(ligacaoAgua);

			// Situacao do Imovel
			String situacaoImovel = " ";

			switch (imovel.getLigacaoAguaSituacao().getId()) {
			case 1:
				situacaoImovel = "0";
				break;
			case 2:
				situacaoImovel = "0";
				break;
			case 3:
				situacaoImovel = "1";
				break;
			case 4:
				situacaoImovel = "1";
				break;
			case 5:
				situacaoImovel = "2";
				break;
			case 6:
				situacaoImovel = "3";
				break;
			case 7:
				situacaoImovel = "3";
				break;
			case 8:
				situacaoImovel = "3";
				break;
			case 9:
				situacaoImovel = "9";
				break;
			}

			objeto.setSituacaoImovel(Util.completaString(situacaoImovel, 1));

			// Situacao de Ligacao de Esgoto - 1
			String ligacaoEsgoto = " ";

			if (imovel.getLigacaoEsgotoSituacao().getId().equals("1")
					|| imovel.getLigacaoEsgotoSituacao().getId().equals("2")) {

				ligacaoEsgoto = "0";
			} else if (imovel.getLigacaoEsgoto() == null
					|| imovel.getLigacaoEsgoto().getLigacaoEsgotoPerfil() == null) {
				ligacaoEsgoto = "0";
			} else if (imovel.getLigacaoEsgoto().getLigacaoEsgotoPerfil()
					.getId().equals("2")) {
				ligacaoEsgoto = "2";
			} else {
				ligacaoEsgoto = "1";
			}

			objeto.setSituacaoEsgoto(ligacaoEsgoto);

			// subcategorias e economias
			Collection<ImovelSubcategoria> colecaoImovelSubcategoria = getControladorImovel()
					.pesquisarImovelSubcategoria(imovel);

			int qtImovelSubCategoria = colecaoImovelSubcategoria.size();

			Iterator iterImovelSubCategoria = colecaoImovelSubcategoria
					.iterator();

			ImovelSubcategoria imovSubCat01 = new ImovelSubcategoria();
			ImovelSubcategoria imovSubCat02 = new ImovelSubcategoria();
			ImovelSubcategoria imovSubCat03 = new ImovelSubcategoria();
			ImovelSubcategoria imovSubCat04 = new ImovelSubcategoria();
			ImovelSubcategoria imovSubCat05 = new ImovelSubcategoria();

			if (qtImovelSubCategoria == 1) {

				imovSubCat01 = (ImovelSubcategoria) iterImovelSubCategoria
						.next();
				objeto
						.setCodigoSubcategoria01(((ImovelSubcategoria) imovSubCat01)
								.getComp_id().getSubcategoria().getCodigo()
								+ "");
				objeto
						.setQuantidadeSubcategoria01(((ImovelSubcategoria) imovSubCat01)
								.getQuantidadeEconomias()
								+ "");
			} else if (qtImovelSubCategoria == 2) {
				imovSubCat01 = (ImovelSubcategoria) iterImovelSubCategoria
						.next();
				objeto
						.setCodigoSubcategoria01(((ImovelSubcategoria) imovSubCat01)
								.getComp_id().getSubcategoria().getCodigo()
								+ "");
				objeto
						.setQuantidadeSubcategoria01(((ImovelSubcategoria) imovSubCat01)
								.getQuantidadeEconomias()
								+ "");

				imovSubCat02 = (ImovelSubcategoria) iterImovelSubCategoria
						.next();
				objeto
						.setCodigoSubcategoria02(((ImovelSubcategoria) imovSubCat02)
								.getComp_id().getSubcategoria().getCodigo()
								+ "");
				objeto
						.setQuantidadeSubcategoria02(((ImovelSubcategoria) imovSubCat02)
								.getQuantidadeEconomias()
								+ "");
			} else if (qtImovelSubCategoria == 3) {
				imovSubCat01 = (ImovelSubcategoria) iterImovelSubCategoria
						.next();
				objeto
						.setCodigoSubcategoria01(((ImovelSubcategoria) imovSubCat01)
								.getComp_id().getSubcategoria().getCodigo()
								+ "");
				objeto
						.setQuantidadeSubcategoria01(((ImovelSubcategoria) imovSubCat01)
								.getQuantidadeEconomias()
								+ "");

				imovSubCat02 = (ImovelSubcategoria) iterImovelSubCategoria
						.next();
				objeto
						.setCodigoSubcategoria02(((ImovelSubcategoria) imovSubCat02)
								.getComp_id().getSubcategoria().getCodigo()
								+ "");
				objeto
						.setQuantidadeSubcategoria02(((ImovelSubcategoria) imovSubCat02)
								.getQuantidadeEconomias()
								+ "");

				imovSubCat03 = (ImovelSubcategoria) iterImovelSubCategoria
						.next();
				objeto
						.setCodigoSubcategoria03(((ImovelSubcategoria) imovSubCat03)
								.getComp_id().getSubcategoria().getCodigo()
								+ "");
				objeto
						.setQuantidadeSubcategoria03(((ImovelSubcategoria) imovSubCat03)
								.getQuantidadeEconomias()
								+ "");

			} else if (qtImovelSubCategoria == 4) {
				imovSubCat01 = (ImovelSubcategoria) iterImovelSubCategoria
						.next();
				objeto
						.setCodigoSubcategoria01(((ImovelSubcategoria) imovSubCat01)
								.getComp_id().getSubcategoria().getCodigo()
								+ "");
				objeto
						.setQuantidadeSubcategoria01(((ImovelSubcategoria) imovSubCat01)
								.getQuantidadeEconomias()
								+ "");

				imovSubCat02 = (ImovelSubcategoria) iterImovelSubCategoria
						.next();
				objeto
						.setCodigoSubcategoria02(((ImovelSubcategoria) imovSubCat02)
								.getComp_id().getSubcategoria().getCodigo()
								+ "");
				objeto
						.setQuantidadeSubcategoria02(((ImovelSubcategoria) imovSubCat02)
								.getQuantidadeEconomias()
								+ "");

				imovSubCat03 = (ImovelSubcategoria) iterImovelSubCategoria
						.next();
				objeto
						.setCodigoSubcategoria03(((ImovelSubcategoria) imovSubCat03)
								.getComp_id().getSubcategoria().getCodigo()
								+ "");
				objeto
						.setQuantidadeSubcategoria03(((ImovelSubcategoria) imovSubCat03)
								.getQuantidadeEconomias()
								+ "");

				imovSubCat04 = (ImovelSubcategoria) iterImovelSubCategoria
						.next();
				objeto
						.setCodigoSubcategoria04(((ImovelSubcategoria) imovSubCat04)
								.getComp_id().getSubcategoria().getCodigo()
								+ "");
				objeto
						.setQuantidadeSubcategoria04(((ImovelSubcategoria) imovSubCat04)
								.getQuantidadeEconomias()
								+ "");

			} else if (qtImovelSubCategoria == 5) {
				imovSubCat01 = (ImovelSubcategoria) iterImovelSubCategoria
						.next();
				objeto
						.setCodigoSubcategoria01(((ImovelSubcategoria) imovSubCat01)
								.getComp_id().getSubcategoria().getCodigo()
								+ "");
				objeto
						.setQuantidadeSubcategoria01(((ImovelSubcategoria) imovSubCat01)
								.getQuantidadeEconomias()
								+ "");

				imovSubCat02 = (ImovelSubcategoria) iterImovelSubCategoria
						.next();
				objeto
						.setCodigoSubcategoria02(((ImovelSubcategoria) imovSubCat02)
								.getComp_id().getSubcategoria().getCodigo()
								+ "");
				objeto
						.setQuantidadeSubcategoria02(((ImovelSubcategoria) imovSubCat02)
								.getQuantidadeEconomias()
								+ "");

				imovSubCat03 = (ImovelSubcategoria) iterImovelSubCategoria
						.next();
				objeto
						.setCodigoSubcategoria03(((ImovelSubcategoria) imovSubCat03)
								.getComp_id().getSubcategoria().getCodigo()
								+ "");
				objeto
						.setQuantidadeSubcategoria03(((ImovelSubcategoria) imovSubCat03)
								.getQuantidadeEconomias()
								+ "");

				imovSubCat04 = (ImovelSubcategoria) iterImovelSubCategoria
						.next();
				objeto
						.setCodigoSubcategoria04(((ImovelSubcategoria) imovSubCat04)
								.getComp_id().getSubcategoria().getCodigo()
								+ "");
				objeto
						.setQuantidadeSubcategoria04(((ImovelSubcategoria) imovSubCat04)
								.getQuantidadeEconomias()
								+ "");

				imovSubCat05 = (ImovelSubcategoria) iterImovelSubCategoria
						.next();
				objeto
						.setCodigoSubcategoria05(((ImovelSubcategoria) imovSubCat05)
								.getComp_id().getSubcategoria().getCodigo()
								+ "");
				objeto
						.setQuantidadeSubcategoria05(((ImovelSubcategoria) imovSubCat05)
								.getQuantidadeEconomias()
								+ "");

			}

			// pesquisa o endereco do imovel
			Collection endereco = getControladorEndereco()
					.pesquisarEnderecoTotalmenteDividido(imovel.getId());

			Object[] objetoEndereco = (Object[]) endereco.iterator().next();

			// Tipo Logradouro - 5
			objeto.setTipoLogradouro(Util.completaString(
					(String) objetoEndereco[22], 5));

			// Titulo Logradouro - 6
			objeto.setTituloLogradouro(Util.completaString(
					(String) objetoEndereco[23], 6));

			// Nome Logradouro - 19
			objeto.setNomeLogradouro(Util.completaString(
					(String) objetoEndereco[0], 19));

			// Nome Bairro - 20
			objeto.setNomeBairro(Util.completaString(
					(String) objetoEndereco[4], 20));

			retorno.add(objeto);

			System.out.println("Formatada Linha: " + i + "/" + j);
			i++;
		}

		return retorno;
	}

	/**
	 * [UC0762] Gerar Arquivo Texto com Dados Cadastrais - CAERN
	 *
	 * O método retorna uma colecao de Imoveis para que a partir daí comece a
	 * geracao das linhas TXTs.
	 *
	 * @author Tiago Moreno
	 * @date 08/04/2008
	 *
	 * @param ArquivoTextoDadosCadastraisHelper
	 *
	 * @return Collection<Imovel>
	 * @throws ControladorException
	 */

	public Collection<Imovel> recuperaImoveisArquivoTextoDadosCadastrais(
			ArquivoTextoDadosCadastraisHelper arquivoTextoDadosCadastraisHelper)
			throws ControladorException {

		try {
			return this.repositorioCadastro
					.pesquisarImovelArquivoTextoDadosCadastrais(arquivoTextoDadosCadastraisHelper);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0763] Gerar Arquivo Texto de Ligacoes com Hidrometro - CAERN
	 *
	 * @author Tiago Moreno
	 * @date 10/04/2008
	 *
	 * @param ArquivoTextoLigacoesHidrometroHelper
	 *
	 * @return
	 * @throws ControladorException
	 */
	public Collection<HidrometroInstalacaoHistorico> recuperaImoveisArquivoTextoLigacoesHidrometro(
			ArquivoTextoLigacoesHidrometroHelper arquivoTextoLigacoesHidrometroHelper)
			throws ControladorException {

		try {
			return this.repositorioCadastro
					.pesquisarImovelArquivoTextoLigacoesHidrometro(arquivoTextoLigacoesHidrometroHelper);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * Pesquisar os todos os ids de Setor comercial
	 *
	 * @author Vivianne Sousa
	 * @date 07/02/2007
	 *
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsCodigosSetorComercial()
			throws ControladorException {

		try {
			return this.repositorioSetorComercial
					.pesquisarIdsCodigosSetorComercial();
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0330] Inserir Mensagem da Conta
	 *
	 * [SB0001] Pesquisar Setor Comercial
	 *
	 * @author Raphael Rossiter
	 * @date 25/06/2008
	 *
	 * @param tipoArgumento
	 * @param indiceInicial
	 * @param indiceFinal
	 * @param anoMesReferencia
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarSetorComercialPorQualidadeAgua(
			int tipoArgumento, BigDecimal indiceInicial,
			BigDecimal indiceFinal, Integer anoMesReferencia)
			throws ControladorException {

		Collection colecaoRetorno = new ArrayList();
		Collection colecaoDadosQualidadeAgua = null;

		try {

			colecaoDadosQualidadeAgua = this.repositorioCadastro
					.pesquisarSetorComercialPorQualidadeAgua(tipoArgumento,
							indiceInicial, indiceFinal, anoMesReferencia);

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		// Caso a qualidade de água não exista na tabela QUALIDADE_AGUA
		if (colecaoDadosQualidadeAgua == null
				|| colecaoDadosQualidadeAgua.isEmpty()) {

			throw new ControladorException("atencao.pesquisa_inexistente",
					null, "Qualidade de Água");
		}

		Iterator iterator = colecaoDadosQualidadeAgua.iterator();
		Object[] dadosQualidadeAgua = null;

		while (iterator.hasNext()) {

			dadosQualidadeAgua = (Object[]) iterator.next();

			// Caso a qualidade de água tenha localidade na tabela
			// QUALIDADE_AGUA
			if (dadosQualidadeAgua[0] != null) {

				Localidade localidade = new Localidade();
				localidade.setId((Integer) dadosQualidadeAgua[0]);
				localidade.setDescricao((String) dadosQualidadeAgua[1]);

				/*
				 * Caso a qualidade de água tenha setor comercial na tabela
				 * QUALIDADE_AGUA, o mesmo será disponibilizado.
				 */
				if (dadosQualidadeAgua[2] != null) {

					// Será necessário carregar o nome da localidade para ser
					// visualizado ao lado do nome do setor
					SetorComercial setorComercial = (SetorComercial) dadosQualidadeAgua[2];
					setorComercial.setLocalidade(localidade);

					FiltroContaMensagem filtroContaMensagem = new FiltroContaMensagem();

					filtroContaMensagem
							.adicionarParametro(new ParametroSimples(
									FiltroContaMensagem.ANO_MES_REFERECIA_FATURAMENTO,
									anoMesReferencia));

					filtroContaMensagem
							.adicionarParametro(new ParametroSimples(
									FiltroContaMensagem.SETOR_COMERCIAL_ID,
									setorComercial.getId()));

					Collection colecaoContaMensagem = this.getControladorUtil()
							.pesquisar(filtroContaMensagem,
									ContaMensagem.class.getName());

					if (colecaoContaMensagem == null
							|| colecaoContaMensagem.isEmpty()) {
						colecaoRetorno.add(dadosQualidadeAgua[2]);
					}

				}
				// Caso contrário todos os setores da localidade serão
				// disponibilizados
				else {

					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial(
							FiltroSetorComercial.DESCRICAO);

					filtroSetorComercial
							.adicionarCaminhoParaCarregamentoEntidade("localidade");

					filtroSetorComercial
							.adicionarParametro(new ParametroSimples(
									FiltroSetorComercial.ID_LOCALIDADE,
									localidade.getId()));

					Collection colecaoSetoresPorLocalidade = this
							.getControladorUtil().pesquisar(
									filtroSetorComercial,
									SetorComercial.class.getName());

					if (colecaoSetoresPorLocalidade != null
							&& !colecaoSetoresPorLocalidade.isEmpty()) {

						Iterator iteratorSetores = colecaoSetoresPorLocalidade
								.iterator();

						while (iteratorSetores.hasNext()) {

							SetorComercial setorComercial = (SetorComercial) iteratorSetores
									.next();

							FiltroContaMensagem filtroContaMensagem = new FiltroContaMensagem();

							filtroContaMensagem
									.adicionarParametro(new ParametroSimples(
											FiltroContaMensagem.ANO_MES_REFERECIA_FATURAMENTO,
											anoMesReferencia));

							filtroContaMensagem
									.adicionarParametro(new ParametroSimples(
											FiltroContaMensagem.SETOR_COMERCIAL_ID,
											setorComercial.getId()));

							Collection colecaoContaMensagem = this
									.getControladorUtil().pesquisar(
											filtroContaMensagem,
											ContaMensagem.class.getName());

							if (colecaoContaMensagem == null
									|| colecaoContaMensagem.isEmpty()) {
								colecaoRetorno.add(setorComercial);
							}
						}
					}
				}
			}
		}

		return colecaoRetorno;
	}

	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 *
	 * @author Flávio Leonardo
	 * @date 10/09/2008
	 *
	 * @param FiltrarRelatorioImoveisFaturasAtrasoHelper
	 *
	 * @return Collection<RelatorioImoveisSituacaoLigacaoAguaHelper>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisFaturasAtrasoHelper> pesquisarRelatorioImoveisFaturasAtrasoDescritasLocalizacao(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ControladorException {

		Collection<RelatorioImoveisFaturasAtrasoHelper> retorno = new ArrayList<RelatorioImoveisFaturasAtrasoHelper>();

		Collection<Object[]> colecaoPesquisa = null;

		try {
			colecaoPesquisa = this.repositorioCadastro
					.pesquisarRelatorioImoveisFaturasAtrasoDescritasLocalizacao(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {

			Iterator itera = colecaoPesquisa.iterator();

			while (itera.hasNext()) {
				Object[] objeto = (Object[]) itera.next();

				RelatorioImoveisFaturasAtrasoHelper helper = new RelatorioImoveisFaturasAtrasoHelper();

				Integer idImovel = (Integer) objeto[12];

				Integer localidade = (Integer) objeto[6];
				Integer codigoSetorComercial = (Integer) objeto[4];
				Integer numeroQuadra = (Integer) objeto[15];

				String cpf = (String) objeto[21];
				String cnpj = (String) objeto[22];

				helper.setMatriculaImovel(Util
						.retornaMatriculaImovelFormatada(idImovel));
				helper.setGerenciaRegional((Integer) objeto[0]);
				helper.setNomeGerenciaRegional((String) objeto[1]);
				helper.setUnidadeNegocio((Integer) objeto[2]);
				helper.setNomeUnidadeNegocio((String) objeto[3]);
				helper.setLocalidade(localidade);
				helper.setDescricaoLocalidade((String) objeto[7]);
				helper.setSetorComercial(codigoSetorComercial);
				helper.setDescricaoSetorComercial((String) objeto[5]);
				helper.setNomeClienteUsuario((String) objeto[8]);
				helper.setSituacaoLigacaoAgua((String) objeto[9]);
				helper.setSituacaoLigacaoEsgoto((String) objeto[14]);
				helper.setRota((Short) objeto[10]);
				helper.setSequencialRota((Integer) objeto[11]);
				helper.setQuantidadeFaturasAtraso(0);
				// helper.setQuantidadeFaturasAtraso( ( (Integer) objeto[17] )
				// );
				helper
						.setValorFaturasAtrasoSemEncargos((BigDecimal) objeto[17]);
				helper.setReferenciaFaturasAtrasoInicial((Integer) objeto[16]);
				// helper.setReferenciaFaturasAtrasoFinal( (Integer) objeto[19]
				// );
				helper.setReferenciaFaturasAtrasoFinal(0);
				helper.setVencimento((Date) objeto[20]);

				if (cpf != null && !cpf.equals("")) {
					helper.setCpfOuCnpjClienteUsuario(Util.formatarCpf(cpf));
				}

				if (cnpj != null && !cnpj.equals("")) {
					helper.setCpfOuCnpjClienteUsuario(Util.formatarCnpj(cnpj));
				}

				Imovel imovel = new Imovel();
				imovel.setId(idImovel);

				Localidade local = new Localidade();
				local.setId(localidade);
				imovel.setLocalidade(local);

				SetorComercial setorComercial = new SetorComercial();
				setorComercial.setCodigo(codigoSetorComercial);
				imovel.setSetorComercial(setorComercial);

				Quadra quadra = new Quadra();
				quadra.setNumeroQuadra(numeroQuadra);
				imovel.setQuadra(quadra);

				imovel.setLote((Short) objeto[18]);
				imovel.setSubLote((Short) objeto[19]);

				helper.setInscricaoImovel(imovel.getInscricaoFormatada());

				String endereco = this.getControladorEndereco()
						.pesquisarEnderecoFormatado(idImovel);
				helper.setEndereco(endereco);

				retorno.add(helper);
			}
		}

		return retorno;
	}

	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 *
	 * @since 02/09/2009
	 * @author Marlon Patrick
	 * @throws Exception
	 */
	public Collection<RelatorioImoveisFaturasAtrasoHelper> pesquisarRelatorioImoveisFaturasAtrasoDescritasCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ControladorException {

		Collection<RelatorioImoveisFaturasAtrasoHelper> retorno = new ArrayList<RelatorioImoveisFaturasAtrasoHelper>();

		Collection<Object[]> colecaoPesquisa = null;

		try {
			colecaoPesquisa = this.repositorioCadastro
					.pesquisarRelatorioImoveisFaturasAtrasoDescritasCliente(filtro);

			if (!Util.isVazioOrNulo(colecaoPesquisa)) {

				for (Object[] dadosRelatorio : colecaoPesquisa) {

					RelatorioImoveisFaturasAtrasoHelper helper = new RelatorioImoveisFaturasAtrasoHelper();

					helper.setIdCliente((Integer) dadosRelatorio[0]);
					helper.setNomeCliente((String) dadosRelatorio[1]);
					helper.setGerenciaRegional((Integer) dadosRelatorio[2]);
					helper.setLocalidade((Integer) dadosRelatorio[3]);
					helper.setSetorComercial((Integer) dadosRelatorio[4]);
					helper.setNumeroQuadra((Integer) dadosRelatorio[5]);
					helper.setRota((Short) dadosRelatorio[6]);
					helper.setSequencialRota((Integer) dadosRelatorio[7]);
					helper.setIdImovel((Integer) dadosRelatorio[8]);
					helper.setMatriculaImovel(Util
							.retornaMatriculaImovelFormatada(helper
									.getIdImovel()));
					helper.setSituacaoLigacaoAgua((String) dadosRelatorio[9]);
					helper
							.setSituacaoLigacaoEsgoto((String) dadosRelatorio[10]);
					helper.setIdConta((Integer) dadosRelatorio[11]);
					helper
							.setReferenciaFaturasAtrasoInicial((Integer) dadosRelatorio[12]);
					helper.setVencimento((Date) dadosRelatorio[13]);
					helper
							.setIndicadorCobrancaMultaConta((Short) dadosRelatorio[14]);
					helper
							.setValorFaturasAtrasoSemEncargos((BigDecimal) dadosRelatorio[15]);
					helper.setReferenciaFaturasAtrasoFinal(0);

					String endereco = this.getControladorEndereco()
							.pesquisarEnderecoFormatado(helper.getIdImovel());
					helper.setEndereco(endereco);

					configurarInscricaoImovelFormatada(helper);

					Cliente clienteUsuario = getControladorImovel()
							.consultarClienteUsuarioImovel(
									new Imovel(helper.getIdImovel()));
					helper.setNomeClienteUsuario(clienteUsuario.getNome());

					if (Util.verificarNaoVazio(clienteUsuario.getCnpj())) {
						helper.setCpfOuCnpjClienteUsuario(clienteUsuario
								.getCnpjFormatado());
					} else {
						helper.setCpfOuCnpjClienteUsuario(clienteUsuario
								.getCpfFormatado());
					}

					Conta conta = new Conta();
					conta.setId(helper.getIdConta());
					conta.setImovel(new Imovel(helper.getIdImovel()));
					conta.setReferencia(helper.getReferenciaFaturasAtrasoInicial());

					Object[] pagamentoContasMenorData = repositorioFaturamento.obterArrecadacaoFormaPagamentoContasMenorData(conta);

					Date dataPagamento = null;
					if (pagamentoContasMenorData != null) {
						dataPagamento = (Date) pagamentoContasMenorData[1];
					}

					BigDecimal valorMultasCobradas = repositorioFaturamento
							.pesquisarValorMultasCobradas(helper.getIdConta());

					SistemaParametro sistemaParametros = Fachada.getInstancia()
							.pesquisarParametrosDoSistema();

					CalcularAcrescimoPorImpontualidadeHelper valorHelper = getControladorCobranca()
							.calcularAcrescimoPorImpontualidade(
									helper.getReferenciaFaturasAtrasoFinal(),
									helper.getVencimento(),
									dataPagamento,
									helper.getValorFaturasAtrasoSemEncargos(),
									valorMultasCobradas,
									helper.getIndicadorCobrancaMultaConta(),
									sistemaParametros.getAnoMesArrecadacao()
											.toString(),
									helper.getIdConta(),
									ConstantesSistema.INDICADOR_ARRECADACAO_ATIVO);

					helper.setValorFaturasAtrasoComEncargos(valorHelper
							.getValorTotalAcrescimosImpontualidade().add(
									helper.getValorFaturasAtrasoSemEncargos()));

					retorno.add(helper);
				}
			}

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 * [UC0xxx] Inserir Unidade de Negocio
	 *
	 *
	 * @author Rômulo Aurélio
	 * @date 29/09/2008
	 *
	 *
	 * @return Integer
	 * @throws ControladorException
	 * @throws ControladorException
	 */

	public Integer inserirUnidadeNegocio(UnidadeNegocio unidadeNegocio,
			Usuario usuarioLogado) throws ControladorException {

		Integer retorno = null;

		if (unidadeNegocio.getNome() != null
				&& unidadeNegocio.getNome().equalsIgnoreCase("")) {
			throw new ControladorException("atencao.required", null, "Nome");

		}

		if (unidadeNegocio.getNomeAbreviado() != null
				&& unidadeNegocio.getNomeAbreviado().equalsIgnoreCase("")) {
			throw new ControladorException("atencao.required", null,
					"Nome Abreviado");

		}

		if (unidadeNegocio.getGerenciaRegional() == null
				|| unidadeNegocio.getGerenciaRegional().getId().toString()
						.equalsIgnoreCase("")) {
			throw new ControladorException("atencao.required", null,
					"Gerência Regional");
		}

		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();

		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
				FiltroUnidadeNegocio.NOME, unidadeNegocio.getNome()));

		// Pesquisa se existe algum funcionario com a matricula informada

		Collection colecaoUnidadeNegocioNome = new ArrayList();

		colecaoUnidadeNegocioNome = getControladorUtil().pesquisar(
				filtroUnidadeNegocio, UnidadeNegocio.class.getName());

		if (colecaoUnidadeNegocioNome != null
				&& !colecaoUnidadeNegocioNome.isEmpty()) {
			throw new ControladorException(
					"atencao.unidade_negocio_ja_existente");

		}

		unidadeNegocio.setUltimaAlteracao(new Date());

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INSERIR_UNIDADE_NEGOCIO,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		// MUDARRRRRRRRR
		operacao.setId(Operacao.OPERACAO_INSERIR_UNIDADE_NEGOCIO);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		unidadeNegocio.setOperacaoEfetuada(operacaoEfetuada);
		unidadeNegocio.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(unidadeNegocio);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		retorno = (Integer) getControladorUtil().inserir(unidadeNegocio);

		return retorno;

	}

	/**
	 * [UC0???] Atualizar Unidade de Negocio
	 *
	 *
	 * @author Rômulo Aurélio
	 * @date 29/09/2008
	 *
	 *
	 * @throws ControladorException
	 * @throws ControladorException
	 */

	public void atualizarUnidadeNegocio(UnidadeNegocio unidadeNegocio,
			Usuario usuarioLogado) throws ControladorException {

		if (unidadeNegocio.getNome() != null
				&& unidadeNegocio.getNome().equalsIgnoreCase("")) {
			throw new ControladorException("atencao.required", null, "Nome");

		}

		if (unidadeNegocio.getNomeAbreviado() != null
				&& unidadeNegocio.getNomeAbreviado().equalsIgnoreCase("")) {
			throw new ControladorException("atencao.required", null,
					"Nome Abreviado");

		}

		if (unidadeNegocio.getGerenciaRegional() == null
				|| unidadeNegocio.getGerenciaRegional().getId().toString()
						.equalsIgnoreCase("")) {
			throw new ControladorException("atencao.required", null,
					"Gerência Regional");
		}

		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();

		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
				FiltroUnidadeNegocio.NOME, unidadeNegocio.getNome()));

		// Pesquisa se existe algum funcionario com a matricula informada

		Collection colecaoUnidadeNegocioNome = new ArrayList();

		colecaoUnidadeNegocioNome = getControladorUtil().pesquisar(
				filtroUnidadeNegocio, UnidadeNegocio.class.getName());

		if (colecaoUnidadeNegocioNome != null
				&& !colecaoUnidadeNegocioNome.isEmpty()) {

			UnidadeNegocio unidadeNegocioBase = (UnidadeNegocio) colecaoUnidadeNegocioNome
					.iterator().next();

			if (unidadeNegocio.getId().intValue() != unidadeNegocioBase.getId()
					.intValue()) {

				throw new ControladorException(
						"atencao.unidade_negocio_ja_existente");
			}
		}

		unidadeNegocio.setUltimaAlteracao(new Date());

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INSERIR_UNIDADE_NEGOCIO,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		// MUDARRRRRRRRR
		operacao.setId(Operacao.OPERACAO_INSERIR_UNIDADE_NEGOCIO);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		unidadeNegocio.setOperacaoEfetuada(operacaoEfetuada);
		unidadeNegocio.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(unidadeNegocio);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		getControladorUtil().atualizar(unidadeNegocio);

	}

	/**
	 * [UC0789] Inserir Empresa
	 *
	 *
	 * @author Rômulo Aurélio
	 * @date 29/09/2008
	 *
	 *
	 * @return Integer
	 * @throws ControladorException
	 */

	public Integer inserirEmpresa(Empresa empresa,
			EmpresaContratoCobranca empresaCobranca, Usuario usuarioLogado,
			List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa)
			throws ControladorException {

		Integer retorno = null;

		if (empresa.getDescricao() != null
				&& empresa.getDescricao().equalsIgnoreCase("")) {
			throw new ControladorException("atencao.required", null,
					"Descrição");

		}

		if (empresa.getIndicadorEmpresaPrincipal() != null
				&& empresa.getIndicadorEmpresaPrincipal().toString()
						.equalsIgnoreCase("")) {
			throw new ControladorException("atencao.required", null,
					"Indicador de Empresa Principal");

		}

		if (empresa.getIndicadorEmpresaContratadaCobranca() != null
				&& empresa.getIndicadorEmpresaContratadaCobranca().toString()
						.equalsIgnoreCase("")) {
			throw new ControladorException("atencao.required", null,
					"Indicador de Empresa Cobrança");

		}

		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();

		filtroEmpresa.adicionarParametro(new ParametroSimples(
				FiltroEmpresa.DESCRICAO, empresa.getDescricao()));

		// Pesquisa se existe alguma empresa com a descricao informada

		Collection colecaoEmpresaDescricao = new ArrayList();

		colecaoEmpresaDescricao = getControladorUtil().pesquisar(filtroEmpresa,
				Empresa.class.getName());

		if (colecaoEmpresaDescricao != null
				&& !colecaoEmpresaDescricao.isEmpty()) {
			throw new ControladorException("atencao.empresa_ja_cadastrada",
					null, empresa.getDescricao());

		}

		// Ultima Alteracao
		empresa.setUltimaAlteracao(new Date());

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INSERIR_EMPRESA,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();

		operacao.setId(Operacao.OPERACAO_INSERIR_EMPRESA);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		empresa.setOperacaoEfetuada(operacaoEfetuada);
		empresa.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(empresa);
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		try {
			retorno = (Integer) getControladorUtil().inserir(empresa);
		} catch (ControladorException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		Integer idEmpresaCobranca = null;

		if (empresaCobranca != null) {

			empresaCobranca.setEmpresa(empresa);

			empresaCobranca.setUltimaAlteracao(new Date());

			// ------------ REGISTRAR TRANSAÇÃO ----------------

			empresaCobranca.setOperacaoEfetuada(operacaoEfetuada);
			empresaCobranca.adicionarUsuario(usuarioLogado,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(empresaCobranca);
			// ------------ REGISTRAR TRANSAÇÃO ----------------
			try {
				idEmpresaCobranca = (Integer) getControladorUtil().inserir(empresaCobranca);

			} catch (ControladorException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}


			if (colecaoEmpresaCobrancaFaixa != null && !colecaoEmpresaCobrancaFaixa.isEmpty()) {
				Iterator iterator = colecaoEmpresaCobrancaFaixa.iterator();
				EmpresaContratoCobranca  empresaContratoCobranca = new EmpresaContratoCobranca();
				empresaContratoCobranca.setId(idEmpresaCobranca);

				while(iterator.hasNext()) {
					EmpresaCobrancaFaixa empresaCobrancaFaixa = (EmpresaCobrancaFaixa) iterator.next();
					empresaCobrancaFaixa.setUltimaAlteracao(new Date());
					empresaCobrancaFaixa.setEmpresaContratoCobranca(empresaContratoCobranca);

					try {
						getControladorUtil().inserir(empresaCobrancaFaixa);

					} catch (ControladorException e) {
						sessionContext.setRollbackOnly();
						throw new ControladorException("erro.sistema", e);
					}

				}
			}
		}

		return retorno;

	}

	/**
	 * [UC0784] Manter Empresa
	 *
	 *
	 * @author Rômulo Aurélio
	 * @date 29/09/2008
	 *
	 *
	 * @throws ControladorException
	 */

	public void atualizarEmpresa(Empresa empresa,
			EmpresaContratoCobranca empresaCobrancaTela, Usuario usuarioLogado,
			List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa,
			List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixaRemover)
			throws ControladorException {

		if (empresa.getDescricao() != null
				&& empresa.getDescricao().equalsIgnoreCase("")) {
			throw new ControladorException("atencao.required", null,
					"Descrição");

		}

		if (empresa.getIndicadorEmpresaPrincipal() != null
				&& empresa.getIndicadorEmpresaPrincipal().toString()
						.equalsIgnoreCase("")) {
			throw new ControladorException("atencao.required", null,
					"Indicador de Empresa Principal");

		}

		if (empresa.getIndicadorEmpresaContratadaCobranca() != null
				&& empresa.getIndicadorEmpresaContratadaCobranca().toString()
						.equalsIgnoreCase("")) {
			throw new ControladorException("atencao.required", null,
					"Indicador de Empresa Cobrança");

		}

		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();

		filtroEmpresa.adicionarParametro(new ParametroSimples(
				FiltroEmpresa.DESCRICAO, empresa.getDescricao()));

		// Pesquisa se existe alguma empresa com a descricao informada

		Collection colecaoEmpresaDescricao = new ArrayList();

		colecaoEmpresaDescricao = getControladorUtil().pesquisar(filtroEmpresa,
				Empresa.class.getName());

		if (colecaoEmpresaDescricao != null
				&& !colecaoEmpresaDescricao.isEmpty()) {

			Empresa empresaBase = (Empresa) colecaoEmpresaDescricao.iterator()
					.next();

			if (empresa.getId().intValue() != empresaBase.getId().intValue()) {

				throw new ControladorException("atencao.empresa_ja_cadastrada",
						null, empresa.getDescricao());
			}
		}

		// Ultima Alteracao
		empresa.setUltimaAlteracao(new Date());

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_ATUALIZAR_EMPRESA,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();

		operacao.setId(Operacao.OPERACAO_ATUALIZAR_EMPRESA);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		empresa.setOperacaoEfetuada(operacaoEfetuada);
		empresa.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(empresa);
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		try {
			getControladorUtil().atualizar(empresa);
		} catch (ControladorException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}


		/*
		 * Remover registros de EmpresaCobrancaFaixa
		 * */
		if (colecaoEmpresaCobrancaFaixaRemover != null && !colecaoEmpresaCobrancaFaixaRemover.isEmpty()) {
			Iterator iterator = colecaoEmpresaCobrancaFaixaRemover.iterator();

			while(iterator.hasNext()) {
				EmpresaCobrancaFaixa empresaCobrancaFaixa = (EmpresaCobrancaFaixa) iterator.next();
				try {
					getControladorUtil().remover(empresaCobrancaFaixa);

				} catch (ControladorException e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}
			}
		}


		FiltroEmpresaContratoCobranca filtroEmpresaCobranca = new FiltroEmpresaContratoCobranca();

		filtroEmpresaCobranca.adicionarParametro(new ParametroSimples(
				FiltroEmpresaContratoCobranca.EMPRESA_ID, empresa.getId()));

		Collection colecaoEmpesaCobrancaBase = getControladorUtil().pesquisar(
				filtroEmpresaCobranca, EmpresaContratoCobranca.class.getName());

		EmpresaContratoCobranca empresaCobrancaBase = null;

		if (colecaoEmpesaCobrancaBase != null
				&& !colecaoEmpesaCobrancaBase.isEmpty()) {

			empresaCobrancaBase = (EmpresaContratoCobranca) colecaoEmpesaCobrancaBase
					.iterator().next();
		}

		// Caso o usuario tenha atualizado os dados de cobranca na tela,
		// atualiza a tabela EmpresaCobranca
		if (empresaCobrancaTela != null && empresaCobrancaBase != null) {

			empresaCobrancaTela.setId(empresaCobrancaBase.getId());

			empresaCobrancaTela.setUltimaAlteracao(new Date());

			// ------------ REGISTRAR TRANSAÇÃO ----------------

			// empresaCobranca.setOperacaoEfetuada(operacaoEfetuada);
			empresaCobrancaTela.adicionarUsuario(usuarioLogado,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(empresaCobrancaTela);
			// ------------ REGISTRAR TRANSAÇÃO ----------------
			try {
				getControladorUtil().atualizar(empresaCobrancaTela);
			} catch (ControladorException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}

			if (colecaoEmpresaCobrancaFaixa != null && !colecaoEmpresaCobrancaFaixa.isEmpty()) {
				Iterator iterator = colecaoEmpresaCobrancaFaixa.iterator();
				EmpresaContratoCobranca empresaContratoCobranca = new EmpresaContratoCobranca();
				empresaContratoCobranca.setId(empresaCobrancaBase.getId());

				while(iterator.hasNext()) {
					EmpresaCobrancaFaixa empresaCobrancaFaixa = (EmpresaCobrancaFaixa) iterator.next();

					if (empresaCobrancaFaixa.getId() == null
							&& empresaCobrancaFaixa.getUltimaAlteracao() == null) {

						empresaCobrancaFaixa.setEmpresaContratoCobranca(empresaContratoCobranca);
						empresaCobrancaFaixa.setUltimaAlteracao(new Date());

						try {
							this.getControladorUtil().inserir(empresaCobrancaFaixa);

						} catch (ControladorException e) {
							sessionContext.setRollbackOnly();
							throw new ControladorException("erro.sistema", e);
						}
					}

				}
			}

		} /*
			 * Caso o usuario tenha informado os dados de cobranca na tela, e na
			 * base nao tenha dados da tabela EmpresaCobranca para a empresa a
			 * ser atualizada, insere os dados de cobranca em EmpresaCobranca
			 */
		else if (empresaCobrancaTela != null && empresaCobrancaBase == null) {

			Integer idEmpresaCobranca = null;

			empresaCobrancaTela.setUltimaAlteracao(new Date());

			// ------------ REGISTRAR TRANSAÇÃO ----------------

			// empresaCobranca.setOperacaoEfetuada(operacaoEfetuada);
			empresaCobrancaTela.adicionarUsuario(usuarioLogado,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(empresaCobrancaTela);
			// ------------ REGISTRAR TRANSAÇÃO ----------------
			try {
				idEmpresaCobranca = (Integer) getControladorUtil().inserir(empresaCobrancaTela);
			} catch (ControladorException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}

			if (colecaoEmpresaCobrancaFaixa != null && !colecaoEmpresaCobrancaFaixa.isEmpty()) {
				Iterator iterator = colecaoEmpresaCobrancaFaixa.iterator();
				EmpresaContratoCobranca empresaContratoCobranca = new EmpresaContratoCobranca();
				empresaContratoCobranca.setId(idEmpresaCobranca);

				while(iterator.hasNext()) {
					EmpresaCobrancaFaixa empresaCobrancaFaixa = (EmpresaCobrancaFaixa) iterator.next();

					if (empresaCobrancaFaixa.getId() == null
							&& empresaCobrancaFaixa.getUltimaAlteracao() == null) {

						empresaCobrancaFaixa.setEmpresaContratoCobranca(empresaContratoCobranca);
						empresaCobrancaFaixa.setUltimaAlteracao(new Date());

						try {
							getControladorUtil().inserir(empresaCobrancaFaixa);

						} catch (ControladorException e) {
							sessionContext.setRollbackOnly();
							throw new ControladorException("erro.sistema", e);
						}
					}

				}
			}
		} /*
			 * Caso o usuario nao tenha informado os dados de cobranca na tela,
			 * e na base os dados tenha dados da tabela EmpresaCobranca, remove
			 * os dados de cobranca em EmpresaCobranca
			 */

		else if (empresaCobrancaTela == null && empresaCobrancaBase != null) {

			if (colecaoEmpresaCobrancaFaixa != null && !colecaoEmpresaCobrancaFaixa.isEmpty()) {
				Iterator iterator = colecaoEmpresaCobrancaFaixa.iterator();

				while(iterator.hasNext()) {
					EmpresaCobrancaFaixa empresaCobrancaFaixa = (EmpresaCobrancaFaixa) iterator.next();

					if (empresaCobrancaFaixa.getId() != null
							&& empresaCobrancaFaixa.getUltimaAlteracao() != null) {
						try {
							getControladorUtil().remover(empresaCobrancaFaixa);

						} catch (ControladorException e) {
							sessionContext.setRollbackOnly();
							throw new ControladorException("erro.sistema", e);
						}
					}

				}
			}

			// ------------ REGISTRAR TRANSAÇÃO ----------------

			// empresaCobranca.setOperacaoEfetuada(operacaoEfetuada);
			empresaCobrancaBase.adicionarUsuario(usuarioLogado,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(empresaCobrancaBase);
			// ------------ REGISTRAR TRANSAÇÃO ----------------
			try {
				getControladorUtil().remover(empresaCobrancaBase);
			} catch (ControladorException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}
		}

	}

	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular
	 *
	 * @author Vinicius Medeiros
	 * @date 25/08/2008
	 */
	public ImovelAtualizacaoCadastral obterImovelGeracaoTabelasTemporarias(Integer idImovel) throws ControladorException {
		ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = null;

		try {
			imovelAtualizacaoCadastral = repositorioCadastro.obterImovelGeracaoTabelasTemporarias(idImovel);

			if (imovelAtualizacaoCadastral != null) {
			    LogradouroTO logradouro = getControladorEndereco().pesquisarLogradouro(idImovel);

				if (logradouro != null) {
				    imovelAtualizacaoCadastral.setIdLogradouro(logradouro.getId());
					imovelAtualizacaoCadastral.setDescricaoLogradouro(logradouro.getNome());
					imovelAtualizacaoCadastral.setIdLogradouroTipo(logradouro.getIdTipo());
					imovelAtualizacaoCadastral.setDsLogradouroTipo(logradouro.getDescricaoTipo());
					imovelAtualizacaoCadastral.setIdLogradouroTitulo(logradouro.getIdTitulo());
					imovelAtualizacaoCadastral.setDsLogradouroTitulo(logradouro.getDescricaoTitulo());
					imovelAtualizacaoCadastral.setIdMunicipio(logradouro.getIdMunicipio());
					imovelAtualizacaoCadastral.setNomeMunicipio(logradouro.getDescricaoMunicipio());
					imovelAtualizacaoCadastral.setIdUinidadeFederacao(logradouro.getIdUf());
					imovelAtualizacaoCadastral.setDsUFSiglaMunicipio(logradouro.getSiglaUf());
				}

				Object[] hidrometro = getControladorMicromedicao().obterDadosHidrometroAtualizacaoCadastral(idImovel);

				if (hidrometro != null) {
					if (hidrometro[0] != null) {
						imovelAtualizacaoCadastral.setNumeroLeituraInstalacaoHidrometro((Integer) hidrometro[0]);
					}

					if (hidrometro[1] != null) {
						imovelAtualizacaoCadastral.setIdCapacidadeHidrometro((Integer) hidrometro[1]);
					}

					if (hidrometro[2] != null) {
						imovelAtualizacaoCadastral.setIdMarcaHidrometro((Integer) hidrometro[2]);
					}

					if (hidrometro[3] != null) {
						imovelAtualizacaoCadastral.setIdLocalInstalacaoHidrometro((Integer) hidrometro[3]);
					}

					if (hidrometro[4] != null) {
						imovelAtualizacaoCadastral.setIdProtecaoHidrometro((Integer) hidrometro[4]);
					}

					if (hidrometro[5] != null) {
						imovelAtualizacaoCadastral.setIndicadorCavalete((Short) hidrometro[5]);
					}

					if (hidrometro[6] != null) {
						imovelAtualizacaoCadastral.setNumeroHidrometro((String) hidrometro[6]);
					}
				}
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return imovelAtualizacaoCadastral;

	}

	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular
	 *
	 * @author Vinicius Medeiros
	 * @date 25/08/2008
	 */
	public void gerarTabelasTemporariasAtualizacaoCadastral(Integer idRota,
			Integer idFuncionalidadeIniciada,
			ImovelGeracaoTabelasTemporariasCadastroHelper helper)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		try {
			idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(
					idFuncionalidadeIniciada, UnidadeProcessamento.ROTA, idRota);

			Collection colecaoIdsImovel = repositorioCadastro.obterIdsImovelGeracaoTabelasTemporarias(idRota, helper);

			if (helper.getImovelSituacao() != null && new Integer(helper.getImovelSituacao()) == 2) {
				colecaoIdsImovel = repositorioCadastro.pesquisarImovelDebitoAtualizacaoCadastral(colecaoIdsImovel);
			}

			IClienteAtualizacaoCadastral clienteAtualizacaoCadastralProprietario = null;
			IClienteAtualizacaoCadastral clienteAtualizacaoCadastralUsuario = null;
			IClienteAtualizacaoCadastral clienteAtualizacaoCadastralResposavel = null;

			Iterator iteratorImovel = colecaoIdsImovel.iterator();
			while (iteratorImovel.hasNext()) {
				Integer idImovel = (Integer) iteratorImovel.next();

				ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = obterImovelGeracaoTabelasTemporarias(idImovel);

				if (imovelAtualizacaoCadastral.getIdImovel() != null) {

					if (!imovelJaExisteImovelAtualizacaoCadastral(imovelAtualizacaoCadastral.getIdImovel())) {

						// Imovel
						imovelAtualizacaoCadastral.setIdSituacaoAtualizacaoCadastral(SituacaoAtualizacaoCadastral.DISPONIVEL);
						imovelAtualizacaoCadastral.setIdEmpresa(new Integer(helper.getFirma()));
						imovelAtualizacaoCadastral.setUltimaAlteracao(new Date());

						getControladorUtil().inserir(imovelAtualizacaoCadastral);

						// Imovel Subcategoria
						Collection imovelSubcategorias = repositorioCadastro.obterImovelSubcategoriaAtualizacaoCadastral(idImovel);

						for (Object item: imovelSubcategorias) {
							ImovelSubcategoriaAtualizacaoCadastral imovSubAtual = (ImovelSubcategoriaAtualizacaoCadastral) item;
							imovSubAtual.setUltimaAlteracao(new Date());
							getControladorUtil().inserir(imovSubAtual);
						}

						Collection<ImovelTipoOcupanteQuantidadeAtualizacaoCadastral> quantidadesTiposOcupantes = repositorioCadastro.obterQuantidadesTiposOcupantesParaAtualizacaoCadastral(idImovel);
						for (ImovelTipoOcupanteQuantidadeAtualizacaoCadastral item : quantidadesTiposOcupantes) {
                            item.setUltimaAlteracao(new Date());
                            getControladorUtil().inserir(item);
                        }

						Collection imovelRamoAtividade = obterImovelRamoAtividadeAtualizacaoCadastral(idImovel);
						for (Object item: imovelRamoAtividade) {
							ImovelRamoAtividadeAtualizacaoCadastral imovRamoAtividade =	(ImovelRamoAtividadeAtualizacaoCadastral) item;
							imovRamoAtividade.setUltimaAlteracao(new Date());
							getControladorUtil().inserir(imovRamoAtividade);
						}

						// Cliente Usuario
						clienteAtualizacaoCadastralUsuario = getControladorCliente().obterClienteAtualizacaoCadastral(idImovel, ClienteRelacaoTipo.USUARIO);

						if (clienteAtualizacaoCadastralUsuario != null) {

							clienteAtualizacaoCadastralUsuario.setUltimaAlteracao(new Date());
							Integer idClienteAtualizacaoCadastral = (Integer) getControladorUtil().inserir(
									clienteAtualizacaoCadastralUsuario);

							Collection clienteFonesAtualizacaoCadastral = getControladorCliente().obterDadosClienteFone(
									clienteAtualizacaoCadastralUsuario.getIdCliente());

							if (clienteFonesAtualizacaoCadastral != null
									&& !clienteFonesAtualizacaoCadastral.isEmpty()) {

								Iterator iteratorClienteFonesAtualizacaoCadastral = clienteFonesAtualizacaoCadastral.iterator();

								while (iteratorClienteFonesAtualizacaoCadastral.hasNext()) {
									ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral =
											(ClienteFoneAtualizacaoCadastral) iteratorClienteFonesAtualizacaoCadastral.next();
									clienteFoneAtualizacaoCadastral.setIdClienteAtualizacaoCadastral(idClienteAtualizacaoCadastral);
									clienteFoneAtualizacaoCadastral.setUltimaAlteracao(new Date());
									getControladorUtil().inserir(clienteFoneAtualizacaoCadastral);
								}
							}
						}

						clienteAtualizacaoCadastralResposavel = getControladorCliente().obterClienteAtualizacaoCadastral(
								idImovel, ClienteRelacaoTipo.RESPONSAVEL);

						if (clienteAtualizacaoCadastralResposavel != null) {

							clienteAtualizacaoCadastralResposavel.setUltimaAlteracao(new Date());
							Integer idClienteAtualizacaoCadastral = (Integer) getControladorUtil().inserir(
									clienteAtualizacaoCadastralResposavel);

							Collection clienteFonesAtualizacaoCadastral = getControladorCliente().obterDadosClienteFone(
									clienteAtualizacaoCadastralResposavel.getIdCliente());

							if (clienteFonesAtualizacaoCadastral != null
									&& !clienteFonesAtualizacaoCadastral.isEmpty()) {

								Iterator iteratorClienteFonesAtualizacaoCadastral = clienteFonesAtualizacaoCadastral.iterator();

								while (iteratorClienteFonesAtualizacaoCadastral.hasNext()) {

									ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral =
											(ClienteFoneAtualizacaoCadastral) iteratorClienteFonesAtualizacaoCadastral.next();
									clienteFoneAtualizacaoCadastral.setIdClienteAtualizacaoCadastral(idClienteAtualizacaoCadastral);
									clienteFoneAtualizacaoCadastral.setUltimaAlteracao(new Date());
									getControladorUtil().inserir(clienteFoneAtualizacaoCadastral);
								}
							}
						}

						// Cliente Proprietario
						clienteAtualizacaoCadastralProprietario = getControladorCliente().obterClienteAtualizacaoCadastral(
								idImovel, ClienteRelacaoTipo.PROPRIETARIO);

						if (clienteAtualizacaoCadastralProprietario != null) {

							clienteAtualizacaoCadastralProprietario.setUltimaAlteracao(new Date());
							Integer idClienteAtualizacaoCadastral = (Integer) getControladorUtil().inserir(
									clienteAtualizacaoCadastralProprietario);

							// Cliente Fone Proprietario
							Collection clienteFonesAtualizacaoCadastral = getControladorCliente().obterDadosClienteFone(
									clienteAtualizacaoCadastralProprietario.getIdCliente());

							if (clienteFonesAtualizacaoCadastral != null
									&& !clienteFonesAtualizacaoCadastral.isEmpty()) {

								Iterator iteratorClienteFonesAtualizacaoCadastral = clienteFonesAtualizacaoCadastral.iterator();

								while (iteratorClienteFonesAtualizacaoCadastral.hasNext()) {
									ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral =
											(ClienteFoneAtualizacaoCadastral) iteratorClienteFonesAtualizacaoCadastral.next();

									clienteFoneAtualizacaoCadastral.setIdClienteAtualizacaoCadastral(idClienteAtualizacaoCadastral);
									clienteFoneAtualizacaoCadastral.setUltimaAlteracao(new Date());
									getControladorUtil().inserir(clienteFoneAtualizacaoCadastral);
								}
							}
						}

						inserirImovelControleAtualizacaoCadastral(idImovel);
						getControladorImovel().atualizarImovelSituacaoAtualizacaoCadastral(
								idImovel, SituacaoAtualizacaoCadastral.BLOQUEADO);
					}
				}
			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}

	public void inserirImovelControleAtualizacaoCadastral(Integer idImovel) {
		ImovelControleAtualizacaoCadastral imovelControleAtualizacaoCadastral = new ImovelControleAtualizacaoCadastral();
		imovelControleAtualizacaoCadastral.setDataGeracao(new Date());
		imovelControleAtualizacaoCadastral.setImovel(new Imovel(idImovel));
		imovelControleAtualizacaoCadastral.setSituacaoAtualizacaoCadastral(new SituacaoAtualizacaoCadastral(SituacaoAtualizacaoCadastral.DISPONIVEL));

		try {
			getControladorUtil().inserir(imovelControleAtualizacaoCadastral);
		} catch (ControladorException e) {
			e.printStackTrace();
		}
	}

	// Método para verificar se já existe o Cliente no banco
	public Boolean clienteJaExisteClienteAtualizacaoCadastral(Integer idCliente)
			throws ControladorException {

		Boolean retorno = false;
		Integer idClienteAtualizacaoCadastral = getControladorCliente()
				.verificaExistenciaClienteAtualizacaoCadastral(idCliente);

		if (idClienteAtualizacaoCadastral != null) {
			retorno = true;
		}

		return retorno;
	}

	// Método para verificar se já existe o Imóvel no banco
	public Boolean imovelJaExisteImovelAtualizacaoCadastral(Integer idImovel)
			throws ControladorException {

		Boolean retorno = false;
		Integer idImovelAtualizacaoCadastral = getControladorImovel()
				.verificaExistenciaImovelAtualizacaoCadastral(idImovel);

		if (idImovelAtualizacaoCadastral != null) {
			retorno = true;
		}

		return retorno;
	}

	/**
	 * Gerar Arquivo Texto da Atualização Cadastral para Dispositivo Móvel
	 *
	 * @param helper
	 *
	 * @author Ana Maria
	 * @date 17/09/2008
	 * @exception ControladorException
	 * @throws ErroRepositorioException
	 */
	public void gerarArquivoTextoAtualizacaoCadastralDispositivoMovel(
			Integer idFuncionalidadeIniciada,
			ImovelGeracaoTabelasTemporariasCadastroHelper helper,
			Integer idRota) throws ControladorException {

		int idUnidadeIniciada = 0;

		try {
			idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, UnidadeProcessamento.ROTA, idRota);

			Leiturista leiturista = getLeituristaAtualizacaoCadastral(Integer.parseInt(helper.getLeiturista()));

			List<Integer> idsImoveis = (List<Integer>) repositorioCadastro.pesquisarIdsImoveisAtualizacaoCadastral(leiturista.getEmpresa().getId(), idRota);

			if (idsImoveis == null || idsImoveis.isEmpty()) {
				System.out.println("Nenhum imovel encontrado. ARQUIVO NAO GERADO");
				getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
			} else {
				if (helper.getQuantidadeMaxima() != null && helper.getQuantidadeMaxima() > 0) {
					List<List<Integer>> partes = Util.quebrarListaEmPartes(idsImoveis, helper.getQuantidadeMaxima());

					for (List<Integer> parte : partes) {
						montarArquivoTextoAtualizacaoCadastral(idRota, leiturista, parte);
					}
				} else {
					montarArquivoTextoAtualizacaoCadastral(idRota, leiturista, idsImoveis);
				}

				getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}

	private void montarArquivoTextoAtualizacaoCadastral(Integer idRota, Leiturista leiturista, List<Integer> idsImoveis) throws ControladorException, IOException {
		Rota rota = getControladorMicromedicao().pesquisarRota(idRota);
		SetorComercial setor = rota.getSetorComercial();
		Localidade localidade = setor.getLocalidade();

		ArquivoTextoAtualizacaoCadastral arquivo = new ArquivoTextoAtualizacaoCadastral();
		arquivo.setSituacaoTransmissaoLeitura(new SituacaoTransmissaoLeitura(SituacaoTransmissaoLeitura.LIBERADO));
		arquivo.setLocalidade(localidade);
		arquivo.setCodigoSetorComercial(new Integer(setor.getCodigo()));
		arquivo.setRota(rota);
		arquivo.setDescricaoArquivo(getDescricaoArquivo(rota, setor, localidade));
		arquivo.setLeiturista(leiturista);
		arquivo.setQuantidadeImovel(idsImoveis.size());
		arquivo.setUltimaAlteracao(new Date());
		arquivo.setArquivoTexto(IoUtil.transformarObjetoParaBytes(new StringBuilder()));

		Integer idArquivoTexto = (Integer) getControladorUtil().inserir(arquivo);
		StringBuilder arquivoTexto = gerarArquivoTxt(idsImoveis, idArquivoTexto, leiturista, rota);

		arquivo.setArquivoTexto(IoUtil.transformarObjetoParaBytes(arquivoTexto));
		getControladorUtil().atualizar(arquivo);
	}

	private String getDescricaoArquivo(Rota rota, SetorComercial setor, Localidade localidade) throws ControladorException {
		String descricao = Util.adicionarZerosEsquedaNumero(3, localidade.getId() + "")
				+ "_" + Util.adicionarZerosEsquedaNumero(3, setor.getCodigo() + "")
				+ "_" + Util.adicionarZerosEsquedaNumero(2, rota.getCodigo() + "");

		descricao = adicionarParteDescricaoArquivo(rota, setor, localidade, descricao);
		return descricao;
	}

	private String adicionarParteDescricaoArquivo(Rota rota, SetorComercial setor, Localidade localidade, String descricao) throws ControladorException {
		Filtro filtro = new FiltroArquivoTextoAtualizacaoCadastral();
		filtro.adicionarParametro(new ParametroSimples(FiltroArquivoTextoAtualizacaoCadastral.LOCALIDADE_ID, localidade.getId()));
		filtro.adicionarParametro(new ParametroSimples(FiltroArquivoTextoAtualizacaoCadastral.SETOR_COMERCIAL_CODIGO, setor.getCodigo()));
		filtro.adicionarParametro(new ParametroSimples(FiltroArquivoTextoAtualizacaoCadastral.ROTA_ID, rota.getId()));

		Collection colecao = getControladorUtil().pesquisar(filtro, ArquivoTextoAtualizacaoCadastral.class.getName());
		if (colecao != null && !colecao.isEmpty()) {
			int ordem = colecao.size() + 1;
			descricao += "-pt" + ordem;
		}

		return descricao;
	}

	/**
	 * Gerar Arquivo Texto para Atualização Cadastral
	 */
	private StringBuilder gerarArquivoTxt(Collection colecaoImovelFiltrado, Integer idArquivoTexto, Leiturista leiturista, Rota rota) throws ControladorException {
		try {
			StringBuilder arquivoTexto = new StringBuilder();
			Iterator imovelFiltradoIterator = colecaoImovelFiltrado.iterator();

			Integer qtdRegistro = 0;

			while (imovelFiltradoIterator.hasNext()) {

				Integer idImovel = (Integer) imovelFiltradoIterator.next();

				Collection colecaoClienteImovel = repositorioClienteImovel.pesquisarClienteImovelAtualizacaoCadastral(idImovel);

				// REGISTRO_TIPO_01(Dados do cliente)
				arquivoTexto.append(this.gerarArquivoTextoRegistroTipoCliente(colecaoClienteImovel, idImovel));
				qtdRegistro = qtdRegistro + 1;

				// REGISTRO_TIPO_02(Dados do imóvel)
				arquivoTexto.append(this.gerarArquivoTextoRegistroTipoImovel(idImovel));
				qtdRegistro = qtdRegistro + 1;

				Collection<ImovelRamoAtividade> colecaoImovelRamoAtividade = getControladorImovel().pesquisarRamoAtividadeDoImovel(idImovel);

				if (colecaoImovelRamoAtividade != null && !colecaoImovelRamoAtividade.isEmpty()) {
					// REGISTRO_TIPO_03(Ramos de Atividade do Imovel)
					arquivoTexto.append(this.gerarArquivoTextoRegistroTipoRamoAtividadeImovel(colecaoImovelRamoAtividade, idImovel));
					qtdRegistro = qtdRegistro + colecaoImovelRamoAtividade.size();
				}

				ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = getControladorAtualizacaoCadastral().pesquisarImovelAtualizacaoCadastral(idImovel);
				Imovel imovel = getControladorImovel().pesquisarImovel(idImovel);

				// REGISTRO_TIPO_04 (Dados Serviços)
				arquivoTexto.append(new GeradorRegistroServicos(imovelAtualizacaoCadastral, imovel).build());
				qtdRegistro = qtdRegistro + 1;

				// REGISTRO_TIPO_05 (Dados Medidor)
				arquivoTexto.append(new GeradorRegistroHidromedro(imovelAtualizacaoCadastral, imovel).build());
				qtdRegistro = qtdRegistro + 1;

				// Registro_Tipo_06 (Localização)
				arquivoTexto.append(new GeradorRegistroLocalizacao(idImovel).build());
				qtdRegistro = qtdRegistro + 1;

				getControladorImovel().atualizarImovelAtualizacaoCadastralSituacaoAtualizacaoCadastral(idImovel, SituacaoAtualizacaoCadastral.EM_CAMPO, null);
				getControladorImovel().atualizarIdArquivoTextoImovelAtualizacaoCadastral(idArquivoTexto, idImovel);
			}

			// Trailer
			Object[] arquivoTrailerEQuantidadeTotalDeLinhas = this.gerarArquivoTextoRegistroTipoTrailer(qtdRegistro, rota, ArquivoTextoAtualizacaoCadastral.TIPO_ARQUIVO_TRANSMISSAO);
			arquivoTexto.append((StringBuilder) arquivoTrailerEQuantidadeTotalDeLinhas[0]);

			StringBuilder arquivoTextoFinal = new StringBuilder();
			// Quantidade Total de Linhas
			arquivoTextoFinal.append((Integer) arquivoTrailerEQuantidadeTotalDeLinhas[1]);
			arquivoTextoFinal.append(System.getProperty("line.separator"));
			arquivoTextoFinal.append(arquivoTexto);

			return arquivoTextoFinal;

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public ArquivoTextoAtualizacaoCadastral regerarArquivoTextoAtualizacaoCadastral(List<Integer> idsImoveis, Integer idArquivoTexto, String tipoArquivo) throws ControladorException {
		try {
			StringBuilder builder = new StringBuilder();
			int qtdRegistro = 0;

			ArquivoTextoAtualizacaoCadastral arquivo = pesquisarArquivoTextoAtualizacaoCadastro(idArquivoTexto);

			for (Integer idImovel : idsImoveis) {
				builder.append(this.gerarArquivoTextoRegistroTipoCliente(repositorioClienteImovel.pesquisarClienteImovelAtualizacaoCadastral(idImovel), idImovel));
				qtdRegistro += 1;

				builder.append(this.gerarArquivoTextoRegistroTipoImovel(idImovel));
				qtdRegistro += 1;

				Collection<ImovelRamoAtividade> colecaoImovelRamoAtividade = getControladorImovel().pesquisarRamoAtividadeDoImovel(idImovel);
				if (!Util.isVazioOrNulo(colecaoImovelRamoAtividade)) {
					builder.append(this.gerarArquivoTextoRegistroTipoRamoAtividadeImovel(colecaoImovelRamoAtividade, idImovel));
					qtdRegistro += colecaoImovelRamoAtividade.size();
				}

				ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = getControladorAtualizacaoCadastral().pesquisarImovelAtualizacaoCadastral(idImovel);
				ImovelControleAtualizacaoCadastral imovelControle =  getControladorAtualizacaoCadastral().pesquisarImovelControleAtualizacao(idImovel);
				if (imovelControle != null && ArquivoTextoAtualizacaoCadastral.TIPO_ARQUIVO_REVISITA.equalsIgnoreCase(tipoArquivo)) {
					imovelControle.setSituacaoAtualizacaoCadastral(new SituacaoAtualizacaoCadastral(SituacaoAtualizacaoCadastral.REVISITA));
					getControladorUtil().atualizar(imovelControle);
				}

				Imovel imovel = getControladorImovel().pesquisarImovel(idImovel);

				builder.append(new GeradorRegistroServicos(imovelAtualizacaoCadastral, imovel).build());
				qtdRegistro += 1;

				builder.append(new GeradorRegistroHidromedro(imovelAtualizacaoCadastral, imovel).build());
				qtdRegistro += 1;

				builder.append(new GeradorRegistroLocalizacao(idImovel).build());
				qtdRegistro += 1;
			}

			Object[] trailler = gerarArquivoTextoRegistroTipoTrailer(qtdRegistro, arquivo.getRota(), tipoArquivo);
			builder.append((StringBuilder) trailler[0]);

			StringBuilder arquivoTexto = new StringBuilder();
			arquivoTexto.append((Integer) trailler[1]);
			arquivoTexto.append(System.getProperty("line.separator"));
			arquivoTexto.append(builder);

			byte[] bytes = IoUtil.transformarObjetoParaBytes(arquivoTexto);
			arquivo.setArquivoTexto(bytes);
			File file = new File(arquivo.getDescricaoArquivo() + ".txt");
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsolutePath())));
			out.write(arquivoTexto.toString());
			out.flush();
			out.close();

			return arquivo;
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		} catch (FileNotFoundException e) {
			throw new ControladorException("erro.sistema", e);
		} catch (IOException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public AtualizacaoCadastral carregarImovelAtualizacaoCadastral(
			BufferedReader buffer, List<String> imagens) throws Exception {

		AtualizacaoCadastral atualizacao = new AtualizacaoCadastral();
		atualizacao.setImagens(imagens);

		try {
			String linha = null;

			while ((linha = buffer.readLine()) != null) {
				ParserUtil parserTipo = new ParserUtil(linha);
				String registroTipo = parserTipo.obterDadoParserTrim(2);

				String conteudo = new String(linha.substring(2).getBytes(), "UTF-8");
				ParserUtil parserConteudo = new ParserUtil(conteudo);

				if ("00".equals(registroTipo)) {
					AbstractAtualizacaoCadastralCommand command = new ParseHeaderCommand(
							parserConteudo,
							repositorioCadastro,
							getControladorUtil(),
							getControladorTransacao(),
							repositorioImovel,
							getControladorEndereco(),
							getControladorAtualizacaoCadastral(),
							getControladorCliente());
					command.execute(atualizacao);

				} else if ("01".equals(registroTipo)) {
					atualizacao.novaAtualizacaoImovel();

					AbstractAtualizacaoCadastralCommand command = new ParseClienteCommand(
							parserConteudo,
							getControladorUtil(),
							repositorioImovel,
							getControladorAtualizacaoCadastral());
					command.execute(atualizacao);
				}

				if (!atualizacao.getImovelAtual().isImovelAprovado()) {

					if ("02".equals(registroTipo)) {
						AbstractAtualizacaoCadastralCommand command = new ParseImovelCommand(
								parserConteudo,
								getControladorUtil(),
								getControladorAtualizacaoCadastral(),
								repositorioImovel);
						command.execute(atualizacao);

					} else if ("03".equals(registroTipo)) {
						AbstractAtualizacaoCadastralCommand command = new ParseRamoAtividadeCommand(
								parserConteudo,
								repositorioCadastro,
								getControladorUtil(),
								getControladorTransacao(),
								repositorioImovel,
								getControladorEndereco(),
								getControladorAtualizacaoCadastral(),
								getControladorCliente());
						command.execute(atualizacao);

					} else if ("04".equals(registroTipo)) {
						AbstractAtualizacaoCadastralCommand command = new ParseServicosCommand(
								parserConteudo,
								repositorioCadastro,
								getControladorUtil(),
								getControladorTransacao(),
								repositorioImovel,
								getControladorEndereco(),
								getControladorAtualizacaoCadastral(),
								getControladorCliente());
						command.execute(atualizacao);

					} else if ("05".equals(registroTipo)) {
						AbstractAtualizacaoCadastralCommand command = new ParseMedidorCommand(
								parserConteudo,
								repositorioCadastro,
								getControladorUtil(),
								getControladorTransacao(),
								repositorioImovel,
								getControladorEndereco(),
								getControladorAtualizacaoCadastral(),
								getControladorCliente());
						command.execute(atualizacao);

					} else if ("06".equals(registroTipo)) {
						AbstractAtualizacaoCadastralCommand command = new ParseAnormalidadeCommand(
								parserConteudo,
								repositorioCadastro,
								getControladorUtil(),
								getControladorTransacao(),
								repositorioImovel,
								getControladorEndereco(),
								getControladorAtualizacaoCadastral(),
								getControladorCliente());
						command.execute(atualizacao);

						atualizacao.liberarValidacao();
					}
				}

				if (atualizacao.validacaoLiberada()) {
					AbstractAtualizacaoCadastralCommand validaCommand = new EfetuarValidacoesAtualizacaoCadastralCommand(
								parserConteudo,
								repositorioCadastro,
								getControladorUtil(),
								getControladorTransacao(),
								repositorioImovel,
								getControladorEndereco(),
								getControladorAtualizacaoCadastral(),
								getControladorCliente(),
								repositorioClienteImovel);
					validaCommand.execute(atualizacao);
				}

				if (atualizacao.validacaoLiberada() && !atualizacao.getImovelAtual().cadastroInvalido()) {
					AbstractAtualizacaoCadastralCommand command = new MontarObjetosAtualizacaoCadastralCommand(
							parserConteudo,
							repositorioCadastro,
							getControladorUtil(),
							getControladorTransacao(),
							repositorioImovel,
							getControladorEndereco(),
							getControladorAtualizacaoCadastral(),
							getControladorCliente(),
							repositorioClienteImovel);
					command.execute(atualizacao);


					AbstractAtualizacaoCadastralCommand retornoCommand = new MontarObjetosRetornoCommand(
							parserConteudo,
							repositorioCadastro,
							getControladorUtil(),
							getControladorTransacao(),
							repositorioImovel,
							getControladorEndereco(),
							getControladorAtualizacaoCadastral(),
							getControladorCliente(),
							repositorioClienteImovel);
					retornoCommand.execute(atualizacao);

					atualizacao.excluirImovelSemErros();
				}
			}

			this.excluirImagemImoveisComErro(atualizacao);

			Integer quantidadeImoveisTransmitidos = repositorioCadastro.pesquisarQuantidadeImoveisTransmitidosAtualizacaoCadastral(atualizacao.getArquivoTexto().getId());

			if (quantidadeImoveisTransmitidos.compareTo(atualizacao.getArquivoTexto().getQuantidadeImovel()) == 0) {
				repositorioCadastro.atualizarArquivoTextoAtualizacaoCadstral(atualizacao.getArquivoTexto().getId(),
						SituacaoTransmissaoLeitura.TRANSMITIDO);
			}
		} catch (Exception e) {
		    logger.error("Erro ao carregar arquivo de retorno de atualizacao cadastral", e);
			throw new Exception("Erro ao carregar arquivo de retorno de atualizacao cadastral", e);
		} finally {
			if (buffer != null) {
				try {
					buffer.close();
				} catch (Exception e) {}
			}
		}

		return atualizacao;
	}

	private void excluirImagemImoveisComErro(AtualizacaoCadastral atualizacao) throws Exception {

		List<AtualizacaoCadastralImovel> imoveisComErro = atualizacao.getImoveisComErro();

		for (AtualizacaoCadastralImovel imovelComErro : imoveisComErro) {
			Integer matricula = imovelComErro.getMatricula();

			for (String nomeImagem : atualizacao.getImagens()) {
				String caminhoJboss = System.getProperty("jboss.server.home.dir");
				String pasta = "/images/" + atualizacao.getArquivoTexto().getDescricaoArquivo();

				if (nomeImagem.contains(matricula.toString())) {
					File arquivo = new File(caminhoJboss + pasta, nomeImagem);
					arquivo.delete();
				}
			}
		}
	}

	/**
	 * Gerar Arquivo Texto para Atualização Cadastral
	 *
	 * Registro Tipo Header
	 *
	 * @author Ana Maria
	 * @date 11/05/2009
	 *
	 * @param imovel
	 * @throws ControladorException
	 */
	public StringBuilder gerarArquivoTextoRegistroTipoHeader(
			Integer idArquivoTexto) throws ControladorException {

		StringBuilder arquivoTextoRegistroTipoHeader = new StringBuilder();

		// TIPO DO REGISTRO
		arquivoTextoRegistroTipoHeader.append("00");

		// DATA DA GERAÇÃO
		arquivoTextoRegistroTipoHeader.append(Util
				.formatarDataSemBarraDDMMAAAA(new Date()));

		// ID DO ARQUIVO TEXTO ATUALIZACAO CADASTRAL
		arquivoTextoRegistroTipoHeader.append(Util.adicionarZerosEsquedaNumero(
				9, idArquivoTexto.toString()));

		arquivoTextoRegistroTipoHeader.append(System
				.getProperty("line.separator"));

		return arquivoTextoRegistroTipoHeader;
	}


	/**
	 * Gerar Arquivo Texto para Atualização Cadastral
	 *
	 * Registro Tipo 01 - Dados do imóvel
	 *
	 * @author Wellington Rocha
	 * @date 21/03/2012
	 *
	 * @param idImovel
	 * @throws ControladorException
	 */
	public StringBuilder gerarArquivoTextoRegistroTipoImovel(Integer idImovel) throws ControladorException {
		ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = getControladorAtualizacaoCadastral().pesquisarImovelAtualizacaoCadastral(idImovel);

		Imovel imovel = getControladorImovel().pesquisarImovel(idImovel);

		StringBuilder arquivoTextoRegistroTipoImovel = new StringBuilder();

		// TIPO DO REGISTRO (DADOS DO IMÓVEL)
		arquivoTextoRegistroTipoImovel.append("02");

		arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(9, imovelAtualizacaoCadastral.getIdImovel().toString()));

		// CÓDIGO CLIENTE (?)
		arquivoTextoRegistroTipoImovel.append(Util.completaString("", 30));
		arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(3, imovel.getLocalidade().getId().toString()));
		arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(3, imovel.getSetorComercial().getCodigo() + ""));
		arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(4, imovel.getQuadra().getNumeroQuadra() + ""));
		arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(4, imovel.getLote() + ""));
		arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(3, imovel.getSubLote() + ""));
		Rota rotaImovel = imovel.getQuadra().getRota();
		arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(2, rotaImovel.getCodigo().toString()));
		arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(2, imovel.getQuadraFace().getNumeroQuadraFace().toString()));
		arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(8, imovelAtualizacaoCadastral.getIdMunicipio().toString()));

		if (imovelAtualizacaoCadastral.getNumeroIptu() != null) {
			arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(31, imovelAtualizacaoCadastral.getNumeroIptu().toString()));
		} else {
			arquivoTextoRegistroTipoImovel.append(Util.completaString("", 31));
		}

		if (imovelAtualizacaoCadastral.getNumeroMedidorEnergia() != null
				&& !imovelAtualizacaoCadastral.getNumeroMedidorEnergia().equals("")) {
			arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(20, imovelAtualizacaoCadastral.getNumeroMedidorEnergia().toString()));
		} else {
			arquivoTextoRegistroTipoImovel.append(Util.completaString("", 20));
		}

		if(imovel.getNumeroPontosUtilizacao()!= null){
			arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(5, imovel.getNumeroPontosUtilizacao()+""));
		}else{
			arquivoTextoRegistroTipoImovel.append("00000");
		}

		if(imovel.getNumeroMorador() != null){
			arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(5, imovel.getNumeroMorador()+""));
		}else{
			arquivoTextoRegistroTipoImovel.append("00000");
		}

		if (imovelAtualizacaoCadastral.getIdLogradouroTipo() != null) {
			arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(2, imovelAtualizacaoCadastral.getIdLogradouroTipo().toString()));
		} else {
			arquivoTextoRegistroTipoImovel.append("00");
		}

		arquivoTextoRegistroTipoImovel.append(Util.completaString(imovelAtualizacaoCadastral.getDescricaoLogradouro(), 40));
		arquivoTextoRegistroTipoImovel.append(Util.completaString(imovelAtualizacaoCadastral.getNumeroImovel().trim(), 5));

		if (imovelAtualizacaoCadastral.getComplementoEndereco() != null
				&& !imovelAtualizacaoCadastral.getComplementoEndereco().equals("")) {
			arquivoTextoRegistroTipoImovel.append(Util.completaString(imovelAtualizacaoCadastral.getComplementoEndereco(), 25));
		} else {
			arquivoTextoRegistroTipoImovel.append(Util.completaString("", 25));
		}

		if (imovelAtualizacaoCadastral.getNomeBairro() != null && !imovelAtualizacaoCadastral.getNomeBairro().equals("")) {
			arquivoTextoRegistroTipoImovel.append(Util.completaString(imovelAtualizacaoCadastral.getNomeBairro(), 20));
		} else {
			arquivoTextoRegistroTipoImovel.append(Util.completaString("", 20));
		}

		if (imovelAtualizacaoCadastral.getCodigoCep() != null && !imovelAtualizacaoCadastral.getCodigoCep().equals("")) {
			arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(8, imovelAtualizacaoCadastral.getCodigoCep().toString()));
		} else {
			arquivoTextoRegistroTipoImovel.append(Util.completaString("", 8));
		}

		arquivoTextoRegistroTipoImovel.append(Util.completaString(imovelAtualizacaoCadastral.getNomeMunicipio(), 15));
		arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(9, imovelAtualizacaoCadastral.getIdLogradouro().toString()));

		// Subcategorias
		Collection colecaoImovelSubcategoria = getControladorAtualizacaoCadastral().pesquisarImovelSubcategoriaAtualizacaoCadastral(idImovel, null, null);

		Iterator imovelSubcategoriaIterator = colecaoImovelSubcategoria.iterator();

		ImovelSubcategoriaAtualizacaoCadastral residencial1 = null;
		ImovelSubcategoriaAtualizacaoCadastral residencial2 = null;
		ImovelSubcategoriaAtualizacaoCadastral residencial3 = null;
		ImovelSubcategoriaAtualizacaoCadastral residencial4 = null;
		ImovelSubcategoriaAtualizacaoCadastral comercial1 = null;
		ImovelSubcategoriaAtualizacaoCadastral comercial2 = null;
		ImovelSubcategoriaAtualizacaoCadastral comercial3 = null;
		ImovelSubcategoriaAtualizacaoCadastral comercial4 = null;
		ImovelSubcategoriaAtualizacaoCadastral publica1 = null;
		ImovelSubcategoriaAtualizacaoCadastral publica2 = null;
		ImovelSubcategoriaAtualizacaoCadastral publica3 = null;
		ImovelSubcategoriaAtualizacaoCadastral publica4 = null;
		ImovelSubcategoriaAtualizacaoCadastral industrial1 = null;
		ImovelSubcategoriaAtualizacaoCadastral industrial2 = null;
		ImovelSubcategoriaAtualizacaoCadastral industrial3 = null;
		ImovelSubcategoriaAtualizacaoCadastral industrial4 = null;

		while (imovelSubcategoriaIterator.hasNext()) {

			ImovelSubcategoriaAtualizacaoCadastral imovelSubcategoria = (ImovelSubcategoriaAtualizacaoCadastral) imovelSubcategoriaIterator
					.next();
			switch (imovelSubcategoria.getSubcategoria().getId().intValue()) {

			case Subcategoria.RESIDENCIAL_R1:
				residencial1 = imovelSubcategoria;
				break;

			case Subcategoria.RESIDENCIAL_R2:
				residencial2 = imovelSubcategoria;
				break;

			case Subcategoria.RESIDENCIAL_R3:
				residencial3 = imovelSubcategoria;
				break;

			case Subcategoria.RESIDENCIAL_R4:
				residencial4 = imovelSubcategoria;
				break;

			case Subcategoria.COMERCIAL_C1:
				comercial1 = imovelSubcategoria;
				break;

			case Subcategoria.COMERCIAL_C2:
				comercial2 = imovelSubcategoria;
				break;

			case Subcategoria.COMERCIAL_C3:
				comercial3 = imovelSubcategoria;
				break;

			case Subcategoria.COMERCIAL_C4:
				comercial4 = imovelSubcategoria;
				break;

			case Subcategoria.INDUSTRIAL_I1:
				industrial1 = imovelSubcategoria;
				break;

			case Subcategoria.INDUSTRIAL_I2:
				industrial2 = imovelSubcategoria;
				break;

			case Subcategoria.INDUSTRIAL_I3:
				industrial3 = imovelSubcategoria;
				break;

			case Subcategoria.INDUSTRIAL_I4:
				industrial4 = imovelSubcategoria;
				break;

			case Subcategoria.PUBLICA_P1:
				publica1 = imovelSubcategoria;
				break;

			case Subcategoria.PUBLICA_P2:
				publica2 = imovelSubcategoria;
				break;

			case Subcategoria.PUBLICA_P3:
				publica3 = imovelSubcategoria;
				break;

			case Subcategoria.PUBLICA_P4:
				publica4 = imovelSubcategoria;
				break;
			}
		}

		// Subcategoria R1
		if (residencial1 != null) {
			if (new Short(residencial1.getQuantidadeEconomias()) != null) {
				arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(3, residencial1.getQuantidadeEconomias()	+ ""));
			} else {
				arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
			}
		} else {
			arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
		}

		// Subcategoria R2
		if (residencial2 != null) {
			if (new Short(residencial2.getQuantidadeEconomias()) != null) {
				arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(3, residencial2.getQuantidadeEconomias()	+ ""));
			} else {
				arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
			}
		} else {
			arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
		}

		// Subcategoria R3
		if (residencial3 != null) {
			if (new Short(residencial3.getQuantidadeEconomias()) != null) {
				arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(3, residencial3.getQuantidadeEconomias() + ""));
			} else {
				arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
			}
		} else {
			arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
		}

		// Subcategoria R4
		if (residencial4 != null) {
			if (new Short(residencial4.getQuantidadeEconomias()) != null) {
				arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(3, residencial4.getQuantidadeEconomias()	+ ""));
			} else {
				arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
			}
		} else {
			arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
		}

		// Subcategoria C1
		if (comercial1 != null) {
			if (new Short(comercial1.getQuantidadeEconomias()) != null) {
				arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(3, comercial1.getQuantidadeEconomias() + ""));
			} else {
				arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
			}
		} else {
			arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
		}

		// Subcategoria C2
		if (comercial2 != null) {
			if (new Short(comercial2.getQuantidadeEconomias()) != null) {
				arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(3, comercial2.getQuantidadeEconomias() + ""));
			} else {
				arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
			}
		} else {
			arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
		}

		// Subcategoria C3
		if (comercial3 != null) {
			if (new Short(comercial3.getQuantidadeEconomias()) != null) {
				arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(3, comercial3.getQuantidadeEconomias() + ""));
			} else {
				arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
			}
		} else {
			arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
		}

		// Subcategoria C4
		if (comercial4 != null) {
			if (new Short(comercial4.getQuantidadeEconomias()) != null) {
				arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(3, comercial4.getQuantidadeEconomias() + ""));
			} else {
				arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
			}
		} else {
			arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
		}

		// Subcategoria P1
		if (publica1 != null) {
			if (new Short(publica1.getQuantidadeEconomias()) != null) {
				arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(3, publica1.getQuantidadeEconomias()	+ ""));
			} else {
				arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
			}
		} else {
			arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
		}

		// Subcategoria P2
		if (publica2 != null) {
			if (new Short(publica2.getQuantidadeEconomias()) != null) {
				arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(3, publica2.getQuantidadeEconomias()	+ ""));
			} else {
				arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
			}
		} else {
			arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
		}

		// Subcategoria P3
		if (publica3 != null) {
			if (new Short(publica3.getQuantidadeEconomias()) != null) {
				arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(3, publica3.getQuantidadeEconomias()	+ ""));
			} else {
				arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
			}
		} else {
			arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
		}

		// Subcategoria P4
		if (publica4 != null) {
			if (new Short(publica4.getQuantidadeEconomias()) != null) {
				arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(3, publica4.getQuantidadeEconomias()	+ ""));
			} else {
				arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
			}
		} else {
			arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
		}

		// Subcategoria I1
		if (industrial1 != null) {
			if (new Short(industrial1.getQuantidadeEconomias()) != null) {
				arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(3, industrial1.getQuantidadeEconomias() + ""));
			} else {
				arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
			}
		} else {
			arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
		}

		// Subcategoria I2
		if (industrial2 != null) {
			if (new Short(industrial2.getQuantidadeEconomias()) != null) {
				arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(3, industrial2.getQuantidadeEconomias()	+ ""));
			} else {
				arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
			}
		} else {
			arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
		}

		// Subcategoria I3
		if (industrial3 != null) {
			if (new Short(industrial3.getQuantidadeEconomias()) != null) {
				arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(3, industrial3.getQuantidadeEconomias() + ""));
			} else {
				arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
			}
		} else {
			arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
		}

		// Subcategoria I4
		if (industrial4 != null) {
			if (new Short(industrial4.getQuantidadeEconomias()) != null) {
				arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(3, industrial4.getQuantidadeEconomias() + ""));
			} else {
				arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
			}
		} else {
			arquivoTextoRegistroTipoImovel.append(Util.completaString("", 3));
		}

		// FONTE DE ABASTECIMENTO (Tabela cadastro.fonte_abastecimento)
		if (imovelAtualizacaoCadastral.getIdFonteAbastecimento() != null
				&& !imovelAtualizacaoCadastral.getIdFonteAbastecimento().equals("")) {
			arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(2, imovelAtualizacaoCadastral.getIdFonteAbastecimento().toString()));
		} else {
			arquivoTextoRegistroTipoImovel.append("00");
		}

		arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(10, imovelAtualizacaoCadastral.getAreaConstruida()));

		if (imovelAtualizacaoCadastral.getClasseSocial() != null){
		    arquivoTextoRegistroTipoImovel.append(imovelAtualizacaoCadastral.getClasseSocial());
		}else{
		    arquivoTextoRegistroTipoImovel.append(" ");
		}

		arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(4, imovelAtualizacaoCadastral.getQuantidadeAnimaisDomesticos()));

		arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(7, imovelAtualizacaoCadastral.getVolumeCisterna()));
		arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(7, imovelAtualizacaoCadastral.getVolumePiscina()));
		arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(7, imovelAtualizacaoCadastral.getVolumeCaixaDagua()));

        if (imovelAtualizacaoCadastral.getTipoUso() != null){
            arquivoTextoRegistroTipoImovel.append(imovelAtualizacaoCadastral.getTipoUso());
        }else{
            arquivoTextoRegistroTipoImovel.append(" ");
        }

        if (imovelAtualizacaoCadastral.getAcessoHidrometro() != null){
            arquivoTextoRegistroTipoImovel.append(imovelAtualizacaoCadastral.getAcessoHidrometro());
        }else{
            arquivoTextoRegistroTipoImovel.append(" ");
        }

        try {
            Collection<ImovelTipoOcupante> lista = repositorioUtil.listar(ImovelTipoOcupante.class);

            Map<Integer, ImovelTipoOcupanteQuantidadeAtualizacaoCadastral> hashQuantidades = new HashMap<Integer, ImovelTipoOcupanteQuantidadeAtualizacaoCadastral>();

            Collection<ImovelTipoOcupanteQuantidadeAtualizacaoCadastral> quantidadesOcupantes = repositorioCadastro.recuperarTipoOcupantesParaAtualizacaoCadastral(idImovel);

            for (ImovelTipoOcupanteQuantidadeAtualizacaoCadastral item : quantidadesOcupantes) {
                hashQuantidades.put(item.getTipoOcupante().getId(), item);
            }

            for (ImovelTipoOcupante tipo : lista) {
                Integer qtd = 0;
                if (hashQuantidades.containsKey(tipo.getId())){
                    qtd = hashQuantidades.get(tipo.getId()).getQuantidade();
                }
                arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(4, qtd));
            }
        } catch (Exception e) {
            throw new ControladorException("Erro ao recuperar ocupantes para atualizacao cadastral", e);
        }

        arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(3, imovelAtualizacaoCadastral.getQuantidadeEconomiasSocial()));
        arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(3, imovelAtualizacaoCadastral.getQuantidadeEconomiasOutra()));
        arquivoTextoRegistroTipoImovel.append(Util.adicionarZerosEsquedaNumero(3, imovelAtualizacaoCadastral.getPercentualAbastecimento()));

		arquivoTextoRegistroTipoImovel.append(System.getProperty("line.separator"));

		return arquivoTextoRegistroTipoImovel;
	}

	/**
	 * Gerar Arquivo Texto para Atualizacao Cadastral
	 *
	 * Registro Tipo 02 - Dados do(s) cliente(s)
	 *
	 * @author Wellington Rocha
	 * @date 21/03/2012
	 *
	 * @param imovel
	 * @throws ControladorException
	 */
	public StringBuilder gerarArquivoTextoRegistroTipoCliente(
			Collection colecaoClienteImovel, Integer idImovel)
			throws ControladorException {

		StringBuilder arquivoTextoRegistroTipoCliente = new StringBuilder();

		// TIPO DO REGISTRO (DADOS DO CLIENTE)
		arquivoTextoRegistroTipoCliente.append("01");

		// IMOVEL
		arquivoTextoRegistroTipoCliente.append(Util.adicionarZerosEsquedaNumero(9, idImovel.toString()));

		Imovel imovel = getControladorImovel().pesquisarImovel(idImovel);

		Localidade locaImovel = null;
		GerenciaRegional gereRegional = null;
		String descricaoGerenciaRegional = null;

		if (imovel.getLocalidade() != null) {
			locaImovel = imovel.getLocalidade();
			if (locaImovel.getGerenciaRegional() != null) {
				gereRegional = locaImovel.getGerenciaRegional();
				if (gereRegional.getNome() != null) {
					descricaoGerenciaRegional = gereRegional.getNome();
				}
			}
		}

		// Descricao Gerencia Regional
		arquivoTextoRegistroTipoCliente.append(Util.completaString(descricaoGerenciaRegional, 25));

		Iterator colecaoClienteImovelIterator = colecaoClienteImovel.iterator();

		IClienteAtualizacaoCadastral clienteUsuario = null;
		IClienteAtualizacaoCadastral clienteResponsavel = null;
		IClienteAtualizacaoCadastral clienteProprietario = null;

		while (colecaoClienteImovelIterator.hasNext()) {
			IClienteAtualizacaoCadastral cliente = (IClienteAtualizacaoCadastral) colecaoClienteImovelIterator
					.next();
			if (cliente.getIdClienteRelacaoTipo() != null) {
				if (cliente.getIdClienteRelacaoTipo().equals(
						new Integer(ClienteRelacaoTipo.USUARIO))) {
					clienteUsuario = cliente;
				} else if (cliente.getIdClienteRelacaoTipo().equals(
						new Integer(ClienteRelacaoTipo.RESPONSAVEL))) {
					clienteResponsavel = cliente;
				} else if (cliente.getIdClienteRelacaoTipo().equals(
						new Integer(ClienteRelacaoTipo.PROPRIETARIO))) {
					clienteProprietario = cliente;
				}
			}
		}

		// Tipo de Endereco Cliente Proprietario
		if (clienteProprietario != null) {
			if (clienteProprietario.getIdEnderecoTipo() != null) {
				arquivoTextoRegistroTipoCliente.append(clienteProprietario
						.getIdEnderecoTipo());
			} else {
				arquivoTextoRegistroTipoCliente.append(" ");
			}
		} else {
			arquivoTextoRegistroTipoCliente.append(" ");
		}

		// Tipo de Endereco do Cliente Responsavel
		if (clienteResponsavel != null) {
			if (clienteResponsavel.getIdEnderecoTipo() != null) {
				arquivoTextoRegistroTipoCliente.append(clienteResponsavel
						.getIdEnderecoTipo());
			} else {
				arquivoTextoRegistroTipoCliente.append(" ");
			}
		} else {
			arquivoTextoRegistroTipoCliente.append(" ");
		}

		// Cliente Proprietario = ClienteUsuario (1-Sim / 2-Nao)
		if (clienteProprietario != null) {
			if (clienteUsuario != null
					&& clienteProprietario.getIdCliente().equals(
							clienteUsuario.getIdCliente())) {
				arquivoTextoRegistroTipoCliente.append("1");
			} else {
				arquivoTextoRegistroTipoCliente.append("2");
			}
		} else {
			arquivoTextoRegistroTipoCliente.append("1");
		}

		// Tipo Responsavel (0-Usuario / 1-Proprietario / 2-Outro)
		if (clienteResponsavel != null) {
			if (clienteUsuario != null
					&& clienteResponsavel.getIdCliente().equals(
							clienteUsuario.getIdCliente())) {
				arquivoTextoRegistroTipoCliente.append("0");
			} else if (clienteProprietario != null
					&& clienteResponsavel.getIdCliente().equals(
							clienteUsuario.getIdCliente())) {
				arquivoTextoRegistroTipoCliente.append("1");
			} else {
				arquivoTextoRegistroTipoCliente.append("2");
			}
		} else {
			arquivoTextoRegistroTipoCliente.append("0");
		}

		// Dados Cliente Usuario
		if (clienteUsuario != null) {
			arquivoTextoRegistroTipoCliente.append(Util.adicionarZerosEsquedaNumero(9, clienteUsuario.getIdCliente()));
			arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteUsuario.getNome(), 50));

			// Pessoa 1-Fi�sica/2-Juri�dica
			if (clienteUsuario.getIdClienteTipo() != null) {
				ClienteTipo clienteTipo = getControladorCliente()
						.pesquisarClienteTipo(clienteUsuario.getIdClienteTipo());
				if (!clienteTipo.getIndicadorPessoaFisicaJuridica().equals("")) {
					arquivoTextoRegistroTipoCliente.append(clienteTipo
							.getIndicadorPessoaFisicaJuridica());
				} else {
					arquivoTextoRegistroTipoCliente.append(" ");
				}
			} else {
				arquivoTextoRegistroTipoCliente.append(" ");
			}

			if (clienteUsuario.getCpf() != null) {
				arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteUsuario.getCpf(), 14));
			} else if (clienteUsuario.getCnpj() != null) {
				arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteUsuario.getCnpj(), 14));
			} else {
				arquivoTextoRegistroTipoCliente.append(Util.completaString("", 14));
			}

			arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteUsuario.getRg(), 13));

			arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteUsuario.getDsUFSiglaOrgaoExpedidorRg(), 2));

			// Sexo do Cliente (1-Masculino/2-Feminino)
			if (clienteUsuario.getPessoaSexo() != null && clienteUsuario.getPessoaSexo().getId() != null) {
				arquivoTextoRegistroTipoCliente.append(clienteUsuario.getPessoaSexo().getId());
			} else {
				arquivoTextoRegistroTipoCliente.append(" ");
			}

			// Telefone Usuario
            Collection colecaoClienteFone = getControladorCliente().pesquisarClienteFoneAtualizacaoCadastral(clienteUsuario.getIdCliente(), idImovel, null,
                    clienteUsuario.getIdClienteRelacaoTipo(), null);

            ClienteFoneAtualizacaoCadastral residencial = null;
            ClienteFoneAtualizacaoCadastral celular = null;
            ClienteFoneAtualizacaoCadastral comercial = null;
            ClienteFoneAtualizacaoCadastral fax = null;

            Iterator clienteFoneIterator = colecaoClienteFone.iterator();
            while (clienteFoneIterator.hasNext()) {

                ClienteFoneAtualizacaoCadastral clienteFone = (ClienteFoneAtualizacaoCadastral) clienteFoneIterator.next();

                if (clienteFone.getIdFoneTipo().equals(FoneTipo.CELULAR)) {
                    celular = clienteFone;
                } else if (clienteFone.getIdFoneTipo().equals(FoneTipo.RESIDENCIAL)) {
                    residencial = clienteFone;
                } else if (clienteFone.getIdFoneTipo().equals(FoneTipo.COMERCIAL)) {
                    comercial = clienteFone;
                } else if (clienteFone.getIdFoneTipo().equals(FoneTipo.FAX)) {
                    fax = clienteFone;
                }
            }

            if (residencial != null) {
                arquivoTextoRegistroTipoCliente.append(Util.completaString(residencial.getDdd(), 2));
                arquivoTextoRegistroTipoCliente.append(Util.completaString(residencial.getTelefone(), 8));
            } else if (comercial != null) {
                arquivoTextoRegistroTipoCliente.append(Util.completaString(comercial.getDdd(), 2));
                arquivoTextoRegistroTipoCliente.append(Util.completaString(comercial.getTelefone(), 8));
            } else {
                arquivoTextoRegistroTipoCliente.append(Util.completaString("", 10));
            }

			// Celular Usuario
            if (celular != null) {
                arquivoTextoRegistroTipoCliente.append(Util.completaString(celular.getDdd(), 2));
                arquivoTextoRegistroTipoCliente.append(Util.completaString(celular.getTelefone(), 8));
            } else {
                arquivoTextoRegistroTipoCliente.append(Util.completaString("", 10));
            }

			// E-mail usuario
            arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteUsuario.getEmail(), 30));
		} else {
			arquivoTextoRegistroTipoCliente.append(Util.completaString("", 140));
		}

		// Dados Cliente Proprietario
        if (clienteProprietario != null) {
            arquivoTextoRegistroTipoCliente.append(Util.adicionarZerosEsquedaNumero(9, clienteProprietario.getIdCliente()));

            // Nome do Cliente Proprietario
            arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteProprietario.getNome(), 50));

            // Pessoa 1-Fi�sica / 2-Juri�dica
            if (clienteProprietario.getIdClienteTipo() != null) {
                ClienteTipo clienteTipo = getControladorCliente().pesquisarClienteTipo(clienteProprietario.getIdClienteTipo());
                if (!clienteTipo.getIndicadorPessoaFisicaJuridica().equals("")) {
                    arquivoTextoRegistroTipoCliente.append(clienteTipo.getIndicadorPessoaFisicaJuridica());
                } else {
                    arquivoTextoRegistroTipoCliente.append(" ");
                }
            } else {
                arquivoTextoRegistroTipoCliente.append(" ");
            }

            // CPF/CNPJ Cliente
            if (clienteProprietario.getCpf() != null) {
                arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteProprietario.getCpf(), 14));

            } else if (clienteProprietario.getCnpj() != null) {
                arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteProprietario.getCnpj(), 14));
            } else {
                arquivoTextoRegistroTipoCliente.append(Util.completaString("", 14));
            }

            // RG Cliente
            arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteProprietario.getRg(), 13));

            // UF Cliente Proprietario
            arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteProprietario.getDsUFSiglaOrgaoExpedidorRg(), 2));

            // Sexo do Cliente (1-Masculino/2-Feminino)
            if (clienteProprietario.getPessoaSexo() != null && clienteProprietario.getPessoaSexo().getId() != null) {
                arquivoTextoRegistroTipoCliente.append(clienteProprietario.getPessoaSexo().getId());
            } else {
                arquivoTextoRegistroTipoCliente.append(" ");
            }

            // Telefone Proprietario
            Collection colecaoClienteFone = getControladorCliente().pesquisarClienteFoneAtualizacaoCadastral(clienteProprietario.getIdCliente(), idImovel, null,
                    clienteProprietario.getIdClienteRelacaoTipo(), null);

            ClienteFoneAtualizacaoCadastral residencial = null;
            ClienteFoneAtualizacaoCadastral celular = null;
            ClienteFoneAtualizacaoCadastral comercial = null;
            ClienteFoneAtualizacaoCadastral fax = null;

            Iterator clienteFoneIterator = colecaoClienteFone.iterator();
            while (clienteFoneIterator.hasNext()) {

                ClienteFoneAtualizacaoCadastral clienteFone = (ClienteFoneAtualizacaoCadastral) clienteFoneIterator.next();

                if (clienteFone.getIdFoneTipo().equals(FoneTipo.CELULAR)) {
                    celular = clienteFone;
                } else if (clienteFone.getIdFoneTipo().equals(FoneTipo.RESIDENCIAL)) {
                    residencial = clienteFone;
                } else if (clienteFone.getIdFoneTipo().equals(FoneTipo.COMERCIAL)) {
                    comercial = clienteFone;
                } else if (clienteFone.getIdFoneTipo().equals(FoneTipo.FAX)) {
                    fax = clienteFone;
                }
            }

            if (residencial != null) {
                arquivoTextoRegistroTipoCliente.append(Util.completaString(residencial.getDdd(), 2));
                arquivoTextoRegistroTipoCliente.append(Util.completaString(residencial.getTelefone(), 8));
            } else if (comercial != null) {
                arquivoTextoRegistroTipoCliente.append(Util.completaString(comercial.getDdd(), 2));
                arquivoTextoRegistroTipoCliente.append(Util.completaString(comercial.getTelefone(), 8));
            } else {
                arquivoTextoRegistroTipoCliente.append(Util.completaString("", 10));
            }

            // Celular Proprietario
            if (celular != null) {
                arquivoTextoRegistroTipoCliente.append(Util.completaString(celular.getDdd(), 2));
                arquivoTextoRegistroTipoCliente.append(Util.completaString(celular.getTelefone(), 8));
            } else {
                arquivoTextoRegistroTipoCliente.append(Util.completaString("", 10));
            }

            arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteProprietario.getEmail() != null ? clienteProprietario.getEmail() : "", 30));
            arquivoTextoRegistroTipoCliente.append(Util.adicionarZerosEsquedaNumero(2, clienteProprietario.getIdLogradouroTipo() != null ? clienteProprietario.getIdLogradouroTipo() : 0));
            arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteProprietario.getDescricaoLogradouro() != null ? clienteProprietario.getDescricaoLogradouro() : "", 40));
            arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteProprietario.getNumeroImovel() != null ? clienteProprietario.getNumeroImovel() : "", 5));
            arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteProprietario.getComplementoEndereco() != null ? clienteProprietario.getComplementoEndereco() : "", 25));
            arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteProprietario.getNomeBairro() != null ? clienteProprietario.getNomeBairro() : "", 20));
            arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteProprietario.getCodigoCep() != null ? clienteProprietario.getCodigoCep().toString() : "", 8));
            arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteProprietario.getNomeMunicipio() != null ? clienteProprietario.getNomeMunicipio() : "", 15));
        } else {
			arquivoTextoRegistroTipoCliente.append(Util.completaString("", 255));
		}

		// Dados Cliente Responsavel
        if (clienteResponsavel != null) {
            arquivoTextoRegistroTipoCliente.append(Util.adicionarZerosEsquedaNumero(9, clienteResponsavel.getIdCliente()));
            arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteResponsavel.getNome(), 50));

            // Pessoa 1-Fisica / 2-Juridica
            if (clienteResponsavel.getIdClienteTipo() != null) {
                ClienteTipo clienteTipo = getControladorCliente().pesquisarClienteTipo(clienteResponsavel.getIdClienteTipo());
                if (!clienteTipo.getIndicadorPessoaFisicaJuridica().equals("")) {
                    arquivoTextoRegistroTipoCliente.append(clienteTipo.getIndicadorPessoaFisicaJuridica());
                } else {
                    arquivoTextoRegistroTipoCliente.append(" ");
                }
            } else {
                arquivoTextoRegistroTipoCliente.append(" ");
            }

            // CPF/CNPJ Cliente
            if (clienteResponsavel.getCpf() != null) {
                arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteResponsavel.getCpf(), 14));
            } else if (clienteResponsavel.getCnpj() != null) {
                arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteResponsavel.getCnpj(), 14));
            } else {
                arquivoTextoRegistroTipoCliente.append(Util.completaString("", 14));
            }

            arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteResponsavel.getRg(), 13));
            arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteResponsavel.getDsUFSiglaOrgaoExpedidorRg(), 2));

            if (clienteResponsavel.getPessoaSexo() != null && clienteResponsavel.getPessoaSexo().getId() != null) {
                arquivoTextoRegistroTipoCliente.append(clienteResponsavel.getPessoaSexo().getId());
            } else {
                arquivoTextoRegistroTipoCliente.append(" ");
            }

            // Telefone Responsavel
            Collection colecaoClienteFone = getControladorCliente().pesquisarClienteFoneAtualizacaoCadastral(clienteResponsavel.getIdCliente(), idImovel, null,
                    clienteResponsavel.getIdClienteRelacaoTipo(), null);

            ClienteFoneAtualizacaoCadastral residencial = null;
            ClienteFoneAtualizacaoCadastral celular = null;
            ClienteFoneAtualizacaoCadastral comercial = null;
            ClienteFoneAtualizacaoCadastral fax = null;

            Iterator clienteFoneIterator = colecaoClienteFone.iterator();
            while (clienteFoneIterator.hasNext()) {

                ClienteFoneAtualizacaoCadastral clienteFone = (ClienteFoneAtualizacaoCadastral) clienteFoneIterator.next();

                if (clienteFone.getIdFoneTipo().equals(FoneTipo.CELULAR)) {
                    celular = clienteFone;
                } else if (clienteFone.getIdFoneTipo().equals(FoneTipo.RESIDENCIAL)) {
                    residencial = clienteFone;
                } else if (clienteFone.getIdFoneTipo().equals(FoneTipo.COMERCIAL)) {
                    comercial = clienteFone;
                } else if (clienteFone.getIdFoneTipo().equals(FoneTipo.FAX)) {
                    fax = clienteFone;
                }
            }

            if (residencial != null) {
                arquivoTextoRegistroTipoCliente.append(Util.completaString(residencial.getDdd(), 2));
                arquivoTextoRegistroTipoCliente.append(Util.completaString(residencial.getTelefone(), 8));
            } else if (comercial != null) {
                arquivoTextoRegistroTipoCliente.append(Util.completaString(comercial.getDdd(), 2));
                arquivoTextoRegistroTipoCliente.append(Util.completaString(comercial.getTelefone(), 8));
            } else {
                arquivoTextoRegistroTipoCliente.append(Util.completaString("", 10));
            }

            // Celular Responsavel
            if (celular != null) {
                arquivoTextoRegistroTipoCliente.append(Util.completaString(celular.getDdd(), 2));
                arquivoTextoRegistroTipoCliente.append(Util.completaString(celular.getTelefone(), 8));
            } else {
                arquivoTextoRegistroTipoCliente.append(Util.completaString("", 10));
            }

            arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteResponsavel.getEmail() != null ? clienteResponsavel.getEmail() : "", 30));
            arquivoTextoRegistroTipoCliente.append(Util.adicionarZerosEsquedaNumero(2, clienteResponsavel.getIdLogradouroTipo() != null ? clienteResponsavel.getIdLogradouroTipo() : 0));
            arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteResponsavel.getDescricaoLogradouro() != null ? clienteResponsavel.getDescricaoLogradouro() : "", 40));
            arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteResponsavel.getNumeroImovel() != null ? clienteResponsavel.getNumeroImovel() : "", 5));
            arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteResponsavel.getComplementoEndereco() != null ? clienteResponsavel.getComplementoEndereco() : "", 25));
            arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteResponsavel.getNomeBairro() != null ? clienteResponsavel.getNomeBairro() : "", 20));
            arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteResponsavel.getCodigoCep() != null ? clienteResponsavel.getCodigoCep().toString() : "", 8));
            arquivoTextoRegistroTipoCliente.append(Util.completaString(clienteResponsavel.getNomeMunicipio() != null ? clienteResponsavel.getNomeMunicipio() : "", 15));
        } else {
			arquivoTextoRegistroTipoCliente.append(Util.completaString("", 255));
		}

		arquivoTextoRegistroTipoCliente.append(System.getProperty("line.separator"));

		return arquivoTextoRegistroTipoCliente;
	}

	/**
	 * Gerar Arquivo Texto para Atualização Cadastral
	 *
	 * Registro Tipo 03 - Ramos Atividade do Imovel
	 *
	 * @author Wellington Rocha
	 * @date 21/03/2012
	 *
	 * @param imovel
	 * @throws ControladorException
	 */
	public StringBuilder gerarArquivoTextoRegistroTipoRamoAtividadeImovel(Collection<ImovelRamoAtividade> colecaoImovelRamoAtividade, Integer idImovel)
			throws ControladorException {

		StringBuilder arquivoTextoRegistroTipoRamoAtividadeImovel = new StringBuilder();

		Iterator imovelRamoAtividadeIterator = colecaoImovelRamoAtividade.iterator();
		while (imovelRamoAtividadeIterator.hasNext()) {

			ImovelRamoAtividade imovelRamoAtividade = (ImovelRamoAtividade) imovelRamoAtividadeIterator.next();

			arquivoTextoRegistroTipoRamoAtividadeImovel.append("03");
			arquivoTextoRegistroTipoRamoAtividadeImovel.append(Util.adicionarZerosEsquedaNumero(9, idImovel.toString()));
			arquivoTextoRegistroTipoRamoAtividadeImovel.append(Util	.adicionarZerosEsquedaNumero(3,
					imovelRamoAtividade.getComp_id().getRamo_atividade().getId().toString()));

			arquivoTextoRegistroTipoRamoAtividadeImovel.append(System.getProperty("line.separator"));

		}
		return arquivoTextoRegistroTipoRamoAtividadeImovel;
	}

	/**
	 * Gerar Arquivo Texto para Atualização Cadastral
	 *
	 * Registro Tipo 05 - Dados Medidor
	 *
	 * @author Wellington Rocha
	 * @date 21/03/2012
	 *
	 * @param imovel
	 * @throws ControladorException
	 */


	/**
	 * Geração da rota para recadastramento
	 *
	 * @author Wellington Rocha
	 */
	public StringBuilder gerarArquivoTextoRegistroTipoGeral(Rota rota, String tipoArquivo) throws ControladorException {
		StringBuilder arquivoTexto = new StringBuilder();
		SistemaParametro parametrosSistema = getControladorUtil().pesquisarParametrosDoSistema();

		// TIPO DO REGISTRO
		arquivoTexto.append("07");
		// CODIGO EMPRESA FEBRABAN
		arquivoTexto.append(Util.adicionarZerosEsquedaNumero(4, parametrosSistema.getCodigoEmpresaFebraban().toString()));
		// ANO MES FATURAMENTO CORRENTE
		arquivoTexto.append(parametrosSistema.getAnoMesFaturamento());
		// 0800 DA EMPRESA
		arquivoTexto.append(Util.completaString(parametrosSistema.getNumero0800Empresa(), 12));
		// CNPJ DA EMPRESA
		arquivoTexto.append(Util.adicionarZerosEsquedaNumero(14, parametrosSistema.getCnpjEmpresa()));
		// INSCRICAO ESTADUAL EMPRESA
		arquivoTexto.append(Util.adicionarZerosEsquedaNumero(20, parametrosSistema.getInscricaoEstadual()));
		// LOGIN LEITURISTA
		arquivoTexto.append(Util.completaString("gcom", 11));

		// SENHA LEITURISTA
		String senhaGerada = "senha";
		String senhaCriptografada = null;
		try {
			senhaCriptografada = Criptografia.encriptarSenha(senhaGerada);
		} catch (ErroCriptografiaException e1) {
			throw new ControladorException("erro.criptografia.senha");
		}
		arquivoTexto.append(Util.completaString(senhaCriptografada, 40));

		arquivoTexto.append(" ");

		if (parametrosSistema.getVersaoCelular() != null) {
			arquivoTexto.append(Util.adicionarZerosEsquedaNumero(10, parametrosSistema.getVersaoCelular()));
		} else {
			arquivoTexto.append(Util.completaString("", 10));
		}

		arquivoTexto.append(Util.completaString("", 8));
		arquivoTexto.append(Util.completaString("", 8));
		arquivoTexto.append(Util.adicionarZerosEsquedaNumero(4, rota.getId().toString()));
		arquivoTexto.append(Util.adicionarZerosEsquedaNumero(3, rota.getSetorComercial().getLocalidade().getId().toString()));
		arquivoTexto.append(Util.adicionarZerosEsquedaNumero(3, rota.getSetorComercial().getCodigo() + ""));
		arquivoTexto.append(Util.adicionarZerosEsquedaNumero(2, rota.getCodigo() + ""));
		arquivoTexto.append(Util.adicionarZerosEsquedaNumero(3, rota.getFaturamentoGrupo().getId() + ""));
		arquivoTexto.append(tipoArquivo);

		arquivoTexto.append(System.getProperty("line.separator"));

		return arquivoTexto;
	}

	public StringBuilder gerarArquivoTextoRegistroTipoAnormalidades(
			CadastroOcorrencia cadastroOcorrencia) throws ControladorException {

		StringBuilder arquivoTextoRegistroTipoAnormalidades = new StringBuilder();

		arquivoTextoRegistroTipoAnormalidades.append("08");

		arquivoTextoRegistroTipoAnormalidades.append(Util
				.adicionarZerosEsquedaNumero(3, cadastroOcorrencia.getId()
						.toString()));

		arquivoTextoRegistroTipoAnormalidades.append(Util.completaString(
				cadastroOcorrencia.getDescricao(), 25));

		arquivoTextoRegistroTipoAnormalidades.append(System
				.getProperty("line.separator"));

		return arquivoTextoRegistroTipoAnormalidades;
	}

	public StringBuilder gerarArquivoTextoRegistroTipoRamoAtividade(
			RamoAtividade ramoAtividade) throws ControladorException {

		StringBuilder arquivoTextoRegistroTipoRamoAtividade = new StringBuilder();

		arquivoTextoRegistroTipoRamoAtividade.append("09");

		arquivoTextoRegistroTipoRamoAtividade.append(Util
				.adicionarZerosEsquedaNumero(3, ramoAtividade.getId()
						.toString()));

		arquivoTextoRegistroTipoRamoAtividade.append(Util.completaString(
				ramoAtividade.getDescricao(), 20));

		arquivoTextoRegistroTipoRamoAtividade.append(System
				.getProperty("line.separator"));

		return arquivoTextoRegistroTipoRamoAtividade;
	}

	public StringBuilder gerarArquivoTextoRegistroTipoLigacaoAguaSituacao(
			LigacaoAguaSituacao ligacaoAguaSituacao)
			throws ControladorException {

		StringBuilder arquivoTextoRegistroTipoLigacaoAguaSituacao = new StringBuilder();

		arquivoTextoRegistroTipoLigacaoAguaSituacao.append("10");

		arquivoTextoRegistroTipoLigacaoAguaSituacao.append(Util
				.adicionarZerosEsquedaNumero(2, ligacaoAguaSituacao.getId()
						.toString()));

		arquivoTextoRegistroTipoLigacaoAguaSituacao.append(Util.completaString(
				ligacaoAguaSituacao.getDescricao(), 20));

		arquivoTextoRegistroTipoLigacaoAguaSituacao.append(System
				.getProperty("line.separator"));

		return arquivoTextoRegistroTipoLigacaoAguaSituacao;
	}

	public StringBuilder gerarArquivoTextoRegistroTipoLigacaoEsgotoSituacao(
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao)
			throws ControladorException {

		StringBuilder arquivoTextoRegistroTipoLigacaoEsgotoSituacao = new StringBuilder();

		arquivoTextoRegistroTipoLigacaoEsgotoSituacao.append("11");

		arquivoTextoRegistroTipoLigacaoEsgotoSituacao.append(Util
				.adicionarZerosEsquedaNumero(2, ligacaoEsgotoSituacao.getId()
						.toString()));

		arquivoTextoRegistroTipoLigacaoEsgotoSituacao.append(Util
				.completaString(ligacaoEsgotoSituacao.getDescricao(), 20));

		arquivoTextoRegistroTipoLigacaoEsgotoSituacao.append(System
				.getProperty("line.separator"));

		return arquivoTextoRegistroTipoLigacaoEsgotoSituacao;
	}

	public StringBuilder gerarArquivoTextoRegistroTipoHidrometroProtecao(
			HidrometroProtecao hidrometroProtecao) throws ControladorException {

		StringBuilder arquivoTextoRegistroTipoHidrometroProtecao = new StringBuilder();

		arquivoTextoRegistroTipoHidrometroProtecao.append("12");

		arquivoTextoRegistroTipoHidrometroProtecao.append(Util
				.adicionarZerosEsquedaNumero(2, hidrometroProtecao.getId()
						.toString()));

		arquivoTextoRegistroTipoHidrometroProtecao.append(Util.completaString(
				hidrometroProtecao.getDescricao(), 50));

		arquivoTextoRegistroTipoHidrometroProtecao.append(System
				.getProperty("line.separator"));

		return arquivoTextoRegistroTipoHidrometroProtecao;
	}

	public StringBuilder gerarArquivoTextoRegistroTipoFonteAbastecimento(
			FonteAbastecimento fonteAbastecimento)
			throws ControladorException {

		StringBuilder arquivoTextoRegistroTipoFonteAbastecimento = new StringBuilder();

		arquivoTextoRegistroTipoFonteAbastecimento.append("13");

		arquivoTextoRegistroTipoFonteAbastecimento.append(Util
				.adicionarZerosEsquedaNumero(2, fonteAbastecimento.getId()
						.toString()));

		arquivoTextoRegistroTipoFonteAbastecimento.append(Util.completaString(
				fonteAbastecimento.getDescricao(), 20));

		arquivoTextoRegistroTipoFonteAbastecimento.append(System
					.getProperty("line.separator"));

		return arquivoTextoRegistroTipoFonteAbastecimento;
	}

	public StringBuilder gerarArquivoTextoRegistroTipoHidrometroMarca(
			HidrometroMarca hidrometroMarca)
			throws ControladorException {

		StringBuilder arquivoTextoRegistroTipoHidrometroMarca = new StringBuilder();

		arquivoTextoRegistroTipoHidrometroMarca.append("14");

		arquivoTextoRegistroTipoHidrometroMarca.append(Util
				.adicionarZerosEsquedaNumero(2, hidrometroMarca.getId()
						.toString()));

		arquivoTextoRegistroTipoHidrometroMarca.append(Util.completaString(
				hidrometroMarca.getDescricao(), 30));

		arquivoTextoRegistroTipoHidrometroMarca.append(System
				.getProperty("line.separator"));

		return arquivoTextoRegistroTipoHidrometroMarca;
	}

	public StringBuilder gerarArquivoTextoRegistroTipoRamalLocalInstalacao(
			RamalLocalInstalacao ramalLocalInstalacao)
			throws ControladorException {

		StringBuilder arquivoTextoRegistroTipoRamalLocalInstalacao = new StringBuilder();

		arquivoTextoRegistroTipoRamalLocalInstalacao.append("15");

		arquivoTextoRegistroTipoRamalLocalInstalacao.append(Util
				.adicionarZerosEsquedaNumero(2, ramalLocalInstalacao.getId()
						.toString()));

		arquivoTextoRegistroTipoRamalLocalInstalacao.append(Util.completaString(
				ramalLocalInstalacao.getDescricao(), 30));

		arquivoTextoRegistroTipoRamalLocalInstalacao.append(System
					.getProperty("line.separator"));

		return arquivoTextoRegistroTipoRamalLocalInstalacao;
	}

	public StringBuilder gerarArquivoTextoRegistroTipoHidrometroCapacidade(
			HidrometroCapacidade hidrometroCapacidade)
			throws ControladorException {

		StringBuilder arquivoTextoRegistroTipoHidrometroCapacidade = new StringBuilder();

		arquivoTextoRegistroTipoHidrometroCapacidade.append("16");

		arquivoTextoRegistroTipoHidrometroCapacidade.append(Util
				.adicionarZerosEsquedaNumero(2, hidrometroCapacidade.getId()
						.toString()));

		arquivoTextoRegistroTipoHidrometroCapacidade.append(Util.completaString(
				hidrometroCapacidade.getDescricao(), 20));

		arquivoTextoRegistroTipoHidrometroCapacidade.append(System
					.getProperty("line.separator"));

		return arquivoTextoRegistroTipoHidrometroCapacidade;
	}

	/**
	 * Gerar Arquivo Texto para Atualização Cadastral
	 *
	 * Registro Tipo Trailer
	 *
	 * @author Ana Maria
	 * @date 11/05/2009
	 *
	 * @param imovel
	 * @throws ControladorException
	 */
    public Object[] gerarArquivoTextoRegistroTipoTrailer(Integer qtdRegistro, Rota rota, String tipoArquivo) throws ControladorException {
        Object[] retorno = new Object[2];
        StringBuilder arquivoTextoRegistroTipoTrailer = new StringBuilder();

        arquivoTextoRegistroTipoTrailer.append(this.gerarArquivoTextoRegistroTipoGeral(rota, tipoArquivo));
        qtdRegistro = qtdRegistro + 1;

        Collection<CadastroOcorrencia> ocorrenciasCadastroCollection = this.pesquisarOcorrenciasCadastro();
        if (ocorrenciasCadastroCollection != null && !ocorrenciasCadastroCollection.isEmpty()) {
            Iterator colecaoIterator = ocorrenciasCadastroCollection.iterator();

            while (colecaoIterator.hasNext()) {
                CadastroOcorrencia cadastroOcorrencia = (CadastroOcorrencia) colecaoIterator.next();

                arquivoTextoRegistroTipoTrailer.append(this.gerarArquivoTextoRegistroTipoAnormalidades(cadastroOcorrencia));
                qtdRegistro = qtdRegistro + 1;

            }
        }

        Collection<RamoAtividade> ramoAtividadeCollection = this.pesquisarRamosAtividade();
        if (ramoAtividadeCollection != null && !ramoAtividadeCollection.isEmpty()) {
            Iterator colecaoIterator = ramoAtividadeCollection.iterator();

            while (colecaoIterator.hasNext()) {
                RamoAtividade ramoAtividade = (RamoAtividade) colecaoIterator.next();

                arquivoTextoRegistroTipoTrailer.append(this.gerarArquivoTextoRegistroTipoRamoAtividade(ramoAtividade));
                qtdRegistro = qtdRegistro + 1;
            }
        }

        Collection<LigacaoAguaSituacao> ligacaoAguaSituacaoCollection = getControladorLigacaoAgua().pesquisarLigacaoAguaSituacao();
        if (ligacaoAguaSituacaoCollection != null && !ligacaoAguaSituacaoCollection.isEmpty()) {
            Iterator colecaoIterator = ligacaoAguaSituacaoCollection.iterator();

            while (colecaoIterator.hasNext()) {
                LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) colecaoIterator.next();

                arquivoTextoRegistroTipoTrailer.append(this.gerarArquivoTextoRegistroTipoLigacaoAguaSituacao(ligacaoAguaSituacao));
                qtdRegistro = qtdRegistro + 1;
            }
        }

        Collection<LigacaoEsgotoSituacao> ligacaoEsgotoSituacaoCollection = getControladorLigacaoEsgoto().pesquisarLigacaoEsgotoSituacao();
        if (ligacaoEsgotoSituacaoCollection != null && !ligacaoEsgotoSituacaoCollection.isEmpty()) {
            Iterator colecaoIterator = ligacaoEsgotoSituacaoCollection.iterator();

            while (colecaoIterator.hasNext()) {
                LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) colecaoIterator.next();

                arquivoTextoRegistroTipoTrailer.append(this.gerarArquivoTextoRegistroTipoLigacaoEsgotoSituacao(ligacaoEsgotoSituacao));
                qtdRegistro = qtdRegistro + 1;
            }
        }

        Collection<HidrometroProtecao> hidrometroProtecaoCollection = getControladorMicromedicao().pesquisarHidrometroProtecao();
        if (hidrometroProtecaoCollection != null && !hidrometroProtecaoCollection.isEmpty()) {
            Iterator colecaoIterator = hidrometroProtecaoCollection.iterator();

            while (colecaoIterator.hasNext()) {
                HidrometroProtecao hidrometroProtecao = (HidrometroProtecao) colecaoIterator.next();

                arquivoTextoRegistroTipoTrailer.append(this.gerarArquivoTextoRegistroTipoHidrometroProtecao(hidrometroProtecao));
                qtdRegistro = qtdRegistro + 1;
            }
        }

        Collection<FonteAbastecimento> fonteAbastecimentoCollection = this.pesquisarFonteAbastecimento();
        if (fonteAbastecimentoCollection != null && !fonteAbastecimentoCollection.isEmpty()) {
            Iterator colecaoIterator = fonteAbastecimentoCollection.iterator();

            while (colecaoIterator.hasNext()) {
                FonteAbastecimento fonteAbastecimento = (FonteAbastecimento) colecaoIterator.next();

                arquivoTextoRegistroTipoTrailer.append(this.gerarArquivoTextoRegistroTipoFonteAbastecimento(fonteAbastecimento));
                qtdRegistro = qtdRegistro + 1;
            }
        }

        Collection<HidrometroMarca> hidrometroMarcaCollection = getControladorMicromedicao().pesquisarHidrometroMarca();
        if (hidrometroMarcaCollection != null && !hidrometroMarcaCollection.isEmpty()) {
            Iterator colecaoIterator = hidrometroMarcaCollection.iterator();

            while (colecaoIterator.hasNext()) {
                HidrometroMarca hidrometroMarca = (HidrometroMarca) colecaoIterator.next();

                arquivoTextoRegistroTipoTrailer.append(this.gerarArquivoTextoRegistroTipoHidrometroMarca(hidrometroMarca));
                qtdRegistro = qtdRegistro + 1;
            }
        }

        Collection<RamalLocalInstalacao> ramalLocalInstalacaoCollection = getControladorAtendimentoPublico().pesquisarRamalLocalInstalacao();
        if (ramalLocalInstalacaoCollection != null && !ramalLocalInstalacaoCollection.isEmpty()) {
            Iterator colecaoIterator = ramalLocalInstalacaoCollection.iterator();

            while (colecaoIterator.hasNext()) {
                RamalLocalInstalacao ramalLocalInstalacao = (RamalLocalInstalacao) colecaoIterator.next();

                arquivoTextoRegistroTipoTrailer.append(this.gerarArquivoTextoRegistroTipoRamalLocalInstalacao(ramalLocalInstalacao));
                qtdRegistro = qtdRegistro + 1;
            }
        }

        FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
        filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(FiltroHidrometroCapacidade.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
        Collection<HidrometroCapacidade> hidrometroCapacidadeCollection = Fachada.getInstancia().pesquisar(filtroHidrometroCapacidade,
                HidrometroCapacidade.class.getName());

        if (hidrometroCapacidadeCollection != null && !hidrometroCapacidadeCollection.isEmpty()) {
            Iterator colecaoIterator = hidrometroCapacidadeCollection.iterator();

            while (colecaoIterator.hasNext()) {
                HidrometroCapacidade hidrometroCapacidade = (HidrometroCapacidade) colecaoIterator.next();

                arquivoTextoRegistroTipoTrailer.append(this.gerarArquivoTextoRegistroTipoHidrometroCapacidade(hidrometroCapacidade));
                qtdRegistro = qtdRegistro + 1;
            }
        }

        FiltroLogradouroTipo filtroLogradouroTipo = new FiltroLogradouroTipo();
        filtroLogradouroTipo.adicionarParametro(new ParametroSimples(FiltroLogradouroTipo.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
        Collection<LogradouroTipo> logradouroTipoCollection = Fachada.getInstancia().pesquisar(filtroLogradouroTipo, LogradouroTipo.class.getName());

        arquivoTextoRegistroTipoTrailer.append(new GeradorRegistroTipoLogradouro(logradouroTipoCollection).build());
        qtdRegistro += logradouroTipoCollection.size();
        arquivoTextoRegistroTipoTrailer.append(new GeradorRegistroClasseSocial().build());
        qtdRegistro += ClasseSocial.values().length;
        arquivoTextoRegistroTipoTrailer.append(new GeradorRegistroTipoUsoImovel().build());
        qtdRegistro += TipoUsoImovel.values().length;
        arquivoTextoRegistroTipoTrailer.append(new GeradorRegistroAcessoHidrometro().build());
        qtdRegistro += AcessoHidrometro.values().length;

        retorno[0] = arquivoTextoRegistroTipoTrailer;
        retorno[1] = qtdRegistro;

        return retorno;
    }

	/**
	 *
	 *
	 * @author bruno
	 * @date 12/01/2009
	 *
	 * @param indicadorTipoFeriado
	 * @param anoOrigemFeriado
	 * @param anoDestinoFeriado
	 */
	public void espelharFeriados(String indicadorTipoFeriado,
			String anoOrigemFeriado, String anoDestinoFeriado)
			throws ControladorException {

		try {
			// Realizamos a pesquisa para os feriados nacionais
			Collection<NacionalFeriado> nacionais = null;

			// Verificamos de o ano de origem e o ano de destino são iguais
			if (Integer.parseInt(anoDestinoFeriado) <= Integer
					.parseInt(anoOrigemFeriado)) {
				throw new ControladorException(
						"atencao.ano_origem_e_destino_iguais");
			}

			if (indicadorTipoFeriado.equals("1")
					|| indicadorTipoFeriado.equals("3")) {
				// Excluimos os feriados
				repositorioCadastro.excluirFeriadosNacionais(anoDestinoFeriado);

				nacionais = repositorioCadastro
						.pesquisarFeriadosNacionais(anoOrigemFeriado);

				for (NacionalFeriado nacional : nacionais) {
					// [FS0003] - Verificar existência do feriado
					FiltroNacionalFeriado filtro = new FiltroNacionalFeriado();

					filtro
							.adicionarParametro(new ParametroSimples(
									FiltroNacionalFeriado.NOME, nacional
											.getDescricao()));

					Calendar c = GregorianCalendar.getInstance();
					c.setTime(nacional.getData());
					c.set(Calendar.YEAR, Integer.parseInt(anoDestinoFeriado));

					filtro.adicionarParametro(new ParametroSimples(
							FiltroNacionalFeriado.DATA, c.getTime()));

					Collection<NacionalFeriado> colFeriadoEncontrado = repositorioUtil
							.pesquisar(filtro, NacionalFeriado.class.getName());

					if (colFeriadoEncontrado != null
							&& colFeriadoEncontrado.size() > 0) {
						throw new ControladorException(
								"atencao.nacional_feriado_com_data_existente");
					}

					// Colocamos a data do feriado com 1 ano na frente
					nacional.setData(c.getTime());
					nacional.setUltimaAlteracao(new Date());
				}
			}

			// Realizamos a pesquisa para os feriados Municipais
			Collection<MunicipioFeriado> municipais = null;

			if (indicadorTipoFeriado.equals("2")
					|| indicadorTipoFeriado.equals("3")) {

				// Excluimos os feriados
				repositorioCadastro
						.excluirFeriadosMunicipais(anoDestinoFeriado);

				municipais = repositorioCadastro
						.pesquisarFeriadosMunicipais(anoOrigemFeriado);

				for (MunicipioFeriado municipal : municipais) {
					// [FS0003] - Verificar existência do feriado por descrição
					FiltroMunicipioFeriado filtro = new FiltroMunicipioFeriado();

					filtro.adicionarParametro(new ParametroSimples(
							FiltroMunicipioFeriado.NOME, municipal
									.getDescricaoFeriado()));

					Calendar c = GregorianCalendar.getInstance();
					c.setTime(municipal.getDataFeriado());
					c.set(Calendar.YEAR, Integer.parseInt(anoDestinoFeriado));

					filtro.adicionarParametro(new ParametroSimples(
							FiltroMunicipioFeriado.DATA, c.getTime()));

					Collection<MunicipioFeriado> colFeriadoEncontrado = repositorioUtil
							.pesquisar(filtro, MunicipioFeriado.class.getName());

					if (colFeriadoEncontrado != null
							&& colFeriadoEncontrado.size() > 0) {
						throw new ControladorException(
								"atencao.municipio_feriado_com_data_existente");
					}

					municipal.setDataFeriado(c.getTime());
					municipal.setUltimaAlteracao(new Date());
				}
			}

			// [FS0010] Verificar existencia de feriados para o ano de origem
			if ((indicadorTipoFeriado.equals("1") && (nacionais == null || nacionais
					.size() == 0))
					|| (indicadorTipoFeriado.equals("2") && (municipais == null || municipais
							.size() == 0))
					|| (indicadorTipoFeriado.equals("3") && ((nacionais == null || nacionais
							.size() == 0) || (nacionais == null || nacionais
							.size() == 0)))) {
				throw new ControladorException(
						"atencao.ano_origem_sem_feriados");
			}

			// Inserimos os dados
			if (nacionais != null) {
				for (NacionalFeriado nacional : nacionais) {
					repositorioUtil.inserir(nacional);
				}
			}

			if (municipais != null) {
				for (MunicipioFeriado municipal : municipais) {
					repositorioUtil.inserir(municipal);
				}
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0880] - Gerar Movimento de Extensao de Contas em Cobranca por Empresa
	 *
	 * @author Rômulo Aurélio
	 * @date 09/02/2009
	 *
	 * @param idRota
	 * @param anoMesReferencia
	 * @return boolean
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidades() throws ControladorException {

		Collection retorno = null;

		try {
			retorno = repositorioCadastro.pesquisarLocalidades();
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;

	}

	/**
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral
	 */
	public List<ArquivoTextoAtualizacaoCadastral> pesquisarArquivoTextoAtualizacaoCadastro(String idEmpresa, String idLocalidade,
			String codigoSetorComercial, String idAgenteComercial, String idSituacaoTransmissao, String exibicao) throws ControladorException {

		try {
			List<ArquivoTextoAtualizacaoCadastral> arquivos = new ArrayList();

			List<ArquivoTextoAtualizacaoCadastral> colecao = repositorioCadastro.pesquisarArquivoTextoAtualizacaoCadastro(
					idEmpresa, idLocalidade, codigoSetorComercial, idAgenteComercial, idSituacaoTransmissao, exibicao);

			for (ArquivoTextoAtualizacaoCadastral arquivo : colecao) {
				Integer quantidadeImoveisTransmitidos = repositorioCadastro.pesquisarQuantidadeImoveisTransmitidosAtualizacaoCadastral(arquivo.getId());
				arquivo.setQuantidadeImoveisTransmitidos(quantidadeImoveisTransmitidos);
				arquivos.add(arquivo);
			}

			return arquivos;
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public ArquivoTextoAtualizacaoCadastral pesquisarArquivoTextoAtualizacaoCadastro(Integer idArquivoTxt) throws ControladorException {
		ArquivoTextoAtualizacaoCadastral arquivo = null;

		try {
			arquivo = repositorioCadastro.pesquisarArquivoTextoAtualizacaoCadastro(idArquivoTxt);

			Integer quantidadeImoveisTransmitidos = repositorioCadastro.pesquisarQuantidadeImoveisTransmitidosAtualizacaoCadastral(arquivo.getId());
			arquivo.setQuantidadeImoveisTransmitidos(quantidadeImoveisTransmitidos);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return arquivo;
	}

	public Collection<ArquivoTextoAtualizacaoCadastral> pesquisarArquivoTextoAtualizacaoCadastro(
			String[] idsArquivoTxt) throws ControladorException {

		try {
			return this.repositorioCadastro.pesquisarArquivoTextoAtualizacaoCadastro(idsArquivoTxt);

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 *
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral
	 */
	public void atualizarArquivoTextoAtualizacaoCadstral(Integer idArquivoTxt) throws ControladorException {
		try {
			repositorioCadastro.atualizarArquivoTextoAtualizacaoCadstral(idArquivoTxt, SituacaoTransmissaoLeitura.EM_CAMPO);
			getControladorAtualizacaoCadastral().atualizarImovelParaSituacaoEmCampoPorArquivo(idArquivoTxt);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Método para verificar o Cliente é um funcionário
	 *
	 * @author Vinicius Medeiros
	 * @date 08/04/2009
	 *
	 * @param idCliente
	 * @return
	 * @throws ControladorException
	 */

	public Integer clienteSelecionadoFuncionario(Integer idCliente)
			throws ControladorException {

		try {
			return this.repositorioCadastro
					.verificarClienteSelecionadoFuncionario(idCliente);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0024] Inserir Quadra
	 *
	 * @author Raphael Rossiter
	 * @date 03/04/2009
	 *
	 * @param quadraFaceNova
	 * @param colecaoQuadraFace
	 * @throws ControladorException
	 */
	public void validarQuadraFace(QuadraFace quadraFaceNova,
			Collection colecaoQuadraFace, boolean verificarExistencia)
			throws ControladorException {

		/*
		 * Bacia (Caso o Indicador de Rede de Esgoto seja com rede de esgoto ou
		 * rede de esgoto parcial deve ser informado; caso contrário, não
		 * informar
		 */
		if (quadraFaceNova.getIndicadorRedeEsgoto() != null
				&& (quadraFaceNova.getIndicadorRedeEsgoto().equals(
						QuadraFace.COM_REDE) || quadraFaceNova
						.getIndicadorRedeEsgoto().equals(QuadraFace.PARCIAL))
				&& quadraFaceNova.getBacia() == null) {

			throw new ControladorException("atencao.campo.informada", null,
					"Bacia");
		}

		/*
		 * Distrito Operacional (Caso o Indicador de Rede de Água seja com rede
		 * de água ou rede de água parcial deve ser informado; caso contrário,
		 * não informar).
		 */
		if (quadraFaceNova.getIndicadorRedeAgua() != null
				&& (quadraFaceNova.getIndicadorRedeAgua().equals(
						QuadraFace.COM_REDE) || quadraFaceNova
						.getIndicadorRedeAgua().equals(QuadraFace.PARCIAL))
				&& quadraFaceNova.getDistritoOperacional() == null) {

			throw new ControladorException("atencao.campo.informado", null,
					"Distrito Operacional");
		}

		if (verificarExistencia) {

			// [FS0013] - Verificar existência da face da quadra
			if (colecaoQuadraFace != null && !colecaoQuadraFace.isEmpty()) {

				Iterator it = colecaoQuadraFace.iterator();

				while (it.hasNext()) {

					QuadraFace quadraFaceJaCadastrada = (QuadraFace) it.next();

					if (quadraFaceJaCadastrada.getNumeroQuadraFace().equals(
							quadraFaceNova.getNumeroQuadraFace())) {

						throw new ControladorException(
								"atencao.quadra_face_ja_informada", null,
								quadraFaceNova.getNumeroQuadraFace().toString());
					}
				}
			}
		}
	}

	/**
	 * Pesquisa a Quadra Face atraves da quadra associada
	 *
	 * Autor: Arthur Carvalho
	 *
	 * Data: 28/04/2009
	 */
	public Collection<Object[]> pesquisarQuadraFaceAssociadaQuadra(
			Integer idQuadra) throws ControladorException {

		Collection<Object[]> quadraFace = null;

		try {
			quadraFace = repositorioCadastro
					.pesquisarQuadraFaceAssociadaQuadra(idQuadra);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return quadraFace;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see gcom.cadastro.ControladorCadastroLocal#validarSeClienteEhPessoaJuridica(Cliente)
	 */
	public void validarSeClienteEhPessoaJuridica(Cliente cliente)
			throws ControladorException {

		if (cliente == null)
			throw new ControladorException("atencao.cliente.inexistente");

		if (cliente.getClienteTipo() == null)
			throw new ControladorException("atencao.cliente.tipo.inexistente");

		if (!cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica()
				.equals(ClienteTipo.INDICADOR_PESSOA_JURIDICA))
			throw new ControladorException(
					"atencao.cliente.tipo.pessoa_juridica");

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see gcom.cadastro.ControladorCadastroLocal#validarSeDebitoTipoNaoEhGeradoAutomaticamente(DebitoTipo)
	 */
	public void validarSeDebitoTipoNaoEhGeradoAutomaticamente(
			DebitoTipo debitoTipo) throws ControladorException {

		if (debitoTipo == null)
			throw new ControladorException("atencao.debito_tipo.inexistente");

		if (!debitoTipo.getIndicadorGeracaoAutomatica().equals(
				ConstantesSistema.SIM))
			throw new ControladorException(
					"atencao.debito_tipo.gerado_automaticamente");

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see gcom.cadastro.ControladorCadastroLocal#validarPreExistenciaEntidadeBeneficente(EntidadeBeneficente)
	 */
	public void validarPreExistenciaEntidadeBeneficente(
			EntidadeBeneficente entidadeBeneficente)
			throws ControladorException {

		if (entidadeBeneficente == null)
			throw new ControladorException("atencao.debito_tipo.inexistente");

		Cliente cliente = entidadeBeneficente.getCliente();

		if (cliente == null)
			throw new ControladorException("atencao.cliente.inexistente");

		FiltroEntidadeBeneficente filtroEntidadeBeneficente = new FiltroEntidadeBeneficente();
		filtroEntidadeBeneficente.adicionarParametro(new ParametroSimples(
				FiltroEntidadeBeneficente.ID_CLIENTE, cliente.getId()));

		Collection<EntidadeBeneficente> entidadesDoMesmoCliente = getControladorUtil()
				.pesquisar(filtroEntidadeBeneficente,
						EntidadeBeneficente.class.getName());

		if (entidadesDoMesmoCliente != null
				&& !entidadesDoMesmoCliente.isEmpty()) {

			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, cliente.getId()));

			cliente = (Cliente) Util
					.retonarObjetoDeColecao(getControladorUtil().pesquisar(
							filtroCliente, Cliente.class.getName()));

			throw new ControladorException(
					"atencao.entidade_beneficente.cliente.pre_existente", null,
					cliente.getNome());
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see gcom.cadastro.ControladorCadastroLocal#inserirEntidadeBeneficente(EntidadeBeneficente)
	 */
	public Integer inserirEntidadeBeneficente(
			EntidadeBeneficente entidadeBeneficente)
			throws ControladorException {
		Integer retorno = null;

		Cliente cliente = entidadeBeneficente.getCliente();

		if (cliente.getId() != null && !new Integer(0).equals(cliente.getId())) {
			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, cliente.getId()));
			filtroCliente
					.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");

			Collection colecaoCliente = getControladorUtil().pesquisar(
					filtroCliente, Cliente.class.getName());

			// [FS0001] - Verificar existência do cliente
			if (colecaoCliente == null || colecaoCliente.isEmpty()) {
				throw new ControladorException("atencao.cliente.inexistente",
						null, "Cliente");
			} else {
				Cliente clienteEncontrado = (Cliente) Util
						.retonarObjetoDeColecao(colecaoCliente);

				// [FS0002] - Verificar se cliente é pessoa jurídica
				validarSeClienteEhPessoaJuridica(clienteEncontrado);

			}
		} else {
			throw new ControladorException("atencao.cliente.inexistente", null,
					"Cliente");
		}

		DebitoTipo debitoTipo = entidadeBeneficente.getDebitoTipo();

		if (debitoTipo.getId() != null
				&& !new Integer(0).equals(debitoTipo.getId())) {

			FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
			filtroDebitoTipo.adicionarParametro(new ParametroSimples(
					FiltroDebitoTipo.ID, debitoTipo.getId()));

			Collection colecaoDebitoTipo = getControladorUtil().pesquisar(
					filtroDebitoTipo, DebitoTipo.class.getName());

			// [FS0003] - Verificar existência do tipo de débito
			if (colecaoDebitoTipo == null || colecaoDebitoTipo.isEmpty()) {
				throw new ControladorException(
						"atencao.debito_tipo.inexistente", null,
						"Tipo de Débito");
			} else {
				DebitoTipo debitoTipoEncontrado = (DebitoTipo) Util
						.retonarObjetoDeColecao(colecaoDebitoTipo);

				// [FS0004] Verificar se tipo de débito não é gerado
				// automaticamente
				validarSeDebitoTipoNaoEhGeradoAutomaticamente(debitoTipoEncontrado);

			}
		} else {
			throw new ControladorException("atencao.debito_tipo.inexistente",
					null, "Tipo de Débito");
		}

		Empresa empresa = entidadeBeneficente.getEmpresa();

		if (empresa == null || empresa.getId() == null
				|| new Integer(0).equals(empresa.getId()))
			throw new ControladorException("atencao.campo_texto.obrigatorio",
					null, "Empresa");

		// [FS0006] - Verificar pré-existência da entidade beneficente
		validarPreExistenciaEntidadeBeneficente(entidadeBeneficente);

		// Toda entidade beneficente é inserida por padrão como ativa
		entidadeBeneficente
				.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

		retorno = (Integer) getControladorUtil().inserir(entidadeBeneficente);

		return retorno;
	}

	/**
	 * [UC0842] Inserir Funcionário
	 *
	 * @author Raphael Rossiter
	 * @date 17/06/2009
	 *
	 * @param funcionario
	 * @param acao ->
	 *            INSERIR = TRUE, ATUALIZAR = FALSE
	 * @throws ControladorException
	 */
	public void validarFuncionario(Funcionario funcionario, boolean acao)
			throws ControladorException {

		FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
		Funcionario funcionarioJaCadastrado = null;

		if (acao) {

			// MATRÍCULA
			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, funcionario.getId().toString()));

			// Pesquisa se existe algum funcionario com a matricula informada

			Collection colecaoFuncionarioMatricula = getControladorUtil()
					.pesquisar(filtroFuncionario, Funcionario.class.getName());

			if (colecaoFuncionarioMatricula != null
					&& !colecaoFuncionarioMatricula.isEmpty()) {

				throw new ControladorException(
						"atencao.funcionario_matricula_ja_existente");

			}

		}

		// NOME
		if (funcionario.getNome() == null
				|| funcionario.getNome().equalsIgnoreCase("")) {
			throw new ControladorException("atencao.required", null, "Nome");
		}

		// CPF
		if (funcionario.getNumeroCpf() != null
				&& !funcionario.getNumeroCpf().equals("")) {

			// CPF INVÁLIDO
			if (!Util.validacaoCPF(funcionario.getNumeroCpf())) {

				throw new ControladorException("atencao.cpf_invalido");
			}

			// CPF JÁ CADASTRADO
			filtroFuncionario.limparListaParametros();

			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.NUMERO_CPF, funcionario.getNumeroCpf()));

			Collection colecaoFuncionario = this.getControladorUtil()
					.pesquisar(filtroFuncionario, Funcionario.class.getName());

			if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {

				funcionarioJaCadastrado = (Funcionario) colecaoFuncionario
						.iterator().next();

				if (acao) {

					// VALIDAÇÃO PARA INSERIR FUNCIONÁRIO
					throw new ControladorException(
							"atencao.cpf.funcionario.ja_cadastrado", null, ""
									+ funcionarioJaCadastrado.getId());
				} else if (funcionarioJaCadastrado.getId().intValue() != funcionario
						.getId().intValue()) {

					// VALIDAÇÃO PARA ATUALIZAR FUNCIONÁRIO
					throw new ControladorException(
							"atencao.cpf.funcionario.ja_cadastrado", null, ""
									+ funcionarioJaCadastrado.getId());
				}

			}
		}

		// DATA DE NASCIMENTO
		if (funcionario.getDataNascimento() != null
				&& !funcionario.getDataNascimento().equals("")) {

			int idadeFuncionario = Util.anosEntreDatas(funcionario
					.getDataNascimento(), new Date());

			if (idadeFuncionario < ConstantesSistema.IDADE_MINIMA_FUNCIONARIO) {

				throw new ControladorException(
						"atencao.funcionario_idade_minima");
			}
		}

		// CARGO
		if (funcionario.getFuncionarioCargo() != null
				&& funcionario.getFuncionarioCargo().getId().toString()
						.equalsIgnoreCase(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			throw new ControladorException("atencao.required", null, "Cargo");

		}

		// EMPRESA
		if (funcionario.getEmpresa() != null
				&& funcionario.getEmpresa().getId().toString()
						.equalsIgnoreCase(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			throw new ControladorException("atencao.required", null, "Empresa");

		}

		// VERIFICANDO SE O USUÁRIO JÁ FOI CADASTRADO
		filtroFuncionario.limparListaParametros();

		filtroFuncionario.adicionarParametro(new ParametroSimples(
				FiltroFuncionario.NOME, funcionario.getNome()));

		filtroFuncionario.adicionarParametro(new ParametroSimples(
				FiltroFuncionario.UNIDADE_EMPRESA, funcionario.getEmpresa()
						.getId().toString()));

		filtroFuncionario.adicionarParametro(new ParametroSimples(
				FiltroFuncionario.UNIDADE_ORGANIZACIONAL_ID, funcionario
						.getUnidadeOrganizacional().getId().toString()));

		Collection colecaoFuncionario = getControladorUtil().pesquisar(
				filtroFuncionario, Funcionario.class.getName());

		if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {

			funcionarioJaCadastrado = (Funcionario) colecaoFuncionario
					.iterator().next();

			if (acao) {

				throw new ControladorException(
						"atencao.funcionario_ja_existente");
			} else if (funcionarioJaCadastrado.getId().intValue() != funcionario
					.getId().intValue()) {

				throw new ControladorException(
						"atencao.funcionario_ja_existente");
			}
		}
	}

	/**
	 * [UC0830] Gerar Tabelas para Atualização Cadastral via celular
	 *
	 * @author Ana Maria
	 * @date 22/06/2009
	 *
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */

	public Collection<Integer> pesquisarSetorComercialGeracaoTabelasTemporarias(
			ImovelGeracaoTabelasTemporariasCadastroHelper helper)
			throws ControladorException {

		Collection<Integer> idsSetor = null;

		try {
			idsSetor = repositorioCadastro
					.pesquisarSetorComercialGeracaoTabelasTemporarias(helper);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return idsSetor;
	}

	/**
	 * [UC0830] Gerar Tabelas para Atualização Cadastral via celular
	 *
	 * @author Ana Maria
	 * @date 22/06/2009
	 *
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */

	public Collection<Integer> obterIdsImovelGeracaoTabelasTemporarias(
			ImovelGeracaoTabelasTemporariasCadastroHelper helper)
			throws ControladorException {
		Collection colecaoIdsImovel = new ArrayList();

		try {

			colecaoIdsImovel = repositorioCadastro
					.obterIdsImovelGeracaoTabelasTemporarias(null, helper);

			if (helper.getImovelSituacao() != null
					&& new Integer(helper.getImovelSituacao()) == 2) {
				colecaoIdsImovel = repositorioCadastro
						.pesquisarImovelDebitoAtualizacaoCadastral(colecaoIdsImovel);
			}

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return colecaoIdsImovel;
	}

	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 *
	 * @author Vivianne Sousa
	 * @date 25/06/2009
	 *
	 * @param idEmpresa
	 * @param data
	 * @throws ControladorException
	 */
	public Object[] gerarBoletimCustoAtualizacaoCadastral(Empresa empresa,
			Date dataAtualizacaoInicio, Date dataAtualizacaoFim)
			throws ControladorException {

		try {

			Object[] retorno = new Object[2];
			// TreeMap<AtributosBoletimChaveHelper, AtributosBoletimHelper>
			// 3. O sistema obtém os dados do contrato com a empresa
			EmpresaContratoCadastro empresaContratoCadastro = repositorioCadastro
					.pesquisarEmpresaContratoCadastro(empresa.getId());

			// [FS0001 ? Verificar existência de contrato vigente para a
			// empresa].
			if (empresaContratoCadastro == null) {
				throw new ControladorException(
						"atencao.nao_existe_contrato_vigente_empresa", null,
						empresa.getDescricao());
			}

			// 4. O sistema seleciona os atributos que compõem o boletim
			// (a partir da tabela ATRIBUTO ordenando pelo grupo do atributo
			// (ATGR_ID) e pela ordem de emissão (ATRB_NNORDEMEMISSAO)).
			Collection colecaoAtributos = repositorioCadastro
					.pesquisarAtributosBoletim();
			Iterator iterAtributos = colecaoAtributos.iterator();

			// 5. O sistema cria uma Lista de Atributos do Boletim e atribui
			// valores aos campos da lista
			TreeMap<AtributosBoletimChaveHelper, AtributosBoletimHelper> mapAtributosBoletim = new TreeMap();

			// 5.7. Quantidade de Atualizações do Atributo (valor zero).
			Integer quantidadeAtualizacaoAtributo = 0;

			while (iterAtributos.hasNext()) {

				Atributo atributo = (Atributo) iterAtributos.next();

				// 5.6. Valor de Atualização do Atributo
				// (ECCA_VLATUALIZACAO da tabela
				// EMPRESA_CONTRATO_CADASTRO_ATRIBUTO
				// com ATRB_ID=ATRB_ID da tabela ATRIBUTO e ECCD_ID=ECCD_ID da
				// tabela EMPRESA_CONTRATO_CADASTRO);
				BigDecimal valorAtualizacaoAtributo = repositorioCadastro
						.pesquisarValorAtualizacaoAtributo(atributo.getId(),
								empresaContratoCadastro.getId());

				AtributosBoletimChaveHelper chave = new AtributosBoletimChaveHelper(
						atributo.getId(), atributo.getAtributoGrupo().getId(),
						atributo.getNumeroOrdemEmissao());

				AtributosBoletimHelper atributosBoletim = new AtributosBoletimHelper(
						atributo, valorAtualizacaoAtributo,
						quantidadeAtualizacaoAtributo);

				mapAtributosBoletim.put(chave, atributosBoletim);

			}

			// 6. O sistema seleciona as operações efetuadas pela empresa no
			// período informado e com imóvel associado
			// [SB0001 ? Selecionar Operações Efetuadas com Imóvel Associado].
			Collection colecaoOperacoesEfetuadasComImovelAssociado = repositorioCadastro
					.pesquisarOperacoesEfetuadasComImovelAssociado(
							dataAtualizacaoInicio, dataAtualizacaoFim, empresa
									.getId());

			// 7. O sistema seleciona as operações efetuadas pela empresa no
			// período informado e sem imóvel associado
			// [SB0002 ? Selecionar Operações Efetuadas sem Imóvel Associado].
			Collection colecaoOperacoesEfetuadasSemImovelAssociado = repositorioCadastro
					.pesquisarOperacoesEfetuadasSemImovelAssociado(
							dataAtualizacaoInicio, dataAtualizacaoFim, empresa
									.getId());

			// 8. Caso as seleções não retornem nenhum registro,
			// o sistema deverá exibir a mensagem "A pesquisa não retornou
			// nenhum resultado" e retornar para a tela de parâmetros.
			// 9.2. Atribui à lista as operações efetuadas sem imóvel associado.
			// Neste caso, o Conteúdo do Argumento deve corresponder ao conteúdo
			// do segundo argumento (TBLA_ID2).
			if ((colecaoOperacoesEfetuadasComImovelAssociado == null || colecaoOperacoesEfetuadasComImovelAssociado
					.isEmpty())
					&& (colecaoOperacoesEfetuadasSemImovelAssociado == null || colecaoOperacoesEfetuadasSemImovelAssociado
							.isEmpty())) {
				throw new ControladorException(
						"atencao.pesquisa.nenhumresultado");
			}

			// 9. O sistema cria uma Lista de Operações Efetuadas a partir das
			// seleções realizadas:
			// 9.1. Atribui à lista as operações efetuadas com imóvel associado.
			// 9.2. Atribui à lista as operações efetuadas sem imóvel associado.
			Collection colecaoOperacoesEfetuadas = new ArrayList();
			if (colecaoOperacoesEfetuadasComImovelAssociado != null
					&& !colecaoOperacoesEfetuadasComImovelAssociado.isEmpty()) {
				colecaoOperacoesEfetuadas
						.addAll(colecaoOperacoesEfetuadasComImovelAssociado);
			}
			if (colecaoOperacoesEfetuadasSemImovelAssociado != null
					&& !colecaoOperacoesEfetuadasSemImovelAssociado.isEmpty()) {
				colecaoOperacoesEfetuadas
						.addAll(colecaoOperacoesEfetuadasSemImovelAssociado);
			}

			// 10. ordena a Lista de Operações Efetuadas pelos campos Conteúdo
			// do Argumento e Identificador do Atributo (ATRB_ID).
			Collections.sort((List) colecaoOperacoesEfetuadas,
					new Comparator() {

						public int compare(Object a, Object b) {

							int retorno = 0;
							OperacoesEfetuadasHelper helper1 = (OperacoesEfetuadasHelper) a;
							OperacoesEfetuadasHelper helper2 = (OperacoesEfetuadasHelper) b;

							if (helper1.getArgumento().compareTo(
									helper2.getArgumento()) == 0) {

								retorno = helper1
										.getId2TabelaLinhaAlteracao()
										.compareTo(
												helper2
														.getId2TabelaLinhaAlteracao());

							} else {
								retorno = helper1.getArgumento().compareTo(
										helper2.getArgumento());
							}
							return retorno;

						}
					});

			BigDecimal valorVisita = empresaContratoCadastro.getValorVisita();
			Integer argumentoAnterior = null;
			Integer argumento = null;

			Collection colecaoOperacoesEfetuadasArgumento = new ArrayList();

			Integer numeroImoveisAtualizados = 0;

			// 11.4. Enquanto houver operações na Lista de Operações Efetuadas
			// para serem processadas:
			Iterator iterOperacoesEfetuadas = colecaoOperacoesEfetuadas
					.iterator();
			while (iterOperacoesEfetuadas.hasNext()) {

				OperacoesEfetuadasHelper operacoesEfetuadas = (OperacoesEfetuadasHelper) iterOperacoesEfetuadas
						.next();

				// quando for a primeira vez
				if (argumentoAnterior == null) {
					argumentoAnterior = operacoesEfetuadas.getArgumento();
				}
				argumento = operacoesEfetuadas.getArgumento();

				// mudou o argumento
				if (argumentoAnterior.compareTo(argumento) != 0) {

					// cria uma Lista de Atributos por Argumento e acumula os
					// valores na Lista de Atributos do Boletim
					processaAtributosArgumentoEAcumulaValores(
							colecaoOperacoesEfetuadasArgumento,
							mapAtributosBoletim, valorVisita);

					// 11.4.1. Número de Imóveis Atualizados = Número de Imóveis
					// Atualizados mais um.
					numeroImoveisAtualizados++;

					argumentoAnterior = operacoesEfetuadas.getArgumento();
					colecaoOperacoesEfetuadasArgumento.clear();
				}

				colecaoOperacoesEfetuadasArgumento.add(operacoesEfetuadas);
			}
			// ultimo argumento
			// cria uma Lista de Atributos por Argumento e acumula os valores na
			// Lista de Atributos do Boletim
			processaAtributosArgumentoEAcumulaValores(
					colecaoOperacoesEfetuadasArgumento, mapAtributosBoletim,
					valorVisita);
			numeroImoveisAtualizados++;

			retorno[0] = mapAtributosBoletim;
			retorno[1] = numeroImoveisAtualizados;

			return retorno;

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 *
	 * cria uma Lista de Atributos por Argumento e acumula os valores na Lista
	 * de Atributos do Boletim
	 *
	 * @author Vivianne Sousa
	 * @date 25/06/2009
	 */
	public void processaAtributosArgumentoEAcumulaValores(
			Collection colecaoOperacoesEfetuadasArgumento,
			TreeMap<AtributosBoletimChaveHelper, AtributosBoletimHelper> mapAtributosBoletim,
			BigDecimal valorVisita) {

		AtributosBoletimHelper atributosArgumento = null;
		BigDecimal valorAtualizacoesArgumento = BigDecimal.ZERO;
		Collection<AtributosBoletimHelper> colecaoAtributosPorArgumento = new ArrayList();
		Iterator iterOperacoesEfetuadasArgumento = colecaoOperacoesEfetuadasArgumento
				.iterator();

		while (iterOperacoesEfetuadasArgumento.hasNext()) {
			OperacoesEfetuadasHelper operacoesEfetuadasArgumento = (OperacoesEfetuadasHelper) iterOperacoesEfetuadasArgumento
					.next();
			// System.out.println("-----" +
			// operacoesEfetuadasArgumento.getArgumento());

			// 11.3.4. Para cada atributo da Lista de Atributos por Argumento, o
			// sistema obtém o valor da atualização por atributo
			BigDecimal valorAtualizacaoAtributo = operacoesEfetuadasArgumento
					.getValorAtualizacaoAtributo();

			// 11.3.5. Valor das Atualizações Efetuadas para o Argumento =
			// somatório de Valor de Atualização do Atributo do Argumento.
			valorAtualizacoesArgumento = valorAtualizacoesArgumento
					.add(valorAtualizacaoAtributo);

			// 11.3.2.1. Identificador do Atributo do Argumento = ATRB_ID;
			// 11.3.2.2. Quantidade de Atualizações do Atributo do Argumento =
			// um (1).
			atributosArgumento = new AtributosBoletimHelper(
					operacoesEfetuadasArgumento
							.getAtributosBoletimChaveHelper().getIdAtributo(),
					valorAtualizacaoAtributo, 1, operacoesEfetuadasArgumento
							.getAtributosBoletimChaveHelper());

			colecaoAtributosPorArgumento.add(atributosArgumento);

		}

		AtributosBoletimChaveHelper chave = null;
		// 11.3.6.1. Caso o Valor das Atualizações Efetuadas para o Argumento
		// não atinja o valor mínimo
		// (Valor das Atualizações Efetuadas para o Argumento menor que
		// ECCD_VLVISITA da tabela EMPRESA_CONTRATO_CADASTRO)
		// if(valorAtualizacoesArgumento.compareTo(valorVisita) < 0){
		// //11.3.6.1.1. Quantidade de Atualizações do Atributo da Lista
		// //de Atributos do Boletim para o atributo de visita
		// //(Identificador do Atributo da Lista de Atributos do Boletim com
		// Indicador de Visita=1)
		// //= Quantidade de Atualizações do Atributo da Lista de Atributos do
		// Boletim para o atributo de visita
		// //(Identificador do Atributo da Lista de Atributos do Boletim com
		// Indicador de Visita=1) mais um (1).
		// chave = AtributosBoletimChaveHelper.NOTIFICACAO_VISITA;
		//
		// AtributosBoletimHelper AtributosBoletimAlterar =
		// mapAtributosBoletim.get(chave);
		//
		// int quantidade =
		// AtributosBoletimAlterar.getQuantidadeAtualizacaoAtributo().intValue()
		// + 1;
		//
		// AtributosBoletimAlterar.setQuantidadeAtualizacaoAtributo(quantidade);
		//
		// }else{
		// 11.3.6.2. Caso contrário, para cada atributo da Lista de Atributos
		// por Argumento:
		// 11.3.6.2.1. Quantidade de Atualizações do Atributo da Lista
		// de Atributos do Boletim para o atributo (Identificador do Atributo do
		// Argumento)
		// = Quantidade de Atualizações do Atributo da Lista de Atributos do
		// Boletim para o atributo
		// (Identificador do Atributo do Argumento) mais Quantidade de
		// Atualizações do Atributo do Argumento

		Iterator iterAtributosPorArgumento = colecaoAtributosPorArgumento
				.iterator();
		while (iterAtributosPorArgumento.hasNext()) {
			AtributosBoletimHelper atribBoletim = (AtributosBoletimHelper) iterAtributosPorArgumento
					.next();
			chave = atribBoletim.getAtributosBoletimChaveHelper();

			AtributosBoletimHelper AtributosBoletimAlterar = mapAtributosBoletim
					.get(chave);

			int quantidade = AtributosBoletimAlterar
					.getQuantidadeAtualizacaoAtributo().intValue()
					+ atribBoletim.getQuantidadeAtualizacaoAtributo()
							.intValue();

			AtributosBoletimAlterar
					.setQuantidadeAtualizacaoAtributo(quantidade);

		}
		// }

	}

	/**
	 * [UC0925] Emitir Boletos
	 *
	 * @author Vivianne Sousa
	 * @date 10/07/2009
	 */
	public void emitirBoletos(Integer idFuncionalidadeIniciada, Integer grupo)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		try {
			/*
			 * Registrar o início do processamento da Unidade de Processamento
			 * do Batch
			 */
			idUnidadeIniciada = getControladorBatch()
					.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
							UnidadeProcessamento.FUNCIONALIDADE, 0);

			boolean flagFimPesquisa = false;
			final int quantidadeCobrancaDocumento = 1000;
			int quantidadeInicio = 0;
			StringBuilder boletoTxt = new StringBuilder();

			System.out.println("***************************************");
			System.out.println("EMITIR BOLETOS");
			System.out.println("***************************************");

			SistemaParametro sistemaParametro = getControladorUtil()
					.pesquisarParametrosDoSistema();

			while (!flagFimPesquisa) {
				Collection dadosBoleto = repositorioCadastro
						.pesquisarDadosBoleto(quantidadeInicio, grupo,
								sistemaParametro.getNomeAbreviadoEmpresa());

				if (dadosBoleto != null && !dadosBoleto.isEmpty()) {

					Iterator iterDadosBoleto = dadosBoleto.iterator();

					if (dadosBoleto.size() < quantidadeCobrancaDocumento) {
						flagFimPesquisa = true;
					} else {
						quantidadeInicio = quantidadeInicio + 1000;
					}

					System.out
							.println("***************************************");
					System.out.println("QUANTIDADE :" + dadosBoleto.size());
					System.out
							.println("***************************************");

					while (iterDadosBoleto.hasNext()) {
						DadosBoletoHelper helper = (DadosBoletoHelper) iterDadosBoleto
								.next();

						// 1.1 Inscrição
						boletoTxt.append(Util.completaString(helper.getImovel()
								.getInscricaoFormatada(), 20));

						// 1.2 Matrícula Imóvel
						String matriculaStr = Util.adicionarZerosEsquedaNumero(
								9, "" + helper.getImovel().getId());
						boletoTxt.append(matriculaStr.substring(0, 8) + "."
								+ matriculaStr.substring(8, 9));

						// 1.3 Nome Cliente Usuário
						boletoTxt.append(Util.completaString(helper
								.getNomeCliente(), 40));

						// 1.4 Endereço do Imóvel
						String endereco = getControladorEndereco()
								.pesquisarEnderecoFormatado(
										helper.getImovel().getId());
						boletoTxt.append(Util.completaString(endereco, 60));

						// 1.5 Grupo de Faturamento
						boletoTxt.append(Util.adicionarZerosEsquedaNumero(2,
								helper.getIdGrupoFaturamento().toString()));

						// 1.6 Empresa
						boletoTxt.append(helper.getIdEmpresa().toString());

						// 1.7 Representação Numérica do Código de Barras
						// 1.8 Código de Barras

						BigDecimal valorCodigoBarras = pesquisarValorSugeridoDebitoTipo(DebitoTipo.DOACAO_AO_PRO_CRIANCA);

						String representacaoNumericaCodBarra = "";

						// Obtém a representação numérica do código de barra
						representacaoNumericaCodBarra = this
								.getControladorArrecadacao()
								.obterRepresentacaoNumericaCodigoBarra(
										4,
										valorCodigoBarras,
										helper.getImovel().getLocalidade()
												.getId(),
										helper.getImovel().getId(), null, null,
										DebitoTipo.DOACAO_AO_PRO_CRIANCA,
										"" + Util.getAno(new Date()), null,
										null, null, null,null);

						// Formata a representação númerica do código de barras
						String representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarra
								.substring(0, 11)
								+ " "
								+ representacaoNumericaCodBarra.substring(11,
										12)
								+ " "
								+ representacaoNumericaCodBarra.substring(12,
										23)
								+ " "
								+ representacaoNumericaCodBarra.substring(23,
										24)
								+ " "
								+ representacaoNumericaCodBarra.substring(24,
										35)
								+ " "
								+ representacaoNumericaCodBarra.substring(35,
										36)
								+ " "
								+ representacaoNumericaCodBarra.substring(36,
										47)
								+ " "
								+ representacaoNumericaCodBarra.substring(47,
										48);

						boletoTxt
								.append(representacaoNumericaCodBarraFormatada);

						// Cria o objeto para gerar o código de barras no padrão
						// intercalado 2 de 5
						Interleaved2of5 codigoBarraIntercalado2de5 = new Interleaved2of5();

						// Recupera a representação númerica do código de barras
						// sem os dígitos verificadores
						String representacaoCodigoBarrasSemDigitoVerificador = representacaoNumericaCodBarra
								.substring(0, 11)
								+ representacaoNumericaCodBarra.substring(12,
										23)
								+ representacaoNumericaCodBarra.substring(24,
										35)
								+ representacaoNumericaCodBarra.substring(36,
										47);

						boletoTxt
								.append(codigoBarraIntercalado2de5
										.encodeValue(representacaoCodigoBarrasSemDigitoVerificador));

						boletoTxt.append(System.getProperty("line.separator"));

					}

				} else {
					flagFimPesquisa = true;
				}

			}

			Date dataAtual = new Date();

			String nomeZip = null;

			nomeZip = "BOLETO_PRO_CRIANCA_GRUPO_" + grupo + "_"
					+ Util.formatarData(dataAtual)
					+ Util.formatarHoraSemDataSemDoisPontos(dataAtual);
			nomeZip = nomeZip.replace("/", "_");

			// pegar o arquivo, zipar pasta e arquivo e escrever no stream
			try {

				System.out.println("***************************************");
				System.out.println("INICO DA CRIACAO DO ARQUIVO");
				System.out.println("***************************************");

				if (boletoTxt != null && boletoTxt.length() != 0) {

					// criar o arquivo zip
					File compactado = new File(nomeZip + ".zip"); // nomeZip
					ZipOutputStream zos = new ZipOutputStream(
							new FileOutputStream(compactado));

					File leitura = new File(nomeZip + ".txt");
					BufferedWriter out = new BufferedWriter(
							new OutputStreamWriter(new FileOutputStream(leitura
									.getAbsolutePath())));
					out.write(boletoTxt.toString());
					out.close();
					ZipUtil.adicionarArquivo(zos, leitura);

					//	 close the stream
					zos.close();
					leitura.delete();
				}
				System.out.println("***************************************");
				System.out.println("FIM DA CRIACAO DO ARQUIVO");
				System.out.println("***************************************");

			} catch (IOException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			System.out.println("******* FIM **********");
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);
			throw new EJBException(ex);
		}

	}


	/**
	 * [UC0925] Emitir Boletos
	 *
	 * retrona DBTP_VLLIMITE para DBTP_ID = idDebitoTipo
	 *
	 * @author Vivianne Sousa
	 * @date 09/07/2009
	 *
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorLimiteDebitoTipo(Integer idDebitoTipo)
			throws ControladorException {
		try {
			return repositorioCadastro
					.pesquisarValorLimiteDebitoTipo(idDebitoTipo);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * Obtém a quantidade de economias da categoria, levando em consideração o
	 * fator de economias
	 *
	 * @author Rafael Corrêa
	 * @date 09/08/2009
	 *
	 * @throws ControladorException
	 */
	public int obterQuantidadeEconomiasCategoria(Categoria categoria)
			throws ControladorException {
		int qtd = 0;

		// Caso a categoria tenha fator de economias diferente de NULO
		if (categoria.getFatorEconomias() != null) {
			qtd = categoria.getFatorEconomias().intValue();
		} else {
			qtd = categoria.getQuantidadeEconomiasCategoria();
		}

		return qtd;
	}

	/**
	 * Obtém a quantidade de economias da subcategoria, levando em consideração
	 * o fator de economias
	 *
	 * @author Rafael Corrêa
	 * @date 09/08/2009
	 *
	 * @throws ControladorException
	 */
	public int obterQuantidadeEconomiasSubcategoria(Subcategoria subcategoria)
			throws ControladorException {
		int qtd = 0;

		// Caso a categoria tenha fator de economias diferente de NULO
		if (subcategoria.getCategoria().getFatorEconomias() != null) {
			qtd = subcategoria.getCategoria().getFatorEconomias().intValue();
		} else {
			qtd = subcategoria.getQuantidadeEconomias();
		}

		return qtd;
	}

	/**
	 * [UC0407]-Filtrar Imóveis para Inserir ou Manter Conta [FS0011]-Verificar
	 * a abrangência do código do usuário
	 *
	 * @author Vivianne Sousa
	 * @date 31/07/2009
	 *
	 * @throws ErroRepositorioException
	 */
	public UnidadeNegocio pesquisarUnidadeNegocioUsuario(Integer idUsuario)
			throws ControladorException {
		try {
			return repositorioCadastro
					.pesquisarUnidadeNegocioUsuario(idUsuario);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0928]-Manter Situação Especial de Faturamento [FS0003]-Verificar a
	 * existência do setor
	 *
	 * @author Marlon Patrick
	 * @date 11/08/2009
	 *
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaSetorComercial(Integer idSetorComercial)
			throws ControladorException {
		try {
			Integer qtdSetores = this.repositorioSetorComercial
					.verificarExistenciaSetorComercial(idSetorComercial);
			return (qtdSetores > 0);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UCXXXX] Excluir Imoveis Da Tarifa Social CRC - 2113
	 *
	 * @author Genival Barbosa
	 * @date 15/09/2009
	 */
	public void excluirImoveisDaTarifaSocial(Integer idSetor,
			Integer idFuncionalidadeIniciada, Integer anoMesFaturamento)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		try {

			Object[] dados = null;
			Object obj = null;

			idUnidadeIniciada = getControladorBatch()
					.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
							UnidadeProcessamento.SETOR_COMERCIAL, (idSetor));

			List colecao = repositorioCadastro
					.pesquisarImoveisExcluirDaTarifaSocial(idSetor,
							anoMesFaturamento);
			String idImovel = "";
			String quantidadeEconomias = "";
			String consumoMedio = "";

			if (colecao != null && !colecao.isEmpty()) {
				for (int i = 0; i < colecao.size(); i++) {
					obj = colecao.get(i);
					if (obj != null) {
						if (obj instanceof Object[]) {
							dados = (Object[]) obj;

							idImovel = dados[0].toString();
							quantidadeEconomias = dados[1].toString();
							consumoMedio = dados[2].toString();
						}
						Double quantidadeEconomiasPORConsumoMedio = Double
								.parseDouble(consumoMedio)
								/ Double.parseDouble(quantidadeEconomias);
						if (quantidadeEconomiasPORConsumoMedio > 19) {
							repositorioCadastro
									.atualizarExcluirDaTarifaSocialTabelaDadoEconomia(idImovel);
							repositorioCadastro
									.atualizarExcluirDaTarifaSocialTabelaImovel(idImovel);
						}
					}
				}
			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}

	/**
	 * Pesquisa a quantidade de imoveis para o relatorio de imoveis por consumo
	 * medio
	 *
	 * @author Arthur Carvalho
	 * @data 02/10/2009
	 *
	 * @param filtro
	 * @return quantidade de imoveis
	 * @throws FachadaException
	 */
	public Integer pesquisarRelatorioImoveisConsumoMedioCount(
			FiltrarRelatorioImoveisConsumoMedioHelper filtro)
			throws ControladorException {

		SistemaParametro sistemaParametro = this.getControladorUtil()
				.pesquisarParametrosDoSistema();

		try {

			return repositorioCadastro
					.pesquisarRelatorioImoveisConsumoMedioCount(filtro,
							sistemaParametro.getAnoMesFaturamento());

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Pesquisa a quantidade de imoveis na tabela imovel atualizacao cadastral
	 *
	 * @author Arthur Carvalho
	 * @data 02/10/2009
	 *
	 * @param filtro
	 * @return quantidade de imoveis
	 * @throws FachadaException
	 */
	public Integer pesquisarImovelAtualizacaoCadastralComIndicadorExclusaoCount()
			throws ControladorException {

		try {

			return repositorioCadastro
					.pesquisarImovelAtualizacaoCadastralComIndicadorExclusaoCount();

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0969] Importar arquivo de atualização cadastral simplificado
	 *
	 * @author Samuel Valerio, Higor Gondim
	 * @date 22/10/2009, 21/05/2010
	 *
	 * @param arquivo
	 *            Arquivo texto a ser importado
	 * @return Id do arquivo texto recém-inserido
	 * @throws ControladorException
	 */
	public Integer inserirArquivoTextoAtualizacaoCadastralSimplificado(AtualizacaoCadastralSimplificado arquivo,
			AtualizacaoCadastralSimplificadoBinario arquivoBinario,	Collection<AtualizacaoCadastralSimplificadoLinha> linhas) throws ControladorException {

		Integer retorno = null;

		int qtdeImoveisComHidrometro = 0;
		int qtdeImoveisComHidrometroAtualizados = 0;
		int qtdeImoveisComEconomiasAtualizados = 0;
		int qtdeImoveisComMedidorEnergiaAtualizados = 0;
		int numeroDaLinha = 0;

		// buscando todas as críticas de uma vez para armazená-las em memória
		// e depois percorrê-las visando não pesquisar a mesma crítica
		// várias
		// vezes (otimização)
		FiltroAtualizacaoCadastralSimplificadoCritica filtro = new FiltroAtualizacaoCadastralSimplificadoCritica();
		Collection<AtualizacaoCadastralSimplificadoCritica> criticas = getControladorUtil().pesquisar(filtro, AtualizacaoCadastralSimplificadoCritica.class.getName());

		// percore todas as linhas do arquivo
		for (AtualizacaoCadastralSimplificadoLinha linha : linhas) {
			numeroDaLinha++;
			try {
				// se há hidrômetro no imóvel
				if (linha.getNumeroMedidor() != null && !"".equals(linha.getNumeroMedidor().trim())) {
					qtdeImoveisComHidrometro++;

					// código do retorno da validação e atualização do
					// hidrômetro
					final Integer validouEAtualizouHidrometro = validarEAtualizarHidrometro(linha);

					// se o retorno for nulo, é pq atualizou com sucesso
					if (validouEAtualizouHidrometro == null)
						qtdeImoveisComHidrometroAtualizados++;
					else if (validouEAtualizouHidrometro != -1) // -1 indica que
																// o hidrômetro
																// já está
																// atualizado no
																// sistema
						adicionarCritica(criticas, linha, validouEAtualizouHidrometro);
				} else { // caso não tenha hidrômetro no imóvel
					final boolean haHidrometroNoImovel = verificarAusenciaHidrometro(linha);
					// caso não exista hidrômetro no imóvel
					if (!haHidrometroNoImovel)
						adicionarCritica(criticas, linha, AtualizacaoCadastralSimplificadoCritica.IMOVEL_COM_HIDROMETRO);
				}
			} catch (ParseException pe) {
				throw new ControladorException("erro.sistema", pe);
			} catch (ErroRepositorioException ere) {
				throw new ControladorException("erro.sistema", ere);
			}

			// código do retorno da validação e atualização de
			// subcategorias e
			// economias
			final Integer validouEAtualizouEconomias = validarEAtualizarEconomias(linha);

			// se o retorno for nulo, é pq atualizou com sucesso
			if (validouEAtualizouEconomias == null) {
				qtdeImoveisComEconomiasAtualizados++;
			} else if (validouEAtualizouEconomias != -1) { // -1 indica que as
															// economias já
															// estão
															// atualizadas
															// no sistema
				adicionarCritica(criticas, linha, validouEAtualizouEconomias);
			}

			// [SB0004] Validar e atualizar numero do medidor de energia do
			// imovel
			if (linha.getNumeroMedidorEnergia() != null && !"".equals(linha.getNumeroMedidorEnergia().trim())) {

				// codigo do retorno da validacao e atualizacao do numero do
				// medidor de energia
				linha.setUsuario(arquivo.getUsuario());
				final Integer validouEAtualizouMedidorEnergia = validarEAtualizarMedidorEnergia(linha);

				// se o retorno for nulo, e pq atualizou com sucesso
				if (validouEAtualizouMedidorEnergia == null) {
					qtdeImoveisComMedidorEnergiaAtualizados++;
				} else if (validouEAtualizouMedidorEnergia != -1) { // -1 indica
																	// que o
					// numero do medidor ja
					// esta atualizado
					// no sistema
					adicionarCritica(criticas, linha, validouEAtualizouMedidorEnergia);
				}
			}

		}

		// considera-se que há um imóvel por linha no arquivo
		Integer qtdeTotalImoveis = linhas.size();

		arquivo.setQtdeImoveisComEconomiasAtualizados(qtdeImoveisComEconomiasAtualizados);
		arquivo.setQtdeImoveisComHidrometro(qtdeImoveisComHidrometro);
		arquivo.setQtdeImoveisComHidrometroAtualizados(qtdeImoveisComHidrometroAtualizados);
		arquivo.setQtdeImoveisSemHidrometro(qtdeTotalImoveis - qtdeImoveisComHidrometro);
		arquivo.setQtdeImoveisComMedidorEnergiaAtualizados(qtdeImoveisComMedidorEnergiaAtualizados);
		arquivo.setQtdeTotalImoveis(qtdeTotalImoveis);

		retorno = (Integer) getControladorUtil().inserir(arquivo);

		arquivoBinario.setArquivo(arquivo);

		getControladorUtil().inserir(arquivoBinario);

		for (AtualizacaoCadastralSimplificadoLinha linha : linhas)
			getControladorUtil().inserir(linha);

		return retorno;

	}

	/**
	 * Verifica se há hidrômetro no imóvel.
	 *
	 * [SB0002] Validar ausência de hidrômetro no imóvel [UC0969] Importar
	 * arquivo de atualização cadastral simplificado
	 *
	 * @author Samuel Valerio
	 * @date 22/10/2009
	 *
	 * @param linha
	 *            Linha com o imóvel a ser verificado.
	 * @return true se existir hidrômetro no imóvel, false caso contrário
	 * @throws ErroRepositorioException
	 * @throws ControladorException
	 */
	private boolean verificarAusenciaHidrometro(
			AtualizacaoCadastralSimplificadoLinha linha)
			throws ErroRepositorioException, ControladorException {
		Hidrometro hidrometro = obterHidrometroAtualmenteInstalado(linha
				.getNumeroLigacao());
		if (hidrometro != null)
			return false;
		else
			return true;
	}

	/**
	 * Adiciona crítica à linha passada como parâmetro.
	 *
	 * [UC0969] Importar arquivo de atualização cadastral simplificado
	 *
	 * @author Samuel Valerio
	 * @date 22/10/2009
	 *
	 * @param criticas
	 *            Críticas existentes na base de dados
	 * @param linha
	 *            Linha para a qual será adicionada a crítica.
	 * @param idCritica
	 * @throws ControladorException
	 */
	private void adicionarCritica(
			Collection<AtualizacaoCadastralSimplificadoCritica> criticas,
			AtualizacaoCadastralSimplificadoLinha linha, Integer idCritica)
			throws ControladorException {
		// inicializando a coleção de críticas (se necessário)
		if (linha.getCriticas() == null)
			linha
					.setCriticas(new HashSet<AtualizacaoCadastralSimplificadoCritica>());

		// percorrendo as críticas existentes até encontrar a crítica a ser
		// adicionada
		for (AtualizacaoCadastralSimplificadoCritica critica : criticas) {
			// se o id foi igual, esta crítica deve ser adicionada
			if (critica.getId().equals(idCritica)) {
				linha.getCriticas().add(critica);
				return;
			}
		}

		// caso percorra todas as críticas e não encontre a correpondente, lança
		// exceção
		throw new ControladorException(
				"erro.atualizacao_cadastral_simplificado.critica_inexistente");

	}

	/**
	 * Valida o número do hidrômetro que vem no arquivo. Bem como verifica se
	 * seu fabricante e capacidade estão cadastrados na base de dados.
	 *
	 * [SB0001] Validar e atualizar Hidrômetro [UC0969] Importar arquivo de
	 * atualização cadastral simplificado
	 *
	 * @author Samuel Valerio
	 * @date 22/10/2009
	 *
	 * @param linha
	 * @return Código indicativo da validação: nulo se foi atualizado com
	 *         sucesso, -1 se já estava atualizado e um número maior que zero se
	 *         houve crítica.
	 * @throws ParseException
	 * @throws ErroRepositorioException
	 * @throws ControladorException
	 */
	public Integer validarEAtualizarHidrometro(
			AtualizacaoCadastralSimplificadoLinha linha) throws ParseException,
			ErroRepositorioException, ControladorException {
		Integer retorno = null; // por padrão, retorna nulo que indica a
								// atualização com sucesso

		final int TAMANHO_PADRAO_ABNT = 10;

		// valida se o tamanho do número do hidrômetro segue o padrão ABNT
		if (linha.getNumeroMedidor() != null
				&& linha.getNumeroMedidor().trim().length() != TAMANHO_PADRAO_ABNT)
			return AtualizacaoCadastralSimplificadoCritica.HIDROMETRO_FORA_TAMANHO_PADRAO_ABNT;

		final String COMPOSICAO_PADRAO_ABNT = "[A-Z]\\d\\d[A-Z]\\d\\d\\d\\d\\d\\d";
		Pattern p = Pattern.compile(COMPOSICAO_PADRAO_ABNT);
		Matcher m = p.matcher(linha.getNumeroMedidor());
		// valida se o número do hidrômetro segue o padrão ABNT
		if (linha.getNumeroMedidor() != null && !m.find())
			return AtualizacaoCadastralSimplificadoCritica.HIDROMETRO_FORA_PADRAO_ABNT;

		Hidrometro hidrometro = obterHidrometroAtualmenteInstalado(linha
				.getNumeroLigacao());
		// valida se tem hidrômetro atualmente instalado no imóvel
		if (hidrometro == null)
			return AtualizacaoCadastralSimplificadoCritica.IMOVEL_SEM_HIDROMETRO;
		else if (hidrometro.getNumero().equals(linha.getNumeroMedidor()))
			return -1; // retornar -1 quando o hidrômetro já está atualizado no
						// imóvel

		FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
		filtroHidrometro.adicionarParametro(new ParametroSimples(
				FiltroHidrometro.NUMERO_HIDROMETRO, linha.getNumeroMedidor()
						.trim()));
		Hidrometro hidrometroInstaladoEmOutroImovel = (Hidrometro) Util
				.retonarObjetoDeColecao(getControladorUtil().pesquisar(
						filtroHidrometro, Hidrometro.class.getName()));
		// valida se o hidrômetro deste número já não está cadastrado
		if (hidrometroInstaladoEmOutroImovel != null
				&& hidrometro != null
				&& !hidrometroInstaladoEmOutroImovel.getNumero().equals(
						hidrometro.getNumero()))
			return AtualizacaoCadastralSimplificadoCritica.HIDROMETRO_INSTALADO_OUTRO_IMOVEL;

		String codigoDaCapacidade = linha.getNumeroMedidor().substring(0, 1);
		FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
		filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
				FiltroHidrometroCapacidade.CODIGO_HIDROMETRO_CAPACIDADE,
				codigoDaCapacidade));
		HidrometroCapacidade hidrometroCapacidade = (HidrometroCapacidade) Util
				.retonarObjetoDeColecao(getControladorUtil().pesquisar(
						filtroHidrometroCapacidade,
						HidrometroCapacidade.class.getName()));
		// valida se a capacidade está cadastrada no sistema
		if (hidrometroCapacidade == null)
			return AtualizacaoCadastralSimplificadoCritica.HIDROMETRO_CAPACIDADE_INEXISTENTE;
		else
			hidrometro.setHidrometroCapacidade(hidrometroCapacidade);

		String terminacaoDoAnoDeFabricacao = linha.getNumeroMedidor()
				.substring(1, 3);

		// valida se a terminação do ano de fabricação é composta apenas por
		// números
		try {
			Integer.parseInt(terminacaoDoAnoDeFabricacao);
		} catch (NumberFormatException nfe) {
			return AtualizacaoCadastralSimplificadoCritica.HIDROMETRO_ANO_FABRICACAO_INVALIDO;
		}

		// complementa e atribui o ano de fabricação
		if (Integer.parseInt(terminacaoDoAnoDeFabricacao) >= 80)
			hidrometro.setAnoFabricacao(Short.parseShort("19"
					+ terminacaoDoAnoDeFabricacao));
		else
			hidrometro.setAnoFabricacao(Short.parseShort("20"
					+ terminacaoDoAnoDeFabricacao));

		Integer anoDataAquisicao = Util.getAno(hidrometro.getDataAquisicao());
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

		// Se o ano de aquisição for menor que o ano de fabricação ou maior que
		// o ano de fabricação mais 3
		// A data de aquisição é considerada inválida e é atribuído o valor
		// padrão de 1 de janeiro do ano de fabricação
		// Regra definida por Márcio e Joab da Comercial
		// exemplo: para o ano de fabricação 2005 o ano de aquisição deve estar
		// entre 2005 e 2008. Caso não esteja
		// é atribuída a data de aquisição 01/01/2005.
		// atribui a data de aquisição
		if (anoDataAquisicao < hidrometro.getAnoFabricacao()
				|| anoDataAquisicao > hidrometro.getAnoFabricacao() + 3)
			hidrometro.setDataAquisicao(formatoData.parse("01/01/"
					+ hidrometro.getAnoFabricacao()));

		String codigoDoFabricante = linha.getNumeroMedidor().substring(3, 4);
		FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca();
		filtroHidrometroMarca.adicionarParametro(new ParametroSimples(
				FiltroHidrometroMarca.CODIGO, codigoDoFabricante));
		HidrometroMarca hidrometroMarca = (HidrometroMarca) Util
				.retonarObjetoDeColecao(getControladorUtil().pesquisar(
						filtroHidrometroMarca, HidrometroMarca.class.getName()));
		// atribui a marca (fabricante) do hidrômetro
		if (hidrometroMarca == null)
			return AtualizacaoCadastralSimplificadoCritica.HIDROMETRO_FABRICANTE_INEXISTENTE;
		else
			hidrometro.setHidrometroMarca(hidrometroMarca);

		String numerosSequenciaisDoFabricante = linha.getNumeroMedidor()
				.substring(4);
		// valida de os sequenciais do fabricante sao apenas numeros
		try {
			Integer.parseInt(numerosSequenciaisDoFabricante);
		} catch (NumberFormatException nfe) {
			return AtualizacaoCadastralSimplificadoCritica.HIDROMETRO_SEQUENCIAIS_FABRICANTE_INVALIDOS;
		}

		// atribui o número do hidrômetro que veio do arquivo
		hidrometro.setNumero(linha.getNumeroMedidor());

		getControladorUtil().atualizar(hidrometro);

		return retorno;
	}

	/**
	 * Obtém o hidrômetro atualmente instalado para o imóvel passado como
	 * parâmetro.
	 *
	 * [UC0969] Importar arquivo de atualização cadastral simplificado
	 *
	 * @author Samuel Valerio
	 * @date 22/10/2009
	 *
	 * @param idImovel
	 *            Id do imóvel para o qual se quer obter o hidrômetro instalado.
	 * @return O hidrômetro atualmente instalado no imóvel
	 * @throws ErroRepositorioException
	 */
	public Hidrometro obterHidrometroAtualmenteInstalado(Integer idImovel)
			throws ErroRepositorioException {
		Integer idHidrometroInstalacaoHistorico;
		idHidrometroInstalacaoHistorico = repositorioMicromedicao
				.verificarExistenciaHidrometroInstalacaoHistoricoTipoAgua(idImovel);
		if (idHidrometroInstalacaoHistorico == null
				|| 0 == idHidrometroInstalacaoHistorico)
			idHidrometroInstalacaoHistorico = repositorioMicromedicao
					.verificarExistenciaHidrometroInstalacaoHistoricoTipoPoco(idImovel);

		if (idHidrometroInstalacaoHistorico != null
				&& 0 != idHidrometroInstalacaoHistorico) {
			FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();
			filtroHidrometroInstalacaoHistorico
					.adicionarParametro(new ParametroSimples(
							FiltroHidrometroInstalacaoHistorico.ID,
							idHidrometroInstalacaoHistorico));
			filtroHidrometroInstalacaoHistorico
					.adicionarCaminhoParaCarregamentoEntidade(FiltroHidrometroInstalacaoHistorico.HIDROMETRO);
			return ((HidrometroInstalacaoHistorico) Util
					.retonarObjetoDeColecao(repositorioUtil.pesquisar(
							filtroHidrometroInstalacaoHistorico,
							HidrometroInstalacaoHistorico.class.getName())))
					.getHidrometro();
		} else {
			return null;
		}

	}

	/**
	 * Busca as críticas existentes para o arquivo passado como parâmetro.
	 *
	 * [UC0969] Importar arquivo de atualização cadastral simplificado
	 *
	 * @author Samuel Valerio
	 * @date 22/10/2009
	 *
	 * @param idArquivo
	 *            Id do arquivo texto importado
	 * @return Críticas existentes para o arquivo.
	 * @throws ControladorException
	 */
	public Collection<AtualizacaoCadastralSimplificadoCritica> pesquisarAtualizacaoCadastralSimplificadoCritica(
			int idArquivo) throws ControladorException {
		try {
			return repositorioCadastro
					.pesquisarAtualizacaoCadastralSimplificadoCritica(idArquivo);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Valida se as subcategorias a serem atualizadas existem na base de dados.
	 * Se não existirem, gera uma crítica e aborta a atualização.
	 *
	 * [SB0003] Validar e atualizar subcategorias e economias do imóvel [UC0969]
	 * Importar arquivo de atualização cadastral simplificado
	 *
	 * @author Samuel Valerio
	 * @date 22/10/2009
	 *
	 * @param linha
	 *            Linha contendo a matrícula do imóvel bem como as subcategorias
	 *            e economias vindas da pesquisa de campo
	 * @return Código indicativo da validação: nulo se foi atualizado com
	 *         sucesso, -1 se já estava atualizado e um número maior que zero se
	 *         houve crítica.
	 * @throws ControladorException
	 */
	public Integer validarEAtualizarEconomias(
			AtualizacaoCadastralSimplificadoLinha linha)
			throws ControladorException {
		Integer retorno = null; // por padrão, retorna nulo que indica a
								// atualização com sucesso

		// obtendo as subcategorias do imóvel do BD
		Collection<ImovelSubcategoria> subcategorias = getControladorImovel()
				.obterColecaoImovelSubcategorias(
						new Imovel(linha.getNumeroLigacao()), 1);

		// em princípio, deve-se remover do BD todas as subcategorias do imóvel
		// e adicionar todas as subcategorias vindas no arquivo
		Collection<ImovelSubcategoria> subcategoriasARemover = new ArrayList<ImovelSubcategoria>(
				subcategorias);

		// percorre todas as subcategorias do imóvel existentes na base para
		// comparar com as subcategorias vindas do arquivo
		for (ImovelSubcategoria imovelSubcategoria : subcategorias) {
			for (int i = 0; i < linha.getCategorias().size(); i++) {
				Integer categoria = Integer.parseInt(linha.getCategorias().get(
						i));
				// se encontrar uma subcategoria que coincida e tenha o mesmo
				// número de economias
				if (imovelSubcategoria.getComp_id().getSubcategoria()
						.getCodigo() == categoria
						&& imovelSubcategoria.getQuantidadeEconomias() == Integer
								.parseInt(linha.getEconomias().get(i))) {
					// esta subcategoria não deve ser mais removida da base
					subcategoriasARemover.remove(imovelSubcategoria);

					// esta subcategoria não deve ser adicionada na base
					linha.getCategorias().remove(i);
					linha.getEconomias().remove(i);
				}
			}
		}

		// caso existam subcategorias a serem removidas do BD
		if (subcategoriasARemover.size() > 0) {
			// remove-as uma a uma, registrando a transação para possibilitar
			// auditoria futura
			for (ImovelSubcategoria imovelSubcategoria : subcategoriasARemover) {
				Imovel imovel = imovelSubcategoria.getComp_id().getImovel();
				RegistradorOperacao registradorOperacao = new RegistradorOperacao(
						Operacao.OPERACAO_IMOVEL_ATUALIZAR, imovel.getId(),
						imovel.getId(), new UsuarioAcaoUsuarioHelper(linha
								.getArquivo().getUsuario(),
								UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

				registradorOperacao.registrarOperacao(imovel);
				registradorOperacao.registrarOperacao(imovelSubcategoria);
				getControladorUtil().remover(imovelSubcategoria);
			}
		}

		// caso existam subcategorias a serem adicionadas no BD (vindas do
		// arquivo)
		if (linha.getCategorias().size() > 0) {
			// adiciona uma a uma, registrando a transação para possibilitar
			// auditoria futura
			for (int i = 0; i < linha.getCategorias().size(); i++) {
				String cat = linha.getCategorias().get(i);

				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, linha.getNumeroLigacao()));
				// carregando entidades necessárias para não dar LazyException
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("areaConstruidaFaixa");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("pavimentoCalcada");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("reservatorioVolumeFaixaSuperior");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("reservatorioVolumeFaixaInferior");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("pavimentoRua");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("pavimentoRua");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("pocoTipo");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("despejo");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("fonteAbastecimento");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("piscinaVolumeFaixa");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("imovelTipoHabitacao");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("imovelTipoPropriedade");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("imovelTipoPropriedade");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("imovelTipoCobertura");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("rotaEntrega");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("funcionario");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("imovelSubcategorias");

				Imovel imovel = (Imovel) Util
						.retonarObjetoDeColecao(getControladorUtil().pesquisar(
								filtroImovel, Imovel.class.getName()));
				FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
				filtroSubcategoria.adicionarParametro(new ParametroSimples(
						FiltroSubCategoria.CODIGO, cat));
				filtroSubcategoria
						.adicionarCaminhoParaCarregamentoEntidade("categoria");
				Subcategoria subcategoria = (Subcategoria) Util
						.retonarObjetoDeColecao(getControladorUtil().pesquisar(
								filtroSubcategoria,
								Subcategoria.class.getName()));
				// caso venha uma subcategoria no arquivo que não exista no BD
				// deve-se gerar uma crítica e abortar a atualização das
				// economias
				if (subcategoria == null)
					return AtualizacaoCadastralSimplificadoCritica.IMOVEL_SUBCATEGORIA_INEXISTENTE;

				// Criando um ImovelSubcategoria para a sub categoria do arquivo
				// ---
				// Associando um imovel a uma subcategoria
				ImovelSubcategoriaPK ispk = new ImovelSubcategoriaPK(imovel,
						subcategoria);
				ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria(
						ispk, new Short(linha.getEconomias().get(i)));
				// Colocando data da atualização individualmete no
				// imovelSubcategoria
				imovelSubcategoria.setUltimaAlteracao(new Date());

				RegistradorOperacao registradorOperacao = new RegistradorOperacao(
						Operacao.OPERACAO_IMOVEL_ATUALIZAR, imovel.getId(),
						imovel.getId(), new UsuarioAcaoUsuarioHelper(linha
								.getArquivo().getUsuario(),
								UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

				registradorOperacao.registrarOperacao(imovel);
				registradorOperacao.registrarOperacao(imovelSubcategoria);
				getControladorUtil().inserir(imovelSubcategoria);
			}
		}

		// se removeu (subcategoriasARemover.size() > 0) ou adicionou
		// (linha.getCategorias.size()>0)
		// economias, retorna nulo
		if (subcategoriasARemover.size() > 0
				|| linha.getCategorias().size() > 0) {
			return retorno; // retorna nulo indicando que as
							// subcategorias/economias do imóvel fora
							// atualizadas com sucesso
		} else {
			return -1; // retorna -1 se as economias do imóvel já estão
						// atualizadas
		}

	}

	public Integer validarEAtualizarMedidorEnergia(AtualizacaoCadastralSimplificadoLinha linha)	throws ControladorException {
		Integer retorno = null; // por padrao, retorna nulo que indica a atualizacao com sucesso

		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, linha.getImovel().getId()));

		Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(getControladorUtil().pesquisar(filtroImovel, Imovel.class.getName()));

		if (imovel.getNumeroMedidorEnergia() == null || !imovel.getNumeroMedidorEnergia().equals(linha.getNumeroMedidorEnergia())) {
			imovel.setNumeroMedidorEnergia(linha.getNumeroMedidorEnergia());
			imovel.setUsuarioParaHistorico(linha.getUsuario());
			getControladorAtualizacaoCadastro().atualizar(imovel);
		} else {
			retorno = -1;// retorna -1 se o medidor de energia do imovel ja esta atualizada
		}

		return retorno;
	}

	/**
	 *
	 * [UC0973] Inserir Imóvel em Programa Especial
	 * [FS0004] Validar dados do imóvel no programa especial
	 * @author Hugo Amorim
	 * @since 17/12/2009
	 *
	 */
	public void validarDadosInserirImovelProgramaEspecial(ImovelProgramaEspecial imovelProgramaEspecial) throws ControladorException {

		// Obter Parametros do sistema
		SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();

		if(imovelProgramaEspecial.getImovel().getLigacaoAguaSituacao()!=null){
			if(imovelProgramaEspecial.getImovel().getLigacaoAguaSituacao().getId().compareTo(LigacaoAguaSituacao.LIGADO)!=0){
				throw new ControladorException("atencao.situacao.agua.invalido");
			}
		}

		// [FS0004] Validar dados do imóvel no programa especial
		// Verifica se categoria do imovel
		// e igual a residencial
		Collection colecaoSubcategoriasImovel =
			this.getControladorImovel().obterColecaoImovelSubcategorias(imovelProgramaEspecial.getImovel(),new Integer(0));

		if(colecaoSubcategoriasImovel!=null && !colecaoSubcategoriasImovel.isEmpty()){
			for (Iterator iterator = colecaoSubcategoriasImovel.iterator(); iterator
					.hasNext();) {
				ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) iterator.next();

				if(imovelSubcategoria.getComp_id().getSubcategoria().getCategoria().getId().compareTo(new Integer(1))!=0){
					throw new ControladorException("atencao.categoria.nao.permite.incluir.programa");
				}
			}
		}

		// [FS0004] Validar dados do imóvel no programa especial
		// Verifica se immóvel não possui hidrometro
		// e tem área construída superior a 100m2

		 // Obtém o indicador de existência de hidrômetro para o imóvel, caso exista
		 // retorna 1(um) indicando SIM caso contrário retorna 2(dois) indicando NÃO
		 Integer possuiHidrometro = this.getControladorImovel().obterIndicadorExistenciaHidrometroImovel(imovelProgramaEspecial.getImovel().getId());

		if(possuiHidrometro.compareTo(new Integer(2))==0
				&& imovelProgramaEspecial.getImovel().getAreaConstruida().compareTo(new BigDecimal("100"))>0){
			throw new ControladorException("atencao.area.maior.permitida");
		}

		// [FS0004] Validar dados do imóvel no programa especial
		// Verifica consumo de água do mês atual se não houver no mês atual
		// é maior que 25m3

		Integer mesAnoFaturamento = sistemaParametro.getAnoMesFaturamento();
		Integer mesAnoAnteriorFaturamento = Util.subtraiAteSeisMesesAnoMesReferencia(mesAnoFaturamento, 1);

		Integer consumoFaturado = null;

		consumoFaturado = this.getControladorImovel()
								.obterConsumoFaturadoImovelNoMes(imovelProgramaEspecial.getImovel().getId(),
										mesAnoFaturamento);
		if(consumoFaturado==null){

			consumoFaturado = this.getControladorImovel()
				.obterConsumoFaturadoImovelNoMes(imovelProgramaEspecial.getImovel().getId(),
						mesAnoAnteriorFaturamento);
		}

		if(consumoFaturado!=null
				&& possuiHidrometro.compareTo(new Integer(1))==0
					&& consumoFaturado.compareTo(new Integer(25))>0){
			throw new ControladorException("atencao.consumo.anterior.invalido");
		}

		// [FS0004] Validar dados do imóvel no programa especial
		// Verifica se cliente reponsável pelo imóvel
		// é diferente do responsável pelo programa especial
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
		filtroClienteImovel
				.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroClienteImovel
				.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.IMOVEL_ID, imovelProgramaEspecial.getImovel().getId()));
		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, ClienteRelacaoTipo.RESPONSAVEL));
		filtroClienteImovel.adicionarParametro(new ParametroNulo(
				FiltroClienteImovel.DATA_FIM_RELACAO));

		Collection clientesImovel = getControladorUtil().pesquisar(
				filtroClienteImovel, ClienteImovel.class.getName());

		ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(clientesImovel);

		if(sistemaParametro.getClienteResponsavelProgramaEspecial()!=null){
			if(clienteImovel!=null
					&& clienteImovel.getCliente().getId()
						.compareTo(sistemaParametro.getClienteResponsavelProgramaEspecial().getId())!=0){

				throw new ControladorException("atencao.cliente.diferente.responsavel.programa",
					null,
					clienteImovel.getCliente().getId().toString());
			}
		}

		// Valida se imovel está em processo de suspensão.
		FiltroImovelProgramaEspecial filtroImovelProgramaEspecial = new FiltroImovelProgramaEspecial();

		filtroImovelProgramaEspecial
				.adicionarParametro(new ParametroSimples(
						FiltroImovelProgramaEspecial.IMOVEL_ID, imovelProgramaEspecial.getImovel().getId()));
		filtroImovelProgramaEspecial
				.adicionarParametro(new ParametroSimples(
						FiltroImovelProgramaEspecial.FORMA_SUSPENSAO, ImovelProgramaEspecial.FORMA_SUSPENSAO_OPERADOR));

		Collection<ImovelProgramaEspecial> colecaoImovelProgramaEspecial =
			this.getControladorUtil().pesquisar(filtroImovelProgramaEspecial,
				ImovelProgramaEspecial.class.getName());

		ImovelProgramaEspecial imovelProgramaEspecialEmProcessoDeSuspensao =
			(ImovelProgramaEspecial) Util.retonarObjetoDeColecao(colecaoImovelProgramaEspecial);

		if(imovelProgramaEspecialEmProcessoDeSuspensao!=null){
			throw new ControladorException("atencao.imovel_em_processo_de_suspensao");
		}

	}

	/**
	 * [UC0925] Emitir Boletos
	 *
	 * retrona DBTP_VLLIMITE para DBTP_ID = idDebitoTipo
	 *
	 * @author Rômulo Aurélio
	 * @date 22/12/2009
	 *
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorSugeridoDebitoTipo(
			Integer idDebitoTipo) throws ControladorException {
		try {
			return repositorioCadastro.pesquisarValorSugeridoDebitoTipo(idDebitoTipo);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	/**
	 *
	 * [UC0976] Suspender Imóvel em Programa Especial
	 * [FS0004] Validar dados da suspensão imóvel no programa especial
	 * @author Hugo Amorim
	 * @since 21/12/2009
	 *
	 */
	public void validarDadosSuspensaoImovelProgramaEspecial(ImovelProgramaEspecial imovelProgramaEspecial) throws ControladorException{

		Pagamento pagamento = null;
		Conta conta = null;
		ClienteConta clienteConta = null;

		SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();

		FiltroConta filtroConta = new FiltroConta();

		filtroConta.adicionarParametro(
				new ParametroSimples(FiltroConta.IMOVEL_ID, imovelProgramaEspecial.getImovel().getId()));
		filtroConta.adicionarParametro(
				new ParametroSimples(FiltroConta.REFERENCIA, sistemaParametro.getAnoMesFaturamento()));

		Collection<Conta> colecaoContas = this.getControladorUtil().pesquisar(filtroConta, Conta.class.getName());

		conta = (Conta) Util.retonarObjetoDeColecao(colecaoContas);

		if(conta!=null){

			FiltroClienteConta filtroClienteConta = new FiltroClienteConta();

			filtroClienteConta.adicionarParametro(
					new ParametroSimples(FiltroClienteConta.CONTA_ID, conta.getId()));
			filtroClienteConta.adicionarParametro(
					new ParametroSimples(FiltroClienteConta.CLIENTE_ID, sistemaParametro.getClienteResponsavelProgramaEspecial().getId()));

			filtroClienteConta.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteConta.CONTA);
			filtroClienteConta.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteConta.CLIENTE);


			Collection<ClienteConta> colecaoClienteConta = this.getControladorUtil().pesquisar(filtroClienteConta, ClienteConta.class.getName());

			clienteConta = (ClienteConta) Util.retonarObjetoDeColecao(colecaoClienteConta);

			if(clienteConta!=null){

				FiltroPagamento filtroPagamento = new FiltroPagamento();

				filtroPagamento.adicionarParametro(
						new ParametroSimples(FiltroPagamento.CONTA_ID, clienteConta.getConta().getId()));

				Collection<Pagamento> colecaoPagamento = this.getControladorUtil().pesquisar(filtroPagamento, Pagamento.class.getName());

				pagamento = (Pagamento) Util.retonarObjetoDeColecao(colecaoPagamento);

			}

		}

		if(conta !=null && clienteConta!=null && pagamento==null){

			throw new ControladorException("atencao.suspensao.so.possivel.depois.faturamento");

		}

	}

	/**
	 *
	 * [UC0976] Suspender Imóvel em Programa Especial Forma Online
	 *  	Suspende Imóvel em Programa Especial forma Online
	 * @author Hugo Amorim
	 * @since 13/01/2010
	 *
	 */
	public void suspenderImovelEmProgramaEspecialOnline(ImovelProgramaEspecial imovelProgramaEspecial,
			Usuario usuarioLogado,Short formaSuspensao) throws ControladorException{

		Date dataAtual = new Date();

		Imovel imovel = imovelProgramaEspecial.getImovel();
		ImovelPerfil imovelPerfil = new ImovelPerfil();
		imovelPerfil.setId(ImovelPerfil.NORMAL);
		imovel.setIndicadorEmissaoExtratoFaturamento(ConstantesSistema.NAO);
		imovel.setImovelPerfil(imovelPerfil);
		imovel.setUsuarioParaHistorico(usuarioLogado);

		this.getControladorAtualizacaoCadastro().atualizar(imovel);

		imovelProgramaEspecial.setMesAnoSaidaPrograma(imovel.getQuadra().getRota().getFaturamentoGrupo().getAnoMesReferencia());
		imovelProgramaEspecial.setUsuarioSuspensao(usuarioLogado);
		imovelProgramaEspecial.setFormaSuspensao(formaSuspensao);
		imovelProgramaEspecial.setDataSuspensao(dataAtual);
		imovelProgramaEspecial.setUltimaAlteracao(dataAtual);

		this.getControladorUtil().atualizar(imovelProgramaEspecial);

	}

	/**
	 *
	 * [UC0973] Inserir Imóvel em Programa Especial
	 *  	Inseri Imóvel em Programa Especial
	 * @author Hugo Amorim
	 * @since 13/01/2010
	 *
	 */
	public Integer inserirImovelEmProgramaEspecial(ImovelProgramaEspecial imovelProgramaEspecial, Usuario usuarioLogado) throws ControladorException{

		SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();

		Date dataAtual = new Date();

		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");

		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, imovelProgramaEspecial.getImovel().getId()));
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, ClienteRelacaoTipo.RESPONSAVEL));
		filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_ID, sistemaParametro.getClienteResponsavelProgramaEspecial().getId()));

		Collection clientesImovel = this.getControladorUtil().pesquisar(filtroClienteImovel, ClienteImovel.class.getName());

		ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(clientesImovel);

		if(clienteImovel==null){
			ClienteImovel clienteImovelInclusao = new ClienteImovel();

			Cliente cliente = new Cliente();
			cliente.setId(sistemaParametro.getClienteResponsavelProgramaEspecial().getId());
			clienteImovelInclusao.setCliente(cliente);
			clienteImovelInclusao.setImovel(imovelProgramaEspecial.getImovel());
			clienteImovelInclusao.setUltimaAlteracao(dataAtual);
			clienteImovelInclusao.setDataInicioRelacao(dataAtual);
			ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();
			clienteRelacaoTipo.setId(new Integer(ClienteRelacaoTipo.RESPONSAVEL));
			clienteImovelInclusao.setClienteRelacaoTipo(clienteRelacaoTipo);
			clienteImovelInclusao.setIndicadorNomeConta(ConstantesSistema.NAO);
			clienteImovelInclusao.setUltimaAlteracao(dataAtual);

			this.getControladorUtil().inserir(clienteImovelInclusao);
		}

		ImovelPerfil imovelPerfil = new ImovelPerfil();
		imovelPerfil.setId(sistemaParametro.getPerfilProgramaEspecial().getId());

		Imovel imovel = imovelProgramaEspecial.getImovel();
		imovel.setImovelPerfil(imovelPerfil);
		imovel.setIndicadorEmissaoExtratoFaturamento(ConstantesSistema.SIM);
		imovel.setUltimaAlteracao(dataAtual);
		CobrancaSituacaoTipo cobrancaSituacaoTipo = new CobrancaSituacaoTipo();
		//Constante = 1
		cobrancaSituacaoTipo.setId(CobrancaSituacaoTipo.COBRANCA_EMPRESA_TERCEIRIZADA);
		imovel.setCobrancaSituacaoTipo(cobrancaSituacaoTipo);

		imovel.setUsuarioParaHistorico(usuarioLogado);
		this.getControladorAtualizacaoCadastro().atualizar(imovel);

		/**
		 * Inserir Situacao de cobranca historico
		 * @author Arthur Carvalho
		 * @date 02/08/2011
		 */

		CobrancaSituacaoMotivo cobrancaSituacaoMotivo = new CobrancaSituacaoMotivo();
		cobrancaSituacaoMotivo.setId(CobrancaSituacaoMotivo.IMOVEL_CADASTRADO_VIVA_AGUA);

		CobrancaSituacaoHistorico cobrancaSituacaoHistorico = new CobrancaSituacaoHistorico();
		cobrancaSituacaoHistorico.setImovel(imovel);
		cobrancaSituacaoHistorico.setCobrancaSituacaoMotivo(cobrancaSituacaoMotivo);
		cobrancaSituacaoHistorico.setCobrancaSituacaoTipo(cobrancaSituacaoTipo);
		cobrancaSituacaoHistorico.setAnoMesCobrancaSituacaoInicio(imovelProgramaEspecial.getImovel().getQuadra().getRota().getFaturamentoGrupo().getAnoMesReferencia());
		cobrancaSituacaoHistorico.setAnoMesCobrancaSituacaoFim(new Integer(201512));
		cobrancaSituacaoHistorico.setUsuario(usuarioLogado);
		cobrancaSituacaoHistorico.setUltimaAlteracao(new Date());

		this.getControladorUtil().inserir(cobrancaSituacaoHistorico);


		imovelProgramaEspecial.setDataInclusao(dataAtual);
		imovelProgramaEspecial.setMesAnoInicioPrograma(imovelProgramaEspecial.getImovel().getQuadra().getRota().getFaturamentoGrupo().getAnoMesReferencia());
		imovelProgramaEspecial.setUltimaAlteracao(dataAtual);
		imovelProgramaEspecial.setUsuarioResponsavel(usuarioLogado);
		imovelProgramaEspecial.setImovelPerfil(imovelPerfil);

		Integer idImovelInserido =
			(Integer) this.getControladorUtil().inserir(imovelProgramaEspecial);

		return idImovelInserido;

	}

	/**
	 *
	 * [UC0976] Suspender Imóvel em Programa Especial Batch
	 *  	Suspende Imóveis ativos no Programa Especial
	 * @author Hugo Amorim
	 * @since 13/01/2010
	 *
	 */
	public void suspenderImovelEmProgramaEspecialBatch(int idFuncionalidadeIniciada,
			Usuario usuarioLogado,Rota rota)
		throws ControladorException{

		Short formaSuspensao = ImovelProgramaEspecial.FORMA_SUSPENSAO_BATCH;

		int idUnidadeIniciada = 0;

		boolean imovelParaSuspender = false;

		int quantidadeImoveisLidos = 0;
		int quantidadeImoveisSuspensos = 0;

		SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();

		idUnidadeIniciada =
			getControladorBatch().iniciarUnidadeProcessamentoBatch(
				idFuncionalidadeIniciada,
				UnidadeProcessamento.ROTA,rota.getId());


		// Variáveis para a paginação da pesquisa de Imovel por Grupo Faturamento
		// ========================================================================
		boolean flagTerminou = false;
		final int quantidadeRegistros = 5000;
		int numeroIndice = 0;
		// ========================================================================

		try{

			if(sistemaParametro.getPerfilProgramaEspecial()==null){
				throw new ControladorException("atencao.nao.existe.perfil.programa.cadastrado");
			}

			while(!flagTerminou){

				Collection imoveisProgramaEspecial = repositorioCadastro.pesquisarImovelEmProgramaEspecial(
						sistemaParametro.getPerfilProgramaEspecial().getId(),
						rota,
						numeroIndice,
						quantidadeRegistros);

				if(imoveisProgramaEspecial!=null && !imoveisProgramaEspecial.isEmpty()){

					Iterator imoveisProgramaEspecialIterator = imoveisProgramaEspecial.iterator();

					while (imoveisProgramaEspecialIterator.hasNext()) {

						Object[] dados = (Object[]) imoveisProgramaEspecialIterator.next();
						quantidadeImoveisLidos+=1;

						ImovelProgramaEspecial imovelProgramaEspecial = (ImovelProgramaEspecial) dados[0];

						Imovel imovel = (Imovel) dados[1];

						Quadra quadra = new Quadra();
						quadra.setId(new Integer(dados[2].toString()));

						FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();
						faturamentoGrupo.setId(new Integer(dados[4].toString()));
						faturamentoGrupo.setAnoMesReferencia(new Integer(dados[5].toString()));

						rota.setFaturamentoGrupo(faturamentoGrupo);
						quadra.setRota(rota);
						imovel.setQuadra(quadra);
						imovelProgramaEspecial.setImovel(imovel);

						/*
						 * 2.2.1.	Caso a data de suspensão não esteja informada;
						 * 				 [FS0007]  Verifica dados do imóvel no programa especial
						 * 2.2.2.	Ou caso o indicador de forma de suspensão igual a 1;
						 * 				 [FS0008]  Verifica dados do imóvel no programa especial suspenso
                         *
						 */

						if(imovelProgramaEspecial.getFormaSuspensao()!=null
								&& imovelProgramaEspecial.getFormaSuspensao()
									.compareTo(ImovelProgramaEspecial.FORMA_SUSPENSAO_OPERADOR)==0){

							boolean suspender = false;
							// [FS0008] Verifica dados do imóvel no programa especial suspenso
							suspender = this.verificarRemocaoRelacaoClienteComImovel(imovelProgramaEspecial, sistemaParametro);

						    if(suspender){
						    	imovelParaSuspender = true;
						    	quantidadeImoveisSuspensos+=1;
						    }

						}else{
							//[FS0007]  Verifica dados do imóvel no programa especial
							imovelParaSuspender = validarDadosSuspenderImovelProgramaEspecial(
								imovelProgramaEspecial,sistemaParametro);

						}

						if(imovelParaSuspender){

							this.efetuarSuspensaoImovelEmProgramaEspecial(
									imovelProgramaEspecial,usuarioLogado,formaSuspensao);

							quantidadeImoveisSuspensos+=1;

						}else{
							continue;
						}
					}
				}

			/**
			 * Incrementa o nº do indice da páginação
			 */
			numeroIndice = numeroIndice + quantidadeRegistros;

			/**
			 * Caso a coleção de imoveis retornados for menor que a
			 * quantidade de registros seta a flag indicando que a
			 * paginação terminou.
			 */
			if (imoveisProgramaEspecial == null || imoveisProgramaEspecial.size() < quantidadeRegistros) {

				flagTerminou = true;
			}

			if (imoveisProgramaEspecial != null) {
				imoveisProgramaEspecial.clear();
				imoveisProgramaEspecial = null;
			}
		}// FIM DO LOOP DA PAGINAÇÃO

		getControladorBatch().encerrarUnidadeProcessamentoBatch(
				null, idUnidadeIniciada, false);

		} catch (Exception e) {
			// Este catch serve para interceptar
			// qualquer exceção que o processo batch
			// venha a lançar e garantir que a unidade
			// de processamento do batch será atualizada
			// com o erro ocorrido

			EnvioEmail envioEmail =
				this.pesquisarEnvioEmail(
					EnvioEmail.SUSPENDER_IMOVEL_EM_PROGRAMA_ESPECIAL_EMAIL);

			String emailRemetente = envioEmail.getEmailReceptor();

			String tituloMensagem = envioEmail.getTituloMensagem();

			String emailReceptor = envioEmail.getEmailReceptor();

			String mensagem = envioEmail.getCorpoMensagem();

			mensagem = mensagem + " Quantidades de imóveis lidos " + quantidadeImoveisLidos
				+ " ,  quantidades de imóveis suspensos " + quantidadeImoveisSuspensos + " . Log Erro -> "
				+ e.getMessage();

			try {
				ServicosEmail.enviarMensagem(emailRemetente, emailReceptor,
						tituloMensagem, mensagem);
			} catch (ErroEmailException erroEnviarEmail) {

			}


			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);
		 }


//		EnvioEmail envioEmail =
//			this.pesquisarEnvioEmail(
//				EnvioEmail.SUSPENDER_IMOVEL_EM_PROGRAMA_ESPECIAL_EMAIL);
//
//		String emailRemetente = envioEmail.getEmailReceptor();
//
//		String tituloMensagem = envioEmail.getTituloMensagem();
//
//		String emailReceptor = envioEmail.getEmailReceptor();
//
//		String mensagem = envioEmail.getCorpoMensagem();
//
//		mensagem = mensagem + " Quantidades de imóveis lidos " + quantidadeImoveisLidos
//			+ " ,  quantidades de imóveis suspensos " + quantidadeImoveisSuspensos + " .";
//
//
//
//		try {
//			ServicosEmail.enviarMensagem(emailRemetente, emailReceptor,
//					tituloMensagem, mensagem);
//		} catch (ErroEmailException erroEnviarEmail) {
//
//		}
	}

	/**
	 *
	 * [UC0973] Inserir Imóvel em Programa Especial
	 * [FS0007] Validar dados do imóvel no programa especial
	 * @author Hugo Amorim
	 * @since 17/12/2009
	 *
	 */
	private boolean validarDadosSuspenderImovelProgramaEspecial(ImovelProgramaEspecial imovelProgramaEspecial,
			SistemaParametro sistemaParametro) throws ControladorException {

		boolean retorno = false;

		// Verifica Situação
		// de agua.
		if(imovelProgramaEspecial.getImovel().getLigacaoAguaSituacao()!=null){
			if(imovelProgramaEspecial.getImovel().getLigacaoAguaSituacao().getId().compareTo(LigacaoAguaSituacao.LIGADO)!=0){

				return true;

			}
		}

		// Verifica se categoria do imovel
		// e igual a residencial
		Collection colecaoSubcategoriasImovel =
			this.getControladorImovel().obterColecaoImovelSubcategorias(imovelProgramaEspecial.getImovel(),new Integer(0));

		if(colecaoSubcategoriasImovel!=null && !colecaoSubcategoriasImovel.isEmpty()){
			for (Iterator iterator = colecaoSubcategoriasImovel.iterator(); iterator
					.hasNext();) {
				ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) iterator.next();

				if(imovelSubcategoria.getComp_id().getSubcategoria().getCategoria().getId().compareTo(Categoria.RESIDENCIAL)!=0){

					return true;
				}
			}
		}

		// Verifica se immóvel não possui hidrometro
		// e tem área construída superior a 100m2

		// Obtém o indicador de existência de hidrômetro para o imóvel, caso exista
		// retorna 1(um) indicando SIM caso contrário retorna 2(dois) indicando NÃO
		Integer possuiHidrometro = this.getControladorImovel().obterIndicadorExistenciaHidrometroImovel(imovelProgramaEspecial.getImovel().getId());

		if(possuiHidrometro.compareTo(new Integer(2))==0
				&& imovelProgramaEspecial.getImovel().getAreaConstruida().compareTo(new BigDecimal("100"))>0){

			return true;
		}

		// [FS0005] Validar dados do imóvel no programa especial
		// Verifica consumo de água do mês atual
		// é maior que 25m3

		FaturamentoGrupo faturamentoGrupo = Fachada.getInstancia()
				.recuperaGrupoFaturamentoDoImovel(imovelProgramaEspecial.getImovel().getId());

		Integer mesAnoFaturamento = faturamentoGrupo.getAnoMesReferencia();
		Integer mesAnoAnteriorFaturamento = Util.subtraiAteSeisMesesAnoMesReferencia(mesAnoFaturamento, 1);

		Integer consumoFaturado = null;

		consumoFaturado = this.getControladorImovel()
								.obterConsumoFaturadoImovelNoMes(imovelProgramaEspecial.getImovel().getId(),
										mesAnoFaturamento);
		if(consumoFaturado==null){

			consumoFaturado = this.getControladorImovel()
				.obterConsumoFaturadoImovelNoMes(imovelProgramaEspecial.getImovel().getId(),
						mesAnoAnteriorFaturamento);
		}

		if(consumoFaturado != null &&
			possuiHidrometro.compareTo(new Integer(1)) == 0 &&
			consumoFaturado.compareTo(new Integer(25)) > 0){

			return true;
		}

		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.IMOVEL_ID, imovelProgramaEspecial.getImovel().getId()));
		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, ClienteRelacaoTipo.RESPONSAVEL));
		filtroClienteImovel.adicionarParametro(new ParametroNulo(
				FiltroClienteImovel.DATA_FIM_RELACAO));
		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_ID, sistemaParametro.getClienteResponsavelProgramaEspecial().getId()));

		Collection clientesImovel = this.getControladorUtil().pesquisar(
				filtroClienteImovel, ClienteImovel.class.getName());

		ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(clientesImovel);

		if(clienteImovel==null){

			return true;
		}

		return retorno;

	}


	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais Analitico
	 *
	 * @author Hugo Leonardo
	 * @date 18/01/2010
	 *
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 *
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws FachadaException
	 */
	public Collection pesquisarRelatorioImoveisProgramasEspeciaisAnalitico(
		FiltrarRelatorioImoveisProgramasEspeciaisHelper helper)throws ControladorException {

		Collection colecaoRetorno = new ArrayList();
		try {

			Collection imovelProgramaEspecial =  repositorioCadastro.pesquisarRelatorioImoveisProgramasEspeciaisAnalitico(helper);

			Iterator iteratorImovelPrograma = imovelProgramaEspecial.iterator();

			while (iteratorImovelPrograma.hasNext()) {

				RelatorioImoveisProgramasEspeciaisHelper relatorioHelper = new RelatorioImoveisProgramasEspeciaisHelper();

				Object[] objeto = (Object[]) iteratorImovelPrograma.next();

				//Id do Imovel
				if ( objeto[0] != null ) {
					String idImovel = objeto[0].toString();
					Imovel imovel = new Imovel();
					relatorioHelper.setEndereco(getControladorEndereco().obterEnderecoAbreviadoImovel(
							(Integer)objeto[0] ));
					imovel.setId(Integer.parseInt(idImovel));

					idImovel = imovel.getMatriculaFormatada();

					relatorioHelper.setIdImovel(idImovel);
				}

				// Id Regiao de Desenvolvimento
				if ( objeto[1] != null ) {
					Integer idRegiaoDesenvolvimento = (Integer) objeto[1];

					relatorioHelper.setIdRegiaoDesenvolvimento( idRegiaoDesenvolvimento);
				}

				//Nome da Regiao de Desenvolvimento
				if ( objeto[2] != null ) {
					relatorioHelper.setNomeRegiaoDesenvolvimento( (String) objeto[2]);
				}

				/*
				//Id da unidade de negocio
				if ( objeto[1] != null ) {
					Integer idUnidadeNegocio = (Integer) objeto[1];

					relatorioHelper.setIdUnidadeNegocio( idUnidadeNegocio);
				}

				//Nome da unidade de negocio
				if ( objeto[2] != null ) {
					relatorioHelper.setNomeUnidadeNegocio( (String) objeto[2]);
				}
				*/

				//Id da localidade
				if ( objeto[3] != null ) {
					Integer idLocalidade = (Integer) objeto[3];

					relatorioHelper.setIdLocalidade( idLocalidade);
				}

				//nome da localidade
				if ( objeto[4] != null ) {
					relatorioHelper.setNomeLocalidade( (String) objeto[4]);
				}

				//nome do cliente
				if( objeto[5] != null){
					relatorioHelper.setNomeUsuario( (String) objeto[5]);
				}

				//situacao medicao
				if( objeto[6] != null){
					relatorioHelper.setSituacaoMedicao( (String) objeto[6]);
				}

				//consumo agua
				if(objeto[7] != null){
					Integer consumoAgua = (Integer) objeto[7];

					relatorioHelper.setConsumoAgua( consumoAgua);
				}

				//valor conta
				if(objeto[8] != null){
					BigDecimal valorConta = (BigDecimal) objeto[8];

					relatorioHelper.setValorConta( valorConta);
				}

				colecaoRetorno.add(relatorioHelper);
			}

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		return colecaoRetorno;
	}

	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais
	 *
	 * @author Hugo Leonardo
	 * @date 19/01/2010
	 *
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 *
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws FachadaException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisProgramaEspecial(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper filtro)
			throws ControladorException{


		try {
			return this.repositorioCadastro
					.pesquisarTotalRegistroRelatorioImoveisProgramaEspecial(filtro);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais Sintetico
	 *
	 * @author Hugo Leonardo
	 * @date 25/01/2010
	 *
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 *
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws FachadaException
	 */
	public Collection pesquisarRelatorioImoveisProgramasEspeciaisSintetico(
		FiltrarRelatorioImoveisProgramasEspeciaisHelper helper)throws ControladorException {

		Collection colecaoRetorno = new ArrayList();
		try {

			Collection imovelProgramaEspecialSemHidr =
				repositorioCadastro.pesquisarRelatorioImoveisProgramasEspeciaisSintetico(helper);

			Iterator iteratorImovelPrograma = imovelProgramaEspecialSemHidr.iterator();

			while (iteratorImovelPrograma.hasNext()) {

				RelatorioImoveisProgramasEspeciaisHelper relatorioHelper =
						new RelatorioImoveisProgramasEspeciaisHelper();

				Object[] objeto = (Object[]) iteratorImovelPrograma.next();

				// Id Região de Desenvolvimento
				if ( objeto[0] != null ) {
					Integer idRegiaoDesenvolvimento = (Integer) objeto[0];

					relatorioHelper.setIdRegiaoDesenvolvimento( idRegiaoDesenvolvimento);
				}

				//Nome da unidade de negocio
				if ( objeto[1] != null ) {
					relatorioHelper.setNomeRegiaoDesenvolvimento( (String) objeto[1]);
				}

				/*
				//Id da unidade de negocio
				if ( objeto[0] != null ) {
					Integer idUnidadeNegocio = (Integer) objeto[0];

					relatorioHelper.setIdUnidadeNegocio( idUnidadeNegocio);
				}

				//Nome da unidade de negocio
				if ( objeto[1] != null ) {
					relatorioHelper.setNomeUnidadeNegocio( (String) objeto[1]);
				}
				*/

				//Id da localidade
				if ( objeto[2] != null ) {
					Integer idLocalidade = (Integer) objeto[2];

					relatorioHelper.setIdLocalidade( idLocalidade);
				}

				//nome da localidade
				if ( objeto[3] != null ) {
					relatorioHelper.setNomeLocalidade( (String) objeto[3]);
				}

				// QTD Imoveis Sem HIDR.
				if(objeto[4] != null){
					Integer qtdImoveisSemHidr = (Integer) objeto[4];

					relatorioHelper.setQtdImoveisSemHidr(qtdImoveisSemHidr);
				}

				// Valor Contas de Imoveis Sem HIDR.
				if(objeto[5] != null){
					BigDecimal valorContasSemHidr = (BigDecimal) objeto[5];

					relatorioHelper.setValorContasSemHidr(valorContasSemHidr);
				}

				// QTD de Imoveis com Hidro
				if ( objeto[6] != null ) {
					Integer qtdImoveisComHidr = (Integer) objeto[6];

					relatorioHelper.setQtdImoveisComHidr( qtdImoveisComHidr);
				}

				//valor Contas de Imoveis com HIDR
				if ( objeto[7] != null ) {
					BigDecimal valorContasComHidr = (BigDecimal) objeto[7];

					relatorioHelper.setValorContasComHidr( valorContasComHidr);
				}

				colecaoRetorno.add(relatorioHelper);
			}

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		return colecaoRetorno;
	}

	/**
	 *
	 * [UC0976] Suspender Imóvel em Programa Especial Batch
	 *   Suspende Imóvel em Programa Especial Forma Batch
	 * @author Hugo Amorim
	 * @since 29/01/2010
	 *
	 */
    private void efetuarSuspensaoImovelEmProgramaEspecial(ImovelProgramaEspecial imovelProgramaEspecial,
			Usuario usuarioLogado,Short formaSuspensao) throws ControladorException{

		SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();

		Date dataAtual = new Date();

		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");

		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");

		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, imovelProgramaEspecial.getImovel().getId()));

		filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));

		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, ClienteRelacaoTipo.RESPONSAVEL));

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_ID, sistemaParametro
						.getClienteResponsavelProgramaEspecial().getId()));

		Collection clientesImovel = this.getControladorUtil().pesquisar(filtroClienteImovel, ClienteImovel.class.getName());

		ClienteImovel clienteImovelAtulizar = (ClienteImovel) Util.retonarObjetoDeColecao(clientesImovel);

		if(clienteImovelAtulizar!=null){

			clienteImovelAtulizar.setDataFimRelacao(dataAtual);
			ClienteImovelFimRelacaoMotivo clienteImovelFimRelacaoMotivo = new ClienteImovelFimRelacaoMotivo();
			clienteImovelFimRelacaoMotivo.setId(ClienteImovelFimRelacaoMotivo.EXCLUSAO_PROGRAMA_ESPECIAL);
			clienteImovelAtulizar.setClienteImovelFimRelacaoMotivo(clienteImovelFimRelacaoMotivo);
			clienteImovelAtulizar.setUltimaAlteracao(dataAtual);

			this.getControladorUtil().atualizar(clienteImovelAtulizar);
		}

		Imovel imovel = imovelProgramaEspecial.getImovel();
		ImovelPerfil imovelPerfil = new ImovelPerfil();
		imovelPerfil.setId(ImovelPerfil.NORMAL);
		imovel.setIndicadorEmissaoExtratoFaturamento(ConstantesSistema.NAO);
		imovel.setImovelPerfil(imovelPerfil);
		imovel.setUltimaAlteracao(dataAtual);
		imovel.setUsuarioParaHistorico(usuarioLogado);

		this.getControladorAtualizacaoCadastro().atualizar(imovel);

		imovelProgramaEspecial.setUltimaAlteracao(dataAtual);
		imovelProgramaEspecial.setMesAnoSaidaPrograma(imovel.getQuadra().getRota().getFaturamentoGrupo().getAnoMesReferencia());
		imovelProgramaEspecial.setUsuarioSuspensao(Usuario.USUARIO_BATCH);
		imovelProgramaEspecial.setFormaSuspensao(formaSuspensao);
		imovelProgramaEspecial.setDataSuspensao(dataAtual);
		imovelProgramaEspecial.setUltimaAlteracao(dataAtual);

		this.getControladorUtil().atualizar(imovelProgramaEspecial);

	}

    /**
	 *
	 * [UC0976] Suspender Imóvel em Programa Especial Batch
	 *
	 * Remover somente Relação caso tenha sido suspenso de forma online.
	 *
	 * @author Hugo Amorim
     * @throws ControladorException
	 * @since 29/01/2010
	 *
	 */
    private boolean verificarRemocaoRelacaoClienteComImovel(ImovelProgramaEspecial imovelProgramaEspecial,SistemaParametro sistemaParametro) throws ControladorException{

    	boolean retorno = false;

    	//Date dataAtual = new Date();

    	FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.IMOVEL_ID, imovelProgramaEspecial
						.getImovel().getId()));

		filtroClienteImovel.adicionarParametro(
				new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID,
				ClienteRelacaoTipo.RESPONSAVEL));

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_ID, sistemaParametro
						.getClienteResponsavelProgramaEspecial().getId()));

		Collection clientesImovel = this.getControladorUtil().pesquisar(filtroClienteImovel,
				ClienteImovel.class.getName());

		ClienteImovel clienteImovelAtulizar = (ClienteImovel) Util
				.retonarObjetoDeColecao(clientesImovel);

		if(clienteImovelAtulizar!=null){
			/*
			clienteImovelAtulizar.setDataFimRelacao(dataAtual);
			ClienteImovelFimRelacaoMotivo clienteImovelFimRelacaoMotivo = new ClienteImovelFimRelacaoMotivo();
			clienteImovelFimRelacaoMotivo
					.setId(ClienteImovelFimRelacaoMotivo.EXCLUSAO_PROGRAMA_ESPECIAL);
			clienteImovelAtulizar
					.setClienteImovelFimRelacaoMotivo(clienteImovelFimRelacaoMotivo);
			clienteImovelAtulizar.setUltimaAlteracao(dataAtual);

			this.getControladorUtil().atualizar(clienteImovelAtulizar);
			*/

			retorno = true;
		}
      return retorno;
    }

    /**
	 *
	 * [UC0973] Inserir Imóvel em Programa Especial
	 *
	 * Verificar se existe parcelamento para o Imovel em Programa Especial.
	 *
	 * @author Hugo Leonardo
     * @throws ControladorException
	 * @date 10/02/2010
	 *
	 */

			public Boolean verificarExistenciaParcelamentoImovel(Integer idImovel) throws ControladorException{

    	try {
			Integer qtdSetores = this.repositorioCadastro.verificarExistenciaParcelamentoImovel(idImovel);
			return (qtdSetores > 0);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
    }

    /**
	 * [UC0999] Gerar Relatório de Coleta de Medidor de Energia.
	 *
	 * @author Hugo Leonardo
	 * @date 09/03/2010
	 *
	 * @param FiltrarRelatorioColetaMedidorEnergiaHelper
	 *
	 * @return Collection<RelatorioColetaMedidorEnergiaHelper>
	 * @throws ControladorException
	 */
	public Collection<RelatorioColetaMedidorEnergiaHelper> pesquisarRelatorioColetaMedidorEnergia(
			FiltrarRelatorioColetaMedidorEnergiaHelper helper) throws ControladorException{

		Collection colecaoRetorno = new ArrayList();
		try {

			Collection coletaMedidorEnergia =
				repositorioCadastro.pesquisarRelatorioColetaMedidorEnergia(
						helper.getIdFaturamentoGrupo() != null ? helper.getIdFaturamentoGrupo().toString() : null,
						helper.getIdLocalidadeInicial() != null ? helper.getIdLocalidadeInicial().toString() : null,
						helper.getIdLocalidadeFinal() != null ? helper.getIdLocalidadeFinal().toString() : null,
						helper.getIdSetorComercialInicial() != null ? helper.getIdSetorComercialInicial().toString() : null,
						helper.getIdSetorComercialFinal() != null ? helper.getIdSetorComercialFinal().toString() : null,
						helper.getRotaInicial() != null ? helper.getRotaInicial().toString() : null,
						helper.getRotaFinal() != null ? helper.getRotaFinal().toString() : null,
						helper.getSequencialRotaInicial() != null ? helper.getSequencialRotaInicial().toString() : null,
						helper.getSequencialRotaFinal() != null ? helper.getSequencialRotaFinal().toString() : null);

			Iterator iteratorColetaMedidorEnergia = coletaMedidorEnergia.iterator();

			while (iteratorColetaMedidorEnergia.hasNext()) {

				RelatorioColetaMedidorEnergiaHelper relatorioHelper =
						new RelatorioColetaMedidorEnergiaHelper();

				Object[] objeto = (Object[]) iteratorColetaMedidorEnergia.next();

				// Id do faturamentoGrupo
				if ( objeto[0] != null ) {
					Integer faturamentoGrupo = (Integer) objeto[0];

					relatorioHelper.setIdFaturamentoGrupo( faturamentoGrupo.toString());
				}

				// Descrição do faturamentoGrupo
				if ( objeto[1] != null ) {
					String descricaoFaturamentoGrupo = (String) objeto[1];
					relatorioHelper.setDescricaoFaturamentoGrupo( descricaoFaturamentoGrupo);
				}

				// Id localidade
				if ( objeto[2] != null ) {
					Integer idLocalidade = (Integer) objeto[2];
					relatorioHelper.setIdLocalidade( idLocalidade.toString());
				}

				// Descrição localidade
				if ( objeto[3] != null ) {
					String descricaoLocalidade = (String) objeto[3];
					relatorioHelper.setDescricaoLocalidade( descricaoLocalidade);
				}

				// Codigo Rota
				if ( objeto[4] != null ) {
					Short codigoRota = (Short) objeto[4];
					relatorioHelper.setRota( codigoRota.toString());
				}

				// Nome Cliente
				if ( objeto[5] != null ) {
					String nomeCliente = (String) objeto[5];
					relatorioHelper.setNomeCliente( nomeCliente);
				}

				// Id Imovel
				if ( objeto[6] != null ) {
					Integer idImovel = (Integer) objeto[6];
					relatorioHelper.setMatriculaImovel( idImovel.toString());
				}

				colecaoRetorno.add(relatorioHelper);
			}

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		return colecaoRetorno;

	}

    /**
	 * [UC0999] Gerar Relatório de Coleta de Medidor de Energia.
	 *
	 * Obtém a quantidade de imoveis de acordo com o filtro.
	 *
	 * @author Hugo Leonardo
	 * @date 09/03/2010
	 *
	 * @param FiltrarRelatorioColetaMedidorEnergiaHelper
	 *
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer countRelatorioColetaMedidorEnergia(
			FiltrarRelatorioColetaMedidorEnergiaHelper helper) throws ControladorException{

		try {
			return this.repositorioCadastro
					.pesquisarTotalRegistroRelatorioColetaMedidorEnergia(
							helper.getIdFaturamentoGrupo() != null ? helper.getIdFaturamentoGrupo().toString() : null,
							helper.getIdLocalidadeInicial() != null ? helper.getIdLocalidadeInicial().toString() : null,
							helper.getIdLocalidadeFinal() != null ? helper.getIdLocalidadeFinal().toString() : null,
							helper.getIdSetorComercialInicial() != null ? helper.getIdSetorComercialInicial().toString() : null,
							helper.getIdSetorComercialFinal() != null ? helper.getIdSetorComercialFinal().toString() : null,
							helper.getRotaInicial() != null ? helper.getRotaInicial().toString() : null,
							helper.getRotaFinal() != null ? helper.getRotaFinal().toString() : null,
							helper.getSequencialRotaInicial() != null ? helper.getSequencialRotaInicial().toString() : null,
							helper.getSequencialRotaFinal() != null ? helper.getSequencialRotaFinal().toString() : null);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

    /**
	 * [UC1011] Emitir Boletim de Cadastro Individual.
	 *
	 * Criar Dados para Relatório de Boletim de Cadastro Individual
	 *
	 * @author Hugo Leonardo
	 * @date 24/03/2010
	 *
	 * @param idImovel
	 *
	 * @return RelatorioBoletimCadastroIndividualBean
	 * @throws ControladorException
	 */
	public RelatorioBoletimCadastroIndividualBean criarDadosRelatorioBoletimCadastroIndividual(
			Integer idImovel) throws ControladorException{

		RelatorioBoletimCadastroIndividualBean bean = new RelatorioBoletimCadastroIndividualBean();


		ClienteEmitirBoletimCadastroHelper clienteProprietario = null;
		ClienteEmitirBoletimCadastroHelper clienteUsuario = null;

		clienteProprietario = getControladorCliente()
				.pesquisarClienteEmitirBoletimCadastro(idImovel, ClienteRelacaoTipo.PROPRIETARIO);

		clienteUsuario = getControladorCliente()
				.pesquisarClienteEmitirBoletimCadastro(idImovel, ClienteRelacaoTipo.USUARIO);

		// Início do processo de geração do arquivo txt

		// Dados do Cliente Proprietário
		if (clienteProprietario != null) {

			//Nome Proprietario
			String nomeProprietario = "";
			if (clienteProprietario.getCliente().getNome() != null) {
				nomeProprietario = clienteProprietario.getCliente().getNome();
			}
			bean.setNomeProprietario(nomeProprietario);

			//sexo Proprietario
			String sexoProprietario = "";
			if(clienteProprietario.getCliente().getPessoaSexo() != null){
				sexoProprietario = clienteProprietario.getCliente().getPessoaSexo().getId().toString();
			}
			bean.setSexoProprietario(sexoProprietario);

			// cpf Proprietario
			String cpfProprietario = "";
			if(clienteProprietario.getCliente().getCpf() != null){
				cpfProprietario = clienteProprietario.getCliente().getCpfFormatado();
			}
			bean.setCpfProprietario(cpfProprietario);

			// cnpj Proprietario
			String cnpjProprietario = "";
			if(clienteProprietario.getCliente().getCnpjFormatado() != null){
				cnpjProprietario = clienteProprietario.getCliente().getCnpjFormatado();
			}
			bean.setCnpjProprietario(cnpjProprietario);

			//rg Proprietario
			String rgProprietario = "";
			if (clienteProprietario.getCliente().getRg() != null) {
				rgProprietario = clienteProprietario.getCliente().getRg();

				// Órgão Expedidor RG
				String orgaoExpedidorRG = "";

				if (clienteProprietario.getCliente().getOrgaoExpedidorRg() != null) {
					orgaoExpedidorRG = clienteProprietario.getCliente()
							.getOrgaoExpedidorRg().getDescricaoAbreviada();
				}
				rgProprietario += " " + orgaoExpedidorRG;
			}
			bean.setRgProprietario(rgProprietario);

			//uf Proprietario
			String ufProprietario = "";
			if(clienteProprietario.getCliente().getUnidadeFederacao() != null
					&& clienteProprietario.getCliente().getUnidadeFederacao().getSigla() != null){
				ufProprietario = clienteProprietario.getCliente().getUnidadeFederacao().getSigla();
			}
			bean.setUfProprietario(ufProprietario);

			// fone Tipo Proprietario
			// Número fone Proprietario
			Collection clientesFone = clienteProprietario.getClientesFone();

			if (clientesFone != null && !clientesFone.isEmpty()) {

				Iterator clientesFoneIterator = clientesFone.iterator();

				while (clientesFoneIterator.hasNext()) {

					ClienteFone clienteFone = (ClienteFone) clientesFoneIterator.next();

					//fone Tipo Proprietario
					String foneTipoProprietario = "";
					if (clienteFone.getFoneTipo() != null ) {
						foneTipoProprietario = clienteFone.getFoneTipo().getId().toString();
					}
					bean.setFoneTipoProprietario(foneTipoProprietario);

					// Número fone Proprietario
					String foneProprietario = "";

					if (clienteFone.getTelefone() != null) {
						foneProprietario = clienteFone.getDddTelefone();
					}

					// Ramal
					String ramal = "";

					if (clienteFone.getRamal() != null) {
						ramal = clienteFone.getRamal();
						foneProprietario += "-"+ramal;
					}
					bean.setFoneProprietario(foneProprietario);
				}
			}

			// endereco Proprietario
			String enderecoProprietario = "";

			// Logradouro
			String logradouro = "";
			if (clienteProprietario.getClienteEndereco()
					.getLogradouroCep() != null
					&& clienteProprietario.getClienteEndereco()
							.getLogradouroCep().getLogradouro() != null) {

				logradouro = Util.adicionarZerosEsquedaNumero(9,
						clienteProprietario.getClienteEndereco()
								.getLogradouroCep().getLogradouro().getId()
								.toString());
			}
			// Endereço Abreviado
			String endereco = "";
			if (clienteProprietario.getEnderecoFormatado() != null) {
				endereco = clienteProprietario.getEnderecoFormatado();
			}
			enderecoProprietario += logradouro +" "+endereco;
			bean.setEnderecoProprietario(enderecoProprietario);

			// endereco Ref. Proprietario
			String enderecoRefProprietario = "";
			if(clienteProprietario.getClienteEndereco().getEnderecoReferencia() != null
					&& clienteProprietario.getClienteEndereco().getEnderecoReferencia().getDescricao() != null){
				enderecoRefProprietario = clienteProprietario.getClienteEndereco()
						.getEnderecoReferencia().getDescricao();
			}
			bean.setEnderecoRefProprietario(enderecoRefProprietario);

			//Id Endereco Ref. Proprietario
			String idEnderecoRefProprietario = "";
			if(clienteProprietario.getClienteEndereco()
					.getEnderecoReferencia() != null){

				idEnderecoRefProprietario = clienteProprietario.getClienteEndereco()
					.getEnderecoReferencia().getId().toString();
			}
			bean.setIdEnderecoRefProprietario(idEnderecoRefProprietario);

			//enderecoComplementoProprietario
			String enderecoComplementoProprietario = "";
			if(clienteProprietario.getClienteEndereco().getComplemento() != null){
				enderecoComplementoProprietario = clienteProprietario.getClienteEndereco().getComplemento();
			}
			bean.setEnderecoComplementoProprietario(enderecoComplementoProprietario);

			//bairro Proprietario
			String bairroProprietario = "";
			if(clienteProprietario.getClienteEndereco().getLogradouroBairro().getBairro() != null
					&& clienteProprietario.getClienteEndereco().getLogradouroBairro().getBairro().getNome() != null){
				bairroProprietario = clienteProprietario.getClienteEndereco().getLogradouroBairro()
						.getBairro().getNome();
			}
			bean.setBairroProprietario(bairroProprietario);

			//municipio Proprietario
			String municipioProprietario = "";
			if(clienteProprietario.getClienteEndereco().getLogradouroBairro().getBairro() != null
					&& clienteProprietario.getClienteEndereco().getLogradouroBairro()
					.getBairro().getMunicipio().getNome() != null){
				municipioProprietario = clienteProprietario.getClienteEndereco().getLogradouroBairro()
					.getBairro().getMunicipio().getNome();
			}
			bean.setMunicipioProprietario(municipioProprietario);

			//cep Proprietario
			String cepProprietario = "";
			if(clienteProprietario.getClienteEndereco().getLogradouroCep().getCep() != null){
				cepProprietario = clienteProprietario.getClienteEndereco().getLogradouroCep()
						.getCep().getCepFormatado();
			}
			bean.setCepProprietario(cepProprietario);

			//endereco Tipo Proprietario
			String enderecoTipoProprietario = "";
			if(clienteProprietario.getClienteEndereco().getEnderecoTipo() != null){
				enderecoTipoProprietario = clienteProprietario.getClienteEndereco().getEnderecoTipo().getId().toString();
			}
			bean.setEnderecoTipoProprietario(enderecoTipoProprietario);

			//
			// USUÁRIO
			//

			//nome Usuario
			String nomeUsuario = "";
			if(clienteUsuario.getCliente().getNome() != null){
				nomeUsuario = clienteUsuario.getCliente().getNome();
			}
			bean.setNomeUsuario(nomeUsuario);

			//sexo Usuario
			String sexoUsuario = "";
			if(clienteUsuario.getCliente() != null && clienteUsuario.getCliente().getPessoaSexo() != null){
				sexoUsuario = clienteUsuario.getCliente().getPessoaSexo().getId().toString();
			}
			bean.setSexoUsuario(sexoUsuario);

			//cpf Usuario
			String cpfUsuario = "";
			if(clienteUsuario.getCliente().getCpf() != null){
				cpfUsuario = clienteUsuario.getCliente().getCpfFormatado();
			}
			bean.setCpfUsuario(cpfUsuario);

			// cnpj Usuario
			String cnpjUsuario = "";
			if(clienteUsuario.getCliente().getCnpjFormatado() != null){
				cnpjUsuario = clienteUsuario.getCliente().getCnpjFormatado();
			}
			bean.setCnpjUsuario(cnpjUsuario);

			//rg Usuario
			String rgUsuario = "";
			if (clienteUsuario.getCliente().getRg() != null) {
				rgUsuario = clienteUsuario.getCliente().getRg();

				// Órgão Expedidor RG
				String orgaoExpedidorRG = "";

				if (clienteUsuario.getCliente().getOrgaoExpedidorRg() != null) {
					orgaoExpedidorRG = clienteUsuario.getCliente()
							.getOrgaoExpedidorRg().getDescricaoAbreviada();
				}
				rgUsuario += " " + orgaoExpedidorRG;
			}
			bean.setRgUsuario(rgUsuario);

			//uf Usuario
			String ufUsuario = "";
			if(clienteUsuario.getCliente().getUnidadeFederacao() != null && clienteUsuario.getCliente().getUnidadeFederacao().getSigla() != null){
				ufUsuario = clienteUsuario.getCliente().getUnidadeFederacao().getSigla();
			}
			bean.setUfUsuario(ufUsuario);

			// fone Tipo Usuario
			// Número fone Usuario
			Collection clientesFoneUsuario = clienteUsuario.getClientesFone();

			if (clientesFoneUsuario != null && !clientesFoneUsuario.isEmpty()) {

				Iterator clientesFoneIterator = clientesFoneUsuario.iterator();

				while (clientesFoneIterator.hasNext()) {

					ClienteFone clienteFone = (ClienteFone) clientesFoneIterator.next();

					//fone Tipo Usuario
					String foneTipoUsuario = "";
					if (clienteFone.getFoneTipo() != null) {
						foneTipoUsuario = clienteFone.getFoneTipo().getId().toString();
					}
					bean.setFoneTipoUsuario(foneTipoUsuario);

					// Número fone Proprietario
					String foneUsuario = "";

					if (clienteFone.getTelefone() != null) {
						foneUsuario = clienteFone.getDddTelefone();
					}

					// Ramal
					String ramal = "";

					if (clienteFone.getRamal() != null) {
						ramal = clienteFone.getRamal();
						foneUsuario += "-"+ramal;
					}
					bean.setFoneUsuario(foneUsuario);
				}
			}

			//Carregar Imovel
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.IMOVEL_PERFIL);
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.ENDERECO_REFERENCIA);
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.CEP);
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.BAIRRO);
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.MUNICIPIO);
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.PAVIMENTO_RUA);
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.PAVIMENTO_CALCADA);
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.CADASTRO_OCORRENCIA);

			Collection<Imovel> imovelPesquisado =
				this.getControladorUtil().pesquisar(filtroImovel, Imovel.class.getName());

			Imovel imovel= null;

			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
				imovel = (Imovel) Util.retonarObjetoDeColecao(imovelPesquisado);
			}

			// inscricao
			String inscricao = "";
			inscricao = this.getControladorImovel().pesquisarInscricaoImovel(idImovel);
			bean.setInscricao(inscricao);

			//matricula
			String matricula = "";
			matricula = Util.retornaMatriculaImovelFormatada(idImovel);
			bean.setMatricula(matricula);

			//perfil Imovel
			String perfilImovel = "";
			if(imovel.getImovelPerfil() != null){
				perfilImovel = imovel.getImovelPerfil().getId().toString();
			}
			bean.setPerfilImovel(perfilImovel);

			//endereco Imovel
			String enderecoImovel = "";
			enderecoImovel = getControladorEndereco().pesquisarEndereco(idImovel);
			bean.setEnderecoImovel(enderecoImovel);

			//endereco Ref. Imovel
			String enderecoRefImovel = "";
			if(imovel.getEnderecoReferencia() != null && imovel.getEnderecoReferencia().getDescricao() != null){
				enderecoRefImovel = imovel.getEnderecoReferencia().getDescricao().toString();
			}
			bean.setEnderecoRefImovel(enderecoRefImovel);

			// Id Endereco Ref. Imovel
			String idEnderecoRefImovel = "";
			if(imovel.getEnderecoReferencia() != null){

				idEnderecoRefImovel = imovel.getEnderecoReferencia().getId().toString();
			}
			bean.setIdEnderecoRefImovel(idEnderecoRefImovel);

			//endereco Complemento Imovel
			String enderecoComplementoImovel = "";
			if(imovel.getComplementoEndereco() != null){
				enderecoComplementoImovel = imovel.getComplementoEndereco();
			}
			bean.setEnderecoComplementoImovel(enderecoComplementoImovel);

			//bairro Imovel
			String bairroImovel = "";
			if(imovel.getLogradouroBairro().getBairro() != null
					&& imovel.getLogradouroBairro().getBairro().getNome() != null){
				bairroImovel = imovel.getLogradouroBairro().getBairro().getNome();
			}
			bean.setBairroImovel(bairroImovel);

			//municipio Imovel
			String municipioImovel = "";
			if(imovel.getLogradouroBairro().getBairro() != null
					&& imovel.getLogradouroBairro().getBairro().getMunicipio().getNome() != null){
				municipioImovel = imovel.getLogradouroBairro().getBairro().getMunicipio().getNome();
			}
			bean.setMunicipioImovel(municipioImovel);

			//cep Imovel
			String cepImovel = "";
			if(imovel.getLogradouroCep() != null && imovel.getLogradouroCep().getCep().getCodigo() != null){
				cepImovel = imovel.getLogradouroCep().getCep().getCodigo().toString();
			}
			bean.setCepImovel(cepImovel);

			//numero Moradores
			String numeroMoradores = "";
			if(imovel.getNumeroMorador() != null){
				numeroMoradores = imovel.getNumeroMorador().toString();
			}
			bean.setNumeroMoradores(numeroMoradores);

			//numero Medidor Celpe
			String numeroMedidorCelpe = "";
			if(imovel.getNumeroMedidorEnergia() != null){
				numeroMedidorCelpe = imovel.getNumeroMedidorEnergia();
			}
			bean.setNumeroMedidorCelpe(numeroMedidorCelpe);

			//pavimento Tipo Rua
			String pavimentoTipoRua = "";
			if(imovel.getPavimentoRua() != null){
				pavimentoTipoRua = imovel.getPavimentoRua().getId().toString();
			}
			bean.setPavimentoTipoRua(pavimentoTipoRua);

			// Pavimento Tipo Calcada
			String pavimentoTipoCalcada = "";
			if ( imovel.getPavimentoCalcada().getId() != null) {
				pavimentoTipoCalcada = imovel.getPavimentoCalcada().getId().toString();
			}
			bean.setPavimentoTipoCalcada(pavimentoTipoCalcada);

			//abastecimento Fonte
			String abastecimentoFonte = "";
			if(imovel.getFonteAbastecimento().getId() != null){
				abastecimentoFonte = imovel.getFonteAbastecimento().getId().toString();
			}

			bean.setAbastecimentoFonte(abastecimentoFonte);

			//esgoto Situacao
			String esgotoSituacao = "";
			if(imovel.getLigacaoEsgotoSituacao() != null){
				esgotoSituacao = imovel.getLigacaoEsgotoSituacao().getId().toString();
			}
			bean.setEsgotoSituacao(esgotoSituacao);

			//agua Situacao
			String aguaSituacao = "";
			if(imovel.getLigacaoAguaSituacao() != null){
				aguaSituacao = imovel.getLigacaoAguaSituacao().getId().toString();
			}
			bean.setAguaSituacao(aguaSituacao);

			// Obtém os dados das ligações de água e esgoto
			DadosLigacoesBoletimCadastroHelper dadosLigacoesBoletimCadastroHelper = getControladorAtendimentoPublico()
					.obterDadosLigacaoAguaEsgoto(idImovel);


			//hidrometro
			// se igual a 0 - NÃO
			// se igual a 1 - SIM
			String hidrometro = "0";

			// hidrometro Numero
			String hidrometroNumero = "";
			if (dadosLigacoesBoletimCadastroHelper.getLigacaoAgua() != null
					&& dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
							.getHidrometroInstalacaoHistorico() != null) {

				hidrometro = "1";
				hidrometroNumero = dadosLigacoesBoletimCadastroHelper
						.getLigacaoAgua().getHidrometroInstalacaoHistorico()
						.getHidrometro().getNumero();
			}
			bean.setHidrometro(hidrometro);
			bean.setHidrometroNumero(hidrometroNumero);

			// hidrometro Capacidade
			String hidrometroCapacidade = "";
			if (dadosLigacoesBoletimCadastroHelper.getLigacaoAgua() != null
					&& dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
							.getHidrometroInstalacaoHistorico() != null
					&& dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
							.getHidrometroInstalacaoHistorico().getHidrometro()
							.getHidrometroCapacidade() != null) {

				hidrometroCapacidade = Util.adicionarZerosEsquedaNumero(2,
						dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
								.getHidrometroInstalacaoHistorico().getHidrometro()
								.getHidrometroCapacidade().getId().toString());

			}
			bean.setHidrometroCapacidade(hidrometroCapacidade);

			// hidrometro Marca
			String hidrometroMarca = "";
			if (dadosLigacoesBoletimCadastroHelper.getLigacaoAgua() != null
					&& dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
							.getHidrometroInstalacaoHistorico() != null
					&& dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
							.getHidrometroInstalacaoHistorico().getHidrometro()
							.getHidrometroMarca() != null) {

				hidrometroMarca = Util.adicionarZerosEsquedaNumero(2,
						dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
								.getHidrometroInstalacaoHistorico().getHidrometro()
								.getHidrometroMarca().getId().toString());

			}
			bean.setHidrometroMarca(hidrometroMarca);

			// Local de Instalação do Hidrômetro
			String localInstalacao = "";
			if (dadosLigacoesBoletimCadastroHelper.getLigacaoAgua() != null
					&& dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
							.getHidrometroInstalacaoHistorico() != null
					&& dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
							.getHidrometroInstalacaoHistorico()
							.getHidrometroLocalInstalacao() != null) {

				localInstalacao = Util.adicionarZerosEsquedaNumero(2,
						dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
								.getHidrometroInstalacaoHistorico()
								.getHidrometroLocalInstalacao().getId().toString());

			}
			bean.setLocalInstalacao(localInstalacao);

			// protecao Tipo
			String protecaoTipo = "";
			if (dadosLigacoesBoletimCadastroHelper.getLigacaoAgua() != null
					&& dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
							.getHidrometroInstalacaoHistorico() != null
					&& dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
							.getHidrometroInstalacaoHistorico()
							.getHidrometroProtecao() != null) {

				protecaoTipo = dadosLigacoesBoletimCadastroHelper
						.getLigacaoAgua().getHidrometroInstalacaoHistorico()
						.getHidrometroProtecao().getId().toString();

			}
			bean.setProtecaoTipo(protecaoTipo);

			// Indicador Cavalete
			String cavalete = "";
			if (dadosLigacoesBoletimCadastroHelper.getLigacaoAgua() != null
					&& dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
							.getHidrometroInstalacaoHistorico() != null
					&& dadosLigacoesBoletimCadastroHelper.getLigacaoAgua()
							.getHidrometroInstalacaoHistorico()
							.getIndicadorExistenciaCavalete() != null) {

				cavalete = dadosLigacoesBoletimCadastroHelper
						.getLigacaoAgua().getHidrometroInstalacaoHistorico()
						.getIndicadorExistenciaCavalete().toString();

			}
			bean.setCavalete(cavalete);

			//ocorrenciaCadastro
			String ocorrenciaCadastro = "";
			if(imovel.getCadastroOcorrencia() != null ){
				ocorrenciaCadastro = imovel.getCadastroOcorrencia().getId().toString();
			}
			bean.setOcorrenciaCadastro(ocorrenciaCadastro);


			//categoriaPrincipal
			String categoriaPrincipal = "";

			// Descrição Abreviada da Principal Categoria do imovel
			Categoria categoria = this.getControladorImovel()
					.obterPrincipalCategoriaImovel(idImovel);

			if (categoria != null) {
				categoriaPrincipal = categoria.getId().toString();
			}
			bean.setCategoriaPrincipal(categoriaPrincipal);

		}

		// Dados das Subcategorias
		Collection colecaoSubcategorias = getControladorImovel()
				.obterQuantidadeEconomiasSubCategoria(idImovel);

		String subcategorias = "";
		String numeroEconomias = "";

		if (colecaoSubcategorias != null && !colecaoSubcategorias.isEmpty()) {

			Iterator colecaoSubcategoriasIterator = colecaoSubcategorias
					.iterator();

			for (int i = 0; i < 6; i++) {

				if (colecaoSubcategoriasIterator.hasNext()) {

					Subcategoria subcategoria = (Subcategoria) colecaoSubcategoriasIterator
							.next();

					subcategorias += Util.adicionarZerosEsquedaNumero(2, subcategoria
									.getId().toString() + "        ");

					numeroEconomias += Util.adicionarZerosEsquedaNumero(4, subcategoria
									.getQuantidadeEconomias().toString() + "        ");
				} else {
					break;
				}
			}
		}
		bean.setSubcategorias(subcategorias);
		bean.setNumeroEconomias(numeroEconomias);

		return bean;
	}

	/**
	 *
	 * Batch criado para atualização da coluna codigo debito automatico do imovel.
	 *
	 * @author Hugo Amorim
	 * @date 30/03/2010
	 */
	public void atualizarCodigoDebitoAutomatico(Integer idFuncionalidadeIniciada,
			SetorComercial setorComercial) throws ControladorException{

		int idUnidadeIniciada = 0;

		try {

			// -------------------------
			// Registrar o início do processamento da Unidade de
			// Processamento do Batch
			// -------------------------

			idUnidadeIniciada = getControladorBatch()
					.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
							UnidadeProcessamento.SETOR_COMERCIAL, setorComercial.getId());

			// Variáveis para a paginação da pesquisa
			// ========================================================================
			boolean flagTerminou = false;
			final int quantidadeMaxima = 300;
			int quantidadeInicio = 0;
			// ========================================================================

			//Variaveis
			String matriculaSemDigito = null;
			Integer codigoDebitoAutomatico = null;

			while (!flagTerminou) {

				Collection<Integer> colecaoDados =
					this.repositorioCadastro
						.pesquisarIdsImoveisDoSetorComercial(setorComercial.getId(),quantidadeInicio,quantidadeMaxima);

				for (Integer idImovel : colecaoDados) {

					matriculaSemDigito = Util.obterMatriculaSemDigitoVerificador(idImovel.toString());
					Integer digitoVerificadorModulo11 = Util.obterDigitoVerificadorModulo11(matriculaSemDigito);

					codigoDebitoAutomatico = new Integer(matriculaSemDigito+digitoVerificadorModulo11.toString());

					this.repositorioCadastro.atualizarCodigoDebitoAutomatico(idImovel,codigoDebitoAutomatico);

				}

				// Incrementa o nº do indice da páginação
				quantidadeInicio = quantidadeInicio + quantidadeMaxima;

				/**
				 * Caso a coleção de dados retornados for menor que a
				 * quantidade de registros seta a flag indicando que a
				 * paginação terminou.
				 */
				if (colecaoDados == null ||
						colecaoDados.size() < quantidadeMaxima) {

					flagTerminou = true;
				}

				if (colecaoDados != null) {
					colecaoDados.clear();
					colecaoDados = null;
				}
			}



			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);

		} catch (Exception e) {
			e.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,idUnidadeIniciada, true);
			throw new EJBException(e);
		}

	}

    /**
     * [UC0811] Processar Requisições do Dispositivo Móvel Impressao Simultanea.
     *
     * Método que baixa a nova versão do JAD do mobile para o celular
     *
     * @author Bruno Barros
     * @date 08/06/2010
     *
     * @param
     * @throws IOException
     */
    public byte[] baixarNovaVersaoJad() throws ControladorException {
        try {
            return this.repositorioCadastro
                .baixarNovaVersaoJad();
        } catch (ErroRepositorioException e) {
            sessionContext.setRollbackOnly();
            throw new ControladorException("erro.sistema", e);
        }

    }

    /**
     * [UC0811] Processar Requisições do Dispositivo Móvel Impressao Simultanea.
     *
     * Método que baixa a nova versão do JAR do mobile para o celular
     *
     * @author Bruno Barros
     * @date 08/06/2010
     *
     * @param
     * @throws IOException
     */
    public byte[] baixarNovaVersaoJar() throws ControladorException {
        try {
            return this.repositorioCadastro.baixarNovaVersaoJar();
        } catch (ErroRepositorioException e) {
            sessionContext.setRollbackOnly();
            throw new ControladorException("erro.sistema", e);
        }

    }

    /**
     *
     * @author Fernando Fontelles
     * @date 07/07/2010
     *
     * @param idImovel
     * @return
     * @throws ControladorException
     */
    public boolean verificarSituacaoImovelCobrancaJudicial(Integer idImovel) throws ControladorException{

    	try {

            return this.repositorioCadastro.verificarSituacaoImovelCobrancaJudicial(idImovel);

        } catch (ErroRepositorioException e) {

            sessionContext.setRollbackOnly();
            throw new ControladorException("erro.sistema", e);

        }

    }

    /**
     *
     * @author Fernando Fontelles
     * @date 07/07/2010
     *
     * @param idImovel
     * @return
     * @throws ControladorException
     */
    public boolean verificarSituacaoImovelNegativacao( Integer idImovel ) throws ControladorException{

    	try {

            return this.repositorioCadastro.verificarSituacaoImovelNegativacao(idImovel);

        } catch (ErroRepositorioException e) {

            sessionContext.setRollbackOnly();
            throw new ControladorException("erro.sistema", e);

        }

    }

    /**
     *
     * [UC1036] - Inserir Cadastro de Email do Cliente
     *
     * @author Fernando Fontelles
     * @date 09/07/2010
     *
     * @param idCliente
     * @param nomeClienteAnterior
     * @param cpfAnterior
     * @param cnpjAnterior
     * @param emailAnterior
     * @param nomeSolicitante
     * @param cpfSolicitante
     * @param nomeClienteAtual
     * @param cpfClienteAtual
     * @param cnpjClienteAtual
     * @param emailAtual
     * @return
     */
    public Integer inserirCadastroEmailCliente( Integer idCliente, String nomeClienteAnterior,
     		String cpfAnterior, String cnpjAnterior, String emailAnterior, String nomeSolicitante,
     		String cpfSolicitante, String nomeClienteAtual, String cpfClienteAtual,
 			String cnpjClienteAtual, String emailAtual) throws ControladorException{

 		    	try {

 		            return this.repositorioCadastro.inserirCadastroEmailCliente( idCliente, nomeClienteAnterior,
 		            		cpfAnterior, cnpjAnterior, emailAnterior, nomeSolicitante, cpfSolicitante,
 		            		nomeClienteAtual, cpfClienteAtual, cnpjClienteAtual, emailAtual);

 		        } catch (ErroRepositorioException e) {

 		            sessionContext.setRollbackOnly();
 		            throw new ControladorException("erro.sistema", e);

 		        }

 	}

    /**
     * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
     *
     * @author Hugo Amorim
     * @date 08/09/2010
     */
 	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesSistemaColuna(GerarRelatorioAlteracoesSistemaColunaHelper helper)
 		throws ControladorException{

 		Collection<Object[]> retorno = null;

 		try {

	    	//POR USUARIO
 			if(helper.getTipoRelatorio().equals("1")){
 				retorno = this.repositorioCadastro.pesquisarDadosRelatorioAlteracoesSistemaColunaPorUsuario(helper);
 			}
 			//POR LOCALIDADE
 			else if(helper.getTipoRelatorio().equals("2")){
 				retorno = this.repositorioCadastro.pesquisarDadosRelatorioAlteracoesSistemaColunaPorLocalidade(helper);
 			}

	    } catch (ErroRepositorioException e) {
	        throw new ControladorException("erro.sistema", e);
	    }

	    return retorno;
 	}

 	/**
     * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
     *
     * [FS0007]
     *
     * @author Hugo Amorim
     * @date 08/09/2010
     */
 	public boolean verificarRelacaoColuna(Integer idColuna) throws ControladorException {
		try {

			return this.repositorioCadastro.verificarRelacaoColuna(idColuna);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
 	}

 	/**
     * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
     *
     * @author Daniel Alves
     * @date 28/09/2010
     * Consulta do Relatório Analítico
     */
 	public Collection pesquisarDadosRelatorioAtualizacaoCadastralViaInternet(GerarRelatorioAtualizacaoCadastralViaInternetHelper filtro)
 		throws ControladorException{

 			try {
 				return this.repositorioCadastro
 						.pesquisarDadosRelatorioAtualizacaoCadastralViaInternet(filtro);
 			} catch (ErroRepositorioException e) {
 				throw new ControladorException("erro.sistema", e);
 			}

	}

 	/**
     * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
     *
     * @author Daniel Alves
     * @date 28/09/2010
     * Consulta do Relatório Resumo
     */
 	public Collection pesquisarDadosRelatorioResumoAtualizacaoCadastralViaInternet(GerarRelatorioAtualizacaoCadastralViaInternetHelper filtro)
 		throws ControladorException{

 			try {
 				return this.repositorioCadastro
 						.pesquisarDadosRelatorioResumoAtualizacaoCadastralViaInternet(filtro);
 			} catch (ErroRepositorioException e) {
 				throw new ControladorException("erro.sistema", e);
 			}

	}

 	/**
     * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
     *
     * @author Hugo Amorim de Lyra
     * @date 06/10/2010
     */
 	public Integer countRelatorioAtualizacaoCadastralViaInternet(GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
 		throws ControladorException{
 		try {
				return this.repositorioCadastro
						.countRelatorioAtualizacaoCadastralViaInternet(helper);
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}
 	}


	/**
	 *
	 * [UC0113] - Faturar Grupo de Faturamento
	 *
	 * @author Rômulo Aurélio
	 * @date 28/09/2010
	 *
	 * @return
	 */
	public ClienteImovel pesquisarClienteResponsavelComEsferaPoderPublico(
			Integer idImovel) throws ControladorException {

		try {

			return this.repositorioCadastro
					.pesquisarClienteResponsavelComEsferaPoderPublico(idImovel);

		} catch (ErroRepositorioException e) {

			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);

		}

	}

	/**
	 * @autor: Adriana Muniz
	 * @date: 23/11/2011
	 *
	 * Pesquisa todas as esfera de poder ativas.
	 *
	 * Manter Contas de um Conjunto de Imóveis.
	 *
	 * @return Collection
	 * @throws ControladorException
	 *
	 */
	public Collection<EsferaPoder> pesquisarEsferaPoder() throws ControladorException {
		try{
			Collection<EsferaPoder> colecaoEsferas = new ArrayList<EsferaPoder>();
			Collection colecao = repositorioCadastro.pesquisarEsferaPoder();

			if(colecao != null && !colecao.isEmpty()) {
				Iterator colecaoIterator = colecao.iterator();

				while(colecaoIterator.hasNext()) {
					EsferaPoder esferaPoder = (EsferaPoder) colecaoIterator.next();
					colecaoEsferas.add(esferaPoder);
				}
			}

			return colecaoEsferas;
		}catch(ErroRepositorioException e){
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1121] Gerar Relatório de Imóveis com Alteração de Inscrição Via Batch
	 *
	 * @author Hugo Leonardo
	 * @date 19/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection<ImovelInscricaoAlterada> pesquisarRelatorioImoveisAlteracaoInscricaoViaBatch(
			FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper) throws ControladorException{

		try {

			return this.repositorioCadastro.pesquisarRelatorioImoveisAlteracaoInscricaoViaBatch(relatorioHelper);

        } catch (ErroRepositorioException ex) {
            ex.printStackTrace();
            throw new ControladorException("erro.sistema", ex);
        }

	}

	/**
	 * [UC1121] Gerar Relatório de Imóveis com Alteração de Inscrição Via Batch
	 *
	 * @author Hugo Leonardo
	 * @date 19/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Integer countTotalRelatorioImoveisAlteracaoInscricaoViaBatch(
			FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper) throws ControladorException{

		try {

			return this.repositorioCadastro.countTotalRelatorioImoveisAlteracaoInscricaoViaBatch(relatorioHelper);

        } catch (ErroRepositorioException ex) {
            ex.printStackTrace();
            throw new ControladorException("erro.sistema", ex);
        }

	}

    /**
     * [UC1124] Gerar Relatório de Alterações de CPF/CNPJ
     *
     * @author Mariana Victor
     * @date 16/02/2011
     */
 	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpj(GerarRelatorioAlteracoesCpfCnpjHelper helper)
 		throws ControladorException {

 		Collection<Object[]> retorno = null;

 		try {

	    	//POR USUARIO
 			if(helper.getTipoRelatorio().equals("1")){
 				retorno = this.repositorioCadastro.pesquisarDadosRelatorioAlteracoesCpfCnpjPorUsuario(helper);
 			}
 			//POR LOCALIDADE
 			else if(helper.getTipoRelatorio().equals("2")){
 				retorno = this.repositorioCadastro.pesquisarDadosRelatorioAlteracoesCpfCnpjPorLocalidade(helper);
 			}
 			//POR MEIO
 			else if(helper.getTipoRelatorio().equals("3")){
 				retorno = this.repositorioCadastro.pesquisarDadosRelatorioAlteracoesCpfCnpjPorMeio(helper);
 			}

	    } catch (ErroRepositorioException e) {
	        throw new ControladorException("erro.sistema", e);
	    }

	    return retorno;
 	}

	/**
	 * Solicitar Conta em Braile.
	 *
	 * [UC1128] Solicitar Conta Braile
	 *
	 * @author Hugo Leonardo
	 * @date 04/03/2011
	 *
	 */
    public Integer inserirSolicitacaoContaBraile(ContaBraileHelper contaBraileHelper)
    	throws ControladorException{

    	Integer idRA = null;

    	try {

    		ContaBraile contaBraile = null;

	    	contaBraile = this.montarContaBraile(contaBraile, contaBraileHelper);

			Integer idContaBraile = (Integer) this.getControladorUtil().inserir(contaBraile);

			idRA = this.montarRA(contaBraile,contaBraileHelper.getProtocoloAtendimento());

			FiltroContaBraile filtroContaBraile = new FiltroContaBraile();
			filtroContaBraile.adicionarParametro(new ParametroSimples(FiltroContaBraile.ID, idContaBraile));

			Collection colecaoContaBraile = this.getControladorUtil().pesquisar(filtroContaBraile, ContaBraile.class.getName());

			ContaBraile contaBraileAtu = (ContaBraile) Util.retonarObjetoDeColecao(colecaoContaBraile);

			RegistroAtendimento rA = new RegistroAtendimento();
			rA.setId(idRA);
			contaBraileAtu.setRegistroAtendimento(rA);
			contaBraileAtu.setUltimaAlteracao(new Date());

			this.getControladorUtil().atualizar(contaBraileAtu);

        } catch (ControladorException e) {

        	sessionContext.setRollbackOnly();

        	String p = "";
			List<String> parametros = e.getParametroMensagem();
			if(parametros != null && !parametros.isEmpty()){
				p = parametros.get(0);
			}

        	throw new ControladorException(e.getMessage(), null, p);
        }

        return idRA;
 	}

	/**
	 * Solicitar Conta em Braile.
	 *
	 * [UC1128] Solicitar Conta Braile
	 *
	 * @author Hugo Leonardo
	 * @date 04/03/2011
	 *
	 */
	private ContaBraile montarContaBraile(ContaBraile contaBraile,
			ContaBraileHelper contaBraileHelper) throws ControladorException{

		// Preparar dados para armazenar na tabela
		String idImovel = "";
		if(contaBraileHelper.getMatricula() != null && !contaBraileHelper.getMatricula().equals("")){

			idImovel = contaBraileHelper.getMatricula().trim();
		}else{

			throw new ControladorException("atencao.informe.matricula_imovel");
		}

		Imovel imovel = new Imovel();
		imovel.setId( new Integer(idImovel));

		FiltroContaBraile filtroContaBraile = new FiltroContaBraile();
		filtroContaBraile.adicionarParametro(new ParametroSimples(FiltroContaBraile.IMOVEL_ID, imovel.getId()));

		Collection colecaoContaBraile = Fachada.getInstancia().pesquisar(filtroContaBraile, ContaBraile.class.getName());

		if(!Util.isVazioOrNulo(colecaoContaBraile)){

			ContaBraile contaBraileAtu = (ContaBraile) Util.retonarObjetoDeColecao(colecaoContaBraile);

			if(contaBraileAtu != null){

				throw new ControladorException("atencao.solicitacao_conta_braile.existente", null, imovel.getId().toString());
			}
		}

		String nomeCliente = "";
		if(contaBraileHelper.getNomeCliente() != null && !contaBraileHelper.getNomeCliente().equals("")){

			nomeCliente = contaBraileHelper.getNomeCliente().toUpperCase();
		}else{

			throw new ControladorException("atencao.necessario.confirmar.nome.cliente");
		}

		String cpfCliente = "";
		String cnpjCliente = "";
		if ( contaBraileHelper.isIndicadorCpf() ){

			cpfCliente = contaBraileHelper.getCpfCnpjCliente();
		}
		else if ( contaBraileHelper.isIndicadorCnpj() ){

			cnpjCliente = contaBraileHelper.getCpfCnpjCliente();
		}

		String email = contaBraileHelper.getEmail();

		String nomeSolicitante = "";
		if(contaBraileHelper.getNomeSolicitante() != null && !contaBraileHelper.getNomeSolicitante().equals("")){

			nomeSolicitante = contaBraileHelper.getNomeSolicitante().toUpperCase();
		}else{

			throw new ControladorException("atencao.informar_nome_solicitante");
		}

		String cpfSolicitante = contaBraileHelper.getCpfSolicitante();

		String telefone = "";
		if ( contaBraileHelper.getTelefoneContato() != null
				&& !contaBraileHelper.getTelefoneContato().equals("") ){

			telefone = contaBraileHelper.getTelefoneContato();
		}

		String rg = "";
		OrgaoExpedidorRg orgaoExpedidorRg = null;
		UnidadeFederacao unidadeFederacao = null;
		if(contaBraileHelper.getRg() != null && !contaBraileHelper.getRg().equals("") &&
				contaBraileHelper.getOrgaoExpeditor() != null && !contaBraileHelper.getOrgaoExpeditor().equals("-1") &&
				contaBraileHelper.getUnidadeFederacao() != null && !contaBraileHelper.getUnidadeFederacao().equals("-1")){

			rg = contaBraileHelper.getRg();

			orgaoExpedidorRg = new OrgaoExpedidorRg();
			orgaoExpedidorRg.setId( new Integer(contaBraileHelper.getOrgaoExpeditor()));

			unidadeFederacao = new UnidadeFederacao();
			unidadeFederacao.setId( new Integer(contaBraileHelper.getUnidadeFederacao()));

		}else{

			throw new ControladorException("atencao.rg_campos_relacionados.nao_preenchidos");
		}

		contaBraile = new ContaBraile(
				imovel,
				nomeCliente,
				cpfCliente,
				cnpjCliente,
				email,
				nomeSolicitante,
				cpfSolicitante,
				rg,
				orgaoExpedidorRg,
				unidadeFederacao,
				new Date() );

		if(!telefone.equals("")){

			contaBraile.setTelefoneContato(telefone);
		}

		contaBraile.setUltimaAlteracao( new Date());

		return contaBraile;

	}

	/**
	 * Solicitar Conta em Braile.
	 *
	 * [UC1128] Solicitar Conta Braile
	 *
	 * @author Hugo Leonardo
	 * @date 04/03/2011
	 *
	 */
	private Integer montarRA(ContaBraile contaBraile,String protocoloAtendimento) throws ControladorException{

		Integer idMeioSolicitacao = MeioSolicitacao.INTERNET;
		Integer idSolicitacaoTipo = new Integer("1"); //ALTERACAO CADASTRAL

		FiltroSolicitacaoTipoEspecificacao filtro = new FiltroSolicitacaoTipoEspecificacao();
		filtro.adicionarParametro(
			new ParametroSimples(
				FiltroSolicitacaoTipoEspecificacao.CODIGO_CONSTANTE, 1));

		Collection colecaoSolTipEspec =
			this.getControladorUtil().pesquisar(filtro, SolicitacaoTipoEspecificacao.class.getName());

		SolicitacaoTipoEspecificacao solTipEspec = (SolicitacaoTipoEspecificacao) Util.retonarObjetoDeColecao(colecaoSolTipEspec);

		if(solTipEspec == null){
			throw new ControladorException("atencao.inexistente.solicitacao_tipo_especificacao.conta_braile");
		}

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples( FiltroUnidadeOrganizacional.CODIGO_CONSTANTE, new Short("1")));
		Collection colecaoUnidadeOrganizacional = this.getControladorUtil().pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

		UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);

		if(unidadeOrganizacional == null){

			throw new ControladorException("pesquisa.unidade_organizacional_internet.inexistente");
		}

		Integer idSolicitacaoTipoEspecificacao = solTipEspec.getId(); // CONTA BRAILE

		Integer idUnidadeAtendimento = unidadeOrganizacional.getId();

		DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper definirDataPrevistaUnidadeDestinoEspecificacaoHelper =
			this.getControladorRegistroAtendimento().definirDataPrevistaUnidadeDestinoEspecificacao(new Date(),
					idSolicitacaoTipoEspecificacao);

		FiltroOrgaoExpedidorRg filtroOrgaoExpedidor = new FiltroOrgaoExpedidorRg();

		filtroOrgaoExpedidor.adicionarParametro(new ParametroSimples(
				FiltroOrgaoExpedidorRg.ID, contaBraile.getOrgaoExpeditor().getId()));

		Collection orgaosExpedidores = this.getControladorUtil().pesquisar(
				filtroOrgaoExpedidor, OrgaoExpedidorRg.class.getName());

		OrgaoExpedidorRg orgaoExpedidorRg = (OrgaoExpedidorRg) Util.retonarObjetoDeColecao(orgaosExpedidores);

		FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao();

		filtroUnidadeFederacao.adicionarParametro(new ParametroSimples(
				FiltroUnidadeFederacao.ID, contaBraile.getUnidadeFederacao().getId()));

		Collection unidadesFederacao = this.getControladorUtil().pesquisar(
				filtroUnidadeFederacao, UnidadeFederacao.class.getName());

		UnidadeFederacao unidadeFederacao = (UnidadeFederacao) Util.retonarObjetoDeColecao(unidadesFederacao);

		String observacao = contaBraile.getCpfSolicitante() + ";"
						  + contaBraile.getRg() + ";"
						  + orgaoExpedidorRg.getDescricaoAbreviada() + ";"
						  + unidadeFederacao.getSigla() + ";"
						  + contaBraile.getTelefoneContato() + ";"
						  + contaBraile.getEmail();

		String parecer = "Tramite automático da internet.";

		String nomeSolicitante = contaBraile.getNomeSolicitante();

		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, contaBraile.getImovel().getId()));
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(
				FiltroImovel.LOCALIDADE);
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(
				FiltroImovel.SETOR_COMERCIAL);
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(
				FiltroImovel.QUADRA);
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(
				FiltroImovel.PAVIMENTO_RUA);
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(
				FiltroImovel.PAVIMENTO_CALCADA);

		Collection colecaoImovel = this.getControladorUtil().pesquisar(filtroImovel, Imovel.class.getName());

		Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);

		Collection colecaoEnderecos = new ArrayList();
		Imovel imovelEndereco = this.getControladorEndereco().pesquisarImovelParaEndereco(imovel.getId());
		colecaoEnderecos.add(imovelEndereco);

		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.INDICADOR_USUARIO_INTERNET, new Integer("1")));
		Collection colecaoUsuario = this.getControladorUtil().pesquisar(filtroUsuario, Usuario.class.getName());

		Usuario  usuarioLogado = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);

		if(usuarioLogado == null){

			throw new ControladorException("pesquisa.usuario_internet.inexistente");
		}

		// Endereço
		FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();

		if(!contaBraile.getCpfCliente().equals("")){

			filtroClienteEndereco.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.CPF,
					contaBraile.getCpfCliente()));

		}else if(!contaBraile.getCnpjCliente().equals("")){

			filtroClienteEndereco.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.CNPJ,
					contaBraile.getCnpjCliente()));
		}

		Collection colecaoEndereco = null;

		if(!contaBraile.getCpfCliente().equals("") || !contaBraile.getCnpjCliente().equals("")){

			Collection colecaoClienteEndereco = this.getControladorUtil().pesquisar(filtroClienteEndereco, ClienteEndereco.class.getName());

			ClienteEndereco clienteEndereco = (ClienteEndereco) Util.retonarObjetoDeColecao(colecaoClienteEndereco);

			colecaoEndereco = new ArrayList();
			colecaoEndereco.add(clienteEndereco);
		}

		RADadosGeraisHelper raDadosGerais = RABuilder.buildRADadosGerais(new Short("1"), idMeioSolicitacao, idSolicitacaoTipoEspecificacao,
																				Util.formatarData(definirDataPrevistaUnidadeDestinoEspecificacaoHelper.getDataPrevista()),
																				observacao, idSolicitacaoTipoEspecificacao,
																				idUnidadeAtendimento, usuarioLogado, protocoloAtendimento);
		RALocalOcorrenciaHelper raLocalOcorrencia = RABuilder.buildRALocalOcorrencia(contaBraile, colecaoEndereco, idUnidadeAtendimento, parecer,
																					ConstantesSistema.NAO);
		RASolicitanteHelper raSolicitante = RABuilder.buildRASolicitante(nomeSolicitante, false, colecaoEndereco);

		Integer[] idRA = this.getControladorRegistroAtendimento().inserirRegistroAtendimento(raDadosGerais, raLocalOcorrencia, raSolicitante);

		return idRA[0];

	}

	/**
	 * UC1162 – AUTORIZAR ALTERACAO INSCRICAO IMOVEL
	 * @author Rodrigo Cabral
	 * @date 05/06/2011
	 */
	public Collection pesquisaImovelInscricaoAlterada(ImovelInscricaoAlteradaHelper helper)throws ControladorException {
		try {

			Collection colecaoRetorno = null;
			Collection colecaoImovelInscricaoAlterada = repositorioCadastro.pesquisaImovelInscricaoAlterada(helper);
			Integer totalImoveis = null;
			Integer idQuadra = null;
			Integer indicadorAutorizar = null;

			if(colecaoImovelInscricaoAlterada != null && !colecaoImovelInscricaoAlterada.isEmpty()){

				Iterator iterImovelInscricaoAlterada = colecaoImovelInscricaoAlterada.iterator();
				colecaoRetorno = new ArrayList();
				ImovelInscricaoAlteradaHelper retorno = null;
				while (iterImovelInscricaoAlterada.hasNext()) {

					Object[] imovelIA = (Object[]) iterImovelInscricaoAlterada.next();

					totalImoveis = (Integer) imovelIA[0];
					idQuadra = (Integer) imovelIA[1];
					indicadorAutorizar = 0;


					FiltroQuadra filtro = new FiltroQuadra();
					filtro.adicionarParametro(new ParametroSimples(
							FiltroQuadra.ID, idQuadra));

					Collection colecaoQuadra = this.getControladorUtil().pesquisar(filtro,
							Quadra.class.getName());

					Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);



					retorno = new ImovelInscricaoAlteradaHelper(
							indicadorAutorizar, totalImoveis, idQuadra);

					retorno.setNumeroQuadra(quadra.getNumeroQuadra());

					colecaoRetorno.add(retorno);

				}
			}
			return colecaoRetorno;
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}



	/**
     * [UC1160] Processar Comando Gerado Carta Tarifa Social
     *
     * @author: Vivianne Sousa
     * @date: 24/03/2011
     */
    public void processarComandoGerado(Integer idLocalidade , Integer idFuncionalidadeIniciada,
    		TarifaSocialComandoCarta tarifaSocialComandoCarta)throws ControladorException{

        int idUnidadeIniciada = 0;

        try {
	        /*
	         * Registrar o início do processamento da Unidade de Processamento do Batch
	        */
            idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.LOCALIDADE,(idLocalidade));

            if(tarifaSocialComandoCarta.getDataProcessamento() == null){
            	//[SB0008]-Verificar carta para o comando
            	verificarCartaParaComando(idLocalidade,tarifaSocialComandoCarta);

            	Integer idGerencia = null;
                if(tarifaSocialComandoCarta.getGerenciaRegional() != null && tarifaSocialComandoCarta.getGerenciaRegional().getId() != null){
                	idGerencia = tarifaSocialComandoCarta.getGerenciaRegional().getId();
                }

                Integer idUnidade = null;
                if(tarifaSocialComandoCarta.getUnidadeNegocio() != null && tarifaSocialComandoCarta.getUnidadeNegocio().getId() != null){
                	idUnidade = tarifaSocialComandoCarta.getUnidadeNegocio().getId();
                }

                Collection colecaoImoveis = null;

    			if(tarifaSocialComandoCarta.getCodigoTipoCarta().equals(new Integer(1))){
    				//CADASTRO
    				colecaoImoveis = getControladorImovel().consultarImovelCadastro(idLocalidade,idGerencia,idUnidade,
    				tarifaSocialComandoCarta.getAnoMesInicialImplantacao(),tarifaSocialComandoCarta.getAnoMesFinalImplantacao());
    				if(colecaoImoveis != null && !colecaoImoveis.isEmpty()){
    					Iterator iterImovel = colecaoImoveis.iterator();
    					while (iterImovel.hasNext()) {
    						Imovel imovel = (Imovel) iterImovel.next();
    						//[SB0002]–Verifica Critério Recadastramento

    						Integer criterio = verificaCriterioRecadastramento(imovel,tarifaSocialComandoCarta);
    						if(criterio != null){

    							if(verificaValidadeCarta(imovel,tarifaSocialComandoCarta)){
    								//[SB0005]–Gera Cartas Tarifa Social
    								gerarCartasTarifaSocial(imovel, tarifaSocialComandoCarta,criterio,null);

    							}
    						}
    					}
    				}

    			}else{
    				//COBRANÇA
    				colecaoImoveis = getControladorImovel().consultarImovel(idLocalidade,idGerencia,idUnidade);

    				if(colecaoImoveis != null && !colecaoImoveis.isEmpty()){
    					Iterator iterImovel = colecaoImoveis.iterator();
    					while (iterImovel.hasNext()) {
    						Imovel imovel = (Imovel) iterImovel.next();

    						//[SB0003]–Verifica Critério Cobrança
    						Collection colecaoContas = verificaCriterioCobranca(imovel,tarifaSocialComandoCarta);
    						if(colecaoContas != null){

    							if(verificaValidadeCarta(imovel,tarifaSocialComandoCarta)){
    								//[SB0005]–Gera Cartas Tarifa Social
    								gerarCartasTarifaSocial(imovel,tarifaSocialComandoCarta,null,colecaoContas);
    							}

    						}
    					}
    				}
    			}

    			//O sistema atualiza a data de processamento e quantidade de imóveis do comando processado
    			//na tabela TAR_SOCIAL_COMANDO_CARTA com TSCC_ID = TSCC_ID recebido
    			Integer qtdeImoveis = getControladorImovel().pesquisarQuantidadeImoveisTarifaSocialCarta(tarifaSocialComandoCarta.getId());
    			getControladorImovel().atualizarTarifaSocialComandoCarta(tarifaSocialComandoCarta.getId(),qtdeImoveis);

            }

            getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);

        } catch (Exception ex) {
            ex.printStackTrace();
            getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,idUnidadeIniciada, true);
            throw new EJBException(ex);
        }

    }

	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social
	 *
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 *
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorGerencia(Integer idGerenciaRegional)throws ControladorException{

		try {

			return this.repositorioCadastro.pesquisarLocalidadesPorGerencia(idGerenciaRegional);

        } catch (ErroRepositorioException ex) {
            ex.printStackTrace();
            throw new ControladorException("erro.sistema", ex);
        }

	}

	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social
	 *
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 *
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorUnidadeNegocio(Integer idUnidadeNegocio)throws ControladorException{

		try {

			return this.repositorioCadastro.pesquisarLocalidadesPorUnidadeNegocio(idUnidadeNegocio);

        } catch (ErroRepositorioException ex) {
            ex.printStackTrace();
            throw new ControladorException("erro.sistema", ex);
        }

	}

	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social
	 *
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 *
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidade()throws ControladorException{

		try {

			return this.repositorioCadastro.pesquisarLocalidade();

        } catch (ErroRepositorioException ex) {
            ex.printStackTrace();
            throw new ControladorException("erro.sistema", ex);
        }

	}


	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social
	 * [SB0002]–Verifica Critério Recadastramento
	 *
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 *
	 * @throws ControladorException
	 */
	public Integer verificaCriterioRecadastramento(Imovel imovel, TarifaSocialComandoCarta tscc)throws ControladorException{

		Integer codigoCriterio = null;

		Cliente clienteUsuario = getControladorCliente().pesquisarClienteUsuarioDoImovel(imovel.getId());

		//Caso  TSCC_ICCRITERIOCPF = 1
		if(tscc.getIndicadorCriterioCpf().equals(ConstantesSistema.SIM)){
			if(clienteUsuario.getCpf() == null){
				return 1;
			}
		}
		//Caso  TSCC_ICCRITERIOIDENTIDADE = 1
		if(tscc.getIndicadorCriterioIdentidade().equals(ConstantesSistema.SIM)){
			if(clienteUsuario.getRg() == null){
				return 2;
			}
		}
		//Caso  TSCC_ICCRITERIOCONTRATOENERGIA = 1
		if(tscc.getIndicadorCriterioContratoEnergia().equals(ConstantesSistema.SIM)){
			if(imovel.getQuantidadeEconomias().equals(new Short("1"))){
				if(imovel.getNumeroCelpe() == null){
					return 3;
				}
			}else if(imovel.getQuantidadeEconomias().compareTo(new Short("1")) == 1){
				Collection colecaoImovelEconomia = getControladorImovel().pesquisarImovelEconomia(imovel.getId());
				if(colecaoImovelEconomia == null || colecaoImovelEconomia.isEmpty()){
					return 3;
				}
			}
		}
		TarifaSocialDadoEconomia tarifaSocialDadoEconomia = getControladorImovel().pesquisarTarifaSocialDadoEconomia(imovel.getId());
		//Caso  TSCC_ICCRITERIODADOSENERGIA = 1
		if(tscc.getIndicadorCriterioDadosEnergia().equals(ConstantesSistema.SIM)){
			if(tarifaSocialDadoEconomia != null &&
			tarifaSocialDadoEconomia.getTarifaSocialRevisaoMotivo() != null &&
			tarifaSocialDadoEconomia.getTarifaSocialRevisaoMotivo().getId().equals(new Integer(46))){
				return 4;
			}
		}
		//Caso  TSCC_ICCRITERIOPROGRAMASOCIAL = 1
		if(tscc.getIndicadorCriterioProgramaSocial().equals(ConstantesSistema.SIM)){
			if(tarifaSocialDadoEconomia != null &&
			tarifaSocialDadoEconomia.getTarifaSocialCartaoTipo() != null &&
			tarifaSocialDadoEconomia.getTarifaSocialCartaoTipo().getDescricaoAbreviada().equals("BF")){
				return 5;
			}
		}
		//Caso  TSCC_ICCRITERIOSEGDESEMPREGO = 1
		if(tscc.getIndicadorCriterioSeguroDesemprego().equals(ConstantesSistema.SIM)){
			if(tarifaSocialDadoEconomia != null &&
				tarifaSocialDadoEconomia.getTarifaSocialCartaoTipo() != null &&
				tarifaSocialDadoEconomia.getTarifaSocialCartaoTipo().getId().equals(new Integer(49)) &&
				tarifaSocialDadoEconomia.getDataValidadeCartao() != null &&
				tarifaSocialDadoEconomia.getDataValidadeCartao().compareTo(new Date()) == -1){
				return 6;
			}
		}
		//Caso  TSCC_ICCRITERIORENDACOMPROVAD = 1
		if(tscc.getIndicadorCriterioRendaComprovada().equals(ConstantesSistema.SIM)){
			if(tarifaSocialDadoEconomia != null &&
					tarifaSocialDadoEconomia.getRendaTipo() != null &&
					tarifaSocialDadoEconomia.getRendaTipo().getId().equals(new Integer(1))){
				return 7;
			}
		}
		//Caso  TSCC_ICCRITERIORENDADECLARADA = 1
		if(tscc.getIndicadorCriterioRendaDeclarada().equals(ConstantesSistema.SIM)){
			if(tarifaSocialDadoEconomia != null &&
					tarifaSocialDadoEconomia.getRendaTipo() != null &&
					tarifaSocialDadoEconomia.getRendaTipo().getId().equals(new Integer(2))){
				return 8;
			}
		}
		//Caso  TSCC_ICCRITERIOQTECONOMIA = 1
		if(tscc.getIndicadorCriterioQtdeEconomia().equals(ConstantesSistema.SIM)){
			if(imovel.getQuantidadeEconomias().compareTo(new Short("1")) == 1){
				return 9;
			}
		}
		//Caso  TSCC_ICCRITERIORECADASTRAMENTO = 1
		if(tscc.getIndicadorCriterioRecadastramento().equals(ConstantesSistema.SIM)
			&& tarifaSocialDadoEconomia != null ){
			Date dataRecadastramento = tarifaSocialDadoEconomia.getDataRecadastramento();

			if(dataRecadastramento != null){
				dataRecadastramento = Util.somarNumeroAnosDeUmaData(dataRecadastramento,2);

				if(dataRecadastramento.compareTo(new Date()) == -1){
					return 10;
				}
			}
		}

		return codigoCriterio;
	}

	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social
	 * [SB0004]-Verifica Validade Carta
	 *
	 * @author Vivianne Sousa
	 * @date 25/03/2011
	 *
	 * @throws ControladorException
	 */
	public boolean verificaValidadeCarta(Imovel imovel, TarifaSocialComandoCarta tscc)throws ControladorException{

		boolean retorno = true;
		TarifaSocialCarta tarifaSocialCarta = getControladorImovel().pesquisarTarifaSocialCarta(imovel.getId(),tscc.getCodigoTipoCarta());

		if(tarifaSocialCarta != null && tarifaSocialCarta.getTarifaSocialComandoCarta() != null){

			Date dataGeracao = tarifaSocialCarta.getTarifaSocialComandoCarta().getDataGeracao();
			Integer qtdeDiasComparecimento = tarifaSocialCarta.getTarifaSocialComandoCarta().getQuantidadeDiasComparecimento();

			if((Util.adicionarNumeroDiasDeUmaData(dataGeracao,qtdeDiasComparecimento)).compareTo(new Date()) == -1 ){
				retorno = false;
			}
		}

		return retorno;
	}

	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social
	 * [SB0003]–Verifica Critério Cobrança
	 *
	 * @author Vivianne Sousa
	 * @date 28/03/2011
	 *
	 * @throws ControladorException
	 */
	public Collection verificaCriterioCobranca(Imovel imovel, TarifaSocialComandoCarta tscc)throws ControladorException{

		Collection retorno = null;

		Date dataVencimentoInicial = Util.criarData(1, 1, 0001);
		Date dataVencimentoFinal = Util.subtrairNumeroDiasDeUmaData(new Date(),tscc.getQuantidadeDiasDebitoVencimento().intValue());
			//Util.criarData(31, 12, 9999);

		// [UC0067] Obter Débito do Imóvel ou Cliente
		ObterDebitoImovelOuClienteHelper imovelDebitoCredito = Fachada.getInstancia()
				.obterDebitoImovelOuCliente(1, // indicadorDebito
						imovel.getId().toString(), // idImovel
						null, // codigoCliente
						null, // clienteRelacaoTipo
						"000101", // anoMesInicialReferenciaDebito
						"999912", // anoMesFinalReferenciaDebito
						dataVencimentoInicial, // anoMesInicialVencimentoDebito
						dataVencimentoFinal, // anoMesFinalVencimentoDebito
						1, // indicadorPagamento
						1, // indicadorConta
						2, // indicadorDebitoACobrar
						2, // indicadorCreditoARealizar
						2, // indicadorNotasPromissorias
						2, // indicadorGuiasPagamento
						2, // indicadorCalcularAcrescimoImpontualidade
						true);// indicadorContas

		// CONTA
		if (imovelDebitoCredito.getColecaoContasValoresImovel() != null
				&& !imovelDebitoCredito.getColecaoContasValoresImovel().isEmpty()) {
			return imovelDebitoCredito.getColecaoContasValoresImovel();
		}
		return retorno;
	}


	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social
	 * [SB0005]–Gera Cartas Tarifa Social
	 *
	 * @author Vivianne Sousa
	 * @date 28/03/2011
	 *
	 * @throws ControladorException
	 */
	public void gerarCartasTarifaSocial(Imovel imovel, TarifaSocialComandoCarta tscc,
			Integer criterio,Collection colecaoContas)throws ControladorException{

		try{

			TarifaSocialCarta tarifaSocialCarta = new TarifaSocialCarta();

			TarifaSocialCartaPK tarifaSocialCartaPK = new TarifaSocialCartaPK();
			Cliente clienteUsuario = getControladorCliente().pesquisarClienteUsuarioDoImovel(imovel.getId());
			tarifaSocialCartaPK.setClienteId(clienteUsuario.getId());
			tarifaSocialCartaPK.setTarifaSocialComandoCartaID(tscc.getId());
			tarifaSocialCartaPK.setImovelId(imovel.getId());
			tarifaSocialCarta.setComp_id(tarifaSocialCartaPK);

			tarifaSocialCarta.setImovel(imovel);
			tarifaSocialCarta.setTarifaSocialComandoCarta(tscc);
			tarifaSocialCarta.setCliente(clienteUsuario);
			tarifaSocialCarta.setIndicadorExcluidoTarifaSocial(ConstantesSistema.NAO);

			tarifaSocialCarta.setLocalidade(imovel.getLocalidade());
			tarifaSocialCarta.setGerenciaRegional(imovel.getLocalidade().getGerenciaRegional());
			tarifaSocialCarta.setUnidadeNegocio(imovel.getLocalidade().getUnidadeNegocio());

			if(tscc.getCodigoTipoCarta().equals(new Integer(2))){
				//correspondente a ‘FATURAS VENCIDAS’
				tarifaSocialCarta.setCodigoMotivo(new Integer(11));

				//[SB0006]–Gera Dados Débito da Carta
				gerardadosDebitoCarta(imovel,tscc,colecaoContas);

			}else{
				TarifaSocialMotivoCarta tsmc =  repositorioCadastro.pesquisarTarifaSocialMotivoCarta(criterio);
				tarifaSocialCarta.setCodigoMotivo(tsmc.getId());
			}
			tarifaSocialCarta.setUltimaAlteracao(new Date());
			getControladorUtil().inserir(tarifaSocialCarta);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}


	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social
	 * [SB0006]–Gera Dados Débito da Carta
	 *
	 * @author Vivianne Sousa
	 * @date 28/03/2011
	 *
	 * @throws ControladorException
	 */
	public void gerardadosDebitoCarta(Imovel imovel, TarifaSocialComandoCarta tscc,Collection colecaoContas)throws ControladorException{

		if(colecaoContas != null && !colecaoContas.isEmpty()){

			Iterator iterContas = colecaoContas.iterator();

			while (iterContas.hasNext()) {
				ContaValoresHelper helper = (ContaValoresHelper) iterContas.next();
				Conta conta = helper.getConta();
				TarifaSocialCartaDebito tarifaSocialCartaDebito = new TarifaSocialCartaDebito();

				TarifaSocialCartaDebitoPK tarifaSocialCartaDebitoPK = new TarifaSocialCartaDebitoPK();
				tarifaSocialCartaDebitoPK.setContaId(conta.getId());
				tarifaSocialCartaDebitoPK.setImovelId(imovel.getId());
				tarifaSocialCartaDebitoPK.setTarifaSocialComandoCartaID(tscc.getId());
				tarifaSocialCartaDebito.setComp_id(tarifaSocialCartaDebitoPK);

				tarifaSocialCartaDebito.setImovel(imovel);
				tarifaSocialCartaDebito.setConta(conta);
				tarifaSocialCartaDebito.setTarifaSocialComandoCarta(tscc);

				tarifaSocialCartaDebito.setDataVencimentoConta(conta.getDataVencimentoConta());
				tarifaSocialCartaDebito.setReferenciaConta(conta.getReferencia());
				tarifaSocialCartaDebito.setValorConta(conta.getValorTotalContaBigDecimal());
				tarifaSocialCartaDebito.setUltimaAlteracao(new Date());

				getControladorUtil().inserir(tarifaSocialCartaDebito);
			}
		}
	}

	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social
	 * [SB0007]-Gera Arquivo TXT das Cartas
	 *
	 * @author Vivianne Sousa
	 * @date 29/03/2011
	 *
	 * @throws ControladorException
	 */
	public void gerarCartaTarifaSocial(TarifaSocialComandoCarta tscc,Integer idFuncionalidadeIniciada)throws ControladorException{

		BufferedWriter out = null;
		ZipOutputStream zos = null;
		File leitura = null;
		Date dataAtual = new Date();
		String nomeZip = null;
		int idUnidadeIniciada = 0;

		try{
			idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.LOCALIDADE,0);

			if(tscc.getDataProcessamento() != null && !tscc.getQuantidadeCartasGeradas().equals(0)){

				if(tscc.getCodigoTipoCarta().equals(new Integer(1))){
					nomeZip = "CARTAS_RECADASTRAMENTO_"
					+ Util.formatarData(dataAtual) + Util.formatarHoraSemDataSemDoisPontos(dataAtual);
				}else {
					nomeZip = "CARTAS_COBRANCA_"
					+ Util.formatarData(dataAtual) + Util.formatarHoraSemDataSemDoisPontos(dataAtual);
				}

				// Definindo arquivo para escrita
				nomeZip = nomeZip.replace("/", "_");
				File compactado = new File(nomeZip + ".zip");
				leitura = new File(nomeZip + ".txt");


			    zos = new ZipOutputStream(new FileOutputStream(compactado));
			    out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leitura.getAbsolutePath())));

			    // pegar o arquivo, zipar pasta e arquivo e escrever no stream
				System.out.println("***************************************");
				System.out.println("INICO DA CRIACAO DO ARQUIVO");
				System.out.println("***************************************");


				Collection colecaoTarifaSocialCarta = getControladorImovel().pesquisarTarifaSocialCarta(tscc.getId());

				System.out.println("***************************************");
				System.out.println("QTD DE CARTAS:"	+ colecaoTarifaSocialCarta.size());
				System.out.println("***************************************");

				//HEADER
				StringBuilder headerTxt = new StringBuilder();
				if(tscc.getCodigoTipoCarta().equals(new Integer(1))){
					headerTxt.append(Util.completaStringComEspacoAEsquerda("Carta de Recadastramento Imóvel com Tarifa Social",50));
				}else{
					headerTxt.append(Util.completaStringComEspacoAEsquerda("Carta de Cobrança Imóvel com Tarifa Social",50));
				}

				headerTxt.append(Util.completaStringComEspacoAEsquerda(tscc.getId().toString(),10));
				headerTxt.append(Util.completaStringComEspacoAEsquerda(Util.formatarData(tscc.getDataGeracao()),10));
				headerTxt.append(Util.completaStringComEspacoAEsquerda(tscc.getQuantidadeCartasGeradas().toString(),10));
				headerTxt.append(Util.completaStringComEspacoAEsquerda(tscc.getUsuario().getId().toString(),10));
				headerTxt.append(Util.completaStringComEspacoAEsquerda(tscc.getQuantidadeDiasComparecimento().toString(),10));
				headerTxt.append(System.getProperty("line.separator"));
			    out.write(headerTxt.toString());

				Iterator iterTSCR = colecaoTarifaSocialCarta.iterator();
				while (iterTSCR.hasNext()) {
					TarifaSocialCarta tarifaSocialCarta = (TarifaSocialCarta) iterTSCR.next();

					Integer idImovel = tarifaSocialCarta.getImovel().getId();

					String matriculaImovelFormatada = Util.adicionarZerosEsquedaNumero(8, idImovel.toString());
					matriculaImovelFormatada = matriculaImovelFormatada.substring(0, 7) + "." + matriculaImovelFormatada.substring(7, 8);
					String inscricao = getControladorImovel().pesquisarInscricaoImovel(idImovel);
					String enderecoImovel = this.getControladorEndereco().pesquisarEnderecoFormatado(idImovel);
					String nomeCliente = tarifaSocialCarta.getCliente().getNome();

					BigDecimal valorContas = ConstantesSistema.VALOR_ZERO;
					if(tscc.getCodigoTipoCarta().equals(new Integer(2))){
						valorContas = getControladorImovel().pesquisarValorContaTarifaSocialCartaDebito(tscc.getId(),idImovel);
					}

					StringBuilder cartaTxt = new StringBuilder();
					cartaTxt.append(matriculaImovelFormatada);
					cartaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(inscricao,20));
					cartaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(enderecoImovel,70));
					cartaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(nomeCliente,50));
					cartaTxt.append(Util.completaStringComEspacoAEsquerdaTruncandoAoTamanhoMaximoInformado(Util.formatarMoedaReal(valorContas),20));

					cartaTxt.append(System.getProperty("line.separator"));
				    out.write(cartaTxt.toString());

				}

				out.flush();

			}

    		getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
			System.out.println("***************************************");
			System.out.println("FIM DA CRIACAO DO ARQUIVO");
			System.out.println("***************************************");

		} catch (IOException ex) {
			ex.printStackTrace();
            getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,idUnidadeIniciada, true);
            throw new EJBException(ex);
	   } catch (Exception ex) {
            ex.printStackTrace();
            getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,idUnidadeIniciada, true);
            throw new EJBException(ex);
		} finally {
			try{
				out.close();
				ZipUtil.adicionarArquivo(zos, leitura);

				// close the stream
				zos.close();
				leitura.delete();
			} catch (IOException e) {
				getControladorBatch().encerrarUnidadeProcessamentoBatch(e,idUnidadeIniciada, true);
	            throw new EJBException(e);
			}
        }

	}

	/**
	 * [UC1161]Retirar Imóvel da Tarifa Social
	 *
	 * @author Vivianne Sousa
	 * @date 01/04/2011
	 *
	 * @throws ControladorException
	 */
	public void retirarImovelTarifaSocial(TarifaSocialComandoCarta tscc,
			int idFuncionalidadeIniciada) throws ControladorException {

		int idUnidadeIniciada = 0;
		Integer qtdeImoveisExcluidos = 0;
		try{
			idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.LOCALIDADE,0);

			Collection colecaoImoveis = getControladorImovel().pesquisarImoveisTarifaSocialCarta(tscc.getId(), tscc.getCodigoTipoCarta());
			if(colecaoImoveis != null && !colecaoImoveis.isEmpty()){
				Iterator iterImoveis = colecaoImoveis.iterator();

				if(tscc.getCodigoTipoCarta().equals(new Integer(1))){

					while (iterImoveis.hasNext()) {
						Imovel imovel = (Imovel) iterImoveis.next();

						//[SB0004]Retirar Imóvel Tarifa Social
						retirarImovelTarifaSocial(imovel, tscc);
						qtdeImoveisExcluidos = qtdeImoveisExcluidos + 1;
					}

				}else{
					while (iterImoveis.hasNext()) {
						Imovel imovel = (Imovel) iterImoveis.next();

						//[SB0003]–Verifica Situação dos Débitos
						Integer qtdeContas = verificaSituacaoDebitos(imovel,tscc);

						if(qtdeContas.compareTo(new Integer(0)) == 1){
							//[SB0004]Retirar Imóvel Tarifa Social
							retirarImovelTarifaSocial(imovel, tscc);
							qtdeImoveisExcluidos = qtdeImoveisExcluidos + 1;
						}

					}

				}

			}

			getControladorImovel().atualizarDataExecucaoTarifaSocialComandoCarta(tscc.getId());


			try {
				String emailReceptor = "";
				// Envia de Arquivo por email
				EnvioEmail envioEmail = this.pesquisarEnvioEmail(EnvioEmail.RETIRAR_IMOVEL_TARIFA_SOCIAL);

				String emailRemetente = envioEmail.getEmailRemetente();
				String tituloMensagem = envioEmail.getTituloMensagem();
				String corpoMensagem = obterConteudoEmail(qtdeImoveisExcluidos, tscc);

				ServicosEmail.enviarMensagem(emailRemetente, emailReceptor, tituloMensagem, corpoMensagem);

			} catch (Exception e) {
				System.out.println("Erro ao enviar email.");
			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);

		} catch (Exception e) {

			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,idUnidadeIniciada, true);
			throw new EJBException(e);
		}

	}
	/**
	 * [UC1161]Retirar Imóvel da Tarifa Social
	 * [SB0003]–Verifica Situação dos Débitos
	 *
	 * @author Vivianne Sousa
	 * @date 01/04/2011
	 *
	 * @throws ControladorException
	 */
	public Integer verificaSituacaoDebitos(Imovel imovel, TarifaSocialComandoCarta tscc)throws ControladorException{

		Collection colecaoContasTarifaSocialCartaDebito = getControladorImovel().
		pesquisarContasTarifaSocialCartaDebito(tscc.getId(),imovel.getId());

		Integer qtdeContas = getControladorFaturamento().pesquisarQtdeContaNaoPaga(colecaoContasTarifaSocialCartaDebito);

		return qtdeContas;
	}

	/**
	 * [UC1161]Retirar Imóvel da Tarifa Social
	 * [SB0002]–Verifica Média de Consumo
	 *
	 * @author Vivianne Sousa
	 * @date 04/04/2011
	 *
	 * @throws ControladorException
	 */
	public boolean verificaMediaConsumo(Imovel imovel)throws ControladorException{

		boolean retorno  = false;
		Integer consumoMedio = getControladorMicromedicao().obterConsumoMedioEmConsumoHistorico(imovel.getId(),LigacaoTipo.LIGACAO_AGUA);

		if(consumoMedio != null){
			FiltroCategoria filtroCategoria = new FiltroCategoria();
			filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO,Categoria.RESIDENCIAL));
			Collection<Categoria> collCategoria = Fachada.getInstancia().pesquisar(filtroCategoria,Categoria.class.getName());
			Categoria categoria = collCategoria.iterator().next();

			Integer consumoMinimoCategoria = categoria.getConsumoMinimo();
			int qtdeEconomias = getControladorImovel().obterQuantidadeEconomias(imovel);

			int consumoMinimo = ((consumoMinimoCategoria.intValue()) * qtdeEconomias);

			if(consumoMedio.intValue() > consumoMinimo){
				retorno = true;
			}
		}

		return retorno;
	}

	/**
	 * [UC1161]Retirar Imóvel da Tarifa Social
	 * [SB0004]–Retirar Imóvel tarifa Social
	 *
	 * @author Vivianne Sousa
	 * @date 04/04/2011
	 *
	 * @throws ControladorException
	 */
	public void retirarImovelTarifaSocial(Imovel imovel, TarifaSocialComandoCarta tscc)throws ControladorException{

		Integer motivoExclusao = null;
		String observacaoRetira = "";
		if(tscc == null){
			motivoExclusao = new Integer(17);
			observacaoRetira = "Imóvel retirado da situação de faturamento através de " +
			"processo batch executado para verificação de imóvel da tarifa social com média de consumo superior a 10m3";
		}else{
			observacaoRetira = "Imóvel retirado da situação de faturamento através de " +
			"processo batch executado por comando de carta de tarifa social com número " + tscc.getId();
			if(tscc.getCodigoTipoCarta().equals(new Integer(1))){
				motivoExclusao = new Integer(22);
			}else{
				motivoExclusao = new Integer(24);
			}
		}

		getControladorImovel().retirarImovelTarifaSocial(motivoExclusao, imovel,observacaoRetira);

	}

	/**
	 * [UC1161]Retirar Imóvel da Tarifa Social
	 *
	 * @author Vivianne Sousa
	 * @date 04/04/2011
	 *
	 * @throws ControladorException
	 */
	public String obterConteudoEmail(Integer qtdeImoveisExcluidos, TarifaSocialComandoCarta tscc)throws ControladorException{

		String conteudoEmail = "Processo para exclusão dos imóveis com perfil de TARIFA SOCIAL";

		if(tscc == null){
			//mensal
			conteudoEmail = conteudoEmail + " e que apresentaram média de consumo dos ultimos 6 meses superior a 10m, ";

		}else{

			if(tscc.getCodigoTipoCarta().equals(new Integer(1))){
				//recadastramento
				conteudoEmail = conteudoEmail + ", que recebeu carta de recadastramento do comando " + tscc.getId()
				+ " ,mas não compareceu para atualização dos dados cadsatrais no prazo estabelecido, ";

			}else{
				//cobrança
				conteudoEmail = conteudoEmail + ", que recebeu carta de cobrança do comando " + tscc.getId()
				+ " ,mas não compareceu para regularização do(s) débito(s) no prazo estabelecido, ";

			}
		}

		conteudoEmail = conteudoEmail + "foi executado com sucesso e retirou " + qtdeImoveisExcluidos
			+ " imóveis do perfil correspondente a TARIFA SOCIAL.";

		return conteudoEmail;
	}


	/**
	 * [UC1161]Retirar Imóvel da Tarifa Social
	 *
	 * @author Vivianne Sousa
	 * @date 04/04/2011
	 *
	 * @throws ControladorException
	 */
	public void retirarImovelTarifaSocial(Integer idLocalidade,	int idFuncionalidadeIniciada) throws ControladorException {

		int idUnidadeIniciada = 0;

		try{
			idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.LOCALIDADE,(idLocalidade));

			Collection colecaoImoveis = getControladorImovel().pesquisarImoveisTarifaSocial(idLocalidade);
			if(colecaoImoveis != null && !colecaoImoveis.isEmpty()){
				Iterator iterImoveis = colecaoImoveis.iterator();

				while (iterImoveis.hasNext()) {
					Imovel imovel = (Imovel) iterImoveis.next();

					if(verificaMediaConsumo(imovel)){
						//[SB0004]Retirar Imóvel Tarifa Social
						retirarImovelTarifaSocial(imovel, null);
					}
				}
			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);

		} catch (Exception e) {

			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,idUnidadeIniciada, true);
			throw new EJBException(e);
		}

	}


	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social
	 * [SB0008]-Verificar carta para o comando
	 *
	 * @author Vivianne Sousa
	 * @date 19/04/2011
	 *
	 * @throws ControladorException
	 */
	public void verificarCartaParaComando(Integer idLocalidade,TarifaSocialComandoCarta tscc)throws ControladorException{

		getControladorImovel().removerCartasComando(tscc.getId(),idLocalidade,tscc.getCodigoTipoCarta());

	}


	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social
	 *
	 * @author Vivianne Sousa
	 * @date 02/05/2011
	 *
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorGerenciaEUnidade(Integer idGerenciaRegional
			,Integer idUnidadeNegocio)throws ControladorException{

		try {

			return this.repositorioCadastro.pesquisarLocalidadesPorGerenciaEUnidade(
					idGerenciaRegional,idUnidadeNegocio);

        } catch (ErroRepositorioException ex) {
            ex.printStackTrace();
            throw new ControladorException("erro.sistema", ex);
        }

	}



	/**
	 * [UC1170] Gerar Relatório Acesso ao SPC
	 *
	 * @author: Diogo Peixoto
	 * @date: 06/05/2011
	 *
	 * @param FiltrarRelatorioAcessoSPCHelper
	 * @return Collection<RelatorioAcessoSPCBean>
	 * @throws ControladorException
	 */
	public Collection<RelatorioAcessoSPCBean> filtrarRelatorioAcessoSPC(FiltrarRelatorioAcessoSPCHelper filtro) throws ControladorException {
		Collection<Object[]> colecaoAcessoSPC = new ArrayList();
		Collection<RelatorioAcessoSPCBean> beans = new ArrayList();
		RelatorioAcessoSPCBean bean = null;

		try {

			colecaoAcessoSPC = this.repositorioCadastro.filtrarRelatorioAcessoSPC(filtro);

			if ( colecaoAcessoSPC != null && colecaoAcessoSPC.size() > 0 ){

				Iterator iteAcessoSPC = colecaoAcessoSPC.iterator();

				while (iteAcessoSPC.hasNext() ){

					Object[] linha = (Object[]) iteAcessoSPC.next();

					String strUnidade = null;
					if(linha[0] != null){
						strUnidade = String.valueOf((Integer) linha[0]);
					}
					if(linha[1] != null){
						strUnidade += " - " + (String) linha[1];
					}

					String strUsuario = null;
					if(linha[2] != null){
						strUsuario = (String) linha[2];
					}

					String cpfCliente = null;
					if(linha[3] != null){
						cpfCliente = (String) linha[3];
						cpfCliente = Util.formatarCpf(cpfCliente);
					}

					String cnpjCliente = null;
					if(linha[4] != null){
						cnpjCliente = (String) linha[4];
						cnpjCliente = Util.formatarCnpj(cnpjCliente);
					}

					//Define se o cliente é Pessoa Física ou Pessoa Jurídica, dependendo seta o cpf ou cnpj
					String cpfCnpjCliente = null;
					if(cpfCliente != null){
						cpfCnpjCliente = cpfCliente;
					}else if(cnpjCliente != null){
						cpfCnpjCliente = cnpjCliente;
					}

					String razaoSocial = null;
					if(linha[5] != null){
						razaoSocial = (String) linha[5];
					}

					String strDataAcesso = null;
					if(linha[6] != null){
						Date dataAacesso = (Date) linha[6];
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						strDataAcesso = sdf.format(dataAacesso);
					}

					bean = new RelatorioAcessoSPCBean(strUnidade, strUsuario, strDataAcesso, cpfCnpjCliente, razaoSocial);
					beans.add(bean);
				}
			}
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return beans;
	}

	/**
	 * [UC0925] Emitir Boletos
	 *
	 * retrona DBTP_VLLIMITE para DBTP_ID = idDebitoTipo
	 *
	 * @author Rômulo Aurélio
	 * @date 22/12/2009
	 *
	 * @throws ErroRepositorioException
	 */
	public void atualizarGrauImportancia(Logradouro logradouro, Integer grauImportancia, Usuario usuario)
			throws ControladorException {
		try {

//			 ------------ REGISTRAR TRANSAÇÃO----------------------------

			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.ATUALIZAR_IMPORTANCIA_LOGRADOURO, logradouro.getId(),
					logradouro.getId(), new UsuarioAcaoUsuarioHelper(usuario,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			registradorOperacao.registrarOperacao(logradouro);
			registradorOperacao.registrarOperacao(logradouro.getProgramaCalibragem());
			getControladorTransacao().registrarTransacao(logradouro);
//			 ------------ REGISTRAR TRANSAÇÃO----------------------------

			repositorioCadastro.atualizarGrauImportancia(logradouro.getId(),grauImportancia);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}




	/**
     * Obtém a coleção de categorias.
     *
     * @author Hugo Azevedo
     * @date 22/06/2011
     *
     * @throws ControladorException
     */

	public Collection obterCategorias() throws ControladorException{
		Collection retornoQuery = null;

		try {
			retornoQuery = this.repositorioCadastro.obterCategorias();
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}

		Collection retorno = null;
		Object[] obj = null;
		Categoria categoria = null;
		retorno = new ArrayList();
		Iterator it = retornoQuery.iterator();
		while(it.hasNext()){

			obj = (Object[])it.next();
			Integer id = (Integer) obj[0];
			String descricao = (String) obj[1];

			categoria = new Categoria();
			categoria.setId(id);
			categoria.setDescricao(descricao);

			retorno.add(categoria);
		}

		return retorno;

	}

	/**
     * Obtém a coleção de perfis de imóveis.
     *
     * @author Hugo Azevedo
     * @date 22/06/2011
     *
     * @throws ControladorException
     */

	public Collection obterPerfisImoveis() throws ControladorException{
		Collection retornoQuery = null;

		try {
			retornoQuery = this.repositorioCadastro.obterPerfisImoveis();
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}

		Collection retorno = new ArrayList();
		Object[] obj = null;

		ImovelPerfil perfil = null;
		Iterator it = retornoQuery.iterator();

		while(it.hasNext()){
			obj = (Object[]) it.next();

			perfil = new ImovelPerfil();
			perfil.setId((Integer) obj[0]);
			perfil.setDescricao((String) obj[1]);

			retorno.add(perfil);

		}

		return retorno;
	}


	/**
	 * [UC0060] Inserir Parametros do Sistema
	 * Validar documentos da loja virtual
	 *
	 * @author Erivan Sousa
	 * @date 15/07/2011
	 *
	 * @param byte[], String
	 * @throws ControladorException
	 */
	public void validarSistemaParametroLojaVirtual(byte[] fileData, String extensao) throws ControladorException{
		if (fileData.length == 0){
			throw new ControladorException("atencao.campo.informado", null, "Arquivo");
		}

		if (!extensao.equalsIgnoreCase("PDF")){
			throw new ControladorException("atencao.arquivo_invalido");
		}

	}


	/**
	 * [MA2011061013]
	 *
	 * @author Paulo Diniz
	 * @date 02/07/2011
	 *
	 * @param idImovel
	 *
	 * @return HidrometroMovimentado
	 * @throws ErroRepositorioException
	 */
	public  List<HidrometroInstalacaoHistorico> pesquisarHidrometroPeloIdImovel(Integer idImovel) throws ControladorException{

		try {
			return repositorioCadastro.pesquisarHidrometroPeloIdImovel(idImovel);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}


	/**
	 *
	 *
	 * @autor: Wellington Rocha
	 * @date: 21/03/2012
	 *
	 *        Pesquisar Ocorrencias de Cadastro
	 *
	 *        Geração de rotas para recadastramento
	 *
	 * @return Collection
	 * @throws ControladorException
	 *
	 */
	public Collection<CadastroOcorrencia> pesquisarOcorrenciasCadastro()
			throws ControladorException {
		try {
			Collection<CadastroOcorrencia> colecaoCadastroOcorrencia = new ArrayList<CadastroOcorrencia>();
			Collection colecao = repositorioCadastro
					.pesquisarOcorrenciasCadastro();

			if (colecao != null && !colecao.isEmpty()) {
				Iterator colecaoIterator = colecao.iterator();

				while (colecaoIterator.hasNext()) {
					CadastroOcorrencia cadastroOcorrencia = (CadastroOcorrencia) colecaoIterator
							.next();
					colecaoCadastroOcorrencia.add(cadastroOcorrencia);
				}
			}

			return colecaoCadastroOcorrencia;
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 *
	 *
	 * @autor: Wellington Rocha
	 * @date: 21/03/2012
	 *
	 *        Pesquisar Ramos de Atividade
	 *
	 *        Geração de rotas para recadastramento
	 *
	 * @return Collection
	 * @throws ControladorException
	 *
	 */
	public Collection<RamoAtividade> pesquisarRamosAtividade()
			throws ControladorException {
		try {
			Collection<RamoAtividade> colecaoRamosAtividade = new ArrayList<RamoAtividade>();
			Collection colecao = repositorioCadastro.pesquisarRamosAtividade();

			if (colecao != null && !colecao.isEmpty()) {
				Iterator colecaoIterator = colecao.iterator();

				while (colecaoIterator.hasNext()) {
					RamoAtividade ramoAtividade = (RamoAtividade) colecaoIterator
							.next();
					colecaoRamosAtividade.add(ramoAtividade);
				}
			}

			return colecaoRamosAtividade;
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 *
	 *
	 * @autor: Wellington Rocha
	 * @date: 21/03/2012
	 *
	 *        Pesquisar Fontes de Abastecimento
	 *
	 *        Geração de rotas para recadastramento
	 *
	 * @return Collection
	 * @throws ControladorException
	 *
	 */
	public Collection<FonteAbastecimento> pesquisarFonteAbastecimento()
			throws ControladorException {
		try {
			Collection<FonteAbastecimento> colecaoFonteAbastecimento = new ArrayList<FonteAbastecimento>();
			Collection colecao = repositorioCadastro
					.pesquisarFonteAbastecimento();

			if (colecao != null && !colecao.isEmpty()) {
				Iterator colecaoIterator = colecao.iterator();

				while (colecaoIterator.hasNext()) {
					FonteAbastecimento fonteAbastecimento = (FonteAbastecimento) colecaoIterator
							.next();
					colecaoFonteAbastecimento.add(fonteAbastecimento);
				}
			}

			return colecaoFonteAbastecimento;
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 *
	 *
	 * Método para atualizar situação (siac_id) de ImovelAtualizacaoCadastral
	 *
	 * @author Felipe Santos
	 * @date 27/12/2013
	 * @param idImovel
	 * @param situacao
	 * @throws ControladorException
	 */
	private void atualizarSituacaoImovelAtualizacaoCadastral(Integer idImovel, Integer situacao)
			throws ControladorException {
		FiltroImovelAtualizacaoCadastral filtroImovel = new FiltroImovelAtualizacaoCadastral();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovelAtualizacaoCadastral.ID, idImovel));

		ImovelAtualizacaoCadastral imovel = (ImovelAtualizacaoCadastral) Util.retonarObjetoDeColecao(
				getControladorUtil().pesquisar(filtroImovel, ImovelAtualizacaoCadastral.class.getName()));

		imovel.setIdSituacaoAtualizacaoCadastral(situacao);

		getControladorUtil().atualizar(imovel);
	}

	/**
	 *
	 *
	 * @author Matheus Souza
	 * @param Id do imovel
	 * @return Collection
	 * @date 18/01/2013
	 */
	public Collection obterImovelRamoAtividadeAtualizacaoCadastral(
			Integer idImovel) throws ControladorException {

		try {

			return this.repositorioCadastro
					.obterImovelRamoAtividadeAtualizacaoCadastral(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	public Collection<Integer> pesquisarRotasAtualizacaoCadastral(
			ImovelGeracaoTabelasTemporariasCadastroHelper helper) throws ControladorException {

		try {
			Leiturista leiturista = getLeituristaAtualizacaoCadastral(Integer.parseInt(helper.getLeiturista()));

			return this.repositorioCadastro.pesquisarRotasAtualizacaoCadastral(helper.getColecaoIdsImoveis());
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	public Leiturista getLeituristaAtualizacaoCadastral(Integer idLeiturista) throws ControladorException {
		try{
			FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.ID, idLeiturista));
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade("empresa");

			Collection<Leiturista> colecaoLeiturista = getControladorUtil().pesquisar(
					filtroLeiturista, Leiturista.class.getName());

			return (Leiturista) Util.retonarObjetoDeColecao(colecaoLeiturista);
		} catch (ControladorException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	public Collection<Integer> pesquisarIdImoveisAprovados() throws ControladorException {
		Collection<Integer> idImoveisAprovados = new ArrayList<Integer>();
		try {
			idImoveisAprovados = repositorioImovel.pesquisarIdImoveisAprovados();
		} catch (ErroRepositorioException e) {
			logger.error("Erro ao pesquisar id's de imoveis aprovados", e);
		}
		return idImoveisAprovados;
	}

	public SituacaoAtualizacaoCadastral pesquisarSituacaoAtualizacaoCadastralPorId(Integer idSituacaoCadastral) throws ControladorException {
		try {
			return repositorioCadastro.pesquisarSituacaoAtualizacaoCadastralPorId(idSituacaoCadastral);
		} catch(ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public Integer pesquisarIdSetorComercialPorCodigoELocalidade(Integer idLocalidade, Integer codigoSetor) throws ControladorException {
		Integer idSetorComercial = null;

		try {
			idSetorComercial = repositorioCadastro.pesquisarIdSetorComercialPorCodigoELocalidade(idLocalidade, codigoSetor);
		} catch (ErroRepositorioException e) {
			logger.error("Erro ao pesquisar id do setor comercial pela localidade e codigo", e);
		}
		return idSetorComercial;
	}

	public Integer pesquisarIdQuadraPorNumeroQuadraEIdSetor(Integer idSetorComercial, Integer numeroQuadra) throws ControladorException {

		Integer idQuadra = null;

		try {
			idQuadra = repositorioCadastro.pesquisarIdQuadraPorNumeroQuadraEIdSetor(idSetorComercial, numeroQuadra);
		} catch (ErroRepositorioException e) {
			logger.error("Erro ao pesquisar id da quadra pelo setor comercial e numero da quadra", e);
		}
		return idQuadra;

	}

	public String retornaIpServidorOperacional() throws Exception{
		return repositorioCadastro.retornaIpServidorOperacional();
	}

	public String retornaIpServidorRelatorios() throws Exception{
		return repositorioCadastro.retornaIpServidorRelatorios();
	}

	public Object[] pesquisarQtdeDebitosPreteritos(Integer idImovel) throws Exception {
		return repositorioCadastro.pesquisarQtdeDebitosPreteritos(idImovel);
	}

	public boolean verificarExistenciaEmpresa(Integer idEmpresa) throws ControladorException {

		boolean retorno = true;

		FiltroEmpresa filtro = new FiltroEmpresa();

		filtro.adicionarParametro(new ParametroSimples(FiltroArrecadador.ID, idEmpresa));

		Collection empresas = getControladorUtil().pesquisar(filtro, Empresa.class.getName());

		if (empresas == null || empresas.isEmpty()) {
			throw new ControladorException("atencao.empresa_inexistente");
		}
		return retorno;

	}
	
	public UnidadeOrganizacional obterUnidadePorLocalidade(Integer idLocalidade) throws ControladorException {
		
		FiltroUnidadeOrganizacional filtro = new FiltroUnidadeOrganizacional();
		filtro.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID_LOCALIDADE, idLocalidade));
		filtro.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.INDICADOR_USO, ConstantesSistema.SIM));
		
		return (UnidadeOrganizacional) getControladorUtil().pesquisar(filtro, UnidadeOrganizacional.class.getName()).iterator().next();
	}
}
