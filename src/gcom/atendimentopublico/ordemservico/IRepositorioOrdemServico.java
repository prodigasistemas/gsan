package gcom.atendimentopublico.ordemservico;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import gcom.atendimentopublico.ordemservico.bean.DadosAtualizacaoOSParaInstalacaoHidrometroHelper;
import gcom.atendimentopublico.ordemservico.bean.OSPavimentoHelper;
import gcom.atendimentopublico.ordemservico.bean.OrdemRepavimentacaoProcessoAceiteHelper;
import gcom.atendimentopublico.ordemservico.bean.PesquisarOrdemServicoHelper;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.operacional.DistritoOperacional;
import gcom.relatorio.atendimentopublico.bean.ImovelEmissaoOrdensSeletivasHelper;
import gcom.relatorio.atendimentopublico.ordemservico.FiltrarBoletimCustoPavimentoHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

public interface IRepositorioOrdemServico {

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
	 * @param PesquisarOrdemServicoHelper
	 * 
	 * @return Collection<OrdemServico>
	 * @throws ControladorException
	 */
	public Collection<OrdemServico> pesquisarOrdemServico(
			PesquisarOrdemServicoHelper filtro) throws ErroRepositorioException;

	/**
	 * [UC0454] Obter Descrição da situação da OS
	 * 
	 * Verificar a situação(ORSE_CDSITUACAO) da ordem de serviço
	 * 
	 * 
	 * @author Leonardo Regis
	 * @date 11/08/2006
	 * 
	 * 
	 * @param idOS
	 * @throws ControladorException
	 */
	public Short verificaSituacaoOS(Integer idOrdemServico)
			throws ErroRepositorioException;

	/**
	 * [UC0441] Consultar Dados da Ordem de Serviço
	 * 
	 * @author Leonardo Regis
	 * @date 15/08/2006
	 * 
	 * @param idOrdemServico
	 * @exception ErroRepositorioExceptions
	 */
	public OrdemServico consultarDadosOS(Integer idOrdemServico)
			throws ErroRepositorioException;

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
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaOSProgramada(Integer idOS)
			throws ErroRepositorioException;

	/**
	 * [UC0450] Pesquisar Ordem Serviço
	 * 
	 * Caso exista registro de atendimento abertos para a unidade de geracao
	 * informada (existe ocorrência na tabela ORDEM_SERVICO com
	 * ORDEM_SERVICO_UNIDADE=Id da Unidade de Atendimento e ATTP_ID=1 -
	 * ABRIR/REGISTRAR)
	 * 
	 * @author Rafael Pinto
	 * @date 19/08/2006
	 * 
	 * @param idUnidadeGeracao
	 * @return Collection<Integer>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperaOSPorUnidadeGeracao(
			Integer idUnidadeGeracao) throws ErroRepositorioException;

	/**
	 * [UC0450] Pesquisar Ordem Serviço
	 * 
	 * Caso exista registro de atendimento abertos para a unidade de geracao
	 * informada (existe ocorrência na tabela ORDEM_SERVICO com
	 * ORDEM_SERVICO_UNIDADE=Id da Unidade de Atendimento e ATTP_ID=1 -
	 * ABRIR/REGISTRAR)
	 * 
	 * @author Rafael Pinto
	 * @date 19/08/2006
	 * 
	 * @param idUnidadeGeracao
	 * @return Collection<Integer>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperaOSPorUnidadeAtual(Integer idUnidadeAtual)
			throws ErroRepositorioException;

	/**
	 * [UC0450] Pesquisar Ordem Serviço
	 * 
	 * Seleciona ordem de serviço por codigo do cliente atraves do RASolicitante
	 * 
	 * @author Rafael Pinto
	 * @date 19/08/2006
	 * 
	 * @param codigoCliente
	 * @return Collection<Integer>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperaOSPorCodigoClienteRASolicitante(
			Integer codigoCliente) throws ErroRepositorioException;

	/**
	 * [UC0450] Pesquisar Ordem Serviço
	 * 
	 * Seleciona ordem de serviço por codigo do cliente atraves do cliente
	 * imovel
	 * 
	 * @author Rafael Pinto
	 * @date 19/08/2006
	 * 
	 * @param codigoCliente
	 * @return Collection<Integer>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperaOSPorCodigoCliente(Integer codigoCliente, String origemOrdemServico)
			throws ErroRepositorioException;

	/**
	 * [UC0450] Pesquisar Ordem Serviço
	 * 
	 * Seleciona ordem de serviço por codigo do cliente atraves do documento
	 * cobrança
	 * 
	 * @author Rafael Pinto
	 * @date 19/08/2006
	 * 
	 * @param codigoCliente
	 * @return Collection<Integer>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperaOSPorCodigoClienteCobrancaDocumento(
			Integer codigoCliente) throws ErroRepositorioException;

	/**
	 * Pesquisar Atividades
	 * 
	 * Seleciona os atividades no array de servico tipo.
	 * 
	 * @author Leandro Cavalcanti
	 * @date 19/08/2006
	 * 
	 * @param Collection
	 *            <Integer>
	 * @return Collection<Integer>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperarAtividadesServicoTipo(
			Collection<Integer> atividades) throws ErroRepositorioException;

	/**
	 * Pesquisar Materiais
	 * 
	 * Seleciona os materiais no array de servico tipo.
	 * 
	 * @author Leandro Cavalcanti
	 * @date 19/08/2006
	 * 
	 * @param Collection
	 *            <Integer>
	 * @return Collection<Integer>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperarMaterialServicoTipo(
			Collection<Integer> materiais) throws ErroRepositorioException;

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
			String tempoMedioExecucaoInicial, String tempoMedioExecucaoFinal,
			String tipoPesquisa, String tipoPesquisaAbreviada,
			Integer numeroPaginasPesquisa) throws ErroRepositorioException;

	/**
	 * [UC0413] Pesquisar Tipo de Serviço
	 * 
	 * Faz o count para saber q quantidade total de registros retornados
	 * 
	 * @author Leandro Cavalcanti
	 * @date 17/08/2006
	 * 
	 */
	public Integer filtrarSTCount(ServicoTipo servicoTipo,
			Collection colecaoAtividades, Collection colecaoMaterial,
			String valorServicoInicial, String valorServicoFinal,
			String tempoMedioExecucaoInicial, String tempoMedioExecucaoFinal,
			String tipoPesquisa, String tipoPesquisaAbreviada)
			throws ErroRepositorioException;

	/**
	 * [UC413]- Pesquisar Tipo de Serviço
	 * 
	 * Pesquisar o Objeto servicoTipo referente ao idTiposervico recebido na
	 * descrição da pesquisa, onde o mesmo sera detalhado.
	 * 
	 * @author Leandro Cavalcanti
	 * @date 23/08/2006
	 * 
	 * @param idImovel
	 * @param idSolicitacaoTipoEspecificacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarServicoTipo(Integer idTipoServico)
			throws ErroRepositorioException;

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
	public Collection recuperarMaterialServicoTipoConsulta(
			Integer idServicoTipoMaterial) throws ErroRepositorioException;

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
	public Collection recuperarAtividadeServicoTipoConsulta(
			Integer idServicoTipoMaterial) throws ErroRepositorioException;

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
	public OrdemServico recuperaOSPorId(Integer idOS)
			throws ErroRepositorioException;

	/**
	 * 
	 * [UC0430] - Gerar Ordem de Serviço
	 * 
	 * @author lms
	 * @date 14/08/2006
	 */
	public boolean existeOrdemServicoDiferenteEncerrado(
			Integer idregistroAtendimento) throws ErroRepositorioException;

	/**
	 * 
	 * [UC0430] - Gerar Ordem de Serviço
	 * 
	 * @author lms
	 * @date 14/08/2006
	 */
	public OrdemServico pesquisarOrdemServicoDiferenteEncerrado(
			Integer idregistroAtendimento, Integer idServicoTipo)
			throws ErroRepositorioException;

	/**
	 * [UC0430] Gerar Ordem de Serviço
	 * 
	 * @author lms
	 * @date 17/08/2006
	 */
	public ServicoTipo pesquisarSevicoTipo(Integer id)
			throws ErroRepositorioException;

	/**
	 * [UC0471] Obter Dados da Equipe
	 * 
	 * @author Raphael Rossiter
	 * @date 01/09/2006
	 * 
	 * @return idQuipe
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarEquipe(Integer idEquipe)
			throws ErroRepositorioException;

	/**
	 * [UC0471] Obter Dados da Equipe
	 * 
	 * @author Raphael Rossiter
	 * @date 01/09/2006
	 * 
	 * @return idQuipe
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarEquipeComponentes(Integer idEquipe)
			throws ErroRepositorioException;

	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public Collection<ServicoTipo> pesquisarServicoTipoPorUnidade(
			Integer unidadeLotacao) throws ErroRepositorioException;

	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public Collection<ServicoTipo> pesquisarServicoTipoPorRA(
			Collection<Integer> idsRa) throws ErroRepositorioException;

	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public Collection<ServicoPerfilTipo> pesquisarServicoPerfilTipoPorUnidade(
			Integer unidadeLotacao) throws ErroRepositorioException;

	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public Collection<Localidade> pesquisarLocalidadePorUnidade(Integer unidade)
			throws ErroRepositorioException;

	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public Collection<SetorComercial> pesquisarSetorComercialPorUnidade(
			Integer unidade) throws ErroRepositorioException;

	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public Collection<SetorComercial> pesquisarSetorComercialPorRA(
			Collection<Integer> idsRa) throws ErroRepositorioException;

	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 *
	 * @author Rafael Pinto, Raphael Rossiter
	 * @date 04/09/2006, 22/05/2009
	 *
	 * @param idsRa
	 * @return Collection<DistritoOperacional>
	 * @throws ErroRepositorioException
	 */
	public Collection<DistritoOperacional> pesquisarDistritoOperacionalPorRAPelaQuadra(
			Collection<Integer> idsRa) throws ErroRepositorioException ;
	
	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 *
	 * @author Rafael Pinto, Raphael Rossiter
	 * @date 04/09/2006, 22/05/2009
	 *
	 * @param idsRa
	 * @return Collection<DistritoOperacional>
	 * @throws ErroRepositorioException
	 */
	public Collection<DistritoOperacional> pesquisarDistritoOperacionalPorRAPelaQuadraFace(
			Collection<Integer> idsRa) throws ErroRepositorioException ;

	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 *
	 * @author Rafael Pinto, Vivianne Sousa, Raphael Rossiter
	 * @date 04/09/2006, 13/06/2008, 22/05/2009
	 *
	 * @param unidade
	 * @return Collection<DistritoOperacional>
	 * @throws ErroRepositorioException
	 */

	public Collection<DistritoOperacional> pesquisarDistritoOperacionalPorUnidade(
			Integer unidade) throws ErroRepositorioException;

	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public Collection<Localidade> pesquisarLocalidadePorRA(
			Collection<Integer> idsRa) throws ErroRepositorioException;

	/**
	 * [UC0465] Filtrar Ordem Serviço
	 * 
	 * Recupera OS programadas
	 * 
	 * @author Leonardo Regis
	 * @date 05/09/2006
	 * 
	 * @param situacaoProgramacao
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> recuperaOSPorProgramacao(
			short situacaoProgramacao) throws ErroRepositorioException;

	/**
	 * [UC0450] Pesquisar Ordem de Servico
	 * 
	 * [SB003] Selecionar Ordem de Servico por Criterio de Seleção
	 * 
	 * @author Rafael Pinto
	 * @date 07/09/2006
	 * 
	 * @param PesquisarOrdemServicoHelper
	 * 
	 * @return Collection<OrdemServico>
	 * @throws ControladorException
	 */
	public Collection<OrdemServico> pesquisarOrdemServicoElaborarProgramacao(
			PesquisarOrdemServicoHelper filtro) throws ErroRepositorioException;

	/**
	 * [UC0465] Filtrar Ordem Serviço
	 * 
	 * Recupera OS por Data de Programação
	 * 
	 * @author Leonardo Regis
	 * @date 05/09/2006
	 * 
	 * @param dataProgramacaoInicial
	 * @param dataProgramacaoFinal
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> recuperaOSPorDataProgramacao(
			Date dataProgramacaoInicial, Date dataProgramacaoFinal)
			throws ErroRepositorioException;

	/**
	 * [UC0462] Obter Dados das Atividades da OS
	 * 
	 * obter OsAtividadeMaterialExecucao por OS
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param idOS
	 * @return Collection<OsAtividadeMaterialExecucao>
	 * @throws ErroRepositorioException
	 */
	public Collection<OsAtividadeMaterialExecucao> obterOsAtividadeMaterialExecucaoPorOS(
			Integer idOS) throws ErroRepositorioException;

