package gcom.gui.cadastro;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0774]FILTRAR Empresa
 * 
 * @author Arthur Carvalho
 * @date 14/05/2008
 */

public class FiltrarEmpresaActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String id;

	private String descricao;

	private String descricaoAbreviada;

	private String indicadorEmpresaPrincipal;

	private String indicadorUso;

	private String tipoPesquisa;

	private String indicadorAtualizar;

	private String email;

	private String indicadorEmpresaCobranca;
	
	private String indicadorEmpresaEntregaContas;

	public String getIndicadorEmpresaCobranca() {
		return indicadorEmpresaCobranca;
	}

	public void setIndicadorEmpresaCobranca(String indicadorEmpresaCobranca) {
		this.indicadorEmpresaCobranca = indicadorEmpresaCobranca;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}

	public String getIndicadorEmpresaPrincipal() {
		return indicadorEmpresaPrincipal;
	}

	public void setIndicadorEmpresaPrincipal(String indicadorEmpresaPrincipal) {
		this.indicadorEmpresaPrincipal = indicadorEmpresaPrincipal;
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

	public String getIndicadorEmpresaEntregaContas() {
		return indicadorEmpresaEntregaContas;
	}

	public void setIndicadorEmpresaEntregaContas(
			String indicadorEmpresaEntregaContas) {
		this.indicadorEmpresaEntregaContas = indicadorEmpresaEntregaContas;
	}
	

}
