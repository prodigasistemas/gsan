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
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface IRepositorioCadastro {
	
 	@SuppressWarnings("rawtypes")
	public Collection pesquisarFeriado(Short tipoFeriado, String descricao, 
						Date dataFeriadoInicio, Date dataFeriadoFim, Integer idMunicipio, Integer numeroPagina)throws ErroRepositorioException;	

	public Integer pesquisarFeriadoCount(Short tipoFeriado, String descricao, 
						Date dataFeriadoInicio, Date dataFeriadoFim, Integer idMunicipio)throws ErroRepositorioException;

	public void atualizarMensagemSistema(String mensagemSistema)throws ErroRepositorioException ;
	
	public EnvioEmail pesquisarEnvioEmail(Integer idEnvioEmail)
			throws ErroRepositorioException;
	
	public DadosEnvioEmailHelper pesquisarDadosEmailSistemaParametros()
	throws ErroRepositorioException;
	
	public Collection<Integer> pesquisarTodosIdsSetorComercial() throws ErroRepositorioException ;
	
	public Object[] pesquisarSetorQuadra(Integer idLocalidade)throws ErroRepositorioException;
	
	public Integer pesquisarLogradouroBairro(Integer codigoLogradouro) throws ErroRepositorioException;
	
	public Integer pesquisarLogradouroCep(Integer codigoLogradouro) throws ErroRepositorioException;
	
	public void inserirClienteEndereco(Integer idCliente, String numeroImovelMenor, String numeroImovelMaior,
			Integer idCep, Integer idBairro, Integer idLograd, Integer idLogradBairro, Integer idLogradCep) throws ErroRepositorioException;

	public void inserirClienteImovel(Integer idCliente, Integer idImovel, String data)throws ErroRepositorioException;	
	
	public void inserirImovelSubcategoria(Integer idImovel, Integer idSubcategoria)throws ErroRepositorioException;
	
	public void inserirLigacaoAgua(Integer idImovel, String dataBD)throws ErroRepositorioException;	
	
 	@SuppressWarnings("rawtypes")
	public Collection pesquisarCadastroRibeiraop() throws ErroRepositorioException;
	
	public void atualizarImovelRibeirao(Integer idImovel, Integer codigo)throws ErroRepositorioException;

 	@SuppressWarnings("rawtypes")
	public Collection pesquisarClientesSubordinados(Integer idCliente) throws ErroRepositorioException;

 	@SuppressWarnings("rawtypes")
	public Collection pesquisarRelatorioAtualizacaoCadastral(Collection idLocalidades,
			Collection idSetores, Collection idQuadras, String rotaInicial,
			String rotaFinal, String sequencialRotaInicial, String sequencialRotaFinal) 
		throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarRelatorioImoveisSituacaoLigacaoAgua(FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper filtro) throws ErroRepositorioException;
	
	public Integer pesquisarTotalRegistroRelatorioImoveisSituacaoLigacaoAgua(FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper filtro) throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoAgrupadasLocalizacao(FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoAgrupadasCliente(FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) throws ErroRepositorioException;

	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoLocalizacao(FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) throws ErroRepositorioException;	

	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoCliente(FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) throws ErroRepositorioException;	

	public Collection<RelatorioImoveisConsumoMedioHelper> pesquisarRelatorioImoveisConsumoMedio(FiltrarRelatorioImoveisConsumoMedioHelper filtro, 
															Integer anoMesFaturamento) throws ErroRepositorioException;
	
	public Integer pesquisarTotalRegistroRelatorioImoveisConsumoMedio(FiltrarRelatorioImoveisConsumoMedioHelper filtro, Integer anoMesFaturamento) throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarRelatorioImoveisUltimosConsumosAgua(FiltrarRelatorioImoveisUltimosConsumosAguaHelper filtro) throws ErroRepositorioException;
	
	public Integer pesquisarTotalRegistroRelatorioImoveisUltimosConsumosAgua(FiltrarRelatorioImoveisUltimosConsumosAguaHelper filtro) throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarRelatorioImoveisAtivosNaoMedidos(FiltrarRelatorioImoveisAtivosNaoMedidosHelper filtro) throws ErroRepositorioException;
	
	public Integer pesquisarTotalRegistroRelatorioImoveisAtivosNaoMedidos(FiltrarRelatorioImoveisAtivosNaoMedidosHelper filtro) throws ErroRepositorioException;	
	
	public Collection<Object[]> pesquisarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(
			FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper filtro) throws ErroRepositorioException;
	
	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(
			FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper filtro) throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarRelatorioImoveisTipoConsumo(FiltrarRelatorioImoveisTipoConsumoHelper filtro) throws ErroRepositorioException;
	
	public Integer pesquisarTotalRegistroRelatorioImoveisTipoConsumo(FiltrarRelatorioImoveisTipoConsumoHelper filtro) throws ErroRepositorioException;
	
	public Collection<Imovel> pesquisarImovelArquivoTextoDadosCadastrais(ArquivoTextoDadosCadastraisHelper objeto) throws ErroRepositorioException;
	
	public Collection<HidrometroInstalacaoHistorico> pesquisarImovelArquivoTextoLigacoesHidrometro(ArquivoTextoLigacoesHidrometroHelper objeto) throws ErroRepositorioException;
	
	public Object[] pesquisarDadosRotaEntregaContaPorRota(Integer idRota) throws ErroRepositorioException;
	
 	@SuppressWarnings("rawtypes")
	public Collection pesquisarSetorComercialPorQualidadeAgua(int tipoArgumento, BigDecimal indiceInicial, 
		BigDecimal indiceFinal, Integer anoMesReferencia) throws ErroRepositorioException ;
	
	public Object[] obterImovelGeracaoTabelasTemporarias(Integer idImovel) throws ErroRepositorioException;

 	@SuppressWarnings("rawtypes")
	public Collection obterImovelSubcategoriaAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException;
		
	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoDescritasLocalizacao(
		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
		throws ErroRepositorioException;

	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoDescritasCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
			throws ErroRepositorioException;

    public Collection<NacionalFeriado> pesquisarFeriadosNacionais(String anoOrigemFeriado) throws ErroRepositorioException;
    
    public Collection<MunicipioFeriado> pesquisarFeriadosMunicipais(String anoOrigemFeriado) throws ErroRepositorioException; 
    
    public void excluirFeriadosNacionais( String anoDestino ) throws ErroRepositorioException;
        
    public void excluirFeriadosMunicipais( String anoDestino ) throws ErroRepositorioException;

 	@SuppressWarnings("rawtypes")
    public Collection pesquisarLocalidades() throws ErroRepositorioException ;

 	@SuppressWarnings("rawtypes")
	public Collection pesquisarArquivoTextoAtualizacaoCadastro(String idEmpresa, 
			String idLocalidade, String idAgenteComercial, String idSituacaoTransmissao)throws ErroRepositorioException;

	public ArquivoTextoAtualizacaoCadastral pesquisarArquivoTextoAtualizacaoCadastro(Integer idArquivoTxt)
		throws ErroRepositorioException;
	
	public Collection<ArquivoTextoAtualizacaoCadastral> pesquisarArquivoTextoAtualizacaoCadastro(String[] idsArquivoTxt)
		throws ErroRepositorioException;
	
	public void atualizarArquivoTextoAtualizacaoCadstral(Integer idArquivoTxt, Integer idSituacaoTransmissao)
			throws ErroRepositorioException;
	
	public ArquivoTextoAtualizacaoCadastral pesquisarArquivoTextoAtualizacaoCadastro(String descricao)
	throws ErroRepositorioException;
	
	public Collection<Integer>  obterIdsImovelGeracaoTabelasTemporarias(Integer idSetor, ImovelGeracaoTabelasTemporariasCadastroHelper helper) 
	throws ErroRepositorioException;
	
 	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelDebitoAtualizacaoCadastral(Collection colecaoIdsImovel)
	throws ErroRepositorioException;
	
    public Integer verificarClienteSelecionadoFuncionario(Integer idCliente) throws ErroRepositorioException ;

	public Collection<Object[]> pesquisarQuadraFaceAssociadaQuadra(Integer idQuadra) 
			throws ErroRepositorioException ;
	
	public Collection<Integer> pesquisarSetorComercialGeracaoTabelasTemporarias(ImovelGeracaoTabelasTemporariasCadastroHelper helper) 
	throws ErroRepositorioException;

 	@SuppressWarnings("rawtypes")
	public Collection pesquisarOperacoesEfetuadasComImovelAssociado(Date dataInicio, Date dataFim,Integer idEmpresa)
		throws ErroRepositorioException;

 	@SuppressWarnings("rawtypes")
	public Collection pesquisarOperacoesEfetuadasSemImovelAssociado(Date dataInicio, Date dataFim,Integer idEmpresa)
		throws ErroRepositorioException;
	
	public EmpresaContratoCadastro pesquisarEmpresaContratoCadastro(Integer idEmpresa)
		throws ErroRepositorioException;

 	@SuppressWarnings("rawtypes")
	public Collection pesquisarAtributosBoletim()
		throws ErroRepositorioException;
	
	public BigDecimal pesquisarValorAtualizacaoAtributo(
			Integer idAtributo,Integer idEmpresaContratoCadastro)throws ErroRepositorioException;

 	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosBoleto(int quantidadeInicio, Integer grupo, String nomeEmpresa)throws ErroRepositorioException;
	
	public BigDecimal pesquisarValorLimiteDebitoTipo(Integer idDebitoTipo)throws ErroRepositorioException;
	
	public UnidadeNegocio pesquisarUnidadeNegocioUsuario(Integer idUsuario)throws ErroRepositorioException;
	
 	@SuppressWarnings("rawtypes")
	public List pesquisarImoveisExcluirDaTarifaSocial(Integer idSetor, Integer anoMesFaturamento)throws ErroRepositorioException;

	public void atualizarExcluirDaTarifaSocialTabelaDadoEconomia(String idImovel)throws ErroRepositorioException;
	
	public void atualizarExcluirDaTarifaSocialTabelaImovel(String idImovel)throws ErroRepositorioException;
	
	public Integer pesquisarRelatorioImoveisConsumoMedioCount(
		FiltrarRelatorioImoveisConsumoMedioHelper filtro, Integer anoMesFaturamento) 
		throws ErroRepositorioException;
	
	public Integer pesquisarImovelAtualizacaoCadastralComIndicadorExclusaoCount() throws ErroRepositorioException;
	
	public Integer pesquisarQuantidadeImoveisPorSituacaoAtualizacaoCadastral(
			Integer situacao, Integer idArquivoTexto) throws ErroRepositorioException;
	
	public Collection<Integer> pesquisarIdsImoveisAtualizacaoCadastral(Integer idEmpresaLeiturista, 
			Integer idRota) throws ErroRepositorioException;
	
 	@SuppressWarnings("rawtypes")
	public Collection<Integer> pesquisarRotasAtualizacaoCadastral(
			Collection idsImoveis) throws ErroRepositorioException;
	
	public Collection<AtualizacaoCadastralSimplificadoCritica> pesquisarAtualizacaoCadastralSimplificadoCritica(int idArquivo) throws ErroRepositorioException;
	
	public BigDecimal pesquisarValorSugeridoDebitoTipo(
			Integer idDebitoTipo)throws ErroRepositorioException;

	public ArquivoTextoAtualizacaoCadastral pesquisarArquivoTextoAtualizacaoCadastro(String idArquivoTxt, Integer idSituacaoTransmissao)
		throws ErroRepositorioException;

 	@SuppressWarnings("rawtypes")
	public Collection pesquisarRelatorioImoveisProgramasEspeciaisAnalitico(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper helper) throws ErroRepositorioException;

 	@SuppressWarnings("rawtypes")
	public Collection pesquisarRelatorioImoveisProgramasEspeciaisSintetico(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper helper) throws ErroRepositorioException;
	
	public Integer pesquisarTotalRegistroRelatorioImoveisProgramaEspecial(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper helper) throws ErroRepositorioException;

 	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelEmProgramaEspecial(
			Integer idPerfilProgramaEspecial,
			Rota rota,
			int numeroIndice, 
			int quantidadeRegistros)
		throws ErroRepositorioException;
	
	public Integer verificarExistenciaParcelamentoImovel(Integer idImovel)
			throws ErroRepositorioException;
	
	public Collection<RelatorioColetaMedidorEnergiaHelper> pesquisarRelatorioColetaMedidorEnergia(
			String faturamentoGrupo, String idLocalidadeInicial, String idLocalidadeFinal, 
			String idSetorComercialInicial, String idSetorComercialFinal,
			String rotaInicial, String rotaFinal, 
			String sequencialRotaInicial, String sequencialRotaFinal) throws ErroRepositorioException;
	
	public Integer pesquisarTotalRegistroRelatorioColetaMedidorEnergia(
			String faturamentoGrupo, String idLocalidadeInicial, String idLocalidadeFinal,
			String idSetorComercialInicial, String idSetorComercialFinal,
			String rotaInicial, String rotaFinal,
			String sequencialRotaInicial, String sequencialRotaFinal) throws ErroRepositorioException;
	public Collection<Integer> pesquisarIdsImoveisDoSetorComercial(Integer idSetor,
			int quantidadeInicio, int quantidadeMaxima)throws ErroRepositorioException;
	
	public void atualizarCodigoDebitoAutomatico(Integer idImovel,
			Integer codigoDebitoAutomatico)throws ErroRepositorioException;
	
    public byte[] baixarNovaVersaoJad() throws ErroRepositorioException;
    
    public byte[] baixarNovaVersaoJar() throws ErroRepositorioException;  
    
    public boolean verificarSituacaoImovelCobrancaJudicial(Integer idImovel) throws ErroRepositorioException; 
    
    public boolean verificarSituacaoImovelNegativacao( Integer idImovel ) throws ErroRepositorioException;
    
    public Integer inserirCadastroEmailCliente( Integer idCliente, String nomeClienteAnterior, 
     		String cpfAnterior, String cnpjAnterior, String emailAnterior, String nomeSolicitante, 
     		String cpfSolicitante, String nomeClienteAtual, String cpfClienteAtual,
 			String cnpjClienteAtual, String emailAtual) throws ErroRepositorioException;
    
    public void atualizarSequenciaRotaImovel(RotaAtualizacaoSeq seq ) throws ErroRepositorioException;
    
    public ClienteImovel pesquisarClienteResponsavelComEsferaPoderPublico( Integer idImovel ) throws ErroRepositorioException;
    
 	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesSistemaColunaPorUsuario(GerarRelatorioAlteracoesSistemaColunaHelper helper)
 		throws ErroRepositorioException;
 	
 	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesSistemaColunaPorLocalidade(GerarRelatorioAlteracoesSistemaColunaHelper helper)
 		throws ErroRepositorioException;
 	
    public boolean verificarRelacaoColuna(Integer idColuna) throws ErroRepositorioException;
    
 	public Collection<Object[]> pesquisarDadosRelatorioAtualizacaoCadastralViaInternet(GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
 		throws ErroRepositorioException;
 	
 	public Collection<Object[]> pesquisarDadosRelatorioResumoAtualizacaoCadastralViaInternet(GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
 		throws ErroRepositorioException;
 	
 	public Integer countRelatorioAtualizacaoCadastralViaInternet(GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
 		throws ErroRepositorioException;
 	
 	public Integer pesquisarIdRotaQuadra(Integer idQuadra) throws ErroRepositorioException;
 	
 	@SuppressWarnings("rawtypes")
 	public Collection pesquisarEsferaPoder() throws ErroRepositorioException;

 	@SuppressWarnings("rawtypes")
	public Collection pesquisarRelatorioImoveisAlteracaoInscricaoViaBatch( 
			FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper) 
		throws ErroRepositorioException;
 	
	public Integer countTotalRelatorioImoveisAlteracaoInscricaoViaBatch( 
			FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper) 
		throws ErroRepositorioException;

 	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpjPorUsuario(GerarRelatorioAlteracoesCpfCnpjHelper helper) throws ErroRepositorioException;

 	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpjPorLocalidade(GerarRelatorioAlteracoesCpfCnpjHelper helper) throws ErroRepositorioException;
 	
 	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpjPorMeio(GerarRelatorioAlteracoesCpfCnpjHelper helper) throws ErroRepositorioException;

 	@SuppressWarnings("rawtypes")
	public Collection pesquisaImovelInscricaoAlterada(ImovelInscricaoAlteradaHelper helper) throws ErroRepositorioException;

 	@SuppressWarnings("rawtypes")
	public Collection pesquisarLocalidadesPorGerencia(Integer idGerenciaRegional)throws ErroRepositorioException;

 	@SuppressWarnings("rawtypes")
	public Collection pesquisarLocalidadesPorUnidadeNegocio(Integer idUnidadeNegocio)throws ErroRepositorioException;

 	@SuppressWarnings("rawtypes")
	public Collection pesquisarLocalidade()throws ErroRepositorioException ;
	
	public TarifaSocialMotivoCarta pesquisarTarifaSocialMotivoCarta(Integer idTarifaSocialMotivoCarta)throws ErroRepositorioException;

 	@SuppressWarnings("rawtypes")
	public Collection pesquisarLocalidadesPorGerenciaEUnidade(Integer idGerenciaRegional, Integer idUnidadeNegocio)throws ErroRepositorioException;
	
	public Collection<Object[]> filtrarRelatorioAcessoSPC(FiltrarRelatorioAcessoSPCHelper filtrarRelatorioAcessoSPCHelper) throws ErroRepositorioException;

 	@SuppressWarnings("rawtypes")
	public Collection obterCategorias() throws ErroRepositorioException;

 	@SuppressWarnings("rawtypes")
	public Collection obterPerfisImoveis() throws ErroRepositorioException;
	
	public Boolean verificarIdentificacaoUsuario(Integer idUsuario) throws ErroRepositorioException;

	public Boolean verificarUsuarioEmpresaComandoCobranca(Integer idUsuario, Integer idComando) throws ErroRepositorioException;

	public String pesquisarEmailEmpresa(Integer idEmpresa) throws ErroRepositorioException;
	
	public void atualizarGrauImportancia(Integer idLogradouro, Integer grauImportancia) throws ErroRepositorioException;
	
	public  List<HidrometroInstalacaoHistorico> pesquisarHidrometroPeloIdImovel(Integer idImovel) throws ErroRepositorioException;
	
 	public Collection<CadastroOcorrencia> pesquisarOcorrenciasCadastro() throws ErroRepositorioException;
 	
 	@SuppressWarnings("rawtypes")
	public Collection pesquisarRamosAtividade() throws ErroRepositorioException;

 	@SuppressWarnings("rawtypes")
 	public Collection pesquisarFonteAbastecimento() throws ErroRepositorioException;

 	@SuppressWarnings("rawtypes")
 	public Collection obterImovelRamoAtividadeAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException;
 	
 	public boolean existeImovelRamoAtividadeAtualizacaoCadastral(Integer idImovel, Integer idRamoAtividade) throws ErroRepositorioException;
 	
	public boolean existeRamoAtividade(Integer idRamoAtividade)	throws ErroRepositorioException;
	
	public boolean existePessoaSexo(Integer id) throws ErroRepositorioException;

	public void liberarCadastroImovel(Integer idImovel) throws ErroRepositorioException;
	
	public Integer pesquisarIdSetorComercialPorCodigoELocalidade(Integer idLocalidade, Integer codigoSetor) throws ErroRepositorioException;
	
	public Integer pesquisarIdQuadraPorNumeroQuadraEIdSetor(Integer idSetorComercial, Integer numeroQuadra) throws ErroRepositorioException;
	
	public String retornaIpServidorOperacional() throws ErroRepositorioException;

	public SituacaoAtualizacaoCadastral pesquisarSituacaoAtualizacaoCadastralPorId(Integer idSituacaoCadastral) throws ErroRepositorioException;
}
