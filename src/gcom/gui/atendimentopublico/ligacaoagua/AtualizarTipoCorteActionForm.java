package gcom.gui.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.ligacaoagua.CorteTipo;

import org.apache.struts.validator.ValidatorForm;


/**
 * [UC1103] MANTER Tipo de Conta
 *
 * @author Ivan Sergio
 * @date 03/12/2010
 */
public class AtualizarTipoCorteActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String idTipoCorte;
	private String descricao;
	private String indicadorUso;
	private String indicadorCorteAdministrativo;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getIdTipoCorte() {
		return idTipoCorte;
	}
	public void setIdTipoCorte(String idTipoCorte) {
		this.idTipoCorte = idTipoCorte;
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
	
	public CorteTipo setFormValues(CorteTipo corteTipo) {
		
		corteTipo.setId(new Integer(getIdTipoCorte()));
		corteTipo.setDescricao(getDescricao());
		corteTipo.setIndicadorUso(new Short(getIndicadorUso()));
		corteTipo.setIndicadorCorteAdministrativo(new Short(getIndicadorCorteAdministrativo()));

		return corteTipo;
	}
}