	/**
	 * [UC0462] Obter Dados das Atividades da OS
	 * 
	 * obter OsAtividadePeriodoExecucao por OS
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param idOS
	 * @return Collection<OsExecucaoEquipe>
	 * @throws ErroRepositorioException
	 */
	public Collection<OsExecucaoEquipe> obterOsExecucaoEquipePorOS(Integer idOS)
			throws ErroRepositorioException;

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
	public Collection<OrdemServicoProgramacao> recuperaOSProgramacaoPorDataRoteiro(
			Date dataRoteiro) throws ErroRepositorioException;
	
	/**
	 * [UC0462] Obter Dados das Atividades da OS
	 * 
	 * obter OsAtividadeMaterialExecucao por OS
	 * 
	 * @author Leonardo Regis
	 * @date 14/09/2006
	 * 
	 * @param idOS
	 * @param idAtividade
	 * @return Collection<OsAtividadeMaterialExecucao>
	 * @throws ErroRepositorioException
	 */
	public Collection<OsAtividadeMaterialExecucao> obterOsAtividadeMaterialExecucaoPorOS(
			Integer idOS, Integer idAtividade) throws ErroRepositorioException;

	/**
	 * [UC0462] Obter Dados das Atividades da OS
	 * 
	 * obter OsAtividadePeriodoExecucao por OS
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param idOS
	 * @param idAtividade
	 * @return OsExecucaoEquipe
	 * @throws ErroRepositorioException
	 */
	public Collection<OsExecucaoEquipe> obterOsExecucaoEquipePorOS(
			Integer idOS, Integer idAtividade) throws ErroRepositorioException;

	/**
	 * [UC0460] Obter Carga de Trabalho da Equipe
	 * 
	 * obter programadações ativas por os e situacao
	 * 
	 * @author Leonardo Regis
	 * @date 14/09/2006
	 * 
	 * @param idOS
	 * @return Collection<OrdemServicoProgramacao>
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> obterProgramacoesAtivasPorOs(
			Integer idOS) throws ErroRepositorioException;

	/**
	 * [UC0460] Obter Carga de Trabalho da Equipe
	 * 
	 * obter o somatório de horas para a OS informada e para o dia do roteiro
	 * solicitado
	 * 
	 * @author Leonardo Regis
	 * @date 15/09/2006
	 * 
	 * @param idEquipe
	 * @param dataRoteiro
	 * @return OsExecucaoEquipe
	 * @throws ErroRepositorioException
	 */
	public Collection<OsExecucaoEquipe> obterOsExecucaoEquipePorEquipe(
			Integer idEquipe, Date dataRoteiro) throws ErroRepositorioException;

	/**
	 * [UC0457] Encerrar Ordem de Serviço
	 * 
	 * @author Sávio Luiz
	 * @date 18/09/2006
	 */
	public Integer pesquisarRAOrdemServico(Integer numeroOS)
			throws ErroRepositorioException;

	/**
	 * pesquisa se a OS tenha uma prgramação ativa com a data menor ou igual a
	 * data corrente e não tenha recebido a data de encerramento
	 * 
	 * [UC0457] Encerra Ordem de Serviço
	 * 
	 * [FS0006] - Verificar Origem do Encerramento da Ordem de Serviço
	 * 
	 * @author Sávio Luiz
	 * @date 18/09/2006
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisarOSProgramacaoAtiva(Integer numeroOS)
			throws ErroRepositorioException;

	/**
	 * pesquisa se a OS tenha uma prgramação ativa com a data menor ou igual a
	 * data corrente e não tenha recebido a data de encerramento
	 * 
	 * [UC0457] Encerra Ordem de Serviço
	 * 
	 * [SB0001] - Encerrar sem execução
	 * 
	 * @author Sávio Luiz
	 * @date 18/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void atualizarParmsOS(Integer numeroOS,
			Integer idMotivoEncerramento, Date dataEncerramento,
			String parecerEncerramento,String codigoRetornoVistoriaOs) throws ErroRepositorioException;

	/**
	 * [UC0457] Encerrar Ordem de Serviço
	 * 
	 * [SB0001] - Encerrar sem execução
	 * 
	 * @author Sávio Luiz
	 * @date 18/09/2006
	 */
	public Integer pesquisarOSReferencia(Integer numeroOS)
			throws ErroRepositorioException;

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
	public void atualizarParmsOSReferencia(Integer idOSReferencia, Integer idMotivoEncerramento)
			throws ErroRepositorioException;

	/**
	 * 
	 * [UC0457] Encerra Ordem de Serviço
	 * 
	 * [FS0004] - Verificar/Excluir/Atualizar Programação da Ordem de Serviço
	 * 
	 * @author Sávio Luiz
	 * @date 18/09/2006
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarRoteiro(Integer numeroOS, Date dataEncerramento)
			throws ErroRepositorioException;

	/**
	 * 
	 * [UC0457] Encerra Ordem de Serviço
	 * 
	 * [FS0004] - Verificar/Excluir/Atualizar Programação da Ordem de Serviço
	 * 
	 * @author Sávio Luiz
	 * @date 18/09/2006
	 * 
	 * @throws ControladorException
	 */
	public Integer verificarExistenciaProgramacaoRoteiroParaOSProgramacao(
			Integer idOSProgramacao, Integer idRoteiro)
			throws ErroRepositorioException;

	/**
	 * [UC0457] Encerra Ordem de Serviço
	 * 
	 * [FS0004] - Verificar/Excluir/Atualizar Programação da Ordem de Serviço
	 * 
	 * @author Sávio Luiz
	 * @date 18/09/2006
	 * 
	 * @throws ControladorException
	 */
	public OrdemServicoProgramacao pesquisarOSProgramacaoAtivaComDataEncerramento(
			Integer numeroOS, Date dataEncerramento)
			throws ErroRepositorioException;

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
	public Collection<Atividade> obterAtividadesOrdemServico(Integer numeroOS)
			throws ErroRepositorioException;

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
	public Collection<Atividade> obterEquipesProgramadas(Integer numeroOS)
			throws ErroRepositorioException;

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Serviço
	 * 
	 * @author Raphael Rossiter
	 * @date 19/09/2006
	 * 
	 * @param numeroOS
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificarOSAssociadaDocumentoCobranca(Integer numeroOS)
			throws ErroRepositorioException;

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Serviço
	 * 
	 * @author Raphael Rossiter
	 * @date 19/09/2006
	 * 
	 * @param numeroOS
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificarOSAssociadaRA(Integer numeroOS)
			throws ErroRepositorioException;

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Serviço
	 * 
	 * @author Raphael Rossiter
	 * @date 19/09/2006
	 * 
	 * @param numeroOS
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer obterUnidadeDestinoUltimoTramite(Integer numeroOS)
			throws ErroRepositorioException;

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Serviço
	 * 
	 * @author Raphael Rossiter
	 * @date 19/09/2006
	 * 
	 * @param idUnidade
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection obterEquipesPorUnidade(Integer idUnidade)
			throws ErroRepositorioException;

	/**
	 * [UC0362] Efetuar Instalação de Hidrômetro
	 * 
	 * @author Leonardo Regis
	 * @date 19/09/2006
	 * 
	 * @param dadosOS
	 * @throws ErroRepositorioException
	 */
	public void atualizarOSParaEfetivacaoInstalacaoHidrometro(
			DadosAtualizacaoOSParaInstalacaoHidrometroHelper dadosOS)
			throws ErroRepositorioException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 11/09/2006
	 * 
	 * @param dataRoteiro,idUnidadeOrganizacional
	 * @return collection
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> recuperaOSProgramacaoPorDataRoteiroUnidade(
			Date dataRoteiro, Integer idUnidadeOrganizacional)
			throws ErroRepositorioException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programação de Ordens de Serviço
	 * 
	 * [FS0008] - Verificar possibilidade da inclusão da ordem de serviço
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ErroRepositorioException
	 */
	public OrdemServicoProgramacao pesquisarOSProgramacaoAtivaPorOS(Integer idOS)
			throws ErroRepositorioException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ErroRepositorioException
	 */
	public OrdemServicoProgramacao pesquisarOSProgramacaoPorId(
			Integer idOrdemServicoProgramacao) throws ErroRepositorioException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ErroRepositorioException
	 */
	public OrdemServicoProgramacao pesquisarOSProgramacaoAtivaComDataRoteiroIdEquipe(
			Integer numeroOS, Date dataRoteiro, Integer idEquipe)
			throws ErroRepositorioException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> pesquisarOSProgramacaoComDataRoteiroIdEquipeDiferenteOS(
			Integer numeroOS, Date dataRoteiro, Integer idEquipe)
			throws ErroRepositorioException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> pesquisarOSProgramacaoComSequencialMaior(
			Integer numeroOS, Date dataRoteiro, Integer idEquipe,
			short sequencialReferencia) throws ErroRepositorioException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> pesquisarOSProgramacaoComDataRoteiroIdEquipe(
			Date dataRoteiro, Integer idEquipe) throws ErroRepositorioException;

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
	public Object[] recuperarParametrosServicoTipo(Integer numeroOS)
			throws ErroRepositorioException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 25/09/2006
	 * 
	 * @param idOs,dataRoteiro
	 * @return Equipe
	 * @throws ErroRepositorioException
	 */
	public Collection<Equipe> recuperaEquipeDaOSProgramacaoPorDataRoteiro(
			Integer idOs, Date dataRoteiro) throws ErroRepositorioException;

	/**
	 * Atualização Geral de OS
	 * 
	 * @author Leonardo Regis
	 * @date 25/09/2006
	 * 
	 * @param os
	 * @throws ErroRepositorioException
	 */
	public void atualizaOSGeral(OrdemServico os)
			throws ErroRepositorioException;

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
	public Collection pesquisarSolicitacoesServicoTipoOS(Integer numeroOS)
			throws ErroRepositorioException;

	/**
	 * [UC0457] Encerrar Ordem de Serviço
	 * 
	 * @author Sávio Luiz
	 * @date 25/09/2006
	 * 
	 * @param numeroOS
	 * @return Integer
	 * @throws ErroRRepositorioException
	 */
	public Collection pesquisarServicoTipo(Collection idsServicoTipo)
			throws ErroRepositorioException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 25/09/2006
	 * 
	 * @param idOs,dataRoteiro
	 * @return Equipe
	 * @throws ErroRepositorioException
	 */
	public boolean verificaExitenciaProgramacaoAtivaParaDiasAnteriores(
			Integer idOs, Date dataRoteiro) throws ErroRepositorioException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 26/09/2006
	 * 
	 * @param idOs,dataRoteiro
	 * @return Equipe
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> recuperaOrdemServicoProgramacaoPorDataRoteiro(
			Integer idOs, Date dataRoteiro) throws ErroRepositorioException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public UnidadeOrganizacional pesquisarUnidadeOrganizacionalPorUnidade(
			Integer idOs, Integer unidadeLotacao)
			throws ErroRepositorioException;

	/**
	 * [UC0457] Encerrar Ordem de Serviço
	 * 
	 * @author Sávio Luiz
	 * @date 26/09/2006
	 * 
	 * @param idOsReferidaRetornoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdMotivoEncerramentoOSReferida(
			Integer idOsReferidaRetornoTipo) throws ErroRepositorioException;

	/**
	 * [UC0457] Encerrar Ordem de Serviço
	 * 
	 * @author Sávio Luiz
	 * @date 26/09/2006
	 * 
	 * @param idOsReferidaRetornoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Short pesquisarIndDiagnosticoServicoTipoReferencia(
			Integer idOrdemServicoReferencia) throws ErroRepositorioException;

	/**
	 * [UC0457] Encerrar Ordem de Serviço
	 * 
	 * @author Sávio Luiz
	 * @date 29/09/2006
	 * 
	 * @param idOsReferidaRetornoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Object[] verificarExistenciaOSEncerrado(Integer idRA,
			Integer idServicoTipo) throws ErroRepositorioException;

	/**
	 * [UC0457] Encerrar Ordem de Serviço
	 * 
	 * @author Sávio Luiz
	 * @date 29/09/2006
	 * 
	 * @param idOsReferidaRetornoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaServicoTipoReferencia(
			Integer idServicoTipo) throws ErroRepositorioException;

	/**
	 * [UC0457] Encerrar Ordem de Serviço
	 * 
	 * @author Sávio Luiz
	 * @date 02/10/2006
	 * 
	 * @param idOsReferidaRetornoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaUnidadeTerceirizada(Integer idUsuario)
			throws ErroRepositorioException;

	/**
	 * [UC0457] Encerrar Ordem de Serviço
	 * 
	 * @author Sávio Luiz
	 * @date 02/10/2006
	 * 
	 * @param idOsReferidaRetornoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Collection verificarOSparaRA(Integer idOrdemServico, Integer idRA)
			throws ErroRepositorioException;

	/**
	 * Pesquisa os campos da OS que serão impressos no relatório de Ordem de
	 * Servico
	 * 
	 * @author Rafael Corrêa
	 * @date 17/10/2006
	 * 
	 * @param idOrdemServico
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOSRelatorio(Collection idsOrdemServico)
			throws ErroRepositorioException;

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Serviço
	 * 
	 * Obtém as datas de encerramento e geração de uma ordem de serviço
	 * 
	 * @author Raphael Rossiter
	 * @date 18/10/2006
	 * 
	 * @param numeroOS
	 * @return Collection
	 */
	public Collection obterDatasGeracaoEncerramentoOS(Integer numeroOS)
			throws ErroRepositorioException;

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
	public Collection obterMateriaisProgramados(Integer numeroOS)
			throws ErroRepositorioException;

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Serviço
	 * 
	 * @author Raphael Rossiter
	 * @date 15/09/2006
	 * 
	 * @param numeroOS,
	 *            idMaterial
	 * @return BigDecimal
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterQuantidadePadraoMaterial(Integer numeroOS,
			Integer idMaterial) throws ErroRepositorioException;

