package gcom.faturamento.bean;

import java.math.BigDecimal;


/**
 * Débitos de um imóvel ou de um cliente
 * @author Fernanda Paiva
 * @since 04/01/2006
 */
public class GerarRelacaoAcompanhamentoFaturamentoHelper {
	
	private String codigoImovel;
	
	private String inscricao;
	
	private String idLocalidade;
	
	private String nomeLocalidade;
	
	private String idGerenciaRegional;
	
	private String nomeGerenciaRegional;
	
	private String categoriaPrincipal;
	
	private String nomeClienteUsuario;
	
	private String enderecoImovel;
	
	private String codigoSetorComercial;
	
	private String numeroQuadra;
	
	private String lote;
	
	private String subLote;
	
	private String quantidadeEconomias;
	
	private String situacaoAgua;
	
	private String situacaoEsgoto;
	
	private String percentualEsgoto;
	
	private String dataCorte;
	
	private BigDecimal valorFatura;
	
	private String endereco;
	
	private String nomeAbreviadoGerencia;
	
	private String valorDebito;
	
	private String esgotoFixado;
	
	private String consumoMedio;
	
	private String consumoMes;
	
	private String consumoAnormalidade;
	
	private String fatura;
	
	private String mesUm;
	
	private String mesDois;
	
	private String mesTres;
	
	private String mesQuatro;
	
	private String mesCinco;
	
	private String mesSeis;
	
	private String anormalidade;
	
	private String mesAnoFaturamento;
	
	private String dataInstalacaoHidrometro;
	
	private String variacao;
	
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
	 * @return Retorna o campo esgotoFixado.
	 */
	public String getEsgotoFixado() {
		return esgotoFixado;
	}

	/**
	 * @param esgotoFixado O esgotoFixado a ser setado.
	 */
	public void setEsgotoFixado(String esgotoFixado) {
		this.esgotoFixado = esgotoFixado;
	}

	/**
	 * @return Retorna o campo categoriaPrincipal.
	 */
	public String getCategoriaPrincipal() {
		return categoriaPrincipal;
	}

	/**
	 * @param categoriaPrincipal O categoriaPrincipal a ser setado.
	 */
	public void setCategoriaPrincipal(String categoriaPrincipal) {
		this.categoriaPrincipal = categoriaPrincipal;
	}

	/**
	 * @return Retorna o campo codigoImovel.
	 */
	public String getCodigoImovel() {
		return codigoImovel;
	}

