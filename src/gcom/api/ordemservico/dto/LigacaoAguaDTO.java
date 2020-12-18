package gcom.api.ordemservico.dto;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaDiametro;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaMaterial;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gcom.atendimentopublico.ligacaoagua.RamalLocalInstalacao;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.util.ConstantesSistema;

public class LigacaoAguaDTO {

	private String data;

	private Integer diametro;

	private Integer material;

	private Integer perfil;

	private Integer localInstalacaoRamal;

	private String profundidadeRamal;

	private String distanciaInstalacaoRamal;

	private Integer pavimentoRua;

	private Integer pavimentoCalcada;

	private Integer origem;

	private String lacre;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public LigacaoAguaDiametro getDiametro() {
		return new LigacaoAguaDiametro(diametro);
	}

	public void setDiametro(Integer diametro) {
		this.diametro = diametro;
	}

	public LigacaoAguaMaterial getMaterial() {
		return new LigacaoAguaMaterial(material);
	}

	public void setMaterial(Integer material) {
		this.material = material;
	}

	public LigacaoAguaPerfil getPerfil() {
		return new LigacaoAguaPerfil(perfil);
	}

	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}

	public RamalLocalInstalacao getLocalInstalacaoRamal() {
		if (campoValido(localInstalacaoRamal)) {
			return new RamalLocalInstalacao(localInstalacaoRamal);
		}
		return null;
	}

	public void setLocalInstalacaoRamal(Integer localInstalacaoRamal) {
		this.localInstalacaoRamal = localInstalacaoRamal;
	}

	public BigDecimal getProfundidadeRamal() {
		if (campoNumericoValido(profundidadeRamal)) {
			return new BigDecimal(profundidadeRamal);
		}
		return null;
	}

	public void setProfundidadeRamal(String profundidadeRamal) {
		this.profundidadeRamal = profundidadeRamal;
	}

	public BigDecimal getDistanciaInstalacaoRamal() {
		if (campoNumericoValido(distanciaInstalacaoRamal)) {
			return new BigDecimal(distanciaInstalacaoRamal);
		}
		return null;
	}

	public void setDistanciaInstalacaoRamal(String distanciaInstalacaoRamal) {
		this.distanciaInstalacaoRamal = distanciaInstalacaoRamal;
	}

	public PavimentoRua getPavimentoRua() {
		if (campoValido(pavimentoRua)) {
			return new PavimentoRua(pavimentoRua);
		}
		return null;
	}

	public void setPavimentoRua(Integer pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}

	public PavimentoCalcada getPavimentoCalcada() {
		if (campoValido(pavimentoCalcada)) {
			return new PavimentoCalcada(pavimentoCalcada);
		}
		return null;
	}

	public void setPavimentoCalcada(Integer pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}

	public LigacaoOrigem getOrigem() {
		if (campoValido(origem)) {
			return new LigacaoOrigem(origem);
		}
		return null;
	}

	public void setOrigem(Integer origem) {
		this.origem = origem;
	}

	public String getLacre() {
		return lacre;
	}

	public void setLacre(String lacre) {
		this.lacre = lacre;
	}

	private boolean campoNumericoValido(String campo) {
		return campo != null && !campo.trim().equals("") && StringUtils.isNumeric(campo);
	}

	private boolean campoValido(Integer campo) {
		return campo != null && !campo.equals(ConstantesSistema.NUMERO_NAO_INFORMADO);
	}
}
