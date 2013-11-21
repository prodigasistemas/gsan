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
package gcom.gui.micromedicao;

import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Atualizar Tipo de Rateio
 * 
 * @author Rafael Santos, Magno Gouveia
 * @since 12/01/2006, 20/08/2011
 */
public class DesfazerVinculoPopupAction extends GcomAction {
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

        ActionForward retorno = actionMapping.findForward("telaSucessoPopup");

        Fachada fachada = Fachada.getInstancia();
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        DesfazerVinculoPopupActionForm form = (DesfazerVinculoPopupActionForm) actionForm;
        
        String[] ids = form.getIdImovel();
        
        Imovel imovelCondominio = null;
        if(sessao.getAttribute("imovelCondominio") != null){
        	imovelCondominio = (Imovel) sessao.getAttribute("imovelCondominio") ;
        }
        
        boolean desvincular = false;
        if(ids.length == ((Collection) sessao.getAttribute("colecaoImoveisVinculados")).size()){
        	desvincular = true;
        } else {
        	/*
			 * SB0003 - FS 4. Caso a opção de desvincular não seja TODOS, o
			 * sistema verifica se o imóvel com indicador de área comum foi
			 * selecionado, [FS0015 - Pesquisar Imóvel Área Comum]
			 */
        	Integer idImovelAreaComum = fachada.pesquisarImovelAreaComum(imovelCondominio.getId());
        	if (idImovelAreaComum != null) {
        		for (String idImovelParaRemover : ids) {
        			
					if (Integer.parseInt(idImovelParaRemover.trim()) == idImovelAreaComum.intValue()) {
						throw new ActionServletException("atencao.imovel_selecionado_corresponde_area_comum", idImovelParaRemover);
					}
				}
        	}
        }
        
        /** alterado por pedro alexandre dia 20/11/2006 
         * Recupera o usuário logado para passar no metódo de desfazer vínculo
         * para verificar se o usuário tem abrangência para desfazer o vínculo do imóvel
         * informado.
         */
        Usuario usuarioLogado = (Usuario) sessao.getAttribute(Usuario.USUARIO_LOGADO);
        fachada.desfazerVinculo(imovelCondominio, ids, desvincular, usuarioLogado);
        //fachada.desfazerVinculo(imovel,ids,desvincular);
        
        //remove da sessao
        if(sessao.getAttribute("colecaoImoveisVinculados") != null){
        	sessao.removeAttribute("colecaoImoveisVinculados");
        }
        if(sessao.getAttribute("imovelCondominio") != null){
        	sessao.removeAttribute("imovelCondominio") ;
        }
        
        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucessoPopup")) {
            montarPaginaSucesso(httpServletRequest,
            		ids.length + " imóvel(is) desvinculado(s) do imóvel condomínio " + imovelCondominio.getId()+" com sucesso." ,
                    "",
            		"");
        }        
        
       return retorno;
    }
}
 