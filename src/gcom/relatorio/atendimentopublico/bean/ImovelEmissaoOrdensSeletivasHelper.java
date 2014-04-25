package gcom.relatorio.atendimentopublico.bean;

/**
 * Classe para encapsular os parâmetros que servirão de filtro para geração do relatório
 * das ordens seletivas
 *
 * @author Raphael Rossiter
 * @date 16/04/2009
 */
public class ImovelEmissaoOrdensSeletivasHelper {

	private String tipoOrdem;
	
	private String firma;
	
	private String nomeFirma;
	
	private String quantidadeMaxima;
	
	private String idImovel;
	
	private String elo;
	
	private String nomeElo;
	
	private String logradouro;
	
	private String descricaoLogradouro;
	
	private String gerenciaRegional;
	
	private String nomeGerenciaRegional;
	
	private String unidadeNegocio;
	
	private String nomeUnidadeNegocio;
	
	private String localidadeInicial;
	
	private String nomeLocalidadeInicial;
	
	private String localidadeFinal;
	
	private String nomeLocalidadeFinal;
	
	private String setorComercialInicial;
	
	private String codigoSetorComercialInicial;
	
	private String setorComercialFinal;
	
	private String codigoSetorComercialFinal;
	
	private String quadraInicial;
	
	private String quadraFinal;
	
	private String rotaInicial;
	
	private String rotaSequenciaInicial;
	
	private String rotaFinal;
	
	private String rotaSequenciaFinal;
	
	private String perfilImovel;
	
	private String descricaoPerfilImovel;
	
	private String categoria;
	
	private String descricaoCategoria;
	
	private String subCategoria;
	
	private String descricaoSubcategoria;
	
	private String quantidadeEconomiasInicial;
	
	private String quantidadeEconomiasFinal;
	
	private String quantidadeEconomia;
	
	private String quantidadeDocumentosInicial;
	
	private String quantidadeDocumentosFinal;
	
	private String quantidadeDocumentos;
	
	private String numeroMoradoresInicial;
	
	private String numeroMoradoresFinal;
	
	private String numeroMoradores;
	
	private String areaConstruidaInicial;
	
	private String areaConstruidaFinal;
	
	private String areaConstruida;
	
	private String imovelCondominio;
	
	private String mediaImovel;
	
	private String consumoPorEconomia;
	private String consumoPorEconomiaFinal;
	
	private String consumoEconomia;
	
	private String tipoMedicao;
	
	private String descricaoTipoMedicao;
	
	private String[] capacidadeHidrometro;
	
	private String descricaoCapacidade;
	
	private String marcaHidrometro;
	
	private String descricaoMarcaHidrometro;
	
	private String[] anormalidadeHidrometro;
	
	private String numeroOcorrenciasAnormalidade;
	
	private String mesAnoInstalacaoInicialHidrometro;
	
	private String mesAnoInstalacaoFinalHidrometro;
	
	private String mesAnoInstalacaoHidrometro;
	
	private String anoMesFaturamento;
	
	private String idProjeto;
	
	private String[] situacaoLigacaoAgua;
	
	private String situacaoLigacaoAguaDescricao;
	
	private String localInstalacaoHidrometro;
	
	private String descricaoLocalInstalacaoHidrometro;
	
	private String tipoEmissao;
	
	private String descricaoComando;
	
	public String getDescricaoComando() {
		return descricaoComando;
	}

	public void setDescricaoComando(String descricaoComando) {
		this.descricaoComando = descricaoComando;
	}

	public String getSituacaoLigacaoAguaDescricao() {
		return situacaoLigacaoAguaDescricao;
	}

	public void setSituacaoLigacaoAguaDescricao(String situacaoLigacaoAguaDescricao) {
		this.situacaoLigacaoAguaDescricao = situacaoLigacaoAguaDescricao;
	}

	public String getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(String idProjeto) {
		this.idProjeto = idProjeto;
	}

	public ImovelEmissaoOrdensSeletivasHelper(){}

	public String getAnoMesFaturamento() {
		return anoMesFaturamento;
	}

	public void setAnoMesFaturamento(String anoMesFaturamento) {
		this.anoMesFaturamento = anoMesFaturamento;
	}

	public String[] getAnormalidadeHidrometro() {
		return anormalidadeHidrometro;
	}

	public void setAnormalidadeHidrometro(String[] anormalidadeHidrometro) {
		this.anormalidadeHidrometro = anormalidadeHidrometro;
	}

	public String getAreaConstruidaFinal() {
		return areaConstruidaFinal;
	}

	public void setAreaConstruidaFinal(String areaConstruidaFinal) {
		this.areaConstruidaFinal = areaConstruidaFinal;
	}

	public String getAreaConstruidaInicial() {
		return areaConstruidaInicial;
	}

