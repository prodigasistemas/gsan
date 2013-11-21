package gcom.gui.relatorio.micromedicao;

import java.io.Serializable;

public class FiltrarRelatorioAcompanhamentoLeituristaHelper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Short codigo;

	private String descricao;
	


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getCodigo() {
		return codigo;
	}

	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FiltrarRelatorioAcompanhamentoLeituristaHelper(Integer id,
			Short codigo, String descricao) {
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
		
	}

}
