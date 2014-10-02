package gcom.atendimentopublico;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacaoServicoACobrar;
import gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.ServicoCobrancaValor;
import gcom.atendimentopublico.ordemservico.bean.ObterValorDebitoHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelSuprimido;
import gcom.cobranca.CobrancaAcaoAtividadeComandoFiscalizacaoSituacao;
import gcom.faturamento.autoinfracao.AutosInfracao;
import gcom.gui.atendimentopublico.registroatendimento.FiltrarAcompanhamentoRegistroAtendimentoHelper;
import gcom.gui.relatorio.atendimentopublico.FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper;
import gcom.gui.relatorio.atendimentopublico.FiltrarRelatorioOSSituacaoHelper;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.relatorio.atendimentopublico.ordemservico.FiltrarRelatorioReligacaoClientesInadiplentesHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * Interfe, que disponibiliza os servi�os do Reposit�rio Atendimento P�blico 
 *
 * @author Leandro Cavalcanti
 * @date 10/07/2006
 */
public interface IRepositorioAtendimentoPublico {
	
	/**
	 * [UC-0355] - Efetuar Corte de Liga�� de �gua
	 * [SB001] Atualizar Hidrometro - (corte de liga��o de �gua)
	 * Atualizar um os campos lastId,dataUltimaAtualiza��o do imovel na base
	 * 
	 * @author Leandro Cavalcanti
	 * @param imovel
	 *            Descri��o do par�metro
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public void atualizarLigacaoAgua(Integer idImovel, Integer idLigacaoAguaSituacao, Integer numeroSeloCorte ) throws ErroRepositorioException;
	/**
	 * [UC-0355] - Efetuar Corte de Liga�� de �gua
	 * [SB001] Atualizar Hidrometro - (corte de liga��o de �gua)
	 * Atualizar os campos hidi_nnleituracorte e hidi_tmultimaalteracao de HidrometroInstalacaoHistorico
	 *			
	 *
	 * @author Leandro Cavalcanti
	 * @param imovel
	 *            Descri��o do par�metro
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public void atualizarHidrometroLIgacaoAgua(Integer imovelId,Integer numeroLeituraCorte) throws ErroRepositorioException ;
		
	/**
	 * [UC-0362] - Efetuar Instala��o de Hidr�metro
	 * [SB002] Atualizar Liga��o de �gua 
	 * Atualizar os campos hidi_id e lagu_tmultimaalteracao de LigacaoAgua
	 * 		
	 * @exception ErroRepositorioExceptions
	 *                Descri��o da exce��o
	 *
	 * @author Ana Maria
	 * @date 13/07/2006
	 *
	 * @param idLigacaoAgua
	 * @param idHidrometroInstalacaoHistorico
	 * @throws ErroRepositorioException
	 */
	public void atualizarHidrometroInstalacaoHistoricoLigacaoAgua(Integer idLigacaoAgua, Integer idHidrometroInstalacaoHistorico) throws ErroRepositorioException;
	
	/**
	 * [UC-0362] - Efetuar Instala��o de Hidr�metro
	 * [SB002] Atualizar Im�vel
	 * Atualizar os campos hidi_id e imov_tmultimaalteracao de Imovel
	 * 
	 * @exception ErroRepositorioExceptions
	 *                Descri��o da exce��o
	 *
	 * @author Ana Maria
	 * @date 13/07/2006
	 *
	 * @param idImovel
	 * @param idHidrometroInstalacaoHistorico
	 * @throws ErroRepositorioException
	 */
	public void atualizarHidrometroIntalacaoHistoricoImovel(Integer idImovel, Integer idHidrometroInstalacaoHistorico, Integer idPocoTipo) throws ErroRepositorioException;
	
	/**
	 * [UC-0362] - Efetuar Instala��o de Hidr�metro
	 * [SB003] Atualizar Hidr�metro
	 * Atualizar o campo hisi_id 
	 * 
	 * @exception ErroRepositorioExceptions
	 *                Descri��o da exce��o
	 *
	 * @author Ana Maria
	 * @date 17/07/2006
	 *
	 * @throws ErroRepositorioException
	 */
	public void atualizarSituacaoHidrometro(Integer idHidrometro, Integer situacaoHidrometro) throws ErroRepositorioException;
	
	/**
	 * [UC0396] - Inserir Tipo de retorno da OS Referida
	 * 
	 * [FS0005] Validar indicador de deferimento
	 * 
	 * @author lms
	 * @date 31/07/2006
	 *
	 * @throws ErroRepositorioException
	 */
	public int consultarTotalIndicadorDeferimentoAtivoPorServicoTipoReferencia(OsReferidaRetornoTipo osReferidaRetornoTipo) throws ErroRepositorioException;
	
