package gcom.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.bean.RegistroAtendimentoDevolucaoValoresHelper;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.portal.QuestionarioSatisfacaoCliente;
import gcom.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao;
import gcom.atendimentopublico.registroatendimento.RAReiteracao;
import gcom.atendimentopublico.registroatendimento.RaDadosAgenciaReguladora;
import gcom.atendimentopublico.registroatendimento.RaEncerramentoComando;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.Tramite;
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
import gcom.relatorio.atendimentopublico.RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

public interface ControladorRegistroAtendimentoLocal extends
		javax.ejb.EJBLocalObject {

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Data Prevista = Data v�lida obtida a partir da Data do Atendimento +
	 * n�mero de dias previstos para a Especifica��o do tipo de Solicita��o
	 * (STEP_NNDIAPRAZO da tabela SOLICITACAO_TIPO_ESPECIFICACAO).
	 * 
	 * Caso o sistema deva sugerir a unidade destino para o primeiro
	 * encaminhamento do Registro de Atendimento (PARM_ICSUGESTAOTRAMITE=1 na
	 * tabela SISTEMA_PARAMETROS)
	 * 
	 * Caso a Especifica��o esteja associada a uma unidade (UNID_ID da tabela
	 * SOLICITACAO_TIPO_ESPECIFICACAO com o valor diferente de nulo), definir a
	 * unidade destino a partir da Especifica��o (UNID_ID e UNID_DSUNIDADE da
	 * tabela UNIDADE_ORGANIZACIONAL com UNID_ICTRAMITE=1 e UNID_ID=UNID_ID da
	 * tabela SOLICITACAO_TIPO_ESPECIFICACAO com SETP_ID=Id da Especifica��o
	 * selecionada).
	 * 
	 * [SB0003] Define Data Prevista e Unidade Destino da Especifica��o
	 * 
	 * @author Raphael Rossiter
	 * @date 25/07/2006
	 * 
	 * @param login
	 * @return UnidadeOrganizacional
	 * @throws ControladorException
	 */
	public DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper definirDataPrevistaUnidadeDestinoEspecificacao(
			Date dataAtendimento, Integer idSolicitacaoTipoEspecificacao)
			throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Valida os dados gerais do atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 27/07/2006
	 * 
	 * @param dataAtendimento,
	 *            horaAtendimento, tempoEsperaInicial, tempoEsperaFinal,
	 *            idUnidadeOrganizacional
	 * @return void
	 * @throws ControladorException
	 */
	public void validarInserirRegistroAtendimentoDadosGerais(
			String dataAtendimento, String horaAtendimento,
			String tempoEsperaInicial, String tempoEsperaFinal,
			String idUnidadeOrganizacional, String numeroRAManual)
			throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso a Especifica��o exija a matr�cula do im�vel (STEP_ICMATRICULA com o
	 * valor correspondente a um na tabela SOLICITACAO_TIPO_ESPECIFICACAO),
	 * obrigat�rio; caso contr�rio, opcional
	 * 
	 * @author Raphael Rossiter
	 * @date 28/07/2006
	 * 
	 * @param idSolicitacaoTipoEspecificacao
	 * @return boolean
	 * @throws ControladorException
	 */
	public boolean verificarExigenciaImovelPelaEspecificacao(
			Integer idSolicitacaoTipoEspecificacao) throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Obter e habilitar/desabilitar os Dados da Identifica��o do Local da
	 * Ocorr�ncia de acordo com as situa��es abaixo descritas no caso de uso
	 * 
	 * @author Raphael Rossiter
	 * @date 28/07/2006
	 * 
	 * @param idImovel,
	 *            idSolicitacaoTipoEspecificacao, idSolicitacaoTipo,
	 *            levantarExceptionImovelInexistente
	 * @return ObterDadosIdentificacaoLocalOcorrenciaHelper
	 * @throws ControladorException
	 */
	public ObterDadosIdentificacaoLocalOcorrenciaHelper obterDadosIdentificacaoLocalOcorrencia(
			Integer idImovel, Integer idSolicitacaoTipoEspecificacao,
			Integer idSolicitacaoTipo,
			boolean levantarExceptionImovelInexistente)
			throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso exista registro de atendimento pendente para o im�vel (existe
	 * Ocorr�ncia na tabela REGISTRO_ATENDIMENTO com IMOV_ID=matr�cula do im�vel
	 * e RGAT_CDSITUACAO=1)
	 * 
	 * [SB0021] Verifica exist�ncia de Registro de Atendimento Pendente para o
	 * im�vel
	 * 
	 * @author Raphael Rossiter
	 * @date 31/07/2006
	 * 
	 * @param idImovel
	 * @return boolean
	 * @throws ControladorException
	 */
	public boolean verificaExistenciaRAPendenteImovel(Integer idImovel)
			throws ControladorException;

	/**
	 * [UC0399] Inserir Tipo de Solicita��o com Especifica��o
	 * 
	 * [SB0001] - Gerar Tipo Solicita��o com Especifica��es
	 * 
	 * @author S�vio Luiz
	 * @date 01/08/2006
	 * 
	 * @param solicitacaoTipo,
	 *            colecaoSolicitacaoTipoEspecificacao, usuarioLogado
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer inserirTipoSolicitacaoEspecificacao(
			SolicitacaoTipo solicitacaoTipo,
			Collection colecaoSolicitacaoTipoEspecificacao,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * [UC0406] Filtrar Registro de Atendimento
	 * 
	 * @author Leonardo Regis
	 * @date 07/08/2006
	 * 
	 * @param filtroRA
	 * @return void
	 * @throws ControladorException
	 */
	public Collection<RegistroAtendimento> filtrarRegistroAtendimento(
			FiltrarRegistroAtendimentoHelper filtroRA)
			throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0002] Habilita/Desabilita Munic�pio, Bairro, �rea do Bairro e
	 * Divis�o de Esgoto
	 * 
	 * @author Raphael Rossiter
	 * @date 02/08/2006
	 * 
	 * @param idSolicitacaoTipo
	 * @return ObterDadosIdentificacaoLocalOcorrenciaHelper
	 * @throws ControladorException
	 */
	public ObterDadosIdentificacaoLocalOcorrenciaHelper habilitarGeograficoDivisaoEsgoto(
			Integer idSolicitacaoTipo) throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso o sistema deva sugerir a unidade destino para o primeiro
	 * encaminhamento do Registro de Atendimento (PARM_ICSUGESTAOTRAMITE=1 na
	 * tabela SISTEMA_PARAMETROS), definir a Unidade Destino da Localidade de
	 * acordo com as regras abaixo. Caso a Especifica��o n�o esteja associada a
	 * uma unidade (UNID_ID da tabela SOLICITACAO_TIPO_ESPECIFICACAO com o valor
	 * nulo): Caso o Tipo de Solicita��o n�o seja relativo � �rea de esgoto
	 * (SOTG_ICESGOTO da tabela SOLICITACAO_TIPO_GRUPO com o valor
	 * correspondente a dois para SOTG_ID=SOTG_ID da tabela SOLICITACAO_TIPO com
	 * SOTP_ID=Id do Tipo de Solicita��o selecionado), definir a unidade destino
	 * a partir da localidade informada/selecionada (UNID_ID e UNID_DSUNIDADE da
	 * tabela UNIDADE_ORGANIZACIONAL com UNID_ICTRAMITE=1 e UNID_ID=UNID_ID da
	 * tabela LOCALIDADE_SOLIC_TIPO_GRUPO com LOCA_ID=Id da Localidade e
	 * SOTG_ID=SOTG_ID da tabela SOLICITACAO_TIPO com SOTP_ID=Id do Tipo de
	 * Solicita��o selecionado) [FS0018 Verificar exist�ncia de unidade
	 * centralizadora].
	 * 
	 * [SB0005] Define Unidade Destino da Localidade
	 * 
	 * @author Raphael Rossiter
	 * @date 04/08/2006
	 * 
	 * @param idLocalidade,
	 *            idSolicitacaoTipo, idSolicitacaoTipoEspecificacao
	 * @return UnidadeOrganizacional
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional definirUnidadeDestinoLocalidade(
			Integer idSolicitacaoTipoEspecificacao, Integer idLocalidade,
			Integer idSolicitacaoTipo, boolean solicitacaoTipoRelativoAreaEsgoto)
			throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso o Tipo de Solicita��o seja relativo � �rea de esgoto (SOTG_ICESGOTO
	 * da tabela SOLICITACAO_TIPO_GRUPO com o valor correspondente a um para
	 * SOTG_ID=SOTG_ID da tabela SOLICITACAO_TIPO com SOTP_ID=Id do Tipo de
	 * Solicita��o selecionado). Caso a quadra esteja preenchida, obter a
	 * divis�o de esgoto da quadra (DVES_ID e DVES_DSDIVISAOESGOTO da tabela
	 * DIVISAO_ESGOTO com DVES_ID=DVES_ID da tabela SISTEMA_ESGOTO com
	 * SESG_ID=SESG_ID da tabela BACIA com BACI_ID=BACI_ID da tabela QUADRA com
	 * QDRA_ID=Id da quadra informada/selecionada).
	 * 
	 * [SB0006] Obt�m divis�o de Esgoto
	 * 
	 * @author Raphael Rossiter
	 * @date 07/08/2006
	 * 
	 * @param solicitacaoTipoRelativoAreaEsgoto,
	 *            idQuadra
	 * @return UnidadeOrganizacional
	 * @throws ControladorException
	 */
	public DivisaoEsgoto obterDivisaoEsgoto(Integer idQuadra,
			boolean solicitacaoTipoRelativoAreaEsgoto)
			throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso informe a divis�o de esgoto: Caso tenha informado a quadra e a mesma
	 * n�o perten�aa a divis�o de esgoto informada (Id da divis�o de esgoto �
	 * diferente de DVES_ID da tabela QUADRA com QDRA_ID=Id da quadra
	 * informada).
	 * 
	 * Caso tenha informado o setor comercial sem a quadra e o setor comercial
	 * n�o perten�aa � divis�o de esgoto informada (Id da divis�o de esgoto �
	 * diferente de todos os DVES_ID da tabela QUADRA com STCM_ID=Id do setor
	 * comercial informado).
	 * 
	 * Caso tenha informado a localidade sem o setor comercial e a localidade
	 * n�o perten�a � divis�o de esgoto informada (Id da divis�o de esgoto �
	 * diferente de todos os DVES_ID da tabela QUADRA com STCM_ID=STCM_ID da
	 * tabela SETOR_COMERCIAL com LOCA_ID=Id da localidade informada).
	 * 
	 * [FS0013] Verificar compatibilidade entre divis�o de esgoto e
	 * localidade/setor/quadra
	 * 
	 * @author Raphael Rossiter
	 * @date 08/08/2006
	 * 
	 * @param idLocalidade,
	 *            idSetorComercial, idQuadra, idDivisaoEsgoto
	 * @return void
	 * @throws ControladorException
	 */
	public void verificarCompatibilidadeDivisaoEsgotoLocalidadeSetorQuadra(
			Integer idLocalidade, Integer idSetorComercial, Integer idQuadra,
			int idDivisaoEsgoto) throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso o sistema deva sugerir a unidade destino para o primeiro
	 * encaminhamento do Registro de Atendimento (PARM_ICSUGESTAOTRAMITE=1 na
	 * tabela SISTEMA_PARAMETROS).
	 * 
	 * Caso a Especifica��o n�o esteja associada a uma unidade (UNID_ID da
	 * tabela SOLICITACAO_TIPO_ESPECIFICACAO com o valor nulo):
	 * 
	 * Caso o Tipo de Solicita��o n�o seja relativo �rea de esgoto
	 * (SOTG_ICESGOTO da tabela SOLICITACAO_TIPO_GRUPO com o valor
	 * correspondente a um para SOTG_ID=SOTG_ID da tabela SOLICITACAO_TIPO com
	 * SOTP_ID=Id do Tipo de Solicita��o selecionado).

	 * Caso a localidade informada/selecionada para o RA 
	 * esteja associada ao grupo  de esgoto(
	 * existe ocorrencia na tabela LOCALIDADE_SOLIC_TIPO_GRUPO 
	 * para LOCA_ID = id da Localidade e SOTG_ID=SOTG_ID da tabela
	 * SOLICITACAO_TIPO com SOTP_ID = id do tipo de solicitacaoselecionado), 
	 * definir a unidade destino a partir da localidade(
	 * UNID_ID e UNID_DSUNIDADE da tabela UNIDADE_ORGANIZACIONAL com UNID_ICTRAMITE=1
	 * e UNID_ID=UNID_ID da tabela LOCALIDADE_SOLIC_TIPO_GRUPO)
	 * 
	 * Caso contrario, Definir a unidade destino a partir da divis�o de esgoto
	 * informada/selecionada (UNID_ID e UNID_DSUNIDADE da tabela
	 * UNIDADE_ORGANIZACIONAL com UNID_ICTRAMITE=1 e UNID_ID=UNID_ID da tabela
	 * DIVISAO_ESGOTO com DVES_ID=Id da divis�o selecionada) [FS0018 Verificar
	 * exist�ncia de unidade centralizadora].
	 * 
	 * [SB0007] Define Unidade Destino da divis�o de Esgoto
	 * 
	 * @author Raphael Rossiter
	 * @date 08/08/2006
	 * 
	 * @param idDivisaoEsgoto,
	 *            idSolicitacaoTipoEspecificacao,
	 *            solicitacaoTipoRelativoAreaEsgoto
	 * @return UnidadeOrganizacional
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional definirUnidadeDestinoDivisaoEsgoto(
			Integer idSolicitacaoTipoEspecificacao, Integer idDivisaoEsgoto,
			boolean solicitacaoTipoRelativoAreaEsgoto,
			Integer idLocalidade, Integer idSolicitacaoTipo)
			throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso a unidade destino informada n�o possa receber registros de
	 * atendimento (UNID_ICTRAMITE=2 na tabela UNIDADE_ORGANIZACIONAL com
	 * UNID_ID=Id da unidade destino informada).
	 * caso a unidade destino informada nao esteja ATIVA (UNID_IUSO=2 na
	 * tabela UNIDADE_ORGANIZACIONAL com UNID_ID= id da unidade destino
	 * informada).
	 * 
	 * [FS0021] - Verificar possibilidade de encaminhamento para a unidade
	 * destino
	 * 
	 * @author Raphael Rossiter, Anderson Italo
	 * @date 08/08/2006, 17/07/2009
	 * 
	 * @param UnidadeOrganizacional
	 * @return void
	 * @throws ControladorException
	 */
	public void verificaPossibilidadeEncaminhamentoUnidadeDestino(
			UnidadeOrganizacional unidadeDestino) throws ControladorException;

	/**
	 * [UC0399] Inserir Tipo de Solicita��o com Especifica��es
	 * 
	 * Verifica se o Servi�o tipo tem como Servi�o automatico gera��o
	 * autom�tica.
	 * 
	 * [SF0003] Validar Tipo de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 08/08/2006
	 * 
	 * @param idServicoTipo
	 * @throws ErroRepositorioException
	 */
	public void verificarServicoTipoReferencia(Integer idServicoTipo)
			throws ControladorException;

	/**
	 * [UC0399] Inserir Tipo de Solicita��o com Especifica��es
	 * 
	 * Verifica se na cole��o existe algum ordem de execu��o .
	 * 
	 * [SF0004] Validar valor ordem execu��o parte
	 * 
	 * @author S�vio Luiz
	 * @date 08/08/2006
	 * 
	 * @param colecaoEspecificacaoServicoTipo,ordemExecucao
	 * @throws ErroRepositorioException
	 */
	public void verificarExistenciaOrdemExecucao(
			Collection colecaoEspecificacaoServicoTipo, Short ordemExecucao)
			throws ControladorException;

	/**
	 * [UC0399] Inserir Tipo de Solicita��o com Especifica��es
	 * 
	 * Verifica se na cole��o existe algum ordem de execu��o fora da
	 * ordem(1,2,3,4,5,6).Ex.:n�o exista numero 2.
	 * 
	 * [SF0004] Validar valor ordem execu��o 2 parte
	 * 
	 * @author S�vio Luiz
	 * @date 08/08/2006
	 * 
	 * @param colecaoEspecificacaoServicoTipo
	 * @throws ErroRepositorioException
	 */
	public void verificarOrdemExecucaoForaOrdem(
			Collection colecaoEspecificacaoServicoTipo)
			throws ControladorException;

	/**
	 * [UC0420] Obter Descri��o da Situa��o do RA
	 * 
	 * @author Leonardo Regis
	 * @date 09/08/2006
	 * 
	 * @param idRa
	 * @return String
	 * @throws ControladorException
	 */
	/*
	 * public String obterDescricaoSituacaoRA(Integer idRa) throws
	 * ControladorException;
	 */

	/**
	 * [UC0418] Obter Unidade Atual do RA
	 * 
	 * @author Leonardo Regis
	 * @date 09/08/2006
	 * 
	 * @param idRa
	 * @return String
	 * @throws ControladorException
	 */
	/*
	 * public UnidadeOrganizacional obterUnidadeAtualRA(Integer idRa) throws
	 * ControladorException;
	 */

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso exista registro de atendimento pendente para o im�vel com a mesma
	 * Especifica��o (existe Ocorr�ncia na tabela REGISTRO_ATENDIMENTO com
	 * IMOV_ID=matr�cula do im�vel e STEP_ID=Id da Especifica��o selecionada e
	 * RGAT_CDSITUACAO=1).
	 * 
	 * [FS0020] - Verificar exist�ncia de registro de atendimento para o im�vel
	 * com a mesma Especifica��o
	 * 
	 * @author Raphael Rossiter
	 * @date 31/07/2006
	 * 
	 * @param idImovel,
	 *            idSolicitacaoTipoEspecificacao
	 * @return void
	 * @throws ControladorException
	 */
	public void verificarExistenciaRAImovelMesmaEspecificacao(Integer idImovel,
			Integer idSolicitacaoTipoEspecificacao) throws ControladorException;

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
			throws ControladorException;

	/**
	 * [UC0418] Obter Unidade Atual do RA
	 * 
	 * Este caso de uso permite obter a unidade atual de um registro de
	 * atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 03/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional obterUnidadeAtualRA(
			Integer idRegistroAtendimento) throws ControladorException;

	/**
	 * [UC0419] Obter Indicador de Autoriza��o para Manuten��o do RA
	 * 
	 * Este caso de uso obt�m o indicador de Autoriza��o para Manuten��o do RA,
	 * ou seja, se o usu�rio tem Autoriza��o para efetuar a Manuten��o do RA
	 * 
	 * 
	 * @author Ana Maria
	 * @date 08/08/2006
	 * 
	 * 
	 * @param idUnidadeOrganizacional
	 * @param idUusuario
	 * @throws ControladorException
	 */
	public Short obterIndicadorAutorizacaoManutencaoRA(
			Integer idUnidadeOrganizacional, Integer idUsuario)
			throws ControladorException;

	/**
	 * [UC0420] Obter Descri��o da Situa��o da RA
	 * 
	 * Este caso de uso permite obter a Descri��o de um registro de atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 04/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public ObterDescricaoSituacaoRAHelper obterDescricaoSituacaoRA(
			Integer idRegistroAtendimento) throws ControladorException;

	/**
	 * [UC0421] Obter Unidade de Atendimento do RA
	 * 
	 * Este caso de uso permite obter a unidade de atendimento de um registro de
	 * atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 04/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional obterUnidadeAtendimentoRA(
			Integer idRegistroAtendimento) throws ControladorException;

	/**
	 * [UC0422] Obter Endere�o da Ocorr�ncia do RA
	 * 
	 * Este caso de uso permite obter o Endere�o da Ocorr�ncia de um registro de
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
	public String obterEnderecoOcorrenciaRA(Integer idRegistroAtendimento)
			throws ControladorException;

	/**
	 * [UC0423] Obter Endere�o do Solicitante do RA
	 * 
	 * Este caso de uso permite obter o Endere�o do solicitante de um registro
	 * de atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 07/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public String obterEnderecoSolicitanteRA(
			Integer idRegistroAtendimentoSolicitante)
			throws ControladorException;
	
	/**
	 * [UC0433] Obter Registro de Atendimento Associado
	 * 
	 * Este caso de uso permite obter o registro de atendimento associado a
	 * outro
	 * 
	 * 
	 * @author Ana Maria
	 * @date 08/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public ObterRAAssociadoHelper obterRAAssociadoConsultarRA(Integer idRegistroAtendimento)
			throws ControladorException;

	/**
	 * [UC0433] Obter Registro de Atendimento Associado
	 * 
	 * Este caso de uso permite obter o registro de atendimento associado a
	 * outro
	 * 
	 * 
	 * @author Ana Maria
	 * @date 08/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public ObterRAAssociadoHelper obterRAAssociado(Integer idRegistroAtendimento)
			throws ControladorException;

	/**
	 * [UC0434] Obter Unidade de Encerramento do RA
	 * 
	 * Este caso de uso permite obter a unidade de encerramento de um registro
	 * de atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 07/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional obterUnidadeEncerramentoRA(
			Integer idRegistroAtendimento) throws ControladorException;

	/**
	 * [UC0445] Obter Nome do Solicitante do RA
	 * 
	 * Este caso de uso permite obter o nome do solicitante de um registro de
	 * atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 09/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimentoSolicitante
	 * @throws ControladorException
	 */
	public String obterNomeSolicitanteRA(
			Integer idRegistroAtendimento)
			throws ControladorException;

	/**
	 * 
	 * [UC0425] - Reiterar Registro de Atendimento
	 * 
	 * @author lms
	 * @date 10/08/2006
	 */
	public Integer reiterarRegistroAtendimento(
			RegistroAtendimento registroAtendimento, Usuario usuario,
			RAReiteracao raReiteracao,Collection colecaoFonesSolicitante)
			throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0020] Verifica Situa��o do im�vel e Especifica��o
	 * 
	 * @author Raphael Rossiter
	 * @date 10/08/2006
	 * 
	 * @param idSolicitacaoTipoEspecificacao
	 * @return void
	 * @throws ControladorException
	 */
	public void verificarSituacaoImovelEspecificacao(Imovel imovel,
			Integer idSolicitacaoTipoEspecificacao) throws ControladorException;

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * Verificar existencia ordem de Servi�o para o registro atendimento
	 * pesquisado
	 * 
	 * @author S�vio Luiz
	 * @date 11/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Integer verificarOrdemServicoParaRA(Integer idRegistroAtendimento)
			throws ControladorException;

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * Pesquisar os parametros para atualizar o registro atendimento escolhido
	 * pelo usu�rio
	 * 
	 * @author S�vio Luiz
	 * @date 11/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] pesquisarParmsRegistroAtendimento(
			Integer idRegistroAtendimento) throws ControladorException;

	/**
	 * [UC0446] Consultar Tr�mites
	 * 
	 * Retorna a cole��o de Tramites do registro de atendimento
	 * 
	 * @author Leonardo Regis
	 * @date 11/08/2006
	 * 
	 * @param idRA
	 * @return Collection<Tramite>
	 * @throws ControladorException
	 */
	public Collection<Tramite> obterTramitesRA(Integer idRA)
			throws ControladorException;

	/**
	 * [UC0447] Consultar RA Solicitantes
	 * 
	 * Retorna a cole��o de registro de atendimento solicitantes
	 * 
	 * @author Rafael Pinto
	 * @date 14/08/2006
	 * 
	 * @param idRA
	 * @return Collection<RegistroAtendimentoSolicitante>
	 * @throws ControladorException
	 */
	public Collection<RegistroAtendimentoSolicitante> obterRASolicitante(
			Integer idRA) throws ControladorException;

	/**
	 * [UC0431] Consultar Ordens de Servi�o do Registro Atendimento
	 * 
	 * Retorna a cole��o de OS's do registro de atendimento
	 * 
	 * @author Leonardo Regis
	 * @date 11/08/2006
	 * 
	 * @param idRA
	 * @return Collection<OrdemServico>
	 * @throws ControladorException
	 */
	public Collection<OrdemServico> obterOSRA(Integer idRA)
			throws ControladorException;

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * [FS0012] - Verificar possibilidade de atualiza��o do registro de
	 * atendimento
	 * 
	 * @author S�vio Luiz
	 * @date 14/08/2006
	 * 
	 * @param idRA,idUsuarioLogado
	 * @throws ControladorException
	 */

	public UnidadeOrganizacional verificarPossibilidadeAtualizacaoRA(
			Integer idRA, Integer idUsuarioLogado) throws ControladorException;

	/**
	 * [UC0409] Obter Indicador de exist�ncia de Hidr�metro na Liga��o de �gua e
	 * no Po�o
	 * 
	 * Este caso de uso obt�m o indicador de exist�ncia de Hidr�metro na liga��o
	 * de �gua e no po�o
	 * 
	 * 
	 * @author Ana Maria
	 * @date 14/08/2006
	 * 
	 * 
	 * @param idImovel
	 * @throws ControladorException
	 */
	public ObterIndicadorExistenciaHidrometroHelper obterIndicadorExistenciaHidrometroLigacaoAguaPoco(
			Integer idImovel) throws ControladorException;

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * [SB0024] - Verificar registro de Atendimento Sem Identifica��o do Local
	 * de Ocorr�ncia
	 * 
	 * @author S�vio Luiz
	 * @date 15/08/2006
	 * 
	 * @param idRA,idUsuarioLogado
	 * @throws ControladorException
	 */

	public int verificarRASemIdentificacaoLO(Integer idImovel, Integer idRA)
			throws ControladorException;

	/**
	 * [UC0452] Obter Dados do Registro de Atendimento
	 * 
	 * Este caso de uso permite obter dados de um registro de atendimento
	 * 
	 * 
	 * @author Ana Maria
	 * @date 14/08/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public ObterDadosRegistroAtendimentoHelper obterDadosRegistroAtendimento(
			Integer idRegistroAtendimento) throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * REGISTRO_ATENDIMENTO com IMOV_ID=nulo e RGAT_CDSITUACAO=1 e
	 * LGBR_ID=LGBR_ID do Endere�o da Ocorr�ncia e LGCP_ID=LGCP_ID do Endere�o
	 * da Ocorr�ncia e STEP_ID=Id da Especifica��o selecionada e STEP_ID=STEP_ID
	 * da tabela SOLICITACAO_TIPO_ESPECIFICACAO com SOTP_ID=SOTP_ID da tabela
	 * SOLICITACAO_TIPO para SOTP_ICFALTAAGUA com o valor correspondente a dois
	 * 
	 * [SB0008] Verifica exist�ncia de Registro de Atendimento Pendente para o
	 * Local da Ocorr�ncia
	 * 
	 * @author Raphael Rossiter
	 * @date 15/08/2006
	 * 
	 * @param idSolicitacaoTipoEspecificacao,
	 *            idLogradouroBairro, idLogradouroCep
	 * @return RegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	public RegistroAtendimento verificaExistenciaRAPendenteLocalOcorrencia(
			Integer idSolicitacaoTipoEspecificacao, Integer idLogradouroBairro,
			Integer idLogradouroCep) throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * REGISTRO_ATENDIMENTO com IMOV_ID=nulo e RGAT_CDSITUACAO=1 e
	 * LGBR_ID=LGBR_ID do Endere�o da Ocorr�ncia e LGCP_ID=LGCP_ID do Endere�o
	 * da Ocorr�ncia e STEP_ID=Id da Especifica��o selecionada e STEP_ID=STEP_ID
	 * da tabela SOLICITACAO_TIPO_ESPECIFICACAO com SOTP_ID=SOTP_ID da tabela
	 * SOLICITACAO_TIPO para SOTP_ICFALTAAGUA com o valor correspondente a dois
	 * 
	 * @author Raphael Rossiter
	 * @date 16/08/2006
	 * 
	 * @param idSolicitacaoTipoEspecificacao,
	 *            idLogradouroCep, idLogradouroBairro
	 * @return RegistroAtendimentoPendenteLocalOcorrenciaHelper
	 * @throws ErroRepositorioException
	 */
	public RegistroAtendimentoPendenteLocalOcorrenciaHelper pesquisarRAPendenteLocalOcorrencia(
			Integer idSolicitacaoTipoEspecificacao, Integer idLogradouroCep,
			Integer idLogradouroBairro) throws ControladorException;

	/**
	 * [UC0413] Pesquisar Tipo de Servi�o
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
	 * 
	 * 
	 * @author Leandro Cavalcanti
	 * @date 17/08/2006
	 */
	public Collection<ServicoTipo> filtrarST(ServicoTipo servicoTipo,
			Collection colecaoAtividades, Collection colecaoMaterial,
			String valorServicoInicial, String valorServicoFinal,
			String tempoMedioExecucaoInicial, String tempoMedioExecucaoFinal)
			throws ControladorException;

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * [SB0017] - Verificar registro de Atendimento de falta de �gua
	 * 
	 * @author S�vio Luiz
	 * @date 16/08/2006
	 * 
	 * @param idRA
	 * @throws ControladorException
	 */
	public VerificarRAFaltaAguaHelper verificarRegistroAtendimentoFaltaAgua(
			Integer idRegistroAtendimento, Date dataAtendimento,
			Integer idBairroArea, Integer idBairro, Integer idEspecificacao,
			Short indFaltaAgua, Integer indMatricula, String continua)
			throws ControladorException;

	/**
	 * 
	 * [UC0430] - Gerar Ordem de Servi�o
	 * 
	 * @author lms
	 * @date 11/08/2006
	 */
	public RegistroAtendimento validarRegistroAtendimento(Integer idRA)
			throws ControladorException;

	/**
	 * [UC0427] Tramitar Registro de Atendimento
	 * 
	 * Validar Tramita��o
	 * 
	 * [FS0001] Verificar se o RA est� cancelado ou bloqueado. [FS0002]
	 * Verificar situa��es das OS(ordem de Servi�o) associadas ao RA [FS0003]
	 * Verificar se o tipo de Solicita��o � Tarifa Social [FS0008] Validar
	 * Unidade de Destino
	 * 
	 * @author Leonardo Regis
	 * @date 18/08/2006
	 * 
	 * @param tramite
	 * @throws ControladorException
	 */
	public void validarTramitacao(Tramite tramite, Usuario usuario) throws ControladorException;

	/**
	 * [UC0427] Tramitar Registro de Atendimento
	 * 
	 * Tramitar
	 * 
	 * @author Leonardo Regis
	 * @date 18/08/2006
	 * 
	 * @param tramite
	 * @param dataConcorrente
	 * @throws ControladorException
	 */
	public void tramitar(Tramite tramite, Date dataConcorrente)
			throws ControladorException;

	/**
	 * [UC0406] Filtrar Registro de Atendimento
	 * 
	 * Caso exista registro de atendimento que est�o na unidade atual informada
	 * (existe Ocorr�ncia na tabela REGISTRO_ATENDIMENTO com TRAMITE=Id da
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
			throws ControladorException;

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * [SB0019] - Exibe Registros de Atendimentos de falta de �gua no im�vel
	 * 
	 * @author S�vio Luiz
	 * @date 21/08/2006
	 * 
	 * @param idRA
	 * @throws ControladorException
	 */
	public RAFaltaAguaPendenteHelper carregarObjetoRAFaltaAguaPendente(
			Integer idRegistroAtendimento, Integer idBairroArea,
			Integer idEspecificacao) throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso a matr�cula do im�vel da Aba Dados do Local da Ocorr�ncia tenha sido
	 * informada e o Cliente informado n�o seja um cliente do im�vel (inexiste
	 * Ocorr�ncia na tabela CLIENTE_IMOVEL com CLIE_ID=Id do cliente e
	 * IMOV_ID=matr�cula do im�vel e CLIM_DTRELACAOFIM com o valor nulo).
	 * 
	 * [FS0027] Verificar informa��o do im�vel
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2006
	 * 
	 * @param idCliente,
	 *            idImovel
	 * @return Cliente
	 * @throws ErroRepositorioException
	 */
	public Cliente verificarInformacaoImovel(Integer idCliente,
			Integer idImovel, boolean levantarException)
			throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso esteja adicionando um novo solicitante e o cliente j� seja um
	 * solicitante do registro de atendimento (existe Ocorr�ncia na tabela
	 * REGISTRO_ATENDIMENTO_SOLICITANTE com RGAT_ID=RGAT_ID do registro de
	 * atendimento selecionado e CLIE_ID=Id do Cliente informado).
	 * 
	 * [FS0012] Verificar exist�ncia do cliente solicitante
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2006
	 * 
	 * @param idRegistroAtendimento,
	 *            idCliente
	 * @throws ErroRepositorioException
	 */
	public void verificarExistenciaClienteSolicitante(
			Integer idRegistroAtendimento, Integer idCliente)
			throws ControladorException;

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * Obter e habilitar/desabilitar os Dados da Identifica��o do Local da
	 * Ocorr�ncia de acordo com as situa��es abaixo descritas no caso de uso
	 * 
	 * [SB0004] Obt�m e Habilita/Desabilita Dados da Identifica��o do Local da
	 * Ocorr�ncia e Dados do Solicitante
	 * 
	 * @author S�vio Luiz
	 * @date 28/07/2006
	 * 
	 * @param idImovel,
	 *            idSolicitacaoTipoEspecificacao, idSolicitacaoTipo,
	 *            levantarExceptionImovelInexistente
	 * @return ObterDadosIdentificacaoLocalOcorrenciaHelper
	 * @throws ControladorException
	 */
	public ObterDadosIdentificacaoLocalOcorrenciaHelper obterDadosIdentificacaoLocalOcorrenciaAtualizar(
			Integer idImovel, Integer idSolicitacaoTipoEspecificacao,
			Integer idSolicitacaoTipo, Integer idRegistroAtendimento,
			boolean levantarExceptionImovelInexistente)
			throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso esteja adicionando um novo solicitante e a unidade j� seja um
	 * solicitante do registro de atendimento (existe Ocorr�ncia na tabela
	 * REGISTRO_ATENDIMENTO_SOLICITANTE com RGAT_ID=RGAT_ID do registro de
	 * atendimento selecionado e UNID_ID=Id da Unidade informada).
	 * 
	 * [FS0026] Verificar exist�ncia da unidade solicitante
	 * 
	 * @author Raphael Rossiter
	 * @date 23/08/2006
	 * 
	 * @param idRegistroAtendimento,
	 *            idUnidade
	 * @throws ControladorException
	 */
	public void verificarExistenciaUnidadeSolicitante(
			Integer idRegistroAtendimento, Integer idUnidade)
			throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento e [UC0408] Atualizar Registro de
	 * Atendimento
	 * 
	 * 
	 * [FS0040] Verificar preenchimento campos. 2 ABA
	 * 
	 * @author S�vio Luiz
	 * @date 24/08/2006
	 * 
	 * @throws ControladorException
	 */
	public void validarCamposObrigatoriosRA_2ABA(String idImovel,
			String pontoReferencia, String idMunicipio,
			String descricaoMunicipio, String cdBairro, String descricaoBairro,
			String idAreaBairro, String idlocalidade,
			String descricaoLocalidade, String cdSetorComercial,
			String descricaoSetorComercial, String numeroQuadra,
			String idDivisaoEsgoto, String idUnidade, String descricaoUnidade,
			String idLocalOcorrencia, String idPavimentoRua,
			String idPavimentoCalcada, String descricaoLocalOcorrencia,
			String imovelObrigatorio, String pavimentoRuaObrigatorio,
			String pavimentoCalcadaObrigatorio,
			String solicitacaoTipoRelativoFaltaAgua,
			String solicitacaoTipoRelativoAreaEsgoto,
			String desabilitarMunicipioBairro, String indRuaLocalOcorrencia,
			String indCalcadaLocalOcorrencia, Integer idEspecificacao, Integer idRAAtualizacao, Collection colecaoEndereco,
			SolicitacaoTipoEspecificacao especificacao,
			Collection colecaoPagamento, Usuario usuarioLogado) throws ControladorException;

	/**
	 * [UC0435] Encerrar Registro de Atendimento
	 * 
	 * Validar Pr�-Encerramento
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
	public void validarPreEncerramentoRA(
			RegistroAtendimento registroAtendimento, Usuario usuarioLogado,
			Short indicadorAutorizacaoManutencaoRA) throws ControladorException;
	
	/**
	 * [UC0435] Encerrar Registro de Atendimento
	 * 
	 * Validar Pr�-Encerramento
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
	public void validarPreEncerramentoRASemTarifaSocial(
			RegistroAtendimento registroAtendimento, Usuario usuarioLogado,
			Short indicadorAutorizacaoManutencaoRA) throws ControladorException ;

	/**
	 * 
	 * [UC0435] - Encerrar Registro de Atendimento
	 * 
	 * [FS003] Validar RA de Refer�ncia
	 * 
	 * @author Leonardo Regis
	 * @date 26/08/2006
	 */
	public RegistroAtendimento validarRAReferencia(Integer idRA,
			Integer idRAReferencia) throws ControladorException;

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
	public void validarEncerramentoRA(RegistroAtendimento registroAtendimento)
			throws ControladorException;

	/**
	 * [UC0435] Encerrar Registro de Atendimento
	 * 
	 * @author Leonardo Regis
	 * @date 26/08/2006
	 * 
	 * @param registroAtendimento
	 * @param registroAtendimentoUnidade
	 * @param dataConcorrencia
	 */
	public void encerrarRegistroAtendimento(
			RegistroAtendimento registroAtendimento,
			RegistroAtendimentoUnidade registroAtendimentoUnidade,
			Usuario usuarioLogado,
			Integer idDebitoTipo, 
            BigDecimal valorDebito, 
            Integer qtdeParcelas, 
            String percentualCobranca,
            Boolean confirmadoGeracaoNovoRA,
            String urlBotaoVoltar,boolean encerrarDebitoACobrar) 
			throws ControladorException ;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [FS0030] Verificar preenchimento dos dados de Identifica��o do
	 * solicitante
	 * 
	 * @author Raphael Rossiter
	 * @date 24/08/2006
	 * 
	 * @throws ControladorException
	 */
	public void verificaDadosSolicitante(Integer idCliente,
			Integer idUnidadeSolicitante, Integer idFuncionario,
			String nomeSolicitante, Collection colecaoEndereco,
			Collection colecaoFone, Short indicadorClienteEspecificacao,
			Integer idImovel, Integer idRegistroAtendimento, Integer idEspecificacao, 
			Integer idMeioSolicitacao) throws ControladorException;

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
	public void inserirRegistroAtendimentoSolicitante(
			Integer idRegistroAtendimento, Integer idCliente,
			Collection colecaoEndereco, String pontoReferencia,
			String nomeSolicitante, boolean novoSolicitante,
			Integer idUnidadeSolicitante, Integer idFuncionario,
			Collection colecaoFone, String protocoloAtendimento,
			String habilitarCampoSatisfacaoEmail, String enviarEmailSatisfacao, String enderecoEmail) throws ControladorException;

	/**
	 * 
	 * passa os parametros do registro atendimento solicitante e a cole��o de
	 * fones do solicitante e retorna um objeto de Registro Atendimento
	 * Solicitante
	 * 
	 * @author S�vio Luiz
	 * @date 02/09/2006
	 * 
	 * @throws ControladorException
	 */
	public RegistroAtendimentoSolicitante inserirDadosNoRegistroAtendimentoSolicitante(
			Integer idRegistroAtendimento, Integer idCliente,
			Collection colecaoEndereco, String pontoReferencia,
			String nomeSolicitante, Integer idUnidadeSolicitante,
			Integer idFuncionario, Collection colecaoFone, String fonePadrao)
			throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0017] - Verificar registro de Atendimento de falta de �gua
	 * 
	 * @author Raphael Rossiter
	 * @date 16/08/2006
	 * 
	 * @throws ControladorException
	 */
	public VerificarRAFaltaAguaHelper verificarRegistroAtendimentoFaltaAguaInserir(
			Date dataAtendimento, Integer idBairroArea, Integer idBairro,
			Integer idEspecificacao, Short indFaltaAgua, Integer indMatricula,
			String continua) throws ControladorException;

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
	public Integer[] inserirRegistroAtendimento(RADadosGeraisHelper raDadosGeraisHelper, RALocalOcorrenciaHelper raLocalOcorrenciaHelper, RASolicitanteHelper raSolicitanteHelper)
			throws ControladorException;

	/**
	 * [UC0440] Consultar Programa��o de Manuten��o
	 * 
	 * Caso exista Programa��o de Manuten��o de uma determinada �rea de Bairro
	 * 
	 * @author R�mulo Aur�lio
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

	public Collection consultarProgramacaoManutencao(String idMunicipio,
			String idBairro, String areaBairro, String mesAnoReferencia)
			throws ControladorException;

	/**
	 * [UC0440] Consultar Programa��o de Abastecimento
	 * 
	 * Caso exista Programa��o de Abastecimento de uma determinada �rea de
	 * Bairro
	 * 
	 * @author R�mulo Aur�lio
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
	public Collection consultarProgramacaoAbastecimento(String idMunicipio,
			String idBairro, String areaBairro, String mesAnoReferencia)
			throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso a Especifica��o esteja associada a um tipo de Servi�o (SVTP_ID da
	 * tabela SOLICITACAO_TIPO_ESPECIFICACAO com o valor diferente de nulo).
	 * (AUTOM�TICA)
	 * 
	 * [SB0030] Gerar Ordem de Servi�o
	 * 
	 * @author Raphael Rossiter
	 * @date 29/08/2006
	 * 
	 * @param idSolicitacaoTipoEspecificacao
	 * @return boolean
	 * @throws ControladorException
	 */
	public boolean gerarOrdemServicoAutomatica(
			Integer idSolicitacaoTipoEspecificacao) throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso a Especifica��o possa gerar alguma ordem de Servi�o
	 * (STEP_ICGERACAOORDEMSERVICO da tabela SOLICITACAO_TIPO_ESPECIFICACAO com
	 * o valor correspondente a um). (OPCIONAL) [SB0030] Gerar Ordem de
	 * Servi�o
	 * 
	 * @author Raphael Rossiter
	 * @date 29/08/2006
	 * 
	 * @param idSolicitacaoTipoEspecificacao
	 * @return boolean
	 * @throws ControladorException
	 */
	public boolean gerarOrdemServicoOpcional(
			Integer idSolicitacaoTipoEspecificacao) throws ControladorException;

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * [SB0028] Atualizar Registro de Atendimento (REGISTRO_ATENDIMENTO)
	 * 
	 * @author S�vio Luiz
	 * @date 30/08/2006
	 * 
	 * @throws ControladorException
	 */
	public Integer[] atualizarRegistroAtendimento(Integer idRA,
			short indicadorAtendimentoOnLine, String dataAtendimento,
			String horaAtendimento, String tempoEsperaInicial,
			String tempoEsperaFinal, Integer idMeioSolicitacao,
			Integer idSolicitacaoTipoEspecificacao, String dataPrevista,
			String observacao, Integer idImovel,
			String descricaoLocalOcorrencia, Integer idSolicitacaoTipo,
			Collection colecaoEndereco, String pontoReferenciaLocalOcorrencia,
			Integer idBairroArea, Integer idLocalidade,
			Integer idSetorComercial, Integer idQuadra,
			Integer idDivisaoEsgoto, Integer idLocalOcorrencia,
			Integer idPavimentoRua, Integer idPavimentoCalcada,
			Integer idUnidadeAtendimento, Usuario usuarioLogado,
			Integer imovelObrigatorio, Date ultimaAlteracao,
			Collection colecaoRASolicitante,
			Collection colecaoRASolicitanteRemovida, Integer idServicoTipo,
			Integer especificacaoNaBase,Integer idUnidadeAtual,
			BigDecimal nnCoordenadaNorte,BigDecimal nnCoordenadaLeste,
			Collection colecaoRegistroAtendimentoAnexo,
			Collection colecaoRegistroAtendimentoConta,
			Collection colecaoRegistroAtendimentoContaRemover,Collection colecaoPagamento,
			BigDecimal nnDiametro
			
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
	public Date definirDataPrevistaRA(Date dataAtendimento,
			Integer idSolicitacaoTipoEspecificacao) throws ControladorException;

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
	public UnidadeOrganizacional definirUnidadeDestino(
			Integer idSolicitacaoTipoEspecificacao, Integer idLocalidade,
			Integer idSolicitacaoTipo,
			boolean solicitacaoTipoRelativoAreaEsgoto, Integer idDivisaoEsgoto)
			throws ControladorException;

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
	public Integer[] reativarRegistroAtendimento(RegistroAtendimento ra,
			Integer idUnidadeAtendimento, Integer idUsuarioLogado,
			Integer idCliente, Integer idRaSolicitante,
			Integer idUnidadeDestino, String parecerUnidadeDestino,
			Integer idSolicitacaoTipo) throws ControladorException;

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
	public SolicitacaoTipoEspecificacao pesquisarTipoEspecificacaoFaltaAguaGeneralizada()
			throws ControladorException;

	/**
	 * [UC0456] Elaborar Roteiro de Programa��o de Ordens de Servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 06/09/2006
	 */
	public Tramite recuperarTramiteMaisAtualPorUnidadeDestino(Integer idUnidade)
			throws ControladorException;

	/**
	 * [UC0426] Reativar Registro de Atendimento
	 * 
	 * [FS0001]Valida possibilidade de reativa��o
	 * 
	 * @author Ana Maria
	 * @date 29/08/2006
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public void validaPossibilidadeReativacaoRA(Integer idRegistroAtendimento,
			Integer idUsuario) throws ControladorException;

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * pesquisa os fones do regsitro atendimento solicitante e joga na cole��o
	 * de ClientesFones
	 * 
	 * @author S�vio Luiz
	 * @date 05/09/2006
	 * 
	 * @return idRASolicitante
	 * @throws ControladorException
	 */
	public Collection pesquisarParmsFoneRegistroAtendimentoSolicitante(
			Integer idRASolicitante) throws ControladorException;

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * [FS0030] Verificar preenchimento dos dados de identifica��o do
	 * solicitante
	 * 
	 * @author S�vio Luiz
	 * @date 07/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void verificaDadosSolicitanteAtualizar(Integer idCliente,
			Integer idUnidadeSolicitante, Integer idFuncionario,
			String nomeSolicitante, Collection colecaoEndereco,
			Collection colecaoFone, Short indicadorClienteEspecificacao,
			Integer idImovel, Integer idRegistroAtendimento,
			Integer idRASolicitante) throws ControladorException;

	/**
	 * [UC0408] Inserir Registro de Atendimento
	 * 
	 * Caso esteja adicionando um novo solicitante e o cliente j� seja um
	 * solicitante do registro de atendimento (existe Ocorr�ncia na tabela
	 * REGISTRO_ATENDIMENTO_SOLICITANTE com RGAT_ID=RGAT_ID do registro de
	 * atendimento selecionado e CLIE_ID=Id do Cliente informado e o registro
	 * atendimento solicitante for diferente do que est� sendo atualizado).
	 * 
	 * [FS0027] Verificar exist�ncia do cliente solicitante
	 * 
	 * @author S�vio Luiz
	 * @date 21/08/2006
	 * 
	 * @param idRegistroAtendimento,
	 *            idCliente
	 * @throws ErroRepositorioException
	 */
	public void verificarExistenciaClienteSolicitanteAtualizar(
			Integer idRegistroAtendimento, Integer idCliente,
			Integer idRASolicitante) throws ControladorException;

	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * Caso esteja adicionando um novo solicitante e a unidade j� seja um
	 * solicitante do registro de atendimento (existe Ocorr�ncia na tabela
	 * REGISTRO_ATENDIMENTO_SOLICITANTE com RGAT_ID=RGAT_ID do registro de
	 * atendimento selecionado e UNID_ID=Id da Unidade informada e RASO_ID<>id
	 * do Registro atendimento solicitante).
	 * 
	 * [FS0018] Verificar exist�ncia da unidade solicitante
	 * 
	 * @author S�vio Luiz
	 * @date 07/09/2006
	 * 
	 * @param idRegistroAtendimento,
	 *            idUnidade
	 * @throws ControladorException
	 */
	public void verificarExistenciaUnidadeSolicitanteAtualizar(
			Integer idRegistroAtendimento, Integer idUnidade,
			Integer idRASolicitante) throws ControladorException;

	/**
	 * [UC0456] Elaborar Roteiro de Programa��o de Ordens de Servi�o
	 * 
	 * 
	 * @author Rafael Pinto
	 * @date 13/09/2006
	 * 
	 * @param idRa
	 * @return dataPrevisaoAtual
	 * @throws ControladorException
	 */
	public Date obterDataAgenciaReguladoraPrevisaoAtual(Integer idRa)
			throws ControladorException;

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
	public Collection consultarRegistroAtendimentoImovel(Integer idImovel,
			String situacao) throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0025] Verifica Registro de Atendimento de �gua Generalizada
	 * 
	 * @author Raphael Rossiter
	 * @date 28/08/2006
	 * 
	 * @throws ControladorException
	 */
	public RegistroAtendimentoFaltaAguaGeneralizadaHelper verificarRegistroAtendimentoFaltaAguaGeneralizafa(
			Integer idEspecificacao, Integer idBairroArea)
			throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0015] Verifica Registro de Atendimento Encerrado para o Local da
	 * Ocorr�ncia
	 * 
	 * @author Raphael Rossiter
	 * @date 28/08/2006
	 * 
	 * @throws ControladorException
	 */
	public RegistroAtendimentoEncerradoLocalOcorrenciaHelper verificarRegistroAtendimentoEncerradoLocalOcorrencia(
			Integer idImovel, Integer idEspecificacao,
			Integer idLogradouroBairro, Integer idLogradouroCep)
			throws ControladorException;

	/**
	 * Pesquisa os dados do Registro Atendimento Solicitante para o Relat�rio de
	 * OS
	 * 
	 * @author Rafael Corr�a
	 * @date 17/10/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */
	public Object[] pesquisarDadosRegistroAtendimentoSolicitanteRelatorioOS(
			Integer idRegistroAtendimento) throws ControladorException;

	/**
	 * Pesquisa o telefone principal do Registro Atendimento Solicitante para o
	 * Relat�rio de OS
	 * 
	 * @author Rafael Corr�a
	 * @date 17/10/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */

	public String pesquisarSolicitanteFonePrincipal(
			Integer idRegistroAtendimento) throws ControladorException;

	/**
	 * 
	 * Pesquisar o Endere�o descritivo do RA a partir do seu id
	 * 
	 * [UC0482] - Obter Endere�o Abreviado da Ocorr�ncia do RA
	 * 
	 * @author Rafael Corr�a
	 * @date 18/10/2006
	 * 
	 * @param idRA
	 * @return String
	 * @throws ControladorException
	 */

	public String obterEnderecoDescritivoRA(Integer idRA)
			throws ControladorException;

	/**
	 * [UC0482] - Obter Endere�o Abreviado da Ocorr�ncia do RA
	 * 
	 * Pesquisa o Endere�o abreviado da Ocorr�ncia do RA
	 * 
	 * @author Rafael Corr�a
	 * @date 17/10/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ControladorException
	 */

	public String obterEnderecoAbreviadoOcorrenciaRA(Integer idRA)
			throws ControladorException;

	/**
	 * Caso n�o exista para o im�vel RA encerrada por execu��o com Especifica��o
	 * da Solicita��o que permita a manunten��o de im�vel
	 * 
	 * @author Rafael Santos
	 * @since 26/10/2006
	 * 
	 */
	public RegistroAtendimento verificarExistenciaRegistroAtendimento(Integer idImovel,
			String mensagemErro, char codigoConstante)
			throws ControladorException;

	/**
	 * Caso n�o exista para o im�vel RA encerrada por execu��o com Especifica��o
	 * da Solicita��o No caso de Tarifa Social
	 * 
	 * @author Rafael Santos
	 * @since 26/10/2006
	 * 
	 */
	public void verificarExistenciaRegistroAtendimentoTarifaSocial(
			Integer idImovel, String mensagemErro) throws ControladorException;

	/**
	 * 
	 * [UC0430] - Gerar Ordem de Servi�o
	 * 
	 * @author lms
	 * @date 11/08/2006
	 */
	public void verificarUnidadeUsuario(
			RegistroAtendimento registroAtendimento, Usuario usuario)
			throws ControladorException;

	/**
	 * [UC0494] Gerar Numera��o de RA Manual
	 * 
	 * @author Raphael Rossiter
	 * @date 06/11/2006
	 * 
	 * @throws ControladorException
	 */
	public GerarNumeracaoRAManualHelper GerarNumeracaoRAManual(
			Integer quantidade, Integer idUnidadeOrganizacional)
			throws ControladorException;

	/**
	 * [UC0404] - Manter Especifica��o da Situa��o do im�vel
	 * 
	 * [SB0001] Atualizar Crit�rio de Cobran�a
	 * 
	 * @author Rafael Pinto
	 * @created 09/11/2006
	 * 
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public void atualizarEspecificacaoSituacaoImovel(
			EspecificacaoImovelSituacao especificacaoImovelSituacao,
			Collection colecaoEspecificacaoCriterios,
			Collection colecaoEspecificacaoCriteriosRemovidas, Usuario usuario)
			throws ControladorException;

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
	public RegistroAtendimento verificarRegistroAtendimentoTarifaSocial(
			String idRegistroAtendimento) throws ControladorException;

	/**
	 * [UC0401] Atualizar Tipo de Solicita��o com Especifica��o
	 * 
	 * [SB0001] - Atualizar Tipo Solicita��o com Especifica�es
	 * 
	 * @author R�mulo Aur�lio
	 * @date 01/08/2006
	 * 
	 * @param solicitacaoTipo,
	 *            colecaoSolicitacaoTipoEspecificacao, usuarioLogado
	 * @return Integer
	 * @throws ControladorException
	 */

	public Integer atualizarTipoSolicitacaoEspecificacao(
			SolicitacaoTipo solicitacaoTipo,
			Collection colecaoSolicitacaoTipoEspecificacao,
			Usuario usuarioLogado,
			Collection colecaoSolicitacaoTipoEspecificacaoRemovidos)
			throws ControladorException;
	
	
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
	public boolean clienteObrigatorioInserirRegistroAtendimento(Integer idEspecificacao) 
		throws ControladorException ;
	
	/**
	 * [UC00503]Tramitar Conjunto de Registro de Atendimento
	 *
	 * [SB0003]Incluir o Tramite
	 *
	 * @author Ana Maria
	 * @date 16/01/2007
	 * 
	 */
	public void tramitarConjuntoRA(Collection tramites) throws ControladorException ;
	/**
	 * [UC00503]Tramitar Conjunto de Registro de Atendimento
	 * 
	 * [FS0006] Valida Data
	 * [FS0007] Valida Hora
	 * [FS0008] Valida Unidade Destino	
	 * 
	 * @author Ana Maria
	 * @date 16/01/2007
	 * 
	 */
	public void validarConjuntoTramitacao(String[] ids, Date dataHoraTramitacao, Integer idUnidadeDestino,Usuario usuario) throws ControladorException;	
	/**
	 * [UC00503]Tramitar Conjunto de Registro de Atendimento
	 *
	 * [FS0002] Verificar as situa��es das OS associadas ao RA
	 * [FS0003] Verificar se o tipo de Solicita��o � Tarifa Social
	 * 
	 * @author Ana Maria
	 * @date 16/01/2007
	 * 
	 */	
	public void validarRATramitacao(String[] ids, Integer idUsuario) 
			throws ControladorException;
	
	/**
	 * [UC00069] Manter Dados Tarifa Social
	 * 
	 * [FS00001] Verificar Registro Atendimento
	 * 
	 * @author Rafael Corr�a
	 * @date 05/02/2007
	 * 
	 * @param idRegistroAtendimento
	 * @return
	 * @throws ControladorException
	 */
	public RegistroAtendimento verificarRegistroAtendimentoManterTarifaSocial(
			String idRegistroAtendimento) throws ControladorException;
	
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Caso a especifica��o informada para o RA tem indicativo que � para verificar d�bito 
	 * (STEP_ICVERIFICADEBITO da tabela SOLICITACAO_TIPO_ESPECIFICACAO com valor igual a SIM (1)), 
	 * o sistema dever� verificar se o im�vel informado tenha d�bito <<incluir>> 
	 * [UC0067] Obter D�bito do Im�vel ou Cliente (passando o im�vel). 
	 * [FS0043] - Verifica im�vel com d�bito.
	 * 
	 * [SB0032] - Verifica se o im�vel informado tem d�bito.
	 * 
	 * [FS0043] - Verifica im�vel com d�bito
	 * 
	 * @author Raphael Rossiter
	 * @date 19/02/2006
	 * 
	 * @param idSolicitacaoTipoEspecificacao
	 * @return boolean
	 * @throws ControladorException
	 */
	public void verificarImovelComDebitos(
			Integer idSolicitacaoTipoEspecificacao, Integer idImovel) throws ControladorException ;
	
	
	
	/**
	 * Atualiza logradouroCep de um ou mais im�veis  
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
			LogradouroCep logradouroCepNovo) throws ControladorException ;
	
	
	/**
	 * Atualiza logradouroBairro de um ou mais im�veis  
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
			LogradouroBairro logradouroBairroNovo) throws ControladorException ;
	
	/**
	 * Atualiza logradouroCep de um ou mais im�veis  
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
			LogradouroCep logradouroCepNovo) throws ControladorException ;
	
	
	/**
	 * Atualiza logradouroBairro de um ou mais im�veis  
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
			LogradouroBairro logradouroBairroNovo) throws ControladorException ;
	
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
	public Integer filtrarRegistroAtendimentoTamanho(
			FiltrarRegistroAtendimentoHelper filtroRA)
			throws ControladorException ;
	
	/**
	 * Consultar Observacao Registro Atendimento
	 * Solicitacao da CAER
	 * 
	 * @author Rafael Pinto
	 * @date 14/03/2007
	 */
	public Collection<RegistroAtendimento> pesquisarObservacaoRegistroAtendimento(Integer matriculaImovel,
			Date dataInicialAtendimento,Date dataFinalAtendimento)
			throws ControladorException ;
	/**
	 * M�todo que gera o resumo dos Registro de Atendimento
	 * 
	 * [UC0567] - Gerar Resumo Registro Atendimento
	 * 
	 * @author Thiago Ten�rio
	 * @date 30/04/2007
	 * 
	 */
	public void gerarResumoRegistroAtendimento(int idLocalidade,
			int idFuncionalidadeIniciada, Integer anoMesReferencia) throws ControladorException;
	
	/**
	 * [UC0459] Informar Dados da Agencia Reguladora
	 *
	 * @author K�ssia Albuquerque
	 * @date 09/04/2007
	 */
	
	
	public Integer informarDadosAgenciaReguladora(RaDadosAgenciaReguladora raDadosAgenciaReguladora,
			Collection collectionRaDadosAgenciaReguladoraFone,Usuario usuarioLogado) throws ControladorException;
	
	
	/**
	 * Procura a quantidade de dias de prazo  
	 * 
	 * [UC0459] Informar Dados da Agencia Reguladora
	 * 
	 * @author K�ssia Albuquerque
	 * @date 19/04/2007
	 * 
	 */
	
	public Integer procurarDiasPazo(Integer raId) throws ControladorException ;
	
	/**
	 * Permite comandar um encerramento de RA
	 * 
	 * [UC0735] Comandar Encerramento de Registros de Atendimento
	 * 
	 * @author Rafael Corr�a
	 * @param usuarioLogado
	 * @date 28/01/2008
	 * 
	 */
	public Integer comandarEncerramentoRA(
			RaEncerramentoComando raEncerramentoComando, Collection<SolicitacaoTipoEspecificacao> colecaoEspecificacoes, Usuario usuarioLogado)
			throws ControladorException;
	
	/**
	 * [UC0736] Executar Comando de Encerramento de Registros de Atendimento
	 * 
	 * M�todo respons�vel pelo encerramento dos RAs
	 * 
	 * @author Rafael Corr�a
	 * @date 28/01/2007
	 * 
	 * @param raEncerramentoComando
	 * @throws ControladorException
	 */
	public void executarComandoEncerramentoRA(RaEncerramentoComando raEncerramentoComando,
			int idFuncionalidadeIniciada, Integer idLocalidade) throws ControladorException;
	
  	/**
  	 * Este caso de uso permite obter o telefone do 
  	 * solicitante de um registro de atendimento.
  	 *
  	 * [UC0719] Obter Telefone do Solicitante do RA
  	 *
  	 * @author Pedro Alexandre
  	 * @date 26/12/2007
  	 *
  	 * @param idRASolicitante
  	 * @return
  	 * @throws ControladorException
  	 */
  	public String obterTelefoneSolicitanteRA(Integer idRASolicitante) throws ControladorException ;
  	
  	
  	/**
	 * [UC0435] Encerrar Registro de Atendimento
	 * 
	 * Calcular valor da presta��o
	 * 
	 * Posi��o[0] = valorPrestacao
	 * Posi��o[1] = taxaJurosFinanciamento
	 * 
	 * autor: Raphael Rossiter
	 * data: 07/03/2008
	 */
	public BigDecimal[] calcularValorPrestacaoAtendimentoPublico(short indicadorCobrarJuros, 
			BigDecimal valorDebito, Integer qtdeParcelas, String percentualCobranca)
			throws ControladorException;

	/**
	 * @author Vivianne Sousa
	 * @date 30/05/2008
	 *
	 * @param tramite
	 * @param dataConcorrenciaRA
	 * @param usuario
	 * @param colecaoOrdemServicoPavimento
	 * @param colecaoOrdemServicoMovimento
	 */
	public void tramitarRAExportandoOSPrestadoras(
			Tramite tramite, Date dataConcorrenciaRA,
			Usuario usuario, 
			Collection colecaoOrdemServicoPavimento, Collection colecaoOrdemServicoMovimento) 
			throws ControladorException;
	
	
	/**
	 * [UC0503]Tramitar Conjunto de Registro de Atendimento
	 * 
	 * @author Vivianne Sousa
	 * @date 03/06/2008
	 *
	 * @param tramite
	 * @param dataConcorrenciaRA
	 * @param usuario
	 * @param colecaoOrdemServicoPavimento
	 * @param colecaoOrdemServicoMovimento
	 */
	public void tramitarConjuntoRAExportandoOSPrestadoras(
			Collection tramites, Collection colecaoOrdemServicoPavimento,
			Collection colecaoOrdemServicoMovimento) 
			throws ControladorException;

	/**
	 * [UC0736] Executar Comando de Encerramento de Registros de Atendimento
	 * 
	 * Pesquisa a cole��o de ids das localidades que possuem Registro de Atendimento
	 *
	 * @author Pedro Alexandre
	 * @date 16/06/2008
	 *
	 * @return
  	 * @throws ControladorException
  	 */
  	public Collection<Integer> pesquisarIdsLocalidadesRA() throws ControladorException ;
    
    /**
     * [UC0457] Encerra Ordem de Servi�o
     *
     * @author Vivianne Sousa
     * @date 11/07/2008
     *
     * @param idRA
     * @return
     * @throws ErroRepositorioException
     */
    public Integer obterUnidadeOrigemDoUltimoTramiteRA(Integer idRA)  throws ControladorException;
    
    /**
	 * Verificar exist�ncia de registro de atendimento para o im�vel sem levantar exce��o
	 *
	 * @author Raphael Rossiter
	 * @date 21/07/2008
	 *
	 * @param idImovel
	 * @param mensagemErro
	 * @param codigoConstante
	 * @return RegistroAtendimento
	 * @throws ControladorException
	 */
	public RegistroAtendimento verificarExistenciaRegistroAtendimentoSemLevantarExcecao(Integer idImovel,
			char codigoConstante) throws ControladorException;
	
    /**
     * [UC0496] Filtrar Relatorio de Gestao do Registro de atendimento
     * 
     * @author Victor Cisneiros
     * @date 20/06/2008
     */
    public List<GestaoRegistroAtendimentoHelper> filtrarGestaoRA(
    		FiltrarGestaoRAHelper filtro) throws ControladorException;
    
    /**
     * [UC0497] Gerar Relatorio Resumo de Solicitacoes de RA por Unidade
     * 
     * @author Victor Cisneiros
     * @date 14/11/2008
     */
    public List<GestaoRegistroAtendimentoHelper> filtrarRelatorioResumoSolicitacoesRAPorUnidade(
    		FiltrarRelatorioResumoSolicitacoesRAPorUnidadeHelper filtro) throws ControladorException;
	
    /**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 30/07/2009
	 * 
	 * @param FileItem
	 * @throws ControladorException
	 */
	public void validarRegistroAtendimentoAnexos(FileItem arquivoAnexo) throws ControladorException;
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 07/08/2009
	 * 
	 * @throws ControladorException
	 */
	public String obterProtocoloAtendimento() throws ControladorException;
	
	/**
	 * [UC00503]Tramitar Conjunto de Registro de Atendimento
	 * 
	 * [FS0006] Valida Data
	 * [FS0007] Valida Hora
	 * [FS0008] Valida Unidade Destino	
	 * 
	 * @author Ana Maria
	 * @date 16/01/2007
	 * 
	 */
	public void validarConjuntoTramitacaoIntegracao(String[] ids, Date dataHoraTramitacao, Integer idUnidadeDestino,Usuario usuario) throws ControladorException;
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 *  Caso o local da ocorr�ncia seja um im�vel e 
	 *  o im�vel tenha um perfil de grande consumidor
	 *  (IPER_ICGRANDECONSUMIDOR da tabela IMOVEL_PERFIL com valor igual a 1 
	 *  com IPER_ID= IPER_ID  da tabela IMOVEL com IMOV_ID = IMOV_ID informado) 
	 *  e a unidade de tramite autom�tico para grandes consumidores 
	 *  esteja preenchida (UNID_IDTRAMITEGRANDECONSUMIDOR da tabela SISTEMA_PARAMETRO)
	 *  preencher a unidade de tramite autom�tico com a unidade informada e n�o permitir alterar. 
	 * 
	 * [FS0046] � Verificar Tramite de Grandes Consumidores.
	 * 
	 * @author Vivianne Sousa
	 * @date 28/12/2009
	 * 
	 */
	public UnidadeOrganizacional verificarTramiteGrandesConsumidores(
			Integer idImovel) throws ControladorException;
	
	/**
	 * M�todo que gera o resumo dos registros de atendimentos Por Ano
	 * 
	 * Gerar Resumo dos Registro de Atendimentos Por Ano
	 * 
	 * @author Fernando Fontelles
	 * @date 17/06/2010
	 * 
	 */
	public void gerarResumoRegistroAtendimentoPorAno(int idLocalidade,
			int idFuncionalidadeIniciada, Integer anoMesReferencia) throws ControladorException;
	
	/**
	 * [UC0424] Consultar Registro de Atendimento
	 * 
	 * verifica a unidade anterior do registro de atendimento pelo �ltimo tr�mite
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
			Integer idRegistroAtendimento) throws ControladorException;
	
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
			Integer idImovel)throws ControladorException;
	/**
	 * [UC0146] Manter Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 10/02/2011
	 * 
	 * @throws ControladorException
	 */
	public boolean existeRegistroAtendimentoConta(
			Integer idConta,Integer idRA) throws ControladorException;
	
	/**
	 * [UC0146] Manter Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 10/02/2011
	 * 
	 * @throws ControladorException
	 */
	public ContaMotivoRetificacao pesquisaContaMotivoRetificacao(
			Integer idMotivo) throws ControladorException;
	
	/**
	 * UC1130 � Filtrar Registro Atendimento de Devolu��o de Valores
	 * @author Vivianne Sousa
	 * @date 11/03/2011
	 */
	public Collection obterRegistroAtendimento(RegistroAtendimentoDevolucaoValoresHelper helper, 
			Integer numeroPagina)throws ControladorException;
	
	/**
	 * UC1130 � Filtrar Registro Atendimento de Devolu��o de Valores
	 * @author Vivianne Sousa
	 * @date 14/03/2011
	 */
	public Integer obterQtdeRegistroAtendimento(RegistroAtendimentoDevolucaoValoresHelper helper)throws ControladorException ;
	
	/**
	 * [UC1131] Efetuar Devolu��o de Valores Pagos em Duplicidade
	 * [SB0001] � Pesquisar os pagamentos associados ao RA
	 * 
	 * @author Vivianne Sousa
	 * @date 15/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisaDadosRegistroAtendimentoPagamentoDuplicidade(
			Integer idRA) throws ControladorException;
	/**
	 * [UC1131] Efetuar Devolu��o de Valores Pagos em Duplicidade
	 * [SB0007] Atualiza Pagamento Devolvido
	 * 
	 * @author Vivianne Sousa
	 * @date 21/03/2011
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarRegistroAtendimentoPagamentoDuplicidade(
			Integer idRa,Integer idPagamento)throws ControladorException;
	
	/**
	 * [UC1131] Efetuar Devolu��o de Valores Pagos em Duplicidade
	 * 
	 * @author Vivianne Sousa
	 * @date 21/03/2011
	 * 
	 * @param
	 * @return void
	 */
	public Collection transferirDevolucaoValoresPagosDuplicidade(Collection colecaoContaASerRetificada,
			Collection colecaoCreditoARealizar,Collection colecaoCreditoASerTransferido,
			Collection colecaoPagamento,SistemaParametro sistemaParametro,Usuario usuarioLogado,
			RegistroAtendimento ra,Integer idImovel)throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * [SB0037] � Define Unidade Destino por Situa��o de Cobran�a.
	 * [FS0018 � Verificar exist�ncia de unidade centralizadora].
	 * 
	 * @author Mariana Victor
	 * @date 04/04/2011
	 * 
	 * @param idLocalidade, idImovel
	 * @return UnidadeOrganizacional
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional definirUnidadeDestinoSituacaoCobranca(
			Integer idEspecificacao, Integer idImovel) throws ControladorException;
	
	/**
	 * RM 5238 � Proposta 20110502
	 * [[UC0366] Inserir Registro de Atendimento]
	 * [FS0052] � Verificar altera��o de vencimento recente para o im�vel
	 * @author R�mulo Aur�lio
	 * @throws ControladorException 
	 * @date 25/04/2011
	 */
	
	public void verificarAlteracaoVencimentoRecente(Integer idEspecificacao,
			Usuario usuarioLogado, Integer idImovel)
			throws ControladorException;
	
	
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
			Integer idRegistroAtendimento) throws ControladorException ;
	
	/**
	 * [UC0425] Reiterar Registro de Atendimento
	 * [FS0008] � Verificar reitera��o do RA pelo cliente 
	 *  
	 * @author Vivianne Sousa
	 * @data 13/05/2011
	 */
	public void verificarExistenciaClienteSolicitanteDataAtual(
			Integer idRegistroAtendimento, Integer idCliente, String nomeCliente)
	throws ControladorException ;

	/**
	 * [UC0425] Reiterar Registro de Atendimento
	 * [FS0007] � Verificar reitera��o do RA pela unidade 
	 *  
	 * @author Vivianne Sousa
	 * @data 13/05/2011
	 */
	public void verificarExistenciaUnidadeSolicitanteDataAtual(
			Integer idRegistroAtendimento, Integer idUnidade, String nomeUnidade)
	throws ControladorException ;
	
	/**
	 * [UC0424] Consultar Registro de Atendimento
	 *  
	 * @author Vivianne Sousa
	 * @data 16/05/2011
	 */
	public Collection pesquisarDadosReiteracaoRA(
			Integer idRegistroAtendimento) throws ControladorException;
	
	/**
	 * pesquisa quantidade de reitera��es do RA
	 *  
	 * @author Vivianne Sousa
	 * @data 18/05/2011
	 */
	public Short pesquisarQtdeReiteracaoRA(Integer idRegistroAtendimento) throws ControladorException;
	
	/**
	 * [[UC0428] Imprimir Registro Atendimento]
	 * @author Rodrigo Cabral
	 * @throws ControladorException 
	 * @date 10/05/2011
	 */
	
	public Collection pesquisarDadosReiteracao(Integer idRA)
			throws ControladorException;
	
	/**
	 * [[UC1165] - Confirmar Altera��es Cadastrais
	 * 
	 * @author S�vio Luiz
	 * @date 18/05/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void inserirRASituacaoLigacaoImovel(
			Integer idMeioSolicitacao,
			Integer idSolicitacaoTipoEspecificacao, 
			Integer idImovel,
			Integer idUnidadeAtendimento, 
			Integer idUsuarioLogado,
			Integer idCliente) throws ControladorException;
	
	/**
	 * [UC0408] Atualizar Registro de Atendimento
	 * 
	 * @author Paulo Diniz
	 * @date 22/06/2011
	 */
	public RegistroAtendimentoSolicitante pesquisarDadosEnvioEmailPesquisaPortal(int idRegistroAtendimentoSolicitante) 
		throws ControladorException;
	
	/**
	 * [UC1181] Registrar Informacao de Pesquisa de Satisfacao
	 * 
	 * @author Paulo Diniz
	 * @date 27/06/2011
	 */
	public int registrarQuestionarioSatisfacaoCliente(QuestionarioSatisfacaoCliente questionario) throws ControladorException;
	
	/**
	 * [UC1181] Registrar Informacoes de Pesquisa de Satisfacao
	 * 
	 * @author Paulo Diniz
	 * @date 22/06/2011
	 */
	public boolean verificaExistenciaQuestionarioSatisfacaoRespondido(Integer idRA)
			throws ControladorException;
	
	/**
	 * [UC1227] Atualizar Ordens Servi�o de Acompanhamento de Celular
	 * 
	 * @autor S�vio Luiz
	 * @date 22/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarDadosRA(
			Integer idRa,String descricaoPontoreferencia, String numeroImovel)
			throws ControladorException;
	
	/**
     *
     * 
     * Gerar relatorio de RA por Unidade por Usu�rio
     * 
     * @author Wellington Rocha
     * @date 18/12/2012
     */
    public List<RAPorUnidadePorUsuarioHelper> filtrarRelatorioRegistroAtendimentoPorUnidadePorUsuario(
    		FiltrarRelatorioRegistroAtendimentoPorUnidadePorUsuarioHelper filtro) throws ControladorException;
}
