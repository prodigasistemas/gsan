package gcom.relatorio.cobranca;

import gcom.gui.cadastro.imovel.ImovelOutrosCriteriosActionForm;

/**
 * Helper para a relação de imoveis com debitoss
 * 
 * @author Bruno Barros
 * @date 17/03/2008
 * 
 */
public class RelatorioGerarRelacaoDebitosHelper {
	
	private String gerenciaRegional;
	// numero da quadra Inicial
	private String quadraInicial;
	// numero quadra Final
	private String quadraFinal;
	// lote Inicial
	private String loteInicial;
	// lote Final
	private String loteFinal;
	// cep
	private String cep;
	// id localidade Inicial
	private String localidadeInicial;
	// id localidade Final
	private String localidadeFinal;
	// setor comercial Inicial CD
	private String setorComercialInicialCD;
	// setor comercial Final CD
	private String setorComercialFinalCD;
	// cliente ID
	private String clienteID;
	// municipio ID
	private String municipioID;
	// bairro ID
	private String bairroID;
	// logradouro ID
	private String logradouroID;
	// cliente relacao tipo ID
	private String clienteRelacaoTipoID;
	// cliente tipo ID
	private String clienteTipoID;
	// imovel condominio ID
	private String imovelCondominioID;
	// imovel Principal ID
	private String imovelPrincipalID;
	// nome Conta ID
	private String nomeContaID;
	// situacao Agua
	private String situacaoAgua;
	// situacao Ligacao Esgoto
	private String situacaoLigacaoEsgoto;
	// consumo Minimo Inicial
	private String consumoMinimoInicial;
	// consumo Minimo Final
	private String consumoMinimoFinal;
	// consumo Minimo Fixado Esgoto Inicial
	private String consumoMinimoFixadoEsgotoInicial;
	// consumo Minimo Fixado Esgoto Final
	private String consumoMinimoFixadoEsgotoFinal;
	// intervalo Percentual Esgoto Inicial
	private String intervaloPercentualEsgotoInicial;
	// intervalor Percentual Esgoto Final
	private String intervaloPercentualEsgotoFinal;
	// indicador Medicao
	private String indicadorMedicao;
	// tipo Medicao ID
	private String tipoMedicaoID;
	// intervalo Media Minima Imovel Inicial
	private String intervaloMediaMinimaImovelInicial;
	// intervalo Media Minima Imovel Final
	private String intervaloMediaMinimaImoveFinal;
	// intervalo Media Minima Hidrometro Inicial
	private String intervaloMediaMinimaHidrometroInicial;
	// intervalo Media Minima Hidrometro Final
	private String intervaloMediaMinimaHidrometroFinal;
	// perfil Imovel ID
	private String perfilImovelID;
	// categoria Imovel ID
	private String categoriaImovelID;
	// sub categoria ID
	private String subCategoriaID;
	// quantidade Economias Inicial
	private String quantidadeEconomiasInicial;
	// quantidade Economias Final
	private String quantidadeEconomiasFinal;
	// numero Pontos Inicial
	private String numeroPontosInicial;
	// numero Pontos Final
	private String numeroPontosFinal;
	// numero Moradores Inicial
	private String numeroMoradoresInicial;
	// numero Moradoras Final
	private String numeroMoradoresFinal;
	// area Construida Inicial
	private String areaConstruidaInicial;
	// area Construida Final
	private String areaConstruidaFinal;
	// area Construida Faixa
	private String areaConstruidaFaixa;
	// poco Tipo ID
	private String pocoTipoID;
	// tipo Situacao Faturamento ID
	private String tipoSituacaoFaturamentoID;
	// tipo Situacao Especial Cobranca ID
	private String tipoSituacaoEspecialCobrancaID;
	// situacao Cobranca ID
	private String situacaoCobrancaID;
	// dia Vencimento Alternativo
	private String diaVencimentoAlternativo;
	// ocorrencia Cadastro
	private String ocorrenciaCadastro;
	// tarifa Consumo
	private String tarifaConsumo;
	// anormalidade Elo
	private String anormalidadeElo;
	
	//Aba de Débito
	String[] tipoDebito;
	private String valorDebitoInicial;
	private String valorDebitoFinal;
	private String qtdContasInicial;
	private String qtdContasFinal;
	private String referenciaFaturaInicial;
	private String referenciaFaturaFinal;
	private String vencimentoInicial;
	private String vencimentoFinal;
	private String qtdImoveis;
	private String qtdMaiores;
	
