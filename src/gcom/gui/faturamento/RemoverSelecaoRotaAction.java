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
package gcom.gui.faturamento;

import gcom.gui.GcomAction;
import gcom.micromedicao.Rota;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

public class RemoverSelecaoRotaAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
    	String forward = httpServletRequest.getParameter("forward");

        //Seta o retorno
        ActionForward retorno = actionMapping
                .findForward(forward);

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //Formulário de pesquisa
        DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

        if (forward.equalsIgnoreCase("exibirSelecionarRota") &&
        	sessao.getAttribute("colecaoRotasSelecionadasUsuario") != null) {

            // Coleção retornada pela pesquisa
            Collection colecaoRotasSelecionadasUsuario = (Collection) sessao
                    .getAttribute("colecaoRotasSelecionadasUsuario");

            // Coleção que irá ser gerada a partir da seleção efetuada pelo
            // usuário
            if (pesquisarActionForm.get("idRotaSelecao") != null) {

                Iterator colecaoRotasSelecionadasUsuarioIterator;

                Rota rotaInserir;

                int indexArray = 0;
                Integer rotaID;

                // Rotas selecionadas pelo usuário para serem removidas da
                // sessão
                String[] rotasSelecionadas = (String[]) pesquisarActionForm
                        .get("idRotaSelecao");

                while (rotasSelecionadas.length > indexArray) {
                    rotaID = new Integer(rotasSelecionadas[indexArray]);

                    colecaoRotasSelecionadasUsuarioIterator = colecaoRotasSelecionadasUsuario
                            .iterator();

                    while (colecaoRotasSelecionadasUsuarioIterator.hasNext()) {

                        rotaInserir = (Rota) colecaoRotasSelecionadasUsuarioIterator
                                .next();

                        if (rotaInserir.getId().equals(rotaID)) {
                            colecaoRotasSelecionadasUsuario.remove(rotaInserir);
                            break;
                        }
                    }

                    indexArray++;
                }
            }
        }
        else if (forward.equalsIgnoreCase("exibirFiltrarRotaComandoAtividade") &&
            	sessao.getAttribute("colecaoRotasSelecionadas") != null){
        	
        	//Coleção retornada pela pesquisa
            Collection colecaoRotasSelecionadasUsuario = (Collection) sessao
                    .getAttribute("colecaoRotasSelecionadas");

            // Coleção que irá ser gerada a partir da seleção efetuada pelo
            // usuário
            if (pesquisarActionForm.get("idRotaSelecao") != null) {

                Iterator colecaoRotasSelecionadasUsuarioIterator;

                Rota rotaInserir;

                int indexArray = 0;
                Integer rotaID;

                // Rotas selecionadas pelo usuário para serem removidas da
                // sessão
                String[] rotasSelecionadas = (String[]) pesquisarActionForm
                        .get("idRotaSelecao");

                while (rotasSelecionadas.length > indexArray) {
                    rotaID = new Integer(rotasSelecionadas[indexArray]);

                    colecaoRotasSelecionadasUsuarioIterator = colecaoRotasSelecionadasUsuario
                            .iterator();

                    while (colecaoRotasSelecionadasUsuarioIterator.hasNext()) {

                        rotaInserir = (Rota) colecaoRotasSelecionadasUsuarioIterator
                                .next();

                        if (rotaInserir.getId().equals(rotaID)) {
                            colecaoRotasSelecionadasUsuario.remove(rotaInserir);
                            break;
                        }
                    }

                    indexArray++;
                }
            }
        }

        //devolve o mapeamento de retorno
        return retorno;
    }
}