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

import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.bean.ObterDescricaoSituacaoRAHelper;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ConsultarImovelRegistroAtendimentoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de inserir bairro
 * 
 * @author Thiago Tenório
 * @created 28 de Junho de 2004
 */
public class ExibirConsultarRegistroAtendimentoPendentesImovelAction extends
		GcomAction {
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

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("consultarRegistroAtendimentoPendentesImovel");
		ConsultarRegistroAtendimentoPendentesImovelActionForm atendimentoPendentesImovelActionForm = (ConsultarRegistroAtendimentoPendentesImovelActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Variavel responsavél pelo preenchimento do imovel no formulário
		/*String idOrdemServico = atendimentoPendentesImovelActionForm
				.getIdOrdemServico(); */

		String idImovel = httpServletRequest.getParameter("idImovel");
		String situacao = httpServletRequest.getParameter("situacao");
		
		sessao.removeAttribute("enderecoImovel");
		sessao.removeAttribute("colecaoConsultarImovelRegistroAtendimentoHelper");
		
		if (idImovel != null && !idImovel.trim().equals("")) {

			Imovel imovelSelecionado = fachada.pesquisarImovelRegistroAtendimento(Util.converterStringParaInteger(idImovel));
			
			if (imovelSelecionado != null){
				
				Collection colecaoConsultarImovelRegistroAtendimentoHelper  = null;
				Collection colecaoRegistroAtendimento = null;
				
				if (situacao != null && !situacao.equalsIgnoreCase("")){
					colecaoRegistroAtendimento = fachada.consultarRegistroAtendimentoImovel(new Integer(idImovel), 
					situacao);
				}
				else{
					colecaoRegistroAtendimento = fachada.consultarRegistroAtendimentoImovel(new Integer(idImovel), 
					null);
				}
				
				
				if(colecaoRegistroAtendimento != null && !colecaoRegistroAtendimento.isEmpty()){
			      
			      Iterator iteratorColecaoRegistroAtendimento = colecaoRegistroAtendimento.iterator();
			      
			      colecaoConsultarImovelRegistroAtendimentoHelper = new ArrayList();
			      
			      while (iteratorColecaoRegistroAtendimento.hasNext()) {
			    	  RegistroAtendimento registroAtendimento = (RegistroAtendimento) iteratorColecaoRegistroAtendimento.next();
			       
			    	  ConsultarImovelRegistroAtendimentoHelper consultarImovelRegistroAtendimentoHelper = new ConsultarImovelRegistroAtendimentoHelper();

				       //id registro atendimento
				       if(registroAtendimento != null  && registroAtendimento.getId() != null ){
				        consultarImovelRegistroAtendimentoHelper.setIdRegistroAtendimento(registroAtendimento.getId().toString());
				       }
				       
				       //tipo de solicitação
				       if(registroAtendimento != null && registroAtendimento.getSolicitacaoTipoEspecificacao() != null 
				         && registroAtendimento.getSolicitacaoTipoEspecificacao().getSolicitacaoTipo() != null){
				        
				        consultarImovelRegistroAtendimentoHelper.setTipoSolicitacao(registroAtendimento.getSolicitacaoTipoEspecificacao().getSolicitacaoTipo().getDescricao());
				       }
				       
				       //especificação
				       if(registroAtendimento != null && registroAtendimento.getSolicitacaoTipoEspecificacao() != null){
				        consultarImovelRegistroAtendimentoHelper.setEspecificacao(registroAtendimento.getSolicitacaoTipoEspecificacao().getDescricao());
				       }
				       
				       //data de atendimento
				       if(registroAtendimento != null && registroAtendimento.getRegistroAtendimento() != null ){
				        consultarImovelRegistroAtendimentoHelper.setDataAtendimento(Util.formatarData(registroAtendimento.getRegistroAtendimento()));
				       }
				       
				       //situacao
				       if(registroAtendimento != null && registroAtendimento.getId() != null){
				        ObterDescricaoSituacaoRAHelper obterDescricaoSituacaoRAHelper = 
				         fachada.obterDescricaoSituacaoRA(registroAtendimento.getId());
				        consultarImovelRegistroAtendimentoHelper.setSituacao(obterDescricaoSituacaoRAHelper.getDescricaoSituacao());
				        
				       }
				       
				       colecaoConsultarImovelRegistroAtendimentoHelper.add(consultarImovelRegistroAtendimentoHelper);
			      	}
			      
			      sessao.setAttribute("colecaoConsultarImovelRegistroAtendimentoHelper",colecaoConsultarImovelRegistroAtendimentoHelper);
				}
				else{
					throw new ActionServletException("atencao.imovel_sem_ra_pendente");
				}
				
				atendimentoPendentesImovelActionForm.setMatriculaImovel(imovelSelecionado.getId().toString());
				atendimentoPendentesImovelActionForm.setInscricaoImovel(imovelSelecionado.getInscricaoFormatada());
				atendimentoPendentesImovelActionForm.setSituacaoLigacaoAgua(imovelSelecionado.getLigacaoAguaSituacao().getDescricao());
				atendimentoPendentesImovelActionForm.setSituacaoLigacaoEsgoto(imovelSelecionado.getLigacaoEsgotoSituacao().getDescricao());
			
				httpServletRequest.setAttribute("enderecoImovel", imovelSelecionado.getEnderecoFormatado());
			}
		}
			
			
		return retorno;
	}
}