package gcom.gui.cadastro;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0837]FILTRAR FONTE DE ABASTECIMENTO
 * 
 * @author Arthur Carvalho
 * @date 14/08/2008
 */

public class FiltrarFonteAbastecimentoActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String id;
	private String descricao;
	private String descricaoAbreviada;
	private String indicadorUso;
	private String indicadorCalcularVolumeFixo;
	private String indicadorAtualizar;
	private String tipoPesquisa;
	private String indicadorPermitePoco;

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
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
	public String getIndicadorCalcularVolumeFixo() {
		return indicadorCalcularVolumeFixo;
	}
	public void setIndicadorCalcularVolumeFixo(String indicadorCalcularVolumeFixo) {
		this.indicadorCalcularVolumeFixo = indicadorCalcularVolumeFixo;
	}
	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}
	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
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
	public String getIndicadorPermitePoco() {
		return indicadorPermitePoco;
	}
	public void setIndicadorPermitePoco(String indicadorPermitePoco) {
		this.indicadorPermitePoco = indicadorPermitePoco;
	}


}	