	/**
	 * [UC0457] Encerrar Ordem de Serviço
	 * 
	 * Recupera o id do imóvel do registro atendimento
	 * 
	 * @author Sávio Luiz
	 * @date 19/10/2006
	 * 
	 * @param numeroOS
	 * @return Collection
	 */
	public Integer recuperarIdImovelDoRA(Integer idRA)
			throws ErroRepositorioException;

	/**
	 * [UC0457] Encerrar Ordem de Serviço
	 * 
	 * Recupera o id do imóvel do registro atendimento
	 * 
	 * @author Sávio Luiz
	 * @date 19/10/2006
	 * 
	 * @param numeroOS
	 * @return Collection
	 */
	public Short recuperarSituacaoOSReferida(Integer idOSReferida)
			throws ErroRepositorioException;

	/**
	 * [UC0482] - Obter Endereço Abreviado da Ocorrência do RA
	 * 
	 * Pesquisa os dados da OS usados para saber de onde será recebido o
	 * endereço abreviado
	 * 
	 * @author Rafael Corrêa
	 * @date 19/10/2006
	 * 
	 * 
	 * @param idOrdemServico
	 * @throws ErroRepositorioException
	 */

	public Object[] obterDadosPesquisaEnderecoAbreviadoOS(Integer idOrdemServico)
			throws ErroRepositorioException;

	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 23/10/2006
	 */
	public Collection<Integer> recuperaRegistroAtendimentoPorMaiorTramiteUnidadeDestino(
			Integer unidadeDestino) throws ErroRepositorioException;

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
	 * @throws ErroRepositorioException
	 */
	public void atualizarOrdemServicoRelatorio(Collection colecaoIdsOrdemServico)
			throws ErroRepositorioException;

	/**
	 * [UC0460] Obter Carga de Trabalho da Equipe
	 * 
	 * obter OsAtividadePeriodoExecucao por OS
	 * 
	 * @author Rafael Pinto
	 * @date 09/09/2006
	 * 
	 * @param idOS
	 * @return OsAtividadePeriodoExecucao
	 * @throws ErroRepositorioException
	 */
	public Collection<OsAtividadePeriodoExecucao> obterOsAtividadePeriodoExecucaoPorOS(
			Integer idOS, Date dataRoteiro) throws ErroRepositorioException;

	/**
	 * [UC0460] Obter Carga de Trabalho da Equipe
	 * 
	 * obter OsAtividadePeriodoExecucao por OS
	 * 
	 * @author Rafael Pinto
	 * @date 09/09/2006
	 * 
	 * @param idEquipe
	 * @param dataRoteiro
	 * @return OsAtividadePeriodoExecucao
	 * @throws ErroRepositorioException
	 */
	public Collection<OsAtividadePeriodoExecucao> obterOsAtividadePeriodoExecucaoPorEquipe(
			Integer idEquipe, Date dataRoteiro) throws ErroRepositorioException;

	/**
	 * [UC0461] - Manter Dados das Atividades da Ordem de Serviço
	 * 
	 * Pesquisa os dados da OrdemServicoAtividade
	 * 
	 * @author Raphael Rossiter
	 * @date 01/11/2006
	 * 
	 * @param idOrdemServico,
	 *            idAtividade
	 * @throws ErroRepositorioException
	 */

	public Object[] pesquisarOrdemServicoAtividade(Integer numeroOS,
			Integer idAtividade) throws ErroRepositorioException;

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
	public Collection pesquisarOsAtividadeMaterialExecucao(
			Integer idOrdemServicoAtividade) throws ErroRepositorioException;

	/**
	 * Cria a query de acordo com os parâmetros de pesquisa informados pelo
	 * usuário
	 * 
	 * [UC0492] - Gerar Relatório Acompanhamento de Execução de Ordem de Serviço
	 * 
	 * @author Rafael Corrêa
	 * @date 01/11/06
	 * 
	 * @param idImovel
	 * @param idCliente
	 * @param idTipoRelacao
	 * @param localidadeInicial
	 * @param localidadeFinal
	 * @param idAvisoBancario
	 * @param idArrecadador
	 * @param periodoArrecadacaoInicial
	 * @param periodoArrecadacaoFinal
	 * @param periodoPagamentoInicio
	 * @param periodoPagamentoFim
	 * @param dataPagamentoInicial
	 * @param dataPagamentoFinal
	 * @param idsPagamentosSituacoes
	 * @param idsDebitosTipos
	 * @param idsArrecadacaoForma
	 * @param idsDocumentosTipos
	 * @return Collection
	 */
	public Collection pesquisarOSGerarRelatorioAcompanhamentoExecucao(
			String origemServico, String situacaoOS, String[] idsServicosTipos,
			String idUnidadeAtendimento, String idUnidadeAtual,
			String idUnidadeEncerramento, Date periodoAtendimentoInicial,
			Date periodoAtendimentoFinal, Date periodoEncerramentoInicial,
			Date periodoEncerramentoFinal, String idEquipeProgramacao,
			String idEquipeExecucao, String tipoOrdenacao)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa a equipe principal de uma OS de programação através do id da OS
	 * 
	 * @author Rafael Corrêa
	 * @date 07/11/2006
	 * 
	 * @param idOS
	 * @throws ErroRepositorioException
	 */
	public Equipe pesquisarEquipePrincipalOSProgramacao(Integer idOS)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa a equipe principal de uma OS de execução da equipe através do id
	 * da OS
	 * 
	 * @author Rafael Corrêa
	 * @date 07/11/2006
	 * 
	 * @param idOS
	 * @throws ErroRepositorioException
	 */
	public Equipe pesquisarEquipePrincipalOSExecucaoEquipe(Integer idOS)
			throws ErroRepositorioException;
	
	/**
	 * Cria o count de acordo com os parâmetros de pesquisa informados pelo
	 * usuário
	 * 
	 * [UC0492] - Gerar Relatório Acompanhamento de Execução de Ordem de Serviço
	 * 
	 * @author Rafael Corrêa
	 * @date 01/11/06
	 * 
	 * @param idImovel
	 * @param idCliente
	 * @param idTipoRelacao
	 * @param localidadeInicial
	 * @param localidadeFinal
	 * @param idAvisoBancario
	 * @param idArrecadador
	 * @param periodoArrecadacaoInicial
	 * @param periodoArrecadacaoFinal
	 * @param periodoPagamentoInicio
	 * @param periodoPagamentoFim
	 * @param dataPagamentoInicial
	 * @param dataPagamentoFinal
	 * @param idsPagamentosSituacoes
	 * @param idsDebitosTipos
	 * @param idsArrecadacaoForma
	 * @param idsDocumentosTipos
	 * @return Collection
	 */
	public int pesquisarOSGerarRelatorioAcompanhamentoExecucaoCount(
			String origemServico, String situacaoOS, String[] idsServicosTipos,
			String idUnidadeAtendimento, String idUnidadeAtual,
			String idUnidadeEncerramento, Date periodoAtendimentoInicial,
			Date periodoAtendimentoFinal, Date periodoEncerramentoInicial,
			Date periodoEncerramentoFinal, String idEquipeProgramacao,
			String idEquipeExecucao)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa as equipes de acordo com os parâmetros informado pelo usuário
	 * 
	 * [UC0370] - Filtrar Equipe
	 * 
	 * @author Rafael Corrêa
	 * @date 01/11/06
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
	public Collection pesquisarEquipes(String idEquipe, String nome,
			String placa, String cargaTrabalho,String codigoDdd,
			String numeroTelefone, String numeroImei, String idUnidade,
			String idFuncionario, String idPerfilServico, String indicadorUso, 
			String tipoPesquisa, Integer numeroPagina,String equipamentosEspeciasId, String cdUsuarioRespExecServico,
			String indicadorProgramacaoAutomatica) throws ErroRepositorioException;
	
	/**
	 * Verifica a quantidade de registros retornados da pesquisa de equipe
	 * 
	 * [UC0370] - Filtrar Equipe
	 * 
	 * @author Rafael Corrêa
	 * @date 01/11/06
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
	public int pesquisarEquipesCount(String idEquipe, String nome,
			String placa, String cargaTrabalho, String codigoDdd,
			String numeroTelefone, String numeroImei, String idUnidade,
			String idFuncionario, String idPerfilServico, String indicadorUso,
			String tipoPesquisa,String equipamentosEspeciasId, String cdUsuarioRespExecServico,
			String indicadorProgramcaoAutomatica) throws ErroRepositorioException;
	
	/**
	 * Verifica se o serviço associado  á ordem de serviço não corresponde  a um serviço de fiscalização de infração
	 * 
	 * [UC0488] - Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Sávio Luiz
	 * @date 01/11/06
	 * 
	 * @return Integer
	 */
	public Object[] pesquisarServicoTipoComFiscalizacaoInfracao(Integer idOS)
			throws ErroRepositorioException;
	
