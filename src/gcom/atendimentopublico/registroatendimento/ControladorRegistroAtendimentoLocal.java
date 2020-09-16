package gcom.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.bean.RegistroAtendimentoDevolucaoValoresHelper;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.portal.QuestionarioSatisfacaoCliente;
import gcom.atendimentopublico.registroatendimento.bean.DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper;
import gcom.atendimentopublico.registroatendimento.bean.FiltrarGestaoRAHelper;
import gcom.atendimentopublico.registroatendimento.bean.FiltrarRegistroAtendimentoHelper;
import gcom.atendimentopublico.registroatendimento.bean.FiltrarRelatorioRegistroAtendimentoPorUnidadePorUsuarioHelper;
import gcom.atendimentopublico.registroatendimento.bean.FiltrarRelatorioResumoSolicitacoesRAPorUnidadeHelper;
import gcom.atendimentopublico.registroatendimento.bean.GerarNumeracaoRAManualHelper;
import gcom.atendimentopublico.registroatendimento.bean.GestaoRegistroAtendimentoHelper;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosIdentificacaoLocalOcorrenciaHelper;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.atendimentopublico.registroatendimento.bean.ObterDescricaoSituacaoRAHelper;
import gcom.atendimentopublico.registroatendimento.bean.ObterIndicadorExistenciaHidrometroHelper;
import gcom.atendimentopublico.registroatendimento.bean.ObterRAAssociadoHelper;
import gcom.atendimentopublico.registroatendimento.bean.RAFaltaAguaPendenteHelper;
import gcom.atendimentopublico.registroatendimento.bean.RAPorUnidadePorUsuarioHelper;
import gcom.atendimentopublico.registroatendimento.bean.RegistroAtendimentoEncerradoLocalOcorrenciaHelper;
import gcom.atendimentopublico.registroatendimento.bean.RegistroAtendimentoFaltaAguaGeneralizadaHelper;
import gcom.atendimentopublico.registroatendimento.bean.RegistroAtendimentoPendenteLocalOcorrenciaHelper;
import gcom.atendimentopublico.registroatendimento.bean.VerificarRAFaltaAguaHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.operacional.DivisaoEsgoto;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

public interface ControladorRegistroAtendimentoLocal extends javax.ejb.EJBLocalObject {

	public DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper definirDataPrevistaUnidadeDestinoEspecificacao(Date dataAtendimento,
			Integer idSolicitacaoTipoEspecificacao) throws ControladorException;

	public void validarInserirRegistroAtendimentoDadosGerais(String dataAtendimento, String horaAtendimento, String tempoEsperaInicial,
			String tempoEsperaFinal, String idUnidadeOrganizacional, String numeroRAManual) throws ControladorException;

	public boolean verificarExigenciaImovelPelaEspecificacao(Integer idSolicitacaoTipoEspecificacao) throws ControladorException;

	public ObterDadosIdentificacaoLocalOcorrenciaHelper obterDadosIdentificacaoLocalOcorrencia(Integer idImovel, Integer idSolicitacaoTipoEspecificacao,
			Integer idSolicitacaoTipo, boolean levantarExceptionImovelInexistente) throws ControladorException;

