package gcom.cadastro.imovel.bean;

import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.QuadraFace;
import gcom.cadastro.localidade.SetorComercial;

public class ImovelAbaLocalidadeRetornoHelper {
	
	private Localidade localidade;
	private SetorComercial setorComercial;
	private Quadra quadra;
	private QuadraFace quadraFace;
	private boolean flagVerificarImovelEmProcessoDeFaturamento;
	/**
	 * @return Retorna o campo localidade.
	 */
	public Localidade getLocalidade() {
		return localidade;
	}
	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}
	/**
	 * @return Retorna o campo quadra.
	 */
	public Quadra getQuadra() {
		return quadra;
	}
	/**
	 * @param quadra O quadra a ser setado.
	 */
	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}
	/**
	 * @return Retorna o campo setorComercial.
	 */
	public SetorComercial getSetorComercial() {
		return setorComercial;
	}
	/**
	 * @param setorComercial O setorComercial a ser setado.
	 */
	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}
	public QuadraFace getQuadraFace() {
		return quadraFace;
	}
	public void setQuadraFace(QuadraFace quadraFace) {
		this.quadraFace = quadraFace;
	}
	public boolean isFlagVerificarImovelEmProcessoDeFaturamento() {
		return flagVerificarImovelEmProcessoDeFaturamento;
	}
	public void setFlagVerificarImovelEmProcessoDeFaturamento(
			boolean flagVerificarImovelEmProcessoDeFaturamento) {
		this.flagVerificarImovelEmProcessoDeFaturamento = flagVerificarImovelEmProcessoDeFaturamento;
	}
}
