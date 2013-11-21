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
* Ivan Sérgio Virginio da Silva Júnior
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

import gcom.atendimentopublico.ordemservico.bean.ManterOrdemServicoConcluidaHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC0753] Manter Ordem de Servico Concluida
 * 
 * @author Ivan Sérgio
 * @date 26/03/2008
 * 
 */
public class ExibirAtualizarOrdemServicoConcluidaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirAtualizarOrdemServicoConcluidaAction");
		
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		ManterOrdemServicoConcluidaActionForm form = (ManterOrdemServicoConcluidaActionForm) actionForm;
		
		// Verifica se o Usuario tem permissão para Atualizar a Fiscalizacao
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		boolean usuarioPermissaoAtualizar = fachada.verificarPermissaoAtualizarDadosFiscalizacao(usuario);
		httpServletRequest.setAttribute("usuarioPermissaoAtualizar", usuarioPermissaoAtualizar);
		
		// Verifica se o Usuario tem permissão para Desfazer a Fiscalizacao
		boolean usuarioPermissaoDesfazerFiscalizacao = 
			fachada.verificarPermissaoDesfazerFiscalizacaoBoletimOSConcluida(usuario);
		httpServletRequest.setAttribute(
				"usuarioPermissaoDesfazerFiscalizacao", usuarioPermissaoDesfazerFiscalizacao);
		
		// Limpa os Campos
		if (httpServletRequest.getParameter("menu") != null) {
			form.setIdOrdemServico("");
			form.setDataEmissao("");
			form.setDataEncerramento("");
			form.setIdImovel("");
			form.setCodigoFiscalizacao("");
			form.setDataFiscalizacao1("");
			form.setDataFiscalizacao2("");
			form.setDataFiscalizacao3("");
			form.setIdUsuario("");
			form.setIndicadorTrocaProtecao("");
			form.setIndicadorTrocaRegistro("");
			form.setDescricaoHidrometroLocalInstalacao("");
			form.setDataEncerramentoBoletim("");
			form.setDataUltimaAlteracao("");
		}
		
		// Numero OS
		Integer idOrdemServico = null;
		// Desfazer
		if (httpServletRequest.getParameter("desfazer") != null) {
			if (!httpServletRequest.getParameter("numeroOs").equals("")) {
				idOrdemServico = new Integer(httpServletRequest.getParameter("numeroOs"));	
			}
		}
		// Para a consulta normal
		if (httpServletRequest.getParameter("idOrdemServicoPesquisado") != null) {
			if (!httpServletRequest.getParameter("idOrdemServicoPesquisado").equals("")) {
				idOrdemServico = new Integer(httpServletRequest.getParameter("idOrdemServicoPesquisado"));
			}
		}
		
		if (idOrdemServico != null) {
			ManterOrdemServicoConcluidaHelper helper = fachada.pesquisarOrdemServicoConcluida(idOrdemServico);
			
			// [FS0001 - Verificar existencia de dados]
			if (helper != null) {
				form.setIdOrdemServicoPesquisado(""); 
				
				// Id da Ordem de Servico
				form.setIdOrdemServico(helper.getIdOrdemServico().toString());
				// Data de Emissao
				if (helper.getDataEmissao() != null) {
					form.setDataEmissao(Util.formatarData(helper.getDataEmissao()));
				}else {
					form.setDataEmissao("");
				}
				// Data de Encerramento
				if (helper.getDataEncerramento() != null) {
					form.setDataEncerramento(Util.formatarData(helper.getDataEncerramento()));
				}else {
					form.setDataEncerramento("");
				}
				// Matricula do Imovel
				form.setIdImovel(helper.getIdImovel().toString());
				// Local de Instalacao
				form.setDescricaoHidrometroLocalInstalacao(helper.getDescricaoHidrometroLocalInstalacao());
				// Indicador de Troca de Protecao
				form.setIndicadorTrocaProtecao(helper.getIndicadorTrocaProtecao().toString());
				httpServletRequest.setAttribute("indicadorTrocaProtecao", form.getIndicadorTrocaProtecao());
				// Indicador de Troca de Registro
				form.setIndicadorTrocaRegistro(helper.getIndicadorTrocaRegistro().toString());
				httpServletRequest.setAttribute("indicadorTrocaRegistro", form.getIndicadorTrocaRegistro());
				// Codigo da Situacao de Fiscalizacao
				form.setCodigoFiscalizacao(helper.getCodigoFiscalizacao().toString());
				httpServletRequest.setAttribute("codigoFiscalizacao", form.getCodigoFiscalizacao());
				// Codigo da Situacao de Fiscalizacao Anterior
				form.setCodigoFiscalizacaoAnterior(helper.getCodigoFiscalizacao().toString());
				httpServletRequest.setAttribute("codigoFiscalizacaoAnterior", form.getCodigoFiscalizacaoAnterior());
				// Data da 1a Fiscalizacao
				if (helper.getDataFiscalizacao1() != null) {
					form.setDataFiscalizacao1(Util.formatarData(helper.getDataFiscalizacao1()));
				}else {
					form.setDataFiscalizacao1("");
				}
				// Data da 2a Fiscalizacao
				if (helper.getDataFiscalizacao2() != null) {
					form.setDataFiscalizacao2(Util.formatarData(helper.getDataFiscalizacao2()));
				}else {
					form.setDataFiscalizacao2("");
				}
				// Data da 3a Fiscalizacao
				if (helper.getDataFiscalizacao3() != null) {
					form.setDataFiscalizacao3(Util.formatarData(helper.getDataFiscalizacao3()));
				}else {
					form.setDataFiscalizacao3("");
				}
				// Id do Funcionario
				if (helper.getIdUsuario() != null) {
					form.setIdUsuario(helper.getIdUsuario().toString());
				}else {
					form.setIdUsuario("");
				}
				// Date de Encerramento do Boletim
				if (helper.getDataEncerramentoBoletim() != null) {
					form.setDataEncerramentoBoletim(Util.formatarData(helper.getDataEncerramentoBoletim()));
				}else {
					form.setDataEncerramentoBoletim("");
				}
				// Data da Ultima Alteracao
				form.setDataUltimaAlteracao(helper.getDataUltimaAlteracao().toString());
			}else {
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}
		}
		
		return retorno;
	}
}