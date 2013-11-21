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
package gcom.relatorio.cadastro;

import gcom.relatorio.RelatorioBean;

/**
 * [UC1076] - Gerar Relatorio Atualizacao Cadastral Via Internet.
 * @author Daniel Alves
 * @date 29/09/2010 
 */
public class RelatorioResumoAtualizacaoCadastralViaInternetBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private Integer quantidadeNome;
	private Integer quantidadeCPF;
	private Integer quantidadeCNPJ;
	private Integer quantidadeEmail;
	private Integer quantidadeClientesAlterados;
	
	public RelatorioResumoAtualizacaoCadastralViaInternetBean(){}
	
	public RelatorioResumoAtualizacaoCadastralViaInternetBean(
			Integer quantidadeCNPJ, Integer quantidadeCPF,
			Integer quantidadeEmail, Integer quantidadeNome) {
		
		this.quantidadeCNPJ = quantidadeCNPJ;
		this.quantidadeCPF = quantidadeCPF;
		this.quantidadeEmail = quantidadeEmail;
		this.quantidadeNome = quantidadeNome;
	}

	public RelatorioResumoAtualizacaoCadastralViaInternetBean(
			Integer quantidadeNome, Integer quantidadeCPF,
			Integer quantidadeCNPJ, Integer quantidadeEmail,
			Integer quantidadeClientesAlterados) {
		this.quantidadeNome = quantidadeNome;
		this.quantidadeCPF = quantidadeCPF;
		this.quantidadeCNPJ = quantidadeCNPJ;
		this.quantidadeEmail = quantidadeEmail;
		this.quantidadeClientesAlterados = quantidadeClientesAlterados;
	}

	public Integer getQuantidadeNome() {
		return quantidadeNome;
	}

	public void setQuantidadeNome(Integer quantidadeNome) {
		this.quantidadeNome = quantidadeNome;
	}

	public Integer getQuantidadeCPF() {
		return quantidadeCPF;
	}

	public void setQuantidadeCPF(Integer quantidadeCPF) {
		this.quantidadeCPF = quantidadeCPF;
	}

	public Integer getQuantidadeCNPJ() {
		return quantidadeCNPJ;
	}

	public void setQuantidadeCNPJ(Integer quantidadeCNPJ) {
		this.quantidadeCNPJ = quantidadeCNPJ;
	}

	public Integer getQuantidadeEmail() {
		return quantidadeEmail;
	}

	public void setQuantidadeEmail(Integer quantidadeEmail) {
		this.quantidadeEmail = quantidadeEmail;
	}

	public Integer getQuantidadeClientesAlterados() {
		return quantidadeClientesAlterados;
	}

	public void setQuantidadeClientesAlterados(Integer quantidadeClientesAlterados) {
		this.quantidadeClientesAlterados = quantidadeClientesAlterados;
	}
	


	
}
