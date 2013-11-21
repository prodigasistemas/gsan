
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
package gcom.gui.seguranca;





import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarUsuarioTipoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarUsuarioTipoActionForm atualizarUsuarioTipoActionForm = (AtualizarUsuarioTipoActionForm) actionForm;

		UsuarioTipo usuarioTipo= (UsuarioTipo) sessao.getAttribute("atualizarUsuarioTipo");
		
		Collection colecaoPesquisa = null;
		
		if(atualizarUsuarioTipoActionForm.getCodigo()!= null 
				&& !atualizarUsuarioTipoActionForm.getCodigo().equals("")){
			usuarioTipo.setId(new Integer(atualizarUsuarioTipoActionForm.getCodigo()));
		}else{
			usuarioTipo.setId(null);
		}
		usuarioTipo.setDescricao(atualizarUsuarioTipoActionForm.getDescricao());
		usuarioTipo.setIndicadorUso(new Short (atualizarUsuarioTipoActionForm.getIndicadorUso()));
		usuarioTipo.setIndicadorFuncionario(new Short (atualizarUsuarioTipoActionForm.getIndicadorFuncionario()));
		
		Short indicadorFuncionario = atualizarUsuarioTipoActionForm.getIndicadorFuncionario();
		String codigo = atualizarUsuarioTipoActionForm.getCodigo();		
        String descricao = atualizarUsuarioTipoActionForm.getDescricao();
        Short indicadorUso = atualizarUsuarioTipoActionForm.getIndicadorUso();
        
		
        usuarioTipo.setDescricao(descricao);
       
        if(codigo != null && !codigo.equals("")){
        	usuarioTipo.setId(new Integer(codigo));
        }else{
        	usuarioTipo.setId(null);
        }
        
        usuarioTipo.setIndicadorFuncionario(new Short(indicadorFuncionario));
        usuarioTipo.setIndicadorUso(new Short(indicadorUso));

        usuarioTipo.setUltimaAlteracao(new Date());
        

        FiltroUsuarioTipo filtroTipoUsuario= new FiltroUsuarioTipo();
        	filtroTipoUsuario.adicionarParametro(
				new ParametroSimples(FiltroUsuarioTipo.DESCRICAO, descricao));
        	filtroTipoUsuario.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroUsuarioTipo.ID, usuarioTipo.getId()));
		
		colecaoPesquisa = (Collection)
		this.getFachada().pesquisar(filtroTipoUsuario, UsuarioTipo.class.getName());
				
		if( colecaoPesquisa !=null && !colecaoPesquisa.isEmpty()){
			throw new ActionServletException("atencao.descricao_existente", null, descricao);
		}
		
		
		FiltroUsuarioTipo filtroUsuarioTipo = new FiltroUsuarioTipo();
			filtroUsuarioTipo.adicionarParametro(
					new ParametroSimples(FiltroUsuarioTipo.ID, codigo));
			filtroUsuarioTipo.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroUsuarioTipo.ID, usuarioTipo.getId()));
			
		colecaoPesquisa = (Collection) 
			this.getFachada().pesquisar(filtroUsuarioTipo, UsuarioTipo.class.getName());
		
	
		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			throw new ActionServletException("atencao.codigo_existente", null, codigo+"");
		}
		
		fachada.atualizar(usuarioTipo);

		
		montarPaginaSucesso(httpServletRequest, "Tipo de Usuário"
				+ descricao + " atualizado com sucesso.",
				"Realizar outra Manutenção do Tipo de Usuário",
				"exibirFiltrarUsuarioTipoAction.do?menu=sim");        
        
		return retorno;
	}
}