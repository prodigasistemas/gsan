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

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gcom.atendimentopublico.registroatendimento.Tramite;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0503]Tramitar Conjunto de Registro de Atendimento
 * 
 * @author Ana Maria, Pedro Alexandre		
 * @date 16/01/2007, 08/01/2008
 */
public class RegistroAtendimentoTramitacaoAction extends GcomAction {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta a ação de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		sessao.setAttribute("tramitarConjunto","sim");
		
		Fachada fachada = Fachada.getInstancia();
		
		Collection<Tramite> tramites = (Collection)sessao.getAttribute("tramites");
		
		/* alterado por pedro alexandre */
		//Verifica se o usuário apertou o botão na págian de tramitar
		String primeiraVez = httpServletRequest.getParameter("primeiraVez");

		//Caso seja a primeira vez tramita o RA, caso contrário o RA não
		//vai ser mais tramitado
		if(primeiraVez != null && primeiraVez.equals("ok")){
			if(tramites == null || tramites.isEmpty()){
	            throw new ActionServletException(
	            "atencao.registros_tramitacao.nao_selecionados");
			}
		}	
				
		//Recupera a coleção de ordem de serviço que já foram processadas		
		Collection colecaoOrdemServicoJaTratada = (Collection)sessao.getAttribute("colecaoOrdemServicoJaTratada");
		
		Tramite tramite = (Tramite) Util.retonarObjetoDeColecao(tramites);
		
		//Cria os indicadores de centralizadora e terceira		
		boolean flagCentralizadora = false;
		boolean flagTerceira = false;

		//Caso a unidade organizacional origem seja uma centralizadora
		if(tramite.getUnidadeOrganizacionalOrigem().getUnidadeTipo().getId().equals(UnidadeTipo.UNIDADE_TIPO_CENTRALIZADORA_ID)){
			flagCentralizadora = true;
		}

		//Caso a unidade organizacional destino seja uma terceira		
		if(tramite.getUnidadeOrganizacionalDestino().getUnidadeTipo().getId().equals(UnidadeTipo.UNIDADE_TIPO_TERCERIZADO_ID)){
			flagTerceira = true;
		}

		Collection colecaoOrdemServicoMovimento = new ArrayList();
		Collection colecaoOrdemServicoPavimento = new ArrayList();
		//Exportar as ordems de serviço		
		if(flagCentralizadora && flagTerceira){
			Collection<OrdemServico> colecaoOrdemServicoPrestadora = new ArrayList();
			if(primeiraVez != null && primeiraVez.equals("ok")){
				//[UC0720 - Exportar Ordem Serviço Prestadoras]
//				colecaoOrdemServicoPrestadora = fachada.exportarOrdemServicoPrestadoras(tramites);
//				sessao.setAttribute("colecaoOrdemServicoPrestadora",colecaoOrdemServicoPrestadora);
				
				Map dadosExportar =  fachada.exportarOrdemServicoPrestadoras(tramites);
				colecaoOrdemServicoPrestadora = (Collection)dadosExportar.get("colecaoOrdemServico");
				colecaoOrdemServicoMovimento = (Collection)dadosExportar.get("colecaoOrdemServicoMovimento");
				
				sessao.setAttribute("colecaoOrdemServicoMovimento", colecaoOrdemServicoMovimento);
				sessao.setAttribute("colecaoOrdemServicoPrestadora",colecaoOrdemServicoPrestadora);
				
			}else{
				colecaoOrdemServicoPrestadora = (Collection)sessao.getAttribute("colecaoOrdemServicoPrestadora");
				colecaoOrdemServicoMovimento = (Collection)sessao.getAttribute("colecaoOrdemServicoMovimento");
			}
			
			if(colecaoOrdemServicoJaTratada!= null && !colecaoOrdemServicoJaTratada.isEmpty()){
				colecaoOrdemServicoPrestadora.removeAll(colecaoOrdemServicoJaTratada);
			}

			//Caso ainda tenha ordem de serviço de prestadora para ser processada.			
			if(colecaoOrdemServicoPrestadora!= null && !colecaoOrdemServicoPrestadora.isEmpty()){
				for(OrdemServico ordemServico : colecaoOrdemServicoPrestadora){

					//Caso a ordem de serviço tenha imóvel, exibir o popup para 
					//inserir os dados do paviemnto 
					//Caso contrário inserir a ordem de serviço pavimento 
					//com os dados de pavimento nulos.
					if(ordemServico.getImovel() != null){
						sessao.setAttribute("ordemServico",ordemServico);
						sessao.setAttribute("imovel",ordemServico.getImovel());
						httpServletRequest.setAttribute("indicadorPavimento","sim");
						retorno = actionMapping.findForward("tramitacaoRegistroAtendimento");
						return retorno;
					}else{
						//Inseri a OrdemServicoPavimento com os dados de pavimento 
						OrdemServicoPavimento ordemServicoPavimento = new OrdemServicoPavimento();
						ordemServicoPavimento.setOrdemServico(ordemServico);
						ordemServicoPavimento.setPavimentoRua(null);
						ordemServicoPavimento.setAreaPavimentoRua(null);
						ordemServicoPavimento.setPavimentoCalcada(null);
						ordemServicoPavimento.setAreaPavimentoCalcada(null);
						ordemServicoPavimento.setPavimentoRuaRetorno(null);
						ordemServicoPavimento.setAreaPavimentoRuaRetorno(null);
						ordemServicoPavimento.setPavimentoCalcadaRetorno(null);
						ordemServicoPavimento.setAreaPavimentoCalcadaRetorno(null);
						ordemServicoPavimento.setDataGeracao(new Date());
						
//						fachada.inserirOrdemServicoPavimento(ordemServicoPavimento);
						
						if(sessao.getAttribute("colecaoOrdemServicoPavimento") != null){
							colecaoOrdemServicoPavimento = (Collection)sessao.getAttribute("colecaoOrdemServicoPavimento");
						}	
						
						colecaoOrdemServicoPavimento.add(ordemServicoPavimento);
						
						sessao.setAttribute("colecaoOrdemServicoPavimento", colecaoOrdemServicoPavimento);
					}
				}
			}
		}
				
