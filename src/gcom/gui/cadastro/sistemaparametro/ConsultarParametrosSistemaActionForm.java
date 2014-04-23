package gcom.gui.cadastro.sistemaparametro;

import org.apache.struts.action.ActionForm;

public class ConsultarParametrosSistemaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	// Dados Gerais
	private String nomeEstado;

	private String nomeEmpresa;

	private String abreviaturaEmpresa;

	private String cnpj;

	private String logradouro;

	private String nomeLogradouro;

	private String numero;

	private String complemento;

	private String bairro;

	private String cep;

	private String enderecoReferencia;

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

	private String site;

	private String inscricaoEstadual;

	private String inscricaoMunicipal;

	private String numeroContrato;

	private String imagemLogomarca;

	private String imagemRelatorio;

	private String logradouroBairro;

	private String logradouroCep;

	private String imagemConta;

	private String indicadorUsaRota;

	private String indicadorExibirMensagem;

	private String numeroExecucaoResumoNegativacao;

	private String indicadorControlaAutoInfracao;

	private String perfilProgramaEspecial;

	private String idClienteResponsavelPrograma;

	private String nomeClienteResponsavelPrograma;

	private String indicadorPopupAtualizacaoCadastral;

	private String percentualConvergenciaRepavimentacao;

	private String indicadorDocumentoObrigatorio;

	private String indicadorConsultaSpc;

	private String valorExtratoFichaComp;

	private String valorGuiaFichaComp;

	private String valorDemonstrativoParcelamentoFichaComp;

	private String indicadorUsoNMCliReceitaFantasia;
	
	private String indicadorVariaHierarquiaUnidade;
	
	private String clienteFicticioAssociarPagamentosNaoIdentificados;
	
	private String nomeClienteFicticioAssociarPagamentosNaoIdentificados;

	// Faturamento Tarifa Social
	private String mesAnoReferencia;

	private String menorConsumo;

	private String menorValor;

	private String qtdeEconomias;

	private String mesesCalculoMedio;

	private String diasMinimoVencimento;

	private String diasMinimoVencimentoCorreio;

	private String numeroMesesValidadeConta;

	private String numeroMesesAlteracaoVencimento;

	private String numeroMesesMaximoCalculoMedia;

	private String numeroMesesCalculoCorrecao;
	
	private String numeroVezesSuspendeLeitura;
	
	private String numeroMesesLeituraSuspensa;
	
	private String numeroMesesReinicioSitEspFatu;

	private String salarioMinimo;

	private String areaMaxima;

	private String consumoMaximo;

	private String indicadorLimiteAlteracaoVencimento;

	private String indicadorCalculoVencimento;

	private String indicadorTarifaCategoria;

	private String indicadorAtualizacaoTarifaria;

	private String mesAnoAtualizacaoTarifaria;

	private String indicadorFaturamentoAntecipado;

	private String indicadorRoteiroEmpresa;

	private String indicadorRetificacaoValorMenor;

	private String indicadorTransferenciaComDebito;

	private String indicadorNaoMedidoTarifa;

	private String numeroDiasVariacaoConsumo;

	private String indicadorQuadraFace;

	private String nnDiasPrazoRecursoAutoInfracao;

	private String diasVencimentoAlternativo;

	private String indicadorBloqueioContaMobile;

	private String valorContaFichaComp;

	private String numeroMesesRetificarConta;

	private String indicadorNormaRetificacao;

	private String mensagemContaBraile;

	private String codigoTipoCalculoNaoMedido;

	// Financeiro
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

	private String indicadorValorMovimentoArrecadador;

	private String numeroMesesAnterioresParaDeclaracaoQuitacao;

	// Micromedicao Cobranca
	private String DescricaoMenorCapacidade;

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
	
	private String indicadorTabelaPrice;

	private String CodigoMenorCapacidade;

	private String numeroDiasVencimentoEntradaParcelamento;

	private String idResolucaoDiretoria;

	private String numeroDiasEncerrarOsFiscalizacaoDecursoPrazo;
	
	// Seguranca
	private String indicadorSugestaoTramite;

	private String diasMaximoReativarRA;

	private String diasMaximoAlterarOS;

	private String ultimoIDGeracaoRA;

	private String diasMaximoExpirarAcesso;

	private String numeroDiasEncerramentoOrdemServico;

	private String numeroDiasEncerramentoOSSeletiva;

	private String numeroDiasAlteracaoVencimentoPosterior;

	private String diasMensagemExpiracaoSenha;

	private String numeroMaximoTentativasAcesso;

	private String numeroMaximoFavoritosMenu;

	private String ipServidorSmtp;

	private String ipServidorGerencial;

	private String emailResponsavel;

	private String mensagemSistema;

	private String indicadorDebitoACobrarValidoCertidaoNegativa;

	private String indicadorLoginUnico;

	private String indicadorDocumentoValido;

	private String diasVencimentoCertidaoNegativa;

	private String indicadorCertidaoNegativaEfeitoPositivo;

	private String indicadorControleTramitacaoRA;

	private String indicadorValidacaoLocalidadeEncerramentoOS;

	private String indicarControleExpiracaoSenhaPorGrupo;

	private String indicarControleBloqueioSenha;

	private String indicadorSenhaForte;

	private String idUnidadeDestinoGrandeConsumidor;

	private String nomeUnidadeDestinoGrandeConsumidor;

	private String numeroDiasRevisaoConta;

	private String numeroDiasBloqueioCelular;

	private String ultimoDiaVencimentoAlternativo;

	private String indicadorBloqueioContasContratoParcelDebitos;

	private String indicadorBloqueioContasContratoParcelManterConta;

	private String indicadorBloqueioGuiasOuAcresContratoParcelDebito;

	private String indicadorBloqueioGuiasOuAcresContratoParcelManterConta;

	private String numeroMaximoParcelasContratosParcelamento;
	
	private String qtdeDiasValidadeOSFiscalizacao;
	
	private String qtdeDiasEncerraOSFiscalizacao;
	
	private String qtdeDiasEnvioEmailConta;
	
	private String descricaoDecreto;
	
	private byte[] arquivoDecreto;
	
	private String descricaoLeiEstTarif;
	
	private byte[] arquivoLeiEstTarif;
	
	private String descricaoLeiIndividualizacao;
	
	private byte[] arquivoLeiIndividualizacao;
	
	private String descricaoNormaCO;
	
	private byte[] arquivoNormaCO;
	
	private String descricaoNormaCM;
	
	private byte[] arquivoNormaCM;

	public String getIndicadorControleTramitacaoRA() {
		return indicadorControleTramitacaoRA;
	}

	public void setIndicadorControleTramitacaoRA(
			String indicadorControleTramitacaoRA) {
		this.indicadorControleTramitacaoRA = indicadorControleTramitacaoRA;
	}

	public String getIndicadorCertidaoNegativaEfeitoPositivo() {
		return indicadorCertidaoNegativaEfeitoPositivo;
	}

	public void setIndicadorCertidaoNegativaEfeitoPositivo(
			String indicadorCertidaoNegativaEfeitoPositivo) {
		this.indicadorCertidaoNegativaEfeitoPositivo = indicadorCertidaoNegativaEfeitoPositivo;
	}

	public String getDiasVencimentoCertidaoNegativa() {
		return diasVencimentoCertidaoNegativa;
	}

	public void setDiasVencimentoCertidaoNegativa(
			String diasVencimentoCertidaoNegativa) {
		this.diasVencimentoCertidaoNegativa = diasVencimentoCertidaoNegativa;
	}

	public String getIndicadorDocumentoValido() {
		return indicadorDocumentoValido;
	}

	public void setIndicadorDocumentoValido(String indicadorDocumentoValido) {
		this.indicadorDocumentoValido = indicadorDocumentoValido;
	}

	/**
	 * @return Returns the emailResponsavel.
	 */
	public String getEmailResponsavel() {
		return emailResponsavel;
	}

	public String getAbreviaturaEmpresa() {
		return abreviaturaEmpresa;
	}

	public void setAbreviaturaEmpresa(String abreviaturaEmpresa) {
		this.abreviaturaEmpresa = abreviaturaEmpresa;
	}

	public String getAreaMaxima() {
		return areaMaxima;
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

	public void setAreaMaxima(String areaMaxima) {
		this.areaMaxima = areaMaxima;
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

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNumeroDiasVariacaoConsumo() {
		return numeroDiasVariacaoConsumo;
	}

	public void setNumeroDiasVariacaoConsumo(String numeroDiasVariacaoConsumo) {
		this.numeroDiasVariacaoConsumo = numeroDiasVariacaoConsumo;
	}

	public String getCodigoEmpresaFebraban() {
		return codigoEmpresaFebraban;
	}

	public void setCodigoEmpresaFebraban(String codigoEmpresaFebraban) {
		this.codigoEmpresaFebraban = codigoEmpresaFebraban;
	}

	public String getDescricaoMenorCapacidade() {
		return DescricaoMenorCapacidade;
	}

	public void setDescricaoMenorCapacidade(String descricaoMenorCapacidade) {
		DescricaoMenorCapacidade = descricaoMenorCapacidade;
	}

	public String getNumeroMesesMaximoCalculoMedia() {
		return numeroMesesMaximoCalculoMedia;
	}

	public void setNumeroMesesMaximoCalculoMedia(
			String numeroMesesMaximoCalculoMedia) {
		this.numeroMesesMaximoCalculoMedia = numeroMesesMaximoCalculoMedia;
	}

	public String getCodigoPeriodicidadeNegativacao() {
		return codigoPeriodicidadeNegativacao;
	}

	public void setCodigoPeriodicidadeNegativacao(
			String codigoPeriodicidadeNegativacao) {
		this.codigoPeriodicidadeNegativacao = codigoPeriodicidadeNegativacao;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getConsumoMaximo() {
		return consumoMaximo;
	}

	public void setConsumoMaximo(String consumoMaximo) {
		this.consumoMaximo = consumoMaximo;
	}

	public String getDecrementoMaximoConsumo() {
		return decrementoMaximoConsumo;
	}

	public void setDecrementoMaximoConsumo(String decrementoMaximoConsumo) {
		this.decrementoMaximoConsumo = decrementoMaximoConsumo;
	}

	public String getDiasMaximoAlterarOS() {
		return diasMaximoAlterarOS;
	}

	public void setDiasMaximoAlterarOS(String diasMaximoAlterarOS) {
		this.diasMaximoAlterarOS = diasMaximoAlterarOS;
	}

	public String getDiasMaximoExpirarAcesso() {
		return diasMaximoExpirarAcesso;
	}

	public void setDiasMaximoExpirarAcesso(String diasMaximoExpirarAcesso) {
		this.diasMaximoExpirarAcesso = diasMaximoExpirarAcesso;
	}

	public String getDiasMaximoReativarRA() {
		return diasMaximoReativarRA;
	}

	public void setDiasMaximoReativarRA(String diasMaximoReativarRA) {
		this.diasMaximoReativarRA = diasMaximoReativarRA;
	}

	public String getDiasMensagemExpiracaoSenha() {
		return diasMensagemExpiracaoSenha;
	}

	public void setDiasMensagemExpiracaoSenha(String diasMensagemExpiracaoSenha) {
		this.diasMensagemExpiracaoSenha = diasMensagemExpiracaoSenha;
	}

	public String getDiasMinimoVencimento() {
		return diasMinimoVencimento;
	}

	public void setDiasMinimoVencimento(String diasMinimoVencimento) {
		this.diasMinimoVencimento = diasMinimoVencimento;
	}

	public String getDiasMinimoVencimentoCorreio() {
		return diasMinimoVencimentoCorreio;
	}

	public void setDiasMinimoVencimentoCorreio(
			String diasMinimoVencimentoCorreio) {
		this.diasMinimoVencimentoCorreio = diasMinimoVencimentoCorreio;
	}

	public String getDiasVencimentoCobranca() {
		return diasVencimentoCobranca;
	}

	public void setDiasVencimentoCobranca(String diasVencimentoCobranca) {
		this.diasVencimentoCobranca = diasVencimentoCobranca;
	}

	public String getDiretorComercial() {
		return diretorComercial;
	}

	public void setDiretorComercial(String diretorComercial) {
		this.diretorComercial = diretorComercial;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnderecoReferencia() {
		return enderecoReferencia;
	}

	public void setEnderecoReferencia(String enderecoReferencia) {
		this.enderecoReferencia = enderecoReferencia;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
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

	public String getIncrementoMaximoConsumo() {
		return incrementoMaximoConsumo;
	}

	public void setIncrementoMaximoConsumo(String incrementoMaximoConsumo) {
		this.incrementoMaximoConsumo = incrementoMaximoConsumo;
	}

	public String getIndentificadorContaDevolucao() {
		return indentificadorContaDevolucao;
	}

	public void setIndentificadorContaDevolucao(
			String indentificadorContaDevolucao) {
		this.indentificadorContaDevolucao = indentificadorContaDevolucao;
	}

	public String getIndicadorAtualizacaoTarifaria() {
		return indicadorAtualizacaoTarifaria;
	}

	public void setIndicadorAtualizacaoTarifaria(
			String indicadorAtualizacaoTarifaria) {
		this.indicadorAtualizacaoTarifaria = indicadorAtualizacaoTarifaria;
	}

	public String getIndicadorCalculoVencimento() {
		return indicadorCalculoVencimento;
	}

	public void setIndicadorCalculoVencimento(String indicadorCalculoVencimento) {
		this.indicadorCalculoVencimento = indicadorCalculoVencimento;
	}

	public String getIndicadorCobrarTaxaExtrato() {
		return indicadorCobrarTaxaExtrato;
	}

	public void setIndicadorCobrarTaxaExtrato(String indicadorCobrarTaxaExtrato) {
		this.indicadorCobrarTaxaExtrato = indicadorCobrarTaxaExtrato;
	}

	public String getIndicadorFaturamentoAntecipado() {
		return indicadorFaturamentoAntecipado;
	}

	public void setIndicadorFaturamentoAntecipado(
			String indicadorFaturamentoAntecipado) {
		this.indicadorFaturamentoAntecipado = indicadorFaturamentoAntecipado;
	}

	public String getIndicadorGeracaoFaixaFalsa() {
		return indicadorGeracaoFaixaFalsa;
	}

	public void setIndicadorGeracaoFaixaFalsa(String indicadorGeracaoFaixaFalsa) {
		this.indicadorGeracaoFaixaFalsa = indicadorGeracaoFaixaFalsa;
	}

	public String getIndicadorGeracaoFiscalizacaoLeitura() {
		return indicadorGeracaoFiscalizacaoLeitura;
	}

	public void setIndicadorGeracaoFiscalizacaoLeitura(
			String indicadorGeracaoFiscalizacaoLeitura) {
		this.indicadorGeracaoFiscalizacaoLeitura = indicadorGeracaoFiscalizacaoLeitura;
	}

	public String getIndicadorLimiteAlteracaoVencimento() {
		return indicadorLimiteAlteracaoVencimento;
	}

	public void setIndicadorLimiteAlteracaoVencimento(
			String indicadorLimiteAlteracaoVencimento) {
		this.indicadorLimiteAlteracaoVencimento = indicadorLimiteAlteracaoVencimento;
	}

	public String getIndicadorPercentualGeracaoFaixaFalsa() {
		return indicadorPercentualGeracaoFaixaFalsa;
	}

	public void setIndicadorPercentualGeracaoFaixaFalsa(
			String indicadorPercentualGeracaoFaixaFalsa) {
		this.indicadorPercentualGeracaoFaixaFalsa = indicadorPercentualGeracaoFaixaFalsa;
	}

	public String getIndicadorPercentualGeracaoFiscalizacaoLeitura() {
		return indicadorPercentualGeracaoFiscalizacaoLeitura;
	}

	public void setIndicadorPercentualGeracaoFiscalizacaoLeitura(
			String indicadorPercentualGeracaoFiscalizacaoLeitura) {
		this.indicadorPercentualGeracaoFiscalizacaoLeitura = indicadorPercentualGeracaoFiscalizacaoLeitura;
	}

	public String getIndicadorSugestaoTramite() {
		return indicadorSugestaoTramite;
	}

	public void setIndicadorSugestaoTramite(String indicadorSugestaoTramite) {
		this.indicadorSugestaoTramite = indicadorSugestaoTramite;
	}

	public String getIndicadorTarifaCategoria() {
		return indicadorTarifaCategoria;
	}

	public void setIndicadorTarifaCategoria(String indicadorTarifaCategoria) {
		this.indicadorTarifaCategoria = indicadorTarifaCategoria;
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

	public String getIpServidorGerencial() {
		return ipServidorGerencial;
	}

	public void setIpServidorGerencial(String ipServidorGerencial) {
		this.ipServidorGerencial = ipServidorGerencial;
	}

	public String getIpServidorSmtp() {
		return ipServidorSmtp;
	}

	public void setIpServidorSmtp(String ipServidorSmtp) {
		this.ipServidorSmtp = ipServidorSmtp;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getMaximoParcelas() {
		return maximoParcelas;
	}

	public void setMaximoParcelas(String maximoParcelas) {
		this.maximoParcelas = maximoParcelas;
	}

	public String getMenorConsumo() {
		return menorConsumo;
	}

	public void setMenorConsumo(String menorConsumo) {
		this.menorConsumo = menorConsumo;
	}

	public String getMenorValor() {
		return menorValor;
	}

	public void setMenorValor(String menorValor) {
		this.menorValor = menorValor;
	}

	public String getMensagemSistema() {
		return mensagemSistema;
	}

	public void setMensagemSistema(String mensagemSistema) {
		this.mensagemSistema = mensagemSistema;
	}

	public String getMesAnoAtualizacaoTarifaria() {
		return mesAnoAtualizacaoTarifaria;
	}

	public void setMesAnoAtualizacaoTarifaria(String mesAnoAtualizacaoTarifaria) {
		this.mesAnoAtualizacaoTarifaria = mesAnoAtualizacaoTarifaria;
	}

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	public String getMesAnoReferenciaArrecadacao() {
		return mesAnoReferenciaArrecadacao;
	}

	public void setMesAnoReferenciaArrecadacao(
			String mesAnoReferenciaArrecadacao) {
		this.mesAnoReferenciaArrecadacao = mesAnoReferenciaArrecadacao;
	}

	public String getMesesCalculoMedio() {
		return mesesCalculoMedio;
	}

	public void setMesesCalculoMedio(String mesesCalculoMedio) {
		this.mesesCalculoMedio = mesesCalculoMedio;
	}

	public String getNomeDiretorComercial() {
		return nomeDiretorComercial;
	}

	public void setNomeDiretorComercial(String nomeDiretorComercial) {
		this.nomeDiretorComercial = nomeDiretorComercial;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getNomeEstado() {
		return nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

	public String getNomeLogradouro() {
		return nomeLogradouro;
	}

	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}

	public String getNomePresidente() {
		return nomePresidente;
	}

	public void setNomePresidente(String nomePresidente) {
		this.nomePresidente = nomePresidente;
	}

	public String getNomeUnidadeOrganizacionalPresidencia() {
		return nomeUnidadeOrganizacionalPresidencia;
	}

	public void setNomeUnidadeOrganizacionalPresidencia(
			String nomeUnidadeOrganizacionalPresidencia) {
		this.nomeUnidadeOrganizacionalPresidencia = nomeUnidadeOrganizacionalPresidencia;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getNumeroDiasCalculoAcrescimos() {
		return numeroDiasCalculoAcrescimos;
	}

	public void setNumeroDiasCalculoAcrescimos(
			String numeroDiasCalculoAcrescimos) {
		this.numeroDiasCalculoAcrescimos = numeroDiasCalculoAcrescimos;
	}

	public String getNumeroLayOut() {
		return numeroLayOut;
	}

	public void setNumeroLayOut(String numeroLayOut) {
		this.numeroLayOut = numeroLayOut;
	}

	public String getNumeroMaximoFavoritosMenu() {
		return numeroMaximoFavoritosMenu;
	}

	public void setNumeroMaximoFavoritosMenu(String numeroMaximoFavoritosMenu) {
		this.numeroMaximoFavoritosMenu = numeroMaximoFavoritosMenu;
	}

	public String getNumeroMaximoMesesSancoes() {
		return numeroMaximoMesesSancoes;
	}

	public void setNumeroMaximoMesesSancoes(String numeroMaximoMesesSancoes) {
		this.numeroMaximoMesesSancoes = numeroMaximoMesesSancoes;
	}

	public String getNumeroMaximoParcelaCredito() {
		return numeroMaximoParcelaCredito;
	}

	public void setNumeroMaximoParcelaCredito(String numeroMaximoParcelaCredito) {
		this.numeroMaximoParcelaCredito = numeroMaximoParcelaCredito;
	}

	public String getNumeroMaximoTentativasAcesso() {
		return numeroMaximoTentativasAcesso;
	}

	public void setNumeroMaximoTentativasAcesso(
			String numeroMaximoTentativasAcesso) {
		this.numeroMaximoTentativasAcesso = numeroMaximoTentativasAcesso;
	}

	public String getNumeroMesesAlteracaoVencimento() {
		return numeroMesesAlteracaoVencimento;
	}

	public void setNumeroMesesAlteracaoVencimento(
			String numeroMesesAlteracaoVencimento) {
		this.numeroMesesAlteracaoVencimento = numeroMesesAlteracaoVencimento;
	}

	public String getNumeroMesesValidadeConta() {
		return numeroMesesValidadeConta;
	}

	public void setNumeroMesesValidadeConta(String numeroMesesValidadeConta) {
		this.numeroMesesValidadeConta = numeroMesesValidadeConta;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public String getNumeroTelefoneAtendimento() {
		return numeroTelefoneAtendimento;
	}

	public void setNumeroTelefoneAtendimento(String numeroTelefoneAtendimento) {
		this.numeroTelefoneAtendimento = numeroTelefoneAtendimento;
	}

	public String getPercentualCalculoIndice() {
		return percentualCalculoIndice;
	}

	public void setPercentualCalculoIndice(String percentualCalculoIndice) {
		this.percentualCalculoIndice = percentualCalculoIndice;
	}

	public String getPercentualEntradaMinima() {
		return percentualEntradaMinima;
	}

	public void setPercentualEntradaMinima(String percentualEntradaMinima) {
		this.percentualEntradaMinima = percentualEntradaMinima;
	}

	public String getPercentualGeracaoFaixaFalsa() {
		return percentualGeracaoFaixaFalsa;
	}

	public void setPercentualGeracaoFaixaFalsa(
			String percentualGeracaoFaixaFalsa) {
		this.percentualGeracaoFaixaFalsa = percentualGeracaoFaixaFalsa;
	}

	public String getPercentualGeracaoFiscalizacaoLeitura() {
		return percentualGeracaoFiscalizacaoLeitura;
	}

	public void setPercentualGeracaoFiscalizacaoLeitura(
			String percentualGeracaoFiscalizacaoLeitura) {
		this.percentualGeracaoFiscalizacaoLeitura = percentualGeracaoFiscalizacaoLeitura;
	}

	public String getPercentualMaximoAbatimento() {
		return percentualMaximoAbatimento;
	}

	public void setPercentualMaximoAbatimento(String percentualMaximoAbatimento) {
		this.percentualMaximoAbatimento = percentualMaximoAbatimento;
	}

	public String getPercentualTaxaFinanciamento() {
		return percentualTaxaFinanciamento;
	}

	public void setPercentualTaxaFinanciamento(
			String percentualTaxaFinanciamento) {
		this.percentualTaxaFinanciamento = percentualTaxaFinanciamento;
	}

	public String getPercentualToleranciaRateioConsumo() {
		return percentualToleranciaRateioConsumo;
	}

	public void setPercentualToleranciaRateioConsumo(
			String percentualToleranciaRateioConsumo) {
		this.percentualToleranciaRateioConsumo = percentualToleranciaRateioConsumo;
	}

	public String getPresidente() {
		return presidente;
	}

	public void setPresidente(String presidente) {
		this.presidente = presidente;
	}

	public String getQtdeEconomias() {
		return qtdeEconomias;
	}

	public void setQtdeEconomias(String qtdeEconomias) {
		this.qtdeEconomias = qtdeEconomias;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public String getSalarioMinimo() {
		return salarioMinimo;
	}

	public void setSalarioMinimo(String salarioMinimo) {
		this.salarioMinimo = salarioMinimo;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getTitulosRelatorio() {
		return titulosRelatorio;
	}

	public void setTitulosRelatorio(String titulosRelatorio) {
		this.titulosRelatorio = titulosRelatorio;
	}

	public String getUltimoIDGeracaoRA() {
		return ultimoIDGeracaoRA;
	}

	public void setUltimoIDGeracaoRA(String ultimoIDGeracaoRA) {
		this.ultimoIDGeracaoRA = ultimoIDGeracaoRA;
	}

	public String getUnidadeOrganizacionalPresidencia() {
		return unidadeOrganizacionalPresidencia;
	}

	public void setUnidadeOrganizacionalPresidencia(
			String unidadeOrganizacionalPresidencia) {
		this.unidadeOrganizacionalPresidencia = unidadeOrganizacionalPresidencia;
	}

	public String getValorSegundaVia() {
		return valorSegundaVia;
	}

	public void setValorSegundaVia(String valorSegundaVia) {
		this.valorSegundaVia = valorSegundaVia;
	}

	public void setEmailResponsavel(String emailResponsavel) {
		this.emailResponsavel = emailResponsavel;
	}

	public String getIndicadorRoteiroEmpresa() {
		return indicadorRoteiroEmpresa;
	}

	public void setIndicadorRoteiroEmpresa(String indicadorRoteiroEmpresa) {
		this.indicadorRoteiroEmpresa = indicadorRoteiroEmpresa;
	}

	public String getNumeroDiasAlteracaoVencimentoPosterior() {
		return numeroDiasAlteracaoVencimentoPosterior;
	}

	public void setNumeroDiasAlteracaoVencimentoPosterior(
			String numeroDiasAlteracaoVencimentoPosterior) {
		this.numeroDiasAlteracaoVencimentoPosterior = numeroDiasAlteracaoVencimentoPosterior;
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

	public String getIndicadorExibirMensagem() {
		return indicadorExibirMensagem;
	}

	public void setIndicadorExibirMensagem(String indicadorExibirMensagem) {
		this.indicadorExibirMensagem = indicadorExibirMensagem;
	}

	public String getIndicadorRetificacaoValorMenor() {
		return indicadorRetificacaoValorMenor;
	}

	public void setIndicadorRetificacaoValorMenor(
			String indicadorRetificacaoValorMenor) {
		this.indicadorRetificacaoValorMenor = indicadorRetificacaoValorMenor;
	}

	public String getIndicadorNaoMedidoTarifa() {
		return indicadorNaoMedidoTarifa;
	}

	public void setIndicadorNaoMedidoTarifa(String indicadorNaoMedidoTarifa) {
		this.indicadorNaoMedidoTarifa = indicadorNaoMedidoTarifa;
	}

	public String getIndicadorTransferenciaComDebito() {
		return indicadorTransferenciaComDebito;
	}

	public void setIndicadorTransferenciaComDebito(
			String indicadorTransferenciaComDebito) {
		this.indicadorTransferenciaComDebito = indicadorTransferenciaComDebito;
	}

	public String getIndicadorParcelamentoConfirmado() {
		return indicadorParcelamentoConfirmado;
	}

	public void setIndicadorParcelamentoConfirmado(
			String indicadorParcelamentoConfirmado) {
		this.indicadorParcelamentoConfirmado = indicadorParcelamentoConfirmado;
	}
	
	
	public String getindicadorTabelaPrice() {
		return indicadorTabelaPrice;
	}

	public void setindicadorTabelaPrice(
			String indicadorTabelaPrice) {
		this.indicadorTabelaPrice = indicadorTabelaPrice;
	}

	/**
	 * @return Retorna o campo codigoMenorCapacidade.
	 */
	public String getCodigoMenorCapacidade() {
		return CodigoMenorCapacidade;
	}

	/**
	 * @param codigoMenorCapacidade
	 *            O codigoMenorCapacidade a ser setado.
	 */
	public void setCodigoMenorCapacidade(String codigoMenorCapacidade) {
		CodigoMenorCapacidade = codigoMenorCapacidade;
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

	/**
	 * @return Returns the diasVencimentoAlternativo.
	 */
	public String getDiasVencimentoAlternativo() {
		return diasVencimentoAlternativo;
	}

	/**
	 * @param diasVencimentoAlternativo
	 *            The diasVencimentoAlternativo to set.
	 */
	public void setDiasVencimentoAlternativo(String diasVencimentoAlternativo) {
		this.diasVencimentoAlternativo = diasVencimentoAlternativo;
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

	public String getIdClienteResponsavelPrograma() {
		return idClienteResponsavelPrograma;
	}

	public void setIdClienteResponsavelPrograma(
			String idClienteResponsavelPrograma) {
		this.idClienteResponsavelPrograma = idClienteResponsavelPrograma;
	}

	public String getNomeClienteResponsavelPrograma() {
		return nomeClienteResponsavelPrograma;
	}

	public void setNomeClienteResponsavelPrograma(
			String nomeClienteResponsavelPrograma) {
		this.nomeClienteResponsavelPrograma = nomeClienteResponsavelPrograma;
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

	public String getIndicadorPopupAtualizacaoCadastral() {
		return indicadorPopupAtualizacaoCadastral;
	}

	public void setIndicadorPopupAtualizacaoCadastral(
			String indicadorPopupAtualizacaoCadastral) {
		this.indicadorPopupAtualizacaoCadastral = indicadorPopupAtualizacaoCadastral;
	}

	public String getIdResolucaoDiretoria() {
		return idResolucaoDiretoria;
	}

	public void setIdResolucaoDiretoria(String idResolucaoDiretoria) {
		this.idResolucaoDiretoria = idResolucaoDiretoria;
	}

	public String getValorContaFichaComp() {
		return valorContaFichaComp;
	}

	public void setValorContaFichaComp(String valorContaFichaComp) {
		this.valorContaFichaComp = valorContaFichaComp;
	}

	public String getValorExtratoFichaComp() {
		return valorExtratoFichaComp;
	}

	public void setValorExtratoFichaComp(String valorExtratoFichaComp) {
		this.valorExtratoFichaComp = valorExtratoFichaComp;
	}

	public String getNumeroDiasBloqueioCelular() {
		return numeroDiasBloqueioCelular;
	}

	public void setNumeroDiasBloqueioCelular(String numeroDiasBloqueioCelular) {
		this.numeroDiasBloqueioCelular = numeroDiasBloqueioCelular;
	}

	public String getIndicadorConsultaSpc() {
		return indicadorConsultaSpc;
	}

	public void setIndicadorConsultaSpc(String indicadorConsultaSpc) {
		this.indicadorConsultaSpc = indicadorConsultaSpc;
	}

	public String getUltimoDiaVencimentoAlternativo() {
		return ultimoDiaVencimentoAlternativo;
	}

	public void setUltimoDiaVencimentoAlternativo(
			String ultimoDiaVencimentoAlternativo) {
		this.ultimoDiaVencimentoAlternativo = ultimoDiaVencimentoAlternativo;
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

	public String getIndicadorUsoNMCliReceitaFantasia() {
		return indicadorUsoNMCliReceitaFantasia;
	}

	public void setIndicadorUsoNMCliReceitaFantasia(
			String indicadorUsoNMCliReceitaFantasia) {
		this.indicadorUsoNMCliReceitaFantasia = indicadorUsoNMCliReceitaFantasia;
	}

	public String getValorDemonstrativoParcelamentoFichaComp() {
		return valorDemonstrativoParcelamentoFichaComp;
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

	public String getNumeroMaximoParcelasContratosParcelamento() {
		return numeroMaximoParcelasContratosParcelamento;
	}

	public void setNumeroMaximoParcelasContratosParcelamento(
			String numeroMaximoParcelasContratosParcelamento) {
		this.numeroMaximoParcelasContratosParcelamento = numeroMaximoParcelasContratosParcelamento;
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

	public String getIndicadorBloqueioGuiasOuAcresContratoParcelManterConta() {
		return indicadorBloqueioGuiasOuAcresContratoParcelManterConta;
	}

	public void setIndicadorBloqueioGuiasOuAcresContratoParcelManterConta(
			String indicadorBloqueioGuiasOuAcresContratoParcelManterConta) {
		this.indicadorBloqueioGuiasOuAcresContratoParcelManterConta = indicadorBloqueioGuiasOuAcresContratoParcelManterConta;
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

	public byte[] getArquivoDecreto() {
		return arquivoDecreto;
	}

	public void setArquivoDecreto(byte[] arquivoDecreto) {
		this.arquivoDecreto = arquivoDecreto;
	}

	public byte[] getArquivoLeiEstTarif() {
		return arquivoLeiEstTarif;
	}

	public void setArquivoLeiEstTarif(byte[] arquivoLeiEstTarif) {
		this.arquivoLeiEstTarif = arquivoLeiEstTarif;
	}

	public byte[] getArquivoLeiIndividualizacao() {
		return arquivoLeiIndividualizacao;
	}

	public void setArquivoLeiIndividualizacao(byte[] arquivoLeiIndividualizacao) {
		this.arquivoLeiIndividualizacao = arquivoLeiIndividualizacao;
	}

	public byte[] getArquivoNormaCM() {
		return arquivoNormaCM;
	}

	public void setArquivoNormaCM(byte[] arquivoNormaCM) {
		this.arquivoNormaCM = arquivoNormaCM;
	}

	public byte[] getArquivoNormaCO() {
		return arquivoNormaCO;
	}

	public void setArquivoNormaCO(byte[] arquivoNormaCO) {
		this.arquivoNormaCO = arquivoNormaCO;
	}

	public String getDescricaoDecreto() {
		return descricaoDecreto;
	}

	public void setDescricaoDecreto(String descricaoDecreto) {
		this.descricaoDecreto = descricaoDecreto;
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
