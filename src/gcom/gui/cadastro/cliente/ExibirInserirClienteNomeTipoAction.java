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
package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuarioPemissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action que inicializa a primeira página do processo de inserir cliente
 * 
 * @author Rodrigo
 */
public class ExibirInserirClienteNomeTipoAction extends GcomAction {

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("inserirClienteNomeTipo");
        
        HttpSession session = httpServletRequest.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		Fachada fachada = Fachada.getInstancia();
		DynaValidatorForm clienteActionForm = (DynaValidatorForm) actionForm;
		
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		String descricao = "";
		
		String descricaoAbreviada = "";
		
		if(sistemaParametro.getIndicadorUsoNMCliReceitaFantasia().intValue() == ConstantesSistema.INDICADOR_USO_ATIVO.intValue()){
			
			session.setAttribute("indicadorNomeFantasia", true);
			
			descricao = "Nome na Receita Federal:" ;
			
			descricaoAbreviada = "Nome de Fantasia:" ;
			
			
		}else{
			
			descricao = "Nome: " ;
			
			descricaoAbreviada = "Nome Abreviado: " ;
			
			session.removeAttribute("indicadorNomeFantasia");
			
		}
		
		session.setAttribute("descricao", descricao);
		session.setAttribute("descricaoAbreviada", descricaoAbreviada);
		
		

        //Pesquisa os Tipos de Clientes para a página
		String indicadorPessoaFisicaJuridica = (String) clienteActionForm.get("indicadorPessoaFisicaJuridica");
		
        FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();
        filtroClienteTipo.adicionarParametro(new ParametroSimples(
                FiltroClienteTipo.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroClienteTipo.adicionarParametro(new ParametroSimples(FiltroClienteTipo.INDICADOR_PESSOA_FISICA_JURIDICA, indicadorPessoaFisicaJuridica));
        filtroClienteTipo.setCampoOrderBy(FiltroClienteTipo.DESCRICAO);

        httpServletRequest.setAttribute("colecaoTipoPessoa", Fachada
                .getInstancia().pesquisar(filtroClienteTipo,
                        ClienteTipo.class.getName()));
        
        /** Verificar as permissão especial para Visualizar Dia de Vencimento em Cliente  					 **/
		boolean temPermissaoVisualizarDiaVencimentoContaCliente = fachada.verificarPermissaoVisualizarDiaVencimentoContaCliente(usuario);
		httpServletRequest.setAttribute("temPermissaoVisualizarDiaVencimentoContaCliente",temPermissaoVisualizarDiaVencimentoContaCliente);
		/******************************************************************************************/
		
		//Verificar se o usuário possui permissão especial para negativar cliente
		FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
		filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
		filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.INSERIR_MANTER_CLIENTE_SEM_NEGATIVACAO));
		
		Collection colecaoUsuarioPermisao = fachada.pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());
		
		session.removeAttribute("permissaoEspecial");
		
		if (colecaoUsuarioPermisao != null && !colecaoUsuarioPermisao.isEmpty()) {
			session.setAttribute("permissaoEspecial", "true");
		}
		
		
		//**********************************************************************
		// CRC2103
		// Autor: Ivan Sergio
		// Data: 02/07/2009
		// Verifica se a tela deve ser exibida como um PopUp
		//**********************************************************************
		if (httpServletRequest.getParameter("POPUP") != null) {
			if (httpServletRequest.getParameter("POPUP").equals("true")) {
				session.setAttribute("POPUP", "true");
			}else {
				session.setAttribute("POPUP", "false");
			}
		}else if (session.getAttribute("POPUP") == null) {
			session.setAttribute("POPUP", "false");
		}
		//**********************************************************************
		
        return retorno;
    }
}
