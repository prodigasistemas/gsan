package gcom.cadastro.imovel;

import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.bean.ImovelAbaCaracteristicasHelper;
import gcom.cadastro.imovel.bean.ImovelAbaCaracteristicasRetornoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaConclusaoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaConclusaoRetornoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaEnderecoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaEnderecoRetornoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaLocalidadeHelper;
import gcom.cadastro.imovel.bean.ImovelAbaLocalidadeRetornoHelper;
import gcom.cadastro.imovel.bean.ImovelSubcategoriaHelper;
import gcom.cadastro.imovel.bean.InserirImovelHelper;
import gcom.cadastro.tarifasocial.TarifaSocialCarta;
import gcom.cadastro.tarifasocial.TarifaSocialComandoCarta;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.bean.SituacaoEspecialCobrancaHelper;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.bean.SituacaoEspecialFaturamentoHelper;
import gcom.faturamento.conta.Conta;
import gcom.gui.faturamento.bean.FiltrarImovelInserirManterContaHelper;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.Rota;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.relatorio.cadastro.GerarRelatorioImoveisDoacoesHelper;
import gcom.relatorio.micromedicao.FiltrarAnaliseExcecoesLeiturasHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * < <Descrição da Interface>>
 * 
 * @author Administrador
 * @created 7 de Junho de 2004
 */
public interface ControladorImovelLocal extends javax.ejb.EJBLocalObject {

	/**
	 * Atualizar um imovel na base
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 */
	public void atualizarImovelLigacaoAgua(Imovel imovel,
			Integer idLigacaoAguaSituacao) throws ControladorException;

	/**
	 * Insere um imovel na base
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 */
	public void inserirImovel(Imovel imovel) throws ControladorException;

	/**
	 * Retorna a quantidade de economias de um imóvel
	 * 
	 * @param imovel
	 *            Imóvel que será consultado
	 * @return Quantidade de economias
	 */
	public int obterQuantidadeEconomias(Imovel imovel)
			throws ControladorException;

	/**
	 * 
	 * @return Quantidade de categorias cadastradas no BD
	 * @throws RemoteException
	 */
	public Object pesquisarObterQuantidadeCategoria()
			throws ControladorException;

	/**
	 * Retorna a quantidade de economias de um imóvel
	 * 
	 * @param imovel
	 *            Imóvel que será consultado
	 * @return Quantidade de economias
	 */
	public Collection obterColecaoImovelSubcategorias(Imovel imovel,
			Integer quantidadeMinimaEconomia) throws ControladorException;

	/**
	 * Retorna a quantidade de categorias de um imóvel
	 * 
	 * @param imovel
	 *            Imóvel que será consultado
	 * @return uma coleção de categorias
	 */
	public Collection obterQuantidadeEconomiasCategoria(Imovel imovel)
			throws ControladorException;

	/**
	 * Retorna a quantidade de categorias de um imóvel
	 * 
	 * @param imovel
	 *            Imóvel que será consultado
	 * @return uma coleção de categorias
	 */
	public Collection obterQuantidadeEconomiasContaCategoria(Conta conta)
			throws ControladorException;

	/**
	 * Inserir Imovel
	 *
	 * @author Raphael Rossiter
	 * @date 19/08/2008
	 *
	 * @param inserirImovelHelper
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer inserirImovelRetorno(InserirImovelHelper inserirImovelHelper) 
	throws ControladorException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param imovelSubcategoria
	 *            Descrição do parâmetro
	 */
	public void inserirImovelSubCategoria(ImovelSubcategoria imovelSubcategoria)
			throws ControladorException;

