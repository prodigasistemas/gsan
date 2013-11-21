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
package gcom.gui.cadastro.funcionario;

import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Realiza a pesquisa de funcionario de acordo com os parâmetros informados
 * 
 * @author Vivianne Sousa
 * @created 02/03/2006
 */

public class ExibirPesquisarFuncionarioAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("funcionarioPesquisar");
        
        //PesquisarFuncionarioActionForm pesquisarFuncionarioActionForm = (PesquisarFuncionarioActionForm) actionForm;
        
        PesquisarFuncionarioActionForm pesquisarFuncionarioActionForm = (PesquisarFuncionarioActionForm) actionForm;
        
        HttpSession sessao = httpServletRequest.getSession(false);
            
        if ((httpServletRequest.getParameter("desfazer") != null && 
        	httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S"))){
        	
        	pesquisarFuncionarioActionForm.setId("");
        	pesquisarFuncionarioActionForm.setNome("");
        	pesquisarFuncionarioActionForm.setIdUnidadeEmpresa("");
        	pesquisarFuncionarioActionForm.setDescricaoUnidadeEmpresa("");
        }
        
        //-------Parte que trata do código quando o usuário tecla enter        

        String idDigitadoEnterUnidadeEmpresa = pesquisarFuncionarioActionForm.getIdUnidadeEmpresa();
        
        //Verifica se o código da Unidade Empresa foi digitado
        if (idDigitadoEnterUnidadeEmpresa != null && 
        	!idDigitadoEnterUnidadeEmpresa.trim().equals("") && 
        	Integer.parseInt(idDigitadoEnterUnidadeEmpresa) > 0) {
        	
        	FiltroUnidadeOrganizacional filtroUnidadeEmpresa = new FiltroUnidadeOrganizacional();

        	filtroUnidadeEmpresa.adicionarParametro(
        		new ParametroSimples(
        			FiltroUnidadeOrganizacional.ID, 
        			idDigitadoEnterUnidadeEmpresa));
			
			Collection<UnidadeOrganizacional> unidadeEmpresaEncontrada = 
				this.getFachada().pesquisar(filtroUnidadeEmpresa,
					UnidadeOrganizacional.class.getName());

			if (unidadeEmpresaEncontrada != null && !unidadeEmpresaEncontrada.isEmpty()) {

				//a unidade de Unidade Empresa foi encontrado
				pesquisarFuncionarioActionForm.setIdUnidadeEmpresa("" + 
					((UnidadeOrganizacional) ((List) unidadeEmpresaEncontrada).get(0)).getId());
				
				pesquisarFuncionarioActionForm.setDescricaoUnidadeEmpresa(
					((UnidadeOrganizacional) ((List) unidadeEmpresaEncontrada).get(0)).getDescricao());
				
				httpServletRequest.setAttribute("idUnidadeEmpresaNaoEncontrada","true");
				httpServletRequest.setAttribute("nomeCampo", "descricaoUnidadeEmpresa");

			} else {

				pesquisarFuncionarioActionForm.setIdUnidadeEmpresa("");
				httpServletRequest.setAttribute("idUnidadeEmpresaNaoEncontrada","exception");
				pesquisarFuncionarioActionForm.setDescricaoUnidadeEmpresa("UNIDADE DE LOTAÇÃO INEXISTENTE");
				httpServletRequest.setAttribute("nomeCampo", "idUnidadeEmpresa");

			}

        }
        //-------Fim de parte que trata do código quando o usuário tecla enter    
        
        if (httpServletRequest.getParameter("tipoConsulta") != null
                && !httpServletRequest.getParameter("tipoConsulta").equals("")) {
            //se for os parametros de enviarDadosParametros serão mandados para
            // a pagina usuario_pesquisar.jsp
        	pesquisarFuncionarioActionForm.setIdUnidadeEmpresa(
                        httpServletRequest.getParameter("idCampoEnviarDados"));
        	pesquisarFuncionarioActionForm.setDescricaoUnidadeEmpresa(
        			    httpServletRequest.getParameter("descricaoCampoEnviarDados"));
        }
        
        //envia uma flag que será verificado no funcionario_resultado_pesquisa.jsp
        //para saber se irá usar o enviar dados ou o enviar dados parametros
        if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaFuncionario") != null) {
        	  sessao.setAttribute("caminhoRetornoTelaPesquisaFuncionario",
        	          httpServletRequest
        	                  .getParameter("caminhoRetornoTelaPesquisaFuncionario"));
        	}
        sessao.removeAttribute("caminhoRetornoTelaPesquisaUnidadeEmpresa");
        
        if (httpServletRequest.getAttribute("nomeCampo") == null){
        	httpServletRequest.setAttribute("nomeCampo", "id");
        }
        
        if (httpServletRequest.getParameter("limpaForm") != null){
        	pesquisarFuncionarioActionForm.setDescricaoUnidadeEmpresa("");
        	pesquisarFuncionarioActionForm.setId("");
        	pesquisarFuncionarioActionForm.setIdUnidadeEmpresa("");
        	pesquisarFuncionarioActionForm.setNome("");
        }
        

        pesquisarFuncionarioActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
        
        if(httpServletRequest.getParameter("tipo") != null){
			sessao.setAttribute("tipo", httpServletRequest.getParameter("tipo"));
		}
        
        return retorno;
        
    }

}