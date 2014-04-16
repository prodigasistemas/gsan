package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0637] - Gerar Relatórios Volumes Faturados
 * 
 * @author Rafael Corrêa
 * @date 10/09/2007
 */
public class RelatorioAnormalidadeConsumoBean implements RelatorioBean {

	private String grupo;
	
	private String unidadeNegocio;
	
	private String gerenciaRegional;

	private String elo;
	
	private String localidade;
	
	private String idSetorComercial;
	
	private String setorComercial;

	private String imovel;

	private String usuario;
	
	private String endereco;
	
	private String situacaoAgua;
	
	private String situacaoEsgoto;
	
	private String debitoAutomatico;

	private String media;

	private String consumo;

	private String anormalidadeConsumo;
	
	private String anormalidadeLeitura;
	
	private String nnLeituraAtualInformada;
	
	private String categoria;
	
	private String economia;
	
	private String tipoMedicao;
	
	private String capacidadeHidrometro;
	
	private String localInstalacao;
	
	private String totalGrupo;
	
	private String totalGerenciaRegional;
	
	private String totalUnidadeNegocio;
	
	private String totalElo;
	
	private String totalLocalidade;
	
	private String totalSetorComercial;
	
	private String idEmpresa;
	
	private String nomeEmpresa;

	private String inscricaoImovel;
	
	public RelatorioAnormalidadeConsumoBean(String grupo,
			String gerenciaRegional, String unidadeNegocio, String elo,
			String localidade, String idSetorComercial, String setorComercial, String imovel, String usuario, String endereco,
			String situacaoAgua, String situacaoEsgoto,
			String debitoAutomatico, String media, String consumo,
			String anormalidadeConsumo, String anormalidadeLeitura, String nnLeituraAtualInformada,
			String categoria, String economia, String tipoMedicao,
			String capacidadeHidrometro, String localInstalacao,
			String totalGrupo, String totalGerenciaRegional,
			String totalUnidadeNegocio, String totalElo, String totalLocalidade, String totalSetorComercial,
			String idEmpresa, String nomeEmpresa, String inscricaoImovel) {
		
		this.grupo = grupo;
		this.unidadeNegocio = unidadeNegocio;
		this.gerenciaRegional = gerenciaRegional;
		this.elo = elo;
		this.localidade = localidade;
		this.idSetorComercial = idSetorComercial;
		this.setorComercial = setorComercial;
		this.imovel = imovel;
		this.usuario = usuario;
		this.endereco = endereco;
		this.situacaoAgua = situacaoAgua;
		this.situacaoEsgoto = situacaoEsgoto;
		this.debitoAutomatico = debitoAutomatico;
		this.media = media;
		this.consumo = consumo;
		this.anormalidadeConsumo = anormalidadeConsumo;
		this.anormalidadeLeitura = anormalidadeLeitura;
		this.nnLeituraAtualInformada = nnLeituraAtualInformada;
		this.categoria = categoria;
		this.economia = economia;
		this.tipoMedicao = tipoMedicao;
		this.capacidadeHidrometro = capacidadeHidrometro;
		this.localInstalacao = localInstalacao;
		this.totalGrupo = totalGrupo;
		this.totalGerenciaRegional = totalGerenciaRegional;
		this.totalUnidadeNegocio = totalUnidadeNegocio;
		this.totalElo = totalElo;
		this.totalLocalidade = totalLocalidade;
		this.totalSetorComercial = totalSetorComercial;
		this.idEmpresa = idEmpresa;
		this.nomeEmpresa = nomeEmpresa;
		this.inscricaoImovel = inscricaoImovel;
	}

	/**
	 * @return Retorna o campo anormalidade.
	 */
	public String getAnormalidadeLeitura() {
		return anormalidadeLeitura;
	}

	/**
	 * @param anormalidade O anormalidade a ser setado.
	 */
	public void setAnormalidadeLeitura(String anormalidadeLeitura) {
		this.anormalidadeLeitura = anormalidadeLeitura;
	}
	
	/**
	 * @return Retorna o campo anormalidade.
	 */
	public String getAnormalidadeConsumo() {
		return anormalidadeConsumo;
	}

	/**
	 * @param anormalidade O anormalidade a ser setado.
	 */
	public void setAnormalidadeConsumo(String anormalidadeConsumo) {
		this.anormalidadeConsumo = anormalidadeConsumo;
	}

	/**
	 * @return Retorna o campo consumo.
	 */
	public String getConsumo() {
		return consumo;
	}