	/**
	 * [UC0463] Atualizar Consumo M�nimo da Liga��o de �gua
	 * 
	 * @author Leonardo Regis
	 * @date 30/08/2006
	 * 
	 * @param ligacaoAgua
	 * @exception ErroRepositorioExceptions
	 */
	public void atualizarConsumoMinimoLigacaoAgua(LigacaoAgua ligacaoAgua) throws ErroRepositorioException;	
	

	
	/**
	 * [UC-0362] - Efetuar Instala��o de Hidr�metro
	 * [SB003] Atualizar Hidr�metro
	 * Atualizar o campo hisi_id 
	 * 
	 * @exception ErroRepositorioExceptions
	 *                Descri��o da exce��o
	 *
	 * @author Ana Maria
	 * @date 17/07/2006
	 *
	 * @throws ErroRepositorioException
	 */
	public void atualizarLocalArmazanagemHidrometro(Integer idHidrometro,Integer localArmazanagemHidrometro) 
		throws ErroRepositorioException;
	
	
	/**
	 * [UC-0362] - Efetuar Instala��o de Hidr�metro
	 * [SB003] Atualizar Hidr�metro
	 * Atualizar o campo hisi_id 
	 * 
	 * @exception ErroRepositorioExceptions
	 *                Descri��o da exce��o
	 *
	 * @author Ana Maria
	 * @date 17/07/2006
	 *
	 * @throws ErroRepositorioException
	 */
	public void atualizarHidrometroInstalacoHistorico(HidrometroInstalacaoHistorico hidrometroSubstituicaoHistorico) 
		throws ErroRepositorioException;	
	/**
	 * [UC0475] Obter Valor do D�bito
	 * 
	 * Verificar exist�ncia de hidr�metro na liga��o de �gua.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou n�o
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaHidrometroEmLigacaoAgua(Integer imovelId) throws ErroRepositorioException;
	
	/**
	 * [UC0475] Obter Valor do D�bito
	 * 
	 * Verificar exist�ncia de hidr�metro no im�vel.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou n�o
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaHidrometroEmImovel(Integer imovelId) throws ErroRepositorioException;
	
	/**
	 * [UC0475] Obter Valor do D�bito
	 * 
	 * Obter Capacidade de Hidr�metro pela Liga��o de �gua.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou n�o 
	 * @throws ErroRepositorioException
	 */
	public HidrometroCapacidade obterHidrometroCapacidadeEmLigacaoAgua(Integer imovelId) throws ErroRepositorioException;
	
	/**
	 * [UC0475] Obter Valor do D�bito
	 * 
	 * Obter Capacidade de Hidr�metro pelo Im�vel.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou n�o 
	 * @throws ErroRepositorioException
	 */
	public HidrometroCapacidade obterHidrometroCapacidadeEmImovel(Integer imovelId) throws ErroRepositorioException;
	
	/**
	 * [UC0475] Obter Valor do D�bito
	 * 
	 * Obter Valor do Debito pelos par�mtros passados.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param obterValorDebitoHelper
	 * @return o valor do d�bito 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterValorDebito(ObterValorDebitoHelper params) throws ErroRepositorioException;	
	
	/**
	 * M�todo que retorna o n�mero do hidr�metro da liga��o de �gua
	 * 
	 * @author Ana Maria
	 * @date 12/09/2006
	 * 
	 * @param idImovel
	 * @return
	 * @throws ControladorException
	 */

	public String pesquisarNumeroHidrometroLigacaoAgua(Integer idLigacaoAgua)
			throws ErroRepositorioException;
	
