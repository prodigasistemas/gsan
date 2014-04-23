package gcom.relatorio.cadastro.imovel;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

public class RelatorioClientesEspeciaisBean implements RelatorioBean {

	private String gerenciaRegional;

	private String categoria;

	private String localidade;

	private String subcategoria;

	private String inscricao;

	private String capacidadeHidrometro;

	private String matricula;

	private String dataInstalacao;

	private String clienteUsuario;

	private String clienteResponsavel;

	private String qtdeEconomias;

	private String consumoAgua;

	private String consumoEsgoto;

	private String ligacaoAgua;

	private String media;

	private String ligacaoEsgoto;

	private String esgotoFixo;

	private BigDecimal debitosVencidos;

	private String faturasEmAtraso;

	private String tarifaConsumo;

	private BigDecimal valorAgua;

	private BigDecimal valorEsgoto;

	private String valorFatura;

	private String setor;

	private String codigoRota;
	
	private Short indicadorCobraMulta;

	public RelatorioClientesEspeciaisBean() {
	}

	public RelatorioClientesEspeciaisBean(String gerenciaRegional,
			String categoria, String localidade, String subcategoria,
			String inscricao, String capacidadeHidrometro, String matricula,
			String dataInstalacao, String clienteUsuario,
			String clienteResponsavel, String qtdeEconomias,
			String consumoAgua, String consumoEsgoto, String ligacaoAgua,
			String media, String ligacaoEsgoto, String esgotoFixo,
			BigDecimal debitosVencidos, String faturasEmAtraso,
			String tarifaConsumo, BigDecimal valorAgua, BigDecimal valorEsgoto,
			String valorFatura) {

		this.gerenciaRegional = gerenciaRegional;
		this.categoria = categoria;
		this.localidade = localidade;
		this.subcategoria = subcategoria;
		this.inscricao = inscricao;
		this.capacidadeHidrometro = capacidadeHidrometro;
		this.matricula = matricula;
		this.dataInstalacao = dataInstalacao;
		this.clienteUsuario = clienteUsuario;
		this.clienteResponsavel = clienteResponsavel;
		this.qtdeEconomias = qtdeEconomias;
		this.consumoAgua = consumoAgua;
		this.consumoEsgoto = consumoEsgoto;
		this.ligacaoAgua = ligacaoAgua;
		this.media = media;
		this.ligacaoEsgoto = ligacaoEsgoto;
		this.esgotoFixo = esgotoFixo;
		this.debitosVencidos = debitosVencidos;
		this.faturasEmAtraso = faturasEmAtraso;
		this.tarifaConsumo = tarifaConsumo;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
		this.valorFatura = valorFatura;

	}

	/**
	 * @return Retorna o campo capacidadeHidrometro.
	 */
	public String getCapacidadeHidrometro() {
		return capacidadeHidrometro;
	}

	/**
	 * @param capacidadeHidrometro
	 *            O capacidadeHidrometro a ser setado.
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
	 * @param categoria
	 *            O categoria a ser setado.
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return Retorna o campo clienteResponsavel.
	 */
	public String getClienteResponsavel() {
		return clienteResponsavel;
	}

	/**
	 * @param clienteResponsavel
	 *            O clienteResponsavel a ser setado.
	 */
	public void setClienteResponsavel(String clienteResponsavel) {
		this.clienteResponsavel = clienteResponsavel;
	}

	/**
	 * @return Retorna o campo clienteUsuario.
	 */
	public String getClienteUsuario() {
		return clienteUsuario;
	}

	/**
	 * @param clienteUsuario
	 *            O clienteUsuario a ser setado.
	 */
	public void setClienteUsuario(String clienteUsuario) {
		this.clienteUsuario = clienteUsuario;
	}

	/**
	 * @return Retorna o campo consumoAgua.
	 */
	public String getConsumoAgua() {
		return consumoAgua;
	}

	/**
	 * @param consumoAgua
	 *            O consumoAgua a ser setado.
	 */
	public void setConsumoAgua(String consumoAgua) {
		this.consumoAgua = consumoAgua;
	}

	/**
	 * @return Retorna o campo consumoEsgoto.
	 */
	public String getConsumoEsgoto() {
		return consumoEsgoto;
	}

