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
package gcom.gui.atendimentopublico.ordemservico;
import gcom.atendimentopublico.ordemservico.EquipamentosEspeciais;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviada.FiltroTabelaAuxiliarAbreviada;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * [UC0387] MANTER TIPO PERFIL SERVICO
 * [SB0001] Atualizar Tipo Perfil de Serviço
 *
 * @author Kássia Albuquerque
 * @date 01/11/2006
 */


public class AtualizarTipoPerfilServicoAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		AtualizarTipoPerfilServicoActionForm atualizarTipoPerfilServicoActionForm = (AtualizarTipoPerfilServicoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		String equipamentoEspecial = atualizarTipoPerfilServicoActionForm.getEquipamentosEspeciais();
	 
		if (equipamentoEspecial != null && !equipamentoEspecial.trim().equals("")) {


			// [UC0387] MANTER TIPO PERFIL SERVICO 
			// [SB0001] Atualizar Tipo Perfil de Serviço
			// [FS0001] Verifica a Existência de Equipamento Especial
			
			FiltroTabelaAuxiliarAbreviada filtroEquipamentoEspecial = new FiltroTabelaAuxiliarAbreviada();

			filtroEquipamentoEspecial.adicionarParametro(new ParametroSimples(FiltroTabelaAuxiliarAbreviada.ID, equipamentoEspecial));

			Collection colecaoEquipamentosEspeciais = fachada.pesquisar(filtroEquipamentoEspecial, EquipamentosEspeciais.class.getName());
	
			if (colecaoEquipamentosEspeciais == null || colecaoEquipamentosEspeciais.isEmpty()) {
				
				atualizarTipoPerfilServicoActionForm.setEquipamentosEspeciais("");
				throw new ActionServletException("atencao.equipamento_especial_inexistente");
			}
		}
		
		ServicoPerfilTipo servicoPerfilTipo = (ServicoPerfilTipo) sessao.getAttribute("servicoPerfilTipo");
		
		//Atualiza a entidade com os valores do formulário
		atualizarTipoPerfilServicoActionForm.setFormValues(servicoPerfilTipo);
		
		//atualiza na base de dados Tipo Perfil Serviço
		 fachada.atualizarServicoTipoPerfil(servicoPerfilTipo,usuarioLogado);
		
		//Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Perfil de Serviço "+ atualizarTipoPerfilServicoActionForm.getDescricaoPerfil() +" atualizado com sucesso.", "Realizar outra Manutenção de Perfil de Serviço",
				"exibirFiltrarTipoPerfilServicoAction.do?menu=sim");
		
		return retorno;
	}
	
}	      
    



