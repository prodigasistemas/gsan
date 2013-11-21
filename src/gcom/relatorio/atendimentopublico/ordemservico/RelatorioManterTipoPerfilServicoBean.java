/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
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