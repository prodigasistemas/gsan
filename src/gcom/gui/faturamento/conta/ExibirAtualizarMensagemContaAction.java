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
package gcom.gui.faturamento.conta;

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMensagem;
import gcom.faturamento.conta.FiltroContaMensagem;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.MaiorQue;
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
 * < <Descrição da Classe>>/
 * 
 * @author Administrador
 */
public class ExibirAtualizarMensagemContaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirAtualizarMensagemContaAction");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarMensagemContaActionForm atualizarMensagemContaActionForm = (AtualizarMensagemContaActionForm) actionForm;

		atualizarMensagemContaActionForm.setGerenciaRegional("");
		atualizarMensagemContaActionForm.setGrupoFaturamento("");
		atualizarMensagemContaActionForm.setLocalidade("");
		atualizarMensagemContaActionForm.setLocalidadeDescricao("");
		atualizarMensagemContaActionForm.setMensagemConta01("");
		atualizarMensagemContaActionForm.setMensagemConta02("");
		atualizarMensagemContaActionForm.setMensagemConta03("");
		atualizarMensagemContaActionForm.setReferenciaFaturamento("");
		atualizarMensagemContaActionForm.setSetorComercial("");
		atualizarMensagemContaActionForm.setSetorComercialDescricao("");
		atualizarMensagemContaActionForm.setQuadra("");

		String idMensagemConta = httpServletRequest
				.getParameter("idMensagemConta");

		if (idMensagemConta != null && !idMensagemConta.equalsIgnoreCase("")) {

	        FiltroContaMensagem filtroContaMensagem = new FiltroContaMensagem();
			filtroContaMensagem.adicionarParametro(new ParametroSimples(
					FiltroContaMensagem.ID, idMensagemConta));
			filtroContaMensagem
					.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroContaMensagem
					.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
			filtroContaMensagem
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroContaMensagem
					.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
			filtroContaMensagem
					.adicionarCaminhoParaCarregamentoEntidade("quadra");
			Collection colecaoMensagemConta = fachada.pesquisar(
					filtroContaMensagem, ContaMensagem.class.getName());
			sessao.setAttribute("colecaoContaMensagem", colecaoMensagemConta);
			if (colecaoMensagemConta != null && !colecaoMensagemConta.isEmpty()) {
				for (Iterator iter = colecaoMensagemConta.iterator(); iter
						.hasNext();) {
					ContaMensagem contaMensagem = (ContaMensagem) iter.next();

					FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
					
					Collection sistemaParametro = fachada.pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());

					Integer refSistemaParametro = new Integer(((SistemaParametro) sistemaParametro.iterator().next()).getAnoMesFaturamento());
					Integer refContaMensagem = new Integer (contaMensagem.getAnoMesRreferenciaFaturamento());
					
					if (refSistemaParametro > refContaMensagem){
						FiltroSistemaParametro filtroSistemaParametro2 = new FiltroSistemaParametro();
						Collection colecaoFSP = fachada.pesquisar(filtroSistemaParametro2, SistemaParametro.class.getName());
						
						if (colecaoFSP != null && !colecaoFSP.isEmpty()){
							SistemaParametro sistemaParametro2 = (SistemaParametro) colecaoFSP.iterator().next();
						
							String mesAno = Util.formatarAnoMesParaMesAno(sistemaParametro2.getAnoMesFaturamento());
							
							throw new ActionServletException(
								 	"atencao.mensagem_nao_inserida", null, mesAno);
						}
					}
		
					atualizarMensagemContaActionForm
							.setReferenciaFaturamento(Util
									.formatarAnoMesParaMesAno(contaMensagem
											.getAnoMesRreferenciaFaturamento()
											.toString()));
					if (contaMensagem.getFaturamentoGrupo() != null) {
						atualizarMensagemContaActionForm
								.setGrupoFaturamento(contaMensagem
										.getFaturamentoGrupo().getDescricao());
					}
					if (contaMensagem.getGerenciaRegional() != null) {
						atualizarMensagemContaActionForm
								.setGerenciaRegional(contaMensagem
										.getGerenciaRegional().getNome());
					}
					if (contaMensagem.getLocalidade() != null) {
						atualizarMensagemContaActionForm
								.setLocalidade(contaMensagem.getLocalidade()
										.getId().toString());
						atualizarMensagemContaActionForm
								.setLocalidadeDescricao(contaMensagem
										.getLocalidade().getDescricao());
					}
					if (contaMensagem.getSetorComercial() != null) {
						atualizarMensagemContaActionForm
								.setSetorComercial(new Integer(contaMensagem
										.getSetorComercial().getCodigo())
										.toString());
						atualizarMensagemContaActionForm
								.setSetorComercialDescricao(contaMensagem
										.getSetorComercial().getDescricao());
					}
					if (contaMensagem.getSetorComercial() != null) {
						atualizarMensagemContaActionForm
								.setSetorComercial(new Integer(contaMensagem
										.getSetorComercial().getCodigo())
										.toString());
						atualizarMensagemContaActionForm
								.setSetorComercialDescricao(contaMensagem
										.getSetorComercial().getDescricao());
					}
					if (contaMensagem.getQuadra() != null) {
						atualizarMensagemContaActionForm
								.setQuadra(contaMensagem
										.getQuadra().getNumeroQuadra() +"");
					}
					if (contaMensagem.getDescricaoContaMensagem02() != null) {
						atualizarMensagemContaActionForm
								.setMensagemConta02(contaMensagem
										.getDescricaoContaMensagem02());
					}
					if (contaMensagem.getDescricaoContaMensagem03() != null) {
						atualizarMensagemContaActionForm
								.setMensagemConta03(contaMensagem
										.getDescricaoContaMensagem03() );
					}
					atualizarMensagemContaActionForm
							.setMensagemConta01(contaMensagem
									.getDescricaoContaMensagem01());
				}
			} else {
				throw new ActionServletException(
						"atencao.conta_mensagem_nao_existente");
			}

		} else {
			Collection colecaoMensagemConta = (Collection) sessao
					.getAttribute("colecaoContaMensagem");

			if (colecaoMensagemConta != null && !colecaoMensagemConta.isEmpty()) {
				

				for (Iterator iter = colecaoMensagemConta.iterator(); iter
						.hasNext();) {
					ContaMensagem contaMensagem = (ContaMensagem) iter.next();
					
					
					FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
					filtroSistemaParametro.adicionarParametro(new MaiorQue(FiltroSistemaParametro.ANO_MES_REFERECIA_ARRECADACAO, contaMensagem.getAnoMesRreferenciaFaturamento()));
					
					Collection sistemaParametro = fachada.pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());

					if (sistemaParametro != null && !sistemaParametro.isEmpty()){
						Integer refSistemaParametro = new Integer(((SistemaParametro) sistemaParametro.iterator().next()).getAnoMesFaturamento());
						Integer refContaMensagem = new Integer (contaMensagem.getAnoMesRreferenciaFaturamento());
						
						if (refSistemaParametro > refContaMensagem){
							FiltroSistemaParametro filtroSistemaParametro2 = new FiltroSistemaParametro();
							Collection colecaoFSP = fachada.pesquisar(filtroSistemaParametro2, SistemaParametro.class.getName());
							
							if (colecaoFSP != null && !colecaoFSP.isEmpty()){
								SistemaParametro sistemaParametro2 = (SistemaParametro) colecaoFSP.iterator().next();
							
								String mesAno = Util.formatarAnoMesParaMesAno(sistemaParametro2.getAnoMesFaturamento());
								
								throw new ActionServletException(
									 	"atencao.mensagem_nao_inserida", null, mesAno);
							}
						}
					}
					
					atualizarMensagemContaActionForm
							.setReferenciaFaturamento(Util
									.formatarAnoMesParaMesAno(contaMensagem
											.getAnoMesRreferenciaFaturamento()
											.toString()));
					if (contaMensagem.getFaturamentoGrupo() != null) {
						atualizarMensagemContaActionForm
								.setGrupoFaturamento(contaMensagem
										.getFaturamentoGrupo().getDescricao());
					}
					if (contaMensagem.getGerenciaRegional() != null) {
						atualizarMensagemContaActionForm
								.setGerenciaRegional(contaMensagem
										.getGerenciaRegional().getNome());
					}
					if (contaMensagem.getLocalidade() != null) {
						atualizarMensagemContaActionForm
								.setLocalidade(contaMensagem.getLocalidade()
										.getId().toString());
						atualizarMensagemContaActionForm
								.setLocalidadeDescricao(contaMensagem
										.getLocalidade().getDescricao());
					}
					if (contaMensagem.getSetorComercial() != null) {
						atualizarMensagemContaActionForm
								.setSetorComercial(new Integer(contaMensagem
										.getSetorComercial().getId())
										.toString());
						atualizarMensagemContaActionForm
								.setSetorComercialDescricao(contaMensagem
										.getSetorComercial().getDescricao());
					}
					if (contaMensagem.getSetorComercial() != null) {
						atualizarMensagemContaActionForm
								.setSetorComercial(new Integer(contaMensagem
										.getSetorComercial().getCodigo())
										.toString());
						atualizarMensagemContaActionForm
								.setSetorComercialDescricao(contaMensagem
										.getSetorComercial().getDescricao());
					}
					if (contaMensagem.getQuadra() != null) {
						atualizarMensagemContaActionForm
								.setQuadra(contaMensagem
										.getQuadra().getNumeroQuadra() + "");
					}
					if (contaMensagem.getDescricaoContaMensagem02() != null && 
							!contaMensagem.getDescricaoContaMensagem02().equals("")) {
						atualizarMensagemContaActionForm
								.setMensagemConta02(contaMensagem
										.getDescricaoContaMensagem02());
					}
					if (contaMensagem.getDescricaoContaMensagem03() != null && 
							!contaMensagem.getDescricaoContaMensagem03().equals("")) {
						atualizarMensagemContaActionForm
								.setMensagemConta03(contaMensagem
										.getDescricaoContaMensagem03());
					}
					atualizarMensagemContaActionForm
							.setMensagemConta01(contaMensagem
									.getDescricaoContaMensagem01());
				}
			} else {
				throw new ActionServletException(
						"atencao.conta_mensagem_nao_existente");
			}
		}
		
		if (httpServletRequest.getParameter("url") != null && httpServletRequest.getParameter("url").equalsIgnoreCase("manter")){
			httpServletRequest.setAttribute("url","javascript:history.back();");
		}else{
			httpServletRequest.setAttribute("url","javascript:window.location.href='/gsan/exibirFiltrarMensagemContaAction.do';");
		}

		return retorno;

	}

}
