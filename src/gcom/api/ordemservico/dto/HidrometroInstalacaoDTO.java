package gcom.api.ordemservico.dto;

public class HidrometroInstalacaoDTO {

	private String numero;

	private String data;

	private Integer tipoMedicao;

	private Integer local;

	private Integer protecao;

	private Integer trocaProtecao;

	private Integer trocaRegistro;

	private String leitura;

	private String selo;

	private Integer cavalete;

	private String lacre;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getTipoMedicao() {
		return tipoMedicao;
	}

	public void setTipoMedicao(Integer tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
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
		if (leitura != null && !leitura.trim().equals("")) {
			return Integer.parseInt(leitura);
		}
		return 0;
	}

	public void setLeitura(String leitura) {
		this.leitura = leitura;
	}

	public String getSelo() {
		return selo;
	}

	public void setSelo(String selo) {
		this.selo = selo;
	}

	public Integer getCavalete() {
		return cavalete;
	}

	public void setCavalete(Integer cavalete) {
		this.cavalete = cavalete;
	}

	public String getLacre() {
		return lacre;
	}

	public void setLacre(String lacre) {
		this.lacre = lacre;
	}
}
