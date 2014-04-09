package gcom.micromedicao;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1065] Manter Item de Servico
 * 
 * @author Rodrigo Cabral
 * @date 04/08/2010
 */

public class AtualizarItemServicoActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String id;
	
	private String descricao;
	
	private String descricaoAbreviada;
	
	private String codigoConstanteCalculo;
	
	private String codigoItem;
	
	private String indicadorUso;

	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public String getCodigoConstanteCalculo() {
		return codigoConstanteCalculo;
	}

	public void setCodigoConstanteCalculo(String codigoConstanteCalculo) {
		this.codigoConstanteCalculo = codigoConstanteCalculo;
	}

	public String getCodigoItem() {
		return codigoItem;
	}

	public void setCodigoItem(String codigoItem) {
		this.codigoItem = codigoItem;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	
}
