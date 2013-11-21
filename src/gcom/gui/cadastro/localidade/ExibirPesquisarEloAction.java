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
package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Author: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author not attributable
 * @created 18 de Maio de 2005
 * @version 1.0
 */

public class ExibirPesquisarEloAction extends GcomAction {
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

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("pesquisarElo");

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

        
        if(httpServletRequest.getParameter("objetoConsulta") == null){
        	pesquisarActionForm.set("descricao", "");
        	pesquisarActionForm.set("idGerenciaRegional", String
        			.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
        }
        String idGerencia = (String) httpServletRequest
                .getParameter("idGerencia");

        if (idGerencia != null && !idGerencia.trim().equalsIgnoreCase("")) {
            sessao.setAttribute("idGerencia", idGerencia);
        }

        Collection colecaoPesquisa = null;
        FiltroGerenciaRegional filtroGerenciaRegional = null;

        if (sessao.getAttribute("colecaoGerenciaRegional") == null) {

            filtroGerenciaRegional = new FiltroGerenciaRegional();
            
            filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
                    FiltroGerenciaRegional.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            //Retorna Gerencia_Regional
            colecaoPesquisa = fachada.pesquisar(filtroGerenciaRegional,
                    GerenciaRegional.class.getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                //Nenhum registro na tabela gerencia_regional foi encontrado
                throw new ActionServletException(
                        "atencao.pesquisa.nenhum_registro_tabela", null,
                        "Gerencia_Regional");
            } else {
                sessao.setAttribute("colecaoGerenciaRegional", colecaoPesquisa);
            }
        }

        //envia uma flag que será verificado no quadra_resultado_pesquisa.jsp
        //para saber se irá usar o enviar dados ou o enviar dados parametros
        if(httpServletRequest.getParameter("caminhoRetornoTelaPesquisa") != null){
        	sessao.setAttribute("caminhoRetornoTelaPesquisaElo", httpServletRequest
                .getParameter("caminhoRetornoTelaPesquisa"));
        }        
        
        //devolve o mapeamento de retorno
        return retorno;
    }
}
