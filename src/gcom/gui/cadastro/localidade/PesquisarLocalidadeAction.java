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

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action que define o processamento da página de pesquisa de Localidade
 * 
 * @author Administrador
 */

public class PesquisarLocalidadeAction extends GcomAction {

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

        ActionForward retorno = actionMapping.findForward("listaLocalidade");
        
        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

        //cria variaveis
        String idLocalidade = (String) pesquisarActionForm.get("idLocalidade");
        String descricao = (String) pesquisarActionForm
                .get("descricaoLocalidade");
        String idGerencia = (String) pesquisarActionForm
                .get("idGerenciaRegional");
        String idElo = (String)sessao.getAttribute("idElo");
        String tipoPesquisa = (String) pesquisarActionForm.get("tipoPesquisa");

        boolean peloMenosUmParametroInformado = false;

        //Obtém a instância da Fachada
        Fachada fachada = Fachada.getInstancia();

        FiltroLocalidade filtroLocalidade = new FiltroLocalidade(FiltroLocalidade.DESCRICAO);
        
        //Objetos que serão retornados pelo Hibernate
        filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");

        if( sessao.getAttribute("indicadorUsoTodos") == null ){
    	    filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.INDICADORUSO,
                ConstantesSistema.INDICADOR_USO_ATIVO));
        }
        if (idLocalidade != null && !idLocalidade.trim().equalsIgnoreCase("")) {
            filtroLocalidade.adicionarParametro(new ParametroSimples(
                    FiltroLocalidade.ID, new Integer(idLocalidade)));
            peloMenosUmParametroInformado = true;
        }
        if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
        
    			if (tipoPesquisa != null
    					&& tipoPesquisa
    							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
    									.toString())) {
    				filtroLocalidade.adicionarParametro(new ComparacaoTextoCompleto(
    						FiltroLocalidade.DESCRICAO, descricao));
    			} else {
    				filtroLocalidade.adicionarParametro(new ComparacaoTexto(
    						FiltroLocalidade.DESCRICAO, descricao));
    			}
        
        }
        
        
        if (idGerencia != null && !idGerencia.trim().equalsIgnoreCase("")
                && !idGerencia.trim().equalsIgnoreCase("-1")) {
            filtroLocalidade.adicionarParametro(new ParametroSimples(
                    FiltroLocalidade.ID_GERENCIA, new Integer(idGerencia)));
            peloMenosUmParametroInformado = true;
        }
        if(idElo != null && !idElo.equals("")){
        	filtroLocalidade.adicionarParametro(new ParametroSimples(
                    FiltroLocalidade.ID_ELO, new Integer(idElo)));
        	 peloMenosUmParametroInformado = true;
        }

        if (!peloMenosUmParametroInformado) {
            throw new ActionServletException(
                    "atencao.filtro.nenhum_parametro_informado");
        }
        
        //          filtroLocalidade.adicionarParametro(new
        // ComparacaoCampos(FiltroLocalidade.ID, "localidade"));
        Collection localidades = fachada.pesquisar(filtroLocalidade,
                Localidade.class.getName());

        if (localidades != null && !localidades.isEmpty()) {
//        	 Aciona o controle de paginação para que sejam pesquisados apenas
			// os registros que aparecem na página
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroLocalidade, Localidade.class.getName());
			localidades = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			
			sessao.setAttribute("localidades", localidades);
        } else {
            throw new ActionServletException(
                    "atencao.pesquisa.nenhumresultado", null, "localidade");
        }

       // httpServletRequest.setAttribute("tipoPesquisa", sessao
        //       .getAttribute("tipoPesquisa"));

        //sessao.removeAttribute("tipoPesquisa");
        
        return retorno;
    }

}
