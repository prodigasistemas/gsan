package gcom.cadastro;

import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoCritica;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.geografico.MunicipioFeriado;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ImovelGeracaoTabelasTemporariasCadastroHelper;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.bean.DadosEnvioEmailHelper;
import gcom.cadastro.tarifasocial.TarifaSocialMotivoCarta;
import gcom.gui.relatorio.cadastro.FiltrarRelatorioAcessoSPCHelper;
import gcom.gui.relatorio.cadastro.GerarRelatorioAlteracoesCpfCnpjHelper;
import gcom.gui.relatorio.seguranca.GerarRelatorioAlteracoesSistemaColunaHelper;
import gcom.micromedicao.ArquivoTextoLigacoesHidrometroHelper;
import gcom.micromedicao.Rota;
import gcom.micromedicao.RotaAtualizacaoSeq;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.relatorio.cadastro.GerarRelatorioAtualizacaoCadastralViaInternetHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisAtivosNaoMedidosHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisConsumoMedioHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisFaturasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisProgramasEspeciaisHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisTipoConsumoHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisUltimosConsumosAguaHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisConsumoMedioHelper;
import gcom.relatorio.cadastro.micromedicao.RelatorioColetaMedidorEnergiaHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.FachadaException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Administrador 
 */
public interface IRepositorioCadastro {
	
	/**
	 * Pesquisa os feriados(nacionais e municipais)
	 * 
	 * @author Kássia Albuquerque
	 * @date 22/01/2007
	 * 
	 */
	public Collection pesquisarFeriado(Short tipoFeriado, String descricao, 
						Date dataFeriadoInicio, Date dataFeriadoFim, Integer idMunicipio, Integer numeroPagina)throws ErroRepositorioException;	

	/**
	 * Pesquisar quantidade de registro dos feriados(nacionais e municipais)
	 * 
	 * @author Kássia Albuquerque
	 * @date 22/01/2007
	 * 
	 */
	public Integer pesquisarFeriadoCount(Short tipoFeriado, String descricao, 
						Date dataFeriadoInicio, Date dataFeriadoFim, Integer idMunicipio)throws ErroRepositorioException;
	
	/**
	 * 
	 * Faz um Update na base
	 * 
	 * @author Kassia Albuquerque e Ana Maria
	 * @date 06/03/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarMensagemSistema(String mensagemSistema)throws ErroRepositorioException ;
	
	/**
	 * Pesquisa os dados do email do batch para ser enviado
	 * 
	 * @author Sávio Luiz
	 * @date 13/03/2007
	 * 
	 */
	public EnvioEmail pesquisarEnvioEmail(Integer idEnvioEmail)
			throws ErroRepositorioException;
	
	public DadosEnvioEmailHelper pesquisarDadosEmailSistemaParametros()
	throws ErroRepositorioException;
	
	/**
	 * Pesquisar todos ids dos setores comerciais.
	 *
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 *
	 * @author Pedro Alexandre
	 * @date 25/04/2007
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarTodosIdsSetorComercial() throws ErroRepositorioException ;
	
	/**
	 * Migração dos dados do município de Ribeirão - O sistema
	 * gerar as tabelas cliente, cliente_endereço, imovel, cliente_imovel,
	 * imovel_subcategoria, ligacao_agua a parti da tabela Cadastro_ribeirao;
	 * 
	 * @author Ana Maria
	 * 
	 * @throws ControladorException
	 */	
	public Object[] pesquisarSetorQuadra(Integer idLocalidade)throws ErroRepositorioException;
	
	public Integer pesquisarLogradouroBairro(Integer codigoLogradouro) throws ErroRepositorioException;
	
	public Integer pesquisarLogradouroCep(Integer codigoLogradouro) throws ErroRepositorioException;
	
	public void inserirClienteEndereco(Integer idCliente, String numeroImovelMenor, String numeroImovelMaior,
			Integer idCep, Integer idBairro, Integer idLograd, Integer idLogradBairro, Integer idLogradCep) throws ErroRepositorioException;

	public void inserirClienteImovel(Integer idCliente, Integer idImovel, String data)throws ErroRepositorioException;	
	
	public void inserirImovelSubcategoria(Integer idImovel, Integer idSubcategoria)throws ErroRepositorioException;
	
	public void inserirLigacaoAgua(Integer idImovel, String dataBD)throws ErroRepositorioException;	
	
	public Collection pesquisarCadastroRibeiraop() throws ErroRepositorioException;
	
	public void atualizarImovelRibeirao(Integer idImovel, Integer codigo)throws ErroRepositorioException;

