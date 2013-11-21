package gcom.micromedicao;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * 
 * @author Rodrigo Cabral
 */

public class FiltrarItemServicoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	

	private String descricao; 
	
	private String descricaoAbreviada;
	
	private String indicadorUso;
	
	private String codigoConstanteCalculo;
	
	private String codigoItem;
	
	private String tipoPesquisa;

	public String getMesAno(Integer anoMes){
		String anoMesStr = anoMes + "";
		String mesAno = "";
		
		mesAno = anoMesStr.substring(0,4) + "/" + anoMesStr.substring(4,6);
		
		return mesAno;
	}


	
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



	public String getIndicadorUso() {
		return indicadorUso;
	}



	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
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



	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
	
}
