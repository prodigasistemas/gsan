package gcom.gerencial.micromedicao;

import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.gerencial.operacional.GDistritoOperacional;
import gcom.relatorio.gerencial.micromedicao.FiltrarRelatorioResumoDistritoOperacionalHelper;
import gcom.util.ErroRepositorioException;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * @author Flávio Cordeiro
 * @created 17/05/2006
 */
public interface IRepositorioGerencialMicromedicao {

	/**
	 * 
	 * Método que consulta os ResumoAnormalidadeHelper
	 * 
	 * @author Flávio Cordeiro
	 * @date 17/05/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getResumoAnormalidadeHelper(String anoMes, int idLocalidade)
			throws ErroRepositorioException;

	/**
	 * 
	 * Método que após consultar os ResumoAnormalidadeHelper no metodo
	 * getResumoAnormalidadeHelper(String anoMes) pega os ids de ligacao agua e
	 * pesquisam o imovel apartir dele
	 * 
	 * @author Flávio Cordeiro
	 * @date 19/05/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarImovelPorIdLigacaoAgua(Integer idLigacaoAgua)
			throws ErroRepositorioException;

	public List getResumoAnormalidadeConsumoHelper(String anoMes)
			throws ErroRepositorioException;

	public String criarCondicionaisResumos(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper,
			String nomeColunaTabela);

	public List consultarResumoAnormalidadeAgua(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ErroRepositorioException;

	public List consultarResumoAnormalidadePoco(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ErroRepositorioException;

	public List consultarResumoAnormalidadeConsumo(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ErroRepositorioException;

	/**
	 * Gera o resumo das instações de hidrômetro para o ano/mês de referência da
	 * arrecadação.
	 * 
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 * 
	 * Verificar existência de dados para o ano/mês de referência da arrecadação
	 * do Resumo das instalações de hidrômetros.
	 * 
	 * [FS0001 - Verificar existência de dados para o ano/mês de referência da
	 * arrecadação]
	 * 
	 * @author Pedro Alexandre
	 * @date 24/04/2007
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaResumoInstalacaoHidrometroParaAnoMesReferenciaArrecadacao(
			Integer anoMesReferenciaArrecadacao, Integer idSetorComercial)
			throws ErroRepositorioException;

	/**
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 * 
	 * Pesquisa os dados do setor comercial.
	 * 
	 * @author Pedro Alexandre
	 * @date 24/04/2007
	 * 
	 * @param idSetorComercial
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosSetorComercial(Integer idSetorComercial)
			throws ErroRepositorioException;

	/**
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 * 
	 * Pesquisa a coleção de ids de quadras para o setor comercial informado.
	 * 
	 * @author Pedro Alexandre
	 * @date 24/04/2007
	 * 
	 * @param idSetorComercial
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosQuadrasPorSetorComercial(
			Integer idSetorComercial, Date dataInicial, Date dataFinal)
			throws ErroRepositorioException;

	/**
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 * 
	 * Pesquisar dados do imóvel pela quadra.
	 * 
	 * @author Pedro Alexandre
	 * @date 26/04/2007
	 * 
	 * @param idQuadra
	 * @param anoMesFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosImovelResumoInstalacaoHidrometroPorQuadra(
			Integer idQuadra, Date dataInicio, Date dataFim)
			throws ErroRepositorioException;

	/**
	 * [UC0564] Gerar Resumo das Instalações de Hidrômetros
	 * 
	 * Pesquisa os dados do cliente responsável do imóvel informado.
	 * 
	 * @author Pedro Alexandre
	 * @date 02/05/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosClienteResponsavelPorImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0564] Gerar Resumo das Instalações de Hidrômetros
	 * 
	 * Pesquisa os dados da instalação do hidrômetro no histórico para o imóvel
	 * informado.
	 * 
	 * @author Pedro Alexandre
	 * @date 02/05/2007
	 * 
	 * @param idImovel
	 * @param anoMesFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosHidrometroInstalacaoHistoricoPorImovel(
			Integer idImovel, Date dataInicio, Date dataFim)
			throws ErroRepositorioException;

	/**
	 * Método que retona uma lista de objeto xxxxx que representa o UC0551 -
	 * Gerar Resumo Leitura Anormalidade
	 * 
	 * @author Ivan Sérgio
	 * @date 23/04/2007
	 * 
	 * @param anoMesLeitura -
	 *            Ano Mes da Leitura
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoLeituraAnormalidade(int localidade,
			int anoMesLeitura) throws ErroRepositorioException;

	/**
	 * [UC0564] Gerar Resumo das Instalações de Hidrômetros
	 * 
	 * Pesquisa os ids dos setores comercias dos imóveis que tem hidrometro
	 * instalado no histórico
	 * 
	 * @author Pedro Alexandre
	 * @date 08/05/2007
	 * 
	 * @param anoMesFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsSetorComercialParaGerarResumoInstalacaoHidrometro(
			Integer anoMesFaturamento) throws ErroRepositorioException;

	/**
	 * Método que retona uma lista de objeto xxxxx que representa o UC0586 -
	 * Gerar Resumo Hidrometro
	 * 
	 * @author Thiago Tenório
	 * @date 23/04/2007
	 * 
	 * @param anoMesArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection getHidrometrosResumoHidrometro(
			Integer idLocalArmazenagem, int indice, int qtRegistros)
			throws ErroRepositorioException;

	/**
	 * [FS0003] - Verificar Existencia de conta para o mes de faturamento Metodo
	 * utilizado para auxiliar o [UC0551 - Gerar Resumo Leitura Anormalidade]
	 * para recuperar o valo da Situacao da Ligacao de Agua.
	 * 
	 * @author Ivan Sérgio
	 * @date 27/07/2007, 08/08/2007
	 * @alteracao - Receber os outros campos da
	 *            getImoveisResumoLeituraAnormalidade;
	 * 
	 * @throws ErroRepositorioException
	 * @return List
	 */
	public List pesquisarLeituraAnormalidadeComplementar(Integer imovel,
			Integer dataFaturamento) throws ErroRepositorioException;

