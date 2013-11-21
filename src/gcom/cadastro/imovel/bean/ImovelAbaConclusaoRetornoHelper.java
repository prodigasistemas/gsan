package gcom.cadastro.imovel.bean;

import gcom.micromedicao.Rota;

public class ImovelAbaConclusaoRetornoHelper {

	private String sequencialRotaEntrega;
	private Rota rotaEntrega;
	private Rota rotaAlternativa;
	
	/**
	 * @return Retorna o campo rotaAlternativa.
	 */
	public Rota getRotaAlternativa() {
		return rotaAlternativa;
	}
	/**
	 * @param rotaAlternativa O campo rotaAlternativa.
	 */
	public void setRotaAlternativa(Rota rotaAlternativa) {
		this.rotaAlternativa = rotaAlternativa;
	}
	/**
	 * @return Retorna o campo rotaEntrega.
	 */
	public Rota getRotaEntrega() {
		return rotaEntrega;
	}
	/**
	 * @param rotaEntrega O rotaEntrega a ser setado.
	 */
	public void setRotaEntrega(Rota rotaEntrega) {
		this.rotaEntrega = rotaEntrega;
	}
	/**
	 * @return Retorna o campo sequencialRotaEntrega.
	 */
	public String getSequencialRotaEntrega() {
		return sequencialRotaEntrega;
	}
	/**
	 * @param sequencialRotaEntrega O sequencialRotaEntrega a ser setado.
	 */
	public void setSequencialRotaEntrega(String sequencialRotaEntrega) {
		this.sequencialRotaEntrega = sequencialRotaEntrega;
	}

}
