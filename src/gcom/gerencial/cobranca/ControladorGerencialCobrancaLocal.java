package gcom.gerencial.cobranca;

import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaEventualHelper;
import gcom.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaHelper;
import gcom.gerencial.cobranca.bean.ResumoPendenciaAcumuladoHelper;
import gcom.gerencial.faturamento.bean.ConsultarResumoSituacaoEspecialHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.util.Collection;
import java.util.List;



/**
 * 
 * 
 * @author Thiago Toscano
 * @created 19/04/2006
 */
public interface ControladorGerencialCobrancaLocal extends javax.ejb.EJBLocalObject {
	
	/**
	 * Método que gera o resumo Resumo Situacao Especial Faturamento  
	 *
	 * [UC0341] 
	 *
	 * @author Thiago Toscano
	 * @date 19/04/2006
	 *
	 */
	public void gerarResumoSituacaoEspecialCobranca(int idLocalidade,
			int idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * Método que gera o resumo da pendencia
	 * 
	 * [UC0335] - Gerar Resumo da Pendencia
	 * 
	 * @author Bruno Barros
	 * @date 19/07/2007
	 * 
	 */	
	public void gerarResumoPendencia( int idSetorComercial,
			int idFuncionalidadeIniciada) throws ControladorException;		
	/**
	 * Este caso de uso permite consultar o resumo da pendência, com a opção de impressão da  consulta.
	 * Dependendo da opção de totalização sempre é gerado o relatório, sem a feração da consulta.
	 *
	 * [UC0338] Consultar Resumo da Pendência
	 *
	 * Gera a lista de pendências das Contas e Guias de Pagamento
	 *
	 * consultarResumoPendencia	
	 *
	 * @author Roberta Costa
	 * @date 24/05/2006
	 *
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarResumoPendencia(InformarDadosGeracaoRelatorioConsultaHelper 
			informarDadosGeracaoRelatorioConsultaHelper) throws ControladorException;
	
	public Collection<ResumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper> recuperaResumoSituacaoEspecialCobranca(ConsultarResumoSituacaoEspecialHelper helper) throws ControladorException;
	
	/**
	 * Este caso de uso permite consultar o resumo da pendência, com a opção de impressão da  consulta.
	 * Dependendo da opção de totalização sempre é gerado o relatório, sem a feração da consulta.
	 *
	 * [UC0338] Consultar Resumo da Pendência
	 *
	 * Retorna os registro de resumo pendência dividindo em coleções de categoria RESIDENCIAL, COMERCIAL,
	 * INDUSTRIAL e PUBLICA
	 *
	 * retornaConsultaResumoPendencia
	 *
	 * @author Roberta Costa
	 * @date 31/05/2006
	 *
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public Collection<ResumoPendenciaAcumuladoHelper> retornaConsultaResumoPendencia( InformarDadosGeracaoRelatorioConsultaHelper
			informarDadosGeracaoRelatorioConsultaHelper) throws ControladorException;
	
	/**
	 * [UC0489] - Consultar Resumo das Ações de Cobrança
	 * 
	 * @author Ana Maria
	 * @date 06/11/2006
	 * 
	 * @return CobrancaAcaoHelper
	 * @throws ErroRepositorioException
	 */	
	public Collection consultarResumoCobrancaAcao(InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper) 
		throws ControladorException;
	
	/**
	 * [UC0489] - Consultar Resumo das Ações de Cobrança
	 * 
	 * @author Ana Maria
	 * @date 06/11/2006
	 * 
	 * @return CobrancaAcaoHelper
	 * @throws ErroRepositorioException
	 */	
	public Collection consultarResumoCobrancaAcaoPerfil(int anoMesReferencia, Integer idCobrancaAcao, 
			Integer idCobrancaAcaoSituacao, Integer idCobrancaAcaoDebito, Short idIndicador,InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoRelatorioConsultaHelper) 
		throws ControladorException;
	
	/**
	 * Método que gera resumo indicadores da cobrança
	 * 
	 * [UC????] - Gerar Resumo Indicadores da Cobrança
	 * 
	 * @author Rafael Corrêa
	 * @date 25/03/2008
	 * 
	 */
	public void gerarResumoIndicadoresCobranca(int idFuncionalidadeIniciada)
			throws ControladorException;
	
	/**
	 * [UC0489] - Consultar Resumo das Ações de Cobrança
	 * 
	 * Pesquisa as situações de débito da situação da ação de acordo com o
	 * indicador antesApos
	 * 
	 * @author Sávio Luiz
	 * @date 06/11/2006
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoDebitoComIndicador(
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper,
			Integer idCobrancaAcao, Integer idCobrancaAcaoSituacao,Integer idCobrancaAcaoDebito)throws ControladorException;
	
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
	public Collection consultarResumoCobrancaAcaoEventual(
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper)
			throws ControladorException;
	
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
	public Collection consultarResumoCobrancaAcaoEventualPerfil(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			Integer idCobrancaAcaoDebito,
			Short idIndicador,
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper)
			throws ControladorException;
	
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
	public Collection consultarCobrancaAcaoEventualDebitoComIndicador(
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper,
			Integer idCobrancaAcao, Integer idCobrancaAcaoSituacao,
			Integer idCobrancaAcaoDebito) throws ControladorException;
	
	/**
	 * [UC0489] - Consultar Resumo das Ações de Cobrança
	 * Popup de Motivo de Encerramento 
	 * 
	 * @author Francisco do Nascimento
	 * @date 13/06/2008
	 * 
	 * @return Collection CobrancaAcaoMotivoEncerramentoHelper
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoCobrancaAcaoMotivoEncerramento(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper,
			boolean ehExecucao)
			throws ControladorException;
	
	/**
	 * [UC0489] - Consultar Resumo das Ações de Cobrança
	 * Popup de Retorno de fiscalizacao 
	 * 
	 * @author Francisco do Nascimento
	 * @date 18/06/2008
	 * 
	 * @return Collection ResumoAcaoCobrancaSituacaoAcaoDetalheHelper
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoCobrancaAcaoRetornoFiscalizacao(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper)
			throws ControladorException;	

	/**
	 * [UC0617] - Consultar Resumo das Ações de Cobrança Eventual
	 * Popup de Motivo de Encerramento 
	 * 
	 * @author Francisco do Nascimento
	 * @date 19/06/2008
	 * 
	 * @return Collection CobrancaAcaoMotivoEncerramentoHelper
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoCobrancaAcaoMotivoEncerramentoEventual(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper,
			boolean ehExecucao)
			throws ControladorException;
	
	/**
	 * [UC0617] - Consultar Resumo das Ações de Cobrança Eventual
	 * Popup de Retorno de fiscalizacao 
	 * 
	 * @author Francisco do Nascimento
	 * @date 19/06/2008
	 * 
	 * @return Collection ResumoAcaoCobrancaSituacaoAcaoDetalheHelper
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoCobrancaAcaoRetornoFiscalizacaoEventual(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper)
			throws ControladorException;
	
	/**
     * Método que gera o resumo da pendencia Por Ano
     * 
     * @author Fernando Fontelles Filho
     * @date 23/03/2010
     */
    public void gerarResumoPendenciaPorAno(int idSetor, int idFuncionalidadeIniciada)
            throws ControladorException;
	
    /**
     * 
     * @author Arthur Carvalho
     * @date 30/09/2010
     * @param idLocalidade
     * @param idFuncionalidadeIniciada
     * @throws ControladorException
     */
    public void gerarGuiaPagamentoPorClienteResumoPendencia( int idLocalidade, int idFuncionalidadeIniciada) throws ControladorException;
    
    /**
     * [UC0489] - Consultar Resumo das Ações de Cobrança Popup de Motivo de
     * Encerramento
     * 
     * @author Ivan Sergio
     * @date 23/12/2010
     * @return Collection CobrancaAcaoMotivoEncerramentoHelper
     * @throws ErroRepositorioException
     */
    public Collection consultarResumoCobrancaAcaoTipoCorte(
            Integer idCobrancaAcao,
            Integer idCobrancaAcaoSituacao,
            InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper,
            boolean ehExecucao) throws ControladorException;
	
}
