package gcom.micromedicao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FaturamentoSituacaoHistorico;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.MotivoInterferenciaTipo;
import gcom.faturamento.consumotarifa.ConsumoTarifaVigencia;
import gcom.gui.micromedicao.ColetaMedidorEnergiaHelper;
import gcom.gui.micromedicao.DadosMovimentacao;
import gcom.gui.relatorio.micromedicao.FiltroRelatorioLeituraConsultarArquivosTextoHelper;
import gcom.gui.relatorio.micromedicao.RelatorioNotificacaoDebitosImpressaoSimultaneaHelper;
import gcom.micromedicao.bean.ConsumoHistoricoCondominio;
import gcom.micromedicao.bean.FiltrarLeiturasTelemetriaHelper;
import gcom.micromedicao.bean.ImovelPorRotaHelper;
import gcom.micromedicao.bean.LigacaoMedicaoIndividualizadaHelper;
import gcom.micromedicao.bean.PesquisarRelatorioRotasOnlinePorEmpresaHelper;
import gcom.micromedicao.consumo.ConsumoAnormalidadeAcao;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraFaixaFalsa;
import gcom.micromedicao.medicao.FiltroMedicaoHistoricoSql;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.relatorio.cadastro.micromedicao.FiltrarRelatorioImoveisComLeiturasHelper;
import gcom.relatorio.cadastro.micromedicao.RelatorioResumoLigacoesCapacidadeHidrometroHelper;
import gcom.relatorio.micromedicao.FiltrarAnaliseExcecoesLeiturasHelper;
import gcom.relatorio.micromedicao.FiltrarRelatorioAnormalidadeLeituraPeriodoHelper;
import gcom.relatorio.micromedicao.FiltrarRelatorioBoletimMedicaoHelper;
import gcom.relatorio.micromedicao.GerarDadosLeituraHelper;
import gcom.relatorio.micromedicao.RelatorioAcompanhamentoLeituristaHelper;
import gcom.relatorio.micromedicao.RelatorioRotasOnlinePorEmpresaBean;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;



/**
 * < <Descrição da Interface>>
 * 
 * @author Administrador
 */
public interface IRepositorioMicromedicao {
	public Collection pesquisarNumeroHidrometroFaixa(String faixaInicial, String faixaFinal) throws ErroRepositorioException;

	public Collection pesquisarNumeroHidrometroFaixaRelatorio(String faixaInicial, String faixaFinal) throws ErroRepositorioException;

	public Collection pesquisarNumeroHidrometroFaixaPaginacao(String faixaInicial, String faixaFinal, Integer numeroPagina)	throws ErroRepositorioException;

	public Collection pesquisarNumeroHidrometroFaixaComLimite(String faixaInicial, String faixaFinal) throws ErroRepositorioException;

	public Collection pesquisarConsumoMedidoHidrometroPeriodoInformadoLigacaoAgua(
			Imovel imovel, int anoMesReferencia,
			LigacaoTipo ligacaoTipo) throws ErroRepositorioException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param anoMesReferencia
	 *            Descrição do parâmetro
	 * @param periodoInformado
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisarConsumoMedidoHidrometroPeriodoInformadoPoco(
			Imovel imovel, int anoMesReferencia)
			throws ErroRepositorioException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param anoMesReferencia
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisarObterConsumoMedioImovel(Imovel imovel,
			int anoMesReferencia) throws ErroRepositorioException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param faturamentoGrupo
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisarImovelFaturamentoGrupoObterIds(
			FaturamentoGrupo faturamentoGrupo) throws ErroRepositorioException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param faturamentoGrupo
	 *            Descrição do parâmetro
	 * @param imovel
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisarImovelTesteMedicaoConsumoLigacaoAgua(
			FaturamentoGrupo faturamentoGrupo, Imovel imovel)
			throws ErroRepositorioException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param faturamentoGrupo
	 *            Descrição do parâmetro
	 * @param imovel
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisarImovelTesteMedicaoConsumoPoco(
			FaturamentoGrupo faturamentoGrupo, Imovel imovel)
			throws ErroRepositorioException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param faturamentoGrupo
	 *            Descrição do parâmetro
	 * @param imovel
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisarImovelTesteMedicaoConsumoLigacaoEsgoto(
			FaturamentoGrupo faturamentoGrupo, Imovel imovel)
			throws ErroRepositorioException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisarImovelLigacaoSituacao(Imovel imovel)
			throws ErroRepositorioException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param objeto
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Object inserirBat(Object objeto) throws ErroRepositorioException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param rotas
	 *            Descrição do parâmetro
	 * @param anoMesReferencia
	 *            Descrição do parâmetro
	 * @param ligacaoAguaSituacaoLigado
	 *            Descrição do parâmetro
	 * @param ligacaoAguaSituacaoCortado
	 *            Descrição do parâmetro
	 * @param ligacaoEsgotoLigado
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisarImoveisLigadosCortadosAguaLigadosEsgoto(
			Rota rota, int anoMesReferencia, Integer ligacaoAguaSituacaoLigado,
			Integer ligacaoAguaSituacaoCortado, Integer ligacaoEsgotoLigado)
			throws ErroRepositorioException;
	
	/**
	 *
	 * [UC0101] - Consistir Leituras e Calcular Consumos
	 *
	 * Método Especifico Para CAERN
	 *
	 * @author Raphael Rossiter, Flávio Cordeiro
	 * @date 20/03/2007
	 *
	 * @param rota
	 * @param anoMesReferencia
	 * @param ligacaoAguaSituacaoLigado
	 * @param ligacaoEsgotoLigado
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImoveisLigadosAguaLigadosEsgoto(
			Rota rota, int anoMesReferencia, Integer ligacaoAguaSituacaoLigado, Integer ligacaoEsgotoLigado)
			throws ErroRepositorioException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param medicaoTipo
	 *            Descrição do parâmetro
	 * @param sistemaParametro
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection<MedicaoHistorico> pesquisarObterDadosHistoricoMedicao(Imovel imovel,
			MedicaoTipo medicaoTipo, FaturamentoGrupo faturamentoGrupo)
			throws ErroRepositorioException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param ligacaoTipo
	 *            Descrição do parâmetro
	 * @param anoMesReferencia
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */

	public Collection pesquisarConsumoHistoricoConsumoAnormalidade(
			Imovel imovel, LigacaoTipo ligacaoTipo, int anoMesReferencia)
			throws ErroRepositorioException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param ligacaoTipo
	 *            Descrição do parâmetro
	 * @param anoMesReferencia
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisarConsumoHistorico(Imovel imovel,
			LigacaoTipo ligacaoTipo, int anoMesReferencia)
			throws ErroRepositorioException;

	public Collection pesquisarAnormalidadeLeitura(
			LeituraAnormalidade leituraAnormalidadeFaturamento)
			throws ErroRepositorioException;

	public Collection pesquisarFaturamentoSituacaoTipo(
			FaturamentoSituacaoTipo faturamentoSituacaoTipo)
			throws ErroRepositorioException;

	public Collection pesquisarMaiorDataVigenciaConsumoTarifaImovel(
			Date dataCorrente, Imovel imovel) throws ErroRepositorioException;

	public Object pesquisarConsumoMinimoTarifaCategoriaVigencia(
			Categoria categoria, ConsumoTarifaVigencia consumoTarifaVigencia)
			throws ErroRepositorioException;

	public void inseriOuAtualizaMedicaoHistorico(
			Collection colecaoMedicaoHistorico) throws ErroRepositorioException;

	public void inseriOuAtualizaConsumoHistorico(
			Collection colecaoConsumoHistorico) throws ErroRepositorioException;

	public Collection pesquisarImoveisPorRota(Collection colecaoRota, Integer idLeituraTipo,int inicioPesquisa)
			throws ErroRepositorioException;

	public Collection pesquisarDadosHidrometroTipoLigacaoAgua(Integer idImovel)
			throws ErroRepositorioException;

	public Collection pesquisarDadosHidrometroTipoPoco(Integer idImovel)
			throws ErroRepositorioException;

