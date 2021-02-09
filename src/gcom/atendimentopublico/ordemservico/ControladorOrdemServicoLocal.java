package gcom.atendimentopublico.ordemservico;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import gcom.api.ordemservico.dto.OrdemServicoDTO;
import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ordemservico.bean.AcompanhamentoArquivosRoteiroHelper;
import gcom.atendimentopublico.ordemservico.bean.DadosAtualizacaoOSParaInstalacaoHidrometroHelper;
import gcom.atendimentopublico.ordemservico.bean.DadosAutoInfracaoRetornoOrdemFiscalizacaoHelper;
import gcom.atendimentopublico.ordemservico.bean.ManterDadosAtividadesOrdemServicoHelper;
import gcom.atendimentopublico.ordemservico.bean.ManterOrdemServicoConcluidaHelper;
import gcom.atendimentopublico.ordemservico.bean.OSPavimentoHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterCargaTrabalhoEquipeHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterDadosAtividadeOSHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterDadosAtividadesOSHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterDadosEquipe;
import gcom.atendimentopublico.ordemservico.bean.ObterDescricaoSituacaoOSHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterOSDistribuidasPorEquipeHelper;
import gcom.atendimentopublico.ordemservico.bean.OrdemRepavimentacaoProcessoAceiteHelper;
import gcom.atendimentopublico.ordemservico.bean.OrdemServicoHelper;
import gcom.atendimentopublico.ordemservico.bean.PesquisarOrdemServicoHelper;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.cobranca.cobrancaporresultado.MovimentarOrdemServicoEncerrarOSHelper;
import gcom.relatorio.atendimentopublico.bean.ImovelEmissaoOrdensSeletivasHelper;
import gcom.relatorio.atendimentopublico.ordemservico.FiltrarBoletimCustoPavimentoHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.FachadaException;

public interface ControladorOrdemServicoLocal extends javax.ejb.EJBLocalObject {