	/**
	 * [UC0367] Informar Retorno Ordem de Fiscalização
	 * 
	 * Recupera os  parámetros necessários da OS
	 * 
	 * @author Sávio Luiz
	 * @date 24/08/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Object[] pesquisarParmsOS(Integer idOS)
			throws ErroRepositorioException;
	
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
	public Object[] pesquisarIdFiscalizacaoSituacaoAgua(
			Integer idLigacaoAguaSituacao, Integer idSituacaoEncontrada) throws ErroRepositorioException;
	
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
	public Object[] pesquisarIdFiscalizacaoSituacaoEsgoto(
			Integer idLigacaoEsgotoSituacao, Integer idSituacaoEncontrada) throws ErroRepositorioException;
	
	/**
	 * [UC0367] Informar Retorno Ordem de Fiscalização
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
	public void atualizarParmsOrdemFiscalizacao(Integer numeroOS,
			String indicadorDocumentoEntregue,
			Short indicadorAtualizacaoSituacaoLigacaoAgua,
			Short indicadorAtualizacaoSituacaoLigacaoEsgoto, Integer idSituacaoEncontrada)
			throws ErroRepositorioException ;
	
	/**
	 * [UC0367] Informar Retorno Ordem de Fiscalização
	 * 
	 * Recupera os parámetros necessários da OS
	 * 
	 * @author Sávio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Object[] pesquisarIdFiscalizacaoSituacaoAguaOS(
			Integer idFiscalizacaoSituacao, Integer idLigacaoAguaSituacao) throws ErroRepositorioException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * Recupera os parámetros necessários da OS
	 * 
	 * @author Sávio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Object[] pesquisarIdFiscalizacaoSituacaoEsgotoOS(
			Integer idFiscalizacaoSituacao, Integer idLigacaoEsgotoSituacao) throws ErroRepositorioException;
	
	/**
	 * [UC0367] Informar Retorno Ordem de Fiscalização
	 * 
	 * Recupera os parámetros necessários da OS
	 * 
	 * @author Sávio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisarHidormetroCapacidadeFiscalizacaoAgua(
			Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 15/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisarHidormetroCapacidadeFiscalizacaoPoco(
			Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 15/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Collection pesquisarFiscalizacaoSituacaoServicoACobrar(
			Integer idFiscalizacaoSituacao) throws ErroRepositorioException;

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 20/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Object[] pesquisarParmsFiscalizacaoSituacaoServicoACobrar(
			Integer idFiscalizacaoSituacao, Integer idDebitoTipo) throws ErroRepositorioException;
	
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
	public Integer pesquisarOrdemServicoTamanho(PesquisarOrdemServicoHelper filtro) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisarIdServicoTipoDaOS(Integer idOS)
			throws ErroRepositorioException;
	
	/**
	 * Consulta a ordem de servico pelo id
	 * 
	 * @author Sávio Luiz
	 * @date 03/01/2007
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public OrdemServico pesquisarDadosServicoTipoPrioridade(Integer idServicoTipo)
		throws ErroRepositorioException;
	
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
	public void atualizarOsDaRA(Integer idRA, Integer idImovel)
			throws ErroRepositorioException;
	
	
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
	public FiscalizacaoSituacao pesquisarFiscalizacaoSituacaoPorOS(Integer idOS)
			throws ErroRepositorioException ;
	
	
	/**
	 * [UC0455] Exibir Calendário para Elaboração ou Acompanhamento de Roteiro 
	 * 
	 * @author Raphael Rossiter
	 * @date 14/02/2007
	 * 
	 * @param idProgramacaoRoteiro
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaOSProgramacao(Integer idProgramacaoRoteiro)
			throws ErroRepositorioException ;
	
	/**
	 * Pesquisa as Os do serviço tipo especifico com RA  
	 * 
	 * @author Sávio Luiz
	 * @date 23/02/2007
	 * 
	 * @param idProgramacaoRoteiro
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOSComServicoTipo(Integer idServicoTipo)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa as Os do serviço tipo especifico com RA  
	 * 
	 * @author Sávio Luiz
	 * @date 23/02/2007
	 * 
	 * @param idProgramacaoRoteiro
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public void atualizarColecaoOS(Collection colecaoIdsOS)
			throws ErroRepositorioException;

	/**
	 * 
	 * Pesquisar data e equipe da programação da ordem serviço
	 * 
	 * @author Ana Maria
	 * @date 09/03/2007
	 */
	public OrdemServicoProgramacao pesquisarDataEquipeOSProgramacao(
			Integer idOs)throws ErroRepositorioException;	
	
	
	/**
	 * [UC0367] Informar Retorno Ordem de Fiscalização
	 * 
	 * Obtém o número de meses da fatura a partir da tabela FISCALIZACAO_SITUACAO_AGUA
	 * 
	 * @author Raphael Rossiter
	 * @date 16/03/2007
	 * 
	 * @param idLigacaoAguaSituacao, idSituacaoEncontrada
	 * @return numeroMesesFatura
	 * @throws ErroRepositorioException
	 */
	public Short pesquisarNumeroMesesFaturaAgua(
			Integer idLigacaoAguaSituacao, Integer idSituacaoEncontrada)
			throws ErroRepositorioException ;
	
	
	/**
	 * [UC0367] Informar Retorno Ordem de Fiscalização
	 * 
	 * Obtém o número de meses da fatura a partir da tabela FISCALIZACAO_SITUACAO_ESGOTO
	 * 
	 * @author Raphael Rossiter
	 * @date 16/03/2007
	 * 
	 * @param idLigacaoEsgotoSituacao, idSituacaoEncontrada
	 * @return numeroMesesFatura
	 * @throws ErroRepositorioException
	 */
	public Short pesquisarNumeroMesesFaturaEsgoto(
			Integer idLigacaoEsgotoSituacao, Integer idSituacaoEncontrada)
			throws ErroRepositorioException ;
	/**
	 * Inserir Debitos 
	 * 
	 * Método Criado a pedido de Rossana Carvalho
	 * para inserção de debitos
	 */
	public Collection pesquisaParaCriarDebitosNaoGerados() throws ErroRepositorioException;
	
	/**
	 * Inserir Debitos Categoria
	 * 
	 * Método Criado a pedido de Rossana Carvalho
	 * para inserção de debitos
	 */
	public Collection pesquisaParaCriarDebitosCategoriaNaoGerados() throws ErroRepositorioException;
	
	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> pesquisarOSProgramacaoComDataRoteiroIdEquipeOrdenada(
			Date dataRoteiro, Integer idEquipe) throws ErroRepositorioException;
	
	
	/**
	 * [UC0457] Encerra Ordem de Serviço 
	 * 
	 * @author Raphael Rossiter
	 * @date 25/04/2007
	 * 
	 * @param idServicoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarServicoTipoOperacao(Integer idServicoTipo)
			throws ErroRepositorioException ;

	/**
	 * [UC0711] Filtro para Emissao de Ordens Seletivas 
	 * 
	 * @author Ivan Sérgio, Ivan Sérgio, Raphael Rossiter
	 * @date 08/11/2007, 27/03/2008, 17/04/2009
	 * @alteracao: Media do Imovel e Consumo por Economia com os valores >= ao informado
	 * 
	 * @param ImovelEmissaoOrdensSeletivasHelper, dataInstalacaoHidrometro, anoMesFaturamento
	 * @return List
	 * @throws ErroRepositorioException
	 */
	public List filtrarImovelEmissaoOrdensSeletivas(ImovelEmissaoOrdensSeletivasHelper helper,
			String dataInstalacaoInicialHidrometro, String anoMesFaturamento, 
			String dataInstalacaoFinalHidrometro,Short codigoEmpresaFebraban) throws ErroRepositorioException ;
	
	/**
	 * Verifica se já existe Ordem de Servico de
	 * Instalacao/Substituicao de Hidrometro para o Imovel
	 * 
	 * @author Ivan Sérgio
	 * @date 19/11/2007
	 */
	public Integer verificarOrdemServicoInstalacaoSubstituicaoImovel(Integer idImovel, Date dataVencimento)
		throws ErroRepositorioException;
	
	/**
	 * [UC0732] Gerar Relatorio Acompanhamento de Oredem de Servico de Hidrometro
	 * 
	 * @author Ivan Sérgio
	 * @date 13/12/2007, 27/03/2008
	 * @alteracao: Adicionado Motivo Encerramento; Setor Comercial;
	 * 
	 * @param tipoOrdem
	 * @param situacaoOrdem
	 * @param idLocalidade
	 * @param dataEncerramentoInicial
	 * @param dataEncerramentoFinal
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemServicoGerarRelatorioAcompanhamentoHidrometro(
			String idEmpresa,
			String tipoOrdem,
			Short situacaoOrdem,
			String idLocalidadeInicial,
			String idLocalidadeFinal,			
			String dataEncerramentoInicial,
			String dataEncerramentoFinal,
			String idMotivoEncerramento,
			String idSetorComercialInicial,
			String idSetorComercialFinal,
			String codigoQuadraInicial,
			String codigoQuadraFinal,
			String codigoRotaInicial,
			String codigoRotaFinal,
			String sequenciaRotaInicial,
			String sequenciaRotaFinal
			) throws ErroRepositorioException;
	

	
	/**
	 * Pesquisa os dados necessários para exportar a ordem de serviço.
	 *
	 * [UC0720] Exportar Ordem Serviço Prestadoras
	 *
	 * @author Pedro Alexandre
	 * @date 21/12/2007
	 *
	 * @param idRegistroAtendimento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosParaExportarOrdemServicoPrestadora(Integer idRegistroAtendimento) throws ErroRepositorioException; 


	
	
	/**
	 * [UC0640] Gerar TXT Ação de Cobrança
	 * 
	 * @author Raphael Rossiter
	 * @date 04/01/2008
	 * 
	 * @param idCobrancaDocumento
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarOrdemServicoPorCobrancaDocumento(Integer idCobrancaDocumento)
			throws ErroRepositorioException ;



	
	/**
	 * [UC0734] Encerrar Ordem de Servico Vencida
	 * 
	 * @author Ivan Sérgio
	 * @date 14/01/2008
	 * 
	 * @param idServicoTipo
	 * @param dataVencimento
	 * @return
	 * @throws ErroRepositorioException
	 */
	
	/**
	 * Inclusão da Origem da OS na busca para encerrar as mesmas quando vencidas.
	 * @author Wellington Rocha
	 * @date 18/01/2012*/
	public List pesquisarOrdemServicoVencida(Integer idServicoTipo, String dataVencimento)
			throws ErroRepositorioException;
	
	/**
	 * [UC0734] Encerrar Ordem de Servico Vencida
	 * 
	 * @author Ivan Sérgio
	 * @date 16/01/2008 
	 * 
	 * @param ordemServico
	 * @throws ErroRepositorioException
	 */
	public void alterarOrdemServicoVencida(OrdemServico ordemServico) throws ErroRepositorioException;

	/**
	 * Pesquisa o movimento da ordem de serviço
	 *
	 * [UC0720] Exportar Ordem de Serviço Movimento
	 *
	 * @author Pedro Alexandre
	 * @date 18/01/2008
	 *
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public OrdemServicoMovimento pesquisarOrdemServicoMovimento(Integer idOrdemServico)	throws ErroRepositorioException ;
	
	/**
	 * [UC0753] Manter Ordem de Servico Concluida
	 * 
	 * @author Ivan Sérgio
	 * @date 26/03/2008
	 * 
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemServicoConcluida(Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC0732] Gerar Relatorio Acompanhamento de Oredem de Servico de Hidrometro
	 * 			RELATORIO: Relacao das Ordens de Servico Concluidas
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
	public Collection pesquisarOrdemServicoConcluidaGerarRelatorioAcompanhamentoHidrometro(
			String idEmpresa,
			String tipoOrdem,
			Short situacaoOrdem,
			String idLocalidadeInicial,
			String idLocalidadeFinal,
			String dataEncerramentoInicial,
			String dataEncerramentoFinal,
			String idMotivoEncerramento,
			String idSetorComercialInicial,
			String idSetorComercialFinal,
			String codigoQuadraInicial,
			String codigoQuadraFinal,
			String codigoRotaInicial,
			String codigoRotaFinal,
			String sequenciaRotaInicial,
			String sequenciaRotaFinal) throws ErroRepositorioException;

    /**
     * [UC0720] Exportar Ordem de Serviço Prestadoras
     * 
     * @author Vivianne Sousa
     * @date 02/04/2008
     * 
     * @throws ControladorException
     */
    public void atualizarIndicadorEncerramentoAutomaticoOS(
            Short indicadorEncerramentoAutomatico,Integer idOrdemServico)
            throws ErroRepositorioException ;
    
    /**
     * [UC0753] Manter Ordem Servico Concluida
     * 
     * @author Ivan Sérgio
     * @date 09/04/2008
     * 
     * @param idOrdemServico
     * @param situacaoFiscalizacao
     * @param dataFiscalizacao1
     * @param idFuncionario
     * @throws ErroRepositorioException
     */
    public void atualizarBoletimOSConcluida(
    		Integer idOrdemServico,
			Short situacaoFiscalizacao,
			Date dataFiscalizacao1,
			Integer idFuncionario) throws ErroRepositorioException;
    
    /**
     * [UC0753] Manter Ordem Servico Concluida
     * 
     * @author Ivan Sérgio
     * @date 09/04/2008
     *  
     * @param idOrdemServico
     * @throws ErroRepositorioException
     */
    public void excluirDatasFiscalizacaoBoletimOSConcluida(Integer idOrdemServico)
    	throws ErroRepositorioException;
    
    /***
     * [UC0765] Gerar Boletim Ordens de Servico Concluidas
     * 
     * @author Ivan Sérgio
     * @date 29/04/2008
     * 
     * @param idEmpresa
     * @param idLocalidade
     * @param anoMesReferenciaBoletim
     * @return
     * @throws ErroRepositorioException
     */
	public Collection pesquisarResumoOrdensServicoConcluidas(
			Integer idEmpresa,
			Integer idLocalidade,
			String anoMesReferenciaBoletim) throws ErroRepositorioException;
	
	/**
	 * [UC0765] Gerar Boletim Ordens de Servico Concluidas
     * 
     * @author Ivan Sérgio
     * @date 02/05/2008
	 * 
	 * @param idOrdemServico
	 * @throws ErroRepositorioException
	 */
	public void encerrarBoletimOrdemServicoConcluida(Integer idOrdemServico) throws ErroRepositorioException;


	
	
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
	public Collection pesquisarOrdemProcessoRepavimentacao(OSPavimentoHelper pavimentoHelper, Integer numeroPagina)
			throws ErroRepositorioException;


	
	
	/**
	 * Pesquisa Ordens em Processo de Repavimentação
	 *
	 * [UC0639] Filtrar Ordens em Processo de Repavimetação.
	 *
	 * @author Yara Taciane
	 * @date 12/06/2008
	 *
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */	
	
	public Collection pesquisarOrdemProcessoRepavimentacaoPorTipoPavimentoCalcada(OSPavimentoHelper pavimentoHelper)throws ErroRepositorioException;

	
	/**
	 * Pesquisa Ordens em Processo de Repavimentação
	 *
	 * [UC0639] Filtrar Ordens em Processo de Repavimetação.
	 *
	 * @author Yara Taciane
	 * @date 12/06/2008
	 *
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */	
	
