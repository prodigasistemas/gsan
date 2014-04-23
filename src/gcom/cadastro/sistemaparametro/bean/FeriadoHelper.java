package gcom.cadastro.sistemaparametro.bean;

import gcom.util.Util;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * de registro de atendimento
 * 
 * @author Kássia Albuquerque
 * @date 22/01/2006
 * 
 */
public class FeriadoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricao;
	private String descricaoMunicipio;
	private Date data;
	private Short tipoFeriado;
	/**
	 * @return Retorna o campo data.
	 */
	public String getData() {
	  String dataFormatada = Util.formatarData(data);
	  return dataFormatada;
	}
	/**
	 * @param data O data a ser setado.
	 */
	public void setData(Date data) {
		this.data = data;
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
	/**
	 * @return Retorna o campo descricaoMunicipio.
	 */
	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}
	/**
	 * @param descricaoMunicipio O descricaoMunicipio a ser setado.
	 */
	public void setDescricaoMunicipio(String descricaoMunicipio) {
		this.descricaoMunicipio = descricaoMunicipio;
	}
	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return Retorna o campo tipoFeriado.
	 */
	public Short getTipoFeriado() {
		return tipoFeriado;
	}
	/**
	 * @param tipoFeriado O tipoFeriado a ser setado.
	 */
	public void setTipoFeriado(Short tipoFeriado) {
		this.tipoFeriado = tipoFeriado;
	}
	
	
}
