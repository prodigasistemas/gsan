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
package gcom.gui.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
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
 * [UC0463] Atualizar Consumo Mínimo da Ligação de Água
 * 
 * Apresentação da atualização de consumo mínimo de ligação de água
 * 
 * @author Leonardo Regis
 * @date 30/08/2006
 */
public class AtualizarConsumoMinimoLigacaoAguaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		AtualizarConsumoMinimoLigacaoAguaActionForm atualizarConsumoMinimoLigacaoAguaActionForm = (AtualizarConsumoMinimoLigacaoAguaActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		// Imovel
		Imovel imovel = new Imovel();
		imovel.setId(new Integer(atualizarConsumoMinimoLigacaoAguaActionForm
				.getMatriculaImovel()));
		ConsumoTarifa consumoTarifa = new ConsumoTarifa();
		consumoTarifa.setId(new Integer(
				atualizarConsumoMinimoLigacaoAguaActionForm
						.getConsumoTarifaId()));
		imovel.setConsumoTarifa(consumoTarifa);
		// Ligação Água
		LigacaoAgua ligacaoAgua = new LigacaoAgua();
		ligacaoAgua.setId(imovel.getId());
		
		if (atualizarConsumoMinimoLigacaoAguaActionForm
				.getConsumoMinimoFixado() != null && !atualizarConsumoMinimoLigacaoAguaActionForm
				.getConsumoMinimoFixado().trim().equals("")) {
			ligacaoAgua.setNumeroConsumoMinimoAgua(new Integer(
				atualizarConsumoMinimoLigacaoAguaActionForm
						.getConsumoMinimoFixado()));
		}
		
		ligacaoAgua
				.setUltimaAlteracao(atualizarConsumoMinimoLigacaoAguaActionForm
						.getDataConcorrencia());
		imovel.setLigacaoAgua(ligacaoAgua);
		// [FS0004] Validar Consumo Mínimo
		if (atualizarConsumoMinimoLigacaoAguaActionForm
				.getConsumoMinimoFixado() != null && !atualizarConsumoMinimoLigacaoAguaActionForm
				.getConsumoMinimoFixado().trim().equals("")) {
			fachada.validarConsumoMinimoLigacaoAgua(imovel);
		}

		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

		integracaoComercialHelper.setLigacaoAgua(ligacaoAgua);
		integracaoComercialHelper.setUsuarioLogado(usuario);
		
		if (atualizarConsumoMinimoLigacaoAguaActionForm.getVeioEncerrarOS()
				.equalsIgnoreCase("FALSE")) {
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);
			
			// Efetuando Atualização volume mínimo da Ligação de Água
			fachada.atualizarConsumoMinimoLigacaoAgua(integracaoComercialHelper);

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
					"Atualização do Consumo Mínimo da Ligação de Água "
							+ ligacaoAgua.getId() + " efetuada com Sucesso",
					"Atualizar o Consumo Mínimo de outra Ligação de Água",
					"exibirAtualizarConsumoMinimoLigacaoAguaAction.do?menu=sim",
					"exibirAtualizarConsumoMinimoLigacaoAguaAction.do?idOrdemServico="
							+ atualizarConsumoMinimoLigacaoAguaActionForm
									.getIdOrdemServico(),
					"Atualizar o Consumo Mínimo da Ligação de Água alterada");
		}

		return retorno;
	}
}