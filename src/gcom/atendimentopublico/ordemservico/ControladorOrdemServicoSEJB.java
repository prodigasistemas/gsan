package gcom.atendimentopublico.ordemservico;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.zip.ZipOutputStream;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.jboss.logging.Logger;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.atendimentopublico.ControladorAtendimentoPublicoLocal;
import gcom.atendimentopublico.ControladorAtendimentoPublicoLocalHome;
import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.ControladorLigacaoAguaLocal;
import gcom.atendimentopublico.ligacaoagua.ControladorLigacaoAguaLocalHome;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.ControladorLigacaoEsgotoLocal;
import gcom.atendimentopublico.ligacaoesgoto.ControladorLigacaoEsgotoLocalHome;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.bean.AcompanhamentoArquivosRoteiroHelper;
import gcom.atendimentopublico.ordemservico.bean.DadosAtualizacaoOSParaInstalacaoHidrometroHelper;
import gcom.atendimentopublico.ordemservico.bean.DadosAutoInfracaoRetornoOrdemFiscalizacaoHelper;
import gcom.atendimentopublico.ordemservico.bean.GerarBoletimOrdensServicoConcluidasHelper;
import gcom.atendimentopublico.ordemservico.bean.ManterDadosAtividadesOrdemServicoHelper;
import gcom.atendimentopublico.ordemservico.bean.ManterOrdemServicoConcluidaHelper;
import gcom.atendimentopublico.ordemservico.bean.OSAtividadePeriodoExecucaoHelper;
import gcom.atendimentopublico.ordemservico.bean.OSExecucaoEquipeHelper;
import gcom.atendimentopublico.ordemservico.bean.OSPavimentoHelper;
import gcom.atendimentopublico.ordemservico.bean.OSPavimentoRetornoHelper;
import gcom.atendimentopublico.ordemservico.bean.OSProgramacaoAcompanhamentoServicoHelper;
import gcom.atendimentopublico.ordemservico.bean.OSProgramacaoHelper;
import gcom.atendimentopublico.ordemservico.bean.OSRelatorioAcompanhamentoExecucaoHelper;
import gcom.atendimentopublico.ordemservico.bean.OSRelatorioHelper;
import gcom.atendimentopublico.ordemservico.bean.OSTipoPavimentoHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterCargaTrabalhoEquipeHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterDadosAtividadeOSHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterDadosAtividadesOSHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterDadosEquipe;
import gcom.atendimentopublico.ordemservico.bean.ObterDescricaoSituacaoOSHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterOSDistribuidasPorEquipeHelper;
import gcom.atendimentopublico.ordemservico.bean.OrdemRepavimentacaoProcessoAceiteHelper;
import gcom.atendimentopublico.ordemservico.bean.OrdemServicoHelper;
import gcom.atendimentopublico.ordemservico.bean.PesquisarOrdemServicoHelper;
import gcom.atendimentopublico.ordemservico.bean.RelacaoOrdensServicoConcluidasHelper;
import gcom.atendimentopublico.ordemservico.bean.RelacaoOrdensServicoEncerradasPendentesHelper;
import gcom.atendimentopublico.ordemservico.bean.RelatorioBoletimOrdensServicoConcluidasHelper;
import gcom.atendimentopublico.ordemservico.bean.SituacaoEncontradaHelper;
import gcom.atendimentopublico.ordemservico.bean.TxtOsInspecaoAnormalidadeHelper;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.ControladorRegistroAtendimentoLocal;
import gcom.atendimentopublico.registroatendimento.ControladorRegistroAtendimentoLocalHome;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.RABuilder;
import gcom.atendimentopublico.registroatendimento.RADadosGeraisHelper;
import gcom.atendimentopublico.registroatendimento.RALocalOcorrenciaHelper;
import gcom.atendimentopublico.registroatendimento.RASolicitanteHelper;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.Tramite;
import gcom.atendimentopublico.registroatendimento.bean.DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper;
import gcom.batch.ControladorBatchLocal;
import gcom.batch.ControladorBatchLocalHome;
import gcom.batch.Processo;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.ControladorCadastroLocal;
import gcom.cadastro.ControladorCadastroLocalHome;
import gcom.cadastro.EnvioEmail;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.cliente.ControladorClienteLocalHome;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.endereco.ControladorEnderecoLocalHome;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.imovel.RepositorioImovelHBM;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.projeto.FiltroProjeto;
import gcom.cadastro.projeto.Projeto;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.ControladorUnidadeLocal;
import gcom.cadastro.unidade.ControladorUnidadeLocalHome;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.CobrancaAcaoOrdemServicoNaoAceitas;
import gcom.cobranca.CobrancaAcaoOrdemServicoNaoAceitasPK;
import gcom.cobranca.CobrancaAcaoSituacao;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.CobrancaDocumentoFisc;
import gcom.cobranca.CobrancaForma;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.ControladorCobrancaLocal;
import gcom.cobranca.ControladorCobrancaLocalHome;
import gcom.cobranca.FiltroCobrancaAcaoOrdemServicoNaoAceitas;
import gcom.cobranca.FiltroCobrancaDocumento;
import gcom.cobranca.FiltroMotivoNaoAceitacaoEncerramentoOS;
import gcom.cobranca.IRepositorioCobranca;
import gcom.cobranca.MotivoNaoAceitacaoEncerramentoOS;
import gcom.cobranca.RepositorioCobrancaHBM;
import gcom.cobranca.bean.DadosPesquisaCobrancaDocumentoHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FaturamentoSituacaoHistorico;
import gcom.faturamento.FaturamentoSituacaoMotivo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.RepositorioFaturamentoHBM;
import gcom.faturamento.autoinfracao.AutoInfracaoSituacao;
import gcom.faturamento.autoinfracao.AutosInfracao;
import gcom.faturamento.autoinfracao.AutosInfracaoDebitoACobrar;
import gcom.faturamento.autoinfracao.FiltroAutoInfracaoSituacao;
import gcom.faturamento.bean.CalcularValoresAguaEsgotoHelper;
import gcom.faturamento.bean.IncluirDadosAcompanhamentoServicoHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.ConsumoTarifaCategoria;
import gcom.faturamento.consumotarifa.ConsumoTarifaVigencia;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarCategoria;
import gcom.faturamento.debito.DebitoACobrarCategoriaPK;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.atendimentopublico.ordemservico.FiltroEquipamentosEspeciais;
import gcom.gui.atendimentopublico.ordemservico.ImovelEmissaoOrdensSeletivasActionForm;
import gcom.gui.atendimentopublico.ordemservico.OSSeletivaInspecaoAnormalidadeGeradaHelper;
import gcom.gui.cobranca.cobrancaporresultado.MovimentarOrdemServicoEncerrarOSHelper;
import gcom.integracao.FiltroServicoTerceiroAcompanhamentoServico;
import gcom.integracao.ServicoTerceiroAcompanhamentoServico;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
import gcom.micromedicao.IRepositorioMicromedicao;
import gcom.micromedicao.RepositorioMicromedicaoHBM;
import gcom.micromedicao.Rota;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.operacional.DistritoOperacional;
import gcom.relatorio.atendimentopublico.OSExecutadasRelatorioHelper;
import gcom.relatorio.atendimentopublico.bean.ImovelEmissaoOrdensSeletivasHelper;
import gcom.relatorio.atendimentopublico.bean.RelatorioOSFiscalizacaoHelper;
import gcom.relatorio.atendimentopublico.ordemservico.FiltrarBoletimCustoPavimentoHelper;
import gcom.relatorio.atendimentopublico.ordemservico.RelatorioBoletimCustoPavimentoHelper;
import gcom.seguranca.ControladorPermissaoEspecialLocal;
import gcom.seguranca.ControladorPermissaoEspecialLocalHome;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.ControladorUsuarioLocal;
import gcom.seguranca.acesso.usuario.ControladorUsuarioLocalHome;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.seguranca.transacao.ControladorTransacaoLocalHome;
import gcom.spcserasa.ControladorSpcSerasaLocal;
import gcom.spcserasa.ControladorSpcSerasaLocalHome;
import gcom.util.ConstantesAplicacao;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorComum;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.Criptografia;
import gcom.util.ErroCriptografiaException;
import gcom.util.ErroRepositorioException;
import gcom.util.FachadaException;
import gcom.util.HibernateUtil;
import gcom.util.RepositorioUtilHBM;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.ZipUtil;
import gcom.util.email.ServicosEmail;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

public class ControladorOrdemServicoSEJB extends ControladorComum{
	private static final long serialVersionUID = -9200009057620946040L;
	
	private static final Logger logger = Logger.getLogger(ControladorOrdemServicoSEJB.class);

	private IRepositorioOrdemServico repositorioOrdemServico = null;
	private IRepositorioImovel       repositorioImovel       = null;
	private IRepositorioFaturamento  repositorioFaturamento  = null;
	private IRepositorioCobranca     repositorioCobranca     = null;
	private IRepositorioMicromedicao repositorioMicromedicao = null;

	public void ejbCreate() throws CreateException {
		repositorioOrdemServico = RepositorioOrdemServicoHBM.getInstancia();
		repositorioCobranca = RepositorioCobrancaHBM.getInstancia();
		repositorioMicromedicao = RepositorioMicromedicaoHBM.getInstancia();
		repositorioImovel = RepositorioImovelHBM.getInstancia();
		repositorioFaturamento = RepositorioFaturamentoHBM.getInstancia();
	}

	/**
	 * [UC0454] Obter Descrição da situação da OS
	 * 
	 * Este caso de uso permite obter a descrição de uma ordem de serviço
	 * 
	 * @author Leonardo Regis
	 * @date 11/08/2006
	 * 
	 * 
	 * @param idOrdemServico
	 * @throws ControladorException
	 */
	public ObterDescricaoSituacaoOSHelper obterDescricaoSituacaoOS(Integer idOrdemServico) throws ControladorException {

		ObterDescricaoSituacaoOSHelper retorno = new ObterDescricaoSituacaoOSHelper();
		try {

			// verifica o código da situação(ORSE_CDSITUACAO) da ordem de
			// serviço
			Short situacao = repositorioOrdemServico.verificaSituacaoOS(idOrdemServico);

			if (situacao != null) {
				// caso o valor igual a 1
				if (situacao.equals(OrdemServico.SITUACAO_PENDENTE)) {
					retorno.setDescricaoSituacao(OrdemServico.SITUACAO_DESCRICAO_PENDENTE);
					retorno.setDescricaoAbreviadaSituacao(OrdemServico.SITUACAO_DESC_ABREV_PENDENTE);
					retorno.setLetraIdentificadoraSituacaoOS(OrdemServico.SITUACAO_LETRA_IDENTIFICADORA_PENDENTE);
					// caso o valor igual a 2
				} else if (situacao.equals(OrdemServico.SITUACAO_ENCERRADO)) {
					retorno.setDescricaoSituacao(OrdemServico.SITUACAO_DESCRICAO_ENCERRADO);
					retorno.setDescricaoAbreviadaSituacao(OrdemServico.SITUACAO_DESC_ABREV_ENCERRADO);
					retorno.setLetraIdentificadoraSituacaoOS(OrdemServico.SITUACAO_LETRA_IDENTIFICADORA_ENCERRADO);
					// caso o valor igual a 3
				} else if (situacao.equals(OrdemServico.SITUACAO_EXECUCAO_EM_ANDAMENTO)) {
					retorno.setDescricaoSituacao(OrdemServico.SITUACAO_DESCRICAO_EXECUCAO_EM_ANDAMENTO);
					retorno.setDescricaoAbreviadaSituacao(OrdemServico.SITUACAO_DESC_ABREV_EXECUCAO_EM_ANDAMENTO);
					retorno.setLetraIdentificadoraSituacaoOS(OrdemServico.SITUACAO_LETRA_IDENTIFICADORA_EXECUCAO_EM_ANDAMENTO);
					// caso o valor igual a 4
				} else if (situacao.equals(OrdemServico.SITUACAO_AGUARDANDO_LIBERACAO)) {
					retorno.setDescricaoSituacao(OrdemServico.SITUACAO_DESCRICAO_AGUARDANDO_LIBERACAO);
					retorno.setDescricaoAbreviadaSituacao(OrdemServico.SITUACAO_DESC_ABREV_AGUARDANDO_LIBERACAO);
					retorno.setLetraIdentificadoraSituacaoOS(OrdemServico.SITUACAO_LETRA_IDENTIFICADORA_AGUARDANDO_LIBERACAO);
				}
			}
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return retorno;
	}

	/**
	 * [UC0441] Consultar Dados da Ordem de Serviço
	 * 
	 * @author Leonardo Regis
	 * @date 15/08/2006
	 * 
	 * @param idOrdemServico
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public OrdemServico consultarDadosOrdemServico(Integer idOrdemServico) throws ControladorException {

		OrdemServico retorno = null;
		try {
			retorno = repositorioOrdemServico.consultarDadosOS(idOrdemServico);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return retorno;
	}

	/**
	 * [UC0427] Tramitar Registro Atendimento
	 * 
	 * Verifica Existência de Ordem de Serviço Programada
	 * 
	 * @author Leonardo Regis
	 * @date 19/08/2006
	 * 
	 * @param idOS
	 * @return boolean
	 * @throws ControladorException
	 */
	public boolean verificarExistenciaOSProgramada(Integer idOS) throws ControladorException {
		boolean retorno = false;
		try {
			retorno = repositorioOrdemServico.verificarExistenciaOSProgramada(idOS);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return retorno;
	}

	/**
	 * [UC0450] Pesquisar Ordem de Servico
	 * 
	 * [SB001] Selecionar Ordem de Servico por Situação [SB002] Selecionar Ordem
	 * de Servico por Situação da Programação [SB003] Selecionar Ordem de
	 * Servico por Matricula do Imovel [SB004] Selecionar Ordem de Servico por
	 * Codigo do Cliente [SB005] Selecionar Ordem de Servico por Unidade
	 * Superior [SB006] Selecionar Ordem de Servico por Município [SB007]
	 * Selecionar Ordem de Servico por Bairro [SB008] Selecionar Ordem de
	 * Servico por Bairro Logradouro
	 * 
	 * @author Rafael Pinto
	 * @date 18/08/2006
	 * 
	 *       Alterado o método para fazer o merge de todos os ids de os
	 *       filtradas, bem como uma mensagem de erro caso não retorne registro.
	 * @author Leonardo Regis
	 * @date 07/09/2006
	 * 
	 * @param PesquisarOrdemServicoHelper
	 * @return Collection<OrdemServico>
	 * @throws ControladorException
	 */
	public Collection<OrdemServico> pesquisarOrdemServico(PesquisarOrdemServicoHelper filtro) throws ControladorException {

		Set colecaoOS = new HashSet();

		try {

			// [SB0004] - Seleciona Ordem Servico por Código de Cliente
			if (filtro.getCodigoCliente() != null) {

				Collection<Integer> colecaoIdOSPorCliente = this.consultarOrdemServicoPorCodigoCliente(filtro.getCodigoCliente(),
						filtro.getDocumentoCobranca(), filtro.getOrigemOrdemServico());

				if (colecaoIdOSPorCliente != null && !colecaoIdOSPorCliente.isEmpty()) {

					if (colecaoOS != null && !colecaoOS.isEmpty()) {
						Collection<Integer> colecaoIdOSRetorno = mergeIDs(colecaoOS, colecaoIdOSPorCliente);

						if (colecaoIdOSRetorno != null && !colecaoIdOSRetorno.isEmpty()) {
							colecaoOS.clear();
							colecaoOS.addAll(colecaoIdOSRetorno);
						} else {
							sessionContext.setRollbackOnly();
							throw new ControladorException("atencao.pesquisa.nenhumresultado");
						}
					} else {
						colecaoOS.addAll(colecaoIdOSPorCliente);
					}
				} else {
					sessionContext.setRollbackOnly();
					throw new ControladorException("atencao.pesquisa.nenhumresultado");
				}
			}

			filtro.setColecaoIdsOS(colecaoOS);

			/*
			 * Adicionado por Victor Cisneiros 19/set/2008
			 * 
			 * Se a pesquisa for por Unidade Superior, pesquisar todas as
			 * unidades filhas dessa Unidade Superior e então pesquisar as
			 * ordens de servico cuja unidade atual está presente nessa colecao
			 * de unidades filhas
			 */
			if (filtro.getUnidadeSuperior() != null && filtro.getIdsUnidadesAtuais() == null) {
				Integer idUnidadeSuperior = filtro.getUnidadeSuperior();

				Collection<UnidadeOrganizacional> unidadesFilhas = getControladorAtendimentoPublico().pesquisarUnidadesFilhas(idUnidadeSuperior).getUnidades()
						.values();

				Collection<Integer> collectionIdsUnidadeAtual = new ArrayList<Integer>();
				for (UnidadeOrganizacional unidade : unidadesFilhas) {
					collectionIdsUnidadeAtual.add(unidade.getId());
				}

				filtro.setIdsUnidadesAtuais(collectionIdsUnidadeAtual);
			}

			return repositorioOrdemServico.pesquisarOrdemServico(filtro);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Filtrar Ordem de Serviço
	 * 
	 * Faz o merge dos ids das os que entrarão na consulta do filtrar os
	 * 
	 * @author Leonardo Regis
	 * @date 07/09/2006
	 * 
	 * @param oldCollection
	 * @param newCollection
	 * @return coleção de ids de os
	 */
	private Collection<Integer> mergeIDs(Collection<Integer> oldCollection, Collection<Integer> newCollection) {
		Collection<Integer> returnCollection = new ArrayList();
		for (Integer oldIDs : oldCollection) {
			for (Integer newIDs : newCollection) {
				if (oldIDs.intValue() == newIDs.intValue()) {
					returnCollection.add(newIDs);
					break;
				}
			}
		}
		return returnCollection;
	}

	/**
	 * [UC0450] Pesquisar Ordem de Servico
	 * 
	 * [SB004] Selecionar Ordem de Servico por Codigo do Cliente [SB004] Caso
	 * 1.1 [SB004] Caso 1.2
	 * 
	 * @author Rafael Pinto
	 * @date 18/08/2006
	 * 
	 * @param codigoCliente
	 * 
	 * @return Collection<Integer>
	 * @throws ControladorException
	 */
	private Collection<Integer> consultarOrdemServicoPorCodigoCliente(Integer codigoCliente, Integer documentoCobranca, String origemOrdemServico)
			throws ControladorException {

		Set colecaoOS = new HashSet();

		try {
			Collection colecaoOSRASolicitantes = repositorioOrdemServico.recuperaOSPorCodigoClienteRASolicitante(codigoCliente);
			colecaoOS.addAll(colecaoOSRASolicitantes);

			Collection colecaoOSClienteImovel = repositorioOrdemServico.recuperaOSPorCodigoCliente(codigoCliente, origemOrdemServico);
			colecaoOS.addAll(colecaoOSClienteImovel);

			if (documentoCobranca != null) {
				Collection colecaoOSCobrancaDocumento = repositorioOrdemServico.recuperaOSPorCodigoClienteCobrancaDocumento(codigoCliente);
				colecaoOS.addAll(colecaoOSCobrancaDocumento);
			}

		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);

		}

		return colecaoOS;
	}

	/**
	 * [UC413]- Pesquisar Tipo de Serviço
	 * 
	 * Pesquisar o Objeto servicoTipo referente ao idTiposervico recebido na
	 * descrição da pesquisa, onde o mesmo sera detalhado.
	 * 
	 * @author Leandro Cavalcanti
	 * @date 23/08/2006
	 * 
	 * @param idTipoServico
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarServicoTipo(Integer idTipoServico) throws ControladorException {
		Object[] servicoTipo;
		try {
			servicoTipo = repositorioOrdemServico.pesquisarServicoTipo(idTipoServico);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return servicoTipo;

	}

	/**
	 * [UC0413] Pesquisar Tipo de Serviço
	 * 
	 * select a.svtp_id from ATENDIMENTOPUBLICO.SERVICO_TIPO A,
	 * ATENDIMENTOPUBLICO.SERVICO_TIPO_ATIVIDADE B,
	 * ATENDIMENTOPUBLICO.SERVICO_TIPO_MATERIAL C WHERE A.SVTP_DSSERVICOTIPO
	 * LIKE '%DESC%' AND A.SVTP_DSABREVIADO LIKE '%DESC%' AND (A.SVTP_VLSERVICO
	 * >= 000000 AND A.SVTP_VLSERVICO <= 99999) AND A.SVTP_ICPAVIMENTO = 1 OU 2
	 * and A.SVTP_ICATUALIZACOMERCIAL = 1 OU 2 AND A.SVTP_ICTERCEIRIZADO = 1 OU
	 * 2 AND A.SVTP_CDSERVICOTIPO = ("O" OR "C") AND
	 * (A.SVTP_NNTEMPOMEDIOEXECUCAO >= 0000 AND A.SVTP_NNTEMPOMEDIOEXECUCAO <=
	 * 9999) AND DBTP_ID = ID INFORMADO AND AND CRTP_ID = ID INFORMADO AND
	 * STSG_ID = ID INFORMADO AND STRF_ID = ID INFORMADO AND STPR_ID = ID
	 * INFORMADO AND A.SVTP_ID = B.SVTP_ID AND B.ATIV_ID IN (ID's INFORMADOS)
	 * AND A.SVTP_ID = C.SVTP_ID AND C.MATE_ID IN (ID's INFORMADOS)
	 * 
	 * 
	 * 
	 * @author Leandro Cavalcanti
	 * @date 17/08/2006
	 */
	public Collection<ServicoTipo> filtrarST(ServicoTipo servicoTipo, Collection colecaoAtividades, Collection colecaoMaterial, String valorServicoInicial,
			String valorServicoFinal, String tempoMedioExecucaoInicial, String tempoMedioExecucaoFinal, String tipoPesquisa, String tipoPesquisaAbreviada,
			Integer numeroPaginasPesquisa) throws ControladorException {

		ServicoTipo tipoServico = null;
		Collection colecaoServicoTipo = null;

		Collection colecaoServicoTipoNova = null;

		try {
			/*
			 * Collection colecaoAtividadesServicoTipo = repositorioOrdemServico
			 * .recuperarAtividadesServicoTipo(colecaoAtividades); Collection
			 * colecaoMaterialServicoTipo = repositorioOrdemServico
			 * .recuperarMaterialServicoTipo(colecaoMaterial);
			 */

			colecaoServicoTipo = repositorioOrdemServico.filtrarST(servicoTipo, colecaoAtividades, colecaoMaterial, valorServicoInicial, valorServicoFinal,
					tempoMedioExecucaoInicial, tempoMedioExecucaoFinal, tipoPesquisa, tipoPesquisaAbreviada, numeroPaginasPesquisa);

			if (colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()) {

				colecaoServicoTipoNova = new ArrayList();

				tipoServico = null;
				Iterator servicoTipoIterator = colecaoServicoTipo.iterator();
				Object[] arrayServicoTipo = null;
				while (servicoTipoIterator.hasNext()) {
					arrayServicoTipo = null;
					arrayServicoTipo = (Object[]) servicoTipoIterator.next();

					tipoServico = new ServicoTipo();

					if (arrayServicoTipo != null) {

						if (arrayServicoTipo[0] != null) {
							tipoServico.setId((Integer) arrayServicoTipo[0]);
						}

						if (arrayServicoTipo[1] != null) {
							tipoServico.setDescricao((String) arrayServicoTipo[1]);
						}

						if (arrayServicoTipo[2] != null) {
							ServicoTipoPrioridade servicoTipoPrioridade = new ServicoTipoPrioridade();
							servicoTipoPrioridade.setDescricao((String) arrayServicoTipo[2]);
							tipoServico.setServicoTipoPrioridade(servicoTipoPrioridade);
						}
						if (arrayServicoTipo[3] != null) {
							tipoServico.setIndicadorAtualizaComercial((Short) arrayServicoTipo[3]);
						}
						if (arrayServicoTipo[4] != null) {
							tipoServico.setIndicadorPavimentoRua((Short) arrayServicoTipo[4]);
						}

						if (arrayServicoTipo[5] != null) {
							tipoServico.setIndicadorPavimentoCalcada((Short) arrayServicoTipo[5]);
						}

						if (arrayServicoTipo[6] != null) {
							tipoServico.setIndicadorTerceirizado((Short) arrayServicoTipo[6]);
						}

						colecaoServicoTipoNova.add(tipoServico);
					}
				}

			}

			return colecaoServicoTipoNova;
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0413] Pesquisar Tipo de Serviço
	 * 
	 * 
	 * 
	 * @author Flávio
	 * @date 17/08/2006
	 */
	public Integer filtrarSTCount(ServicoTipo servicoTipo, Collection colecaoAtividades, Collection colecaoMaterial, String valorServicoInicial,
			String valorServicoFinal, String tempoMedioExecucaoInicial, String tempoMedioExecucaoFinal, String tipoPesquisa, String tipoPesquisaAbreviada)
			throws ControladorException {

		try {

			Integer qtdRegistros = repositorioOrdemServico.filtrarSTCount(servicoTipo, colecaoAtividades, colecaoMaterial, valorServicoInicial,
					valorServicoFinal, tempoMedioExecucaoInicial, tempoMedioExecucaoFinal, tipoPesquisa, tipoPesquisaAbreviada);

			return qtdRegistros;
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Pesquisar Atividades pelo idServicoTipo na tabela de ServicoTipoAtividade
	 * 
	 * Recupera os Atividades do servico tipo Atividade
	 * 
	 * @author Leandro Cavalcanti
	 * @date 24/08/2006
	 * 
	 * @param Integer
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Collection recuperarAtividadeServicoTipoConsulta(Integer idServicoTipoAtividade) throws ControladorException {
		Collection consultaAtividades;
		try {
			consultaAtividades = repositorioOrdemServico.recuperarAtividadeServicoTipoConsulta(idServicoTipoAtividade);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return consultaAtividades;

	}

	/**
	 * Pesquisar Materiais pelo idServicoTipo na tabela de ServicoTipoMaterial
	 * 
	 * Recupera os materiais do servico tipo material
	 * 
	 * @author Leandro Cavalcanti
	 * @date 24/08/2006
	 * 
	 * @param Integer
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Collection recuperarMaterialServicoTipoConsulta(Integer idServicoTipoMaterial) throws ControladorException {
		Collection consultaAtividades;
		try {
			consultaAtividades = repositorioOrdemServico.recuperarMaterialServicoTipoConsulta(idServicoTipoMaterial);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return consultaAtividades;

	}

	/**
	 * [UC0367] Atualizar Dados da Ligação Agua
	 * 
	 * Consulta a ordem de servico pelo id
	 * 
	 * @author Rafael Pinto
	 * @date 24/08/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public OrdemServico recuperaOSPorId(Integer idOS) throws ControladorException {

		try {
			return repositorioOrdemServico.recuperaOSPorId(idOS);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * 
	 * [UC0430] - Gerar Ordem de Serviço
	 * 
	 * @author lms
	 * @date 14/08/2006
	 */
	public void verificarExistenciaOrdemServico(Integer idRegistroAtendimento) throws ControladorException {
		try {

			// [FS0008] - Alerta Existência Ordem de Serviço

			/*
			 * Caso existam ordens de serviço pendentes para o registro de
			 * atendimento informado, exibir a mensagem: "Existe(m) Ordem(ns) de
			 * Serviço pendentes associadas ao Registro de Atendimento"
			 */
			boolean existe = repositorioOrdemServico.existeOrdemServicoDiferenteEncerrado(idRegistroAtendimento);

			if (existe) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.registro_atendimento.existe_os_pendente");
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * 
	 * [UC0430] - Gerar Ordem de Serviço
	 * 
	 * @author lms
	 * @date 14/08/2006
	 */
	public void validarServicoTipo(Integer idRegistroAtendimento, Integer idServicoTipo) throws ControladorException {
		try {

			// [FS0002] - Validar Tipo de Serviço

			// Caso 2
			OrdemServico os = repositorioOrdemServico.pesquisarOrdemServicoDiferenteEncerrado(idRegistroAtendimento, idServicoTipo);

			if (os != null) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.servico_tipo.existe_os", null, new String[] { os.getId().toString(),
						os.getServicoTipo().getDescricao(), os.getRegistroAtendimento().getId().toString() });
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0430] Gerar Ordem de Serviço
	 * 
	 * @author lms
	 * @date 17/08/2006
	 */
	public ServicoTipo pesquisarSevicoTipo(Integer id) throws ControladorException {
		try {
			return repositorioOrdemServico.pesquisarSevicoTipo(id);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * 
	 * [UC0430] - Gerar Ordem de Serviço
	 * 
	 * @author lms
	 * @date 18/08/2006
	 */
	public void validarOrdemServico(OrdemServico ordemServico) throws ControladorException {

		// [FS0003] - Validar Ordem de Serviço

		if (ordemServico.getServicoTipo().getServicoTipoReferencia() != null
				&& ordemServico.getServicoTipo().getServicoTipoReferencia().getIndicadorExistenciaOsReferencia() == ConstantesSistema.SIM) {

			if (ordemServico.getOsReferencia() == null) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.campo.informado", null, "Ordem de Serviço Referência");
			}

			// Caso 1
			FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
			filtroOrdemServico.adicionarParametro(new ParametroSimples(FiltroOrdemServico.ID, ordemServico.getOsReferencia().getId()));

			filtroOrdemServico.adicionarParametro(new ParametroSimples(FiltroOrdemServico.REGISTRO_ATENDIMENTO_ID, ordemServico.getRegistroAtendimento()
					.getId()));

			OrdemServico os = this.pesquisar(filtroOrdemServico, OrdemServico.class);

			if (os == null) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.os_referencia_pertence_outra_ra");
			}

			// Caso 2
			if (ordemServico.getServicoTipo().getServicoTipoReferencia().getServicoTipo() != null
					&& !ordemServico.getServicoTipo().getServicoTipoReferencia().getServicoTipo().getId().equals(ordemServico.getServicoTipo().getId())) {

				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.servico_tipo_os.incompativel", null, ordemServico.getServicoTipo().getServicoTipoReferencia()
						.getDescricao());
			}

			// Caso 3
			if (ordemServico.getOsReferencia().getSituacao() != ordemServico.getServicoTipo().getServicoTipoReferencia().getSituacaoOsReferenciaAntes()
					.shortValue()) {

				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.situacao_os.incompativel.situacao_os_requerida");
			}
		}
	}

	/**
	 * 
	 * [UC0488] - Informar Retorno Ordem de Fiscalização
	 * 
	 * [FS0001] - Validar Ordem de Serviço
	 * 
	 * @author Raphael Rossiter
	 * @date 29/01/2007
	 */
	public void validarOrdemServicoInformarRetornoOrdemFiscalizacao(OrdemServico ordemServico) throws ControladorException {

		// 1º Situação

		/*
		 * Caso o serviço associado à Ordem de Serviço não corresponda a um
		 * serviço de fiscalização de infração (SVTP_ICFISCALIZACAOINFRACAO da
		 * tabela SERVICO_TIPO com SVTP_ID = SVTP_ID da tabela ORDEM_SERVICO com
		 * valor correspondente a (Não) -2), exibir a mensagem “O serviço
		 * associado a esta ordem de serviço não corresponde a fiscalização de
		 * infração”.
		 */
		if (ordemServico.getServicoTipo().getIndicadorFiscalizacaoInfracao() == ConstantesSistema.NAO) {

			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.os.servico_tipo_nao_fiscalizacao");
		}

		// 2º Situação

		/*
		 * Caso a Ordem de Serviço esteja na situação de encerrada
		 * (ORSE_CDSITUAÇÃO na tabela ORDEM_SERVICO com o valor diferente de
		 * “Pendente” (1)), exibir a mensagem “Esta Ordem de Serviço está
		 * <<descrição da situação >>” e retornar para o passo correspondente no
		 * fluxo principal
		 */
		if (ordemServico.getSituacao() != OrdemServico.SITUACAO_PENDENTE) {

			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.os.encerrada");
		}

		// 3º Situação

		/*
		 * Caso a Ordem de Serviço não esteja associada a um documento de
		 * cobrança (CBDO_ID da tabela ORDEM_SERVICO com valor igual a NULO),
		 * exibir a mensagem “A ordem de serviço não está associada a um
		 * documento de cobrança” e retornar para o passo correspondente no
		 * fluxo principal.
		 */
		// if (ordemServico.getCobrancaDocumento() == null) {
		//
		// sessionContext.setRollbackOnly();
		// throw new ControladorException(
		// "atencao.os.nao_associada_cobranca_documento");
		// }
	}

	/**
	 * 
	 * [UC0430] - Gerar Ordem de Serviço
	 * 
	 * Mudar no método gerarOrdemServicoSemValidacao, gerarOrdemServicoSeletiva
	 * caso aconteça alguma mudança nesse método
	 * 
	 * @author lms
	 * @date 18/08/2006
	 */
	public Integer gerarOrdemServico(OrdemServico ordemServico, Usuario usuario, Integer funcionalidade) throws ControladorException {

		// [FS0003] - Validar Ordem de Serviço
		this.validarOrdemServico(ordemServico);

		Calendar calendar = Calendar.getInstance();

		// [SB0004] - Gerar Ordem de serviço

		ordemServico.setAtendimentoMotivoEncerramento(null);
		ordemServico.setOsReferidaRetornoTipo(null);
		ordemServico.setSituacao(OrdemServico.SITUACAO_PENDENTE);
		ordemServico.setDataGeracao(new Date());
		ordemServico.setDataEmissao(null);
		ordemServico.setDataEncerramento(null);
		ordemServico.setDescricaoParecerEncerramento(null);
		ordemServico.setAreaPavimento(null);
		ordemServico.setIndicadorComercialAtualizado(ConstantesSistema.NAO);
		ordemServico.setServicoNaoCobrancaMotivo(null);
		ordemServico.setPercentualCobranca(null);
		ordemServico.setFiscalizacaoColetiva(null);
		ordemServico.setIndicadorServicoDiagnosticado(ConstantesSistema.NAO);
		ordemServico.setUltimaAlteracao(calendar.getTime());
		ordemServico.setValorOriginal(ordemServico.getServicoTipo().getValor());
		ordemServico.setServicoTipoPrioridadeOriginal(ordemServico.getServicoTipo().getServicoTipoPrioridade());

		if (ordemServico.getServicoTipoPrioridadeAtual() == null
				|| ordemServico.getServicoTipoPrioridadeAtual().getId().equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			ordemServico.setServicoTipoPrioridadeAtual(ordemServico.getServicoTipoPrioridadeOriginal());
		}

		// Colocar como ordem de servico não programada - Raphael Rossiter em
		// 08/02/2007
		ordemServico.setIndicadorProgramada(OrdemServico.NAO_PROGRAMADA);

		// Vivianne Sousa - 02/04/2008
		ordemServico.setIndicadorEncerramentoAutomatico(ConstantesSistema.NAO);
		ordemServico.setIndicadorBoletim(ConstantesSistema.NAO);

		UnidadeOrganizacional unidadeOrganizacional = null;
		/*
		 * Caso não exista RA, atualizar a unidade como a unidade que represente
		 * a localidade do imóvel associado ao documento de cobrança
		 */
		if (ordemServico.getRegistroAtendimento() != null) {

			/*
			 * unidadeOrganizacional = getControladorRegistroAtendimento()
			 * .obterUnidadeAtualRA(
			 * ordemServico.getRegistroAtendimento().getId());
			 * 
			 * unidadeOrganizacional = ordemServico.getRegistroAtendimento().get
			 */

			// unidadeOrganizacional =
			// this.getControladorRegistroAtendimento().obterUnidadeAtendimentoRA(
			// ordemServico.getRegistroAtendimento().getId());

			// inclusão da coluna unidade atual nas tabelas REGISTRO_ATENDIMENTO
			// e ORDEM_SERVICO
			// comentado por Vivianne Sousa 16/06/2008 analista:Fátima Sampaio
			unidadeOrganizacional = this.getControladorRegistroAtendimento().obterUnidadeAtualRA(ordemServico.getRegistroAtendimento().getId());

		}
		// inclusão da coluna unidade atual nas tabelas REGISTRO_ATENDIMENTO e
		// ORDEM_SERVICO
		// Vivianne Sousa 16/06/2008 analista:Fátima Sampaio
		//
		// caso a funcionalidade que chamou esse caso de uso
		// corresponda a
		// "[UC0870] Gerar Movimento de Contas em Cobrança por Empresa"
		// Mariana Victor 15/04/2011 Analista: Rosana Carvalho
		if (funcionalidade == null
				|| (funcionalidade.intValue() != Funcionalidade.GERAR_MOVIMENTO_CONTAS_COBRANCA_POR_EMPRESA
						&& funcionalidade.intValue() != Funcionalidade.MOVIMENTAR_ORDENS_DE_SERVICO_DE_COBRANCA_POR_RESULTADO && funcionalidade.intValue() != Funcionalidade.MOVIMENTAR_OS_SELETIVA_INSPECAO_ANORMALIDADE)) {
			ordemServico.setUnidadeAtual(unidadeOrganizacional);
		}

		Integer idOrdemServico = (Integer) getControladorUtil().inserir(ordemServico);

		OrdemServicoUnidade ordemServicoUnidade = new OrdemServicoUnidade();

		ordemServicoUnidade.setOrdemServico(ordemServico);

		// Vivianne Sousa 20/06/2008
		// Analista: Fatima Sampaio
		if (funcionalidade != null
				&& (funcionalidade.intValue() == Funcionalidade.INSERIR_REGISTRO_ATENDIMENTO || funcionalidade.intValue() == Funcionalidade.REATIVAR_REGISTRO_ATENDIMENTO)) {
			// caso a funcionalidade q chamou esse caso de uso
			// corresponda a "Inserir Registro de Atendimento"
			// determinar a partir da unidade de atendimento do RA

			UnidadeOrganizacional unidadeAtendimentoRA = this.getControladorRegistroAtendimento().obterUnidadeAtendimentoRA(
					ordemServico.getRegistroAtendimento().getId());

			ordemServicoUnidade.setUnidadeOrganizacional(unidadeAtendimentoRA);
		} else if (funcionalidade != null
				&& (funcionalidade.intValue() == Funcionalidade.GERAR_MOVIMENTO_CONTAS_COBRANCA_POR_EMPRESA
						|| funcionalidade.intValue() == Funcionalidade.MOVIMENTAR_ORDENS_DE_SERVICO_DE_COBRANCA_POR_RESULTADO || funcionalidade.intValue() == Funcionalidade.MOVIMENTAR_OS_SELETIVA_INSPECAO_ANORMALIDADE)) {
			// Determinar a partir da unidade atual da Ordem de Serviço
			ordemServicoUnidade.setUnidadeOrganizacional(ordemServico.getUnidadeAtual());
		} else {
			// caso contrario
			// determinar a partir da unidade atual do RA
			ordemServicoUnidade.setUnidadeOrganizacional(unidadeOrganizacional);
		}

		ordemServicoUnidade.setUsuario(usuario);
		AtendimentoRelacaoTipo atrt = new AtendimentoRelacaoTipo();
		atrt.setId(AtendimentoRelacaoTipo.ABRIR_REGISTRAR);
		ordemServicoUnidade.setAtendimentoRelacaoTipo(atrt);
		ordemServicoUnidade.setUltimaAlteracao(calendar.getTime());

		getControladorUtil().inserir(ordemServicoUnidade);

		if (ordemServico.getServicoTipo().getServicoTipoReferencia() != null
				&& ordemServico.getServicoTipo().getServicoTipoReferencia().getIndicadorExistenciaOsReferencia() == ConstantesSistema.SIM) {

			OrdemServico osReferencia = ordemServico.getOsReferencia();
			osReferencia.setSituacao(OrdemServico.SITUACAO_AGUARDANDO_LIBERACAO);

			this.getControladorUtil().atualizar(osReferencia);

		}

		return idOrdemServico;
	}

	/**
	 * 
	 * [UC0430] - Gerar Ordem de Serviço
	 * 
	 * Método que é chamado pelo [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * @author Sávio Luiz
	 * @date 18/08/2006
	 */
	public Integer gerarOrdemServicoSemValidacao(OrdemServico ordemServico, Integer idLocalidade, Usuario usuario) throws ControladorException {

		Calendar calendar = Calendar.getInstance();

		// [SB0004] - Gerar Ordem de serviço

		ordemServico.setAtendimentoMotivoEncerramento(null);
		ordemServico.setOsReferidaRetornoTipo(null);
		ordemServico.setSituacao(OrdemServico.SITUACAO_PENDENTE);
		ordemServico.setDataGeracao(calendar.getTime());
		ordemServico.setDataEmissao(null);
		ordemServico.setDataEncerramento(null);
		ordemServico.setDescricaoParecerEncerramento(null);
		ordemServico.setAreaPavimento(null);
		ordemServico.setIndicadorComercialAtualizado(ConstantesSistema.NAO);
		ordemServico.setServicoNaoCobrancaMotivo(null);
		ordemServico.setPercentualCobranca(null);
		ordemServico.setFiscalizacaoColetiva(null);
		ordemServico.setIndicadorServicoDiagnosticado(ConstantesSistema.NAO);
		ordemServico.setUltimaAlteracao(calendar.getTime());
		ordemServico.setIndicadorEncerramentoAutomatico(ConstantesSistema.NAO);

		OrdemServico ordemServicoDados = null;
		try {
			ordemServicoDados = repositorioOrdemServico.pesquisarDadosServicoTipoPrioridade(ordemServico.getServicoTipo().getId());
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		if (ordemServicoDados != null) {
			ordemServico.setValorOriginal(ordemServicoDados.getServicoTipo().getValor());
			ordemServico.setServicoTipoPrioridadeOriginal(ordemServicoDados.getServicoTipo().getServicoTipoPrioridade());

			ordemServico.setServicoTipoPrioridadeAtual(ordemServicoDados.getServicoTipo().getServicoTipoPrioridade());

		}

		// Colocar como ordem de servico não programada - Raphael Rossiter em
		// 08/02/2007
		ordemServico.setIndicadorProgramada(OrdemServico.NAO_PROGRAMADA);
		ordemServico.setIndicadorBoletim(ConstantesSistema.NAO);

		UnidadeOrganizacional unidadeOrganizacional = getControladorUnidade().pesquisarUnidadeOrganizacionalLocalidade(idLocalidade);

		// [FS0009]-Verificar existência de Unidade correspondente à Localidade
		if (unidadeOrganizacional == null) {
			throw new ControladorException("atencao.unidade_nao_existe_localidade", null, idLocalidade.toString());
		}

		// inclusão da coluna unidade atual nas tabelas REGISTRO_ATENDIMENTO e
		// ORDEM_SERVICO
		// Vivianne Sousa 13/06/2008 analista:Fátima Sampaio
		ordemServico.setUnidadeAtual(unidadeOrganizacional);

		Integer idOrdemServico = (Integer) getControladorUtil().inserir(ordemServico);

		OrdemServicoUnidade ordemServicoUnidade = new OrdemServicoUnidade();

		ordemServicoUnidade.setOrdemServico(ordemServico);

		ordemServicoUnidade.setUnidadeOrganizacional(unidadeOrganizacional);
		ordemServicoUnidade.setUsuario(usuario);
		AtendimentoRelacaoTipo atrt = new AtendimentoRelacaoTipo();
		atrt.setId(AtendimentoRelacaoTipo.ABRIR_REGISTRAR);
		ordemServicoUnidade.setAtendimentoRelacaoTipo(atrt);
		ordemServicoUnidade.setUltimaAlteracao(calendar.getTime());

		getControladorUtil().inserir(ordemServicoUnidade);

		/*
		 * if (ordemServico.getServicoTipo().getServicoTipoReferencia() != null
		 * && ordemServico.getServicoTipo().getServicoTipoReferencia()
		 * .getIndicadorExistenciaOsReferencia() == ConstantesSistema.SIM) {
		 * 
		 * OrdemServico osReferencia = ordemServico.getOsReferencia();
		 * osReferencia
		 * .setSituacao(OrdemServico.SITUACAO_AGUARDANDO_LIBERACAO);
		 * 
		 * this.getControladorUtil().atualizar(osReferencia); }
		 */

		return idOrdemServico;
	}

	/**
	 * [UC0430] Gerar Ordem de Serviço
	 * 
	 * @author lms
	 * @date 17/08/2006
	 */
	public OrdemServico pesquisarOrdemServico(Integer id) throws ControladorException {

		FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("osReferencia");
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("servicoTipo.servicoTipoReferencia.servicoTipo");
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("servicoTipo.servicoTipoPrioridade");
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("servicoTipo.programaCalibragem");
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("servicoTipoReferencia");
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("servicoTipoReferencia.servicoTipoReferencia");
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("servicoTipoPrioridadeAtual");
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("servicoTipoPrioridadeOriginal");
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("imovel.imovelPerfil");

		filtroOrdemServico.adicionarParametro(new ParametroSimples(FiltroOrdemServico.ID, id.toString()));

		return pesquisar(filtroOrdemServico, OrdemServico.class);
	}

	/**
	 * [UC0806] Gerar Registro Atendimento para Imóveis com Anormalidades
	 * 
	 * @author Diogo Peixoto
	 * @date 14/03/2011
	 */
	public OrdemServico pesquisarOrdemServicoRegistroAtendimento(RegistroAtendimento ra) throws ControladorException {

		FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("osReferencia");
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("servicoTipo.servicoTipoReferencia.servicoTipo");
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("servicoTipo.servicoTipoPrioridade");
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("servicoTipoReferencia");
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("servicoTipoReferencia.servicoTipoReferencia");
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("servicoTipoPrioridadeAtual");
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("servicoTipoPrioridadeOriginal");
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("imovel.imovelPerfil");

		filtroOrdemServico.adicionarParametro(new ParametroSimples(FiltroOrdemServico.REGISTRO_ATENDIMENTO_ID, ra.getId().toString()));

		return pesquisar(filtroOrdemServico, OrdemServico.class);
	}

	private <T> T pesquisar(Filtro filtro, Class clazz) throws ControladorException {

		T objeto = null;

		Collection colecao = getControladorUtil().pesquisar(filtro, clazz.getName());

		if (colecao != null && !colecao.isEmpty()) {
			objeto = (T) colecao.iterator().next();
		}
		return objeto;
	}

	/**
	 * [UC0471] Obter Dados da Equipe
	 * 
	 * @author Raphael Rossiter
	 * @date 01/09/2006
	 * 
	 * @return idQuipe
	 * @throws ControladorException
	 */
	public ObterDadosEquipe obterDadosEquipe(Integer idEquipe) throws ControladorException {

		ObterDadosEquipe retorno = null;

		try {

			Collection colecaoEquipe = repositorioOrdemServico.pesquisarEquipe(idEquipe);

			if (colecaoEquipe != null && !colecaoEquipe.isEmpty()) {

				retorno = new ObterDadosEquipe();

				// Dados da Equipe
				Object[] arrayEquipe = (Object[]) colecaoEquipe.iterator().next();

				Equipe equipe = new Equipe();
				UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
				ServicoPerfilTipo servicoPerfilTipo = new ServicoPerfilTipo();

				equipe.setId((Integer) arrayEquipe[0]);
				equipe.setNome((String) arrayEquipe[1]);

				if (arrayEquipe[2] != null) {
					equipe.setPlacaVeiculo((String) arrayEquipe[2]);
				}

				if (arrayEquipe[3] != null) {
					equipe.setCargaTrabalho((Integer) arrayEquipe[3]);
				}

				unidadeOrganizacional.setId((Integer) arrayEquipe[4]);
				unidadeOrganizacional.setDescricao((String) arrayEquipe[5]);

				equipe.setUnidadeOrganizacional(unidadeOrganizacional);

				servicoPerfilTipo.setId((Integer) arrayEquipe[6]);
				servicoPerfilTipo.setDescricao((String) arrayEquipe[7]);

				equipe.setServicoPerfilTipo(servicoPerfilTipo);

				retorno.setEquipe(equipe);

				Collection colecaoEquipeComponentes = repositorioOrdemServico.pesquisarEquipeComponentes(idEquipe);

				if (colecaoEquipeComponentes != null && !colecaoEquipeComponentes.isEmpty()) {

					// Dados dos componenetes da equipe
					Collection colecaoEquipeComponentesFinal = new ArrayList();

					Iterator iteratorComponentes = colecaoEquipeComponentes.iterator();
					EquipeComponentes equipeComponentes = null;
					Funcionario funcionario = null;
					Object[] arrayEquipeComponentes = null;

					while (iteratorComponentes.hasNext()) {

						arrayEquipeComponentes = (Object[]) iteratorComponentes.next();

						equipeComponentes = new EquipeComponentes();

						equipeComponentes.setEquipe(equipe);

						equipeComponentes.setId((Integer) arrayEquipeComponentes[0]);
						equipeComponentes.setIndicadorResponsavel((Short) arrayEquipeComponentes[1]);

						if (arrayEquipeComponentes[2] != null) {
							funcionario = new Funcionario();

							funcionario.setId((Integer) arrayEquipeComponentes[2]);
							funcionario.setNome((String) arrayEquipeComponentes[3]);

							equipeComponentes.setFuncionario(funcionario);
						}

						if (arrayEquipeComponentes[4] != null) {
							equipeComponentes.setComponentes((String) arrayEquipeComponentes[4]);
						}

						colecaoEquipeComponentesFinal.add(equipeComponentes);
					}

					retorno.setColecaoEquipeComponentes(colecaoEquipeComponentesFinal);
				}
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto, Raphael Rossiter
	 * @date 04/09/2006, 22/05/2009
	 * 
	 * @param unidadeLotacao
	 * @param tipoCriterio
	 * @param origemServico
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarTipoServicoDisponivelPorCriterio(UnidadeOrganizacional unidadeLotacao, int tipoCriterio, int origemServico)
			throws ControladorException {

		Set retorno = new CopyOnWriteArraySet();

		Collection colecao = new ArrayList();
		try {

			Collection<Integer> idsRa = this.repositorioOrdemServico.recuperaRegistroAtendimentoPendenteUnidadeAtual(unidadeLotacao.getId());

			switch (tipoCriterio) {

			case 1:

				// Consulta somente o servico solicitado
				if (origemServico == 1) {

					if (idsRa != null && !idsRa.isEmpty()) {
						colecao = this.repositorioOrdemServico.pesquisarServicoTipoPorRA(idsRa);
						retorno.addAll(colecao);
						break;
					}

					// Consulta somente o servico seletivo
				} else if (origemServico == 2) {

					colecao = this.repositorioOrdemServico.pesquisarServicoTipoPorUnidade(unidadeLotacao.getId());
					retorno.addAll(colecao);
					break;

					// Consulta ambas origens de serviço
				} else {

					if (idsRa != null && !idsRa.isEmpty()) {
						retorno.addAll(this.repositorioOrdemServico.pesquisarServicoTipoPorRA(idsRa));
					}

					colecao = this.repositorioOrdemServico.pesquisarServicoTipoPorUnidade(unidadeLotacao.getId());

					retorno.addAll(this.removeObjetoRepetido(retorno, colecao));
					break;
				}

			case 2:
				colecao = this.repositorioOrdemServico.pesquisarServicoPerfilTipoPorUnidade(unidadeLotacao.getId());
				retorno.addAll(colecao);
				break;

			case 3:

				// Consulta somente o servico solicitado
				if (origemServico == 1) {

					if (idsRa != null && !idsRa.isEmpty()) {

						colecao = this.getControladorUnidade().pesquisarUnidadeOrganizacionalPorRA(idsRa);
						retorno.addAll(colecao);
						break;
					}

					// Consulta somente o servico seletivo
				} else if (origemServico == 2) {
					colecao = this.getControladorUnidade().pesquisarUnidadeOrganizacionalPorUnidade(unidadeLotacao.getId());
					retorno.addAll(colecao);
					break;

					// Consulta ambas origens de serviço
				} else {

					if (idsRa != null && !idsRa.isEmpty()) {
						retorno.addAll(this.getControladorUnidade().pesquisarUnidadeOrganizacionalPorRA(idsRa));
					}

					colecao = this.getControladorUnidade().pesquisarUnidadeOrganizacionalPorUnidade(unidadeLotacao.getId());

					retorno.addAll(this.removeObjetoRepetido(retorno, colecao));
					break;
				}

			case 4:

				// Consulta somente o servico solicitado
				if (origemServico == ConstantesSistema.SIM.intValue()) {

					if (idsRa != null && !idsRa.isEmpty()) {
						colecao = this.repositorioOrdemServico.pesquisarLocalidadePorRA(idsRa);
						retorno.addAll(colecao);
						break;
					}
					// Consulta somente o servico seletivo
				} else if (origemServico == ConstantesSistema.NAO.intValue()) {
					colecao = this.repositorioOrdemServico.pesquisarLocalidadePorUnidade(unidadeLotacao.getId());
					retorno.addAll(colecao);
					break;
					// Consulta ambas origens de serviço
				} else {

					if (idsRa != null && !idsRa.isEmpty()) {
						retorno.addAll(this.repositorioOrdemServico.pesquisarLocalidadePorRA(idsRa));
					}

					colecao = this.repositorioOrdemServico.pesquisarLocalidadePorUnidade(unidadeLotacao.getId());

					retorno.addAll(this.removeObjetoRepetido(retorno, colecao));
					break;
				}

			case 5:

				// Consulta somente o servico solicitado
				if (origemServico == ConstantesSistema.SIM.intValue()) {

					if (idsRa != null && !idsRa.isEmpty()) {
						colecao = this.repositorioOrdemServico.pesquisarSetorComercialPorRA(idsRa);
						retorno.addAll(colecao);
						break;
					}

					// Consulta somente o servico seletivo
				} else if (origemServico == ConstantesSistema.NAO.intValue()) {
					colecao = this.repositorioOrdemServico.pesquisarSetorComercialPorUnidade(unidadeLotacao.getId());
					retorno.addAll(colecao);
					break;
					// Consulta ambas origens de serviço
				} else {
					if (idsRa != null && !idsRa.isEmpty()) {
						retorno.addAll(this.repositorioOrdemServico.pesquisarSetorComercialPorRA(idsRa));
					}

					colecao = this.repositorioOrdemServico.pesquisarSetorComercialPorUnidade(unidadeLotacao.getId());

					retorno.addAll(this.removeObjetoRepetido(retorno, colecao));
					break;
				}

			case 6:

				SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();

				// Consulta somente o servico solicitado
				if (origemServico == ConstantesSistema.SIM.intValue()) {

					if (idsRa != null && !idsRa.isEmpty()) {

						/*
						 * Caso a empresa utilize o conceito de face da quadra
						 * (PARM_ICQUADRAFACE = 1 da tabela SISTEMA_PARAMETROS);
						 * os campos de INDICADOR_REDE_AGUA,
						 * INDICADOR_REDE_ESGOTO DISTRITO_OPERACIONAL e BACIA
						 * serão obtidos a partir da face da quadra.
						 */
						if (sistemaParametro.getIndicadorQuadraFace().equals(ConstantesSistema.SIM)) {

							colecao = this.repositorioOrdemServico.pesquisarDistritoOperacionalPorRAPelaQuadraFace(idsRa);
						} else {

							colecao = this.repositorioOrdemServico.pesquisarDistritoOperacionalPorRAPelaQuadra(idsRa);
						}

						retorno.addAll(colecao);

						break;
					}

					// Consulta somente o servico seletivo
				} else if (origemServico == ConstantesSistema.NAO.intValue()) {
					colecao = this.repositorioOrdemServico.pesquisarDistritoOperacionalPorUnidade(unidadeLotacao.getId());
					retorno.addAll(colecao);
					break;
					// Consulta ambas origens de serviço
				} else {

					if (idsRa != null && !idsRa.isEmpty()) {

						/*
						 * Caso a empresa utilize o conceito de face da quadra
						 * (PARM_ICQUADRAFACE = 1 da tabela SISTEMA_PARAMETROS);
						 * os campos de INDICADOR_REDE_AGUA,
						 * INDICADOR_REDE_ESGOTO DISTRITO_OPERACIONAL e BACIA
						 * serão obtidos a partir da face da quadra.
						 */
						if (sistemaParametro.getIndicadorQuadraFace().equals(ConstantesSistema.SIM)) {

							retorno.addAll(this.repositorioOrdemServico.pesquisarDistritoOperacionalPorRAPelaQuadraFace(idsRa));
						} else {

							retorno.addAll(this.repositorioOrdemServico.pesquisarDistritoOperacionalPorRAPelaQuadra(idsRa));
						}
					}
					colecao = this.repositorioOrdemServico.pesquisarDistritoOperacionalPorUnidade(unidadeLotacao.getId());

					retorno.addAll(this.removeObjetoRepetido(retorno, colecao));
					break;
				}
			}// fim do switch

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		return retorno;
	}

	/**
	 * Remove Objetos Repetidos na colecao passada Objetos: ServicoTipo
	 * ServicoPerfilTipo UnidadeOrganizacional Localidade SetorComercial
	 * DistritoOperacional
	 */
	private Collection removeObjetoRepetido(Collection oldCollection, Collection newCollection) {

		Iterator oldItera = oldCollection.iterator();
		while (oldItera.hasNext()) {
			Object old = (Object) oldItera.next();

			Iterator newItera = newCollection.iterator();

			while (newItera.hasNext()) {

				Object novo = (Object) newItera.next();

				if (old instanceof ServicoTipo) {

					if (((ServicoTipo) old).getId().intValue() == ((ServicoTipo) novo).getId().intValue()) {
						newItera.remove();
					}

				} else if (old instanceof ServicoPerfilTipo) {

					if (((ServicoPerfilTipo) old).getId().intValue() == ((ServicoPerfilTipo) novo).getId().intValue()) {
						newItera.remove();
					}

				} else if (old instanceof UnidadeOrganizacional) {

					if (((UnidadeOrganizacional) old).getId().intValue() == ((UnidadeOrganizacional) novo).getId().intValue()) {
						newItera.remove();
					}

				} else if (old instanceof Localidade) {

					if (((Localidade) old).getId().intValue() == ((Localidade) novo).getId().intValue()) {
						newItera.remove();
					}

				} else if (old instanceof SetorComercial) {
					if (((SetorComercial) old).getId().intValue() == ((SetorComercial) novo).getId().intValue()) {
						newItera.remove();
					}

				} else if (old instanceof DistritoOperacional) {

					if (((DistritoOperacional) old).getId().intValue() == ((DistritoOperacional) novo).getId().intValue()) {
						newItera.remove();
					}

				}
			}
		}
		return newCollection;
	}

	/**
	 * [UC0450] Pesquisar Ordem de Servico
	 * 
	 * [SB0003] Seleciona Ordem de Servico por Criterio de Seleção [SB0004]
	 * Seleciona Ordem de Servico por Situacao de Diagnostico [SB0005] Seleciona
	 * Ordem de Servico por Situacao de Acompanhamento pela Agencia [SB0006]
	 * Seleciona Ordem de Servico por Critério Geral
	 * 
	 * @author Rafael Pinto
	 * @date 07/09/2006
	 * 
	 * @param PesquisarOrdemServicoHelper
	 * 
	 * @return Collection<OrdemServico>
	 * @throws ControladorException
	 */
	public Collection<OrdemServico> pesquisarOrdemServicoElaborarProgramacao(PesquisarOrdemServicoHelper filtro) throws ControladorException {

		try {

			return this.repositorioOrdemServico.pesquisarOrdemServicoElaborarProgramacao(filtro);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0371]Inserir Equipe no sistema.
	 * 
	 * @author Leonardo Regis
	 * @date 25/07/2006
	 * 
	 * @param equipe
	 * @throws ControladorException
	 */
	public long inserirEquipe(Equipe equipe, Collection<EquipeComponentes> colecaoEquipeComponentes,
			Collection<EquipeEquipamentosEspeciais> colecaoEquipeEquipamentosEspeciais, Usuario usuario) throws ControladorException {

		// valida de o imei existe
		if (equipe.getNumeroImei() != null && !equipe.getNumeroImei().equals("")) {
			FiltroEquipe filtroEquipe = new FiltroEquipe();
			filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.NUMERO_IMEI, equipe.getNumeroImei()));
			filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.INDICADOR_USO, ConstantesSistema.SIM));
			Collection collEquipes = getControladorUtil().pesquisar(filtroEquipe, Equipe.class.getName());
			if (collEquipes != null && !collEquipes.isEmpty()) {
				throw new ControladorException("atencao.descricao_concatenada", null, "IMEI: " + equipe.getNumeroImei() + " informado para outra equipe.");
			}
		}

		try {
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(Operacao.OPERACAO_EQUIPE_INSERIR, new UsuarioAcaoUsuarioHelper(usuario,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

			Operacao operacao = new Operacao();
			operacao.setId(Operacao.OPERACAO_EQUIPE_INSERIR);

			OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
			operacaoEfetuada.setOperacao(operacao);

			// Registra operação
			equipe.setOperacaoEfetuada(operacaoEfetuada);
			equipe.adicionarUsuario(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(equipe);

			// Insere a equipe na base
			getControladorUtil().inserir(equipe);
			this.inserirEquipeComponentes(colecaoEquipeComponentes, equipe, usuario);
			this.inserirEquipeEquipamentosEspeciais(colecaoEquipeEquipamentosEspeciais, equipe, usuario);

			return equipe.getId();
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades da
	 * inserção da equipe.
	 * 
	 * [FS0007] Verificar quantidade de indicador de responsável Validar Carga
	 * Horária Máxima Validar Placa do Veículo
	 * 
	 * @author Leonardo Regis
	 * @date 25/07/2006
	 * 
	 * @param equipe
	 */
	public void validarInsercaoEquipe(Equipe equipe) throws ControladorException {
		// Verificar objeto a ser inserido na base.
		if (equipe != null) {
			// Verificar Carga Horária Máxima.
			int cargaHoraria = equipe.getCargaTrabalho() / 60;
			if (cargaHoraria > Equipe.CARGA_HORARIA_MAXIMA) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.inserir_equipe_carga_horaria_maxima", null, "");
			}
			// Validar Placa do Veículo
			if (equipe.getPlacaVeiculo() != null && !equipe.getPlacaVeiculo().equals("")) {
				if (equipe.getPlacaVeiculo().length() != 7) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("atencao.inserir_equipe_placa_veiculo_invalida", null, "");
				}
				// Padrões da expressão regular
				String LETRAS_PLACA_MASK = "^[a-zA-Z]*$";
				String DIGITOS_PLACA_MASK = "^[0-9]*$";
				// Pega os 3 primeiros caractes
				String letras = equipe.getPlacaVeiculo().substring(0, 3);
				String digitos = equipe.getPlacaVeiculo().substring(3, 7);
				// Compara a placa com as expressões regulares
				boolean letrasValidas = Util.validateMask(letras, LETRAS_PLACA_MASK);
				boolean digitosValidos = Util.validateMask(digitos, DIGITOS_PLACA_MASK);
				if (!letrasValidas || !digitosValidos) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("atencao.inserir_equipe_placa_veiculo_invalida", null, "");
				}
			}

			// Equipe com o mesmo nome
			FiltroEquipe filtro = new FiltroEquipe();
			filtro.adicionarParametro(new ComparacaoTexto(FiltroEquipe.NOME, equipe.getNome()));

			Collection colecao = getControladorUtil().pesquisar(filtro, Equipe.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				throw new ControladorException("atencao.inserir_equipe_mesmo_nome");
			}

		} else {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.inserir_equipe_inválida", null, "");
		}
	}

	/**
	 * Inserir Componentes da Equipe no sistema.
	 * 
	 * @author Leonardo Regis
	 * @date 25/07/2006
	 * 
	 * @param equipeComponentes
	 * @throws ControladorException
	 */
	public void inserirEquipeComponentes(Collection<EquipeComponentes> colecaoEquipeComponentes, Equipe equipe, Usuario usuario) throws ControladorException {

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(Operacao.OPERACAO_EQUIPE_INSERIR, new UsuarioAcaoUsuarioHelper(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_EQUIPE_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		for (Iterator iter = colecaoEquipeComponentes.iterator(); iter.hasNext();) {
			EquipeComponentes element = (EquipeComponentes) iter.next();
			element.setEquipe(equipe);
			// Registra operação
			element.setOperacaoEfetuada(operacaoEfetuada);
			element.adicionarUsuario(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(equipe);
			// Insere componente
			getControladorUtil().inserir(element);
		}

	}

	/**
	 * Este método se destina a validar todas as situações e particularidades da
	 * inserção de componentes da equipe.
	 * 
	 * [FS0003] Validar equipe componente já existente [FS0004] Verificar
	 * existência do funcionário [FS0006] Verificar quantidade de componentes da
	 * equipe em Tipo Perfil Serviço
	 * 
	 * @author Leonardo Regis
	 * @date 25/07/2006
	 * 
	 * @param equipeComponentes
	 */
	public void validarExibirInsercaoEquipeComponentes(Collection colecaoEquipeComponentes, EquipeComponentes equipeComponentes) throws ControladorException {
		// Verificar objeto a ser inserido na base.
		if (equipeComponentes != null) {
			// Testar se novo componente pode ser inserido na coleção
			if (colecaoEquipeComponentes != null && !colecaoEquipeComponentes.isEmpty()) {
				// Varre coleção de componentes da grid (ainda não inseridos na
				// base)
				for (Iterator iter = colecaoEquipeComponentes.iterator(); iter.hasNext();) {
					EquipeComponentes element = (EquipeComponentes) iter.next();
					// [FS0003] Validar equipe componente já existente
					if (element.getFuncionario() != null && equipeComponentes.getFuncionario() != null) {
						if (element.getFuncionario().getId().intValue() == equipeComponentes.getFuncionario().getId().intValue()) {
							sessionContext.setRollbackOnly();
							throw new ControladorException("atencao.inserir_equipe_componente_ja_informado", null, "");
						}
					}
					// [FS0007] Verificar quantidade de indicador de responsável
					if (equipeComponentes.getIndicadorResponsavel() == EquipeComponentes.INDICADOR_RESPONSAVEL_SIM) {
						if (element.getIndicadorResponsavel() == equipeComponentes.getIndicadorResponsavel()) {
							sessionContext.setRollbackOnly();
							throw new ControladorException("atencao.inserir_equipe_um_responsavel", null, "");
						}
					}
				}
			}
		} else {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.inserir_equipe_componente_invalido", null, "");
		}
	}

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades da
	 * inserção de componentes da equipe.
	 * 
	 * [FS0006] Verificar quantidade de componentes da equipe em Tipo Perfil
	 * Serviço Validar se possui algum responsável
	 * 
	 * @author Leonardo Regis
	 * @date 29/07/2006
	 * 
	 * @param equipeComponentes
	 * @throws ControladorException
	 */
	public void validarInsercaoEquipeComponentes(Collection colecaoEquipeComponentes) throws ControladorException {
		// Testar se novo componente pode ser inserido na coleção
		if (colecaoEquipeComponentes != null && !colecaoEquipeComponentes.isEmpty()) {
			boolean achouResponsavel = false;
			// Varre coleção de componentes da grid (ainda não inseridos na
			// base)
			for (Iterator iter = colecaoEquipeComponentes.iterator(); iter.hasNext();) {
				EquipeComponentes element = (EquipeComponentes) iter.next();
				// [FS0007] Verificar quantidade de indicador de responsável
				if (element.getIndicadorResponsavel() == EquipeComponentes.INDICADOR_RESPONSAVEL_SIM) {
					achouResponsavel = true;
					break;
				}
			}
			if (!achouResponsavel) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.inserir_equipe_nenhum_responsavel", null, "");
			}
		}
	}

	/**
	 * [UC0462] Obter Dados das Atividades da OS
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param idOS
	 * @return Collection<ObterDadosAtividadesOSHelper>
	 * @throws ControladorException
	 */
	public Collection<ObterDadosAtividadesOSHelper> obterDadosAtividadesOS(Integer idOS) throws ControladorException {
		Collection<ObterDadosAtividadesOSHelper> colecaoObterDadosAtividadesOSHelper = new ArrayList();
		ObterDadosAtividadesOSHelper obterDadosAtividadesOSHelper = null;
		try {
			Collection<OsAtividadeMaterialExecucao> colecaoOsAtividadeMaterialExecucao = this.repositorioOrdemServico
					.obterOsAtividadeMaterialExecucaoPorOS(idOS);
			if (colecaoOsAtividadeMaterialExecucao != null && !colecaoOsAtividadeMaterialExecucao.isEmpty()) {
				for (OsAtividadeMaterialExecucao material : colecaoOsAtividadeMaterialExecucao) {
					obterDadosAtividadesOSHelper = new ObterDadosAtividadesOSHelper();
					obterDadosAtividadesOSHelper.setMaterial(true);
					obterDadosAtividadesOSHelper.setAtividade(material.getOrdemServicoAtividade().getAtividade());
					obterDadosAtividadesOSHelper.setMaterial(material.getMaterial());
					obterDadosAtividadesOSHelper.setMaterialUnidade(material.getMaterial().getMaterialUnidade());
					obterDadosAtividadesOSHelper.setQtdeMaterial(material.getQuantidadeMaterial());
					colecaoObterDadosAtividadesOSHelper.add(obterDadosAtividadesOSHelper);
				}
			}
			Collection<OsExecucaoEquipe> colecaoOsExecucaoEquipe = this.repositorioOrdemServico.obterOsExecucaoEquipePorOS(idOS);
			if (colecaoOsExecucaoEquipe != null && !colecaoOsExecucaoEquipe.isEmpty()) {
				for (OsExecucaoEquipe periodo : colecaoOsExecucaoEquipe) {
					obterDadosAtividadesOSHelper = new ObterDadosAtividadesOSHelper();
					obterDadosAtividadesOSHelper.setPeriodo(true);
					obterDadosAtividadesOSHelper.setAtividade(periodo.getOsAtividadePeriodoExecucao().getOrdemServicoAtividade().getAtividade());
					obterDadosAtividadesOSHelper.setDataInicio(periodo.getOsAtividadePeriodoExecucao().getDataInicio());
					obterDadosAtividadesOSHelper.setDataFim(periodo.getOsAtividadePeriodoExecucao().getDataFim());
					obterDadosAtividadesOSHelper.setEquipe(periodo.getEquipe());
					colecaoObterDadosAtividadesOSHelper.add(obterDadosAtividadesOSHelper);
				}
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		return colecaoObterDadosAtividadesOSHelper;
	}

	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 11/09/2006
	 * 
	 * @param dataRoteiro
	 * @return collection
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> recuperaOSProgramacaoPorDataRoteiro(Date dataRoteiro) throws ControladorException {

		try {
			return this.repositorioOrdemServico.recuperaOSProgramacaoPorDataRoteiro(dataRoteiro);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0479] Gerar Débito da Ordem de Serviço
	 * 
	 * [FS0001] Verificar Existência da Ordem de Serviço
	 * 
	 * @author Leonardo Regis
	 * @date 11/09/2006
	 * 
	 * @param ordemServicoId
	 * @param tipoDebitoId
	 * @param valorDebito
	 * @param qtdeParcelas
	 * 
	 * @throws ControladorException
	 */
	public DebitoACobrar gerarDebitoOrdemServico(Integer ordemServicoId, Integer idDebitoTipo, BigDecimal valorDebito, int qtdeParcelas,
			String percentualCobranca, Usuario usuarioLogado) throws ControladorException {

		DebitoACobrar debitoACobrar = null;

		try {

			OrdemServico os = this.repositorioOrdemServico.recuperaOSPorId(ordemServicoId);

			/**
			 * Alterado por Arthur Carvalho Analista Aryed Lins Data: 26/01/2010
			 * Obs: Alteração temporaria
			 * 
			 * Verifica se o Imóvel tem o perfil = tarifa social e o valor do
			 * debito é =< 0
			 */

			boolean geraDebito = true;

			if (os.getImovel() != null && os.getImovel().getId() != null) {

				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.IMOVEL_PERFIL);
				filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, os.getImovel().getId()));

				Collection colecaoImovel = getControladorUtil().pesquisar(filtroImovel, Imovel.class.getName());

				if (colecaoImovel != null && !colecaoImovel.isEmpty()) {
					Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);

					if (imovel.getImovelPerfil().getId().toString().equals("4") && valorDebito.intValue() <= 0) {
						// NÃO GERA DÉBITO
						geraDebito = false;
					}
				}

			}
			if (geraDebito) {
				// [FS0001] Verificar Existência da Ordem de Serviço
				Imovel imovel = null;
				if (os == null) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("atencao.os_inexistente");
				} else {
					imovel = os.getImovel();
					if (imovel == null) {
						if (os.getRegistroAtendimento().getImovel() == null) {
							sessionContext.setRollbackOnly();
							throw new ControladorException("atencao.os_sem_imovel");
						} else {
							imovel = os.getRegistroAtendimento().getImovel();
						}
					}
				}

				// Alterado por Rafael Corrêa 05/11/2008
				FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
				filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.DEBITO_TIPO_ID, idDebitoTipo));
				filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.EMISSAO_GUIA_PAGAMENTO, os.getDataGeracao()));
				filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.IMOVEL_ID, imovel.getId()));

				Collection colecaoGuiasPagamento = getControladorUtil().pesquisar(filtroGuiaPagamento, GuiaPagamento.class.getName());

				if (colecaoGuiasPagamento == null || colecaoGuiasPagamento.isEmpty() && valorDebito != null && valorDebito.compareTo(BigDecimal.ZERO) == 1) {

					/*
					 * [FS0002] Verificar Existência do Tipo de Débito [FS0003]
					 * Validar Valor do Débito [FS0004] Validar Quantidade de
					 * Parcelas
					 */
					DebitoTipo debitoTipo = this.validacaoGerarDebito(idDebitoTipo, valorDebito, qtdeParcelas);

					// Calcula o valor da prestação
					BigDecimal[] valorCalculo = getControladorRegistroAtendimento().calcularValorPrestacaoAtendimentoPublico(
							os.getServicoTipo().getIndicadorCobrarJuros(), valorDebito, qtdeParcelas, percentualCobranca);

					BigDecimal valorPrestacao = valorCalculo[0];
					BigDecimal taxaJurosFinanciamento = valorCalculo[1];

					// Recalcula o valor total do débito
					valorDebito = valorPrestacao.multiply(new BigDecimal(qtdeParcelas));

					// ------------------------------------------------------------------------------------------------
					// Insere débito a cobrar geral
					DebitoACobrarGeral debitoACobrarGeral = inserirDebitoACobrarGeral();

					// Insere débito a cobrar
					debitoACobrar = inserirDebitoACobrar(valorDebito, qtdeParcelas, os, debitoTipo, debitoACobrarGeral, taxaJurosFinanciamento, null,
							usuarioLogado);

					// Recupera Categorias por Imóvel
					Collection<Categoria> colecaoCategoria = this.getControladorImovel().obterQuantidadeEconomiasCategoria(imovel);

					// Recupera Valores por Categorias
					Collection<BigDecimal> colecaoValoresCategorias = this.getControladorImovel().obterValorPorCategoria(colecaoCategoria, valorDebito);

					// Insere débito a cobrar por categoria
					inserirDebitoACobrarCategoria(colecaoCategoria, colecaoValoresCategorias, debitoACobrar);
					// ------------------------------------------------------------------------------------------------

				}
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return debitoACobrar;
	}

	/**
	 * [UC0479] Gerar Débito da Ordem de Serviço
	 * 
	 * Persiste Debito a Cobrar Categoria
	 * 
	 * @author Leonardo Regis
	 * @date 12/09/2006
	 * 
	 * @param colecaoCategoria
	 * @param colecaoValores
	 * @throws ControladorException
	 */
	public void inserirDebitoACobrarCategoria(Collection<Categoria> colecaoCategoria, Collection<BigDecimal> colecaoValoresCategorias,
			DebitoACobrar debitoACobrar) throws ControladorException {
		DebitoACobrarCategoria debitoACobrarCategoria = null;
		BigDecimal valorCategoria = new BigDecimal(0);
		DebitoACobrarCategoriaPK debitoACobrarCategoriaPK = null;
		Iterator icolecaoValoresPorCategoria = colecaoValoresCategorias.iterator();
		for (Categoria categoria : colecaoCategoria) {
			debitoACobrarCategoriaPK = new DebitoACobrarCategoriaPK();
			debitoACobrarCategoriaPK.setCategoria(categoria);
			debitoACobrarCategoriaPK.setDebitoACobrar(debitoACobrar);

			debitoACobrarCategoria = new DebitoACobrarCategoria();
			debitoACobrarCategoria.setComp_id(debitoACobrarCategoriaPK);
			// debitoACobrarCategoria.setCategoria(categoria);
			debitoACobrarCategoria.setQuantidadeEconomia(categoria.getQuantidadeEconomiasCategoria());
			valorCategoria = (BigDecimal) icolecaoValoresPorCategoria.next();
			debitoACobrarCategoria.setValorCategoria(valorCategoria);
			debitoACobrarCategoria.setUltimaAlteracao(new Date());
			// debitoACobrarCategoria.setDebitoACobrar(debitoACobrar);
			this.getControladorUtil().inserir(debitoACobrarCategoria);
		}
	}

	/**
	 * [UC0479] Gerar Débito da Ordem de Serviço
	 * 
	 * Persiste Debito a Cobrar Geral
	 * 
	 * @author Leonardo Regos
	 * @date 12/09/2006
	 * 
	 * @param debitoACobrar
	 * @throws ControladorException
	 */
	public DebitoACobrarGeral inserirDebitoACobrarGeral() throws ControladorException {

		DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
		debitoACobrarGeral.setIndicadorHistorico(DebitoACobrarGeral.INDICADOR_NAO_POSSUI_HISTORICO);
		debitoACobrarGeral.setUltimaAlteracao(new Date());
		this.getControladorUtil().inserir(debitoACobrarGeral);

		return debitoACobrarGeral;
	}

	/**
	 * [UC0479] Gerar Débito da Ordem de Serviço
	 * 
	 * Persiste Debito a Cobrar
	 * 
	 * @author Leonardo Regos
	 * @date 12/09/2006
	 * 
	 * @throws ControladorException
	 */
	public DebitoACobrar inserirDebitoACobrar(BigDecimal valorDebito, int qtdeParcelas, OrdemServico os, DebitoTipo debitoTipo,
			DebitoACobrarGeral debitoACobrarGeral, BigDecimal percentualTaxaJurosFinanciamento, RegistroAtendimento registroAtendimento, Usuario usuarioLogado)
			throws ControladorException {

		DebitoACobrar debitoACobrar = new DebitoACobrar();
		debitoACobrar.setId(debitoACobrarGeral.getId());
		debitoACobrar.setDebitoACobrarGeral(debitoACobrarGeral);

		Imovel imovel = null;
		if (os != null) {

			imovel = os.getImovel();

			if (imovel == null) {
				imovel = os.getRegistroAtendimento().getImovel();
			}

			debitoACobrar.setRegistroAtendimento(os.getRegistroAtendimento());
			debitoACobrar.setOrdemServico(os);
		} else {
			imovel = registroAtendimento.getImovel();

			debitoACobrar.setRegistroAtendimento(registroAtendimento);
			debitoACobrar.setOrdemServico(null);
		}

		SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

		debitoACobrar.setImovel(imovel);
		debitoACobrar.setDebitoTipo(debitoTipo);
		debitoACobrar.setGeracaoDebito(new Date());
		debitoACobrar.setAnoMesReferenciaDebito(sistemaParametro.getAnoMesFaturamento());
		debitoACobrar.setAnoMesCobrancaDebito(sistemaParametro.getAnoMesArrecadacao());

		// Alteracao CRC1389 Data:09/03/2009
		// Author: Rômulo Aurélio
		// Analista: Rosana Carvalho

		int anoMesAtual = Util.getAnoMesComoInt(new Date());

		if (sistemaParametro.getAnoMesFaturamento().compareTo(anoMesAtual) < 0) {

			debitoACobrar.setAnoMesReferenciaContabil(anoMesAtual);

		} else {
			debitoACobrar.setAnoMesReferenciaContabil(sistemaParametro.getAnoMesFaturamento());
		}

		debitoACobrar.setValorDebito(valorDebito);
		debitoACobrar.setNumeroPrestacaoDebito(new Integer(qtdeParcelas).shortValue());
		debitoACobrar.setNumeroPrestacaoCobradas(new Short("0"));
		debitoACobrar.setLocalidade(imovel.getLocalidade());
		debitoACobrar.setQuadra(imovel.getQuadra());

		debitoACobrar.setCodigoSetorComercial(imovel.getSetorComercial().getCodigo());

		debitoACobrar.setPercentualTaxaJurosFinanciamento(percentualTaxaJurosFinanciamento);

		debitoACobrar.setNumeroQuadra(imovel.getQuadra().getNumeroQuadra());
		debitoACobrar.setNumeroLote(imovel.getLote());
		debitoACobrar.setNumeroSubLote(imovel.getSubLote());
		debitoACobrar.setPercentualTaxaJurosFinanciamento(new BigDecimal(0));
		debitoACobrar.setFinanciamentoTipo(debitoTipo.getFinanciamentoTipo());
		debitoACobrar.setLancamentoItemContabil(debitoTipo.getLancamentoItemContabil());
		DebitoCreditoSituacao debitoCreditoSituacaoAtual = new DebitoCreditoSituacao();
		debitoCreditoSituacaoAtual.setId(DebitoCreditoSituacao.NORMAL);
		debitoACobrar.setDebitoCreditoSituacaoAtual(debitoCreditoSituacaoAtual);
		debitoACobrar.setDebitoCreditoSituacaoAnterior(null);
		debitoACobrar.setParcelamentoGrupo(null);
		CobrancaForma cobrancaForma = new CobrancaForma();
		cobrancaForma.setId(CobrancaForma.COBRANCA_EM_CONTA);
		debitoACobrar.setCobrancaForma(cobrancaForma);
		debitoACobrar.setUsuario(usuarioLogado);
		debitoACobrar.setUltimaAlteracao(new Date());

		this.getControladorUtil().inserir(debitoACobrar);

		return debitoACobrar;
	}

	/**
	 * Faz o controle de concorrencia da programacao roteiro
	 * 
	 * @author Rafael Pinto
	 * @throws ControladorException
	 */
	private void verificarProgramacaoRoteiroControleConcorrencia(ProgramacaoRoteiro programacaoRoteiro) throws ControladorException {

		FiltroProgramacaoRoteiro filtroProgramacaoRoteiro = new FiltroProgramacaoRoteiro();
		filtroProgramacaoRoteiro.adicionarParametro(new ParametroSimples(FiltroProgramacaoRoteiro.ID, programacaoRoteiro.getId()));

		Collection colecaoProgramacaoRoteiro = getControladorUtil().pesquisar(filtroProgramacaoRoteiro, ProgramacaoRoteiro.class.getName());

		if (colecaoProgramacaoRoteiro == null || colecaoProgramacaoRoteiro.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		ProgramacaoRoteiro programacaoRoteiroAtual = (ProgramacaoRoteiro) Util.retonarObjetoDeColecao(colecaoProgramacaoRoteiro);

		if (programacaoRoteiroAtual.getUltimaAlteracao().after(programacaoRoteiro.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}
	}

	/**
	 * Faz o controle de concorrencia da Ordem de Servico Programação
	 * 
	 * @author Rafael Pinto
	 * @throws ControladorException
	 */
	private void verificarOrdemServicoProgramacaoControleConcorrencia(OrdemServicoProgramacao ordemServicoProgramacao) throws ControladorException {

		OrdemServicoProgramacao ordemServicoProgramacaoAtual;
		try {
			ordemServicoProgramacaoAtual = this.repositorioOrdemServico.pesquisarOSProgramacaoPorId(ordemServicoProgramacao.getId());

			if (ordemServicoProgramacaoAtual == null) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}

			if (ordemServicoProgramacaoAtual.getUltimaAlteracao().after(ordemServicoProgramacao.getUltimaAlteracao())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);

		}

	}

	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 13/09/2006
	 * 
	 * @param colecaoOrdemServicoProgramacao
	 * @throws ControladorException
	 */
	public void elaboraRoteiro(Collection colecaoOrdemServicoProgramacao, Usuario usuarioLogado) throws ControladorException {

		Iterator iter = colecaoOrdemServicoProgramacao.iterator();

		while (iter.hasNext()) {
			OSProgramacaoHelper helper = (OSProgramacaoHelper) iter.next();

			OrdemServicoProgramacao ordemServicoProgramacao = helper.getOrdemServicoProgramacao();
			ProgramacaoRoteiro programacao = ordemServicoProgramacao.getProgramacaoRoteiro();

			// Filtro para programacao roteiro
			FiltroProgramacaoRoteiro filtroProgramacaoRoteiro = new FiltroProgramacaoRoteiro();

			filtroProgramacaoRoteiro.adicionarParametro(new ParametroSimples(FiltroProgramacaoRoteiro.DATA_ROTEIRO, programacao.getDataRoteiro()));

			filtroProgramacaoRoteiro.adicionarParametro(new ParametroSimples(FiltroProgramacaoRoteiro.UNIDADE_ORGANIZACIONAL_ID, programacao
					.getUnidadeOrganizacional().getId()));

			Collection colecaoProgramacao = this.getControladorUtil().pesquisar(filtroProgramacaoRoteiro, ProgramacaoRoteiro.class.getName());

			// [SB00015] - Inclui ou Altera Roteiro
			if (colecaoProgramacao != null && !colecaoProgramacao.isEmpty()) {
				programacao = (ProgramacaoRoteiro) Util.retonarObjetoDeColecao(colecaoProgramacao);

				programacao.setUltimaAlteracao(new Date());

				this.verificarProgramacaoRoteiroControleConcorrencia(programacao);

				this.getControladorUtil().atualizar(programacao);
			} else {

				programacao.setUltimaAlteracao(new Date());
				this.getControladorUtil().inserir(programacao);

			}

			// Colocado por Raphael Rossiter em 08/02/2007 (Mudança no UC)
			OrdemServico ordemServico = this.pesquisarOrdemServico(ordemServicoProgramacao.getOrdemServico().getId());
			ordemServico.setIndicadorProgramada(OrdemServico.PROGRAMADA);

			this.atualizarOrdemServico(ordemServico, usuarioLogado);

			// [SB00016] - Inclui Programacao
			ordemServicoProgramacao.setProgramacaoRoteiro(programacao);
			ordemServicoProgramacao.setUsuarioFechamento(null);
			ordemServicoProgramacao.setOsProgramNaoEncerMotivo(null);
			ordemServicoProgramacao.setIndicadorAtivo(ConstantesSistema.SIM);
			ordemServicoProgramacao.setUltimaAlteracao(new Date());
			ordemServicoProgramacao.setSituacaoFechamento(ConstantesSistema.NAO);

			/*
			 * 04/09/2013
			 * 
			 * Alteração para setar o campo indicadorAcompanhamentoServico que
			 * não pode ser nulo
			 */
			ordemServicoProgramacao.setIndicadorAcompanhamentoServico(ConstantesSistema.NAO);

			this.getControladorUtil().inserir(ordemServicoProgramacao);
		}
	}

	/**
	 * [UC0462] Obter Dados das Atividades da OS
	 * 
	 * @author Leonardo Regis
	 * @date 14/09/2006
	 * 
	 * @param idOS
	 * @param idAtividade
	 * @param tipoAtividade
	 * @return Collection<ObterDadosAtividadeOSHelper>
	 * @throws ControladorException
	 */
	public Collection<ObterDadosAtividadeOSHelper> obterDadosAtividadeOS(Integer idOS, Integer idAtividade, int tipoAtividade) throws ControladorException {
		Collection<ObterDadosAtividadeOSHelper> colecaoObterDadosAtividadeOSHelper = new ArrayList();
		ObterDadosAtividadeOSHelper obterDadosAtividadeOSHelper = null;
		try {
			if (tipoAtividade == ObterDadosAtividadeOSHelper.INDICADOR_MATERIAL.intValue()) {
				Collection<OsAtividadeMaterialExecucao> colecaoOsAtividadeMaterialExecucao = this.repositorioOrdemServico
						.obterOsAtividadeMaterialExecucaoPorOS(idOS, idAtividade);
				if (colecaoOsAtividadeMaterialExecucao != null && !colecaoOsAtividadeMaterialExecucao.isEmpty()) {
					for (OsAtividadeMaterialExecucao material : colecaoOsAtividadeMaterialExecucao) {
						obterDadosAtividadeOSHelper = new ObterDadosAtividadeOSHelper();
						obterDadosAtividadeOSHelper.setMaterial(true);
						obterDadosAtividadeOSHelper.setAtividade(material.getOrdemServicoAtividade().getAtividade());
						obterDadosAtividadeOSHelper.setMaterialUtilizado(material.getMaterial());
						obterDadosAtividadeOSHelper.setMaterialUnidade(material.getMaterial().getMaterialUnidade());
						obterDadosAtividadeOSHelper.setQtdeMaterial(material.getQuantidadeMaterial());
						colecaoObterDadosAtividadeOSHelper.add(obterDadosAtividadeOSHelper);
					}
				}

			} else if (tipoAtividade == ObterDadosAtividadeOSHelper.INDICADOR_PERIODO.intValue()) {
				Collection<OsExecucaoEquipe> colecaoOsExecucaoEquipe = this.repositorioOrdemServico.obterOsExecucaoEquipePorOS(idOS, idAtividade);
				if (colecaoOsExecucaoEquipe != null && !colecaoOsExecucaoEquipe.isEmpty()) {
					for (OsExecucaoEquipe periodo : colecaoOsExecucaoEquipe) {
						obterDadosAtividadeOSHelper = new ObterDadosAtividadeOSHelper();
						obterDadosAtividadeOSHelper.setPeriodo(true);
						obterDadosAtividadeOSHelper.setAtividade(periodo.getOsAtividadePeriodoExecucao().getOrdemServicoAtividade().getAtividade());
						obterDadosAtividadeOSHelper.setDataInicio(periodo.getOsAtividadePeriodoExecucao().getDataInicio());
						obterDadosAtividadeOSHelper.setDataFim(periodo.getOsAtividadePeriodoExecucao().getDataFim());
						obterDadosAtividadeOSHelper.setEquipe(periodo.getEquipe());
						colecaoObterDadosAtividadeOSHelper.add(obterDadosAtividadeOSHelper);
					}
				}
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		return colecaoObterDadosAtividadeOSHelper;
	}

	/**
	 * [UC0460] Obter Carga de Trabalho da Equipe
	 * 
	 * @author Leonardo Regis
	 * @date 14/09/2006
	 * 
	 * @param equipeId
	 * @param colecaoIdOSProgramadas
	 * @param colecaoOSDistribuidasPorEquipe
	 * @param dataFinalProgramacao
	 * @param dataRoteiro
	 * @return valor da carga de trabalho da equipe
	 * @throws ControladorException
	 */
	public ObterCargaTrabalhoEquipeHelper obterCargaTrabalhoEquipe(Integer equipeId, Collection<Integer> colecaoIdOSProgramadas,
			Collection<ObterOSDistribuidasPorEquipeHelper> colecaoOSDistribuidasPorEquipe, Date dataRoteiro) throws ControladorException {

		ObterCargaTrabalhoEquipeHelper obterCargaTrabalhoEquipeHelper = null;

		try {

			if (dataRoteiro == null) {
				dataRoteiro = new Date();
			}

			Equipe equipe = this.obterDadosEquipe(equipeId).getEquipe();

			OrdemServico ordemServicoProgramada = null;

			int tempoMedioExecucao = 0;
			int qtdeProgramacoesAtivas = 0;

			int cargaPrevistaEquipe = 0;
			int qtdeHorasExecutadas = 0;

			Collection<OrdemServicoProgramacao> colecaoOSP = null;
			Collection<OsAtividadePeriodoExecucao> colecaoOsExecucaoEquipe = null;

			if (colecaoIdOSProgramadas != null && !colecaoIdOSProgramadas.isEmpty()) {

				for (Integer osId : colecaoIdOSProgramadas) {

					ordemServicoProgramada = this.recuperaOSPorId(osId);

					if (ordemServicoProgramada.getServicoTipo() == null) {
						sessionContext.setRollbackOnly();
						throw new ControladorException("atencao.os_sem_tipo_servico", null, osId + "");
					}

					tempoMedioExecucao += new Short(ordemServicoProgramada.getServicoTipo().getTempoMedioExecucao()).intValue();

					colecaoOSP = repositorioOrdemServico.obterProgramacoesAtivasPorOs(osId);

					if (colecaoOSP != null && !colecaoOSP.isEmpty()) {
						qtdeProgramacoesAtivas += colecaoOSP.size();
					} else {
						sessionContext.setRollbackOnly();
						throw new ControladorException("atencao.os_sem_programacao_ativa", null, osId + "");
					}

					colecaoOsExecucaoEquipe = this.repositorioOrdemServico.obterOsAtividadePeriodoExecucaoPorOS(osId, dataRoteiro);

					qtdeHorasExecutadas = 0;
					if (colecaoOsExecucaoEquipe != null && !colecaoOsExecucaoEquipe.isEmpty()) {
						for (OsAtividadePeriodoExecucao execucaoPeriodo : colecaoOsExecucaoEquipe) {
							qtdeHorasExecutadas += Util.obterQtdeHorasEntreDatas(execucaoPeriodo.getDataInicio(), execucaoPeriodo.getDataFim());
						}
					}
					cargaPrevistaEquipe += (tempoMedioExecucao / qtdeProgramacoesAtivas) - qtdeHorasExecutadas * 60;
				}
			}

			tempoMedioExecucao = 0;

			int qtdeDiasTrabalhados = 0;
			int qtdeEquipes = 0;

			if (colecaoOSDistribuidasPorEquipe != null && !colecaoOSDistribuidasPorEquipe.isEmpty()) {
				for (ObterOSDistribuidasPorEquipeHelper osDistribuidas : colecaoOSDistribuidasPorEquipe) {

					ordemServicoProgramada = this.recuperaOSPorId(osDistribuidas.getIdOS());

					if (ordemServicoProgramada.getServicoTipo() == null) {
						sessionContext.setRollbackOnly();
						throw new ControladorException("atencao.os_sem_tipo_servico", null, osDistribuidas.getIdOS() + "");
					}

					tempoMedioExecucao += new Short(ordemServicoProgramada.getServicoTipo().getTempoMedioExecucao()).intValue();

					if (osDistribuidas.getColecaoEquipe() != null && !osDistribuidas.getColecaoEquipe().isEmpty()) {
						qtdeEquipes = osDistribuidas.getColecaoEquipe().size();
					} else {
						qtdeEquipes = 0;
					}

					qtdeDiasTrabalhados = Util.obterQuantidadeDiasEntreDuasDatas(dataRoteiro, osDistribuidas.getDataFinalProgramacao());

					qtdeDiasTrabalhados = (qtdeDiasTrabalhados + 1) * qtdeEquipes;

					colecaoOsExecucaoEquipe = this.repositorioOrdemServico.obterOsAtividadePeriodoExecucaoPorOS(osDistribuidas.getIdOS(), dataRoteiro);

					qtdeHorasExecutadas = 0;

					if (colecaoOsExecucaoEquipe != null && !colecaoOsExecucaoEquipe.isEmpty()) {
						for (OsAtividadePeriodoExecucao execucaoPeriodo : colecaoOsExecucaoEquipe) {
							qtdeHorasExecutadas += Util.obterQtdeHorasEntreDatas(execucaoPeriodo.getDataInicio(), execucaoPeriodo.getDataFim());
						}
					}
					cargaPrevistaEquipe += (tempoMedioExecucao / qtdeDiasTrabalhados) - qtdeHorasExecutadas * 60;
				}
			}

			obterCargaTrabalhoEquipeHelper = new ObterCargaTrabalhoEquipeHelper();

			double percCTP = 0;
			if (cargaPrevistaEquipe > 0) {

				double valorDividido = cargaPrevistaEquipe / equipe.getCargaTrabalho().doubleValue();
				percCTP = valorDividido * 100;
			} else {
				percCTP = 100;
			}

			BigDecimal percentualCargaTrabalhoPrevista = new BigDecimal(percCTP);

			String bigFormatado = Util.formataBigDecimal(percentualCargaTrabalhoPrevista, 2, true);

			obterCargaTrabalhoEquipeHelper.setPercentualCargaTrabalhoPrevista(new BigDecimal((bigFormatado.replace(".", "")).replace(",", ".")));

			int somatorioHorasEquipe = 0;

			colecaoOsExecucaoEquipe = this.repositorioOrdemServico.obterOsAtividadePeriodoExecucaoPorEquipe(equipeId, dataRoteiro);

			for (OsAtividadePeriodoExecucao execucao : colecaoOsExecucaoEquipe) {
				somatorioHorasEquipe += Util.obterQtdeHorasEntreDatas(execucao.getDataInicio(), execucao.getDataFim());
			}

			BigDecimal percentualCargaRealizada = new BigDecimal(0);
			if (cargaPrevistaEquipe > 0) {
				percentualCargaRealizada = new BigDecimal((somatorioHorasEquipe * 60) / cargaPrevistaEquipe * 100);
			} else {
				percentualCargaRealizada = new BigDecimal(100 + Math.abs(cargaPrevistaEquipe));
			}

			bigFormatado = Util.formataBigDecimal(percentualCargaRealizada, 2, true);
			obterCargaTrabalhoEquipeHelper.setPercentualCargaRealizada(new BigDecimal(bigFormatado.replace(',', '.')));

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		return obterCargaTrabalhoEquipeHelper;
	}

	/**
	 * [UC0457] Encerra Ordem de Serviço
	 * 
	 * [FS0001] - Verificar Unidade do Usuário
	 * 
	 * @author Sávio Luiz
	 * @date 18/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void verificarUnidadeUsuario(Integer numeroOS, Usuario usuarioLogado) throws ControladorException {
		Integer numeroRA = null;
		try {
			numeroRA = repositorioOrdemServico.pesquisarRAOrdemServico(numeroOS);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (numeroRA != null) {

			UnidadeOrganizacional unidadeAtual = getControladorRegistroAtendimento().obterUnidadeAtualRA(numeroRA);

			Short indManutencaoRA = null;
			if (unidadeAtual != null && !unidadeAtual.equals("")) {
				indManutencaoRA = getControladorRegistroAtendimento().obterIndicadorAutorizacaoManutencaoRA(unidadeAtual.getId(), usuarioLogado.getId());
			}
			// caso o indicador de manutençaõ de RA seja igual a 2 - NÃO
			if (indManutencaoRA != null && indManutencaoRA.equals(RegistroAtendimento.INDICADOR_MANUTENCAO_RA_NAO)) {
				throw new ControladorException("atencao.nao_possivel_encerramento_os", null, unidadeAtual.getDescricao());
			}
		} else {

			SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

			// Caso a empresa concessionária faça a opção por validar a
			// localidade no encerramento da OS
			String indicadorValidarLocalizacao = sistemaParametro.getIndicadorValidarLocalizacaoEncerramentoOS().toString();
			if (indicadorValidarLocalizacao.equals("1")) {

				FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
				filtroOrdemServico.adicionarParametro(new ParametroSimples(FiltroOrdemServico.ID, numeroOS));
				filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
				filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade.gerenciaRegional");
				filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("unidadeAtual");
				filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("unidadeAtual.unidadeSuperior");
				Collection colecaoOrdemServico = this.getControladorUtil().pesquisar(filtroOrdemServico, OrdemServico.class.getName());

				OrdemServico ordemServico = (OrdemServico) Util.retonarObjetoDeColecao(colecaoOrdemServico);

				Short indicadorManutencaoOS = obterIndicadorAutorizacaoManutencaoOS(usuarioLogado.getId(), ordemServico);

				// Caso Indicador Manutenção retorne NÂO
				if (indicadorManutencaoOS != null && indicadorManutencaoOS.toString().equals("2")) {
					throw new ControladorException("atencao.os_foi_gerada_por_outra_unidade_organizacional", null, ordemServico.getUnidadeAtual()
							.getDescricao());
				} else if (indicadorManutencaoOS != null && indicadorManutencaoOS.toString().equals("3")) {
					throw new ControladorException("atencao.os_foi_gerada_por_outra_localidade", null, ordemServico.getImovel().getLocalidade().getDescricao());
				}

			}
		}
	}

	/**
	 * [UC0457] Encerra Ordem de Serviço
	 * 
	 * [FS0006] - Verificar Origem do Encerramento da Ordem de Serviço
	 * 
	 * @author Sávio Luiz
	 * @date 18/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void verificarOrigemEncerramentoOS(Integer numeroOS, Date dataEncerramento) throws ControladorException {
		Integer numeroOSVerificada = null;
		if (dataEncerramento == null || dataEncerramento.equals("")) {
			try {
				numeroOSVerificada = repositorioOrdemServico.pesquisarOSProgramacaoAtiva(numeroOS);
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}
			if (numeroOSVerificada != null && !numeroOSVerificada.equals("")) {
				throw new ControladorException("atencao.origem_encerramento_os");
			}
		}
	}

	/**
	 * [UC0457] Encerra Ordem de Serviço
	 * 
	 * [FS0002] - Validar Tipo Serviço [FS0004] - Verificar preenchimento dos
	 * campos [FS0007] - Validar Data de Encerramento [FS0008] - Validar Data do
	 * roteiro
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 29/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void validarCamposEncerrarOS(String indicadorExecucao, String numeroOS, String motivoEncerramento, String dataEncerramento,
			Collection colecaoManterDadosAtividadesOrdemServicoHelper, String tipoServicoReferenciaId, String indicadorPavimento, String pavimento,
			String idTipoRetornoOSReferida, String indicadorDeferimento, String indicadorTrocaServicoTipo, String idServicoTipo, String dataRoteiro,
			String idRA, String indicadorVistoriaServicoTipo, String codigoRetornoVistoriaOs, String indicadorDiagnostico, String observacaoEncerramento,
			Usuario usuario) throws ControladorException {

		// caso o motivo de encerramento esteja nulo
		if (motivoEncerramento == null || motivoEncerramento.equals("")) {
			throw new ControladorException("atencao.required", null, "Motivo de Encerramento");
		}

		// caso não exista a data de encerramento então a data de encerramento
		// será a data atual
		if (dataEncerramento != null && !dataEncerramento.equals("")) {
			// [FS0007] - Validar data de encerramento
			if (Util.validarDiaMesAno(dataEncerramento)) {
				throw new ControladorException("atencao.data.invalida", null, "Data de Encerramento");
			}
			Date dataEncerramentoDate = Util.converteStringParaDate(dataEncerramento);
			if (dataEncerramentoDate.after(new Date())) {
				throw new ControladorException("atencao.data.maior.data.corrente", null, "Data de Encerramento");
			}

			// ..........................................................................................
			// alterado por Yara Taciane - 18/06/2007 - Início
			// Solicitado por Denys Guimarães.
			// ..........................................................................................

			// caso dataEncerramento seja menor que dataCorrente menos
			// quantidade de dias parametros.
			Integer numOS = Util.converterStringParaInteger(numeroOS);
			try {
				Date dataGeracaoOS = Util.getData(repositorioOrdemServico.obterDataGeracaOS(numOS));

				SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

				/*
				 * Felipe Santos
				 * 
				 * Não permitir encerramento de OS com data inferior a data
				 * corrente menos os dias retroativos.
				 */
				Date dataCorrente = Util.getData(new Date());
				Date xData = Util.subtrairNumeroDiasDeUmaData(dataCorrente, sistemaParametro.getNumeroDiasEncerramentoOrdemServico());

				// -1 se a data1 for menor que a data2, 0 se as datas forem
				// iguais,
				// 1 se a data1 for maior que a data2.
				if (Util.compararData(dataEncerramentoDate, dataGeracaoOS) == -1) {
					throw new ControladorException("atencao.data_encerramento_anterior_permitido", null, "Data de Encerramento");
				}

				if (Util.compararData(dataEncerramentoDate, xData) == -1) {
					throw new ControladorException("atencao.data_encerramento_anterior_permitido", null, "Data de Encerramento");
				}

				// Verifica se é uma ordem de serviço seletiva.
				Integer retorno = repositorioOrdemServico.verificarOrdemServicoSeletiva(numOS);
				// Verifica se o usuário tem permissão especial.
				boolean permissaoEspecial = getControladorPermissaoEspecial().verificarPermissaoInformarDataEncOSAnteriorDataCorrente(usuario);
				// Caso seja uma ordem de serviço seletiva.
				if (retorno != null) {

					// Obtem data de data corrente menos a quantidade de dias do
					// parâmetro.
					Date yData = Util.subtrairNumeroDiasDeUmaData(dataCorrente, sistemaParametro.getNumeroDiasEncerramentoOSSeletiva());

					// Caso dataEncerramento esteja menor que data corrente
					// menos a quantidade de dias
					// e o usuário NÃO tenha permissão especial.
					if (Util.compararData(dataEncerramentoDate, yData) == -1 && permissaoEspecial == false) {
						throw new ControladorException("atencao.data_encerramento_anterior_permitido_os_seletiva", null, "Data de Encerramento");
					}
				}

			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			// ..........................................................................................
			// alterado por Yara Taciane - 18/06/2007 - Fim
			// ..........................................................................................

		} else {
			throw new ControladorException("atencao.required", null, "Data de Encerramento");
		}
		// caso não exista a data de encerramento então a data de encerramento
		// será a data atual
		if (dataRoteiro != null && !dataRoteiro.equals("")) {
			// [FS0008] - Validar data de roteiro
			if (Util.validarDiaMesAno(dataRoteiro)) {
				throw new ControladorException("atencao.data.invalida", null, "Data de Encerramento");
			}
			Date dataRoteiroDate = Util.converteStringParaDate(dataRoteiro);
			Date dataEncerramentoDate = Util.converteStringParaDate(dataEncerramento);
			if (dataEncerramentoDate.after(dataRoteiroDate)) {
				throw new ControladorException("atencao.data_encerramento_maior_data_roteiro", null);
			}
		}

		// indicador execução seja diferente de nulo
		if (indicadorExecucao != null && !indicadorExecucao.equals("")) {
			short indicadorExecucaoShort = Short.parseShort(indicadorExecucao);

			// indicador execução seja igual a sim(1)
			if (indicadorExecucaoShort == AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_SIM) {

				try {
					if (numeroOS != null && !numeroOS.equals("")) {
						Short indicadorAtividade = repositorioOrdemServico.pesquisarIndAtividadeServicoTipoOS(new Integer(numeroOS));

						boolean temPermissaoEspecial = getControladorPermissaoEspecial().verificarPermissaoEspecial(
								PermissaoEspecial.ENCERRAR_ORDEM_SERVICO_SEM_ATIVIDADES, usuario);

						if (!temPermissaoEspecial && indicadorAtividade.equals(ConstantesSistema.SIM)) {
							if (colecaoManterDadosAtividadesOrdemServicoHelper == null || colecaoManterDadosAtividadesOrdemServicoHelper.isEmpty()) {

								throw new ControladorException("atencao.required", null, "Atividades");
							}
						}
					}

				} catch (ErroRepositorioException e) {
					throw new ControladorException("erro.sistema", e);
				}

				// se o serviço tipo referencia seja igual a nulo
				if (tipoServicoReferenciaId == null || tipoServicoReferenciaId.equals("")) {
					// [SB0002] - Encerrar com execução e sem referência

					// caso o indicador de pavimento esteja igual a sim e o
					// pavimento não
					// esteja preenchido então
					// if (indicadorPavimento != null
					// && !indicadorPavimento.equals("")) {
					// short indicadorPavimentoShort = new Short(
					// indicadorPavimento);
					// if (indicadorPavimentoShort ==
					// ServicoTipo.INDICADOR_PAVIMENTO_SIM) {
					//
					// if ( pavimento == null || pavimento.equals("") ) {
					// throw new ControladorException(
					// "atencao.required", null, "Pavimento");
					// }
					// }
					// }

				} else {
					// [SB0003] - Encerrar com execução e com referência

					if (idTipoRetornoOSReferida == null || idTipoRetornoOSReferida.equals("")) {
						throw new ControladorException("atencao.required", null, "Tipo de Retorno Referida");
					}
					if (indicadorDeferimento != null && !indicadorDeferimento.equals("")) {
						short indDeferimento = Short.parseShort(indicadorDeferimento);
						// se indicador deferimento for igual a sim(1)
						if (indDeferimento == OsReferidaRetornoTipo.INDICADOR_DEFERIMENTO_SIM) {
							// 9.1.1 caso o indicador de pavimento esteja igual
							// a sim e o
							// pavimento não
							// esteja preenchido então
							if (indicadorPavimento != null && !indicadorPavimento.equals("")) {
								short indicadorPavimentoShort = new Short(indicadorPavimento);
								if (indicadorPavimentoShort == ServicoTipo.INDICADOR_PAVIMENTO_SIM) {

									if (pavimento == null || pavimento.equals("")) {
										throw new ControladorException("atencao.required", null, "Pavimento");
									}
								}
							}
							// 9.1.2 caso o indicador de troca se serviço da
							// tabela os
							// referida
							// retorno tipo seja diferente de nula e igual a sim
							if (indicadorTrocaServicoTipo != null && !indicadorTrocaServicoTipo.equals("")) {
								short indicadorTrocaServicoTipoShort = new Short(indicadorTrocaServicoTipo);
								if (indicadorTrocaServicoTipoShort == OsReferidaRetornoTipo.INDICADOR_TROCA_SERVICO_SIM) {
									if (idServicoTipo == null || idServicoTipo.equals("")) {
										throw new ControladorException("atencao.required", null, "Tipo de Serviço");
									} else {
										// [FS0002] - Validar Tipo de Serviço
										// Caso já exista OS para RA informado
										// com o mesmo tipo de serviço
										// selecionado
										if (idRA != null && !idRA.equals("")) {
											try {
												Object[] parmsRAServicoTipo = repositorioOrdemServico.verificarExistenciaOSEncerrado(new Integer(idRA),
														new Integer(idServicoTipo));
												if (parmsRAServicoTipo != null && !parmsRAServicoTipo.equals("")) {
													Integer idOSNaBase = null;
													String descricaoServicoTipo = null;
													if (parmsRAServicoTipo[0] != null) {
														idOSNaBase = (Integer) parmsRAServicoTipo[0];
													}
													if (parmsRAServicoTipo[1] != null) {
														descricaoServicoTipo = (String) parmsRAServicoTipo[1];
													}
													if (idOSNaBase != null) {
														throw new ControladorException("atencao.ordem_servico_com_ra", null, numeroOS, idRA != null ? "" + idRA
																: "", descricaoServicoTipo);
													}
												}

												Integer idServTipoRef = repositorioOrdemServico.verificarExistenciaServicoTipoReferencia(new Integer(
														idServicoTipo));
												if (idServTipoRef != null && !idServTipoRef.equals("")) {
													throw new ControladorException("atencao.existe_tipo_servico_referencia_os");
												}

											} catch (ErroRepositorioException e) {
												throw new ControladorException("erro.sistema", e);
											}
										}
										// tipo de serviço selecionado tenha um
										// tipo de serviço referência
									}

								}
							}
						}
					}

				}
			}

		}

		// caso a ordem de serviço tenha o tipo de serviço de referência de
		// DIAGNÓSTICO,
		// exigir o Parecer de Encerramento
		if (indicadorDiagnostico != null && indicadorDiagnostico.equalsIgnoreCase(ServicoTipoReferencia.INDICADOR_DIAGNOSTICO_ATIVO.toString())
				&& (observacaoEncerramento == null || observacaoEncerramento.equalsIgnoreCase(""))) {
			throw new ControladorException("atencao.indicador_diagnostico_sim");
		}

		if (observacaoEncerramento != null && !observacaoEncerramento.equals("") && observacaoEncerramento.length() > 400) {

			String[] msg = new String[2];
			msg[0] = "Parecer";
			msg[1] = "400";

			throw new ControladorException("atencao.execedeu_limit_observacao", null, msg);
		}

	}

	/**
	 * [UC0457] Encerra Ordem de Serviço
	 * 
	 * [SB0001] - Encerrar sem execução
	 * 
	 * @author Sávio Luiz
	 * @date 18/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void encerrarOSSemExecucao(Integer numeroOS, Date dataEncerramento, Usuario usuarioLogado, String motivoEncerramento, Date ultimaAlteracao,
			String parecerEncerramento, OrdemServico osFiscalizacao, String indicadorVistoriaServicoTipo, String codigoRetornoVistoriaOs,
			OrdemServicoBoletim ordemServicoBoletim, Short indicadorServicoAceito) throws ControladorException {

		Calendar dataEncerramentoParametro = Calendar.getInstance();
		dataEncerramentoParametro.setTime(dataEncerramento);
		OrdemServico osNaBase = null;
		if (ultimaAlteracao != null && !ultimaAlteracao.equals("")) {
			// controle de transação
			FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();

			filtroOrdemServico.adicionarParametro(new ParametroSimples(FiltroOrdemServico.ID, numeroOS));

			filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.COBRANCA_DOCUMENTO);
			filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.ATENDIMENTO_MOTIVO_ENCERRAMENTO);

			Collection colecaoOS = getControladorUtil().pesquisar(filtroOrdemServico, OrdemServico.class.getName());

			if (colecaoOS == null || colecaoOS.isEmpty()) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}

			osNaBase = (OrdemServico) colecaoOS.iterator().next();

			if (osNaBase.getUltimaAlteracao().after(ultimaAlteracao)) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}
			// fim controle transação
		}
		Integer idMotivoEncerramento = null;
		// caso o motivo de encerramento esteja nulo
		if (motivoEncerramento == null || motivoEncerramento.equals("")) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.required", null, "Motivo de Encerramento");
		} else {
			idMotivoEncerramento = Util.converterStringParaInteger(motivoEncerramento);
		}

		if (parecerEncerramento != null && !parecerEncerramento.equals("") && parecerEncerramento.length() > 400) {

			String[] msg = new String[2];
			msg[0] = "Parecer";
			msg[1] = "400";

			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.execedeu_limit_observacao", null, msg);
		}

		// caso não exista a data de encerramento então a data de encerramento
		// será a data atual
		if (dataEncerramento == null || dataEncerramento.equals("")) {
			dataEncerramento = new Date();
		}

		if (indicadorVistoriaServicoTipo != null && !indicadorVistoriaServicoTipo.equals("")) {
			if (indicadorVistoriaServicoTipo.equals(ServicoTipo.INDICADOR_VISTORIA_SIM)) {
				if (codigoRetornoVistoriaOs == null || codigoRetornoVistoriaOs.equals("")) {
					throw new ControladorException("atencao.required", null, "Retorno Vistoria");
				}
			}
		}
		try {
			// Preparando objeto para o registrar transação
			AtendimentoMotivoEncerramento atendimentoMotivo = new AtendimentoMotivoEncerramento();
			atendimentoMotivo.setId(idMotivoEncerramento);
			osNaBase.setAtendimentoMotivoEncerramento(atendimentoMotivo);
			osNaBase.setDataEncerramento(dataEncerramento);
			osNaBase.setDescricaoParecerEncerramento(parecerEncerramento);

			// ------------ REGISTRAR TRANSAÇÃO----------------------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(Operacao.OPERACAO_ORDEM_SERVICO_ENCERRAR, osNaBase.getId(), osNaBase.getId(),
					new UsuarioAcaoUsuarioHelper(usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			registradorOperacao.registrarOperacao(osNaBase);
			getControladorTransacao().registrarTransacao(osNaBase);
			// ------------ REGISTRAR TRANSAÇÃO----------------------------

			repositorioOrdemServico.atualizarParmsOS(numeroOS, idMotivoEncerramento, dataEncerramento, parecerEncerramento, codigoRetornoVistoriaOs);

			// Alterado por Francisco - 22/05/08, por conta do Resumo de Ações
			// de cobrança
			// O encerramento da OS atualiza o documento de cobranca
			// correspondente
			// Analista: Ana Breda
			getControladorCobranca().atualizarCobrancaDocumentoAposEncerrarOS(osNaBase);

			// inseri a tabela ordem serviço unidade
			OrdemServicoUnidade ordemServicoUnidade = new OrdemServicoUnidade();
			// id do usuário logado
			if (usuarioLogado.getId() != null && !usuarioLogado.getId().equals("")) {
				// unidade do usuário que está logado
				if (usuarioLogado.getUnidadeOrganizacional() != null && !usuarioLogado.getUnidadeOrganizacional().equals("")
						&& usuarioLogado.getUnidadeOrganizacional().getId() != null && !usuarioLogado.getUnidadeOrganizacional().getId().equals("")) {
					UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
					unidadeOrganizacional.setId(usuarioLogado.getUnidadeOrganizacional().getId());
					// seta a unidade organizacional na ordem serviço unidade
					ordemServicoUnidade.setUnidadeOrganizacional(unidadeOrganizacional);
				}
				// seta o usuário na ordem serviço unidade
				ordemServicoUnidade.setUsuario(usuarioLogado);

			}
			// inseri as ordem de serviço na ordem serviço unidade
			if (numeroOS != null && !numeroOS.equals("")) {
				OrdemServico ordemServico = new OrdemServico();
				ordemServico.setId(numeroOS);
				ordemServicoUnidade.setOrdemServico(ordemServico);
			}

			// seta o id do atendimento relação tipo
			AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
			atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
			ordemServicoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);

			// seta a ultima alteração com a data atual
			ordemServicoUnidade.setUltimaAlteracao(new Date());

			// inseri a ordem de serviço unidade
			getControladorUtil().inserir(ordemServicoUnidade);

			// caso a ordem de serviço que está sendo encerrada tenha uma ordem
			// de
			// serviço referencia
			Integer idOSReferencia = repositorioOrdemServico.pesquisarOSReferencia(numeroOS);
			if (idOSReferencia != null && !idOSReferencia.equals("")) {
				repositorioOrdemServico.atualizarParmsOSReferencia(idOSReferencia, idMotivoEncerramento);
			}

			// Verifica se a data de encerramento vinda como parametro do método
			// é diferente de nulo
			if (dataEncerramentoParametro != null && !dataEncerramentoParametro.equals("")) {
				// [SB0004] - Verificar/Excluir/Atualizar Programação da Ordem
				// de Serviço
				verificarExcluirAtualizarProgramacaoOS(numeroOS, dataEncerramento);
			}
			// caso exista a ordem de serviço fiscalização então gera a os
			// Fiscalização
			if (osFiscalizacao != null && !osFiscalizacao.equals("")) {
				gerarOrdemServico(osFiscalizacao, usuarioLogado, null);
			}

			// RM93 - adicionado por Vivianne Sousa 30/01/2011 - analista:Rosana
			// Carvalho
			if (ordemServicoBoletim != null) {
				getControladorUtil().inserir(ordemServicoBoletim);
			}
			// RM777 - adicionado por Vivianne Sousa 03/06/2011 -
			// analista:Claudio Lira
			if (indicadorServicoAceito != null && indicadorServicoAceito.equals(ConstantesSistema.NAO)) {
				// 4.5. Caso o usuário rejeite o retorno encontrado na
				// fiscalização,
				// indicando a situação do serviço como REJEITADO,

				rejeitarOrdemServico(numeroOS, usuarioLogado);
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * Rotina Batch que encerra todas as OS de um serviço tipo especifico que
	 * não tenha RA
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 23/02/2007
	 * 
	 * @throws ControladorException
	 */
	public void encerrarOSDoServicoTipoSemRA(Usuario usuarioLogado, Integer idServicoTipo) throws ControladorException {
		try {
			Collection idsOS = repositorioOrdemServico.pesquisarOSComServicoTipo(idServicoTipo);

			Collection colecaoOrdemServicoUnidade = new ArrayList();

			if (idsOS != null && !idsOS.isEmpty()) {
				repositorioOrdemServico.atualizarColecaoOS(idsOS);
				UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
				// id do usuário logado
				if (usuarioLogado.getId() != null && !usuarioLogado.getId().equals("")) {
					// unidade do usuário que está logado
					if (usuarioLogado.getUnidadeOrganizacional() != null && !usuarioLogado.getUnidadeOrganizacional().equals("")
							&& usuarioLogado.getUnidadeOrganizacional().getId() != null && !usuarioLogado.getUnidadeOrganizacional().getId().equals("")) {

						unidadeOrganizacional.setId(usuarioLogado.getUnidadeOrganizacional().getId());

					}

					// seta o id do atendimento relação tipo
					AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
					atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);

					Iterator iteIdsOS = idsOS.iterator();

					Usuario usuarioMigracao = Usuario.USUARIO_BATCH;

					while (iteIdsOS.hasNext()) {
						OrdemServico ordemServico = new OrdemServico();
						// inseri a tabela ordem serviço unidade
						OrdemServicoUnidade ordemServicoUnidade = new OrdemServicoUnidade();
						Integer idOS = (Integer) iteIdsOS.next();
						ordemServico.setId(idOS);

						// seta a unidade organizacional na ordem serviço
						// unidade
						ordemServicoUnidade.setUnidadeOrganizacional(usuarioMigracao.getUnidadeOrganizacional());

						// seta o usuário na ordem serviço unidade
						ordemServicoUnidade.setUsuario(usuarioMigracao);
						ordemServicoUnidade.setUltimaAlteracao(new Date());
						ordemServicoUnidade.setOrdemServico(ordemServico);
						ordemServicoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
						colecaoOrdemServicoUnidade.add(ordemServicoUnidade);

					}
				}

				getControladorBatch().inserirColecaoObjetoParaBatch(colecaoOrdemServicoUnidade);

			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);

		}
	}

	/**
	 * [UC0457] Encerra Ordem de Serviço
	 * 
	 * [SB0002] - Encerrar com execução e sem referência
	 * 
	 * @author Sávio Luiz
	 * @date 25/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void encerrarOSComExecucaoSemReferencia(Integer numeroOS, Date dataEncerramento, Usuario usuarioLogado, String motivoEncerramento,
			Date ultimaAlteracao, String parecerEncerramento, String indicadorPavimento, String pavimento,
			Collection colecaoManterDadosAtividadesOrdemServicoHelper, IntegracaoComercialHelper integracaoComercialHelper, String tipoServicoOSId,
			OrdemServico osFiscalizacao, String indicadorVistoriaServicoTipo, String codigoRetornoVistoriaOs, OrdemServicoPavimento ordemServicoPavimento,
			OrdemServicoBoletim ordemServicoBoletim, Short indicadorServicoAceito) throws ControladorException {

		Integer idMotivoEncerramento = null;
		
		if (motivoEncerramento == null || motivoEncerramento.equals("")) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.required", null, "Motivo de Encerramento");
		} else {
			idMotivoEncerramento = Util.converterStringParaInteger(motivoEncerramento);
		}

		if (parecerEncerramento != null && !parecerEncerramento.equals("") && parecerEncerramento.length() > 400) {

			String[] msg = new String[2];
			msg[0] = "Parecer";
			msg[1] = "400";

			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.execedeu_limit_observacao", null, msg);
		}

		if (motivoEncerramento == null || motivoEncerramento.equals("")) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.required", null, "Motivo de Encerramento");
		}

		if (dataEncerramento == null || dataEncerramento.equals("")) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.required", null, "Data de Encerramento");
		}

		try {
			if (numeroOS != null && !numeroOS.equals("")) {
				Short indicadorAtividade = repositorioOrdemServico.pesquisarIndAtividadeServicoTipoOS(new Integer(numeroOS));

				boolean temPermissaoEspecial = getControladorPermissaoEspecial().verificarPermissaoEspecial(PermissaoEspecial.ENCERRAR_ORDEM_SERVICO_SEM_ATIVIDADES, usuarioLogado);

				if (!temPermissaoEspecial && indicadorAtividade.equals(ConstantesSistema.SIM)) {
					if (colecaoManterDadosAtividadesOrdemServicoHelper == null || colecaoManterDadosAtividadesOrdemServicoHelper.isEmpty()) {
						throw new ControladorException("atencao.required", null, "Atividades");
					}
				}
			}

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		if (colecaoManterDadosAtividadesOrdemServicoHelper != null && !colecaoManterDadosAtividadesOrdemServicoHelper.isEmpty()) {
			this.manterDadosAtividadesOrdemServico(colecaoManterDadosAtividadesOrdemServicoHelper);
		}

		if (indicadorVistoriaServicoTipo != null && !indicadorVistoriaServicoTipo.equals("")) {
			if (indicadorVistoriaServicoTipo.equals(ServicoTipo.INDICADOR_VISTORIA_SIM)) {
				if (codigoRetornoVistoriaOs == null || codigoRetornoVistoriaOs.equals("")) {
					throw new ControladorException("atencao.required", null, "Retorno Vistoria");
				}
			}
		}

		FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
		filtroOrdemServico.adicionarParametro(new ParametroSimples(FiltroOrdemServico.ID, numeroOS));
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.COBRANCA_DOCUMENTO);
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.UNIDADE_ORGANIZACIONAL_ATUAL);
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.SERVICO_TIPO);

		Collection<OrdemServico> colecaoOS = getControladorUtil().pesquisar(filtroOrdemServico, OrdemServico.class.getName());

		if (colecaoOS == null || colecaoOS.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		OrdemServico osNaBase = colecaoOS.iterator().next();

		validarEncerramentoOsImovelEmCampo(osNaBase);

		if (ultimaAlteracao != null && !ultimaAlteracao.equals("")) {

			if (osNaBase.getUltimaAlteracao().after(ultimaAlteracao)) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}
		}

		// fim controle transação

		if (codigoRetornoVistoriaOs == null || codigoRetornoVistoriaOs.equals("")) {
			osNaBase.setCodigoRetornoVistoria(null);
		} else {
			osNaBase.setCodigoRetornoVistoria(new Short(codigoRetornoVistoriaOs));
		}

		// atualizar o objeto da base
		// seta o código da situação como 2
		osNaBase.setSituacao(new Short("2"));
		// seta a data de encerramento recebida
		osNaBase.setDataEncerramento(dataEncerramento);
		// seta o parecer de encerramento
		if (parecerEncerramento != null && !parecerEncerramento.equals("")) {
			osNaBase.setDescricaoParecerEncerramento(parecerEncerramento);
		} else {
			osNaBase.setDescricaoParecerEncerramento(null);
		}
		// seta o pavimento
		if (pavimento != null && !pavimento.equals("")) {
			osNaBase.setAreaPavimento(Util.formatarMoedaRealparaBigDecimal(pavimento));
		} else {
			osNaBase.setAreaPavimento(null);
		}
		// pesquisa os parametros tabela serviço tipo
		Short indAtualizarComercial = null;
		Short indIncluirDebito = null;
		Integer idDebitoTipo = null;
		Object[] parmsServTipo = null;
		Integer idImovel = null;
		BigDecimal valorServico = null;
		try {
			parmsServTipo = repositorioOrdemServico.recuperarParametrosServicoTipo(numeroOS);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (parmsServTipo != null) {
			if (parmsServTipo[0] != null) {
				indAtualizarComercial = (Short) parmsServTipo[0];
			}
			if (parmsServTipo[1] != null) {
				idDebitoTipo = (Integer) parmsServTipo[1];
			}
			if (parmsServTipo[2] != null) {
				idImovel = (Integer) parmsServTipo[2];
			}
			if (parmsServTipo[4] != null) {
				indIncluirDebito = (Short) parmsServTipo[4];
			}
			if (parmsServTipo[5] != null) {
				valorServico = (BigDecimal) parmsServTipo[5];
			}
		}
		if (indAtualizarComercial != null && !indAtualizarComercial.equals("")) {
			if (indAtualizarComercial.equals(ServicoTipo.INDICADOR_ATUALIZA_COMERCIAL_SIM)) {
				osNaBase.setIndicadorComercialAtualizado(ServicoTipo.INDICADOR_ATUALIZA_COMERCIAL_SIM);

				if (integracaoComercialHelper != null && !integracaoComercialHelper.equals("")) {
					if (integracaoComercialHelper.getOrdemServico() != null && !integracaoComercialHelper.getOrdemServico().equals("")) {
						if (integracaoComercialHelper.getOrdemServico().getValorAtual() != null
								&& !integracaoComercialHelper.getOrdemServico().getValorAtual().equals("")) {
							osNaBase.setValorAtual(integracaoComercialHelper.getOrdemServico().getValorAtual());
						}
						if (integracaoComercialHelper.getOrdemServico().getServicoNaoCobrancaMotivo() != null
								&& !integracaoComercialHelper.getOrdemServico().getServicoNaoCobrancaMotivo().equals("")) {
							if (!integracaoComercialHelper.getOrdemServico().getServicoNaoCobrancaMotivo().getId()
									.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
								osNaBase.setServicoNaoCobrancaMotivo(integracaoComercialHelper.getOrdemServico().getServicoNaoCobrancaMotivo());
							}
						}
						if (integracaoComercialHelper.getOrdemServico().getPercentualCobranca() != null
								&& !integracaoComercialHelper.getOrdemServico().getPercentualCobranca().equals("")) {
							osNaBase.setPercentualCobranca(integracaoComercialHelper.getOrdemServico().getPercentualCobranca());
						}
					}
				}

			} 
		}

		AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
		atendimentoMotivoEncerramento.setId(idMotivoEncerramento);
		osNaBase.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
		osNaBase.setUltimaAlteracao(new Date());
		if (integracaoComercialHelper != null && integracaoComercialHelper.getOrdemServico() != null) {
			integracaoComercialHelper.getOrdemServico().setUltimaAlteracao(new Date());

			// ******************************************************************
			// Alterado por: Ivan Sergio
			// Data: 12/02/2009
			// CRC1222 - Seta a data de encerramento que sera utilizada no
			// efetuar
			// instalacao de hidrometro
			// ******************************************************************
			integracaoComercialHelper.getOrdemServico().setDataEncerramento(dataEncerramento);
			// ******************************************************************
		}

		// ------------ REGISTRAR TRANSAÇÃO----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(Operacao.OPERACAO_ORDEM_SERVICO_ENCERRAR, osNaBase.getId(), osNaBase.getId(),
				new UsuarioAcaoUsuarioHelper(usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		registradorOperacao.registrarOperacao(osNaBase);
		// ------------ REGISTRAR TRANSAÇÃO---------------------------

		getControladorUtil().atualizar(osNaBase);

		// Alterado por Yara Taciane - 29/05/08, por conta do [UC457 - Encerrar
		// Ordem de Serviço]
		// 4.2. Caso o indicador de conserto do pavimento de RUA esteja indicado
		// como sim, sistema deverá inserir
		// a tabela ORDEM_SERVICO_PAVIMENTO, com ORSE_ID da tabela que está
		// sendo encerrada.
		// Analista: Fabíola Araújo.
		if (osNaBase != null && osNaBase.getServicoTipo() != null && osNaBase.getServicoTipo().getId() != null) {

			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, osNaBase.getServicoTipo().getId()));
			Collection<ServicoTipo> colecaoServicoTipo = getControladorUtil().pesquisar(filtroServicoTipo, ServicoTipo.class.getName());
			ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(colecaoServicoTipo);

			if (servicoTipo != null
					&& ((servicoTipo.getIndicadorPavimentoRua() != null && servicoTipo.getIndicadorPavimentoRua().toString().equals("1")) || (servicoTipo
							.getIndicadorPavimentoCalcada() != null && servicoTipo.getIndicadorPavimentoCalcada().toString().equals("1")))) {

				if (osNaBase != null && ordemServicoPavimento != null) {

					ordemServicoPavimento.setOrdemServico(osNaBase);
					ordemServicoPavimento.setDataGeracao(new Date());
					getControladorUtil().inserir(ordemServicoPavimento);
				}
			}

			verificarSituacaoFaturamento(usuarioLogado, osNaBase);
		}

		// Alterado por Francisco - 22/05/08, por conta do [UC478 - Gerar Resumo
		// de Ações de cobrança]
		// O encerramento da OS atualiza o documento de cobranca correspondente
		// Analista: Ana Breda
		getControladorCobranca().atualizarCobrancaDocumentoAposEncerrarOS(osNaBase);

		// inseri a tabela ordem serviço unidade
		OrdemServicoUnidade ordemServicoUnidade = new OrdemServicoUnidade();
		// id do usuário logado
		if (usuarioLogado.getId() != null && !usuarioLogado.getId().equals("")) {
			// unidade do usuário que está logado
			if (usuarioLogado.getUnidadeOrganizacional() != null && !usuarioLogado.getUnidadeOrganizacional().equals("")
					&& usuarioLogado.getUnidadeOrganizacional().getId() != null && !usuarioLogado.getUnidadeOrganizacional().getId().equals("")) {
				UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
				unidadeOrganizacional.setId(usuarioLogado.getUnidadeOrganizacional().getId());
				// seta a unidade organizacional na ordem serviço unidade
				ordemServicoUnidade.setUnidadeOrganizacional(unidadeOrganizacional);
			}
			// seta o usuário na ordem serviço unidade
			ordemServicoUnidade.setUsuario(usuarioLogado);

		}
		// inseri as ordem de serviço na ordem serviço unidade
		if (numeroOS != null && !numeroOS.equals("")) {
			OrdemServico ordemServico = new OrdemServico();
			ordemServico.setId(numeroOS);
			ordemServicoUnidade.setOrdemServico(ordemServico);
		}
		// seta o id do atendimento relação tipo
		AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
		atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
		ordemServicoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);

		// seta a ultima alteração com a data atual
		ordemServicoUnidade.setUltimaAlteracao(new Date());

		// inseri a ordem de serviço unidade
		getControladorUtil().inserir(ordemServicoUnidade);

		// Verifica se a data de encerramento vinda como parametro do método
		// é diferente de nulo
		if (dataEncerramento != null && !dataEncerramento.equals("")) {
			// [SB0004] - Verificar/Excluir/Atualizar Programação da Ordem
			// de Serviço
			verificarExcluirAtualizarProgramacaoOS(numeroOS, dataEncerramento);
		}

		// caso exista a ordem de serviço fiscalização então gera a os
		// Fiscalização
		// Comentado por Raphael Rossiter em 28/02/2007
		/*
		 * if (osFiscalizacao != null && !osFiscalizacao.equals("")) { // seta a
		 * situação para 2 pois já foi atualizado como encerrado na // base essa
		 * os osFiscalizacao.getOsReferencia().setSituacao(new Short("2"));
		 * gerarOrdemServico(osFiscalizacao, usuarioLogado); }
		 */

		if (osFiscalizacao != null && osFiscalizacao.getOsReferencia() != null) {
			// seta a situação para 2 pois já foi atualizado como encerrado na
			// base essa os
			osFiscalizacao.getOsReferencia().setSituacao(new Short("2"));
			gerarOrdemServico(osFiscalizacao, usuarioLogado, null);
		}

		// RM93 - adicionado por Vivianne Sousa 30/01/2011 - analista:Rosana
		// Carvalho
		if (ordemServicoBoletim != null) {
			getControladorUtil().inserir(ordemServicoBoletim);
		}

		/*
		 * Colocado por Raphael Rossiter em 27/04/2007
		 * 
		 * Caso o serviço associado à ordem de serviço tenha indicativo que é
		 * para gerar débito a cobrar automaticamente.
		 * 
		 * Serviço associado não atualiza o sistema comercial
		 * 
		 * Valor do serviço maior que zero
		 * 
		 * Tipo do débito diferente de zero
		 */
		if (indIncluirDebito != null && indAtualizarComercial != null) {

			if (indIncluirDebito.shortValue() == ConstantesSistema.SIM.shortValue()
					&& indAtualizarComercial.shortValue() == ServicoTipo.INDICADOR_ATUALIZA_COMERCIAL_NAO
					&& (idDebitoTipo != null && idDebitoTipo.intValue() != ConstantesSistema.ZERO.intValue())) {

				Integer idServicoTipo = Util.converterStringParaInteger(tipoServicoOSId);

				if (valorServico.compareTo(new BigDecimal("0.00")) == 0) {

					// Metodo que verifica o tipo de medição
					boolean hidrometroExistente = false;
					try {
						hidrometroExistente = repositorioMicromedicao.verificaExistenciaHidrometro(idImovel);
					} catch (ErroRepositorioException e) {
						
						e.printStackTrace();
					}

					if (hidrometroExistente) {
						valorServico = getControladorAtendimentoPublico().obterValorDebito(idServicoTipo, idImovel, new Short("1"));
					} else {
						valorServico = getControladorAtendimentoPublico().obterValorDebito(idServicoTipo, idImovel, new Short("2"));
					}
				}
				// Alterado por Rafael Corrêa 05/11/2008
				FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
				filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.DEBITO_TIPO_ID, idDebitoTipo));
				filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.EMISSAO_GUIA_PAGAMENTO, osNaBase.getDataGeracao()));
				filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.IMOVEL_ID, idImovel));

				Collection<GuiaPagamento> colecaoGuiasPagamento = getControladorUtil().pesquisar(filtroGuiaPagamento, GuiaPagamento.class.getName());

				if (colecaoGuiasPagamento == null || colecaoGuiasPagamento.isEmpty()) {
					// [UC0475] - Gerar Débito Ordem de Serviço
					if (valorServico.compareTo(BigDecimal.ZERO) == 1) {
						this.gerarDebitoOrdemServico(numeroOS, idDebitoTipo, valorServico, 1, "100", usuarioLogado);
					}
				}
			}
		}

		/*
		 * INTEGRAÇÃO COMERCIAL
		 * 
		 * Autor: Raphael Rossiter Data: 25/04/2007
		 */
		if (tipoServicoOSId != null) {
			int idServicoTipoInt = Util.converterStringParaInteger(tipoServicoOSId);

			this.integracaoComercial(idServicoTipoInt, integracaoComercialHelper, usuarioLogado);
		}

		// RM777 - adicionado por Vivianne Sousa 03/06/2011 - analista:Claudio
		// Lira
		if (indicadorServicoAceito != null && indicadorServicoAceito.equals(ConstantesSistema.NAO)) {
			rejeitarOrdemServico(numeroOS, usuarioLogado);
		}

	}

	private void rejeitarOrdemServico(Integer numeroOS, Usuario usuarioLogado) throws ControladorException {
		try {
			Integer idOSReferenciaRejeitada = repositorioOrdemServico.pesquisarOSReferencia(numeroOS);
			
			if (idOSReferenciaRejeitada != null && !idOSReferenciaRejeitada.equals("")) {
				// o sistema pesquisa se a ordem de serviço referida já existe
				// na tabela de OS não aceita
				// (selecionar na tabela cobranca.cob_ac_os_nao_aceitas com
				// orse_id = orse_idreferencia),
				FiltroCobrancaAcaoOrdemServicoNaoAceitas filtroCobrancaAcaoOrdemServicoNaoAceitas = new FiltroCobrancaAcaoOrdemServicoNaoAceitas();
				
				filtroCobrancaAcaoOrdemServicoNaoAceitas.adicionarParametro(new ParametroSimples(FiltroCobrancaAcaoOrdemServicoNaoAceitas.ORDEM_SERVICO_ID,
						idOSReferenciaRejeitada));
				
				Collection<CobrancaAcaoOrdemServicoNaoAceitas> colecao = getControladorUtil().pesquisar(filtroCobrancaAcaoOrdemServicoNaoAceitas,
						CobrancaAcaoOrdemServicoNaoAceitas.class.getName());
				
				if (colecao == null || colecao.isEmpty()) {
					// Caso não exista[SB0008-Incluir Ordem Serviço Não Aceita].
					incluirOrdemServicoNaoAceita(idOSReferenciaRejeitada, usuarioLogado);
				}
			}
		} catch (Exception e) {
			throw new ControladorException("Erro ao rejeitar ordem de servico", e);
		}
	}

	private void validarEncerramentoOsImovelEmCampo(OrdemServico ordemServico) throws ControladorException {
		FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();

		filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.INDICADOR_PERMITE_ALTERACAO_IMOVEL_EM_CAMPO, ConstantesSistema.NAO));

		Collection<ServicoTipo> colecaoServicoTipo = getControladorUtil().pesquisar(filtroServicoTipo, ServicoTipo.class.getName());

		for (ServicoTipo servicoTipo : colecaoServicoTipo) {
			if (ordemServico.getServicoTipo().getId().equals(servicoTipo.getId())) {
				getControladorMicromedicao().validarImovelEmCampo(ordemServico.getImovel().getId());
				break;
			}
		}

	}

	/**
	 * [UC0457] Encerra Ordem de Serviço
	 * [SB0003] - Encerrar com execução e com referência
	 * 
	 * @author Sávio Luiz
	 * @date 27/09/2006
	 * 
	 * @throws ControladorException
	 */
	public Integer encerrarOSComExecucaoComReferencia(Integer numeroOS, Date dataEncerramento, Usuario usuarioLogado, String motivoEncerramento,
			Date ultimaAlteracao, String parecerEncerramento, String indicadorPavimento, String pavimento, String idTipoRetornoOSReferida,
			String indicadorDeferimento, String indicadorTrocaServicoTipo, String idServicoTipo, String idOSReferencia, String idServicoTipoReferenciaOS,
			Collection colecaoManterDadosAtividadesOrdemServicoHelper, IntegracaoComercialHelper integracaoComercialHelper, String tipoServicoOSId,
			OrdemServico osFiscalizacao, String indicadorVistoriaServicoTipo, String codigoRetornoVistoriaOs, OrdemServicoBoletim ordemServicoBoletim,
			Short indicadorServicoAceito) throws ControladorException {

		try {
			Integer idMotivoEncerramento = null;
			if (motivoEncerramento == null || motivoEncerramento.equals("")) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.required", null, "Motivo de Encerramento");
			} else {
				idMotivoEncerramento = Util.converterStringParaInteger(motivoEncerramento);
			}

			if (parecerEncerramento != null && !parecerEncerramento.equals("") && parecerEncerramento.length() > 400) {

				String[] msg = new String[2];
				msg[0] = "Parecer";
				msg[1] = "400";

				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.execedeu_limit_observacao", null, msg);
			}

			Integer idOrdemServicoDiagnostico = null;

			try {
				if (numeroOS != null && !numeroOS.equals("")) {
					Short indicadorAtividade = repositorioOrdemServico.pesquisarIndAtividadeServicoTipoOS(new Integer(numeroOS));

					boolean temPermissaoEspecial = getControladorPermissaoEspecial().verificarPermissaoEspecial(
							PermissaoEspecial.ENCERRAR_ORDEM_SERVICO_SEM_ATIVIDADES, usuarioLogado);

					if (!temPermissaoEspecial && indicadorAtividade.equals(ConstantesSistema.SIM)) {
						if (colecaoManterDadosAtividadesOrdemServicoHelper == null || colecaoManterDadosAtividadesOrdemServicoHelper.isEmpty()) {

							throw new ControladorException("atencao.required", null, "Atividades");
						}
					}
				}

			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			if (colecaoManterDadosAtividadesOrdemServicoHelper != null && !colecaoManterDadosAtividadesOrdemServicoHelper.isEmpty()) {
				this.manterDadosAtividadesOrdemServico(colecaoManterDadosAtividadesOrdemServicoHelper);
			}

			if (dataEncerramento == null || dataEncerramento.equals("")) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.required", null, "Data de Encerramento");
			}
			if (idTipoRetornoOSReferida == null || idTipoRetornoOSReferida.equals("")) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.required", null, "Tipo de Retorno Referida");
			}
			if (indicadorDeferimento != null && !indicadorDeferimento.equals("")) {
				short indDeferimento = Short.parseShort(indicadorDeferimento);

				if (indDeferimento == OsReferidaRetornoTipo.INDICADOR_DEFERIMENTO_SIM) {
					if (indicadorPavimento != null && !indicadorPavimento.equals("")) {
						short indicadorPavimentoShort = new Short(indicadorPavimento);
						if (indicadorPavimentoShort == ServicoTipo.INDICADOR_PAVIMENTO_SIM) {
							if (pavimento == null || pavimento.equals("")) {
								sessionContext.setRollbackOnly();
								throw new ControladorException("atencao.required", null, "Pavimento");
							}
						}
					}
					
					if (indicadorTrocaServicoTipo != null && !indicadorTrocaServicoTipo.equals("")) {
						short indicadorTrocaServicoTipoShort = new Short(indicadorTrocaServicoTipo);
						if (indicadorTrocaServicoTipoShort == OsReferidaRetornoTipo.INDICADOR_TROCA_SERVICO_SIM) {
							if (idServicoTipo == null || idServicoTipo.equals("")) {
								sessionContext.setRollbackOnly();
								throw new ControladorException("atencao.required", null, "Tipo de Serviço");
							}

						}
					}
				}
			}

			if (indicadorVistoriaServicoTipo != null && !indicadorVistoriaServicoTipo.equals("")) {
				if (indicadorVistoriaServicoTipo.equals(ServicoTipo.INDICADOR_VISTORIA_SIM)) {
					if (codigoRetornoVistoriaOs == null || codigoRetornoVistoriaOs.equals("")) {
						throw new ControladorException("atencao.required", null, "Retorno Vistoria");
					}
				}
			}

			FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
			filtroOrdemServico.adicionarParametro(new ParametroSimples(FiltroOrdemServico.ID, numeroOS));
			filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("servicoTipo.servicoTipoPrioridade");
			filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("servicoTipo.servicoTipoReferencia.servicoTipo");
			filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("osReferencia");
			filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.COBRANCA_DOCUMENTO);
			filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.UNIDADE_ORGANIZACIONAL_ATUAL);

			Collection<OrdemServico> colecaoOS = getControladorUtil().pesquisar(filtroOrdemServico, OrdemServico.class.getName());

			if (colecaoOS == null || colecaoOS.isEmpty()) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}

			OrdemServico osNaBase = (OrdemServico) colecaoOS.iterator().next();

			validarEncerramentoOsImovelEmCampo(osNaBase);
			
			if (ultimaAlteracao != null && !ultimaAlteracao.equals("")) {

				if (osNaBase.getUltimaAlteracao().after(ultimaAlteracao)) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("atencao.atualizacao.timestamp");
				}
			}

			if (codigoRetornoVistoriaOs == null || codigoRetornoVistoriaOs.equals("")) {
				osNaBase.setCodigoRetornoVistoria(null);
			} else {
				osNaBase.setCodigoRetornoVistoria(new Short(codigoRetornoVistoriaOs));
			}

			osNaBase.setSituacao(new Short("2"));
			osNaBase.setDataEncerramento(dataEncerramento);

			if (parecerEncerramento != null && !parecerEncerramento.equals("")) {
				osNaBase.setDescricaoParecerEncerramento(parecerEncerramento);
			} else {
				osNaBase.setDescricaoParecerEncerramento(null);
			}

			if (pavimento != null && !pavimento.equals("")) {
				osNaBase.setAreaPavimento(Util.formatarMoedaRealparaBigDecimal(pavimento));
			} else {
				osNaBase.setAreaPavimento(null);
			}

			Integer idOSRetornoTipoReferida = Util.converterStringParaInteger(idTipoRetornoOSReferida);
			OsReferidaRetornoTipo osReferidaRetornoTipo = new OsReferidaRetornoTipo();
			osReferidaRetornoTipo.setId(idOSRetornoTipoReferida);
			osNaBase.setOsReferidaRetornoTipo(osReferidaRetornoTipo);

			if (indicadorTrocaServicoTipo != null && !indicadorTrocaServicoTipo.equals("")) {
				short indicadorTrocaServicoTipoShort = new Short(indicadorTrocaServicoTipo);
				if (indicadorTrocaServicoTipoShort == OsReferidaRetornoTipo.INDICADOR_TROCA_SERVICO_SIM) {
					ServicoTipo servicoTipoReferencia = new ServicoTipo();
					servicoTipoReferencia.setId(Util.converterStringParaInteger(idServicoTipo));
					osNaBase.setServicoTipoReferencia(servicoTipoReferencia);
				} else {
					osNaBase.setServicoTipoReferencia(null);
				}
			} else {
				osNaBase.setServicoTipoReferencia(null);
			}

			Short indAtualizarComercial = null;
			Short indIncluirDebito = null;
			Integer idDebitoTipo = null;
			Object[] parmsServTipo = null;
			Integer idImovel = null;
			BigDecimal valorServico = null;
			try {
				parmsServTipo = repositorioOrdemServico.recuperarParametrosServicoTipo(numeroOS);
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			if (parmsServTipo != null) {
				if (parmsServTipo[0] != null) {
					indAtualizarComercial = (Short) parmsServTipo[0];
				}
				if (parmsServTipo[1] != null) {
					idDebitoTipo = (Integer) parmsServTipo[1];
				}
				if (parmsServTipo[2] != null) {
					idImovel = (Integer) parmsServTipo[2];
				}
				if (parmsServTipo[4] != null) {
					indIncluirDebito = (Short) parmsServTipo[4];
				}
				if (parmsServTipo[5] != null) {
					valorServico = (BigDecimal) parmsServTipo[5];
				}

			}

			if (indAtualizarComercial != null && !indAtualizarComercial.equals("")) {
				if (indAtualizarComercial.equals(ServicoTipo.INDICADOR_ATUALIZA_COMERCIAL_SIM)) {
					osNaBase.setIndicadorComercialAtualizado(ServicoTipo.INDICADOR_ATUALIZA_COMERCIAL_SIM);

					if (integracaoComercialHelper != null && !integracaoComercialHelper.equals("")) {
						if (integracaoComercialHelper.getOrdemServico() != null && !integracaoComercialHelper.getOrdemServico().equals("")) {
							if (integracaoComercialHelper.getOrdemServico().getValorAtual() != null
									&& !integracaoComercialHelper.getOrdemServico().getValorAtual().equals("")) {
								osNaBase.setValorAtual(integracaoComercialHelper.getOrdemServico().getValorAtual());
							}
							if (integracaoComercialHelper.getOrdemServico().getServicoNaoCobrancaMotivo() != null
									&& !integracaoComercialHelper.getOrdemServico().getServicoNaoCobrancaMotivo().equals("")) {
								if (!integracaoComercialHelper.getOrdemServico().getServicoNaoCobrancaMotivo().equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
									osNaBase.setServicoNaoCobrancaMotivo(integracaoComercialHelper.getOrdemServico().getServicoNaoCobrancaMotivo());
								}
							}
							if (integracaoComercialHelper.getOrdemServico().getPercentualCobranca() != null
									&& !integracaoComercialHelper.getOrdemServico().getPercentualCobranca().equals("")) {
								osNaBase.setPercentualCobranca(integracaoComercialHelper.getOrdemServico().getPercentualCobranca());
							}
						}
					}

				} 
			} 

			AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
			atendimentoMotivoEncerramento.setId(idMotivoEncerramento);
			osNaBase.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
			osNaBase.setUltimaAlteracao(new Date());
			if (integracaoComercialHelper != null && integracaoComercialHelper.getOrdemServico() != null) {
				integracaoComercialHelper.getOrdemServico().setUltimaAlteracao(new Date());
			}

			RegistradorOperacao registradorOperacao = new RegistradorOperacao(Operacao.OPERACAO_ORDEM_SERVICO_ENCERRAR, osNaBase.getId(), osNaBase.getId(),
					new UsuarioAcaoUsuarioHelper(usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

			registradorOperacao.registrarOperacao(osNaBase);

			getControladorUtil().atualizar(osNaBase);
			getControladorCobranca().atualizarCobrancaDocumentoAposEncerrarOS(osNaBase);

			if (idOSReferencia != null && !idOSReferencia.equals("")) {
				filtroOrdemServico = new FiltroOrdemServico();
				filtroOrdemServico.adicionarParametro(new ParametroSimples(FiltroOrdemServico.ID, idOSReferencia));

				Collection<OrdemServico> colecaoOSReferencia = getControladorUtil().pesquisar(filtroOrdemServico, OrdemServico.class.getName());

				if (colecaoOSReferencia == null || colecaoOSReferencia.isEmpty()) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("atencao.atualizacao.timestamp");
				}

				OrdemServico osReferenciaNaBase = new OrdemServico();
				osReferenciaNaBase = (OrdemServico) colecaoOSReferencia.iterator().next();

				osReferenciaNaBase.setDataEncerramento(dataEncerramento);
				Integer idMotivoEncerramentoOSReferida = null;
				try {
					idMotivoEncerramentoOSReferida = repositorioOrdemServico.pesquisarIdMotivoEncerramentoOSReferida(idOSRetornoTipoReferida);
				} catch (ErroRepositorioException e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}
				if (idMotivoEncerramentoOSReferida != null) {
					osReferidaRetornoTipo.setId(idOSRetornoTipoReferida);
					osReferenciaNaBase.setOsReferidaRetornoTipo(osReferidaRetornoTipo);
					osReferenciaNaBase.setDataEncerramento(dataEncerramento);
				} else {
					osReferenciaNaBase.setOsReferidaRetornoTipo(null);
					osReferenciaNaBase.setDataEncerramento(null);
				}

				Short codigoSituacaoOSReferida = null;
				try {
					codigoSituacaoOSReferida = repositorioOrdemServico.recuperarSituacaoOSReferida(idOSRetornoTipoReferida);
				} catch (ErroRepositorioException e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}
				if (codigoSituacaoOSReferida != null && !codigoSituacaoOSReferida.equals("")) {
					osReferenciaNaBase.setSituacao(codigoSituacaoOSReferida);
				}

				if (indicadorTrocaServicoTipo != null && !indicadorTrocaServicoTipo.equals("")) {
					short indicadorTrocaServicoTipoShort = new Short(indicadorTrocaServicoTipo);
					if (indicadorTrocaServicoTipoShort == OsReferidaRetornoTipo.INDICADOR_TROCA_SERVICO_SIM) {
						ServicoTipo servicoTipo = new ServicoTipo();
						servicoTipo.setId(Util.converterStringParaInteger(idServicoTipo));
						osReferenciaNaBase.setServicoTipo(servicoTipo);
					} else {
						osReferenciaNaBase.setServicoTipoReferencia(null);
					}
				} else {
					osReferenciaNaBase.setServicoTipoReferencia(null);
				}

				osReferenciaNaBase.setIndicadorComercialAtualizado(ServicoTipo.INDICADOR_ATUALIZA_COMERCIAL_NAO);

				Short indicadorDiagnostico = null;
				try {
					indicadorDiagnostico = repositorioOrdemServico
							.pesquisarIndDiagnosticoServicoTipoReferencia(Util.converterStringParaInteger(idOSReferencia));
				} catch (ErroRepositorioException e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}
				if (indicadorDiagnostico != null && !indicadorDiagnostico.equals("")) {
					if (indicadorDiagnostico.equals(ServicoTipoReferencia.INDICADOR_DIAGNOSTICO_ATIVO)) {
						osReferenciaNaBase.setIndicadorServicoDiagnosticado(ServicoTipoReferencia.INDICADOR_DIAGNOSTICO_ATIVO);
					}
				}
				osReferenciaNaBase.setUltimaAlteracao(new Date());

				getControladorUtil().atualizar(osReferenciaNaBase);

				// Alterado por Francisco - 22/05/08, por conta do [UC478 -
				// Gerar Resumo de Ações de cobrança]
				// O encerramento da OS atualiza o documento de cobranca
				// correspondente
				// Analista: Ana Breda
				getControladorCobranca().atualizarCobrancaDocumentoAposEncerrarOS(osReferenciaNaBase);
			}

			// FIM ATUALIZAÇÃO ORDEM SERVIÇO REFERÊNCIA

			// inseri a tabela ordem serviço unidade
			OrdemServicoUnidade ordemServicoUnidade = new OrdemServicoUnidade();
			// id do usuário logado
			if (usuarioLogado.getId() != null && !usuarioLogado.getId().equals("")) {
				// unidade do usuário que está logado
				if (usuarioLogado.getUnidadeOrganizacional() != null && !usuarioLogado.getUnidadeOrganizacional().equals("")
						&& usuarioLogado.getUnidadeOrganizacional().getId() != null && !usuarioLogado.getUnidadeOrganizacional().getId().equals("")) {
					UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
					unidadeOrganizacional.setId(usuarioLogado.getUnidadeOrganizacional().getId());
					// seta a unidade organizacional na ordem serviço unidade
					ordemServicoUnidade.setUnidadeOrganizacional(unidadeOrganizacional);
				}
				// seta o usuário na ordem serviço unidade
				ordemServicoUnidade.setUsuario(usuarioLogado);

			}
			// inseri as ordem de serviço na ordem serviço unidade
			if (numeroOS != null && !numeroOS.equals("")) {
				OrdemServico ordemServico = new OrdemServico();
				ordemServico.setId(numeroOS);
				ordemServicoUnidade.setOrdemServico(ordemServico);
			}
			// seta o id do atendimento relação tipo
			AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
			atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
			ordemServicoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);

			// seta a ultima alteração com a data atual
			ordemServicoUnidade.setUltimaAlteracao(new Date());

			// inseri a ordem de serviço unidade
			getControladorUtil().inserir(ordemServicoUnidade);

			// Verifica se a data de encerramento vinda como parametro do método
			// é diferente de nulo
			if (dataEncerramento != null && !dataEncerramento.equals("")) {
				// [SB0004] - Verificar/Excluir/Atualizar Programação da Ordem
				// de Serviço
				verificarExcluirAtualizarProgramacaoOS(numeroOS, dataEncerramento);
			}

			// se o id deferimento da tabela os_Referida_Retorno_tipo for igual
			// a
			// sim
			if (indicadorDeferimento != null && !indicadorDeferimento.equals("")) {
				short indDeferimento = Short.parseShort(indicadorDeferimento);
				if (indDeferimento == OsReferidaRetornoTipo.INDICADOR_DEFERIMENTO_SIM) {
					// se o id do serviço tipo referência for diferente de nulo
					if (idServicoTipoReferenciaOS != null && !idServicoTipoReferenciaOS.equals("")) {
						// [UC0430] - Gerar Ordem de Serviço
						OrdemServico ordemServicoGerar = new OrdemServico();
						ordemServicoGerar.setRegistroAtendimento(osNaBase.getRegistroAtendimento());
						FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
						filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, idServicoTipoReferenciaOS));
						filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("servicoTipoPrioridade");

						/*
						 * Felipe Santos -
						 * 15/03/2012
						 * 
						 * Adição do objeto ServicoTipoReferencia no filtro de
						 * pesquisa
						 */
						filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("servicoTipoReferencia");
						// fim da alteração

						Collection<ServicoTipo> colecaoServicoTipo = getControladorUtil().pesquisar(filtroServicoTipo, ServicoTipo.class.getName());

						ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(colecaoServicoTipo);

						idImovel = null;
						try {
							idImovel = repositorioOrdemServico.recuperarIdImovelDoRA(osNaBase.getRegistroAtendimento().getId());
						} catch (ErroRepositorioException e) {
							sessionContext.setRollbackOnly();
							throw new ControladorException("erro.sistema", e);
						}
						// caso exista o imóvel seta na ordem de serviço
						if (idImovel != null && !idImovel.equals("")) {
							Imovel imovel = new Imovel();
							imovel.setId(idImovel);
							ordemServicoGerar.setImovel(imovel);
						}

						ordemServicoGerar.setServicoTipo(servicoTipo);
						// ordemServicoGerar.set
						idOrdemServicoDiagnostico = gerarOrdemServico(ordemServicoGerar, usuarioLogado, null);
					}
				}
			}

			// caso exista a ordem de serviço fiscalização então gera a os
			// Fiscalização
			if (osFiscalizacao != null && !osFiscalizacao.equals("")) {
				// seta a situação para 2 pois já foi atualizado como encerrado
				// na
				// base essa os
				osFiscalizacao.getOsReferencia().setSituacao(new Short("2"));
				gerarOrdemServico(osFiscalizacao, usuarioLogado, null);
			}

			/*
			 * Colocado por Raphael Rossiter em 27/04/2007
			 * 
			 * Caso o serviço associado à ordem de serviço tenha indicativo que
			 * é para gerar débito a cobrar automaticamente.
			 * 
			 * Serviço associado não atualiza o sistema comercial
			 * 
			 * Valor do serviço maior que zero
			 * 
			 * Tipo do débito diferente de zero
			 */

			if (indIncluirDebito != null && indAtualizarComercial != null) {
				if (indIncluirDebito.shortValue() == ConstantesSistema.SIM.shortValue()
						&& indAtualizarComercial.shortValue() == ServicoTipo.INDICADOR_ATUALIZA_COMERCIAL_NAO
						&& valorServico.compareTo(new BigDecimal("0.00")) == 1
						&& (idDebitoTipo != null && idDebitoTipo.intValue() != ConstantesSistema.ZERO.intValue())) {

					// Alterado por Rafael Corrêa 05/11/2008
					FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
					filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.DEBITO_TIPO_ID, idDebitoTipo));
					filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.EMISSAO_GUIA_PAGAMENTO, osNaBase.getDataGeracao()));
					filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.IMOVEL_ID, idImovel));
					
					Collection<GuiaPagamento> colecaoGuiasPagamento = getControladorUtil().pesquisar(filtroGuiaPagamento, GuiaPagamento.class.getName());

					if (colecaoGuiasPagamento == null || colecaoGuiasPagamento.isEmpty()) {
						// [UC0475] - Gerar Débito Ordem de Serviço
						this.gerarDebitoOrdemServico(numeroOS, idDebitoTipo, valorServico, 1, "100", usuarioLogado);
					}

				}
			}

			// RM93 - adicionado por Vivianne Sousa 30/01/2011 - analista:Rosana
			// Carvalho
			if (ordemServicoBoletim != null) {
				getControladorUtil().inserir(ordemServicoBoletim);
			}

			/*
			 * INTEGRAÇÃO COMERCIAL
			 * 
			 * Autor: Raphael Rossiter Data: 25/04/2007
			 */

			if (tipoServicoOSId != null) {

				int idServicoTipoInt = Util.converterStringParaInteger(tipoServicoOSId);

				this.integracaoComercial(idServicoTipoInt, integracaoComercialHelper, usuarioLogado);
			}

			// RM777 - adicionado por Vivianne Sousa 03/06/2011 -
			// analista:Claudio Lira
			if (indicadorServicoAceito != null && indicadorServicoAceito.equals(ConstantesSistema.NAO)) {
				rejeitarOrdemServico(numeroOS, usuarioLogado);
			}

			/*
			 * Alterado por Diogo Peixoto em 30/08/2011 [SB0009]- Verificar
			 * Situação Especial de Faturamento
			 */
			if (osNaBase.getServicoTipo() != null || osNaBase.getServicoTipo().getId() != null) {
				// 4.6. Caso o tipo de serviço da ordem de serviço corresponda a
				// SUBSTITUIÇÃO ou RETIRADA de hidrômetro
				verificarSituacaoFaturamento(usuarioLogado, osNaBase);
			}
			return idOrdemServicoDiagnostico;
		} catch (Exception e) {
			logger.error("Erro ao encerrar OS executada com referencia", e);
			sessionContext.setRollbackOnly();
			throw new ControladorException("Erro ao encerrar OS executada com referencia", e);
		}
	}

	private void verificarSituacaoFaturamento(Usuario usuarioLogado, OrdemServico osNaBase)	throws ControladorException {
		// 4.6. Caso o tipo de serviço da ordem de serviço corresponda a SUBSTITUIÇÃO ou RETIRADA de hidrômetro
	
		if (osNaBase.getServicoTipo().getId().equals(310) && osNaBase.getServicoTipo().getId().equals(307)) {
			boolean situacaoEspecial = false;
			Imovel imovel = null;
			// [SB0009 - Verificar Situação Especial de Faturamento]
			
			// Caso o imov_id na tabela ordem_servico diferente de nulo
			try {
				if (osNaBase.getImovel() != null && osNaBase.getImovel().getId() != null) {
					imovel = this.repositorioImovel.pesquisarImovelSituacaoEspecialFaturamento(osNaBase.getImovel().getId());
					if (imovel != null) {
						situacaoEspecial = true;
					}
				} else {
					RegistroAtendimento ra = osNaBase.getRegistroAtendimento();
					if (ra != null && ra.getImovel() != null && ra.getImovel().getId() != null) {
						imovel = this.repositorioImovel.pesquisarImovelSituacaoEspecialFaturamento(ra.getImovel().getId());
						if (imovel != null) {
							situacaoEspecial = true;
						}
					}
				}
				
				if (situacaoEspecial) {
					imovel.setFaturamentoSituacaoTipo(null);
					imovel.setUsuarioParaHistorico(usuarioLogado);
					getControladorAtualizacaoCadastro().atualizar(imovel);
					
					FaturamentoSituacaoHistorico historico = this.repositorioFaturamento.pesquisarFaturamentoSituacaoHistorico(imovel.getId());
					if (historico != null) {
						SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
						
						historico.setAnoMesFaturamentoRetirada(sistemaParametro.getAnoMesFaturamento());
						historico.setUsuarioRetira(usuarioLogado);
						String observacao = "EXCLUÍDO DA SITUAÇÃO ESPECIAL DE FATURAMENTO DECORRENTE DE RETIRADA/SUBSTITUIÇAO "
								+ "DE HIDRÔMETRO ATRAVÉS DA ORDEM DE SERVIÇO " + osNaBase.getId();
						historico.setObservacaoRetira(observacao);
						historico.setUltimaAlteracao(new Date());
						this.repositorioFaturamento.atualizarFaturamentoSituacaoHistorico(historico);
					}
				}
			} catch (ErroRepositorioException e) {
				throw new ControladorException("Erro ao verificar situacao do faturamento", e);
			}
		}
	}

	/**
	 * [UC0457] Encerra Ordem de Serviço
	 * 
	 * [SB0004] - Verificar/Excluir/Atualizar Programação da Ordem de Serviço
	 * 
	 * @author Sávio Luiz
	 * @date 18/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void verificarExcluirAtualizarProgramacaoOS(Integer numeroOS, Date dataEncerramento) throws ControladorException {

		try {
			Collection colecaoParmsProgramacaoOS = repositorioOrdemServico.pesquisarRoteiro(numeroOS, dataEncerramento);
			if (colecaoParmsProgramacaoOS != null && !colecaoParmsProgramacaoOS.isEmpty()) {
				Iterator iteParmsProgramacaoOs = colecaoParmsProgramacaoOS.iterator();
				while (iteParmsProgramacaoOs.hasNext()) {
					Object[] parmsProgramacaoOs = (Object[]) iteParmsProgramacaoOs.next();
					if (parmsProgramacaoOs != null && !parmsProgramacaoOs.equals("")) {
						Integer idOsProgramacao = null;
						Integer idRoteiro = null;
						// id da OSProgramacao
						if (parmsProgramacaoOs[0] != null) {
							idOsProgramacao = (Integer) parmsProgramacaoOs[0];
						}
						// id do roteiro
						if (parmsProgramacaoOs[1] != null) {
							idRoteiro = (Integer) parmsProgramacaoOs[1];
						}

						if (idOsProgramacao != null && idRoteiro != null) {

							OrdemServicoProgramacao ordemServicoProgramacao = new OrdemServicoProgramacao();
							ordemServicoProgramacao.setId(idOsProgramacao);
							ordemServicoProgramacao.setUltimaAlteracao(new Date());
							// remove a ordem de serviço programação
							getControladorUtil().remover(ordemServicoProgramacao);

							Integer idRoteiroBase = repositorioOrdemServico.verificarExistenciaProgramacaoRoteiroParaOSProgramacao(idOsProgramacao, idRoteiro);
							if (idRoteiroBase == null || idRoteiroBase.equals("")) {

								// ProgramacaoRoteiro programacaoRoteiro = new
								// ProgramacaoRoteiro();
								// programacaoRoteiro.setId(idRoteiro);

								// Alterado por: Ivan Sergio
								// Data: 29-12-2008
								// Esta solicitando os dados completos para
								// excluir
								FiltroProgramacaoRoteiro filtroProgramacaoRoteiro = new FiltroProgramacaoRoteiro();
								filtroProgramacaoRoteiro.adicionarParametro(new ParametroSimples(FiltroProgramacaoRoteiro.ID, idRoteiro));
								ProgramacaoRoteiro programacaoRoteiro = this.pesquisar(filtroProgramacaoRoteiro, ProgramacaoRoteiro.class);

								// remove a programação roteiro
								getControladorUtil().remover(programacaoRoteiro);
							}
						}
					}
				}
			}
			OrdemServicoProgramacao ordemServicoProgramacao = repositorioOrdemServico
					.pesquisarOSProgramacaoAtivaComDataEncerramento(numeroOS, dataEncerramento);
			if (ordemServicoProgramacao != null && !ordemServicoProgramacao.equals("")) {
				ordemServicoProgramacao.setIndicadorAtivo(OrdemServicoProgramacao.INDICADOR_ATIVO_NAO);
				ordemServicoProgramacao.setUltimaAlteracao(new Date());
				ordemServicoProgramacao.setSituacaoFechamento(OrdemServicoProgramacao.SITUACAO_FECHAMENTO);
				getControladorUtil().atualizar(ordemServicoProgramacao);
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0457] Encerra Ordem de Serviço
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 27/09/2006
	 * 
	 * @throws ControladorException
	 */
	public boolean tramitarOuEncerrarRADaOSEncerrada(Integer numeroOS, Usuario usuarioLogado, String idMotivoEncerramento, String idRA, String dataRoteiro)
			throws ControladorException {
		boolean encerrarRA = false;
		// caso a OS tenha sido encerrada por um usuário associado a uma unidade
		// terceirizada
		Integer idOrdemServico = null;
		try {
			idOrdemServico = repositorioOrdemServico.verificarExistenciaUnidadeTerceirizada(usuarioLogado.getId());
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		if ((idOrdemServico != null && !idOrdemServico.equals("")) && (idRA != null && !idRA.equals(""))) {
			Tramite tramite = new Tramite();
			// id RA
			RegistroAtendimento registroAtendimento = new RegistroAtendimento();
			registroAtendimento.setId(Util.converterStringParaInteger(idRA));
			tramite.setRegistroAtendimento(registroAtendimento);

			if (usuarioLogado.getUnidadeOrganizacional() != null && !usuarioLogado.getUnidadeOrganizacional().equals("")) {
				// Unidade Origem
				UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
				unidadeOrganizacional.setId(usuarioLogado.getUnidadeOrganizacional().getId());
				tramite.setUnidadeOrganizacionalOrigem(unidadeOrganizacional);
				// Unidade destino
				// tramite.setUnidadeOrganizacionalDestino(unidadeOrganizacional);
				// alterado por Vivianne Sousa 11/07/2008
				// analista:Fabiola
				Integer idUnid = getControladorRegistroAtendimento().obterUnidadeOrigemDoUltimoTramiteRA(Util.converterStringParaInteger(idRA));
				UnidadeOrganizacional unidadeOrganizacionalDestino = new UnidadeOrganizacional();
				unidadeOrganizacionalDestino.setId(idUnid);
				tramite.setUnidadeOrganizacionalDestino(unidadeOrganizacionalDestino);

			}
			// id Usuario registro
			tramite.setUsuarioRegistro(usuarioLogado);
			// usuário registro
			tramite.setUsuarioResponsavel(usuarioLogado);
			tramite.setParecerTramite("TRAMITE GERADO PELO SISTEMA NO ENCERRAMENTO DA ORDEM DE SERVIÇO EXECUTADA POR UMA UNIDADE TERCEIRIZADA");
			tramite.setDataTramite(new Date());
			tramite.setUltimaAlteracao(new Date());
			getControladorRegistroAtendimento().tramitar(tramite, new Date());
			// Caso contrário
			// caso a OS esteja associada a um registro de atendimento
		} else {
			// caso exista RA para a OS
			if (idRA != null && !idRA.equals("")) {
				Collection colecaoRA = null;
				try {
					colecaoRA = repositorioOrdemServico.verificarOSparaRA(numeroOS, Util.converterStringParaInteger(idRA));
				} catch (ErroRepositorioException e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}
				// caso exista OS para a RA
				if (colecaoRA == null || colecaoRA.isEmpty()) {
					encerrarRA = true;
				}
			}
		}
		return encerrarRA;

	}

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Serviço
	 * 
	 * @author Raphael Rossiter
	 * @date 15/09/2006
	 * 
	 * @param numeroOS
	 * @return Collection<Atividade>
	 * @throws ErroRepositorioException
	 */
	public Collection<Atividade> obterAtividadesOrdemServico(Integer numeroOS) throws ControladorException {

		Collection<Atividade> retorno = null;

		try {

			retorno = repositorioOrdemServico.obterAtividadesOrdemServico(numeroOS);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		// [FS0002 - Verificar Existência de Dados]
		if (retorno == null || retorno.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.entidade_sem_dados_para_selecao", null, "ATIVIDADE");
		}

		return retorno;
	}

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Serviço
	 * 
	 * @author Raphael Rossiter
	 * @date 18/09/2006
	 * 
	 * @param numeroOS
	 * @return Collection<Equipe>
	 * @throws ErroRepositorioException
	 */
	public Collection<Equipe> obterEquipesProgramadas(Integer numeroOS) throws ControladorException {

		Collection colecaoEquipe = null;
		Collection<Equipe> retorno = new ArrayList();

		try {

			colecaoEquipe = repositorioOrdemServico.obterEquipesProgramadas(numeroOS);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		// [FS0002 - Verificar Existência de Dados]
		/*
		 * if (colecaoEquipe == null || colecaoEquipe.isEmpty()) {
		 * sessionContext.setRollbackOnly(); throw new ControladorException(
		 * "atencao.entidade_sem_dados_para_selecao", null, "EQUIPE"); }
		 */

		if (colecaoEquipe != null && !colecaoEquipe.isEmpty()) {

			Iterator equipeIterator = colecaoEquipe.iterator();
			Object[] arrayEquipe = null;
			Equipe equipe = null;

			while (equipeIterator.hasNext()) {

				equipe = new Equipe();
				arrayEquipe = (Object[]) equipeIterator.next();

				equipe.setId((Integer) arrayEquipe[0]);
				equipe.setNome((String) arrayEquipe[1]);

				retorno.add(equipe);
			}
		}

		return retorno;
	}

	/**
	 * 
	 * [UC0467] - Atualizar Ordem de Serviço
	 * 
	 * @author lms
	 * @date 18/09/2006
	 */
	public void atualizarOrdemServico(OrdemServico ordemServico, Usuario usuario) throws ControladorException {

		Calendar calendar = Calendar.getInstance();

		validarOrdemServico(ordemServico);

		ordemServico.setUltimaAlteracao(calendar.getTime());

		// ------------ REGISTRAR TRANSAÇÃO----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(Operacao.OPERACAO_ORDEM_SERVICO_ATUALIZAR, ordemServico.getId(),
				ordemServico.getId(), new UsuarioAcaoUsuarioHelper(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		registradorOperacao.registrarOperacao(ordemServico);
		// ------------ REGISTRAR TRANSAÇÃO----------------------------

		getControladorUtil().atualizar(ordemServico);

	}

	/**
	 * [UC1185] Informar Calibragem
	 * 
	 * @date 28/06/2011
	 * @author Thúlio Araújo
	 * @param osProgramacaoCalibragem
	 * @param usuario
	 * @throws ControladorException
	 */
	public void atualizarInformarCalibragem(OSProgramacaoCalibragem osProgramacaoCalibragem, Usuario usuario) throws ControladorException {

		// ------------ REGISTRAR TRANSAÇÃO----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(Operacao.OPERACAO_ATUALIZAR_CALIBRAGEM, osProgramacaoCalibragem.getId(),
				osProgramacaoCalibragem.getId(), new UsuarioAcaoUsuarioHelper(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		registradorOperacao.registrarOperacao(osProgramacaoCalibragem);

		getControladorTransacao().registrarTransacao(osProgramacaoCalibragem);
		// ------------ REGISTRAR TRANSAÇÃO----------------------------

		try {
			repositorioOrdemServico.atualizarInformarCalibragem(osProgramacaoCalibragem);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Serviço
	 * 
	 * @author Raphael Rossiter
	 * @date 19/09/2006
	 * 
	 * @param numeroOS
	 * @return boolean
	 * @throws ControladorException
	 */
	public boolean verificarOSAssociadaDocumentoCobranca(Integer numeroOS) throws ControladorException {

		Integer quantidadeOS = null;
		boolean retorno = false;

		try {

			quantidadeOS = repositorioOrdemServico.verificarOSAssociadaDocumentoCobranca(numeroOS);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (quantidadeOS != null && quantidadeOS > 0) {
			retorno = true;
		}

		return retorno;
	}

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Serviço
	 * 
	 * @author Raphael Rossiter
	 * @date 19/09/2006
	 * 
	 * @param numeroOS
	 * @return boolean
	 * @throws ControladorException
	 */
	public boolean verificarOSAssociadaRA(Integer numeroOS) throws ControladorException {

		Integer quantidadeOS = null;
		boolean retorno = false;

		try {

			quantidadeOS = repositorioOrdemServico.verificarOSAssociadaRA(numeroOS);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (quantidadeOS != null && quantidadeOS > 0) {
			retorno = true;
		}

		return retorno;
	}

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Serviço
	 * 
	 * @author Raphael Rossiter
	 * @date 19/09/2006
	 * 
	 * @param numeroOS
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer obterUnidadeDestinoUltimoTramite(Integer numeroOS) throws ControladorException {

		Integer idUnidadeDestino = null;

		try {

			idUnidadeDestino = repositorioOrdemServico.obterUnidadeDestinoUltimoTramite(numeroOS);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return idUnidadeDestino;
	}

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Serviço
	 * 
	 * @author Raphael Rossiter
	 * @date 19/09/2006
	 * 
	 * @param numeroOS
	 * @return Collection<Equipe>
	 * @throws ControladorException
	 */
	public Collection<Equipe> obterEquipesPorUnidade(Integer idUnidade) throws ControladorException {

		Collection colecaoEquipe = null;
		Collection<Equipe> retorno = new ArrayList();

		try {

			colecaoEquipe = repositorioOrdemServico.obterEquipesPorUnidade(idUnidade);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoEquipe != null && !colecaoEquipe.isEmpty()) {

			Iterator equipeIterator = colecaoEquipe.iterator();
			Object[] arrayEquipe = null;
			Equipe equipe = null;

			while (equipeIterator.hasNext()) {

				equipe = new Equipe();
				arrayEquipe = (Object[]) equipeIterator.next();

				equipe.setId((Integer) arrayEquipe[0]);
				equipe.setNome((String) arrayEquipe[1]);

				retorno.add(equipe);
			}
		}

		return retorno;
	}

	/**
	 * [UC0362] Efetuar Instalação de Hidrômetro
	 * 
	 * @author Leonardo Regis
	 * @date 19/09/2006
	 * 
	 * @param dadosOS
	 * @throws ControladorException
	 */
	public void atualizarOSParaEfetivacaoInstalacaoHidrometro(DadosAtualizacaoOSParaInstalacaoHidrometroHelper dadosOS) throws ControladorException {
		try {
			repositorioOrdemServico.atualizarOSParaEfetivacaoInstalacaoHidrometro(dadosOS);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Serviço
	 * 
	 * @author Raphael Rossiter
	 * @date 20/09/2006
	 * 
	 * @param dataExecucao
	 *            , horaInicio, horaFim, idEquipe
	 * @return void
	 * @throws ControladorException
	 */
	public void verificaDadosAdicionarPeriodoEquipe(String dataExecucao, String horaInicio, String horaFim, Integer idEquipe, String dataEncerramentoOS,
			Integer numeroOS) throws ControladorException {

		// [FS0005] - Validar data de execução
		Collection colecaoOrdemnServico = null;

		if (dataEncerramentoOS != null && !dataEncerramentoOS.equalsIgnoreCase("")) {

			try {
				colecaoOrdemnServico = repositorioOrdemServico.obterDatasGeracaoEncerramentoOS(numeroOS);

			} catch (ErroRepositorioException ex) {
				ex.printStackTrace();
				throw new ControladorException("erro.sistema", ex);
			}

			if (colecaoOrdemnServico != null && !colecaoOrdemnServico.isEmpty()) {

				Iterator osIterator = colecaoOrdemnServico.iterator();

				Object[] arrayOS = (Object[]) osIterator.next();

				Date datageracao = Util.converteStringParaDate(Util.formatarData((Date) arrayOS[0]));
				Date dataEncerramento = Util.converteStringParaDate(dataEncerramentoOS);
				Date dataExecucaoObjeto = Util.converteStringParaDate(dataExecucao);

				if (Util.formatarDataFinal(dataExecucaoObjeto).compareTo(Util.formatarDataFinal(datageracao)) == -1
						|| Util.formatarDataFinal(dataExecucaoObjeto).compareTo(Util

						.formatarDataFinal(dataEncerramento)) == 1) {

					throw new ControladorException("atencao.data_execucao_invalida");
				}
			}
		}

		// [FS0006] - Validar hora início e fim de execução
		if (Util.compararHoraMinuto(horaInicio, horaFim, ">")) {
			throw new ControladorException("atencao.hora_final_anterior_hora_inicio");
		}

		// [FS0007] - Verificar existência da equipe
		FiltroEquipe filtroEquipe = new FiltroEquipe();

		filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.ID, idEquipe));

		filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoEquipe = this.getControladorUtil().pesquisar(filtroEquipe, Equipe.class.getName());

		if (colecaoEquipe == null || colecaoEquipe.isEmpty()) {
			throw new ControladorException("atencao.pesquisa_inexistente", null, "Equipe");
		}
	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 11/09/2006
	 * 
	 * @param dataRoteiro
	 *            ,idUnidadeOrganizacional
	 * @return collection<OrdemServicoProgramacao>
	 * @throws ControladorException
	 */
	public Collection<OrdemServicoProgramacao> recuperaOSProgramacaoPorDataRoteiroUnidade(Date dataRoteiro, Integer idUnidadeOrganizacional)
			throws ControladorException {

		try {
			return repositorioOrdemServico.recuperaOSProgramacaoPorDataRoteiroUnidade(dataRoteiro, idUnidadeOrganizacional);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programação de Ordens de Serviço [FS0008]
	 * - Verificar possibilidade da inclusão da ordem de serviço
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @param ordemServico
	 * @throws ControladorException
	 */
	public void validarInclusaoOsNaProgramacao(OrdemServico ordemServico, Integer unidadeLotacao) throws ControladorException {

		try {
			// Caso 1
			if (ordemServico.getRegistroAtendimento() != null) {

				UnidadeOrganizacional unidadeAtual = this.obterUnidadeAtualOS(ordemServico.getId());

				if (unidadeAtual.getId().intValue() != unidadeLotacao.intValue()) {

					String[] parametros = new String[2];

					parametros[0] = ordemServico.getId().toString();
					parametros[1] = unidadeLotacao.toString();

					throw new ControladorException("atencao.unidade_lotacao_nao_encontra_ordem_servico", null, parametros);
				}

			} else {
				UnidadeOrganizacional unidadeAtual = this.repositorioOrdemServico.pesquisarUnidadeOrganizacionalPorUnidade(ordemServico
						.getRegistroAtendimento().getId(), unidadeLotacao);

				if (unidadeAtual != null && unidadeAtual.getId().intValue() == unidadeLotacao.intValue()) {

					String[] parametros = new String[2];

					parametros[0] = ordemServico.getId().toString();
					parametros[1] = unidadeLotacao.toString();

					throw new ControladorException("atencao.unidade_lotacao_nao_encontra_ordem_servico", null, parametros);
				}

			}

			// Caso 2
			if (ordemServico.getSituacao() == OrdemServico.SITUACAO_ENCERRADO.shortValue()) {
				throw new ControladorException("atencao.ordem_servico_encerrada_para_programacao");
			}

			// Caso 3
			OrdemServicoProgramacao osProgramacao = repositorioOrdemServico.pesquisarOSProgramacaoAtivaPorOS(ordemServico.getId());

			if (osProgramacao != null) {

				String[] parametros = new String[2];

				parametros[0] = osProgramacao.getEquipe().getNome();
				parametros[1] = Util.formatarData(osProgramacao.getProgramacaoRoteiro().getDataRoteiro());

				throw new ControladorException("atencao.ordem_servico_ja_programada", null, parametros);
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programação de Ordens de Serviço [FS0012]
	 * - Reordena Sequencial de Programação - Inclusão de Ordem de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @param ordemServico
	 * @throws ControladorException
	 */
	public void reordenaSequencialProgramacaoInclusaoOrdemServico(Date dataRoteiro, short sequencialAlterado) throws ControladorException {

		try {

			Collection<OrdemServicoProgramacao> colecaoOSProgramacao = this.repositorioOrdemServico.recuperaOSProgramacaoPorDataRoteiro(dataRoteiro);

			Iterator itera = colecaoOSProgramacao.iterator();

			while (itera.hasNext()) {
				OrdemServicoProgramacao osProgramacao = (OrdemServicoProgramacao) itera.next();

				short sequencial = osProgramacao.getNnSequencialProgramacao();

				if (sequencial == sequencialAlterado || sequencial > sequencialAlterado) {

					osProgramacao.setNnSequencialProgramacao(sequencial++);
					osProgramacao.setUltimaAlteracao(new Date());

					this.verificarOrdemServicoProgramacaoControleConcorrencia(osProgramacao);
					this.getControladorUtil().atualizar(osProgramacao);
				}
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programação de Ordens de Serviço [FS0012]
	 * Reordena Sequencial de Programação - Nova Ordem para Ordem de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @param ordemServico
	 * @throws ControladorException
	 */
	public void reordenaSequencialProgramacaoNovaOrdem(Date dataRoteiro, short sequencialInformado, short sequencialAtual, Integer idEquipe,
			boolean verificaOsPendente) throws ControladorException {

		try {

			int fatorAjusteSequencial = 0;
			if (sequencialInformado > sequencialAtual) {
				fatorAjusteSequencial = sequencialInformado - sequencialAtual;
			} else {
				fatorAjusteSequencial = sequencialAtual - sequencialInformado;
			}

			Collection<OrdemServicoProgramacao> colecaoOSProgramacao = this.repositorioOrdemServico.pesquisarOSProgramacaoComDataRoteiroIdEquipe(dataRoteiro,
					idEquipe);

			Iterator itera = colecaoOSProgramacao.iterator();

			while (itera.hasNext()) {
				OrdemServicoProgramacao osProgramacao = (OrdemServicoProgramacao) itera.next();

				short sequencial = osProgramacao.getNnSequencialProgramacao();

				osProgramacao.setUltimaAlteracao(new Date());

				if (sequencialAtual == sequencial) {
					osProgramacao.setNnSequencialProgramacao(sequencialInformado);
					osProgramacao.setIndicadorAcompanhamentoServico(ConstantesSistema.NAO);

					this.verificarOrdemServicoProgramacaoControleConcorrencia(osProgramacao);

					if (verificaOsPendente) {
						if (!Short.valueOf(osProgramacao.getOrdemServico().getSituacao()).equals(OrdemServico.SITUACAO_PENDENTE)) {
							throw new ControladorException("atencao.situacao.diferente_pendente", null, "reordenar o sequencial da");
						}
					}

					this.getControladorUtil().atualizar(osProgramacao);

					if (verificaOsPendente) {
						this.atualizarOrdemProgramacaoAcompanhamentoServico(idEquipe, dataRoteiro, osProgramacao.getOrdemServico().getId());
					}

				} else if (sequencial == sequencialInformado) {

					int diferenca = 0;

					if (sequencial > sequencialAtual) {
						diferenca = sequencialInformado - fatorAjusteSequencial;
					} else {
						diferenca = sequencialInformado + fatorAjusteSequencial;
					}

					osProgramacao.setNnSequencialProgramacao((short) diferenca);
					osProgramacao.setIndicadorAcompanhamentoServico(ConstantesSistema.NAO);

					this.verificarOrdemServicoProgramacaoControleConcorrencia(osProgramacao);

					if (verificaOsPendente) {
						if (!Short.valueOf(osProgramacao.getOrdemServico().getSituacao()).equals(OrdemServico.SITUACAO_PENDENTE)) {
							throw new ControladorException("atencao.situacao.diferente_pendente", null, "reordenar o sequencial da");
						}
					}

					this.getControladorUtil().atualizar(osProgramacao);

					if (verificaOsPendente) {
						this.atualizarOrdemProgramacaoAcompanhamentoServico(idEquipe, dataRoteiro, osProgramacao.getOrdemServico().getId());
					}
				}
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programação de Ordens de Serviço [FS0012]
	 * Reordena Sequencial de Programação
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @param ordemServico
	 * @throws ControladorException
	 */
	public void reordenarSequencialProgramacao(Date dataRoteiro, Integer idEquipe) throws ControladorException {

		try {

			Collection<OrdemServicoProgramacao> colecaoOSProgramacao = this.repositorioOrdemServico.pesquisarOSProgramacaoComDataRoteiroIdEquipeOrdenada(
					dataRoteiro, idEquipe);

			if (colecaoOSProgramacao != null && !colecaoOSProgramacao.isEmpty()) {

				short sequencialCorreto = 1;

				Iterator colecaoOSProgramacaoIterator = colecaoOSProgramacao.iterator();

				while (colecaoOSProgramacaoIterator.hasNext()) {

					OrdemServicoProgramacao ordemServicoProgramacao = (OrdemServicoProgramacao) colecaoOSProgramacaoIterator.next();

					if (ordemServicoProgramacao.getNnSequencialProgramacao() != sequencialCorreto) {
						ordemServicoProgramacao.setNnSequencialProgramacao(sequencialCorreto);
						getControladorUtil().atualizar(ordemServicoProgramacao);
					}

					sequencialCorreto++;
				}

			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0455] Exibir Calendário para Elaboração ou Acompanhamento de Roteiro
	 * 
	 * @author Rômulo Aurélio
	 * @date 21/09/2006
	 * 
	 * @param mesAnoReferencia
	 * @return Collection<ProgramacaoRoteiro>
	 * @throws ControladorException
	 */

	public Collection consultarProgramacaoRoteiro(String mesAnoReferencia, Integer unidadeOrganizacional) throws ControladorException {

		String ano = null;

		String mes = null;

		String anoMesReferencia = null;

		if (mesAnoReferencia == null || mesAnoReferencia.equals("")) {

			Date dataCorrente = new Date();
			String dataCorrenteTexto = Util.formatarData(dataCorrente);
			ano = dataCorrenteTexto.substring(6, 10);
			mes = dataCorrenteTexto.substring(3, 5);
			String anoMesCorrente = ano + mes;
			anoMesReferencia = anoMesCorrente;

		} else {

			boolean mesAnoValido = Util.validarMesAno(mesAnoReferencia);

			if (mesAnoValido == false) {
				throw new ControladorException("atencao.ano_mes_referencia.invalida");
			}

			mes = mesAnoReferencia.substring(0, 2);
			ano = mesAnoReferencia.substring(3, 7);

			anoMesReferencia = ano + mes;
		}

		Integer anoMesReferenciaSomada = Util.somaUmMesAnoMesReferencia(new Integer(anoMesReferencia));

		String dia = "01";

		String dataInicial = "" + anoMesReferencia + dia;

		dataInicial = Util.formatarData(dataInicial);

		Date dataInicialFormatada = Util.converteStringParaDate(dataInicial);

		// Colocado por Raphael Rossiter em 14/02/2007
		dataInicialFormatada = Util.formatarDataInicial(dataInicialFormatada);

		String dataFinal = "" + anoMesReferenciaSomada + dia;

		dataFinal = Util.formatarData(dataFinal);

		Date dataFinalFormatada = Util.converteStringParaDate(dataFinal);

		// Colocado por Raphael Rossiter em 14/02/2007
		dataFinalFormatada = Util.formatarDataFinal(dataFinalFormatada);

		FiltroProgramacaoRoteiro filtroProgramacaoRoteiro = new FiltroProgramacaoRoteiro();

		filtroProgramacaoRoteiro.adicionarParametro(new Intervalo(FiltroProgramacaoRoteiro.DATA_ROTEIRO, dataInicialFormatada, dataFinalFormatada));
		filtroProgramacaoRoteiro.setCampoOrderBy(FiltroProgramacaoRoteiro.DATA_ROTEIRO);

		filtroProgramacaoRoteiro.adicionarParametro(new ParametroSimples(FiltroProgramacaoRoteiro.UNIDADE_ORGANIZACIONAL_ID, unidadeOrganizacional));

		Collection colecaoProgramacaoRoteiro = getControladorUtil().pesquisar(filtroProgramacaoRoteiro, ProgramacaoRoteiro.class.getName());

		return colecaoProgramacaoRoteiro;

	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void incluirOrdemServicoProgramacao(OrdemServicoProgramacao ordemServicoProgramacao, Usuario usuarioLogado) throws ControladorException {

		try {
			short sequencial = ordemServicoProgramacao.getNnSequencialProgramacao();

			if (sequencial == ConstantesSistema.NUMERO_NAO_INFORMADO) {

				Collection<OrdemServicoProgramacao> colecao = this.repositorioOrdemServico.pesquisarOSProgramacaoComDataRoteiroIdEquipe(ordemServicoProgramacao
						.getProgramacaoRoteiro().getDataRoteiro(), ordemServicoProgramacao.getEquipe().getId());

				sequencial = this.retornaUltimoSequencial(colecao);
				sequencial++;
				ordemServicoProgramacao.setNnSequencialProgramacao(sequencial);
			}

			// [UC0107] - Registrar Transação
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(Operacao.OPERACAO_INSERIR_ORDEM_SERVICO_PROGRAMACAO_ACOMPANHAMENTO_ROTEIRO,
					new UsuarioAcaoUsuarioHelper(usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

			Operacao operacao = new Operacao();
			operacao.setId(Operacao.OPERACAO_INSERIR_ORDEM_SERVICO_PROGRAMACAO_ACOMPANHAMENTO_ROTEIRO);

			OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
			operacaoEfetuada.setOperacao(operacao);

			// [UC0107] - Registrar Transação
			ordemServicoProgramacao.setOperacaoEfetuada(operacaoEfetuada);
			ordemServicoProgramacao.setIndicadorAcompanhamentoServico((short) 2);
			ordemServicoProgramacao.adicionarUsuario(usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);

			registradorOperacao.registrarOperacao(ordemServicoProgramacao);

			// Colocado por Raphael Rossiter em 08/02/2007 (Mudança no UC)
			OrdemServico ordemServico = this.pesquisarOrdemServico(ordemServicoProgramacao.getOrdemServico().getId());
			ordemServico.setIndicadorProgramada(OrdemServico.PROGRAMADA);

			this.atualizarOrdemServico(ordemServico, usuarioLogado);

			this.getControladorUtil().inserir(ordemServicoProgramacao);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ControladorException
	 */

	private short retornaUltimoSequencial(Collection<OrdemServicoProgramacao> colecao) {

		short valorSequencial = 0;
		Iterator iter = colecao.iterator();
		while (iter.hasNext()) {
			OrdemServicoProgramacao ordemServicoProgramacao = (OrdemServicoProgramacao) iter.next();

			if (valorSequencial < ordemServicoProgramacao.getNnSequencialProgramacao()) {
				valorSequencial = ordemServicoProgramacao.getNnSequencialProgramacao();
			}
		}
		return valorSequencial;
	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void alocaEquipeParaOs(Integer numeroOS, Date dataRoteiro, Integer idEquipe, boolean naoRemoverOs, Integer idArquivo) throws ControladorException {

		try {
			OrdemServicoProgramacao ordemServicoProgramacao = this.repositorioOrdemServico.pesquisarOSProgramacaoAtivaComDataRoteiroIdEquipe(numeroOS,
					dataRoteiro, idEquipe);

			this.verificarOrdemServicoProgramacaoControleConcorrencia(ordemServicoProgramacao);

			short sequencialReferencia = ordemServicoProgramacao.getNnSequencialProgramacao();

			if (naoRemoverOs) {
				ordemServicoProgramacao.setIndicadorAcompanhamentoServico((short) 3);
				ordemServicoProgramacao.setUltimaAlteracao(new Date());
				this.getControladorUtil().atualizar(ordemServicoProgramacao);
				this.atualizarOrdemProgramacaoAcompanhamentoServico(idEquipe, dataRoteiro, numeroOS);
			} else {
				this.getControladorUtil().remover(ordemServicoProgramacao);
			}

			Collection<OrdemServicoProgramacao> colecaoExistentes = this.repositorioOrdemServico.pesquisarOSProgramacaoComDataRoteiroIdEquipeDiferenteOS(
					numeroOS, dataRoteiro, idEquipe);

			if (colecaoExistentes != null && !colecaoExistentes.isEmpty()) {
				if (!naoRemoverOs) {
					this.reordenaSequencialProgramacaoOSZerado(numeroOS, idEquipe, dataRoteiro, sequencialReferencia);
				}
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void atualizarOrdemServicoProgramacaoSituacaoOs(Integer numeroOS, Date dataRoteiro, short situacaoFechamento, Integer idOsProgramNaoEncerMotivo,
			boolean naoInformaIndicadorAtivo) throws ControladorException {

		try {

			Collection<Equipe> colecaoEquipes = this.repositorioOrdemServico.recuperaEquipeDaOSProgramacaoPorDataRoteiro(numeroOS, dataRoteiro);

			short indicadorAtivo = ConstantesSistema.NUMERO_NAO_INFORMADO;

			if (situacaoFechamento == OrdemServico.SITUACAO_PENDENTE.shortValue()
					|| situacaoFechamento == OrdemServico.SITUACAO_EXECUCAO_EM_ANDAMENTO.shortValue()) {

				indicadorAtivo = ConstantesSistema.NAO;
			}

			if (colecaoEquipes != null && !colecaoEquipes.isEmpty()) {
				Iterator itera = colecaoEquipes.iterator();
				while (itera.hasNext()) {

					Equipe equipe = (Equipe) itera.next();

					OrdemServicoProgramacao ordemServicoProgramacao = this.repositorioOrdemServico.pesquisarOSProgramacaoAtivaComDataRoteiroIdEquipe(numeroOS,
							dataRoteiro, equipe.getId());

					short sequencialReferencia = ordemServicoProgramacao.getNnSequencialProgramacao();
					if (naoInformaIndicadorAtivo) {
						if (indicadorAtivo != ConstantesSistema.NUMERO_NAO_INFORMADO) {
							ordemServicoProgramacao.setIndicadorAtivo(indicadorAtivo);
						}
					}

					ordemServicoProgramacao.setSituacaoFechamento(situacaoFechamento);

					if (idOsProgramNaoEncerMotivo != null) {
						OsProgramNaoEncerMotivo osProgramNaoEncerMotivo = new OsProgramNaoEncerMotivo();
						osProgramNaoEncerMotivo.setId(idOsProgramNaoEncerMotivo);

						ordemServicoProgramacao.setOsProgramNaoEncerMotivo(osProgramNaoEncerMotivo);

					}

					ordemServicoProgramacao.setUltimaAlteracao(new Date());

					this.verificarOrdemServicoProgramacaoControleConcorrencia(ordemServicoProgramacao);
					this.getControladorUtil().atualizar(ordemServicoProgramacao);

					this.reordenaSequencialProgramacaoOSZerado(numeroOS, equipe.getId(), dataRoteiro, sequencialReferencia);

				}
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ControladorException
	 */
	private void reordenaSequencialProgramacaoOSZerado(Integer numeroOs, Integer idEquipe, Date dataRoteiro, short sequencialReferencia)
			throws ControladorException {

		try {

			Collection<OrdemServicoProgramacao> colecaoOsProgramacao = this.repositorioOrdemServico.pesquisarOSProgramacaoComSequencialMaior(numeroOs,
					dataRoteiro, idEquipe, sequencialReferencia);

			if (colecaoOsProgramacao != null && !colecaoOsProgramacao.isEmpty()) {

				Iterator itera = colecaoOsProgramacao.iterator();
				while (itera.hasNext()) {
					OrdemServicoProgramacao osProgramacao = (OrdemServicoProgramacao) itera.next();

					short seq = osProgramacao.getNnSequencialProgramacao();

					osProgramacao.setNnSequencialProgramacao(seq--);
					osProgramacao.setIndicadorAcompanhamentoServico(ConstantesSistema.NAO);
					osProgramacao.setUltimaAlteracao(new Date());

					this.verificarOrdemServicoProgramacaoControleConcorrencia(osProgramacao);
					this.getControladorUtil().atualizar(osProgramacao);

				}

			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * Método que valida a ordem de serviço utilizado por diversos outros
	 * métodos do atendimento ao público
	 * 
	 * @author Leonardo Regis
	 * @date 22/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void validaOrdemServico(OrdemServico ordemServico, boolean veioEncerrarOS) throws ControladorException {

		if (!veioEncerrarOS) {

			if (ordemServico.getSituacao() != OrdemServico.SITUACAO_ENCERRADO.shortValue()) {

				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.ordem_servico_situacao", null, OrdemServico.SITUACAO_DESCRICAO_ENCERRADO);
			} else {
				if (ordemServico.getAtendimentoMotivoEncerramento() != null
						&& ordemServico.getAtendimentoMotivoEncerramento().getIndicadorExecucao() != ConstantesSistema.SIM) {

					sessionContext.setRollbackOnly();
					throw new ControladorException("atencao.ordem_servico_nao_executada", null, OrdemServico.SITUACAO_DESCRICAO_ENCERRADO_NAO_EXECUTADA);
				}
			}

			if (new Integer(ordemServico.getIndicadorComercialAtualizado()).intValue() == ConstantesSistema.SIM.intValue()) {

				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.ordem_servico_sistema_comercial_atualizado");
			}
		}
	}

	/**
	 * Método que valida a ordem de serviço utilizado por diversos outros
	 * métodos do atendimento ao público sem a validação de indicador comercial
	 * 
	 * @author Rafael Francisco Pinto
	 * @date 12/10/2006
	 * 
	 * @throws ControladorException
	 */
	public void validaOrdemServicoAtualizacao(OrdemServico ordemServico, boolean veioEncerrarOS) throws ControladorException {

		if (!veioEncerrarOS) {

			if (ordemServico.getSituacao() != OrdemServico.SITUACAO_ENCERRADO.shortValue()) {

				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.ordem_servico_situacao", null, OrdemServico.SITUACAO_DESCRICAO_ENCERRADO);
			} else {
				if (ordemServico.getAtendimentoMotivoEncerramento() != null
						&& ordemServico.getAtendimentoMotivoEncerramento().getIndicadorExecucao() != ConstantesSistema.SIM) {

					sessionContext.setRollbackOnly();
					throw new ControladorException("atencao.ordem_servico_nao_executada", null, OrdemServico.SITUACAO_DESCRICAO_ENCERRADO_NAO_EXECUTADA);
				}
			}

		}
	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 25/09/2006
	 * 
	 * @param idOs
	 *            ,dataRoteiro
	 * @return Equipe
	 * @throws ErroRepositorioException
	 */

	public Collection<Equipe> recuperaEquipeDaOSProgramacaoPorDataRoteiro(Integer idOs, Date dataRoteiro) throws ControladorException {

		try {
			return this.repositorioOrdemServico.recuperaEquipeDaOSProgramacaoPorDataRoteiro(idOs, dataRoteiro);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * Atualização Geral de OS
	 * 
	 * @author Leonardo Regis
	 * @date 25/09/2006
	 * 
	 * @param os
	 * @throws ControladorException
	 */
	public void atualizaOSGeral(OrdemServico os) throws ControladorException {
		try {
			this.repositorioOrdemServico.atualizaOSGeral(os);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0457] Encerrar Ordem de Serviço
	 * 
	 * @author Sávio Luiz
	 * @date 25/09/2006
	 * 
	 * @param numeroOS
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarColecaoServicoTipo(Integer numeroOS) throws ControladorException {
		Collection colecaoServicoTipo = null;
		try {
			Collection colecaoEspecificacaoServicoTipo = this.repositorioOrdemServico.pesquisarSolicitacoesServicoTipoOS(numeroOS);
			if (colecaoEspecificacaoServicoTipo != null && !colecaoEspecificacaoServicoTipo.isEmpty()) {
				Iterator iteEspecificacaoServicoTipo = colecaoEspecificacaoServicoTipo.iterator();
				Collection colecaoIdsServicoTipo = new ArrayList();
				while (iteEspecificacaoServicoTipo.hasNext()) {
					EspecificacaoServicoTipo espcificacaoServicoTipo = (EspecificacaoServicoTipo) iteEspecificacaoServicoTipo.next();
					colecaoIdsServicoTipo.add(espcificacaoServicoTipo.getComp_id().getIdServicoTipo());
				}
				colecaoServicoTipo = repositorioOrdemServico.pesquisarServicoTipo(colecaoIdsServicoTipo);
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		return colecaoServicoTipo;
	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 25/09/2006
	 * 
	 * @param idOs
	 *            ,dataRoteiro
	 * @return Equipe
	 * @throws ErroRepositorioException
	 */
	public void verificaExitenciaProgramacaoAtivaParaDiasAnteriores(Integer idOs, Date dataRoteiro) throws ControladorException {

		try {

			boolean retorno = this.repositorioOrdemServico.verificaExitenciaProgramacaoAtivaParaDiasAnteriores(idOs, dataRoteiro);

			if (retorno) {
				throw new ControladorException("atencao.programacao_ativa_dias_anteriores");
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * Faz o controle de concorrencia da ordem servico
	 * 
	 * @author Rafael Pinto
	 * @throws ControladorException
	 */
	public void verificarOrdemServicoControleConcorrencia(OrdemServico ordemServico) throws ControladorException {

		FiltroOrdemServico filtroOrdeServico = new FiltroOrdemServico();
		filtroOrdeServico.adicionarParametro(new ParametroSimples(FiltroOrdemServico.ID, ordemServico.getId()));

		Collection colecaoOrdemServicoBase = getControladorUtil().pesquisar(filtroOrdeServico, OrdemServico.class.getName());

		if (colecaoOrdemServicoBase == null || colecaoOrdemServicoBase.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		OrdemServico ordemServicoAtual = (OrdemServico) Util.retonarObjetoDeColecao(colecaoOrdemServicoBase);

		if (ordemServicoAtual.getUltimaAlteracao().after(ordemServico.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}
	}

	/**
	 * [UC0391] Inserir Valor de Cobrança de Serviço.
	 * 
	 * Permite a inclusão de um novo valor de cobrança de serviço na tabela
	 * SERVICO_COBRANCA_VALOR.
	 * 
	 * @author Leonardo Regis
	 * @date 29/09/2006
	 * 
	 * @param servicoCobrancaValor
	 * @throws ControladorException
	 */
	public void inserirValorCobrancaServico(ServicoCobrancaValor servicoCobrancaValor) throws ControladorException {
		/* [SB0001] Gerar Valor da cobrança do Serviço */
		getControladorUtil().inserir(servicoCobrancaValor);
	}

	/**
	 * [UC0391] Atualizar Valor de Cobrança de Serviço.
	 * 
	 * Permite a atualização de um novo valor de cobrança de serviço na tabela
	 * SERVICO_COBRANCA_VALOR.
	 * 
	 * @author Rômulo Aurélio
	 * @date 01/11/2006
	 * 
	 * @param servicoCobrancaValor
	 * @throws ControladorException
	 */
	public void atualizarValorCobrancaServico(ServicoCobrancaValor servicoCobrancaValor, Usuario usuarioLogado) throws ControladorException {

		// [FS0002] - Atualização realizada por outro usuário
		FiltroServicoCobrancaValor filtroServicoCobrancaValor = new FiltroServicoCobrancaValor();
		filtroServicoCobrancaValor.adicionarParametro(new ParametroSimples(FiltroServicoCobrancaValor.ID, servicoCobrancaValor.getId()));

		Collection colecaoServicoCobrancaValorBase = getControladorUtil().pesquisar(filtroServicoCobrancaValor, ServicoCobrancaValor.class.getName());

		if (colecaoServicoCobrancaValorBase == null || colecaoServicoCobrancaValorBase.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		ServicoCobrancaValor servicoCobrancaValorBase = (ServicoCobrancaValor) colecaoServicoCobrancaValorBase.iterator().next();

		if (servicoCobrancaValorBase.getUltimaAlteracao().after(servicoCobrancaValor.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		filtroServicoCobrancaValor.limparListaParametros();

		servicoCobrancaValor.setUltimaAlteracao(new Date());

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(Operacao.OPERACAO_VALOR_COBRANCA_SERVICO_ATUALIZAR, servicoCobrancaValor
				.getServicoTipo().getId(), servicoCobrancaValor.getServicoTipo().getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		registradorOperacao.registrarOperacao(servicoCobrancaValor);

		getControladorUtil().atualizar(servicoCobrancaValor);
	}

	/**
	 * [UC0298] Manter Tipo de Retorno da OS_Referida [] Atualizar Tipo de
	 * Retorno da OS_Referida Metodo que atualiza a Situação Usuario
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 25/05/2006
	 * 
	 * @param Tipo
	 *            de Retorno da OS_Referida Usuário
	 * @throws ControladorException
	 */

	public void atualizarTipoRetornoOrdemServicoReferida(OsReferidaRetornoTipo osReferidaRetornoTipo) throws ControladorException {

		// Verifica se todos os campos obrigatorios foram preenchidos

		if ((osReferidaRetornoTipo.getDescricao() == null || osReferidaRetornoTipo.getDescricao().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (osReferidaRetornoTipo.getDescricaoAbreviada() == null || osReferidaRetornoTipo.getDescricaoAbreviada().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (osReferidaRetornoTipo.getServicoTipoReferencia() == null || osReferidaRetornoTipo.getServicoTipoReferencia().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (osReferidaRetornoTipo.getIndicadorTrocaServico() == null || osReferidaRetornoTipo.getIndicadorTrocaServico().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (osReferidaRetornoTipo.getSituacaoOsReferencia() == null || osReferidaRetornoTipo.getSituacaoOsReferencia().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (osReferidaRetornoTipo.getAtendimentoMotivoEncerramento() == null || osReferidaRetornoTipo.getAtendimentoMotivoEncerramento().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO))

				&& (osReferidaRetornoTipo.getIndicadorTrocaServico() == null || osReferidaRetornoTipo.getIndicadorTrocaServico().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			throw new ControladorException("atencao.filtro.nenhum_parametro_informado");

		}

		// Verifica se o campo Descrição foi preenchido

		if (osReferidaRetornoTipo.getDescricao() == null || osReferidaRetornoTipo.getDescricao().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null, " Descrição");
		}

		// Verifica se o campo Referência do Tipo de Serviço foi preenchido
		if (osReferidaRetornoTipo.getServicoTipoReferencia() == null
				|| osReferidaRetornoTipo.getServicoTipoReferencia().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null, " Referência do Tipo de Serviço");
		}

		// Verifica se o campo Indicador de Troca de Serviço foi preenchido

		if (osReferidaRetornoTipo.getIndicadorTrocaServico() == null
				|| osReferidaRetornoTipo.getIndicadorTrocaServico().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ControladorException("atencao.Informe_entidade", null, " Indicador de Troca de Serviço");
		}

		// Verifica se o campo Código da Situação foi preenchido

		// if (osReferidaRetornoTipo.getSituacaoOsReferencia() == null
		// || osReferidaRetornoTipo.getSituacaoOsReferencia().equals(
		// "" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
		// throw new ControladorException("atencao.Informe_entidade", null,
		// " Código da Situação");
		// }

		// [FS0003] - Atualização realizada por outro usuário
		FiltroOSReferidaRetornoTipo filtroOSReferidaRetornoTipo = new FiltroOSReferidaRetornoTipo();
		filtroOSReferidaRetornoTipo.adicionarParametro(new ParametroSimples(FiltroOSReferidaRetornoTipo.ID, osReferidaRetornoTipo.getId()));

		Collection colecaoTipoRetornoOsReferidaBase = getControladorUtil().pesquisar(filtroOSReferidaRetornoTipo, OsReferidaRetornoTipo.class.getName());

		if (colecaoTipoRetornoOsReferidaBase == null || colecaoTipoRetornoOsReferidaBase.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		OsReferidaRetornoTipo osReferidaRetornoTipoBase = (OsReferidaRetornoTipo) colecaoTipoRetornoOsReferidaBase.iterator().next();

		if (osReferidaRetornoTipo.getUltimaAlteracao().after(osReferidaRetornoTipoBase.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		osReferidaRetornoTipo.setUltimaAlteracao(new Date());

		getControladorUtil().atualizar(osReferidaRetornoTipo);

	}

	/**
	 * [UC0458] - Imprimir Ordem de Serviço
	 * 
	 * Pesquisa os campos da OS que serão impressos no relatório de Ordem de
	 * Servico
	 * 
	 * @author Rafael Corrêa
	 * @date 17/10/2006
	 * 
	 * @param idOrdemServico
	 * @return OSRelatorioHelper
	 * @throws ControladorException
	 */
	public Collection pesquisarOSRelatorio(Collection idsOrdemServico) throws ControladorException {

		Object[] dadosOS = null;

		// Coleção de todas os dados das OS
		Collection colecaoDadosOS = null;

		Collection colecaoOSRelatorioHelper = new ArrayList();

		try {
			colecaoDadosOS = this.repositorioOrdemServico.pesquisarOSRelatorio(idsOrdemServico);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoDadosOS != null && !colecaoDadosOS.isEmpty()) {

			Iterator colecaoDadosOSIterator = colecaoDadosOS.iterator();

			while (colecaoDadosOSIterator.hasNext()) {

				dadosOS = (Object[]) colecaoDadosOSIterator.next();

				if (dadosOS != null) {

					OSRelatorioHelper oSRelatorioHelper = new OSRelatorioHelper();

					// Data da Geração
					if (dadosOS[0] != null) { // 0
						oSRelatorioHelper.setDataGeracao((Date) dadosOS[0]);
					}

					Imovel imovel = new Imovel();

					// Localidade
					if (dadosOS[1] != null) { // 1
						Localidade localidade = new Localidade();
						localidade.setId((Integer) dadosOS[1]);
						oSRelatorioHelper.setIdLocalidade((Integer) dadosOS[1]);
						imovel.setLocalidade(localidade);
					}

					// Setor Comercial
					if (dadosOS[2] != null) { // 2
						SetorComercial setorComercial = new SetorComercial();
						setorComercial.setCodigo((Integer) dadosOS[2]);
						imovel.setSetorComercial(setorComercial);
					}

					// Quadra
					if (dadosOS[3] != null) { // 3
						Quadra quadra = new Quadra();
						quadra.setNumeroQuadra((Integer) dadosOS[3]);
						imovel.setQuadra(quadra);
					}

					// Lote
					if (dadosOS[4] != null) { // 4
						imovel.setLote((Short) dadosOS[4]);
					}

					// SubLote
					if (dadosOS[5] != null) { // 5
						imovel.setSubLote((Short) dadosOS[5]);
					}

					// Inscrição do Imóvel
					String inscricao = "";

					if (imovel.getLocalidade() != null) {
						inscricao = imovel.getInscricaoFormatada();
					}
					oSRelatorioHelper.setInscricaoImovel(inscricao);

					// Matrícula do Imóvel
					if (dadosOS[6] != null) { // 6
						oSRelatorioHelper.setIdImovel((Integer) dadosOS[6]);
						// Seta o id no objeto imóvel para pesquisar,
						// posteriormente, a
						// descrição da categoria e a quantidade de economias
						imovel.setId((Integer) dadosOS[6]);

						Cliente clienteUsuario = this.getControladorImovel().pesquisarClienteUsuarioImovel(imovel.getId());

						if (clienteUsuario != null) {
							oSRelatorioHelper.setNomeUsuarioImovel(clienteUsuario.getNome());
							oSRelatorioHelper.setCpfCliente(clienteUsuario.getCpf());
							oSRelatorioHelper.setCnpjCliente(clienteUsuario.getCnpj());
						}

					}

					// Categoria e Quantidade de Economias
					if (imovel.getId() != null) {
						Categoria categoria = getControladorImovel().obterDescricoesCategoriaImovel(imovel);
						oSRelatorioHelper.setCategoria(categoria.getDescricaoAbreviada());

						int quantidadeEconomias = getControladorImovel().obterQuantidadeEconomias(imovel);
						oSRelatorioHelper.setQuantidadeEconomias(quantidadeEconomias);
					}

					// Situação da Ligação de Água
					if (dadosOS[7] != null) { // 7
						oSRelatorioHelper.setSituacaoAgua((String) dadosOS[7]);
					}

					// Situação da Ligação de Esgoto
					if (dadosOS[8] != null) { // 8
						oSRelatorioHelper.setSituacaoEsgoto((String) dadosOS[8]);
					}

					// Esgoto Fixo
					if (dadosOS[9] != null) { // 9
						oSRelatorioHelper.setEsgotoFixo((Integer) dadosOS[9]);
					}

					// Pavimento Rua
					if (dadosOS[10] != null) { // 10
						oSRelatorioHelper.setPavimentoRua((String) dadosOS[10]);
					} else {
						oSRelatorioHelper.setPavimentoRua("");
					}

					// Pavimento Calçada
					if (dadosOS[11] != null) { // 11
						oSRelatorioHelper.setPavimentoCalcada((String) dadosOS[11]);
					} else {
						oSRelatorioHelper.setPavimentoCalcada("");
					}

					// Meio
					if (dadosOS[12] != null) { // 12
						oSRelatorioHelper.setMeio((String) dadosOS[12]);
					} else {
						oSRelatorioHelper.setMeio("");
					}

					// Nome do Atendente
					if (dadosOS[13] != null) { // 13
						oSRelatorioHelper.setNomeAtendente((String) dadosOS[13]);
					}

					// Matrícula do Atendente
					if (dadosOS[14] != null) { // 14
						oSRelatorioHelper.setIdAtendente((Integer) dadosOS[14]);
					}

					// Ponto de Referência
					if (dadosOS[15] != null) { // 15
						oSRelatorioHelper.setPontoReferencia((String) dadosOS[15]);

					} else {
						oSRelatorioHelper.setPontoReferencia("");
					}

					// Id Serviço Solicitado
					if (dadosOS[16] != null) { // 16
						oSRelatorioHelper.setIdServicoSolicitado((Integer) dadosOS[16]);
					}

					// Descrição Serviço Solicitado
					if (dadosOS[17] != null) { // 17
						oSRelatorioHelper.setDescricaoServicoSolicitado((String) dadosOS[17]);
					}

					// Valor Serviço Solicitado
					if (dadosOS[31] != null) { // 31
						oSRelatorioHelper.setValorServicoSolicitado(Util.formatarMoedaReal((BigDecimal) dadosOS[31]));
					}

					// Local Ocorrência
					if (dadosOS[18] != null) { // 18
						oSRelatorioHelper.setLocalOcorrencia((String) dadosOS[18]);
					}

					// Data Previsão Atual
					if (dadosOS[19] != null) { // 19
						oSRelatorioHelper.setPrevisao((Date) dadosOS[19]);
					}

					// Observação do RA
					if (dadosOS[20] != null) { // 20
						oSRelatorioHelper.setObservacaoRA((String) dadosOS[20]);
					} else {
						oSRelatorioHelper.setObservacaoRA("");
					}

					// Observação da OS
					if (dadosOS[21] != null) { // 21
						oSRelatorioHelper.setObservacaoOS((String) dadosOS[21]);
					} else {
						oSRelatorioHelper.setObservacaoOS("");
					}

					// Id do RA
					if (dadosOS[22] != null) { // 22
						oSRelatorioHelper.setIdRA((Integer) dadosOS[22]);
					}

					// Especificação
					if (dadosOS[23] != null) { // 23
						oSRelatorioHelper.setEspecificao((String) dadosOS[23]);
					}

					// Id da OS
					if (dadosOS[24] != null) { // 24
						oSRelatorioHelper.setIdOrdemServico((Integer) dadosOS[24]);
					}

					// Tempo Médio Execução
					if (dadosOS[25] != null) { // 25
						oSRelatorioHelper.setTempoMedioExecucao((Short) dadosOS[25]);
					}

					// Origem
					if (dadosOS[26] != null) { // 26
						oSRelatorioHelper.setOrigem((String) dadosOS[26]);
					} else {
						oSRelatorioHelper.setOrigem("");
					}

					// Sequencial Rota
					if (dadosOS[27] != null) { // 27
						oSRelatorioHelper.setSequencialRota((Integer) dadosOS[27]);
					}

					// Rota
					if (dadosOS[28] != null) { // 28
						oSRelatorioHelper.setCodigoRota((Short) dadosOS[28]);
					}

					// Id do Serviço Tipo Referência
					if (dadosOS[29] != null) { // 29
						oSRelatorioHelper.setIdServicoTipoReferencia((Integer) dadosOS[29]);
						FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
						filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, (Integer) dadosOS[29]));
						Collection servicoTipos = getControladorUtil().pesquisar(filtroServicoTipo, ServicoTipo.class.getName());
						ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(servicoTipos);
						oSRelatorioHelper.setDescricaoServicoTipoReferencia(servicoTipo.getDescricao());
					}

					// Data do Encerramento
					if (dadosOS[32] != null) { // 32
						oSRelatorioHelper.setDataEncerramento((Date) dadosOS[32]);
					}

					// Parecer do Encerramento
					if (dadosOS[33] != null) { // 33
						oSRelatorioHelper.setParecerEncerramento((String) dadosOS[33]);
					}

					// Data Emissao
					if (dadosOS[35] != null) { // 33
						oSRelatorioHelper.setDataEmissao((Date) dadosOS[35]);
					}

					if (oSRelatorioHelper.getIdRA() != null) {

						Object[] dadosSolicitante = getControladorRegistroAtendimento().pesquisarDadosRegistroAtendimentoSolicitanteRelatorioOS(
								oSRelatorioHelper.getIdRA());

						if (dadosSolicitante != null) {

							String telefone = "";

							// Id do Cliente
							if (dadosSolicitante[0] != null) { // 0
								oSRelatorioHelper.setIdSolicitante((Integer) dadosSolicitante[0]);

								// Seta o valor do telefone a partir de cliente
								// fone,
								// caso o id do cliente seja diferente de nulo
								telefone = getControladorCliente().pesquisarClienteFonePrincipal(oSRelatorioHelper.getIdSolicitante());
								oSRelatorioHelper.setTelefone(telefone);
							} else {
								// Seta o valor do telefone a partir de
								// solicitante
								// fone,
								// caso o id do cliente seja nulo
								telefone = getControladorRegistroAtendimento().pesquisarSolicitanteFonePrincipal(oSRelatorioHelper.getIdRA());
								oSRelatorioHelper.setTelefone(telefone);
							}

							// Id da Unidade
							if (dadosSolicitante[2] != null) { // 2
								oSRelatorioHelper.setIdUnidade((Integer) dadosSolicitante[2]);
							}

							// Descrição da Unidade
							if (dadosSolicitante[3] != null) { // 3
								oSRelatorioHelper.setDescricaoUnidade((String) dadosSolicitante[3]);
							}

							// Verificamos quem será considerado solicitante
							// Se o id do cliente foi encontrado, é o
							// solicitante
							if (dadosSolicitante[0] != null) {
								oSRelatorioHelper.setNomeSolicitante((String) dadosSolicitante[1]);
								// Se id da unidade foi encontrada, é o
								// solicitante
							} else if (dadosSolicitante[2] != null) {
								oSRelatorioHelper.setNomeSolicitante((String) dadosSolicitante[3]);
								// Se id do funcionário foi encontrada, é o
								// solicitante
							} else if (dadosSolicitante[4] != null) {
								oSRelatorioHelper.setNomeSolicitante((String) dadosSolicitante[5]);
							} else {
								oSRelatorioHelper.setNomeSolicitante((String) dadosSolicitante[6]);
							}

							// Endereço
							String endereco = getControladorRegistroAtendimento().obterEnderecoOcorrenciaRA(oSRelatorioHelper.getIdRA());
							oSRelatorioHelper.setEndereco(endereco);

						}

						// eh documento de cobrança
					} else if (dadosOS[34] != null) {

						// colocado por flávio leonardo - a pedido de Rafael
						// pinto e claudio lira
						// 18/09/2008
						// caso n exista RA para a OS pegar os dados do
						// cliente/Endereço
						// do documento de cobranca da OS.
						FiltroCobrancaDocumento filtroCobrancaDocumento = new FiltroCobrancaDocumento();
						filtroCobrancaDocumento.adicionarCaminhoParaCarregamentoEntidade("cliente");
						filtroCobrancaDocumento.adicionarCaminhoParaCarregamentoEntidade("imovel");
						filtroCobrancaDocumento.adicionarParametro(new ParametroSimples(FiltroCobrancaDocumento.ID, (Integer) dadosOS[34]));

						Collection colecaoCobrancaDocumento = getControladorUtil().pesquisar(filtroCobrancaDocumento, CobrancaDocumento.class.getName());

						if (colecaoCobrancaDocumento != null && !colecaoCobrancaDocumento.isEmpty()) {

							CobrancaDocumento cobrancaDocumento = (CobrancaDocumento) colecaoCobrancaDocumento.iterator().next();

							Cliente cliente = getControladorImovel().pesquisarClienteUsuarioImovel(cobrancaDocumento.getImovel().getId());

							// idCliente
							oSRelatorioHelper.setIdSolicitante(cliente.getId());

							// Id do documento de cobrança
							// colocado por José Guilherme - 29/05/2009 - 10:05
							oSRelatorioHelper.setIdCobrancaDocumento(cobrancaDocumento.getId() + "");

							// Seta o valor do telefone a partir de cliente
							// fone,
							// caso o id do cliente seja diferente de nulo
							String telefone = getControladorCliente().pesquisarClienteFonePrincipal(oSRelatorioHelper.getIdSolicitante());
							oSRelatorioHelper.setTelefone(telefone);

							// Nome Cliente
							oSRelatorioHelper.setNomeSolicitante(cliente.getNome());

							// CPF do Cliente
							oSRelatorioHelper.setCpfCliente(cliente.getCpf());

							// CNPJ do Cliente
							oSRelatorioHelper.setCnpjCliente(cliente.getCnpj());

							// Endereço
							String endereco = getControladorEndereco().pesquisarEndereco(cobrancaDocumento.getImovel().getId());

							oSRelatorioHelper.setEndereco(endereco);

						}

						// eh ordem de servico seletiva
					} else {

						Cliente cliente = getControladorImovel().pesquisarClienteUsuarioImovel(imovel.getId());

						// idCliente
						oSRelatorioHelper.setIdSolicitante(cliente.getId());

						// Seta o valor do telefone a partir de cliente
						// fone,
						// caso o id do cliente seja diferente de nulo
						String telefone = getControladorCliente().pesquisarClienteFonePrincipal(oSRelatorioHelper.getIdSolicitante());
						oSRelatorioHelper.setTelefone(telefone);

						// Nome Cliente
						oSRelatorioHelper.setNomeSolicitante(cliente.getNome());

						// CPF do Cliente
						oSRelatorioHelper.setCpfCliente(cliente.getCpf());

						// CNPJ do Cliente
						oSRelatorioHelper.setCnpjCliente(cliente.getCnpj());

						// Endereço
						String endereco = getControladorEndereco().pesquisarEndereco(imovel.getId());

						oSRelatorioHelper.setEndereco(endereco);

					}
					if (dadosOS[36] != null) {
						FiltroProjeto filtroProjeto = new FiltroProjeto();

						Integer idProjeto = (Integer) dadosOS[36];

						filtroProjeto.adicionarParametro(new ParametroSimples(FiltroProjeto.ID, idProjeto));

						Collection projetos = getControladorUtil().pesquisar(filtroProjeto, Projeto.class.getName());

						Projeto projeto = (Projeto) Util.retonarObjetoDeColecao(projetos);

						if (projeto != null) {
							oSRelatorioHelper.setNomeProjeto(projeto.getNome());
						}
					}
					colecaoOSRelatorioHelper.add(oSRelatorioHelper);
				}
			}
		}

		return colecaoOSRelatorioHelper;

	}

	/**
	 * [UC0482] - Obter Endereço Abreviado da Ocorrência do RA
	 * 
	 * Pesquisa o Endereco Abreviado da OS
	 * 
	 * @author Rafael Corrêa
	 * @date 19/10/2006
	 * 
	 * 
	 * @param idOrdemServico
	 * @throws ControladorException
	 */

	public String obterEnderecoAbreviadoOS(Integer idOrdemServico) throws ControladorException {
		Object[] dadosOS = null;
		try {
			dadosOS = repositorioOrdemServico.obterDadosPesquisaEnderecoAbreviadoOS(idOrdemServico);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		String endereco = "";

		if (dadosOS != null) {

			// Id do RA
			if (dadosOS[0] != null) {
				Integer idRa = (Integer) dadosOS[0];
				endereco = getControladorRegistroAtendimento().obterEnderecoAbreviadoOcorrenciaRA(idRa);
			}

			// Id do Imóvel
			if (dadosOS[1] != null) {
				Integer idImovel = (Integer) dadosOS[1];
				endereco = getControladorEndereco().obterEnderecoAbreviadoImovel(idImovel);
			}

		}

		return endereco;
	}

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Serviço
	 * 
	 * @author Raphael Rossiter
	 * @date 15/09/2006
	 * 
	 * @param numeroOS
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection<Material> obterMateriaisProgramados(Integer numeroOS) throws ControladorException {

		Collection colecaoMaterial = null;
		Collection<Material> retorno = new ArrayList();

		try {

			colecaoMaterial = repositorioOrdemServico.obterMateriaisProgramados(numeroOS);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		// [FS0002 - Verificar Existência de Dados]
		/*
		 * if (colecaoEquipe == null || colecaoEquipe.isEmpty()) {
		 * sessionContext.setRollbackOnly(); throw new ControladorException(
		 * "atencao.entidade_sem_dados_para_selecao", null, "EQUIPE"); }
		 */

		if (colecaoMaterial != null && !colecaoMaterial.isEmpty()) {

			Iterator materialIterator = colecaoMaterial.iterator();
			Object[] arrayMaterial = null;
			Material material = null;

			while (materialIterator.hasNext()) {

				material = new Material();
				arrayMaterial = (Object[]) materialIterator.next();

				material.setId((Integer) arrayMaterial[0]);
				material.setDescricao((String) arrayMaterial[1]);

				retorno.add(material);
			}
		}

		return retorno;
	}

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Serviço
	 * 
	 * @author Raphael Rossiter
	 * @date 15/09/2006
	 * 
	 * @param numeroOS
	 *            , idMaterial
	 * @return BigDecimal
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterQuantidadePadraoMaterial(Integer numeroOS, Integer idMaterial) throws ControladorException {

		BigDecimal retorno = null;

		try {

			retorno = repositorioOrdemServico.obterQuantidadePadraoMaterial(numeroOS, idMaterial);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 * Imprimir OS
	 * 
	 * Atualiza a data de emissão e a de última alteração de OS quando gerar o
	 * relatório
	 * 
	 * @author Rafael Corrêa
	 * @date 26/10/2006
	 * 
	 * 
	 * @param colecaoIdsOrdemServico
	 * @throws ControladorException
	 */
	public void atualizarOrdemServicoRelatorio(Collection colecaoIdsOrdemServico) throws ControladorException {

		try {

			this.repositorioOrdemServico.atualizarOrdemServicoRelatorio(colecaoIdsOrdemServico);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Serviço
	 * 
	 * @author Raphael Rossiter
	 * @date 26/10/2006
	 * 
	 * @param Collection
	 *            <ManterDadosAtividadesOrdemServicoHelper>
	 * @return void
	 * @throws ControladorException
	 */
	public void manterDadosAtividadesOrdemServico(Collection<ManterDadosAtividadesOrdemServicoHelper> colecaoDadosAtividades) throws ControladorException {

		if (colecaoDadosAtividades != null && !colecaoDadosAtividades.isEmpty()) {

			Iterator iteratorColecaoDadosAtividades = colecaoDadosAtividades.iterator();
			ManterDadosAtividadesOrdemServicoHelper manterDadosAtividadesOrdemServicoHelper = null;

			FiltroOrdemServicoAtividade filtroOrdemServicoAtividade = null;
			Collection colecaoOrdemServicoAtividade = null;

			OrdemServicoAtividade ordemServicoAtividade = null;
			Integer idOrdemServicoAtividade = null;

			FiltroOsAtividadeMaterialExecucao filtroOsAtividadeMaterialExecucao = null;
			Collection colecaoOsAtividadeMaterialExecucaoPesquisa = null;
			Collection colecaoOsAtividadeMaterialExecucao = null;
			OsAtividadeMaterialExecucao osAtividadeMaterialExecucao = null;

			FiltroOsAtividadePeriodoExecucao filtroOsAtividadePeriodoExecucao = null;
			Collection colecaoOSAtividadePeriodoExecucao = null;
			Collection colecaoOSAtividadePeriodoExecucaoHelper = null;
			OSAtividadePeriodoExecucaoHelper osAtividadePeriodoExecucaoHelper = null;
			OsAtividadePeriodoExecucao osAtividadePeriodoExecucao = null;
			Integer idOsAtividadePeriodoExecucao = null;

			FiltroOsExecucaoEquipe filtroOsExecucaoEquipe = null;
			Collection colecaoOsExecucaoEquipe = null;
			Collection colecaoOsExecucaoEquipeHelper = null;
			OSExecucaoEquipeHelper osExecucaoEquipeHelper = null;
			OsExecucaoEquipe osExecucaoEquipe = null;
			OsExecucaoEquipePK osExecucaoEquipePK = null;

			Collection colecaoOsExecucaoEquipeComponentes = null;
			OsExecucaoEquipeComponentes osExecucaoEquipeComponentes = null;

			// Atividade
			while (iteratorColecaoDadosAtividades.hasNext()) {

				manterDadosAtividadesOrdemServicoHelper = (ManterDadosAtividadesOrdemServicoHelper) iteratorColecaoDadosAtividades.next();

				filtroOrdemServicoAtividade = new FiltroOrdemServicoAtividade();

				filtroOrdemServicoAtividade.adicionarParametro(new ParametroSimples(FiltroOrdemServicoAtividade.ID_ORDEM_SERVICO,
						manterDadosAtividadesOrdemServicoHelper.getOrdemServicoAtividade().getOrdemServico().getId()));

				filtroOrdemServicoAtividade.adicionarParametro(new ParametroSimples(FiltroOrdemServicoAtividade.ID_ATIVIDADE,
						manterDadosAtividadesOrdemServicoHelper.getOrdemServicoAtividade().getAtividade().getId()));

				colecaoOrdemServicoAtividade = this.getControladorUtil().pesquisar(filtroOrdemServicoAtividade, OrdemServicoAtividade.class.getName());

				if (colecaoOrdemServicoAtividade != null && !colecaoOrdemServicoAtividade.isEmpty()) {

					ordemServicoAtividade = (OrdemServicoAtividade) Util.retonarObjetoDeColecao(colecaoOrdemServicoAtividade);
				} else {

					ordemServicoAtividade = manterDadosAtividadesOrdemServicoHelper.getOrdemServicoAtividade();
					ordemServicoAtividade.setUltimaAlteracao(new Date());

					// Inserindo OrdemServicoAtividade
					idOrdemServicoAtividade = (Integer) this.getControladorUtil().inserir(ordemServicoAtividade);
					ordemServicoAtividade.setId(idOrdemServicoAtividade);
				}

				// Apropriação de Material
				// ==============================================================================================
				colecaoOsAtividadeMaterialExecucao = manterDadosAtividadesOrdemServicoHelper.getColecaoOsAtividadeMaterialExecucao();

				if (colecaoOsAtividadeMaterialExecucao != null && !colecaoOsAtividadeMaterialExecucao.isEmpty()) {

					Iterator iteratorcolecaoOsAtividadeMaterialExecucao = colecaoOsAtividadeMaterialExecucao.iterator();

					while (iteratorcolecaoOsAtividadeMaterialExecucao.hasNext()) {

						osAtividadeMaterialExecucao = (OsAtividadeMaterialExecucao) iteratorcolecaoOsAtividadeMaterialExecucao.next();

						filtroOsAtividadeMaterialExecucao = new FiltroOsAtividadeMaterialExecucao();

						filtroOsAtividadeMaterialExecucao.adicionarParametro(new ParametroSimples(FiltroOsAtividadeMaterialExecucao.ID_ORDEM_SERVICO_ATIVIDADE,
								ordemServicoAtividade.getId()));

						filtroOsAtividadeMaterialExecucao.adicionarParametro(new ParametroSimples(FiltroOsAtividadeMaterialExecucao.ID_MATERIAL,
								osAtividadeMaterialExecucao.getMaterial().getId()));

						colecaoOsAtividadeMaterialExecucaoPesquisa = this.getControladorUtil().pesquisar(filtroOsAtividadeMaterialExecucao,
								OsAtividadeMaterialExecucao.class.getName());

						if (colecaoOsAtividadeMaterialExecucaoPesquisa != null && !colecaoOsAtividadeMaterialExecucaoPesquisa.isEmpty()) {

							osAtividadeMaterialExecucao = (OsAtividadeMaterialExecucao) Util.retonarObjetoDeColecao(colecaoOsAtividadeMaterialExecucaoPesquisa);
						} else {

							osAtividadeMaterialExecucao.setOrdemServicoAtividade(ordemServicoAtividade);
							osAtividadeMaterialExecucao.setUltimaAlteracao(new Date());

							this.getControladorUtil().inserir(osAtividadeMaterialExecucao);
						}
					}
				}
				// ==============================================================================================

				// Apropriação de Horas
				colecaoOSAtividadePeriodoExecucaoHelper = manterDadosAtividadesOrdemServicoHelper.getColecaoOSAtividadePeriodoExecucaoHelper();

				if (colecaoOSAtividadePeriodoExecucaoHelper != null && !colecaoOSAtividadePeriodoExecucaoHelper.isEmpty()) {

					Iterator iteratorColecaoOSAtividadePeriodoExecucaoHelper = colecaoOSAtividadePeriodoExecucaoHelper.iterator();

					while (iteratorColecaoOSAtividadePeriodoExecucaoHelper.hasNext()) {

						osAtividadePeriodoExecucaoHelper = (OSAtividadePeriodoExecucaoHelper) iteratorColecaoOSAtividadePeriodoExecucaoHelper.next();

						filtroOsAtividadePeriodoExecucao = new FiltroOsAtividadePeriodoExecucao();

						filtroOsAtividadePeriodoExecucao.adicionarParametro(new ParametroSimples(FiltroOsAtividadePeriodoExecucao.ID_ORDEM_SERVICO_ATIVIDADE,
								ordemServicoAtividade.getId()));

						filtroOsAtividadePeriodoExecucao.adicionarParametro(new ParametroSimples(FiltroOsAtividadePeriodoExecucao.DATA_INICIO,
								osAtividadePeriodoExecucaoHelper.getOsAtividadePeriodoExecucao().getDataInicio()));

						colecaoOSAtividadePeriodoExecucao = this.getControladorUtil().pesquisar(filtroOsAtividadePeriodoExecucao,
								OsAtividadePeriodoExecucao.class.getName());

						if (colecaoOSAtividadePeriodoExecucao != null && !colecaoOSAtividadePeriodoExecucao.isEmpty()) {

							osAtividadePeriodoExecucao = (OsAtividadePeriodoExecucao) Util.retonarObjetoDeColecao(colecaoOSAtividadePeriodoExecucao);
						} else {

							osAtividadePeriodoExecucao = osAtividadePeriodoExecucaoHelper.getOsAtividadePeriodoExecucao();

							osAtividadePeriodoExecucao.setOrdemServicoAtividade(ordemServicoAtividade);
							osAtividadePeriodoExecucao.setUltimaAlteracao(new Date());

							idOsAtividadePeriodoExecucao = (Integer) this.getControladorUtil().inserir(osAtividadePeriodoExecucao);
							osAtividadePeriodoExecucao.setId(idOsAtividadePeriodoExecucao);
						}

						// Equipe
						colecaoOsExecucaoEquipeHelper = osAtividadePeriodoExecucaoHelper.getColecaoOSExecucaoEquipeHelper();

						Iterator iteratorColecaoOsExecucaoEquipeHelper = colecaoOsExecucaoEquipeHelper.iterator();

						while (iteratorColecaoOsExecucaoEquipeHelper.hasNext()) {

							osExecucaoEquipeHelper = (OSExecucaoEquipeHelper) iteratorColecaoOsExecucaoEquipeHelper.next();

							filtroOsExecucaoEquipe = new FiltroOsExecucaoEquipe();

							filtroOsExecucaoEquipe.adicionarParametro(new ParametroSimples(FiltroOsExecucaoEquipe.ID_OS_ATIVIDADE_PERIODO_EXECUCAO,
									osAtividadePeriodoExecucao.getId()));

							filtroOsExecucaoEquipe.adicionarParametro(new ParametroSimples(FiltroOsExecucaoEquipe.ID_EQUIPE, osExecucaoEquipeHelper
									.getOsExecucaoEquipe().getEquipe().getId()));

							colecaoOsExecucaoEquipe = this.getControladorUtil().pesquisar(filtroOsExecucaoEquipe, OsExecucaoEquipe.class.getName());

							if (colecaoOsExecucaoEquipe != null && !colecaoOsExecucaoEquipe.isEmpty()) {

								osExecucaoEquipe = (OsExecucaoEquipe) Util.retonarObjetoDeColecao(colecaoOsExecucaoEquipe);
							} else {

								osExecucaoEquipe = osExecucaoEquipeHelper.getOsExecucaoEquipe();

								osExecucaoEquipePK = new OsExecucaoEquipePK();
								osExecucaoEquipePK.setIdEquipe(osExecucaoEquipe.getEquipe().getId());
								osExecucaoEquipePK.setIdOsAtividadePeriodoExecucao(osAtividadePeriodoExecucao.getId());

								osExecucaoEquipe.setComp_id(osExecucaoEquipePK);
								osExecucaoEquipe.setOsAtividadePeriodoExecucao(osAtividadePeriodoExecucao);

								this.getControladorUtil().inserir(osExecucaoEquipe);
							}

							// Componente
							colecaoOsExecucaoEquipeComponentes = osExecucaoEquipeHelper.getColecaoOsExecucaoEquipeComponentes();

							if (colecaoOsExecucaoEquipeComponentes != null && !colecaoOsExecucaoEquipeComponentes.isEmpty()) {

								Iterator iteratorColecaoOsExecucaoEquipeComponentes = colecaoOsExecucaoEquipeComponentes.iterator();

								while (iteratorColecaoOsExecucaoEquipeComponentes.hasNext()) {

									osExecucaoEquipeComponentes = (OsExecucaoEquipeComponentes) iteratorColecaoOsExecucaoEquipeComponentes.next();

									osExecucaoEquipeComponentes.setOsExecucaoEquipe(osExecucaoEquipe);
									osExecucaoEquipeComponentes.setUltimaAlteracao(new Date());

									this.getControladorUtil().inserir(osExecucaoEquipeComponentes);
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * [UC0461] - Manter Dados das Atividades da Ordem de Serviço
	 * 
	 * Pesquisa os dados da OrdemServicoAtividade
	 * 
	 * @author Raphael Rossiter
	 * @date 01/11/2006
	 * 
	 * @param idOrdemServico
	 *            , idAtividade
	 * @throws ErroRepositorioException
	 */
	public OrdemServicoAtividade pesquisarOrdemServicoAtividade(Integer numeroOS, Integer idAtividade) throws ControladorException {

		Object[] arrayOrdemServicoAtividade = null;
		OrdemServicoAtividade retorno = null;

		try {

			arrayOrdemServicoAtividade = repositorioOrdemServico.pesquisarOrdemServicoAtividade(numeroOS, idAtividade);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (arrayOrdemServicoAtividade != null) {

			OrdemServico ordemServico = new OrdemServico();
			ordemServico.setId(numeroOS);

			Atividade atividade = new Atividade();
			atividade.setId(idAtividade);

			retorno = new OrdemServicoAtividade();

			retorno.setId((Integer) arrayOrdemServicoAtividade[0]);
			retorno.setOrdemServico(ordemServico);
			retorno.setAtividade(atividade);
			retorno.setUltimaAlteracao((Date) arrayOrdemServicoAtividade[1]);

		}

		return retorno;
	}

	/**
	 * [UC0461] - Manter Dados das Atividades da Ordem de Serviço
	 * 
	 * Pesquisa os dados da OsAtividadeMaterialExecucao associada à
	 * OrdemServicoAtividade para a data informada
	 * 
	 * @author Raphael Rossiter
	 * @date 01/11/2006
	 * 
	 * @param idOrdemServicoAtividade
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOsAtividadeMaterialExecucao(Integer idOrdemServicoAtividade) throws ControladorException {

		Collection colecaoOsAtividadeMaterialExecucao = null;
		Collection<OsAtividadeMaterialExecucao> retorno = new ArrayList();

		try {

			colecaoOsAtividadeMaterialExecucao = repositorioOrdemServico.pesquisarOsAtividadeMaterialExecucao(idOrdemServicoAtividade);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoOsAtividadeMaterialExecucao != null && !colecaoOsAtividadeMaterialExecucao.isEmpty()) {

			Iterator osAtividadeMaterialExecucaoIterator = colecaoOsAtividadeMaterialExecucao.iterator();
			Object[] arrayOsAtividadeMaterialExecucao = null;
			OsAtividadeMaterialExecucao osAtividadeMaterialExecucao = null;

			OrdemServicoAtividade ordemServicoAtividade = new OrdemServicoAtividade();
			ordemServicoAtividade.setId(idOrdemServicoAtividade);

			while (osAtividadeMaterialExecucaoIterator.hasNext()) {

				osAtividadeMaterialExecucao = new OsAtividadeMaterialExecucao();

				osAtividadeMaterialExecucao.setOrdemServicoAtividade(ordemServicoAtividade);

				arrayOsAtividadeMaterialExecucao = (Object[]) osAtividadeMaterialExecucaoIterator.next();

				osAtividadeMaterialExecucao.setId((Integer) arrayOsAtividadeMaterialExecucao[0]);

				if (arrayOsAtividadeMaterialExecucao[1] != null) {
					osAtividadeMaterialExecucao.setQuantidadeMaterial((BigDecimal) arrayOsAtividadeMaterialExecucao[1]);
				}

				osAtividadeMaterialExecucao.setMaterial((Material) arrayOsAtividadeMaterialExecucao[2]);
				osAtividadeMaterialExecucao.setUltimaAlteracao((Date) arrayOsAtividadeMaterialExecucao[3]);

				retorno.add(osAtividadeMaterialExecucao);
			}
		}

		return retorno;
	}

	/**
	 * Retorna o resultado da pesquisa para a pesquisa
	 * 
	 * [UC0492] - Gerar Relatório Acompanhamento de Execução de Ordem de Serviço
	 * 
	 * @author Rafael Corrêa
	 * @date 01/11/06
	 * 
	 * @param origemServico
	 * @param situacaoOS
	 * @param idsServicosTipos
	 * @param idUnidadeAtendimento
	 * @param idUnidadeAtual
	 * @param idUnidadeEncerramento
	 * @param periodoAtendimentoInicial
	 * @param periodoAtendimentoFinal
	 * @param periodoEncerramentoInicial
	 * @param periodoEncerramentoFinal
	 * @param idEquipeProgramacao
	 * @param idEquipeExecucao
	 * @param tipoOrdenacao
	 * @return Collection
	 */
	public Collection pesquisarOSGerarRelatorioAcompanhamentoExecucao(String origemServico, String situacaoOS, String[] idsServicosTipos,
			String idUnidadeAtendimento, String idUnidadeAtual, String idUnidadeEncerramento, Date periodoAtendimentoInicial, Date periodoAtendimentoFinal,
			Date periodoEncerramentoInicial, Date periodoEncerramentoFinal, String idEquipeProgramacao, String idEquipeExecucao, String tipoOrdenacao)
			throws ControladorException {

		Collection colecaoDadosOS = null;
		Collection<OSRelatorioAcompanhamentoExecucaoHelper> retorno = new ArrayList();

		try {

			colecaoDadosOS = repositorioOrdemServico.pesquisarOSGerarRelatorioAcompanhamentoExecucao(origemServico, situacaoOS, idsServicosTipos,
					idUnidadeAtendimento, idUnidadeAtual, idUnidadeEncerramento, periodoAtendimentoInicial, periodoAtendimentoFinal,
					periodoEncerramentoInicial, periodoEncerramentoFinal, idEquipeProgramacao, idEquipeExecucao, tipoOrdenacao);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoDadosOS != null && !colecaoDadosOS.isEmpty()) {

			Iterator colecaoDadosOSIterator = colecaoDadosOS.iterator();

			Object[] dadosOS = null;

			OSRelatorioAcompanhamentoExecucaoHelper osRelatorioAcompanhamentoExecucaoHelper = null;

			while (colecaoDadosOSIterator.hasNext()) {

				osRelatorioAcompanhamentoExecucaoHelper = new OSRelatorioAcompanhamentoExecucaoHelper();

				dadosOS = (Object[]) colecaoDadosOSIterator.next();

				// Id da OS
				if (dadosOS[0] != null) { // 0
					osRelatorioAcompanhamentoExecucaoHelper.setIdOrdemServico((Integer) dadosOS[0]);
				}

				// Situação da OS
				if (dadosOS[1] != null) { // 1
					if (dadosOS[1].equals(OrdemServico.SITUACAO_ENCERRADO)) {
						osRelatorioAcompanhamentoExecucaoHelper.setSituacaoOS("ENCERRADA");
					} else {
						osRelatorioAcompanhamentoExecucaoHelper.setSituacaoOS("PENDENTE");
					}
				}

				// Id do Tipo de Serviço
				if (dadosOS[2] != null) { // 2
					osRelatorioAcompanhamentoExecucaoHelper.setIdServicoTipo((Integer) dadosOS[2]);
				}

				// Desrição do Tipo de Serviço
				if (dadosOS[3] != null) { // 3
					osRelatorioAcompanhamentoExecucaoHelper.setDescricaoServicoTipo((String) dadosOS[3]);
				}

				// Id do RA
				if (dadosOS[4] != null) { // 4
					osRelatorioAcompanhamentoExecucaoHelper.setIdRegistroAtendimento((Integer) dadosOS[4]);
				}

				// Data da Solicitação
				if (dadosOS[5] != null) { // 5
					osRelatorioAcompanhamentoExecucaoHelper.setDataSolicitacao((Date) dadosOS[5]);
				}

				// Data de Encerramento
				if (dadosOS[6] != null) { // 6
					osRelatorioAcompanhamentoExecucaoHelper.setDataEncerramento((Date) dadosOS[6]);
				}

				// Data da Programação
				if (dadosOS[7] != null) { // 7
					if (dadosOS[12] != null && dadosOS[13] != null) {
						Short indicadorAtivo = (Short) dadosOS[12];
						Short indicadorEquipePrincipal = (Short) dadosOS[13];
						if (indicadorAtivo.equals(OrdemServicoProgramacao.INDICADOR_ATIVO)
								&& indicadorEquipePrincipal.equals(OrdemServicoProgramacao.EQUIPE_PRINCIPAL)) {
							osRelatorioAcompanhamentoExecucaoHelper.setDataProgramacao((Date) dadosOS[7]);
						}
					}
				}

				// Id da Unidade de Atendimento(Origem)
				if (dadosOS[8] != null) { // 8
					osRelatorioAcompanhamentoExecucaoHelper.setIdUnidadeAtendimento((Integer) dadosOS[8]);
				}

				// Nome da Unidade de Atendimento(Origem)
				if (dadosOS[9] != null) { // 9
					osRelatorioAcompanhamentoExecucaoHelper.setNomeUnidadeAtendimento((String) dadosOS[9]);
				}

				// Data de Encerramento do RA
				if (dadosOS[10] != null) { // 10
					osRelatorioAcompanhamentoExecucaoHelper.setDataEncerramentoRA((Date) dadosOS[10]);
				}

				// Dias de Prazo
				if (dadosOS[11] != null) { // 11
					osRelatorioAcompanhamentoExecucaoHelper.setDiasPrazo((Integer) dadosOS[11]);
				}

				// Endereço
				String endereco = this.obterEnderecoAbreviadoOS(osRelatorioAcompanhamentoExecucaoHelper.getIdOrdemServico());

				osRelatorioAcompanhamentoExecucaoHelper.setEndereco(endereco);

				// Equipe
				Equipe equipePrincipal = null;

				if (osRelatorioAcompanhamentoExecucaoHelper.getSituacaoOS().equalsIgnoreCase("ENCERRADA")) {

					try {

						equipePrincipal = this.repositorioOrdemServico.pesquisarEquipePrincipalOSExecucaoEquipe(osRelatorioAcompanhamentoExecucaoHelper
								.getIdOrdemServico());

					} catch (ErroRepositorioException e) {
						sessionContext.setRollbackOnly();
						throw new ControladorException("erro.sistema", e);
					}

				} else {

					try {

						equipePrincipal = this.repositorioOrdemServico.pesquisarEquipePrincipalOSProgramacao(osRelatorioAcompanhamentoExecucaoHelper
								.getIdOrdemServico());

					} catch (ErroRepositorioException e) {
						sessionContext.setRollbackOnly();
						throw new ControladorException("erro.sistema", e);
					}

				}

				if (equipePrincipal != null) {

					osRelatorioAcompanhamentoExecucaoHelper.setNomeEquipe(equipePrincipal.getNome());

				}

				if (osRelatorioAcompanhamentoExecucaoHelper.getIdRegistroAtendimento() != null) {

					// Unidade Atual
					UnidadeOrganizacional unidadeAtual = getControladorRegistroAtendimento().obterUnidadeAtualRA(
							osRelatorioAcompanhamentoExecucaoHelper.getIdRegistroAtendimento());

					osRelatorioAcompanhamentoExecucaoHelper.setNomeUnidadeAtual(unidadeAtual.getDescricao());
				}

				retorno.add(osRelatorioAcompanhamentoExecucaoHelper);
			}
		}

		if (tipoOrdenacao != null && tipoOrdenacao.equals("2")) {

			// Organizar a coleção

			Collections.sort((List) retorno, new Comparator() {
				public int compare(Object a, Object b) {
					Integer tipoServico1 = ((OSRelatorioAcompanhamentoExecucaoHelper) a).getIdServicoTipo();
					Integer tipoServico2 = ((OSRelatorioAcompanhamentoExecucaoHelper) b).getIdServicoTipo();

					if (!tipoServico1.equals(tipoServico2)) {
						return tipoServico1.compareTo(tipoServico2);
					} else {

						String endereco1 = ((OSRelatorioAcompanhamentoExecucaoHelper) a).getEndereco();
						String endereco2 = ((OSRelatorioAcompanhamentoExecucaoHelper) b).getEndereco();

						return endereco1.compareTo(endereco2);

					}
				}
			});

		}

		return retorno;
	}

	/**
	 * Retorna o resultado da pesquisa para a pesquisa
	 * 
	 * [UC0492] - Gerar Relatório Acompanhamento de Execução de Ordem de Serviço
	 * 
	 * @author Rafael Corrêa
	 * @date 01/11/06
	 * 
	 * @param origemServico
	 * @param situacaoOS
	 * @param idsServicosTipos
	 * @param idUnidadeAtendimento
	 * @param idUnidadeAtual
	 * @param idUnidadeEncerramento
	 * @param periodoAtendimentoInicial
	 * @param periodoAtendimentoFinal
	 * @param periodoEncerramentoInicial
	 * @param periodoEncerramentoFinal
	 * @param idEquipeProgramacao
	 * @param idEquipeExecucao
	 * @return int
	 */
	public int pesquisarOSGerarRelatorioAcompanhamentoExecucaoCount(String origemServico, String situacaoOS, String[] idsServicosTipos,
			String idUnidadeAtendimento, String idUnidadeAtual, String idUnidadeEncerramento, Date periodoAtendimentoInicial, Date periodoAtendimentoFinal,
			Date periodoEncerramentoInicial, Date periodoEncerramentoFinal, String idEquipeProgramacao, String idEquipeExecucao) throws ControladorException {

		int retorno = 0;

		try {

			retorno = repositorioOrdemServico.pesquisarOSGerarRelatorioAcompanhamentoExecucaoCount(origemServico, situacaoOS, idsServicosTipos,
					idUnidadeAtendimento, idUnidadeAtual, idUnidadeEncerramento, periodoAtendimentoInicial, periodoAtendimentoFinal,
					periodoEncerramentoInicial, periodoEncerramentoFinal, idEquipeProgramacao, idEquipeExecucao);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;

	}

	/**
	 * Pesquisa as equipes de acordo com os parâmetros informado pelo usuário
	 * 
	 * [UC0370] - Filtrar Equipe
	 * 
	 * @author Rafael Corrêa
	 * @date 09/11/06
	 * 
	 * @param idEquipe
	 * @param nome
	 * @param placa
	 * @param cargaTrabalho
	 * @param idUnidade
	 * @param idFuncionario
	 * @param idPerfilServico
	 * @param indicadorUso
	 * @param numeroPagina
	 * @return Collection
	 */
	public Collection pesquisarEquipes(String idEquipe, String nome, String placa, String cargaTrabalho, String codigoDdd, String numeroTelefone,
			String numeroImei, String idUnidade, String idFuncionario, String idPerfilServico, String indicadorUso, String tipoPesquisa, Integer numeroPagina,
			String equipamentosEspeciasId, String cdUsuarioRespExecServico, String indicadorProgramacaoAutomatica) throws ControladorException {

		Collection retorno = null;

		try {

			retorno = repositorioOrdemServico.pesquisarEquipes(idEquipe, nome, placa, cargaTrabalho, codigoDdd, numeroTelefone, numeroImei, idUnidade,
					idFuncionario, idPerfilServico, indicadorUso, tipoPesquisa, numeroPagina, equipamentosEspeciasId, cdUsuarioRespExecServico,
					indicadorProgramacaoAutomatica);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;

	}

	/**
	 * Verifica a quantidade de registros retornados da pesquisa de equipe
	 * 
	 * [UC0370] - Filtrar Equipe
	 * 
	 * @author Rafael Corrêa
	 * @date 09/11/06
	 * 
	 * @param idEquipe
	 * @param nome
	 * @param placa
	 * @param cargaTrabalho
	 * @param idUnidade
	 * @param idFuncionario
	 * @param idPerfilServico
	 * @param indicadorUso
	 * @return int
	 */
	public int pesquisarEquipesCount(String idEquipe, String nome, String placa, String cargaTrabalho, String codigoDdd, String numeroTelefone,
			String numeroImei, String idUnidade, String idFuncionario, String idPerfilServico, String indicadorUso, String tipoPesquisa,
			String equipamentosEspeciasId, String cdUsuarioRespExecServico, String indicadorProgramacaoAutomatica) throws ControladorException {

		int retorno = 0;

		try {

			retorno = repositorioOrdemServico.pesquisarEquipesCount(idEquipe, nome, placa, cargaTrabalho, codigoDdd, numeroTelefone, numeroImei, idUnidade,
					idFuncionario, idPerfilServico, indicadorUso, tipoPesquisa, equipamentosEspeciasId, cdUsuarioRespExecServico,
					indicadorProgramacaoAutomatica);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;

	}

	/**
	 * Remove as equipes selecionadas pelo usuário e as equipes componentes
	 * associadas a ela
	 * 
	 * [UC0372] - Manter Equipe
	 * 
	 * @author Rafael Corrêa
	 * @date 09/11/06
	 * 
	 * @param idsEquipes
	 * @throws ControladorException
	 */
	public void removerEquipes(String[] idsEquipes, Usuario usuarioLogado) throws ControladorException {

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(Operacao.OPERACAO_EQUIPE_REMOVER, new UsuarioAcaoUsuarioHelper(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_EQUIPE_REMOVER);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		if (idsEquipes != null && idsEquipes.length > 0) {
			for (int i = 0; i < idsEquipes.length; i++) {
				String idEquipe = idsEquipes[i];

				// Cria o filtro de equipe para verificar se a equipe ja foi
				// removida
				FiltroEquipe filtroEquipe = new FiltroEquipe();

				filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.ID, idEquipe));

				Collection colecaoEquipe = getControladorUtil().pesquisar(filtroEquipe, Equipe.class.getSimpleName());

				if (colecaoEquipe != null && !colecaoEquipe.isEmpty()) {

					Equipe equipe = (Equipe) Util.retonarObjetoDeColecao(colecaoEquipe);

					FiltroEquipeComponentes filtroEquipeComponentes = new FiltroEquipeComponentes();

					filtroEquipeComponentes.adicionarParametro(new ParametroSimples(FiltroEquipeComponentes.ID_EQUIPE, idEquipe));

					Collection colecaoEquipeComponentes = getControladorUtil().pesquisar(filtroEquipeComponentes, EquipeComponentes.class.getSimpleName());

					if (colecaoEquipeComponentes != null && !colecaoEquipeComponentes.isEmpty()) {
						Iterator colecaoEquipeComponentesIterator = colecaoEquipeComponentes.iterator();

						while (colecaoEquipeComponentesIterator.hasNext()) {

							EquipeComponentes equipeComponentes = (EquipeComponentes) colecaoEquipeComponentesIterator.next();

							// ------------ REGISTRAR TRANSAÇÃO ----------------
							equipeComponentes.setOperacaoEfetuada(operacaoEfetuada);
							equipeComponentes.adicionarUsuario(usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
							registradorOperacao.registrarOperacao(equipeComponentes);
							// ------------ REGISTRAR TRANSAÇÃO ----------------

							getControladorUtil().remover(equipeComponentes);

						}

					}

					// ------------ REGISTRAR TRANSAÇÃO ----------------
					equipe.setOperacaoEfetuada(operacaoEfetuada);
					equipe.adicionarUsuario(usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
					registradorOperacao.registrarOperacao(equipe);
					// ------------ REGISTRAR TRANSAÇÃO ----------------

					getControladorUtil().remover(equipe);

				} else {
					sessionContext.setRollbackOnly();
					throw new ControladorException("atencao.registro_remocao_nao_existente");
				}

			}
		}
	}

	/**
	 * Valida a ordem de serviço
	 * 
	 * [UC0488] - Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Sávio Luiz
	 * @date 01/11/06
	 * 
	 * @return Integer
	 */
	public void validarOrdemServico(Integer idOrdemServico) throws ControladorException {

		Object[] parmsOS = null;

		try {

			parmsOS = repositorioOrdemServico.pesquisarServicoTipoComFiscalizacaoInfracao(idOrdemServico);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		if (parmsOS != null) {
			String descricaoOS = null;
			Short situacaoOS = null;
			Integer idCobrancaDocumento = null;
			if (parmsOS[0] != null) {
				descricaoOS = (String) parmsOS[0];
			}
			if (parmsOS[1] != null) {
				situacaoOS = (Short) parmsOS[1];
			}
			if (parmsOS[2] != null) {
				idCobrancaDocumento = (Integer) parmsOS[2];
			}

			if (situacaoOS != null && !situacaoOS.equals(OrdemServico.SITUACAO_PENDENTE)) {
				throw new ControladorException("atencao.os_encerrada", null, descricaoOS);
			}

			if (idCobrancaDocumento == null || idCobrancaDocumento.equals("")) {
				throw new ControladorException("atencao.os_nao_documento_cobranca");
			}

		} else {
			throw new ControladorException("atencao.os_sem_fiscalizacao_infracao");
		}

	}

	/**
	 * [UC0367] Informar Retorno Ordem de Fiscalização
	 * 
	 * Recupera os parámetros necessários da OS
	 * 
	 * @author Sávio Luiz
	 * @date 24/08/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Object[] pesquisarParmsOS(Integer idOrdemServico) throws ControladorException {

		Object[] parmsOS = null;

		try {

			parmsOS = repositorioOrdemServico.pesquisarParmsOS(idOrdemServico);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return parmsOS;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * Preparação dos dados para geração de RA
	 * 
	 * @author Raphael Rossiter
	 * @date 30/01/2007
	 * 
	 * @param idImovel
	 *            , idSolicitacaoTipoEspecificacao, idSolicitacaoTipo,
	 *            idServicoTipo, idUsuarioLogado
	 * @return Integer[]
	 * @throws ControladorException
	 */
	public Integer[] informarRetornoOSFiscalizacaoGerarRA(Integer idImovel, Integer idSolicitacaoTipoEspecificacao, Integer idSolicitacaoTipo,
			Integer idServicoTipo, Usuario usuarioLogado) throws ControladorException {

		Date dataAtual = new Date();

		DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper dataPrevistaUnidadeDestino = getControladorRegistroAtendimento()
				.definirDataPrevistaUnidadeDestinoEspecificacao(dataAtual, idSolicitacaoTipoEspecificacao);

		String dataPrevista = null;
		if (dataPrevistaUnidadeDestino.getDataPrevista() != null) {

			dataPrevista = Util.formatarData(dataPrevistaUnidadeDestino.getDataPrevista());
		}

		String parecerUnidadeDestino = null;
		Integer idUnidadeDestino = null;
		if (dataPrevistaUnidadeDestino.getUnidadeOrganizacional() != null) {

			idUnidadeDestino = dataPrevistaUnidadeDestino.getUnidadeOrganizacional().getId();
			parecerUnidadeDestino = "RA GERADA A PARTIR DA FISCALIZAÇÃO - (AUTO-INFRAÇÃO)";
		}

		Collection colecaoEndereco = new ArrayList();
		Imovel imovelEndereco = getControladorEndereco().pesquisarImovelParaEndereco(idImovel);
		colecaoEndereco.add(imovelEndereco);

		Imovel imovelCarregado = getControladorImovel().pesquisarImovelDigitado(idImovel);

		Cliente clienteUsuario = getControladorImovel().pesquisarClienteUsuarioImovel(idImovel);

		RADadosGeraisHelper raDadosGerais = RABuilder.buildRADadosGerais(RegistroAtendimento.INDICADOR_ATENDIMENTO_ON_LINE, dataAtual, MeioSolicitacao.INTERNO,
				idSolicitacaoTipoEspecificacao, dataPrevista, idSolicitacaoTipo, usuarioLogado);
		RALocalOcorrenciaHelper raLocalOcorrencia = RABuilder.buildRALocalOcorrencia(imovelCarregado, colecaoEndereco, idUnidadeDestino, parecerUnidadeDestino,
				ConstantesSistema.NAO);
		RASolicitanteHelper raSolicitante = RABuilder.buildRASolicitante(false, clienteUsuario.getId());

		Integer[] idsGerados = getControladorRegistroAtendimento().inserirRegistroAtendimento(raDadosGerais, raLocalOcorrencia, raSolicitante);

		return idsGerados;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer[] informarRetornoOSFiscalizacao(Integer idOrdemServico, String indicadorDocumentoEntregue, Integer idLigacaoAguaSituacaoImovel,
			Integer idLigacaoEsgotoSituacaoImovel, Integer idImovel, String indicadorMedicaoTipo, String indicadorGeracaoDebito, Integer idCobrancaDocumento,
			Usuario usuarioLogado, DadosAutoInfracaoRetornoOrdemFiscalizacaoHelper dadosAutoInfracao, Short confirmaAtualizacaoSituacaoLigacaoAgua,
			Short confirmaAtualizacaoSituacaoLigacaoEsgoto, Collection colecaoFiscalizacaoSituacaoSelecionada, LigacaoEsgoto ligacaoEsgoto,
			String indicadorLigacaoEsgoto) throws ControladorException {

		Integer[] retorno = new Integer[5];
		retorno[0] = idOrdemServico;

		// caso não tenha entrado em alguma condição que finalize o caso de uso
		// então continua o fluxo
		boolean finalizador = false;
		Integer consumoMedioMedido = 0;
		Integer maiorConsumoMedido = 0;
		Integer consumoMedioNaoMedido = 0;
		SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

		try {

			OrdemServico ordemServico = repositorioOrdemServico.recuperaOrdemServico(idOrdemServico);
			Short indicadorAtualizacaoSituacaoLigacaoAgua = ordemServico.getIndicadorAtualizaAgua();
			Short indicadorAtualizacaoSituacaoLigacaoEsgoto = ordemServico.getIndicadorAtualizaEsgoto();

			if (indicadorAtualizacaoSituacaoLigacaoAgua == null) {
				indicadorAtualizacaoSituacaoLigacaoAgua = ConstantesSistema.NAO;
			}
			if (indicadorAtualizacaoSituacaoLigacaoEsgoto == null) {
				indicadorAtualizacaoSituacaoLigacaoEsgoto = ConstantesSistema.NAO;
			}

			// pesquisa imovel para Atualizar indicador para reincidência de
			// infração
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
			Collection imoveis = this.getControladorUtil().pesquisar(filtroImovel, Imovel.class.getName());
			Imovel imovelPesquisa = (Imovel) Util.retonarObjetoDeColecao(imoveis);

			Object[] fiscalizacaoSituacaoAgua = null;

			Object[] fiscalizacaoSituacaoEsgoto = null;

			FiscalizacaoSituacao fiscalizacaoSituacao = null;

			Iterator iteratorFiscalizacaoSituacaoSelecionada = colecaoFiscalizacaoSituacaoSelecionada.iterator();
			while (iteratorFiscalizacaoSituacaoSelecionada.hasNext()) {
				SituacaoEncontradaHelper helper = (SituacaoEncontradaHelper) iteratorFiscalizacaoSituacaoSelecionada.next();
				fiscalizacaoSituacao = helper.getFiscalizacaoSituacao();

				if (confirmaAtualizacaoSituacaoLigacaoAgua != null) {
					if (confirmaAtualizacaoSituacaoLigacaoAgua.equals(ConstantesSistema.SIM)) {
						// 5.1. verificar se é necessário validar a situação da
						// ligação de água do imóvel fiscalizado.
						if (fiscalizacaoSituacao.getIndicadorAguaSituacao() == FiscalizacaoSituacao.INDICADOR_SIM) {
							// 5.1.1. caso não exista situação de ligação de
							// agua do imóvel

							// [0] = idLigacaoAguaSituacao
							// [1] = ligacaoAguaSituacaoByLastIdnova.id
							// [2] = solicitacaoTipoEspecificacao.id
							// [3] = solicitacaoTipo.id
							// [4] = servicoTipo.id

							fiscalizacaoSituacaoAgua = repositorioOrdemServico.pesquisarIdFiscalizacaoSituacaoAgua(idLigacaoAguaSituacaoImovel,
									fiscalizacaoSituacao.getId());

							if (fiscalizacaoSituacaoAgua == null) {

								FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
								filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.ID, idLigacaoAguaSituacaoImovel));

								Collection colecaoLigacaoAguaSituacao = getControladorUtil().pesquisar(filtroLigacaoAguaSituacao,
										LigacaoAguaSituacao.class.getName());

								LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoAguaSituacao);

								sessionContext.setRollbackOnly();
								throw new ControladorException("atencao.situacao.agua.incompativel", null, ligacaoAguaSituacao.getDescricao(),
										fiscalizacaoSituacao.getDescricaoFiscalizacaoSituacao());

							} else {

								/*
								 * 5.1.2.2. O sistema deve verificar se é para
								 * gerar um RA para o imóvel associado ao
								 * documento de cobrança (STEP_ID da tabela
								 * FISCALIZACAO_SITUACAO_AGUA com valor
								 * diferente de nulo)
								 */
								if (fiscalizacaoSituacaoAgua[2] != null) {

									/*
									 * [UC0366] - Inserir Registro de
									 * Atendimento, passando o imóvel e a
									 * especificação informada.
									 */
									Integer[] idsRAAgua = this.informarRetornoOSFiscalizacaoGerarRA(idImovel, (Integer) fiscalizacaoSituacaoAgua[2],
											(Integer) fiscalizacaoSituacaoAgua[3], (Integer) fiscalizacaoSituacaoAgua[4], usuarioLogado);

									if (idsRAAgua.length > 1) {
										retorno[1] = idsRAAgua[0];
										retorno[3] = idsRAAgua[1];
									} else {
										retorno[1] = idsRAAgua[0];
									}
								}
								/*
								 * 5.1.2.1. O sistema deve verificar se é para
								 * atualizar a situação da ligação de água do
								 * imóvel [SB0002 - Atualizar Imovel/Ligação de
								 * Água]
								 */

								else if (fiscalizacaoSituacaoAgua[1] != null) {

									Short[] indicadores = atualizarImovelLigacaoAgua(fiscalizacaoSituacao.getId(), idImovel, 1, idLigacaoAguaSituacaoImovel,
											idLigacaoEsgotoSituacaoImovel, ConstantesSistema.SIM, ConstantesSistema.NAO);

									if (indicadores[0] != null) {
										indicadorAtualizacaoSituacaoLigacaoAgua = indicadores[0];
									}
								}
							}
						}
					} else {
						indicadorAtualizacaoSituacaoLigacaoAgua = ConstantesSistema.NAO;
					}
				}

				if (confirmaAtualizacaoSituacaoLigacaoEsgoto != null) {

					// [0] = idLigacaoEsgotoSituacao
					// [1] = ligacaoAguaSituacaoByLastIdnova.id
					// [2] = solicitacaoTipoEspecificacao.id
					// [3] = solicitacaoTipo.id
					// [4] = servicoTipo.id

					fiscalizacaoSituacaoEsgoto = repositorioOrdemServico.pesquisarIdFiscalizacaoSituacaoEsgoto(idLigacaoEsgotoSituacaoImovel,
							fiscalizacaoSituacao.getId());

					if (confirmaAtualizacaoSituacaoLigacaoEsgoto.equals(ConstantesSistema.SIM)) {
						if (!finalizador) {
							// 5.2. verificar se é necessário validar a situação
							// da ligação de esgoto do imóvel fiscalizado.
							if (fiscalizacaoSituacao.getIndicadorEsgotoSituacao() == FiscalizacaoSituacao.INDICADOR_SIM) {
								// 5.2.1. caso não exista situação de ligação de
								// agua do imóvel

								if (fiscalizacaoSituacaoEsgoto == null) {
									FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
									filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID,
											idLigacaoEsgotoSituacaoImovel));

									Collection colecaoLigacaoEsgotoSituacao = getControladorUtil().pesquisar(filtroLigacaoEsgotoSituacao,
											LigacaoEsgotoSituacao.class.getName());

									LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) Util
											.retonarObjetoDeColecao(colecaoLigacaoEsgotoSituacao);

									sessionContext.setRollbackOnly();
									throw new ControladorException("atencao.situacao.esgoto.incompativel", null, ligacaoEsgotoSituacao.getDescricao(),
											fiscalizacaoSituacao.getDescricaoFiscalizacaoSituacao());

								} else {

									/*
									 * 5.1.2.2. O sistema deve verificar se é
									 * para gerar um RA para o imóvel associado
									 * ao documento de cobrança (STEP_ID da
									 * tabela FISCALIZACAO_SITUACAO_AGUA com
									 * valor diferente de nulo)
									 */
									if (fiscalizacaoSituacaoEsgoto[2] != null) {

										/*
										 * [UC0366] - Inserir Registro de
										 * Atendimento, passando o imóvel e a
										 * especificação informada.
										 */
										Integer[] idsRAEsgoto = this.informarRetornoOSFiscalizacaoGerarRA(idImovel, (Integer) fiscalizacaoSituacaoEsgoto[2],
												(Integer) fiscalizacaoSituacaoEsgoto[3], (Integer) fiscalizacaoSituacaoEsgoto[4], usuarioLogado);

										if (idsRAEsgoto.length > 1) {
											retorno[2] = idsRAEsgoto[0];
											retorno[4] = idsRAEsgoto[1];
										} else {
											retorno[2] = idsRAEsgoto[0];
										}
									}
									/*
									 * 5.1.2.1. O sistema deve verificar se é
									 * para atualizar a situação da ligação de
									 * água do imóvel [SB0002 - Atualizar
									 * Imovel/Ligação de Água]
									 */
									else if (fiscalizacaoSituacaoEsgoto[1] != null) {

										Short[] indicadores = atualizarImovelLigacaoAgua(fiscalizacaoSituacao.getId(), idImovel, 1,
												idLigacaoAguaSituacaoImovel, idLigacaoEsgotoSituacaoImovel, ConstantesSistema.NAO, ConstantesSistema.SIM);

										if (indicadores[1] != null) {
											indicadorAtualizacaoSituacaoLigacaoEsgoto = indicadores[1];
										}
									}
								}
							}
						}
					} else {
						indicadorAtualizacaoSituacaoLigacaoEsgoto = ConstantesSistema.NAO;
					}
				}

				if (!finalizador) {
					// 5.3. verificar se é necessário verificar se o imóvel é
					// medido.
					if (fiscalizacaoSituacao.getIndicadorMedido() == FiscalizacaoSituacao.INDICADOR_SIM) {
						boolean existeHidrometro = getControladorAtendimentoPublico().verificarExistenciaHidrometroEmLigacaoAgua(idImovel);
						// 5.3.1. caso o imóvel não seja medido
						if (!existeHidrometro) {
							// [SB0001 - Atualizar Ordem de Serviço]
							atualizarOrdemServico(idOrdemServico, indicadorDocumentoEntregue, indicadorAtualizacaoSituacaoLigacaoAgua,
									indicadorAtualizacaoSituacaoLigacaoEsgoto, colecaoFiscalizacaoSituacaoSelecionada, idCobrancaDocumento);
							finalizador = true;
						}
					}
				}

				if (!finalizador) {
					// 5.4. verificar se é necessário validar a capacidade do
					// hidrometro.
					if (fiscalizacaoSituacao.getIndicadorHidrometroCapacidade() == FiscalizacaoSituacao.INDICADOR_SIM) {
						// 5.4.1. caso a opção de medição igual a
						// "Ligação de Água"
						if (indicadorMedicaoTipo != null && indicadorMedicaoTipo.equals("1")) {
							boolean existeHidrometro = getControladorAtendimentoPublico().verificarExistenciaHidrometroEmLigacaoAgua(idImovel);
							// 5.4.1.1. caso não exista hidrometro na ligação
							// água
							if (!existeHidrometro) {
								// [SB0001 - Atualizar Ordem de Serviço]
								atualizarOrdemServico(idOrdemServico, indicadorDocumentoEntregue, indicadorAtualizacaoSituacaoLigacaoAgua,
										indicadorAtualizacaoSituacaoLigacaoEsgoto, colecaoFiscalizacaoSituacaoSelecionada, idCobrancaDocumento);
								finalizador = true;
							} else {
								// 5.4.1.2. caso exista a capacidade do
								// hidrômetro
								// instalado na ligação de água
								Integer idHidrometroCapacidadeFiscalizacao = repositorioOrdemServico.pesquisarHidormetroCapacidadeFiscalizacaoAgua(idImovel);
								if (idHidrometroCapacidadeFiscalizacao == null || idHidrometroCapacidadeFiscalizacao.equals("")) {
									// [SB0001 - Atualizar Ordem de Serviço]
									atualizarOrdemServico(idOrdemServico, indicadorDocumentoEntregue, indicadorAtualizacaoSituacaoLigacaoAgua,
											indicadorAtualizacaoSituacaoLigacaoEsgoto, colecaoFiscalizacaoSituacaoSelecionada, idCobrancaDocumento);
									finalizador = true;
								}
							}
						} else {
							// 5.4.2. Caso a opção de medição igual a "Poço"
							if (indicadorMedicaoTipo != null && indicadorMedicaoTipo.equals("2")) {
								boolean existeHidrometro = getControladorAtendimentoPublico().verificarExistenciaHidrometroEmImovel(idImovel);
								// 5.4.2.1. caso não exista hidrometro no imóvel
								if (!existeHidrometro) {
									// [SB0001 - Atualizar Ordem de Serviço]
									atualizarOrdemServico(idOrdemServico, indicadorDocumentoEntregue, indicadorAtualizacaoSituacaoLigacaoAgua,
											indicadorAtualizacaoSituacaoLigacaoEsgoto, colecaoFiscalizacaoSituacaoSelecionada, idCobrancaDocumento);
									finalizador = true;
								} else {
									// 5.4.2.2. caso exista a capacidade do
									// hidrômetro
									// instalado na ligação de água
									Integer idHidrometroCapacidadeFiscalizacao = repositorioOrdemServico
											.pesquisarHidormetroCapacidadeFiscalizacaoPoco(idImovel);
									if (idHidrometroCapacidadeFiscalizacao == null || idHidrometroCapacidadeFiscalizacao.equals("")) {
										// [SB0001 - Atualizar Ordem de Serviço]
										atualizarOrdemServico(idOrdemServico, indicadorDocumentoEntregue, indicadorAtualizacaoSituacaoLigacaoAgua,
												indicadorAtualizacaoSituacaoLigacaoEsgoto, colecaoFiscalizacaoSituacaoSelecionada, idCobrancaDocumento);
										finalizador = true;
									}
								}
							}
						}
					}
				}

				Short indicadorDebito = repositorioOrdemServico.recuperaIndicadorDebitoDaOrdemServicoFiscSit(idOrdemServico, fiscalizacaoSituacao.getId());

				// 6. O sistema deverá gerar os valores de consumo do imóvel
				boolean existeHidrometro = getControladorAtendimentoPublico().verificarExistenciaHidrometroEmLigacaoAgua(idImovel);
				if (existeHidrometro) {
					// 6.1. Caso o imóvel tenha consumo medido

					// 6.1.1.1 Caso a opção de medição seja "Ligação Água"
					if (indicadorMedicaoTipo != null && indicadorMedicaoTipo.equals("1")) {
						consumoMedioMedido = getControladorMicromedicao().pesquisarConsumoMedio(idImovel, LigacaoTipo.LIGACAO_AGUA);
					} else {
						// 6.1.1.2 Caso contrário a opção de medição seja "Poço"
						if (indicadorMedicaoTipo != null && indicadorMedicaoTipo.equals("2")) {
							consumoMedioMedido = getControladorMicromedicao().pesquisarConsumoMedio(idImovel, LigacaoTipo.LIGACAO_ESGOTO);
						}
					}
					// 6.1.2 O sistema deverá obter o maior consumo medido do
					// imóvel até a quantidade de meses definida no sistema
					// parametros

					short qtdMeses = sistemaParametro.getMesesMediaConsumo();
					// 6.1.2.1 Caso a opção de medição seja "Ligação Água"
					if (indicadorMedicaoTipo != null && indicadorMedicaoTipo.equals("1")) {

						maiorConsumoMedido = getControladorMicromedicao().pesquisarMaiorConsumoFaturadoQuantidadeMeses(idImovel, LigacaoTipo.LIGACAO_AGUA,
								qtdMeses);
					} else {
						// 6.1.2.2 Caso contrário a opção de medição seja "Poço"
						if (indicadorMedicaoTipo != null && indicadorMedicaoTipo.equals("2")) {
							maiorConsumoMedido = getControladorMicromedicao().pesquisarMaiorConsumoFaturadoQuantidadeMeses(idImovel,
									LigacaoTipo.LIGACAO_ESGOTO, qtdMeses);
						}
					}
				}

				if (consumoMedioMedido == null) {
					consumoMedioMedido = 0;
				}

				if (maiorConsumoMedido == null) {
					maiorConsumoMedido = 0;
				}

				/*
				 * 6.2 = 6.1.3. O Sistema deverá obter o consumo médio como o
				 * não medido para o imóvel
				 */
				Imovel imovel = new Imovel();

				if (idImovel != null) {
					imovel.setId(idImovel);
				}

				Integer idConsumoTarifa = getControladorImovel().recuperarIdConsumoTarifa(idImovel);

				ConsumoTarifa consumoTarifa = new ConsumoTarifa();
				if (idConsumoTarifa != null) {
					consumoTarifa.setId(idConsumoTarifa);
				}

				imovel.setConsumoTarifa(consumoTarifa);

				// Colocado por Raphael Rossiter em 06/03/2007
				consumoMedioNaoMedido = getControladorImovel().obterConsumoMedioNaoMedidoImovel(imovel);

				boolean gerarDebitoLigadoClandestino = true;

				if ((fiscalizacaoSituacao.getId().equals(FiscalizacaoSituacao.LIGADO_CLANDESTINO_DE_AGUA) && fiscalizacaoSituacaoAgua == null)
						|| (fiscalizacaoSituacao.getId().equals(FiscalizacaoSituacao.LIGADO_CLANDESTINO_DE_AGUA_ESGOTO) && (fiscalizacaoSituacaoAgua == null && fiscalizacaoSituacaoEsgoto == null))
						|| (fiscalizacaoSituacao.getId().equals(FiscalizacaoSituacao.LIGADO_CLANDESTINO_DE_ESGOTO) && fiscalizacaoSituacaoEsgoto == null)) {

					gerarDebitoLigadoClandestino = false;

				}

				// 7. caso o usuário tenha selecionado "Sim" para geração de
				// débito
				if (indicadorGeracaoDebito != null && indicadorGeracaoDebito.equals("1") && helper.getGeracaoDebito() == 1
						&& (indicadorDebito == null || !indicadorDebito.equals(ConstantesSistema.SIM)) && gerarDebitoLigadoClandestino) {

					Collection colecaoCodicoConsumoCalculo = repositorioOrdemServico.pesquisarFiscalizacaoSituacaoServicoACobrar(fiscalizacaoSituacao.getId());

					// [SB0005] Atualizar Autos de Infração
					AutosInfracao autosInfracao = null;

					if (colecaoCodicoConsumoCalculo != null && !colecaoCodicoConsumoCalculo.isEmpty()) {

						Iterator iteCodigoConsumoCalculado = colecaoCodicoConsumoCalculo.iterator();

						while (iteCodigoConsumoCalculado.hasNext()) {

							Object[] fiscalizacaoSituacaoServicoACobrar = (Object[]) iteCodigoConsumoCalculado.next();

							Short codigoConsumoCalculado = (Short) fiscalizacaoSituacaoServicoACobrar[0];
							Integer idDebitoTipo = (Integer) fiscalizacaoSituacaoServicoACobrar[1];

							// 7.1.1. Caso o código do calculo de consumo=1
							if (codigoConsumoCalculado != null && codigoConsumoCalculado.equals(FiscalizacaoSituacaoServicoACobrar.CONSUMO_CALCULO_UM)) {

								BigDecimal valorDebito = new BigDecimal("0.00");

								// 7.1.1.1 Caso o imóvel tenha consumo medido maior que zero
								if (consumoMedioMedido > 0) {
									// [SB0004 - Calcular Valor de Água e/ou
									// Esgoto]
									valorDebito = calcularValorAguaEsgoto(consumoMedioMedido, sistemaParametro, idImovel, fiscalizacaoSituacao.getId(),
											idLigacaoAguaSituacaoImovel, idLigacaoEsgotoSituacaoImovel);
								} else {
									// 7.1.1.2 o imóvel tenha consumo medido
									// igual a zero passar o médio não medido
									if (consumoMedioMedido == 0) {
										// [SB0004 - Calcular Valor de Água e/ou
										// Esgoto]
										valorDebito = calcularValorAguaEsgoto(consumoMedioNaoMedido, sistemaParametro, idImovel, fiscalizacaoSituacao.getId(),
												idLigacaoAguaSituacaoImovel, idLigacaoEsgotoSituacaoImovel);
									}

								}
								// [SB003 - Calcular/Inserir Valor]
								// o sistema deverá inserir o débito a cobrar
								// para o imóvel
								Short gerouDebitoACobrar = calcularInserirValor(valorDebito, idImovel, fiscalizacaoSituacao.getId(),
										new Integer(idOrdemServico), idDebitoTipo, autosInfracao, usuarioLogado);
								helper.setIndicadorDebitoOrdemServicoFiscSit(gerouDebitoACobrar);
							}

							// 7.1.2. Caso o código do calculo de consumo=2
							if (codigoConsumoCalculado != null && codigoConsumoCalculado.equals(FiscalizacaoSituacaoServicoACobrar.CONSUMO_CALCULO_DOIS)) {

								BigDecimal valorDebito = new BigDecimal("0.00");

								// 7.1.2.1 Caso o imóvel tenha consumo medido
								// maior que consumo
								// médio não medido passar o valor do consumo
								// médio médido
								if (consumoMedioMedido >= consumoMedioNaoMedido) {
									// [SB0004 - Calcular Valor de Água e/ou
									// Esgoto]
									valorDebito = calcularValorAguaEsgoto(consumoMedioMedido, sistemaParametro, idImovel, fiscalizacaoSituacao.getId(),
											idLigacaoAguaSituacaoImovel, idLigacaoEsgotoSituacaoImovel);
								} else {
									// 7.1.2.2 o imóvel tenha consumo medido
									// menor que o consumo medio não medido
									// passar o médio não medido
									if (consumoMedioMedido < consumoMedioNaoMedido) {
										// [SB0004 - Calcular Valor de Água e/ou
										// Esgoto]
										valorDebito = calcularValorAguaEsgoto(consumoMedioNaoMedido, sistemaParametro, idImovel, fiscalizacaoSituacao.getId(),
												idLigacaoAguaSituacaoImovel, idLigacaoEsgotoSituacaoImovel);
									}
								}

								// [SB0003 - Calcular/Inserir Valor]
								// o sistema deverá inserir o débito a
								// cobrar para o imóvel
								Short gerouDebitoACobrar = calcularInserirValor(valorDebito, idImovel, fiscalizacaoSituacao.getId(),
										new Integer(idOrdemServico), idDebitoTipo, autosInfracao, usuarioLogado);
								helper.setIndicadorDebitoOrdemServicoFiscSit(gerouDebitoACobrar);
							}
							// 7.1.3. Caso o código do calculo de consumo=3
							if (codigoConsumoCalculado != null && codigoConsumoCalculado.equals(FiscalizacaoSituacaoServicoACobrar.CONSUMO_CALCULO_TRES)) {

								BigDecimal maiorValor = new BigDecimal("0.00");

								// 7.1.3.1 Caso o imóvel tenha consumo
								// medido não medido maior que o consumo médio
								// medido
								// passar o valor do consumo merio medido
								if (consumoMedioNaoMedido >= maiorConsumoMedido) {
									// [SB0004 - Calcular Valor de Água e/ou
									// Esgoto]
									maiorValor = calcularValorAguaEsgoto(consumoMedioNaoMedido, sistemaParametro, idImovel, fiscalizacaoSituacao.getId(),
											idLigacaoAguaSituacaoImovel, idLigacaoEsgotoSituacaoImovel);
								} else {
									// 7.1.3.2 o imóvel tenha o maior
									// consumo medido maior que o consumo medio
									// não medido
									if (maiorConsumoMedido > consumoMedioNaoMedido) {
										// [SB0004 - Calcular Valor de Água e/ou
										// Esgoto]
										maiorValor = calcularValorAguaEsgoto(maiorConsumoMedido, sistemaParametro, idImovel, fiscalizacaoSituacao.getId(),
												idLigacaoAguaSituacaoImovel, idLigacaoEsgotoSituacaoImovel);
									}
								}

								/*
								 * 7.1.3.4 o sistema deverá obter o numero de
								 * meses de instalação do hidrometro de acordo
								 * com a opção de medição
								 */
								Short numeroMesesCalculoConsumo = sistemaParametro.getMesesMediaConsumo();

								Date dataInstalacaoHidrometro = null;

								if (indicadorMedicaoTipo != null && indicadorMedicaoTipo.equals(MedicaoTipo.POCO)) {

									// 7.1.3.4.1 Caso a opção de ligação seja
									// poço
									dataInstalacaoHidrometro = getControladorMicromedicao().pesquisarDataInstalacaoHidrometroPoco(idImovel);

									if (dataInstalacaoHidrometro == null) {

										sessionContext.setRollbackOnly();
										throw new ControladorException("atencao.imovel_poco_sem_hidrometro", null, idImovel.toString());
									}

								} else {

									// 7.1.3.4.1 Caso a opção de ligação seja de
									// água
									dataInstalacaoHidrometro = getControladorMicromedicao().pesquisarDataInstalacaoHidrometroAgua(idImovel);

									if (dataInstalacaoHidrometro == null) {

										sessionContext.setRollbackOnly();
										throw new ControladorException("atencao.imovel_ligacao_agua_sem_hidrometro", null, idImovel.toString());
									}
								}

								// Colocado por Raphael Rossiter em 19/01/2007
								int anoMesUltimoConsumoFaturado = getControladorMicromedicao().pesquisarUltimoAnoMesConsumoFaturado(idImovel,
										LigacaoTipo.LIGACAO_AGUA);

								int quantidadeMeses = Util.dataDiff(dataInstalacaoHidrometro, new Date());

								/*
								 * Se a quantidade de meses for maior que o
								 * numero de meses calculado do sistema
								 * parametro
								 */
								if (quantidadeMeses > numeroMesesCalculoConsumo.intValue()) {

									quantidadeMeses = numeroMesesCalculoConsumo.intValue();
								}

								BigDecimal valorAcumulado = BigDecimal.ZERO;

								for (int i = quantidadeMeses; i > 0; i--) {

									Integer consumoMes = null;
									int anoMesCalculo = Util.subtrairMesDoAnoMes(anoMesUltimoConsumoFaturado, i - 1);
									if (indicadorMedicaoTipo != null) {

										// 7.1.3.5.2.1.1 Caso a opção de
										// ligação seja de água ou poço
										consumoMes = getControladorMicromedicao().pesquisarConsumoFaturaMes(anoMesCalculo, new Integer(indicadorMedicaoTipo),
												idImovel);

									}

									// 7.1.3.5.2.3 O sistema deve obter o valor
									// de água e de esgoto
									// [SB0004 - Calcular Valor de Água e/ou
									// Esgoto]
									BigDecimal valorAtual = calcularValorAguaEsgoto(consumoMes, sistemaParametro, idImovel, fiscalizacaoSituacao.getId(),
											idLigacaoAguaSituacaoImovel, idLigacaoEsgotoSituacaoImovel);
									// 7.1.3.5.2.3 O sistema deverá obter o
									// valor final
									BigDecimal valorDiferenca = maiorValor.subtract(valorAtual);
									valorAcumulado = valorAcumulado.add(valorDiferenca);

								}

								// 7.1.3.6 O sistema deverá inserir o débito a
								// cobrar
								// [SB0003 - Calcular/Inserir Valor]
								// o sistema deverá inserir o débito a cobrar
								// para o imóvel
								calcularInserirValor(valorAcumulado, idImovel, fiscalizacaoSituacao.getId(), new Integer(idOrdemServico), idDebitoTipo,
										autosInfracao, usuarioLogado);

							}

							// 7.1.4. Caso o código do calculo de consumo=4
							if (codigoConsumoCalculado != null && codigoConsumoCalculado.equals(FiscalizacaoSituacaoServicoACobrar.CONSUMO_CALCULO_QUATRO)) {

								Integer idServicoTipo = repositorioOrdemServico.pesquisarIdServicoTipoDaOS(idOrdemServico);
								Short tipoLigacao = null;
								if (indicadorMedicaoTipo != null) {
									tipoLigacao = new Short(indicadorMedicaoTipo);
								}
								// 7.1.4.2 o sistema deverá inserir o
								// débitoa cobrar para o imóvel
								// [UC0475] Obter valor do débito

								BigDecimal valorDebito = getControladorAtendimentoPublico().obterValorDebito(idServicoTipo, idImovel, tipoLigacao);

								if (valorDebito != null && valorDebito.compareTo(new BigDecimal("0.00")) == 1) {

									// [SB003 - Calcular/Inserir Valor]
									// o sistema deverá inserir o débito a
									// cobrar para o imóvel
									Short gerouDebitoACobrar = calcularInserirValor(valorDebito, idImovel, fiscalizacaoSituacao.getId(), new Integer(
											idOrdemServico), idDebitoTipo, autosInfracao, usuarioLogado);
									helper.setIndicadorDebitoOrdemServicoFiscSit(gerouDebitoACobrar);

								}
							}

							// 7.1.5. Caso o código do calculo de consumo=5
							if (codigoConsumoCalculado != null && codigoConsumoCalculado.equals(FiscalizacaoSituacaoServicoACobrar.CONSUMO_CALCULO_CINCO)) {

								BigDecimal valorDebito = new BigDecimal("0.00");

								// 7.1.5.1 Caso o imóvel tenha consumo
								// médio medido maior ou igual que consumo médio
								// não medido
								// passar o valor do consumo médio médido
								if (consumoMedioMedido >= consumoMedioNaoMedido) {
									// [SB0004 - Calcular Valor de Água e/ou
									// Esgoto]
									valorDebito = calcularValorAguaEsgoto(consumoMedioMedido, sistemaParametro, idImovel, fiscalizacaoSituacao.getId(),
											idLigacaoAguaSituacaoImovel, idLigacaoEsgotoSituacaoImovel);
								} else {
									// 7.1.5.2 O imóvel tenha consumo médio
									// medido
									// menor que o consumo medio não medido
									// passar o consumo médio não medido
									if (consumoMedioMedido < consumoMedioNaoMedido) {
										// [SB0004 - Calcular Valor de Água
										// e/ou Esgoto]
										valorDebito = calcularValorAguaEsgoto(consumoMedioNaoMedido, sistemaParametro, idImovel, fiscalizacaoSituacao.getId(),
												idLigacaoAguaSituacaoImovel, idLigacaoEsgotoSituacaoImovel);
									}
								}

								/*
								 * 7.1.8 - O sistema deverá aplicar o fator
								 * obtido no valor calculado e inserir o débito
								 * a cobrar para o imóvel [SB0003 -
								 * Calcular/Inserir Valor].
								 */
								Short fatorMultiplicacaoAgua = this.obterFatorMultiplicacaoParaFiscalizacaoAgua(idLigacaoAguaSituacaoImovel,
										fiscalizacaoSituacao.getId(), idImovel);

								valorDebito = valorDebito.multiply(new BigDecimal(fatorMultiplicacaoAgua.toString()));

								// [SB003 - Calcular/Inserir Valor]
								// o sistema deverá inserir o débito a
								// cobrar para o imóvel
								Short gerouDebitoACobrar = calcularInserirValor(valorDebito, idImovel, fiscalizacaoSituacao.getId(),
										new Integer(idOrdemServico), idDebitoTipo, autosInfracao, usuarioLogado);
								helper.setIndicadorDebitoOrdemServicoFiscSit(gerouDebitoACobrar);
							}

							// 7.1.6. Caso o código do calculo de consumo=6
							if (codigoConsumoCalculado != null && codigoConsumoCalculado.equals(FiscalizacaoSituacaoServicoACobrar.CONSUMO_CALCULO_SEIS)) {

								BigDecimal valorDebito = new BigDecimal("0.00");

								// 7.1.6.1 Caso o imóvel tenha consumo
								// médio medido maior ou igual que consumo médio
								// não medido
								// passar o valor do consumo médio médido
								if (consumoMedioMedido >= consumoMedioNaoMedido) {
									// [SB0004 - Calcular Valor de Água e/ou
									// Esgoto]
									valorDebito = calcularValorAguaEsgoto(consumoMedioMedido, sistemaParametro, idImovel, fiscalizacaoSituacao.getId(),
											idLigacaoAguaSituacaoImovel, idLigacaoEsgotoSituacaoImovel);
								} else {
									// 7.1.6.2 O imóvel tenha consumo médio
									// medido
									// menor que o consumo medio não medido
									// passar o consumo médio não medido
									if (consumoMedioMedido < consumoMedioNaoMedido) {
										// [SB0004 - Calcular Valor de Água
										// e/ou Esgoto]
										valorDebito = calcularValorAguaEsgoto(consumoMedioNaoMedido, sistemaParametro, idImovel, fiscalizacaoSituacao.getId(),
												idLigacaoAguaSituacaoImovel, idLigacaoEsgotoSituacaoImovel);
									}
								}

								/*
								 * 7.1.8 - O sistema deverá aplicar o fator
								 * obtido no valor calculado e inserir o débito
								 * a cobrar para o imóvel [SB0003 -
								 * Calcular/Inserir Valor].
								 */
								Short fatorMultiplicacaoEsgoto = this.obterFatorMultiplicacaoParaFiscalizacaoEsgoto(idLigacaoEsgotoSituacaoImovel,
										fiscalizacaoSituacao.getId(), idImovel);

								valorDebito = valorDebito.multiply(new BigDecimal(fatorMultiplicacaoEsgoto.toString()));

								// [SB003 - Calcular/Inserir Valor]
								// o sistema deverá inserir o débito a
								// cobrar para o imóvel
								Short gerouDebitoACobrar = calcularInserirValor(valorDebito, idImovel, fiscalizacaoSituacao.getId(),
										new Integer(idOrdemServico), idDebitoTipo, autosInfracao, usuarioLogado);
								helper.setIndicadorDebitoOrdemServicoFiscSit(gerouDebitoACobrar);
							}

							// Alterado por Rômulo Aurélio
							// Analista: Rosana Carvalho
							// Data: 16/11/2009
							// 7.1.13. Caso o código do calculo de consumo = 10
							if (codigoConsumoCalculado != null && codigoConsumoCalculado.equals(FiscalizacaoSituacaoServicoACobrar.CONSUMO_CALCULO_DEZ)) {

								BigDecimal valorDebito = new BigDecimal("0.00");
								FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
								filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.ID, idDebitoTipo));

								Collection colecaoDebitoTipo = RepositorioUtilHBM.getInstancia().pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());

								if (colecaoDebitoTipo != null && !colecaoDebitoTipo.isEmpty()) {

									DebitoTipo debitoTipo = (DebitoTipo) colecaoDebitoTipo.iterator().next();

									// 7.1.13.1 O sistema devera passar o valor
									// sugerido do tipo de debito
									valorDebito = debitoTipo.getValorSugerido();

									// 7.1.13.2 - [SB003] - Calcular/Inserir
									// Valor]
									// o sistema deverá inserir o débito a
									// cobrar para o imóvel
									Short gerouDebitoACobrar = calcularInserirValor(valorDebito, idImovel, fiscalizacaoSituacao.getId(), new Integer(
											idOrdemServico), idDebitoTipo, autosInfracao, usuarioLogado);
									helper.setIndicadorDebitoOrdemServicoFiscSit(gerouDebitoACobrar);
								}

								// 8.1.15. Caso código de cálculo de consumo =
								// 11
							} else if (codigoConsumoCalculado != null && codigoConsumoCalculado.equals(FiscalizacaoSituacaoServicoACobrar.CONSUMO_CALCULO_ONZE)) {

								Categoria categoriaPrincipal = this.getControladorImovel().obterPrincipalCategoriaImovel(idImovel);

								ImovelSubcategoria subcategoriaPrincipal = null;
								BigDecimal valorTarifa = BigDecimal.ZERO;
								ConsumoTarifaVigencia consumoTarifaVigencia = null;

								Integer anoMesReferencia = sistemaParametro.getAnoMesFaturamento();
								Date dataFaturamento = Util.criarData(1, Util.obterMes(anoMesReferencia), Util.obterAno(anoMesReferencia));

								try {
									Collection collectionConsumoTarifaVigencia = this.repositorioFaturamento.pesquisarConsumoTarifaVigenciaImovel(idImovel,
											dataFaturamento);

									if (collectionConsumoTarifaVigencia == null || collectionConsumoTarifaVigencia.isEmpty()) {

										System.out.println(" %%% ERRO.informarRetornoOSFiscalizacao: " + "Imovel: " + idImovel + ", Data Faturamneto: "
												+ dataFaturamento);

										// A lista com as tarifas vigentes está
										// nula ou vazia
										sessionContext.setRollbackOnly();
										throw new ControladorException("atencao.nao_cadastrada_data_vigencia");

									} else {

										Object[] retornoConsumo = (Object[]) Util.retonarObjetoDeColecao(collectionConsumoTarifaVigencia);

										consumoTarifaVigencia = new ConsumoTarifaVigencia();
										consumoTarifaVigencia.setId((Integer) retornoConsumo[0]);
									}

									if (sistemaParametro.getIndicadorTarifaCategoria().equals(ConstantesSistema.SIM)) {

										Collection colecaoConsumoTarifaCategoria = this.repositorioFaturamento.pesquisarConsumoTarifaCategoria(
												consumoTarifaVigencia, categoriaPrincipal);

										if (colecaoConsumoTarifaCategoria != null && !colecaoConsumoTarifaCategoria.isEmpty()) {

											ConsumoTarifaCategoria consumoTarifaCategoriaPrincipal = (ConsumoTarifaCategoria) Util
													.retonarObjetoDeColecao(colecaoConsumoTarifaCategoria);

											valorTarifa = consumoTarifaCategoriaPrincipal.getValorTarifaMinima();
										}

									} else {

										Collection<ImovelSubcategoria> colSubCategorias = this.repositorioImovel.obterSubCategoriasPorCategoria(
												categoriaPrincipal.getId(), idImovel);

										// Selecionamos o de maior quantidade de
										// economias,
										// caso exista subcategorias com o
										// mesmos numero de economias,
										// considerar a que tem o menor valor
										if (colSubCategorias != null && colSubCategorias.size() > 0) {
											for (ImovelSubcategoria sub : colSubCategorias) {

												if (subcategoriaPrincipal == null
														|| subcategoriaPrincipal.getQuantidadeEconomias() < sub.getQuantidadeEconomias()) {

													subcategoriaPrincipal = sub;

													Collection colecaoConsumoTarifaCategoriaPrincipal = this.repositorioFaturamento
															.pesquisarConsumoTarifaCategoriaPorSubCategoria(consumoTarifaVigencia, categoriaPrincipal,
																	subcategoriaPrincipal.getComp_id().getSubcategoria());

													if (colecaoConsumoTarifaCategoriaPrincipal != null && !colecaoConsumoTarifaCategoriaPrincipal.isEmpty()) {

														ConsumoTarifaCategoria consumoTarifaCategoriaPrincipal = (ConsumoTarifaCategoria) Util
																.retonarObjetoDeColecao(colecaoConsumoTarifaCategoriaPrincipal);

														valorTarifa = consumoTarifaCategoriaPrincipal.getValorTarifaMinima();

													}

												} else if (subcategoriaPrincipal.getQuantidadeEconomias() == sub.getQuantidadeEconomias()) {

													Collection colecaoConsumoTarifaCategoriaPesquisado = this.repositorioFaturamento
															.pesquisarConsumoTarifaCategoriaPorSubCategoria(consumoTarifaVigencia, categoriaPrincipal, sub
																	.getComp_id().getSubcategoria());

													BigDecimal valorTarifaPesquisado = BigDecimal.ZERO;

													if (colecaoConsumoTarifaCategoriaPesquisado != null && !colecaoConsumoTarifaCategoriaPesquisado.isEmpty()) {

														ConsumoTarifaCategoria consumoTarifaCategoriaPesquisado = (ConsumoTarifaCategoria) Util
																.retonarObjetoDeColecao(colecaoConsumoTarifaCategoriaPesquisado);

														valorTarifaPesquisado = consumoTarifaCategoriaPesquisado.getValorTarifaMinima();

													}

													if (valorTarifaPesquisado.compareTo(BigDecimal.ZERO) == 1
															&& valorTarifaPesquisado.compareTo(valorTarifa) == -1) {

														subcategoriaPrincipal = sub;
														valorTarifa = valorTarifaPesquisado;
													}
												}
											}

											if (valorTarifa.compareTo(BigDecimal.ZERO) != 1) {

												Collection colecaoConsumoTarifaCategoria = this.repositorioFaturamento.pesquisarConsumoTarifaCategoria(
														consumoTarifaVigencia, categoriaPrincipal);

												if (colecaoConsumoTarifaCategoria != null && !colecaoConsumoTarifaCategoria.isEmpty()) {

													ConsumoTarifaCategoria consumoTarifaCategoriaPrincipal = (ConsumoTarifaCategoria) Util
															.retonarObjetoDeColecao(colecaoConsumoTarifaCategoria);

													valorTarifa = consumoTarifaCategoriaPrincipal.getValorTarifaMinima();
												}
											}

										}
									}

								} catch (ErroRepositorioException ex) {
									sessionContext.setRollbackOnly();
									throw new ControladorException("erro.sistema", ex);
								}

								if (valorTarifa.compareTo(BigDecimal.ZERO) != 1) {
									sessionContext.setRollbackOnly();
									throw new ControladorException("atencao.categoria_nao_existe_para_tarifa_vigente");
								}

								// O sistema deverá multiplicar a quantidade de
								// economias da subcategoria selecionada,
								// limitando a 4 (quatro), pelo valor da tarifa,
								// obtendo assim o valor calculado.
								BigDecimal valorDebito = new BigDecimal("0.00");

								short quantidadeEconomias = 4;

								if (subcategoriaPrincipal.getQuantidadeEconomias() <= 4) {
									quantidadeEconomias = subcategoriaPrincipal.getQuantidadeEconomias();
								}

								valorDebito = valorTarifa.multiply(new BigDecimal(quantidadeEconomias));

								// O sistema deverá inserir o débito a cobrar
								// para o imóvel
								// [SB0003 ? Calcular/Inserir Valor].

								Short gerouDebitoACobrar = this.calcularInserirValor(valorDebito, idImovel, fiscalizacaoSituacao.getId(), new Integer(
										idOrdemServico), idDebitoTipo, autosInfracao, usuarioLogado);

								helper.setIndicadorDebitoOrdemServicoFiscSit(gerouDebitoACobrar);

							}
						}

					}

				}

				// 10. O sistema deverá atualizar o indicador de reincidência de
				// infração.
				// SB0007 - Atualizar indicador para reincidência de infração.
				if (fiscalizacaoSituacao != null && imovelPesquisa != null
						&& fiscalizacaoSituacao.getIndicadorVerificarReincidencia().compareTo(ConstantesSistema.SIM) == 0) {

					imovelPesquisa.setIndicadorReincidenciaInfracao(ConstantesSistema.SIM.intValue());
					imovelPesquisa.setUsuarioParaHistorico(usuarioLogado);
					this.getControladorAtualizacaoCadastro().atualizar(imovelPesquisa);
				}

				// [SB0008] - Inserir Dados da Ligação de Esgoto
				if (ligacaoEsgoto != null) {

					if (ligacaoEsgoto.getImovel() != null) {
						this.getControladorUtil().atualizar(ligacaoEsgoto);
					} else {
						Imovel imovelInserir = new Imovel();
						imovelInserir.setId(idImovel);

						ligacaoEsgoto.setImovel(imovelInserir);
						this.getControladorUtil().inserir(ligacaoEsgoto);
					}

					// [SB0009] - Inserir Faturamento Situação Histórico
					if (ligacaoEsgoto.getIndicadorLigacaoEsgoto() == 2) {
						this.inserirDadosFaturamentoSituacaoHistorico(idImovel, usuarioLogado);
					}
				}

				/*
				 * Colocado por Rômulo Aurélio em 13/05/2009 - Analista: Nelson
				 * Carvalho 7.1.10.
				 */
				if (dadosAutoInfracao != null && fiscalizacaoSituacao.getIndicadorAtualizacaoAutosInfracao() == 1) {
					AutosInfracao autosInfracao = this.gerarAutosInfracao(dadosAutoInfracao, new Short(indicadorDocumentoEntregue), idImovel, idOrdemServico,
							fiscalizacaoSituacao.getId(), usuarioLogado);

					if (indicadorDebito == null || !indicadorDebito.equals(ConstantesSistema.SIM)) {
						// [FS0009]-Verificar opção de geração do débito

						if (dadosAutoInfracao.getIndicadorGerarDebito() != null && dadosAutoInfracao.getIndicadorGerarDebito().toString().equalsIgnoreCase("2")
								&& autosInfracao.getAutoInfracaoSituacao().getIndicadorGerarDebito().toString().equalsIgnoreCase("1")) {

							throw new ControladorException("atencao.situacao_auto_infracao_incompativel_geracao_debito");

						} else if (dadosAutoInfracao.getIndicadorGerarDebito() != null && dadosAutoInfracao.getIndicadorGerarDebito().equals("1")) {

							Short gerouDebitoACobrar = getControladorAtendimentoPublico().gerarDebitoACobrarAutoInfracao(autosInfracao, sistemaParametro);
							helper.setIndicadorDebitoOrdemServicoFiscSit(gerouDebitoACobrar);

						}

					}
				}

				// RM93 - adicionado por Vivianne Sousa - 20/12/2010 -
				// analista:Rosana
				// Incluir situação de cobrança, caso tenha informação na tabela
				// FISCALIZACAO_SITUACAO(CBST_ID)
				if (fiscalizacaoSituacao != null && fiscalizacaoSituacao.getCobrancaSituacao() != null) {

					boolean existeImovelCobrancaSituacaoParaImovel = getControladorSpcSerasa().existeImovelCobrancaSituacaoParaImovel(imovelPesquisa.getId(),
							fiscalizacaoSituacao.getCobrancaSituacao().getId());

					if (!existeImovelCobrancaSituacaoParaImovel) {
						CobrancaSituacao cobrancaSituacao = fiscalizacaoSituacao.getCobrancaSituacao();
						Cliente clienteUsuarioImovel = getControladorCliente().retornaClienteUsuario(imovelPesquisa.getId());

						getControladorImovel().inserirImovelSitucaoCobranca(imovelPesquisa, cobrancaSituacao, clienteUsuarioImovel, null, null, new Date(),
								sistemaParametro.getAnoMesFaturamento(), null, usuarioLogado);
					}
				}

			}

			if (!finalizador) {
				// 8. o sistema deverá inserir a ordem de serviço de
				// fiscalização
				// [SB0001 - Atualizar Ordem de Serviço]
				atualizarOrdemServico(idOrdemServico, indicadorDocumentoEntregue, indicadorAtualizacaoSituacaoLigacaoAgua,
						indicadorAtualizacaoSituacaoLigacaoEsgoto, colecaoFiscalizacaoSituacaoSelecionada, idCobrancaDocumento);
			}

			// [SB0010] - Atualizar Situação da Ligação de Esgoto
			if (indicadorDocumentoEntregue.equals("3")) {
				this.repositorioImovel.atualizarImovelLigacaoAguaEsgoto(idImovel, null, Integer.valueOf(indicadorLigacaoEsgoto));
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;

	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * Utilizado para obter o fator de multiplicalçao do débito no cáculo 5
	 * 
	 * @author Raphael Rossiter
	 * @date 16/03/2007
	 * 
	 * @param idLigacaoAguaSituacaoImovel
	 *            , idFiscalizacaoSituacao, idImovel
	 * @return fatorMultiplicacao
	 * @throws ControladorException
	 */
	public Short obterFatorMultiplicacaoParaFiscalizacaoAgua(Integer idLigacaoAguaSituacaoImovel, Integer idFiscalizacaoSituacao, Integer idImovel)
			throws ControladorException {

		Short fatorMultiplicacao = null;

		try {

			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();

			/*
			 * 7.1.5.4 - Caso existe fator de multiplicação para ser aplicado ao
			 * débito para a situação de fiscalização informada e para situação
			 * da ligação de água
			 */
			fatorMultiplicacao = repositorioOrdemServico.pesquisarNumeroMesesFaturaAgua(idLigacaoAguaSituacaoImovel, idFiscalizacaoSituacao);

			/*
			 * 7.1.5.5 - Caso contrário, o sistema deverá obter o valor de
			 * diferença de meses a ser utilizado como fator de multiplicação
			 */
			if (fatorMultiplicacao == null) {

				LigacaoAgua ligacaoAgua = this.getControladorLigacaoAgua().recuperaParametrosLigacaoAgua(idImovel);

				/*
				 * 7.1.5.5.1 - Caso a situação da ligação de água do imóvel seja
				 * igual a "CORTADO"
				 */
				if (ligacaoAgua != null && idLigacaoAguaSituacaoImovel != null && idLigacaoAguaSituacaoImovel.equals(LigacaoAguaSituacao.CORTADO)) {

					/*
					 * 7.1.5.5.1.1 - O sistema deverá calcular a "diferença", em
					 * meses, da data de corte da ligação de água do imóvel e a
					 * data corrente
					 */
					fatorMultiplicacao = new Short(String.valueOf(Util.dataDiff(ligacaoAgua.getDataCorte(), new Date())));
				}

				/*
				 * 7.1.5.5.2 - Caso a situação da ligação de água do imóvel seja
				 * igual a "SUPRIMIDO" ou "SUPR. PARCIAL" ou "SUPR. A PEDIDO"
				 */
				else if (ligacaoAgua != null
						&& idLigacaoAguaSituacaoImovel != null
						&& (idLigacaoAguaSituacaoImovel.equals(LigacaoAguaSituacao.SUPRIMIDO)
								|| idLigacaoAguaSituacaoImovel.equals(LigacaoAguaSituacao.SUPR_PARC) || idLigacaoAguaSituacaoImovel
									.equals(LigacaoAguaSituacao.SUPR_PARC_PEDIDO))) {

					/*
					 * 7.1.5.5.1.2 - O sistema deverá calcular a "diferença", em
					 * meses, da data da supressão da ligação de água do imóvel
					 * e a data corrente
					 */
					fatorMultiplicacao = new Short(String.valueOf(Util.dataDiff(ligacaoAgua.getDataSupressao(), new Date())));

				}
			}

			/*
			 * 7.1.6 - Caso a "diferença em meses" seja a igual a zeros, o
			 * sistema deverá atualizar o fator de multiplicação por 1 (um)
			 */
			if ((fatorMultiplicacao == null) || (fatorMultiplicacao != null && fatorMultiplicacao.equals(ConstantesSistema.ZERO))) {

				fatorMultiplicacao = new Short("1");
			}

			/*
			 * 7.1.7 - Caso contrário, o sistema deverá obter o valor máximo de
			 * meses de sanção
			 */
			else if (fatorMultiplicacao != null) {

				/*
				 * 7.1.7.1 - Caso a "diferença em meses" seja maior que o número
				 * máximo de meses, usar como fator o número máximo de meses
				 */
				if (sistemaParametro.getNumeroMaximoMesesSancoes() != null && fatorMultiplicacao > sistemaParametro.getNumeroMaximoMesesSancoes()) {

					fatorMultiplicacao = sistemaParametro.getNumeroMaximoMesesSancoes();
				}
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return fatorMultiplicacao;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * Utilizado para obter o fator de multiplicalçao do débito no cáculo 6
	 * 
	 * @author Raphael Rossiter
	 * @date 16/03/2007
	 * 
	 * @param idLigacaoAguaSituacaoImovel
	 *            , idFiscalizacaoSituacao, idImovel
	 * @return fatorMultiplicacao
	 * @throws ControladorException
	 */
	public Short obterFatorMultiplicacaoParaFiscalizacaoEsgoto(Integer idLigacaoEsgotoSituacaoImovel, Integer idFiscalizacaoSituacao, Integer idImovel)
			throws ControladorException {

		Short fatorMultiplicacao = null;

		try {

			/*
			 * 7.1.5.4 - Caso existe fator de multiplicação para ser aplicado ao
			 * débito para a situação de fiscalização informada e para situação
			 * da ligação de esgoto
			 */
			fatorMultiplicacao = repositorioOrdemServico.pesquisarNumeroMesesFaturaEsgoto(idLigacaoEsgotoSituacaoImovel, idFiscalizacaoSituacao);

			/*
			 * 7.1.5.5 - Caso contrário, o sistema deverá obter o valor de
			 * diferença de meses a ser utilizado como fator de multiplicação
			 */
			if (fatorMultiplicacao == null) {

				fatorMultiplicacao = new Short("1");
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return fatorMultiplicacao;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * [SB0001] - Atualizar Ordem de Serviço
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public void atualizarOrdemServico(Integer idOrdemServico, String indicadorDocumentoEntregue, Short indicadorAtualizacaoSituacaoLigacaoAgua,
			Short indicadorAtualizacaoSituacaoLigacaoEsgoto, Collection colecaoFiscalizacaoSituacaoSelecionada, Integer idCobrancaDocumento)
			throws ControladorException {

		try {

			// caso o indicador documento entregue seja igual a três que
			// equevale a
			// nenhum então seta essa variavel para null
			if (indicadorDocumentoEntregue != null && indicadorDocumentoEntregue.equals("4")) {
				indicadorDocumentoEntregue = null;
			}

			// Id da primeira situacao fiscalizacao selecionada
			Integer idFiscalizacaoSituacaoSelecionada = null;
			if (colecaoFiscalizacaoSituacaoSelecionada != null && !colecaoFiscalizacaoSituacaoSelecionada.isEmpty()) {

				SituacaoEncontradaHelper helper = (SituacaoEncontradaHelper) Util.retonarObjetoDeColecao(colecaoFiscalizacaoSituacaoSelecionada);

				if (helper.getFiscalizacaoSituacao() != null && helper.getFiscalizacaoSituacao().getId() != null) {
					idFiscalizacaoSituacaoSelecionada = helper.getFiscalizacaoSituacao().getId();
				}

			}

			repositorioOrdemServico.atualizarParmsOrdemFiscalizacao(idOrdemServico, indicadorDocumentoEntregue, indicadorAtualizacaoSituacaoLigacaoAgua,
					indicadorAtualizacaoSituacaoLigacaoEsgoto, idFiscalizacaoSituacaoSelecionada);

			// 2.O sistema deverá excluir as situações de fiscalização já
			// associadas à ordem de serviço
			repositorioOrdemServico.excluirSituacaoFiscalizacaoPorOS(idOrdemServico);

			// 3.O sistema deverá excluir as situações de fiscalização
			// já associadas ao documento de cobrança da ordem de serviço
			repositorioOrdemServico.excluirCobrancaDocumentoFiscPorOS(idOrdemServico);

			// 4.Para cada Situação da Fiscalização Selecionada
			// 4.1.O sistema deverá inserir os seguintes dados na tabela
			// ORDEM_SERVICO_FISC_SIT:
			OrdemServico ordemServico = new OrdemServico();
			ordemServico.setId(idOrdemServico);
			Date menorDataFiscalizacaoSituacao = new Date();
			Iterator iteratorFiscalizacaoSituacaoSelecionada = colecaoFiscalizacaoSituacaoSelecionada.iterator();
			while (iteratorFiscalizacaoSituacaoSelecionada.hasNext()) {

				SituacaoEncontradaHelper helper = (SituacaoEncontradaHelper) iteratorFiscalizacaoSituacaoSelecionada.next();

				FiscalizacaoSituacao fiscalizacaoSituacao = helper.getFiscalizacaoSituacao();

				OrdemServicoFiscSit ordemServicoFiscSit = new OrdemServicoFiscSit();
				ordemServicoFiscSit.setFiscalizacaoSituacao(fiscalizacaoSituacao);
				ordemServicoFiscSit.setOrdemServico(ordemServico);

				if (helper.getIndicadorDebitoOrdemServicoFiscSit() != null) {
					ordemServicoFiscSit.setIndicadorDebito(helper.getIndicadorDebitoOrdemServicoFiscSit());
				} else {
					ordemServicoFiscSit.setIndicadorDebito(ConstantesSistema.NAO);
				}
				ordemServicoFiscSit.setDataFiscalizacaoSituacao(helper.getDataFiscalizacao());
				ordemServicoFiscSit.setUltimaAlteracao(new Date());

				getControladorUtil().inserir(ordemServicoFiscSit);

				if (menorDataFiscalizacaoSituacao.compareTo(helper.getDataFiscalizacao()) == 1) {
					menorDataFiscalizacaoSituacao = helper.getDataFiscalizacao();
				}

				// 4.2.O sistema deverá inserir os seguintes dados na tabela
				// COBRANCA_DOCUMENTO_FISC:
				if (idCobrancaDocumento != null) {

					CobrancaDocumento cobrancaDocumento = new CobrancaDocumento();
					cobrancaDocumento.setId(idCobrancaDocumento);

					CobrancaDocumentoFisc cobrancaDocumentoFisc = new CobrancaDocumentoFisc();
					cobrancaDocumentoFisc.setFiscalizacaoSituacao(fiscalizacaoSituacao);
					cobrancaDocumentoFisc.setOrdemServico(ordemServico);
					cobrancaDocumentoFisc.setUltimaAlteracao(new Date());
					cobrancaDocumentoFisc.setCobrancaDocumento(cobrancaDocumento);
					getControladorUtil().inserir(cobrancaDocumentoFisc);
				}

			}

			if (idCobrancaDocumento != null) {
				// Alterado por Francisco - 22/05/08, por conta do [UC478 -
				// Gerar Resumo de Ações de cobrança]
				// O encerramento da OS atualiza o documento de cobranca
				// correspondente
				// Analista: Ana Breda
				DadosPesquisaCobrancaDocumentoHelper cobrancaDocumentoAtualizar = new DadosPesquisaCobrancaDocumentoHelper();
				cobrancaDocumentoAtualizar.setIdDocumento(idCobrancaDocumento);
				cobrancaDocumentoAtualizar.setIdSituacaoAcao(CobrancaAcaoSituacao.FISCALIZADA);
				// cobrancaDocumentoAtualizar.setDataSituacaoAcao(new Date());
				cobrancaDocumentoAtualizar.setDataSituacaoAcao(menorDataFiscalizacaoSituacao);
				// cobrancaDocumentoAtualizar.setIdFiscalizacao(fiscalizacaoSituacao.getId());
				Collection colecaoCobrancaDocumentoParaAtualizar = new ArrayList();
				colecaoCobrancaDocumentoParaAtualizar.add(cobrancaDocumentoAtualizar);
				getControladorCobranca().atualizarCobrancaDocumento(colecaoCobrancaDocumentoParaAtualizar);
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * [SB0002] - Atualizar Imóvel/Ligação Água
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Short[] atualizarImovelLigacaoAgua(Integer idSituacaoEncontrada, Integer idImovel, Integer consumoDefinido, Integer idLigacaoAguaSituacao,
			Integer idLigacaoEsgotoSituacao, Short atualizarSituacaoLigacaoAgua, Short atualizarSituacaoLigacaoEsgoto) throws ControladorException {

		Integer idLigacaoAguaSituacaoNovo = null;
		Integer idLigacaoEsgotoSituacaoNovo = null;
		short indicadorLigacaoDataAtualiza = 0;
		short indicadorConsumoFixado = 0;
		short indicadorVolumeFixado = 0;
		Short indicadorAtualizacaoSituacaoLigacaoAgua = null;
		Short indicadorAtualizacaoSituacaoLigacaoEsgoto = null;
		try {

			if (atualizarSituacaoLigacaoAgua.equals(ConstantesSistema.SIM)) {

				Object[] parmsLigacaoAguaFiscalizacao = repositorioOrdemServico.pesquisarIdFiscalizacaoSituacaoAguaOS(idSituacaoEncontrada,
						idLigacaoAguaSituacao);

				if (parmsLigacaoAguaFiscalizacao != null) {
					if (parmsLigacaoAguaFiscalizacao[3] != null) {
						idLigacaoAguaSituacaoNovo = (Integer) parmsLigacaoAguaFiscalizacao[3];
					}
					if (parmsLigacaoAguaFiscalizacao[1] != null) {
						indicadorConsumoFixado = (Short) parmsLigacaoAguaFiscalizacao[1];
					}
					if (parmsLigacaoAguaFiscalizacao[2] != null) {
						indicadorLigacaoDataAtualiza = (Short) parmsLigacaoAguaFiscalizacao[2];
					}

					indicadorAtualizacaoSituacaoLigacaoAgua = ConstantesSistema.SIM;
				}

			}

			if (atualizarSituacaoLigacaoEsgoto.equals(ConstantesSistema.SIM)) {

				Object[] parmsLigacaoEsgotoFiscalizacao = repositorioOrdemServico.pesquisarIdFiscalizacaoSituacaoEsgotoOS(idSituacaoEncontrada,
						idLigacaoEsgotoSituacao);

				if (parmsLigacaoEsgotoFiscalizacao != null) {
					if (parmsLigacaoEsgotoFiscalizacao[2] != null) {
						idLigacaoEsgotoSituacaoNovo = (Integer) parmsLigacaoEsgotoFiscalizacao[2];
					}
					if (parmsLigacaoEsgotoFiscalizacao[1] != null) {
						indicadorVolumeFixado = (Short) parmsLigacaoEsgotoFiscalizacao[1];
					}
					if (parmsLigacaoEsgotoFiscalizacao[3] != null) {
						indicadorLigacaoDataAtualiza = (Short) parmsLigacaoEsgotoFiscalizacao[3];
					}
					indicadorAtualizacaoSituacaoLigacaoEsgoto = ConstantesSistema.SIM;
				}
			}

			// chama o método no controlador imóvel que vai atualizar a tabela
			// imóvel ,ligaçãoAguaSituação e ligaçãoEsgotoSituação
			getControladorImovel().atualizarImovelLigacaoAguaEsgoto(idImovel, idLigacaoAguaSituacaoNovo, idLigacaoEsgotoSituacaoNovo,
					indicadorLigacaoDataAtualiza, indicadorConsumoFixado, consumoDefinido, indicadorVolumeFixado);

			Short[] retorno = new Short[2];
			retorno[0] = indicadorAtualizacaoSituacaoLigacaoAgua;
			retorno[1] = indicadorAtualizacaoSituacaoLigacaoEsgoto;
			return retorno;

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0372] - Manter Equipe
	 * 
	 * Atualiza a equipe e seus componentes na base
	 * 
	 * @author Rafael Corrêa
	 * @date 14/11/2006
	 * 
	 * @param equipe
	 * @throws ControladorException
	 */
	public void atualizarEquipe(Equipe equipe, Collection colecaoEquipeComponentes, Usuario usuarioLogado, Collection colecaoEquipeEquipamentosEspeciais)
			throws ControladorException {

		// [FS0011] - Verificar equipe já existente
		FiltroEquipe filtroEquipe = new FiltroEquipe();
		filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.NOME, equipe.getNome()));
		filtroEquipe.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroEquipe.ID, equipe.getId()));

		Collection colecaoEquipeJaExistente = getControladorUtil().pesquisar(filtroEquipe, Equipe.class.getName());

		if (colecaoEquipeJaExistente != null && !colecaoEquipeJaExistente.isEmpty()) {
			throw new ControladorException("atencao.inserir_equipe_mesmo_nome");
		}

		// [FS0009] - Atualização realizada por outro usuário
		filtroEquipe.limparListaParametros();
		filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.ID, equipe.getId()));

		Collection colecaoEquipeBase = getControladorUtil().pesquisar(filtroEquipe, Equipe.class.getName());

		if (colecaoEquipeBase == null || colecaoEquipeBase.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		Equipe equipeBase = (Equipe) colecaoEquipeBase.iterator().next();

		if (equipeBase.getUltimaAlteracao().after(equipe.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		equipe.setUltimaAlteracao(new Date());

		// valida de o imei existe
		if (equipe.getNumeroImei() != null && !equipe.getNumeroImei().equals("")) {
			FiltroEquipe filtroEquipeImei = new FiltroEquipe();
			filtroEquipeImei.adicionarParametro(new ParametroSimples(FiltroEquipe.NUMERO_IMEI, equipe.getNumeroImei()));
			filtroEquipeImei.adicionarParametro(new ParametroSimples(FiltroEquipe.INDICADOR_USO, ConstantesSistema.SIM));
			filtroEquipeImei.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroEquipe.ID, equipe.getId()));
			Collection collEquipes = getControladorUtil().pesquisar(filtroEquipeImei, Equipe.class.getName());
			if (collEquipes != null && !collEquipes.isEmpty()) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.descricao_concatenada", null, "IMEI: " + equipe.getNumeroImei() + " informado para outra equipe.");
			}
		}

		// ------------ REGISTRAR TRANSAÇÃO----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(Operacao.OPERACAO_EQUIPE_ATUALIZAR, new UsuarioAcaoUsuarioHelper(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_EQUIPE_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		equipe.setOperacaoEfetuada(operacaoEfetuada);
		equipe.adicionarUsuario(usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(equipe);
		// ------------ REGISTRAR TRANSAÇÃO----------------------------

		getControladorUtil().atualizar(equipe);

		// Faz um filtro para recuperar todas as equipes componentes na base
		// associadas àquela equipe e remove todas para em seguida inserir
		// apenas as que o usuario selecionou no Atualizar Equipe
		FiltroEquipeComponentes filtroEquipeComponentes = new FiltroEquipeComponentes();
		filtroEquipeComponentes.adicionarParametro(new ParametroSimples(FiltroEquipeComponentes.ID_EQUIPE, equipe.getId()));

		Collection colecaoEquipeComponentesBase = getControladorUtil().pesquisar(filtroEquipeComponentes, EquipeComponentes.class.getName());

		if (colecaoEquipeComponentesBase != null && !colecaoEquipeComponentesBase.isEmpty()) {

			Iterator colecaoEquipeComponentesBaseIterator = colecaoEquipeComponentesBase.iterator();

			while (colecaoEquipeComponentesBaseIterator.hasNext()) {

				EquipeComponentes equipeComponentesBase = (EquipeComponentes) colecaoEquipeComponentesBaseIterator.next();
				getControladorUtil().remover(equipeComponentesBase);

				equipeComponentesBase.setOperacaoEfetuada(operacaoEfetuada);
				equipeComponentesBase.adicionarUsuario(usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);

				registradorOperacao.registrarOperacao(equipeComponentesBase);

			}

		}

		// Insere as equipes selecionadas na base
		if (colecaoEquipeComponentes != null && !colecaoEquipeComponentes.isEmpty()) {

			Iterator colecaoEquipeComponentesIterator = colecaoEquipeComponentes.iterator();

			boolean responsavel = false;

			while (colecaoEquipeComponentesIterator.hasNext()) {

				EquipeComponentes equipeComponentes = (EquipeComponentes) colecaoEquipeComponentesIterator.next();

				if (equipeComponentes.getIndicadorResponsavel() == EquipeComponentes.INDICADOR_RESPONSAVEL_SIM) {
					responsavel = true;
				}

				equipeComponentes.setEquipe(equipe);
				equipeComponentes.setUltimaAlteracao(new Date());

				getControladorUtil().inserir(equipeComponentes);

				equipeComponentes.setOperacaoEfetuada(operacaoEfetuada);
				equipeComponentes.adicionarUsuario(usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);

				registradorOperacao.registrarOperacao(equipeComponentes);
			}

			if (!responsavel) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.inserir_equipe_nenhum_responsavel");
			}
		}

		// Faz um filtro para recuperar todas as equipes Equipamentos Especiais
		// na base
		// associadas àquela equipe e remove todas para em seguida inserir
		// apenas as que o usuario selecionou no Atualizar Equipe
		FiltroEquipeEquipamentosEspeciais filtroEquipeEquipamentosEspeciais = new FiltroEquipeEquipamentosEspeciais();
		filtroEquipeEquipamentosEspeciais.adicionarParametro(new ParametroSimples(FiltroEquipeEquipamentosEspeciais.ID_EQUIPE, equipe.getId()));

		Collection colecaoEquipeEquipamentosEspeciaisBase = getControladorUtil().pesquisar(filtroEquipeEquipamentosEspeciais,
				EquipeEquipamentosEspeciais.class.getName());

		if (colecaoEquipeEquipamentosEspeciaisBase != null && !colecaoEquipeEquipamentosEspeciaisBase.isEmpty()) {

			Iterator colecaoEquipeEquipamentosEspeciaisBaseIterator = colecaoEquipeEquipamentosEspeciaisBase.iterator();

			while (colecaoEquipeEquipamentosEspeciaisBaseIterator.hasNext()) {

				EquipeEquipamentosEspeciais equipeEquipamentosEspeciaisBase = (EquipeEquipamentosEspeciais) colecaoEquipeEquipamentosEspeciaisBaseIterator
						.next();
				getControladorUtil().remover(equipeEquipamentosEspeciaisBase);

				equipeEquipamentosEspeciaisBase.setOperacaoEfetuada(operacaoEfetuada);
				equipeEquipamentosEspeciaisBase.adicionarUsuario(usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);

				registradorOperacao.registrarOperacao(equipeEquipamentosEspeciaisBase);

			}

		}

		// Insere as equipes selecionadas na base
		if (colecaoEquipeEquipamentosEspeciais != null && !colecaoEquipeEquipamentosEspeciais.isEmpty()) {

			Iterator colecaoEquipeEquipamentosEspeciaisIterator = colecaoEquipeEquipamentosEspeciais.iterator();

			while (colecaoEquipeEquipamentosEspeciaisIterator.hasNext()) {

				EquipeEquipamentosEspeciais equipeEquipamentosEspeciais = (EquipeEquipamentosEspeciais) colecaoEquipeEquipamentosEspeciaisIterator.next();

				equipeEquipamentosEspeciais.setEquipe(equipe);
				equipeEquipamentosEspeciais.setUltimaAlteracao(new Date());

				getControladorUtil().inserir(equipeEquipamentosEspeciais);

				equipeEquipamentosEspeciais.setOperacaoEfetuada(operacaoEfetuada);
				equipeEquipamentosEspeciais.adicionarUsuario(usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);

				registradorOperacao.registrarOperacao(equipeEquipamentosEspeciais);
			}

		}
	}

	/**
	 * [UC0383] Manter Material [FS0001] Atualizar Material [FS0002] Atualizar
	 * Material
	 * 
	 * @author Kassia Albuquerque
	 * @date 20/11/2006
	 * 
	 * @param material
	 * @throws ControladorException
	 */

	// [FS0001] VERIFICAR EXISTENCIA DA DESCRIÇÃO
	// [FS0002] VERIFICAR EXISTENCIA DA DESCRIÇÃO ABREVIADA
	public void verificarExistenciaDescricaoMaterial(Material material) throws ControladorException {

		FiltroMaterial filtroMaterial = new FiltroMaterial();
		filtroMaterial.adicionarParametro(new ParametroSimples(FiltroMaterial.DESCRICAO, material.getDescricao()));
		filtroMaterial.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroMaterial.ID, material.getId()));

		Collection colecaoMaterial = getControladorUtil().pesquisar(filtroMaterial, Material.class.getName());
		if (colecaoMaterial != null && !colecaoMaterial.isEmpty()) {
			throw new ControladorException("atencao.material.decricao.existente");
		}

		filtroMaterial = new FiltroMaterial();
		filtroMaterial.adicionarParametro(new ParametroSimples(FiltroMaterial.DESCRICAO_ABREVIADA, material.getDescricaoAbreviada()));
		filtroMaterial.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroMaterial.ID, material.getId()));
		colecaoMaterial = new ArrayList();
		colecaoMaterial = getControladorUtil().pesquisar(filtroMaterial, Material.class.getName());
		if (!colecaoMaterial.isEmpty()) {
			throw new ControladorException("atencao.material.decricaoabreviada.existente");
		}

	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * [SB0004] - Calcular Valor de Água e/ou Esgoto
	 * 
	 * 
	 * @author Sávio Luiz, Raphael Rossiter
	 * @date 20/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public BigDecimal calcularValorAguaEsgoto(int consumoInformado, SistemaParametro sistemaParametro, Integer idImovel, Integer idSituacaoEncontrada,
			Integer idLigacaoAguaSituacao, Integer idLigacaoEsgotoSituacao) throws ControladorException {
		Integer idLigacaoAguaSituacaoNova = null;
		Integer idLigacaoEsgotoSituacaoNova = null;

		BigDecimal valorTotal = new BigDecimal("0.00");
		try {
			Object[] parmsLigacaoAguaFiscalizacao = null;
			if (idLigacaoAguaSituacao != null) {
				parmsLigacaoAguaFiscalizacao = repositorioOrdemServico.pesquisarIdFiscalizacaoSituacaoAguaOS(idSituacaoEncontrada, idLigacaoAguaSituacao);
			}
			if (parmsLigacaoAguaFiscalizacao != null) {
				if (parmsLigacaoAguaFiscalizacao[3] != null) {
					idLigacaoAguaSituacaoNova = (Integer) parmsLigacaoAguaFiscalizacao[3];
				}

			}

			Object[] parmsLigacaoEsgotoFiscalizacao = null;
			if (idLigacaoEsgotoSituacao != null) {
				parmsLigacaoEsgotoFiscalizacao = repositorioOrdemServico.pesquisarIdFiscalizacaoSituacaoEsgotoOS(idSituacaoEncontrada, idLigacaoEsgotoSituacao);
			}

			if (parmsLigacaoEsgotoFiscalizacao != null) {
				if (parmsLigacaoEsgotoFiscalizacao[2] != null) {
					idLigacaoEsgotoSituacaoNova = (Integer) parmsLigacaoEsgotoFiscalizacao[2];
				}
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		// 1.1. ano mes de referencia
		int anoMesReferencia = sistemaParametro.getAnoMesFaturamento();
		/*
		 * // 1.2. situação de ligação de agua if (idLigacaoAguaSituacaoNova ==
		 * null) { idLigacaoAguaSituacao = getControladorImovel()
		 * .pesquisarIdLigacaoAguaSituacao(idImovel); } // 1.3. situação de
		 * ligação de agua if (idLigacaoEsgotoSituacaoNova == null) {
		 * idLigacaoEsgotoSituacao = getControladorImovel()
		 * .pesquisarIdLigacaoEsgotoSituacao(idImovel); }
		 */
		// 1.4. indicador de faturamento água
		short indicadorFatAgua = 1;
		// 1.5. indicador de faturamento esgoto
		short indicadorFatEsgoto = 1;
		Imovel imovel = new Imovel();
		imovel.setId(idImovel);

		/*
		 * Colocado por Raphael Rossiter em 15/01/2007. Será utilizado para
		 * obter o consumo mínimo da ligação
		 * 
		 * ==============================================
		 */
		Integer idConsumoTarifa = getControladorImovel().recuperarIdConsumoTarifa(idImovel);

		ConsumoTarifa consumoTarifa = new ConsumoTarifa();
		if (idConsumoTarifa != null) {
			consumoTarifa.setId(idConsumoTarifa);
		}

		imovel.setConsumoTarifa(consumoTarifa);
		// ===============================================

		/**
		 *  Refatoração...
		 */
		Collection colecaoQuantidadeEconomias = getControladorImovel().obterColecaoCategoriaOuSubcategoriaDoImovel(imovel);

		// 1.7. consumo fatura de água
		int consumoAgua = 0;
		// 1.7.1. caso a nova situaçao de agua seja ligado ou cortado
		if (idLigacaoAguaSituacaoNova != null
				&& (idLigacaoAguaSituacaoNova.equals(LigacaoAguaSituacao.LIGADO) || idLigacaoAguaSituacaoNova.equals(LigacaoAguaSituacao.CORTADO))) {
			consumoAgua = consumoInformado;
		} else {
			// 1.7.3 caso a nova situação de agua não seja informado e a
			// situação atual da ligação de água seja ligado ou cortado
			if (idLigacaoAguaSituacaoNova == null && idLigacaoAguaSituacao != null
					&& (idLigacaoAguaSituacao.equals(LigacaoAguaSituacao.LIGADO) || idLigacaoAguaSituacao.equals(LigacaoAguaSituacao.CORTADO))) {
				consumoAgua = consumoInformado;
			}
		}

		// 1.8. consumo fatura de esgoto
		int consumoEsgoto = 0;
		// 1.8.1. caso a nova situaçao de esgoto seja ligado ou cortado
		if (idLigacaoEsgotoSituacaoNova != null && idLigacaoEsgotoSituacaoNova.equals(LigacaoEsgotoSituacao.LIGADO)) {
			consumoEsgoto = consumoInformado;
		} else {
			// 1.8.3 caso a nova situação de esgoto não seja informado e a
			// situação atual da ligação de esgoto seja ligado ou cortado
			if (idLigacaoEsgotoSituacaoNova == null && idLigacaoEsgotoSituacao != null && idLigacaoEsgotoSituacao.equals(LigacaoEsgotoSituacao.LIGADO)) {
				consumoEsgoto = consumoInformado;
			}
		}
		// 1.9. consumo mínimo da ligacao
		int volumeObtido = getControladorMicromedicao().obterConsumoMinimoLigacao(imovel, null);

		// 1.10.Data de leitura anterior
		Date dataRealizacao = getControladorFaturamento().pesquisarDataRealizacaoFaturamentoAtividadeCronagrama(idImovel, 1);

		if (dataRealizacao == null) {

			dataRealizacao = getControladorFaturamento().pesquisarDataPrevistaFaturamentoAtividadeCronograma(idImovel, 1);

		}

		/*
		 * Colocado por Raphael Rossiter em 16/01/2007
		 * 
		 * Caso o valor retornado para a data de leitura atual seja nulo, o
		 * valor da mesma será o valor retornado para a data de leitura
		 * anterior. Já a data de leitura anterior será novamente pesquisa,
		 * porém será passado 2 e não 1 a quantidade de meses para retroceder.
		 */
		// 1.11.Data de leitura atual
		Date dataRealizacaoAtual = getControladorFaturamento().pesquisarDataRealizacaoFaturamentoAtividadeCronagrama(idImovel, 0);

		if (dataRealizacaoAtual == null) {

			dataRealizacaoAtual = dataRealizacao;

			dataRealizacao = getControladorFaturamento().pesquisarDataRealizacaoFaturamentoAtividadeCronagrama(idImovel, 2);
		}

		// 1.13.Tarifa do imóvel
		/*
		 * Integer idConsumoTarifa =
		 * getControladorImovel().pesquisarTarifaImovel( idImovel);
		 */

		if (idLigacaoAguaSituacaoNova == null) {
			idLigacaoAguaSituacaoNova = idLigacaoAguaSituacao;
		}
		if (idLigacaoEsgotoSituacaoNova == null) {
			idLigacaoEsgotoSituacaoNova = idLigacaoEsgotoSituacao;
		}

		// 1.12 percentual de esgoto
		BigDecimal percentualEsgoto = getControladorLigacaoEsgoto().recuperarPercentualEsgoto(idImovel);

		if (percentualEsgoto == null) {
			percentualEsgoto = new BigDecimal("100.00");
		}

		/*
		 * CRC3465 autor: Anderson Italo data: 12/01/2010 Adicionado teste para
		 * verificar se a data de realização(dataLeituraAnterior) ou a data de
		 * realização atual(dataLeituraAtual) estão nulas, caso esteja, irá ser
		 * preenchida pela chamada do gerarPeriodoLeituraFaturamento() no
		 * controladorFaturamento.
		 */
		if (dataRealizacao == null || dataRealizacaoAtual == null) {

			FaturamentoGrupo faturamentoGrupo = getControladorImovel().pesquisarGrupoImovel(imovel.getId());

			Date[] dataLeituras = getControladorFaturamento().gerarPeriodoLeituraFaturamento(dataRealizacaoAtual, dataRealizacao, faturamentoGrupo,
					anoMesReferencia, Util.subtrairMesDoAnoMes(anoMesReferencia, 1));

			if (dataRealizacao == null) {
				dataRealizacao = dataLeituras[0];
			}

			if (dataRealizacaoAtual == null) {
				dataRealizacaoAtual = dataLeituras[1];
			}
		}

		// [UC0120] - Calcular Valores de Água e/ou Esgoto
		Collection colecaoCalcularValoresAguaEsgotoHelper = getControladorFaturamento().calcularValoresAguaEsgoto(anoMesReferencia, idLigacaoAguaSituacaoNova,
				idLigacaoEsgotoSituacaoNova, indicadorFatAgua, indicadorFatEsgoto, colecaoQuantidadeEconomias, consumoAgua, consumoEsgoto, volumeObtido,
				dataRealizacao, dataRealizacaoAtual, percentualEsgoto, idConsumoTarifa, null, null);

		BigDecimal valorTotalAgua = new BigDecimal("0.00");
		BigDecimal valorTotalEsgoto = new BigDecimal("0.00");

		// 7.1.3.3 o sistema deve inserir o
		// débito a cobrar para o imóvel
		// Cria o iterator para os valores de água e
		// esgoto
		Iterator iteratorColecaoCalcularValoresAguaEsgotoHelper = colecaoCalcularValoresAguaEsgotoHelper.iterator();

		// Laço para acumular os valores de água e
		// esgoto.
		while (iteratorColecaoCalcularValoresAguaEsgotoHelper.hasNext()) {

			// Recupera o objeto helper que contem
			// os valores de água e
			// esgoto.
			CalcularValoresAguaEsgotoHelper calcularValoresAguaEsgotoHelper = (CalcularValoresAguaEsgotoHelper) iteratorColecaoCalcularValoresAguaEsgotoHelper
					.next();

			/*
			 * Caso tenha valor de água faturado para categoria adiciona o valor
			 * de água ao valor total de água. Caso contrário soma zero.
			 */
			if (calcularValoresAguaEsgotoHelper.getValorFaturadoAguaCategoria() != null) {
				valorTotalAgua = valorTotalAgua.add(calcularValoresAguaEsgotoHelper.getValorFaturadoAguaCategoria());
			} else {
				valorTotalAgua = valorTotalAgua.add(new BigDecimal("0.00"));
			}

			/*
			 * Caso tenha valor de esgoto faturado para categoria adiciona o
			 * valor de esgoto ao valor total de esgoto. Caso contrário soma
			 * zero.
			 */
			if (calcularValoresAguaEsgotoHelper.getValorFaturadoEsgotoCategoria() != null) {
				valorTotalEsgoto = valorTotalEsgoto.add(calcularValoresAguaEsgotoHelper.getValorFaturadoEsgotoCategoria());
			} else {
				valorTotalEsgoto = valorTotalEsgoto.add(new BigDecimal("0.00"));
			}
		}

		// Colocado por Raphael Rossiter em 16/01/2007
		valorTotal = valorTotalAgua.add(valorTotalEsgoto);

		return valorTotal;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * [SB0003] - Calcular/Inserir Valor
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 20/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Short calcularInserirValor(BigDecimal valordebito, Integer idImovel, Integer idFiscalizacaoSituacao, Integer idOS, Integer idDebitoTipo,
			AutosInfracao autosInfracao, Usuario usuarioLogado) throws ControladorException {

		Short gerouDebitoACobrar = 2;
		LigacaoAgua ligacaoAgua = getControladorLigacaoAgua().recuperaParametrosLigacaoAgua(idImovel);
		Integer idLigacaoAguaSituacao = getControladorImovel().pesquisarIdLigacaoAguaSituacao(idImovel);
		int quantidadeMeses = 0;

		if (idLigacaoAguaSituacao != null) {
			// caso a situação de ligação de agua do imóvel seja igual a
			// "Cortado"
			if (idLigacaoAguaSituacao.equals(LigacaoAguaSituacao.CORTADO)) {
				quantidadeMeses = Util.dataDiff(ligacaoAgua.getDataCorte(), new Date());
			}
			if (idLigacaoAguaSituacao.equals(LigacaoAguaSituacao.SUPRIMIDO) || idLigacaoAguaSituacao.equals(LigacaoAguaSituacao.SUPR_PARC)
					|| idLigacaoAguaSituacao.equals(LigacaoAguaSituacao.SUPR_PARC_PEDIDO)) {

				quantidadeMeses = Util.dataDiff(ligacaoAgua.getDataSupressao(), new Date());
			}
		}

		// [FS0003] - Verificar Valor do Débito
		if (valordebito != null && (valordebito.compareTo(new BigDecimal("0.00")) == 1)) {

			Short numeroVezesServicoCalculadoValor = 0;

			if (idFiscalizacaoSituacao != null) {

				Object[] parmsFiscalizacaoSituacaoServicoACobrar = null;

				try {
					parmsFiscalizacaoSituacaoServicoACobrar = repositorioOrdemServico.pesquisarParmsFiscalizacaoSituacaoServicoACobrar(idFiscalizacaoSituacao,
							idDebitoTipo);
				} catch (ErroRepositorioException e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}

				FiltroFiscalizacaoSituacao filtroFiscalizacaoSituacao = new FiltroFiscalizacaoSituacao();
				filtroFiscalizacaoSituacao.adicionarParametro(new ParametroSimples(FiltroFiscalizacaoSituacao.ID, idFiscalizacaoSituacao));
				Collection colecaoSituacoesFiscalizacao = this.getControladorUtil().pesquisar(filtroFiscalizacaoSituacao, FiscalizacaoSituacao.class.getName());

				FiscalizacaoSituacao fiscalizacaoSituacao = (FiscalizacaoSituacao) Util.retonarObjetoDeColecao(colecaoSituacoesFiscalizacao);

				FiltroImovel filtroImovel = new FiltroImovel();

				filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));

				Collection imoveis = this.getControladorUtil().pesquisar(filtroImovel, Imovel.class.getName());

				Imovel imovelPesquisa = (Imovel) Util.retonarObjetoDeColecao(imoveis);

				if (fiscalizacaoSituacao != null && imovelPesquisa != null
						&& fiscalizacaoSituacao.getIndicadorVerificarReincidencia().compareTo(ConstantesSistema.SIM) == 0
						&& imovelPesquisa.getIndicadorReincidenciaInfracao() != null
						&& imovelPesquisa.getIndicadorReincidenciaInfracao().compareTo(ConstantesSistema.SIM.intValue()) == 0) {

					if (parmsFiscalizacaoSituacaoServicoACobrar[2] != null) {
						numeroVezesServicoCalculadoValor = (Short) parmsFiscalizacaoSituacaoServicoACobrar[2];
					}
				} else if (parmsFiscalizacaoSituacaoServicoACobrar != null) {
					// recupera o numero de vezes que o valor foi calculado no
					// serviço
					if (parmsFiscalizacaoSituacaoServicoACobrar[1] != null) {
						numeroVezesServicoCalculadoValor = (Short) parmsFiscalizacaoSituacaoServicoACobrar[1];
					}
				}
			}
			// 3.3.1. Caso a diferença de meses tenha valor diferente de zero e
			// seja menor que o numero de vezes de calculo do valor
			if (quantidadeMeses != 0 && quantidadeMeses < numeroVezesServicoCalculadoValor.intValue()) {
				// valor do debito é multiplicado pela diferença de meses
				valordebito = valordebito.multiply(new BigDecimal(quantidadeMeses));

			} else {
				// 3.3.2. Caso Contrario,a diferença de meses tenha valor
				// diferente de zero e seja maior que o numero de vezes de
				// calculo do valor
				if (quantidadeMeses != 0 && quantidadeMeses > numeroVezesServicoCalculadoValor.intValue()) {
					// valor do debito é multiplicado pelo numero de vezes de
					// calculo do valor
					valordebito = valordebito.multiply(new BigDecimal(numeroVezesServicoCalculadoValor.intValue()));

				} else {
					// 3.3.3. Caso Contrario,a diferença de meses tenha valor
					// igual a zero e o numero de vezes de calculo do valor
					// tenha valor diferente de zero
					if (quantidadeMeses == 0 && numeroVezesServicoCalculadoValor.intValue() != 0) {
						// valor do debito é multiplicado pelo numero de vezes
						// de calculo do valor
						valordebito = valordebito.multiply(new BigDecimal(numeroVezesServicoCalculadoValor.intValue()));

					}
					// 3.3.4. Caso contrário então deixa o valor que está.
				}
			}

			// Alterado para sempre passar a quantidade de parcelas igual a 1
			/*
			 * gerarDebitoOrdemServico(idOS, idDebitoTipo, valordebito,
			 * quantidadeMeses);
			 */

			DebitoACobrar debitoACobrar = gerarDebitoOrdemServico(idOS, idDebitoTipo, valordebito, 1, "100", usuarioLogado);

			// [SB0005] Atualizar Autos de Infração
			this.gerarAutosInfracaoDebitoACobrar(autosInfracao, debitoACobrar);

			if (debitoACobrar != null) {
				// 2. O sistema atualiza a indicação de geração de débito para a
				// situação de fiscalização
				try {
					repositorioOrdemServico.atualizarIndicadorDebitoOS(new Integer(1), idFiscalizacaoSituacao, idOS);
				} catch (ErroRepositorioException e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}

				gerouDebitoACobrar = 1;
			}

		}

		return gerouDebitoACobrar;
	}

	/**
	 * [UC0450] Pesquisar Ordem de Servico verifica o tamanho da consulta
	 * 
	 * [SB001] Selecionar Ordem de Servico por Situação [SB002] Selecionar Ordem
	 * de Servico por Situação da Programação [SB003] Selecionar Ordem de
	 * Servico por Matricula do Imovel [SB004] Selecionar Ordem de Servico por
	 * Codigo do Cliente [SB005] Selecionar Ordem de Servico por Unidade
	 * Superior [SB006] Selecionar Ordem de Servico por Município [SB007]
	 * Selecionar Ordem de Servico por Bairro [SB008] Selecionar Ordem de
	 * Servico por Bairro Logradouro
	 * 
	 * @author Rafael Pinto
	 * @date 18/08/2006
	 * 
	 * @param PesquisarOrdemServicoHelper
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarOrdemServicoTamanho(PesquisarOrdemServicoHelper filtro) throws ControladorException {

		Set colecaoOS = new HashSet();

		try {

			// [SB0004] - Seleciona Ordem Servico por Código de Cliente
			if (filtro.getCodigoCliente() != null) {

				Collection<Integer> colecaoIdOSPorCliente = this.consultarOrdemServicoPorCodigoCliente(filtro.getCodigoCliente(),
						filtro.getDocumentoCobranca(), filtro.getOrigemOrdemServico());

				if (colecaoIdOSPorCliente != null && !colecaoIdOSPorCliente.isEmpty()) {

					if (colecaoOS != null && !colecaoOS.isEmpty()) {
						Collection<Integer> colecaoIdOSRetorno = mergeIDs(colecaoOS, colecaoIdOSPorCliente);

						if (colecaoIdOSRetorno != null && !colecaoIdOSRetorno.isEmpty()) {
							colecaoOS.clear();
							colecaoOS.addAll(colecaoIdOSRetorno);
						} else {
							sessionContext.setRollbackOnly();
							throw new ControladorException("atencao.pesquisa.nenhumresultado");
						}
					} else {
						colecaoOS.addAll(colecaoIdOSPorCliente);
					}
				} else {
					sessionContext.setRollbackOnly();
					throw new ControladorException("atencao.pesquisa.nenhumresultado");
				}
			}

			filtro.setColecaoIdsOS(colecaoOS);

			/*
			 * Adicionado por Victor Cisneiros 19/set/2008
			 * 
			 * Se a pesquisa for por Unidade Superior, pesquisar todas as
			 * unidades filhas dessa Unidade Superior e então pesquisar as
			 * ordens de servico cuja unidade atual está presente nessa colecao
			 * de unidades filhas
			 */
			if (filtro.getUnidadeSuperior() != null && filtro.getIdsUnidadesAtuais() == null) {
				Integer idUnidadeSuperior = filtro.getUnidadeSuperior();

				Collection<UnidadeOrganizacional> unidadesFilhas = getControladorAtendimentoPublico().pesquisarUnidadesFilhas(idUnidadeSuperior).getUnidades()
						.values();

				Collection<Integer> collectionIdsUnidadeAtual = new ArrayList<Integer>();
				for (UnidadeOrganizacional unidade : unidadesFilhas) {
					collectionIdsUnidadeAtual.add(unidade.getId());
				}

				filtro.setIdsUnidadesAtuais(collectionIdsUnidadeAtual);
			}

			return repositorioOrdemServico.pesquisarOrdemServicoTamanho(filtro);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Atualiza os imoveis da OS que refere a RA passada como parametro
	 * 
	 * @author Sávio Luiz
	 * @date 03/01/2007
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public void atualizarOsDaRA(Integer idRA, Integer idImovel) throws ControladorException {
		try {
			repositorioOrdemServico.atualizarOsDaRA(idRA, idImovel);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Raphael Rossiter
	 * @date 25/01/2007
	 * 
	 * @param idOS
	 * @return fiscalizacaoSituacao
	 * @throws ControladorException
	 */
	public void verificarOSJaFiscalizada(Integer idOS) throws ControladorException {

		FiscalizacaoSituacao fiscalizacaoSituacaoOS = null;

		try {

			fiscalizacaoSituacaoOS = repositorioOrdemServico.pesquisarFiscalizacaoSituacaoPorOS(idOS);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (fiscalizacaoSituacaoOS != null) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.os_ja_fiscalizada");
		}

	}

	/**
	 * [UC0539] Manter Prioridade do Tipo de Serviço
	 * 
	 * Remove um ou mais objeto do tipo ServicoTipoPrioridade no BD
	 * 
	 * @author Vivianne Sousa
	 * @date 12/02/2007
	 * @param ids
	 * @return void
	 * @throws ControladorException
	 */
	public void removerPrioridadeTipoServico(String[] ids, Usuario usuarioLogado) throws ControladorException {

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_SERVICO_TIPO_PRIORIDADE_REMOVER);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
		colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		// remover ServicoTipoPrioridade(s)
		this.getControladorUtil().remover(ids, ServicoTipoPrioridade.class.getName(), operacaoEfetuada, colecaoUsuarios);

	}

	/**
	 * [UC0455] Exibir Calendário para Elaboração ou Acompanhamento de Roteiro
	 * 
	 * @author Raphael Rossiter
	 * @date 14/02/2007
	 * 
	 * @param idProgramacaoRoteiro
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer verificarExistenciaOSProgramacao(Integer idProgramacaoRoteiro) throws ControladorException {

		Integer retorno = null;

		try {
			retorno = repositorioOrdemServico.verificarExistenciaOSProgramacao(idProgramacaoRoteiro);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return retorno;
	}

	/**
	 * 
	 * Pesquisar data e equipe da programação da ordem serviço
	 * 
	 * @author Ana Maria
	 * @date 09/03/2007
	 */
	public OrdemServicoProgramacao pesquisarDataEquipeOSProgramacao(Integer idOs) throws ControladorException {

		OrdemServicoProgramacao retorno = null;

		try {
			retorno = repositorioOrdemServico.pesquisarDataEquipeOSProgramacao(idOs);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return retorno;
	}

	/**
	 * [UC0513] Manter Tipo de Crédito
	 * 
	 * Remover Tipo de Crédito
	 * 
	 * @author Thiago Tenório
	 * @date 19/03/2007
	 * 
	 * @param
	 * @throws ControladorException
	 */
	public void removerTipoRetornoOrdemServicoReferida(String[] ids, Usuario usuarioLogado) throws ControladorException {

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_TIPO_RETORNO_OS_REFERIDA_REMOVER);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
		colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		this.getControladorUtil().remover(ids, OsReferidaRetornoTipo.class.getName(), null, null);
	}

	/**
	 * [UC0457] Encerra Ordem de Serviço
	 * 
	 * Método responsável pela integração com o comercial
	 * 
	 * @author Raphael Rossiter
	 * @date 25/04/2007
	 * 
	 * @param idServicoTipo
	 *            , integracaoComercialHelper
	 * @throws ControladorException
	 */
	public void integracaoComercial(Integer idServicoTipo, IntegracaoComercialHelper integracaoComercialHelper, Usuario usuarioLogado)
			throws ControladorException {

		if (idServicoTipo != null && integracaoComercialHelper != null) {

			Integer idOperacao = this.pesquisarServicoTipoOperacao(idServicoTipo);

			if (idOperacao != null) {

				switch (idOperacao) {
				case (Operacao.OPERACAO_LIGACAO_AGUA_EFETUAR_INT):
					getControladorAtendimentoPublico().efetuarLigacaoAgua(integracaoComercialHelper);
					break;
				case (Operacao.OPERACAO_LIGACAO_ESGOTO_EFETUAR_INT):
					getControladorAtendimentoPublico().inserirLigacaoEsgoto(integracaoComercialHelper);
					break;
				case (Operacao.OPERACAO_CORTE_LIGACAO_AGUA_EFETUAR_INT):
					getControladorLigacaoAgua().efetuarCorteLigacaoAgua(integracaoComercialHelper);
					break;
				case (Operacao.OPERACAO_SUPRESSAO_LIGACAO_AGUA_EFETUAR_INT):
					getControladorAtendimentoPublico().efetuarSupressaoLigacaoAgua(integracaoComercialHelper);
					break;
				case (Operacao.OPERACAO_RESTABELECIMENTO_LIGACAO_AGUA_EFETUAR_INT):
					getControladorAtendimentoPublico().efetuarRestabelecimentoLigacaoAgua(integracaoComercialHelper);
					break;
				case (Operacao.OPERACAO_RELIGACAO_AGUA_EFETUAR_INT):
					getControladorAtendimentoPublico().efetuarReligacaoAgua(integracaoComercialHelper);
					break;
				case (Operacao.OPERACAO_CORTE_ADMINISTRATIVO_LIGACAO_AGUA_EFETUAR_INT):
					getControladorLigacaoAgua().efetuarCorteAdministrativoLigacaoAgua(integracaoComercialHelper.getDadosEfetuacaoCorteLigacaoAguaHelper(),
							usuarioLogado);
					break;
				case (Operacao.OPERACAO_RETIRADA_HIDROMETRO_EFETUAR_INT):
					getControladorAtendimentoPublico().efetuarRetiradaHidrometro(integracaoComercialHelper);
					break;
				case (Operacao.OPERACAO_REMANEJAMENTO_HIDROMETRO_EFETUAR_INT):
					getControladorAtendimentoPublico().efetuarRemanejamentoHidrometro(integracaoComercialHelper);
					break;

				case (Operacao.OPERACAO_INSTALACAO_HIDROMETRO_EFETUAR_INT):
					getControladorAtendimentoPublico().efetuarInstalacaoHidrometro(integracaoComercialHelper);
					break;
				case (Operacao.OPERACAO_SUBSTITUICAO_HIDROMETRO_EFETUAR_INT):
					getControladorAtendimentoPublico().efetuarSubstituicaoHidrometro(integracaoComercialHelper);
					break;
				case (Operacao.OPERACAO_MUDANCA_SITUACAO_FATURAMENTO_LIGACAO_ESGOTO_INT):
					getControladorAtendimentoPublico().efetuarMudancaSituacaoFaturamentoLiagacaoEsgoto(integracaoComercialHelper);
					break;
				case (Operacao.OPERACAO_CONSUMO_MINIMO_LIGACAO_AGUA_ATUALIZAR_INT):
					getControladorLigacaoAgua().atualizarConsumoMinimoLigacaoAgua(integracaoComercialHelper);
					break;
				case (Operacao.OPERACAO_VOLUME_MINIMO_LIGACAO_ESGOTO_ATUALIZAR_INT):
					getControladorLigacaoEsgoto().atualizarVolumeMinimoLigacaoEsgoto(integracaoComercialHelper);
					break;
				case (Operacao.OPERACAO_EFETUAR_LIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO_INT):
					getControladorAtendimentoPublico().efetuarLigacaoAguaComInstalacaoHidrometro(integracaoComercialHelper, usuarioLogado);
					break;
				case (Operacao.RESTABELECIMENTO_LIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO_INT):
					getControladorAtendimentoPublico().efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometro(integracaoComercialHelper, usuarioLogado);
					break;
				case (Operacao.OPERACAO_EFETUAR_RELIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO_INT):
					getControladorAtendimentoPublico().efetuarReligacaoAguaComInstalacaoHidrometro(integracaoComercialHelper, usuarioLogado);
					break;
				case (Operacao.OPERACAO_ALTERAR_TIPO_CORTE_INT):

					getControladorLigacaoAgua().atualizarTipoCorte(integracaoComercialHelper);

					break;

				}
			}

		}
	}

	/**
	 * [UC0457] Encerra Ordem de Serviço
	 * 
	 * Pesquisa a operação que faz parte da associação com o SERVICO_TIPO na
	 * tabela SERVICO_TIPO_OPERACAO
	 * 
	 * @author Raphael Rossiter
	 * @date 26/04/2007
	 * 
	 * @param idServicoTipo
	 *            , integracaoComercialHelper
	 * @throws ControladorException
	 */
	public Integer pesquisarServicoTipoOperacao(Integer idServicoTipo) throws ControladorException {

		Integer retorno = null;

		try {
			retorno = repositorioOrdemServico.pesquisarServicoTipoOperacao(idServicoTipo);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return retorno;
	}

	/**
	 * [UC0711] Filtro para Emissao de Ordens Seletivas
	 * 
	 * @author Ivan Sérgio, Raphael Rossiter
	 * @date 08/11/2007, 17/04/2009
	 * 
	 * @param helper
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection filtrarImovelEmissaoOrdensSeletivas(ImovelEmissaoOrdensSeletivasHelper helper) throws ControladorException {

		Collection<Integer> colecaoImoveis = null;
		List colecaoDados = null;
		String anormalidades = "";

		int quantidade = 0;
		int count = 0;
		Integer idImovel = 0;
		Date data = null;
		String dataInstalacaoInicialHidrometro = "";
		String dataInstalacaoFinalHidrometro = "";

		Collection colecaoImoveisPorLocalidade = new ArrayList();

		Integer idLocalidade = 0;
		String desLocalidade = "";

		Integer idSetorComercial = 0;
		String desSetorComercial = "";

		try {

			// Recupera o AnoMesFaturamento de Sistema Parametro
			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();

			// Subtrai 1 mes do ano/mes faturamento para pegar sempre o mes
			// fechado
			String anoMesFaturamento = Util.subtraiAteSeisMesesAnoMesReferencia(sistemaParametro.getAnoMesFaturamento(), 1).toString();

			// Verifica o Tipo de Ordem(Servico)
			if (helper.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_INSTALACAO)) {
				helper.setTipoOrdem("" + ServicoTipo.TIPO_EFETUAR_INSTALACAO_HIDROMETRO);
			} else if (helper.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_SUBSTITUICAO)) {
				helper.setTipoOrdem("" + ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO);
			} else if (helper.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_INSPECAO_ANORMALIDADE)) {
				helper.setTipoOrdem("" + ServicoTipo.TIPO_INSPECAO_ANORMALIDADE);
			} else {
				helper.setTipoOrdem("" + ServicoTipo.TIPO_EFETUAR_REMOCAO_HIDROMETRO);
			}

			// Data Atual - 30 dias para verificar os Imoveis com
			// Hidrometro instalado a mais de 30 dias
			data = Util.subtrairNumeroDiasDeUmaData(new Date(), 30);
			// dataInstalacaoHidrometro = Util.getAno(data) + "-" +
			// Util.getMes(data) + "-" + Util.getDiaMes(data);

			if (helper.getMesAnoInstalacaoInicialHidrometro() != null && !helper.getMesAnoInstalacaoInicialHidrometro().equals("")) {

				if (helper.getMesAnoInstalacaoFinalHidrometro() != null && !helper.getMesAnoInstalacaoFinalHidrometro().equals("")) {

					dataInstalacaoFinalHidrometro = helper.getMesAnoInstalacaoFinalHidrometro();
				} else {
					dataInstalacaoFinalHidrometro = helper.getMesAnoInstalacaoInicialHidrometro();
				}

				dataInstalacaoFinalHidrometro = Util.somaMesAnoMesReferencia(new Integer(dataInstalacaoFinalHidrometro), 1).toString();

				dataInstalacaoFinalHidrometro = Util.formatarDataComTracoAAAAMMDD(Util.converteStringInvertidaSemBarraParaDate(dataInstalacaoFinalHidrometro
						+ "01"));

				dataInstalacaoInicialHidrometro = Util.formatarDataComTracoAAAAMMDD(Util.converteStringInvertidaSemBarraParaDate(helper
						.getMesAnoInstalacaoInicialHidrometro() + "01"));

			} else {
				dataInstalacaoFinalHidrometro = Util.formatarDataComTracoAAAAMMDD(data);
			}

			// Verifica as Anormalidades Selecionadas
			if (helper.getAnormalidadeHidrometro() != null) {
				for (int i = 0; i < helper.getAnormalidadeHidrometro().length; i++) {
					if (!helper.getAnormalidadeHidrometro()[i].trim().equalsIgnoreCase(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

						anormalidades += helper.getAnormalidadeHidrometro()[i].toString() + ",";
					}
				}

				if (!anormalidades.equals("")) {
					// Retira a ultima virgula
					anormalidades = anormalidades.substring(0, anormalidades.length() - 1);
				} else {
					anormalidades = null;
				}

				// Numero de Ocorrencias Consecutivas

			}

			colecaoDados = repositorioOrdemServico.filtrarImovelEmissaoOrdensSeletivas(helper, dataInstalacaoInicialHidrometro, anoMesFaturamento,
					dataInstalacaoFinalHidrometro, sistemaParametro.getCodigoEmpresaFebraban());
			Collection<Integer> colecaoImoveisComAnormalidades = null;

			if (colecaoDados != null && !colecaoDados.isEmpty()) {
				colecaoImoveis = new ArrayList();
				// Verifica a Quantidade Maxima informada pelo usuario
				if (helper.getQuantidadeMaxima() != null && !helper.getQuantidadeMaxima().equals("")) {
					quantidade = Util.converterStringParaInteger(helper.getQuantidadeMaxima()).intValue();
				}

				if (quantidade == 0) {
					quantidade = colecaoDados.size();
				}

				// coleção de imoveis com quantidades de anormalidades
				// informadas
				if (anormalidades != null && !anormalidades.equalsIgnoreCase("") && helper.getNumeroOcorrenciasAnormalidade() != null
						&& !helper.getNumeroOcorrenciasAnormalidade().equalsIgnoreCase("")) {

					int numOcorrencias = Integer.parseInt(helper.getNumeroOcorrenciasAnormalidade());
					Integer anoMesFaturamentoAtual = sistemaParametro.getAnoMesFaturamento();

					colecaoImoveisComAnormalidades = repositorioOrdemServico.pesquisarNumeroDeOcorrenciasConsecultivasAnormalidades(anormalidades,
							String.valueOf(numOcorrencias), anoMesFaturamentoAtual,
							// String.valueOf(Util.subtrairMesDoAnoMes(Integer.parseInt(anoMesFaturamentoAtual),
							// numOcorrencias)),
							helper);

				}

				Iterator iColecaoDados = colecaoDados.iterator();
				Integer totalContasEmDebito = null;
				Object obj = null;
				Object[] retorno = null;

				Map objImoveisPorLocalidadeOuSetor = new HashMap();

				// while ( iColecaoDados.hasNext() & (count < quantidade) ) {
				for (int i = 0; (i < colecaoDados.size()) && (count < quantidade); i++) {
					obj = colecaoDados.get(i);
					iColecaoDados.next();
					if (obj instanceof Object[]) {
						retorno = (Object[]) obj;

						idImovel = (Integer) retorno[0];

						if (idLocalidade == 0 && !sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAERN)) {
							idLocalidade = (Integer) retorno[1];
							desLocalidade = (String) retorno[6];
							idSetorComercial = (Integer) retorno[2];
							desSetorComercial = (String) retorno[7];
						} else if (idLocalidade == 0 && sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAERN)) {
							idLocalidade = (Integer) retorno[1];
							desLocalidade = (String) retorno[5];
							idSetorComercial = (Integer) retorno[2];
							desSetorComercial = (String) retorno[6];
						}

						boolean incluiImovel = true;

						// Intervalo Quantidade de Documentos
						if (incluiImovel) {
							if (helper.getQuantidadeDocumentosInicial() != null && !helper.getQuantidadeDocumentosInicial().equals("")
									&& helper.getQuantidadeDocumentosFinal() != null && !helper.getQuantidadeDocumentosFinal().equals("")) {

								Calendar dataInicio = new GregorianCalendar();
								dataInicio.set(Calendar.YEAR, 1980);
								dataInicio.set(Calendar.MONTH, 0);
								dataInicio.set(Calendar.DAY_OF_MONTH, 1);

								Calendar dataFim = new GregorianCalendar();
								dataFim.set(Calendar.YEAR, 9999);
								dataFim.set(Calendar.MONTH, 11);
								dataFim.set(Calendar.DAY_OF_MONTH, 31);

								ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper = this.getControladorCobranca()
										.obterDebitoImovelOuCliente(1, idImovel.toString(), null, null, "198001", "999912", dataInicio.getTime(),
												dataFim.getTime(), 1, 1, 2, 2, 2, 2, 2, null);

								if (obterDebitoImovelOuClienteHelper.getColecaoContasValores() != null
										&& !obterDebitoImovelOuClienteHelper.getColecaoContasValores().isEmpty()) {

									totalContasEmDebito = obterDebitoImovelOuClienteHelper.getColecaoContasValores().size();

									if ((totalContasEmDebito >= Util.converterStringParaInteger(helper.getQuantidadeDocumentosInicial()))
											&& (totalContasEmDebito <= Util.converterStringParaInteger(helper.getQuantidadeDocumentosFinal()))) {

										// se nao for do tipo instalação,
										// executar o filtro
										// if(!helper.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_INSTALACAO)
										// && colecaoImoveisComAnormalidades !=
										// null){
										// boolean isImovAnormalidade = false;
										//
										// for (Iterator iterator =
										// colecaoImoveisComAnormalidades.iterator();
										// iterator.hasNext();) {
										// Integer idImovAnormalidade =
										// (Integer) iterator.next();
										//
										// if(idImovel == idImovAnormalidade){
										// isImovAnormalidade = true;
										// }
										// }
										//
										// incluiImovel = isImovAnormalidade;
										// }else{
										incluiImovel = true;
										// }

									} else {
										incluiImovel = false;
									}
								} else {
									incluiImovel = false;
								}
							}
						}

						// se nao for do tipo instalação, executar o filtro
						if (!helper.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_INSTALACAO)
								&& colecaoImoveisComAnormalidades != null) {
							boolean isImovAnormalidade = false;

							for (Iterator iterator = colecaoImoveisComAnormalidades.iterator(); iterator.hasNext();) {
								Integer idImovAnormalidade = (Integer) iterator.next();

								if (idImovel.equals(idImovAnormalidade)) {
									isImovAnormalidade = true;
								}
							}

							incluiImovel = isImovAnormalidade;
						} else {
							incluiImovel = true;
						}

						// Verificar Ordem de Servico Pendente e Válida
						if (incluiImovel) {
							boolean existeOSInstalacaoSubstituicaoValida = false;
							existeOSInstalacaoSubstituicaoValida = verificarOrdemServicoInstalacaoSubstituicaoImovel(idImovel);

							if (existeOSInstalacaoSubstituicaoValida) {
								incluiImovel = false;
							}
						}

						// Adiciona os Imoveis por Localidade
						if ((helper.getTipoEmissao() != null && helper.getTipoEmissao().equals("SINTETICO"))
								&& ((helper.getLocalidadeInicial() == null && helper.getLocalidadeFinal() == null))
								|| (helper.getLocalidadeInicial() != null && (!helper.getLocalidadeFinal().equals(helper.getLocalidadeInicial())))) {

							if (!retorno[1].equals(idLocalidade) || !iColecaoDados.hasNext()) {
								objImoveisPorLocalidadeOuSetor.put("idLocalidade", idLocalidade);
								objImoveisPorLocalidadeOuSetor.put("desLocalidade", desLocalidade);
								objImoveisPorLocalidadeOuSetor.put("colecaoImoveis", colecaoImoveis);
								colecaoImoveisPorLocalidade.add(objImoveisPorLocalidadeOuSetor);

								if (iColecaoDados.hasNext()) {
									colecaoImoveis = new ArrayList();
									objImoveisPorLocalidadeOuSetor = new HashMap();
								}

							}

						}// Adiciona os imoveis por Setor
						else if ((helper.getTipoEmissao() != null && helper.getTipoEmissao().equals("SINTETICO"))
								&& (helper.getLocalidadeInicial() != null && (helper.getLocalidadeFinal().equals(helper.getLocalidadeInicial())))) {

							if (!retorno[2].equals(idSetorComercial) || !iColecaoDados.hasNext()) {
								objImoveisPorLocalidadeOuSetor.put("idSetorComercial", idSetorComercial);
								objImoveisPorLocalidadeOuSetor.put("desSetorComercial", desSetorComercial);
								objImoveisPorLocalidadeOuSetor.put("colecaoImoveis", colecaoImoveis);
								colecaoImoveisPorLocalidade.add(objImoveisPorLocalidadeOuSetor);

								if (iColecaoDados.hasNext()) {
									colecaoImoveis = new ArrayList();
									objImoveisPorLocalidadeOuSetor = new HashMap();
								}
							}

						}

						// Adiciona o Id do Imovel na Colecao
						if (incluiImovel) {
							colecaoImoveis.add(idImovel);
							count++;

						}
						if (!sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAERN)) {
							idLocalidade = (Integer) retorno[1];
							desLocalidade = (String) retorno[6];
							idSetorComercial = (Integer) retorno[2];
							desSetorComercial = (String) retorno[7];
						} else {
							idLocalidade = (Integer) retorno[1];
							desLocalidade = (String) retorno[5];
							idSetorComercial = (Integer) retorno[2];
							desSetorComercial = (String) retorno[6];
						}

					}

				}
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (helper.getTipoOrdem().equals("" + ServicoTipo.TIPO_EFETUAR_INSTALACAO_HIDROMETRO)) {
			helper.setTipoOrdem("" + ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_INSTALACAO);
		} else if (helper.getTipoOrdem().equals("" + ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO)) {
			helper.setTipoOrdem(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_SUBSTITUICAO);
			// caso o relatorio passe pelo count mas nao haja Ordens de Serviço
			// pendentes e Válidas
			if (colecaoImoveis == null || colecaoImoveis.size() == 0) {
				throw new ControladorException("atencao.pesquisa.nenhumresultado", null, "");
			}
		} else if (helper.getTipoOrdem().equals("" + ServicoTipo.TIPO_INSPECAO_ANORMALIDADE)) {
			helper.setTipoOrdem(ImovelEmissaoOrdensSeletivasActionForm.TIPO_INSPECAO_ANORMALIDADE);
			// caso o relatorio passe pelo count mas nao haja Ordens de Serviço
			// pendentes e Válidas
			if (colecaoImoveis == null || colecaoImoveis.size() == 0) {
				throw new ControladorException("atencao.pesquisa.nenhumresultado", null, "");
			}
		} else {
			helper.setTipoOrdem(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_REMOCAO);
			// caso o relatorio passe pelo count mas nao haja Ordens de Serviço
			// pendentes e Válidas
			if (colecaoImoveis == null || colecaoImoveis.size() == 0) {
				throw new ControladorException("atencao.pesquisa.nenhumresultado", null, "");
			}
		}

		if (colecaoImoveisPorLocalidade != null && !colecaoImoveisPorLocalidade.isEmpty()) {

			return colecaoImoveisPorLocalidade;
		} else {
			return colecaoImoveis;
		}
	}

	/**
	 * Verifica se já existe Ordem de Servico de Instalacao/Substituicao de
	 * Hidrometro para o Imovel
	 * 
	 * @author Ivan Sérgio
	 * @date 19/11/2007
	 */
	public boolean verificarOrdemServicoInstalacaoSubstituicaoImovel(Integer idImovel) throws ControladorException {
		boolean retorno = false;
		Integer retornoRepositorio = null;
		Date data = null;
		String dataValidade = "";

		try {
			// Data Validade - 90 dias
			data = Util.subtrairNumeroDiasDeUmaData(new Date(), 90);
			// dataValidade = "" + Util.getAno(data);
			//
			// // Adiciona 0 na frente do mes
			// if (Util.getMes(data) < 10) {
			// dataValidade += "0" + Util.getMes(data);
			// }else {
			// dataValidade += Util.getMes(data);
			// }
			//
			// // Adiciona 0 no dia
			// if (Util.getDiaMes(data) < 10) {
			// dataValidade += "0" + Util.getDiaMes(data);
			// }else {
			// dataValidade += Util.getDiaMes(data);
			// }

			retornoRepositorio = repositorioOrdemServico.verificarOrdemServicoInstalacaoSubstituicaoImovel(idImovel, data);

			// Verifica se a consulta retornou resultado
			if (retornoRepositorio != null) {
				retorno = true;
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 * 
	 * [UC0430] - Gerar Ordem de Serviço
	 * 
	 * Método que é chamado pelo [UC0713] Emitir Ordem de Servico Seletiva
	 * 
	 * @author Ivan Sérgio
	 * @date 27/11/2007
	 */
	public Integer gerarOrdemServicoSeletiva(OrdemServico ordemServico, UnidadeOrganizacional unidadeOrganizacional, Imovel imovel, Usuario usuario)
			throws ControladorException {

		Calendar calendar = Calendar.getInstance();

		// [SB0004] - Gerar Ordem de serviço

		ordemServico.setAtendimentoMotivoEncerramento(null);
		ordemServico.setOsReferidaRetornoTipo(null);
		ordemServico.setSituacao(OrdemServico.SITUACAO_PENDENTE);
		ordemServico.setDataGeracao(new Date());
		ordemServico.setDataEmissao(calendar.getTime());
		ordemServico.setDataEncerramento(null);
		ordemServico.setDescricaoParecerEncerramento(null);
		ordemServico.setAreaPavimento(null);
		ordemServico.setIndicadorComercialAtualizado(ConstantesSistema.NAO);
		ordemServico.setServicoNaoCobrancaMotivo(null);
		ordemServico.setPercentualCobranca(null);
		ordemServico.setFiscalizacaoColetiva(null);
		ordemServico.setIndicadorServicoDiagnosticado(ConstantesSistema.NAO);
		ordemServico.setUltimaAlteracao(calendar.getTime());

		ordemServico.setIndicadorEncerramentoAutomatico(ConstantesSistema.NAO);

		OrdemServico ordemServicoDados = null;

		try {

			Integer servicoId = repositorioOrdemServico.recuperaServicoTipoSeletivoPorConstante(ordemServico.getServicoTipo().getId());

			ordemServicoDados = repositorioOrdemServico.pesquisarDadosServicoTipoPrioridade(servicoId);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (ordemServicoDados != null) {
			ordemServico.setValorOriginal(ordemServicoDados.getServicoTipo().getValor());

			ordemServico.setServicoTipoPrioridadeOriginal(ordemServicoDados.getServicoTipo().getServicoTipoPrioridade());

			ordemServico.setServicoTipoPrioridadeAtual(ordemServicoDados.getServicoTipo().getServicoTipoPrioridade());
		}

		// Colocar como ordem de servico não programada - Raphael Rossiter em
		// 08/02/2007
		ordemServico.setIndicadorProgramada(OrdemServico.NAO_PROGRAMADA);
		ordemServico.setIndicadorBoletim(ConstantesSistema.NAO);

		// Sete o imovel
		if (imovel != null) {
			ordemServico.setImovel(imovel);
		}

		ordemServico.setUnidadeAtual(unidadeOrganizacional);

		Integer idOrdemServico = null;

		try {
			idOrdemServico = (Integer) getControladorUtil().inserir(ordemServico);
		} catch (ControladorException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		OrdemServicoUnidade ordemServicoUnidade = new OrdemServicoUnidade();

		ordemServicoUnidade.setOrdemServico(ordemServico);

		// Caso não Exista Documento de Cobranca nem RA, atualizar a unidade
		// com a unidade da tabela com o ID da Empresa
		ordemServicoUnidade.setUnidadeOrganizacional(unidadeOrganizacional);

		ordemServicoUnidade.setUsuario(usuario);
		AtendimentoRelacaoTipo atrt = new AtendimentoRelacaoTipo();
		atrt.setId(AtendimentoRelacaoTipo.ABRIR_REGISTRAR);
		ordemServicoUnidade.setAtendimentoRelacaoTipo(atrt);
		ordemServicoUnidade.setUltimaAlteracao(calendar.getTime());

		try {
			getControladorUtil().inserir(ordemServicoUnidade);
		} catch (ControladorException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return idOrdemServico;
	}

	/**
	 * 
	 * [UC0732] - Gerar Relatorio Acompanhamento de Ordens de Servico Hidrometro
	 * 
	 * @author Ivan Sérgio
	 * @date 27/11/2007, 27/03/2008
	 * @alteracao: Adicionado Motivo Encerramento; Setor Comercial;
	 * 
	 * @param tipoOrdem
	 * @param situacaoOrdemServico
	 * @param idLocalidade
	 * @param dataEncerramentoInicial
	 * @param dataEncerramentoFinal
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarOrdemServicoGerarRelatorioAcompanhamentoHidrometro(String idEmpresa, String tipoOrdem, String situacaoOrdemServico,
			String idLocalidadeInicial, String idLocalidadeFinal, String dataEncerramentoInicial, String dataEncerramentoFinal, String idMotivoEncerramento,
			String idSetorComercialInicial, String idSetorComercialFinal, String codigoQuadraInicial, String codigoQuadraFinal, String codigoRotaInicial,
			String codigoRotaFinal, String sequenciaRotaInicial, String sequenciaRotaFinal) throws ControladorException {

		Collection colecaoDados = null;
		Collection<RelacaoOrdensServicoEncerradasPendentesHelper> retorno = new ArrayList();
		Short situacaoOrdem = null;

		// Verifica o Tipo da Ordem(Instalacao ou Substituicao)
		if (tipoOrdem.equalsIgnoreCase("INSTALACAO")) {
			tipoOrdem = "" + ServicoTipo.TIPO_EFETUAR_INSTALACAO_HIDROMETRO;
		} else if (tipoOrdem.equalsIgnoreCase("SUBSTITUICAO")) {
			tipoOrdem = "" + ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO;
		} else if (tipoOrdem.equalsIgnoreCase("REMOCAO")) {
			tipoOrdem = "" + ServicoTipo.TIPO_EFETUAR_REMOCAO_HIDROMETRO;
		} else if (tipoOrdem.equalsIgnoreCase("INSPECAO DE ANORMALIDADE")) {
			tipoOrdem = "" + ServicoTipo.TIPO_INSPECAO_ANORMALIDADE;
		} else if (tipoOrdem.equalsIgnoreCase("SUBSTITUICAO COM REMOCAO")) {
			tipoOrdem = "" + ServicoTipo.TIPO_SUBSTITUICAO_COM_REMOCAO;
		}

		// Verifica a Situacao da Ordem(Encerradas ou Pendentes)
		if (situacaoOrdemServico.equalsIgnoreCase("ENCERRADAS")) {
			situacaoOrdem = OrdemServico.SITUACAO_ENCERRADO;
		} else if (situacaoOrdemServico.equalsIgnoreCase("PENDENTES")) {
			situacaoOrdem = OrdemServico.SITUACAO_PENDENTE;
		}

		try {
			colecaoDados = repositorioOrdemServico
					.pesquisarOrdemServicoGerarRelatorioAcompanhamentoHidrometro(idEmpresa, tipoOrdem, situacaoOrdem, idLocalidadeInicial, idLocalidadeFinal,
							dataEncerramentoInicial, dataEncerramentoFinal, idMotivoEncerramento, idSetorComercialInicial, idSetorComercialFinal,
							codigoQuadraInicial, codigoQuadraFinal, codigoRotaInicial, codigoRotaFinal, sequenciaRotaInicial, sequenciaRotaFinal);

		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoDados != null && !colecaoDados.isEmpty()) {
			Iterator iColecaoDados = colecaoDados.iterator();
			Object[] dados = null;

			RelacaoOrdensServicoEncerradasPendentesHelper Helper = null;

			while (iColecaoDados.hasNext()) {
				Helper = new RelacaoOrdensServicoEncerradasPendentesHelper();
				dados = (Object[]) iColecaoDados.next();

				// Id Ordem Servico
				Helper.setIdOrdemServico((Integer) dados[0]);
				// Id Imovel
				Helper.setIdImovel((Integer) dados[1]);
				// Id Localidade
				Helper.setIdLocalidade((Integer) dados[2]);
				// Nome Localidade
				Helper.setNomeLocalidade((String) dados[3]);
				// Codigo Setor Comercial
				Helper.setCodigoSetorComercial((Integer) dados[4]);
				// Numero Quadra
				Helper.setNumeroQuadra((Integer) dados[5]);
				// Lote
				Helper.setLote((Short) dados[6]);
				// SubLote
				Helper.setSubLote((Short) dados[7]);
				// Data Geracao
				String dataGeracao = dados[8].toString();
				dataGeracao = dataGeracao.substring(8, 10) + "/" + dataGeracao.substring(5, 7) + "/" + dataGeracao.substring(0, 4);
				Helper.setDataGeracao(dataGeracao);
				// Data Encerramento
				String dataEncerramento = "";
				if (dados[9] != null) {
					dataEncerramento = dados[9].toString();
					dataEncerramento = dataEncerramento.substring(8, 10) + "/" + dataEncerramento.substring(5, 7) + "/" + dataEncerramento.substring(0, 4);
				}
				Helper.setDataEncerramento(dataEncerramento);
				// Motivo Encerramento
				String motivoEncerramento = "";
				if (dados[10] != null) {
					motivoEncerramento = (String) dados[10];
				}
				Helper.setMotivoEncerramento(motivoEncerramento);
				// Id Setor Comercial
				Helper.setIdSetorComercial((Integer) dados[11]);
				// Nome Setor Comercial
				Helper.setNomeSetorComercial((String) dados[12]);
				// Id Motivo Encerramento
				Helper.setIdMotivoEncerramento((Integer) dados[13]);

				// Adiciona na Colecao
				retorno.add(Helper);
			}
		}

		return retorno;
	}

	/**
	 * [UC0640] Gerar TXT Ação de Cobrança
	 * 
	 * @author Raphael Rossiter
	 * @date 04/01/2008
	 * 
	 * @param idCobrancaDocumento
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarOrdemServicoPorCobrancaDocumento(Integer idCobrancaDocumento) throws ControladorException {

		Integer retorno = null;

		try {
			retorno = repositorioOrdemServico.pesquisarOrdemServicoPorCobrancaDocumento(idCobrancaDocumento);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return retorno;
	}

	// /**
	// * Este caso de uso permite a exportação de ordem de serviço das
	// * prestadoras.
	// *
	// * [UC0720] Exportar Ordem de Serviço Prestadoras
	// *
	// * @author Pedro Alexandre
	// * @date 21/12/2007
	// *
	// * @param colecaoTramite
	// * @return Retorna uma coleçaõ de ordem de serviço de prestadoras
	// * @throws ControladorException

	// */
	// public Collection<OrdemServico>
	// exportarOrdemServicoPrestadoras(Collection colecaoTramite) throws
	// ControladorException {
	// Collection<OrdemServico> retorno = new ArrayList();
	// Collection colecaoDadosOrdemServico = null;
	//
	// try {
	// //caso a coleção de tramites não esteja nula
	// if(colecaoTramite != null && !colecaoTramite.isEmpty()){
	//
	// // Para cada tramite pesquisa as ordem de serviço da RA do tramite
	// Iterator iteratorTramite = colecaoTramite.iterator();
	//
	// while(iteratorTramite.hasNext()){
	//
	// Tramite tramite = (Tramite) iteratorTramite.next();
	//
	// Integer idRegistroAtendimento = tramite.getRegistroAtendimento().getId();
	// //Pesquisa a coleção de OS da RA
	// colecaoDadosOrdemServico =
	// this.repositorioOrdemServico.pesquisarDadosParaExportarOrdemServicoPrestadora(idRegistroAtendimento);
	//
	// //para cada OS exporta os dados
	// if(colecaoDadosOrdemServico != null &&
	// !colecaoDadosOrdemServico.isEmpty()){
	// Iterator iteratorDadosOS = colecaoDadosOrdemServico.iterator();
	//
	// while (iteratorDadosOS.hasNext()) {
	// /*
	// * 0 - id da ordem de serviço
	// * 1 - data da geração da ordem de serviço
	// * 2 - nº de horas prevista para o tipo de serviço
	// * 3 - id do tipo de serviço
	// * 4 - id imovel
	// * 5 - nome do bairro
	// * 6 - descricao da localidade
	// * 7 - id do elo
	// * 8 - cídigo do setor comercial
	// * 9 - nº da quadra
	// * 10 - observação da ordem de serviço
	// * 11 - id do serviço tipo prioridade atual
	// * 12 - indicador de pavimento de rua
	// * 13 - indicador de pavimento de calçada
	// * 14 - ponto de referência do registro de atendimento
	// * 15 - descrição do tipo de serviço
	// * 16 - tipo de pavimento de rua
	// * 17 - tipo de pavimento de calçada
	// */
	// Object[] dadosOS = (Object[]) iteratorDadosOS.next();
	//
	// ///Cria a ordemde serviço
	// OrdemServico ordemServico = new OrdemServico();
	// Integer idOS = (Integer)dadosOS[0];
	// ordemServico.setId(idOS);
	//
	// //Recupera as informações da OS necessárias para a exportação
	// Date dataGeracaoOS = (Date) dadosOS[1];
	// UnidadeOrganizacional unidadeOrganizacionalDestino =
	// tramite.getUnidadeOrganizacionalDestino();
	// String loginUsuario = null;
	// UnidadeOrganizacional unidadeOrganizacionalOrigem =
	// tramite.getUnidadeOrganizacionalOrigem();
	// Date dataTramite = tramite.getDataTramite();
	// Short numeroHorasPrevisaoExecucao = (Short) dadosOS[2];
	// String parecerTramite = tramite.getParecerTramite();
	// //VERIFICAR ISSO COM FABIOLA 08/01/2008
	// if(parecerTramite == null){
	// parecerTramite = "";
	// }
	//
	// //Recupera os indicadores de paviemnto de rua e calçada
	// Short indicadorPavimentoRua = (Short) dadosOS[12];
	// Short indicadorPavimentoCalcada = (Short) dadosOS[13];
	//
	// //Recupera as informações do tipo de serviço
	// Integer idServicoTipo = (Integer) dadosOS[3];
	// String descricaoTipoServico = (String) dadosOS[15];
	// ServicoTipo servicoTipo = new ServicoTipo();
	// servicoTipo.setId(idServicoTipo);
	// servicoTipo.setDescricao(descricaoTipoServico);
	// servicoTipo.setIndicadorPavimentoRua(indicadorPavimentoRua);
	// servicoTipo.setIndicadorPavimentoCalcada(indicadorPavimentoCalcada);
	// ordemServico.setServicoTipo(servicoTipo);
	//
	// //Caso o imóvel tenha pavimento de rua
	// Integer idPavimentoRua = (Integer) dadosOS[16];
	// PavimentoRua pavimentoRua = null;
	// if(idPavimentoRua != null){
	// pavimentoRua = new PavimentoRua();
	// pavimentoRua.setId(idPavimentoRua);
	// }
	//
	// //Caso o imóvel tenha pavimento de calçada
	// Integer idPavimentoCalcada = (Integer) dadosOS[17];
	// PavimentoCalcada pavimentoCalcada = null;
	// if(idPavimentoCalcada != null){
	// pavimentoCalcada = new PavimentoCalcada();
	// pavimentoCalcada.setId(idPavimentoCalcada);
	// }
	//
	// Integer idImovel = (Integer) dadosOS[4];
	// Imovel imovel = null;
	// String inscricaoImovel = null;
	// //Caso a ordem de serviço possua um imóvel, pesquisa a inscrição do
	// imóvel.
	// if(idImovel != null){
	// imovel = new Imovel();
	// imovel.setId(idImovel);
	// inscricaoImovel =
	// this.getControladorImovel().pesquisarInscricaoImovel(idImovel);
	// imovel.setPavimentoRua(pavimentoRua);
	// imovel.setPavimentoCalcada(pavimentoCalcada);
	// }
	// //Seta o imóvel na OS
	// ordemServico.setImovel(imovel);
	//
	// //Recupera os dados da localização do imóvel
	// String nomeBairro = (String) dadosOS[5];
	// String descricaoLocalidade = (String) dadosOS[6];
	// Integer idElo = (Integer) dadosOS[7];
	// Integer codigoSetorComercial = (Integer) dadosOS[8];
	// Integer numeroQuadra = (Integer) dadosOS[9];
	// String observacaoOS = (String) dadosOS[10];
	// String pontoReferenciaRA = (String) dadosOS[14];
	//
	// //Recupera a prioridade atual do serviço
	// Integer idPrioridadeAtualServico = (Integer) dadosOS[11];
	// ServicoTipoPrioridade servicoTipoPrioridadeAtual = null;
	// if(idPrioridadeAtualServico != null){
	// servicoTipoPrioridadeAtual = new ServicoTipoPrioridade();
	// servicoTipoPrioridadeAtual.setId(idPrioridadeAtualServico);
	// }
	//
	// //Calcula a data prevista de execução
	// Calendar data = new GregorianCalendar();
	// data.setTime(dataGeracaoOS);
	// if(numeroHorasPrevisaoExecucao != null){
	// data.add(Calendar.HOUR_OF_DAY,numeroHorasPrevisaoExecucao);
	// }
	// Date dataPrevistaExecucao = data.getTime();
	//
	//
	// //Recupera os dados do solicitanteda RA
	// String nomeSolicitanteRA =
	// this.getControladorRegistroAtendimento().obterNomeSolicitanteRA(idRegistroAtendimento);
	// String telefoneSolicitante =
	// this.getControladorRegistroAtendimento().obterTelefoneSolicitanteRA(idRegistroAtendimento);
	// String enderecoOcorrenciaRA =
	// this.getControladorRegistroAtendimento().obterEnderecoOcorrenciaRA(idRegistroAtendimento);
	//
	//
	// //Inseri o movimento da ordem de serviço
	// OrdemServicoMovimento ordemServicoMovimento = new
	// OrdemServicoMovimento();
	// ordemServicoMovimento.setId(idOS);
	// ordemServicoMovimento.setDataGeracao(dataGeracaoOS);
	// ordemServicoMovimento.setUnidadeOrganizacionalExecutora(unidadeOrganizacionalDestino);
	// ordemServicoMovimento.setLoginUsuario(loginUsuario);
	// ordemServicoMovimento.setUnidadeOrganizacionalCentralizadora(unidadeOrganizacionalOrigem);
	// ordemServicoMovimento.setDataTramite(dataTramite);
	// ordemServicoMovimento.setDataExecucao(dataPrevistaExecucao);
	// ordemServicoMovimento.setServicoTipo(servicoTipo);
	// ordemServicoMovimento.setRegistroAtendimento(tramite.getRegistroAtendimento());
	// ordemServicoMovimento.setImovel(imovel);
	// ordemServicoMovimento.setInscricaoImovel(inscricaoImovel);
	// ordemServicoMovimento.setNomeSolicitante(nomeSolicitanteRA);
	// ordemServicoMovimento.setTelefoneSolicitante(telefoneSolicitante);
	// ordemServicoMovimento.setEndereco(enderecoOcorrenciaRA);
	// ordemServicoMovimento.setPontoReferencia(pontoReferenciaRA);
	// ordemServicoMovimento.setBairro(nomeBairro);
	// ordemServicoMovimento.setNomeLocalidade(descricaoLocalidade);
	// ordemServicoMovimento.setCodigoElo(idElo );
	// ordemServicoMovimento.setCodigoSetor(codigoSetorComercial);
	// ordemServicoMovimento.setNumeroQuadra(numeroQuadra);
	// ordemServicoMovimento.setObservacao(observacaoOS);
	// ordemServicoMovimento.setServicoTipoPrioridadeAtual(servicoTipoPrioridadeAtual);
	// ordemServicoMovimento.setParecer(parecerTramite);
	// ordemServicoMovimento.setPavimentoRua(null);
	// ordemServicoMovimento.setAreaPavimentoRua(null);
	// ordemServicoMovimento.setPavimentoCalcada(null);
	// ordemServicoMovimento.setAreaPavimentoCalcada(null);
	// ordemServicoMovimento.setIndicadorMovimento(ConstantesSistema.SIM);
	//
	// this.getControladorUtil().inserir(ordemServicoMovimento);
	//
	// //assinala ordem de serviço para encerramento automático
	// this.atualizarIndicadorEncerramentoAutomaticoOS(ConstantesSistema.SIM,
	// idOS);
	//
	// //Caso tenha indicador de pavimento de rua ou calçada
	// //adiciona a ordem de serviço a coleção de retorno.
	// if(indicadorPavimentoRua.equals(ConstantesSistema.SIM) ||
	// indicadorPavimentoCalcada.equals(ConstantesSistema.SIM)){
	// retorno.add(ordemServico);
	// }
	// }
	// }
	// }
	// }
	// } catch (ErroRepositorioException e) {
	// sessionContext.setRollbackOnly();
	// throw new ControladorException("erro.sistema", e);
	// }
	//
	// return retorno;
	// }

	// /////////////////////////////////////////////////////////////////////////////////

	/**
	 * Este caso de uso permite a exportação de ordem de serviço das
	 * prestadoras.
	 * 
	 * [UC0720] Exportar Ordem de Serviço Prestadoras
	 * 
	 * @author Pedro Alexandre, Ivan Sergio
	 * @date 21/12/2007, 29/09/2009
	 * 
	 * @param colecaoTramite
	 * @return Retorna uma coleçaõ de ordem de serviço de prestadoras
	 * @throws ControladorException
	 */
	public Map exportarOrdemServicoPrestadoras(Collection colecaoTramite) throws ControladorException {
		Collection<OrdemServico> colecaoOrdemServico = new ArrayList();
		Collection colecaoDadosOrdemServico = null;
		Collection colecaoOrdemServicoMovimento = null;
		Map retorno = new HashMap();

		try {
			// caso a coleção de tramites não esteja nula
			if (colecaoTramite != null && !colecaoTramite.isEmpty()) {

				// Para cada tramite pesquisa as ordem de serviço da RA do
				// tramite
				Iterator iteratorTramite = colecaoTramite.iterator();
				colecaoOrdemServicoMovimento = new ArrayList();

				while (iteratorTramite.hasNext()) {

					Tramite tramite = (Tramite) iteratorTramite.next();

					Integer idRegistroAtendimento = tramite.getRegistroAtendimento().getId();
					// Pesquisa a coleção de OS da RA
					colecaoDadosOrdemServico = this.repositorioOrdemServico.pesquisarDadosParaExportarOrdemServicoPrestadora(idRegistroAtendimento);

					// para cada OS exporta os dados
					if (colecaoDadosOrdemServico != null && !colecaoDadosOrdemServico.isEmpty()) {
						Iterator iteratorDadosOS = colecaoDadosOrdemServico.iterator();

						while (iteratorDadosOS.hasNext()) {
							/*
							 * 0 - id da ordem de serviço 1 - data da geração da
							 * ordem de serviço 2 - nº de horas prevista para o
							 * tipo de serviço 3 - id do tipo de serviço 4 - id
							 * imovel 5 - nome do bairro 6 - descricao da
							 * localidade 7 - id do elo 8 - cídigo do setor
							 * comercial 9 - nº da quadra 10 - observação da
							 * ordem de serviço 11 - id do serviço tipo
							 * prioridade atual 12 - indicador de pavimento de
							 * rua 13 - indicador de pavimento de calçada 14 -
							 * ponto de referência do registro de atendimento 15
							 * - descrição do tipo de serviço 16 - tipo de
							 * pavimento de rua 17 - tipo de pavimento de
							 * calçada
							 * 
							 * 18 - numero coordenada Norte 19 - numero
							 * coordenada Leste
							 */
							Object[] dadosOS = (Object[]) iteratorDadosOS.next();

							// /Cria a ordemde serviço
							OrdemServico ordemServico = new OrdemServico();
							Integer idOS = (Integer) dadosOS[0];
							ordemServico.setId(idOS);

							// Recupera as informações da OS necessárias para a
							// exportação
							Date dataGeracaoOS = (Date) dadosOS[1];
							UnidadeOrganizacional unidadeOrganizacionalDestino = tramite.getUnidadeOrganizacionalDestino();
							String loginUsuario = null;
							UnidadeOrganizacional unidadeOrganizacionalOrigem = tramite.getUnidadeOrganizacionalOrigem();
							Date dataTramite = tramite.getDataTramite();
							Short numeroHorasPrevisaoExecucao = (Short) dadosOS[2];
							String parecerTramite = tramite.getParecerTramite();
							// VERIFICAR ISSO COM FABIOLA 08/01/2008
							if (parecerTramite == null) {
								parecerTramite = "";
							}

							// Recupera os indicadores de paviemnto de rua e
							// calçada
							Short indicadorPavimentoRua = (Short) dadosOS[12];
							Short indicadorPavimentoCalcada = (Short) dadosOS[13];

							// Recupera as informações do tipo de serviço
							Integer idServicoTipo = (Integer) dadosOS[3];
							String descricaoTipoServico = (String) dadosOS[15];
							ServicoTipo servicoTipo = new ServicoTipo();
							servicoTipo.setId(idServicoTipo);
							servicoTipo.setDescricao(descricaoTipoServico);
							servicoTipo.setIndicadorPavimentoRua(indicadorPavimentoRua);
							servicoTipo.setIndicadorPavimentoCalcada(indicadorPavimentoCalcada);
							ordemServico.setServicoTipo(servicoTipo);

							// Caso o imóvel tenha pavimento de rua
							Integer idPavimentoRua = (Integer) dadosOS[16];
							PavimentoRua pavimentoRua = null;
							if (idPavimentoRua != null) {
								pavimentoRua = new PavimentoRua();
								pavimentoRua.setId(idPavimentoRua);
							}

							// Caso o imóvel tenha pavimento de calçada
							Integer idPavimentoCalcada = (Integer) dadosOS[17];
							PavimentoCalcada pavimentoCalcada = null;
							if (idPavimentoCalcada != null) {
								pavimentoCalcada = new PavimentoCalcada();
								pavimentoCalcada.setId(idPavimentoCalcada);
							}

							Integer idImovel = (Integer) dadosOS[4];
							Imovel imovel = null;
							String inscricaoImovel = null;
							// Caso a ordem de serviço possua um imóvel,
							// pesquisa a inscrição do imóvel.
							if (idImovel != null) {
								imovel = new Imovel();
								imovel.setId(idImovel);
								inscricaoImovel = this.getControladorImovel().pesquisarInscricaoImovel(idImovel);
								imovel.setPavimentoRua(pavimentoRua);
								imovel.setPavimentoCalcada(pavimentoCalcada);
							}
							// Seta o imóvel na OS
							ordemServico.setImovel(imovel);

							// Recupera os dados da localização do imóvel
							String nomeBairro = (String) dadosOS[5];
							String descricaoLocalidade = (String) dadosOS[6];
							Integer idElo = (Integer) dadosOS[7];
							Integer codigoSetorComercial = (Integer) dadosOS[8];
							Integer numeroQuadra = (Integer) dadosOS[9];
							String observacaoOS = (String) dadosOS[10];
							String pontoReferenciaRA = (String) dadosOS[14];

							// Recupera a prioridade atual do serviço
							Integer idPrioridadeAtualServico = (Integer) dadosOS[11];
							ServicoTipoPrioridade servicoTipoPrioridadeAtual = null;
							if (idPrioridadeAtualServico != null) {
								servicoTipoPrioridadeAtual = new ServicoTipoPrioridade();
								servicoTipoPrioridadeAtual.setId(idPrioridadeAtualServico);
							}

							// Calcula a data prevista de execução
							Calendar data = new GregorianCalendar();
							data.setTime(dataTramite);
							if (numeroHorasPrevisaoExecucao != null) {
								data.add(Calendar.HOUR_OF_DAY, numeroHorasPrevisaoExecucao);
							}
							Date dataPrevistaExecucao = data.getTime();

							// Recupera os dados do solicitanteda RA
							String nomeSolicitanteRA = this.getControladorRegistroAtendimento().obterNomeSolicitanteRA(idRegistroAtendimento);
							String telefoneSolicitante = this.getControladorRegistroAtendimento().obterTelefoneSolicitanteRA(idRegistroAtendimento);

							String enderecoOcorrenciaRA = this.getControladorRegistroAtendimento().obterEnderecoOcorrenciaRA(idRegistroAtendimento);
							enderecoOcorrenciaRA = Util.completaString(enderecoOcorrenciaRA, 100);

							// Inseri o movimento da ordem de serviço
							OrdemServicoMovimento ordemServicoMovimento = new OrdemServicoMovimento();
							ordemServicoMovimento.setId(idOS);
							ordemServicoMovimento.setDataGeracao(dataGeracaoOS);
							ordemServicoMovimento.setUnidadeOrganizacionalExecutora(unidadeOrganizacionalDestino);
							ordemServicoMovimento.setLoginUsuario(loginUsuario);
							ordemServicoMovimento.setUnidadeOrganizacionalCentralizadora(unidadeOrganizacionalOrigem);
							ordemServicoMovimento.setDataTramite(dataTramite);
							ordemServicoMovimento.setDataExecucao(dataPrevistaExecucao);
							ordemServicoMovimento.setServicoTipo(servicoTipo);
							ordemServicoMovimento.setRegistroAtendimento(tramite.getRegistroAtendimento());
							ordemServicoMovimento.setImovel(imovel);
							ordemServicoMovimento.setInscricaoImovel(inscricaoImovel);
							ordemServicoMovimento.setNomeSolicitante(nomeSolicitanteRA);
							ordemServicoMovimento.setTelefoneSolicitante(telefoneSolicitante);
							ordemServicoMovimento.setEndereco(enderecoOcorrenciaRA);
							ordemServicoMovimento.setPontoReferencia(pontoReferenciaRA);
							ordemServicoMovimento.setBairro(nomeBairro);
							ordemServicoMovimento.setNomeLocalidade(descricaoLocalidade);
							ordemServicoMovimento.setCodigoElo(idElo);
							ordemServicoMovimento.setCodigoSetor(codigoSetorComercial);
							ordemServicoMovimento.setNumeroQuadra(numeroQuadra);
							ordemServicoMovimento.setObservacao(observacaoOS);
							ordemServicoMovimento.setServicoTipoPrioridadeAtual(servicoTipoPrioridadeAtual);
							ordemServicoMovimento.setParecer(parecerTramite);
							ordemServicoMovimento.setPavimentoRua(null);
							ordemServicoMovimento.setAreaPavimentoRua(null);
							ordemServicoMovimento.setPavimentoCalcada(null);
							ordemServicoMovimento.setAreaPavimentoCalcada(null);
							ordemServicoMovimento.setIndicadorMovimento(ConstantesSistema.SIM);

							if (dadosOS[18] != null && !dadosOS[18].equals("")) {
								ordemServicoMovimento.setNnCoordenadaNorte((BigDecimal) dadosOS[18]);
							} else {
								ordemServicoMovimento.setNnCoordenadaNorte(new BigDecimal(0));
							}

							if (dadosOS[19] != null && !dadosOS[19].equals("")) {
								ordemServicoMovimento.setNnCoordenadaLeste((BigDecimal) dadosOS[19]);
							} else {
								ordemServicoMovimento.setNnCoordenadaLeste(new BigDecimal(0));
							}

							colecaoOrdemServicoMovimento.add(ordemServicoMovimento);
							// this.getControladorUtil().inserir(ordemServicoMovimento);

							// assinala ordem de serviço para encerramento
							// automático
							this.atualizarIndicadorEncerramentoAutomaticoOS(ConstantesSistema.SIM, idOS);

							// Caso tenha indicador de pavimento de rua ou
							// calçada
							// adiciona a ordem de serviço a coleção de retorno.
							if (indicadorPavimentoRua.equals(ConstantesSistema.SIM) || indicadorPavimentoCalcada.equals(ConstantesSistema.SIM)) {
								colecaoOrdemServico.add(ordemServico);
							}
						}
					}
				}
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		retorno.put("colecaoOrdemServico", colecaoOrdemServico);
		retorno.put("colecaoOrdemServicoMovimento", colecaoOrdemServicoMovimento);

		return retorno;
	}

	// ////////////////////////////////////////////////////////////////////

	/**
	 * [UC0720] - Exportar Ordem de Serviço Prestadoras
	 * 
	 * [SB0002] - Gerar ocorrência na tabela OrdemServicoPavimento
	 * 
	 * @author Pedro Alexandre
	 * @date 07/01/2008
	 * 
	 * @param ordemServicoPavimento
	 * @return
	 * @throws ControladorException
	 */
	public Integer inserirOrdemServicoPavimento(OrdemServicoPavimento ordemServicoPavimento) throws ControladorException {

		Integer retorno = null;

		try {
			// inserir o pavimento da OS
			retorno = (Integer) this.getControladorUtil().inserir(ordemServicoPavimento);

			// recupera o movimento da OS
			OrdemServicoMovimento ordemServicoMovimento = this.repositorioOrdemServico.pesquisarOrdemServicoMovimento(ordemServicoPavimento.getOrdemServico()
					.getId());

			// atualiza os dados do pavimento no movimento da OS
			ordemServicoMovimento.setPavimentoRua(ordemServicoPavimento.getPavimentoRua());
			ordemServicoMovimento.setAreaPavimentoRua(ordemServicoPavimento.getAreaPavimentoRua());
			ordemServicoMovimento.setPavimentoCalcada(ordemServicoPavimento.getPavimentoCalcada());
			ordemServicoMovimento.setAreaPavimentoCalcada(ordemServicoPavimento.getAreaPavimentoCalcada());
			this.getControladorUtil().atualizar(ordemServicoMovimento);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 * [UC0734] Encerrar Ordem Servico Vencida
	 * 
	 * @author Ivan Sérgio
	 * @date 14/01/2008
	 * 
	 * @param idServicoTipo
	 * @param quantidadeDias
	 * @return totalOrdemServicoEncerradas
	 * @throws ControladorException
	 */
	/**
	 * Inclusão da Origem da OS e do UsuarioLogado na
	 * busca para encerrar as mesmas quando vencidas.
	 * 
	 * @author Wellington Rocha
	 * @date 18/01/2012
	 */
	public Integer encerrarOrdemServicoVencida(Integer idServicoTipo, Integer quantidadeDias, Usuario usuarioLogado) throws ControladorException {

		Integer totalOrdemServicoEncerradas = 0;
		List listaOrdemServico = null;
		String dataVencimento = "";
		Object obj = null;
		Object[] dados = null;

		// Subtrai a Quantidade de Dias a Data Atual + 90 dias que é o limite de
		// validade
		Date data = Util.subtrairNumeroDiasDeUmaData(new Date(), (quantidadeDias.intValue() + 90));
		dataVencimento = Util.getAno(data) + "-";
		// Adiciona "0" ao mes se necessario
		if (Util.getMes(data) < 10) {
			dataVencimento += "0" + Util.getMes(data) + "-";
		} else {
			dataVencimento += Util.getMes(data) + "-";
		}
		// Adiciona "0" ao dia se necessario
		if (Util.getDiaMes(data) < 10) {
			dataVencimento += "0" + Util.getDiaMes(data);
		} else {
			dataVencimento += Util.getDiaMes(data);
		}

		/**
		 * Inclusão da Origem da OS na busca para
		 * encerrar as mesmas quando vencidas.
		 * 
		 * @author Wellington Rocha
		 * @date 18/01/2012
		 */
		try {
			/*
			 * Alterações em testes!!! listaOrdemServico =
			 * repositorioOrdemServico.pesquisarOrdemServicoVencida(
			 * idServicoTipo, codigoOrigemOS, dataVencimento);
			 */
			listaOrdemServico = repositorioOrdemServico.pesquisarOrdemServicoVencida(idServicoTipo, dataVencimento);

			if (listaOrdemServico != null & !listaOrdemServico.isEmpty()) {
				totalOrdemServicoEncerradas = listaOrdemServico.size();

				for (int i = 0; i < listaOrdemServico.size(); i++) {
					obj = listaOrdemServico.get(i);

					// Encerra a Ordem de Servico Selecionada
					if (obj instanceof Object[]) {
						dados = (Object[]) obj;

						Integer idOrdemServico = (Integer) dados[0];
						Integer idUnidade = (Integer) dados[1];

						/******************************************************************
						 * Atualiza a Ordem de Servico
						 ******************************************************************/
						OrdemServico ordemServico = new OrdemServico();

						AtendimentoMotivoEncerramento motivoEncerramento = new AtendimentoMotivoEncerramento();
						motivoEncerramento.setId(new Integer(AtendimentoMotivoEncerramento.CANCELADO_POR_DERCURSO_DE_PRAZO));

						ordemServico.setId(idOrdemServico); // orse_id
						ordemServico.setAtendimentoMotivoEncerramento(motivoEncerramento); // amen_id
						ordemServico.setSituacao(OrdemServico.SITUACAO_ENCERRADO); // orse_cdsituacao
						ordemServico.setDataEncerramento(new Date()); // orse_tmencerramento
						ordemServico.setDescricaoParecerEncerramento(null); // orse_dsparecerencerramento
						ordemServico.setUltimaAlteracao(new Date()); // orse_tmultimaalteracao

						this.repositorioOrdemServico.alterarOrdemServicoVencida(ordemServico);

						// Atualizar os documentos de cobranca associados as OS
						// para serem usados no
						// Resumo de Acoes de cobranca ...
						// 'Esta ordem de servico vencida nao tem documento de
						// cobranca' - Francisco/Ivan
						// getControladorCobranca().atualizarCobrancaDocumentoAposEncerrarOS(ordemServico);

						// Usuario user = new Usuario();
						// user.setId(new Integer(0));
						/******************************************************************/

						/******************************************************************
						 * Insere em Ordem Servico Unidade
						 ******************************************************************/
						OrdemServicoUnidade ordemServicoUnidade = new OrdemServicoUnidade();

						UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
						unidadeOrganizacional.setId(idUnidade);

						Usuario usuario = new Usuario();
						usuario.setId(0);

						AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
						atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);

						ordemServicoUnidade.setUnidadeOrganizacional(unidadeOrganizacional); // unid_id
						/**
						 * Correção de erro na
						 * execução da funcionalidade
						 * 
						 * @author Wellington Rocha
						 * @date 19/11/2012
						 */
						ordemServicoUnidade.setUsuario(usuarioLogado); // usur_id
						ordemServicoUnidade.setOrdemServico(ordemServico); // orse_id
						ordemServicoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo); // attp_id
						ordemServicoUnidade.setUltimaAlteracao(new Date()); // osun_tmultimaalteracao

						this.getControladorUtil().inserir(ordemServicoUnidade);
						/******************************************************************/
					}
				}
			}

			return totalOrdemServicoEncerradas;

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0479] Gerar Débito da Ordem de Serviço
	 * 
	 * [FS0002] Verificar Existência do Tipo de Débito [FS0003] Validar Valor do
	 * Débito [FS0004] Validar Quantidade de Parcelas
	 * 
	 * @author Raphael Rossiter
	 * @date 10/03/2008
	 * 
	 * @param ordemServicoId
	 * 
	 * @throws ControladorException
	 */
	public DebitoTipo validacaoGerarDebito(Integer idDebitoTipo, BigDecimal valorDebito, int qtdeParcelas) throws ControladorException {

		// [FS0002] Verificar Existência do Tipo de Débito
		// -----------------------------------------------------------------------------------
		FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();

		filtroDebitoTipo.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
		filtroDebitoTipo.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");

		filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.ID, idDebitoTipo));

		// Recupera Tipo do débito
		Collection colecaoDebitoTipo = this.getControladorUtil().pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());

		DebitoTipo debitoTipo = null;
		if (colecaoDebitoTipo != null && !colecaoDebitoTipo.isEmpty()) {
			debitoTipo = (DebitoTipo) Util.retonarObjetoDeColecao(colecaoDebitoTipo);
		} else {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.tipo_debito_inexistente");
		}
		// -----------------------------------------------------------------------------------

		// [FS0003] Validar Valor do Débito
		if (valorDebito.intValue() <= 0) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.valor_debito_invalido");
		}

		// [FS0004] Validar Quantidade de Parcelas
		if (qtdeParcelas <= 0) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.qtde_parcelas_invalida");
		}

		return debitoTipo;
	}

	/***
	 * [FS0753] Manter Ordem de Servico Concluida
	 * 
	 * @author Ivan Sérgio
	 * @date 26/03/2008
	 * 
	 * @param idOrdemServico
	 * @return
	 * @throws ControladorException
	 */
	public ManterOrdemServicoConcluidaHelper pesquisarOrdemServicoConcluida(Integer idOrdemServico) throws ControladorException {
		ManterOrdemServicoConcluidaHelper helper = null;

		try {
			Collection colecaoDados = repositorioOrdemServico.pesquisarOrdemServicoConcluida(idOrdemServico);

			if (colecaoDados != null && !colecaoDados.isEmpty()) {
				Iterator iColecaoDados = colecaoDados.iterator();
				Object[] dados = (Object[]) iColecaoDados.next();

				helper = new ManterOrdemServicoConcluidaHelper();

				helper.setIdOrdemServico((Integer) dados[0]);
				helper.setDataEmissao((Date) dados[1]);
				helper.setDataEncerramento((Date) dados[2]);
				helper.setIdImovel((Integer) dados[3]);
				helper.setCodigoFiscalizacao((Short) dados[4]);
				helper.setDataFiscalizacao1((Date) dados[5]);
				helper.setDataFiscalizacao2((Date) dados[6]);
				helper.setDataFiscalizacao3((Date) dados[7]);
				helper.setIdUsuario((Integer) dados[8]);
				helper.setIndicadorTrocaProtecao((Short) dados[9]);
				helper.setIndicadorTrocaRegistro((Short) dados[10]);
				helper.setDescricaoHidrometroLocalInstalacao((String) dados[11]);
				helper.setDataEncerramentoBoletim((Date) dados[12]);
				helper.setDataUltimaAlteracao((Date) dados[13]);
			}

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return helper;
	}

	/**
	 * [UC0720] Exportar Ordem de Serviço Prestadoras
	 * 
	 * @author Vivianne Sousa
	 * @date 02/04/2008
	 * 
	 * @throws ControladorException
	 */
	public void atualizarIndicadorEncerramentoAutomaticoOS(Short indicadorEncerramentoAutomatico, Integer idOrdemServico) throws ControladorException {

		try {
			repositorioOrdemServico.atualizarIndicadorEncerramentoAutomaticoOS(indicadorEncerramentoAutomatico, idOrdemServico);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * [UC0732] Gerar Relatorio Acompanhamento de Oredem de Servico de
	 * Hidrometro RELATORIO: Relacao das Ordens de Servico Concluidas
	 * 
	 * @author Ivan Sérgio
	 * @date 04/04/2008
	 * 
	 * @param tipoOrdem
	 * @param situacaoOrdem
	 * @param idLocalidade
	 * @param dataEncerramentoInicial
	 * @param dataEncerramentoFinal
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemServicoConcluidaGerarRelatorioAcompanhamentoHidrometro(String idEmpresa, String tipoOrdem, String situacaoOrdemServico,
			String idLocalidadeInicial, String idLocalidadeFinal, String dataEncerramentoInicial, String dataEncerramentoFinal, String idMotivoEncerramento,
			String idSetorComercialInicial, String idSetorComercialFinal, String codigoQuadraInicial, String codigoQuadraFinal, String codigoRotaInicial,
			String codigoRotaFinal, String sequenciaRotaInicial, String sequenciaRotaFinal) throws ControladorException {

		Collection colecaoDados = null;
		Collection<RelacaoOrdensServicoConcluidasHelper> retorno = new ArrayList();
		Short situacaoOrdem = null;

		// Verifica o Tipo da Ordem(Instalacao ou Substituicao)
		if (tipoOrdem.equalsIgnoreCase("INSTALACAO")) {
			tipoOrdem = String.valueOf(ServicoTipo.TIPO_EFETUAR_INSTALACAO_HIDROMETRO);
		} else if (tipoOrdem.equalsIgnoreCase("SUBSTITUICAO")) {
			tipoOrdem = String.valueOf(ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO);
		} else if (tipoOrdem.equalsIgnoreCase("REMOCAO")) {
			tipoOrdem = String.valueOf(ServicoTipo.TIPO_EFETUAR_REMOCAO_HIDROMETRO);
		} else if (tipoOrdem.equalsIgnoreCase("INSPECAO DE ANORMALIDADE")) {
			tipoOrdem = String.valueOf(ServicoTipo.TIPO_INSPECAO_ANORMALIDADE);
		} else if (tipoOrdem.equalsIgnoreCase("SUBSTITUICAO COM REMOCAO")) {
			tipoOrdem = String.valueOf(ServicoTipo.TIPO_SUBSTITUICAO_COM_REMOCAO);
		}

		situacaoOrdem = OrdemServico.SITUACAO_ENCERRADO;

		try {
			colecaoDados = repositorioOrdemServico
					.pesquisarOrdemServicoConcluidaGerarRelatorioAcompanhamentoHidrometro(idEmpresa, tipoOrdem, situacaoOrdem, idLocalidadeInicial,
							idLocalidadeFinal, dataEncerramentoInicial, dataEncerramentoFinal, idMotivoEncerramento, idSetorComercialInicial,
							idSetorComercialFinal, codigoQuadraInicial, codigoQuadraFinal, codigoRotaInicial, codigoRotaFinal, sequenciaRotaInicial,
							sequenciaRotaFinal);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoDados != null && !colecaoDados.isEmpty()) {
			Iterator iColecaoDados = colecaoDados.iterator();
			Object[] dados = null;

			RelacaoOrdensServicoConcluidasHelper helper = null;

			while (iColecaoDados.hasNext()) {
				helper = new RelacaoOrdensServicoConcluidasHelper();
				dados = (Object[]) iColecaoDados.next();

				// Id Ordem Servico
				helper.setIdOrdemServico((Integer) dados[0]);
				// Id Imovel
				helper.setIdImovel((Integer) dados[1]);
				// Id Localidade
				helper.setIdLocalidade((Integer) dados[2]);
				// Codigo Setor Comercial
				helper.setCodigoSetorComercial((Integer) dados[3]);
				// Numero Quadra
				helper.setNumeroQuadra((Integer) dados[4]);
				// Lote
				helper.setLote((Short) dados[5]);
				// SubLote
				helper.setSubLote((Short) dados[6]);
				// Data Geracao
				String dataGeracao = dados[7].toString();
				dataGeracao = dataGeracao.substring(8, 10) + "/" + dataGeracao.substring(5, 7) + "/" + dataGeracao.substring(0, 4);
				helper.setDataGeracao(dataGeracao);
				// Data Encerramento
				String dataEncerramento = "";
				if (dados[8] != null) {
					dataEncerramento = dados[8].toString();
					dataEncerramento = dataEncerramento.substring(8, 10) + "/" + dataEncerramento.substring(5, 7) + "/" + dataEncerramento.substring(0, 4);
				}
				helper.setDataEncerramento(dataEncerramento);
				// Motivo Encerramento
				String motivoEncerramento = "";
				if (dados[9] != null) {
					motivoEncerramento = (String) dados[9];
				}
				helper.setMotivoEncerramento(motivoEncerramento);
				// Id Setor Comercial
				helper.setIdSetorComercial((Integer) dados[10]);
				// Id Motivo Encerramento
				helper.setIdMotivoEncerramento((Integer) dados[11]);
				// ****************************************************************
				// Local de Instalacao do Hidrometro
				// ****************************************************************
				Integer idLocalInstalacao = null;

				idLocalInstalacao = (Integer) dados[12];
				if (idLocalInstalacao != null) {
					// Id do Local de Instalacao do Hidrometro
					helper.setIdLocalInstalacaoHidrometro(idLocalInstalacao);
					// Descricao do Local de Instalacao do Hidrometro
					helper.setDescricaoLocalInstalacaoHidrometro((String) dados[13]);
				} else {
					idLocalInstalacao = 9;
					FiltroHidrometroLocalInstalacao filtro = new FiltroHidrometroLocalInstalacao();
					filtro.adicionarParametro(new ParametroSimples(FiltroHidrometroLocalInstalacao.ID, idLocalInstalacao));

					Collection colecaoDadosLocalInstalacao = getControladorUtil().pesquisar(filtro, HidrometroLocalInstalacao.class.getName());

					if (colecaoDadosLocalInstalacao != null && !colecaoDadosLocalInstalacao.isEmpty()) {
						Iterator iColecaoDadosLocalInstalacao = colecaoDadosLocalInstalacao.iterator();

						HidrometroLocalInstalacao localInstalacao = (HidrometroLocalInstalacao) iColecaoDadosLocalInstalacao.next();

						// Id do Local de Instalacao do Hidrometro
						helper.setIdLocalInstalacaoHidrometro(localInstalacao.getId());
						// Descricao do Local de Instalacao do Hidrometro
						helper.setDescricaoLocalInstalacaoHidrometro(localInstalacao.getDescricao());
					}
				}

				helper.setNomeLocalidade((String) dados[14]);
				// ****************************************************************

				// Adiciona na Colecao
				retorno.add(helper);
			}
		}

		return retorno;
	}

	/**
	 * [UC0753] Manter Ordem Servico Concluida
	 * 
	 * @author Ivan Sérgio
	 * @date 09/04/2008
	 * 
	 * @param idOrdemServico
	 * @param situacaoFiscalizacao
	 * @param dataFiscalizacao1
	 * @param dataFiscalizacao2
	 * @param dataFiscalizacao3
	 * @param idFuncionario
	 * @throws ControladorException
	 */
	public void atualizarDadosFiscalizacao(Integer idOrdemServico, Short situacaoFiscalizacao, String dataFiscalizacao1, String dataFiscalizacao2,
			String dataFiscalizacao3, Integer idFuncionario, Short situacaoFiscalizacaoAnterior) throws ControladorException {

		try {
			// ***************************************************************
			// Verifica se o usuario voltou a situacao do Boletim para
			// Nao Fiscalizado. Caso isso aconteca, excluir os dados da
			// fiscalizacao do Boletim.
			// ***************************************************************
			// if (situacaoFiscalizacaoAnterior != 0 && situacaoFiscalizacao ==
			// 0) {
			// Exclui as datas da Fiscalizacao
			// repositorioOrdemServico.excluirDatasFiscalizacaoBoletimOSConcluida(idOrdemServico);
			// }

			// ***************************************************************
			// Realiza a Alteracao na tabela de Boletim
			// ***************************************************************
			Date data1 = null;
			if (dataFiscalizacao1 != null) {
				if (!dataFiscalizacao1.equals("")) {
					data1 = Util.converteStringParaDate(dataFiscalizacao1);
				}
			}

			repositorioOrdemServico.atualizarBoletimOSConcluida(idOrdemServico, situacaoFiscalizacao, data1, idFuncionario);

			// ***************************************************************
			// Insere ou Atualiza a tabela Data Fiscalizacao OS Seletiva
			// ***************************************************************

			// @@@ Exclui as datas da Fiscalizacao
			repositorioOrdemServico.excluirDatasFiscalizacaoBoletimOSConcluida(idOrdemServico);

			if (dataFiscalizacao2 != null || dataFiscalizacao3 != null) {
				if (!dataFiscalizacao2.equals("") || !dataFiscalizacao3.equals("")) {

					Date data2 = null;
					if (dataFiscalizacao2 != null) {
						if (!dataFiscalizacao2.equals("")) {
							data2 = Util.converteStringParaDate(dataFiscalizacao2);
						}
					}

					Date data3 = null;
					if (dataFiscalizacao3 != null) {
						if (!dataFiscalizacao3.equals("")) {
							data3 = Util.converteStringParaDate(dataFiscalizacao3);
						}
					}

					DataFiscalizacaoOsSeletiva fiscalizacao = new DataFiscalizacaoOsSeletiva();
					fiscalizacao.setId(idOrdemServico);
					fiscalizacao.setDataFiscalizacaoOrdemServico2(data2);
					fiscalizacao.setDataFiscalizacaoOrdemServico3(data3);

					// Verifica se Insere ou Atualiza
					// FiltroDataFiscalizacaoOSSeletiva filtroFiscalizacao = new
					// FiltroDataFiscalizacaoOSSeletiva();
					// filtroFiscalizacao.adicionarParametro(new
					// ParametroSimples(
					// FiltroDataFiscalizacaoOSSeletiva.ID, idOrdemServico));

					// Collection dadosFiscalizacao =
					// getControladorUtil().pesquisar(
					// filtroFiscalizacao,
					// DataFiscalizacaoOsSeletiva.class.getName());

					// if (dadosFiscalizacao.isEmpty()) {
					// Insere
					getControladorUtil().inserir(fiscalizacao);
					// }else {
					// Atualiza
					// getControladorUtil().atualizar(fiscalizacao);
					// }
				}
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0765] Gerar Boletim Ordens de Servico Concluidas
	 * 
	 * @author Ivan Sérgio
	 * @date 29/04/2008
	 * 
	 * @param idEmpresa
	 * @param idLocalidade
	 * @param anoMesReferenciaBoletim
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarResumoOrdensServicoConcluidas(Integer idEmpresa, Integer idLocalidade, String anoMesReferenciaBoletim)
			throws ControladorException {

		Collection colecaoDados = null;
		Collection<GerarBoletimOrdensServicoConcluidasHelper> retorno = new ArrayList();

		try {
			colecaoDados = repositorioOrdemServico.pesquisarResumoOrdensServicoConcluidas(idEmpresa, idLocalidade, anoMesReferenciaBoletim);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoDados != null && !colecaoDados.isEmpty()) {
			Iterator iColecaoDados = colecaoDados.iterator();
			Object[] dados = null;

			GerarBoletimOrdensServicoConcluidasHelper Helper = null;

			while (iColecaoDados.hasNext()) {
				Helper = new GerarBoletimOrdensServicoConcluidasHelper();
				dados = (Object[]) iColecaoDados.next();

				// Ano/Mes Referencia Boletim
				Helper.setAnoMesReferenciaBoletim(dados[0].toString());
				// Id Ordem Servico
				Helper.setIdOrdemServico((Integer) dados[1]);
				// Codigo da Fiscalizacao
				Helper.setCodigoFiscalizacao((Short) dados[2]);
				// Indicador de Troca de Protecao
				Helper.setIndicadorTrocaProtecao((Short) dados[3]);
				// Indicador de Troca de Registro
				Helper.setIndicadorTrocaRegistro((Short) dados[4]);
				// Id Local Instalacao do Hidrometro
				Helper.setIdLocalInstalacaoHidrometro((Integer) dados[5]);
				// Id do Tipo de Servico
				Helper.setIdTipoServico((Integer) dados[6]);
				// Data de Fiscalizacao1
				Helper.setDataFiscalizacao1((Date) dados[7]);
				// Data de Fiscalizacao2
				Helper.setDataFiscalizacao2((Date) dados[8]);
				// Data de Fiscalizacao3
				Helper.setDataFiscalizacao3((Date) dados[9]);

				retorno.add(Helper);
			}
		}

		return retorno;
	}

	/**
	 * [UC0765] Gerar Boletim Ordens de Servico Concluidas
	 * 
	 * @author Ivan Sérgio
	 * @date 02/05/2008
	 * 
	 * @param colecaoIdOrdemServico
	 * @throws ControladorException
	 */
	public void encerrarBoletimOrdemServicoConcluida(Collection colecaoIdOrdemServico) throws ControladorException {

		try {
			if (colecaoIdOrdemServico != null && !colecaoIdOrdemServico.isEmpty()) {
				Integer idOrdemServico = null;
				Iterator iColecaoIdOrdemServico = colecaoIdOrdemServico.iterator();

				while (iColecaoIdOrdemServico.hasNext()) {
					idOrdemServico = (Integer) iColecaoIdOrdemServico.next();

					repositorioOrdemServico.encerrarBoletimOrdemServicoConcluida(idOrdemServico);
				}
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Pesquisa Ordens em Processo de Repavimentação
	 * 
	 * [UC0639] Filtrar Ordens em Processo de Repavimetação.
	 * 
	 * @author Yara Taciane
	 * @date 02/06/2008
	 * 
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemProcessoRepavimentacao(OSPavimentoHelper oSPavimentoHelper, Integer numeroPagina) throws ControladorException {
		Collection collHelper = new ArrayList();

		try {
			Collection collOrdemServicoPavimento = repositorioOrdemServico.pesquisarOrdemProcessoRepavimentacao(oSPavimentoHelper, numeroPagina);

			Iterator it = collOrdemServicoPavimento.iterator();
			OSPavimentoRetornoHelper osPavimentoRetornoHelper = null;

			while (it.hasNext()) {
				OrdemServicoPavimento ordemServicoPavimento = (OrdemServicoPavimento) it.next();
				if (ordemServicoPavimento.getOrdemServico() != null) {

					osPavimentoRetornoHelper = new OSPavimentoRetornoHelper();
					Imovel imovel = ordemServicoPavimento.getOrdemServico().getImovel();
					if (imovel != null) {
						String[] endereco = getControladorEndereco().pesquisarEnderecoFormatadoDividido(imovel.getId());
						osPavimentoRetornoHelper.setEndereco(endereco[3] + "-" + endereco[0]);
					} else {
						RegistroAtendimento ra = ordemServicoPavimento.getOrdemServico().getRegistroAtendimento();

						String endereco = getControladorEndereco().pesquisarEnderecoRegistroAtendimentoFormatadoIniciadoPeloBairro(ra.getId());
						osPavimentoRetornoHelper.setEndereco(endereco);

					}
					osPavimentoRetornoHelper.setOrdemServicoPavimento(ordemServicoPavimento);
					osPavimentoRetornoHelper.setOrdemServico(ordemServicoPavimento.getOrdemServico());

					if (ordemServicoPavimento.getMotivoRejeicao() != null) {

						StringBuilder hint1 = new StringBuilder();
						hint1.append("Motivo da Rejeição: ");

						if (ordemServicoPavimento.getMotivoRejeicao().getDescricao() == null) {
							hint1.append("-");
						} else {
							hint1.append(ordemServicoPavimento.getMotivoRejeicao().getDescricao());
						}

						hint1.append(" <br> Usuário que efetuou a Rejeição: ");

						if (ordemServicoPavimento.getUsuarioRejeicao() == null) {
							hint1.append("-");
						} else {
							hint1.append(ordemServicoPavimento.getUsuarioRejeicao().getNomeUsuario());
						}

						hint1.append(" <br> Descrição da Rejeição: ");

						if (ordemServicoPavimento.getDescricaoRejeicao() == null) {
							hint1.append("-");
						} else {
							hint1.append(ordemServicoPavimento.getDescricaoRejeicao());
						}

						hint1.append("<br>");

						osPavimentoRetornoHelper.setHint1(hint1.toString());
					} else {
						osPavimentoRetornoHelper.setHint1(null);
					}

					collHelper.add(osPavimentoRetornoHelper);
				}
			}
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return collHelper;
	}

	/**
	 * Pesquisa Ordens em Processo de Repavimentação
	 * 
	 * [UC0639] Filtrar Ordens em Processo de Repavimetação.
	 * 
	 * @author Yara Taciane, Hugo Leonardo
	 * @date 02/06/2008, 13/12/2010
	 * 
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioOrdemProcessoRepavimentacao(OSPavimentoHelper oSPavimentoHelper, String escolhaRelatorio) throws ControladorException {

		Collection collHelper = new ArrayList();

		try {

			Collection collOrdemServicoPavimento = repositorioOrdemServico.pesquisarOrdemProcessoRepavimentacao(oSPavimentoHelper, null);

			/**
			 * Alterado por Arthur Carvalho data 25 de fevereiro de 2010
			 * 
			 * Anteriormente no sub-relatorio do relatorio divergente aparecia
			 * todos os tipos de metragem como no relatorio completo, o que foi
			 * que eu fiz, criei duas novas colecoes que vai mandar somente as
			 * ordens de servico pavimento com divergencia. Uma colecao leva os
			 * dados de pavimento rua e a outra de pavimento rua retorno.
			 */
			// RELATORIO DIVERGENTE
			if (escolhaRelatorio != null && escolhaRelatorio.equals("2")) {

				Collection<OSTipoPavimentoHelper> colecaoTipoPavimentoRuaDivergente = new ArrayList();
				Collection<OSTipoPavimentoHelper> colecaoTipoPavimentoRuaRetDivergente = new ArrayList();

				OSPavimentoRetornoHelper osPavimentoRetornoHelper = null;
				if (collOrdemServicoPavimento != null) {
					Iterator iteratorCollOrdemServicoPavimento = collOrdemServicoPavimento.iterator();
					while (iteratorCollOrdemServicoPavimento.hasNext()) {

						OSTipoPavimentoHelper helperRua = new OSTipoPavimentoHelper();
						OSTipoPavimentoHelper helperRuaRet = new OSTipoPavimentoHelper();

						osPavimentoRetornoHelper = new OSPavimentoRetornoHelper();

						OrdemServicoPavimento ospv = (OrdemServicoPavimento) iteratorCollOrdemServicoPavimento.next();

						// Verifica se o pavimento e a metragem sao divergentes
						// caso seja inclui na colecao.
						if ((ospv.getPavimentoRua() != null && ospv.getAreaPavimentoRua() != null && ospv.getPavimentoRuaRetorno() != null && ospv
								.getAreaPavimentoRuaRetorno() != null)
								&& ((ospv.getPavimentoRua().getId().intValue() != ospv.getPavimentoRuaRetorno().getId().intValue()) || (!ospv
										.getAreaPavimentoRua().toString().equals(ospv.getAreaPavimentoRuaRetorno().toString())))
								&& ospv.getMotivoRejeicao() == null) {
							/**
							 * Seta os parametros usados no subrelatorio.
							 */

							Imovel imovel = ospv.getOrdemServico().getImovel();

							if (imovel != null) {
								String[] endereco = getControladorEndereco().pesquisarEnderecoFormatadoDividido(imovel.getId());
								osPavimentoRetornoHelper.setEndereco(endereco[3] + "-" + endereco[0]);
							} else {
								RegistroAtendimento ra = ospv.getOrdemServico().getRegistroAtendimento();
								String endereco = getControladorEndereco().pesquisarEnderecoRegistroAtendimentoFormatadoIniciadoPeloBairro(ra.getId());
								osPavimentoRetornoHelper.setEndereco(endereco);
							}
							// Pavimento Rua -- trata para que so apareca um
							// tipo de pavimento
							// com o total da area de todos os pavimentos
							// daquele tipo
							boolean inseriu = true;
							if (colecaoTipoPavimentoRuaDivergente != null) {
								Iterator iterator = colecaoTipoPavimentoRuaDivergente.iterator();
								while (iterator.hasNext()) {
									OSTipoPavimentoHelper helperRuaDaColecao = (OSTipoPavimentoHelper) iterator.next();

									if (helperRuaDaColecao.getId().intValue() == ospv.getPavimentoRua().getId().intValue()) {

										helperRuaDaColecao.setSomatorioArea(Util.somaBigDecimal(helperRuaDaColecao.getSomatorioArea(),
												ospv.getAreaPavimentoRua()));

										inseriu = false;
										break;
									}

								}
								if (inseriu) {
									helperRua.setId(ospv.getPavimentoRua().getId());
									helperRua.setDescricao(ospv.getPavimentoRua().getDescricao());
									helperRua.setSomatorioArea(ospv.getAreaPavimentoRua());

									colecaoTipoPavimentoRuaDivergente.add(helperRua);
								}
							} else {
								helperRua.setId(ospv.getPavimentoRua().getId());
								helperRua.setDescricao(ospv.getPavimentoRua().getDescricao());
								helperRua.setSomatorioArea(ospv.getAreaPavimentoRua());

								colecaoTipoPavimentoRuaDivergente.add(helperRua);
							}

							// Pavimento rua retorno -- trata para que so
							// apareca um tipo de pavimento de retorno
							// com o total da area de todos os pavimentos de
							// retorno daquele tipo
							boolean inseriuRetorno = true;
							if (colecaoTipoPavimentoRuaRetDivergente != null) {
								Iterator iteratorRetorno = colecaoTipoPavimentoRuaRetDivergente.iterator();
								while (iteratorRetorno.hasNext()) {
									OSTipoPavimentoHelper helperRuaRetornoDaColecao = (OSTipoPavimentoHelper) iteratorRetorno.next();

									if (helperRuaRetornoDaColecao.getId().intValue() == ospv.getPavimentoRuaRetorno().getId().intValue()) {

										helperRuaRetornoDaColecao.setSomatorioArea(Util.somaBigDecimal(helperRuaRetornoDaColecao.getSomatorioArea(),
												ospv.getAreaPavimentoRuaRetorno()));
										inseriuRetorno = false;
										break;
									}
								}
								if (inseriuRetorno) {
									helperRuaRet.setId(ospv.getPavimentoRuaRetorno().getId());
									helperRuaRet.setDescricao(ospv.getPavimentoRuaRetorno().getDescricao());
									helperRuaRet.setSomatorioArea(ospv.getAreaPavimentoRuaRetorno());

									colecaoTipoPavimentoRuaRetDivergente.add(helperRuaRet);
								}
							} else {
								helperRuaRet.setId(ospv.getPavimentoRuaRetorno().getId());
								helperRuaRet.setDescricao(ospv.getPavimentoRuaRetorno().getDescricao());
								helperRuaRet.setSomatorioArea(ospv.getAreaPavimentoRuaRetorno());

								colecaoTipoPavimentoRuaRetDivergente.add(helperRuaRet);
							}

							osPavimentoRetornoHelper.setOrdemServicoPavimento(ospv);
							osPavimentoRetornoHelper.setOrdemServico(ospv.getOrdemServico());
							osPavimentoRetornoHelper.setCollOSTipoPavimentoHelper_Rua(colecaoTipoPavimentoRuaDivergente);
							osPavimentoRetornoHelper.setCollOSTipoPavimentoHelper_RuaRet(colecaoTipoPavimentoRuaRetDivergente);

							collHelper.add(osPavimentoRetornoHelper);
						}

					}

				}

				// RELATORIO COMPLETO
			} else if (escolhaRelatorio != null && escolhaRelatorio.equals("1")) {

				// Feito apenas pra RUA.
				Collection<OSTipoPavimentoHelper> collTipoPavimentoRua = pesquisarOrdemProcessoRepavimentacaoPorTipoPavimentoRua(oSPavimentoHelper);
				Collection<OSTipoPavimentoHelper> collTipoPavimentoRuaRet = pesquisarOrdemProcessoRepavimentacaoPorTipoPavimentoRuaRet(oSPavimentoHelper);

				Iterator it = collOrdemServicoPavimento.iterator();
				OSPavimentoRetornoHelper osPavimentoRetornoHelper = null;

				while (it.hasNext()) {
					OrdemServicoPavimento ordemServicoPavimento = (OrdemServicoPavimento) it.next();
					if (ordemServicoPavimento.getOrdemServico() != null) {
						osPavimentoRetornoHelper = new OSPavimentoRetornoHelper();
						Imovel imovel = ordemServicoPavimento.getOrdemServico().getImovel();

						if (imovel != null) {
							String[] endereco = getControladorEndereco().pesquisarEnderecoFormatadoDividido(imovel.getId());
							osPavimentoRetornoHelper.setEndereco(endereco[3] + "-" + endereco[0]);
						} else {
							RegistroAtendimento ra = ordemServicoPavimento.getOrdemServico().getRegistroAtendimento();

							String endereco = getControladorEndereco().pesquisarEnderecoRegistroAtendimentoFormatadoIniciadoPeloBairro(ra.getId());
							osPavimentoRetornoHelper.setEndereco(endereco);
						}

						osPavimentoRetornoHelper.setOrdemServicoPavimento(ordemServicoPavimento);
						osPavimentoRetornoHelper.setOrdemServico(ordemServicoPavimento.getOrdemServico());
						osPavimentoRetornoHelper.setCollOSTipoPavimentoHelper_Rua(collTipoPavimentoRua);
						osPavimentoRetornoHelper.setCollOSTipoPavimentoHelper_RuaRet(collTipoPavimentoRuaRet);

						collHelper.add(osPavimentoRetornoHelper);
					}
				}
				// RELATORIO CONVERGENTE
			} else if (escolhaRelatorio != null && escolhaRelatorio.equals("3")) {

				Collection<OSTipoPavimentoHelper> colecaoTipoPavimentoRuaConvergente = new ArrayList();
				Collection<OSTipoPavimentoHelper> colecaoTipoPavimentoRuaRetConvergente = new ArrayList();

				OSPavimentoRetornoHelper osPavimentoRetornoHelper = null;

				if (collOrdemServicoPavimento != null) {

					Iterator iteratorCollOrdemServicoPavimento = collOrdemServicoPavimento.iterator();
					while (iteratorCollOrdemServicoPavimento.hasNext()) {

						OSTipoPavimentoHelper helperRua = new OSTipoPavimentoHelper();
						OSTipoPavimentoHelper helperRuaRet = new OSTipoPavimentoHelper();

						osPavimentoRetornoHelper = new OSPavimentoRetornoHelper();

						OrdemServicoPavimento ospv = (OrdemServicoPavimento) iteratorCollOrdemServicoPavimento.next();

						// Verifica se o pavimento e a metragem sao divergentes
						// caso seja inclui na colecao.
						if ((ospv.getPavimentoRua() != null && ospv.getAreaPavimentoRua() != null && ospv.getPavimentoRuaRetorno() != null && ospv
								.getAreaPavimentoRuaRetorno() != null)
								&& ((ospv.getPavimentoRua().getId().intValue() == ospv.getPavimentoRuaRetorno().getId().intValue()) && (ospv
										.getAreaPavimentoRua().toString().equals(ospv.getAreaPavimentoRuaRetorno().toString())))
								&& ospv.getMotivoRejeicao() == null) {
							/**
							 * Seta os parametros usados no subrelatorio.
							 */

							Imovel imovel = ospv.getOrdemServico().getImovel();
							if (imovel != null) {
								String[] endereco = getControladorEndereco().pesquisarEnderecoFormatadoDividido(imovel.getId());
								osPavimentoRetornoHelper.setEndereco(endereco[3] + "-" + endereco[0]);
							} else {
								RegistroAtendimento ra = ospv.getOrdemServico().getRegistroAtendimento();

								String endereco = getControladorEndereco().pesquisarEnderecoRegistroAtendimentoFormatadoIniciadoPeloBairro(ra.getId());
								osPavimentoRetornoHelper.setEndereco(endereco);
							}
							// Pavimento Rua -- trata para que so apareca um
							// tipo de pavimento
							// com o total da area de todos os pavimentos
							// daquele tipo
							boolean inseriu = true;
							if (colecaoTipoPavimentoRuaConvergente != null) {
								Iterator iterator = colecaoTipoPavimentoRuaConvergente.iterator();
								while (iterator.hasNext()) {
									OSTipoPavimentoHelper helperRuaDaColecao = (OSTipoPavimentoHelper) iterator.next();

									if (helperRuaDaColecao.getId().intValue() == ospv.getPavimentoRua().getId().intValue()) {

										helperRuaDaColecao.setSomatorioArea(Util.somaBigDecimal(helperRuaDaColecao.getSomatorioArea(),
												ospv.getAreaPavimentoRua()));

										inseriu = false;
										break;
									}

								}
								if (inseriu) {
									helperRua.setId(ospv.getPavimentoRua().getId());
									helperRua.setDescricao(ospv.getPavimentoRua().getDescricao());
									helperRua.setSomatorioArea(ospv.getAreaPavimentoRua());

									colecaoTipoPavimentoRuaConvergente.add(helperRua);
								}
							} else {
								helperRua.setId(ospv.getPavimentoRua().getId());
								helperRua.setDescricao(ospv.getPavimentoRua().getDescricao());
								helperRua.setSomatorioArea(ospv.getAreaPavimentoRua());

								colecaoTipoPavimentoRuaConvergente.add(helperRua);
							}

							// Pavimento rua retorno -- trata para que so
							// apareca um tipo de pavimento de retorno
							// com o total da area de todos os pavimentos de
							// retorno daquele tipo
							boolean inseriuRetorno = true;
							if (colecaoTipoPavimentoRuaRetConvergente != null) {
								Iterator iteratorRetorno = colecaoTipoPavimentoRuaRetConvergente.iterator();
								while (iteratorRetorno.hasNext()) {
									OSTipoPavimentoHelper helperRuaRetornoDaColecao = (OSTipoPavimentoHelper) iteratorRetorno.next();

									if (helperRuaRetornoDaColecao.getId().intValue() == ospv.getPavimentoRuaRetorno().getId().intValue()) {

										helperRuaRetornoDaColecao.setSomatorioArea(Util.somaBigDecimal(helperRuaRetornoDaColecao.getSomatorioArea(),
												ospv.getAreaPavimentoRuaRetorno()));
										inseriuRetorno = false;
										break;
									}
								}
								if (inseriuRetorno) {
									helperRuaRet.setId(ospv.getPavimentoRuaRetorno().getId());
									helperRuaRet.setDescricao(ospv.getPavimentoRuaRetorno().getDescricao());
									helperRuaRet.setSomatorioArea(ospv.getAreaPavimentoRuaRetorno());

									colecaoTipoPavimentoRuaRetConvergente.add(helperRuaRet);
								}
							} else {
								helperRuaRet.setId(ospv.getPavimentoRuaRetorno().getId());
								helperRuaRet.setDescricao(ospv.getPavimentoRuaRetorno().getDescricao());
								helperRuaRet.setSomatorioArea(ospv.getAreaPavimentoRuaRetorno());

								colecaoTipoPavimentoRuaRetConvergente.add(helperRuaRet);
							}

							osPavimentoRetornoHelper.setOrdemServicoPavimento(ospv);
							osPavimentoRetornoHelper.setOrdemServico(ospv.getOrdemServico());
							osPavimentoRetornoHelper.setCollOSTipoPavimentoHelper_Rua(colecaoTipoPavimentoRuaConvergente);
							osPavimentoRetornoHelper.setCollOSTipoPavimentoHelper_RuaRet(colecaoTipoPavimentoRuaRetConvergente);

							collHelper.add(osPavimentoRetornoHelper);
						}

					}

				}
			}

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return collHelper;

	}

	/**
	 * Pesquisa Ordens em Processo de Repavimentação
	 * 
	 * [UC0639] Filtrar Ordens em Processo de Repavimetação.
	 * 
	 * @author Yara Taciane
	 * @date 02/06/2008
	 * 
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemProcessoRepavimentacaoPorTipoPavimentoCalcada(OSPavimentoHelper oSPavimentoHelper) throws ControladorException {
		Collection coll = new ArrayList();

		try {
			coll = repositorioOrdemServico.pesquisarOrdemProcessoRepavimentacaoPorTipoPavimentoCalcada(oSPavimentoHelper);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return coll;

	}

	/**
	 * Pesquisa Ordens em Processo de Repavimentação
	 * 
	 * [UC0639] Filtrar Ordens em Processo de Repavimetação.
	 * 
	 * @author Yara Taciane
	 * @date 02/06/2008
	 * 
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemProcessoRepavimentacaoPorTipoPavimentoRua(OSPavimentoHelper oSPavimentoHelper) throws ControladorException {
		Collection coll = new ArrayList();

		try {
			coll = repositorioOrdemServico.pesquisarOrdemProcessoRepavimentacaoPorTipoPavimentoRua(oSPavimentoHelper);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return coll;

	}

	/**
	 * Pesquisa Ordens em Processo de Repavimentação
	 * 
	 * [UC0639] Filtrar Ordens em Processo de Repavimetação.
	 * 
	 * @author Yara Taciane
	 * @date 02/06/2008
	 * 
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemProcessoRepavimentacaoPorTipoPavimentoRuaRet(OSPavimentoHelper oSPavimentoHelper) throws ControladorException {
		Collection coll = new ArrayList();

		try {
			coll = repositorioOrdemServico.pesquisarOrdemProcessoRepavimentacaoPorTipoPavimentoRuaRet(oSPavimentoHelper);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return coll;

	}

	/**
	 * [UC0766] Gerar Relatorio Boletim de Ordens de Servico Concluidas
	 * 
	 * @author Ivan Sérgio
	 * @date 07/05/2008
	 * 
	 * @param idEmpresa
	 * @param idLocalidade
	 * @param idSetorComercial
	 * @param referenciaEncerramento
	 * @param situacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarBoletimOrdensServicoConcluidasGerarRelatorio(Integer idEmpresa, Integer idLocalidade, Integer idSetorComercial,
			String referenciaEncerramento, Short situacao) throws ControladorException {

		Collection colecaoDados = null;
		Collection<RelatorioBoletimOrdensServicoConcluidasHelper> retorno = new ArrayList();

		try {
			colecaoDados = repositorioOrdemServico.pesquisarBoletimOrdensServicoConcluidasGerarRelatorio(idEmpresa, idLocalidade, idSetorComercial,
					referenciaEncerramento, situacao);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoDados != null && !colecaoDados.isEmpty()) {
			Iterator iColecaoDados = colecaoDados.iterator();
			Object[] dados = null;

			RelatorioBoletimOrdensServicoConcluidasHelper helper = null;

			while (iColecaoDados.hasNext()) {
				helper = new RelatorioBoletimOrdensServicoConcluidasHelper();
				dados = (Object[]) iColecaoDados.next();

				// Id Ordem Servico
				helper.setIdOrdemServico((Integer) dados[0]);
				// Ano/Mes Referencia do Boletim
				helper.setAnoMesReferenciaBoletim((Integer) dados[1]);
				// Codigo da Fiscalizacao
				helper.setCodigoFiscalizaco((Short) dados[2]);
				// Indicador Troca Protecao
				helper.setIndicadorTorcaProtecaoHidrometro((Short) dados[3]);
				// Indicador Troca Registro
				helper.setIndicadorTorcaRegistroHidrometro((Short) dados[4]);
				// Tipo do Servico
				helper.setIdTipoServico((Integer) dados[5]);
				// Id da Localidade
				helper.setIdLocalidade((Integer) dados[6]);
				// Descricao da Localidade
				helper.setDescricaoLocalidade(dados[7].toString());
				// Id do Local de Instalacao
				helper.setIdLocalInstalacaoHidrometro((Integer) dados[8]);
				// Descricao do Local de Instalacao
				helper.setDescricaoLocalInstalacaoHidrometro(dados[9].toString());
				// Descricao Abreviada da Empresa
				helper.setDescricaoAbreviadaFirma(dados[10].toString());
				// Codigo do Setor
				helper.setCodigoSetorComercial((Integer) dados[11]);
				// Numero da Quadra
				helper.setNumeroQuadra((Integer) dados[12]);
				// Lote
				helper.setLote((Short) dados[13]);
				// Sub Lote
				helper.setSubLote((Short) dados[14]);
				// Data Geracao da Ordem Servico
				String dataGeracao = "";
				if (dados[15] != null) {
					dataGeracao = dados[15].toString();
					dataGeracao = dataGeracao.substring(8, 10) + "/" + dataGeracao.substring(5, 7) + "/" + dataGeracao.substring(0, 4);
				}
				helper.setDataGeracaoOrdemServico(dataGeracao);
				// Data de Encerramento da Ordem Servico
				String dataEncerramentoOs = "";
				if (dados[16] != null) {
					dataEncerramentoOs = dados[16].toString();
					dataEncerramentoOs = dataEncerramentoOs.substring(8, 10) + "/" + dataEncerramentoOs.substring(5, 7) + "/"
							+ dataEncerramentoOs.substring(0, 4);
				}
				helper.setDataEncerramentoOrdemServico(dataEncerramentoOs);
				// Data de Fiscalizacao 1
				String dataFiscalizacao1 = "";
				if (dados[17] != null) {
					dataFiscalizacao1 = dados[17].toString();
					dataFiscalizacao1 = dataFiscalizacao1.substring(8, 10) + "/" + dataFiscalizacao1.substring(5, 7) + "/" + dataFiscalizacao1.substring(0, 4);
				}
				helper.setDataFiscalizacao1(dataFiscalizacao1);
				// Data de Fiscalizacao 2
				String dataFiscalizacao2 = "";
				if (dados[18] != null) {
					dataFiscalizacao2 = dados[18].toString();
					dataFiscalizacao2 = dataFiscalizacao2.substring(8, 10) + "/" + dataFiscalizacao2.substring(5, 7) + "/" + dataFiscalizacao2.substring(0, 4);
				}
				helper.setDataFiscalizacao2(dataFiscalizacao2);
				// Data de Fiscalizacao 3
				String dataFiscalizacao3 = "";
				if (dados[19] != null) {
					dataFiscalizacao3 = dados[19].toString();
					dataFiscalizacao3 = dataFiscalizacao3.substring(8, 10) + "/" + dataFiscalizacao3.substring(5, 7) + "/" + dataFiscalizacao3.substring(0, 4);
				}
				helper.setDataFiscalizacao3(dataFiscalizacao3);
				// Data de Encerramento do Boletim
				String dataEncerramentoBoletim = "";
				if (dados[20] != null) {
					dataEncerramentoBoletim = dados[20].toString();
					dataEncerramentoBoletim = dataEncerramentoBoletim.substring(8, 10) + "/" + dataEncerramentoBoletim.substring(5, 7) + "/"
							+ dataEncerramentoBoletim.substring(0, 4);
				}
				helper.setDataEncerramentoBoletim(dataEncerramentoBoletim);
				// ID do Imovel
				helper.setIdImovel((Integer) dados[21]);
				// ID do Setor
				helper.setIdSetorComercial((Integer) dados[22]);

				// Adiciona na Colecao
				retorno.add(helper);
			}
		}

		return retorno;
	}

	/**
	 * [UC0427] Tramitar Registro de Atendimento
	 * 
	 * @author Vivianne Sousa
	 * @date 10/06/2008
	 * 
	 * @throws ControladorException
	 */
	public void atualizarUnidadeOrganizacionalAtualOS(Integer idUnidadeOrganizacionalAtual, Integer idRA) throws ControladorException {

		try {
			repositorioOrdemServico.atualizarUnidadeOrganizacionalAtualOS(idUnidadeOrganizacionalAtual, idRA);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programação de Ordens de Serviço [FS0008]
	 * - Verificar possibilidade da inclusão da ordem de serviço
	 * 
	 * @author Vivianne Sousa
	 * @date 12/06/2008
	 * 
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional obterUnidadeAtualOS(Integer idOS) throws ControladorException {

		try {
			return repositorioOrdemServico.obterUnidadeAtualOS(idOS);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * [FS0006] Verificar data de emissão [FS0007] Verificar data de início do
	 * recurso [FS0008] Verificar data de término do recurso
	 * 
	 * @author Raphael Rossiter
	 * @date 05/09/2008
	 * 
	 * @param dadosAutoInfracao
	 * @param documentoEntregue
	 * @throws ControladorException
	 */
	public void validarDadosAutoInfracaoRetornoOSFiscalizacao(DadosAutoInfracaoRetornoOrdemFiscalizacaoHelper dadosAutoInfracao, Short documentoEntregue)
			throws ControladorException {

		SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();

		if (sistemaParametro.getIndicadorControlaAutoInfracao() == ConstantesSistema.SIM.shortValue()
				&& documentoEntregue.equals(ConstantesSistema.DOCUMENTO_ENTREGUE_AUTO_INFRACAO)) {

			if (dadosAutoInfracao.getIdResponsavel() == null) {
				throw new ControladorException("atencao.required", null, "Responsável");
			}

			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

			filtroFuncionario.adicionarParametro(new ParametroSimples(FiltroFuncionario.ID, dadosAutoInfracao.getIdResponsavel().intValue()));

			Collection colecaoFuncionario = this.getControladorUtil().pesquisar(filtroFuncionario, Funcionario.class.getName());

			if (colecaoFuncionario == null || colecaoFuncionario.isEmpty()) {
				throw new ControladorException("pesquisa.funcionario.inexistente");
			}

			if (dadosAutoInfracao.getDataEmissao() == null || dadosAutoInfracao.getDataEmissao().equals("")) {

				throw new ControladorException("atencao.required", null, "Data Emissão");
			}

			FiltroAutoInfracaoSituacao filtroAutoInfracaoSituacao = new FiltroAutoInfracaoSituacao();

			filtroAutoInfracaoSituacao.adicionarParametro(new ParametroSimples(FiltroAutoInfracaoSituacao.ID, dadosAutoInfracao.getIdAutoInfracaoSituacao()));

			Collection<AutoInfracaoSituacao> colecaoAutoInfracaoSituacao = getControladorUtil().pesquisar(filtroAutoInfracaoSituacao,
					AutoInfracaoSituacao.class.getName());

			AutoInfracaoSituacao autoInfracaoSituacao = (AutoInfracaoSituacao) Util.retonarObjetoDeColecao(colecaoAutoInfracaoSituacao);

			// [FS0009]-Verificar opção de geração do débito

			// if (dadosAutoInfracao.getIndicadorGerarDebito() != null
			// &&
			// dadosAutoInfracao.getIndicadorGerarDebito().equalsIgnoreCase("1")
			// &&
			// autoInfracaoSituacao.getIndicadorGerarDebito().toString().equalsIgnoreCase("2"))
			// {
			// throw new
			// ControladorException("atencao.situacao_auto_infracao_incompativel_geracao_debito");
			// }

			// [FS0009]-Verificar opção de geração do débito

			if (dadosAutoInfracao.getIndicadorGerarDebito() != null && dadosAutoInfracao.getIndicadorGerarDebito().toString().equalsIgnoreCase("2")
					&& autoInfracaoSituacao.getIndicadorGerarDebito().toString().equalsIgnoreCase("1")) {
				throw new ControladorException("atencao.situacao_auto_infracao_incompativel_geracao_debito");
			}

			if (autoInfracaoSituacao.getIndicadorDataInicioRecurso().toString().equals(AutoInfracaoSituacao.OBRIGATORIO.toString())) {

				if (dadosAutoInfracao.getDataInicioRecurso() == null) {

					throw new ControladorException("atencao.required", null, "Data Início Recurso");
				}
			}
			if (autoInfracaoSituacao.getIndicadorDataTerminoRecurso().toString().equals(AutoInfracaoSituacao.OBRIGATORIO.toString())) {
				if (dadosAutoInfracao.getDataTerminoRecurso() == null) {

					throw new ControladorException("atencao.required", null, "Data Térm. Recurso");
				}
			}

			// Validacao da Data de Emissao
			getControladorFaturamento().validarQuantidadeParcelasAutoInfracao(dadosAutoInfracao.getQuantidadeParcelas());

			// Validacao da Data de Emissao
			getControladorFaturamento().validarDataEmissaoAutoInfracao(dadosAutoInfracao.getDataEmissao(), dadosAutoInfracao.getIdAutoInfracaoSituacao(),
					sistemaParametro);

			if (dadosAutoInfracao.getDataInicioRecurso() != null && dadosAutoInfracao.getDataTerminoRecurso() != null) {

				getControladorFaturamento().validarDataEmissaoAutoInfracao(dadosAutoInfracao.getDataEmissao(), dadosAutoInfracao.getIdAutoInfracaoSituacao(),
						sistemaParametro);

				// Validacao da Data de Inicio do Recurso
				getControladorFaturamento().validarDataInicioRecursoAutoInfracao(dadosAutoInfracao.getDataEmissao(), dadosAutoInfracao.getDataInicioRecurso(),
						sistemaParametro);

				// Validacao da Data de Termino do Recurso
				getControladorFaturamento().validarDataTerminoRecursoAutoInfracao(dadosAutoInfracao.getDataInicioRecurso(),
						dadosAutoInfracao.getDataTerminoRecurso());
			}
		}
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * [SB0005] Atualizar Autos de Infração
	 * 
	 * @author Raphael Rossiter
	 * @date 05/09/2008
	 * 
	 * @param dadosAutoInfracao
	 * @param documentoEntregue
	 * @param idImovel
	 * @param idOrdemServico
	 * @param idFiscalizacaoSituacao
	 * @return AutosInfracao
	 * @throws ControladorException
	 */
	@SuppressWarnings("static-access")
	public AutosInfracao gerarAutosInfracao(DadosAutoInfracaoRetornoOrdemFiscalizacaoHelper dadosAutoInfracao, Short documentoEntregue, Integer idImovel,
			Integer idOrdemServico, Integer idFiscalizacaoSituacao, Usuario usuarioLogado) throws ControladorException {

		AutosInfracao autosInfracao = null;

		SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();

		if (sistemaParametro.getIndicadorControlaAutoInfracao() == ConstantesSistema.SIM.shortValue()
				&& documentoEntregue.equals(ConstantesSistema.DOCUMENTO_ENTREGUE_AUTO_INFRACAO)) {

			AutosInfracao autosInfracaoNaBase = getControladorFaturamento().pesquisarAutosInfracaoPorOS(idOrdemServico);

			if (autosInfracaoNaBase != null) {

				// RESPONSAVEL
				Funcionario funcionario = new Funcionario();
				funcionario.setId(dadosAutoInfracao.getIdResponsavel());
				autosInfracaoNaBase.setFuncionario(funcionario);

				// AUTO_INFRACAO_SITUACAO
				FiltroAutoInfracaoSituacao filtroAutoInfracaoSituacao = new FiltroAutoInfracaoSituacao();
				filtroAutoInfracaoSituacao
						.adicionarParametro(new ParametroSimples(filtroAutoInfracaoSituacao.ID, dadosAutoInfracao.getIdAutoInfracaoSituacao()));

				Collection colecaoAutoInfracaoSituacao = getControladorUtil().pesquisar(filtroAutoInfracaoSituacao, AutoInfracaoSituacao.class.getName());

				AutoInfracaoSituacao autoInfracaoSituacao = (AutoInfracaoSituacao) Util.retonarObjetoDeColecao(colecaoAutoInfracaoSituacao);
				autoInfracaoSituacao.setId(dadosAutoInfracao.getIdAutoInfracaoSituacao());
				autosInfracaoNaBase.setAutoInfracaoSituacao(autoInfracaoSituacao);

				// QTD DE PARCELAS
				autosInfracaoNaBase.setNumeroParcelasDebito(dadosAutoInfracao.getQuantidadeParcelas());

				// DATA_EMISSAO
				autosInfracaoNaBase.setDataEmissao(dadosAutoInfracao.getDataEmissao());

				// DATA_INICIO_RECURSO
				autosInfracaoNaBase.setDataInicioRecurso(dadosAutoInfracao.getDataInicioRecurso());

				// DATA_TERMINO_RECURSO
				autosInfracaoNaBase.setDataTerminoRecurso(dadosAutoInfracao.getDataTerminoRecurso());

				// ANO_MES_REFERENCIA_GERADA
				autosInfracaoNaBase.setAnoMesReferenciaGerada(sistemaParametro.getAnoMesFaturamento());

				// OBSERVACAO
				autosInfracaoNaBase.setObservacao(dadosAutoInfracao.getObservacao());

				// DEBITO_TIPO
				DebitoTipo debitoTipo = new DebitoTipo();
				debitoTipo.setId(dadosAutoInfracao.getIdDebitoTipo());
				autosInfracaoNaBase.setDebitoTipo(debitoTipo);

				// USUÁRIO LOGADO
				autosInfracaoNaBase.setUsuario(usuarioLogado);

				autosInfracaoNaBase.setUltimaAlteracao(new Date());

				getControladorUtil().atualizar(autosInfracaoNaBase);

				autosInfracao = autosInfracaoNaBase;
			} else {
				autosInfracao = new AutosInfracao();

				// IMOVEL
				Imovel imovel = new Imovel();
				imovel.setId(idImovel);
				autosInfracao.setImovel(imovel);

				// ORDEM_SERVICO
				OrdemServico ordemServico = new OrdemServico();
				ordemServico.setId(idOrdemServico);
				autosInfracao.setOrdemServico(ordemServico);

				// RESPONSAVEL
				Funcionario funcionario = new Funcionario();
				funcionario.setId(dadosAutoInfracao.getIdResponsavel());
				autosInfracao.setFuncionario(funcionario);

				// FISCALIZACAO_SITUACAO
				FiscalizacaoSituacao fiscalizacaoSituacao = new FiscalizacaoSituacao();
				fiscalizacaoSituacao.setId(idFiscalizacaoSituacao);
				autosInfracao.setFiscalizacaoSituacao(fiscalizacaoSituacao);

				// AUTO_INFRACAO_SITUACAO

				FiltroAutoInfracaoSituacao filtroAutoInfracaoSituacao = new FiltroAutoInfracaoSituacao();
				filtroAutoInfracaoSituacao
						.adicionarParametro(new ParametroSimples(filtroAutoInfracaoSituacao.ID, dadosAutoInfracao.getIdAutoInfracaoSituacao()));

				Collection colecaoAutoInfracaoSituacao = getControladorUtil().pesquisar(filtroAutoInfracaoSituacao, AutoInfracaoSituacao.class.getName());

				AutoInfracaoSituacao autoInfracaoSituacao = (AutoInfracaoSituacao) Util.retonarObjetoDeColecao(colecaoAutoInfracaoSituacao);
				autoInfracaoSituacao.setId(dadosAutoInfracao.getIdAutoInfracaoSituacao());
				autosInfracao.setAutoInfracaoSituacao(autoInfracaoSituacao);

				// QTD DE PARCELAS
				autosInfracao.setNumeroParcelasDebito(dadosAutoInfracao.getQuantidadeParcelas());

				// DATA_EMISSAO
				autosInfracao.setDataEmissao(dadosAutoInfracao.getDataEmissao());

				// DATA_INICIO_RECURSO
				autosInfracao.setDataInicioRecurso(dadosAutoInfracao.getDataInicioRecurso());

				// DATA_TERMINO_RECURSO
				autosInfracao.setDataTerminoRecurso(dadosAutoInfracao.getDataTerminoRecurso());

				// ANO_MES_REFERENCIA_GERADA
				autosInfracao.setAnoMesReferenciaGerada(sistemaParametro.getAnoMesFaturamento());

				// OBSERVACAO
				autosInfracao.setObservacao(dadosAutoInfracao.getObservacao());

				// DEBITO_TIPO
				DebitoTipo debitoTipo = new DebitoTipo();
				debitoTipo.setId(dadosAutoInfracao.getIdDebitoTipo());

				autosInfracao.setDebitoTipo(debitoTipo);

				// USUÁRIO LOGADO
				autosInfracao.setUsuario(usuarioLogado);

				autosInfracao.setUltimaAlteracao(new Date());

				// INSERINDO AUTOS_INFRACAO
				Integer idAutosInfracao = (Integer) this.getControladorUtil().inserir(autosInfracao);
				autosInfracao.setId(idAutosInfracao);
			}

		}

		return autosInfracao;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * [SB0005] Atualizar Autos de Infração
	 * 
	 * @author Raphael Rossiter
	 * @date 05/09/2008
	 * 
	 * @param autosInfracao
	 * @param debitoACobrar
	 * @return AutosInfracaoDebitoACobrar
	 * @throws ControladorException
	 */
	public AutosInfracaoDebitoACobrar gerarAutosInfracaoDebitoACobrar(AutosInfracao autosInfracao, DebitoACobrar debitoACobrar) throws ControladorException {

		AutosInfracaoDebitoACobrar autosInfracaoDebitoACobrar = null;

		if (autosInfracao != null && debitoACobrar != null) {

			autosInfracaoDebitoACobrar = new AutosInfracaoDebitoACobrar();

			// AUTOS_INFRACAO
			autosInfracaoDebitoACobrar.setAutosInfracao(autosInfracao);

			// DEBITO_A_COBRAR_GERAL
			DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
			debitoACobrarGeral.setId(debitoACobrar.getId());

			autosInfracaoDebitoACobrar.setDebitoACobrarGeral(debitoACobrarGeral);

			autosInfracaoDebitoACobrar.setUltimaAlteracao(new Date());

			// INSERINDO AUTOS_INFRACAO_DEBITO_A_COBRAR
			Integer idAutosInfracaoDebitoACobrar = (Integer) this.getControladorUtil().inserir(autosInfracaoDebitoACobrar);
			autosInfracaoDebitoACobrar.setId(idAutosInfracaoDebitoACobrar);

		}

		return autosInfracaoDebitoACobrar;
	}

	/**
	 * [UC0427] - Tramitar Registro Atendimento [FS0011]Validar Tramite para
	 * terceira
	 * 
	 * @author Vivianne Sousa
	 * @date 29/10/2008
	 */
	public boolean existeMaisDeUmaOrdemServicoPendente(Integer idregistroAtendimento) throws ControladorException {

		try {
			return repositorioOrdemServico.existeMaisDeUmaOrdemServicoPendente(idregistroAtendimento);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * [UC0488] - Consultar Dados do Registro de Atendimento
	 * 
	 * Dados da Os Associadas
	 * 
	 * @author Arthur Carvalho
	 * @date 17/02/2009
	 */
	public Collection<OrdemServicoHelper> pesquisarOrdensServicosAssociados(Integer idregistroAtendimento) throws ControladorException {
		Collection<OrdemServicoHelper> colecaoOs = new ArrayList<OrdemServicoHelper>();
		try {
			Collection retornoColecao = repositorioOrdemServico.pesquisarOrdensServicosAssociados(idregistroAtendimento);

			if (retornoColecao != null && !retornoColecao.isEmpty()) {

				Iterator itera = retornoColecao.iterator();

				while (itera.hasNext()) {
					Object[] retorno = (Object[]) itera.next();

					OrdemServicoHelper osHelper = new OrdemServicoHelper();

					osHelper.setNumeroOrdemServico((Integer) retorno[0]);
					osHelper.setDataGeracao((Date) retorno[1]);
					osHelper.setIdServicoTipo((Integer) retorno[2]);
					osHelper.setDescricaoServicoTipo((String) retorno[3]);
					osHelper.setDataEncerramento((Date) retorno[4]);
					osHelper.setParecerEncerramento((String) retorno[5]);

					ObterDescricaoSituacaoOSHelper obterDescricaoSituacaoOSHelper = this.obterDescricaoSituacaoOS(osHelper.getNumeroOrdemServico());

					osHelper.setSituacao(obterDescricaoSituacaoOSHelper.getDescricaoSituacao());

					colecaoOs.add(osHelper);
				}
			}

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return colecaoOs;
	}

	/**
	 * Retorna a quantidade de registros para geracao do relatorio
	 * 
	 * [UC0711] Filtro para Emissao de Ordens Seletivas
	 * 
	 * @author Anderson Italo
	 * @date 21/08/2009
	 * 
	 * @param ImovelEmissaoOrdensSeletivasHelper
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer filtrarImovelEmissaoOrdensSeletivasCount(ImovelEmissaoOrdensSeletivasHelper helper) throws ControladorException {

		Integer retorno = 0;
		// Date data = null;
		String dataInstalacaoHidrometroInicial = "";
		String dataInstalacaoHidrometroFinal = "";

		try {

			// Recupera o AnoMesFaturamento de Sistema Parametro
			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();

			// Subtrai 1 mes do ano/mes faturamento para pegar sempre o mes
			// fechado
			String anoMesFaturamento = Util.subtraiAteSeisMesesAnoMesReferencia(sistemaParametro.getAnoMesFaturamento(), 1).toString();

			// Verifica o Tipo de Ordem(Servico)
			if (helper.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_INSTALACAO)) {
				helper.setTipoOrdem("" + ServicoTipo.TIPO_EFETUAR_INSTALACAO_HIDROMETRO);
			} else if (helper.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_SUBSTITUICAO)) {
				helper.setTipoOrdem("" + ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO);
			} else if (helper.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_REMOCAO)) {
				helper.setTipoOrdem("" + ServicoTipo.TIPO_EFETUAR_REMOCAO_HIDROMETRO);
			} else if (helper.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_INSPECAO_ANORMALIDADE)) {
				helper.setTipoOrdem("" + ServicoTipo.TIPO_INSPECAO_ANORMALIDADE);
			}

			// Data Atual - 30 dias para verificar os Imoveis com
			// Hidrometro instalado a mais de 30 dias
			// data = Util.subtrairNumeroDiasDeUmaData(new Date(), 30);
			// dataInstalacaoHidrometro = Util.getAno(data) + "-" +
			// Util.getMes(data) + "-" + Util.getDiaMes(data);

			dataInstalacaoHidrometroInicial = helper.getMesAnoInstalacaoInicialHidrometro();

			dataInstalacaoHidrometroFinal = helper.getMesAnoInstalacaoFinalHidrometro();

			retorno = repositorioOrdemServico.filtrarImovelEmissaoOrdensSeletivasCount(helper, dataInstalacaoHidrometroInicial, anoMesFaturamento,
					dataInstalacaoHidrometroFinal);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 * [UC0620] Obter Indicador de Autorização para Manutenção da OS
	 * 
	 * Este caso de uso Obtém o indicador de autorização para manutenção da OS,
	 * ou seja, se o usuário tem autorização para efetuar a manutenção da OS
	 * 
	 * @author Arthur Carvalho
	 * @date 05/11/2009
	 * 
	 * @param idUnidadeOrganizacional
	 * @param idUusuario
	 * @throws ControladorException
	 */
	public Short obterIndicadorAutorizacaoManutencaoOS(Integer idUsuario, OrdemServico ordemServico) throws ControladorException {

		// Atribui o valor 1-sim ao indicador
		Short retorno = ConstantesSistema.SIM;

		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, idUsuario));
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("usuarioTipo");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("usuarioAbrangencia");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("localidadeElo");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
		Collection colecaoUsuario = this.getControladorUtil().pesquisar(filtroUsuario, Usuario.class.getName());

		Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);

		// Unidade de Lotação do Usuário corresponda à Unidade de Geração da OS
		if (usuario.getUnidadeOrganizacional().getId() != null && usuario.getUnidadeOrganizacional().getId().equals(ordemServico.getUnidadeAtual().getId())) {
			// SB0001
			retorno = verificarOSLocalidadeUsuario(usuario, ordemServico);

		} else if (usuario.getUsuarioTipo() != null && usuario.getUsuarioTipo().getIndicadorFuncionario().toString().equals("2")) {
			// Caso o usuário seja de empresa terceira
			try {
				// Caso a empresa do usuário seja uma das empresas de cobrança
				// da localidade da ordem de serviço
				Collection colecaoIdsEmpresaCobranca = repositorioCobranca.pesquisarEmpresaCobrancaDaRota(ordemServico.getImovel().getLocalidade().getId());
				Iterator iteratorColecaoIdsEmpresaCobranca = colecaoIdsEmpresaCobranca.iterator();
				while (iteratorColecaoIdsEmpresaCobranca.hasNext()) {
					Object idEmpresaCobranca = (Object) iteratorColecaoIdsEmpresaCobranca.next();
					if (usuario.getEmpresa().getId() != null && usuario.getEmpresa().getId().toString().equals(idEmpresaCobranca.toString())) {
						// SB0001
						retorno = verificarOSLocalidadeUsuario(usuario, ordemServico);
						break;
					} else {
						retorno = ConstantesSistema.NAO;
					}
				}
			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}

		} else {

			UnidadeOrganizacional unidadeOrganizacionalSuperior = new UnidadeOrganizacional();

			try {

				// Verificar existência de unidade superior
				if (unidadeOrganizacionalSuperior == null || unidadeOrganizacionalSuperior.equals("")) {
					retorno = ConstantesSistema.NAO;
				} else {

					if (ordemServico != null && ordemServico.getUnidadeAtual() != null && ordemServico.getUnidadeAtual().getUnidadeSuperior() != null
							&& ordemServico.getUnidadeAtual().getUnidadeSuperior().getId() != null) {

						unidadeOrganizacionalSuperior = repositorioOrdemServico.pesquisarUnidadeOrganizacionalSuperior(ordemServico.getUnidadeAtual()
								.getUnidadeSuperior().getId());
					}

				}

				while (retorno != ConstantesSistema.NAO) {
					// A Unidade de Lotação do Usuário corresponda à Unidade
					// Superior
					if (usuario.getUnidadeOrganizacional() != null && usuario.getUnidadeOrganizacional().getId() != null
							&& unidadeOrganizacionalSuperior != null && unidadeOrganizacionalSuperior.getId() != null
							&& usuario.getUnidadeOrganizacional().getId().toString().equals(unidadeOrganizacionalSuperior.getId().toString())) {
						// SB0001
						retorno = verificarOSLocalidadeUsuario(usuario, ordemServico);
						break;
					} else {
						// Verificar existência de unidade superior
						if (unidadeOrganizacionalSuperior.getUnidadeSuperior() != null && !unidadeOrganizacionalSuperior.getUnidadeSuperior().equals("")) {

							unidadeOrganizacionalSuperior = repositorioOrdemServico.pesquisarUnidadeOrganizacionalSuperior(unidadeOrganizacionalSuperior
									.getUnidadeSuperior().getId());
						} else {
							retorno = ConstantesSistema.NAO;
						}
					}

				}

			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}

		}

		return retorno;
	}

	/**
	 * [UC0620] Verificar Localidade da OS e do Usuario - SB0001-Verificar
	 * Localidade -
	 * 
	 * @author Arthur Carvalho
	 * @date 06/11/2009
	 * 
	 * @param idUnidadeOrganizacional
	 * @param idUusuario
	 * @throws ControladorException
	 */
	public Short verificarOSLocalidadeUsuario(Usuario usuario, OrdemServico ordemServico) throws ControladorException {

		// Atribui o valor 1-sim ao indicador
		Short retorno = ConstantesSistema.SIM;

		// Caso a Abrangencia do Usuario seja Gerencia Regional
		if (usuario.getUsuarioAbrangencia().getId().toString().equals("2")) {
			if (usuario.getGerenciaRegional() != null) {
				if (!usuario.getGerenciaRegional().getId().toString().equals(ordemServico.getImovel().getLocalidade().getGerenciaRegional().getId().toString())) {
					retorno = ConstantesSistema.NAO_LOCALIDADE;
				}
			}
		}

		// Caso a Abrangencia do Usuario seja Unidade Negocio
		if (usuario.getUsuarioAbrangencia().getId().toString().equals("5")) {

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, ordemServico.getImovel().getLocalidade().getId()));

			Collection colecaoLocalidade = this.getControladorUtil().pesquisar(filtroLocalidade, Localidade.class.getName());

			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			if (usuario.getUnidadeNegocio() != null) {
				if (!usuario.getUnidadeNegocio().getId().toString().equals(localidade.getUnidadeNegocio().getId().toString())) {
					retorno = ConstantesSistema.NAO_LOCALIDADE;
				}
			}
		}

		// Caso a Abrangencia do Usuario seja Localidade
		if (usuario.getUsuarioAbrangencia().getId().toString().equals("4")) {

			if (usuario.getLocalidade() != null) {
				if (!usuario.getLocalidade().getId().toString().equals(ordemServico.getImovel().getLocalidade().getId().toString())) {
					retorno = ConstantesSistema.NAO_LOCALIDADE;
				}
			}
		}

		// Caso a Abrangencia do Usuario seja Elo
		if (usuario.getUsuarioAbrangencia().getId().toString().equals("3")) {

			if (usuario.getLocalidadeElo() != null) {
				if (!usuario.getLocalidadeElo().getId().toString().equals(ordemServico.getImovel().getLocalidade().getLocalidade().getId().toString())) {
					retorno = ConstantesSistema.NAO_LOCALIDADE;
				}
			}
		}

		return retorno;
	}

	/**
	 * [UC0457] Encerra Ordem de Serviço
	 * 
	 * [FS0002] - Validar Tipo Serviço [FS0004] - Verificar preenchimento dos
	 * campos [FS0007] - Validar Data de Encerramento [FS0008] - Validar Data do
	 * roteiro
	 * 
	 * 
	 * @author Arthur Carvalho
	 * @date 15/01/2010
	 * 
	 * @throws ControladorException
	 */
	public void validarCamposEncerrarOSPopup(String indicadorExecucao, String numeroOS, String motivoEncerramento, String dataEncerramento,
			Collection colecaoManterDadosAtividadesOrdemServicoHelper, String tipoServicoReferenciaId, String indicadorPavimento, String tipoServicoOsId,
			String idTipoRetornoOSReferida, String indicadorDeferimento, String indicadorTrocaServicoTipo, String idServicoTipo, String dataRoteiro,
			String idRA, String indicadorVistoriaServicoTipo, String codigoRetornoVistoriaOs, String indicadorDiagnostico, String observacaoEncerramento,
			Usuario usuario, String idPavimentoRua, String metragemPavimentoRua, String idPavimentoCalcada, String metragemPavimentoCalcada,
			String idUnidadeRepavimentadora) throws ControladorException {

		// caso o motivo de encerramento esteja nulo
		if (motivoEncerramento == null || motivoEncerramento.equals("")) {
			throw new ControladorException("atencao.required", null, "Motivo de Encerramento");
		}

		// caso não exista a data de encerramento então a data de encerramento
		// será a data atual
		if (dataEncerramento != null && !dataEncerramento.equals("")) {
			// [FS0007] - Validar data de encerramento
			if (Util.validarDiaMesAno(dataEncerramento)) {
				throw new ControladorException("atencao.data.invalida", null, "Data de Encerramento");
			}
			Date dataEncerramentoDate = Util.converteStringParaDate(dataEncerramento);
			if (dataEncerramentoDate.after(new Date())) {
				throw new ControladorException("atencao.data.maior.data.corrente", null, "Data de Encerramento");
			}

			// ..........................................................................................
			// alterado por Yara Taciane - 18/06/2007 - Início
			// Solicitado por Denys Guimarães.
			// ..........................................................................................

			// caso dataEncerramento menos que dataGeracaoOS menos quantidade de
			// dias parametros.
			Integer numOS = Util.converterStringParaInteger(numeroOS);
			try {
				Date dataGeracaoOS = repositorioOrdemServico.obterDataGeracaOS(numOS);
				SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

				Date xData = Util.subtrairNumeroDiasDeUmaData(dataGeracaoOS, sistemaParametro.getNumeroDiasEncerramentoOrdemServico());

				// -1 se a data1 for menor que a data2, 0 se as datas forem
				// iguais,
				// 1 se a data1 for maior que a data2.
				if (Util.compararData(dataEncerramentoDate, xData) == -1) {
					throw new ControladorException("atencao.data_encerramento_anterior_permitido", null, "Data de Encerramento");
				}

				// Verifica se é uma ordem de serviço seletiva.
				Integer retorno = repositorioOrdemServico.verificarOrdemServicoSeletiva(numOS);
				// Verifica se o usuário tem permissão especial.
				boolean permissaoEspecial = getControladorPermissaoEspecial().verificarPermissaoInformarDataEncOSAnteriorDataCorrente(usuario);
				// Caso seja uma ordem de serviço seletiva.
				if (retorno != null) {
					Date dataCorrente = new Date();
					// Obtem data de data corrente menos a quantidade de dias do
					// parâmetro.
					Date yData = Util.subtrairNumeroDiasDeUmaData(dataCorrente, sistemaParametro.getNumeroDiasEncerramentoOSSeletiva());

					// Caso dataEncerramento esteja menor que data corrente
					// menos a quantidade de dias
					// e o usuário NÃO tenha permissão especial.
					if (Util.compararData(dataEncerramentoDate, yData) == -1 && permissaoEspecial == false) {
						throw new ControladorException("atencao.data_encerramento_anterior_permitido_os_seletiva", null, "Data de Encerramento");
					}
				}

			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			// ..........................................................................................
			// alterado por Yara Taciane - 18/06/2007 - Fim
			// ..........................................................................................

		} else {
			throw new ControladorException("atencao.required", null, "Data de Encerramento");
		}
		// caso não exista a data de encerramento então a data de encerramento
		// será a data atual
		if (dataRoteiro != null && !dataRoteiro.equals("")) {
			// [FS0008] - Validar data de roteiro
			if (Util.validarDiaMesAno(dataRoteiro)) {
				throw new ControladorException("atencao.data.invalida", null, "Data de Encerramento");
			}
			Date dataRoteiroDate = Util.converteStringParaDate(dataRoteiro);
			Date dataEncerramentoDate = Util.converteStringParaDate(dataEncerramento);
			if (dataEncerramentoDate.after(dataRoteiroDate)) {
				throw new ControladorException("atencao.data_encerramento_maior_data_roteiro", null);
			}
		}

		// indicador execução seja diferente de nulo
		if (indicadorExecucao != null && !indicadorExecucao.equals("")) {
			short indicadorExecucaoShort = Short.parseShort(indicadorExecucao);

			// indicador execução seja igual a sim(1)
			if (indicadorExecucaoShort == AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_SIM) {

				try {
					if (numeroOS != null && !numeroOS.equals("")) {
						Short indicadorAtividade = repositorioOrdemServico.pesquisarIndAtividadeServicoTipoOS(new Integer(numeroOS));

						boolean temPermissaoEspecial = getControladorPermissaoEspecial().verificarPermissaoEspecial(
								PermissaoEspecial.ENCERRAR_ORDEM_SERVICO_SEM_ATIVIDADES, usuario);

						if (!temPermissaoEspecial && indicadorAtividade.equals(ConstantesSistema.SIM)) {
							if (colecaoManterDadosAtividadesOrdemServicoHelper == null || colecaoManterDadosAtividadesOrdemServicoHelper.isEmpty()) {

								throw new ControladorException("atencao.required", null, "Atividades");
							}
						}
					}

				} catch (ErroRepositorioException e) {
					throw new ControladorException("erro.sistema", e);
				}

				// se o serviço tipo referencia seja igual a nulo
				if (tipoServicoReferenciaId == null || tipoServicoReferenciaId.equals("")) {
					// [SB0002] - Encerrar com execução e sem referência

					// caso o indicador de pavimento esteja igual a sim e o
					// pavimento não
					// esteja preenchido então
					if (indicadorPavimento != null && !indicadorPavimento.equals("")) {
						short indicadorPavimentoShort = new Short(indicadorPavimento);
						if (indicadorPavimentoShort == ServicoTipo.INDICADOR_PAVIMENTO_SIM) {

							FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
							filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, tipoServicoOsId));

							Collection colecaoServicoTipo = getControladorUtil().pesquisar(filtroServicoTipo, ServicoTipo.class.getName());

							if (colecaoServicoTipo != null && !colecaoServicoTipo.equals("")) {
								ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(colecaoServicoTipo);
								// Verifica se é pavimento RUA
								if (servicoTipo.getIndicadorPavimentoRua().intValue() == ConstantesSistema.SIM.intValue()) {
									// Lança a exception
									if (idPavimentoRua == null || idPavimentoRua.equals("")) {
										throw new ControladorException("atencao.required", null, "Pavimento Rua");
									}
									if (metragemPavimentoRua == null || metragemPavimentoRua.equals("")) {
										throw new ControladorException("atencao.required", null, "Metragem de Pavimento Rua");
									}

								}
								// Verifica se é pavimento CALCADA
								// if
								// (servicoTipo.getIndicadorPavimentoCalcada().intValue()
								// == ConstantesSistema.SIM.intValue()){
								// if ( idPavimentoCalcada == null ||
								// idPavimentoCalcada.equals("") ) {
								// throw new ControladorException(
								// "atencao.required", null,
								// "Pavimento Calçada");
								// }
								// if ( metragemPavimentoCalcada == null ||
								// metragemPavimentoCalcada.equals("") ) {
								// throw new ControladorException(
								// "atencao.required", null,
								// "Metragem de Pavimento Calçada");
								// }
								// }

							}

						}
					}

				} else {
					// [SB0003] - Encerrar com execução e com referência

					if (idTipoRetornoOSReferida == null || idTipoRetornoOSReferida.equals("")) {
						throw new ControladorException("atencao.required", null, "Tipo de Retorno Referida");
					}
					if (indicadorDeferimento != null && !indicadorDeferimento.equals("")) {
						short indDeferimento = Short.parseShort(indicadorDeferimento);
						// se indicador deferimento for igual a sim(1)
						if (indDeferimento == OsReferidaRetornoTipo.INDICADOR_DEFERIMENTO_SIM) {
							// 9.1.1 caso o indicador de pavimento esteja igual
							// a sim e o
							// pavimento não
							// esteja preenchido então
							if (indicadorPavimento != null && !indicadorPavimento.equals("")) {
								short indicadorPavimentoShort = new Short(indicadorPavimento);
								if (indicadorPavimentoShort == ServicoTipo.INDICADOR_PAVIMENTO_SIM) {
									FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
									filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, tipoServicoOsId));

									Collection colecaoServicoTipo = getControladorUtil().pesquisar(filtroServicoTipo, ServicoTipo.class.getName());

									if (colecaoServicoTipo != null) {
										ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(colecaoServicoTipo);
										// Verifica se é pavimento RUA
										if (servicoTipo.getIndicadorPavimentoRua().intValue() == ConstantesSistema.SIM.intValue()) {
											// Lança a exception
											if (idPavimentoRua == null || idPavimentoRua.equals("")) {
												throw new ControladorException("atencao.required", null, "Pavimento Rua");
											}
											if (metragemPavimentoRua == null || metragemPavimentoRua.equals("")) {
												throw new ControladorException("atencao.required", null, "Metragem de Pavimento Rua");
											}

										}
										// Verifica se é pavimento CALCADA
										// if
										// (servicoTipo.getIndicadorPavimentoCalcada().intValue()
										// == ConstantesSistema.SIM.intValue()){
										// if ( idPavimentoCalcada == null ||
										// idPavimentoCalcada.equals("") ) {
										// throw new ControladorException(
										// "atencao.required", null,
										// "Pavimento Calçada");
										// }
										// if ( metragemPavimentoCalcada == null
										// ||
										// metragemPavimentoCalcada.equals("") )
										// {
										// throw new ControladorException(
										// "atencao.required", null,
										// "Metragem de Pavimento Calçada");
										// }
										// }

									}
								}
							}
							// 9.1.2 caso o indicador de troca se serviço da
							// tabela os
							// referida
							// retorno tipo seja diferente de nula e igual a sim
							if (indicadorTrocaServicoTipo != null && !indicadorTrocaServicoTipo.equals("")) {
								short indicadorTrocaServicoTipoShort = new Short(indicadorTrocaServicoTipo);
								if (indicadorTrocaServicoTipoShort == OsReferidaRetornoTipo.INDICADOR_TROCA_SERVICO_SIM) {
									if (idServicoTipo == null || idServicoTipo.equals("")) {
										throw new ControladorException("atencao.required", null, "Tipo de Serviço");
									} else {
										// [FS0002] - Validar Tipo de Serviço
										// Caso já exista OS para RA informado
										// com o mesmo tipo de serviço
										// selecionado
										if (idRA != null && !idRA.equals("")) {
											try {
												Object[] parmsRAServicoTipo = repositorioOrdemServico.verificarExistenciaOSEncerrado(new Integer(idRA),
														new Integer(idServicoTipo));
												if (parmsRAServicoTipo != null && !parmsRAServicoTipo.equals("")) {
													Integer idOSNaBase = null;
													String descricaoServicoTipo = null;
													if (parmsRAServicoTipo[0] != null) {
														idOSNaBase = (Integer) parmsRAServicoTipo[0];
													}
													if (parmsRAServicoTipo[1] != null) {
														descricaoServicoTipo = (String) parmsRAServicoTipo[1];
													}
													if (idOSNaBase != null) {
														throw new ControladorException("atencao.ordem_servico_com_ra", null, numeroOS, idRA != null ? "" + idRA
																: "", descricaoServicoTipo);
													}
												}

												Integer idServTipoRef = repositorioOrdemServico.verificarExistenciaServicoTipoReferencia(new Integer(
														idServicoTipo));
												if (idServTipoRef != null && !idServTipoRef.equals("")) {
													throw new ControladorException("atencao.existe_tipo_servico_referencia_os");
												}

											} catch (ErroRepositorioException e) {
												throw new ControladorException("erro.sistema", e);
											}
										}
										// tipo de serviço selecionado tenha um
										// tipo de serviço referência
									}

								}
							}
						}
					}

				}
			}

		}

		// caso a ordem de serviço tenha o tipo de serviço de referência de
		// DIAGNÓSTICO,
		// exigir o Parecer de Encerramento
		if (indicadorDiagnostico != null && indicadorDiagnostico.equalsIgnoreCase(ServicoTipoReferencia.INDICADOR_DIAGNOSTICO_ATIVO.toString())
				&& (observacaoEncerramento == null || observacaoEncerramento.equalsIgnoreCase(""))) {
			throw new ControladorException("atencao.indicador_diagnostico_sim");
		}

		if (observacaoEncerramento != null && !observacaoEncerramento.equals("") && observacaoEncerramento.length() > 400) {

			String[] msg = new String[2];
			msg[0] = "Parecer";
			msg[1] = "400";

			throw new ControladorException("atencao.execedeu_limit_observacao", null, msg);
		}

	}

	/**
	 * 
	 * [UC0430] - Gerar Ordem de Serviço
	 * 
	 * 
	 * @author Hugo Amorim
	 * @date 12/02/2010
	 */
	public Integer gerarOrdemServicoFiscalizacao(OrdemServico ordemServico, Usuario usuario) throws ControladorException {

		Calendar calendar = Calendar.getInstance();

		ServicoTipo servicoTipo = this.recuperaServicoTipoPorConstante(ServicoTipo.TIPO_ORDEM_SERVICO_FISCALIZACAO);

		ordemServico.setServicoTipo(servicoTipo);
		ordemServico.setAtendimentoMotivoEncerramento(null);
		ordemServico.setOsReferidaRetornoTipo(null);
		ordemServico.setSituacao(OrdemServico.SITUACAO_PENDENTE);
		ordemServico.setDataGeracao(new Date());
		ordemServico.setDataEmissao(null);
		ordemServico.setDataEncerramento(null);
		ordemServico.setDescricaoParecerEncerramento(null);
		ordemServico.setAreaPavimento(null);
		ordemServico.setIndicadorComercialAtualizado(ConstantesSistema.NAO);
		ordemServico.setServicoNaoCobrancaMotivo(null);
		ordemServico.setPercentualCobranca(null);
		ordemServico.setFiscalizacaoColetiva(null);
		ordemServico.setIndicadorServicoDiagnosticado(ConstantesSistema.NAO);
		ordemServico.setUltimaAlteracao(calendar.getTime());
		ordemServico.setValorOriginal(servicoTipo.getValor());
		ordemServico.setServicoTipoPrioridadeOriginal(servicoTipo.getServicoTipoPrioridade());
		ordemServico.setServicoTipoPrioridadeAtual(servicoTipo.getServicoTipoPrioridade());
		ordemServico.setIndicadorProgramada(OrdemServico.NAO_PROGRAMADA);
		ordemServico.setIndicadorEncerramentoAutomatico(ConstantesSistema.NAO);

		FiltroUnidadeOrganizacional filtroUnidade = new FiltroUnidadeOrganizacional();

		filtroUnidade.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID_LOCALIDADE, ordemServico.getImovel().getLocalidade().getId()));

		Collection colecaoUnidade = this.getControladorUtil().pesquisar(filtroUnidade, UnidadeOrganizacional.class.getName());

		UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidade);

		ordemServico.setUnidadeAtual(unidadeOrganizacional);
		ordemServico.setIndicadorBoletim(ConstantesSistema.NAO);

		Integer idOrdemServico = (Integer) getControladorUtil().inserir(ordemServico);

		OrdemServicoUnidade ordemServicoUnidade = new OrdemServicoUnidade();

		ordemServicoUnidade.setOrdemServico(ordemServico);
		ordemServicoUnidade.setUnidadeOrganizacional(unidadeOrganizacional);
		ordemServicoUnidade.setUsuario(usuario);
		AtendimentoRelacaoTipo atrt = new AtendimentoRelacaoTipo();
		atrt.setId(AtendimentoRelacaoTipo.ABRIR_REGISTRAR);
		ordemServicoUnidade.setAtendimentoRelacaoTipo(atrt);
		ordemServicoUnidade.setUltimaAlteracao(calendar.getTime());

		getControladorUtil().inserir(ordemServicoUnidade);

		return idOrdemServico;
	}

	public ServicoTipo recuperaServicoTipoPorConstante(Integer codigoConstate) throws ControladorException {

		ServicoTipo retorno = null;

		try {
			Integer servitoId = repositorioOrdemServico.recuperaServicoTipoPorConstante(codigoConstate);

			// if(servitoId==null){
			// throw new
			// ControladorException("atencao.servico_tipo_nao_existe");
			// }

			FiltroServicoTipo filtro = new FiltroServicoTipo();

			filtro.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, servitoId));

			Collection colecaoServico = this.getControladorUtil().pesquisar(filtro, ServicoTipo.class.getName());

			retorno = (ServicoTipo) Util.retonarObjetoDeColecao(colecaoServico);

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;

	}

	/**
	 * Pesquisa quantidade Ordens em Processo de Repavimentação
	 * 
	 * [UC0639] Filtrar Ordens em Processo de Repavimetação.
	 * 
	 * @author Arthur Carvalho
	 * @date 02/06/2008
	 * 
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarOrdemProcessoRepavimentacaoCount(OSPavimentoHelper oSPavimentoHelper) throws ControladorException {

		Integer count = null;

		try {

			count = repositorioOrdemServico.pesquisarOrdemProcessoRepavimentacaoCount(oSPavimentoHelper);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return count;
	}

	/**
	 * [UC0457] Encerrar Ordem de Serviço [SB0006] – Obter Unidade
	 * Repavimentadora do Município
	 * 
	 * @author Arthur Carvalho
	 * @date 12/04/2010
	 * 
	 */
	public UnidadeOrganizacional obterUnidadeRepavimentadorAPartirMunicipio(OrdemServico os, String tipoPesquisa) throws ControladorException {

		UnidadeOrganizacional unidadeOrganizacional = null;

		try {

			unidadeOrganizacional = repositorioOrdemServico.obterUnidadeRepavimentadorAPartirMunicipio(os, tipoPesquisa);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return unidadeOrganizacional;
	}

	/**
	 * @author Arthur Carvalho
	 * @date 12/04/2010 [UC0457] Encerrar Ordem de Serviço [FS0011 – Verificar
	 *       existência da unidade repavimentadora];
	 * @param idUnidadeRepavimentadora
	 */
	public void verificaUnidadeTipoRepavimentadora(String idUnidadeRepavimentadora) throws ControladorException {

		FiltroUnidadeOrganizacional filtro = new FiltroUnidadeOrganizacional();
		filtro.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, idUnidadeRepavimentadora));
		filtro.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeOrganizacional.UNIDADE_TIPO);

		Collection colecaoUnidadeOrgazanicional = getControladorUtil().pesquisar(filtro, UnidadeOrganizacional.class.getName());

		if (colecaoUnidadeOrgazanicional != null && !colecaoUnidadeOrgazanicional.isEmpty()) {

			UnidadeOrganizacional unidade = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrgazanicional);

			// verifica unidade informada
			if (!unidade.getUnidadeTipo().getCodigoTipo().equals("R")) {
				throw new ControladorException("atencao.unidade_nao_e_tipo_repavimentadora");
			}
		}
	}

	/**
	 * Pesquisa quantidade Ordens de Repavimentação em Processo de Aceite.
	 * 
	 * [UC1019] Filtrar Ordens de Repavimetação em Processo de Aceite.
	 * 
	 * @author Hugo Leonardo.
	 * @date 17/05/2010
	 * 
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarOrdemProcessoRepavimentacaoAceiteCount(OrdemRepavimentacaoProcessoAceiteHelper oSPavimentoAceiteHelper) throws ControladorException {

		Integer count = null;

		try {

			count = repositorioOrdemServico.pesquisarOrdemProcessoRepavimentacaoAceiteCount(oSPavimentoAceiteHelper);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return count;
	}

	/**
	 * Pesquisa Ordens de Repavimentação em Processo de Aceite.
	 * 
	 * [UC1019] Filtrar Ordens de Repavimetação em Processo de Aceite.
	 * 
	 * @author Hugo Leonardo
	 * @date 17/05/2010
	 * 
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemRepavimentacaoProcessoAceite(OrdemRepavimentacaoProcessoAceiteHelper oSPavimentoHelper, Integer numeroPagina)
			throws ControladorException {

		Collection collHelper = new ArrayList();

		try {
			Collection collOrdemServicoPavimento = repositorioOrdemServico.pesquisarOrdemRepavimentacaoProcessoAceite(oSPavimentoHelper, numeroPagina);

			Iterator it = collOrdemServicoPavimento.iterator();
			OSPavimentoRetornoHelper osPavimentoRetornoHelper = null;

			while (it.hasNext()) {
				OrdemServicoPavimento ordemServicoPavimento = (OrdemServicoPavimento) it.next();
				if (ordemServicoPavimento.getOrdemServico() != null) {

					osPavimentoRetornoHelper = new OSPavimentoRetornoHelper();
					Imovel imovel = ordemServicoPavimento.getOrdemServico().getImovel();
					if (imovel != null) {
						String[] endereco = getControladorEndereco().pesquisarEnderecoFormatadoDividido(imovel.getId());
						osPavimentoRetornoHelper.setEndereco(endereco[3] + "-" + endereco[0]);
					} else {
						RegistroAtendimento ra = ordemServicoPavimento.getOrdemServico().getRegistroAtendimento();

						String endereco = getControladorEndereco().pesquisarEnderecoRegistroAtendimentoFormatado(ra.getId());
						osPavimentoRetornoHelper.setEndereco(endereco);

					}

					if (ordemServicoPavimento.getDescricaoMotivoAceite() != null && !ordemServicoPavimento.getDescricaoMotivoAceite().equals("")) {
						if (ordemServicoPavimento.getDescricaoMotivoAceite().length() > 10) {
							osPavimentoRetornoHelper.setMotivo(ordemServicoPavimento.getDescricaoMotivoAceite().substring(0, 10));
						} else {
							osPavimentoRetornoHelper.setMotivo(ordemServicoPavimento.getDescricaoMotivoAceite());
						}

					}
					osPavimentoRetornoHelper.setOrdemServicoPavimento(ordemServicoPavimento);
					osPavimentoRetornoHelper.setOrdemServico(ordemServicoPavimento.getOrdemServico());

					collHelper.add(osPavimentoRetornoHelper);
				}
			}

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return collHelper;
	}

	/**
	 * [UC1020] - Exibir Ordens de Repavimentação em Processo de Aceite.
	 * 
	 * Verificar se existe Ordem de Repavimentação em Aceite entre as Ordens
	 * selecionadas.
	 * 
	 * @author Hugo Leonardo
	 * @param Collection
	 * @throws ControladorException
	 * @data 21/05/2010
	 */
	public void verificarExistenciaRepavimentacaoAceite(Collection colecaoOSPavimentoSelecionados) throws ControladorException {

		boolean retorno = false;

		Iterator it = colecaoOSPavimentoSelecionados.iterator();
		OrdemServicoPavimento ordemServicoPavimento = new OrdemServicoPavimento();

		while (it.hasNext()) {

			ordemServicoPavimento = (OrdemServicoPavimento) it.next();

			if (ordemServicoPavimento.getIndicadorAceite() != null && ordemServicoPavimento.getIndicadorAceite().toString().equals("1")) {

				retorno = true;
			}
		}

		if (retorno) {
			throw new ControladorException("atencao.os.repavimentacao.aceite_selecionada", null, "");
		}
	}

	/**
	 * [UC1020] - Exibir Ordens de Repavimentação em Processo de Aceite.
	 * 
	 * Aceitar as Ordens de Serviço em Processo de Repavimentacao Convergente.
	 * 
	 * @author Hugo Leonardo
	 * @param OrdemRepavimentacaoProcessoAceiteHelper
	 * @throws ControladorException
	 * @data 24/05/2010
	 */
	public void aceitarOSRepavimentacaoConvergente(OrdemRepavimentacaoProcessoAceiteHelper osPavimentoAceiteHelper, Usuario usuario, Date dataAceite,
			Short indicadorSituacaoAceite) throws ControladorException {

		Collection colecaoOSPavimentoSelecionados = this.pesquisarOrdemRepavimentacaoProcessoAceite(osPavimentoAceiteHelper, null);

		Iterator it = colecaoOSPavimentoSelecionados.iterator();
		OrdemServicoPavimento ordemServicoPavimento = new OrdemServicoPavimento();
		OSPavimentoRetornoHelper osPavimentoHelper = new OSPavimentoRetornoHelper();

		while (it.hasNext()) {

			osPavimentoHelper = (OSPavimentoRetornoHelper) it.next();

			if (osPavimentoHelper.getOrdemServicoPavimento().getAreaPavimentoRua()
					.equals(osPavimentoHelper.getOrdemServicoPavimento().getAreaPavimentoRuaRetorno())
					&& osPavimentoHelper.getOrdemServicoPavimento().getPavimentoRua().getId()
							.equals(osPavimentoHelper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getId())) {

				Integer id = osPavimentoHelper.getOrdemServico().getId();
				OrdemServico os = this.pesquisarOrdemServico(id);

				if (osPavimentoHelper.getOrdemServico().getId().equals(os.getId())) {

					ordemServicoPavimento = osPavimentoHelper.getOrdemServicoPavimento();

					ordemServicoPavimento.setIndicadorAceite(indicadorSituacaoAceite);
					ordemServicoPavimento.setDataAceite(dataAceite);
					ordemServicoPavimento.setUsuarioAceite(usuario);

					this.getControladorUtil().atualizar(ordemServicoPavimento);
				}
			}
		}
	}

	/**
	 * [UC0430] - Gerar Ordem de Serviço
	 * 
	 * @author Hugo Amorim
	 * @date 12/02/2010
	 */
	public Integer gerarOrdemServicoCorte(OrdemServico ordemServico, Usuario usuario) throws ControladorException {

		Calendar calendar = Calendar.getInstance();

		ServicoTipo servicoTipo = this.recuperaServicoTipoPorConstanteServicoTipoSeletivo(ServicoTipoSeletiva.CORTE_SELETIVO);

		if (servicoTipo == null) {
			throw new ControladorException("atencao.servico_tipo_nao_existe");
		}
		/*
		 * FiltroServicoTipoSeletiva filtro = new FiltroServicoTipoSeletiva();
		 * 
		 * filtro.adicionarParametro(new
		 * ParametroSimples(FiltroServicoTipoSeletiva.CONSTANTE,
		 * ServicoTipoSeletiva.CORTE_SELETIVO));
		 * 
		 * filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroServicoTipoSeletiva
		 * .SERVICO_TIPO);
		 * 
		 * Collection colecaoServico =
		 * this.getControladorUtil().pesquisar(filtro,
		 * ServicoTipoSeletiva.class.getName());
		 * 
		 * ServicoTipoSeletiva servicoTipoSeletiva = (ServicoTipoSeletiva)
		 * Util.retonarObjetoDeColecao(colecaoServico);
		 * 
		 * if(servicoTipoSeletiva!=null &&
		 * servicoTipoSeletiva.getServicoTipo()!=null){ servicoTipo =
		 * servicoTipoSeletiva.getServicoTipo(); }else{ throw new
		 * ControladorException("atencao.servico_tipo_nao_existe"); }
		 */

		ordemServico.setServicoTipo(servicoTipo);
		ordemServico.setAtendimentoMotivoEncerramento(null);
		ordemServico.setOsReferidaRetornoTipo(null);
		ordemServico.setSituacao(OrdemServico.SITUACAO_PENDENTE);
		ordemServico.setDataGeracao(new Date());
		ordemServico.setDataEmissao(null);
		ordemServico.setDataEncerramento(null);
		ordemServico.setDescricaoParecerEncerramento(null);
		ordemServico.setAreaPavimento(null);
		ordemServico.setIndicadorComercialAtualizado(ConstantesSistema.NAO);
		ordemServico.setServicoNaoCobrancaMotivo(null);
		ordemServico.setPercentualCobranca(null);
		ordemServico.setFiscalizacaoColetiva(null);
		ordemServico.setIndicadorServicoDiagnosticado(ConstantesSistema.NAO);
		ordemServico.setUltimaAlteracao(calendar.getTime());
		ordemServico.setValorOriginal(servicoTipo.getValor());
		ordemServico.setServicoTipoPrioridadeOriginal(servicoTipo.getServicoTipoPrioridade());
		ordemServico.setServicoTipoPrioridadeAtual(servicoTipo.getServicoTipoPrioridade());
		ordemServico.setIndicadorProgramada(OrdemServico.NAO_PROGRAMADA);
		ordemServico.setIndicadorEncerramentoAutomatico(ConstantesSistema.NAO);
		ordemServico.setObservacao("Ordem de Corte Seletiva gerada especificamente para o imóvel.");
		ordemServico.setIndicadorBoletim(ConstantesSistema.NAO);

		FiltroUnidadeOrganizacional filtroUnidade = new FiltroUnidadeOrganizacional();

		filtroUnidade.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID_LOCALIDADE, ordemServico.getImovel().getLocalidade().getId()));

		Collection colecaoUnidade = this.getControladorUtil().pesquisar(filtroUnidade, UnidadeOrganizacional.class.getName());

		UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidade);

		ordemServico.setUnidadeAtual(unidadeOrganizacional);

		Integer idOrdemServico = (Integer) getControladorUtil().inserir(ordemServico);

		OrdemServicoUnidade ordemServicoUnidade = new OrdemServicoUnidade();

		ordemServicoUnidade.setOrdemServico(ordemServico);
		ordemServicoUnidade.setUnidadeOrganizacional(unidadeOrganizacional);
		ordemServicoUnidade.setUsuario(usuario);
		AtendimentoRelacaoTipo atrt = new AtendimentoRelacaoTipo();
		atrt.setId(AtendimentoRelacaoTipo.ABRIR_REGISTRAR);
		ordemServicoUnidade.setAtendimentoRelacaoTipo(atrt);
		ordemServicoUnidade.setUltimaAlteracao(calendar.getTime());

		getControladorUtil().inserir(ordemServicoUnidade);

		return idOrdemServico;
	}

	/**
	 * Pesquisar Serviço Tipo Seletivo
	 * 
	 * Seleciona Serviço Tipo Seletivo por codigo da constante
	 * 
	 * @author Hugo Amorim
	 * @date 26/07/2010
	 * 
	 */
	public ServicoTipo recuperaServicoTipoPorConstanteServicoTipoSeletivo(Integer codigoConstate) throws ControladorException {

		ServicoTipo retorno = null;

		try {
			Integer servitoId = repositorioOrdemServico.recuperaServicoTipoSeletivoPorConstante(codigoConstate);

			FiltroServicoTipo filtro = new FiltroServicoTipo();

			filtro.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, servitoId));

			Collection colecaoServico = this.getControladorUtil().pesquisar(filtro, ServicoTipo.class.getName());

			retorno = (ServicoTipo) Util.retonarObjetoDeColecao(colecaoServico);

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;

	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * Seleciona OSFS_DTFISCALIZACAOSITUACAO da tabela ORDEM_SERVICO_FISC_SIT
	 * para ORSE_ID=ORSE_ID da tabela ORDEM_SERVICO
	 * 
	 * @author Vivianne Sousa
	 * @date 28/07/2010
	 * 
	 */
	public Date recuperaDataFiscalizacaoSituacao(Integer idOrdemServico, Integer idFiscalizacaoSituacao) throws ControladorException {
		try {

			return repositorioOrdemServico.recuperaDataFiscalizacaoSituacao(idOrdemServico, idFiscalizacaoSituacao);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 29/07/2010
	 */
	public OrdemServicoFiscSit recuperaOrdemServicoFiscSit(Integer idOrdemServico, Integer idFiscalizacaoSituacao) throws ControladorException {
		try {

			return repositorioOrdemServico.recuperaOrdemServicoFiscSit(idOrdemServico, idFiscalizacaoSituacao);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 30/07/2010
	 */
	public OrdemServico recuperaOrdemServico(Integer idOrdemServico) throws ControladorException {
		try {

			return repositorioOrdemServico.recuperaOrdemServico(idOrdemServico);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 02/08/2010
	 */
	public Collection recuperaFiscalizacaoSituacao(Integer idOrdemServico) throws ControladorException {
		try {

			return repositorioOrdemServico.recuperaFiscalizacaoSituacao(idOrdemServico);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 03/08/2010
	 */
	public Collection recuperaFiscalizacaoSituacaoSelecionada(Integer idOrdemServico) throws ControladorException {
		try {

			return repositorioOrdemServico.recuperaFiscalizacaoSituacaoSelecionada(idOrdemServico);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 09/08/2010
	 */
	public Collection recuperaOrdemServicoFiscSit(Integer idOrdemServico) throws ControladorException {
		try {

			return repositorioOrdemServico.recuperaOrdemServicoFiscSit(idOrdemServico);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização [SB0003] –
	 * Calcular/Inserir Valor
	 * 
	 * @author Vivianne Sousa
	 * @date 18/08/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorDebitoOS(Integer indicadorDebito, Integer idFiscalizacaoSituacao, Integer idOS) throws ControladorException {
		try {

			repositorioOrdemServico.atualizarIndicadorDebitoOS(indicadorDebito, idFiscalizacaoSituacao, idOS);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * @author Vivianne Sousa
	 * @date 30/08/2010
	 * 
	 */
	public OrdemServicoFiscSit recuperaOrdemServicoFiscSitComMenorDataFiscalizacao(Integer idOrdemServico) throws ControladorException {
		try {

			return repositorioOrdemServico.recuperaOrdemServicoFiscSitComMenorDataFiscalizacao(idOrdemServico);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0441] Consultar Dados da Ordem de Serviço
	 * 
	 * @author Vivianne Sousa
	 * @date 01/09/2010
	 */
	public Collection pesquisaOrdemServicoFiscSit(Integer idOrdemServico) throws ControladorException {
		try {

			return repositorioOrdemServico.pesquisaOrdemServicoFiscSit(idOrdemServico);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 19/08/2010
	 */
	public Short recuperaIndicadorDebitoDaOrdemServicoFiscSit(Integer idOrdemServico, Integer idFiscalizacaoSituacao) throws ControladorException {
		try {

			return repositorioOrdemServico.recuperaIndicadorDebitoDaOrdemServicoFiscSit(idOrdemServico, idFiscalizacaoSituacao);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Filtrar as Ordens de Serviço dos Comandos de Ação de Cobrança
	 * 
	 * [UC1098] Informar Não Aceitação de Motivo de Encerramento Ordem de
	 * Serviço
	 * 
	 * @author Mariana Victor
	 * @date 13/12/2010
	 * 
	 * @return filtroCobrancaAcao
	 * @throws ControladorException
	 */
	public FiltroOrdemServico construirFiltroOrdemServico(String grupoCobranca, String acaoCobranca, String anoMesPeriodoReferenciaContasInicial,
			String anoMesPeriodoReferenciaContasFinal) throws ControladorException {

		FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();

		filtroOrdemServico.adicionarParametro(new ParametroSimples(FiltroOrdemServico.SITUACAO, ConstantesSistema.SITUACAO_REFERENCIA_ENCERRADA));
		filtroOrdemServico.adicionarParametro(new ParametroSimples(FiltroOrdemServico.ATENDIMENTO_MOTIVO_ENCERRAMENTO_IC_EXECUCAO,
				ConstantesSistema.INDICADOR_NAO_EXECUCAO));

		filtroOrdemServico.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroOrdemServico.ATENDIMENTO_MOTIVO_ENCERRAMENTO_ID,
				AtendimentoMotivoEncerramento.CANCELADO_POR_DERCURSO_DE_PRAZO));

		filtroOrdemServico.adicionarParametro(new ParametroSimples(FiltroOrdemServico.COBRANCA_GRUPO_ID, grupoCobranca));
		filtroOrdemServico.adicionarParametro(new ParametroSimples(FiltroOrdemServico.COBRANCA_ACAO_ID, acaoCobranca));

		// validar Período de Referência das Contas
		// [FS0001] - Validar Referência
		// [FS0002] - Verificar referência final menor que referência inicial
		this.getControladorUtil().validarAnoMesInicialFinalPeriodo(anoMesPeriodoReferenciaContasInicial, anoMesPeriodoReferenciaContasFinal,
				"Período de Referência das Contas Inicial", "Período de Referência das Contas Final", "atencao.referencia.final.menor.referencia.inicial");

		// Período de Referência da Cobrança
		if ((anoMesPeriodoReferenciaContasInicial != null && !anoMesPeriodoReferenciaContasInicial.equals(""))
				&& (anoMesPeriodoReferenciaContasFinal != null && !anoMesPeriodoReferenciaContasFinal.equals(""))) {

			String anoInicial = anoMesPeriodoReferenciaContasInicial.substring(3, 7);
			String mesInicial = anoMesPeriodoReferenciaContasInicial.substring(0, 2);

			String anoFinal = anoMesPeriodoReferenciaContasFinal.substring(3, 7);
			String mesFinal = anoMesPeriodoReferenciaContasFinal.substring(0, 2);

			filtroOrdemServico.adicionarParametro(new Intervalo(FiltroOrdemServico.COBRANCA_GRUPO_CRONOGRAMA_MES_MES_ANO, anoInicial + mesInicial, anoFinal
					+ mesFinal));

		}

		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.SERVICO_TIPO);
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.ATENDIMENTO_MOTIVO_ENCERRAMENTO);
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.COBRANCA_DOCUMENTO);
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.COBRANCA_ACAO);
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.COBRANCA_GRUPO);
		filtroOrdemServico.setCampoOrderBy(FiltroOrdemServico.ID);

		return filtroOrdemServico;
	}

	/**
	 * [UC1098] Informar Não Aceitação de Motivo de Encerramento Ordem de
	 * Serviço
	 * 
	 * @author Mariana Victor
	 * @date 23/12/2010
	 */
	public OrdemServico pesquisarOS(Integer id) throws ControladorException {

		FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();

		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.SERVICO_TIPO);
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.ATENDIMENTO_MOTIVO_ENCERRAMENTO);
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.COBRANCA_DOCUMENTO);
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.COBRANCA_ACAO);
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.COBRANCA_GRUPO);

		filtroOrdemServico.adicionarParametro(new ParametroSimples(FiltroOrdemServico.ID, id.toString()));

		return pesquisar(filtroOrdemServico, OrdemServico.class);
	}

	/**
	 * [UC1110] Gerar Boletim de Custo de Repavimentação por Tipo de Pavimento
	 * 
	 * @author Hugo Leonardo
	 * @date 03/01/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioBoletimCustoPavimento(FiltrarBoletimCustoPavimentoHelper relatorioHelper) throws ControladorException {

		Collection collHelper = new ArrayList();

		try {

			// [FS0004] Nenhum registro encontrado.
			// this.verificaDadosGeracaoBoletimCustoRepavimentacao(relatorioHelper);

			Collection collOrdemServicoPavimento = repositorioOrdemServico.pesquisarRelatorioBoletimCustoPavimento(relatorioHelper);

			if (!Util.isVazioOrNulo(collOrdemServicoPavimento)) {

				// Analítico
				if (relatorioHelper.getIndicadorTipoBoletim() != null && relatorioHelper.getIndicadorTipoBoletim().equals("1")) {

					Iterator it = collOrdemServicoPavimento.iterator();
					RelatorioBoletimCustoPavimentoHelper boletimCustoPavimentoHelper = null;

					while (it.hasNext()) {

						OrdemServicoPavimento ordemServicoPavimento = (OrdemServicoPavimento) it.next();
						String endereco = null;

						if (ordemServicoPavimento.getOrdemServico() != null) {

							boletimCustoPavimentoHelper = new RelatorioBoletimCustoPavimentoHelper();

							Imovel imovel = ordemServicoPavimento.getOrdemServico().getImovel();

							if (imovel != null) {

								endereco = getControladorEndereco().pesquisarEnderecoFormatado(imovel.getId());
								boletimCustoPavimentoHelper.setEndereco(endereco);
							} else {

								RegistroAtendimento ra = ordemServicoPavimento.getOrdemServico().getRegistroAtendimento();
								Collection collRaso = getControladorRegistroAtendimento().obterRASolicitante(ra.getId());
								RegistroAtendimentoSolicitante raso = null;
								Iterator itt = collRaso.iterator();

								while (itt.hasNext()) {
									raso = (RegistroAtendimentoSolicitante) itt.next();
								}

								endereco = getControladorEndereco().pesquisarBairroLogradouroRegistroAtendimentoSolicitante(raso.getID());
								boletimCustoPavimentoHelper.setEndereco(endereco);
							}

							boletimCustoPavimentoHelper.setOrdemServicoPavimento(ordemServicoPavimento);
							boletimCustoPavimentoHelper.setOrdemServico(ordemServicoPavimento.getOrdemServico());

							collHelper.add(boletimCustoPavimentoHelper);
						}
					}
				}
			} else {

				throw new ControladorException("atencao.pesquisa.nenhumresultado");
			}

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return collHelper;
	}

	/**
	 * [UC1109] Filtrar Dados para Geração Boletim de Custo de Repavimentação
	 * 
	 * [FS0004] - Nenhum registro encontrado.
	 * 
	 * @author Hugo Leonardo
	 * @date 03/01/2011
	 * 
	 * @return void
	 */
	public void verificaDadosGeracaoBoletimCustoRepavimentacao(FiltrarBoletimCustoPavimentoHelper relatorioHelper) throws ControladorException {

		try {
			if (this.repositorioOrdemServico.verificaDadosGeracaoBoletimCustoRepavimentacao(relatorioHelper)) {

				throw new ControladorException("atencao.pesquisa.nenhumresultado");
			}

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1110] Gerar Boletim de Custo de Repavimentação por Tipo de Pavimento
	 * 
	 * @author Hugo Leonardo
	 * @date 04/01/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarBoletimCustoPavimentoPorTipoPavimentoRua(FiltrarBoletimCustoPavimentoHelper relatorioHelper) throws ControladorException {

		Collection coll = new ArrayList();

		try {
			coll = this.repositorioOrdemServico.pesquisarBoletimCustoPavimentoPorTipoPavimentoRua(relatorioHelper);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return coll;
	}

	/**
	 * [UC1110] Gerar Boletim de Custo de Repavimentação por Tipo de Pavimento
	 * 
	 * @author Hugo Leonardo
	 * @date 04/01/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarBoletimCustoPavimentoPorTipoPavimentoRuaRet(FiltrarBoletimCustoPavimentoHelper relatorioHelper) throws ControladorException {

		Collection coll = new ArrayList();

		try {
			coll = this.repositorioOrdemServico.pesquisarBoletimCustoPavimentoPorTipoPavimentoRuaRet(relatorioHelper);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return coll;
	}

	/**
	 * [UC1110] Gerar Boletim de Custo de Repavimentação por Tipo de Pavimento
	 * 
	 * @author Hugo Leonardo
	 * @date 10/01/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarTotaisPorTipoPavimentoRua(FiltrarBoletimCustoPavimentoHelper relatorioHelper) throws ControladorException {

		Collection coll = new ArrayList();
		Collection collRetorno = new ArrayList();

		try {

			coll = this.repositorioOrdemServico.pesquisarTotaisPorTipoPavimentoRuaDemandadas(relatorioHelper);

			if (!Util.isVazioOrNulo(coll)) {

				collRetorno.addAll(coll);
			}

			coll = this.repositorioOrdemServico.pesquisarTotaisPorTipoPavimentoRuaDemandadas3Meses(relatorioHelper);

			if (!Util.isVazioOrNulo(coll)) {

				collRetorno.addAll(coll);
			}

			coll = this.repositorioOrdemServico.pesquisarTotaisPorTipoPavimentoRuaAceitas(relatorioHelper);

			if (!Util.isVazioOrNulo(coll)) {

				collRetorno.addAll(coll);
			}

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return collRetorno;
	}

	/**
	 * [UC0457] Encerrar Ordem de Serviço [SB0007]- Gerar Informações para
	 * Boletim de Medição.
	 * 
	 * @author Vivianne Sousa
	 * @created 18/01/2011
	 */
	public void gerarInformacoesBoletimMedicao(Integer idOrdemServico) throws ControladorException {

		try {

			ServicoTipo servicoTipo = this.repositorioOrdemServico.recuperaServicoTipoDaOrdemServico(idOrdemServico);

			if (servicoTipo.getIndicadorBoletim().equals(ConstantesSistema.SIM)) {
				// Caso o indicador do serviço da ordem de serviço
				// que está sendo encerrada tenha indicador para obter
				// as informações para geração do boletim de medição

				ServicoTipoBoletim servicoTipoBoletim = this.repositorioOrdemServico.recuperaServicoTipoBoletimDoServicoTipo(servicoTipo.getId());

				if (servicoTipoBoletim != null) {
					// 1.1.Caso o indicador de pavimento esteja solicitando
					// a informação da existência de pavimento
					if (servicoTipoBoletim.getIndicadorPavimento().equals(ConstantesSistema.SIM)) {
						// 1.1.1.O sistema deverá solicitar a informação
						// de existência do pavimento (Sim ou Não,
						// obrigatoriamente)

					}

					// 1.2.Caso o indicador de quantidade de reposição em m²
					// de asfalto esteja solicitando a informação do valor
					if (servicoTipoBoletim.getIndicadorReposicaoAsfalto().equals(ConstantesSistema.SIM)) {
						// 1.2.1.O sistema deverá solicitar a informação da
						// quantidade de reposição em m² de asfalto.
						// [FS0011 – Validar a quantidade m²]

					}

					// 1.3.Caso o indicador de quantidade de reposição em m²
					// de paralelo esteja solicitando a informação do valor
					if (servicoTipoBoletim.getIndicadorReposicaoParalelo().equals(ConstantesSistema.SIM)) {
						// 1.3.1.O sistema deverá solicitar a informação da
						// quantidade de reposição em m² de asfalto.
						// [FS0011 – Validar a quantidade m²]

					}

					// 1.4.Caso o indicador de quantidade de reposição em m²
					// de calçada esteja solicitando a informação do valor
					if (servicoTipoBoletim.getIndicadorReposicaoCalcada().equals(ConstantesSistema.SIM)) {
						// 1.4.1.O sistema deverá solicitar a informação da
						// quantidade de reposição em m² de calçada.
						// [FS0011 – Validar a quantidade m²]

					}

				}

			}

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0457] Encerrar Ordem de Serviço
	 * 
	 * @author Vivianne Sousa
	 * @date 18/01/2011
	 */
	public ServicoTipo recuperaServicoTipoDaOrdemServico(Integer idOrdemServico) throws ControladorException {
		try {

			return repositorioOrdemServico.recuperaServicoTipoDaOrdemServico(idOrdemServico);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0457] Encerrar Ordem de Serviço
	 * 
	 * @author Vivianne Sousa
	 * @date 21/01/2011
	 */
	public ServicoTipoBoletim recuperaServicoTipoBoletimDoServicoTipo(Integer idServicoTipo) throws ControladorException {
		try {

			return repositorioOrdemServico.recuperaServicoTipoBoletimDoServicoTipo(idServicoTipo);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1116] Atualizar Informações da OS para Boletim de Medição
	 * 
	 * @author Vivianne Sousa
	 * @date 02/02/2011
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public OrdemServico recuperaOSEDadosImovel(Integer idOS) throws ControladorException {
		try {

			return repositorioOrdemServico.recuperaOSEDadosImovel(idOS);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1116] Atualizar Informações da OS para Boletim de Medição
	 * 
	 * @author Vivianne Sousa
	 * @date 02/02/2011
	 */
	public OrdemServicoBoletim recuperaOrdemServicoBoletimDaOS(Integer idOrdemServico) throws ControladorException {
		try {

			return repositorioOrdemServico.recuperaOrdemServicoBoletimDaOS(idOrdemServico);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1163] Gerar Relatório de OS executadas por Prestadora de Serviço
	 * 
	 * @author Vivianne Sousa
	 * @date 13/04/2011
	 */
	public Collection recuperaOSExecutadas(Date dataInicial, Date dataFinal, Integer idGerencia, Integer idUnidade, Integer idLocalidade, String[] idsRegiao,
			String[] idsMicroregiao, String[] idsMunicipio) throws ControladorException {
		try {

			Collection colecaoRetorno = null;

			Collection colecaoDadosOS = repositorioOrdemServico.recuperaOSExecutadas(dataInicial, dataFinal, idGerencia, idUnidade, idLocalidade, idsRegiao,
					idsMicroregiao, idsMunicipio);

			if (colecaoDadosOS != null && !colecaoDadosOS.isEmpty()) {

				colecaoRetorno = new ArrayList();
				Iterator iter = colecaoDadosOS.iterator();

				while (iter.hasNext()) {

					Object[] objeto = (Object[]) iter.next();
					OSExecutadasRelatorioHelper helper = new OSExecutadasRelatorioHelper();

					if (objeto[0] != null) {
						helper.setNumeroOS((String) objeto[0]);
					}
					if (objeto[1] != null) {
						helper.setCodigoServico((String) objeto[1]);
					} else {
						helper.setCodigoServico("0");
					}
					if (objeto[2] != null) {
						helper.setDescServico((String) objeto[2]);
					} else {
						helper.setDescServico("SERVIÇO SEM DESCRIÇÃO");
					}
					if (objeto[3] != null) {
						helper.setDescTipoPavimento((String) objeto[3]);
					} else {
						helper.setDescTipoPavimento("");
					}
					if (objeto[4] != null) {
						helper.setMaterialrede((String) objeto[4]);
					} else {
						helper.setMaterialrede("");
					}
					if (objeto[5] != null) {
						helper.setDiametroRede((String) objeto[5]);
					} else {
						helper.setDiametroRede("");
					}
					if (objeto[6] != null) {
						helper.setDataConclusao((Date) objeto[6]);
					}
					if (objeto[7] != null) {
						helper.setCodigoExcedente((String) objeto[7]);
					} else {
						helper.setCodigoExcedente("");
					}

					if (objeto[8] != null) {
						helper.setDescMaterial((String) objeto[8]);
						helper.setQtdeExcedente("0");
					} else {
						helper.setDescMaterial("SEM SERVIÇO EXCEDENTE");
						helper.setQtdeExcedente("");
					}

					// if(objeto[8] != null){
					// helper.setCodigoMaterial((String)objeto[8]);
					// }else{
					// helper.setCodigoMaterial("");
					// }
					// if(objeto[9] != null){
					// helper.setDescMaterial((String)objeto[9]);
					// }else{
					// helper.setDescMaterial("");
					// }
					// if(objeto[10] != null){
					// helper.setQtdeExcedente((String)objeto[10]);
					// }else{
					// helper.setQtdeExcedente("");
					// }

					if (objeto[9] != null) {
						helper.setProfundRede((String) objeto[9]);
					} else {
						helper.setProfundRede("");
					}
					if (objeto[10] != null) {
						helper.setDimenBuraco((String) objeto[10]);
					} else {
						helper.setDimenBuraco("");
					}
					if (objeto[11] != null) {
						helper.setIdLocalidade((Integer) objeto[11]);
					}
					if (objeto[12] != null) {
						helper.setNomeLocalidade((String) objeto[12]);
					}
					if (objeto[13] != null) {
						// id da RA
						String enderecoRA = getControladorRegistroAtendimento().obterEnderecoOcorrenciaRA((Integer) objeto[13]);
						helper.setEnderecoRA(enderecoRA);
					}
					if (objeto[14] != null) {
						helper.setNomeUnidade((String) objeto[14]);
					}
					if (objeto[15] != null) {
						helper.setNomeGerencia((String) objeto[15]);
					}
					colecaoRetorno.add(helper);
				}

			}

			return colecaoRetorno;

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1163] Gerar Relatório de OS executadas por Prestadora de Serviço
	 * 
	 * @author Vivianne Sousa
	 * @date 18/04/2011
	 */
	public Collection recuperaTotalServicoOSExecutadas(Date dataInicial, Date dataFinal, Integer idLocalidade) throws ControladorException {
		try {

			Collection colecaoRetorno = null;

			Collection colecaoDadosOS = repositorioOrdemServico.recuperaTotalServicoOSExecutadas(dataInicial, dataFinal, idLocalidade);

			if (colecaoDadosOS != null && !colecaoDadosOS.isEmpty()) {

				colecaoRetorno = new ArrayList();
				Iterator iter = colecaoDadosOS.iterator();

				while (iter.hasNext()) {

					Object[] objeto = (Object[]) iter.next();

					Integer qtdeOS = (Integer) objeto[0];
					String codigoServico = "";
					if (objeto[1] != null) {
						codigoServico = (String) objeto[1];
					} else {
						codigoServico = "0";
					}
					String descServico = "";
					if (objeto[2] != null) {
						descServico = (String) objeto[2];
					} else {
						descServico = "SERVIÇO SEM DESCRIÇÃO";
					}

					OSExecutadasRelatorioHelper helper = new OSExecutadasRelatorioHelper(qtdeOS, codigoServico, descServico);
					colecaoRetorno.add(helper);
				}

			}

			return colecaoRetorno;

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1163] Gerar Relatório de OS executadas por Prestadora de Serviço
	 * [SB0003] – Gerar Analítico TXT
	 * 
	 * @author Vivianne Sousa
	 * @date 18/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void gerarTxtOSExecutadasPrestadoraServico(Date dataInicial, Date dataFinal, Integer idGerencia, Integer idUnidade, Integer idLocalidade,
			String[] idsRegiao, String[] idsMicroregiao, String[] idsMunicipio, String header) throws ControladorException {

		BufferedWriter out = null;
		ZipOutputStream zos = null;
		File leitura = null;
		File compactado = null;
		String nomeZip = "OS_ENCERRADAS_PONTOFORTE";

		try {

			Collection colecaoOsExecutadas = recuperaOSExecutadas(dataInicial, dataFinal, idGerencia, idUnidade, idLocalidade, idsRegiao, idsMicroregiao,
					idsMunicipio);

			if (colecaoOsExecutadas != null && !colecaoOsExecutadas.isEmpty()) {

				// Definindo arquivo para escrita
				compactado = new File(nomeZip + ".zip");
				leitura = new File(nomeZip + ".txt");

				zos = new ZipOutputStream(new FileOutputStream(compactado));
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leitura.getAbsolutePath())));

				// pegar o arquivo, zipar pasta e arquivo e escrever no stream
				System.out.println("***************************************");
				System.out.println("INICO DA CRIACAO DO ARQUIVO");
				System.out.println("***************************************");

				// HEADER
				StringBuilder headerTxt = new StringBuilder();
				headerTxt.append(header);
				headerTxt.append(System.getProperty("line.separator"));
				out.write(headerTxt.toString());

				Iterator iterHelper = colecaoOsExecutadas.iterator();
				while (iterHelper.hasNext()) {
					OSExecutadasRelatorioHelper helper = (OSExecutadasRelatorioHelper) iterHelper.next();

					StringBuilder analiticoTxt = new StringBuilder();
					analiticoTxt.append(helper.getNumeroOS().trim() + ";");
					analiticoTxt.append(helper.getCodigoServico().trim() + ";");
					analiticoTxt.append(helper.getDescServico().trim() + ";");
					analiticoTxt.append(helper.getDescTipoPavimento().trim() + ";");
					analiticoTxt.append(helper.getMaterialrede().trim() + ";");
					analiticoTxt.append(helper.getDiametroRede().trim() + ";");
					analiticoTxt.append(helper.getProfundRede().trim() + ";");
					analiticoTxt.append(helper.getDimenBuraco().trim() + ";");
					analiticoTxt.append(helper.getDataConclusao() + ";");
					analiticoTxt.append(helper.getCodigoExcedente().trim() + ";");
					analiticoTxt.append(helper.getDescMaterial().trim() + ";");
					analiticoTxt.append(helper.getQtdeExcedente().trim() + ";");
					analiticoTxt.append(helper.getEnderecoRA() + ";");

					analiticoTxt.append(System.getProperty("line.separator"));
					out.write(analiticoTxt.toString());

				}

				out.flush();

			}

			System.out.println("***************************************");
			System.out.println("FIM DA CRIACAO DO ARQUIVO");
			System.out.println("***************************************");

		} catch (IOException ex) {
			ex.printStackTrace();
			throw new EJBException(ex);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new EJBException(ex);
		} finally {
			try {
				ZipUtil.adicionarArquivo(zos, leitura);

				// close the stream
				if (out != null) {
					out.close();
				}
				if (zos != null) {
					zos.close();
				}
				if (leitura != null) {
					leitura.delete();
				}

			} catch (IOException e) {
				throw new EJBException(e);
			}
		}

		try {

			EnvioEmail envioEmail = getControladorCadastro().pesquisarEnvioEmail(EnvioEmail.GERAR_TXT_OS_PRESTADORA_SERVICO);

			String emailRemetente = envioEmail.getEmailRemetente();
			String tituloMensagem = envioEmail.getTituloMensagem();
			String corpoMensagem = envioEmail.getCorpoMensagem();
			String emailReceptor = envioEmail.getEmailReceptor();

			ServicosEmail.enviarMensagemArquivoAnexado(emailRemetente, emailReceptor, tituloMensagem, corpoMensagem, compactado);

		} catch (IOException e) {
			throw new ControladorException("erro.sistema", e);
		} catch (Exception e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC1163] Gerar Relatório de OS executadas por Prestadora de Serviço
	 * 
	 * @author Vivianne Sousa
	 * @date 18/04/2011
	 */
	public Collection recuperaTotalServicoOSExecutadasPorLocalidade(Date dataInicial, Date dataFinal, Integer idGerencia, Integer idUnidade,
			Integer idLocalidade, String[] idsRegiao, String[] idsMicroregiao, String[] idsMunicipio) throws ControladorException {
		try {

			Collection colecaoRetorno = null;

			Collection colecaoDadosOS = repositorioOrdemServico.recuperaTotalServicoOSExecutadasPorLocalidade(dataInicial, dataFinal, idGerencia, idUnidade,
					idLocalidade, idsRegiao, idsMicroregiao, idsMunicipio);

			if (colecaoDadosOS != null && !colecaoDadosOS.isEmpty()) {

				colecaoRetorno = new ArrayList();
				Iterator iter = colecaoDadosOS.iterator();

				while (iter.hasNext()) {

					Object[] objeto = (Object[]) iter.next();

					Integer qtdeOS = (Integer) objeto[0];
					String codigoServico = "";
					if (objeto[1] != null) {
						codigoServico = (String) objeto[1];
					} else {
						codigoServico = "0";
					}
					String descServico = "";
					if (objeto[2] != null) {
						descServico = (String) objeto[2];
					} else {
						descServico = "SERVIÇO SEM DESCRIÇÃO";
					}
					Integer idLoca = (Integer) objeto[3];
					String nomeLocalidade = (String) objeto[4];

					OSExecutadasRelatorioHelper helper = new OSExecutadasRelatorioHelper(idLoca, nomeLocalidade, qtdeOS, codigoServico, descServico);

					colecaoRetorno.add(helper);
				}

			}

			return colecaoRetorno;

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1163] Gerar Relatório de OS executadas por Prestadora de Serviço
	 * 
	 * @author Vivianne Sousa
	 * @date 04/05/2011
	 */
	public Integer recuperaTotalOSExecutadasPorLocalidade(Date dataInicial, Date dataFinal, Integer idGerencia, Integer idUnidade, Integer idLocalidade,
			String[] idsRegiao, String[] idsMicroregiao, String[] idsMunicipio) throws ControladorException {

		try {

			return repositorioOrdemServico.recuperaTotalOSExecutadasPorLocalidade(dataInicial, dataFinal, idGerencia, idUnidade, idLocalidade, idsRegiao,
					idsMicroregiao, idsMunicipio);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Método que valida a ordem de serviço utilizado por diversos outros
	 * métodos do atendimento ao público
	 * 
	 * @author Leonardo Regis,Vivianne Sousa
	 * @date 22/09/2006,19/05/2011
	 * 
	 * @throws ControladorException
	 */
	public void validaOrdemServicoDiasAditivoPrazo(OrdemServico ordemServico, boolean veioEncerrarOS) throws ControladorException {

		if (!veioEncerrarOS) {

			if (ordemServico.getSituacao() != OrdemServico.SITUACAO_ENCERRADO.shortValue()) {

				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.ordem_servico_situacao", null, OrdemServico.SITUACAO_DESCRICAO_ENCERRADO);
			} else {
				if (ordemServico.getAtendimentoMotivoEncerramento() != null
						&& ordemServico.getAtendimentoMotivoEncerramento().getIndicadorExecucao() != ConstantesSistema.SIM) {

					Integer qtdeDiasAditivoPrazo = ordemServico.getAtendimentoMotivoEncerramento().getQtdeDiasAditivoPrazo();

					if (qtdeDiasAditivoPrazo == null) {
						// Caso a quantidade de dias de aditivo = Valor Nulo
						sessionContext.setRollbackOnly();
						throw new ControladorException("atencao.ordem_servico_nao_executada", null, OrdemServico.SITUACAO_DESCRICAO_ENCERRADO_NAO_EXECUTADA);
					} else {
						// Caso Contrário projetar a data de encerramento da OS,
						// somando a quantidade de dias do
						// aditivo(amen_qtdiasaditivoprazo)
						// com a data de encerramento(orse_tmencerramento)
						Date dataEncerramentoMaisDiasAditivo = Util.adicionarNumeroDiasDeUmaData(ordemServico.getDataEncerramento(), qtdeDiasAditivoPrazo);

						if (dataEncerramentoMaisDiasAditivo.compareTo(new Date()) == -1) {
							// SE a data projetada for menor que a data corrente
							sessionContext.setRollbackOnly();
							throw new ControladorException("atencao.ordem_servico_nao_executada", null, OrdemServico.SITUACAO_DESCRICAO_ENCERRADO_NAO_EXECUTADA);
						}
					}

				}
			}

			if (new Integer(ordemServico.getIndicadorComercialAtualizado()).intValue() == ConstantesSistema.SIM.intValue()) {

				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.ordem_servico_sistema_comercial_atualizado");
			}
		}
	}

	/**
	 * Atualiza o documento de cobrança da ordem de serviço que foi gerado pelo
	 * "[UC0444 Gerar e Emitir Extrato de Débito]"
	 * 
	 * [UC1169] Movimentar Ordens de Serviço de Cobrança por Resultado
	 * 
	 * @author Mariana Victor
	 * @date 19/05/2011
	 */
	public void atualizarDocumentoCobrancaOS(Integer idOrdemServico, Integer idCobrancaDocumento) throws ControladorException {

		try {

			repositorioOrdemServico.atualizarDocumentoCobrancaOS(idOrdemServico, idCobrancaDocumento);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada
	 * 
	 * [FS0001] – Validar Ordem de Serviço.
	 * 
	 * @author Vivianne Sousa
	 * @date 24/05/2011
	 */
	public Integer pesquisarOSFiscalizacaoPendente(Integer numeroOS) throws ControladorException {

		try {

			return repositorioOrdemServico.pesquisarOSFiscalizacaoPendente(numeroOS);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada
	 * [SB0001] - Selecionar Ordens de Serviço
	 * 
	 * @author Vivianne Sousa
	 * @date 24/05/2011
	 */
	public Map recuperaQtdeOSEncerrada(Integer idGrupoCobranca, Integer idGerencia, Integer idUnidade, Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idTipoServico, Integer qtdeDiasEncerramentoOS) throws ControladorException {

		try {

			Map objRetorno = new HashMap();

			Integer idEmpresaContratoServico = repositorioOrdemServico.pesquisarEmpresaContratoEmpresaServico(idGrupoCobranca);

			// Integer idRotaGrupoCobranca =
			// repositorioOrdemServico.pesquisarRotaGrupoCobranca(idGrupoCobranca);

			Integer qtdeOSEncerradaConclusaoServico = repositorioOrdemServico.recuperaQtdeOSEncerradaConclusaoServico(idGrupoCobranca, idGerencia, idUnidade,
					idLocalidadeInicial, idLocalidadeFinal, idTipoServico, qtdeDiasEncerramentoOS, idEmpresaContratoServico);

			Integer qtdeOSSemConclusaoServico = repositorioOrdemServico.recuperaQtdeOSEncerradaNaoConclusaoServico(idGrupoCobranca, idGerencia, idUnidade,
					idLocalidadeInicial, idLocalidadeFinal, idTipoServico, qtdeDiasEncerramentoOS, idEmpresaContratoServico);

			objRetorno.put("qtdeOSConclusaoServico", qtdeOSEncerradaConclusaoServico);
			objRetorno.put("qtdeOSSemConclusaoServico", qtdeOSSemConclusaoServico);

			return objRetorno;

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada
	 * [SB0002] – Verificar Ordem Serviço
	 * 
	 * @author Vivianne Sousa
	 * @date 26/05/2011
	 */
	public Integer pesquisarIdMotivoEncerramentoOS(Integer idOrdemServico) throws ControladorException {

		try {

			return repositorioOrdemServico.pesquisarIdMotivoEncerramentoOS(idOrdemServico);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada
	 * [SB0003] - Gerar Várias Ordens de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 26/05/2011
	 */
	public Collection gerarVariasOsFiscalizacao(Integer idGrupoCobranca, Integer idGerencia, Integer idUnidade, Integer idLocalidadeInicial,
			Integer idLocalidadeFinal, Integer idTipoServico, Integer qtdeDiasEncerramentoOS, BigDecimal percentualOSgeradas, Usuario usuario)
			throws ControladorException {

		try {

			Collection colecaoOSGeradas = new ArrayList();
			Integer idEmpresaContratoServico = repositorioOrdemServico.pesquisarEmpresaContratoEmpresaServico(idGrupoCobranca);

			// Integer idRotaGrupoCobranca =
			// repositorioOrdemServico.pesquisarRotaGrupoCobranca(idGrupoCobranca);

			ServicoTipo servicoTipo = null;
			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, ServicoTipo.TIPO_FISCALIZACAO));
			Collection colecaoServicoTipo = getControladorUtil().pesquisar(filtroServicoTipo, ServicoTipo.class.getName());

			if (colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()) {
				servicoTipo = (ServicoTipo) colecaoServicoTipo.iterator().next();
			}

			// TODOS
			Collection colecaoOSSemConclusaoServico = repositorioOrdemServico.recuperaIdOSEncerradaNaoConclusaoServico(idGrupoCobranca, idGerencia, idUnidade,
					idLocalidadeInicial, idLocalidadeFinal, idTipoServico, qtdeDiasEncerramentoOS, idEmpresaContratoServico);
			Collection colecaoOSGeradaSemConclusaoServico = null;
			if (colecaoOSSemConclusaoServico != null && !colecaoOSSemConclusaoServico.isEmpty()) {
				colecaoOSGeradaSemConclusaoServico = gerarOrdemServicoFiscalizacao(colecaoOSSemConclusaoServico, servicoTipo, usuario);
				colecaoOSGeradas.addAll(colecaoOSGeradaSemConclusaoServico);
			}

			Collection colecaoOSEncerradaConclusaoServico = repositorioOrdemServico.recuperaIdOSEncerradaConclusaoServico(idGrupoCobranca, idGerencia,
					idUnidade, idLocalidadeInicial, idLocalidadeFinal, idTipoServico, qtdeDiasEncerramentoOS, idEmpresaContratoServico);
			Collection colecaoGerar = null;
			Collection colecaoOSGeradaConclusaoServico = null;
			if (colecaoOSEncerradaConclusaoServico != null && !colecaoOSEncerradaConclusaoServico.isEmpty()) {
				Integer tamanhoColecao = colecaoOSEncerradaConclusaoServico.size();
				BigDecimal resultadoMultiplicacao = percentualOSgeradas.multiply(new BigDecimal(tamanhoColecao));
				BigDecimal qtdeOsGerar = resultadoMultiplicacao.divide(new BigDecimal(100), RoundingMode.HALF_DOWN);

				colecaoGerar = new ArrayList();

				List<Integer> listOSEncerradaConclusaoServico = new ArrayList();
				listOSEncerradaConclusaoServico.addAll(colecaoOSEncerradaConclusaoServico);
				Collections.shuffle(listOSEncerradaConclusaoServico);
				for (int i = 0; i < qtdeOsGerar.intValue(); i++) {
					colecaoGerar.add(((Integer) listOSEncerradaConclusaoServico.remove(0)).intValue());
				}
				colecaoOSGeradaConclusaoServico = gerarOrdemServicoFiscalizacao(colecaoGerar, servicoTipo, usuario);
				colecaoOSGeradas.addAll(colecaoOSGeradaConclusaoServico);
			}

			return colecaoOSGeradas;

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada
	 * [SB0004] – Gerar Ordem de Serviço.
	 * 
	 * @author Vivianne Sousa
	 * @date 27/05/2011
	 */
	public Collection gerarOrdemServicoFiscalizacao(Collection colecaoOrdemServicoSelecionadas, ServicoTipo servicoTipo, Usuario usuario)
			throws ControladorException {

		try {
			Collection colecaoRetorno = new ArrayList();
			Iterator iterOs = colecaoOrdemServicoSelecionadas.iterator();
			while (iterOs.hasNext()) {
				Integer idOsReferencia = (Integer) iterOs.next();
				OrdemServico osReferencia = new OrdemServico();
				osReferencia.setId(idOsReferencia);

				OrdemServico ordemServico = new OrdemServico();
				ordemServico.setServicoTipo(servicoTipo);
				ordemServico.setOsReferencia(osReferencia);
				ordemServico.setSituacao(ConstantesSistema.SIM.shortValue());
				ordemServico.setDataEmissao(new Date());
				ordemServico.setDataGeracao(new Date());
				ordemServico.setValorOriginal(servicoTipo.getValor());
				ordemServico.setServicoTipoPrioridadeOriginal(servicoTipo.getServicoTipoPrioridade());
				ordemServico.setServicoTipoPrioridadeAtual(servicoTipo.getServicoTipoPrioridade());
				ordemServico.setIndicadorComercialAtualizado(ConstantesSistema.NAO);
				ordemServico.setIndicadorServicoDiagnosticado(ConstantesSistema.NAO);
				ordemServico.setUltimaAlteracao(new Date());
				ordemServico.setIndicadorEncerramentoAutomatico(ConstantesSistema.NAO);
				ordemServico.setIndicadorBoletim(ConstantesSistema.NAO);

				Object[] objetoPesquisado = repositorioOrdemServico.pesquisarImovelEUnidadeOrganizacional(osReferencia.getId());
				Imovel imovel = new Imovel();
				imovel.setId((Integer) objetoPesquisado[0]);
				UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
				unidadeOrganizacional.setId((Integer) objetoPesquisado[1]);

				ordemServico.setImovel(imovel);
				ordemServico.setUnidadeAtual(unidadeOrganizacional);

				Integer idOrdemServico = (Integer) getControladorUtil().inserir(ordemServico);
				ordemServico.setId(idOrdemServico);
				colecaoRetorno.add(ordemServico);

				OrdemServicoUnidade ordemServicoUnidade = new OrdemServicoUnidade();

				ordemServicoUnidade.setOrdemServico(ordemServico);
				ordemServicoUnidade.setUnidadeOrganizacional(unidadeOrganizacional);
				ordemServicoUnidade.setUsuario(usuario);
				AtendimentoRelacaoTipo atrt = new AtendimentoRelacaoTipo();
				atrt.setId(AtendimentoRelacaoTipo.ABRIR_REGISTRAR);
				ordemServicoUnidade.setAtendimentoRelacaoTipo(atrt);
				ordemServicoUnidade.setUltimaAlteracao(new Date());

				getControladorUtil().inserir(ordemServicoUnidade);

			}
			return colecaoRetorno;

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada
	 * [SB0005] – Gerar Formulário em formato pdf
	 * 
	 * @author Vivianne Sousa
	 * @date 26/05/2011
	 */
	public Collection recuperaDadosOsFiscalizacao(Collection colecaoOSFiscalizacao, Integer idGrupoCobranca) throws ControladorException {

		try {

			Collection colecaoRetorno = null;

			if (colecaoOSFiscalizacao != null && !colecaoOSFiscalizacao.isEmpty()) {
				SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
				Iterator iterOrdemServico = colecaoOSFiscalizacao.iterator();
				colecaoRetorno = new ArrayList();
				int sequencial = 0;
				while (iterOrdemServico.hasNext()) {

					OrdemServico OSFiscalizacao = (OrdemServico) iterOrdemServico.next();
					sequencial = sequencial + 1;
					Integer idOrdemServico = OSFiscalizacao.getOsReferencia().getId();// antiga

					Object[] dadosRelatorio = repositorioOrdemServico.recuperaDadosOSPorId(idOrdemServico);

					String servicoTipo = (String) dadosRelatorio[0];
					String motivoEncerramento = "";
					Date dataEncerramento = null;
					String numeroSequencialRotaEntrega = "";
					String ligAguaSituacao = "";
					String ligEsgotoSituacao = "";
					String codigoRota = "";
					Integer idImovel = null;
					Integer idcobrancaGrupoOS = null;

					if (dadosRelatorio[1] != null) {
						motivoEncerramento = (String) dadosRelatorio[1];
					}
					if (dadosRelatorio[2] != null) {
						dataEncerramento = (Date) dadosRelatorio[2];
					}
					if (dadosRelatorio[3] != null) {
						idImovel = (Integer) dadosRelatorio[3];
					}
					if (dadosRelatorio[4] != null) {
						numeroSequencialRotaEntrega = "" + ((Integer) dadosRelatorio[4]);
					}
					if (dadosRelatorio[5] != null) {
						ligAguaSituacao = (String) dadosRelatorio[5];
					}
					if (dadosRelatorio[6] != null) {
						ligEsgotoSituacao = (String) dadosRelatorio[6];
					}
					if (dadosRelatorio[7] != null) {
						codigoRota = "" + ((Short) dadosRelatorio[7]);
					}
					if (dadosRelatorio[8] != null) {
						idcobrancaGrupoOS = (Integer) dadosRelatorio[8];
					}

					Date dataValidade = Util.adicionarNumeroDiasDeUmaData(new Date(), sistemaParametro.getQtdeDiasValidadeOSFiscalizacao());

					Integer categoriaRES = 0;
					Integer categoriaPUB = 0;
					Integer categoriaIND = 0;
					Integer categoriaCOM = 0;
					Imovel imovel = new Imovel();
					imovel.setId(idImovel);
					// Inclui [UC0108] - Obter Quantidade de Economias por
					// Categoria

					Collection<Categoria> colecaoCategorias = getControladorImovel().obterQuantidadeEconomiasCategoria(imovel);

					Iterator iteratorCategoria = colecaoCategorias.iterator();
					while (iteratorCategoria.hasNext()) {
						Categoria categoria = (Categoria) iteratorCategoria.next();
						if (categoria.getDescricaoAbreviada().equals(Categoria.RESIDENCIAL_DESCRICAO_ABREVIADA)) {
							categoriaRES = categoria.getQuantidadeEconomiasCategoria();
						} else if (categoria.getDescricaoAbreviada().equals(Categoria.COMERCIAL_DESCRICAO_ABREVIADA)) {
							categoriaCOM = categoria.getQuantidadeEconomiasCategoria();
						} else if (categoria.getDescricaoAbreviada().equals(Categoria.INDUSTRIAL_DESCRICAO_ABREVIADA)) {
							categoriaIND = categoria.getQuantidadeEconomiasCategoria();
						} else if (categoria.getDescricaoAbreviada().equals(Categoria.PUBLICO_DESCRICAO_ABREVIADA)) {
							categoriaPUB = categoria.getQuantidadeEconomiasCategoria();
						}
					}

					String enderecoImovel = getControladorEndereco().pesquisarEnderecoFormatado(idImovel);
					Cliente clienteUsuario = getControladorCliente().retornaDadosClienteUsuario(idImovel);
					String inscricao = getControladorImovel().pesquisarInscricaoImovel(idImovel);

					OrdemServicoBoletim ordemServicoBoletim = repositorioOrdemServico.recuperaOrdemServicoBoletim(idOrdemServico);
					String tipoPavimento = "";
					BigDecimal qtdeRecomposta = ConstantesSistema.VALOR_ZERO;
					if (ordemServicoBoletim != null) {
						if (ordemServicoBoletim.getNumeroReposicaoAsfalto() != null && !ordemServicoBoletim.getNumeroReposicaoAsfalto().equals("")
								&& ordemServicoBoletim.getNumeroReposicaoAsfalto().compareTo(ConstantesSistema.VALOR_ZERO) == 1) {
							tipoPavimento = "ASFALTO";
							qtdeRecomposta = ordemServicoBoletim.getNumeroReposicaoAsfalto();
						} else if (ordemServicoBoletim.getNumeroReposicaoParalelo() != null && !ordemServicoBoletim.getNumeroReposicaoParalelo().equals("")
								&& ordemServicoBoletim.getNumeroReposicaoParalelo().compareTo(ConstantesSistema.VALOR_ZERO) == 1) {
							tipoPavimento = "PARALELO";
							qtdeRecomposta = ordemServicoBoletim.getNumeroReposicaoParalelo();
						} else if (ordemServicoBoletim.getNumeroReposicaoCalcada() != null && !ordemServicoBoletim.getNumeroReposicaoCalcada().equals("")
								&& ordemServicoBoletim.getNumeroReposicaoCalcada().compareTo(ConstantesSistema.VALOR_ZERO) == 1) {
							tipoPavimento = "CALÇADA";
							qtdeRecomposta = ordemServicoBoletim.getNumeroReposicaoCalcada();
						}
					}

					String numeroHidrometro = repositorioOrdemServico.pesquisarNumeroHidrometro(idImovel);

					RelatorioOSFiscalizacaoHelper helper = new RelatorioOSFiscalizacaoHelper();
					helper.setSequencial("" + sequencial);
					helper.setOrdemServico(OSFiscalizacao.getId().toString());
					helper.setNumeroOrdemExecutada(idOrdemServico.toString());
					helper.setDataEmissao(Util.formatarData(new Date()));
					helper.setDataValidade(Util.formatarData(dataValidade));
					if (idGrupoCobranca != null) {
						helper.setGrupo(idGrupoCobranca.toString());
					} else {
						if (idcobrancaGrupoOS != null) {
							helper.setGrupo(idcobrancaGrupoOS.toString());
						} else {
							helper.setGrupo("");
						}
					}
					helper.setMatricula(idImovel.toString());
					helper.setInscricao(inscricao);
					helper.setRota(codigoRota);
					helper.setSequencialRota(numeroSequencialRotaEntrega);
					helper.setSituacaoLigacaoAgua(ligAguaSituacao);
					helper.setSituacaoLigacaoEsgoto(ligEsgotoSituacao);

					helper.setNomeClienteUsuario(clienteUsuario.getNome());
					helper.setEnderecoImovel(enderecoImovel);
					helper.setCategoriaRES(categoriaRES.toString());
					helper.setCategoriaCOM(categoriaCOM.toString());
					helper.setCategoriaIND(categoriaIND.toString());
					helper.setCategoriaPUB(categoriaPUB.toString());

					helper.setDataExecucao(Util.formatarData(dataEncerramento));
					helper.setTipoServico(servicoTipo);
					helper.setMotivoEncerramento(motivoEncerramento);

					helper.setTipoPavimento(tipoPavimento);
					helper.setQtdeRecomposta(Util.formatarMoedaReal(qtdeRecomposta));

					if (numeroHidrometro != null) {
						helper.setNumeroHidrometro(numeroHidrometro);
					} else {
						helper.setNumeroHidrometro("");
					}
					helper.setLeituraHidrometro("");

					colecaoRetorno.add(helper);
				}
			}

			return colecaoRetorno;

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0457] Encerrar Ordem de Serviço [SB0008]- Incluir Ordem Serviço Não
	 * Aceita.
	 * 
	 * @author Vivianne Sousa
	 * @date 03/06/2011
	 */
	public void incluirOrdemServicoNaoAceita(Integer idOSReferencia, Usuario usuario) throws ControladorException {

		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setId(idOSReferencia);
		Date date = new Date();
		String observacao = "USUÁRIO " + usuario.getId() + " REJEITOU A ORDEM DE SERVIÇO";

		// PESQUISAR MOTIVO DE NÃO ACEITAÇÃO
		FiltroMotivoNaoAceitacaoEncerramentoOS filtro = new FiltroMotivoNaoAceitacaoEncerramentoOS();
		filtro.adicionarParametro(new ParametroSimples(FiltroMotivoNaoAceitacaoEncerramentoOS.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtro.adicionarParametro(new ParametroSimples(FiltroMotivoNaoAceitacaoEncerramentoOS.INDICADOR_MOTIVO_FISCALIZACAO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoMotivosNaoAceitacao = getControladorUtil().pesquisar(filtro, MotivoNaoAceitacaoEncerramentoOS.class.getName());
		MotivoNaoAceitacaoEncerramentoOS motivosNaoAceitacao = null;
		if (colecaoMotivosNaoAceitacao != null && !colecaoMotivosNaoAceitacao.isEmpty()) {
			motivosNaoAceitacao = (MotivoNaoAceitacaoEncerramentoOS) Util.retonarObjetoDeColecao(colecaoMotivosNaoAceitacao);
		}

		CobrancaAcaoOrdemServicoNaoAceitas cobrancaAcaoOrdemServicoNaoAceitas = new CobrancaAcaoOrdemServicoNaoAceitas();
		CobrancaAcaoOrdemServicoNaoAceitasPK cobrancaAcaoOrdemServicoNaoAceitasPK = new CobrancaAcaoOrdemServicoNaoAceitasPK();

		cobrancaAcaoOrdemServicoNaoAceitas.setUltimaAlteracao(date);
		cobrancaAcaoOrdemServicoNaoAceitas.setIndicadorNaoAceitacao(new Short("1"));
		cobrancaAcaoOrdemServicoNaoAceitas.setIndicadorDescontoEfetuado(new Short("2"));
		cobrancaAcaoOrdemServicoNaoAceitas.setCobrancaAcao(null);
		cobrancaAcaoOrdemServicoNaoAceitas.setOrdemServico(ordemServico);
		cobrancaAcaoOrdemServicoNaoAceitas.setObservacao(observacao);
		cobrancaAcaoOrdemServicoNaoAceitas.setMotivoNaoAceitacao(motivosNaoAceitacao);

		cobrancaAcaoOrdemServicoNaoAceitasPK.setOrdemServicoId(ordemServico.getId());
		cobrancaAcaoOrdemServicoNaoAceitas.setComp_id(cobrancaAcaoOrdemServicoNaoAceitasPK);

		getControladorUtil().inserir(cobrancaAcaoOrdemServicoNaoAceitas);

	}

	/**
	 * Inserir Equipamentos Especiais no sistema.
	 * 
	 * @author Nathalia Santos
	 * @date 21/06/2011
	 * 
	 * @param equipeEquipamentosEspeciais
	 * @throws ControladorException
	 */
	public void inserirEquipeEquipamentosEspeciais(Collection<EquipeEquipamentosEspeciais> colecaoEquipeEquipamentosEspeciais, Equipe equipe, Usuario usuario)
			throws ControladorException {

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(Operacao.OPERACAO_EQUIPE_INSERIR, new UsuarioAcaoUsuarioHelper(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_EQUIPE_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		for (Iterator iter = colecaoEquipeEquipamentosEspeciais.iterator(); iter.hasNext();) {
			EquipeEquipamentosEspeciais element = (EquipeEquipamentosEspeciais) iter.next();
			element.setEquipe(equipe);
			// Registra operação
			element.setOperacaoEfetuada(operacaoEfetuada);
			element.adicionarUsuario(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(element);
			getControladorUtil().inserir(element);
		}

	}

	/**
	 * [FS0011] Validar equipamentos especiais já existente
	 * 
	 * @author Nathalia Santos
	 * @date 21/06/2011
	 * 
	 * @param equipeEquipamentosEspeciais
	 */
	public boolean validarExibirInsercaoEquipeEquipamentosEspeciais(Collection colecaoEquipeEquipamentosEspeciais,
			EquipeEquipamentosEspeciais equipeEquipamentosEspeciais) throws ControladorException {
		boolean retorno = false;
		// Verificar objeto a ser inserido na base.
		if (equipeEquipamentosEspeciais != null) {
			// Testar se novo componente pode ser inserido na coleção
			if (colecaoEquipeEquipamentosEspeciais != null && !colecaoEquipeEquipamentosEspeciais.isEmpty()) {
				// Varre coleção de componentes da grid (ainda não inseridos na
				// base)
				Session session = HibernateUtil.getSession();
				String consulta;
				Integer quantidadeEquipeEquipamentosEspeciais = null;
				try {
					consulta = " SELECT count(*) AS equipeEquipamentosEspeciais " + " FROM atendimento.equipe_equip_espec ";
					quantidadeEquipeEquipamentosEspeciais = (Integer) session.createSQLQuery(consulta).addScalar("quantidade", Hibernate.INTEGER)
							.setInteger("id", equipeEquipamentosEspeciais.getId()).setMaxResults(1).uniqueResult();

					if (quantidadeEquipeEquipamentosEspeciais == null || quantidadeEquipeEquipamentosEspeciais == 0) {
						throw new ControladorException("atencao.entidade_sem_dados_para_selecao", null, "EQUIPE_EQUIP_ESPEC");
					}

					for (Iterator iter = colecaoEquipeEquipamentosEspeciais.iterator(); iter.hasNext();) {
						EquipeEquipamentosEspeciais element = (EquipeEquipamentosEspeciais) iter.next();
						// [FS0011] Validar equipamentos equipe já existente
						if (element.getQuantidade() != null && equipeEquipamentosEspeciais.getQuantidade() != null) {
							if (element.getId().intValue() == equipeEquipamentosEspeciais.getId().intValue()) {
								throw new ControladorException("atencao.inserir_equipamentos_equipe", null, "");

							} else {
								throw new ControladorException("atencao.inserir_equipe_equipamentos_especiais_invalido", null, "");
							}
						}
					}
				} catch (HibernateException e) {
					// levanta a exceção para a próxima camada
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				} finally {
					// fecha a sessão
					HibernateUtil.closeSession(session);
				}
			}
		}
		return retorno;
	}

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades da
	 * inserção de equipamentos especiais da equipe.
	 * 
	 * @author Nathalia Santos
	 * @date 28/06/2011
	 * 
	 * @param equipamentosEspeciais
	 * @throws ControladorException
	 */
	public void validarInsercaoEquipeEquipamentosEspeciais(Collection colecaoEquipeEquipamentosEspeciais) throws ControladorException {
		// Testar se novo componente pode ser inserido na coleção
		if (colecaoEquipeEquipamentosEspeciais != null && !colecaoEquipeEquipamentosEspeciais.isEmpty()) {
			// Varre coleção de componentes da grid (ainda não inseridos na
			// base)
			EquipeEquipamentosEspeciais equipeEquipamentoEspecial = null;
			for (Iterator iter = colecaoEquipeEquipamentosEspeciais.iterator(); iter.hasNext();) {
				equipeEquipamentoEspecial = (EquipeEquipamentosEspeciais) iter.next();

				if (iter.hasNext()) {

					EquipeEquipamentosEspeciais equipeEquipamentoEspecial2 = null;

					while (iter.hasNext()) {
						equipeEquipamentoEspecial2 = (EquipeEquipamentosEspeciais) iter.next();
						if (equipeEquipamentoEspecial.getEquipamentosEspeciais().getId().intValue() == equipeEquipamentoEspecial2.getEquipamentosEspeciais()
								.getId().intValue()) {
							colecaoEquipeEquipamentosEspeciais.remove(equipeEquipamentoEspecial2);
							throw new ControladorException("atencao.equipe_equipamentos_especial_ja_informado", null, equipeEquipamentoEspecial
									.getEquipamentosEspeciais().getDescricao());
						}
					}
				}

			}

		}
	}

	/**
	 * [UC0711] Filtro para Emissao de Ordens Seletivas [SB0001]-Gerar Comando.
	 * 
	 * @author Vivianne Sousa
	 * @date 21/06/2011
	 * 
	 * @param helper
	 * @throws ControladorException
	 */
	public Integer gerarComando(ImovelEmissaoOrdensSeletivasHelper helper, int quantidadeOs, Usuario usuarioLogado) throws ControladorException {

		ComandoOrdemSeletiva comandoOrdemSeletiva = new ComandoOrdemSeletiva();

		comandoOrdemSeletiva.setSituacaoComando(ConstantesSistema.SIM);
		comandoOrdemSeletiva.setDescricaoComando(helper.getDescricaoComando());
		comandoOrdemSeletiva.setDataGeracao(new Date());
		comandoOrdemSeletiva.setDataEncerramento(null);
		comandoOrdemSeletiva.setQuantidadeOrdemServico(quantidadeOs);

		if (helper.getQuantidadeMaxima() != null && !helper.getQuantidadeMaxima().equals("")) {
			comandoOrdemSeletiva.setQuantidadeMaximaOrdemServico(new Integer(helper.getQuantidadeMaxima()));
		}
		if (helper.getRotaSequenciaInicial() != null && !helper.getRotaSequenciaInicial().equals("")) {
			comandoOrdemSeletiva.setSequencialRotaInicial(new Integer(helper.getRotaSequenciaInicial()));
		}
		if (helper.getRotaSequenciaFinal() != null && !helper.getRotaSequenciaFinal().equals("")) {
			comandoOrdemSeletiva.setSequencialRotaFinal(new Integer(helper.getRotaSequenciaFinal()));
		}
		if (helper.getNumeroOcorrenciasAnormalidade() != null && !helper.getNumeroOcorrenciasAnormalidade().equals("")) {
			comandoOrdemSeletiva.setQuantidadeConsecutivaAnormalidade(new Integer(helper.getNumeroOcorrenciasAnormalidade()));
		}
		if (helper.getMesAnoInstalacaoInicialHidrometro() != null && !helper.getMesAnoInstalacaoInicialHidrometro().equals("")) {
			comandoOrdemSeletiva.setAnoMesHidrometroInstInicial(new Integer(helper.getMesAnoInstalacaoInicialHidrometro()));
		}
		if (helper.getMesAnoInstalacaoFinalHidrometro() != null && !helper.getMesAnoInstalacaoFinalHidrometro().equals("")) {
			comandoOrdemSeletiva.setAnoMesHidrometroInstFinal(new Integer(helper.getMesAnoInstalacaoFinalHidrometro()));
		}
		if (helper.getQuantidadeEconomiasInicial() != null && !helper.getQuantidadeEconomiasInicial().equals("")) {
			comandoOrdemSeletiva.setQuantidadeEconomiaInicial(new Integer(helper.getQuantidadeEconomiasInicial()));
		}
		if (helper.getQuantidadeEconomiasFinal() != null && !helper.getQuantidadeEconomiasFinal().equals("")) {
			comandoOrdemSeletiva.setQuantidadeEconomiaFinal(new Integer(helper.getQuantidadeEconomiasFinal()));
		}
		if (helper.getQuantidadeDocumentosInicial() != null && !helper.getQuantidadeDocumentosInicial().equals("")) {
			comandoOrdemSeletiva.setQuantidadeDocumentoInicial(new Integer(helper.getQuantidadeDocumentosInicial()));
		}
		if (helper.getQuantidadeDocumentosFinal() != null && !helper.getQuantidadeDocumentosFinal().equals("")) {
			comandoOrdemSeletiva.setQuantidadeDocumentoFinal(new Integer(helper.getQuantidadeDocumentosFinal()));
		}
		if (helper.getNumeroMoradoresInicial() != null && !helper.getNumeroMoradoresInicial().equals("")) {
			comandoOrdemSeletiva.setQuantidadeMoradoresInicial(new Integer(helper.getNumeroMoradoresInicial()));
		}
		if (helper.getNumeroMoradoresFinal() != null && !helper.getNumeroMoradoresFinal().equals("")) {
			comandoOrdemSeletiva.setQuantidadeMoradoresFinal(new Integer(helper.getNumeroMoradoresFinal()));
		}
		if (helper.getAreaConstruidaInicial() != null && !helper.getAreaConstruidaInicial().equals("")) {
			comandoOrdemSeletiva.setAreaConstruidaInicial(Util.formatarMoedaRealparaBigDecimal(helper.getAreaConstruidaInicial()));
		}
		if (helper.getAreaConstruidaFinal() != null && !helper.getAreaConstruidaFinal().equals("")) {
			comandoOrdemSeletiva.setAreaConstruidaFinal(Util.formatarMoedaRealparaBigDecimal(helper.getAreaConstruidaFinal()));
		}
		comandoOrdemSeletiva.setIndicadorImovelCondominio(new Short(helper.getImovelCondominio()));

		if (helper.getMediaImovel() != null && !helper.getMediaImovel().equals("")) {
			comandoOrdemSeletiva.setMediaConsumo(new Integer(helper.getMediaImovel()));
		}
		if (helper.getConsumoPorEconomia() != null && !helper.getConsumoPorEconomia().equals("")) {
			comandoOrdemSeletiva.setConsumoEconomiaInicial(new Integer(helper.getConsumoPorEconomia()));
		}
		if (helper.getConsumoPorEconomiaFinal() != null && !helper.getConsumoPorEconomiaFinal().equals("")) {
			comandoOrdemSeletiva.setConsumoEconomiaFinal(new Integer(helper.getConsumoPorEconomiaFinal()));
		}
		comandoOrdemSeletiva.setUltimaAlteracao(new Date());

		ServicoTipo servicoTipo = new ServicoTipo();
		boolean gerarTxtInspecaoAnormalidade = false;
		if (helper.getTipoOrdem().equals("" + ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_INSTALACAO)) {
			servicoTipo.setId(ServicoTipo.TIPO_EFETUAR_INSTALACAO_HIDROMETRO);
		} else if (helper.getTipoOrdem().equals("" + ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_SUBSTITUICAO)) {
			servicoTipo.setId(ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO);
		} else if (helper.getTipoOrdem().equals("" + ImovelEmissaoOrdensSeletivasActionForm.TIPO_INSPECAO_ANORMALIDADE)) {
			servicoTipo.setId(ServicoTipo.TIPO_INSPECAO_ANORMALIDADE);
			gerarTxtInspecaoAnormalidade = true;
		} else if (helper.getTipoOrdem().equals("" + ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_REMOCAO)) {
			servicoTipo.setId(ServicoTipo.TIPO_EFETUAR_REMOCAO_HIDROMETRO);
		}
		// servicoTipo.setId(new Integer(helper.getTipoOrdem()));
		comandoOrdemSeletiva.setServicoTipo(servicoTipo);

		if (helper.getPerfilImovel() != null && !helper.getPerfilImovel().equals("")
				&& !helper.getPerfilImovel().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			ImovelPerfil imovelPerfil = new ImovelPerfil();
			imovelPerfil.setId(new Integer(helper.getPerfilImovel()));
			comandoOrdemSeletiva.setImovelPerfil(imovelPerfil);
		}
		if (helper.getCategoria() != null && !helper.getCategoria().equals("") && !helper.getCategoria().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			Categoria categoria = new Categoria();
			categoria.setId(new Integer(helper.getCategoria()));
			comandoOrdemSeletiva.setCategoria(categoria);
		}
		if (helper.getSubCategoria() != null && !helper.getSubCategoria().equals("")
				&& !helper.getSubCategoria().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			Subcategoria subcategoria = new Subcategoria();
			subcategoria.setId(new Integer(helper.getSubCategoria()));
			comandoOrdemSeletiva.setSubcategoria(subcategoria);
		}
		if (helper.getFirma() != null && !helper.getFirma().equals("") && !helper.getFirma().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			Empresa empresa = new Empresa();
			empresa.setId(new Integer(helper.getFirma()));
			comandoOrdemSeletiva.setEmpresa(empresa);
		}
		if (helper.getGerenciaRegional() != null && !helper.getGerenciaRegional().equals("")
				&& !helper.getGerenciaRegional().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			GerenciaRegional gerenciaRegional = new GerenciaRegional();
			gerenciaRegional.setId(new Integer(helper.getGerenciaRegional()));
			comandoOrdemSeletiva.setGerenciaRegional(gerenciaRegional);
		}
		if (helper.getUnidadeNegocio() != null && !helper.getUnidadeNegocio().equals("")
				&& !helper.getUnidadeNegocio().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
			unidadeNegocio.setId(new Integer(helper.getUnidadeNegocio()));
			comandoOrdemSeletiva.setUnidadeNegocio(unidadeNegocio);
		}
		if (helper.getLocalidadeInicial() != null && !helper.getLocalidadeInicial().equals("")) {
			Localidade localidadeInicial = new Localidade();
			localidadeInicial.setId(new Integer(helper.getLocalidadeInicial()));
			comandoOrdemSeletiva.setLocalidadeInicial(localidadeInicial);
		}
		if (helper.getLocalidadeFinal() != null && !helper.getLocalidadeFinal().equals("")) {
			Localidade localidadeFinal = new Localidade();
			localidadeFinal.setId(new Integer(helper.getLocalidadeFinal()));
			comandoOrdemSeletiva.setLocalidadeFinal(localidadeFinal);
		}
		if (helper.getQuadraInicial() != null && !helper.getQuadraInicial().equals("")) {
			Quadra quadraInicial = new Quadra();
			quadraInicial.setId(new Integer(helper.getQuadraInicial()));
			comandoOrdemSeletiva.setQuadraInicial(quadraInicial);
		}
		if (helper.getQuadraFinal() != null && !helper.getQuadraFinal().equals("")) {
			Quadra quadraFinal = new Quadra();
			quadraFinal.setId(new Integer(helper.getQuadraFinal()));
			comandoOrdemSeletiva.setQuadraFinal(quadraFinal);
		}
		if (helper.getSetorComercialInicial() != null && !helper.getSetorComercialInicial().equals("")) {
			SetorComercial setorComercialInicial = new SetorComercial();
			setorComercialInicial.setId(new Integer(helper.getSetorComercialInicial()));
			comandoOrdemSeletiva.setSetorComercialInicial(setorComercialInicial);
		}
		if (helper.getSetorComercialFinal() != null && !helper.getSetorComercialFinal().equals("")) {
			SetorComercial setorComercialFinal = new SetorComercial();
			setorComercialFinal.setId(new Integer(helper.getSetorComercialFinal()));
			comandoOrdemSeletiva.setSetorComercialFinal(setorComercialFinal);
		}
		if (helper.getRotaInicial() != null && !helper.getRotaInicial().equals("")) {
			Rota rotaInicial = new Rota();
			rotaInicial.setId(new Integer(helper.getRotaInicial()));
			comandoOrdemSeletiva.setRotaInicial(rotaInicial);
		}
		if (helper.getRotaFinal() != null && !helper.getRotaFinal().equals("")) {
			Rota rotaFinal = new Rota();
			rotaFinal.setId(new Integer(helper.getRotaFinal()));
			comandoOrdemSeletiva.setRotaFinal(rotaFinal);
		}
		if (helper.getIdImovel() != null && !helper.getIdImovel().equals("")) {
			Imovel imovel = new Imovel();
			imovel.setId(new Integer(helper.getIdImovel()));
			comandoOrdemSeletiva.setImovel(imovel);
		}
		if (helper.getElo() != null && !helper.getElo().equals("")) {
			Localidade localidadePolo = new Localidade();
			localidadePolo.setId(new Integer(helper.getElo()));
			comandoOrdemSeletiva.setLocalidadePolo(localidadePolo);
		}
		if (helper.getLogradouro() != null && !helper.getLogradouro().equals("")) {
			Logradouro logradouro = new Logradouro();
			logradouro.setId(new Integer(helper.getLogradouro()));
			comandoOrdemSeletiva.setLogradouro(logradouro);
		}
		if (helper.getLocalInstalacaoHidrometro() != null && !helper.getLocalInstalacaoHidrometro().equals("")
				&& !helper.getLocalInstalacaoHidrometro().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			HidrometroLocalInstalacao hidrometroLocalInstalacao = new HidrometroLocalInstalacao();
			hidrometroLocalInstalacao.setId(new Integer(helper.getLocalInstalacaoHidrometro()));
			comandoOrdemSeletiva.setHidrometroLocalInstalacao(hidrometroLocalInstalacao);
		}
		if (helper.getLocalInstalacaoHidrometro() != null && !helper.getLocalInstalacaoHidrometro().equals("")
				&& !helper.getLocalInstalacaoHidrometro().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			HidrometroMarca hidrometroMarca = new HidrometroMarca();
			hidrometroMarca.setId(new Integer(helper.getLocalInstalacaoHidrometro()));
			comandoOrdemSeletiva.setHidrometroMarca(hidrometroMarca);
		}
		// LigacaoAguaSituacao ligacaoAguaSituacao = null;
		// HidrometroCapacidade hidrometroCapacidade = null;

		comandoOrdemSeletiva.setIndicadorGeracaoTxt(ConstantesSistema.NAO);

		Integer idComandoOrdemSeletiva = (Integer) getControladorUtil().inserir(comandoOrdemSeletiva);

		comandoOrdemSeletiva.setId(idComandoOrdemSeletiva);

		if (helper.getAnormalidadeHidrometro() != null) {
			if (!helper.getAnormalidadeHidrometro().equals("")) {

				for (int i = 0; i < helper.getAnormalidadeHidrometro().length; i++) {

					AnormalidadeComandoOSS anormalidadeComandoOSS = new AnormalidadeComandoOSS();
					// anormalidadeComandoOSS.setComandoOrdemSeletiva(comandoOrdemSeletiva);

					Integer idLeituraAnormalidade = new Integer(helper.getAnormalidadeHidrometro()[i]);
					// LeituraAnormalidade leituraAnormalidade = new
					// LeituraAnormalidade();
					// leituraAnormalidade.setId(idLeituraAnormalidade);
					// anormalidadeComandoOSS.setLeituraAnormalidade(leituraAnormalidade);

					AnormalidadeComandoOSSPK anormalidadeComandoOSSPK = new AnormalidadeComandoOSSPK();
					anormalidadeComandoOSSPK.setComandoOrdemSeletivaId(idComandoOrdemSeletiva);
					anormalidadeComandoOSSPK.setLeituraAnormalidadeId(idLeituraAnormalidade);
					anormalidadeComandoOSS.setComp_id(anormalidadeComandoOSSPK);

					anormalidadeComandoOSS.setUltimaAlteracao(new Date());

					getControladorUtil().inserir(anormalidadeComandoOSS);
				}
			}
		}

		if (helper.getSituacaoLigacaoAgua() != null) {
			if (!helper.getSituacaoLigacaoAgua().equals("")) {

				for (int i = 0; i < helper.getSituacaoLigacaoAgua().length; i++) {

					LigacaoSitComandoOSS ligacaoSitComandoOSS = new LigacaoSitComandoOSS();
					// ligacaoSitComandoOSS.setComandoOrdemSeletiva(comandoOrdemSeletiva);

					Integer idLigacaoAguaSituacao = new Integer(helper.getSituacaoLigacaoAgua()[i]);
					// LigacaoAguaSituacao ligacaoAguaSituacao = new
					// LigacaoAguaSituacao();
					// ligacaoAguaSituacao.setId(idLigacaoAguaSituacao);
					// ligacaoSitComandoOSS.setLigacaoAguaSituacao(ligacaoAguaSituacao);

					LigacaoSitComandoOSSPK ligacaoSitComandoOSSPK = new LigacaoSitComandoOSSPK();
					ligacaoSitComandoOSSPK.setComandoOrdemSeletivaId(idComandoOrdemSeletiva);
					ligacaoSitComandoOSSPK.setLigacaoAguaSituacaoId(idLigacaoAguaSituacao);
					ligacaoSitComandoOSS.setComp_id(ligacaoSitComandoOSSPK);

					ligacaoSitComandoOSS.setUltimaAlteracao(new Date());

					getControladorUtil().inserir(ligacaoSitComandoOSS);
				}
			}
		}

		if (helper.getCapacidadeHidrometro() != null) {
			if (!helper.getCapacidadeHidrometro().equals("")) {

				for (int i = 0; i < helper.getCapacidadeHidrometro().length; i++) {

					CapacidHidrComandoOSS capacidHidrComandoOSS = new CapacidHidrComandoOSS();

					Integer idHidrometroCapacidade = new Integer(helper.getCapacidadeHidrometro()[i]);

					CapacidHidrComandoOSSPK capacidHidrComandoOSSPK = new CapacidHidrComandoOSSPK();
					capacidHidrComandoOSSPK.setComandoOrdemSeletivaId(idComandoOrdemSeletiva);
					capacidHidrComandoOSSPK.setHidrometroCapacidadeId(idHidrometroCapacidade);
					capacidHidrComandoOSS.setComp_id(capacidHidrComandoOSSPK);

					capacidHidrComandoOSS.setUltimaAlteracao(new Date());

					getControladorUtil().inserir(capacidHidrComandoOSS);
				}
			}
		}

		if (gerarTxtInspecaoAnormalidade) {
			Map parametros = new HashMap();
			parametros.put("idComandoOrdemSeletiva", idComandoOrdemSeletiva);
			parametros.put("qtdAnormalidadesConsecutivas", comandoOrdemSeletiva.getQuantidadeConsecutivaAnormalidade());
			Fachada.getInstancia().inserirProcessoIniciadoParametrosLivresAguardandoAutorizacao(parametros, Processo.GERAR_TXT_OS_INSPECAO_ANORMALIDADE,
					usuarioLogado);
		}

		return idComandoOrdemSeletiva;
	}

	/**
	 * [UC0713] Emitir Ordem de Serviço Seletiva [SB0002] Gerar TXT
	 * 
	 * @author Vivianne Sousa
	 * @date 28/06/2011
	 */
	public void gerarTxtOsInspecaoAnormalidade(Integer idFuncionalidadeIniciada, Integer idComandoOrdemSeletiva, Integer qtdAnormalidadesConsecutivas)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		// Criação do Arquivo
		// ========================================================================
		Date dataAtual = new Date();
		String nomeZip = null;
		nomeZip = "OS_INSPECAO_ANORMALIDADE" + idComandoOrdemSeletiva + Util.formatarData(dataAtual) + Util.formatarHoraSemDataSemDoisPontos(dataAtual);
		nomeZip = nomeZip.replace("/", "_");
		File compactado = new File(nomeZip + ".zip");
		ZipOutputStream zos = null;
		File leitura = new File(nomeZip + ".txt");
		BufferedWriter out = null;
		// ========================================================================

		try {

			// -------------------------
			// Registrar o início do processamento da Unidade de Processamento
			// do Batch
			// -------------------------

			idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, UnidadeProcessamento.FUNCIONALIDADE, 0);

			zos = new ZipOutputStream(new FileOutputStream(compactado));
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leitura.getAbsolutePath())));

			// Variáveis para a paginação da pesquisa
			// ========================================================================
			boolean flagTerminou = false;
			final int quantidadeMaxima = 1000;
			int quantidadeInicio = 0;
			// ========================================================================

			int sequencial = 0;
			StringBuilder linha = null;

			Collection anormalidadesComando = repositorioOrdemServico.pesquisarAnormalidadeComandoOSS(idComandoOrdemSeletiva);
			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
			if (qtdAnormalidadesConsecutivas == null) {
				qtdAnormalidadesConsecutivas = 0;
			}
			int numOcorrencias = qtdAnormalidadesConsecutivas + 1;
			List<String> anoMesOcorrencias = new ArrayList<String>();
			for (int i = 1; i <= numOcorrencias; i++) {
				anoMesOcorrencias.add(Integer.toString(Util.subtrairMesDoAnoMes(sistemaParametro.getAnoMesFaturamento(), i)));
			}

			while (!flagTerminou) {

				Collection<TxtOsInspecaoAnormalidadeHelper> colecaoDados = this.pesquisarDadosEmitirTxtOsInspecaoAnormalidade(quantidadeInicio,
						quantidadeMaxima, idComandoOrdemSeletiva, anormalidadesComando, anoMesOcorrencias, qtdAnormalidadesConsecutivas);

				if (colecaoDados != null && !colecaoDados.isEmpty()) {

					for (TxtOsInspecaoAnormalidadeHelper helper : colecaoDados) {
						linha = new StringBuilder();
						sequencial++;
						// dados da OS
						linha.append(helper.getCodigoOrdemServico() + ";");
						linha.append(Util.formatarData(new Date()) + ";");
						linha.append(sequencial + ";");

						// dados do Imóvel
						linha.append(helper.getInscricaoImovel() + ";");
						linha.append(helper.getMatriculaImovel() + ";");
						linha.append(helper.getDescPerfilImovel() + ";");
						linha.append(helper.getEnderecoImovel() + ";");
						linha.append(helper.getCategoriaRES() + ";");
						linha.append(helper.getCategoriaCOM() + ";");
						linha.append(helper.getCategoriaIND() + ";");
						linha.append(helper.getCategoriaPUB() + ";");
						linha.append(helper.getUltimaAlteracao() + ";");
						linha.append(helper.getGrupoFaturamento() + ";");
						linha.append(helper.getDescSituacaoLigAgua() + ";");
						linha.append(helper.getConsumoImovel() + ";");
						linha.append(helper.getDescSituacaoLigEsgoto() + ";");
						linha.append(helper.getColetaEsgoto() + ";");

						// dados do Cliente Usuário
						linha.append(helper.getNomeCliente() + ";");
						linha.append(helper.getCpfCnpjCliente() + ";");
						linha.append(helper.getIdentidadeCliente() + ";");
						linha.append(helper.getFoneCliente() + ";");

						// dados Hidrômetro
						linha.append(helper.getMarcaHidrometro() + ";");
						linha.append(helper.getNumeroHidrometro() + ";");
						linha.append(helper.getLocalInstalacao() + ";");
						linha.append(helper.getDataInstalacao() + ";");
						linha.append(helper.getAnormalidade() + ";");
						linha.append(helper.getProtecaoHidrometro() + ";");
						linha.append(helper.getIndicadorCavalete() + ";");
						linha.append(helper.getCapacidadeHidrometro() + ";");
						linha.append(helper.getDiamentroHidrometro() + ";");
						linha.append(helper.getLeituraAtualFaturamento() + ";");

						// Collection colecaoMotivoEncerramento =
						// helper.getColecaoAtendimentoMotivoEncerramento();
						// if(colecaoMotivoEncerramento != null &&
						// !colecaoMotivoEncerramento.isEmpty()){
						// Iterator iterMotivoEncerramento =
						// colecaoMotivoEncerramento.iterator();
						// while (iterMotivoEncerramento.hasNext()) {
						// String descricao = (String)
						// iterMotivoEncerramento.next();
						// linha.append(descricao + ";");
						// }
						//
						// }

						linha.append(System.getProperty("line.separator"));

						out.write(linha.toString());
						out.flush();

						linha = null;

					}

				}

				// Incrementa o nº do indice da páginação
				quantidadeInicio = quantidadeInicio + quantidadeMaxima;

				/**
				 * Caso a coleção de dados retornados for menor que a quantidade
				 * de registros seta a flag indicando que a paginação terminou.
				 */
				if (colecaoDados == null || colecaoDados.size() < quantidadeMaxima) {

					flagTerminou = true;
				}

				if (colecaoDados != null) {
					colecaoDados.clear();
					colecaoDados = null;
				}
			}

			repositorioOrdemServico.atualizarIndicadorGeracaoTxtComando(idComandoOrdemSeletiva);

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);

		} catch (Exception e) {
			e.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
			throw new EJBException(e);
		} finally {
			try {
				out.close();
				ZipUtil.adicionarArquivo(zos, leitura);

				// close the stream
				zos.close();
				leitura.delete();
			} catch (IOException e) {
				getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
				throw new EJBException(e);
			}
		}

	}

	/**
	 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada
	 * [SB0005] – Gerar Formulário em formato pdf
	 * 
	 * @author Vivianne Sousa
	 * @date 26/05/2011
	 */
	public Collection pesquisarDadosEmitirTxtOsInspecaoAnormalidade(Integer quantidadeInicio, Integer quantidadeMaxima, Integer idComandoOrdemSeletiva,
			Collection anormalidadesComando, List<String> anoMesOcorrencias, Integer qtdAnormalidadesConsecutivas) throws ControladorException {

		try {

			Collection colecaoRetorno = null;

			Collection<Object[]> colecaoDados = this.repositorioOrdemServico.pesquisarDadosEmitirTxtOsInspecaoAnormalidade(quantidadeInicio, quantidadeMaxima,
					idComandoOrdemSeletiva);

			if (colecaoDados != null && !colecaoDados.isEmpty()) {

				colecaoRetorno = new ArrayList();
				TxtOsInspecaoAnormalidadeHelper helper = null;

				for (Object[] objeto : colecaoDados) {

					helper = new TxtOsInspecaoAnormalidadeHelper();
					Integer matriculaImovel = (Integer) objeto[1];
					// dados da OS
					helper.setCodigoOrdemServico((Integer) objeto[0]);
					// dados do Imóvel
					helper.setMatriculaImovel(matriculaImovel);
					helper.setUltimaAlteracao(Util.formatarData((Date) objeto[3]));
					helper.setDescPerfilImovel((String) objeto[2]);
					helper.setDescSituacaoLigAgua((String) objeto[4]);
					helper.setDescSituacaoLigEsgoto((String) objeto[5]);

					String anormalidade = this.repositorioOrdemServico.pesquisarAnormalidadeImovelPorNumeroDeOcorrenciasConsecultivas(anormalidadesComando,
							qtdAnormalidadesConsecutivas, anoMesOcorrencias, matriculaImovel);
					helper.setAnormalidade(anormalidade);

					String inscricaoImovel = this.getControladorImovel().pesquisarInscricaoImovel(matriculaImovel);
					helper.setInscricaoImovel(inscricaoImovel);

					// [UC0085 - Obter Endereço]
					String enderecoImovel = this.getControladorEndereco().pesquisarEnderecoFormatado(matriculaImovel);
					helper.setEnderecoImovel(enderecoImovel);

					Integer categoriaRES = 0;
					Integer categoriaPUB = 0;
					Integer categoriaIND = 0;
					Integer categoriaCOM = 0;
					Imovel imovel = new Imovel();
					imovel.setId(matriculaImovel);
					// Inclui [UC0108] - Obter Quantidade de Economias por
					// Categoria
					Collection<Categoria> colecaoCategorias = getControladorImovel().obterQuantidadeEconomiasCategoria(imovel);

					Iterator iteratorCategoria = colecaoCategorias.iterator();
					while (iteratorCategoria.hasNext()) {
						Categoria categoria = (Categoria) iteratorCategoria.next();
						if (categoria.getDescricaoAbreviada().equals(Categoria.RESIDENCIAL_DESCRICAO_ABREVIADA)) {
							categoriaRES = categoria.getQuantidadeEconomiasCategoria();
						} else if (categoria.getDescricaoAbreviada().equals(Categoria.COMERCIAL_DESCRICAO_ABREVIADA)) {
							categoriaCOM = categoria.getQuantidadeEconomiasCategoria();
						} else if (categoria.getDescricaoAbreviada().equals(Categoria.INDUSTRIAL_DESCRICAO_ABREVIADA)) {
							categoriaIND = categoria.getQuantidadeEconomiasCategoria();
						} else if (categoria.getDescricaoAbreviada().equals(Categoria.PUBLICO_DESCRICAO_ABREVIADA)) {
							categoriaPUB = categoria.getQuantidadeEconomiasCategoria();
						}
					}

					helper.setCategoriaRES(categoriaRES.toString());
					helper.setCategoriaPUB(categoriaPUB.toString());
					helper.setCategoriaIND(categoriaIND.toString());
					helper.setCategoriaCOM(categoriaCOM.toString());

					Integer grupoFaturamento = getControladorFaturamento().pesquisarFaturamentoGrupoImovel(matriculaImovel);
					helper.setGrupoFaturamento(grupoFaturamento.toString());

					Integer consumoImovel = getControladorMicromedicao().pesquisarUltimoConsumoFaturadoImovel(matriculaImovel, LigacaoTipo.LIGACAO_AGUA);
					if (consumoImovel != null) {
						helper.setConsumoImovel(consumoImovel.toString());
					} else {
						helper.setConsumoImovel("0");
					}
					Integer coletaEsgoto = getControladorMicromedicao().pesquisarUltimoConsumoFaturadoImovel(matriculaImovel, LigacaoTipo.LIGACAO_ESGOTO);
					if (coletaEsgoto != null) {
						helper.setColetaEsgoto(coletaEsgoto.toString());
					} else {
						helper.setColetaEsgoto("0");
					}

					// dados do Cliente
					Cliente clienteUsuario = getControladorCliente().retornaDadosClienteUsuario(matriculaImovel);

					if (clienteUsuario != null) {

						helper.setNomeCliente(clienteUsuario.getNome());

						String cpfCnpjCliente = "";
						if (clienteUsuario.getCpf() != null) {
							cpfCnpjCliente = clienteUsuario.getCpfFormatado();
						} else if (clienteUsuario.getCnpj() != null) {
							cpfCnpjCliente = clienteUsuario.getCnpjFormatado();
						}
						helper.setCpfCnpjCliente(cpfCnpjCliente);

						String identidadeCliente = "";
						if (clienteUsuario.getRg() != null) {
							identidadeCliente = clienteUsuario.getRg();

							if (clienteUsuario.getOrgaoExpedidorRg() != null && clienteUsuario.getOrgaoExpedidorRg().getDescricaoAbreviada() != null) {
								identidadeCliente = identidadeCliente + " " + clienteUsuario.getOrgaoExpedidorRg().getDescricaoAbreviada();
							}
							if (clienteUsuario.getUnidadeFederacao() != null && clienteUsuario.getUnidadeFederacao().getSigla() != null) {
								identidadeCliente = identidadeCliente + " " + clienteUsuario.getUnidadeFederacao().getSigla();
							}
						}
						helper.setIdentidadeCliente(identidadeCliente);

						String foneCliente = getControladorCliente().pesquisarClienteFonePrincipal(clienteUsuario.getId());
						helper.setFoneCliente(foneCliente);
					} else {
						helper.setNomeCliente("");
						helper.setCpfCnpjCliente("");
						helper.setIdentidadeCliente("");
						helper.setFoneCliente("");
					}

					// dados Hidrômetro
					Object[] dadosHidrometro = getControladorMicromedicao().pesquisarDadosHidrometro(matriculaImovel);

					String marcaHidrometro = "";
					String numeroHidrometro = "";
					String localInstalacao = "";
					String dataInstalacao = "";
					String protecaoHidrometro = "";
					Short indicadorCavalete = null;
					String capacidadeHidrometro = "";
					String diamentroHidrometro = "";

					if (dadosHidrometro != null) {
						if (dadosHidrometro[0] != null) {
							marcaHidrometro = (String) dadosHidrometro[0];
						}
						if (dadosHidrometro[1] != null) {
							numeroHidrometro = (String) dadosHidrometro[1];
						}
						if (dadosHidrometro[2] != null) {
							localInstalacao = (String) dadosHidrometro[2];
						}
						if (dadosHidrometro[3] != null) {
							dataInstalacao = Util.formatarData((Date) dadosHidrometro[3]);
						}
						if (dadosHidrometro[4] != null) {
							protecaoHidrometro = (String) dadosHidrometro[4];
						}
						if (dadosHidrometro[5] != null) {
							indicadorCavalete = (Short) dadosHidrometro[5];
						}
						if (dadosHidrometro[6] != null) {
							capacidadeHidrometro = (String) dadosHidrometro[6];
						}
						if (dadosHidrometro[7] != null) {
							diamentroHidrometro = (String) dadosHidrometro[7];
						}
					}
					helper.setMarcaHidrometro(marcaHidrometro);
					helper.setNumeroHidrometro(numeroHidrometro);
					helper.setLocalInstalacao(localInstalacao);
					helper.setDataInstalacao(dataInstalacao);
					helper.setProtecaoHidrometro(protecaoHidrometro);
					if (indicadorCavalete != null && indicadorCavalete.equals(ConstantesSistema.SIM)) {
						helper.setIndicadorCavalete("SIM");
					} else {
						helper.setIndicadorCavalete("NÃO");
					}
					helper.setCapacidadeHidrometro(capacidadeHidrometro);
					helper.setDiamentroHidrometro(diamentroHidrometro);

					Integer leituraAtualFaturamento = getControladorMicromedicao().pesquisarNumeroLeituraRetiradaLigacaoAgua(matriculaImovel);
					if (leituraAtualFaturamento != null) {
						helper.setLeituraAtualFaturamento(leituraAtualFaturamento.toString());
					} else {
						helper.setLeituraAtualFaturamento("");
					}

					// Exibir até 15 motivos de encerramento na tabela
					// atendimentopublico.atend_motivo_encmt com
					// amen_icexibiformordemseletiva = 1
					// Selecionar os seguintes dados(amen_id e
					// amen_dsmotivoencerramento)
					// Collection colecaoAtendimentoMotivoEncerramento =
					// repositorioOrdemServico.pesquisarAtendimentoMotivoEncerramento();
					// Collection colecaoAtendimentoMotivoEncerramentoExibir =
					// null;
					// if(colecaoAtendimentoMotivoEncerramento != null &&
					// !colecaoAtendimentoMotivoEncerramento.isEmpty()){
					// colecaoAtendimentoMotivoEncerramentoExibir = new
					// ArrayList();
					// int i = 0;
					// Iterator iterAmen =
					// colecaoAtendimentoMotivoEncerramento.iterator();
					// while (iterAmen.hasNext() && i < 15) {
					// i++;
					// AtendimentoMotivoEncerramento amen =
					// (AtendimentoMotivoEncerramento) iterAmen.next();
					// String dadosAmen = amen.getId() + " " +
					// amen.getDescricao();
					// colecaoAtendimentoMotivoEncerramentoExibir.add(dadosAmen);
					// }
					// helper.setColecaoAtendimentoMotivoEncerramento(colecaoAtendimentoMotivoEncerramentoExibir);
					// }

					colecaoRetorno.add(helper);
				}

			}

			return colecaoRetorno;

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0412] Manter Tipo de Serviço [SB0003] Atualizar Grau de Importância
	 * 
	 * @author Thúlio Araújo
	 * @date 30/06/2011
	 * 
	 * @param servicoTipo
	 * @param grauImportancia
	 * @param usuario
	 * @throws ControladorException
	 */
	public void atualizarGrauImportanciaServicoTipo(ServicoTipo servicoTipo, Usuario usuario) throws ControladorException {
		// ------------ REGISTRAR TRANSAÇÃO----------------------------

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(Operacao.ATUALIZAR_IMPORTANCIA_TIPO_SERVICO, servicoTipo.getId(),
				servicoTipo.getId(), new UsuarioAcaoUsuarioHelper(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		registradorOperacao.registrarOperacao(servicoTipo);
		registradorOperacao.registrarOperacao(servicoTipo.getProgramaCalibragem());
		getControladorTransacao().registrarTransacao(servicoTipo);
		// ------------ REGISTRAR TRANSAÇÃO----------------------------
		try {

			repositorioOrdemServico.atualizarGrauImportanciaServicoTipo(servicoTipo);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 11/07/2011
	 */
	public Integer pesquisarDadosComandoOSSeletivaCount(Integer idEmpresa, Date comandoInicial, Date comandoFinal) throws ControladorException {
		try {

			return repositorioOrdemServico.pesquisarDadosComandoOSSeletivaCount(idEmpresa, comandoInicial, comandoFinal);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 11/07/2011
	 */
	public Collection pesquisarDadosComandoOSSeletivaResumido(Integer idEmpresa, Date dataInicial, Date dataFinal, int numeroIndice, int quantidadeRegistros,
			Integer qtdeDiasValidadeOSAnormalidadeFiscalizacao) throws ControladorException {
		try {

			Collection colecaoRetorno = null;

			Collection colecaoDadosComando = repositorioOrdemServico.pesquisarDadosComandoOSSeletivaResumido(idEmpresa, dataInicial, dataFinal, numeroIndice,
					quantidadeRegistros);

			if (colecaoDadosComando != null && !colecaoDadosComando.isEmpty()) {
				colecaoRetorno = new ArrayList();
				Iterator iterDadosComando = colecaoDadosComando.iterator();

				while (iterDadosComando.hasNext()) {
					Object[] dadosComando = (Object[]) iterDadosComando.next();
					ConsultarComandosOSSeletivaInspecaoAnormalidadeHelper helper = new ConsultarComandosOSSeletivaInspecaoAnormalidadeHelper();

					helper.setIdComando((Integer) dadosComando[0]);
					helper.setDescComando((String) dadosComando[1]);
					helper.setDataExecucao((Date) dadosComando[2]);

					if (dadosComando[3] != null) {
						helper.setDataEncerramento((Date) dadosComando[3]);
					} else {
						// calcular data prevista
						Date dataPrevista = helper.getDataExecucao();
						if (qtdeDiasValidadeOSAnormalidadeFiscalizacao != null) {
							dataPrevista = Util.adicionarNumeroDiasDeUmaData(dataPrevista, qtdeDiasValidadeOSAnormalidadeFiscalizacao);
						}
						helper.setDataEncerramentoPrevista(dataPrevista);
					}

					Short situacaoComando = (Short) dadosComando[4];
					if (situacaoComando.equals(ConstantesSistema.NAO)) {
						helper.setSituacao("ENCERRADO");
					} else {
						helper.setSituacao("PENDENTE");
					}

					colecaoRetorno.add(helper);
				}
			}

			return colecaoRetorno;

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 12/07/2011
	 */
	public ComandoOrdemSeletiva pesquisarDadosComandoOSSeletiva(Integer idComandoOrdemSeletiva) throws ControladorException {
		try {

			return repositorioOrdemServico.pesquisarDadosComandoOSSeletiva(idComandoOrdemSeletiva);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 13/07/2011
	 */
	public Collection pesquisarDadosAnormalidadeComandoOSS(Integer idComandoOrdemSeletiva) throws ControladorException {
		try {

			return repositorioOrdemServico.pesquisarDadosAnormalidadeComandoOSS(idComandoOrdemSeletiva);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 13/07/2011
	 */
	public Collection pesquisarDadosCapacidHidrComandoOSS(Integer idComandoOrdemSeletiva) throws ControladorException {
		try {

			return repositorioOrdemServico.pesquisarDadosCapacidHidrComandoOSS(idComandoOrdemSeletiva);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 13/07/2011
	 */
	public Collection pesquisarDadosLigacaoSitComandoOSS(Integer idComandoOrdemSeletiva) throws ControladorException {
		try {

			return repositorioOrdemServico.pesquisarDadosLigacaoSitComandoOSS(idComandoOrdemSeletiva);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 15/07/2011
	 */
	public ComandoOrdemSeletiva pesquisarComandoOSSeletiva(Integer idComandoOrdemSeletiva) throws ControladorException {
		try {

			return repositorioOrdemServico.pesquisarComandoOSSeletiva(idComandoOrdemSeletiva);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1192] Movimentar OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @date 18/07/2011
	 */
	public Collection pesquisarDadosOSEmitir(Integer idComando, Integer numeroOSInicial, Integer numeroOSFinal) throws ControladorException {
		try {
			Collection colecaoretorno = null;

			Collection<Object[]> colecaoDadosOSEmitir = repositorioOrdemServico.pesquisarDadosOSEmitir(idComando, numeroOSInicial, numeroOSFinal);

			if (colecaoDadosOSEmitir != null && !colecaoDadosOSEmitir.isEmpty()) {
				colecaoretorno = new ArrayList();

				Iterator iterDadosOSEmitir = colecaoDadosOSEmitir.iterator();

				while (iterDadosOSEmitir.hasNext()) {
					Object[] dados = (Object[]) iterDadosOSEmitir.next();

					OSSeletivaInspecaoAnormalidadeGeradaHelper helper = new OSSeletivaInspecaoAnormalidadeGeradaHelper();

					if (dados[0] != null) {
						helper.setNumeroOS(((Integer) dados[0]).toString());
					}
					if (dados[1] != null) {
						helper.setTipoServico((String) dados[1]);
					}
					if (dados[2] != null) {
						helper.setMatriculaImovel(((Integer) dados[2]).toString());
					}
					if (dados[3] != null) {
						helper.setCliente((String) dados[3]);
					}

					colecaoretorno.add(helper);
				}
			}

			return colecaoretorno;

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1192] Movimentar OS Seletiva de Inspeção de Anormalidade [FS0001] -
	 * Verificar se ordem de serviço faz parte do comando
	 * 
	 * @author Vivianne Sousa
	 * @date 19/07/2011
	 */
	public String retornaOsNaoFazParteComando(Integer idComandoOrdemSeletiva, List<Integer> numerosOSPesquisar) throws ControladorException {
		try {

			String retorno = "";

			Collection colecaoFazParteComando = repositorioOrdemServico.verificaSeOSFazParteComando(idComandoOrdemSeletiva, numerosOSPesquisar);

			List<Integer> listFazParteComando = new ArrayList(colecaoFazParteComando);

			Iterator iter = numerosOSPesquisar.iterator();

			while (iter.hasNext()) {
				Integer dado = (Integer) iter.next();

				if (!listFazParteComando.contains(dado)) {
					retorno = retorno + dado + ", ";
				}
			}

			if (!retorno.equals("")) {
				retorno = Util.removerUltimosCaracteres(retorno, 2);
			}

			return retorno;

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1192] Movimentar OS Seletiva de Inspeção de Anormalidade [FS0003] –
	 * Verificar se imóvel faz parte do comando
	 * 
	 * @author Vivianne Sousa
	 * @date 19/07/2011
	 */
	public String retornaImovelNaoFazParteComando(Integer idComandoOrdemSeletiva, List<Integer> numerosImoveisPesquisar) throws ControladorException {
		try {

			String retorno = "";

			Collection colecaoFazParteComando = repositorioOrdemServico.verificaSeImovelFazParteComando(idComandoOrdemSeletiva, numerosImoveisPesquisar);

			List<Integer> listFazParteComando = new ArrayList(colecaoFazParteComando);

			Iterator iter = numerosImoveisPesquisar.iterator();

			while (iter.hasNext()) {
				Integer dado = (Integer) iter.next();

				if (!listFazParteComando.contains(dado)) {
					retorno = retorno + dado + ", ";
				}
			}

			if (!retorno.equals("")) {
				retorno = Util.removerUltimosCaracteres(retorno, 2);
			}

			return retorno;

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1192] Movimentar OS Seletiva de Inspeção de Anormalidade
	 * 
	 * Encerrar ordem(ns) de serviço.
	 * 
	 * @author: Vivianne Sousa
	 * @date: 20/07/2011
	 */
	public void movimentarOrdemServicoEncerrarOS(Usuario usuarioLogado, MovimentarOrdemServicoEncerrarOSHelper helper) throws ControladorException {

		if (helper.getColecaoOrdemServico() != null && !helper.getColecaoOrdemServico().isEmpty()) {
			Iterator iterator = helper.getColecaoOrdemServico().iterator();

			while (iterator.hasNext()) {
				OrdemServico ordemServico = (OrdemServico) iterator.next();

				Date dataEncerramento = helper.getDataEncerramento();

				String observacao = "Encerrada por Empresa de Inspeção de Anormalidade";

				if (helper.getObservacaoEncerramento() != null && !helper.getObservacaoEncerramento().trim().equals("")) {
					observacao = helper.getObservacaoEncerramento();
				}

				// [UC0457] - Encerrar Ordem de Serviço
				this.encerrarOSSemExecucao(ordemServico.getId(), dataEncerramento, usuarioLogado, helper.getIdMotivoEncerramento(), new Date(), observacao,
						null, null, null, null, null);
			}
		}

	}

	/**
	 * 
	 /**
	 * 
	 * @author Rodrigo Cabral
	 * @date 11/07/2011
	 * 
	 */
	public Object[] pesquisarIdFiscalizacaoSituacaoAgua(Integer idLigacaoAguaSituacao, Integer idSituacaoEncontrada) throws ControladorException {

		Object[] fiscalizacaoSituacaoAgua = null;
		try {

			fiscalizacaoSituacaoAgua = repositorioOrdemServico.pesquisarIdFiscalizacaoSituacaoAgua(idLigacaoAguaSituacao, idSituacaoEncontrada);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		return fiscalizacaoSituacaoAgua;
	}

	/**
	 * @author Rodrigo Cabral
	 * @date 11/07/2011
	 * 
	 */
	public Object[] pesquisarIdFiscalizacaoSituacaoEsgoto(Integer idLigacaoEsgotoSituacao, Integer idSituacaoEncontrada) throws ControladorException {

		Object[] fiscalizacaoSituacaoEsgoto = null;
		try {

			fiscalizacaoSituacaoEsgoto = repositorioOrdemServico.pesquisarIdFiscalizacaoSituacaoAgua(idLigacaoEsgotoSituacao, idSituacaoEncontrada);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		return fiscalizacaoSituacaoEsgoto;
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * @author Bruno Barros
	 * @date 28/06/2011
	 * 
	 * @param idsEquipes
	 *            - Id's das equipes que terão seus roteiros gerados. Caso esse
	 *            parametro venha nulo, iremos gerar de todas as equipes que
	 *            possuam OS programadas
	 * @param dataServico
	 *            - Data do serviço a ser gerado.
	 * 
	 * @throws ControladorException
	 */
	public void gerarArquivoAcompanharServicoRoteiroProgramado(Integer idFuncionalidadeIniciada, Integer idUnidadeOrganizacional, Date dataServico)
			throws ControladorException {

		int idUnidadeIniciada = 0;
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, UnidadeProcessamento.UNIDADE_NEGOCIO,
				idUnidadeOrganizacional);

		try {

			Collection<Integer> colEquipe = this.repositorioOrdemServico.pesquisarIdsEquipesPorUnidadeOrganizacional(idUnidadeOrganizacional);

			if (colEquipe != null && !colEquipe.isEmpty()) {
				// Como nenhuma equipe foi informada, pesquisamos no banco tudo
				// o que precisa
				// ser gerado para a data informada
				colEquipe = pesquisarEquipesOSNaoEnviadas(colEquipe, dataServico);

				// Inicialmente, zeramos a base
				excluirDadosArquivoAcompanharServicoRoteiroProgramado(idUnidadeOrganizacional, dataServico);

				// Para cada equipe...
				for (Integer idEquipe : colEquipe) {

					// [SB0001] - Inserir dados no Arquivo Texto por Equipe
					inserirArquivoTextoPorEquipe(idEquipe, dataServico);

					// [SB0002] - Inserir Ordem Programação Acompanhamento
					// Serviço.
					inserirOrdemProgramacoAcompanhamentoServico(idEquipe, dataServico);
				}
			}

			// --------------------------------------------------------
			//
			// Registrar o fim da execução da Unidade de Processamento
			//
			// --------------------------------------------------------
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
		} catch (Exception e) {
			e.printStackTrace();
			/*
			 * Este catch serve para interceptar qualquer exceção que o processo
			 * batch venha a lançar e garantir que a unidade de processamento do
			 * batch será atualizada com o erro ocorrido.
			 */
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
			throw new EJBException(e);
		}
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * Exclue o arquivo de uma equipe para uma determinada data
	 * 
	 * 
	 * @date 03/08/2011
	 * @author Bruno Barros
	 * 
	 * @param idEquipe
	 * @param dataProgramacao
	 * @throws ErroRepositorioException
	 */
	private void excluirDadosArquivoAcompanharServicoRoteiroProgramado(Integer idUnidadeOrganizacional, Date dataServico) throws ControladorException {
		try {

			Collection<Integer> idsArquivo = repositorioOrdemServico.pesquisarArquivoTextoAcompanhamentoServico(idUnidadeOrganizacional, dataServico);

			if (idsArquivo != null && !idsArquivo.isEmpty()) {
				for (Integer idArquivo : idsArquivo) {
					FiltroOSProgramacaoAcompanhamentoServico filtroOSProgramacaoAcompanhamentoServico = new FiltroOSProgramacaoAcompanhamentoServico();
					filtroOSProgramacaoAcompanhamentoServico.adicionarParametro(new ParametroSimples(FiltroOSProgramacaoAcompanhamentoServico.ID_ARQUIVO,
							idArquivo));
					Collection<OSProgramacaoAcompanhamentoServico> colOSProgramacaoAcompanhamentoServico = this.getControladorUtil().pesquisar(
							filtroOSProgramacaoAcompanhamentoServico, OSProgramacaoAcompanhamentoServico.class.getName());

					for (OSProgramacaoAcompanhamentoServico programacao : colOSProgramacaoAcompanhamentoServico) {

						this.repositorioOrdemServico.excluirOrdemServicoAtividadeAcompanharServicoRoteiroProgramado(programacao.getId());

						repositorioOrdemServico.excluirOSProgramadasAcompanharServicoRoteiroProgramado(programacao.getId());
					}

					repositorioOrdemServico.excluirDadosArquivoAcompanharServicoRoteiroProgramado(idArquivo);

					Collection<OrdemServicoProgramacao> colOSProgramacao = repositorioOrdemServico.pesquisarOSEnviadas(idUnidadeOrganizacional, dataServico);

					for (OrdemServicoProgramacao programacao : colOSProgramacao) {
						programacao.setIndicadorAcompanhamentoServico(ConstantesSistema.NAO);

						this.getControladorUtil().atualizar(programacao);
					}
				}
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * Pesquisa os id's das equipes que ainda possuem OS, para a data informada,
	 * que ainda não foram encaminhadas para o campo.
	 * 
	 * @author Bruno Barros
	 * @date 06/07/2011
	 * 
	 * @param dataRoteiro
	 *            - Data para a pesquisa das OS
	 * 
	 * @return Collection<Integer> - Coleção com todos os ID's das equipes.
	 * 
	 * @throws ControladorException
	 */
	private Collection<Integer> pesquisarEquipesOSNaoEnviadas(Collection<Integer> colsEquipes, Date dataRoteiro) throws ControladorException {

		Collection<Integer> colIdEquipes = null;

		try {
			colIdEquipes = this.repositorioOrdemServico.pesquisarEquipesOSNaoEnviadas(colsEquipes, dataRoteiro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return colIdEquipes;
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * [SB0001] ? Inserir dados no Arquivo Texto por Equipe
	 * 
	 * @author Bruno Barros
	 * @date 06/07/2011
	 * 
	 * @param idEquipe
	 *            - Id da equipe a ter um arquivo texto incluido
	 * @param dataRoteiro
	 *            - Data para a incluisão do arquivo texto
	 * 
	 * @return void...
	 * 
	 * @throws ControladorException
	 */
	private void inserirArquivoTextoPorEquipe(Integer idEquipe, Date dataRoteiro) throws ControladorException {

		try {
			Integer idArquivoTexto = repositorioOrdemServico.pesquisarIdArquivoTextoAcompanhamentoServico(idEquipe, dataRoteiro);
			if (idArquivoTexto == null || idArquivoTexto.equals("")) {

				ArquivoTextoAcompanhamentoServico arquivo = new ArquivoTextoAcompanhamentoServico();

				BigDecimal imei = repositorioOrdemServico.pesquisarIMEIEquipe(idEquipe);

				Equipe equipe = new Equipe();
				equipe.setId(idEquipe);

				arquivo.setDataProgramacao(dataRoteiro);
				arquivo.setDataUltimaAlteracao(new Date());
				arquivo.setEquipe(equipe);
				arquivo.setImei(imei);

				SituacaoTransmissaoLeitura situacao = new SituacaoTransmissaoLeitura();
				situacao.setId(SituacaoTransmissaoLeitura.LIBERADO);

				arquivo.setSituacaoTransmissaoLeitura(situacao);

				// Inserimos no banco
				RepositorioUtilHBM.getInstancia().inserir(arquivo);

				arquivo = null;
				equipe = null;
				imei = null;
				situacao = null;
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1211] Inserir Ordem Programação Acompanhamento Serviço
	 * 
	 * 
	 * @author Bruno Barros
	 * @date 06/07/2011
	 * 
	 * @param idEquipe
	 *            - Id da equipe a ter as ordens incluidas
	 * @param dataRoteiro
	 *            - Data para a incluisão das OS
	 * 
	 * @return void...
	 * 
	 * @throws ControladorException
	 */
	public void inserirOrdemProgramacoAcompanhamentoServico(Integer idEquipe, Date dataRoteiro) throws ControladorException {

		try {
			// Selecionamos as OS ainda não enviadas para campo
			// para equipe e data informadas...
			Collection<OrdemServicoProgramacao> colOSProgramacao = repositorioOrdemServico.pesquisarOSNaoEnviadas(idEquipe, dataRoteiro);

			for (OrdemServicoProgramacao programacao : colOSProgramacao) {

				// verifica se já existe essa ordem de serviço programada para a
				// data atual
				Integer idOSProgAcompServico = repositorioOrdemServico.pesquisarOSAcompServicoAtual(programacao.getOrdemServico().getId(), dataRoteiro);

				if (idOSProgAcompServico == null || idOSProgAcompServico.equals("")) {

					// 2.1. Inserir dados na tabela Ordem Serviço Programação
					// Acompanhamento Serviço
					// (ATENDIMENTOPUBLICO.OS_PRG_ACOMP_SERVICO):
					OSProgramacaoAcompanhamentoServico osProgramacaoAcompanhamentoServico = new OSProgramacaoAcompanhamentoServico();

					Equipe equipe = new Equipe();
					equipe.setId(idEquipe);
					osProgramacaoAcompanhamentoServico.setEquipe(equipe);

					Integer idArquivo = repositorioOrdemServico.pesquisarIdArquivoTextoAcompanhamentoServico(idEquipe, dataRoteiro);

					ArquivoTextoAcompanhamentoServico arquivo = new ArquivoTextoAcompanhamentoServico();
					arquivo.setId(idArquivo);

					osProgramacaoAcompanhamentoServico.setArquivoTextoAcompanhamentoServico(arquivo);
					osProgramacaoAcompanhamentoServico.setOrdemServico(programacao.getOrdemServico());
					osProgramacaoAcompanhamentoServico.setDataProgramacao(new Date());
					osProgramacaoAcompanhamentoServico.setSequencialProgramacao(programacao.getNnSequencialProgramacao());

					OrdemServicoSituacao ost = new OrdemServicoSituacao();
					if (programacao.getOrdemServico() != null && !programacao.getOrdemServico().equals("")) {
						ost.setId(new Integer("" + programacao.getOrdemServico().getSituacao()));
					}

					RegistroAtendimento ra = programacao.getOrdemServico().getRegistroAtendimento();
					Imovel imo = programacao.getOrdemServico().getImovel();

					osProgramacaoAcompanhamentoServico.setOrdemServicoSituacao(ost);

					if (imo != null) {
						osProgramacaoAcompanhamentoServico.setImovel(imo);
					}

					osProgramacaoAcompanhamentoServico.setDescricaoPontoReferencia(ra.getPontoReferencia());

					osProgramacaoAcompanhamentoServico.setNumeroImovel(ra.getNumeroImovel());

					Collection<RegistroAtendimentoSolicitante> colRASolicitante = this.getControladorRegistroAtendimento().obterRASolicitante(ra.getId());

					for (RegistroAtendimentoSolicitante raSolicitante : colRASolicitante) {
						if (raSolicitante.getIndicadorSolicitantePrincipal() == ConstantesSistema.SIM) {
							if (raSolicitante.getCliente() != null && raSolicitante.getCliente().getId() != null) {
								osProgramacaoAcompanhamentoServico.setNomeSolicitante(raSolicitante.getCliente().getNome());
							} else if (raSolicitante.getSolicitante() != null && raSolicitante.getSolicitante() != null) {
								osProgramacaoAcompanhamentoServico.setNomeSolicitante(raSolicitante.getSolicitante());
							} else {
								osProgramacaoAcompanhamentoServico.setNomeSolicitante(raSolicitante.getFuncionario().getNome());
							}

							break;
						}
					}

					colRASolicitante = null;

					osProgramacaoAcompanhamentoServico.setNumeroTelefone(this.getControladorRegistroAtendimento().obterTelefoneSolicitanteRA(ra.getId()));
					osProgramacaoAcompanhamentoServico.setDescricaoEndereco(this.getControladorRegistroAtendimento().obterEnderecoOcorrenciaRA(ra.getId()));

					if (osProgramacaoAcompanhamentoServico.getDescricaoEndereco() == null) {
						osProgramacaoAcompanhamentoServico.setDescricaoEndereco("testestestes");
					} else if (osProgramacaoAcompanhamentoServico.getDescricaoEndereco().length() > 100) {
						osProgramacaoAcompanhamentoServico.setDescricaoEndereco(osProgramacaoAcompanhamentoServico.getDescricaoEndereco().substring(0, 100));
					}

					if (imo != null) {
						osProgramacaoAcompanhamentoServico.setDescricaoLigacaoAguaSituacao(imo.getLigacaoAguaSituacao().getDescricao());
						osProgramacaoAcompanhamentoServico.setDescricaoLigacaoEsgotoSituacao(imo.getLigacaoEsgotoSituacao().getDescricao());
						osProgramacaoAcompanhamentoServico.setInscricaoImovel(this.getControladorImovel().pesquisarInscricaoImovel(imo.getId()));

						if (imo.getLigacaoAgua() != null && imo.getLigacaoAgua().getHidrometroInstalacaoHistorico() != null) {
							osProgramacaoAcompanhamentoServico.setNumeroHidrometro(imo.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro()
									.getNumero());
							osProgramacaoAcompanhamentoServico.setDescricaoHidrometroCapacidade(imo.getLigacaoAgua().getHidrometroInstalacaoHistorico()
									.getHidrometro().getHidrometroCapacidade().getDescricao());
						} else if (imo.getHidrometroInstalacaoHistorico() != null) {
							osProgramacaoAcompanhamentoServico.setNumeroHidrometro(imo.getHidrometroInstalacaoHistorico().getHidrometro().getNumero());
							osProgramacaoAcompanhamentoServico.setDescricaoHidrometroCapacidade(imo.getHidrometroInstalacaoHistorico().getHidrometro()
									.getHidrometroCapacidade().getDescricao());
						}
					}

					osProgramacaoAcompanhamentoServico.setServicoTipo(programacao.getOrdemServico().getServicoTipo());

					osProgramacaoAcompanhamentoServico.setIndicadorAtualizacaoOS(ConstantesSistema.ZERO);
					osProgramacaoAcompanhamentoServico.setIndicadorTrasmissaoOS(ConstantesSistema.NAO);

					osProgramacaoAcompanhamentoServico.setDataUltimaAlteracao(new Date());

					// Caso indicador de acompanhamento de serviço igual a 3,
					// então recebe 1.
					// Caso contrário, recebe 2.
					if (programacao.getIndicadorAcompanhamentoServico().equals(OrdemServicoProgramacao.INDICADOR_ACOMP_SERV_REALOCADA)) {
						osProgramacaoAcompanhamentoServico.setIndicadorExcluido(ConstantesSistema.SIM);
					} else {
						osProgramacaoAcompanhamentoServico.setIndicadorExcluido(ConstantesSistema.NAO);
					}

					osProgramacaoAcompanhamentoServico.setId((Integer) this.getControladorUtil().inserir(osProgramacaoAcompanhamentoServico));

					// //2.2. Inserir dados na tabela Ordem de Serviço Atividade
					// Programação Acompanhamento Serviço
					// (ATENDIMENTOPUBLICO.OS_AT_PRG_ACOMP_SERVICO):
					// FiltroOrdemServicoAtividade filtro = new
					// FiltroOrdemServicoAtividade();
					// filtro.adicionarParametro( new ParametroSimples(
					// FiltroOrdemServicoAtividade.ID_ORDEM_SERVICO,
					// osProgramacaoAcompanhamentoServico.getOrdemServico().getId()
					// ) );
					// Collection<OrdemServicoAtividade>
					// colOrdemServicoAtividade =
					// this.getControladorUtil().pesquisar( filtro,
					// OrdemServicoAtividade.class.getName() );
					//
					// for (OrdemServicoAtividade ordemServicoAtividade :
					// colOrdemServicoAtividade) {
					// OSAtividadeProgramacaoAcompanhamentoServico
					// osAtividadeProgramacaoAcompanhamentoServico =
					// new OSAtividadeProgramacaoAcompanhamentoServico();
					//
					// osAtividadeProgramacaoAcompanhamentoServico.setOsProgramacaoAcompanhamentoServico(
					// osProgramacaoAcompanhamentoServico );
					//
					// osAtividadeProgramacaoAcompanhamentoServico.setAtividade(
					// ordemServicoAtividade.getAtividade() );
					// osAtividadeProgramacaoAcompanhamentoServico.setOrdemServicoSituacao(
					// ost );
					// osAtividadeProgramacaoAcompanhamentoServico.setIndicadorAtualizacaoOS(
					// ConstantesSistema.NAO );
					// osAtividadeProgramacaoAcompanhamentoServico.setIndicadorTransmissaoOS(
					// ConstantesSistema.NAO );
					// osAtividadeProgramacaoAcompanhamentoServico.setDataUltimaAlteracao(
					// new Date() );
					// //Caso indicador de acompanhamento de serviço igual a 3,
					// então recebe 1.
					// //Caso contrário, recebe 2.
					// if(programacao.getIndicadorAcompanhamentoServico().equals(OrdemServicoProgramacao.INDICADOR_ACOMP_SERV_REALOCADA)){
					// osAtividadeProgramacaoAcompanhamentoServico.setIndicadorExcluido(ConstantesSistema.SIM);
					// }else{
					// osAtividadeProgramacaoAcompanhamentoServico.setIndicadorExcluido(ConstantesSistema.NAO);
					// }
					//
					// this.getControladorUtil().inserir(
					// osAtividadeProgramacaoAcompanhamentoServico );
					// }
				}

				programacao.setIndicadorAcompanhamentoServico(ConstantesSistema.SIM);
				this.getControladorUtil().atualizar(programacao);
			}

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * [SB0002] - Nome do arquivo texto
	 * 
	 * @author Bruno Barros
	 * @date 26/07/2011
	 * 
	 * @param Equipe
	 *            - equipe que teve seu arquivo gerado
	 * 
	 * @return String - retorna o nome do arquivo que será gerado
	 * 
	 * @throws ControladorException
	 */
	public String nomeArquivoAcompanhamentoServico(Integer idEquipe) throws ControladorException {
		return
		// AS ? Fixos as letras AS(Acompanhamento do Serviço);
		"AS" +
		// EE ? Código da Equipe
				idEquipe +
				// AAAA ? Ano da geração do Arquivo
				Util.getAno(new Date()) +
				// MM ? Mês da geração do Arquivo
				Util.adicionarZerosEsquedaNumero(2, Util.getMes(new Date()) + "") +
				// DD - Dia da geração do Arquivo
				Util.adicionarZerosEsquedaNumero(2, Util.getDiaMes(new Date()) + "") +
				// FIXO - TXT
				".txt";
	}

	/**
	 * [UC1212] Gerar Arquivo Texto para as Ordens de Serviço de Acompanhamento
	 * por Equipe
	 * 
	 * 
	 * @author Bruno Barros
	 * @date 26/07/2011
	 * 
	 * @param idEquipe
	 * @param dataRoteiro
	 * 
	 * @return String - retorna o arquivo
	 * 
	 * @throws ControladorException
	 */
	public byte[] gerarArquivoTextoOrdensServicoAcompanhamentoEquipe(Integer idEquipe, Date dataRoteiro, boolean gerarTabelasBasicas, boolean arquivoOnLine)
			throws ControladorException {

		StringBuilder retorno = new StringBuilder("");

		try {
			// Selecionamos as OS ainda não enviadas para campo
			// para equipe e data informadas...
			Collection<OSProgramacaoAcompanhamentoServico> colOSProgramacaoAcompanhamentoServico = repositorioOrdemServico.pesquisarOSAcompanhamentoServico(
					idEquipe, dataRoteiro, arquivoOnLine);

			if (gerarTabelasBasicas && colOSProgramacaoAcompanhamentoServico != null && colOSProgramacaoAcompanhamentoServico.size() > 0) {
				retorno.append(gerarArquivoTextoTabelasBasicasAcompanhamentoServicos(idEquipe, dataRoteiro));
			}

			for (OSProgramacaoAcompanhamentoServico programacaoAcompanhamentoServico : colOSProgramacaoAcompanhamentoServico) {
				// Gera os registros do tipo 11
				retorno.append(gerarRegistroTipo11(programacaoAcompanhamentoServico));

				// Selecionamos as atividades das OS ainda não enviadas para
				// campo
				// para equipe e data informadas...
				/*
				 * Collection<OSAtividadeProgramacaoAcompanhamentoServico>
				 * colAtividadeOSProgramacaoAcompanhamentoServico =
				 * repositorioOrdemServico
				 * .pesquisarAtividadeOSAcompanhamentoServico (
				 * programacaoAcompanhamentoServico.getId() );
				 * 
				 * for (OSAtividadeProgramacaoAcompanhamentoServico servico :
				 * colAtividadeOSProgramacaoAcompanhamentoServico) { // Gera os
				 * registros do tipo 2 retorno.append( gerarRegistroTipo12(
				 * servico, programacaoAcompanhamentoServico.getOrdemServico() )
				 * ); }
				 */

				programacaoAcompanhamentoServico.setIndicadorTrasmissaoOS(ConstantesSistema.SIM);

				this.getControladorUtil().atualizar(programacaoAcompanhamentoServico);
			}

			return retorno.toString().getBytes();
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * [SB0003] - Gerar Arquivo Texto para as Ordens de Serviço de
	 * Acompanhamento por Equipe
	 * 
	 * @author Bruno Barros
	 * @date 26/07/2011
	 * 
	 * @param Equipe
	 *            - equipe que teve seu arquivo gerado
	 * 
	 * @return String - retorna registros tipo 1
	 * 
	 * @throws ControladorException
	 */
	private String gerarRegistroTipo11(OSProgramacaoAcompanhamentoServico programacao) throws ControladorException {
		StringBuilder retorno = new StringBuilder("");

		OrdemServico os = programacao.getOrdemServico();
		Imovel imovel = os.getImovel();

		// Tipo do Registro
		retorno.append(Util.formatarCampoParaConcatenacao("11"));

		// Id da ordem de serviço
		retorno.append(Util.formatarCampoParaConcatenacao(os.getId()));

		// Data da Programacao
		retorno.append(Util.formatarCampoParaConcatenacao(Util.formatarData(new Date())));

		// Seqüencial de Programação da Ordem de Serviço
		retorno.append(Util.formatarCampoParaConcatenacao(programacao.getSequencialProgramacao()));

		// Situação da Ordem de Serviço
		retorno.append(Util.formatarCampoParaConcatenacao(programacao.getOrdemServicoSituacao().getId()));

		// Matrícula do Imóvel
		if (imovel != null)
			retorno.append(Util.formatarCampoParaConcatenacao(imovel.getId()));
		else
			retorno.append(Util.formatarCampoParaConcatenacao(null));

		// Ponto de Referência da execução da Ordem de Serviço
		retorno.append(Util.formatarCampoParaConcatenacao(programacao.getDescricaoPontoReferencia()));

		// Número do Imóvel da Execução da Ordem de Serviço
		retorno.append(Util.formatarCampoParaConcatenacao(programacao.getNumeroImovel()));

		// Nome do Solicitante da Abertura da RA
		retorno.append(Util.formatarCampoParaConcatenacao(programacao.getNomeSolicitante()));

		// Telefone do Solicitante da Abertura da RA
		retorno.append(Util.formatarCampoParaConcatenacao(programacao.getNumeroTelefone()));

		// Endereço Referência da Execução da OS
		retorno.append(Util.formatarCampoParaConcatenacao(programacao.getDescricaoEndereco()));

		// Situação da Ligação de Água
		retorno.append(Util.formatarCampoParaConcatenacao(programacao.getDescricaoLigacaoAguaSituacao()));

		// Situação da Ligação de Esgoto
		retorno.append(Util.formatarCampoParaConcatenacao(programacao.getDescricaoLigacaoEsgotoSituacao()));

		// Inscrição do Imóvel
		if (imovel != null)
			retorno.append(Util.formatarCampoParaConcatenacao(imovel.getInscricaoFormatada()));
		else
			retorno.append(Util.formatarCampoParaConcatenacao(null));

		// Número do Hidrômetro da Execução do Serviço
		retorno.append(Util.formatarCampoParaConcatenacao(programacao.getNumeroHidrometro()));

		// Descrição da Capacidade do Hidrômetro da Execução do Serviço
		retorno.append(Util.formatarCampoParaConcatenacao(programacao.getDescricaoHidrometroCapacidade()));

		// Tipo do Serviço
		if (os.getServicoTipo() != null)
			retorno.append(Util.formatarCampoParaConcatenacao(os.getServicoTipo().getId()));
		else
			retorno.append(Util.formatarCampoParaConcatenacao(null));

		// indicador de excluido
		retorno.append(Util.formatarCampoParaConcatenacao(programacao.getIndicadorExcluido()));

		retorno.append("\n");

		os = null;
		imovel = null;

		return retorno.toString();
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * [SB0003] - Gerar Arquivo Texto para as Ordens de Serviço de
	 * Acompanhamento por Equipe
	 * 
	 * @author Bruno Barros
	 * @date 26/07/2011
	 * 
	 * @param Equipe
	 *            - equipe que teve seu arquivo gerado
	 * 
	 * @return String - retorna registros tipo 1
	 * 
	 * @throws ControladorException
	 */
	private String gerarRegistroTipo12(OSAtividadeProgramacaoAcompanhamentoServico servico, OrdemServico os) throws ControladorException {

		StringBuilder retorno = new StringBuilder("");

		// Tipo do Registro
		retorno.append(Util.formatarCampoParaConcatenacao("12"));

		// Id da ordem de serviço
		retorno.append(Util.formatarCampoParaConcatenacao(os.getId()));

		// Id da Atividade
		retorno.append(Util.formatarCampoParaConcatenacao(servico.getOrdemServicoSituacao().getId()));

		// Id da Situação da OS
		retorno.append(Util.formatarCampoParaConcatenacao(os.getSituacao()));

		// indicador de excluido
		retorno.append(Util.formatarCampoParaConcatenacao(servico.getIndicadorExcluido()));

		retorno.append("\n");

		os = null;

		return retorno.toString();
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * [SB0003] - Nome do arquivo texto
	 * 
	 * @author Bruno Barros
	 * @date 26/07/2011
	 * 
	 * @param idEquipe
	 * @param dataRoteiro
	 * 
	 * @return String - retorna o arquivo
	 * 
	 * @throws ControladorException
	 */
	private String gerarArquivoTextoTabelasBasicasAcompanhamentoServicos(Integer idEquipe, Date dataRoteiro) throws ControladorException {

		StringBuilder retorno = new StringBuilder("");

		retorno.append(gerarRegistroTipo1());
		retorno.append(gerarRegistroTipo2());
		retorno.append(gerarRegistroTipo3());
		retorno.append(gerarRegistroTipo4());
		retorno.append(gerarRegistroTipo5());
		retorno.append(gerarRegistroTipo6());
		retorno.append(gerarRegistroTipo7());
		retorno.append(gerarRegistroTipo8());
		retorno.append(gerarRegistroTipo9());
		retorno.append(gerarRegistroTipo10(idEquipe));

		return retorno.toString();
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * [SB0004] - Gerar Arquivo Texto para as Ordens de Serviço de
	 * Acompanhamento por Equipe
	 * 
	 * @author Bruno Barros
	 * @date 26/07/2011
	 * 
	 * @param Equipe
	 *            - equipe que teve seu arquivo gerado
	 * 
	 * @return String - retorna registros tipo 5
	 * 
	 * @throws ControladorException
	 */
	private String gerarRegistroTipo1() throws ControladorException {
		// StringBuilder retorno = new StringBuilder( "" );

		FiltroOrdemServicoSituacao filtro = new FiltroOrdemServicoSituacao();
		filtro.adicionarParametro(new ParametroSimples(FiltroOrdemServicoSituacao.INDICADOR_USO, ConstantesSistema.SIM));
		Collection<OrdemServicoSituacao> colOrdemServicoSituacao = this.getControladorUtil().pesquisar(filtro, OrdemServicoSituacao.class.getName());

		StringBuilder retorno = new StringBuilder("");

		for (OrdemServicoSituacao situacao : colOrdemServicoSituacao) {
			retorno.append("01|");
			retorno.append(Util.formatarCampoParaConcatenacao(situacao.getId()));
			retorno.append(Util.formatarCampoParaConcatenacao(situacao.getDescricaoSituacao()));
			retorno.append(Util.formatarCampoParaConcatenacao(situacao.getDescricaoAbreviadaSituacao()) + "\n");
		}

		return retorno.toString();
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * [SB0004] - Gerar Arquivo Texto para as Ordens de Serviço de
	 * Acompanhamento por Equipe
	 * 
	 * @author Bruno Barros
	 * @date 26/07/2011
	 * 
	 * @param Equipe
	 *            - equipe que teve seu arquivo gerado
	 * 
	 * @return String - retorna registros tipo 6
	 * 
	 * @throws ControladorException
	 */
	private String gerarRegistroTipo2() throws ControladorException {

		FiltroOsProgramNaoEncerMotivo filtro = new FiltroOsProgramNaoEncerMotivo();
		filtro.adicionarParametro(new ParametroSimples(FiltroOsProgramNaoEncerMotivo.INDICADOR_USO, ConstantesSistema.SIM));
		Collection<OsProgramNaoEncerMotivo> colOsProgramNaoEncerMotivo = this.getControladorUtil().pesquisar(filtro, OsProgramNaoEncerMotivo.class.getName());

		StringBuilder retorno = new StringBuilder("");

		for (OsProgramNaoEncerMotivo encerramento : colOsProgramNaoEncerMotivo) {
			retorno.append("02|");
			retorno.append(Util.formatarCampoParaConcatenacao(encerramento.getId()));
			retorno.append(Util.formatarCampoParaConcatenacao(encerramento.getDescricao()) + "\n");
		}

		return retorno.toString();

	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * [SB0004] - Gerar Arquivo Texto para as Ordens de Serviço de
	 * Acompanhamento por Equipe
	 * 
	 * @author Bruno Barros
	 * @date 26/07/2011
	 * 
	 * @param Equipe
	 *            - equipe que teve seu arquivo gerado
	 * 
	 * @return String - retorna registros tipo 7
	 * 
	 * @throws ControladorException
	 */
	private String gerarRegistroTipo3() throws ControladorException {
		FiltroServicoTipo filtro = new FiltroServicoTipo();
		filtro.adicionarParametro(new ParametroSimples(FiltroServicoTipo.INDICADOR_USO, ConstantesSistema.SIM));
		filtro.adicionarParametro(new ParametroSimples(FiltroServicoTipo.INDICADOR_PROGRAMACAO_AUTOMATICA, ConstantesSistema.SIM));
		Collection<ServicoTipo> colServicoTipo = this.getControladorUtil().pesquisar(filtro, ServicoTipo.class.getName());

		StringBuilder retorno = new StringBuilder("");

		for (ServicoTipo servicoTipo : colServicoTipo) {
			retorno.append("03|");
			retorno.append(Util.formatarCampoParaConcatenacao(servicoTipo.getId()));
			retorno.append(Util.formatarCampoParaConcatenacao(servicoTipo.getDescricao()) + "\n");
		}

		return retorno.toString();
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * [SB0004] - Gerar Arquivo Texto para as Ordens de Serviço de
	 * Acompanhamento por Equipe
	 * 
	 * @author Bruno Barros
	 * @date 26/07/2011
	 * 
	 * @param Equipe
	 *            - equipe que teve seu arquivo gerado
	 * 
	 * @return String - retorna registros tipo 8
	 * 
	 * @throws ControladorException
	 */
	private String gerarRegistroTipo4() throws ControladorException {
		FiltroAtividade filtro = new FiltroAtividade();
		filtro.adicionarParametro(new ParametroSimples(FiltroAtividade.INDICADORUSO, ConstantesSistema.SIM));
		Collection<Atividade> colAtividade = this.getControladorUtil().pesquisar(filtro, Atividade.class.getName());

		StringBuilder retorno = new StringBuilder("");

		for (Atividade atividade : colAtividade) {
			retorno.append("04|");
			retorno.append(Util.formatarCampoParaConcatenacao(atividade.getId()));
			retorno.append(Util.formatarCampoParaConcatenacao(atividade.getDescricao()));
			retorno.append(Util.formatarCampoParaConcatenacao(atividade.getDescricaoAbreviada()) + "\n");
		}

		return retorno.toString();
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * [SB0004] - Gerar Arquivo Texto para as Ordens de Serviço de
	 * Acompanhamento por Equipe
	 * 
	 * @author Bruno Barros
	 * @date 26/07/2011
	 * 
	 * @param Equipe
	 *            - equipe que teve seu arquivo gerado
	 * 
	 * @return String - retorna registros tipo 9
	 * 
	 * @throws ControladorException
	 */
	private String gerarRegistroTipo6() throws ControladorException {
		FiltroMaterial filtro = new FiltroMaterial();
		filtro.adicionarParametro(new ParametroSimples(FiltroMaterial.INDICADOR_USO, ConstantesSistema.SIM));
		Collection<Material> colMaterial = this.getControladorUtil().pesquisar(filtro, Material.class.getName());

		StringBuilder retorno = new StringBuilder("");

		for (Material material : colMaterial) {
			retorno.append("06|");
			retorno.append(Util.formatarCampoParaConcatenacao(material.getId()));
			retorno.append(Util.formatarCampoParaConcatenacao(material.getDescricao()));
			retorno.append(Util.formatarCampoParaConcatenacao(material.getDescricaoAbreviada()));
			retorno.append(Util.formatarCampoParaConcatenacao(material.getMaterialUnidade().getId()));
			retorno.append(Util.formatarCampoParaConcatenacao(material.getCodigo()) + "\n");
		}

		return retorno.toString();
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * [SB0004] - Gerar Arquivo Texto para as Ordens de Serviço de
	 * Acompanhamento por Equipe
	 * 
	 * @author Bruno Barros
	 * @date 26/07/2011
	 * 
	 * @param Equipe
	 *            - equipe que teve seu arquivo gerado
	 * 
	 * @return String - retorna registros tipo 10
	 * 
	 * @throws ControladorException
	 */
	private String gerarRegistroTipo5() throws ControladorException {
		FiltroMaterialUnidade filtro = new FiltroMaterialUnidade();
		// filtro.adicionarParametro( new ParametroSimples(
		// FiltroMaterialUnidade.INDICADOR_USO, ConstantesSistema.SIM ) );
		Collection<MaterialUnidade> colMaterialUnidade = this.getControladorUtil().pesquisar(filtro, MaterialUnidade.class.getName());

		StringBuilder retorno = new StringBuilder("");

		for (MaterialUnidade materialUnidade : colMaterialUnidade) {
			retorno.append("05|");
			retorno.append(Util.formatarCampoParaConcatenacao(materialUnidade.getId()));
			retorno.append(Util.formatarCampoParaConcatenacao(materialUnidade.getDescricao()));
			retorno.append(Util.formatarCampoParaConcatenacao(materialUnidade.getDescricaoAbreviada()) + "\n");
		}

		return retorno.toString();
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * [SB0004] - Gerar Arquivo Texto para as Ordens de Serviço de
	 * Acompanhamento por Equipe
	 * 
	 * @author Bruno Barros
	 * @date 26/07/2011
	 * 
	 * @param Equipe
	 *            - equipe que teve seu arquivo gerado
	 * 
	 * @return String - retorna registros tipo 11
	 * 
	 * @throws ControladorException
	 */
	private String gerarRegistroTipo7() throws ControladorException {
		StringBuilder retorno = new StringBuilder("");
		FiltroEquipamentosEspeciais filtro = new FiltroEquipamentosEspeciais();
		filtro.adicionarParametro(new ParametroSimples(FiltroEquipamentosEspeciais.INDICADORUSO, ConstantesSistema.SIM));
		Collection<EquipamentosEspeciais> colEquipamentosEspeciais = this.getControladorUtil().pesquisar(filtro, EquipamentosEspeciais.class.getName());

		for (EquipamentosEspeciais equipamentosEspeciais : colEquipamentosEspeciais) {
			retorno.append("07|");
			retorno.append(Util.formatarCampoParaConcatenacao(equipamentosEspeciais.getId()));
			retorno.append(Util.formatarCampoParaConcatenacao(equipamentosEspeciais.getDescricao()) + "\n");
		}

		return retorno.toString();
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * [SB0004] - Gerar Arquivo Texto para as Ordens de Serviço de
	 * Acompanhamento por Equipe
	 * 
	 * @author Sávio Luiz
	 * @date 18/08/2011
	 * 
	 * @param Equipe
	 *            - equipe que teve seu arquivo gerado
	 * 
	 * @return String - retorna registros tipo 13
	 * 
	 * @throws ControladorException
	 */
	private String gerarRegistroTipo8() throws ControladorException {
		StringBuilder retorno = new StringBuilder("");
		FiltroServicoTerceiroAcompanhamentoServico filtro = new FiltroServicoTerceiroAcompanhamentoServico();
		filtro.adicionarParametro(new ParametroSimples(FiltroServicoTerceiroAcompanhamentoServico.INDICADOR_EXCEDENTE, ConstantesSistema.NAO));
		Collection<ServicoTerceiroAcompanhamentoServico> colServicoTerceiroAcompanhamentoServico = this.getControladorUtil().pesquisar(filtro,
				ServicoTerceiroAcompanhamentoServico.class.getName());

		for (ServicoTerceiroAcompanhamentoServico servicoTerceiroAcompanhamentoServico : colServicoTerceiroAcompanhamentoServico) {
			retorno.append("08|");
			retorno.append(Util.formatarCampoParaConcatenacao(servicoTerceiroAcompanhamentoServico.getId()));
			retorno.append(Util.formatarCampoParaConcatenacao(servicoTerceiroAcompanhamentoServico.getCodigoServico()));
			retorno.append(Util.formatarCampoParaConcatenacao(servicoTerceiroAcompanhamentoServico.getDescricaoServico()) + "\n");
		}

		return retorno.toString();
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * [SB0004] - Gerar Arquivo Texto para as Ordens de Serviço de
	 * Acompanhamento por Equipe
	 * 
	 * @author Sávio Luiz
	 * @date 18/08/2011
	 * 
	 * @param Equipe
	 *            - equipe que teve seu arquivo gerado
	 * 
	 * @return String - retorna registros tipo 13
	 * 
	 * @throws ControladorException
	 */
	private String gerarRegistroTipo9() throws ControladorException {
		StringBuilder retorno = new StringBuilder("");
		FiltroFotoSituacaoOrdemServico filtro = new FiltroFotoSituacaoOrdemServico();
		Collection<FotoSituacaoOrdemServico> colFotoSituacaoOrdemServico = this.getControladorUtil()
				.pesquisar(filtro, FotoSituacaoOrdemServico.class.getName());

		for (FotoSituacaoOrdemServico fotoSituacaoOrdemServico : colFotoSituacaoOrdemServico) {
			retorno.append("09|");
			retorno.append(Util.formatarCampoParaConcatenacao(fotoSituacaoOrdemServico.getId()));
			retorno.append(Util.formatarCampoParaConcatenacao(fotoSituacaoOrdemServico.getDescricao()) + "\n");
		}

		return retorno.toString();
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * [SB0004] - Gerar Arquivo Texto para as Ordens de Serviço de
	 * Acompanhamento por Equipe
	 * 
	 * @author Sávio Luiz
	 * @date 18/08/2011
	 * 
	 * @param Equipe
	 *            - equipe que teve seu arquivo gerado
	 * 
	 * @return String - retorna registros tipo 14
	 * 
	 * @throws ControladorException
	 */
	private String gerarRegistroTipo10(Integer idEquipe) throws ControladorException {
		StringBuilder retorno = new StringBuilder("");
		FiltroEquipe filtro = new FiltroEquipe();
		filtro.adicionarParametro(new ParametroSimples(FiltroEquipe.ID, idEquipe));
		filtro.adicionarCaminhoParaCarregamentoEntidade("usuarioRespExecServico");
		Collection<Equipe> colEquipe = this.getControladorUtil().pesquisar(filtro, Equipe.class.getName());

		for (Equipe equipe : colEquipe) {
			// recuperar login e senha
			String loginUsuario = "gsan";
			if (equipe.getUsuarioRespExecServico() != null) {
				loginUsuario = equipe.getUsuarioRespExecServico().getLogin();
			}
			String senhaGerada = "senha";
			String senhaCriptografada = null;
			try {
				senhaCriptografada = Criptografia.encriptarSenha(senhaGerada);
			} catch (ErroCriptografiaException e1) {
				throw new ControladorException("erro.criptografia.senha");
			}

			retorno.append("10|");
			retorno.append(Util.formatarCampoParaConcatenacao(equipe.getId()));
			retorno.append(Util.formatarCampoParaConcatenacao(equipe.getNome()));
			retorno.append(Util.formatarCampoParaConcatenacao(loginUsuario));
			retorno.append(Util.formatarCampoParaConcatenacao(senhaCriptografada) + "\n");

		}

		return retorno.toString();
	}

	/**
	 * [UC1199] – Acompanhar Arquivos de Roteiro
	 * 
	 * Pesquisa o Arquivo Texto do Acompanhamento de Serviço
	 * 
	 * @author Thúlio Araújo
	 * @date 19/07/2011
	 * 
	 * @param periodoProgramacaoInicial
	 * @param periodoProgramacaoFinal
	 * @param idEmpresa
	 * @param idSituacao
	 * 
	 * @returnCollection <ArquivoTextoAcompanhamentoServico>
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<AcompanhamentoArquivosRoteiroHelper> pesquisarAcompanhamentoArquivosRoteiro(String dataProgramacao, String idEmpresa, String idSituacao,
			Integer idUnidOrganizacional) throws ControladorException {

		Collection<AcompanhamentoArquivosRoteiroHelper> colecaoAcompanhamentoArquivosRoteiro = new ArrayList();
		Collection<OSProgramacaoAcompanhamentoServico> colecaoOsProgramacaoAcompanhamentoServico = new ArrayList();

		try {
			Collection colecaoDadosArquivosRoteiro = repositorioOrdemServico.pesquisarAcompanhamentoArquivosRoteiro(dataProgramacao, idEmpresa, idSituacao,
					idUnidOrganizacional);
			if (colecaoDadosArquivosRoteiro != null && !colecaoDadosArquivosRoteiro.isEmpty()) {
				Iterator itDadosArquivos = colecaoDadosArquivosRoteiro.iterator();
				Object[] dadosArquivo = null;
				while (itDadosArquivos.hasNext()) {
					AcompanhamentoArquivosRoteiroHelper acompanhamentoArquivosRoteiroHelper = new AcompanhamentoArquivosRoteiroHelper();
					dadosArquivo = (Object[]) itDadosArquivos.next();
					if (dadosArquivo != null) {
						acompanhamentoArquivosRoteiroHelper.setIdAcompanhamentoArquivosRoteiro(Integer.toString((Integer) dadosArquivo[0]));
						acompanhamentoArquivosRoteiroHelper.setNmEquipe((String) dadosArquivo[1]);
						acompanhamentoArquivosRoteiroHelper.setIdEquipe(Integer.toString((Integer) dadosArquivo[2]));
						acompanhamentoArquivosRoteiroHelper.setQtdOrdemServicoProgramadas(Integer.toString((Integer) dadosArquivo[3]));
						acompanhamentoArquivosRoteiroHelper.setQtdOrdemServicoTransmitidas(Integer.toString((Integer) dadosArquivo[4]));
						acompanhamentoArquivosRoteiroHelper.setDsSituacao((String) dadosArquivo[5]);

						Collection colecaoDadosOsProgramacaoAcompServico = repositorioOrdemServico
								.pesquisarOSProgramacaoAcompServico(acompanhamentoArquivosRoteiroHelper.getIdAcompanhamentoArquivosRoteiro());
						if (colecaoDadosOsProgramacaoAcompServico != null && !colecaoDadosOsProgramacaoAcompServico.isEmpty()) {
							Iterator itDadosOsProgramacaoAcompServico = colecaoDadosOsProgramacaoAcompServico.iterator();
							Object[] dadosOsProgramacaoAcompServico = null;
							while (itDadosOsProgramacaoAcompServico.hasNext()) {
								OSProgramacaoAcompanhamentoServicoHelper osProgramacaoAcompanhamentoServicoHelper = new OSProgramacaoAcompanhamentoServicoHelper();
								dadosOsProgramacaoAcompServico = (Object[]) itDadosOsProgramacaoAcompServico.next();
								if (dadosOsProgramacaoAcompServico != null) {
									osProgramacaoAcompanhamentoServicoHelper.setIdOsProgramacaoAcompanhamentoServico(Integer
											.toString((Integer) dadosOsProgramacaoAcompServico[0]));
									osProgramacaoAcompanhamentoServicoHelper.setIdOrdemServico(Integer.toString((Integer) dadosOsProgramacaoAcompServico[1]));
									osProgramacaoAcompanhamentoServicoHelper.setDsEndereco((String) dadosOsProgramacaoAcompServico[2]);
									osProgramacaoAcompanhamentoServicoHelper.setDsSituacao((String) dadosOsProgramacaoAcompServico[3]);
									osProgramacaoAcompanhamentoServicoHelper.setNnSequencialProgramacao(Integer
											.toString((Integer) dadosOsProgramacaoAcompServico[4]));
									osProgramacaoAcompanhamentoServicoHelper.setDsServicoTipo((String) dadosOsProgramacaoAcompServico[5]);
								}
								acompanhamentoArquivosRoteiroHelper.getOsProgramacaoAcompServicoHelper().add(osProgramacaoAcompanhamentoServicoHelper);
							}
						}
					}
					colecaoAcompanhamentoArquivosRoteiro.add(acompanhamentoArquivosRoteiroHelper);
				}
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return colecaoAcompanhamentoArquivosRoteiro;
	}

	/**
	 * [UC1197] Encerrar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @created 21/07/2011
	 */
	public void encerrarComandoOSSeletivaInspecaoAnormalidade(Integer idFuncionalidadeIniciada, Usuario usuarioLogado, Integer idComando,
			Short idMotivoEncerramento) throws ControladorException {

		int idUnidadeIniciada = 0;

		try {
			/*
			 * Registrar o início do processamento da Unidade de Processamento
			 * do Batch
			 */
			idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, UnidadeProcessamento.FUNCIONALIDADE, 0);

			boolean flagFimPesquisa = false;
			final int quantidadeImoveis = 1000;
			int quantidadeInicio = 0;

			System.out.println("***************************************");
			System.out.println("ENCERRAR COMANDOS");
			System.out.println("***************************************");

			while (!flagFimPesquisa) {
				Collection idsOrdemServico = this.repositorioOrdemServico.pesquisaOrdemServicoFazParteComando(quantidadeInicio, idComando);

				if (idsOrdemServico != null && !idsOrdemServico.isEmpty()) {

					Iterator iterOrdemServico = idsOrdemServico.iterator();

					if (idsOrdemServico.size() < quantidadeImoveis) {
						flagFimPesquisa = true;
					} else {
						quantidadeInicio = quantidadeInicio + 1000;
					}

					System.out.println("***************************************");
					System.out.println("QUANTIDADE: " + idsOrdemServico.size());
					System.out.println("***************************************");

					while (iterOrdemServico.hasNext()) {
						Integer idOrdemServico = (Integer) iterOrdemServico.next();

						Date dataAtual = new Date();

						// [UC0457] - Encerrar Ordem de Serviço
						this.encerrarOSSemExecucao(idOrdemServico, dataAtual, usuarioLogado, idMotivoEncerramento.toString(), dataAtual, null, null, null,
								null, null, null);
					}

				} else {
					flagFimPesquisa = true;
				}

			}

			this.repositorioOrdemServico.atualizarDataEncerramentoComando(idComando);

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
			System.out.println("******* FIM **********");
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			throw new EJBException(ex);
		}

	}

	/**
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
	 * 
	 * @author Sávio Luiz
	 * @date 19/07/2011
	 */
	public void inserirProgramacaoAutomaticaAcompanhamentoOS(Integer idUnidadeOrganizacional, Date dataProgramacao, int idFuncionalidadeIniciada)
			throws ControladorException {

		// -------------------------
		//
		// Registrar o início do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		int idUnidadeIniciada = 0;
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, UnidadeProcessamento.UNIDADE_NEGOCIO,
				idUnidadeOrganizacional);

		try {

			// apaga o que foi programado para o mesmo dia da programação
			excluirDadosArquivoAcompanharServicoRoteiroProgramado(idUnidadeOrganizacional, dataProgramacao);
			repositorioOrdemServico.excluirOSProgramadas(idUnidadeOrganizacional, dataProgramacao);

			// recupera o usuário da rotina batch
			Usuario usuarioBatch = getControladorUsuario().pesquisarUsuarioRotinaBatch();

			// pesquisar data de programação anterior
			Date dataProgramacaoAnterior = repositorioOrdemServico.pesquisarDataAnteriorProgramacaoRoteiro(idUnidadeOrganizacional);
			if (dataProgramacaoAnterior == null || dataProgramacaoAnterior.equals("")) {
				dataProgramacaoAnterior = Util.subtrairNumeroDiasDeUmaData(new Date(), 1);
			}

			// pesquisar se existem ordens de serviços transmitidas que ainda
			// não foram atualizadas
			Collection collOSTransmitidasNaoAtualizadas = repositorioOrdemServico.pesquisarOSAcompServicoTransmitidasNaoAtualizadas(idUnidadeOrganizacional,
					dataProgramacaoAnterior);
			if (collOSTransmitidasNaoAtualizadas != null && !collOSTransmitidasNaoAtualizadas.isEmpty()) {
				// [SB0001] Atualiza Ordem de Serviço Programação Pendentes
				//
				// [UC1227] Atualizar Ordens Serviço de Acompanhamento de
				// Celular
				//
				// Método que irá atualizar as ordens de serviço para encerradas

				this.atualizarOrdemServicoAcompanhamentoServico(dataProgramacaoAnterior, null, idUnidadeOrganizacional);
			}

			// 2. Caso existam OS com situação diferente de encerradas
			Collection<OSProgramacaoAcompanhamentoServico> collOSAcompServicoNaoEncerradas = repositorioOrdemServico.pesquisarOSAcompServicoNaoENcerradas(
					idUnidadeOrganizacional, dataProgramacaoAnterior);
			if (collOSAcompServicoNaoEncerradas != null && !collOSAcompServicoNaoEncerradas.isEmpty()) {
				for (OSProgramacaoAcompanhamentoServico osProgramacaoAcompanhamentoServico : collOSAcompServicoNaoEncerradas) {
					// verifica se já existe essa ordem de serviço programada
					// para a data atual
					Integer idOSProgAcompServico = repositorioOrdemServico.pesquisarOSProgramacaoAtual(osProgramacaoAcompanhamentoServico.getOrdemServico()
							.getId(), dataProgramacao);

					if (idOSProgAcompServico == null || idOSProgAcompServico.equals("")) {
						// Caso o motivo do não encerramento seja diferente de
						// null e
						// seja 'Falta de Equipamento' ou 'Equipamento Quebrado'
						if (osProgramacaoAcompanhamentoServico.getOsProgramacaoNaoEncerramentoMotivo() != null
								&& osProgramacaoAcompanhamentoServico.getOsProgramacaoNaoEncerramentoMotivo().getId() != null
								&& !osProgramacaoAcompanhamentoServico.getOsProgramacaoNaoEncerramentoMotivo().getId().equals("")
								&& (osProgramacaoAcompanhamentoServico.getOsProgramacaoNaoEncerramentoMotivo().getId()
										.equals(OsProgramNaoEncerMotivo.EQUIPAMENTO_QUEBRADO) || osProgramacaoAcompanhamentoServico
										.getOsProgramacaoNaoEncerramentoMotivo().getId().equals(OsProgramNaoEncerMotivo.FALTA_EQUIPAMENTO))) {
							// Se existe equipamento faltante na tabela de os
							// programação acompanhamento serviço
							Equipe equipe = null;
							if (osProgramacaoAcompanhamentoServico.getEquipamentosEspeciaisFaltante() != null) {
								equipe = repositorioOrdemServico.pesquisarEquipeComEquipamentoEspecial(idUnidadeOrganizacional,
										osProgramacaoAcompanhamentoServico.getEquipamentosEspeciaisFaltante().getId());
							}

							// Caso exista equipe com equipamento para execução
							// do serviço
							if (equipe != null && !equipe.equals("")) {
								// [SB0002] Inserir Ordem de Serviço na
								// Programação
								this.inserirOrdemServicoProgramacao(idUnidadeOrganizacional, dataProgramacao, equipe, osProgramacaoAcompanhamentoServico
										.getOrdemServico().getId(), usuarioBatch);
							}
						} else {
							// [SB0002] Inserir Ordem de Serviço na Programação
							this.inserirOrdemServicoProgramacao(idUnidadeOrganizacional, dataProgramacao, null, osProgramacaoAcompanhamentoServico
									.getOrdemServico().getId(), usuarioBatch);
						}
					}
				}

			}

			// 3. Verificar se existe alguma ordem de serviço pendente que o
			// motivo de não execução seja
			// Falta de Equipamento ou Equipammento Quebrado
			Collection<OSProgramacaoAcompanhamentoServico> collOSAcompServicoNaoEncerradasMotivo = repositorioOrdemServico
					.pesquisarOSAcompServicoNaoENcerradasMotivo(idUnidadeOrganizacional);
			if (collOSAcompServicoNaoEncerradasMotivo != null && !collOSAcompServicoNaoEncerradasMotivo.isEmpty()) {
				for (OSProgramacaoAcompanhamentoServico osProgramacaoAcompanhamentoServico : collOSAcompServicoNaoEncerradasMotivo) {
					// verifica se já existe essa ordem de serviço programada
					// para a data atual
					Integer idOSProgAcompServico = repositorioOrdemServico.pesquisarOSProgramacaoAtual(osProgramacaoAcompanhamentoServico.getOrdemServico()
							.getId(), dataProgramacao);

					if (idOSProgAcompServico == null || idOSProgAcompServico.equals("")) {
						// Se existe equipamento faltante na tabela de os
						// programação acompanhamento serviço
						Equipe equipe = null;
						if (osProgramacaoAcompanhamentoServico.getEquipamentosEspeciaisFaltante() != null) {
							equipe = repositorioOrdemServico.pesquisarEquipeComEquipamentoEspecial(idUnidadeOrganizacional, osProgramacaoAcompanhamentoServico
									.getEquipamentosEspeciaisFaltante().getId());
						}

						// Caso exista equipe com equipamento para execução do
						// serviço
						if (equipe != null && !equipe.equals("")) {
							// [SB0002] Inserir Ordem de Serviço na Programação
							this.inserirOrdemServicoProgramacao(idUnidadeOrganizacional, dataProgramacao, equipe, osProgramacaoAcompanhamentoServico
									.getOrdemServico().getId(), usuarioBatch);
						}
					}
				}

			}

			// 4. Calcular o valor de prioridade das ordens de serviços que
			// ainda não programadas
			this.inserirPrioridadeProgramacaoOrdemServico(idUnidadeOrganizacional);

			// 5. Para cada ordem de serviço priorizada agrupada por unidade
			// organizacional e ordenada pelo fator de priorização decrescente
			Collection<Integer> collOrdemServico = repositorioOrdemServico.pesquisarOSFatorPrioridadeDecrescente(idUnidadeOrganizacional);
			if (collOrdemServico != null && !collOrdemServico.isEmpty()) {
				for (Integer idOrdemServico : collOrdemServico) {
					// [SB0002] Inserir Ordem de Serviço na Programação
					boolean temEquipeComCargaTrabalho = this.inserirOrdemServicoProgramacao(idUnidadeOrganizacional, dataProgramacao, null, idOrdemServico,
							usuarioBatch);
					// caso não tenha mais carga de trabalho para nenhuma equipe
					// da unidade organizacional,
					// então finaliza o batch
					if (!temEquipeComCargaTrabalho) {
						break;
					}
				}
			}

			// --------------------------------------------------------
			//
			// Registrar o fim da execução da Unidade de Processamento
			//
			// --------------------------------------------------------
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
		} catch (Exception e) {
			e.printStackTrace();
			/*
			 * Este catch serve para interceptar qualquer exceção que o processo
			 * batch venha a lançar e garantir que a unidade de processamento do
			 * batch será atualizada com o erro ocorrido.
			 */
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
			throw new EJBException(e);
		}
	}

	/**
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
	 * 
	 * [SB0004] Recuperar Equipe Pela Ordem de Serviço
	 * 
	 * @author Sávio Luiz
	 * @date 19/07/2011 ---
	 */
	private Equipe recuperarEquipePelaOS(Integer idProgramacaoRoteiro, Integer idOrdemServico, Integer idUnidadeOrganizacional) throws ControladorException {
		Equipe equipe = null;
		try {
			// Obter a coleção de equipes pela unidade organizacional
			Collection collEquipes = repositorioOrdemServico.obterEquipesPorUnidade(idUnidadeOrganizacional);
			if (collEquipes != null && !collEquipes.isEmpty()) {
				Iterator it = collEquipes.iterator();
				Integer cargaTrabalhoEquipeSelecionada = null;
				Integer tempoMedioExEquipeSelecionada = null;

				equipe = new Equipe();
				while (it.hasNext()) {
					Object[] idNomeEquipe = (Object[]) it.next();
					if (idNomeEquipe != null) {
						Integer idEquipe = (Integer) idNomeEquipe[0];

						Object[] dadosEquipe = repositorioOrdemServico.pesquisarTempoMedioOSProgramacaoComDataRoteiroUnidade(idProgramacaoRoteiro, idEquipe);

						if (dadosEquipe != null && !dadosEquipe.equals("")) {
							Integer cargaTrabalhoEquipe = null;
							if (dadosEquipe[0] != null) {
								cargaTrabalhoEquipe = (Integer) dadosEquipe[0];
							}
							Integer tpMedioExcServicosEquipe = null;
							if (dadosEquipe[1] != null) {
								tpMedioExcServicosEquipe = new Integer("" + (Short) dadosEquipe[1]);
							}

							if (cargaTrabalhoEquipe != null && tpMedioExcServicosEquipe != null && cargaTrabalhoEquipe > tpMedioExcServicosEquipe) {

								ServicoTipo servicoTipo = repositorioOrdemServico.recuperaServicoTipoDaOrdemServico(idOrdemServico);

								if (servicoTipo != null) {

									Integer tmMedioExcOS = new Integer("" + servicoTipo.getTempoMedioExecucao());

									Integer tempoEquipeSelecionada = null;
									if (cargaTrabalhoEquipeSelecionada != null && tempoMedioExEquipeSelecionada != null) {

										tempoEquipeSelecionada = cargaTrabalhoEquipeSelecionada - tempoMedioExEquipeSelecionada;

										Integer tempoEquipeProcessada = cargaTrabalhoEquipe - (tmMedioExcOS + tpMedioExcServicosEquipe);

										if (tempoEquipeProcessada > tempoEquipeSelecionada) {
											if (tempoEquipeProcessada >= 0) {
												equipe.setId(idEquipe);

												cargaTrabalhoEquipeSelecionada = cargaTrabalhoEquipe;

												tempoMedioExEquipeSelecionada = tmMedioExcOS + tpMedioExcServicosEquipe;
											}
										}

									} else {
										tempoEquipeSelecionada = cargaTrabalhoEquipe - (tmMedioExcOS + tpMedioExcServicosEquipe);
										if (tempoEquipeSelecionada >= 0) {
											equipe.setId(idEquipe);
											cargaTrabalhoEquipeSelecionada = cargaTrabalhoEquipe;

											tempoMedioExEquipeSelecionada = tmMedioExcOS + tpMedioExcServicosEquipe;
										}

									}
								}
							}
						} else {
							ServicoTipo servicoTipo = repositorioOrdemServico.recuperaServicoTipoDaOrdemServico(idOrdemServico);

							if (servicoTipo != null) {
								Integer cargaTrabalho = (Integer) idNomeEquipe[2];
								if (cargaTrabalho > new Integer("" + servicoTipo.getTempoMedioExecucao())) {
									equipe.setId(idEquipe);
									break;
								}
							}

						}
					}
				}
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		return equipe;
	}

	// public void inserirPrioridadeProgramacaoOrdemServico(Integer
	// idUnidadeOrganizacional,Integer idOrdemServico) throws
	// ControladorException {
	// try{
	//
	// //pesquisa a existência de ordens de serviço para a programação atual
	// Collection colecaoOSPrograacao =
	// repositorioOrdemServico.pesquisarProgramacaoRoteiro(idUnidadeOrganizacional,dataProgramacao);
	//
	// }catch (ErroRepositorioException e) {
	// sessionContext.setRollbackOnly();
	// throw new ControladorException("erro.sistema", e);
	// }
	// }

	/**
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
	 * 
	 * [SB0002] Inserir Ordem de Serviço na Programação
	 * 
	 * @author Sávio Luiz
	 * @date 19/07/2011
	 */
	private boolean inserirOrdemServicoProgramacao(Integer idUnidadeOrganizacional, Date dataProgramacao, Equipe equipe, Integer idOrdemServico,
			Usuario usuarioBatch) throws ControladorException {
		boolean temEquipeComCargaTrabalho = true;
		try {
			UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
			unidadeOrganizacional.setId(idUnidadeOrganizacional);

			// pesquisa a existência programação para data atual e a unidade
			// organizacional
			ProgramacaoRoteiro programacaoRoteiro = repositorioOrdemServico.pesquisarProgramacaoRoteiro(idUnidadeOrganizacional, dataProgramacao);

			// caso não exista programação, então inserir uma programação
			if (programacaoRoteiro == null || programacaoRoteiro.equals("")) {
				programacaoRoteiro = new ProgramacaoRoteiro();
				programacaoRoteiro.setDataRoteiro(dataProgramacao);
				programacaoRoteiro.setUnidadeOrganizacional(unidadeOrganizacional);
				programacaoRoteiro.setUltimaAlteracao(new Date());
				Integer idProgramacaoRoteiro = (Integer) getControladorUtil().inserir(programacaoRoteiro);
				programacaoRoteiro.setId(idProgramacaoRoteiro);
			}

			// Inserir dados em ordem de serviço programação
			OrdemServicoProgramacao ordemServicoProgramacao = new OrdemServicoProgramacao();
			ordemServicoProgramacao.setProgramacaoRoteiro(programacaoRoteiro);

			OrdemServico ordemServico = new OrdemServico();
			ordemServico.setId(idOrdemServico);
			ordemServicoProgramacao.setOrdemServico(ordemServico);

			// caso a equipe seja diferente de nulo
			if (equipe != null && !equipe.equals("")) {
				ordemServicoProgramacao.setEquipe(equipe);
			} else {
				// [SB0005] Recuperar Equipe Pela Ordem de Serviço
				equipe = this.recuperarEquipePelaOS(programacaoRoteiro.getId(), idOrdemServico, idUnidadeOrganizacional);
				ordemServicoProgramacao.setEquipe(equipe);
			}
			// caso tenha mais equipe com carga de trabalho para a unidade
			// organizacional
			if (equipe != null && !equipe.equals("") && equipe.getId() != null) {
				ordemServicoProgramacao.setUsuarioProgramacao(usuarioBatch);

				// pesquisar o sequencial máximo de programacao
				short sequencialProgramacao = repositorioOrdemServico.pesquisarMaiorSequencialOSProgramacao(programacaoRoteiro.getId(), equipe.getId());
				sequencialProgramacao = new Integer(sequencialProgramacao + 1).shortValue();

				// seta o sequencial máximo mais um
				ordemServicoProgramacao.setNnSequencialProgramacao(sequencialProgramacao);

				// seta o indicador de ativo para um
				ordemServicoProgramacao.setIndicadorAtivo(ConstantesSistema.SIM);
				// seta a equipe principal para um
				ordemServicoProgramacao.setIndicadorEquipePrincipal(ConstantesSistema.SIM);
				// seta a equipe principal para um
				ordemServicoProgramacao.setIndicadorAcompanhamentoServico(ConstantesSistema.NAO);
				// seta a equipe principal para um
				ordemServicoProgramacao.setUltimaAlteracao(new Date());

				// inserir a ordem de serviço programação
				getControladorUtil().inserir(ordemServicoProgramacao);

				// atualiza o indicador de programação da ordem de serviço
				repositorioOrdemServico.atualizarIndicadorOSProgramada(idOrdemServico);
			} else {
				temEquipeComCargaTrabalho = false;
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			// throw new ControladorException("erro.sistema", e);
		}
		return temEquipeComCargaTrabalho;
	}

	/**
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
	 * 
	 * [SB0003] Programação Automática das Ordens de Serviço por Prioridade
	 * 
	 * @author Sávio Luiz
	 * @date 19/07/2011
	 */
	private void inserirPrioridadeProgramacaoOrdemServico(Integer idUnidadeOrganizacional) throws ControladorException {
		try {

			// pesquisa a existência de ordens de serviço para a programação
			// atual
			Collection<Object[]> colecaoOSProgramacao = repositorioOrdemServico.pesquisarOSProgramacaoAutomatica(idUnidadeOrganizacional);

			// caso a coleção de ordem de serviço para programação esteja
			// diferente de nulo
			if (colecaoOSProgramacao != null && !colecaoOSProgramacao.isEmpty()) {
				for (Object[] dadosProgramacao : colecaoOSProgramacao) {
					if (dadosProgramacao != null) {
						// inicializaçao dos campos para o calculo da
						// priorização
						Integer pesoLocalizacao = 1;
						Integer fatorLocalizacao = 1;
						Integer pesoDiametro = 1;
						Integer fatorDiametro = 1;
						Integer pesoReiteracao = 1;
						Integer fatorReiteracao = 1;
						Integer pesoReincidencia = 1;
						Integer fatorReincidencia = 1;
						Integer pesoTempo = 1;
						Integer fatorTempo = 1;
						Integer pesoTipoServico = 1;
						Integer fatorTipoServico = 1;

						// recupera a ordem de Serviço
						Integer idOrdemServico = (Integer) dadosProgramacao[0];
						// recupera a data de geração da ordem de Serviço
						Date dataGeracaoOS = (Date) dadosProgramacao[7];
						// recupera a os calibragem do logradouro do bairro
						Integer idOSCalibragemLogBairro = null;
						if (dadosProgramacao[1] != null) {
							idOSCalibragemLogBairro = (Integer) dadosProgramacao[1];
							Object[] dadosCalibragem = repositorioOrdemServico.pesquisarDadosOSCalibragem(OSPriorizacaoTipo.LOCALIZACAO_SERVICO,
									idOSCalibragemLogBairro, null);
							if (dadosCalibragem != null) {
								// peso da os calibragem
								if (dadosCalibragem[0] != null) {
									pesoLocalizacao = (Integer) dadosCalibragem[0];
								}
								// fator da os calibragem
								if (dadosCalibragem[1] != null) {
									fatorLocalizacao = (Integer) dadosCalibragem[1];
								}
							}
						} else {
							// recupera a os calibragem do logradouro do Cep
							Integer idOSCalibragemLogCep = null;
							if (dadosProgramacao[2] != null) {
								idOSCalibragemLogCep = (Integer) dadosProgramacao[2];
								idOSCalibragemLogBairro = (Integer) dadosProgramacao[1];
								Object[] dadosCalibragem = repositorioOrdemServico.pesquisarDadosOSCalibragem(OSPriorizacaoTipo.LOCALIZACAO_SERVICO,
										idOSCalibragemLogCep, null);
								if (dadosCalibragem != null) {
									// peso da os calibragem
									if (dadosCalibragem[0] != null) {
										pesoLocalizacao = (Integer) dadosCalibragem[0];
									}
									// fator da os calibragem
									if (dadosCalibragem[1] != null) {
										fatorLocalizacao = (Integer) dadosCalibragem[1];
									}
								}
							}
						}
						// recupera o número de diametro da rede
						Integer numeroDiametroRede = null;
						if (dadosProgramacao[3] != null) {
							numeroDiametroRede = new Integer("" + ((BigDecimal) dadosProgramacao[3]));

							Object[] dadosCalibragem = repositorioOrdemServico.pesquisarDadosOSCalibragem(OSPriorizacaoTipo.DIAMETRO_REDE, null,
									numeroDiametroRede);
							if (dadosCalibragem != null) {
								// peso da os calibragem
								if (dadosCalibragem[0] != null) {
									pesoDiametro = (Integer) dadosCalibragem[0];
								}
								// fator da os calibragem
								if (dadosCalibragem[1] != null) {
									fatorDiametro = (Integer) dadosCalibragem[1];
								}
							}
						}
						// recupera a quantidade de reiterações
						Integer quantidadeReiteracoes = 0;
						if (dadosProgramacao[4] != null) {
							quantidadeReiteracoes = new Integer("" + (Short) dadosProgramacao[4]);
						}

						Object[] dadosCalibragemReit = repositorioOrdemServico.pesquisarDadosOSCalibragem(OSPriorizacaoTipo.REITERACAO, null,
								quantidadeReiteracoes);
						if (dadosCalibragemReit != null) {
							// peso da os calibragem
							if (dadosCalibragemReit[0] != null) {
								pesoReiteracao = (Integer) dadosCalibragemReit[0];
							}
							// fator da os calibragem
							if (dadosCalibragemReit[1] != null) {
								fatorReiteracao = (Integer) dadosCalibragemReit[1];
							}
						}

						// recupera a quantidade de reativação
						Integer quantidadeReativacao = 0;
						if (dadosProgramacao[5] != null) {
							Integer idRegistroAtendimento = (Integer) dadosProgramacao[5];
							quantidadeReativacao = repositorioOrdemServico.pesquisarQuantidadeRAReativacao(idRegistroAtendimento);
						}

						Object[] dadosCalibragemRea = repositorioOrdemServico.pesquisarDadosOSCalibragem(OSPriorizacaoTipo.REINCIDENCIA, null,
								quantidadeReativacao);
						if (dadosCalibragemRea != null) {
							// peso da os calibragem
							if (dadosCalibragemRea[0] != null) {
								pesoReincidencia = (Integer) dadosCalibragemRea[0];
							}
							// fator da os calibragem
							if (dadosCalibragemRea[1] != null) {
								fatorReincidencia = (Integer) dadosCalibragemRea[1];
							}
						}

						// recupera a os calibragem do serviço tipo
						Integer idOSCalibragemServicoTipo = null;
						if (dadosProgramacao[6] != null) {
							idOSCalibragemServicoTipo = (Integer) dadosProgramacao[6];

							Object[] dadosCalibragem = repositorioOrdemServico.pesquisarDadosOSCalibragem(OSPriorizacaoTipo.TIPO_SERVICO,
									idOSCalibragemServicoTipo, null);
							if (dadosCalibragem != null) {
								// peso da os calibragem
								if (dadosCalibragem[0] != null) {
									pesoTipoServico = (Integer) dadosCalibragem[0];
								}
								// fator da os calibragem
								if (dadosCalibragem[1] != null) {
									fatorTipoServico = (Integer) dadosCalibragem[1];
								}
							}
						}

						// recupera a quantidade de dias da os aberta
						Integer qtdDiasOSAberta = Util.obterQuantidadeDiasEntreDuasDatas(dataGeracaoOS, new Date());
						Object[] dadosCalibragem = repositorioOrdemServico.pesquisarDadosOSCalibragem(OSPriorizacaoTipo.DIAS_OS_ABERTA, null, qtdDiasOSAberta);
						if (dadosCalibragem != null) {
							// peso da os calibragem
							if (dadosCalibragem[0] != null) {
								pesoTempo = (Integer) dadosCalibragem[0];
							}
							// fator da os calibragem
							if (dadosCalibragem[1] != null) {
								fatorTempo = (Integer) dadosCalibragem[1];
							}
						}
						// calcular o valor da priorização da OS
						Integer valorPriorizacao = (pesoLocalizacao * fatorLocalizacao) + (pesoDiametro * fatorDiametro) + (pesoReiteracao * fatorReiteracao)
								+ (pesoReincidencia * fatorReincidencia) + (pesoTempo * fatorTempo) + (pesoTipoServico * fatorTipoServico);

						// Atualiza o valor da priorização na Ordem de Serviço
						repositorioOrdemServico.atualizarFatorPrioridadeOS(idOrdemServico, valorPriorizacao);
					}

				}
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			// throw new ControladorException("erro.sistema", e);
		}
	}

	// public void inserirPrioridadeProgramacaoOrdemServico(Integer
	// idUnidadeOrganizacional,Integer idOrdemServico) throws
	// ControladorException {
	// try{
	//
	// //pesquisa a existência de ordens de serviço para a programação atual
	// Collection colecaoOSPrograacao =
	// repositorioOrdemServico.pesquisarProgramacaoRoteiro(idUnidadeOrganizacional,dataProgramacao);
	//
	// }catch (ErroRepositorioException e) {
	// sessionContext.setRollbackOnly();
	// throw new ControladorException("erro.sistema", e);
	// }
	// }

	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @date 26/07/2011
	 */
	public Integer pesquisaOrdemServicoNaoPendenteFazParteComando(Integer idComandoOrdemSeletiva) throws ControladorException {
		try {

			return repositorioOrdemServico.pesquisaOrdemServicoNaoPendenteFazParteComando(idComandoOrdemSeletiva);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0412] Manter Tipo de Serviço
	 * 
	 * Metodo responsável por deletar motivos de encerramento a partir de um
	 * tipo de serviço
	 * 
	 * @author Raimundo Martins
	 * @date 26/07/2011
	 * 
	 * @param idServicoTipo
	 * 
	 */
	public void removerServicoTipoMotivoEncerramento(Integer idServicoTipo) throws ControladorException {
		try {
			repositorioOrdemServico.removerServicoTipoMotivoEncerramento(idServicoTipo);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0412] Manter Tipo de Serviço
	 * 
	 * Metodo responsável por inserir um motivo de encerramento para um tipo de
	 * serviço
	 * 
	 * @author Raimundo Martins
	 * @date 27/07/2011
	 * 
	 * @param servicoTipoMotivoEncerramento
	 * @throws ControladorException
	 * 
	 */
	public void inserirServicoTipoMotivoEncerramento(ServicoTipoMotivoEncerramento servicoTipoMotivoEncerramento) throws ControladorException {
		try {
			this.getControladorUtil().inserir(servicoTipoMotivoEncerramento);
		} catch (ControladorException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
	 * 
	 * @author Sávio Luiz
	 * @date 30/07/2011
	 */
	public Collection pequisarUnidadesOrganizacionaisdasEquipes() throws ControladorException {
		try {
			return repositorioOrdemServico.pequisarUnidadesOrganizacionaisdasEquipes();
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1199] Acompanhamento de Arquivos de Roteiro
	 * 
	 * @author Thúlio Araújo
	 * @date 28/07/2011
	 * 
	 * @param ids
	 * @param situacaoNova
	 */
	public void atualizarArquivoTextoAcompArqRoteiro(Vector<Integer> ids, Integer situacaoNova) throws ControladorException {
		try {
			Iterator<Integer> it = ids.iterator();
			while (it.hasNext()) {
				Integer id = it.next();
				if (situacaoNova.equals(SituacaoTransmissaoLeitura.LIBERADO)) {
					FiltroOSProgramacaoAcompanhamentoServico filtroOSProgramacaoAcompanhamentoServico = new FiltroOSProgramacaoAcompanhamentoServico();
					filtroOSProgramacaoAcompanhamentoServico.adicionarParametro(new ParametroSimples(FiltroOSProgramacaoAcompanhamentoServico.ID_ARQUIVO, id));
					filtroOSProgramacaoAcompanhamentoServico.adicionarParametro(new ParametroSimplesDiferenteDe(
							FiltroOSProgramacaoAcompanhamentoServico.IC_EXCLUIDO, ConstantesSistema.SIM));
					filtroOSProgramacaoAcompanhamentoServico.adicionarCaminhoParaCarregamentoEntidade("ordemServicoSituacao");
					filtroOSProgramacaoAcompanhamentoServico.adicionarCaminhoParaCarregamentoEntidade("equipe");
					Collection<OSProgramacaoAcompanhamentoServico> colOSProgramacaoAcompanhamentoServico = this.getControladorUtil().pesquisar(
							filtroOSProgramacaoAcompanhamentoServico, OSProgramacaoAcompanhamentoServico.class.getName());

					Iterator iter = colOSProgramacaoAcompanhamentoServico.iterator();

					while (iter.hasNext()) {
						OSProgramacaoAcompanhamentoServico oSProgramacao = (OSProgramacaoAcompanhamentoServico) iter.next();
						if (oSProgramacao.getOrdemServicoSituacao().getId() == OrdemServico.SITUACAO_EXECUCAO_EM_ANDAMENTO.intValue()) {
							// throw new
							// ControladorException("atencao.nao_possivel.liberar_osexecucao",null,
							// oSProgramacao.getEquipe().getNome());
						}
					}

					iter = colOSProgramacaoAcompanhamentoServico.iterator();

					while (iter.hasNext()) {
						OSProgramacaoAcompanhamentoServico oSProgramacaoAcompanhamentoServico = (OSProgramacaoAcompanhamentoServico) iter.next();
						if (oSProgramacaoAcompanhamentoServico.getOrdemServicoSituacao().getId() == OrdemServico.SITUACAO_PENDENTE.intValue()) {
							oSProgramacaoAcompanhamentoServico.setIndicadorTrasmissaoOS(ConstantesSistema.NAO);
							Fachada.getInstancia().atualizar(oSProgramacaoAcompanhamentoServico);
						}
					}

					this.repositorioOrdemServico.atualizarArquivoTextoAcompArqRoteiro(id, situacaoNova);
				} else if (situacaoNova.equals(SituacaoTransmissaoLeitura.TRANSMITIDO)) {
					this.repositorioOrdemServico.atualizarArquivoTextoAcompArqRoteiro(id, situacaoNova);
				} else if (situacaoNova.equals(SituacaoTransmissaoLeitura.EM_CAMPO)) {
					FiltroOSProgramacaoAcompanhamentoServico filtroOSProgramacaoAcompanhamentoServico = new FiltroOSProgramacaoAcompanhamentoServico();
					filtroOSProgramacaoAcompanhamentoServico.adicionarParametro(new ParametroSimples(FiltroOSProgramacaoAcompanhamentoServico.ID_ARQUIVO, id));
					filtroOSProgramacaoAcompanhamentoServico.adicionarParametro(new ParametroSimplesDiferenteDe(
							FiltroOSProgramacaoAcompanhamentoServico.IC_EXCLUIDO, ConstantesSistema.SIM));
					filtroOSProgramacaoAcompanhamentoServico.adicionarCaminhoParaCarregamentoEntidade("ordemServicoSituacao");
					filtroOSProgramacaoAcompanhamentoServico.adicionarCaminhoParaCarregamentoEntidade("equipe");
					Collection<OSProgramacaoAcompanhamentoServico> colOSProgramacaoAcompanhamentoServico = this.getControladorUtil().pesquisar(
							filtroOSProgramacaoAcompanhamentoServico, OSProgramacaoAcompanhamentoServico.class.getName());

					Iterator iter = colOSProgramacaoAcompanhamentoServico.iterator();

					while (iter.hasNext()) {
						OSProgramacaoAcompanhamentoServico oSProgramacao = (OSProgramacaoAcompanhamentoServico) iter.next();
						if (oSProgramacao.getOrdemServicoSituacao().getId() != OrdemServico.SITUACAO_ENCERRADO.intValue()) {
							oSProgramacao.setIndicadorTrasmissaoOS(ConstantesSistema.SIM);
							Fachada.getInstancia().atualizar(oSProgramacao);
						}
					}
					this.repositorioOrdemServico.atualizarArquivoTextoAcompArqRoteiro(id, situacaoNova);
				}
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1192] Movimentar OS Seletiva de Inspeção de Anormalidade Verificar se
	 * ordem de serviço que faz parte do comando ja esta encerrada
	 * 
	 * @author Vivianne Sousa
	 * @date 02/08/2011
	 */
	public String retornaOsJaEncerrada(List<Integer> numerosOSPesquisar) throws ControladorException {
		try {

			String retorno = "";

			Collection colecaoOSJaEncerrada = repositorioOrdemServico.verificaSeOSJaEncerrada(numerosOSPesquisar);

			List<Integer> listOSJaEncerrada = new ArrayList(colecaoOSJaEncerrada);

			Iterator iter = numerosOSPesquisar.iterator();

			while (iter.hasNext()) {
				Integer dado = (Integer) iter.next();

				if (listOSJaEncerrada.contains(dado)) {
					retorno = retorno + dado + ", ";
				}
			}

			if (!retorno.equals("")) {
				retorno = Util.removerUltimosCaracteres(retorno, 2);
			}

			return retorno;

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [SB00009] - Inserir Faturamento Situação Histórico
	 * 
	 * Método auxiliar responsável por inserir um faturamento na situação
	 * histórico.
	 * 
	 * @param idImovel
	 * @param usuarioLogado
	 * @throws ControladorException
	 */
	private void inserirDadosFaturamentoSituacaoHistorico(Integer idImovel, Usuario usuarioLogado) throws ErroRepositorioException {

		FaturamentoSituacaoHistorico faturamentoSituacaoHistorico = new FaturamentoSituacaoHistorico();

		// IMOVEL
		Imovel imovel = new Imovel();
		imovel.setId(idImovel);
		faturamentoSituacaoHistorico.setImovel(imovel);

		Date dataAtual = new Date();
		// ANO_MES_INICIAL = ano/mês da data atual
		faturamentoSituacaoHistorico.setAnoMesFaturamentoSituacaoInicio(Util.getAnoMesComoInteger(dataAtual));

		// ANO_MES_FINAL = ano/mês da (data atual + 30 dias)
		faturamentoSituacaoHistorico.setAnoMesFaturamentoSituacaoFim(Util.getAnoMesComoInteger(Util.adicionarNumeroDiasDeUmaData(dataAtual, 30)));

		// ANO_MES_FATURAMENTO_RETIRADA = NULL
		faturamentoSituacaoHistorico.setAnoMesFaturamentoRetirada(null);

		// FATURAMENTO_SITUACAO_MOTIVO
		FaturamentoSituacaoMotivo faturamentoSituacaoMotivo = new FaturamentoSituacaoMotivo();
		faturamentoSituacaoMotivo.setId(FaturamentoSituacaoMotivo.INSPECAO_ESGOTO);
		faturamentoSituacaoHistorico.setFaturamentoSituacaoMotivo(faturamentoSituacaoMotivo);

		// FATURAMENTO_SITUACAO_TIPO
		FaturamentoSituacaoTipo faturamentoSituacaoTipo = new FaturamentoSituacaoTipo();
		faturamentoSituacaoTipo.setId(FaturamentoSituacaoTipo.PARALISAR_FATURAMENTO_DE_ESGOTO);
		faturamentoSituacaoHistorico.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);

		// USUÁRIO
		faturamentoSituacaoHistorico.setUsuario(usuarioLogado);

		// USUÁRIO INFORMA
		faturamentoSituacaoHistorico.setUsuarioInforma(usuarioLogado);

		faturamentoSituacaoHistorico.setUltimaAlteracao(new Date());

		try {
			// INSERINDO
			this.getControladorUtil().inserir(faturamentoSituacaoHistorico);
			this.repositorioImovel.atualizarSituacaoEspecialFaturamentoImovel(idImovel, FaturamentoSituacaoTipo.PARALISAR_FATURAMENTO_DE_ESGOTO,
					FaturamentoSituacaoMotivo.INSPECAO_ESGOTO);
		} catch (ControladorException e) {
			throw new ErroRepositorioException(e.getMessage());
		}
	}

	/**
	 * [UC1213] Emitir Relatorio de Ordem de Servico de Fiscalizacao
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 06/08/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarOrdensServicoFiscalizacao(int tipoRelatorio, String periodoInicial, String periodoFinal, String idGerenciaRegional,
			String idUnidadeNegocios, String idLocalidadeInicial, String idLocalidadeFinal, String situacaoOS, String idOSReferidaRetornoTipo,
			String aceitacaoDaOS) throws ControladorException {

		try {
			return this.repositorioOrdemServico.pesquisarOrdensServicoFiscalizacao(tipoRelatorio, periodoInicial, periodoFinal, idGerenciaRegional,
					idUnidadeNegocios, idLocalidadeInicial, idLocalidadeFinal, situacaoOS, idOSReferidaRetornoTipo, aceitacaoDaOS);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC1199] – Acompanhar Arquivos de Roteiro
	 * 
	 * Pesquisa a data da OS Programacao Acompanhamento Servico filtrando pelo
	 * identificador da Ordem de Serviço
	 * 
	 * @author Raimundo Martins
	 * @date 09/08/2011
	 * 
	 * @param idOrdemServiço
	 * @return Lista com as OSProgramacaoAcompanhamentoServico
	 * @throws ControladorException
	 */

	public Collection<Integer> pesquisarEquipeOSProgramacaoAcompServicoPorIdOrdemServico(Integer idOrdemServico) throws ControladorException {
		try {
			Collection<Integer> retorno;
			retorno = repositorioOrdemServico.pesquisarEquipeOSProgramacaoAcompServicoPorIdOrdemServico(idOrdemServico);

			if (retorno.size() <= 0) {
				throw new ControladorException("atencao.pesquisa.nenhumresultado");
			} else {
				return retorno;
			}

		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1204] Programação Automática do Roteiro para Acompanhamento das OS
	 * 
	 * [SB0002] Inserir Ordem de Serviço na Programação
	 * 
	 * @author Thúlio Araújo
	 * @date 19/07/2011
	 */
	public ProgramacaoRoteiro pesquisarProgramacaoRoteiro(Integer idUnidadeOrganizacional, Date dataProgramacao) throws ControladorException {
		ProgramacaoRoteiro retorno = null;

		try {
			retorno = repositorioOrdemServico.pesquisarProgramacaoRoteiro(idUnidadeOrganizacional, dataProgramacao);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		return retorno;
	}

	/**
	 * [UC1211] Atualizar Ordem Programação Acompanhamento Serviço
	 * 
	 * 
	 * @author Thúlio Araújo
	 * @date 22/08/2011
	 * 
	 * @param idEquipe
	 *            - Id da equipe atual
	 * @param dataRoteiro
	 *            - Data para a incluisão das OS
	 * 
	 * @return void...
	 * 
	 * @throws ControladorException
	 */
	public void atualizarOrdemProgramacaoAcompanhamentoServico(Integer idEquipeAtual, Date dataRoteiro, Integer idOrdemServico) throws ControladorException {

		try {
			// Selecionamos as OS ainda não enviadas para campo
			// para equipe e data informadas...
			Collection<OrdemServicoProgramacao> colOSProgramacao = repositorioOrdemServico.pesquisarOSNaoEnviadasRemanejadas(idEquipeAtual, dataRoteiro,
					idOrdemServico);

			for (OrdemServicoProgramacao programacao : colOSProgramacao) {

				Collection<OSProgramacaoAcompanhamentoServico> colecao = this.repositorioOrdemServico.pesquisarOSProgramacaoAcompServicoPorEquipeOS(
						idOrdemServico, dataRoteiro, idEquipeAtual);

				if (colecao != null && !colecao.isEmpty()) {

					Iterator<?> colecaoIt = colecao.iterator();
					OSProgramacaoAcompanhamentoServico osProgramacaoAcompanhamentoServico = (OSProgramacaoAcompanhamentoServico) colecaoIt.next();
					osProgramacaoAcompanhamentoServico.setIndicadorTrasmissaoOS(ConstantesSistema.NAO);
					osProgramacaoAcompanhamentoServico.setDataUltimaAlteracao(new Date());

					osProgramacaoAcompanhamentoServico.setSequencialProgramacao(programacao.getNnSequencialProgramacao());

					// Caso indicador de acompanhamento de serviço igual a 3,
					// então recebe 1.
					// Caso contrário, recebe 2.
					if (programacao.getIndicadorAcompanhamentoServico().equals(OrdemServicoProgramacao.INDICADOR_ACOMP_SERV_REALOCADA)) {
						osProgramacaoAcompanhamentoServico.setIndicadorExcluido(ConstantesSistema.SIM);
					} else {
						osProgramacaoAcompanhamentoServico.setIndicadorExcluido(ConstantesSistema.NAO);
					}

					this.getControladorUtil().atualizar(osProgramacaoAcompanhamentoServico);

					// 2.2. Inserir dados na tabela Ordem de Serviço Atividade
					// Programação Acompanhamento Serviço
					// (ATENDIMENTOPUBLICO.OS_AT_PRG_ACOMP_SERVICO):
					FiltroOSAtividadeProgramacaoAcompanhamentoServico filtro = new FiltroOSAtividadeProgramacaoAcompanhamentoServico();
					filtro.adicionarParametro(new ParametroSimples(FiltroOSAtividadeProgramacaoAcompanhamentoServico.ID_OS_ACOMP_SERVICO,
							osProgramacaoAcompanhamentoServico.getId()));
					Collection<OSAtividadeProgramacaoAcompanhamentoServico> colOrdemServicoAtividade = this.getControladorUtil().pesquisar(filtro,
							OSAtividadeProgramacaoAcompanhamentoServico.class.getName());

					for (OSAtividadeProgramacaoAcompanhamentoServico osAtividadeProgramacaoAcompanhamentoServico : colOrdemServicoAtividade) {

						osAtividadeProgramacaoAcompanhamentoServico.setOsProgramacaoAcompanhamentoServico(osProgramacaoAcompanhamentoServico);

						osAtividadeProgramacaoAcompanhamentoServico.setDataUltimaAlteracao(new Date());
						// Caso indicador de acompanhamento de serviço igual a
						// 3, então recebe 1.
						// Caso contrário, recebe 2.
						if (programacao.getIndicadorAcompanhamentoServico().equals(OrdemServicoProgramacao.INDICADOR_ACOMP_SERV_REALOCADA)) {
							osAtividadeProgramacaoAcompanhamentoServico.setIndicadorExcluido(ConstantesSistema.SIM);
						} else {
							osAtividadeProgramacaoAcompanhamentoServico.setIndicadorExcluido(ConstantesSistema.NAO);
						}

						this.getControladorUtil().atualizar(osAtividadeProgramacaoAcompanhamentoServico);
					}
					if (programacao.getIndicadorAcompanhamentoServico().equals(ConstantesSistema.NAO)) {
						programacao.setIndicadorAcompanhamentoServico(ConstantesSistema.SIM);
						this.getControladorUtil().atualizar(programacao);
					}
				}
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1205] – Remanejar Ordem de Servico
	 * 
	 * Pesquisa OS Programacao Acompanhamento Servico por Equipe
	 * 
	 * @author Thúlio Araújo
	 * @date 22/08/2011
	 * 
	 * @param idArqTextoAcompServico
	 * @return Date - data da OS Programacao Acompanhamento Servico
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOSProgramacaoAcompServicoPorEquipeOS(Integer idOrdemServico, Date dataProgramacao, Integer idEquipe) throws ControladorException {
		try {
			return repositorioOrdemServico.pesquisarOSProgramacaoAcompServicoPorEquipeOS(idOrdemServico, dataProgramacao, idEquipe);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1205] - Remanejar Ordem de Servico
	 * 
	 * [FS0007] Validar carga de trabalho da Equipe
	 * 
	 * @author Thúlio Araújo
	 * @date 22/08/2011 ---
	 */
	public boolean validarCargaTrabalhoEquipe(Integer idEquipeRemanejada, Integer idProgramacaoRoteiro, Integer idOrdemServico, Integer idUnidadeOrganizacional)
			throws ControladorException {
		Equipe equipe = null;
		boolean retorno = false;
		try {
			Integer cargaTrabalhoEquipeSelecionada = null;
			Integer tempoMedioExEquipeSelecionada = null;

			equipe = new Equipe();
			Object[] dadosEquipe = repositorioOrdemServico.pesquisarTempoMedioOSProgramacaoComDataRoteiroUnidade(idProgramacaoRoteiro, idEquipeRemanejada);

			if (dadosEquipe != null && !dadosEquipe.equals("")) {
				Integer cargaTrabalhoEquipe = null;
				if (dadosEquipe[0] != null) {
					cargaTrabalhoEquipe = (Integer) dadosEquipe[0];
				}
				Integer tpMedioExcServicosEquipe = null;
				if (dadosEquipe[1] != null) {
					tpMedioExcServicosEquipe = new Integer("" + (Short) dadosEquipe[1]);
				}

				if (cargaTrabalhoEquipe != null && tpMedioExcServicosEquipe != null && cargaTrabalhoEquipe > tpMedioExcServicosEquipe) {

					ServicoTipo servicoTipo = repositorioOrdemServico.recuperaServicoTipoDaOrdemServico(idOrdemServico);

					if (servicoTipo != null) {

						Integer tmMedioExcOS = new Integer("" + servicoTipo.getTempoMedioExecucao());

						Integer tempoEquipeProcessada = cargaTrabalhoEquipe - (tmMedioExcOS + tpMedioExcServicosEquipe);

						if (tempoEquipeProcessada < 0) {
							retorno = true;
						}
					}
				} else {
					retorno = true;
				}
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		return retorno;
	}

	/**
	 * [UC1213] Emitir Relatorio de Ordem de Servico de Fiscalizacao
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 06/08/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public OrdemServico pesquisarOrdemServicoFiscalizada(Integer idOrdemServico) throws ControladorException {

		try {
			return repositorioOrdemServico.pesquisarOrdemServicoFiscalizada(idOrdemServico);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Reordena Sequencial de Programação Acompanhamento de Serviço
	 * 
	 * @author Thúlio Araújo
	 * @date 27/08/2011
	 * 
	 * @param ordemServico
	 * @throws ControladorException
	 */
	public short reordenaSequencialOSProgramacao(Date dataRoteiro, short sequencialInformado, Integer idEquipe) throws ControladorException {
		short sequencialRetorno = 0;
		try {
			Collection<OrdemServicoProgramacao> colecaoOSProgramacao = this.repositorioOrdemServico.pesquisarOSProgramacaoComDataRoteiroIdEquipe(dataRoteiro,
					idEquipe);

			Iterator itera = colecaoOSProgramacao.iterator();

			OrdemServicoProgramacao osProgramacaoTemp = null;
			OrdemServicoProgramacao osProgramacao = null;

			while (itera.hasNext()) {
				osProgramacao = (OrdemServicoProgramacao) itera.next();

				short sequencial = osProgramacao.getNnSequencialProgramacao();

				osProgramacao.setUltimaAlteracao(new Date());
				osProgramacao.setIndicadorAcompanhamentoServico(ConstantesSistema.NAO);

				if (sequencialInformado == sequencial) {
					if (Short.valueOf(osProgramacao.getOrdemServico().getSituacao()).equals(OrdemServico.SITUACAO_PENDENTE)) {
						if (osProgramacaoTemp != null) {
							osProgramacaoTemp.setNnSequencialProgramacao(sequencialInformado);
							this.getControladorUtil().atualizar(osProgramacaoTemp);

							this.atualizarOrdemProgramacaoAcompanhamentoServico(idEquipe, dataRoteiro, osProgramacaoTemp.getOrdemServico().getId());
						} else {
							sequencialRetorno = sequencialInformado;
						}
						osProgramacao.setNnSequencialProgramacao(++sequencialInformado);
						osProgramacaoTemp = osProgramacao;
					} else {
						sequencialInformado++;
					}
				}
			}
			if (osProgramacaoTemp != null && !osProgramacaoTemp.equals("")) {
				osProgramacaoTemp.setNnSequencialProgramacao(sequencialInformado);
				this.getControladorUtil().atualizar(osProgramacaoTemp);

				this.atualizarOrdemProgramacaoAcompanhamentoServico(idEquipe, dataRoteiro, osProgramacaoTemp.getOrdemServico().getId());
			} else {
				sequencialRetorno = sequencialInformado;
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		return sequencialRetorno;
	}

	/**
	 * [UC1199] - Acompanhamento de Arquivos de Roteiro
	 * 
	 * Pesquisa os id's das equipes que ainda possuem OS, para a data informada,
	 * que ainda não foram encaminhadas para o campo.
	 * 
	 * @author Thúlio Araújo
	 * @date 06/07/2011
	 * 
	 * @param dataRoteiro
	 *            - Data para a pesquisa das OS
	 * 
	 * @return Collection<Integer> - Coleção com todos os ID's das equipes.
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Equipe> pesquisarEquipesOSNaoEnviadasProgramadas(Integer idUnidadeLotacao, Date dataRoteiro, Integer idEquipe)
			throws ControladorException {
		try {
			return repositorioOrdemServico.pesquisarEquipesOSNaoEnviadasProgramadas(idUnidadeLotacao, dataRoteiro, idEquipe);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1206] Informar Situação da Ordem de Serviço
	 * 
	 * 
	 * @author Thúlio Araújo
	 * @date 29/08/2011
	 * 
	 * @param idEquipe
	 *            - Id da equipe atual
	 * @param dataRoteiro
	 *            - Data para a incluisão das OS
	 * 
	 * @return void...
	 * 
	 * @throws ControladorException
	 */
	public void atualizarOrdemProgramacaoAcompServicoInformarSituacao(Integer idArquivo, Date dataRoteiro, Integer idOrdemServico, Short novaSituacao,
			Integer idOsProgramNaoEncerMotivo) throws ControladorException {
		try {

			Collection<OSProgramacaoAcompanhamentoServico> colecaoOsProgramacaoAcompanhamentoServico = repositorioOrdemServico
					.pesquisarOSProgramacaoAcompArquivoComDataRoteiroIdEquipe(dataRoteiro, idArquivo);

			if (colecaoOsProgramacaoAcompanhamentoServico != null && !colecaoOsProgramacaoAcompanhamentoServico.isEmpty()) {
				Iterator itera = colecaoOsProgramacaoAcompanhamentoServico.iterator();
				while (itera.hasNext()) {

					OSProgramacaoAcompanhamentoServico osProgramacaoAcompServico = (OSProgramacaoAcompanhamentoServico) itera.next();

					if (osProgramacaoAcompServico.getOrdemServico().getId() == idOrdemServico
							|| osProgramacaoAcompServico.getOrdemServico().getId().equals(idOrdemServico)) {

						OrdemServicoSituacao ordemServicoSituacao = new OrdemServicoSituacao();
						ordemServicoSituacao.setId(novaSituacao.intValue());
						osProgramacaoAcompServico.setOrdemServicoSituacao(ordemServicoSituacao);

						if (idOsProgramNaoEncerMotivo != null && !idOsProgramNaoEncerMotivo.equals(null)) {
							OsProgramNaoEncerMotivo osProgramNaoEncerMotivo = new OsProgramNaoEncerMotivo();
							osProgramNaoEncerMotivo.setId(idOsProgramNaoEncerMotivo);
							osProgramacaoAcompServico.setOsProgramacaoNaoEncerramentoMotivo(osProgramNaoEncerMotivo);
						}

						osProgramacaoAcompServico.setIndicadorAtualizacaoOS(ConstantesSistema.SIM);
						osProgramacaoAcompServico.setIndicadorTrasmissaoOS(ConstantesSistema.NAO);

						osProgramacaoAcompServico.setDataUltimaAlteracao(new Date());

						this.getControladorUtil().atualizar(osProgramacaoAcompServico);
					}
				}
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0467] Atualizar Ordem Serviço
	 * 
	 * Atualiza os dados da tabela de acompanhamento de serviço de acordo com os
	 * dados informados.
	 * 
	 * @author Thúlio Araújo
	 * @date 31/08/2011
	 * 
	 * @param idEquipe
	 *            - Id da equipe atual
	 * @param dataRoteiro
	 *            - Data para a incluisão das OS
	 * 
	 * @return void...
	 * 
	 * @throws ControladorException
	 */
	public void atualizarOSProgramacaoAcompServico(Integer idArquivo, Date dataRoteiro, Integer idOrdemServico, Integer idServicoTipo)
			throws ControladorException {
		try {

			Collection<OSProgramacaoAcompanhamentoServico> colecaoOsProgramacaoAcompanhamentoServico = repositorioOrdemServico
					.pesquisarOSProgramacaoAcompArquivoComDataRoteiroIdEquipe(dataRoteiro, idArquivo);

			if (colecaoOsProgramacaoAcompanhamentoServico != null && !colecaoOsProgramacaoAcompanhamentoServico.isEmpty()) {
				Iterator itera = colecaoOsProgramacaoAcompanhamentoServico.iterator();
				while (itera.hasNext()) {

					OSProgramacaoAcompanhamentoServico osProgramacaoAcompServico = (OSProgramacaoAcompanhamentoServico) itera.next();

					if (osProgramacaoAcompServico.getOrdemServico().getId() == idOrdemServico
							|| osProgramacaoAcompServico.getOrdemServico().getId().equals(idOrdemServico)) {

						ServicoTipo servicoTipo = new ServicoTipo();
						servicoTipo.setId(idServicoTipo);
						osProgramacaoAcompServico.setServicoTipo(servicoTipo);

						osProgramacaoAcompServico.setIndicadorTrasmissaoOS(ConstantesSistema.NAO);
						osProgramacaoAcompServico.setDataUltimaAlteracao(new Date());

						this.getControladorUtil().atualizar(osProgramacaoAcompServico);
					}
				}
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC-1209] Acompanhar Serviços no Dispositivo Móvel
	 * 
	 * Método que irá pesquisar o arquivo que será carregado no celular
	 * 
	 * @autor Bruno Barros
	 * @date 31/08/2011
	 * 
	 * @param imei
	 *            - Imei do aparalho que irá receber o arquivo
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public byte[] baixarArquivoTextoAcompanhamentoServico(long imei) throws ControladorException {

		FiltroEquipe filtroEquipe = new FiltroEquipe();
		filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.NUMERO_IMEI, imei));
		Collection<Equipe> colEquipes = this.getControladorUtil().pesquisar(filtroEquipe, Equipe.class.getName());

		Equipe equipe = (Equipe) Util.retonarObjetoDeColecao(colEquipes);

		if (equipe == null) {
			return null;
		} else {
			byte[] retorno = gerarArquivoTextoOrdensServicoAcompanhamentoEquipe(equipe.getId(), new Date(), true, true);

			try {
				this.repositorioOrdemServico.atualizarSituacaoArquivoTextoAcompanhamentoServico(equipe.getId(),
						SituacaoTransmissaoLeitura.EM_CAMPO.shortValue());
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			return retorno;
		}
	}

	/**
	 * [UC1199] Acompanhamento de Arquivos de Roteiro
	 * 
	 * @author Thúlio Araújo
	 * @date 27/08/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSProgramacaoAcompanhamentoServico> pesquisarOSProgramacaoAcompArquivoComDataRoteiroIdEquipe(Date dataRoteiro, Integer idArquivo)
			throws ControladorException {
		try {
			return repositorioOrdemServico.pesquisarOSProgramacaoAcompArquivoComDataRoteiroIdEquipe(dataRoteiro, idArquivo);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1199] - Acompanhamento de Arquivos de Roteiro
	 * 
	 * Pesquisa o Arquivo Texto do Acompanhamento de Serviço por Equipe e Data
	 * Roteiro
	 * 
	 * @author Thúlio Araújo
	 * @date 06/07/2011
	 * 
	 * @param idEquipe
	 *            - Identificador da equipe
	 * @param dataRoteiro
	 *            - Data do roteiro a ser pesquisado
	 * 
	 * @return Integer - Numero do imei da equipe informada
	 * 
	 * @throws ErroRepositorioException
	 */
	public ArquivoTextoAcompanhamentoServico pesquisarArquivoTextoAcompanhamentoServicoEquipe(Integer idEquipe, Date dataRoteiro) throws ControladorException {
		try {
			return repositorioOrdemServico.pesquisarArquivoTextoAcompanhamentoServicoEquipe(idEquipe, dataRoteiro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1199] – Acompanhamento Arquivos Roteiro Remanejar Ordem Servico Action
	 * 
	 * @author Thúlio Araújo
	 * @date 06/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public OrdemServicoProgramacao pesquisarOSProgramacaoAtivaComDataRoteiroIdEquipe(Integer numeroOS, Date dataRoteiro, Integer idEquipe)
			throws ControladorException {
		try {
			return repositorioOrdemServico.pesquisarOSProgramacaoAtivaComDataRoteiroIdEquipe(numeroOS, dataRoteiro, idEquipe);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Reordena Sequencial de Programação Acompanhamento de Serviço
	 * 
	 * @author Thúlio Araújo
	 * @date 27/08/2011
	 * 
	 * @param ordemServico
	 * @throws ControladorException
	 */
	public void reordenaSequencialOSProgramacaoAcompServico(Date dataRoteiro, short sequencialInformado, Integer idEquipe, short sequencialAtual)
			throws ControladorException {
		try {
			Collection<OrdemServicoProgramacao> colecaoOSProgramacao = this.repositorioOrdemServico.pesquisarOSProgramacaoComDataRoteiroIdEquipe(dataRoteiro,
					idEquipe);

			Iterator itera = colecaoOSProgramacao.iterator();
			OrdemServicoProgramacao osProgramacaoTemp = null;
			OrdemServicoProgramacao osProgramacao = null;
			short sequencialInformadoTemp = sequencialInformado;

			while (itera.hasNext()) {
				osProgramacao = (OrdemServicoProgramacao) itera.next();

				short sequencial = osProgramacao.getNnSequencialProgramacao();

				osProgramacao.setUltimaAlteracao(new Date());
				osProgramacao.setIndicadorAcompanhamentoServico(ConstantesSistema.NAO);

				if (sequencialInformado == sequencial && sequencialInformado != sequencialAtual) {
					if (Short.valueOf(osProgramacao.getOrdemServico().getSituacao()).equals(OrdemServico.SITUACAO_PENDENTE)) {
						if (osProgramacaoTemp != null) {
							this.getControladorUtil().atualizar(osProgramacaoTemp);

							this.atualizarOrdemProgramacaoAcompanhamentoServico(idEquipe, dataRoteiro, osProgramacaoTemp.getOrdemServico().getId());
						}
						osProgramacao.setNnSequencialProgramacao(++sequencialInformado);
						osProgramacaoTemp = osProgramacao;
					} else {
						if (sequencialInformadoTemp == sequencialInformado) {
							sequencialInformadoTemp++;
						}
						sequencialInformado++;
						if (osProgramacaoTemp != null) {
							osProgramacaoTemp.setNnSequencialProgramacao(sequencialInformado);
						}
					}
				} else {
					if (Short.valueOf(osProgramacao.getOrdemServico().getSituacao()).equals(OrdemServico.SITUACAO_PENDENTE)) {
						if (osProgramacaoTemp != null) {
							osProgramacaoTemp.setNnSequencialProgramacao(sequencialInformado);
						}
					}
					if (sequencialInformado == sequencialAtual) {
						break;
					}
				}
			}

			Collection<OrdemServicoProgramacao> colecaoOSProgramacaoTemp = this.repositorioOrdemServico.pesquisarOSProgramacaoComDataRoteiroIdEquipe(
					dataRoteiro, idEquipe);

			itera = colecaoOSProgramacaoTemp.iterator();

			while (itera.hasNext()) {
				osProgramacao = (OrdemServicoProgramacao) itera.next();

				short sequencial = osProgramacao.getNnSequencialProgramacao();

				if (sequencialAtual == sequencial) {
					osProgramacao.setNnSequencialProgramacao(sequencialInformadoTemp);
					osProgramacao.setUltimaAlteracao(new Date());
					osProgramacao.setIndicadorAcompanhamentoServico(ConstantesSistema.NAO);

					this.getControladorUtil().atualizar(osProgramacao);
					this.atualizarOrdemProgramacaoAcompanhamentoServico(idEquipe, dataRoteiro, osProgramacao.getOrdemServico().getId());
					break;
				}
			}

			if (osProgramacaoTemp != null) {
				this.getControladorUtil().atualizar(osProgramacaoTemp);

				this.atualizarOrdemProgramacaoAcompanhamentoServico(idEquipe, dataRoteiro, osProgramacaoTemp.getOrdemServico().getId());

			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UCXXX] - Retornar Mensagem Cadastrada para Equipe
	 * 
	 * @author Thúlio Araújo
	 * @date 08/09/2011
	 * 
	 * @param imei
	 * 
	 * @return MensagemAcompanhamentoServico - Objeto de Mensagem
	 * 
	 * @throws ControladorException
	 */
	public String retornaMensagemAcompanhamentoArquivosRoteiroImei(long imei) throws ControladorException {
		try {
			String retorno = null;

			Integer idArquivo = this.repositorioOrdemServico.pesquisarIdArquivoTextoAcompanhamentoServicoImei(imei);

			MensagemAcompanhamentoServico mensagemAcompanhamentoServico = this.repositorioOrdemServico
					.retornaMensagemAcompanhamentoArquivosRoteiroImei(idArquivo);

			if (mensagemAcompanhamentoServico != null && !mensagemAcompanhamentoServico.equals(null)) {
				retorno = mensagemAcompanhamentoServico.getDescricaoMensagem();

				mensagemAcompanhamentoServico.setIndicadorSituacao(ConstantesSistema.SIM.intValue());
				this.getControladorUtil().atualizar(mensagemAcompanhamentoServico);
			}

			return retorno;
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC-1209] Acompanhar Serviços no Dispositivo Móvel
	 * 
	 * Método que irá pesquisar o arquivo que será carregado no celular
	 * 
	 * @autor Bruno Barros
	 * @date 31/08/2011
	 * 
	 * @param imei
	 *            - Imei do aparalho que irá receber o arquivo
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public byte[] atualizarArquivoTextoAcompanhamentoServico(long imei) throws ControladorException {

		byte[] file = null;

		FiltroEquipe filtroEquipe = new FiltroEquipe();
		filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.NUMERO_IMEI, imei));
		Collection<Equipe> colEquipes = this.getControladorUtil().pesquisar(filtroEquipe, Equipe.class.getName());

		Equipe equipe = (Equipe) Util.retonarObjetoDeColecao(colEquipes);

		if (equipe != null) {

			file = gerarArquivoTextoOrdensServicoAcompanhamentoEquipe(equipe.getId(), new Date(), false, true);
		}

		return file;
	}

	/**
	 * [UC-1225] Incluir dados acompanhamento servico
	 * 
	 * Método que insere o array de bytes vindo do celular e o insere no banco
	 * 
	 * @param numeroOS
	 *            - Id da OS
	 * @param tipoFoto
	 *            - Se essa foto foi do inicio do meio ou do fim da obra
	 * @param foto
	 *            - array de bytes com a foto em si
	 * 
	 * @throws FachadaException
	 */
	public void inserirFotoOrdemServico(int numeroOS, int tipoFoto, byte[] foto) throws ControladorException {
		// repositorioOrdemServico.inserirFotoOrdemServico( numeroOS, tipoFoto,
		// foto );

		FiltroFotoSituacaoOrdemServico filtroSituacaoFoto = new FiltroFotoSituacaoOrdemServico();
		filtroSituacaoFoto.adicionarParametro(new ParametroSimples(FiltroFotoSituacaoOrdemServico.ID, tipoFoto));
		Collection<FotoSituacaoOrdemServico> colFotoSituacaoOrdemServico = this.getControladorUtil().pesquisar(filtroSituacaoFoto,
				FotoSituacaoOrdemServico.class.getName());

		FotoSituacaoOrdemServico situacao = (FotoSituacaoOrdemServico) Util.retonarObjetoDeColecao(colFotoSituacaoOrdemServico);

		OrdemServicoFoto ost = new OrdemServicoFoto();

		FotoSituacaoOrdemServico fsos = new FotoSituacaoOrdemServico();
		fsos.setId(tipoFoto);

		OrdemServico os = new OrdemServico();
		os.setId(numeroOS);

		ost.setFotoSituacao(fsos);
		ost.setOrdemServico(os);
		ost.setDataFoto(new Date());
		ost.setDescricaoFoto(situacao.getDescricao());
		ost.setFotoOrdemServico(foto);
		ost.setUltimaAlteracao(new Date());

		this.getControladorUtil().inserir(ost);
	}

	/**
	 * [UC-1225] Incluir dados acompanhamento servico
	 * 
	 * Altera a situação de uma ordem de serviço no GSAN
	 * 
	 * @param numeroOS
	 *            - Id da OS
	 * @param situacao
	 *            - Id da nova situacao
	 * @param foto
	 *            - array de bytes com a foto em si
	 * 
	 * @throws FachadaException
	 */
	public void atualizarSituacaoProgramacaoOrdemServico(int numeroOS, short situacao) throws ControladorException {
		try {
			this.repositorioOrdemServico.atualizarSituacaoProgramacaoOrdemServico(numeroOS, situacao);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC1227] Atualizar Ordens Serviço de Acompanhamento de Celular
	 * 
	 * Método que irá atualizar as ordens de serviço para encerradas
	 * 
	 * @autor Sávio Luiz
	 * @date 22/09/2011
	 * 
	 * @param dataProgramação
	 *            - data de programação
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public void atualizarOrdemServicoAcompanhamentoServico(Date dataProgramacao, Integer idEquipe, Integer idUnidadeOrganizacional) throws ControladorException {

		try {

			Collection<OSProgramacaoAcompanhamentoServico> collOSProgramacaoAcompServ = this.repositorioOrdemServico
					.pesquisarOSProgramacaoAcompServicoPelaDataProgramacao(dataProgramacao, idEquipe, idUnidadeOrganizacional);

			// recupera o usuário da rotina batch
			Usuario usuarioBatch = getControladorUsuario().pesquisarUsuarioRotinaBatch();

			// Caso existam os programação acompanhamento serviço
			if (collOSProgramacaoAcompServ != null && !collOSProgramacaoAcompServ.isEmpty()) {
				for (OSProgramacaoAcompanhamentoServico osProgramacaoAcompanhamentoServico : collOSProgramacaoAcompServ) {
					Collection<OSAtividadeProgramacaoAcompanhamentoServico> collOSAtividadeProgramacaoAcompanhamentoServico = this.repositorioOrdemServico
							.pesquisarOSAtividadeProgramacaoAcompServico(osProgramacaoAcompanhamentoServico.getId());
					// Caso existam os atividade programação acompanhamento
					// serviço
					if (collOSAtividadeProgramacaoAcompanhamentoServico != null && !collOSAtividadeProgramacaoAcompanhamentoServico.isEmpty()) {
						for (OSAtividadeProgramacaoAcompanhamentoServico osAtividadeProgramacaoAcompanhamentoServico : collOSAtividadeProgramacaoAcompanhamentoServico) {
							// verifica a existencia de Ordem Serviço Atividade
							Object[] dadosOSAtividade = this.repositorioOrdemServico.pesquisarOrdemServicoAtividade(osProgramacaoAcompanhamentoServico
									.getOrdemServico().getId(), osAtividadeProgramacaoAcompanhamentoServico.getAtividade().getId());
							Integer idOSAtividade = null;
							if (dadosOSAtividade == null || dadosOSAtividade.equals("")) {
								// Inserir a Ordem de Serviço Atividade
								OrdemServicoAtividade ordemServicoAtividade = new OrdemServicoAtividade();
								ordemServicoAtividade.setAtividade(osAtividadeProgramacaoAcompanhamentoServico.getAtividade());
								ordemServicoAtividade.setOrdemServico(osProgramacaoAcompanhamentoServico.getOrdemServico());
								ordemServicoAtividade.setUltimaAlteracao(new Date());
								idOSAtividade = (Integer) getControladorUtil().inserir(ordemServicoAtividade);
							} else {
								idOSAtividade = (Integer) dadosOSAtividade[0];
							}

							// pesquisa os dados da tabela de serviço atividade
							// material acompanhamento de serviço
							Collection<OSAtividadeMaterialProgramacaoAcompanhamentoServico> collOSAtividadeMaterialProgramacaoAcompanhamentoServico = this.repositorioOrdemServico
									.pesquisarOSAtividadeMaterialProgramacaoAcompanhamentoServico(osAtividadeProgramacaoAcompanhamentoServico.getId());

							// verifica a existencia de Ordem Serviço Atividade
							// Material Execução
							Collection collOSAtivMaterialExec = this.repositorioOrdemServico.pesquisarOsAtivMaterialExecucao(idOSAtividade);
							// Caso exista Ordem de Serviço Atividade Material
							// Execução, então remove e inseri novamente
							if (collOSAtivMaterialExec != null && !collOSAtivMaterialExec.isEmpty()) {
								// deleta os dados da tabela de OS Atividade
								// Material Execução
								getControladorBatch().removerColecaoObjetoParaBatch(collOSAtivMaterialExec);
							}

							// Caso existam os atividade material programação
							// acompanhamento serviço
							if (collOSAtividadeMaterialProgramacaoAcompanhamentoServico != null
									&& !collOSAtividadeMaterialProgramacaoAcompanhamentoServico.isEmpty()) {
								for (OSAtividadeMaterialProgramacaoAcompanhamentoServico osAtividadeMaterialProgramacaoAcompanhamentoServico : collOSAtividadeMaterialProgramacaoAcompanhamentoServico) {
									// inseri Ordem de Serviço Atividade
									// Material Execução
									OsAtividadeMaterialExecucao osAtividadeMaterialExecucao = new OsAtividadeMaterialExecucao();
									OrdemServicoAtividade ordemServicoAtividade = new OrdemServicoAtividade();
									ordemServicoAtividade.setId(idOSAtividade);
									osAtividadeMaterialExecucao.setOrdemServicoAtividade(ordemServicoAtividade);
									osAtividadeMaterialExecucao.setMaterial(osAtividadeMaterialProgramacaoAcompanhamentoServico.getMaterial());
									osAtividadeMaterialExecucao.setQuantidadeMaterial(osAtividadeMaterialProgramacaoAcompanhamentoServico
											.getQuantidadeMaterial());
									osAtividadeMaterialExecucao.setUltimaAlteracao(new Date());
									getControladorUtil().inserir(osAtividadeMaterialExecucao);

								}
							}

							// pesquisa os dados da tabela de serviço atividade
							// execução acompanhamento de serviço
							Collection<OSAtividadeExecucaoAcompanhamentoServico> collOSAtividadeExecucaoAcompanhamentoServico = this.repositorioOrdemServico
									.pesquisarOSAtividadeExecucaolProgramacaoAcompanhamentoServico(osAtividadeProgramacaoAcompanhamentoServico.getId());

							// verifica a existencia de Ordem Serviço Atividade
							// Periodo Execução
							Collection collOSAtivPeriodoExec = this.repositorioOrdemServico.pesquisarOsAtivPeriodoExecucao(idOSAtividade);
							// Caso exista Ordem de Serviço Atividade Periodo
							// Execução, então remove e inseri novamente
							if (collOSAtivPeriodoExec != null && !collOSAtivPeriodoExec.isEmpty()) {
								// deleta os dados da tabela de OS Atividade
								// Periodo Execução
								getControladorBatch().removerColecaoObjetoParaBatch(collOSAtivPeriodoExec);
							}

							// Caso existam os atividade execução programação
							// acompanhamento serviço
							if (collOSAtividadeExecucaoAcompanhamentoServico != null && !collOSAtividadeExecucaoAcompanhamentoServico.isEmpty()) {
								for (OSAtividadeExecucaoAcompanhamentoServico osAtividadeExecucaoAcompanhamentoServico : collOSAtividadeExecucaoAcompanhamentoServico) {
									// inseri Ordem de Serviço Atividade Periodo
									// Execução
									OsAtividadePeriodoExecucao osAtividadePeriodoExecucao = new OsAtividadePeriodoExecucao();
									OrdemServicoAtividade ordemServicoAtividade = new OrdemServicoAtividade();
									ordemServicoAtividade.setId(idOSAtividade);
									osAtividadePeriodoExecucao.setOrdemServicoAtividade(ordemServicoAtividade);
									osAtividadePeriodoExecucao.setDataInicio(osAtividadeExecucaoAcompanhamentoServico.getDataExecucaoInicio());
									osAtividadePeriodoExecucao.setDataFim(osAtividadeExecucaoAcompanhamentoServico.getDataExecucaoInicio());
									osAtividadePeriodoExecucao.setUltimaAlteracao(new Date());
									getControladorUtil().inserir(osAtividadePeriodoExecucao);

								}
							}
							// Atualiza o indicador de atualização da ordem de
							// serviço atividade acompanhamento de serviço
							osAtividadeProgramacaoAcompanhamentoServico.setIndicadorAtualizacaoOS(ConstantesSistema.SIM);
							getControladorUtil().atualizar(osAtividadeProgramacaoAcompanhamentoServico);
						}
					}

					// Atualiza alguns campos da tabela de Registro Atendimento
					this.getControladorRegistroAtendimento().atualizarDadosRA(
							osProgramacaoAcompanhamentoServico.getOrdemServico().getRegistroAtendimento().getId(),
							osProgramacaoAcompanhamentoServico.getDescricaoPontoReferencia(), osProgramacaoAcompanhamentoServico.getNumeroImovel());

					// Caso a situação da ordem de serviço acompanhamento
					// serviço esteja com a situação diferente de encerrada
					if (osProgramacaoAcompanhamentoServico.getOrdemServicoSituacao().getId() != null
							&& !osProgramacaoAcompanhamentoServico.getOrdemServicoSituacao().getId().equals(OrdemServicoSituacao.ENCERRADO)) {
						// Atualiza os dados da Ordem de Serviço
						short sitacao = new Short("" + osProgramacaoAcompanhamentoServico.getOrdemServicoSituacao().getId());
						this.repositorioOrdemServico.atualizarCodigoSituacaoOS(osProgramacaoAcompanhamentoServico.getOrdemServico().getId(), sitacao);

					} else {

						// Caso a situação ordem de serviço acompanhamento
						// serviço esteja com a situação igual a encerrada,então
						// verificar a situação
						// da ordem de serviço
						Short situacaoOS = this.repositorioOrdemServico.verificaSituacaoOS(osProgramacaoAcompanhamentoServico.getOrdemServico().getId());
						if (!situacaoOS.equals(OrdemServico.SITUACAO_ENCERRADO)) {
							// Caso o campo de Motivo Não Encerramento da tabela
							// de acompanhamento de Serviço esteja diferente de
							// nulo
							if (osProgramacaoAcompanhamentoServico.getOsProgramacaoNaoEncerramentoMotivo() != null
									&& !osProgramacaoAcompanhamentoServico.getOsProgramacaoNaoEncerramentoMotivo().getId().equals("")) {

								// Atualizar a Ordem de Serviço para a Situação
								// de Pendente
								this.repositorioOrdemServico.atualizarCodigoSituacaoOS(osProgramacaoAcompanhamentoServico.getOrdemServico().getId(),
										OrdemServico.SITUACAO_PENDENTE);

								// Pesquisa a Ordem de Serviço Programação
								OrdemServicoProgramacao ordemServicoProgramacao = this.repositorioOrdemServico
										.pesquisarOSProgramacaoAtivaComDataRoteiroIdEquipe(osProgramacaoAcompanhamentoServico.getOrdemServico().getId(),
												osProgramacaoAcompanhamentoServico.getDataProgramacao(), osProgramacaoAcompanhamentoServico.getEquipe().getId());

								// Caso exista, então atualiza a ordem de
								// serviço programação
								if (ordemServicoProgramacao != null && !ordemServicoProgramacao.equals("")) {
									ordemServicoProgramacao.setSituacaoFechamento(OrdemServicoProgramacao.SITUACAO_FECHAMENTO);
									if (osProgramacaoAcompanhamentoServico.getEquipamentosEspeciaisFaltante() != null
											&& !osProgramacaoAcompanhamentoServico.getEquipamentosEspeciaisFaltante().equals("")) {
										ordemServicoProgramacao.setEquipamentoEspecialFaltante(osProgramacaoAcompanhamentoServico
												.getEquipamentosEspeciaisFaltante());
									}
									ordemServicoProgramacao.setOsProgramNaoEncerMotivo(osProgramacaoAcompanhamentoServico
											.getOsProgramacaoNaoEncerramentoMotivo());
									ordemServicoProgramacao.setUsuarioFechamento(usuarioBatch);
									ordemServicoProgramacao.setUltimaAlteracao(new Date());
									getControladorUtil().atualizar(ordemServicoProgramacao);

								}

							} else {
								// encerrar a ordem de serviço
								this.encerrarOSComExecucaoSemReferencia(osProgramacaoAcompanhamentoServico.getOrdemServico().getId(), new Date(), usuarioBatch,
										AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO.toString(), new Date(),
										"Serviço Executado Pelo Sistema de Acompanhamento de Serviço", ConstantesSistema.NAO.toString(), null, null, null,
										null, null, null, null, null, null, null);
							}

						}

						// Atualiza o indicador de atualização da ordem de
						// serviço
						osProgramacaoAcompanhamentoServico.setIndicadorAtualizacaoOS(ConstantesSistema.SIM);
						getControladorUtil().atualizar(osProgramacaoAcompanhamentoServico);

					}
				}
			}

		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1225] Incluir Dados Acompanhamento de Serviço
	 * 
	 * Este caso de uso permite a inserção de dados na tabela de ordem de
	 * serviço para acompanhamento do serviço.
	 * 
	 * @author Thúlio Araújo
	 * 
	 * @date 22/09/2011
	 * @param buffer
	 *            - BufferedReader com o arquivo selecionado
	 * @return Object[]
	 * @throws ControladorException
	 */
	public void retornoAtualizarOrdemServicoAcompanhamentoServico(BufferedReader buffer) throws ControladorException {

		try {
			// Convertemos o arquivo em uma coleção de Helpers
			IncluirDadosAcompanhamentoServicoHelper helper = new IncluirDadosAcompanhamentoServicoHelper();
			Collection<IncluirDadosAcompanhamentoServicoHelper> colecaoIncluirDadosAcompanhamentoServicoHelper = new ArrayList();
			IncluirDadosAcompanhamentoServicoHelper helperDadosAcompanhamentoServico = null;

			Collection<IncluirDadosAcompanhamentoServicoHelper> colHelper = helper.parseHelperArquivo(buffer);

			// [FS0002] - Verificar seqüência dos tipos de registros
			// Para essa validação, precisamos ler todo o arquivo
			verificarSequenciaTiposRegistros(colHelper);

			// Para as outras, validamos linha a linha para economizar
			// processamento
			// Verificamos que:
			Integer ordemServico = null;
			for (IncluirDadosAcompanhamentoServicoHelper helperLaco : colHelper) {

				if (Integer.valueOf(helperLaco.getTipoRegistro()) == 1
						&& (ordemServico == null || !ordemServico.equals(Integer.valueOf(helperLaco.getIdOrdemServico())))) {
					ordemServico = Integer.valueOf(helperLaco.getIdOrdemServico());
					colecaoIncluirDadosAcompanhamentoServicoHelper.add(helperLaco);
					helperDadosAcompanhamentoServico = helperLaco;
				}

				// [FS0003] ? Verificar valor do tipo de registro
				verificarValorTipoRegistro(helperLaco);

				// [FS0001] - Verificar existência do número da ordem de serviço
				verificarExistenciaOrdemServico(helperLaco);
			}

			Object[] dadosRetorno = incluiDadosOsProgramacaoAcompanhamentoServico(colHelper);

			if (dadosRetorno != null && !dadosRetorno.equals("")) {
				Date dataProgramacao = (Date) dadosRetorno[0];
				Integer idEquipe = (Integer) dadosRetorno[1];

				this.atualizarOrdemServicoAcompanhamentoServico(dataProgramacao, idEquipe, null);
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new ControladorException("erro de io", e);
		}
	}

	/**
	 * Este caso de uso permite a atualização de dados na tabela OS Programação
	 * Acompanhamento Serviço
	 * 
	 * [UC1225] Incluir Dados Acompanhamento de Serviço
	 * 
	 * 
	 * [FS0002] - Verificar seqüência dos tipos de registros
	 * 
	 * Não poderá vir um registro do tipo 1 depois de outro tipo 1 para a mesma
	 * ordem de serviço, deverá retornar uma mensagem ?Ordem de Serviço:
	 * <<número da Ordem de Serviço>>, do arquivo, com seqüencial
	 * 
	 * 1 depois de outro seqüencial 1?;
	 * 
	 * Não poderá vir um registro do tipo 2 sem que tenha um do tipo 1 para a
	 * mesma ordem de serviço, deverá retornar uma mensagem ?Ordem de Serviço:
	 * <<número da Ordem de Serviço>>, do arquivo, com seqüencial
	 * 
	 * 2 sem seqüencial 1.?;
	 * 
	 * Não poderá vir um registro do tipo 3 sem que tenha um do tipo 1 para a
	 * mesma ordem de serviço, deverá retornar uma mensagem ?Ordem de Serviço:
	 * <<número da Ordem de Serviço>>, do arquivo, com seqüencial 3 sem
	 * seqüencial 2.?;
	 * 
	 * 
	 * Não poderá vir um registro do tipo 4 sem que tenha um do tipo 1 para a
	 * mesma ordem de serviço deverá retornar uma mensagem ?Ordem de Serviço:
	 * <<número da Ordem de Serviço>>, do arquivo, com seqüencial 4 sem
	 * seqüencial 1.?;
	 * 
	 * 
	 * @author Thúlio Araújo
	 * @date 23/09/2011
	 * @param colHelper
	 * 
	 * @throws ControladorException
	 */
	private void verificarSequenciaTiposRegistros(Collection<IncluirDadosAcompanhamentoServicoHelper> colHelper) throws ControladorException {

		String registroAnterior = null;
		String ordemServicoRegistroTipo1Selecionado = null;
		String ordemServicoRegistroTipo2Selecionado = null;

		// Verificamos que:
		for (IncluirDadosAcompanhamentoServicoHelper helperLaco : colHelper) {

			if (helperLaco.getTipoRegistro().equals(IncluirDadosAcompanhamentoServicoHelper.REGISTRO_TIPO_1)) {
				// Não poderá vir um registro do tipo 1 depois de outro tipo 1
				if (registroAnterior != null && registroAnterior.equals(helperLaco.getTipoRegistro())) {
					throw new ControladorException(ConstantesAplicacao.get("atencao.imovel_movimento_dados_faturamento_registro_tipo_1",
							helperLaco.getIdOrdemServico()));
				}

				// Guardamos as informações necessarias ao registro tipo 1
				ordemServicoRegistroTipo1Selecionado = helperLaco.getIdOrdemServico();
			} else if (helperLaco.getTipoRegistro().equals(IncluirDadosAcompanhamentoServicoHelper.REGISTRO_TIPO_2)) {
				// Não poderá vir um registro do tipo 2 sem que tenha um do
				// tipo 1 para a mesma ordem de serviço
				if (ordemServicoRegistroTipo1Selecionado == null || !ordemServicoRegistroTipo1Selecionado.equals(helperLaco.getIdOrdemServico())) {
					throw new ControladorException(ConstantesAplicacao.get("atencao.os_dados_acompanhamento_servico_registro_tipo_2",
							helperLaco.getIdOrdemServico()));
				}

				// Guardamos as informações necessarias ao registro tipo 2
				ordemServicoRegistroTipo2Selecionado = helperLaco.getRg2IdOsAtividade();
			} else if (helperLaco.getTipoRegistro().equals(IncluirDadosAcompanhamentoServicoHelper.REGISTRO_TIPO_3)) {
				// Não poderá vir um registro do tipo 3 sem que tenha um do
				// tipo 2 para o mesmo imóvel
				if (ordemServicoRegistroTipo2Selecionado == null
						|| !ordemServicoRegistroTipo2Selecionado.equals(helperLaco.getRg3IdOsAtividadePrgAcompServico())) {
					throw new ControladorException(ConstantesAplicacao.get("atencao.os_dados_acompanhamento_servico_registro_tipo_3",
							helperLaco.getIdOrdemServico()));
				}
			} else if (helperLaco.getTipoRegistro().equals(IncluirDadosAcompanhamentoServicoHelper.REGISTRO_TIPO_4)) {
				// Não poderá vir um registro do tipo 4 sem que tenha um do
				// tipo 1 para o mesmo imóvel
				if (ordemServicoRegistroTipo2Selecionado == null
						|| !ordemServicoRegistroTipo2Selecionado.equals(helperLaco.getRg4IdOsAtividadePrgAcompServico())) {
					throw new ControladorException(ConstantesAplicacao.get("atencao.imovel_movimento_dados_faturamento_registro_tipo_4",
							helperLaco.getIdOrdemServico()));
				}
			}

			registroAnterior = helperLaco.getTipoRegistro();
		}
	}

	/**
	 * [UC1225] Incluir Dados Acompanhamento de Serviço
	 * 
	 * [FS0001] - Verificar existência do número da ordem de serviço
	 * 
	 * Caso o numero da ordem de serviço não exista na tabela ORDEM_SERVICO,
	 * exibir a mensagem: Ordem de Serviço inexistente: <<número da Ordem de
	 * Serviço>>?
	 * 
	 * e retornar para próximo tipo 1 do arquivo de retorno.
	 * 
	 * @author Thúlio Araújo
	 * @date 23/09/2011
	 * 
	 * @param helperLaco
	 * @throws ControladorException
	 */
	private void verificarExistenciaOrdemServico(IncluirDadosAcompanhamentoServicoHelper helperLaco) throws ControladorException {

		if (helperLaco.getTipoRegistro().equals(IncluirDadosAcompanhamentoServicoHelper.REGISTRO_TIPO_1)) {
			try {
				// [FS0001] - Verificar existência do número da ordem de serviço
				if (!this.repositorioOrdemServico.verificarExistenciaOrdemServico(Integer.valueOf(helperLaco.getIdOrdemServico()))) {
					throw new ControladorException(ConstantesAplicacao.get("atencao.ordem_servico_inexistente", helperLaco.getIdOrdemServico()));
				}
			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}
		}
	}

	/**
	 * [UC1225] Incluir Dados Acompanhamento de Serviço
	 * 
	 * [SB0001] - Atualizar Dados OS programação Acompanhamento de Serviço
	 * 
	 * @author Thúlio Araújo
	 * @date 23/09/2011
	 * 
	 * @param colHelper
	 */
	private Object[] incluiDadosOsProgramacaoAcompanhamentoServico(Collection<IncluirDadosAcompanhamentoServicoHelper> colHelper) throws ControladorException {

		String idOrdemServico = "";

		Date dataProgramacao = null;
		Integer idEquipe = null;

		Object[] retorno = new Object[2];

		try {
			OSProgramacaoAcompanhamentoServico osProgramacaoAcompServico = null;
			OSAtividadeProgramacaoAcompanhamentoServico osAtividadeProgramacaoAcompanhamentoServico = null;

			SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

			boolean jaSelecionouRegistroTipo1 = false;

			for (IncluirDadosAcompanhamentoServicoHelper helper : colHelper) {

				// 1. Caso o tipo de registro seja igual a 1.
				if (helper.getTipoRegistro().equals(IncluirDadosAcompanhamentoServicoHelper.REGISTRO_TIPO_1)) {

					osProgramacaoAcompServico = repositorioOrdemServico.pesquisarOSProgramacaoAcompServicoPorIdOs(Integer.valueOf(helper.getIdOrdemServico()),
							Util.converteStringParaDate(helper.getRg1DtProgramacamo()));

					if (osProgramacaoAcompServico != null && !osProgramacaoAcompServico.equals(null)) {

						idOrdemServico = helper.getIdOrdemServico();

						if (dataProgramacao == null) {
							dataProgramacao = osProgramacaoAcompServico.getDataProgramacao();
							idEquipe = osProgramacaoAcompServico.getEquipe().getId();
						}

						// EQES_IDFALTANTE
						// Caso o id Equipamento Especial Faltante seja
						// diferente de nulo, então o id Equipamento Especial
						// Faltante.
						if (helper.getRg1IdEquipamentoEspecialFaltante() != null && !helper.getRg1IdEquipamentoEspecialFaltante().equals(null)) {
							EquipamentosEspeciais equipamentosEspeciais = new EquipamentosEspeciais();
							equipamentosEspeciais.setId(Integer.valueOf(helper.getRg1IdEquipamentoEspecialFaltante()));
							osProgramacaoAcompServico.setEquipamentosEspeciaisFaltante(equipamentosEspeciais);
						}

						// OPNE_ID
						// Caso o Id da Ordem de Serviço Programação Não
						// Encerramento Motivo, então Id da Ordem de Serviço
						// Programação Não Encerramento Motivo.
						if (helper.getRg1IdOsProgramacaoNaoEncerrarMotivo() != null && !helper.getRg1IdOsProgramacaoNaoEncerrarMotivo().equals(null)) {
							OsProgramNaoEncerMotivo osProgramNaoEncerMotivo = new OsProgramNaoEncerMotivo();
							osProgramNaoEncerMotivo.setId(Integer.valueOf(helper.getRg1IdOsProgramacaoNaoEncerrarMotivo()));
							osProgramacaoAcompServico.setOsProgramacaoNaoEncerramentoMotivo(osProgramNaoEncerMotivo);
						}

						// OSST_ID
						// Id da Ordem de Serviço Situação
						OrdemServicoSituacao ordemServicoSituacao = new OrdemServicoSituacao();
						ordemServicoSituacao.setId(Integer.valueOf(helper.getRg1IdOsSituacao()));
						osProgramacaoAcompServico.setOrdemServicoSituacao(ordemServicoSituacao);

						// OSAS_DSPONTOREFERENCIA
						// Descrição Ponto de Referência
						osProgramacaoAcompServico.setDescricaoPontoReferencia(helper.getRg1DsPontoReferencia());

						// OSAS_NNIMOVEL
						// Número do Imóvel
						osProgramacaoAcompServico.setNumeroImovel(helper.getRg1NumeroImovel());

						// OSAS_TMULTIMAALTERACAO
						// Data e Hora Atual.
						osProgramacaoAcompServico.setDataUltimaAlteracao(new Date());

						// OATS_ICATUALIZAOS
						// 2.
						osProgramacaoAcompServico.setIndicadorAtualizacaoOS(ConstantesSistema.NAO);

						// Atualizar o objeto osProgramacaoAcompanhamentoServico
						this.getControladorBatch().atualizarObjetoParaBatch(osProgramacaoAcompServico);
					}

					// Guardamos o atual
					if (!jaSelecionouRegistroTipo1) {
						jaSelecionouRegistroTipo1 = true;
					} else {
						jaSelecionouRegistroTipo1 = false;
					}

					// 2. Caso o tipo de registro seja igual a 2.
				} else if (helper.getTipoRegistro().equals(IncluirDadosAcompanhamentoServicoHelper.REGISTRO_TIPO_2)) {

					jaSelecionouRegistroTipo1 = false;

					// [FS0006] - Verificar existência da OS Atividade
					// Programação Acompanhamento Serviço
					verificarExistenciaOsAtividadeProgramacaoAcompanhamentoServico(Integer.valueOf(helper.getIdOrdemServico()),
							Integer.valueOf(helper.getRg2IdAtividade()), osProgramacaoAcompServico.getDataProgramacao());

					osAtividadeProgramacaoAcompanhamentoServico = new OSAtividadeProgramacaoAcompanhamentoServico();

					// ATIV_ID
					// Id da Atividade.
					Atividade atividade = new Atividade();
					atividade.setId(Integer.valueOf(helper.getRg2IdAtividade()));
					osAtividadeProgramacaoAcompanhamentoServico.setAtividade(atividade);

					// OSST_ID
					// Id da Ordem de Serviço Situação.
					OrdemServicoSituacao ordemServicoSituacao = new OrdemServicoSituacao();
					ordemServicoSituacao.setId(Integer.valueOf(helper.getRg2IdOsSituacao()));
					osAtividadeProgramacaoAcompanhamentoServico.setOrdemServicoSituacao(ordemServicoSituacao);

					// OATS_ICATUALIZAOS
					// 2.
					osAtividadeProgramacaoAcompanhamentoServico.setIndicadorAtualizacaoOS(ConstantesSistema.NAO);

					// OATS_ICTRANSMISSAO
					// 1.
					osAtividadeProgramacaoAcompanhamentoServico.setIndicadorTransmissaoOS(ConstantesSistema.SIM);

					// OSAS_ID
					// OSAS_ID da tabela OS_PRG_ACOMP_SERVICO atualizado no
					// registro tipo 1.
					osAtividadeProgramacaoAcompanhamentoServico.setOsProgramacaoAcompanhamentoServico(osProgramacaoAcompServico);

					// EQES_IDFALTANTE
					// Caso o id Equipamento Especial Faltante seja diferente de
					// nulo, então o id Equipamento Especial Faltante.
					if (helper.getRg2IdEquipamentoEspecialFaltante() != null && !helper.getRg2IdEquipamentoEspecialFaltante().equals(null)) {
						EquipamentosEspeciais equipamentosEspeciais = new EquipamentosEspeciais();
						equipamentosEspeciais.setId(Integer.valueOf(helper.getRg2IdEquipamentoEspecialFaltante()));
						osAtividadeProgramacaoAcompanhamentoServico.setEquipamentoFaltante(equipamentosEspeciais);
					}

					// OATS_TMULTIMAALTERACAO
					// Data e Hora Atual.
					osAtividadeProgramacaoAcompanhamentoServico.setDataUltimaAlteracao(new Date());

					// OATS_ICEXCLUIDO
					// 2
					osAtividadeProgramacaoAcompanhamentoServico.setIndicadorExcluido(ConstantesSistema.NAO);

					// STAS_ID
					// Id do Serviço Prestador Serviço.
					if (helper.getRg2IdPrestadorServico() != null && !helper.getRg2IdPrestadorServico().equals("")) {
						ServicoTerceiroAcompanhamentoServico servicoTerceiroAcompanhamentoServico = new ServicoTerceiroAcompanhamentoServico();
						servicoTerceiroAcompanhamentoServico.setId(Integer.valueOf(helper.getRg2IdPrestadorServico()));
						osAtividadeProgramacaoAcompanhamentoServico.setServicoTerceiroAcompanhamentoServico(servicoTerceiroAcompanhamentoServico);
					}

					// OATS_QTMATERIALEXEDENTE
					// Quantidade de Material Excedente.
					osAtividadeProgramacaoAcompanhamentoServico.setQtdMaterialExedente(Util.formatarStringParaBigDecimal(helper.getRg2QtdMaterialExcedente()));

					// Inserimos
					Integer idOsAtividadeAcompServ = (Integer) this.getControladorBatch().inserirObjetoParaBatch(osAtividadeProgramacaoAcompanhamentoServico);

					osAtividadeProgramacaoAcompanhamentoServico.setId(idOsAtividadeAcompServ);

					// 3. Caso o tipo de registro seja igual a 3
				} else if (helper.getTipoRegistro().equals(IncluirDadosAcompanhamentoServicoHelper.REGISTRO_TIPO_3)) {

					OSAtividadeMaterialProgramacaoAcompanhamentoServico osAtividadeMaterialProgramacaoAcompanhamentoServico = new OSAtividadeMaterialProgramacaoAcompanhamentoServico();

					// OSAT_ID
					// Id da tabela OS_AT_PRG_ACOMP_SERVICO cadastrada no
					// registro tipo 2.
					osAtividadeMaterialProgramacaoAcompanhamentoServico
							.setOsAtividadeProgramacaoAcompanhamentoServico(osAtividadeProgramacaoAcompanhamentoServico);

					// MATE_ID
					// Id Material.
					Material material = new Material();
					material.setId(Integer.valueOf(helper.getRg3IdMaterial()));
					osAtividadeMaterialProgramacaoAcompanhamentoServico.setMaterial(material);

					// OAME_QTMATERIAL
					// Quantidade de Material.
					osAtividadeMaterialProgramacaoAcompanhamentoServico.setQuantidadeMaterial(Util.formatarStringParaBigDecimal(helper.getRg3QtdMaterial()));

					// OAME_TMULTIMAALTERACAO
					// Data e Hora Atual.
					osAtividadeMaterialProgramacaoAcompanhamentoServico.setDataUltimaAlteracao(new Date());

					// Inserimos
					this.getControladorBatch().inserirObjetoParaBatch(osAtividadeMaterialProgramacaoAcompanhamentoServico);

					// 4. Caso o tipo de registro seja igual a 4.
				} else if (helper.getTipoRegistro().equals(IncluirDadosAcompanhamentoServicoHelper.REGISTRO_TIPO_4)) {

					OSAtividadeExecucaoAcompanhamentoServico osAtividadeExecucaoAcompanhamentoServico = new OSAtividadeExecucaoAcompanhamentoServico();

					// OEAS_TMEXECUCAOINICIO
					// Data de Execução Início
					osAtividadeExecucaoAcompanhamentoServico.setDataExecucaoInicio(Util.converteStringParaDateHora(helper.getRg4DataExecucaoInicio()));

					// OEAS_TMEXECUCAOFIM
					// Data de Execução Fim
					osAtividadeExecucaoAcompanhamentoServico.setDataExecucaoFim(Util.converteStringParaDateHora(helper.getRg4DataExecucaoFim()));

					// OATS_ID
					// Id da tabela OS_AT_PRG_ACOMP_SERVICO cadastrada no
					// registro tipo 2.
					osAtividadeExecucaoAcompanhamentoServico.setOsAtividadeProgramacaoAcompanhamentoServico(osAtividadeProgramacaoAcompanhamentoServico);

					// OEAS_TMULTIMAALTERACAO
					// Data e Hora Atual.
					osAtividadeExecucaoAcompanhamentoServico.setDataUltimaAlteracao(new Date());

					// Inserimos
					this.getControladorBatch().inserirObjetoParaBatch(osAtividadeExecucaoAcompanhamentoServico);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControladorException("atencao.erro_inserindo_os_programacao", e, idOrdemServico);
		}

		retorno[0] = dataProgramacao;
		retorno[1] = idEquipe;

		return retorno;
	}

	/**
	 * [UC1225] Incluir Dados Acompanhamento de Serviço
	 * 
	 * [FS0003 - Verificar valor do tipo de registro]
	 * 
	 * Caso o tipo de registro possua valor <> 1, 2, 3 ou 4, exibir a mensagem
	 * ?Ordem de Serviço: <<número da Ordem de Serviço>> , do arquivo, sem
	 * seqüencial 1,2,3 ou 4?.
	 * 
	 * @author Thúlio Araújo
	 * @date 26/09/2011
	 * 
	 * @param helperLaco
	 */
	private void verificarValorTipoRegistro(IncluirDadosAcompanhamentoServicoHelper helperLaco) throws ControladorException {

		Collection<String> errors = new ArrayList();

		if (!helperLaco.getTipoRegistro().equals(IncluirDadosAcompanhamentoServicoHelper.REGISTRO_TIPO_1)
				&& !helperLaco.getTipoRegistro().equals(IncluirDadosAcompanhamentoServicoHelper.REGISTRO_TIPO_2)
				&& !helperLaco.getTipoRegistro().equals(IncluirDadosAcompanhamentoServicoHelper.REGISTRO_TIPO_3)
				&& !helperLaco.getTipoRegistro().equals(IncluirDadosAcompanhamentoServicoHelper.REGISTRO_TIPO_4)) {
			throw new ControladorException(ConstantesAplicacao.get("atencao.ordem_servico_registros_invalidos", helperLaco.getIdOrdemServico() + ""));
		}
	}

	/**
	 * [UC1225] Incluir Dados Acompanhamento de Serviço
	 * 
	 * [FS0006] - Verificar existência da OS Atividade Programação
	 * Acompanhamento Serviço
	 * 
	 * Caso já exista OS Atividade Programação Acompanhamento Serviço para a
	 * ordem de serviço e a data de roteiro e a atividade, então excluir na
	 * tabela OS_AT_PRG_ACOMP_SERVICO o seu correspondente e seus dependentes.
	 * 
	 * @author Thúlio Araújo
	 * @date 26/09/2011
	 * 
	 * @param helperLaco
	 */
	private void verificarExistenciaOsAtividadeProgramacaoAcompanhamentoServico(Integer idOrdemServico, Integer idAtividade, Date dataProgramacao)
			throws ControladorException {

		try {

			Collection<Integer> collIdOSAtividadeProgramacaoAcompanhamentoServico = this.repositorioOrdemServico
					.pesquisarOSAtividadeProgramacaoAcompServicoPorIdOs(idOrdemServico, dataProgramacao, idAtividade);

			// Caso existam os atividade programação acompanhamento serviço
			if (collIdOSAtividadeProgramacaoAcompanhamentoServico != null && !collIdOSAtividadeProgramacaoAcompanhamentoServico.isEmpty()) {

				// Excluir os dados da tabela
				// OSAtividadeExecucaoAcompanhamentoServico para cada id da
				// tabela
				// OsAtividadeProgramcaoAcompanhamentoServico
				this.repositorioOrdemServico.excluirOsAtividadeExecucaoAcompahamentoServico(collIdOSAtividadeProgramacaoAcompanhamentoServico);

				// Excluir os dados da tabela
				// OSAtividadeMaterialProgramacaoAcompanhamentoServico para cada
				// id da tabela
				// OsAtividadeProgramcaoAcompanhamentoServico
				this.repositorioOrdemServico.excluirOsAtividadeMaterialProgramacaoAcompahamentoServico(collIdOSAtividadeProgramacaoAcompanhamentoServico);

				// Excluir os dados da tabela
				// OSAtividadeProgramacaoAcompanhamentoServico para cada id da
				// tabela
				// OsAtividadeProgramcaoAcompanhamentoServico
				this.repositorioOrdemServico.excluirOsAtividadeProgramacaoAcompahamentoServico(collIdOSAtividadeProgramacaoAcompanhamentoServico);
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC-1209] Acompanhar Serviços no Dispositivo Móvel
	 * 
	 * Método que irá pesquisar o arquivo que será carregado no celular
	 * 
	 * @autor Sávio Luiz
	 * @date 31/08/2011
	 * 
	 * @param imei
	 *            - Imei do aparalho que irá receber o arquivo
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public void atualizarSituacaoArquivoTextoAcompanhamentoServico(long imei, Short idSituacaoTransmissaoLeitura) throws ControladorException {

		FiltroEquipe filtroEquipe = new FiltroEquipe();
		filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.NUMERO_IMEI, imei));
		Collection<Equipe> colEquipes = this.getControladorUtil().pesquisar(filtroEquipe, Equipe.class.getName());

		Equipe equipe = (Equipe) Util.retonarObjetoDeColecao(colEquipes);

		if (equipe != null && !equipe.equals("")) {
			try {
				this.repositorioOrdemServico.atualizarSituacaoArquivoTextoAcompanhamentoServico(equipe.getId(), idSituacaoTransmissaoLeitura);
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}
		}

	}

}
