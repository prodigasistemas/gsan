package gcom.gui.atendimentopublico;



import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0774]FILTRAR ATIVIDADE
 * 
 * @author Vinicius Medeiros
 * @date 28/04/2008
 */

public class FiltrarAtividadeActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String id;
	private String descricao;
	private String descricaoAbreviada;
	private String indicadorAtividadeUnica;
	private String indicadorUso;
	private String tipoPesquisa;
	private String indicadorAtualizar;

	public String getIndicadorAtividadeUnica() {
		return indicadorAtividadeUnica;
	}

	public void setIndicadorAtividadeUnica(String indicadorAtividadeUnica) {
		this.indicadorAtividadeUnica = indicadorAtividadeUnica;
	}

	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

}