	public Collection pesquisarLeituraAnteriorTipoLigacaoAgua(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException;

	public Collection pesquisarLeituraAnteriorTipoPoco(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException;

	public Collection pesquisarIdConsumoTarifaCategoria(Integer idImovel)
			throws ErroRepositorioException;

	public Integer pesquisarMaiorDataVigencia(Integer idConsumoTarifa)
			throws ErroRepositorioException;

	public Integer pesquisarConsumoMinimoTarifaCategoria(
			Integer idConsumoTarifaVigencia, Integer idCategoria)
			throws ErroRepositorioException;

	public Integer verificarExistenciaHidrometroInstalacaoHistoricoTipoAgua(
			Integer idImovel) throws ErroRepositorioException;

	public Integer verificarExistenciaHidrometroInstalacaoHistoricoTipoPoco(
			Integer idImovel) throws ErroRepositorioException;

	public Integer verificarExistenciaMedicaoTipo(Integer idMedicaoTipo)
			throws ErroRepositorioException;

	public Object[] pesquisarLeituraAnormalidade(Integer idLeituraAnormalidade)
			throws ErroRepositorioException;

	public Object[] pesquisarMedicaoHistoricoAnterior(Integer idImovel,
			Integer anoMes, Integer idMedicaoTipo)
			throws ErroRepositorioException;

	public Object[] pesquisarMedicaoHistoricoAnteriorTipoPoco(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException;

	public MedicaoHistorico pesquisarMedicaoHistoricoTipoAgua(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException;

	public MedicaoHistorico pesquisarMedicaoHistoricoTipoPoco(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException;

	public void inserirAtualizarMedicaoHistorico(
			Collection medicoesHistoricosParaRegistrar)
			throws ErroRepositorioException;

	public Date pesquisarDataInstalacaoHidrometro(
			Integer idHidrometroInstalacaoHistorico)
			throws ErroRepositorioException;

	/**
	 * Consultar Dados do Cliente Imovel Vinculado Auhtor: Rafael Santos Data:
	 * 23/01/2006
	 * 
	 * @param inscricaoImovel
	 *            Inscrição do Imovel
	 * @return Dados do Imovel Vinculado
	 * @throws ControladorException
	 */
	public Object[] consultarDadosClienteImovelUsuario(Imovel imovel)
			throws ErroRepositorioException;

	/**
	 * Consultar Matriculas dos Imoveis Vinculados do Imovel condominio Auhtor:
	 * Rafael Santos Data: 23/01/2006 [UC0179] Consultar Historico Medição
	 * Indiviualizada
	 * 
	 * @param consumoHistorico
	 *            Consumo Historico
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> consultarConsumoHistoricoImoveisVinculados(ConsumoHistoricoCondominio consumoHistorico) throws ErroRepositorioException;

	public Object[] consultarDadosConsumoTipoConsumoHistorico(ConsumoHistorico consumoHistorico) throws ErroRepositorioException;

	public ConsumoHistorico obterConsumoHistoricoMedicaoIndividualizada(Imovel imovel,
			LigacaoTipo ligacaoTipo, int anoMesReferencia)
			throws ErroRepositorioException;

	public Object pesquisarObterDadosMaiorHistoricoMedicao(Imovel imovel,
			MedicaoTipo medicaoTipo, SistemaParametro sistemaParametro)
			throws ErroRepositorioException;

	/**
	 * Consultar Consumo Historico da Medicao Individualizada [UC0113] Faturar
	 * Grupo Faturamento Auhtor: Rafael Santos Data: 20/02/2006
	 * 
	 * @param idImovel
	 * @param idAnormalidade
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterConsumoHistoricoAnormalidade(Integer idImovel,
			Integer idAnormalidade, int anoMes) throws ErroRepositorioException;

	/**
	 * Consultar Imoveis com Medição Indiviualizada Auhtor: Sávio Luiz Data:
	 * 06/02/2006 [UC0180] Consultar Imoveis com Medição Indiviualizada
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String pesquisarDescricaoRateioTipoLigacaoAgua(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Consultar Imoveis com Medição Indiviualizada Auhtor: Sávio Luiz Data:
	 * 06/02/2006 [UC0180] Consultar Imoveis com Medição Indiviualizada
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String pesquisarDescricaoRateioTipoLigacaoEsgoto(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0121] - Filtrar Exceções de Leituras e Consumos 
	 *
	 * @author Flávio Leonardo, Raphael Rossiter
	 * @date 00/00/0000, 06/10/2009
	 *
	 * @param filtroMedicaoHistoricoSql
	 * @param faturamentoGrupo
	 * @param numeroPagina
	 * @param todosRegistros
	 * @param anoMes
	 * @param pesquisarPorRotaAlternativa
	 * @return Integer
	 * @throws ControladorException
	 */
	public Collection pesquisarImovelExcecoesLeituras(
			FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql,
			FaturamentoGrupo faturamentoGrupo, Integer numeroPagina,
			boolean todosRegistros, String anoMes, String valorAguaEsgotoInicial, 
			String valorAguaEsgotoFinal, boolean pesquisarPorRotaAlternativa) 
	throws ErroRepositorioException;

	/**
	 * [UC0121] - Filtrar Exceções de Leituras e Consumos 
	 *
	 * @author Flávio Leonardo, Raphael Rossiter
	 * @date 00/00/0000, 06/10/2009
	 *
	 * @param filtroMedicaoHistoricoSql
	 * @param faturamentoGrupo
	 * @param mesAnoPesquisa
	 * @param pesquisarPorRotaAlternativa
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarImovelExcecoesLeiturasCount(
			FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql,
			FaturamentoGrupo faturamentoGrupo,String anoMes, String valorAguaEsgotoInicial, 
			String valorAguaEsgotoFinal, boolean pesquisarPorRotaAlternativa) 
		throws ErroRepositorioException;

	/**
	 * 
	 * Método que retorna os imoveis condominiais e esteja com ligados ou
	 * cortados a aqua e ou ligados com esgoto que possuam hidrometro no poço
	 * das rotas passadas
	 * 
	 * [UC0103] Efetuar Rateio de Consumo
	 * 
	 * @author Thiago Toscano
	 * @date 07/04/2006
	 * 
	 * @param idRota
	 * @return Imoveis
	 */
	public Collection pesquisarImovelCondominioParaCalculoDoRateioPorRota(
			Integer idRota) throws ErroRepositorioException;

	/**
	 * Método que retorna todos os imoveis veinculados a um imovel condominio
	 * 
	 * [UC0103] Efetuar Rateio de Consumo
	 * 
	 * @author Thiago Toscano
	 * @date 07/04/2006
	 * 
	 * @param rotas
	 * @return Imoveis
	 */
	public Collection<Imovel> getImovelVinculadosImovelCondominio(Integer id)
			throws ErroRepositorioException;

	/**
	 * 
	 * Método que retorna o consumo de ligacao da agua ou esgoto (tipo passado)
	 * de um imovel em um determinado anoMes do faturamento. Método utilizado
	 * pra saber a ligacao de
	 * 
	 * 2.2.2.2 e 2.2.3.2 do [UC0103] Efetuar Rateio de Consumo
	 * 
	 * @author thiago toscano
	 * @date 07/04/2006
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @param idLigacaoTipo
	 *            podendo ser agua ou esgoto
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object obterConsumoLigacaoAguaOuEsgotoDoImovel(Integer idImovel,
			Integer anoMes, Integer idLigacaoTipo)
			throws ErroRepositorioException;

	/**
	 * Método que retorna um consumoHistorico do imovel com o anoMes passado
	 * 
	 * @author thiago toscano
	 * @date 18/04/2006
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ConsumoHistorico obterConsumoHistoricoImovel(Integer idImovel,
			Integer anoMes,Integer idLigacaoTipo) throws ErroRepositorioException;

	/**
	 * Método que retorna o id da leitura anormalidade do faturamento no caso do
	 * tipo de ligação ser agua
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0003] Obter Dados do Consumo e Medicao Anterior
	 * 
	 * @author Sávio Luiz
	 * @date 17/05/2006
	 * 
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdLeituraAnormalidadeTipoAgua(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException;

	/**
	 * Método que retorna o id da leitura anormalidade do faturamento no caso do
	 * tipo de ligação ser esgoto
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0003] Obter Dados do Consumo e Medicao Anterior
	 * 
	 * @author Sávio Luiz
	 * @date 17/05/2006
	 * 
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdLeituraAnormalidadeTipoEsgoto(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException;

	/**
	 * Método que retorna um arrey de Object com informações do histórico de
	 * medição com tipo de medição agua
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0004] Obter Dados de medição da conta
	 * 
	 * @author Sávio Luiz
	 * @date 17/05/2006
	 * 
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Object[] obterDadosMedicaoContaTipoAgua(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException;

	/**
	 * Método que retorna um arrey de Object com informações do histórico de
	 * medição com tipo de medição poco
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0004] Obter Dados de medição da conta
	 * 
	 * @author Sávio Luiz
	 * @date 17/05/2006
	 * 
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterDadosMedicaoContaTipoPoco(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException;

	/**
	 * Método que retorna um arrey de Object com informações do histórico de
	 * consumo com tipo de medição poco
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0006] Obter Dados de consumo da conta
	 * 
	 * @author Sávio Luiz
	 * @date 19/05/2006
	 * 
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @param idTipoLigacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterDadosConsumoConta(Integer idImovel,
			int anoMesReferencia, Integer idTipoLigacao)
			throws ErroRepositorioException;

	/**
	 * 
	 * Método que retorna o consumo de ligacao da agua ou esgoto (tipo passado)
	 * de um imovel em um determinado anoMes do faturamento. Método utilizado
	 * pra saber a ligacao de
	 * 
	 * 2.2.2.2 e 2.2.3.2 do [UC0103] Efetuar Rateio de Consumo
	 * 
	 * @author Sávio Luiz
	 * @date 07/04/2006
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @param idLigacaoTipo
	 *            podendo ser agua ou esgoto
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
			Integer idImovel, Integer anoMes, Integer idLigacaoTipo)
			throws ErroRepositorioException;

	/**
	 * 
	 * @param faixaInicial
	 *            Descrição do parâmetro
	 * @param faixaFinal
	 *            Descrição do parâmetro
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */

	public Integer pesquisarNumeroHidrometroFaixaCount(String Fixo,
			String faixaInicial, String faixaFinal)
			throws ErroRepositorioException;

	/**
	 * Método que retorna o maior código de Rota de um Setor Comercial
	 * 
	 * @author Vivianne Sousa
	 * @date 12/07/2006
	 * 
	 * @param idSetorComercial
	 * @return
	 * @throws ControladorException
	 */

	public Short pesquisarMaximoCodigoRota(Integer idSetorComercial)
			throws ErroRepositorioException;

	/**
	 * Método que remove RotaAcaoCriterio
	 * 
	 * @author Vivianne Sousa
	 * @date 12/07/2006
	 * 
	 * @param id
	 * @param operacaoEfetuada
	 * @param acaoUsuarioHelper
	 * @return
	 * @throws ControladorException
	 */
	public void removerRotaAcaoCriterio(int id,
			OperacaoEfetuada operacaoEfetuada,
			Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper)
			throws ErroRepositorioException;

	/**
	 * 
	 * Método que apresenta os dados do imovel
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 * 
	 * @author Sávio Luiz
	 * @date 04/08/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 */
	public Collection pesquiarImovelExcecoesApresentaDados(Integer idImovel,
			boolean ligacaoAgua) throws ErroRepositorioException;

	/**
	 * 
	 * Método que apresenta os dados do imovel
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 * 
	 * @author Sávio Luiz
	 * @date 04/08/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 */
	public Collection pesquiarMedicaoConsumoHistoricoExcecoesApresentaDados(
			FaturamentoGrupo faturamentoGrupo, Integer idImovel,
			boolean ligacaoAgua) throws ErroRepositorioException;

	/**
	 * 
	 * Retorna uma coleção com os dados das medicoes para apresentação
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 */
	public Collection carregarDadosMedicao(Integer idImovel, boolean ligacaoAgua)
			throws ErroRepositorioException;

	/**
	 * 
	 * Retorna uma coleção com os dados das medicoes para apresentação
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 */
	public Collection carregarDadosMedicaoResumo(Integer idImovel,
			boolean ligacaoAgua) throws ErroRepositorioException;

	/**
	 * 
	 * Retorna uma coleção com os dados dos Consumos para apresentação
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 */
	public Collection carregarDadosConsumo(Integer idImovel, int anoMes,
			boolean ligacaoAgua) throws ErroRepositorioException;

	/**
	 * 
	 * Método utilizado para pesquisar os consumo historicos a serem
	 * substituidos pelo caso de uso [UC0106] Substituir Consumos Anteriores
	 * 
	 */
	public Collection pesquisaConsumoHistoricoSubstituirConsumo(
			Integer idImovel, SistemaParametro sistemaParametro, Integer anoMesFaturamento)
			throws ErroRepositorioException;

	/**
	 * Atualiza o valor de cshi_nnconsumoFaturadomes consumo historico [UC0106] -
	 * Substituir Consumos Anteriores
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarConsumosAnteriores(ConsumoHistorico consumoHistorico)
			throws ErroRepositorioException;

	/**
	 * 
	 * Método que apresenta os dados do imovel
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 * 
	 * @author Flávio Cordeiro
	 * @date 04/08/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 */
	public Collection pesquiarImovelExcecoesApresentaDadosResumido(
			Integer idImovel, boolean ligacaoAgua)
			throws ErroRepositorioException;

	/**
	 * 
	 * Retorna um objeto com os dados das medicoes para apresentação
	 * 
	 * Flávio
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 */
	public Object[] carregarDadosMedicaoResumido(Integer idImovel,
			boolean ligacaoAgua, String anoMes) throws ErroRepositorioException;

	/**
	 * Atualizar Analise excecoes consumo resumido
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarLeituraConsumoResumido(Integer idImovel,
			String mesAno, String dataLeituraAnteriorFaturamento,
			String leituraAnteriorFaturamento,
			String dataLeituraAtualInformada, String leituraAtualInformada,
			String consumo, boolean ligacaoAgua, Integer idAnormalidade,
			Integer idleituraSituacaoAtual, Usuario usuarioLogado, boolean alterouAnormalidade, MotivoInterferenciaTipo motivoInterferenciaTipo)
			throws ErroRepositorioException;
	
	/**
	 * Atualizar Analise excecoes consumo resumido
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorAnalisadoMedicaoHistorico(Integer idMedicaoHistorico, Usuario usuarioLogado) throws ErroRepositorioException;

	/**
	 * Registrar leituras e anormalidades Autor:Sávio Luiz
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarMedicaoHistorico(Collection idsImovel,
			Integer anoMes, Integer idMedicaoTipo)
			throws ErroRepositorioException;

	/**
	 * [UC0082] - Registrar Leituras e Anormalidades Autor: Sávio Luiz Data:
	 * 04/01/2006
	 */

	public Collection pesquisarMedicaoHistoricoAnterior(
			Collection colecaoIdsImoveis, Integer anoMes, Integer idMedicaoTipo)
			throws ErroRepositorioException;

	public void inseriMedicaoHistorico(Collection colecaoMedicaoHistorico)
			throws ErroRepositorioException;

	// ----------Savio
	public void atualizarMedicaoHistorico(Collection medicaoHistoricoAtualizar)
			throws ErroRepositorioException;

	/**
	 * Registrar leituras e anormalidades Autor:Sávio Luiz
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarHidrometroInstalacaoHistorico(
			Collection idsImovel) throws ErroRepositorioException;

	/**
	 * Método que retorna o número da leitura de retirada do hidrômetro
	 * 
	 * @author Ana Maria
	 * @date 25/09/2006
	 * 
	 * @param idLigacaoAgua
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarNumeroLeituraRetiradaLigacaoAgua(
			Integer idLigacaoAgua) throws ErroRepositorioException;

	/**
	 * Método que retorna o número da leitura de retirada do hidrômetro
	 * 
	 * @author Ana Maria
	 * @date 25/09/2006
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarNumeroLeituraRetiradaImovel(Integer idImovel)
			throws ErroRepositorioException;

	
	public void atualizarFaturamentoAtividadeCronograma(Integer idGrupoFaturemanto,int amReferencia)
	throws ErroRepositorioException;
	
	/**
	 * Método que retorna o consumo médio do imóvel
	 * 
	 * @author Ana Maria
	 * @date 17/10/2006
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarConsumoMedioImovel(Integer idImovel)
			throws ErroRepositorioException;
	
	
	public void deletarConsumoHistorico(Integer idRota, int amFaturamento) throws ErroRepositorioException;
	
	
	public void deletarConsumoHistoricoCondominio(Integer idRota, int amFaturamento) throws ErroRepositorioException;
	
	
	//RETIRAR APOS OS TESTE DE NATAL
	public void deletarConsumoHistoricoRETIRAR(Integer idImovel, int amFaturamento) throws ErroRepositorioException;
	
	
	public void deletarConsumoHistoricoCondominioRETIRAR(Integer idImovel, int amFaturamento) throws ErroRepositorioException;
	
	
	
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
	public Integer pesquisarConsumoMedio(Integer idImovel,Integer tipoMedicao)
			throws ErroRepositorioException;
	
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

	public Collection pesquisarConsumoFaturadoQuantidadeMeses(Integer idImovel,Integer tipoMedicao,short qtdMeses)
			throws ErroRepositorioException; 

	/**
	 * Atualizar Hidrômetro
	 * 
	 * Pesquisa o imóvel no qual o hidrômetro está instalado
	 * 
	 * @author Rafael Corrêa
	 * @date 23/11/2006
	 * 
	 * @param idHidrometro
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarImovelHidrometroInstalado(Integer idHidrometro)
	throws ErroRepositorioException;
	
	/**
	 * [UC0498] - Efetuar Ligação de Água com Instalaação de Hidrômetro
	 * 
	 * Pesquisa o id do hidrômetro e a sua situação pelo número
	 * 
	 * @author Rafael Corrêa
	 * @date 29/11/2006
	 * 
	 * @param numeroHidrometro
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarHidrometroPeloNumero(String numeroHidrometro)
			throws ErroRepositorioException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 06/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */

	public Date pesquisarDataInstalacaoHidrometroAgua(
			Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 06/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */

	public Date pesquisarDataInstalacaoHidrometroPoco(
			Integer idImovel)
			throws ErroRepositorioException;
	
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
	public Integer pesquisarConsumoFaturaMes(int anoMesReferencia,
			Integer idMedicaoTipo,Integer idImovel) throws ErroRepositorioException;
	
	
	/**
	 * Analise Excecoes Leitura e Consumos
	 * 
	 * 
	 * @author Flávio Leonardo
	 * @date 14/11/2006
	 * 
	 * @param idImovel, SistemaParametros
	 * @return count id
	 * @throws ErroRepositorioException
	 */
	public Collection pesqusiarLigacoesMedicaoIndividualizada(Integer idImovel, 
			String anoMes) throws ErroRepositorioException;
	
	
	
	
	/**
	 * Obtém os ids de todas as rotas cadastradas
	 * 
	 * [UC0251] - Gerar Atividade de Ação de Cobrança 
	 *
	 * [SB0002] - Gerar Atividade de Ação de Cobrança para os Imóveis da Lista de Rotas
	 *
	 * @author Leonardo Vieira
	 * @date 13/12/2006
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarListaRotas() throws ErroRepositorioException;
	
	/**
     * [] Ligacoes Medicao Individualizada
     * 
     * 
     * @author Flávio Cordeiro
     * @date 17/12/2006
     * 
     * @param colecaoLigacoesMedicao
     * @throws ControladorException
     */
    public void atualizarLigacoesMedicaoIndividualizada(LigacaoMedicaoIndividualizadaHelper ligacaoMedicaoIndividualizadaHelper,
    		Integer anoMes) throws ErroRepositorioException;
    
    /**
	 * [UC0113] - Faturar Grupo de Faturamento
	 * 
	 * Obter empresa da rota.
	 * 
	 * [SB0006 - ]
	 * 
	 * @author Raphael Rossiter
	 * @date 09/01/2007
	 * 
	 * @param rota
	 * @throws ErroRepositorioException
	 */
	public Integer obterIdEmpresaPorRota(Rota rota) throws ErroRepositorioException ;
	
	
	/**
	 * < <Descrição do método>>
	 * 
	 * @param imovel 			Descrição do parâmetro
	 * @param anoMesReferencia	Descrição do parâmetro
	 * @param periodoInformado 	Descrição do parâmetro
	 * @param ligacaoTipo		Descrição do parâmetro
	 * 
	 * @return Descrição do retorno
	 * 
	 * @exception ErroRepositorioException Descrição da exceção
	 */
	public Integer pesquisarConsumoFaturadoMesPorConsumoHistorico(Integer idImovel, 
		int anoMesReferencia,Integer idLigacaoTipo) throws ErroRepositorioException ;

    /**
     * [UC0103] Efetuar Rateio de Consumo 
     *
     * atualiza o consumo de água/esgoto a ser rateado e o consumo de água/esgoto dos 
     * imóveis vínculados do imóvel condomínio.
     *
     * @author Pedro Alexandre 
     * @date 17/01/2007
     *
     * @param idConsumoHistoricoImovelCondominio
     * @param consumoRateio
     * @param consumoImovelVinculadosCondominio
     * @throws ErroRepositorioException
     */
    public void atualizarConsumoHistoricoImovelCondominio(Integer idConsumoHistoricoImovelCondominio, Integer consumoRateio, Integer consumoImovelVinculadosCondominio) throws ErroRepositorioException ;
    
	
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0002] Gerar Relação(ROL) em TXT
	 * 
	 * @author Rafael Francisco Pinto
	 * @date 19/01/2007
	 * 
	 * @param idRota
	 * @param idLeituraTipo
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImoveisParaLeituraPorColecaoRota(Collection colecaoRota,
			Integer idLeituraTipo, int numeroPagina)
			throws ErroRepositorioException ;	
	
    /**
     * [UC00083] Gerar Dados para Leitura 
     *
     * [SB0002] Gerar Relação(ROL) em TXT - Registro 2 (DOIS)
     * 
     * @author Rafael Francisco Pinto 
     * @date 22/01/2007
     *
     * @param idImovel
     * @param anoMesReferencia
     * 
     * @throws ErroRepositorioException
     */  
	public Integer pesquisarNumeroConsumoMedioImovel(Integer idImovel,int anoMesReferencia,Integer idLigacaoTipo) 
		throws ErroRepositorioException ;	

    /**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * @author Raphael Rossiter
	 * @date 19/01/2007
	 * 
	 * @param Integer idImovel, Integer tipoMedicao
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarUltimoAnoMesConsumoFaturado(Integer idImovel,
			Integer tipoMedicao)
			throws ErroRepositorioException ;
	
    /**
     * [UC00083] Gerar Dados para Leitura 
     *
     * [SB0002] Gerar Relação(ROL) em TXT - Registro 1 (HUM)
     * 
     * @author Rafael Francisco Pinto 
     * @date 23/01/2007
     *
     * @param idImovel
     * @param anoMesReferencia
     * @param idTipoLigacao
     * 
     * @throws ErroRepositorioException
     */ 
	public String obterDescricaoConsumoTipo(Integer idImovel,Integer idTipoLigacao)
		throws ErroRepositorioException ;
	
	public Object[] obterConsumoAnteriorAnormalidadeDoImovel(
			Integer idImovel, Integer anoMes, Integer idLigacaoTipo)
			throws ErroRepositorioException;
	
	/**
	 * Atualiza o valor de cshi_nnconsumomedio, cshi_nnconsumomediohidrometro consumo historico [UC0106] -
	 * Substituir Consumos Anteriores
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarConsumosMedio(Integer idImovel , Integer anoMesConsumoHistorico, 
			int consumoMedioHidrometroAgua, int consumoMedioHidrometroEsgoto)
			throws ErroRepositorioException;
	
	/**
	 * Obtém os ids de todas as rotas cadastradas menos as rotas que tiverem o emp_cobranca = 1
	 * 
	 * [UC0251] - Gerar Atividade de Ação de Cobrança
	 * 
	 * [SB0002] - Gerar Atividade de Ação de Cobrança para os Imóveis da Lista
	 * de Rotas
	 * 
	 * @author Sávio Luiz
	 * @date 05/03/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarListaRotasEspecificas() throws ErroRepositorioException;
	
	/**
	 * 
	 * Método que apresenta os dados do imovel
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 * 
	 * @author Sávio Luiz
	 * @date 04/08/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 */
	public Collection pesquisarMedicaoConsumoHistoricoExcecoesApresentaDadosConsultarImovel(
			Integer anoMesReferencia, Integer idImovel,
			boolean ligacaoAgua) throws ErroRepositorioException;
	
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * @author Sávio Luiz
	 * @date 13/03/2007
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImoveisPorRotaOrdenadoPorInscricao(Collection colecaoRota,
			Integer idLeituraTipo, int inicioPesquisa, String empresa)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa todas as rotas do sistema.
	 * Metódo usado no [UC0302] Gerar Débito a Cobrar de Acréscimos por Impontualidade
	 *
	 * @author Pedro Alexandre
	 * @date 20/03/2007
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarListaRotasCarregadas() throws ErroRepositorioException ;
	
	
	/**
	 * [UC0105] Obter Consumo Mínimo da Ligação por Subcategoria
	 * 
	 * (CSTC_NNCONSUMOMINIMO da tarifa associada ao imóvel na tabela CONSUMO_TARIFA_CATEGORIA com SCAT_ID=Id 
	 * da subcategoria e CSTV_ID = CSTV_ID da tabela CONSUMO_TARIFA_VIGENCIA com CSTF_ID=CSTF_ID da tabela 
	 * IMOVEL e maior CSTV_DTVIGENCIA, que seja menor ou igual a data corrente)  
	 * 
	 * @author Raphael Rossiter
	 * @date 11/04/2007
	 * 
	 * @return subcategoria, consumoTarifaVigencia
	 * @throws ControladorException
	 */
	public Object pesquisarConsumoMinimoTarifaSubcategoriaVigencia(
			Subcategoria subcategoria, ConsumoTarifaVigencia consumoTarifaVigencia)
			throws ErroRepositorioException ;
	
	/**
	 * [UC0082] - Registrar Leituras e Anormalidades
	 * 
	 * [SB0001] - Gerar Relatório Resumo das Leituras e Anormalidades
	 * 
	 * Pesquisa as Anormalidades de Leitura e suas quantidades
	 * 
	 * @author Rafael Corrêa
	 * @param idRotaFinal 
	 * @param idRotaInicial 
	 * @date 13/04/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarAnormalidadesRelatorioComparativoLeiturasEAnormalidades(
			Integer idGrupoFaturamento, Integer idEmpresa, Integer anoMes, 
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idSetorInicial, Integer idSetorFinal,
			Integer idGerencia, Integer idUnidadeNegocio, Integer idLeiturista,
			Integer idRotaInicial, Integer idRotaFinal,
			Integer idPerfilImovel, Integer numOcorrenciasConsecutivas, Collection colecaoAnormalidadesLeituras,
			SistemaParametro sistemaParametro) throws ErroRepositorioException;
	
	/**
	 * [UC0082] - Registrar Leituras e Anormalidades
	 * 
	 * [SB0001] - Gerar Relatório Resumo das Leituras e Anormalidades
	 * 
	 * Pesquisa os dados do relatório do comparativo de leituras e anormalidades
	 * 
	 * @author Rafael Corrêa
	 * @param idRotaFinal 
	 * @param idRotaInicial 
	 * @date 13/04/2007
	 * 
	 * @author Magno Gouveia
	 * @param idPerfilImovel
	 * @param numOcorrenciasConsecutivas
	 * @param colecaoAnormalidadesLeituras
	 * @param sistemaParametro
	 * @date 21/06/2011
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosRelatorioComparativoLeiturasEAnormalidades(Integer idGrupoFaturamento,
			Integer idEmpresa, Integer anoMes, 
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idSetorInicial, Integer idSetorFinal, 
			Integer idGerencia, Integer idUnidadeNegocio, Integer idLeiturista, 
			Integer idRotaInicial, Integer idRotaFinal,
			Integer idPerfilImovel, Integer numOcorrenciasConsecutivas, Collection colecaoAnormalidadesLeituras,
			SistemaParametro sistemaParametro)
			throws ErroRepositorioException;
	
	/**
	 * [UC0082] - Registrar Leituras e Anormalidades
	 * 
	 * [SB0001] - Gerar Relatório Resumo das Leituras e Anormalidades Registradas 
	 * 
	 * Obter empresa do imóvel.
	 * 
	 * @author Rafael Corrêa
	 * @date 13/04/2007
	 * 
	 * @param idImovel
	 * @throws ErroRepositorioException
	 */
	public Integer obterIdEmpresaPorImovel(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Vivianne Sousa
	 * @date 06/06/2007
	 * 
	 * @param idImovel
	 * @param anoMes
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterDadosConsumoHistoricoDoImovel(
			Integer idImovel, Integer anoMes)
			throws ErroRepositorioException;


	/**
	 * 
	 * @author Vivianne Sousa
	 * @date 07/06/2007
	 * 
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterDadosMedicaoHistoricoDoImovel(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException;
	
	
	/**
	 * 
	 * @author Vivianne Sousa
	 * @date 08/06/2007
	 * 
	 * @param idImovel
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer obterMaxAMFaturamentoConsumoHistoricoDoImovel(
			Integer idImovel)
			throws ErroRepositorioException;
	
	
	/**
	 * 
	 * @author Vivianne Sousa
	 * @date 08/06/2007
	 * 
	 * @param idImovel
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterDadosHidrometro(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC0613] - Registrar Leituras e Anormalidades
	 * 
	 * Pesquisa os imóveis com faixa falsa
	 * 
	 * @author Rafael Corrêa
	 * @date 18/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImovelFaixaFalsa(Integer anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * [UC0613] - Registrar Leituras e Anormalidades
	 * 
	 * Retorna a quantidade de imóveis com faixa falsa
	 * 
	 * @author Rafael Corrêa
	 * @date 18/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarImovelFaixaFalsaCount(Integer anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * @author Vivianne Sousa
	 * @date 13/06/2007
	 * 
	 * @param idImovel
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterRotaESequencialRotaDoImovel(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC0623] Gerar Resumo de Metas CAERN
	 * 
	 * @author Sávio Luiz
	 * @date 15/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */

	public Integer pesquisarConsumoFaturado(Integer idImovel,
			Integer tipoLigacao, Integer idConsumoTipoMediaImovel,Integer idConsumoTipoMediaHidrometro,Integer amArrecadacao)
			throws ErroRepositorioException;
	

	/**
	 * selecionar os movimentos roteiros empresas. 
	 *
	 * [UC0627] Gerar Arquivo Texto para Leituristas
	 *
	 * @author Pedro Alexandre
	 * @date 03/08/2007
	 *
	 * @param idRoteiroEmpresa
	 * @param anoMesFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarMovimentoRoteiroEmpresa(Integer idRoteiroEmpresa,Integer anoMesFaturamento , Integer idFaturamentoGrupo) throws ErroRepositorioException ;

	/**
	 * selecionar os movimentos roteiros empresas.
	 * 
	 * [UC0627] Gerar Arquivo Texto para Leituristas
	 * 
	 * @author Breno Santos
	 * @date 17/03/2011
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMovimentoRoteiroEmpresa(Integer idImovel) throws ErroRepositorioException ;
	
	/**
	 * [UC0627] Gerar Arquivo Texto para Leituristas
	 * 
	 * Pesquisa a quantidade de setores comercias por roteiro empresa. 
	 *
	 * [FS0004] Verificar Quantidade de setores comercias.
	 *
	 * @author Pedro Alexandre
	 * @date 06/08/2007
	 *
	 * @param idRoteiroEmpresa
	 * @param anoMesFaturamento
	 * @param idFaturamentoGrupo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeSetorComercialPorRoteiroEmpresa(Integer idRoteiroEmpresa, Integer anoMesFaturamento, Integer idFaturamentoGrupo, Integer idLeituraTipo) throws ErroRepositorioException ;
	
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0005] Gerar Relação(ROL) em TXT - CAER
	 * 
	 * @author Sávio Luiz
	 * @date 13/08/2007
	 * 
	 * @param idRota
	 * @param idLeituraTipo
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImoveisParaLeituraPorColecaoRotaCAER(Rota rota,
			Integer idLeituraTipo)
			throws ErroRepositorioException;

	
	/**
	 * Pesquisa os roteiros empresa de acordo com os parâmetros informado pelo usuário
	 * 
	 * [UC0370] - Filtrar Roteiro Empresa
	 * 
	 * @author Thiago Tenório
	 * @date 29/08/2007
	 * 
	 * @param empresa
	 * @param idLocalidade
	 * @param idLeiturista
	 * @param idSetorComercial
	 * @param indicadorUso
	 * @param numeroPagina
	 * @return Collection
	 */
	public Collection pesquisarRoteiroEmpresa(String idEmpresa, String idLocalidade,
			String codigoSetorComercial, String idLeiturista, String indicadorUso,
			Integer numeroPagina) throws ErroRepositorioException;
	
	/**
	 * Verifica a quantidade de registros retornados da pesquisa de roteiro empresa
	 * 
	 * [UC0370] - Filtrar Roteiro Empresa
	 * 
	 * @author Thiago Tenório
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
	public int pesquisarRoteiroEmpresaCount(String idEmpresa, String idLocalidade,
			String codigoSetorComercial, String idLeiturista, String indicadorUso)
			throws ErroRepositorioException;

	
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0005] Gerar Relação(ROL) em TXT - CAER
	 * 
	 * @author Sávio Luiz
	 * @date 24/08/2007
	 * 
	 * @param idRota
	 * @param idLeituraTipo
	 * 
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosLocalProtecaoTipoLigacaoAgua(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0005] Gerar Relação(ROL) em TXT - CAER
	 * 
	 * @author Sávio Luiz
	 * @date 24/08/2007
	 * 
	 * @param idRota
	 * @param idLeituraTipo
	 * 
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosLocalProtecaoTipoTipoPoco(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UC00082] Registrar Leituras e Anormalidades
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 29/08/2007
	 * 
	 * @param idRota
	 * @param idLeituraTipo
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<MovimentoRoteiroEmpresa> pesquisarColecaoMovimentoRoteiroEmpresa(
			Integer idGrupoFaturamento) throws ErroRepositorioException;

	/**
	 [UC0631] Processar Requisições do Dispositivo Móvel.
	 * 
	 * [SB0001] Baixar Arquivo Texto para o Leiturista.
	 *
	 * 
	 * @author Thiago Nascimento 
	 * @date 14/08/2007
	 *  
	 * @param imei
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] baixarArquivoTextoParaLeitura(long imei,Integer idServicoTipoCelular) throws ErroRepositorioException;
	
	/**
	 [UC0631] Processar Requisições do Dispositivo Móvel.
	 * 
	 * Atualizar Situação do Arquivo Texto.
	 *
	 * 
	 * @author Thiago Nascimento 
	 * @date 14/08/2007
	 *  
	 * @param imei
	 * @param situacaoAnterior
	 * @param situacaoNova
	 * @throws ErroRepositorioException
	 */
	public void atualizarArquivoTextoEnviado(long imei, int situacaoAnterior, int situacaoNova) throws ErroRepositorioException;
	
	/**
	 [UC0631] Processar Requisições do Dispositivo Móvel.
	 * 
	 * Atualizar Situação do Arquivo Texto.
	 *
	 * 
	 * @author Thiago Nascimento 
	 * @date 14/08/2007
	 *  
	 * @param idRota
	 * @param situacaoAnterior
	 * @param situacaoNova
	 * @throws ErroRepositorioException
	 */
	public void atualizarArquivoTextoEnviadoPorRota(Integer idRota, int situacaoAnterior, int situacaoNova) throws ErroRepositorioException;
	
	/**
	 [UC0631] Processar Requisições do Dispositivo Móvel.
	 * 
	 * [SB0002] Atualizar o movimento roteiro empresa.
	 *
	 * 
	 * @author Thiago Nascimento 
	 * @date 14/08/2007
	 *  
	 * @param dados
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarRoteiro(Collection<DadosMovimentacao> dados, Integer anoMesReferencia,
			boolean atualizaDataProcessamento) throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Vivianne Sousa
	 * @date 06/09/2007
	 * 
	 * @param idImovel
	 * @param anoMes
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer obterConsumoMedioEmConsumoHistorico(Integer idImovel, Integer idLigacaoTipo) throws ErroRepositorioException;

	public void removerMovimentoRoteiroEmpresa(
			Integer anoMesCorrente, Integer idGrupoFaturamentoRota) throws ErroRepositorioException;
	
	/**
	 * Pesquisa os roteiros empresas pelo grupo de faturamento
	 *
	 * [UC0627] Gerar Arquivo Texto para Leitura
	 *
	 * @author Pedro Alexandre
	 * @date 13/09/2007
	 *
	 * @param idGrupoFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRoteiroEmpresaPorGrupoFaturamento(Integer idGrupoFaturamento) throws ErroRepositorioException ;

	
	/**
	 * [UC0101] - Consistir Leituras e Calcular Consumos
	 * 
	 * @author Raphael Rossiter, Raphael Rossiter
	 * @date 17/09/2007, 25/08/2009
	 * 
	 * @return Collection
	 * @exception ErroRepositorioException
	 */
	public Collection pesquisarImovelConsistirLeituraPorRota(Rota rota) throws ErroRepositorioException ;
	
	/**
	 * [FS005] - Verificar existência do arquivo texto roteiro empresa. 
	 * 
	 * Caso já exista um arquivo texto para o mês de referência informado,
	 * mesmo roteiro empresa, mesmo grupo de faturamento e sua situação de leitura 
	 * transmissão esteja disponível, exclui o arquivo correspondente e retorna pra o caso 
	 * se uso que chamou esta funcionalidade.   
	 *
	 * [UC0627] Gerar Arquivo Texto para Leiturista
	 *
	 * @author Pedro Alexandre
	 * @date 17/09/2007
	 *
	 * @param anoMesReferencia
	 * @param idRoteiroEmpresa
	 * @param idGrupoFaturamento
	 * @throws ErroRepositorioException
	 */
	public boolean excluirArquivoTextoParaLeiturista(Integer anoMesReferencia, Integer idRoteiroEmpresa, Integer idGrupoFaturamento) throws ErroRepositorioException ;
	
	
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public String[] pesquisarQuantidadeRotas(String codigoSetorComercial, 
			String rotaInicial, String rotaFinal, String idLocalidade, String idCobrancaAcao, Integer idCriterio) 
			throws ErroRepositorioException;
	
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public String[] pesquisarQuantidadeRotasIntervaloSetor(String codigoSetorComercialInicial, 
			String codigoSetorComercialFinal, String idLocalidade, String idCobrancaAcao, Integer idCriterio) 
			throws ErroRepositorioException;
	
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public String[] pesquisarQuantidadeRotasIntervaloLocalidade(String idLocalidadeInicial, 
			String idLocalidadeFinal, String idCobrancaAcao, Integer idCriterio) 
			throws ErroRepositorioException;
	
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public String[] pesquisarQuantidadeRotasIntervaloGerencia(String idGerenciaRegional, String idCobrancaAcao, Integer idCriterio) 
			throws ErroRepositorioException;
	
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public String[] pesquisarQuantidadeRotasIntervaloGrupo(String idGrupoCobranca, String idCobrancaAcao, Integer idCriterio) 
			throws ErroRepositorioException;
	
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public String[] pesquisarQuantidadeRotasIntervaloUnidadeNegocio(String idUnidadeNegocio, String idCobrancaAcao, Integer idCriterio) 
	throws ErroRepositorioException;
	
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca	
	public String[] pesquisarQuantidadeRotasPorCobrancaAcao(String idCobrancaAcao, Integer idCriterio) 
	throws ErroRepositorioException;
	
	/**
	 * Pesquisa as rotas pelo grupo de faturamento
	 *
	 * [UC0627] Gerar Arquivo Texto para Leitura
	 *
	 * @author Pedro Alexandre
	 * @date 15/10/2007
	 *
	 * @param idGrupoFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRotasPorGrupoFaturamento(Integer idGrupoFaturamento) throws ErroRepositorioException ;

	
	/**
	 * [FS005] - Verificar existência do arquivo texto roteiro empresa por rota. 
	 * 
	 * Caso já exista um arquivo texto para o mês de referência informado,
	 * mesma rota, mesmo grupo de faturamento e sua situação de leitura 
	 * transmissão esteja liberado, exclui o arquivo correspondente e retorna pra o caso 
	 * se uso que chamou esta funcionalidade.   
	 *
	 * [UC0627] Gerar Arquivo Texto para Leiturista
	 *
	 * @author Pedro Alexandre
	 * @date 15/10/2007
	 *
	 * @param anoMesReferencia
	 * @param idRota
	 * @param idGrupoFaturamento
	 * @throws ErroRepositorioException
	 */
	public boolean excluirArquivoTextoParaLeituristaPorRota(Integer anoMesReferencia, Integer idRota, Integer idGrupoFaturamento) throws ErroRepositorioException ;

	
	/**
	 * [UC0627] Gerar Arquivo Texto para Leituristas
	 * 
	 * Pesquisa a quantidade de setores comercias por roteiro empresa.
	 * 
	 * [FS0004] Verificar Quantidade de setores comercias.
	 * 
	 * @author Pedro Alexandre
	 * @date 15/10/2007
	 * 
	 * @param idRota
	 * @param anoMesFaturamento
	 * @param idFaturamentoGrupo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeSetorComercialPorRota(
			Integer idRota, 
			Integer anoMesFaturamento,
			Integer idFaturamentoGrupo) throws ErroRepositorioException ;

	/**
	 * selecionar os movimentos roteiros empresas por rota.
	 * 
	 * [UC0627] Gerar Arquivo Texto para Leituristas
	 * 
	 * @author Pedro Alexandre
	 * @date 15/10/2007
	 * 
	 * @param idRota
	 * @param anoMesFaturamento
	 * @param idFaturamentoGrupo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarMovimentoRoteiroEmpresaPorRota(
			Integer idRota, 
			Integer anoMesFaturamento,
			Integer idFaturamentoGrupo, String empresa, Integer idLeituraTipo) throws ErroRepositorioException ;
	
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public void desassociarRotasIntervaloRotas(String codigoSetorComercial, 
			String rotaInicial, String rotaFinal, String idLocalidade, String idCobrancaAcao) 
			throws ErroRepositorioException;
	
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public void desassociarRotasIntervaloSetor(String codigoSetorComercialInicial, 
			String codigoSetorComercialFinal, String idLocalidade, String idCobrancaAcao) 
			throws ErroRepositorioException;
	
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public void desassociarRotasIntervaloLocalidade(String idLocalidadeInicial, 
			String idLocalidadeFinal, String idCobrancaAcao) 
			throws ErroRepositorioException;
	
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public void desassociarRotasIntervaloGerencia(String idGerenciaRegional, String idCobrancaAcao) 
			throws ErroRepositorioException;
	
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public void desassociarRotasIntervaloGrupo(String idGrupoCobranca, String idCobrancaAcao) 
			throws ErroRepositorioException;
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public void desassociarRotasIntervaloUnidadeNegocio(String idUnidadeNegocio, String idCobrancaAcao) 
	throws ErroRepositorioException;
	
	/**
	 * [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	 * @param idCobrancaAcao
	 * @author Francisco Nascimento
	 * @date 27/02/08
	 * @throws ErroRepositorioException
	 */
	public void desassociarRotasPorCobrancaAcao(String idCobrancaAcao) 
		throws ErroRepositorioException;
	
	/**
	 * pesquisa o consumo historico passando o imovel e o anomes referencia e o
	 * consumo anormalidade correspondente ao faturameto antecipado.
	 * 
	 * [UC0113] Faturar Grupo de Faturamento
	 * 
	 * @author Sávio Luiz
	 * @date 08/11/2007
	 * 
	 * @param idRota
	 * @param anoMesFaturamento
	 * @param idFaturamentoGrupo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarConsumoHistoricoAntecipado(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException;
	
	/**
	 * 
	 * Relatório Analise de Consumo
	 * Flávio Leonardo 
	 * 26/12/2007
	 * 
	 * @param idImovel
	 * @param anomes
	 * @return
	 * @throws ErroRepositorioException
	 */
	 
	public Collection pesquisarLeiturasImovel(String idImovel, String anoMes)
			throws ErroRepositorioException;
	
	/**
	 * Relatório Manter Hidrometro
	 * 
	 * Flávio Leonardo
	 * 
	 * pesquisa o id do imovel do hidrometro instalado
	 * @throws ErroRepositorioException 
	 */
	public Integer pesquisarImovelPeloHidrometro(Integer hidrometroId) throws ErroRepositorioException;
	
	/**
	 * 	 
	 * Busca o Ano Mês de Referencia o grupo de Faturamento fornecido.
	 * 
	 * [UC0712] Atualizar Leituras e Anormalidades do Celular
	 * 
	 * @author Thiago Nascimento
	 * @date 11/12/2007
	 * 
	 * @param grupo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public FaturamentoGrupo buscarAnoMesReferenciaCasoOperador(Integer grupo)throws ErroRepositorioException ;
	
	/**
	 * 
	 * Método para a atualização das leituras e Anormalidades do Celular,
	 * em que atualiza o Movimento do Roteiro de uma Empresa e inserir no Histórico
	 * de medições. 
	 * 
	 * [UC0712] Atualizar Leituras e Anormalidades do Celular
	 * 
	 * @author Thiago Nascimento
	 * @date 05/12/2007
	 * 
	 * @param dados
	 * @param anoMesFaturamento
	 * @throws ErroRepositorioException
	 */
	public void atualizarLeituraAnormailidadeCelular(
			DadosMovimentacao dado, Integer anoMesFaturamento, Leiturista l)
			throws ErroRepositorioException ;
	
	/**
	 * 
	 * Verifica se houve todos os processamentos do um determinado grupo e ano-Mês de Referencia
	 * e inserir no cronograma de atividades.
	 * 
	 * [UC0712] Atualizar Leituras e Anormalidades do Celular
	 * 
	 * @author Thiago Nascimento
	 * @date 05/12/2007
	 * 
	 * @param grupo
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 */
	public void verificarProcessamentoUltimoArquivoTexto(Integer grupo,
			Integer anoMesReferencia,Date dataRealizacao) throws ErroRepositorioException;
	
	/**
	 * 
	 * Busca o Ano Mês de Referencia para a matrícula do Imóvel informada.
	 * 
	 * [UC0712] Atualizar Leituras e Anormalidades do Celular
	 * 
	 * @author Thiago Nascimento
	 * @date 11/12/2007
	 * 
	 * @param matricula
	 * @return
	 * @throws ErroRepositorioException
	 */
	public FaturamentoGrupo buscarAnoMesReferenciaCasoSistema(Integer matricula)throws ErroRepositorioException;
	
	/**
	 * 
	 * Método que busca a descarição da LeituraAnormalidade que tem o código
	 * passado como parâmetro.
	 * 
	 *	[UC0712] Atualizar Leituras e Anormalidades do Celular
	 * 
	 * @author Thiago Nascimento
	 * @date 12/12/2007 
	 * 
	 * @param id
	 * @return String com a descricao da LeituraAnormalidade
	 * @throws ErroRepositorioException
	 */
	public String buscarDescricaoLeituraAnormalidade(Integer id)throws ErroRepositorioException;
	
	/**
	 * Atualiza Movimento Roteiro Empresa 
	 *
	 * @author Raphael Rossiter
	 * @date 31/03/2010
	 *
	 * @param dado
	 * @param anoMesReferencia
	 * @param atualizaDataProcessamento
	 * @return MovimentoRoteiroEmpresa
	 * @throws ErroRepositorioException
	 */
	public MovimentoRoteiroEmpresa atualizarMovimentoRoteiroEmpresa(DadosMovimentacao dado, Integer anoMesReferencia, 
			boolean atualizaDataProcessamento) throws ErroRepositorioException ;
	
	/**
	 * Pesquisar o imovel pela Matrícula.
	 * 
	 * [UC0101] - Consistir Leituras e Calcular Consumos
	 * 
	 * @author Raphael Rossiter
	 * @date 17/09/2007
	 * 
	 * @return Collection
	 * @exception ErroRepositorioException
	 */
	public Object[] pesquisarImovelPelaMatricula(Integer matricula) throws ErroRepositorioException;
	
	/**
	 * Buscar Rota a qual o imóvel pertence.
	 * 
	 * @param matricula
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Rota buscarRotaDoImovel(Integer matricula) throws ErroRepositorioException ;
	
	/**
	 * [UC0629] Consultar Arquivo Texto Leitura.
	 * 
	 * Atualizar Situação do Arquivo Texto.
	 * 
	 * 
	 * @author Thiago Nascimento
	 * @date 29/01/2008
	 * 
	 * @param id
	 * @param situacaoNova
	 * @throws ErroRepositorioException
	 */
	public void atualizarArquivoTexto(Integer id, Integer situacaoNova, String motivoFinalizacao) throws ErroRepositorioException;
	
	/**
	 * 
	 * Atualizar a situação do Arquivo Roteiro Empresa para 4, para o arquivo com 
	 * grupo, anoMesReferencia, localidade e setor e quadra passados como parametros. 
	 * 
	 * @param grupo
	 * @param dado
	 * @throws ErroRepositorioException
	 */
	public void atualizarArquivoRoteiroEmpresaParaFinalizado(
			FaturamentoGrupo grupo, DadosMovimentacao dado,Integer idRota)
			throws ErroRepositorioException;	
	
	/**
	 * 
	 * Retorna uma coleção com os dados dos Consumos para apresentação
	 * sem informar o ano/mes para o caso em que o Imovel nao possui
	 * Hidrometro (Sem Medicao).
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 */
	public Collection carregarDadosConsumo(Integer idImovel, boolean ligacaoAgua) throws ErroRepositorioException;
	
	
	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 *
	 * @author Raphael Rossiter
	 * @date 24/04/2008
	 *
	 * @param idImovel
	 * @param anoMesReferenciaInicio
	 * @param anoMesReferenciaFim
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection obterUltimosConsumosImovel(Integer idImovel,
			Integer tipoLigacao) throws ErroRepositorioException ;
	
	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 *
	 * @author Raphael Rossiter
	 * @date 24/04/2008
	 *
	 * @param consumoHistorico
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer obterLeituraAnormalidadeFaturamentoMedicaoHistorico(ConsumoHistorico consumoHistorico) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 *
	 * [SB0006] - Obter dados dos tipos de medição
	 *
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 *
	 * @param imovel
	 * @param anoMesReferencia
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Object[] obterDadosTiposMedicao(Integer idImovel,Integer idLigacaoAgua, 
			Integer anoMesReferencia) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection buscarImoveisPorRota(Integer idRota, String empresa, Integer anoMesFaturamento) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public void removerRelacaoRotaLeiturista(Collection rotas) throws ErroRepositorioException;
	
	public boolean temArquivoLiberado(Integer idArquivo) throws ErroRepositorioException;
	
	public Integer numeroSequenciaUltimaRota(Integer leiturista) throws ErroRepositorioException;
	
	public Integer maximoIdLeiturista() throws ErroRepositorioException;
	
	public Integer quantidadeLeiturasRealizada(Integer rota, Integer anoMes,Integer idServicoTipoCelular) throws ErroRepositorioException;
	
	/**
	 * [UC0800] - Obter Consumo Não Medido
	 * Obter o consumo mínimo associado à faixa de área do imóvel e a categoria ou subcategoria informada
	 */
	public Integer pesquisarConsumoMinimoArea(BigDecimal areaConstruida, Integer anoMesReferencia,
			Integer idSubcategoria, Integer idCategoria) throws ErroRepositorioException ;
	
	/**
	 * 
	 * [UC0781] - Informar Consumo por Área
	 *
	 *
	 * @author Rômulo Aurélio
	 * @date 21/05/2008
	 *
	 * @param anoMesReferenciaInformado
	 * @return
	 * @throws ErroRepositorioException
	 */
	
	
	public Integer pesquisarAnoMesReferenciaMenorAnoMesReferenciaFaturamentoGrupo(
			int anoMesReferenciaInformado) throws ErroRepositorioException ;
	
	/**
	 * 
	 * [UC0805] - Gerar Aviso de Anormalidade
	 *
	 * Pesquisa os dados necessérios para a geração do relatório
	 *
	 * @author Rafael Corrêa
	 * @date 03/06/2008
	 *
	 * @param colecaoImoveis
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarAvisoAnormalidadeRelatorio(
			Collection colecaoImoveis, Integer anoMes) throws ErroRepositorioException;
	
	/**
	 * [UC0805] - Gerar Aviso de Anormalidade
	 *
	 * Pesquisa os dados necessérios para a geração do relatório
	 *
	 * @author Rafael Corrêa
	 * @date 03/06/2008
	 *
	 * @param colecaoImoveis
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarAvisoAnormalidadeRelatorio(
			FiltrarAnaliseExcecoesLeiturasHelper filtrarAnaliseExcecoesLeiturasHelper, Integer anoMes) throws ErroRepositorioException;
	
	/**
	 * [UC0805] - Gerar Aviso de Anormalidade
	 *
	 * Pesquisa os dados necessérios para a geração do relatório
	 *
	 * @author Rafael Corrêa
	 * @date 28/06/2008
	 *
	 * @param gerarDadosLeituraHelper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosParaLeituraRelatorio(GerarDadosLeituraHelper gerarDadosLeituraHelper, String empresa) throws ErroRepositorioException;
	
	/**
	 * [UC0805] - Gerar Aviso de Anormalidade
	 * 
	 * Pesquisa a quantidade de registros do relatório
	 * 
	 * @author Rafael Corrêa
	 * @date 28/06/2008
	 * 
	 * @param gerarDadosLeituraHelper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarDadosParaLeituraRelatorioCount(
			GerarDadosLeituraHelper gerarDadosLeituraHelper)
			throws ErroRepositorioException;
	
	/**
	 * [UC0082] - Registrar Leituras e Anormalidades
	 *
	 * Obter o leiturista do imóvel
	 *
	 * @author Rafael Corrêa
	 * @date 01/07/2008
	 *
	 * @param idImovel
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarLeituristaImovel(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * 
	 * Atualiza o FaturamentoAtividadeCronograma do grupo no anoMes especificado para o
	 * Registrar, Consistir e Efetuar Leitura. 
	 * 
	 * [UC0712] Atualizar Leituras e Anormalidades do Celular
	 * 
	 * @author Thiago Nascimento
	 * @date 23/05/2008
	 * 
	 * @param grupo
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 */
	public void atualizarFaturamentoAtividadeCronogramaRegistrarConsistirEfetuarLeitura(Integer grupo,
			Integer anoMesReferencia,Date dataRealizacao) throws ErroRepositorioException;
	
	/**
	 * Retorno a quantidade leituras que ainda não foram registradas
	 * 
	 * @data 03/06/2008
	 * @param anoMes
	 * @param grupo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer consultarQuantidadeLeiturasNaoResgistradas(Integer anoMes, Integer grupo)
		throws ErroRepositorioException;
	
	/**
	 * Retorna as leituras que ainda não foram registradas
	 * 
	 * @param anoMes
	 * @param grupo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List consultarLeiturasNaoResgistradas(Integer anoMes, Integer grupo)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * Obter dados Leitura Anteriores
	 * 
	 * @author Tiago Moreno
	 * @data 03/06/2008
	 * 
	 * @param idImovel
	 * @param anomes
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarLeiturasImovelAnoMes(String idImovel, String anoMes)
			throws ErroRepositorioException;

	
	/**
	 *
	 *
	 * @author Yara Taciane de Souza
	 * @date 26/08/2008
	 *
	 * @param idLocalidade
	 * @param codigoSetorComercial
	 * @param anoMesReferencia
	 * @return
	 * @throws ErroRepositorioException
	 */
	
	
	public Integer pesquisarLeiturasEnviadasRelatorioCount(Integer anoMesReferencia, Integer idEmpresa, Integer idLocalidade,
			Integer codigoSetorComercial, Integer idGrupoFaturamento, Integer idGerencia, Integer idUnidadeNegocio, Integer idLeiturista)throws ErroRepositorioException;
	
	/**
	 * [UC0101] - Consistir Leituras e Calcular Consumos
	 *
	 * [SB0021] - Dados para Faturamento Especial Medido
	 *
	 * @author Raphael Rossiter
	 * @date 12/08/2008
	 *
	 * @param imovel
	 * @param faturamentoGrupo
	 * @return FaturamentoSituacaoHistorico
	 * @throws ErroRepositorioException
	 */
	public FaturamentoSituacaoHistorico pesquisarFaturamentoSituacaoHistoricoConsumoVolumeFixo(Imovel imovel, FaturamentoGrupo faturamentoGrupo)
			throws ErroRepositorioException ;

	/**
	 * Retorna os imoveis ordenados por inscricao
	 * Author: Rômulo Aurélio
	 * @param rota
	 * @param inicioPesquisa
	 * @param empresa
	 * @return
	 * @throws ErroRepositorioException
	 */
	
	public Collection pesquisarImoveisPorRotaCompesa(
			Rota rota, 
			String empresa) throws ErroRepositorioException;
	
	
	/**
	 * 
	 * @author: Rômulo Aurélio 
	 * Selecionar os movimentos roteiros empresas.
	 * @param anoMesFaturamento
	 * @param idFaturamentoGrupo
	 * @return
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarMovimentoRoteiroEmpresaConvencional(Integer anoMesFaturamento,
			Integer idFaturamentoGrupo) throws ErroRepositorioException;
	
	/**
	 * 
	 * Pesquisar Faixa de leitura Falsa
	 * @author: Rômulo Aurélio
	 * @param idMedicaoTipo
	 * @param imovel
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	
	public LeituraFaixaFalsa pesquisarDadosLeituraFaixaFalsa(Integer idMedicaoTipo, Imovel imovel, Integer anoMes)
	throws ErroRepositorioException;
	
	/**
	 *  Selecionar os movimentos roteiros empresas.
	 * 
	 * @author: Rômulo Aurélio
	 * @param anoMesFaturamento
	 * @param idFaturamentoGrupo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarMovimentoRoteiroEmpresaRolCompesa(
			Integer anoMesFaturamento, Integer idFaturamentoGrupo,
			int inicioPesquisa) throws ErroRepositorioException;

	/**
	 * Pesquisa imoveis para leitura por colecao de Rota para Caema
	 * 
	 * @author: Rômulo Aurélio
	 * @date 02/07/2008
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarImoveisParaLeituraPorColecaoRotaCAEMA(Rota rota,
			int inicioPesquisa, Integer anoMes) throws ErroRepositorioException;
	/**
	 * Pesquisa MovimentoRoteiroEmpresa para leitura por Rota
	 * 
	 * @author: Rômulo Aurélio
	 * @date 02/07/2008
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarMovimentoRoteiroEmpresaParaLeituraPorColecaoRotaCAERN(
			Collection colecaoRotas , Integer idLeituraTipo, int numeroIndice,Integer anoMesCorrente)
			throws ErroRepositorioException;
	/**
	 * Pesquisa MovimentoRoteiroEmpresa para leitura por colecao de Rota Caer
	 * 
	 * @author: Rômulo Aurélio
	 * @date 02/07/2008
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarMovimentoRoteiroEmpresaParaLeituraPorColecaoRotaCAER(
			Rota rota, Integer idLeituraTipo, Integer anoMesCorrente) throws ErroRepositorioException;
	/**
	 * Pesquisa imoveis por Rota Caema
	 * 
	 * @author: Rômulo Aurélio
	 * @date 02/07/2008
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImoveisPorRotaCaema(Rota rota, String empresa) throws ErroRepositorioException;
	/**
	 * Remove todos os dados de movimento roteiro empresa por Rota, Grupo de Faturamento e anoMes 
	 *  
	 * @author: Rômulo Aurélio
	 * @date 02/07/2008
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void removerMovimentoRoteiroEmpresa(Integer anoMesCorrente,
			Integer idGrupoFaturamentoRota, Rota rota) throws ErroRepositorioException;
	/**
	 * Remove todos os dados de arquivo texto roteiro empresa por Rota, Grupo de Faturamento e anoMes 
	 *  
	 * @author: Rômulo Aurélio
	 * @date 02/07/2008
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean removerArquivoTextoRoteiroEmpresa(Integer anoMesCorrente,
			Integer idGrupoFaturamentoRota, Integer idRota) throws ErroRepositorioException;
	
	public Collection pesquisarImoveisPorRotaCAERN(
			Rota rota,
			String empresa) throws ErroRepositorioException;
	/**
	 * [UC0629] Retornar Arquivo Txt Leitura
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 06/10/2008
	 *  
	 */
	public Collection pesquisarArquivosTextoRoteiroEmpresaParaArquivoZip(
			String[] ids) throws ErroRepositorioException;
	
	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 23/09/2008
	 * 
	 * @return ImovelSubcategoria
	 * @throws ErroRepositorioException
	 */

	public Object[] obterDadosHidrometroAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC00083] Gerar Dados para Leitura 
	 * [FS0006]-Verificar imóveis processados na competência
	 * 
	 * @date 13/10/2008
	 * @author Rômulo Aurélio
	 * 
	 */
	public Collection pesquisarImoveisMovimentoRoteiroEmpresaParaExistenteGeradoParaOutroGrupo(
			Collection colecaoImoveisParaGerar, Integer idFaturamentoGrupo, Integer anoMes) throws ErroRepositorioException;

	
	/**
	 * [UC0???] Gerar Relatorio Rotas Online por Empresa
	 * 
	 * @see RepositorioMicromedicaoHBM#pesquisarRelatorioRotasOnlinePorEmpresa(PesquisarRelatorioRotasOnlinePorEmpresaHelper)
	 * 
	 * @author Victor Cisneiros
	 * @date 28/10/2008
	 */
	public Collection<RelatorioRotasOnlinePorEmpresaBean> pesquisarRelatorioRotasOnlinePorEmpresa(
			PesquisarRelatorioRotasOnlinePorEmpresaHelper helper) throws ErroRepositorioException;

//	
//	/**
//	 * Processar Requisições da Atualizacao Cadastral.
//	 * 
//	 * @author Victor Cisneiros
//	 * @date 10/11/2008
//	 */
//	public byte[] baixarArquivoTextoAtualizacaoCadastral(long imei) throws ErroRepositorioException;

	
	/**
	 * [UC0???] Gerar Relatorio Rotas Online por Empresa
	 * 
	 * @author Victor Cisneiros
	 * @date 28/10/2008
	 */
	public Integer pesquisarRelatorioRotasOnlinePorEmpresaCount(
			PesquisarRelatorioRotasOnlinePorEmpresaHelper helper) throws ErroRepositorioException;
	
	public Collection pesquisarDadosRelatorioAnaliseImovelCorporativoGrande(
			Integer idGerenciaRegional,Integer idUnidadeNegocio,  
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idSetorComercialInicial, Integer idSetorComercialFinal, 
			Integer referencia, Integer idImovelPerfil, Integer selecionar)throws ErroRepositorioException;
	
	
	/**
	 * Pesquisar quantidade de imóveis por arquivo texto leitura.
	 * 
	 * @author Yara T. Souza
	 * @date 18/12/2008
	 *  
	 */
	public Object[] pesquisarQuantidadeImoveisPorArquivo(Integer id)throws ErroRepositorioException;
    
    /**
     * 
     * [UC0889] - Alterar datas das leituras
     *
     * Pesquisamos todos os dados necessários para a alteração das datas
     *
     * @author bruno
     * @date 26/02/2009
     *
     * @param idGrupoFaturamento
     * @return
     */
    public Collection<Object[]> 
        pesquisarDadosAlterarGruposFaturamento( Integer idGrupoFaturamento ) 
        throws ErroRepositorioException;
        
    /**
     * 
     * [UC0889] - Alterar datas das leituras
     *
     * Alteramos todos as datas informadas
     *
     * @author bruno
     * @date 26/02/2009
     *
     * @param idGrupoFaturamento
     * @return
     */
    public void alterarDatasLeituras( 
            AlterarDatasLeiturasHelper helper,
            Integer idGrupo ) 
            throws ErroRepositorioException;
    
    
    /**
	 * pesquisa uma coleção de cep(s) de acordo com o código
	 * 
	 * @param faixaInicial
	 *            Descrição do parâmetro
	 * @param faixaFinal
	 *            Descrição do parâmetro
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Integer pesquisarNumeroHidrometroSituacaoInstaladoPaginacaoCount(FiltrarHidrometroHelper helper)
			throws ErroRepositorioException ;
    
    /**
	 * pesquisa uma coleção de cep(s) de acordo com o código
	 * 
	 * @param faixaInicial
	 *            Descrição do parâmetro
	 * @param faixaFinal
	 *            Descrição do parâmetro
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarNumeroHidrometroSituacaoInstaladoPaginacao(FiltrarHidrometroHelper helper,
			Integer numeroPagina)
			throws ErroRepositorioException;
	
	 /**
	 * pesquisa uma coleção de cep(s) de acordo com o código
	 * 
	 * @param faixaInicial
	 *            Descrição do parâmetro
	 * @param faixaFinal
	 *            Descrição do parâmetro
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarNumeroHidrometroSituacaoInstaladoRelatorio(FiltrarHidrometroHelper helper)
			throws ErroRepositorioException;
       
    
    /**
	 * [UC0898] Atualizar Autos de Infração com prazo de Recurso Vencido
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 11/05/2009
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */

	public Collection pesquisarConsumoFaturadoQuantidadeMesesPorReferencia(Integer idImovel,
			Integer tipoMedicao, short qtdMeses)
			throws ErroRepositorioException;
	  /**
	 * Pesquisa Hidrometros de 1000 em 1000
	 * 
	 * 
	 * @author Hugo Amorim
	 * @date 10/06/2009
	 * 
	 */
	public Collection pesquisarNumeroHidrometroFaixaBatch(String faixaInicial,
			String faixaFinal, Integer qtd) throws ErroRepositorioException;
	
	/**
	 * 
	 * Método que retorna os consumos de ligacao da agua ou esgoto (tipo passado)
	 * de um imovel em um determinado período do faturamento. 
	 * 
	 * @author Rafael Corrêa
	 * @date 27/07/2009
	 * 
	 * @param idImovel
	 * @param anoMesInicial
	 * @param anoMesFinal
	 * @param idLigacaoTipo
	 *            podendo ser agua ou esgoto
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> obterConsumosAnterioresAnormalidadeDoImovel(Integer idImovel,
			Integer anoMesInicial, Integer anoMesFinal, Integer idLigacaoTipo)
			throws ErroRepositorioException;
	
	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 *
	 * @author Sávio Luiz
	 * @date 01/07/2009
	 *
	 * @param imovel
	 * @param anoMesReferencia
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarMaiorDataLeituraImoveis(Integer idImovel, Integer anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * [UC0101] - Consistir Leituras e Calcular Consumos
	 * 
	 * @author Raphael Rossiter
	 * @date 25/08/2009
	 * 
	 * @return Collection
	 * @exception ErroRepositorioException
	 */
	public Collection pesquisarImovelConsistirLeituraPorRotaAlternativa(Rota rota) 
		throws ErroRepositorioException ;
	
	/**
	 * Método que retorna os imoveis condominiais e esteja com ligados ou
	 * cortados a agua e ou ligados com esgoto que possuam hidrometro no poço
	 * das rotas passadas
	 * 
	 * [UC0103] Efetuar Rateio de Consumo
	 * 
	 * @author Raphael Rossiter
	 * @date 26/08/2009
	 * 
	 * @param idRota
	 * @return Imoveis
	 */
	public Collection pesquisarImovelCondominioParaCalculoDoRateioPorRotaAlternativa(Integer idRota) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * @author Raphael Rossiter
	 * @date 27/08/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImoveisPorRotaAlternativa(Rota rota, String empresa)
			throws ErroRepositorioException ;
	
	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 *
	 * [SB0006] - Obter dados dos tipos de medição
	 *
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 *
	 * @param imovel
	 * @param anoMesReferencia
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection obterDadosTiposMedicaoHidrometro(Imovel imovel) throws ErroRepositorioException ;
	
	/**
	 * [UC0811] Requisições do Dispositivo Móvel da Conta Pré-faturada.
	 * 
	 * SB0001 ? Baixar Arquivo Texto para o Leiturista
	 * 
	 * @author Bruno Barros
	 * @date 24/09/2009
	 * 
	 * @param imei
	 * @return
	 * @throws ControladorException
	 */
	public Object[] baixarArquivoTextoParaLeituristaImpressaoSimultanea(long imei,Integer idServicoTipoCelular)
			throws ErroRepositorioException;
	
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0002] Gerar Relação(ROL) em TXT
	 * 
	 * @author Raphael Rossiter
	 * @date 17/09/2009
	 */
	public boolean removerArquivoTextoRoteiroEmpresa(Integer anoMesCorrente, Integer idGrupoFaturamentoRota) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0006] Gerar Relação(ROL) em TXT - COSANPA
	 * 
	 * @author Rômulo Aurelio
	 * @date 02/07/2008
	 * 
	 * @param idRota
	 * @param idLeituraTipo
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImoveisParaLeituraPorColecaoRotaCOSANPA(Rota rota, int inicioPesquisa, Integer anoMes)
			throws ErroRepositorioException ;
	

	/**
	 * Pesquisa as rotas que possuem validos para geração do relatorio de
	 * acompanhamento de leiturista.
	 * 
	 * @author Hugo Amorim
	 * @data 16/10/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection selecionarRotasRelatorioAcompanhamentoLeiturista(
			Integer anoMesReferencia,
			Integer rotas[],
			String idEmpresa,
			String idLeiturista)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Hugo Amorim
	 * @data 19/10/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioAcompanhamentoLeiturista(
			RelatorioAcompanhamentoLeituristaHelper helper  ,Integer idRota ,Integer idLeiturista)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Hugo Amorim
	 * @data 19/10/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarLeituristasDasRotas(
			RelatorioAcompanhamentoLeituristaHelper helper)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Hugo Amorim
	 * @data 19/10/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosResumoLeituraAgua(
			RelatorioAcompanhamentoLeituristaHelper helper)
			throws ErroRepositorioException;
	/**
	 * 
	 * @author Hugo Amorim
	 * @data 19/10/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosResumoLeituraPoco(
			RelatorioAcompanhamentoLeituristaHelper helper)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Hugo Amorim
	 * @data 19/10/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosFaturadosPelaMedia(
			RelatorioAcompanhamentoLeituristaHelper helper)
			throws ErroRepositorioException;
	/**
	 * 
	 * @author Hugo Amorim
	 * @data 19/10/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosFaturadosPeloMinimo(
			RelatorioAcompanhamentoLeituristaHelper helper)
			throws ErroRepositorioException;
	/**
	 * 
	 * @author Hugo Amorim
	 * @data 19/10/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosConsumoTotalMedido(
			RelatorioAcompanhamentoLeituristaHelper helper)
			throws ErroRepositorioException;
	/**
	 * 
	 * @author Hugo Amorim
	 * @data 19/10/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosConsumoTotalFaturado(
			RelatorioAcompanhamentoLeituristaHelper helper)
			throws ErroRepositorioException;
	
	/**
	 *[UC0965] - Relatorio de Anormalidade de Leitura por Periodo
	 *
	 *@since 03/11/2009
	 *@author Marlon Patrick
	 */
	public  Collection<Object[]> pesquisarRelatorioAnormalidadeLeituraPeriodo(FiltrarRelatorioAnormalidadeLeituraPeriodoHelper filtro)throws ErroRepositorioException;

	/**
	 *[UC0965] - Relatorio de Anormalidade de Leitura por Periodo
	 *
	 *@since 03/11/2009
	 *@author Marlon Patrick
	 */
	public  Collection<Object[]> pesquisarTotalRegistrosRelatorioAnormalidadeLeituraPeriodo(FiltrarRelatorioAnormalidadeLeituraPeriodoHelper filtro)throws ErroRepositorioException;
	
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * @author Hugo Amorim	
	 * @date 18/11/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImoveisPorRotaAlternativaCAER(Rota rota, String empresa)
			throws ErroRepositorioException;
	/**
	 * [UC0082] - Registrar Leituras e Anormalidades
	 * 
	 * [SB0001] - Gerar Relatório Resumo das Leituras e Anormalidades
	 * 
	 * Pesquisa a quantidade de dados do relatório do comparativo de leituras e anormalidades
	 * 
	 * @author Arthur Carvalho  - Hugo Leonardo      - Magno Gouveia
	 * @date 16/11/2009			- 22/03/2010		 - 21/06/2011
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarDadosRelatorioComparativoLeiturasEAnormalidadesCount(Integer idGrupoFaturamento,
			Integer idEmpresa, Integer anoMes, 
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idSetorInicial, Integer idSetorFinal, 
			Integer idGerencia, Integer idUnidadeNegocio, Integer idLeiturista, 
			Integer idRotaInicial, Integer idRotaFinal,
			Integer idPerfilImovel, Integer numOcorrenciasConsecutivas, Collection colecaoAnormalidadesLeituras,
			SistemaParametro sistemaParametro)
			throws ErroRepositorioException;
	
	/**
	 * [UC0038] Inserir Rota 
	 * [UC0039]Manter Rota
	 *
	 * @author Rafael Pinto
	 * @date 31/08/2009
	 *
	 * @param idRota
	 * @param idFaturamentoGrupo
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarComandoNaoRealizadoParaRota(Integer idRota,Integer idFaturamentoGrupo)
		throws ErroRepositorioException ;
	
	/**
	 * 
	 * Verifica se houve todos os processamentos do um determinado grupo e
	 * ano-Mês de Referencia e inserir no cronograma de atividades.
	 * 
	 * [UC0840] - Atualizar Faturamento do Movimento Celular 
	 * 
	 * @author Sávio Luiz
	 * @date 01/12/2009
	 * 
	 * @param grupo
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 */
	public void atualizarDataRealizacaoGronogramaPreFaturamento(Integer grupo,
			Integer anoMesReferencia,Date dataRealizacao) throws ErroRepositorioException;
	
	/**
	 * [UC0101] Consistir Leituras e Calcular Consumos
	 * 
	 * [SB0014] - Verificar Estouro de Consumo
	 * 
	 * @author Vivianne Sousa
	 * @date 17/12/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public ConsumoAnormalidadeAcao pesquisarConsumoAnormalidadeAcao(
			Integer idConsumoAnormalidade, Integer idCategoria, Integer idPerfilImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UC0101] Consistir Leituras e Calcular Consumos
	 * 
	 * [SB0014] - Verificar Estouro de Consumo
	 * 
	 * @author Vivianne Sousa
	 * @date 17/12/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarConsumoHistoricoConsumoAnormalidade(
			Imovel imovel, LigacaoTipo ligacaoTipo, int anoMesReferencia, 
			Integer idConsumoAnormalidade)throws ErroRepositorioException;
	
	/**
	 * Método que retorna os dados necessários
	 * para efetuar o rateio de um imovel condominio
	 * e seus imoveis vinculados
	 * 
	 * [UC0103] Efetuar Rateio de Consumo
	 * 
	 * @author Bruno Barros
	 * @date 29/12/2009
	 * 
	 * @param idImovel id do imovel condominio
	 * @return Imovel Encapsulado dentro da colação os dados retornados
	 */
	public Collection pesquisarImovelCondominio( Integer idImovel ) 
		throws ErroRepositorioException;
	
	/**
	 * 
	 * Buscar Rota a partir da Matrícula de um Imóvel e anoMesFaturamentoGrupo.
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 03/02/2010
	 * 
	 * @param dados
	 * 
	 * @throws ControladorException
	 */
	public Rota buscarRotaPorMatriculaImovel(Integer imovel, Integer anoMesFaturamentoGrupo) throws ErroRepositorioException;
	
	/**
	 * Pesquisa Arquivos que estão na situação passada como parametro 
	 * para serem gerando novamente.
	 * 
	 * @author Hugo Amorim
	 * @date 08/02/2010
	 * 
	 * @param anoMesReferencia
	 * @param idRota
	 * @param idGrupoFaturamento
	 * @throws ErroRepositorioException
	 */
	public ArquivoTextoRoteiroEmpresa pesquisaArquivoTextoParaLeituristaPorRota(
			Integer anoMesReferencia, Integer idRota, Integer idGrupoFaturamento)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa Arquivos que estão na situação passada como parametro 
	 * para serem gerando novamente.
	 * 
	 * @author Hugo Amorim
	 * @date 08/02/2010
	 * 
	 * @param anoMesReferencia
	 * @param idRota
	 * @param idGrupoFaturamento
	 * @throws ErroRepositorioException
	 */
	public ArquivoTextoRoteiroEmpresa pesquisaArquivoTextoParaLeituristaPorRoteiroEmpresa(
			Integer anoMesReferencia, Integer idRoteiroEmpresa, Integer idGrupoFaturamento)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa Arquivos que estão na situação passada como parametro 
	 * para serem gerando novamente.
	 * 
	 * @author Hugo Amorim
	 * @date 08/02/2010
	 * 
	 * @param anoMesReferencia
	 * @param idRota
	 * @param idGrupoFaturamento
	 * @throws ErroRepositorioException
	 */
	public ArquivoTextoRoteiroEmpresa pesquisaArquivoTextoParaLeiturista(
			Integer anoMesReferencia, Integer idGrupoFaturamento)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisar ultima anormalidade de leitura do imovel
	 * 
	 * @author Hugo Amorim
	 * @date 19/02/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public String pesquisarAnormalidadesImovel(
			Integer idImovel,String indicadorAguaEsgoto)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * Método que retorna os tipos de consumos de um determinado consumo historico
	 * 
	 * @author Tiago Moreno
	 * @date 25/02/2010
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @param idLigacaoTipo
	 *            podendo ser agua ou esgoto
	 * @return consumoTipo - descricaoAbreviada
	 * @throws ErroRepositorioException
	 */
	public String obterConsumoTipoImovel(Integer idImovel, Integer anoMes, Integer idLigacaoTipo)
			throws ErroRepositorioException;
	/**
	 * Verificar quais rotas ainda faltam ser 
	 * transmitidas pela impressao simultanea 
	 * 
	 * @author Rafael Pinto
	 * @date 01/03/2010
	 * 
	 * @param anoMesReferencia
	 * @param idGrupoFaturamento
	 * @throws ErroRepositorioException
	 */
	public Collection<Rota>  pesquisaRotasNaoTransmitidas(
		Integer anoMesReferencia, Integer idGrupoFaturamento)
		throws ErroRepositorioException ;
	
	/**
	 * Método que retorna as datas de leituas anteriores e atuais.
	 * 
	 * @author Tiago Moreno
	 * @date 25/02/2010
	 * 
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Object[] obterLeituraAnteriorEAtual(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException;

	/**
	 * 
	 * Pesquisa numero de hidrometro da ligacao de agua do imovel
	 *
	 * @author Hugo Amorim
	 * @date 04/03/2010
	 * @param Id Imovel
	 * @return Numero do Hidrometro
	 * @throws ErroRepositorioException
	 */
	public String pesquisarNumeroHidrometro(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC0820] Atualizar Faturamento do Movimento Celular
     * [SB002] Incluir Medicao
     * 
     * Método criado para atualizar apenas os campos necessários para
     * medição histórico.
     * 	 
     * @author Bruno Barros
     * @date 31/03/2010
     * @param medicaoHistorico
	 * @throws ErroRepositorioException
	 */
	public void atualizarMedicaoHistoricoProcessoMOBILE( MedicaoHistorico medicaoHistorico ) 
		throws ErroRepositorioException;

	/**
	 * [UC1000] Informar Medidor de Energia por Rota.
	 * 
	 * Obtém a quantidade de imoveis de acordo com o filtro.
	 * 
	 * @author Hugo Leonardo
	 * @date 12/03/2010
	 * 
	 * @param FiltrarRelatorioColetaMedidorEnergiaHelper
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarTotalRegistroColetaMedidorEnergia(String idLocalidade, String idSetorComercial,
			String rota, String tipo) throws ErroRepositorioException;
	
    /**
	 * [UC1000] Informar Medidor de Energia por Rota.
	 * 
	 * @author Hugo Leonardo
	 * @date 12/03/2010
	 * 
	 * @param ColetaMedidorEnergiaHelper
	 * 
	 * @return Collection<ColetaMedidorEnergiaHelper>
	 * @throws ControladorException
	 */
	public Collection<ColetaMedidorEnergiaHelper> pesquisarColetaMedidorEnergia(
			String idLocalidade, String idSetorComercial, String rota, String tipo) throws ErroRepositorioException;
	/**
	 * [UC0631] Processar Requisições do Dispositivo Móvel.
	 * 
	 * Atualizar Situação do Arquivo Texto.
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 05/04/2010
	 * 
	 * @param imei
	 * @param situacaoAnterior
	 * @param situacaoNova
	 * @throws ErroRepositorioException
	 */
	public void atualizarArquivoTextoMenorSequencialLeitura(long imei, int situacaoAnterior,
			int situacaoNova,int idServicoTipoCelular) throws ErroRepositorioException;
    /**
	 * [UC0997] Gerar Resumo de Ligações por Capacidade de Hidrômetro.
	 * 
	 * @author Hugo Leonardo
	 * @date 30/03/2010
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Collection pesquisarRelatorioResumoLigacoesCapacidadeHidrometro(
			RelatorioResumoLigacoesCapacidadeHidrometroHelper helper, String tipoTotalizacao) 
		throws ErroRepositorioException;
	
    /**
	 * [UC0997] Gerar Resumo de Ligações por Capacidade de Hidrômetro.
	 * 
	 * @author Hugo Leonardo
	 * @date 30/03/2010
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer countRelatorioResumoLigacoesCapacidadeHidrometro(
			RelatorioResumoLigacoesCapacidadeHidrometroHelper helper ) 
		throws ErroRepositorioException;

	/**
	 * 
	 * [UC0091] Alterar Dados para Faturamento
	 * 
	 * 	[FS0015]  Verificar Imóvel Impressão Simultânea
	 * 
	 * @author Hugo Amorim
	 * @date 08/04/2010
	 */
	public boolean verificarExistenciaArquivoDeImpressao(Integer idImovel,
			Integer tipoMedicao)throws ErroRepositorioException;
	
	/**
	 * Pesquisa os dados de Medição e Consumo Historico da ligação de Esgoto do imóvel.
	 * 
	 * @author Arthur Carvalho
	 * 
	 * @date 07/05/2010
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection carregarDadosMedicaoConsumoHistoricoLigacaoEsgoto(Integer idImovel) 
		throws ErroRepositorioException;

	
	/**
	 * @author Arthur Carvalho
	 * @date 25/05/2010
	 */
	public Integer pesquisarMedicaoTipo(Integer idImovel, Integer anoMesReferencia)
		throws ErroRepositorioException;


	
	/**
	 * [UC1022] Relatório de Notificação de Débitos para Impressão Simultânea	 
	 * 
	 * @author Daniel Alves
	 * @date 19/05/2010
	 */
	public Collection pesquisarNotificacaoDebitosImpressaoSimultanea(
			RelatorioNotificacaoDebitosImpressaoSimultaneaHelper filtro)throws ErroRepositorioException;
	
	/**
	 * @author Arthur Carvalho
	 * @date 25/05/2010
	 */
	public HidrometroInstalacaoHistorico verificaExistenciaDeHidrometroInstalado(Integer idImovel, 
			MedicaoTipo medicaoTipo) throws ErroRepositorioException;
	
	/**
	 * Pesquisar Capacidade do Hidrometro	 
	 * 
	 * @author Arthur Carvalho
	 * @date 17/06/2010
	 */
	public HidrometroCapacidade pesquisarCapacidadeHidrometro(String numeroHidrometro)
		throws ErroRepositorioException;

	/**
     * UC1027 - Exibir Consultar Arquivo Texto Leitura Divisão.
     *  
     * @author Hugo Leonardo
     * @created 04/06/2010
	 * 
	 * @param idArquivoTextoRoteiroEmpresaDivisao
	 * @param situacaoNova
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarArquivoTextoDivisaoEnviado(Integer idArquivoTextoRoteiroEmpresaDivisao, int situacaoNova) 
		throws ErroRepositorioException;
		
	/**
	 * UC1027 - Exibir Consultar Arquivo Texto Leitura Divisão.
     *  
     * @author Hugo Leonardo
     * @created 04/06/2010
	 * 
	 * @param ids
	 * @param situacaoNova
	 * 
	 * @param id
	 * @param situacaoNova
	 * @throws ErroRepositorioException
	 */
	public void atualizarArquivoTextoDivisao(Integer id, Integer situacaoNova)
			throws ErroRepositorioException;
	
	/**
	 * [UC0103] - Efetuar Rateio de Consumo
	 *
	 * @author Raphael Rossiter
	 * @date 01/07/2010
	 *
	 * @param imovel
	 * @return RateioTipo
	 * @throws ErroRepositorioException
	 */
	public RateioTipo obterDadosRateioTipoParaLigacaoAgua(Imovel imovel)
			throws ErroRepositorioException ;
	
	/**
	 *	CRC 4821
	 * @author Arthur Carvalho
	 * @date 14/07/2010
	 *
	 * @throws ErroRepositorioException
	 */
	public boolean verificaExistenciaHidrometro(Integer idImovel)
			throws ErroRepositorioException ;
	
	/**
	 * Remover todos os Itens de Servico do Contrato
	 * [UC1055] - Informar Valor de Item de Serviço Por Contrato
	 * 
	 * @author Hugo Leonardo
	 * @date 03/08/2010
	 * 
	 * @param idContratoEmpresaServico
	 * @return void
	 * @throws ErroRepositorioException
	 */
	public void removerItemServicoDoContrato(Integer idContratoEmpresaServico) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1054] - Gerar Relatório Boletim de Medição
	 * 
	 * @author Hugo Leonardo
	 * @date 05/08/2010
	 * 
	 * @param FiltrarRelatorioBoletimMedicaoHelper
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioBoletimMedicao( FiltrarRelatorioBoletimMedicaoHelper helper) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1054] - Gerar Relatório Boletim de Medição
	 * 
	 * @author Hugo Leonardo
	 * @date 06/08/2010
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarEmpresasContratoServico( ) throws ErroRepositorioException;
    
    /**
     * Exclue todos os registros da tabela de atualização 
     * de sequencial de rota
     *  
     * @author bruno
     * @date 09/08/2010
     * 
     * @param anoMesReferencia
     * @param idRota
     * @throws ErroRepositorioException
     */
    public void deletarRotaAtualizacaoSequencial( 
            Integer anoMesReferencia, Integer idRota ) throws ErroRepositorioException;
    
    
    /**
	 * 
	 * Obter rota do imóvel através do código da rota
	 * do sequencial e da localidade .
	 * 
	 * @author Breno Santos
	 * @date 13/08/2010
	 * 
	 * @param codRota, sequencial, localidade
	 * @throws ErroRepositorioException
	 */
	public Integer obterIdRotaPorSetorComercialELocalidade(Integer codRota, Integer setorComercial, Integer localidade)
			throws ErroRepositorioException;

    
    /**
     * 
     * Seleciona todos os imóveis de uma determinada
     * rota / ano mes.
     * 
     * @author Bruno Barros
     * @date 11/08/2010
     *  
     * @param idRota - Id da rota 
     * @param anoMesReferencia - Ano Mes de referencia do Grupo de faturamento
     * @throws ControladorException
     */
    public Collection<RotaAtualizacaoSeq> pesquisarRotaAtualizacao( Integer idRota, Integer anoMesReferenciaGrupoFaturamento )
            throws ErroRepositorioException;    

    
	/**
	 * 
 	 * [UC0629] Consultar Arquivo Texto para Leitura
 	 * 	
 	 * 	[FS0011 - Verificar Leituras];
 	 * 
	 * @author Hugo Amorim
	 * @date 20/08/2010
	 */
	public Collection pesquisarSituacaoLeitura(Integer anoMes,Integer idGrupo,Integer idRota)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * Verifica se uma rota em questão está com
	 * os arquivos de impressão simultanea divididos
	 * 
	 * @autor Bruno Barros.
	 * @date 26/08/2010 
	 * 
	 * @param idRota - Id da rota a ser pesquisada
	 * 
	 * @return boolean - A rota está dividida 
	 */
	public boolean isRotaDividida( Integer idRota, Integer anoMesFasturamento ) throws ErroRepositorioException;
	
	/**
	 * Verifica de imovel é medido ou não-medido
	 * 
	 * 	retorna true se medido
	 * 			false se não-medido
	 * 
	 * @author Hugo Amorim
	 * @date 26/08/2010
	 */
	public boolean verificarSituacaoMedicao(Integer idImovel)throws ErroRepositorioException;
	
	/**
	 * Verificar se a leitura de para impressão
	 * simultanea de um determinado imóvel/ano mes chegou
	 * 
	 * 
	 * 	retorna 
	 * 		true Se chegou
	 * 		false Se não chegou
	 * 
	 * @author Bruno Barros
	 * @date 31/08/2010
	 * 
	 * @param String matricula - Matricula do imóvel a ser pesquisado
	 * @param Integer anoMesFaruramento - Ano mes do faturamento a ser pesquisado
	 */
	public boolean verificarExistenciaLeituraImpressaoSimultanea(String matricula,
			Integer anoMesFaturamento) throws ErroRepositorioException;
	
	/**
	 * 
	 * Verifica quais os imóveis para uma determinada
	 * cujo as releituras foram solicitadas, e que 
	 * ainda não foram enviadas, para um determinado
	 * ano mes de referencia
	 * 
	 * @author Bruno Barros
	 * @date 01/09/2010
	 * 
	 * @param idRota - Id da rota a ser pesquisada
	 * @param anoMesReferencia - Ano mes de referencia
	 * 
	 * @return Collection<ReleituraMobile> - Coleção com os registros
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<ReleituraMobile> pesquisarImoveisReleituraMobileSolicitada( Integer idRota, Integer anoMesReferencia )
		throws ErroRepositorioException;	
	
	/**
	 * [UC0936] Informar Leitura por Rota 
	 * 
	 * @author Hugo Amorim
	 * @date 30/08/2010
	 * 
	 */
	public ArquivoTextoRoteiroEmpresa pesquisarArquivosTextoRoteiroEmpresa(
			Integer idLocalidade,Integer idRota, Integer idGrupo, Integer anoMesReferencia) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1004] Processar Leitura via Telemetria
	 * 
	 * [SB0002] – Validar Dados Leitura
	 *
	 * @author Raphael Rossiter
	 * @date 09/09/2010
	 *
	 * @param idImovel
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarImovelTelemetria(Integer idImovel) throws ErroRepositorioException ;
	
