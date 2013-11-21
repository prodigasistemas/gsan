package gcom.gui.micromedicao.hidrometro;

import org.apache.struts.validator.ValidatorForm;

/**
 * 
 * @author Wallace Thierre
 */
public class FiltrarRetornoControleHidrometroActionForm extends ValidatorForm{
	
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String descricao;
	private String indicadorUso;
	private String indicadorGeracao;
	private String dataCorrente;
	private String indicadorAtualizar;
	private String tipoPesquisa;
	
	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
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
	
	public String getIndicadorGeracao() {
		return indicadorGeracao;
	}
	
	public void setIndicadorGeracao(String indicadorGeracao) {
		this.indicadorGeracao = indicadorGeracao;
	}
	
	public String getDataCorrente() {
		return dataCorrente;
	}
	
	public void setDataCorrente(String dataCorrente) {
		this.dataCorrente = dataCorrente;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}	
				 
}
