package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0386]Filtrar Tipo Perfil de Serviço
 *
 * @author Kássia Albuquerque
 * @date 25/10/2006
 */


public class FiltrarTipoPerfilServicoActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
private String indicadorAtualizar;
private String codigoPerfilServico;
private String descricaoPerfilServico;
private String abrevPerfilServico;
private String qtdComponentesEquipe;
private String idEquipamentoEspecial;
private String descricaoEquipamentoEspecial;
private String indicadorVeiculoProprio;
private String indicadorUso;


public String getAbrevPerfilServico() {
	return abrevPerfilServico;
}
public void setAbrevPerfilServico(String abrevPerfilServico) {
	this.abrevPerfilServico = abrevPerfilServico;
}
public String getCodigoPerfilServico() {
	return codigoPerfilServico;
}
public void setCodigoPerfilServico(String codigoPerfilServico) {
	this.codigoPerfilServico = codigoPerfilServico;
}
public String getDescricaoEquipamentoEspecial() {
	return descricaoEquipamentoEspecial;
}
public void setDescricaoEquipamentoEspecial(String descricaoEquipamentoEspecial) {
	this.descricaoEquipamentoEspecial = descricaoEquipamentoEspecial;
}
public String getDescricaoPerfilServico() {
	return descricaoPerfilServico;
}
public void setDescricaoPerfilServico(String descricaoPerfilServico) {
	this.descricaoPerfilServico = descricaoPerfilServico;
}
public String getIdEquipamentoEspecial() {
	return idEquipamentoEspecial;
}
public void setIdEquipamentoEspecial(String idEquipamentoEspecial) {
	this.idEquipamentoEspecial = idEquipamentoEspecial;
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
public String getIndicadorVeiculoProprio() {
	return indicadorVeiculoProprio;
}
public void setIndicadorVeiculoProprio(String indicadorVeiculoProprio) {
	this.indicadorVeiculoProprio = indicadorVeiculoProprio;
}
public String getQtdComponentesEquipe() {
	return qtdComponentesEquipe;
}
public void setQtdComponentesEquipe(String qtdComponentesEquipe) {
	this.qtdComponentesEquipe = qtdComponentesEquipe;
}


	
	
	
}