	public void setAreaConstruidaInicial(String areaConstruidaInicial) {
		this.areaConstruidaInicial = areaConstruidaInicial;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getConsumoEconomia() {
		return consumoEconomia;
	}

	public void setConsumoEconomia(String consumoEconomia) {
		this.consumoEconomia = consumoEconomia;
	}

	public String getElo() {
		return elo;
	}

	public void setElo(String elo) {
		this.elo = elo;
	}

	public String getImovelCondominio() {
		return imovelCondominio;
	}

	public void setImovelCondominio(String imovelCondominio) {
		this.imovelCondominio = imovelCondominio;
	}

	public String getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(String localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public String getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(String localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public String getMarcaHidrometro() {
		return marcaHidrometro;
	}

	public void setMarcaHidrometro(String marcaHidrometro) {
		this.marcaHidrometro = marcaHidrometro;
	}

	public String getMediaImovel() {
		return mediaImovel;
	}

	public void setMediaImovel(String mediaImovel) {
		this.mediaImovel = mediaImovel;
	}

	public String getNumeroMoradoresFinal() {
		return numeroMoradoresFinal;
	}

	public void setNumeroMoradoresFinal(String numeroMoradoresFinal) {
		this.numeroMoradoresFinal = numeroMoradoresFinal;
	}

	public String getNumeroMoradoresInicial() {
		return numeroMoradoresInicial;
	}

	public void setNumeroMoradoresInicial(String numeroMoradoresInicial) {
		this.numeroMoradoresInicial = numeroMoradoresInicial;
	}

	public String getNumeroOcorrenciasAnormalidade() {
		return numeroOcorrenciasAnormalidade;
	}

	public void setNumeroOcorrenciasAnormalidade(
			String numeroOcorrenciasAnormalidade) {
		this.numeroOcorrenciasAnormalidade = numeroOcorrenciasAnormalidade;
	}

	public String getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public String getQuadraFinal() {
		return quadraFinal;
	}

	public void setQuadraFinal(String quadraFinal) {
		this.quadraFinal = quadraFinal;
	}

	public String getQuadraInicial() {
		return quadraInicial;
	}

	public void setQuadraInicial(String quadraInicial) {
		this.quadraInicial = quadraInicial;
	}

	public String getQuantidadeDocumentosFinal() {
		return quantidadeDocumentosFinal;
	}

	public void setQuantidadeDocumentosFinal(String quantidadeDocumentosFinal) {
		this.quantidadeDocumentosFinal = quantidadeDocumentosFinal;
	}

	public String getQuantidadeDocumentosInicial() {
		return quantidadeDocumentosInicial;
	}

	public void setQuantidadeDocumentosInicial(String quantidadeDocumentosInicial) {
		this.quantidadeDocumentosInicial = quantidadeDocumentosInicial;
	}

	public String getQuantidadeEconomiasFinal() {
		return quantidadeEconomiasFinal;
	}

	public void setQuantidadeEconomiasFinal(String quantidadeEconomiasFinal) {
		this.quantidadeEconomiasFinal = quantidadeEconomiasFinal;
	}

	public String getQuantidadeEconomiasInicial() {
		return quantidadeEconomiasInicial;
	}

	public void setQuantidadeEconomiasInicial(String quantidadeEconomiasInicial) {
		this.quantidadeEconomiasInicial = quantidadeEconomiasInicial;
	}

	public String getQuantidadeMaxima() {
		return quantidadeMaxima;
	}

	public void setQuantidadeMaxima(String quantidadeMaxima) {
		this.quantidadeMaxima = quantidadeMaxima;
	}

	public String getRotaFinal() {
		return rotaFinal;
	}

	public void setRotaFinal(String rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	public String getRotaInicial() {
		return rotaInicial;
	}

	public void setRotaInicial(String rotaInicial) {
		this.rotaInicial = rotaInicial;
	}

	public String getSetorComercialFinal() {
		return setorComercialFinal;
	}

	public void setSetorComercialFinal(String setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}

	public String getSetorComercialInicial() {
		return setorComercialInicial;
	}

	public void setSetorComercialInicial(String setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}

	public String getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}

	public String getTipoMedicao() {
		return tipoMedicao;
	}

	public void setTipoMedicao(String tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}

	public String getTipoOrdem() {
		return tipoOrdem;
	}

	public void setTipoOrdem(String tipoOrdem) {
		this.tipoOrdem = tipoOrdem;
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public String getNomeFirma() {
		return nomeFirma;
	}

	public void setNomeFirma(String nomeFirma) {
		this.nomeFirma = nomeFirma;
	}

	public String getNomeElo() {
		return nomeElo;
	}

	public void setNomeElo(String nomeElo) {
		this.nomeElo = nomeElo;
	}

	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}

	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}

	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}

	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}

