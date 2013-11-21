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
package gcom.gui.atendimentopublico.registroatendimento;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoAnexo;
import gcom.gui.GcomAction;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os parâmetros para realização
 * da atualização da observação de um arquivo anexo ao RA (Aba nº 04 - Anexos) 
 *
 * @author Raphael Rossiter
 * @date 06/08/2009
 */
public class ExibirAtualizarRegistroAtendimentoObservacaoArquivoAnexoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("atualizarRegistroAtendimentoObservacaoArquivoAnexoAction");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        AtualizarRegistroAtendimentoObservacaoArquivoAnexoActionForm form = 
        (AtualizarRegistroAtendimentoObservacaoArquivoAnexoActionForm) actionForm;
        
        String idRegistroAtendimentoAnexo = httpServletRequest.getParameter("idRegistroAtendimentoAnexo");
        String acaoAtualizar = httpServletRequest.getParameter("acaoAtualizar");
        
        RegistroAtendimentoAnexo registroAtendimentoAnexo = this.obterArquivoParaVisualizacao(
        idRegistroAtendimentoAnexo, sessao);
        
        if (registroAtendimentoAnexo != null){
        	
        	form.setIdRegistroAtendimentoAnexo(String.valueOf(GcomAction.obterTimestampIdObjeto(registroAtendimentoAnexo)));
        	form.setObservacaoAnexoAtualizacao(registroAtendimentoAnexo.getDescricaoDocumento());
        }
        
        //ATUALIZANDO OBSERVAÇÃO
        this.atualizarObservacao(form.getIdRegistroAtendimentoAnexo(), sessao, acaoAtualizar, 
        form.getObservacaoAnexoAtualizacao(), httpServletRequest);
        
        //MONTANDO URL DE RETORNO
		if (httpServletRequest.getParameter("telaRetorno") != null){
    		sessao.setAttribute("telaRetorno", 
    		(String.valueOf(httpServletRequest.getParameter("telaRetorno"))) + ".do");
    	}
        
        httpServletRequest.setAttribute("nomeCampo", "observacaoAnexoAtualizacao");
        
        return retorno;
	}
	
	/**
	 * Obter arquivo para atualização da observação
	 * 
	 * @author Raphael Rossiter
	 * @date 06/08/2009
	 * 
	 * @param String
	 * @param HttpSession
	 */
	private RegistroAtendimentoAnexo obterArquivoParaVisualizacao(String identificacao, HttpSession sessao){
		
		RegistroAtendimentoAnexo registroAtendimentoAnexo = null;
		
		if (identificacao != null && !identificacao.equals("")){
			
			Collection colecaoRegistroAtendimentoAnexo = (Collection) 
			sessao.getAttribute("colecaoRegistroAtendimentoAnexo");
			
			Iterator it = colecaoRegistroAtendimentoAnexo.iterator();
			RegistroAtendimentoAnexo anexoColecao = null;
			
			while (it.hasNext()){
				
				anexoColecao = (RegistroAtendimentoAnexo) it.next();
				
				if (obterTimestampIdObjeto(anexoColecao) == Long.parseLong(identificacao)){
					registroAtendimentoAnexo = anexoColecao;
					break;
				}
			}
		}
		
		return registroAtendimentoAnexo;
	}
	
	/**
	 * Atualizar observação
	 * 
	 * @author Raphael Rossiter
	 * @date 06/08/2009
	 * 
	 * @param String
	 * @param HttpSession
	 */
	private void atualizarObservacao(String identificacao, HttpSession sessao,
			String acaoAtualizar, String observacao, HttpServletRequest httpServletRequest){
		
		if (acaoAtualizar != null && !acaoAtualizar.equals("")){
			
			Collection colecaoRegistroAtendimentoAnexo = (Collection) 
			sessao.getAttribute("colecaoRegistroAtendimentoAnexo");
			
			Iterator it = colecaoRegistroAtendimentoAnexo.iterator();
			RegistroAtendimentoAnexo anexoColecao = null;
			
			while (it.hasNext()){
				
				anexoColecao = (RegistroAtendimentoAnexo) it.next();
				
				if (obterTimestampIdObjeto(anexoColecao) == Long.parseLong(identificacao)){
					
					anexoColecao.setDescricaoDocumento(observacao);
					break;
				}
			}
			
			httpServletRequest.setAttribute("reloadPage", "OK");
		}
	}
}
