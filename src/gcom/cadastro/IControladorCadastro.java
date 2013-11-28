package gcom.cadastro;


import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificado;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoBinario;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoCritica;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoLinha;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.cadastro.empresa.EmpresaContratoCobranca;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.geografico.MunicipioFeriado;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.EntidadeBeneficente;
import gcom.cadastro.imovel.ImovelInscricaoAlterada;
import gcom.cadastro.imovel.ImovelProgramaEspecial;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.imovel.bean.GerarArquivoTextoAtualizacaoCadastralHelper;
import gcom.cadastro.imovel.bean.ImovelGeracaoTabelasTemporariasCadastroHelper;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.QuadraFace;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaAlteracaoHistorico;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.sistemaparametro.bean.DadosEnvioEmailHelper;
import gcom.cadastro.tarifasocial.TarifaSocialComandoCarta;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.relatorio.cadastro.FiltrarRelatorioAcessoSPCHelper;
import gcom.gui.relatorio.cadastro.GerarRelatorioAlteracoesCpfCnpjHelper;
import gcom.gui.relatorio.cadastro.micromedicao.FiltrarRelatorioColetaMedidorEnergiaHelper;
import gcom.gui.relatorio.seguranca.GerarRelatorioAlteracoesSistemaColunaHelper;
import gcom.micromedicao.ArquivoTextoLigacoesHidrometroHelper;
import gcom.micromedicao.Rota;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.relatorio.cadastro.GerarRelatorioAtualizacaoCadastralViaInternetHelper;
import gcom.relatorio.cadastro.RelatorioAcessoSPCBean;
import gcom.relatorio.cadastro.RelatorioBoletimCadastroIndividualBean;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisAtivosNaoMedidosHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisConsumoMedioHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisFaturasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisProgramasEspeciaisHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisTipoConsumoHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisUltimosConsumosAguaHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisAtivosNaoMedidosHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisConsumoMedioHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisFaturasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisProgramasEspeciaisHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisSituacaoLigacaoAguaHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisTipoConsumoHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisUltimosConsumosAguaHelper;
import gcom.relatorio.cadastro.micromedicao.RelatorioColetaMedidorEnergiaHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.FachadaException;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;





/**
 * Interface Controlador Cobranca PADRÃO
 * 
 * @author Rômulo Aurélio 
 * @date 24/11/2009
 */

public interface IControladorCadastro {
	
	
	/**
	 * Permite inserir um Histórico Alteração de Sistema
	 * 
	 * [UC0217] Inserir Historico Alteracao Sistema
	 * 
	 * @author Thiago Tenorio
	 * @date 30/03/2006
	 * 
	 */

	public Integer inserirHistoricoAlteracaoSistema(
			SistemaAlteracaoHistorico sistemaAlteracaoHistorico)
			throws ControladorException;

	/**
	 * Metódo responsável por inserir uma Gerência Regional
	 * 
	 * [UC0000 - Inserir Gerencia Regional
	 * 
	 * @author Thiago Tenório
	 * @date 27/06/2006, 16/11/2006
	 * 
	 * @param agencia
	 *            bancaria
	 * @param usuarioLogado
	 * @return
	 * @throws ControladorException
	 */
	public Integer inserirGerenciaRegional(GerenciaRegional gerenciaRegional)
			throws ControladorException;

	/**
	 * [UC0391] Atualizar Gerência Regional.
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
	public void atualizarGerenciaRegional(GerenciaRegional gerenciaRegional)
			throws ControladorException;

	/**
	 * Pesquisa as empresas que serão processadas no emitir contas
	 * 
	 * @author Sávio Luiz
	 * @date 09/01/2007
	 * 
	 */
	public Collection pesquisarIdsEmpresa() throws ControladorException;

	
	/**
	 * 
	 * Recebe uma arquivo e pra cada linha desse arquivo ele processa o imovelCelular ou ClienteImovelCelular
	 *
	 * @author Thiago Toscano
	 * @date 16/02/2009
	 *
	 * @param file
	 * @throws ControladorException
	 */
	public void carregarImovelAtualizacaoCadastral(BufferedReader buffer) throws ControladorException;

	/**
	 * Informar Parametros do Sistema
	 * 
	 * @author Rômulo Aurélio
	 * @date 09/01/2007
	 * 
	 */

