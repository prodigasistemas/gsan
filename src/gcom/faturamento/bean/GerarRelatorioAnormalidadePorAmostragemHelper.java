package gcom.faturamento.bean;

public class GerarRelatorioAnormalidadePorAmostragemHelper {

	private static final long serialVersionUID = 1L;

	private Integer idGrupo;
	private String nomeGrupo;
	private Integer idUnidadeNegocio;
	private String nomeUnidadeNegocio;
	private Integer idGerenciaRegional;
	private String nomeGerenciaRegional;
	private Integer idElo;
	private String nomeElo;
	private Integer idLocalidade;
	private String nomeLocalidade;
	private Integer idImovel;
	private String nomeUsuario;
	private Integer situacaoLigacaoAgua;
	private Integer situacaoLigacaoEsgoto;
	private Short indicadorDebito;
	private Integer consumoMedio;
	private Integer consumoMes;
	private String descricaoAbrevConsumoAnormalidade;
	private Integer idLeituraAnormalidade;
	private String categoria;
	private Short quantidadeEconomias;
	private String tipoMedicao;
	private String capacidadeHidrometro;
	private String localInstalacaoHidrometro;
	private Integer idSetorComercial;
	private Integer codigoSetorComercial;
	private Integer nnLeituraAtualInformada;
	private Integer idEmpresa;
	private String nomeEmpresa;
	private String enderecoImovel;
	private String inscricaoImovel;

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	/**
	 * @return Retorna o campo idSetorComercial.
	 */
	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}

	/**
	 * @param idSetorComercial O idSetorComercial a ser setado.
	 */
	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	/**
	 * @return Retorna o campo codigoSetorComercial.
	 */
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	/**
	 * @param codigoSetorComercial O codigoSetorComercial a ser setado.
	 */
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	/**
	 * @return Retorna o campo idElo.
	 */
	public Integer getIdElo() {
		return idElo;
	}

	/**
	 * @param idElo
	 *            O idElo a ser setado.
	 */
	public void setIdElo(Integer idElo) {
		this.idElo = idElo;
	}

	/**
	 * @return Retorna o campo idGerenciaRegional.
	 */
	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	/**
	 * @param idGerenciaRegional
	 *            O idGerenciaRegional a ser setado.
	 */
	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	/**
	 * @return Retorna o campo idGrupo.
	 */
	public Integer getIdGrupo() {
		return idGrupo;
	}

	/**
	 * @param idGrupo
	 *            O idGrupo a ser setado.
	 */
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade
	 *            O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Retorna o campo idUnidadeNegocio.
	 */
	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	/**
	 * @param idUnidadeNegocio
	 *            O idUnidadeNegocio a ser setado.
	 */
	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo nomeElo.
	 */
	public String getNomeElo() {
		return nomeElo;
	}

	/**
	 * @param nomeElo
	 *            O nomeElo a ser setado.
	 */
	public void setNomeElo(String nomeElo) {
		this.nomeElo = nomeElo;
	}

	/**
	 * @return Retorna o campo nomeGerenciaRegional.
	 */
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}

	/**
	 * @param nomeGerenciaRegional
	 *            O nomeGerenciaRegional a ser setado.
	 */
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}

	/**
	 * @return Retorna o campo nomeGrupo.
	 */
	public String getNomeGrupo() {
		return nomeGrupo;
	}

	/**
	 * @param nomeGrupo
	 *            O nomeGrupo a ser setado.
	 */
	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}

	/**
	 * @return Retorna o campo nomeLocalidade.
	 */
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	/**
	 * @param nomeLocalidade
	 *            O nomeLocalidade a ser setado.
	 */
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	/**
	 * @return Retorna o campo nomeUnidadeNegocio.
	 */
	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}

	/**
	 * @param nomeUnidadeNegocio
	 *            O nomeUnidadeNegocio a ser setado.
	 */
	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}

	public Integer getConsumoMedio() {
		return consumoMedio;
	}

	public void setConsumoMedio(Integer consumoMedio) {
		this.consumoMedio = consumoMedio;
	}

	public Integer getConsumoMes() {
		return consumoMes;
	}

	public void setConsumoMes(Integer consumoMes) {
		this.consumoMes = consumoMes;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Short getIndicadorDebito() {
		return indicadorDebito;
	}

	public void setIndicadorDebito(Short indicadorDebito) {
		this.indicadorDebito = indicadorDebito;
	}

	public Integer getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	public void setSituacaoLigacaoAgua(Integer situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	public Integer getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	public void setSituacaoLigacaoEsgoto(Integer situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	public String getDescricaoAbrevConsumoAnormalidade() {
		return descricaoAbrevConsumoAnormalidade;
	}

	public void setDescricaoAbrevConsumoAnormalidade(
			String descricaoAbrevConsumoAnormalidade) {
		this.descricaoAbrevConsumoAnormalidade = descricaoAbrevConsumoAnormalidade;
	}
	
	/**
	 * @return Retorna o campo descricaoAbrevLeituraAnormalidade.
	 */
	public Integer getIdLeituraAnormalidade() {
		return idLeituraAnormalidade;
	}

	/**
	 * @param descricaoAbrevLeituraAnormalidade O descricaoAbrevLeituraAnormalidade a ser setado.
	 */
	public void setIdLeituraAnormalidade(
			Integer idLeituraAnormalidade) {
		this.idLeituraAnormalidade = idLeituraAnormalidade;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
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
	 * @return Retorna o campo localInstalacaoHidrometro.
	 */
	public String getLocalInstalacaoHidrometro() {
		return localInstalacaoHidrometro;
	}

	/**
	 * @param localInstalacaoHidrometro O localInstalacaoHidrometro a ser setado.
	 */
	public void setLocalInstalacaoHidrometro(String localInstalacaoHidrometro) {
		this.localInstalacaoHidrometro = localInstalacaoHidrometro;
	}

	/**
	 * @return Retorna o campo quantidadeEconomias.
	 */
	public Short getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	/**
	 * @param quantidadeEconomias O quantidadeEconomias a ser setado.
	 */
	public void setQuantidadeEconomias(Short quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
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
	 * @return Retorna o campo nnLeituraAtualInformada.
	 */
	public Integer getNnLeituraAtualInformada() {
		return nnLeituraAtualInformada;
	}

	/**
	 * @param nnLeituraAtualInformada O nnLeituraAtualInformada a ser setado.
	 */
	public void setNnLeituraAtualInformada(Integer nnLeituraAtualInformada) {
		this.nnLeituraAtualInformada = nnLeituraAtualInformada;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	
}
