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
package gcom.relatorio.financeiro;

import java.math.BigDecimal;
/**
 * UC0718 Gerar Relatório da Evolução do Contas a Receber Contábil
 * @author Francisco do Nascimento
 *
 */
public class RelatorioEvolucaoContasAReceberContabilHelper {

	private String tipoGrupo;
	
	private String descricaoGrupo;
	
	private int ordemGrupo;
	
	private String descricaoElementoGrupo;

	private int sequenciaTipoLancamento;
	
	private String descricaoLancamento;
	
	private String idCategoriaTipo;	
	
	private BigDecimal valorItem;

	public RelatorioEvolucaoContasAReceberContabilHelper(String tipoGrupo, String descricaoGrupo, int ordemGrupo, String descricaoElementoGrupo, int sequenciaTipoLancamento, String descricaoLancamento, String idCategoriaTipo, BigDecimal valorItem) {
		super();
		// TODO Auto-generated constructor stub
		this.tipoGrupo = tipoGrupo;
		this.descricaoGrupo = descricaoGrupo;
		this.ordemGrupo = ordemGrupo;
		this.descricaoElementoGrupo = descricaoElementoGrupo;
		this.sequenciaTipoLancamento = sequenciaTipoLancamento;
		this.descricaoLancamento = descricaoLancamento;
		this.idCategoriaTipo = idCategoriaTipo;
		this.valorItem = valorItem;
	}

	public String getDescricaoElementoGrupo() {
		return descricaoElementoGrupo;
	}

	public void setDescricaoElementoGrupo(String descricaoElementoGrupo) {
		this.descricaoElementoGrupo = descricaoElementoGrupo;
	}

	public String getDescricaoGrupo() {
		return descricaoGrupo;
	}

	public void setDescricaoGrupo(String descricaoGrupo) {
		this.descricaoGrupo = descricaoGrupo;
	}

	public int getOrdemGrupo() {
		return ordemGrupo;
	}

	public void setOrdemGrupo(int ordemGrupo) {
		this.ordemGrupo = ordemGrupo;
	}

	public String getTipoGrupo() {
		return tipoGrupo;
	}

	public void setTipoGrupo(String tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}

	public BigDecimal getValorItem() {
		return valorItem;
	}

	public void setValorItem(BigDecimal valorItem) {
		this.valorItem = valorItem;
	}

	public String getDescricaoLancamento() {
		return descricaoLancamento;
	}

	public void setDescricaoLancamento(String descricaoLancamento) {
		this.descricaoLancamento = descricaoLancamento;
	}

	public String getIdCategoriaTipo() {
		return idCategoriaTipo;
	}

	public void setIdCategoriaTipo(String idCategoriaTipo) {
		this.idCategoriaTipo = idCategoriaTipo;
	}

	public int getSequenciaTipoLancamento() {
		return sequenciaTipoLancamento;
	}

	public void setSequenciaTipoLancamento(int sequenciaTipoLancamento) {
		this.sequenciaTipoLancamento = sequenciaTipoLancamento;
	}
	


}
