package gcom.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.bean.RegistroAtendimentoDevolucaoValoresHelper;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.bean.FiltrarGestaoRAHelper;
import gcom.atendimentopublico.registroatendimento.bean.FiltrarRegistroAtendimentoHelper;
import gcom.atendimentopublico.registroatendimento.bean.FiltrarRelatorioRegistroAtendimentoPorUnidadePorUsuarioHelper;
import gcom.atendimentopublico.registroatendimento.bean.FiltrarRelatorioResumoSolicitacoesRAPorUnidadeHelper;
import gcom.atendimentopublico.registroatendimento.bean.GestaoRegistroAtendimentoHelper;
import gcom.atendimentopublico.registroatendimento.bean.RAPorUnidadePorUsuarioHelper;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.operacional.DivisaoEsgoto;
import gcom.util.ErroRepositorioException;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface IRepositorioRegistroAtendimento {

	public RegistroAtendimento verificarExistenciaRAImovelMesmaEspecificacao(Integer idImovel, Integer idSolicitacaoTipoEspecificacao)
			throws ErroRepositorioException;

	public Tramite recuperarTramiteMaisAtualPorRA(Integer idRA) throws ErroRepositorioException;

	public Integer verificaExistenciaRAPendenteImovel(Integer idImovel) throws ErroRepositorioException;

	public Collection<Integer> recuperaRAPorUnidadeAtendimento(UnidadeOrganizacional unidadeOrganizacional) throws ErroRepositorioException;

	public Collection<Integer> recuperaRAPorUnidadeAtual(UnidadeOrganizacional unidadeOrganizacional) throws ErroRepositorioException;

	public Collection<RegistroAtendimento> filtrarRA(FiltrarRegistroAtendimentoHelper filtroRA) throws ErroRepositorioException;

	public UnidadeOrganizacional definirUnidadeDestinoLocalidade(Integer idLocalidade, Integer idSolicitacaoTipo) throws ErroRepositorioException;

	public DivisaoEsgoto obterDivisaoEsgoto(Integer idQuadra) throws ErroRepositorioException;

	public DivisaoEsgoto verificarCompatibilidadeDivisaoEsgotoQuadra(Integer idQuadra, Integer idDivisaoEsgoto) throws ErroRepositorioException;

	public DivisaoEsgoto verificarCompatibilidadeDivisaoEsgotoSetor(Integer idSetorComercial, Integer idDivisaoEsgoto) throws ErroRepositorioException;

	public DivisaoEsgoto verificarCompatibilidadeDivisaoEsgotoLocalidade(Integer idLocalidade, Integer idDivisaoEsgoto) throws ErroRepositorioException;

	public UnidadeOrganizacional definirUnidadeDestinoDivisaoEsgoto(Integer idDivisaoEsgoto) throws ErroRepositorioException;

	public Integer verificarServicoTipoReferencia(Integer idServicoTipo) throws ErroRepositorioException;

	public Integer verificarExistenciaDescricaoTipoSolicitacao(String descricaoTipoSolicitacao) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection verificarSituacaoImovelEspecificacao(Integer idSolicitacaoTipoEspecificacao) throws ErroRepositorioException;

	public UnidadeOrganizacional verificaUnidadeAtualRA(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public Integer verificaUnidadeSuperiorUnidade(Integer idUnidade) throws ErroRepositorioException;

	public Short verificaSituacaoRA(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public UnidadeOrganizacional verificaUnidadeAtendimentoRA(Integer idRegistroAtendimento, Integer idAtendimentoRelacaoTipo) throws ErroRepositorioException;

	public Object[] verificaEnderecoOcorrenciaRA(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public Object[] verificaEnderecoRASolicitante(Integer idRegistroAtendimentoSolicitante) throws ErroRepositorioException;

	public Object[] verificaDuplicidadeRegistroAtendimentoConsultarRA(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public Object[] verificaRegistroAtendimentoReativadoConsultarRA(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public Object[] verificaRegistroAtendimentoReativacaoConsultarRA(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public RegistroAtendimento verificaDuplicidadeRegistroAtendimento(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public RegistroAtendimento verificaRegistroAtendimentoReativado(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public RegistroAtendimento verificaRegistroAtendimentoReativacao(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public Object[] pesquisarRegistroAtendimentoSolicitante(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public Object[] pesquisarUnidadeOrganizacionalUsuario(Integer idUsuario) throws ErroRepositorioException;

	public Object[] pesquisarParmsRegistroAtendimento(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public Integer verificarOrdemServicoParaRA(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public Collection<Tramite> obterTramitesRA(Integer idRA) throws ErroRepositorioException;

	public Collection<OrdemServico> obterOSRA(Integer idRA) throws ErroRepositorioException;

	public Object[] verificarParmsRA(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public Integer verificarExistenciaOrdemServicoProgramacaoRA(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public Object[] pesquisarHidrometroImovel(Integer idImovel) throws ErroRepositorioException;

	public Collection<RegistroAtendimentoSolicitante> obterRASolicitante(Integer idRA) throws ErroRepositorioException;

	public Short pesquisarCdSituacaoRegistroAtendimento(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public Integer pesquisarImovelDescritivo(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection verificaExistenciaRAPendenteLocalOcorrencia(Integer idSolicitacaoTipoEspecificacao, Integer idLogradouroCep, Integer idLogradouroBairro)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarRAPendenteLocalOcorrencia(Integer idSolicitacaoTipoEspecificacao, Integer idLogradouroCep, Integer idLogradouroBairro)
			throws ErroRepositorioException;

	public Object[] pesquisarDadosRegistroAtendimento(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public Object[] pesquisarDadosRASolicitante(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public Object[] pesquisarIndicadorFaltaAguaRA(Integer idEspecificacao) throws ErroRepositorioException;

	public Integer verificarOcorrenciaAbastecimentoProgramacao(Date dataAbastecimentoRA, Integer idBairroArea, Integer idBairro)
			throws ErroRepositorioException;

	public String pesquisarNomeBairroArea(Integer idBairroArea) throws ErroRepositorioException;

	public Integer verificarOcorrenciaManutencaoProgramacao(Date dataAbastecimentoRA, Integer idBairroArea, Integer idBairro) throws ErroRepositorioException;

	public Integer pesquisarIdAtendimentoEncerramentoMotivo() throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarRAAreaBairro(Integer idRegistroAtendimento, Integer idBairroArea, Integer idEspecificacao) throws ErroRepositorioException;

	public Integer pesquisarRAAreaBairroFaltaAguaImovel(Integer idBairroArea) throws ErroRepositorioException;

	public String descricaoSolTipoEspecAguaGeneralizada(Integer idSolicitacaoTipoEspecificacao) throws ErroRepositorioException;

	public String descricaoSolTipoEspecAguaImovel(Integer idSolicitacaoTipoEspecificacao) throws ErroRepositorioException;

	public Object[] pesquisarParmsRAFaltaAguaImovel(Integer idRegistroAtendimento) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection<ServicoTipo> filtrarST(ServicoTipo servicoTipo, Collection colecaoAtividades, Collection colecaoMaterial, String valorServicoInicial,
			String valorServicoFinal, String tempoMedioExecucaoInicial, String tempoMedioExecucaoFinal) throws ErroRepositorioException;

	public Date verificarConcorrenciaRA(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public Integer verificarExistenciaClienteSolicitante(Integer idRegistroAtendimento, Integer idCliente) throws ErroRepositorioException;

	public RegistroAtendimento verificarExistenciaRAAtualizarImovelMesmaEspecificacao(Integer idImovel, Integer idRA, Integer idSolicitacaoTipoEspecificacao)
			throws ErroRepositorioException;

	public Integer verificarExistenciaUnidadeSolicitante(Integer idRegistroAtendimento, Integer idUnidade) throws ErroRepositorioException;

	public RegistroAtendimento pesquisarRegistroAtendimento(Integer id) throws ErroRepositorioException;

	public Date obterMaiorDataEncerramentoOSRegistroAtendimento(Integer idRegistroAtendimento) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarRAAreaBairroInserir(Integer idBairroArea, Integer idEspecificacao) throws ErroRepositorioException;

	public void encerrarRegistroAtendimento(RegistroAtendimento registroAtendimento) throws ErroRepositorioException;

	public RegistroAtendimentoUnidade pesquisarRAUnidade(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public void removerSolicitanteFone(Integer idRegistroAtendimentoSolicitante) throws ErroRepositorioException;

	public UnidadeOrganizacional obterUnidadeDestinoEspecificacao(Integer especificacao) throws ErroRepositorioException;

	public RegistroAtendimentoSolicitante obterRegistroAtendimentoSolicitante(Integer idRaSolicitante) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarFoneSolicitante(Integer idRaSolicitante) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarTipoEspecificacaoFaltaAguaGeneralizada() throws ErroRepositorioException;

	public Tramite recuperarTramiteMaisAtualPorUnidadeDestino(Integer idUnidade) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarParmsFoneRegistroAtendimentoSolicitante(Integer idRASolicitante) throws ErroRepositorioException;

	public Integer verificarExistenciaClienteSolicitanteAtualizar(Integer idRegistroAtendimento, Integer idCliente, Integer idRegistroAtendimentoSolicitante)
			throws ErroRepositorioException;

	public Integer verificarExistenciaUnidadeSolicitanteAtualizar(Integer idRegistroAtendimento, Integer idUnidade, Integer idRASolicitante)
			throws ErroRepositorioException;

	public Date obterDataAgenciaReguladoraPrevisaoAtual(Integer idRa) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarRegistroAtendimentoImovel(Integer idImovel, String situacao) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarRAFaltaAguaGeneralizada(Integer idBairroArea) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarRAEncerradoImovel(Integer idImovel, Integer idEspecificacao, Date dataReativacao) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarRAEncerradoLocalOcorrencia(Integer idLogradouroBairro, Integer idLogradouroCep, Integer idEspecificacao, Date dataReativacao)
			throws ErroRepositorioException;

	public Object[] pesquisarDadosRegistroAtendimentoSolicitanteRelatorioOS(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public Object[] pesquisarSolicitanteFonePrincipal(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public String descricaoSolTipoEspecFaltaAguaGeneralizada() throws ErroRepositorioException;

	public Object[] obterLogradouroBairroImovelRegistroAtendimento(Integer idRA) throws ErroRepositorioException;

	public Object[] obterEnderecoDescritivoRA(Integer idRA) throws ErroRepositorioException;

	public Integer verificaNumeracaoRAManualInformada(Integer ultimoRAManual) throws ErroRepositorioException;

	public RegistroAtendimento pesquisarRegistroAtendimentoTarifaSocial(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public Integer verificarMesmaRA(Integer idImovel, Integer idRA) throws ErroRepositorioException;

	public Short verificarIndicadorTarifaSocialRA(Integer idRA) throws ErroRepositorioException;

	public Short verificarIndicadorTarifaSocialUsuario(Integer idUsuario) throws ErroRepositorioException;

	public String pesquisarDescricaoSolicitacaoTipoEspecificacao(Integer idSolicitacaoTipoEspecificacao) throws ErroRepositorioException;

	public void atualizarLogradouroBairro(LogradouroBairro logradouroBairroAntigo, LogradouroBairro logradouroBairroNovo) throws ErroRepositorioException;

	public void atualizarLogradouroCep(LogradouroCep logradouroCepAntigo, LogradouroCep logradouroCepNovo) throws ErroRepositorioException;

	public void atualizarLogradouroBairroSolicitante(LogradouroBairro logradouroBairroAntigo, LogradouroBairro logradouroBairroNovo)
			throws ErroRepositorioException;

	public void atualizarLogradouroCepSolicitante(LogradouroCep logradouroCepAntigo, LogradouroCep logradouroCepNovo) throws ErroRepositorioException;

	public Integer filtrarRATamanho(FiltrarRegistroAtendimentoHelper filtroRA) throws ErroRepositorioException;

	public Tramite pesquisarUltimaDataTramite(Integer idRA) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarObservacaoRegistroAtendimento(Integer matriculaImovel, Date dataInicialAtendimento, Date dataFinalAtendimento)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection getImoveisResumoRegistroAtendimento(int idLocalidade, Integer anoMesReferencia, Integer dtAtual) throws ErroRepositorioException;

	public Integer pesquisarUnidadeCentralizadoraRa(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public Integer procurarDiasPazo(Integer raId) throws ErroRepositorioException;

	public Integer pesquisaUnidadeSolicitacaoRa(Integer idRa) throws ErroRepositorioException;

	public Integer pesquisaUnidadeEncerradaRa(Integer idRa) throws ErroRepositorioException;

	public Integer pesquisarQuantidadeOSEmAbertoAssociadasRA(Integer idRA) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarRAExecutarComandoEncerramento(RaEncerramentoComando raEncerramentoComando, Integer idLocalidade,
			Collection<Integer> idsEspecificacoes) throws ErroRepositorioException;

	public Object[] pesquisarTipoSolicitanteRA(Integer idRASolicitante) throws ErroRepositorioException;

	public Object[] pesquisarTelefoneSolicitanteRACliente(Integer idCliente) throws ErroRepositorioException;

	public Object[] pesquisarTelefoneSolicitanteRASolicitante(Integer idRASolicitante) throws ErroRepositorioException;

	public Collection<Integer> pesquisarIdsUnidadesSubordinadas(Integer idUnidadeSuperior) throws ErroRepositorioException;

	public void atualizarUnidadeOrganizacionalAtualRA(Integer idUnidadeOrganizacionalAtual, Integer idRA) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarRAExecutarComandoEncerramento(RaEncerramentoComando raEncerramentoComando, Integer idUnidadeAtual,
			Integer idLocalidade, Collection<Integer> idsEspecificacoes) throws ErroRepositorioException;

	public Collection<Integer> pesquisarIdsLocalidadesRA() throws ErroRepositorioException;

	public Collection<Integer> pesquisarIdsEspecificacoesRAEncerramentoComando(Integer idRaEncerramentoComando) throws ErroRepositorioException;

	public Integer obterUnidadeOrigemDoUltimoTramiteRA(Integer idRA) throws ErroRepositorioException;

	public List<GestaoRegistroAtendimentoHelper> filtrarGestaoRA(FiltrarGestaoRAHelper filtro) throws ErroRepositorioException;

	public List<GestaoRegistroAtendimentoHelper> filtrarRelatorioResumoSolicitacoesRAPorUnidade(FiltrarRelatorioResumoSolicitacoesRAPorUnidadeHelper filtro)
			throws ErroRepositorioException;

	public Integer pesquisarSequencialProtocoloAtendimento() throws ErroRepositorioException;

	public String pesquisarProtocoloAtendimentoPorRA(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public Collection<Integer> pesquisarPossiveisUnidadesCentralizadorasRa(Integer idRegistroAtendimento) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection getImoveisResumoRegistroAtendimentoPorAno(int idLocalidade, Integer anoMesReferencia, Integer dtAtual) throws ErroRepositorioException;

	public UnidadeOrganizacional verificaUnidadeAnteriorRA(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public Integer verificaSolicitacaoTipoEspecificacaoRA(Integer idImovel) throws ErroRepositorioException;

	public RegistroAtendimentoConta pesquisaRegistroAtendimentoConta(Integer idConta, Integer idRA) throws ErroRepositorioException;

	public ContaMotivoRetificacao pesquisaContaMotivoRetificacao(Integer idMotivo) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterRegistroAtendimento(RegistroAtendimentoDevolucaoValoresHelper helper, Integer numeroPagina) throws ErroRepositorioException;

	public Integer obterQtdeRegistroAtendimento(RegistroAtendimentoDevolucaoValoresHelper helper) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisaDadosRegistroAtendimentoPagamentoDuplicidade(Integer idRA) throws ErroRepositorioException;

	public void atualizarRegistroAtendimentoPagamentoDuplicidade(Integer idRa, Integer idPagamento) throws ErroRepositorioException;

	public Object[] pesquisarUnidadeDestinoEspecificacaoRA(Integer idEspecificacao, Integer idImovel) throws ErroRepositorioException;

	public RegistroAtendimento pesquisarDadosRegistroAtendimentoParaReiteracao(Integer idRegistroAtendimento) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarUsuarioVisualizacaoRaUrgencia(Integer idRegistroAtendimento) throws ErroRepositorioException;

	public Integer verificarExistenciaClienteSolicitanteDataAtual(Integer idRegistroAtendimento, Integer idCliente) throws ErroRepositorioException;

	public Integer verificarExistenciaUnidadeSolicitanteDataAtual(Integer idRegistroAtendimento, Integer idUnidade) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarRAReiteracao(Integer idRegistroAtendimento) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarRAReiteracaoFone(Integer idRAReiteracao) throws ErroRepositorioException;

	public Short pesquisarQtdeReiteracaoRA(Integer idRegistroAtendimento) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosReiteracao(Integer idRA) throws ErroRepositorioException;

	public RegistroAtendimentoSolicitante pesquisarDadosEnvioEmailPesquisaPortal(int idRegistroAtendimentoSolicitante) throws ErroRepositorioException;

	public boolean verificaExistenciaQuestionarioSatisfacaoRespondido(Integer idRA) throws ErroRepositorioException;

	public void atualizarDadosRA(Integer idRa, String descricaoPontoreferencia, String numeroImovel) throws ErroRepositorioException;

	public List<RAPorUnidadePorUsuarioHelper> filtrarRelatorioRegistroAtendimentoPorUnidadePorUsuario(
			FiltrarRelatorioRegistroAtendimentoPorUnidadePorUsuarioHelper filtro) throws ErroRepositorioException;

	public boolean existeRAAbertaPorSoliticacao(Integer idImovel, Integer idSolicitacao) throws ErroRepositorioException;
}
