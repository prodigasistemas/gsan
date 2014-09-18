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
import gcom.relatorio.atendimentopublico.RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface IRepositorioRegistroAtendimento {

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso exista registro de atendimento pendente para o imóvel com a mesma
	 * especificação (existe ocorrência na tabela REGISTRO_ATENDIMENTO com
	 * IMOV_ID=Matrícula do Imóvel e STEP_ID=Id da Especificação selecionada e
	 * RGAT_CDSITUACAO=1).
	 * 
	 * [FS0020] - Verificar existência de registro de atendimento para o imóvel
	 * com a mesma especificação
	 * 
	 * @author Raphael Rossiter
	 * @date 31/07/2006
	 * 
	 * @param idImovel,
	 *            idSolicitacaoTipoEspecificacao
	 * @return RegistroAtendimento
	 * @throws ControladorException
	 */
	public RegistroAtendimento verificarExistenciaRAImovelMesmaEspecificacao(
			Integer idImovel, Integer idSolicitacaoTipoEspecificacao)
			throws ErroRepositorioException;

	/**
	 * [UC0424] Consultar Registro Atendimento
	 * 
	 * Retorno o Tramite mais atual a partir
	 * 
	 * @author Rafael Pinto
	 * @date 10/08/2006
	 * 
	 * @param idRA
	 * @return Tramite
	 * @throws ErroRepositorioException
	 */
	public Tramite recuperarTramiteMaisAtualPorRA(Integer idRA)
			throws ErroRepositorioException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso exista registro de atendimento pendente para o imóvel (existe
	 * ocorrência na tabela REGISTRO_ATENDIMENTO com IMOV_ID=Matrícula do Imóvel
	 * e RGAT_CDSITUACAO=1)
	 * 
	 * [SB0021] - Verifica Existência de Registro de Atendimento Pendente para o
	 * Imóvel
	 * 
	 * @author Raphael Rossiter
	 * @date 31/07/2006
	 * 
	 * @param idImovel
	 * @return quantidadeRA
	 * @throws ControladorException
	 */
	public Integer verificaExistenciaRAPendenteImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0406] Filtrar Registro de Atendimento
	 * 
	 * Caso exista registro de atendimento abertos para a unidade de atendimento
	 * informada (existe ocorrência na tabela REGISTRO_ATENDIMENTO com
	 * REGISTRO_ATENDIMENTO_UNIDADE=Id da Unidade de Atendimento e ATTP_ID=1 -
	 * ABRIR/REGISTRAR)
	 * 
	 * @author Leonardo Regis
	 * @date 07/08/2006
	 * 
	 * @param unidadeOrganizacional
	 * @return Collection<RegistroAtendimento>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperaRAPorUnidadeAtendimento(
			UnidadeOrganizacional unidadeOrganizacional)
			throws ErroRepositorioException;

	/**
	 * [UC0406] Filtrar Registro de Atendimento
	 * 
	 * Caso exista registro de atendimento que estão na unidade atual informada
	 * (existe ocorrência na tabela REGISTRO_ATENDIMENTO com TRAMITE=Id da
	 * Unidade Atual e maior TRAM_TMTRAMITE)
	 * 
	 * @author Leonardo Regis
	 * @date 07/08/2006
	 * 
	 * @param unidadeOrganizacional
	 * @return Collection<RegistroAtendimento>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperaRAPorUnidadeAtual(
			UnidadeOrganizacional unidadeOrganizacional)
			throws ErroRepositorioException;

	/**
	 * [UC0406] Filtrar Registro de Atendimento
	 * 
	 * [SB004] Selecionar Registro de Atendimento por Município [SB005]
	 * Selecionar Registro de Atendimento por Bairro [SB006] Selecionar Registro
	 * de Atendimento por Logradouro
	 * 
	 * @author Leonardo Regis
	 * @date 07/08/2006
	 * 
	 * @param filtroRA
	 * @return Collection<RegistroAtendimento>
	 * @throws ErroRepositorioException
	 */
	public Collection<RegistroAtendimento> filtrarRA(FiltrarRegistroAtendimentoHelper filtroRA) throws ErroRepositorioException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Definir a unidade destino a partir da localidade informada/selecionada
	 * (UNID_ID e UNID_DSUNIDADE da tabela UNIDADE_ORGANIZACIONAL com
	 * UNID_ICTRAMITE=1 e UNID_ID=UNID_ID da tabela LOCALIDADE_SOLIC_TIPO_GRUPO
	 * com LOCA_ID=Id da Localidade e SOTG_ID=SOTG_ID da tabela SOLICITACAO_TIPO
	 * com SOTP_ID=Id do Tipo de Solicitação selecionado).
	 * 
	 * [SB0005] - Define Unidade Destino da Localidade
	 * 
	 * @author Raphael Rossiter
	 * @date 04/08/2006
	 * 
	 * @param idLocalidade,
	 *            idSolicitacaoTipo
	 * @return UnidadeDestino
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional definirUnidadeDestinoLocalidade(
			Integer idLocalidade, Integer idSolicitacaoTipo)
			throws ErroRepositorioException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso o Tipo de Solicitação seja relativo à área de esgoto (SOTG_ICESGOTO
	 * da tabela SOLICITACAO_TIPO_GRUPO com o valor correspondente a um para
	 * SOTG_ID=SOTG_ID da tabela SOLICITACAO_TIPO com SOTP_ID=Id do Tipo de
	 * Solicitação selecionado). Caso a quadra esteja preenchida, obter a
	 * divisão de esgoto da quadra (DVES_ID e DVES_DSDIVISAOESGOTO da tabela
	 * DIVISAO_ESGOTO com DVES_ID=DVES_ID da tabela SISTEMA_ESGOTO com
	 * SESG_ID=SESG_ID da tabela BACIA com BACI_ID=BACI_ID da tabela QUADRA com
	 * QDRA_ID=Id da quadra informada/selecionada).
	 * 
	 * [SB0006] - Obtém Divisão de Esgoto
	 * 
	 * @author Raphael Rossiter
	 * @date 07/08/2006
	 * 
	 * @param solicitacaoTipoRelativoAreaEsgoto,
	 *            idQuadra
	 * @return UnidadeOrganizacional
	 * @throws ErroRepositorioException
	 */
	public DivisaoEsgoto obterDivisaoEsgoto(Integer idQuadra)
			throws ErroRepositorioException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso tenha informado a quadra e a mesma não pertença a divisão de esgoto
	 * informada (Id da divisão de esgoto é diferente de DVES_ID da tabela
	 * QUADRA com QDRA_ID=Id da quadra informada)
	 * 
	 * [SB0006] - Obtém Divisão de Esgoto
	 * 
	 * @author Raphael Rossiter
	 * @date 08/08/2006
	 * 
	 * @param idQuadra,
	 *            idDivisaoEsgoto
	 * @return DivisaoEsgoto
	 * @throws ErroRepositorioException
	 */
	public DivisaoEsgoto verificarCompatibilidadeDivisaoEsgotoQuadra(
			Integer idQuadra, Integer idDivisaoEsgoto)
			throws ErroRepositorioException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso tenha informado o setor comercial sem a quadra e o setor comercial
	 * não pertença à divisão de esgoto informada (Id da divisão de esgoto é
	 * diferente de todos os DVES_ID da tabela QUADRA com STCM_ID=Id do setor
	 * comercial informado).
	 * 
	 * [SB0006] - Obtém Divisão de Esgoto
	 * 
	 * @author Raphael Rossiter
	 * @date 08/08/2006
	 * 
	 * @param idSetorComercial,
	 *            idDivisaoEsgoto
	 * @return DivisaoEsgoto
	 * @throws ErroRepositorioException
	 */
	public DivisaoEsgoto verificarCompatibilidadeDivisaoEsgotoSetor(
			Integer idSetorComercial, Integer idDivisaoEsgoto)
			throws ErroRepositorioException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso tenha informado a localidade sem o setor comercial e a localidade
	 * não pertença à divisão de esgoto informada (Id da divisão de esgoto é
	 * diferente de todos os DVES_ID da tabela QUADRA com STCM_ID=STCM_ID da
	 * tabela SETOR_COMERCIAL com LOCA_ID=Id da localidade informada).
	 * 
	 * [SB0006] - Obtém Divisão de Esgoto
	 * 
	 * @author Raphael Rossiter
	 * @date 07/08/2006
	 * 
	 * @param idLocalidade,
	 *            idDivisaoEsgoto
	 * @return DivisaoEsgoto
	 * @throws ErroRepositorioException
	 */
	public DivisaoEsgoto verificarCompatibilidadeDivisaoEsgotoLocalidade(
			Integer idLocalidade, Integer idDivisaoEsgoto)
			throws ErroRepositorioException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Definir a unidade destino a partir da divisão de esgoto
	 * informada/selecionada (UNID_ID e UNID_DSUNIDADE da tabela
	 * UNIDADE_ORGANIZACIONAL com UNID_ICTRAMITE=1 e UNID_ID=UNID_ID da tabela
	 * DIVISAO_ESGOTO com DVES_ID=Id da divisão selecionada)
	 * 
	 * [SB0007] - Define Unidade Destino da Divisão de Esgoto
	 * 
	 * @author Raphael Rossiter
	 * @date 08/08/2006
	 * 
	 * @param idDivisaoEsgoto
	 * @return UnidadeDestino
	 * @throws ErroRepositorioException
	 */
	public UnidadeOrganizacional definirUnidadeDestinoDivisaoEsgoto(
			Integer idDivisaoEsgoto) throws ErroRepositorioException;

	/**
	 * [UC0399] Inserir Tipo de Solicitação com Especificações
	 * 
	 * Verifica se o serviço tipo tem como sreviço automatico geração
	 * automática.
	 * 
	 * [SF0003] - Validar Tipo de Serviço
	 * 
	 * @author Sávio Luiz
	 * @date 08/08/2006
	 * 
	 * @param idServicoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificarServicoTipoReferencia(Integer idServicoTipo)
			throws ErroRepositorioException;

	/**
	 * [UC0399] Inserir Tipo de Solicitação com Especificações
	 * 
	 * Verificar descrição do tipo de solicitação com especificação e indicador
	 * uso ativo se já inserido na base .
	 * 
	 * [SF0001] - Verificar existencia da descrição
	 * 
	 * @author Sávio Luiz
	 * @date 08/08/2006
	 * 
	 * @param idServicoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaDescricaoTipoSolicitacao(
			String descricaoTipoSolicitacao) throws ErroRepositorioException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0020] - Verifica Situação do Imóvel e Especificação
	 * 
	 * @author Raphael Rossiter
	 * @date 07/08/2006
	 * 
	 * @param idSolicitacaoTipoEspecificacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection verificarSituacaoImovelEspecificacao(
			Integer idSolicitacaoTipoEspecificacao)
			throws ErroRepositorioException;

	/**
	 * [UC0418] Obter Unidade Atual do RA
	 * 
	 * Pesquisar a unidade atual do registro de atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 04/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional verificaUnidadeAtualRA(
			Integer idRegistroAtendimento) throws ErroRepositorioException;

	/**
	 * [UC0419] Obter Indicador de Autorização para Manutenção do RA
	 * 
	 * Verifica a unidade superior da unidade do RA
	 * 
	 * @author Ana Maria
	 * @date 08/08/2006
	 * 
	 * 
	 * @param idUnidade
	 * @throws ControladorException
	 */
	public Integer verificaUnidadeSuperiorUnidade(Integer idUnidade)
			throws ErroRepositorioException;

	/**
	 * [UC0420] Obter Descrição da situação da RA
	 * 
	 * Pesquisar a situação(RGAT_CDSITUACAO) do registro de atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 03/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Short verificaSituacaoRA(Integer idRegistroAtendimento)
			throws ErroRepositorioException;

	/**
	 * [UC0421] Obter Unidade de Atendimento do RA ou [UC0434] Obter Unidade de
	 * Encerramento do RA
	 * 
	 * Verifica a unidade de atendimento do registro de atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 04/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional verificaUnidadeAtendimentoRA(
			Integer idRegistroAtendimento, Integer idAtendimentoRelacaoTipo)
			throws ErroRepositorioException;

	/**
	 * [UC0422] Obter Endereço da Ocorrência do RA
	 * 
	 * Verifica existência do logradouro(lgbr_id) e do imovel no registro de
	 * atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 07/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] verificaEnderecoOcorrenciaRA(Integer idRegistroAtendimento)
			throws ErroRepositorioException;

	/**
	 * [UC0423] Obter Endereço do Solicitante do RA
	 * 
	 * Verifica existência do logradouro(lgbr_id) e do cliente no registro de
	 * atendimento solicitante
	 * 
	 * 
	 * @author Ana Maria
	 * @date 07/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] verificaEnderecoRASolicitante(
			Integer idRegistroAtendimentoSolicitante)
			throws ErroRepositorioException;
	
	/**
	 * [UC0433] Obter Registro de Atendimento Associado
	 * 
	 * Verifica duplicidade no registro de atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 08/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] verificaDuplicidadeRegistroAtendimentoConsultarRA(
			Integer idRegistroAtendimento) throws ErroRepositorioException;
	
	/**
	 * [UC0433] Obter Registro de Atendimento Associado
	 * 
	 * Verifica se o registro de atendimento foi reativado
	 * 
	 * 
	 * @author Ana Maria
	 * @date 08/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] verificaRegistroAtendimentoReativadoConsultarRA(
			Integer idRegistroAtendimento) throws ErroRepositorioException;
	
	/**
	 * [UC0433] Obter Registro de Atendimento Associado
	 * 
	 * Verifica se o registro de atendimento é reativação de outro
	 * 
	 * 
	 * @author Ana Maria
	 * @date 08/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] verificaRegistroAtendimentoReativacaoConsultarRA(
			Integer idRegistroAtendimento) throws ErroRepositorioException;

	/**
	 * [UC0433] Obter Registro de Atendimento Associado
	 * 
	 * Verifica duplicidade no registro de atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 08/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public RegistroAtendimento verificaDuplicidadeRegistroAtendimento(
			Integer idRegistroAtendimento) throws ErroRepositorioException;

	/**
	 * [UC0433] Obter Registro de Atendimento Associado
	 * 
	 * Verifica se o registro de atendimento foi reativado
	 * 
	 * 
	 * @author Ana Maria
	 * @date 08/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public RegistroAtendimento verificaRegistroAtendimentoReativado(
			Integer idRegistroAtendimento) throws ErroRepositorioException;

	/**
	 * [UC0433] Obter Registro de Atendimento Associado
	 * 
	 * Verifica se o registro de atendimento é reativação de outro
	 * 
	 * 
	 * @author Ana Maria
	 * @date 08/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public RegistroAtendimento verificaRegistroAtendimentoReativacao(
			Integer idRegistroAtendimento) throws ErroRepositorioException;

	/**
	 * [UC0445] Obter Nome do Solicitante do RA
	 * 
	 * Pesquisa o registro de atendimento solicitante
	 * 
	 * 
	 * @author Ana Maria, Raphael Rossiter
	 * @date 09/08/2006, 07/02/2008
	 * 
	 * 
	 * @param idRegistroAtendimentoSolicitante
	 * @throws ControladorException
	 */
	public Object[] pesquisarRegistroAtendimentoSolicitante(
			Integer idRegistroAtendimento)
			throws ErroRepositorioException;

	/**
	 * [UC0419] Obter Indicador de Autorização para Manutenção do RA
	 * 
	 * Pesquisa unidade organizacional do usúario
	 * 
	 * 
	 * @author Ana Maria
	 * @date 09/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimentoSolicitante
	 * @throws ControladorException
	 */
	public Object[] pesquisarUnidadeOrganizacionalUsuario(Integer idUsuario)
			throws ErroRepositorioException;

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * Pesquisar os parametros para atualizar o registro atendimento escolhido
	 * pelo usuário
	 * 
	 * @author Sávio Luiz
	 * @date 11/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] pesquisarParmsRegistroAtendimento(
			Integer idRegistroAtendimento) throws ErroRepositorioException;

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * Verificar existencia ordem de serviço para o registro atendimento
	 * pesquisado
	 * 
	 * @author Sávio Luiz
	 * @date 11/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Integer verificarOrdemServicoParaRA(Integer idRegistroAtendimento)
			throws ErroRepositorioException;

	/**
	 * [UC0446] Consultar Trâmites
	 * 
	 * Retorna a Coleção de Tramites do registro de atendimento
	 * 
	 * @author Leonardo Regis
	 * @date 11/08/2006
	 * 
	 * @param idRA
	 * @return Collection<Tramite>
	 * @throws ControladorException
	 */
	public Collection<Tramite> obterTramitesRA(Integer idRA)
			throws ErroRepositorioException;

	/**
	 * [UC0431] Consultar Ordens de Serviço do Registro Atendimento
	 * 
	 * Retorna a Coleção de OS's do registro de atendimento
	 * 
	 * @author Leonardo Regis
	 * @date 11/08/2006
	 * 
	 * @param idRA
	 * @return Collection<OrdemServico>
	 * @throws ControladorException
	 */
	public Collection<OrdemServico> obterOSRA(Integer idRA)
			throws ErroRepositorioException;

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * verificar duplicidade do registro de atendimento e código situação
	 * 
	 * [FS0012] - Verificar possibilidade de atualização do registro de
	 * atendimento
	 * 
	 * @author Sávio Luiz
	 * @date 14/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] verificarParmsRA(Integer idRegistroAtendimento)
			throws ErroRepositorioException;

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * verificar existencai da ordem de servico programação para o RA
	 * 
	 * [FS0012] - Verificar possibilidade de atualização do registro de
	 * atendimento
	 * 
	 * @author Sávio Luiz
	 * @date 14/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Integer verificarExistenciaOrdemServicoProgramacaoRA(
			Integer idRegistroAtendimento) throws ErroRepositorioException;

	/**
	 * [UC0409] Obter Indicador de Existência de Hidrômetro na Ligaçaõ de Água e
	 * no Poço
	 * 
	 * Pesquisar a situação e o indicador de existência de hidrômetro na ligação
	 * de água e no poço
	 * 
	 * @author Ana Maria
	 * @date 09/08/2006
	 * 
	 * 
	 * @param idImovel
	 * @throws ControladorException
	 */
	public Object[] pesquisarHidrometroImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0447] Consultar RA Solicitantes
	 * 
	 * Retorna a Coleção de registro de atendimento solicitantes
	 * 
	 * @author Rafael Pinto
	 * @date 14/08/2006
	 * 
	 * @param idRA
	 * @return Collection<RegistroAtendimentoSolicitante>
	 * @throws ControladorException
	 */
	public Collection<RegistroAtendimentoSolicitante> obterRASolicitante(
			Integer idRA) throws ErroRepositorioException;

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * Pesquisar os parametros para saber como está a situacao do registro de
	 * antendimento
	 * 
	 * @author Sávio Luiz
	 * @date 15/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Short pesquisarCdSituacaoRegistroAtendimento(
			Integer idRegistroAtendimento) throws ErroRepositorioException;

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * Pesquisar se o imóvel é descritivo
	 * 
	 * @author Sávio Luiz
	 * @date 15/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Integer pesquisarImovelDescritivo(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * REGISTRO_ATENDIMENTO com IMOV_ID=nulo e RGAT_CDSITUACAO=1 e
	 * LGBR_ID=LGBR_ID do endereço da ocorrência e LGCP_ID=LGCP_ID do endereço
	 * da ocorrência e STEP_ID=Id da Especificação selecionada e STEP_ID=STEP_ID
	 * da tabela SOLICITACAO_TIPO_ESPECIFICACAO com SOTP_ID=SOTP_ID da tabela
	 * SOLICITACAO_TIPO para SOTP_ICFALTAAGUA com o valor correspondente a dois
	 * 
	 * [SB0008] - Verifica Existência de Registro de Atendimento Pendente para o
	 * Local da Ocorrência
	 * 
	 * @author Raphael Rossiter
	 * @date 15/08/2006
	 * 
	 * @param idSolicitacaoTipoEspecificacao,
	 *            idLogradouroCep, idLogradouroBairro
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection verificaExistenciaRAPendenteLocalOcorrencia(
			Integer idSolicitacaoTipoEspecificacao, Integer idLogradouroCep,
			Integer idLogradouroBairro) throws ErroRepositorioException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * REGISTRO_ATENDIMENTO com IMOV_ID=nulo e RGAT_CDSITUACAO=1 e
	 * LGBR_ID=LGBR_ID do endereço da ocorrência e LGCP_ID=LGCP_ID do endereço
	 * da ocorrência e STEP_ID=Id da Especificação selecionada e STEP_ID=STEP_ID
	 * da tabela SOLICITACAO_TIPO_ESPECIFICACAO com SOTP_ID=SOTP_ID da tabela
	 * SOLICITACAO_TIPO para SOTP_ICFALTAAGUA com o valor correspondente a dois
	 * 
	 * @author Raphael Rossiter
	 * @date 16/08/2006
	 * 
	 * @param idSolicitacaoTipoEspecificacao,
	 *            idLogradouroCep, idLogradouroBairro
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRAPendenteLocalOcorrencia(
			Integer idSolicitacaoTipoEspecificacao, Integer idLogradouroCep,
			Integer idLogradouroBairro) throws ErroRepositorioException;

	/**
	 * [UC0452] Obter Dados do Registro Atendimento
	 * 
	 * Pesquisar dados do registro de atendimento
	 * 
	 * @author Ana Maria
	 * @date 14/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] pesquisarDadosRegistroAtendimento(
			Integer idRegistroAtendimento) throws ErroRepositorioException;

	/**
	 * [UC0452] Obter Dados do Registro Atendimento
	 * 
	 * Pesquisar dados do registro de atendimento solicitante
	 * 
	 * @author Ana Maria
	 * @date 15/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] pesquisarDadosRASolicitante(Integer idRegistroAtendimento)
			throws ErroRepositorioException;

	/**
	 * [UC0452] Obter Dados do Registro Atendimento
	 * 
	 * Pesquisar Indicador falta de agua da RA
	 * 
	 * @author Sávio Luiz
	 * @date 16/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] pesquisarIndicadorFaltaAguaRA(Integer idEspecificacao)
			throws ErroRepositorioException;

	/**
	 * [UC0452] Obter Dados do Registro Atendimento
	 * 
	 * [SB0018] Verificar Programação de Abastecimento e/ou Manutenção
	 * 
	 * @author Sávio Luiz
	 * @date 16/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Integer verificarOcorrenciaAbastecimentoProgramacao(
			Date dataAbastecimentoRA, Integer idBairroArea, Integer idBairro)
			throws ErroRepositorioException;

	/**
	 * [UC0452] Obter Dados do Registro Atendimento
	 * 
	 * [SB0018] Verificar Programação de Abastecimento e/ou Manutenção
	 * 
	 * @author Sávio Luiz
	 * @date 16/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public String pesquisarNomeBairroArea(Integer idBairroArea)
			throws ErroRepositorioException;

	/**
	 * [UC0452] Obter Dados do Registro Atendimento
	 * 
	 * [SB0018] Verificar Programação de Abastecimento e/ou Manutenção
	 * 
	 * @author Sávio Luiz
	 * @date 16/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Integer verificarOcorrenciaManutencaoProgramacao(
			Date dataAbastecimentoRA, Integer idBairroArea, Integer idBairro)
			throws ErroRepositorioException;

	/**
	 * [UC0408] Atualizar Registro Atendimento
	 * 
	 * Pesquisar id do AtendimentoEncerramentoMotivo
	 * 
	 * @author Sávio Luiz
	 * @date 16/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Integer pesquisarIdAtendimentoEncerramentoMotivo()
			throws ErroRepositorioException;

	/**
	 * [UC0408] Atualizar Registro Atendimento
	 * 
	 * Pesquisar Indicador falta de agua da RA
	 * 
	 * @author Sávio Luiz
	 * @date 16/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Collection pesquisarRAAreaBairro(Integer idRegistroAtendimento,
			Integer idBairroArea, Integer idEspecificacao)
			throws ErroRepositorioException;
	
	
	/**
	 * [UC0408] Atualizar Registro Atendimento
	 * 
	 * Pesquisar o id do registro atendimento para a area bairro especifica
	 * 
	 * @author Sávio Luiz
	 * @date 16/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Integer pesquisarRAAreaBairroFaltaAguaImovel(Integer idBairroArea)
			throws ErroRepositorioException;

	/**
	 * [UC0408] Atualizar Registro Atendimento
	 * 
	 * Pesquisar a descrição da solicitacao do tipo de especificação
	 * 
	 * @author Sávio Luiz
	 * @date 16/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public String descricaoSolTipoEspecAguaGeneralizada(
			Integer idSolicitacaoTipoEspecificacao)
			throws ErroRepositorioException;

	/**
	 * [UC0408] Atualizar Registro Atendimento
	 * 
	 * Pesquisar a descrição da solicitacao do tipo de especificação
	 * 
	 * @author Sávio Luiz
	 * @date 16/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public String descricaoSolTipoEspecAguaImovel(
			Integer idSolicitacaoTipoEspecificacao)
			throws ErroRepositorioException;

	/**
	 * [UC0408] Atualizar Registro Atendimento
	 * 
	 * Pesquisar parms registro atendimento e jogando o objeto helper
	 * 
	 * @author Sávio Luiz
	 * @date 16/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] pesquisarParmsRAFaltaAguaImovel(
			Integer idRegistroAtendimento) throws ErroRepositorioException;

	/**
	 * [UC0413] Pesquisar Tipo de Serviço
	 * 
	 * select a.svtp_id from ATENDIMENTOPUBLICO.SERVICO_TIPO A,
	 * ATENDIMENTOPUBLICO.SERVICO_TIPO_ATIVIDADE B,
	 * ATENDIMENTOPUBLICO.SERVICO_TIPO_MATERIAL C WHERE A.SVTP_DSSERVICOTIPO
	 * LIKE '%DESC%' AND A.SVTP_DSABREVIADO LIKE '%DESC%' AND (A.SVTP_VLSERVICO >=
	 * 000000 AND A.SVTP_VLSERVICO <= 99999) AND A.SVTP_ICPAVIMENTO = 1 OU 2 and
	 * A.SVTP_ICATUALIZACOMERCIAL = 1 OU 2 AND A.SVTP_ICTERCEIRIZADO = 1 OU 2
	 * AND A.SVTP_CDSERVICOTIPO = ("O" OR "C") AND (A.SVTP_NNTEMPOMEDIOEXECUCAO >=
	 * 0000 AND A.SVTP_NNTEMPOMEDIOEXECUCAO <= 9999) AND DBTP_ID = ID INFORMADO
	 * AND AND CRTP_ID = ID INFORMADO AND STSG_ID = ID INFORMADO AND STRF_ID =
	 * ID INFORMADO AND STPR_ID = ID INFORMADO AND A.SVTP_ID = B.SVTP_ID AND
	 * B.ATIV_ID IN (ID's INFORMADOS) AND A.SVTP_ID = C.SVTP_ID AND C.MATE_ID IN
	 * (ID's INFORMADOS)
	 * 
	 * @author Leandro Cavalcanti
	 * @date 17/08/2006
	 * 
	 */
	public Collection<ServicoTipo> filtrarST(ServicoTipo servicoTipo,
			Collection colecaoAtividades, Collection colecaoMaterial,
			String valorServicoInicial, String valorServicoFinal,
			String tempoMedioExecucaoInicial, String tempoMedioExecucaoFinal)
			throws ErroRepositorioException;

	/**
	 * [UC0427] Tramitar Registro Atendimento
	 * 
	 * [FS009] Atualização realizada por outro usuário
	 * 
	 * @author Leonardo Regis
	 * @date 22/08/2006
	 * 
	 * @param idRegistroAtendimento
	 * @param dataAtual
	 * @throws ErroRepositorioException
	 */
	public Date verificarConcorrenciaRA(Integer idRegistroAtendimento)
			throws ErroRepositorioException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso esteja adicionando um novo solicitante e o cliente já seja um
	 * solicitante do registro de atendimento (existe ocorrência na tabela
	 * REGISTRO_ATENDIMENTO_SOLICITANTE com RGAT_ID=RGAT_ID do registro de
	 * atendimento selecionado e CLIE_ID=Id do Cliente informado).
	 * 
	 * [FS0012] - Verificar existência do cliente solicitante
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2006
	 * 
	 * @param idRegistroAtendimento,
	 *            idCliente
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaClienteSolicitante(
			Integer idRegistroAtendimento, Integer idCliente)
			throws ErroRepositorioException;

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * Caso exista registro de atendimento pendente para o imóvel com a mesma
	 * especificação (existe ocorrência na tabela REGISTRO_ATENDIMENTO com
	 * IMOV_ID=Matrícula do Imóvel e STEP_ID=Id da Especificação selecionada e
	 * RGAT_CDSITUACAO=1 e RA_ID<> ID da RA que está sendo atualizado).
	 * 
	 * [FS0020] - Verificar existência de registro de atendimento para o imóvel
	 * com a mesma especificação
	 * 
	 * @author Sávio Luiz
	 * @date 21/08/2006
	 * 
	 * @param idImovel,
	 *            idSolicitacaoTipoEspecificacao
	 * @return RegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	public RegistroAtendimento verificarExistenciaRAAtualizarImovelMesmaEspecificacao(
			Integer idImovel, Integer idRA,
			Integer idSolicitacaoTipoEspecificacao)
			throws ErroRepositorioException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso esteja adicionando um novo solicitante e a unidade já seja um
	 * solicitante do registro de atendimento (existe ocorrência na tabela
	 * REGISTRO_ATENDIMENTO_SOLICITANTE com RGAT_ID=RGAT_ID do registro de
	 * atendimento selecionado e UNID_ID=Id da Unidade informada).
	 * 
	 * [FS0026] - Verificar existência da unidade solicitante
	 * 
	 * @author Raphael Rossiter
	 * @date 23/08/2006
	 * 
	 * @param idRegistroAtendimento,
	 *            idUnidade
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaUnidadeSolicitante(
			Integer idRegistroAtendimento, Integer idUnidade)
			throws ErroRepositorioException;

	/**
	 * [UC0430] Gerar Ordem de Serviço
	 * 
	 * @author lms
	 * @date 17/08/2006
	 */
	public RegistroAtendimento pesquisarRegistroAtendimento(Integer id)
			throws ErroRepositorioException;

	/**
	 * [UC0435] Encerrar Registro de Atendimento
	 * 
	 * Verificar maior data de encerramento das ordens de serviço de um RA
	 * 
	 * @author Leonardo Regis
	 * @date 26/08/2006
	 * 
	 * @param idRegistroAtendimento
	 * @return Maior Data de Encerramento da OS de um RA
	 * @throws ControladorException
	 */
	public Date obterMaiorDataEncerramentoOSRegistroAtendimento(
			Integer idRegistroAtendimento) throws ErroRepositorioException;

	/**
	 * [UC0366] Inserir Registro Atendimento
	 * 
	 * Pesquisar Indicador falta de agua da RA
	 * 
	 * @author Raphael Rossiter
	 * @date 16/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRAAreaBairroInserir(Integer idBairroArea,
			Integer idEspecificacao) throws ErroRepositorioException;

	/**
	 * [UC0435] Encerrar Registro de Atendimento
	 * 
	 * Permite encerrar o ra atualizando a tabela REGISTRO_ATENDIMENTO
	 * 
	 * @author Leonardo Regis
	 * @date 29/08/2006
	 * 
	 * @param registroAtendimento
	 */
	public void encerrarRegistroAtendimento(
			RegistroAtendimento registroAtendimento)
			throws ErroRepositorioException;

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * pesquisar o registro atendimento unidade com o id do registro atendimento
	 * e o id do atendimento relação tipo igual a abrir/registrar
	 * 
	 * @author Sávio Luiz
	 * @date 30/08/2006
	 * 
	 * @param idRegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	public RegistroAtendimentoUnidade pesquisarRAUnidade(
			Integer idRegistroAtendimento) throws ErroRepositorioException;

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * pesquisar o registro atendimento unidade com o id do registro atendimento
	 * e o id do atendimento relação tipo igual a abrir/registrar
	 * 
	 * @author Sávio Luiz
	 * @date 30/08/2006
	 * 
	 * @param idRegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	public void removerSolicitanteFone(Integer idRegistroAtendimentoSolicitante)
			throws ErroRepositorioException;
	
    /**
	 * [UC0426] Reativar Registro de Atendimento
	 * 
	 *  
	 * @author Ana Maria
	 * @date 24/08/2006
	 * 
	 * @param especificacao
	 * @return UnidadeOrganizacional
	 * @throws ControladorException
	 */
    public UnidadeOrganizacional obterUnidadeDestinoEspecificacao(Integer especificacao)
			throws ErroRepositorioException;
    
    /**
	 * [UC0426] Reativar Registro de Atendimento
	 * 
	 *  
	 * @author Ana Maria
	 * @date 24/08/2006
	 * 
	 * @param idRaSolicitante
	 * @return RegistroAtendimentoSolicitante
	 * @throws ControladorException
	 */
    public RegistroAtendimentoSolicitante obterRegistroAtendimentoSolicitante(Integer idRaSolicitante)
			throws ErroRepositorioException;
    
    /**
	 * [UC0426] Reativar Registro de Atendimento
	 * 
	 *  
	 * @author Ana Maria
	 * @date 29/08/2006
	 * 
	 * @param idRaSolicitante
	 * @return RegistroAtendimentoSolicitante
	 * @throws ControladorException
	 */
	public Collection pesquisarFoneSolicitante(Integer idRaSolicitante)
			throws ErroRepositorioException;
	
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * SOTP_ICFALTAAGUA da tabela SOLICITACAO_TIPO com o valor correspondente a um e STEP_ICMATRICULA com 
	 * o valor correspondente a dois na tabela SOLICITACAO_TIPO_ESPECIFICACAO).
	 *  
	 * @author Raphael Rossiter	
	 * @date 29/08/2006
	 * 
	 * @return idEspecificacao, idTipo
	 * @throws ControladorException
	 */
	public Collection pesquisarTipoEspecificacaoFaltaAguaGeneralizada()
			throws ErroRepositorioException ;
	
	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 06/09/2006
	 */	
	public Tramite recuperarTramiteMaisAtualPorUnidadeDestino(Integer idUnidade)
		throws ErroRepositorioException;	
		
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * pesquisa os fones do regsitro atendimento solicitante
	 *  
	 * @author Sávio Luiz
	 * @date 05/09/2006
	 * 
	 * @return idRASolicitante
	 * @throws ControladorException
	 */
	public Collection pesquisarParmsFoneRegistroAtendimentoSolicitante(Integer idRASolicitante)
			throws ErroRepositorioException ;

	/**
	 * [UC0408] Inserir Registro de Atendimento
	 * 
	 * Caso esteja adicionando um novo solicitante e o cliente já seja um
	 * solicitante do registro de atendimento (existe ocorrência na tabela
	 * REGISTRO_ATENDIMENTO_SOLICITANTE com RGAT_ID=RGAT_ID do registro de
	 * atendimento selecionado e CLIE_ID=Id do Cliente informado e o registro
	 * atendimento solicitante for diferente do que está sendo atualizado).
	 * 
	 * [FS0027] - Verificar existência do cliente solicitante
	 * 
	 * @author Sávo Luiz
	 * @date 21/08/2006
	 * 
	 * @param idRegistroAtendimento,
	 *            idCliente
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaClienteSolicitanteAtualizar(
			Integer idRegistroAtendimento, Integer idCliente,
			Integer idRegistroAtendimentoSolicitante)
			throws ErroRepositorioException; 
	
	/**
	 * [UC0408] Inserir Registro de Atendimento
	 * 
	 * Caso esteja adicionando um novo solicitante e a unidade já seja um
	 * solicitante do registro de atendimento (existe ocorrência na tabela
	 * REGISTRO_ATENDIMENTO_SOLICITANTE com RGAT_ID=RGAT_ID do registro de
	 * atendimento selecionado e UNID_ID=Id da Unidade informada e RASO_ID<>id
	 * do Registro atendimento solicitante).
	 * 
	 * [FS0018] - Verificar existência da unidade solicitante
	 * 
	 * @author Sávio Luiz
	 * @date 07/09/2006
	 * 
	 * @param idRegistroAtendimento,
	 *            idUnidade
	 * @throws ControladorException
	 */
	public Integer verificarExistenciaUnidadeSolicitanteAtualizar(
			Integer idRegistroAtendimento, Integer idUnidade,
			Integer idRASolicitante) throws ErroRepositorioException;
	
    /**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 *  
	 * @author Rafael Pinto
	 * @date 13/09/2006
	 * 
	 * @param idRa
	 * @return dataPrevisaoAtual
	 * @throws ErroRepositorioException
	 */
    public Date obterDataAgenciaReguladoraPrevisaoAtual(Integer idRa)
			throws ErroRepositorioException;

	/**
	 * Consultar os registros de atendimento do Imovel 
	 * [UC0472]  Consultar Imovel
	 * 
	 * @author Rafael Santos
	 * @date 25/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection consultarRegistroAtendimentoImovel(Integer idImovel,String situacao)
			throws ErroRepositorioException;	
    
    
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * REGISTRO_ATENDIMENTO com RGAT_CDSITUACAO=1 e BRAR_ID=Id da Área do Bairro selecionada e STEP_ID=STEP_ID da 
	 * tabela SOLICITACAO_TIPO_ESPECIFICACAO com STEP_ICMATRICULA com o valor correspondente a um e SOTP_ID=SOTP_ID 
	 * da tabela SOLICITACAO_TIPO para SOTP_ICFALTAAGUA com o valor correspondente a um.
	 * 
	 * @author Raphael Rossiter
	 * @date 16/08/2006
	 * 
	 * @param idBairroArea
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRAFaltaAguaGeneralizada(Integer idBairroArea) throws ErroRepositorioException ;
	
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 *  
	 * [SB0015] - Verifica Registro de Atendimento de Água Generalizada
	 * 
	 * @author Raphael Rossiter
	 * @date 16/08/2006
	 * 
	 * @param idImovel, idEspecificacao, dataReativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRAEncerradoImovel(Integer idImovel, Integer idEspecificacao, Date dataReativacao) 
		throws ErroRepositorioException ;
	
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 *  
	 * [SB0015] - Verifica Registro de Atendimento de Água Generalizada
	 * 
	 * @author Raphael Rossiter
	 * @date 16/08/2006
	 * 
	 * @param idLogradouroBairro, idLogradouroCep, idEspecificacao, dataReativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRAEncerradoLocalOcorrencia(Integer idLogradouroBairro, Integer idLogradouroCep, 
			Integer idEspecificacao, Date dataReativacao) 
		throws ErroRepositorioException;
	
	/**
	 * Pesquisa os dados do Registro Atendimento Solicitante para o Relatório de OS
	 * 
	 * @author Rafael Corrêa
	 * @date 17/10/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosRegistroAtendimentoSolicitanteRelatorioOS(
			Integer idRegistroAtendimento) throws ErroRepositorioException;
	
	/**
	 * Pesquisa o telefone principal do Registro Atendimento Solicitante para o
	 * Relatório de OS
	 * 
	 * @author Rafael Corrêa
	 * @date 17/10/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	
	public Object[] pesquisarSolicitanteFonePrincipal(
			Integer idRegistroAtendimento) throws ErroRepositorioException;

	
	/**
	 * [UC0408] Atualizar Registro Atendimento
	 * 
	 * Pesquisar a descrição da solicitacao do tipo de especificação
	 * 
	 * @author Sávio Luiz
	 * @date 16/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public String descricaoSolTipoEspecFaltaAguaGeneralizada()
			throws ErroRepositorioException;
	
	/**
	 * [UC0482] - Obter Endereço Abreviado da Ocorrência do RA
	 * 
	 * Pesquisa os dados necessários do RA para verificar como o endereço será obtido
	 * 
	 * @author Rafael Corrêa
	 * @date 18/10/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	
	public Object[] obterLogradouroBairroImovelRegistroAtendimento(
			Integer idRA) throws ErroRepositorioException;
	
	/**
	 * [UC0482] - Obter Endereço Abreviado da Ocorrência do RA
	 * 
	 * Pesquisa o Endereco Descritivo do RA
	 * 
	 * @author Rafael Corrêa
	 * @date 18/10/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	
	public Object[] obterEnderecoDescritivoRA(
			Integer idRA) throws ErroRepositorioException;
	
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [FS0042] - Verifica Número informado
	 * Imóvel
	 * 
	 * @author Raphael Rossiter
	 * @date 07/11/2006
	 * 
	 * @param ultimoRAManual
	 * @return quantidadeRA
	 * @throws ErroRepositorioException
	 */
	public Integer verificaNumeracaoRAManualInformada(Integer ultimoRAManual)
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Pesquisa o registro de atendimento fazendo os carregamentos necessários
	 * 
	 * @author Rafael Corrêa
	 * @date 03/01/2007
	 * 
	 * @param idRegistroAtendimento
	 * @return registroAtendimento
	 * @throws ErroRepositorioException
	 */
	public RegistroAtendimento pesquisarRegistroAtendimentoTarifaSocial(
			Integer idRegistroAtendimento) throws ErroRepositorioException;
	
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 12/01/2007
	 * 
	 * @param idImovel,
	 *            idSolicitacaoTipoEspecificacao
	 * @return RegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	public Integer verificarMesmaRA(
			Integer idImovel, Integer idRA)
			throws ErroRepositorioException;
	
	/**
	 * [UC00503]Tramitar Conjunto de Registro de Atendimento
	 * 
	 * @author Ana Maria
	 * @date 16/01/2007
	 * 
	 */
	public Short verificarIndicadorTarifaSocialRA(Integer idRA)
			throws ErroRepositorioException;	
	
	/**
	 * [UC00503]Tramitar Conjunto de Registro de Atendimento
	 * 
	 * @author Ana Maria
	 * @date 16/01/2007
	 * 
	 */
	public Short verificarIndicadorTarifaSocialUsuario(Integer idUsuario)
			throws ErroRepositorioException;	
	
	
	/**
	 * [UC0366] Inserir Registro Atendimento
	 * 
	 * @author Raphael Rossiter	
	 * @date 09/02/2007
	 * 
	 * @param idSolicitacaoTipoEspecificacao
	 * @throws ControladorException
	 */
	public String pesquisarDescricaoSolicitacaoTipoEspecificacao(Integer idSolicitacaoTipoEspecificacao)
			throws ErroRepositorioException;
	
	
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
	public void atualizarLogradouroBairro(LogradouroBairro logradouroBairroAntigo, 
			LogradouroBairro logradouroBairroNovo) throws ErroRepositorioException ;
	
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
	public void atualizarLogradouroCep(LogradouroCep logradouroCepAntigo, 
			LogradouroCep logradouroCepNovo) throws ErroRepositorioException ;
	
	
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
	public void atualizarLogradouroBairroSolicitante(LogradouroBairro logradouroBairroAntigo, 
			LogradouroBairro logradouroBairroNovo) throws ErroRepositorioException ;
	
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
	public void atualizarLogradouroCepSolicitante(LogradouroCep logradouroCepAntigo, 
			LogradouroCep logradouroCepNovo) throws ErroRepositorioException ;
	
	/**
	 * [UC0406] Filtrar Registro de Atendimento
	 * 
	 * [SB004] Selecionar Registro de Atendimento por Município [SB005]
	 * Selecionar Registro de Atendimento por Bairro [SB006] Selecionar Registro
	 * de Atendimento por Logradouro
	 * 
	 * @author Leonardo Regis
	 * @date 07/08/2006
	 * 
	 * @param filtroRA
	 * @return Collection<RegistroAtendimento>
	 * @throws ErroRepositorioException
	 */
	public Integer filtrarRATamanho(
			FiltrarRegistroAtendimentoHelper filtroRA)
			throws ErroRepositorioException ;
	
	/**
	 * UC?? - ????????
	 * 
	 * @author Rômulo Aurélio Filho
	 * @date 20/03/2007
	 * @descricao O método retorna um objeto com a maior data de Tramite
	 *            
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	
	public Tramite pesquisarUltimaDataTramite(Integer idRA) throws ErroRepositorioException ;
	
	
	/**
	 * Consultar Observacao Registro Atendimento
	 * Solicitacao da CAER
	 * 
	 * @author Rafael Pinto
	 * @date 14/03/2007
	 */
	public Collection pesquisarObservacaoRegistroAtendimento(Integer matriculaImovel,
			Date dataInicialAtendimento,Date dataFinalAtendimento)
			throws ErroRepositorioException ;
	
	/**
	 * Método que retona uma lista de objeto xxxxx que representa o agrupamento
	 * id do imovel id gerencia regional id licalidade id do setor comercial
	 * codigo do setor comercial id rota id guradra numero da quadra id perfil
	 * do imovel id situacao da ligacao de agua id situacao da ligacao de esgoto
	 * principal categoria do imovel esfera de poder do cliente responsavel
	 * indicador de existencia de hidrometro
	 * 
	 * @author Thiago Tenório
	 * @date 30/04/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection getImoveisResumoRegistroAtendimento( 
			int idLocalidade, 
			Integer anoMesReferencia, 
			Integer dtAtual ) throws ErroRepositorioException;
	
	/**
	 * [UC0427] Tramitar Registro Atendimento
	 * 
	 * @author Ana Maria
	 * @date 03/05/2007
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Integer pesquisarUnidadeCentralizadoraRa(Integer idRegistroAtendimento) 
			throws ErroRepositorioException;	

	/**
	 * Procura a quantidade de dias de prazo  
	 * 
	 * [UC0459] Informar Dados da Agencia Reguladora
	 * 
	 * @author Kássia Albuquerque
	 * @date 19/04/2007
	 * 
	 */
	
	public Integer procurarDiasPazo(Integer raId) throws ErroRepositorioException;
	
	/**
  	 * Pesquisa a Unidade Solicitante de acordo com a RA  
  	 * 
  	 * @author Ivan Sérgio
  	 * @date 17/08/2007
  	 * 
  	 */
	public Integer pesquisaUnidadeSolicitacaoRa(Integer idRa) throws ErroRepositorioException;
	
	/**
  	 * Pesquisa a Unidade Encerramento de acordo com a RA  
  	 * 
  	 * @author Ivan Sérgio
  	 * @date 17/08/2007
  	 * 
  	 */
	public Integer pesquisaUnidadeEncerradaRa(Integer idRa) throws ErroRepositorioException;
	
	/**
	 * Pesquisa a quantidade de OS em aberto associadas a uma RA
	 *
	 * [UC0736] Executar Comando de Encerramento de Registros de Atendimento
	 *
	 * @author Rafael Corrêa
	 * @date 31/01/2008
	 *
	 * @param idRA
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeOSEmAbertoAssociadasRA(Integer idRA) throws ErroRepositorioException;
	
	/**
	 * Pesquisa os registros de atendimento de acordo com o comando solicitado
	 *
	 * [UC0736] Executar Comando de Encerramento de Registros de Atendimento
	 *
	 * @author Rafael Corrêa
	 * @date 31/01/2008
	 *
	 * @param raEncerramentoComando
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRAExecutarComandoEncerramento(
			RaEncerramentoComando raEncerramentoComando, Integer idLocalidade, Collection<Integer> idsEspecificacoes)
			throws ErroRepositorioException;

	
	/**
	 * Pesquisa os dados do telefone do solicitante do
	 * Registro de atendimento.
	 *
	 * [UC0719] Obter Telefone do Solicitante do RA
	 *
	 * @author Pedro Alexandre
	 * @date 26/12/2007
	 *
	 * @param idRegistroAtendimentoSolicitante
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarTipoSolicitanteRA(Integer idRASolicitante) throws ErroRepositorioException ;
	
	/**
	 * Obtém os dados do telefone do cliente solicitante da RA.
	 *
	 * [UC0719] Obter Telefone do Solicitante do RA
	 *
	 * @author Pedro Alexandre
	 * @date 26/12/2007
	 *
	 * @param idCliente
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarTelefoneSolicitanteRACliente(Integer idCliente) throws ErroRepositorioException ;

	/**
	 * Obtém os dados do telefone do solicitante da RA.
	 *
	 * [UC0719] Obter Telefone do Solicitante do RA
	 *
	 * @author Pedro Alexandre
	 * @date 26/12/2007
	 *
	 * @param idRASolicitante
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarTelefoneSolicitanteRASolicitante(Integer idRASolicitante) throws ErroRepositorioException ;
	
	/**
	 * [UC0736] Executar Comando de Encerramento de Registros de Atendimento
	 * 
	 * [SB0002 - Selecionar Registro de Atendimento por Unidade Superior]
	 * 
	 * Pesquisa a coleção de ids das unidades subordinadas a unidade superior informada
	 *
	 * @author Pedro Alexandre
	 * @date 16/06/2008
	 *
	 * @param idUnidadeSuperior
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsUnidadesSubordinadas(Integer idUnidadeSuperior) throws ErroRepositorioException ;

	/**
     * [UC0427] Tramitar Registro de Atendimento
     * 
     * @author Vivianne Sousa
     * @date 10/06/2008
     * 
     * @throws ControladorException
     */
    public void atualizarUnidadeOrganizacionalAtualRA(Integer idUnidadeOrganizacionalAtual,
            Integer idRA) throws ErroRepositorioException;
    
	/**
	 * Pesquisa os registros de atendimento de acordo com o comando solicitado
	 * e a unidade atual informada.
	 *
	 * [UC0736] Executar Comando de Encerramento de Registros de Atendimento
	 *
	 * @author Pedro Alexandre
	 * @date 16/06/2008
	 *
	 * @param raEncerramentoComando
	 * @param idUnidadeAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRAExecutarComandoEncerramento(RaEncerramentoComando raEncerramentoComando, Integer idUnidadeAtual, Integer idLocalidade, Collection<Integer> idsEspecificacoes) throws ErroRepositorioException ;

	
	/**
	 * [UC0736] Executar Comando de Encerramento de Registros de Atendimento
	 * 
	 * Pesquisa a coleção de ids das localidades que possuem Registro de Atendimento
	 *
	 * @author Pedro Alexandre
	 * @date 16/06/2008
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesRA() throws ErroRepositorioException ;
	
	/**
	 * [UC0736] Executar Comando de Encerramento de Registros de Atendimento
	 * 
	 * Pesquisa a coleção de ids das especificações que possuem Registro de Atendimento
	 *
	 * @author Rafael Corrêa
	 * @date 25/11/2008
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsEspecificacoesRAEncerramentoComando(Integer idRaEncerramentoComando) throws ErroRepositorioException;
    
    /**
     * [UC0457] Encerra Ordem de Serviço
     *
     * @author Vivianne Sousa
     * @date 11/07/2008
     *
     * @param idRA
     * @return
     * @throws ErroRepositorioException
     */
    public Integer obterUnidadeOrigemDoUltimoTramiteRA(Integer idRA) throws ErroRepositorioException;
    
    /**
     * [UC0496] Filtrar Relatorio de Gestao do Registro de atendimento
     * 
     * @author Victor Cisneiros
     * @date 20/06/2008
     */
    public List<GestaoRegistroAtendimentoHelper> filtrarGestaoRA(
    		FiltrarGestaoRAHelper filtro) throws ErroRepositorioException;
    
    /**
     * [UC0497] Gerar Relatorio Resumo de Solicitacoes de RA por Unidade
     * 
     * @author Victor Cisneiros
     * @date 14/11/2008
     */
    public List<GestaoRegistroAtendimentoHelper> filtrarRelatorioResumoSolicitacoesRAPorUnidade(
    		FiltrarRelatorioResumoSolicitacoesRAPorUnidadeHelper filtro) throws ErroRepositorioException;
    
    /**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 07/08/2009
	 * 
	 * @throws ErroRepositorioException
	 */
    public Integer pesquisarSequencialProtocoloAtendimento() throws ErroRepositorioException ;
    
    /**
	 * [UC0426] Reativar Registro de Atendimento
	 * 
	 * Pesquisar o número de protocolo do RA
	 * 
	 * @author Raphael Rossiter
	 * @date 07/08/2009
	 * 
	 * @param idRegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	public String pesquisarProtocoloAtendimentoPorRA(Integer idRegistroAtendimento) 
		throws ErroRepositorioException ;

	/**
	 * [UC0427] Tramitar Registro Atendimento
	 * 
	 * @author Francisco do Nascimento
	 * @date 09/12/2009
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarPossiveisUnidadesCentralizadorasRa(
			Integer idRegistroAtendimento) throws ErroRepositorioException;	
	
	/**
	 * Método que retona uma lista de objeto xxxxx que representa o agrupamento
	 * id do imovel id gerencia regional id licalidade id do setor comercial
	 * codigo do setor comercial id perfil
	 * do imovel id situacao da ligacao de agua id situacao da ligacao de esgoto
	 * principal categoria do imovel esfera de poder do cliente responsavel
	 * indicador de existencia de hidrometro
	 * 
	 * @author Fernando Fontelles
	 * @date 17/06/2010
	 * 
	 * @param idLocalidade
	 *            id da localida a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection getImoveisResumoRegistroAtendimentoPorAno(int idLocalidade,
			Integer anoMesReferencia, Integer dtAtual) throws ErroRepositorioException;
	
	/**
	 * [UC0424] Consultar Registro de Atendimento
	 * 
	 * verifica a unidade anterior do registro de atendimento pelo último trâmite
	 * efetuado
	 * 
	 * 
	 * @author Vivianne Sousa
	 * @date 04/02/2011
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional verificaUnidadeAnteriorRA(
			Integer idRegistroAtendimento) throws ErroRepositorioException;
	
	/**
	 * [UC0146] Manter Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 09/02/2011
	 * 
	 * @param idImovel
	 * @throws ControladorException
	 */
	public Integer verificaSolicitacaoTipoEspecificacaoRA(
			Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC0146] Manter Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 10/02/2011
	 * 
	 * @throws ControladorException
	 */
	public RegistroAtendimentoConta pesquisaRegistroAtendimentoConta(
			Integer idConta,Integer idRA) throws ErroRepositorioException;
	/**
	 * [UC0146] Manter Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 10/02/2011
	 * 
	 * @throws ControladorException
	 */
	public ContaMotivoRetificacao pesquisaContaMotivoRetificacao(
			Integer idMotivo) throws ErroRepositorioException;
	
	/**
	 * UC1130 – Filtrar Registro Atendimento de Devolução de Valores
	 * @author Vivianne Sousa
	 * @date 11/03/2011
	 */
	public Collection obterRegistroAtendimento(RegistroAtendimentoDevolucaoValoresHelper helper, Integer numeroPagina)throws ErroRepositorioException ;
	
	/**
	 * UC1130 – Filtrar Registro Atendimento de Devolução de Valores
	 * @author Vivianne Sousa
	 * @date 14/03/2011
	 */
	public Integer obterQtdeRegistroAtendimento(RegistroAtendimentoDevolucaoValoresHelper helper)throws ErroRepositorioException;
	
	/**
	 * [UC1131] Efetuar Devolução de Valores Pagos em Duplicidade
	 * [SB0001] – Pesquisar os pagamentos associados ao RA
	 * 
	 * @author Vivianne Sousa
	 * @date 15/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisaDadosRegistroAtendimentoPagamentoDuplicidade(
			Integer idRA) throws ErroRepositorioException;
	
	/**
	 * [UC1131] Efetuar Devolução de Valores Pagos em Duplicidade
	 * [SB0007] Atualiza Pagamento Devolvido
	 * 
	 * @author Vivianne Sousa
	 * @date 21/03/2011
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarRegistroAtendimentoPagamentoDuplicidade(
			Integer idRa,
			Integer idPagamento)
			throws ErroRepositorioException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0037] – Define Unidade Destino por Situação de Cobrança.
	 * 
	 * @author Mariana Victor
	 * @date 05/04/2011
	 * 
	 * @param idEspecificacao
	 * @param idImovel
	 * @return Collection
	 */
	public Object[] pesquisarUnidadeDestinoEspecificacaoRA(
			Integer idEspecificacao, Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC0425] Reiterar Registro de Atendimento
	 * 
	 * Pesquisar dados do registro de atendimento
	 * 
	 * @author Vivianne Sousa
	 * @date 10/05/2011
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public RegistroAtendimento pesquisarDadosRegistroAtendimentoParaReiteracao(
			Integer idRegistroAtendimento) throws ErroRepositorioException;
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 *  [SB0034] – Verificar RA de urgência
	 *  
	 * Pesquisar os Usuários da Unidade relacionada a RA, na tabela "VisualizacaoRaUrgencia" 
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ErroRepositorioException
	 * @data   03/06/2010 
	 * 
	 */
	public Collection pesquisarUsuarioVisualizacaoRaUrgencia(Integer idRegistroAtendimento)
		throws ErroRepositorioException;
	
	/**
	 * [UC0425] Reiterar Registro de Atendimento
	 * [FS0008] – Verificar reiteração do RA pelo cliente 
	 *  
	 * @author Vivianne Sousa
	 * @data 13/05/2011
	 * 
	 */
	public Integer verificarExistenciaClienteSolicitanteDataAtual(
			Integer idRegistroAtendimento, Integer idCliente)
			throws ErroRepositorioException;

	/**
	 * [UC0425] Reiterar Registro de Atendimento
	 * [FS0008] – Verificar reiteração do RA pelo cliente 
	 *  
	 * @author Vivianne Sousa
	 * @data 13/05/2011
	 * 
	 */
	public Integer verificarExistenciaUnidadeSolicitanteDataAtual(
			Integer idRegistroAtendimento, Integer idUnidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0424] Consultar Registro de Atendimento
	 *  
	 * @author Vivianne Sousa
	 * @data 16/05/2011
	 */
	public Collection pesquisarRAReiteracao(Integer idRegistroAtendimento)
			throws ErroRepositorioException;

	/**
	 * [UC0424] Consultar Registro de Atendimento
	 *  
	 * @author Vivianne Sousa
	 * @data 16/05/2011
	 */
	public Collection pesquisarRAReiteracaoFone(Integer idRAReiteracao)
			throws ErroRepositorioException;
	
	/**
	 * pesquisa quantidade de reiterações do RA
	 *  
	 * @author Vivianne Sousa
	 * @data 18/05/2011
	 */
	public Short pesquisarQtdeReiteracaoRA(Integer idRegistroAtendimento)
			throws ErroRepositorioException;
	
	/**
	 * [UC0428] Imprimir Registro Atendimento
	 * 
	 * @author Rodrigo Cabral
	 * @date 10/05/2011
	 */
	public Collection pesquisarDadosReiteracao(
			Integer idRA) throws ErroRepositorioException;
	
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * @author Paulo Diniz
	 * @date 22/06/2011
	 */
	public RegistroAtendimentoSolicitante pesquisarDadosEnvioEmailPesquisaPortal(int idRegistroAtendimentoSolicitante) 
		throws ErroRepositorioException; 
	
	/**
	 * [UC1181] Registrar Informacoes de Pesquisa de Satisfacao
	 * 
	 * @author Paulo Diniz
	 * @date 22/06/2011
	 */
	public boolean verificaExistenciaQuestionarioSatisfacaoRespondido(Integer idRA)
			throws ErroRepositorioException;
	
	/**
	 * [UC1227] Atualizar Ordens Serviço de Acompanhamento de Celular
	 * 
	 * @autor Sávio Luiz
	 * @date 22/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarDadosRA(
			Integer idRa,String descricaoPontoreferencia, String numeroImovel)
			throws ErroRepositorioException;
	
	 /**
     *
     * 
     * Gerar relatório de Registro de Atendimento por unidade por usuário
     * 
     * @author Wellington Rocha
     * @date 18/12/2012
     */
    public List<RAPorUnidadePorUsuarioHelper> filtrarRelatorioRegistroAtendimentoPorUnidadePorUsuario(
    		FiltrarRelatorioRegistroAtendimentoPorUnidadePorUsuarioHelper filtro) throws ErroRepositorioException;
}
