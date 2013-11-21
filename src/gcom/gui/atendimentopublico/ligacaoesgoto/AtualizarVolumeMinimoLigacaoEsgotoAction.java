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
package gcom.gui.atendimentopublico.ligacaoesgoto;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0464] Atualizar Volume Mínimo da Ligação de Esgoto
 * 
 * Apresentação da atualização de volume mínimo de ligação de esgoto
 * 
 * @author Leonardo Regis
 * @date 22/09/2006
 */
public class AtualizarVolumeMinimoLigacaoEsgotoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		AtualizarVolumeMinimoLigacaoEsgotoActionForm form = (AtualizarVolumeMinimoLigacaoEsgotoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// Imovel
		Imovel imovel = new Imovel();
		imovel.setId(new Integer(form.getMatriculaImovel()));
		ConsumoTarifa consumoTarifa = new ConsumoTarifa();
		consumoTarifa.setId(new Integer(form.getConsumoTarifaId()));
		imovel.setConsumoTarifa(consumoTarifa);
		// Ligação Esgoto
		LigacaoEsgoto ligacaoEsgoto = new LigacaoEsgoto();
		ligacaoEsgoto.setId(imovel.getId());
		
		if (form.getConsumoMinimoFixado() != null
				&& !form.getConsumoMinimoFixado().trim().equals("")) {
			ligacaoEsgoto.setConsumoMinimo(new Integer(form
					.getConsumoMinimoFixado()));
		}
		
		ligacaoEsgoto.setUltimaAlteracao(form.getDataConcorrencia());
		imovel.setLigacaoEsgoto(ligacaoEsgoto);
		
		// [FS0004] Validar Volume Mínimo
		if (form.getConsumoMinimoFixado() != null
				&& !form.getConsumoMinimoFixado().trim().equals("")) {
			fachada.validarVolumeMinimoLigacaoEsgoto(imovel);
		}
		
		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

		integracaoComercialHelper.setLigacaoEsgoto(ligacaoEsgoto);
		integracaoComercialHelper.setUsuarioLogado(usuario);

		if (form.getVeioEncerrarOS().equalsIgnoreCase("FALSE")) {
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);

			// Efetuando Atualização volume mínimo da Ligação de Esgoto
			fachada.atualizarVolumeMinimoLigacaoEsgoto(integracaoComercialHelper);
		} else {
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.TRUE);
			sessao.setAttribute("integracaoComercialHelper",
					integracaoComercialHelper);

			if (sessao.getAttribute("semMenu") == null) {
				retorno = actionMapping
						.findForward("encerrarOrdemServicoAction");
			} else {
				retorno = actionMapping
						.findForward("encerrarOrdemServicoPopupAction");
			}
			sessao.removeAttribute("caminhoRetornoIntegracaoComercial");
		}
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			// Monta a página de sucesso
			montarPaginaSucesso(
					httpServletRequest,
					"Atualização do Volume Mínimo da Ligação de Esgoto "
							+ ligacaoEsgoto.getId() + " efetuada com Sucesso",
					"Atualizar o Volume Mínimo de outra Ligação de Esgoto",
					"exibirAtualizarVolumeMinimoLigacaoEsgotoAction.do?menu=sim",
					"exibirAtualizarVolumeMinimoLigacaoEsgotoAction.do?idOrdemServico="
							+ form.getIdOrdemServico(),
					"Atualizar o Volume Mínimo da Ligação de Esgoto alterada");
		}

		return retorno;
	}
}