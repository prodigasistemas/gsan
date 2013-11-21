/**
 * 
 */
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
package gcom.gui.cobranca;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaAtividade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pesquisa de Comando de Ação de Cobrança 
 *
 * [UC0243] Inserir Comando de Ação de Conbrança
 * @author Rafael Santos
 * @since 08/03/2006
 */
public class ExibirPesquisarComandoAcaoCobrancaAction  extends GcomAction{
	
	/**
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

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirPesquisarComandoAcaoCobrancaAction");

		// Mudar isso quando implementar a parte de segurança
        HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
        
		//CARREGAR AS COBRANÇAS ATIVIDADE  - INICIO
		
		FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
		filtroCobrancaAtividade.setCampoOrderBy(FiltroCobrancaAtividade.DESCRICAO);
		if(sessao.getAttribute("colecaoCobrancaAtividade") == null){        		
			Collection colecaoCobrancaAtividade = (Collection) fachada.pesquisar(filtroCobrancaAtividade,CobrancaAtividade.class.getName());
			
	        if(colecaoCobrancaAtividade != null && !colecaoCobrancaAtividade.isEmpty()){
	            //carregar atividade de cobrança
	        	sessao.setAttribute("colecaoCobrancaAtividade",colecaoCobrancaAtividade);
			} else {
				throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela",
					null, "Tabela Cobrança Atividade");
			}
		}
		
        
		//FIM COBRANÇA ATIVIDADE

		
		//CARREGAR AS COBRANÇAS ACAO  - INICIO
		
		FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
		filtroCobrancaAcao.setCampoOrderBy(FiltroCobrancaAcao.DESCRICAO);
		if(sessao.getAttribute("colecaoCobrancaAcao") == null){         		
			Collection colecaoCobrancaAcao = (Collection) fachada.pesquisar(filtroCobrancaAcao,CobrancaAcao.class.getName());
			
	        if(colecaoCobrancaAcao != null && !colecaoCobrancaAcao.isEmpty()){
	            //carregar ação de cobrança
	        	sessao.setAttribute("colecaoCobrancaAcao",colecaoCobrancaAcao);
			} else {
				throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela",
					null, "Tabela Cobrança Ação");
			}
		}
	        
		//FIM COBRANÇA Ação
        
		PesquisarComandoAcaoCobrancaActionForm pesquisarComandoAcaoCobrancaActionForm = (PesquisarComandoAcaoCobrancaActionForm)actionForm;
		String idUsuario = pesquisarComandoAcaoCobrancaActionForm.getIdUsuario();  
		if(idUsuario != null && !idUsuario.equals("")){
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID,idUsuario));
			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario,Usuario.class.getName());
			if(colecaoUsuario != null && !colecaoUsuario.isEmpty()){
				Usuario usuario = (Usuario) colecaoUsuario.iterator().next();
				pesquisarComandoAcaoCobrancaActionForm.setUsuario(usuario.getNomeUsuario());
				httpServletRequest.setAttribute("usuarioNaoEncontrado",null);
			}else{
				httpServletRequest.setAttribute("usuarioNaoEncontrado","true");
				pesquisarComandoAcaoCobrancaActionForm.setUsuario("USUÁRIO INEXISTENTE");
			}
		}
        
        if (httpServletRequest.getParameter("tipoConsulta") != null
                && !httpServletRequest.getParameter("tipoConsulta").equals("")) {
            
        	pesquisarComandoAcaoCobrancaActionForm.setIdUsuario(
                        httpServletRequest.getParameter("idCampoEnviarDados"));
        	pesquisarComandoAcaoCobrancaActionForm.setUsuario(
        			    httpServletRequest.getParameter("descricaoCampoEnviarDados"));
        	sessao.removeAttribute("caminhoRetornoTelaPesquisaUsuario");
        	sessao.removeAttribute("caminhoRetornoTelaPesquisa");
        }
           
        pesquisarComandoAcaoCobrancaActionForm.setTipoPesquisa("1");
        return retorno;
    }

}
