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
package gcom.gui.faturamento.conta;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class AtualizarMensagemContaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String referenciaFaturamento;
	private String grupoFaturamento;
	private String mensagemConta01;
	private String mensagemConta02;
	private String mensagemConta03;
	private String gerenciaRegional;
	private String localidade;
	private String localidadeDescricao;
	private String setorComercial;
	private String setorComercialDescricao;
	private String atualizar;
	private String quadra;
	
	public String getAtualizar() {
		return atualizar;
	}
	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
	}
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public String getGrupoFaturamento() {
		return grupoFaturamento;
	}
	public void setGrupoFaturamento(String grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getLocalidadeDescricao() {
		return localidadeDescricao;
	}
	public void setLocalidadeDescricao(String localidadeDescricao) {
		this.localidadeDescricao = localidadeDescricao;
	}
	public String getReferenciaFaturamento() {
		return referenciaFaturamento;
	}
	public void setReferenciaFaturamento(String referenciaFaturamento) {
		this.referenciaFaturamento = referenciaFaturamento;
	}
	public String getSetorComercial() {
		return setorComercial;
	}
	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}
	public String getSetorComercialDescricao() {
		return setorComercialDescricao;
	}
	public void setSetorComercialDescricao(String setorComercialDescricao) {
		this.setorComercialDescricao = setorComercialDescricao;
	}
	public String getMensagemConta01() {
		return mensagemConta01;
	}
	public void setMensagemConta01(String mensagemConta01) {
		this.mensagemConta01 = mensagemConta01;
	}
	public String getMensagemConta02() {
		return mensagemConta02;
	}
	public void setMensagemConta02(String mensagemConta02) {
		this.mensagemConta02 = mensagemConta02;
	}
	public String getMensagemConta03() {
		return mensagemConta03;
	}
	public void setMensagemConta03(String mensagemConta03) {
		this.mensagemConta03 = mensagemConta03;
	}
	
	public String getQuadra() {
		return quadra;
	}
	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}
	@Override
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		// TODO Auto-generated method stub
		super.reset(arg0, arg1);
		referenciaFaturamento="";
		grupoFaturamento="";
		mensagemConta01="";
		mensagemConta02="";
		mensagemConta03="";
		gerenciaRegional="";
		localidade="";
		localidadeDescricao="";
		setorComercial="";
		setorComercialDescricao="";
	}
	
	
	

}
