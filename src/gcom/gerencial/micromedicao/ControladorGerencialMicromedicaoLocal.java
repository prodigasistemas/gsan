package gcom.gerencial.micromedicao;

import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.relatorio.gerencial.micromedicao.FiltrarRelatorioResumoDistritoOperacionalHelper;
import gcom.relatorio.gerencial.micromedicao.RelatorioResumoDistritoOperacionalHelper;
import gcom.relatorio.gerencial.micromedicao.RelatorioResumoZonaAbastecimentoHelper;
import gcom.util.ControladorException;

import java.util.Collection;
import java.util.List;

/**
 * 
 * 
 * @author Thiago Toscano
 * @created 19/04/2006
 */
public interface ControladorGerencialMicromedicaoLocal extends
		javax.ejb.EJBLocalObject {

	/**
	 * [UC0344] Consultar Resumo de Anormalidades
	 * 
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarResumoAnormalidadeAgua(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ControladorException;

	/**
	 * [UC0344] Consultar Resumo de Anormalidades
	 * 
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarResumoAnormalidadePoco(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ControladorException;

	/**
	 * [UC0344] Consultar Resumo de Anormalidades
	 * 
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarResumoAnormalidadeConsumo(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ControladorException;

	/**
	 * [UC0344] Consultar Resumo de Anormalidades
	 * 
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public void gerarResumoAnormalidadeLeitura(int idLocalidade,
			int idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * [UC0344] Consultar Resumo de Anormalidades
	 * 
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public void gerarResumoAnormalidadeConsumo() throws ControladorException;

	/**
	 * Gera o resumo das ligações de hidrômetro.
	 * 
	 * [UC0564 - Gerar Resumo das Intalações de Hidrômetros]
	 * 
	 * @author Pedro Alexandre
	 * @date 24/04/2007
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idSetorComercial
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarResumoInstalacoesHidrometros(
			Integer anoMesReferenciaArrecadacao, Integer idSetorComercial,
			int idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * Método que gera o resumo da Leituras Anormalidades
	 * 
	 * [UC0551] - Gerar Resumo da Leitura Anormalidade
	 * 
	 * @author Ivan Sérgio
	 * @date 26/04/2007
	 */
	public void gerarResumoLeituraAnormalidade(int idLocalidade,
			int anoMesLeitura, int idFuncionalidadeIniciada)
			throws ControladorException;

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
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarIdsSetorComercialParaGerarResumoInstalacaoHidrometro(
			Integer anoMesFaturamento) throws ControladorException;

	/**
	 * Método que gera o resumo de Hidrometros
	 * 
	 * [UC0586] - Gerar Resumo Hidrometro
	 * 
	 * @author Thiago Tenório
	 * @date 30/04/2007
	 * 
	 */
	public void gerarResumoHidrometros(Integer idHidrometroMarca,
			int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * Método que gera resumo indicadores da micromedição
	 * 
	 * [UC0573] - Gerar Resumo Indicadores da Micromedição
	 * 
	 * @author Rafael Corrêa
	 * @date 11/03/2008
	 * 
	 */
	public void gerarResumoIndicadoresMicromedicao(int idFuncionalidadeIniciada)
			throws ControladorException;
	/**
	 * 
	 * [UC0892]Consulta os registros do Relatorio Resumo Distrito Operacional
	 * 
	 * @author Hugo Amorim
	 * @date 15/04/2009
	 * 
	 * @return Collection<RelatorioResumoDistritoOperacionalHelper>
	 * 
	 */
	public Collection<RelatorioResumoDistritoOperacionalHelper> pesquisarRelatorioResumoDistritoOperacional(FiltrarRelatorioResumoDistritoOperacionalHelper filtro)
			throws ControladorException;
	
	/**
	 * 
	 * [UC0892]Consulta total dos registros do Relatorio Resumo Distrito Operacional
	 * 
	 * @author Hugo Amorim
	 * @date 15/04/2009
	 * 
	 * @return Innteger
	 * 
	 */
	public Integer pesquisarTotalRegistroRelatorioResumoDistritoOperacional(FiltrarRelatorioResumoDistritoOperacionalHelper helper) throws ControladorException;
	/**
	 * 
	 * [UC0892]Consulta os registros do Relatorio Resumo Zona Abastecimento
	 * 
	 * @author Hugo Amorim
	 * @date 23/04/2009
	 * 
	 * @return Collection<RelatorioResumoDistritoOperacionalHelper>
	 * 
	 */
	public Collection<RelatorioResumoZonaAbastecimentoHelper> pesquisarRelatorioResumoZonaAbastecimento(FiltrarRelatorioResumoDistritoOperacionalHelper filtro)
			throws ControladorException;
	
	/**
	 * Método que gera resumo indicadores da micromedição
	 * 
	 * @author Fernando Fontelles
	 * @date 17/05/2010
	 * 
	 */
	public void gerarResumoIndicadoresMicromedicaoPorAno(int idFuncionalidadeIniciada)
			throws ControladorException;
	
	/**
	 * Gera o resumo das ligações de hidrômetro por ano.
	 * 
	 * Gerar Resumo das Instalações de Hidrômetros Por Ano
	 * 
	 * @author Fernando Fontelles
	 * @date 17/06/2010
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idSetorComercial
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarResumoInstalacoesHidrometrosPorAno(
			Integer anoMesReferenciaFaturamento, Integer idSetorComercial,
			int idFuncionalidadeIniciada) throws ControladorException;
}	