	/**
	 * @param consumo O consumo a ser setado.
	 */
	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}

	/**
	 * @return Retorna o campo debitoAutomatico.
	 */
	public String getDebitoAutomatico() {
		return debitoAutomatico;
	}

	/**
	 * @param debitoAutomatico O debitoAutomatico a ser setado.
	 */
	public void setDebitoAutomatico(String debitoAutomatico) {
		this.debitoAutomatico = debitoAutomatico;
	}

	/**
	 * @return Retorna o campo elo.
	 */
	public String getElo() {
		return elo;
	}

	/**
	 * @param elo O elo a ser setado.
	 */
	public void setElo(String elo) {
		this.elo = elo;
	}

	/**
	 * @return Retorna o campo endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco O endereco a ser setado.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return Retorna o campo gerenciaRegional.
	 */
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	/**
	 * @param gerenciaRegional O gerenciaRegional a ser setado.
	 */
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	/**
	 * @return Retorna o campo grupo.
	 */
	public String getGrupo() {
		return grupo;
	}

	/**
	 * @param grupo O grupo a ser setado.
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	/**
	 * @return Retorna o campo imovel.
	 */
	public String getImovel() {
		return imovel;
	}

	/**
	 * @param imovel O imovel a ser setado.
	 */
	public void setImovel(String imovel) {
		this.imovel = imovel;
	}

	/**
	 * @return Retorna o campo localidade.
	 */
	public String getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Retorna o campo media.
	 */
	public String getMedia() {
		return media;
	}

	/**
	 * @param media O media a ser setado.
	 */
	public void setMedia(String media) {
		this.media = media;
	}

	/**
	 * @return Retorna o campo situacaoAgua.
	 */
	public String getSituacaoAgua() {
		return situacaoAgua;
	}

	/**
	 * @param situacaoAgua O situacaoAgua a ser setado.
	 */
	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	/**
	 * @return Retorna o campo situacaoEsgoto.
	 */
	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}

	/**
	 * @param situacaoEsgoto O situacaoEsgoto a ser setado.
	 */
	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}

	/**
	 * @return Retorna o campo unidadeNegocio.
	 */
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	/**
	 * @param unidadeNegocio O unidadeNegocio a ser setado.
	 */
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	/**
	 * @return Retorna o campo usuario.
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario O usuario a ser setado.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return Retorna o campo capacidadeHidrometro.
	 */
	public String getCapacidadeHidrometro() {
		return capacidadeHidrometro;
	}

	/**
	 * @param capacidadeHidrometro O capacidadeHidrometro a ser setado.
	 */
	public void setCapacidadeHidrometro(String capacidadeHidrometro) {
		this.capacidadeHidrometro = capacidadeHidrometro;
	}

	/**
	 * @return Retorna o campo categoria.
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria O categoria a ser setado.
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return Retorna o campo economia.
	 */
	public String getEconomia() {
		return economia;
	}

	/**
	 * @param economia O economia a ser setado.
	 */
	public void setEconomia(String economia) {
		this.economia = economia;
	}

	/**
	 * @return Retorna o campo localInstalacao.
	 */
	public String getLocalInstalacao() {
		return localInstalacao;
	}

	/**
	 * @param localInstalacao O localInstalacao a ser setado.
	 */
	public void setLocalInstalacao(String localInstalacao) {
		this.localInstalacao = localInstalacao;
	}

	/**
	 * @return Retorna o campo tipoMedicao.
	 */
	public String getTipoMedicao() {
		return tipoMedicao;
	}

	/**
	 * @param tipoMedicao O tipoMedicao a ser setado.
	 */
	public void setTipoMedicao(String tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}

	/**
	 * @return Retorna o campo totalElo.
	 */
	public String getTotalElo() {
		return totalElo;
	}

	/**
	 * @param totalElo O totalElo a ser setado.
	 */
	public void setTotalElo(String totalElo) {
		this.totalElo = totalElo;
	}

	/**
	 * @return Retorna o campo totalGerenciaRegional.
	 */
	public String getTotalGerenciaRegional() {
		return totalGerenciaRegional;
	}

	/**
	 * @param totalGerenciaRegional O totalGerenciaRegional a ser setado.
	 */
	public void setTotalGerenciaRegional(String totalGerenciaRegional) {
		this.totalGerenciaRegional = totalGerenciaRegional;
	}

	/**
	 * @return Retorna o campo totalGrupo.
	 */
	public String getTotalGrupo() {
		return totalGrupo;
	}

	/**
	 * @param totalGrupo O totalGrupo a ser setado.
	 */
	public void setTotalGrupo(String totalGrupo) {
		this.totalGrupo = totalGrupo;
	}

	/**
	 * @return Retorna o campo totalLocalidade.
	 */
	public String getTotalLocalidade() {
		return totalLocalidade;
	}

	/**
	 * @param totalLocalidade O totalLocalidade a ser setado.
	 */
	public void setTotalLocalidade(String totalLocalidade) {
		this.totalLocalidade = totalLocalidade;
	}

	/**
	 * @return Retorna o campo totalUnidadeNegocio.
	 */
	public String getTotalUnidadeNegocio() {
		return totalUnidadeNegocio;
	}

	/**
	 * @param totalUnidadeNegocio O totalUnidadeNegocio a ser setado.
	 */
	public void setTotalUnidadeNegocio(String totalUnidadeNegocio) {
		this.totalUnidadeNegocio = totalUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo setorComercial.
	 */
	public String getSetorComercial() {
		return setorComercial;
	}

	/**
	 * @param setorComercial O setorComercial a ser setado.
	 */
	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	/**
	 * @return Retorna o campo totalSetorComercial.
	 */
	public String getTotalSetorComercial() {
		return totalSetorComercial;
	}

	/**
	 * @param totalSetorComercial O totalSetorComercial a ser setado.
	 */
	public void setTotalSetorComercial(String totalSetorComercial) {
		this.totalSetorComercial = totalSetorComercial;
	}

	/**
	 * @return Retorna o campo idSetorComercial.
	 */
	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	/**
	 * @param idSetorComercial O idSetorComercial a ser setado.
	 */
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	/**
	 * @return Retorna o campo nnLeituraAtualInformada.
	 */
	public String getNnLeituraAtualInformada() {
		return nnLeituraAtualInformada;
	}

	/**
	 * @param nnLeituraAtualInformada O nnLeituraAtualInformada a ser setado.
	 */
	public void setNnLeituraAtualInformada(String nnLeituraAtualInformada) {
		this.nnLeituraAtualInformada = nnLeituraAtualInformada;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	
}
