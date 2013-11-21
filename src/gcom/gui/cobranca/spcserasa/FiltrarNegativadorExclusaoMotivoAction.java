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
* Yara Taciane de Souza
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

import gcom.cobranca.FiltroNegativadorExclusaoMotivo;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorExclusaoMotivo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativador;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Realiza o filtro do Negativador Exclusao Motivo de acordo com os parâmetros informados
 * 
 * @author Yara Taciane de Souza
 * @created 03/01/2008
 */
public class FiltrarNegativadorExclusaoMotivoAction extends GcomAction {
	/**
	 * Este caso de uso permite o filtro de um Negativador Exclusao Motivo
	 * 
	 * [UC0670] Filtrar Motivo Exclusao Negativador
	 * 
	 * 
	 * @author Yara Taciane de Souza
	 * @date 03/01/2007
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


        ActionForward retorno = actionMapping.findForward("retornarFiltroNegativadorExclusaoMotivo");
        
        FiltrarNegativadorExclusaoMotivoActionForm filtrarNegativadorExclusaoMotivoActionForm = (FiltrarNegativadorExclusaoMotivoActionForm) actionForm;
       
        HttpSession sessao = httpServletRequest.getSession(false);
        
    	Fachada fachada = Fachada.getInstancia();
        
        //Fachada fachada = Fachada.getInstancia();
        
        Boolean peloMenosUmParametroInformado = false;
        
   //-------------------------------------------------------------------------
        String idNegativador = null;
		if (filtrarNegativadorExclusaoMotivoActionForm.getIdNegativador() != null
				&& !filtrarNegativadorExclusaoMotivoActionForm
						.getIdNegativador().equals("-1")) {			
			idNegativador=filtrarNegativadorExclusaoMotivoActionForm.getIdNegativador();
		} else {
			throw new ActionServletException("atencao.required", null,
					"Negativador");
		}
     
        String codigoMotivo = null;
        if(!"".equals(filtrarNegativadorExclusaoMotivoActionForm.getCodigoMotivo())){
        	codigoMotivo = filtrarNegativadorExclusaoMotivoActionForm.getCodigoMotivo(); 
        }
        String descricaoExclusaoMotivo = null;
        if(!"".equals(filtrarNegativadorExclusaoMotivoActionForm.getDescricaoExclusaoMotivo())){
        	descricaoExclusaoMotivo = filtrarNegativadorExclusaoMotivoActionForm.getDescricaoExclusaoMotivo(); 
        }        
        String indicadorUso = null;
        if(!"-1".equals(filtrarNegativadorExclusaoMotivoActionForm.getIndicadorUso())){
        	indicadorUso = filtrarNegativadorExclusaoMotivoActionForm.getIndicadorUso(); 
        }
   
		// 1 check   --- null uncheck 
		String indicadorAtualizar = filtrarNegativadorExclusaoMotivoActionForm.getIndicadorAtualizar();

		FiltroNegativadorExclusaoMotivo filtroNegativadorExclusaoMotivo = new FiltroNegativadorExclusaoMotivo();
		
		if (idNegativador != null && !idNegativador.equalsIgnoreCase("")){
			filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorExclusaoMotivo.NEGATIVADOR_ID, idNegativador));
			peloMenosUmParametroInformado = true;	

		}
		
		
		if (codigoMotivo != null && !codigoMotivo.equalsIgnoreCase("")){				

			FiltroNegativadorExclusaoMotivo filtroNegativadorExclMotivo = new FiltroNegativadorExclusaoMotivo();

			filtroNegativadorExclMotivo.adicionarParametro(new ParametroSimples(
					FiltroNegativadorExclusaoMotivo.CODIGO_EXCLUSAO_MOTIVO, codigoMotivo));
			
			Collection collNegativadorExclusaoMotivo= fachada.pesquisar(filtroNegativadorExclMotivo,NegativadorExclusaoMotivo.class.getName());
			
			
			if(Util.isVazioOrNulo(collNegativadorExclusaoMotivo)){		
				throw new ActionServletException("atencao.codigo_motivo_nao_existe_cadastro");
				
			}
			
			filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorExclusaoMotivo.CODIGO_EXCLUSAO_MOTIVO, codigoMotivo));
			peloMenosUmParametroInformado = true;	

		}

		if (descricaoExclusaoMotivo != null && !descricaoExclusaoMotivo.equalsIgnoreCase("")){
			filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorExclusaoMotivo.DESCRICAO_EXCLUSAO_MOTIVO, descricaoExclusaoMotivo));
			peloMenosUmParametroInformado = true;	

		}
		
		if (indicadorUso != null && !indicadorUso.equalsIgnoreCase("")){
			filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorExclusaoMotivo.INDICADOR_USO, indicadorUso));
			peloMenosUmParametroInformado = true;	

		}
		

		//[FS0003] Verificar preenchimento dos campos
		if (!peloMenosUmParametroInformado){
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		
        //Exibe na Tela o nome do Cliente Negativador
        FiltroNegativador filtroNegativador = new FiltroNegativador();
        filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.ID,idNegativador));
        
        filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");
        Collection collNegativador = fachada.pesquisar(filtroNegativador, Negativador.class.getName());
		
		
		// Recupera da coleção o Negativador que vai ser atualizado
		Negativador negativador = (Negativador) Util.retonarObjetoDeColecao(collNegativador);		
		
		if(negativador != null){		
	
			sessao.setAttribute("negativador", negativador);
			
		}
		
		sessao.setAttribute("filtroNegativadorExclusaoMotivo",filtroNegativadorExclusaoMotivo);
		sessao.setAttribute("indicadorAtualizar",indicadorAtualizar );
	
       return retorno;
    }
   
    

}
 