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

import gcom.cobranca.parcelamento.FiltroParcelamentoPerfil;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Realiza o filtro do Perfil de Parcelamento de acordo com os parâmetros informados
 * 
 * @author Vivianne Sousa
 * @created 11/05/2006
 */
public class FiltrarPerfilParcelamentoAction extends GcomAction {
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


        ActionForward retorno = actionMapping.findForward("retornarFiltroPerfilParcelamento");
        FiltrarPerfilParcelamentoActionForm filtrarPerfilParcelamentoActionForm = 
    		(FiltrarPerfilParcelamentoActionForm) actionForm;
        HttpSession sessao = httpServletRequest.getSession(false);
        //Fachada fachada = Fachada.getInstancia();
        Boolean peloMenosUmParametroInformado = false;
        
        String idResolucaoDiretoria = filtrarPerfilParcelamentoActionForm.getResolucaoDiretoria();
		String idImovelSituacaoTipo = filtrarPerfilParcelamentoActionForm.getImovelSituacaoTipo();
		String idImovelPerfil = filtrarPerfilParcelamentoActionForm.getImovelPerfil();
		String idSubcategoria = filtrarPerfilParcelamentoActionForm.getSubcategoria();
		// 1 check   --- null uncheck 
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");

		FiltroParcelamentoPerfil filtroParcelamentoPerfil = new FiltroParcelamentoPerfil();
		
		if (idResolucaoDiretoria != null 
				&& !idResolucaoDiretoria.equalsIgnoreCase("")){

	    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("resolucaoDiretoria");
	    	filtroParcelamentoPerfil.adicionarParametro(new ParametroSimples(
	    			FiltroParcelamentoPerfil.RESOLUCAO_DIRETORIA_ID, idResolucaoDiretoria));
	    	peloMenosUmParametroInformado = true;	

		}

    	if (idImovelSituacaoTipo != null 
    			&& !idImovelSituacaoTipo.equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("imovelSituacaoTipo");
        	filtroParcelamentoPerfil.adicionarParametro(new ParametroSimples(
        			FiltroParcelamentoPerfil.IMOVEL_SITUACAO_TIPO_ID, idImovelSituacaoTipo));
        	peloMenosUmParametroInformado = true;
    	}

    	
    	if (idImovelPerfil != null
    			&& !idImovelPerfil.equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
        	filtroParcelamentoPerfil.adicionarParametro(new ParametroSimples(
        			FiltroParcelamentoPerfil.IMOVEL_PERFIL_ID, idImovelPerfil));
        	peloMenosUmParametroInformado = true;
    		
    	}

		if (idSubcategoria != null 
				&& !idSubcategoria.equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
	    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("subcategoria");
	    	filtroParcelamentoPerfil.adicionarParametro(new ParametroSimples(
	    			FiltroParcelamentoPerfil.SUBCATEGORIA_ID, idSubcategoria));
	    	peloMenosUmParametroInformado = true;
		}

		//[FS0003] Verificar preenchimento dos campos
		if (!peloMenosUmParametroInformado){
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		sessao.setAttribute("filtroParcelamentoPerfil",filtroParcelamentoPerfil);
		sessao.setAttribute("indicadorAtualizar",indicadorAtualizar );
	
       return retorno;
    }
   
    

}
 