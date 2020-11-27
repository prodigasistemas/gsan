package gcom.api.ordemServico.DTO;

import com.google.gson.annotations.SerializedName;

public class DadosLigacaoAguaDTO {
	
	@SerializedName("dataLigacao")
	private String dataLigacao;
	
	@SerializedName("diametro")
	private Integer diametro;
	
	@SerializedName("material")
	private Integer material;
	
	@SerializedName("perfil")
	private Integer perfil;
	
	@SerializedName("localInstalacaoRamal") 
	private Integer localInstalacaoRamal;
	
	@SerializedName("profundidade")
	private Integer profundidade;
	
	@SerializedName("distanciaInstalacaoRamal") 
	private Integer distanciaInstalacaoRamal;
	
	@SerializedName("pavimentoRua") 
	private Integer pavimentoRua;
	
	@SerializedName("pavimentoCalcada") 
	private Integer pavimentoCalcada;
	
	@SerializedName("origem") 
	private Integer origem;
	
	@SerializedName("lacreAgua")
	private Integer lacreAgua;

	public String getDataLigacao() {
		return dataLigacao;
	}

	public void setDataLigacao(String dataLigacao) {
		this.dataLigacao = dataLigacao;
	}

	public Integer getDiametro() {
		return diametro;
	}

	public void setDiametro(Integer diametro) {
		this.diametro = diametro;
	}

	public Integer getMaterial() {
		return material;
	}

	public void setMaterial(Integer material) {
		this.material = material;
	}

	public Integer getPerfil() {
		return perfil;
	}

	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}

	public Integer getLocalInstalacaoRamal() {
		return localInstalacaoRamal;
	}

	public void setLocalInstalacaoRamal(Integer localInstalacaoRamal) {
		this.localInstalacaoRamal = localInstalacaoRamal;
	}

	public Integer getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(Integer profundidade) {
		this.profundidade = profundidade;
	}

	public Integer getDistanciaInstalacaoRamal() {
		return distanciaInstalacaoRamal;
	}

	public void setDistanciaInstalacaoRamal(Integer distanciaInstalacaoRamal) {
		this.distanciaInstalacaoRamal = distanciaInstalacaoRamal;
	}

	public Integer getPavimentoRua() {
		return pavimentoRua;
	}

	public void setPavimentoRua(Integer pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}

	public Integer getPavimentoCalcada() {
		return pavimentoCalcada;
	}

	public void setPavimentoCalcada(Integer pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}

	public Integer getOrigem() {
		return origem;
	}

	public void setOrigem(Integer origem) {
		this.origem = origem;
	}

	public Integer getLacreAgua() {
		return lacreAgua;
	}

	public void setLacreAgua(Integer lacreAgua) {
		this.lacreAgua = lacreAgua;
	}
	
	

}