	public Collection pesquisarOrdemProcessoRepavimentacaoPorTipoPavimentoRua(OSPavimentoHelper pavimentoHelper)throws ErroRepositorioException;

	
	/**
	 * Pesquisa Ordens em Processo de Repavimentação
	 *
	 * [UC0639] Filtrar Ordens em Processo de Repavimetação.
	 *
	 * @author Yara Taciane
	 * @date 12/06/2008
	 *
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */	
	public Collection pesquisarOrdemProcessoRepavimentacaoPorTipoPavimentoRuaRet(OSPavimentoHelper pavimentoHelper)throws ErroRepositorioException;
	/**
	 * [UC0457]Encerrar Ordem de Servico
	 * 
	 * Obtém as datas de geração de uma ordem de serviço
	 * 
	 * @author Yara Taciane 
	 * @date 18/06/2006
	 * 
	 * @param numeroOS
	 * @return Collection
	 */
	public Date obterDataGeracaOS(Integer integer)throws ErroRepositorioException;

	
	/**
	 * [UC0457]Encerrar Ordem de Servico
	 * 
	 * Verifica se é OS Seletiva.
	 * 
	 * @author Yara Taciane 
	 * @date 18/06/2006
	 * 
	 * @param numeroOS
	 * @return Collection
	 */
	public Integer verificarOrdemServicoSeletiva(Integer numOS)throws ErroRepositorioException;
			

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
	public Collection pesquisarBoletimOrdensServicoConcluidasGerarRelatorio(
			Integer idEmpresa,
			Integer idLocalidade,
			Integer idSetorComercial,
			String referenciaEncerramento,
			Short situacao) throws ErroRepositorioException;

	/**
     * [UC0427] Tramitar Registro de Atendimento
     * 
     * @author Vivianne Sousa
     * @date 10/06/2008
     * 
     * @throws ControladorException
     */
    public void atualizarUnidadeOrganizacionalAtualOS(Integer idUnidadeOrganizacionalAtual,
            Integer idRA)throws ErroRepositorioException;
    
    /**
     * [UC0470] Acompanhar Roteiro de Programação de Ordens de Serviço
     * [FS0008] - Verificar possibilidade da inclusão da ordem de serviço
     * 
     * @author Vivianne Sousa
     * @date 12/06/2008
     * 
     * @throws ControladorException
     */
    public UnidadeOrganizacional obterUnidadeAtualOS(Integer idOS)throws ErroRepositorioException;
    
    /**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Vivianne Sousa
	 * @date 13/06/2008
	 */
	public Collection<Integer> recuperaRegistroAtendimentoPendenteUnidadeAtual(
			Integer unidadeAtual) throws ErroRepositorioException;
	
	/**
	 * [UC0427] - Tramitar Registro Atendimento
	 * [FS0011]Validar Tramite para terceira
	 * 
	 * @author Vivianne Sousa
	 * @date 29/10/2008
	 */
	public boolean existeMaisDeUmaOrdemServicoPendente(
			Integer idregistroAtendimento) throws ErroRepositorioException;
	
	/**
	 * [UC0488] - Consultar Dados do Registro de Atendimento 
	 * 
	 * Dados da Os Associadas
	 * 
	 * @author Arthur Carvalho
	 * @date 17/02/2009
	 */
	public Collection pesquisarOrdensServicosAssociados(Integer idregistroAtendimento) 
		throws ErroRepositorioException ;
	
	/**
	 * Retorna a quantidade de registros para geracao do relatorio
	 * 
	 * [UC0711] Filtro para Emissao de Ordens Seletivas 
	 * 
	 * @author Anderson Italo
	 * @date 21/08/2009
	 * 
	 * @param ImovelEmissaoOrdensSeletivasHelper, dataInstalacaoHidrometro, anoMesFaturamento
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer filtrarImovelEmissaoOrdensSeletivasCount(ImovelEmissaoOrdensSeletivasHelper helper,
			String dataInstalacaoHidrometroInicial, String anoMesFaturamento, String dataInstalacaoHidrometroFinal ) throws ErroRepositorioException;
	
	/**
	 * Pesquisar Serviço Tipo Seletivo
	 * 
	 * Seleciona Serviço Tipo Seletivo por codigo da constante
	 * 
	 * @author Hugo Amorim
	 * @date 09/10/2009
	 * 
	 */
	public Integer recuperaServicoTipoSeletivoPorConstante(Integer codigoConstante) throws ErroRepositorioException;
	
	public Integer recuperaServicoTipoSeletivoPorId(Integer id) throws ErroRepositorioException;
	
	
	/**
	 * [UC0620] Obter Indicador de Autorização para Manutenção da OS 
	 * 
	 *          Pesquisa a unidade Superior da unidade Organizacional
	 * 
	 * @author Arthur Carvalho
	 * 
	 * @date 11/11/2009
	 * 
	 * @param idUnidadeOrganizacional
	 * @return
	 * @throws ErroRepositorioException
	 */
	
	public UnidadeOrganizacional pesquisarUnidadeOrganizacionalSuperior(Integer idUnidadeOrganizacional) 
			 throws ErroRepositorioException;
	