	/**
	 * @param consumoEsgoto
	 *            O consumoEsgoto a ser setado.
	 */
	public void setConsumoEsgoto(String consumoEsgoto) {
		this.consumoEsgoto = consumoEsgoto;
	}

	/**
	 * @return Retorna o campo valorFatura.
	 */
	public String getValorFatura() {
		return valorFatura;
	}

	/**
	 * @param valorFatura
	 *            O valorFatura a ser setado.
	 */
	public void setValorFatura(String valorFatura) {
		this.valorFatura = valorFatura;
	}

	/**
	 * @return Retorna o campo dataInstalacao.
	 */
	public String getDataInstalacao() {
		return dataInstalacao;
	}

	/**
	 * @param dataInstalacao
	 *            O dataInstalacao a ser setado.
	 */
	public void setDataInstalacao(String dataInstalacao) {
		this.dataInstalacao = dataInstalacao;
	}

	/**
	 * @return Retorna o campo esgotoFixo.
	 */
	public String getEsgotoFixo() {
		return esgotoFixo;
	}

	/**
	 * @param esgotoFixo
	 *            O esgotoFixo a ser setado.
	 */
	public void setEsgotoFixo(String esgotoFixo) {
		this.esgotoFixo = esgotoFixo;
	}

	/**
	 * @return Retorna o campo faturasEmAtraso.
	 */
	public String getFaturasEmAtraso() {
		return faturasEmAtraso;
	}

	/**
	 * @param faturasEmAtraso
	 *            O faturasEmAtraso a ser setado.
	 */
	public void setFaturasEmAtraso(String faturasEmAtraso) {
		this.faturasEmAtraso = faturasEmAtraso;
	}

	/**
	 * @return Retorna o campo gerenciaRegional.
	 */
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	/**
	 * @param gerenciaRegional
	 *            O gerenciaRegional a ser setado.
	 */
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	/**
	 * @return Retorna o campo inscricao.
	 */
	public String getInscricao() {
		return inscricao;
	}

	/**
	 * @param inscricao
	 *            O inscricao a ser setado.
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
	 * @param ligacaoAgua
	 *            O ligacaoAgua a ser setado.
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
	 * @param ligacaoEsgoto
	 *            O ligacaoEsgoto a ser setado.
	 */
	public void setLigacaoEsgoto(String ligacaoEsgoto) {
		this.ligacaoEsgoto = ligacaoEsgoto;
	}

	/**
	 * @return Retorna o campo localidade.
	 */
	public String getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade
	 *            O localidade a ser setado.
	 */
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Retorna o campo matricula.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * @param matricula
	 *            O matricula a ser setado.
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return Retorna o campo media.
	 */
	public String getMedia() {
		return media;
	}

	/**
	 * @param media
	 *            O media a ser setado.
	 */
	public void setMedia(String media) {
		this.media = media;
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
	 * @return Retorna o campo subcategoria.
	 */
	public String getSubcategoria() {
		return subcategoria;
	}

	/**
	 * @param subcategoria
	 *            O subcategoria a ser setado.
	 */
	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}

	/**
	 * @return Retorna o campo tarifaConsumo.
	 */
	public String getTarifaConsumo() {
		return tarifaConsumo;
	}

	/**
	 * @param tarifaConsumo
	 *            O tarifaConsumo a ser setado.
	 */
	public void setTarifaConsumo(String tarifaConsumo) {
		this.tarifaConsumo = tarifaConsumo;
	}

	public void setDebitosVencidos(BigDecimal debitosVencidos) {
		this.debitosVencidos = debitosVencidos;
	}

	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}

	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}

	public BigDecimal getDebitosVencidos() {
		return debitosVencidos;
	}

	public BigDecimal getValorAgua() {
		return valorAgua;
	}

	public BigDecimal getValorEsgoto() {
		return valorEsgoto;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String descricaoSetor) {
		this.setor = descricaoSetor;
	}

	public String getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}

	public Short getIndicadorCobraMulta() {
		return indicadorCobraMulta;
	}

	public void setIndicadorCobraMulta(Short indicadorCobraMulta) {
		this.indicadorCobraMulta = indicadorCobraMulta;
	}

}
