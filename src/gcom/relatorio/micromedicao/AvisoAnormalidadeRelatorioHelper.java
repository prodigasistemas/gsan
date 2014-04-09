package gcom.relatorio.micromedicao;

import java.math.BigDecimal;


public class AvisoAnormalidadeRelatorioHelper {
	
	private static final long serialVersionUID = 1L;
	
	private String nomeCliente;
	private String endereco;
	private String inscricao;
	private Integer idImovel;
	private Short codigoRota;
	private Integer sequencialRota;
	private Integer anoMes;
	private String descricaoAnormalidadeConsumo;
	private String nomeMunicipio;
	private Integer consumoFaturado;
	private Integer consumoMedio;
	private Integer consumoMedido;
	private BigDecimal variacaoConsumo;
	
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
	/**
	 * @return Retorna o campo anoMes.
	 */
	public Integer getAnoMes() {
		return anoMes;
	}
	/**
	 * @param anoMes O anoMes a ser setado.
	 */
	public void setAnoMes(Integer anoMes) {
		this.anoMes = anoMes;
	}
	/**
	 * @return Retorna o campo codigoRota.
	 */
	public Short getCodigoRota() {
		return codigoRota;
	}
	/**
	 * @param codigoRota O codigoRota a ser setado.
	 */
	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
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
	 * @return Retorna o campo idImovel.
	 */
	public Integer getIdImovel() {
		return idImovel;
	}
	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
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
	 * @return Retorna o campo sequencialRota.
	 */
	public Integer getSequencialRota() {
		return sequencialRota;
	}
	/**
	 * @param sequencialRota O sequencialRota a ser setado.
	 */
	public void setSequencialRota(Integer sequencialRota) {
		this.sequencialRota = sequencialRota;
	}
	/**
	 * @return Retorna o campo descricaoAnormalidadeConsumo.
	 */
	public String getDescricaoAnormalidadeConsumo() {
		return descricaoAnormalidadeConsumo;
	}
	/**
	 * @param descricaoAnormalidadeConsumo O descricaoAnormalidadeConsumo a ser setado.
	 */
	public void setDescricaoAnormalidadeConsumo(String descricaoAnormalidadeConsumo) {
		this.descricaoAnormalidadeConsumo = descricaoAnormalidadeConsumo;
	}
	/**
	 * @return Retorna o campo nomeMunicipio.
	 */
	public String getNomeMunicipio() {
		return nomeMunicipio;
	}
	/**
	 * @param nomeMunicipio O nomeMunicipio a ser setado.
	 */
	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
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

}