	/**
	 * Pesquisar Serviço Tipo Seletivo
	 * 
	 * Seleciona Serviço Tipo Seletivo por codigo da constante
	 * 
	 * @author Hugo Amorim
	 * @date 12/02/2009
	 * 
	 */
	public Integer recuperaServicoTipoPorConstante(
			Integer codigoConstante) throws ErroRepositorioException;
	
	
	/**
	 * Pesquisa quantidade Ordens em Processo de Repavimentação
	 *
	 * [UC0639] Filtrar Ordens em Processo de Repavimetação.
	 *
	 * @author Arthur Carvalho
	 * @date 22/02/2010
	 *
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarOrdemProcessoRepavimentacaoCount(OSPavimentoHelper pavimentoHelper)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa a quantidade de Ocorrências Consecultivas da anormalidade
	 *
	 * Parte do [UC0711] Filtro Para Geração de Ordens de Serviço Hidrômetro.
	 *
	 * @author Daniel Alves
	 * @date 22/03/2010
	 *
	 * @param String idLocalidadeInicial, String idLocalidadeFinal,
			  String idSetorComercialInicial, String idSetorComercialFinal,
			  String idQuadraInicial, String idQuadraFinal,
			  String idRotaInicial, String idRotaFinal,
			  String sequenciaRotaInicial, String sequenciaRotaFinal,
			  String idAnormalidade, int qtdAnormalidades,
			  int referencia
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarNumeroDeOcorrenciasConsecultivasAnormalidades(			
			String idAnormalidades, String qtdAnormalidades,
			Integer referenciaFaturamento, 
			ImovelEmissaoOrdensSeletivasHelper  helper 		
			   )throws ErroRepositorioException;
	
	/**
	 *[SB0006] – Obter Unidade Repavimentadora do Município
	 * 
	 * 1.1.1.1.	Obter a unidade repavimentadora a partir do município do endereço do CEP 
	 * 
	 * @author Arthur Carvalho
	 * @date 12/02/2009
	 * 
	 */
	public UnidadeOrganizacional obterUnidadeRepavimentadorAPartirMunicipio(OrdemServico os,
			String tipoPesquisa) 
		throws ErroRepositorioException ;
	
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
			throws ErroRepositorioException;
	
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
	public Integer pesquisarOrdemProcessoRepavimentacaoAceiteCount(OrdemRepavimentacaoProcessoAceiteHelper oSPavimentoAceiteHelper)
			throws ErroRepositorioException ;
	
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
	public Date recuperaDataFiscalizacaoSituacao(
			Integer idOrdemServico,Integer idFiscalizacaoSituacao) throws ErroRepositorioException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 29/07/2010
	 */
	public OrdemServicoFiscSit recuperaOrdemServicoFiscSit(
			Integer idOrdemServico, Integer idFiscalizacaoSituacao) throws ErroRepositorioException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 30/07/2010
	 */
	public OrdemServico recuperaOrdemServico(
			Integer idOrdemServico) throws ErroRepositorioException;

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 02/08/2010
	 */
	public Collection recuperaFiscalizacaoSituacao(
			Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 03/08/2010
	 */
	public Collection recuperaFiscalizacaoSituacaoSelecionada(
			Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 09/08/2010
	 */
	public Collection recuperaOrdemServicoFiscSit(
			Integer idOrdemServico) throws ErroRepositorioException;
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * [SB0003] – Calcular/Inserir Valor
	 * 
	 * @author Vivianne Sousa
	 * @date 18/08/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorDebitoOS(Integer indicadorDebito,
			Integer idFiscalizacaoSituacao, Integer idOS)throws ErroRepositorioException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * [SB0001] – Atualizar Ordem de Serviço.
	 * 
	 * @author Vivianne Sousa
	 * @date 18/08/2010
	 * 
	 * @throws ErroRepositorioException
	 */
    public void excluirSituacaoFiscalizacaoPorOS(Integer idOrdemServico)
    	throws ErroRepositorioException ;
    
    /**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * [SB0001] – Atualizar Ordem de Serviço.
	 * 
	 * @author Vivianne Sousa
	 * @date 18/08/2010
	 * 
	 * @throws ErroRepositorioException
	 */
    public void excluirCobrancaDocumentoFiscPorOS(Integer idOrdemServico)
    	throws ErroRepositorioException;
    
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 19/08/2010
	 */
	public Short recuperaIndicadorDebitoDaOrdemServicoFiscSit(
			Integer idOrdemServico, Integer idFiscalizacaoSituacao) throws ErroRepositorioException;
	
	/**
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * @author Vivianne Sousa
	 * @date 30/08/2010
	 * 
	 */
	public OrdemServicoFiscSit recuperaOrdemServicoFiscSitComMenorDataFiscalizacao(
			Integer idOrdemServico) throws ErroRepositorioException;
	/**
	 * [UC0441] Consultar Dados da Ordem de Serviço
	 * 
	 * @author Vivianne Sousa
	 * @date 01/09/2010
	 */
	public Collection pesquisaOrdemServicoFiscSit(
			Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC1109] Filtrar Dados para Geração Boletim de Custo de Repavimentação
	 *
	 * @author Hugo Leonardo
	 * @date 03/01/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioBoletimCustoPavimento(FiltrarBoletimCustoPavimentoHelper relatorioHelper)
			throws ErroRepositorioException;
	
	/**
	 * [UC1109] Filtrar Dados para Geração Boletim de Custo de Repavimentação
 	 * 
 	 * @author Hugo Leonardo
 	 * @date 03/01/2011
 	 * 
 	 * @return boolean
	 */
	public boolean verificaDadosGeracaoBoletimCustoRepavimentacao(FiltrarBoletimCustoPavimentoHelper relatorioHelper)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC1110] Gerar Boletim de Custo de Repavimentação por Tipo de Pavimento
	 *
	 * @author Hugo Leonardo
	 * @date 04/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarBoletimCustoPavimentoPorTipoPavimentoRua(FiltrarBoletimCustoPavimentoHelper relatorioHelper)	
		throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC1110] Gerar Boletim de Custo de Repavimentação por Tipo de Pavimento
	 *
	 * @author Hugo Leonardo
	 * @date 04/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarBoletimCustoPavimentoPorTipoPavimentoRuaRet(FiltrarBoletimCustoPavimentoHelper relatorioHelper)	
		throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC1110] Gerar Boletim de Custo de Repavimentação por Tipo de Pavimento
	 *
	 * @author Hugo Leonardo
	 * @date 10/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarTotaisPorTipoPavimentoRuaDemandadas(
			FiltrarBoletimCustoPavimentoHelper relatorioHelper)	throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC1110] Gerar Boletim de Custo de Repavimentação por Tipo de Pavimento
	 *
	 * @author Hugo Leonardo
	 * @date 10/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarTotaisPorTipoPavimentoRuaDemandadas3Meses(
			FiltrarBoletimCustoPavimentoHelper relatorioHelper)	throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC1110] Gerar Boletim de Custo de Repavimentação por Tipo de Pavimento
	 *
	 * @author Hugo Leonardo
	 * @date 10/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarTotaisPorTipoPavimentoRuaAceitas(
			FiltrarBoletimCustoPavimentoHelper relatorioHelper)	throws ErroRepositorioException;
	
	/**
	 * [UC0457] Encerrar Ordem de Serviço
	 * 
	 * @author Vivianne Sousa
	 * @date 18/01/2011
	 */
	public ServicoTipo recuperaServicoTipoDaOrdemServico(
			Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC0457] Encerrar Ordem de Serviço
	 * 
	 * @author Vivianne Sousa
	 * @date 21/01/2011
	 */
	public ServicoTipoBoletim recuperaServicoTipoBoletimDoServicoTipo(
			Integer idServicoTipo) throws ErroRepositorioException;
	
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
	public OrdemServico recuperaOSEDadosImovel(Integer idOS)
			throws ErroRepositorioException;
	
	/**
	 * [UC1116] Atualizar Informações da OS para Boletim de Medição
	 * 
	 * @author Vivianne Sousa
	 * @date 02/02/2011
	 */
	public OrdemServicoBoletim recuperaOrdemServicoBoletimDaOS(
			Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC1163]  Gerar  Relatório de OS executadas por Prestadora de Serviço
	 * 
	 * @author Vivianne Sousa
	 * @date 13/04/2011
	 */
	public Collection recuperaOSExecutadas(Date dataInicial,Date dataFinal, 
		Integer idGerencia, Integer idUnidade, Integer idLocalidade,
		String[] idsRegiao,String[] idsMicroregiao,
		String[] idsMunicipio) throws ErroRepositorioException;
	
	/**
	 * [UC1163]  Gerar  Relatório de OS executadas por Prestadora de Serviço
	 * 
	 * @author Vivianne Sousa
	 * @date 18/04/2011
	 */
	public Collection recuperaTotalServicoOSExecutadas(Date dataInicial,Date dataFinal, 
		Integer idLocalidade) throws ErroRepositorioException;
	
	/**
	 * [UC1163]  Gerar  Relatório de OS executadas por Prestadora de Serviço
	 * 
	 * @author Vivianne Sousa
	 * @date 18/04/2011
	 */
	public Collection recuperaTotalServicoOSExecutadasPorLocalidade(Date dataInicial,Date dataFinal, 
			Integer idGerencia, Integer idUnidade, Integer idLocalidade,
			String[] idsRegiao,String[] idsMicroregiao,	String[] idsMunicipio) throws ErroRepositorioException;
	
	/**
	 * [UC1163]  Gerar  Relatório de OS executadas por Prestadora de Serviço
	 * 
	 * @author Vivianne Sousa
	 * @date 04/05/2011
	 */
	public Integer recuperaTotalOSExecutadasPorLocalidade(Date dataInicial,Date dataFinal, 
			Integer idGerencia, Integer idUnidade, Integer idLocalidade,
			String[] idsRegiao,String[] idsMicroregiao,
			String[] idsMunicipio) throws ErroRepositorioException ;
	
	/**
	 * 
	 * Recupera os parámetros necessários da OS
	 * 
	 * @author Sávio Luiz
	 * @date 04/04/2011
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Short pesquisarIndAtividadeServicoTipoOS(Integer idOS)
			throws ErroRepositorioException;

	/**
	 * Atualiza o documento de cobrança da ordem de serviço que foi 
	 * gerado pelo "[UC0444 Gerar e Emitir Extrato de Débito]"
	 * 
	 * [UC1169] Movimentar Ordens de Serviço de Cobrança por Resultado
	 * 
	 * @author Mariana Victor
	 * @date 19/05/2011
	 */
	public void atualizarDocumentoCobrancaOS(Integer idOrdemServico,
			Integer idCobrancaDocumento)
			throws ErroRepositorioException;	
	
	/**
	 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada 
	 * 
	 * [FS0001] – Validar Ordem de Serviço.
	 * 
	 * @author Vivianne Sousa
	 * @date 24/05/2011
	 */
	public Integer pesquisarOSFiscalizacaoPendente(Integer numeroOS)
			throws ErroRepositorioException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada 
	 * [SB0001] - Selecionar Ordens de Serviço 
	 * 
	 * @author Vivianne Sousa
	 * @date 24/05/2011
	 */
	public Integer recuperaQtdeOSEncerradaConclusaoServico(Integer idRotaGrupoCobranca,Integer idGerencia, 
			Integer idUnidade, Integer idLocalidadeInicial,Integer idLocalidadeFinal, 
			Integer idTipoServico, Integer qtdeDiasEncerramentoOS, Integer idEmpresaContratoServico) throws ErroRepositorioException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada 
	 * [SB0001] - Selecionar Ordens de Serviço 
	 * 
	 * @author Vivianne Sousa
	 * @date 24/05/2011
	 */
	public Integer recuperaQtdeOSEncerradaNaoConclusaoServico(Integer idRotaGrupoCobranca,Integer idGerencia, 
			Integer idUnidade, Integer idLocalidadeInicial,Integer idLocalidadeFinal, 
			Integer idTipoServico, Integer qtdeDiasEncerramentoOS, Integer idEmpresaContratoServico) throws ErroRepositorioException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada 
	 * 
	 * [FS0001] – Validar Ordem de Serviço.
	 * 
	 * @author Vivianne Sousa
	 * @date 26/05/2011
	 */
	public Integer pesquisarEmpresaContratoEmpresaServico(Integer idGrupoCobranca)
			throws ErroRepositorioException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada 
	 * 
	 * [FS0001] – Validar Ordem de Serviço.
	 * 
	 * @author Vivianne Sousa
	 * @date 26/05/2011
	 */
	public Integer pesquisarRotaGrupoCobranca(Integer idGrupoCobranca)
			throws ErroRepositorioException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada 
	 * [SB0002] – Verificar Ordem Serviço  
	 * 
	 * @author Vivianne Sousa
	 * @date 26/05/2011
	 */
	public Integer pesquisarIdMotivoEncerramentoOS(Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada 
	 * [SB0003] - Gerar Várias Ordens de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 26/05/2011
	 */
	public Collection recuperaIdOSEncerradaNaoConclusaoServico(Integer idRotaGrupoCobranca,Integer idGerencia, 
			Integer idUnidade, Integer idLocalidadeInicial,Integer idLocalidadeFinal, 
			Integer idTipoServico, Integer qtdeDiasEncerramentoOS, Integer idEmpresaContratoServico) throws ErroRepositorioException;

	/**
	 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada 
	 * [SB0003] - Gerar Várias Ordens de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 26/05/2011
	 */
	public Collection recuperaIdOSEncerradaConclusaoServico(Integer idRotaGrupoCobranca,Integer idGerencia, 
			Integer idUnidade, Integer idLocalidadeInicial,Integer idLocalidadeFinal, 
			Integer idTipoServico, Integer qtdeDiasEncerramentoOS, Integer idEmpresaContratoServico) throws ErroRepositorioException;
	
	
	/**
	 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada 
	 * [SB0004] – Gerar Ordem de Serviço.
	 * 
	 * @author Vivianne Sousa
	 * @date 27/05/2011
	 */
	public Object[] pesquisarImovelEUnidadeOrganizacional(Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada 
	 * [SB0004] – Gerar Ordem de Serviço.
	 * 
	 * Consulta a ordem de servico pelo id
	 * 
	 * @author Vivianne sousa
	 * @date 30/05/2011
	 * 
	 * @param idOS
	 * @throws ControladorException
	 */
	public Object[] recuperaDadosOSPorId(Integer idOS)throws ErroRepositorioException;
	
	/**
	 * [UC1116] Atualizar Informações da OS para Boletim de Medição
	 * 
	 * @author Vivianne Sousa
	 * @date 01/06/2011
	 */
	public OrdemServicoBoletim recuperaOrdemServicoBoletim(
			Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC1116] Atualizar Informações da OS para Boletim de Medição
	 * 
	 * @author Vivianne Sousa
	 * @date 01/06/2011
	 */
	public String pesquisarNumeroHidrometro(Integer idImovel) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0713] Emitir Ordem de Serviço Seletiva
	 * [SB0002] Gerar TXT 
	 * 
	 * @author Vivianne Sousa
	 * @date 28/06/2011
	 */
	public Collection pesquisarDadosEmitirTxtOsInspecaoAnormalidade(
			Integer quantidadeInicio, Integer quantidadeMaxima,Integer idComandoOrdemSeletiva)
			throws ErroRepositorioException;
	
	/**
	 * [UC0713] Emitir Ordem de Serviço Seletiva
	 * [SB0002] Gerar TXT 
	 * 
	 * @author Vivianne Sousa
	 * @date 29/06/2011
	 */
	public Collection pesquisarAtendimentoMotivoEncerramento()
			throws ErroRepositorioException;
	
	/**
	 * [UC1185] Informar Calibragem
	 * 
	 * @author Thúlio Araújo
	 * @param osProgramacaoCalibragem
	 * @throws ErroRepositorioException
	 */
	public void atualizarInformarCalibragem(OSProgramacaoCalibragem osProgramacaoCalibragem) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0471] Obter Dados dos equipamentos especiais
	 * 
	 * @author Nathalia Santos
	 * @date 29/06/2011
	 * 
	 * @return EquipamentosEspecial
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarEquipeEquipamentosEspeciais(Integer indicadorEquipamentosEspeciais)
			throws ErroRepositorioException;
	
	/**
	 * [UC0713] Emitir Ordem de Serviço Seletiva
	 * [SB0002] Gerar TXT 
	 * 
	 * @author Vivianne Sousa
	 * @date 05/07/2011
	 */
	public String pesquisarAnormalidadeImovelPorNumeroDeOcorrenciasConsecultivas(			
			Collection idAnormalidades, Integer qtdAnormalidades,
			List<String> anoMesOcorrencias, Integer idImovel)throws ErroRepositorioException;
	
	/**
	 * [UC0713] Emitir Ordem de Serviço Seletiva
	 * [SB0002] Gerar TXT 
	 * 
	 * @author Vivianne Sousa
	 * @date 05/07/2011
	 */
	public Collection pesquisarAnormalidadeComandoOSS(Integer idComando)
			throws ErroRepositorioException;
	
	/**
	 * [UC0412] Manter Tipo de Serviço
	 * [SB0003] Atualizar Grau de Importância
	 *
	 * @author Thúlio Araújo
	 * @date 30/06/2011
	 */
	public void atualizarGrauImportanciaServicoTipo(ServicoTipo servicoTipo)
	throws ErroRepositorioException;
	
	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 11/07/2011
	 */
	public Integer pesquisarDadosComandoOSSeletivaCount(
			Integer idEmpresa, Date comandoInicial, Date comandoFinal)
			throws ErroRepositorioException;
	
	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 11/07/2011
	 */
	public Collection pesquisarDadosComandoOSSeletivaResumido(
			Integer idEmpresa, Date dataInicial, Date dataFinal,
			 int numeroIndice,int quantidadeRegistros) throws ErroRepositorioException;
	
	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 12/07/2011
	 */
	public ComandoOrdemSeletiva pesquisarDadosComandoOSSeletiva(
			Integer idComandoOrdemSeletiva) throws ErroRepositorioException;
	
	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 13/07/2011
	 */
	public Collection pesquisarDadosAnormalidadeComandoOSS(
			Integer idComandoOrdemSeletiva) throws ErroRepositorioException ;

	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 13/07/2011
	 */
	public Collection pesquisarDadosCapacidHidrComandoOSS(
			Integer idComandoOrdemSeletiva) throws ErroRepositorioException;	


	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 13/07/2011
	 */
	public Collection pesquisarDadosLigacaoSitComandoOSS(
			Integer idComandoOrdemSeletiva) throws ErroRepositorioException;
	
	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 15/07/2011
	 */
	public ComandoOrdemSeletiva pesquisarComandoOSSeletiva(
			Integer idComandoOrdemSeletiva) throws ErroRepositorioException;
	
	/**
	 * [UC1192] Movimentar OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @date 18/07/2011
	 */
	public Collection<Object[]> pesquisarDadosOSEmitir(Integer idComando,
			Integer numeroOSInicial, Integer numeroOSFinal) throws ErroRepositorioException;
	
	/**
	 * [UC1192] Movimentar OS Seletiva de Inspeção de Anormalidade
	 * [FS0001] - Verificar se ordem de serviço faz parte do comando
	 * 
	 * @author Vivianne Sousa
	 * @date 19/07/2011
	 */
	public Collection verificaSeOSFazParteComando(Integer idComandoOrdemSeletiva,
			List<Integer> numerosOSPesquisar) throws ErroRepositorioException;
	
	/**
	 * [UC1192] Movimentar OS Seletiva de Inspeção de Anormalidade
	 * [FS0003] – Verificar se imóvel faz parte do comando
	 * 
	 * @author Vivianne Sousa
	 * @date 19/07/2011
	 */
	public Collection verificaSeImovelFazParteComando(Integer idComandoOrdemSeletiva,
			List<Integer> numerosImoveisPesquisar) throws ErroRepositorioException;
	
	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * Pesquisa os id's das equipes que ainda possuem OS, para a data
	 * informada, que ainda não foram encaminhadas para o campo.
	 * 
	 * @author Bruno Barros
	 * @date 06/07/2011
	 * 
	 * @param dataRoteiro - Data para a pesquisa das OS
	 * 
	 * @return Collection<Integer> - Coleção com todos os ID's das equipes.
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> 
		pesquisarEquipesOSNaoEnviadas( Collection<Integer> colsEquipes, Date dataRoteiro ) 
			throws ErroRepositorioException;	
	
	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * Pesquisa se ja foi incluido um arquivo de acompanhamento
	 * de serviço para a equipe / data informadas
	 * 
	 * @author Bruno Barros
	 * @date 06/07/2011
	 * 
	 * @param idEquipe - Identificador da equipe
	 * @param dataRoteiro - Data para a pesquisa do arquivo
	 * 
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> 
		pesquisarArquivoTextoAcompanhamentoServico( 
			Integer idUnidadeOrganizacional, 
			Date dataRoteiro ) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * Pesquisa o imei de uma equipe
	 * 
	 * @author Bruno Barros
	 * @date 06/07/2011
	 * 
	 * @param idEquipe - Identificador da equipe
	 * 
	 * @return BigDecimal - Numero do imei da equipe informada
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal 
		pesquisarIMEIEquipe( 
			Integer idEquipe ) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * Pesquisa as OS que ainda não foram enviadas para uma equipe em uma
	 * determinada data
	 * 
	 * @author Bruno Barros
	 * @date 06/07/2011
	 * 
	 * @param idEquipe - id da equipe que terá as os incluidas 
	 * 
	 * @return Collection<OSProgramacao> - Coleção com todos as ordens de servico
	 * a serem incluidas na programação
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> 
	pesquisarOSNaoEnviadas( Integer idEquipe, Date dataProgramacao ) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * Pesquisa o id do Arquivo Texto do Acompanhamento de Serviço
	 * 
	 * @author Bruno Barros
	 * @date 06/07/2011
	 * 
	 * @param idEquipe - Identificador da equipe
	 * @param dataRoteiro - Data do roteiro a ser pesquisado
	 * 
	 * @return Integer - Numero do imei da equipe informada
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer 
		pesquisarIdArquivoTextoAcompanhamentoServico( 
			Integer idEquipe,
			Date dataRoteiro)
			throws ErroRepositorioException;
	
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
	public Collection<?> pesquisarAcompanhamentoArquivosRoteiro( 
			String dataProgramacao,
			String idEmpresa,
			String idSituacao,
			Integer idUnidOrganizacacional) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1199] – Acompanhar Arquivos de Roteiro
	 * 
	 * Pesquisa OS Programacao Acompanhamento Servico
	 * 
	 * @author Thúlio Araújo
	 * @date 21/07/2011
	 * 
	 * @param idArqTextoAcompServico
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOSProgramacaoAcompServico(
			String idArqTextoAcompServico) throws ErroRepositorioException;
	
	/**
	 * [UC1197] Encerrar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @date 21/07/2011
	 */
	public Collection pesquisaOrdemServicoFazParteComando(int quantidadeInicio, 
			Integer idComandoOrdemSeletiva) throws ErroRepositorioException;
	
	/**
	 * [UC1197] Encerrar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @date 21/07/2011
	 */
	public void atualizarDataEncerramentoComando(Integer idComando) throws ErroRepositorioException;
	

	/**
	 * [UC0713] Emitir Ordem de Serviço Seletiva
	 * [SB0002] Gerar TXT 
	 * 
	 * @author Vivianne Sousa
	 * @date 21/07/2011
	 */
	public void atualizarIndicadorGeracaoTxtComando(Integer idComando) throws ErroRepositorioException ;
			
	
	/**
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
	 *
	 * [SB0003] Programação Automática das Ordens de Serviço por Prioridade 
	 * @author Sávio Luiz
	 * @date 19/07/2011
	 */
	public Object[] pesquisarDadosOSCalibragem(Integer idPriorizacaoTipo,Integer idOSProgramaCalibragem,
			Integer faixaPriorizacao) throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
	 *
	 * [SB0004] Recuperar Equipe Pela Ordem de Serviço
	 *  
	 * @author Sávio Luiz
	 * @date 19/07/2011
	 */
	public Collection<Equipe> pequisarEquipesPorUnidade(Integer idUnidade)
			throws ErroRepositorioException;
	

	/**
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
	 *
	 * [SB0004] Recuperar Equipe Pela Ordem de Serviço
	 *  
	 * @author Sávio Luiz
	 * @date 19/07/2011
	 */
	public Object[] pesquisarTempoMedioOSProgramacaoComDataRoteiroUnidade(
			Integer idProgramacaoRoteiro,Integer idEquipe) throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
	 *
	 * @author Sávio Luiz
	 * @date 19/07/2011
	 */
	public Collection<Integer> pesquisarOSFatorPrioridadeDecrescente(Integer idUnidadeOrganizacional)
	throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
	 *
	 * @author Sávio Luiz
	 * @date 19/07/2011
	 */
	public Date pesquisarDataAnteriorProgramacaoRoteiro(Integer idUnidadeOrganizacional)
	throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
	 *
	 * @author Sávio Luiz
	 * @date 19/07/2011
	 */
	public Collection pesquisarOSAcompServicoTransmitidasNaoAtualizadas(Integer idUnidadeOrganizacional,Date dataProgramacao)
	throws ErroRepositorioException;
	

	/**
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
	 *
	 * @author Sávio Luiz
	 * @date 19/07/2011
	 */
	public Collection pesquisarOSAcompServicoNaoENcerradas(Integer idUnidadeOrganizacional,Date dataProgramacao)
	throws ErroRepositorioException;
	
	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * Pesquisa a tabela atendimentopublico.os_prg_acomp_servico retornando
	 * apenas as os que ainda não foram enviadas.
	 * 
	 * @author Bruno Barros
	 * @date 26/07/2011
	 * 
	 * @param idEquipe - id da equipe que terá as os incluidas 
	 * 
	 * @return Collection<OSProgramacaoAcompanhamentoServico> - Coleção com todos as ordens de servico
	 * a serem incluidas na programação
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSProgramacaoAcompanhamentoServico> 
		pesquisarOSAcompanhamentoServico( Integer idEquipe,Date dataRoteiro, boolean arquivoOnLine ) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * Pesquisa a tabela atendimentopublico.os_at_prg_acomp_servico
	 * apenas as os que ainda não foram enviadas.
	 * 
	 * @author Bruno Barros
	 * @date 26/07/2011
	 * 
	 * @param idOSProgramacaoAcompanhamentoServico - id da ativdade programacao 
	 * 
	 * @return Collection<OSAtividadeProgramacaoAcompanhamentoServico> - Coleção com todas as atividades
	 * a serem incluidas na programação
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSAtividadeProgramacaoAcompanhamentoServico> 
		pesquisarAtividadeOSAcompanhamentoServico( Integer idOSProgramacaoAcompanhamentoServico ) 
			throws ErroRepositorioException;	
	
	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @date 26/07/2011
	 */
	public Integer pesquisaOrdemServicoNaoPendenteFazParteComando(Integer idComandoOrdemSeletiva) throws ErroRepositorioException ;
	
	/**
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
	 *
	 * @author Sávio Luiz
	 * @date 19/07/2011
	 */
	public Equipe pesquisarEquipeComEquipamentoEspecial(Integer idUnidadeOrganizacional,Integer idEquipamentoEspecial)
	throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
	 *
	 * [SB0002] Inserir Ordem de Serviço na Programação 
	 * @author Sávio Luiz
	 * @date 19/07/2011
	 */
	public ProgramacaoRoteiro pesquisarProgramacaoRoteiro(Integer idUnidadeOrganizacional,Date dataProgramacao)
	throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
	 *
	 * [SB0002] Inserir Ordem de Serviço na Programação 
	 * @author Sávio Luiz
	 * @date 19/07/2011
	 */
	public void atualizarIndicadorOSProgramada(Integer idOrdemServico)
	throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
	 *
	 * [SB0002] Inserir Ordem de Serviço na Programação 
	 * @author Sávio Luiz
	 * @date 19/07/2011
	 */
    public void excluirOSProgramadas(Integer idUnidadeOrganizacional,Date dataProgramacao)
    	throws ErroRepositorioException;

    /**
	 * [UC0412] Manter Tipo de Serviço
	 * 
	 * Metodo responsável por deletar motivos de
	 * encerramento a partir de um tipo de serviço  
	 * 
	 * @author Raimundo Martins
	 * @date 26/07/2011
	 * 
	 * @param idServicoTipo
	 * 
	 */	
	public void removerServicoTipoMotivoEncerramento(Integer idServicoTipo) throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
	 *
	 * [SB0003] Programação Automática das Ordens de Serviço por Prioridade 
	 * @author Sávio Luiz
	 * @date 19/07/2011
	 */
	public void atualizarFatorPrioridadeOS(Integer idOrdemServico,Integer fatorPrioridade) throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
	 *
	 * [SB0003] Programação Automática das Ordens de Serviço por Prioridade 
	 * @author Sávio Luiz
	 * @date 19/07/2011
	 */
	public Integer pesquisarQuantidadeRAReativacao(Integer idRA) throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
	 *
	 * @author Sávio Luiz
	 * @date 30/07/2011
	 */
	public Collection pequisarUnidadesOrganizacionaisdasEquipes()
			throws ErroRepositorioException;
    
    /**
   	 * [UC1199] Acompanhamento de Arquivos de Roteiro
   	 * 
   	 * 
   	 * 
   	 * @author Thúlio Araújo
   	 * @date 28/07/2011
   	 * 
   	 * @param ids
   	 * @param situacaoNova
   	 */
     public void atualizarArquivoTextoAcompArqRoteiro(Integer id, Integer situacaoNova) throws ErroRepositorioException;
	public Collection<Object[]> pesquisarOSProgramacaoAutomatica(Integer idUnidadeOrganizacional)
	throws ErroRepositorioException;
     
     
     /**
 	 * [UC1192] Movimentar OS Seletiva de Inspeção de Anormalidade
 	 * Verificar se ordem de serviço que faz parte do comando ja esta encerrada
 	 * 
 	 * @author Vivianne Sousa
 	 * @date 02/08/2011
 	 */
 	public Collection verificaSeOSJaEncerrada(List<Integer> numerosOSPesquisar) throws ErroRepositorioException;
 	
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
	public void excluirDadosArquivoAcompanharServicoRoteiroProgramado(Integer idArquivoTexto)
		throws ErroRepositorioException; 	
	
    /**
     * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
     * 
     * Exclue o as os programadas
     * 
     * 
     * @date 03/08/2011
     * @author Bruno Barros
     * 
     * @param idEquipe
     * @param dataProgramacao
     * @throws ErroRepositorioException
     */
	public void excluirOSProgramadasAcompanharServicoRoteiroProgramado( Integer id )
		throws ErroRepositorioException;
	
    /**
     * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
     * 
     * Exclue o as os programadas
     * 
     * 
     * @date 03/08/2011
     * @author Bruno Barros
     * 
     * @param idEquipe
     * @param dataProgramacao
     * @throws ErroRepositorioException
     */
	public void excluirOrdemServicoAtividadeAcompanharServicoRoteiroProgramado( Integer id )
		throws ErroRepositorioException;
	
	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * Pesquisa as OS que foram enviadas para uma equipe em uma
	 * determinada data
	 * 
	 * @author Bruno Barros
	 * @date 06/07/2011
	 * 
	 * @param idEquipe - id da equipe que terá as os incluidas 
	 * 
	 * @return Collection<OSProgramacao> - Coleção com todos as ordens de servico
	 * a serem incluidas na programação
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> 
		pesquisarOSEnviadas( Integer idUnidadeOrganizacional, Date dataProgramacao ) 
			throws ErroRepositorioException;
	
	public Collection<Integer> pesquisarIdsEquipesPorUnidadeOrganizacional(Integer idUnidadeOrganizacional) throws ErroRepositorioException;
	
	/**
	 * [UC1199] – Acompanhar Arquivos de Roteiro
	 * 
	 * Pesquisa a(s) equipe(s) da OS Programacao Acompanhamento Servico 
	 * filtrando pelo identificador da Ordem de Serviço
	 * 
	 * @author Raimundo Martins
	 * @date 09/08/2011
	 * 
	 * @param idOrdemServiço
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Integer> pesquisarEquipeOSProgramacaoAcompServicoPorIdOrdemServico(Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
	 *
	 * [SB0002] Inserir Ordem de Serviço na Programação 
	 * @author Sávio Luiz
	 * @date 19/07/2011
	 */
	public short pesquisarMaiorSequencialOSProgramacao(Integer idProgramacaoRoteiro,Integer idEquipe)
	throws ErroRepositorioException;
	
	/**
	 * [UC1213] Emitir Relatorio de Ordem de Servico de Fiscalizacao
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 06/08/2011
	 * 
	 * @throws ErroRepositorioException
	 */	
	public Collection pesquisarOrdensServicoFiscalizacao(int tipoRelatorio, String periodoInicial, String periodoFinal, String idGerenciaRegional, String idUnidadeNegocios,String idLocalidadeInicial, String idLocalidadeFinal, String situacaoOS, String idOSReferidaRetornoTipo, String aceitacaoDaOS) throws ErroRepositorioException;
	
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
	public Collection pesquisarOSProgramacaoAcompServicoPorEquipeOS(
			Integer idOrdemServico, Date dataProgramacao, Integer idEquipe) throws ErroRepositorioException;
	
	/** --
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
	 *
	 * @author Sávio Luiz
	 * @date 19/07/2011
	 */
	public Integer pesquisarOSAcompServicoAtual(Integer idOrdemServico,Date dataProgramacao)
	throws ErroRepositorioException;
	
	/**
	 * [UC1213] Emitir Relatorio de Ordem de Servico de Fiscalizacao
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 06/08/2011
	 * 
	 * @throws ErroRepositorioException
	 */	
	public OrdemServico pesquisarOrdemServicoFiscalizada(Integer idOrdemServico) throws ErroRepositorioException;
	
	/** --
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
	 *
	 * @author Sávio Luiz
	 * @date 19/07/2011
	 */
	public Integer pesquisarOSProgramacaoAtual(Integer idOrdemServico,Date dataProgramacao)
	throws ErroRepositorioException;
	
	/**
	 * [UC1199] Acompanhamento de Arquivos de Roteiro
	 * 
	 * @author Thúlio Araújo
	 * @date 27/08/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSProgramacaoAcompanhamentoServico> pesquisarOSProgramacaoAcompArquivoComDataRoteiroIdEquipe(
			Date dataRoteiro, Integer idArquivo) throws ErroRepositorioException;
	
	/**
	 * [UC1199] Acompanhamento de Arquivos de Roteiro
	 * 
	 * @author Thúlio Araújo
	 * @date 27/08/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSProgramacaoAcompanhamentoServico> pesquisarOSProgramacaoAcompServicoComSequencialMaior(
			Integer numeroOS, Date dataRoteiro, Integer idArquivo,
			short sequencialReferencia) throws ErroRepositorioException;
	
	/**
	 * [UC1205] - Remanejar ordem de servico
	 * 
	 * Pesquisa as OS que ainda não foram enviadas para uma equipe em uma
	 * determinada data
	 * 
	 * @author Thúlio Araújo
	 * @date 06/07/2011
	 * 
	 * @param idEquipe - id da equipe que terá as os incluidas 
	 * 
	 * @return Collection<OSProgramacao> - Coleção com todos as ordens de servico
	 * a serem incluidas na programação
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> 
	pesquisarOSNaoEnviadasRemanejadas( Integer idEquipe, Date dataProgramacao, Integer idOrdemServico ) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1199] - Acompanhamento de Arquivos de Roteiro
	 * 
	 * Pesquisa os id's das equipes que ainda possuem OS, para a data
	 * informada, que ainda não foram encaminhadas para o campo.
	 * 
	 * @author Thúlio Araújo
	 * @date 06/07/2011
	 * 
	 * @param dataRoteiro - Data para a pesquisa das OS 
	 * 
	 * @return Collection<Integer> - Coleção com todos os ID's das equipes.
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Equipe> 
		pesquisarEquipesOSNaoEnviadasProgramadas(Integer idUnidadeLotacao, Date dataRoteiro, Integer idEquipe ) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1199] - Acompanhamento de Arquivos de Roteiro
	 * 
	 * Pesquisa o Arquivo Texto do Acompanhamento de Serviço por Equipe e Data Roteiro
	 * 
	 * @author Thúlio Araújo
	 * @date 06/07/2011
	 * 
	 * @param idEquipe - Identificador da equipe
	 * @param dataRoteiro - Data do roteiro a ser pesquisado
	 * 
	 * @return Integer - Numero do imei da equipe informada
	 * 
	 * @throws ErroRepositorioException
	 */
	public ArquivoTextoAcompanhamentoServico 
		pesquisarArquivoTextoAcompanhamentoServicoEquipe( 
			Integer idEquipe,
			Date dataRoteiro) 
			throws ErroRepositorioException;
	
	/**
	 * [UCXXX] - Retornar Mensagem Cadastrada para Equipe
	 * 
	 * @author Thúlio Araújo
	 * @date 08/09/2011
	 * 
	 * @param idArquivo - id do Arquivo a ser pesquisada a mensagem
	 * 
	 * @return MensagemAcompanhamentoServico - Objeto de Mensagem
	 * 
	 * @throws ErroRepositorioException
	 */
	public MensagemAcompanhamentoServico 
		retornaMensagemAcompanhamentoArquivosRoteiroImei( 
			Integer idArquivo)
			throws ErroRepositorioException;
	
	/**
	 * [UCXXX] - Retornar Mensagem Cadastrada para Equipe
	 * 
	 * Pesquisar o id do Arquivo Texto do Acompanhamento de Serviço
	 * 
	 * @author Thúlio Araújo
	 * @date 15/09/2011
	 * 
	 * @param imei
	 * @param dataRoteiro - Data do roteiro a ser pesquisado
	 * 
	 * @return Integer - idArquivo
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer 
		pesquisarIdArquivoTextoAcompanhamentoServicoImei( 
			long imei) 
			throws ErroRepositorioException;
	
	public void atualizarSituacaoArquivoTextoAcompanhamentoServico( Integer equipe, short situacao ) throws ErroRepositorioException;
	
	public void atualizarSituacaoProgramacaoOrdemServico( int numeroOS, short situacao ) throws ErroRepositorioException;

	/**
	 * [UC1227] Atualizar Ordens Serviço de Acompanhamento de Celular
	 * 
	 * @autor Sávio Luiz
	 * @date 22/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSProgramacaoAcompanhamentoServico> pesquisarOSProgramacaoAcompServicoPelaDataProgramacao(
			Date dataRoteiro,Integer idEquipe, Integer idUnidadeOrganizacional) throws ErroRepositorioException;
	
	/**
	 * [UC1227] Atualizar Ordens Serviço de Acompanhamento de Celular
	 * 
	 * @autor Sávio Luiz
	 * @date 22/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSAtividadeProgramacaoAcompanhamentoServico> pesquisarOSAtividadeProgramacaoAcompServico(
			Integer idOSProgramacaoAcompServico) throws ErroRepositorioException;
	
	/**
	 * [UC1227] Atualizar Ordens Serviço de Acompanhamento de Celular
	 * 
	 * @autor Sávio Luiz
	 * @date 22/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSAtividadeMaterialProgramacaoAcompanhamentoServico> pesquisarOSAtividadeMaterialProgramacaoAcompanhamentoServico(
			Integer idOSAtividadeProgramacaoAcompanhamentoServico) throws ErroRepositorioException ;
	
	/**
	 * [UC1227] Atualizar Ordens Serviço de Acompanhamento de Celular
	 * 
	 * @autor Sávio Luiz
	 * @date 22/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOsAtivMaterialExecucao(
			Integer idOrdemServicoAtividade) throws ErroRepositorioException;
	
	/**
	 * [UC1227] Atualizar Ordens Serviço de Acompanhamento de Celular
	 * 
	 * @autor Sávio Luiz
	 * @date 22/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSAtividadeExecucaoAcompanhamentoServico> pesquisarOSAtividadeExecucaolProgramacaoAcompanhamentoServico(
			Integer idOSAtividadeProgramacaoAcompanhamentoServico) throws ErroRepositorioException;
	
	/**
	 * [UC1227] Atualizar Ordens Serviço de Acompanhamento de Celular
	 * 
	 * @autor Sávio Luiz
	 * @date 22/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOsAtivPeriodoExecucao(
			Integer idOrdemServicoAtividade) throws ErroRepositorioException;
	
	/**
	 * [UC1227] Atualizar Ordens Serviço de Acompanhamento de Celular
	 * 
	 * @autor Sávio Luiz
	 * @date 22/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarCodigoSituacaoOS(Integer numeroOS, short situacao) throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
	 *
	 * @author Sávio Luiz
	 * @date 19/07/2011
	 */
	public Collection<OSProgramacaoAcompanhamentoServico> pesquisarOSAcompServicoNaoENcerradasMotivo(Integer idUnidadeOrganizacional)
	throws ErroRepositorioException;
	
	/**
	 * [UC1225] Incluir Dados Acompanhamento de Serviço
	 * 
	 * [FS0001] - Verificar existência do número da ordem de serviço
	 * 
	 * Caso o numero da ordem de serviço não exista na tabela ORDEM_SERVICO,
	 * exibir a mensagem ?Ordem de Serviço inexistente: <<número da Ordem de Serviço>>?
	 *
	 * e retornar para próximo tipo 1 do arquivo de retorno.
	 * 
	 * @author Thúlio Araújo
	 * @date 23/09/2011
	 * 
	 * @param helperLaco
	 * @throws ControladorException
	 */
	public boolean verificarExistenciaOrdemServico(
			Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC1225] Incluir Dados Acompanhamento de Serviço
	 * 
	 * Pesquisa OS Programacao Acompanhamento Servico por idOs e dataProgramacao
	 * 
	 * @author Thúlio Araújo
	 * @date 23/09/2011
	 * 
	 * @param idOs, dataProgramacao
	 * @return boolean - data da OS Programacao Acompanhamento Servico
	 * @throws ControladorException
	 */
	public OSProgramacaoAcompanhamentoServico pesquisarOSProgramacaoAcompServicoPorIdOs(
			Integer idOrdemServico, Date dataProgramacao) throws ErroRepositorioException;
	
	/**
	 * [UC1225] Incluir Dados Acompanhamento de Serviço
	 * 
	 * Pesquisa OS Atividade Programacao Acompanhamento Servico por idOs, dataProgramacao, idAtividade 
	 * 
	 * @author Thúlio Araújo
	 * @date 23/09/2011
	 * 
	 * @param idOrdemServico, dataProgramacao, idAtividade
	 * @return Collection<OSAtividadeProgramacaoAcompanhamentoServico>
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarOSAtividadeProgramacaoAcompServicoPorIdOs(
			Integer idOrdemServico, Date dataProgramacao, Integer idAtividade) throws ErroRepositorioException;
	
	/**
	 * [UC1225] Incluir Dados Acompanhamento de Serviço
	 * 
	 * Excluir os dados da OSAtividadeExecucaoAcompanhamentoServico para cada id da tabela
	 * OsAtividadeProgramcaoAcompanhamentoServico
	 * 
	 * @author Thúlio Araújo
	 * @date 26/09/2011
	 * 
	 * @param Collection<Integer> isOsAtividadeProgramacaoAcompanhamentoServico
	 * @throws ErroRepositorioException
	 */
	public void excluirOsAtividadeExecucaoAcompahamentoServico(Collection<Integer> isOsAtividadeProgramacaoAcompanhamentoServico) throws ErroRepositorioException;
	
	/**
	 * [UC1225] Incluir Dados Acompanhamento de Serviço
	 * 
	 * Excluir os dados da tabela OSAtividadeMaterialProgramacaoAcompanhamentoServico para cada id da tabela
	 * OsAtividadeProgramcaoAcompanhamentoServico
	 * 
	 * @author Thúlio Araújo
	 * @date 26/09/2011
	 * 
	 * @param Collection<Integer> isOsAtividadeProgramacaoAcompanhamentoServico
	 * @throws ErroRepositorioException
	 */
	public void excluirOsAtividadeMaterialProgramacaoAcompahamentoServico(Collection<Integer> isOsAtividadeProgramacaoAcompanhamentoServico) throws ErroRepositorioException;
	
	/**
	 * [UC1225] Incluir Dados Acompanhamento de Serviço
	 * 
	 * Excluir os dados da tabela OSAtividadeProgramacaoAcompanhamentoServico para cada id da tabela
	 * OsAtividadeProgramcaoAcompanhamentoServico
	 * 
	 * @author Thúlio Araújo
	 * @date 26/09/2011
	 * 
	 * @param Collection<Integer> isOsAtividadeProgramacaoAcompanhamentoServico
	 * @throws ErroRepositorioException
	 */
	public void excluirOsAtividadeProgramacaoAcompahamentoServico(Collection<Integer> isOsAtividadeProgramacaoAcompanhamentoServico) throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarOrdensServicoProgramadas() throws ErroRepositorioException;
}
