package gcom.micromedicao;

import com.google.gson.annotations.SerializedName;

public class DadosHidrometroInstalacaoDTO {
	
	@SerializedName("numeroInstalacao") 
	private Integer idInstalacao;
	
	@SerializedName("dataInstalacao") 
	private String dataInstalacao;
	
	@SerializedName("localInstalacao") 
	private Integer localInstalacao;
	
	@SerializedName("protecao")
	private Integer protecao;
	
	@SerializedName("trocaProtecao")
	private Integer trocaProtecao;
	
	@SerializedName("trocaRegistro")
	private Integer trocaRegistro;
	
	@SerializedName("leituraInstalacao")
	private Integer leituraInstalacao;
	
	@SerializedName("selo")
	private Integer selo;
	
	@SerializedName("cavalete")
	private Integer cavalete;
	
	@SerializedName("lacreHidrometro")
	private Integer lacreHidrometro;

	public Integer getIdInstalacao() {
		return idInstalacao;
	}

	public void setIdInstalacao(Integer idInstalacao) {
		this.idInstalacao = idInstalacao;
	}

	public String getDataInstalacao() {
		return dataInstalacao;
	}

	public void setDataInstalacao(String dataInstalacao) {
		this.dataInstalacao = dataInstalacao;
	}

	public Integer getLocalInstalacao() {
		return localInstalacao;
	}

	public void setLocalInstalacao(Integer localInstalacao) {
		this.localInstalacao = localInstalacao;
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

	public Integer getLeituraInstalacao() {
		return leituraInstalacao;
	}

	public void setLeituraInstalacao(Integer leituraInstalacao) {
		this.leituraInstalacao = leituraInstalacao;
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

	public Integer getLacreHidrometro() {
		return lacreHidrometro;
	}

	public void setLacreHidrometro(Integer lacreHidrometro) {
		this.lacreHidrometro = lacreHidrometro;
	}
	
	

}
