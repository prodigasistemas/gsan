package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Rafael Corrêa
 * @created 08/08/2006
 */
/**
 * @author Administrador
 *
 */
public class RelatorioContratoPrestacaoServicoBean implements RelatorioBean {

	private String indicadorPessoaFisica;

	private String numeroPagina;
	
	private String numeroContrato;

	private String nomeCliente;
	
	private String nomeLocalidade;

	private String nomeResponsavel;
	
	private String cpfResponsavel;

	private String rgResponsavel;
	
	private String cpfCliente;

	private String rgCliente;
	
	private String enderecoCliente;
	
	private String idImovel;
	
	private String enderecoImovel;
	
	private String categoria;
	
	private String consumoMinimo;
	
	private String data;
	
	private String municipio;

	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAguaBean
	 * 
	 * @param codigo
	 *            Description of the Parameter
	 * @param nome
	 *            Description of the Parameter
	 * @param municipio
	 *            Description of the Parameter
	 * @param codPref
	 *            Description of the Parameter
	 * @param indicadorUso
	 *            Description of the Parameter
	 */
	public RelatorioContratoPrestacaoServicoBean(String indicadorPessoaFisica,
			String numeroPagina, String numeroContrato, String nomeCliente,
			String nomeLocalidade, String nomeResponsavel,
			String cpfResponsavel, String rgResponsavel, String cpfCliente,
			String rgCliente, String enderecoCliente, String idImovel,
			String enderecoImovel, String categoria, String consumoMinimo, String data, String municipio) {
		this.indicadorPessoaFisica = indicadorPessoaFisica;
		this.numeroPagina = numeroPagina;
		this.numeroContrato = numeroContrato;
		this.nomeCliente = nomeCliente;
		this.nomeLocalidade = nomeLocalidade;
		this.nomeResponsavel = nomeResponsavel;
		this.cpfResponsavel = cpfResponsavel;
		this.rgResponsavel = rgResponsavel;
		this.cpfCliente = cpfCliente;
		this.rgCliente = rgCliente;
		this.enderecoCliente = enderecoCliente;
		this.idImovel = idImovel;
		this.enderecoImovel = enderecoImovel;
		this.categoria = categoria;
		this.consumoMinimo = consumoMinimo;
		this.data = data;
		this.municipio = municipio;
	}

	public String getIndicadorPessoaFisica() {
		return indicadorPessoaFisica;
	}

	public void setIndicadorPessoaFisica(String indicadorPessoaFisica) {
		this.indicadorPessoaFisica = indicadorPessoaFisica;
	}

	public String getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(String numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getConsumoMinimo() {
		return consumoMinimo;
	}

	public void setConsumoMinimo(String consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getCpfResponsavel() {
		return cpfResponsavel;
	}

	public void setCpfResponsavel(String cpfResponsavel) {
		this.cpfResponsavel = cpfResponsavel;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getEnderecoCliente() {
		return enderecoCliente;
	}

	public void setEnderecoCliente(String enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getRgCliente() {
		return rgCliente;
	}

	public void setRgCliente(String rgCliente) {
		this.rgCliente = rgCliente;
	}

	public String getRgResponsavel() {
		return rgResponsavel;
	}

	public void setRgResponsavel(String rgResponsavel) {
		this.rgResponsavel = rgResponsavel;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

}
