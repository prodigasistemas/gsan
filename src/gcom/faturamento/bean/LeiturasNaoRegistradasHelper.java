package gcom.faturamento.bean;

import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;


public class LeiturasNaoRegistradasHelper {

	private Localidade localidade;
	
	private SetorComercial setorComercial;
	
	private Short codigoRota;
	
	private Integer valorTotalImoveis;
	
	public Short getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}

	public SetorComercial getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Integer getValorTotalImoveis() {
		return valorTotalImoveis;
	}

	public void setValorTotalImoveis(Integer valorTotalImoveis) {
		this.valorTotalImoveis = valorTotalImoveis;
	}

}
