package gcom.relatorio.micromedicao;

import java.math.BigDecimal;

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
public class RelatorioAvisoAnormalidadeBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;

	private String nomeCliente;

	private String endereco;

	private String matricula;
	
	private String inscricao;
	
	private String rota;
	
	private String sequencialRota;

	private String mesAno;
	
	private String anormalidade;
	
	private String municipio;
	
	private Integer consumoFaturado;
	
	private Integer consumoMedio;
	
	private BigDecimal variacaoConsumo;
	
	private Integer consumoMedido;

	/**
	 * Construtor da classe RelatorioAvisoAnormalidadeBean
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
	public RelatorioAvisoAnormalidadeBean(String nomeCliente, String endereco,
			String matricula, String inscricao, String rota, String sequencialRota,
			String mesAno, String anormalidade, String municipio,
			Integer consumoFaturado, Integer consumoMedio, BigDecimal variacaoConsumo, Integer consumoMedido) {
		this.nomeCliente = nomeCliente;
		this.endereco = endereco;
		this.matricula = matricula;
		this.inscricao = inscricao;
		this.rota = rota;
		this.sequencialRota = sequencialRota;
		this.mesAno = mesAno;
		this.anormalidade = anormalidade;
		this.municipio = municipio;
		this.consumoFaturado = consumoFaturado;
		this.consumoMedio = consumoMedio;
		this.variacaoConsumo = variacaoConsumo;
		this.consumoMedido = consumoMedido;
	}

	/**
	 * @return Retorna o campo anormalidade.
	 */
	public String getAnormalidade() {
		return anormalidade;
	}

	/**
	 * @param anormalidade O anormalidade a ser setado.
	 */
	public void setAnormalidade(String anormalidade) {
		this.anormalidade = anormalidade;
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
	 * @return Retorna o campo matricula.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * @param matricula O matricula a ser setado.
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return Retorna o campo mesAno.
	 */
	public String getMesAno() {
		return mesAno;
	}

	/**
	 * @param mesAno O mesAno a ser setado.
	 */
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	/**
	 * @return Retorna o campo nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente O nomeCliente a ser setado.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * @return Retorna o campo municipio.
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * @param municipio O municipio a ser setado.
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	/**
	 * @return Retorna o campo rota.
	 */
	public String getRota() {
		return rota;
	}

	/**
	 * @param rota O rota a ser setado.
	 */
	public void setRota(String rota) {
		this.rota = rota;
	}

	/**
	 * @return Retorna o campo sequencialRota.
	 */
	public String getSequencialRota() {
		return sequencialRota;
	}

	/**
	 * @param sequencialRota O sequencialRota a ser setado.
	 */
	public void setSequencialRota(String sequencialRota) {
		this.sequencialRota = sequencialRota;
	}

	/**
	 * @return Retorna o campo inscricao.
	 */
	public String getInscricao() {
		return inscricao;
	}

	/**
	 * @param inscricao O inscricao a ser setado.
	 */
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	/**
	 * @return Retorna o campo consumoFaturado.
	 */
	public Integer getConsumoFaturado() {
		return consumoFaturado;
	}

	/**
	 * @param consumoFaturado O consumoFaturado a ser setado.
	 */
	public void setConsumoFaturado(Integer consumoFaturado) {
		this.consumoFaturado = consumoFaturado;
	}

	/**
	 * @return Retorna o campo consumoMedido.
	 */
	public Integer getConsumoMedido() {
		return consumoMedido;
	}

	/**
	 * @param consumoMedido O consumoMedido a ser setado.
	 */
	public void setConsumoMedido(Integer consumoMedido) {
		this.consumoMedido = consumoMedido;
	}

	/**
	 * @return Retorna o campo consumoMedio.
	 */
	public Integer getConsumoMedio() {
		return consumoMedio;
	}

	/**
	 * @param consumoMedio O consumoMedio a ser setado.
	 */
	public void setConsumoMedio(Integer consumoMedio) {
		this.consumoMedio = consumoMedio;
	}

	/**
	 * @return Retorna o campo variacaoConsumo.
	 */
	public BigDecimal getVariacaoConsumo() {
		return variacaoConsumo;
	}

	/**
	 * @param variacaoConsumo O variacaoConsumo a ser setado.
	 */
	public void setVariacaoConsumo(BigDecimal variacaoConsumo) {
		this.variacaoConsumo = variacaoConsumo;
	}
	
	

}
