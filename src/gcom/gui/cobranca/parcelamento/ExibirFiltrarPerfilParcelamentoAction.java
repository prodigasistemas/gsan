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
package gcom.gui.cobranca.parcelamento;

import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroImovelSituacaoTipo;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSituacaoTipo;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cobranca.FiltroResolucaoDiretoria;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.fachada.Fachada;
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


/**
 * Action que define o pré-processamento da página de filtrar Perfil de Parcelamento
 * 
 * @author Vivianne Sousa
 * @created 11/05/2006
 */
public class ExibirFiltrarPerfilParcelamentoAction extends GcomAction {
	/**
	 * Este caso de uso permite o filtro de um Perfil de Parcelamento
	 * 
	 * [UC0222] Filtrar Perfil de Parcelamento
	 * 
	 * 
	 * @author Vivianne Sousa
	 * @date 11/05/2006
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


        ActionForward retorno = actionMapping.findForward("filtrarPerfilParcelamento");
        FiltrarPerfilParcelamentoActionForm filtrarPerfilParcelamentoActionForm = 
        		(FiltrarPerfilParcelamentoActionForm) actionForm;
        Fachada fachada = Fachada.getInstancia();
        HttpSession sessao = httpServletRequest.getSession(false);

        //Pesquisando Resolucao Diretoria
		FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();
		filtroResolucaoDiretoria.setCampoOrderBy(FiltroResolucaoDiretoria.NUMERO);
        Collection<ResolucaoDiretoria> collectionResolucaoDiretoria = fachada.pesquisar(filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());
        sessao.setAttribute("collectionResolucaoDiretoria", collectionResolucaoDiretoria);
        //Fim de pesquisando Resolucao Diretoria
        
        //Pesquisando Tipo da Situacao do Imóvel
        FiltroImovelSituacaoTipo filtroImovelSituacaoTipo = new FiltroImovelSituacaoTipo();
        filtroImovelSituacaoTipo.setCampoOrderBy(FiltroImovelSituacaoTipo.DESCRICAO_IMOVEL_SITUACAO_TIPO);
        Collection<ImovelSituacaoTipo> collectionImovelSituacaoTipo = fachada.pesquisar(filtroImovelSituacaoTipo, ImovelSituacaoTipo.class.getName());
        sessao.setAttribute("collectionImovelSituacaoTipo", collectionImovelSituacaoTipo);
        //Fim de pesquisando Tipo da Situacao do Imóvel
        

        //Pesquisando Perfil do Imóvel
        FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
        filtroImovelPerfil.adicionarParametro(new ParametroSimples(
        		FiltroImovelPerfil.INDICADOR_USO,  new Short("1")));
        filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
        Collection<ImovelPerfil> collectionImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
        sessao.setAttribute("collectionImovelPerfil", collectionImovelPerfil);
        //Fim de pesquisando Perfil do Imóvel
        
        
        //Pesquisando Categoria
        FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
        filtroSubCategoria.adicionarParametro(new ParametroSimples(
        		FiltroSubCategoria.INDICADOR_USO,  new Short("1")));
        filtroSubCategoria.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);
        Collection<Subcategoria> collectionSubcategoria = fachada.pesquisar(filtroSubCategoria, Subcategoria.class.getName());
        sessao.setAttribute("collectionSubcategoria", collectionSubcategoria);
        //Fim de pesquisando Categoria

        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	//-------------- bt DESFAZER ---------------
        	
        	filtrarPerfilParcelamentoActionForm.setResolucaoDiretoria("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	filtrarPerfilParcelamentoActionForm.setImovelSituacaoTipo("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	filtrarPerfilParcelamentoActionForm.setImovelPerfil("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	filtrarPerfilParcelamentoActionForm.setSubcategoria("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	sessao.setAttribute("indicadorAtualizar","1");
            sessao.removeAttribute("caminhoRetornoTelaPesquisa");

        }
        
        // código para checar ou naum o Atualizar
        String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar","1");
		}
      
        
        sessao.removeAttribute("caminhoRetornoTelaPesquisa");
		sessao.removeAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
		sessao.removeAttribute("collectionParcelamentoDescontoInatividade");
		sessao.removeAttribute("collectionParcelamentoDescontoAntiguidade");
		sessao.removeAttribute("collectionParcelamentoDescontoInatividadeAVista");
		
        return retorno;
    }
    
   

}
 