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
* Thiago Vieira
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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.NegativadorRetornoMotivo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativadorRetornoMotivo;
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
 * Description of the Class
 * 
 * @author Thiago Vieira
 */
public class FiltrarNegativadorRetornoMotivoAction extends GcomAction {

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

//    	 localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("exibirManterNegativadorRetornoMotivo");
		HttpSession sessao = httpServletRequest.getSession(false);

        FiltrarNegativadorRetornoMotivoActionForm form = (FiltrarNegativadorRetornoMotivoActionForm) actionForm; 
        
        Integer idNegativador = 0;
        short codigoRetornoMotivo = 0;
        String descricaoRetornoMotivo = "";
        short indicadorRegistroAceito = 0;
        String checkAtualizar = "";
        short indicadorUso = 0;
        
        if (form.getIdNegativador() != null && !form.getIdNegativador().equals("")){
        	idNegativador = new Integer(form.getIdNegativador());
        }
        if (form.getCodigoMotivo() != null && !form.getCodigoMotivo().equals("")){
        	codigoRetornoMotivo = Short.parseShort(form.getCodigoMotivo());
        } 
        if (form.getDescricaoRetornoMotivo() != null && !form.getDescricaoRetornoMotivo().equals("") ){
        	descricaoRetornoMotivo = form.getDescricaoRetornoMotivo();
        }
        if (form.getIndicadorRegistroAceito() != null && !form.getIndicadorRegistroAceito().equals("") && !form.getIndicadorRegistroAceito().equals(ConstantesSistema.TODOS.toString())){
        	indicadorRegistroAceito = Short.parseShort(form.getIndicadorRegistroAceito());
        }
        if (form.getIndicadorUso() != null && !form.getIndicadorUso().equals("") && !form.getIndicadorUso().equals(ConstantesSistema.TODOS.toString())){
        	indicadorUso = Short.parseShort(form.getIndicadorUso());
        }
        if (form.getCheckAtualizar() != null && !form.getCheckAtualizar().equals("")){
        	checkAtualizar = form.getCheckAtualizar();
        }
        
		FiltroNegativadorRetornoMotivo filtroNegativadorRetornoMotivo = new FiltroNegativadorRetornoMotivo();
		
		if (form.getIdNegativador() != null && !form.getIdNegativador().equals("")){
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(
					FiltroNegativadorRetornoMotivo.NEGATIVADOR_RETORNO_MOTIVO_NEGATIVADOR, idNegativador));
		}
		
		if (form.getCodigoMotivo() != null && !form.getCodigoMotivo().equals("")){
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(
					FiltroNegativadorRetornoMotivo.CODIGO_RETORNO_MOTIVO, codigoRetornoMotivo));
		}
		
		if (form.getDescricaoRetornoMotivo() != null && !form.getDescricaoRetornoMotivo().equals("")){
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(
					FiltroNegativadorRetornoMotivo.DESCRICAO_RETORNO_CODIGO, descricaoRetornoMotivo));
		}
		
		if (form.getIndicadorRegistroAceito() != null && !form.getIndicadorRegistroAceito().equals("") && !form.getIndicadorRegistroAceito().equals(ConstantesSistema.TODOS.toString())){
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(
					FiltroNegativadorRetornoMotivo.INDICADOR_REGISTRO_ACEITO, indicadorRegistroAceito));
		}
		
		if (form.getIndicadorUso() != null && !form.getIndicadorUso().equals("") && !form.getIndicadorUso().equals(ConstantesSistema.TODOS.toString())){
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(
					FiltroNegativadorRetornoMotivo.INDICADOR_USO, indicadorUso));
		}

		//check do código do motivo
        //caso não exista, informa erro no preenchimento do campo de codigo de retorno
        if (codigoRetornoMotivo != 0){
			FiltroNegativadorRetornoMotivo fNRM = new FiltroNegativadorRetornoMotivo();
			fNRM.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.CODIGO_RETORNO_MOTIVO, codigoRetornoMotivo));
        	
        	Collection codigoMotivoEncontrado = Fachada.getInstancia().pesquisar(fNRM, NegativadorRetornoMotivo.class.getName());
        	        	
        	if (codigoMotivoEncontrado == null || codigoMotivoEncontrado.isEmpty()) {
        		throw new ActionServletException("atencao.codigo_motivo_negativador_retorno_motivo_nao_existe_cadastro");
        	}
		}
		
		sessao.setAttribute("filtroNegativadorRetornoMotivo",filtroNegativadorRetornoMotivo);
		sessao.setAttribute("checkAtualizar",checkAtualizar);
		
		return retorno;
        
    }
}