	/**
	 * Fim - Migração dos dados do município de Ribeirão
	 */
	
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

	throws ErroRepositorioException;



	
	/**
	 * [UC0624] Gerar Relatório para Atualização Cadastral
	 */
	
	public Collection pesquisarRelatorioAtualizacaoCadastral(Collection idLocalidades,
			Collection idSetores, Collection idQuadras, String rotaInicial,
			String rotaFinal, String sequencialRotaInicial, String sequencialRotaFinal) 
		throws ErroRepositorioException;
	
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
	public Collection<Object[]> pesquisarRelatorioImoveisSituacaoLigacaoAgua(
		FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper filtro) 
		throws ErroRepositorioException;
	
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
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisSituacaoLigacaoAgua(
		FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper filtro) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 * 
	 * @author Bruno Barros
	 * @date 06/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisFaturasAtraso
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoAgrupadasLocalizacao(
		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
		throws ErroRepositorioException;

	
	/**
	 *[UC0726] - Gerar Relatório de Imóveis com Faturas em Atraso
	 *
	 *@since 31/08/2009
	 *@author Marlon Patrick
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoAgrupadasCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
			throws ErroRepositorioException;

	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 * 
	 * Pesquisa o Total Registro
	 * 
	 * @author Bruno Barros
	 * @date 04/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisFaturasAtrasoHelper
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoLocalizacao(
		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
		throws ErroRepositorioException;	

	
	/**
	 *[UC0726] - Gerar Relatório de Imóveis com Faturas em Atraso
	 *
	 *@since 31/08/2009
	 *@author Marlon Patrick
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
			throws ErroRepositorioException;	

	
	/**
	 * [UC0727] Gerar Relatório de Imóveis por Consumo Medio
	 * 
	 * @author Bruno Barros
	 * @date 17/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisConsumoMedio
	 * 
	 * @return Collection<FiltrarRelatorioImoveisConsumoMedio[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisConsumoMedioHelper> pesquisarRelatorioImoveisConsumoMedio(
		FiltrarRelatorioImoveisConsumoMedioHelper filtro, Integer anoMesFaturamento) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0727] Gerar Relatório de Imóveis por Consumo Medio
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
			FiltrarRelatorioImoveisConsumoMedioHelper filtro, Integer anoMesFaturamento) 
		throws ErroRepositorioException;
	
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
	public Collection<Object[]> pesquisarRelatorioImoveisUltimosConsumosAgua(
		FiltrarRelatorioImoveisUltimosConsumosAguaHelper filtro) 
		throws ErroRepositorioException;
	
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
	public Integer pesquisarTotalRegistroRelatorioImoveisUltimosConsumosAgua(
		FiltrarRelatorioImoveisUltimosConsumosAguaHelper filtro) 
		throws ErroRepositorioException;
	
	/**
	 * [UC00728] Gerar Relatório de Imóveis Ativos e Não Medidos
	 * 
	 * @author Rafael Pinto
	 * @date 03/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisAtivosNaoMedidosHelper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisAtivosNaoMedidos(
			FiltrarRelatorioImoveisAtivosNaoMedidosHelper filtro) 
		throws ErroRepositorioException;
	
	/**
	 * [UC00728] Gerar Relatório de Imóveis Ativos e Não Medidos
	 * 
	 * @author Rafael Pinto
	 * @date 03/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisAtivosNaoMedidosHelper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisAtivosNaoMedidos(
			FiltrarRelatorioImoveisAtivosNaoMedidosHelper filtro) 
		throws ErroRepositorioException;	
	
	/**
	 * [UC00730] Gerar Relatório de Imóveis com Faturas Recentes em Dia e Faturas Antigas em Atraso
	 * 
	 * @author Rafael Pinto
	 * @date 08/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(
			FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper filtro) 
		throws ErroRepositorioException;
	
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
	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(
			FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper filtro) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0729] Gerar Relatório de Imóveis por Tipo Consumo
	 * 
	 * @author Bruno Barros
	 * @date 14/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisTipoConsumo
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisTipoConsumo(
		FiltrarRelatorioImoveisTipoConsumoHelper filtro) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0729] Gerar Relatório de Imóveis por Tipo Consumo
	 * 
	 * @author Bruno Barros
	 * @date 14/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisTipoConsumo
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisTipoConsumo(
			FiltrarRelatorioImoveisTipoConsumoHelper filtro) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0762] Gerar Arquivo Texto com Dados Cadastrais - CAERN
	 * 
	 *  A pesquisa retorna uma colecao de Imoveis para que a partir
	 *  daí comece a geracao das linhas TXTs.
	 *  
	 * @author Tiago Moreno
	 * @date 08/04/2008
	 * 
	 * @param ArquivoTextoDadosCadastraisHelper
	 * 
	 * @return Collection<Imovel>
	 * @throws ControladorException
	 */
	public Collection<Imovel> pesquisarImovelArquivoTextoDadosCadastrais(
			ArquivoTextoDadosCadastraisHelper objeto)
				throws ErroRepositorioException;
	
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
	
	public Collection<HidrometroInstalacaoHistorico> pesquisarImovelArquivoTextoLigacoesHidrometro(
			ArquivoTextoLigacoesHidrometroHelper objeto) 
				throws ErroRepositorioException;
	
	/**
	 * Pesquisa o id localidade,codigo setor e codigo da rota 
	 * apartir do id da rota
	 * 
	 * @author Rafael Pinto

	 * @date 02/06/2008
	 * 
	 * @throws ErroRepositorioException
	 * @return Object[3] onde :
	 * 
	 * Object[0]=id localidade
	 * Object[1]=codigo do setor
	 * Object[2]=codigo da rota
	 */
	public Object[] pesquisarDadosRotaEntregaContaPorRota(Integer idRota)
			throws ErroRepositorioException ;
	
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
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarSetorComercialPorQualidadeAgua(int tipoArgumento, BigDecimal indiceInicial, 
			BigDecimal indiceFinal, Integer anoMesReferencia) throws ErroRepositorioException ;
	
	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/08/2008
	 * 
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */

	public Object[] obterImovelGeracaoTabelasTemporarias(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 20/09/2008
	 * 
	 * @return ImovelSubcategoria
	 * @throws ErroRepositorioException
	 */

	public Collection obterImovelSubcategoriaAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException;
		

	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso Contas Descritas
	 * 
	 * @author Flávio Leonardo
	 * @date 08/09/2008
	 * 
	 * @param FiltrarRelatorioImoveisFaturasAtraso
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoDescritasLocalizacao(
		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
		throws ErroRepositorioException;

	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso Contas Descritas
	 *
	 *@since 02/09/2009
	 *@author Marlon Patrick
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoDescritasCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
			throws ErroRepositorioException;

    /**
     * 
     * [UC0535] Manter Feriado
     * 
     * @author bruno
     * @date 12/01/2009
     *
     * @param anoOrigemFeriado
     */
    public Collection<NacionalFeriado> pesquisarFeriadosNacionais( 
            String anoOrigemFeriado  )
        throws ErroRepositorioException;
    
    /**
     * 
     * [UC0535] Manter Feriado
     * 
     * @author bruno
     * @date 12/01/2009
     *
     * @param anoOrigemFeriado
     */
    public Collection<MunicipioFeriado> pesquisarFeriadosMunicipais( 
            String anoOrigemFeriado  )
        throws ErroRepositorioException; 
    
    /**
     * 
     * [UC0535] Manter Feriados
     *
     * @author bruno
     * @date 13/01/2009
     *
     * @param anoDestino
     * @throws ErroRepositorioException
     */
    public void excluirFeriadosNacionais( String anoDestino ) 
        throws ErroRepositorioException;
        
    /**
     * 
     * [UC0535] Manter Feriados
     *
     * @author bruno
     * @date 13/01/2009
     *
     * @param anoDestino
     * @throws ErroRepositorioException
     */
    public void excluirFeriadosMunicipais( String anoDestino ) 
        throws ErroRepositorioException;
    
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
    public Collection pesquisarLocalidades() throws ErroRepositorioException ;
    
	/**
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral 
	 * 
	 * @author Ana Maria
	 * @date 04/03/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarArquivoTextoAtualizacaoCadastro(String idEmpresa, 
			String idLocalidade, String idAgenteComercial, String idSituacaoTransmissao)throws ErroRepositorioException;
	/**
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral 
	 * 
	 * @author Ana Maria
	 * @date 04/03/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public ArquivoTextoAtualizacaoCadastral pesquisarArquivoTextoAtualizacaoCadastro(Integer idArquivoTxt)
		throws ErroRepositorioException;
	
	/**
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral 
	 * 
	 * @author COSANPA - Felipe Santos
	 * @date 04/12/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection<ArquivoTextoAtualizacaoCadastral> pesquisarArquivoTextoAtualizacaoCadastro(String[] idsArquivoTxt)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral 
	 * 
	 * @author Ana Maria
	 * @date 05/03/2009
	 * 
	 * @return void
	 * @throws ErroRepositorioException
	 */
	public void atualizarArquivoTextoAtualizacaoCadstral(Integer idArquivoTxt, Integer idSituacaoTransmissao)
			throws ErroRepositorioException;
	
	/**
	 * [UC0830] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/08/2008
	 * 
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */

	public ArquivoTextoAtualizacaoCadastral pesquisarArquivoTextoAtualizacaoCadastro(String descricao)
	throws ErroRepositorioException;
	
	public Collection<Integer>  obterIdsImovelGeracaoTabelasTemporarias(Integer idSetor, ImovelGeracaoTabelasTemporariasCadastroHelper helper) 
	throws ErroRepositorioException;
	
	/**
	 * [UC0830] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Ana Maria
	 * @date 26/03/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImovelDebitoAtualizacaoCadastral(Collection colecaoIdsImovel)
	throws ErroRepositorioException;
	
    /**
	 * [UC0893] - Inserir Unidade de Negocio
	 * 
	 * Verificar se o Cliente Selecionado existe na tabela Funcionario
	 * 
	 * @author Vinicius Medeiros
	 * @date 08/04/2009
	 * 
	 * @param idCliente
	 * @throws ControladorException
	 */
    
    public Integer verificarClienteSelecionadoFuncionario(Integer idCliente) 
    	throws ErroRepositorioException ;

    /**
	 * Pesquisa a(s) quadra face associada a quadra 
	 * 
	 * Autor: Arthur Carvalho
	 * 
	 * Data: 28/04/2009
	 */
	public Collection<Object[]> pesquisarQuadraFaceAssociadaQuadra(Integer idQuadra) 
			throws ErroRepositorioException ;
	
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
	throws ErroRepositorioException;

	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 *
	 * O sistema seleciona as operações efetuadas pela empresa no período informado e com imóvel associado
	 * [SB0001 - Selecionar Operações Efetuadas com Imóvel Associado].
	 * 
	 * @author Vivianne Sousa
	 * @date 25/06/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOperacoesEfetuadasComImovelAssociado(Date dataInicio, Date dataFim,Integer idEmpresa)
		throws ErroRepositorioException;
	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 *
	 * O sistema seleciona as operações efetuadas pela empresa no período informado e sem imóvel associado
	 * [SB0002] - Selecionar Operações Efetuadas sem Imóvel Associado
	 * 
	 * @author Vivianne Sousa
	 * @date 25/06/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOperacoesEfetuadasSemImovelAssociado(Date dataInicio, Date dataFim,Integer idEmpresa)
		throws ErroRepositorioException;
	
	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 *
	 * O sistema obtém os dados do contrato com a empresa 
	 * (a partir da tabela EMPRESA_CONTRATO_CADASTRO com EMPR_ID=Id da empresa retornado 
	 * e ECCD_DTFINALCONTRATO maior ou igual à data corrente e ECCD_DTCANCELCONTRATO com o valor nulo)
	 * 
	 * @author Vivianne Sousa
	 * @date 25/06/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public EmpresaContratoCadastro pesquisarEmpresaContratoCadastro(Integer idEmpresa)
		throws ErroRepositorioException;
	
	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 *
	 * O sistema seleciona os atributos que compõem o boletim 
	 * (a partir da tabela ATRIBUTO ordenando pelo grupo do atributo (ATGR_ID) 
	 * e pela ordem de emissão (ATRB_NNORDEMEMISSAO)).
	 * 
	 * @author Vivianne Sousa
	 * @date 26/06/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarAtributosBoletim()
		throws ErroRepositorioException;
	
	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 *
	 * Valor de Atualização do Atributo 
	 * (ECCA_VLATUALIZACAO da tabela EMPRESA_CONTRATO_CADASTRO_ATRIBUTO 
	 * com ATRB_ID=ATRB_ID da tabela ATRIBUTO e 
	 * ECCD_ID=ECCD_ID da tabela EMPRESA_CONTRATO_CADASTRO);
	 * 
	 * @author Vivianne Sousa
	 * @date 26/06/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorAtualizacaoAtributo(
			Integer idAtributo,Integer idEmpresaContratoCadastro)throws ErroRepositorioException;
	
	/**
	 * [UC0925] Emitir Boletos
	 *
	 * @author Vivianne Sousa
	 * @date 09/07/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosBoleto(int quantidadeInicio, Integer grupo, String nomeEmpresa)throws ErroRepositorioException;
	
	/**
	 * [UC0925] Emitir Boletos
	 *
	 * retrona DBTP_VLLIMITE para DBTP_ID = idDebitoTipo
	 *
	 * @author Vivianne Sousa
	 * @date 09/07/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorLimiteDebitoTipo(Integer idDebitoTipo)throws ErroRepositorioException;
	
	/**
	 * [UC0407]-Filtrar Imóveis para Inserir ou Manter Conta
	 * [FS0011]-Verificar a abrangência do código do usuário
	 *
	 * @author Vivianne Sousa
	 * @date 31/07/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public UnidadeNegocio pesquisarUnidadeNegocioUsuario(Integer idUsuario)throws ErroRepositorioException;
	
	/**
	 * [UCXXXX]- Excluir Imoveis Da Tarifa Social
	 * CRC - 2113
	 * 
	 * @author Genival Barbosa
	 * @date 15/09/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public List pesquisarImoveisExcluirDaTarifaSocial(Integer idSetor, Integer anoMesFaturamento)throws ErroRepositorioException;

	/**
	 * [UCXXXX]- Excluir Imoveis Da Tarifa Social
	 * CRC - 2113
	 * 
	 * @author Genival Barbora
	 * @date 15/09/2009
	 * 
	 * @return void
	 * @throws ErroRepositorioException
	 */
	public void atualizarExcluirDaTarifaSocialTabelaDadoEconomia(String idImovel)throws ErroRepositorioException;
	
	/**
	 * [UCXXXX]- Excluir Imoveis Da Tarifa Social
	 * CRC - 2113
	 * 
	 * @author Genival Barbora
	 * @date 15/09/2009
	 * 
	 * @return void
	 * @throws ErroRepositorioException
	 */
	public void atualizarExcluirDaTarifaSocialTabelaImovel(String idImovel)throws ErroRepositorioException;
	
	/**
	 * [UC0727] Gerar Relatório de Imóveis por Consumo Medio
	 * 
	 * @author Arthur Carvalho
	 * @date 02/10/2009
	 * 
	 * @param FiltrarRelatorioImoveisConsumoMedio
	 * 
	 * @return quantidade de imoveis
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarRelatorioImoveisConsumoMedioCount(
		FiltrarRelatorioImoveisConsumoMedioHelper filtro, Integer anoMesFaturamento) 
		throws ErroRepositorioException;
	
	/**
	 *  Gerar Relatório de Imóveis 
	 * 
	 * @author Arthur Carvalho
	 * @date 08/10/2009
	 * 
	 * @return quantidade de imoveis
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarImovelAtualizacaoCadastralComIndicadorExclusaoCount() throws ErroRepositorioException;
	
	public Integer pesquisarQuantidadeImoveisPorSituacaoAtualizacaoCadastral(
			Integer situacao, Integer idArquivoTexto) throws ErroRepositorioException;
	
	/**
	 *  Pesquisar Ids dos Imoveis com siac_id = 0 and empresa = a empresa do leiturista 
	 * 
	 * @author Arthur Carvalho
	 * @date 27/10/2009
	 * 
	 * @return quantidade de imoveis
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsImoveisAtualizacaoCadastral(Integer idEmpresaLeiturista, 
			Integer idRota) throws ErroRepositorioException;
	
	public Collection<Integer> pesquisarRotasAtualizacaoCadastral(
			Collection idsImoveis) throws ErroRepositorioException;
	
	/**
	 * Pesquisa as críticas existentes para um determinado arquivo importado da atualizacao cadastral simplificado.
	 * 
	 * [UC0969] Importar arquivo de atualização cadastral simplificado
	 * 
	 * @author Samuel Valerio
	 * @date 22/10/2009
	 * 
	 * @param idArquivo Id do arquivo
	 * @return Coleção de críticas do arquivo
	 */
	public Collection<AtualizacaoCadastralSimplificadoCritica> pesquisarAtualizacaoCadastralSimplificadoCritica(int idArquivo) throws ErroRepositorioException;
	
	/**
	 * [UC0925] Emitir Boletos
	 *
	 * retrona DBTP_VLSUGERIDO para DBTP_ID = idDebitoTipo
	 *
	 * @author Rômulo Aurélio
	 * @date 22/12/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorSugeridoDebitoTipo(
			Integer idDebitoTipo)throws ErroRepositorioException;
	/**
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral 
	 * 
	 * @author Ana Maria
	 * @date 04/03/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public ArquivoTextoAtualizacaoCadastral pesquisarArquivoTextoAtualizacaoCadastro(String idArquivoTxt, Integer idSituacaoTransmissao)
		throws ErroRepositorioException;

	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais Analitico
	 * 
	 * @author Hugo Leonardo
	 * @date 18/01/2010
	 * 
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 * 
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws FachadaException
	 */
	public Collection pesquisarRelatorioImoveisProgramasEspeciaisAnalitico(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper helper) throws ErroRepositorioException;
	
	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais Sem Hidrometro
	 * 
	 * @author Hugo Leonardo
	 * @date 25/01/2010
	 * 
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 * 
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws FachadaException
	 */
	public Collection pesquisarRelatorioImoveisProgramasEspeciaisSintetico(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper helper) throws ErroRepositorioException;
	
	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais
	 * 
	 * @author Hugo Leonardo
	 * @date 18/01/2010
	 * 
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 * 
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws FachadaException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisProgramaEspecial(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper helper) throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0976] Suspender Imóvel em Programa Especial Batch
	 *  	Pesquisa imoveis para execução do batch
	 * @author Hugo Amorim
	 * @since 13/01/2010
	 *
	 */
	public Collection pesquisarImovelEmProgramaEspecial(
			Integer idPerfilProgramaEspecial,
			Rota rota,
			int numeroIndice, 
			int quantidadeRegistros)
		throws ErroRepositorioException;
	
	/**
	 * [UC0973] Inserir Imóvel em Programa Especial
	 * 
	 * @author Hugo Leonardo
	 * @date 10/02/2010
	 * 
	 * @param idImovel
	 * 
	 * @return Quantidade de Parcelamentos do Imóvel
	 * @throws FachadaException
	 */
	public Integer verificarExistenciaParcelamentoImovel(Integer idImovel)
			throws ErroRepositorioException;
	
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
			String faturamentoGrupo, String idLocalidadeInicial, String idLocalidadeFinal, 
			String idSetorComercialInicial, String idSetorComercialFinal,
			String rotaInicial, String rotaFinal, 
			String sequencialRotaInicial, String sequencialRotaFinal) throws ErroRepositorioException;
	
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
	public Integer pesquisarTotalRegistroRelatorioColetaMedidorEnergia(
			String faturamentoGrupo, String idLocalidadeInicial, String idLocalidadeFinal,
			String idSetorComercialInicial, String idSetorComercialFinal,
			String rotaInicial, String rotaFinal,
			String sequencialRotaInicial, String sequencialRotaFinal) throws ErroRepositorioException;
	/**
	 * 
	 * Batch criado para atualização da coluna codigo debito automatico do imovel.
	 * 
	 * @author Hugo Amorim
	 * @date 30/03/2010	
	 */
	public Collection<Integer> pesquisarIdsImoveisDoSetorComercial(Integer idSetor,
			int quantidadeInicio, int quantidadeMaxima)throws ErroRepositorioException;
	
	/**
	 * 
	 * Batch criado para atualização da coluna codigo debito automatico do imovel.
	 * 
	 * @author Hugo Amorim
	 * @date 30/03/2010	
	 */
	public void atualizarCodigoDebitoAutomatico(Integer idImovel,
			Integer codigoDebitoAutomatico)throws ErroRepositorioException;
	
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
    public byte[] baixarNovaVersaoJad() throws ErroRepositorioException;
    
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
    public byte[] baixarNovaVersaoJar() throws ErroRepositorioException;  
    
    /**
     * 
     * @author Fernando Fontelles
     * @date 07/07/2010
     * 
     * @param idImovel
     * @return
     * @throws ErroRepositorioException
     */
    public boolean verificarSituacaoImovelCobrancaJudicial(Integer idImovel) throws ErroRepositorioException; 
    
    /**
     * 
     * @author Fernando Fontelles
     * @date 07/07/2010
     * 
     * @param idImovel
     * @return
     * @throws ControladorException
     */
    public boolean verificarSituacaoImovelNegativacao( Integer idImovel ) throws ErroRepositorioException;
    
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
 			String cnpjClienteAtual, String emailAtual) throws ErroRepositorioException;
    
