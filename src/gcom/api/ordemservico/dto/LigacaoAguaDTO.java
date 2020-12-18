package gcom.api.ordemservico.dto;

public class LigacaoAguaDTO {

	private String data;
	private Integer diametro;
	private Integer material;
	private Integer perfil;
	private Integer localInstalacaoRamal;
	private Integer profundidadeRamal;
	private Integer distanciaInstalacaoRamal;
	private Integer pavimentoRua;
	private Integer pavimentoCalcada;
	private Integer origem;
	private Integer lacre;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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

	public Integer getProfundidadeRamal() {
		return profundidadeRamal;
	}

	public void setProfundidadeRamal(Integer profundidadeRamal) {
		this.profundidadeRamal = profundidadeRamal;
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

	public Integer getLacre() {
		return lacre;
	}

	public void setLacre(Integer lacre) {
		this.lacre = lacre;
	}
}