	public boolean verificaExistenciaRAPendenteImovel(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Integer inserirTipoSolicitacaoEspecificacao(SolicitacaoTipo solicitacaoTipo, Collection colecaoSolicitacaoTipoEspecificacao, Usuario usuarioLogado)
			throws ControladorException;

	public Collection<RegistroAtendimento> filtrarRegistroAtendimento(FiltrarRegistroAtendimentoHelper filtroRA) throws ControladorException;

	public ObterDadosIdentificacaoLocalOcorrenciaHelper habilitarGeograficoDivisaoEsgoto(Integer idSolicitacaoTipo) throws ControladorException;

	public UnidadeOrganizacional definirUnidadeDestinoLocalidade(Integer idSolicitacaoTipoEspecificacao, Integer idLocalidade, Integer idSolicitacaoTipo,
			boolean solicitacaoTipoRelativoAreaEsgoto) throws ControladorException;

	public DivisaoEsgoto obterDivisaoEsgoto(Integer idQuadra, boolean solicitacaoTipoRelativoAreaEsgoto) throws ControladorException;

	public void verificarCompatibilidadeDivisaoEsgotoLocalidadeSetorQuadra(Integer idLocalidade, Integer idSetorComercial, Integer idQuadra, int idDivisaoEsgoto)
			throws ControladorException;

	public UnidadeOrganizacional definirUnidadeDestinoDivisaoEsgoto(Integer idSolicitacaoTipoEspecificacao, Integer idDivisaoEsgoto,
			boolean solicitacaoTipoRelativoAreaEsgoto, Integer idLocalidade, Integer idSolicitacaoTipo) throws ControladorException;

	public void verificaPossibilidadeEncaminhamentoUnidadeDestino(UnidadeOrganizacional unidadeDestino) throws ControladorException;

	public void verificarServicoTipoReferencia(Integer idServicoTipo) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void verificarExistenciaOrdemExecucao(Collection colecaoEspecificacaoServicoTipo, Short ordemExecucao) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void verificarOrdemExecucaoForaOrdem(Collection colecaoEspecificacaoServicoTipo) throws ControladorException;

	public void verificarExistenciaRAImovelMesmaEspecificacao(Integer idImovel, Integer idSolicitacaoTipoEspecificacao) throws ControladorException;

	public Tramite recuperarTramiteMaisAtualPorRA(Integer idRA) throws ControladorException;

	public UnidadeOrganizacional obterUnidadeAtualRA(Integer idRegistroAtendimento) throws ControladorException;

	public Short obterIndicadorAutorizacaoManutencaoRA(Integer idUnidadeOrganizacional, Integer idUsuario) throws ControladorException;

	public ObterDescricaoSituacaoRAHelper obterDescricaoSituacaoRA(Integer idRegistroAtendimento) throws ControladorException;

	public UnidadeOrganizacional obterUnidadeAtendimentoRA(Integer idRegistroAtendimento) throws ControladorException;

	public String obterEnderecoOcorrenciaRA(Integer idRegistroAtendimento) throws ControladorException;

	public String obterEnderecoSolicitanteRA(Integer idRegistroAtendimentoSolicitante) throws ControladorException;

	public ObterRAAssociadoHelper obterRAAssociadoConsultarRA(Integer idRegistroAtendimento) throws ControladorException;

	public ObterRAAssociadoHelper obterRAAssociado(Integer idRegistroAtendimento) throws ControladorException;

	public UnidadeOrganizacional obterUnidadeEncerramentoRA(Integer idRegistroAtendimento) throws ControladorException;

	public String obterNomeSolicitanteRA(Integer idRegistroAtendimento) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Integer reiterarRegistroAtendimento(RegistroAtendimento registroAtendimento, Usuario usuario, RAReiteracao raReiteracao,
			Collection colecaoFonesSolicitante) throws ControladorException;

	public void verificarSituacaoImovelEspecificacao(Imovel imovel, Integer idSolicitacaoTipoEspecificacao) throws ControladorException;

	public Integer verificarOrdemServicoParaRA(Integer idRegistroAtendimento) throws ControladorException;

	public Object[] pesquisarParmsRegistroAtendimento(Integer idRegistroAtendimento) throws ControladorException;

	public Collection<Tramite> obterTramitesRA(Integer idRA) throws ControladorException;

	public Collection<RegistroAtendimentoSolicitante> obterRASolicitante(Integer idRA) throws ControladorException;

	public Collection<OrdemServico> obterOSRA(Integer idRA) throws ControladorException;

	public UnidadeOrganizacional verificarPossibilidadeAtualizacaoRA(Integer idRA, Integer idUsuarioLogado) throws ControladorException;

	public ObterIndicadorExistenciaHidrometroHelper obterIndicadorExistenciaHidrometroLigacaoAguaPoco(Integer idImovel) throws ControladorException;

	public int verificarRASemIdentificacaoLO(Integer idImovel, Integer idRA) throws ControladorException;

	public ObterDadosRegistroAtendimentoHelper obterDadosRegistroAtendimento(Integer idRegistroAtendimento) throws ControladorException;

	public RegistroAtendimento verificaExistenciaRAPendenteLocalOcorrencia(Integer idSolicitacaoTipoEspecificacao, Integer idLogradouroBairro,
			Integer idLogradouroCep) throws ControladorException;

	public RegistroAtendimentoPendenteLocalOcorrenciaHelper pesquisarRAPendenteLocalOcorrencia(Integer idSolicitacaoTipoEspecificacao, Integer idLogradouroCep,
			Integer idLogradouroBairro) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection<ServicoTipo> filtrarST(ServicoTipo servicoTipo, Collection colecaoAtividades, Collection colecaoMaterial, String valorServicoInicial,
			String valorServicoFinal, String tempoMedioExecucaoInicial, String tempoMedioExecucaoFinal) throws ControladorException;

	public VerificarRAFaltaAguaHelper verificarRegistroAtendimentoFaltaAgua(Integer idRegistroAtendimento, Date dataAtendimento, Integer idBairroArea,
			Integer idBairro, Integer idEspecificacao, Short indFaltaAgua, Integer indMatricula, String continua) throws ControladorException;

	public RegistroAtendimento validarRegistroAtendimento(Integer idRA) throws ControladorException;

	public void validarTramitacao(Tramite tramite, Usuario usuario) throws ControladorException;

	public void tramitar(Tramite tramite, Date dataConcorrente) throws ControladorException;

	public Collection<Integer> recuperaRAPorUnidadeAtual(UnidadeOrganizacional unidadeOrganizacional) throws ControladorException;

	public RAFaltaAguaPendenteHelper carregarObjetoRAFaltaAguaPendente(Integer idRegistroAtendimento, Integer idBairroArea, Integer idEspecificacao)
			throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso a matrícula do imóvel da Aba Dados do Local da Ocorrência tenha sido
	 * informada e o Cliente informado não seja um cliente do imóvel (inexiste
	 * Ocorrência na tabela CLIENTE_IMOVEL com CLIE_ID=Id do cliente e
	 * IMOV_ID=matrícula do imóvel e CLIM_DTRELACAOFIM com o valor nulo).
	 * 
	 * [FS0027] Verificar informação do imóvel
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2006
	 * 
	 * @param idCliente
	 *            , idImovel
	 * @return Cliente
	 * @throws ErroRepositorioException
	 */
	public Cliente verificarInformacaoImovel(Integer idCliente, Integer idImovel, boolean levantarException) throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso esteja adicionando um novo solicitante e o cliente já seja um
	 * solicitante do registro de atendimento (existe Ocorrência na tabela
	 * REGISTRO_ATENDIMENTO_SOLICITANTE com RGAT_ID=RGAT_ID do registro de
	 * atendimento selecionado e CLIE_ID=Id do Cliente informado).
	 * 
	 * [FS0012] Verificar existência do cliente solicitante
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2006
	 * 
	 * @param idRegistroAtendimento
	 *            , idCliente
	 * @throws ErroRepositorioException
	 */
	public void verificarExistenciaClienteSolicitante(Integer idRegistroAtendimento, Integer idCliente) throws ControladorException;

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * Obter e habilitar/desabilitar os Dados da Identificação do Local da
	 * Ocorrência de acordo com as situações abaixo descritas no caso de uso
	 * 
	 * [SB0004] Obtém e Habilita/Desabilita Dados da Identificação do Local da
	 * Ocorrência e Dados do Solicitante
	 * 
	 * @author Sávio Luiz
	 * @date 28/07/2006
	 * 
	 * @param idImovel
	 *            , idSolicitacaoTipoEspecificacao, idSolicitacaoTipo,
	 *            levantarExceptionImovelInexistente
	 * @return ObterDadosIdentificacaoLocalOcorrenciaHelper
	 * @throws ControladorException
	 */
	public ObterDadosIdentificacaoLocalOcorrenciaHelper obterDadosIdentificacaoLocalOcorrenciaAtualizar(Integer idImovel,
			Integer idSolicitacaoTipoEspecificacao, Integer idSolicitacaoTipo, Integer idRegistroAtendimento, boolean levantarExceptionImovelInexistente)
			throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso esteja adicionando um novo solicitante e a unidade já seja um
	 * solicitante do registro de atendimento (existe Ocorrência na tabela
	 * REGISTRO_ATENDIMENTO_SOLICITANTE com RGAT_ID=RGAT_ID do registro de
	 * atendimento selecionado e UNID_ID=Id da Unidade informada).
	 * 
	 * [FS0026] Verificar existência da unidade solicitante
	 * 
	 * @author Raphael Rossiter
	 * @date 23/08/2006
	 * 
	 * @param idRegistroAtendimento
	 *            , idUnidade
	 * @throws ControladorException
	 */
	public void verificarExistenciaUnidadeSolicitante(Integer idRegistroAtendimento, Integer idUnidade) throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento e [UC0408] Atualizar Registro de
	 * Atendimento
	 * 
	 * 
	 * [FS0040] Verificar preenchimento campos. 2 ABA
	 * 
	 * @author Sávio Luiz
	 * @date 24/08/2006
	 * 
	 * @throws ControladorException
	 */
	public void validarCamposObrigatoriosRA_2ABA(String idImovel, String pontoReferencia, String idMunicipio, String descricaoMunicipio, String cdBairro,
			String descricaoBairro, String idAreaBairro, String idlocalidade, String descricaoLocalidade, String cdSetorComercial,
			String descricaoSetorComercial, String numeroQuadra, String idDivisaoEsgoto, String idUnidade, String descricaoUnidade, String idLocalOcorrencia,
			String idPavimentoRua, String idPavimentoCalcada, String descricaoLocalOcorrencia, String imovelObrigatorio, String pavimentoRuaObrigatorio,
			String pavimentoCalcadaObrigatorio, String solicitacaoTipoRelativoFaltaAgua, String solicitacaoTipoRelativoAreaEsgoto,
			String desabilitarMunicipioBairro, String indRuaLocalOcorrencia, String indCalcadaLocalOcorrencia, Integer idEspecificacao,
			Integer idRAAtualizacao, Collection colecaoEndereco, SolicitacaoTipoEspecificacao especificacao, Collection colecaoPagamento, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * [UC0435] Encerrar Registro de Atendimento
	 * 
	 * Validar Pré-Encerramento
	 * 
	 * [FS0001] Verificar possibilidade de encerramento do registro de
	 * atendimento
	 * 
	 * @author Leonardo Regis
	 * @date 25/08/2006
	 * 
	 * @param registroAtendimento
	 * @param usuarioLogado
	 * @param indicadorAutorizacaoManutencaoRA
	 * @throws ControladorException
	 */
	public void validarPreEncerramentoRA(RegistroAtendimento registroAtendimento, Usuario usuarioLogado, Short indicadorAutorizacaoManutencaoRA)
			throws ControladorException;

	/**
	 * [UC0435] Encerrar Registro de Atendimento
	 * 
	 * Validar Pré-Encerramento
	 * 
	 * [FS0001] Verificar possibilidade de encerramento do registro de
	 * atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 08/08/2007
	 * 
	 * @param registroAtendimento
	 * @param usuarioLogado
	 * @param indicadorAutorizacaoManutencaoRA
	 * @throws ControladorException
	 */
	public void validarPreEncerramentoRASemTarifaSocial(RegistroAtendimento registroAtendimento, Usuario usuarioLogado, Short indicadorAutorizacaoManutencaoRA)
			throws ControladorException;

	/**
	 * 
	 * [UC0435] - Encerrar Registro de Atendimento
	 * 
	 * [FS003] Validar RA de Referência
	 * 
	 * @author Leonardo Regis
	 * @date 26/08/2006
	 */
	public RegistroAtendimento validarRAReferencia(Integer idRA, Integer idRAReferencia) throws ControladorException;

	/**
	 * [UC0435] Encerrar Registro de Atendimento
	 * 
	 * Validar Encerramento
	 * 
	 * [FS0004] Verificar data do encerramento [FS0005] Verificar hora do
	 * encerramento
	 * 
	 * @author Leonardo Regis
	 * @date 26/08/2006
	 * 
	 * @param registroAtendimento
	 * @throws ControladorException
	 */
	public void validarEncerramentoRA(RegistroAtendimento registroAtendimento) throws ControladorException;

	/**
	 * [UC0435] Encerrar Registro de Atendimento
	 * 
	 * @author Leonardo Regis
	 * @date 26/08/2006
	 * 
	 * @param registroAtendimento
	 * @param registroAtendimentoUnidade
	 * @param dataConcorrencia
	 * @return 
	 */
	public Integer encerrarRegistroAtendimento(RegistroAtendimento registroAtendimento, RegistroAtendimentoUnidade registroAtendimentoUnidade,
			Usuario usuarioLogado, Integer idDebitoTipo, BigDecimal valorDebito, Integer qtdeParcelas, String percentualCobranca,
			Boolean confirmadoGeracaoNovoRA, String urlBotaoVoltar, boolean encerrarDebitoACobrar) throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [FS0030] Verificar preenchimento dos dados de Identificação do
	 * solicitante
	 * 
	 * @author Raphael Rossiter
	 * @date 24/08/2006
	 * 
	 * @throws ControladorException
	 */
	public void verificaDadosSolicitante(Integer idCliente, Integer idUnidadeSolicitante, Integer idFuncionario, String nomeSolicitante,
			Collection colecaoEndereco, Collection colecaoFone, Short indicadorClienteEspecificacao, Integer idImovel, Integer idRegistroAtendimento,
			Integer idEspecificacao, Integer idMeioSolicitacao) throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0027] Inclui Solicitante do Registro de Atendimento
	 * (REGISTRO_ATENDIMENTO_SOLICITANTE)
	 * 
	 * @author Raphael Rossiter, Raphael Rossiter
	 * @date 24/08/2006, 07/08/2009
	 * 
	 * @throws ControladorException
	 */
	public void inserirRegistroAtendimentoSolicitante(Integer idRegistroAtendimento, Integer idCliente, Collection colecaoEndereco, String pontoReferencia,
			String nomeSolicitante, boolean novoSolicitante, Integer idUnidadeSolicitante, Integer idFuncionario, Collection colecaoFone,
			String protocoloAtendimento, String habilitarCampoSatisfacaoEmail, String enviarEmailSatisfacao, String enderecoEmail) throws ControladorException;

	/**
	 * 
	 * passa os parametros do registro atendimento solicitante e a coleção de
	 * fones do solicitante e retorna um objeto de Registro Atendimento
	 * Solicitante
	 * 
	 * @author Sávio Luiz
	 * @date 02/09/2006
	 * 
	 * @throws ControladorException
	 */
	public RegistroAtendimentoSolicitante inserirDadosNoRegistroAtendimentoSolicitante(Integer idRegistroAtendimento, Integer idCliente,
			Collection colecaoEndereco, String pontoReferencia, String nomeSolicitante, Integer idUnidadeSolicitante, Integer idFuncionario,
			Collection colecaoFone, String fonePadrao) throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0017] - Verificar registro de Atendimento de falta de Água
	 * 
	 * @author Raphael Rossiter
	 * @date 16/08/2006
	 * 
	 * @throws ControladorException
	 */
	public VerificarRAFaltaAguaHelper verificarRegistroAtendimentoFaltaAguaInserir(Date dataAtendimento, Integer idBairroArea, Integer idBairro,
			Integer idEspecificacao, Short indFaltaAgua, Integer indMatricula, String continua) throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0028] Inclui Registro de Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 28/08/2006
	 * 
	 * @throws ControladorException
	 */
	public Integer[] inserirRegistroAtendimento(RADadosGeraisHelper raDadosGeraisHelper, RALocalOcorrenciaHelper raLocalOcorrenciaHelper,
			RASolicitanteHelper raSolicitanteHelper) throws ControladorException;

	/**
	 * [UC0440] Consultar Programação de Manutenção
	 * 
	 * Caso exista Programação de Manutenção de uma determinada Área de Bairro
	 * 
	 * @author Rômulo Aurélio
	 * @date 28/08/2006
	 * 
	 * @param idMunicipio
	 * @param idBairro
	 * @param areaBairro
	 * @param mesAnoReferencia
	 * @return Collection<ManutencaoProgramacao>
	 * @throws ControladorException
	 * @throws ControladorException
	 */

	public Collection consultarProgramacaoManutencao(String idMunicipio, String idBairro, String areaBairro, String mesAnoReferencia)
			throws ControladorException;

	/**
	 * [UC0440] Consultar Programação de Abastecimento
	 * 
	 * Caso exista Programação de Abastecimento de uma determinada Área de
	 * Bairro
	 * 
	 * @author Rômulo Aurélio
	 * @date 28/08/2006
	 * 
	 * @param idMunicipio
	 * @param idBairro
	 * @param areaBairro
	 * @param mesAnoReferencia
	 * @return Collection<AbastecimentoProgramacao>
	 * @throws ControladorException
	 * @throws ControladorException
	 */
	public Collection consultarProgramacaoAbastecimento(String idMunicipio, String idBairro, String areaBairro, String mesAnoReferencia)
			throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso a Especificação esteja associada a um tipo de Serviço (SVTP_ID da
	 * tabela SOLICITACAO_TIPO_ESPECIFICACAO com o valor diferente de nulo).
	 * (AUTOMÁTICA)
	 * 
	 * [SB0030] Gerar Ordem de Serviço
	 * 
	 * @author Raphael Rossiter
	 * @date 29/08/2006
	 * 
	 * @param idSolicitacaoTipoEspecificacao
	 * @return boolean
	 * @throws ControladorException
	 */
	public boolean gerarOrdemServicoAutomatica(Integer idSolicitacaoTipoEspecificacao) throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso a Especificação possa gerar alguma ordem de Serviço
	 * (STEP_ICGERACAOORDEMSERVICO da tabela SOLICITACAO_TIPO_ESPECIFICACAO com
	 * o valor correspondente a um). (OPCIONAL) [SB0030] Gerar Ordem de Serviço
	 * 
	 * @author Raphael Rossiter
	 * @date 29/08/2006
	 * 
	 * @param idSolicitacaoTipoEspecificacao
	 * @return boolean
	 * @throws ControladorException
	 */
	public boolean gerarOrdemServicoOpcional(Integer idSolicitacaoTipoEspecificacao) throws ControladorException;

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * [SB0028] Atualizar Registro de Atendimento (REGISTRO_ATENDIMENTO)
	 * 
	 * @author Sávio Luiz
	 * @date 30/08/2006
	 * 
	 * @throws ControladorException
	 */
	public Integer[] atualizarRegistroAtendimento(Integer idRA, short indicadorAtendimentoOnLine, String dataAtendimento, String horaAtendimento,
			String tempoEsperaInicial, String tempoEsperaFinal, Integer idMeioSolicitacao, Integer idSolicitacaoTipoEspecificacao, String dataPrevista,
			String observacao, Integer idImovel, String descricaoLocalOcorrencia, Integer idSolicitacaoTipo, Collection colecaoEndereco,
			String pontoReferenciaLocalOcorrencia, Integer idBairroArea, Integer idLocalidade, Integer idSetorComercial, Integer idQuadra,
			Integer idDivisaoEsgoto, Integer idLocalOcorrencia, Integer idPavimentoRua, Integer idPavimentoCalcada, Integer idUnidadeAtendimento,
			Usuario usuarioLogado, Integer imovelObrigatorio, Date ultimaAlteracao, Collection colecaoRASolicitante, Collection colecaoRASolicitanteRemovida,
			Integer idServicoTipo, Integer especificacaoNaBase, Integer idUnidadeAtual, BigDecimal nnCoordenadaNorte, BigDecimal nnCoordenadaLeste,
			Collection colecaoRegistroAtendimentoAnexo, Collection colecaoRegistroAtendimentoConta, Collection colecaoRegistroAtendimentoContaRemover,
			Collection colecaoPagamento, BigDecimal nnDiametro

	) throws ControladorException;

	/**
	 * [UC0426] Reativar Registro de Atendimento
	 * 
	 * 
	 * [SB0001] Define Data Prevista
	 * 
	 * @author Ana Maria
	 * @date 24/08/2006
	 * 
	 * @param login
	 * @return UnidadeOrganizacional
	 * @throws ControladorException
	 */
	public Date definirDataPrevistaRA(Date dataAtendimento, Integer idSolicitacaoTipoEspecificacao) throws ControladorException;

	/**
	 * [UC0426] Reativar Registro de Atendimento
	 * 
	 * 
	 * [SB0001],[SB0002],[SB0003] Define Unidade Destino
	 * 
	 * @author Ana Maria
	 * @date 24/08/2006
	 * 
	 * @param login
	 * @return UnidadeOrganizacional
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional definirUnidadeDestino(Integer idSolicitacaoTipoEspecificacao, Integer idLocalidade, Integer idSolicitacaoTipo,
			boolean solicitacaoTipoRelativoAreaEsgoto, Integer idDivisaoEsgoto) throws ControladorException;

	/**
	 * [UC0426] Reativar Registro de Atendimento
	 * 
	 * [SB0006]Incluir Registro de Atendimento
	 * 
	 * @author Ana Maria
	 * @date 29/08/2006
	 * 
	 * @throws ControladorException
	 */
	public Integer[] reativarRegistroAtendimento(RegistroAtendimento ra, Integer idUnidadeAtendimento, Integer idUsuarioLogado, Integer idCliente,
			Integer idRaSolicitante, Integer idUnidadeDestino, String parecerUnidadeDestino, Integer idSolicitacaoTipo) throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * SOTP_ICFALTAAGUA da tabela SOLICITACAO_TIPO com o valor correspondente a
	 * um e STEP_ICMATRICULA com o valor correspondente a dois na tabela
	 * SOLICITACAO_TIPO_ESPECIFICACAO).
	 * 
	 * @author Raphael Rossiter
	 * @date 29/08/2006
	 * 
	 * @return SolicitacaoTipoEspecificacao
	 * @throws ControladorException
	 */
	public SolicitacaoTipoEspecificacao pesquisarTipoEspecificacaoFaltaAguaGeneralizada() throws ControladorException;

	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 06/09/2006
	 */
	public Tramite recuperarTramiteMaisAtualPorUnidadeDestino(Integer idUnidade) throws ControladorException;

	/**
	 * [UC0426] Reativar Registro de Atendimento
	 * 
	 * [FS0001]Valida possibilidade de reativação
	 * 
	 * @author Ana Maria
	 * @date 29/08/2006
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public void validaPossibilidadeReativacaoRA(Integer idRegistroAtendimento, Integer idUsuario) throws ControladorException;

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * pesquisa os fones do regsitro atendimento solicitante e joga na coleção
	 * de ClientesFones
	 * 
	 * @author Sávio Luiz
	 * @date 05/09/2006
	 * 
	 * @return idRASolicitante
	 * @throws ControladorException
	 */
	public Collection pesquisarParmsFoneRegistroAtendimentoSolicitante(Integer idRASolicitante) throws ControladorException;

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * [FS0030] Verificar preenchimento dos dados de identificação do
	 * solicitante
	 * 
	 * @author Sávio Luiz
	 * @date 07/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void verificaDadosSolicitanteAtualizar(Integer idCliente, Integer idUnidadeSolicitante, Integer idFuncionario, String nomeSolicitante,
			Collection colecaoEndereco, Collection colecaoFone, Short indicadorClienteEspecificacao, Integer idImovel, Integer idRegistroAtendimento,
			Integer idRASolicitante) throws ControladorException;

	/**
	 * [UC0408] Inserir Registro de Atendimento
	 * 
	 * Caso esteja adicionando um novo solicitante e o cliente já seja um
	 * solicitante do registro de atendimento (existe Ocorrência na tabela
	 * REGISTRO_ATENDIMENTO_SOLICITANTE com RGAT_ID=RGAT_ID do registro de
	 * atendimento selecionado e CLIE_ID=Id do Cliente informado e o registro
	 * atendimento solicitante for diferente do que está sendo atualizado).
	 * 
	 * [FS0027] Verificar existência do cliente solicitante
	 * 
	 * @author Sávio Luiz
	 * @date 21/08/2006
	 * 
	 * @param idRegistroAtendimento
	 *            , idCliente
	 * @throws ErroRepositorioException
	 */
	public void verificarExistenciaClienteSolicitanteAtualizar(Integer idRegistroAtendimento, Integer idCliente, Integer idRASolicitante)
			throws ControladorException;

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * Caso esteja adicionando um novo solicitante e a unidade já seja um
	 * solicitante do registro de atendimento (existe Ocorrência na tabela
	 * REGISTRO_ATENDIMENTO_SOLICITANTE com RGAT_ID=RGAT_ID do registro de
	 * atendimento selecionado e UNID_ID=Id da Unidade informada e RASO_ID<>id
	 * do Registro atendimento solicitante).
	 * 
	 * [FS0018] Verificar existência da unidade solicitante
	 * 
	 * @author Sávio Luiz
	 * @date 07/09/2006
	 * 
	 * @param idRegistroAtendimento
	 *            , idUnidade
	 * @throws ControladorException
	 */
	public void verificarExistenciaUnidadeSolicitanteAtualizar(Integer idRegistroAtendimento, Integer idUnidade, Integer idRASolicitante)
			throws ControladorException;

	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * 
	 * @author Rafael Pinto
	 * @date 13/09/2006
	 * 
	 * @param idRa
	 * @return dataPrevisaoAtual
	 * @throws ControladorException
	 */
	public Date obterDataAgenciaReguladoraPrevisaoAtual(Integer idRa) throws ControladorException;

	/**
	 * Consultar os registros de atendimento do Imovel [UC0472] Consultar Imovel
	 * 
	 * @author Rafael Santos
	 * @date 25/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection consultarRegistroAtendimentoImovel(Integer idImovel, String situacao) throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0025] Verifica Registro de Atendimento de Água Generalizada
	 * 
	 * @author Raphael Rossiter
	 * @date 28/08/2006
	 * 
	 * @throws ControladorException
	 */
	public RegistroAtendimentoFaltaAguaGeneralizadaHelper verificarRegistroAtendimentoFaltaAguaGeneralizafa(Integer idEspecificacao, Integer idBairroArea)
			throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0015] Verifica Registro de Atendimento Encerrado para o Local da
	 * Ocorrência
	 * 
	 * @author Raphael Rossiter
	 * @date 28/08/2006
	 * 
	 * @throws ControladorException
	 */
	public RegistroAtendimentoEncerradoLocalOcorrenciaHelper verificarRegistroAtendimentoEncerradoLocalOcorrencia(Integer idImovel, Integer idEspecificacao,
			Integer idLogradouroBairro, Integer idLogradouroCep) throws ControladorException;

	/**
	 * Pesquisa os dados do Registro Atendimento Solicitante para o Relatório de
	 * OS
	 * 
	 * @author Rafael Corrêa
	 * @date 17/10/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] pesquisarDadosRegistroAtendimentoSolicitanteRelatorioOS(Integer idRegistroAtendimento) throws ControladorException;

	/**
	 * Pesquisa o telefone principal do Registro Atendimento Solicitante para o
	 * Relatório de OS
	 * 
	 * @author Rafael Corrêa
	 * @date 17/10/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */

	public String pesquisarSolicitanteFonePrincipal(Integer idRegistroAtendimento) throws ControladorException;

	/**
	 * 
	 * Pesquisar o Endereço descritivo do RA a partir do seu id
	 * 
	 * [UC0482] - Obter Endereço Abreviado da Ocorrência do RA
	 * 
	 * @author Rafael Corrêa
	 * @date 18/10/2006
	 * 
	 * @param idRA
	 * @return String
	 * @throws ControladorException
	 */

	public String obterEnderecoDescritivoRA(Integer idRA) throws ControladorException;

	/**
	 * [UC0482] - Obter Endereço Abreviado da Ocorrência do RA
	 * 
	 * Pesquisa o Endereço abreviado da Ocorrência do RA
	 * 
	 * @author Rafael Corrêa
	 * @date 17/10/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */

	public String obterEnderecoAbreviadoOcorrenciaRA(Integer idRA) throws ControladorException;

	/**
	 * Caso não exista para o imóvel RA encerrada por execução com Especificação
	 * da Solicitação que permita a manuntenção de imóvel
	 * 
	 * @author Rafael Santos
	 * @since 26/10/2006
	 * 
	 */
	public RegistroAtendimento verificarExistenciaRegistroAtendimento(Integer idImovel, String mensagemErro, char codigoConstante) throws ControladorException;

	/**
	 * Caso não exista para o imóvel RA encerrada por execução com Especificação
	 * da Solicitação No caso de Tarifa Social
	 * 
	 * @author Rafael Santos
	 * @since 26/10/2006
	 * 
	 */
	public void verificarExistenciaRegistroAtendimentoTarifaSocial(Integer idImovel, String mensagemErro) throws ControladorException;

	/**
	 * 
	 * [UC0430] - Gerar Ordem de Serviço
	 * 
	 * @author lms
	 * @date 11/08/2006
	 */
	public void verificarUnidadeUsuario(RegistroAtendimento registroAtendimento, Usuario usuario) throws ControladorException;

	/**
	 * [UC0494] Gerar Numeração de RA Manual
	 * 
	 * @author Raphael Rossiter
	 * @date 06/11/2006
	 * 
	 * @throws ControladorException
	 */
	public GerarNumeracaoRAManualHelper GerarNumeracaoRAManual(Integer quantidade, Integer idUnidadeOrganizacional) throws ControladorException;

	/**
	 * [UC0404] - Manter Especificação da Situação do imóvel
	 * 
	 * [SB0001] Atualizar Critério de Cobrança
	 * 
	 * @author Rafael Pinto
	 * @created 09/11/2006
	 * 
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public void atualizarEspecificacaoSituacaoImovel(EspecificacaoImovelSituacao especificacaoImovelSituacao, Collection colecaoEspecificacaoCriterios,
			Collection colecaoEspecificacaoCriteriosRemovidas, Usuario usuario) throws ControladorException;

	/**
	 * [UC00054] Inserir Tarifa Social
	 * 
	 * [FS00001] Verificar Registro Atendimento
	 * 
	 * @author Rafael Santos
	 * @date 10/11/2006
	 * 
	 * @param idRegistroAtendimento
	 * @return
	 * @throws ControladorException
	 */
	public RegistroAtendimento verificarRegistroAtendimentoTarifaSocial(String idRegistroAtendimento) throws ControladorException;

	/**
	 * [UC0401] Atualizar Tipo de Solicitação com Especificação
	 * 
	 * [SB0001] - Atualizar Tipo Solicitação com Especificaões
	 * 
	 * @author Rômulo Aurélio
	 * @date 01/08/2006
	 * 
	 * @param solicitacaoTipo
	 *            , colecaoSolicitacaoTipoEspecificacao, usuarioLogado
	 * @return Integer
	 * @throws ControladorException
	 */

	public Integer atualizarTipoSolicitacaoEspecificacao(SolicitacaoTipo solicitacaoTipo, Collection colecaoSolicitacaoTipoEspecificacao,
			Usuario usuarioLogado, Collection colecaoSolicitacaoTipoEspecificacaoRemovidos) throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0027] Inclui Solicitante do Registro de Atendimento
	 * (REGISTRO_ATENDIMENTO_SOLICITANTE)
	 * 
	 * @author Raphael Rossiter
	 * @date 24/08/2006
	 * 
	 * @throws ControladorException
	 */
	public boolean clienteObrigatorioInserirRegistroAtendimento(Integer idEspecificacao) throws ControladorException;

	/**
	 * [UC00503]Tramitar Conjunto de Registro de Atendimento
	 *
	 * [SB0003]Incluir o Tramite
	 *
	 * @author Ana Maria
	 * @date 16/01/2007
	 * 
	 */
	public void tramitarConjuntoRA(Collection tramites) throws ControladorException;

	/**
	 * [UC00503]Tramitar Conjunto de Registro de Atendimento
	 * 
	 * [FS0006] Valida Data [FS0007] Valida Hora [FS0008] Valida Unidade Destino
	 * 
	 * @author Ana Maria
	 * @date 16/01/2007
	 * 
	 */
	public void validarConjuntoTramitacao(String[] ids, Date dataHoraTramitacao, Integer idUnidadeDestino, Usuario usuario) throws ControladorException;

	/**
	 * [UC00503]Tramitar Conjunto de Registro de Atendimento
	 *
	 * [FS0002] Verificar as situações das OS associadas ao RA [FS0003]
	 * Verificar se o tipo de Solicitação é Tarifa Social
	 * 
	 * @author Ana Maria
	 * @date 16/01/2007
	 * 
	 */
	public void validarRATramitacao(String[] ids, Integer idUsuario) throws ControladorException;

	/**
	 * [UC00069] Manter Dados Tarifa Social
	 * 
	 * [FS00001] Verificar Registro Atendimento
	 * 
	 * @author Rafael Corrêa
	 * @date 05/02/2007
	 * 
	 * @param idRegistroAtendimento
	 * @return
	 * @throws ControladorException
	 */
	public RegistroAtendimento verificarRegistroAtendimentoManterTarifaSocial(String idRegistroAtendimento) throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso a especificação informada para o RA tem indicativo que é para
	 * verificar débito (STEP_ICVERIFICADEBITO da tabela
	 * SOLICITACAO_TIPO_ESPECIFICACAO com valor igual a SIM (1)), o sistema
	 * deverá verificar se o imóvel informado tenha débito <<incluir>> [UC0067]
	 * Obter Débito do Imóvel ou Cliente (passando o imóvel). [FS0043] -
	 * Verifica imóvel com débito.
	 * 
	 * [SB0032] - Verifica se o imóvel informado tem débito.
	 * 
	 * [FS0043] - Verifica imóvel com débito
	 * 
	 * @author Raphael Rossiter
	 * @date 19/02/2006
	 * 
	 * @param idSolicitacaoTipoEspecificacao
	 * @return boolean
	 * @throws ControladorException
	 */
	public void verificarImovelComDebitos(Integer idSolicitacaoTipoEspecificacao, Integer idImovel) throws ControladorException;

	/**
	 * Atualiza logradouroCep de um ou mais imóveis
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarLogradouroCep(LogradouroCep logradouroCepAntigo, LogradouroCep logradouroCepNovo) throws ControladorException;

	/**
	 * Atualiza logradouroBairro de um ou mais imóveis
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarLogradouroBairro(LogradouroBairro logradouroBairroAntigo, LogradouroBairro logradouroBairroNovo) throws ControladorException;

	/**
	 * Atualiza logradouroCep de um ou mais imóveis
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarLogradouroCepSolicitante(LogradouroCep logradouroCepAntigo, LogradouroCep logradouroCepNovo) throws ControladorException;

	/**
	 * Atualiza logradouroBairro de um ou mais imóveis
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarLogradouroBairroSolicitante(LogradouroBairro logradouroBairroAntigo, LogradouroBairro logradouroBairroNovo)
			throws ControladorException;

	/**
	 * [UC0406] Filtrar Registro de Atendimento
	 * 
	 * @author Leonardo Regis
	 * @date 07/08/2006
	 * 
	 * @param filtroRA
	 * @return integer - tamanho
	 * @throws ControladorException
	 */
	public Integer filtrarRegistroAtendimentoTamanho(FiltrarRegistroAtendimentoHelper filtroRA) throws ControladorException;

	/**
	 * Consultar Observacao Registro Atendimento Solicitacao da CAER
	 * 
	 * @author Rafael Pinto
	 * @date 14/03/2007
	 */
	public Collection<RegistroAtendimento> pesquisarObservacaoRegistroAtendimento(Integer matriculaImovel, Date dataInicialAtendimento,
			Date dataFinalAtendimento) throws ControladorException;

	/**
	 * Método que gera o resumo dos Registro de Atendimento
	 * 
	 * [UC0567] - Gerar Resumo Registro Atendimento
	 * 
	 * @author Thiago Tenório
	 * @date 30/04/2007
	 * 
	 */
	public void gerarResumoRegistroAtendimento(int idLocalidade, int idFuncionalidadeIniciada, Integer anoMesReferencia) throws ControladorException;

	/**
	 * [UC0459] Informar Dados da Agencia Reguladora
	 *
	 * @author Kássia Albuquerque
	 * @date 09/04/2007
	 */

	public Integer informarDadosAgenciaReguladora(RaDadosAgenciaReguladora raDadosAgenciaReguladora, Collection collectionRaDadosAgenciaReguladoraFone,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * Procura a quantidade de dias de prazo
	 * 
	 * [UC0459] Informar Dados da Agencia Reguladora
	 * 
	 * @author Kássia Albuquerque
	 * @date 19/04/2007
	 * 
	 */

	public Integer procurarDiasPazo(Integer raId) throws ControladorException;

	/**
	 * Permite comandar um encerramento de RA
	 * 
	 * [UC0735] Comandar Encerramento de Registros de Atendimento
	 * 
	 * @author Rafael Corrêa
	 * @param usuarioLogado
	 * @date 28/01/2008
	 * 
	 */
	public Integer comandarEncerramentoRA(RaEncerramentoComando raEncerramentoComando, Collection<SolicitacaoTipoEspecificacao> colecaoEspecificacoes,
			Usuario usuarioLogado) throws ControladorException;

	public void executarComandoEncerramentoRA(Collection<RaEncerramentoComando> raEncerramentoComandos, int idFuncionalidadeIniciada, Integer idLocalidade)
			throws ControladorException;

	public String obterTelefoneSolicitanteRA(Integer idRASolicitante) throws ControladorException;

	public BigDecimal[] calcularValorPrestacaoAtendimentoPublico(short indicadorCobrarJuros, BigDecimal valorDebito, Integer qtdeParcelas,
			String percentualCobranca) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void tramitarRAExportandoOSPrestadoras(Tramite tramite, Date dataConcorrenciaRA, Usuario usuario, Collection colecaoOrdemServicoPavimento,
			Collection colecaoOrdemServicoMovimento) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void tramitarConjuntoRAExportandoOSPrestadoras(Collection tramites, Collection colecaoOrdemServicoPavimento, Collection colecaoOrdemServicoMovimento)
			throws ControladorException;

	public Collection<Integer> pesquisarIdsLocalidadesRA() throws ControladorException;

	public Integer obterUnidadeOrigemDoUltimoTramiteRA(Integer idRA) throws ControladorException;

	public RegistroAtendimento verificarExistenciaRegistroAtendimentoSemLevantarExcecao(Integer idImovel, char codigoConstante) throws ControladorException;

	public List<GestaoRegistroAtendimentoHelper> filtrarGestaoRA(FiltrarGestaoRAHelper filtro) throws ControladorException;

	public List<GestaoRegistroAtendimentoHelper> filtrarRelatorioResumoSolicitacoesRAPorUnidade(FiltrarRelatorioResumoSolicitacoesRAPorUnidadeHelper filtro)
			throws ControladorException;

	public void validarRegistroAtendimentoAnexos(FileItem arquivoAnexo) throws ControladorException;

	public String obterProtocoloAtendimento() throws ControladorException;

	public void validarConjuntoTramitacaoIntegracao(String[] ids, Date dataHoraTramitacao, Integer idUnidadeDestino, Usuario usuario)
			throws ControladorException;

	public UnidadeOrganizacional verificarTramiteGrandesConsumidores(Integer idImovel) throws ControladorException;

	public void gerarResumoRegistroAtendimentoPorAno(int idLocalidade, int idFuncionalidadeIniciada, Integer anoMesReferencia) throws ControladorException;

	public UnidadeOrganizacional verificaUnidadeAnteriorRA(Integer idRegistroAtendimento) throws ControladorException;

	public Integer verificaSolicitacaoTipoEspecificacaoRA(Integer idImovel) throws ControladorException;

	public boolean existeRegistroAtendimentoConta(Integer idConta, Integer idRA) throws ControladorException;

	public ContaMotivoRetificacao pesquisaContaMotivoRetificacao(Integer idMotivo) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterRegistroAtendimento(RegistroAtendimentoDevolucaoValoresHelper helper, Integer numeroPagina) throws ControladorException;

	public Integer obterQtdeRegistroAtendimento(RegistroAtendimentoDevolucaoValoresHelper helper) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisaDadosRegistroAtendimentoPagamentoDuplicidade(Integer idRA) throws ControladorException;

	public void atualizarRegistroAtendimentoPagamentoDuplicidade(Integer idRa, Integer idPagamento) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection transferirDevolucaoValoresPagosDuplicidade(Collection colecaoContaASerRetificada, Collection colecaoCreditoARealizar,
			Collection colecaoCreditoASerTransferido, Collection colecaoPagamento, SistemaParametro sistemaParametro, Usuario usuarioLogado,
			RegistroAtendimento ra, Integer idImovel) throws ControladorException;

	public UnidadeOrganizacional definirUnidadeDestinoSituacaoCobranca(Integer idEspecificacao, Integer idImovel) throws ControladorException;

	public void verificarAlteracaoVencimentoRecente(Integer idEspecificacao, Usuario usuarioLogado, Integer idImovel) throws ControladorException;

	public RegistroAtendimento pesquisarDadosRegistroAtendimentoParaReiteracao(Integer idRegistroAtendimento) throws ControladorException;

	public void verificarExistenciaClienteSolicitanteDataAtual(Integer idRegistroAtendimento, Integer idCliente, String nomeCliente)
			throws ControladorException;

	public void verificarExistenciaUnidadeSolicitanteDataAtual(Integer idRegistroAtendimento, Integer idUnidade, String nomeUnidade)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosReiteracaoRA(Integer idRegistroAtendimento) throws ControladorException;

	public Short pesquisarQtdeReiteracaoRA(Integer idRegistroAtendimento) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosReiteracao(Integer idRA) throws ControladorException;

	public void inserirRASituacaoLigacaoImovel(Integer idMeioSolicitacao, Integer idSolicitacaoTipoEspecificacao, Integer idImovel,
			Integer idUnidadeAtendimento, Integer idUsuarioLogado, Integer idCliente) throws ControladorException;

	public RegistroAtendimentoSolicitante pesquisarDadosEnvioEmailPesquisaPortal(int idRegistroAtendimentoSolicitante) throws ControladorException;

	public int registrarQuestionarioSatisfacaoCliente(QuestionarioSatisfacaoCliente questionario) throws ControladorException;

	public boolean verificaExistenciaQuestionarioSatisfacaoRespondido(Integer idRA) throws ControladorException;

	public void atualizarDadosRA(Integer idRa, String descricaoPontoreferencia, String numeroImovel) throws ControladorException;

	public List<RAPorUnidadePorUsuarioHelper> filtrarRelatorioRegistroAtendimentoPorUnidadePorUsuario(
			FiltrarRelatorioRegistroAtendimentoPorUnidadePorUsuarioHelper filtro) throws ControladorException;
	
	public boolean existeRAAbertaPorSoliticacao(Integer idImovel, Integer idSolicitacao) throws ControladorException;
}