	/**
	 * @param codigoImovel O codigoImovel a ser setado.
	 */
	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	/**
	 * @return Retorna o campo enderecoImovel.
	 */
	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	/**
	 * @param enderecoImovel O enderecoImovel a ser setado.
	 */
	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	/**
	 * @return Retorna o campo idGerenciaRegional.
	 */
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	/**
	 * @param idGerenciaRegional O idGerenciaRegional a ser setado.
	 */
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
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
	 * @return Retorna o campo nomeClienteUsuario.
	 */
	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}

	/**
	 * @param nomeClienteUsuario O nomeClienteUsuario a ser setado.
	 */
	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}

	/**
	 * @return Retorna o campo nomeGerenciaRegional.
	 */
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}

	/**
	 * @param nomeGerenciaRegional O nomeGerenciaRegional a ser setado.
	 */
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}

	/**
	 * @return Retorna o campo nomeLocalidade.
	 */
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	/**
	 * @param nomeLocalidade O nomeLocalidade a ser setado.
	 */
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	/**
	 * @return Retorna o campo codigoSetorComercial.
	 */
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	/**
	 * @param codigoSetorComercial O codigoSetorComercial a ser setado.
	 */
	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	/**
	 * @return Retorna o campo lote.
	 */
	public String getLote() {
		return lote;
	}

	/**
	 * @param lote O lote a ser setado.
	 */
	public void setLote(String lote) {
		this.lote = lote;
	}

	/**
	 * @return Retorna o campo numeroQuadra.
	 */
	public String getNumeroQuadra() {
		return numeroQuadra;
	}

	/**
	 * @param numeroQuadra O numeroQuadra a ser setado.
	 */
	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	/**
	 * @return Retorna o campo subLote.
	 */
	public String getSubLote() {
		return subLote;
	}

	/**
	 * @param subLote O subLote a ser setado.
	 */
	public void setSubLote(String subLote) {
		this.subLote = subLote;
	}

	/**
	 * @return Retorna o campo dataCorte.
	 */
	public String getDataCorte() {
		return dataCorte;
	}

	/**
	 * @param dataCorte O dataCorte a ser setado.
	 */
	public void setDataCorte(String dataCorte) {
		this.dataCorte = dataCorte;
	}

	/**
	 * @return Retorna o campo percentualEsgoto.
	 */
	public String getPercentualEsgoto() {
		return percentualEsgoto;
	}

	/**
	 * @param percentualEsgoto O percentualEsgoto a ser setado.
	 */
	public void setPercentualEsgoto(String percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}

	/**
	 * @return Retorna o campo quantidadeEconomias.
	 */
	public String getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	/**
	 * @param quantidadeEconomias O quantidadeEconomias a ser setado.
	 */
	public void setQuantidadeEconomias(String quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
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
	 * @return Retorna o campo fatura.
	 */
	public BigDecimal getValorFatura() {
		return valorFatura;
	}

	/**
	 * @param fatura O fatura a ser setado.
	 */
	public void setValorFatura(BigDecimal valorFatura) {
		this.valorFatura = valorFatura;
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
	 * @return Retorna o campo nomeAbreviadoGerencia.
	 */
	public String getNomeAbreviadoGerencia() {
		return nomeAbreviadoGerencia;
	}

	/**
	 * @param nomeAbreviadoGerencia O nomeAbreviadoGerencia a ser setado.
	 */
	public void setNomeAbreviadoGerencia(String nomeAbreviadoGerencia) {
		this.nomeAbreviadoGerencia = nomeAbreviadoGerencia;
	}

	/**
	 * @return Retorna o campo valorDebito.
	 */
	public String getValorDebito() {
		return valorDebito;
	}

	/**
	 * @param valorDebito O valorDebito a ser setado.
	 */
	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}

	/**
	 * @return Retorna o campo consumoMedio.
	 */
	public String getConsumoMedio() {
		return consumoMedio;
	}

	/**
	 * @param consumoMedio O consumoMedio a ser setado.
	 */
	public void setConsumoMedio(String consumoMedio) {
		this.consumoMedio = consumoMedio;
	}

	/**
	 * @return Retorna o campo consumoMes.
	 */
	public String getConsumoMes() {
		return consumoMes;
	}

	/**
	 * @param consumoMes O consumoMes a ser setado.
	 */
	public void setConsumoMes(String consumoMes) {
		this.consumoMes = consumoMes;
	}

	/**
	 * @return Retorna o campo consumoAnormalidade.
	 */
	public String getConsumoAnormalidade() {
		return consumoAnormalidade;
	}

	/**
	 * @param consumoAnormalidade O consumoAnormalidade a ser setado.
	 */
	public void setConsumoAnormalidade(String consumoAnormalidade) {
		this.consumoAnormalidade = consumoAnormalidade;
	}

	/**
	 * @return Retorna o campo fatura.
	 */
	public String getFatura() {
		return fatura;
	}

	/**
	 * @param fatura O fatura a ser setado.
	 */
	public void setFatura(String fatura) {
		this.fatura = fatura;
	}

	/**
	 * @return Retorna o campo mesCinco.
	 */
	public String getMesCinco() {
		return mesCinco;
	}

	/**
	 * @param mesCinco O mesCinco a ser setado.
	 */
	public void setMesCinco(String mesCinco) {
		this.mesCinco = mesCinco;
	}

	/**
	 * @return Retorna o campo mesDois.
	 */
	public String getMesDois() {
		return mesDois;
	}

	/**
	 * @param mesDois O mesDois a ser setado.
	 */
	public void setMesDois(String mesDois) {
		this.mesDois = mesDois;
	}

	/**
	 * @return Retorna o campo mesQuatro.
	 */
	public String getMesQuatro() {
		return mesQuatro;
	}

	/**
	 * @param mesQuatro O mesQuatro a ser setado.
	 */
	public void setMesQuatro(String mesQuatro) {
		this.mesQuatro = mesQuatro;
	}

	/**
	 * @return Retorna o campo mesSeis.
	 */
	public String getMesSeis() {
		return mesSeis;
	}

	/**
	 * @param mesSeis O mesSeis a ser setado.
	 */
	public void setMesSeis(String mesSeis) {
		this.mesSeis = mesSeis;
	}

	/**
	 * @return Retorna o campo mesTres.
	 */
	public String getMesTres() {
		return mesTres;
	}

	/**
	 * @param mesTres O mesTres a ser setado.
	 */
	public void setMesTres(String mesTres) {
		this.mesTres = mesTres;
	}

	/**
	 * @return Retorna o campo mesUm.
	 */
	public String getMesUm() {
		return mesUm;
	}

	/**
	 * @param mesUm O mesUm a ser setado.
	 */
	public void setMesUm(String mesUm) {
		this.mesUm = mesUm;
	}

	/**
	 * @return Retorna o campo mesAnoFaturamento.
	 */
	public String getMesAnoFaturamento() {
		return mesAnoFaturamento;
	}

	/**
	 * @param mesAnoFaturamento O mesAnoFaturamento a ser setado.
	 */
	public void setMesAnoFaturamento(String mesAnoFaturamento) {
		this.mesAnoFaturamento = mesAnoFaturamento;
	}

	/**
	 * @return Retorna o campo dataInstalacaoHidrometro.
	 */
	public String getDataInstalacaoHidrometro() {
		return dataInstalacaoHidrometro;
	}

	/**
	 * @param dataInstalacaoHidrometro O dataInstalacaoHidrometro a ser setado.
	 */
	public void setDataInstalacaoHidrometro(String dataInstalacaoHidrometro) {
		this.dataInstalacaoHidrometro = dataInstalacaoHidrometro;
	}

	/**
	 * @return Retorna o campo variacao.
	 */
	public String getVariacao() {
		return variacao;
	}

	/**
	 * @param variacao O variacao a ser setado.
	 */
	public void setVariacao(String variacao) {
		this.variacao = variacao;
	}
	
}
