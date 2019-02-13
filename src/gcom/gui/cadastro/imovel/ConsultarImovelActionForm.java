package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ImovelMicromedicao;
import gcom.fachada.Fachada;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Action Form do [UC0472]Consultar Imovel
 * 
 * @author Rafael Santos
 * @since 07/09/2006
 */
public class ConsultarImovelActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	//Dados 1° Aba  - Dados Cadastrais
	private String idImovelDadosCadastrais;
	private String matriculaImovelDadosCadastrais;
	private String enderecoImovelDadosCadastrais;
	private String descricaoMunicipio;
	private String enderecoAnaliseMedicaoConsumo;
	private String situacaoAguaDadosCadastrais;
	private String situacaoEsgotoDadosCadastrais;
	private String imovelPerfilDadosCadastrais;
	private String despejoDadosCadastrais;
	private String areaConstruidaDadosDadosCadastrais;
	private String testadaLoteDadosCadastrais;
	private String volumeReservatorioInferiorDadosCadastrais;
	private String volumeReservatorioSuperiorDadosCadastrais;
	private String volumePiscinaDadosCadastrais;
	private String fonteAbastecimentoDadosCadastrais;
	private String pocoTipoDadosCadastrais;
	private String distritoOperacionalDadosCadastrais;
	private String divisaoEsgotoDadosCadastrais;
	private String pavimentoRuaDadosCadastrais;
	private String pavimentoCalcadaDadosCadastrais;
	private String numeroIptuDadosCadastrais;
	private String numeroCelpeDadosCadastrais;
	private String coordenadaXDadosCadastrais;
	private String coordenadaYDadosCadastrais;
	private String cadastroOcorrenciaDadosCadastrais;
	private String eloAnormalidadeDadosCadastrais;
	private String indicadorImovelCondominioDadosCadastrais;
	private String imovelCondominioDadosCadastrais;
	private String imovelPrincipalDadosCadastrais;
	private String numeroPontosUtilizacaoDadosCadastrais;
	private String numeroMoradoresDadosCadastrais;
	private String jardimDadosCadastrais;
	private String tipoHabitacaoDadosCadastrais;
	private String tipoPropriedadeDadosCadastrais;
	private String tipoConstrucaoDadosCadastrais;
	private String tipoCoberturaDadosCadastrais;
	private String dataProcessamento;
	private String observacaoCategoriaDadosCadastrais;
	private String dataEmissaoComunicadoIrregularidadeFaturamento;
	
	
	private String indicadorNivelInstalacaoEsgotoDadosCadastrais;
	
	//Dados 1° Aba  - Dados Complementares
	private String idImovelDadosComplementares;
	private String matriculaImovelDadosComplementares;
	private String enderecoImovelDadosComplementares;
	private String situacaoAguaDadosComplementares;
	private String situacaoEsgotoDadosComplementares;
	private String tarifaConsumoDadosComplementares;
	private String quantidadeRetificacoesDadosComplementares;
	private String quantidadeParcelamentosDadosComplementares;
	private String quantidadeReparcelamentoDadosComplementares;
	private String quantidadeReparcelamentoConsecutivosDadosComplementares;
	private String situacaoCobrancaDadosComplementares;
	private String descricaoOcorrenciaDadosComplementares;
	private String descricaoAnormalidadeDadosComplementares;
	private String idFuncionario;
	private String nomeFuncionario;
	private String informacoesComplementares;
	
	//Dados 3° Aba  - Analise Medicao e Consumo
	private String idImovelAnaliseMedicaoConsumo;
	private String matriculaImovelAnaliseMedicaoConsumo;
	private String situacaoAguaAnaliseMedicaoConsumo;
	private String situacaoEsgotoAnaliseMedicaoConsumo;
	private String tipoApresentacao;
	private String localidade;
	private String nomeLocalidade;
	private String setorComercialID;
	private String setorComercial;
	private String setorComercialNome;
	private String quadraInicial;
	private String quadraInicialNome;
	private String quadraInicialID;
	private String quadraInicialMensagem;
	private String quadraFinal;
	private String quadraFinalNome;
	private String quadraFinalID;
	private String quadraFinalMensagem;
	private String imovel;
	private String imovelCondominio;
	private String grupoFaturamento;
	private String diaVencimento;
	private String empresaLeitura;
	private String rota;
	private String sequencialRota;
	private String indicadorImovelCondominio;
	private String perfilImovel;
	private String categoriaImovel;
	private String quantidadeEconomia;
	private String hint1;

    private String idTipoMedicao;
	private String tipoLigacao;
	private String tipoAnormalidade;
	private String anormalidadeLeituraInformada;
	private String anormalidadeLeituraFaturada;
	private String anormalidadeConsumo;
	private String consumoFaturamdoMinimo;
	private String consumoMedidoMinimo;
	private String consumoMedioMinimo;
	private String inscricaoTipo;
	private String mesAnoFaturamentoCorrente;
	private String inscricaoImovel;
	private String enderecoFormatado;
	private String ligacaoAguaSituacao;
	private String ligacaoEsgotoSituacao;
	private String clienteNome;
	private String clienteCpfCnpj;
	private Collection<ImovelMicromedicao> imoveisMicromedicaoEsgoto;
	private String ligacaoAguaSituacaoId;
	private String ligacaoEsgotoSituacaoId;
	private String leituraInstalacaoHidrometro;
	
	
	
	//dados do hidrometro da ligacao agua
	private String tipoMedicao;
	private String numeroHidrometro;
	private String instalacaoHidrometro;
	private String capacidadeHidrometro;
	
	private String tipoHidrometro;
	private String marcaHidrometro;
	private String localInstalacaoHidrometro;
	private String diametroHidrometro;
	
	private String protecaoHidrometro;
	private String indicadorCavalete;
	private String anoFabricacao;
	private String tipoRelojoaria;
	private String usuarioResponsavelInstalacao;
	private String numeroLacreInstalacao;
	
	//dados do hidrometro da poco
	private String tipoMedicaoPoco;
	private String numeroHidrometroPoco;
	private String instalacaoHidrometroPoco;
	private String capacidadeHidrometroPoco;
	
	private String tipoHidrometroPoco;
	private String marcaHidrometroPoco;
	private String localInstalacaoHidrometroPoco;
	private String diametroHidrometroPoco;
	
	private String protecaoHidrometroPoco;
	private String indicadorCavaletePoco;
	private String anoFabricacaoPoco;
	private String tipoRelojoariaPoco;
	private String usuarioResponsavelInstalacaoPoco;
	private String numeroLacreInstalacaoPoco;
	
	
	private String dtLeituraAnterior;
	private String leituraAnterior;
	private String dtLeituraInformada;
	private String leituraAtualInformada;
	private String situacaoLeituraAtual;
	private String codigoFuncionario;
	private String dtLeituraFaturada;
	private String leituraAtualFaturada;
	private String consumoRateio;
	private String consumoFaturado;
	private String consumoMedido;
	private String percentualVariacao;
	private String consumoMedioHidrometro;
	private String diasConsumo;
	private String consumoTipo;
	private String idImovelSubstituirConsumo;
	private String habilitaLupa;
	private String idEmpresa;
	private String idGrupoFaturamento;
	private String localidadeFiltro;
	private String nomeLocalidadeFiltro;
	private String setorComercialFiltro;
	private String quadraInicialFiltro;
	private String quadraFinalFiltro;
	private String imovelFiltro;
	private String imovelCondominioFiltro;
	private String imovelMatriculaFiltro;
	private String imovelMatriculaCondominioFiltro;
	private String dataLigacaoAgua;
	private String dataCorteAgua;
	private String dataReligacaoAgua;
	private String dataSupressaoAgua;
	private String dataSupressaoParcialAgua;
	private String dataRestabelecimentoAgua;
	private String descricaoLigacaoAguaDiametro;
	private String descricaoLigacaoAguaMaterial;
	private String descricaoligacaoAguaPerfil;
	private String numeroConsumominimoAgua;
	
	private String idLigacaoEsgoto;
	private String consumoMesEsgoto;
	private String dataLigacaoEsgoto;
	private String descricaoLigacaoEsgotoDiametro;
	private String descricaoLigacaoEsgotoMaterial;
	private String descricaoligacaoEsgotoPerfil;
	private String numeroConsumominimoEsgoto;
	private String percentualEsgoto;
	private String percentualAguaConsumidaColetada;
	private String descricaoPocoTipo;
	private String posicaoLigacaoEsgoto;
	private String condicaoEsgotamento;
	private String sistemaCaixaInspecao;
	private String destinoDejetos;
	private String destinoAguasPluviais;
	
	private String idGrupoFaturamentoFiltro;
	private String idEmpresaFiltro;
	private String indicadorImovelCondominioFiltro;
	private String consumoMedioImovel;
	private String perfilImovelFiltro;
	private String categoriaImovelFiltro;
	private String quantidadeEconomiaFiltro;
	private String tipoMedicaoFiltro;
	private String idTipoMedicaoFiltro;
	private String tipoLigacaoFiltro;
	private String tipoAnormalidadeFiltro;
	private String anormalidadeLeituraInformadaFiltro;
	private String anormalidadeLeituraFaturadaFiltro;
	private String anormalidadeConsumoFiltro;
	private String consumoFaturamdoMinimoFiltro;
	private String consumoMedidoMinimoFiltro;
	private String consumoMedioMinimoFiltro;
	private String idLigacaoAguaSituacao;
	private String idLigacaoAgua;
	private String idAnormalidade;
	private String descricaoAnormalidade;
	private String leituraInstalacaoHidrometroPoco;

	//Dados da medição do mes do poço
	private String dtLeituraAnteriorPoco;
	private String leituraAnteriorPoco;
	private String dtLeituraInformadaPoco;
	private String leituraAtualInformadaPoco;
	private String dtLeituraFaturadaPoco;
	private String leituraAtualFaturadaPoco;
	private String situacaoLeituraAtualPoco;
	private String codigoFuncionarioPoco;
	private String anormalidadeLeituraInformadaPoco;
	private String anormalidadeLeituraFaturadaPoco;
	private String consumoMedioHidrometroPoco;
	

	private String consumoMedidoEsgoto;
	private String consumoFaturadoEsgoto;
	private String consumoRateioEsgoto;
	private String consumoMedioImovelEsgoto;
	private String anormalidadeConsumoEsgoto;
	private String percentualVariacaoEsgoto;
	private String diasConsumoEsgoto;
	private String consumoTipoEsgoto;
	
	
	//4° Aba - Historico Faturamento
	private String idImovelHistoricoFaturamento;
	private String matriculaImovelHistoricoFaturamento;
	private String situacaoAguaHistoricoFaturamento;
	private String situacaoEsgotoHistoricoFaturamento;
	private String conta;
	private String descricaoImovel;
	private String nomeClienteUsuario;
	
	//5° Aba - Debitos
	private String idImovelDebitos;
	private String matriculaImovelDebitos;
	private String situacaoEsgotoDebitos;
	private String situacaoAguaDebitos;
	private String codigoImovel;
	private String tipoRelacao;
	private String referenciaInicial;
	private String referenciaFinal;
	private String dataVencimentoInicial;
	private String dataVencimentoFinal;
	private String ligacaoAgua;
	private String ligacaoEsgoto;
	private String maticula;
	private String inscricao;
	private String nomeCliente;
	private String tipoRelacaoCliente;
	private String cpf;
	private String cnpj;
	private String refInicial;
	private String refFinal;
	private String dtInicial;
	private String dtFinal;
	private String indicadorEmissaoExtratoNaConsulta;
	
	//6° Aba - Pagamentos do Imovel
	private String idImovelPagamentos;
	private String matriculaImovelPagamentos;
	private String situacaoAguaPagamentos;
	private String situacaoEsgotoPagamentos;
	private String arrecadador;

	//7° Aba  -
	private String idImovelDevolucoesImovel;
	private String matriculaImovelDevolucoesImovel;
	private String situacaoAguaDevolucoesImovel;
	private String situacaoEsgotoDevolucoesImovel;
	
	private String cpfCnpj;
	private String profissao;
	private String ramoAtividade;
	private String telefone;
	private String idArrecadador;
	private String descricaoArrecadador;
	private String dataLancamento;
	private String sequencialAviso;
	private String tipoAviso;
	private String idImovel;
	private String idCliente;
	

	
	//Dados 8° Aba  - Documentos Cobrança
	private String idImovelDocumentosCobranca;
	private String matriculaImovelDocumentosCobranca;
	private String situacaoAguaDocumentosCobranca;
	private String situacaoEsgotoDocumentosCobranca;

	//Dados 9° Aba  - Parcelamentos Debitos
	private String idImovelParcelamentosDebitos;
	private String matriculaImovelParcelamentosDebitos;
	private String situacaoAguaParcelamentosDebitos;
	private String situacaoEsgotoParcelamentosDebitos;
	private String parcelamento;
	private String reparcelamento;
	private String reparcelamentoConsecutivo;

	//Dados 10° Aba  - Registro Atendimento
	private String idImovelRegistroAtendimento;
	private String matriculaImovelRegistroAtendimento;
	private String situacaoAguaRegistroAtendimento;
	private String situacaoEsgotoRegistroAtendimento;
    
    private String imovIdAnt;
	
	
	/**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     */
    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    }


	public String getInformacoesComplementares() {
		return informacoesComplementares;
	}


	public void setInformacoesComplementares(String informacoesComplementares) {
		this.informacoesComplementares = informacoesComplementares;
	}


	public String getIdImovelDadosCadastrais() {
		return idImovelDadosCadastrais;
	}

	public void setIdImovelDadosCadastrais(String idImovelDadosCadastrais) {
		this.idImovelDadosCadastrais = idImovelDadosCadastrais;
	}


	public String getEnderecoImovelDadosCadastrais() {
		return enderecoImovelDadosCadastrais;
	}


	public void setEnderecoImovelDadosCadastrais(
			String enderecoImovelDadosCadastrais) {
		this.enderecoImovelDadosCadastrais = enderecoImovelDadosCadastrais;
	}


	public String getSituacaoAguaDadosCadastrais() {
		return situacaoAguaDadosCadastrais;
	}


	public void setSituacaoAguaDadosCadastrais(String situacaoAguaDadosCadastrais) {
		this.situacaoAguaDadosCadastrais = situacaoAguaDadosCadastrais;
	}


	public String getSituacaoEsgotoDadosCadastrais() {
		return situacaoEsgotoDadosCadastrais;
	}


	public void setSituacaoEsgotoDadosCadastrais(
			String situacaoEsgotoDadosCadastrais) {
		this.situacaoEsgotoDadosCadastrais = situacaoEsgotoDadosCadastrais;
	}


	public String getAreaConstruidaDadosDadosCadastrais() {
		return areaConstruidaDadosDadosCadastrais;
	}


	public void setAreaConstruidaDadosDadosCadastrais(
			String areaConstruidaDadosDadosCadastrais) {
		this.areaConstruidaDadosDadosCadastrais = areaConstruidaDadosDadosCadastrais;
	}


	public String getCadastroOcorrenciaDadosCadastrais() {
		return cadastroOcorrenciaDadosCadastrais;
	}


	public void setCadastroOcorrenciaDadosCadastrais(
			String cadastroOcorrenciaDadosCadastrais) {
		this.cadastroOcorrenciaDadosCadastrais = cadastroOcorrenciaDadosCadastrais;
	}


	public String getCoordenadaXDadosCadastrais() {
		return coordenadaXDadosCadastrais;
	}


	public void setCoordenadaXDadosCadastrais(String coordenadaXDadosCadastrais) {
		this.coordenadaXDadosCadastrais = coordenadaXDadosCadastrais;
	}


	public String getCoordenadaYDadosCadastrais() {
		return coordenadaYDadosCadastrais;
	}


	public void setCoordenadaYDadosCadastrais(String coordenadaYDadosCadastrais) {
		this.coordenadaYDadosCadastrais = coordenadaYDadosCadastrais;
	}


	public String getDespejoDadosCadastrais() {
		return despejoDadosCadastrais;
	}


	public void setDespejoDadosCadastrais(String despejoDadosCadastrais) {
		this.despejoDadosCadastrais = despejoDadosCadastrais;
	}


	public String getDistritoOperacionalDadosCadastrais() {
		return distritoOperacionalDadosCadastrais;
	}


	public void setDistritoOperacionalDadosCadastrais(
			String distritoOperacionalDadosCadastrais) {
		this.distritoOperacionalDadosCadastrais = distritoOperacionalDadosCadastrais;
	}


	public String getEloAnormalidadeDadosCadastrais() {
		return eloAnormalidadeDadosCadastrais;
	}


	public void setEloAnormalidadeDadosCadastrais(
			String eloAnormalidadeDadosCadastrais) {
		this.eloAnormalidadeDadosCadastrais = eloAnormalidadeDadosCadastrais;
	}


	public String getFonteAbastecimentoDadosCadastrais() {
		return fonteAbastecimentoDadosCadastrais;
	}


	public void setFonteAbastecimentoDadosCadastrais(
			String fonteAbastecimentoDadosCadastrais) {
		this.fonteAbastecimentoDadosCadastrais = fonteAbastecimentoDadosCadastrais;
	}


	public String getImovelCondominioDadosCadastrais() {
		return imovelCondominioDadosCadastrais;
	}


	public void setImovelCondominioDadosCadastrais(
			String imovelCondominioDadosCadastrais) {
		this.imovelCondominioDadosCadastrais = imovelCondominioDadosCadastrais;
	}


	public String getImovelPerfilDadosCadastrais() {
		return imovelPerfilDadosCadastrais;
	}


	public void setImovelPerfilDadosCadastrais(String imovelPerfilDadosCadastrais) {
		this.imovelPerfilDadosCadastrais = imovelPerfilDadosCadastrais;
	}


	public String getImovelPrincipalDadosCadastrais() {
		return imovelPrincipalDadosCadastrais;
	}


	public void setImovelPrincipalDadosCadastrais(
			String imovelPrincipalDadosCadastrais) {
		this.imovelPrincipalDadosCadastrais = imovelPrincipalDadosCadastrais;
	}


	public String getIndicadorImovelCondominioDadosCadastrais() {
		return indicadorImovelCondominioDadosCadastrais;
	}


	public void setIndicadorImovelCondominioDadosCadastrais(
			String indicadorImovelCondominioDadosCadastrais) {
		this.indicadorImovelCondominioDadosCadastrais = indicadorImovelCondominioDadosCadastrais;
	}


	public String getNumeroCelpeDadosCadastrais() {
		return numeroCelpeDadosCadastrais;
	}


	public void setNumeroCelpeDadosCadastrais(String numeroCelpeDadosCadastrais) {
		this.numeroCelpeDadosCadastrais = numeroCelpeDadosCadastrais;
	}


	public String getNumeroIptuDadosCadastrais() {
		return numeroIptuDadosCadastrais;
	}


	public void setNumeroIptuDadosCadastrais(String numeroIptuDadosCadastrais) {
		this.numeroIptuDadosCadastrais = numeroIptuDadosCadastrais;
	}


	public String getPavimentoCalcadaDadosCadastrais() {
		return pavimentoCalcadaDadosCadastrais;
	}


	public void setPavimentoCalcadaDadosCadastrais(
			String pavimentoCalcadaDadosCadastrais) {
		this.pavimentoCalcadaDadosCadastrais = pavimentoCalcadaDadosCadastrais;
	}


	public String getPavimentoRuaDadosCadastrais() {
		return pavimentoRuaDadosCadastrais;
	}


	public void setPavimentoRuaDadosCadastrais(String pavimentoRuaDadosCadastrais) {
		this.pavimentoRuaDadosCadastrais = pavimentoRuaDadosCadastrais;
	}


	public String getPocoTipoDadosCadastrais() {
		return pocoTipoDadosCadastrais;
	}


	public void setPocoTipoDadosCadastrais(String pocoTipoDadosCadastrais) {
		this.pocoTipoDadosCadastrais = pocoTipoDadosCadastrais;
	}


	public String getTestadaLoteDadosCadastrais() {
		return testadaLoteDadosCadastrais;
	}


	public void setTestadaLoteDadosCadastrais(String testadaLoteDadosCadastrais) {
		this.testadaLoteDadosCadastrais = testadaLoteDadosCadastrais;
	}


	public String getVolumePiscinaDadosCadastrais() {
		return volumePiscinaDadosCadastrais;
	}


	public void setVolumePiscinaDadosCadastrais(String volumePiscinaDadosCadastrais) {
		this.volumePiscinaDadosCadastrais = volumePiscinaDadosCadastrais;
	}


	public String getVolumeReservatorioInferiorDadosCadastrais() {
		return volumeReservatorioInferiorDadosCadastrais;
	}


	public void setVolumeReservatorioInferiorDadosCadastrais(
			String volumeReservatorioInferiorDadosCadastrais) {
		this.volumeReservatorioInferiorDadosCadastrais = volumeReservatorioInferiorDadosCadastrais;
	}


	public String getVolumeReservatorioSuperiorDadosCadastrais() {
		return volumeReservatorioSuperiorDadosCadastrais;
	}


	public void setVolumeReservatorioSuperiorDadosCadastrais(
			String volumeReservatorioSuperiorDadosCadastrais) {
		this.volumeReservatorioSuperiorDadosCadastrais = volumeReservatorioSuperiorDadosCadastrais;
	}


	public String getNumeroMoradoresDadosCadastrais() {
		return numeroMoradoresDadosCadastrais;
	}


	public void setNumeroMoradoresDadosCadastrais(
			String numeroMoradoresDadosCadastrais) {
		this.numeroMoradoresDadosCadastrais = numeroMoradoresDadosCadastrais;
	}


	public String getNumeroPontosUtilizacaoDadosCadastrais() {
		return numeroPontosUtilizacaoDadosCadastrais;
	}


	public void setNumeroPontosUtilizacaoDadosCadastrais(
			String numeroPontosUtilizacaoDadosCadastrais) {
		this.numeroPontosUtilizacaoDadosCadastrais = numeroPontosUtilizacaoDadosCadastrais;
	}


	public String getEnderecoImovelDadosComplementares() {
		return enderecoImovelDadosComplementares;
	}


	public void setEnderecoImovelDadosComplementares(
			String enderecoImovelDadosComplementares) {
		this.enderecoImovelDadosComplementares = enderecoImovelDadosComplementares;
	}


	public String getIdImovelDadosComplementares() {
		return idImovelDadosComplementares;
	}


	public void setIdImovelDadosComplementares(String idImovelDadosComplementares) {
		this.idImovelDadosComplementares = idImovelDadosComplementares;
	}


	public String getSituacaoAguaDadosComplementares() {
		return situacaoAguaDadosComplementares;
	}


	public void setSituacaoAguaDadosComplementares(
			String situacaoAguaDadosComplementares) {
		this.situacaoAguaDadosComplementares = situacaoAguaDadosComplementares;
	}


	public String getSituacaoEsgotoDadosComplementares() {
		return situacaoEsgotoDadosComplementares;
	}


	public void setSituacaoEsgotoDadosComplementares(
			String situacaoEsgotoDadosComplementares) {
		this.situacaoEsgotoDadosComplementares = situacaoEsgotoDadosComplementares;
	}


	public String getDescricaoAnormalidadeDadosComplementares() {
		return descricaoAnormalidadeDadosComplementares;
	}


	public void setDescricaoAnormalidadeDadosComplementares(
			String descricaoAnormalidadeDadosComplementares) {
		this.descricaoAnormalidadeDadosComplementares = descricaoAnormalidadeDadosComplementares;
	}
	
	public String getLeituraInstalacaoHidrometroPoco() {
		return leituraInstalacaoHidrometroPoco;
	}


	public void setLeituraInstalacaoHidrometroPoco(String leituraInstalacaoHidrometroPoco) {
		this.leituraInstalacaoHidrometroPoco = leituraInstalacaoHidrometroPoco;
	}

	public String getDescricaoOcorrenciaDadosComplementares() {
		return descricaoOcorrenciaDadosComplementares;
	}


	public void setDescricaoOcorrenciaDadosComplementares(
			String descricaoOcorrenciaDadosComplementares) {
		this.descricaoOcorrenciaDadosComplementares = descricaoOcorrenciaDadosComplementares;
	}


	public String getQuantidadeParcelamentosDadosComplementares() {
		return quantidadeParcelamentosDadosComplementares;
	}


	public void setQuantidadeParcelamentosDadosComplementares(
			String quantidadeParcelamentosDadosComplementares) {
		this.quantidadeParcelamentosDadosComplementares = quantidadeParcelamentosDadosComplementares;
	}


	public String getQuantidadeReparcelamentoConsecutivosDadosComplementares() {
		return quantidadeReparcelamentoConsecutivosDadosComplementares;
	}


	public void setQuantidadeReparcelamentoConsecutivosDadosComplementares(
			String quantidadeReparcelamentoConsecutivosDadosComplementares) {
		this.quantidadeReparcelamentoConsecutivosDadosComplementares = quantidadeReparcelamentoConsecutivosDadosComplementares;
	}


	public String getQuantidadeReparcelamentoDadosComplementares() {
		return quantidadeReparcelamentoDadosComplementares;
	}


	public void setQuantidadeReparcelamentoDadosComplementares(
			String quantidadeReparcelamentoDadosComplementares) {
		this.quantidadeReparcelamentoDadosComplementares = quantidadeReparcelamentoDadosComplementares;
	}


	public String getQuantidadeRetificacoesDadosComplementares() {
		return quantidadeRetificacoesDadosComplementares;
	}


	public void setQuantidadeRetificacoesDadosComplementares(
			String quantidadeRetificacoesDadosComplementares) {
		this.quantidadeRetificacoesDadosComplementares = quantidadeRetificacoesDadosComplementares;
	}


	public String getTarifaConsumoDadosComplementares() {
		return tarifaConsumoDadosComplementares;
	}


	public void setTarifaConsumoDadosComplementares(
			String tarifaConsumoDadosComplementares) {
		this.tarifaConsumoDadosComplementares = tarifaConsumoDadosComplementares;
	}


	public String getAnoFabricacao() {
		return anoFabricacao;
	}


	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}


	public String getAnormalidadeConsumo() {
		return anormalidadeConsumo;
	}


	public void setAnormalidadeConsumo(String anormalidadeConsumo) {
		this.anormalidadeConsumo = anormalidadeConsumo;
	}


	public String getAnormalidadeConsumoFiltro() {
		return anormalidadeConsumoFiltro;
	}


	public void setAnormalidadeConsumoFiltro(String anormalidadeConsumoFiltro) {
		this.anormalidadeConsumoFiltro = anormalidadeConsumoFiltro;
	}


	public String getAnormalidadeLeituraFaturada() {
		return anormalidadeLeituraFaturada;
	}


	public void setAnormalidadeLeituraFaturada(String anormalidadeLeituraFaturada) {
		this.anormalidadeLeituraFaturada = anormalidadeLeituraFaturada;
	}


	public String getAnormalidadeLeituraFaturadaFiltro() {
		return anormalidadeLeituraFaturadaFiltro;
	}


	public void setAnormalidadeLeituraFaturadaFiltro(
			String anormalidadeLeituraFaturadaFiltro) {
		this.anormalidadeLeituraFaturadaFiltro = anormalidadeLeituraFaturadaFiltro;
	}


	public String getAnormalidadeLeituraInformada() {
		return anormalidadeLeituraInformada;
	}


	public void setAnormalidadeLeituraInformada(String anormalidadeLeituraInformada) {
		this.anormalidadeLeituraInformada = anormalidadeLeituraInformada;
	}


	public String getAnormalidadeLeituraInformadaFiltro() {
		return anormalidadeLeituraInformadaFiltro;
	}


	public void setAnormalidadeLeituraInformadaFiltro(
			String anormalidadeLeituraInformadaFiltro) {
		this.anormalidadeLeituraInformadaFiltro = anormalidadeLeituraInformadaFiltro;
	}


	public String getCapacidadeHidrometro() {
		return capacidadeHidrometro;
	}


	public void setCapacidadeHidrometro(String capacidadeHidrometro) {
		this.capacidadeHidrometro = capacidadeHidrometro;
	}


	public String getCategoriaImovel() {
		return categoriaImovel;
	}


	public void setCategoriaImovel(String categoriaImovel) {
		this.categoriaImovel = categoriaImovel;
	}


	public String getCategoriaImovelFiltro() {
		return categoriaImovelFiltro;
	}


	public void setCategoriaImovelFiltro(String categoriaImovelFiltro) {
		this.categoriaImovelFiltro = categoriaImovelFiltro;
	}


	public String getClienteCpfCnpj() {
		return clienteCpfCnpj;
	}


	public void setClienteCpfCnpj(String clienteCpfCnpj) {
		this.clienteCpfCnpj = clienteCpfCnpj;
	}


	public String getClienteNome() {
		return clienteNome;
	}


	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}


	public String getCodigoFuncionario() {
		return codigoFuncionario;
	}


	public void setCodigoFuncionario(String codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}


	public String getConsumoFaturado() {
		return consumoFaturado;
	}


	public void setConsumoFaturado(String consumoFaturado) {
		this.consumoFaturado = consumoFaturado;
	}


	public String getConsumoFaturamdoMinimo() {
		return consumoFaturamdoMinimo;
	}


	public void setConsumoFaturamdoMinimo(String consumoFaturamdoMinimo) {
		this.consumoFaturamdoMinimo = consumoFaturamdoMinimo;
	}


	public String getConsumoFaturamdoMinimoFiltro() {
		return consumoFaturamdoMinimoFiltro;
	}


	public void setConsumoFaturamdoMinimoFiltro(String consumoFaturamdoMinimoFiltro) {
		this.consumoFaturamdoMinimoFiltro = consumoFaturamdoMinimoFiltro;
	}


	public String getConsumoMedido() {
		return consumoMedido;
	}


	public void setConsumoMedido(String consumoMedido) {
		this.consumoMedido = consumoMedido;
	}


	public String getConsumoMedidoMinimo() {
		return consumoMedidoMinimo;
	}


	public void setConsumoMedidoMinimo(String consumoMedidoMinimo) {
		this.consumoMedidoMinimo = consumoMedidoMinimo;
	}


	public String getConsumoMedidoMinimoFiltro() {
		return consumoMedidoMinimoFiltro;
	}


	public void setConsumoMedidoMinimoFiltro(String consumoMedidoMinimoFiltro) {
		this.consumoMedidoMinimoFiltro = consumoMedidoMinimoFiltro;
	}


	public String getConsumoMedioHidrometro() {
		return consumoMedioHidrometro;
	}


	public void setConsumoMedioHidrometro(String consumoMedioHidrometro) {
		this.consumoMedioHidrometro = consumoMedioHidrometro;
	}


	public String getConsumoMedioImovel() {
		return consumoMedioImovel;
	}


	public void setConsumoMedioImovel(String consumoMedioImovel) {
		this.consumoMedioImovel = consumoMedioImovel;
	}


	public String getConsumoMedioMinimo() {
		return consumoMedioMinimo;
	}


	public void setConsumoMedioMinimo(String consumoMedioMinimo) {
		this.consumoMedioMinimo = consumoMedioMinimo;
	}


	public String getConsumoMedioMinimoFiltro() {
		return consumoMedioMinimoFiltro;
	}


	public void setConsumoMedioMinimoFiltro(String consumoMedioMinimoFiltro) {
		this.consumoMedioMinimoFiltro = consumoMedioMinimoFiltro;
	}


	public String getConsumoMesEsgoto() {
		return consumoMesEsgoto;
	}


	public void setConsumoMesEsgoto(String consumoMesEsgoto) {
		this.consumoMesEsgoto = consumoMesEsgoto;
	}


	public String getConsumoRateio() {
		return consumoRateio;
	}


	public void setConsumoRateio(String consumoRateio) {
		this.consumoRateio = consumoRateio;
	}


	public String getConsumoTipo() {
		return consumoTipo;
	}


	public void setConsumoTipo(String consumoTipo) {
		this.consumoTipo = consumoTipo;
	}


	public String getDataCorteAgua() {
		return dataCorteAgua;
	}


	public void setDataCorteAgua(String dataCorteAgua) {
		this.dataCorteAgua = dataCorteAgua;
	}


	public String getDataLigacaoAgua() {
		return dataLigacaoAgua;
	}


	public void setDataLigacaoAgua(String dataLigacaoAgua) {
		this.dataLigacaoAgua = dataLigacaoAgua;
	}


	public String getDataLigacaoEsgoto() {
		return dataLigacaoEsgoto;
	}


	public void setDataLigacaoEsgoto(String dataLigacaoEsgoto) {
		this.dataLigacaoEsgoto = dataLigacaoEsgoto;
	}


	public String getDataReligacaoAgua() {
		return dataReligacaoAgua;
	}


	public void setDataReligacaoAgua(String dataReligacaoAgua) {
		this.dataReligacaoAgua = dataReligacaoAgua;
	}


	public String getDataRestabelecimentoAgua() {
		return dataRestabelecimentoAgua;
	}


	public void setDataRestabelecimentoAgua(String dataRestabelecimentoAgua) {
		this.dataRestabelecimentoAgua = dataRestabelecimentoAgua;
	}


	public String getDataSupressaoAgua() {
		return dataSupressaoAgua;
	}


	public void setDataSupressaoAgua(String dataSupressaoAgua) {
		this.dataSupressaoAgua = dataSupressaoAgua;
	}


	public String getDescricaoAnormalidade() {
		return descricaoAnormalidade;
	}


	public void setDescricaoAnormalidade(String descricaoAnormalidade) {
		this.descricaoAnormalidade = descricaoAnormalidade;
	}


	public String getDescricaoLigacaoAguaDiametro() {
		return descricaoLigacaoAguaDiametro;
	}


	public void setDescricaoLigacaoAguaDiametro(String descricaoLigacaoAguaDiametro) {
		this.descricaoLigacaoAguaDiametro = descricaoLigacaoAguaDiametro;
	}


	public String getDescricaoLigacaoAguaMaterial() {
		return descricaoLigacaoAguaMaterial;
	}


	public void setDescricaoLigacaoAguaMaterial(String descricaoLigacaoAguaMaterial) {
		this.descricaoLigacaoAguaMaterial = descricaoLigacaoAguaMaterial;
	}


	public String getDescricaoligacaoAguaPerfil() {
		return descricaoligacaoAguaPerfil;
	}


	public void setDescricaoligacaoAguaPerfil(String descricaoligacaoAguaPerfil) {
		this.descricaoligacaoAguaPerfil = descricaoligacaoAguaPerfil;
	}


	public String getDescricaoLigacaoEsgotoDiametro() {
		return descricaoLigacaoEsgotoDiametro;
	}


	public void setDescricaoLigacaoEsgotoDiametro(
			String descricaoLigacaoEsgotoDiametro) {
		this.descricaoLigacaoEsgotoDiametro = descricaoLigacaoEsgotoDiametro;
	}


	public String getDescricaoLigacaoEsgotoMaterial() {
		return descricaoLigacaoEsgotoMaterial;
	}


	public void setDescricaoLigacaoEsgotoMaterial(
			String descricaoLigacaoEsgotoMaterial) {
		this.descricaoLigacaoEsgotoMaterial = descricaoLigacaoEsgotoMaterial;
	}


	public String getDescricaoligacaoEsgotoPerfil() {
		return descricaoligacaoEsgotoPerfil;
	}


	public void setDescricaoligacaoEsgotoPerfil(String descricaoligacaoEsgotoPerfil) {
		this.descricaoligacaoEsgotoPerfil = descricaoligacaoEsgotoPerfil;
	}


	public String getDescricaoPocoTipo() {
		return descricaoPocoTipo;
	}


	public void setDescricaoPocoTipo(String descricaoPocoTipo) {
		this.descricaoPocoTipo = descricaoPocoTipo;
	}


	public String getDiametroHidrometro() {
		return diametroHidrometro;
	}


	public void setDiametroHidrometro(String diametroHidrometro) {
		this.diametroHidrometro = diametroHidrometro;
	}


	public String getDiasConsumo() {
		return diasConsumo;
	}


	public void setDiasConsumo(String diasConsumo) {
		this.diasConsumo = diasConsumo;
	}


	public String getDtLeituraAnterior() {
		return dtLeituraAnterior;
	}


	public void setDtLeituraAnterior(String dtLeituraAnterior) {
		this.dtLeituraAnterior = dtLeituraAnterior;
	}


	public String getDtLeituraFaturada() {
		return dtLeituraFaturada;
	}


	public void setDtLeituraFaturada(String dtLeituraFaturada) {
		this.dtLeituraFaturada = dtLeituraFaturada;
	}


	public String getDtLeituraInformada() {
		return dtLeituraInformada;
	}


	public void setDtLeituraInformada(String dtLeituraInformada) {
		this.dtLeituraInformada = dtLeituraInformada;
	}


	public String getEmpresaLeitura() {
		return empresaLeitura;
	}


	public void setEmpresaLeitura(String empresaLeitura) {
		this.empresaLeitura = empresaLeitura;
	}


	public String getEnderecoFormatado() {
		return enderecoFormatado;
	}


	public void setEnderecoFormatado(String enderecoFormatado) {
		this.enderecoFormatado = enderecoFormatado;
	}


	public String getGrupoFaturamento() {
		return grupoFaturamento;
	}


	public void setGrupoFaturamento(String grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}


	public String getHabilitaLupa() {
		return habilitaLupa;
	}


	public void setHabilitaLupa(String habilitaLupa) {
		this.habilitaLupa = habilitaLupa;
	}


	public String getIdAnormalidade() {
		return idAnormalidade;
	}


	public void setIdAnormalidade(String idAnormalidade) {
		this.idAnormalidade = idAnormalidade;
	}


	public String getIdEmpresa() {
		return idEmpresa;
	}


	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}


	public String getIdEmpresaFiltro() {
		return idEmpresaFiltro;
	}


	public void setIdEmpresaFiltro(String idEmpresaFiltro) {
		this.idEmpresaFiltro = idEmpresaFiltro;
	}


	public String getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}


	public void setIdGrupoFaturamento(String idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}


	public String getIdGrupoFaturamentoFiltro() {
		return idGrupoFaturamentoFiltro;
	}


	public void setIdGrupoFaturamentoFiltro(String idGrupoFaturamentoFiltro) {
		this.idGrupoFaturamentoFiltro = idGrupoFaturamentoFiltro;
	}


	public String getIdImovelAnaliseMedicaoConsumo() {
		return idImovelAnaliseMedicaoConsumo;
	}


	public void setIdImovelAnaliseMedicaoConsumo(
			String idImovelAnaliseMedicaoConsumo) {
		this.idImovelAnaliseMedicaoConsumo = idImovelAnaliseMedicaoConsumo;
	}


	public String getIdImovelSubstituirConsumo() {
		return idImovelSubstituirConsumo;
	}


	public void setIdImovelSubstituirConsumo(String idImovelSubstituirConsumo) {
		this.idImovelSubstituirConsumo = idImovelSubstituirConsumo;
	}


	public String getIdLigacaoAgua() {
		return idLigacaoAgua;
	}


	public void setIdLigacaoAgua(String idLigacaoAgua) {
		this.idLigacaoAgua = idLigacaoAgua;
	}


	public String getIdLigacaoAguaSituacao() {
		return idLigacaoAguaSituacao;
	}


	public void setIdLigacaoAguaSituacao(String idLigacaoAguaSituacao) {
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
	}


	public String getIdLigacaoEsgoto() {
		return idLigacaoEsgoto;
	}


	public void setIdLigacaoEsgoto(String idLigacaoEsgoto) {
		this.idLigacaoEsgoto = idLigacaoEsgoto;
	}


	public String getIdTipoMedicao() {
		return idTipoMedicao;
	}


	public void setIdTipoMedicao(String idTipoMedicao) {
		this.idTipoMedicao = idTipoMedicao;
	}


	public String getIdTipoMedicaoFiltro() {
		return idTipoMedicaoFiltro;
	}


	public void setIdTipoMedicaoFiltro(String idTipoMedicaoFiltro) {
		this.idTipoMedicaoFiltro = idTipoMedicaoFiltro;
	}


	public String getImovel() {
		return imovel;
	}


	public void setImovel(String imovel) {
		this.imovel = imovel;
	}


	public String getImovelCondominio() {
		return imovelCondominio;
	}


	public void setImovelCondominio(String imovelCondominio) {
		this.imovelCondominio = imovelCondominio;
	}


	public String getImovelCondominioFiltro() {
		return imovelCondominioFiltro;
	}


	public void setImovelCondominioFiltro(String imovelCondominioFiltro) {
		this.imovelCondominioFiltro = imovelCondominioFiltro;
	}


	public String getImovelFiltro() {
		return imovelFiltro;
	}


	public void setImovelFiltro(String imovelFiltro) {
		this.imovelFiltro = imovelFiltro;
	}


	public String getImovelMatriculaCondominioFiltro() {
		return imovelMatriculaCondominioFiltro;
	}


	public void setImovelMatriculaCondominioFiltro(
			String imovelMatriculaCondominioFiltro) {
		this.imovelMatriculaCondominioFiltro = imovelMatriculaCondominioFiltro;
	}


	public String getImovelMatriculaFiltro() {
		return imovelMatriculaFiltro;
	}


	public void setImovelMatriculaFiltro(String imovelMatriculaFiltro) {
		this.imovelMatriculaFiltro = imovelMatriculaFiltro;
	}


	public String getIndicadorCavalete() {
		return indicadorCavalete;
	}


	public void setIndicadorCavalete(String indicadorCavalete) {
		this.indicadorCavalete = indicadorCavalete;
	}


	public String getIndicadorImovelCondominio() {
		return indicadorImovelCondominio;
	}


	public void setIndicadorImovelCondominio(String indicadorImovelCondominio) {
		this.indicadorImovelCondominio = indicadorImovelCondominio;
	}


	public String getIndicadorImovelCondominioFiltro() {
		return indicadorImovelCondominioFiltro;
	}


	public void setIndicadorImovelCondominioFiltro(
			String indicadorImovelCondominioFiltro) {
		this.indicadorImovelCondominioFiltro = indicadorImovelCondominioFiltro;
	}


	public String getInscricaoImovel() {
		return inscricaoImovel;
	}


	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}


	public String getInscricaoTipo() {
		return inscricaoTipo;
	}


	public void setInscricaoTipo(String inscricaoTipo) {
		this.inscricaoTipo = inscricaoTipo;
	}


	public String getInstalacaoHidrometro() {
		return instalacaoHidrometro;
	}


	public void setInstalacaoHidrometro(String instalacaoHidrometro) {
		this.instalacaoHidrometro = instalacaoHidrometro;
	}


	public String getLeituraAnterior() {
		return leituraAnterior;
	}


	public void setLeituraAnterior(String leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}


	public String getLeituraAtualFaturada() {
		return leituraAtualFaturada;
	}


	public void setLeituraAtualFaturada(String leituraAtualFaturada) {
		this.leituraAtualFaturada = leituraAtualFaturada;
	}


	public String getLeituraAtualInformada() {
		return leituraAtualInformada;
	}


	public void setLeituraAtualInformada(String leituraAtualInformada) {
		this.leituraAtualInformada = leituraAtualInformada;
	}


	public String getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}


	public void setLigacaoAguaSituacao(String ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}


	public String getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}


	public void setLigacaoEsgotoSituacao(String ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}


	public String getLocalidade() {
		return localidade;
	}


	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}


	public String getLocalidadeFiltro() {
		return localidadeFiltro;
	}


	public void setLocalidadeFiltro(String localidadeFiltro) {
		this.localidadeFiltro = localidadeFiltro;
	}


	public String getLocalInstalacaoHidrometro() {
		return localInstalacaoHidrometro;
	}


	public void setLocalInstalacaoHidrometro(String localInstalacaoHidrometro) {
		this.localInstalacaoHidrometro = localInstalacaoHidrometro;
	}


	public String getMarcaHidrometro() {
		return marcaHidrometro;
	}


	public void setMarcaHidrometro(String marcaHidrometro) {
		this.marcaHidrometro = marcaHidrometro;
	}


	public String getMesAnoFaturamentoCorrente() {
		return mesAnoFaturamentoCorrente;
	}


	public void setMesAnoFaturamentoCorrente(String mesAnoFaturamentoCorrente) {
		this.mesAnoFaturamentoCorrente = mesAnoFaturamentoCorrente;
	}


	public String getNomeLocalidade() {
		return nomeLocalidade;
	}


	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}


	public String getNomeLocalidadeFiltro() {
		return nomeLocalidadeFiltro;
	}


	public void setNomeLocalidadeFiltro(String nomeLocalidadeFiltro) {
		this.nomeLocalidadeFiltro = nomeLocalidadeFiltro;
	}


	public String getNumeroConsumominimoAgua() {
		return numeroConsumominimoAgua;
	}


	public void setNumeroConsumominimoAgua(String numeroConsumominimoAgua) {
		this.numeroConsumominimoAgua = numeroConsumominimoAgua;
	}


	public String getNumeroConsumominimoEsgoto() {
		return numeroConsumominimoEsgoto;
	}


	public void setNumeroConsumominimoEsgoto(String numeroConsumominimoEsgoto) {
		this.numeroConsumominimoEsgoto = numeroConsumominimoEsgoto;
	}


	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}


	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}


	public String getPercentualAguaConsumidaColetada() {
		return percentualAguaConsumidaColetada;
	}


	public void setPercentualAguaConsumidaColetada(
			String percentualAguaConsumidaColetada) {
		this.percentualAguaConsumidaColetada = percentualAguaConsumidaColetada;
	}


	public String getPercentualEsgoto() {
		return percentualEsgoto;
	}


	public void setPercentualEsgoto(String percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}


	public String getPercentualVariacao() {
		return percentualVariacao;
	}


	public void setPercentualVariacao(String percentualVariacao) {
		this.percentualVariacao = percentualVariacao;
	}


	public String getPerfilImovel() {
		return perfilImovel;
	}


	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}


	public String getPerfilImovelFiltro() {
		return perfilImovelFiltro;
	}


	public void setPerfilImovelFiltro(String perfilImovelFiltro) {
		this.perfilImovelFiltro = perfilImovelFiltro;
	}


	public String getProtecaoHidrometro() {
		return protecaoHidrometro;
	}


	public void setProtecaoHidrometro(String protecaoHidrometro) {
		this.protecaoHidrometro = protecaoHidrometro;
	}


	public String getQuadraFinal() {
		return quadraFinal;
	}


	public void setQuadraFinal(String quadraFinal) {
		this.quadraFinal = quadraFinal;
	}


	public String getQuadraFinalFiltro() {
		return quadraFinalFiltro;
	}


	public void setQuadraFinalFiltro(String quadraFinalFiltro) {
		this.quadraFinalFiltro = quadraFinalFiltro;
	}


	public String getQuadraFinalID() {
		return quadraFinalID;
	}


	public void setQuadraFinalID(String quadraFinalID) {
		this.quadraFinalID = quadraFinalID;
	}


	public String getQuadraFinalMensagem() {
		return quadraFinalMensagem;
	}


	public void setQuadraFinalMensagem(String quadraFinalMensagem) {
		this.quadraFinalMensagem = quadraFinalMensagem;
	}


	public String getQuadraFinalNome() {
		return quadraFinalNome;
	}


	public void setQuadraFinalNome(String quadraFinalNome) {
		this.quadraFinalNome = quadraFinalNome;
	}


	public String getQuadraInicial() {
		return quadraInicial;
	}


	public void setQuadraInicial(String quadraInicial) {
		this.quadraInicial = quadraInicial;
	}


	public String getQuadraInicialFiltro() {
		return quadraInicialFiltro;
	}


	public void setQuadraInicialFiltro(String quadraInicialFiltro) {
		this.quadraInicialFiltro = quadraInicialFiltro;
	}


	public String getQuadraInicialID() {
		return quadraInicialID;
	}


	public void setQuadraInicialID(String quadraInicialID) {
		this.quadraInicialID = quadraInicialID;
	}


	public String getQuadraInicialMensagem() {
		return quadraInicialMensagem;
	}


	public void setQuadraInicialMensagem(String quadraInicialMensagem) {
		this.quadraInicialMensagem = quadraInicialMensagem;
	}


	public String getQuadraInicialNome() {
		return quadraInicialNome;
	}


	public void setQuadraInicialNome(String quadraInicialNome) {
		this.quadraInicialNome = quadraInicialNome;
	}


	public String getQuantidadeEconomia() {
		return quantidadeEconomia;
	}


	public void setQuantidadeEconomia(String quantidadeEconomia) {
		this.quantidadeEconomia = quantidadeEconomia;
	}


	public String getQuantidadeEconomiaFiltro() {
		return quantidadeEconomiaFiltro;
	}


	public void setQuantidadeEconomiaFiltro(String quantidadeEconomiaFiltro) {
		this.quantidadeEconomiaFiltro = quantidadeEconomiaFiltro;
	}


	public String getSetorComercial() {
		return setorComercial;
	}


	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}


	public String getSetorComercialFiltro() {
		return setorComercialFiltro;
	}


	public void setSetorComercialFiltro(String setorComercialFiltro) {
		this.setorComercialFiltro = setorComercialFiltro;
	}


	public String getSetorComercialID() {
		return setorComercialID;
	}


	public void setSetorComercialID(String setorComercialID) {
		this.setorComercialID = setorComercialID;
	}


	public String getSetorComercialNome() {
		return setorComercialNome;
	}


	public void setSetorComercialNome(String setorComercialNome) {
		this.setorComercialNome = setorComercialNome;
	}


	public String getSituacaoAguaAnaliseMedicaoConsumo() {
		return situacaoAguaAnaliseMedicaoConsumo;
	}


	public void setSituacaoAguaAnaliseMedicaoConsumo(
			String situacaoAguaAnaliseMedicaoConsumo) {
		this.situacaoAguaAnaliseMedicaoConsumo = situacaoAguaAnaliseMedicaoConsumo;
	}


	public String getSituacaoEsgotoAnaliseMedicaoConsumo() {
		return situacaoEsgotoAnaliseMedicaoConsumo;
	}


	public void setSituacaoEsgotoAnaliseMedicaoConsumo(
			String situacaoEsgotoAnaliseMedicaoConsumo) {
		this.situacaoEsgotoAnaliseMedicaoConsumo = situacaoEsgotoAnaliseMedicaoConsumo;
	}


	public String getSituacaoLeituraAtual() {
		return situacaoLeituraAtual;
	}


	public void setSituacaoLeituraAtual(String situacaoLeituraAtual) {
		this.situacaoLeituraAtual = situacaoLeituraAtual;
	}


	public String getTipoAnormalidade() {
		return tipoAnormalidade;
	}


	public void setTipoAnormalidade(String tipoAnormalidade) {
		this.tipoAnormalidade = tipoAnormalidade;
	}


	public String getTipoAnormalidadeFiltro() {
		return tipoAnormalidadeFiltro;
	}


	public void setTipoAnormalidadeFiltro(String tipoAnormalidadeFiltro) {
		this.tipoAnormalidadeFiltro = tipoAnormalidadeFiltro;
	}


	public String getTipoApresentacao() {
		return tipoApresentacao;
	}


	public void setTipoApresentacao(String tipoApresentacao) {
		this.tipoApresentacao = tipoApresentacao;
	}


	public String getTipoHidrometro() {
		return tipoHidrometro;
	}


	public void setTipoHidrometro(String tipoHidrometro) {
		this.tipoHidrometro = tipoHidrometro;
	}


	public String getTipoLigacao() {
		return tipoLigacao;
	}


	public void setTipoLigacao(String tipoLigacao) {
		this.tipoLigacao = tipoLigacao;
	}


	public String getTipoLigacaoFiltro() {
		return tipoLigacaoFiltro;
	}


	public void setTipoLigacaoFiltro(String tipoLigacaoFiltro) {
		this.tipoLigacaoFiltro = tipoLigacaoFiltro;
	}


	public String getTipoMedicao() {
		return tipoMedicao;
	}


	public void setTipoMedicao(String tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}


	public String getTipoMedicaoFiltro() {
		return tipoMedicaoFiltro;
	}


	public void setTipoMedicaoFiltro(String tipoMedicaoFiltro) {
		this.tipoMedicaoFiltro = tipoMedicaoFiltro;
	}


	public String getMatriculaImovelDadosCadastrais() {
		return matriculaImovelDadosCadastrais;
	}


	public void setMatriculaImovelDadosCadastrais(
			String matriculaImovelDadosCadastrais) {
		this.matriculaImovelDadosCadastrais = matriculaImovelDadosCadastrais;
	}


	public String getMatriculaImovelDadosComplementares() {
		return matriculaImovelDadosComplementares;
	}


	public void setMatriculaImovelDadosComplementares(
			String matriculaImovelDadosComplementares) {
		this.matriculaImovelDadosComplementares = matriculaImovelDadosComplementares;
	}


	public String getMatriculaImovelAnaliseMedicaoConsumo() {
		return matriculaImovelAnaliseMedicaoConsumo;
	}


	public void setMatriculaImovelAnaliseMedicaoConsumo(
			String matriculaImovelAnaliseMedicaoConsumo) {
		this.matriculaImovelAnaliseMedicaoConsumo = matriculaImovelAnaliseMedicaoConsumo;
	}


	public String getConta() {
		return conta;
	}


	public void setConta(String conta) {
		this.conta = conta;
	}


	public String getDescricaoImovel() {
		return descricaoImovel;
	}


	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}


	public String getIdImovelHistoricoFaturamento() {
		return idImovelHistoricoFaturamento;
	}


	public void setIdImovelHistoricoFaturamento(String idImovelHistoricoFaturamento) {
		this.idImovelHistoricoFaturamento = idImovelHistoricoFaturamento;
	}


	public String getMatriculaImovelHistoricoFaturamento() {
		return matriculaImovelHistoricoFaturamento;
	}


	public void setMatriculaImovelHistoricoFaturamento(
			String matriculaImovelHistoricoFaturamento) {
		this.matriculaImovelHistoricoFaturamento = matriculaImovelHistoricoFaturamento;
	}


	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}


	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}


	public String getSituacaoAguaHistoricoFaturamento() {
		return situacaoAguaHistoricoFaturamento;
	}


	public void setSituacaoAguaHistoricoFaturamento(
			String situacaoAguaHistoricoFaturamento) {
		this.situacaoAguaHistoricoFaturamento = situacaoAguaHistoricoFaturamento;
	}

	public String getSituacaoEsgotoHistoricoFaturamento() {
		return situacaoEsgotoHistoricoFaturamento;
	}


	public void setSituacaoEsgotoHistoricoFaturamento(
			String situacaoEsgotoHistoricoFaturamento) {
		this.situacaoEsgotoHistoricoFaturamento = situacaoEsgotoHistoricoFaturamento;
	}


	/**
	 * @return Retorna o campo cnpj.
	 */
	public String getCnpj() {
		return cnpj;
	}


	/**
	 * @param cnpj O cnpj a ser setado.
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	/**
	 * @return Retorna o campo codigoImovel.
	 */
	public String getCodigoImovel() {
		return codigoImovel;
	}


	/**
	 * @param codigoImovel O codigoImovel a ser setado.
	 */
	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}


	/**
	 * @return Retorna o campo cpf.
	 */
	public String getCpf() {
		return cpf;
	}


	/**
	 * @param cpf O cpf a ser setado.
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	/**
	 * @return Retorna o campo dataVencimentoFinal.
	 */
	public String getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}


	/**
	 * @param dataVencimentoFinal O dataVencimentoFinal a ser setado.
	 */
	public void setDataVencimentoFinal(String dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}


	/**
	 * @return Retorna o campo dataVencimentoInicial.
	 */
	public String getDataVencimentoInicial() {
		return dataVencimentoInicial;
	}


	/**
	 * @param dataVencimentoInicial O dataVencimentoInicial a ser setado.
	 */
	public void setDataVencimentoInicial(String dataVencimentoInicial) {
		this.dataVencimentoInicial = dataVencimentoInicial;
	}


	/**
	 * @return Retorna o campo dtFinal.
	 */
	public String getDtFinal() {
		return dtFinal;
	}


	/**
	 * @param dtFinal O dtFinal a ser setado.
	 */
	public void setDtFinal(String dtFinal) {
		this.dtFinal = dtFinal;
	}


	/**
	 * @return Retorna o campo dtInicial.
	 */
	public String getDtInicial() {
		return dtInicial;
	}


	/**
	 * @param dtInicial O dtInicial a ser setado.
	 */
	public void setDtInicial(String dtInicial) {
		this.dtInicial = dtInicial;
	}


	/**
	 * @return Retorna o campo inscricao.
	 */
	public String getInscricao() {
		return inscricao;
	}


	/**
	 * @param inscricao O inscricao a ser setado.
	 */
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}


	/**
	 * @return Retorna o campo ligacaoAgua.
	 */
	public String getLigacaoAgua() {
		return ligacaoAgua;
	}


	/**
	 * @param ligacaoAgua O ligacaoAgua a ser setado.
	 */
	public void setLigacaoAgua(String ligacaoAgua) {
		this.ligacaoAgua = ligacaoAgua;
	}


	/**
	 * @return Retorna o campo ligacaoEsgoto.
	 */
	public String getLigacaoEsgoto() {
		return ligacaoEsgoto;
	}


	/**
	 * @param ligacaoEsgoto O ligacaoEsgoto a ser setado.
	 */
	public void setLigacaoEsgoto(String ligacaoEsgoto) {
		this.ligacaoEsgoto = ligacaoEsgoto;
	}


	/**
	 * @return Retorna o campo maticula.
	 */
	public String getMaticula() {
		return maticula;
	}


	/**
	 * @param maticula O maticula a ser setado.
	 */
	public void setMaticula(String maticula) {
		this.maticula = maticula;
	}


	/**
	 * @return Retorna o campo nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}


	/**
	 * @param nomeCliente O nomeCliente a ser setado.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}


	/**
	 * @return Retorna o campo referenciaFinal.
	 */
	public String getReferenciaFinal() {
		return referenciaFinal;
	}


	/**
	 * @param referenciaFinal O referenciaFinal a ser setado.
	 */
	public void setReferenciaFinal(String referenciaFinal) {
		this.referenciaFinal = referenciaFinal;
	}


	/**
	 * @return Retorna o campo referenciaInicial.
	 */
	public String getReferenciaInicial() {
		return referenciaInicial;
	}


	/**
	 * @param referenciaInicial O referenciaInicial a ser setado.
	 */
	public void setReferenciaInicial(String referenciaInicial) {
		this.referenciaInicial = referenciaInicial;
	}


	/**
	 * @return Retorna o campo refFinal.
	 */
	public String getRefFinal() {
		return refFinal;
	}


	/**
	 * @param refFinal O refFinal a ser setado.
	 */
	public void setRefFinal(String refFinal) {
		this.refFinal = refFinal;
	}


	/**
	 * @return Retorna o campo refInicial.
	 */
	public String getRefInicial() {
		return refInicial;
	}


	/**
	 * @param refInicial O refInicial a ser setado.
	 */
	public void setRefInicial(String refInicial) {
		this.refInicial = refInicial;
	}


	/**
	 * @return Retorna o campo tipoRelacao.
	 */
	public String getTipoRelacao() {
		return tipoRelacao;
	}


	/**
	 * @param tipoRelacao O tipoRelacao a ser setado.
	 */
	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}


	/**
	 * @return Retorna o campo tipoRelacaoCliente.
	 */
	public String getTipoRelacaoCliente() {
		return tipoRelacaoCliente;
	}


	/**
	 * @param tipoRelacaoCliente O tipoRelacaoCliente a ser setado.
	 */
	public void setTipoRelacaoCliente(String tipoRelacaoCliente) {
		this.tipoRelacaoCliente = tipoRelacaoCliente;
	}

	/**
	 * @return Retorna o campo idImovelDebitos.
	 */
	public String getIdImovelDebitos() {
		return idImovelDebitos;
	}


	/**
	 * @param idImovelDebitos O idImovelDebitos a ser setado.
	 */
	public void setIdImovelDebitos(String idImovelDebitos) {
		this.idImovelDebitos = idImovelDebitos;
	}


	/**
	 * @return Retorna o campo matriculaImovelDebitos.
	 */
	public String getMatriculaImovelDebitos() {
		return matriculaImovelDebitos;
	}


	/**
	 * @param matriculaImovelDebitos O matriculaImovelDebitos a ser setado.
	 */
	public void setMatriculaImovelDebitos(String matriculaImovelDebitos) {
		this.matriculaImovelDebitos = matriculaImovelDebitos;
	}


	/**
	 * @return Retorna o campo situacaoEsgotoDebitos.
	 */
	public String getSituacaoEsgotoDebitos() {
		return situacaoEsgotoDebitos;
	}


	/**
	 * @param situacaoEsgotoDebitos O situacaoEsgotoDebitos a ser setado.
	 */
	public void setSituacaoEsgotoDebitos(String situacaoEsgotoDebitos) {
		this.situacaoEsgotoDebitos = situacaoEsgotoDebitos;
	}


	/**
	 * @return Retorna o campo situacaoaguaDebitos.
	 */
	public String getSituacaoAguaDebitos() {
		return situacaoAguaDebitos;
	}


	/**
	 * @param situacaoaguaDebitos O situacaoaguaDebitos a ser setado.
	 */
	public void setSituacaoAguaDebitos(String situacaoAguaDebitos) {
		this.situacaoAguaDebitos = situacaoAguaDebitos;
	}


	/**
	 * @return Retorna o campo idImovelPagamentos.
	 */
	public String getIdImovelPagamentos() {
		return idImovelPagamentos;
	}


	/**
	 * @param idImovelPagamentos O idImovelPagamentos a ser setado.
	 */
	public void setIdImovelPagamentos(String idImovelPagamentos) {
		this.idImovelPagamentos = idImovelPagamentos;
	}


	/**
	 * @return Retorna o campo matriculaImovelPagamentos.
	 */
	public String getMatriculaImovelPagamentos() {
		return matriculaImovelPagamentos;
	}


	/**
	 * @param matriculaImovelPagamentos O matriculaImovelPagamentos a ser setado.
	 */
	public void setMatriculaImovelPagamentos(String matriculaImovelPagamentos) {
		this.matriculaImovelPagamentos = matriculaImovelPagamentos;
	}


	/**
	 * @return Retorna o campo situacaoAguaPagamentos.
	 */
	public String getSituacaoAguaPagamentos() {
		return situacaoAguaPagamentos;
	}


	/**
	 * @param situacaoAguaPagamentos O situacaoAguaPagamentos a ser setado.
	 */
	public void setSituacaoAguaPagamentos(String situacaoAguaPagamentos) {
		this.situacaoAguaPagamentos = situacaoAguaPagamentos;
	}


	/**
	 * @return Retorna o campo situacaoEsgotoPagamentos.
	 */
	public String getSituacaoEsgotoPagamentos() {
		return situacaoEsgotoPagamentos;
	}


	/**
	 * @param situacaoEsgotoPagamentos O situacaoEsgotoPagamentos a ser setado.
	 */
	public void setSituacaoEsgotoPagamentos(String situacaoEsgotoPagamentos) {
		this.situacaoEsgotoPagamentos = situacaoEsgotoPagamentos;
	}
	
	/**
	 * @return Retorna o campo arrecadador.
	 */
	public String getArrecadador() {
		return arrecadador;
	}

	/**
	 * @param arrecadador O arrecadador a ser setado.
	 */
	public void setArrecadador(String arrecadador) {
		this.arrecadador = arrecadador;
	}
	


	/**
	 * @return Retorna o campo idImovelDocumentosCobranca.
	 */
	public String getIdImovelDocumentosCobranca() {
		return idImovelDocumentosCobranca;
	}


	/**
	 * @param idImovelDocumentosCobranca O idImovelDocumentosCobranca a ser setado.
	 */
	public void setIdImovelDocumentosCobranca(String idImovelDocumentosCobranca) {
		this.idImovelDocumentosCobranca = idImovelDocumentosCobranca;
	}


	/**
	 * @return Retorna o campo matriculaImovelDocumentosCobranca.
	 */
	public String getMatriculaImovelDocumentosCobranca() {
		return matriculaImovelDocumentosCobranca;
	}


	/**
	 * @param matriculaImovelDocumentosCobranca O matriculaImovelDocumentosCobranca a ser setado.
	 */
	public void setMatriculaImovelDocumentosCobranca(
			String matriculaImovelDocumentosCobranca) {
		this.matriculaImovelDocumentosCobranca = matriculaImovelDocumentosCobranca;
	}


	/**
	 * @return Retorna o campo situacaoAguaDocumentosCobranca.
	 */
	public String getSituacaoAguaDocumentosCobranca() {
		return situacaoAguaDocumentosCobranca;
	}


	/**
	 * @param situacaoAguaDocumentosCobranca O situacaoAguaDocumentosCobranca a ser setado.
	 */
	public void setSituacaoAguaDocumentosCobranca(
			String situacaoAguaDocumentosCobranca) {
		this.situacaoAguaDocumentosCobranca = situacaoAguaDocumentosCobranca;
	}


	/**
	 * @return Retorna o campo situacaoEsgotoDocumentosCobranca.
	 */
	public String getSituacaoEsgotoDocumentosCobranca() {
		return situacaoEsgotoDocumentosCobranca;
	}


	/**
	 * @param situacaoEsgotoDocumentosCobranca O situacaoEsgotoDocumentosCobranca a ser setado.
	 */
	public void setSituacaoEsgotoDocumentosCobranca(
			String situacaoEsgotoDocumentosCobranca) {
		this.situacaoEsgotoDocumentosCobranca = situacaoEsgotoDocumentosCobranca;
	}


	/**
	 * @return Retorna o campo idImovelParcelamentosDebitos.
	 */
	public String getIdImovelParcelamentosDebitos() {
		return idImovelParcelamentosDebitos;
	}


	/**
	 * @param idImovelParcelamentosDebitos O idImovelParcelamentosDebitos a ser setado.
	 */
	public void setIdImovelParcelamentosDebitos(String idImovelParcelamentosDebitos) {
		this.idImovelParcelamentosDebitos = idImovelParcelamentosDebitos;
	}


	/**
	 * @return Retorna o campo matriculaImovelParcelamentosDebitos.
	 */
	public String getMatriculaImovelParcelamentosDebitos() {
		return matriculaImovelParcelamentosDebitos;
	}


	/**
	 * @param matriculaImovelParcelamentosDebitos O matriculaImovelParcelamentosDebitos a ser setado.
	 */
	public void setMatriculaImovelParcelamentosDebitos(
			String matriculaImovelParcelamentosDebitos) {
		this.matriculaImovelParcelamentosDebitos = matriculaImovelParcelamentosDebitos;
	}


	/**
	 * @return Retorna o campo situacaoAguaParcelamentosDebitos.
	 */
	public String getSituacaoAguaParcelamentosDebitos() {
		return situacaoAguaParcelamentosDebitos;
	}


	/**
	 * @param situacaoAguaParcelamentosDebitos O situacaoAguaParcelamentosDebitos a ser setado.
	 */
	public void setSituacaoAguaParcelamentosDebitos(
			String situacaoAguaParcelamentosDebitos) {
		this.situacaoAguaParcelamentosDebitos = situacaoAguaParcelamentosDebitos;
	}


	/**
	 * @return Retorna o campo situacaoEsgotoParcelamentosDebitos.
	 */
	public String getSituacaoEsgotoParcelamentosDebitos() {
		return situacaoEsgotoParcelamentosDebitos;
	}


	/**
	 * @param situacaoEsgotoParcelamentosDebitos O situacaoEsgotoParcelamentosDebitos a ser setado.
	 */
	public void setSituacaoEsgotoParcelamentosDebitos(
			String situacaoEsgotoParcelamentosDebitos) {
		this.situacaoEsgotoParcelamentosDebitos = situacaoEsgotoParcelamentosDebitos;
	}


	/**
	 * @return Retorna o campo parcelamento.
	 */
	public String getParcelamento() {
		return parcelamento;
	}


	/**
	 * @param parcelamento O parcelamento a ser setado.
	 */
	public void setParcelamento(String parcelamento) {
		this.parcelamento = parcelamento;
	}


	/**
	 * @return Retorna o campo reparcelamento.
	 */
	public String getReparcelamento() {
		return reparcelamento;
	}


	/**
	 * @param reparcelamento O reparcelamento a ser setado.
	 */
	public void setReparcelamento(String reparcelamento) {
		this.reparcelamento = reparcelamento;
	}


	/**
	 * @return Retorna o campo reparcelamentoConsecutivo.
	 */
	public String getReparcelamentoConsecutivo() {
		return reparcelamentoConsecutivo;
	}


	/**
	 * @param reparcelamentoConsecutivo O reparcelamentoConsecutivo a ser setado.
	 */
	public void setReparcelamentoConsecutivo(String reparcelamentoConsecutivo) {
		this.reparcelamentoConsecutivo = reparcelamentoConsecutivo;
	}


	/**
	 * @return Retorna o campo idImovelDevolucoesImovel.
	 */
	public String getIdImovelDevolucoesImovel() {
		return idImovelDevolucoesImovel;
	}


	/**
	 * @param idImovelDevolucoesImovel O idImovelDevolucoesImovel a ser setado.
	 */
	public void setIdImovelDevolucoesImovel(String idImovelDevolucoesImovel) {
		this.idImovelDevolucoesImovel = idImovelDevolucoesImovel;
	}


	/**
	 * @return Retorna o campo idImovelRegistroAtendimento.
	 */
	public String getIdImovelRegistroAtendimento() {
		return idImovelRegistroAtendimento;
	}


	/**
	 * @param idImovelRegistroAtendimento O idImovelRegistroAtendimento a ser setado.
	 */
	public void setIdImovelRegistroAtendimento(String idImovelRegistroAtendimento) {
		this.idImovelRegistroAtendimento = idImovelRegistroAtendimento;
	}


	/**
	 * @return Retorna o campo matriculaImovelDevolucoesImovel.
	 */
	public String getMatriculaImovelDevolucoesImovel() {
		return matriculaImovelDevolucoesImovel;
	}


	/**
	 * @param matriculaImovelDevolucoesImovel O matriculaImovelDevolucoesImovel a ser setado.
	 */
	public void setMatriculaImovelDevolucoesImovel(
			String matriculaImovelDevolucoesImovel) {
		this.matriculaImovelDevolucoesImovel = matriculaImovelDevolucoesImovel;
	}


	/**
	 * @return Retorna o campo matriculaImovelRegistroAtendimento.
	 */
	public String getMatriculaImovelRegistroAtendimento() {
		return matriculaImovelRegistroAtendimento;
	}


	/**
	 * @param matriculaImovelRegistroAtendimento O matriculaImovelRegistroAtendimento a ser setado.
	 */
	public void setMatriculaImovelRegistroAtendimento(
			String matriculaImovelRegistroAtendimento) {
		this.matriculaImovelRegistroAtendimento = matriculaImovelRegistroAtendimento;
	}


	/**
	 * @return Retorna o campo situacaoAguaDevolucoesImovel.
	 */
	public String getSituacaoAguaDevolucoesImovel() {
		return situacaoAguaDevolucoesImovel;
	}


	/**
	 * @param situacaoAguaDevolucoesImovel O situacaoAguaDevolucoesImovel a ser setado.
	 */
	public void setSituacaoAguaDevolucoesImovel(String situacaoAguaDevolucoesImovel) {
		this.situacaoAguaDevolucoesImovel = situacaoAguaDevolucoesImovel;
	}


	/**
	 * @return Retorna o campo situacaoAguaRegistroAtendimento.
	 */
	public String getSituacaoAguaRegistroAtendimento() {
		return situacaoAguaRegistroAtendimento;
	}


	/**
	 * @param situacaoAguaRegistroAtendimento O situacaoAguaRegistroAtendimento a ser setado.
	 */
	public void setSituacaoAguaRegistroAtendimento(
			String situacaoAguaRegistroAtendimento) {
		this.situacaoAguaRegistroAtendimento = situacaoAguaRegistroAtendimento;
	}


	/**
	 * @return Retorna o campo situacaoEsgotoDevolucoesImovel.
	 */
	public String getSituacaoEsgotoDevolucoesImovel() {
		return situacaoEsgotoDevolucoesImovel;
	}


	/**
	 * @param situacaoEsgotoDevolucoesImovel O situacaoEsgotoDevolucoesImovel a ser setado.
	 */
	public void setSituacaoEsgotoDevolucoesImovel(
			String situacaoEsgotoDevolucoesImovel) {
		this.situacaoEsgotoDevolucoesImovel = situacaoEsgotoDevolucoesImovel;
	}


	/**
	 * @return Retorna o campo situacaoEsgotoRegistroAtendimento.
	 */
	public String getSituacaoEsgotoRegistroAtendimento() {
		return situacaoEsgotoRegistroAtendimento;
	}


	/**
	 * @param situacaoEsgotoRegistroAtendimento O situacaoEsgotoRegistroAtendimento a ser setado.
	 */
	public void setSituacaoEsgotoRegistroAtendimento(
			String situacaoEsgotoRegistroAtendimento) {
		this.situacaoEsgotoRegistroAtendimento = situacaoEsgotoRegistroAtendimento;
	}


	/**
	 * @return Retorna o campo jardimDadosCadastrais.
	 */
	public String getJardimDadosCadastrais() {
		return jardimDadosCadastrais;
	}


	/**
	 * @param jardimDadosCadastrais O jardimDadosCadastrais a ser setado.
	 */
	public void setJardimDadosCadastrais(String jardimDadosCadastrais) {
		this.jardimDadosCadastrais = jardimDadosCadastrais;
	}


	/**
	 * @return Retorna o campo cpfCnpj.
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}


	/**
	 * @param cpfCnpj O cpfCnpj a ser setado.
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}


	/**
	 * @return Retorna o campo dataLancamento.
	 */
	public String getDataLancamento() {
		return dataLancamento;
	}


	/**
	 * @param dataLancamento O dataLancamento a ser setado.
	 */
	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}


	/**
	 * @return Retorna o campo descricaoArrecadador.
	 */
	public String getDescricaoArrecadador() {
		return descricaoArrecadador;
	}


	/**
	 * @param descricaoArrecadador O descricaoArrecadador a ser setado.
	 */
	public void setDescricaoArrecadador(String descricaoArrecadador) {
		this.descricaoArrecadador = descricaoArrecadador;
	}


	/**
	 * @return Retorna o campo idArrecadador.
	 */
	public String getIdArrecadador() {
		return idArrecadador;
	}


	/**
	 * @param idArrecadador O idArrecadador a ser setado.
	 */
	public void setIdArrecadador(String idArrecadador) {
		this.idArrecadador = idArrecadador;
	}


	/**
	 * @return Retorna o campo idCliente.
	 */
	public String getIdCliente() {
		return idCliente;
	}


	/**
	 * @param idCliente O idCliente a ser setado.
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}


	/**
	 * @return Retorna o campo idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}


	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}


	/**
	 * @return Retorna o campo profissao.
	 */
	public String getProfissao() {
		return profissao;
	}


	/**
	 * @param profissao O profissao a ser setado.
	 */
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}


	/**
	 * @return Retorna o campo ramoAtividade.
	 */
	public String getRamoAtividade() {
		return ramoAtividade;
	}


	/**
	 * @param ramoAtividade O ramoAtividade a ser setado.
	 */
	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}


	/**
	 * @return Retorna o campo sequencialAviso.
	 */
	public String getSequencialAviso() {
		return sequencialAviso;
	}


	/**
	 * @param sequencialAviso O sequencialAviso a ser setado.
	 */
	public void setSequencialAviso(String sequencialAviso) {
		this.sequencialAviso = sequencialAviso;
	}


	/**
	 * @return Retorna o campo telefone.
	 */
	public String getTelefone() {
		return telefone;
	}


	/**
	 * @param telefone O telefone a ser setado.
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	/**
	 * @return Retorna o campo tipoAviso.
	 */
	public String getTipoAviso() {
		return tipoAviso;
	}


	/**
	 * @param tipoAviso O tipoAviso a ser setado.
	 */
	public void setTipoAviso(String tipoAviso) {
		this.tipoAviso = tipoAviso;
	}


	/**
	 * @return Retorna o campo anoFabricacaoPoco.
	 */
	public String getAnoFabricacaoPoco() {
		return anoFabricacaoPoco;
	}


	/**
	 * @param anoFabricacaoPoco O anoFabricacaoPoco a ser setado.
	 */
	public void setAnoFabricacaoPoco(String anoFabricacaoPoco) {
		this.anoFabricacaoPoco = anoFabricacaoPoco;
	}


	/**
	 * @return Retorna o campo capacidadeHidrometroPoco.
	 */
	public String getCapacidadeHidrometroPoco() {
		return capacidadeHidrometroPoco;
	}


	/**
	 * @param capacidadeHidrometroPoco O capacidadeHidrometroPoco a ser setado.
	 */
	public void setCapacidadeHidrometroPoco(String capacidadeHidrometroPoco) {
		this.capacidadeHidrometroPoco = capacidadeHidrometroPoco;
	}


	/**
	 * @return Retorna o campo diametroHidrometroPoco.
	 */
	public String getDiametroHidrometroPoco() {
		return diametroHidrometroPoco;
	}


	/**
	 * @param diametroHidrometroPoco O diametroHidrometroPoco a ser setado.
	 */
	public void setDiametroHidrometroPoco(String diametroHidrometroPoco) {
		this.diametroHidrometroPoco = diametroHidrometroPoco;
	}


	/**
	 * @return Retorna o campo indicadorCavaletePoco.
	 */
	public String getIndicadorCavaletePoco() {
		return indicadorCavaletePoco;
	}


	/**
	 * @param indicadorCavaletePoco O indicadorCavaletePoco a ser setado.
	 */
	public void setIndicadorCavaletePoco(String indicadorCavaletePoco) {
		this.indicadorCavaletePoco = indicadorCavaletePoco;
	}


	/**
	 * @return Retorna o campo instalacaoHidrometroPoco.
	 */
	public String getInstalacaoHidrometroPoco() {
		return instalacaoHidrometroPoco;
	}


	/**
	 * @param instalacaoHidrometroPoco O instalacaoHidrometroPoco a ser setado.
	 */
	public void setInstalacaoHidrometroPoco(String instalacaoHidrometroPoco) {
		this.instalacaoHidrometroPoco = instalacaoHidrometroPoco;
	}


	/**
	 * @return Retorna o campo localInstalacaoHidrometroPoco.
	 */
	public String getLocalInstalacaoHidrometroPoco() {
		return localInstalacaoHidrometroPoco;
	}


	/**
	 * @param localInstalacaoHidrometroPoco O localInstalacaoHidrometroPoco a ser setado.
	 */
	public void setLocalInstalacaoHidrometroPoco(
			String localInstalacaoHidrometroPoco) {
		this.localInstalacaoHidrometroPoco = localInstalacaoHidrometroPoco;
	}


	/**
	 * @return Retorna o campo marcaHidrometroPoco.
	 */
	public String getMarcaHidrometroPoco() {
		return marcaHidrometroPoco;
	}


	/**
	 * @param marcaHidrometroPoco O marcaHidrometroPoco a ser setado.
	 */
	public void setMarcaHidrometroPoco(String marcaHidrometroPoco) {
		this.marcaHidrometroPoco = marcaHidrometroPoco;
	}


	/**
	 * @return Retorna o campo numeroHidrometroPoco.
	 */
	public String getNumeroHidrometroPoco() {
		return numeroHidrometroPoco;
	}


	/**
	 * @param numeroHidrometroPoco O numeroHidrometroPoco a ser setado.
	 */
	public void setNumeroHidrometroPoco(String numeroHidrometroPoco) {
		this.numeroHidrometroPoco = numeroHidrometroPoco;
	}


	/**
	 * @return Retorna o campo protecaoHidrometroPoco.
	 */
	public String getProtecaoHidrometroPoco() {
		return protecaoHidrometroPoco;
	}


	/**
	 * @param protecaoHidrometroPoco O protecaoHidrometroPoco a ser setado.
	 */
	public void setProtecaoHidrometroPoco(String protecaoHidrometroPoco) {
		this.protecaoHidrometroPoco = protecaoHidrometroPoco;
	}


	/**
	 * @return Retorna o campo tipoHidrometroPoco.
	 */
	public String getTipoHidrometroPoco() {
		return tipoHidrometroPoco;
	}


	/**
	 * @param tipoHidrometroPoco O tipoHidrometroPoco a ser setado.
	 */
	public void setTipoHidrometroPoco(String tipoHidrometroPoco) {
		this.tipoHidrometroPoco = tipoHidrometroPoco;
	}


	/**
	 * @return Retorna o campo tipoMedicaoPoco.
	 */
	public String getTipoMedicaoPoco() {
		return tipoMedicaoPoco;
	}


	/**
	 * @param tipoMedicaoPoco O tipoMedicaoPoco a ser setado.
	 */
	public void setTipoMedicaoPoco(String tipoMedicaoPoco) {
		this.tipoMedicaoPoco = tipoMedicaoPoco;
	}


	/**
	 * @return Retorna o campo anormalidadeLeituraFaturadaPoco.
	 */
	public String getAnormalidadeLeituraFaturadaPoco() {
		return anormalidadeLeituraFaturadaPoco;
	}


	/**
	 * @param anormalidadeLeituraFaturadaPoco O anormalidadeLeituraFaturadaPoco a ser setado.
	 */
	public void setAnormalidadeLeituraFaturadaPoco(
			String anormalidadeLeituraFaturadaPoco) {
		this.anormalidadeLeituraFaturadaPoco = anormalidadeLeituraFaturadaPoco;
	}


	/**
	 * @return Retorna o campo anormalidadeLeituraInformadaPoco.
	 */
	public String getAnormalidadeLeituraInformadaPoco() {
		return anormalidadeLeituraInformadaPoco;
	}


	/**
	 * @param anormalidadeLeituraInformadaPoco O anormalidadeLeituraInformadaPoco a ser setado.
	 */
	public void setAnormalidadeLeituraInformadaPoco(
			String anormalidadeLeituraInformadaPoco) {
		this.anormalidadeLeituraInformadaPoco = anormalidadeLeituraInformadaPoco;
	}


	/**
	 * @return Retorna o campo codigoFuncionarioPoco.
	 */
	public String getCodigoFuncionarioPoco() {
		return codigoFuncionarioPoco;
	}


	/**
	 * @param codigoFuncionarioPoco O codigoFuncionarioPoco a ser setado.
	 */
	public void setCodigoFuncionarioPoco(String codigoFuncionarioPoco) {
		this.codigoFuncionarioPoco = codigoFuncionarioPoco;
	}


	/**
	 * @return Retorna o campo consumoMedioHidrometroPoco.
	 */
	public String getConsumoMedioHidrometroPoco() {
		return consumoMedioHidrometroPoco;
	}


	/**
	 * @param consumoMedioHidrometroPoco O consumoMedioHidrometroPoco a ser setado.
	 */
	public void setConsumoMedioHidrometroPoco(String consumoMedioHidrometroPoco) {
		this.consumoMedioHidrometroPoco = consumoMedioHidrometroPoco;
	}


	/**
	 * @return Retorna o campo dtLeituraAnteriorPoco.
	 */
	public String getDtLeituraAnteriorPoco() {
		return dtLeituraAnteriorPoco;
	}


	/**
	 * @param dtLeituraAnteriorPoco O dtLeituraAnteriorPoco a ser setado.
	 */
	public void setDtLeituraAnteriorPoco(String dtLeituraAnteriorPoco) {
		this.dtLeituraAnteriorPoco = dtLeituraAnteriorPoco;
	}


	/**
	 * @return Retorna o campo dtLeituraFaturadaPoco.
	 */
	public String getDtLeituraFaturadaPoco() {
		return dtLeituraFaturadaPoco;
	}


	/**
	 * @param dtLeituraFaturadaPoco O dtLeituraFaturadaPoco a ser setado.
	 */
	public void setDtLeituraFaturadaPoco(String dtLeituraFaturadaPoco) {
		this.dtLeituraFaturadaPoco = dtLeituraFaturadaPoco;
	}


	/**
	 * @return Retorna o campo dtLeituraInformadaPoco.
	 */
	public String getDtLeituraInformadaPoco() {
		return dtLeituraInformadaPoco;
	}


	/**
	 * @param dtLeituraInformadaPoco O dtLeituraInformadaPoco a ser setado.
	 */
	public void setDtLeituraInformadaPoco(String dtLeituraInformadaPoco) {
		this.dtLeituraInformadaPoco = dtLeituraInformadaPoco;
	}


	/**
	 * @return Retorna o campo leituraAnteriorPoco.
	 */
	public String getLeituraAnteriorPoco() {
		return leituraAnteriorPoco;
	}


	/**
	 * @param leituraAnteriorPoco O leituraAnteriorPoco a ser setado.
	 */
	public void setLeituraAnteriorPoco(String leituraAnteriorPoco) {
		this.leituraAnteriorPoco = leituraAnteriorPoco;
	}


	/**
	 * @return Retorna o campo leituraAtualFaturadaPoco.
	 */
	public String getLeituraAtualFaturadaPoco() {
		return leituraAtualFaturadaPoco;
	}


	/**
	 * @param leituraAtualFaturadaPoco O leituraAtualFaturadaPoco a ser setado.
	 */
	public void setLeituraAtualFaturadaPoco(String leituraAtualFaturadaPoco) {
		this.leituraAtualFaturadaPoco = leituraAtualFaturadaPoco;
	}


	/**
	 * @return Retorna o campo leituraAtualInformadaPoco.
	 */
	public String getLeituraAtualInformadaPoco() {
		return leituraAtualInformadaPoco;
	}


	/**
	 * @param leituraAtualInformadaPoco O leituraAtualInformadaPoco a ser setado.
	 */
	public void setLeituraAtualInformadaPoco(String leituraAtualInformadaPoco) {
		this.leituraAtualInformadaPoco = leituraAtualInformadaPoco;
	}


	/**
	 * @return Retorna o campo situacaoLeituraAtualPoco.
	 */
	public String getSituacaoLeituraAtualPoco() {
		return situacaoLeituraAtualPoco;
	}


	/**
	 * @param situacaoLeituraAtualPoco O situacaoLeituraAtualPoco a ser setado.
	 */
	public void setSituacaoLeituraAtualPoco(String situacaoLeituraAtualPoco) {
		this.situacaoLeituraAtualPoco = situacaoLeituraAtualPoco;
	}


	/**
	 * @return Retorna o campo anormalidadeConsumoEsgoto.
	 */
	public String getAnormalidadeConsumoEsgoto() {
		return anormalidadeConsumoEsgoto;
	}


	/**
	 * @param anormalidadeConsumoEsgoto O anormalidadeConsumoEsgoto a ser setado.
	 */
	public void setAnormalidadeConsumoEsgoto(String anormalidadeConsumoEsgoto) {
		this.anormalidadeConsumoEsgoto = anormalidadeConsumoEsgoto;
	}


	/**
	 * @return Retorna o campo consumoFaturadoEsgoto.
	 */
	public String getConsumoFaturadoEsgoto() {
		return consumoFaturadoEsgoto;
	}


	/**
	 * @param consumoFaturadoEsgoto O consumoFaturadoEsgoto a ser setado.
	 */
	public void setConsumoFaturadoEsgoto(String consumoFaturadoEsgoto) {
		this.consumoFaturadoEsgoto = consumoFaturadoEsgoto;
	}


	/**
	 * @return Retorna o campo consumoMedidoEsgoto.
	 */
	public String getConsumoMedidoEsgoto() {
		return consumoMedidoEsgoto;
	}


	/**
	 * @param consumoMedidoEsgoto O consumoMedidoEsgoto a ser setado.
	 */
	public void setConsumoMedidoEsgoto(String consumoMedidoEsgoto) {
		this.consumoMedidoEsgoto = consumoMedidoEsgoto;
	}


	/**
	 * @return Retorna o campo consumoMedioImovelEsgoto.
	 */
	public String getConsumoMedioImovelEsgoto() {
		return consumoMedioImovelEsgoto;
	}


	/**
	 * @param consumoMedioImovelEsgoto O consumoMedioImovelEsgoto a ser setado.
	 */
	public void setConsumoMedioImovelEsgoto(String consumoMedioImovelEsgoto) {
		this.consumoMedioImovelEsgoto = consumoMedioImovelEsgoto;
	}


	/**
	 * @return Retorna o campo consumoRateioEsgoto.
	 */
	public String getConsumoRateioEsgoto() {
		return consumoRateioEsgoto;
	}


	/**
	 * @param consumoRateioEsgoto O consumoRateioEsgoto a ser setado.
	 */
	public void setConsumoRateioEsgoto(String consumoRateioEsgoto) {
		this.consumoRateioEsgoto = consumoRateioEsgoto;
	}


	/**
	 * @return Retorna o campo consumoTipoEsgoto.
	 */
	public String getConsumoTipoEsgoto() {
		return consumoTipoEsgoto;
	}


	/**
	 * @param consumoTipoEsgoto O consumoTipoEsgoto a ser setado.
	 */
	public void setConsumoTipoEsgoto(String consumoTipoEsgoto) {
		this.consumoTipoEsgoto = consumoTipoEsgoto;
	}


	/**
	 * @return Retorna o campo diasConsumoEsgoto.
	 */
	public String getDiasConsumoEsgoto() {
		return diasConsumoEsgoto;
	}


	/**
	 * @param diasConsumoEsgoto O diasConsumoEsgoto a ser setado.
	 */
	public void setDiasConsumoEsgoto(String diasConsumoEsgoto) {
		this.diasConsumoEsgoto = diasConsumoEsgoto;
	}


	/**
	 * @return Retorna o campo percentualVariacaoEsgoto.
	 */
	public String getPercentualVariacaoEsgoto() {
		return percentualVariacaoEsgoto;
	}


	/**
	 * @param percentualVariacaoEsgoto O percentualVariacaoEsgoto a ser setado.
	 */
	public void setPercentualVariacaoEsgoto(String percentualVariacaoEsgoto) {
		this.percentualVariacaoEsgoto = percentualVariacaoEsgoto;
	}


	/**
	 * @return Retorna o campo diaVencimento.
	 */
	public String getDiaVencimento() {
		return diaVencimento;
	}


	/**
	 * @param diaVencimento O diaVencimento a ser setado.
	 */
	public void setDiaVencimento(String diaVencimento) {
		this.diaVencimento = diaVencimento;
	}


	/**
	 * @return Retorna o campo rota.
	 */
	public String getRota() {
		return rota;
	}


	/**
	 * @param rota O rota a ser setado.
	 */
	public void setRota(String rota) {
		this.rota = rota;
	}


	/**
	 * @return Retorna o campo sequencialRota.
	 */
	public String getSequencialRota() {
		return sequencialRota;
	}


	/**
	 * @param sequencialRota O sequencialRota a ser setado.
	 */
	public void setSequencialRota(String sequencialRota) {
		this.sequencialRota = sequencialRota;
	}


	/**
	 * @return Retorna o campo divisaoEsgotoDadosCadastrais.
	 */
	public String getDivisaoEsgotoDadosCadastrais() {
		return divisaoEsgotoDadosCadastrais;
	}


	/**
	 * @param divisaoEsgotoDadosCadastrais O divisaoEsgotoDadosCadastrais a ser setado.
	 */
	public void setDivisaoEsgotoDadosCadastrais(String divisaoEsgotoDadosCadastrais) {
		this.divisaoEsgotoDadosCadastrais = divisaoEsgotoDadosCadastrais;
	}


	public String getEnderecoAnaliseMedicaoConsumo() {
		return enderecoAnaliseMedicaoConsumo;
	}


	public void setEnderecoAnaliseMedicaoConsumo(
			String enderecoAnaliseMedicaoConsumo) {
		this.enderecoAnaliseMedicaoConsumo = enderecoAnaliseMedicaoConsumo;
	}


	public String getIdFuncionario() {
		return idFuncionario;
	}


	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}


	public String getNomeFuncionario() {
		return nomeFuncionario;
	}


	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}


	public String getTipoCoberturaDadosCadastrais() {
		return tipoCoberturaDadosCadastrais;
	}


	public void setTipoCoberturaDadosCadastrais(String tipoCoberturaDadosCadastrais) {
		this.tipoCoberturaDadosCadastrais = tipoCoberturaDadosCadastrais;
	}


	public String getTipoConstrucaoDadosCadastrais() {
		return tipoConstrucaoDadosCadastrais;
	}


	public void setTipoConstrucaoDadosCadastrais(
			String tipoConstrucaoDadosCadastrais) {
		this.tipoConstrucaoDadosCadastrais = tipoConstrucaoDadosCadastrais;
	}


	public String getTipoHabitacaoDadosCadastrais() {
		return tipoHabitacaoDadosCadastrais;
	}


	public void setTipoHabitacaoDadosCadastrais(String tipoHabitacaoDadosCadastrais) {
		this.tipoHabitacaoDadosCadastrais = tipoHabitacaoDadosCadastrais;
	}


	public String getTipoPropriedadeDadosCadastrais() {
		return tipoPropriedadeDadosCadastrais;
	}


	public void setTipoPropriedadeDadosCadastrais(
			String tipoPropriedadeDadosCadastrais) {
		this.tipoPropriedadeDadosCadastrais = tipoPropriedadeDadosCadastrais;
	}


	/**
	 * @return Retorna o campo condicaoEsgotamento.
	 */
	public String getCondicaoEsgotamento() {
		return condicaoEsgotamento;
	}


	/**
	 * @param condicaoEsgotamento O condicaoEsgotamento a ser setado.
	 */
	public void setCondicaoEsgotamento(String condicaoEsgotamento) {
		this.condicaoEsgotamento = condicaoEsgotamento;
	}


	/**
	 * @return Retorna o campo destinoAguasPluviais.
	 */
	public String getDestinoAguasPluviais() {
		return destinoAguasPluviais;
	}


	/**
	 * @param destinoAguasPluviais O destinoAguasPluviais a ser setado.
	 */
	public void setDestinoAguasPluviais(String destinoAguasPluviais) {
		this.destinoAguasPluviais = destinoAguasPluviais;
	}


	/**
	 * @return Retorna o campo destinoDejetos.
	 */
	public String getDestinoDejetos() {
		return destinoDejetos;
	}


	/**
	 * @param destinoDejetos O destinoDejetos a ser setado.
	 */
	public void setDestinoDejetos(String destinoDejetos) {
		this.destinoDejetos = destinoDejetos;
	}


	/**
	 * @return Retorna o campo numeroLacreInstalacao.
	 */
	public String getNumeroLacreInstalacao() {
		return numeroLacreInstalacao;
	}


	/**
	 * @param numeroLacreInstalacao O numeroLacreInstalacao a ser setado.
	 */
	public void setNumeroLacreInstalacao(String numeroLacreInstalacao) {
		this.numeroLacreInstalacao = numeroLacreInstalacao;
	}


	/**
	 * @return Retorna o campo numeroLacreInstalacaoPoco.
	 */
	public String getNumeroLacreInstalacaoPoco() {
		return numeroLacreInstalacaoPoco;
	}


	/**
	 * @param numeroLacreInstalacaoPoco O numeroLacreInstalacaoPoco a ser setado.
	 */
	public void setNumeroLacreInstalacaoPoco(String numeroLacreInstalacaoPoco) {
		this.numeroLacreInstalacaoPoco = numeroLacreInstalacaoPoco;
	}


	/**
	 * @return Retorna o campo posicaoLigacaoEsgoto.
	 */
	public String getPosicaoLigacaoEsgoto() {
		return posicaoLigacaoEsgoto;
	}


	/**
	 * @param posicaoLigacaoEsgoto O posicaoLigacaoEsgoto a ser setado.
	 */
	public void setPosicaoLigacaoEsgoto(String posicaoLigacaoEsgoto) {
		this.posicaoLigacaoEsgoto = posicaoLigacaoEsgoto;
	}


	/**
	 * @return Retorna o campo sistemaCaixaInspecao.
	 */
	public String getSistemaCaixaInspecao() {
		return sistemaCaixaInspecao;
	}


	/**
	 * @param sistemaCaixaInspecao O sistemaCaixaInspecao a ser setado.
	 */
	public void setSistemaCaixaInspecao(String sistemaCaixaInspecao) {
		this.sistemaCaixaInspecao = sistemaCaixaInspecao;
	}


	/**
	 * @return Retorna o campo tipoRelojoaria.
	 */
	public String getTipoRelojoaria() {
		return tipoRelojoaria;
	}


	/**
	 * @param tipoRelojoaria O tipoRelojoaria a ser setado.
	 */
	public void setTipoRelojoaria(String tipoRelojoaria) {
		this.tipoRelojoaria = tipoRelojoaria;
	}


	/**
	 * @return Retorna o campo tipoRelojoariaPoco.
	 */
	public String getTipoRelojoariaPoco() {
		return tipoRelojoariaPoco;
	}


	/**
	 * @param tipoRelojoariaPoco O tipoRelojoariaPoco a ser setado.
	 */
	public void setTipoRelojoariaPoco(String tipoRelojoariaPoco) {
		this.tipoRelojoariaPoco = tipoRelojoariaPoco;
	}


	/**
	 * @return Retorna o campo usuarioResponsavelInstalacao.
	 */
	public String getUsuarioResponsavelInstalacao() {
		return usuarioResponsavelInstalacao;
	}


	/**
	 * @param usuarioResponsavelInstalacao O usuarioResponsavelInstalacao a ser setado.
	 */
	public void setUsuarioResponsavelInstalacao(String usuarioResponsavelInstalacao) {
		this.usuarioResponsavelInstalacao = usuarioResponsavelInstalacao;
	}


	/**
	 * @return Retorna o campo usuarioResponsavelInstalacaoPoco.
	 */
	public String getUsuarioResponsavelInstalacaoPoco() {
		return usuarioResponsavelInstalacaoPoco;
	}
	
	
	/**
	 * 
	 * @return Retorna o campo dataProcessamento
	 */
	public String getDataProcessamento() {
		return dataProcessamento;
	}


	/**
	 * @param usuarioResponsavelInstalacaoPoco O usuarioResponsavelInstalacaoPoco a ser setado.
	 */
	public void setUsuarioResponsavelInstalacaoPoco(
			String usuarioResponsavelInstalacaoPoco) {
		this.usuarioResponsavelInstalacaoPoco = usuarioResponsavelInstalacaoPoco;
	}


	public String getHint1() {
		return hint1.replace( '\n', ' ' );
	}


	public void setHint1(String hint1) {
		this.hint1 = hint1;
	}


	public String getHint2() {
        String hint2 = "";
        
        String imovId =
            ( this.idImovelDadosCadastrais != null && !this.idImovelDadosCadastrais.equals( imovIdAnt ) ? this.idImovelDadosCadastrais :
            ( this.idImovelDadosComplementares != null && !this.idImovelDadosComplementares.equals( imovIdAnt ) ? this.idImovelDadosComplementares :
            ( this.idImovelAnaliseMedicaoConsumo != null && !this.idImovelAnaliseMedicaoConsumo.equals( imovIdAnt ) ? this.idImovelAnaliseMedicaoConsumo : 
            ( this.idImovelHistoricoFaturamento != null && !this.idImovelHistoricoFaturamento.equals( imovIdAnt ) ? this.idImovelHistoricoFaturamento :    
            ( this.idImovelDebitos != null && !this.idImovelDebitos.equals( imovIdAnt ) ? this.idImovelDebitos :
            ( this.idImovelPagamentos != null && !this.idImovelPagamentos.equals( imovIdAnt ) ? this.idImovelPagamentos :
            ( this.idImovelDevolucoesImovel != null && !this.idImovelDevolucoesImovel.equals( imovIdAnt ) ? this.idImovelDevolucoesImovel :
            ( this.idImovelDocumentosCobranca != null && !this.idImovelDocumentosCobranca.equals( imovIdAnt )? this.idImovelDocumentosCobranca :
            ( this.idImovelParcelamentosDebitos != null && !this.idImovelParcelamentosDebitos.equals( imovIdAnt ) ? this.idImovelParcelamentosDebitos :                            
            ( this.idImovelRegistroAtendimento != null && !this.idImovelRegistroAtendimento.equals( imovIdAnt ) ? this.idImovelRegistroAtendimento :                        
              this.imovIdAnt ) ) ) ) ) ) ) ) ) );
        
        this.idImovelDadosCadastrais = imovId;
        this.idImovelDadosComplementares = imovId;
        this.idImovelAnaliseMedicaoConsumo = imovId; 
        this.idImovelHistoricoFaturamento = imovId;    
        this.idImovelDebitos = imovId;
        this.idImovelPagamentos = imovId;
        this.idImovelDevolucoesImovel = imovId;
        this.idImovelDocumentosCobranca = imovId;
        this.idImovelParcelamentosDebitos = imovId;                            
        this.idImovelRegistroAtendimento = imovId;          
        
        if ( imovId != null && !imovId.equals( "" ) ){
            Fachada fachada = Fachada.getInstancia();
            
            String inscricao = fachada.pesquisarInscricaoImovelExcluidoOuNao( Integer.parseInt( imovId ) );
             
            Cliente clienteUsuario = fachada.pesquisarClienteUsuarioImovelExcluidoOuNao( Integer.parseInt( imovId ) );
            
            FiltroImovel filtro = new FiltroImovel();
            filtro.adicionarParametro( new ParametroSimples( FiltroImovel.ID, imovId ) );
            filtro.adicionarCaminhoParaCarregamentoEntidade( "ligacaoAgua.hidrometroInstalacaoHistorico.hidrometro" );
            Collection<Imovel> colImovel = fachada.pesquisar( filtro, Imovel.class.getName() );
            
            Imovel imovel = ( Imovel ) colImovel.iterator().next();
            
            String numeroHidrometro = 
                ( imovel.getLigacaoAgua() != null &&
                  imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() != null &&
                  imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro() != null &&
                  imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro().getNumero() != null ? 
                  imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro().getNumero() : "-" );
            
            String usuario = "";
            
            if (clienteUsuario != null) {
            	usuario = clienteUsuario.getCodigoNome();
            }
            
    		try {
                hint2 =  "Inscrição: " + 
                         inscricao + "<br>" +
                         "Cliente Usuário: " +
                         usuario + "<br>" +
                         "Hidrômetro: " +
                         numeroHidrometro + "<br>" +                     
                         "Endereço: " +
                         fachada.pesquisarEndereco( Integer.parseInt( imovId ) ) + "<br>";        
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        
        this.imovIdAnt = imovId;
        
        return hint2.replace( '\n', ' ' );
	}

	public String getDataSupressaoParcialAgua() {
		return dataSupressaoParcialAgua;
	}


	public void setDataSupressaoParcialAgua(String dataSupressaoParcialAgua) {
		this.dataSupressaoParcialAgua = dataSupressaoParcialAgua;
	}


	public void setImovIdAnt(String imovIdAnt) {
		this.imovIdAnt = imovIdAnt;
	}


	public String getImovIdAnt() {
		return imovIdAnt;
	}


	public String getIndicadorEmissaoExtratoNaConsulta() {
		return indicadorEmissaoExtratoNaConsulta;
	}


	public void setIndicadorEmissaoExtratoNaConsulta(
			String indicadorEmissaoExtratoNaConsulta) {
		this.indicadorEmissaoExtratoNaConsulta = indicadorEmissaoExtratoNaConsulta;
	}


	/**
	 * @return Returns the imoveisMicromedicaoEsgoto.
	 */
	public Collection<ImovelMicromedicao> getImoveisMicromedicaoEsgoto() {
		return imoveisMicromedicaoEsgoto;
	}


	/**
	 * @param imoveisMicromedicaoEsgoto The imoveisMicromedicaoEsgoto to set.
	 */
	public void setImoveisMicromedicaoEsgoto(Collection<ImovelMicromedicao> imoveisMicromedicaoEsgoto) {
		this.imoveisMicromedicaoEsgoto = imoveisMicromedicaoEsgoto;
	}


	public String getSituacaoCobrancaDadosComplementares() {
		return situacaoCobrancaDadosComplementares;
	}


	public void setSituacaoCobrancaDadosComplementares(
			String situacaoCobrancaDadosComplementares) {
		this.situacaoCobrancaDadosComplementares = situacaoCobrancaDadosComplementares;
	}


	public String getLigacaoAguaSituacaoId() {
		return ligacaoAguaSituacaoId;
	}


	public void setLigacaoAguaSituacaoId(String ligacaoAguaSituacaoId) {
		this.ligacaoAguaSituacaoId = ligacaoAguaSituacaoId;
	}


	public String getLigacaoEsgotoSituacaoId() {
		return ligacaoEsgotoSituacaoId;
	}


	public void setLigacaoEsgotoSituacaoId(String ligacaoEsgotoSituacaoId) {
		this.ligacaoEsgotoSituacaoId = ligacaoEsgotoSituacaoId;
	}
	
	public String getLeituraInstalacaoHidrometro() {
		return leituraInstalacaoHidrometro;
	}


	public void setLeituraInstalacaoHidrometro(String leituraInstalacaoHidrometro) {
		this.leituraInstalacaoHidrometro = leituraInstalacaoHidrometro;
	}


	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}


	public void setDescricaoMunicipio(String descricaoMunicipio) {
		this.descricaoMunicipio = descricaoMunicipio;
	}


	public String getIndicadorNivelInstalacaoEsgotoDadosCadastrais() {
		return indicadorNivelInstalacaoEsgotoDadosCadastrais;
	}


	public void setIndicadorNivelInstalacaoEsgotoDadosCadastrais(
			String indicadorNivelInstalacaoEsgotoDadosCadastrais) {
		this.indicadorNivelInstalacaoEsgotoDadosCadastrais = indicadorNivelInstalacaoEsgotoDadosCadastrais;
	}


	public void setDataProcessamento(String dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}
	
	public String getObservacaoCategoriaDadosCadastrais() {
		return observacaoCategoriaDadosCadastrais;
	}


	public void setObservacaoCategoriaDadosCadastrais(String observacaoCategoriaDadosCadastrais) {
		this.observacaoCategoriaDadosCadastrais = observacaoCategoriaDadosCadastrais;
	}


	public String getDataEmissaoComunicadoIrregularidadeFaturamento() {
		return dataEmissaoComunicadoIrregularidadeFaturamento;
	}


	public void setDataEmissaoComunicadoIrregularidadeFaturamento(String dataEmissaoComunicadoIrregularidadeFaturamento) {
		this.dataEmissaoComunicadoIrregularidadeFaturamento = dataEmissaoComunicadoIrregularidadeFaturamento;
	}

	
}
