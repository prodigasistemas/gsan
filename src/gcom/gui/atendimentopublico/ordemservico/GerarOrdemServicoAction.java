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

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <<Descrição da Classe>>
 * 
 * @author lms
 * @date 22/08/2006
 */
public class GerarOrdemServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		GerarOrdemServicoActionForm form = (GerarOrdemServicoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		OrdemServico ordemServico = (OrdemServico) sessao
				.getAttribute("ordemServico");

		// Inicio Alterado por Sàvio Luiz

		if (form.getValorServicoOriginal() == null
				|| form.getValorServicoOriginal().equals("")) {
			Integer idRA = Integer.valueOf(form.getIdRegistroAtendimento());
			RegistroAtendimento ra = fachada.validarRegistroAtendimento(idRA);
			// Serviço Tipo
			ServicoTipo servicoTipo = null;

			Integer idServicoTipo = Util.converterStringParaInteger(form
					.getIdServicoTipo());
			String descricaoServicoTipo = null;
			String valorServicoOriginal = null;
			Integer idServicoTipoPrioridadeOriginal = null;
			String descricaoServicoTipoPrioridadeOriginal = null;

			if (Util.validarNumeroMaiorQueZERO(idServicoTipo)) {
				servicoTipo = fachada.pesquisarSevicoTipo(idServicoTipo);

				fachada.validarServicoTipo(ra.getId(), idServicoTipo);

				if (servicoTipo != null) {
					descricaoServicoTipo = servicoTipo.getDescricao();
					if (servicoTipo.getValor() != null) {
						String valorFormatado = servicoTipo.getValor()
								.toString().replace('.', ',');
						valorServicoOriginal = valorFormatado;
					}
					if (servicoTipo.getServicoTipoPrioridade() != null) {
						idServicoTipoPrioridadeOriginal = servicoTipo
								.getServicoTipoPrioridade().getId();
						descricaoServicoTipoPrioridadeOriginal = servicoTipo
								.getServicoTipoPrioridade().getDescricao();
					}
					httpServletRequest.setAttribute("idServicoTipoEncontrada",
							"true");
				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Tipo de Serviço");
				}
				form.getOrdemServico().setServicoTipo(servicoTipo);
			}

			form.setDescricaoServicoTipo(descricaoServicoTipo);
			form.setValorServicoOriginal(valorServicoOriginal);
			form.setIdPrioridadeServicoOriginal(idServicoTipoPrioridadeOriginal
					+ "");
			form
					.setDescricaoPrioridadeServicoOriginal(descricaoServicoTipoPrioridadeOriginal);
		}

		// Fim Alterado por Sàvio Luiz

		if (ordemServico != null) {
			ordemServico = form.setFormValues(form.getOrdemServico());
		} else {
			ordemServico = form.setFormValues(form.getOrdemServico());
		}
		
		if(ordemServico.getServicoTipo()!=null 
				&& ordemServico.getRegistroAtendimento() != null){
			fachada.validarServicoTipo(ordemServico.getRegistroAtendimento().getId(),ordemServico.getServicoTipo().getId());
		}
		
		String veioAcompanhamento = (String) sessao
				.getAttribute("veioAcompanhamento");
		
		String veioAcompanhamentoRoteiro = (String) sessao
				.getAttribute("veioAcompanhamentoRoteiro");
		
		if (veioAcompanhamentoRoteiro != null && !veioAcompanhamentoRoteiro.equals("")){
			if (ordemServico.getServicoTipo() != null &&
					ordemServico.getServicoTipo().getIndicadorProgramacaoAutomatica() != ConstantesSistema.SIM.shortValue()){
				throw new ActionServletException("atencao.servico_tipo.nao_compativel");
			}
		}
		
		// gera a ordem de serviço
		Integer idOrdemServico = fachada.gerarOrdemServico(ordemServico,
				usuario, null);

		sessao.removeAttribute("ordemServico");
		
		if (veioAcompanhamentoRoteiro != null && !veioAcompanhamentoRoteiro.equals("")){
			retorno = actionMapping
					.findForward("incluirOrdemServicoAcompanhamentoArqRoteiro");

			httpServletRequest.setAttribute("objetoConsulta", "");
			httpServletRequest.setAttribute("idOrdemServico", "" + idOrdemServico);
			httpServletRequest.setAttribute("descOrdemServico", "" + ordemServico.getServicoTipo().getDescricao());
			
			httpServletRequest.setAttribute("numeroRa", ordemServico.getRegistroAtendimento().getId());

		} else if (veioAcompanhamento != null) {

			retorno = actionMapping
					.findForward("incluirOrdemServicoAcompanharRoteiroProgramacao");

			httpServletRequest.setAttribute("objetoConsulta", "2");
			httpServletRequest.setAttribute("idOrdemServico", ""
					+ idOrdemServico);
		}else{

			String caminhoRetornoGerarOs = (String) sessao
					.getAttribute("caminhoRetornoGerarOs");

			if (caminhoRetornoGerarOs != null
					&& caminhoRetornoGerarOs
							.equals("exibirConsultarRegistroAtendimentoAction.do")) {

				String idRa = ordemServico.getRegistroAtendimento().getId()
						.toString();
				caminhoRetornoGerarOs = caminhoRetornoGerarOs + "?numeroRA="
						+ idRa;
			}

			String msg = "Geração da Ordem de Serviço " + ordemServico.getId()
					+ " para o registro de Atendimento número "
					+ ordemServico.getRegistroAtendimento().getId()
					+ " efetuada com sucesso.";

			if (caminhoRetornoGerarOs == null) {
				montarPaginaSucessoUmRelatorio(httpServletRequest, msg,"",
	                    "","exibirGerarOrdemServicoAction.do","Voltar",
						"Imprimir OS",
						"gerarRelatorioOrdemServicoAction.do?idsOS=" + idOrdemServico);

			} else {	
				montarPaginaSucessoUmRelatorio(httpServletRequest, msg,"",
	                    "",caminhoRetornoGerarOs,"Voltar",
						"Imprimir OS",
						"gerarRelatorioOrdemServicoAction.do?idsOS=" + idOrdemServico);
				
			}
		}
		return retorno;
	}

}