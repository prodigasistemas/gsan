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
package gcom.relatorio.micromedicao.rota;

import gcom.relatorio.RelatorioBean;

/**
 * Title: GCOM
 * 
 * @author Rafael Corrêa
 * @date 17/07/2006
 */

public class RelatorioManterRotaBean implements RelatorioBean {

	private String codigo;

	private String localidade;

	private String setorComercial;

	private String grupoFaturamento;

	private String grupoCobranca;

	private String tipoLeitura;

	private String empresaLeitura;

	private String indicadorUso;
	
	private String empresaCobranca;
	
	private String empresaEntregaContas;



	public RelatorioManterRotaBean(String codigo, String localidade,
			String setorComercial, String grupoFaturamento,
			String grupoCobranca, String tipoLeitura, String empresaLeitura,
			String empresaCobranca, String empresaEntregaContas,
			String indicadorUso) {
		this.codigo = codigo;
		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.grupoFaturamento = grupoFaturamento;
		this.grupoCobranca = grupoCobranca;
		this.tipoLeitura = tipoLeitura;
		this.empresaLeitura = empresaLeitura;
		this.empresaCobranca = empresaCobranca;
		this.empresaEntregaContas = empresaEntregaContas;
		this.indicadorUso = indicadorUso;
	}

	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEmpresaLeitura() {
		return empresaLeitura;
	}

	public void setEmpresaLeitura(String empresaLeitura) {
		this.empresaLeitura = empresaLeitura;
	}

	public String getGrupoCobranca() {
		return grupoCobranca;
	}

	public void setGrupoCobranca(String grupoCobranca) {
		this.grupoCobranca = grupoCobranca;
	}

	public String getGrupoFaturamento() {
		return grupoFaturamento;
	}

	public void setGrupoFaturamento(String grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	public String getTipoLeitura() {
		return tipoLeitura;
	}

	public void setTipoLeitura(String tipoLeitura) {
		this.tipoLeitura = tipoLeitura;
	}

	public String getEmpresaCobranca() {
		return empresaCobranca;
	}

	public void setEmpresaCobranca(String empresaCobranca) {
		this.empresaCobranca = empresaCobranca;
	}

	public String getEmpresaEntregaContas() {
		return empresaEntregaContas;
	}

	public void setEmpresaEntregaContas(String empresaEntregaContas) {
		this.empresaEntregaContas = empresaEntregaContas;
	}

}
