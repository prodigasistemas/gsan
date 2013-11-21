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
package gcom.atendimentopublico.ordemservico.bean;

import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.Material;
import gcom.atendimentopublico.ordemservico.MaterialUnidade;

import java.math.BigDecimal;
import java.util.Date;

/**
 * [UC0462] Obter Dados das Atividades da OS
 * 
 * Esta classe tem por finalidade obter dados das atividades da ordem de serviço. 
 * 
 * @author Leonardo Regis
 * @date 09/09/2006
 */
public class ObterDadosAtividadeOSHelper {

	public final static Integer INDICADOR_MATERIAL = 1;
	public final static Integer INDICADOR_PERIODO = 2;
	
	private Atividade atividade;
	
	private boolean isMaterial = false;
	private Material materialUtilizado = null;
	private MaterialUnidade materialUnidade = null;
	private BigDecimal qtdeMaterial = null;
	
	private boolean isPeriodo = false;
	private Date dataInicio = null;
	private Date dataFim = null;
	private Equipe equipe = null;
	
	public ObterDadosAtividadeOSHelper(){}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public boolean isMaterial() {
		return isMaterial;
	}

	public void setMaterial(boolean isMaterial) {
		this.isMaterial = isMaterial;
	}

	public Material getMaterialUtilizado() {
		return materialUtilizado;
	}

	public void setMaterialUtilizado(Material material) {
		this.materialUtilizado = material;
	}

	public MaterialUnidade getMaterialUnidade() {
		return materialUnidade;
	}

	public void setMaterialUnidade(MaterialUnidade materialUnidade) {
		this.materialUnidade = materialUnidade;
	}

	public BigDecimal getQtdeMaterial() {
		return qtdeMaterial;
	}

	public void setQtdeMaterial(BigDecimal qtdeMaterial) {
		this.qtdeMaterial = qtdeMaterial;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public boolean isPeriodo() {
		return isPeriodo;
	}

	public void setPeriodo(boolean isPeriodo) {
		this.isPeriodo = isPeriodo;
	}
}