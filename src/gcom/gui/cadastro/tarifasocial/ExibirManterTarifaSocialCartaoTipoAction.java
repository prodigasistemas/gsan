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
package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.tarifasocial.FiltroTarifaSocialCartaoTipo;
import gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirManterTarifaSocialCartaoTipoAction extends GcomAction {
    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Define ação de retorno
        ActionForward retorno = actionMapping
                .findForward("manterTarifaSocialCartaoTipo");

        //Obtém a fachada
        Fachada fachada = Fachada.getInstancia();

        //Form dinâmico para obter
        //DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

        //Inicializa a coleção
        Collection colecaoTarifaSocialCartaoTipo = null;

        //Mudar isso quando implementar a parte de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        //Criação do filtro de tarifa social cartão tipo
        FiltroTarifaSocialCartaoTipo filtroTarifaSocialCartaoTipo = null;

        //Verifica se o filtro foi informado pela página de filtragem de
        // logradouro
        if (httpServletRequest.getAttribute("filtroTarifaSocialCartaoTipo") != null) {
            filtroTarifaSocialCartaoTipo = (FiltroTarifaSocialCartaoTipo) httpServletRequest
                    .getAttribute("filtroTarifaSocialCartaoTipo");
        } else {
            //Caso o exibirManterTarifaSocialCartaoTipo não tenha passado por
            // algum esquema de filtro,
            //a quantidade de registros é verificada para avaliar a necessidade
            // de filtragem
            filtroTarifaSocialCartaoTipo = new FiltroTarifaSocialCartaoTipo();

            if (fachada.registroMaximo(TarifaSocialCartaoTipo.class) > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_MANUTENCAO) {
                //Se o limite de registros foi atingido, a página de filtragem
                // é chamada
                retorno = actionMapping
                        .findForward("filtrarTarifaSocialCartaoTipo");
                //limpa os parametros do form pesquisar
                sessao.removeAttribute("PesquisarActionForm");
            }
        }

        //A pesquisa de tarifa social cartão tipo só será feita se o forward
        // estiver direcionado
        //para a página de manterTarifaSocialCartaoTipo
        if (retorno.getName().equalsIgnoreCase("manterTarifaSocialCartaoTipo")) {
                        
        	Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroTarifaSocialCartaoTipo, TarifaSocialCartaoTipo.class.getName());
			colecaoTarifaSocialCartaoTipo = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

            if (colecaoTarifaSocialCartaoTipo == null
                    || colecaoTarifaSocialCartaoTipo.isEmpty()) {
                //Nenhum cliente cadastrado
                throw new ActionServletException("atencao.naocadastrado", null,
                        "tipo de cartão da tarifa social");
            }

            /*
             * if (logradouros.size() >
             * ConstantesSistema.NUMERO_MAXIMO_REGISTROS_MANUTENCAO) { throw new
             * ActionServletException("atencao.pesquisa.muitosregistros"); }
             */
            sessao.setAttribute("colecaoTarifaSocialCartaoTipo",
                    colecaoTarifaSocialCartaoTipo);

        }
        return retorno;
    }

}
