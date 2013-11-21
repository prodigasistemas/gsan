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
* Yara Taciane de Souza
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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.FiltroNegativadorRegistroTipo;
import gcom.cobranca.NegativadorRegistroTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action form de manter Negativador Registro Tipo
 * 
 * @author Yara Taciane 
 * @created 08/01/2008
 */
public class AtualizarNegativadorRegistroTipoAction extends GcomAction {
	/**
	 * @author Yara Taciane
	 * @date 08/01/2008
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AtualizarNegativadorRegistroTipoActionForm atualizarNegativadorRegistroTipoActionForm = (AtualizarNegativadorRegistroTipoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_ATUALIZAR_NEGATIVADOR_REGISTRO_TIPO,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_ATUALIZAR_NEGATIVADOR_REGISTRO_TIPO);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança

		NegativadorRegistroTipo negativadorRegistroTipo = (NegativadorRegistroTipo) sessao.getAttribute("negativadorRegistroTipo");

		// Pegando os dados do Formulário	
		String descricaoRegistroTipo = atualizarNegativadorRegistroTipoActionForm.getDescricaoRegistroTipo();
		String codigoRegistro = atualizarNegativadorRegistroTipoActionForm.getCodigoRegistro();
		Long time = Long.parseLong(atualizarNegativadorRegistroTipoActionForm.getTime()); 
		
		// Seta os campos para serem atualizados				
		
		if (descricaoRegistroTipo!= null	&& !descricaoRegistroTipo.equals("")) {

			negativadorRegistroTipo.setDescricaoRegistroTipo(descricaoRegistroTipo);

		} else {
			throw new ActionServletException("atencao.required", null,
					"Descrição do Tipo do Registro");
		}	

		// -------------------------------------------------------------------------------------

		if (codigoRegistro != null 	&& !codigoRegistro.equals("")) {
			
			if(!codigoRegistro.equalsIgnoreCase("H")&& !codigoRegistro.equalsIgnoreCase("D") && !codigoRegistro.equalsIgnoreCase("T") ){
				throw new ActionServletException("atencao.codigo_tipo_registro_invalido");
			}
				
			negativadorRegistroTipo.setCodigoRegistro(codigoRegistro);
			

		} else {
			throw new ActionServletException("atencao.required", null,"Código do Registro");
		}
		
		//------------------------------------------------------------------------------------		
		//Check para atualização realizada por outro usuário 
		FiltroNegativadorRegistroTipo filtroNegativadorRegistroTipo = new FiltroNegativadorRegistroTipo(); 
		filtroNegativadorRegistroTipo.adicionarParametro(new ParametroSimples(FiltroNegativadorRegistroTipo.ID, negativadorRegistroTipo.getId()));
		
		Collection collNegativadorRegistroTipo = Fachada.getInstancia().pesquisar(filtroNegativadorRegistroTipo, NegativadorRegistroTipo.class.getName());
		
		NegativadorRegistroTipo negativRegistroTipo = (NegativadorRegistroTipo)collNegativadorRegistroTipo.iterator().next();

		if (negativRegistroTipo.getUltimaAlteracao().getTime() != time){
			throw new ActionServletException("atencao.registro_remocao_nao_existente");
		}
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		negativadorRegistroTipo.setOperacaoEfetuada(operacaoEfetuada);
		negativadorRegistroTipo.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(negativadorRegistroTipo);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

			
		negativadorRegistroTipo.setUltimaAlteracao(new Date());
		
		// Atualiza o negativadorContrato 
		fachada.atualizar(negativadorRegistroTipo);
		
		montarPaginaSucesso(httpServletRequest, " Tipo do Registro do Negativador de código "
				+ negativadorRegistroTipo.getId() + " atualizado com sucesso.",
				"Realizar outra Manutenção do Tipo do Registro do Negativador",
				"exibirFiltrarNegativadorRegistroTipoAction.do?desfazer=S");
	
		return retorno;
	}
    
	
    
   
}
 