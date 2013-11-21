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
public class AtualizarOrdemServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		AtualizarOrdemServicoActionForm form = (AtualizarOrdemServicoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		OrdemServico ordemServico = form.setFormValues(form.getOrdemServico());

		// pesquisa do enter serviço tipo
		String idServicoTipo = form.getIdServicoTipo();
		String descricaoServicoTipo = form.getDescricaoServicoTipo();
		
		if (idServicoTipo != null
				&& !idServicoTipo.equals("")
				&& (descricaoServicoTipo == null || descricaoServicoTipo
						.equals(""))) {
			Integer idServicoTipoInteger = Util.converterStringParaInteger(form
					.getIdServicoTipo());

			ServicoTipo servicoTipo = fachada
					.pesquisarSevicoTipo(idServicoTipoInteger);

			if (servicoTipo != null && !servicoTipo.equals("")) {
				ordemServico.setServicoTipo(servicoTipo);

			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Tipo Serviço");
			}
		}
		
		if(ordemServico.getObservacao() != null && 
			!ordemServico.getObservacao().equals("") && 
			ordemServico.getObservacao().length() > 200){
					
			String[] msg = new String[2];
			msg[0]="Observação";
			msg[1]="200";
				
			throw new ActionServletException("atencao.execedeu_limit_observacao",null,msg);
		}					

		// pesquisa do enter Ordem de Serviço Referência
		String idOSReferencia = form.getIdOrdemServicoReferencia();
		String descricaoOsReferencia = form
				.getDescricaoOrdemServicoReferencia();
		if (idOSReferencia != null
				&& !idOSReferencia.equals("")
				&& (descricaoOsReferencia == null || descricaoOsReferencia
						.equals(""))) {
			Integer idOrdemServicoReferencia = Util
					.converterStringParaInteger(form
							.getIdOrdemServicoReferencia());

			OrdemServico os = fachada
					.pesquisarOrdemServico(idOrdemServicoReferencia);

			if (os != null && !os.equals("")) {
				ordemServico.setOsReferencia(os);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Ordem Serviço Referência");

			}
		}

		// pesquisa do enter Serviço Tipo Referência
		String idServicoTipoReferencia = form.getIdServicoTipoReferencia();
		String descricaoServicoTipoReferencia = form
				.getDescricaoServicoTipoReferencia();
		if (idServicoTipoReferencia != null
				&& !idServicoTipoReferencia.equals("")
				&& (descricaoServicoTipoReferencia == null || descricaoServicoTipoReferencia
						.equals(""))) {

			Integer idTipoServicoReferenciaInteger = Util
					.converterStringParaInteger(form
							.getIdServicoTipoReferencia());

			ServicoTipo st = fachada
					.pesquisarSevicoTipo(idTipoServicoReferenciaInteger);
			if (st != null && !st.equals("")) {
				ordemServico.setServicoTipoReferencia(st);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Serviço Tipo Referência");
			}
		}

		String veioAcompanhamentoRoteiro = (String) sessao
				.getAttribute("veioAcompanhamentoRoteiro");		
		
		if (veioAcompanhamentoRoteiro != null && !veioAcompanhamentoRoteiro.equals("")){
			if (ordemServico.getServicoTipo() != null &&
					ordemServico.getServicoTipo().getIndicadorProgramacaoAutomatica() != ConstantesSistema.SIM.shortValue()){
				throw new ActionServletException("atencao.servico_tipo.nao_compativel.atualizar_os");
			}
		}
		
		// gera a ordem de serviço
		fachada.atualizarOrdemServico(ordemServico, usuarioLogado);

		if (sessao.getAttribute("ehPopup") == null
				|| sessao.getAttribute("ehPopup").equals("")) {
			// Exibe a página de sucesso
			String mensagem = "";
			if(ordemServico.getRegistroAtendimento() != null && ordemServico.getRegistroAtendimento().getId() != null ){
				mensagem = "Ordem de Serviço "
					+ ordemServico.getId()
					+ " para o registro de Atendimento número "
					+ ordemServico.getRegistroAtendimento().getId()
					+ " atualizada com sucesso.";
			}else if (ordemServico.getCobrancaDocumento() != null && ordemServico.getCobrancaDocumento().getId() != null ){
				mensagem = "Ordem de Serviço "
					+ ordemServico.getId()
					+ " para o Documento de Cobrança número "
					+ ordemServico.getCobrancaDocumento().getId()
					+ " atualizada com sucesso.";
			} else {
				mensagem = "Ordem de Serviço "
					+ ordemServico.getId()
					+ " atualizada com sucesso.";
			}
			
			if (sessao.getAttribute("importarMovimentoACQUAGIS") != null &&
					sessao.getAttribute("importarMovimentoACQUAGIS").equals("sim")) {
				
				montarPaginaSucesso(httpServletRequest,  mensagem,
						"Voltar",
						sessao.getAttribute("caminhoRetornoOS").toString());
				
			}else {
				montarPaginaSucesso(httpServletRequest,  mensagem, "Realizar outra Manutenção de Ordem de Serviço",
						"exibirFiltrarOrdemServicoAction.do?menu=sim",
						"exibirConsultarDadosOrdemServicoAction.do?menu=sim&numeroOS=" + ordemServico.getId(), 
						"Voltar");
			}
			
		} else {
			// volta para tela e limpa o popup
			httpServletRequest.setAttribute("fecharPopup", "SIM");
			retorno = actionMapping.findForward("exibirAtualizarOrdemServico");
		}
		
		String dataProgramacao = (String) sessao.getAttribute("dataProgramacaoAtualizar");
		String chaveArquivo = (String) sessao.getAttribute("chaveArquivoAtualizar");
		
		if (veioAcompanhamentoRoteiro != null && !veioAcompanhamentoRoteiro.equals("")){
			fachada.atualizarOSProgramacaoAcompServico(Util.converterStringParaInteger(chaveArquivo), Util.converteStringParaDate(dataProgramacao), ordemServico.getId(),
					ordemServico.getServicoTipo().getId());
		}
		
		sessao.removeAttribute("dataProgramacaoAtualizar");
		sessao.removeAttribute("chaveArquivoAtualizar");
		
		sessao.removeAttribute("ehPopup");
		sessao.removeAttribute("servicoTipo");
		sessao.removeAttribute("colecaoServicoTipoPrioridade");
		sessao.removeAttribute("colecaoServicosTipo");
		
		return retorno;
	}
}