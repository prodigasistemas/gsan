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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

/**
 * Esta classe tem por finalidade validar as informações da primeira aba do
 * processo de inserção de um registro de atendimento
 * 
 * @author Sávio Luiz
 * @date 10/08/2006
 */
public class AtualizarRegistroAtendimentoDadosGeraisAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("");

		AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm = (AtualizarRegistroAtendimentoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
		filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID, atualizarRegistroAtendimentoActionForm.getEspecificacao()));
		filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.INDICADOR_ENCERRAMENTO_AUTOMATICO, SolicitacaoTipoEspecificacao.INDICADOR_COM_ENCERRAMENTO_AUTOMATICO));
		
		Collection colecaoSolicitacaoTipoEspecificacao = fachada
				.pesquisar(filtroSolicitacaoTipoEspecificacao,
						SolicitacaoTipoEspecificacao.class.getName());
		
		// Caso a especificação seja de encerramento automático, verifica se a observação foi informada
		if (colecaoSolicitacaoTipoEspecificacao != null && !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
			if (atualizarRegistroAtendimentoActionForm.getObservacao() == null || atualizarRegistroAtendimentoActionForm.getObservacao().trim().equals("")) {
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Observação");
			}
		}
		
		if(atualizarRegistroAtendimentoActionForm.getObservacao() != null && 
			!atualizarRegistroAtendimentoActionForm.getObservacao().equals("") && 
			atualizarRegistroAtendimentoActionForm.getObservacao().length() > 400){
				
			String[] msg = new String[2];
			msg[0]="Observação";
			msg[1]="400";
				
			throw new ActionServletException("atencao.execedeu_limit_observacao",null,msg);
		}		

		fachada.validarInserirRegistroAtendimentoDadosGerais(
				atualizarRegistroAtendimentoActionForm.getDataAtendimento(),
				atualizarRegistroAtendimentoActionForm.getHora(),
				atualizarRegistroAtendimentoActionForm.getTempoEsperaInicial(),
				atualizarRegistroAtendimentoActionForm.getTempoEsperaFinal(),
				atualizarRegistroAtendimentoActionForm.getUnidade(),
				null);

		String idUnidade = atualizarRegistroAtendimentoActionForm.getUnidade();
		String descricaoUnidade = atualizarRegistroAtendimentoActionForm
				.getDescricaoUnidade();

		if (idUnidade != null && !idUnidade.equalsIgnoreCase("")
				&& (descricaoUnidade == null || descricaoUnidade.equals(""))) {

			UnidadeOrganizacional unidadeOrganizacionalSelecionada = fachada
					.verificarAutorizacaoUnidadeAberturaRA(new Integer(
							idUnidade), false);

			if (unidadeOrganizacionalSelecionada != null) {
				atualizarRegistroAtendimentoActionForm
						.setUnidade(unidadeOrganizacionalSelecionada.getId()
								.toString());
				atualizarRegistroAtendimentoActionForm
						.setDescricaoUnidade(unidadeOrganizacionalSelecionada
								.getDescricao());

				if (unidadeOrganizacionalSelecionada.getMeioSolicitacao() != null) {

					atualizarRegistroAtendimentoActionForm
							.setMeioSolicitacao(unidadeOrganizacionalSelecionada
									.getMeioSolicitacao().getId().toString());
				}

			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Unidade de Atendimento");
			}

		}

		return retorno;
	}

}