	/**
	 * M�todo que retorna o tipo da liga��o de �gua e a data do corte da liga��o de �gua
	 * 
	 * @author Ana Maria
	 * @date 18/08/2006
	 * 
	 * 
	 * @param idLigacaoAgua
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosLigacaoAgua(Integer idLigacaoAgua)
			throws ErroRepositorioException;
	
	/**
	 * Consulta os dados das ordens de servi�o para a gera��o do relat�rio
	 * 
	 * @author Rafael Corr�a
	 * @created 07/10/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemServicoProgramacaoRelatorio(Integer idEquipe, Date dataRoteiro)
			throws ErroRepositorioException;
	
	/**
	 * [UC0404] Manter Especifica��o da Situa��o do Imovel
	 * 
	 * Este caso de uso remove a especifica��o e os crit�rio
	 * 
	 * [SB0002] Remover Especifica��o da situacao
	 * 
	 * @author Rafael Pinto
	 * @created 08/11/2006
	 * 
	 * @throws ControladorException Controlador Exception
	 */
	public void removerEspecificacaoSituacaoImovelCriterio(String[] idsEspecificacaoSituacaoImovel)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa todos os ids das situa��es de liga��o de �gua.
	 *
	 * [UC0564 - Gerar Resumo das Instala��es de Hidr�metros]
	 *
	 * @author Pedro Alexandre
	 * @date 25/04/2007
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarTodosIdsSituacaoLigacaoAgua() throws ErroRepositorioException ;
	
	
	/**
	 * Pesquisa todos os ids das situa��es de liga��o de esgoto.
	 *
	 * [UC0564 - Gerar Resumo das Instala��es de Hidr�metros]
	 *
	 * @author Pedro Alexandre
	 * @date 25/04/2007
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarTodosIdsSituacaoLigacaoEsgoto() throws ErroRepositorioException ;
	
	/**
	 * 
	 * Este cso de uso permite efetuar a liga��o de �gua e eventualmente 
	 * a instala��o de hidr�metro, sem informa��o de RA sendo chamado direto pelo menu.
	 *
	 * [UC0579] - Efetuar Liga��o de �gua com Intala��o de Hidr�metro
	 *
	 * @author Fl�vio Leonardo
	 * @date 25/04/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarEfetuarLigacaoAguaHidrometroSemRA(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UC0XXX] Gerar Contrato de Presta��o de Servi�o
	 * 
	 * @author Rafael Corr�a
	 * @date 03/05/2007
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection obterDadosContratoPrestacaoServico(
			Integer idImovel) throws ErroRepositorioException;
	
	public void atualizarImovelLigacaoAguaInstalacaoHidrometroSemRA(Integer idImovel, Integer idHidrometro) throws ErroRepositorioException;
	
	/**
	 * [UC0582] - Emitir Boletim de Cadastro
	 * 
	 * Obt�m os dados necess�rio da liga��o de �gua, de esgoto e do hidr�metro
	 * instalado na liga��o de �gua
	 * 
	 * @author Rafael Corr�a
	 * @date 17/05/2007
	 * 
	 * @throws ErroRepositorioException
	 */
	public Object[] obterDadosLigacaoAguaEsgoto(
			Integer idImovel) throws ErroRepositorioException;
	
//	*********************************************************
	//****************CONTRATO PESSOA JURIDICA*****************
	
	public Cliente pesquisaClienteContrato(Integer idCliente) throws ErroRepositorioException;
	
	public ClienteImovel pesquisarDadosContratoJuridica(Integer idImovel) throws ErroRepositorioException;
	
	public String pesquisarMunicipio(Integer idImovel) throws ErroRepositorioException;
	
	//*********************************************************

	/**
	 * Substituicao de hidrometro
	 */
	public void atualizarSubstituicaoHidrometroInstalacoHistorico(
			HidrometroInstalacaoHistorico hidrometroSubstituicaoHistorico)
			throws ErroRepositorioException;
	
	/**
	 * [UC0482]Emitir 2� Via de Conta
	 *obter numero do hidr�metro na liga��o de �gua.
	 * 
	 * @author Vivianne Sousa
	 * @date 11/09/2007
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou n�o
	 * @throws ErroRepositorioException
	 */
	public String obterNumeroHidrometroEmLigacaoAgua(Integer imovelId)
			throws ErroRepositorioException;
	
	/**
	 * [UC0738] Retorna as informa��es para o relat�rio de certid�o negativa
	 * @param imo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioCertidaoNegativa( Imovel imo )
		throws ErroRepositorioException;
	
	/**
	 * Pesquisa os dados necess�rios para a gera��o do relat�rio
	 * 
	 * [UC0864] Gerar Certid�o Negativa por Cliente
	 * 
	 * @return
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioCertidaoNegativaCliente(Collection<Integer> idsClientes)
			throws ErroRepositorioException;
	
	/**
	 * [UC0541] Emitir 2a Via Conta Internet
	 *
	 * [FS0004] - Cliente n�o associado ao documento
	 *
	 * @author Raphael Rossiter
	 * @date 21/10/2008
	 *
	 * @param idImovel
	 * @param cpf
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteAssociadoImovelComCPF(Integer idImovel, String cpf)
		throws ErroRepositorioException ;
	
	/**
	 * [UC0541] Emitir 2a Via Conta Internet
	 *
	 * [FS0004] - Cliente n�o associado ao documento
	 *
	 * @author Raphael Rossiter
	 * @date 21/10/2008
	 *
	 * @param idImovel
	 * @param cnpj
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteAssociadoImovelComCNPJ(Integer idImovel, String cnpj)
		throws ErroRepositorioException ;
	
	/**
	 * [UC0482] Emitir 2a Via Conta
	 *
	 * [FS0002] - Cliente sem documento
	 *
	 * @author Raphael Rossiter
	 * @date 24/10/2008
	 *
	 * @param idImovel
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteAssociadoImovelComDocumentoInformado(Integer idImovel)
		throws ErroRepositorioException ;
	
	 /**
     * [UC0150] Retificar Conta
     * @author Vivianne Sousa
     * @date 26/11/2008
     */
    public BigDecimal obterPercentualAguaConsumidaColetadaImovel(Integer idImovel)
            throws ErroRepositorioException ;
    
    /**
	 * [UC0898] Atualizar Autos de Infra��o com prazo de Recurso Vencido
	 * 
	 * @author S�vio Luiz
	 * @date 08/05/2009
	 */
    public Collection<AutosInfracao> pesquisarAutoInfracaoRecursoVencido(Integer idSituacaoAutoInfracao,Date prazoEntregaRecursoVencido)
            throws ErroRepositorioException;
    
