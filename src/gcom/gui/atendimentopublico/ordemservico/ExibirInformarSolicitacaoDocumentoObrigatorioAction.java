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

import gcom.atendimentopublico.registroatendimento.FiltroMeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoDocumentoObrigatorio;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoDocumentoObrigatorio;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.atendimentopublico.registroatendimento.ExibirInformarSolicitacaoDocumentoObrigatorioActionForm;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;
import gcom.util.filtro.ParametroSimplesNotIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1067] - Informar Obrigatoriedade Documento Especificação
 * 
 * @author Hugo Leonardo
 *
 * @date 23/08/2010
 */
public class ExibirInformarSolicitacaoDocumentoObrigatorioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirInformarSolicitacaoDocumentoObrigatorioAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		// Form
		ExibirInformarSolicitacaoDocumentoObrigatorioActionForm form = 
			(ExibirInformarSolicitacaoDocumentoObrigatorioActionForm) actionForm;
		
		if (httpServletRequest.getParameter("menu") != null
				&& !httpServletRequest.getParameter("menu").equals("")) {
			
			// Pesquisar Tipo Solicitacao
			this.pesquisarTipoSolicitacao(sessao, form);
		}
		
		// Pesquisar Tipo Especificacao
		if(form.getIdTipoSolicitacao() != null && !form.getIdTipoSolicitacao().equals("-1") && 
				httpServletRequest.getParameter("pesquisa") != null && 
				httpServletRequest.getParameter("pesquisa").equals("tipoEspecificacao") ) {
			
			this.pesquisarTipoEspecificacao(httpServletRequest, sessao, form);
		}else if(form.getIdTipoSolicitacao() != null && form.getIdTipoSolicitacao().equals("-1")){
			
			form.setIcDocumentoSolicitanteObrigatorio(null);
			form.setIdsTipoEspecificacao(null);

			httpServletRequest.removeAttribute("colecaoDocumentoObrigatorio");
			httpServletRequest.removeAttribute("colecaoDocumentoNaoObrigatorio");
			sessao.removeAttribute("colecaoSolicitacaoTipoEspecificacao");
			
		}else if(form.getIdsTipoEspecificacao() != null && form.getIdsTipoEspecificacao()[0].equals("-1")){
			
			form.setIcDocumentoSolicitanteObrigatorio(null);
			form.setIcValidarDocumentoClienteResponsavel(null);
			form.setIdsTipoEspecificacao(null);

			httpServletRequest.removeAttribute("colecaoDocumentoObrigatorio");
			httpServletRequest.removeAttribute("colecaoDocumentoNaoObrigatorio");
		}
		
		// caso tenha selecionado mais de um Tipo de Especificação
		if(httpServletRequest.getParameter("tipoEspecificacao") != null && 
	        	httpServletRequest.getParameter("tipoEspecificacao").equals("muitos") ){
			

			FiltroMeioSolicitacao filtroMeioSolicitacao = new FiltroMeioSolicitacao();
			
			filtroMeioSolicitacao.setConsultaSemLimites(true);
			filtroMeioSolicitacao.adicionarParametro(new ParametroSimples( 
					FiltroMeioSolicitacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoMeioSolicitacaoObrigatorio = 
				Fachada.getInstancia().pesquisar(filtroMeioSolicitacao, MeioSolicitacao.class.getName());
			
			if (colecaoMeioSolicitacaoObrigatorio != null && !colecaoMeioSolicitacaoObrigatorio.isEmpty()) {	
				
				if( form.getIcDocumentoSolicitanteObrigatorio() != null && 
						form.getIcDocumentoSolicitanteObrigatorio().equals("1")){

					httpServletRequest.setAttribute("colecaoDocumentoObrigatorio", colecaoMeioSolicitacaoObrigatorio);
				}else if(form.getIcDocumentoSolicitanteObrigatorio() != null && form.getIcDocumentoSolicitanteObrigatorio().equals("2")){
					
					httpServletRequest.setAttribute("colecaoDocumentoNaoObrigatorio", colecaoMeioSolicitacaoObrigatorio);
				}
				
			}
		// caso tenha selecionado apenas um Tipo de Especificação
		}else if(( httpServletRequest.getParameter("tipoEspecificacao") == null )){
			
			// Monta a colecao de tipos Servicos
			if(form.getIdsTipoEspecificacao() != null && !form.getIdsTipoEspecificacao().equals("-1")){
				
				Collection<Integer> colecaoIdsTipoEspecificacao = new ArrayList();
				String[] array = form.getIdsTipoEspecificacao();
				
				for (int i = 0; i < array.length; i++) {
					if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
						colecaoIdsTipoEspecificacao.add(new Integer(array[i]));
					}
				}
				
				Collection<SolicitacaoTipoEspecificacao> colecaoSolTipoEspec = new ArrayList();
				
				if(colecaoIdsTipoEspecificacao != null && !colecaoIdsTipoEspecificacao.isEmpty()){
					
					FiltroSolicitacaoTipoEspecificacao filtroSolTipoEspec = new FiltroSolicitacaoTipoEspecificacao();
					filtroSolTipoEspec.adicionarParametro(new ParametroSimplesIn( 
							FiltroSolicitacaoTipoEspecificacao.ID, colecaoIdsTipoEspecificacao));	
					
					colecaoSolTipoEspec = this.getFachada().pesquisar(filtroSolTipoEspec, 
							SolicitacaoTipoEspecificacao.class.getName());
					
					SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = null;
					
					Iterator iterator = colecaoSolTipoEspec.iterator();
					solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) iterator.next();
					
					if(httpServletRequest.getParameter("indicadorDoc") == null 
							|| !httpServletRequest.getParameter("indicadorDoc").equals("alterado")){
						
						form.setIcDocumentoSolicitanteObrigatorio("" + 
								solicitacaoTipoEspecificacao.getIndicadorDocumentoObrigatorio());
					}
					
					if(httpServletRequest.getParameter("indicadorDoc") == null 
							|| !httpServletRequest.getParameter("indicadorDoc").equals("alterado")){
					
						form.setIcValidarDocumentoClienteResponsavel("" + 
							solicitacaoTipoEspecificacao.getIndicadorValidarDocResponsavel());
					}
					
					this.pesquisarMeioSolicitacao(httpServletRequest, form);
				}
				
			}else{
				
				form.setIcDocumentoSolicitanteObrigatorio("");
			}
		}
			
		return retorno;
	}
	
	/**
	 * Pesquisa Tipo Solicitacao
	 *
	 * @author Hugo Leonardo
	 * @date 23/08/2010
	 */
	private void pesquisarTipoSolicitacao(HttpSession sessao, 
			ExibirInformarSolicitacaoDocumentoObrigatorioActionForm form) {
	
		FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();
		
		filtroSolicitacaoTipo.setConsultaSemLimites(true);
		filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples( FiltroSolicitacaoTipo.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroSolicitacaoTipo.setCampoOrderBy(FiltroSolicitacaoTipo.DESCRICAO);
		
		Collection colecaoSolicitacaoTipo = 
			this.getFachada().pesquisar(filtroSolicitacaoTipo, SolicitacaoTipo.class.getName());
		
		if(colecaoSolicitacaoTipo == null || colecaoSolicitacaoTipo.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null, "Tipo Solicitação");
		}else{
			sessao.setAttribute("colecaoSolicitacaoTipo", colecaoSolicitacaoTipo);
		}
	}
	
	/**
	 * Pesquisa Tipo Especificacao
	 *
	 * @author Hugo Leonardo
	 * @date 23/08/2010
	 */
	private void pesquisarTipoEspecificacao(HttpServletRequest httpServletRequest, HttpSession sessao,
			ExibirInformarSolicitacaoDocumentoObrigatorioActionForm form) {
	
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
		
		filtroSolicitacaoTipoEspecificacao.setConsultaSemLimites(true);
		filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples( 
				FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples( 
				FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO, new Integer(form.getIdTipoSolicitacao())) );	
		filtroSolicitacaoTipoEspecificacao.setCampoOrderBy(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);
		
		Collection colecaoSolicitacaoTipoEspecificacao = 
			this.getFachada().pesquisar(filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
		
		form.setIcDocumentoSolicitanteObrigatorio(null);
		form.setIcValidarDocumentoClienteResponsavel(null);

		form.setIdsTipoEspecificacao(null);

		
		httpServletRequest.removeAttribute("colecaoDocumentoObrigatorio");
		httpServletRequest.removeAttribute("colecaoDocumentoNaoObrigatorio");
		
		if(colecaoSolicitacaoTipoEspecificacao == null || colecaoSolicitacaoTipoEspecificacao.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null, "Tipo Solicitação Especificação");
		}else{
			
			sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao", colecaoSolicitacaoTipoEspecificacao);
		}
		
	}

	/**
	 * Pesquisa Meio Solicitacao
	 *
	 * @author Hugo Leonardo
	 * @date 23/08/2010
	 */
	private void pesquisarMeioSolicitacao(HttpServletRequest httpServletRequest, 
			ExibirInformarSolicitacaoDocumentoObrigatorioActionForm form){
		
		FiltroSolicitacaoDocumentoObrigatorio filtro = new FiltroSolicitacaoDocumentoObrigatorio();
		
		Collection<Integer> colecaoIdsTipoEspecificacao = new ArrayList();
		
		String[] array = form.getIdsTipoEspecificacao();
		for (int i = 0; i < array.length; i++) {
			if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				colecaoIdsTipoEspecificacao.add(new Integer(array[i]));
			}
		}
		
		filtro.setConsultaSemLimites(true);
		filtro.adicionarParametro(new ParametroSimplesIn( 
				FiltroSolicitacaoDocumentoObrigatorio.SOLICITACAO_TIPO_ESPECIFICACAO_ID, colecaoIdsTipoEspecificacao) );
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoDocumentoObrigatorio.MEIO_SOLICITACAO);

		Collection colecaoSolicEspecDocObrigMeio = 
			Fachada.getInstancia().pesquisar(filtro, SolicitacaoDocumentoObrigatorio.class.getName());

		if (colecaoSolicEspecDocObrigMeio == null || colecaoSolicEspecDocObrigMeio.isEmpty()) {
			
			// 4.4.1
			if(form.getIdsTipoEspecificacao() != null && !form.getIdsTipoEspecificacao().equals("-1")
					&& form.getIcDocumentoSolicitanteObrigatorio() != null 
					&& form.getIcDocumentoSolicitanteObrigatorio().equals("1")){
				
				FiltroMeioSolicitacao filtroMeioSolicitacao = new FiltroMeioSolicitacao();
				
				filtroMeioSolicitacao.setConsultaSemLimites(true);
				filtroMeioSolicitacao.adicionarParametro(new ParametroSimples( 
						FiltroMeioSolicitacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoMeioSolicitacao = 
					Fachada.getInstancia().pesquisar(filtroMeioSolicitacao, MeioSolicitacao.class.getName());

				if (colecaoMeioSolicitacao != null && !colecaoMeioSolicitacao.isEmpty()) {
					httpServletRequest.setAttribute("colecaoDocumentoObrigatorio", colecaoMeioSolicitacao);
				}
				
			// 4.4.2
			}else if(form.getIdsTipoEspecificacao() != null && !form.getIdsTipoEspecificacao().equals("-1")
					&& form.getIcDocumentoSolicitanteObrigatorio() != null
					&& form.getIcDocumentoSolicitanteObrigatorio().equals("2")){
				
				FiltroMeioSolicitacao filtroMeioSolicitacao = new FiltroMeioSolicitacao();
				
				filtroMeioSolicitacao.setConsultaSemLimites(true);
				filtroMeioSolicitacao.adicionarParametro(new ParametroSimples( 
						FiltroMeioSolicitacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoMeioSolicitacaoObrigatorio = 
					Fachada.getInstancia().pesquisar(filtroMeioSolicitacao, MeioSolicitacao.class.getName());

				if (colecaoMeioSolicitacaoObrigatorio != null && !colecaoMeioSolicitacaoObrigatorio.isEmpty()) {
					httpServletRequest.setAttribute("colecaoDocumentoNaoObrigatorio", colecaoMeioSolicitacaoObrigatorio);
				}
			}
		//4.4.3	Caso existam informacoes ja cadastradas em SOL_ESPEC_DOC_OBRIG_MEIO para o STEP_ID selecionado.
		}else{

			if(form.getIcDocumentoSolicitanteObrigatorio().equals("1")){
				Iterator iterator = colecaoSolicEspecDocObrigMeio.iterator();
				
				SolicitacaoDocumentoObrigatorio solicitacaoEspecificacao;
				
				Collection<Integer> colecaoMeioSolicitacao = new ArrayList();
				
				while (iterator.hasNext()) {
					
					solicitacaoEspecificacao = (SolicitacaoDocumentoObrigatorio) iterator.next();
					colecaoMeioSolicitacao.add(solicitacaoEspecificacao.getComp_id().getMeioSolicitacao().getId());
				}
				
				solicitacaoEspecificacao = null;
				
				// Documento Obrigatório
				FiltroMeioSolicitacao filtroMeioSolicitacaoObrigatorio = new FiltroMeioSolicitacao();
				
				filtroMeioSolicitacaoObrigatorio.setConsultaSemLimites( true);
				filtroMeioSolicitacaoObrigatorio.adicionarParametro( new ParametroSimplesIn( FiltroMeioSolicitacao.ID,
						colecaoMeioSolicitacao));
				
				Collection colecaoObrigatorio = Fachada.getInstancia().pesquisar( 
						filtroMeioSolicitacaoObrigatorio, MeioSolicitacao.class.getName());
	
				if (colecaoObrigatorio != null && !colecaoObrigatorio.isEmpty()) {
					httpServletRequest.setAttribute("colecaoDocumentoObrigatorio", colecaoObrigatorio);
				}
				
				// Documento Não Obrigatório
				FiltroMeioSolicitacao filtroMeioSolicitacaoNaoObrigatorio = new FiltroMeioSolicitacao();
				filtroMeioSolicitacaoNaoObrigatorio.setConsultaSemLimites( true);
				filtroMeioSolicitacaoNaoObrigatorio.adicionarParametro(new ParametroSimples( 
						FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroMeioSolicitacaoNaoObrigatorio.adicionarParametro( new ParametroSimplesNotIn( FiltroMeioSolicitacao.ID,
						colecaoMeioSolicitacao));
				
				Collection colecaoNaoObrigatorio = Fachada.getInstancia().pesquisar( 
						filtroMeioSolicitacaoNaoObrigatorio, MeioSolicitacao.class.getName());
	
				if (colecaoNaoObrigatorio != null && !colecaoNaoObrigatorio.isEmpty()) {
					httpServletRequest.setAttribute("colecaoDocumentoNaoObrigatorio", colecaoNaoObrigatorio);
				}
			}else if(form.getIcDocumentoSolicitanteObrigatorio().equals("2")){
				
				// Documento Não Obrigatório
				FiltroMeioSolicitacao filtroMeioSolicitacaoNaoObrigatorio = new FiltroMeioSolicitacao();
				filtroMeioSolicitacaoNaoObrigatorio.setConsultaSemLimites( true);
				filtroMeioSolicitacaoNaoObrigatorio.adicionarParametro(new ParametroSimples( 
						FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
				
				Collection colecaoNaoObrigatorio = Fachada.getInstancia().pesquisar( 
						filtroMeioSolicitacaoNaoObrigatorio, MeioSolicitacao.class.getName());
	
				if (colecaoNaoObrigatorio != null && !colecaoNaoObrigatorio.isEmpty()) {
					httpServletRequest.setAttribute("colecaoDocumentoNaoObrigatorio", colecaoNaoObrigatorio);
				}
			}
		}
	}

}