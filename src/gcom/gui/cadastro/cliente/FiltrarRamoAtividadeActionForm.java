package gcom.gui.cadastro.cliente;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * 
 * @author Fernando Fontelles Filho
 */

public class FiltrarRamoAtividadeActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String codigo;

	private String descricao;
	
	private String indicadorUso;
	
	private String tipoPesquisa;

	public String getMesAno(Integer anoMes){
		String anoMesStr = anoMes + "";
		String mesAno = "";
		
		mesAno = anoMesStr.substring(0,4) + "/" + anoMesStr.substring(4,6);
		
		return mesAno;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
	
}