	public String getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	public void setCodigoSetorComercialFinal(String codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	public String getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	public void setCodigoSetorComercialInicial(String codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}

	public String getRotaSequenciaFinal() {
		return rotaSequenciaFinal;
	}

	public void setRotaSequenciaFinal(String rotaSequenciaFinal) {
		this.rotaSequenciaFinal = rotaSequenciaFinal;
	}

	public String getRotaSequenciaInicial() {
		return rotaSequenciaInicial;
	}

	public void setRotaSequenciaInicial(String rotaSequenciaInicial) {
		this.rotaSequenciaInicial = rotaSequenciaInicial;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	public String getDescricaoPerfilImovel() {
		return descricaoPerfilImovel;
	}

	public void setDescricaoPerfilImovel(String descricaoPerfilImovel) {
		this.descricaoPerfilImovel = descricaoPerfilImovel;
	}

	public String getDescricaoSubcategoria() {
		return descricaoSubcategoria;
	}

	public void setDescricaoSubcategoria(String descricaoSubcategoria) {
		this.descricaoSubcategoria = descricaoSubcategoria;
	}

	public String getAreaConstruida() {
		return areaConstruida;
	}

	public void setAreaConstruida(String areaConstruida) {
		this.areaConstruida = areaConstruida;
	}

	public String getConsumoPorEconomia() {
		return consumoPorEconomia;
	}

	public void setConsumoPorEconomia(String consumoPorEconomia) {
		this.consumoPorEconomia = consumoPorEconomia;
	}

	
	
	public String getConsumoPorEconomiaFinal() {
		return consumoPorEconomiaFinal;
	}

	public void setConsumoPorEconomiaFinal(String consumoPorEconomiaFinal) {
		this.consumoPorEconomiaFinal = consumoPorEconomiaFinal;
	}

	public String getDescricaoCapacidade() {
		return descricaoCapacidade;
	}

	public void setDescricaoCapacidade(String descricaoCapacidade) {
		this.descricaoCapacidade = descricaoCapacidade;
	}

	public String getDescricaoMarcaHidrometro() {
		return descricaoMarcaHidrometro;
	}

	public void setDescricaoMarcaHidrometro(String descricaoMarcaHidrometro) {
		this.descricaoMarcaHidrometro = descricaoMarcaHidrometro;
	}

	public String getDescricaoTipoMedicao() {
		return descricaoTipoMedicao;
	}

	public void setDescricaoTipoMedicao(String descricaoTipoMedicao) {
		this.descricaoTipoMedicao = descricaoTipoMedicao;
	}

	public String getNumeroMoradores() {
		return numeroMoradores;
	}

	public void setNumeroMoradores(String numeroMoradores) {
		this.numeroMoradores = numeroMoradores;
	}

	public String getQuantidadeDocumentos() {
		return quantidadeDocumentos;
	}

	public void setQuantidadeDocumentos(String quantidadeDocumentos) {
		this.quantidadeDocumentos = quantidadeDocumentos;
	}

	public String getQuantidadeEconomia() {
		return quantidadeEconomia;
	}

	public void setQuantidadeEconomia(String quantidadeEconomia) {
		this.quantidadeEconomia = quantidadeEconomia;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}

	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}

	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}

	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getDescricaoLogradouro() {
		return descricaoLogradouro;
	}

	public void setDescricaoLogradouro(String descricaoLogradouro) {
		this.descricaoLogradouro = descricaoLogradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getMesAnoInstalacaoFinalHidrometro() {
		return mesAnoInstalacaoFinalHidrometro;
	}

	public void setMesAnoInstalacaoFinalHidrometro(
			String mesAnoInstalacaoFinalHidrometro) {
		this.mesAnoInstalacaoFinalHidrometro = mesAnoInstalacaoFinalHidrometro;
	}

	public String getMesAnoInstalacaoInicialHidrometro() {
		return mesAnoInstalacaoInicialHidrometro;
	}

	public void setMesAnoInstalacaoInicialHidrometro(
			String mesAnoInstalacaoInicialHidrometro) {
		this.mesAnoInstalacaoInicialHidrometro = mesAnoInstalacaoInicialHidrometro;
	}

	public String getMesAnoInstalacaoHidrometro() {
		return mesAnoInstalacaoHidrometro;
	}

	public void setMesAnoInstalacaoHidrometro(String mesAnoInstalacaoHidrometro) {
		this.mesAnoInstalacaoHidrometro = mesAnoInstalacaoHidrometro;
	}

	public String getLocalInstalacaoHidrometro() {
		return localInstalacaoHidrometro;
	}

	public void setLocalInstalacaoHidrometro(String localInstalacaoHidrometro) {
		this.localInstalacaoHidrometro = localInstalacaoHidrometro;
	}

	public String getDescricaoLocalInstalacaoHidrometro() {
		return descricaoLocalInstalacaoHidrometro;
	}

	public void setDescricaoLocalInstalacaoHidrometro(
			String descricaoLocalInstalacaoHidrometro) {
		this.descricaoLocalInstalacaoHidrometro = descricaoLocalInstalacaoHidrometro;
	}

	public String[] getCapacidadeHidrometro() {
		return capacidadeHidrometro;
	}

	public void setCapacidadeHidrometro(String[] capacidadeHidrometro) {
		this.capacidadeHidrometro = capacidadeHidrometro;
	}

	public String[] getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	public void setSituacaoLigacaoAgua(String[] situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	public String getTipoEmissao() {
		return tipoEmissao;
	}

	public void setTipoEmissao(String tipoEmissao) {
		this.tipoEmissao = tipoEmissao;
	}

	
}