	/**
	 * calcula a quantidade de hidrometro instalados ramal de água
	 * 
	 * [UC0564] Gerar Resumo das Instalações de Hidrômetro
	 * 
	 * @author Pedro Alexandre
	 * @date 08/08/2007
	 * 
	 * @param idLigacaoAgua
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeHidrometroInstalacaoRamalAgua(
			Integer idLigacaoAgua) throws ErroRepositorioException;

	/**
	 * Seleciona o maior mês/ano de referência da tabela un_resumo_consumo_agua
	 * 
	 * [UC????] - Gerar Resumo Indicadores da Micromedição
	 * 
	 * @author Rafael Corrêa
	 * @date 11/03/2008
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoConsumoAgua()
			throws ErroRepositorioException;

	/**
	 * Seleciona o maior mês/ano de referência da tabela un_resumo_coleta_esgoto
	 * 
	 * [UC????] - Gerar Resumo Indicadores da Micromedição
	 * 
	 * @author Rafael Corrêa
	 * @date 11/03/2008
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoColetaEsgoto()
			throws ErroRepositorioException;

	/**
	 * Seleciona o maior mês/ano de referência da tabela
	 * un_resumo_leitura_anormalidade
	 * 
	 * [UC????] - Gerar Resumo Indicadores da Micromedição
	 * 
	 * @author Rafael Corrêa
	 * @date 11/03/2008
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoLeituraAnormalidade()
			throws ErroRepositorioException;

	/**
	 * Seleciona o maior mês/ano de referência da tabela
	 * un_resumo_instalacao_hidrometro
	 * 
	 * [UC????] - Gerar Resumo Indicadores da Micromedição
	 * 
	 * @author Rafael Corrêa
	 * @date 11/03/2008
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoInstalacaoHidrometro()
			throws ErroRepositorioException;

	/**
	 * Seleciona o maior mês/ano de referência da tabela
	 * un_resumo_indicador_desempenho_micromedicao
	 * 
	 * [UC????] - Gerar Resumo Indicadores da Micromedição
	 * 
	 * @author Rafael Corrêa
	 * @date 11/03/2008
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoIndicadorDesempenhoMicromedicao()
			throws ErroRepositorioException;

	/**
	 * Atualiza os dados na tabela un_resumo_indicador_desempenho_micromedicao
	 * 
	 * [UC????] - Gerar Resumo Indicadores da Micromedição
	 * 
	 * @author Rafael Corrêa
	 * @date 12/03/2008
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarDadosResumoIndicadorDesempenhoMicromedicao(
			Integer anoMesReferenciaIndicador, Integer anoMesReferenciaTabelas)
			throws ErroRepositorioException;

	/**
	 * calcula a quantidade de hidrometro instalados atualmente no poço
	 * 
	 * [UC0564] Gerar Resumo das Instalações de Hidrômetro
	 * 
	 * @author Pedro Alexandre
	 * @date 08/08/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeHidrometroInstalacaoPoco(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * 
	 * [UC0892]Consulta os registros do Relatorio Resumo Distrito Operacional
	 * 
	 * @author Hugo Amorim
	 * @date 15/04/2009
	 * 
	 * @return Collection<GDistritoOperacional[]>
	 * 
	 */
	public Collection<GDistritoOperacional> pesquisarRelatorioResumoDistritoOperacional(
			FiltrarRelatorioResumoDistritoOperacionalHelper filtro)
			throws ErroRepositorioException;

