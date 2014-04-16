package gcom.gui.micromedicao.hidrometro;

import org.apache.struts.action.ActionForm;

public class InserirCapacidadeHidrometroActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	// private String identificador;

	private String descricao;

	private String abreviatura;

	private String numMinimo;

	private String numMaximo;

	private String numOrdem;

	private String codigo;
	
	private String indicadorUso;

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
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

	// public String getIdentificador() {
	// return identificador;
	// }
	//
	// public void setIdentificador(String identificador) {
	// this.identificador = identificador;
	// }

	public String getNumMaximo() {
		return numMaximo;
	}

	public void setNumMaximo(String numMaximo) {
		this.numMaximo = numMaximo;
	}

	public String getNumMinimo() {
		return numMinimo;
	}

	public void setNumMinimo(String numMinimo) {
		this.numMinimo = numMinimo;
	}

	public String getNumOrdem() {
		return numOrdem;
	}

	public void setNumOrdem(String numOrdem) {
		this.numOrdem = numOrdem;
	}

}
