package gcom.gui.cadastro.sistemaparametro;

import gcom.cadastro.imovel.ImovelPerfil;

import java.util.Collection;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 12/01/2007
 */
public class InformarSistemaParametrosActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	// 1 aba InserirParametrosSistemaDadosGeraisEmpresa
	private String nomeEstado;
	private String nomeEmpresa;
	private String abreviaturaEmpresa;
	private String cnpj;
	private String numero;
	private String complemento;
	private String numeroTelefone;
	private String ramal;
	private String fax;
	private String email;
	private String titulosRelatorio;
	private String unidadeOrganizacionalPresidencia;
	private String nomeUnidadeOrganizacionalPresidencia;
	private String presidente;
	private String nomePresidente;
	private String diretorComercial;
	private String nomeDiretorComercial;
	private String numeroTelefoneAtendimento;
	private String quantidadeDigitosQuadra; // Quantidade de dígitos que a
											// quadra deve ter
	private String indicadorQuadraFace; // foi retirado da segunda aba para a
										// primeira a pedido de Rosana
	private String site;
	private String inscricaoEstadual;
	private String inscricaoMunicipal;
	private String numeroContrato;
	private String imagemLogomarca;
	private String imagemRelatorio;
	private String imagemConta;
	private String logradouroBairro;
	private String logradouroCep;
	private String enderecoReferencia;
	private String numeroExecucaoResumoNegativacao;
	private String indicadorControlaAutoInfracao;
	private String indicadorExibirMensagem;
	private String indicadorUsaRota;
	private String perfilProgramaEspecial;
	private String idClienteResponsavelProgramaEspecial;
	private String nomeClienteResponsavelProgramaEspecial;
	private Collection<ImovelPerfil> colecaoPerfisImovel;
	private String versaoCelular;
	private String percentualConvergenciaRepavimentacao;
	private String indicadorDocumentoObrigatorio;
	private String indicadorPopupAtualizacaoCadastral;
	private String indicadorCpfCnpj;
	private String valorExtratoFichaComp;
	private String indicadorDuplicidadeCliente;
	private String indicadorNomeMenorDez;
	private String indicadorNomeClienteGenerico;
	private String indicadorUsoNMCliReceitaFantasia;
	private String indicadorVariaHierarquiaUnidade;
	private String clienteFicticioAssociarPagamentosNaoIdentificados;
	private String nomeClienteFicticioAssociarPagamentosNaoIdentificados;


	// 2ª aba InserirParametrosSistemaFaturamentoTarifaSocial
	private String mesAnoReferencia;
	private String menorConsumo;
	private String menorValor;
	private String qtdeEconomias;
	private String mesesCalculoMedio;
	private String diasMinimoVencimento;
	private String diasMinimoVencimentoCorreio;
	private String numeroMesesValidadeConta;
	private String numeroMesesAlteracaoVencimento;
	private String salarioMinimo;
	private String areaMaxima;
	private String consumoMaximo;
	private String indicadorLimiteAlteracaoVencimento;
	private String indicadorTarifaCategoria;
	private String indicadorAtualizacaoTarifaria;
	private String mesAnoAtualizacaoTarifaria;
	private String indicadorFaturamentoAntecipado;
	private String numeroMesesMaximoCalculoMedia;
	private String numeroMesesCalculoCorrecao;
	private String numeroVezesSuspendeLeitura;
	private String numeroMesesLeituraSuspensa;
	private String numeroMesesReinicioSitEspFatu;
	private String indicadorRoteiroEmpresa;
	private String indicadorRetificacaoValorMenor;
	private String indicadorTransferenciaComDebito;
	private String indicadorNaoMedidoTarifa;
	private String numeroDiasVariacaoConsumo;

	private String nnDiasPrazoRecursoAutoInfracao = "0";
	private String qtdeContasRetificadas;
	private String percentualBonusSocial;

	private String indicadorBloqueioContaMobile;

	private String indicadorCalculaVencimento;
	private String valorContaFichaComp;
	
	private String numeroMesesRetificarConta;

	private String indicadorNormaRetificacao;
	
	private String mensagemContaBraile;
	
	private String codigoTipoCalculoNaoMedido;
	
	private String valorGuiaFichaComp;
	private String valorDemonstrativoParcelamentoFichaComp;
	
	// 3 aba InserirParametrosSistemaArrecadacaoFinanceiro
	private String mesAnoReferenciaArrecadacao;
	private String codigoEmpresaFebraban;
	private String numeroLayOut;
	private String indentificadorContaDevolucao;
	private String percentualEntradaMinima;
	private String maximoParcelas;
	private String percentualMaximoAbatimento;
	private String percentualTaxaFinanciamento;
	private String numeroMaximoParcelaCredito;
	private String percentualCalculoIndice;
	private String numeroModuloDigitoVerificador;
	private String numeroMesesPesquisaImoveisRamaisSuprimidos;
	private String numeroAnoQuitacao;
	private String indicadorContaParcelada;
	private String indicadorCobrancaJudical;
	private String numeroMesesAnterioresParaDeclaracaoQuitacao;
	private String indicadorValorMovimentoArrecadador;
	private String cdDadosDiarios;

	// 4 aba InserirParametrosSistemaMicromedicaoCobranca
	private String codigoMenorCapacidade;
	private String indicadorGeracaoFaixaFalsa;
	private String indicadorPercentualGeracaoFaixaFalsa;
	private String percentualGeracaoFaixaFalsa;
	private String indicadorGeracaoFiscalizacaoLeitura;
	private String indicadorPercentualGeracaoFiscalizacaoLeitura;
	private String percentualGeracaoFiscalizacaoLeitura;
	private String incrementoMaximoConsumo;
	private String decrementoMaximoConsumo;
	private String percentualToleranciaRateioConsumo;
	private String diasVencimentoCobranca;
	private String numeroMaximoMesesSancoes;
	private String valorSegundaVia;
	private String indicadorCobrarTaxaExtrato;
	private String codigoPeriodicidadeNegativacao;
	private String numeroDiasCalculoAcrescimos;
	private String numeroDiasValidadeExtrato;
	private String numeroDiasValidadeExtratoPermissaoEspecial;
	private String indicadorParcelamentoConfirmado;
	private String indicadorControleDividaAtiva;
	private String numeroDiasVencimentoEntradaParcelamento;
	private String idResolucaoDiretoria;
	

	private String indicadorBloqueioContasContratoParcelDebitos;
	private String indicadorBloqueioContasContratoParcelManterConta;
	private String indicadorBloqueioGuiasOuAcresContratoParcelDebito;
	private String indicadorBloqueioDebitoACobrarContratoParcelDebito;	
	private String indicadorBloqueioGuiasOuAcresContratoParcelManterConta;
	private String indicadorBloqueioDebitoACobrarContratoParcelManterDebito;	
	private String numeroMaximoParcelasContratosParcelamento;
	private String numeroDiasEncerrarOsFiscalizacaoDecursoPrazo;

	// 5 aba InserirParametrosSistemaAtendimentoPublicoSeguranca
	private String indicadorSugestaoTramite;
	private String diasMaximoReativarRA;
	private String diasMaximoAlterarOS;
	private String numeroDiasEncerramentoOrdemServico;
	private String numeroDiasEncerramentoOSSeletiva;
	private String numeroDiasAlteracaoVencimentoPosterior;
	private String ultimoIDGeracaoRA;
	private String diasMaximoExpirarAcesso;
	private String diasMensagemExpiracaoSenha;
	private String numeroMaximoTentativasAcesso;
	private String numeroMaximoFavoritosMenu;
	private String ipServidorSmtp;
	private String ipServidorGerencial;
	private String emailResponsavel;
	private String urlHelp;
	private String mensagemSistema;
	private String diasVencimentoCertidaoNegativa;
	private String indicadorCertidaoNegativaEfeitoPositivo;
	private String indicadorControleTramitacaoRA;
	private String indicadorDocumentoValido;
	private String indicadorCalculoPrevisaoRADiasUteis;
	private String indicadorDebitoACobrarValidoCertidaoNegativa;
	private String indicadorLoginUnico;
	private String indicadorValidacaoLocalidadeEncerramentoOS;
	private String diasVencimentoAlternativo;
	private String indicarControleExpiracaoSenhaPorGrupo;
	private String indicarControleBloqueioSenha;
	private String indicadorSenhaForte;
	private String idUnidadeDestinoGrandeConsumidor;
	private String nomeUnidadeDestinoGrandeConsumidor;
	private String numeroDiasRevisaoConta;
	private String numeroDiasBloqueioCelular;
	private String ultimoDiaVencimentoAlternativo;
	private String indicadorTabelaPrice;	
	private String qtdeDiasValidadeOSFiscalizacao;
	private String qtdeDiasEncerraOSFiscalizacao;
	private String qtdeDiasEnvioEmailConta;
	private String descricaoDecreto;
	private FormFile arquivoDecreto;
	private String descricaoLeiEstTarif;
	private FormFile arquivoLeiEstTarif;
	private String descricaoLeiIndividualizacao;
	private FormFile arquivoLeiIndividualizacao;
	private String descricaoNormaCO;
	private FormFile arquivoNormaCO;
	private String descricaoNormaCM;
	private FormFile arquivoNormaCM;


	/**
	 * 
	 * @author José Guilherme Macedo Vieira
	 * @date 03/06/2009
	 * 
	 * @return String - Retorna a quantidade de contas retificadas
	 */
	public String getQtdeContasRetificadas() {
		return qtdeContasRetificadas;
	}

	/**
	 * @author José Guilherme Macedo Vieira
	 * @date 03/06/2009
	 * 
	 * @param String
	 *            qtdeContasRetificadas - A quantidade de contas retificadas da
	 *            empresa
	 */
	public void setQtdeContasRetificadas(String qtdeContasRetificadas) {
		this.qtdeContasRetificadas = qtdeContasRetificadas;
	}

	/**
	 * @author José Guilherme Macedo Vieira
	 * @date 02/06/2009
	 * @return String - Returns quantidadeDigitosQuadra
	 */
	public String getQuantidadeDigitosQuadra() {
		return quantidadeDigitosQuadra;
	}

	/**
	 * @author José Guilherme Macedo Vieira
	 * @date 02/06/2009
	 * @param quantidadeDigitosQuadra
	 *            - The quadra (block) max digits number
	 */
	public void setQuantidadeDigitosQuadra(String quantidadeDigitosQuadra) {
		this.quantidadeDigitosQuadra = quantidadeDigitosQuadra;
	}

	/**
	 * @return Returns the emailResponsavel.
	 */
	public String getEmailResponsavel() {
		return emailResponsavel;
	}

	/**
	 * @param emailResponsavel
	 *            The emailResponsavel to set.
	 */
	public void setEmailResponsavel(String emailResponsavel) {
		this.emailResponsavel = emailResponsavel;
	}

	/**
	 * @return Returns the ipServidorSmtp.
	 */
	public String getIpServidorSmtp() {
		return ipServidorSmtp;
	}

	/**
	 * @param ipServidorSmtp
	 *            The ipServidorSmtp to set.
	 */
	public void setIpServidorSmtp(String ipServidorSmtp) {
		this.ipServidorSmtp = ipServidorSmtp;
	}

	/**
	 * @return Retorna o campo abreviaturaEmpresa.
	 */
	public String getAbreviaturaEmpresa() {
		return abreviaturaEmpresa;
	}

	/**
	 * @param abreviaturaEmpresa
	 *            O abreviaturaEmpresa a ser setado.
	 */
	public void setAbreviaturaEmpresa(String abreviaturaEmpresa) {
		this.abreviaturaEmpresa = abreviaturaEmpresa;
	}

	/**
	 * @return Retorna o campo areaMaxima.
	 */
	public String getAreaMaxima() {
		return areaMaxima;
	}

	/**
	 * @param areaMaxima
	 *            O areaMaxima a ser setado.
	 */
	public void setAreaMaxima(String areaMaxima) {
		this.areaMaxima = areaMaxima;
	}

	/**
	 * @return Retorna o campo cnpj.
	 */
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * @param cnpj
	 *            O cnpj a ser setado.
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	/**
	 * @return Retorna o campo codigoEmpresaFebraban.
	 */
	public String getCodigoEmpresaFebraban() {
		return codigoEmpresaFebraban;
	}

	/**
	 * @param codigoEmpresaFebraban
	 *            O codigoEmpresaFebraban a ser setado.
	 */
	public void setCodigoEmpresaFebraban(String codigoEmpresaFebraban) {
		this.codigoEmpresaFebraban = codigoEmpresaFebraban;
	}

	/**
	 * @return Retorna o campo codigoMenorCapacidade.
	 */
	public String getCodigoMenorCapacidade() {
		return codigoMenorCapacidade;
	}

	/**
	 * @param codigoMenorCapacidade
	 *            O codigoMenorCapacidade a ser setado.
	 */
	public void setCodigoMenorCapacidade(String codigoMenorCapacidade) {
		this.codigoMenorCapacidade = codigoMenorCapacidade;
	}

	/**
	 * @return Retorna o campo consumoMaximo.
	 */
	public String getConsumoMaximo() {
		return consumoMaximo;
	}

	/**
	 * @param consumoMaximo
	 *            O consumoMaximo a ser setado.
	 */
	public void setConsumoMaximo(String consumoMaximo) {
		this.consumoMaximo = consumoMaximo;
	}

	/**
	 * @return Retorna o campo decrementoMaximoConsumo.
	 */
	public String getDecrementoMaximoConsumo() {
		return decrementoMaximoConsumo;
	}

	/**
	 * @param decrementoMaximoConsumo
	 *            O decrementoMaximoConsumo a ser setado.
	 */
	public void setDecrementoMaximoConsumo(String decrementoMaximoConsumo) {
		this.decrementoMaximoConsumo = decrementoMaximoConsumo;
	}

	/**
	 * @return Retorna o campo diasMaximoAlterarOS.
	 */
	public String getDiasMaximoAlterarOS() {
		return diasMaximoAlterarOS;
	}

	/**
	 * @param diasMaximoAlterarOS
	 *            O diasMaximoAlterarOS a ser setado.
	 */
	public void setDiasMaximoAlterarOS(String diasMaximoAlterarOS) {
		this.diasMaximoAlterarOS = diasMaximoAlterarOS;
	}

	/**
	 * @return Retorna o campo diasMaximoExpirarAcesso.
	 */
	public String getDiasMaximoExpirarAcesso() {
		return diasMaximoExpirarAcesso;
	}

	/**
	 * @param diasMaximoExpirarAcesso
	 *            O diasMaximoExpirarAcesso a ser setado.
	 */
	public void setDiasMaximoExpirarAcesso(String diasMaximoExpirarAcesso) {
		this.diasMaximoExpirarAcesso = diasMaximoExpirarAcesso;
	}

	/**
	 * @return Retorna o campo diasMaximoReativarRA.
	 */
	public String getDiasMaximoReativarRA() {
		return diasMaximoReativarRA;
	}

	/**
	 * @param diasMaximoReativarRA
	 *            O diasMaximoReativarRA a ser setado.
	 */
	public void setDiasMaximoReativarRA(String diasMaximoReativarRA) {
		this.diasMaximoReativarRA = diasMaximoReativarRA;
	}

	/**
	 * @return Retorna o campo diasMensagemExpiracaoSenha.
	 */
	public String getDiasMensagemExpiracaoSenha() {
		return diasMensagemExpiracaoSenha;
	}

	/**
	 * @param diasMensagemExpiracaoSenha
	 *            O diasMensagemExpiracaoSenha a ser setado.
	 */
	public void setDiasMensagemExpiracaoSenha(String diasMensagemExpiracaoSenha) {
		this.diasMensagemExpiracaoSenha = diasMensagemExpiracaoSenha;
	}

	/**
	 * @return Retorna o campo diasMinimoVencimento.
	 */
	public String getDiasMinimoVencimento() {
		return diasMinimoVencimento;
	}

	/**
	 * @param diasMinimoVencimento
	 *            O diasMinimoVencimento a ser setado.
	 */
	public void setDiasMinimoVencimento(String diasMinimoVencimento) {
		this.diasMinimoVencimento = diasMinimoVencimento;
	}

	/**
	 * @return Retorna o campo diasMinimoVencimentoCorreio.
	 */
	public String getDiasMinimoVencimentoCorreio() {
		return diasMinimoVencimentoCorreio;
	}

	/**
	 * @param diasMinimoVencimentoCorreio
	 *            O diasMinimoVencimentoCorreio a ser setado.
	 */
	public void setDiasMinimoVencimentoCorreio(
			String diasMinimoVencimentoCorreio) {
		this.diasMinimoVencimentoCorreio = diasMinimoVencimentoCorreio;
	}

	/**
	 * @return Retorna o campo diasVencimentoCobranca.
	 */
	public String getDiasVencimentoCobranca() {
		return diasVencimentoCobranca;
	}

	/**
	 * @param diasVencimentoCobranca
	 *            O diasVencimentoCobranca a ser setado.
	 */
	public void setDiasVencimentoCobranca(String diasVencimentoCobranca) {
		this.diasVencimentoCobranca = diasVencimentoCobranca;
	}

	/**
	 * @return Retorna o campo email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            O email a ser setado.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return Retorna o campo fax.
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax
	 *            O fax a ser setado.
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return Retorna o campo incrementoMaximoConsumo.
	 */
	public String getIncrementoMaximoConsumo() {
		return incrementoMaximoConsumo;
	}

	/**
	 * @param incrementoMaximoConsumo
	 *            O incrementoMaximoConsumo a ser setado.
	 */
	public void setIncrementoMaximoConsumo(String incrementoMaximoConsumo) {
		this.incrementoMaximoConsumo = incrementoMaximoConsumo;
	}

	/**
	 * @return Retorna o campo indentificadorContaDevolucao.
	 */
	public String getIndentificadorContaDevolucao() {
		return indentificadorContaDevolucao;
	}

	/**
	 * @param indentificadorContaDevolucao
	 *            O indentificadorContaDevolucao a ser setado.
	 */
	public void setIndentificadorContaDevolucao(
			String indentificadorContaDevolucao) {
		this.indentificadorContaDevolucao = indentificadorContaDevolucao;
	}

	/**
	 * @return Retorna o campo indicadorGeracaoFaixaFalsa.
	 */
	public String getIndicadorGeracaoFaixaFalsa() {
		return indicadorGeracaoFaixaFalsa;
	}

	/**
	 * @param indicadorGeracaoFaixaFalsa
	 *            O indicadorGeracaoFaixaFalsa a ser setado.
	 */
	public void setIndicadorGeracaoFaixaFalsa(String indicadorGeracaoFaixaFalsa) {
		this.indicadorGeracaoFaixaFalsa = indicadorGeracaoFaixaFalsa;
	}

	/**
	 * @return Retorna o campo indicadorGeracaoFiscalizacaoLeitura.
	 */
	public String getIndicadorGeracaoFiscalizacaoLeitura() {
		return indicadorGeracaoFiscalizacaoLeitura;
	}

	/**
	 * @param indicadorGeracaoFiscalizacaoLeitura
	 *            O indicadorGeracaoFiscalizacaoLeitura a ser setado.
	 */
	public void setIndicadorGeracaoFiscalizacaoLeitura(
			String indicadorGeracaoFiscalizacaoLeitura) {
		this.indicadorGeracaoFiscalizacaoLeitura = indicadorGeracaoFiscalizacaoLeitura;
	}

	/**
	 * @return Retorna o campo indicadorPercentualGeracaoFaixaFalsa.
	 */
	public String getIndicadorPercentualGeracaoFaixaFalsa() {
		return indicadorPercentualGeracaoFaixaFalsa;
	}

	/**
	 * @param indicadorPercentualGeracaoFaixaFalsa
	 *            O indicadorPercentualGeracaoFaixaFalsa a ser setado.
	 */
	public void setIndicadorPercentualGeracaoFaixaFalsa(
			String indicadorPercentualGeracaoFaixaFalsa) {
		this.indicadorPercentualGeracaoFaixaFalsa = indicadorPercentualGeracaoFaixaFalsa;
	}

	/**
	 * @return Retorna o campo indicadorPercentualGeracaoFiscalizacaoLeitura.
	 */
	public String getIndicadorPercentualGeracaoFiscalizacaoLeitura() {
		return indicadorPercentualGeracaoFiscalizacaoLeitura;
	}

	/**
	 * @param indicadorPercentualGeracaoFiscalizacaoLeitura
	 *            O indicadorPercentualGeracaoFiscalizacaoLeitura a ser setado.
	 */
	public void setIndicadorPercentualGeracaoFiscalizacaoLeitura(
			String indicadorPercentualGeracaoFiscalizacaoLeitura) {
		this.indicadorPercentualGeracaoFiscalizacaoLeitura = indicadorPercentualGeracaoFiscalizacaoLeitura;
	}

	/**
	 * @return Retorna o campo indicadorSugestaoTramite.
	 */
	public String getIndicadorSugestaoTramite() {
		return indicadorSugestaoTramite;
	}

	/**
	 * @param indicadorSugestaoTramite
	 *            O indicadorSugestaoTramite a ser setado.
	 */
	public void setIndicadorSugestaoTramite(String indicadorSugestaoTramite) {
		this.indicadorSugestaoTramite = indicadorSugestaoTramite;
	}

	/**
	 * @return Retorna o campo maximoParcelas.
	 */
	public String getMaximoParcelas() {
		return maximoParcelas;
	}

	/**
	 * @param maximoParcelas
	 *            O maximoParcelas a ser setado.
	 */
	public void setMaximoParcelas(String maximoParcelas) {
		this.maximoParcelas = maximoParcelas;
	}

	/**
	 * @return Retorna o campo menorConsumo.
	 */
	public String getMenorConsumo() {
		return menorConsumo;
	}

	/**
	 * @param menorConsumo
	 *            O menorConsumo a ser setado.
	 */
	public void setMenorConsumo(String menorConsumo) {
		this.menorConsumo = menorConsumo;
	}

	/**
	 * @return Retorna o campo menorValor.
	 */
	public String getMenorValor() {
		return menorValor;
	}

	/**
	 * @param menorValor
	 *            O menorValor a ser setado.
	 */
	public void setMenorValor(String menorValor) {
		this.menorValor = menorValor;
	}

	/**
	 * @return Retorna o campo mesAnoReferencia.
	 */
	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	/**
	 * @param mesAnoReferencia
	 *            O mesAnoReferencia a ser setado.
	 */
	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	/**
	 * @return Retorna o campo mesAnoReferenciaArrecadacao.
	 */
	public String getMesAnoReferenciaArrecadacao() {
		return mesAnoReferenciaArrecadacao;
	}

	/**
	 * @param mesAnoReferenciaArrecadacao
	 *            O mesAnoReferenciaArrecadacao a ser setado.
	 */
	public void setMesAnoReferenciaArrecadacao(
			String mesAnoReferenciaArrecadacao) {
		this.mesAnoReferenciaArrecadacao = mesAnoReferenciaArrecadacao;
	}

	/**
	 * @return Retorna o campo mesesCalculoMedio.
	 */
	public String getMesesCalculoMedio() {
		return mesesCalculoMedio;
	}

	/**
	 * @param mesesCalculoMedio
	 *            O mesesCalculoMedio a ser setado.
	 */
	public void setMesesCalculoMedio(String mesesCalculoMedio) {
		this.mesesCalculoMedio = mesesCalculoMedio;
	}

	/**
	 * @return Retorna o campo nomeEmpresa.
	 */
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	/**
	 * @param nomeEmpresa
	 *            O nomeEmpresa a ser setado.
	 */
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	/**
	 * @return Retorna o campo nomeEstado.
	 */
	public String getNomeEstado() {
		return nomeEstado;
	}

	/**
	 * @param nomeEstado
	 *            O nomeEstado a ser setado.
	 */
	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

	/**
	 * @return Retorna o campo numero.
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero
	 *            O numero a ser setado.
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return Retorna o campo numeroLayOut.
	 */
	public String getNumeroLayOut() {
		return numeroLayOut;
	}

	/**
	 * @param numeroLayOut
	 *            O numeroLayOut a ser setado.
	 */
	public void setNumeroLayOut(String numeroLayOut) {
		this.numeroLayOut = numeroLayOut;
	}

	/**
	 * @return Retorna o campo numeroMaximoFavoritosMenu.
	 */
	public String getNumeroMaximoFavoritosMenu() {
		return numeroMaximoFavoritosMenu;
	}

	/**
	 * @param numeroMaximoFavoritosMenu
	 *            O numeroMaximoFavoritosMenu a ser setado.
	 */
	public void setNumeroMaximoFavoritosMenu(String numeroMaximoFavoritosMenu) {
		this.numeroMaximoFavoritosMenu = numeroMaximoFavoritosMenu;
	}

	/**
	 * @return Retorna o campo numeroMaximoParcelaCredito.
	 */
	public String getNumeroMaximoParcelaCredito() {
		return numeroMaximoParcelaCredito;
	}

	/**
	 * @param numeroMaximoParcelaCredito
	 *            O numeroMaximoParcelaCredito a ser setado.
	 */
	public void setNumeroMaximoParcelaCredito(String numeroMaximoParcelaCredito) {
		this.numeroMaximoParcelaCredito = numeroMaximoParcelaCredito;
	}

	/**
	 * @return Retorna o campo numeroMaximoTentativasAcesso.
	 */
	public String getNumeroMaximoTentativasAcesso() {
		return numeroMaximoTentativasAcesso;
	}

	/**
	 * @param numeroMaximoTentativasAcesso
	 *            O numeroMaximoTentativasAcesso a ser setado.
	 */
	public void setNumeroMaximoTentativasAcesso(
			String numeroMaximoTentativasAcesso) {
		this.numeroMaximoTentativasAcesso = numeroMaximoTentativasAcesso;
	}

	/**
	 * @return Retorna o campo numeroMesesAlteracaoVencimento.
	 */
	public String getNumeroMesesAlteracaoVencimento() {
		return numeroMesesAlteracaoVencimento;
	}

	/**
	 * @param numeroMesesAlteracaoVencimento
	 *            O numeroMesesAlteracaoVencimento a ser setado.
	 */
	public void setNumeroMesesAlteracaoVencimento(
			String numeroMesesAlteracaoVencimento) {
		this.numeroMesesAlteracaoVencimento = numeroMesesAlteracaoVencimento;
	}

	/**
	 * @return Retorna o campo numeroMesesValidadeConta.
	 */
	public String getNumeroMesesValidadeConta() {
		return numeroMesesValidadeConta;
	}

	/**
	 * @param numeroMesesValidadeConta
	 *            O numeroMesesValidadeConta a ser setado.
	 */
	public void setNumeroMesesValidadeConta(String numeroMesesValidadeConta) {
		this.numeroMesesValidadeConta = numeroMesesValidadeConta;
	}

	/**
	 * @return Retorna o campo numeroTelefone.
	 */
	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	/**
	 * @param numeroTelefone
	 *            O numeroTelefone a ser setado.
	 */
	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	/**
	 * @return Retorna o campo percentualCalculoIndice.
	 */
	public String getPercentualCalculoIndice() {
		return percentualCalculoIndice;
	}

	/**
	 * @param percentualCalculoIndice
	 *            O percentualCalculoIndice a ser setado.
	 */
	public void setPercentualCalculoIndice(String percentualCalculoIndice) {
		this.percentualCalculoIndice = percentualCalculoIndice;
	}

	/**
	 * @return Retorna o campo percentualEntradaMinima.
	 */
	public String getPercentualEntradaMinima() {
		return percentualEntradaMinima;
	}

	/**
	 * @param percentualEntradaMinima
	 *            O percentualEntradaMinima a ser setado.
	 */
	public void setPercentualEntradaMinima(String percentualEntradaMinima) {
		this.percentualEntradaMinima = percentualEntradaMinima;
	}

	/**
	 * @return Retorna o campo percentualGeracaoFaixaFalsa.
	 */
	public String getPercentualGeracaoFaixaFalsa() {
		return percentualGeracaoFaixaFalsa;
	}

	/**
	 * @param percentualGeracaoFaixaFalsa
	 *            O percentualGeracaoFaixaFalsa a ser setado.
	 */
	public void setPercentualGeracaoFaixaFalsa(
			String percentualGeracaoFaixaFalsa) {
		this.percentualGeracaoFaixaFalsa = percentualGeracaoFaixaFalsa;
	}

	/**
	 * @return Retorna o campo percentualGeracaoFiscalizacaoLeitura.
	 */
	public String getPercentualGeracaoFiscalizacaoLeitura() {
		return percentualGeracaoFiscalizacaoLeitura;
	}

	/**
	 * @param percentualGeracaoFiscalizacaoLeitura
	 *            O percentualGeracaoFiscalizacaoLeitura a ser setado.
	 */
	public void setPercentualGeracaoFiscalizacaoLeitura(
			String percentualGeracaoFiscalizacaoLeitura) {
		this.percentualGeracaoFiscalizacaoLeitura = percentualGeracaoFiscalizacaoLeitura;
	}

	/**
	 * @return Retorna o campo percentualMaximoAbatimento.
	 */
	public String getPercentualMaximoAbatimento() {
		return percentualMaximoAbatimento;
	}

	/**
	 * @param percentualMaximoAbatimento
	 *            O percentualMaximoAbatimento a ser setado.
	 */
	public void setPercentualMaximoAbatimento(String percentualMaximoAbatimento) {
		this.percentualMaximoAbatimento = percentualMaximoAbatimento;
	}

	/**
	 * @return Retorna o campo percentualTaxaFinanciamento.
	 */
	public String getPercentualTaxaFinanciamento() {
		return percentualTaxaFinanciamento;
	}

	/**
	 * @param percentualTaxaFinanciamento
	 *            O percentualTaxaFinanciamento a ser setado.
	 */
	public void setPercentualTaxaFinanciamento(
			String percentualTaxaFinanciamento) {
		this.percentualTaxaFinanciamento = percentualTaxaFinanciamento;
	}

	/**
	 * @return Retorna o campo percentualToleranciaRateioConsumo.
	 */
	public String getPercentualToleranciaRateioConsumo() {
		return percentualToleranciaRateioConsumo;
	}

	/**
	 * @param percentualToleranciaRateioConsumo
	 *            O percentualToleranciaRateioConsumo a ser setado.
	 */
	public void setPercentualToleranciaRateioConsumo(
			String percentualToleranciaRateioConsumo) {
		this.percentualToleranciaRateioConsumo = percentualToleranciaRateioConsumo;
	}

	/**
	 * @return Retorna o campo qtdeEconomias.
	 */
	public String getQtdeEconomias() {
		return qtdeEconomias;
	}

	/**
	 * @param qtdeEconomias
	 *            O qtdeEconomias a ser setado.
	 */
	public void setQtdeEconomias(String qtdeEconomias) {
		this.qtdeEconomias = qtdeEconomias;
	}

	/**
	 * @return Retorna o campo ramal.
	 */
	public String getRamal() {
		return ramal;
	}

	/**
	 * @param ramal
	 *            O ramal a ser setado.
	 */
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	/**
	 * @return Retorna o campo salarioMinimo.
	 */
	public String getSalarioMinimo() {
		return salarioMinimo;
	}

	/**
	 * @param salarioMinimo
	 *            O salarioMinimo a ser setado.
	 */
	public void setSalarioMinimo(String salarioMinimo) {
		this.salarioMinimo = salarioMinimo;
	}

	/**
	 * @return Retorna o campo ultimoIDGeracaoRA.
	 */
	public String getUltimoIDGeracaoRA() {
		return ultimoIDGeracaoRA;
	}

	/**
	 * @param ultimoIDGeracaoRA
	 *            O ultimoIDGeracaoRA a ser setado.
	 */
	public void setUltimoIDGeracaoRA(String ultimoIDGeracaoRA) {
		this.ultimoIDGeracaoRA = ultimoIDGeracaoRA;
	}

	/**
	 * @return Retorna o campo titulosRelatorio.
	 */
	public String getTitulosRelatorio() {
		return titulosRelatorio;
	}

	/**
	 * @param titulosRelatorio
	 *            O titulosRelatorio a ser setado.
	 */
	public void setTitulosRelatorio(String titulosRelatorio) {
		this.titulosRelatorio = titulosRelatorio;
	}

	public String getNomeUnidadeOrganizacionalPresidencia() {
		return nomeUnidadeOrganizacionalPresidencia;
	}

	public void setNomeUnidadeOrganizacionalPresidencia(
			String nomeUnidadeOrganizacionalPresidencia) {
		this.nomeUnidadeOrganizacionalPresidencia = nomeUnidadeOrganizacionalPresidencia;
	}

	public String getUnidadeOrganizacionalPresidencia() {
		return unidadeOrganizacionalPresidencia;
	}

	public void setUnidadeOrganizacionalPresidencia(
			String unidadeOrganizacionalPresidencia) {
		this.unidadeOrganizacionalPresidencia = unidadeOrganizacionalPresidencia;
	}

	public String getNomePresidente() {
		return nomePresidente;
	}

	public void setNomePresidente(String nomePresidente) {
		this.nomePresidente = nomePresidente;
	}

	public String getPresidente() {
		return presidente;
	}

	public void setPresidente(String presidente) {
		this.presidente = presidente;
	}

	public String getDiretorComercial() {
		return diretorComercial;
	}

	public void setDiretorComercial(String diretorComercial) {
		this.diretorComercial = diretorComercial;
	}

	public String getNomeDiretorComercial() {
		return nomeDiretorComercial;
	}

	public void setNomeDiretorComercial(String nomeDiretorComercial) {
		this.nomeDiretorComercial = nomeDiretorComercial;
	}

	public String getNumeroTelefoneAtendimento() {
		return numeroTelefoneAtendimento;
	}

	public void setNumeroTelefoneAtendimento(String numeroTelefoneAtendimento) {
		this.numeroTelefoneAtendimento = numeroTelefoneAtendimento;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getIndicadorLimiteAlteracaoVencimento() {
		return indicadorLimiteAlteracaoVencimento;
	}

	public void setIndicadorLimiteAlteracaoVencimento(
			String indicadorLimiteAlteracaoVencimento) {
		this.indicadorLimiteAlteracaoVencimento = indicadorLimiteAlteracaoVencimento;
	}

	public String getImagemConta() {
		return imagemConta;
	}

	public void setImagemConta(String imagemConta) {
		this.imagemConta = imagemConta;
	}

	public String getImagemLogomarca() {
		return imagemLogomarca;
	}

	public void setImagemLogomarca(String imagemLogomarca) {
		this.imagemLogomarca = imagemLogomarca;
	}

	public String getImagemRelatorio() {
		return imagemRelatorio;
	}

	public void setImagemRelatorio(String imagemRelatorio) {
		this.imagemRelatorio = imagemRelatorio;
	}

	public String getIpServidorGerencial() {
		return ipServidorGerencial;
	}

	public void setIpServidorGerencial(String ipServidorGerencial) {
		this.ipServidorGerencial = ipServidorGerencial;
	}

	public String getMensagemSistema() {
		return mensagemSistema;
	}

	public void setMensagemSistema(String mensagemSistema) {
		this.mensagemSistema = mensagemSistema;
	}

	public String getIndicadorTarifaCategoria() {
		return indicadorTarifaCategoria;
	}

	public void setIndicadorTarifaCategoria(String indicadorTarifaCategoria) {
		this.indicadorTarifaCategoria = indicadorTarifaCategoria;
	}

	public String getIndicadorAtualizacaoTarifaria() {
		return indicadorAtualizacaoTarifaria;
	}

	public void setIndicadorAtualizacaoTarifaria(
			String indicadorAtualizacaoTarifaria) {
		this.indicadorAtualizacaoTarifaria = indicadorAtualizacaoTarifaria;
	}

	public String getMesAnoAtualizacaoTarifaria() {
		return mesAnoAtualizacaoTarifaria;
	}

	public void setMesAnoAtualizacaoTarifaria(String mesAnoAtualizacaoTarifaria) {
		this.mesAnoAtualizacaoTarifaria = mesAnoAtualizacaoTarifaria;
	}

	public String getIndicadorFaturamentoAntecipado() {
		return indicadorFaturamentoAntecipado;
	}

	public void setIndicadorFaturamentoAntecipado(
			String indicadorFaturamentoAntecipado) {
		this.indicadorFaturamentoAntecipado = indicadorFaturamentoAntecipado;
	}

	public String getCodigoPeriodicidadeNegativacao() {
		return codigoPeriodicidadeNegativacao;
	}

	public void setCodigoPeriodicidadeNegativacao(
			String codigoPeriodicidadeNegativacao) {
		this.codigoPeriodicidadeNegativacao = codigoPeriodicidadeNegativacao;
	}

	public String getIndicadorCobrarTaxaExtrato() {
		return indicadorCobrarTaxaExtrato;
	}

	public void setIndicadorCobrarTaxaExtrato(String indicadorCobrarTaxaExtrato) {
		this.indicadorCobrarTaxaExtrato = indicadorCobrarTaxaExtrato;
	}

	public String getNumeroDiasCalculoAcrescimos() {
		return numeroDiasCalculoAcrescimos;
	}

	public void setNumeroDiasCalculoAcrescimos(
			String numeroDiasCalculoAcrescimos) {
		this.numeroDiasCalculoAcrescimos = numeroDiasCalculoAcrescimos;
	}

	public String getNumeroMaximoMesesSancoes() {
		return numeroMaximoMesesSancoes;
	}

	public void setNumeroMaximoMesesSancoes(String numeroMaximoMesesSancoes) {
		this.numeroMaximoMesesSancoes = numeroMaximoMesesSancoes;
	}

	public String getValorSegundaVia() {
		return valorSegundaVia;
	}

	public void setValorSegundaVia(String valorSegundaVia) {
		this.valorSegundaVia = valorSegundaVia;
	}

	public String getLogradouroBairro() {
		return logradouroBairro;
	}

	public void setLogradouroBairro(String logradouroBairro) {
		this.logradouroBairro = logradouroBairro;
	}

	public String getLogradouroCep() {
		return logradouroCep;
	}

	public void setLogradouroCep(String logradouroCep) {
		this.logradouroCep = logradouroCep;
	}

	public String getNumeroDiasEncerramentoOrdemServico() {
		return numeroDiasEncerramentoOrdemServico;
	}

	public void setNumeroDiasEncerramentoOrdemServico(
			String numeroDiasEncerramentoOrdemServico) {
		this.numeroDiasEncerramentoOrdemServico = numeroDiasEncerramentoOrdemServico;
	}

	public String getNumeroDiasEncerramentoOSSeletiva() {
		return numeroDiasEncerramentoOSSeletiva;
	}

	public void setNumeroDiasEncerramentoOSSeletiva(
			String numeroDiasEncerramentoOSSeletiva) {
		this.numeroDiasEncerramentoOSSeletiva = numeroDiasEncerramentoOSSeletiva;
	}

	public String getNumeroDiasAlteracaoVencimentoPosterior() {
		return numeroDiasAlteracaoVencimentoPosterior;
	}

	public void setNumeroDiasAlteracaoVencimentoPosterior(
			String numeroDiasAlteracaoVencimentoPosterior) {
		this.numeroDiasAlteracaoVencimentoPosterior = numeroDiasAlteracaoVencimentoPosterior;
	}

	public String getNumeroMesesMaximoCalculoMedia() {
		return numeroMesesMaximoCalculoMedia;
	}

	public void setNumeroMesesMaximoCalculoMedia(
			String numeroMesesMaximoCalculoMedia) {
		this.numeroMesesMaximoCalculoMedia = numeroMesesMaximoCalculoMedia;
	}

	public String getIndicadorRoteiroEmpresa() {
		return indicadorRoteiroEmpresa;
	}

	public void setIndicadorRoteiroEmpresa(String indicadorRoteiroEmpresa) {
		this.indicadorRoteiroEmpresa = indicadorRoteiroEmpresa;
	}

	public String getNumeroMesesCalculoCorrecao() {
		return numeroMesesCalculoCorrecao;
	}

	public void setNumeroMesesCalculoCorrecao(String numeroMesesCalculoCorrecao) {
		this.numeroMesesCalculoCorrecao = numeroMesesCalculoCorrecao;
	}

	public String getNumeroMesesLeituraSuspensa() {
		return numeroMesesLeituraSuspensa;
	}

	public void setNumeroMesesLeituraSuspensa(String numeroMesesLeituraSuspensa) {
		this.numeroMesesLeituraSuspensa = numeroMesesLeituraSuspensa;
	}

	public String getNumeroMesesReinicioSitEspFatu() {
		return numeroMesesReinicioSitEspFatu;
	}

	public void setNumeroMesesReinicioSitEspFatu(
			String numeroMesesReinicioSitEspFatu) {
		this.numeroMesesReinicioSitEspFatu = numeroMesesReinicioSitEspFatu;
	}

	public String getNumeroVezesSuspendeLeitura() {
		return numeroVezesSuspendeLeitura;
	}

	public void setNumeroVezesSuspendeLeitura(String numeroVezesSuspendeLeitura) {
		this.numeroVezesSuspendeLeitura = numeroVezesSuspendeLeitura;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getEnderecoReferencia() {
		return enderecoReferencia;
	}

	public void setEnderecoReferencia(String enderecoReferencia) {
		this.enderecoReferencia = enderecoReferencia;
	}

	public String getNumeroDiasValidadeExtrato() {
		return numeroDiasValidadeExtrato;
	}

	public void setNumeroDiasValidadeExtrato(String numeroDiasValidadeExtrato) {
		this.numeroDiasValidadeExtrato = numeroDiasValidadeExtrato;
	}

	public String getNumeroDiasValidadeExtratoPermissaoEspecial() {
		return numeroDiasValidadeExtratoPermissaoEspecial;
	}

	public void setNumeroDiasValidadeExtratoPermissaoEspecial(
			String numeroDiasValidadeExtratoPermissaoEspecial) {
		this.numeroDiasValidadeExtratoPermissaoEspecial = numeroDiasValidadeExtratoPermissaoEspecial;
	}

	public String getNumeroExecucaoResumoNegativacao() {
		return numeroExecucaoResumoNegativacao;
	}

	public void setNumeroExecucaoResumoNegativacao(
			String numeroExecucaoResumoNegativacao) {
		this.numeroExecucaoResumoNegativacao = numeroExecucaoResumoNegativacao;
	}

	public String getIndicadorControlaAutoInfracao() {
		return indicadorControlaAutoInfracao;
	}

	public void setIndicadorControlaAutoInfracao(
			String indicadorControlaAutoInfracao) {
		this.indicadorControlaAutoInfracao = indicadorControlaAutoInfracao;
	}

	public String getIndicadorRetificacaoValorMenor() {
		return indicadorRetificacaoValorMenor;
	}

	public void setIndicadorRetificacaoValorMenor(
			String indicadorRetificacaoValorMenor) {
		this.indicadorRetificacaoValorMenor = indicadorRetificacaoValorMenor;
	}

	public String getIndicadorTransferenciaComDebito() {
		return indicadorTransferenciaComDebito;
	}

	public void setIndicadorTransferenciaComDebito(
			String indicadorTransferenciaComDebito) {
		this.indicadorTransferenciaComDebito = indicadorTransferenciaComDebito;
	}

	public String getIndicadorNaoMedidoTarifa() {
		return indicadorNaoMedidoTarifa;
	}

	public void setIndicadorNaoMedidoTarifa(String indicadorNaoMedidoTarifa) {
		this.indicadorNaoMedidoTarifa = indicadorNaoMedidoTarifa;
	}

	public String getIndicadorParcelamentoConfirmado() {
		return indicadorParcelamentoConfirmado;
	}

	public void setIndicadorParcelamentoConfirmado(
			String indicadorParcelamentoConfirmado) {
		this.indicadorParcelamentoConfirmado = indicadorParcelamentoConfirmado;
	}
	
	public String getDiasVencimentoCertidaoNegativa() {
		return diasVencimentoCertidaoNegativa;
	}

	public void setDiasVencimentoCertidaoNegativa(
			String diasVencimentoCertidaoNegativa) {
		this.diasVencimentoCertidaoNegativa = diasVencimentoCertidaoNegativa;
	}

	public String getIndicadorCertidaoNegativaEfeitoPositivo() {
		return indicadorCertidaoNegativaEfeitoPositivo;
	}

	public void setIndicadorCertidaoNegativaEfeitoPositivo(
			String indicadorCertidaoNegativaEfeitoPositivo) {
		this.indicadorCertidaoNegativaEfeitoPositivo = indicadorCertidaoNegativaEfeitoPositivo;
	}

	public String getIndicadorExibirMensagem() {
		return indicadorExibirMensagem;
	}

	public void setIndicadorExibirMensagem(String indicadorExibirMensagem) {
		this.indicadorExibirMensagem = indicadorExibirMensagem;
	}

	public String getIndicadorControleTramitacaoRA() {
		return indicadorControleTramitacaoRA;
	}

	public void setIndicadorControleTramitacaoRA(
			String indicadorControleTramitacaoRA) {
		this.indicadorControleTramitacaoRA = indicadorControleTramitacaoRA;
	}

	public String getIndicadorDocumentoValido() {
		return indicadorDocumentoValido;
	}

	public void setIndicadorDocumentoValido(String indicadorDocumentoValido) {
		this.indicadorDocumentoValido = indicadorDocumentoValido;
	}

	public String getIndicadorDebitoACobrarValidoCertidaoNegativa() {
		return indicadorDebitoACobrarValidoCertidaoNegativa;
	}

	public void setIndicadorDebitoACobrarValidoCertidaoNegativa(
			String indicadorDebitoACobrarValidoCertidaoNegativa) {
		this.indicadorDebitoACobrarValidoCertidaoNegativa = indicadorDebitoACobrarValidoCertidaoNegativa;
	}

	public String getIndicadorUsaRota() {
		return indicadorUsaRota;
	}

	public void setIndicadorUsaRota(String indicadorUsaRota) {
		this.indicadorUsaRota = indicadorUsaRota;
	}

	public String getIndicadorLoginUnico() {
		return indicadorLoginUnico;
	}

	public void setIndicadorLoginUnico(String indicadorLoginUnico) {
		this.indicadorLoginUnico = indicadorLoginUnico;
	}

	public String getIndicadorCalculoPrevisaoRADiasUteis() {
		return indicadorCalculoPrevisaoRADiasUteis;
	}

	public void setIndicadorCalculoPrevisaoRADiasUteis(
			String indicadorCalculoPrevisaoRADiasUteis) {
		this.indicadorCalculoPrevisaoRADiasUteis = indicadorCalculoPrevisaoRADiasUteis;
	}

	public String getNumeroDiasVariacaoConsumo() {
		return numeroDiasVariacaoConsumo;
	}

	public void setNumeroDiasVariacaoConsumo(String numeroDiasVariacaoConsumo) {
		this.numeroDiasVariacaoConsumo = numeroDiasVariacaoConsumo;
	}

	public String getIndicadorQuadraFace() {
		return indicadorQuadraFace;
	}

	public void setIndicadorQuadraFace(String indicadorQuadraFace) {
		this.indicadorQuadraFace = indicadorQuadraFace;
	}

	public String getNnDiasPrazoRecursoAutoInfracao() {
		return nnDiasPrazoRecursoAutoInfracao;
	}

	public void setNnDiasPrazoRecursoAutoInfracao(
			String nnDiasPrazoRecursoAutoInfracao) {
		this.nnDiasPrazoRecursoAutoInfracao = nnDiasPrazoRecursoAutoInfracao;
	}

	public String getPercentualBonusSocial() {
		return percentualBonusSocial;
	}

	public void setPercentualBonusSocial(String percentualBonusSocial) {
		this.percentualBonusSocial = percentualBonusSocial;
	}

	public String getIndicadorControleDividaAtiva() {
		return indicadorControleDividaAtiva;
	}

	public void setIndicadorControleDividaAtiva(
			String indicadorControleDividaAtiva) {
		this.indicadorControleDividaAtiva = indicadorControleDividaAtiva;
	}

	public String getUrlHelp() {
		return urlHelp;
	}

	public void setUrlHelp(String urlHelp) {
		this.urlHelp = urlHelp;
	}

	public String getDiasVencimentoAlternativo() {
		return diasVencimentoAlternativo;
	}

	public void setDiasVencimentoAlternativo(String diasVencimentoAlternativo) {
		this.diasVencimentoAlternativo = diasVencimentoAlternativo;
	}

	/**
	 * @return Returns the indicadorValidacaoLocalidadeEncerramentoOS.
	 */
	public String getIndicadorValidacaoLocalidadeEncerramentoOS() {
		return indicadorValidacaoLocalidadeEncerramentoOS;
	}

	/**
	 * @param indicadorValidacaoLocalidadeEncerramentoOS
	 *            The indicadorValidacaoLocalidadeEncerramentoOS to set.
	 */
	public void setIndicadorValidacaoLocalidadeEncerramentoOS(
			String indicadorValidacaoLocalidadeEncerramentoOS) {
		this.indicadorValidacaoLocalidadeEncerramentoOS = indicadorValidacaoLocalidadeEncerramentoOS;
	}

	public String getIndicarControleExpiracaoSenhaPorGrupo() {
		return indicarControleExpiracaoSenhaPorGrupo;
	}

	public void setIndicarControleExpiracaoSenhaPorGrupo(
			String indicarControleExpiracaoSenhaPorGrupo) {
		this.indicarControleExpiracaoSenhaPorGrupo = indicarControleExpiracaoSenhaPorGrupo;
	}

	public String getIndicarControleBloqueioSenha() {
		return indicarControleBloqueioSenha;
	}

	public void setIndicarControleBloqueioSenha(
			String indicarControleBloqueioSenha) {
		this.indicarControleBloqueioSenha = indicarControleBloqueioSenha;
	}

	public String getIndicadorSenhaForte() {
		return indicadorSenhaForte;
	}

	public void setIndicadorSenhaForte(String indicadorSenhaForte) {
		this.indicadorSenhaForte = indicadorSenhaForte;
	}

	public String getPerfilProgramaEspecial() {
		return perfilProgramaEspecial;
	}

	public void setPerfilProgramaEspecial(String perfilProgramaEspecial) {
		this.perfilProgramaEspecial = perfilProgramaEspecial;
	}

	public String getIdClienteResponsavelProgramaEspecial() {
		return idClienteResponsavelProgramaEspecial;
	}

	public void setIdClienteResponsavelProgramaEspecial(
			String idClienteResponsavelProgramaEspecial) {
		this.idClienteResponsavelProgramaEspecial = idClienteResponsavelProgramaEspecial;
	}

	public String getNomeClienteResponsavelProgramaEspecial() {
		return nomeClienteResponsavelProgramaEspecial;
	}

	public void setNomeClienteResponsavelProgramaEspecial(
			String nomeClienteResponsavelProgramaEspecial) {
		this.nomeClienteResponsavelProgramaEspecial = nomeClienteResponsavelProgramaEspecial;
	}

	public Collection<ImovelPerfil> getColecaoPerfisImovel() {
		return colecaoPerfisImovel;
	}

	public void setColecaoPerfisImovel(
			Collection<ImovelPerfil> colecaoPerfisImovel) {
		this.colecaoPerfisImovel = colecaoPerfisImovel;
	}

	public String getIdUnidadeDestinoGrandeConsumidor() {
		return idUnidadeDestinoGrandeConsumidor;
	}

	public void setIdUnidadeDestinoGrandeConsumidor(
			String idUnidadeDestinoGrandeConsumidor) {
		this.idUnidadeDestinoGrandeConsumidor = idUnidadeDestinoGrandeConsumidor;
	}

	public String getNomeUnidadeDestinoGrandeConsumidor() {
		return nomeUnidadeDestinoGrandeConsumidor;
	}

	public void setNomeUnidadeDestinoGrandeConsumidor(
			String nomeUnidadeDestinoGrandeConsumidor) {
		this.nomeUnidadeDestinoGrandeConsumidor = nomeUnidadeDestinoGrandeConsumidor;
	}

	/**
	 * @return Returns the versaoCelular.
	 */
	public String getVersaoCelular() {
		return versaoCelular;
	}

	/**
	 * @param versaoCelular
	 *            The versaoCelular to set.
	 */
	public void setVersaoCelular(String versaoCelular) {
		this.versaoCelular = versaoCelular;
	}

	public String getNumeroModuloDigitoVerificador() {
		return numeroModuloDigitoVerificador;
	}

	public void setNumeroModuloDigitoVerificador(
			String numeroModuloDigitoVerificador) {
		this.numeroModuloDigitoVerificador = numeroModuloDigitoVerificador;
	}

	public String getNumeroMesesPesquisaImoveisRamaisSuprimidos() {
		return numeroMesesPesquisaImoveisRamaisSuprimidos;
	}

	public void setNumeroMesesPesquisaImoveisRamaisSuprimidos(
			String numeroMesesPesquisaImoveisRamaisSuprimidos) {
		this.numeroMesesPesquisaImoveisRamaisSuprimidos = numeroMesesPesquisaImoveisRamaisSuprimidos;
	}

	public String getNumeroAnoQuitacao() {
		return numeroAnoQuitacao;
	}

	public void setNumeroAnoQuitacao(String numeroAnoQuitacao) {
		this.numeroAnoQuitacao = numeroAnoQuitacao;
	}

	public String getIndicadorContaParcelada() {
		return indicadorContaParcelada;
	}

	public void setIndicadorContaParcelada(String indicadorContaParcelada) {
		this.indicadorContaParcelada = indicadorContaParcelada;
	}

	public String getIndicadorCobrancaJudical() {
		return indicadorCobrancaJudical;
	}

	public void setIndicadorCobrancaJudical(String indicadorCobrancaJudical) {
		this.indicadorCobrancaJudical = indicadorCobrancaJudical;
	}

	public String getNumeroMesesAnterioresParaDeclaracaoQuitacao() {
		return numeroMesesAnterioresParaDeclaracaoQuitacao;
	}

	public void setNumeroMesesAnterioresParaDeclaracaoQuitacao(
			String numeroMesesAnterioresParaDeclaracaoQuitacao) {
		this.numeroMesesAnterioresParaDeclaracaoQuitacao = numeroMesesAnterioresParaDeclaracaoQuitacao;
	}

	public String getIndicadorValorMovimentoArrecadador() {
		return indicadorValorMovimentoArrecadador;
	}

	public void setIndicadorValorMovimentoArrecadador(
			String indicadorValorMovimentoArrecadador) {
		this.indicadorValorMovimentoArrecadador = indicadorValorMovimentoArrecadador;
	}

	public String getNumeroDiasVencimentoEntradaParcelamento() {
		return numeroDiasVencimentoEntradaParcelamento;
	}

	public void setNumeroDiasVencimentoEntradaParcelamento(
			String numeroDiasVencimentoEntradaParcelamento) {
		this.numeroDiasVencimentoEntradaParcelamento = numeroDiasVencimentoEntradaParcelamento;
	}

	public String getIndicadorBloqueioContaMobile() {
		return indicadorBloqueioContaMobile;
	}

	public void setIndicadorBloqueioContaMobile(
			String indicadorBloqueioContaMobile) {
		this.indicadorBloqueioContaMobile = indicadorBloqueioContaMobile;
	}

	/**
	 * @return Returns the percentualConvergenciaRepavimentacao.
	 */
	public String getPercentualConvergenciaRepavimentacao() {
		return percentualConvergenciaRepavimentacao;
	}

	/**
	 * @param percentualConvergenciaRepavimentacao
	 *            The percentualConvergenciaRepavimentacao to set.
	 */
	public void setPercentualConvergenciaRepavimentacao(
			String percentualConvergenciaRepavimentacao) {
		this.percentualConvergenciaRepavimentacao = percentualConvergenciaRepavimentacao;
	}

	public String getNumeroDiasRevisaoConta() {
		return numeroDiasRevisaoConta;
	}

	public void setNumeroDiasRevisaoConta(String numeroDiasRevisaoConta) {
		this.numeroDiasRevisaoConta = numeroDiasRevisaoConta;
	}

	public String getIndicadorDocumentoObrigatorio() {
		return indicadorDocumentoObrigatorio;
	}

	public void setIndicadorDocumentoObrigatorio(
			String indicadorDocumentoObrigatorio) {
		this.indicadorDocumentoObrigatorio = indicadorDocumentoObrigatorio;
	}

	public String getIndicadorCalculaVencimento() {
		return indicadorCalculaVencimento;
	}

	public void setIndicadorCalculaVencimento(String indicadorCalculaVencimento) {
		this.indicadorCalculaVencimento = indicadorCalculaVencimento;
	}

	public String getIndicadorPopupAtualizacaoCadastral() {
		return indicadorPopupAtualizacaoCadastral;
	}

	public void setIndicadorPopupAtualizacaoCadastral(
			String indicadorPopupAtualizacaoCadastral) {
		this.indicadorPopupAtualizacaoCadastral = indicadorPopupAtualizacaoCadastral;
	}


	public String getIndicadorCpfCnpj() {
		return indicadorCpfCnpj;
	}

	public void setIndicadorCpfCnpj(String indicadorCpfCnpj) {
		this.indicadorCpfCnpj = indicadorCpfCnpj;
	}


	public String getValorContaFichaComp() {
		return valorContaFichaComp;
	}

	public void setValorContaFichaComp(String valorContaFichaComp) {
		this.valorContaFichaComp = valorContaFichaComp;
	}

	public String getIdResolucaoDiretoria() {
		return idResolucaoDiretoria;
	}

	public void setIdResolucaoDiretoria(String idResolucaoDiretoria) {
		this.idResolucaoDiretoria = idResolucaoDiretoria;
	}

	public String getNumeroDiasBloqueioCelular() {
		return numeroDiasBloqueioCelular;
	}

	public void setNumeroDiasBloqueioCelular(String numeroDiasBloqueioCelular) {
		this.numeroDiasBloqueioCelular = numeroDiasBloqueioCelular;
	}

	public String getValorExtratoFichaComp() {
		return valorExtratoFichaComp;
	}

	public void setValorExtratoFichaComp(String valorExtratoFichaComp) {
		this.valorExtratoFichaComp = valorExtratoFichaComp;
	}

	public String getUltimoDiaVencimentoAlternativo() {
		return ultimoDiaVencimentoAlternativo;
	}

	public void setUltimoDiaVencimentoAlternativo(
			String ultimoDiaVencimentoAlternativo) {
		this.ultimoDiaVencimentoAlternativo = ultimoDiaVencimentoAlternativo;
	}

	public String getIndicadorDuplicidadeCliente() {
		return indicadorDuplicidadeCliente;
	}

	public void setIndicadorDuplicidadeCliente(String indicadorDuplicidadeCliente) {
		this.indicadorDuplicidadeCliente = indicadorDuplicidadeCliente;
	}

	public String getIndicadorNomeClienteGenerico() {
		return indicadorNomeClienteGenerico;
	}

	public void setIndicadorNomeClienteGenerico(String indicadorNomeClienteGenerico) {
		this.indicadorNomeClienteGenerico = indicadorNomeClienteGenerico;
	}

	public String getIndicadorNomeMenorDez() {
		return indicadorNomeMenorDez;
	}

	public void setIndicadorNomeMenorDez(String indicadorNomeMenorDez) {
		this.indicadorNomeMenorDez = indicadorNomeMenorDez;
	}

	public String getNumeroMesesRetificarConta() {
		return numeroMesesRetificarConta;
	}

	public void setNumeroMesesRetificarConta(String numeroMesesRetificarConta) {
		this.numeroMesesRetificarConta = numeroMesesRetificarConta;
	}

	public String getIndicadorNormaRetificacao() {
		return indicadorNormaRetificacao;
	}

	public void setIndicadorNormaRetificacao(String indicadorNormaRetificacao) {
		this.indicadorNormaRetificacao = indicadorNormaRetificacao;
	}

	public String getMensagemContaBraile() {
		return mensagemContaBraile;
	}

	public void setMensagemContaBraile(String mensagemContaBraile) {
		this.mensagemContaBraile = mensagemContaBraile;
	}

	public void setValorDemonstrativoParcelamentoFichaComp(
			String valorDemonstrativoParcelamentoFichaComp) {
		this.valorDemonstrativoParcelamentoFichaComp = valorDemonstrativoParcelamentoFichaComp;
	}

	public String getValorGuiaFichaComp() {
		return valorGuiaFichaComp;
	}

	public void setValorGuiaFichaComp(String valorGuiaFichaComp) {
		this.valorGuiaFichaComp = valorGuiaFichaComp;
	}

	public String getIndicadorUsoNMCliReceitaFantasia() {
		return indicadorUsoNMCliReceitaFantasia;
	}

	public void setIndicadorUsoNMCliReceitaFantasia(
			String indicadorUsoNMCliReceitaFantasia) {
		this.indicadorUsoNMCliReceitaFantasia = indicadorUsoNMCliReceitaFantasia;
	}

	public String getIndicadorBloqueioContasContratoParcelDebitos() {
		return indicadorBloqueioContasContratoParcelDebitos;
	}

	public void setIndicadorBloqueioContasContratoParcelDebitos(
			String indicadorBloqueioContasContratoParcelDebitos) {
		this.indicadorBloqueioContasContratoParcelDebitos = indicadorBloqueioContasContratoParcelDebitos;
	}

	public String getIndicadorBloqueioContasContratoParcelManterConta() {
		return indicadorBloqueioContasContratoParcelManterConta;
	}

	public void setIndicadorBloqueioContasContratoParcelManterConta(
			String indicadorBloqueioContasContratoParcelManterConta) {
		this.indicadorBloqueioContasContratoParcelManterConta = indicadorBloqueioContasContratoParcelManterConta;
	}

	public String getIndicadorBloqueioGuiasOuAcresContratoParcelDebito() {
		return indicadorBloqueioGuiasOuAcresContratoParcelDebito;
	}

	public void setIndicadorBloqueioGuiasOuAcresContratoParcelDebito(
			String indicadorBloqueioGuiasOuAcresContratoParcelDebito) {
		this.indicadorBloqueioGuiasOuAcresContratoParcelDebito = indicadorBloqueioGuiasOuAcresContratoParcelDebito;
	}

	public String getIndicadorBloqueioGuiasOuAcresContratoParcelManterConta() {
		return indicadorBloqueioGuiasOuAcresContratoParcelManterConta;
	}

	public void setIndicadorBloqueioGuiasOuAcresContratoParcelManterConta(
			String indicadorBloqueioGuiasOuAcresContratoParcelManterConta) {
		this.indicadorBloqueioGuiasOuAcresContratoParcelManterConta = indicadorBloqueioGuiasOuAcresContratoParcelManterConta;
	}

	public String getNumeroMaximoParcelasContratosParcelamento() {
		return numeroMaximoParcelasContratosParcelamento;
	}

	public void setNumeroMaximoParcelasContratosParcelamento(
			String numeroMaximoParcelasContratosParcelamento) {
		this.numeroMaximoParcelasContratosParcelamento = numeroMaximoParcelasContratosParcelamento;
	}

	public String getIndicadorTabelaPrice() {
		return indicadorTabelaPrice;
	}

	public void setIndicadorTabelaPrice(String indicadorTabelaPrice) {
		this.indicadorTabelaPrice = indicadorTabelaPrice;
	}

	public String getIndicadorVariaHierarquiaUnidade() {
		return indicadorVariaHierarquiaUnidade;
	}

	public void setIndicadorVariaHierarquiaUnidade(
			String indicadorVariaHierarquiaUnidade) {
		this.indicadorVariaHierarquiaUnidade = indicadorVariaHierarquiaUnidade;
	}

	public String getCodigoTipoCalculoNaoMedido() {
		return codigoTipoCalculoNaoMedido;
	}

	public void setCodigoTipoCalculoNaoMedido(String codigoTipoCalculoNaoMedido) {
		this.codigoTipoCalculoNaoMedido = codigoTipoCalculoNaoMedido;
	}

	public String getValorDemonstrativoParcelamentoFichaComp() {
		return valorDemonstrativoParcelamentoFichaComp;
	}

	public String getQtdeDiasEncerraOSFiscalizacao() {
		return qtdeDiasEncerraOSFiscalizacao;
	}

	public void setQtdeDiasEncerraOSFiscalizacao(
			String qtdeDiasEncerraOSFiscalizacao) {
		this.qtdeDiasEncerraOSFiscalizacao = qtdeDiasEncerraOSFiscalizacao;
	}

	public String getQtdeDiasEnvioEmailConta() {
		return qtdeDiasEnvioEmailConta;
	}

	public void setQtdeDiasEnvioEmailConta(String qtdeDiasEnvioEmailConta) {
		this.qtdeDiasEnvioEmailConta = qtdeDiasEnvioEmailConta;
	}

	public String getQtdeDiasValidadeOSFiscalizacao() {
		return qtdeDiasValidadeOSFiscalizacao;
	}

	public void setQtdeDiasValidadeOSFiscalizacao(
			String qtdeDiasValidadeOSFiscalizacao) {
		this.qtdeDiasValidadeOSFiscalizacao = qtdeDiasValidadeOSFiscalizacao;
	}
	
	public String getDescricaoDecreto() {
		return descricaoDecreto;
	}

	public void setDescricaoDecreto(String descricaoDecreto) {
		this.descricaoDecreto = descricaoDecreto;
	}

	public FormFile getArquivoDecreto() {
		return arquivoDecreto;
	}

	public void setArquivoDecreto(FormFile arquivoDecreto) {
		this.arquivoDecreto = arquivoDecreto;
	}

	public FormFile getArquivoLeiEstTarif() {
		return arquivoLeiEstTarif;
	}

	public void setArquivoLeiEstTarif(FormFile arquivoLeiEstTarif) {
		this.arquivoLeiEstTarif = arquivoLeiEstTarif;
	}

	public FormFile getArquivoLeiIndividualizacao() {
		return arquivoLeiIndividualizacao;
	}

	public void setArquivoLeiIndividualizacao(FormFile arquivoLeiIndividualizacao) {
		this.arquivoLeiIndividualizacao = arquivoLeiIndividualizacao;
	}

	public FormFile getArquivoNormaCM() {
		return arquivoNormaCM;
	}

	public void setArquivoNormaCM(FormFile arquivoNormaCM) {
		this.arquivoNormaCM = arquivoNormaCM;
	}

	public FormFile getArquivoNormaCO() {
		return arquivoNormaCO;
	}

	public void setArquivoNormaCO(FormFile arquivoNormaCO) {
		this.arquivoNormaCO = arquivoNormaCO;
	}

	public String getDescricaoLeiEstTarif() {
		return descricaoLeiEstTarif;
	}

	public void setDescricaoLeiEstTarif(String descricaoLeiEstTarif) {
		this.descricaoLeiEstTarif = descricaoLeiEstTarif;
	}

	public String getDescricaoLeiIndividualizacao() {
		return descricaoLeiIndividualizacao;
	}

	public void setDescricaoLeiIndividualizacao(String descricaoLeiIndividualizacao) {
		this.descricaoLeiIndividualizacao = descricaoLeiIndividualizacao;
	}

	public String getDescricaoNormaCM() {
		return descricaoNormaCM;
	}

	public void setDescricaoNormaCM(String descricaoNormaCM) {
		this.descricaoNormaCM = descricaoNormaCM;
	}

	public String getDescricaoNormaCO() {
		return descricaoNormaCO;
	}

	public void setDescricaoNormaCO(String descricaoNormaCO) {
		this.descricaoNormaCO = descricaoNormaCO;
	}

	public String getIndicadorBloqueioDebitoACobrarContratoParcelDebito() {
		return indicadorBloqueioDebitoACobrarContratoParcelDebito;
	}

	public void setIndicadorBloqueioDebitoACobrarContratoParcelDebito(
			String indicadorBloqueioDebitoACobrarContratoParcelDebito) {
		this.indicadorBloqueioDebitoACobrarContratoParcelDebito = indicadorBloqueioDebitoACobrarContratoParcelDebito;
	}

	public String getIndicadorBloqueioDebitoACobrarContratoParcelManterDebito() {
		return indicadorBloqueioDebitoACobrarContratoParcelManterDebito;
	}

	public void setIndicadorBloqueioDebitoACobrarContratoParcelManterDebito(
			String indicadorBloqueioDebitoACobrarContratoParcelManterDebito) {
		this.indicadorBloqueioDebitoACobrarContratoParcelManterDebito = indicadorBloqueioDebitoACobrarContratoParcelManterDebito;
	}
	
	

	public String getCdDadosDiarios() {
		return cdDadosDiarios;
	}

	public void setCdDadosDiarios(String cdDadosDiarios) {
		this.cdDadosDiarios = cdDadosDiarios;
	}
	
	public String getNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo() {
		return numeroDiasEncerrarOsFiscalizacaoDecursoPrazo;
	}

	public void setNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo(
			String numeroDiasEncerrarOsFiscalizacaoDecursoPrazo) {
		this.numeroDiasEncerrarOsFiscalizacaoDecursoPrazo = numeroDiasEncerrarOsFiscalizacaoDecursoPrazo;
	}

	public String getClienteFicticioAssociarPagamentosNaoIdentificados() {
		return clienteFicticioAssociarPagamentosNaoIdentificados;
	}

	public void setClienteFicticioAssociarPagamentosNaoIdentificados(
			String clienteFicticioAssociarPagamentosNaoIdentificados) {
		this.clienteFicticioAssociarPagamentosNaoIdentificados = clienteFicticioAssociarPagamentosNaoIdentificados;
	}

	public String getNomeClienteFicticioAssociarPagamentosNaoIdentificados() {
		return nomeClienteFicticioAssociarPagamentosNaoIdentificados;
	}

	public void setNomeClienteFicticioAssociarPagamentosNaoIdentificados(
			String nomeClienteFicticioAssociarPagamentosNaoIdentificados) {
		this.nomeClienteFicticioAssociarPagamentosNaoIdentificados = nomeClienteFicticioAssociarPagamentosNaoIdentificados;
	}
}