	/**
	 * 
	 * [UC0892]Consulta total dos registros do Relatorio Resumo Distrito
	 * Operacional
	 * 
	 * @author Hugo Amorim
	 * @date 15/04/2009
	 * 
	 * @return Integer
	 * 
	 */
	public Integer pesquisarTotalRegistroRelatorioResumoDistritoOperacional(
			FiltrarRelatorioResumoDistritoOperacionalHelper helper)
			throws ErroRepositorioException;

	/**
	 * [UC0892]Consulta quantidade de dados de Hidrometos por Distrito
	 * 
	 * @author Hugo Amorim
	 * @date 17/04/2009
	 * 
	 * @return Integer
	 */
	public Integer pesquisarQuantidadeHidrometros(FiltrarRelatorioResumoDistritoOperacionalHelper filtro, int i,
			Integer id,String indicador,String referencia,String idDistrito,String idGerencia) throws ErroRepositorioException;

	/**
	 * [UC0892]Consulta Situação da Agua de Hidrometos por Distrito
	 * 
	 * @author Hugo Amorim
	 * @date 17/04/2009
	 * 
	 * @return Integer
	 */
	public Integer pesquisarSituacaoAgua(FiltrarRelatorioResumoDistritoOperacionalHelper filtro, String parametro, Integer id,String indicador,String referencia, String idDistrito, String idGerencia)
			throws ErroRepositorioException;

	/**
	 * [UC0892]Consulta Economias por Distrito
	 * 
	 * @author Hugo Amorim
	 * @date 17/04/2009
	 * 
	 * @return Integer
	 */
	public Integer pesquisarEconomias(String tipoRelatorio, FiltrarRelatorioResumoDistritoOperacionalHelper filtro, Integer id,String indicador,String referencia,String idDistrito, String idGerencia)
			throws ErroRepositorioException;

	/**
	 * [UC0892]Consulta Volume Real Medido por Distrito
	 * 
	 * @author Hugo Amorim
	 * @date 20/04/2009
	 * 
	 * @return Integer
	 */
	public Integer pesquisarVolumeRealMedido(FiltrarRelatorioResumoDistritoOperacionalHelper filtro, Integer id,String indicador,String referencia,String idDistrito, String idGerencia)
			throws ErroRepositorioException;

