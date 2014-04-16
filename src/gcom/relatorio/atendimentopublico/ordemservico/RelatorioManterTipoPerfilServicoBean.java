package gcom.relatorio.atendimentopublico.ordemservico;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Arthur Carvalho
 * @version 1.0
 */

public class RelatorioManterTipoPerfilServicoBean implements RelatorioBean {
	
	private String codigoPerfilServico;

	private String descricaoPerfilServico;
	
	private String abrevPerfilServico;
	
	private String qtdComponentesEquipe;
	
	private String idEquipamentoEspecial;
//	
	private String equipamentosEspeciais;
	
	private String indicadorVeiculoProprio;
	
	private String indicadorUso;

	/**
	 * Construtor da classe RelatorioManterBaciaBean
	 * 
	 * @param id
	 *            Descrição do parâmetro
	 * @param descricao
	 *            Descrição do parâmetro
	 * @param sistemaEsgoto
	 *            Descrição do parâmetro
	 */

	public RelatorioManterTipoPerfilServicoBean(String codigoPerfilServico,
			String descricaoPerfilServico, String abrevPerfilServico, String qtdComponentesEquipe,
			String idEquipamentoEspecial, String equipamentosEspeciais,//String descricaoEquipamentoEspecial, 
			String indicadorVeiculoProprio,
			String indicadorUso) {
		this.codigoPerfilServico = codigoPerfilServico;
		this.descricaoPerfilServico = descricaoPerfilServico;
		this.abrevPerfilServico = abrevPerfilServico;
		this.qtdComponentesEquipe = qtdComponentesEquipe;
		this.idEquipamentoEspecial = idEquipamentoEspecial;
		this.equipamentosEspeciais = equipamentosEspeciais;
		//this.descricaoEquipamentoEspecial = descricaoEquipamentoEspecial;
		this.indicadorVeiculoProprio = indicadorVeiculoProprio;
		this.indicadorUso = indicadorUso;
	}

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

//	public String getDescricaoEquipamentoEspecial() {
//		return descricaoEquipamentoEspecial;
//	}
//
//	public void setDescricaoEquipamentoEspecial(String descricaoEquipamentoEspecial) {
//		this.descricaoEquipamentoEspecial = descricaoEquipamentoEspecial;
//	}

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

	public String getEquipamentosEspeciais() {
		return equipamentosEspeciais;
	}

	public void setEquipamentosEspeciais(String equipamentosEspeciais) {
		this.equipamentosEspeciais = equipamentosEspeciais;
	}

	

	
}
