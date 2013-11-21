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
import gcom.atendimentopublico.ordemservico.OrdemServicoFoto;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1199] – Acompanhar Arquivos de Roteiro
 * 
 * @author Thúlio Araújo
 *
 * @date 15/07/2011
 */
public class SelecionarAcompanhamentoArquivosRoteiroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirAcompanhamentoArquivosRoteiro");
		
		HttpSession sessao = httpServletRequest.getSession(false);		

		// Precisa pegar a unidade do usuario do login que esta na sessao
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		Integer idUnidadeLotacao = usuario.getUnidadeOrganizacional().getId();
		
		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Form
		AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanhamentoArquivosRoteiroActionForm = 
			(AcompanharRoteiroProgramacaoOrdemServicoActionForm) actionForm;
		
		String limparSessao = httpServletRequest.getParameter("limparSessao");

		String metodo = httpServletRequest.getParameter("metodo");
		if (Util.verificarNaoVazio(metodo) && metodo.equals("visualizarFotos")){
			Integer idOS = Integer.valueOf(httpServletRequest.getParameter("idOS"));
			
			ArrayList<OrdemServicoFoto> fotos = (ArrayList<OrdemServicoFoto>) Fachada.getInstancia().pesquisarFotosOrdemServico(idOS, true);
			if (!Util.isVazioOrNulo(fotos)){
				sessao.setAttribute("colecaoFotoOS", fotos);
				sessao.setAttribute("numeroFotos", fotos.size());
				sessao.setAttribute("idFoto", fotos.get(0).getId().intValue());
			} else {
				throw new ActionServletException("atencao.ordem.servico.nao.possui.foto");
			}
			retorno = actionMapping.findForward("fotos");
		} else {
			Collection<?> colecaoAcompanhamentoArquivosRoteiro = fachada.pesquisarAcompanhamentoArquivosRoteiro(
					acompanhamentoArquivosRoteiroActionForm.getDataProgramacao(),
					acompanhamentoArquivosRoteiroActionForm.getIdEmpresa(),
					acompanhamentoArquivosRoteiroActionForm.getIdSituacao(),
					idUnidadeLotacao);
			
			if (!colecaoAcompanhamentoArquivosRoteiro.isEmpty()){
				sessao.setAttribute("achou","1");
			} else {
				throw new ActionServletException("atencao.nao_existe_dados_filtro");
			}
			sessao.setAttribute("colecaoAcompanhamentoArquivosRoteiro", colecaoAcompanhamentoArquivosRoteiro);
		}
		
		Date dataInformada = Util.converteStringParaDate(acompanhamentoArquivosRoteiroActionForm.getDataProgramacao());
		Date dataAtual = Util.formatarDataSemHora(new Date());
		
		if (Util.compararData(dataInformada, dataAtual) != -1){
			httpServletRequest.setAttribute("exibirBotoes", true);
		}
		
		if (limparSessao != null && !limparSessao.equals("")){
			sessao.removeAttribute("dataRoteiroInformarSituacao");
			sessao.removeAttribute("chaveOsInformarSituacao");
			sessao.removeAttribute("chaveArquivoInformarSituacao");
		}
		
		String idOs = (String)sessao.getAttribute("chaveOsInformarSituacao");
		
		if (idOs != null && !idOs.equals("")){
			this.atualizarInformaSituacaoOrdemServico(sessao);
		}
		
		httpServletRequest.setAttribute("fecharPopup", "true");
		
		return retorno;
	}
	
	private void atualizarInformaSituacaoOrdemServico(HttpSession sessao){
		
		Fachada fachada = Fachada.getInstancia();
		
		Date dataRoteiro = (Date)sessao.getAttribute("dataRoteiroInformarSituacao");
		String idOs = (String) sessao.getAttribute("chaveOsInformarSituacao");
		String chaveArquivo = (String) sessao.getAttribute("chaveArquivoInformarSituacao");
		
		OrdemServico ordemServico = fachada.recuperaOSPorId(Util.converterStringParaInteger(idOs));
		
		Integer motivoNaoEncerramentoOs = null;
		
		fachada.atualizarOrdemProgramacaoAcompServicoInformarSituacao(Util.converterStringParaInteger(chaveArquivo),dataRoteiro,
				ordemServico.getId(), ordemServico.getSituacao(), motivoNaoEncerramentoOs);
		
		boolean naoInformaIndicadorAtivo = false;
		
		fachada.atualizarOrdemServicoProgramacaoSituacaoOs(ordemServico.getId(),
				dataRoteiro,ordemServico.getSituacao(),motivoNaoEncerramentoOs, naoInformaIndicadorAtivo);
		
		sessao.removeAttribute("dataRoteiroInformarSituacao");
		sessao.removeAttribute("chaveOsInformarSituacao");
		sessao.removeAttribute("chaveArquivoInformarSituacao");
	}
}