    /**
     * Atualiza o sequencial de rota do imóvel correspondente ao
     * RotaAtualizacaoSeq recebido 
     *  
     * @author bruno
     * @date 11/08/2010
     * 
     * @param rotaAtualizacaoSeq - Registro da tabela micromedicao.rota_atualizacao_sequencial
     * 
     * @throws ErroRepositorioException
     */
    public void atualizarSequenciaRotaImovel( 
            RotaAtualizacaoSeq seq )
            throws ErroRepositorioException;
    /**
     * 
     * @author Rômulo Aurélio
     * @date 28/09/2010
     * 
     * @param idImovel
     * @return
     * @throws ControladorException
     */
    public ClienteImovel pesquisarClienteResponsavelComEsferaPoderPublico( Integer idImovel ) 
    		throws ErroRepositorioException;
    
    /**
     * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
     *  	-TIPO USUARIO
     * @author Hugo Amorim
     * @date 08/09/2010
     */
 	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesSistemaColunaPorUsuario(GerarRelatorioAlteracoesSistemaColunaHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
     * 		-TIPO LOCALIDADE
     * @author Hugo Amorim
     * @date 08/09/2010
     */
 	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesSistemaColunaPorLocalidade(GerarRelatorioAlteracoesSistemaColunaHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
     * 
     * [FS0007] 
     * 
     * @author Hugo Amorim
     * @date 08/09/2010
     */
    public boolean verificarRelacaoColuna(Integer idColuna) throws ErroRepositorioException;
    
    
    
    /**
     * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
     * 
     * @author Daniel Alves
     * @date 28/09/2010
     */
 	public Collection<Object[]> pesquisarDadosRelatorioAtualizacaoCadastralViaInternet(GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
 		throws ErroRepositorioException;
 	
 	
 	/**
     * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
     * 
     * @author Daniel Alves
     * @date 28/09/2010
     */
 	public Collection<Object[]> pesquisarDadosRelatorioResumoAtualizacaoCadastralViaInternet(GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
     * 
     * @author Hugo Amorim de Lyra
     * @date 06/10/2010
     */
 	public Integer countRelatorioAtualizacaoCadastralViaInternet(GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
 		throws ErroRepositorioException;
 	
 	/**TODO: COSANPA
	 * @autor: Adriana Muniz
	 * @date: 12/05/2011
	 * Pesquisa o id da rota pelo id da quadra - Gerar
	 * Arquivo da declaração de quitação anual de débito
	 */
 	public Integer pesquisarIdRotaQuadra(Integer idQuadra) throws ErroRepositorioException;
 	
 	/**TODO: COSANPA
	 * @autor: Adriana Muniz
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
 	public Collection pesquisarEsferaPoder() throws ErroRepositorioException;
 	
 	/**
	 * [UC1121] Gerar Relatório de Imóveis com Alteração de Inscrição Via Batch.
	 *
	 * @author Hugo Leonardo
	 * @date 19/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioImoveisAlteracaoInscricaoViaBatch( 
			FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper) 
		throws ErroRepositorioException;
 	
	/**
	 * [UC1121] Gerar Relatório de Imóveis com Alteração de Inscrição Via Batch.
	 *
	 * @author Hugo Leonardo
	 * @date 19/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Integer countTotalRelatorioImoveisAlteracaoInscricaoViaBatch( 
			FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper) 
		throws ErroRepositorioException;

    /**
     * [UC1124] Gerar Relatório de Alterações de CPF/CNPJ
     * 
     * @author Mariana Victor
     * @date 16/02/2011
     */
 	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpjPorUsuario(GerarRelatorioAlteracoesCpfCnpjHelper helper)
 		throws ErroRepositorioException;

    /**
     * [UC1124] Gerar Relatório de Alterações de CPF/CNPJ
     * 
     * @author Mariana Victor
     * @date 17/02/2011
     */
 	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpjPorLocalidade(GerarRelatorioAlteracoesCpfCnpjHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1124] Gerar Relatório de Alterações de CPF/CNPJ por Meio de Solicitação
     * 
     * @author Mariana Victor
     * @date 16/02/2011
     */
 	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpjPorMeio(GerarRelatorioAlteracoesCpfCnpjHelper helper)
 		throws ErroRepositorioException;

 	/**
	 * UC1162 – AUTORIZAR ALTERACAO INSCRICAO IMOVEL
	 * @author Rodrigo Cabral
	 * @date 05/06/2011
	 */
	public Collection pesquisaImovelInscricaoAlterada(ImovelInscricaoAlteradaHelper helper)
		throws ErroRepositorioException;
	

	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorGerencia(Integer idGerenciaRegional)throws ErroRepositorioException;
	
		/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorUnidadeNegocio(Integer idUnidadeNegocio)throws ErroRepositorioException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidade()throws ErroRepositorioException ;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public TarifaSocialMotivoCarta pesquisarTarifaSocialMotivoCarta(
			Integer idTarifaSocialMotivoCarta)throws ErroRepositorioException;

	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 02/05/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorGerenciaEUnidade(Integer idGerenciaRegional
			,Integer idUnidadeNegocio)throws ErroRepositorioException;
	
	/**
	 * [UC1170] Gerar Relatório Acesso ao SPC
	 *  
	 * @author: Diogo Peixoto
	 * @date: 06/05/2011
	 * 
	 * @param FiltrarRelatorioAcessoSPCHelper
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> filtrarRelatorioAcessoSPC(FiltrarRelatorioAcessoSPCHelper filtrarRelatorioAcessoSPCHelper) throws ErroRepositorioException;

	
	
	/**
     * Obtém a coleção de categorias.
     * 
     * @author Hugo Azevedo
     * @date 22/06/2011
     * 
     * @throws ErroRepositorioException
     */
	
	public Collection obterCategorias() throws ErroRepositorioException;
	
	
	/**
     * Obtém a coleção de perfis dos imóveis.
     * 
     * @author Hugo Azevedo
     * @date 22/06/2011
     * 
     * @throws ErroRepositorioException
     */
	
	public Collection obterPerfisImoveis() throws ErroRepositorioException;
	
	

	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
	 * 
	 * Consulta chamada pelo "[FS0010 – Validar Identificação do Usuário.]" 
	 * 
	 * @author Mariana Victor
	 * @data 21/06/2011
	 */
	public Boolean verificarIdentificacaoUsuario(Integer idUsuario) throws ErroRepositorioException;

	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
	 * 
	 * Consulta chamada pelo "[FS0010 – Validar Identificação do Usuário.]" 
	 * 
	 * @author Mariana Victor
	 * @data 21/06/2011
	 */
	public Boolean verificarUsuarioEmpresaComandoCobranca(Integer idUsuario, Integer idComando) throws ErroRepositorioException;

	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
	 * 
	 * Pesquisa o email da Empresa 
	 * 
	 * @author Mariana Victor
	 * @data 22/06/2011
	 */
	public String pesquisarEmailEmpresa(Integer idEmpresa) throws ErroRepositorioException;
	
	
	/**
	 * [UC34] Importância Logradouro
	 * 
	 * Atualiza a Importância do Logradouro
	 * 
	 * @author Fernanda Almeida
	 * @data 30/06/2011
	 */
	public void atualizarGrauImportancia(Integer idLogradouro, Integer grauImportancia) throws ErroRepositorioException;
	
	/**
	 * [MA2011061013]
	 * 
	 * @author Paulo Diniz
	 * @date 02/07/2011
	 * 
	 * @param idImovel
	 * 
	 * @return HidrometroMovimentado
	 * @throws ErroRepositorioException
	 */
	public  List<HidrometroInstalacaoHistorico> pesquisarHidrometroPeloIdImovel(Integer idImovel) throws ErroRepositorioException;
	
	/**TODO: COSANPA
	 * @author Wellington Rocha
	 * Data: 21/03/2012
	 * 
	 * Pesquisar todas as ocorrencias de cadastro ativas
	 * 
	 * Geração de Rotas para Recadastramento
	 * 
	 * @return Collection
	 * @throws ControladorException
	 *  
	 */
 	public Collection<CadastroOcorrencia> pesquisarOcorrenciasCadastro() throws ErroRepositorioException;
 	
 	/**TODO: COSANPA
	 * @author Wellington Rocha
	 * Data: 21/03/2012
	 * 
	 * Pesquisar todos os ramos de atividades ativos
	 * 
	 * Geração de Rotas para Recadastramento
	 * 
	 * @return Collection
	 * @throws ControladorException
	 *  
	 */
 	public Collection pesquisarRamosAtividade() throws ErroRepositorioException;
 	
 	/**TODO: COSANPA
	 * @author Wellington Rocha
	 * Data: 21/03/2012
	 * 
	 * Pesquisar todos as fontes de abastecimento ativas
	 * 
	 * Geração de Rotas para Recadastramento
	 * 
	 * @return Collection
	 * @throws ControladorException
	 *  
	 */
 	public Collection pesquisarFonteAbastecimento() throws ErroRepositorioException;
 	
 	/**
 	 * TODO: COSANPA
 	 * @author Matheus Souza
 	 * @param idImovel
 	 * @return Collection
 	 * @throws ErroRepositorioException
 	 */
 	public Collection obterImovelRamoAtividadeAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException;
 	
 	/**
 	 * TODO: COSANPA
 	 * @author Matheus Souza
 	 * @param idImovel, idRamoAtividade
 	 * @return boolean
 	 * @throws ErroRepositorioException
 	 */
 	public boolean existeImovelRamoAtividadeAtualizacaoCadastral(Integer idImovel, Integer idRamoAtividade) throws ErroRepositorioException;
 	
	public boolean existeRamoAtividade(Integer idRamoAtividade)	throws ErroRepositorioException;
	
	public boolean existePessoaSexo(Integer id) throws ErroRepositorioException;

	public void liberarCadastroImovel(Integer idImovel) throws ErroRepositorioException;
}