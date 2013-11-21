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
package gcom.relatorio.transacao;
 
import gcom.relatorio.RelatorioBean;

public class RelatorioOperacaoBean implements RelatorioBean {

	private String identificadorLinha1;
	private String identificadorLinha2;
	private String nomeTabela;
	private String nomeUsuario;
	private String acaoUsuario;
	private String tipoUsuario;
	private String nomeColuna;
	private String conteudoAnterior;
	private String conteudoAtual;
	private String dataHora;
	
	
	public RelatorioOperacaoBean ( String identificadorLinha1, String identificadorLinha2,
		String nomeTabela , String nomeUsuario , String acaoUsuario , 
		String tipoUsuario , String nomeColuna , String conteudoAnterior , 
		String conteudoAtual , String dataHora ) {
		
		super();
		// TODO Auto-generated constructor stub
		this.identificadorLinha1 = identificadorLinha1;
		this.identificadorLinha2 = identificadorLinha2;
		this.nomeTabela = nomeTabela;
		this.nomeUsuario = nomeUsuario;
		this.acaoUsuario = acaoUsuario;
		this.tipoUsuario = tipoUsuario;
		this.nomeColuna = nomeColuna;
		this.conteudoAnterior = conteudoAnterior;
		this.conteudoAtual = conteudoAtual;
		this.dataHora = dataHora;
	}
	
	public String getAcaoUsuario() {
		return acaoUsuario;
	}
	public void setAcaoUsuario(String acaoUsuario) {
		this.acaoUsuario = acaoUsuario;
	}
	public String getConteudoAnterior() {
		return conteudoAnterior;
	}
	public void setConteudoAnterior(String conteudoAnterior) {
		this.conteudoAnterior = conteudoAnterior;
	}
	public String getConteudoAtual() {
		return conteudoAtual;
	}
	public void setConteudoAtual(String conteudoAtual) {
		this.conteudoAtual = conteudoAtual;
	}
	public String getDataHora() {
		return dataHora;
	}
	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
	public String getIdentificadorLinha1() {
		return identificadorLinha1;
	}
	public void setIdentificadorLinha1(String identificadorLinha1) {
		this.identificadorLinha1 = identificadorLinha1;
	}
	public String getIdentificadorLinha2() {
		return identificadorLinha2;
	}
	public void setIdentificadorLinha2(String identificadorLinha2) {
		this.identificadorLinha2 = identificadorLinha2;
	}
	public String getNomeColuna() {
		return nomeColuna;
	}
	public void setNomeColuna(String nomeColuna) {
		this.nomeColuna = nomeColuna;
	}
	public String getNomeTabela() {
		return nomeTabela;
	}
	public void setNomeTabela(String nomeTabela) {
		this.nomeTabela = nomeTabela;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	
}