		//só vai tramitar depois de exibir o popup de dados do pavimento
		//[SB0003]Incluir o Tramite
//		fachada.tramitarConjuntoRA(tramites);
		
		fachada.tramitarConjuntoRAExportandoOSPrestadoras(
				tramites, colecaoOrdemServicoPavimento, colecaoOrdemServicoMovimento);
		
		
		//[SB0001] – Verificar RA de urgência
		if(tramites != null){
			//[SB0034] – Verificar RA de urgência
			for(Tramite t : tramites){
				
				//Caso o STEP_ID da RA correspondente possua SOLICITACAO_TIPO_ESPECIFICACAO.ICURGENCIA=1
				if(this.getFachada().verificarRegistroAtendimentoUrgencia(t.getRegistroAtendimento().getId()) > 0){

					this.getFachada().atualizarUsuarioVisualizacaoRaUrgencia(t.getRegistroAtendimento().getId(), t.getUnidadeOrganizacionalOrigem().getId(), null, 1, null);			
			
					//caso já exista relacionamento entre a RA e Unidade, atualizar usuarios da Unidade com tramitacao e visualizacao = 2.
					if(this.getFachada().verificarUsuariosRegistroAtendimentoUrgencia(t.getRegistroAtendimento().getId(), t.getUnidadeOrganizacionalDestino().getId()) > 0){
						
						this.getFachada().atualizarUsuarioVisualizacaoRaUrgencia(t.getRegistroAtendimento().getId(), t.getUnidadeOrganizacionalDestino().getId(), null, 2, 2);
					     
					//caso não exista relacionamento entre a RA e Unidade, insere nova RA.     
					}else{
						
						getFachada().inserirUsuarioVisualizacaoRaUrgencia(
								t.getRegistroAtendimento().getId(),	ConstantesSistema.NAO);
					}

				}

			}

		}


		sessao.removeAttribute("tramites");
		sessao.removeAttribute("colecaoRAHelper");
		
		if(retorno.getName().equalsIgnoreCase("telaSucesso")){
			//Monta a página de sucesso
			montarPaginaSucesso(httpServletRequest, "Tramitação do Conjunto de Registro de Atendimento efetuada com sucesso.", 
					"Tramitar outro Conjunto de Registro de Atendimento", 
					"exibirFiltrarRegistroAtendimentoTramitacaoAction.do?menu=sim");
		}
		return retorno;
	}
}
