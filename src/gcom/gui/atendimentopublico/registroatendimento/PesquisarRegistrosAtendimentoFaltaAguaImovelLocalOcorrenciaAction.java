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

import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.ExibirRAFaltaAguaImovelHelper;
import gcom.atendimentopublico.registroatendimento.bean.RAFaltaAguaPendenteHelper;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.FiltroBairroArea;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela com os registros
 * de atendimento de falta de água no imóvel da mesma área do bairro (Aba nº 02 -
 * Dados do local de ocorrência)
 * 
 * @author Sávio Luiz
 * @date 17/07/2006
 */
public class PesquisarRegistrosAtendimentoFaltaAguaImovelLocalOcorrenciaAction
		extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("pesquisarRAsFaltaAguaOcorrencia");

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//PesquisarRegistrosAtendimentoFaltaAguaImovelLocalOcorrenciaActionForm form = 
		//(PesquisarRegistrosAtendimentoFaltaAguaImovelLocalOcorrenciaActionForm) actionForm;
		
		Integer idRegistroAtendimento = Util.converterStringParaInteger(httpServletRequest.getParameter("idRA"));
		Integer idBairroArea = Util.converterStringParaInteger(httpServletRequest.getParameter("idBairroArea"));
		Integer idEspecificacao = Util.converterStringParaInteger(httpServletRequest.getParameter("idEspecificacao"));

		RAFaltaAguaPendenteHelper rAFaltaAguaPendenteHelper = fachada
		.carregarObjetoRAFaltaAguaPendente(idRegistroAtendimento,
		idBairroArea, idEspecificacao);
		
		//Caso a chamada tenha sido feita pelo [UC0366] - Inserir Registro de Atendimento
		if (idRegistroAtendimento == null){
			
			//SolicitacaoTipo e SolicitacaoTipoEspecificacao
			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new 
			FiltroSolicitacaoTipoEspecificacao();
			
			filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipo");
			
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(
			FiltroSolicitacaoTipoEspecificacao.ID, idEspecificacao));
			
			Collection colecaoEspecificacao = fachada.pesquisar(filtroSolicitacaoTipoEspecificacao,
			SolicitacaoTipoEspecificacao.class.getName());
			
			SolicitacaoTipoEspecificacao especificacao = (SolicitacaoTipoEspecificacao) 
			Util.retonarObjetoDeColecao(colecaoEspecificacao);
			
			rAFaltaAguaPendenteHelper.setIdSolicitacaoTipo(especificacao.getSolicitacaoTipo().getId());
			rAFaltaAguaPendenteHelper.setDescricaoSolicitacaoTipo(especificacao.getSolicitacaoTipo().getDescricao());
			rAFaltaAguaPendenteHelper.setIdSolicitacaoTipoEspecificacao(especificacao.getId());
			rAFaltaAguaPendenteHelper.setDescricaoSolicitacaoTipoEspecificacao(especificacao.getDescricao());
			
			//Bairro e BairroArea
			FiltroBairroArea filtroBairroArea = new FiltroBairroArea();
			
			filtroBairroArea.adicionarCaminhoParaCarregamentoEntidade("bairro");
			
			filtroBairroArea.adicionarParametro(new ParametroSimples(
			FiltroBairroArea.ID, idBairroArea));
			
			Collection colecaoBairroArea = fachada.pesquisar(filtroBairroArea,
			BairroArea.class.getName());
			
			BairroArea bairroArea = (BairroArea) Util.retonarObjetoDeColecao(colecaoBairroArea);
		
			rAFaltaAguaPendenteHelper.setCodigoBairro(bairroArea.getBairro().getCodigo());
			rAFaltaAguaPendenteHelper.setNomeBairro(bairroArea.getBairro().getNome());
			rAFaltaAguaPendenteHelper.setIdBairroArea(bairroArea.getId());
			rAFaltaAguaPendenteHelper.setNomeBairroArea(bairroArea.getNome());
		}
		
		
		if (rAFaltaAguaPendenteHelper.getColecaoExibirRAFaltaAguaHelper() != null
				&& !rAFaltaAguaPendenteHelper
						.getColecaoExibirRAFaltaAguaHelper().isEmpty()) {
			Iterator iter = rAFaltaAguaPendenteHelper
					.getColecaoExibirRAFaltaAguaHelper().iterator();
			while (iter.hasNext()) {
				ExibirRAFaltaAguaImovelHelper exibirRAFaltaAguaImovelHelper = (ExibirRAFaltaAguaImovelHelper) iter
						.next();
				String enderecoOcorrencia = fachada
						.obterEnderecoOcorrenciaRA(exibirRAFaltaAguaImovelHelper
								.getIdRA());
				exibirRAFaltaAguaImovelHelper
						.setEnderecoOcorrencia(enderecoOcorrencia);
			}
		}
		
		

		sessao.setAttribute("rAFaltaAguaPendenteHelper",
				rAFaltaAguaPendenteHelper);

		return retorno;
	}

}
