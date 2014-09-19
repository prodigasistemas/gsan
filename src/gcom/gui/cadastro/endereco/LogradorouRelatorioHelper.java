package gcom.gui.cadastro.endereco;

public class LogradorouRelatorioHelper {
	String idMunicipio;

	String idBairro;

	String nomeLogradouro;

	String nomePopularLogradouro;

	String idTipoLogradouro;

	String idTituloLogradouro;

	String codigoCep;

	String idLogradouro;

	String indicadorUso;

	String tipoPesquisa;

	String tipoPesquisaPopular;

	/**
	 * Construtor de LogradorouRelatorioHelper 
	 * 
	 * @param idMunicipio
	 * @param idBairro
	 * @param nomeLogradouro
	 * @param nomePopularLogradouro
	 * @param idTipoLogradouro
	 * @param idTituloLogradouro
	 * @param codigoCep
	 * @param idLogradouro
	 * @param indicadorUso
	 * @param tipoPesquisa
	 * @param tipoPesquisaPopular
	 */
	public LogradorouRelatorioHelper(String idMunicipio, String idBairro, String nomeLogradouro, String nomePopularLogradouro, String idTipoLogradouro, String idTituloLogradouro, String codigoCep, String idLogradouro, String indicadorUso, String tipoPesquisa, String tipoPesquisaPopular) {
		
		this.idMunicipio = idMunicipio;
		this.idBairro = idBairro;
		this.nomeLogradouro = nomeLogradouro;
		this.nomePopularLogradouro = nomePopularLogradouro;
		this.idTipoLogradouro = idTipoLogradouro;
		this.idTituloLogradouro = idTituloLogradouro;
		this.codigoCep = codigoCep;
		this.idLogradouro = idLogradouro;
		this.indicadorUso = indicadorUso;
		this.tipoPesquisa = tipoPesquisa;
		this.tipoPesquisaPopular = tipoPesquisaPopular;
	}

	public LogradorouRelatorioHelper() {
		
	}

	public String getCodigoCep() {
		return codigoCep;
	}

	public void setCodigoCep(String codigoCep) {
		this.codigoCep = codigoCep;
	}

	public String getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(String idBairro) {
		this.idBairro = idBairro;
	}

	public String getIdLogradouro() {
		return idLogradouro;
	}

	public void setIdLogradouro(String idLogradouro) {
		this.idLogradouro = idLogradouro;
	}

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getIdTipoLogradouro() {
		return idTipoLogradouro;
	}

	public void setIdTipoLogradouro(String idTipoLogradouro) {
		this.idTipoLogradouro = idTipoLogradouro;
	}

	public String getIdTituloLogradouro() {
		return idTituloLogradouro;
	}

	public void setIdTituloLogradouro(String idTituloLogradouro) {
		this.idTituloLogradouro = idTituloLogradouro;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getNomeLogradouro() {
		return nomeLogradouro;
	}

	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}

	public String getNomePopularLogradouro() {
		return nomePopularLogradouro;
	}

	public void setNomePopularLogradouro(String nomePopularLogradouro) {
		this.nomePopularLogradouro = nomePopularLogradouro;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getTipoPesquisaPopular() {
		return tipoPesquisaPopular;
	}

	public void setTipoPesquisaPopular(String tipoPesquisaPopular) {
		this.tipoPesquisaPopular = tipoPesquisaPopular;
	}
	
}
