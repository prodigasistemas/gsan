package gcom.gerencial;

import java.util.Collection;
import java.util.List;

import gcom.gerencial.bean.FiltrarRelatorioOrcamentoSINPHelper;
import gcom.gerencial.bean.FiltrarRelatorioQuadroMetasAcumuladoHelper;
import gcom.gerencial.bean.FiltrarRelatorioQuadroMetasExercicioHelper;
import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaEventualHelper;
import gcom.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaHelper;
import gcom.gerencial.bean.OrcamentoSINPHelper;
import gcom.gerencial.bean.QuadroMetasAcumuladoHelper;
import gcom.gerencial.bean.QuadroMetasExercicioHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;



/**
 * 
 * 
 * @author Raphael Rossiter
 * @created 20/05/2006
 */
public interface ControladorGerencialLocal extends javax.ejb.EJBLocalObject {
	
	/**
	 * Esta funcionalidade permite informar dados para geração de relatórios ou consultas 
	 * 
	 * [UC0304] - Informar Dados para Geração de Relatório ou Consulta
	 * 
	 * @author Raphael Rossiter
	 * @date 22/05/2006
	 *
	 * @param mesAnoFaturamento
	 * @param opcaoTotalizacao
	 * @param idFauramentoGrupo
	 * @param idGerenciaRegional
	 * @param idEloPolo
	 * @param idLocalidade
	 * @param idSetorComercial
	 * @param nmQuadra
	 * @param idsImovelPerfil
	 * @param idsLigacaoAguaSituacao
	 * @param idsLigacaoEsgotoSituacao
	 * @param idsCategoria
	 * @param idsEsferaPoder
	 * @param tipoAnaliseFaturamento
	 * @param tipoRelatorio
	 * @return InformarDadosGeracaoRelatorioConsultaHelper
	 * @throws ControladorException
	 */
	public InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsulta(String mesAnoFaturamento,
			Integer opcaoTotalizacao, Integer idFauramentoGrupo, Integer idCobrancaGrupo, Integer idGerenciaRegional, Integer idEloPolo,
			Integer idLocalidade, Integer idSetorComercial, Integer nmQuadra, String[] idsImovelPerfil,
			String[] idsLigacaoAguaSituacao, String[] idsLigacaoEsgotoSituacao, String[] idsCategoria,
			String[] idsEsferaPoder, Integer tipoAnaliseFaturamento, Integer tipoRelatorio,
			Integer idUnidadeNegocio, Integer idMunicipio, Integer idRota) throws ControladorException ;
	
	
	/**
	 * Método para auxilio de Casos de Uso de resumos
	 */
	public Collection criarColecaoAgrupamentoResumos(InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper) throws ControladorException;
	
	public Collection criarColecaoAgrupamentoResumosCobrancaAcao(InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper) throws ControladorException;
	
	public List consultarComparativoResumosFaturamentoArrecadacaoPendencia(InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper) throws ControladorException;
	
	
	/**
	 * Pesquisa o valor e a quantidade de contas do resumo da faturamento
	 *
	 * [UC0350] - Consultar Comparativo entre os Resumos do Faturamento, Arrecadação e da Pendência.
	 *
	 * @author Pedro Alexandre
	 * @date 09/06/2006
	 *
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarResumoFaturamento(InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper) throws ControladorException;

	/**
	 * Pesquisa o valor e a quantidade de contas do resumo da arrecadação
	 *
	 * [UC0350] - Consultar Comparativo entre os Resumos do Faturamento, Arrecadação e da Pendência.
	 *
	 * @author Pedro Alexandre
	 * @date 10/06/2006
	 *
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarResumoArrecadacao(InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper) throws ControladorException;

	/**
	 * Pesquisa o valor e a quantidade de contas do resumo da pendência.
	 *
	 * [UC0350] - Consultar Comparativo entre os Resumos do Faturamento, Arrecadação e da Pendência.
	 *
	 * @author Pedro Alexandre
	 * @date 10/06/2006
	 *
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarResumoComparativoPendencia(InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper) throws ControladorException;
	
	/**
	 * [UC0617] Consultar Resumo das Ações de Cobrança Eventuais
	 * 
	 * Pesquisa as ações de cobrança
	 * 
	 * @author Sávio Luiz
	 * @date 26/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection criarColecaoAgrupamentoResumosCobrancaAcaoEventual(
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper)
			throws ControladorException;
	
	/**
	 * Pesquisa os valores necessarios na tabela un_resumo_faturamento
	 *
	 * [UC0722] - Gerar Relatorio para Orçamento e SINP
	 *
	 * @author Rafael Pinto
	 * @date 20/11/2006
	 *
	 * @param FiltrarRelatorioOrcamentoSINPHelper
	 * @return Collection<OrcamentoSINPHelper>
	 * 
	 * @throws ControladorException
	 */
	public Collection<OrcamentoSINPHelper> pesquisarRelatorioOrcamentoSINP(
		FiltrarRelatorioOrcamentoSINPHelper filtrarRelatorioOrcamentoSINPHelper) 
		throws ControladorException ;
	
	/**
	 * 
	 * [UC0733] Gerar Quadro de metas Acumulado
	 * 
	 * @author Bruno Barros
	 * @param filtrarRelatorioQuadroMetasAcumuladoHelper
	 * @return
	 */
	public Collection<QuadroMetasAcumuladoHelper> pesquisarRelatorioQuadroMetasAcumulado(
			FiltrarRelatorioQuadroMetasAcumuladoHelper filtrarRelatorioQuadroMetasAcumuladoHelper) 
		throws ControladorException;
	
	/**
	 * Verifica se existe dados nas tabelas de resumo
	 *
	 * [UC0722] - Gerar Relatorio para Orçamento e SINP
	 *
	 * @author Rafael Pinto
	 * @date 11/01/2007
	 *
	 * @param anoMesReferencia
	 * 
	 * @throws ControladorException
	 */
	public void validarDadosOrcamentoSINP(int anoMesReferencia) 
		throws ControladorException ;
	
	/**
	 * Pesquisa todas as tabelas de resumo para o Orcamento sem a tabela de resumo pendencia e arrecadação
	 *
	 * [UC0750] - Gerar Arquivo Texto para Orçamento e SINP
	 *
	 * @author Sávio Luiz
	 * @date 12/02/2008
	 *
	 * @return anoMesReferencia
	 * 
	 * @throws ErroRepositorioException
	 */
	public void existeDadosUnResumoParcialParaOrcamentoSINP(int anoMesReferencia) 
		throws ControladorException;
	
	/**
	 * Gera o Arquivo de Oracamento e SINP
	 *
	 * [UC0750] - Gerar Arquivo Texto para Orçamento e SINP
	 *
	 * @author Tiago Moreno
	 * @date 14/02/2008
	 *
	 * @return anoMesReferencia
	 * 
	 * @throws ErroRepositorioException
	 */
	public void gerarArquivoTextoOrcamentoSinp(int anoMesReferencia) 
		throws ControladorException;
	
	/**
	 * 
	 * [UC0752] Gerar Quadro de metas por Exercicio
	 * 
	 * @author Bruno Barros
	 * @param filtrarRelatorioQuadroMetasExercicioHelper
	 * @return
	 */
	public Collection<QuadroMetasExercicioHelper> pesquisarRelatorioQuadroMetasExercicio(
			FiltrarRelatorioQuadroMetasExercicioHelper filtrarRelatorioQuadroMetasExercicioHelper) throws ControladorException;	
}