    /**
	 * [UC0898] Atualizar Autos de Infra��o com prazo de Recurso Vencido
	 * 
	 * [SB0001] Atualizar Autos Infra��o
	 * 
	 * @author S�vio Luiz
	 * @date 08/05/2009
	 */
    public void atualizarAutosInfracao(Collection idsAutosInfracao,
    		Integer idSituacaoAutoInfracao) throws ErroRepositorioException;
    
    /**
	 * [UC0898] Atualizar Autos de Infra��o com prazo de Recurso Vencido
	 * 
	 * @author S�vio Luiz
	 * @date 08/05/2009
	 */
    public Collection<FiscalizacaoSituacaoServicoACobrar> pesquisarFiscalizacaoSituacaoServicoACobrar(Integer idFiscalizacaoSituacao)
            throws ErroRepositorioException;
    
    /**
	 * [UC0996] Emitir Ordem de Fiscaliza��o para im�veis suprimidos
	 * 
	 * @author Hugo Amorim
	 * @date 08/03/2010
	 * @param idFuncionalidadeIniciada
	 * @param usuarioLogado
	 * @param setorComercial
	 */
    public Collection<Object[]> pesquisarImoveisBatchEmitirOrdemFiscalizacao(
    		Integer idSetorComercial,Date data,Integer quantidadeInicio, Integer quantidadeMaxima)throws ErroRepositorioException;
    
	/**
	 * [UC0996] Emitir Ordem de Fiscaliza��o para im�veis suprimidos
	 * 
	 * 	Inseri objeto na base do tipo ImovelSuprimido
	 * 
	 * @author Hugo Amorim
	 * @date 08/03/2010
	 * @param idFuncionalidadeIniciada
	 * @param usuarioLogado
	 * @param setorComercial
	 */
	public void inserirImovelSuprimido(ImovelSuprimido imovelSuprimido)throws ErroRepositorioException;
	
	 /**
	 * [UC0996] Emitir Ordem de Fiscaliza��o para im�veis suprimidos
	 * 
	 * 	Pesquisas linhas do txt
	 * 
	 * @author Hugo Amorim
	 * @date 08/03/2010
	 * @param idFuncionalidadeIniciada
	 * @param usuarioLogado
	 * @param setorComercial
	 */
	public Collection<ImovelSuprimido> pesquisarDadosEmitirArquivoTextoDeOrdemFiscalizacao(
			Integer quantidadeInicio, Integer quantidadeMaxima)
			throws ErroRepositorioException;
	
	
	/**
	 * [SB0002] � Replicar os servi�os existentes para uma nova vig�ncia e valor.
	 * Pesquisa a �ltima vig�ncia de cada tipo servi�o, e retorna uma cole��o. 
	 * 
	 * @author Josenildo Neves
	 * @date 03/02/2010
	 */
	public Collection<ServicoCobrancaValor> pesquisarServicoCobrancaValorUltimaVigencia(Integer numeroPagina) throws ErroRepositorioException;
	
	/**
	 [SB0002] � Replicar os servi�os existentes para uma nova vig�ncia e valor.
	 * Pesquisa a �ltima vig�ncia de cada tipo servi�o, e retorna o total.   
	 * 
	 * @author Josenildo Neves
	 * @date 03/02/2010
	 */
	public Integer pesquisarServicoCobrancaValorUltimaVigenciaTotal() throws ErroRepositorioException;
	
