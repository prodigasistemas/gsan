package gcom.api.ordemservico.dto;

public class HidrometroInstalacaoDTO {

	private Integer numero;

	private String data;

	private Integer local;

	private Integer protecao;

	private Integer trocaProtecao;

	private Integer trocaRegistro;

	private Integer leitura;

	private Integer selo;

	private Integer cavalete;

	private Integer lacre;

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getLocal() {
		return local;
	}

	public void setLocal(Integer local) {
		this.local = local;
	}

	public Integer getProtecao() {
		return protecao;
	}

	public void setProtecao(Integer protecao) {
		this.protecao = protecao;
	}

	public Integer getTrocaProtecao() {
		return trocaProtecao;
	}

	public void setTrocaProtecao(Integer trocaProtecao) {
		this.trocaProtecao = trocaProtecao;
	}

	public Integer getTrocaRegistro() {
		return trocaRegistro;
	}

	public void setTrocaRegistro(Integer trocaRegistro) {
		this.trocaRegistro = trocaRegistro;
	}

	public Integer getLeitura() {
		return leitura;
	}

	public void setLeitura(Integer leitura) {
		this.leitura = leitura;
	}

	public Integer getSelo() {
		return selo;
	}

	public void setSelo(Integer selo) {
		this.selo = selo;
	}

	public Integer getCavalete() {
		return cavalete;
	}

	public void setCavalete(Integer cavalete) {
		this.cavalete = cavalete;
	}

	public Integer getLacre() {
		return lacre;
	}

	public void setLacre(Integer lacre) {
		this.lacre = lacre;
	}
}
