package gcom.gerencial.cadastro;

import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.util.ControladorException;

import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * @author Thiago Toscano
 * @created 19/04/2006
 */
public interface ControladorGerencialCadastroLocal extends
		javax.ejb.EJBLocalObject {

	/**
	 * Método que gera o resumo das ligações e economias
	 * 
	 * [UC0275] - Gerar Resumo das Ligações/Economias
	 * 
	 * @author Thiago Toscano
	 * @date 19/04/2006
	 * 
	 */
	public void gerarResumoLigacoesEconomias(int idLocalidade,
			int idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * [UC0344] Consultar Resumo Anormalidade
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarResumoLigacoesEconomias(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ControladorException;

	public void gerarResumoConsumoAgua(int idSetor,
			int idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * Método que gera o resumo do Parcelamento
	 * 
	 * [UC0565] - Gerar Resumo do Parcelamento
	 * 
	 * @author Marcio Roberto
	 * @param anoMes
	 * @date 04/05/2007
	 * 
	 */
	public void gerarResumoParcelamento(int idLocalidade,
			int idFuncionalidadeIniciada, int anoMes)
			throws ControladorException;

	/**
	 * Método que gera o resumo dos histogramas de água e esgoto
	 * 
	 * [UC0566] - Gerar Histograma de Água e Esgoto
	 * 
	 * @author Bruno Barros
	 * @date 09/05/2007
	 * 
	 */
	public void gerarResumoHistograma(int anoMesReferencia, int idSetor,
			int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * Método que gera resumo indicadores de comercialização
	 * 
	 * [UC0573] - Gerar Resumo Indicadores da Comercialização
	 * 
	 * @author Rafael Corrêa
	 * @date 06/03/2008
	 * 
	 */
	public void gerarResumoIndicadoresComercializacao(int idFuncionalidadeIniciada)
			throws ControladorException;
	
	/**
	 * Método que gera o resumo de Metas da CAERN
	 * 
	 * [UC0623] - Gerar Resumo de Metas
	 * 
	 * @author Sávio Luiz
	 * @date 20/07/2007
	 * 
	 */
	public void gerarResumoMetas(int idSetor, Date dataInicial, Date dataFinal,
			int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * Método que gera o resumo de Metas da CAERN
	 * 
	 * [UC0623] - Gerar Resumo de Metas
	 * 
	 * @author Sávio Luiz
	 * @date 20/07/2007
	 * 
	 */
	public void gerarResumoMetasAcumulado(int idSetor,
			Integer anoMesArrecadacao, int idFuncionalidadeIniciada)
			throws ControladorException;
	
	/**
	 * Método que gera o resumo do coleta esgoto
	 * 
	 * [UC0573] - Gerar Resumo do coleta esgoto
	 * 
	 * @author Marcio Roberto
	 * @date 18/09/2007
	 * 
	 */
	public void gerarResumoColetaEsgoto(int idSetor, int idFuncionalidadeIniciada)
			throws ControladorException;

	public void excluirResumoGerencial(Integer anoMesFaturamento, String string, String string2, int idSetor) throws ControladorException;
	
    public void excluirResumoGerencial ( 
   		  int anomesreferencia , 
   		  String resumoGerencial, 
   		  String resumoCampoAnoMes,
   		  String resumoCampoUnidadeProcessamento,
   		  int idUnidadeProcessamento ) throws ControladorException;	
	
	public void excluirResumoGerencialSQL(Integer anoMesFaturamento, String string, String string2, int idSetor) throws ControladorException;	

	public void excluirResumoGerencialC(Integer anoMesFaturamento, String string, String string2, String string3, int idCampo) throws ControladorException;	
	
	/**
	 * Método que gera o resumo das ligações e economias Por Ano
	 * 
	 *Gerar Resumo das Ligações/Economias Por Ano
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 29/04/2010
	 * 
	 */
	public void gerarResumoLigacoesEconomiasPorAno(int idLocalidade,
			int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * Método que gera o resumo do Consumo de Agua Por Ano
	 * 
	 *Gerar Resumo Consumo de Agua Por Ano
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 24/05/2010
	 * 
	 */
	public void gerarResumoConsumoAguaPorAno(int idSetor,
			int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * Método que gera o resumo do coleta esgoto Por Ano
	 * 
	 * Gerar Resumo do coleta esgoto Por Ano
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 15/06/2010
	 * 
	 */
	public void gerarResumoColetaEsgotoPorAno(int idSetor, int idFuncionalidadeIniciada)
			throws ControladorException;
	
	/**
	 * Método que gera o resumo do Parcelamento PorAno
	 * 
	 * Gerar Resumo do Parcelamento Por Ano
	 * 
	 * @author Fernando Fontelles
	 * @date 21/06/2010
	 * 
	 */
	public void gerarResumoParcelamentoPorAno(int idLocalidade,
			int idFuncionalidadeIniciada, int anoMes)
			throws ControladorException;
	
	/***
	 * [UC1057] - Gerar Histograma de Agua e Esgoto Sem Quadras
	 * @author Ivan Sergio
	 * @date: 11/08/2010
	 * 
	 * @param anoMesReferencia
	 * @param idSetor
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarResumoHistogramaSemQuadra(
			int anoMesReferencia,
			int idSetor,
			int idFuncionalidadeIniciada) throws ControladorException;
	
}