	/**
	 * [UC0892]Consultar volumes faturados por Distrito
	 * 
	 * @author Hugo Amorim
	 * @date 20/04/2009
	 * 
	 * @return Integer
	 */
	public Integer pesquisarVolumesFaturados(FiltrarRelatorioResumoDistritoOperacionalHelper filtro, String parametro,
			Integer id,String indicador,String referencia,String idDistrito, String idGerencia) throws ErroRepositorioException;

	/**
	 * [UC0892]Consultar volumes faturados por Distrito
	 * 
	 * @author Hugo Amorim
	 * @date 20/04/2009
	 * 
	 * @return Integer
	 */
	public Integer pesquisarVolumesFaturadosTotal(FiltrarRelatorioResumoDistritoOperacionalHelper filtro, Integer id,String indicador,String referencia,String idDistrito, String idGerencia)
			throws ErroRepositorioException;

	/**
	 * [UC0892]Consulta Situação da Agua de Hidrometos por Distrito
	 * 
	 * @author Hugo Amorim
	 * @date 20/04/2009
	 * 
	 * @return Integer
	 */
	public Integer pesquisarSituacaoAguaTotal(FiltrarRelatorioResumoDistritoOperacionalHelper filtro, Integer id,String indicador,String referencia,String idDistrito, String idGerencia)
			throws ErroRepositorioException;

	/**
	 * 
	 * [UC0892]Consulta os registros do Relatorio Resumo Zona Abastecimento
	 * 
	 * @author Hugo Amorim
	 * @date 23/04/2009
	 * 
	 * @return Collection<Object[]>
	 * 
	 */
	public Collection<Object[]> pesquisarRelatorioResumoZonaAbastecimento(
			FiltrarRelatorioResumoDistritoOperacionalHelper filtro)
			throws ErroRepositorioException;


	/**
	 * 
	 * [UC0892]Consulta total dos registros do Relatorio Resumo Zona
	 * Abastecimento
	 * 
	 * @author Hugo Amorim
	 * @date 23/04/2009
	 * 
	 * @return Integer
	 * 
	 */
	public Integer pesquisarTotalRelatorioResumoZonaAbastecimento(
			FiltrarRelatorioResumoDistritoOperacionalHelper filtro) throws ErroRepositorioException;
	/**
	 * 
	 * [UC0892]Consulta quantida de resgistro Factiveis e Potenciais dos registros do Relatorio Resumo Zona
	 * Abastecimento
	 * 
	 * @author Hugo Amorim
	 * @date 28/04/2009
	 * 
	 * @return Integer
	 * 
	 */
	public Integer pesquisarSituacaoFactivelPotencial(FiltrarRelatorioResumoDistritoOperacionalHelper filtro, Integer id,String indicador,String tipo ,String referencia,String idDistrito, String idGerencia) throws ErroRepositorioException;

	/**
	 * Seleciona o maior mês/ano de referência da tabela
	 * un_resumo_indicador_desempenho_micromedicao_ref_2010
	 * 
	 * @author Fernando Fontelles
	 * @date 17/05/2010
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoIndicadorDesempenhoMicromedicaoPorAno()
			throws ErroRepositorioException;
	
	/**
	 * Atualiza os dados na tabela un_resumo_indicador_desempenho_micromedicao_ref_2010
	 * 
	 * @author Fernando Fontelles
	 * @date 17/05/2010
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarDadosResumoIndicadorDesempenhoMicromedicaoPorAno(
			Integer anoMesReferenciaIndicador, Integer anoMesReferenciaTabelas)
			throws ErroRepositorioException;
	
	/**
	 * Gerar Resumo das Instalações de Hidrômetros Por Ano
	 * 
	 * Pesquisar dados do imóvel.
	 * 
	 * @author Fernando Fontelles
	 * @date 17/06/2010
	 * 
	 * @param idSetorComercial
	 * @param anoMesFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosImovelResumoInstalacaoHidrometroPorAno(
			Integer idSetorComercial, Date dataInicio, Date dataFim)
			throws ErroRepositorioException;
	
}
