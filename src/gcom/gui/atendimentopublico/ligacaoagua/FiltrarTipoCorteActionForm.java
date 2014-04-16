package gcom.gui.atendimentopublico.ligacaoagua;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC1103] Filtrar Tipo de Corte
 *
 * @author Ivan Sergio
 * @date 03/12/2010
 */
public class FiltrarTipoCorteActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
	
	private String idTipoCorte;
	private String descricao;
	private String indicadorUso;
	private String indicadorCorteAdministrativo;
	private String tipoPesquisa;
	private String indicadorAtualizar;
	
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
	public String getIndicadorCorteAdministrativo() {
		return indicadorCorteAdministrativo;
	}
	public void setIndicadorCorteAdministrativo(String indicadorCorteAdministrativo) {
		this.indicadorCorteAdministrativo = indicadorCorteAdministrativo;
	}
	public String getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public String getIdTipoCorte() {
		return idTipoCorte;
	}
	public void setIdTipoCorte(String idTipoCorte) {
		this.idTipoCorte = idTipoCorte;
	}
	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}
	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}
}
