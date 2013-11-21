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
package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

/**
 * Gerar e Emitir Extrato de Débito por Cliente
 * @author Vivianne Sousa
 * @date 27/04/2010
 */
public class RelatorioExtratoDebitoClienteContasDetailBean implements RelatorioBean {

	
	private String faturaAtrasada1 ;
	
	private String vencimentoFatura1;
	
	private String matricula1;
	
	private String valorFatura1;
	
	private String faturaAtrasada2 ;
	
	private String vencimentoFatura2;
	
	private String matricula2;
	
	private String valorFatura2;

	public RelatorioExtratoDebitoClienteContasDetailBean(String faturaAtrasada1, 
			String vencimentoFatura1, String valorFatura1, 
			String faturaAtrasada2, String vencimentoFatura2, String valorFatura2,
			String matricula1, String matricula2) {
		super();
		// TODO Auto-generated constructor stub
		this.faturaAtrasada1 = faturaAtrasada1;
		this.vencimentoFatura1 = vencimentoFatura1;
		this.valorFatura1 = valorFatura1;
		this.faturaAtrasada2 = faturaAtrasada2;
		this.vencimentoFatura2 = vencimentoFatura2;
		this.valorFatura2 = valorFatura2;
		this.matricula1 = matricula1;
		this.matricula2 = matricula2;
	}

	public String getFaturaAtrasada1() {
		return faturaAtrasada1;
	}

	public void setFaturaAtrasada1(String faturaAtrasada1) {
		this.faturaAtrasada1 = faturaAtrasada1;
	}

	public String getFaturaAtrasada2() {
		return faturaAtrasada2;
	}

	public void setFaturaAtrasada2(String faturaAtrasada2) {
		this.faturaAtrasada2 = faturaAtrasada2;
	}

	public String getValorFatura1() {
		return valorFatura1;
	}

	public void setValorFatura1(String valorFatura1) {
		this.valorFatura1 = valorFatura1;
	}

	public String getValorFatura2() {
		return valorFatura2;
	}

	public void setValorFatura2(String valorFatura2) {
		this.valorFatura2 = valorFatura2;
	}

	public String getVencimentoFatura1() {
		return vencimentoFatura1;
	}

	public void setVencimentoFatura1(String vencimentoFatura1) {
		this.vencimentoFatura1 = vencimentoFatura1;
	}

	public String getVencimentoFatura2() {
		return vencimentoFatura2;
	}

	public void setVencimentoFatura2(String vencimentoFatura2) {
		this.vencimentoFatura2 = vencimentoFatura2;
	}

	public String getMatricula1() {
		return matricula1;
	}

	public void setMatricula1(String matricula1) {
		this.matricula1 = matricula1;
	}

	public String getMatricula2() {
		return matricula2;
	}

	public void setMatricula2(String matricula2) {
		this.matricula2 = matricula2;
	}
	
	
	
	
}
