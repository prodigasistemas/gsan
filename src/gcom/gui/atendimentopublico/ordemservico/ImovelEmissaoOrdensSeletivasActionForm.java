package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Ivan Sérgio
 * @created 29/10/2007
 */
public class ImovelEmissaoOrdensSeletivasActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	public static final String TIPO_ORDEM_INSTALACAO = "INSTALACAO";
	public static final String TIPO_ORDEM_SUBSTITUICAO = "SUBSTITUICAO";
	public static final String TIPO_ORDEM_REMOCAO = "REMOCAO";
	public static final String TIPO_INSPECAO_ANORMALIDADE = "INSPECAO";
	
	// Parametros
	private String tipoOrdem;
	private String sugestao;
	private String tipoRelatorio;
	private String firma;
	private String nomeFirma;
	private String quantidadeMaxima;
	private String elo;
	private String gerenciaRegional;
	private String unidadeNegocio;
	private String nomeElo;
	private String logradouro;
	private String descricaoLogradouro;
	private String inscricaoTipo;
	private String localidadeInicial;
	private String nomeLocalidadeInicial;
	private String localidadeFinal;
	private String nomeLocalidadeFinal;
	private String setorComercialInicial;
	private String codigoSetorComercialInicial;
	private String nomeSetorComercialInicial;
	private String setorComercialFinal;
	private String codigoSetorComercialFinal;
	private String nomeSetorComercialFinal;
	private String quadraInicial;
	private String idQuadraInicial;
	private String quadraFinal;
	private String idQuadraFinal;
	private String rotaInicial;
	private String rotaSequenciaInicial;
	private String rotaFinal;
	private String rotaSequenciaFinal;
	private String idImovel;
	private String inscricaoImovel;
	private String descricaoComando;
	
	// Caracteristicas
	private String perfilImovel;
	private String perfilImovelDescricao;
	private String categoria;
	private String categoriaDescricao;
	private String subCategoria;
	private String subCategoriaDescricao;
	private String intervaloQuantidadeEconomiasInicial;
	private String intervaloQuantidadeEconomiasFinal;
	private String intervaloQuantidadeDocumentosInicial;
	private String intervaloQuantidadeDocumentosFinal;
	private String intervaloNumeroMoradoresInicial;
	private String intervaloNumeroMoradoresFinal;
	private String intervaloAreaConstruidaInicial;
	private String intervaloAreaConstruidaFinal;
	private String intervaloAreaConstruidaPredefinida;
	private String imovelCondominio;
	private String mediaImovel;
	private String consumoPorEconomia;
	private String consumoPorEconomiaFinal;
	private String tipoMedicao;
	private String tipoMedicaoDescricao;
	private String idProjeto;
	private String nomeProjeto;
	private String[] situacaoLigacaoAgua;
	private String situacaoLigacaoAguaDescricao;
	private String usuarioSemPermissaoGerarOS;
	
	// Hidrometro
	private String[] capacidade;
	private String capacidadeDescricao;
	private String marca;
	private String marcaDescricao;
	private String[] anormalidadeHidrometro;
	private String numeroOcorrenciasConsecutivas;
	private String mesAnoInstalacaoInicial;
	private String mesAnoInstalacaoFinal;
	private String localInstalacao;
	private String localInstalacaoDescricao; 
	
	
	
	public String[] getAnormalidadeHidrometro() {
		return anormalidadeHidrometro;
	}
	public void setAnormalidadeHidrometro(String[] anormalidadeHidrometro) {
		this.anormalidadeHidrometro = anormalidadeHidrometro;
	}

	public String[] getCapacidade() {
		return capacidade;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
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
	public String getElo() {
		return elo;
	}
	public void setElo(String elo) {
		this.elo = elo;
	}
	public String getFirma() {
		return firma;
	}
	public void setFirma(String firma) {
		this.firma = firma;
	}
	public String getImovelCondominio() {
		return imovelCondominio;
	}
	public void setImovelCondominio(String imovelCondominio) {
		this.imovelCondominio = imovelCondominio;
	}
	public String getIntervaloAreaConstruidaFinal() {
		return intervaloAreaConstruidaFinal;
	}
	public void setIntervaloAreaConstruidaFinal(String intervaloAreaConstruidaFinal) {
		this.intervaloAreaConstruidaFinal = intervaloAreaConstruidaFinal;
	}
	public String getIntervaloAreaConstruidaInicial() {
		return intervaloAreaConstruidaInicial;
	}
	public void setIntervaloAreaConstruidaInicial(
			String intervaloAreaConstruidaInicial) {
		this.intervaloAreaConstruidaInicial = intervaloAreaConstruidaInicial;
	}
	public String getIntervaloAreaConstruidaPredefinida() {
		return intervaloAreaConstruidaPredefinida;
	}
	public void setIntervaloAreaConstruidaPredefinida(
			String intervaloAreaConstruidaPredefinida) {
		this.intervaloAreaConstruidaPredefinida = intervaloAreaConstruidaPredefinida;
	}
	public String getIntervaloNumeroMoradoresFinal() {
		return intervaloNumeroMoradoresFinal;
	}
	public void setIntervaloNumeroMoradoresFinal(
			String intervaloNumeroMoradoresFinal) {
		this.intervaloNumeroMoradoresFinal = intervaloNumeroMoradoresFinal;
	}
	public String getIntervaloNumeroMoradoresInicial() {
		return intervaloNumeroMoradoresInicial;
	}
	public void setIntervaloNumeroMoradoresInicial(
			String intervaloNumeroMoradoresInicial) {
		this.intervaloNumeroMoradoresInicial = intervaloNumeroMoradoresInicial;
	}
	public String getIntervaloQuantidadeDocumentosInicial() {
		return intervaloQuantidadeDocumentosInicial;
	}
	public void setIntervaloQuantidadeDocumentosInicial(
			String intervaloQuantidadeDocumentosInicial) {
		this.intervaloQuantidadeDocumentosInicial = intervaloQuantidadeDocumentosInicial;
	}
	public String getIntervaloQuantidadeDocumentosFinal() {
		return intervaloQuantidadeDocumentosFinal;
	}
	public void setIntervaloQuantidadeDocumentosFinal(
			String intervaloQuantidadeDocumentosFinal) {
		this.intervaloQuantidadeDocumentosFinal = intervaloQuantidadeDocumentosFinal;
	}
	public String getIntervaloQuantidadeEconomiasFinal() {
		return intervaloQuantidadeEconomiasFinal;
	}
	public void setIntervaloQuantidadeEconomiasFinal(
			String intervaloQuantidadeEconomiasFinal) {
		this.intervaloQuantidadeEconomiasFinal = intervaloQuantidadeEconomiasFinal;
	}
	public String getIntervaloQuantidadeEconomiasInicial() {
		return intervaloQuantidadeEconomiasInicial;
	}
	public void setIntervaloQuantidadeEconomiasInicial(
			String intervaloQuantidadeEconomiasInicial) {
		this.intervaloQuantidadeEconomiasInicial = intervaloQuantidadeEconomiasInicial;
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
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getMediaImovel() {
		return mediaImovel;
	}
	public void setMediaImovel(String mediaImovel) {
		this.mediaImovel = mediaImovel;
	}

	public String getNumeroOcorrenciasConsecutivas() {
		return numeroOcorrenciasConsecutivas;
	}
	public void setNumeroOcorrenciasConsecutivas(
			String numeroOcorrenciasConsecutivas) {
		this.numeroOcorrenciasConsecutivas = numeroOcorrenciasConsecutivas;
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
	public String getSugestao() {
		return sugestao;
	}
	public void setSugestao(String sugestao) {
		this.sugestao = sugestao;
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
	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}
	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}
	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}
	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}
	public String getNomeSetorComercialFinal() {
		return nomeSetorComercialFinal;
	}
	public void setNomeSetorComercialFinal(String nomeSetorComercialFinal) {
		this.nomeSetorComercialFinal = nomeSetorComercialFinal;
	}
	public String getNomeSetorComercialInicial() {
		return nomeSetorComercialInicial;
	}
	public void setNomeSetorComercialInicial(String nomeSetorComercialInicial) {
		this.nomeSetorComercialInicial = nomeSetorComercialInicial;
	}
	public String getIdQuadraFinal() {
		return idQuadraFinal;
	}
	public void setIdQuadraFinal(String idQuadraFinal) {
		this.idQuadraFinal = idQuadraFinal;
	}
	public String getIdQuadraInicial() {
		return idQuadraInicial;
	}
	public void setIdQuadraInicial(String idQuadraInicial) {
		this.idQuadraInicial = idQuadraInicial;
	}
	public String getInscricaoTipo() {
		return inscricaoTipo;
	}
	public void setInscricaoTipo(String inscricaoTipo) {
		this.inscricaoTipo = inscricaoTipo;
	}
	public String getNomeElo() {
		return nomeElo;
	}
	public void setNomeElo(String nomeElo) {
		this.nomeElo = nomeElo;
	}
	
	public void limparCamposHidrometro() {
		this.capacidade = null;
		this.marca = null;
		this.anormalidadeHidrometro = null;
		this.numeroOcorrenciasConsecutivas = null;
		this.mesAnoInstalacaoInicial = null;
		this.mesAnoInstalacaoInicial = null;
	}
	public String getNomeFirma() {
		return nomeFirma;
	}
	public void setNomeFirma(String nomeFirma) {
		this.nomeFirma = nomeFirma;
	}
	
	public String getCapacidadeDescricao() {
		return capacidadeDescricao;
	}
	public void setCapacidadeDescricao(String capacidadeDescricao) {
		this.capacidadeDescricao = capacidadeDescricao;
	}
	public String getCategoriaDescricao() {
		return categoriaDescricao;
	}
	public void setCategoriaDescricao(String categoriaDescricao) {
		this.categoriaDescricao = categoriaDescricao;
	}
	public String getMarcaDescricao() {
		return marcaDescricao;
	}
	public void setMarcaDescricao(String marcaDescricao) {
		this.marcaDescricao = marcaDescricao;
	}
	public String getPerfilImovelDescricao() {
		return perfilImovelDescricao;
	}
	public void setPerfilImovelDescricao(String perfilImovelDescricao) {
		this.perfilImovelDescricao = perfilImovelDescricao;
	}
	public String getSubCategoriaDescricao() {
		return subCategoriaDescricao;
	}
	public void setSubCategoriaDescricao(String subCategoriaDescricao) {
		this.subCategoriaDescricao = subCategoriaDescricao;
	}
	public String getTipoMedicaoDescricao() {
		return tipoMedicaoDescricao;
	}
	public void setTipoMedicaoDescricao(String tipoMedicaoDescricao) {
		this.tipoMedicaoDescricao = tipoMedicaoDescricao;
	}
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
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
	public String[] getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	public void setSituacaoLigacaoAgua(String[] situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}
	public void setCapacidade(String[] capacidade) {
		this.capacidade = capacidade;
	}
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public String getNomeProjeto() {
		return nomeProjeto;
	}
	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}
	public String getIdProjeto() {
		return idProjeto;
	}
	public void setIdProjeto(String idProjeto) {
		this.idProjeto = idProjeto;
	}
	public String getMesAnoInstalacaoFinal() {
		return mesAnoInstalacaoFinal;
	}
	public void setMesAnoInstalacaoFinal(String mesAnoInstalacaoFinal) {
		this.mesAnoInstalacaoFinal = mesAnoInstalacaoFinal;
	}
	public String getMesAnoInstalacaoInicial() {
		return mesAnoInstalacaoInicial;
	}
	public void setMesAnoInstalacaoInicial(String mesAnoInstalacaoInicial) {
		this.mesAnoInstalacaoInicial = mesAnoInstalacaoInicial;
	}
	
	public String getSituacaoLigacaoAguaDescricao() {
		return situacaoLigacaoAguaDescricao;
	}
	public void setSituacaoLigacaoAguaDescricao(String situacaoLigacaoAguaDescricao) {
		this.situacaoLigacaoAguaDescricao = situacaoLigacaoAguaDescricao;
	}
	public String getTipoRelatorio() {
		return tipoRelatorio;
	}
	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}
	public String getLocalInstalacao() {
		return localInstalacao;
	}
	public void setLocalInstalacao(String localInstalacao) {
		this.localInstalacao = localInstalacao;
	}
	public String getLocalInstalacaoDescricao() {
		return localInstalacaoDescricao;
	}
	public void setLocalInstalacaoDescricao(String localInstalacaoDescricao) {
		this.localInstalacaoDescricao = localInstalacaoDescricao;
	}
	public String getUsuarioSemPermissaoGerarOS() {
		return usuarioSemPermissaoGerarOS;
	}
	public void setUsuarioSemPermissaoGerarOS(String usuarioSemPermissaoGerarOS) {
		this.usuarioSemPermissaoGerarOS = usuarioSemPermissaoGerarOS;
	}
	public String getDescricaoComando() {
		return descricaoComando;
	}
	public void setDescricaoComando(String descricaoComando) {
		this.descricaoComando = descricaoComando;
	}
	
	
	
	
	
}