	/**
	 * [UC0454] Obter Descrição da situação da OS
	 * 
	 * Este caso de uso permite obter a descrição de uma ordem de serviço
	 * 
	 * @author Leonardo Regis
	 * @date 11/08/2006
	 * 
	 * @param idOrdemServico
	 * @throws ControladorException
	 */
	public ObterDescricaoSituacaoOSHelper obterDescricaoSituacaoOS(
			Integer idOrdemServico) throws ControladorException;

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
	public OrdemServico consultarDadosOrdemServico(Integer idOrdemServico)
			throws ControladorException;

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
	public boolean verificarExistenciaOSProgramada(Integer idOS)
			throws ControladorException;

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
			PesquisarOrdemServicoHelper filtro) throws ControladorException;

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
	public Object[] pesquisarServicoTipo(Integer idTipoServico)
			throws ControladorException;

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
	 * 
	 * 
	 * @author Leandro Cavalcanti
	 * @date 17/08/2006
	 */
	public Collection<ServicoTipo> filtrarST(ServicoTipo servicoTipo,
			Collection colecaoAtividades, Collection colecaoMaterial,
			String valorServicoInicial, String valorServicoFinal,
			String tempoMedioExecucaoInicial, String tempoMedioExecucaoFinal,
			String tipoPesquisa, String tipoPesquisaAbreviada,
			Integer numeroPaginasPesquisa) throws ControladorException;

	/**
	 * [UC0413] Pesquisar Tipo de Serviço
	 * 
	 * Count
	 * 
	 * @author Flávio
	 * @date 17/08/2006
	 */
	public Integer filtrarSTCount(ServicoTipo servicoTipo,
			Collection colecaoAtividades, Collection colecaoMaterial,
			String valorServicoInicial, String valorServicoFinal,
			String tempoMedioExecucaoInicial, String tempoMedioExecucaoFinal,
			String tipoPesquisa, String tipoPesquisaAbreviada)
			throws ControladorException;

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
			Integer idServicoTipoMaterial) throws ControladorException;

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
			Integer idServicoTipoAtividade) throws ControladorException;

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
			throws ControladorException;

	/**
	 * [UC0430] Gerar Ordem de Serviço
	 * 
	 * @author lms
	 * @date 17/08/2006
	 */
	public ServicoTipo pesquisarSevicoTipo(Integer id)
			throws ControladorException;

	/**
	 * 
	 * [UC0430] - Gerar Ordem de Serviço
	 * 
	 * @author lms
	 * @date 18/08/2006
	 */
	public void validarOrdemServico(OrdemServico ordemServico)
			throws ControladorException;

	/**
	 * [UC0430] Gerar Ordem de Serviço
	 * 
	 * @author lms
	 * @date 17/08/2006
	 */
	public OrdemServico pesquisarOrdemServico(Integer id)
			throws ControladorException;

	/**
	 * [UC0806] Gerar Registro Atendimento para Imóveis com Anormalidades
	 * 
	 * @author Diogo Peixoto
	 * @date 14/03/2011
	 */
	public OrdemServico pesquisarOrdemServicoRegistroAtendimento(RegistroAtendimento ra)
			throws ControladorException;
	
	/**
	 * 
	 * [UC0430] - Gerar Ordem de Serviço
	 * 
	 * @author lms
	 * @date 18/08/2006
	 */
	public Integer gerarOrdemServico(OrdemServico ordemServico, Usuario usuario, Integer funcionalidade)
			throws ControladorException;

	/**
	 * [UC0471] Obter Dados da Equipe
	 * 
	 * @author Raphael Rossiter
	 * @date 01/09/2006
	 * 
	 * @return idQuipe
	 * @throws ControladorException
	 */
	public ObterDadosEquipe obterDadosEquipe(Integer idEquipe)
			throws ControladorException;

	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public Collection pesquisarTipoServicoDisponivelPorCriterio(
			UnidadeOrganizacional unidadeLotacao, int tipoCriterio,
			int origemServico) throws ControladorException;

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
	public Collection<OrdemServico> pesquisarOrdemServicoElaborarProgramacao(
			PesquisarOrdemServicoHelper filtro) throws ControladorException;

	/**
	 * [UC0371]Inserir Equipe no sistema.
	 * 
	 * @author Leonardo Regis
	 * @date 25/07/2006
	 * 
	 * @param equipe
	 *            a ser atualizada
	 * 
	 * @throws ControladorException
	 */
	public long inserirEquipe(Equipe equipe, Collection<EquipeComponentes> colecaoEquipeComponentes, 
			Collection<EquipeEquipamentosEspeciais> colecaoEquipeEquipamentosEspeciais,
			Usuario usuario) throws ControladorException;

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades da
	 * inserção de equipe.
	 * 
	 * [FS0007] Verificar quantidade de indicador de responsável Validar Carga
	 * Horária Máxima Validar Placa do Veículo
	 * 
	 * @author Leonardo Regis
	 * @date 25/07/2006
	 * 
	 * @param equipe
	 * @throws ControladorException
	 */
	public void validarInsercaoEquipe(Equipe equipe)
			throws ControladorException;

	/**
	 * Inserir Componentes da Equipe no sistema.
	 * 
	 * @author Leonardo Regis
	 * @date 25/07/2006
	 * 
	 * @param equipeComponentes
	 *            a ser atualizada
	 * 
	 * @throws ControladorException
	 */
	public void inserirEquipeComponentes(Collection<EquipeComponentes> colecaoEquipeComponentes,
			Equipe equipe, Usuario usuario)
	throws ControladorException ;

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades da
	 * exibição da inserção de componentes da equipe.
	 * 
	 * [FS0003] Validar equipe componente já existente [FS0004] Verificar
	 * existência do funcionário [FS0007] Verificar quantidade de indicador de
	 * responsável
	 * 
	 * @author Leonardo Regis
	 * @date 25/07/2006
	 * 
	 * @param equipeComponentes
	 * @throws ControladorException
	 */
	public void validarExibirInsercaoEquipeComponentes(
			Collection colecaoEquipeComponentes,
			EquipeComponentes equipeComponentes) throws ControladorException;

	/**
	 * [UC] Este método se destina a validar todas as situações e
	 * particularidades da inserção de componentes da equipe.
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
	public void validarInsercaoEquipeComponentes(
			Collection colecaoEquipeComponentes) throws ControladorException;

	/**
	 * Inserir Equipamentos Especiais no Sistema.
	 * 
	 * @author Nathalia Santos 
	 * @date 20/06/2011
	 * 
	 * @param EquipeEquipamentosEspeciais
	 *            a ser atualizada
	 * 
	 * @throws ControladorException
	 */
	public void inserirEquipeEquipamentosEspeciais(Collection <EquipeEquipamentosEspeciais> colecaoEquipeEquipamentosEspeciais, 
			Equipe equipe,Usuario usuario)
			throws ControladorException;
	
	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades da
	 * exibição da inserção de equipamentos especiais.
	 * 
	 * [FS0011] Validar equipe componente já existente 
	 * 
	 * @author Nathalia Santos
	 * @date 20/06/2011
	 * 
	 * @param EquipeEquipamentosEspeciais
	 * @throws ControladorException
	 */
	public boolean validarExibirInsercaoEquipeEquipamentosEspeciais(
			Collection colecaoEquipeEquipamentosEspeciais,
			EquipeEquipamentosEspeciais equipeEquipamentosEspeciais) throws ControladorException;

	/**
	 * [UC 0371] Este método se destina a validar todas as situações e
	 * particularidades da inserção de equipamentos especiais.
	 * 
	 * @author Nathalia Santos
	 * @date 20/06/2011
	 * 
	 * @param EquipeEquipamentosEspeciais
	 * @throws ControladorException
	 */
	public void validarInsercaoEquipeEquipamentosEspeciais(
			Collection colecaoEquipeEquipamentosEspeciais) throws ControladorException;

	
	
	
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
	public Collection<ObterDadosAtividadesOSHelper> obterDadosAtividadesOS(
			Integer idOS) throws ControladorException;

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
			Date dataRoteiro) throws ControladorException;

	/**
	 * [UC0479] Gerar Débito da Ordem de Serviço
	 * 
	 * [FS0001] Verificar Existência da Ordem de Serviço [FS0002] Verificar
	 * Existência do Tipo de Débito [FS0003] Validar Valor do Débito [FS0004]
	 * Validar Quantidade de Parcelas
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
	public DebitoACobrar gerarDebitoOrdemServico(Integer ordemServicoId,
			Integer tipoDebitoId, BigDecimal valorDebito, int qtdeParcelas, String percentualCobranca, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 13/09/2006
	 * 
	 * @param colecaoOrdemServicoProgramacao
	 * @throws ControladorException
	 */
	public void elaboraRoteiro(Collection colecaoOrdemServicoProgramacao,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * [UC0462] Obter Dados das Atividades da OS
	 * 
	 * @author Leonardo Regis
	 * @date 14/09/2006
	 * 
	 * @param idOS
	 * @param idAtividade
	 * @param tipoAtividade
	 * @return Collection<ObterDadosAtividadesOSHelper>
	 * @throws ControladorException
	 */
	public Collection<ObterDadosAtividadeOSHelper> obterDadosAtividadeOS(
			Integer idOS, Integer idAtividade, int tipoAtividade)
			throws ControladorException;

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
	public ObterCargaTrabalhoEquipeHelper obterCargaTrabalhoEquipe(
			Integer equipeId,
			Collection<Integer> colecaoIdOSProgramadas,
			Collection<ObterOSDistribuidasPorEquipeHelper> colecaoOSDistribuidasPorEquipe,
			Date dataRoteiro) throws ControladorException;

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
	public void verificarUnidadeUsuario(Integer numeroOS, Usuario usuarioLogado)
			throws ControladorException;

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
	public void verificarOrigemEncerramentoOS(Integer numeroOS,
			Date dataEncerramento) throws ControladorException;

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
	public void encerrarOSSemExecucao(Integer numeroOS, Date dataEncerramento,
			Usuario usuarioLogado, String motivoEncerramento,
			Date ultimaAlteracao, String parecerEncerramento,
			OrdemServico osFiscalizacao, String indicadorVistoriaServicoTipo,
			String codigoRetornoVistoriaOs,OrdemServicoBoletim ordemServicoBoletim,
			Short indicadorServicoAceito) throws ControladorException;

	public void integracaoComercial(Integer idServicoTipo, IntegracaoComercialHelper helper, Usuario usuarioLogado) throws ControladorException;
	
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
			throws ControladorException;

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
	public Collection<Equipe> obterEquipesProgramadas(Integer numeroOS)
			throws ControladorException;

	/**
	 * 
	 * [UC0467] - Atualizar Ordem de Serviço
	 * 
	 * @author lms
	 * @date 18/09/2006
	 */
	public void atualizarOrdemServico(OrdemServico ordemServico, Usuario usuario)
			throws ControladorException;

	
	/**
	 * [UC1185] - Informar Calibragem
	 * 
	 * @author Thúlio Araújo
	 * @param osProgramacaoCalibragem
	 * @throws ControladorException
	 * @date 28/06/2011
	 */
	public void atualizarInformarCalibragem(OSProgramacaoCalibragem osProgramacaoCalibragem, Usuario usuario)
			throws ControladorException;
	
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
	public boolean verificarOSAssociadaDocumentoCobranca(Integer numeroOS)
			throws ControladorException;

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
	public boolean verificarOSAssociadaRA(Integer numeroOS)
			throws ControladorException;

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
	public Integer obterUnidadeDestinoUltimoTramite(Integer numeroOS)
			throws ControladorException;

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
	public Collection<Equipe> obterEquipesPorUnidade(Integer idUnidade)
			throws ControladorException;

	/**
	 * [UC0362] Efetuar Instalação de Hidrômetro
	 * 
	 * @author Leonardo Regis
	 * @date 19/09/2006
	 * 
	 * @param dadosOS
	 * @throws ControladorException
	 */
	public void atualizarOSParaEfetivacaoInstalacaoHidrometro(
			DadosAtualizacaoOSParaInstalacaoHidrometroHelper dadosOS)
			throws ControladorException;

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Serviço
	 * 
	 * @author Raphael Rossiter
	 * @date 20/09/2006
	 * 
	 * @param dataExecucao,
	 *            horaInicio, horaFim, idEquipe
	 * @return void
	 * @throws ControladorException
	 */
	public void verificaDadosAdicionarPeriodoEquipe(String dataExecucao,
			String horaInicio, String horaFim, Integer idEquipe,
			String dataEncerramentoOS, Integer numeroOS)
			throws ControladorException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programação de Ordens de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 11/09/2006
	 * 
	 * @param dataRoteiro,idUnidadeOrganizacional
	 * @return collection<OrdemServicoProgramacao>
	 * @throws ControladorException
	 */
	public Collection<OrdemServicoProgramacao> recuperaOSProgramacaoPorDataRoteiroUnidade(
			Date dataRoteiro, Integer idUnidadeOrganizacional)
			throws ControladorException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programação de Ordens de Serviço [FS0008] -
	 * Verificar possibilidade da inclusão da ordem de serviço
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @param ordemServico
	 * @throws ControladorException
	 */
	public void validarInclusaoOsNaProgramacao(OrdemServico ordemServico,
			Integer unidadeLotacao) throws ControladorException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programação de Ordens de Serviço [FS0012] -
	 * Reordena Sequencial de Programação - Inclusão de Ordem de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @param ordemServico
	 * @throws ControladorException
	 */
	public void reordenaSequencialProgramacaoInclusaoOrdemServico(
			Date dataRoteiro, short sequencialAlterado)
			throws ControladorException;

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

	public Collection consultarProgramacaoRoteiro(String mesAnoReferencia,
			Integer unidadeOrganizacional) throws ControladorException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void alocaEquipeParaOs(Integer numeroOS, Date dataRoteiro,
			Integer idEquipe, boolean naoRemoverOs, Integer idArquivo) throws ControladorException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void incluirOrdemServicoProgramacao(
			OrdemServicoProgramacao ordemServicoProgramacao,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * Método que valida a ordem de serviço utilizado por diversos outros
	 * métodos do atendimento ao público
	 * 
	 * @author Leonardo Regis
	 * @date 22/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void validaOrdemServico(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException;

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
			Integer idOs, Date dataRoteiro) throws ControladorException;

	/**
	 * Atualização Geral de OS
	 * 
	 * @author Leonardo Regis
	 * @date 25/09/2006
	 * 
	 * @param os
	 * @throws ControladorException
	 */
	public void atualizaOSGeral(OrdemServico os) throws ControladorException;

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
	public Collection pesquisarColecaoServicoTipo(Integer numeroOS)
			throws ControladorException;

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
	public void verificaExitenciaProgramacaoAtivaParaDiasAnteriores(
			Integer idOs, Date dataRoteiro) throws ControladorException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void atualizarOrdemServicoProgramacaoSituacaoOs(Integer numeroOS,
			Date dataRoteiro, short situacaoFechamento,
			Integer idOsProgramNaoEncerMotivo, boolean naoInformaIndicadorAtivo) throws ControladorException;

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
	public void encerrarOSComExecucaoSemReferencia(Integer numeroOS,
			Date dataEncerramento, Usuario usuarioLogado,
			String motivoEncerramento, Date ultimaAlteracao,
			String parecerEncerramento, String indicadorPavimento,
			String pavimento,
			Collection colecaoManterDadosAtividadesOrdemServicoHelper,
			IntegracaoComercialHelper integracaoComercialHelper,
			String tipoServicoOSId, OrdemServico osFiscalizacao,
			String indicadorVistoriaServicoTipo, String codigoRetornoVistoriaOs,
			OrdemServicoPavimento ordemServicoPavimento,OrdemServicoBoletim ordemServicoBoletim,
			Short indicadorServicoAceito)throws ControladorException;
	
	/**
	 * [UC0457] Encerra Ordem de Serviço
	 * 
	 * [SB0003] - Encerrar com execução e com referência
	 * 
	 * @author Sávio Luiz
	 * @date 27/09/2006
	 * 
	 * @throws ControladorException
	 */
	public Integer encerrarOSComExecucaoComReferencia(Integer numeroOS,
			Date dataEncerramento, Usuario usuarioLogado,
			String motivoEncerramento, Date ultimaAlteracao,
			String parecerEncerramento, String indicadorPavimento,
			String pavimento, String idTipoRetornoOSReferida,
			String indicadorDeferimento, String indicadorTrocaServicoTipo,
			String idServicoTipo, String idOSReferencia,
			String idServicoTipoReferenciaOS,
			Collection colecaoManterDadosAtividadesOrdemServicoHelper,
			IntegracaoComercialHelper integracaoComercialHelper,
			String tipoServicoOSId, OrdemServico osFiscalizacao,
			String indicadorVistoriaServicoTipo, String codigoRetornoVistoriaOs,
			OrdemServicoBoletim ordemServicoBoletim,Short indicadorServicoAceito)throws ControladorException;

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
	public void reordenaSequencialProgramacaoNovaOrdem(Date dataRoteiro,
			short sequencialInformado, short sequencialAtual, Integer idEquipe, boolean verificaOsPendente)
			throws ControladorException;

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
	public void validarCamposEncerrarOS(String indicadorExecucao,
			String numeroOS, String motivoEncerramento,
			String dataEncerramento,
			Collection colecaoManterDadosAtividadesOrdemServicoHelper,
			String tipoServicoReferenciaId, String indicadorPavimento,
			String pavimento, String idTipoRetornoOSReferida,
			String indicadorDeferimento, String indicadorTrocaServicoTipo,
			String idServicoTipo, String dataRoteiro, String idRA,
			String indicadorVistoriaServicoTipo, String codigoRetornoVistoriaOs,
            String indicadorDiagnostico, String observacaoEncerramento, Usuario usuario)
			throws ControladorException;

	public void verificarOrdemServicoControleConcorrencia(
			OrdemServico ordemServico) throws ControladorException;

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
	public void inserirValorCobrancaServico(
			ServicoCobrancaValor servicoCobrancaValor)
			throws ControladorException;

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
	 * @param usuarioLogado 
	 * @throws ControladorException
	 */
	public void atualizarValorCobrancaServico(
			ServicoCobrancaValor servicoCobrancaValor, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * [UC0391] Atualizar Tipo de Retorno da OS_Referida.
	 * 
	 * 
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 01/11/2006
	 * 
	 * @param
	 * @throws ControladorException
	 */
	public void atualizarTipoRetornoOrdemServicoReferida(
			OsReferidaRetornoTipo osReferidaRetornoTipo)
			throws ControladorException;

	/**
	 * [UC0457] Encerra Ordem de Serviço
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 27/09/2006
	 * 
	 * @throws ControladorException
	 */
	public boolean tramitarOuEncerrarRADaOSEncerrada(Integer numeroOS,
			Usuario usuarioLogado, String idMotivoEncerramento, String idRA,
			String dataRoteiro) throws ControladorException;

	/**
	 * 
	 * [UC0430] - Gerar Ordem de Serviço
	 * 
	 * @author lms
	 * @date 14/08/2006
	 */
	public void validarServicoTipo(Integer idRegistroAtendimento,
			Integer idServicoTipo) throws ControladorException;

	/**
	 * Método que valida a ordem de serviço utilizado por diversos outros
	 * métodos do atendimento ao público sem a validação de indicador comercial
	 * 
	 * @author Rafael Francisco Pinto
	 * @date 12/10/2006
	 * 
	 * @throws ControladorException
	 */
	public void validaOrdemServicoAtualizacao(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException;

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
	public Collection pesquisarOSRelatorio(Collection idsOrdemServico)
			throws ControladorException;

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

	public String obterEnderecoAbreviadoOS(Integer idOrdemServico)
			throws ControladorException;

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
	public Collection<Material> obterMateriaisProgramados(Integer numeroOS)
			throws ControladorException;

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
			Integer idMaterial) throws ControladorException;

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
	public void atualizarOrdemServicoRelatorio(Collection colecaoIdsOrdemServico)
			throws ControladorException;

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
	public void manterDadosAtividadesOrdemServico(
			Collection<ManterDadosAtividadesOrdemServicoHelper> colecaoDadosAtividades)
			throws ControladorException;

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
	public OrdemServicoAtividade pesquisarOrdemServicoAtividade(
			Integer numeroOS, Integer idAtividade) throws ControladorException;

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
			Integer idOrdemServicoAtividade) throws ControladorException;

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
	public Collection pesquisarOSGerarRelatorioAcompanhamentoExecucao(
			String origemServico, String situacaoOS, String[] idsServicosTipos,
			String idUnidadeAtendimento, String idUnidadeAtual,
			String idUnidadeEncerramento, Date periodoAtendimentoInicial,
			Date periodoAtendimentoFinal, Date periodoEncerramentoInicial,
			Date periodoEncerramentoFinal, String idEquipeProgramacao,
			String idEquipeExecucao, String tipoOrdenacao)
			throws ControladorException;

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
	public int pesquisarOSGerarRelatorioAcompanhamentoExecucaoCount(
			String origemServico, String situacaoOS, String[] idsServicosTipos,
			String idUnidadeAtendimento, String idUnidadeAtual,
			String idUnidadeEncerramento, Date periodoAtendimentoInicial,
			Date periodoAtendimentoFinal, Date periodoEncerramentoInicial,
			Date periodoEncerramentoFinal, String idEquipeProgramacao,
			String idEquipeExecucao) throws ControladorException;

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
	public Collection pesquisarEquipes(String idEquipe, String nome,
			String placa, String cargaTrabalho,String codigoDdd,
			String numeroTelefone, String numeroImei, String idUnidade,
			String idFuncionario, String idPerfilServico, String indicadorUso,
			String tipoPesquisa, Integer numeroPagina,String equipamentosEspeciasId, String cdUsuarioRespExcServico,
			String indicadorProgramacaoAutomatica) throws ControladorException;
			

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
	public int pesquisarEquipesCount(String idEquipe, String nome,
			String placa, String cargaTrabalho, 
			String codigoDdd, String numeroTelefone, String numeroImei,String idUnidade, 
			String idFuncionario, String idPerfilServico, String indicadorUso,
			String tipoPesquisa,String equipamentosEspeciasId, String cdUsuarioRespExecServico,
			String indicadorProgramacaoAutomatica)
			throws ControladorException;

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

	public void removerEquipes(String[] idsEquipes, Usuario usuarioLogado)
			throws ControladorException;

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
	public void validarOrdemServico(Integer idOrdemServico)
			throws ControladorException;

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
	public Object[] pesquisarParmsOS(Integer idOrdemServico)
			throws ControladorException;

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
	public void atualizarEquipe(Equipe equipe,
			Collection colecaoEquipeComponentes, Usuario usuarioLogado,Collection colecaoEquipeEquipamentosEspeciais)
			throws ControladorException;
	
	/**
	 * [UC0372] - Manter Equipe
	 * 
	 * Atualiza a equipamentos especiais e seus componentes na base
	 * 
	 * @author Nathalia Santos
	 * @date 20/065/2011
	 * 
	 * @param equipamentos especiais
	 * @throws ControladorException
	 */
//	public void atualizarEquipeEquipamentosEspeciais(Equipe equipe,
//			Collection colecaoEquipeEquipamentosEspeciais, Usuario usuarioLogado)
//			throws ControladorException;
	
	
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
	public void verificarExistenciaDescricaoMaterial(Material material)
			throws ControladorException;

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
	public Integer pesquisarOrdemServicoTamanho(
			PesquisarOrdemServicoHelper filtro) throws ControladorException;

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return Integer[]
	 * @throws ControladorException
	 */
	public Integer[] informarRetornoOSFiscalizacao(Integer idOrdemServico,
			String indicadorDocumentoEntregue,
			Integer idLigacaoAguaSituacaoImovel,
			Integer idLigacaoEsgotoSituacaoImovel, 
			Integer idImovel,
			String indicadorMedicaoTipo, 
			String indicadorGeracaoDebito, 
			Integer idCobrancaDocumento,
			Usuario usuarioLogado,
			DadosAutoInfracaoRetornoOrdemFiscalizacaoHelper dadosAutoInfracao,
			Short confirmaAtualizacaoSituacaoLigacaoAgua,
			Short confirmaAtualizacaoSituacaoLigacaoEsgoto,
			Collection colecaoFiscalizacaoSituacaoSelecionada,
			LigacaoEsgoto ligacaoEsgoto,
			String indicadorLigacaoEsgoto)throws ControladorException;

	/**
	 * 
	 * [UC0430] - Gerar Ordem de Serviço
	 * 
	 * Método que é chamado pelo [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * @author Sávio Luiz
	 * @date 18/08/2006
	 */
	public Integer gerarOrdemServicoSemValidacao(OrdemServico ordemServico,
			Integer idLocalidade, Usuario usuario) throws ControladorException;

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
			throws ControladorException;

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
	public void verificarOSJaFiscalizada(Integer idOS)
			throws ControladorException;

	/**
	 * 
	 * [UC0488] - Informar Retorno Ordem de Fiscalização
	 * 
	 * [FS0001] - Validar Ordem de Serviço
	 * 
	 * @author Raphael Rossiter
	 * @date 29/01/2007
	 */
	public void validarOrdemServicoInformarRetornoOrdemFiscalizacao(
			OrdemServico ordemServico) throws ControladorException;

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
	public void removerPrioridadeTipoServico(String[] ids, Usuario usuarioLogado)
			throws ControladorException;

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
	public Integer verificarExistenciaOSProgramacao(Integer idProgramacaoRoteiro)
			throws ControladorException;

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
	public void encerrarOSDoServicoTipoSemRA(Usuario usuarioLogado,
			Integer idServicoTipo) throws ControladorException;

	/**
	 * 
	 * Pesquisar data e equipe da programação da ordem serviço
	 * 
	 * @author Ana Maria
	 * @date 09/03/2007
	 */
	public OrdemServicoProgramacao pesquisarDataEquipeOSProgramacao(Integer idOs)
			throws ControladorException;

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
	public void reordenarSequencialProgramacao(Date dataRoteiro,
			Integer idEquipe) throws ControladorException;

	/**
	 * [UC0513] Manter Tipo Retorno OS Referida
	 * 
	 * Remover Tipo Retorno OS Referida
	 * 
	 * @author Thiago Tenório
	 * @date 19/03/2007
	 * 
	 */
	public void removerTipoRetornoOrdemServicoReferida(String[] ids, Usuario usuarioLogado)
			throws ControladorException;
	
	
	/**
	 * [UC0457] Encerra Ordem de Serviço
	 * 
	 * Pesquisa a operação que faz parte da associação com o SERVICO_TIPO na
	 * tabela SERVICO_TIPO_OPERACAO
	 * 
	 * @author Raphael Rossiter
	 * @date 26/04/2007
	 * 
	 * @param idServicoTipo, integracaoComercialHelper
	 * @throws ControladorException
	 */
	public Integer pesquisarServicoTipoOperacao(Integer idServicoTipo) throws ControladorException ;

	
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
	public Collection filtrarImovelEmissaoOrdensSeletivas(ImovelEmissaoOrdensSeletivasHelper helper) 
		throws ControladorException ;
	
	/**
	 * 
	 * [UC0430] - Gerar Ordem de Serviço
	 * 
	 * Método que é chamado pelo [UC0713] Emitir Ordem de Servico Seletiva
	 * 
	 * @author Ivan Sérgio
	 * @date 27/11/2007
	 */
	public Integer gerarOrdemServicoSeletiva(
			OrdemServico ordemServico,
			UnidadeOrganizacional unidadeOrganizacional,
			Imovel imovel,
			Usuario usuario) throws ControladorException;
	
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
	public Collection pesquisarOrdemServicoGerarRelatorioAcompanhamentoHidrometro(
			String idEmpresa,
			String tipoOrdem,
			String situacaoOrdemServico,
			String idLocalidadeInicial,
			String idLocalidadeFinal,
			String dataEncerramentoInicial,
			String dataEncerramentoFinal,
			String idMotivoEncerramento,
			String idSetorComercialIncial,
			String idSetorComercialFinal,
			String codigoQuadraInicial,
			String codigoQuadraFinal,
			String codigoRotaInicial,
			String codigoRotaFinal,
			String sequenciaRotaInicial,
			String sequenciaRotaFinal
			) throws ControladorException;

	
	/**
	 * Este caso de uso permite a exportação de ordem de serviço das 
	 * prestadoras.
	 *
	 * [UC0720] Exportar Ordem de Serviço Prestadoras 
	 *
	 * @author Pedro Alexandre 
	 * @date 21/12/2007
	 *
	 * @param colecaoTramite
	 * @return
	 * @throws ControladorException
	 */
	public Map exportarOrdemServicoPrestadoras(Collection colecaoTramite) throws ControladorException ;

	
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
	public Integer inserirOrdemServicoPavimento(OrdemServicoPavimento ordemServicoPavimento) throws ControladorException ;

	
	
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
	public Integer pesquisarOrdemServicoPorCobrancaDocumento(Integer idCobrancaDocumento)
			throws ControladorException ;
	
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
	 * Correção de erro na execução da funcionalidade
	 * @author Wellington Rocha
	 * @date 19/11/2012*/
	public Integer encerrarOrdemServicoVencida(Integer idServicoTipo, Integer quantidadeDias, Usuario usuarioLogado)
			throws ControladorException;
	
	
	/**
	 * [UC0479] Gerar Débito da Ordem de Serviço
	 * 
	 * [FS0002] Verificar Existência do Tipo de Débito 
	 * [FS0003] Validar Valor do Débito 
	 * [FS0004] Validar Quantidade de Parcelas
	 * 
	 * @author Raphael Rossiter
	 * @date 10/03/2008
	 * 
	 * @param ordemServicoId
	 * 
	 * @throws ControladorException
	 */
	public DebitoTipo validacaoGerarDebito(Integer idDebitoTipo, BigDecimal valorDebito, int qtdeParcelas) 
		throws ControladorException ;
	
	
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
	public DebitoACobrarGeral inserirDebitoACobrarGeral() throws ControladorException ;
	
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
	public DebitoACobrar inserirDebitoACobrar(BigDecimal valorDebito,
			int qtdeParcelas, OrdemServico os, DebitoTipo debitoTipo,
			DebitoACobrarGeral debitoACobrarGeral, BigDecimal percentualTaxaJurosFinanciamento,
			RegistroAtendimento registroAtendimento, Usuario usuarioLogado) throws ControladorException ;
	
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
	public void inserirDebitoACobrarCategoria(
			Collection<Categoria> colecaoCategoria,
			Collection<BigDecimal> colecaoValoresCategorias,
			DebitoACobrar debitoACobrar) throws ControladorException ;
	
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
	public ManterOrdemServicoConcluidaHelper pesquisarOrdemServicoConcluida(Integer idOrdemServico) throws ControladorException;
	
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
			String situacaoOrdemServico,
			String idLocalidadeInicial,
			String idLocalidadeFinal,
			String dataEncerramentoInicial,
			String dataEncerramentoFinal,
			String idMotivoEncerramento,
			String idSetorComercialInicial,
			String idSetorComercialFinal,
			String codigoQuadraincial,
			String codigoQuadraFinal,
			String codigoRotaIncial,
			String codigoRotaFinal,
			String sequenciaRotaInicial,
			String sequenciaRotaFinal) throws ControladorException;
	
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
	public void atualizarDadosFiscalizacao(
			Integer idOrdemServico,
			Short situacaoFiscalizacao,
			String dataFiscalizacao1,
			String dataFiscalizacao2,
			String dataFiscalizacao3,
			Integer idFuncionario,
			Short situacaoFiscalizacaoAnterior) throws ControladorException;
	
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
	public Collection pesquisarResumoOrdensServicoConcluidas(
			Integer idEmpresa,
			Integer idLocalidade,
			String anoMesReferenciaBoletim) throws ControladorException;
	
	/**
	 * [UC0765] Gerar Boletim Ordens de Servico Concluidas
     * 
     * @author Ivan Sérgio
     * @date 02/05/2008
	 * 
	 * @param colecaoIdOrdemServico
	 * @throws ControladorException
	 */
	public void encerrarBoletimOrdemServicoConcluida(Collection colecaoIdOrdemServico) throws ControladorException;

	
	
	
	

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
	public Collection pesquisarOrdemProcessoRepavimentacao(OSPavimentoHelper oSPavimentoHelper, 
			Integer numeroPagina)throws ControladorException;

	
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
	public Collection pesquisarRelatorioOrdemProcessoRepavimentacao(OSPavimentoHelper pavimentoHelper, 
			String relatorioCompletoOuDivergente)throws ControladorException;

	
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
			Short situacao) throws ControladorException;
	
    /**
     * [UC0720] Exportar Ordem de Serviço Prestadoras
     * 
     * @author Vivianne Sousa
     * @date 02/04/2008
     * 
     * @throws ControladorException
     */
    public void atualizarIndicadorEncerramentoAutomaticoOS(Short indicadorEncerramentoAutomatico,
            Integer idOrdemServico) throws ControladorException;
    
    /**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 *
	 * [FS0006] Verificar data de emissão
	 * [FS0007] Verificar data de início do recurso
	 * [FS0008] Verificar data de término do recurso
	 *
	 * @author Raphael Rossiter
	 * @date 05/09/2008
	 *
	 * @param dadosAutoInfracao
	 * @param documentoEntregue
	 * @throws ControladorException
	 */
	public void validarDadosAutoInfracaoRetornoOSFiscalizacao(
		DadosAutoInfracaoRetornoOrdemFiscalizacaoHelper dadosAutoInfracao,
		Short documentoEntregue) throws ControladorException ;

    
	/**
     * [UC0427] Tramitar Registro de Atendimento
     * 
     * @author Vivianne Sousa
     * @date 10/06/2008
     * 
     * @throws ControladorException
     */
    public void atualizarUnidadeOrganizacionalAtualOS(Integer idUnidadeOrganizacionalAtual,
            Integer idRA)throws ControladorException;
    
	/**
	 * [UC0427] - Tramitar Registro Atendimento
	 * [FS0011]Validar Tramite para terceira
	 * 
	 * @author Vivianne Sousa
	 * @date 29/10/2008
	 */
	public boolean existeMaisDeUmaOrdemServicoPendente(Integer idregistroAtendimento)
			throws ControladorException;
	
	/**
	 * [UC0488] - Consultar Dados do Registro de Atendimento 
	 * 
	 * Dados da Os Associadas
	 * 
	 * @author Arthur Carvalho
	 * @date 17/02/2009
	 */
	public Collection<OrdemServicoHelper> pesquisarOrdensServicosAssociados(Integer idregistroAtendimento) 
		throws ControladorException ;
	
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
	public BigDecimal calcularValorAguaEsgoto(int consumoInformado,
			SistemaParametro sistemaParametro, Integer idImovel,
			Integer idSituacaoEncontrada, Integer idLigacaoAguaSituacaoImovel, Integer idLigacaoEsgotoSituacaoImovel) throws ControladorException;
	
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
	public Integer filtrarImovelEmissaoOrdensSeletivasCount(ImovelEmissaoOrdensSeletivasHelper helper) 
		throws ControladorException;
	
	
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
	public void validarCamposEncerrarOSPopup(String indicadorExecucao,
			String numeroOS, String motivoEncerramento,
			String dataEncerramento,
			Collection colecaoManterDadosAtividadesOrdemServicoHelper,
			String tipoServicoReferenciaId, String indicadorPavimento,
			String pavimento, String idTipoRetornoOSReferida,
			String indicadorDeferimento, String indicadorTrocaServicoTipo,
			String idServicoTipo, String dataRoteiro, String idRA,
			String indicadorVistoriaServicoTipo, String codigoRetornoVistoriaOs,
            String indicadorDiagnostico, String observacaoEncerramento, Usuario usuario,
            String idPavimentoRua, String metragemPavimentoRua, String idPavimentoCalcada,
            String metragemPavimentoCalcada, String idUnidadeRepavimentadora)
			throws ControladorException;
	
	
	/**
	 * 
	 * [UC0430] - Gerar Ordem de Serviço
	 *  
	 * @author Hugo Amorim	
	 * @date 12/02/2010
	 */
	public Integer gerarOrdemServicoFiscalizacao(OrdemServico ordemServico,Usuario usuario)
			throws ControladorException;
	
	/**
	 * Pesquisa Servico Tipo por codigo da constante.
	 *  
	 * @author Hugo Amorim	
	 * @date 18/02/2010
	 */
	public ServicoTipo recuperaServicoTipoPorConstante(Integer codigoConstate)
		throws ControladorException;

	/**
	 * Pesquisa Count Ordens em Processo de Repavimentação
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
	public Integer pesquisarOrdemProcessoRepavimentacaoCount(OSPavimentoHelper oSPavimentoHelper)
			throws ControladorException;
	
	/** 
	 * [UC0457] Encerrar Ordem de Serviço
	 * [SB0006] – Obter Unidade Repavimentadora do Município
	 * 
	 * @author Arthur Carvalho
	 * @date 12/04/2010
	 *
	 */
	public UnidadeOrganizacional obterUnidadeRepavimentadorAPartirMunicipio(OrdemServico os,
			String tipoPesquisa) throws ControladorException;

	/**
	 * @author Arthur Carvalho
	 * @date 12/04/2010
	 * [UC0457] Encerrar Ordem de Serviço
	 * [FS0011 – Verificar existência da unidade repavimentadora];
	 * @param idUnidadeRepavimentadora
	 */
	public void verificaUnidadeTipoRepavimentadora(String idUnidadeRepavimentadora) throws ControladorException;
	
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
		throws ControladorException;
	
	/**
	 * Pesquisa quantidade Ordens de Repavimentação em Processo de Aceite.
	 *
	 * [UC1019] Filtrar Ordens de Repavimetação em Processo de Aceite.
	 *
	 * @author Hugo Leonardo.
	 * @date 17/05/2010
	 */
	public Integer pesquisarOrdemProcessoRepavimentacaoAceiteCount(OrdemRepavimentacaoProcessoAceiteHelper oSPavimentoAceiteHelper) 
		throws ControladorException;
	
	/**
	 * [UC1020]	- Exibir Ordens de Repavimentação em Processo de Aceite.
	 * 
	 * Verificar se existe Ordem de Repavimentação em Aceite entre as Ordens selecionadas.
	 * 
	 * @author Hugo Leonardo
	 * @param Collection
	 * @throws ControladorException
	 * @data 21/05/2010
	 */
	public void verificarExistenciaRepavimentacaoAceite(Collection colecaoOSPavimentoSelecionados) 
			throws ControladorException;
	
	/**
	 * [UC1020]	- Exibir Ordens de Repavimentação em Processo de Aceite.
	 * 
	 * Aceitar as Ordens de Serviço em Processo de Repavimentacao Convergente.
	 * 
	 * @author Hugo Leonardo
	 * @param OrdemRepavimentacaoProcessoAceiteHelper
	 * @throws ControladorException
	 * @data 24/05/2010
	 */
	public void aceitarOSRepavimentacaoConvergente(OrdemRepavimentacaoProcessoAceiteHelper osPavimentoAceiteHelper, Usuario usuario,
			Date dataAceite, Short indicadorSituacaoAceite) throws ControladorException;
	
	/**
	 * 
	 * [UC0430] - Gerar Ordem de Serviço
	 *  
	 * @author Hugo Amorim	
	 * @date 12/02/2010
	 */
	public Integer gerarOrdemServicoCorte(OrdemServico ordemServico,Usuario usuario)
			throws ControladorException;
	
	/**
	 * Pesquisar Serviço Tipo Seletivo
	 * 
	 * Seleciona Serviço Tipo Seletivo por codigo da constante
	 * 
	 * @author Hugo Amorim
	 * @date 26/07/2010
	 * 
	 */
	public ServicoTipo recuperaServicoTipoPorConstanteServicoTipoSeletivo(Integer codigoConstate)
		throws ControladorException;
	
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
			Integer idOrdemServico,Integer idFiscalizacaoSituacao) throws ControladorException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 29/07/2010
	 */
	public OrdemServicoFiscSit recuperaOrdemServicoFiscSit(
			Integer idOrdemServico, Integer idFiscalizacaoSituacao) throws ControladorException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 30/07/2010
	 */
	public OrdemServico recuperaOrdemServico(
			Integer idOrdemServico)  throws ControladorException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 02/08/2010
	 */
	public Collection recuperaFiscalizacaoSituacao(
			Integer idOrdemServico)  throws ControladorException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 03/08/2010
	 */
	public Collection recuperaFiscalizacaoSituacaoSelecionada(
			Integer idOrdemServico) throws ControladorException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 09/08/2010
	 */
	public Collection recuperaOrdemServicoFiscSit(
			Integer idOrdemServico) throws ControladorException;

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
			Integer idFiscalizacaoSituacao, Integer idOS)throws ControladorException;
	
	/**
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * @author Vivianne Sousa
	 * @date 30/08/2010
	 * 
	 */
	public OrdemServicoFiscSit recuperaOrdemServicoFiscSitComMenorDataFiscalizacao(
			Integer idOrdemServico) throws ControladorException;
	
	/**
	 * [UC0441] Consultar Dados da Ordem de Serviço
	 * 
	 * @author Vivianne Sousa
	 * @date 01/09/2010
	 */
	public Collection pesquisaOrdemServicoFiscSit(
			Integer idOrdemServico)  throws ControladorException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 19/08/2010
	 */
	public Short recuperaIndicadorDebitoDaOrdemServicoFiscSit(
			Integer idOrdemServico, Integer idFiscalizacaoSituacao)throws ControladorException;
	
	/**
	 * [UC1110] Gerar Boletim de Custo de Repavimentação por Tipo de Pavimento
	 *
	 * @author Hugo Leonardo
	 * @date 03/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioBoletimCustoPavimento(FiltrarBoletimCustoPavimentoHelper relatorioHelper) 
		throws ControladorException;
	
	/**
	 * [UC1110] Gerar Boletim de Custo de Repavimentação por Tipo de Pavimento
	 *
	 * @author Hugo Leonardo
	 * @date 04/01/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarBoletimCustoPavimentoPorTipoPavimentoRua(FiltrarBoletimCustoPavimentoHelper relatorioHelper) 
		throws ControladorException;
	
	/**
	 * [UC1110] Gerar Boletim de Custo de Repavimentação por Tipo de Pavimento
	 *
	 * @author Hugo Leonardo
	 * @date 10/01/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarTotaisPorTipoPavimentoRua(FiltrarBoletimCustoPavimentoHelper relatorioHelper) 
		throws ControladorException;
	

	/**
	 * Filtrar as Ordens de Serviço dos Comandos de Ação de Cobrança
	 * 
	 * [UC1098] Informar Não Aceitação de Motivo de Encerramento Ordem de Serviço
	 * 
	 * @author Mariana Victor
	 * @date 13/12/2010
	 * 
	 * @return filtroCobrancaAcao
	 * @throws ControladorException
	 */
	public FiltroOrdemServico construirFiltroOrdemServico(
			String grupoCobranca, String acaoCobranca,
			String anoMesPeriodoReferenciaContasInicial,
			String anoMesPeriodoReferenciaContasFinal)
			throws ControladorException;

	/**
	 * [UC1098] Informar Não Aceitação de Motivo de Encerramento Ordem de Serviço
	 * 
	 * @author Mariana Victor
	 * @date 23/12/2010
	 */
	public OrdemServico pesquisarOS(Integer id)
			throws ControladorException;
	
	/**
	 * [UC0457] Encerrar Ordem de Serviço
	 * 
	 * @author Vivianne Sousa
	 * @date 18/01/2011
	 */
	public ServicoTipo recuperaServicoTipoDaOrdemServico(
			Integer idOrdemServico)throws ControladorException;     
	
	/**
	 * [UC0457] Encerrar Ordem de Serviço
	 * 
	 * @author Vivianne Sousa
	 * @date 21/01/2011
	 */
	public ServicoTipoBoletim recuperaServicoTipoBoletimDoServicoTipo(
			Integer idServicoTipo)throws ControladorException;
	
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
		throws ControladorException;
	
	/**
	 * [UC1116] Atualizar Informações da OS para Boletim de Medição
	 * 
	 * @author Vivianne Sousa
	 * @date 02/02/2011
	 */
	public OrdemServicoBoletim recuperaOrdemServicoBoletimDaOS(
			Integer idOrdemServico)throws ControladorException;
	
	/**
	 * [UC1163]  Gerar  Relatório de OS executadas por Prestadora de Serviço
	 * 
	 * @author Vivianne Sousa
	 * @date 13/04/2011
	 */
	public Collection recuperaOSExecutadas(Date dataInicial,Date dataFinal, 
			Integer idGerencia, Integer idUnidade, Integer idLocalidade,
			String[] idsRegiao,String[] idsMicroregiao,
			String[] idsMunicipio) throws ControladorException;
	
	/**
	 * [UC1163]  Gerar  Relatório de OS executadas por Prestadora de Serviço
	 * 
	 * @author Vivianne Sousa
	 * @date 18/04/2011
	 */
	public Collection recuperaTotalServicoOSExecutadas(Date dataInicial,Date dataFinal, 
		Integer idLocalidade) throws ControladorException;
	
	/**
	 * [UC1163]  Gerar  Relatório de OS executadas por Prestadora de Serviço
	 * [SB0003] – Gerar Analítico TXT
	 * 
	 * @author Vivianne Sousa
	 * @date 18/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void gerarTxtOSExecutadasPrestadoraServico(Date dataInicial,Date dataFinal, 
			Integer idGerencia, Integer idUnidade, Integer idLocalidade,String[] idsRegiao,
			String[] idsMicroregiao,String[] idsMunicipio,String header)throws ControladorException;
	
	/**
	 * [UC1163]  Gerar  Relatório de OS executadas por Prestadora de Serviço
	 * 
	 * @author Vivianne Sousa
	 * @date 18/04/2011
	 */
	public Collection recuperaTotalServicoOSExecutadasPorLocalidade(Date dataInicial,Date dataFinal, 
			Integer idGerencia, Integer idUnidade, Integer idLocalidade,String[] idsRegiao,
			String[] idsMicroregiao,String[] idsMunicipio)throws ControladorException;
	
	/**
	 * [UC1163]  Gerar  Relatório de OS executadas por Prestadora de Serviço
	 * 
	 * @author Vivianne Sousa
	 * @date 04/05/2011
	 */
	public Integer recuperaTotalOSExecutadasPorLocalidade(Date dataInicial,Date dataFinal, 
			Integer idGerencia, Integer idUnidade, Integer idLocalidade,
			String[] idsRegiao,String[] idsMicroregiao,
			String[] idsMunicipio) throws ControladorException ;
	
	/**
	 * Método que valida a ordem de serviço utilizado por diversos outros
	 * métodos do atendimento ao público
	 * 
	 * @author Leonardo Regis,Vivianne Sousa
	 * @date 22/09/2006,19/05/2011
	 * 
	 * @throws ControladorException
	 */
	public void validaOrdemServicoDiasAditivoPrazo(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException;

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
			Integer idCobrancaDocumento) throws ControladorException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada 
	 * 
	 * [FS0001] – Validar Ordem de Serviço.
	 * 
	 * @author Vivianne Sousa
	 * @date 24/05/2011
	 */
	public Integer pesquisarOSFiscalizacaoPendente(Integer numeroOS)throws ControladorException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada 
	 * [SB0001] - Selecionar Ordens de Serviço 
	 * 
	 * @author Vivianne Sousa
	 * @date 24/05/2011
	 */
	public Map recuperaQtdeOSEncerrada(Integer idGrupoCobranca,Integer idGerencia, 
		Integer idUnidade, Integer idLocalidadeInicial,Integer idLocalidadeFinal, 
		Integer idTipoServico, Integer qtdeDiasEncerramentoOS) throws ControladorException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada 
	 * [SB0002] – Verificar Ordem Serviço  
	 * 
	 * @author Vivianne Sousa
	 * @date 26/05/2011
	 */
	public Integer pesquisarIdMotivoEncerramentoOS(Integer idOrdemServico)throws ControladorException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada 
	 * [SB0003] - Gerar Várias Ordens de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 26/05/2011
	 */
	public Collection gerarVariasOsFiscalizacao(Integer idGrupoCobranca,Integer idGerencia, 
			Integer idUnidade, Integer idLocalidadeInicial,Integer idLocalidadeFinal, 
			Integer idTipoServico, Integer qtdeDiasEncerramentoOS, 
			BigDecimal percentualOSgeradas, Usuario usuario) throws ControladorException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada 
	 * [SB0004] – Gerar Ordem de Serviço.
	 * 
	 * @author Vivianne Sousa
	 * @date 27/05/2011
	 */
	public Collection gerarOrdemServicoFiscalizacao(Collection colecaoOrdemServicoSelecionadas,
			ServicoTipo servicoTipo,Usuario usuario) throws ControladorException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada 
	 * [SB0005] – Gerar Formulário em formato pdf
	 * 
	 * @author Vivianne Sousa
	 * @date 26/05/2011
	 */
	public Collection recuperaDadosOsFiscalizacao(Collection colecaoOSFiscalizacao,
			Integer idGrupoCobranca) throws ControladorException;
	
	/**
	 * [UC0711] Filtro para Emissao de Ordens Seletivas
	 * [SB0001]-Gerar Comando. 
	 * 
	 * @author Vivianne Sousa
	 * @date 21/06/2011
	 *
	 * @param helper
	 * @throws ControladorException
	 */
	public Integer gerarComando(ImovelEmissaoOrdensSeletivasHelper helper, int quantidadeOs,Usuario usuarioLogado) 
		throws ControladorException;
	
	/**
	 * [UC0713] Emitir Ordem de Serviço Seletiva
	 * [SB0002] Gerar TXT 
	 * 
	 * @author Vivianne Sousa
	 * @date 28/06/2011
	 */
	public void gerarTxtOsInspecaoAnormalidade(Integer idFuncionalidadeIniciada, 
			Integer idComandoOrdemSeletiva,Integer qtdAnormalidadesConsecutivas)
		throws ControladorException;
	
	/**
	 * [UC0412] Manter Tipo de Serviço
	 * [SB0003] Atualizar Grau de Importância
	 *
	 * @author Thúlio Araújo
	 * @date 30/06/2011
	 * 
	 * @param servicoTipo
	 * @param usuario
	 * @throws ControladorException
	 */
	public void atualizarGrauImportanciaServicoTipo(ServicoTipo servicoTipo, Usuario usuario)
			throws ControladorException;
	
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
	public Collection<AcompanhamentoArquivosRoteiroHelper> pesquisarAcompanhamentoArquivosRoteiro( 
			String datProgramacao,
			String idEmpresa,
			String idSituacao,
			Integer idUnidOrganizacional) throws ControladorException;
	
	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 11/07/2011
	 */
	public Integer pesquisarDadosComandoOSSeletivaCount(
			Integer idEmpresa, Date comandoInicial, Date comandoFinal)
			throws ControladorException;
	
	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 11/07/2011
	 */
	public Collection pesquisarDadosComandoOSSeletivaResumido(
			Integer idEmpresa, Date dataInicial, Date dataFinal,
			 int numeroIndice,int quantidadeRegistros, 
			 Integer qtdeDiasValidadeOSAnormalidadeFiscalizacao)throws ControladorException;
	
	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 12/07/2011
	 */
	public ComandoOrdemSeletiva pesquisarDadosComandoOSSeletiva(
			Integer idComandoOrdemSeletiva)throws ControladorException ;
	
	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 13/07/2011
	 */
	public Collection pesquisarDadosAnormalidadeComandoOSS(
			Integer idComandoOrdemSeletiva) throws ControladorException;

	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 13/07/2011
	 */
	public Collection pesquisarDadosCapacidHidrComandoOSS(
			Integer idComandoOrdemSeletiva) throws ControladorException;


	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 13/07/2011
	 */
	public Collection pesquisarDadosLigacaoSitComandoOSS(
			Integer idComandoOrdemSeletiva) throws ControladorException;
	
	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 15/07/2011
	 */
	public ComandoOrdemSeletiva pesquisarComandoOSSeletiva(
			Integer idComandoOrdemSeletiva)  throws ControladorException;
	
	/**
	 * [UC1192] Movimentar OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @date 18/07/2011
	 */
	public Collection pesquisarDadosOSEmitir(Integer idComando,
			Integer numeroOSInicial, Integer numeroOSFinal) throws ControladorException;
	
	/**
	 * [UC1192] Movimentar OS Seletiva de Inspeção de Anormalidade
	 * [FS0001] - Verificar se ordem de serviço faz parte do comando
	 * 
	 * @author Vivianne Sousa
	 * @date 19/07/2011
	 */
	public String retornaOsNaoFazParteComando(Integer idComandoOrdemSeletiva,
			List<Integer> numerosOSPesquisar)  throws ControladorException;
	
	/**
	 * [UC1192] Movimentar OS Seletiva de Inspeção de Anormalidade
	 * [FS0003] – Verificar se imóvel faz parte do comando
	 * 
	 * @author Vivianne Sousa
	 * @date 19/07/2011
	 */
	public String retornaImovelNaoFazParteComando(Integer idComandoOrdemSeletiva,
			List<Integer> numerosImoveisPesquisar) throws ControladorException;
	
	/**
	 * [UC1192] Movimentar OS Seletiva de Inspeção de Anormalidade
	 * 
	 * Encerrar ordem(ns) de serviço.
	 * 
	 * @author: Vivianne Sousa
	 * @date: 20/07/2011
	 */
	public void movimentarOrdemServicoEncerrarOS(Usuario usuarioLogado,
			MovimentarOrdemServicoEncerrarOSHelper helper) throws ControladorException;
	
	/**
	 * [UC1197] Encerrar Comandos de OS Seletiva de Inspeção de Anormalidade
	 *
	 * @author Vivianne Sousa
	 * @created 21/07/2011
	 */
	public void encerrarComandoOSSeletivaInspecaoAnormalidade(Integer idFuncionalidadeIniciada,
			Usuario usuarioLogado, Integer idComando,Short idMotivoEncerramento) throws ControladorException;
	
	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @date 26/07/2011
	 */
	public Integer pesquisaOrdemServicoNaoPendenteFazParteComando(
			Integer idComandoOrdemSeletiva) throws ControladorException;
	

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
	public void removerServicoTipoMotivoEncerramento(Integer idServicoTipo) throws ControladorException;
	
	
	/**
	 * [UC0412] Manter Tipo de Serviço
	 * 
	 * Metodo responsável por inserir
	 * um motivo de encerramento para
	 * um tipo de serviço  
	 * 
	 * @author Raimundo Martins
	 * @date 27/07/2011
	 * 
	 * @param servicoTipoMotivoEncerramento
	 * 
	 */
	
	
	/**
	 * [UC0412] Manter Tipo de Serviço
	 * 
	 * Metodo responsável por inserir
	 * um motivo de encerramento para
	 * um tipo de serviço  
	 * 
	 * @author Raimundo Martins
	 * @date 27/07/2011
	 * 
	 * @param servicoTipoMotivoEncerramento
	 * 
	 */
	public void inserirServicoTipoMotivoEncerramento(ServicoTipoMotivoEncerramento servicoTipoMotivoEncerramento) throws ControladorException;
	
	/**
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS 
	 * 
	 * @author Sávio Luiz
	 * @date 19/07/2011
	 */
	public void inserirProgramacaoAutomaticaAcompanhamentoOS(Integer idUnidadeOrganizacional,Date dataProgramacao,int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
	 *
	 * @author Sávio Luiz
	 * @date 30/07/2011
	 */
	public Collection pequisarUnidadesOrganizacionaisdasEquipes() throws ControladorException;
	
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
	public void atualizarArquivoTextoAcompArqRoteiro(Vector<Integer> ids, Integer situacaoNova)
		throws ControladorException;
	
	/**
	 * [UC1192] Movimentar OS Seletiva de Inspeção de Anormalidade
	 * Verificar se ordem de serviço que faz parte do comando ja esta encerrada
	 * 
	 * @author Vivianne Sousa
	 * @date 02/08/2011
	 */
	public String retornaOsJaEncerrada(List<Integer> numerosOSPesquisar)  throws ControladorException;
	
	/**
	 * 
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * @author Bruno Barros
	 * @date 28/06/2011
	 * 
	 * @param idsEquipes - Id's das equipes que terão seus roteiros gerados. Caso esse
	 *                     parametro venha nulo, iremos gerar de todas as equipes que
	 *                     possuam OS programadas
	 * @param dataServico - Data do serviço a ser gerado.
	 * 
	 * @throws ControladorException
	 */
	public void gerarArquivoAcompanharServicoRoteiroProgramado(
			Integer idFuncionalidadeIniciada,
			Integer idUnidadeOrganizacional,
			Date dataServico ) throws ControladorException;	
	
	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Serviço do Roteiro Programado
	 * 
	 * [SB0002] - Nome do arquivo texto  
	 * 
	 * @author Bruno Barros
	 * @date 26/07/2011
	 * 
	 * @param Equipe - equipe que teve seu arquivo gerado
	 * 
	 * @return String - retorna o nome do arquivo que será gerado
	 * 
	 * @throws ControladorException
	 */
	public String nomeArquivoAcompanhamentoServico( Integer idEquipe ) 
		throws ControladorException;
	
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
	public byte[] gerarArquivoTextoOrdensServicoAcompanhamentoEquipe( Integer idEquipe, Date dataRoteiro , boolean gerarTabelasBasicas, boolean arquivoOnLine)
		throws ControladorException;
	
	/**
	 * @author Rodrigo Cabral
	 * @date 11/07/2011
	 * 
	 */
	public Object[] pesquisarIdFiscalizacaoSituacaoAgua(Integer idLigacaoAguaSituacao, Integer idSituacaoEncontrada)
			throws ControladorException;
	
	/**
	 * @author Rodrigo Cabral
	 * @date 11/07/2011
	 * 
	 */
	public Object[] pesquisarIdFiscalizacaoSituacaoEsgoto(Integer idLigacaoEsgotoSituacao, Integer idSituacaoEncontrada)
			throws ControladorException;

	/**	 
	 * [UC1199] – Acompanhar Arquivos de Roteiro	 
	 * Pesquisa a(s) equipe(s) da OS Programacao Acompanhamento Servico 
	 * filtrando pelo identificador da Ordem de Serviço
	 * 
	 * @author Raimundo Martins
	 * @date 09/08/2011
	 * 
	 * @param idOrdemServiço
	 * @return Collection<Integer>	 
	 * @throws ControladorException	 */		
	
	public Collection<Integer> pesquisarEquipeOSProgramacaoAcompServicoPorIdOrdemServico(Integer idOrdemServico) throws ControladorException;		
	

	
	/**
	 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
	 *
	 * [SB0002] Inserir Ordem de Serviço na Programação 
	 * @author Thúlio Araújo
	 * @date 19/07/2011
	 */
	public ProgramacaoRoteiro pesquisarProgramacaoRoteiro(Integer idUnidadeOrganizacional,Date dataProgramacao)
			throws ControladorException;
	
	/**
	 * [UC1211] Inserir Ordem Programação Acompanhamento Serviço
	 * 
	 * 
	 * @author Bruno Barros
	 * @date 06/07/2011
	 * 
	 * @param idEquipe - Id da equipe a ter as ordens incluidas
	 * @param dataRoteiro - Data para a incluisão das OS
	 * 
	 * @return void...
	 * 
	 * @throws ControladorException
	 */	
	public void inserirOrdemProgramacoAcompanhamentoServico( Integer idEquipe, Date dataRoteiro ) throws ControladorException;

	
	/**
	 * [UC1213] Emitir Relatorio de Ordem de Servico de Fiscalizacao
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 06/08/2011
	 * 
	 * @throws ControladorException
	 */	
	public Collection pesquisarOrdensServicoFiscalizacao(int tipoRelatorio, String periodoInicial, String periodoFinal, String idGerenciaRegional, String idUnidadeNegocios,String idLocalidadeInicial, String idLocalidadeFinal, String situacaoOS, String idOSReferidaRetornoTipo, String aceitacaoDaOS) throws ControladorException;
	
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
			Integer idOrdemServico, Date dataProgramacao, Integer idEquipe) throws ControladorException;
	
	/**
	 * [UC1205] - Remanejar Ordem de Servico
	 * 
	 * [FS0007] Validar carga de trabalho da Equipe   
	 * 
	 * @author Thúlio Araújo
	 * @date 22/08/2011 ---
	 */
	public boolean validarCargaTrabalhoEquipe(Integer idEquipeRemanejada, Integer idProgramacaoRoteiro,Integer idOrdemServico,Integer idUnidadeOrganizacional) throws ControladorException;
	
	/**
	 * [UC1213] Emitir Relatorio de Ordem de Servico de Fiscalizacao
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 06/08/2011
	 * 
	 * @throws ErroRepositorioException
	 */	
	public OrdemServico pesquisarOrdemServicoFiscalizada(Integer idOrdemServico)  throws ControladorException;
	
	/**
	 * Reordena Sequencial de Programação Acompanhamento de Serviço
	 * 
	 * @author Thúlio Araújo
	 * @date 27/08/2011
	 * 
	 * @param ordemServico
	 * @throws ControladorException
	 */
	public short reordenaSequencialOSProgramacao(Date dataRoteiro,
			short sequencialInformado, Integer idEquipe)
			throws ControladorException;
	
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
			throws ControladorException;
	
	/**
	 * [UC1206] Informar Situação da Ordem de Serviço
	 * 
	 * 
	 * @author Thúlio Araújo
	 * @date 29/08/2011
	 * 
	 * @param idEquipe - Id da equipe atual
	 * @param dataRoteiro - Data para a incluisão das OS
	 * 
	 * @return void...
	 * 	
	 * @throws ControladorException
	 */	
	public void atualizarOrdemProgramacaoAcompServicoInformarSituacao( Integer idArquivo, Date dataRoteiro, Integer idOrdemServico,
			Short novaSituacao, Integer idOsProgramNaoEncerMotivo) throws ControladorException;
	
	/**
	 * [UC0467] Atualizar Ordem Serviço
	 * 
	 * Atualiza os dados da tabela de acompanhamento de serviço de acordo com os dados informados.
	 * 	  
	 * @author Thúlio Araújo
	 * @date 31/08/2011
	 * 
	 * @param idEquipe - Id da equipe atual
	 * @param dataRoteiro - Data para a incluisão das OS
	 * 
	 * @return void...
	 * 	
	 * @throws ControladorException
	 */	
	public void atualizarOSProgramacaoAcompServico( Integer idArquivo, Date dataRoteiro, Integer idOrdemServico, Integer idServicoTipo) throws ControladorException;
	
	/**
	 * [UC-1209] Acompanhar Serviços no Dispositivo Móvel
	 * 
	 * Método que irá pesquisar o arquivo que será carregado no
	 * celular
	 * 
	 * @autor Bruno Barros
	 * @date 31/08/2011
	 * 
	 * @param imei - Imei do aparalho que irá receber o arquivo
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public byte[] baixarArquivoTextoAcompanhamentoServico( long imei ) throws ControladorException;	
	
	/**
	 * [UC1199] Acompanhamento de Arquivos de Roteiro
	 * 
	 * @author Thúlio Araújo
	 * @date 27/08/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSProgramacaoAcompanhamentoServico> pesquisarOSProgramacaoAcompArquivoComDataRoteiroIdEquipe(
			Date dataRoteiro, Integer idArquivo) throws ControladorException;
	
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
			throws ControladorException;
	
	/**
	 * [UC1199] – Acompanhamento Arquivos Roteiro Remanejar Ordem Servico Action
	 * 
	 * @author Thúlio Araújo
	 * @date 06/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public OrdemServicoProgramacao pesquisarOSProgramacaoAtivaComDataRoteiroIdEquipe(
			Integer numeroOS, Date dataRoteiro, Integer idEquipe)
			throws ControladorException;
	
	/**
	 * Reordena Sequencial de Programação Acompanhamento de Serviço
	 * 
	 * @author Thúlio Araújo
	 * @date 27/08/2011
	 * 
	 * @param ordemServico
	 * @throws ControladorException
	 */
	public void reordenaSequencialOSProgramacaoAcompServico(Date dataRoteiro,
			short sequencialInformado, Integer idEquipe, short sequencialAtual)
			throws ControladorException;
	
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
	public String retornaMensagemAcompanhamentoArquivosRoteiroImei(long imei)
			throws ControladorException;
	
	/**
	 * [UC-1209] Acompanhar Serviços no Dispositivo Móvel
	 * 
	 * Método que irá pesquisar o arquivo que será carregado no
	 * celular
	 * 
	 * @autor Bruno Barros
	 * @date 31/08/2011
	 * 
	 * @param imei - Imei do aparalho que irá receber o arquivo
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public byte[] atualizarArquivoTextoAcompanhamentoServico( long imei ) throws ControladorException;
	
	/**
	 * [UC-1225] Incluir dados acompanhamento servico 
	 * 
	 * Método que insere o array de bytes vindo do celular
	 * e o insere no banco
	 * 
	 * @param numeroOS - Id da OS
	 * @param tipoFoto - Se essa foto foi do inicio do meio ou do fim da obra
	 * @param foto     - array de bytes com a foto em si
	 * 
	 * @throws FachadaException
	 */
	public void inserirFotoOrdemServico( int numeroOS, int tipoFoto, byte[] foto ) throws ControladorException;	
	
	/**
	 * [UC-1225] Incluir dados acompanhamento servico 
	 * 
	 * Altera a situação de uma ordem de serviço no GSAN
	 * 
	 * @param numeroOS - Id da OS
	 * @param situacao - Id da nova situacao
	 * @param foto     - array de bytes com a foto em si
	 * 
	 * @throws FachadaException
	 */	
	public void atualizarSituacaoProgramacaoOrdemServico( int numeroOS, short situacao ) throws ControladorException;
	
	/**
	 * [UC1227] Atualizar Ordens Serviço de Acompanhamento de Celular
	 * 
	 * Método que irá atualizar as ordens de serviço para encerradas
	 * 
	 * @autor Sávio Luiz
	 * @date 22/09/2011
	 * 
	 * @param dataProgramação - data de programação
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public void atualizarOrdemServicoAcompanhamentoServico( Date dataProgramacao, Integer idEquipe, Integer idUnidadeOrganizacional ) throws ControladorException;
	
	/**
	 * [UC1225] Incluir Dados Acompanhamento de Serviço
	 *
	 * Este caso de uso permite a inserção de dados na tabela de ordem de serviço para acompanhamento do serviço.
	 * 
	 * @author Thúlio Araújo
	 *
	 * @date 22/09/2011
	 * @param buffer - BufferedReader com o arquivo selecionado
	 * @return Object[]
	 * @throws ControladorException
	 */
	public void retornoAtualizarOrdemServicoAcompanhamentoServico(
			BufferedReader buffer) throws ControladorException;
	
	/**
	 * [UC-1209] Acompanhar Serviços no Dispositivo Móvel
	 * 
	 * Método que irá pesquisar o arquivo que será carregado no
	 * celular
	 * 
	 * @autor Sávio Luiz
	 * @date 31/08/2011
	 * 
	 * @param imei - Imei do aparalho que irá receber o arquivo
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public void atualizarSituacaoArquivoTextoAcompanhamentoServico( long imei,Short idSituacaoTransmissaoLeitura  ) throws ControladorException;

	public void validarEncerramentoOsImovelEmCampo(OrdemServico ordemServico) throws ControladorException;

	/**
	 * Método que retorna os dados das Ordens de Serviços programadas 
	 * @param funcionarioID 
	 * 
	 * @return List<OrdemServicoDTO> DTO das Ordens de Serviços
	 * @throws ControladorException
	 */
	public List<OrdemServicoDTO> pesquisarOrdensServicoProgramadas(Integer funcionarioId) throws ControladorException;
}
