package gcom.micromedicao.bean;

import java.io.Serializable;


public class CalculoConsumoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String descricao;
	private Integer consumo;
	
	/**
	 * @return Retorna o campo consumo.
	 */
	public Integer getConsumo() {
		return consumo;
	}
	/**
	 * @param consumo O consumo a ser setado.
	 */
	public void setConsumo(Integer consumo) {
		this.consumo = consumo;
	}
	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
