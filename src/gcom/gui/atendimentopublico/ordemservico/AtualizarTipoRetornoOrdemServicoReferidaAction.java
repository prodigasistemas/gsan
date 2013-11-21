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
package gcom.gui.atendimentopublico.ordemservico;

import java.util.Collection;

import gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarTipoRetornoOrdemServicoReferidaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarTipoRetornoOrdemServicoReferidaActionForm atualizarTipoRetornoOrdemServicoReferidaActionForm = (AtualizarTipoRetornoOrdemServicoReferidaActionForm) actionForm;

		OsReferidaRetornoTipo osReferidaRetornoTipo = (OsReferidaRetornoTipo) sessao
				.getAttribute("osReferidaRetornoTipoAtualizar");

		osReferidaRetornoTipo.setId(new Integer(
				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getCodigoTipoRetorno()));
		osReferidaRetornoTipo
				.setDescricao(atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getDescricao());
		osReferidaRetornoTipo
				.setDescricaoAbreviada(atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getAbreviatura());

		ServicoTipoReferencia servicoTipoReferencia = null;

		if (atualizarTipoRetornoOrdemServicoReferidaActionForm
				.getServicoTipoReferencia() != null
				&& !atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getServicoTipoReferencia().equals("")
				&& !atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getServicoTipoReferencia().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			servicoTipoReferencia = new ServicoTipoReferencia();
			servicoTipoReferencia.setId(new Integer(
					atualizarTipoRetornoOrdemServicoReferidaActionForm
							.getServicoTipoReferencia()));

		}

		osReferidaRetornoTipo.setServicoTipoReferencia(servicoTipoReferencia);
		osReferidaRetornoTipo.setIndicadorDeferimento(new Short(
				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getDeferimento()));
		osReferidaRetornoTipo.setIndicadorTrocaServico(new Short(
				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getTrocaServico()));
		if (atualizarTipoRetornoOrdemServicoReferidaActionForm.getSituacao() != null
				&& !atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getSituacao().equals("")) {
			osReferidaRetornoTipo.setSituacaoOsReferencia(new Short(
					atualizarTipoRetornoOrdemServicoReferidaActionForm
							.getSituacao()));
		} else {
			osReferidaRetornoTipo.setSituacaoOsReferencia(null);
		}

		osReferidaRetornoTipo.setIndicadorUso(new Short(
				atualizarTipoRetornoOrdemServicoReferidaActionForm
						.getIndicadorUso()));

		//AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = null;

		if (atualizarTipoRetornoOrdemServicoReferidaActionForm
				.getAtendimentoMotivoEncerramento() != null) {

			Integer idAtendimentoMotivoEncerramento = new Integer(
					atualizarTipoRetornoOrdemServicoReferidaActionForm
							.getAtendimentoMotivoEncerramento());

			if (idAtendimentoMotivoEncerramento
					.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				osReferidaRetornoTipo.setAtendimentoMotivoEncerramento(null);
			} else {
				FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
				filtroAtendimentoMotivoEncerramento
						.adicionarParametro(new ParametroSimples(
								FiltroAtendimentoMotivoEncerramento.ID,
								atualizarTipoRetornoOrdemServicoReferidaActionForm
										.getAtendimentoMotivoEncerramento()
										.toString()));
				Collection colecaoAtendimentoMotivoEncerramento = (Collection) fachada
						.pesquisar(filtroAtendimentoMotivoEncerramento,
								AtendimentoMotivoEncerramento.class.getName());

				// setando
				osReferidaRetornoTipo
						.setAtendimentoMotivoEncerramento((AtendimentoMotivoEncerramento) colecaoAtendimentoMotivoEncerramento
								.iterator().next());
			}
		}

		fachada.atualizarTipoRetornoOrdemServicoReferida(osReferidaRetornoTipo);

		montarPaginaSucesso(httpServletRequest, "Tipo Retorno da OS_Referida "
				+ osReferidaRetornoTipo.getId().toString()
				+ " atualizado com sucesso.",
				"Realizar outra Manutenção de Tipo Retorno da OS_Referida",
				"exibirFiltrarTipoRetornoOrdemServicoReferidaAction.do?menu=sim");
		return retorno;
	}
}
