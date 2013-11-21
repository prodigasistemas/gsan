package gcom.gui.micromedicao;

import java.io.Serializable;

public class DadosLeiturista implements Serializable,Comparable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String descricao;

	/**
	 * @param id
	 * @param descricao
	 */
	public DadosLeiturista(Integer id, String descricao) {
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

	public int compareTo(Object o) {
		
		int retorno = 0;
		
		if (o instanceof DadosLeiturista) {
			DadosLeiturista dados = (DadosLeiturista) o;
			
			retorno =  this.getDescricao().compareToIgnoreCase(dados.getDescricao());
			
		}
		
		return retorno;
	}
	
	

}