	/**
	 * [SB0002] � Replicar os Valores de Cobran�a de Servi�o existentes para uma nova vig�ncia e valor.
	 * Pesquisa a �ltima vig�ncia de cada tipo Cobran�a, e retorna uma cole��o. 
	 * 
	 * @author Hugo Leonardo
	 * @date 23/04/2010
	 */
	public Collection<ServicoCobrancaValor> replicarServicoCobrancaValorUltimaVigencia(String[] selecionados) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0391] Inserir valor cobran�a Servi�o.
	 * 
	 * Verificar se existe vig�ncia j� cadastrada para o Servi�o Tipo.
	 * 
	 * @author Hugo Leonardo
	 * @param dataVigenciaInicial
	 * @param dataVigenciaFinal
	 * @param idServicoTipo
	 * @param opcao
	 * @throws ErroRepositorioException
	 * @data 03/05/2010
	 * 
	 * return String
	 * 
	 * @see Caso a opcao = 1 - verifica as situa��es de inserir e atualizar Servi�o Tipo.
	 * @see Caso a opcao = 2 - verifica a situa��o de retificar Servi�o Tipo.
	 */
	public String verificarExistenciaVigenciaServicoTipo(String dataVigenciaInicial, String dataVigenciaFinal, Integer idServicoTipo)
		throws ErroRepositorioException;
	
	/**
	 * [UC0475] Obter Valor do D�bito
	 * 
	 * Obter Valor do Debito pelos par�mtros passados.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param obterValorDebitoHelper
	 * @return o valor do d�bito 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterValorDebitoHidrometroCapacidade(ObterValorDebitoHelper params) throws ErroRepositorioException;	
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 *  [SB0034] � Verificar RA de urg�ncia
	 * 
	 * Verifica se o Registro de Atendimento tem o nivel selecionado como Urg�ncia
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ErroRepositorioException
	 * @data   03/06/2010
	 * 
	 * 
	 */
	public Integer verificarRegistroAtendimentoUrgencia(Integer idRegistroAtendimento)
		throws ErroRepositorioException;	
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 *  [SB0034] � Verificar RA de urg�ncia
	 *  
	 * Retorna um ou todos usu�rios da unidade relacionada a RA, 
	 *  da tabela "VisualizacaoRaUrgencia" 
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento, ID da Unidade, ID do Usu�rio 
	 * @throws ErroRepositorioException
	 * @data   04/06/2010
	 * 
	 */
	public Collection pesquisarUsuarioVisualizacaoRaUrgencia(Integer idRegistroAtendimento)
		throws ErroRepositorioException;
	
	/**
	 * [UC0503] Tramitar Conjunto Registro Atendimento	 * 
	 *  [SB0004] � Verificar RA de urg�ncia
	 *  
	 * Retorna um ou todos usu�rios da unidade relacionada a RA, 
	 *  da tabela "VisualizacaoRaUrgencia" 
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento, ID da Unidade, ID do Usu�rio 
	 * @throws ErroRepositorioException
	 * @data   04/06/2010
	 * 
	 */
	public Collection pesquisarVisualizacaoRaUrgencia(Integer idRegistroAtendimento, Integer idUnidade, Integer idUsuario)
		throws ErroRepositorioException;
		
	/**
	 * [UC0503] Tramitar Conjunto Registro Atendimento	 * 
	 *  [SB0004] � Verificar RA de urg�ncia
	 * 
	 * Verifica se o Registro de Atendimento j� est� relacionado a uma Unidade informada.
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ErroRepositorioException
	 * @data   05/06/2010
	 * 
	 */
	public Integer verificarUsuariosRegistroAtendimentoUrgencia(Integer idRegistroAtendimento, Integer idUnidade)
		throws ErroRepositorioException;
	
	
	/**	 
	 * [UC1028] Exibir Registro Atendimento Urg�ncia
	 *  
	 * Verifica se o Usuario possui algum Registro de Atendimento urgente.
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ErroRepositorioException
	 * @data   07/06/2010
	 * 	  
	 */
	public Collection verificarUsuarioRegistroAtendimentoUrgencia(Integer idUsuario)
		throws ErroRepositorioException;
	
	
	/**  
	 * Atualiza um ou v�rios campos da tabela "VisualizacaoRaUrgencia" 
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento, ID da Unidade, ID do Usu�rio, indicador Tramite e indicador Visualizacao 
	 * @throws ErroRepositorioException
	 * @data   10/06/2010
	 * 
	 */
	public void atualizarUsuarioRegistroAtendimentoUrgencia(Integer idRegistroAtendimento, String idUsuarios, Integer idUsuario, Integer indicadorTramite, Integer indicadorVisualizacao)
            throws ErroRepositorioException;
	
    /**
	 * [UC0251] Gerar Atividade de A��o de Cobran�a
	 * 
	 * @author Hugo Amorim
	 * @date 15/07/2010
	 */
	public Collection<CobrancaAcaoAtividadeComandoFiscalizacaoSituacao> 
		pesquisarCobrancaAcaoAtividadeComandoFiscalizacaoSituacao(
			Integer idComando, Collection idsSituacos)throws ErroRepositorioException;
	
	/**
	 * [UC1056] ? Gerar Relat�rio de Acompanhamento dos Registros de Atendimento
	 * 
	 * @author Hugo Leonardo
	 * @date 28/09/2010
	 * 
	 * @param FiltrarAcompanhamentoRegistroAtendimentoHelper
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioAcompanhamentoRAAnalitico( FiltrarAcompanhamentoRegistroAtendimentoHelper helper) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1056] Gerar Relat�rio de Acompanhamento dos Registros de Atendimento
	 * 
	 * @author Hugo Leonardo
	 * @date 30/09/2010
	 * 
	 * @param FiltrarAcompanhamentoRegistroAtendimentoHelper
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer countPesquisarRelatorioAcompanhamentoRAAnalitico( FiltrarAcompanhamentoRegistroAtendimentoHelper helper) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1056] Gerar Relat�rio de Acompanhamento dos Registros de Atendimento
	 * 
	 * @author Hugo Leonardo
	 * @date 01/10/2010
	 * 
	 * @param FiltrarAcompanhamentoRegistroAtendimentoHelper
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioAcompanhamentoRASinteticoAberto( FiltrarAcompanhamentoRegistroAtendimentoHelper helper) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1056] Gerar Relat�rio de Acompanhamento dos Registros de Atendimento
	 * 
	 * @author Hugo Leonardo
	 * @date 01/10/2010
	 * 
	 * @param FiltrarAcompanhamentoRegistroAtendimentoHelper
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioAcompanhamentoRASinteticoEncerrado( FiltrarAcompanhamentoRegistroAtendimentoHelper helper) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1107] Manter Custo de Pavimento por Repavimentadora
 	 * 
 	 * @author Hugo Leonardo
 	 * @date 27/12/2010
 	 * 
 	 * @param idRepavimentadora, idPavimento, indicadorPavimento: 1-Rua, 2-Cal�ada
 	 * @return boolean
	 */
	public boolean verificaRemoverCustoPavimentoPorRepavimentadora(Integer idRepavimentadora,
			Integer idPavimento, Integer indicadorPavimento)throws ErroRepositorioException;
	
	/**
	 * [UC1107] Manter Custo de Pavimento por Repavimentadora
 	 * 
 	 * @author Hugo Leonardo
 	 * @date 28/12/2010
 	 * 
 	 * @param id, idRepavimentadora, idPavimento, dataInicio, dataFinal, indicadorPavimento: 1-Rua, 2-Cal�ada
 	 * @return Integer
	 */
	public Integer verificaAtualizarCustoPavimentoPorRepavimentadora(Integer idAtualizacao, Integer idRepavimentadora,
			Integer idPavimento, Date dataInicio, Date dataFinal, Integer indicadorPavimento, Integer tipo)
		throws ErroRepositorioException;
	
	/**
	 * [UC1107] Manter Custo de Pavimento por Repavimentadora
 	 * 
 	 * 		[FS0010] Verificar se existem dias sem valor
 	 * 
 	 * @author Hugo Leonardo
 	 * @date 11/01/2011
 	 * 
 	 * @param id, idRepavimentadora, idPavimento, dataInicio, dataFinal, indicadorPavimento: 1-Rua, 2-Cal�ada
 	 * @return Integer
	 * 
	 * VerificarExistenciDiasSemValor
	 */
	public Integer verificarExistenciDiasSemValorCustoPavimentoPorRepavimentadora(Integer idAtualizacao, 
			Integer idRepavimentadora, Integer idPavimento, Date dataInicio, 
			Date dataFinal, Integer indicadorPavimento, Integer tipo) throws ErroRepositorioException;
	
	/**
     * [UC0412] Manter Tipo de Servi�o
     * 
     * @author Vivianne Sousa
     * @created 07/01/2011
     */
    public void removerServicoTipoBoletim(Integer idServicoTipo)
            throws ErroRepositorioException ;
	
	/**
	 * [UC1120] Gerar Relat�rio de religa��o de clientes inadimplentes.
	 *
	 * @author Hugo Leonardo
	 * @date 25/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioReligacaoClientesInadiplentesOS( 
			FiltrarRelatorioReligacaoClientesInadiplentesHelper relatorioHelper) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1120] Gerar Relat�rio de religa��o de clientes inadimplentes.
	 *
	 * @author Hugo Leonardo
	 * @date 28/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioReligacaoClientesInadiplentes( 
			Integer os, Integer imovel, Date dataEncerramentoOS, Integer tipo) throws ErroRepositorioException;
	
	/**
	 * [UC1120] Gerar Relat�rio de religa��o de clientes inadimplentes.
	 *
	 * @author Hugo Leonardo
	 * @date 31/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioReligacaoClientesInadiplentes( Collection<Integer> idsOS) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1120] Gerar Relat�rio de religa��o de clientes inadimplentes
	 *
	 * @author Hugo Leonardo
	 * @date 01/02/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioReligacaoClientesInadiplentesRecorrentes( 
			Integer imovel, FiltrarRelatorioReligacaoClientesInadiplentesHelper relatorioHelper) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1120] Gerar Relat�rio de religa��o de clientes inadimplentes.
	 *
	 * @author Hugo Leonardo
	 * @date 09/02/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioReligacaoClientesInadiplentesDatasOS( 
			FiltrarRelatorioReligacaoClientesInadiplentesHelper relatorioHelper, Integer imovel) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1120] Gerar Relat�rio de religa��o de clientes inadimplentes
	 *
	 * @author Hugo Leonardo
	 * @date 16/02/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRecorrenciaPorUsuarioQueAbriuOuEncerrouOS( 
			Integer usuario, FiltrarRelatorioReligacaoClientesInadiplentesHelper relatorioHelper) 
		throws ErroRepositorioException;

	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobran�a
	 * 
	 * Consulta chamada pelo "[FS0008 � Validar Motivo Encerramento]" 
	 * 
	 * @author Mariana Victor
	 * @data 20/06/2011
	 */
	public Boolean verificarAtendimentoMotivoEncerramento(Integer idMotivoEncerramento) throws ErroRepositorioException;
	
	/**
	 * [UC1177] Gerar Relat�rio de Ordens de Servi�o por Situa��o
	 * 
	 * O segundo par�metro (boletimGerado) � um booleano que
	 * indica se para um dado grupo de cobran�a e um m�s referencia
	 * foi gerado um boletim de medi��o.
	 * 
	 * @author Diogo Peixoto
	 * @date 09/06/2011
	 * 
	 * @param FiltrarRelatorioOSSituacaoHelper
	 * @param boletimGerado
	 * @return Collection<FiltrarRelatorioOSSituacaoHelper>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> filtrarRelatorioOSSituacao(FiltrarRelatorioOSSituacaoHelper helper, boolean boletimGerado)
		throws ErroRepositorioException;
	
	
	/**
     * Obt�m a cole��o de perfis de tipo de servi�o para OS.
     * 
     * @author Hugo Azevedo
     * @date 22/06/2011
     * 
     * @throws ErroRepositorioException
     */
	public Collection obterColecaoTipoOSgerada() throws ErroRepositorioException;
	
	
	/**
     * Obt�m a cole��o de OS a partir dos par�metros passados pela funcionalidade de Acompanhamento de Cobran�a por Resultado.
     * 
     * @author Hugo Azevedo
     * @date 27/06/2011
     * 
     * @throws ErroRepositorioException
     */
	
	public Collection obterColecaoImovelOSCobrancaResultado(String[] categoriaImovel,
			  String[] perfilImovel,
			  String[] gerenciaRegional,
			  String[] unidadeNegocio,
			  String idLocalidadeInicial,
			  String idLocalidadeFinal,
			  String idSetorComercialInicial,
			  String idSetorComercialFinal,
			  String idQuadraInicial,
			  String idQuadraFinal,
			  String tipoServico,
			  String comando) throws ErroRepositorioException;
	
	/**
	 * [UC1178] Gerar Relat�rio de Acompanhamento dos Boletins de Medi��o
	 * 
	 * O segundo par�metro (relatorioDefinitivo) � um booleano que
	 * indica se o relat�rio � definitivo ou n�o, pois o resultado
	 * da query � diferente para os relat�rios definitivos e os
	 * de simula��o
	 * 
	 * @author Diogo Peixoto
	 * @date 26/07/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @param relatorioDefinitivo
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> filtrarRelatorioAcompanhamentoBoletimMedicao(
			FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro, boolean relatorioDefinitivo) throws ErroRepositorioException;
	
	/**
	 * [UC1178] Gerar Relat�rio de Acompanhamento dos Boletins de Medi��o
	 * 
	 * M�todo que vai retornar as quantidades acumuladas e os valores acumulados
	 * no per�odo para gera��o do relat�rio de acompanhamento do boletim de medi��o.
	 * 
	 * @author Diogo Peixoto
	 * @date 01/08/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @param relatorioDefinitivo
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> filtrarRelatorioAcompanhamentoBoletimMedicaoAcumuladas(FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1178] Gerar Relat�rio de Acompanhamento dos Boletins de Medi��o
	 * 
	 * O segundo par�metro (relatorioDefinitivo) � um booleano que
	 * indica se o relat�rio � definitivo ou n�o, pois o resultado
	 * da query � diferente para os relat�rios definitivos e os
	 * de simula��o
	 * 
	 * @author Diogo Peixoto
	 * @date 26/07/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @param relatorioDefinitivo
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<BigDecimal> filtrarRelatorioAcompanhamentoBoletimMedicaoPenalidades(FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro,
			boolean relatorioDefinitivo) throws ErroRepositorioException;
	
	/**
	 * [UC1178] Gerar Relat�rio de Acompanhamento dos Boletins de Medi��o
	 * 
	 * @author Diogo Peixoto
	 * @date 01/08/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @return Taxa de Sucesso do Boletim de Medi��o
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarTaxaSucessoBoletimMedicao(FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro) throws ErroRepositorioException;
	
	
	/**
	 * [UC1186] Gerar Relat�rio Ordem de Servi�o Cobran�a p/Resultado
	 * 
	 * Pesquisar as Ordens de servi�os a partir de seu im�vel e tipo de servi�o
	 * 
	 * @author Hugo Azevedo
	 * @data 14/01/2011
	 */
	
	public Collection obterOSImovelTipoServico(Integer id, Integer tipoServico) throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC1186] Gerar Relat�rio Ordem de Servi�o Cobran�a p/Resultado
	 * 
     * Obt�m a quantida de OS a partir dos par�metros passados pela funcionalidade de Acompanhamento de Cobran�a por Resultado.
     * 
     * @author Hugo Azevedo
     * @date 27/06/2011
     * 
     * @throws ErroRepositorioException
     */
	
	public Collection obterTotalOSColecaoImovelTipoServico(Collection colecaoImovel,Integer tipoServico) throws ErroRepositorioException;
	
	/**
	 * [UC1189] Inserir Registro de Atendimento Loja Virtual
	 * 
	 * @author Magno Gouveia
	 * @date 12/07/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarSolicitacaoTipoLojaVirtual() throws ErroRepositorioException;
	
	/**
	 * [UC1196] Exibir Lojas de Atendimento na Loja Virtual
	 * [SB0001] Selecionar Munic�pios da Regi�o
	 * 
	 * @author Magno Gouveia
	 * @date 14/07/2011
	 * 
	 * @return colecaoDeMunicipios
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarMunicipiosLojaVirtualCompesa() throws ErroRepositorioException;

	/**
	 * [UC1196] Exibir Lojas de Atendimento na Loja Virtual
	 * [SB0005] Exibir Dados da Loja
	 * 
	 * @author Magno Gouveia
	 * @date 14/07/2011
	 * 
	 * @param id do municipio
	 * @return colecaoDeLojas
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarLojasDeAtendimentoLojaVirtualCompesa(Integer idMunicipio) throws ErroRepositorioException;
	
	/**
	 * [UC1178] Gerar Relat�rio de Acompanhamento dos Boletins de Medi��o
	 * 
	 * M�todo que vai retornar um booleano indicando se o relat�rio de acompanhamento de boletim
	 * medi��o � definitivo ou n�o definitivo.
	 * 
	 * @author Diogo Peixoto
	 * @date 26/07/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @return boolean
	 * @throws ErroRepositorioException
	 */
	public boolean gruposIniciaodsJaForamEncerrados(FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro)	throws ErroRepositorioException;
	
	/**
	 * [UC1178] Gerar Relat�rio de Acompanhamento dos Boletins de Medi��o
	 * 
	 * @author Diogo Peixoto
	 * @date 28/07/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @param relatorioDefinitivo
	 * @return Quantidade de OS Penalizadas para determinado boletim de medi��o
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeOSPenalizadas(FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro) 
		throws ErroRepositorioException;
	
	
	/**
	 * [UC1178] Gerar Relat�rio de Acompanhamento dos Boletins de Medi��o
	 * 
	 * @author Diogo Peixoto
	 * @date 28/07/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @param relatorioDefinitivo
	 * @return Quantidade de OS Executadas para determinado boletim de medi��o
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeOSExecutadas(FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro) 
		throws ErroRepositorioException;
	
	public Collection obterColecaoOSFiscalizacaoNaoExecutadas() throws ErroRepositorioException;
	
	/**
	 * [UC1199] � Acompanhar Arquivos de Roteiro
	 * [SB0003] � Pesquisar Fotos da OS
	 * 
	 * M�todo que vai retornar as fotos de uma determinada
	 * ordem de servi�o passada no par�metro.
	 * 
	 * @author Diogo Peixoto
	 * @date 12/08/2011
	 * 
	 * @param Integer - ID da Ordem de Servi�o
	 * 
	 * @return Collection<Object[]> - Cole��o das Fotos da OS
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarFotosOrdemServico(Integer idFoto) throws ErroRepositorioException;
	
	/**
	 * [UC1199] � Acompanhar Arquivos de Roteiro
	 * [SB0003] � Pesquisar Fotos da OS
	 * 
	 * M�todo que vai retornar as fotos de uma determinada
	 * ordem de servi�o passada no par�metro.
	 * 
	 * @author Diogo Peixoto
	 * @date 12/08/2011
	 * 
	 * @param Integer - ID da Foto da Ordem de Servi�o
	 * 
	 * @return Collection<Object[]> - Foto da Ordem de Servi�o
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarFotosOrdemServicoPorIdFoto(Integer idFoto) throws ErroRepositorioException;
	
	public Object[] pesquisarPrazoMedioAtendimentoOSRelatorioBIG(Date dataInicial,
			Date dataFinal, Integer idLocalidade)
			throws ErroRepositorioException;
	
	public Object[] pesquisarNovasLigacoesAguaRelatorioBIG(Date dataInicial,
			Date dataFinal, Integer idLocalidade)
			throws ErroRepositorioException;
	
	public Object[] pesquisarNovasLigacoesEsgotoRelatorioBIG(Date dataInicial,
			Date dataFinal, Integer idLocalidade)
			throws ErroRepositorioException;

	public Integer pesquisarQuantidadeConsumidoresRelatorioBIG(Integer idLocalidade,
			Integer situacao) throws ErroRepositorioException;
	
	/**
	 * @author Wellington Rocha
	 * Data: 03/07/2012
	 * 
	 * Pesquisar todas os Locais de Instala��o de Ramal
	 * 
	 * Gera��o de Rotas para Recadastramento
	 * 
	 * @return Collection
	 * @throws ControladorException
	 *  
	 */
 	public Collection pesquisarRamalLocalInstalacao() throws ErroRepositorioException;
	
}