	public RelatorioGerarRelacaoDebitosHelper( ImovelOutrosCriteriosActionForm actionForm ){
		// id da genrencia regional
		this.gerenciaRegional = actionForm.getIdGerenciaRegional();
		// numero da quadra Inicial
		this.quadraInicial = actionForm.getQuadraOrigemNM();
		// numero quadra Final
		this.quadraFinal = actionForm.getQuadraDestinoNM();
		// lote Inicial
		this.loteInicial = actionForm.getLoteOrigem();
		// lote Final
		this.loteFinal = actionForm.getLoteDestino();
		// cep
		this.cep = actionForm.getCEP();
		// id localidade Inicial
		this.localidadeInicial = actionForm.getLocalidadeOrigemID();
		// id localidade Final
		this.localidadeFinal = actionForm.getLocalidadeDestinoID();
		// setor comercial Inicial ID
		this.setorComercialInicialCD = actionForm.getSetorComercialOrigemCD();
		// setor comercial Final ID
		this.setorComercialFinalCD = actionForm.getSetorComercialDestinoCD();
		// cliente ID
		this.clienteID = actionForm.getIdCliente();
		// municipio ID
		this.municipioID = actionForm.getIdMunicipio();
		// bairro ID
		this.bairroID = actionForm.getIdBairro();
		// logradouro ID
		this.logradouroID = actionForm.getIdLogradouro();
		
		// cliente tipo ID
		this.clienteTipoID = actionForm.getDescricao();
		
		// cliente relacao tipo ID
		this.clienteRelacaoTipoID = actionForm.getIndicadorUso();
		
		// imovel condominio ID
		this.imovelCondominioID = actionForm.getIdImovelCondominio();
		// imovel Principal ID
		this.imovelPrincipalID = actionForm.getIdImovelPrincipal();
		// nome Conta ID
		this.nomeContaID = actionForm.getIdNomeConta();
		// situacao ligacao Agua
		this.situacaoAgua = actionForm.getSituacaoAgua();
		// consumo Minimo Inicial agua
		this.consumoMinimoInicial = actionForm.getConsumoMinimoInicial();
		// consumo Minimo Final agua
		this.consumoMinimoFinal = actionForm.getConsumoMinimoFinal();

		// situacao Ligacao Esgoto
		this.situacaoLigacaoEsgoto = actionForm.getSituacaoLigacaoEsgoto();
		// consumo Minimo Fixado Esgoto Inicial
		this.consumoMinimoFixadoEsgotoInicial = actionForm
						.getConsumoMinimoFixadoEsgotoInicial();
		// consumo Minimo Fixado Esgoto Final
		this.consumoMinimoFixadoEsgotoFinal = actionForm
						.getConsumoMinimoFixadoEsgotoFinal();

		// intervalo Percentual Esgoto Inicial
		this.intervaloPercentualEsgotoInicial = actionForm
						.getIntervaloPercentualEsgotoInicial();
		// intervalor Percentual Esgoto Final
		this.intervaloPercentualEsgotoFinal = actionForm
						.getIntervaloPercentualEsgotoFinal();
		// indicador Medicao
		this.indicadorMedicao = actionForm.getIndicadorMedicao();
		// tipo Medicao ID
		this.tipoMedicaoID = actionForm.getTipoMedicao();
		// intervalo Media Minima Imovel Inicial
		this.intervaloMediaMinimaImovelInicial = actionForm
						.getIntervaloMediaMinimaImovelInicio();
		// intervalo Media Minima Imovel Final
		this.intervaloMediaMinimaImoveFinal = actionForm
						.getIntervaloMediaMinimaImovelFinal();
		// intervalo Media Minima Hidrometro Inicial
		this.intervaloMediaMinimaHidrometroInicial = actionForm
						.getIntervaloMediaMinimaHidrometroInicio();
		// intervalo Media Minima Hidrometro Final
		this.intervaloMediaMinimaHidrometroFinal = actionForm
						.getIntervaloMediaMinimaHidrometroFinal();
		// perfil Imovel ID
		this.perfilImovelID = actionForm.getPerfilImovel();
		// categoria Imovel ID
		this.categoriaImovelID = actionForm.getCategoriaImovel();
		// sub categoria ID
		this.subCategoriaID = actionForm.getSubcategoria();
		// quantidade Economias Inicial
		this.quantidadeEconomiasInicial = actionForm.getQuantidadeEconomiasInicial();
		// quantidade Economias Final
		this.quantidadeEconomiasFinal = actionForm.getQuantidadeEconomiasFinal();
		// numero Pontos Inicial
		this.numeroPontosInicial = actionForm.getNumeroPontosInicial();
		// numero Pontos Final
		this.numeroPontosFinal = actionForm.getNumeroPontosFinal();
		// numero Moradores Inicial
		this.numeroMoradoresInicial = actionForm.getNumeroMoradoresInicial();
		// numero Moradoras Final
		this.numeroMoradoresFinal = actionForm.getNumeroMoradoresFinal();
		// area Construida Inicial
		this.areaConstruidaInicial = actionForm.getAreaConstruidaInicial();
		// area Construida Final
		this.areaConstruidaFinal = actionForm.getAreaConstruidaFinal();
		// area Construida Faixa
		this.areaConstruidaFaixa = actionForm.getAreaConstruidaFaixa();
		// poco Tipo ID
		this.pocoTipoID = actionForm.getTipoPoco();
		// tipo Situacao Faturamento ID
		this.tipoSituacaoFaturamentoID = actionForm
						.getTipoSituacaoEspecialFaturamento();
		// tipo Situacao Especial Cobranca ID
		this.tipoSituacaoEspecialCobrancaID = actionForm
						.getTipoSituacaoEspecialCobranca();
		// situacao Cobranca ID
		this.situacaoCobrancaID = actionForm.getSituacaoCobranca();
		// dia Vencimento Alternativo
		this.diaVencimentoAlternativo = actionForm.getDiaVencimentoAlternativo();
		// ocorrencia Cadastro
		this.ocorrenciaCadastro = actionForm.getOcorrenciaCadastro();
		// tarifa Consumo
		this.tarifaConsumo = actionForm.getTarifaConsumo();
		// anormalidade Elo
		this.anormalidadeElo = actionForm.getAnormalidadeElo();
		
		//Aba de Débito
		this.tipoDebito = actionForm.getTipoDebito();
		this.valorDebitoInicial = actionForm.getValorDebitoInicial();
		this.valorDebitoFinal = actionForm.getValorDebitoFinal();
		this.qtdContasInicial = actionForm.getQtdContasInicial();
		this.qtdContasFinal = actionForm.getQtdContasFinal();
		this.referenciaFaturaInicial = actionForm.getReferenciaFaturaInicial();
		this.referenciaFaturaFinal = actionForm.getReferenciaFaturaFinal();		
		this.vencimentoInicial = actionForm.getVencimentoInicial();
		this.vencimentoFinal = actionForm.getVencimentoFinal();		
		this.qtdImoveis = actionForm.getQtdImoveis();
		this.qtdMaiores = actionForm.getQtdMaiores();
		
	}

	
	public String getAnormalidadeElo() {
	
		return anormalidadeElo;
	}

	
	public String getAreaConstruidaFaixa() {
	
		return areaConstruidaFaixa;
	}

	
	public String getAreaConstruidaFinal() {
	
		return areaConstruidaFinal;
	}

	
	public String getAreaConstruidaInicial() {
	
		return areaConstruidaInicial;
	}

	
	public String getBairroID() {
	
		return bairroID;
	}

	
	public String getCategoriaImovelID() {
	
		return categoriaImovelID;
	}

	
	public String getCep() {
	
		return cep;
	}

	
	public String getClienteID() {
	
		return clienteID;
	}

	
	public String getClienteRelacaoTipoID() {
	
		return clienteRelacaoTipoID;
	}

	
	public String getClienteTipoID() {
	
		return clienteTipoID;
	}

	
	public String getConsumoMinimoFinal() {
	
		return consumoMinimoFinal;
	}

	
	public String getConsumoMinimoFixadoEsgotoFinal() {
	
		return consumoMinimoFixadoEsgotoFinal;
	}

	
	public String getConsumoMinimoFixadoEsgotoInicial() {
	
		return consumoMinimoFixadoEsgotoInicial;
	}

	
	public String getConsumoMinimoInicial() {
	
		return consumoMinimoInicial;
	}

	
	public String getDiaVencimentoAlternativo() {
	
		return diaVencimentoAlternativo;
	}

	
	public String getGerenciaRegional() {
	
		return gerenciaRegional;
	}

	
	public String getImovelCondominioID() {
	
		return imovelCondominioID;
	}

	
	public String getImovelPrincipalID() {
	
		return imovelPrincipalID;
	}

	
	public String getIndicadorMedicao() {
	
		return indicadorMedicao;
	}

	
	public String getIntervaloMediaMinimaHidrometroFinal() {
	
		return intervaloMediaMinimaHidrometroFinal;
	}

	
	public String getIntervaloMediaMinimaHidrometroInicial() {
	
		return intervaloMediaMinimaHidrometroInicial;
	}

	
	public String getIntervaloMediaMinimaImoveFinal() {
	
		return intervaloMediaMinimaImoveFinal;
	}

	
	public String getIntervaloMediaMinimaImovelInicial() {
	
		return intervaloMediaMinimaImovelInicial;
	}

	
	public String getIntervaloPercentualEsgotoFinal() {
	
		return intervaloPercentualEsgotoFinal;
	}

	
	public String getIntervaloPercentualEsgotoInicial() {
	
		return intervaloPercentualEsgotoInicial;
	}

	
	public String getLocalidadeFinal() {
	
		return localidadeFinal;
	}

	
	public String getLocalidadeInicial() {
	
		return localidadeInicial;
	}

	
	public String getLogradouroID() {
	
		return logradouroID;
	}

	
	public String getLoteFinal() {
	
		return loteFinal;
	}

	
	public String getLoteInicial() {
	
		return loteInicial;
	}

	
	public String getMunicipioID() {
	
		return municipioID;
	}

	
	public String getNomeContaID() {
	
		return nomeContaID;
	}

	
	public String getNumeroMoradoresFinal() {
	
		return numeroMoradoresFinal;
	}

	
	public String getNumeroMoradoresInicial() {
	
		return numeroMoradoresInicial;
	}

	
	public String getNumeroPontosFinal() {
	
		return numeroPontosFinal;
	}

	
	public String getNumeroPontosInicial() {
	
		return numeroPontosInicial;
	}

	
	public String getOcorrenciaCadastro() {
	
		return ocorrenciaCadastro;
	}

	
	public String getPerfilImovelID() {
	
		return perfilImovelID;
	}

	
	public String getPocoTipoID() {
	
		return pocoTipoID;
	}

	
	public String getQtdContasFinal() {
	
		return qtdContasFinal;
	}

	
	public String getQtdContasInicial() {
	
		return qtdContasInicial;
	}

	
	public String getQtdImoveis() {
	
		return qtdImoveis;
	}

	
	public String getQtdMaiores() {
	
		return qtdMaiores;
	}

	
	public String getQuadraFinal() {
	
		return quadraFinal;
	}

	
	public String getQuadraInicial() {
	
		return quadraInicial;
	}

	
	public String getQuantidadeEconomiasFinal() {
	
		return quantidadeEconomiasFinal;
	}

	
	public String getQuantidadeEconomiasInicial() {
	
		return quantidadeEconomiasInicial;
	}

	
	public String getReferenciaFaturaFinal() {
	
		return referenciaFaturaFinal;
	}

	
	public String getReferenciaFaturaInicial() {
	
		return referenciaFaturaInicial;
	}

	
	public String getSetorComercialFinalCD() {
	
		return setorComercialFinalCD;
	}

	
	public String getSetorComercialInicialCD() {
	
		return setorComercialInicialCD;
	}

	
	public String getSituacaoAgua() {
	
		return situacaoAgua;
	}

	
	public String getSituacaoCobrancaID() {
	
		return situacaoCobrancaID;
	}

	
	public String getSituacaoLigacaoEsgoto() {
	
		return situacaoLigacaoEsgoto;
	}

	
	public String getSubCategoriaID() {
	
		return subCategoriaID;
	}

	
	public String getTarifaConsumo() {
	
		return tarifaConsumo;
	}

	
	public String[] getTipoDebito() {
	
		return tipoDebito;
	}

	
	public String getTipoMedicaoID() {
	
		return tipoMedicaoID;
	}

	
	public String getTipoSituacaoEspecialCobrancaID() {
	
		return tipoSituacaoEspecialCobrancaID;
	}

	
	public String getTipoSituacaoFaturamentoID() {
	
		return tipoSituacaoFaturamentoID;
	}

	
	public String getValorDebitoFinal() {
	
		return valorDebitoFinal;
	}

	
	public String getValorDebitoInicial() {
	
		return valorDebitoInicial;
	}

	
	public String getVencimentoFinal() {
	
		return vencimentoFinal;
	}

	
	public String getVencimentoInicial() {
	
		return vencimentoInicial;
	}	
}
