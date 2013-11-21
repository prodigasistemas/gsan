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
package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.Bairro;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade incluir os bairros que foram selecionados pelo usuário na coleção final
 *
 * @author Raphael Rossiter
 * @date 02/05/2006
 */
public class InserirSelecaoBairroAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirSelecionarBairro");

        HttpSession sessao = httpServletRequest.getSession(false);

        SelecionarBairroActionForm selecionarBairroActionForm = (SelecionarBairroActionForm) actionForm;
        
        String tipoRetorno = (String) sessao.getAttribute("tipoPesquisaRetorno");
        //String tipoOperacao = (String) sessao.getAttribute("operacao");

        if (tipoRetorno != null && !tipoRetorno.trim().equalsIgnoreCase("")){
        	
        
        	if (sessao.getAttribute("colecaoBairrosSelecionados") != null) {

            // Coleção retornada pela pesquisa
            Collection colecaoBairrosSelecionados = (Collection) sessao
            .getAttribute("colecaoBairrosSelecionados");

            Collection colecaoBairrosSelecionadosUsuario = new ArrayList();

            if (selecionarBairroActionForm.getIdBairroSelecao() != null) {

                Iterator  colecaoBairrosSelecionadosIterator;

                Bairro bairroInserir;

                int indexArray = 0;
                Integer bairroID;

                // Bairros selecionadas pelo usuário
                String[] bairrosSelecionados = selecionarBairroActionForm.getIdBairroSelecao();

                while (bairrosSelecionados.length > indexArray) {
                    bairroID = new Integer(bairrosSelecionados[indexArray]);

                    colecaoBairrosSelecionadosIterator = colecaoBairrosSelecionados
                    .iterator();

                    while (colecaoBairrosSelecionadosIterator.hasNext()) {

                    	bairroInserir = (Bairro) colecaoBairrosSelecionadosIterator
                        .next();

                        if (bairroInserir.getId().equals(bairroID)) {
                        	colecaoBairrosSelecionadosUsuario.add(bairroInserir);
                            break;
                        }
                    }

                    indexArray++;
                }

                // A coleção pode ser acumulativa ou está sendo gerada pela primeira vez
                if (sessao.getAttribute("colecaoBairrosSelecionadosUsuario") != null) {
                
                	Collection colecaoBairrosSelecionadosUsuarioSessao = (Collection) sessao
                            .getAttribute("colecaoBairrosSelecionadosUsuario");

                    Bairro bairro;
                    Iterator iteratorColecaoBairrosSelecionadosUsuario = 
                    colecaoBairrosSelecionadosUsuario.iterator();
                    
                    while(iteratorColecaoBairrosSelecionadosUsuario.hasNext()){
                    	bairro = (Bairro) iteratorColecaoBairrosSelecionadosUsuario.next();
                    	
                    	if (!colecaoBairrosSelecionadosUsuarioSessao.contains(bairro)){
                    		colecaoBairrosSelecionadosUsuarioSessao.add(bairro);
                    	}
                    	else{
                    		throw new ActionServletException(
                                    "atencao.objeto_ja_selecionado", null, "Bairro");
                    	}
                    }
                
                } else {
                    sessao.setAttribute("colecaoBairrosSelecionadosUsuario",
                    		colecaoBairrosSelecionadosUsuario);
                }

                // Remove a coleção gerada pela pesquisa efetuada pelo usuário
                sessao.removeAttribute("colecaoBairrosSelecionados");

                // Flag para indicar o retorno para o caso de uso que chamou a funcionalidade
                httpServletRequest.setAttribute("retornarUseCase", "OK");
                
                
                if (tipoRetorno.trim().equalsIgnoreCase("logradouro")) {
                    httpServletRequest.setAttribute("flagRedirect", "logradouro");
                } else if (tipoRetorno.trim().equalsIgnoreCase("endereco")) {
                    httpServletRequest.setAttribute("flagRedirect", "endereco");
                }
            }

        	}
        }
        return retorno;
    }

}
