package gcom.cadastro.imovel.bean;

import java.util.Date;

public class EmitirConsumoImovelCondominimoHelper {

	/**
	 * Emiti o extrato de consumo dos imoveis condominios
	 * apartir de uma colecao de rotas
	 *
	 * [UC0493] Emitir Extrato de consumo de imovel condominio
	 *
	 * @author Flávio Cordeiro
	 * @date 08/01/2007
	 *
	 * @param args
	 */
	
	private Integer idImovel;
	private Integer idLocalidade;
	private String nomeLocalidade;
	private Integer idCliente;
	private String nomeCliente;
	private String inscricaoImovel;
	private String endereco;
	private String mesAnoDigito;
	private Date dataLeituraAtualFat;
	private Integer numeroLeituraAtualFat;
	private Integer consumoFaturado;
	private String descricaoLeituraSituacao;
	private String descricaoConsumoTipo;
	private String descricaoAnormalidadeConsumo;
	private Integer qtdEconomias;
	private Integer situacaoAgua;
	private Integer situacaoEsgoto;
	private String abreviadaConsumoTipo;
	private Integer anormalidadeLeituraFat;
	private String abreviadaConsumoAnormalidade;
	private Integer perfilImovel;
	private Date dataLeituraAnteriorFat;
	private Integer consumoMedio;
	private Integer rateio;
	private Integer idEmpresa;
	private Integer idLeituraSituacao;
	
	public Integer getIdLeituraSituacao() {
		return idLeituraSituacao;
	}

	public void setIdLeituraSituacao(Integer idLeituraSituacao) {
		this.idLeituraSituacao = idLeituraSituacao;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getAbreviadaConsumoAnormalidade() {
		return abreviadaConsumoAnormalidade;
	}

	public void setAbreviadaConsumoAnormalidade(String abreviadaConsumoAnormalidade) {
		this.abreviadaConsumoAnormalidade = abreviadaConsumoAnormalidade;
	}

	public Integer getAnormalidadeLeituraFat() {
		return anormalidadeLeituraFat;
	}

	public void setAnormalidadeLeituraFat(Integer anormalidadeLeituraFat) {
		this.anormalidadeLeituraFat = anormalidadeLeituraFat;
	}

	public Integer getConsumoMedio() {
		return consumoMedio;
	}

	public void setConsumoMedio(Integer consumoMedio) {
		this.consumoMedio = consumoMedio;
	}

	public Date getDataLeituraAnteriorFat() {
		return dataLeituraAnteriorFat;
	}

	public void setDataLeituraAnteriorFat(Date dataLeituraAnteriorFat) {
		this.dataLeituraAnteriorFat = dataLeituraAnteriorFat;
	}

	public Integer getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(Integer perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public Integer getQtdEconomias() {
		return qtdEconomias;
	}

	public void setQtdEconomias(Integer qtdEconomias) {
		this.qtdEconomias = qtdEconomias;
	}

	public String getDescricaoConsumoTipo() {
		return descricaoConsumoTipo;
	}

	public void setDescricaoConsumoTipo(String descricaoConsumoTipo) {
		this.descricaoConsumoTipo = descricaoConsumoTipo;
	}

	public String getDescricaoLeituraSituacao() {
		return descricaoLeituraSituacao;
	}

	public void setDescricaoLeituraSituacao(String descricaoLeituraSituacao) {
		this.descricaoLeituraSituacao = descricaoLeituraSituacao;
	}

	public Integer getConsumoFaturado() {
		return consumoFaturado;
	}

	public void setConsumoFaturado(Integer consumoFaturado) {
		this.consumoFaturado = consumoFaturado;
	}

	public Date getDataLeituraAtualFat() {
		return dataLeituraAtualFat;
	}

	public void setDataLeituraAtualFat(Date dataLeituraAtualFat) {
		this.dataLeituraAtualFat = dataLeituraAtualFat;
	}

	public Integer getNumeroLeituraAtualFat() {
		return numeroLeituraAtualFat;
	}

	public void setNumeroLeituraAtualFat(Integer numeroLeituraAtualFat) {
		this.numeroLeituraAtualFat = numeroLeituraAtualFat;
	}

	public String getMesAnoDigito() {
		return mesAnoDigito;
	}

	public void setMesAnoDigito(String mesAnoDigito) {
		this.mesAnoDigito = mesAnoDigito;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getDescricaoAnormalidadeConsumo() {
		return descricaoAnormalidadeConsumo;
	}

	public void setDescricaoAnormalidadeConsumo(String descricaoAnormalidadeConsumo) {
		this.descricaoAnormalidadeConsumo = descricaoAnormalidadeConsumo;
	}

	public Integer getSituacaoAgua() {
		return situacaoAgua;
	}

	public void setSituacaoAgua(Integer situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	public Integer getSituacaoEsgoto() {
		return situacaoEsgoto;
	}

	public void setSituacaoEsgoto(Integer situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}

	public Integer getRateio() {
		return rateio;
	}

	public void setRateio(Integer rateio) {
		this.rateio = rateio;
	}

	public String getAbreviadaConsumoTipo() {
		return abreviadaConsumoTipo;
	}

	public void setAbreviadaConsumoTipo(String abreviadaConsumoTipo) {
		this.abreviadaConsumoTipo = abreviadaConsumoTipo;
	}

}