	/**
	 * inseri o imóvel economia e o cliente imovel economia do imóvel
	 * subcategoria
	 * 
	 * @param imoveisEconomias
	 *            Description of the Parameter
	 */
	public void informarImovelEconomias(Collection imoveisEconomias, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * remove o imóvel economia e o cliente imovel economia do imóvel
	 * subcategoria
	 * 
	 * @param imovelEconomia
	 *            Description of the Parameter
	 */
	public void removerImovelEconomia(ImovelEconomia imovelEconomia, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public void atualizarImovel(Imovel imovel,Usuario usuario) throws ControladorException;
	
	/**
	 * Transferir Imóvel para outro logradouro
	 *
	 * @author Davi Menezes
	 * @date 05/08/2011
	 *
	 * @param imovel, usuarioLogado
	 * @throws ControladorException
	 */
	
	public void transferirImovel(Imovel imovel,Usuario usuario) throws ControladorException;
	
	/**
	 * Atualizar os dados do Faturamento do Imóvel
	 *
	 * @author Mariana Victor
	 * @date 11/11/2010
	 *
	 * @param imovel
	 * @throws ControladorException
	 */
	
	public void atualizarImovelAlterarFaturamento(Imovel imovel, Usuario usuarioLogado) throws ControladorException;
	
	/**
	 * Atualizar Imóvel
	 *
	 * @author Raphael Rossiter
	 * @date 20/08/2008
	 *
	 * @param inserirImovelHelper
	 * @throws ControladorException
	 */
	public void atualizarImovel(InserirImovelHelper inserirImovelHelper)
			throws ControladorException;

	/**
	 * verifica se existe algum iptu no imovel ou imovelEconomia
	 * 
	 * @param imoveisEconomia
	 *            Descrição do parâmetro
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param numeroIptu
	 *            Description of the Parameter
	 * @param dataUltimaAlteracao
	 *            Descrição do parâmetro
	 */
	public void verificarExistenciaIPTU(Collection imoveisEconomia,
			Imovel imovel, String numeroIptu, Date dataUltimaAlteracao)
			throws ControladorException;

	/**
	 * verifica se existe algum numero da celpe no imovel ou imovelEconomia
	 * 
	 * @param imoveisEconomia
	 *            Descrição do parâmetro
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param numeroCelpe
	 *            Description of the Parameter
	 * @param dataUltimaAlteracao
	 *            Descrição do parâmetro
	 */
	public void verificarExistenciaCelpe(Collection imoveisEconomia,
			Imovel imovel, String numeroCelpe, Date dataUltimaAlteracao)
			throws ControladorException;

	
	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * @author Pedro Alexandre
	 * @date 18/11/2006
	 *
	 * @param ids
	 * @param usuarioLogado
	 * @throws ControladorException
	 */
	public void removerImovel(String[] ids, Usuario usuarioLogado) throws ControladorException;

	/**
	 * Pesquisa uma coleção de imóveis com uma query especifica
	 * 
	 * @param idLocalidade
	 *            parametros para a consulta
	 * @param idSetorComercial
	 *            parametros para a consulta
	 * @param idQuadra
	 *            parametros para a consulta
	 * @param lote
	 *            Descrição do parâmetro
	 * @return Description of the Return Value
	 */
	public Collection pesquisarImovel(Integer idLocalidade,
			Integer idSetorComercial, Integer idQuadra, Short lote)
			throws ControladorException;

	/**
	 * Atualiza apenas os dados (Localidade, Setor, Quadra e lote) do imóvel
	 * 
	 * @param imovel
	 *            parametros para a consulta
	 */
	public void atualizarImovelInscricao(Imovel imovel, Usuario usuario)
			throws ControladorException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param imovelSubcategoria
	 *            Descrição do parâmetro
	 */
	public void atualizarImovelSubCategoria(
			ImovelSubcategoria imovelSubcategoria) throws ControladorException;

	public Collection filtrarImovelOutrosCriterios(String tipoMedicao)
			throws ControladorException;

	public Collection<Imovel> pesquisarImovelParametrosClienteImovel(
			FiltroClienteImovel filtroClienteImovel)
			throws ControladorException;

	// ----- Metodo Para Carregar o Objeto ImovelMicromedicao
	// ----- Flávio Leonardo
	public Collection carregarImovelMicromedicao(Collection imoveisMicromedicao)
			throws ControladorException;

	public Categoria obterDescricoesCategoriaImovel(Imovel imovel)
			throws ControladorException;

	/**
	 * [UC0185] Obter VAlor por Categoria Author: Rafael Santos Data: 29/12/2005
	 * Rateia um daterminado valore entre as categorias do imóvel
	 * 
	 * @param colecaoCategorias
	 *            Coleção de Categorias
	 * @param valor
	 *            Valor
	 * @return Coleção com os valores por categorias
	 */
	public Collection obterValorPorCategoria(
			Collection<Categoria> colecaoCategorias, BigDecimal valor);

	/**
	 * Atualiza uma categoria no sistema
	 * 
	 * @author Roberta Costa
	 * @date 29/12/2005
	 * @param categoria
	 *            Categoria a ser atualizada
	 */
	public void atualizarCategoria(Categoria categoria)
			throws ControladorException;

	/**
	 * Insere uma categoria no sistema
	 * 
	 * @author Roberta Costa
	 * @date 04/01/2006
	 * @param categoria
	 *            Categoria a ser inserida
	 */
	public Integer inserirCategoria(Categoria categoria)
			throws ControladorException;

	/**
	 * Atualiza uma subcategoria no sistema
	 * 
	 * @author Fernanda Paiva
	 * @date 09/01/2006
	 * @param subcategoria
	 *            Subcategoria a ser atualizada
	 */
	public void atualizarSubcategoria(Subcategoria subcategoria,
			Subcategoria subcategoriaVelha) throws ControladorException;

	/**
	 * Pesquisar Imovel Outros Criterios
	 * 
	 * @author Rhawi Dantas
	 * @date 09/01/2006
	 * 
	 * 
	 */

	/*--<merge>--
	 public Collection pesquisarImovelOutrosCriterios(
	 FiltrarImovelOutrosCriteriosHelper filtrarImovelOutrosCriteriosHelper)
	 throws ControladorException;*/

	/**
	 * [UC0156] Informar Situacao Especial Faturamento
	 * 
	 * @author Rhawi Dantas
	 * @created 09/01/2006
	 * 
	 */
	public Collection pesquisarImovelSituacaoEspecialFaturamento(String valor,
			SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper)
			throws ControladorException;

	/**
	 * [UC0177] Informar Situacao Especial de Cobranca
	 * 
	 * @author Rhawi Dantas
	 * @created 09/01/2006
	 * 
	 */
	public Collection pesquisarImovelSituacaoEspecialCobranca(String valor,
			SituacaoEspecialCobrancaHelper situacaoEspecialFaturamentoHelper)
			throws ControladorException;

	/**
	 * [UC0156] Informar Situacao Especial Faturamento
	 * 
	 * @author Rhawi Dantas
	 * @created 09/01/2006
	 * 
	 */
	public int validarMesAnoReferencia(
			SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper)
			throws ControladorException;

	/**
	 * Pesquisa uma coleção de categorias
	 * 
	 * @return Coleção de Categorias
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Collection<Categoria> pesquisarCategoria()
			throws ControladorException;

	/**
	 * [UC0156] Informar Situacao Especial Faturamento
	 * 
	 * @author Rhawi Dantas
	 * @created 16/01/2006
	 * 
	 */
	public void atualizarFaturamentoSituacaoTipo(Collection colecaoIdsImoveis,
			Integer idFaturamentoTipo, Usuario usuario) throws ControladorException;

	/**
	 * [UC0156] Informar Situacao Especial Faturamento
	 * 
	 * @author Rhawi Dantas
	 * @created 16/01/2006
	 * 
	 */
	public void retirarSituacaoEspecialFaturamento(
			SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper, 
			Collection pesquisarImoveisParaSerRetirados)
			throws ControladorException;

	public Collection<Integer> pesquisarImoveisIds(Integer rotaId)
			throws ControladorException;

	public Object pesquisarImovelIdComConta(Integer imovelId,
			Integer anoMesReferencia) throws ControladorException;

	/**
	 * Verifica se o ClienteImovel existe na Coleção do Cliente Imovel do Imovel
	 * Author: Rafael Santos Data: 01/02/2006
	 * 
	 * @param colecaoClienteImovel
	 *            Coleção Cliente Imovel
	 * @param clienteImovel
	 *            Cliente Imovel
	 * @return true se o cliente imovel existe, false caso contrário
	 */
	public boolean verificarExistenciaClienteImovel(
			Collection colecaoClienteImovel, ClienteImovel clienteImovel);

	/**
	 * Verifica se ImovelSubCategoria existe na Coleção de SubCategoria do
	 * Imovel Author: Rafael Santos Data: 01/02/2006
	 * 
	 * @param colecaoSubCategoria
	 *            Coleção Imovel Sub Categoria
	 * @param imovelSubcategoria
	 *            ImovelSubcategoria
	 * @return true se a Imovel SubCategoria existe, false caso contrário
	 */
	public boolean verificarExistenciaImovelSubcategoria(
			Collection colecaoImovelSubCategoria,
			ImovelSubcategoria imovelSubcategoria);

	/**
	 * Metodo que verifica se ja tem um imovel ou um imovel economia com o
	 * numero do iptu passado caso a pessoa passe o idSetorComercial verifica
	 * apenas no mesmo municipio
	 * 
	 * @param numeroIptu
	 * @param idSetorComercial
	 * @throws ControladorException
	 */
	public void verificarExistenciaIPTU(String numeroIptu,
			Integer idSetorComercial) throws ControladorException;

	/**
	 * Metodo que verifica se ja tem um imovel ou um imovel economia com o
	 * numero do iptu passado caso a pessoa passe o idSetorComercial verifica
	 * apenas no mesmo municipio
	 * 
	 * @param numeroIptu
	 * @param idSetorComercial
	 * @throws ControladorException
	 */
	public void verificarExistenciaCELPE(String numeroCelp,
			Integer idSetorComercial) throws ControladorException;

	public Integer verificarExistenciaImovel(Integer idImovel)
			throws ControladorException;

	/**
	 * Pesquisa o maior ano mes de referencia da tabela de faturamento grupo
	 * 
	 * [UC0177] Informar Situacao Especial de Cobrança
	 * 
	 * @author Sávio Luiz
	 * @date 18/03/2006
	 * 
	 * @param situacaoEspecialCobrancaHelper
	 * @return
	 */
	public Integer validarMesAnoReferenciaCobranca(
			SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper)
			throws ControladorException;

	/**
	 * Atualiza o id da cobrança situação tipo da tabela imóvel com o id da
	 * situação escolhido pelo usuario
	 * 
	 * [UC0177] Informar Situacao Especial de Cobrança
	 * 
	 * @author Sávio Luiz
	 * @date 18/03/2006
	 * 
	 * @param situacaoEspecialCobrancaHelper
	 * @return
	 */
	public void atualizarCobrancaSituacaoTipo(Collection colecaoIdsImoveis,
			Integer idCobrancaTipo, Usuario usuario) throws ControladorException;

	/**
	 * Seta para null o id da cobrança situação tipo da tabela imóvel
	 * 
	 * [UC0177] Informar Situacao Especial de Cobrança
	 * 
	 * @author Sávio Luiz
	 * @date 18/03/2006
	 * 
	 * @param situacaoEspecialCobrancaHelper
	 * @return
	 */
	public void retirarSituacaoEspecialCobranca(SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper,
			Collection imoveisParaSerRetirados,Usuario usuario)throws ControladorException;

	/**
	 * Permite efetuar o parcelamento dos débitos de um imóvel
	 * 
	 * [UC0214] Efetuar Parcelamento de Débitos
	 * 
	 * Obtém as Opções de Parcelamento do Débito do Imóvel
	 * 
	 * [SF0002] Obter Opções Parcelamento
	 * 
	 * Obtém o perfil do imóvel
	 * 
	 * @author Roberta Costa
	 * @date 21/03/2006
	 * 
	 * @param situacaoAguaId
	 * @param situacaoEsgotoId
	 * @return ImovelSituacao
	 */

	public ImovelSituacao obterSituacaoImovel(Integer situacaoAguaId,
			Integer situacaoEsgotoId) throws ControladorException;

	/**
	 * Inseri uma coleção de pagamentos no sistema
	 * 
	 * [UC0265] Inserir Pagamentos
	 * 
	 * Este fluxo secundário tem como objetivo pesquisar o imóvel digitado pelo
	 * usuário
	 * 
	 * [FS0008] - Verificar existência da matrícula do imóvel
	 * 
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 * 
	 * @param idImovelDigitado
	 * @return
	 * @throws ControladorException
	 */
	public Imovel pesquisarImovelDigitado(Integer idImovelDigitado)
			throws ControladorException;

	/**
	 * Inseri uma coleção de pagamentos no sistema
	 * 
	 * [UC0265] Inserir Pagamentos
	 * 
	 * Verifica se a localidade informada é a mesma do imóvel informado
	 * 
	 * [FS0009] - Verificar localidade da matrícula do imóvel
	 * 
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 * 
	 * @param idLocalidadeInformada
	 * @param imovelInformado
	 * @return
	 * @throws ControladorException
	 */
	public boolean verificarLocalidadeMatriculaImovel(
			String idLocalidadeInformada, Imovel imovelInformado)
			throws ControladorException;

	/**
	 * Inseri uma coleção de pagamentos no sistema
	 * 
	 * [UC0265] Inserir Pagamentos
	 * 
	 * Verifica se o usuário informou o imóvel ou o cliente, não pode existir os
	 * doi nem nenhum
	 * 
	 * [FS0010] Verificar preenchimento do imóvel e do cliente
	 * 
	 * @author Pedro Alexandre
	 * @date 24/03/2006
	 * 
	 * @param idImovel
	 * @param idCliente
	 * @throws ControladorException
	 */
	public void verificarPreeenchimentoImovelECliente(String idImovel,
			String idCliente) throws ControladorException;

	/**
	 * [UC0224] Inserir Situação do imóvel
	 * 
	 * Verifica se o usuário informou o tipo da situação do imóvel
	 * 
	 * [FS0001] Verificar preenchimento do imóvel
	 * 
	 * @author Rômulo Aurélio
	 * @date 29/03/2006
	 * 
	 * @param idImovelSituacaoTipo
	 * @param idLigacaoAguaSituacao
	 * @param idLigacaoEsgotoSituacao
	 * @throws ControladorException
	 */
	public Integer inserirSituacaoImovel(String idImovelSituacaoTipo,
			String idLigacaoAguaSituacao, String idLigacaoEsgotoSituacao)
			throws ControladorException;

	/**
	 * Obtém o indicador de existência de hidrômetro para o imóvel, caso exista
	 * retorna 1(um) indicando SIM caso contrário retorna 2(dois) indicando NÃO
	 * 
	 * [UC0307] Obter Indicador de Existência de Hidrômetro no Imóvel
	 * 
	 * @author Pedro Alexandre
	 * @date 18/04/2006
	 * 
	 * @param idImovel
	 * @return
	 * @throws ControladorException
	 */
	public int obterIndicadorExistenciaHidrometroImovel(Integer idImovel)
			throws ControladorException;

	/**
	 * Obtém a principal categoria do imóvel
	 * 
	 * [UC0306] Obter Principal Categoria do Imóvel
	 * 
	 * @author Pedro Alexandre
	 * @date 18/04/2006
	 * 
	 * @param idImovel
	 * @return
	 * @throws ControladorException
	 */
	public Categoria obterPrincipalCategoriaImovel(Integer idImovel)
			throws ControladorException;

	/**
	 * Atualiza campos de imovel na execução de ordem Serviço
	 * 
	 * @author Leandro Cavalcanti
	 * @throws ControladorException
	 */
	public void atualizarImovelExecucaoOrdemServicoLigacaoAgua(Imovel imovel,
			LigacaoAguaSituacao situacaoAgua) throws ControladorException;

	/**
	 * Atualiza campos de imovel na execução de ordem Serviço
	 * 
	 * @author Leandro Cavalcanti
	 * @throws ControladorException
	 */
	public void atualizarImovelExecucaoOrdemServicoLigacaoEsgoto(Imovel imovel,
			LigacaoEsgotoSituacao situacaoEsgoto) throws ControladorException;

	/**
	 * Gerar Relatório de Imóveis Outros Critérios
	 * 
	 * @author Rafael Corrêa
	 * @date 25/07/2006
	 * 
	 * @param idImovelCondominio
	 * @param idImovelPrincipal
	 * @param idNomeConta
	 * @param idSituacaoLigacaoAgua
	 * @param consumoMinimoInicialAgua
	 * @param consumoMinimoFinalAgua
	 * @param idSituacaoLigacaoEsgoto
	 * @param consumoMinimoInicialEsgoto
	 * @param consumoMinimoFinalEsgoto
	 * @param intervaloValorPercentualEsgotoInicial
	 * @param intervaloValorPercentualEsgotoFinal
	 * @param intervaloMediaMinimaImovelInicial
	 * @param intervaloMediaMinimaImovelFinal
	 * @param intervaloMediaMinimaHidrometroInicial
	 * @param intervaloMediaMinimaHidrometroFinal
	 * @param idImovelPerfil
	 * @param idPocoTipo
	 * @param idFaturamentoSituacaoTipo
	 * @param idCobrancaSituacaoTipo
	 * @param idSituacaoEspecialCobranca
	 * @param idEloAnormalidade
	 * @param areaConstruidaInicial
	 * @param areaConstruidaFinal
	 * @param idCadastroOcorrencia
	 * @param idConsumoTarifa
	 * @param idGerenciaRegional
	 * @param idLocalidadeInicial
	 * @param idLocalidadeFinal
	 * @param setorComercialInicial
	 * @param setorComercialFinal
	 * @param quadraInicial
	 * @param quadraFinal
	 * @param loteOrigem
	 * @param loteDestno
	 * @param cep
	 * @param logradouro
	 * @param bairro
	 * @param municipio
	 * @param idTipoMedicao
	 * @param indicadorMedicao
	 * @param idSubCategoria
	 * @param idCategoria
	 * @param quantidadeEconomiasInicial
	 * @param quantidadeEconomiasFinal
	 * @param diaVencimento
	 * @param idCliente
	 * @param idClienteTipo
	 * @param idClienteRelacaoTipo
	 * @param numeroPontosInicial
	 * @param numeroPontosFinal
	 * @param numeroMoradoresInicial
	 * @param numeroMoradoresFinal
	 * @param idAreaConstruidaFaixa
	 * @return
	 * @throws ControladorException
	 */
	public Collection gerarRelatorioImovelOutrosCriterios(
			String idImovelCondominio, String idImovelPrincipal,
			String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
			String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,
			String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto,
			String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,

			String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal,

			String idImovelPerfil, String idPocoTipo,
			String idFaturamentoSituacaoTipo, String idCobrancaSituacaoTipo,
			String idSituacaoEspecialCobranca, String idEloAnormalidade,
			String areaConstruidaInicial, String areaConstruidaFinal,
			String idCadastroOcorrencia, String idConsumoTarifa,
			String idGerenciaRegional, String idLocalidadeInicial,
			String idLocalidadeFinal, String setorComercialInicial,
			String setorComercialFinal, String quadraInicial,
			String quadraFinal, String loteOrigem, String loteDestno,
			String cep, String logradouro, String bairro, String municipio,
			String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo,
			String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial,
			String numeroMoradoresFinal, String idAreaConstruidaFaixa,String idUnidadeNegocio,String cdRotaInicial,
			String cdRotaFinal, String sequencialRotaInicial,
			String sequencialRotaFinal)
			throws ControladorException;

	/**
	 * 
	 * [UC0164] Filtrar Imoveis por Outros Criterios
	 * 
	 * Filtra para saber a quantidade de imoveis antes de executar o filtro
	 * 
	 * @author Rafael Santos
	 * @date 01/08/2006
	 * 
	 */
	public Integer obterQuantidadaeRelacaoImoveisDebitos(
			String idImovelCondominio, String idImovelPrincipal,
			String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
			String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,
			String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto,
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
			String numeroMoradoresFinal, String idAreaConstruidaFaixa,String idUnidadeNegocio,
			Integer relatorio,String cdRotaInicial,
			String cdRotaFinal, String sequencialRotaInicial,
			String sequencialRotaFinal) throws ControladorException;

	/**
	 * O método abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necessários para a criação da inscrição do imóvel para exibição.
	 * 
	 * aki é montada a inscrição
	 */
	public String pesquisarInscricaoImovel(Integer idImovel)
			throws ControladorException;

	/**
	 * Gerar Relatório de Dados das Economias do Imóvel
	 * 
	 * @author Rafael Corrêa
	 * @date 01/08/2006
	 * 
	 * @param idImovelCondominio
	 * @param idImovelPrincipal
	 * @param idNomeConta
	 * @param idSituacaoLigacaoAgua
	 * @param consumoMinimoInicialAgua
	 * @param consumoMinimoFinalAgua
	 * @param idSituacaoLigacaoEsgoto
	 * @param consumoMinimoInicialEsgoto
	 * @param consumoMinimoFinalEsgoto
	 * @param intervaloValorPercentualEsgotoInicial
	 * @param intervaloValorPercentualEsgotoFinal
	 * @param intervaloMediaMinimaImovelInicial
	 * @param intervaloMediaMinimaImovelFinal
	 * @param intervaloMediaMinimaHidrometroInicial
	 * @param intervaloMediaMinimaHidrometroFinal
	 * @param idImovelPerfil
	 * @param idPocoTipo
	 * @param idFaturamentoSituacaoTipo
	 * @param idCobrancaSituacaoTipo
	 * @param idSituacaoEspecialCobranca
	 * @param idEloAnormalidade
	 * @param areaConstruidaInicial
	 * @param areaConstruidaFinal
	 * @param idCadastroOcorrencia
	 * @param idConsumoTarifa
	 * @param idGerenciaRegional
	 * @param idLocalidadeInicial
	 * @param idLocalidadeFinal
	 * @param setorComercialInicial
	 * @param setorComercialFinal
	 * @param quadraInicial
	 * @param quadraFinal
	 * @param loteOrigem
	 * @param loteDestno
	 * @param cep
	 * @param logradouro
	 * @param bairro
	 * @param municipio
	 * @param idTipoMedicao
	 * @param indicadorMedicao
	 * @param idSubCategoria
	 * @param idCategoria
	 * @param quantidadeEconomiasInicial
	 * @param quantidadeEconomiasFinal
	 * @param diaVencimento
	 * @param idCliente
	 * @param idClienteTipo
	 * @param idClienteRelacaoTipo
	 * @param numeroPontosInicial
	 * @param numeroPontosFinal
	 * @param numeroMoradoresInicial
	 * @param numeroMoradoresFinal
	 * @param idAreaConstruidaFaixa
	 * @return
	 * @throws ControladorException
	 */
	public Collection gerarRelatorioDadosEconomiaImovel(
			String idImovelCondominio, String idImovelPrincipal,
			String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
			String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,
			String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto,
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
			String numeroMoradoresFinal, String idAreaConstruidaFaixa,String idUnidadeNegocio)
			throws ControladorException;

	/**
	 * 
	 * Esse método é usado para fzazer uma pesquisa na tabela imóvel e confirmar
	 * se o id passado é de um imóvel excluído(idExclusao)
	 * 
	 * Flávio Cordeiro
	 * 
	 * @throws ErroRepositorioException
	 */

	public Boolean confirmarImovelExcluido(Integer idImovel);

	/**
	 * Permite pesquisar entidades beneficentes
	 * [UC0389] Inserir Autorização para Doação Mensal
	 * @author  César Araújo
	 * @date    30/08/2006
	 * @param   idEntidadeBeneficente - Código da entidade beneficente
	 * @return  Collection<EntidadeBeneficente> - Coleção de entidades beneficentes
	 * @throws  ControladorException
	 */
	public Collection<EntidadeBeneficente> pesquisarEntidadeBeneficente(
			Integer idEntidadeBeneficente) throws ControladorException;

	/**
	 * Permite pesquisar imóveis doação 
	 * [UC0389] Inserir Autorização para Doação Mensal
	 * @author  César Araújo
	 * @date    30/08/2006
	 * @param   idImovelDoacao - Código do imóvel doação
	 * @return  Collection<ImovelDoacao> - Coleção de imóveis doação
	 * @throws  ControladorException
	 */
	public Collection<ImovelDoacao> pesquisarImovelDoacao(FiltroImovelDoacao filtroImovelDoacao)
			throws ControladorException;

	/**
	 * Permite verificar se existe um determinado imóvel doação 
	 * [UC0389] Inserir Autorização para Doação Mensal
	 * @author  César Araújo
	 * @date    30/08/2006
	 * @param   idImovel - Código do imóvel
	 * @param   idEntidadeBeneficente - Código da entidade beneficente
	 * @return  ImovelDoacao - Retorna um imóvel doação caso a combinação de imóvel e entidade beneficente exista.  
	 * @throws  ControladorException
	 */
	public ImovelDoacao verificarExistenciaImovelDoacao(Integer idImovel,
			Integer idEntidadeBeneficente) throws ControladorException;

	/**
	 * Permite atualizar as informações do imóvel doação 
	 * [UC0390] Manter Autorização para Doação Mensal
	 * @author  César Araújo,Pedro Alexandre
	 * @date    30/08/2006, 17/11/2006
	 * @param   imovelDoacao - Código do imóveo doação
     * @param   usuarioLogado - Usuário logado no sistema
	 * @throws  ControladorException
	 */
	public void atualizarImovelDoacao(ImovelDoacao imovelDoacao, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * Pesquisa um imóvel a partir do seu id.Retorna os dados que compõem a
	 * inscrição e o endereço do mesmo
	 * 
	 * @author Raphael Rossiter
	 * @date 01/08/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Imovel pesquisarImovelRegistroAtendimento(Integer idImovel)
			throws ControladorException;

	/**
	 * Verifica a existência do hidômetro de acordo com tipo de medição
	 * informado (MedicaoTipo.LIGACAO_AGUA ou MedicaoTipo.POCO).
	 * 
	 * [UC0368] Atualizar Instalação do Hidrômetro
	 * 
	 * [FS0003] - Validar existência do hidrômetro
	 * 
	 * @author lms
	 * @created 24/07/2006
	 * @throws ControladorException
	 * 
	 */
	public void validarExistenciaHidrometro(Imovel imovel, Integer medicaoTipo)
			throws ControladorException;

	/**
	 * Verifica a existência da matrícula do imóvel. Caso exista, verifica se o
	 * imóvel está ativo.
	 * 
	 * [UC0368] Atualizar Instalação do Hidrômetro
	 * 
	 * [FS0001] - Verificar a existência da matrícula do imóvel [FS0002] -
	 * Verificar a situação do imóvel
	 * 
	 * @author lms
	 * @created 19/07/2006
	 * @throws ControladorException
	 * 
	 */
	public Imovel pesquisarImovelSituacaoAtiva(FiltroImovel filtroImovel)
			throws ControladorException;

	/**
	 * Faz o controle de concorrencia do imovel
	 * 
	 * @author Rafael Pinto
	 * @throws ControladorException
	 */
	public void verificarImovelControleConcorrencia(Imovel imovel)
			throws ControladorException;

	/**
	 * Consulta os Dados Cadastrais do Imovel
	 * 
	 * @author Rafael Santos
	 * @date 07/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Imovel consultarImovelDadosCadastrais(Integer idImovel)
			throws ControladorException;

	/**
	 * Consulta os Clientes do Imovel [UC0472] Consultar Imovel
	 * 
	 * @author Rafael Santos
	 * @date 07/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarClientesImovel(Integer idImovel)
			throws ControladorException;
	
	/**
	 * Consulta os Clientes do Imovel [UC0472] Consultar Imovel
	 * 
	 * @author Breno Santos
	 * @date 07/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarClientesImovelDataMax(Integer idImovel)
			throws ControladorException;
	
	/**
	 * Pesquisa a coleção de clientes do imovel mesmo que o imóvel já tenha sido excluído [UC0472] Consultar Imovel
	 * 
	 * @param filtroClienteImovel
	 * parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ControladorException
	 * Description of the Exception
	 */
	public Collection pesquisarClientesImovelExcluidoOuNao(Integer idImovel)
			throws ControladorException;

	/**
	 * Pesquisa a coleção de categorias do imovel [UC0472] Consultar Imovel
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarCategoriasImovel(Integer idImovel)
			throws ControladorException;

	/**
	 * [UC0475] Consultar Perfil Imovel
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param idImovel
	 * @return Perfil do Imóvel
	 * @exception ControladorException
	 */
	public ImovelPerfil obterImovelPerfil(Integer idImovel)
			throws ControladorException;

	/**
	 * Consulta os Dados Complementares do Imovel
	 * 
	 * @author Rafael Santos
	 * @date 11/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Imovel consultarImovelDadosComplementares(Integer idImovel)
			throws ControladorException;

	/**
	 * Pesquisa a coleção de vencimento alternativos do imovel [UC0473]
	 * Consultar Imovel Dados Complementares
	 * 
	 * @author Rafael Santos
	 * @date 11/09/2006
	 * 
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarVencimentoAlternativoImovel(Integer idImovel)
			throws ControladorException;

	/**
	 * Pesquisa a coleção de Debitos Automaticos do imovel [UC0473] Consultar
	 * Imovel Dados Complementares
	 * 
	 * @author Rafael Santos
	 * @date 11/09/2006
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarDebitosAutomaticosImovel(Integer idImovel)
			throws ControladorException;

	/**
	 * Pesquisa a coleção de Faturamento Situação Historico do Imovel [UC0473]
	 * Consultar Imovel Dados Complementares
	 * 
	 * @author Rafael Santos
	 * @date 11/09/2006
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarFaturamentosSituacaoHistorico(Integer idImovel)
			throws ControladorException;

	/**
	 * Pesquisa a coleção de cobranças Situação Historico do Imovel [UC0473]
	 * Consultar Imovel Dados Complementares
	 * 
	 * @author Rafael Santos
	 * @date 11/09/2006
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarCobrancasSituacaoHistorico(Integer idImovel)
			throws ControladorException;

	/**

	 * Consutlar os Dados de Analise da Medição e Consumo do Imovel [UC0473]
	 * Consultar Imóvel
	 * 
	 * @author Rafael Santos
	 * @date 12/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Imovel consultarImovelAnaliseMedicaoConsumo(Integer idImovel)
			throws ControladorException;

	
	/**
	 * Consultar os Dados do Historico de Faturamento 
	 * [UC0473] Consultar Imóvel
	 * 
	 * @author Rafael Santos
	 * @date 13/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Imovel consultarImovelHistoricoFaturamento(Integer idImovel)
			throws ControladorException;
	
	/**
	 * Consultar o cliente usuário do Imovel 
	 * [UC0473] Consultar Imóvel
	 * 
	 * @author Rafael Santos
	 * @date 13/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public String consultarClienteUsuarioImovel(Integer idImovel)
			throws ControladorException;
	
	/**
	 * Consultar o cliente usuário do Imovel 
	 * [UC 0275] Gerar Resumo Ligacao Agua
	 * 
	 * @author Bruno Barros
	 * @date 27/04/2007
	 * 
	 * @param imovel
	 * @return Cliente
	 * @throws ControladorException
	 */
	public Cliente consultarClienteUsuarioImovel(Imovel imovel)
			throws ControladorException;	
	
	/**
	 * Consultar as contas do Imovel 
	 * [UC0473] Consultar Imóvel
	 * 
	 * @author Rafael Santos
	 * @date 13/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection consultarContasImovel(Integer idImovel)
			throws ControladorException;
	
	/**
	 * Consultar as contas Historicos do Imovel [UC0473] Consultar Imóvel
	 * 
	 * @author Rafael Santos
	 * @date 13/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection consultarContasHistoricosImovel(Integer idImovel)
			throws ControladorException;			
	
	/**
	 * 
	 * Registrar leituras e anormalidades
	 * 
	 * @author Sávio Luiz
	 * @date 12/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public void atualizarImovelLeituraAnormalidade(
			Map<Integer, MedicaoHistorico> mapAtualizarLeituraAnormalidadeImovel,Date dataAtual)
			throws ControladorException;
	
	/**
	 * Consultar os dados de parcelamentos do Imovel 
	 * [UC0473] Consultar Imóvel
	 * 
	 * @author Rafael Santos
	 * @date 20/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Imovel consultarParcelamentosDebitosImovel(Integer idImovel)
			throws ControladorException;	
	
	
	/**
	 *  [UC0366] Inserir Registro de Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 27/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Cliente pesquisarClienteUsuarioImovel(Integer idImovel) throws ControladorException;
	
	/**
	 * 
	 * [UC0488] - Informar Retorno Ordem de Fiscalização [SB0002] Atualizar
	 * Imóvel/Ligação de Água
	 * 
	 * 
	 * @date 14/11/2006
	 * @author Sávio Luiz
	 * @param imovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarImovelLigacaoAguaEsgoto(Integer idImovel,
			Integer idLigacaoAguaSituacao, Integer idLigacaoEsgotoSituacao,
			short indicadorLigacaoAtualizada, short indiacadorConsumoFixado,
			Integer consumoDefinido, short indicadorVolumeFixado)
			throws ControladorException;
	
    /**
     * Responsável pela inserção de um Imóvel Doação
     *
     * [UC0389] - Inserir Imovel Doacao
     *
     * @author César Araújo, Pedro Alexandre
     * @date 03/08/2006, 16/11/2006
     *
     * @param imovelDoacao
     * @param usuarioLogado
     * @return
     * @throws ControladorException
     */
    public Integer inserirImovelDoacaoRetorno(ImovelDoacao imovelDoacao, Usuario usuarioLogado) throws ControladorException ;
    
    /**
	 * 
	 * [UC0488] - Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * @date 14/11/2006
	 * @author Sávio Luiz
	 * @param imovel
	 * @throws ErroRepositorioException
	 */
	public Integer recuperarIdConsumoTarifa(Integer idImovel)
			throws ControladorException;
	
	/**
	 * 
	 * Filtrar o Imovel pelos parametros informados
	 *
	 * @author Rafael Santos
	 * @date 27/11/2006
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImovel(
			String idImovel, 
			String idLocalidade,
		    String codigoSetorComercial,
		    String numeroQuadra,
		    String lote,
		    String subLote,
		    String codigoCliente,
		    String idMunicipio,
		    String cep,
		    String idBairro,
		    String idLogradouro, String numeroImovelInicial, String numeroImovelFinal,
		    boolean pesquisarImovelManterVinculo,boolean pesquisarImovelCondominio, Integer numeroPagina) throws ControladorException ;		
	
	/**
	 * 
	 * Pesquisa a quantidade de Imoveis
	 *
	 * @author Rafael Santos
	 * @date 27/11/2006
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeImovel(
			String idImovel, 
			String idLocalidade,
		    String codigoSetorComercial,
		    String numeroQuadra,
		    String lote,
		    String subLote,
		    String codigoCliente,
		    String idMunicipio,
		    String cep,
		    String idBairro,
		    String idLogradouro, String numeroImovelInicial, String numeroImovelFinal,
		    boolean pesquisarImovelManterVinculo, boolean pesquisarImovelCondominio) throws ControladorException ;		
	
	/**
	 * 
	 * Pesquisa o Imovel pelos parametros informados
	 *
	 * @author Rafael Santos
	 * @date 27/11/2006
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImovelInscricao(
			String idImovel,
			String idLocalidade,
		    String codigoSetorComercial,
		    String numeroQuadra,
		    String lote,
		    String subLote,
		    String codigoCliente,
		    String idMunicipio,
		    String cep,
		    String idBairro,
		    String idLogradouro, String numeroImovelInicial, String numeroImovelFinal,  
		    boolean pesquisarImovelCondominio, Integer numeroPagina) throws ControladorException ;
	
	/**
	 * [UC0490] Informar Ocorrencia de Cadastro e/ou Anormalidade de Elo
	 *
	 * @author Tiago Moreno
	 * @date 27/11/2006
	 * 
	 * @param idImovel
	 * @param idOcorrenciaCadastro
	 * @param idAnormalidadeElo
	 * @param dataOcorrenciaCadastro
	 * @param dataAnormalidadeElo
	 * @param uploadPictureCadastro
	 * @param uploadPictureAnormalidade
	 * 
	 * @return
	 * @throws ControladorException
	 * 
	 */
	public void informarOcorrenciaCadastroAnormalidadeElo(
			String idImovel,
			String idOcorrenciaCadastro,
			String idAnormalidadeElo,
			String dataOcorrenciaCadastro,
			String dataAnormalidadeElo,
			byte[] uploadPictureCadastro,
			byte[] uploadPictureAnormalidade, 
			Usuario usuarioLogado, Collection colecaoIdCadastroOcorrenciaRemover,
			Collection colecaoIdAnormalidadeRemover) throws ControladorException;
	
	/**
	 * [UC0367] Informar Retorno Ordem de Fiscalização
	 * 
	 * Recupera o id da situação da ligação de agua
	 * 
	 * @author Sávio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisarIdLigacaoAguaSituacao(Integer idImovel) throws ControladorException;
	
	/**
	 * [UC0367] Informar Retorno Ordem de Fiscalização
	 * 
	 * Recupera o id da situação da ligação de esgoto
	 * 
	 * @author Sávio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisarIdLigacaoEsgotoSituacao(Integer idImovel) throws ControladorException;
	
	/**
	 * [UC0367] Informar Retorno Ordem de Fiscalização
	 * 
	 * Recupera o id da situação da ligacao de agua
	 * 
	 * @author Sávio Luiz
	 * @date 04/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisarTarifaImovel(Integer idImovel)
			throws ControladorException;

	/**
	 * [UC0490] Informar Situação de Cobrança
	 * 
	 * @author Tiago Moreno
	 * @date 09/12/2006
	 * 
	 * @param imovel
	 * @param situacaoCobranca
	 * @param cliente
	 * @param dataImplantação
	 * @param anoMesInicio
	 * @param anoMesFim
	 * 
	 * @return
	 * @throws ControladorException
	 * 
	 */
	public void inserirImovelSitucaoCobranca(Imovel imovel,
			CobrancaSituacao cobrancaSituacao, Cliente cliente,Cliente clienteEscritorio,Cliente clienteAdvogado,
			Date dataImplantacao, Integer anoMesInicio,
			Integer anoMesFim, Usuario usuarioLogado) throws ControladorException;
	
	public void retirarImovelSitucaoCobranca(Integer idImovel, Usuario usuarioLogado,String[] idsImovelCobrancaSituacao) throws ControladorException;
	
	
	
	/**
	 * Pesquisa os imóveis do cliente de acordo com o tipo de relação
	 * 
	 * [UC0251] Gerar Atividade de Ação de Cobrança [SB0001] Gerar Atividade de
	 * Ação de Cobrança para os Imóveis do Cliente
	 * 
	 * @author Leonardo Vieira
	 * @created 12/12/2006
	 * 
	 * @param cliente
	 * @param relacaoClienteImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImoveisClientesRelacao(Cliente cliente,
			ClienteRelacaoTipo relacaoClienteImovel,Integer numeroInicial)
			throws ControladorException;

	
	public Integer pesquisarExistenciaImovelSubCategoria(Integer idImovel,
			Integer idCategoria) throws ControladorException;
	
	
	public Collection pesquisarImoveisPorRota(Integer idRota) throws ControladorException;
	
	
	
	/**
	 * 
	 * [UC0378] Associar Tarifa de Consumo a Imóveis
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @created 20/12/2006
	 * 
	 * @param dLocalidadeInicial,
	 *            idLocalidadeFinal, codigoSetorComercialInicial,
	 *            codigoSetorComercialFinal
	 * @param quadraInicial,
	 *            quadraFinal, String loteInicial, loteFinal, subLoteInicial,
	 *            subLoteFinal, idTarifaAnterior
	 * @return
	 * @throws ControladorException
	 */

	public Collection pesquisarImoveisTarifaConsumo(String idLocalidadeInicial,
			String idLocalidadeFinal, String codigoSetorComercialInicial,
			String codigoSetorComercialFinal, String quadraInicial,
			String quadraFinal, String loteInicial, String loteFinal,
			String subLoteInicial, String subLoteFinal, String idTarifaAnterior)
			throws ControladorException;
	
	
	/**
	 * Atualiza a tarifa de consumo de um ou mais imoveis
	 * 
	 * [UC0378] Associar Tarifa de Consumo a Imóveis
	 * 
	 * @author Rômulo Aurelio
	 * @created 19/12/2006
	 * 
	 * @param matricula,
	 *            tarifaAtual, colecaoImoveis
	 * @return
	 * @throws ControladorException
	 */

	public void atualizarImoveisTarifaConsumo(String matricula,
			String tarifaAtual, Collection colecaoImoveis)
			throws ControladorException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Atualiza o perfil do imóvel para o perfil normal
	 * 
	 * @date 04/01/2007, 07/05/08
	 * @author Rafael Corrêa, Francisco do Nascimento
	 * @throws ErroRepositorioException
	 */
	public void atualizarImovelPerfilNormal(Imovel imovel, RegistradorOperacao registradorOperacao)
			throws ControladorException;
	
	/**
	 * [UC0490] - Informar Situação de Cobrança
	 * 
	 * Pesquisa o imóvel com a situação da ligação de água e a de esgoto
	 * 
	 * @date 13/01/2007
	 * @author Rafael Corrêa
	 * @throws ControladorException
	 */
	public Imovel pesquisarImovelComSituacaoAguaEsgoto(Integer idImovel)
			throws ControladorException;
	
	/**
	 * Pesquisar imoveis por rota, situacao de ligacao de agua e situacao de ligacao
	 * de esgoto, utilizando paginacao
	 * 
	 * Utilizado no  
	 * [UC0251] Gerar Atividade de Ação de Cobrança [SB0001] Gerar Atividade de
	 * Ação de Cobrança para os Imóveis do Cliente
	 * 
	 * @param idRota
	 * @param idsSituacaoLigacaoAgua
	 * @param idsSituacaoLigacaoEsgoto
	 * @param numeroInicial
	 * @return 
	 * @throws ControladorException
	 * 
	 * @author Francisco do Nascimento
	 * @date 21/10/2009
	 * 
	 */	
	public Collection pesquisarImoveisPorRotaComPaginacao(Rota rota,
			Collection idsSituacaoLigacaoAgua, Collection idsSituacaoLigacaoEsgoto,
			int numeroInicial, int numeroMaximo) throws ControladorException ;
	
	/**
	 * [UC0069] - Manter Dados da Tarifa Social
	 * 
	 * [FS0006] - Verificar número de IPTU
	 * 
	 * Verifica se já existe outro imóvel ou economia com o mesmo número de IPTU
	 * no mesmo município
	 * 
	 * @date 18/01/2007
	 * @author Rafael Corrêa
	 * @throws ControladorException
	 */
	public Integer verificarNumeroIptu(String numeroIptu, Integer idImovel,
			Integer idImovelEconomia, Integer idMunicipio)
			throws ControladorException;
	
	/**
	 * [UC0069] - Manter Dados da Tarifa Social
	 * 
	 * [FS0007] - Verificar número de contrato da companhia de energia elétrica
	 * 
	 * Verifica se já existe outro imóvel ou economia com o mesmo número de
	 * contrato da companhia elétrica
	 * 
	 * @date 18/01/2007
	 * @author Rafael Corrêa
	 * @throws ControladorException
	 */
	public Integer verificarNumeroCompanhiaEletrica(Long numeroCompanhiaEletrica, Integer idImovel,
			Integer idImovelEconomia)
			throws ControladorException;
	
	
	/**
	 * Usado pelo Pesquisar Imovel Retorno o Imovel, com o Nome do Cliente,
	 * Matricula e Endereço
	 * 
	 * @author Rafael Santos
	 * @date 27/11/2006
	 */
	/*public Collection pesquisarImovelInscricaoNew(String idImovel,
			String idLocalidade, String codigoSetorComercial,
			String numeroQuadra, String lote, String subLote,
			String codigoCliente, String idMunicipio, String cep,
			String idBairro, String idLogradouro,
			boolean pesquisarImovelCondominio, Integer numeroPagina)
			throws ControladorException ;*/
	
	/**
	 * Obtém o quantidade de economias da subCategoria por imovel
	 * 
	 * @param idImovel 		O identificador do imóvel
	 * @return 				Coleção de SubCategorias
	 * @exception ErroRepositorioException Descrição da exceção
	 */
	public Collection<Subcategoria> obterQuantidadeEconomiasSubCategoria(Integer idImovel)
			throws ControladorException;
	
	/**
	 * @date 21/02/2007
	 * @author Vivianne Sousa
	 * @throws ControladorException
	 */
	public Imovel pesquisarImovel(Integer idImovel)
			throws ControladorException;
	
	
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
			LogradouroCep logradouroCepNovo) throws ControladorException ;
	
	
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
			LogradouroBairro logradouroBairroNovo) throws ControladorException ;
	
	/**
	 * [UC0302] Gerar Débitos a Cobrar de Acréscimos por Impontualidade
	 *
	 * Pequisa o identificador de cobranca de acréscimo pro impontualidade 
	 * para o imóvel do cliente responsável.
	 *
	 * @author Pedro Alexandre
	 * @date 26/02/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ControladorException
	 */
	public Short obterIndicadorGeracaoAcrescimosClienteImovel(Integer idImovel) throws ControladorException ;
	
//	public Integer verificarExistenciaImovelParaCliente(Integer idImovel)
//	throws ControladorException;
//	
	
	/**  
	 * [UC0488] - Informar Retorno Ordem de Fiscalização
	 * 
	 * Obter o consumo médio como não medido para o imóvel passado
	 * 
	 * @author Raphael Rossiter
	 * @date 06/03/2007
	 */
	public Integer obterConsumoMedioNaoMedidoImovel(Imovel imovel) throws ControladorException ;
	
	/**  
	 * Obter a situação de cobrança para o imóvel passado
	 * 
	 * @author Vivianne Sousa
	 * @date 07/03/2007
	 */
	public String obterSituacaoCobrancaImovel(Integer idImovel)
			throws ControladorException;
	
	/**
	 * Pesquisa coleção de imóveis
	 * 
	 * @author Ana Maria
	 * @date 16/03/206
	 */
	public Collection pesquisarColecaoImovel(FiltrarImovelInserirManterContaHelper filtro) 
		throws ControladorException;
	
	/**
	 * 
	 * Pesquisa uma coleção de imóveis com perfil bloqueado
	 * 
	 * @return Boolean
	 * @exception ErroRepositorioException
	 * 
	 */
	public Integer pesquisarColecaoImovelPerfilBloqueado(FiltrarImovelInserirManterContaHelper filtro)
			throws ControladorException;
	
	/**
	 * Pesquisa coleção de imóveis do cliente
	 * 
	 * @author Ana Maria
	 * @date 16/03/206
	 */
	public Collection pesquisarColecaoImovelCliente(Integer codigoCliente, Integer relacaoTipo, Boolean verificarImovelPerfilBloqueado) 
			throws ControladorException;
	
	/**
	 * Pesquisa quantidade de imóveis do cliente com perfil bloqueado
	 * 
	 * @author Ana Maria
	 * @date 20/04/2009
	 */
	public Integer pesquisarColecaoImovelClienteBloqueadoPerfil(Integer codigoCliente,
			Integer relacaoTipo)throws ControladorException;
	
	/**
	 * Seleciona a Subcategoria principal de uma categoria
	 * 
	 * @author Bruno Barros
	 * @date 16/04/2007 
	 * 
	 * @param idCategoria
	 * @return
	 * @throws ControladorException
	 */
	public ImovelSubcategoria obterPrincipalSubcategoria( Integer idCategoria, Integer idImovel )
		throws ControladorException;	
	
	/**  
	 * [UC0150] - Retificar Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 16/04/2007
	 */
	public Collection obterQuantidadeEconomiasContaCategoriaPorSubcategoria(Conta conta)
			throws ControladorException ;
	
	/**  
	 * [UC0150] - Retificar Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 16/04/2007
	 */
	public Collection obterQuantidadeEconomiasContaCategoriaPorSubcategoria(Integer conta)
			throws ControladorException ;
	
	
	
	/**
	 * 
	 * Autor: Raphael Rossiter
	 * Data: 18/04/2007
	 * 
	 * @return Quantidade de subcategorias
	 * @throws ControladorException
	 */
	public Object pesquisarObterQuantidadeSubcategoria()
			throws ControladorException ;
	
	/**
	 * Pesquisa todos os ids do perfil do imóvel.
	 *
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 *
	 * @author Pedro Alexandre
	 * @date 25/04/2007
	 *
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarTodosIdsPerfilImovel() throws ControladorException ;
	
	/**  
	 * [UC0591] - Gerar Relatório de Clientes Especiais
	 * 
	 * Pesquisas os imóveis de acordo com os parâmetros da pesquisa
	 * 
	 * @author Rafael Corrêa
	 * @date 31/05/2007
	 */
	public Collection pesquisarImovelClientesEspeciaisRelatorio(
			String idUnidadeNegocio, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String codigoSetorInicial, String codigoSetorFinal,
			String codigoRotaInicial, String codigoRotaFinal,
			String[] idsPerfilImovel, String[] idsCategoria,
			String[] idsSubcategoria, String idSituacaoAgua,
			String idSituacaoEsgoto, String qtdeEconomiasInicial,
			String qtdeEconomiasFinal, String intervaloConsumoAguaInicial,
			String intervaloConsumoAguaFinal,
			String intervaloConsumoEsgotoInicial,
			String intervaloConsumoEsgotoFinal, String idClienteResponsavel,
			String intervaloConsumoResponsavelInicial,
			String intervaloConsumoResponsavelFinal,
			Date dataInstalacaoHidrometroInicial,
			Date dataInstalacaoHidrometroFinal,
			String[] idsCapacidadesHidrometro, String[] idsTarifasConsumo,
			Integer anoMesFaturamento, String idLeituraAnormalidade,
			String leituraAnormalidade, String idConsumoAnormalidade,
			String consumoAnormalidade)
		throws ControladorException;
	
	/**
	 * 
	 * Pesquisar Grupo do Imovel
	 * 
	 * @author Flavio Cordeiro
	 * @date 18/05/2007
	 *
	 * @param idImovel
	 * @return
	 */
	public FaturamentoGrupo pesquisarGrupoImovel(Integer idImovel);

	
	
	/**
	 * [UC00609] Transferencia de Debitos/Creditos
	 * 
	 * Recupera a situação da ligação de esgoto
	 * 
	 * @author Raphael Rossiter
	 * @date 07/06/2007
	 * 
	 * @param idImovel
	 * @return LigacaoEsgotoSituacao
	 * @throws ErroRepositorioException
	 */
	public LigacaoEsgotoSituacao pesquisarLigacaoEsgotoSituacao(Integer idImovel)
			throws ControladorException ;
	
	
	/**
	 * [UC00609] Transferencia de Debitos/Creditos
	 * 
	 * Recupera a situação da ligação de agua
	 * 
	 * @author Raphael Rossiter
	 * @date 07/06/2007
	 * 
	 * @param idImovel
	 * @return LigacaoAguaSituacao
	 * @throws ErroRepositorioException
	 */
	public LigacaoAguaSituacao pesquisarLigacaoAguaSituacao(Integer idImovel)
			throws ControladorException ;

	
	/**
	 * Gerar Relatório de Imóveis Outros Critérios
	 * 
	 * @author Rafael Corrêa
	 * @date 25/07/2006
	 */
	public Collection gerarRelatorioImovelEnderecoOutrosCriterios(
			String idImovelCondominio, String idImovelPrincipal,
			String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
			String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,
			String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto,
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
			String idUnidadeNegocio, String seqRotaInicial, String seqRotaFinal,
			String rotaInicial, String rotaFinal,
			String ordenacaoRelatorio) throws ControladorException;	

	/**
	 * Gerar Relatório de Imóveis Outros Critérios
	 * 
	 * @author Rafael Corrêa
	 * @date 25/07/2006
	 * 
	 * @throws ControladorException
	 */
	public Collection gerarRelatorioCadastroConsumidoresInscricao(
			String idImovelCondominio, String idImovelPrincipal,
			String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
			String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,
			String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto,
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
			String idUnidadeNegocio, String cdRotaInicial,
			String cdRotaFinal, String sequencialRotaInicial,
			String sequencialRotaFinal, String ordenacao, String[] pocoTipoListIds) throws ControladorException;
	
	
	/**
	 * Filtra o Pagamento Historico pelo seu id carregando os dados necessários
	 * 
	 * [UC0549] Consultar Dados do Pagamento
	 * 
	 * @author Kássia Albuquerque
	 * @date 12/07/2007
	 
	 * @throws ControladorException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoPeloId(Integer idPagamentoHistorico)throws ControladorException  ;
	
	
	
	/**
	 * Filtra o Pagamento pelo seu id carregando os dados necessários
	 * 
	 * [UC0549] Consultar Dados do Pagamento
	 * 
	 * @author Kássia Albuquerque
	 * @date 12/07/2007
	 * 
	 * @throws ControladorException
	 */
	public Collection<Pagamento> pesquisarPagamentoPeloId(Integer idPagamento)throws ControladorException ;
	
	
	
	/**
	 * [UC0549] Consultar Dados de um Pagamento
	 * 
	 * @author Kassia Albuquerque
	 * @date 05/07/2007
	 * 
	 * @throws ErroRepositorioException
	 */
	public Cliente obterDescricaoIdCliente(Integer idImovel)throws ControladorException ;
	
	
	
	/**
	 * [UC0549] Consultar Dados de um Pagamento
	 * 
	 * @author Kassia Albuquerque
	 * @date 09/07/2007
	 * 
	 * @throws ErroRepositorioException
	 */
	public String pesquisarNomeAgenteArrecadador(Integer idPagamentoHistorico)throws ControladorException ;
	
	/**
	 * Retorna o cep do imóvel
	 * 
	 * @param imovel
	 * 
	 * @return Descrição do retorno
	 * 
	 * @exception ControladorException
	 * 
	 */
	public Cep pesquisarCepImovel(Imovel imovel)
			throws ControladorException;
	
	/**
	 * Obtém a o 117º caracter de uma String
	 *
	 * @author Kassia Albuquerque
	 * @date 20/07/2007
	 *
	 * @return String
	 * @throws ControladorException
	 */

	public String pesquisarCaracterRetorno(Integer idConteudoRegistro)throws ControladorException ;
	
	/**
	 * [UC0623] - GERAR RESUMO DE METAS EXECUTDO NO MÊS(CAERN)
	 * 
	 * @author Sávio Luiz
	 * @date 08/08/2007
	 */
	public ImovelSubcategoria obterPrincipalSubcategoriaComCodigoGrupo(
			Integer idCategoria, Integer idImovel) throws ControladorException;
	
	/**
	 * Obtém a principal categoria da Conta
	 * 
	 * [UC0000] Obter Principal Categoria da Conta
	 * 
	 * @author Ivan Sérgio
	 * @date 08/08/2007
	 * 
	 * @param idConta
	 * @return
	 * @throws ControladorException
	 */
	public Categoria obterPrincipalCategoriaConta(Integer idConta)
			throws ControladorException;
	
	/**
	 * O método abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necessários para a criação da inscrição do imóvel para exibição.
	 * 
	 * aki é montada a inscrição
	 */
	public Object[] pesquisarLocalidadeSetorImovel(Integer idImovel)
			throws ControladorException;
	
	/**
	 * 
	 * @author Sávio Luiz
	 * @date 24/08/2007
	 * 
	 * @return String
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarSequencialRota(Integer idImovel)
			throws ControladorException;
    
    /**
     * [UC0541] Emitir 2 Via de Conta Internet
     * 
     * @author Vivianne Sousa
     * @date 02/09/2007
     * 
     * @throws ErroRepositorioException
     */

    public Imovel pesquisarDadosImovel(Integer idImovel)throws ControladorException;
    
    /**
     * 
     * Permite Pesquisar as categorias do Imóvel [UC0394] Gerar Débitos a Cobrar
     * 
     * de Doações
     * 
     * 
     * 
     * @author César Araújo
     * 
     * @date 10/09/2006
     * 
     * @param Imovel
     * 
     * imovel - objeto imovel
     * 
     * @return Collection<Categoria> - Coleção de categorias
     * 
     * @throws ErroRepositorioException
     * 
     */

    public Collection<Categoria> pesquisarCategoriasImovel(Imovel imovel)throws ControladorException ;
	
    /**
	 * O método abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necessários para a criação da inscrição ,de rota e endereço para exibição.
	 * 
	 * @author Vivianne Sousa
     * @date 06/11/2007
	 */

	public Collection pesquisarDadosImovel(String idsImovel)throws ControladorException;
	
    /**
	 * O método abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necessários para a criação da inscrição e de rota para exibição.
	 * 
	 * @author Vivianne Sousa
     * @date 06/11/2007
	 */
    public Collection pesquisarDadosImovel(Collection colecaoIdsImovel) throws ControladorException;
    
    /**
	 * O método abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necessários para a criação da inscrição e de rota para exibição.
	 * 
	 * @author Vivianne Sousa
     * @date 06/11/2007
	 */
    public Collection pesquisarDadosImovel(
			FiltrarAnaliseExcecoesLeiturasHelper filtrarAnaliseExcecoesLeiturasHelper,
			Integer anoMes) throws ControladorException;
	
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

	public Collection pesquisarImoveisClientesRelacao(Collection idsCliente,Integer numeroInicial)

	throws ControladorException;	
	public CobrancaSituacao pesquisarImovelCobrancaSituacao(Integer idImmovel)
		throws ControladorException;
	
	/**
	 * 
	 * Atualiza a situação de cobrança do imóvel
	 * 
	 */
	public void atualizarSituacaoCobrancaImovel(Integer idSituacaoCobranca, Integer idImovel)
		throws ControladorException;
	
	public String pesquisarInscricaoImovelSemPonto(Integer idImovel)
		throws ControladorException;
	public Collection<ImovelSubcategoria> pesquisarImovelSubcategoria(Imovel imovel) throws ControladorException;
	
	/**
	 * Filtrar o Imovel pelos parametros informados, para saber a quantidade de imoveis.
	 * Utilizado para corrigir o erro da listagem de Imoveis: o metodo pesquisarQuantidadeImovel nao
	 * traz a mesma quantidade de imovel do metodo pesquisarImovelInscricaoNew.
	 * 
	 * @author Ivan Sérgio
	 * @date 11/03/2008
	 */
	public Integer pesquisarQuantidadeImovelInscricao(
			String idImovel, String idLocalidade, String codigoSetorComercial,
			String numeroQuadra, String lote, String subLote,
			String codigoCliente, String idMunicipio, String cep,
			String idBairro, String idLogradouro,
			String numeroImovelInicial, String numeroImovelFinal, 
			boolean pesquisarImovelCondominio) throws ControladorException;
    
    /**
     * [UC0011] Inserir Imovel
     * 
     * @author Vivianne Sousa
     * @date 23/05/2008
     */
    public Collection pesquisarImovelPerfilDiferenteCorporativo()throws ControladorException;
    
    /**
     * [UC0810] Obter Quantidade de Economias Virtuais
     *
     * @author Raphael Rossiter
     * @date 04/06/2008
     *
     * @param idImovel
     * @return Integer
     * @throws ControladorException
     */
    public Integer obterQuantidadeEconomiasVirtuais(Integer idImovel) throws ControladorException;
    
    /**
     * [UC0011] Inserir Imovel
     * 
     * @author Vivianne Sousa
     * @date 23/05/2008
     */
    public Collection pesquisarImovelPerfilTarifaSocialDiferenteCorporativo()throws ControladorException;
   
    
    /**
     * [UC0823] Atualiza Ligação de Água de Ligado em Análise para Ligado
     * 
     * Seleciona a lista de imóveis que esteja com a situação de água ligado em análise.
     * @author Yara Taciane
     * @date 23/05/2008
     */
	public List pesquisarLocalidadeComImoveisComSituacaoLigadoEmAnalise()throws ControladorException;
    
    
    
    /**
     * [UC0823] Atualiza Ligação de Água de Ligado em Análise para Ligado
     * 
     * Seleciona a lista de imóveis que esteja com a situação de água ligado em análise.
     * @author Yara Taciane
     * @date 23/05/2008
     */
	public Collection pesquisarImoveisComSituacaoLigadoEmAnalise(Integer idLocalidade)throws ControladorException;

	
	   /**
     * [UC0823] Atualiza Ligação de Água de Ligado em Análise para Ligado
     * 
     * Seleciona a lista de imóveis que esteja com a situação de água ligado em análise.
     * @author Yara Taciane
     * @date 23/05/2008
     */
	
	public void atualizarSituacaoAguaPorImovel(String id, String situacaoAguaLigado) throws ControladorException;
	
	/**
	 * Consultar Perfil Quadra
	 * 
	 * @param idImovel
	 * @return Perfil da Quadra 
	 * @exception ControladorException
	 */
	public Integer obterQuadraPerfil(Integer idImovel)throws ControladorException;
	

	/**
	 * [UC0101] - Consistir Leituras e Calcular Consumos
	 *
	 * Pesquisar as contas pertencentes ao imovel e anoMes informados que não estejam com a situação
	 * atual igual a "PRÉ-FATURADA" 
	 *
	 * @author Raphael Rossiter
	 * @date 15/08/2008
	 *
	 * @param imovelId
	 * @param anoMesReferencia
	 * @return Object
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarImovelIdComContaNaoPreFaturada(Integer imovelId,
			Integer anoMesReferencia) throws ControladorException ;
	
	
	/**
	 * Inserir Imovel
	 *
	 * Validar Aba de inserir imovel subcategoria
	 *
	 * @author Raphael Rossiter
	 * @date 22/08/2008
	 *
	 * @param colecaoSubcategorias
	 * @param permissaoEspecial
	 * @param usuarioLogado
	 * @throws ControladorException
	 */
	public void validarAbaInserirImovelSubcategoria(Collection colecaoSubcategorias, 
		int permissaoEspecial, Usuario usuarioLogado) throws ControladorException;
	
	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 22/09/2008
	 */
	public void atualizarImovelSituacaoAtualizacaoCadastral(Integer idImovel,
			Integer idSituacaoAtualizacaoCadastral)throws ControladorException ;
	
	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 22/09/2008
	 */
	public void atualizarImovelAtualizacaoCadastralSituacaoAtualizacaoCadastral(Integer idImovel,
			Integer idSituacaoAtualizacaoCadastral, Integer idEmpresa)throws ControladorException;
	
    /**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/09/2008
	 */
	public Integer verificaExistenciaImovelAtualizacaoCadastral(Integer idImovel) 
		throws ControladorException;
	
	/**
	 * Informar Economia
	 * 
	 * @author Vivianne Sousa
	 * @date 23/10/2008
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImovelEconomia(Integer idImovel, Integer idSubcategoria) 
		throws ControladorException;
	
    /**
     * Pesquisar Imóvel Atualização Cadastral(Dados da Inscrição)
     * 
     * @author Ana Maria
     * @date 17/09/2008
     * 
     * @throws ErroRepositorioException
     */
    public ImovelAtualizacaoCadastral pesquisarImovelAtualizacaoCadastralInscricao(Integer idImovel, Integer idEmpresa) 
    	throws ControladorException;
    
	/**
	 * Consultar Imóveis Atualização Cadastral por Quadra
	 * 
	 * @param idSetorComercial
	 * @param quadraInicial
	 * @param quadraFinal	 
	 * @param idEmpresa
	 * @return Collection<Imovel> - Coleção de imóveis.
	 * 
	 * @author Ana Maria
     * @date 18/09/2008
	 * @exception ErroRepositorioException
	 */
	public Collection obterImoveisAtualizacaoCadastral(Integer idLocalidade, String codigoSetor,
			Integer quadraInicial, Integer quadraFinal, Integer idEmpresa, Integer idRota)throws ControladorException;

	/**
	 * Pesquisar existência de imóvel economia
	 * 
	 * @author Ana Maria
	 * @date 05/12/2008
	 * 
	 * @return Boolean
	 * @throws ErroRepositorioException
	 */
	public Boolean pesquisarExistenciaImovelEconomia(Integer idImovel, Integer idSubcategoria) 
		throws ControladorException;
	
	/**
	 * [UC0011] Inserir Imóvel
	 * 
	 * Validações do Imovel referente à aba de Localidade
	 * 
	 * @author Victor Cisneiros
	 * @date 28/01/2009
	 */
	public ImovelAbaLocalidadeRetornoHelper validarImovelAbaLocalidade(
			ImovelAbaLocalidadeHelper helper) throws ControladorException;
	
	/**
	 * [UC0011] Inserir Imóvel
	 * 
	 * Validações do Imovel referente à aba de Endereco
	 * 
	 * @author Victor Cisneiros
	 * @date 28/01/2009
	 */
	public ImovelAbaEnderecoRetornoHelper validarImovelAbaEndereco(
			ImovelAbaEnderecoHelper helper) throws ControladorException;
	
	/**
	 * [UC0011] Inserir Imóvel
	 * 
	 * Validações do Imovel referente à aba de Clientes
	 * 
	 * @author Victor Cisneiros
	 * @date 28/01/2009
	 */
	public void validarImovelAbaCliente(
			Collection<ClienteImovel> imoveisClientes, Usuario usuario) throws ControladorException;
	
	/**
	 * [UC0011] Inserir Imóvel
	 * 
	 * Validações do Imovel referente à aba de Conclusao
	 * 
	 * @author Victor Cisneiros
	 * @date 28/01/2009
	 */
	public ImovelAbaCaracteristicasRetornoHelper validarImovelAbaCaracteristicas(
			ImovelAbaCaracteristicasHelper helper) throws ControladorException;
	
	/**
	 * [UC0011] Inserir Imóvel
	 * 
	 * Validações do Imovel referente à aba de Conclusao
	 * 
	 * @author Victor Cisneiros
	 * @date 28/01/2009
	 */
	public ImovelAbaConclusaoRetornoHelper validarImovelAbaConclusao(
			ImovelAbaConclusaoHelper helper) throws ControladorException;
	
	  /**
	 * Consultar os dodos cliente usuário do Imovel 
	 * 
	 * @author Arthur Carvalho
	 * @date 12/03/2009
	 */
    public Object[] consultarDadosClienteUsuarioImovel(
			String idImovel) throws ControladorException ;
	
    /**
	 * Gerar Relatório de Imóveis Outros Critérios 
	 * 
	 * @author Rômulo Aurelio
	 * @date 25/07/2006
	 * 
	 * @param idImovelCondominio
	 * @param idImovelPrincipal
	 * @param idNomeConta
	 * @param idSituacaoLigacaoAgua
	 * @param consumoMinimoInicialAgua
	 * @param consumoMinimoFinalAgua
	 * @param idSituacaoLigacaoEsgoto
	 * @param consumoMinimoInicialEsgoto
	 * @param consumoMinimoFinalEsgoto
	 * @param intervaloValorPercentualEsgotoInicial
	 * @param intervaloValorPercentualEsgotoFinal
	 * @param intervaloMediaMinimaImovelInicial
	 * @param intervaloMediaMinimaImovelFinal
	 * @param intervaloMediaMinimaHidrometroInicial
	 * @param intervaloMediaMinimaHidrometroFinal
	 * @param idImovelPerfil
	 * @param idPocoTipo
	 * @param idFaturamentoSituacaoTipo
	 * @param idCobrancaSituacaoTipo
	 * @param idSituacaoEspecialCobranca
	 * @param idEloAnormalidade
	 * @param areaConstruidaInicial
	 * @param areaConstruidaFinal
	 * @param idCadastroOcorrencia
	 * @param idConsumoTarifa
	 * @param idGerenciaRegional
	 * @param idLocalidadeInicial
	 * @param idLocalidadeFinal
	 * @param setorComercialInicial
	 * @param setorComercialFinal
	 * @param quadraInicial
	 * @param quadraFinal
	 * @param loteOrigem
	 * @param loteDestno
	 * @param cep
	 * @param logradouro
	 * @param bairro
	 * @param municipio
	 * @param idTipoMedicao
	 * @param indicadorMedicao
	 * @param idSubCategoria
	 * @param idCategoria
	 * @param quantidadeEconomiasInicial
	 * @param quantidadeEconomiasFinal
	 * @param diaVencimento
	 * @param idCliente
	 * @param idClienteTipo
	 * @param idClienteRelacaoTipo
	 * @param numeroPontosInicial
	 * @param numeroPontosFinal
	 * @param numeroMoradoresInicial
	 * @param numeroMoradoresFinal
	 * @param idAreaConstruidaFaixa
	 * @return qtde
	 * @throws ControladorException
	 */
	public Integer gerarRelatorioCadastroConsumidoresInscricaoCount(
			String idImovelCondominio, String idImovelPrincipal,
			String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
			String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,
			String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto,
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
			String idUnidadeNegocio, String cdRotaInicial,
			String cdRotaFinal, String sequencialRotaInicial,
			String sequencialRotaFinal, String[] pocoTipoListIds) throws ControladorException;
	
	/**
	 * Gerar Relatório de Imóveis Outros Critérios 
	 * 
	 * @author Rômulo Aurelio
	 * @date 18/03/2009
	 * 
	 * @return qtde
	 * @throws ControladorException
	 */
	public Integer gerarRelatorioImovelEnderecoOutrosCriteriosCount(
							String idImovelCondominio, 
							String idImovelPrincipal,
							String idSituacaoLigacaoAgua, 
							String consumoMinimoInicialAgua,
							String consumoMinimoFinalAgua, 
							String idSituacaoLigacaoEsgoto,
							String consumoMinimoInicialEsgoto, 
							String consumoMinimoFinalEsgoto,
							String intervaloValorPercentualEsgotoInicial,
							String intervaloValorPercentualEsgotoFinal,
							String intervaloMediaMinimaImovelInicial,
							String intervaloMediaMinimaImovelFinal,
							String intervaloMediaMinimaHidrometroInicial,
							String intervaloMediaMinimaHidrometroFinal, 
							String idImovelPerfil,
							String idPocoTipo, 
							String idFaturamentoSituacaoTipo,
							String idCobrancaSituacaoTipo, 
							String idSituacaoEspecialCobranca,
							String idEloAnormalidade, 
							String areaConstruidaInicial,
							String areaConstruidaFinal, 
							String idCadastroOcorrencia,
							String idConsumoTarifa, 
							String idGerenciaRegional,
							String idLocalidadeInicial, 
							String idLocalidadeFinal,
							String setorComercialInicial, 
							String setorComercialFinal,
							String quadraInicial, 
							String quadraFinal, 
							String loteOrigem,
							String loteDestno, 
							String cep, 
							String logradouro, 
							String bairro,
							String municipio, 
							String idTipoMedicao, 
							String indicadorMedicao,
							String idSubCategoria, 
							String idCategoria,
							String quantidadeEconomiasInicial, 
							String quantidadeEconomiasFinal,
							String diaVencimento, 
							String idCliente, 
							String idClienteTipo,
							String idClienteRelacaoTipo, 
							String numeroPontosInicial,
							String numeroPontosFinal, 
							String numeroMoradoresInicial,
							String numeroMoradoresFinal, 
							String idAreaConstruidaFaixa,
							String idUnidadeNegocio,
							String rotaInicial, 
							String rotaFinal,
							String ordenacaoRelatorio,
							String seqRotaInicial, 
							String seqRotaFinal) throws ControladorException;
	
	/**
	 * 
	 * Atualiza a situação de cobrança e a situação do tipo de cobrança
	 * 
	 * @author Sávio Luiz
	 * @date 18/02/2009
	 */

	public void atualizarSituacaoCobrancaETipoIdsImoveis(Integer idSituacaoCobranca,Integer idSituacaoCobrancaTipo, Collection<Integer> idsImovel)
		throws ControladorException; 
	
	/**
	 * O método abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necessários para a criação da inscrição do imóvel para exibição,
	 * independente do imóvel ter sido excluído ou não.
	 * 
	 * aqui é montada a inscrição
	 */
	public String pesquisarInscricaoImovelExcluidoOuNao(Integer idImovel)
			throws ControladorException;
	
	/**
	 * [UCXXXX] Consultar Imóvel
	 * 
	 * @author Rafael Corrêa
	 * @date 22/05/2009
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Cliente pesquisarClienteUsuarioImovelExcluidoOuNao(Integer idImovel)
			throws ControladorException;
	
	/**
	 * Verificar se o imóvel perfil está bloqueado
	 * 
	 * @author Ana Maria
	 * @date 22/04/2009
	 * 
	 * @return Boolean
	 * @throws ErroRepositorioException
	 */
	public Boolean verificarImovelPerfilBloqueado(Integer idImovel) 
	throws ControladorException;
	
	/**
	 * 
	 * Autor: Sávio Luiz
	 * Data: 07/05/2009
	 * 
	 * Pesquisa o Fator de Economia
	 * 
	 * 
	 */
	public Short pesquisarFatorEconomiasCategoria(Integer idCategoria)throws ControladorException;
	
	/**
	 * Verificar se a ultima alteracao do imóvel 
	 * 
	 * @author Rômulo Aurélio
	 * @date 27/05/2009
	 * 
	 * @return Date
	 */
	public Date pesquisarUltimaAlteracaoImovel(Integer idImovel) 
			throws ControladorException;
	
	/**
	 * Pesquisa a coleção de categorias do imovel [UC0472] Consultar Imovel
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarCategoriaSubcategoriaImovel(Integer idImovel)
			throws ControladorException; 
	
	/**
	 * 
	 * Pesquisa qual foi o consumo faturado do imovel
	 * 
	 * @author Hugo Amorim
	 * @date 18/12/2009
	 * @return consumoFaturadoMes
	 * @throws ControladorException
	 */
	public Integer obterConsumoFaturadoImovelNoMes(Integer idImovel, Integer mesAnoReferencia) throws ControladorException;
	
	 /** 
	 * Pesquisa o perfil do imovel do imovel informado
	 * 
	 * @author Rômulo Aurélio
	 * @date 03/03/2010
	 * @throws ControladorException
	 */
	public ImovelPerfil recuperaPerfilImovel(Integer idImovel)  throws ControladorException;
	
	/**
	 * [UC1005] Determinar Confirmação da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 11/03/2010
	 */
	
	public void atualizarDataRetiradaImovelSituacaoCobranca(
			Integer idImovelSituacaoCobranca, Date dataRetirada) throws ControladorException;
	
	/**
	 * [UC0672] - Registrar Movimento de Retorno dos Negativadores
	 *
	 * @author Vivianne Sousa
	 * @date 12/03/2010
	 */
	public void atualizarSituacaoCobrancaImovel(Integer idSituacaoCobrancaNova, 
			Integer idSituacaoCobrancaBanco, Integer idImovel) throws ControladorException;
	
	/** 
	 * Verifica se ja Existe dado na tabela cliente Imovel.
	 * 
	 * @author Arthur Carvalho
	 * @date 19/03/2010
	 * @throws ControladorException
	 */
	public void verificaRestricaoDaTabelaClienteImovel(ClienteImovel clienteImovel)  throws ControladorException;

	/**
	 * [UC0101] - Consistir Leituras e Calcular Consumos
	 *
	 * Pesquisar as contas pertencentes ao imovel e anoMes informados que não estejam com a situação
	 * atual igual a "PRÉ-FATURADA" 
	 *
	 * @author Raphael Rossiter
	 * @date 15/08/2008
	 *
	 * @param imovelId
	 * @param anoMesReferencia
	 * @return Object
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarImovelCondominioIdComContaNaoPreFaturada(Integer imovelId,
			Integer anoMesReferencia) throws ControladorException;

	/** 
	 * [UC1000] Informar Medidor de Energia por Rota.
	 * 
	 * Atualizar Número Medidor de Energia do Imóvel.
	 * 
	 * @author Hugo Leonardo
	 * @date 15/03/2010
	 *
	 */
	public void atualizarNumeroMedidorEnergiaImovel(Integer matricula, String medidorEnergia)
		throws ControladorException;
	
	/**  
	 * [UC0591] - Gerar Relatório de Clientes Especiais
	 * 
	 * 					Count
	 * 
	 * @author Hugo Amorim
	 * @date 11/05/2010
	 */
	public Integer pesquisarImovelClientesEspeciaisRelatorioCount(
			String idUnidadeNegocio, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String codigoSetorInicial, String codigoSetorFinal,
			String codigoRotaInicial, String codigoRotaFinal,
			String[] idsPerfilImovel, String[] idsCategoria,
			String[] idsSubcategoria, String idSituacaoAgua,
			String idSituacaoEsgoto, String qtdeEconomiasInicial,
			String qtdeEconomiasFinal, String intervaloConsumoAguaInicial,
			String intervaloConsumoAguaFinal,
			String intervaloConsumoEsgotoInicial,
			String intervaloConsumoEsgotoFinal, String idClienteResponsavel,
			String intervaloConsumoResponsavelInicial,
			String intervaloConsumoResponsavelFinal,
			Date dataInstalacaoHidrometroInicial,
			Date dataInstalacaoHidrometroFinal,
			String[] idsCapacidadesHidrometro, String[] idsTarifasConsumo,
			Integer anoMesFaturamento, String idLeituraAnormalidade,
			String leituraAnormalidade, String idConsumoAnormalidade,
			String consumoAnormalidade)throws ControladorException;

	/**
	 * [UC0490] Informar Situação de Cobrança
	 * [SB0004] – Selecionar Situações de Cobrança
	 * 
	 * seleciona as situações de cobrança 
	 * (a partir da tabela COBRANÇA_SITUACAO com CBST_ICUSO=1 
	 * e CBST_ICBLOQUEIOINCLUSAO=2)retirando as ocorrências 
	 * com CBST_ID=CBST_ID da tabela IMOVEL_COBRANCA_SITUACAO 
	 * para IMOV_ID=Id do imóvel recebido e 
	 * ISCB_DTRETIRADACOBRANCA com valor igual a nulo
	 * 
	 * @author Vivianne Sousa
	 * @date 12/05/2010
	 */
	public Collection pesquisarCobrancaSituacao(
			Integer idImovel, boolean temPermissaoEspecial) throws ControladorException ;
	
	/**
	 * Retorna a quantidade de categorias de um imóvel
	 * 
	 * @param imovel
	 *            Imóvel que será consultado
	 * @return uma coleção de categorias
	 */
	public Collection obterQuantidadeEconomiasContaCategoria(Integer idConta)
			throws ControladorException;
	
	/**
	 * 
	 * O método abaixo realiza uma pesquisa em imovel e retorna os campos
	 * 
	 * id localidade e codigo do setor
	 * 
	 *  Object[0] = idLocalidade
	 *  Object[1] = codigoSetor
	 * 
	 * @author Hugo Amorim
	 * @date 01/06/2010
	 */
	public Object[] pesquisarLocalidadeCodigoSetorImovel(Integer idImovel)
		throws ControladorException;
	
	/**
	 * Inserir e Atualizar Imovel 
	 *
	 * @author Raphael Rossiter
	 * @date 02/06/2010
	 *
	 * @param idImovel
	 * @throws ControladorException
	 */
	public void verificarIndicadorNomeConta(Integer idImovel) throws ControladorException ;
	
	/**
	 * [UC0014] Manter Imóvel
	 * 
	 * [FS0037] Verificar Imóvel em Processo de Faturamento
	 * 
	 * @author Hugo Amorim
	 * @param idImovel
	 * @param anoMesFaturamento
	 * 
	 * @throws ControladorException
	 */
	public boolean verificarImovelEmProcessoDeFaturamento(
			Integer idImovel)throws ControladorException;
	
	
	/**
 	 * @author Daniel Alves
	 * @date 20/07/2010
	 * @param idImovelPerfil
	 * @return ImovelPerfil
	 * @throws ErroRepositorioException
	 */
	public ImovelPerfil  pesquisarImovelPerfil(
			Integer idImovelPerfil) throws ControladorException;

	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 16/09/2010
	 * @param idQuadraAnterior
	 * @param idQuadraAtual
	 * @return
	 * @throws ControladorException
	 */
	public FaturamentoGrupo[] verificaInscricaoAlteradaAcarretaMudancaDoGrupoFaturamento(Integer idQuadraAnterior, Integer idQuadraAtual) throws ControladorException;
	
	/**
	 * @author Daniel Alves
	 * @date 28/07/2010
	 * @param idImovel
	 * @return Colecao de imovelSubcategoriaHelper
	 * @throws ControladorException
	 */
	public Collection<ImovelSubcategoriaHelper> consultaSubcategorias(int idImovel)throws ControladorException;
	
	/**
	 *  @author Hugo Leonardo
	 *  @date 21/09/2010
	 *  
	 * 	UC_0009 - Manter Cliente
	 *  	[FS0008] ? Verificar permissão especial para cliente de imóvel público
	 *  
	 * 	Verifica se o Cliente possui algum imóvel, cujo o tipo da categoria 
	 *  em subcategoria seja igual a PUBLICO.
	 *  
	 * 	Caso o cliente possua algum imóvel, retornar a quantidade de imoveis nesta situação
	 * 	Caso contrário retorna zero. 
	 * 
	 *  @param idCliente
	 *  @return Boolean
	 *  @throws ControladorException
	 */
	public Boolean pesquisarExistenciaImovelPublico(Integer idCliente) 
		throws ControladorException;
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 16/09/2010
	 * @param idQuadraAnterior
	 * @param idQuadraAtual
	 * @return
	 * @throws ControladorException
	 */
	public boolean verificaGeracaoDadosLeituraGrupoFaturamento(FaturamentoGrupo faturamentoGrupo) throws ControladorException;
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 19/09/2010
	 * @param idLocalidade
	 * @param idSetorComercial
	 * @param idQuadra
	 * @param idQuadraFace
	 * @param subLote
	 * @param lote
	 * @return
	 * @throws ControladorException
	 */
	public ImovelInscricaoAlterada verificarDuplicidadeImovelInscricaoAlterada(Integer idLocalidade, Integer idSetorComercial, Integer idQuadra,
			Integer idQuadraFace, Integer subLote, Integer lote) throws ControladorException;
	
	/**
	 * [UC0995] Emitir Declaração Transferência de Débitos/Créditos
	 * @author Daniel Alves
	 * @date 23/09/2010
	 * @return Municipio
	 */
	public Municipio pesquisarMunicipioImovel(Integer idImovel) throws ControladorException;
	
	
	/**
	 * 
	 * [UC0630] Solicitar Emissão do Extrato de Débitos
	 * 
	 * [SB0001] – Calcular valor dos descontos pagamento à vista.
	 * 
	 * @author Vivianne Sousa
	 * @date 21/10/2010
	 * 
	 * @throws ControladorException
	 */
	public Short consultarNumeroReparcelamentoConsecutivosImovel(Integer idImovel)
	throws ControladorException ;
	
	/**
	 * TODO : COSANPA
	 * 
	 * Metodo para verificar se a rota que o imovel pertence
	 * ja foi gerada para o mes de faturamento do grupo 
	 * 
	 * @author Pamela Gatinho
	 * @date 01/08/2011
	 * @return boolean
	 */
	public boolean verificaGeracaoDadosLeituraRota(FaturamentoGrupo faturamentoGrupo, Rota rota) throws ControladorException;
	
	/**TODO:COSANPA
	 * @author Adriana Muniz
	 * @date 06/12/2011
	 * 
	 * Pesquisa o perfil do imovel pelo id do imóvel
	 * 
	 * @param idImovelPerfil
	 * @return
	 * @throws ErroRepositorioException
	 * @throws ControladorException 
	 */
	public ImovelPerfil  pesquisarImovelPerfilIdImovel(
			Integer idImovel) throws ControladorException ;
	
	/**
	 * TODO : COSANPA
	 * Pamela e Adriana - 21/08/2012
	 * 
	 * Método que verifica se um determinado imóvel
	 * possui faturamento ativo de água
	 * 
	 * @param imovel
	 * @return
	 */
	public boolean isFaturamentoAguaAtivo(Imovel imovel);
	
	/**
	 * TODO : COSANPA
	 * Pamela e Adriana - 21/08/2012
	 * 
	 * Método que verifica se um determinado imóvel
	 * possui faturamento ativo de esgoto
	 * 
	 * @param imovel
	 * @return
	 */
	public boolean isFaturamentoEsgotoAtivo(Imovel imovel);
	
	/**
	 * TODO : COSANPA
	 * Pamela Gatinho e Adriana Muniz
	 * 21/08/2012
	 * 
	 * Método que retorna a colecao de categoria ou subcategoria do
	 * imóvel, dependendo do parâmetro cadastrado na tabela sistema
	 * parâmetro.
	 * 
	 * @param imovel
	 * @return
	 * @throws ControladorException
	 */
	public Collection obterColecaoCategoriaOuSubcategoriaDoImovel(Imovel imovel) throws ControladorException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection consultarImovel(Integer idLocalidade,Integer idGerenciaRegional,
			Integer idUnidadeNegocio)throws ControladorException ;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection consultarImovelCadastro(Integer idLocalidade,Integer idGerenciaRegional,
			Integer idUnidadeNegocio, Integer anoMesPesquisaInicial,Integer anoMesPesquisaFinal)throws ControladorException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarImovelEconomia(Integer idImovel)throws ControladorException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public TarifaSocialDadoEconomia pesquisarTarifaSocialDadoEconomia(Integer idImovel)throws ControladorException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 25/03/2011
	 * 
	 * @throws ControladorException
	 */
	public TarifaSocialCarta pesquisarTarifaSocialCarta(Integer idImovel,Integer codigoTipoCarta)throws ControladorException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 29/03/2011
	 * 
	 * @throws ControladorException
	 */
	public void atualizarTarifaSocialComandoCarta(Integer idTarifaSocialComandoCarta, Integer qtdeImoveis)throws ControladorException ;

	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 29/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisarQuantidadeImoveisTarifaSocialCarta(Integer idTarifaSocialComandoCarta) throws ControladorException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 29/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarTarifaSocialCarta(Integer idTarifaSocialComandoCarta)  throws ControladorException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 29/03/2011
	 * 
	 * @throws ControladorException
	 */
	public BigDecimal pesquisarValorContaTarifaSocialCartaDebito(Integer idTarifaSocialComandoCarta,Integer idImovel) throws ControladorException;
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Imóvel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 31/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarTarifaSocialComandoCarta(Integer codigoTipoCarta, String situacao,Integer numeroPagina) throws ControladorException;
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Imóvel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 31/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisarQtdeTarifaSocialComandoCarta(Integer codigoTipoCarta, String situacao)throws ControladorException;
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Imóvel da Tarifa Social
	 * [SB0003] Excluir Comando Selecionado
	 *  
	 * @author Vivianne Sousa
	 * @date 31/03/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void removerComando(Integer idTarifaSocialComandoCarta)throws ControladorException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 01/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarImoveisTarifaSocialCarta(Integer idTarifaSocialComandoCarta,Integer codigoTipoCarta) throws ControladorException;
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Imóvel da Tarifa Social
	 * [SB0003] Excluir Comando Selecionado 
	 * 
	 * @author Vivianne Sousa
	 * @date 01/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarContasTarifaSocialCartaDebito(Integer idTarifaSocialComandoCarta,Integer idImovel) throws ControladorException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * [SB0004]–Retirar Imóvel tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 04/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void retirarImovelTarifaSocial(Integer motivoExclusao, Imovel imovel,String observacaoRetira)throws ControladorException ;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 04/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void atualizarDataExecucaoTarifaSocialComandoCarta(Integer idTarifaSocialComandoCarta)throws ControladorException;
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Imóvel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 01/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarImoveisTarifaSocial(Integer idLocalidade) throws ControladorException;
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Imóvel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 05/04/2011
	 * 
	 * @throws ControladorException
	 */
	public TarifaSocialComandoCarta pesquisarTarifaSocialComandoCarta(Integer idTarifaSocialComandoCarta) throws ControladorException;
	
	/**
	 * [UC1164] Gerar Resumo dos Imóveis Excluídos da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 07/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarQtdeImoveisExcluidostarifaSocial(
			Integer codigoTipoCarta,Integer idGerencia,Integer idUnidade,Integer idLocalidade, 
			Integer referenciaInicial, Integer refereciaFinal)throws ControladorException;
	
	/**
	 * [UC1164] Gerar Resumo dos Imóveis Excluídos da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 08/04/2011
	 * 
	 * @throws ControladorException
	 */
	public String pesquisarDescricaoMotivoCarta(Integer idCodigoMotivo)  throws ControladorException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 08/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarQtdeTarifaSocialDadoEconomia(Integer idtarifaSocialExclusaoMotivo,
			Integer referenciaInicial, Integer refereciaFinal,Integer idGerencia,Integer idUnidade,
			Integer idLocalidade)  throws ControladorException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * [SB0008]-Verificar carta para o comando
	 *  
	 * @author Vivianne Sousa
	 * @date 19/04/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void removerCartasComando(Integer idTarifaSocialComandoCarta, 
			Integer idLocalidade, Integer tipoCarta) throws ControladorException; 
	
	/**
	 * [UC0352] Emitir Contas e Cartas
	 * [SB0017] – Obter Mensagem da Conta em 3 Partes
	 * 
	 * @author Vivianne Sousa
	 * @date 29/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisarAnoMesExclusaoTarifaSocialImovel(Integer idImovel) 
	throws ControladorException;
	
	/**
	 *  [UC1168] Encerrar Comandos de Cobrança por Empresa
	 *
	 * @author Mariana Victor
	 * @created 10/05/2011
	 */
	public void retirarSituacaoCobrancaImovel(Integer idImovel, Integer idCobrancaSituacao) 
		throws ControladorException;

	/**
	 * [UC1169] Movimentar Ordens de Serviço de Cobrança por Resultado
	 * 
	 * Emitir OS de Empresa de Cobrança - 
	 * 
	 * @author Mariana Victor
	 * @data 18/05/2011
	 */
	public Collection<Integer[]> pesquisarIdsImoveis(String[] idsOrdemServico)
		throws ControladorException;
	
	/**
	 * [UC1174] Gerar Relatório Imóveis com Doações
	 * 
	 * Quantidade de imoveis com doações - 
	 * 
	 * @author Erivan Sousa	
	 * @data 13/06/2011
	 */
	public Integer pesquisarQuantidadeImoveisDoacoes(GerarRelatorioImoveisDoacoesHelper filtro)throws ControladorException;
	
	/**
	 * [UC1174] Gerar Relatório Imóveis com Doações
	 * 
	 * Pesquisar imoveis com doações - 
	 * 
	 * @author Erivan Sousa	
	 * @data 13/06/2011
	 */
	public Collection pesquisarImoveisDoacoes(GerarRelatorioImoveisDoacoesHelper filtro)throws ControladorException;
	
	/**
	 * Método que retorna o imóvel área comum
	 * <p>
	 * [UC0098] Manter Vínculos de Imóveis para Rateio de Consumo
	 * </p>
	 * <p>
	 * [SB0001] Atualizar Tipo de Rateio
	 * </p>
	 * 
	 * @author Magno Gouveia
	 * @since 17/08/2011
	 * 
	 * @param idImovelCondomio
	 * @return imovel.id
	 */
	public Integer pesquisarImovelAreaComum(Integer idImovelCondominio) throws ControladorException;

	/**
	 * <p>
	 * [UC0098] Manter Vínculos de Imóveis para Rateio Comum
	 * </p>
	 * <p>
	 * [SB0001] - [FS0012] - Caso a matrícula do imóvel para área comum
	 * informada não exista na tabela IMOVEL, exibir a mensagem "Matrícula
	 * inexistente no cadastro" e retornar para o passo correspondente no fluxo
	 * principal
	 * </p>
	 * 
	 * @author Magno Gouveia
	 * @since 19/08/2011
	 * @param idImovel
	 * @return
	 */
	public Short verificarExistenciaDoImovel(Integer idImovel) throws ControladorException;
	
	/**
	 * Método que retorna o imóvel condomínio
	 * <p>
	 * [UC0098] Manter Vínculos de Imóveis para Rateio de Consumo
	 * </p>
	 * <p>
	 * [SB0001] Atualizar Tipo de Rateio
	 * </p>
	 * 
	 * @author Magno Gouveia
	 * @since 17/08/2011
	 * 
	 * @param idImovel
	 * @return imovel.id
	 */
	public Integer pesquisarImovelCondominio(Integer idImovel) throws ControladorException;
	
	/**
	 * Método que retorna o id do imóvel condominio
	 * 
	 * [UC0098] Manter Vínculos de Imóveis para Rateio de Consumo
	 * [SB0001] Atualizar Tipo de Rateio
	 * 
	 * @author Magno Gouveia
	 * @since 19/08/2011
	 * 
	 * @param idImovel, indicadorImovelAreaComum
	 */
	public void atualizarIndicadorImovelAreaComumDoImovel(Integer idImovel, Short indicadorImovelAreaComum)	throws ControladorException;
	
	/**
	 * [UC0457] - Encerrar Ordem de Serviço.
	 * [SB0009] - Verificar Situação Especial de Faturamento.
	 * 
	 * Verifica se um imóvel está em situação especial de faturamento
	 * para um dado imovel (idImovel). 
	 * A situação especial de faturamento tem o ftst_id = 2
	 * 
	 * @param idImovel
	 * @return Imovel
	 * @throws ControladorException 
	 */
	public Imovel pesquisarImovelSituacaoEspecialFaturamento(Integer idImovel) throws ControladorException;
	
	/**
	 * Obtém a principal categoria
	 * 
	 * @author Rodrigo Cabral
	 * @date 13/09/2011
	 * 
	 * @param colecaoImovelSubCategorias
	 * @return
	 * @throws ControladorException
	 */
	public Categoria obterPrincipalCategoria(Collection colecaoImovelSubCategorias)
		throws ControladorException;
	
	/**
	 * Seleciona a Subcategoria principal de uma categoria
	 * 
	 * @author Rodrigo Cabral
	 * @date 13/09/2011 
	 * 
	 * @param idCategoria
	 * @return
	 * @throws ControladorException
	 */
	public ImovelSubcategoria obterPrincipalSubcategoria( Integer idCategoria, Collection colecaoImovelSubCategorias )
		throws ControladorException;
	
	public void atualizarImovel(Imovel imovel) throws ControladorException;

	/**TODO: COSANPA
	 * Mantis 494
	 * 
	 * Geração da Rota para recadastramento.
	 * 
	 * @author Wellington
	 * @return Coleção de RamoAtividade
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Collection<ImovelRamoAtividade> pesquisarRamoAtividadeDoImovel(Integer idImovel)
		throws ControladorException;
	
	public void atualizarIdArquivoTextoImovelAtualizacaoCadastral(
			Integer idArquivoTexto, Integer idImovel) throws ControladorException;
	
    public Collection<ImovelSubcategoria> pesquisarImovelSubcategorias(Imovel imovel) throws ControladorException;
}
