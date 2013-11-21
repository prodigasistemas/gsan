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
package gcom.gui.seguranca.acesso.usuario;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Thiago Tenório
 * @date 06/05/2006
 */
public class InserirAbrangenciaUsuarioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		InserirAbrangenciaUsuarioActionForm inserirAbrangenciaUsuarioActionForm = (InserirAbrangenciaUsuarioActionForm) actionForm;

		UsuarioAbrangencia usuarioAbrangencia = new UsuarioAbrangencia();

		usuarioAbrangencia.setDescricao(inserirAbrangenciaUsuarioActionForm
				.getDescricaoUsuarioSituacao());
		usuarioAbrangencia
				.setDescricaoAbreviada(inserirAbrangenciaUsuarioActionForm
						.getDescricaoAbreviada());
		// Se Abrangeência Superior for nulo vai atribuir o valor igual a -1

		if (inserirAbrangenciaUsuarioActionForm.getAbrangenciaSuperior()
				.equalsIgnoreCase("-1")) {
			inserirAbrangenciaUsuarioActionForm.setAbrangenciaSuperior(null);
		}
		// Se Abrangência Superior for nulo Abrangência Superior vai ser ID do próprio Usuario 

		if (inserirAbrangenciaUsuarioActionForm.getAbrangenciaSuperior() != null
				&& !inserirAbrangenciaUsuarioActionForm
						.getAbrangenciaSuperior().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			UsuarioAbrangencia usuarioAbrangenciaSuperior = new UsuarioAbrangencia();
			usuarioAbrangenciaSuperior.setId(Integer
					.parseInt(inserirAbrangenciaUsuarioActionForm
							.getAbrangenciaSuperior()));
			usuarioAbrangencia
					.setUsuarioAbrangenciaSuperior(usuarioAbrangenciaSuperior);

		}

		usuarioAbrangencia.setIndicadorUso(new Short((short) 1));

		Integer id = (Integer) fachada
				.inserirAbrangenciaUsuario(usuarioAbrangencia);

		montarPaginaSucesso(httpServletRequest, "Abrangência Usuario" + id
				+ " inserida com sucesso.",
				"Inserir outra Abrangência do Usuário",
				"exibirInserirAbrangenciaUsuarioAction.do?menu=sim",
				"exibirAtualizarAbrangenciaUsuarioAction.do?idRegistroAtualizacao="
				+ id, "Atualizar Abrangencia do Usuário inserida");



		return retorno;
	}
}
