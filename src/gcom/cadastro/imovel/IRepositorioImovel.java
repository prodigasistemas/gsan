package gcom.cadastro.imovel;

import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.bean.ImovelSubcategoriaHelper;
import gcom.cadastro.tarifasocial.TarifaSocialCarta;
import gcom.cadastro.tarifasocial.TarifaSocialComandoCarta;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.bean.EmitirDocumentoCobrancaBoletimCadastroHelper;
import gcom.cobranca.bean.SituacaoEspecialCobrancaHelper;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.bean.SituacaoEspecialFaturamentoHelper;
import gcom.gui.faturamento.bean.FiltrarImovelInserirManterContaHelper;
import gcom.micromedicao.Rota;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.relatorio.cadastro.GerarRelatorioImoveisDoacoesHelper;
import gcom.relatorio.cadastro.RelatorioResumoQtdeImoveisExcluidosTarifaSocialHelper;
import gcom.relatorio.micromedicao.FiltrarAnaliseExcecoesLeiturasHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IRepositorioImovel {

	/**
	 * Atualizar um os campos lastId,dataUltimaAtualiza��o do imovel na base
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public void atualizarImovelExecucaoOrdemServicoLigacaoAgua(Imovel imovel,
			LigacaoAguaSituacao situacaoAgua) throws ErroRepositorioException;

	/**
	 * Atualizar um os campos lastId,dataUltimaAtualiza��o do imovel na base
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public void atualizarImovelExecucaoOrdemServicoLigacaoEsgoto(Imovel imovel,
			LigacaoEsgotoSituacao situacaoEsgoto)
			throws ErroRepositorioException;

	/**
	 * Inseri um imovel na base
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public void inserirImovel(Imovel imovel) throws ErroRepositorioException;

	/**
	 * Atualiza um imovel na base
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public void atualizarImovel(Imovel imovel) throws ErroRepositorioException;

	/**
	 * Remove um cliente imovel economia
	 * 
	 * @param id
	 *            Description of the Parameter
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public void removerClienteImovelEconomia(Integer id)
			throws ErroRepositorioException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param objeto
	 *            Descri��o do par�metro
	 * @param condicional
	 *            Descri��o do par�metro
	 * @param id
	 *            Descri��o do par�metro
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public void removerTodos(String objeto, String condicional, Integer id)
			throws ErroRepositorioException;

	/**
	 * Pesquisa uma cole��o de im�veis com uma query especifica
	 * 
	 * @param idLocalidade
	 *            parametros para a consulta
	 * @param idSetorComercial
	 *            parametros para a consulta
	 * @param idQuadra
	 *            parametros para a consulta
	 * @param lote
	 *            Descri��o do par�metro
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public Collection pesquisarImovel(Integer idLocalidade,
			Integer idSetorComercial, Integer idQuadra, Short lote,
			int indicadorExclusao) throws ErroRepositorioException;

	/**
	 * Atualiza apenas os dados (Localidade, Setor, Quadra e lote) do im�vel
	 * 
	 * @param imovel
	 *            parametros para a consulta
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public void atualizarImovelInscricao(Imovel imovel)
			throws ErroRepositorioException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param imovelSubcategoria
	 *            Descri��o do par�metro
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public void atualizarImovelSubCategoria(
			ImovelSubcategoria imovelSubcategoria)
			throws ErroRepositorioException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public Object pesquisarObterQuantidadeEconomias(Imovel imovel)
			throws ErroRepositorioException;
	
	public Short pesquisarObterQuantidadeEconomias(Imovel imovel, Categoria categoria) 
		throws ErroRepositorioException;


	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public Collection pesquisarObterQuantidadeEconomiasCategoria(
			Integer idImovel) throws ErroRepositorioException;

	public Collection obterQuantidadeEconomiasCategoria(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public Collection obterDescricoesCategoriaImovel(Imovel imovel)
			throws ErroRepositorioException;

	/**
	 * [UC0164] Filtrar Imovel Por Outros Criterios
	 * 
	 * @author Rhawi Dantas
	 * @created 29/12/2005
	 * 
	 */
	public Collection<Imovel> pesquisarImovelParametrosClienteImovel(
			FiltroClienteImovel filtroClienteImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0164] Filtrar Imovel Por Outros Criterios
	 * 
	 * @author Rhawi Dantas
	 * @created 29/12/2005
	 * 
	 */
	/*--<merge>--
	 public Collection pesquisarImovelOutrosCriterios(
	 FiltrarImovelOutrosCriteriosHelper filtrarImovelOutrosCriteriosHelper)
	 throws ErroRepositorioException;*/

	/**
	 * [UC0156] Informar Situacao Especial Faturamento
	 * 
	 * @author Rhawi Dantas
	 * @created 09/01/2006
	 * 
	 */
	public Collection pesquisarImovelSituacaoEspecialFaturamento(String valor,
			SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper)
			throws ErroRepositorioException;

	/**
	 * [UC0177] Informar Situacao Especial de Cobran�a
	 * 
	 * @author S�vio Luiz
	 * @created 07/03/2006
	 * 
	 */
	public Collection pesquisarImovelSituacaoEspecialCobranca(String valor,
			SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper)
			throws ErroRepositorioException;

	public Integer verificarExistenciaImovel(Integer idImovel)
			throws ErroRepositorioException;

	public Integer recuperarMatriculaImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0156] Informar Situacao Especial Faturamento
	 * 
	 * @author Rhawi Dantas
	 * @created 09/01/2006
	 * 
	 */
	public Integer validarMesAnoReferencia(
			SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper)
			throws ErroRepositorioException;

	/**
	 * Atualiza os ids do faturamento situa��o tipo da tabela im�vel com o id do
	 * faturamento escolhido pelo usuario
	 * 
	 * [UC0156] Informar Situacao Especial de Faturamento
	 * 
	 * @author S�vio Luiz
	 * @date 18/03/2006
	 * 
	 * @param situacaoEspecialCobrancaHelper
	 * @return
	 */
	public void atualizarFaturamentoSituacaoTipo(Collection colecaoIdsImoveis,
			Integer idFaturamentoTipo) throws ErroRepositorioException;

	/**
	 * Seta para null o id da cobran�a situa��o tipo da tabela im�vel
	 * 
	 * [UC0156] Informar Situacao Especial Faturamento
	 * 
	 * @author S�vio Luiz
	 * @date 18/03/2006
	 * 
	 * @param situacaoEspecialCobrancaHelper
	 * @return
	 */
	public void retirarSituacaoEspecialFaturamento(Collection colecaoIdsImoveis)
			throws ErroRepositorioException;

	public Collection<Integer> pesquisarImoveisIds(Integer rotaId)
			throws ErroRepositorioException;

	public Object pesquisarImovelIdComConta(Integer imovelId,
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * Pesquisa o maior ano mes de referencia da tabela de faturamento grupo
	 * 
	 * [UC0177] Informar Situacao Especial de Cobran�a
	 * 
	 * @author S�vio Luiz
	 * @date 18/03/2006
	 * 
	 * @param situacaoEspecialCobrancaHelper
	 * @return
	 */
	public Integer validarMesAnoReferenciaCobranca(
			SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper)
			throws ErroRepositorioException;

	/**
	 * Atualiza o id da cobran�a situa��o tipo da tabela im�vel com o id da
	 * situa��o escolhido pelo usuario
	 * 
	 * [UC0177] Informar Situacao Especial de Cobran�a
	 * 
	 * @author S�vio Luiz
	 * @date 18/03/2006
	 * 
	 * @param situacaoEspecialCobrancaHelper
	 * @return
	 */

	public void atualizarCobrancaSituacaoTipo(Collection colecaoIdsImoveis,
			Integer idCobrancaTipo) throws ErroRepositorioException;

	/**
	 * Seta para null o id da cobran�a situa��o tipo da tabela im�vel
	 * 
	 * [UC0177] Informar Situacao Especial de Cobran�a
	 * 
	 * @author S�vio Luiz
	 * @date 18/03/2006
	 * 
	 * @param situacaoEspecialCobrancaHelper
	 * @return
	 */
	public void retirarSituacaoEspecialCobranca(Collection colecaoIdsImoveis)
			throws ErroRepositorioException;

	/**
	 * Obt�m o indicador de exist�ncia de hidr�metro para o im�vel, caso exista
	 * retorna 1(um) indicando SIM caso contr�rio retorna 2(dois) indicando N�O
	 * 
	 * [UC0307] Obter Indicador de Exist�ncia de Hidr�metro no Im�vel
	 * 
	 * @author Thiago Toscano
	 * @date 02/06/2006
	 * 
	 * @param idImovel
	 * @return
	 * @throws ControladorException
	 */

	public Integer obterIndicadorExistenciaHidrometroImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * <Breve descri��o sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * <Breve descri��o sobre o subfluxo>
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * <Breve descri��o sobre o fluxo secund�rio>
	 * 
	 * <Identificador e nome do fluxo secund�rio>
	 * 
	 * @author Administrador
	 * @date 07/07/2006
	 * 
	 * @param imovel
	 * @param idLigacaoAguaSituacao
	 * @throws ErroRepositorioException
	 */
	public void atualizarImovelLigacaoAgua(Imovel imovel,
			Integer idLigacaoAguaSituacao) throws ErroRepositorioException;

	/**
	 * 
	 * Gerar Relat�rio de Im�vel Outros Crit�rios
	 * 
	 * @author Rafael Corr�a
	 * @date 24/07/2006
	 * 
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
			String numeroMoradoresFinal, String idAreaConstruidaFaixa,
			String idUnidadeNegocio,String cdRotaInicial,
			String cdRotaFinal, String sequencialRotaInicial,
			String sequencialRotaFinal) throws ErroRepositorioException;

	public Collection pesquisarSubcategoriasImovelRelatorio(Integer idImovel)
			throws ErroRepositorioException;

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
			String numeroMoradoresFinal, String idAreaConstruidaFaixa,
			String idUnidadeNegocio, Integer relatorio,String cdRotaInicial,
			String cdRotaFinal, String sequencialRotaInicial,
			String sequencialRotaFinal)
			throws ErroRepositorioException;

	/**
	 * O m�todo abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necess�rios para a cria��o da inscri��o para exibi��o.
	 * 
	 * acima no controlador ser� montada a inscri��o
	 */
	public Object[] pesquisarInscricaoImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * 
	 * Esse m�todo � usado para fzazer uma pesquisa na tabela im�vel e confirmar
	 * se o id passado � de um im�vel exclu�do(idExclusao)
	 * 
	 * Fl�vio Cordeiro
	 */

	public Boolean confirmarImovelExcluido(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * 
	 * Gerar Relat�rio de Dados de Economias do Im�vel
	 * 
	 * @author Rafael Santos
	 * @date 02/08/2006
	 * 
	 */
	public Collection gerarRelatorioDadosEconomiasImovelOutrosCriterios(
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
			String idUnidadeNegocio) throws ErroRepositorioException;

	/**
	 * 
	 * Obtem os dados da Subcategoria do Imovel para o Relatorio de Dados
	 * Economias para o Imovel
	 * 
	 * @author Rafael Santos
	 * @date 02/08/2006
	 * 
	 */
	public Collection gerarRelatorioDadosEconomiasImovelSubcategoria(
			String idImovel) throws ErroRepositorioException;

	/**
	 * 
	 * Obtem os dados do Imovel Economia do Imovel para o Relatorio de Dados
	 * Economias para o Imovel
	 * 
	 * @author Rafael Santos
	 * @date 02/08/2006
	 * 
	 */
	public Collection gerarRelatorioDadosEconomiasImovelEconomia(
			String idImovel, String idSubCategoria)
			throws ErroRepositorioException;

	/**
	 * 
	 * Obtem os dados do Cliente Imovel Economia do Imovel para o Relatorio de
	 * Dados Economias para o Imovel
	 * 
	 * @author Rafael Santos
	 * @date 02/08/2006
	 * 
	 */
	public Collection gerarRelatorioDadosEconomiasImovelClienteEconomia(
			String idImovelEconomia) throws ErroRepositorioException;

	/**
	 * 
	 * [UC164] Filtrar Imoveis Outros Criterios
	 * 
	 * Monta os inner join da query de acordo com os parametros informados
	 * 
	 * @author Rafael Santos
	 * @date 03/08/2006
	 * 
	 * @return
	 */
	public String montarInnerJoinFiltrarImoveisOutrosCriterios(

	String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal,
			String idFaturamentoSituacaoTipo, String idCobrancaSituacaoTipo,
			String idSituacaoEspecialCobranca, String idEloAnormalidade,
			String idCadastroOcorrencia, String idConsumoTarifa,
			String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria, String idCliente,
			String idClienteTipo, String idClienteRelacaoTipo, Integer relatorio, String cpfCnpj);

	/**
	 * Permite pesquisar entidade beneficente [UC0389] Inserir Autoriza��o para
	 * Doa��o Mensal
	 * 
	 * @author C�sar Ara�jo
	 * @date 30/08/2006
	 * @param filtroEntidadeBeneficente -
	 *            Filtro com os valores para pesquisa
	 * @return Collection<EntidadeBeneficente> - Cole��o de entidade(s)
	 *         beneficente(s)
	 * @throws ErroRepositorioException
	 */
	public Collection<EntidadeBeneficente> pesquisarEntidadeBeneficente(
			FiltroEntidadeBeneficente filtroEntidadeBeneficente)
			throws ErroRepositorioException;

	/**
	 * Permite pesquisar im�vel doa��o [UC0389] Inserir Autoriza��o para Doa��o
	 * Mensal
	 * 
	 * @author C�sar Ara�jo
	 * @date 30/08/2006
	 * @param filtroImoveldoacao -
	 *            Filtro com os valores para pesquisa
	 * @return Collection<ImovelDoacao> - Cole��o de im�vei(s) doa��o
	 * @throws ErroRepositorioException
	 */
	public Collection<ImovelDoacao> pesquisarImovelDoacao(
			FiltroImovelDoacao filtroImovelDoacao)
			throws ErroRepositorioException;

	/**
	 * Permite atualizar as informa��es referentes ao im�vel doa��o [UC0390]
	 * Manter Autoriza��o para Doa��o Mensal
	 * 
	 * @author C�sar Ara�jo
	 * @date 30/08/2006
	 * @param imovelDoacao -
	 *            inst�ncia de im�vel doa��o que servir� de base para a
	 *            atuali��o
	 * @throws ErroRepositorioException
	 */
	public void atualizarImovelDoacao(ImovelDoacao imovelDoacao)
			throws ErroRepositorioException;

	/**
	 * Pesquisa um im�vel a partir do seu id.Retorna os dados que comp�em a
	 * inscri��o e o endere�o do mesmo
	 * 
	 * @author Raphael Rossiter
	 * @date 01/08/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarImovelRegistroAtendimento(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Consutlar os Dados Cadastrais do Imovel [UC0472] Consultar Im�vel
	 * 
	 * @author Rafael Santos
	 * @date 07/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection consultarImovelDadosCadastrais(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Pesquisa a cole��o de clientes do imovel [UC0472] Consultar Cliente
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarClientesImovel(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa a cole��o de clientes do imovel pela maior data [UC0472] Consultar Cliente
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarClientesImovelDataMax(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa a cole��o de clientes do imovel mesmo que o im�vel j� tenha sido exclu�do [UC0472] Consultar Imovel
	 * 
	 * @param filtroClienteImovel
	 * parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 * Description of the Exception
	 */
	public Collection pesquisarClientesImovelExcluidoOuNao(Integer idImovel) throws ErroRepositorioException;

	/**
	 * Pesquisa a cole��o de categorias do imovel [UC0472] Consultar Imovel
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarCategoriasImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0475] Consultar Perfil Imovel
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param idImovel
	 * @return Perfil do Im�vel
	 * @exception ErroRepositorioException
	 */
	public ImovelPerfil obterImovelPerfil(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Consutlar os Dados Complementares do Imovel [UC0473] Consultar Im�vel
	 * 
	 * @author Rafael Santos
	 * @date 11/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection consultarImovelDadosComplementares(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Pesquisa a cole��o de vencimento alternativos do imovel [UC0473]
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
			throws ErroRepositorioException;

	/**
	 * Pesquisa a cole��o de Debitos Automaticos do imovel [UC0473] Consultar
	 * Imovel Dados Complementares
	 * 
	 * @author Rafael Santos
	 * @date 11/09/2006
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarDebitosAutomaticosImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Pesquisa a cole��o de Faturamento Situa��o Historico do Imovel [UC0473]
	 * Consultar Imovel Dados Complementares
	 * 
	 * @author Rafael Santos
	 * @date 11/09/2006
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarFaturamentosSituacaoHistorico(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Pesquisa a cole��o de cobran�as Situa��o Historico do Imovel [UC0473]
	 * Consultar Imovel Dados Complementares
	 * 
	 * @author Rafael Santos
	 * @date 11/09/2006
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarCobrancasSituacaoHistorico(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Consutlar os Dados de Analise da Medi��o e Consumo do Imovel [UC0473]
	 * Consultar Im�vel
	 * 
	 * @author Rafael Santos
	 * @date 12/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection consultarImovelAnaliseMedicaoConsumo(Integer idImovel)
			throws ErroRepositorioException;

	// ----------Savio
	public void atualizarImovelLeituraAnormalidade(
			Map<Integer, MedicaoHistorico> mapAtualizarLeituraAnormalidadeImovel,Date dataAtual)
			throws ErroRepositorioException;

	/**
	 * Consutlar os Dados do Historico de Faturamento [UC0473] Consultar Im�vel
	 * 
	 * @author Rafael Santos
	 * @date 13/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection consultarImovelHistoricoFaturamento(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Consutlar o cliente usu�rio do Imovel [UC0473] Consultar Im�vel
	 * 
	 * @author Rafael Santos
	 * @date 13/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public String consultarClienteUsuarioImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Consutlar as contas do Imovel [UC0473] Consultar Im�vel
	 * 
	 * @author Rafael Santos
	 * @date 13/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection consultarContasImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Consutlar as contas Historicos do Imovel [UC0473] Consultar Im�vel
	 * 
	 * @author Rafael Santos
	 * @date 13/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection consultarContasHistoricosImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Consultar os dados de parcelamentos do Imovel [UC0473] Consultar Im�vel
	 * 
	 * @author Rafael Santos
	 * @date 20/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection consultarParcelamentosDebitosImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 27/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarClienteUsuarioImovel(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UCXXXX] Consultar Im�vel
	 * 
	 * @author Rafael Corr�a
	 * @date 22/05/2009
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteUsuarioImovelExcluidoOuNao(Integer idImovel)
		throws ErroRepositorioException;

	/**
	 * Atualiza apenas os dados (numeroParcelamento,
	 * numeroParcelamentoConsecutivo, numeroReparcelamentoConsecutivo) do im�vel
	 * 
	 * @param imovel
	 *            parametros para a consulta
	 * 
	 * Author: Fernanda Paiva
	 * 
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */

	public void atualizarDadosImovel(Integer codigoImovel,
			Integer numeroParcelamento,
			Integer numeroReparcelamentoConsecutivo,
			Integer numeroReparcelamento) throws ErroRepositorioException;

	public Collection<Categoria> pesquisarCategoriasImovel(Imovel imovel)
			throws ErroRepositorioException;

	/**
	 * 
	 * [UC0488] - Informar Retorno Ordem de Fiscaliza��o [SB0002] Atualizar
	 * Im�vel/Liga��o de �gua
	 * 
	 * 
	 * @date 14/11/2006
	 * @author S�vio Luiz
	 * @param imovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarImovelLigacaoAguaEsgoto(Integer idImovel,
			Integer idLigacaoAguaSituacao, Integer idLigacaoEsgotoSituacao)
			throws ErroRepositorioException;

	/**
	 * 
	 * [UC0488] - Informar Retorno Ordem de Fiscaliza��o [SB0002] Atualizar
	 * Im�vel/Liga��o de �gua
	 * 
	 * 
	 * @date 14/11/2006
	 * @author S�vio Luiz
	 * @param imovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarLigacaoAgua(Integer idLigacaoAgua,
			Integer consumoMinimo,short indiacadorConsumoFixado) throws ErroRepositorioException;

	/**
	 * 
	 * [UC0488] - Informar Retorno Ordem de Fiscaliza��o [SB0002] Atualizar
	 * Im�vel/Liga��o de �gua
	 * 
	 * 
	 * @date 14/11/2006
	 * @author S�vio Luiz
	 * @param imovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarLigacaoEsgoto(Integer idLigacaoEsgoto,
			Integer consumoMinimo,short indicadorVolumeFixado) throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0488] - Informar Retorno Ordem de Fiscaliza��o 
	 * 
	 * @date 20/11/2006
	 * @author S�vio Luiz
	 * @param imovel
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarConsumoTarifa(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * 
	 * Filtrar o Imovel pelos parametros informados
	 *
	 * @author Rafael Santos
	 * @date 24/11/2006
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
		    boolean pesquisarImovelManterVinculo,boolean pesquisarImovelCondominio, Integer numeroPagina) throws ErroRepositorioException; 

	/**
	 * 
	 * Filtrar o Imovel pelos parametros informados, para saber a quantidade de imoveis
	 *
	 * @author Rafael Santos
	 * @date 24/11/2006
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarQuantidadeImovel(
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
		    boolean pesquisarImovelManterVinculo, boolean pesquisarImovelCondominio) throws ErroRepositorioException;
	
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
		    String idLogradouro, boolean pesquisarImovelCondominio, Integer numeroPagina) throws ErroRepositorioException; 

	/**
	 * [UC0367] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * Recupera o id da situa��o da liga��o de esgoto
	 * 
	 * @author S�vio Luiz
	 * @date 04/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisaridLigacaoEsgotoSituacao(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UC0367] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * Recupera o id da situa��o da ligacao de agua
	 * 
	 * @author S�vio Luiz
	 * @date 04/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisaridLigacaoAguaSituacao(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UC0367] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * Recupera o id da situa��o da ligacao de agua
	 * 
	 * @author S�vio Luiz
	 * @date 04/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisarTarifaImovel(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa os im�veis do cliente de acordo com o tipo de rela��o
	 * 
	 * [UC0251] Gerar Atividade de A��o de Cobran�a [SB0001] Gerar Atividade de
	 * A��o de Cobran�a para os Im�veis do Cliente
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
			throws ErroRepositorioException;
	
	
	public Integer pesquisarExistenciaImovelSubCategoria(Integer idImovel, Integer idCategoria) throws ErroRepositorioException;
	
	public Collection pesquisarImoveisPorRota(Integer idRota) throws ErroRepositorioException;
	
	
	/**
	 * 
	 * [UC0378] Associar Tarifa de Consumo a Im�veis
	 * 
	 * 
	 * @author R�mulo Aur�lio
	 * @created 20/12/2006
	 * 
	 * @param dLocalidadeInicial,
	 *            idLocalidadeFinal, codigoSetorComercialInicial,
	 *            codigoSetorComercialFinal
	 * @param quadraInicial,
	 *            quadraFinal, String loteInicial, loteFinal, subLoteInicial,
	 *            subLoteFinal, idTarifaAnterior
	 * @return
	 * @throws ErroRepositorioException
	 */
	
	public Collection pesquisarImoveisTarifaConsumo(String idLocalidadeInicial,
			String idLocalidadeFinal, String codigoSetorComercialInicial,
			String codigoSetorComercialFinal, String quadraInicial,
			String quadraFinal, String loteInicial, String loteFinal,
			String subLoteInicial, String subLoteFinal, String idTarifaAnterior)
			throws ErroRepositorioException;

	
	
	
	/**
	 * Atualiza a tarifa de consumo de um ou mais imoveis
	 *  
	 * [UC0378] Associar Tarifa de Consumo a Im�veis 
	 * 
	 * @author R�mulo Aurelio
	 * @created 19/12/2006
	 * 
	 * @param matricula,
	 *            tarifaAtual, colecaoImoveis
	 * @return
	 * @throws ErroRepositorioException
	 */

	public void atualizarImoveisTarifaConsumo(String matricula,
			String tarifaAtual, Collection colecaoImoveis)
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Atualiza o perfil do im�vel para o perfil normal
	 * 
	 * @date 04/01/2007
	 * @author Rafael Corr�a
	 * @throws ErroRepositorioException
	 */
	public void atualizarImovelPerfilNormal(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UC0490] - Informar Situa��o de Cobran�a
	 * 
	 * Pesquisa o im�vel com a situa��o da liga��o de �gua e a de esgoto
	 * 
	 * @date 13/01/2007
	 * @author Rafael Corr�a
	 * @throws ErroRepositorioException
	 */
	public Imovel pesquisarImovelComSituacaoAguaEsgoto(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisar imoveis por rota, situacao de ligacao de agua e situacao de ligacao
	 * de esgoto, utilizando paginacao
	 * 
	 * Utilizado no  
	 * [UC0251] Gerar Atividade de A��o de Cobran�a [SB0001] Gerar Atividade de
	 * A��o de Cobran�a para os Im�veis do Cliente
	 * 
	 * @param idRota
	 * @param idsSituacaoLigacaoAgua
	 * @param idsSituacaoLigacaoEsgoto
	 * @param numeroInicial
	 * @return 
	 * @throws ControladorException
	 * 
	 * @author Breno Santos
	 * @date 21/10/2009
	 * 
	 */	
	public Collection pesquisarImoveisPorRotaComPaginacaoSemRotaAlternativa(Rota rota,
			Collection idsSituacaoLigacaoAgua, Collection idsSituacaoLigacaoEsgoto,
			Integer numeroInicial, int numeroMaximo) throws ErroRepositorioException;
	
	
	/**
	 * Pesquisar imoveis por rota, situacao de ligacao de agua e situacao de ligacao
	 * de esgoto, utilizando paginacao
	 * 
	 * Utilizado no  
	 * [UC0251] Gerar Atividade de A��o de Cobran�a [SB0001] Gerar Atividade de
	 * A��o de Cobran�a para os Im�veis do Cliente
	 * 
	 * @param idRota
	 * @param idsSituacaoLigacaoAgua
	 * @param idsSituacaoLigacaoEsgoto
	 * @param numeroInicial
	 * @return 
	 * @throws ControladorException
	 * 
	 * @author Breno Santos
	 * @date 21/10/2009
	 * 
	 */	
	public Collection pesquisarImoveisPorRotaComPaginacaoComRotaAlternativa(Rota rota,
			Collection idsSituacaoLigacaoAgua, Collection idsSituacaoLigacaoEsgoto,
			Integer numeroInicial, int numeroMaximo) throws ErroRepositorioException;
	
/**
     * 
     * Usado pelo Pesquisar Imovel Retorno o Imovel, com o Nome do Cliente,
     * Matricula e Endere�o
     * 
     * @author Rafael Santos
     * @date 27/11/2006
     * 
     * @param idImovel
     * @return
     * @throws ErroRepositorioException
     */
    public Collection pesquisarImovelInscricaoNew(String idImovel,
            String idLocalidade, String codigoSetorComercial,
            String numeroQuadra, String lote, String subLote,
            String codigoCliente, String idMunicipio, String cep,
            String idBairro, String idLogradouro, String numeroImovelInicial, String numeroImovelFinal,  
            boolean pesquisarImovelCondominio, Integer numeroPagina)
            throws ErroRepositorioException ;
	
	/**
	 * [UC0069] - Manter Dados da Tarifa Social
	 * 
	 * [FS0006] - Verificar n�mero de IPTU
	 * 
	 * Verifica se j� existe outro im�vel ou economia com o mesmo n�mero de IPTU
	 * no mesmo munic�pio
	 * 
	 * @date 13/01/2007
	 * @author Rafael Corr�a
	 * @throws ErroRepositorioException
	 */
	public Integer verificarNumeroIptu(String numeroIptu, Integer idImovel,
			Integer idImovelEconomia, Integer idMunicipio)
			throws ErroRepositorioException;
	
	/**
	 * [UC0069] - Manter Dados da Tarifa Social
	 * 
	 * [FS0007] - Verificar n�mero de contrato da companhia de energia el�trica
	 * 
	 * Verifica se j� existe outro im�vel ou economia com o mesmo n�mero de
	 * contrato da companhia el�trica
	 * 
	 * @date 18/01/2007
	 * @author Rafael Corr�a
	 * @throws ErroRepositorioException
	 */
	public Integer verificarNumeroCompanhiaEletrica(Long numeroCompanhiaEletrica, Integer idImovel,
			Integer idImovelEconomia)
			throws ErroRepositorioException;
	
	/**
	 * Obt�m o quantidade de economias da subCategoria por imovel
	 * 
	 * @param idImovel 		O identificador do im�vel
	 * @return 				Cole��o de SubCategorias
	 * @exception ErroRepositorioException Descri��o da exce��o
	 */
	public Collection obterQuantidadeEconomiasSubCategoria(Integer idImovel)
			throws ErroRepositorioException ;
	
	
	/**
	 * Obt�m o quantidade de economias da subCategoria por imovel
	 * @autor R�mulo Aur�lio 
	 * @param idImovel 		O identificador do im�vel
	 * @return 				Cole��o de imovelSubCategorias
	 * @exception ErroRepositorioException Descri��o da exce��o
	 * @date 08/02/2007
	 */
	
	
	public Collection pesquisarImovelSubcategorias(Integer idImovel)
			throws ErroRepositorioException ;
	
	/**
	 * @date 21/02/2007
	 * @author Vivianne Sousa
	 * @throws ErroRepositorioException
	 */
	public Imovel pesquisarImovel(Integer idImovel)
		throws ErroRepositorioException;
	
	
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
			LogradouroBairro logradouroBairroNovo) throws ErroRepositorioException ;
	
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
			LogradouroCep logradouroCepNovo) throws ErroRepositorioException ;
	
	/**
	 * [UC0302] Gerar D�bitos a Cobrar de Acr�scimos por Impontualidade
	 *
	 * Pequisa o identificador de cobranca de acr�scimo pro impontualidade 
	 * para o im�vel do cliente respons�vel.
	 *
	 * @author Pedro Alexandre
	 * @date 26/02/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Short obterIndicadorGeracaoAcrescimosClienteImovel(Integer idImovel)	throws ErroRepositorioException ;
	
//	public Integer verificarExistenciaImovelParaCliente(Integer idImovel)
//	throws ErroRepositorioException;
	
	
	
	/**  
	 * [UC0488] - Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * Obter o consumo m�dio como n�o medido para o im�vel passado
	 * 
	 * @author Raphael Rossiter
	 * @date 06/03/2007
	 */
	public Integer obterConsumoMedioNaoMedidoImovel(Imovel imovel)
			throws ErroRepositorioException ;
	
	
	/**
	 * Filtra o Pagamento pelo seu id carregando os dados necess�rios
	 * 
	 * [UC0549] Consultar Dados do Pagamento
	 * 
	 * @author K�ssia Albuquerque
	 * @date 12/07/2007
	 * 
	 * @param idPagamentoHistorico
	 * @return Collection<PagamentoHistorico>
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentoPeloId(Integer idPagamento) throws ErroRepositorioException ;
	
	/**
	 * Filtra o Pagamento Historico pelo seu id carregando os dados necess�rios
	 * 
	 * [UC0549] Consultar Dados do Pagamento
	 * 
	 * @author K�ssia Albuquerque
	 * @date 12/07/2007
	 * 
	 * @param idPagamentoHistorico
	 * @return Collection<PagamentoHistorico>
	 * @throws ErroRepositorioException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoPeloId(Integer idPagamentoHistorico) throws ErroRepositorioException;
	
	/**  
	 * Obter a situa��o de cobran�a para o im�vel passado
	 * 
	 * @author Vivianne Sousa
	 * @date 07/03/2007
	 */
	public String obterSituacaoCobrancaImovel(Integer idImovel)
			throws ErroRepositorioException ;
	
	/**
	 * Pesquisa uma cole��o de im�veis
	 * 
	 * @author Ana Maria
	 * @date 16/03/2007
	 */
	public Collection pesquisarColecaoImovel(FiltrarImovelInserirManterContaHelper filtro) 
		throws ErroRepositorioException;
	
	/**
	 * 
	 * Pesquisa uma cole��o de im�veis com perfil bloqueado
	 * 
	 * @return Boolean
	 * @exception ErroRepositorioException
	 * 
	 */
	public Integer pesquisarColecaoImovelPerfilBloqueado(FiltrarImovelInserirManterContaHelper filtro)
		throws ErroRepositorioException;
	
	/**
	 * Pesquisa uma cole��o de im�veis do cliente
	 * 	
	 * @author Ana Maria
	 * @date 20/03/2007
	 */
	public Collection pesquisarColecaoImovelCliente(Integer codigoCliente, Integer relacaoTipo, Boolean verificarImovelPerfilBloqueado) 
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa quantidade de im�veis do cliente com perfil bloqueado
	 * 
	 * @author Ana Maria
	 * @date 20/04/2009
	 */
	public Integer pesquisarColecaoImovelClienteBloqueadoPerfil(Integer codigoCliente,
			Integer relacaoTipo) throws ErroRepositorioException;
	
	public Integer pesquisarContaMotivoRevisao(Integer idImovel)
		    throws ErroRepositorioException;	
	
	/**  
	 * [UC0150] - Retificar Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 16/04/2007
	 */
	public Collection obterQuantidadeEconomiasCategoriaPorSubcategoria(Integer conta)
	throws ErroRepositorioException ;
	
	/**
	 * Obtem as subcategorias de uma determinada categoria
	 * @param id Id da categoria a ser pesquisada
	 * @return Colecao de subcategorias
	 * @throws ErroRepositorioException
	 */
	public Collection obterSubCategoriasPorCategoria( Integer idCategoria, Integer idImovel ) 
	  		throws ErroRepositorioException;
	
	/**
	 * Pesquisa todos os ids do perfil do im�vel.
	 *
	 * [UC0564 - Gerar Resumo das Instala��es de Hidr�metros]
	 *
	 * @author Pedro Alexandre
	 * @date 25/04/2007
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarTodosIdsPerfilImovel() throws ErroRepositorioException ;
	
	/**
	 * Consutlar o cliente usu�rio do Imovel [UC0473] Consultar Im�vel
	 * 
	 * @author Bruno Barros
	 * @date 27/04/2007
	 * 
	 * @param imovel
	 * @return Cliente
	 * @throws ErroRepositorioException
	 */
	public Cliente consultarClienteUsuarioImovel( Imovel imovel )
			throws ErroRepositorioException;
	
	/**  
	 * [UC0591] - Gerar Relat�rio de Clientes Especiais
	 * 
	 * Pesquisas os im�veis de acordo com os par�metros da pesquisa
	 * 
	 * @author Rafael Corr�a
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
		throws ErroRepositorioException;
	

	/**
	 * [UC00609] Transferencia de Debitos/Creditos
	 * 
	 * Recupera a situa��o da liga��o de esgoto
	 * 
	 * @author Raphael Rossiter
	 * @date 07/06/2007
	 * 
	 * @param idImovel
	 * @return LigacaoEsgotoSituacao
	 * @throws ErroRepositorioException
	 */
	public LigacaoEsgotoSituacao pesquisarLigacaoEsgotoSituacao(Integer idImovel)
			throws ErroRepositorioException ;
	
	
	/**
	 * [UC00609] Transferencia de Debitos/Creditos
	 * 
	 * Recupera a situa��o da liga��o de agua
	 * 
	 * @author Raphael Rossiter
	 * @date 07/06/2007
	 * 
	 * @param idImovel
	 * @return LigacaoAguaSituacao
	 * @throws ErroRepositorioException
	 */
	public LigacaoAguaSituacao pesquisarLigacaoAguaSituacao(Integer idImovel)
			throws ErroRepositorioException ;

	
	public FaturamentoGrupo pesquisarGrupoImovel(Integer idImovel) throws ErroRepositorioException;
	
	
	/**
	 * Obt�m a descri��o de uma categoria
	 *
	 * @author Rafael Corr�a
	 * @date 04/06/2007
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String obterDescricaoSubcategoria(Integer idSubcategoria)
			throws ErroRepositorioException;
	
 	/**
 	 * 
 	 * Gerar Relat�rio de Im�vel Outros Crit�rios
 	 * 
 	 * @author Rafael Corr�a,Rafael Santos
 	 * @date 24/07/2006,01/08/2006
 	 * 
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
 			String ordenacaoRelatorio) throws ErroRepositorioException;	

 	
	/**
	 * 
	 * Gerar Relat�rio de Im�vel Outros Crit�rios
	 * 
	 * @author Rafael Corr�a,Rafael Santos
	 * @date 24/07/2006,01/08/2006
	 * 
	 */
	public Collection gerarRelatorioCadastroConsumidoreInscricao(
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
			String sequencialRotaFinal, String ordenacao, String[] pocoTipoListIds) throws ErroRepositorioException;
 	
	
	/**
	 * Obt�m a o nome do cliente
	 *
	 * @author Kassia Albuquerque
	 * @date 05/07/2007
	 *
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] obterDescricaoIdCliente(Integer idImovel)
			throws ErroRepositorioException;
 	
	
	/**
	 * Obt�m a o nome do arrecadador
	 *
	 * @author Kassia Albuquerque
	 * @date 09/07/2007
	 *
	 * @return String
	 * @throws ErroRepositorioException
	 */

	public String pesquisarNomeAgenteArrecadador(Integer idPagamentoHistorico)
			throws ErroRepositorioException;
	
	/**
	 * Obt�m a o 117� caracter de uma String
	 *
	 * @author Kassia Albuquerque
	 * @date 20/07/2007
	 *
	 * @return String
	 * @throws ErroRepositorioException
	 */

	public String pesquisarCaracterRetorno(Integer idConteudoRegistro) throws ErroRepositorioException;
	
	/**
	 * [UC0623] - GERAR RESUMO DE METAS EXECUTDO NO M�S(CAERN)
	 * 
	 * @author S�vio Luiz
	 * @date 08/08/2007
	 */
	public Object[] obterSubCategoriasComCodigoGrupoPorCategoria(
			Integer idCategoria, Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * O m�todo abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necess�rios para a cria��o da inscri��o para exibi��o.
	 * 
	 * acima no controlador ser� montada a inscri��o
	 */
	public Object[] pesquisarLocalidadeSetorImovel(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * @author S�vio Luiz
	 * @date 24/08/2007
	 * 
	 * @return String
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarSequencialRota(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * Retorna o cep do im�vel
	 * 
	 * @param imovel
	 * 
	 * @return Descri��o do retorno
	 * 
	 * @exception ErroRepositorioException
	 * 
	 */
	public Cep pesquisarCepImovel(Imovel imovel)
			throws ErroRepositorioException;

	
	/**
	 * Gerar Boletim de Cadastro
	 * 
	 * @date 20/08/2007
	 */
	public Collection<EmitirDocumentoCobrancaBoletimCadastroHelper> pesquisarBoletimCadastro(
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
			String idUnidadeNegocio, int quantidadeCobrancaDocumentoInicio, 
			String indicadorCpfCnpj, String cpfCnpj) throws ErroRepositorioException;
	/**
	 * @author Vivianne Sousa
	 * @date 19/09/2007
	 * 
	 * @return ImovelCobrancaSituacao
	 * @throws ErroRepositorioException
	 */

	public CobrancaSituacao pesquisarImovelCobrancaSituacao(Integer idImovel) throws ErroRepositorioException;
    
    /**
     * [UC0541] Emitir 2 Via de Conta Internet
     * 
     * @author Vivianne Sousa
     * @date 02/09/2007
     * 
     * @throws ErroRepositorioException
     */

    public Imovel pesquisarDadosImovel(Integer idImovel) throws ErroRepositorioException;
    
    /**
	 * O m�todo abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necess�rios para a cria��o da inscri��o e de rota para exibi��o.
	 * 
	 * @author Vivianne Sousa
     * @date 06/11/2007
	 */

	public Collection pesquisarInscricaoImoveleRota(String idsImovel)
	throws ErroRepositorioException;
	
	/**
	 * 
	 * Pesquisa os im�veis do cliente de acordo com o tipo de rela��o
	 * 
	 * 
	 * 
	 * [UC0251] Gerar Atividade de A��o de Cobran�a [SB0001] Gerar Atividade de
	 * 
	 * A��o de Cobran�a para os Im�veis do Cliente
	 * 
	 * 
	 * 
	 * @author S�vio Luiz
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

	throws ErroRepositorioException;
	
	/**
	 * 
	 * Buscar Empresa apatir da Matr�cula de um Im�vel .
	 * 
	 * 
	 * @author Thiago Nascimento
	 * @date 21/01/2008
	 * 
	 * @param dados
	 * 
	 * @throws ControladorException
	 */
	public Empresa buscarEmpresaPorMatriculaImovel(Integer imovel) throws ErroRepositorioException;
	
	/**
	 * 
	 * Atualiza a situa��o de cobran�a do im�vel
	 * 
	 */

	public void atualizarSituacaoCobrancaImovel(Integer idSituacaoCobranca, Integer idImovel)
		throws ErroRepositorioException;
	
	/**
	 * Filtrar o Imovel pelos parametros informados, para saber a quantidade de imoveis.
	 * Utilizado para corrigir o erro da listagem de Imoveis: o metodo pesquisarQuantidadeImovel nao
	 * traz a mesma quantidade de imovel do metodo pesquisarImovelInscricaoNew.  
	 * 
	 * @author Ivan S�rgio
	 * @date 11/03/2008
	 * 
	 * @param idImovel
	 * @param idLocalidade
	 * @param codigoSetorComercial
	 * @param numeroQuadra
	 * @param lote
	 * @param subLote
	 * @param codigoCliente
	 * @param idMunicipio
	 * @param cep
	 * @param idBairro
	 * @param idLogradouro
	 * @param pesquisarImovelCondominio
	 * @param numeroPagina
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarQuantidadeImovelInscricao(
		String idImovel, String idLocalidade, String codigoSetorComercial,
		String numeroQuadra, String lote, String subLote,
		String codigoCliente, String idMunicipio, String cep,
		String idBairro, String idLogradouro, String numeroImovelInicial, String numeroImovelFinal, 
		boolean pesquisarImovelCondominio) throws ErroRepositorioException;
	
	/**
	 * 
	 * recupera os ImovelSubcategoria, com 5 resultados, ordenados por Qt economias desc
	 *
	 * @author Tiago Moreno
	 * @date 08/04/2008
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	
	public Collection pesquisarImovelSubcategoriasMaxCinco(Integer idImovel)

	throws ErroRepositorioException;
	
	
	/**
	 * [UC0800] - Obter Consumo N�o Medido
	 *
	 * [FS0001] - Verificar �rea N�o Informada 
	 *
	 * @author Raphael Rossiter
	 * @date 22/05/2008
	 *
	 * @param idImovel
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarAreaConstruida(Integer idImovel) throws ErroRepositorioException ;
	
    /**
	 * O m�todo abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necess�rios para a cria��o da inscri��o e de rota para exibi��o.
	 * 
	 * @author Vivianne Sousa
     * @date 06/11/2007
	 */

	public Collection pesquisarInscricaoImoveleRota(Collection colecaoIdsImovel)
		throws ErroRepositorioException;
	
    /**
	 * O m�todo abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necess�rios para a cria��o da inscri��o e de rota para exibi��o.
	 * 
	 * @author Vivianne Sousa
     * @date 06/11/2007
	 */
	public Collection pesquisarInscricaoImoveleRota(FiltrarAnaliseExcecoesLeiturasHelper filtrarAnaliseExcecoesLeiturasHelper, Integer anoMes)
		throws ErroRepositorioException;
    
    /**
     * [UC0011] Inserir Imovel
     * 
     * @author Vivianne Sousa
     * @date 23/05/2008
     */
    public Collection pesquisarImovelPerfilDiferenteCorporativo() throws ErroRepositorioException;

	/**
	 * M�todo para obter o id da esfera de poder de um imovel
	 * @param idImovel
	 * @return
	 * 
	 * @author Francisco do Nascimento
	 * @date 22-05-2008
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer obterIdEsferaPoder(Integer idImovel)
		throws ErroRepositorioException;

	/**
	 * Consulta o ID da categoria principal, considerando que a categoria principal � a quem tem
	 * maior soma de qtd de economias em suas subcategorias e tenha o 
	 * idCategoria menor (ou maior caso exija ordemDecrescente seja true)
	 * 
	 *  @author Francisco do Nascimento
	 *  @date 22/05/2008
	 *  
	 */
	public Integer obterIdCategoriaPrincipal(Integer idImovel, boolean ordemDecrescente)
		throws ErroRepositorioException;
	
    /**
     * [UC0011] Inserir Imovel
     * 
     * @author Vivianne Sousa
     * @date 23/05/2008
     */
    public Collection pesquisarImovelPerfilTarifaSocialDiferenteCorporativo() throws ErroRepositorioException;
    
    
    
    
    /**
     * [UC0823] Atualiza Liga��o de �gua de Ligado em An�lise para Ligado
     * 
     * Seleciona a lista de im�veis que esteja com a situa��o de �gua ligado em an�lise.
     * @author Yara Taciane
     * @date 23/05/2008
     */
    public List pesquisarLocalidadeComImoveisComSituacaoLigadoEmAnalise()throws ErroRepositorioException;
    
    
    /**
     * [UC0823] Atualiza Liga��o de �gua de Ligado em An�lise para Ligado
     * 
     * Seleciona a lista de im�veis que esteja com a situa��o de �gua ligado em an�lise.
     * @author Yara Taciane
     * @date 23/05/2008
     */
	public Collection pesquisarImoveisComSituacaoLigadoEmAnalise(Integer idLocalidade)throws ErroRepositorioException;
	
	   /**
     * [UC0823] Atualiza Liga��o de �gua de Ligado em An�lise para Ligado
     * 
     * Seleciona a lista de im�veis que esteja com a situa��o de �gua ligado em an�lise.
     * @author Yara Taciane
     * @date 23/05/2008
     */
	
	public void atualizarSituacaoAguaPorImovel(String id, String situacaoAguaLigado) throws ErroRepositorioException;
	
	/**
	 * [UC0101] - Consistir Leituras e Calcular Consumos
	 *
	 * Pesquisar as contas pertencentes ao imovel e anoMes informados que n�o estejam com a situa��o
	 * atual igual a "PR�-FATURADA" 
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
	Integer anoMesReferencia) throws ErroRepositorioException ;
	
	/**
	 * 
	 * Consultar Perfil Quadra
	 * 
	 * @param idImovel
	 * 
	 * @return Perfil da Quadra
	 * 
	 * @exception ErroRepositorioException
	 * 
	 */

	public Integer obterQuadraPerfil(Integer idImovel)
		 throws ErroRepositorioException;
	
	/**
	 * [UC0831] Gerar Tabelas para Atualiza��o Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 22/09/2008
	 */
	public void atualizarImovelSituacaoAtualizacaoCadastral(Integer idImovel,
			Integer idSituacaoAtualizacaoCadastral) throws ErroRepositorioException;
	
	
	/**
	 * [UC0831] Gerar Tabelas para Atualiza��o Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 22/09/2008
	 */
	public void atualizarImovelAtualizacaoCadastralSituacaoAtualizacaoCadastral(Integer idImovel,
			Integer idSituacaoAtualizacaoCadastral, Integer idEmpresa) throws ErroRepositorioException;

	/**
	 * [UC0831] Gerar Tabelas para Atualiza��o Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 26/09/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer verificaExistenciaImovelAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException;

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
			throws ErroRepositorioException;
	
    /**
     * Pesquisar Im�vel Atualiza��o Cadastral(Dados da Inscri��o)
     * 
     * @author Ana Maria
     * @date 17/09/2008
     * 
     * @throws ErroRepositorioException
     */
    public ImovelAtualizacaoCadastral pesquisarImovelAtualizacaoCadastralInscricao(Integer idImovel, Integer idEmpresa) 
    	throws ErroRepositorioException;
    
	/**
	 * Consultar Im�veis Atualiza��o Cadastral por Quadra
	 * 
	 * @param idSetorComercial
	 * @param quadraInicial
	 * @param quadraFinal
	 * @param idEmpresa
	 * @return Collection<Imovel> - Cole��o de im�veis.
	 * 
	 * @author Ana Maria
     * @date 18/09/2008
	 * @exception ErroRepositorioException
	 */
	public Collection obterImoveisAtualizacaoCadastral(Integer idLocalidade, String codigoSetor,
			Integer quadraInicial, Integer quadraFinal, Integer idEmpresa, Integer idRota)throws ErroRepositorioException;
	
	/**
	 * Pesquisar exist�ncia de im�vel economia
	 * 
	 * @author Ana Maria
	 * @date 05/12/2008
	 * 
	 * @return Boolean
	 * @throws ErroRepositorioException
	 */
	public Boolean pesquisarExistenciaImovelEconomia(Integer idImovel, Integer idSubcategoria) 
			throws ErroRepositorioException;
	/**
	 * Validar a(s) categoria(s) do imovel com a(s) respectivas tarifas 
	 * 
	 * @author R�mulo Aur�lio
	 * @date 19/12/2008
	 * 
	 * @return Boolean
	 * @throws ErroRepositorioException
	 */
	public Boolean verificarExistenciaTarifaConsumoCategoriaParaCategoriaImovel(Integer idImovel, Integer idCategoria)
	throws ErroRepositorioException ;
	
	/**
	 * Remover Im�vel Subcategoria
	 *  
	 * @author Ana Maria
	 * @date 10/02/2009
	 * 
	 * @param idImovel
	 * @throws ErroRepositorioException
	 */
	public void removerImovelSubcategoria(Integer idImovel)throws ErroRepositorioException;
	
	/**
	 * Remover Im�vel Ramo Atividade
	 *  
	 * @author Jos� Guilherme
	 * @date 02/10/2009
	 * 
	 * @param Integer idImovel
	 * @throws ErroRepositorioException
	 */
	public void removerImovelRamoAtividade(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UC0011] Inserir Im�vel
	 * 
	 * @author Victor Cisneiros
	 * @date 18/02/2009
	 */
    public Integer pesquisarMaiorNumeroLoteDaQuadra(Integer idQuadra) throws ErroRepositorioException;
    
    /**
	 * Consultar os dodos cliente usu�rio do Imovel 
	 * 
	 * @author Arthur Carvalho
	 * @date 12/03/2009
	 */
    public Object[] consultarDadosClienteUsuarioImovel(
			String idImovel) throws ErroRepositorioException ;
    
    /**
	 * 
	 * 
	 * 
	 * Gerar Relat�rio de Im�vel Outros Crit�rios
	 * 
	 * 
	 * 
	 * @author Rafael Corr�a,Rafael Santos
	 * 
	 * @date 24/07/2006,01/08/2006
	 * 
	 * 
	 * 
	 */

	public Integer gerarRelatorioCadastroConsumidoresInscricaoCount(String idImovelCondominio, 
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
											String cdRotaInicial,
											String cdRotaFinal, 
											String sequencialRotaInicial,
											String sequencialRotaFinal,
											String[] pocoTipoListIds
											) throws ErroRepositorioException; 
	
	
	/**
	 * 
	 * 
	 * 
	 * Gerar Relat�rio de Im�vel Outros Crit�rios
	 * 
	 * 
	 * 
	 * @author Rafael Corr�a,Rafael Santos
	 * 
	 * @date 24/07/2006,01/08/2006
	 * 
	 * 
	 * 
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
							String seqRotaFinal) throws ErroRepositorioException;
    
	/**
	 * O m�todo abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necess�rios para a cria��o da inscri��o para exibi��o, independente do
	 * im�vel ter sido exclu�do ou n�o.
	 * 
	 * acima no controlador ser� montada a inscri��o
	 */
	public Object[] pesquisarInscricaoImovelExcluidoOuNao(Integer idImovel)
		throws ErroRepositorioException;
	
    /**
	 * 
	 * Atualiza a situa��o de cobran�a e a situa��o do tipo de cobran�a
	 * 
	 * @author S�vio Luiz
	 * @date 18/02/2009
	 */

	public void atualizarSituacaoCobrancaETipoIdsImoveis(Integer idSituacaoCobranca,Integer idSituacaoCobrancaTipo, Collection<Integer> idsImovel)
		throws ErroRepositorioException; 
	/**
	 * Verificar se o im�vel perfil est� bloqueado
	 * 
	 * @author Ana Maria
	 * @date 22/04/2009
	 * 
	 * @return Boolean
	 * @throws ErroRepositorioException
	 */
	public Boolean verificarImovelPerfilBloqueado(Integer idImovel) 
			throws ErroRepositorioException;
	
	/**
	 * Verificar se a ultima alteracao do im�vel 
	 * 
	 * @author R�mulo Aur�lio
	 * @date 27/05/2009
	 * 
	 * @return Date
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarUltimaAlteracaoImovel(Integer idImovel) 
			throws ErroRepositorioException ;

	/**
	 * Verifica se existe imovel com n�mero do Medidor de Energia informado
	 * 
	 * @author R�mulo Aur�lio
	 * @date 08/06/2009
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarImovelComNumeroMedidorEnergiaImovel(String numeroMedidorEnergia) 
			throws ErroRepositorioException;
	
	/**
	 * 
	 * Pesquisa a cole��o de categorias do imovel [UC0472] Consultar Imovel
	 * 
	 * 
	 * 
	 * @author S�vio Luiz
	 * 
	 * @since 07/09/2006
	 * 
	 * 
	 * 
	 * @param filtroClienteImovel
	 * 
	 * parametros para a consulta
	 * 
	 * @return Description of the Return Value
	 * 
	 * @exception ErroRepositorioException
	 * 
	 * Description of the Exception
	 * 
	 */

	public Collection pesquisarCategoriaSubcategoriaImovel(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * 
	 * Pesquisa qual foi o consumo faturado do imovel
	 * 
	 * @author Hugo Amorim
	 * @date 18/12/2009
	 * @return consumoFaturadoMes
	 * @throws ControladorException
	 */
	public Integer obterConsumoFaturadoImovelNoMes(Integer idImovel,
			Integer mesAnoReferencia) throws ErroRepositorioException;
	
	/**
	 * 
	 * Pesquisa o perfil do imovel do imovel informado
	 * 
	 * @author R�mulo Aur�lio
	 * @date 03/03/2010
	 * @throws ControladorException
	 */
	public ImovelPerfil recuperaPerfilImovel(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC0101] - Consistir Leituras e Calcular Consumos
	 *
	 * Pesquisar as contas pertencentes ao imovel e anoMes informados que n�o estejam com a situa��o
	 * atual igual a "PR�-FATURADA" 
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

	Integer anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * [UC0820] Atualizar Faturamento do Movimento Celular
     * 
     * M�todo criado para atualizar apenas os campos necess�rios para
     * imovel.
     * 	 
     * @author Bruno Barros
     * @date 31/03/2010
     * @param imovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarImovelLeituraAnormalidadeProcessoMOBILE( Imovel imovel ) throws ErroRepositorioException;
	
	/**
	 * [UC1005] Determinar Confirma��o da Negativa��o
	 *
	 * @author Vivianne Sousa
	 * @date 11/03/2010
	 */
	public void atualizarDataRetiradaImovelSituacaoCobranca(
			Integer idImovelSituacaoCobranca, Date dataRetirada) throws ErroRepositorioException;
	
	/**
	 * [UC0672] - Registrar Movimento de Retorno dos Negativadores
	 *
	 * @author Vivianne Sousa
	 * @date 12/03/2010
	 */
	public void atualizarSituacaoCobrancaImovel(Integer idSituacaoCobrancaNova, 
			Integer idSituacaoCobrancaBanco, Integer idImovel)	throws ErroRepositorioException;

	/** 
	 * [UC1000] Informar Medidor de Energia por Rota.
	 * 
	 * Atualizar N�mero Medidor de Energia do Im�vel.
	 * 
	 * @author Hugo Leonardo
	 * @date 15/03/2010
	 *
	 */
	public void atualizarNumeroMedidorEnergiaImovel(Integer matricula, String medidorEnergia)
		throws ErroRepositorioException; 
	
	/**  
	 * [UC0591] - Gerar Relat�rio de Clientes Especiais
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
			String consumoAnormalidade) throws ErroRepositorioException;

	/**
	 * @author Vivianne Sousa
	 * @date 11/05/2010
	 */
	public Collection pesquisarImovelCobrancaSituacaoPorImovel(
			String[] idsImovelCobrancaSituacao) throws ErroRepositorioException;
	
	/**
	 * [UC0490] Informar Situa��o de Cobran�a
	 * [SB0004] � Selecionar Situa��es de Cobran�a
	 * 
	 * seleciona as situa��es de cobran�a 
	 * (a partir da tabela COBRAN�A_SITUACAO com CBST_ICUSO=1 
	 * e CBST_ICBLOQUEIOINCLUSAO=2)retirando as ocorr�ncias 
	 * com CBST_ID=CBST_ID da tabela IMOVEL_COBRANCA_SITUACAO 
	 * para IMOV_ID=Id do im�vel recebido e 
	 * ISCB_DTRETIRADACOBRANCA com valor igual a nulo
	 * 
	 * @author Vivianne Sousa
	 * @date 12/05/2010
	 */
	public Collection pesquisarCobrancaSituacao(
			Integer idImovel, boolean temPermissaoEspecial) throws ErroRepositorioException ;
	
	
	/**
	 * 
	 * O m�todo abaixo realiza uma pesquisa em imovel e retorna os campos
	 * 
	 * id localidade e codigo do setor
	 * 
	 * @author Hugo Amorim
	 * @date 01/06/2010
	 */
	public Object[] pesquisarLocalidadeCodigoSetorImovel(Integer idImovel)
		throws ErroRepositorioException;
	
	/**
	 * Inserir e Atualizar Imovel 
	 *
	 * @author Raphael Rossiter
	 * @date 02/06/2010
	 *
	 * @param idImovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarClienteImovelUsuario(Integer idImovel) throws ErroRepositorioException;
	
	/**
 	 * @author R�mulo Aur�lio
	 * @date 23/06/2010
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer  pesquisarDebitoTipoImovelDoacao(			
			Integer idImovel) throws ErroRepositorioException;
	
	
	/**
 	 * @author Daniel Alves
	 * @date 20/07/2010
	 * @param idImovelPerfil
	 * @return ImovelPerfil
	 * @throws ErroRepositorioException
	 */
	public ImovelPerfil  pesquisarImovelPerfil(
			Integer idImovelPerfil) throws ErroRepositorioException;
	
	/**
	 * @author Daniel Alves
	 * @date 28/07/2010
	 * @param idImovel
	 * @return Colecao de imovelSubcategoriaHelper
	 * @throws ErroRepositorioException
	 */
	public Collection<ImovelSubcategoriaHelper> consultaSubcategorias(int idImovel)
		throws ErroRepositorioException;
	

	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 16/09/2010
	 * @param idQuadraAnterior
	 * @param idQuadraAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public FaturamentoGrupo[] verificaInscricaoAlteradaAcarretaMudancaDoGrupoFaturamento(Integer idQuadraAnterior, Integer idQuadraAtual)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 17/09/2010
	 * @param faturamentoGrupoOrigem
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean verificaGeracaoDadosLeituraGrupoFaturamento(FaturamentoGrupo faturamentoGrupo) 
		throws ErroRepositorioException;
	
	/**
	 * 	@author Hugo Leonardo
	 *  @date 21/09/2010
	 *  
	 * 	UC_0009 - Manter Cliente
	 *  	[FS0008] ? Verificar permiss�o especial para cliente de im�vel p�blico
	 *  
	 * 	Verifica se o Cliente possui algum im�vel, cujo o tipo da categoria 
	 *  em subcategoria seja igual a PUBLICO.
	 *  
	 * 	Caso o cliente possua algum im�vel, retornar a quantidade de imoveis nesta situa��o
	 * 	Caso contr�rio retorna zero. 
	 * 
	 *  @param idCliente
	 *  @return Integer
	 *  
	 *  @throws RepositorioException
	 */
	public Integer obterQuantidadeImoveisPublicosAssociadoAoCliente(Integer idCliente) 
		throws ErroRepositorioException;

	/**
	 * [UC0074] Alterar Inscri��o de Im�vel
	 * [FS0010] � Verificar Duplicidade de Inscri��o
	 * @author Arthur Carvalho
	 * @date 19/09/2010
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ImovelInscricaoAlterada verificarDuplicidadeImovelInscricaoAlterada(Integer idLocalidade, Integer idSetorComercial, Integer idQuadra,
			Integer idQuadraFace, Integer subLote, Integer lote) throws ErroRepositorioException;
	
	/**
	 * [UC0995] Emitir Declara��o Transfer�ncia de D�bitos/Cr�ditos
	 * @author Daniel Alves
	 * @date 23/09/2010
	 * @return Municipio
	 */
	public Municipio pesquisarMunicipioImovel(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0630] Solicitar Emiss�o do Extrato de D�bitos
	 * 
	 * [SB0001] � Calcular valor dos descontos pagamento � vista.
	 * 
	 * @author Vivianne Sousa
	 * @date 21/10/2010
	 * 
	 * @throws ControladorException
	 */
	public Short consultarNumeroReparcelamentoConsecutivosImovel(Integer idImovel)
		throws ErroRepositorioException;

	/**TODO: COSANPA
	 * autor: Adriana Muniz
	 * Data: 12/05/2011
	 * Pesquisa o id da quadra pelo id do im�vel - Gerar
	 * Arquivo da declara��o de quita��o anual de d�bito
	 */
	public Integer pesquisaIdQuadraImovel(Integer idImovel) throws ErroRepositorioException;
	
	
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
	public boolean verificaGeracaoDadosLeituraRota(FaturamentoGrupo faturamentoGrupo, Rota rota) throws ErroRepositorioException;
	
	/**TODO:COSANPA
	 * @author Adriana Muniz
	 * @date 06/12/2011
	 * 
	 * Pesquisa o perfil do imovel pelo id do im�vel
	 * 
	 * @param idImovelPerfil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ImovelPerfil  pesquisarImovelPerfilIdImovel(
			Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC1122] Automatizar Perfis de Grandes Consumidores
	 * 
	 * @author Mariana Victor
	 * @date 07/02/2011
	 * 
	 * @param anoMesFaturamentoSistemaParametro
	 * @param idSetorComercial
	 * @throws ControladorException
	 */
	public Collection<Imovel> consultarImovelLocalidade(Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC1122] Automatizar Perfis de Grandes Consumidores
	 * 
	 * @author Mariana Victor
	 * @date 07/02/2011
	 * 
	 * @param anoMesFaturamentoSistemaParametro
	 * @param idSetorComercial
	 * @throws ControladorException
	 */
	public void atualizarImovelPerfil(Integer idImovel, Integer idPerfil)
			throws ErroRepositorioException;
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection consultarImovel(Integer idLocalidade,Integer idGerenciaRegional,
			Integer idUnidadeNegocio)throws ErroRepositorioException;
	
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection consultarImovelCadastro(Integer idLocalidade,Integer idGerenciaRegional,
			Integer idUnidadeNegocio, Integer anoMesPesquisaInicial,Integer anoMesPesquisaFinal)throws ErroRepositorioException;
	

	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarImovelEconomia(Integer idImovel)throws ErroRepositorioException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public TarifaSocialDadoEconomia pesquisarTarifaSocialDadoEconomia(Integer idImovel) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 25/03/2011
	 * 
	 * @throws ControladorException
	 */
	public TarifaSocialCarta pesquisarTarifaSocialCarta(Integer idImovel,Integer codigoTipoCarta) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 29/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisarQuantidadeImoveisTarifaSocialCarta(Integer idTarifaSocialComandoCarta) 
		throws ErroRepositorioException;


	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 29/03/2011
	 * 
	 * @throws ControladorException
	 */
	public void atualizarTarifaSocialComandoCarta(Integer idTarifaSocialComandoCarta, Integer qtdeImoveis)
			throws ErroRepositorioException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 29/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarTarifaSocialCarta(Integer idTarifaSocialComandoCarta) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 29/03/2011
	 * 
	 * @throws ControladorException
	 */
	public BigDecimal pesquisarValorContaTarifaSocialCartaDebito(Integer idTarifaSocialComandoCarta,Integer idImovel) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Im�vel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 31/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarTarifaSocialComandoCarta(Integer codigoTipoCarta, String situacao,Integer numeroPagina) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Im�vel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 31/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisarQtdeTarifaSocialComandoCarta(Integer codigoTipoCarta, String situacao) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Im�vel da Tarifa Social
	 * [SB0003] Excluir Comando Selecionado
	 *  
	 * @author Vivianne Sousa
	 * @date 31/03/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void removerComando(Integer idTarifaSocialComandoCarta)
		throws ErroRepositorioException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 01/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarImoveisTarifaSocialCarta(Integer idTarifaSocialComandoCarta,Integer codigoTipoCarta) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Im�vel da Tarifa Social
	 * [SB0003] Excluir Comando Selecionado 
	 * 
	 * @author Vivianne Sousa
	 * @date 01/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarContasTarifaSocialCartaDebito(Integer idTarifaSocialComandoCarta,Integer idImovel) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * [SB0004]�Retirar Im�vel tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 04/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void retirarImovelTarifaSocial(Integer motivoExclusao, Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 04/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void atualizarDataExecucaoTarifaSocialComandoCarta(Integer idTarifaSocialComandoCarta)
			throws ErroRepositorioException ;
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Im�vel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 01/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarImoveisTarifaSocial(Integer idLocalidade) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Im�vel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 05/04/2011
	 * 
	 * @throws ControladorException
	 */
	public TarifaSocialComandoCarta pesquisarTarifaSocialComandoCarta(Integer idTarifaSocialComandoCarta) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC1164] Gerar Resumo dos Im�veis Exclu�dos da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 07/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarQtdeImoveisExcluidostarifaSocial(
			Integer codigoTipoCarta,Integer idGerencia,Integer idUnidade,Integer idLocalidade, 
			Integer referenciaInicial, Integer refereciaFinal)throws ErroRepositorioException;
	
	/**
	 * [UC1164] Gerar Resumo dos Im�veis Exclu�dos da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 07/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisarQtdeImoveisExcluidostarifaSocial(Integer referenciaInicial, Integer refereciaFinal,
			Integer codigoTipoCarta,RelatorioResumoQtdeImoveisExcluidosTarifaSocialHelper helper) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1164] Gerar Resumo dos Im�veis Exclu�dos da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 08/04/2011
	 * 
	 * @throws ControladorException
	 */
	public String pesquisarDescricaoMotivoCarta(Integer idCodigoMotivo) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 08/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarQtdeTarifaSocialDadoEconomia(Integer idtarifaSocialExclusaoMotivo,
			Integer referenciaInicial, Integer refereciaFinal,Integer idGerencia,Integer idUnidade,Integer idLocalidade) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC1161] Retirar Im�vel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 11/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void atualizarDadosFaturamentoSituacaoHistorico(Integer idImovel,
			Integer referenciaFaturamentoGrupo,String observacaoRetira)
			throws ErroRepositorioException;
	
	/**
	 * [UC1161] Retirar Im�vel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 11/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void atualizarDadosImovel(Integer idImovel, Integer referencia)
			throws ErroRepositorioException;
	
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
			Integer idLocalidade, Integer tipoCarta)throws ErroRepositorioException;
	
	/**
	 * [UC0352] Emitir Contas e Cartas
	 * [SB0017] � Obter Mensagem da Conta em 3 Partes
	 * 
	 * @author Vivianne Sousa
	 * @date 29/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisarAnoMesExclusaoTarifaSocialImovel(Integer idImovel) 
		throws ErroRepositorioException;

	/**
	 *  [UC1168] Encerrar Comandos de Cobran�a por Empresa
	 *
	 * @author Mariana Victor
	 * @created 10/05/2011
	 */
	public void retirarSituacaoCobrancaImovel(Integer idImovel, Integer idCobrancaSituacao)
		throws ErroRepositorioException;

	/**
	 * [UC1169] Movimentar Ordens de Servi�o de Cobran�a por Resultado
	 * 
	 * Emitir OS de Empresa de Cobran�a - 
	 * 
	 * @author Mariana Victor
	 * @data 18/05/2011
	 */
	public Collection<Integer[]> pesquisarIdsImoveis(String[] idsOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC1174] Gerar Relat�rio Im�veis com Doa��es
	 * 
	 * Quantidade de imoveis com doa��es - 
	 * 
	 * @author Erivan Sousa	
	 * @data 13/06/2011
	 */
	public Integer pesquisarQuantidadeImoveisDoacoes(GerarRelatorioImoveisDoacoesHelper filtro)throws ErroRepositorioException;
	
	/**
	 * [UC1174] Gerar Relat�rio Im�veis com Doa��es
	 * 
	 * Pesquisar Imoveis com Doa��es - 
	 * 
	 * @author Erivan Sousa	
	 * @data 13/06/2011
	 */
	public Collection pesquisarImoveisDoacoes(GerarRelatorioImoveisDoacoesHelper filtro)throws ErroRepositorioException;
	
	/**
	 * [UC0000] Obter Consumo N�o Medido por Par�metro
	 * [FS0001] - Verificar "Pontos de Utiliza��o" N�o Informado.
	 * 
	 * @author Mariana Victor
	 * @date 23/05/2011
	 * 
	 * @throws ControladorException
	 */

	public Short pesquisarPontosUtilizacaoImovel(Integer idImovel) 
		throws ErroRepositorioException;

	/**
	 * [UC0000] Obter Consumo N�o Medido por Par�metro
	 * [FS0002] - Verificar �N�mero de Moradores� N�o Informado.
	 * 
	 * @author Mariana Victor
	 * @date 23/05/2011
	 * 
	 * @throws ControladorException
	 */

	public Short pesquisarNumeroMoradoresImovel(Integer idImovel) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * @author Vivianne Sousa
	 * @date 09/08/2011
	 */
	public void atualizarSituacaoEspecialFaturamentoImovel(Integer idImovel,
			Integer idFaturamentoSituacaoTipo, Integer idFaturamentoSituacaoMotivo)	throws ErroRepositorioException;
	
	/**
	 * M�todo que retorna o id do im�vel �rea comum
	 * 
	 * [UC0098] Manter V�nculos de Im�veis para Rateio de Consumo
	 * [SB0001] Atualizar Tipo de Rateio
	 * 
	 * @author Magno Gouveia
	 * @since 17/08/2011
	 * 
	 * @param idImovelCondomio
	 * @return imovel.id
	 */
	public Integer pesquisarImovelAreaComum(Integer idImovelCondominio) throws ErroRepositorioException;

	/**
	 * <p>
	 * [UC0098] Manter V�nculos de Im�veis para Rateio Comum
	 * </p>
	 * <p>
	 * [SB0001] - [FS0012] - Caso a matr�cula do im�vel para �rea comum
	 * informada n�o exista na tabela IMOVEL, exibir a mensagem "Matr�cula
	 * inexistente no cadastro" e retornar para o passo correspondente no fluxo
	 * principal
	 * </p>
	 * 
	 * @author Magno Gouveia
	 * @since 19/08/2011
	 * @param idImovel
	 * @return
	 */
	public Short verificarExistenciaDoImovel(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * M�todo que retorna o id do im�vel condom�nio
	 * 
	 * [UC0098] Manter V�nculos de Im�veis para Rateio de Consumo
	 * [SB0001] Atualizar Tipo de Rateio
	 * 
	 * @author Magno Gouveia
	 * @since 17/08/2011
	 * 
	 * @param idImovel
	 * @return imovel.id
	 */
	public Integer pesquisarImovelCondominio(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * M�todo que retorna o id do im�vel condominio
	 * 
	 * [UC0098] Manter V�nculos de Im�veis para Rateio de Consumo
	 * [SB0001] Atualizar Tipo de Rateio
	 * 
	 * @author Magno Gouveia
	 * @since 19/08/2011
	 * 
	 * @param idImovel, indicadorImovelAreaComum
	 */
	public void atualizarIndicadorImovelAreaComumDoImovel(Integer idImovel, Short indicadorImovelAreaComum)	throws ErroRepositorioException;
	
	/**
	 * [UC0457] - Encerrar Ordem de Servi�o.
	 * [SB0009] - Verificar Situa��o Especial de Faturamento.
	 * 
	 * Verifica se um im�vel est� em situa��o especial de faturamento
	 * para um dado imovel (idImovel). 
	 * A situa��o especial de faturamento tem o ftst_id = 2
	 * 
	 * @param idImovel
	 * @return Imovel
	 * @throws ErroRepositorioException
	 */
	public Imovel pesquisarImovelSituacaoEspecialFaturamento(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * TODO: COSANPA
	 * 
	 * Mantis 494
	 * 
	 * @param idImovel
	 * 
	 * @author Wellington Rocha
     * @date 21/03/2012
	 * @exception ErroRepositorioException
	 */
	public Collection<ImovelRamoAtividade> pesquisarRamoAtividadeDoImovel(Integer idImovel) throws ErroRepositorioException;
	
	public ImovelControleAtualizacaoCadastral pesquisarImovelControleAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException;
	
	public Collection<Integer> pesquisarIdImoveisAprovados() throws ErroRepositorioException;
	
	public LogradouroTipo pesquisarTipoLogradouro(Integer idTipoLogradouro) throws ErroRepositorioException;
	
	public Logradouro pesquisarLogradouro(Integer codigoLogradouro) throws ErroRepositorioException;
	
	public Integer pesquisarLogradouroImovelAtualizacaoCadastral(Integer matriculaImovel) throws ErroRepositorioException;
	
	public ImovelCobrancaSituacao obterImovelCobrancaSituacao(Integer idImovelSituacaoCobranca) throws ErroRepositorioException;
}
