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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirInserirImovelPrincipalAction extends GcomAction {

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

        ActionForward retorno = actionMapping
                .findForward("inserirImovelPrincipal");

        //Obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        DynaValidatorForm inserirImovelPrincipalActionForm = (DynaValidatorForm) sessao
                .getAttribute("InserirImovelActionForm");

        String pesquisar = httpServletRequest.getParameter("pesquisar");
        
        //Cria variaveis
        String idImovel = (String) inserirImovelPrincipalActionForm
                .get("idImovel");

        //Cria Filtros
        FiltroImovel filtroImovel = new FiltroImovel();
        
        //Objetos que serão retornados pelo Hibernate
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
        
        filtroImovel.adicionarParametro(new ParametroSimplesDiferenteDe(
				FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO,
				Imovel.IMOVEL_EXCLUIDO, FiltroParametro.CONECTOR_OR,2));

        filtroImovel.adicionarParametro(new ParametroNulo(
				FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO));
        

        //Obtém a instância da Fachada
        Fachada fachada = Fachada.getInstancia();

        Collection imoveis = null;//new HashSet();

        //       sessao.setAttribute("imoveisPrincipal", imoveis);

        if (idImovel != null && !idImovel.trim().equalsIgnoreCase("")) {
            filtroImovel.adicionarParametro(new ParametroSimples(
                    FiltroImovel.ID, new Integer(idImovel.trim())));

            imoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());

            if (imoveis != null && !imoveis.isEmpty()) {
                /*
                 * inserirImovelPrincipalActionForm.set("idImovel", ((Imovel)
                 * ((List) imoveis).get(0)).getId().toString());
                 * inserirImovelPrincipalActionForm.set("descricaoImovel",
                 * ((Imovel) ((List) imoveis).get(0)).getNumeroImovel());
                 * inserirImovelPrincipalActionForm.set("tipoLogradouro",
                 * ((Imovel) ((List)
                 * imoveis).get(0)).getLogradouro().getLogradouroTipo().getDescricao());
                 * inserirImovelPrincipalActionForm.set("tituloLogradouro",
                 * ((Imovel) ((List)
                 * imoveis).get(0)).getLogradouro().getLogradouroTitulo().getDescricao());
                 * inserirImovelPrincipalActionForm.set("logradouro", ((Imovel)
                 * ((List) imoveis).get(0)).getLogradouro().getNome());
                 * inserirImovelPrincipalActionForm.set("municipio", ((Imovel)
                 * ((List)
                 * imoveis).get(0)).getLogradouro().getMunicipio().getNome());
                 * inserirImovelPrincipalActionForm.set("bairro", ((Imovel)
                 * ((List) imoveis).get(0)).getQuadra().getBairro().getNome());
                 */
                sessao.setAttribute("imoveisPrincipal", imoveis);
            } else {
            	if(pesquisar != null && !pesquisar.equalsIgnoreCase("")){
            		throw new ActionServletException("atencao.pesquisa.imovel.inexistente", null,
            				idImovel);
            	}
            }

        }

        return retorno;
    }

}