	/**
	 * 
	 * Pesquisa os imóveis para a tela de Filtrar Imoveis para Releitura
	 * 
	 * @author Bruno Barros
	 * @date 14/09/2010
	 * 
	 * @param anoMesReferencia 
	 * @param idGrupoFaturamento
	 * @param idRota
	 * @param idQuadra
	 * @param idSituacaoTrasmissaoLeitura
	 * @return
	 */
	public Collection<ReleituraMobile> pesquisarImovelParaReleitura(
			String anoMesReferencia,
			String idGrupoFaturamento,
			String idRota,
			String idQuadra,
			String idEmpresa) throws ErroRepositorioException;
	
	/**
	 * [UC0113] Faturar Grupo de Faturamento
	 * [SB0006] - Gerar Dados da Conta 
	 * 
	 * @author Vivianne Sousa
	 * @date 20/09/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public  Object[] obterLeituraAnteriorEAtualFaturamentoMedicaoHistorico(
			Integer idImovel, Integer anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0629] Consultar Arquivo Texto para Leitura
	 * 
	 * @author Bruno Barros
	 * @date 27/09/2010
	 *
	 */
	public Collection<Object[]>  pesquisarImoveisFaltandoSituacaoLeitura(
			Integer idRota, Integer anoMesReferencia ) throws ErroRepositorioException;
	