	public void informarParametrosSistema(SistemaParametro sistemaParametro,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * [UC0534] Inserir Feriado
	 * 
	 * @author Kassia Albuquerque
	 * @date 17/01/2007
	 * 
	 */
	public Integer inserirFeriado(NacionalFeriado nacionalFeriado,
			MunicipioFeriado municipioFeriado, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * Pesquisa os feriados(nacionais e municipais)
	 * 
	 * @author Kássia Albuquerque
	 * @date 22/01/2007
	 * 
	 */
	public Collection pesquisarFeriado(Short tipoFeriado, String descricao,
			Date dataFeriado, Date dataFeriadoFim, Integer idMunicipio,
			Integer numeroPagina) throws ControladorException;

	/**
	 * Pesquisar quantidade de registro dos feriados(nacionais e municipais)
	 * 
	 * @author Kássia Albuquerque
	 * @date 22/01/2007
	 * 
	 */
	public Integer pesquisarFeriadoCount(Short tipoFeriado, String descricao,
			Date dataFeriadoInicio, Date dataFeriadoFim, Integer idMunicipio)
			throws ControladorException;

	/**
	 * [UC0535] Manter Feriado [SB0001] Atualizar Feriado
	 * 
	 * @author Kassia Albuquerque
	 * @date 27/01/2006
	 * 
	 * @pparam feriado
	 * @throws ControladorException
	 */

	public void atualizarFeriado(NacionalFeriado nacionalFeriado,
			MunicipioFeriado municipioFeriado, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * [UC0535] Manter Feriado
	 * 
	 * Remover Feriado
	 * 
	 * @author Kassia Albuquerque
	 * @date 29/01/2007
	 * 
	 * @pparam municipio
	 * @throws ControladorException
	 */
	public void removerFeriado(String[] ids, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * Pesquisar os ids do Setor comercial pela localidade
	 * 
	 * @author Ana Maria
	 * @date 07/02/2007
	 * 
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsSetorComercial(Integer idLocalidade)
			throws ControladorException;
	
	/**
	 * Informar Mensagem do Sistema 
	 * 
	 * @author Kássia Albuquerque
	 * @date 02/03/2007
	 * 
	 */
	public void atualizarMensagemSistema(SistemaParametro sistemaParametro,Usuario usuarioLogado)
			throws ControladorException;
	
	/**
	 * Pesquisa os dados do email do batch para ser enviado
	 * 
	 * @author Sávio Luiz
	 * @date 13/03/2007
	 * 
	 */
	public EnvioEmail pesquisarEnvioEmail(Integer idEnvioEmail)
			throws ControladorException;
	
	public DadosEnvioEmailHelper pesquisarDadosEmailSistemaParametros()
	throws ControladorException;
	/**
	 * [UC????] Inserir Funcionario
	 * 
	 * @author Rômulo Aurélio
	 * @date 12/04/2007
	 * 
	 */
	public void inserirFuncionario(Funcionario funcionario,
			 Usuario usuarioLogado)
			throws ControladorException;
	
	/**
	 * [UC????] Atualizar Funcionario
	 * 
	 * @author Rômulo Aurélio
	 * @date 17/04/2007
	 * 
	 * @param funcionario, usuarioLogado, idFuncionario
	 * 
	 */
	public void atualizarFuncionario(Funcionario funcionario,
			 Usuario usuarioLogado)	throws ControladorException;
	
	/**
	 * Pesquisar todos ids dos setores comerciais.
	 *
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 *
	 * @author Pedro Alexandre
	 * @date 25/04/2007
	 *
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarTodosIdsSetorComercial() throws ControladorException;
	
	/**
	 * Este caso de uso permite a emissão de boletins de cadastro
	 * 
	 * [UC0582] Emitir Boletim de Cadastro
	 * 
	 * @author Rafael Corrêa
	 * @data 15/05/2007
	 * 
	 * @param
	 * @return void
	 */
	public void emitirBoletimCadastro(
			CobrancaAcaoAtividadeCronograma cronogramaAtividadeAcaoCobranca,
			CobrancaAcaoAtividadeComando comandoAtividadeAcaoCobranca, Date dataAtualPesquisa,
			CobrancaAcao cobrancaAcao, int idFuncionalidadeIniciada)
			throws ControladorException;
	
	/**
	 * Metódo responsável por inserir um Cliente Tipo
	 * 
	 * [UC???? - Inserir Cliente Tipo
	 * 
	 * @author Thiago Tenório
	 * @date 18/06/2007
	 * 
	 * @param ClienteTipo
	 *           
	 * @param usuarioLogado
	 * @return
	 * @throws ControladorException
	 */
	public Integer inserirClienteTipo(
			ClienteTipo clienteTipo, Usuario usuarioLogado)
			throws ControladorException;
	
	
	/**
	 * Metódo responsável por inserir um Cliente Tipo
	 * 
	 * [UC???? - Inserir Cliente Tipo
	 * 
	 * @author Thiago Tenório
	 * @date 18/06/2007
	 * 
	 * @param ClienteTipo
	 *           
	 * @param usuarioLogado
	 * @return
	 * @throws ControladorException
	 */
	public void atualizarClienteTipo(
			ClienteTipo clienteTipo)
			throws ControladorException;
	
	/**
	 * Este caso de uso permite a emissão de boletins de cadastro
	 * 
	 * [UC0582] Emitir Boletim de Cadastro pelo Filtro Imóvel por Outros Critérios
	 * 
	 * @param
	 * @return void
	 */
	public byte[] emitirBoletimCadastro(String idImovelCondominio, String idImovelPrincipal,
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
			String idUnidadeNegocio, String indicadorCpfCnpj, String cpfCnpj) throws ControladorException;
	
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

	public Collection pesquisarClientesSubordinados(Integer idCliente)

	throws ControladorException;
	
	/**
	 * [UC0725] Gerar Relatório de Imóveis por Situação da Ligação de Agua
	 * 
	 * @author Rafael Pinto
	 * @date 03/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisSituacaoLigacaoAguaHelper> pesquisarRelatorioImoveisSituacaoLigacaoAgua(
		FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper filtro) 
		throws ControladorException;
	
	/**
	 * [UC0725] Gerar Relatório de Imóveis por Situação da Ligação de Agua
	 * 
	 * Pesquisa o Total Registro
	 * 
	 * @author Rafael Pinto
	 * @date 04/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisSituacaoLigacaoAgua(
		FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper filtro) 
		throws ControladorException;
	
	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 * 
	 * @author Bruno Barros
	 * @date 06/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisFaturasAtrasoHelper
	 * 
	 * @return Collection<RelatorioImoveisFaturasAtrasoHelper>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisFaturasAtrasoHelper> pesquisarRelatorioImoveisFaturasAtrasoAgrupadasLocalizacao(
		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
		throws ControladorException;

	
	/**
	 *[UC0726] - Gerar Relatório de Imóveis com Faturas em Atraso
	 *
	 *@since 31/08/2009
	 *@author Marlon Patrick
	 */
	public Collection<RelatorioImoveisFaturasAtrasoHelper> pesquisarRelatorioImoveisFaturasAtrasoAgrupadasCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
			throws ControladorException;

	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 * 
	 * Pesquisa o Total Registro
	 * 
	 * @author Bruno Barros
	 * @date 06/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisFaturasAtrasoHelper
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoLocalizacao(
		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
		throws ControladorException;

	/**
	 *[UC0726] - Gerar Relatório de Imóveis com Faturas em Atraso
	 *
	 *@since 31/08/2009
	 *@author Marlon Patrick
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
			throws ControladorException;

	/**
	 * [UC0727] Gerar Relatório de Imóveis por Consumo Medio
	 * 
	 * @author Bruno Barros
	 * @date 17/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisConsumoMedioHelper
	 * 
	 * @return Collection<RelatorioImoveisConsumoMedioHelper>
	 * @throws ControladorException
	 */
	public Collection<RelatorioImoveisConsumoMedioHelper> pesquisarRelatorioImoveisConsumoMedio(
			FiltrarRelatorioImoveisConsumoMedioHelper filtro) throws ControladorException;
	
	/**
	 * Pesquisa a quantidade de imoveis para  o relatorio de imoveis por consumo medio
	 * 
	 * @author Bruno Barros
	 * @data 17/12/2007
	 * 
	 * @param filtro
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisConsumoMedio(
			FiltrarRelatorioImoveisConsumoMedioHelper filtro) throws ControladorException;
	
	/**
	 * [UC0731] Gerar Relatório de Imóveis com os Ultimos Consumos de Agua
	 * 
	 * @author Rafael Pinto
	 * @date 18/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisUltimosConsumosAguaHelper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisUltimosConsumosAguaHelper> pesquisarRelatorioImoveisUltimosConsumosAgua(
			FiltrarRelatorioImoveisUltimosConsumosAguaHelper filtro) 
		throws ControladorException;
	
	/**
	 * [UC0731] Gerar Relatório de Imóveis com os Ultimos Consumos de Agua
	 * 
	 * @author Rafael Pinto
	 * @date 19/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisUltimosConsumosAguaHelper
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisUltimosConsumosAgua(
			FiltrarRelatorioImoveisUltimosConsumosAguaHelper filtro) 
		throws ControladorException;
	
	
	/**
	 * [UC00728] Gerar Relatório de Imóveis Ativos e Não Medidos
	 * 
	 * @author Rafael Pinto
	 * @date 03/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisAtivosNaoMedidosHelper
	 * 
	 * @return Collection<RelatorioImoveisAtivosNaoMedidosHelper>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisAtivosNaoMedidosHelper> pesquisarRelatorioImoveisAtivosNaoMedidos(
			FiltrarRelatorioImoveisAtivosNaoMedidosHelper filtro) 
		throws ControladorException;
	
	/**
	 * [UC00728] Gerar Relatório de Imóveis Ativos e Não Medidos
	 * 
	 * @author Rafael Pinto
	 * @date 03/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisAtivosNaoMedidosHelper
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisAtivosNaoMedidos(
			FiltrarRelatorioImoveisAtivosNaoMedidosHelper filtro) 
		throws ControladorException;
	
	/**
	 * [UC00730] Gerar Relatório de Imóveis com Faturas Recentes em Dia e Faturas Antigas em Atraso
	 * 
	 * @author Rafael Pinto
	 * @date 08/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper
	 * 
	 * @return Collection<RelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper> pesquisarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(
			FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper filtro) 
		throws ControladorException;	
	
	/**
	 * [UC00730] Gerar Relatório de Imóveis com Faturas Recentes em Dia e Faturas Antigas em Atraso
	 * 
	 * @author Rafael Pinto
	 * @date 08/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(
			FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper filtro) 
		throws ControladorException;	
	
	/**
	 * [UC0729] Gerar Relatório de Imóveis por Tipo de Consumo
	 * 
	 * @author Bruno Barros
	 * @date 10/01/2008
	 * 
	 * @param RelatorioImoveisTipoConsumoHelper
	 * 
	 * @return Collection<RelatorioImoveisTipoConsumoHelper>
	 * @throws FachadaException
	 */
	public Collection<RelatorioImoveisTipoConsumoHelper> pesquisarRelatorioImoveisTipoConsumo(
		FiltrarRelatorioImoveisTipoConsumoHelper filtro)
		throws ControladorException;
	
	/**
	 * [UC0729] Gerar Relatório de Imóveis por Tipo de Consumo
	 * 
	 * @author Bruno Barros
	 * @date 10/01/2008
	 * 
	 * @param RelatorioImoveisTipoConsumoHelper
	 * 
	 * @return Collection<RelatorioImoveisTipoConsumoHelper>
	 * @throws FachadaException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisTipoConsumo(
			FiltrarRelatorioImoveisTipoConsumoHelper filtro) 
			throws ControladorException;	

	/**
	 * [UC0762] Gerar Arquivo Texto com Dados Cadastrais - CAERN 
	 * @author Tiago Moreno
	 * @date 08/04/2008
	 * 
	 * @param ArquivoTextoDadosCadastraisHelper
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public void gerarArquivoTextoDadosCadastrais(
			ArquivoTextoDadosCadastraisHelper arquivoTextoDadosCadastraisHelper) throws ControladorException;
	
	/**
	 * [UC0763] Gerar Arquivo Texto de Ligacoes com Hidrometro - CAERN 
	 * @author Tiago Moreno
	 * @date 10/04/2008
	 * 
	 * @param ArquivoTextoLigacoesHidrometroHelper
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection<HidrometroInstalacaoHistorico> recuperaImoveisArquivoTextoLigacoesHidrometro(
			ArquivoTextoLigacoesHidrometroHelper arquivoTextoLigacoesHidrometroHelper) throws ControladorException;
    
    /**
     * Pesquisar os todos os ids de Setor comercial 
     * 
     * @author Vivianne Sousa
     * @date 07/02/2007
     * 
     * @return Collection<Integer>
     * @throws ErroRepositorioException
     */
    public Collection<Integer> pesquisarIdsCodigosSetorComercial()
            throws ControladorException;
    
    /**
     * [UC0330] Inserir Mensagem da Conta
	 *
	 * [SB0001] Pesquisar Setor Comercial
     *
     * @author Raphael Rossiter
     * @date 25/06/2008
     *
     * @param tipoArgumento
     * @param indiceInicial
     * @param indiceFinal
     * @param anoMesReferencia
     * @return Collection
     * @throws ControladorException
     */
    public Collection pesquisarSetorComercialPorQualidadeAgua(int tipoArgumento, BigDecimal indiceInicial, 
		BigDecimal indiceFinal, Integer anoMesReferencia) throws ControladorException;
    
	
	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 * 
	 * @author Flávio Leonardo
	 * @date 10/09/2008
	 * 
	 * @param FiltrarRelatorioImoveisFaturasAtrasoHelper
	 * 
	 * @return Collection<RelatorioImoveisSituacaoLigacaoAguaHelper>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisFaturasAtrasoHelper> pesquisarRelatorioImoveisFaturasAtrasoDescritasLocalizacao(
		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
		throws ControladorException;

	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 *
	 *@since 02/09/2009
	 *@author Marlon Patrick
	 */
	public Collection<RelatorioImoveisFaturasAtrasoHelper> pesquisarRelatorioImoveisFaturasAtrasoDescritasCliente(
		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
		throws ControladorException;

     /**
     * [UC0xxx] Inserir Unidade de Negocio
     * 
     * 
     * @author Rômulo Aurélio
     * @date 29/09/2008
     * 
     * 
     * @return Integer
     * @throws ControladorException 
     * @throws ControladorException
     */
    
    
    public Integer inserirUnidadeNegocio(UnidadeNegocio unidadeNegocio, 
            Usuario usuarioLogado) throws ControladorException;
    
    /**
	 * [UC0???] Atualizar Unidade de Negocio
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 29/09/2008
	 * 
	 * 
	 * @throws ControladorException 
	 * @throws ControladorException
	 */

	public void atualizarUnidadeNegocio(UnidadeNegocio unidadeNegocio,
			Usuario usuarioLogado) throws ControladorException;
	
	
	/**
     * [UC0789] Inserir Empresa
     * 
     * 
     * @author Rômulo Aurélio
     * @date 29/09/2008
     * 
     * 
     * @return Integer
     * @throws ControladorException
     */
    
    public Integer inserirEmpresa(Empresa empresa, EmpresaContratoCobranca empresaCobranca,
            Usuario usuarioLogado, List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa) throws ControladorException;
    
    
    /**
	 * [UC0784] Manter Empresa
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 29/09/2008
	 * 
	 * 
	 * @throws ControladorException
	 */

    
    public void atualizarEmpresa(Empresa empresa,
			EmpresaContratoCobranca empresaCobranca, Usuario usuarioLogado,
			List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa,
			List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixaRemover)
			throws ControladorException ;
    
	
    /**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/08/2008
	 */

	public void obterImovelClienteProprietarioUsuario(Integer idSetor , Integer idFuncionalidadeIniciada,
			ImovelGeracaoTabelasTemporariasCadastroHelper helper) throws ControladorException;
	
	/**
	 * Gerar Arquivo Texto da Atualização Cadastral 
	 * para Dispositivo Móvel
	 * 
	 * @param helper
	 * 
	 * @author Ana Maria
     * @date 17/09/2008
	 * @exception ControladorException
	 */
    
	public void gerarArquivoTextoAtualizacaoCadastralDispositivoMovel(Integer idFuncionalidadeIniciada, GerarArquivoTextoAtualizacaoCadastralHelper helper)
		throws ControladorException;
    
    /**
     * 
     * [UC0535] Manter Feriado
     * 
     * @author bruno
     * @date 12/01/2009
     *
     * @param indicadorTipoFeriado
     * @param anoOrigemFeriado
     * @param anoDestinoFeriado
     */
    public void espelharFeriados( 
            String indicadorTipoFeriado, 
            String anoOrigemFeriado, 
            String anoDestinoFeriado )
        throws ControladorException;   
    
    /**
	 * [UC0880] - Gerar Movimento de Extensao de Contas em Cobranca por Empresa
	 * 
	 * @author Rômulo Aurélio
	 * @date 09/02/2009
	 * 
	 * @param idRota
	 * @param anoMesReferencia
	 * @return boolean
	 * @throws ControladorException
	 */
    public Collection pesquisarLocalidades() throws ControladorException ;
    

	/**
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral 
	 * 
	 * @author Ana Maria
	 * @date 04/03/2009
	 * 
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarArquivoTextoAtualizacaoCadastro(String idEmpresa, 
			String idLocalidade, String idAgenteComercial, String idSituacaoTransmissao)throws ControladorException;
	
	/**
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral 
	 * 
	 * @author Ana Maria
	 * @date 04/03/2009
	 * 
	 * @return Collection
	 * @throws ControladorException
	 */
	public ArquivoTextoAtualizacaoCadastral pesquisarArquivoTextoAtualizacaoCadastro(String idArquivoTxt)
		throws ControladorException;
	
	/**
	 * 
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral 
	 * 
	 * @author Ana Maria
	 * @date 05/03/2009
	 * 
	 * @return void
	 * @throws ControladorException
	 */
	public void atualizarArquivoTextoAtualizacaoCadstral(Integer idArquivoTxt)
		throws ControladorException;

	/** Método para verificar o Cliente é um funcionário
	 * 
	 * @author Vinicius Medeiros
	 * @date 08/04/2009
	 *
	 * @param idCliente
	 * @return
	 * @throws ControladorException
	 */
	
	 public Integer clienteSelecionadoFuncionario(Integer idCliente)
		throws ControladorException;
	 
	
	/**
	 * [UC0024] Inserir Quadra
	 *
	 * @author Raphael Rossiter
	 * @date 03/04/2009
	 *
	 * @param quadraFaceNova
	 * @param colecaoQuadraFace
	 * @throws ControladorException
	 */
	public void validarQuadraFace(QuadraFace quadraFaceNova, Collection colecaoQuadraFace, boolean verificarExistencia) 
		throws ControladorException;
        
	/**
	 * Pesquisa a Quadra Face atraves da quadra associada
	 * 
	 * Autor: Arthur Carvalho
	 * 
	 * Data: 28/04/2009
	 */
	public Collection<Object[]> pesquisarQuadraFaceAssociadaQuadra(Integer idQuadra)
			throws ControladorException ;
	
	/**
	 * Valida se o cliente é uma pessoa jurídica. Se não for, lança uma exceção.
	 * 
	 * [FS0002] do [UC0915] Inserir Entidade Beneficente
	 * 
	 * @author Samuel Valerio
	 * @date 11/06/2009
	 * @since 4.1.6.4
	 * 
	 * @param cliente Cliente a ser verificado
	 * @throws ControladorException 
	 */
	public void validarSeClienteEhPessoaJuridica(Cliente cliente) throws ControladorException;
	
	/**
	 * Valida se o tipo do débito não é gerado automaticamente. Se for, lança uma exceção.
	 * 
	 * [FS0004] do [UC0915] Inserir Entidade Beneficente
	 * 
	 * @author Samuel Valerio
	 * @date 12/06/2009
	 * @since 4.1.6.4
	 * 
	 * @param debitoTipo Tipo de débito a ser verificado
	 * @throws ControladorException
	 */
	public void validarSeDebitoTipoNaoEhGeradoAutomaticamente(DebitoTipo debitoTipo) throws ControladorException;
	
	/**
	 * Valida se já não existe uma entidade beneficente com aquele cliente. Se houver, lança uma exceção.
	 * 
	 * [FS0006] do [UC0915] Inserir Entidade Beneficente
	 * 
	 * @author Samuel Valerio
	 * @date 12/06/2009
	 * @since 4.1.6.4
	 * 
	 * @param entidadeBeneficente Entidade beneficente a ser verificada
	 * @throws ControladorException
	 */
	public void validarPreExistenciaEntidadeBeneficente(EntidadeBeneficente entidadeBeneficente) throws ControladorException;
	
	/**
	 * [UC0915] Inserir Entidade Beneficente
	 * 
	 * @author Samuel Valerio
	 * @date 12/06/2009
	 * @since 4.1.6.4
	 * 
	 * @param entidadeBeneficente Entidade beneficente a ser inserida
	 * @throws ControladorException
	 */
	public Integer inserirEntidadeBeneficente(EntidadeBeneficente entidadeBeneficente) throws ControladorException;
	
	/**
	 * [UC0830] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Ana Maria
	 * @date 22/06/2009
	 * 
	 * @return Collection<Integer>
	 * @throws ControladorException
	 */

	public Collection<Integer> pesquisarSetorComercialGeracaoTabelasTemporarias(ImovelGeracaoTabelasTemporariasCadastroHelper helper) 
			throws ControladorException;
	
	/**
	 * [UC0830] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Ana Maria
	 * @date 22/06/2009
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */

	public Collection<Integer> obterIdsImovelGeracaoTabelasTemporarias(ImovelGeracaoTabelasTemporariasCadastroHelper helper) 
			throws ControladorException;
	
	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 *
	 * @author Vivianne Sousa
	 * @date 25/06/2009
	 *
	 * @param idEmpresa
	 * @param data
	 * @throws ControladorException
	 */
	public Object[] gerarBoletimCustoAtualizacaoCadastral(
			Empresa empresa, Date dataAtualizacaoInicio, Date dataAtualizacaoFim)throws ControladorException;

	/**
	 * [UC0925] Emitir Boletos
	 *
	 * @author Vivianne Sousa
	 * @date 10/07/2009
	 */
	public void emitirBoletos(Integer idFuncionalidadeIniciada,Integer grupo)throws ControladorException;
	
	/**
	 * Obtém a quantidade de economias da categoria, levando em consideração o
	 * fator de economias
	 * 
	 * @author Rafael Corrêa
	 * @date 09/08/2009
	 * 
	 * @throws ControladorException
	 */
	public int obterQuantidadeEconomiasCategoria(Categoria categoria) throws ControladorException;
	
	/**
	 * Obtém a quantidade de economias da subcategoria, levando em consideração o
	 * fator de economias
	 * 
	 * @author Rafael Corrêa
	 * @date 09/08/2009
	 * 
	 * @throws ControladorException
	 */
	public int obterQuantidadeEconomiasSubcategoria(Subcategoria subcategoria) throws ControladorException;
	
	/**
	 * [UC0407]-Filtrar Imóveis para Inserir ou Manter Conta
	 * [FS0011]-Verificar a abrangência do código do usuário
	 *
	 * @author Vivianne Sousa
	 * @date 31/07/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public UnidadeNegocio pesquisarUnidadeNegocioUsuario(Integer idUsuario)throws ControladorException;

	/**
	 * [UC0928]-Manter Situação Especial de Faturamento
	 * [FS0003]-Verificar a existência do setor
	 *
	 * @author Marlon Patrick
	 * @date 11/08/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaSetorComercial(Integer idSetorComercial)throws ControladorException;
	
	/**
	 * [UCXXXX] Excluir Imoveis Da Tarifa Social 
	 * CRC - 2113
	 * 
	 * @author Genival Barbosa
	 * @date 15/09/2009
	 */	
	public void excluirImoveisDaTarifaSocial(Integer idSetor , Integer idFuncionalidadeIniciada, Integer anoMesFaturamento) 
			throws ControladorException;

	/**
	 * [UC0727] Gerar Relatório de Imóveis por Consumo Medio
	 * 
	 * @author Arthur Carvalho
	 * @date 02/10/2009
	 * 
	 * @param FiltrarRelatorioImoveisConsumoMedioHelper
	 * 
	 * @return quantidade de imoveis
	 * @throws ControladorException
	 */
	public Integer pesquisarRelatorioImoveisConsumoMedioCount(
			FiltrarRelatorioImoveisConsumoMedioHelper filtro) throws ControladorException;
	
	/**
	 * @author Arthur Carvalho
	 * @date 08/10/2009
	 * 
	 * @return quantidade de imoveis
	 * @throws ControladorException
	 */
	public Integer pesquisarImovelAtualizacaoCadastralComIndicadorExclusaoCount() throws ControladorException;

	

	/**
	 * [UC0969] Importar arquivo de atualização cadastral simplificado
	 * 
	 * @author Samuel Valerio
	 * @date 22/10/2009
	 * 
	 * @param arquivo Arquivo texto a ser importado
	 * @return Id do arquivo texto recém-inserido
	 * @throws ControladorException
	 */
	public Integer inserirArquivoTextoAtualizacaoCadastralSimplificado(AtualizacaoCadastralSimplificado arquivo, AtualizacaoCadastralSimplificadoBinario arquivoBinario,Collection<AtualizacaoCadastralSimplificadoLinha> linhas) throws ControladorException;

	/**
	 * Busca as críticas existentes para o arquivo passado como parâmetro.
	 * 
	 * [UC0969] Importar arquivo de atualização cadastral simplificado
	 * 
	 * @author Samuel Valerio
	 * @date 22/10/2009
	 * 
	 * @param idArquivo Id do arquivo texto importado
	 * @return Críticas existentes para o arquivo.
	 * @throws ControladorException
	 */
	public Collection<AtualizacaoCadastralSimplificadoCritica> pesquisarAtualizacaoCadastralSimplificadoCritica(int idArquivo) throws ControladorException;
	/**
	 * 
	 * [UC0973] Inserir Imóvel em Programa Especial
	 * [FS0004] Validar dados do imóvel no programa especial
	 * @author Hugo Amorim
	 * @since 17/12/2009
	 *
	 */
	public void validarDadosInserirImovelProgramaEspecial(ImovelProgramaEspecial imovelProgramaEspecial) throws ControladorException;
	
	/**
	 * 
	 * [UC0976] Suspender Imóvel em Programa Especial
	 * [FS0004] Validar dados da suspensão imóvel no programa especial
	 * @author Hugo Amorim
	 * @since 21/12/2009
	 *
	 */
	public void validarDadosSuspensaoImovelProgramaEspecial(
			ImovelProgramaEspecial imovelProgramaEspecial) throws ControladorException;
	
	/**
	 * 
	 * [UC0976] Suspender Imóvel em Programa Especial
	 *  	Suspende Imóvel em Programa Especial
	 * @author Hugo Amorim
	 * @since 13/01/2010
	 *
	 */
	public void suspenderImovelEmProgramaEspecialOnline(ImovelProgramaEspecial imovelProgramaEspecial,
			Usuario usuarioLogado,Short formaSuspensao) throws ControladorException;
	
	/**
	 * 
	 * [UC0973] Inserir Imóvel em Programa Especial
	 *  	Inseri Imóvel em Programa Especial
	 * @author Hugo Amorim
	 * @since 13/01/2010
	 *
	 */
	public Integer inserirImovelEmProgramaEspecial(ImovelProgramaEspecial imovelProgramaEspecial,
			Usuario usuarioLogado) throws ControladorException;
	
	/**
	 * 
	 * [UC0976] Suspender Imóvel em Programa Especial Batch
	 *  	Suspende Imóveis ativos no Programa Especial
	 * @author Hugo Amorim
	 * @since 13/01/2010
	 *
	 */
	public void suspenderImovelEmProgramaEspecialBatch(int idFuncionalidadeIniciada,
			Usuario usuarioLogado,Rota rota)
		throws ControladorException;
	
	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais
	 * 
	 * @author Hugo Leonardo
	 * @date 19/01/2010
	 * 
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 * 
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws ControladorException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisProgramaEspecial(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper filtro) 
			throws ControladorException;
	
	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais Analitico
	 * 
	 * @author Hugo Leonardo
	 * @date 18/01/2010
	 * 
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 * 
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws ControladorException
	 */
	public Collection<RelatorioImoveisProgramasEspeciaisHelper> pesquisarRelatorioImoveisProgramasEspeciaisAnalitico(
		FiltrarRelatorioImoveisProgramasEspeciaisHelper filtro)
		throws ControladorException;
	
	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais - Relatório Sintético
	 * 
	 * @author Hugo Leonardo
	 * @date 25/01/2010
	 * 
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 * 
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws ControladorException
	 */
	
	public Collection pesquisarRelatorioImoveisProgramasEspeciaisSintetico(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper helper)
			throws ControladorException; 
	
	/**
	 * 
	 * [UC0973] Inserir Imóvel em Programa Especial
	 * 
	 * Verificar se existe parcelamento para o Imovel em Programa Especial.
	 * 
	 * @author Hugo Leonardo
     * @throws ControladorException 
	 * @since 10/02/2010
	 *
	 */
    public Boolean verificarExistenciaParcelamentoImovel(Integer idImovel) throws ControladorException;
    
    /**
	 * [UC0999] Gerar Relatório de Coleta de Medidor de Energia.
	 * 
	 * @author Hugo Leonardo
	 * @date 09/03/2010
	 * 
	 * @param FiltrarRelatorioColetaMedidorEnergiaHelper
	 * 
	 * @return Collection<RelatorioColetaMedidorEnergiaHelper>
	 * @throws ControladorException
	 */
	public Collection<RelatorioColetaMedidorEnergiaHelper> pesquisarRelatorioColetaMedidorEnergia(
			FiltrarRelatorioColetaMedidorEnergiaHelper helper)
		throws ControladorException;
	
    /**
	 * [UC0999] Gerar Relatório de Coleta de Medidor de Energia.
	 * 
	 * Obtém a quantidade de imoveis de acordo com o filtro.
	 * 
	 * @author Hugo Leonardo
	 * @date 09/03/2010
	 * 
	 * @param FiltrarRelatorioColetaMedidorEnergiaHelper
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer countRelatorioColetaMedidorEnergia(
			FiltrarRelatorioColetaMedidorEnergiaHelper helper) 
			throws ControladorException;
	
    /**
	 * [UC1011] Emitir Boletim de Cadastro Individual.
	 * 
	 * Criar Dados para Relatório de Boletim de Cadastro Individual
	 * 
	 * @author Hugo Leonardo
	 * @date 24/03/2010
	 * 
	 * @param idImovel
	 * 
	 * @return RelatorioBoletimCadastroIndividualBean
	 * @throws ControladorException
	 */
	public RelatorioBoletimCadastroIndividualBean criarDadosRelatorioBoletimCadastroIndividual(
			Integer idImovel) throws ControladorException;
	
	/**
	 * 
	 * Batch criado para atualização da coluna codigo debito automatico do imovel.
	 * 
	 * @author Hugo Amorim
	 * @date 30/03/2010	
	 */
	public void atualizarCodigoDebitoAutomatico(Integer integer,
			SetorComercial setorComercial) throws ControladorException;
	
    /**
     * [UC0811] Processar Requisições do Dispositivo Móvel Impressao Simultanea.
     * 
     * Método que baixa a nova versão do JAD do mobile para o celular
     * 
     * @author Bruno Barros
     * @date 08/06/2010
     *  
     * @param 
     * @throws IOException
     */   
    public byte[] baixarNovaVersaoJad() throws ControladorException;
    
    /**
     * [UC0811] Processar Requisições do Dispositivo Móvel Impressao Simultanea.
     * 
     * Método que baixa a nova versão do JAR do mobile para o celular
     * 
     * @author Bruno Barros
     * @date 08/06/2010
     *  
     * @param 
     * @throws IOException
     */   
    public byte[] baixarNovaVersaoJar() throws ControladorException;
	
    /**
     * 
     * @author Fernando Fontelles
     * @date 07/07/2010
     * 
     * @param idImovel
     * @return
     * @throws ControladorException
     */
    public boolean verificarSituacaoImovelCobrancaJudicial(Integer idImovel) throws ControladorException;
    
    /**
     * 
     * @author Fernando Fontelles
     * @date 07/07/2010
     * 
     * @param idImovel
     * @return
     * @throws ControladorException
     */
    public boolean verificarSituacaoImovelNegativacao( Integer idImovel ) throws ControladorException;
    
    /**
     * 
     * [UC1036] - Inserir Cadastro de Email do Cliente
     * 
     * @author Fernando Fontelles
     * @date 09/07/2010
     * 
     * @param idCliente
     * @param nomeClienteAnterior
     * @param cpfAnterior
     * @param cnpjAnterior
     * @param emailAnterior
     * @param nomeSolicitante
     * @param cpfSolicitante
     * @param nomeClienteAtual
     * @param cpfClienteAtual
     * @param cnpjClienteAtual
     * @param emailAtual
     * @return
     */
    public Integer inserirCadastroEmailCliente( Integer idCliente, String nomeClienteAnterior, 
     		String cpfAnterior, String cnpjAnterior, String emailAnterior, String nomeSolicitante, 
     		String cpfSolicitante, String nomeClienteAtual, String cpfClienteAtual,
 			String cnpjClienteAtual, String emailAtual) throws ControladorException;
    
    /**
     * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
     * 
     * @author Hugo Amorim
     * @date 08/09/2010
     */
 	public Collection pesquisarDadosRelatorioAlteracoesSistemaColuna(GerarRelatorioAlteracoesSistemaColunaHelper helper)
 		throws ControladorException;
 	
 	/**
     * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
     * 
     * [FS0007] 
     * 
     * @author Hugo Amorim
     * @date 08/09/2010
     */
 	public boolean verificarRelacaoColuna(Integer idColuna) throws ControladorException;
 	
 	
	/**
     * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
     * 
     * @author Daniel Alves
     * @date 28/09/2010
     * Consulta do Relatório Analítico
     */
 	public Collection pesquisarDadosRelatorioAtualizacaoCadastralViaInternet(GerarRelatorioAtualizacaoCadastralViaInternetHelper filtro)
 		throws ControladorException;
    
    /**
	 * 
	 * [UC0113] - Faturar Grupo de Faturamento
	 * 
	 * @author Rômulo Aurélio
	 * @date 28/09/2010
	 * 
	 * @return
	 */
	public ClienteImovel pesquisarClienteResponsavelComEsferaPoderPublico(
			Integer idImovel) throws ControladorException ;
 	/**
     * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
     * 
     * @author Daniel Alves
     * @date 28/09/2010
     * Consulta do Relatório Resumo
     */
 	public Collection pesquisarDadosRelatorioResumoAtualizacaoCadastralViaInternet(GerarRelatorioAtualizacaoCadastralViaInternetHelper filtro)
 		throws ControladorException;
 	
 	/**
     * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
     * 
     * @author Hugo Amorim de Lyra
     * @date 06/10/2010
     */
 	public Integer countRelatorioAtualizacaoCadastralViaInternet(GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
 		throws ControladorException;
 	
 	/**TODO: COSANPA
	 * @author: Adriana Muniz
	 * @date: 23/11/2011
	 * 
	 * Pesquisa todas as esfera de poder ativas
	 * 
	 * Manter Contas de um Conjunto de Imóveis
	 * 
	 * @return Collection
	 * @throws ControladorException
	 *  
	 */
	public Collection<EsferaPoder> pesquisarEsferaPoder() throws ControladorException;
 	
 	
	/**
	 * [UC1121] Gerar Relatório de Imóveis com Alteração de Inscrição Via Batch
	 *
	 * @author Hugo Leonardo
	 * @date 19/01/2011
	 *
	 * @throws ControladorException
	 */
	public Collection<ImovelInscricaoAlterada> pesquisarRelatorioImoveisAlteracaoInscricaoViaBatch( 
			FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper) throws ControladorException;
	
	/**
	 * [UC1121] Gerar Relatório de Imóveis com Alteração de Inscrição Via Batch.
	 *
	 * @author Hugo Leonardo
	 * @date 19/01/2011
	 *
	 * @throws ControladorException
	 */
	public Integer countTotalRelatorioImoveisAlteracaoInscricaoViaBatch( 
			FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper) 
		throws ControladorException;
	  
	/**
     * [UC1124] Gerar Relatório de Alterações de CPF/CNPJ
     * 
     * @author Mariana Victor
     * @date 16/02/2011
     */
 	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpj(GerarRelatorioAlteracoesCpfCnpjHelper helper)
 		throws ControladorException;
 	
	/**
	 * Solicitar Conta em Braile.
	 * 
	 * [UC1128] Solicitar Conta Braile
	 * 
	 * @author Hugo Leonardo
	 * @date 04/03/2011
	 * 
	 */
    public Integer inserirSolicitacaoContaBraile(ContaBraileHelper contaBraileHelper) 
    	throws ControladorException;
    
    /**
	 * UC1162 – AUTORIZAR ALTERACAO INSCRICAO IMOVEL
	 * @author Rodrigo Cabral
	 * @date 05/06/2011
	 */
	public Collection pesquisaImovelInscricaoAlterada(ImovelInscricaoAlteradaHelper helper)
		throws ControladorException;
	
 	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorGerencia(Integer idGerenciaRegional)throws ControladorException;
	
		/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorUnidadeNegocio(Integer idUnidadeNegocio)throws ControladorException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidade()throws ControladorException;
	
	/**
     * [UC1160] Processar Comando Gerado Carta Tarifa Social  
     * 
     * @author: Vivianne Sousa
     * @date: 24/03/2011
     */
    public void processarComandoGerado(Integer idLocalidade , Integer idFuncionalidadeIniciada,
    		TarifaSocialComandoCarta tarifaSocialComandoCarta)throws ControladorException;
    
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * [SB0007]-Gera Arquivo TXT das Cartas
	 * 
	 * @author Vivianne Sousa
	 * @date 29/03/2011
	 * 
	 * @throws ControladorException
	 */
	public void gerarCartaTarifaSocial(TarifaSocialComandoCarta tscc,Integer idFuncionalidadeIniciada)throws ControladorException;
	
	/**
	 * [UC1161]Retirar Imóvel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 04/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void retirarImovelTarifaSocial(Integer idLocalidade,	int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * [UC1161]Retirar Imóvel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 01/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void retirarImovelTarifaSocial(TarifaSocialComandoCarta tscc,int idFuncionalidadeIniciada) throws ControladorException ;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 02/05/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorGerenciaEUnidade(Integer idGerenciaRegional
			,Integer idUnidadeNegocio)throws ControladorException;
	
	/**
	 * [UC1170] Gerar Relatório Acesso ao SPC
	 *  
	 * @author: Diogo Peixoto
	 * @date: 06/05/2011
	 * 
	 * @param FiltrarRelatorioAcessoSPCHelper
	 * @return Collection<RelatorioAcessoSPCBean>
	 * @throws ControladorException
	 */
	public Collection<RelatorioAcessoSPCBean> filtrarRelatorioAcessoSPC(FiltrarRelatorioAcessoSPCHelper filtrarRelatorioAcessoSPCHelper) throws ControladorException;
	
	/**
	 * [UC1170] Gerar Relatório Acesso ao SPC
	 *  
	 * @author: Diogo Peixoto
	 * @date: 06/05/2011
	 * 
	 * @param FiltrarRelatorioAcessoSPCHelper
	 * @return Collection<RelatorioAcessoSPCBean>
	 * @throws ControladorException
	 */
	public void atualizarGrauImportancia(Logradouro logradouro, Integer grauImportancia,Usuario usuario) throws ControladorException;

	
	
	
	 /**
     * Obtém a coleção de categorias.
     * 
     * @author Hugo Azevedo
     * @date 22/06/2011
     * 
     * @throws ControladorException
     */

	public Collection obterCategorias() throws ControladorException;
	
	
	/**
     * Obtém a coleção de perfis de imóveis.
     * 
     * @author Hugo Azevedo
     * @date 22/06/2011
     * 
     * @throws ControladorException
     */

	public Collection obterPerfisImoveis() throws ControladorException;
	
	/**
	 * [UC0060] Inserir Parametros do Sistema
	 * Validar documentos da loja virtual
	 * 
	 * @author Erivan Sousa
	 * @date 15/07/2011
	 * 
	 * @param byte[], String
	 * @throws ControladorException
	 */
	public void validarSistemaParametroLojaVirtual(byte[] fileData, String extensao) throws ControladorException;
	

	/**
	 * [MA2011061013]
	 * 
	 * @author Paulo Diniz
	 * @date 02/07/2011
	 * 
	 * @param idImovel
	 * 
	 * @return HidrometroMovimentado
	 * @throws ControladorException
	 */
	public  List<HidrometroInstalacaoHistorico> pesquisarHidrometroPeloIdImovel(Integer idImovel) throws ControladorException;
	
	public void carregarImovelAtualizacaoCadastral(BufferedReader buffer, ArrayList<String> nomesImagens) throws ControladorException;

}
