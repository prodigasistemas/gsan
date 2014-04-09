package gcom.gui.atendimentopublico.ligacaoagua;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author Ivan Sergio
 * @date 02/12/2010
 */
public class InserirTipoCorteActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String descricao;
	private String indicadorUso;
	private String indicadorCorteAdministrativo;
	
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
}
