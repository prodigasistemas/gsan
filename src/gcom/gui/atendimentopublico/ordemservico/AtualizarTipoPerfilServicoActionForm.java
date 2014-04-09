package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.EquipamentosEspeciais;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;

import org.apache.struts.validator.ValidatorForm;


/**
 * [SB0001]Atualizar Tipo Perfil de Serviço
 *
 * @author Kássia Albuquerque
 * @date 30/10/2006
 */

public class AtualizarTipoPerfilServicoActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
private String idTipoPerfilServico;	
private String codigoPerfilServico;
private String descricaoPerfil;
private String abreviaturaPerfil;
private String quantidadeComponente;
private String equipamentosEspeciais;
private String descricaoEquipamentoEspecial;
private String veiculoProprio;
private String indicadorUso;

public String getIdTipoPerfilServico() {
	return idTipoPerfilServico;
}
public void setIdTipoPerfilServico(String idTipoPerfilServico) {
	this.idTipoPerfilServico = idTipoPerfilServico;
}
public String getAbreviaturaPerfil() {
	return abreviaturaPerfil;
}
public void setAbreviaturaPerfil(String abreviaturaPerfil) {
	this.abreviaturaPerfil = abreviaturaPerfil;
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
public String getDescricaoPerfil() {
	return descricaoPerfil;
}
public void setDescricaoPerfil(String descricaoPerfil) {
	this.descricaoPerfil = descricaoPerfil;
}
public String getEquipamentosEspeciais() {
	return equipamentosEspeciais;
}
public void setEquipamentosEspeciais(String equipamentosEspeciais) {
	this.equipamentosEspeciais = equipamentosEspeciais;
}
public String getIndicadorUso() {
	return indicadorUso;
}
public void setIndicadorUso(String indicadorUso) {
	this.indicadorUso = indicadorUso;
}
public String getQuantidadeComponente() {
	return quantidadeComponente;
}
public void setQuantidadeComponente(String quantidadeComponente) {
	this.quantidadeComponente = quantidadeComponente;
}
public String getVeiculoProprio() {
	return veiculoProprio;
}
public void setVeiculoProprio(String veiculoProprio) {
	this.veiculoProprio = veiculoProprio;
}
public ServicoPerfilTipo setFormValues(ServicoPerfilTipo servicoPerfilTipo) {
	
	/*
	 * Campos obrigatórios
	 */
	
	//Descrição
	servicoPerfilTipo.setId(new Integer(getIdTipoPerfilServico()));
	servicoPerfilTipo.setDescricao(getDescricaoPerfil());
	servicoPerfilTipo.setDescricaoAbreviada(getAbreviaturaPerfil());		
	servicoPerfilTipo.setComponentesEquipe(Short.parseShort(getQuantidadeComponente()));
	servicoPerfilTipo.setIndicadorVeiculoProprio(Short.parseShort(getVeiculoProprio()));
	//servicoPerfilTipo.setUltimaAlteracao(new Date());
	//Equipamento Especial
	
	if(getEquipamentosEspeciais() != null && !getEquipamentosEspeciais().equals("")){
		  //Equipamento Especial
		  EquipamentosEspeciais equipamentosEspeciais = new EquipamentosEspeciais();
		  equipamentosEspeciais.setId(Integer.parseInt(getEquipamentosEspeciais()));
		  servicoPerfilTipo.setEquipamentosEspeciais(equipamentosEspeciais);
		}
	
	/*
	 * Campos opcionais 
	 */

	//data da retirada
	servicoPerfilTipo.setIndicadorUso(new Short(getIndicadorUso()));

	
	return servicoPerfilTipo;
}

}
