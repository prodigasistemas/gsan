package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

/**
 * 
 * Bean do [UC0713] Emitir Ordem de Servico Seletiva 
 *
 * @author Ivan Sérgio
 * @date 10/11/2007
 */
public class RelatorioEmitirOrdemServicoSeletivaSugestaoBean implements RelatorioBean {
	
	private String descricaoTipoServico;
	private String totalSelecionados;
	private String sugestao;
	private String firma;
	
	// Parametros
	private String nomeFirma;
	private String nomeElo;
	private String tipoOrdem;
	private String quantidadeMaxima;
	private String nomeLocalidadeInicial;
	private String nomeLocalidadeFinal;
	private String nomeSetorComercialInicial;
	private String nomeSetorComercialFinal;
	private String quadraInicial;
	private String quadraFinal;
	
	// Caracteristicas
	private String perfilImovelDescricao;
	private String categoriaDescricao;
	private String subCategoriaDescricao;
	private String quantidadeEconomia;
	private String quantidadeDocumentos;
	private String numeroMoradores;
	private String areaConstruida;
	private String imovelCondominio;
	private String mediaImovel;
	private String consumoEconomia;
	private String tipoMedicaoDescricao;
	private String situacaoLigacaoAgua;
	private String situacaoLigacaoAguaDescricao;
	
	// Hidrometro
	private String capacidadeDescricao;
	private String marcaDescricao;
	//private String mesAnoInstalacao;
	private String mesAnoInstalacaoInicial;
	private String mesAnoInstalacaoFinal;
	private String anormalidadeHidrometro;
	private String localInstalacaoDescricao;
	
	private String idLocalidade;
	private String desLocalidade;
	private String idSetorComercial;
	private String desSetorComercial;

	
	public String getAreaConstruida() {
		return areaConstruida;
	}
	public void setAreaConstruida(String areaConstruida) {
		this.areaConstruida = areaConstruida;
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
	public String getConsumoEconomia() {
		return consumoEconomia;
	}
	public void setConsumoEconomia(String consumoEconomia) {
		this.consumoEconomia = consumoEconomia;
	}
	public String getDescricaoTipoServico() {
		return descricaoTipoServico;
	}
	public void setDescricaoTipoServico(String descricaoTipoServico) {
		this.descricaoTipoServico = descricaoTipoServico;
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
	public String getMarcaDescricao() {
		return marcaDescricao;
	}
	public void setMarcaDescricao(String marcaDescricao) {
		this.marcaDescricao = marcaDescricao;
	}
	public String getMediaImovel() {
		return mediaImovel;
	}
	public void setMediaImovel(String mediaImovel) {
		this.mediaImovel = mediaImovel;
	}
//	public String getMesAnoInstalacao() {
//		return mesAnoInstalacao;
//	}
//	public void setMesAnoInstalacao(String mesAnoInstalacao) {
//		this.mesAnoInstalacao = mesAnoInstalacao;
//	}
	public String getNomeFirma() {
		return nomeFirma;
	}
	public void setNomeFirma(String nomeFirma) {
		this.nomeFirma = nomeFirma;
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
	public String getNumeroMoradores() {
		return numeroMoradores;
	}
	public void setNumeroMoradores(String numeroMoradores) {
		this.numeroMoradores = numeroMoradores;
	}
	public String getPerfilImovelDescricao() {
		return perfilImovelDescricao;
	}
	public void setPerfilImovelDescricao(String perfilImovelDescricao) {
		this.perfilImovelDescricao = perfilImovelDescricao;
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
	public String getQuantidadeMaxima() {
		return quantidadeMaxima;
	}
	public void setQuantidadeMaxima(String quantidadeMaxima) {
		this.quantidadeMaxima = quantidadeMaxima;
	}
	public String getSubCategoriaDescricao() {
		return subCategoriaDescricao;
	}
	public void setSubCategoriaDescricao(String subCategoriaDescricao) {
		this.subCategoriaDescricao = subCategoriaDescricao;
	}
	public String getSugestao() {
		return sugestao;
	}
	public void setSugestao(String sugestao) {
		this.sugestao = sugestao;
	}
	public String getTipoMedicaoDescricao() {
		return tipoMedicaoDescricao;
	}
	public void setTipoMedicaoDescricao(String tipoMedicaoDescricao) {
		this.tipoMedicaoDescricao = tipoMedicaoDescricao;
	}
	public String getTipoOrdem() {
		return tipoOrdem;
	}
	public void setTipoOrdem(String tipoOrdem) {
		this.tipoOrdem = tipoOrdem;
	}
	public String getTotalSelecionados() {
		return totalSelecionados;
	}
	public void setTotalSelecionados(String totalSelecionados) {
		this.totalSelecionados = totalSelecionados;
	}
	public String getNomeElo() {
		return nomeElo;
	}
	public void setNomeElo(String nomeElo) {
		this.nomeElo = nomeElo;
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
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}
	public String getSituacaoLigacaoAguaDescricao() {
		return situacaoLigacaoAguaDescricao;
	}
	public void setSituacaoLigacaoAguaDescricao(String situacaoLigacaoAguaDescricao) {
		this.situacaoLigacaoAguaDescricao = situacaoLigacaoAguaDescricao;
	}
	public String getAnormalidadeHidrometro() {
		return anormalidadeHidrometro;
	}
	public void setAnormalidadeHidrometro(String anormalidadeHidrometro) {
		this.anormalidadeHidrometro = anormalidadeHidrometro;
	}
	public String getLocalInstalacaoDescricao() {
		return localInstalacaoDescricao;
	}
	public void setLocalInstalacaoDescricao(String localInstalacaoDescricao) {
		this.localInstalacaoDescricao = localInstalacaoDescricao;
	}
	public String getDesLocalidade() {
		return desLocalidade;
	}
	public void setDesLocalidade(String desLocalidade) {
		this.desLocalidade = desLocalidade;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getDesSetorComercial() {
		return desSetorComercial;
	}
	public void setDesSetorComercial(String desSetorComercial) {
		this.desSetorComercial = desSetorComercial;
	}
	public String getIdSetorComercial() {
		return idSetorComercial;
	}
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	
	
	
}
