package gcom.gui.micromedicao;

import java.io.Serializable;

public class DadosRota implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String descricao;

	/**
	 * @param id
	 * @param descricao
	 */
	public DadosRota(Integer id, String descricao) {
		super();
		
		this.id = id;
		this.descricao = descricao;
	}

	/**
	 * @return Returns the descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao The descricao to set.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return Returns the id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	

}
