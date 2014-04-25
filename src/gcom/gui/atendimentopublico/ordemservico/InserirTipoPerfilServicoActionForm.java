package gcom.gui.atendimentopublico.ordemservico;


import gcom.atendimentopublico.ordemservico.EquipamentosEspeciais;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.util.ConstantesSistema;
import java.util.Date;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * 
 * @author Ana Maria
 * @date 31/07/2006
 * 
 */
public class InserirTipoPerfilServicoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String descricaoPerfil;
	
	private String abreviaturaPerfil;

	private String quantidadeComponente;
	
	private String equipamentosEspeciais;
	
	private String descricaoEquipamentoEspecial;

	private String veiculoProprio;
	
	/**
	 * @return Retorna o campo descricaoPerfil.
	 */
	public String getDescricaoPerfil() {
		return descricaoPerfil;
	}

	/**
	 * @param descricaoPerfil a ser setado.
	 */
	public void setDescricaoPerfil(String descricaoPerfil) {
		this.descricaoPerfil = descricaoPerfil;
	}

	/**
	 * @return Retorna o campo abreviaturaPerfil.
	 */
	public String getAbreviaturaPerfil() {
		return abreviaturaPerfil;
	}

	/**
	 * @param abreviaturaPerfil a ser setado.
	 */
	public void setAbreviaturaPerfil(String abreviaturaPerfil) {
		this.abreviaturaPerfil = abreviaturaPerfil;
	}
	
	/**
	 * @return Retorna o campo quantidadeComponente.
	 */
	public String getQuantidadeComponente() {
		return quantidadeComponente;
	}

	/**
	 * @param quantidadeComponente a ser setado.
	 */
	public void setQuantidadeComponente(String quantidadeComponente) {
		this.quantidadeComponente = quantidadeComponente;
	}
	
	/**
	 * @return Retorna o campo equipamentoEspecial.
	 */
	public String getEquipamentosEspeciais() {
		return equipamentosEspeciais;
	}

	/**
	 * @param equipamentoEspecial a ser setado.
	 */
	public void setEquipamentosEspeciais(String equipamentosEspeciais) {
		this.equipamentosEspeciais = equipamentosEspeciais;
	}
	
	/**
	 * @return Retorna o campo veiculoProprio.
	 */
	public String getVeiculoProprio() {
		return veiculoProprio;
	}

	/**
	 * @param veiculoProprio a ser setado.
	 */
	public void setVeiculoProprio(String veiculoProprio) {
		this.veiculoProprio = veiculoProprio;
	}
	
	public ServicoPerfilTipo setFormValues(ServicoPerfilTipo servicoPerfilTipo) {
		
		/*
		 * Campos obrigatórios
		 */
		
		//Descrição
		servicoPerfilTipo.setDescricao(getDescricaoPerfil());
		servicoPerfilTipo.setDescricaoAbreviada(getAbreviaturaPerfil());		
		servicoPerfilTipo.setComponentesEquipe(Short.parseShort(getQuantidadeComponente()));
		servicoPerfilTipo.setIndicadorVeiculoProprio(Short.parseShort(getVeiculoProprio()));
		servicoPerfilTipo.setUltimaAlteracao(new Date());
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
		servicoPerfilTipo.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

		
		return servicoPerfilTipo;
	}

	public String getDescricaoEquipamentoEspecial() {
		return descricaoEquipamentoEspecial;
	}

	public void setDescricaoEquipamentoEspecial(String descricaoEquipamentoEspecial) {
		this.descricaoEquipamentoEspecial = descricaoEquipamentoEspecial;
	}
}
