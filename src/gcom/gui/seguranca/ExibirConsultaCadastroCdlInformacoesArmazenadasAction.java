/*
* Copyright (C) 2007-2007 the GSAN ? Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place ? Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN ? Sistema Integrado de Gestão de Serviços de Saneamento
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
package gcom.gui.seguranca;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.ConsultaCadastroCdlInformacoesArmazenadasActionForm;
import gcom.seguranca.ConsultaCdl;
import gcom.seguranca.FiltroConsultaCadastroCdl;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 *
 * @author Rodrigo Cabral
 * @date 08/11/2010
 */

public class ExibirConsultaCadastroCdlInformacoesArmazenadasAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("consultaCadastroCdlInformacoesArmazenadas");				
		
		ConsultaCadastroCdlInformacoesArmazenadasActionForm form = (ConsultaCadastroCdlInformacoesArmazenadasActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		String codigo = null;
		
		form.setLoginUsuario("");
		form.setCodigoCliente("");
		form.setCpfCliente("");
		form.setCnpjCliente("");
		form.setNomeCliente("");
		form.setLogradouroCliente("");
		form.setNumeroImovelCliente("");
		form.setBairroCliente("");
		form.setComplementoEnderecoCliente("");
		form.setCidadeCliente("");
		form.setCepCliente("");
		form.setCodigoDdd("");
		form.setTelefoneCliente("");
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
			codigo = httpServletRequest.getParameter("idRegistroAtualizacao");
		} else {
			codigo = ""+((ConsultaCdl)sessao.getAttribute("consultaCadastroCdlInformacoesArmazenadas")).getId();
		}
		
		if (httpServletRequest.getParameter("menu") != null) {
			sessao.setAttribute("menu", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("menu");
		}
		
		if (codigo == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				codigo = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				codigo = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}
		
		ConsultaCdl consultaCdl = null;
		
		if (codigo != null && !codigo.trim().equals("") && Integer.parseInt(codigo) > 0) {
		
			FiltroConsultaCadastroCdl filtroConsultaCadastroCdl = new FiltroConsultaCadastroCdl();
			
			filtroConsultaCadastroCdl.adicionarParametro(new ParametroSimples(FiltroConsultaCadastroCdl.ID, codigo));
			
			Collection colecaoConsultaCdl = fachada.pesquisar
				(filtroConsultaCadastroCdl,ConsultaCdl.class.getName());
			
			if(colecaoConsultaCdl != null && !colecaoConsultaCdl.isEmpty()){
				
				consultaCdl = (ConsultaCdl)Util.retonarObjetoDeColecao(colecaoConsultaCdl);
				
				if (consultaCdl.getLoginUsuario() != null){
					form.setLoginUsuario(consultaCdl.getLoginUsuario());
				}
				
				if (consultaCdl.getCodigoCliente() != null){
					form.setCodigoCliente(consultaCdl.getCodigoCliente().getId().toString());
				}
				
				if (consultaCdl.getCpfCliente() != null){
					form.setCpfCliente(Util.formatarCpf(consultaCdl.getCpfCliente()));
					
					if (consultaCdl.getCodigoDddResidencial() != null){
						form.setCodigoDdd(consultaCdl.getCodigoDddResidencial());
					}
				
					if (consultaCdl.getTelefoneResidencialCliente() != null){
						form.setTelefoneCliente(consultaCdl.getTelefoneResidencialCliente());
					}
				}
				
				if (consultaCdl.getCnpjCliente() != null){
					form.setCnpjCliente(Util.formatarCnpj(consultaCdl.getCnpjCliente()));
					
					if (consultaCdl.getCodigoDddComercial() != null){
						form.setCodigoDdd(consultaCdl.getCodigoDddComercial());
					}
				
					if (consultaCdl.getTelefoneComercialCliente() != null){
						form.setTelefoneCliente(consultaCdl.getTelefoneComercialCliente());
					}
				}
				
				if (consultaCdl.getNomeCliente() != null){
					form.setNomeCliente(consultaCdl.getNomeCliente());
				}
				
				if (consultaCdl.getLogradouroCliente() != null){
					form.setLogradouroCliente(consultaCdl.getLogradouroCliente());
				}
				
				if (consultaCdl.getNumeroImovelCliente() != null){
					form.setNumeroImovelCliente(consultaCdl.getNumeroImovelCliente().toString());
				}
				
				if (consultaCdl.getBairroCliente() != null){
					form.setBairroCliente(consultaCdl.getBairroCliente());
				}
				
				if (consultaCdl.getComplementoEnderecoCliente() != null){
					form.setComplementoEnderecoCliente(consultaCdl.getComplementoEnderecoCliente());
				}
				
				if (consultaCdl.getCidadeCliente() != null){
					form.setCidadeCliente(consultaCdl.getCidadeCliente());
				}
				
				if (consultaCdl.getCepCliente() != null){
					form.setCepCliente(Util.formatarCEP( consultaCdl.getCepCliente().toString()));
				}
				
			}
				
			
			
			sessao.setAttribute("consultaCadastroCdlInformacoesArmazenadas", consultaCdl);

			if (sessao.getAttribute("colecaoConsultaCdl") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarConsultaCadastroCdlAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarConsultaCadastroCdlAction.do");
			}
			
		}

		return retorno;
	
	}

}