	/**
	 * [UC0811] Requisições do Dispositivo Móvel da Conta Pré-faturada.
	 * 
	 * SB0001 - Baixar Arquivo Texto para o Leiturista
	 * 
	 * @author Sávio Luiz
	 * @date 29/09/2010
	 * 
	 * @param imei
	 * @return
	 * @throws ControladorException
	 */
	public Object[] baixarArquivoTextoDivididoParaLeituristaImpressaoSimultanea(long imei)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * Verifica se uma rota em questão está com
	 * os arquivos de impressão simultanea divididos com a 
	 * situação diferente da situação enviada como parâmetro 
	 * 
	 * @autor Sávio Luiz.
	 * @date 30/09/2010 
	 * 
	 * @param idRota - Id da rota a ser pesquisada
	 * 
	 * @return boolean - A rota está dividida 
	 */
	public boolean verificarExistenciaArquivosDivididosSituacaoDiferente( Integer idRota, Integer anoMesFaturamento, Integer[] idsSituacaoTransmissao ) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0932] Monitorar Leituras Transmitidas
	 * 
	 * Pesquisa as leituras que foram trasmitidas.
	 * 
	 * @author Bruno Barros
	 * @date 28/09/2010
	 * 
	 * @param idRota
	 * @param anoMesReferencia
	 * @param indicadorContaImpressa
	 * @param indicadorMedido
	 * @return Colecao com as informações necessarias para o prenchimento da tela.
	 * 
	 */
	public Collection<Object[]> pesquisarImoveisMonitorarLeiturasTransmitidas(
		Integer idRota, Integer anoMesReferencia, Short indicadorContaImpressa, Short indicadorMedido ) throws ErroRepositorioException;	

	/**
	 * [UC0933] Alterar Leiturista do Arquivo Texto para Leitura
	 * 
	 * @author Tiago Nascimento, Rômulo Aurélio
	 * @Data ??/??/????,  27/10/2010
	 *
	 */
	public void atualizarLeituristaMovimentoRoteiroEmpresa(Integer idGrupoFaturamento, Integer anoMesReferencia, 
			Leiturista  leiturista, ArquivoTextoRoteiroEmpresa arq)	throws ErroRepositorioException;
	
	/**
	 * [UC1004] Processar Leitura via Telemetria 
	 *
	 * @author Raphael Rossiter
	 * @date 27/09/2010
	 *
	 * @param telemetriaLog
	 * @throws ErroRepositorioException
	 */
	public void atualizarTelemtriaLogMovimentoRejeitado(TelemetriaLog telemetriaLog) throws ErroRepositorioException ;
	
	/**
 	 * [UC1070] Filtrar Leituras Telemetria
 	 * 
 	 * @author Hugo Amorim
 	 * @date 27/09/2010
 	 * 
 	 */
	public Collection<TelemetriaMovReg> filtrarLeiturasTelemetria(
			FiltrarLeiturasTelemetriaHelper helper)throws ErroRepositorioException;
	
	/**
 	 * [UC1070] Filtrar Leituras Telemetria
 	 * 
 	 * @author Hugo Amorim
 	 * @date 27/09/2010
 	 * 
 	 */
	public Integer countFiltrarLeiturasTelemetria(
			FiltrarLeiturasTelemetriaHelper helper)throws ErroRepositorioException;
	
	/**
 	 * [UC1070] Filtrar Leituras Telemetria
 	 * 
 	 * @author Hugo Amorim
 	 * @date 27/09/2010
 	 * 
 	 */
	public boolean verificarLeiturasTelemetriaNaoProcessadas(
			FiltrarLeiturasTelemetriaHelper helper)throws ErroRepositorioException;
	
	/**
 	 * [UC1070] Filtrar Leituras Telemetria
 	 * 
 	 * @author Hugo Amorim
 	 * @date 27/09/2010
 	 * 
 	 */
	public boolean verificarGruposDiferentesLeiturasTelemetria(
			String[] ids)throws ErroRepositorioException;
	
	/**
 	 * [UC1070] Filtrar Leituras Telemetria
 	 * 
 	 * @author Hugo Amorim
 	 * @date 27/09/2010
 	 * 
 	 */
	public Collection<TelemetriaMovReg> perquisarLeiturasTelemetriaPorId(
			String[] ids)throws ErroRepositorioException;
	
	/**
	 * 
	 * UC1027 - Exibir Consultar Arquivo Texto Leitura Divisão.
	 * 
	 * Avalia se existe algum arquivo dividido liberado para o leiturista do arquivo
	 * passado no parametro.
	 * 
	 * @autor Sávio Luiz
	 * @date 04/10/2010
	 * 
	 * @param idArquivo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] temArquivoTextoDivididoLiberado(Integer idLeiturista)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * UC1027 - Exibir Consultar Arquivo Texto Leitura Divisão.
	 * 
	 * Avalia se existe algum arquivo dividido liberado para o leiturista do arquivo
	 * passado no parametro.
	 * 
	 * @autor Sávio Luiz
	 * @date 04/10/2010
	 * 
	 * @param idArquivo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] temArquivoTextoLiberado(Integer idLeiturista)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * UC1027 - Exibir Consultar Arquivo Texto Leitura Divisão.
	 * 
	 * Pesquisa o leiturista do arquivo que será liberado.
	 * 
	 * @autor Sávio Luiz
	 * @date 04/10/2010
	 * 
	 * @param idArquivo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer recuperaLeituristaArquivoTexto(Integer idArquivo,boolean arquivoDividido)
			throws ErroRepositorioException;
	
	/**
	 * [UCXXXX] - Obter Volume Médio Água ou Esgoto
	 * 
	 * @author Ivan Sergio
	 * @data 13/12/2010
	 * 
	 * @param idImovel
	 * @param amReferenciaInicial
	 * @param amReferenciaFinal
	 * @param idLigacaoTipo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterVolumeMedioAguaOuEsgoto(
			Integer idImovel, 
			Integer amReferenciaInicial, 
			Integer amReferenciaFinal, 
			Integer idLigacaoTipo) throws ErroRepositorioException;

	/**
	 * 
	 * [UC0091] Alterar Dados para Faturamento
	 * 
	 * 	[FS0015]  Verificar Imóvel Impressão Simultânea
	 * 
	 * @author Rômulo Aurélio
	 * @date 08/11/2010
	 */
	public boolean verificarExistenciaArquivoDeImpressaoRotaAlternativa(Integer idImovel,
			Integer tipoMedicao)throws ErroRepositorioException;
	
	/**
	 * 
	 * Obter rota do imóvel através do código da rota
	 * do sequencial e da localidade .
	 * 
	 * @author Sávio Luiz
	 * @date 13/08/2010
	 * 
	 * @param codRota, sequencial, localidade
	 * @throws ErroRepositorioException
	 */
	public Integer obterIdRotaDeMovimentoRotEmpresaPelaLocalidade(Integer codRota, Integer setorComercial, Integer localidade)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC-1127] - Gerar RA e OS para Anormalidade Consumo
	 * Passo 2
	 * 
	 * @param anoMesFaturamento
	 * @param colRotas
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection selecionarImoveisAnormalidadeConsumoGeradoraRA( int anoMesFaturamento, Rota rota ) 
		throws ErroRepositorioException;
	
	/**
	 * [UC-1127] - Gerar RA e OS para Anormalidade Consumo
	 * 
	 * Pesquisamos a existencia de Consumo Anormalidade Acao para o imovel informado
	 * Corresponde ao passo 3 e ao FS0004 do UC
	 * 
	 * @author Bruno Barros
	 * @date 16/02/2011
	 * 
	 * @param idCategoria
	 * @param idPerfilImovel
	 * @return
	 * @throws ControladorException
	 */	
	public Collection 
		verificarExistenciaConsumoAnormalidadeAcaoImovel(
				Integer idConsumoAnormalidadeAcao,
				Integer idCategoria,
				Integer idPerfilImovel ) 
			throws ErroRepositorioException;
	
	/**
	 * [UC-1127] - Gerar RA e OS para Anormalidade Consumo
	 * 
	 * atendimentopublico.LOCALID_SOLIC_TIPO_GRUPO para LOCA_ID=LOCA_ID 
	 * da tabela cadastro.IMOVEL para IMOV_ID=IMOV_ID da tabela 
	 * micromedicao.CONSUMO_HISTORICO e SOTG_ID=SOTG_ID da tabela 
	 * atendimentopublico.SOLICITACAO_TIPO para SOTP_ID=SOTP_ID da 
	 * tabela atendimentopublico.SOLICITACAO_TIPO_ESPEC para 
	 * STEP_ID=Especificação do Tipo de Solicitação
	 * 
	 * @author Bruno Barros
	 * @date 16/02/2011
	 * 
	 * @param idLocalidade
	 * @param idSolicitacaoTipoGrupo
	 * 
	 * @return
	 * @throws ControladorException
	 */	
	public UnidadeOrganizacional
		unidadeDestinoAssociadaLocalidadeImovelGrupoTipoSolicitacaoEspecificacao(
				Integer idLocalidade,
				Integer idSolicitacaoTipoGrupo ) 
			throws ErroRepositorioException;	
	
	/**
	 * 
	 * Recuperar os ids dos imoveis vinculados a um determinado condominio
	 * 
	 * @author Tiago Moreno
	 * @date 22/02/2010
	 * 
	 * @param idImovelCondominio
	 * @throws ErroRepositorioException
	 */
	
	public Collection recuperarImoveisVinculadosAoCondominio(
			Integer idImovelCondominio) throws ErroRepositorioException;
	
	/**
	 * 
	 * [F0011] Verifica a existenica de imovel micro em processo de faturamento
	 * 
	 * @author Tiago Moreno
	 * @date 22/02/2010
	 * 
	 * @param idImoveis, anoMes
	 * @throws ErroRepositorioException
	 */
	
	public Collection verificarImoveisCicloFaturamento(
			Collection idImoveis, Integer anoMes) throws ErroRepositorioException;
	
	/**
	 * Pesquisa do relatorio consultar arquivo texto leitura
	 * 
	 * @author Rafael Pinto
	 * @date 11/03/2011
	 * 
	 * @param FiltroRelatorioLeituraConsultarArquivosTextoHelper
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> consultarRelatorioLeituraConsultarArquivosTexto(
			FiltroRelatorioLeituraConsultarArquivosTextoHelper helper)
		throws ErroRepositorioException ;
	
	/**
	 * Pesquisa do relatorio consultar arquivo texto leitura
	 * 
	 * @author Rafael Pinto
	 * @date 11/03/2011
	 * 
	 * @param FiltroRelatorioLeituraConsultarArquivosTextoHelper
	 * @throws ErroRepositorioException
	 */
	public Integer consultarCountRelatorioLeituraConsultarArquivosTexto(
			FiltroRelatorioLeituraConsultarArquivosTextoHelper helper)
		throws ErroRepositorioException ;
	
    /**
	 * Obter rota do imóvel através do intervalor de localidade,setor e numero da quadra .
	 * 
	 * @author Rafael Pinto
	 * @date 16/05/2011
	 * 
	 * @param localidadeInicial, localidadeFinal,
			codigoSetorComercialIncial,codigoSetorComercialFinal,
			numeroQuadraInicial,numeroQuadraFinal
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> obterIdRotaPorQuadra(
		Integer localidadeInicial,
		Integer localidadeFinal,
		Integer codigoSetorComercialIncial, 
		Integer codigoSetorComercialFinal,
		Integer numeroQuadraInicial, 
		Integer numeroQuadraFinal)
		throws ErroRepositorioException ;

	/**
	 * [UC0800] - Obter Consumo Não Medido
	 *
	 * [SB0005] - Determinar consumo mínimo da subcategoria por pontos de utilização
	 * 
	 * Obter o consumo mínimo associado ao ponto de utilização e a subcategoria informada
	 *
	 * @author Mariana Victor
	 * @date 23/05/2011
	 * 
	 * @param areaConstruida
	 * @param anoMesReferencia
	 * @param idSubcategoria
	 * @param idCategoria
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarConsumoMinimoParametro(BigDecimal numeroParametro,
			Integer anoMesReferencia, Integer idSubcategoria,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * [UC0000] Obter Consumo Não Medido por Parâmetro
	 *
	 * Obter o consumo mínimo associado ao ponto de utilização e a subcategoria informada
	 *
	 * @author Mariana Victor
	 * @date 23/05/2011
	 * 
	 * @param pontosUtilizacao
	 * @param anoMesReferencia
	 * @param idSubcategoria
	 * @param idCategoria
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarConsumoMinimoParametro(Short pontosUtilizacao,
			Integer anoMesReferencia, Integer idSubcategoria,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * [UC0000] Obter Consumo Não Medido por Parâmetro
	 *
	 * 2.1.1. , 2.2.1. - Caso exista consumo mínimo da subcategoria 
	 * 
	 * @author Mariana Victor
	 * @date 23/05/2011
	 * 
	 * @param pontosUtilizacao
	 * @param anoMesReferencia
	 * @param idSubcategoria
	 * @param idCategoria
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarConsumoMinimoSubCategoria(Integer idSubcategoria) throws ErroRepositorioException;

	/**
	 * <b>[UC1180] Relatório Imóveis com Leituristas</b>:
	 *
	 * <ul>
	 * 		<li> 
	 * 			<b>[SB0001] Gerar Relatório do Tipo 1</b>: Quantitativo de imóveis com leituras através da WEB
	 * 		</li>
	 * 		<li> 
	 * 			<b>[SB0002] Gerar Relatório do Tipo 2</b>: Quantitativo de imóveis sem leituras através da ISC e WEB
	 * 		</li>
	 * 		<li> 
	 * 			<b>[SB0003] Gerar Relatório do Tipo 3</b>: Quantitativo de imóveis que estão na rota mas não foram recebidos através da ISC</p>
	 * 		</li>
	 * </ul>
	 * 
	 * @author Magno Gouveia
	 * @date 06/06/2011
	 * 
	 * @param helper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> filtrarRelatorioImoveisComLeiturasQuantitativos(FiltrarRelatorioImoveisComLeiturasHelper helper, int parametroPersistirRelatorio) throws ErroRepositorioException;
	
	/**
	 * <b>[UC1180] Relatório Imóveis com Leituristas</b>:
	 *
	 * <ul>
	 * 		<li> 
	 * 			<b>[SB0004] Gerar Relatório do Tipo 4</b>: Relação de imóveis com leituras não recebidas através da ISC</b>
	 * 		</li>
	 * 		<li> 
	 * 			<b>[SB0005] Gerar Relatório do Tipo 5</b>: Relação de imóveis não medidos que não estão na rota de ISC</b>
	 * 		</li>
	 * 		<li> 
	 * 			<b>[SB0006] Gerar Relatório do Tipo 6</b>: Relação de imóveis medidos que não estão na rota de ISC</b>
	 * 		</li>
	 * </ul>
	 * 
	 * @author Magno Gouveia
	 * @date 10/06/2011
	 * 
	 * @param helper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> filtrarRelatorioImoveisComLeiturasRelacao(FiltrarRelatorioImoveisComLeiturasHelper helper, int parametroPersistirRelatorio) throws ErroRepositorioException;
	
	/**
	 * <b>[UC1180] Relatório Imóveis com Leituristas</b>:
	 *
	 * <ul>
	 * 		<li> 
	 * 			<b>[SB0007] Gerar Relatório do Tipo 7</b>: Quantitativo de imóveis com leituras enviado e recebidos</b>
	 * 		</li>
	 * </ul>
	 * 
	 * @author Magno Gouveia
	 * @date 13/06/2011
	 * 
	 * @param helper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> filtrarRelatorioImoveisComLeiturasTipo7(FiltrarRelatorioImoveisComLeiturasHelper helper) throws ErroRepositorioException;
	
	/**
	 * [UC0713] Emitir Ordem de Serviço Seletiva
	 * [SB0002] Gerar TXT 
	 * 
	 * @author Vivianne Sousa
	 * @date 29/06/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarUltimoConsumoFaturadoImovel(Integer idImovel,Integer tipoLigacao)
			throws ErroRepositorioException ;
	
	/**
	 * [UC0713] Emitir Ordem de Serviço Seletiva
	 * [SB0002] Gerar TXT 
	 * 
	 * @author Vivianne Sousa
	 * @date 29/06/2011
	 * 
	 * @param idImovel
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosHidrometro(Integer idImovel)
			throws ErroRepositorioException ;
	
	/**
	 * [MA2011061011]
	 * 
	 * @author Paulo Diniz
	 * @date 02/07/2011
	 * 
	 * @param idHidrometro
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarMaiorDataHidrometroMovimentado(Integer idHidrometro) throws ErroRepositorioException ;
	
	
	/**[MA2011061010]
	 * 
	 * @param faixaInicial
	 *            Descricao do parametro
	 * @param faixaFinal
	 *            Descricao do parametro
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Integer pesquisarNumeroHidrometroMovimentacaoPorFaixaCount(String Fixo,
			String faixaInicial, String faixaFinal) throws ErroRepositorioException;
	
	/** [MA2011061010]
	 * 
	 * pesquisa uma colecao de HidrometroMovimentacao
	 * 
	 * @param faixaInicial
	 *            Descricao do parametro
	 * @param faixaFinal
	 *            Descricao do parametro
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarNumeroHidrometroMovimentacaoPorFaixaPaginacao(
			String faixaInicial, String faixaFinal, Integer numeroPagina)
			throws ErroRepositorioException;
	
	/** [MA2011061010]
	 */
	public Collection pesquisarNumeroHidrometroMovimentacaoPorFaixa(String faixaInicial, String faixaFinal) throws ErroRepositorioException;

	public LigacaoAguaSituacao obterDadosSituacaoLigacaoAgua(Integer idLigacao) throws ErroRepositorioException;

    /**
     * [UC0936]
     */
    public Integer pesquisarAnoMesFaturamentoSituacaoInicio(Integer idImovel, Integer idFaturamentoSituacao) throws ErroRepositorioException;
    
	public Object[] pesquisarImovelHidrometroInstaladoMaiorDataInstalacao(Integer idHidrometro) throws ErroRepositorioException;
	
	public void atualizarArquivoTextoDividido(Integer idRota, Integer anoMesFaturamento, Integer numeroSequenciaArquivo, int situacaoAnterior, int situacaoNova) throws ErroRepositorioException;
	
	public Rota pesquisarRota(Integer idRota) throws ErroRepositorioException;
	
	public void atualizarLeituraRetificaConta(Integer leituraAtual, int anoMesReferencia, Integer idImovel) throws ErroRepositorioException;
	
	public Rota obterRotaPorLocalidadeSetorComercial(Integer idLocalidade, Integer codigoSetorComercial) throws ErroRepositorioException;
	
	public Collection pesquisarArquivosTextoRoteiroEmpresaCompletoParaArquivoZip(String[] ids) throws ErroRepositorioException;
	
	public ArquivoTextoRoteiroEmpresa pesquisarArquivosTextoRoteiroEmpresaTransmissaoOffline(Integer idLocalidade,Integer idRota, Integer anoMesReferencia) throws ErroRepositorioException;
	
	public ArquivoTextoRoteiroEmpresaDivisao pesquisarArquivoTextoRoteiroEmpresaDivisao(Integer atreId, Integer numeroSequenciaArquivo) throws ErroRepositorioException;
	
	public Collection pesquisarDadosRelatorioLeiturasRealizadas(int anoMesReferencia, Integer idFaturamentoGrupo) throws ErroRepositorioException;

	public boolean pesquisaArquivoRotaPorGrupoEReferencia(Integer idGrupo, Integer referencia) throws ErroRepositorioException;
		
	public MedicaoHistorico pesquisarMedicaoHistoricoTipoAguaLeituraAnormalidade(Integer imovel, Integer anoMes) throws ErroRepositorioException;

	public MedicaoHistorico pesquisarMedicaoHistoricoTipoPocoLeituraAnormalidade(Integer imovel, Integer anoMes) throws ErroRepositorioException;
	
	public Short pesquisarMedicaoHistoricoLigacaoAguaAnalisado(Integer idImovel, Integer anoMesReferenciaGrupoFaturamento, Integer idMedicaoTipo) throws ErroRepositorioException;

	public Integer pesquisarQuantidadeHidrometrosRelatorioBIG(Date dataInicial, Date dataFinal, Integer idLocalidade, Integer situacao) throws ErroRepositorioException;
	
     public Collection pesquisarHidrometroProtecao() throws ErroRepositorioException;
    
     public Collection pesquisarHidrometroMarca() throws ErroRepositorioException;
     
     public Collection<ImovelPorRotaHelper> buscarImoveisFaturamentoSeletivo(Integer matriculaImovel, Integer idRota, Integer anoMesFaturamento) throws ErroRepositorioException;
	
     public LigacaoAgua obterLigacaoAgua(Integer idLigacao) throws ErroRepositorioException;
}
