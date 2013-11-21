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

import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.Tramite;
import gcom.atendimentopublico.registroatendimento.bean.RAFiltroHelper;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

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
 * @date 15/01/2007, 10/01/2008
 */
public class RegistroAtendimentoTramitacaoPopupAction extends GcomAction {
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
		ActionForward retorno = actionMapping.findForward("registroAtendimentoTramitacaoPopupFechar");
		
		ConjuntoTramitacaoRaActionForm form = (ConjuntoTramitacaoRaActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		 //Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");	
		
		String[] ids = (String[])sessao.getAttribute("ids");
		
        //Data e Hora da tramitação
		String dataHoraTramitacao = form.getDataTramitacao() + " " + form.getHoraTramitacao()+ ":00";
		Date dataHoraTramitacaoObjetoDate = Util.converteStringParaDateHora(dataHoraTramitacao);
		
		//Unidade Destino
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID,form.getIdUnidadeDestino()));
		UnidadeOrganizacional unidadeDestino =(UnidadeOrganizacional) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroUnidadeOrganizacional,UnidadeOrganizacional.class.getName()));
		
		//[FS0006] Valida Data
		//[FS0007] Valida Hora
		//[FS0008] Valida Unidade Destino		
		fachada.validarConjuntoTramitacao(ids, dataHoraTramitacaoObjetoDate, Integer.parseInt(form.getIdUnidadeDestino()),usuario);
		
		Collection tramites = null;
		//Recupera a coleção de tramite da sessão, caso exista, ou cria uma nova
		if(sessao.getAttribute("tramites") != null && !sessao.getAttribute("tramites").equals("")){
			tramites = (Collection)sessao.getAttribute("tramites");
		}else{
			tramites  = new ArrayList();
		}
		
		boolean achou = false;
		
		if (ids != null && ids.length != 0) {
			for (int i = 0; i < ids.length; i++) {
				achou = false;
				String[] idsTramitacao = ids[i].split(";");
				 // Verifica a existência da coleção na sessão.
				if(sessao.getAttribute("tramites") != null && !sessao.getAttribute("tramites").equals("")){
				   Collection tramitesSessao = (Collection)sessao.getAttribute("tramites");
					if(tramitesSessao != null && !tramitesSessao.isEmpty()){
					  Iterator iteratorTramite = tramitesSessao.iterator();
					  while(iteratorTramite.hasNext()){
						  Tramite tramiteColecao = (Tramite)iteratorTramite.next();
						  //Caso exita na colecão da sessão o registro de atendimento selecionado atualiza o tramite existente.							 
						  if(tramiteColecao.getRegistroAtendimento().getId().equals(Integer.parseInt(idsTramitacao[0]))){
							  //Unidade Destino
							  tramiteColecao.setUnidadeOrganizacionalDestino(unidadeDestino); 
							  //Atualiza a unidade Destino do RA
							  atualizarUnidadeDestinoColecao(Integer.parseInt(idsTramitacao[0]), sessao, unidadeDestino);
							  //Usuário Responsável
							  if(form.getIdUsuarioResponsavel() != null && !form.getIdUsuarioResponsavel().equals("")){
								Usuario usuarioResponsavel = new Usuario();
								usuarioResponsavel.setId(Integer.parseInt(form.getIdUsuarioResponsavel()));
								tramiteColecao.setUsuarioResponsavel(usuarioResponsavel);
							  }else{
								  tramiteColecao.setUsuarioResponsavel(null);  
							  }
						      //Data da tramitação
							  tramiteColecao.setDataTramite(dataHoraTramitacaoObjetoDate);
							  //Parecer da tramitação
							  if(form.getParecer() != null && !form.getParecer().equals("")){
								  tramiteColecao.setParecerTramite(form.getParecer());					
							  }else{
							       tramiteColecao.setParecerTramite(null);	
							  }	
							achou = true;
							break;
						  }
					  }
					}
				}
			if(!achou){
				// Cria o tramite e joga na sessão
				Tramite tramite = new Tramite();
				//Unidade Destino
				tramite.setUnidadeOrganizacionalDestino(unidadeDestino);
				//Atualiza a unidade Destino do RA
				atualizarUnidadeDestinoColecao(Integer.parseInt(idsTramitacao[0]), sessao, unidadeDestino);
				//Registro de Atendimento
				FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
				filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade(FiltroRegistroAtendimento.SOLICITACAO_TIPO);
				
				filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID, idsTramitacao[0]));
				
				Collection colecaoRA = fachada.pesquisar(filtroRegistroAtendimento, RegistroAtendimento.class.getName());
				
				RegistroAtendimento ra = (RegistroAtendimento) Util.retonarObjetoDeColecao(colecaoRA);
//				ra.setId(Integer.parseInt(idsTramitacao[0]));
				tramite.setRegistroAtendimento(ra);	
				//Unidade Atual
				FiltroUnidadeOrganizacional filtroUnidadeOrganizacionalAtual = new FiltroUnidadeOrganizacional();
				filtroUnidadeOrganizacionalAtual.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
				filtroUnidadeOrganizacionalAtual.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID,Integer.parseInt(idsTramitacao[1])));
				UnidadeOrganizacional unidadeAtual =(UnidadeOrganizacional) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroUnidadeOrganizacionalAtual,UnidadeOrganizacional.class.getName()));

				tramite.setUnidadeOrganizacionalOrigem(unidadeAtual);
				//Usuário Responsável
				if(form.getIdUsuarioResponsavel() != null && !form.getIdUsuarioResponsavel().equals("")){
					Usuario usuarioResponsavel = new Usuario();
					usuarioResponsavel.setId(Integer.parseInt(form.getIdUsuarioResponsavel()));
					tramite.setUsuarioResponsavel(usuarioResponsavel);
				}
				//Usuário Logado
				tramite.setUsuarioRegistro(usuario);
			    //Data da tramitação				
				tramite.setDataTramite(dataHoraTramitacaoObjetoDate);
				//Parecer da tramitação
				if(form.getParecer() != null && !form.getParecer().equals("")){
				  tramite.setParecerTramite(form.getParecer());
				}
				
				fachada.validarTramitacao(tramite, usuario);
				tramites.add(tramite);
			}
			}
		}	
		
		sessao.setAttribute("tramites", tramites);
		
		httpServletRequest.setAttribute("fecharPopup", "OK");
		
		sessao.removeAttribute("ConjuntoTramitacaoRaActionForm");
		
		return retorno;
	}
	
	/**
	 * Atualiza a unidade destino no filtro da pesquisa
	 *
	 * @param idRA
	 * @param sessao
	 * @param unidadeDestino
	 */
	private void atualizarUnidadeDestinoColecao(Integer idRA, HttpSession sessao, UnidadeOrganizacional unidadeDestino){
		Collection colecaoRAHelper = (Collection)sessao.getAttribute("colecaoRAHelper");
		RAFiltroHelper helper = null;
		Iterator iteratorColecaoRaHelper = colecaoRAHelper.iterator();
		  while(iteratorColecaoRaHelper.hasNext()){
			   helper = (RAFiltroHelper)iteratorColecaoRaHelper.next();
			   if(helper.getRegistroAtendimento().getId().equals(idRA)){
				  helper.setUnidadeDestino(unidadeDestino);   
				  break;
			   }
		  }
		}
}
