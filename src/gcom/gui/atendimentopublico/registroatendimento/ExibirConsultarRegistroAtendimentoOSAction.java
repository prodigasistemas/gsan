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

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.ObterDescricaoSituacaoRAHelper;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * Action que define o pré-processamento da página de consulta de os do ra.
 * 
 * @author Leonardo Regis
 * @created 11/08/2006
 */
public class ExibirConsultarRegistroAtendimentoOSAction extends GcomAction {
	/**
	 * Execute do Exibir Consultar RA OS do Popup
	 *
	 * @author Leonardo Regis
	 * @date 11/08/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("consultarRegistroAtendimentoOS");
		
		Fachada fachada = Fachada.getInstancia();
		
		ConsultarRegistroAtendimentoOSActionForm consultarRegistroAtendimentoOS = 
				(ConsultarRegistroAtendimentoOSActionForm) actionForm;
		
		RegistroAtendimento registroAtendimento = 
			pesquisarRegistroAtendimento(new Integer(consultarRegistroAtendimentoOS.getNumeroRA()));
		
		consultarRegistroAtendimentoOS.setNumeroRA(""+registroAtendimento.getId());
		
		ObterDescricaoSituacaoRAHelper situacaoRA = 
			fachada.obterDescricaoSituacaoRA(registroAtendimento.getId());
		consultarRegistroAtendimentoOS.setSituacaoRA(situacaoRA.getDescricaoSituacao());		
		
		SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = 
			registroAtendimento.getSolicitacaoTipoEspecificacao();
		if(solicitacaoTipoEspecificacao != null){
			if(solicitacaoTipoEspecificacao.getSolicitacaoTipo() != null){
				consultarRegistroAtendimentoOS.setTipoSolicitacao(solicitacaoTipoEspecificacao.getSolicitacaoTipo().getDescricao());	
				consultarRegistroAtendimentoOS.setTipoSolicitacaoId(solicitacaoTipoEspecificacao.getSolicitacaoTipo().getId()+"");
			}
			consultarRegistroAtendimentoOS.setEspecificacao(solicitacaoTipoEspecificacao.getDescricao());
			consultarRegistroAtendimentoOS.setEspecificacaoId(solicitacaoTipoEspecificacao.getId()+"");
		}

		UnidadeOrganizacional unidadeAtual = fachada.obterUnidadeAtualRA(registroAtendimento.getId());
		if(unidadeAtual != null){
			consultarRegistroAtendimentoOS.setIdUnidadeAtual(""+unidadeAtual.getId());
			consultarRegistroAtendimentoOS.setUnidadeAtual(unidadeAtual.getDescricao());
		}
		
		//OS's do RA
		Collection<OrdemServico> colecaoOS = fachada.obterOSRA(registroAtendimento.getId());
		if(colecaoOS != null &&
				!colecaoOS.isEmpty()) {
			consultarRegistroAtendimentoOS.setColecaoOS(colecaoOS);
		} else {
			throw new ActionServletException("atencao.consultar_os_consulta_vazia"); 
		}
		
        
        // o botão encerrar so aparece no popup de consultar ordem de serviço 
        // qd for chamada a partir do manter RA
        HttpSession sessao = httpServletRequest.getSession(false);
        if (httpServletRequest.getParameter("botaoEncerraOs") != null){
            sessao.setAttribute("botaoEncerraOs",httpServletRequest.getParameter("botaoEncerraOs"));
        }
        
        
		return retorno;
	}

	/**
	 * Consulta o registro atendimento pelo id informado
	 * 
	 * @author Leonardo Regis
	 * @created 11/08/2006
	 */
	private RegistroAtendimento pesquisarRegistroAtendimento(Integer id){

		RegistroAtendimento retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		Collection colecaoRegistroAtendimento = null; 

		FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();

		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(
				FiltroRegistroAtendimento.ID,id));
		
		filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoEspecificacao");
		filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoEspecificacao.solicitacaoTipo");

		colecaoRegistroAtendimento = fachada.pesquisar(filtroRegistroAtendimento, 
			RegistroAtendimento.class.getName());

		if (colecaoRegistroAtendimento != null && !colecaoRegistroAtendimento.isEmpty()) {
			retorno = (RegistroAtendimento) Util.retonarObjetoDeColecao(colecaoRegistroAtendimento);
		} else {
			throw new ActionServletException("atencao.naocadastrado",null, "Registro Atendimento");
		}
		
		return retorno;
	}
}