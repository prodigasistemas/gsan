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
package gcom.gui.faturamento.debito;

import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Inserir Débito a Cobrar Popup 
 * 
 * @author Vivianne Sousa
 * @since 28/08/2007
 */
public class ExibirInserirDebitoACobrarPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("inserirDebitoACobrarPopup");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		InserirDebitoACobrarPopupActionForm inserirDebitoACobrarPopupActionForm = (InserirDebitoACobrarPopupActionForm) actionForm;
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		String limparForm = (String) httpServletRequest.getParameter("limparForm");

		String idDebitoTipo = inserirDebitoACobrarPopupActionForm.getIdTipoDebito();
		
		  if (httpServletRequest.getParameter("objetoConsulta") != null
	                && httpServletRequest.getParameter("objetoConsulta").equalsIgnoreCase("1")) {
				
			  //pesquisar debito Tipo
				if (idDebitoTipo != null && !idDebitoTipo.trim().equals("")) {
					DebitoTipo debitoTipo = fachada.pesquisarDebitoTipo(idDebitoTipo);
					if(debitoTipo != null){
						//[FS0008] - Verificar Existência de débito acobrar para o registro de atendimento
						
						inserirDebitoACobrarPopupActionForm.setIdTipoDebito(debitoTipo.getId().toString());
						inserirDebitoACobrarPopupActionForm.setDescricaoTipoDebito(debitoTipo.getDescricao());
						inserirDebitoACobrarPopupActionForm.setValorTotalServico(
								Util.formatarMoedaReal(debitoTipo.getValorSugerido()));
						httpServletRequest.setAttribute("corDebitoTipo","valor");
						httpServletRequest.setAttribute("nomeCampo","valorTotalServico");
					}else{
						inserirDebitoACobrarPopupActionForm.setIdTipoDebito("");
						inserirDebitoACobrarPopupActionForm.setDescricaoTipoDebito("Tipo de Débito Inexistente");
						httpServletRequest.setAttribute("corDebitoTipo",null);
						httpServletRequest.setAttribute("nomeCampo","idTipoDebito");
					}
				}
				
		  }
		

		if (limparForm != null && !limparForm.trim().equalsIgnoreCase("")) {

			inserirDebitoACobrarPopupActionForm.setDescricaoTipoDebito("");
			inserirDebitoACobrarPopupActionForm.setIdTipoDebito("");
			inserirDebitoACobrarPopupActionForm.setValorTotalServico("");

			if (sessao.getAttribute("imovelPesquisado") != null) {
				sessao.removeAttribute("imovelPesquisado");
			}
		}
		
		if (httpServletRequest.getParameter("tipoConsulta") != null && 
			!httpServletRequest.getParameter("tipoConsulta").equals("")) {
				
			if (httpServletRequest.getParameter("tipoConsulta").equals("tipoDebito")) {
				DebitoTipo debitoTipo = fachada.pesquisarDebitoTipo(
						httpServletRequest.getParameter("idCampoEnviarDados"));		
				/*inserirDebitoACobrarPopupActionForm.setIdTipoDebito(
					httpServletRequest.getParameter("idCampoEnviarDados"));
				
				inserirDebitoACobrarPopupActionForm.setDescricaoTipoDebito(
					httpServletRequest.getParameter("descricaoCampoEnviarDados"));*/
				inserirDebitoACobrarPopupActionForm.setIdTipoDebito(debitoTipo.getId().toString());
				inserirDebitoACobrarPopupActionForm.setDescricaoTipoDebito(debitoTipo.getDescricao());
				inserirDebitoACobrarPopupActionForm.setValorTotalServico(
						Util.formatarMoedaReal(debitoTipo.getValorSugerido()));
						
				httpServletRequest.setAttribute("corDebitoTipo","valor");
				httpServletRequest.setAttribute("nomeCampo","valorTotalServico");
				
				sessao.removeAttribute("caminhoRetornoTelaPesquisaTipoDebito");
			}
		}
		
		
		boolean alterarValorSugeridoEmTipoDebito = Fachada
				.getInstancia()
				.verificarPermissaoEspecial(
						PermissaoEspecial.ALTERAR_VALOR_SUGERIDO_EM_TIPO_DEBITO,
						usuario);

		httpServletRequest.setAttribute("alterarValorSugeridoEmTipoDebito",
				alterarValorSugeridoEmTipoDebito);

		if (inserirDebitoACobrarPopupActionForm.getValorTotalServico() == null
				|| inserirDebitoACobrarPopupActionForm.getValorTotalServico().equals("")) {

			httpServletRequest.setAttribute("alterarValorSugeridoEmTipoDebito",
					true);

		}
		
		
		sessao.removeAttribute("informarImovel");
		
		return retorno;
	}
}